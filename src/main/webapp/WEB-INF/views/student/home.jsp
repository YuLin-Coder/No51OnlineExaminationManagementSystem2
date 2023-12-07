<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>OES | Student_Home</title>
    <!-- Tell the browser to be responsive to screen width -->
    <%@include file="../include/css.jsp"%>
    <style>
        p
        {
            text-indent:30px;
        }
    </style>


</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="include/header.jsp"%>

    <!-- =============================================== -->

    <!-- 左侧菜单栏 -->
    <jsp:include page="include/sider.jsp">
        <jsp:param name="menu" value="home"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1 style="font-family: 'Microsoft Yahei', Arial, Helvetica, sans-serif;text-align:center; font-size: 30px" >
                欢迎使用OES在线考试系统！
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
            </ol>
        </section>



        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title lead" style="font-size: 22px;font-weight: 800">诚信考试倡议书</h3>

                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip" title="Collapse">
                            <i class="fa fa-minus"></i></button>
                        <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove">
                            <i class="fa fa-times"></i></button>
                    </div>
                </div>

                <div class="box-body" style="font-size: 20px">


                    <p>孔子曰：“君子以诚为贵”。诚信是中华民族的优良传统，也是每一个公民的基本道德要求，我们应以此作为人生的信条、处事的准则。</p>

                    <p>即使是在线考试我们也应该自觉遵守考试纪律，营造文明、诚信、健康的考风，在此，我们向全校同学发出倡议：</p>

                    <p>一、认真复习，积极备考</p>

                    <p>二、文明考风，诚信应考</p>

                    <p>三、遵守纪律， 共创和谐</p>

                    <P>四、慎思明辨，防微杜渐</P>

                </div>
                <!-- /.box-body -->
                <div class="box-footer ">
                    <blockquote class="blockquote-reverse">
                        <p style="color: green">祝您开启愉快的一天！</p>

                            <a class="close" aria-hidden="true">
                                ${message}
                            </a>


                    </blockquote>

                </div>
                <!-- /.box-footer-->
            </div>
            <!-- /.box -->

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <%@include file="../include/footer.jsp"%>

</div>
<!-- ./wrapper -->

<%@include file="../include/js.jsp"%>
</body>
</html>

