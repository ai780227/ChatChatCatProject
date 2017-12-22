<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8" />
<title>誰按讚頁面</title>

<link rel="stylesheet" href="<c:url value='/css/sunny/fontsize1.1/jquery-ui-1.10.4.custom.css'/>">

<script src="<c:url value='/js/jquery-1.10.2.js'/>"></script>
<script src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>

<script>
	$(function() {
		var post_id = window.location.search.slice(9);

		if (post_id != "") {
			if (post_id != null) {
				$.ajax({
					"url" : "<c:url value='/GetWhoLikeServlet.do'/>",
					"type" : "post",
					"data" : {
						"post_id" : post_id},
					"datatype" : "text",
					"success" : function(data) {
						var json = eval('(' + data + ')');
						if (json.whoLikes != null) {
							for (var i = 0; i < json.whoLikes.length; i++) {
								$("#whoLikeId").append(json.whoLikes[i].m_name + "<br>");
							}
						}
					}
				});
			}
		}
	});

</script>

<style>

</style>

</head>

<body>
	<h1>誰按讚列表</h1>
	<div id="whoLikeId"></div>
</body>

</html>
