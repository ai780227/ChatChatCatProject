<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8" />
<title>新檢舉</title>

<link rel="stylesheet" href="<c:url value='/css/sunny/fontsize1.1/jquery-ui-1.10.4.custom.css'/>">

<script src="<c:url value='/js/jquery-1.10.2.js'/>"></script>
<script src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>
<script src="<c:url value='/js/jquery.validate.js'/>"></script>
<script src="<c:url value='/js/messages_zh_TW.js'/>"></script>

<style>
div {
	padding-top: .4em;
	padding-bottom: .4em;
	font-size: 28px;
	font-family: '微軟正黑體';
	font-weight: bold;
}
</style>

<script>
	$(function() {
		$("button").button();

		var post_id = window.location.search.slice(9);
		$("#deletePostId").val(post_id);


		$("#yes").click(function() {
			$('#yes').submit();
			parent.$().message("您已刪除貼文！");
			parent.$('#deleteconfirm').dialog('close');
			parent.location.reload();
		});
	});


</script>

</head>

<body>

<div>是否刪除？</div>
<div style="text-align: right;">
<form action="<c:url value='/DeletePostServlet.do'/>">
<input type="hidden" name="post_id" id="deletePostId" value="${post_id}">
<button id="yes">確定</button>
<button id="no" onclick="parent.$('#deleteconfirm').dialog('close');">取消</button>
</form>
</div>

</body>

</html>