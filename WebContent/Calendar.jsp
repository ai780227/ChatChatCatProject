<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Calendar</title>

	<style>
	body {
		padding: 0;
		margin: 0;
	}
	</style>

	<link rel="stylesheet"
	href="<c:url value='/css/sunny/fontsize0.9/jquery-ui-1.10.4.custom.css'/>">
	<script type="text/javascript" src="<c:url value="/js/jquery-2.1.0.js"/>"></script> 
	<script src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>
	
	<script>

	//forToGo
	var haveact = [];
	//forInvite
	var haveinv = [];
	//act_id
	
	var haveactid = [];

	var haveinvid = [];
	
	
	$(function() {
		//ajax
		$.ajax({
			'url' : '<c:url value="/ForCalendarServlet.do"/>',
			'type': 'post',
			'datatype' : 'text',
			'success':function(data){
				var json = eval('('+data+')');	

				if(data!="0"){	
					if(json.hasOwnProperty("forToGo")){
						for(var i=0;i<json.forToGo.length;i++){
							var obj = new Date(json.forToGo[i].year,json.forToGo[i].month, json.forToGo[i].day);
							haveact.push(obj);
							haveactid.push(json.forToGo[i].act_id);
						}
					}else if(json.hasOwnProperty("forInvite")){
						for(var i=0;i<json.forInvite.length;i++){
							var obj = new Date(json.forInvite[i].year,json.forInvite[i].month,json.forInvite[i].day);
							haveinv.push(obj);
							haveinvid.push(json.forInvite[i].act_id);
						}
					}else if(json[0].hasOwnProperty("forToGo")&&json[1].hasOwnProperty("forInvite")){
						for(var i=0;i<json[0].forToGo.length;i++){
							var obj = new Date(json[0].forToGo[i].year,json[0].forToGo[i].month, json[0].forToGo[i].day);
							haveact.push(obj);
							haveactid.push(json[0].forToGo[i].act_id);
						}
						for(var i=0;i<json[1].forInvite.length;i++){
							var obj = new Date(json[1].forInvite[i].year,json[1].forInvite[i].month,json[1].forInvite[i].day);
							haveinv.push(obj);
							haveinvid.push(json[1].forInvite[i].act_id);
						}
					}	
					$("#datepicker").datepicker({
						beforeShowDay : sethaveact
					});
					$(document).tooltip();
					setdayclass();
					$(document).on('click', setdayclass);	
				}								
				if(json=="0"){
					$("#datepicker").datepicker({
						beforeShowDay : sethaveact
					});
					$(document).tooltip();
					setdayclass();
					$(document).on('click', setdayclass);
				}
			}		
		});		
		
	});

	function setdayclass() {
		for ( var i = 0; (i < haveact.length) || (i < haveinv.length); i++) {		
			$(".haveactclass"+i).unbind("click").children().attr("href","../ShowThisAct.do?Act_id="+haveactid[i]).attr("target","f3").css("background-image", "url('../images/paw-icon.png')").css("background-repeat", "no-repeat").css("background-color","rgb(254,230,154)").css("background-position", "left center").css("border-color","rgb(238,207,124)");
			$(".haveinvclass"+i).unbind("click").children().attr("href","../ShowThisAct.do?Act_id="+haveinvid[i]).attr("target","f3").css("background-image", "url('../images/ajax-loader.gif')");
		}	
	}

	function sethaveact(date) {
		for ( var i = 0; (i < haveact.length) || (i < haveinv.length); i++) {
			if (haveact[i] - date == 0) {
				return [ true, 'haveactclass'+i, '今天有活動' ];
			} else if (haveinv[i] - date == 0) {
				return [ true, 'haveinvclass'+i, '有活動邀請' ];
			}
		}
		return [ false, 'else', '' ];
	}
	</script>		
</head>
<body>
	<div style="text-align: center;">
		<span id="datepicker" style="display: inline-block;"></span>
	</div>
</body>
</html>