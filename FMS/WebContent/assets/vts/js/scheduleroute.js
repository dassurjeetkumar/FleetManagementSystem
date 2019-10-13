var markers = [];
var busstopmarkers = [];
var poly;
var isfirst = false;
var liveTrackingDeviceId, maxId = null;
var lastLat = 0.0;
var lastLong = 0.0;
var geocoder;
var map;
var mapOptions;
var flightPlanCoordinates = [];
var encodedPolyLine = [];
var intervalID = null;
var bounds = null;
var flightPatharr = [];
var iconsetngs = "";
var infowindow=null;
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
var tmeout = [];
var vehiclemarker = [];
$(function() {

	mapOptions = {

		center : new google.maps.LatLng(12.9746, 77.5946),
		zoom : 13,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};
	map = new google.maps.Map(document.getElementById('route_map'), mapOptions);
	bounds = new google.maps.LatLngBounds();
	iconsetngs = {
		path : google.maps.SymbolPath.FORWARD_CLOSED_ARROW
	};
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
		get_schedule_number : function(deviceid) {

			$.ajax({
				url : "getScheduledVehicle.action",
				method : "post",
				data : "deviceid=" + deviceid,
				success : function(response) {
					obj = jQuery.parseJSON(response);
					// alert("obj"+obj);
					if(obj!=null){
					var length = obj["aaData"].length;
					// for (i = 0; i < length; i++) {
					// console.log('response["aaData"][i][i]'+obj["aaData"][i][0]);
					// if (response["aaData"][i][3] === deviceid) {
					$("#schNumber").html(obj["aaData"]);
					schNum = obj["aaData"];
					}
					// break;
					// }
					// }
					// if ($("#schNumber").html() == "") {
					// $("#schNumber").html("Not Mapped");
					// }
				},
				error : function() {
					console.log("error");
				}
			});
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
		shiftMarker : function() {
			if (markers.length > 0) {
				markers.shift();
			}
		},
		popMarker : function() {
			if (markers.length > 0) {
				markers.pop();
			}
		},
		changeHistoryIcon : function() {
			for (i = 0; i < markers.length; i++) {
				if (i === 0) {
					markers[i - 1].icon = "assets/images/bus-map-icon.png";
				} else {
					if (markers[i].vehicledirection > 0
							&& markers[i].vehicledirection <= 90) {
						markers[i].icon = "assets/images/bus_stop_right.png";
					} else if (markers[i].vehicledirection > 90
							&& markers[i].vehicledirection <= 180) {
						markers[i].icon = "assets/images/bus_stop_down.png";
					} else if (markers[i].vehicledirection > 180
							&& markers[i].vehicledirection <= 270) {
						markers[i].icon = "assets/images/bus_stop_left.png";
					} else if (markers[i].vehicledirection > 270
							&& markers[i].vehicledirection <= 360) {
						markers[i].icon = "assets/images/bus_stop_up.png";
					}
				}
			}
			markers[i].icon = "assets/images/BusIconRedFlag.png";

			setAllMap(map);
		},
		changeIcon : function() {
			for (i = 0; i < markers.length; i++) {
				if (i === 0) {
					markers[i].icon = "assets/images/BusIconRedFlag.png";
				} else if (i === markers.length -1){
					//markers[i].icon = "assets/images/bus.png";
					if(markers[i].vehicledirection>=0 && markers[i].vehicledirection<22.5){
						markers[i].icon= "assets/images/busRotation/busN.png";
					} else if(markers[i].vehicledirection>=22.5 && markers[i].vehicledirection<67.5){
						markers[i].icon = "assets/images/busRotation/busNE.png";
					} else if(markers[i].vehicledirection>=67.5 && markers[i].vehicledirection<112.5){
						markers[i].icon = "assets/images/busRotation/busE.png";
					} else if(markers[i].vehicledirection>=112.5 && markers[i].vehicledirection<157.5){
						markers[i].icon = "assets/images/busRotation/busSE.png";
					} else if(markers[i].vehicledirection>=157.5 && markers[i].vehicledirection<202.5){
						markers[i].icon = "assets/images/busRotation/busS.png";
					} else if(markers[i].vehicledirection>=202.5 && markers[i].vehicledirection<247.5){
						markers[i].icon = "assets/images/busRotation/busSW.png";
					} else if(markers[i].vehicledirection>=247.5 && markers[i].vehicledirection<292.5){
						markers[i].icon = "assets/images/busRotation/busW.png";
					} else if(markers[i].vehicledirection>=292.5 && markers[i].vehicledirection<337.5){
						markers[i].icon = "assets/images/busRotation/busNW.png";
					} else if(markers[i].vehicledirection>=337.5 && markers[i].vehicledirection<361){
						markers[i].icon = "assets/images/busRotation/busN.png";
					}
				}else{
					markers[i].icon = 'assets/images/red-marker-icon.png';
				}
			}
			
			setAllMap(map);
		},
		getCordinates : function() {
			$.ajax({
				url : "actgetCordinates.action",
				dataType : 'json',
				success : function(response) {

					if (response.status == 'OK') {
						places = response.places;

						// loop through places and add markers
						for (p in places) {
							tmpLatLng = new google.maps.LatLng(
									places[p].geo[0], places[p].geo[1]);

							// make and place map maker.
							var marker = new google.maps.Marker({
								map : map,
								position : tmpLatLng,
								title : places[p].name + "<br>"
										+ places[p].geo_name
							});

							bindInfoWindow(marker, map, infowindow, '<b>'
									+ places[p].name + "</b><br>"
									+ places[p].geo_name);
							markers.push(marker);
						}

					}
				}
			});
		}

	};

}();
function getDistanceFromLatLonInKm(lat1, lon1, lat2, lon2) {
	/*
	 * var lat1deg = convertDMS(lat1); var lat2deg = convertDMS(lat2); var
	 * long1deg = convertDMS(lon1); var long2deg = convertDMS(lon2);
	 */

	var R = 6371; // km
	var dLat1 = deg2rad(lat1);
	var dLat2 = deg2rad(lat2);
	var degtoraddifflat = deg2rad(lat2 - lat1);
	var degtoraddifflong = deg2rad(lon2 - lon1);

	var a = Math.sin(degtoraddifflat / 2) * Math.sin(degtoraddifflat / 2)
			+ Math.cos(dLat1) * Math.cos(dLat2)
			* Math.sin(degtoraddifflong / 2) * Math.sin(degtoraddifflong / 2);
	var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

	var d = (R * c) * 1000;
	totdist += d;
	var rounduptotdist = round(totdist, 2);
	//alert("totald"+rounduptotdist);
	$("#distance").html(parseFloat(rounduptotdist / 1000).toFixed(2) + "Km");
	return round(totdist, 2);
}

function getDistanceFromLatLonInKmonsubmit(lat1, lon1, lat2, lon2) {
	/*
	 * var lat1deg = convertDMS(lat1); var lat2deg = convertDMS(lat2); var
	 * long1deg = convertDMS(lon1); var long2deg = convertDMS(lon2);
	 */

	var R = 6371; // km
	var dLat1 = deg2rad(lat1);
	var dLat2 = deg2rad(lat2);
	var degtoraddifflat = deg2rad(lat2 - lat1);
	var degtoraddifflong = deg2rad(lon2 - lon1);

	var a = Math.sin(degtoraddifflat / 2) * Math.sin(degtoraddifflat / 2)
			+ Math.cos(dLat1) * Math.cos(dLat2)
			* Math.sin(degtoraddifflong / 2) * Math.sin(degtoraddifflong / 2);
	var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

	var d = (R * c);
	return round(d, 2);
}
function rad2deg(rad) {
	return rad * (180 / Math.PI);
}
/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
/* :: This function converts decimal degrees to radians : */
/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
function deg2rad(deg) {
	return deg * (Math.PI / 180);
}
function round(value, exp) {
	if (typeof exp === 'undefined' || +exp === 0)
		return Math.round(value);

	value = +value;
	exp = +exp;

	if (isNaN(value) || !(typeof exp === 'number' && exp % 1 === 0))
		return NaN;

	// Shift
	value = value.toString().split('e');
	value = Math
			.round(+(value[0] + 'e' + (value[1] ? (+value[1] + exp) : exp)));

	// Shift back
	value = value.toString().split('e');
	return +(value[0] + 'e' + (value[1] ? (+value[1] - exp) : -exp));
}
// 863071013980609
/*function addEncodedPath(encodedPath, bus_stop_name, lat, lng, i, length,
		latLong_end) {
	var path = google.maps.geometry.encoding.decodePath(encodedPath);
	var latLong = new google.maps.LatLng(lat, lng);

	imgicon = 'assets/images/blue_stop.png';

	if (i == 0) {
		imgicon = 'assets/images/red.png';
	}
	infowindow = new google.maps.InfoWindow();
	var marker = new google.maps.Marker(
			{
				map : map,
				position : latLong,
				icon : imgicon,
				latitude : lat,
				longitude : lng,
				info : '<div class="portlet-body form">'
						+ '<div class="form-body" >'
						+ '<div class="table-responsive" style="color:#000000;width:200px"><table class="table table-hover"><tr>'
						+ '<td align=""><B>Bus Stop:</B></th><td><div id="location" style="width:200px;height:50px">'
						+ bus_stop_name + '</div></td></tr></table>' + '</div>'
						+ '</div>' + '</div>'
			});// End of marker defination
	google.maps.event.addListener(marker, 'click', function() {
		if (infowindow) {
			infowindow.close();
			}
		infowindow.setContent(this.info);
		infowindow.open(map, this);
		// AllDevices.get_reverse_geocode(path[0].lat(), path[0].lng());
	});
	google.maps.event.addListener( map, 'click', function() { 
	    infowindow.open( null, null ); 
	} );
	busstopmarkers.push(marker);
	if (i == length) {
		imgicon = 'assets/images/green.png';
		marker = new google.maps.Marker(
				{
					map : map,
					position : latLong_end,
					icon : imgicon,
					latitude : lat,
					longitude : lng,
					info : '<div class="portlet-body form">'
							+ '<div class="form-body" >'
							+ '<div class="table-responsive" style="color:#000000;width:200px"><table class="table table-hover"><tr>'
							+ '<td align=""><B>Bus Stop:</B></th><td><div id="location" style="width:200px;height:50px">'
							+ bus_stop_name + '</div></td></tr></table>'
							+ '</div>' + '</div>' + '</div>'
				});// End of marker defination
		google.maps.event.addListener(marker, 'click', function() {
			if (infowindow) {
				infowindow.close();
				}
			infowindow.setContent(this.info);
			infowindow.open(map, this);
			// AllDevices.get_reverse_geocode(path[0].lat(), path[0].lng());
		});
		google.maps.event.addListener( map, 'click', function() { 
		    infowindow.open( null, null ); 
		} );
		busstopmarkers.push(marker);
	}
	// Set marker as center point of map
	map.setCenter(latLong);
	var polyline = new google.maps.Polyline({
		path : path,
		geodesic : true,
		strokeColor : "RED",
		strokeOpacity : 0.35,
		strokeWeight : 10,
		draggable : false
	});
	polyline.setMap(map);
	showBusMarkers();
	bounds.extend(latLong);
	map.fitBounds(bounds);
	return polyline;
}*/
function initializeMap(){
	mapOptions = {

			center : new google.maps.LatLng(12.9746, 77.5946),
			zoom : 15,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};
		map = new google.maps.Map(document.getElementById('route_map'), mapOptions);
		bounds = new google.maps.LatLngBounds();
		iconsetngs = {
			path : google.maps.SymbolPath.FORWARD_CLOSED_ARROW
		};
}
/*function drawScheduleRoute(device_id, route_id, start_point, end_point,
		start_time, trip_status, duty_date, id, end_time, schedule_name) {
	var selectedDate = document.getElementById("selecteddate").value;
	duty_date = selectedDate;
	$('#bus_stop_list').html('');
	$("#route_name_display").html(
			"Route Name: " + document.getElementById("route_name_" + id).value);

	$("#msg_schedule").html("Fetching Schedule Route !!!");
	var routeData = $.ajax({
		url : "getRoutefence.action?routeid=" + route_id + "&start_point="
				+ start_point + "&end_point=" + end_point,
		dataType : 'json',
		datatype : "post",
		global : false,
		async : false,
		success : function(response) {
			$("#msg_schedule").html("Scheduled Route Plotted !!!!");
			return response;
		}
	}).responseText;
	parseData = jQuery.parseJSON(routeData);
	var length = parseData["aaData"].length;

	if (length == 0) {
		$("#msg_schedule").html("Route Data Not Available !!!!");
	} else {
		for ( var i = 0; i < encodedPolyLine.length; i++) {
			encodedPolyLine[i].setMap(null);
		}
		encodedPolyLine = [];
		
		for ( var i = 0; i < length; i++) {
			//console.log(parseData["aaData"][i]["6"]);
			if(parseData["aaData"][i]["6"]!=2 && parseData["aaData"][i]["6"]!=13){
			var latLong_end = new google.maps.LatLng(
					parseData["aaData"][i]["4"], parseData["aaData"][i]["5"]);
			encodedPolyLine.push(addEncodedPath(parseData["aaData"][i]["0"],
					parseData["aaData"][i]["1"], parseData["aaData"][i]["2"],
					parseData["aaData"][i]["3"], i, length - 1, latLong_end));
			document.getElementById("bus_stop_list").style.display = '';
			// $('#bus_stop_list
			// tr').last().after('<tr><td>'+parseData["aaData"][i]["1"]+'</td></tr>');
			
			$("#bus_stop_list").html(
					$("#bus_stop_list").html() + "<br />" + (i + 1) + ". "
							+ parseData["aaData"][i]["1"]);
			}
		}
	}

	if (trip_status === 'Running') {
		map = new google.maps.Map(document.getElementById('route_map'),
				mapOptions);
		for (i = 0; i < markers.length; i++) {
			markers[i].setMap(null);
		}
		markers = [];
		for (i = 0; i < busstopmarkers.length; i++) {
			busstopmarkers[i].setMap(null);
		}
		busstopmarkers = [];
		for (i = 0; i < length; i++) {
			if(parseData["aaData"][i]["6"]!=2 && parseData["aaData"][i]["6"]!=13){
			var latLong_end = new google.maps.LatLng(
					parseData["aaData"][i]["4"], parseData["aaData"][i]["5"]);
			encodedPolyLine.push(addEncodedPath(parseData["aaData"][i]["0"],
					parseData["aaData"][i]["1"], parseData["aaData"][i]["2"],
					parseData["aaData"][i]["3"], i, length - 1, latLong_end));
		}
		}
		getCordinatesFromMarker(device_id, 0, duty_date + " " + start_time,
				duty_date + " " + end_time, 'ScheduleSubmit', false,
				schedule_name);
		
		// getCordinates(device_id, 0, '', '', '');
	}
	if (trip_status === 'Completed') {
		map = new google.maps.Map(document.getElementById('route_map'),
				mapOptions);
		for (i = 0; i < markers.length; i++) {
			markers[i].setMap(null);
		}
		markers = [];
		for (i = 0; i < busstopmarkers.length; i++) {
			busstopmarkers[i].setMap(null);
		}
		busstopmarkers = [];
		for (i = 0; i < length; i++) {
			if(parseData["aaData"][i]["6"]!=2 && parseData["aaData"][i]["6"]!=13){
			var latLong_end = new google.maps.LatLng(
					parseData["aaData"][i]["4"], parseData["aaData"][i]["5"]);
			encodedPolyLine.push(addEncodedPath(parseData["aaData"][i]["0"],
					parseData["aaData"][i]["1"], parseData["aaData"][i]["2"],
					parseData["aaData"][i]["3"], i, length - 1, latLong_end));
		}
		}
		getCordinatesFromMarker(device_id, 0, duty_date + " " + start_time,
				duty_date + " " + end_time, 'ScheduleSubmit', true,
				schedule_name);
		clearInterval(intervalID);
		return;
	}
	if (trip_status === 'NotPerformed') {
		if (intervalID != null) {
			clearInterval(intervalID);
		}
		map = new google.maps.Map(document.getElementById('route_map'),
				mapOptions);
		for (i = 0; i < markers.length; i++) {
			markers[i].setMap(null);
		}
		markers = [];
		for (i = 0; i < busstopmarkers.length; i++) {
			busstopmarkers[i].setMap(null);
		}
		busstopmarkers = [];
		for (i = 0; i < length; i++) {
			if(parseData["aaData"][i]["6"]!=2 && parseData["aaData"][i]["6"]!=13){
			var latLong_end = new google.maps.LatLng(
					parseData["aaData"][i]["4"], parseData["aaData"][i]["5"]);
			encodedPolyLine.push(addEncodedPath(parseData["aaData"][i]["0"],
					parseData["aaData"][i]["1"], parseData["aaData"][i]["2"],
					parseData["aaData"][i]["3"], i, length, latLong_end));
		}
		}
		getCordinatesFromMarker(device_id, 0, duty_date + " " + start_time,
				duty_date + " " + end_time, 'ScheduleSubmit', true,
				schedule_name);
		clearInterval(intervalID);
		return;
	}

}

*/// Shows any markers currently in the array.
var busstopdist=[];
var busstopid=[];
var busstopid1=[];
var ttldistroute=0;
var laststopid=0;
function drawScheduleRoute(device_id, route_id, start_point, end_point,start_time, trip_status, duty_date, id, end_time, schedule_name) {
	clearInterval(intervalID);
	deleteMarkers();
	deleteMarkersvehicle();
	initializeMap();
	flightPlanCoordinates = [];
	var selectedDate = document.getElementById("selecteddate").value;
	duty_date = selectedDate;
	$('#msg_schedule').html('Processing Please Wait');
	$("#route_name_display").html("Route Name: " + document.getElementById("route_name_" + id).value);
	var encodedPaths=[];
	$.ajax({
	    type        : 'POST',
	    data: {
	        routeid: route_id,
	        start_point: start_point,
	        end_point: end_point,
	        
	    },
	    url         :  'ShowRouteAjax.action',
	   
	    success: function(data){
	    	$('#msg_schedule').html('');
	        result=JSON.parse(data);
	        var bussopslist="";
	        points=result.routemap;
	        for(var i=0;i<result.routepointlen;i++){
	        	bussopslist=bussopslist+result.routepoint[i][1]+",";
	        	 busstopid1.push(result.routepoint[i][1]);
	        	if(i==result.routepointlen-1){
 					bussopslist=bussopslist.substring(0, bussopslist.length-1);
 			        getStopsForRoute(bussopslist,end_point);
 			        //$('#bus_stops').children( 'tr:not(:first)' ).remove();
 			        //$('#bus_stops tr').last().after('<tr><td id="b"></td><td id="a"></td><td id ="c"></td></tr>');
 			       laststopid=result.routepoint[i][1];
 			      } 
	        }
	        var calcdist=0;
	        for(var i=0;i<result.routemaplen;i++){
	        	busstopid.push(result.routemap[i][2]);
	        	calcdist=calcdist+result.routemap[i][6]+result.routemap[i][7];
	        	if(result.routemap[i][15]=="TRIMAX"){
	        		calcdist=calcdist+result.routemap[i][6]+result.routemap[i][7];
	        	}else{
	        	busstopdist.push(calcdist);
	        	
	        	}
	        }
	       
	        for(var i=0;i<result.routemaplen;i++){
	        	encodedPaths.push(result.routemap[i][13]);
	        }
	        addEncodedPaths( encodedPaths );
	       
	        var mypos = 0;var currspeed=0;
	       
	    	intervalID = setInterval(
	    			function() {
	    				var path=[];
	    				$
	    						.ajax({
	    							url : "actgetCordinates.action",
	    							data : "DEVICE_ID=" + device_id + "&ID=" + 0
	    									+ "&startdate=" + duty_date + " " + start_time + "&enddate="
	    									+ duty_date + " " + end_time + "&value=" + "",
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
	    											liveTrackingDeviceId = device_id;
	    										}
	    										if (liveTrackingDeviceId !== device_id) {
	    											isfirst = false;
	    											liveTrackingDeviceId = device_id;
	    											ID = 0;
	    										} else {
	    										}

	    										if (i === 0) {
	    											// G-Map image iconfor Current bus
	    											// stop
	    											
	    											ID = obj["aaData"][i][5];
	    											lastLat = obj["aaData"][i][0];
	    											lastLong = obj["aaData"][i][1];
	    										} else {
	    											// G-Map image icon for Intermediate
	    											// geo-points
	    											
	    										}
	    										if (!isfirst
	    												&& (objLength - i - 1) === 0) {
	    											// G-Map image icon for first
	    											// geo-points
	    											isfirst = true;
	    											
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

//	    																AllDevices
//	    																		.get_schedule_number(device_id);
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
//	    																AllDevices
//	    																		.get_schedule_number(device_id);
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
	    										
	    										latLong = new google.maps.LatLng(
	    	    										obj["aaData"][0][0],
	    	    										obj["aaData"][0][1]);
	    	    								map.setCenter(latLong);
	    	    								AllDevices.changeIcon();
	    	    								//var flightPlanCoordinates = [];
	    	    								flightPlanCoordinates.push(latLong);
	    	    								
//	    	    								for (mrk =0; mrk < marker.length; mrk++) {
//	    	    									flightPlanCoordinates.push(markers[mrk].position);
//	    	    								}
	    	    								mypos++;
	    	    								var flightPath =null;
	    	    								if(currspeed < 60){
	    	    									//console.log('Less');
	    	    								 flightPath = new google.maps.Polyline({
	    	    									path : flightPlanCoordinates,
	    	    									geodesic : true,
	    	    									strokeColor : '#009933',
	    	    									strokeOpacity : 1.0,
	    	    									strokeWeight : 8,
	    	    									/*icons : [ {
	    	    										repeat : '70px', // CHANGE THIS VALUE
	    	    										// TO CHANGE THE
	    	    										// DISTANCE BETWEEN
	    	    										// ARROWS
	    	    										icon : iconsetngs,
	    	    										offset : '100%'
	    	    									} ],*/
	    	    									draggable: false
	    	    									
	    	    								});
	    	    								// console.log(flightPath);
	    	    								}else{
	    	    									//console.log('Above');
	    	    									flightPath = new google.maps.Polyline({
	    	    										path : flightPlanCoordinates,
	    	    										geodesic : true,
	    	    										strokeColor : '#CC0000',
	    	    										strokeOpacity : 1.0,
	    	    										strokeWeight : 8,
	    	    										/*icons : [ {
	    	    											repeat : '70px', // CHANGE THIS VALUE
	    	    											// TO CHANGE THE
	    	    											// DISTANCE BETWEEN
	    	    											// ARROWS
	    	    											icon : iconsetngs,
	    	    											offset : '100%'
	    	    										} ],*/
	    	    										draggable: false
	    	    									});
	    	    									//console.log(flightPath);
	    	    								}
	    	    								flightPath.setMap(null);
	    	    								flightPath.setMap(map);
	    									}
	    									// End of for loop
	    								}
	    								
	    							}
	    						});
	    			}, 10000);
	        
	        	    },
	    error : function(xhr, errmsg) {
	    	alert("No values found..!!"+errmsg);
	    	}
	});
	
}
function addEncodedPaths( encodedPaths ) {
	//alert(encodedPaths);
   for( var i = 0, n = encodedPaths.length;  i < n;  i++ ) {
	   setTimeout(function(){}, 100);
	   addEncodedPath( encodedPaths[i] );
   }
}
function addEncodedPath( encodedPath ) {  
    var path = google.maps.geometry.encoding.decodePath( encodedPath );
    var polyline = new google.maps.Polyline({
       path: path,
       geodesic: true,
       strokeColor: '#FF0000',
       strokeOpacity: 2.0,
       strokeWeight: 2,
       icons : [ {
			repeat : '200px', // CHANGE THIS VALUE TO CHANGE THE
								// DISTANCE BETWEEN ARROWS
			icon : iconsetngs,
			offset : '100%'
		} ],
       draggable: false
   });
   polyline.setMap( map ); 
 
}

function getStopsForRoute(bussops,end_point){	
	 var locations;
	$.ajax({
	    type        : 'POST',
	    data: {
	        lat: '12',
	        lng: '77',
	        bussopslist:bussops,
	        
	    },
	    url         :  "GetRouteStopMapForRoute.action",
	   
	    success: function(data){
	        result=data;
	        
	        locations =result.split("@@@");
	        imgicon = 'assets/images/Bus-Icon.png';
			clearMarkers();
			var dloc;
			//map.setCenter(obj["aaData"][objLength-1][0],obj["aaData"][objLength-1][1]);
			//alert(locations.length);
			for (i = 0; i < locations.length; i++) {
				dloc=locations[i].split("|");
				if(laststopid==dloc[3]){
					$("#b").html(dloc[0]);
					endstopcord=dloc[1]+","+dloc[2];
				}
				if(dloc[14]!=2 && dloc[14]!=13){
					marker = new google.maps.Marker(
					{
						map : map,
						position: new google.maps.LatLng(dloc[1], dloc[2]),
						icon : imgicon,
						title: dloc[0]+" ("+$('<div/>').html(dloc[5]).text()+")"+" Towards:"+dloc[10],
						
					});
					
					
				}
				
			}
	    },
	    error : function(xhr, errmsg) {
	    	//alert("Session out error..Please login again");
	    	}
	});
}

function showMarkers() {

	setAllMap(map);
}

function showBusMarkers() {

	setAllBusMap(map);
}
function deleteMarkers() {
	clearMarkers();
	markers = [];
}
function setAllMap(map) {
	for ( var i = 0; i < markers.length; i++) {
		markers[i].setMap(map);
	}
}
function setAllBusMap(map) {
	for ( var i = 0; i < busstopmarkers.length; i++) {
		busstopmarkers[i].setMap(map);
	}
}
function setFlightMap(map) {
	for ( var i = 0; i < flightPatharr.length; i++) {
		flightPatharr[i].setMap(map);
	}

}
function clearMarkers() {
	setAllMap(null);
}


function deleteMarkersvehicle() {
	clearMarkers();
	vehiclemarker = [];

}

function getRefresh() {
	if ($("#refreshID").is(':checked')) {
		// plotVehicle();
	} else {
		clearInterval(intervalID);
	}
}