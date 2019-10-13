<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<%-- <style  type="text/css">
table#linearRoute{
	max-width:500px;
	overflow-x:scroll; 
}
table#linearRoute th{
	heigth:10px;
}

</style> --%>
<head>
<script src="https://maps.googleapis.com/maps/api/js?client=gme-trimaxitinfrastructure&sensor=false&libraries=places,drawing,geometry&signature=CpSOeA5B6SzC2rxqlzd2JwenGX0="></script>
	<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
 	<script type="text/javascript" src="assets/vts/js/vts.js"></script> 
<script src="assets/global/plugins/jquery-1.11.0.min.js" value='1' type="text/javascript"></script>
<script src="assets/vts/js/deviatedtracking.js"
	type="text/javascript"></script>

<link rel="stylesheet" href="https://code.jquery.com/ui/1.9.1/themes/black-tie/jquery-ui.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />


</head>
<body>
	<div class="page-content-wrapper">
		<div class="page-content">
			<div class="row">
				<div class="col-md-12">
					<h3 class="page-title">
						TRIP PLANNER <small></small>
					</h3>
				</div>
			</div>
			<div class="tab-content">
				<div class="tab-pane active">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Route Wise Trip Planner
							</div>
						</div>
						<div class="portlet-body">
							<div class="row">
								<b>
									<font color="green"> <s:actionmessage/></font>
									<font color="red"><s:actionerror/></font>		
								</b>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="tabbable tabbable-custom tabbable-reversed">
										<ul class="nav nav-tabs">
											<li id='tab0' class='active'><a href="#tab_0" data-toggle="tab">Route Wise</a></li>
											<!-- <li id='tab1'><a href="#tab_1" data-toggle="tab">Source & Destination Wise </a></li> -->
										</ul>
										<div class="tab-content">
											<div class="tab-pane active"   id="tab_0">
												<div class="form-group">
													<label class="col-md-2 control-label">Route Number</label>
													<div class="col-md-3">
														<input class="form-control" tabindex="1" placeholder="Enter Bus Stop to Search" id="project" name="project" type="text"	onkeyup="getRoute(this.value)"   />
															<input id="project-id" type="hidden">
								 						<input id="project-id1" type="hidden">
								 						<input id="project-id2" type="hidden">
													</div>
<!-- 													<label class="col-md-2 control-label">Destination Stop</label> -->
<!-- 													<div class="col-md-3"> -->
<!-- 														<input class="form-control" tabindex="1" placeholder="Enter Bus Stop to Search" id="projectdest" name="projectdest" type="text"	onkeyup="getRoute1(this.value)"   /> -->
<!-- 														<input id="projectdest-id" type="hidden"> -->
<!-- 								 						<input id="projectdest-id1" type="hidden"> -->
<!-- 								 						<input id="projectdest-id2" type="hidden"> -->
<!-- 													</div> -->
														<button type="button" class="btn default" onclick="getRouteDataMap(0)" style="position: static;">Submit</button>
														<button  type='button' class="btn grey" onclick="getRouteDataMap(1);"> Refresh</button>
														</div>
													<div class="table-scrollable"> 	
													<div id='loadingmessage' style='display:none'>
 <center> Loading Vehicle Position Please Wait...<img src='assets/images/loader1.gif' width="300px" align="middle"/></center> 
</div>
<!-- 													<div id="gmap_basic" class="gmaps" style="height: 100%"></div> -->
													 </div>	
												
											 	<div class="form-group">
											 		<div class="form-group" id="mapdiv" >
				<table class="table table-striped">
					<tr>
						<td valign="top">
							<div class="portlet-body" style="height: 600px">
								<div id="gmap_basic" class="gmaps" style="height: 100%"></div>
							</div>
						</td>
					</tr>
				</table>
			</div>
														<p id='loading'>
														</p>
														<p id='bus_stop_names'>
														</p>
												</div>
												<div class="form-group">
													
												</div>
											</div>
											
											<div class="tab-pane" id="tab_1">
												<br/>
												<br/>
												<br/>
											</div><a data-toggle="modal" style="display:none"  id="popup" href="#model" >sss</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<div style="display: none" id="model" class="modal fade"
	tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
	aria-hidden="true"   data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width:30%">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title"><b>Details</b></h3>
			</div>
			<div class="modal-body" >
				<div  id="scrollable-div2" >
					<p id="tipText"></p>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal"  class="btn default"  id='cancel' >Cancel</button>
				<!-- <button type="button" class="btn green"  onclick='return saveIds()'>Submit</button> -->
			</div>
		</div>
	</div>
</div> 
</body>									
<script>
var dataSet = [];
var routeNo;
function showvehdet(vehicle){
	$("#vehicledata").find("tr:gt(0)").remove();
	for(var i=0; i<vehdetroudata.etaModel.length; i++){
	   	if(vehdetroudata.etaModel[i].vehicleNo==vehicle){
     		$('#vehicledata tr').last().after('<tr><td>'+vehdetroudata.etaModel[i].busStopName+'</td><td>'+Math.round(parseInt(vehdetroudata.etaModel[i].eta)/60000)+'Min</td></tr>');
        }
 	}
 }
/* setInterval(function() {
	getdetails(route_id);
}, 10000); */

function getRoute(val){
	var result = "";
	var availableTags=[];	
	$.ajax({
	    type : 'GET',
	    data :'json',
	    url  :  "<s:url action='RouteDropDownList1'/>",
	    data :{
	    	routename: val,
	    },
	    success: function(data){
	        data =eval(data);
	        result=data;
	        $( "#project" ).autocomplete({
		        	minLength: 0,
		        	source: result,
		        	focus: function( event, ui ) {
		        	$( "#project" ).val( ui.item.route_number  );
		        	return false;
	        	},
	        	select: function( event, ui ) {
		        	$( "#project" ).val( ui.item.route_number );
		        	$( "#project-id" ).val( ui.item.route_name );
		        	return false;
	        	}
	        }).data( "ui-autocomplete" )._renderItem = function( ul, item ) {
	        	return $( "<li>" )
	        	.append( "<a>" + item.route_number + "</a>" )
	        	.appendTo( ul );
	       };
	    },
	    select: function (event, ui) {

	        alert("selected!");
	    },
	    change: function (event, ui) {

	        alert("changed!");
	    },
	    error : function(xhr, errmsg) {
	    	alert("No values found..!!"+errmsg);
	    }
	});
}




var routeidreldata=[];
var aaa = 0;
var route_id = 0;
var routePointsArray = [];
$("#gmap_basic").html('');
var viewStartLeft = 20;
var viewStartTop = 20;
var viewEndRight = 20;

var canvasObj = document.getElementById("gmap_basic");

var canvasW = parseInt(Math.round($("#gmap_basic").width()))-viewStartLeft-viewEndRight;
var ratioOfPix = 0;
var canvasCntrl = canvasObj.getContext("2d");
function getRouteDataMap(valmap){
	//alert(valmap);
	var markers = [];
	var vehiclemarker = [];
	var poly;
	var isfirst = false;
	var liveTrackingDeviceId, maxId = null;
	var lastLat = 0.0;
	var lastLong = 0.0;
	var geocoder;
	var map;
	var mapOptions;
	var bounds = null;
	var sec = 0;
	var min = 0;
	var hour = 0;
	var stlatitude = 0;
	var stlongitude = 0;
	var stlatitudeonsubmit = 0;
	var stlongitudeonsubmit = 0;
	var endlatitudeonsubmit = 0;
	var endlongitudeonsubmit = 0;
	var endlatitude = 0;
	var endlongitude = 0;
	var totdist = 0;
	var timerInterval = null;
	var totspeed = 0;
	var avgspeedcounter = 0;
	var infowindow = null;
	var schNum = "";
	var $plotvehicleRequest;
	var $coordinateRequest;
	
// 	if(valmap==0){
		var det=$("#project-id").val().split("-");
	     routeNo = $("#project").val();
		//alert(det[0]+""+det[1]+""+det[2]);
		var retfun=getdeviatedRouteTrac(det[0],det[1],det[2],0);
		retfun=true;
		console.log(retfun);
		if(retfun==true){
			getOnlineVehicles(routeNo,valmap);
		}else{
			return false;
		}
// 		this.getCordinatesFromMarkerPlan();
// 	}

	
 }

function getOnlineVehicles(routeNo,valmap){
	if(valmap==0){
		//alert("hii");
	map = new google.maps.Map(document.getElementById('gmap_basic'), mapOptionsAll);
	$plotvehicleRequest = $.ajax({
		           url :  "<%=request.getContextPath()%>/TripPlannerMapDisplayActionRoute?routeName="+routeNo,
				method : "post",
				success : function(response) {
					console.log(response);
					//console.log(result[i]);
					for ( var i = 0; i < vehiclemarker.length; i++) {
						vehiclemarker[i].setMap(null);

					}
					$('#loadingmessage').hide();
					vehiclemarker = [];
					// console.log(response);
					deleteMarkers();
					var infowindow = new google.maps.InfoWindow();
					var obj = jQuery.parseJSON(response);
					console.log(obj[i]["vehicleNo"]);
					var j = 0;
					var info = "";
					var i = 0;
					if (obj != null) {
						var objlength = obj.length;
						for (i = 0; i < objlength; i++) {
							var latLong = new google.maps.LatLng(
									obj[i]["LAT"], obj[i]["LONGITUDE"]);
							//var time = obj["aaData"][i][3].split(" ");
							var bus_icon = "assets/images/bus-map-icon.png";
							/* if (obj[i]["SPEED"] > 5) {
								bus_icon = 'assets/images/bus-map-icon.png';
							} else if ((obj[i]["SPEED"] <= 5)
									&& (obj[i]["SPEED"] > 0)) {
								bus_icon = 'assets/images/Icon-Bus-Orange.png';
							} else if ((obj[i]["SPEED"] == "0.00")) {
								bus_icon = 'assets/images/Icon-Bus-Blue.png';
							}
							if ((obj[i]["SPEED"] == "0")) {
								bus_icon = 'assets/images/grey.png';
							} */
							
							
							var marker = new google.maps.Marker(
									{
										map : map,
										position : latLong,
										icon : bus_icon,
										optimized: false,
										// icon : (obj["aaData"][i][2] >
										// 5)?'assets/images/Bus-Icon-Green.png':
										// //
										// (obj["aaData"][i][2]==="0.00")?'assets/images/Icon-Bus-Blue.png':
										// 'assets/images/Bus-Icon.png',
										latitude : obj[i]["LAT"],
										longitude : obj[i]["LONGITUDE"],
										deviceid : obj[i]["vehicleNo"],
										//vehicledirection : obj["aaData"][i][8],
										schno : '<div class="portlet-body form">'
												+ '<div class="form-body">'
												+ '<div class="table-responsive" style="color:#000000;width: 400px;" ><table class="table table-hover"><tr>'
												+ '<tr><td align=""><B>Vehicle</B></th><td>'
												+ obj[i]["vehicleNo"]
												+ '</td></tr>'
											
												+ '<tr><td  align=""><B>Route No</B></th><td id="schNumber">'
												+ routeNo
												+ '</td></tr>'
												+ '<tr><td  align=""><B>Last Bus Stop Crossed</B></th><td id="schNumber">'
												+ obj[i]["lastBustopCoveredBusTop"]
												+ '</td></tr>'
												+ '<tr><td  align=""><B>Next Bus Stop</B></th><td id="schNumber">'
												+ obj[i]["nextBusStop"]
												+ '</td></tr>'
// 												+ '<tr><td align=""><B>Current Location:</B></th><td><div id="location"></div></td></tr>'
// 												+ '<tr><td>'
												/* + '<a data-toggle="modal" class="btn blue" role="button" '
												+ "onclick=\"javascript:getCordinates('"
												+ obj[i]["device_id"]
												+ "',00,'all')\"/>"
												+ "Track Online </a></td>" */
// 												+ ' <td><a data-toggle="modal" class="btn yellow" role="button" onclick="javascript:AllDevices.get_reverse_geocode('
// 												+ obj[i]["LAT"]
// 												+ ','
// 												+ obj[i]["LONGITUDE"]
// 												+ ')">Location</a></td>'
												+ '</table>'
												+ '</div>'
												+ '</div>'
												+ '</div>',
										info : '<div class="portlet-body form">'
												+ '<div class="form-body">'
												+ '<div class="table-responsive" style="color:#000000;width: 400px;" ><table class="table table-hover"><tr>'
												// + '<tr><td
												// align=""><B>ID:</B></th><td>'
												// + obj["aaData"][i][5]
												// + '</td></tr>'
												+ '<tr><td  align=""><B>GPS Time:</B></th><td>'
												//+ time[0]
												+ " "
												//+ time[1]
												+ '</td></tr>'
												+ '<tr><td align=""><B>Vehicle Number:</B></th><td>'
												+ obj[i]["vehicleNo"]
												+ '</td></tr>'
												+ '<tr><td  align=""><B>Depot Name:</B></th><td>'
												//+ obj["aaData"][i][12]
												+ '</td></tr>'
												+ '<tr><td  align=""><B>Schedule No:</B></th><td id="schNumber">'
												//+ obj["aaData"][i][10]												+ '</td></tr>'
												+ '<tr><td align=""><B>Device Number:</B></th><td>'
											//	+ obj["aaData"][i][4]
												+ '</td></tr>'
												+ ''
												+ '</td></tr>'
												+ '<tr><td align=""><B>Phone Number:</B></th><td>'
											//	+ obj["aaData"][i][7]
												+ '</td></tr>'
												+ '<tr><td align=""><B>Speed:</B></th><td>'
											//	+ obj["aaData"][i][2]
												+ 'Kmph</td></tr>'
												// + '<tr><td
												// align=""><B>Latitude:</B></th><td>'
												// + obj["aaData"][i][0]
												// + '</td></tr>'
												// + '<tr><td
												// align=""><B>Longitude:</B></th><td>'
												// + obj["aaData"][i][1]
												// + '</td></tr>'
												+ '<tr><td align=""><B>Current Location:</B></th><td><div id="location"></div></td></tr>'
												+ '<tr><td>'
												//+ '<a data-toggle="modal" class="btn blue" role="button" '
												//+ "onclick=\"javascript:getCordinates('"
											//	+ obj["aaData"][i][4]
												//+ "',00,'all')\"/>"
												//+ "Track Online </a></td>"
												+ ' <td><a data-toggle="modal" class="btn yellow" role="button" onclick="javascript:AllDevices.get_reverse_geocode('
												+ obj[i]["LAT"]
												+ ','
												+ obj[i]["LONGITUDE"]
												+ ')">Location</a></td>'
												/* + ' <td><a data-toggle="modal" class="btn blue" role="button" href="#mymodal1" '
												+ "onclick=\"javascript:getVehData('"
												+ obj["aaData"][i][4]
												+ "','"
												+ obj["aaData"][i][6]
												+ "','"
												+ time[0] + " " + time[1]
												+ "','"
												+ obj["aaData"][i][0]
												+ "','"
												+ obj["aaData"][i][1]
												+ "','"
												+ obj["aaData"][i][13]
												+ "','"
												+ obj["aaData"][i][14]
												+ "','"
												+ obj["aaData"][i][15]
												+ "','"
												+ obj["aaData"][i][12]
												+ "','"
												+ obj["aaData"][i][10]
												+ "')\"/>"
												+ "Change Vehicle Status</a></td>" */
												+ '</tr></table>'
												+ '</div>'
												+ '</div>'
												+ '</div>'
									});// End of marker defination
							// Setting Info window
							/* google.maps.event
									.addListener(
											marker,
											'click',
											(function(marker, j) {
												return function() {
													infowindow.setContent(this.info);
													infowindow.open(map, this);
													AllDevices.get_reverse_geocode(this.latitude,this.longitude);
													//AllDevices.get_schedule_number(this.deviceid);
												}
											})(marker, j)); */
							google.maps.event
									.addListener(
											marker,
											'mouseover',
											(function(marker, j) {
												return function() {
													infowindow
															.setContent(this.schno);
													infowindow.open(map, this);
													
												}
											})(marker, j));
							// google.maps.event
							// .addListener(
							// marker,
							// 'mouseout',
							// (function(marker, j) {
							// return function() {
							// infowindow
							// .setContent(this.schno);
							// infowindow.open(map, this);
							// AllDevices
							// .get_schedule_number(this.deviceid);
							// }
							// })(marker, j));
							google.maps.event.addListener(map, 'click',
									function() {
										infowindow.open(null, null);
									});
							j++;
							// Setting Center location
							
							// Setting marker
							// on map
						} // End of for loop
						setAllMap(map);
					}
					// map.setCenter(latLong);
				}
			});
	
	}else{
		//alert("hii++");
		$plotvehicleRequest = $.ajax({
	           url :  "<%=request.getContextPath()%>/TripPlannerMapDisplayActionRoute?routeName="+routeNo,
			method : "post",
			success : function(response) {
				console.log(response);
				//console.log(result[i]);
				for ( var i = 0; i < vehiclemarker.length; i++) {
					vehiclemarker[i].setMap(null);

				}
				$('#loadingmessage').hide();
				vehiclemarker = [];
				// console.log(response);
				deleteMarkers();
				var infowindow = new google.maps.InfoWindow();
				var obj = jQuery.parseJSON(response);
				console.log(obj[i]["vehicleNo"]);
				var j = 0;
				var info = "";
				var i = 0;
				if (obj != null) {
					var objlength = obj.length;
					for (i = 0; i < objlength; i++) {
						var latLong = new google.maps.LatLng(
								obj[i]["LAT"], obj[i]["LONGITUDE"]);
						//var time = obj["aaData"][i][3].split(" ");
						var bus_icon = "assets/images/bus-map-icon.png";
						/* if (obj[i]["SPEED"] > 5) {
							bus_icon = 'assets/images/bus-map-icon.png';
						} else if ((obj[i]["SPEED"] <= 5)
								&& (obj[i]["SPEED"] > 0)) {
							bus_icon = 'assets/images/Icon-Bus-Orange.png';
						} else if ((obj[i]["SPEED"] == "0.00")) {
							bus_icon = 'assets/images/Icon-Bus-Blue.png';
						}
						if ((obj[i]["SPEED"] == "0")) {
							bus_icon = 'assets/images/grey.png';
						} */
						
						
						var marker = new google.maps.Marker(
								{
									map : map,
									position : latLong,
									icon : bus_icon,
									optimized: false,
									// icon : (obj["aaData"][i][2] >
									// 5)?'assets/images/Bus-Icon-Green.png':
									// //
									// (obj["aaData"][i][2]==="0.00")?'assets/images/Icon-Bus-Blue.png':
									// 'assets/images/Bus-Icon.png',
									latitude : obj[i]["LAT"],
									longitude : obj[i]["LONGITUDE"],
									deviceid : obj[i]["vehicleNo"],
									//vehicledirection : obj["aaData"][i][8],
									schno : '<div class="portlet-body form">'
											+ '<div class="form-body">'
											+ '<div class="table-responsive" style="color:#000000;width: 400px;" ><table class="table table-hover"><tr>'
											+ '<tr><td align=""><B>Vehicle</B></th><td>'
											+ obj[i]["vehicleNo"]
											+ '</td></tr>'
										
											+ '<tr><td  align=""><B>Route No</B></th><td id="schNumber">'
											+ routeNo
											+ '</td></tr>'
											+ '<tr><td  align=""><B>Last Bus Stop Crossed</B></th><td id="schNumber">'
											+ obj[i]["lastBustopCoveredBusTop"]
											+ '</td></tr>'
											+ '<tr><td  align=""><B>Next Bus Stop</B></th><td id="schNumber">'
											+ obj[i]["nextBusStop"]
											+ '</td></tr>'
// 											+ '<tr><td align=""><B>Current Location:</B></th><td><div id="location"></div></td></tr>'
// 											+ '<tr><td>'
											/* + '<a data-toggle="modal" class="btn blue" role="button" '
											+ "onclick=\"javascript:getCordinates('"
											+ obj[i]["device_id"]
											+ "',00,'all')\"/>"
											+ "Track Online </a></td>" */
// 											+ ' <td><a data-toggle="modal" class="btn yellow" role="button" onclick="javascript:AllDevices.get_reverse_geocode('
// 											+ obj[i]["LAT"]
// 											+ ','
// 											+ obj[i]["LONGITUDE"]
// 											+ ')">Location</a></td>'
											+ '</table>'
											+ '</div>'
											+ '</div>'
											+ '</div>',
									info : '<div class="portlet-body form">'
											+ '<div class="form-body">'
											+ '<div class="table-responsive" style="color:#000000;width: 400px;" ><table class="table table-hover"><tr>'
											// + '<tr><td
											// align=""><B>ID:</B></th><td>'
											// + obj["aaData"][i][5]
											// + '</td></tr>'
											+ '<tr><td  align=""><B>GPS Time:</B></th><td>'
											//+ time[0]
											+ " "
											//+ time[1]
											+ '</td></tr>'
											+ '<tr><td align=""><B>Vehicle Number:</B></th><td>'
											+ obj[i]["vehicleNo"]
											+ '</td></tr>'
											+ '<tr><td  align=""><B>Depot Name:</B></th><td>'
											//+ obj["aaData"][i][12]
											+ '</td></tr>'
											+ '<tr><td  align=""><B>Schedule No:</B></th><td id="schNumber">'
											//+ obj["aaData"][i][10]												+ '</td></tr>'
											+ '<tr><td align=""><B>Device Number:</B></th><td>'
										//	+ obj["aaData"][i][4]
											+ '</td></tr>'
											+ ''
											+ '</td></tr>'
											+ '<tr><td align=""><B>Phone Number:</B></th><td>'
										//	+ obj["aaData"][i][7]
											+ '</td></tr>'
											+ '<tr><td align=""><B>Speed:</B></th><td>'
										//	+ obj["aaData"][i][2]
											+ 'Kmph</td></tr>'
											// + '<tr><td
											// align=""><B>Latitude:</B></th><td>'
											// + obj["aaData"][i][0]
											// + '</td></tr>'
											// + '<tr><td
											// align=""><B>Longitude:</B></th><td>'
											// + obj["aaData"][i][1]
											// + '</td></tr>'
											+ '<tr><td align=""><B>Current Location:</B></th><td><div id="location"></div></td></tr>'
											+ '<tr><td>'
											//+ '<a data-toggle="modal" class="btn blue" role="button" '
											//+ "onclick=\"javascript:getCordinates('"
										//	+ obj["aaData"][i][4]
											//+ "',00,'all')\"/>"
											//+ "Track Online </a></td>"
											+ ' <td><a data-toggle="modal" class="btn yellow" role="button" onclick="javascript:AllDevices.get_reverse_geocode('
											+ obj[i]["LAT"]
											+ ','
											+ obj[i]["LONGITUDE"]
											+ ')">Location</a></td>'
											/* + ' <td><a data-toggle="modal" class="btn blue" role="button" href="#mymodal1" '
											+ "onclick=\"javascript:getVehData('"
											+ obj["aaData"][i][4]
											+ "','"
											+ obj["aaData"][i][6]
											+ "','"
											+ time[0] + " " + time[1]
											+ "','"
											+ obj["aaData"][i][0]
											+ "','"
											+ obj["aaData"][i][1]
											+ "','"
											+ obj["aaData"][i][13]
											+ "','"
											+ obj["aaData"][i][14]
											+ "','"
											+ obj["aaData"][i][15]
											+ "','"
											+ obj["aaData"][i][12]
											+ "','"
											+ obj["aaData"][i][10]
											+ "')\"/>"
											+ "Change Vehicle Status</a></td>" */
											+ '</tr></table>'
											+ '</div>'
											+ '</div>'
											+ '</div>'
								});// End of marker defination
						// Setting Info window
						/* google.maps.event
								.addListener(
										marker,
										'click',
										(function(marker, j) {
											return function() {
												infowindow.setContent(this.info);
												infowindow.open(map, this);
												AllDevices.get_reverse_geocode(this.latitude,this.longitude);
												//AllDevices.get_schedule_number(this.deviceid);
											}
										})(marker, j)); */
						google.maps.event
								.addListener(
										marker,
										'mouseover',
										(function(marker, j) {
											return function() {
												infowindow
														.setContent(this.schno);
												infowindow.open(map, this);
												
											}
										})(marker, j));
						// google.maps.event
						// .addListener(
						// marker,
						// 'mouseout',
						// (function(marker, j) {
						// return function() {
						// infowindow
						// .setContent(this.schno);
						// infowindow.open(map, this);
						// AllDevices
						// .get_schedule_number(this.deviceid);
						// }
						// })(marker, j));
						google.maps.event.addListener(map, 'click',
								function() {
									infowindow.open(null, null);
								});
						j++;
						// Setting Center location
						
						// Setting marker
						// on map
					} // End of for loop
					setAllMap(map);
				}
				// map.setCenter(latLong);
			}
		});
	}
	
}



//Calling Methods to Plot on GMAP....
function getCordinates(DEVICE_ID, ID, startDate, endDate, value) {
	if ($plotvehicleRequest != null){ 
		$plotvehicleRequest.abort();
		$plotvehicleRequest = null;
	}
	if ($coordinateRequest != null){ 
		$coordinateRequest.abort();
		$coordinateRequest = null;
	}
	$('#loadingmessage').hide();
	map = new google.maps.Map(document.getElementById('gmap_basic'), mapOptions);
	if(startDate=='all'){
	var trafficLayer = new google.maps.TrafficLayer();
	trafficLayer.setMap(map);
	}
	deleteMarkersvehicle();
	if (intervalID != null) {
		clearInterval(intervalID);
	}
	
	deleteMarkers();
	deleteMarkersvehicle();
	if (tmeout != null && tmeout.length>0) {
//		alert(tmeout.length);
		for (var i =0; i < tmeout.length; i++){
			clearTimeout(tmeout[i]);
		}
	}
	isfirst = false;
	

	if (maxId == null) {
		ID = 0;
	}
	if (intervalID != null) {
		clearInterval(intervalID);
	}

	$('#vehicledetails').remove();

	if ($('#vehicledetails').length) {

	} else {
		if (value === 'Submit') {

		} else {
			var homeControlDiv = document.createElement('div');
			homeControlDiv.id = 'vehicledetails';
			var homeControl = new HomeControl(homeControlDiv, map, DEVICE_ID,
					ID, startDate, endDate, value);
			homeControlDiv.index = 1;
			map.controls[google.maps.ControlPosition.RIGHT_TOP]
					.push(homeControlDiv);
		}
	}
	var mypos = 0;var currspeed=0;
	intervalID = setInterval(
			function() {
				if (value === 'Submit') {

				} else {
					changeDistanceValue(DEVICE_ID, ID, startDate, endDate,
							value);
				}
				var iconsetngs = {
						path : google.maps.SymbolPath.FORWARD_CLOSED_ARROW,
						scale : 2,
						fillColor : "yellow",
						strokeColor : "red",
						strokeOpacity : 1,
						strokeWeight : 1
					};
				var path=[];
				$coordinateRequest = $.ajax({
							url : "actgetCordinates.action",
							data : "DEVICE_ID=" + DEVICE_ID + "&ID=" + ID
									+ "&startdate=" + startDate + "&enddate="
									+ endDate + "&value=" + value,
							method : "post",
							async : true,
							success : function(response) {
								// Response Check
								poly=null;poly=[];
								infowindow = new google.maps.InfoWindow();
								
								var info = "";
								var j, i = 0;

								// Parsing response
								var obj = jQuery.parseJSON(response);
								if (obj != null) {
									var objLength = obj["aaData"].length;
									for (i = 0; i < objLength; i++) {

										if (liveTrackingDeviceId == null) {
											liveTrackingDeviceId = DEVICE_ID;
										}
										if (liveTrackingDeviceId !== DEVICE_ID) {
											isfirst = false;
											liveTrackingDeviceId = DEVICE_ID;
											ID = 0;
										} else {
											// AllDevices.deleteLastMarker();
										}

										if (i === 0) {
											// G-Map image iconfor Current bus
											// stop
											//imgicon = 'assets/images/bus-map-icon.png';
											ID = obj["aaData"][i][5];
											lastLat = obj["aaData"][i][0];
											lastLong = obj["aaData"][i][1];
										} else {
											// G-Map image icon for Intermediate
											// geo-points
											//imgicon = google.maps.SymbolPath.FORWARD_CLOSED_ARROW// 'assets/images/red-marker-icon.png';
										}
										if (!isfirst
												&& (objLength - i - 1) === 0) {
											// G-Map image icon for first
											// geo-points
											isfirst = true;
											//imgicon = 'assets/images/BusIconRedFlag.png';
										}
										var latLong = new google.maps.LatLng(
												obj["aaData"][i][0],
												obj["aaData"][i][1]);
										var time = obj["aaData"][i][3]
												.split(" ");
										//Distance Calc.
										var time  =obj["aaData"][i][3].split(" ");
										$("#speed").html(obj["aaData"][i][2] + "Kmph");
										$("#updateTime").html(time[1]);
										endlatitude = obj["aaData"][i][0];
										endlongitude = obj["aaData"][i][1];
										var dist = getDistanceFromLatLonInKm(stlatitude,
												stlongitude, endlatitude, endlongitude);
										
										
										//END
										var marker = new google.maps.Marker(
												{
													map : map,
													position : latLong,
													icon : imgicon,
													latitude : obj["aaData"][i][0],
													longitude : obj["aaData"][i][1],
													vehicledirection : obj["aaData"][i][8],
													info : '<div class="portlet-body form">'
															+ '<div class="form-body">'
															+ '<div class="table-responsive" style="color:#000000"><table class="table table-hover"><tr>'
															// + '<tr><td
															// align=""><B>ID:</B></th><td>'
															// +
															// obj["aaData"][i][5]
															// + '</td></tr>'
															+ '<tr><td  align=""><B>GPS Time:</B></th><td>'
															+ time[0]
															+ " "
															+ time[1]
															+ '</td></tr>'
															+ '<tr><td align=""><B>Vehicle Number:</B></th><td>'
															+ obj["aaData"][i][6]
															+ '</td></tr>'
															+ ''
															+ '</td></tr>'
															+ '<tr><td align=""><B>Phone Number:</B></th><td>'
															+ obj["aaData"][i][7]
															+ '</td></tr>'
															+ '<tr><td  align=""><B>Depot Name:</B></th><td>'
															+ obj["aaData"][i][14]
															+ '</td></tr>'
															+ '<tr><td  align=""><B>Schedule No:</B></th><td id="schNumber">'
															+ obj["aaData"][i][15]
															+ '</td></tr>'
															+ '<tr><td align=""><B>Device Number:</B></th><td>'
															+ obj["aaData"][i][4]
															+ '</td></tr>'
															+ '<tr><td align=""><B>Speed:</B></th><td>'
															+ obj["aaData"][i][2]
															+ 'Kmph</td></tr>'
															// + '<tr><td
															// align=""><B>Latitude:</B></th><td>'
															// +
															// obj["aaData"][i][0]
															// + '</td></tr>'
															// + '<tr><td
															// align=""><B>Longitude:</B></th><td>'
															// +
															// obj["aaData"][i][1]
															// + '</td></tr>'
															+ '<tr><td align=""><B>Current Location:</B></th><td><div id="location"></div></td></tr></table>'
															+ '</div>'
															+ '</div>'
															+ '</div>'
												});// End of marker defination
										
										google.maps.event
												.addListener(
														marker,
														'click',
														(function(marker, j) {
															return function() {
																if (infowindow) {
																	infowindow
																			.close();
																}
																infowindow
																		.setContent(this.info);
																infowindow
																		.open(
																				map,
																				this);
																AllDevices
																		.get_reverse_geocode(
																				this.latitude,
																				this.longitude);

//																AllDevices
//																		.get_schedule_number(DEVICE_ID);
															}
														})(marker, j));
										google.maps.event
												.addListener(
														marker,
														'click',
														(function(marker, j) {
															return function() {
																if (infowindow) {
																	infowindow
																			.close();
																}
																infowindow
																		.setContent(this.info);
																infowindow
																		.open(
																				map,
																				this);
																AllDevices
																		.get_reverse_geocode(
																				this.latitude,
																				this.longitude);
//
//																AllDevices
//																		.get_schedule_number(DEVICE_ID);
															}
														})(marker, j));
										google.maps.event
												.addListener(
														map,
														'click',
														function() {
															infowindow.open(
																	null, null);
														});
										j++;
										// Push if Last Geocode Same...
										if (obj["aaData"][i][0] != '0.00000000') {
											markers.push(marker);
											path.push(latLong);
										}
										currspeed=obj["aaData"][i][2];
									}
									// End of for loop
								}
								latLong = new google.maps.LatLng(
										obj["aaData"][0][0],
										obj["aaData"][0][1]);
								map.setCenter(latLong);
								AllDevices.changeIcon();
								var flightPlanCoordinates = [];
								//flightPlanCoordinates.push(latLong);
								
								for (mrk = mypos; mrk < markers.length; mrk++) {
									flightPlanCoordinates.push(markers[mrk].position);
								}
								mypos++;
								var flightPath =null;
								if(currspeed < 70){
								 flightPath = new google.maps.Polyline({
									path : flightPlanCoordinates,
									geodesic : true,
									strokeColor : '#009933',
									strokeOpacity : 1.0,
									strokeWeight : 8,
									icons : [ {
										repeat : '70px', // CHANGE THIS VALUE
										// TO CHANGE THE
										// DISTANCE BETWEEN
										// ARROWS
										icon : iconsetngs,
										offset : '100%'
									} ],
									draggable: false
								});
								}else{
									flightPath = new google.maps.Polyline({
										path : flightPlanCoordinates,
										geodesic : true,
										strokeColor : '#CC0000',
										strokeOpacity : 1.0,
										strokeWeight : 8,
										icons : [ {
											repeat : '70px', // CHANGE THIS VALUE
											// TO CHANGE THE
											// DISTANCE BETWEEN
											// ARROWS
											icon : iconsetngs,
											offset : '100%'
										} ],
										draggable: false
									});
								}
								flightPath.setMap(null);
								flightPath.setMap(map);
							}
						});
			}, 10000);

}
var vehdetroudata="";
var vehiclesData = [];
var dots = [];
function getdetails(routeid) {
	$('#loadingmessage').show(); 
	$("#myCanvas").html('');
	var routeNameWithDirection = $("#project").val();
	addListener();
	var data =  $.ajax({
		type: "get",
   	   	async:false,
   	   	data : 'json',
		url :  "<%=request.getContextPath()%>/VehicleList?routeName="+routeNameWithDirection,
	    success: function(data){}
	}).responseText;
	try{
		
		result=JSON.parse(data);
		//alert(data);
	    console.log(result);
	   	dataSet = [];
	   	vehiclesData =[];
		var c=document.getElementById("myCanvas");
		var ctrl=c.getContext("2d");
		ctrl.clearRect(0, 0, c.width, 75);
		//ctrl.beginPath();
		dots = [];
		if(result.length>0){
		   	for(var i=0;i<result.length;i++){
				for(var k=0;k<routePointsArray.length;k++){
					
					if(result[i]["lastBustopCoveredBusTopId"]==routePointsArray[k].busStopId){     
						//parseInt(routePointsArray[k].accDistance)+parseInt(Math.round(result[i]["traveldistance"])
						var startpoint =  (10+parseInt(routePointsArray[k].accDistance)+parseInt(Math.round(result[i]["travelledDistance"])));
						ctrl.beginPath();
						ctrl.arc(startpoint*ratioOfPix, 60, 4, 0, Math.PI*2); 
						ctrl.lineWidth = 2;
						ctrl.strokeStyle = "red";
						ctrl.stroke();
						ctrl.restore(); 
						/* var imgBasket = new Image();
						imgBasket.src = "assets/images/track_blue.png";
						ctrl.drawImage(imgBasket,startpoint*ratioOfPix,50,20,20); */
						
						dots.push({
					        x: startpoint*ratioOfPix,
					        y: 60,
					        r: 4,
					        rXr: 7,
					        /* color: "red", */
					        tipText:"<h5>Vehilce No : <b>"+result[i].vehicleNo+" </b></h4>"
					        +"<h5>Last Covered stop : <b>"+result[i].lastBustopCoveredBusTop+"</b></h4>"
					        +"<h5>Next stop : <b>"+result[i].nextBusStop+"</b></h4>",
					    });
						console.log(startpoint*ratioOfPix);
					} 
				}
		   	}
		   	$('#loadingmessage').hide(); 
		}else{
			bootbox.alert("No Vehicles Found");	
			$('#loadingmessage').hide(); 
		}
	}catch(e){
		alert(e);
		bootbox.alert("Please check connection");
		$('#loadingmessage').hide(); 
	}
 }
var graph = document.getElementById("myCanvas");
var ctx = graph.getContext("2d");
var canvasOffset = $("#myCanvas").offset();


function addListener(){
	$("#myCanvas").mousemove(function (e) {
		//console.log(dots);
		var canvas = document.getElementById("myCanvas");
	   handleMouseMove(e,canvas);
	});
}
//show tooltip when mouse hovers over dot
function handleMouseMove(e,canvas) {
	var offsetX = canvasOffset.left;
	var offsetY = canvasOffset.top;
	//console.log(offsetX+" "+offsetY);
    var mx =  e.clientX +xMousePos - offsetX;
    var my = e.clientY + yMousePos- offsetY; 
    for (var i = 0; i < dots.length; i++) {
   // console.log((mx-dots[i].x)*(mx-dots[i].x)+ (my-dots[i].y)*(my-dots[i].y)+" "+(dots[i].rXr * dots[i].rXr));
		if (((mx-dots[i].x)*(mx-dots[i].x)+ (my-dots[i].y)*(my-dots[i].y)) < (dots[i].rXr * dots[i].rXr)){
            $("#tipText").html(dots[i].tipText);
            	   $("#popup").click();
	    }
    }
}
$(window).scroll(function(event) {
    if(lastScrolledLeft != $(document).scrollLeft()){
        xMousePos -= lastScrolledLeft;
        lastScrolledLeft = $(document).scrollLeft();
        xMousePos += lastScrolledLeft;
    }
    if(lastScrolledTop != $(document).scrollTop()){
        yMousePos -= lastScrolledTop;
        lastScrolledTop = $(document).scrollTop();
        yMousePos += lastScrolledTop;
    }
});
function getMousePos(canvas, evt) {
    var rect = canvas.getBoundingClientRect();
    return {
      x: evt.clientX - rect.left,
      y: evt.clientY - rect.top
    };
  } 
 function getPonitType(pointId,points){
	 var pointType = 0;
	 //console.log(points);
	 for( var i=0;i<points.length;i++){     
		 //alert(points[i]["1"] +" "+ pointId);
		 if(points[i][1]==pointId){
			 pointType = points[i][7];
		 }
	 }
	
	 return pointType;
 }
 var xMousePos = 0;
 var yMousePos = 0;
 var lastScrolledLeft = 0;
 var lastScrolledTop = 0;
 
 
 
 

 
 var map;
	var firstcordinate;
	function initialize() {
		// create the geocoder
		geocoder = new google.maps.Geocoder();
		// set some default map details, initial center point, zoom and style
		
		var tmpLatLng = new google.maps.LatLng(12.9746, 77.5946);
		// create the map and reference the div#map-canvas container		
		<s:iterator value="list" id="list">
		//alert(<s:property value="LAT" />);		
		tmpLatLng = new google.maps.LatLng(<s:property value="LAT" />,
				<s:property value="LONGITUDE" />);
		// make and place map maker.
		var indMarker = new google.maps.Marker({
			map : map,
			position : tmpLatLng,
			icon : 'assets/images/Bus-Icon.png',
			title : '#' + "<s:property value="DEVICE_ID" />",
		});
		contentString = "<s:property value="DEVICE_ID" />";
		google.maps.event.addListener(indMarker, 'click', function(
				contentString) {
			return function() {
				infowindow.setContent(contentString);//set the content
				infowindow.open(map, this);
		 	};
		});
		markers.push(indMarker);
		</s:iterator> 
	} 
	google.maps.event.addDomListener(window, 'load', initialize);
</script>
</body>
</html>