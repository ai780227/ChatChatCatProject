<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<c:url value='/ForgotPasswordServlet'/>" method="POST">
		帳號<input type="text" name="m_id" value="${param.m_id }"/>${errorMsg.errorIdEmpty }${errorMsg.errorIdNonexists }<br>
		信箱<input type="text" name="m_email" value="${param.m_email }"/>${errorMsg.errorEmailEmpty }${errorMsg.errorEmailNonexists }<br>
		<input type="submit" value="送出" /> 
	</form>
</body>
</html>