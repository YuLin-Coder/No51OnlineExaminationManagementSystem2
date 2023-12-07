<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>OES | Answer_Record</title>

    <!-- css style -->
    <%@ include file="../include/css.jsp"%>
    <style>
        th {
            font-size: 18px;
        }
        td {
            font-size: 17px;
        }
    </style>



</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <!-- 顶部导航栏部分 -->
    <%@ include file="../teacher/include/header.jsp"%>

    <!-- =============================================== -->

    <!-- 左侧菜单栏 -->
    <jsp:include page="../teacher/include/sider.jsp">
        <jsp:param name="menu" value="review"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content col-sm-12">

            <div style="text-align: center">
               <p style="font-size: 20px; font-weight: 400" > 试卷名称：${paper.paperName} &nbsp;&nbsp;&nbsp;&nbsp;
                   &nbsp;&nbsp;&nbsp;&nbsp; 学生姓名：${student.name}
                   &nbsp;&nbsp;&nbsp;&nbsp; 学生学号：${student.stuNumber}</p>
            </div>

            <section class="col-sm-6">
                <div class="box ">

                    <div class="box-body ">

                        <table class="table table-hover">
                            <th>学生答案</th>
                            <tbody>

                            <c:if test="${empty stuAnswer}">
                                <tr>
                                    <td colspan="6">未找到主观题</td>
                                </tr>
                            </c:if>

                                <c:forEach items="${stuAnswer}" var="answer" varStatus="vs" >

                                    <tr class="success">
                                        <td>${vs.count}. ${answer.key}</td>
                                    </tr>
                                    <tr class="rowDetail">
                                        <td>${answer.value}</td>
                                    </tr>

                                </c:forEach>

                            </tbody>
                        </table>

                    </div>
                </div>
            </section>
            <section class="col-sm-6">
                <div class="box ">
                    <div class="box-body ">
                    <table class="table table-hover">
                    <th>题库答案</th>
                    <tbody>

                    <c:if test="${empty questionList}">
                        <tr>
                            <td colspan="6">未找到主观题</td>
                        </tr>
                    </c:if>
                    <c:forEach items="${questionList}" var="question" varStatus="vs">
                        <tr class="rowDetail success" rel="${question.id}">
                            <td>${vs.count}. ${question.questionName}</td>
                        </tr>
                        <tr class="rowDetail" rel="${question.id}">
                            <td>${question.answer}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                </div>
                </div>
            </section>

        </section>




        <!-- /.content -->

    </div>
    <!-- /.content-wrapper -->

    <!-- 底部 -->
    <%@ include file="../include/footer.jsp"%>

</div>
<!-- ./wrapper -->

<!-- js -->
<%@ include file="../include/js.jsp"%>
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/js/moment.js"></script>
<script src="/static/js/jquery.countdown.min.js"></script>

<!-- BSA AdPacks code. Please ignore and remove.-->
<script>

    $(function () {

        $(".rowDetail").click(function () {
            var id = $(this).attr("rel");
            window.location.href = "/question/show/"+id;
        });



    });
</script>
</body>
</html>

