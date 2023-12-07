<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 左侧菜单栏 -->
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

        <!-- 搜索表单，不需要删除即可 -->
        <form action="/search" id="search" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="questionId"  class="form-control" placeholder="Search...">
                <span class="input-group-btn">
                  <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                  </button>
                </span>
            </div>
        </form>
        <!-- 菜单 -->
        <ul class="sidebar-menu">
            <li class="${param.menu == 'home' ? 'active' : ''}"><a href="/student/home/${sessionScope.get("studentId")}"><i class="fa fa-home"></i> <span>首页</span></a></li>
            <li class="header">系统功能</li>
            <!-- 消息公告 -->
            <%--<li class="treeview ${param.menu.startsWith('customer_')?'active':''}">--%>
            <li class="treeview ${fn:startsWith(param.menu,'announce')?'active':''}">
                <a href="/student/announce/system">
                    <i class="fa fa-address-book-o" ></i> <span>消息公告</span>
                    <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                </a>
            </li>
            <!-- 我的考试 -->
            <li class="treeview ${param.menu == 'exam'?'active':''}">
                <a href="/student/exam">
                    <i class="fa fa-bars"></i> <span>我的考试</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <%--<ul class="treeview-menu">--%>
                    <%--<li class="${param.menu == 'exam_my'?'active':''}"><a href="/student/exam"><i class="fa fa-circle-o"></i> 我的考试</a></li>--%>
                    <%--&lt;%&ndash;<li><a href="/recode/public"><i class="fa fa-circle-o"></i> 公共记录</a></li>&ndash;%&gt;--%>
                <%--</ul>--%>
            </li>
            <!-- 我的成绩 -->
            <li class="treeview ${fn:startsWith(param.menu,'score')?'active':''}">
                <a href="/student/score/${sessionScope.get("studentId")}">
                    <i class="fa fa-calendar"></i> <span>我的成绩</span>
                    <span class="pull-right-container">
                    <i class="fa fa-angle-left pull-right"></i></span>
                </a>
            </li>
            <!-- 成绩分析 -->
            <li class="treeview ${fn:startsWith(param.menu,'chart_')?'active':''}">
                <a href="">
                    <i class="fa fa-window-restore"></i> <span>成绩分析</span>
                    <span class="pull-right-container">
                    <i class="fa fa-angle-left pull-right"></i></span>
                </a>
                <ul class="treeview-menu">
                <li class="${param.menu == 'chart_demo'?'active':''}"><a href="/student/chart"><i class="fa fa-circle-o"></i> 分析示例</a></li>
                <li class="${param.menu == 'chart_my'?'active':''}"><a href="/score/student/chart"><i class="fa fa-circle-o"></i> 我的分析</a></li>
                </ul>
            </li>
            <!-- 系统网盘 -->
            <li class="treeview ${param.menu == 'disk'?'active':''}" >
                <a href="/student/disk">
                    <i class="fa fa-cloud"></i> <span>系统网盘</span>
                    <span class="pull-right-container">
                    <i class="fa fa-angle-left pull-right"></i></span>
                </a>
            </li>

            <li class=" ${fn:startsWith(param.menu,'help')?'active':''}">
                <a href="/student/help"><i class="fa fa-share-alt"></i> <span>帮助中心</span></a>
            </li>

        </ul>
    </section>
    <!-- /.sidebar -->
</aside>

