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
        <jsp:param name="menu" value="score_major"/>
    </jsp:include>
    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <div class="box" >
                <div class="box-header with-border">
                    <h3 class="box-title">特定班级成绩统计</h3>
                </div>
                <div class="box-body" >
                    <div id="main" style="height: 400px;width: 100%; "></div>
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
        var myChart = echarts.init(document.getElementById('main'));

       // var cusChart = echarts.init($("#customer")[0]);

        myChart.setOption({
            title : {
                text: '${majorName}——${course.courseName}成绩统计',
                subtext: '成绩源于数据库',
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
                    data:[],
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        });


        $.get("/score/majorChart/${course.id}",{'majorName': '${majorName}'}).done(function (json) {
            if(json.state == 'success') {

                var aNum;
                var bNum;
                var cNum;
                var dNum;
                var eNum;

                var data = json.data;

                var obj = data[0];
                $.each(obj, function (name, value) {
                   if(name == '90分以上') {
                      aNum = value;
                      return true;
                   }
                });
                $.each(obj, function (name, value) {
                    if(name == '80-90分') {
                      bNum = value;
                      return true;
                    }
                });
                $.each(obj, function (name, value) {
                    if(name == '70-80分') {
                        cNum = value;
                        return true;
                    }
                });
                $.each(obj, function (name, value) {
                    if(name == '60-70分') {
                        dNum = value;
                        return true;
                    }
                });
                $.each(obj, function (name, value) {
                    if(name == '60分以下') {
                        eNum = value;
                        return true;
                    }
                });


                myChart.setOption({
                    series: {
                        data: [{value:eNum, name:'60分以下'},
                            {value:dNum, name:'60-70分'},
                            {value:cNum, name:'70-80分'},
                            {value:bNum, name:'80-90分'},
                            {value:aNum, name:'90分以上'}]
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