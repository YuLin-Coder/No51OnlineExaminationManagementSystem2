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
    <link rel="stylesheet" href="/static/plugins/uploader/webuploader.css">
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
        .webuploader-container {
            display: inline-block;
        }
        .webuploader-pick {
            padding: 5px 10px;
            overflow: visible;
            font-size: 12px;
            /*height: 30px;*/
            line-height:1.5;
            font-weight: 400;
            /*background-color:#0066FF;*/
        }
    </style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <!-- 顶部导航栏部分 -->
    <%@ include file="../teacher/include/header.jsp"%>

    <!-- =============================================== -->

    <!-- 左侧菜单栏 -->
    <jsp:include page="../teacher/include/sider.jsp">
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
                            <c:otherwise>
                                <div id="picker"><i class="fa fa-upload"></i> 上传文件</div>
                                <button id="newFolder" class="btn btn-success btn-sm"><i class="fa fa-plus"></i> 新建文件夹</button>
                            </c:otherwise>
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
                                        <ul class="dropdown-menu">
                                            <li><a href="">${disk.type == 'file'?'下载':'打开'}</a></li>
                                            <li><a href="#">重命名</a></li>
                                            <li><a href="#">删除</a></li>
                                        </ul>
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
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/plugins/moment/moment.js"></script>
<script src="/static/plugins/art-template/art-template.js"></script>
<script src="/static/plugins/uploader/webuploader.js"></script>

<script type="text/template" id="trTemplate">
    <tr class="tr rowDetail" rel="{{id}}">
        <td width="50" class="file_icon">
            <? if(type == 'file') {?>
                <i class="fa fa-file-o"></i>
            <?} else {?>
            <i class="fa fa-folder-o"></i>
            <?}?>
        </td>
        <td>{{name}}</td>
        <td>{{createTime}}</td>
        <td width="100"></td>
        <td width="150">
            <div class="btn-group">
                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    <i class="fa fa-ellipsis-h"></i>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="">下载</a></li>
                    <li><a href="#">重命名</a></li>
                    <li><a href="#">删除</a></li>
                </ul>
            </div>
        </td>
    </tr>
</script>

<script>
    $(function () {

        var teacherId = ${sessionScope.get("teacherId")}
        var pId = ${not empty requestScope.disk? requestScope.disk.id:'0'};

        $("#newFolder").click(function () {
            layer.prompt({title:"请输入文件夹名称"}, function (value, index) {
                layer.close(index);
                $.post("/disk/new", {"name":value,"pId":pId,"teacherId":teacherId}).done(function (json) {
                    if(json.state == "success") {
                        layer.msg("添加成功");
                        //动态渲染
                        $("#tBody").html("");
                        for(var i = 0; i < json.data.length; i++) {
                            var obj = json.data[i];
                            obj.createTime = moment(obj.createTime).format("MM月DD日");
                            var html = template("trTemplate", obj);
                            $("#tBody").append(html);
                        }
                    } else {
                        layer.msg(json.message);
                    }
                }).error(function () {
                    layer.msg("服务器异常");
                });
            });
        });

        //行点击 委托
        $(document).delegate(".rowDetail", "click", function () {
            var id = $(this).attr("rel");
            window.location.href = "/disk?_="+id;
        });

        var uploader = WebUploader.create({
            auto:true,
            swf:'/static/plugins/uploader/Uploader.swf',
            server:'/disk/upload',
            pick:'#picker',
            fileVal:'file',
            formData:{
                'pId':pId,
                'teacherId':teacherId
            }
        });

        var loadIndex = -1;

        uploader.on('uploadStart', function (file) {
            loadIndex = layer.load(2);
        });

        uploader.on('uploadSuccess', function (file,json) {
            if(json.state == 'success') {
                layer.msg("上传成功");
                //动态渲染
                $("#tBody").html("");
                for(var i = 0; i < json.data.length; i++) {
                    var obj = json.data[i];
                    obj.createTime = moment(obj.createTime).format("MM月DD日");
                    var html = template("trTemplate", obj);
                    $("#tBody").append(html);
                }
            }
        });

        uploader.on('uploadError', function (file) {
            layer.msg("上传失败,服务器异常");
        });

        uploader.on('uploadStart', function (file) {
            layer.close(loadIndex);
        });


    });
</script>
</body>
</html>

