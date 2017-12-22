<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>所有貼文框架</title>

<link rel="stylesheet" href="<c:url value='/css/sunny/fontsize1.1/jquery-ui-1.10.4.custom.css'/>">	

<script type="text/javascript" src="<c:url value='/js/jquery-1.10.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>

<style>
.newsclass {
	position: absolute;
	top: 0px;
	left: 36px;
	width: 801px;
	frameBorder: 0;
	border: 0;
	cellspacing: 0;
}
</style>

<script>
$(function() {
	
	$('iframe.newsclass').attr('height',$(parent.parent.parent.window).height()-130);
	
	$(window).resize(function(){
		$('iframe.newsclass').attr('height',$(parent.parent.parent.window).height()-130);
	});
	
});

</script>

</head>

<body>
	<!-- border-top-style:solid; 若要上邊框，這行放入body的style內 -->
<!-- 
	<iframe class="newsclass" src="News_content.jsp" name=f6></iframe>
 -->	
	<iframe class="newsclass" src="<c:url value='/ShowNewsServlet'/>" name=f6></iframe>

</body>
</html>