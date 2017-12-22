<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Change Password</title>

<link rel="stylesheet" href="<c:url value='/css/newspaper/jquery-ui-1.10.4.custom.css'/>">

<script type="text/javascript" src="<c:url value='/js/jquery-1.10.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>

<style>

</style>
<script>
	$(function() {
		$(".buttonClass").button();
	});
</script>

</head>
<body>
	<div style="text-align:center; font-family: '微軟正黑體';">
		<p>帳號確認</p>
		<p>我們已將新密碼寄到您的信箱</p>
		<p>請至您的信箱收信</p>
		<p>謝謝</p>
		<input type="button" value="確認" class="buttonClass" onclick="parent.$('#forgetpassword').dialog('close');"/>
	</div>
</body>
</html>