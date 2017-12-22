	var geocoder;
	var map;

	//GOOGLE MAP 初始化
	function initialize() {
	geocoder = new google.maps.Geocoder();
	
	//初始化地圖時的定位經緯度設定
	var latlng = new google.maps.LatLng(25.0337719, 121.543272);
	
	//初始化地圖options設定
	var mapOptions = {
			zoom: 14,
			center: latlng
	}
	
	//初始化地圖
	 map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
	
	//印出初始化地圖時想顯示的字串
	showInfo("<div style='width:100px'><H4>We are here!</H4></div>", 25.0337719,121.543272);
	
	marker = new google.maps.Marker({
	    	 draggable:false,
	         position: latlng,
	         title:"台北市 TaipeiCity",
	         map:map
	      }); 
	 }
	 
	//印出初始化地圖時想顯示的字串
	function showInfo(content, lat, lng){
		var coordInfoWindow = new google.maps.InfoWindow();
		coordInfoWindow.setContent(content);
		coordInfoWindow.setPosition(new google.maps.LatLng(lat, lng));
		coordInfoWindow.open(map);
	}
	
	//搜尋地點
	function codeAddress() {
	   var address = document.getElementById("address").value;
	   geocoder.geocode( { 'address': address}, function(results, status) {
	     if (status == google.maps.GeocoderStatus.OK) {      		 
	       	map.setCenter(results[0].geometry.location);
	      	var marker = new google.maps.Marker({
	        map: map,
	        position: results[0].geometry.location
	     	});
	      	var latitude = results[0].geometry.location.lat();
	        var longitude = results[0].geometry.location.lng(); 
	        showInfo("<H4>"+address+"</H4>", latitude, longitude);
	     } else {
	       alert("查無此地");
	     }
	   });
	 }	