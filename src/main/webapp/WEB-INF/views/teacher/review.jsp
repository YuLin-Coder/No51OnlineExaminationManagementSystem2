<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>OES | Paper_review</title>
    <%@ include file="../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">
    <%@include file="../teacher/include/header.jsp"%>
    <!-- =============================================== -->

    <jsp:include page="../teacher/include/sider.jsp">
        <jsp:param name="menu" value="review"/>
    </jsp:include>
    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <form method="get" action="/teacher/reviewRes" style="z-index: 999; position: relative" >
                <div class="form-group col-sm-12">
                    <label class="control-label col-sm-2"><h4 style="font-weight: 800">请输入学生学号</h4></label>
                    <div class="col-sm-8">
                        <input type="text" name="stuNumber" value="311409030311" class="form-control">
                    </div>
                </div>

                <div class="form-group col-sm-12">
                    <label class="control-label col-sm-2"><h4 style="font-weight: 800">请选择试卷名称</h4></label>
                    <div class="col-sm-8">
                        <select class="form-control " name="paperId" >
                            <option value=""></option>
                            <c:forEach items="${paperList}" var="paper">
                                <option value="${paper.id}">${paper.paperName}</option>
                            </c:forEach>
                        </select>
                    </div>
                        <button class="btn btn-success pull-right">查找</button>
                </div>

            </form>


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

</script>
</body>
</html>