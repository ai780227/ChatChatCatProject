<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理員管理檢舉頁面</title>

<link rel="stylesheet" href="<c:url value='/css/sunny/fontsize1.1/jquery-ui-1.10.4.custom.css'/>">
	
<script src="<c:url value='/js/jquery-1.10.2.js'/>"></script>
<script src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>
<script src="<c:url value='/js/jquery.elastic.source.js'/>"></script>

<style>
.block{
position: relative; 
margin: 10px; 
padding:10px; 
width: 600px; 
border-style:groove; 
border-width:5px 5px 1px 5px; 
border-color:blue; 
border-radius: 25px; 
background: rgb(223,243,249)
}

.iconinvisible{
	opacity: 0.2;
	filter: alpha(opacity =    0.2);
}

.iconinvisible:hover{
	opacity: 1;
	filter: alpha(opacity =    100);
}

.responseblock{
	position: relative; 
	top:-10px; 
	left: 10px; 
	margin: 0px; 
	padding:10px; 
	width: 600px; 
	border-style:groove; 
	border-width:1px 5px 0px 5px; 
	border-color:green; 
	border-radius: 25px; 
	background: rgb(240,250,211);
}

</style>

<script>
	$(function() {
		$(".buttonClass").button();
		$( ".elastic" ).elastic();
	});
	

	function CheckForm(){
		if(confirm("確認發送警告通知嗎?")==true){
			return true;
		}else{
			return false;
		}
	}
</script>

</head>

<body style="padding-top: 6px;">

<c:forEach var="aRep" items="${Report}">
	<div>
		<div class="block">
		<div style="position: relative;" id="post01">
		<a href="#" style="position:absolute; right:5px; top:35px;" class="iconinvisible"><img src="<c:url value='/images/delete.png'/>"></a>
		</div>		
			被檢舉人帳號:${aRep.getPost().getMemberBean().m_id}<br> 
			貼文內容：<br>${aRep.getPost().post_content}<br>
			貼文時間：${aRep.getPost().post_time}<br>
			<c:choose>
				<c:when test="${aRep.getPost().post_property==1}">
					公開狀態：所有<br>
				</c:when>
				<c:when test="${aRep.getPost().post_property==2}">
					公開狀態：好友<br>
				</c:when>
				<c:when test="${aRep.getPost().post_property==3}">
					公開狀態：封鎖<br>
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${aRep.getPost().post_type==1}">
					貼文類型：一般動態<br>
				</c:when>
				<c:when test="${aRep.getPost().post_type==2}">
					貼文類型：知識(醫療、飼養)<br>
				</c:when>
				<c:when test="${aRep.getPost().post_type==3}">
					貼文類型：領養<br>
				</c:when>
				<c:when test="${aRep.getPost().post_type==4}">
					貼文類型：貓咪店家<br>
				</c:when>
				<c:when test="${aRep.getPost().post_type==5}">
					貼文類型：活動<br>
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${aRep.getPost().getActivityBean().act_title==null}">
				</c:when>
				<c:otherwise>
					活動名稱：${aRep.getPost().getActivityBean().act_title}<br>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="responseblock" id="response01">
			檢舉人帳號：${aRep.getMember().m_id}<br>
			檢舉人帳號：${aRep.getMember().m_name}<br>
			檢舉內容:${aRep.rep_cause}<br>
		</div>
			
		<div style="position: relative; top:-10px; left: 10px; margin: 0px; padding:10px; width: 600px; border-style:groove; border-width:0px 5px 5px 5px; border-color:green; border-radius: 25px;; background: rgb(240,250,211)">
		<span style="position:absolute;vertical-align:middle;top:25%;">警告理由：</span>		
			<form action="InsertWarningNoticeServlet.mgr" method="POST" onSubmit="return CheckForm();">
				<textarea name="reason" cols="52" rows="1" placeholder="填寫警告理由" style="position: relative; left: 82px; resize: none; max-height:240px; margin-top:5px;" class="elastic text ui-widget-content ui-corner-all"></textarea>		
				<input type="submit" value="發送" class="buttonClass" style="font-size: 12px; position: relative; left: 92px; top: -10px;">
				<input type="hidden" name="post_type" value="${aRep.getPost().post_type}">
				<input type="hidden" name="ReportedAccountMember" value="${aRep.getPost().getMemberBean().m_id}">
				<input type="hidden" name="post_content" value="${aRep.getPost().post_content}">
				<input type="hidden" name="postime" value="${aRep.getPost().post_time}">
				<input type="hidden" name="rep_cause" value="${aRep.rep_cause}">
				<input type="hidden" name="m_id" value="${aRep.getMember().m_id}">
				<input type="hidden" name="post_property" value="${aRep.getPost().post_property}">
				<input type="hidden" name="like_count" value="${aRep.getPost().like_count}">	
				<input type="hidden" name="act_id" value="${aRep.getPost().getActivityBean().act_id}">
				<input type="hidden" name="post_id" value="${aRep.getPost().post_id}">
			</form>
			<br/>
		</div>
</c:forEach>

<!-- 
	<div>
		<div class="block">
		<div style="position: relative;" id="post01">
		<a href="#" style="position:absolute; right:5px; top:35px;" class="iconinvisible"><img src="../images/delete.png"></a>
		</div>
		PO文者：會員01<br/>
		內容：<br/>
		LALALALA<br/>
		LALALALA<br/>
		LALALALA<br/>
		LALALALA<br/>
		LALALALA<br/>
		</div>
		<div class="responseblock" id="response01">
		檢舉人：AAA<br/>
		檢舉內容：LALALALA<br/>
		</div>
		<div style="position: relative; top:-10px; left: 10px; margin: 0px; padding:10px; width: 600px; border-style:groove; border-width:0px 5px 5px 5px; border-color:green; border-radius: 25px;; background: rgb(240,250,211)">
		<span style="position:absolute;vertical-align:middle;top:50%;">警告理由：</span>
		<textarea cols="52" rows="1" placeholder="填寫警告理由" style="position: relative; left: 82px; resize: none; max-height:240px; margin-top:5px;" class="elastic text ui-widget-content ui-corner-all"></textarea>
		<a href="#" class="buttonClass" style="font-size: 12px; position: relative; left: 92px; top: -10px;">送出</a>
		<br/>
		</div>
		<hr>
	</div>
	 -->	
</body>
</html>