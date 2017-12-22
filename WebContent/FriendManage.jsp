<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ccc.model.bean.*"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>朋友管理頁面</title>

<link rel="stylesheet" href="<c:url value='/css/sunny/fontsize1.1/jquery-ui-1.10.4.custom.css'/>">

<script type="text/javascript" src="<c:url value='/js/jquery-1.10.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>

<script>
	$(function() {
		$(".block").hover(function(){
				$(this).toggleClass("block2",300).clearQueue();
		});
		/*
					var availableTags = [
		       		      "ActionScript",
		       		      "AppleScript",
		       		      "Asp",
		       		      "BASIC",
		       		      "C",
		       		      "C++",
		       		      "Clojure",
		       		      "COBOL",
		       		      "ColdFusion",
		       		      "Erlang",
		       		      "Fortran",
		       		      "Groovy",
		       		      "Haskell",
		       		      "Java",
		       		      "JavaScript",
		       		      "Lisp",
		       		      "Perl",
		       		      "PHP",
		       		      "Python",
		       		      "Ruby",
		       		      "Scala",
		       		      "Scheme"
		       		    ];
					$( "#MemberSearch" ).autocomplete({
					      source: availableTags
					    });*/
	});

</script>

<style>
.block {
	display:inline-block;
	position: relative;
	border-style: outset;
	border-color: orange;
	border-radius: 60px;
	border-width: 7px;
	margin: 15px;
	padding:15px;
	background-image:url('<c:url value='/images/paperbackground.jpg'/>');
	box-shadow: 10px 10px 15px black;
}
.block2 {
	border-color: yellow;
	box-shadow: 0px 0px 20px yellow;
	top: -10px;
	left: -5px;
}
.textblock {
	display:inline-block;
	border-style: outset;
	border-color: white;
	border-radius: 60px;
	border-width: 2px;
	margin: 4px;
	padding:8px;
	background-image:url('<c:url value='/images/paperbackground_white.jpg'/>');
	box-shadow: 2px 2px 2px black;
}
.iconinvisible{
	position: absolute;
	top:5px;
	right:5px;
	opacity: 0;
	filter: alpha(opacity =    0);
}
.iconvisible{
	opacity: 1;
	filter: alpha(opacity =    100);
}
input.text {
	margin-bottom: 12px;
	width: 200px;
	padding: .4em;
	font-size: 16px;
	font-family: '微軟正黑體';
}
.thumbnail {
	border-style: outset;
	border-color: black;
	border-radius: 40px; 	
	box-shadow: 2px 2px 2px black; 
	margin:5px;
}
</style>

<!-- 晸芳的會員搜尋 -->
<script type="text/javascript">
	
$(function() {
	$(".block").hover(function(){
		$(".iconinvisible").toggleClass("iconvisible",300).clearQueue();
		$(this).toggleClass("block2",300).clearQueue();
	});	
	
	$('#MemberSearch').keyup(function() {
		clearTimeout($.data(this,'timer'));
		var wait=setTimeout(function(){
		var str=document.getElementById("MemberSearch").value;
		console.log(str);
		
		$.ajax( { 'url'  :'<c:url value="/SearchMembersServlet.do" />',
				  'type' :'post',
				  'data' :{'Member':str,
				   	       'post_type':'5'},
				  'datatype':'text',
				  'success':function(data) {
							if(data==null||data==''||data.length==0){
								$('#div1').html("");
								$('#div1').append("找不到會員");
								  
							}
				  			if(data!='' || data!=null ||data.length!=0) {
				  				var json = eval('(' + data + ')');
				  				$('#div1').html("");
				  				if(json.jsonArray.length!=0){
				  					for(var i=0;i<json.jsonArray.length;i++){
				  						var path;
				  						if(json.jsonArray[i].member_pic_path != null) {
				  							path = "<c:url value='/'/>" + "userImages/" + json.jsonArray[i].member_id + "/" + json.jsonArray[i].member_pic_path;
				  						}				  							
				  						else {
				  							path = "<c:url value='/images/cat2.jpg'/>";	
				  						}				  							
						  				$('#div1').append(
						  					/*	"<form name="+json.jsonArray[i].member_id+" method='post' action='<c:url value='/FriendServlet.do' />'>"+	
						  						"<li onclick='document."+json.jsonArray[i].member_id+".submit();'>"+
						  						"<img src='<c:url value='/userImages/"+json.jsonArray[i].member_id+"/"+json.jsonArray[i].member_pic_path+"'/>'/>"+	
						  						json.jsonArray[i].member_name+
						  						"<input type='hidden' name ='Member' value='"+json.jsonArray[i].member_id+"'>"+
						  						"<input type='hidden' name='friendship_type' value='PersonalPage'></form></li><BR>"*/
						  					//大頭照：<img src="<c:url value='/${requestScope.Member_friend["m_id"]}/${requestScope.Member_friend["m_pic_path"]}'/>"/>
						  					
						  						"<div class='block'  onclick='document."+ json.jsonArray[i].member_id +".submit();'>"
						  							+ "<img src='" + path + "' width='160' height='auto' class='thumbnail'/>"
						  							+ "<br>"
						  							+ "<div style='text-align:center;'>"
						  								+ "<span class='textblock'>" + json.jsonArray[i].member_name + "</span>"
						  								+ "<br>"
						  								+ "<span>" + json.jsonArray[i].member_id + "</sapn>"
						  							+ "<div>"
						  							+ "<form name="+json.jsonArray[i].member_id+" method='post' action='<c:url value='/FriendServlet.do' />'>"
							  							+ "<input type='hidden' name='Member' value='" + json.jsonArray[i].member_id + "'>"
								  						+ "<input type='hidden' name='friendship_type' value='PersonalPage'>"
							  						+ "</form>"
						  						+ "</div>"
						  							
						  						/*
						  						<div class="block">
						  							<img src="../images/cat2.jpg" onclick="" width="160" height="auto" class="thumbnail">
						  							<br>
						  							<div style="text-align:center;">
						  								<span class="textblock">小姜</span>
						  								<br>
						  								<span>a854981</span>
						  							</div>
						  							<a href="#"><img src="../images/delete.png" class="iconinvisible"></a>
						  						</div>
						  						*/
						  					);
						  				}
						  				$(".block").hover(function(){
						  					$(this).toggleClass("block2",300).clearQueue();
						  				});
				  					}	
				  				}	
					  		} 
					});
	
			},500);
		$(this).data('timer',wait);
	});
});
</script>


</head>

<body>
	<div style="text-align: center;">
		<form>
			<input type="text" id="MemberSearch" name="text" placeholder="搜尋會員" onblur="javascript:onbler();"
					class="text ui-widget-content ui-corner-all">
			<input type="hidden" name="post_type" value="5">
		</form>
	</div>
	
	<div id="div1">
	
		
		
	<!-- 	
		<div class="block">
		<img src="../images/cat3.jpg" onclick="" width="60" height="60" style="border-radius: 5px;"><span>美江的好友1</span>
		<a href="#"><img src="../images/delete.png" class="iconinvisible"></a>
		</div>
		
		<div class="block">
		<img src="../images/cat4.jpg" onclick="" width="60" height="60" style="border-radius: 5px;"><span>美江的好友2</span>
		<a href="#"><img src="../images/delete.png" class="iconinvisible"></a>
		</div>
		
		<div class="block">
		<img src="../images/cat2.jpg" onclick="" width="60" height="60" style="border-radius: 5px;"><span>美江的好友3</span>
		<a href="#"><img src="../images/delete.png" class="iconinvisible"></a>
		</div>
		-->
		
		
		<!-- 		
		<div class="block">
		<img src="../images/cat3.jpg" onclick="" width="160" height="auto" class="thumbnail"><br><div style="text-align:center;"><span class="textblock">廖小凱</span><br><span>cecj001</span></div>
		<a href="#"><img src="../images/delete.png" class="iconinvisible"></a>
		</div>
		
		<div class="block">
		<img src="../images/cat4.jpg" onclick="" width="160" height="auto" class="thumbnail"><br><div style="text-align:center;"><span class="textblock">陳小愷</span><br><span>cj4</span></div>
		<a href="#"><img src="../images/delete.png" class="iconinvisible"></a>
		</div>
		
		<div class="block">
		<img src="../images/cat2.jpg" onclick="" width="160" height="auto" class="thumbnail"><br><div style="text-align:center;"><span class="textblock">小姜</span><br><span>a854981</span></div>
		<a href="#"><img src="../images/delete.png" class="iconinvisible"></a>
		</div>
		 -->
		
			<c:choose>
			<c:when test="${MyFriend==null}">
					尚未新增好友
			</c:when>
			
			
			<c:when test="${MyFriend!=null}">
				<c:forEach  var="Member"  items="${MyFriend}">	
				
		
				<div class="block" onclick="document.${Member.getM_id()}.submit();" >
						<c:choose>
							<c:when test="${Member.getM_pic_path()==null}">
								<img src="<c:url value='/images/cat1.jpg'/>" onclick="" width="160" height="auto" class="thumbnail" >
							</c:when>
							<c:when test="${Member.getM_pic_path()!=null}">
								<img src="<c:url value='/userImages/${Member.getM_id()}/${Member.getM_pic_path()}'/>" 
									onclick="" width="160" height="auto" class="thumbnail" >
							</c:when>
						</c:choose>
						<br>
						<div style="text-align:center;">
							<span class="textblock">${Member.m_name}</span>
							<br>
							<span>${Member.m_id}</span>
						</div>		
						<form name="${Member.getM_id()}" method="post" action="<c:url value="/FriendServlet.do" />">
							<input type="hidden" name="friendship_type" value="PersonalPage">
							<input type="hidden" name ="Member" value="${Member.getM_id()}">				
						</form>	
						
					
				
				</div>
				</c:forEach> 
			</c:when>
		</c:choose>
	
	</div>
	
	<script>
		var myfriend = $('#div1').html();
		function onbler() {
			var content = document.getElementById("MemberSearch").value;
	    	if(content==''){
	    		$('#div1').html('');
	    		$('#div1').html(myfriend);    	
			}
		}
	</script>
</body>
</html>