<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>頁籤畫面</title>

<link rel="stylesheet" href="<c:url value='/css/sunny/fontsize1.1/jquery-ui-1.10.4.custom.css'/>">	
	
<script type="text/javascript" src="<c:url value='/js/jquery-1.10.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>

<style>
body {
	padding: 0;
	margin: 0;
}

.class1 {
	width: 837px;
	frameBorder: 0px;
	border: 0px;
	cellspacing: 0px;
	marginwidth: 0px;
	marginheight: 0px;
	overflow: hidden;
	display: block;
}

ul.navigation a,a:visited {
	color: black;
	font-weight: bold;
	text-decoration: none;
}

ul.navigation {
	list-style: none;
}

ul.navigation li {
	background: orange;
	margin: 1px;
	padding: 5px 15px;
	display: block;
	float: left;
}

ul.navigation li.highlight {
	background: white;
}

ul.navigation li.highlight a {
	cursor: default;
}

ul.n1 {
	overflow: hidden;
	width: 801px;
	margin: 0;
	padding: 0;
	padding-left: 36px;
	list-style: none;
}

ul.n1 li {
	float: left;
	margin: 0 -15px 0 0;
}

ul.n1 a {
	float: left;
	position: relative;
	margin: 0 3px;
	padding: 0 39px;
	height: 0;
	line-height: 30px;
	text-transform: uppercase;
	text-decoration: none;
	color: #fff;
	border-left: 15px solid transparent;
	border-right: 15px solid transparent;
	border-bottom: 30px solid #3D3D3D;
	border-bottom-color: rgb(255,195,125);
	opacity: 1;
	filter: alpha(opacity = 100);
	left:-3px;
}

ul.n1 a:hover,ul.n1 a:focus {
	border-bottom-color: rgb(255,127,39);
	opacity: 1;
	filter: alpha(opacity = 100);
	z-index: 2;
}

ul.n1 a:focus {
	outline: 0;
}

ul.n1 li.highlight a {
	z-index: 3;
	border-bottom-color: rgba(168,84,0,1);
	opacity: 1;
	filter: alpha(opacity = 100);
	cursor: default;
}
</style>

<script>
	// $(function() {		    
	//	    $( "#tabs" ).tabs({
	//	        beforeLoad: function( event, ui ) {
	//	          ui.jqXHR.error(function() {
	//	            ui.panel.html(
	//	              "Couldn't load this tab. We'll try to fix this as soon as possible. " +
	//	              "If this wouldn't be a demo." );
	//	          });
	//	        }
	//	      });
	//	    
	//	  });

	$(function() {
		$("ul.navigation li a").on('click', function() {
			//$("ul.navigation li.highlight a").attr('href',$('ul.navigation li.highlight a').attr('temp'));
			//$("ul.navigation li.highlight a").removeAttr('temp');
			$("ul.navigation li.highlight").removeClass();
			$(this).parent().addClass('highlight');
			//$(this).attr('temp',$(this).attr("href"));
			//$(this).removeAttr('href');
		});
		$("ul.n1 li a").on('click', function() {
			//$("ul.navigation li.highlight a").attr('href',$('ul.navigation li.highlight a').attr('temp'));
			//$("ul.navigation li.highlight a").removeAttr('temp');
			$("ul.n1 li.highlight").removeClass();
			$(this).parent().addClass('highlight');
			//$(this).attr('temp',$(this).attr("href"));
			//$(this).removeAttr('href');	
		});	
		
		$('iframe.class1').attr('height',$(parent.parent.window).height()-130);

		$(parent.parent.window).resize(function(){
			$('iframe.class1').attr('height',$(parent.parent.window).height()-130);
		});
	
	});
	
</script>

</head>

<body style="background-color: #ffe6b5; overflow-x:hidden;">

	<ul class='n1'>
		<li class="highlight"><a href="<c:url value='/pages/All.jsp'/>" target=f5>全部</a></li>
		<li><a href="<c:url value='/pages/Life.jsp'/>" target=f5>生活</a></li>
		<li><a href="<c:url value='/pages/knowledge.jsp'/>" target=f5>知識</a></li>
		<li><a href="<c:url value='/pages/Store.jsp'/>" target=f5>店家</a></li>
		<li><a href="<c:url value='/pages/Adopt.jsp'/>" target=f5>領養</a></li>
		<li><a href="<c:url value='/pages/News.jsp'/>" target=f5>新聞</a></li>
	</ul>

	<iframe class="class1" src="<c:url value='/pages/All.jsp'/>" name=f5 scrolling="no"></iframe>

</body>
</html>