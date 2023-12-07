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
            <form method="get" action="/score/courseChart">
                <div class="form-group">
                    <label class="control-label col-sm-2"><h4 style="font-weight: 800">请选择课程</h4></label>
                    <div class="col-sm-8">
                        <select class="form-control " name="courseId" >
                            <option value=""></option>
                            <c:forEach items="${courseList}" var="course">
                                <option value="${course.id}">${course.courseName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <button class="btn btn-success ">提交</button>
            </form>

            <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
            <div id="main" style="height: 400px;width: 100%"></div>

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
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '数据库课程设计示例',
            left: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: function(data)
            {
                return data.name + '<br/>' +data.seriesName + '：'+data.value; //将小数转化为百分数显示
            }

        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar', 'pie']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        legend: {
            data:['人数'],
            left:'left'
        },
        xAxis: {
            data: ["60分以下","60-70分 ","70-80分 ","80-90分 ","90分以上"]
        },
        yAxis: {},
        series: [{
            name: '人数',
            type: 'bar',
            data: [1, 18, 26, 30, 7],
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
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>
</body>
</html>