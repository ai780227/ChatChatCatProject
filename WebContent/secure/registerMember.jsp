<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<c:url value='/js/jquery-2.1.0.js'/>"></script>
</head>
<body>
<form action="<c:url value='/RegisterServlet'/>" method="POST">
*帳號<input type="text" id="m_id" name="m_id" placeholder="m_id" value="${param.m_id }"/><span id='checkId'>${errorMsg.errorIdEmpty} ${errorMsg.errorIdFormat} ${errorMsg.errorIdExists}</span><br>
*密碼<input type="text" id="m_pwd" name="m_pwd" placeholder="m_pwd" value="${param.m_pwd }"/>${errorMsg.errorPwdEmpty} ${errorMsg.errorPwdFormat}<br>
*重新輸入密碼<input type="text" id="re_pwd" name="re_pwd" placeholder="re_pwd" value="${param.re_pwd }"/>${errorMsg.errorRePwdEmpty} ${errorMsg.errorPwdDifferent}<br>
*email<input type="text" id="m_email" name="m_email" placeholder="m_email" value="${param.m_email }"/><span id='checkEmail'>${errorMsg.errorEmailEmpty} ${errorMsg.errorEmailFormat} ${errorMsg.errorEmailExists}</span><br>
*名字<input type="text" id="m_name" name="m_name" placeholder="m_name" value="${param.m_name }"/>${errorMsg.errorNameEmpty}<br>
-----<br>
性別<input type="text" id="m_sex" name="m_sex" placeholder="m_sex" value="${param.m_sex }"/><br>
生日<input type="text" id="m_birth" name="m_birth" placeholder="m_birth" value="${param.m_birth }"/><br>
自我簡介<input type="text" id="m_intro" name="m_intro" placeholder="m_intro" value="${param.m_intro }"/><br>
大頭貼<input type="file" id="m_pic_path" name="m_pic_path" placeholder="m_pic_path" value="${param.m_pic_path }"/><br>
------<br>
貓咪<input type="text" id="m_cat" name="m_cat" placeholder="m_cat"/><br>
<input type="submit" value="送出"/>
</form>
<script>
//帳號、信箱ajax驗證
$('#m_id').bind('blur',function() {
				if($('#m_id').val()!='') {
					$.ajax( {'url'		:'<c:url value="/AccountCheckServlet"/>',
							 'type'		:'get',
							 'data'		:{'check' :'m_id',
								 	  	  'm_id'  :$('#m_id').val()},
							 'datatype'	:'text',
							 'success'	:function(data) {
							 				if(data == "此帳號可使用")
												$('#checkId').text(data).css('color','green');
							 				else
												$('#checkId').text(data).css('color','red');
						 				}
							} );
				}
		});
$('#m_email').bind('blur',function() {
				if($('#m_email').val()!='') {
					$.ajax( {'url'		:'<c:url value="/AccountCheckServlet"/>',
							 'type'		:'get',
							 'data'		:{'check'	 :'m_eamil',
										  'm_eamil'  :$('#m_email').val()},
							 'datatype'	:'text',
							 'success'	:function(data) {
									 		if(data == "此信箱可使用")
												$('#checkEmail').text(data).css('color','green');
									 		else
												$('#checkEmail').text(data).css('color','red');
								 		}
							} );
				}
		});
		
</script>
</body>
</html>