<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>OES_学生|教师登录</title>
    <link href="/static/css/login.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/static/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="/static/js/login.js" charset="utf-8"></script>
    <script type="text/javascript" src="/static/js/check.js" ></script>
    <script>
        $(function () {

            var func = function() {
                $('#switch_login').removeClass("switch_btn").addClass('switch_btn_focus');
                $('#switch_qlogin').removeClass("switch_btn_focus").addClass('switch_btn');
                $('#switch_bottom').animate({
                    left: '154px',
                    width: '64px'
                });
                $('#qlogin').css('display', 'block');
                $('#web_qr_login').css('display', 'none');
                $('.b2').animate({
                    opacity: '0',
                }, 1000);
                $('.b1').animate({
                    opacity: '1',
                }, 500);
            }
            var message = ${message_tea};
            if(message != "") {
              $(document).on("click", $("#switch_qlogin"), func());
            }

        });
    </script>
</head>
<body>
<h1>OES在线考试系统</h1>
<div class="login" style="margin-top:50px;">
    <div class="header">
        <div class="switch" id="switch">
            <a class="switch_btn_focus" id="switch_qlogin" href="javascript:;">学生登录</a>
            <a class="switch_btn" id="switch_login" href="javascript:;">老师登录</a>
            <div class="switch_bottom" id="switch_bottom" style="position: absolute; width: 64px; left: 0px;"></div>
        </div>
    </div>

    <!--学生登录-->
    <div class="web_qr_login" id="web_qr_login" style="display: block;">
        <div class="web_login">
            <form name="student_login" method="post" onsubmit="return login_studentID_username()">
                <ul class="reg_form" >
                    <c:if test="${empty message}">
                        <div id="studentCue" class="cue">请输入学号和密码进行登录</div>
                    </c:if>
                    <c:if test="${!empty message}">
                        <p class="login-box-msg cue">${message}</p>
                    </c:if>
                    <li>
                        <label class="input-tips2">学号：</label>
                        <div class="inputOuter2">
                            <input type="text" id="studentID" name="studentID" value="311409030311" maxlength="16" class="inputstyle2" autocomplete="on"/>
                        </div>
                    </li>
                    <li>
                        <label class="input-tips2">密码：</label>
                        <div class="inputOuter2">
                            <input type="password" id="studentPassword" name="studentPassword" value="123" maxlength="16" class="inputstyle2" />
                        </div>
                    </li>
                    <%--<li style="height: 30px;">
                        <label class="input-tips2"></label>
                        <label><input type="checkbox" name="autoLogin" value="1" />七天内自动登陆</label>
                    </li>--%>
                    <li>
                        <div class="inputArea">
                            <input type="submit" id="s_reg" style="margin-top:10px;margin-left:90px;" class="button_blue" value="立即登录" />
                        </div>
                    </li>
                </ul>
            </form>
        </div>
    </div>
    <!--学生登录end-->
    <!--老师登录-->
    <div class="qlogin" id="qlogin" style="display: none; ">
        <div class="web_login">
            <form name="teacher_login" id="regUser" action="/teacher/login" method="post" onsubmit="return login_teacherID_username()">
                <ul class="reg_form" id="reg-ul">
                    <c:if test="${empty message_tea}">
                        <div id="teacherCue" class="cue">请输入职工号和密码进行登录</div>
                    </c:if>
                    <c:if test="${!empty message_tea}">
                        <p class="login-box-msg cue">${message_tea}</p>
                    </c:if>
                    <li>
                        <label class="input-tips2">职工号：</label>
                        <div class="inputOuter2">
                            <input type="text" id="teacherID" name="teacherID" value="123" maxlength="16" class="inputstyle2" />
                        </div>
                    </li>
                    <li>
                        <label class="input-tips2">密码：</label>
                        <div class="inputOuter2">
                            <input type="password" id="teacherPassword" name="teacherPassword" value="123" maxlength="16" class="inputstyle2" />
                        </div>
                    </li>
                   <%-- <li style="height: 30px;">
                        <label class="input-tips2"></label>
                        <label><input type="checkbox" name="autoLogin" value="1" />七天内自动登陆</label>
                    </li>--%>
                    <li>
                        <div class="inputArea">
                            <input type="submit" id="reg" style="margin-top:10px;margin-left:90px;" class="button_blue" value="立即登录" />
                        </div>
                    </li>
                </ul>
            </form>
        </div>
    </div>
    <!--老师登录end-->
</div>
<div class="b1"></div>
<div class="b2"></div>
<jsp:include page="./include/login_footer.jsp"></jsp:include>



</body>
</html>
