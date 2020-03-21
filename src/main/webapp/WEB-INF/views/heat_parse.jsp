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
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.js" charset="utf-8"></script>
</head>
<body>
<div class="x-nav">
		<span class="layui-breadcrumb">
            <a href="">首页</a>
            <a href="">演示</a>
			<a>
                <cite>导航元素</cite>
            </a>
		</span>
        <a class="layui-btn layui-btn-small"
                   style="line-height: 1.6em; margin-top: 3px; float: right"
                   href="javascript:location.replace(location.href);" title="刷新">
            <i class="layui-icon" style="line-height: 30px">ဂ</i>
        </a>
</div>
<div class="x-body">
    <div class="layui-row">
        <div class="layui-form-item">
            <label class="layui-form-label">选择教材分类：</label>
            <div class="layui-input-inline layui-form">
                <select lay-filter="book_type" id="book_type">
                </select>
            </div>
        </div>
    </div>
    <div id="main" style="width: 600px; height: 400px;"></div>
</div>
</body>
<script type="text/javascript">
    var book_type = "人文";
    var chart = document.getElementById('main');
    var echart = echarts.init(chart);
    //生成图形

    var option = {
        title : {
            text: '各种种类教材已领/入库的比例',
            x:'center'
        },
        tooltip : {
            trigger : 'axis',
            axisPointer : {
                type : 'shadow'
            }
        },
        toolbox : {
            show : true,
            feature : {
                saveAsImage : {
                    show : true
                }
            }
        },
        grid : {
            left : '3%',
            right : '4%',
            bottom : '3%',
            containLabel : true
        },
        xAxis : [ {
            min : 0,
            type : 'category',
            data : []
        } ],
        yAxis : [ {
            min : 0,
            type : 'value'
        } ],
        series : [
            {
                name:'直接访问',
                type:'bar',
                barWidth: '60%',
                data:[]
            }
        ]
    };
    echart.setOption(option);

    $.ajax({
        type : "post",
        async : true, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url : "${pageContext.request.contextPath}/findStoreInPercent", //请求发送到TestServlet处
        data : {
            "book_type" : book_type,
        },
        dataType : "json", //返回数据形式为json
        success : function(result) {
            console.log(result)
            var book_name=[];
            var persent=[];
            $.each(result,function (key,values) {
                book_name.push(values.book_name);
                persent.push(values.persent);
            })
            echart.setOption({ //加载数据图表
                xAxis: {
                    data: book_name
                },
                series: {
                    // 根据名字对应到相应的系列
                    name: ['教材名称'],
                    data: persent
                }
            });
        },
        error : function(errorMsg) {
            //请求失败时执行该函数
            alert("图表请求数据失败!");
            echart.hideLoading();
        }
    })
    layui.use([ 'table', 'form' ], function() {
        var form = layui.form;
        form.render();
        form.on('select(book_type)', function(data) {
            book_type = data.value;
            $.ajax({
                type : "post",
                async : true, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                url : "${pageContext.request.contextPath}/findStoreInPercent", //请求发送到TestServlet处
                data : {
                    "book_type" : book_type
                },
                dataType : "json", //返回数据形式为json
                success : function(result) {
                    console.log(result)
                    var book_name=[];
                    var persent=[];
                    $.each(result,function (key,values) {
                        book_name.push(values.book_name);
                        persent.push(values.persent);
                    })
                    echart.setOption({ //加载数据图表
                        xAxis: {
                            data: book_name
                        },
                        series: {
                            // 根据名字对应到相应的系列
                            name: ['教材名称'],
                            data: persent
                        }
                    });
                },
                error : function(errorMsg) {
                    //请求失败时执行该函数
                    alert("图表请求数据失败!");
                    echart.hideLoading();
                }
            })
            //echart.setOption(option, true);
        });
    });

    $(function () {
        $.ajax({
            url:'${pageContext.request.contextPath}/book/findAllBookKind',
            dataType: 'json',
            type: 'post',
            success: function (data) {
                $.each(data, function (index, item) {
                    console.log("item:"+item)
                    $('#book_type').append(new Option(item.book_kind, item.book_kind));// 下拉菜单里添加元素
                });
                layui.form.render("select");
            }
        });
    });
</script>
</html>