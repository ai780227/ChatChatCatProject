var geocoder;
var map;
	 
//GOOGLE MAP 初始化
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