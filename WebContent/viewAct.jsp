<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Act</title>
<link rel="stylesheet" href="<c:url value='/css/newspaper/jquery-ui-1.10.4.custom.css'/>">
<link rel="stylesheet" href="<c:url value='/css/post.css'/>">

<script type="text/javascript" src="<c:url value="/js/jquery-2.1.0.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/jquery-ui-1.10.4.custom.js" />"></script>
<script src="<c:url value='/js/jquery.elastic.source.js'/>"></script>
	<script type="text/javascript"
      src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDTwtDHP39nM6NcX2VfMNNdI8ePbb3vuR4&sensor=false">
    </script>
  	<script type="text/javascript" src="<c:url value="/js/checkMap.js" />"></script>


<style>
.outerblock {

border-radius: 30px;
text-align:center;
font-size: 20px;
padding-top:20px;
padding-left:40px;
font-family:'微軟正黑體';
line-height:36px;
color:#001188; 
text-shadow: 0px 0px 5px #cca6a6;
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

</style>


<script>
	$(function() {
		$("#radiospan").buttonset();

		$("#summitid").click(function() {
			var string = $("textarea").val();
			string = string.replace(/\n/g, '<br>');
			string = string.replace(/(\n|\r)/g, '<br>');
			string = string.replace(/\s/g, "&nbsp;");
			$("textarea").val(string);
		});

	});
</script>


</head>
<body onload="initialize()">

	<div class="outerblock">
	發起人：<c:out value="${requestScope.thisAct.getMember().m_name}"/><br>
	活動標題：<c:out value="${requestScope.thisAct.getAct_title()}"/><br>
	活動時間：<c:out value="${requestScope.thisAct.getAct_time().toString()}"/><br>
	<span style="margin-left:130px;">活動地點：<input type="text" name="act_location" id="address" readonly="readonly" style="font-family:'微軟正黑體'; color:#001188; text-shadow: 0px 0px 5px #cca6a6; font-weight:bold; font-size: 20px; cursor:default; background: none; border:0; width:225px" value="${requestScope.thisAct.getAct_location()}"></span>
			<div id="map-canvas" style="margin:10px 0px 20px 0px; padding-left:240px; width: 500px; height: 350px; border-radius:30px; box-shadow: 5px 5px 15px black;"></div>
	活動內容：<c:out value="${requestScope.thisAct.getAct_content()}"/><br>
	參加人數：<c:out value="${requestScope.count}"/><br>
	<c:if test="${requestScope.yesOrNo == true}">
		<form	method="post" action="<c:url value='/JoinOrQuit.do'/>">
			<input type="hidden" value="${requestScope.thisAct.getAct_id()}" name="Act_id">
			<input type="hidden" value="frmoViewAct" name="where">
			<input type="submit" value="參加該活動" name="switch" class="buttonClass">
		</form>
	</c:if>






	<div style=" border: solid 2px orange; text-align:center; line-height:35px; border-radius:50px; padding:0px 40px 20px 40px; margin:10px 20px 0px 0px; background:rgb(255,223,252); font-family:'微軟正黑體'; font-size: 18px;">
	<p style="font-size:24px; text-weight:bold; text-shadow: 0px 0px 5px #cca6a6; color:#001188; margin-bottom:10px;" id="CreatePost">發布貼文</p>
	<form action="<c:url value='/CreateActPostServlet.do'/>" enctype="multipart/form-data" method="post">
		<input type="hidden" name="post_type" value="5">

	<span>性質：</span>
		<span id="radiospan"> <input id="public" type="radio" name="post_property"
				value="1" checked><label for="public" style="text-shadow:none; font-size: 16px;">公開</label> <input
				id="private" type="radio" name="post_property" value="2"><label
				for="private" style="text-shadow:none; font-size: 16px;">好友</label></span><br>

	內容：
		<textarea name="post_content" cols="40" rows="5" style="margin: 10px;"></textarea><br>
	<span style="margin-left: 150px;">照片：</span>
		<input type="file" name="pic" id="pic" multiple style="font-family: '微軟正黑體'; font-size: 16px; color:black;"><br>
		<input type="hidden" value="${requestScope.thisAct.getAct_id()}" name="Act_id">
		<input id="summitid" type="submit" value="送出" style="margin:5px; font-size: 16px; padding:2px 15px; ">
		<input type="reset" value="重置" style="margin:5px; font-size: 16px; padding:2px 15px; ">
	</form>
	</div>

	</div>

		<div class="actPost">
		<%-- <c:forEach var="aPost" items="${posts}">
			<form method="post">
				Post_ID=
				<input type="button" value="${aPost.post_id} Go to Member's Page" onclick="this.form.action='<c:url value='/GetMemberInfoByPostServlet.do'/>';this.form.submit();"><br>
				<input type="button" value="誰按讚:${aPost.like_count}人" onclick="this.form.action='<c:url value='/GetWhoLikeServlet.do'/>';this.form.submit();">
				<input type="button" value="加入最愛" onclick="this.form.action='<c:url value='/AddPostToFavoriteServlet.do'/>';this.form.submit();">
				<input type="button" value="按讚" onclick="this.form.action='<c:url value='/LikePostServlet.do'/>';this.form.submit();"><br>
				<c:choose>
					<c:when test="${userId == aPost.getMemberBean().m_id}">
						<input type="button" value="編輯貼文" onclick="this.form.action='<c:url value='/ViewAPostServlet.do'/>';this.form.submit();">
						<input type="button" value="刪除貼文" onclick="this.form.action='<c:url value='/DeletePostServlet.do'/>';this.form.submit();"><br>
					</c:when>
					<c:otherwise>
						<input type="button" value="檢舉貼文" onclick="this.form.action='<c:url value='/ViewAPostServlet.do'/>';this.form.submit();"><br>
					</c:otherwise>
				</c:choose>
				<input type="hidden" name="post_id" value="${aPost.post_id}">
				Content=${aPost.post_content}<br>
				Time=${aPost.post_time}<br>
				<c:if test="${aPost.getActivityBean().act_id != null}">
					ACT_ID=${aPost.getActivityBean().act_id}<br>
				</c:if>
				M_ID=
				<input type="button" value="${aPost.getMemberBean().m_id}" onclick="this.form.action='<c:url value='/FriendServlet.do'/>';this.form.submit();"><br>
				<input type="hidden" name="Member" value="${aPost.getMemberBean().m_id}">
				<input type="hidden" name="friendship_type" value="PersonalPage">
				Property=${aPost.post_property}, Type=${aPost.post_type}<br>
			<c:if test="${postPictures != null}">
				<c:forEach var="aPostPicture" items="${postPictures}">
					<c:if test="${aPost.post_id eq aPostPicture.getPostBean().post_id}">
						<img src="<c:url value='/${aPostPicture.getPostBean().getMemberBean().m_id}/${aPostPicture.getPictureBean().pic_file_path}'/>"/>
					</c:if>
				</c:forEach>
			</c:if><br>
			</form>
		</c:forEach> --%>
		</div>


<script type="text/javascript">
var page = 0, size = 3;
var user = "${user}";
var user_id = "${sessionScope.user.getM_id()}";
var Act_id = "${requestScope.thisAct.getAct_id()}";
var manager = "${manager}";
var ActPostURL = "<c:url value='/ShowThisActJSONServlet.do'/>";
var LikeURL = "<c:url value='/LikePostServlet.do'/>";
var FavoriteURL = "<c:url value='/AddPostToFavoriteServlet.do'/>";
var ResponseURL = "<c:url value='/CreateResponseServlet.do'/>";
var AccuseURL = "<c:url value='/pages/NewReport.jsp'/>";
var imgURL = "<c:url value='/'/>";
var DelResURL = "<c:url value='/DeleteResponseServlet.do'/>";
</script>
<script src="<c:url value='/js/actpost.js'/>"></script>
	<div style="text-align:center; margin: 10px;">
	<input type="button" class="buttonClass" value="回活動首頁" onclick="javascript:window.location='<c:url value='/pages/ActivityIndex.jsp'/>'">
	</div>
</body>
</html>