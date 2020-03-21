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
<div id="barDemo" style="display: none;">
    <button type="button"   class="layui-btn layui-btn-sm layui-btn-danger" onclick="deluser()">
        删除
    </button>
    <button type="button"   class="layui-btn layui-btn-sm layui-btn-danger" >
        修改
    </button>
</div>
<body class="layui-anim layui-anim-up">
<div class="x-nav">
    <button type="button"   class="layui-btn layui-btn-sm " onclick="addStuUser()">
        增加学生
    </button>
    <button type="button"   class="layui-btn layui-btn-sm " onclick="addTeaUser()">
        增加教师
    </button>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
</div>
<div class="x-body">
    <form id="userSearch" class="layui-input-inline" method="post" action="" style="margin-top: 20px;">
        <div class="layui-row">
                <div class="layui-form-item">
                    <label class="layui-form-label">选择学院：</label>
                    <div class="layui-input-inline layui-form">
                        <select lay-filter="college-data" id="college-data">
                        </select>
                    </div>
                    <label class="layui-form-label">选择专业：</label>
                    <div class="layui-input-inline layui-form">
                        <select lay-filter="profession-data" id="profession-data">
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">选择年级：</label>
                    <div class="layui-input-inline layui-form">
                        <select lay-filter="grade-data" id="grade-data">
                        </select>
                    </div>
                    <label class="layui-form-label">选择班级：</label>
                    <div class="layui-input-inline layui-form">
                        <select lay-filter="cclass-data" id="cclass-data">
                        </select>
                    </div>
                </div>
        </div>
    </form>
   <%-- <div class="layui-input-inline" >
        <button class="layui-btn" data-type="reload">条件搜索</button>
    </div>--%>

    <div class="layui-input-inline" >
        <button class="layui-btn" data-type="reload">条件搜索</button>
    </div>
    <table id="userInfo" lay-filter="userInfo"></table>
</div>
</body>
<script>
    var college_data = '';
    var profession_data = '';
    var grade_data = '';
    var cclass_data = '';
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
        var tableIns = table.render({
            elem: '#userInfo'  //绑定table表格
            ,id:'userInfo'
            ,method:'post'
            ,url: '${pageContext.request.contextPath}/getAllUser' //后台springmvc接收路径
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
                    ,{field:'id',title:'账号', sort: true}
                    ,{field:'name', title:'姓名'}
                    ,{field:'phone', title:'手机号'}
                    ,{field:'email', title:'邮箱'}
                    ,{field: 'identification', title: '身份'}
                    ,{field: 'col_name', title: '学院名'}
                    ,{field: 'prof_name', title: '专业名'}
                    ,{field: 'gra_name', title: '年级名'}
                    ,{field: 'ccl_name', title: '班级名'}
                    ,{field:'operation',title:'操作',toolbar: '#barDemo'}
                ]
            ]
        });
    var $ = layui.$, active = {
            reload: function(){
                //执行重载
                table.reload('userInfo', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    , method: 'POST'
                    ,where: {
                        key: {
                            college_data:college_data,
                            profession_data : profession_data,
                            grade_data: grade_data,
                            cclass_data :cclass_data
                        }
                    }
                    ,url:'${pageContext.request.contextPath}/getUserInfo'
                });
            }
        };
        $('.layui-input-inline .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
    function deluser(){
        layui.use('table',function(){
            var table = layui.table
            var checkStatus = table.checkStatus('userInfo');
            console.log(checkStatus.data.length)
            if(checkStatus.data.length>0){//强制用户选择该行复选框
                id = checkStatus.data[0].id;
                $.ajax({
                    url:'${pageContext.request.contextPath}/deleteUser',
                    data: {'id':id},
                    success:function (res) {
                        if(res=='success'){
                            layer.msg("删除成功！",function(){

                            });
                        }else {
                            layer.msg("删除成功！",function(){

                            });
                        }
                    }
                })
            }else {
                layer.msg("请选中该行表格的复选框")
            }
        });
    }
    //添加学生用户信息
    function addStuUser(){
        layer.open(
            {
                type: 2,
                title: '添加学生用户',
                skin: 'layui-layer-lan',
                shadeClose: false,
                shade: 0.8,
                area:  ['400px', '500px'],
                resize:true,
                content:'${pageContext.request.contextPath}/userstu_add',
                end: function(){
                    window.location.reload(); //刷新父页面
                }
            }
        );
    }
    //增加教师用户
    function addTeaUser(){
        layer.open(
            {
                type: 2,
                title: '添加教师用户',
                skin: 'layui-layer-lan',
                shadeClose: false,
                shade: 0.8,
                area:  ['400px', '500px'],
                resize:true,
                content:'${pageContext.request.contextPath}/usertea_add',
                end: function(){
                    window.location.reload(); //刷新父页面
                }
            }
        );
    }

    $(function () {
        $.ajax({
            url: '${pageContext.request.contextPath}/getAllCollege',
            dataType: 'json',
            type: 'post',
            success: function (data) {
                $('#college-data').html('')
                $('#college-data').append(new Option('请选择学院','请选择学院'))
                $.each(data.data, function (index, item) {
                    $('#college-data').append(new Option(item.col_name, item.col_name));// 下拉菜单里添加元素
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
                        $('#profession-data').append(new Option(item.prof_name, item.prof_name));// 下拉菜单里添加元素
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
                        $('#grade-data').append(new Option(item.gra_name, item.gra_name));// 下拉菜单里添加元素
                    });
                    layui.form.render("select");
                }
            });
        },500)
        setTimeout(function(){
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath}/getAllCclass",
                success: function (data) {
                    $('#cclass-data').html('')
                    $('#cclass-data').append(new Option('请选择班级','请选择班级'))
                    $.each(data.data, function (index, item) {
                        $('#cclass-data').append(new Option(item.ccl_name, item.ccl_name));// 下拉菜单里添加元素
                    });
                    layui.form.render("select");
                }
            });
        },500)
    })
</script>
</html>
