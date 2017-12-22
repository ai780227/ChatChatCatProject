<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Activity</title>
	<style>
	input.text,textarea {
		margin-bottom: 12px;
		width: 50%;
		padding: .4em;
		font-size: 16px;
		font-family: '微軟正黑體';
	}
	
	button.ui-datepicker-current {
		display: none;
	}
	</style>
	
	<link rel="stylesheet"
	href="<c:url value='/css/sunny/fontsize1.1/jquery-ui-1.10.4.custom.css'/>">
	<link rel="stylesheet" href="<c:url value='/css/jquery-ui-timepicker-addon.css'/>">
	<script type="text/javascript" src="<c:url value="/js/jquery-2.1.0.js" />"></script> 
	<script src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>
	<script src="<c:url value='/js/jquery-ui-timepicker-addon.js'/>"></script>
	<script src="<c:url value='/js/jquery-ui-sliderAccess.js'/>"></script>	
 	<script type="text/javascript"
      src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDTwtDHP39nM6NcX2VfMNNdI8ePbb3vuR4&sensor=false">
 	</script>	
	<script type="text/javascript" src="<c:url value="/js/useNewMap.js" />"></script> 
	<script>
	$(function() {
		//parent.$('#manageactivity').dialog("option", "position", "top");
		$('#act_time').datetimepicker({
			showButtonPanel : true,
			closeText : "完成",
			currentText : "現在時間",
			dateFormat : 'yy-mm-dd',
			timeFormat : 'HH:mm:ss',
			stepMinute : 30,
			showSecond : false,
			timeText : "時間",
			hourText : "幾點",
			minuteText : "幾分",
			secondText : "秒"
		});
		$('#act_deadline').datetimepicker({
			dateFormat : 'yy-mm-dd',
			timeFormat : 'HH:mm:ss',
			showTime: false,
			showHour : false,
			showMinute : false,
			showSecond : false,
			closeText : "完成",
			currentText : "今天日期",
			showButtonPanel : false
		});
		$("p > span").buttonset();
		$(".buttonClass").button();
		$(this).height(500);
	});
	</script>
</head>
<body onload="initialize()">
	<h3 style="text-shadow: 2px 2px 2px white; color: black; font-family: '微軟正黑體';">新活動</h3>
	<form method="POST" action="<c:url value='/PrepareEditAct.do'/>" style="line-height: 16px; font-size: 16px; font-family: '微軟正黑體';">
<!--   活動屬性:		<input type="radio" name="act_property" value="0">私人
				<input type="radio" name="act_property" value="1">公開<br>
     活動標題:		<input type="text" name="act_title"><br>
     活動時間:		<input type="datetime" name="act_time"><br>
     報名截止:		<input type="datetime" name="act_deadline"><br>
     活動地點: 		<input type="text" name="act_location" id="address" style="width:225px">
			    <input type="button" value="搜尋" onclick="codeAddress()">
				<div id="map-canvas" style="width: 350px; height: 350px;"></div>		
     活動內容: 		<input type="text" name="act_content"  style="width:270px;"><br>
     選取好友:		<c:forEach	var="myFriends" items="${requestScope.friendsList}">	
     					<input type="checkbox" name="friendsName" value="${myFriends.getM_id()}">${myFriends.getM_name()}&nbsp;
     			</c:forEach>
-->  
     	<p>
			活動性質： <span> <input id="public" type="radio"
				name="act_property" value="1" checked><label for="public">公開</label>
				<input id="private" type="radio" name="act_property" value="0"><label
				for="private">私人</label>
			</span>
		</p>
     	<p>
			<label for="title">活動標題：</label> <input type="text" id="title"
				name="act_title" autofocus
				class="text ui-widget-content ui-corner-all">
		</p>
		<p>
			活動時間： <input type="text" placeholder="選擇日期時間" id="act_time"
				class="text ui-widget-content ui-corner-all" name="act_time">
		</p>
		<p>
			<label for="title">活動地點：</label> <input type="text" id="address"
				name="act_location" class="text ui-widget-content ui-corner-all">
				<input type="button" class="buttonClass" value="搜尋" onclick="codeAddress()">
				<div id="map-canvas" style="position:relative; left:90px; width: 425px; height: 350px; border-radius: 25px;"></div>
		</p>
		<p>
			<span style="position:relative"><label for="content" style="position:absolute; top:-60px;">活動簡介：</label>
			<textarea cols="80" rows="5" placeholder="輸入活動簡介"
				class="text ui-widget-content ui-corner-all"
				style="position:relative; left:85px; resize: none;" name="act_content"></textarea>
			</span>
		</p>
		<p>
			報名截止日： <input type="text" placeholder="選擇日期" id="act_deadline"
				class="text ui-widget-content ui-corner-all" name="act_deadline"><span
				style="color: grey;">（非必填）</span>
		</p>
		 選取好友:<c:forEach	var="myFriends" items="${requestScope.friendsList}">	
     					<input type="checkbox" name="friendsName" value="${myFriends.getM_id()}">${myFriends.getM_name()}&nbsp;
     			</c:forEach>
     			<hr>
     			<input type="submit" class="buttonClass" value="新增活動" name="doWhat" id="sumitid">
     			<input type="reset" class="buttonClass" value="清除重填" > 
     			<input type="button" class="buttonClass" value="回活動管理" onclick="javascript:window.location='<c:url value='/ForManageActPage.do'/>'">     			
  	</form>																
</body>
</html>