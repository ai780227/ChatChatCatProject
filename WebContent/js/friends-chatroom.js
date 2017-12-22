$(function() {
	/*
	float:right;clear:both; 			浮動 & 清除兩旁浮動
	word-wrap:break-word  				自動換行
	$('').scrollTop(scrollHeight)		將對話框捲軸控制在最底部
	*/
		//點選好友名字後，送出請求，取出與對方之聊天記錄(預設5筆)
		$('#friends_block > h3').bind('click',function() {	
			var friend = $(this).attr("id");
			$.ajax( {'url':ChatMessageServletURL,
				 	 'type':'get',
					 'data':{'friend':friend,
					 		 'page':5},
				 	 'datatype':'text',
				 	 'success':function(data) {
						 var jsonObj = eval('(' + data + ')');
		//				 var user = '${user.m_id }';
						 $('#'+ friend +'_chatblock').text("");	//清空之前的內容
						 for(var i=0; i<jsonObj.length; i++) {
							 if(jsonObj[i].from == user)	//我發的訊息
							 	$('#'+ friend +'_chatblock').prepend("<div class='chat_message_me'>"
							 									+""+jsonObj[i].from_name + " - " + jsonObj[i].time.substr(0,16)+"<br>"
									 							+"<div class='chat_message_in_me'>"
									 							+jsonObj[i].content+"</div>"
									 							+"</div>");
							 else						//對方發的訊息
								 $('#'+ friend +'_chatblock').prepend("<div class='chat_message_you'>"
																+""+jsonObj[i].from_name + " - " + jsonObj[i].time.substr(0,16)+"<br>"
				 												+"<div class='chat_message_in_you'>"
				 												+jsonObj[i].content+"</div>"
				 												+"</div>");
						 }
						//x筆對話取出後，增加取得歷史訊息之block
						$('#'+ friend +'_chatblock')
								.prepend("<div id='" + friend + "_history' "
										 +"style='text-align:center; cursor:pointer; color:#c9c9c9; margin:5px;'>︽瀏覽之前的訊息</div>");
						//將對話框捲軸控制在最底部
						 var scrollHeight = document.getElementById(friend+"_chatblock").scrollHeight;
						 $('#'+ friend+"_chatblock").scrollTop(scrollHeight);
						 //prepend完之後才註冊以下事件
						 //游標over事件
						 $('div[id$="_history"]').hover(function() { $(this).css('color','#f57d05'); } ,function() { $(this).css('color','#a1a1a1'); });
						 //點選取得對話之歷史訊息後，送出請求(選取name的最末字元為 sendmsg 的node)
						 $('div[id$="_history"]').bind('click', function() {
							 	var id = $(this).attr('id');					//取得屬性為name的內容(id_sendmsg)
								var length = id.length;							//取得name總長度
								var friend = id.substr(0,length-8);				//取得ID，substr(0,總長度-8)=id
								var currentMsgCount = $('#'+ friend +'_chatblock > div').size() - 1;		//取得目前之對話數量(原本包含 histroy 的 div )
								$.ajax( { 'url':ChatMessageServletURL,
										  'type':'get',
										  'data':{'friend':friend,
									 			  'page':currentMsgCount+5},
									 	  'datatype':'text',
									 	  'success':function(data) {
									 			var jsonObj = eval('(' + data + ')');
										//	 	var user = '${user.m_id }';
											 	for(var i = currentMsgCount ; i<jsonObj.length; i++) {
													 if(jsonObj[i].from == user)	//我發的訊息
													 	$('#'+ friend +'_history').after("<div class='chat_message_me'>"
												 									+""+jsonObj[i].from_name + " - " + jsonObj[i].time.substr(0,16)+"<br>"
														 							+"<div class='chat_message_in_me'>"
														 							+jsonObj[i].content+"</div>"
														 							+"</div>");
													 else						//對方發的訊息
														 $('#'+ friend +'_history').after("<div class='chat_message_you'>"
																					+""+jsonObj[i].from_name + " - " + jsonObj[i].time.substr(0,16)+"<br>"
									 												+"<div class='chat_message_in_you'>"
									 												+jsonObj[i].content+"</div>"
									 												+"</div>");
											 }
									 	 }} );
						});
					 }} );
		});
		
		
		$('input[name$="_msg"]').keyup(function(event){
		    if(event.keyCode == 13){
				var name = $(this).attr('name');					//取得屬性為name的內容(id_msg)
				var length = name.length;							//取得name總長度
				var send_to = name.substr(0,length-4);				//取得ID，substr(0,總長度-4)=id
		        $("#"+send_to+"_sendmsg").click();
		    }
		});		
		//傳送聊天內容，選取name的最末字元為 sendmsg 的node
		$('input[name$="_sendmsg"]').bind('click', function() {	
			var name = $(this).attr('name');					//取得屬性為name的內容(id_sendmsg)
			var length = name.length;							//取得name總長度
			var send_to = name.substr(0,length-8);				//取得ID，substr(0,總長度-8)=id
			var content = $('#' + send_to + '_msg').val();		//取得要傳送的訊息內容
			//如果訊息不為空，才傳送訊息
			if(content != '') {		
				var time = new Date();							//取得現在時間
				var mem_to = {"m_id": send_to};					//收件者物件
		        var data = {"member_to": mem_to, "msg_content": content, "msg_time": time};		//裝到msg字串	
		        $('#' + send_to + '_msg').val('');							//清空輸入框
		        
		        //2014-3-19 19:56:55							//自訂時間格式，將時間組成一組字串供傳訊者使用
		        var year = time.getFullYear();
		        var month = time.getMonth()+1;
		        var date = time.getDate()+1;
		        var hour = time.getHours();
		        var min = time.getMinutes();
		        var sec = time.getSeconds();
		        var actTime = year +"-"+ month +"-"+ date +" "+ hour +":"+ min +":"+ sec;
		        
		//        var user_name = '${user.m_name}';				//取得傳訊者的名字
		        //將自己傳送的訊息也在自己的對話框中顯示
		        $('#' + send_to + '_chatblock').append("<div class='chat_message_me'>"
												+""+user_name + " - " + actTime+"<br>"
												+"<div class='chat_message_in_me'>"
												+content+"</div>"
												+"</div>");
		        //將對話框捲軸控制在最底部
				var scrollHeight = document.getElementById(send_to+"_chatblock").scrollHeight;
				$('#'+ send_to+"_chatblock").scrollTop(scrollHeight);
				//使用server端method，傳送訊息
		        ChatroomService.sendMessage(data);	
			}
		});
		
});		
		//接收推送之聊天內容
		function showMessage(mem_from, mem_from_name, content, time) {
			//接收server端通知，將內容呈現給client端
			content = decodeURI(content);
			mem_from_name = decodeURI(mem_from_name);
	//		var user = '${user.m_id }';
			//如果有訊息發送
			var chatSideBarBtn = $('#chat-sidebar-button > img').attr('src');
			console.log(chatSideBarBtn);
			if(chatSideBarBtn == '/ChatChatCatProject/images/sidebar-arrow-right.png')
				$('#chat-sidebar-button > img').attr('src', '/ChatChatCatProject/images/sidebar-arrow-right-red.png');
			if(mem_from == user) {	 //我發的訊息
				 $('#'+ mem_from +'_chatblock').append("<div class='chat_message_me'>"
				 								+""+mem_from_name + " - " + time.substr(0,16)+"<br>"
						 						+"<div class='chat_message_in_me'>"
						 						+content+"</div>"
						 						+"</div>");
			} else {						//對方發的訊息
				$('#'+ mem_from +'_chatblock').append("<div class='chat_message_you'>"
												+""+mem_from_name + " - " + time.substr(0,16)+"<br>"
												+"<div class='chat_message_in_you'>"
												+content+"</div>"
												+"</div>");
			}
			var scrollHeight = document.getElementById(mem_from + '_chatblock').scrollHeight;
			$('#'+ mem_from +'_chatblock').scrollTop(scrollHeight);
		}
	
