<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>OES | Course_chart</title>
    <%@ include file="../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">
    <%@include file="../teacher/include/header.jsp"%>
    <!-- =============================================== -->

    <jsp:include page="../teacher/include/sider.jsp">
        <jsp:param name="menu" value="score_course"/>
    </jsp:include>
    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <div class="box" >
                <div class="box-header with-border">
                    <h3 class="box-title">特定课程成绩统计</h3>
                </div>
                <div class="box-body" >
                    <div id="bar" style="height: 400px;width: 100%; "></div>
                </div>
            </div>


        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <%@ include file="../include/footer.jsp"%>

</div>
<!-- ./wrapper -->

<%@include file="../include/js.jsp"%>
<script src="/static/dist/js/echarts.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>

<script>
    $(function () {
        var myChart = echarts.init(document.getElementById('bar'));

       // var cusChart = echarts.init($("#customer")[0]);

        myChart.setOption({
            title: {
                text: '${course.courseName}' + '成绩统计',
                left: 'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: function(data)
                {
                    return data.name + '<br/>' +data.seriesName + '：'+data.value;
                }
            },
            toolbox: {
                feature: {
                    dataView: {show: true, readOnly: false},
                    magicType: {show: true, type: ['line', 'bar']},
                    restore: {show: true},
                    saveAsImage: {show: true}
                }
            },
            legend: {
                data:['人数'],
                left:'left'
            },
            xAxis: {
                name:'分数段',
                data: []
            },
            yAxis: {},
            series: [{
                name: '人数',
                type: 'bar',
                data: [],
                itemStyle: {
                    normal: {
                        label: {
                            show: true,
                            position: 'top',
                            textStyle: {
                                color: '#615a5a'
                            },
                            formatter:function(params){
                                if(params.value==0){
                                    return '';
                                }else
                                {
                                    return params.value;
                                }
                            }
                        }
                    }
                },
            }]
        });


        $.post("/score/courseChart/${course.id}").done(function (json) {
            if(json.state == 'success') {

                var keyArray = [];
                var valueArray = [];
                var data = json.data;

                var obj = data[0];
                $.each(obj, function (name, value) {
                   if(name == '90分以上') {

                       keyArray.push(name);
                       valueArray.push(value);
                       return true;
                   }
                });
                $.each(obj, function (name, value) {
                    if(name == '80-90分') {
                        keyArray.push(name);
                        valueArray.push(value);
                        return true;
                    }
                });
                $.each(obj, function (name, value) {
                    if(name == '70-80分') {
                        keyArray.push(name);
                        valueArray.push(value);
                        return true;
                    }
                });
                $.each(obj, function (name, value) {
                    if(name == '60-70分') {
                        keyArray.push(name);
                        valueArray.push(value);
                        return true;
                    }
                });
                $.each(obj, function (name, value) {
                    if(name == '60分以下') {
                        keyArray.push(name);
                        valueArray.push(value);
                        return true;
                    }
                });


                myChart.setOption({
                    xAxis: {
                        data: keyArray
                    },
                    series: {
                        data: valueArray
                    }
                });

            } else {
                layer.msg("json.message");
            }
        }).error(function () {
            layer.msg("服务器异常");
        });



    });
</script>
</body>
</html>