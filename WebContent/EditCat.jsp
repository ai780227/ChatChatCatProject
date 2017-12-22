<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>編輯貓咪</title>

<script>
	var servCtexPath = "<c:url value='/' />";
</script>

<link rel="stylesheet" href="<c:url value='/css/sunny/fontsize1.1/jquery-ui-1.10.4.custom.css'/>">
	
<script type="text/javascript" src="<c:url value='/js/jquery-1.10.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.validate.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/messages_zh_TW.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/additional-methods.js'/>"></script>
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
		$("#personalform").validate({
			success : function(label) {
				label.addClass("success").html("<img src='/ChatChatCatProject/images/check.png'>");
			},
			rules : {
				c_intro : {
					maxlength : 120
				},
				pic : {
					accept : "image/*"
				},
				c_name : {
					required : true,
					rangelength : [ 1, 8 ]
				},
				c_breed : {
					maxlength : 10
				}
			}
		});
		$("#summitid").click(function() {
			parent.parent.$().message("已修改貓咪資料");
		});
		
		$( "#slider-year" ).slider({

		      min: new Date().getFullYear()-30,
		      max: new Date().getFullYear(),
		      value: '${cat.c_age }',
		      slide: function( event, ui ) {
		        $( "#c_age" ).val( ui.value );
		      }
		    });
		$( "#c_age" ).val( $( "#slider-year" ).slider( "value" ) );
		    
	});
</script>

</head>

<body style="overflow-x:hidden;">

	<form id="personalform" action="<c:url value='/EditCatInfoServlet.do'/>" method="POST" enctype="multipart/form-data"
		style="position:relative; left: 150px; line-height: 12px; font-size: 16px; font-family: '微軟正黑體'; padding-right: 300px; ">
		<input type="hidden" name="c_id" value="${cat.c_id }" >
		<p>
			<label for="c_name">貓名：</label>
			<input id="c_name" type="text" name="c_name" placeholder="長度限制 1 至 8"
				class="text ui-widget-content ui-corner-all" value="${cat.c_name }">
		</p>
		<div style="color: grey; height:30px; position:relative; top:8px;">（以下欄位可空白）</div>
		<p>
			<label for="c_breed">品種：</label>
			<input name="c_breed" type="text" id="c_breed" size="30" placeholder="若不知道可以輸入「米克思」"
				class="text ui-widget-content ui-corner-all" value="${cat.c_breed }">
		</p>
		
		
		<p style="border-bottom:none;">
			性別： 
			<span id="radio"> 
				<input id="male" type="radio" name="c_sex" value="M">
					<label for="male">公</label> 
				<input id="female" type="radio" name="c_sex" value="F">
					<label for="female">母</label>
			</span>
		</p>
		<!-- input type="file" 加上 multiple attribute使其可以選擇多檔案 -->
		<p style="border-bottom:none;">
			<label for="pic">上傳照片：</label>
			<input type="file" name="pic" id="pic" value="<c:url value='/userImages/${cat.getMember().m_id}/${cat.c_pic_path}'/>"
				style="width: 300px; font-size: 18px; font-family: '微軟正黑體';">
		</p>
		<p style="border-bottom:none;">
			<label for="c_intro">貓咪介紹：</label>
			<textarea name="c_intro" id="c_intro" cols="80" rows="3" style="resize: none; max-height:62px;"
				placeholder="幫你的貓來段生動有趣的自我介紹吧！長度限制120字"
				class="elastic text ui-widget-content ui-corner-all">${cat.c_intro }</textarea>
		</p>
		<p style="border-bottom:none;"> 出生年：
			<input type="text" id="c_age" name="c_age" value="${cat.c_age }"
				 style="border:0; font-size: 16px; font-family: '微軟正黑體'; background:transparent;">
			<span style="border:0; font-size: 16px; font-family: '微軟正黑體';">請滑動桿子選擇年份</span>
		</p>
		<div id="slider-year"></div>
		
		<p>
			<input id="summitid" type="submit" value="送出 " class="buttonClass">
			<input type="button" value="取消" class="buttonClass" onclick="javascript:history.go(-1)">
		</p>
	</form>
	
	<script>
		switch('${cat.c_sex}') {
		case 'M' : $('#male').attr('checked','true');
		break;
		case 'F' : $('#female').attr('checked','true');
		break;
		}
	</script>
	
</body>
</html>