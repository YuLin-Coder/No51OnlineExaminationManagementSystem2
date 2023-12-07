<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>OES | 编辑试题</title>

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
                    <h3 class="box-title">编辑试题</h3>
                    <div class="box-tools pull-right">
                        <a href="/question" class="btn btn-primary btn-sm"><i class="fa fa-arrow-left"></i> 返回列表</a>
                    </div>
                </div>
                <div class="box-body">
                    <form method="post" id="editForm">
                        <div class="form-group">
                            <label>试题名称</label>
                            <input type="hidden" class="form-control" name="teacherId" value="${sessionScope.get('teacherId')}">
                            <input type="text" class="form-control" name="questionName" value="${question.questionName}">
                        </div>

                        <div class="form-group">
                            <label>答案 <span style="color: #af0000">(单选题答案只填写单个大写字母,多选题答案用英文逗号隔开)</span></label>
                            <input type="text" name="answer" value="${fn:escapeXml(question.answer)}" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>答案解析 (选填)</label>
                            <input type="text" name="remark" value="${question.remark}" class="form-control">
                        </div>
                    </form>
                </div>
                <div class="box-footer">
                    <button class="btn btn-primary" id="editBtn">保存</button>
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

        $("#editBtn").click(function () {
            $("#editForm").submit();
        });

        $("#editForm").validate({
            errorClass:'text-danger',
            errorElement:'span',
            rules:{
                questionName:{
                    required:true
                },
                answer:{
                    required:true
                }
            },
            messages:{
                questionName:{
                    required:"请输入试题名称"
                },
                answer:{
                    required:"请输入试题答案"
                }
            }
        });




    });
</script>
</body>
</html>

