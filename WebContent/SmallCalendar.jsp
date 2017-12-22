<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8" />
<title>活動</title>

<link rel="stylesheet" href="<c:url value='/css/sunny/fontsize0.9/jquery-ui-1.10.4.custom.css'/>">
	
<script type="text/javascript" src="<c:url value='/js/jquery-1.10.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>

<style>
body {
	padding: 0;
	margin: 0;
}
</style>

<script>
	var haveact = [ new Date(2014, 3 - 1, 1), new Date(2014, 3 - 1, 10),
			new Date(2014, 3 - 1, 11) ];
	var haveinv = [ new Date(2014, 3 - 1, 20), new Date(2014, 3 - 1, 21),
			new Date(2014, 3 - 1, 22) ];

	$(function() {
		$("#datepicker").datepicker({
			beforeShowDay : sethaveact
		});
		$(document).tooltip();
		setdayclass();
		$(document).on('click', setdayclass);
	});

	function setdayclass() {
		$(".haveactclass").unbind("click").on('click', function() {
			//parent.parent.$('#option').dialog('open');
			alert('User clicked on "foo."');
		}).children().css("background-image", "url('../images/paw-icon.jpg')");
		//children().append("<img src='../images/paw-icon.jpg' style='width: 8px; height: 8px'>")
		$(".haveinvclass").unbind("click").on('click', function() {
			alert('User clicked on "foo."');
		}).children().css("background-image", "url('../images/ajax-loader.gif')");
		//$(".else").unbind( "click" );
		// $(".else").unbind( "click" ).contents().contents().unwrap();
	}

	function sethaveact(date) {
		for ( var i = 0; (i < haveact.length) || (i < haveinv.length); i++) {
			if (haveact[i] - date == 0) {
				return [ true, 'haveactclass', '今天有活動' ];
			} else if (haveinv[i] - date == 0) {
				return [ true, 'haveinvclass', '有活動邀請' ];
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

</body>
</html>