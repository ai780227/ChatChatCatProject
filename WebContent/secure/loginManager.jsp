<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<c:url value='/js/jquery-2.1.0.js'/>"></script>
</head>
<body>
<form action='<c:url value="/ManagerLoginServlet"/>' method='POST'>
帳號<input type='text' name='mgr_id' placeholder='帳號'/>
密碼<input type='text' name='mgr_pwd' placeholder='密碼'/>
<input type='submit' value='送出'/>
</form>
</body>
</html>