<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Change Password</title>

<link rel="stylesheet" href="<c:url value='/css/sunny/fontsize1.1/jquery-ui-1.10.4.custom.css'/>">
<link rel="stylesheet" href="<c:url value='/css/jquery.validate.password.css'/>">

<script type="text/javascript" src="<c:url value='/js/jquery-1.10.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.validate.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/messages_zh_TW.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.validate.password.js'/>"></script>

<style>
label {
	display: inline-block;
	width: 5em;
}

input.text {
	margin-bottom: 12px;
	width: 300px;
	padding: .4em;
	font-size: 14px;
	font-family: '微軟正黑體';
}

label.error {
	font-size: 16px;
	width: auto;
}
</style>
<script>
$(function() {
	$(".buttonClass").button();
//	window.parent.location.reload();
//	window.parent.$('#forForgotPwdToChangePwd').dialog('open');
});
</script>

</head>
<body>

	<form action="<c:url value="/ChangePwdServlet.do"/>" method="POST" style="line-height: 24px; font-size: 18px; font-family: '微軟正黑體';">
		${user.m_name } 您好<br>
		請重新設定您的個人密碼<br>
		<a href="/ChatChatCatProject/index.jsp" target="_top">首頁</a><br>
		<p>
			<label for="pwd">輸入新密碼</label>
			<input type="text" name="pwd" id="pwd" class="text ui-widget-content ui-corner-all"/>
			${errorMsg.errorPwdEmpty }<br>
		</p>
		<p>	
			<label for="re_pwd">重新輸入新密碼</label>
			<input type="text" name="re_pwd" id="re_pwd" class="text ui-widget-content ui-corner-all"/>
			${errorMsg.errorRePwdEmpty } ${errorMsg.errorPwdDifferent }<br>
		</p>	
			<input type="submit" value="送出" class="buttonClass"/>

</form>
</body>
</html>