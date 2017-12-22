<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Activities</title>

<link rel="stylesheet" href="<c:url value='/css/sunny/fontsize1.1/jquery-ui-1.10.4.custom.css'/>">
<script type="text/javascript" src="<c:url value="/js/jquery-2.1.0.js"/>"></script>
<script src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>

<style>
.outerblock {

border-radius: 30px; 
text-align:center; 
font-size: 20px; 
padding:20px; 
font-family:'微軟正黑體'; 
line-height:36px; 
color:#001188; 
text-shadow: 0px 0px 5px #cca6a6;
font-weight:bold;
background: rgb(255,238,243);
border: solid 5px pink;

}

.buttonClass {

	background: #fceabb; /* Old browsers */
	background: -moz-linear-gradient(top,  #fceabb 0%, #fccd4d 50%, #f8b500 51%, #fbdf93 100%); /* FF3.6+ */
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#fceabb), color-stop(50%,#fccd4d), color-stop(51%,#f8b500), color-stop(100%,#fbdf93)); /* Chrome,Safari4+ */
	background: -webkit-linear-gradient(top,  #fceabb 0%,#fccd4d 50%,#f8b500 51%,#fbdf93 100%); /* Chrome10+,Safari5.1+ */
	background: -o-linear-gradient(top,  #fceabb 0%,#fccd4d 50%,#f8b500 51%,#fbdf93 100%); /* Opera 11.10+ */
	background: -ms-linear-gradient(top,  #fceabb 0%,#fccd4d 50%,#f8b500 51%,#fbdf93 100%); /* IE10+ */
	background: linear-gradient(to bottom,  #fceabb 0%,#fccd4d 50%,#f8b500 51%,#fbdf93 100%); /* W3C */
	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#fceabb', endColorstr='#fbdf93',GradientType=0 ); /* IE6-9 */
	
	padding: 7px 15px;
    border-radius: 35px;
    border:outset 1px orange;
    box-shadow: 3px 3px 5px black;
	cursor:pointer;
	font-family:'微軟正黑體';
	font-size: 18px; 
}

.buttonClass:hover {
background: rgb(254,245,224); /* Old browsers */
background: -moz-linear-gradient(top,  rgba(254,245,224,1) 0%, rgba(254,232,172,1) 50%, rgba(255,222,131,1) 51%, rgba(253,240,205,1) 100%); /* FF3.6+ */
background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(254,245,224,1)), color-stop(50%,rgba(254,232,172,1)), color-stop(51%,rgba(255,222,131,1)), color-stop(100%,rgba(253,240,205,1))); /* Chrome,Safari4+ */
background: -webkit-linear-gradient(top,  rgba(254,245,224,1) 0%,rgba(254,232,172,1) 50%,rgba(255,222,131,1) 51%,rgba(253,240,205,1) 100%); /* Chrome10+,Safari5.1+ */
background: -o-linear-gradient(top,  rgba(254,245,224,1) 0%,rgba(254,232,172,1) 50%,rgba(255,222,131,1) 51%,rgba(253,240,205,1) 100%); /* Opera 11.10+ */
background: -ms-linear-gradient(top,  rgba(254,245,224,1) 0%,rgba(254,232,172,1) 50%,rgba(255,222,131,1) 51%,rgba(253,240,205,1) 100%); /* IE10+ */
background: linear-gradient(to bottom,  rgba(254,245,224,1) 0%,rgba(254,232,172,1) 50%,rgba(255,222,131,1) 51%,rgba(253,240,205,1) 100%); /* W3C */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#fef5e0', endColorstr='#fdf0cd',GradientType=0 ); /* IE6-9 */
}

.buttonClassblue {
background: rgb(219,240,253); /* Old browsers */
background: -moz-linear-gradient(top,  rgba(219,240,253,1) 0%, rgba(159,215,253,1) 50%, rgba(113,198,255,1) 51%, rgba(197,231,253,1) 100%); /* FF3.6+ */
background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(219,240,253,1)), color-stop(50%,rgba(159,215,253,1)), color-stop(51%,rgba(113,198,255,1)), color-stop(100%,rgba(197,231,253,1))); /* Chrome,Safari4+ */
background: -webkit-linear-gradient(top,  rgba(219,240,253,1) 0%,rgba(159,215,253,1) 50%,rgba(113,198,255,1) 51%,rgba(197,231,253,1) 100%); /* Chrome10+,Safari5.1+ */
background: -o-linear-gradient(top,  rgba(219,240,253,1) 0%,rgba(159,215,253,1) 50%,rgba(113,198,255,1) 51%,rgba(197,231,253,1) 100%); /* Opera 11.10+ */
background: -ms-linear-gradient(top,  rgba(219,240,253,1) 0%,rgba(159,215,253,1) 50%,rgba(113,198,255,1) 51%,rgba(197,231,253,1) 100%); /* IE10+ */
background: linear-gradient(to bottom,  rgba(219,240,253,1) 0%,rgba(159,215,253,1) 50%,rgba(113,198,255,1) 51%,rgba(197,231,253,1) 100%); /* W3C */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#dbf0fd', endColorstr='#c5e7fd',GradientType=0 ); /* IE6-9 */

	padding: 7px 15px;
    border-radius: 35px;
    border:outset 1px blue;
    box-shadow: 3px 3px 5px black;
	cursor:pointer;
	font-family:'微軟正黑體';
	font-size: 18px; 
	
}

.buttonClassblue:hover {
background: rgb(240,249,254); /* Old browsers */
background: -moz-linear-gradient(top,  rgba(240,249,254,1) 0%, rgba(212,237,254,1) 50%, rgba(193,230,255,1) 51%, rgba(230,244,254,1) 100%); /* FF3.6+ */
background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(240,249,254,1)), color-stop(50%,rgba(212,237,254,1)), color-stop(51%,rgba(193,230,255,1)), color-stop(100%,rgba(230,244,254,1))); /* Chrome,Safari4+ */
background: -webkit-linear-gradient(top,  rgba(240,249,254,1) 0%,rgba(212,237,254,1) 50%,rgba(193,230,255,1) 51%,rgba(230,244,254,1) 100%); /* Chrome10+,Safari5.1+ */
background: -o-linear-gradient(top,  rgba(240,249,254,1) 0%,rgba(212,237,254,1) 50%,rgba(193,230,255,1) 51%,rgba(230,244,254,1) 100%); /* Opera 11.10+ */
background: -ms-linear-gradient(top,  rgba(240,249,254,1) 0%,rgba(212,237,254,1) 50%,rgba(193,230,255,1) 51%,rgba(230,244,254,1) 100%); /* IE10+ */
background: linear-gradient(to bottom,  rgba(240,249,254,1) 0%,rgba(212,237,254,1) 50%,rgba(193,230,255,1) 51%,rgba(230,244,254,1) 100%); /* W3C */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#f0f9fe', endColorstr='#e6f4fe',GradientType=0 ); /* IE6-9 */
}

</style>

<script>
	$(function() {
		$(this).height(500);
	});
</script>

</head>

<body>

	<div style="text-align:center;">
	<form method="POST" action="<c:url value='/ForViewPriActPage.do'/>">
		<input type="button" class="buttonClass" value="公開活動查詢"
			onclick="javascript:window.location='<c:url value='/ForViewPubActPage.do'/>'">
		<input type="submit" class="buttonClass" value="私人活動查詢"> <input
			type="button" class="buttonClass" value="歷史活動查詢"
			onclick="javascript:window.location='<c:url value='/ForViewHisActPage.do'/>'">
	</form>
	</div>

	
	<div class="outerblock">
	<c:choose>
		<c:when test="${requestScope.size!=0}">
			<c:forEach var="Acts" items="${requestScope.Acts}">
				
				
				
					活動性質： <c:choose>
							<c:when test="${Acts.getAct_property() == 1}">
								<c:out value="公開活動" />
								<br>
							</c:when>
							<c:when test="${Acts.getAct_property() == 0}">
								<c:out value="私人活動" />
								<br>
							</c:when>
						</c:choose> 活動標題：<c:out value="${Acts.getAct_title()}" /><br> 活動時間：<c:out
							value="${Acts.getAct_time().toString()}" /><br> 活動地點：<c:out
							value="${Acts.getAct_location()}" /><br> 活動內容：<c:out
							value="${Acts.getAct_content()}" /><br>
						
						
						<form method="POST" action="<c:url value='/JoinOrQuit.do'/>">
							<input type="hidden" name="Act_id" value="${Acts.getAct_id()}">
							<input type="submit" class="buttonClassblue" value="退出該活動"
								name="switch"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
								type="button" class="buttonClassblue" value="觀看活動詳情"
								onclick="this.form.action='<c:url value='/ShowThisAct.do'/>';this.form.submit();">
						</form>
				
				<hr>
							
			</c:forEach>
		</c:when>
		<c:otherwise>
			<h2 style="font-family:'微軟正黑體';">沒有活動</h2>
		</c:otherwise>
	</c:choose>
	
	</div>
	
	<div style="margin:10px;">
	<input type="button" class="buttonClass" value="回活動首頁"
		onclick="javascript:window.location='<c:url value='/pages/ActivityIndex.jsp'/>'">
	</div>
	
</body>
</html>