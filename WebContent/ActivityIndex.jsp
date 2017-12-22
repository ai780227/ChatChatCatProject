<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ActivityIndex</title>
	
	<link rel="stylesheet"
		href="../css/sunny/fontsize1.1/jquery-ui-1.10.4.custom.css">	
	<script src="../js/jquery-1.10.2.js"></script>
	<script src="../js/jquery-ui-1.10.4.custom.js"></script>
	
	
	<script>
	
	
	$(function() {
		
		$(".buttonClass").button().css({
				"margin":"0px 20px 0px 20px",
		      "font-size":"48px",
		      "box-shadow" : "5px 5px 10px #888888",
		      "color" : "white",
		      "border" : "none",
		      "border-radius" : "25px",
		      "text-shadow" : "3px 3px 7px #555555",
		    });
	
	});
	
	</script>
	
	
	
	<style>
	
	
.orangeBackground {
background: rgb(239,162,67); /* Old browsers */
background: -moz-linear-gradient(top,  rgba(239,162,67,1) 0%, rgba(196,128,86,1) 50%, rgba(189,94,0,1) 51%, rgba(239,201,143,1) 100%); /* FF3.6+ */
background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(239,162,67,1)), color-stop(50%,rgba(196,128,86,1)), color-stop(51%,rgba(189,94,0,1)), color-stop(100%,rgba(239,201,143,1))); /* Chrome,Safari4+ */
background: -webkit-linear-gradient(top,  rgba(239,162,67,1) 0%,rgba(196,128,86,1) 50%,rgba(189,94,0,1) 51%,rgba(239,201,143,1) 100%); /* Chrome10+,Safari5.1+ */
background: -o-linear-gradient(top,  rgba(239,162,67,1) 0%,rgba(196,128,86,1) 50%,rgba(189,94,0,1) 51%,rgba(239,201,143,1) 100%); /* Opera 11.10+ */
background: -ms-linear-gradient(top,  rgba(239,162,67,1) 0%,rgba(196,128,86,1) 50%,rgba(189,94,0,1) 51%,rgba(239,201,143,1) 100%); /* IE10+ */
background: linear-gradient(to bottom,  rgba(239,162,67,1) 0%,rgba(196,128,86,1) 50%,rgba(189,94,0,1) 51%,rgba(239,201,143,1) 100%); /* W3C */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#efa243', endColorstr='#efc98f',GradientType=0 ); /* IE6-9 */
}

.orangeBackground:hover {
background: rgb(255,207,153); /* Old browsers */
background: -moz-linear-gradient(top,  rgba(255,207,153,1) 0%, rgba(196,128,86,1) 50%, rgba(189,94,0,1) 51%, rgba(255,238,191,1) 100%); /* FF3.6+ */
background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(255,207,153,1)), color-stop(50%,rgba(196,128,86,1)), color-stop(51%,rgba(189,94,0,1)), color-stop(100%,rgba(255,238,191,1))); /* Chrome,Safari4+ */
background: -webkit-linear-gradient(top,  rgba(255,207,153,1) 0%,rgba(196,128,86,1) 50%,rgba(189,94,0,1) 51%,rgba(255,238,191,1) 100%); /* Chrome10+,Safari5.1+ */
background: -o-linear-gradient(top,  rgba(255,207,153,1) 0%,rgba(196,128,86,1) 50%,rgba(189,94,0,1) 51%,rgba(255,238,191,1) 100%); /* Opera 11.10+ */
background: -ms-linear-gradient(top,  rgba(255,207,153,1) 0%,rgba(196,128,86,1) 50%,rgba(189,94,0,1) 51%,rgba(255,238,191,1) 100%); /* IE10+ */
background: linear-gradient(to bottom,  rgba(255,207,153,1) 0%,rgba(196,128,86,1) 50%,rgba(189,94,0,1) 51%,rgba(255,238,191,1) 100%); /* W3C */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ffcf99', endColorstr='#ffeebf',GradientType=0 ); /* IE6-9 */
}

.blueBackground {
background: rgb(80,135,198); /* Old browsers */
background: -moz-linear-gradient(top,  rgba(80,135,198,1) 0%, rgba(72,102,120,1) 50%, rgba(32,65,98,1) 51%, rgba(99,127,205,1) 100%); /* FF3.6+ */
background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(80,135,198,1)), color-stop(50%,rgba(72,102,120,1)), color-stop(51%,rgba(32,65,98,1)), color-stop(100%,rgba(99,127,205,1))); /* Chrome,Safari4+ */
background: -webkit-linear-gradient(top,  rgba(80,135,198,1) 0%,rgba(72,102,120,1) 50%,rgba(32,65,98,1) 51%,rgba(99,127,205,1) 100%); /* Chrome10+,Safari5.1+ */
background: -o-linear-gradient(top,  rgba(80,135,198,1) 0%,rgba(72,102,120,1) 50%,rgba(32,65,98,1) 51%,rgba(99,127,205,1) 100%); /* Opera 11.10+ */
background: -ms-linear-gradient(top,  rgba(80,135,198,1) 0%,rgba(72,102,120,1) 50%,rgba(32,65,98,1) 51%,rgba(99,127,205,1) 100%); /* IE10+ */
background: linear-gradient(to bottom,  rgba(80,135,198,1) 0%,rgba(72,102,120,1) 50%,rgba(32,65,98,1) 51%,rgba(99,127,205,1) 100%); /* W3C */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#5087c6', endColorstr='#637fcd',GradientType=0 ); /* IE6-9 */
}

.blueBackground:hover {
background: rgb(153,201,255); /* Old browsers */
background: -moz-linear-gradient(top,  rgba(153,201,255,1) 0%, rgba(86,154,196,1) 50%, rgba(0,95,190,1) 51%, rgba(191,208,255,1) 100%); /* FF3.6+ */
background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(153,201,255,1)), color-stop(50%,rgba(86,154,196,1)), color-stop(51%,rgba(0,95,190,1)), color-stop(100%,rgba(191,208,255,1))); /* Chrome,Safari4+ */
background: -webkit-linear-gradient(top,  rgba(153,201,255,1) 0%,rgba(86,154,196,1) 50%,rgba(0,95,190,1) 51%,rgba(191,208,255,1) 100%); /* Chrome10+,Safari5.1+ */
background: -o-linear-gradient(top,  rgba(153,201,255,1) 0%,rgba(86,154,196,1) 50%,rgba(0,95,190,1) 51%,rgba(191,208,255,1) 100%); /* Opera 11.10+ */
background: -ms-linear-gradient(top,  rgba(153,201,255,1) 0%,rgba(86,154,196,1) 50%,rgba(0,95,190,1) 51%,rgba(191,208,255,1) 100%); /* IE10+ */
background: linear-gradient(to bottom,  rgba(153,201,255,1) 0%,rgba(86,154,196,1) 50%,rgba(0,95,190,1) 51%,rgba(191,208,255,1) 100%); /* W3C */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#99c9ff', endColorstr='#bfd0ff',GradientType=0 ); /* IE6-9 */
}

.greenBackground {
background: rgb(80,198,86); /* Old browsers */
background: -moz-linear-gradient(top,  rgba(80,198,86,1) 0%, rgba(77,120,72,1) 50%, rgba(32,98,33,1) 51%, rgba(99,205,126,1) 100%); /* FF3.6+ */
background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(80,198,86,1)), color-stop(50%,rgba(77,120,72,1)), color-stop(51%,rgba(32,98,33,1)), color-stop(100%,rgba(99,205,126,1))); /* Chrome,Safari4+ */
background: -webkit-linear-gradient(top,  rgba(80,198,86,1) 0%,rgba(77,120,72,1) 50%,rgba(32,98,33,1) 51%,rgba(99,205,126,1) 100%); /* Chrome10+,Safari5.1+ */
background: -o-linear-gradient(top,  rgba(80,198,86,1) 0%,rgba(77,120,72,1) 50%,rgba(32,98,33,1) 51%,rgba(99,205,126,1) 100%); /* Opera 11.10+ */
background: -ms-linear-gradient(top,  rgba(80,198,86,1) 0%,rgba(77,120,72,1) 50%,rgba(32,98,33,1) 51%,rgba(99,205,126,1) 100%); /* IE10+ */
background: linear-gradient(to bottom,  rgba(80,198,86,1) 0%,rgba(77,120,72,1) 50%,rgba(32,98,33,1) 51%,rgba(99,205,126,1) 100%); /* W3C */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#50c656', endColorstr='#63cd7e',GradientType=0 ); /* IE6-9 */
}

.greenBackground:hover {
background: rgb(153,255,158); /* Old browsers */
background: -moz-linear-gradient(top,  rgba(153,255,158,1) 0%, rgba(97,196,86,1) 50%, rgba(0,190,3,1) 51%, rgba(191,255,207,1) 100%); /* FF3.6+ */
background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(153,255,158,1)), color-stop(50%,rgba(97,196,86,1)), color-stop(51%,rgba(0,190,3,1)), color-stop(100%,rgba(191,255,207,1))); /* Chrome,Safari4+ */
background: -webkit-linear-gradient(top,  rgba(153,255,158,1) 0%,rgba(97,196,86,1) 50%,rgba(0,190,3,1) 51%,rgba(191,255,207,1) 100%); /* Chrome10+,Safari5.1+ */
background: -o-linear-gradient(top,  rgba(153,255,158,1) 0%,rgba(97,196,86,1) 50%,rgba(0,190,3,1) 51%,rgba(191,255,207,1) 100%); /* Opera 11.10+ */
background: -ms-linear-gradient(top,  rgba(153,255,158,1) 0%,rgba(97,196,86,1) 50%,rgba(0,190,3,1) 51%,rgba(191,255,207,1) 100%); /* IE10+ */
background: linear-gradient(to bottom,  rgba(153,255,158,1) 0%,rgba(97,196,86,1) 50%,rgba(0,190,3,1) 51%,rgba(191,255,207,1) 100%); /* W3C */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#99ff9e', endColorstr='#bfffcf',GradientType=0 ); /* IE6-9 */
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	</style>
	
</head>
<body>
	
	
	
	
	
	<div style="text-align:center; margin-top: 60px; margin-bottom: 60px;">
<a href="<c:url value='/ForManageActPage.do'/>" class="buttonClass orangeBackground">管理活動</a>
</div>

	<div style="text-align:center; margin-top: 60px; margin-bottom: 60px;">
<a href="<c:url value='/ForViewPubActPage.do'/>" class="buttonClass blueBackground">瀏覽活動</a>
</div>


	<div style="text-align:center; margin-top: 60px; margin-bottom: 60px;">
<a href="<c:url value='/DefaultInvitationPubPage.do'/>" class="buttonClass greenBackground">接收的邀請</a>
	</div>



	
	
</body>
</html>