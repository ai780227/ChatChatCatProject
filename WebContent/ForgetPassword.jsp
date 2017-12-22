<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>註冊視窗</title>
<script>
	var servCtexPath = "<c:url value='/' />";
</script>
<link rel="stylesheet" href="<c:url value='/css/newspaper/jquery-ui-1.10.4.custom.css'/>">
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

</style>

<script>
	$(function() {
		$(".buttonClass").button();
		
	});

</script>

</head>

<body>
	
	<form id="forgetpasswordform" action="<c:url value='/ForgotPasswordServlet'/>" method="POST" 
			style="line-height: 24px; font-size: 18px; font-family: '微軟正黑體';">
		<p>
			<label for="username">帳號</label>
			<input type="text" name="username" id="username" value="${param.m_id }" 
				placeholder="請輸入帳號"
				class="text ui-widget-content ui-corner-all"/>
				${errorMsg.errorIdEmpty }${errorMsg.errorIdNonexists }<br>
		</p>
		<p>	
			<label for="email">信箱</label>
			<input type="text" name="email" id="email" size="30" value="${param.m_email }" 
				placeholder="請輸入註冊的電子信箱"
				class="text ui-widget-content ui-corner-all"/>
				${errorMsg.errorEmailEmpty }${errorMsg.errorEmailNonexists }<br>
		</p>	
			<input type="submit" value="送出" class="buttonClass"/> 
			<input type="button" value="取消" class="buttonClass" onclick="parent.$('#signup').dialog('close');"/>
	</form>	

</body>
</html>