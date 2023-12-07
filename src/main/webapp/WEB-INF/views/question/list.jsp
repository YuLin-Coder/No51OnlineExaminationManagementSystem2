<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>OES | Question_list</title>
    <!-- Tell the browser to be responsive to screen width -->
    <%@include file="../include/css.jsp"%>

    <style>

        .table>tbody>tr:hover {
            cursor: pointer;
        }
        .table>tbody>tr>td {
            vertical-align: middle;
        }
        .star {
            font-size: 20px;
            color: #ff7400;
        }
        th {
            font-size: 14px;
        }
        td {
            font-size: 15px;
        }

    </style>


</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../teacher/include/header.jsp"%>

    <!-- =============================================== -->

    <!-- 左侧菜单栏 -->
    <jsp:include page="../teacher/include/sider.jsp">
        <jsp:param name="menu" value="question"/>
    </jsp:include>



    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">试题管理</h3>
                    <div class="box-tools pull-right">
                        <a href="/question/new" class="btn btn-success btn-sm"><i class="fa fa-plus"></i> 新增试题</a>
                    </div>

                </div>
                <div class="box-body no-padding">
                    <table class="table table-hover">
                        <tbody>
                        <tr>
                            <th width="10"></th>
                            <th>题目编号</th>
                            <th>题目名称</th>
                            <th>题目类型</th>
                            <th>难易程度</th>
                        </tr>
                        <c:if test="${empty page.list}">
                            <tr>
                                <td colspan="6">题库中还未添加题目</td>
                            </tr>
                        </c:if>
                        <c:forEach items="${page.list}" var="question">
                            <tr class="rowDetail" rel="${question.id}">
                                <td></td>
                                <td>&nbsp; &nbsp; &nbsp; ${question.id}</td>
                                <td>${question.questionName}</td>

                                <td>${question.typeId == '1' ? '单选题' : ''}
                                        ${question.typeId == '2' ? '多选题' : ''}
                                        ${question.typeId == '3' ? '填空题' : ''}
                                        ${question.typeId == '4' ? '判断题' : ''}
                                        ${question.typeId == '5' ? '简答题' : ''}
                                        ${question.typeId == '6' ? '编程题' : ''}
                                </td>
                                <td class="star">${question.difficulty}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
            <ul id="pagination-demo" class="pagination pull-right"></ul>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <%@include file="../include/footer.jsp"%>

</div>
<!-- ./wrapper -->

<%@include file="../include/js.jsp"%>
<script src="/static/bootstrap/js/jquery.twbsPagination.min.js"></script>
<script>
    $(function () {
        $(".rowDetail").click(function () {
            var id = $(this).attr("rel");
            window.location.href = "/question/show/"+id;
        });

        //分页
        $('#pagination-demo').twbsPagination({
            totalPages: "${page.pages}",
            visiblePages:5,
            first:'首页',
            last:'末页',
            prev:'上一页',
            next:'下一页',
            href:"?p={{number}}"
        });

    });
</script>
</body>
</html>


