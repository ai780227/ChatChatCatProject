<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主內容畫面</title>

<script src="<c:url value='/js/jquery-1.10.2.js'/>"></script>

<style>
.content {
	width: 837px;
	frameBorder: 0px;
	border: 0px;
	cellspacing: 0px;
	marginwidth: 0px;
	marginheight: 0px;
	display: inline;
	margin:0;
	overflow-x:hidden;
    overflow-y:auto;
}

.rightsidebar {
	width: 255px;
	frameBorder: 0;
	border: 0;
	cellspacing: 0;
	overflow: hidden;
	display: inline;
	margin:0;
}

body {
	padding: 0;
	margin: 0;
}
</style>

<script>

$(function() {
	
  	$('iframe.content, iframe.rightsidebar').attr('height',$(parent.window).height()-100);

	$(window).resize(function(){
	  	$('iframe.content, iframe.rightsidebar').attr('height',$(parent.window).height()-100);
	});

});

</script>
</head>
<!-- 
<frameset cols="1000,200" frameborder="no">
   <frame src="01.html" name=f3>
　　 <frame src="02.html" name=f4>
</frameset>
 -->

<body style="background-color: #ffe6b5;">
<div style="display:inline; text-align:left">
<span>
		<iframe class="content" height=0 src="Content.jsp" name=f3></iframe> 
</span>
</div>
<div style="display:inline; text-align:right">
<span>
		<iframe class="rightsidebar" height=0 src="<c:url value='/pages/ManagerRightSideBar.jsp'/>" name=f4 scrolling="no"></iframe>
</span>
</div>
</body>


</html>
