<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8" />
<title>新貼文</title>

<link rel="stylesheet" href="<c:url value='/css/sunny/fontsize1.1/jquery-ui-1.10.4.custom.css'/>">

<script src="<c:url value='/js/jquery-1.10.2.js'/>"></script>
<script src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>
<script src="<c:url value='/js/jquery.elastic.source.js'/>"></script>

<style>
input.text,input.file,textarea {
	margin-bottom: 12px;
	width: 235px;
	padding: .4em;
	font-size: 14px;
	font-family: '微軟正黑體';
}

button.ui-datepicker-current {
	display: none;
}

p{
	border-style: solid;
	border-color: grey;
	border-width: thin;
	padding:10px;
	margin:-1px;
	border-radius: 15px;
}
</style>

<script>
	$(function() {
		$("#summitid").hide();
		$(".buttonClass, button").button();
		$( ".elastic" ).elastic();
		$(document).keyup(function(){
				if($("#selfintro").val() != "") {
					$("#summitid").show();
				}
				else {
					$("#summitid").hide();
				}
			});

		$("#summitid").click(function() {
			var string = $("textarea").val();
			string = string.replace(/\r\n/g, '<br>');
			string = string.replace(/(\n|\r)/g, '<br>');
			string = string.replace(/\s/g, "&nbsp;");
			$("textarea").val(string);
			$('#newpostform').submit();
			parent.$().message("貼文已送出");
			parent.$('#newpost').dialog('close');
			parent.location.href=parent.location.href;
		});

		$('#public').attr('checked', 'checked');
		$('#life').attr('checked', 'checked');
		$(".radioclass").buttonset();
	});

</script>

</head>

<body style="overflow-x:hidden;">

	<form id="newpostform" action="<c:url value='/CreatePostServlet.do'/>" enctype="multipart/form-data" method="post"
		style="position:relative; line-height: 12px; font-size: 16px; font-family: '微軟正黑體'; padding-: 10px; ">

		<p style="border-bottom:none;">
			性質： <span class="radioclass">
			<input id="public" type="radio" name="post_property" value="1"><label for="public">公開</label>
			<input id="private" type="radio" name="post_property" value="2"><label for="private">私人</label>
			</span>
		</p>
		<p style="border-bottom:none;">
			類型： <span class="radioclass">
			<input id="life" type="radio" name="post_type" value="1"><label for="life">生活</label>
			<input id="knowledge" type="radio" name="post_type" value="2"><label for="knowledge">知識</label>
			<input id="store" type="radio" name="post_type" value="4"><label for="store">店家</label>
			<input id="adopt" type="radio" name="post_type" value="3"><label for="adopt">領養</label>
			</span>
		</p>
		<p style="border-bottom:none;">
			<label for="selfintro">內容：</label>
			<textarea name="post_content" id="selfintro" cols="80" rows="3" style="resize: none; max-height:282px; width: 400px;"
				placeholder="請輸入貼文內容，請放心不限字數。輸入框會隨內容伸展。"
				class="elastic text ui-widget-content ui-corner-all"></textarea>
		</p>
		<!-- input type="file" 加上 multiple attribute使其可以選擇多檔案 -->
		<p style="border-bottom:none;">
			<label for="uploadphoto">上傳圖片：</label><input type="file"
				name="pic" id="uploadphoto" multiple
				style="width: 300px; font-size: 18px; font-family: '微軟正黑體';">
		</p>
		<p>
			<input id="summitid" type="submit" value="送出 " class="buttonClass">
			<input type="reset" value="重填" class="buttonClass">
		</p>
	</form>

</body>

</html>