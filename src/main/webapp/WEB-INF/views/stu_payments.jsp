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

</script>
<body class="layui-anim layui-anim-up">
<div class="x-nav">
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i>
    </a>
</div>
<div class="x-body">
    <table id="stu_store_view" lay-filter="stu_store_view"></table>
</div>
<div id="OpRowBar" style="display: none;">
    <button type="button"   class="layui-btn layui-btn-sm " >
        修改
    </button>
    <button type="button"   class="layui-btn layui-btn-sm " >
        删除
    </button>
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
            elem: '#stu_store_view'  //绑定table表格
            ,id:'stu_store_view'
            ,dataType:'json'
            ,method:'post'
            ,url: '${pageContext.request.contextPath}/payments' //后台springmvc接收路径
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
                    ,{field:'name',title:'学生姓名', sort: true}
                    ,{field:'book_name', title:'教材名称',sort:true}
                    ,{field:'book_price', title:'教材单价',sort:true}
                    ,{field:'payment', title:'共计价格'}
                    ,{field:'pay_time', title:'付款时间'}
                    ,{field:'operation',title:'操作',toolbar: '#OpRowBar'}
                ]
            ]
        });
    });

    function payments(){
        layui.use('table',function(){
            var table = layui.table
            var checkStatus = table.checkStatus('stu_store_view');
            stoo_id = checkStatus.data[0].stoo_id;
        });
        $.ajax({
            url : '${pageContext.request.contextPath}/payments',
            data : {"stoo_id":stoo_id},
            type:'POST',
            success:function(data){
                console.log(data)
                if(data=='success'){
                    layer.msg("付款成功！",function(){

                    });
                }

            },
            error:function(args){
                layer.msg("付款失败！",function(){

                });
            }
        })
    }
    
    
    
</script>
</html>
