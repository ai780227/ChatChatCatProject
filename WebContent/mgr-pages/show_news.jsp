<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<c:url value='/js/jquery-2.1.0.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/ckeditor/ckeditor.js'/>"></script>
</head>
<body>

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

<script>
	$('input[id^="edit_"]').bind('click',function() {
								var edit_btn_id = $(this).attr('id').length;
								var news_id = $(this).attr('id').substr(5,edit_btn_id-1);	//取得news id
								var title = $('#title_' + news_id).html();
								var content = $('#content_' + news_id).html();
								var source = $('#source_' + news_id).html();
								var link = $('#link_' + news_id).html();
								var original = $('#News_id_' + news_id).html();
								$('#News_id_' + news_id).html('');	//先清空div內的資訊
								//生成一個edit text form
								$('#News_id_' + news_id).append('<form action="<c:url value="/NewsMaintain.mgr"/>" method="POST">'
																+'Title: <input name="title" id="title_'+news_id+'" type="text"><br>'
																+'Content:<br> <textarea name="editor" id="editor_'+news_id+'" rows="1000px" cols="500px"></textarea>'
																+'Source: <input name="source" id="source_'+news_id+'" type="text"><br>'
																+'Link: <input name="link" id="link_'+news_id+'" type="text"><br>'
																+'<input type="submit" value="送出"><br><input type="hidden" name="id" id="id_'+news_id+'">');
								$('#News_id_' + news_id).append('</form>');
								$('#title_'+ news_id).val(title);
								$('#editor_'+ news_id).val(content);
								$('#source_'+ news_id).val(source);
								$('#link_'+ news_id).val(link);
								$('#id_'+ news_id).val(news_id);
								CKEDITOR.replace( 'editor_' + news_id );
								CKEDITOR.config.height = 500;
								CKEDITOR.config.width = 800;
						});
</script>

</body>
</html>