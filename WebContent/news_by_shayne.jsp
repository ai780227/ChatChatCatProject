<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>News</title>

	<link rel="stylesheet" href="<c:url value='/js/jQuery-TE_v.1.4.0_TextEdit/jquery-te-1.4.0.css'/>">
	<script type="text/javascript" src="<c:url value='/js/jquery-2.1.0.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/ckeditor/ckeditor.js'/>"></script>
	

</head>
<body>
<form action="<c:url value='/NewsMaintain'/>" method='POST'>
標題:<input type='text' id='title'>${errorMsg.errorTitleEmpty }
	<textarea name="editor1" id="editor1" rows="1000px" cols="500px">
            This is my textarea to be replaced with CKEditor.
	</textarea>
	<br>${errorMsg.errorContentEmpty }<br>
	<input type='button' id='btn' value='btn'>
	<input type='submit' value='send'>
來源:<input type='text' id='source'>${errorMsg.errorSourceEmpty }
來源連結:<input type='text' id='link'>
<script>
	// Replace the <textarea id="editor1"> with a CKEditor
	// instance, using default configuration.
	CKEDITOR.replace( 'editor1' );
	CKEDITOR.config.height = 500;
	CKEDITOR.config.width = 800;
	$('#btn').bind('click',function() {
						console.log($('#content').val());
                	});
</script>
</form>
</body>
</html>