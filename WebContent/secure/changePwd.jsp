<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Change Password</title>
</head>
<body>

<form action="<c:url value="/ChangePwdServlet.do"/>" method="POST">
	${user.m_id }<br>
	<label for="pwd">輸入新密碼</label><input type="text" name="pwd" id="pwd"/>${errorMsg.errorPwdEmpty }<br>
	<label for="re_pwd">重新輸入新密碼</label><input type="text" name="re_pwd" id="re_pwd"/>${errorMsg.errorRePwdEmpty } ${errorMsg.errorPwdDifferent }<br>
	<input type="submit" value="送出"/>
</form>
</body>
</html>