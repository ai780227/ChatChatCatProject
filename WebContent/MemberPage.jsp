<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${requestScope.Member_friend['m_name']}的個人版面</title>

<link rel="stylesheet" href="<c:url value='/css/sunny/fontsize1.1/jquery-ui-1.10.4.custom.css'/>">
<link rel="stylesheet" href="<c:url value='/css/post.css'/>">
	
<script type="text/javascript" src="<c:url value='/js/jquery-1.10.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.elastic.source.js'/>"></script>

<style>
body {
	overflow-x:hidden;
}
.catblock {
	position: relative;
	border-style: outset;
	border-color: orange;
	border-radius: 60px;
	border-width: 7px;
	margin: 30px auto;
/*	background-image:url('../images/paperbackground.jpg');*/
	box-shadow: 10px 10px 15px black;
	width: 80%;
	min-height:250px;
	font-family: "微軟正黑體";
}
.catblock2 {
	border-color: yellow;
}
.thumbnail {
	position: relative;
	border-style: outset;
	border-color: black;
	border-radius: 140px; 	
	box-shadow: 2px 2px 2px black; 
	margin:30px;
	left:0px;
	top:10px;
}
.textblock {	
	display:inline-block;
	border-style: outset;
	border-color: white;
	border-radius: 60px;
	border-width: 2px;
	padding:8px;
	box-shadow: 2px 2px 2px black;
	font-family: "微軟正黑體";
	font-weight: bold;
	font-size:18px;
}
.catinfoblock {
	display:inline-block;
	text-align:center; 
	width: 260px; 
	padding: 10px; 
	line-height:30px;
	font-family: "微軟正黑體";
}
.catblock td{
width:115px;
text-align:center;
	font-family: "微軟正黑體";
}

.meminfo {
	position: relative;
	border-style: outset;
	border-color: #0072E3;
	border-radius: 60px;
	border-width: 7px;
	margin: 30px auto;
	box-shadow: 10px 10px 15px black;
	width: 95%;
	min-height:270px;
	font-family: "微軟正黑體";
	font-weight: bold;
}

.memthumb {
	position: relative;
	border-style: outset;
	border-color: blue;
	border-radius: 40px; 	
	box-shadow: 2px 2px 2px black; 
	margin:30px;
	left:35px;
	top:0px;
	font-family: "微軟正黑體";
}

.memtextblock {	
	display:inline-block;
	border-style: outset;
	border-color: blue;
	border-radius: 60px;
	border-width: 2px;
	padding:8px;
	background: #ACD6FF;
	box-shadow: 2px 2px 2px black;
	font-size:24px;
	font-family: "微軟正黑體";
	font-weight: bold;
}
</style>

<script>

$(function() {
		$(".buttonClass").button();
		
	 	$( ".elastic" ).elastic();
		$(".catblock").hover(function(){
			$(this).toggleClass("catblock2",300).clearQueue();
		});
		var servletCtxPath = '<c:url value="/"/>';
		$('.textblock,.meminfo').css('background-image',"url( '" + servletCtxPath + "images/paperbackground_white.jpg')");
		$('.catblock').css('background-image',"url( '" + servletCtxPath + "images/paperbackground.jpg')");
});	


</script>

</head>

<body>
	
	
	
	
	<table class="meminfo">
	<tr>
			<td rowspan="4">
			
		<c:choose>
			<c:when test="${requestScope.Member_friend['m_pic_path']==null}">
				<img src="<c:url value='/images/cat2.jpg'/>" width="160" height="auto" class="memthumb" style="border-radius: 25px;"/>
			</c:when>
			<c:when test="${requestScope.Member_friend['m_pic_path']!=null}">
				<img src="<c:url value='/userImages/${requestScope.Member_friend["m_id"]}/${requestScope.Member_friend["m_pic_path"]}'/>"
						 width="160" height="auto" class="memthumb" style="border-radius: 25px;"/>
			</c:when>
		</c:choose>
		
			<div style="text-align:center; width:10em;margin-left:4em">
				<form>
					<input type="hidden"  name="Member" value='${requestScope.Member_friend["m_id"]}'/>
					<input type="hidden"  name="friendship_type" value="Blockadelifted"/>
					<input id='friendship' type="hidden"  name="friendship" value='${requestScope.Member_friend["isMyFriend"]}'/>
				 	<c:choose>
						<c:when test="${applicationScope.user.getM_id()==requestScope.Member_friend['m_id']}">
							<input type="hidden"  name="Member" value='${requestScope.Member_friend["m_id"]}'/>
							<input id="fribtn" class="buttonClass" type="button" value='進入會員各版' onclick="this.form.action='<c:url value='/FriendServlet.do'/>';this.form.submit();" />
						</c:when>
						<c:when test="${applicationScope.user.getM_id()!=requestScope.Member_friend['m_id']}">
							<input id="fribtn" class="buttonClass" type="button" name='${requestScope.Member_friend["isMyFriend"]}'/>
						</c:when>
					</c:choose>		
				</form>
			</div>
			
		</td>
		
		<td width="180px" rowspan="3">
		
		<div class="memtextblock">
			<c:out value="${requestScope.Member_friend['m_name']}" />
			
		</div>
			
			
		</td>
			
			
			
		<td width="240px">
				<c:choose>
					<c:when test="${requestScope.Member_friend['m_birth']==null}">
					</c:when>
					<c:when test="${requestScope.Member_friend['m_birth']!=null}">生日：${requestScope.Member_friend["m_birth"]}
					</c:when>
				</c:choose>
			</td>
			
			</tr>
		<tr>
			<td width="240px">
			
				<c:choose>
					<c:when test="${requestScope.Member_friend['m_email']==null}">
					</c:when>
					<c:when test="${requestScope.Member_friend['m_email']!=null}">E-mail：${requestScope.Member_friend["m_email"]}
					</c:when>
				</c:choose>
				</td>
		</tr>
		<tr>
			<td width="240px">
				
				<c:choose>
					<c:when test="${requestScope.Member_friend['m_sex']==null}"></c:when>
					<c:when test="${requestScope.Member_friend['m_sex']!=null}">性別：<img src="<c:url value='/images/${requestScope.Member_friend["m_sex"]}.png' />"/></c:when>
				</c:choose>
				
		</td>
		</tr>
		<tr>
			<td colspan="2" width="320px">
				
				<c:choose>
					<c:when test="${requestScope.Member_friend['m_intro']==null}">
					</c:when>
					<c:when test="${requestScope.Member_friend['m_intro']!=null}">自我簡介：${requestScope.Member_friend["m_intro"]}
					</c:when>
				</c:choose>
			</td>
		</tr>
	</table>
	
	<form action="PhotoServlet.do" method="post">
		<input type="hidden" name="photos" value="viewFriendPhotoscat">
		<input type="hidden" name="m_id" value="${requestScope.Member_friend['m_id']}">
		<input type="submit" class="buttonClass" value="瀏覽貓咪照片" >		
	</form>

	
	<c:choose>
		<c:when test="${requestScope.Member_friend['m_cats']==null}">沒有貓<HR></c:when>
		<c:when test="${requestScope.Member_friend['m_cats']!=null}">
			<c:forEach var="Cat" items="${requestScope.Member_friend['m_cats']}">
				<div class="catblock">
					<table>
						<tr>
							<td>
								<c:choose>							
									<c:when test="${Cat.c_pic_path==null}">
										<a href="#">
											<img src="<c:url value='/images/cat2.jpg'/>"
												 width="160" height="auto" class="thumbnail"/>
										</a>
									</c:when>
									<c:otherwise>
										<a href="#">
											<img src="<c:url value='/userImages/${requestScope.Member_friend["m_id"]}/${Cat.c_pic_path}'/>"
												 width="160" height="auto" class="thumbnail"/>
										</a>
									</c:otherwise>													
								</c:choose>
							</td>
							<td><div class="textblock">${Cat.c_name}</div></td>
							<td>
								<div class="catinfoblock">	
									<div>											
										<c:choose>
											<c:when test="${Cat.c_age==null}">
											</c:when>
											<c:when test="${Cat.c_age!=null}">出生年：${Cat.c_age}
											</c:when>
										</c:choose>
									</div>										 									
									<c:choose>
										<c:when test="${Cat.c_breed==null}">
										</c:when>
										<c:when test="${Cat.c_breed!=null}"><div>品種：${Cat.c_breed}</div>
										</c:when>
									</c:choose>											
									<c:choose>
										<c:when test="${Cat.c_sex==null}">
										</c:when>
										<c:when test="${Cat.c_sex!=null}"><div>性別：<img src="<c:url value='/images/${Cat.c_sex}.png' />"/></div>
										</c:when>
									</c:choose>										
									<c:choose>
										<c:when test="${Cat.c_intro==null}">
										</c:when>
										<c:when test="${Cat.c_intro!=null}"><div>簡介：${Cat.c_intro}</div>
										</c:when>
									</c:choose>									
								</div>
							</td>
							</tr>
					</table>
				</div>
			</c:forEach>
		</c:when>	
	</c:choose>
	
<!-- 	
<div class="block">
	<table>
		<tr>
		  <td><img src="../images/cat2.jpg" onclick="" width="160" height="auto" class="thumbnail"></td>
		  <td><div class="textblock">小白大白貓</div></td> 
		  <td><div class="catinfoblock"><div>出生年：1990</div><div>品種：米克斯</div><div>性別：icon-male</div><div>簡介：這是小白，我養的第二隻貓~</div></div></td>
		</tr>
	</table>
</div>
 -->	
	

	
	
	<script>
	$(function() {
		var fribtn = '${requestScope.Member_friend["isMyFriend"]}';
		if(fribtn == 'Addfriend')
			fribtn = '加入好友';
		else if(fribtn == 'Wait')
			fribtn = '等待回應';
		else if(fribtn == 'Friendsblockade')
			fribtn = '封鎖好友';
		else if(fribtn == 'Unblock')
			fribtn = '解除封鎖';
		$('#fribtn').val(fribtn);
	})
	
		$('#fribtn').bind('click',function() {
				if( $('#fribtn').val() != '等待回應') {
							$.ajax( { 'url'  :'<c:url value="/FriendServlet.do" />',
									  'type' :'post',
									  'data' :{ 'Member':'${requestScope.Member_friend["m_id"]}',
										  		'friendship_type':"Blockadelifted",
										  		'friendship':$('#fribtn').attr('name') },
									  'datatype':'text',
									  'success':function(data) {
										  			if(data!='' || data!=null) {
										  				$('#friendship').val(data);
										  				
										  				$('#fribtn').attr('name',data);
										  				if(data == 'Addfriend')
										  					data = '加入好友';
										  				else if(data == 'Wait')
										  					data = '等待回應';
										  				else if(data == 'Friendsblockade')
										  					data = '封鎖好友';
										  				else if(data == 'Unblock')
										  					data = '解除封鎖'; 
										  				$('#fribtn').val(data);
										  			}
											  } 
								} );
				}
		});		
	</script>
	
	
	
	
	<div class="post"></div>
<input type="hidden" id="btn_submit">
<script type="text/javascript">
var page = 0, size = 5;
var post_type = 0, post_property = 0;
var user = "${user}";
var user_id = "${user.getM_id()}";
var m_id = "${requestScope.Member_friend['m_id']}";
var manager = "${manager}";
var PostURL = "<c:url value='/ViewPostsServlet.do'/>";
var SearchURL = "<c:url value='/SearchServlet.do'/>";
var LikeURL = "<c:url value='/LikePostServlet.do'/>";
var FavoriteURL = "<c:url value='/AddPostToFavoriteServlet.do'/>";
var ResponseURL = "<c:url value='/CreateResponseServlet.do'/>";
var AccuseURL = "<c:url value='/pages/NewReport.jsp'/>";
var imgURL = "<c:url value='/'/>";
var ServletCtxURL = "<c:url value='/'/>";
var DelResURL = "<c:url value='/DeleteResponseServlet.do'/>";
$(".buttonClass").button();
</script>
<script src="<c:url value='/js/post.js'/>"></script>
	
	
	
<!-- 	
	<p>
	<a href="#" onClick="return false;"><span>小白</span> <img src="../images/cat2.jpg"
		onclick="" width="120" height="120">
	</a>
	<span class="waterwheelcarousel">
	<img src="../images/cat1.jpg" alt="Image 1" width="120" height="120" />
	<img src="../images/cat1.jpg" alt="Image 2" width="120" height="120" />
	<img src="../images/cat1.jpg" alt="Image 3" width="120" height="120" />
	<img src="../images/cat1.jpg" alt="Image 4" width="120" height="120" />
	<img src="../images/cat1.jpg" alt="Image 5" width="120" height="120" />
	</span>
	</p>
	
	<p>
	<a href="#" onClick="return false;"><span>小花</span> <img src="../images/cat3.jpg"
		onclick="" width="120" height="120">
	</a>
	</p>
	
	<p>
	<a href="#" onclick="return false"><span>喵喵</span> <img src="../images/cat4.jpg"
		onclick="" width="120" height="120">
	</a>
	</p>
	
	
	
	<div class="scroll" style="width: 700px;">
	<div style="position: relative; margin: 10px; padding:10px; width: 600px; border-style:groove; border-width:5px; border-color:blue; border-radius: 25px; background: rgb(223,243,249)">
	<div style="position: relative;">
	<a href="#" style="position:absolute; right:5px; top:5px;"><img src="../images/like.png"></a>
	<a href="#" style="position:absolute; right:5px; top:35px;"><img src="../images/favorite.png"></a>
	<a href="#" style="position:absolute; right:5px; top:65px;"><img src="../images/edit.png"></a>
	<a href="#" style="position:absolute; right:5px; top:95px;"><img src="../images/delete.png"></a>
	</div>
	PO文者：會員01<br/>
	內容：<br/>
	LALALALA<br/>
	LALALALA<br/>
	LALALALA<br/>
	LALALALA<br/>
	LALALALA<br/>
	</div>
	<div style="position: relative; margin: 10px; padding:10px; width: 600px; border-style:groove; border-width:0px 5px 0px 5px; border-color:green; border-radius: 25px;; background: rgb(240,250,211)">
	留言者：<input id="comment01" type="text" name="comment01" placeholder="我要留言" class="text ui-widget-content ui-corner-all" style="position: relative; left: 2px;"><br/>
	<span style="position:absolute;vertical-align:middle;top:50%;">內容：</span>
	<textarea cols="60" rows="1" placeholder="我要留言" style="position: relative; left: 52px; resize: none; max-height:240px; margin-top:5px;"
		class="elastic text ui-widget-content ui-corner-all"></textarea>
		<br/>
	</div>
	<div style="position: relative; margin: 10px; padding:10px; width: 600px; border-style:groove; border-width:5px; border-color:green; border-radius: 25px;; background: rgb(240,250,211)">
	留言者：AAA<br/>
	內容：LALALALA<br/>
	</div>
	<div style="position: relative; margin: 10px; padding:10px; width: 600px; border-style:groove; border-width:5px; border-color:green; border-radius: 25px;; background: rgb(240,250,211)">
	留言者：AAA<br/>
	內容：LALALALABBBBBBBBBBBBBBBBBB<br/>
	<div class="next"><a href="othercontent.html">next page</a></div>
	</div>
	</div>
 -->	
	
</body>
</html>