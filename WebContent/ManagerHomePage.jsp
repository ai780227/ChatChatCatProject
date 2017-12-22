<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ChatChatCat後台系統</title>

<link rel="stylesheet" href="<c:url value="/css/normalize.css" />">
<link rel="stylesheet" href="<c:url value="/css/sunny/fontsize1.1/jquery-ui-1.10.4.custom.css" />">
<link rel="stylesheet" href="<c:url value="/css/jquery.message.css" />">

<script src="<c:url value="/js/jquery-1.10.2.js" />"></script>
<script src="<c:url value="/js/jquery-ui-1.10.4.custom.js" />"></script>
<script src="<c:url value="/js/jquery.autoheight.js" />"></script>
<script src="<c:url value="/js/jquery.message.js" />"></script>

<style>

.menu {
	position: relative;
	display: block;
	width: 1125px;
	height: 100px;
	margin: 0;
	border: 0px;
	margin-left: auto;
	margin-right: auto;
}

.mainContent {
	width: 1125px;
	border: 0px;
	frameBorder: 0px;
	cellspacing: 0px;
	marginwidth: 0px;
	marginheight: 0px;
	overflow: hidden;
	margin: 0;
	display: block;
}

.mainContentblock{
	width: 1125px;
	margin: 0;
	padding: 0;
	display: block;
	margin-left: auto;
	margin-right: auto;
	border: 0px;
	display: block;
}

body {
	padding: 0;
	margin: 0;
}

.ui-dialog,.ui-button,.ui-button-text .ui-button {
	font-family: '微軟正黑體';
}

.logotextclass {
	text-shadow: 0px 0px 5px black;
	color: #fff4dd;
	font-family: '微軟正黑體';
	font-weight: 700;
	text-decoration: none;
	font-size: 24px;
	z-index: 0;
	position: absolute;
	top: 62px;
	left: 220px;
	opacity: 0.2;
	filter: alpha(opacity =    20);
}

.logotextclass2 {
	text-shadow: 0px 0px 5px black;
	color: #ffdc6a;
	font-family: '微軟正黑體';
	font-weight: 700;
	text-decoration: none;
	font-size: 24px;
	z-index: 0;
	position: absolute;
	top: 62px;
	left: 240px;
	opacity: 1;
	filter: alpha(opacity =    100);
}

.logoclass {
	position: absolute;
	top: 4px;
	left: 0px;
	z-index: 1;
	opacity: 1;
	filter: alpha(opacity =    100);
	width: 233px;
	height: 97px;
}

</style>

<script>
	$(function() {

		$('#logoid').hover(function() {
			// on hovering over, find the element we want to fade *up*
			var fade = $('> div', this);
			// if the element is currently being animated (to a fadeOut)...
			if (fade.is(':animated')) {
				// ...take it's current opacity back up to 1
				fade.stop().fadeTo(250, 1);
			} else {
				// fade in quickly
				fade.fadeIn(250);
			}
		}, function() {
			// on hovering out, fade the element out
			var fade = $('> div', this);
			if (fade.is(':animated')) {
				fade.stop().fadeTo(3000, 0);
			} else {
				// fade away slowly
				fade.fadeOut(3000);
			}
		});

		$(".buttonClass").button();

	/*	$("#logout").click(function() {
			//$( "#option" ).dialog( "open" );
			alert("管理員按下登出鍵");
		});*/

		$("#logo").hover(function() {
			$(".logotextclass").toggleClass("logotextclass2", {duration: 300}).clearQueue();
		});
	
	});

	$(window).load(function() {
		$('iframe.mainContent').attr('height',$(window).height()-100);
	});
	
	$(window).resize(function(){
		  $('iframe.mainContent').attr('height',$(window).height()-100);
	});
	
</script>

</head>

<!-- 
<frameset rows="90,*" frameborder="no">
   <frame src="pages/header.html" name=f1>
　　 <frame src="pages/homepage.html" name=f2>
</frameset>
 -->

<body style="background-color: #ffe6b5;">

		<div class="menu">

			<span id="logo"> <a href="<c:url value="/pages/Content.jsp" />" target=f3
				class="logotextclass">全球第一貓咪社群網站</a> <a
				href="<c:url value="/pages/Content.jsp" />" target=f3><img
					src="<c:url value="/images/logo_large2.png" />" id="logoid" class="logoclass"></a>
			</span>

			<div style="float: right; display: inline-block;">													
					
					<input type="button" id="logout" class="buttonClass afterlogin"
					style="position: absolute; top: 58px; right: 126px; font-size: 14px;" onclick="javascript:location.href='<c:url value="/ManagerLogoutServlet" />'" value="登出">
			</div>
		
		</div>

	<div class="mainContentblock">

		<iframe class="mainContent" src="<c:url value="/pages/ManagerMainContent.jsp" />" name=f2 scrolling="no"></iframe>

	</div>

</body>
</html>