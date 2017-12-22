<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8" />
<title>知識貼文框架</title>

<link rel="stylesheet" href="<c:url value='/css/sunny/fontsize1.1/jquery-ui-1.10.4.custom.css'/>">
<link rel="stylesheet" href="<c:url value='/css/sidetag.css'/>">

<script src="<c:url value='/js/jquery-1.10.2.js'/>"></script>
<script src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>
<script src="<c:url value='/js/sidetag.js'/>"></script>

<script>
$(function() {

	$('iframe.iframeclass').attr('height',$(parent.parent.parent.window).height()-130);

	$(window).resize(function(){
		$('iframe.iframeclass').attr('height',$(parent.parent.parent.window).height()-130);
	});

});

</script>

</head>

<body>



<c:if test="${user !=null }">
	<ul class='navigation'>
		<li class="highlight"><a href="<c:url value='knowledge_all.jsp'/>" target=f8>所有</a></li>
		<li><a href="<c:url value='knowledge_public.jsp'/>" target=f8>公開</a></li>
		<li><a href="<c:url value='knowledge_private.jsp'/>" target=f8>好友</a></li>
		<li><a style="top: -50px;" href="<c:url value='knowledge_manual.jsp'/>" target=f8>知識手冊</a></li>
	</ul>
	<iframe class="iframeclass" src="<c:url value='knowledge_all.jsp'/>" name=f8></iframe>
</c:if>
<c:if test="${user ==null }">
	<ul class='navigation'>
		<li class="highlight"><a href="<c:url value='knowledge_all_manager.jsp'/>" target=f8>所有</a></li>
		<li><a style="top: -50px;" href="<c:url value='knowledge_manual.jsp'/>" target=f8>知識手冊</a></li>
	</ul>
	<iframe class="iframeclass" src="<c:url value='knowledge_all_manager.jsp'/>" name=f8></iframe>
</c:if>


</body>

</html>