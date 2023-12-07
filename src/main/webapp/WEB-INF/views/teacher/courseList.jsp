<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>OES | Course_list</title>
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
            font-size: 15px;
        }
        td {
            font-size: 16px;
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
        <jsp:param name="menu" value="course"/>
    </jsp:include>



    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">课程管理</h3>
                    <div class="box-tools pull-right">
                        <a id="addBtn" class="btn btn-success btn-sm"><i class="fa fa-plus"></i> 新增课程</a>
                    </div>
                </div>
                <div class="box-body no-padding">
                    <table class="table table-hover">
                        <tbody>
                        <tr>
                            <th width="10"></th>
                            <th>课程编号</th>
                            <th>课程名称</th>
                            <th>操作</th>
                        </tr>
                        <c:if test="${empty courseList}">
                            <tr>
                                <td colspan="6">老师您还未添加课程</td>
                            </tr>
                        </c:if>
                        <c:forEach items="${courseList}" var="course" varStatus="vs">
                            <tr class="rowDetail" rel="${course.id}">
                                <td></td>
                                <td>&nbsp; &nbsp; &nbsp; ${vs.count}</td>
                                <td>${course.courseName}</td>
                                <td><a class="rowDel" rel="${course.id}">删除</a>  </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
            <ul id="pagination-demo" class="pagination-sm pull-right"></ul>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <%@include file="../include/footer.jsp"%>

    <!-- 新增课程模态框 -->
    <div class="modal fade" id="newCourse">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">新增课程</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">课程名称</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="courseName" id="courseName">
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="saveBtn">确定</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

</div>
<!-- ./wrapper -->

<%@include file="../include/js.jsp"%>
<script src="/static/bootstrap/js/jquery.validate.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script>
    $(function () {

        $(".rowDel").click(function () {
            var id = $(this).attr("rel");
            layer.confirm("确定删除该课程么?", function () {
                $.get("/teacher/course/del/" + id).done(function (data) {
                    if(data.state == "success") {
                        layer.msg("删除成功!");
                        window.location.href = "/teacher/course/list";
                    } else {
                        layer.msg(data.messge);
                    }
                }).error(function () {
                    layer.msg("服务器异常");
                });
            });
        });

        //添加课程
        $("#addBtn").click(function () {
            $("#newCourse").modal({
                show : true,
                backdrop : 'static'
            });
        });

        $("#saveBtn").click(function () {

            var courseName = $("#courseName").val();

            layer.confirm("确定添加该课程?", function () {
                var teacherId = ${sessionScope.get('teacherId')};
                $.get("/teacher/course/new", {"courseName": courseName, "teacherId": teacherId}).done(function (data) {
                    if(data.state == "success") {
                        layer.msg("添加成功!");
                        window.location.href = "/teacher/course/list";
                    }
                }).error(function () {
                    layer.msg("服务器异常");
                });
            });
        });


    });
</script>
</body>
</html>


