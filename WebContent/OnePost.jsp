<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8" />
<title>貼文頁面</title>
<link rel="stylesheet" href="<c:url value='/css/newspaper/jquery-ui-1.10.4.custom.css'/>">
<link rel="stylesheet" href="<c:url value='/css/post.css'/>">

<script src="<c:url value='/js/jquery-1.10.2.js'/>"></script>
<script src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>
<script src="<c:url value='/js/jquery.elastic.source.js'/>"></script>

</head>

<body style="padding-top: 6px;">



<div class="post"></div>


<script type="text/javascript">
var post_id = window.location.search.slice(9);
var page = 0;
var user = "${user}";
var user_id = "${user.getM_id()}";
var manager = "${manager}";
var PostURL = "<c:url value='/ViewAPostServlet.do'/>";
var LikeURL = "<c:url value='/LikePostServlet.do'/>";
var FavoriteURL = "<c:url value='/AddPostToFavoriteServlet.do'/>";
var ResponseURL = "<c:url value='/CreateResponseServlet.do'/>";
var AccuseURL = "<c:url value='/pages/NewReport.jsp'/>";
var imgURL = "<c:url value='/'/>";
var DelResURL = "<c:url value='/DeleteResponseServlet.do'/>";
$(".buttonClass").button();
</script>
<script src="<c:url value='/js/apost.js'/>"></script>

</body>
</html>