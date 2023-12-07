<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">

            <div class="pull-left image">
                <img src="/static/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>${sessionScope.get("admin").name}</p>
                <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                <a href="/admin/home/${sessionScope.get("admin").id}" class="pull-right"><i class="fa fa-home"></i> <span>首页</span></a>
            </div>
        </div>

        <ul class="sidebar-menu" data-widget="tree">

            <li class="header">系统功能</li>
            <li class="treeview ${param.menu == 'announce'?'active':''}">
                <a href="/admin/announce">
                    <i class="fa fa-dashboard"></i> <span>公告管理</span>
                    <span class="pull-right-container">
                      <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-files-o"></i>
                    <span>系统日志</span>
                    <span class="pull-right-container">
              <span class="label label-primary pull-right">4</span>
            </span>
                </a>
            </li>



        </ul>
    </section>
    <!-- /.sidebar -->
</aside>