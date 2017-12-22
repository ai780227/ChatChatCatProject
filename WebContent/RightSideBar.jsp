<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8" />
<title>右側欄</title>

<link rel="stylesheet" href="<c:url value='/css/sunny/fontsize1.1/jquery-ui-1.10.4.custom.css'/>">

<script type="text/javascript" src="<c:url value='/js/jquery-1.10.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>

<style>

body {
	padding: 0;
	margin: 0;
	text-align: center;
}

label {
	display: inline-block;
	width: 5em;
	line-height: 24px; 
	font-size: 18px; 
	font-family: '微軟正黑體';
}

input.text {
	margin-bottom: 12px;
	width: 210px;
	padding: .4em;
	font-size: 14px;
	font-family: '微軟正黑體';
}

/* RightSideBarAfterLogin */
.calendarclass {
	width: 100%;
	height: 300px;
	border: 0px;
}

div {
	margin: 5px;
	padding: 6px;
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
border: solid 2px rgb(255,253,238);
    box-shadow: 0px 0px 5px orange;
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
	
	padding: 7px 15px;
    border-radius: 35px;
    border:outset 1px orange;
    box-shadow: 3px 3px 5px black;
}

.rightimg {
	border:outset 5px white;
	box-shadow: 3px 3px 5px black;
	border-radius: 25px;
}

.rightimg:hover {
	border:outset 5px rgb(255,207,119);
	box-shadow: 3px 3px 5px rgb(255,207,119);
}

.username {
	font-size: 18px; 
}

.username:hover {
	color:white;
	text-shadow: 0px 0px 5px black;
}

</style>

<script>

</script>
</head>

<body style="background-color: #ffe6b5;">

<c:if test="${ user != null }">
	<a href="<c:url value='/pages/MyMemberPage.jsp'/>" target=f3>
		<c:choose>
			<c:when test="${user.m_pic_path != null}">
				<img src="<c:url value='/userImages/${user.m_id}/${user.m_pic_path}'/>" width="150" class="rightimg">
			</c:when>
			<c:otherwise>
				<img src="<c:url value='/images/cat1.jpg'/>" width="150" class="rightimg">
			</c:otherwise>
		</c:choose>		
	</a>

	<div style="margin:0px 0p 5px 0px;">
	<div><a href="<c:url value='/pages/MyMemberPage.jsp'/>" target=f3 class="username">${user.m_name }</a></div>
	<div><a href="<c:url value='/pages/ActivityIndex.jsp'/>" target=f3><span>　活動　</span></a></div>
	<div><a href="<c:url value='/PhotoServlet.do' />?photos=viewPhotos" target=f3><span>　相本　</span></a></div>
	<div><a href="<c:url value='/ViewCatsInfoServlet.do'/>" target=f3><span>　貓咪　</span></a></div>
	<div><a href="<c:url value='/FriendServlet.do?friendship_type=FriendsList'/>" target=f3><span>　好友　</span></a></div>
	<div><a href="<c:url value='/pages/Favorite.jsp'/>" target=f3><span>　最愛　</span></a></div>
	<div><a href="<c:url value='/pages/Personal.jsp'/>" target=f3><span>個人資料</span></a></div></div>
	
	<iframe class="calendarclass" src="<c:url value='/pages/Calendar.jsp'/>"></iframe>
</c:if>


</body>
</html>