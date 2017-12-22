<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公告頁面</title>

<link rel="stylesheet" href="../css/sunny/fontsize1.1/jquery-ui-1.10.4.custom.css">

<script src="../js/jquery-1.10.2.js"></script>
<script src="../js/jquery-ui-1.10.4.custom.js"></script>
<script src="../js/jquery.elastic.source.js"></script>

<script>
	$(function() {
		$( ".elastic" ).elastic();
	});

</script>

<style>
.block {
	font-family:"微軟正黑體";
	line-height: 24px;
	position: relative;
	border-style: solid;
	border-color: orange;
	border-radius: 35px;
	width:430px;
    margin: 12px auto;
    padding: 5px 15px 25px 15px;
}
.content {
	margin: 20px;
}
.deleteiconinvisible{
	position: absolute;
	top:5px;
	right:5px;
	opacity: 0.2;
	filter: alpha(opacity =    0.2);
}
.deleteiconinvisible:hover{
	opacity: 1;
	filter: alpha(opacity =    100);
}
.editiconinvisible{
	position: absolute;
	top:35px;
	right:5px;
	opacity: 0.2;
	filter: alpha(opacity =    0.2);
}
.editiconinvisible:hover{
	opacity: 1;
	filter: alpha(opacity =    100);
}
textarea {
	margin-bottom: 12px;
	width: 68%;
	padding: .4em;
	font-size: 16px;
	font-family: '微軟正黑體';
}
.time {
	border-top: solid;
	border-color: orange;
	border-top-width: thin;
}
.timetext {
	position:relative;
	left: 30px;
}

</style>
<script>
$(document).ready(function(){
	$.ajax({
		"url":'<c:url value="/ViewBoardServlet"/>',
		"type":"get",
		"data":{},
		"datatype":"json",
		"success":function(data){
			if(data==null||data==""){
				$('#board').html("");
				$('#board').append("找不到公告");
			}
			
			if(data!=''||data!=null||data.length!=0){
				console.log("讀到Json資料");
				var json=eval('('+data+')');
				$('#board').html("");
				$('#board').append("<div class='block' id='newboard'>"
						+"<div style='margin-top: 15px;'>"
						+"<span style='position:relative'>"
						+"<label for='content' style='position:absolute; top:-60px; left: 10px;'>"+"新公告 :"+"</label>"
						+"<textarea id='addContent' cols='80' rows='5' name='insert' placeholder='輸入公告內容' class='elastic text ui-widget-content ui-corner-all' style='position:relative; left:85px; resize: none; max-height:142px;'>"
						+"</textarea>"
						+"<input class='buttonClass1' style='top:20px;position:relative;left:0px' name='update' type='button' value='新增' id='add'>"
						+"</span>"
						+"</div>"
						+"</div>");
				//$('.buttonClass').button().css("font-size",16).css("padding","5px 10px 5px 10px").css("top","20px");
				$('.buttonClass1').button().css("font-size",16).css("padding","5px 10px 5px 10px");
				if(json.jsonArray.length!=0){
					
					for(var i=0;i<json.jsonArray.length;i++){
						$('#board').append("<div class='block' id='board"+i+"'>"
								+"<div class='content' id='content"+i+"'>"+json.jsonArray[i].content+"</div>"							
								+"<img src='../images/delete.png' class='deleteiconinvisible' id='delete"+i+"'>"
								+"<img src='../images/edit.png' class='editiconinvisible' id='edit"+i+"'>"
								+"<div class='time'>"
								+"<span class='timetext'>時間:"+json.jsonArray[i].time+"</span>"
								+"</div>"
								+"<div>"+json.jsonArray[i].board_id+"</div>"
								+"<input type='hidden' name='id' value="+json.jsonArray[i].board_id+">"
								+"<input type='hidden' name='update' value='delete"+i+"'>"
								+"</div>"
								);
						};
						
						$('#add').bind('click',function(){
							var post=$('#addContent').val();
							$.ajax({
								"url":'<c:url value="/BoardServlet.mgr"/>',
								"type":"post",
								"data":{"update":"新增","content":post},
								"datatype":"text",
								"success":function(data){
									if(data==null||data==""){
										$('#board').html("");
										$('#board').append("找不到公告");
									}
									if(data!=''||data!=null||data.length!=0){
										$('#board').append(data);
										}
									}
							});
						});
						
						$('img[id^="delete"]').bind('click',function(){
							var number=$(this).parent().attr('id').substr(5,1);
							var board_id=$(this).parent().children('input[name="id"]').attr('value');
					     	$.ajax({
					     		"url":"<c:url value='/BoardServlet.mgr'/>",
					     		"type":"post",
					     		"data":{"id":board_id,"update":"delete"}
					     	});
					
					     	var number=$(this).attr('id').substr(6,1);
					     	$('#board'+number).remove();
						});
						
						
						
						//$('body').on('click','img[id^="edit"]',function(){
						$('img[id^="edit"]').bind('click',function(){
							//var edit_id=$(this).attr('id').length;
							var number=$(this).parent().attr('id').substr(5,1);
							var v=$('#content'+number).text();
							var board_id=$(this).parent().children('input[name="id"]').attr('value');
							$('#board'+number).empty();
							$('#board'+number).append("<div id='newboard'>"
									+"<div style='margin-top: 15px;'>"
									+"<span style='position:relative'>"
									+"<label for='content' style='position:absolute; top:-60px; left: 10px;'>"+"新公告 :"+"</label>"
									+"<textarea  cols='80' rows='5' name='content' placeholder='輸入公告內容' class='elastic text ui-widget-content ui-corner-all' style='position:relative; left:85px; resize: none; max-height:142px; margin-bottom:5px;'>"
									+ v
									+"</textarea>"
									+"</span>"
									+"<input type='hidden' name='id' value="+board_id+">"
									+"</div>"
									+"<input class='buttonClass' name='update' type='button' value='確認修改'>"
									+"<input class='buttonClass'  name='cancel' type='button' value='取消修改'>"
									+"</div>");
							$('.buttonClass').button().css("font-size",16).css("padding","5px 10px 5px 10px").css("left","220px");
							
							$('input[name="update"]').bind('click',function(){
								var board_id= $(this).parent().children().children('input[name="id"]').attr('value');
								var post=$(this).parent().children().children().children('textarea').val();
								alert(post);
								$.ajax({
									"url":'<c:url value="/BoardServlet.mgr"/>',
									"type":"post",
									"data":{"update":"確認修改","id":board_id,"content":post},
									"datatype":"text",
									"success":function(data){
										if(data==null||data==""){
											$('#board').html("");
											$('#board').append("找不到公告");
										}
										if(data!=''||data!=null||data.length!=0){
											$('#board').append(data);
											}
										}
									});
								});
							
							$('input[name="cancel"]').bind('click',function(){
								window.location.assign("<c:url value='/pages/ManagerBoard.jsp'/>");
								});
							});
						}
					}
				}
			});	
		});


</script>

</head>

<body>

<h1 style="position:fixed;top:20px;left:75px;">公告</h1>
	<div id="board"></div>
	<!--<c:forEach var="board" items="${boardlist}" varStatus="i">
	<div class="block" id="board${i.count}">
	<div class="content">${board.board_content}</div>
	<div class="time"> <span class="timetext">時間：${board.board_time}</span></div>
	</div>
	</c:forEach>-->
</body>

</html>
