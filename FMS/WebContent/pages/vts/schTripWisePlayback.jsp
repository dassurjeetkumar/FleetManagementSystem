
<!DOCTYPE html>
<html>
<head>
<script src="https://maps.googleapis.com/maps/api/js?client=gme-trimaxitinfrastructure&sensor=false&libraries=geometry,places,drawing&signature=CpSOeA5B6SzC2rxqlzd2JwenGX0="></script>
<script src="../../assets/global/plugins/jquery-1.11.0.min.js"
	type="text/javascript"></script>
<script src="../../assets/vts/js/scheduledeviation.js"
	type="text/javascript"></script>
	
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
<meta charset="utf-8">
<style>
html,body,#map-canvas {
	height: 95%;
	margin: 0px;
	padding: 0px
}

.controls {
	margin-top: 16px;
	border: 1px solid transparent;
	border-radius: 2px 0 0 2px;
	box-sizing: border-box;
	-moz-box-sizing: border-box;
	height: 32px;
	outline: none;
	box-shadow: 0 2px 6px rgba(0, 0, 0, 0.3);
}

#pac-input {
	background-color: #fff;
	padding: 0 11px 0 13px;
	width: 400px;
	font-family: Roboto;
	font-size: 15px;
	font-weight: 300;
	text-overflow: ellipsis;
}

#pac-input:focus {
	border-color: #4d90fe;
	margin-left: -1px;
	padding-left: 14px; /* Regular padding-left + 1. */
	width: 401px;
}

.pac-container {
	font-family: Roboto;
}

#type-selector {
	color: #fff;
	background-color: #4d90fe;
	padding: 5px 11px 0px 11px;
}

#type-selector label {
	font-family: Roboto;
	font-size: 13px;
	font-weight: 300;
}

.botton {
	padding: 5px;
	background-color: #dcdcdc;
	border: 1px solid #666;
	color: #000;
	text-decoration: none;
}
}
</style>
<title>Route Deviation Tracking</title>
<script>
	var map;
	var firstcordinate;
	var bounds = null;
	var geocoder;
	function initialize() {
		geocoder = new google.maps.Geocoder();
		var mapOptions = {
			zoom : 16,
			// Center the map on Bngalore
			center : new google.maps.LatLng(12.97928309, 77.57205963)
		};

		map = new google.maps.Map(document.getElementById('map-canvas'),
				mapOptions);
		bounds = new google.maps.LatLngBounds();
	}

	google.maps.event.addDomListener(window, 'load', initialize);
</script>
<script>
	function getData() {
		var device_id= window.opener.document.getElementById("deviceid").value;
		var id = 0 ;
		var start_time=window.opener.document.getElementById("starttime").value ;
		var end_time=window.opener.document.getElementById("endtime").value ;
		
		var route_id= window.opener.document.getElementById("routeid").value;
		var startpoint=window.opener.document.getElementById("startpoint").value ;
		var endpoint=window.opener.document.getElementById("endpoint").value ;
		
		var schNo = window.opener.document.getElementById("schNo").value;
		var tripNo = window.opener.document.getElementById("tripNo").value;
		var fromStop = window.opener.document.getElementById("fromStop").value;
		var endStop = window.opener.document.getElementById("endStop").value;
		var schDep = window.opener.document.getElementById("schDep").value;
		var gpsDep = window.opener.document.getElementById("gpsDep").value;
		var schArr = window.opener.document.getElementById("schArr").value;
		var gpsArr = window.opener.document.getElementById("gpsArr").value;
		var schDistance = window.opener.document.getElementById("schDistance").value;
		var value = "ScheduleSubmit"
		var timeframe = 0.05 ;
		getCordinatesFromMarker_playback(device_id, id, start_time, end_time, value, timeframe,route_id,startpoint,endpoint);
		document.getElementById("schInfo").innerHTML = "<table  cellspacing='10'><tr><td>Schedule Name. : "+schNo
		+"</td><td>Trip No.:"+tripNo+"</td><td>From:"+fromStop+"</td><td>To:"+endStop+"</td></tr><tr><td>Schedule Departure:"+schDep+"</td><td>Actual Departure:"+gpsDep+
		"</td><td>Schedule Arrival:"+schArr+"</td><td> Actual Arrival:"+gpsArr+"</td></tr><tr><td>Schedule Distance.:"+schDistance+"</td></tr></table>";		
	}
</script>
<style>
#target {
	width: 345px;
}
</style>
</head>
<body onload="getData()">
	<div id="map-canvas"></div>
	<div id="schInfo" align="center" style="display: block;">
	</div>
</body>
</html>

