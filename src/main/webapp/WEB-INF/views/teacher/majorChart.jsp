<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>OES | Major_chart</title>
    <%@ include file="../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">
    <%@include file="../teacher/include/header.jsp"%>
    <!-- =============================================== -->

    <jsp:include page="../teacher/include/sider.jsp">
        <jsp:param name="menu" value="score_major"/>
    </jsp:include>
    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <form method="get" action="/score/majorChart" style="z-index: 999; position: relative" >
                <div class="form-group col-sm-12">
                    <label class="control-label col-sm-2"><h4 style="font-weight: 800">请输入专业班级</h4></label>
                    <div class="col-sm-8">
                        <input type="text" name="majorName" value="信管1403" class="form-control">
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <label class="control-label col-sm-2"><h4 style="font-weight: 800">请选择课程</h4></label>
                    <div class="col-sm-8">
                        <select class="form-control " name="courseId" >
                            <option value=""></option>
                            <c:forEach items="${courseList}" var="course">
                                <option value="${course.id}">${course.courseName}</option>
                            </c:forEach>
                        </select>
                    </div>
                        <button class="btn btn-success pull-right">提交</button>
                </div>

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
    option = {
        title : {
            text: '信管1403——信息系统分析与设计示例',
            subtext: '数据非真实',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ["60分以下","60-70分","70-80分","80-90分","90分以上"]
        },
        series : [
            {
                name: '分数段',
                type: 'pie',
                radius : '75%',
                center: ['50%', '60%'],
                data:[
                    {value:335, name:'60分以下'},
                    {value:310, name:'60-70分'},
                    {value:234, name:'70-80分'},
                    {value:135, name:'80-90分'},
                    {value:1548, name:'90分以上'}
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };


    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>
</body>
</html>