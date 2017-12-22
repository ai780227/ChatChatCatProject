<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>個人頁面</title>

<link rel="stylesheet" href="<c:url value='/css/sunny/fontsize1.1/jquery-ui-1.10.4.custom.css'/>">
<link rel="stylesheet" href="<c:url value='/css/jquery.validate.password.css'/>">

<script>
	var servCtexPath = "<c:url value='/' />";		//本機server path
</script>

<script type="text/javascript" src="<c:url value='/js/jquery-1.10.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.validate.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/messages_zh_TW.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/additional-methods.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.validate.password.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.elastic.source.js'/>"></script>

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
		
		$("#radio").buttonset();
		$(document).tooltip();
		$(".buttonClass, button").button();
		$( ".elastic" ).elastic();
		$('#birthday').datepicker({
			dateFormat : 'yy-mm-dd',
			timeFormat : 'HH:mm:ss',
			showButtonPanel : true,
			changeYear : true,
			closeText : "完成",
			currentText : "今天日期",
			yearRange : "-100:+0"
		});
		$("#personalform").validate({
			success : function(label) {
				label.addClass("success").html("<img src='<c:url value='/images/check.png' />'>");
			},
			rules : {
				selfintro : {
					maxlength : 120
				},
				pic : {
					accept : "image/*"
				},
				name : {
					required : true,
					rangelength : [ 1, 8 ]
				},
				email : {
					required : true,
					email : true,
					maxlength : 50
				}
			}
		});
		$("#summitid").click(function() {
			parent.parent.$().message("個人資料已修改");
		});	
		
		$("#openchangepassword").click(function() {
			parent.parent.$('#changepassword').dialog('open');
		});		
	});
</script>

</head>

<body style="overflow-x:hidden;">

	<form id="personalform" action="<c:url value='/ModifyPersonalInfoServlet.do'/>" method="POST" enctype="multipart/form-data"
		style="position:relative; left: 150px; line-height: 12px; font-size: 16px; font-family: '微軟正黑體'; padding-right: 300px; ">
				<p style="border-bottom:none;">
			<label for="name">名稱：</label>
			<input id="name" type="text" name="name" placeholder="長度限制 1 至 8"
				class="text ui-widget-content ui-corner-all" value="${user.m_name }">
		<!-- 		<button id="openchangepassword">修改密碼</button> -->
				<input id="openchangepassword" type="button" value="修改密碼 " class="buttonClass">
		</p>
		<p>
			<label for="email">Email：</label>
			<input name="email" type="email" id="email" size="30" placeholder="請輸入正確的電子信箱"
				class="text ui-widget-content ui-corner-all" value="${user.m_email }">
		</p>
		
		<div style="color: grey; height:30px; position:relative; top:8px;">（以下欄位可空白）</div>
		<p style="border-bottom:none;">
			性別： <span id="radio"> 
				<input id="male" type="radio" name="gender" value="M" >
				<label for="male">男</label>
				<input id="female" type="radio" name="gender" value="F">
				<label for="female">女</label>
			</span>
		</p>
		
		<!-- input type="file" 加上 multiple attribute使其可以選擇多檔案 -->
		<p style="border-bottom:none;">
			<label for="pic">上傳照片：</label>
			<input type="file" name="pic" id="pic"
				style="width: 300px; font-size: 18px; font-family: '微軟正黑體';">
		</p>
		<p style="border-bottom:none;">
			<label for="selfintro">自我介紹：</label>
			<textarea name="selfintro" id="selfintro" cols="80" rows="3" style="resize: none; max-height:82px;"
				placeholder="來段生動有趣的自我介紹吧！文長限120字"
				class="elastic text ui-widget-content ui-corner-all">${user.m_intro }</textarea>
		</p>
		<p style="border-bottom:none;">
			生日： <input type="text" placeholder="選擇日期" id="birthday" name="birthday" value="${user.m_birth }"
				class="text ui-widget-content ui-corner-all">
		</p>
		<p>${result}</p>
		<p>
			<input id="summitid" type="submit" value="送出 " class="buttonClass">
			<input type="button" value="取消" class="buttonClass" onclick="history.go(-1); return false;">
		</p>
	</form>
	
	
	<script>
		if('${user.m_sex}' != '') {
			if('${user.m_sex}' == 'M') {
				$('#male').attr('checked','checked');
			} else if('${user.m_sex}' == 'F') {
				$('#female').attr('checked','checked');
			}
		}
	</script>
</body>
</html>