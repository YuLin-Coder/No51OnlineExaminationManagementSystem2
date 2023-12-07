<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>OES | Exam_list</title>
    <!-- Tell the browser to be responsive to screen width -->
    <%@include file="../include/css.jsp"%>

    <style>

        .table>tbody>tr:hover {
            cursor: pointer;
        }
        .table>tbody>tr>td {
            vertical-align: middle;
        }
        th {
            font-size: 16px;
        }
        td {
            font-size: 16px;
        }

    </style>


</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../student/include/header.jsp"%>

    <!-- =============================================== -->

    <!-- 左侧菜单栏 -->
    <jsp:include page="../student/include/sider.jsp">
        <jsp:param name="menu" value="exam"/>
    </jsp:include>



    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">正式考试</h3>
                    <div class="box-tools pull-right">
                       <%-- <a id="newBtn" class="btn btn-success btn-sm"><i class="fa fa-plus"></i> 新增试卷</a>
                        <a href="/paper/showPaperForm" class="btn btn-success btn-sm"><i class="fa fa-search"></i> 查看模版</a>--%>
                    </div>
                </div>
                <div class="box-body no-padding">
                    <table class="table table-hover">
                        <tbody>
                        <c:if test="${empty paperList}">
                            <tr>
                                <td colspan="6">暂无考试</td>
                            </tr>
                        </c:if>
                        <c:if test="${not empty paperList}">
                            <tr>
                                <th width="10"></th>
                                <th>试卷名称</th>
                                <th>开考时间</th>
                                <th>结束时间</th>
                            </tr>
                        </c:if>
                        <c:forEach items="${paperList}" var="paper" varStatus="state">
                            <tr class="rowDetail" rel="${paper.id}">
                                <td>${state.count}</td>
                                <td>${paper.paperName}</td>
                                <td>${paper.beginTime}</td>
                                <td>${paper.endTime}</td>
                                <c:choose>
                                    <c:when test="${paper.end}">
                                        <td><a class="btn btn-danger" disabled>考试已结束</a></td>
                                    </c:when>

                                    <c:when test="${paper.start}">
                                        <td><a href="/student/${sessionScope.get("studentId")}/paper/${paper.id}" class="btn btn-success">进入考试</a></td>
                                    </c:when>

                                    <c:otherwise>
                                        <td><a id="doPaperBtn" class="btn btn-danger" disabled>等待考试</a></td>
                                    </c:otherwise>

                                </c:choose>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!-- /.box-body -->
            </div>

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">模拟考试</h3>
                    <div class="box-tools pull-right">
                        <%-- <a id="newBtn" class="btn btn-success btn-sm"><i class="fa fa-plus"></i> 新增试卷</a>
                         <a href="/paper/showPaperForm" class="btn btn-success btn-sm"><i class="fa fa-search"></i> 查看模版</a>--%>
                    </div>
                </div>
                <div class="box-body no-padding">
                    <table class="table table-hover">
                        <tbody>
                        <c:if test="${empty practicePaperList}">
                            <tr>
                                <td colspan="6">暂无考试</td>
                            </tr>
                        </c:if>
                        <c:if test="${not empty practicePaperList}">
                            <tr>
                                <th width="10"></th>
                                <th>试卷名称</th>
                            </tr>
                        </c:if>
                        <c:forEach items="${practicePaperList}" var="paper" varStatus="state">
                            <tr class="rowDetail" rel="${paper.id}">
                                <td>${state.count}</td>
                                <td>${paper.paperName}</td>
                                <td><a class="btn btn-success" id="doPaper" href="/student/doPaper/${paper.id}">进入考试</a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!-- /.box-body -->
            </div>


        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <%@include file="../include/footer.jsp"%>

</div>
<!-- ./wrapper -->

<%@include file="../include/js.jsp"%>
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/js/moment.js"></script>
<script src="/static/js/jquery.countdown.min.js"></script>
<script>
    $(function () {





    });
</script>
</body>
</html>


