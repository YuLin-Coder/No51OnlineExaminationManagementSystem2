<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>OES | Announce_new</title>

    <!-- css style -->
    <%@ include file="../include/css.jsp"%>



</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <!-- 顶部导航栏部分 -->
    <%@ include file="../admin/include/header.jsp"%>

    <!-- =============================================== -->

    <!-- 左侧菜单栏 -->
    <jsp:include page="../admin/include/sider.jsp">
        <jsp:param name="menu" value="announce"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">新增公告</h3>
                    <div class="box-tools pull-right">
                        <a href="/admin/home/${sessionScope.get("admin").id}" class="btn btn-primary btn-sm"><i class="fa fa-arrow-left"></i> 返回列表</a>
                    </div>
                </div>
                <div class="box-body">
                    <form method="post" id="addForm">
                        <div class="form-group">
                            <label>公告标题</label>
                            <input type="hidden" class="form-control" name="adminId" value="${sessionScope.get('admin').id}">
                            <input type="hidden" class="form-control" name="roleId" value="${sessionScope.get('roleId')}">
                            <input type="text" class="form-control" name="title">
                        </div>

                        <div class="form-group">
                            <label>公告内容</label>
                            <input type="text" name="content" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>发布人</label>
                            <input type="text" name="authorName" class="form-control">
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

        $("#addForm").validate({
            errorClass:'text-danger',
            errorElement:'span',
            rules:{
                title:{
                    required:true
                },
                content:{
                    required:true
                },
                authorName:{
                    required:true
                }
            },
            messages:{
                title:{
                    required:"请输入标题"
                },
                content:{
                    required:"请输入内容"
                },
                authorName:{
                    required:"请输入发布人姓名"
                }
            }
        });




    });
</script>
</body>
</html>

