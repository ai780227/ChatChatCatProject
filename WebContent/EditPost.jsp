<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8" />
<title>編輯貼文</title>

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

.blockstyle {
	border-style: solid;
	border-color: grey;
	border-width: thin;
	padding:10px;
	margin:-1px;
	border-radius: 15px;
}

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
</style>

<script>
	$(function() {

		$( ".elastic" ).elastic();

	//	$('#public').attr('checked', 'checked');
	//	$('#life').attr('checked', 'checked');

		var post_id = window.location.search.slice(9);
		$("#editPostId").val(post_id);

		if (post_id != "") {
			if (post_id != null) {
				$.ajax({
					"url" : "<c:url value='/ViewAPostServlet.do'/>",
					"type" : "post",
					"data" : {
						"post_id" : post_id},
					"datatype" : "text",
					"success" : function(data) {
						var json = eval('(' + data + ')');
						var content = json.post.post_content;
						while (content.indexOf("<br>",0) != -1 ){
							content = content.replace("<br>", "\n");
						}
						while (content.indexOf("&nbsp;",0) != -1 ){
							content = content.replace("&nbsp;", " ");
						}
						$("#selfintro").val(content);
						if (json.post.post_property == '1') {
							$('#public').attr('checked', 'checked');
						} else if (json.post.post_property == '2') {
							$('#private').attr('checked', 'checked');
						}
						if(json.post.post_type == '1') {
							$('#life').attr('checked', 'checked');
						} else if (json.post.post_type == '2') {
							$('#knowledge').attr('checked', 'checked');
						} else if (json.post.post_type == '3') {
							$('#adopt').attr('checked', 'checked');
						} else if (json.post.post_type == '4') {
							$('#store').attr('checked', 'checked');
						} else if (json.post.post_type == '5') {
							// nothing
						}
						if (json.pics != null) {
							for (var i = 0; i < json.pics.length; i++) {
								$(".blockstyle").append(
										"<div style=\"display:inline-block; position:relative;\">" +
										"<img src=\"<c:url value='/userImages/" + json.pics[i].m_id + "/" + json.pics[i].pic_file_path + "'/>\" alt=\"Image 1\" width=\"120\" height=\"120\" class=\"nowimg\"/>" +
											"<a href=\"#\"><img src=\"<c:url value='/images/delete.png'/>\" class=\"iconinvisible\"></a>" +
									"</div>");
							}
						}
						$(".radioclass").buttonset();

						$(".buttonClass, button").button();

						$(".nowimg").hover(function(){
							$(this).parent().find(".iconinvisible").toggleClass("iconvisible");
						});

						$(".iconinvisible").hover(function(){
							$(this).toggleClass("iconvisible");
						});

						$(".blockstyle a").click(function(){
							return false;
						});
					}
				});
			}
		}

		$("#summitid").click(function() {
			var string = $("textarea").val();
			string = string.replace(/\n/g, '<br>');
			string = string.replace(/(\n|\r)/g, '<br>');
			string = string.replace(/\s/g, "&nbsp;");
			$("textarea").val(string);
			$('#editpostform').submit();
			parent.$().message("您已編輯貼文！");
			parent.$('#editpost').dialog('close');
			//document.location.assign("<c:url value='/pages/All_all.jsp'/>");
			//window.location.replace("All_all.jsp").reload();
			parent.location.href=parent.location.href;
		});
	});
</script>

</head>

<body style="overflow-x:hidden;">

	<form id="editpostform" action="<c:url value='/EditPostServlet.do'/>" enctype="multipart/form-data" method="post"
		style="position:relative; line-height: 12px; font-size: 16px; font-family: '微軟正黑體'; padding-: 10px; ">
	<input type="hidden" name="post_id" id="editPostId" value="${post_id}">
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
			<textarea name="post_content" id="selfintro" cols="80" rows="3" style="resize: none; max-height:132px; width: 400px;"
				placeholder="請輸入貼文內容，請放心不限字數。輸入框會隨內容伸展。"
				class="elastic text ui-widget-content ui-corner-all"></textarea>
		</p>
		<div style="border-bottom:none;" class="blockstyle">
			<label for="selfintro">現有圖片：</label>

		<%-- 	<div style="display:inline-block; position:relative;">
				<img src="<c:url value='../images/cat1.jpg'/>" alt="Image 1" width="120" height="120" class="nowimg"/>
					<a href="#"><img src="<c:url value='../images/delete.png'/>" class="iconinvisible"></a>
			</div> --%>

		</div>
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