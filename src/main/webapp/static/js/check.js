//登录界面学号验证
function login_studentID_username() {
	var stuIDval = document.getElementById("studentID").value;
	var patt1 = new RegExp(/^[0-9]+$/);
	var studentCue = document.getElementById("studentCue");
	if(!patt1.test(stuIDval)){
		studentCue.innerHTML="<font color='red'><b>学号只能为纯数字</b></font>";
	}
	return patt1.test(stuIDval);
}
//登录界面职工号验证
function login_teacherID_username() {
	var stuIDval = document.getElementById("teacherID").value;
	var patt1 = new RegExp(/^[0-9]+$/);
	var teacherCue = document.getElementById("teacherCue");
	if(!patt1.test(stuIDval)){
		teacherCue.innerHTML="<font color='red'><b>职工号只能为纯数字</b></font>";
	}
	return patt1.test(stuIDval);
}
