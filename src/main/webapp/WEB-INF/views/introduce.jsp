<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>个人信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/lib/layui/css/layui.css" media="all">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" media="all">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css" media="all">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/personal.css" media="all">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/lib/layui/layui.js" charset="utf-8"></script>
</head>
<body>
<section class="layui-larry-box">
    <div class="larry-personal">
        <header class="larry-personal-tit">
            <span>个人信息</span>
        </header><!-- /header -->
        <div class="larry-personal-body clearfix">
            <form class="layui-form col-lg-5"  method="post">
                <div class="layui-form-item">
                    <label class="layui-form-label">账号</label>
                    <div class="layui-input-block">
                        <input type="text" name="id" id="id" autocomplete="off"  class="layui-input layui-disabled" value="${sessionScope.user.getId()}" disabled="disabled" >
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">所属角色</label>
                    <div class="layui-input-block">
                        <input type="text" name="identification" id="identification" autocomplete="off" class="layui-input layui-disabled" value="${sessionScope.user.getIdentification()}" disabled="disabled">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">真实姓名</label>
                    <div class="layui-input-block">
                        <input type="text" name="name" id="name" autocomplete="off" class="layui-input" disabled="disabled">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">手机号码</label>
                    <div class="layui-input-block">
                        <input type="text" name="phone" id="phone" autocomplete="off" class="layui-input" placeholder="输入手机号码" disabled="disabled">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">email</label>
                    <div class="layui-input-block">
                        <input type="text" name="email" id="email" autocomplete="off" class="layui-input" placeholder="输入邮箱" disabled="disabled">
                    </div>
                </div>
            </form>
        </div>
    </div>
</section>
<script type="text/javascript">
    layui.use(['form','upload'],function(){
        var $ = layui.jquery,upload = layui.upload,form=layui.form,imgpath;;
        var uploadInst = upload.render({
            elem: '#photo'
            , url: '${pageContext.request.contextPath}/uploadImg'
            , field: "photo"  //默认是file
            , before: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    console.log("result:"+result)
                    $('#display').attr('src', result.data); //图片链接（base64）
                });
            }
            , done: function (res) {
                console.log(res.data)
                //如果上传失败
                if (res.code > 0) {
                    return layer.msg('上传失败');
                }
                imgpath = res.data;

            }
            , error: function () {
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function () {
                    uploadInst.upload();
                });
            }
        });

        form.on('submit(ch_perInfo)', function(data) {
            var updateJson= data.field;
            updateJson.photo= imgpath;
            data=JSON.stringify(updateJson)
            $.ajax({
                url : '${pageContext.request.contextPath}/updateUserInfo',
                data : data,
                type:'POST',
                contentType: 'application/json',
                success:function(data){
                    console.log(data)
                    layer.msg("修改成功！",function(){

                    });
                },
                error:function(args){
                    layer.msg("账号已经存在或者信息未填完整，注册失败！",function(){

                    });
                }
            });
        });
    });

    $(function () {
        $.ajax({
            url:'${pageContext.request.contextPath}/findUserById',
            dataType: 'json',
            type: 'post',
            success: function (data) {
               res = data.data[0];
               console.log(res.name)
               $("#name").val(res.name)
               $("#phone").val(res.phone)
               $("#email").val(res.email)
            }
        })
    });
</script>
</body>
</html>