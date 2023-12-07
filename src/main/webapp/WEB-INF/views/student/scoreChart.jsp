<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>OES | Student_chart</title>
    <%@ include file="../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">
    <%@include file="../student/include/header.jsp"%>
    <!-- =============================================== -->

    <jsp:include page="../student/include/sider.jsp">
        <jsp:param name="menu" value="chart_my"/>
    </jsp:include>
    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <div class="box" style="background-color: inherit">
                <div class="box-header with-border">
                    <h3 class="box-title">我的成绩分析</h3>
                </div>
                <div class="box-body" >
                    <div id="main" style="height: 560px;width: 100%"></div>
                </div>
            </div>


            <div class="box" style="background-color: inherit">
                <div class="box-body" >
                    <div id="chart" style="height: 400px;width: 100%"></div>
                </div>
                <button class="btn btn-primary pull-right"  id="analyse">具体数据</button>

            </div>

            <!-- 为ECharts准备一个具备大小（宽高）的Dom -->


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

        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        option = {
            title: {
                text: '学期成绩雷达图',
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
                    { name: '市场营销学（Marketing）', max: 100}]
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

        //post请求后端数据并整理
        $.post("/score/student/chart/${sessionScope.get("studentId")}").done(function (json) {
            if(json.state == 'success') {

                var keyArray = [];
                var avgArray = [];
                var myArray = [];
                var data = json.data;

                var Course = function(name, max) {
                    this.name = name;
                    this.max = max;
                    this.getName = function() {
                        return this.name;
                    },
                    this.getMax = function() {
                        return this.max;
                    }
                };

                //每门成绩平均分
                var obj = data[0];
                $.each(obj, function (name, value) {
                    avgArray.push(value);
                   var course = new Course(name, 100);

                    keyArray.push(course);
                });


                //我的每门成绩
                obj = data[1];
                $.each(obj, function (name, value) {
                    myArray.push(value);
                });

                myChart.setOption({

                    radar: {
                        indicator:
                        keyArray //一定要吧【】去掉！！！！！ 对象数组，对象有两个属性name,max
//                      { name: keyArray[1], max: 100},
                    },
                    series: [{
                        name: '平均 vs 我的（Average vs My）',
                        type: 'radar',
                        // areaStyle: {normal: {}},
                        data : [
                            {
                                value : avgArray,
                                name : '平均成绩（Average Achievement）'
                            },
                            {
                                value : myArray,
                                name : '我的成绩（My Achievement）'
                            }
                        ]
                    }]
                });

            } else {
                layer.msg("json.message");
            }
        }).error(function () {
            layer.msg("服务器异常");
        });

        // 基于准备好的dom，初始化echarts实例
        var chart = echarts.init(document.getElementById('chart'));

        chart.setOption({
            title : {
                text: '本学期成绩分数段统计',
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

        $.get("/score/student/chart2").done(function (json) {
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


                chart.setOption({
                    series: {
                        data: [{value:eNum, name:'60分以下'},
                            {value:dNum, name:'60-70分'},
                            {value:cNum, name:'70-80分'},
                            {value:bNum, name:'80-90分'},
                            {value:aNum, name:'90分以上'}]
                    }
                });

                //行点击 委托
                $(document).delegate("#analyse", "click", function () {



                    //分析的话
                    var txta = "";
                    var txtb = "";
                    var txtc = "";
                    var txtd = "";
                    var txte = "";
                    var levelPaper = data[1];
                    $.each(levelPaper, function (name, value) {
                        if(name == '60分以下') {
                            if(value) {
                            txte  = name + "的考试有：" + value;
                            }
                            return true;
                        }
                    });

                    $.each(levelPaper, function (name, value) {
                        if(name == '60-70分') {
                            if(value) {
                                txtd  = name + "的考试有：" + value;
                            }
                            return true;
                        }
                    });

                    $.each(levelPaper, function (name, value) {
                        if(name == '70-80分') {
                            if(value) {
                                txtc  = name + "的考试有：" + value;
                            }
                            return true;
                        }
                    });

                    $.each(levelPaper, function (name, value) {
                        if(name == '80-90分') {
                            if(value) {
                                txtb  = name + "的考试有：" + value;
                            }
                            return true;
                        }
                    });

                    $.each(levelPaper, function (name, value) {
                        if(name == '90分以上') {
                            if(value) {
                                txta  = name + "的考试有：" + value;
                            }
                            return true;
                        }
                    });

                    layer.open({
                        type: 0,
                        skin: 'layui-layer-demo', //样式类名
                        offset: 'r',
                        closeBtn: 1, //不显示关闭按钮
                        anim: 2,
                        shadeClose: true, //开启遮罩关闭
                        content: "<h4>"+txta+"</h4> <br/>" + "<h4>"+txtb+"</h4> <br/>"
                            + "<h4>"+txtc+"</h4> <br/>" + "<h4>"+txtd+"</h4> <br/>"
                            + "<h4>"+txte+"</h4> <br/>"
                    });
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