<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ChatChatCat全球第一貓咪社群網站</title>

<script>
	var servCtexPath = "<c:url value='/' />";
</script>

<link rel="stylesheet" href="<c:url value='/css/normalize.css'/>">
<link rel="stylesheet" href="<c:url value='/css/leftsidebar.css'/>">
<link rel="stylesheet" href="<c:url value='/css/sunny/fontsize1.1/jquery-ui-1.10.4.custom.css'/>">
<link rel="stylesheet" href="<c:url value='/css/jquery.message.css'/>">
<link rel="stylesheet" href="<c:url value='/css/lightbox.css'/>"/>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/rcarousel.css'/>" />
<link type="text/css" rel="stylesheet" href="<c:url value='/css/jquery.custom-scrollbar.css'/>" />
<link rel="stylesheet" href="<c:url value='/css/accordion/jquery-ui-1.10.4.accordion.min.css'/>">
<link rel="stylesheet" href="<c:url value='/css/jquery_flickr_tooltip_menu.css'/>">	 				<!-- 通知icon的css -->
<link rel="stylesheet" href="<c:url value='/css/chatroom.css'/>">									<!-- 聊天室的css-->

<!-- DWR -->
<script type="text/javascript" src="${pageContext.request.contextPath }/dwr/engine.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/dwr/util.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/dwr/interface/ChatroomService.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/dwr/interface/DwrPush.js"></script>
<!-- 柏愷的 -->
<script type="text/javascript" src="<c:url value='/js/jquery-1.10.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/lightbox-2.6.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.ui.rcarousel.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.autoheight.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/leftsidebar.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.message.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.custom-scrollbar.js'/>"></script>
<!-- 協和的 -->
<script type="text/javascript" src="<c:url value='/js/jquery-ui-1.10.4.accordion.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/notice-menu.js'/>"></script>	 		<!-- 通知的js-->
<script type="text/javascript" src="<c:url value='/js/friends-chatroom.js'/>"></script>		<!-- 聊天室的js-->

<style>
.menu {
	position: relative;
	display: block;
	width: 1125px;
	height: 100px;
	margin: 0;
	border: 0px;
	margin-left: auto;
	margin-right: auto;
}

.mainContent {
	width: 1125px;
	border: 0px;
	frameBorder: 0px;
	cellspacing: 0px;
	marginwidth: 0px;
	marginheight: 0px;
	overflow: hidden;
	margin: 0;
	display: block;
}

.mainContentblock{
	width: 1125px;
	margin: 0;
	padding: 0;
	display: block;
	margin-left: auto;
	margin-right: auto;
	border: 0px;
	display: block;
}

body {
	padding: 0;
	margin: 0;
}

.ui-dialog,.ui-button,.ui-button-text .ui-button {
	font-family: '微軟正黑體';
}

.inv {
	visibility: hidden;
	position: absolute;
}

.board,.board:visited {
	text-decoration: none;
	font-family: '微軟正黑體';
	font-size: 12px;
	color: rgb(153,123,93);
	padding: 18px;
	text-shadow: 0px 0px 2px rgb(240,236,232);
}

.board:hover {
	color: black;
	text-shadow: 0px 0px 1px rgb(240,236,232);
}

.boardarrowclass {
	opacity: 0.1;
	filter: alpha(opacity =    10);
}

.boardarrowclass2 {
	opacity: 0.2;
	filter: alpha(opacity =    20);
}

.logotextclass {
	text-shadow: 0px 0px 5px black;
	color: #fff4dd;
	font-family: '微軟正黑體';
	font-weight: 700;
	text-decoration: none;
	font-size: 24px;
	z-index: 0;
	position: absolute;
	top: 2px;
	left: 130px;
	opacity: 0.2;
	filter: alpha(opacity =    20);
}

.logotextclass2 {
	text-shadow: 0px 0px 5px black;
	color: #ffdc6a;
	font-family: '微軟正黑體';
	font-weight: 700;
	text-decoration: none;
	font-size: 24px;
	z-index: 0;
	position: absolute;
	top: 2px;
	left: 130px;
	opacity: 1;
	filter: alpha(opacity =    100);
}

.logoclass {
	position: absolute;
	top: 4px;
	left: 20px;
	z-index: 1;
	opacity: 1;
	filter: alpha(opacity =    100);
	width: 233px;
	height: 97px;
}

/*
#commenticon {
	position: absolute; top: 14px; right: 195px; opacity: 0.3; filter: alpha(opacity = 30);
}

#commenticon:hover {
	opacity: 1; filter: alpha(opacity = 100);
}

#commenticon:active {
	top: 15px; right: 194px;
}

#activityicon {
	position: absolute; top: 13px; right: 108px; opacity: 0.3; filter: alpha(opacity = 30);
}

#activityicon:hover {
	opacity: 1; filter: alpha(opacity = 100);
}

#activityicon:active {
	top: 14px; right: 107px;
}

#addfriendicon {
	position: absolute; top: 14px; right: 150px; opacity: 0.3; filter: alpha(opacity = 30);
}

#addfriendicon:hover {
	opacity: 1; filter: alpha(opacity = 100);
}

#addfriendicon:active {
	top: 15px; right: 149px;
}

#warningicon {
	position: absolute; top: 14px; right: 63px; opacity: 0.3; filter: alpha(opacity = 30);
}

#warningicon:hover {
	opacity: 1; filter: alpha(opacity = 100);
}

#warningicon:active {
	top: 15px; right: 62px;
}
*/
/*/////////////////////////////////////////////////通知icon  協和修改以上的///////////////////////////////////////////////////////////*/
#response_notice_icon {
	position: absolute; top: 14px; right: 195px; opacity: 0.3; filter: alpha(opacity = 30);
}

#response_notice_icon:hover {
	opacity: 1; filter: alpha(opacity = 100);
}

#response_notice_icon:active {
	top: 15px; right: 194px;
}

#activity_notice_icon {
	position: absolute; top: 13px; right: 108px; opacity: 0.3; filter: alpha(opacity = 30);
}

#activity_notice_icon:hover {
	opacity: 1; filter: alpha(opacity = 100);
}

#activity_notice_icon:active {
	top: 14px; right: 107px;
}

#friend_notice_icon {
	position: absolute; top: 14px; right: 150px; opacity: 0.3; filter: alpha(opacity = 30);
}

#friend_notice_icon:hover {
	opacity: 1; filter: alpha(opacity = 100);
}

#friend_notice_icon:active {
	top: 15px; right: 149px;
}

#warning_notice_icon {
	position: absolute; top: 14px; right: 63px; opacity: 0.3; filter: alpha(opacity = 30);
}

#warning_notice_icon:hover {
	opacity: 1; filter: alpha(opacity = 100);
}

#warning_notice_icon:active {
	top: 15px; right: 62px;
}

/*/////////////////////////////////////////////////////////////////*/
.alarmnumber {
	position:absolute;
	top: -2px;
	left: 25px;
	text-decoration: none;
	font-family: '微軟正黑體';
	font-size: 10px;
	color: white;
	background: red;
	padding-left: 3px;
	padding-right: 3px;
	border-radius: 5px;
}
</style>

<script>
	$(function() {
		
		$.ajax({
			"url":'<c:url value="/ViewBoardServlet"/>',
			"type":"get",
			"data":{},
			"datatype":"json",
			"success":function(data){
				if(data==null||data==""){
					$('#carousel').html("");
					$('#carousel').append("找不到公告");
				}
				
				if(data!=''||data!=null||data.length!=0){
					console.log("讀到Json資料");
					var json=eval('('+data+')');
					$('#carousel').html("");
					if(json.jsonArray.length!=0){
						for(var i=0;i<json.jsonArray.length;i++){
					/*		$('#carousel').append("<div class='board' id='board"+i+"'>"
									+"<div class='board'>"+json.jsonArray[i].content+"</div>"
									+"<div class='board'>"
									+"<span class='board'>時間:"+json.jsonArray[i].time+"</span>"
									+"</div>"
									);*/
							$('#carousel').append("<div>"
									+"<span class='board'>"+json.jsonArray[i].content
									+"<br>&nbsp;&nbsp;&nbsp;&nbsp;時間:"+json.jsonArray[i].time.substr(0,16)
									+"</span>"
									+"</div>"
									);							
						}
				/*	<div id="a3">
						<a href="pages/Board.html#board3" class="board" target=f3>[呼籲] 沒有登入到 ChatChatCat 的人可看到您的個人名稱以及大頭貼照。您可以自行設定這些人是否可以看到您的生日、E-mail、個人簡介以及性別。</a>
					</div>*/
						$("#carousel").rcarousel({
							visible : 1,
							step : 1,
							width : 300,
							height : 80,
							auto : {
								enabled : true,
								direction : "next",
								interval : 3000
							}
						});
					}
				}
			}
		});	
		
		$("#deleteconfirm").dialog({
			width : 400,
			autoOpen : false,
			modal : true,
			show : {
				effect : "fade",
				duration : 500
			},
			hide : {
				effect : "fade",
				duration : 200
			}
		});
		
		$("#editpost").dialog({
			width : 1000,
			autoOpen : false,
			modal : true,
			show : {
				effect : "fade",
				duration : 500
			},
			hide : {
				effect : "fade",
				duration : 200
			}
		});
		
		$("#wholike").dialog({
			width : 350,
			autoOpen : false,
			modal : true,
			show : {
				effect : "fade",
				duration : 500
			},
			hide : {
				effect : "fade",
				duration : 200
			}
		});
		
		$("#option").dialog({
			width : 500,
			autoOpen : false,
			modal : true,
			show : {
				effect : "fade",
				duration : 500
			},
			hide : {
				effect : "fade",
				duration : 200
			},
			open: function() {
	            $('.ui-widget-overlay').bind('click', function() {
	                $('#option').dialog('close');
	            })
	        }
		});

		$("#signup").dialog({
			width : 600,
			autoOpen : false,
			modal : true,
			show : {
				effect : "fade",
				duration : 500
			},
			hide : {
				effect : "fade",
				duration : 200
			}
		});
		
		$("#newpost").dialog({
			width : 600,
			autoOpen : false,
			modal : true,
			show : {
				effect : "fade",
				duration : 500
			},
			hide : {
				effect : "fade",
				duration : 200
			}
		});
		
		$("#newreport").dialog({
			width : 450,
			autoOpen : false,
			modal : true,
			show : {
				effect : "fade",
				duration : 500
			},
			hide : {
				effect : "fade",
				duration : 200
			}
		});

		$("#newwarning").dialog({
			width : 450,
			autoOpen : false,
			modal : true,
			show : {
				effect : "fade",
				duration : 500
			},
			hide : {
				effect : "fade",
				duration : 200
			}
		});
		
		$("#changepassword").dialog({
			width : 500,
			autoOpen : false,
			modal : true,
			show : {
				effect : "fade",
				duration : 500
			},
			hide : {
				effect : "fade",
				duration : 200
			},
			open: function() {
	            $('.ui-widget-overlay').bind('click', function() {
	                $('#changepassword').dialog('close');
	            })
	        }
		});
		
		$("#manageactivity").dialog({
			width : 550,
			autoOpen : false,
			modal : true,
			show : {
				effect : "fade",
				duration : 500
			},
			hide : {
				effect : "fade",
				duration : 200
			},
		});	

		$("#newactivity").dialog({
			width : 600,
			autoOpen : false,
			modal : true,
			show : {
				effect : "fade",
				duration : 500
			},
			hide : {
				effect : "fade",
				duration : 200
			}
		});	
		
		

		$('#logoid').hover(function() {
			// on hovering over, find the element we want to fade *up*
			var fade = $('> div', this);
			// if the element is currently being animated (to a fadeOut)...
			if (fade.is(':animated')) {
				// ...take it's current opacity back up to 1
				fade.stop().fadeTo(250, 1);
			} else {
				// fade in quickly
				fade.fadeIn(250);
			}
		}, function() {
			// on hovering out, fade the element out
			var fade = $('> div', this);
			if (fade.is(':animated')) {
				fade.stop().fadeTo(3000, 0);
			} else {
				// fade away slowly
				fade.fadeOut(3000);
			}
		});

		$(".buttonClass").button();

		$("#openoption").click(function() {
			$('#option').dialog('open');
		});
		
/*		$("#logout").click(function() {
			alert("使用者按下登出鍵");
		});*/
		
		$(".boardarrowclass").hover(function() {
			$(this).toggleClass("boardarrowclass2", {duration: 300}).clearQueue();
		});

		$("#logo").hover(function() {
			$(".logotextclass").toggleClass("logotextclass2", {duration: 300}).clearQueue();
	//		$("#logoid").toggleClass("logoclass2", {duration: 300}).clearQueue();
		});

		$(".page-main > aside > ul > li").click(function() {
			alert("跳至該會員頁面");
		});

		$(".page-main > aside > ul").customScrollbar({
			updateOnWindowResize : false
		});

		$("#addfriendicon").hover(function() {
			$(this).toggleClass("addfriendiconclass2", {duration: 150}).clearQueue();
		});
	
	});

	$(window).load(function() {
		$(".page-main aside ul").customScrollbar();
		$('iframe.mainContent').attr('height',$(window).height()-100);
		if (!!window.chrome && !(!!window.opera || navigator.userAgent.indexOf(' OPR/') >= 0)) {
			$(".page-main > aside > button").attr('disabled', 'disabled');
		}
	});
	
	$(window).resize(function(){
		  $('iframe.mainContent').attr('height',$(window).height()-100);
		  $(".page-main aside ul").customScrollbar("resize", true);
	});
	
	/////////////////////////////////////////////////////////////////////DWR/////////////////////////////////////////////////
	//load完連至server端記錄該頁面ScripSession，用於DWR
	function onPageLoad(){  
		DwrPush.onPageLoad();  
	}
	
	//好友聊天列表的accordion效果
	$(function() {
		$('#friends_block').accordion({active: false, collapsible: true});
	})
	
/////////////////////////////////////////////////////////////////////通知系統js檔需要的變數/////////////////////////////////////////////////
	var user = '${user.m_id}';					//從session中取得user id
	var user_name = '${user.m_name}';				//從session中取得user name
	var NoticeServletURL = '<c:url value="/NoticeServlet"/>';					//NoticeServlet 的 URL
	var ChatMessageServletURL = '<c:url value="/ChatMessageServlet.do"/>';		//ChatMessageServlet.do 的 URL	
	var FriendServletURL = '<c:url value="/FriendServlet.do"/>';
	var ViewOneWarNoticeServletURL = '<c:url value="/ViewOneWarNoticeServlet.do"/>';
	var ShowThisActServletURL = '<c:url value="/ShowThisAct.do"/>';
	var ViewOnePostURL = '<c:url value="/pages/OnePost.jsp"/>';
		
	$(function() {
		$(".buttonClass").button();
/*		$('#loginid').click(function(){
			window.parent.parent.$(".afterlogin").show();
		});*/
		$("#openforgetpassword").click(function() {
			window.$('#forgetpassword').dialog('open');
		});
		$("#opensignup").click(function() {
			window.$('#signup').dialog('open');
		});
	});

	$(function() {
		if(user == null || user == '') {
			window.location.assign("<c:url value='/index.jsp'/>");
		} 
	});
	function gotoIndex() {
		window.location.assign("<c:url value='/index.jsp'/>")
	}
	
	$(function() {
		$(document).mouseup(function (e)
				{
				    var container = $(".menu_div");

				    if (!container.is(e.target) // if the target of the click isn't the container...
				        && container.has(e.target).length === 0) // ... nor a descendant of the container
				    {
				    	container.parent().removeClass("clicked").addClass("bt").children('div').hide();
				    }
				});
	})

</script>

</head>
<body style="background-color: #ffe6b5;" onload="dwr.engine.setActiveReverseAjax(true);dwr.engine.setNotifyServerOnPageUnload(true);onPageLoad();">	

	<a href="<c:url value='/images/cat1.jpg'/>" data-lightbox="imageset-1" title="第三隻貓">
		<img id="cat1" class="inv">
	</a>
	<a href="<c:url value='/images/cat.jpg'/>" data-lightbox="imageset-1" title="第二隻貓"> <img
		id="cat" class="inv">
	</a>
	<a href="<c:url value='/images/paw-icon.jpg'/>" data-lightbox="imageset-1" title="第一隻貓">
		<img id="paw-icon" class="inv">
	</a>

	<div class="page-main" role="main" style="z-index:10;width:700px;" >
		<aside>			
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
							<input style='margin-left:28px' size="26px" type="text" id="${friend.m_id }_msg" name='${friend.m_id }_msg'><input type='button' name='${friend.m_id }_sendmsg' id='${friend.m_id }_sendmsg' value='send'/>
						</div>
					</c:forEach>
				 </c:if>
			</div>
			
			<button id="chat-sidebar-button">
				<img src="<c:url value='/images/sidebar-arrow-right.png'/>" style="opacity: 0.5;">
			</button>
		</aside>
	</div>
	
		<div class="menu">

			<span id="logo"> <a href="<c:url value='/pages/Content.jsp'/>" target=f3
				class="logotextclass">全球第一貓咪社群網站</a> 
				<a href="<c:url value='/pages/Content.jsp'/>" target=f3>
					<img src="<c:url value='/images/logo_large2.png'/>" id="logoid" class="logoclass">
				</a>
			</span>

			<div style="float: right; display: inline-block;">
				<a href="#" id="ui-carousel-prev"
					style="position: absolute; top: 37px; right: 594px;"><img
					src="<c:url value='/images/arrow-left.png'/>" class="boardarrowclass"></a>
				<div id="carousel" style="position: absolute; right: 285px;">
					
				</div>
				<a href="#" id="ui-carousel-next"
					style="position: absolute; top: 37px; right: 260px;"><img
					src="<c:url value='/images/arrow-right.png'/>" class="boardarrowclass"></a> 
										
				
					<a href="#" id="openoption" class="buttonClass afterlogin"
					style="position: absolute; top: 58px; right: 147px; font-size: 14px;">設定</a>  
		<!-- 		<a href="#" id="logout" class="buttonClass afterlogin"
					style="position: absolute; top: 58px; right: 77px; font-size: 14px;">登出</a> -->
					<input type="button" id="logout" class="buttonClass afterlogin"
					style="position: absolute; top: 58px; right: 77px; font-size: 14px;" onclick="javascript:location.href='<c:url value="/LogoutServlet" />'" value="登出">
				
				
				<!--	<div class="iconclass"> 若不用<a>包裹<img>，可容易解決游標停於邊緣的抖動問題
					<a href="#" id="commenticon" class="afterlogin"><img src="images/commenticon.png"><span class="alarmnumber">3</span></a> 
					<a href="#" id="addfriendicon" class="afterlogin"><img src="images/addfriendicon.png"></a> 
					<a href="#" id="activityicon" class="afterlogin"><img src="images/activityicon.png"><span class="alarmnumber">16</span></a>
					<a href="#" id="warningicon" class="afterlogin"><img src="images/warningicon.png"><span class="alarmnumber">7</span></a>
					</div>-->
					
				<!-- ///////////////////////////////////////////////////////////////////// -->	

					<div id="box" class="iconclass">
					    <div id='response_notice_icon' class="bt btleft" style='display:inline-block;'>
					    	<img src="<c:url value='/images/commenticon.png'/>"><span id="respNoticeUnreadCount" class="alarmnumber"></span>
							<div class='menu_div' style='margin:0px;position: absolute;left:-10px;'>
							    <div class="triangle"></div>
							    <div id='response_notice_menu' class="tooltip_menu" style='position:absolute;width:300px'>
							    	<!-- 此處塞回覆通知內容 -->
							    </div>
							</div>
					    </div>
					    <div id='friend_notice_icon' class="bt btmiddle_left" style='display:inline-block'>
					    	<img src="<c:url value='/images/addfriendicon.png'/>"><span id="fsNoticeUnreadCount" class="alarmnumber"></span>
							<div class='menu_div' style='margin:0px;position: absolute;left:-10px;'>
							    <div class="triangle"></div>
							    <div id='friend_notice_menu' class="tooltip_menu" style='position:absolute;width:300px'>
							    	<!-- 此處塞加友通知內容 -->
							    </div>
							</div>
						</div>   
					    <div id='activity_notice_icon' class="bt btmiddle_right" style='display:inline-block'>
					    	<img src="<c:url value='/images/activityicon.png'/>"><span id="actNoticeUnreadCount" class="alarmnumber"></span>
							<div class='menu_div' style='margin:0px;position: absolute;left:-10px;'>
							    <div class="triangle"></div>
							    <div id='activity_notice_menu' class="tooltip_menu" style='position:absolute;width:300px'>
							    	<!-- 此處塞活動通知內容 -->
							    </div>
							</div>
						</div>  
					    <div id='warning_notice_icon' class="bt btright" style='display:inline-block'>
					    	<img src="<c:url value='/images/warningicon.png'/>"><span id="warNoticeUnreadCount" class="alarmnumber"></span>
							<div class='menu_div' style='margin:0px;position: absolute;left:-10px;'>
							    <div class="triangle"></div>
							    <div id='warning_notice_menu' class="tooltip_menu" style='position:absolute;width:300px'>
							    	<!-- 此處塞被警告通知內容 -->
							    </div>
							</div>
					    </div>
					</div>
				<!-- ///////////////////////////////////////////////////////////////////// -->		
				
			</div>
		
		</div>

	<div class="mainContentblock">

		<iframe class="mainContent" src="<c:url value='/pages/MainContent.jsp'/>" name=f2 scrolling="no"></iframe>

	</div>
	

	
	
	<!-- 以下給管理員用的功能  會員登入時為隱藏 -->
	<div id="deleteconfirm" title="確認刪除" style="font-size: 24px">
		<iframe frameborder="0" scrolling="no" width="350" height="165" id="deleteconfirmiframe"
			src="<c:url value='/pages/DeleteConfirm.jsp'/>"> </iframe>
	</div>
	
	<div id="editpost" title="編輯貼文" style="font-size: 24px">
		<iframe frameborder="0" scrolling="no" width="950" height="570" id="editpostiframe"
			src="<c:url value='/pages/EditPost.jsp'/>"> </iframe>
	</div>
	
	<div id="wholike" title="有誰按讚" style="font-size: 24px">
		<iframe frameborder="0" scrolling="no" width="300" height="200" id="wholikeiframe"
			src="<c:url value='/pages/WhoLike.jsp'/>"> </iframe>
	</div>
	
	<div id="option" title="個人設定">
		<iframe frameborder="0" scrolling="no" width="450" height="500"
			src="<c:url value='/pages/Settings.jsp'/>"> </iframe>
	</div>

	<div id="newpost" title="新貼文" style="font-size: 24px">
		<iframe frameborder="0" scrolling="no" width="550" height="570"
			src="<c:url value='/pages/NewPost.jsp'/>"> </iframe>
	</div>

	<div id="changepassword" title="變更密碼" style="font-size: 24px">
		<iframe frameborder="0" scrolling="no" width="450" height="400"
			src="<c:url value='/pages/ChangePassword.jsp'/>	"> </iframe>
	</div>

	<div id="newreport" title="檢舉貼文" style="font-size: 24px">
		<iframe frameborder="0" scrolling="no" width="400" height="200" id="newreportiframe"
			src="<c:url value='/pages/NewReport.jsp'/>	"> </iframe>
	</div>

	<div id="newwarning" title="發送警告" style="font-size: 24px">
		<iframe frameborder="0" scrolling="no" width="400" height="200"
			src="<c:url value='/pages/NewWarning.html'/>	"> </iframe>
	</div>
			
	<div id="manageactivity" title="管理活動" style="font-size: 24px">
		<iframe frameborder="0" scrolling="no" width="500"
			height="500" src="<c:url value='/pages/ManageActivity.html'/>"> </iframe>
	</div>
	<!-- 以上給管理員用的功能  會員登入時為隱藏 -->


</body>
</html>