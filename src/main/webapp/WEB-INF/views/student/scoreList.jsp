<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>OES | Score_list</title>
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
        <jsp:param name="menu" value="score"/>
    </jsp:include>



    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">成绩列表</h3>
                    <div class="box-tools pull-right">
                       <%-- <a id="newBtn" class="btn btn-success btn-sm"><i class="fa fa-plus"></i> 新增试卷</a>
                        <a href="/paper/showPaperForm" class="btn btn-success btn-sm"><i class="fa fa-search"></i> 查看模版</a>--%>
                    </div>
                </div>
                <div class="box-body no-padding">
                    <table class="table table-hover">
                        <tbody>
                        <c:if test="${empty scoreList}">
                            <tr>
                                <td colspan="6">暂无成绩</td>
                            </tr>
                        </c:if>
                        <c:if test="${not empty scoreList}">
                            <tr>
                                <th width="10"></th>
                                <th>试卷名称</th>
                                <th>考试分数</th>
                                <th>错题ID集合</th>
                            </tr>
                        </c:if>
                        <c:forEach items="${scoreList}" var="score" varStatus="state">
                            <tr class="rowDetail" rel="${score.id}">
                                <td>${state.count}</td>
                                <td>${score.paperName}</td>
                                <td>&nbsp; &nbsp; &nbsp; ${score.score}</td>
                                <td>&nbsp; &nbsp;  ${score.wrongIds}</td>
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


