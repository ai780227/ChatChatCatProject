<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>建立新聞</title>

<link rel="stylesheet" href="<c:url value='/css/sunny/fontsize1.1/jquery-ui-1.10.4.custom.css'/>">	

<script type="text/javascript" src="<c:url value='/js/jquery-1.10.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/ckeditor/ckeditor.js'/>"></script>		<!-- text edit -->

<style>
.block {
	position: relative;
	border-style: solid;
	border-color: orange;
	border-radius: 15px;
	margin: 2px;
}

.allpage {
	background-image:url('<c:url value="/images/paperbackground.jpg"/>'); 
	border-width: 10px; border-style:ridge; 
	border-color:rgb(241,206,138); 
	border-radius:15px; 
	padding:5px 35px 35px 35px;
	font-size:'30px';
	font-family:'微軟正黑體';
	
}

label {
	display: inline-block;
	width: 5em;
}

input.text {
	margin-bottom: 12px;
	width: 300px;
	padding: .4em;
	font-size: 14px;
	font-family: '微軟正黑體';
}

</style>

<script>
$(function() {
	$(".buttonClass").button();

	// Replace the <textarea id="editor1"> with a CKEditor
	// instance, using default configuration.
	CKEDITOR.replace( 'editor' );
	CKEDITOR.config.height = 500;
	CKEDITOR.config.width = 600;
});
</script>
</head>

<body>

<div class="allpage">
	<h1 style="text-align:center; text-shadow: 0px 1px 1px #AA8119;">貓咪日報</h1><br>

	<form action="<c:url value='/NewsMaintainServlet.mgr'/>" method='POST'>
		<p><label for="title">標題:</label>
			<input type='text' name='title' id="title" value='${param.title }'
				placeholder="請輸入新聞標題"
				class="text ui-widget-content ui-corner-all"/>
			<span class="error">${errorMsg.errorTitleEmpty }</span>
		</p>
		<textarea name="editor" id="editor" rows="1000px" cols="500px">
		    ${param.editor1 }
		</textarea><span class="error">${errorMsg.errorContentEmpty }</span>
		<p><label for="source">來源:</label>
			<input type='text' name='source' id="source" value='${param.source }'
				placeholder="請輸入新聞來源"
				class="text ui-widget-content ui-corner-all">
			<span class="error">${errorMsg.errorSourceEmpty }</span>
		</p>
		<p><label for="link">來源連結:</label>
			<input type='text' name='link' id="link" value='${param.link }'
				class="text ui-widget-content ui-corner-all">
		</p>
		
		<input type='submit' class="buttonClass" value='送出'>
		<input type='submit' class="buttonClass" value='取消' onclick="history.go(-1); return false;"><br>
	
		<script>
		</script>
	</form>
</div>



<script>


</script>
<!-- 
<c:forEach items="${newsList }" var="newsBean" varStatus="varStatus">
<div id="News_id_${newsBean.news_id }">
	Title: <span id="title_${newsBean.news_id }">${newsBean.news_title }</span><input type="button" id="edit_${newsBean.news_id }" value="edit"><br>
	Content: 
	<div id="content_${newsBean.news_id }">${newsBean.news_content }</div>
	Source: <span id="source_${newsBean.news_id }">${newsBean.news_source }</span><br>
	Link: <span id="link_${newsBean.news_id }">${newsBean.news_link }</span><br>
	Time:${newsBean.news_time }<br>
</div>
 	<c:if test="${!varStatus.last }"><br>
		<hr>
	</c:if> 
</c:forEach>
 -->
</body>

</html>
