<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>註冊視窗</title>
<script>
	var servCtexPath = "<c:url value='/' />";
</script>

<link rel="stylesheet" href="<c:url value='/css/newspaper/jquery-ui-1.10.4.custom.css'/>">
<link rel="stylesheet" href="<c:url value='/css/jquery.validate.password.css'/>">

<script type="text/javascript" src="<c:url value='/js/jquery-1.10.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.validate.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/messages_zh_TW.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.validate.password.js'/>"></script>

<script type="text/javascript" src="<c:url value='/js/additional-methods.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.elastic.source.js'/>"></script>

<style>
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

label.error {
	font-size: 16px;
	width: auto;
}

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

.padding1 {
	padding: 10px 0px;	
	width: 500px;
}
</style>

<script>
	$(function() {
		// '/ChatChatCatProject/'
		var servletCtxPath = '<c:url value="/" />'; 
		
		$(document).tooltip();
		$(".buttonClass").button();
		
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
		$("#radio").buttonset();
		
		$("#signupform").validate({
			success : function(label) {
				label.addClass("success").html("<img src='<c:url value='/images/check.png' />'>");
			},
			rules : {
				username : {
					required : true,
					rangelength : [ 5, 12 ],
					remote : servletCtxPath + "AccountCheckServlet?check=username" + $('#username').val()
				},
				pwd : {
					maxlength : 18
				},
				pwd1 : {
					equalTo: "#password"
				},
				name : {
					required : true,
					rangelength : [ 1, 8 ]
				},
				email : {
					required : true,
					email : true,
					maxlength : 50,
					remote : servletCtxPath + "AccountCheckServlet?check=email" + $('#email').val()
				},
				
				
				selfintro : {
					maxlength : 120
				},
				pic : {
					accept : "image/*"
				}
			}
		});
	});

//function chkEmail() {
//		var theEmail = document.getElementById("idEmail").value;
//		var re = /^\S+@\S+\.\S{2,3}$/;
//		//var re = new RegExp("/^\\S+@\\S+\\.\\S{2,3}$/")
//		if (!(re.test(theEmail))) {
//			alert("Email格式錯誤");
//		}
//	} 
/*
	$(function() {
		//帳號、信箱ajax驗證
		$('#username').bind('blur',function() {
						if($('#username').val()!='') {
							$.ajax( {'url'		:'<c:url value="/AccountCheckServlet"/>',
									 'type'		:'get',
									 'data'		:{'check' :'m_id',
										 	  	  'm_id'  :$('#m_id').val()},
									 'datatype'	:'text',
									 'success'	:function(data) {
									 				if(data == "此帳號可使用")
														$('#checkId').text(data).css('color','green');
									 				else
														$('#checkId').text(data).css('color','red');
								 				}
									} );
						}
				});
		$('#email').bind('blur',function() {
						if($('#email').val()!='') {
							$.ajax( {'url'		:'<c:url value="/AccountCheckServlet"/>',
									 'type'		:'get',
									 'data'		:{'check'	 :'m_eamil',
												  'm_eamil'  :$('#m_email').val()},
									 'datatype'	:'text',
									 'success'	:function(data) {
											 		if(data == "此信箱可使用")
														$('#checkEmail').text(data).css('color','green');
											 		else
														$('#checkEmail').text(data).css('color','red');
										 		}
									} );
						}
				});				
	});*/
	$(function() {
		$('#page2_submit').bind('click',function() {
			$('#signupform').submit();
		});		
	});
</script>

</head>

<body>

	<form id="signupform" action="<c:url value='/RegisterServlet'/>" method="POST" enctype="multipart/form-data"
		style="line-height: 24px; font-size: 18px; font-family: '微軟正黑體';">
		
		<!-- 第一頁 -->
		<div id="page1div" style="position:absolute; left:50px;">
			<p>
				<label for="username">帳號：</label><input id="username" type="text"
					name="username" placeholder="長度限制 5 至 12 "
					class="text ui-widget-content ui-corner-all">
			</p>
			<div style="position: relative;"><label for="password">密碼：</label><input id="password"
				style="width: 150px;" type="password" name="pwd"
				placeholder="必須包含英、數"
				class="text ui-widget-content ui-corner-all passwordvalidate">
			<div class="password-meter"
				style="position: relative; left: 98px; top: -5px; z-index: -1;">
				<div class="password-meter-message"
					style="position: absolute; left: 0px; top: 3px;"></div>
				<div class="password-meter-bg">
					<div class="password-meter-bar"></div>
				</div>
			</div>
			</div><br/>
			<p>
				<label for="password1">確認密碼：</label><input id="password1"
					type="password" name="pwd1" placeholder="請重新輸入密碼"
					class="text ui-widget-content ui-corner-all">
			</p>
			<p>
				<label for="name">名稱：</label><input id="name" type="text" name="name"
					placeholder="長度限制 1 至 8"
					class="text ui-widget-content ui-corner-all">
			</p>
			<p>
				<label for="email">Email：</label><input name="email" type="email"
					id="email" size="30" placeholder="請輸入正確的電子信箱"
					class="text ui-widget-content ui-corner-all">
			</p>
			
			<div style="position:absolute;">
			<a href="#" onclick="
			$('#page1div').animate({ 'left': '-=600px' }, 'slow' );
			$('#page2div').animate({ 'left': '-=600px' }, 'slow' );
			return false;
			"><img src="../images/sidebar-arrow-right.png" style="opacity: 0.5; position: absolute; right: -460px; top: -280px;"></a>
			<img src="../images/signupintro_right.png" style="opacity: 0.5; position: absolute; right: -460px; top: -180px;">
			</div>
			
			
			<input type="submit" id="page1_submit" value="送出 " class="buttonClass"> 
			<input type="button" value="取消" class="buttonClass" onclick="parent.$('#signup').dialog('close');"/>
		</div>
		
		<!-- 第二頁 -->
		<div id="page2div" style="position:absolute; left:720px;">		
			<div style="position:absolute;">
				<a href="#" onclick="$('#page1div').animate({ 'left': '+=600px' }, 'slow' );$('#page2div').animate({ 'left': '+=600px' }, 'slow' );return false;">
					<img src="../images/sidebar-arrow-left.png" style="opacity: 0.5; position: absolute; right: 50px; top: 80px;"></a>
				<img src="../images/signupintro_left.png" style="opacity: 0.5; position: absolute; right: 10px; top: 185px;">
			</div>
			
			<div style="color: grey; height:30px; position:relative; top:8px;">（以下欄位可空白）</div>
			<p style="border-bottom:none;">
				性別： <span id="radio">
							<input id="male" type="radio" name="gender" value="M">
								<label for="male">男</label> 
							<input id="female" type="radio" name="gender" value="F">
								<label for="female">女</label>
				</span>
			</p>
			<!-- input type="file" 加上 multiple attribute使其可以選擇多檔案 -->
			<p class="padding1">
				<label for="pic">上傳照片：</label>
					<input type="file" name="pic" id="pic"
							style="width:200px; font-size: 18px; font-family: '微軟正黑體';">
			</p>
			<p class="padding1">
				<label for="selfintro">自我介紹：</label>
				<textarea name="selfintro" id="selfintro" cols="80" rows="3" style="resize: none; max-height:82px;"
					placeholder="來段生動有趣的自我介紹吧！文長限120字"
					class="elastic text ui-widget-content ui-corner-all"></textarea>
			</p>
			<p class="padding1">
				生日： <input type="text" placeholder="選擇日期" id="birthday" name="birthday"
							class="text ui-widget-content ui-corner-all">
			</p>
			<p>
			<input type="button" id="page2_submit" value="送出 " class="buttonClass" > 
			<input type="button" value="取消" class="buttonClass" onclick="parent.$('#signup').dialog('close');"/>
		</div>
		
		<!-- 
		String m_sex = request.getParameter("m_sex");
		String m_birth = request.getParameter("m_birth");
		String m_intro = request.getParameter("m_intro");
		String m_pic_path = request.getParameter("m_pic_path");
		String m_cat = request.getParameter("m_cat");
		 -->
	</form>

</body>
</html>