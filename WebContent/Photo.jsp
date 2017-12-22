<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>相片</title>

<link rel="stylesheet" href="<c:url value='/css/sunny/fontsize1.1/jquery-ui-1.10.4.custom.css'/>">

<script src="<c:url value='/js/jquery-1.10.2.js'/>"></script>
<script src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>
<script src="<c:url value='/js/jquery.waterwheelCarousel.js'/>"></script>




<style>

body {
	overflow-x:hidden;
}

/*  以下為新增    */ 
.smallimg1{
	margin:3px;
	border-radius: 15px;
    box-shadow: 10px 10px 35px black;
	width:auto;
	height:120px;
}
.smallimg2{
    border-style: outset;
    border-color: grey;
    border-width: 7px;
}

/*  以上為新增    */

</style>

<script>

$(function() {
	

	
	
	 $(".buttonClass").button();
	  
	 $(".smallimg1").hover(function() {
			$(this).toggleClass("smallimg2", {duration: 300}).clearQueue();
		});

});

</script>
</head>

<body>

	<h1 style="text-align:center;">您的所有相片</h1>
	
	<div style="text-align:center; margin-bottom: 20px;"><a href="<c:url value='/PhotoServlet.do' />?photos=viewPhotos_big" class="buttonClass">單張模式</a></div>

	<div class="smallviewblock">
	<c:choose>
		<c:when test="${photos!=null}">
			<c:forEach var="photos" items="${photos}">
				<input type="hidden" id="Pic_id" value="${photos.getPic_id()}">
				<img src="<c:url value='/userImages/${member.getM_id()}/${photos.getPic_file_path()}'/>" alt="Image ${photos.getPic_id()}" class="smallimg1"/>
			</c:forEach>
		</c:when>
		<c:when test="${photos==null}">沒有照片</c:when>
	</c:choose>
	</div>
</body>

</html>