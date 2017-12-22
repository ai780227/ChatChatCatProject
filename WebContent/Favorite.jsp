<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8" />
<title>我的最愛</title>
<link rel="stylesheet" href="<c:url value='/css/newspaper/jquery-ui-1.10.4.custom.css'/>">
<link rel="stylesheet" href="<c:url value='/css/post.css'/>">

<script src="<c:url value='/js/jquery-1.10.2.js'/>"></script>
<script src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>
<script src="<c:url value='/js/jquery.elastic.source.js'/>"></script>

<style>
body {
	overflow-x:hidden;
}
</style>

<script>

$(function() {
		$( ".elastic" ).elastic();
});

</script>

</head>

<body>
<input type="hidden" id="btn_submit">
<h1>我的最愛</h1>
<div class="post"></div>

<script type="text/javascript">
var page = 0, size = 5;
var user = "${user}";
var user_id = "${user.getM_id()}";
var manager = "${manager}";
var ViewFavURL = "<c:url value='/ViewFavoritePostsServlet.do'/>";
var LikeURL = "<c:url value='/LikePostServlet.do'/>";
var ResponseURL = "<c:url value='/CreateResponseServlet.do'/>";
var AccuseURL = "<c:url value='/pages/NewReport.jsp'/>";
var imgURL = "<c:url value='/'/>";
var DelResURL = "<c:url value='/DeleteResponseServlet.do'/>";
</script>
<script src="<c:url value='/js/favorite.js'/>"></script>


</body>

</html>