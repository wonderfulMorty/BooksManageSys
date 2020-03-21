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
            <label class="layui-form-label"><i class="layui-icon layui-icon-password"></i> 领取教材数量</label>
            <div class="layui-input-block">
                <input type="text" name="book_out" placeholder="请输入领取教材数量" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item lau-sign-other" style="margin-top: 20px;text-align:center">
            <button type="button" class="layui-btn layui-btn-normal" lay-submit lay-filter="store_out" style="margin-right: 100px">提交</button>
        </div>
    </form>
</body>
<script>
    var book_id = ""
    var num = getQueryString("num")
    $(function () {
        $.ajax({
            url:'${pageContext.request.contextPath}/getStoreInById',
            dataType: 'text',
            data:{"id":num},
            type: 'post',
            success: function (data) {
                console.log("data:"+data)
                book_id = data;
            }
        })
    });

    layui.use(['table','form'], function(){
        var form = layui.form;
        form.on('submit(store_out)', function(result) {
            res = result.field
            console.log("res:"+res)
            res.book_id = book_id;
            res.id = num;
            data = JSON.stringify(res)
            console.log(data)
            $.ajax({
                url:"${pageContext.request.contextPath}/inserTeachertReceiveBook",
                data : data,
                type:'POST',
                contentType : 'application/json',
                success:function(data){
                    if(data=="success"){
                        layer.msg("成功！",function(){

                        });
                    }else if(data=="cannot receve"){
                        layer.msg("该教材您已有领取记录，不能再次领取，只能修改记录！",function(){

                        });
                    }else {
                        layer.msg("教材领取失败！",function(){

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
</script>
</html>
