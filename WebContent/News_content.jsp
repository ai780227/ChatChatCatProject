<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新聞</title>

<link rel="stylesheet" href="<c:url value='/css/newspaper/jquery-ui-1.10.4.custom.css'/>">	

<script type="text/javascript" src="<c:url value='/js/jquery-1.10.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui-1.10.4.custom.js'/>"></script>

<style>
body {
	margin: 10px 15px 0px 15px;
}
</style>

<script>

$(function() {
    $( "#accordion" ).accordion({
      collapsible: true,
      heightStyle: "content",
      active: false
    });
  });

</script>

</head>

<body>

<div style="background-image:url('<c:url value="/images/paperbackground_white.jpg"/>'); border-width: 10px; border-style:dashed; border-color:rgb(198,190,170); border-radius:15px; padding:5px 35px 35px 35px;">
	<h1 style="text-align:center; text-shadow: 0px 1px 1px #AA8119;">貓咪日報</h1>


<div id="accordion">
	<c:forEach items="${newsList }" var="newsBean" varStatus="varStatus">
		<h3>${newsBean.news_title }</h3>
		<div>
			<div id="content_${newsBean.news_id }">${newsBean.news_content }</div>
			<p>來源: <span id="source_${newsBean.news_id }">${newsBean.news_source }</span></p>
			<p>連結: <span id="link_${newsBean.news_id }"><a href="${newsBean.news_link }" target="_blank">${newsBean.news_link }</a></span></p>
			<p>時間:${newsBean.news_time }</p>
		</div>
	</c:forEach>

</div>



<!-- 
<div id="accordion">
  <h3>從帝制到現在都對貓好 北京故宮讓流浪貓安居</h3>
  <div>
  <figure
			style="float: right; width: 150px; padding: 10px 10px 10px 10px; border: thin solid silver; margin: 15px 15px 15px 15px">
			<img src="../images/cat.jpg" width="150">
			<figcaption style="font-size: 12px">這是一隻故宮的流浪貓，記者被他的眼神嚇跑了，無法訪問</figcaption>
	</figure>
    <p>北京故宮有一群最神秘的「員工」，遊客來參觀故宮博物院裡皇帝的起居作息地，能見到這裡的貓兒是「更稀罕一些」，因為牠們很少在遊客區活動。每天閉館後，工作人員會巡視所有區域，一層層關上宮門，任何人都不能在紅牆內逗留，待夜幕降臨，卻是神秘「員工」的執勤時間。</p>
    <p>說起神秘的「貓員工」由來，北京故宮博物院院容科科長馬國慶指出，故宮很久以來就有流浪貓了；工作人員寶媽說，古時有很多宮中的人養貓。太監劉若愚在《酌中誌·內府衙門職掌》記載，「貓兒房，近侍三四人，專飼禦前有名分之貓，凡聖心所鍾愛者，亦加陞管事職銜。」</p>
	<p>貓兒房是「宮貓」的管理機構，職責不僅是管理大量的「宮貓」，還要在眾多的「宮貓」中選拔「佼佼者」獻給皇帝，皇帝留下自己喜歡的，把其他的賞賜給皇親國戚。</p>
	<p>工作人員指出，目前故宮的流浪貓，一部分可能是當年宮貓的後代，一部分是自己跑進來的，還有一部分是遊客夾帶進來的，「故宮對流浪貓好，很多人都知道。」在那些曾經是皇帝妃子們生活的地方，這些貓依然來去自由。直到為了改變牠們的生活條件，也起到防鼠效用，讓牠們與故宮相處更和諧，故宮人便將牠們「收編」了。</p>
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
-->
</div>

</body>
</html>