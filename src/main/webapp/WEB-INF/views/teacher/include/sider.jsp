<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 左侧菜单栏 -->
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

        <!-- 搜索表单，不需要删除即可 -->
        <form action="#" method="get" class="sidebar-form">
          <div class="input-group">
            <input type="text" name="q" class="form-control" placeholder="Search...">
                <span class="input-group-btn">
                  <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                  </button>
                </span>
          </div>
        </form>
        <!-- /.search form -->
        <!-- 菜单 -->
        <ul class="sidebar-menu">
            <li class="${param.menu == 'home' ? 'active' : ''}"><a href="/teacher/home/${sessionScope.get("teacher").id}"><i class="fa fa-home"></i> <span>首页</span></a></li>
            <li class="header">系统功能</li>
            <!-- 消息公告 -->
            <%--<li class="treeview ${param.menu.startsWith('customer_')?'active':''}">--%>
            <li class="treeview ${param.menu == 'announce' ? 'active' : ''}">
                <a href="/teacher/announce/system">
                    <i class="fa fa-address-book-o" ></i> <span>消息公告</span>
                    <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                </a>
            </li>
            <!-- 课程管理 -->
            <li class="treeview ${param.menu == 'course'?'active':''}">
                <a href="/teacher/course/list">
                    <i class="fa fa-users"></i> <span>课程管理</span>
                    <span class="pull-right-container">
                    <i class="fa fa-angle-left pull-right"></i></span>
                </a>
            </li>
            <!-- 试题管理 -->
            <li class="treeview ${param.menu == 'question'?'active':''}">
                <a href="/question">
                    <i class="fa fa-bars"></i> <span>试题管理</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
            </li>
            <!-- 试卷管理 -->
            <li class="treeview ${param.menu == 'paper'?'active':''}">
                <a href="/paper">
                    <i class="fa fa-book"></i> <span>试卷管理</span>
                    <span class="pull-right-container">
                    <i class="fa fa-angle-left pull-right"></i></span>
                </a>
            </li>
            <!-- 考试管理 -->
            <li class="treeview ${param.menu == 'exam'?'active':''}">
                <a href="/teacher/exam">
                    <i class="fa fa-paper-plane"></i> <span>考试管理</span>
                    <span class="pull-right-container">
                    <i class="fa fa-angle-left pull-right"></i></span>
                </a>
            </li>

            <!-- 试卷复查 -->
            <li class="treeview ${param.menu == 'review'?'active':''}">
                <a href="/teacher/reviewPaper">
                    <i class="fa fa-calendar"></i> <span>试卷复查</span>
                    <span class="pull-right-container">
                    <i class="fa fa-angle-left pull-right"></i></span>
                </a>
            </li>
            <!-- 成绩统计 -->
            <li class="treeview ${fn:startsWith(param.menu,'score')?'active':''}">
                <a href="#">
                    <i class="fa fa-pie-chart"></i> <span>成绩统计</span>
                    <span class="pull-right-container">
                    <i class="fa fa-angle-left pull-right"></i></span>
                </a>
                <ul class="treeview-menu">
                <li class="${param.menu == 'score_course'?'active':''}"><a href="/score/course"><i class="fa fa-circle-o"></i> 特定课程</a></li>
                <li class="${param.menu == 'score_major'?'active':''}"><a href="/score/major"><i class="fa fa-circle-o"></i> 特定班级</a></li>
                </ul>
            </li>
            <!-- 系统网盘 -->
            <li class="treeview ${param.menu == 'disk'?'active':''}">
                <a href="/disk">
                    <i class="fa fa-cloud"></i> <span>系统网盘</span>
                    <span class="pull-right-container">
                    <i class="fa fa-angle-left pull-right"></i></span>
                </a>
            </li>

            <li class=" ${fn:startsWith(param.menu,'help')?'active':''}">
                <a href="/teacher/help"><i class="fa fa-share-alt"></i> <span>帮助中心</span></a>
            </li>
                <!--<li><a href="#"><i class="fa fa-circle-o text-yellow"></i> <span>Warning</span></a></li>
                <li><a href="#"><i class="fa fa-circle-o text-aqua"></i> <span>Information</span></a></li>-->
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>
