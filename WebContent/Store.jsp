<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8" />
<title>店家貼文框架</title>

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
		<li class="highlight"><a href="<c:url value='Store_all.jsp'/>" target=f9>所有</a></li>
		<li><a href="<c:url value='Store_public.jsp'/>" target=f9>公開</a></li>
		<li><a href="<c:url value='Store_private.jsp'/>" target=f9>好友</a></li>
		<li><a style="top: -50px;" href="<c:url value='Store_recommend.html'/>" target=f9>推薦店家</a></li>
	</ul>
	<iframe class="iframeclass" src="<c:url value='Store_all.jsp'/>" name=f9></iframe>
</c:if>
<c:if test="${user ==null }">
	<ul class='navigation'>
		<li class="highlight"><a href="<c:url value='Store_all_manager.jsp'/>" target=f9>所有</a></li>
		<li><a style="top: -50px;" href="<c:url value='Store_recommend.html'/>" target=f9>推薦店家</a></li>
	</ul>
	<iframe class="iframeclass" src="<c:url value='Store_all_manager.jsp'/>" name=f9></iframe>
</c:if>


</body>

</html>