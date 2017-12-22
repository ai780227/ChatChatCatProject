
$(function() {
	//取得通知筆數
	$.ajax( { 'url':NoticeServletURL,
			  'type':'get',
			  'data':{'NoticeType':'notice_count'},
			  'datatype':'json',
			  'success':function(data) {
			   				var jsonObj = eval('(' + data + ')');
			   				if(jsonObj[0].fsNoticeUnreadCount != 0)
			   					$('#fsNoticeUnreadCount').text(jsonObj[0].fsNoticeUnreadCount);
			   				if(jsonObj[0].respNoticeUnreadCount != 0)
			   					$('#respNoticeUnreadCount').text(jsonObj[0].respNoticeUnreadCount);
			   				if(jsonObj[0].actNoticeUnreadCount != 0)
			   					$('#actNoticeUnreadCount').text(jsonObj[0].actNoticeUnreadCount);
			   				if(jsonObj[0].warNoticeUnreadCount != 0)
			   					$('#warNoticeUnreadCount').text(jsonObj[0].warNoticeUnreadCount);
			  			} 
			  } );
	
	//取得加友通知內容
	$('#friend_notice_icon').bind('click', function() {
					//將同一層的其他icon收起來(hide);
	  				$(this).siblings().removeClass("clicked").addClass("bt").children('div').hide();
	  				//展開(show)或收起(hide)點選的node
					if ($(this).hasClass("bt")) {
				        $(this).removeClass("bt").addClass("clicked").children('div').show();
				    } else {
				        $(this).removeClass("clicked").addClass("bt").children('div').hide();
				    }
		
		//			var user = '${user.m_id}';
					$.get( NoticeServletURL, 
						   {'user':user, 'NoticeType':'friend_notice_icon'},
						   function(data) {
							   		//將未讀計數給清掉
							   		$('#fsNoticeUnreadCount').text('');
							   		
							   		var jsonObj = eval('(' + data + ')');
								 	$('#friend_notice_menu').html('');
								 	if(jsonObj.length==0) {
								 		$('#friend_notice_menu').prepend('<div style="height:50px;">'
	 									+'沒有通知 '
	 									 + '</div>');
								 	}
								 	for(var i=0; i < jsonObj.length ; i++) {
								 		if(jsonObj[i].fri_type==0)		//id = fsNoticeId_ + 此通知的id  ，接受=fsNoticeBtnAccessed(1)，拒絕=fsNoticeBtnReject(0)
								 			$('#friend_notice_menu').prepend('<div id="fsNoticeId_'+jsonObj[i].fri_notid+'" style="height:50px;">'
								 											+'<table style="width:100%;">'
								 												+'<tr ><td style="width:65%;">' + jsonObj[i].m_name_from +' 傳送了交友邀請給你</td>'
								 													 +'<td style="width:35%;">'
								 													 	+'<input name="fsNoticeBtnAccessed" type="button" value="接受"/>'
								 													 	+' <input name="fsNoticeBtnReject" type="button" value="拒絕"/></td>'
								 												+'</tr>'
								 											+'</table>' 
								 									 + '</div>');
								 		else if(jsonObj[i].fri_type==1)		//id = fsNoticeId_ + 此通知的id  ，確認=fsNoticeBtnOkay(1)
								 			$('#friend_notice_menu').prepend('<div id="fsNoticeId_'+jsonObj[i].fri_notid+'" style="height:50px;">'
								 											+'<table style="width:100%;">'
								 												+'<tr ><td style="width:65%;">'+ jsonObj[i].m_name_from +' 已接受了你的交友邀請</td>'
								 													 +'<td style="width:35%;">'
								 													 	+'<input name="fsNoticeBtnOkay" type="button" value="確認"/></td>'
								 												+'</tr>'
								 											+'</table>'
								 									  +'</div>');							 			
								 	}
								 	//添加接受/拒絕/確認  事件
								 	$('input[name^=fsNoticeBtn]').bind('click',function() {
								 								//<input> → <td> → <tr> → <tbody> → <table> → <div> 
								 								var divId = $(this).parent().parent().parent().parent().parent().attr('id');
								 								var fri_notid = divId.substr(11,divId.length);
								 								var fsNotice_response;
								 								if( $(this).attr('name')== 'fsNoticeBtnAccessed')		//接受  1
								 									fsNotice_response = 1;
								 								else if( $(this).attr('name')== 'fsNoticeBtnReject')	//拒絕  0
								 									fsNotice_response = 0;
								 								else if( $(this).attr('name')== 'fsNoticeBtnOkay')	//確認  2
								 									fsNotice_response = 2;
								 								$.ajax( { 'url':FriendServletURL,
								 										  'type':'get',
								 										  'data':{'fri_notid':fri_notid,			//通知ID
								 											  	  'fsNotice_response':fsNotice_response,		//回覆結果
								 											  	  'friendship_type':'respondInvite'},
								 										  'datatype':'text',
								 										  'success':function() {
								 											  $('#'+divId).remove();
								 											 location.reload();
								 										  }} );
								 						});
						   });
			});
	//取得回覆通知內容
	$('#response_notice_icon').bind('click', function() {
					//將同一層的其他icon收起來(hide);
					$(this).siblings().removeClass("clicked").addClass("bt").children('div').hide();
					//展開(show)或收起(hide)點選的node
					if ($(this).hasClass("bt")) {
				        $(this).removeClass("bt").addClass("clicked").children('div').show();
				    } else {
				        $(this).removeClass("clicked").addClass("bt").children('div').hide();
				    }
					
			//		var user = '${user.m_id}';
					$.get( NoticeServletURL, 
						   {'user':user, 'NoticeType':'response_notice_icon'},
						   function(data) {
							   		//將未讀計數給清掉
							   		$('#respNoticeUnreadCount').text('');
							   		
							   		var jsonObj = eval('(' + data + ')');
								 	$('#response_notice_menu').html('');
								 	if(jsonObj.length==0) {
								 		$('#response_notice_menu').prepend('<div style="height:50px;">'
	 									+'沒有通知 '
	 									 + '</div>');
								 	}
								 	for(var i=0; i < jsonObj.length ; i++) {
								 		$('#response_notice_menu').prepend('<a style="text-decoration:none !important;" target=f3 href="' + ViewOnePostURL +'?post_id='+jsonObj[i].post_id+'">'
																			+'<div id="respNoticeId_'+jsonObj[i].res_notid+'" style="height:50px;">'
								 												+ jsonObj[i].m_name_from +' 在貼文"' 
								 												+ jsonObj[i].post_content.substr(0,10)+'..."中，做出新的回應'
							 												+'</div>'
										 								+'</a>');							 			
								 	}
								 	//添加  "連結到貼文"  事件 
								 	$('div[id^="respNoticeId_"]').bind('click',function() {
							 								var divId = $(this).attr('id');
							 								var resp_notid = divId.substr(13,divId.length);
							 								$.ajax( { 'url':'#',
							 										  'type':'get',
							 										  'data':{'resp_notid':resp_notid},			//傳入回覆通知的id
							 										  'datatype':'text',
							 										  'success':'' } );
								 						});
						   });
			});		
	
	//取得活動通知內容
	$('#activity_notice_icon').bind('click', function() {
					//將同一層的其他icon收起來(hide);
					$(this).siblings().removeClass("clicked").addClass("bt").children('div').hide();
					//展開(show)或收起(hide)點選的node
					if ($(this).hasClass("bt")) {
				        $(this).removeClass("bt").addClass("clicked").children('div').show();
				    } else {
				        $(this).removeClass("clicked").addClass("bt").children('div').hide();
				    }
					
			//		var user = '${user.m_id}';
					$.get( NoticeServletURL, 
						   {'user':user, 'NoticeType':'activity_notice_icon'},
						   function(data) {
							   		//將未讀計數給清掉
							   		$('#actNoticeUnreadCount').text('');
							   		
							   		var jsonObj = eval('(' + data + ')');
								 	$('#activity_notice_menu').html('');
								 	if(jsonObj.length==0) {
								 		$('#activity_notice_menu').prepend('<div style="height:50px;">'
	 									+'沒有通知 '
	 									 + '</div>');
								 	}
								 	for(var i=0; i < jsonObj.length ; i++) {
								 		if(jsonObj[i].act_notContent == 'invite') {
									 		$('#activity_notice_menu').prepend('<a style="text-decoration:none !important;" target=f3 href="' + ShowThisActServletURL +'?Act_id='+jsonObj[i].act_id+'">'
		 																		+'<div id="actNoticeId_'+jsonObj[i].act_notid+'" style="height:50px;">'
									 												+ '<input type="hidden" name="act_id" value="' + jsonObj[i].act_id + '"/>'
									 												+ jsonObj[i].act_host + ' 邀請你參加活動 "'+jsonObj[i].act_title +'"'
								 												+'</div>'
									 										+'</a>');		
								 		} else if(jsonObj[i].act_notContent == 'update') {
										 	$('#activity_notice_menu').prepend('<a style="text-decoration:none !important;" target=f3 href="' + ShowThisActServletURL +'?Act_id='+jsonObj[i].act_id+'">'
																				+'<div id="actNoticeId_'+jsonObj[i].act_notid+'" style="height:50px;">'
										 											+ '<input type="hidden" name="act_id" value="' + jsonObj[i].act_id + '"/>'
										 											+ jsonObj[i].act_host + ' 對活動 "'+jsonObj[i].act_title +'"做了修改'
									 											+'</div>'
										 									+'</a>');										 			
								 		} else if(jsonObj[i].act_notContent == 'cancel') {
										 	$('#activity_notice_menu').prepend('<div id="actNoticeId_'+jsonObj[i].act_notid+'" style="height:50px;">'
										 											+ '<input type="hidden" name="act_id" value="' + jsonObj[i].act_id + '"/>'
										 											+ jsonObj[i].act_host + ' 將活動 "'+jsonObj[i].act_title +'"取消了'
									 											+'</div>');									 			
								 		}
								 	}
								 	//添加  "連結到活動"  事件 
								 	$('div[id^="actNoticeId_"]').bind('click',function() {
							 								var divId = $(this).attr('id');
							 								var act_notid = divId.substr(12,divId.length);
							 								var act_id = $(this).find('input').val();
							 								$.ajax( { 'url':'#',
							 										  'type':'get',
							 										  'data':{'act_notid':act_notid,		//傳入活動通知的id
							 											  	  'act_id':act_id},				//傳入活動的id
							 										  'datatype':'text',
							 										  'success':'' } );
								 						});
						   });
			});			
	
	//取得被警告通知
	$('#warning_notice_icon').bind('click', function() {
					//將同一層的其他icon收起來(hide);
					$(this).siblings().removeClass("clicked").addClass("bt").children('div').hide();
					//展開(show)或收起(hide)點選的node
					if ($(this).hasClass("bt")) {
				        $(this).removeClass("bt").addClass("clicked").children('div').show();
				    } else {
				        $(this).removeClass("clicked").addClass("bt").children('div').hide();
				    }
					
		//			var user = '${user.m_id}';
					$.get( NoticeServletURL, 
						   {'user':user, 'NoticeType':'warning_notice_icon'},
						   function(data) {
							   		//將未讀計數給清掉
							   		$('#warNoticeUnreadCount').text('');
							   		
							   		var jsonObj = eval('(' + data + ')');
								 	$('#warning_notice_menu').html('');
								 	if(jsonObj.length==0) {
								 		$('#warning_notice_menu').prepend('<div style="height:50px;">'
	 									+'沒有通知 '
	 									 + '</div>');
								 	}
								 	for(var i=0; i < jsonObj.length ; i++) {
								 		$('#warning_notice_menu').prepend('<a style="text-decoration:none !important;" target=f3 href="' + ViewOneWarNoticeServletURL +'?war_notid='+jsonObj[i].war_notid+'">'
								 											+'<div id="warNoticeId_'+jsonObj[i].war_notid+'" style="height:50px;">'
								 												+ '你的一則貼文 "'+jsonObj[i].post_content.substr(0,10) +'..."'
								 												+'，因"' +jsonObj[i].rep_cause +'"而被管理員刪除'
							 												+'</div>'
							 											  +'</a>');						 			
								 	}
								 	//添加  "連結到被警告通知之貼文"  事件 
								 	$('div[id^="warNoticeId_"]').bind('click',function() {
							 								var divId = $(this).attr('id');
							 								var war_notid = divId.substr(12,divId.length);
							 								$.ajax( { 'url':'#',
							 										  'type':'get',
							 										  'data':{'war_notid':war_notid},			//傳入被警告通知的id
							 										  'datatype':'text',
							 										  'success':'' } );
								 						});
						   });
			});	
});



//////////////////////////////////////////////////////////////////////////////////////
//接收加友通知
function receiveFriendNotice(data) {
//	$('#friend_notice').css('background-color','pink');
//	alert('friend_notice successed !!');
	var fsNoticeUnreadCount = $('#fsNoticeUnreadCount').text();
	if(fsNoticeUnreadCount == '') {
		$('#fsNoticeUnreadCount').text('1');
	} else {
		$('#fsNoticeUnreadCount').text(parseInt(fsNoticeUnreadCount) + 1);
	}
}

//////////////////////////////////////////////////////////////////////////////////////
//接收回覆通知
function receiveRespNotice(data) {
//	$('#response_notice').css('background-color','silver');
//	alert('response_notice  successed !!');
	var respNoticeUnreadCount = $('#respNoticeUnreadCount').text();
	if(respNoticeUnreadCount == '') {
		$('#respNoticeUnreadCount').text('1');
	} else {
		$('#respNoticeUnreadCount').text(parseInt(respNoticeUnreadCount) + 1);
	}
}

///////////////////////////////////////////////////////////////////////////////////////
//接收活動通知
function receiveActNotice(data) {
//	$('#activity_notice').css('background-color','#FFC991');
//	alert('activity_notice  successed !!')
	var actNoticeUnreadCount = $('#actNoticeUnreadCount').text();
	if(actNoticeUnreadCount == '') {
		$('#actNoticeUnreadCount').text('1');
	} else {
		$('#actNoticeUnreadCount').text(parseInt(actNoticeUnreadCount) + 1);
	}
}

//////////////////////////////////////////////////////////////////////////////////////////	
//接收活動通知
function receiveWarNotice(data) {
//	$('#warning_notice').css('background-color','#DF3B39');
//	alert('warning_notice  successed !!')
	var warNoticeUnreadCount = $('#warNoticeUnreadCount').text();
	if(warNoticeUnreadCount == '') {
		$('#warNoticeUnreadCount').text('1');
	} else {
		$('#warNoticeUnreadCount').text(parseInt(warNoticeUnreadCount) + 1);
	}
}

/////////////////////////////////////////////////////////////////////////////////////////////////
/*
* 	$.ajax( { 'url':NoticeServletURL,
		  'type':'get',
		  'data':{'NoticeType':'notice_count'},
		  'datatype':'json',
		  'success':function(data) {
		   				var jsonObj = eval('(' + data + ')');
		   				if(jsonObj[0].fsNoticeUnreadCount != 0)
		   					$('#fsNoticeUnreadCount').text(jsonObj[0].fsNoticeUnreadCount);
		   				if(jsonObj[0].respNoticeUnreadCount != 0)
		   					$('#respNoticeUnreadCount').text(jsonObj[0].respNoticeUnreadCount);
		   				if(jsonObj[0].actNoticeUnreadCount != 0)
		   					$('#actNoticeUnreadCount').text(jsonObj[0].actNoticeUnreadCount);
		   				if(jsonObj[0].warNoticeUnreadCount != 0)
		   					$('#warNoticeUnreadCount').text(jsonObj[0].warNoticeUnreadCount);
		  			} 
		  } );
*/

