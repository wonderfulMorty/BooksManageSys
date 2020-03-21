<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/3
  Time: 1:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>欢迎页面-X-admin2.0</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/xadmin.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<script type="text/html" id="barDemo">
    <a title="修改信息 "   onclick="updateQsById()" href="javascript:;">
        <i class="layui-icon">&#xe642;</i>
    </a>
    <a title="删除信息"  onclick="deleteQsById()" href="javascript:;" lay-event="edit">
        <i class="layui-icon">&#xe640;</i>
    </a>
</script>
<body class="layui-anim layui-anim-up">
<div class="x-nav">
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
</div>
<div class="x-body">
    <xblock>
        <button class="layui-btn layui-btn-danger" onclick="exportData();"><i class="iconfont">&#xe71d;</i>导出excel</button>
        <button class="layui-btn" id="importData"><i class="iconfont">&#xe714;</i>导入excel</button>
    </xblock>
    <table id="supplierInfo" lay-filter="supplierInfo"></table>
</div>
</body>
<script>
    layui.use([ "element", "laypage", "layer", "upload","table"], function() {
        var element = layui.element;
        var laypage = layui.laypage;
        var layer = layui.layer;
        var upload = layui.upload;//主要是这个
        var table = layui.table;
        var tableIns = table.render({
            elem: '#supplierInfo'  //绑定table表格
            ,id:'supplierInfo'
            ,dataType:'json'
            ,method:'post'
            ,url: '${pageContext.request.contextPath}/supplier/getAllSupplierByPage' //后台springmvc接收路径
            ,page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                ,groups: 1 //只显示 1 个连续页码
                ,first: false //不显示首页
                ,last: false //不显示尾页
                ,limit:3
                ,limits:[3,6,9]
            }
            ,cols: [
                [
                    {type: 'checkbox'}
                    ,{field:'id',title:'供货商代码', sort: true}
                    ,{field:'qs_name', title:'供货商名'}
                    ,{field:'qs_location', title:'供货商地址'}
                    ,{field:'qs_phone', title:'供货商联系方式'}
                    ,{field:'operation',title:'操作',toolbar: '#barDemo'}
                ]
            ]
        });

        layui.upload.render({
            elem: "#importData",//导入id
            url: "${pageContext.request.contextPath}/supplier/insertData",
            size: '3072',
            accept: "file",
            exts: 'xls|xlsx|xlsm|xlt|xltx|xltm',
            done: function (result) {
                console.log(result)
                if(result.message=="success"){
                    location.reload(true);
                }else {
                   layer.msg('excel导入失败！');
                }
            },
            error:function (result) {
                layer.msg('excel导入失败！');
            }
        });
    });

    function exportData() {
        window.location.href = "${pageContext.request.contextPath}/supplier/exportData"
    };

    function updateQsById() {
        var qs_id="";
        layui.use('table',function(){
            var table = layui.table
            var checkStatus = table.checkStatus('supplierInfo');
            qs_id = checkStatus.data[0].id;
        });
        layer.open({
            type: 2,
            title: '领取教材',
            skin: 'layui-layer-molv',
            shadeClose: false,
            shade: 0.8,
            area:  ['700px', '450px'],
            content: 'supplier_update?qs_id='+qs_id,
            end: function(){
                window.location.reload(); //刷新父页面
            }
        });
    }

    function deleteQsById() {
        var qs_id="";
        layui.use('table',function(){
            var table = layui.table
            var checkStatus = table.checkStatus('supplierInfo');
            qs_id = checkStatus.data[0].id;
        });
        $.ajax({
            url : '${pageContext.request.contextPath}/deleteQsById',
            data : {"qs_id":qs_id},
            type:'POST',
            success:function(data){
                console.log(data)
                layer.msg("删除成功！",function(){

                });
            },
            error:function(args){
                layer.msg("删除失败！",function(){

                });
            }
        })
    }
</script>
</html>
