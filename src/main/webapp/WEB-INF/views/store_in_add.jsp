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
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="layui-unselect lau-sign-body" style="padding-top: 0px " >
    <form  class="layui-form">
        <div class="layui-inline">
            <label class="layui-form-label"><i class="layui-icon layui-icon-password"></i> 教材入库信息</label>
            <div class="layui-input-block">
                <input type="text" name="store_info" placeholder="请输入教材入库信息" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label" ><i class="layui-icon layui-icon-password"></i> 教材入库数量</label>
            <div class="layui-input-block">
                <input type="text" name="book_count" placeholder="请输入教材入库数量" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><i class="layui-icon layui-icon-password"></i> 供应商选择</label>
            <div class="layui-input-block">
                <select lay-filter="qs_name" id="qs_name" class="layui-input">
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"><i class="layui-icon layui-icon-password"></i> 教材选择</label>
            <div class="layui-input-block">
                <select lay-filter="book_name" id="book_name" class="layui-input">
                </select>
            </div>
        </div>
        <div class="layui-form-item lau-sign-other" style="margin-top: 20px;text-align:center">
            <button type="button" class="layui-btn layui-btn-normal" lay-submit="" lay-filter="addStoreIn" style="margin-right: 100px">提交</button>
        </div>
    </form>
</body>
<script>
    layui.use(['table','form'], function(){
        var form = layui.form;
        var book_name =""
        var qs_name = ""
        form.on('select(book_name)', function(data){
            book_name = data.value;
            console.log("bk_name"+book_name)
        });
        form.on('select(qs_name)', function(data){
            qs_name = data.value;
            console.log(qs_name)
            $.getJSON("${pageContext.request.contextPath}/findBookNameByQsName?qs_name="+qs_name, function(data2){
                console.log(data2)
                var optionstring = "";
                $.each(data2, function(i,item){
                    console.log("bs_name:"+item)
                    optionstring += "<option value=\"" + item + "\" >" + item + "</option>";
                });
                $("#book_name").html('<option value=""></option>' + optionstring);
                form.render('select'); //这个很重要
            });
        });
        //监听提交
        form.on('submit(addStoreIn)', function(result) {
            console.log("result.field:"+typeof (result.field))//object
            res = result.field
            res.book_name = book_name;//改变js对象的值
            res.qs_name = qs_name
            data = JSON.stringify(res)//把js对象转换成json字符串,string
            console.log("data:"+typeof (data))
            //var person={"name":"zhangsan","sex":"男","age":"24"}
            //console.log("person:"+typeof (person))
            //sconsole.log(data.field.type())
            $.ajax({
                url : '${pageContext.request.contextPath}/insertStoreIn',
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
            url:'${pageContext.request.contextPath}/findAllQsName',
            dataType: 'json',
            type: 'post',
            success: function (data) {
                $.each(data, function (index, item) {
                    //$('#book_name').append(new Option(item.book_name, item.book_name));// 下拉菜单里添加元素
                    $('#qs_name').append(new Option(item, item));
                });
                layui.form.render("select");
            }
        })
    });
</script>
</html>
