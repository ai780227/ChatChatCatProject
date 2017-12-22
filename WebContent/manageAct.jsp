<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">	
<title>Manage Activities</title>
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
	.actblock{
    border: solid 2px orange;
	text-shadow: 0px 0px 5px black;
	display:inline-block;
	text-align:center;
	line-height:35px;
	border-radius:50px;
	padding:20px 40px 20px 40px;
	margin-right:20px;
	color:white;
	font-family:"微軟正黑體";
	font-size: 18px;
background: rgb(239,162,67); /* Old browsers */
background: -moz-linear-gradient(top,  rgba(239,162,67,1) 0%, rgba(196,128,86,1) 50%, rgba(189,94,0,1) 51%, rgba(239,201,143,1) 100%); /* FF3.6+ */
background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(239,162,67,1)), color-stop(50%,rgba(196,128,86,1)), color-stop(51%,rgba(189,94,0,1)), color-stop(100%,rgba(239,201,143,1))); /* Chrome,Safari4+ */
background: -webkit-linear-gradient(top,  rgba(239,162,67,1) 0%,rgba(196,128,86,1) 50%,rgba(189,94,0,1) 51%,rgba(239,201,143,1) 100%); /* Chrome10+,Safari5.1+ */
background: -o-linear-gradient(top,  rgba(239,162,67,1) 0%,rgba(196,128,86,1) 50%,rgba(189,94,0,1) 51%,rgba(239,201,143,1) 100%); /* Opera 11.10+ */
background: -ms-linear-gradient(top,  rgba(239,162,67,1) 0%,rgba(196,128,86,1) 50%,rgba(189,94,0,1) 51%,rgba(239,201,143,1) 100%); /* IE10+ */
background: linear-gradient(to bottom,  rgba(239,162,67,1) 0%,rgba(196,128,86,1) 50%,rgba(189,94,0,1) 51%,rgba(239,201,143,1) 100%); /* W3C */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#efa243', endColorstr='#efc98f',GradientType=0 ); /* IE6-9 */
}

	.actblock:hover {
background: rgb(255,207,153); /* Old browsers */
background: -moz-linear-gradient(top,  rgba(255,207,153,1) 0%, rgba(196,128,86,1) 50%, rgba(189,94,0,1) 51%, rgba(255,238,191,1) 100%); /* FF3.6+ */
background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(255,207,153,1)), color-stop(50%,rgba(196,128,86,1)), color-stop(51%,rgba(189,94,0,1)), color-stop(100%,rgba(255,238,191,1))); /* Chrome,Safari4+ */
background: -webkit-linear-gradient(top,  rgba(255,207,153,1) 0%,rgba(196,128,86,1) 50%,rgba(189,94,0,1) 51%,rgba(255,238,191,1) 100%); /* Chrome10+,Safari5.1+ */
background: -o-linear-gradient(top,  rgba(255,207,153,1) 0%,rgba(196,128,86,1) 50%,rgba(189,94,0,1) 51%,rgba(255,238,191,1) 100%); /* Opera 11.10+ */
background: -ms-linear-gradient(top,  rgba(255,207,153,1) 0%,rgba(196,128,86,1) 50%,rgba(189,94,0,1) 51%,rgba(255,238,191,1) 100%); /* IE10+ */
background: linear-gradient(to bottom,  rgba(255,207,153,1) 0%,rgba(196,128,86,1) 50%,rgba(189,94,0,1) 51%,rgba(255,238,191,1) 100%); /* W3C */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ffcf99', endColorstr='#ffeebf',GradientType=0 ); /* IE6-9 */
}
	
	</style>
	<link rel="stylesheet"
	href="<c:url value='/css/sunny/fontsize1.1/jquery-ui-1.10.4.custom.css'/>">
	<script type="text/javascript" src="<c:url value="/js/jquery-2.1.0.js" />"></script> 
	<script src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>
	<script>
	$(function() {
		$(".buttonClass").button().css("font-size","16px");
		$(this).height(500);
	});
	</script>
	<!-- <script>
		$(function() {
	
			$("#openmanageactivity").click(function() {
				parent.parent.$('#manageactivity').dialog('open');
				$('#createActForm').submit();
				return false;
			});
		});
	</script> -->
</head>
<body>
		<form id="createActForm" method="POST" action="<c:url value='/PrepareEditAct.do'/>">
			<input type="submit" class="buttonClass" value="發起活動" name="doWhat">
		</form>
		 
	<c:choose>
		<c:when test="${requestScope.size != 0}">
			<c:forEach	var="myActs" items="${requestScope.myacts}"  varStatus="i" begin="0" end="${requestScope.size}">
				<ol style="list-style-type: none; text-align:center;">	
					<li class="actblock">
						活動性質：			
						<c:choose>
						<c:when test="${myActs.getAct_property() == 1}">
							<c:out value="公開活動"/><br>
						</c:when>
						<c:when test="${myActs.getAct_property() == 0}">
							<c:out value="私人活動"/><br>
						</c:when>
						</c:choose>
						活動標題：<c:out value="${myActs.getAct_title()}"/><br>
						活動時間：<c:out value="${myActs.getAct_time().toString()}"/><br>
						活動地點：<c:out value="${myActs.getAct_location()}"/><br>
						參加人數：<c:out value="${requestScope.count[i.index]}">123</c:out>
						<form method="POST" style="margin-top:10px;" action="<c:url value='/PrepareEditAct.do'/>">
							<input type="hidden" name="Act_id" value="${myActs.getAct_id()}">
							<input type="submit" class="buttonClass" value="編輯活動" name="doWhat">
							<input type="submit" class="buttonClass" value="刪除活動" name="doWhat"> 
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" class="buttonClass" value="觀看活動詳情" onclick="this.form.action='<c:url value='/ShowThisAct.do'/>';this.form.submit();"> 
						</form>					
					</li>	
				</ol>		
			</c:forEach>
		</c:when>	
		<c:when test="${requestScope.size == 0}">
			<H2 style="font-family:'微軟正黑體';">目前尚無發起任何活動</H2>
		</c:when>
	</c:choose>
	<hr>
	&nbsp;&nbsp;&nbsp; 	
	<input type="button" class="buttonClass" value="回活動首頁" onclick="javascript:window.location='<c:url value='/pages/ActivityIndex.jsp'/>'">															
</body>
</html>