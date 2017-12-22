<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="<c:url value='/css/accordion/jquery-ui-1.10.4.accordion.min.css'/>">
<link rel="stylesheet" href="<c:url value='/css/jquery_flickr_tooltip_menu.css'/>">	 <!-- 通知icon的 -->
<link rel="stylesheet" href="<c:url value='/css/chatroom.css'/>">	 <!-- 聊天室的css-->

<script type="text/javascript" src="${pageContext.request.contextPath }/dwr/engine.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/dwr/util.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/dwr/interface/ChatroomService.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/dwr/interface/DwrPush.js"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-2.1.0.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui-1.10.4.accordion.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/notice-menu.js'/>"></script>	 		<!-- 通知的js-->
<script type="text/javascript" src="<c:url value='/js/friends-chatroom.js'/>"></script>		<!-- 聊天室的js-->
<script>
	var user = '${user.m_id}';					//從session中取得user id
	var user_name = '${user.m_id}';				//從session中取得user name
	var NoticeServletURL = '<c:url value="/NoticeServlet"/>';					//NoticeServlet 的 URL
	var ChatMessageServletURL = '<c:url value="/ChatMessageServlet.do"/>';		//ChatMessageServlet.do 的 URL
	
	//網頁onload後執行此function
	function onPageLoad(){  
		DwrPush.onPageLoad();  
	}
	//好友聊天列表的accordion效果
	$(function() {
		$('#friends_block').accordion({active: false, collapsible: true});
	})
</script>
    
</head>

<body onload="dwr.engine.setActiveReverseAjax(true);dwr.engine.setNotifyServerOnPageUnload(true);onPageLoad();">

<h1>Login Success</h1>
<a href="<c:url value='/pages/page1.jsp'/>" >page1</a>
<input type="button" value="Logout" onclick="javascript:location.href='<c:url value="/LogoutServlet" />'"/>
<input type="button" value="News" onclick="javascript:location.href='<c:url value="/pages/news.jsp" />'"/><br>
<br>

<!-- /////////////////////////////////////////////////// Accordion Friends 好友聊天列表/////////////////////////////////////////////////////////////////////////////////// -->
<div id='friends_block' style='width:350px'>

	<c:if test="${friends != null }">
		<c:forEach var="friend" items="${friends }">
			<h3 id="${friend.m_id }" align="left">${friend.m_name }</h3>
			<div style='border:0px;margin:0px;padding:0px;'>
	  			<div class='chat_block_background' id="${friend.m_id }_chatblock" 
	  				 style='border:1px solid black;
	  				 		margin:auto; 
	  				 		width:300px; height:300px;
	  				 		overflow:auto; overflow-y:scroll;' >
	
				</div>
				<input style='margin-left:28px' size="26px" type="text" id="${friend.m_id }_msg"><input type='button' name='${friend.m_id }_sendmsg' id='sendmsg' value='send'/>
			</div>
		</c:forEach>
	 </c:if>
</div>
	
<!-- //////////////////////////////////////////////////////查看通知內容//////////////////////////////////////////////////////////////////////////////// -->
<!-- 用jquery_flickr_tooltip_menu.css -->
<div id="box">
    <div id='response_notice_icon' class="bt btleft" style='display:inline-block'>Response Notice<span>&#9660;</span>
		<div class='menu' style='margin:0px;position: relative;'>
		    <div id="triangle"></div>
		    <div id='response_notice_menu' class="tooltip_menu" style='position:absolute;width:300px'>
		    	<!-- 此處塞回覆通知內容 -->
		    </div>
		</div>
    </div>
    <div id='friend_notice_icon' class="bt btmiddle_left" style='display:inline-block'>Friend Notice<span>&#9660;</span>
		<div id='menu' class='menu' style='margin:0px;position: relative;'>
		    <div id="triangle"></div>
		    <div id='friend_notice_menu' class="tooltip_menu" style='position:absolute;width:300px'>
		    	<!-- 此處塞加友通知內容 -->
		    </div>
		</div>
	</div>   
    <div id='activity_notice_icon' class="bt btmiddle_right" style='display:inline-block'>Activity Notice<span>&#9660;</span>
		<div id='menu' class='menu' style='margin:0px;position: relative;'>
		    <div id="triangle"></div>
		    <div id='activity_notice_menu' class="tooltip_menu" style='position:absolute;width:300px'>
		    	<!-- 此處塞活動通知內容 -->
		    </div>
		</div>
	</div>  
    <div id='warning_notice_icon' class="bt btright" style='display:inline-block'>Warning Notice<span>&#9660;</span>
		<div id='menu' class='menu' style='margin:0px;position: relative;'>
		    <div id="triangle"></div>
		    <div id='warning_notice_menu' class="tooltip_menu" style='position:absolute;width:300px'>
		    	<!-- 此處塞被警告通知內容 -->
		    </div>
		</div>
    </div>
</div>

<!-- 取得通知筆數 -->
<br>
fsNoticeUnreadCount = <span id='fsNoticeUnreadCount'></span><br>
respNoticeUnreadCount = <span id='respNoticeUnreadCount'></span><br>
actNoticeUnreadCount = <span id='actNoticeUnreadCount'></span><br>
warNoticeUnreadCount =<span id='warNoticeUnreadCount'></span><br>
<br>

<!-- ////////////////////////////////////////////推送通知////////////////////////////////////////////////////////////////////////////////////////// -->
<br>
<br>
<div id='friend_notice' style='width:400px; height:50px; border:1px solid green;'></div>	<!-- 加友通知 -->
<input type='button' id='sendFriendNotice' value='Add Friend' /><br>
<br>
<div id='response_notice' style='width:400px; height:50px; border:1px solid green'></div>	<!-- 回覆通知 -->
<input type='button' id='sendRespNotice' value='Response Notice' /><br>
<br>
<div id='activity_notice' style='width:400px; height:50px; border:1px solid green'></div>	<!-- 活動通知 -->
<input type='button' id='sendActNotice' value='Activity Notice' /><br>
<br>
<div id='warning_notice' style='width:400px; height:50px; border:1px solid green'></div>	<!-- 被警告通知 -->
<input type='button' id='sendWarNotice' value='Warning Notice' /><br>


<script>
	//////////////////////////////////////////////////////////////////////////////////////
	//傳送加友通知
	$('#sendFriendNotice').bind('click', function() {
		$.ajax( { 'url':'<c:url value="NoticeServlet"/>',
				  'type':'get',
				  'data':{'mem_to':$('#send_to').val(),
					  	  'NoticeType':'FriendshipNotice'},
				  'datatype':'text',
				  'success':function(data){alert(data)} });
	});	
	//接收加友通知
	function receiveFriendNotice(data) {
		$('#friend_notice').css('background-color','pink');
		alert('friend_notice successed !!');
	}

	//////////////////////////////////////////////////////////////////////////////////////
	//傳送回覆通知 ( 測試為對cecj001發通知 )
	$('#sendRespNotice').bind('click', function() {
		$.ajax( { 'url':'<c:url value="NoticeServlet"/>',
				  'type':'get',
				  'data':{'NoticeType':'ResponseNotice'},
				  'datatype':'text',
				  'success':''});
	});
	//接收回覆通知
	function receiveRespNotice(data) {
		$('#response_notice').css('background-color','silver');
		alert('response_notice  successed !!');
	}

	///////////////////////////////////////////////////////////////////////////////////////
	//傳送活動通知
	$('#sendActNotice').bind('click',function() {
		$.ajax( { 'url':'<c:url value="NoticeServlet"/>',
				  'type':'get',
				  'data':{'NoticeType':'ActivityNotice'},
				  'datatype':'text',
				  'success':''});
	});
	//接收活動通知
	function receiveActNotice(data) {
		$('#activity_notice').css('background-color','#FFC991');
		alert('activity_notice  successed !!')
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////
	//傳送被警告通知
	$('#sendWarNotice').bind('click',function() {
		$.ajax( { 'url':'<c:url value="NoticeServlet"/>',
				  'type':'get',
				  'data':{'NoticeType':'WarningNotice'},
				  'datatype':'text',
				  'success':''});
	});
	//接收活動通知
	function receiveWarNotice(data) {
		$('#warning_notice').css('background-color','#DF3B39');
		alert('warning_notice  successed !!')
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
</script>
</body>
</html>