var markers = [];
var encodedPolyLine = [];
var busstopmarkers = [];
var vehiclemarkers = [];
var map;
var mapOptions;
var intervalID = null;
var req_status = false;
var bounds = null;
var infowindow = null;
var bustopseq =[];
function showvehicleMarkers() {
	setAllVehicleMap(map);
}
function setAllVehicleMap(map) {
	for ( var i = 0; i < vehiclemarkers.length; i++) {
		vehiclemarkers[i].setMap(map);
	}
}
function showBusMarkers() {
	setAllBusMap(map);
}
function setAllBusMap(map) {
	for ( var i = 0; i < busstopmarkers.length; i++) {
		busstopmarkers[i].setMap(map);
	}
}
var PlotCordinates = function() {

}();
$(function() {
	mapOptions = {
		center : new google.maps.LatLng(12.9746, 77.5946),
		zoom : 13,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};
	map = new google.maps.Map(document.getElementById('route_map'), mapOptions);
	bounds = new google.maps.LatLngBounds();
});
var AllDevices = function() {
	return {
		// Main Function
		init : function() {
			if (!$().dataTable) {
				console.log("DataTable: Library does not exists !!!");
				return;
			} else {

			}
		},
		get_reverse_geocode : function(lat, lng) {
			geocoder = new google.maps.Geocoder();
			var latlng = new google.maps.LatLng(lat, lng);
			geocoder.geocode({
				'latLng' : latlng
			}, function(results, status) {
				if (status === google.maps.GeocoderStatus.OK) {
					if (results[1]) {
						$("#location").html(results[1].formatted_address);
						$("#geoContent").attr("style", "display:none")
					} else {
						alert('No results found');
					}
				} else {
					alert('Geocoder failed due to: ' + status);
				}
			});
		},
		return_get_reverse_geocode : function(lat, lng) {
			geocoder = new google.maps.Geocoder();
			var latlng = new google.maps.LatLng(lat, lng);
			geocoder.geocode({
				'latLng' : latlng
			}, function(results, status) {
				loc = "";
				if (status === google.maps.GeocoderStatus.OK) {
					if (results[1]) {
						$("#location").html();
						loc = results[1].formatted_address;
					} else {
						// alert('No results found');
						loc = "Location Not found";
					}
				} else {
					loc = 'Geocoder failed due to: ' + status;
				}
			});
		},
		allDevices : function() {
			// $("#all_devices").dataTable();
		},

	};

}();
function addEncodedPaths(encodedPaths) {
	for ( var i = 0, n = encodedPaths.length; i < n; i++) {
		setTimeout(function() {
		}, 100);
		addEncodedPath(encodedPaths[i]);
	}
}
function getStopsForRoute(bussops, end_point,start_point) {
	var locations;
	$.ajax({
		type : 'POST',
		data : {
			lat : '12',
			lng : '77',
			bussopslist : bussops,

		},
		url : "GetRouteStopMapForRoute.action",

		success : function(data) {
			result = data;

			locations = result.split("@@@");
			
			clearMarkers();
			var dloc;
			var count=0;
			for (i = 0; i < locations.length; i++) {
				dloc = locations[i].split("|");
				
				if (dloc[14] != 2 && dloc[14] != 13) {
					if(dloc[3]===start_point){
						imgicon = 'assets/images/red.png';
					}else if(dloc[3]===end_point){
						imgicon = 'assets/images/red.png';
					}else{
						imgicon = 'assets/images/blue_stop.png';
					}
				
				}
				
				if (laststopid == dloc[3]) {
					
					endstopcord = dloc[1] + "," + dloc[2];
				}
				if (dloc[14] != 2 && dloc[14] != 13) {
					marker = new google.maps.Marker({
						map : map,
						position : new google.maps.LatLng(dloc[1], dloc[2]),
						icon : imgicon,
						title : dloc[0] + " ("
								+ $('<div/>').html(dloc[5]).text() + ")"
								+ " Towards:" + dloc[10],

					});

				}

			}
			for(i=0;i<bustopseq.length;i++){
				$("#route_stops").html( $("#route_stops").html() + "<br />" +
						  (count + 1) + ". " + bustopseq[i]);
				count++;
			}
		},
		error : function(xhr, errmsg) {
			alert(errmsg);
		}
	});
}
var busstopdist = [];
var busstopid = [];
var busstopid1 = [];
function clearMarkers() {
	setAllMap(null);
}
function setAllMap(map) {
	for ( var i = 0; i < markers.length; i++) {
		markers[i].setMap(map);
	}
}
var RouteTracking = function() {

	return {
		init : function() {
			map = new google.maps.Map(document.getElementById('route_map'),
					mapOptions);
			for (i = 0; i < busstopmarkers.length; i++) {
				busstopmarkers[i].setMap(null);
			}
			busstopmarkers = [];
			for ( var i = 0; i < vehiclemarkers.length; i++) {
				vehiclemarkers[i].setMap(null);

			}
			clearInterval(intervalID);
			vehiclemarkers = [];
			
			$('#vehicle_stop_list').html('');
			$("#route_stops").html('');
			$("#msg_schedule").html('');
			$("#route_name_display").html('');
			if (!req_status) {
				req_status = true;
				var routeId = $("#routeList").val();
				var arr = routeId.split("-");
				$("#route_stops").html('');
				$("#msg_schedule").html("Fetching Route Information !!!!");
				getRouteTrac(arr[0],arr[1],arr[2],1);		
					}

		}
	}
}();
function getRouteTrac(routeID,start_point,end_point,rparam)
{
	var encodedPaths = [];
	$.ajax({
	    type        : 'POST',
	    data: {
	        routeid:  routeID,
	        start_point: start_point,
	        end_point:  end_point,
	        
	    },
	    url         :  'ShowRouteAjax.action',
	   
	success : function(response) {
		result = JSON.parse(response);
		var bussopslist = "";
		points = result.routemap;
		if(result !=null){
			if(rparam==1){
			$("#msg_schedule").html("Route Plotted !!!!");
			}
		for ( var i = 0; i < result.routepointlen; i++) {
			bussopslist = bussopslist
					+ result.routepoint[i][1] + ",";
			busstopid1.push(result.routepoint[i][1]);
			bustopseq.push(result.routepoint[i][6]);
			if (i == result.routepointlen - 1) {
				bussopslist = bussopslist.substring(0,
						bussopslist.length - 1);
				getStopsForRoute(bussopslist, end_point,start_point);
				laststopid = result.routepoint[i][1];
			}
		}
		var calcdist = 0;
		for ( var i = 0; i < result.routemaplen; i++) {
			busstopid.push(result.routemap[i][2]);
			calcdist = calcdist + result.routemap[i][6]
					+ result.routemap[i][7];
			if (result.routemap[i][15] == "TRIMAX") {
				calcdist = calcdist
						+ result.routemap[i][6]
						+ result.routemap[i][7];
			} else {
				busstopdist.push(calcdist);
			}
		}
		for ( var i = 0; i < result.routemaplen; i++) {
			encodedPaths.push(result.routemap[i][13]);
		}
		addEncodedPaths(encodedPaths);
		if(rparam==1){
		$("#vehicle_stop_list").html('');
		callingOnceVehicle(routeID);
		intervalID = setInterval(
				function() {
					$("#vehicle_stop_list").html('');
		callingOnceVehicle(routeID);},10000);
		}
		// END

	}else{
		$("#msg_schedule").html("Route Information Not Available !!!!");
	}
	}
});	

}
function addEncodedPath( encodedPath ) {  
    var path = google.maps.geometry.encoding.decodePath( encodedPath );
    var iconsetngs = {
    		path : google.maps.SymbolPath.FORWARD_CLOSED_ARROW,
    		scale : 2,
    		fillColor : "yellow",
    		strokeColor : "green",
    		strokeOpacity : 1,
    		strokeWeight : 1
    	};
    var polyline = new google.maps.Polyline({
		path : path,
		geodesic : true,
		strokeColor : "RED",
		strokeOpacity : 0.35,
		strokeWeight : 10,
		draggable : false,
		icons : [ {
			repeat : '70px', // CHANGE THIS VALUE TO CHANGE THE DISTANCE
			// BETWEEN ARROWS
			icon : iconsetngs,
			offset : '100%'
		} ]
	});
   polyline.setMap( map ); 
 
}

function callingOnceVehicle(route_id) {
	$("#route_name_display").html("Feching Vehicle Location Please Wait !!!!");
	var imgicon = 'assets/images/bus-map-icon.png';
	infowindow = new google.maps.InfoWindow();
	$
			.ajax({
				url : "routetracking.action?routeID=" + route_id,
				success : function(response) {
					// req_status = true;
					obj = jQuery.parseJSON($.trim(response));
					if (obj["deviceData"].length > 0) {
						$("#route_name_display").html(
								"Vehicles Plotted on Route !!!!");
						objLength = obj["deviceData"].length;
						$("#vehicle_stop_list")
								.html(
										$("#vehicle_stop_list").html()
												+ "<br />"
												+ "<B>#</B>"
												+ ". "
												+ "<B>(Vehicle No)-(Schedule No)-(Trip no)</B>");
						for (i = 0; i < objLength; i++) {
							$("#vehicle_stop_list").html(
									$("#vehicle_stop_list").html() + "<br />"
											+ (i + 1) + ".("
											+ obj["deviceData"][i][2] + ")-("
											+ (obj["deviceData"][i][1]) + ")-("
											+ obj["deviceData"][i][3] + ")");
							if (obj["deviceData"][4] != "") {
								var latLong = new google.maps.LatLng(
										obj["deviceData"][i][4],
										obj["deviceData"][i][5]);
								var marker = new google.maps.Marker(
										{
											map : map,
											position : latLong,
											icon : imgicon,
											latitude : obj["deviceData"][i][4],
											longitude : obj["deviceData"][i][5],
											info : '<div class="portlet-body form">'
													+ '<div class="form-body" >'
													+ '<div class="table-responsive" style="color:#000000;width:200px"><table class="table table-hover"><tr>'
													+ '<td align=""><B>Vehicle Number:</B></th><td>'
													+ obj["deviceData"][i][2]
													+ '</td></tr><tr>'
													+ '<td align=""><B>Depot Name:</B></th><td>'
													+ obj["deviceData"][i][7]
													+ '</td></tr><tr>'
													+ '<td align=""><B>Schedule Number:</B></th><td>'
													+ obj["deviceData"][i][1]
													+ '</td></tr><tr>'
													+ '<td align=""><B>Trip Number:</B></th><td>'
													+ obj["deviceData"][i][3]
													+ '</td></tr><tr>'
													+ '<td align=""><B>Speed KMPH:</B></th><td>'
													+ obj["deviceData"][i][6]
													+ '</td></tr></table>'
													+ '</div>'
													+ '</div>'
													+ '</div>'
										});// End
								// of
								// marker
								// defination

								google.maps.event.addListener(marker, 'click',
										function() {
											if (infowindow) {
												infowindow.close();
											}
											infowindow.setContent(this.info);
											infowindow.open(map, this);
										});
								google.maps.event.addListener(map, 'click',
										function() {
											infowindow.open(null, null);
										});
								vehiclemarkers.push(marker);
								showvehicleMarkers();
							}// if device
							// data
							// end
						}// End of for loop
					}// end of device data
					// length
					else {
						$("#route_name_display").html(
								"Vehicle Data Not Available !!!!");

					}
				},
				error : function() {
					console.log("Error Occured !!!!");
				}
			});
}