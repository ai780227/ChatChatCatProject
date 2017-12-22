<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

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
});
</script>
</head>
<body>
	<form action="<c:url value='/ForgotPasswordServlet'/>" method="POST" style="line-height: 24px; font-size: 18px; font-family: '微軟正黑體';">
		<p>
			<label for="username">帳號</label>
			<input type="text" name="username" id="username" value="${param.m_id }" class="text ui-widget-content ui-corner-all"/>
			${errorMsg.errorIdEmpty }${errorMsg.errorIdNonexists }<br>
		</p>
		<p>	
			<label for="email">信箱</label>
			<input type="text" name="email" id="email" value="${param.m_email }" class="text ui-widget-content ui-corner-all"/>
			${errorMsg.errorEmailEmpty }${errorMsg.errorEmailNonexists }<br>
		</p>	
			<input type="submit" value="送出" class="buttonClass"/> 
	</form>		
	
</body>
</html>