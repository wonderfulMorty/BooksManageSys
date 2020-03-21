<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/6
  Time: 0:06
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
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/getQueryString.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="layui-unselect lau-sign-body" style="padding-top: 0px " >
    <form  class="layui-form">
        <div class="layui-inline">
            <label class="layui-form-label"><i class="layui-icon layui-icon-password"></i> 供应商名称</label>
            <div class="layui-input-block">
                <input type="text" name="qs_name" placeholder="请输入供应商名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label" ><i class="layui-icon layui-icon-password"></i> 供应商地址</label>
            <div class="layui-input-block">
                <input type="text" name="qs_location" placeholder="请输入供应商地址" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><i class="layui-icon layui-icon-password"></i> 供货商联系方式</label>
            <div class="layui-input-block">
                <input type="text" name="qs_phone" placeholder="请输入供货商联系方式" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item lau-sign-other" style="margin-top: 20px;text-align:center">
            <button type="button" class="layui-btn layui-btn-normal" lay-submit="" lay-filter="updateQsById" style="margin-right: 100px">提交</button>
        </div>
    </form>
</body>
<script>
    var qs_id = getQueryString("qs_id")
    console.log(qs_id)
    layui.use([ "element", "laypage", "layer", "upload","table"], function() {
        var form = layui.form
        //监听提交
        form.on('submit(updateQsById)', function(result) {
            console.log("result.field:"+typeof (result.field))//object
            res = result.field
            res.id = qs_id
            data = JSON.stringify(res)//把js对象转换成json字符串,string
            console.log("data:"+typeof (data))
            //var person={"name":"zhangsan","sex":"男","age":"24"}
            //console.log("person:"+typeof (person))
            //sconsole.log(data.field.type())
            $.ajax({
                url : '${pageContext.request.contextPath}/updateQsById',
                data : data,
                type:'POST',
                dateType:'text',
                contentType : 'application/json',
                success:function(data){
                    if(data=="success"){
                        layer.msg("成功！",function(){

                        });
                    }else{
                        layer.msg("失败！",function(){

                        });
                    }
                },
                error:function(args){
                    layer.msg("失败！",function(){

                    });
                }
            });
        });
    });

    $(function () {
        $.ajax({
            url:'${pageContext.request.contextPath}/book/getAllBook',
            dataType: 'json',
            type: 'post',
            success: function (data) {
                $.each(data.data, function (index, item) {
                    $('#qs_name').append(new Option(item.qs_name, item.qs_name));
                });
                layui.form.render("select");
            }
        })
    });

</script>
</html>
