<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>CRM-修改密码</title>
    <!-- css style -->
    <%@ include file="../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <!-- 顶部导航栏部分 -->
    <%@ include file="../student/include/header.jsp"%>

    <!-- =============================================== -->

    <!-- 左侧菜单栏 -->
    <jsp:include page="../student/include/sider.jsp">
        <jsp:param name="menu" value="info"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">修改密码</h3>
                </div>
                <div class="box-body">
                    <form method="post" id="change">
                        <div class="form-group">
                            <label>原始密码</label>
                            <input type="password" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>新密码</label>
                            <input type="password" id="password" name="password" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>确认密码</label>
                            <input type="password" name="confirm_password" class="form-control">
                        </div>

                    </form>
                </div>
                <div class="box-footer">
                    <button id="editBtn" class="btn btn-primary">保存</button>
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
            $("#change").submit();
        });

        //jqValidate验证
        $("#change").validate({
            errorClass : "text-danger",
            errorElement : "span",
            rules : {
                password: {
                    required: true
                },
                confirm_password: {
                    required: true,
                    equalTo: "#password"
                }
            },
            messages : {
                password: {
                    required: "请输入密码",
                },
                confirm_password: {
                    required: "请再次输入密码",
                    equalTo: "两次密码输入不一致"
                }
            }
        });
    });
</script>
</body>
</html>
