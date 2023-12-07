<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>OES | PaperForm_new</title>

    <!-- css style -->
    <%@ include file="../include/css.jsp"%>



</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <!-- 顶部导航栏部分 -->
    <%@ include file="../teacher/include/header.jsp"%>

    <!-- =============================================== -->

    <!-- 左侧菜单栏 -->
    <jsp:include page="../teacher/include/sider.jsp">
        <jsp:param name="menu" value="paper"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">新增试卷模版</h3>
                    <div class="box-tools pull-right">
                        <a href="/paper" class="btn btn-primary btn-sm"><i class="fa fa-arrow-left"></i> 返回列表</a>
                    </div>
                </div>
                <div class="box-body">
                    <form method="post" id="addForm" class="form-horizontal">

                        <div class="form-group">
                            <label class="col-sm-2 control-label">单选题</label>
                            <label class="col-sm-2 control-label">数量</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" name="qChoiceNum">
                                <input type="hidden" class="form-control" name="teacherId" value="${sessionScope.get('teacherId')}">
                            </div>
                            <label class="col-sm-2 control-label">每题分数</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" name="qChoiceScore">
                            </div>

                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">多选题</label>
                            <label class="col-sm-2 control-label">数量</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" name="qMulChoiceNum">
                            </div>
                            <label class="col-sm-2 control-label">每题分数</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" name="qMulChoiceScore">
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="col-sm-2 control-label">判断题</label>
                            <label class="col-sm-2 control-label">数量</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" name="qTofNum">
                            </div>
                            <label class="col-sm-2 control-label">每题分数</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" name="qTofScore">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">填空题</label>
                            <label class="col-sm-2 control-label">数量</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" name="qFillNum">
                            </div>
                            <label class="col-sm-2 control-label">每题分数</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" name="qFillScore">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">简答题</label>
                            <label class="col-sm-2 control-label">数量</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" name="qSaqNum">
                            </div>
                            <label class="col-sm-2 control-label">每题分数</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" name="qSaqScore">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">编程题</label>
                            <label class="col-sm-2 control-label">数量</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" name="qProgramNum">
                            </div>
                            <label class="col-sm-2 control-label">每题分数</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" name="qProgramScore">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="box-footer">
                    <button class="btn btn-primary" id="addBtn">保存</button>
                    <a href="javascript:history.go(0)" class="btn btn-danger" id="reset">重置</a>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
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
<script src="/static/bootstrap/js/jquery.validate.js"></script>
<script>

    $(function () {

        $("#addBtn").click(function () {
            $("#addForm").submit();
        });






    });
</script>
</body>
</html>

