<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>OES | Disk</title>
    <!-- css style -->
    <%@ include file="../include/css.jsp"%>

    <style>
        tr{
            height: 50px;
            line-height: 50px;
        }
        .table>tbody>tr>td{
            vertical-align: middle;
        }
        .file_icon {
            font-size: 30px;
        }
        .table>tbody>tr:hover{
            cursor: pointer;
        }
    </style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <!-- 顶部导航栏部分 -->
    <%@ include file="../student/include/header.jsp"%>

    <!-- =============================================== -->

    <!-- 左侧菜单栏 -->
    <jsp:include page="../student/include/sider.jsp">
        <jsp:param name="menu" value="disk"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">系统网盘</h3>

                    <div class="box-tools pull-right">
                        <c:if test="${not empty disk}">
                            <a href="javascript:history.back()"  class="btn btn-gray btn-sm"><i class="fa fa-arrow-left"></i> 返回上级</a>
                        </c:if>
                        <c:choose>
                            <c:when test="${disk.type == 'file'}">
                                <c:if test="${disk.name.endsWith('.txt') or disk.name.endsWith('.pdf') or disk.name.endsWith('.jpg') or disk.name.endsWith('.png') or disk.name.endsWith('.bmp') or disk.name.endsWith('.gif')}">
                                    <a href="/disk/download?_=${disk.id}" target="_blank" class="btn btn-sm btn-info"><i class="fa fa-search"></i> 在线预览</a>
                                </c:if>
                                <a href="/disk/download?_=${disk.id}&fileName=${disk.name}" class="btn btn-sm btn-danger"><i class="fa fa-download"></i> 下载</a>
                            </c:when>
                        </c:choose>

                    </div>
                </div>
                <div class="box-body no-padding">
                    <table class="table table-hover">
                        <tbody id="tBody">
                        <c:if test="${disk.type != 'file' and empty diskList}">
                            <tr><td colspan="4">暂无内容</td></tr>
                        </c:if>
                            <c:forEach items="${diskList}" var="disk">
                            <tr class="tr rowDetail" rel="${disk.id}">
                                <td width="50" class="file_icon">
                                    <c:choose>
                                        <c:when test="${disk.name.endsWith('pdf')}">
                                            <i class="fa fa-file-pdf-o"></i>
                                        </c:when>
                                        <c:when test="${disk.name.endsWith('jpg') or disk.name.endsWith('png') or disk.name.endsWith('gif') }">
                                            <i class="fa fa-file-image-o"></i>
                                        </c:when>
                                        <c:when test="${disk.type == 'file'}">
                                            <i class="fa fa-file-text-o"></i>
                                        </c:when>
                                        <c:otherwise>
                                            <i class="fa fa-folder-open-o"></i>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${disk.name}</td>
                                <td><fmt:formatDate value="${disk.createTime}" pattern="MM月dd日"/></td>
                                <td width="100">
                                    <c:if test="${disk.type == 'file'}">
                                        ${disk.fileSize}
                                    </c:if>
                                </td>
                                <td width="150">
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                            <i class="fa fa-ellipsis-h"></i>
                                        </button>
                                    </div>
                                </td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>

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

<%@ include file="../include/js.jsp"%>

<script>
    $(function () {
        //行点击 委托
        $(document).delegate(".rowDetail", "click", function () {
            var id = $(this).attr("rel");
            window.location.href = "/disk?_="+id;
        });
    });
</script>
</body>
</html>

