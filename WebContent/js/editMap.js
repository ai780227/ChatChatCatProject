	var geocoder;
	var map;

	function initialize() {
		geocoder = new google.maps.Geocoder();
		
		//初始化地圖options設定
		var mapOptions = {
		    zoom: 15
		}
		   
		//初始化地圖
		map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
		   
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
		     } else {
		       alert("查無此地");
		     }
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