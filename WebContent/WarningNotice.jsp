<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page import="javax.servlet.http.HttpServletRequest;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	span{
		color: rgba(31, 82, 31, 0.8);
		font-weight: bold;
		text-decoration: none;
		font-family:'微軟正黑體';
	}
	
	div{
		color: rgba(79,37,0,1);
		font-weight: bold;
		text-decoration: none;
		font-family:'微軟正黑體';
	}
</style>
</head>
<body>

			<%request.setCharacterEncoding("UTF-8");%>
		<span>
			貼文內容:<div>${oneNotice.post_content}</div><br>
			貼文時間:<div> ${oneNotice.post_time}</div><br>
			<c:choose>
				<c:when test="${oneNotice.like_count == 0}">
					按讚數：<div>沒有人稱讚</div><br>
				</c:when>
				<c:otherwise>
					按讚數:<div>${oneNotice.like_count}</div><br>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${oneNotice.post_property==1}">
					公開狀態：<div>所有</div><br>
				</c:when>
				<c:when test="${oneNotice.post_property==2}">
					公開狀態：<div>好友</div><br>
				</c:when>
				<c:when test="${oneNotice.post_property==3}">
					公開狀態：<div>封鎖</div><br>
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${oneNotice.post_type==1}">
					貼文類型：<div>一般動態</div><br>
				</c:when>
				<c:when test="${oneNotice.post_type==2}">
					貼文類型：<div>知識(醫療、飼養)</div><br>
				</c:when>
				<c:when test="${oneNotice.post_type==3}">
					貼文類型：<div>領養</div><br>
				</c:when>
				<c:when test="${oneNotice.post_type==4}">
					貼文類型：<div>貓咪店家</div><br>
				</c:when>
				<c:when test="${oneNotice.post_type==5}">
					貼文類型：<div>活動</div><br>
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${oneNotice.getActivity().act_title==null}" >
					活動名稱：<div>沒有活動</div><br>
				</c:when>
				<c:otherwise>
					活動名稱：<div>${oneNotice.getActivity().act_title}</div><br>
				</c:otherwise>
			</c:choose>
			檢舉原因:<div>${oneNotice.rep_cause}</div><br>
		</span>


		
		
</body>
</html>