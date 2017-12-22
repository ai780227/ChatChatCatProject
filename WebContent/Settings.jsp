<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>設定</title>

<link rel="stylesheet" href="<c:url value='/css/sunny/fontsize1.1/jquery-ui-1.10.4.custom.css'/>">
	
<script type="text/javascript" src="<c:url value='/js/jquery-1.10.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>

<script>
	$(function() {
		$(".buttonClass").button();
		
		
/*		$('#birth_on').attr('checked', 'checked');
		$('#email_friend').attr('checked', 'checked');
		$('#intro_on').attr('checked', 'checked');
		$('#sex_off').attr('checked', 'checked');
		$('#post_friend').attr('checked', 'checked');
		$('#res_on').attr('checked', 'checked');
		$('#act_on').attr('checked', 'checked');
		$('#theme_2').attr('checked', 'checked');*/
		$("p > span").buttonset().css('font-size', '14px');
		
	/*	$("p input").click(function() {
			parent.$().message("設定已儲存");
		});*/		
		$("#setting-form").submit(function() {
			parent.$().message("設定已儲存");
		});		
		
	});
</script>

</head>

<body>

	<form id="setting-form" action="<c:url value='/AccountSettingsServlet.do'/>" method="POST"
		style="font-size: 16px; font-family: '微軟正黑體'; text-align: center; line-height: 44px;">
		
		<p>
			生日：<span> 
					<input id="birth_on" type="radio" name="cf_birth" value="1">
						<label for="birth_on">公開</label> 
					<input id="birth_friend" type="radio" name="cf_birth" value="2">
						<label for="birth_friend">好友</label> 
					<input id="birth_off" type="radio" name="cf_birth" value="0">
						<label for="birth_off">不公開</label>
				</span>
		</p>
		<p>
			email：<span> 
					<input id="email_on" type="radio" name="cf_email" value="1">
						<label for="email_on">公開</label> 
					<input id="email_friend" type="radio" name="cf_email" value="2">
						<label for="email_friend">好友</label> 
					<input id="email_off" type="radio" name="cf_email" value="0">
						<label for="email_off">不公開</label>
				</span>
		</p>
		<p>
			簡介：<span> 
					<input id="intro_on" type="radio" name="cf_intro" value="1">
						<label for="intro_on">公開</label> 
					<input id="intro_friend" type="radio" name="cf_intro" value="2">
						<label for="intro_friend">好友</label>
					<input id="intro_off" type="radio" name="cf_intro" value="0">
						<label for="intro_off">不公開</label>
				</span>
		</p>
		<p>
			性別：<span> 
					<input id="sex_on" type="radio" name="cf_sex" value="1">
						<label for="sex_on">公開</label> 
					<input id="sex_friend" type="radio" name="cf_sex" value="2">
						<label for="sex_friend">好友</label> 
					<input id="sex_off" type="radio" name="cf_sex" value="0">
						<label for="sex_off">不公開</label>
				</span>
		</p>
		<p>
			預設貼文屬性：
				<span> 
					<input id="post_on" type="radio" name="cf_post" value="1">
						<label for="post_on">公開</label> 
					<input id="post_friend" type="radio" name="cf_post" value="2">
						<label for="post_friend">好友</label>
				</span>
		</p>
		<p>
			回覆通知：
				<span> 
					<input id="res_on" type="radio" name="cf_res" value="0">
						<label for="res_on">提醒</label> 
					<input id="res_off" type="radio" name="cf_res" value="1">
						<label for="res_off">不提醒</label>
				</span>
		</p>
		<p>
			活動通知：
				<span> 
					<input id="act_on" type="radio" name="cf_act" value="0">
						<label for="act_on">提醒</label> 
					<input id="act_off" type="radio" name="cf_act" value="1">
						<label for="act_off">不提醒</label>
				</span>
		</p>
		<p style="display:none;">
			佈景主題：
				<span> 
					<input id="theme_1" type="radio" name="cf_theme" value="1">
						<label for="theme_1">主題１</label> 
					<input id="theme_2" type="radio" name="cf_theme" value="2">
						<label for="theme_2">主題２</label> 
					<input id="theme_3" type="radio" name="cf_theme" value="3">
						<label for="theme_3">主題３</label>
				</span>
		</p>
		
		<p>
			<input type="submit" value="送出 " class="buttonClass" > 
			<input type="button" value="取消" class="buttonClass" onclick="parent.$('#option').dialog('close');"/>
		</p>
	</form>
	
<script>
	//checked
	//cf_birth cf_email cf_intro cf_sex cf_post cf_res cf_act cf_theme
	switch ('${user.cf_birth}') {
		case '0' : $('#birth_off').attr('checked', 'checked');
		break;
		case '1' : $('#birth_on').attr('checked', 'checked');
		break;
		case '2' : $('#birth_friend').attr('checked', 'checked');
		break;
	}
	switch ('${user.cf_email}') {
		case '0' : $('#email_off').attr('checked', 'checked');
		break;
		case '1' : $('#email_on').attr('checked', 'checked');
		break;
		case '2' : $('#email_friend').attr('checked', 'checked');
		break;
	}
	switch ('${user.cf_intro}') {
		case '0' : $('#intro_off').attr('checked', 'checked');
		break;
		case '1' : $('#intro_on').attr('checked', 'checked');
		break;
		case '2' : $('#intro_friend').attr('checked', 'checked');
		break;
	}
	switch ('${user.cf_sex}') {
		case '0' : $('#sex_off').attr('checked', 'checked');
		break;
		case '1' : $('#sex_on').attr('checked', 'checked');
		break;
		case '2' : $('#sex_friend').attr('checked', 'checked');
		break;
	}
	switch ('${user.cf_post}') {
		case '1' : $('#post_on').attr('checked', 'checked');
		break;
		case '2' : $('#post_friend').attr('checked', 'checked');
		break;
	}
	switch ('${user.cf_res}') {
		case '0' : $('#res_on').attr('checked', 'checked');
		break;
		case '1' : $('#res_off').attr('checked', 'checked');
		break;
	}
	switch ('${user.cf_act}') {
		case '0' : $('#act_on').attr('checked', 'checked');
		break;
		case '1' : $('#act_off').attr('checked', 'checked');
		break;
	}
	switch ('${user.cf_theme}') {
		case '1' : $('#theme_1').attr('checked', 'checked');
		break;
		case '2' : $('#theme_2').attr('checked', 'checked');
		break;
		case '3' : $('#theme_3').attr('checked', 'checked');
		break;
	}
		
	
	/*	$('#birth_on').attr('checked', 'checked');
		$('#email_friend').attr('checked', 'checked');
		$('#intro_on').attr('checked', 'checked');
		$('#sex_off').attr('checked', 'checked');
		$('#post_friend').attr('checked', 'checked');
		$('#res_on').attr('checked', 'checked');
		$('#act_on').attr('checked', 'checked');
		$('#theme_2').attr('checked', 'checked');*/
	
</script>

</body>

</html>