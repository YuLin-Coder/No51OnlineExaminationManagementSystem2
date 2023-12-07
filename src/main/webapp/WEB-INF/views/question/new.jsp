<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>OES | 新增试题</title>

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
                    <h3 class="box-title">新增试题</h3>
                    <div class="box-tools pull-right">
                        <a href="/question" class="btn btn-primary btn-sm"><i class="fa fa-arrow-left"></i> 返回列表</a>
                    </div>
                </div>
                <div class="box-body">
                    <form method="post" id="addForm">
                        <div class="form-group">
                            <label>试题名称</label>
                            <input type="hidden" class="form-control" name="teacherId" value="${sessionScope.get('teacherId')}">
                            <input type="text" class="form-control" name="questionName">
                        </div>
                        <div class="form-group">
                            <label>题目类型</label>
                            <select class="form-control" name="typeId" >
                                <option value=""></option>
                                <c:forEach items="${typeList}" var="type">
                                    <option value="${type.id}">${type.typeName}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <hr/>
                            <div class="form-group">
                                <label style="color: #af0000">注意:  选项A、B、C、D内容非选择题无需填写</label>
                            </div>
                            <div class="form-group">
                                <label>选项A</label>
                                <input type="text" name="optionA" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>选项B</label>
                                <input type="text" name="optionB" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>选项C</label>
                                <input type="text" name="optionC" class="form-control">
                            </div>

                            <div class="form-group">
                                <label>选项D</label>
                                <input type="text" name="optionD" class="form-control">
                            </div>
                        <hr/>

                        <div class="form-group">
                            <label>答案 <span style="color: #af0000">(单选题答案只填写单个大写字母,多选题答案用英文逗号隔开)</span></label>
                            <input type="text" name="answer" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>所属课程</label>
                            <select class="form-control" name="courseId">
                                <option value=""></option>
                                <c:forEach items="${courseList}" var="course">
                                    <option value="${course.id}">${course.courseName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>难易程度</label>
                            <select class="form-control" name="difficulty">
                                <option value=""></option>
                                <option value="★">★</option>
                                <option value="★★">★★</option>
                                <option value="★★★">★★★</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>答案解析 (选填)</label>
                            <input type="text" name="remark" class="form-control">
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
                questionName:{
                    required:true
                },
                typeId:{
                    required:true
                },
                answer:{
                    required:true
                },
                courseId:{
                    required:true
                },
                difficulty:{
                    required:true
                }
            },
            messages:{
                questionName:{
                    required:"请输入试题名称"
                },
                typeId:{
                    required:"请选择题目类型"
                },
                answer:{
                    required:"请输入试题答案"
                },
                courseId:{
                    required:"请选择所属课程"
                },
                difficulty:{
                    required:"请选择难易程度"
                }
            }
        });




    });
</script>
</body>
</html>

