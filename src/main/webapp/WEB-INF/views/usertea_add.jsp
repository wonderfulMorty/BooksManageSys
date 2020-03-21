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
        <div>
            <label class="layui-form-label"><i class="layui-icon layui-icon-password"></i> 姓名</label>
            <div class="layui-input-block">
                <input type="text" name="name" placeholder="请输入姓名" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div >
            <label class="layui-form-label" ><i class="layui-icon layui-icon-password"></i>密码</label>
            <div class="layui-input-block" >
                <input type="text" name="password" placeholder="请输入密码" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div >
            <label class="layui-form-label" ><i class="layui-icon layui-icon-password"></i>手机号</label>
            <div class="layui-input-block" >
                <input type="text" name="phone" placeholder="请输入手机号" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div >
            <label class="layui-form-label" ><i class="layui-icon layui-icon-password"></i>邮箱</label>
            <div class="layui-input-block" >
                <input type="text" name="email" placeholder="请输入邮箱" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div>
            <label class="layui-form-label">选择学院：</label>
            <div class="layui-input-block">
                <select lay-filter="college-data" id="college-data">
                </select>
            </div>
        </div>
        <div>
            <label class="layui-form-label">选择专业：</label>
            <div class="layui-input-block">
                <select lay-filter="profession-data" id="profession-data">
                </select>
            </div>
        </div>
        <div>
            <label class="layui-form-label">选择年级：</label>
            <div class="layui-input-block">
                <select lay-filter="grade-data" id="grade-data">
                </select>
            </div>
        </div>
        <div>
            <label class="layui-form-label">选择班级：</label>
            <div class="layui-input-block">
                <select lay-filter="cclass-data" id="cclass-data">
                </select>
            </div>
        </div>
        <div class="layui-form-item lau-sign-other" style="margin-top: 20px;text-align:center">
            <button type="button" class="layui-btn layui-btn-normal" lay-submit="" lay-filter="submitTeaInfo" style="margin-right: 100px">提交</button>
        </div>
    </form>
</body>
<script>
    var college_data = '';
    var profession_data = '';
    var grade_data = '';
    var cclass_data = '';
    var teacher_data ='';
    layui.use(['table','form'], function(){
        var table = layui.table;
        var form = layui.form;
        form.on('select(college-data)', function(data){
            college_data = data.value;
            console.log(college_data)
        });
        form.on('select(profession-data)', function(data){
            profession_data = data.value;
            console.log(profession_data)
        });
        form.on('select(grade-data)', function(data){
            grade_data = data.value;
            console.log(grade_data)
        });
        form.on('select(cclass-data)', function(data){
            cclass_data = data.value;
            console.log(cclass_data)
        });
        form.on('select(teacher-data)', function(data){
            teacher_data = data.value;
            console.log(teacher_data)
        });

        form.on('submit(submitTeaInfo)', function(result) {
            console.log("result.field:"+typeof (result.field))//object
            res = result.field
            res.t_id = teacher_data
            res.col_id = college_data
            res.prof_id= profession_data
            res.gra_id = grade_data
            res.ccl_id = cclass_data
            data = JSON.stringify(res)
            console.log('data:'+data)
            $.ajax({
                url : '${pageContext.request.contextPath}/insertTeaUser',
                data : data,
                type:'POST',
                dataType:'text',
                contentType : 'application/json',
                success:function(data){
                    if(data=="success"){
                        layer.msg("用户信息插入成功！",function(){

                        });
                    }else{
                        layer.msg("用户信息插入失败！",function(){

                        });
                    }
                },
                error:function(args){
                    layer.msg("用户信息插入失败！",function(){

                    });
                }
            });
        });
    });
    $(function () {
        $.ajax({
            url: '${pageContext.request.contextPath}/getAllCollege',
            dataType: 'json',
            type: 'post',
            success: function (data) {
                $('#college-data').html('')
                $('#college-data').append(new Option('请选择学院','请选择学院'))
                $.each(data.data, function (index, item) {
                    $('#college-data').append(new Option(item.col_name, item.id));// 下拉菜单里添加元素
                });
                layui.form.render("select");
            }
        });
        setTimeout(function(){
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath}/getAllProfession",
                success: function (data) {
                    $('#profession-data').html('')
                    $('#profession-data').append(new Option('请选择专业','请选择专业'))
                    $.each(data.data, function (index, item) {
                        console.log("prof:"+item.id)
                        $('#profession-data').append(new Option(item.prof_name, item.id));// 下拉菜单里添加元素
                    });
                    layui.form.render("select");
                }
            });
        },500);
        setTimeout(function(){
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath}/getAllGrade",
                success: function (data) {
                    $('#grade-data').html('')
                    $('#grade-data').append(new Option('请选择年级','请选择年级'))
                    $.each(data.data, function (index, item) {
                        $('#grade-data').append(new Option(item.gra_name, item.id));// 下拉菜单里添加元素
                    });
                    layui.form.render("select");
                }
            });
        },500);
        setTimeout(function(){
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath}/getAllCclass",
                success: function (data) {
                    $('#cclass-data').html('')
                    $('#cclass-data').append(new Option('请选择班级','请选择班级'))
                    $.each(data.data, function (index, item) {
                        $('#cclass-data').append(new Option(item.ccl_name, item.id));// 下拉菜单里添加元素
                    });
                    layui.form.render("select");
                }
            });
        },500)
    })
</script>
</html>
