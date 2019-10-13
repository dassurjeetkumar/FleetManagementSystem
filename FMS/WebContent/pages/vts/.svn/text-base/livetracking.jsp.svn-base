
<!DOCTYPE html>
<html>
<head>
<script src="../../assets/global/plugins/jquery-1.11.0.min.js"
	type="text/javascript"></script>
<!-- <script -->
<!-- 	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places,drawing,geometry"></script> -->
<script src="https://maps.googleapis.com/maps/api/js?client=gme-trimaxitinfrastructure&sensor=false&libraries=places,drawing&signature=CpSOeA5B6SzC2rxqlzd2JwenGX0="></script>
 <script src="../../assets/vts/js/vts.js" type="text/javascript"></script>
 <!--
<meta name="viewport" content="initial-scale=1.0, user-scalable=no"> -->

<meta charset="utf-8">
<style>
html,body,#gmap_basic {
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
<title>Live Tracking</title>
<script>
	var map;
	var firstcordinate;
	function initialize() {
		var mapOptions = {
			zoom : 13,
			// Center the map on Bngalore
			center : new google.maps.LatLng(12.97928309, 77.57205963)
		};

		map = new google.maps.Map(document.getElementById('gmap_basic'),
				mapOptions);
	}

	google.maps.event.addDomListener(window, 'load', initialize);
</script>
<script>
	function getData() {
		var DEVICE_ID = window.opener.document.getElementById('deviceid').value;
		var VEHICLE_ID = window.opener.document.getElementById('vehicleid').value;
		var SCHEDULE_NO = window.opener.document.getElementById('scheduleno').value;
		var DepotName = window.opener.document.getElementById('depotName').value;
		$("#vehicleno").text("Depot Name:"+DepotName+" Schedule Number:" + SCHEDULE_NO);
		getCordinates(DEVICE_ID, 0, '', '', '');
	}
	
</script>
<style>
#target {
	width: 345px;
}
</style>
</head>
<body onload="getData()">
	<div id='vehicleno' style="display: block; position: relative;">
	</div>
	<div id="gmap_basic"></div>
</body>
</html>

