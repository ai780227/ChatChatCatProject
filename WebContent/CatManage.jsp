<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>貓咪管理頁面</title>

<link rel="stylesheet" href="<c:url value='/css/sunny/fontsize1.1/jquery-ui-1.10.4.custom.css'/>">
	
<script type="text/javascript" src="<c:url value='/js/jquery-1.10.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>

<style>
body {
	overflow-x:hidden;
}
  
.block {
	position: relative;
	border-style: outset;
	border-color: orange;
	border-radius: 60px;
	border-width: 7px;
	margin: 30px auto;
/*	background-image:url('../images/paperbackground.jpg');*/
	box-shadow: 10px 10px 15px black;
	width: 80%;
	min-height:250px;
	font-family: "微軟正黑體";
}
.block2 {
	border-color: yellow;
}
.thumbnail {
	position: relative;
	border-style: outset;
	border-color: black;
	border-radius: 140px; 	
	box-shadow: 2px 2px 2px black; 
	margin:30px;
	left:0px;
	top:10px;
}
.textblock {	
	display:inline-block;
	border-style: outset;
	border-color: white;
	border-radius: 60px;
	border-width: 2px;
	padding:8px;
/*	background-image:url('../images/paperbackground_white.jpg');*/
	box-shadow: 2px 2px 2px black;
	font-family: "微軟正黑體";
	font-weight: bold;
	font-size:18px;
}
.catinfoblock {
	display:inline-block;
	text-align:center; 
	width: 260px; 
	padding: 10px; 
	line-height:30px;
	font-family: "微軟正黑體";
}
.block td{
	width:115px;
	text-align:center;
	font-family: "微軟正黑體";
}
.iconinvisible{
	position: absolute;
	top:15px;
	right:15px;
	opacity: 0.2;
	filter: alpha(opacity =    20);
}
.iconinvisible:hover{
	opacity: 1;
	filter: alpha(opacity =    100);
}
.iconinvisible2{
	position: absolute;
	top:50px;
	right:15px;
	opacity: 0.2;
	filter: alpha(opacity =    20);
}
.iconinvisible2:hover{
	opacity: 1;
	filter: alpha(opacity =    100);
}
</style>

<script>
	$(function() {
		$(".buttonClass").button();
		
		$(".block").hover(function(){
			$(this).toggleClass("block2",300).clearQueue();
		});
		
		var servletCtxPath = '<c:url value="/"/>';
		$('.textblock,.meminfo').css('background-image',"url( '" + servletCtxPath + "images/paperbackground_white.jpg')");
		$('.block').css('background-image',"url( '" + servletCtxPath + "images/paperbackground.jpg')");

	/*	$("a[name='delete']").click(function() {
			parent.parent.$().message("貓咪已刪除");
		});*/
	});

</script>


</head>

<body>
	
	<div>
	<a href="<c:url value='/pages/NewCat.jsp'/>" class="buttonClass">新貓咪</a>
	</div>
	
	<c:forEach items="${cats }" var="aCat">
		<div class="block" id="cat_id_${aCat.c_id }">
			<table>
	<tr>
  	<td>
				<c:choose>
					<c:when test="${aCat.c_pic_path != null}">
						<img src="<c:url value='/userImages/${aCat.getMember().m_id}/${aCat.c_pic_path}'/>"
								 width="160" height="auto" class="thumbnail" />
					</c:when>
					<c:otherwise>
						<img src="<c:url value='/images/cat.jpg'/>"
								 width="160" height="auto" class="thumbnail" />
					</c:otherwise>
				</c:choose>
				</td>
	<td><div class="textblock">${aCat.c_name}</div></td> 
  	<td><div class="catinfoblock"><div><c:if test="${aCat.c_age != null && aCat.c_age !='' }">出生年：${aCat.c_age }</c:if></div><div>
										  <c:if test="${aCat.c_breed != null && aCat.c_breed !='' }">品種：${aCat.c_breed}</c:if></div><div>
										  <c:if test="${aCat.c_sex != null && aCat.c_sex !='' }">性別：${aCat.c_sex}</c:if></div><div>
						<c:if test="${aCat.c_intro != null && aCat.c_intro !='' }">
							簡介：${aCat.c_intro}						
						</c:if>					
					</div></div></td>
	</tr>
	</table>
			<a name="delete" href="<c:url value='/RemoveCatInfoServlet.do?c_id=${aCat.c_id }'/>" onclick="return confirm('確認刪除?')">
				<img src="<c:url value='/images/delete.png'/>" class="iconinvisible"></a>
			<a href="<c:url value='/ViewOneCatInfoServlet.do?c_id=${aCat.c_id }'/>">
				<img src="<c:url value='/images/edit.png'/>" class="iconinvisible2"></a>
		</div>
	</c:forEach>

</body>
</html>