<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>OES | Paper_show</title>

    <!-- css style -->
    <%@ include file="../include/css.jsp"%>
    <style>
        th {
            font-size: 18px;
        }
        td {
            font-size: 17px;
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
        <jsp:param name="menu" value="paper"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">试卷详情</h3>
                    <div class="box-tools">
                        <a href="/paper" class="btn btn-primary btn-sm"><i class="fa fa-arrow-left"></i> 返回列表</a>

                            <button id="delBtn" class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i> 删除</button>
                    </div>
                </div>
                <div class="box-body ">

                    <table class="table">
                        <tr>
                            <th class="td_title" style="background-color: #e4e4e4">试卷名称:  ${paper.paperName}</th>

                        </tr>
                        <tr>
                            <td class="td_title">试卷题目id: ${paper.questionId}</td>
                        </tr>
                        <tr>
                            <td class="td_title">开始时间: ${paper.beginTime}</td>
                        </tr>
                        <tr>
                            <td class="td_title">结束时间: ${paper.endTime}</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td class="td_title">考试时长: ${paper.allowTime}</td>
                        </tr>
                        <tr>
                            <td class="td_title">试卷总分: ${paper.score}</td>
                        </tr>
                        <tr>
                            <td class="td_title">试卷状态:
                                <c:choose>
                                    <c:when test="${paper.end}">已结束</c:when>
                                    <c:when test="${paper.start}">考试中</c:when>
                                    <c:otherwise>未开始</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td class="td_title">试卷类型: ${paper.paperType}</td>
                        </tr>
                        <tr>
                            <td class="td_title">考试课程: ${course.courseName}</td>
                        </tr>
                        <tr>
                            <td class="td_title">考试班级: ${major.major}</td>
                        </tr>

                    </table>
                </div>
                <%--<div class="box-footer">
                    <span style="color: #ccc" class="pull-right">
                        创建日期：<span title="<fmt:formatDate value="${customer.createTime}"/>"><fmt:formatDate value="${customer.createTime}" pattern="MM月dd日"/></span> &nbsp;&nbsp;&nbsp;&nbsp;
                        最后修改日期：<span title="<fmt:formatDate value="${customer.updateTime}"/>"><fmt:formatDate value="${customer.updateTime}" pattern="MM月dd日"/></span>
                    </span>
                </div>--%>
            </div>



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
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/js/moment.js"></script>
<script src="/static/js/jquery.countdown.min.js"></script>

<script>

    $(function () {
        var paperId = ${paper.id};

        //删除试卷
        $("#delBtn").click(function () {
            layer.confirm("确定要删除改试卷么？",function () {
                window.location.href = "/paper/delete/" + paperId;
            })
        });


    });
</script>
</body>
</html>

