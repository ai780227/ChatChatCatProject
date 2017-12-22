<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>News</title>

	<script type="text/javascript" src="<c:url value='/js/jquery-2.1.0.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/ckeditor/ckeditor.js'/>"></script>
	

</head>
<body>
<form action="<c:url value='/NewsMaintain.mgr'/>" method='POST'>
標題:<input type='text' name='title' value='${param.title }'>${errorMsg.errorTitleEmpty }
	<textarea name="editor" id="editor" rows="1000px" cols="500px">
           ${param.editor1 }
	</textarea>
	<br>${errorMsg.errorContentEmpty }<br>
	<input type='button' id='btn' value='btn'>
	<input type='submit' value='send'><br>
來源:<input type='text' name='source' value='${param.source }'>${errorMsg.errorSourceEmpty }<br>
來源連結:<input type='text' name='link' value='${param.link }'>
<script>
	// Replace the <textarea id="editor1"> with a CKEditor
	// instance, using default configuration.
	CKEDITOR.replace( 'editor' );
	CKEDITOR.config.height = 500;
	CKEDITOR.config.width = 800;
</script>
</form>
</body>
</html>