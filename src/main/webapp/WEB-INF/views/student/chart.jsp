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
    <%@include file="../student/include/header.jsp"%>
    <!-- =============================================== -->

    <jsp:include page="../student/include/sider.jsp">
        <jsp:param name="menu" value="chart_demo"/>
    </jsp:include>
    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
            <div id="main" style="height: 560px;width: 100%"></div>

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
        title: {
            text: '学期成绩雷达图示例',
        },
        tooltip: {},
        legend: {
            data: ['平均成绩（Average Achievement）', '我的成绩（My Achievement）']
        },
        radar: {
            // shape: 'circle',
            name: {
                textStyle: {
                    color: '#fff',
                    backgroundColor: '#999',
                    borderRadius: 5,
                    padding: [5, 10]
                }
            },
            indicator: [
                { name: '市场营销学（Marketing）', max: 100},
                { name: '管理学（Management Science）', max: 100},
                { name: '信息安全技术（Information Techology）', max: 100},
                { name: '系统分析（Systems Analysis）', max: 100},
                { name: 'JavaEE编程技术（Programming technology）', max: 100},
                { name: '信息经济学（Information Economics）', max: 100}
            ]
        },
        series: [{
            name: '平均 vs 我的（Average vs My）',
            type: 'radar',
            // areaStyle: {normal: {}},
            data : [
                {
                    value : [62, 67, 76, 73, 78, 84],
                    name : '平均成绩（Average Achievement）'
                },
                {
                    value : [72, 65, 77, 73, 88, 89],
                    name : '我的成绩（My Achievement）'
                }
            ]
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>
</body>
</html>