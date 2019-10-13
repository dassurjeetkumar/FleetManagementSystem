
<!DOCTYPE html>
<html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<%-- <script --%>
<%-- 	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places,drawing"></script> --%>
<script src="https://maps.googleapis.com/maps/api/js?client=gme-trimaxitinfrastructure&sensor=false&libraries=places,drawing&signature=CpSOeA5B6SzC2rxqlzd2JwenGX0="></script>
<script src="../../assets/global/plugins/jquery-1.11.0.min.js"
	type="text/javascript"></script>
<script src="assets/vts/js/skipstop.js"
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
<title>Skip Stop </title>
<script>
	var map;
	var firstcordinate;
	var bounds = null;
	var geocoder;
	function initialize() {
		geocoder = new google.maps.Geocoder();
		var mapOptions = {
			zoom : 13,
			// Center the map on Bngalore
			center : new google.maps.LatLng(12.97928309, 77.57205963)
		};

		//map = new google.maps.Map(document.getElementById('map-canvas'),
		//		mapOptions);
		bounds = new google.maps.LatLngBounds();
	}

	google.maps.event.addDomListener(window, 'load', initialize);
</script>
     
<script>
$(document).ready(function() {
	getData();
	});
	function getData() {
		console.log("inside skip stopJSP0");
		//var dev=window.opener.document.getElementById('deviceser').value
		//console.log(dev);
		    var device_id = document.getElementById("serialno").value;
			var route_id = document.getElementById("routeid").value;
			var start_point = document.getElementById("startpoint").value;
			var end_point=document.getElementById("endpoint").value;
			var start_date=document.getElementById("startdate").value;
			var end_date=document.getElementById("enddate").value;
			var busstop_id=document.getElementById("busstopid").value;
			console.log("inside skip stopJSP");
		
		skipstopTracking(device_id, route_id, start_point, end_point,
				start_date,end_date,busstop_id);
		//console.log("device_id "+device_id+"device_id"+device_id+"")
	}
</script>
<style>
#target {
	width: 345px;
}
</style>
</head>
<body>
<!-- 	<div id='vehicleno' style="display: block; position: relative;"> -->
<!-- 	</div> -->
<div id='loadingmessage' style='display:none'>
 <center> Loading Please Wait...<img src='assets/images/loader1.gif' width="500px" align="middle"/></center> 
</div>
	<div id="map-canvas"></div>
	<label class="col-md-6 control-label"><b>Depot No.:</b> <font 
 					
						color="red"></font> <s:property value="orgname"/>
  					</label> 
  					<label class="col-md-6 control-label"><b>Vehicle No.: </b><font 
 					
						color="red"></font> <s:property value="vehicleNo"/>
  					</label> <br>
  					<label class="col-md-6 control-label"><b>Start Point:</b> <font 
 					
						color="red"></font> <img src="assets/images/BusIconRedFlag.png" >
  					</label> 
  					<label class="col-md-6 control-label"><b>End Point: </b><font 
 					
						color="red"></font> <img src="assets/images/bus-map-icon.png" >
  					</label>
 				<div class="form-group"> 
<!--  				<div class="col-md-4"> -->
<!--  					<label class="col-md-6 control-label">Depot Name: <font -->
 					
<%-- 						color="red"></font> <s:property value="orgname"/><br> --%>
<!--  					</label>  -->
 					<input type="hidden" name="orgname" value='<s:property value="orgname"/>' id ="orgname">
<!--  					</div></div> -->
<!--  					<div class="form-group"> -->
<!--  					<div class="col-md-4"> -->
<!--  					<label class="col-md-6 control-label">Vehicle No: <font -->
<%-- 						color="red"></font> <s:property value="vehicleNo"/><br> --%>
						<input type="hidden" name="vehicleNo" value='<s:property value="vehicleNo"/>' id ="vehicleNo">
						
						 <input type="hidden" name="serialno" value='<s:property value="serialno"/>' id ="serialno">
						  <input type="hidden" name="startdate" value='<s:property value="startdate"/>' id ="startdate">
						   <input type="hidden" name="enddate" value='<s:property value="enddate"/>' id ="enddate">
						   
						   <input type="hidden" name="busstopid" value='<s:property value="busstopid"/>' id ="busstopid">
						
						 <input type="hidden" name="routeid" value='<s:property value="routeid"/>' id ="routeid">
						  <input type="hidden" name="startpoint" value='<s:property value="startpoint"/>' id ="startpoint">
						   <input type="hidden" name="endpoint" value='<s:property value="endpoint"/>' id ="endpoint">

</body>
</html>

