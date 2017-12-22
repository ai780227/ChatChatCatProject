<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Change Password</title>

<script>
	var servCtexPath = "<c:url value='/' />";
</script>
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
	width: 7em;
	font-family: '微軟正黑體';
}

input.text {
	margin-bottom: 12px;
	width: 300px;
	padding: .4em;
	font-size: 14px;
	font-family: '微軟正黑體';
}


p {
	font-size: 16px;
	font-family: '微軟正黑體';
}

span.err{
	width: 10em;
	text-align: center;
	font-size: 16px;
	font-family: '微軟正黑體';
	color:red;
}
</style>

<script>
$(function() {
	$(".buttonClass").button();
	

});
</script>
</head>
<body>

<form id="changePasswordForm" action="<c:url value="/ChangePwdservlet.do"/>" method="POST">
	
	<br><br>
		<label for="old_pwd">輸入舊密碼 </label>
			<input id="old_pwd" type="password" name="old_pwd" placeholder="必須包含英、數" class="text ui-widget-content ui-corner-all passwordvalidate"/><br>
			<span class="err">${errorMsg.errorOldPwdEmpty}</span>
			<span class="err"> ${errorMsg.errorOldPwd}</span><br><br>
	
		<label for="new_pwd">輸入新密碼 </label>
			<input type="password" name="new_pwd" id="new_pwd" placeholder="必須包含英、數" class="text ui-widget-content ui-corner-all passwordvalidate"/><br>
			<span class="err">${errorMsg.errorPwdEmpty }</span><br><br>
	
	<label for="re_pwd">重新輸入新密碼 </label>
			<input type="password" name="re_pwd" id="re_pwd" placeholder="必須與新密碼一致" class="text ui-widget-content ui-corner-all"/><br>
			<span class="err">${errorMsg.errorRePwdEmpty } </span>
			<span class="err">${errorMsg.errorPwdDifferent } </span><br><br>
		<div style="text-align:center"><input type="submit" value="送出" class="buttonClass"/></div>
	
</form>
</body>
</html>