<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>註冊結果視窗</title>

<link rel="stylesheet" href="<c:url value='/css/newspaper/jquery-ui-1.10.4.custom.css'/>">
<link rel="stylesheet" href="<c:url value='/css/jquery.validate.password.css'/>">

<script type="text/javascript" src="<c:url value='/js/jquery-1.10.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.validate.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/messages_zh_TW.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.validate.password.js'/>"></script>

<script type="text/javascript" src="<c:url value='/js/additional-methods.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.elastic.source.js'/>"></script>

<style>

</style>

<script>
	$(function() {
		$(document).tooltip();
		$(".buttonClass").button();		
		
	});
</script>

</head>

<body>


<c:if test="${SignUp_result == 'success'}">
	<div style="line-height: 24px; font-size: 18px; font-family: '微軟正黑體'; text-align: center; margin:150px;">
		<p>註冊成功</p>
		<p>請返回首頁再登入</p>
	<input type="button" value="確定" class="buttonClass" onclick="parent.$('#signup').dialog('close');"/>
</div>
</c:if>

<c:if test="${SignUp_result == 'fail'}">
	<div style="line-height: 24px; font-size: 18px; font-family: '微軟正黑體'; text-align: center; margin:150px;">
		<p>註冊失敗</p>
		<p>請返回首頁再註冊</p>
	<input type="button" value="確定" class="buttonClass" onclick="parent.$('#signup').dialog('close');"/>
</div>

</c:if>


</body>
</html>