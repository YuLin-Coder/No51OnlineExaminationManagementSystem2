<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>OES在线考试系统|管理员登录</title>
		<link rel="stylesheet" type="text/css" href="/static/css/login.css" />
		<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
	</head>

	<body>
	<div class="b3"></div>
		<div class="admin_login_wrap">
			<h1>OES在线考试系统</h1>
			<h1 style="margin-top:-30px;margin-bottom: 20px">后台管理</h1>		
			<div class="login">
			<div class="header" style="text-align: center;">
			<label style="font-size:20px;line-height:51px">管理员登录</label>
			</div>
				<div class="web_login" style="margin-top:20px">
					<form method="post">
						<ul class="reg_form">
							<li>
								<label class="input-tips2">用户名：</label>
								<input type="text" name="adminID" value="456" id="adminID" size="35" class="inputstyle2" />
							</li>
							<li>
								<label class="input-tips2">密码：</label>
								<input type="password" name="adminPassword" value="123" id="adminPassword" size="35" class="inputstyle2" />
							</li>
							<%--<li style="height: 30px;">
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
		</div>
		<jsp:include page="../include/login_footer.jsp"></jsp:include>
	</body>
</html>