<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 左侧菜单栏 -->
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

        <!-- 搜索表单，不需要删除即可 -->
        <!--<form action="#" method="get" class="sidebar-form">
          <div class="input-group">
            <input type="text" name="q" class="form-control" placeholder="Search...">
                <span class="input-group-btn">
                  <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                  </button>
                </span>
          </div>
        </form>-->
        <!-- /.search form -->
        <!-- 菜单 -->
        <ul class="sidebar-menu">
            <li class="${param.menu == 'home' ? 'active' : ''}"><a href="/student/home/${student.id}"><i class="fa fa-home"></i> <span>首页</span></a></li>
            <li class="header">系统功能</li>
            <!-- 消息公告 -->
            <%--<li class="treeview ${param.menu.startsWith('customer_')?'active':''}">--%>
            <li class="treeview ${fn:startsWith(param.menu,'notice')?'active':''}">
                <a href="#">
                    <i class="fa fa-address-book-o" ></i> <span>消息公告</span>
                    <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                </a>
                <ul class="treeview-menu">
                    <li class="${param.menu == 'notice_my'?'active':''}"><a href="/notice/my"><i class="fa fa-circle-o"></i> 考试通知</a></li>
                    <li class="${param.menu == 'notice_system'?'active':''}"><a href="/notice/system"><i class="fa fa-circle-o"></i> 系统公告</a></li>
                </ul>
            </li>
            <!-- 我的考试 -->
            <li class="treeview ${param.menu == 'exam_my'?'active':''}">
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
                <a href="/student/score">
                    <i class="fa fa-calendar"></i> <span>我的成绩</span>
                    <span class="pull-right-container">
                    <i class="fa fa-angle-left pull-right"></i></span>
                </a>
                <%--<ul class="treeview-menu">--%>
                    <%--<li class="${param.menu == 'task_todo'?'active':''}"><a href="/task/todo"><i class="fa fa-circle-o"></i> 待办列表</a></li>--%>
                    <%--<li class="${param.menu == 'task_over'?'active':''}"><a href="/task/over"><i class="fa fa-circle-o"></i> 逾期事项</a></li>--%>
                <%--</ul>--%>
            </li>
            <!-- 成绩分析 -->
            <li class="treeview ${fn:startsWith(param.menu,'info')?'active':''}">
                <a href="/student/info">
                    <i class="fa fa-pie-chart"></i> <span>个人信息</span>
                    <span class="pull-right-container">
                      <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <%--<ul class="treeview-menu">--%>
                    <%--<li class="${param.menu == 'score_'?'active':''}"><a href="/charts"><i class="fa fa-circle-o"></i> 客户统计</a></li>--%>
                    <%--<li class="${param.menu == 'score_'?'active':''}"><a href="/charts/sale"><i class="fa fa-circle-o"></i> 销售统计</a></li>--%>
                <%--</ul>--%>
            </li>


            <li class=" ${fn:startsWith(param.menu,'help')?'active':''}">
                <a href="/help"><i class="fa fa-share-alt"></i> <span>帮助中心</span></a>
            </li>
                <!--<li><a href="#"><i class="fa fa-circle-o text-yellow"></i> <span>Warning</span></a></li>
                <li><a href="#"><i class="fa fa-circle-o text-aqua"></i> <span>Information</span></a></li>-->
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>
