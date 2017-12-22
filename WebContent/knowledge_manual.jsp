<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>知識手冊</title>

<link rel="stylesheet" href="<c:url value='/css/knowledge/jquery-ui-1.10.4.custom.css'/>">
	
<script src="<c:url value='/js/jquery-1.10.2.js'/>"></script>
<script src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>

<style>
body {
	margin: 10px 15px 0px 15px;
}
</style>

<script>

$(function() {
	$(".buttonClass").button();
	
    $( "#accordion" ).accordion({
      collapsible: true,
      heightStyle: "content",
      active: false
    });
  });

</script>

</head>

<body>

<div style="background-image:url('../images/paperbackground.jpg'); border-width: 10px; border-style:ridge; border-color:rgb(241,206,138); border-radius:15px; padding:5px 35px 35px 35px;">
	<h1 style="text-align:center; text-shadow: 0px 1px 1px #AA8119;">
	知識手冊
		<c:if test="${manager != null }">
			<div style="display:inline-block;float:right;font-size:16px;">
				<input class="buttonClass" type="button" id="create" value="新增"/>
			</div>
		</c:if>
	</h1>

<div id="accordion">
  <h3>運動的重要性</h3>
  <div>
    <p>運動對小貓的身心健康十分重要。維持好身材可避免過胖 與其他相關問題的發生。 小貓們均喜歡運動，如同喜歡與您在一起時所得到的關注。事實上，大部份的貓可以從遊戲當中獲得運動。
將貓薄荷縫入布袋或短襪裡並在頂端打個結也是小貓所喜歡 的安全玩具。雖然多年以來，貓兒已熟悉玩耍各種繩線與毛線球，但實際 上它們卻是十分危險的物品。如果不小心吞食，可能會堵塞 腸道。</p>
  </div>
  <h3>美容</h3>
  <div>
    <p>定期的美容與獸醫師照顧，對您寵物的健康與您所承擔之責 任而言，扮演著重要的角色。 您的小貓會每天自己整理儀容，但您亦可協助其梳理，以免打結。</p>
  </div>
  <h3>梳毛</h3>
  <div>
    <ul>
      <li>工具：依其毛髮之長度，選用寵物專用之刷子或梳子。</li>
      <li>方法：順著毛髮生長的方向梳理。</li>
      <li>頻率：每週一次。但建議長毛貓應每日梳理。</li>
    </ul>
  </div>
  <h3>口腔清潔 </h3>
  <div>
    <p>工具：良好的口腔健康，是整體健康之基礎。 <br/>

可利用以下方式進行： <br/>
*使用潔牙用具，您可向獸醫師詢問有關刷牙之方式。           <br/>
*若要完全清潔清潔牙齒與牙齦，則可由您的獸醫師進行專業之洗牙。 <br/>
頻率：每6個月至1年進行獸醫專業之檢查。</p>
  </div>
  
  <h3>耳朵 </h3>
  <div>
    <p>工具：棉花球與耳朵清潔液((不可使用棉花棒)) <br/>
方法：請向您的獸醫師詢問。 <br/>
頻率：每隔一週進行，或視小貓耳朵髒污情況而定。 <br/>
健康的貓耳是不需要伸入式清潔的!  <br/>
清潔貓咪的耳朵，可以拿棉花或質地較輕柔的 化妝棉，沾上溫水或是潔耳劑，在耳稍做擦 拭即可，不必伸入耳內。 有時不當的清耳反而會造成耳內受傷，嚴重則會造成永久聽力受損。  <br/>
小貓很少需要洗澡，因為小貓會自行清潔。但偶爾需要協助去除上身之髒汙或氣味。 <br/>
工具：使用溫和的小貓專用洗髮精，與一塊橡膠墊 ((使您的小貓於洗澡時站穩))，再準備一條毛巾供擦拭。<br/>
方法：將棉花球輕輕塞在小貓的耳朵，以避免進水。梳掉毛球。使用溫水儘快為其清洗。清水沖淨泡沫，再用毛巾擦乾並加以梳理。 注意仍要小心別將水浸濕到耳內的棉花，並於洗澡完時，再以乾棉花輕拭耳內，以保持乾燥。<br/>
</p>
  </div>
  
  <h3>貓窩</h3>
  <div>
    <p>雖然貓很可能會自己找到最舒服的地方來睡覺，但你仍應使牠有自己的窩。<br/>

便盆。至於需要準備便盆的原因，則不需言明了。 <br/>
食盆和水碗。貓需要有潔淨的食盆和水碗。 <br/>
磨爪器。準備一個供貓磨利貓爪的木柱或襯墊，可阻止貓扒抓家。如果貓養在屋內，尤為重要。<br/>
玩具。貓都喜歡嬉戲。市場上有許多貓用的玩具，而紗線球之類的家庭用品同樣可以成為貓的玩具，但千萬不要買軟橡膠做的玩具──因為貓吞食軟膠後，會引起窒息或體內疾病。</p>
  </div>
  
  <h3>貓糧 </h3>
  <div>
    <p>最重要的是有適當的貓糧（幼貓現時的食糧）。貓和狗不同，牠比狗隻需要多些蛋白質，而只餵飯和魚是不足夠的。 <br/>
每天都必須更換新鮮貓糧</p>
  </div>
  
</div>

</div>
</body>

</html>