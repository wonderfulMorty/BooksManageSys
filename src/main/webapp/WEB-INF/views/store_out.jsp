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

<body class="layui-anim layui-anim-up">
<div class="x-nav">
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
</div>
<div class="x-body">
    <table id="store_in_info" lay-filter="store_in_info"></table>
</div>
<div id="OpRowBar" style="display: none;">
    <button type="button"   class="layui-btn layui-btn-sm " onclick="receive_book()">
        领书
    </button>
    <button type="button"   class="layui-btn layui-btn-sm " >
        修改
    </button>
    <button type="button"   class="layui-btn layui-btn-sm " >
        删除
    </button>
</div>
</body>
<script>
    var documentWidth = $(document).width();
    layui.use([ "element", "laypage", "layer", "upload","table"], function() {
        var element = layui.element;
        var laypage = layui.laypage;
        var layer = layui.layer;
        var upload = layui.upload;//主要是这个
        var table = layui.table;
        var tableIns = table.render({
            elem: '#store_in_info'  //绑定table表格
            ,id:'store_in_info'
            ,dataType:'json'
            ,method:'post'
            ,url: '${pageContext.request.contextPath}/getAllStoreInByPage' //后台springmvc接收路径
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
                    {type: 'checkbox',width: documentWidth*2/100},
                    {field:'id',title:'入库单号', sort: true,width: documentWidth*16/100},
                    {field:'store_time', title:'入库时间',sort:true,width: documentWidth*16/100},
                    {field:'store_info', title:'入库信息',width: documentWidth*10/100},
                    {field:'book_count', title:'库存',width: documentWidth*6/100},
                    {field:'book_name', title:'教材名称',width: documentWidth*10/100},
                    {field:'book_price', title:'教材单价',width: documentWidth*10/100},
                    {field:'qs_name', title:'供应商名称',width: documentWidth*10/100},
                    {field:'operation',title:'操作',toolbar: '#OpRowBar'}
                ]
            ]
        });
    });

    function receive_book() {
        var store_in_num="";//库存单号
        layui.use('table',function(){
            var table = layui.table
            var checkStatus = table.checkStatus('store_in_info');
            console.log(checkStatus.data.length)
            if(checkStatus.data.length>0){//强制用户选择该行复选框
                store_in_num = checkStatus.data[0].id;
                layer.open({
                    type: 2,
                    title: '领取教材',
                    skin: 'layui-layer-molv',
                    shadeClose: false,
                    shade: 0.8,
                    area:  ['700px', '450px'],
                    content: 'teach_receive_book?num='+store_in_num,
                    end: function(){
                        window.location.reload(); //刷新父页面
                    }
                });
            }else {
                layer.msg("请选中该行表格的复选框")
            }
        });
    }


    
</script>
</html>
