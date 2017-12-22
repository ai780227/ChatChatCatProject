<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新聞管理頁面</title>

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
.iconinvisible{
	position: absolute;
	top:5px;
	right:5px;
	opacity: 0;
	filter: alpha(opacity =    0);
}
.iconvisible{
	opacity: 1;
	filter: alpha(opacity =    100);
}
input.text {
	margin-bottom: 12px;
	width: 200px;
	padding: .4em;
	font-size: 16px;
	font-family: '微軟正黑體';
}

.iconinvisible2{
	position: relative;
	top:10px;
	right:5px;
	opacity: 0.2;
	filter: alpha(opacity =    20);
}

.iconinvisible2:hover{
	opacity: 1;
	filter: alpha(opacity =    100);
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
	$(".block").hover(function(){
		$(".iconinvisible").toggleClass("iconvisible",300).clearQueue();
	});	
	
    $( "#accordion" ).accordion({
      collapsible: true,
      heightStyle: "content",
      active: false
    });
    
    
  //編輯新聞

    $('body').on('click','img[id^="edit_"]',function() {
    	var edit_btn_id = $(this).attr('id').length;
    	var news_id = $(this).attr('id').substr(5,edit_btn_id-1);	//取得news id
    	var title = $('#title_' + news_id).text();
    	var content = $('#content_' + news_id).html();
    	var source = $('#source_' + news_id).html();
    	var link = $('#link_' + news_id).html();
    	var orignalHtml = $('#News_id_' + news_id).html();		//先記錄原先內容	
    	$('#News_id_' + news_id).html('');	//先清空div內的資訊
    	//生成一個edit text form
    	$('#News_id_' + news_id).append('<form action="<c:url value="/NewsMaintainServlet.mgr"/>" method="POST">'
    									+'<p><label for="title_'+news_id+'">Title:</labal><input name="title" value="'+ title + '" id="title_'+news_id+'" type="text"/><p>'
    									+'<label for="editor_'+news_id+'">Content:</labal><br> <textarea name="editor" id="editor_'+news_id+'" rows="1000px" cols="500px"></textarea>'
    									+'<p><label for="source_'+news_id+'">Source:</labal> <input name="source" id="source_'+news_id+'" type="text"/><p>'
    									+'<p><label for="link_'+news_id+'">Link:</labal> <input name="link" id="link_'+news_id+'" type="text" size="50px"/><p>'
    									+'<input type="submit" value="送出"><input name="cancel" type="button" value="取消">'
    									+'<input type="hidden" name="id" value="id_'+news_id+'" id="id_'+news_id+'">'
										+'</form>');
  //  	$('#title_'+ news_id).val(title);
    	$('#editor_'+ news_id).val(content);
    	$('#source_'+ news_id).val(source);
    	$('#link_'+ news_id).val(link);
    	$('#id_'+ news_id).val(news_id);
    	CKEDITOR.replace( 'editor_' + news_id );
    	CKEDITOR.config.height = 500;
    	CKEDITOR.config.width = 600;
    	
    	$('input[name="cancel"]').bind('click',function() {
    		$('#News_id_' + news_id).html(orignalHtml);
    	});
    });
    
    
});
    

</script>

</head>

<body>

<div style="background-image:url('<c:url value="/images/paperbackground.jpg"/>'); border-width: 10px; border-style:ridge; border-color:rgb(241,206,138); border-radius:15px; padding:5px 35px 35px 35px;">
	<h1 style="text-align:center; text-shadow: 0px 1px 1px #AA8119;">
		貓咪日報
		<div style="display:inline-block;float:right;">
			<a style="font-size:16px;" class="buttonClass" href="<c:url value='/pages/CreateNews.jsp'/>">新增新聞</a>
		</div>
	</h1><br>

	<div id="accordion">
	
		<c:forEach items="${newsList }" var="newsBean" varStatus="varStatus">
			<h3 id="title_${newsBean.news_id }">${newsBean.news_title }</h3>
			<div id="News_id_${newsBean.news_id }">
				<div style="text-align:right;"><img id="edit_${newsBean.news_id }" src="<c:url value='/images/edit.png'/>" class="iconinvisible2"></div>
				<div id="content_${newsBean.news_id }">${newsBean.news_content }</div>
				<p>來源: <span id="source_${newsBean.news_id }">${newsBean.news_source }</span></p>
				<p>連結: <span><a id="link_${newsBean.news_id }" href="${newsBean.news_link }" target="_blank">${newsBean.news_link }</a></span></p>
				<p>時間: ${fn:substring(newsBean.news_time,0,16)}</p>
				
			</div>
		</c:forEach>
	
	</div>
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
