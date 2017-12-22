<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8" />
<title>新檢舉</title>

<link rel="stylesheet" href="<c:url value='/css/sunny/fontsize1.1/jquery-ui-1.10.4.custom.css'/>">

<script src="<c:url value='/js/jquery-1.10.2.js'/>"></script>
<script src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>
<script src="<c:url value='/js/jquery.validate.js'/>"></script>
<script src="<c:url value='/js/messages_zh_TW.js'/>"></script>

<style>
label {
	display: inline-block;
	width: 5em;
}

input.text {
	margin-bottom: 12px;
	width: 300px;
	padding: .4em;
	font-size: 14px;
	font-family: '微軟正黑體';
}

select {
	margin-bottom: 12px;
	width: 200px;
	padding: .4em;
	font-size: 16px;
	font-family: '微軟正黑體';
}
</style>

<script>
	$(function() {
		$(".buttonClass").button();

		$("#summitid").click(function() {
			$('#accuse').submit();
			parent.$().message("您已送出檢舉！");
			parent.$('#newreport').dialog('close');
			window.location.reload();
		});

		$(document).keyup(function(){
			if($("#other_cause").val() != "") {
				$("#summitid").show();
			}
			else{
				$("#summitid").hide();
			}
		});

		$("#accuseId").val(window.location.search.slice(9));
	});

	function res() {
		if(document.getElementById('reason').value == '5.其他') {
			document.getElementById('other_cause').style.display = "inline";
			$("#summitid").hide();
		}
		else {
			document.getElementById('other_cause').style.display = "none";
			$("#summitid").show();
		}
	}

</script>

</head>

<body>

<form id="accuse" name="accuse" action="<c:url value='/AccusePostServlet.do'/>" method="post">
<input type="hidden" name="post_id" id="accuseId" value="${post_id}">
<label for="reason">檢舉原因:</label>
<select name="rep_cause" id="reason" onchange="res()">
<option value="1.廣告性質之貼文">1.廣告性質之貼文</option>
<option value="2.人身攻擊之貼文">2.人身攻擊之貼文</option>
<option value="3.與貓咪無關之貼文">3.與貓咪無關之貼文</option>
<option value="4.傷害貓咪之貼文">4.傷害貓咪之貼文</option>
<option value="5.其他" >5.其他</option>
</select><br>
<input type="text" name="other_cause" id="other_cause" class="text ui-widget-content ui-corner-all" style="display: none;"><br>
<input type="submit" id="summitid" value="送出" class="buttonClass">
<input type="reset" value="重置" class="buttonClass">
</form>

</body>

</html>