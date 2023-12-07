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
    <link rel="stylesheet" href="/static/plugins/datetimepicker/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="/static/plugins/datepicker/datepicker3.css">

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
                                <td colspan="6">暂无需要管理的考试</td>
                            </tr>
                        </c:if>
                        <c:if test="${not empty paperList}">
                            <tr>
                                <th width="10"></th>
                                <th>试卷名称</th>
                                <th>开考时间</th>
                                <th>结束时间</th>
                                <th>操作</th>
                            </tr>
                        </c:if>
                        <c:forEach items="${paperList}" var="paper" varStatus="state">
                            <tr class="rowDetail" rel="${paper.id}">
                                <td>${state.count}</td>
                                <td>${paper.paperName}</td>
                                <td>${paper.beginTime}</td>
                                <td>${paper.endTime}</td>
                                <td><a class="rowDel" rel="${paper.id}">修改时间</a> </td>
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

    <!-- 修改时间模态框 -->
    <div class="modal fade" id="modifyModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">修改时间</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group form_datetime">
                        <label>开始时间</label>
                        <input type="text" name="beginTime" class="form-control" id="datepicker" >
                    </div>
                    <div class="form-group form_datetime">
                        <label>结束时间</label>
                        <input type="text" name="endTime" class="form-control" id="datepicker2" >
                    </div>
                    <div class="form-group form_datetime">
                        <label>考试时长 (示例:120分钟)</label>
                        <input type="text" name="allowTime" id="allowTime" class="form-control" >
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
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/js/moment.js"></script>
<script src="/static/js/jquery.countdown.min.js"></script>
<script src="/static/plugins/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="/static/plugins/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="/static/plugins/moment/moment.js"></script>
<script src="/static/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="/static/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="/static/bootstrap/js/jquery.validate.js"></script>
<script>
    $(function () {

        var date = new Date();
        var picker = $("#datepicker").datetimepicker({
            format:"yyyy-mm-dd hh:ii",
            language:"zh-CN",
            autoclose:true,
            todayHighlight:true,
            startDate:date
        });
        picker.on("changeDate", function (e) {
            var time = $("#datepicker").val();
            var day = time.split(" ")[0];
            $('#datepicker2').datetimepicker('setStartDate', day);
        });

        var timepicker = $('#datepicker2').datetimepicker({
            format:"yyyy-mm-dd hh:ii",
            language:"zh-CN",
            autoclose:true,
            todayHighlight:true
        });


        //修改考试时间
        $(".rowDel").click(function () {

            $("#modifyModal").modal({
                show : true,
                backdrop : 'static'
            });

            var id = $(this).attr("rel");
            $("#saveBtn").click(function () {

                layer.confirm("确定修改考试时间么?", function () {
                    var begin = $("#datepicker").val();
                    var end = $("#datepicker2").val();
                    var allow = $("#allowTime").val();

                    $.post("/teacher/editPaper/"+id, {"beginTime":begin, "endTime":end, "allowTime":allow}).done(function (data) {
                        if(data.state == "success") {
                            layer.msg("修改成功!");
                            window.location.href = "/teacher/exam";

                        }
                    }).error(function () {
                        layer.msg("服务器异常");
                    });
                });
            });
        });





    });
</script>
</body>
</html>


