<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${requestScope.Member_friend['m_name']}的個人版面</title>

<script>

</script>

<link rel="stylesheet" href="<c:url value='/css/sunny/fontsize1.1/jquery-ui-1.10.4.custom.css'/>">
<link rel="stylesheet" href="<c:url value='/css/post.css'/>">

<script type="text/javascript" src="<c:url value='/js/jquery-1.10.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.waterwheelCarousel.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.elastic.source.js'/>"></script>

<style>
body {
	overflow-x:hidden;
}
.catblock {
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
.catblock2 {
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
.catblock td{
width:115px;
text-align:center;
	font-family: "微軟正黑體";
}

.meminfo {
	position: relative;
	border-style: outset;
	border-color: #0072E3;
	border-radius: 60px;
	border-width: 7px;
	margin: 30px auto;
/*	background-image:url('../images/paperbackground_white.jpg');*/
	box-shadow: 10px 10px 15px black;
	width: 95%;
	min-height:270px;
	font-family: "微軟正黑體";
	font-weight: bold;
}

.memthumb {
	position: relative;
	border-style: outset;
	border-color: blue;
	border-radius: 40px; 	
	box-shadow: 2px 2px 2px black; 
	margin:30px;
	left:35px;
	top:0px;
	font-family: "微軟正黑體";
}

.memtextblock {	
	display:inline-block;
	border-style: outset;
	border-color: blue;
	border-radius: 60px;
	border-width: 2px;
	padding:8px;
	background: #ACD6FF;
	box-shadow: 2px 2px 2px black;
	font-size:24px;
	font-family: "微軟正黑體";
	font-weight: bold;
}
</style>

<script>

$(function() {
	 $(".buttonClass").button();
	 $(".waterwheelcarousel").waterwheelCarousel({
	      // include options like this:
	      // (use quotes only for string values, and no trailing comma after last option)
	      // option: value,
	      // option: value
		  separation: 100,
		  clickedCenter: function($clickedItem) {
		      // $clickedItem is a jQuery wrapped object describing the image that was clicked.
		      var imageUrl = $clickedItem.attr('src');
		      imageUrl = imageUrl.slice(10, -4);
		      imageUrl = "#" + imageUrl;
		      parent.parent.$(imageUrl).click();
		  }
	 });
	 
		$( ".elastic" ).elastic();
		$(".catblock").hover(function(){
			$(this).toggleClass("catblock2",300).clearQueue();
		});

		var servletCtxPath = '<c:url value="/"/>';
		$('.textblock,.meminfo').css('background-image',"url( '" + servletCtxPath + "images/paperbackground_white.jpg')");
		$('.catblock').css('background-image',"url( '" + servletCtxPath + "images/paperbackground.jpg')");
});

</script>

</head>

<body>
	
	<table class="meminfo">
		<tr>
				<td rowspan="4">
				
			<c:choose>
				<c:when test="${user.m_pic_path == null}">
					<img src="<c:url value='/images/cat1.jpg'/>" width="160" height="auto" style="border-radius: 25px;"/>
				</c:when>
				<c:when test="${user.m_pic_path != null}">
					<img src="<c:url value='/userImages/${user.m_id}/${user.m_pic_path}'/>"
							 width="160" height="auto" style="border-radius: 25px;"/>
				</c:when>
			</c:choose>
			
			</td>
			
			<td width="180px" rowspan="3">		
				<div class="memtextblock">
					<c:out value="${user['m_name']}" />		
				</div>					
			</td>
			
			<td width="240px">		
				<c:choose>
					<c:when test="${user['m_birth']==null}">
					</c:when>
					<c:when test="${user['m_birth']!=null}">生日：${user["m_birth"]}<br>
					</c:when>
				</c:choose>
			</td>
		</tr>
		
		<tr>
			<td width="240px">			
				<c:choose>
					<c:when test="${user['m_email']==null}">
					</c:when>
					<c:when test="${user['m_email']!=null}">E-mail：${user["m_email"]}<br>
					</c:when>
				</c:choose>
			</td>
		</tr>
		
		<tr>
			<td width="240px">				
				<c:choose>
					<c:when test="${user['m_sex']==null}"></c:when>
					<c:when test="${user['m_sex']!=null}">性別：<img src="<c:url value='/images/${user["m_sex"]}.png' />"/><br></c:when>
				</c:choose>
			</td>
		</tr>
		
		<tr>
			<td colspan="2" width="320px">
				<c:choose>
					<c:when test="${user['m_intro']==null}">
					</c:when>
					<c:when test="${user['m_intro']!=null}">自我簡介：${user["m_intro"]}
					</c:when>
				</c:choose>
			</td>
		</tr>
	</table>
	
	<c:choose>
		<c:when test="${cats == null}">沒有貓<HR></c:when>
		<c:when test="${cats != null}">
			<c:forEach var="Cat" items="${cats}">
				<div class="catblock">
					<table>
						<tr>
							<td>
								<c:choose>							
									<c:when test="${Cat.c_pic_path==null}">
										<a href="#">
											<img src="<c:url value='/images/cat2.jpg'/>"
												 width="160" height="auto" class="thumbnail"/>
										</a>
									</c:when>
									<c:otherwise>
										<a href="#">
											<img src="<c:url value='/userImages/${user["m_id"]}/${Cat.c_pic_path}'/>"
												 width="160" height="auto" class="thumbnail"/>
										</a>
									</c:otherwise>													
								</c:choose>
							</td>
							<td><div class="textblock">${Cat.c_name}</div></td>
							<td>
								<div class="catinfoblock">	
									<div>											
										<c:choose>
											<c:when test="${Cat.c_age==null}">
											</c:when>
											<c:when test="${Cat.c_age!=null}">出生年：${Cat.c_age}
											</c:when>
										</c:choose>
									</div>										 									
									<c:choose>
										<c:when test="${Cat.c_breed==null}">
										</c:when>
										<c:when test="${Cat.c_breed!=null}"><div>品種：${Cat.c_breed}</div>
										</c:when>
									</c:choose>											
									<c:choose>
										<c:when test="${Cat.c_sex==null}">
										</c:when>
										<c:when test="${Cat.c_sex!=null}"><div>性別：<img src="<c:url value='/images/${Cat.c_sex}.png' />"/></div>
										</c:when>
									</c:choose>										
									<c:choose>
										<c:when test="${Cat.c_intro==null}">
										</c:when>
										<c:when test="${Cat.c_intro!=null}"><div>簡介：${Cat.c_intro}</div>
										</c:when>
									</c:choose>									
								</div>
							</td>
							</tr>
					</table>
				</div>
			</c:forEach>
		</c:when>	
	</c:choose>
<!-- 	
<div class="block">
	<table>
		<tr>
		  <td><img src="../images/cat2.jpg" onclick="" width="160" height="auto" class="thumbnail"></td>
		  <td><div class="textblock">小白大白貓</div></td> 
		  <td><div class="catinfoblock"><div>出生年：1990</div><div>品種：米克斯</div><div>性別：icon-male</div><div>簡介：這是小白，我養的第二隻貓~</div></div></td>
		</tr>
	</table>
</div>
 -->	
	

<div class="post"></div>
<input type="hidden" id="btn_submit">
<script type="text/javascript">
var page = 0, size = 5;
var post_type = 0, post_property = 0;
var user = "${user}";
var user_id = "${user.getM_id()}";
var m_id = "${user.getM_id()}";
var manager = "${manager}";
var PostURL = "<c:url value='/ViewPostsServlet.do'/>";
var SearchURL = "<c:url value='/SearchServlet.do'/>";
var LikeURL = "<c:url value='/LikePostServlet.do'/>";
var FavoriteURL = "<c:url value='/AddPostToFavoriteServlet.do'/>";
var ResponseURL = "<c:url value='/CreateResponseServlet.do'/>";
var AccuseURL = "<c:url value='/pages/NewReport.jsp'/>";
var imgURL = "<c:url value='/'/>";
var ServletCtxURL = "<c:url value='/'/>";
var DelResURL = "<c:url value='/DeleteResponseServlet.do'/>";
$(".buttonClass").button();
</script>
<script src="<c:url value='/js/post.js'/>"></script>

	
</body>
</html>