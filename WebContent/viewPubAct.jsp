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
background: rgb(255,238,243);
border: solid 5px pink;
font-weight:bold;

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
	<form method="POST" action="<c:url value='/ForViewPubActPage.do'/>">
	
	<div style="text-align:center; margin: 5px;">
		<input type="submit" class="buttonClass" value="公開活動查詢" name="property">
		<input type="button" class="buttonClass" value="私人活動查詢" onclick="javascript:window.location='<c:url value='/ForViewPriActPage.do'/>'">
		<input type="button" class="buttonClass" value="歷史活動查詢" onclick="javascript:window.location='<c:url value='/ForViewHisActPage.do'/>'">	
	</div>
	
	<div style="text-align:center; margin: 10px;">
		<input type="submit" class="buttonClass" value="ALL" name="property">	
		<input type="submit" class="buttonClass" value="準備參加活動" name="property">
	</div>
	
	<div style="text-align:center; margin: 10px;">
		搜尋公開活動 : <input type="text" id="search" class="text ui-widget-content ui-corner-all" style="padding:5px;">
	</div>
	

	</form>		
	
	
		
	<div id="old">
	
	<div class="outerblock">
		<c:choose>
			<c:when test="${requestScope.size!=0}">
				<c:forEach var="Acts" items="${requestScope.Acts}" varStatus="varStatus">  
					
										
							活動性質：
							<c:choose>
								<c:when test="${Acts.getAct_property() == 1}">
									<c:out value="公開活動"/><br>
								</c:when>
								<c:when test="${Acts.getAct_property() == 0}">
									<c:out value="私人活動"/><br>
								</c:when>
							</c:choose>
							活動標題：<c:out value="${Acts.getAct_title()}"/><br>
							活動時間：<c:out value="${Acts.getAct_time().toString()}"/><br>
							活動地點：<c:out value="${Acts.getAct_location()}"/><br>
							活動內容：<c:out value="${Acts.getAct_content()}"/><br>
							<c:choose>
								<c:when test="${Acts.getMember().getM_id()==sessionScope.user.getM_id()}">
									<form method="POST" action="<c:url value='/ShowThisAct.do'/>">
										<input type="hidden" name="Act_id" value="${Acts.getAct_id()}">
										<input type="submit" class="buttonClass" value="觀看活動詳情"> 
									</form>
								</c:when>
								<c:when test="${requestScope.param==0}">
									<form method="POST" action="<c:url value='/JoinOrQuit.do'/>">
										<input type="hidden" class="buttonClass" name="Act_id" value="${Acts.getAct_id()}">
										<input type="submit" class="buttonClassblue" value="參加該活動" name="switch">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" class="buttonClassblue" value="觀看活動詳情" onclick="this.form.action='<c:url value='/ShowThisAct.do'/>';this.form.submit();"> 
									</form>
								</c:when>
								<c:when test="${requestScope.param==1}">
									<form method="POST" action="<c:url value='/JoinOrQuit.do'/>">
										<input type="hidden" name="Act_id" value="${Acts.getAct_id()}">
										<input type="submit" class="buttonClassblue" value="退出該活動" name="switch">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" class="buttonClassblue" value="觀看活動詳情" onclick="this.form.action='<c:url value='/ShowThisAct.do'/>';this.form.submit();"> 
									</form>
								</c:when>					
							</c:choose>
							<c:if test="${!varStatus.last }">
								<hr>
							</c:if>					
				</c:forEach>
			</c:when>
			<c:otherwise>
				<h2 style="font-family:'微軟正黑體';">沒有活動</h2>
			</c:otherwise>
		</c:choose>	
		</div>
		
	</div>
	
		
		<div style="margin:10px;">	
		<input type="button" class="buttonClass" value="回活動首頁" onclick="javascript:window.location='<c:url value='/pages/ActivityIndex.jsp'/>'">
		</div>
		
</body>

<script type="text/javascript">
	//var curl = "<c:url value='/ShowThisAct.do'/>"
	$('#search').keyup(function(){
		clearTimeout($.data(this,'timer'));
		var wait=setTimeout(function(){
		var str = document.getElementById("search").value;
			$.ajax({
					'url'  	:'<c:url value="/ForSearchAct.do"/>',
					'type' 	:'post',
					'data' 	:{'Act':str},
					'datatype':'text',
					'success':function(data){
						var json = eval('('+data+')');	
						if(data==''){
							$('#old').empty();
							//alert("找不到活動資料，請重新輸入。");
							$('#old').html('There is no activities');
							//alert("找不到活動資料，請重新輸入。");
						}
						if(data!=''){	
							$('#old').html('');											
							if(json.forSearch.length!=0){
								for(var i =0;i<json.forSearch.length;i++){
									$('#old').append(
											"<div class='outerblock'>"+
											"活動標題："+json.forSearch[i].act_title+"<br>"+
											"活動時間："+json.forSearch[i].act_time+"<br>"+
											"活動地點："+json.forSearch[i].act_location+"<br>"+
											"活動內容："+json.forSearch[i].act_content+"<br>"+
											"<form method='POST' action='<c:url value='/JoinOrQuit.do'/>'>"+
											"<input type='hidden' name='Act_id' value='"+json.forSearch[i].act_id+"'>"+
											"<input type='submit' class='buttonClassblue' value='參加該活動' name='switch'>"+
											"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
											"<input type='button' class='buttonClassblue' value='觀看活動詳情' onclick=\"this.form.action=\'<c:url value='/ShowThisAct.do'/>\';this.form.submit();\"><br>"+
											"</form></div>"
									);
								}
								$(this).height(500);
							}
						}
						}
					});
			},500);
			$(this).data('timer',wait);
		});
</script>

</html>