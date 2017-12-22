<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-TW">
<head id="photopage">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>相片</title>

<link rel="stylesheet" href="<c:url value='/css/sunny/fontsize1.1/jquery-ui-1.10.4.custom.css'/>">

<script src="<c:url value='/js/jquery-1.10.2.js'/>"></script>
<script src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>
<script src="<c:url value='/js/jquery.waterwheelCarousel.js'/>"></script>

<style>

body {
	overflow-x:hidden;
}

.waterwheelcarousel {
    height: 10px;
}

.waterwheelcarousel img {
    display: hidden; /* hide images until carousel prepares them */
    cursor: pointer; /* not needed if you wrap carousel items in links */
    border-radius: 100px;
    border-style: outset;
    border-color: white;
    border-width: 7px;
    box-shadow: 10px 10px 35px black;
    width:450px;
	height:auto;
}

.block {
	margin-top:330px;
}

</style>

<script>

$(function() {
	 $(".buttonClass").button();
	$(".waterwheelcarousel").waterwheelCarousel({
		separation: 195,
		flankingItems: 10
	 });
	$('img').css( {height:'auto' , width:'50%' });
	 $("#deletephoto").button().click(function(){
		 var photoid=$(".carousel-center").attr("id");
		 $.ajax({'url' :'<c:url value="/PhotoServlet.do"/>',
		 	 'type':'post',
		 	 'data':{'photo_name':photoid,
		 		 	 'photos':'editPhotos',
		 		 	 'photo_id':photoid
		 	 		},
	 		 'datatype':'text',
	 		 'success' :function(data){
	 			 if(data!=null&&data!=''){
	 				var json = eval('(' + data + ')');
	 				$('#photo_all').html("");
	  				$('#photo_all_w').html("");
	  				if(json.jsonArray.length!=0){
	  					$('#photo_all').append("<div class='waterwheelcarousel' id='photo_all_w'>");
	  					for(var i=0;i<json.jsonArray.length;i++){
			  				$('#photo_all_w').append(
			  						//"<input type='hidden' id='Pic_id' value="+json.jsonArray[i].Pic_id+">"+
			  						"<img id='"+json.jsonArray[i].Pic_id+"' src='<c:url value='/userImages/"+json.memberBean[0].memberBean+"/"+json.jsonArray[i].Pic_file_path+"'/>' alt='Images"+json.jsonArray[i].Pic_id+"' />"
								);
			  				}
	  					$('#photo_all').append("</div>");
	  					$(".waterwheelcarousel").waterwheelCarousel({
	  						separation: 195,
	  						flankingItems: 10,
		  					clickedCenter: function($clickedItem) {
		  					      // $clickedItem is a jQuery wrapped object describing the image that was clicked.
		  					      var imageUrl = $clickedItem.attr('src');
		  					      imageUrl = imageUrl.slice(10, -4);
		  					      imageUrl = "#" + imageUrl;
		  					      parent.parent.$(imageUrl).click();
		  					 }
	  				 	});
	  					$('img').css({height:'50%' ,width:'50%' });
	  				}
	 			 }else{
	 				$('#photo_all_w').html("");
	 			 	}
	 		 	}
			 
		 	});
		 }); 


});

</script>
</head>

<body>
	<div style="text-align:center; z-index:9999; margin-bottom:5px;">
	<form name="form" action="<c:url value='PhotoServlet.do'/>" method="POST" enctype="multipart/form-data" style="z-index:9999;">  
    		<input type="hidden" id ="photos" name ="photos" value="onloadPhoto" style="z-index:9999;"/>
    		<div> <span style="font-family:'微軟正黑體';">上傳照片:</span><input type="file" name="pic" id="pic" style="z-index:9999; font-family:'微軟正黑體';" multiple>
    	 	<input type="submit" value="上傳" style="z-index:9999; font-family:'微軟正黑體';"/></div>
    		 
  	</form>
	</div>
		
	<div style="text-align:center; z-index:9999;">	
	<a href="<c:url value='/PhotoServlet.do' />?photos=viewPhotoscat"  class="buttonClass" style="z-index:9999;">貓咪相本模式</a>
 	 <a href="<c:url value='/PhotoServlet.do' />?photos=viewPhotos"  class="buttonClass" style="z-index:9999;">相本模式</a>
 	 <button id="deletephoto"  style="z-index:9999;">刪除相片</button>
  	</div>
  
  
  
	<div class="block" id="photo_all">
	<span></span>
	<div class="waterwheelcarousel" id="photo_all_w">
	<c:choose>
		<c:when test="${photos!=null}">
			<c:forEach var="photos" items="${photos}">
				
				<img id="${photos.getPic_id()}" src="<c:url value='/userImages/${member.getM_id()}/${photos.getPic_file_path()}'/>" alt="Image ${photos.getPic_id()}"/>
			</c:forEach>
		</c:when>
		<c:when test="${photos==null}"></c:when>
	</c:choose>
	</div>
	</div>

	
	
	
</body>

</html>


<!-- 
<script>
		$('#onload').bind('click',function() {
			alert('pic:'+$('#pic').val());
							$.ajax( { 'url'  :'<c:url value="/PhotoServlet.do" />',
									  'type' :'post',
									  'data' :{'photos':'onloadPhoto'
										  	  },
									  'fileElementId':'pic',
									  'datatype':'text',
									  'success':function(data) {
										  			if(data!='' || data!=null) {
										  				alert("hear.....");
										  			}
											  } 
									} );
					});
		
	
	
	



  function ajaxFileUpload() {  
        $("#loading").ajaxStart(function() {  
            $(this).show();  
        }).ajaxComplete(function() {  
            $(this).hide();  
        });  
  
        $.ajaxFileUpload({  
            url : 'upload',
            secureuri : false,  
            fileElementId : 'fileToUpload',
            dataType : 'json',  
            data : {username : $("#username").val()}, 
            success : function(data, status) {  
                if(data.msg) {  
                    alert(data.msg);  
                }  
            },  
            error : function(data, status, e) {  
                alert('上传出错');  
            }  
        })  
  
        return false;  
  
    }  
	</script>



 

 -->