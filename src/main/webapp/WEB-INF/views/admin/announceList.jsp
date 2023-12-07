<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>OES | Announce_list</title>
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

    <%@include file="../admin/include/header.jsp"%>

    <!-- =============================================== -->

    <!-- 左侧菜单栏 -->
    <jsp:include page="../admin/include/sider.jsp">
        <jsp:param name="announce" value="announce"/>
    </jsp:include>



    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">公告管理</h3>
                    <div class="box-tools pull-right">
                        <a href="/admin/announce/new" class="btn btn-success btn-sm"><i class="fa fa-plus"></i> 发布公告</a>
                        <button id="delBtn" class="btn btn-danger btn-sm" disabled><i class="fa fa-dustbin"></i> 批量删除</button>
                    </div>
                </div>
                <div class="box-body no-padding">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <%--<th width="30"><input type="checkbox" id="ckFather"
                                <c:if test="${unreadNum == 0}"> style="display:none"</c:if>
                                ></th>--%>
                                <th width="30"><input type="checkbox" id="ckFather"></th>
                                <th>公告标题</th>
                                <th>发布人</th>
                                <th>身份</th>
                                <th>发布日期</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody>

                        <c:if test="${empty announceList}">
                            <tr>
                                <td colspan="6">暂无公告信息</td>
                            </tr>
                        </c:if>
                        <c:forEach items="${announceList}" var="announce">
                            <tr class="rowDetail" rel="${announce.id}">
                                <td><input value="${announce.id}" type="checkbox" class="ckSon"></td>
                                <td>${announce.title}</td>
                                <td>${announce.authorName}</td>
                                <td>
                                        ${announce.roleId == '1' ? '管理员' : ''}
                                        ${announce.roleId == '3' ? '教师' : ''}

                                </td>
                                <td><fmt:formatDate value="${announce.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                <%--<td>${announce.createTime}</td>--%>
                                <td>
                                    <a href="javascript:;" class="del" rel="${announce.id}">删除</a>
                                </td>
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

</div>
<!-- ./wrapper -->

<%@include file="../include/js.jsp"%>
<script src="/static/plugins/layer/layer.js"></script>
<script>
    $(function () {

        $("#ckFather").click(function() {
            var sons = $(".ckSon");
            for(var i = 0; i < sons.length; i++) {
                sons[i].checked = $(this)[0].checked;
            }
            if($(this)[0].checked == true) {
                $("#markBtn").removeAttr("disabled");
            } else {
                $("#markBtn").attr("disabled", "disabled");
            }
        });

        $(".ckSon").click(function() {
            var sons = $(".ckSon");
            var num = 0;
            for(var i = 0; i < sons.length; i++) {
                if(sons[i].checked == true) {
                    num++;
                }
            }
            if(num == sons.length) {
                $("#ckFather")[0].checked = true;
            } else {
                $("#ckFather")[0].checked = false;
            }
            if(num > 0) {
                $("#markBtn").removeAttr("disabled");
                $("#delBtn").removeAttr("disabled");
            } else {
                $("#markBtn").attr("disabled", "disabled");
                $("#delBtn").attr("disabled", "disabled");
            }
        });

        //删除一个公告
        $(".del").click(function() {
            var id = $(this).attr("rel");
            layer.confirm("确定要删除么？", function() {
                $.get("/admin/announce/del", {"id":id}).done(function(json) {
                    if(json.state == "success") {
                        layer.alert("删除成功", function() {
                            history.go(0);
                        });
                    } else {
                        layer.msg(json.message);
                    }
                }).error(function() {
                    layer.msg("系统异常...");
                });
            });
        });

        //批量删除公告
        $("#delBtn").click(function() {

            var sons = $(".ckSon");
            var num = 0;
            var ids = new Array();
            for(var i = 0; i < sons.length; i++) {
                if(sons[i].checked == true) {
                    ids[num] = sons[i].value;
                    num++;
                    console.log(ids);
                }
            }



            layer.confirm("确定要删除么？", function() {
                $.post("/admin/announce/del",{"ids":ids.serialize()}).done(function(json) {
                    if(json.state == "success") {
                        layer.alert("删除成功", function() {
                            history.go(0);
                        });
                    } else {
                        layer.msg(json.message);
                    }
                }).error(function() {
                    layer.msg("系统异常...");
                });
            });
        });

    });
</script>
</body>
</html>


