<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:v="urn:schemas-microsoft-com:vml">
<%@ taglib uri="/struts-tags" prefix="s"%>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Google Maps JavaScript API Search Along a Route Example</title>
<script src="https://maps.google.com/maps/api/js?sensor=false"
	type="text/javascript"></script>
<script src="../../assets/vts/js/RouteBoxer.js"></script>
<script src="../../assets/global/plugins/jquery-1.11.0.min.js"
	type="text/javascript"></script>
<script type="text/javascript">
	var map = null;
	var boxpolys = null;
	var directions = null;
	var routeBoxer = null;
	var distance = null; // km

	function initialize() {
		// Default the map view to the continental U.S.
		routeBoxer = new RouteBoxer();
		directionService = new google.maps.DirectionsService();
		directionsRenderer = new google.maps.DirectionsRenderer({
			map : map
		});
		var routeData = $.ajax({
			url : "fence.action?routeid=" + routeID,
			dataType : 'json',
			datatype : "post",
			global : false,
			async : false,
			success : function(response) {
				return response;
			}
		}).responseText;
		parseData = jQuery.parseJSON(routeData);
		var length = parseData["aaData"].length;
		var x = "";
		for ( var r = 0; r < length; r++) {
			x = parseData["aaData"][r]["3"];

		}
		if (x.length != 0) {
			var arr = x.split("(");
			var arrr = arr[1].split(",");
			var arrrr = '';

			arrrr = arrr[0].split(" ");
		} else {
			arrrr = [ 12.97928309, 77.57205963 ];
		}
		var mapOptions = {
			center : new google.maps.LatLng(arrrr[0], arrrr[1]),
			zoom : 16
		};
		map = new google.maps.Map(document.getElementById("map"), mapOptions);
		setTimeout(function() {
			route()
		}, 3000);
	}
	var routeID = window.opener.document.getElementById('routefence').value;
	var startpoints = [];
	var endpoints = [];
	var path = "";
	var routeString = "";
	var parseData = "";
	var stpoint = "";
	var enpoint = "";
	var route_geoFence = "";
	var centerPoint = "";
	function route() {
		// Clear any previous route boxes from the map
		clearBoxes();
		//Call Ajax For 
		var routeData = $.ajax({
			url : "fence.action?routeid=" + routeID,
			dataType : 'json',
			datatype : "post",
			global : false,
			async : false,
			success : function(response) {
				return response;

			}
		}).responseText;
		parseData = jQuery.parseJSON(routeData);
		var length = parseData["aaData"].length;
		var x = "";
		var stop_order=[];
		var order="";
		for ( var r = 0; r < length; r++) {
			stop_order.push(parseData["aaData"][r]["0"]);
			x += parseData["aaData"][r]["4"] + ",";
			startpoints.push(parseData["aaData"][r]["1"]);
			endpoints.push(parseData["aaData"][r]["2"]);
			route_geoFence = parseData["aaData"][r]["5"];
		}
		// Convert the distance to box around the route from miles to km
		distance = parseFloat(document.getElementById("distance").value) / 1000;
		arr = x.split(",");
		//console.log(stop_order);
		var final_path = "";
		var arr_final_path = [];
		for (i = 0; i < arr.length - 1; i++) {
			final_path = addEncodedPath(arr[i]);
			arr_final_path.push(final_path);
			path += final_path;
		}
		//console.log("===>"+path);
		function addEncodedPath(encodedPath) {
			var path = google.maps.geometry.encoding.decodePath(encodedPath);
			var polyline = new google.maps.Polyline({
				path : path,
				geodesic : true,
				strokeColor : "#FF0000",
				//				strokeOpacity : 2.0,
				strokeWeight : 2,
				draggable : false
			});
			polyline.setMap(map);
			return path;
		}
		
		for (i = 0; i < arr_final_path.length; i++) {
			var boxes = routeBoxer.box(arr_final_path[i], 0.040);
			stpoint = startpoints[i];
			enpoint = endpoints[i];
			order =stop_order[i];
			//console.log(order);
			drawBoxes(boxes);
			if (route_geoFence != 'Y') {
				//Ajax Call To Insert Data if GeoFence is not Y..
				$('#loading').show(); 
				$.ajax({
					type : 'POST',
					data : "routeid=" + routeID + "&path=" + arr_final_path[i]
							+ "&startpoints=" + stpoint + "&endpoints="
							+ enpoint + "&routeString=" + routeString +"&centerpoint="+centerPoint+"&commonPath="+x+"&stopOrder="+order,
					url : "<s:url action='insertfence'/>",
					success : function(response) {
						$('#loading').hide(); 
						$('#loading').html('Route Fenced SuccessFully');
						if(i==arr_final_path.length-1){
							$('#loading').hide();
						}
						
						return response;						
					}
				});
			}else{
				$('#loading').hide();
			}
			routeString="";centerPoint="";
		}
		//Update route_geofence of Route Table
		if (route_geoFence != 'Y') {
			$.ajax({
				type : 'POST',
				data : "routeid=" + routeID,
				url : "<s:url action='updateRouteGeofence'/>",
				success : function(response) {
					return response;

				}
			});
		}
	}

	// Draw the array of boxes as polylines on the map
	function drawBoxes(boxes) {
		boxpolys = new Array(boxes.length);
		for ( var i = 0; i < boxes.length; i++) {
			
			if (route_geoFence != 'Y') {
				routeString += boxes[i].getSouthWest().lat()+ " " +boxes[i].getSouthWest().lng() +","
						+ boxes[i].getNorthEast().lat()+" "+boxes[i].getSouthWest().lng() + ","
						+ boxes[i].getNorthEast().lat()+" "+ boxes[i].getNorthEast().lng() + ","
						+ boxes[i].getSouthWest().lat()+" "+ boxes[i].getNorthEast().lng() + ","
						+ boxes[i].getSouthWest().lat()+" "+ boxes[i].getSouthWest().lng() + "#";
				centerPoint += boxes[i].getCenter().lat()+"#";
			}
			boxpolys[i] = new google.maps.Rectangle({
				bounds : boxes[i],
				fillOpacity : 0.35,
				strokeOpacity : 0.0,
				strokeColor : '#FF0000',
				fillColor : '#FF0000',
				strokeWeight : 0,
				map : map
			});
		}
		

	}

	// Clear boxes currently on the map
	function clearBoxes() {
		if (boxpolys != null) {
			for ( var i = 0; i < boxpolys.length; i++) {
				boxpolys[i].setMap(null);
			}
		}
		boxpolys = null;
	}
</script>
<style>
#map {
	border: 1px solid black;
}
</style>
</head>
<body onload="initialize();">
	<div id="loading">
	<center> Loading Please Wait...<img src='../../assets/images/loader1.gif' width="500px" align="middle"/></center> 
	</div>
	
	<div id="map" style="width: 800px; height: 500px; "></div>	
	<input type="hidden" id="distance"
		placeholder="Enter Distance in meters" />

</body>
</html>
