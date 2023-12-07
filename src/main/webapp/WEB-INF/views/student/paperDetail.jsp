<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>OES | Paper_Detail</title>

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
    <%@ include file="../student/include/header.jsp"%>

    <!-- =============================================== -->

    <!-- 左侧菜单栏 -->
    <%--<jsp:include page="../student/include/paperDetailSider.jsp">
        <jsp:param name="menu" value="exam"/>
    </jsp:include>--%>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content">
        <!-- Main content -->
        <section class="content">


            <div class="box">
                <div class=" with-border text-center">
                    <h3 class="title" >${paper.paperName}</h3>
                        <h5><div style="font-family: 'Glyphicons Halflings';" class="clock"> 距离考试结束: <span class="clock" style="color: #af0000" id="clock">xx分xx秒</span>
                        </div></h5>


                   <%-- <div class="box-tools">
                        <a href="/question" class="btn btn-primary btn-sm"><i class="fa fa-arrow-left"></i> 返回列表</a>

                            <a href="/question/edit/${question.id}" class="btn bg-purple btn-sm"><i class="fa fa-pencil"></i> 编辑</a>
                            <button id="delBtn" class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i> 删除</button>
                    </div>--%>
                </div>

                <div class="box-body">
                    <form id="paper" method="post">
                        <ul class="list-group">
                            <%--单选题--%>
                                <c:if test="${not empty qChoiceList}">
                                    <div class="panel panel-primary">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">单选题</h4>
                                        </div>
                                        <div class="panel-body">
                                            <c:forEach items="${qChoiceList}" var="choice" varStatus="state" >
                                                ${state.count }. ${choice.questionName}
                                                <div class="radio" >
                                                    <label>
                                                        <input type="radio" name="${choice.id}" required  value="A">
                                                        A. <span style="display: inline-block;"> <c:out value="${choice.optionA}" escapeXml="true"/> </span>
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="${choice.id}" required  value="B">
                                                        B. <span style="display: inline-block;"><c:out value="${choice.optionB}" escapeXml="true"/></span>
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="${choice.id}" required value="C">
                                                        C. <span style="display: inline-block;"><c:out value="${choice.optionC}" escapeXml="true"/></span>
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="${choice.id}" required value="D">
                                                        D. <span style="display: inline-block;"><c:out value="${choice.optionD}" escapeXml="true"/></span>
                                                    </label>
                                                </div>
                                                <hr/>
                                            </c:forEach>
                                        </div>
                                    </div>

                                </c:if>

                            <%--多选题--%>
                            <c:if test="${not empty qMulChoiceList}">
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">多选题 <small>(不选、错选、漏选均不得分)</small></h4>
                                    </div>
                                    <div class="panel-body">
                                        <c:forEach items="${qMulChoiceList}" var="qMulChoice" varStatus="state">
                                            ${state.count }. ${qMulChoice.questionName}
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="${qMulChoice.id}"  value="A">
                                                    A. <span style="display: inline-block;"><c:out value="${qMulChoice.optionA}" escapeXml="true"/> </span>
                                                </label>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="${qMulChoice.id}"  value="B">
                                                    B. <span style="display: inline-block;"><c:out value="${qMulChoice.optionB}" escapeXml="true"/></span>
                                                </label>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="${qMulChoice.id}"  value="C">
                                                    C. <span style="display: inline-block;"><c:out value="${qMulChoice.optionC}" escapeXml="true"/></span>
                                                </label>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="${qMulChoice.id}"  value="D">
                                                    D. <span style="display: inline-block;"><c:out value="${qMulChoice.optionD}" escapeXml="true"/></span>
                                                </label>
                                            </div>
                                            <hr/>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <%--判断题--%>
                            <c:if test="${not empty qTofList}">
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">判断题</h4>
                                    </div>
                                    <div class="panel-body">
                                        <c:forEach items="${qTofList}" var="question" varStatus="state">
                                            ${state.count }. ${question.questionName}
                                            <div class="radio">
                                                <label>
                                                    <input type="radio" name="${question.id}"  required value="对">
                                                    A. 对<span style="display: inline-block;"></span>
                                                </label>
                                            </div>
                                            <div class="radio">
                                                <label>
                                                    <input type="radio" name="${question.id}" required value="错">
                                                    B. 错<span style="display: inline-block;"></span>
                                                </label>
                                            </div>
                                            <hr/>
                                        </c:forEach>
                                    </div>
                                </div>

                            </c:if>
                            <%--填空题--%>
                            <c:if test="${not empty qFillList}">
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">填空题</h4>
                                    </div>
                                    <div class="panel-body">
                                        <c:forEach items="${qFillList}" var="question" varStatus="state">
                                            ${state.count }. ${question.questionName}
                                            <div class="form-group">
                                                <label>答案</label>
                                                <input type="text" name="${question.id}" class="form-control">
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <%--简答题--%>
                            <c:if test="${not empty qSaqList}">
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">简答题</h4>
                                    </div>
                                    <div class="panel-body">
                                        <c:forEach items="${qSaqList}" var="question" varStatus="state">
                                            ${state.count }. ${question.questionName}
                                            <div class="form-group">
                                                <label>答案</label>
                                                <input type="text" name="${question.id}" class="form-control">
                                            </div>
                                            <hr/>
                                        </c:forEach>
                                    </div>
                                </div>

                            </c:if>
                            <%--编程题--%>
                            <c:if test="${not empty qProgramList}">
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">编程题</h4>
                                    </div>
                                    <div class="panel-body">
                                        <c:forEach items="${qProgramList}" var="question" varStatus="state">
                                            ${state.count }. ${question.questionName}
                                            <div class="form-group">
                                                <label>答案</label>
                                                <input type="text" name="${question.id}" class="form-control">
                                            </div>
                                            <hr/>
                                        </c:forEach>
                                    </div>
                                </div>

                            </c:if>

                    </ul>
                        <button id="submitBtn" class="btn btn-success pull-right">提交答案</button>
                    </form>
                </div>
            </div>

        </section>
        <!-- /.content -->

    </div>
    <!-- /.content-wrapper -->

    <!-- 底部 -->


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

        $("#clock").countdown("${paper.endTime}", function (event) {
            $(this).html(event.strftime('%H小时 %M分钟 %S秒'));
        }).on("finish.countdown", function () {
            $("#submitBtn").text("考试结束").attr("disabled","disabled");

            layer.msg('试卷将在2s后自动提交', {icon: 4});

            $(this).delay(2000).queue(function(){
                $("#paper").submit();
            });

        });




    });
</script>
</body>
</html>

