<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>右側欄</title>

<link rel="stylesheet" href="<c:url value='/css/sunny/fontsize1.1/jquery-ui-1.10.4.custom.css'/>">
	
<script src="<c:url value='/js/jquery-1.10.2.js'/>"></script>
<script src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>

<style>
.calendarclass {
	width: 100%;
	height: 300px;
	border: 0px;
}

body {
	padding: 0;
	margin: 0;
	text-align: center;
}

div {
	margin: 5px;
	padding: 5px 5px;
}

a,a:visited {
	color: rgba(79,37,0,1);
	font-weight: bold;
	text-decoration: none;
	font-family:'微軟正黑體';
}

span:hover {
background: rgb(255,243,216); /* Old browsers */
background: -moz-linear-gradient(top,  rgba(255,243,216,1) 0%, rgba(255,213,109,1) 50%, rgba(255,202,112,1) 51%, rgba(255,250,242,1) 100%); /* FF3.6+ */
background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(255,243,216,1)), color-stop(50%,rgba(255,213,109,1)), color-stop(51%,rgba(255,202,112,1)), color-stop(100%,rgba(255,250,242,1))); /* Chrome,Safari4+ */
background: -webkit-linear-gradient(top,  rgba(255,243,216,1) 0%,rgba(255,213,109,1) 50%,rgba(255,202,112,1) 51%,rgba(255,250,242,1) 100%); /* Chrome10+,Safari5.1+ */
background: -o-linear-gradient(top,  rgba(255,243,216,1) 0%,rgba(255,213,109,1) 50%,rgba(255,202,112,1) 51%,rgba(255,250,242,1) 100%); /* Opera 11.10+ */
background: -ms-linear-gradient(top,  rgba(255,243,216,1) 0%,rgba(255,213,109,1) 50%,rgba(255,202,112,1) 51%,rgba(255,250,242,1) 100%); /* IE10+ */
background: linear-gradient(to bottom,  rgba(255,243,216,1) 0%,rgba(255,213,109,1) 50%,rgba(255,202,112,1) 51%,rgba(255,250,242,1) 100%); /* W3C */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#fff3d8', endColorstr='#fffaf2',GradientType=0 ); /* IE6-9 */

}

span {
	background: #fceabb; /* Old browsers */
	background: -moz-linear-gradient(top,  #fceabb 0%, #fccd4d 50%, #f8b500 51%, #fbdf93 100%); /* FF3.6+ */
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#fceabb), color-stop(50%,#fccd4d), color-stop(51%,#f8b500), color-stop(100%,#fbdf93)); /* Chrome,Safari4+ */
	background: -webkit-linear-gradient(top,  #fceabb 0%,#fccd4d 50%,#f8b500 51%,#fbdf93 100%); /* Chrome10+,Safari5.1+ */
	background: -o-linear-gradient(top,  #fceabb 0%,#fccd4d 50%,#f8b500 51%,#fbdf93 100%); /* Opera 11.10+ */
	background: -ms-linear-gradient(top,  #fceabb 0%,#fccd4d 50%,#f8b500 51%,#fbdf93 100%); /* IE10+ */
	background: linear-gradient(to bottom,  #fceabb 0%,#fccd4d 50%,#f8b500 51%,#fbdf93 100%); /* W3C */
	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#fceabb', endColorstr='#fbdf93',GradientType=0 ); /* IE6-9 */
	
	padding: 4px 25px;
    border-radius: 25px;
    border: solid 2px orange;
}
</style>

<script>
	$(function() {
		

	});
</script>

</head>

<body style="background-color: #ffe6b5;">
	<img src="<c:url value='/images/cat1.jpg'/>" width="150" style="border-radius: 25px;">
	<div>${manager.mgr_name }</div>
	<br>
	<div><a href="<c:url value='/NewsMaintainServlet.mgr'/>" target=f3><span>維護新聞</span></a></div>
	<div><a href="<c:url value='/pages/ManagerBoard.jsp'/>" target=f3><span>維護公告</span></a></div>
	<div><a href="<c:url value='/ViewReportServlet.mgr'/>" target=f3><span>檢舉系統</span></a></div>
	<div><a href="<c:url value='/pages/knowledge_manual.jsp'/>" target=f3><span>知識手冊</span></a></div>
	<div><a href="<c:url value='/index.jsp'/>" target="_top"><span>前台系統</span></a></div>
	
</body>
</html>