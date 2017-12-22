<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Activity</title>
<link rel="stylesheet" href="<c:url value='/css/sunny/fontsize1.1/jquery-ui-1.10.4.custom.css'/>">
<script type="text/javascript" src="<c:url value="/js/jquery-2.1.0.js" />"></script>
<script src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>
<script type="text/javascript"src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDTwtDHP39nM6NcX2VfMNNdI8ePbb3vuR4&sensor=false"></script>
<script type="text/javascript" src="<c:url value="/js/editMap.js" />"></script>

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
	<form method="POST" action="<c:url value='/EditAct.do'/>">
		<input type="hidden" name="act_id"
			value="${requestScope.editTheAct.getAct_id()}"> 活動標題：<input
			type="text" name="act_title"
			value="<c:out value='${requestScope.editTheAct.getAct_title()}'/>"><br>
		活動時間：<input type="text" name="act_time"
			value="<c:out value='${requestScope.editTheAct.getAct_time()}'/>"><br>
		活動地點：<input type="text" name="act_location" id="address"
			style="width: 200px"
			value="<c:out value='${requestScope.editTheAct.getAct_location()}'/>">
		<input type="button" class="buttonClass" value="搜尋" onclick="codeAddress()" style="font-size:16px; margin-bottom:8px;">
		<div id="map-canvas" style="width: 350px; height: 350px; border-radius: 25px;"></div>
		活動內容：<input type="text" name="act_content"
			value="<c:out value='${requestScope.editTheAct.getAct_content()}'/>"><br>
		尚未邀請的好友：
		<c:choose>
			<c:when test="${requestScope.myfriquantity!=0}">
				<c:forEach var="myFriends" items="${requestScope.myfri}">
					<input type="checkbox" name="friendsName"
						value="${myFriends.getM_id()}">${myFriends.getM_name()} &nbsp; 
				</c:forEach>
			</c:when>
			<c:when test="${requestScope.myfriquantity==0}">
				<c:out value="已邀請所有好友" />
			</c:when>
		</c:choose>
		<br> <input type="submit" class="buttonClass" value="確認修改">
		<input type="button" class="buttonClass" value="取消編輯"
			onclick="javascript:window.location='<c:url value='/ForManageActPage.do'/>'">
	</form>

</div>

</body>
</html>