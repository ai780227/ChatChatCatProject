<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Check New Activity</title>
<link rel="stylesheet" href="<c:url value='/css/sunny/fontsize1.1/jquery-ui-1.10.4.custom.css'/>">
<script type="text/javascript" src="<c:url value="/js/jquery-2.1.0.js" />"></script>
<script src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>
<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDTwtDHP39nM6NcX2VfMNNdI8ePbb3vuR4&sensor=false"></script>
<script type="text/javascript" src="<c:url value="/js/checkMap.js" />"></script>

<style>
.outerblock {

border-radius: 30px;
font-size: 20px;
padding-top:20px;
padding-left:40px;
font-family:'微軟正黑體';
line-height:36px;
color:#001188; 
text-shadow: 0px 0px 5px #cca6a6;
font-weight:bold;
}


.buttonClass {

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
	cursor:pointer;
	font-family:'微軟正黑體';
	font-size: 18px;
}

</style>

<script>
	$(function() {
		$(this).height(500);
	});
</script>

</head>

<body onload="initialize()">

	<div class="outerblock">
	<jsp:useBean id="newAct" class="com.ccc.model.bean.ActivityBean" scope="request" />
	活動屬性：
	<c:choose>
		<c:when test="${requestScope.property == 1}">
			<c:out value="公開活動" />
		</c:when>
		<c:when test="${requestScope.property == 0}">
			<c:out value="私人活動" />
		</c:when>
	</c:choose>
	<br> 活動標題：<jsp:getProperty property="act_title" name="newAct" /><br>
	活動日期：<jsp:getProperty property="act_time" name="newAct" /><br>
	報名截止：
	<c:out value="${requestScope.deadline}">無活動截止日</c:out>
	<br> 活動地點：
	<input type="text" name="act_location" id="address" readonly="readonly"
		style="font-size: 16px; cursor: default; background: none; border: 0; width: 225px"
		value="${requestScope.newAct.getAct_location()}">
	<div id="map-canvas" style="width: 350px; height: 350px; border-radius: 25px;"></div>
	活動內容：
	<c:out value="${requestScope.newAct.getAct_content()}" />
	<br> 邀請好友：
	<c:choose>
		<c:when test="${requestScope.friendsName!=null}">
			<c:forEach var="myFriends" items="${requestScope.friendsName}">
						${myFriends.getM_name()} &nbsp; 
					</c:forEach>
		</c:when>
		<c:when test="${requestScope.friendsName==null}">
			<c:out value="無邀請好友" />
		</c:when>
	</c:choose>
	</div>

	<input type="button" class="buttonClass" value="回活動管理"
		onclick="javascript:window.location='<c:url value='/ForManageActPage.do'/>'">
</body>
</html>