var markers = [];
var poly;
var isfirst = false;
var liveTrackingDeviceId, maxId = null;
var lastLat = 0.0;
var lastLong = 0.0;
var geocoder;
var map;
var mapOptions;
var plotMapFlag=false;
function plotVehicle() {
	
	initialize();
	deleteMarkers();
	
	plotVehilcesOnMap();
	/*intervalID = setInterval(function() {
		plotVehilcesOnMap();
	}, 10000);*/

}

function plotVehilcesOnMap() {
	/*alert("In Plot Vehicles On MAp ............");*/
	$
			.ajax({
				url : "actgetCordinates.action",
				data : "DEVICE_ID=ALL",
				method : "post",
				success : function(response) {
					// console.log(response);
					deleteMarkers();
					var infowindow = new google.maps.InfoWindow();
					var obj = jQuery.parseJSON(response);
					var j = 0;
					var info = "";
					var i = 0;
					if (obj != null) {
						var objlength = obj["aaData"].length;
						for (i = 0; i < objlength; i++) {
							var latLong = new google.maps.LatLng(
									obj["aaData"][i][0], obj["aaData"][i][1]);
							var time = obj["aaData"][i][3].split(" ");
							marker = new google.maps.Marker(
									{
										map : map,
										position : latLong,
										icon : (obj["aaData"][i][2] > 5) ? 'assets/images/bus-map-icon.png'
												: 'assets/images/Bus-Icon.png',
										latitude : obj["aaData"][i][0],
										longitude : obj["aaData"][i][1],
										info : '<div class="portlet-body form">'
												+ '<div class="form-body">'
												+ '<div class="table-responsive" style="color:#000000;width: 400px;" ><table class="table table-hover"><tr>'
												+ '<tr><td  align=""><B>ID:</B></th><td>'
												+ obj["aaData"][i][5]
												+ '</td></tr>'
												+ '<tr><td  align=""><B>GPS Time:</B></th><td>'
												+ time[0]
												+ " "
												+ time[1]
												+ '</td></tr>'
												+ '<tr><td align=""><B>Vehicle Number:</B></th><td>'+obj["aaData"][i][6]+'</td></tr>'
												+ '<tr><td align=""><B>Device Number:</B></th><td>'
												+ obj["aaData"][i][4]
												+ '</td></tr>'
												+ '<tr><td align=""><B>Speed:</B></th><td>'
												+ obj["aaData"][i][2]
												+ '</td></tr>'
												+ '<tr><td align=""><B>Latitude:</B></th><td>'
												+ obj["aaData"][i][0]
												+ '</td></tr>'
												+ '<tr><td align=""><B>Longitude:</B></th><td>'
												+ obj["aaData"][i][1]
												+ '</td></tr>'
												+ '<tr><td align=""><B>Current Location:</B></th><td><div id="location"></div></td></tr>'
												+ '<tr><td>'
												+ '<a data-toggle="modal" class="btn blue" role="button" '
												+ "onclick=\"javascript:getCordinates('"
												+ obj["aaData"][i][4]
												+ "',00)\"/>"
												+ "Track Online </a></td>"
												+ ' <td><a data-toggle="modal" class="btn yellow" role="button" onclick="javascript:AllDevices.get_reverse_geocode('
												+ obj["aaData"][i][0]
												+ ','
												+ obj["aaData"][i][1]
												+ ')">Location</a></td></tr></table>'
												+ '</div>'
												+ '</div>'
												+ '</div>'
									});// End of marker defination
							// Setting Info window
							google.maps.event.addListener(marker, 'click',
									(function(marker, j) {
										return function() {
											infowindow.setContent(this.info);
											infowindow.open(map, this);
											AllDevices.get_reverse_geocode(
													this.latitude,
													this.longitude);
										}
									})(marker, j));
							j++;
							// Setting Center location
							latLong = new google.maps.LatLng(
									obj["aaData"][0][0], obj["aaData"][0][1]);
							markers.push(marker);
							// marker.setMap(map); // Setting marker
							// on map
						} // End of for loop
						setAllMap(map);
					}
					map.setCenter(latLong);
				}
			});

	
}
function setAllMap(map) {
	for ( var i = 0; i < markers.length; i++) {
		markers[i].setMap(map);
	}
}

function clearMarkers() {
	setAllMap(null);
}

// Shows any markers currently in the array.
function showMarkers() {
	setAllMap(map);
}

function deleteMarkers() {
	clearMarkers();
	markers = [];
}


//SOS Details..
function getSosAttendedDetails()
{
	document.getElementById("alert_sosdetails_display").style.display = '';
	var fromDate=document.getElementById("fromDate").value;
	var toDate=document.getElementById("toDate").value;
	if(fromDate =="" && toDate=="")
		{
			bootbox.alert("Please Select Date Range");
			return false;
		}
	$('#SOS_ATTENDED_UNATTENDED').dataTable(
				{
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					"iDisplayLength" : 5,
					"sAjaxSource" : "getSosAttendedDetails.action?fromDate="+fromDate+"&toDate="+toDate,
					"sPaginationType" : "bootstrap",
					"bDestroy" : true,
					"oLanguage" : {
						"sLengthMenu" : "_MENU_ records",
						"oPaginate" : {
							"sPrevious" : "Prev",
							"sNext" : "Next"
						}
					},
					"aoColumnDefs" : [ {
						'bSortable' : false,
						'aTargets' : [ 0 ]
					} ]
				});
		jQuery('#SOS_ATTENDED_wrapper .dataTables_filter input').addClass(
				"form-control input-medium input-inline"); // modify table
		// search input
		jQuery('#SOS_ATTENDED_wrapper .dataTables_length select').addClass(
				"form-control input-xsmall input-inline");
		
// search input
}

//Unattended Details
function getSosUnAttendedDetails()
{
	document.getElementById("alert_sosdetails_display").style.display = '';
	var fromDate=document.getElementById("fromDate").value;
	var toDate=document.getElementById("toDate").value;
	if(fromDate =="" && toDate=="")
		{
			bootbox.alert("Please Select Date Range");
			return false;
		}
	$('#SOS_ATTENDED_UNATTENDED').dataTable(
				{
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					"iDisplayLength" : 5,
					"sAjaxSource" : "getSosUnAttendedDetails.action?fromDate="+fromDate+"&toDate="+toDate,
					"sPaginationType" : "bootstrap",
					"bDestroy" : true,
					"oLanguage" : {
						"sLengthMenu" : "_MENU_ records",
						"oPaginate" : {
							"sPrevious" : "Prev",
							"sNext" : "Next"
						}
					},
					"aoColumnDefs" : [ {
						'bSortable' : false,
						'aTargets' : [ 0 ]
					} ]
				});
		jQuery('#SOS_ATTENDED_UNATTENDED_wrapper .dataTables_filter input').addClass(
				"form-control input-medium input-inline"); // modify table
		// search input
		jQuery('#SOS_ATTENDED_UNATTENDED_wrapper .dataTables_length select').addClass(
				"form-control input-xsmall input-inline");
		
// search input
}


var geocoder;
var map;
var places;
function initialize() {
	// create the geocoder

	geocoder = new google.maps.Geocoder();
	// set some default map details, initial center point, zoom and style
	var mapOptions = {
		center : new google.maps.LatLng(12.9746, 77.5946),
		zoom : 13,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};
	var tmpLatLng = new google.maps.LatLng(12.9746, 77.5946);
	// create the map and reference the div#map-canvas container
	this.map = new google.maps.Map(document.getElementById("gmap_basic_1"),
			mapOptions);

	var polyOptions = {
		strokeColor : '#000000',
		strokeOpacity : 1.0,
		strokeWeight : 3
	};
	poly = new google.maps.Polyline(polyOptions);
	poly.setMap(map);

}
$(document).ready(function() {
	plotVehilcesOnMap();

});

function getSOSData(call_id, device_id, call_time, driver_name,
		conductor_name, vehicle_no, route_no, sch_no, depot_no) {
	$("#vehicle_no").val(vehicle_no);
	/* var  vehicle ='abc';
		//$("#vehicle_no").val();
	alert(vehicle);
	$
	.ajax({
		url : "getSOSData.action",
		data : "vehical="+vehicle,
		type: 'json',
		success : function(result) {
			//alert(result);
			var obj = jQuery.parseJSON(result);
			//alert(obj);
			alert(obj["aaData1"][0][0]+"and "+obj["aaData1"][0][1]);
			document.getElementById("depot_name").value =obj["aaData"][0][0];
			document.getElementById("route_no").value =obj["aaData1"][0][1];
			document.getElementById("schedule_no").value =obj["aaData1"][0][0];
		}
	}); */
	$("#call_id").val(call_id);
	$("#device_id").val(device_id);
	$("#call_time").val(call_time);
	$("#driver_name").val(driver_name);
	$("#conductor_name").val(conductor_name);
	$("#vehicle_no").val(vehicle_no);
	$("#route_no").val(route_no);
	$("#schedule_no").val(sch_no);
	$("#depot_name").val(depot_no);

}

function getRevenueDetails(chart_id) {
	//alert("chartid" +chart_id) ; 

	$('#revenue_depot').dataTable(
			{
				"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
				// per
				// page
				// values
				// here
				],
				// set the initial value
				"iDisplayLength" : 5,
				"sAjaxSource" : "getRevenueData_depot.action?parent_id="
						+ chart_id,
				"sPaginationType" : "bootstrap",
				"bDestroy" : true,
				"oLanguage" : {
					"sLengthMenu" : "_MENU_ records",
					"oPaginate" : {
						"sPrevious" : "Prev",
						"sNext" : "Next"
					}
				},
				"aoColumnDefs" : [ {
					'bSortable' : false,
					'aTargets' : [ 0 ]
				} ]
			});
	jQuery('#revenue_depot_wrapper .dataTables_filter input').addClass(
			"form-control input-medium input-inline"); // modify table
	// search input
	jQuery('#revenue_depot_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");

}
function getRevenueDetails_depot(parent_id) {
	/*  var divId = document.getElementById("mymodal_depot");
	divId.style.display = 'none'; */

	var divId1 = document.getElementById("mymodal_waybill");
	divId1.style.display = '';

	$('#revenue_way').dataTable({
		"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
		// per
		// page
		// values
		// here
		],
		// set the initial value
		"iDisplayLength" : 5,
		"sAjaxSource" : "getRevenueDataWay.action?parent_id=" + parent_id,
		"sPaginationType" : "bootstrap",
		"bDestroy" : true,
		"oLanguage" : {
			"sLengthMenu" : "_MENU_ records",
			"oPaginate" : {
				"sPrevious" : "Prev",
				"sNext" : "Next"
			}
		},
		"aoColumnDefs" : [ {
			'bSortable' : false,
			'aTargets' : [ 0 ]
		} ]
	});

	jQuery('#revenue_way_wrapper .dataTables_filter input').addClass(
			"form-control input-medium input-inline"); // modify table
	// search input
	jQuery('#revenue_way_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");

}

function getTicket_Depot(parent_id) {
	/*  var divId = document.getElementById("mymodal_depot");
	divId.style.display = 'none'; */

	var divId1 = document.getElementById("ticket_depot");
	divId1.style.display = '';

	$('#ticket_depot').dataTable(
			{
				"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
				// per
				// page
				// values
				// here
				],
				// set the initial value
				"iDisplayLength" : 5,
				"sAjaxSource" : "getTicketByData_depot.action?parent_id="
						+ parent_id,
				"sPaginationType" : "bootstrap",
				"bDestroy" : true,
				"oLanguage" : {
					"sLengthMenu" : "_MENU_ records",
					"oPaginate" : {
						"sPrevious" : "Prev",
						"sNext" : "Next"
					}
				},
				"aoColumnDefs" : [ {
					'bSortable' : false,
					'aTargets' : [ 0 ]
				} ]
			});

	jQuery('#ticket_depot_wrapper .dataTables_filter input').addClass(
			"form-control input-medium input-inline"); // modify table
	// search input
	jQuery('#ticket_depot_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");

}

function getTicket_Way(parent_id) {
	/*  var divId = document.getElementById("mymodal_depot");
	divId.style.display = 'none'; */

	var divId1 = document.getElementById("mymodal_ticket_waybill");
	divId1.style.display = '';

	$('#ticket_way').dataTable(
			{
				"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
				// per
				// page
				// values
				// here
				],
				// set the initial value
				"iDisplayLength" : 5,
				"sAjaxSource" : "getTicketByData_way.action?parent_id="
						+ parent_id,
				"sPaginationType" : "bootstrap",
				"bDestroy" : true,
				"oLanguage" : {
					"sLengthMenu" : "_MENU_ records",
					"oPaginate" : {
						"sPrevious" : "Prev",
						"sNext" : "Next"
					}
				},
				"aoColumnDefs" : [ {
					'bSortable' : false,
					'aTargets' : [ 0 ]
				} ]
			});

	jQuery('#ticket_way_wrapper .dataTables_filter input').addClass(
			"form-control input-medium input-inline"); // modify table
	// search inputgetTicket_Way
	jQuery('#ticket_way_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");

}

function check() {
	var val = $("#call_type option:selected").val();

	if (val == 1) {
		var divId = document.getElementById("breakdown_type");
		divId.style.display = 'none';
		var divId = document.getElementById("accident_type");
		divId.style.display = '';
		var type = 'accident';
		$.ajax({
			url : "getSOSType.action",
			data : "type=" + type,
			success : function(result) {

				document.getElementById('depotlist').innerHTML = result;
			}
		});
	} else {
		var divId1 = document.getElementById("accident_type");
		divId1.style.display = 'none';
		var divId = document.getElementById("breakdown_type");
		divId.style.display = '';

		var type = 'breakdown';
		$.ajax({
			url : "getSOSType.action",
			data : "type=" + type,
			success : function(result) {

				document.getElementById('vehiclelist').innerHTML = result;
			}
		});

	}
}
