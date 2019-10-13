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
function initialize() {
	setTimeout(initialize, 1000);
}
$(function() {

	mapOptions = {
		zoom : 15,
		center : new google.maps.LatLng(12.97916, 77.56443),
		scaleControl : true
	};
	mapOptionsAll = {
			zoom : 10,
			center : new google.maps.LatLng(12.97916, 77.56443),
			scaleControl : true
		};
	map = new google.maps.Map(document.getElementById('gmap_basic'), mapOptions);
	bounds = new google.maps.LatLngBounds();
});
/**
 * Code for creation of timer
 * 
 */
function startTime() {
	timerInterval = setInterval(function() {
		a();
	}, 1000);
}
function a() {
	sec++;
	if (sec == 60) {
		sec = 0;
		min = min + 1;
	} else {
		min = min;
	}
	if (min == 60) {
		min = 0;
		hour += 1;
	}

	if (sec <= 9) {
		sec = "0" + sec;
	}
	$("#timer").html(
			((hour <= 9) ? "0" + hour : hour) + " : "
					+ ((min <= 9) ? "0" + min : min) + " : " + sec);

}
/**
 * Convert longitude/latitude decimal to degrees no seconds
 * 
 * @param lat,latitude
 *            degrees decimal
 * 
 */

function convertDMS(lat) {

	var latdegnumber;
	var addpart;
	var convertLat = Math.abs(lat);
	var LatDeg = Math.floor(convertLat);
	var LatSec = (Math.floor((convertLat - LatDeg) * 60));

	if (parseInt(LatSec) < 10) {

		latdegnumber = LatDeg + "0" + LatSec;
	} else {

		latdegnumber = LatDeg + "" + LatSec;
	}

	var latdegdecimalpart = Math.abs((convertLat - LatDeg) * 60)
			- Math.floor((convertLat - LatDeg) * 60);

	var expval = parseFloat(latdegnumber) + parseFloat(latdegdecimalpart);
	// alert(round(expval, 4));
	return round(expval, 4);
}
/**
 * Round up a floating point number
 * 
 * 
 */
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
/*
 * :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 * ***********
 */
/* :: This function Calculates Distance betweeen two latitudes and longititudes: */
/*
 * :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 * **********
 */
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

/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
/* :: This function converts radians to decimal degrees : */
/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
function rad2deg(rad) {
	return rad * (180 / Math.PI);
}
/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
/* :: This function converts decimal degrees to radians : */
/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
function deg2rad(deg) {
	return deg * (Math.PI / 180);
}
/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
/* :: This function displays the change in distance value in every 10 seconds */
/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */

function changeDistanceValue(DEVICE_ID, ID, startDate, endDate, value) {
	avgspeedcounter++;

	stlatitude = endlatitude;
	stlongitude = endlongitude;
//alert("hdfdf");
	/*$.ajax({
		url : "actgetCordinates.action",
		
		 * dataType : 'json', contentType : "application/json",
		 
		data : "DEVICE_ID=" + DEVICE_ID + "&ID=" + ID + "&startdate="
				+ startDate + "&enddate=" + endDate + "&value=" + value,
		method : "post",
		async : true,
		success : function(response) {
			var obj = jQuery.parseJSON(response);
			if (obj != null) {
				var objLength = obj["aaData"].length;
				for (i = 0; i < objLength; i++) {
					totspeed += parseInt(obj["aaData"][i][2]);
					var avgspeed = totspeed / avgspeedcounter;
					var time  =obj["aaData"][i][3].split(" ");
					
					$("#speed").html(obj["aaData"][i][2] + "Kmph");
					$("#updateTime").html(time[1]);
					*//** ***Ending of avg speed value calculation*** *//*
					AllDevices.get_schedule_number(DEVICE_ID);
					*//** **Starting of distance calculation*** *//*
					endlatitude = obj["aaData"][i][0];
					endlongitude = obj["aaData"][i][1];
					var dist = getDistanceFromLatLonInKm(stlatitude,
							stlongitude, endlatitude, endlongitude);
					*//** **Ending of distance calculation*** *//*
					// Setup the click event listeners: simply set the
					// map to Chicago.
				}
				// End of for loop
			}

		}

	});*/
}
/* :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
/* :: This function creates a division for displaying livetracking information :: */
/* :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
function HomeControl(controlDiv, map, DEVICE_ID, ID, startDate, endDate, value) {
	sec = 0;
	min = 0;
	hour = 0;
	stlatitude = 0;
	stlongitude = 0;
	endlatitude = 0;
	endlongitude = 0;
	totdist = 0;
	avgspeedcounter++;
	var iconsetngs = {
		path : google.maps.SymbolPath.FORWARD_CLOSED_ARROW
	};
	// Set CSS styles for the DIV containing the control
	// Setting padding to 5 px will offset the control
	// from the edge of the map.

	// stlatitude = endlatitude;
	// stlongitude = endlongitude;
//alert("Hid in actocror");
	$
			.ajax({
				url : "actgetCordinates.action",
				/*
				 * dataType : 'json', contentType : "application/json",
				 */
				data : "DEVICE_ID=" + DEVICE_ID + "&ID=" + ID + "&startdate="
						+ startDate + "&enddate=" + endDate + "&value=" + value,
				method : "post",
				async : true,
				success : function(response) {

					// Response Check
					/**
					 * ***START CODE FOR MARKER INFO WINDOW
					 * DISPLAY****************
					 */
					infowindow = new google.maps.InfoWindow();
					poly = new google.maps.Polyline({
						strokeColor : '#009933',
						strokeOpacity : 1.0,
						strokeWeight : 2,
						icons : [ {
							repeat : '70px', // CHANGE THIS VALUE
							// TO CHANGE THE
							// DISTANCE BETWEEN
							// ARROWS
							icon : iconsetngs,
							offset : '100%'
						} ]
					});
					poly.setMap(map);
					var path = poly.getPath();
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
								imgicon = 'assets/images/bus-map-icon.png';
								ID = obj["aaData"][i][5];
								lastLat = obj["aaData"][i][0];
								lastLong = obj["aaData"][i][1];
							} else {
								// G-Map image icon for Intermediate
								// geo-points
								imgicon = google.maps.SymbolPath.FORWARD_CLOSED_ARROW// 'assets/images/red-marker-icon.png';
							}
							if (!isfirst && (objLength - i - 1) === 0) {
								// G-Map image icon for first
								// geo-points
								isfirst = true;
								imgicon = 'assets/images/BusIconRedFlag.png';
							}
							var latLong = new google.maps.LatLng(
									obj["aaData"][i][0], obj["aaData"][i][1]);
							var time = obj["aaData"][i][3].split(" ");
							
							if (obj["aaData"][i][2] > 5) {
								bus_icon = 'assets/images/bus-map-icon.png';
							} else if ((obj["aaData"][i][2] <= 5)
									&& (obj["aaData"][i][2] > 0)) {
								bus_icon = 'assets/images/Icon-Bus-Orange.png';
							} else if ((obj["aaData"][i][2] == "0.00")) {
								bus_icon = 'assets/images/Icon-Bus-Blue.png';
							}
							if ((obj["aaData"][i][8] == "0")) {
								bus_icon = 'assets/images/grey.png';
							}
							if ((obj["aaData"][i][9] == "1")) {
								bus_icon = 'assets/images/fourbyfour.png';
							}
							if ((obj["aaData"][i][11] == "A")) {
//								bus_icon = 'assets/images/Icon-Bus-Red.png';
								bus_icon = 'assets/images/Icon-Bus-Red.gif';
							}
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
												+ '<tr><td  align=""><B>Schedule No:</B></th><td>'
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
												+ '<tr><td align=""><B>Current Location:</B></th><td><div id="location"></div></td></tr>'
												+ '<tr>'
												+ ' <td  style="text-align:center"><a data-toggle="modal" class="btn blue" role="button" href="#mymodal1" '
												+ "onclick=\"javascript:getVehData('"
												+ obj["aaData"][i][4]
												+ "','"
												+ obj["aaData"][i][6]
												+ "','"
												+ time[0] + " " + time[1]
												+ "')\"/>"
												+ "Change Vehicle Status</a></td>"
												+ '</tr></table>'
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
														infowindow.close();
													}
													infowindow
															.setContent(this.info);
													infowindow.open(map, this);
													AllDevices
															.get_reverse_geocode(
																	this.latitude,
																	this.longitude);

//													AllDevices
//															.get_schedule_number(DEVICE_ID);
												}
											})(marker, j));
							j++;
							// Push if Last Geocode Same...
							if (obj["aaData"][i][0] != '0.00000000') {
								markers.push(marker);

								// alert(latLong);
								path.push(latLong);
							}
							/**
							 * ***END CODE FOR MARKER INFO WINDOW
							 * DISPLAY****************
							 */

							/**
							 * ********START CODE FOR TOOLTIP VEHICLE
							 * INFORMATION***********
							 */
							controlDiv.style.padding = '5px';
							var device_Id = obj["aaData"][i][3].split(" ");

							// Set CSS for the control border.
							var controlUI = document.createElement('div');
							controlUI.style.backgroundColor = 'white';
							controlUI.style.borderStyle = 'solid';
							controlUI.style.borderWidth = '1px';
							controlUI.style.cursor = 'pointer';
							controlUI.style.textAlign = 'left';
							controlUI.title = 'Vehicle Details';
							controlDiv.appendChild(controlUI);

							// Set CSS for the control interior.
							var controlText = document.createElement('div');
							controlText.style.fontFamily = 'Arial,sans-serif';
							controlText.style.fontSize = '12px';
							controlText.style.paddingLeft = '4px';
							controlText.style.paddingRight = '4px';
							AllDevices.get_schedule_number(DEVICE_ID);

							var dist = getDistanceFromLatLonInKm(stlatitude,
									stlongitude, endlatitude, endlongitude);
							totspeed += parseInt(obj["aaData"][i][2]);

							var avgspeed = totspeed / avgspeedcounter;
							var roundavgspeed = round(avgspeed, 2);
							endlatitude = obj["aaData"][i][0];
							endlongitude = obj["aaData"][i][1];
							controlText.innerHTML = '<table cellspacing="3" cellpadding="3">'
									+ '<tr><th colspan="2" align="center">Vehicle Details</th></tr>'
									+ '<tr id="devidtr"><th id="deviceid">Vehicle</th><td>'
									+ obj["aaData"][i][6]
									+ '</td></tr>'

									+ '<tr><td  align=""><B>Schedule No:</B></th><td>'

									+ obj["aaData"][i][15]
									+ '</td></tr>'
									+ '<tr id="speedtr"><th>Speed</th><td id="speed">'
									+ obj["aaData"][i][2]
									+ 'Kmph</td></tr>'
									+ '<tr><th>Distance</th><td id="distance">'
									+ round(dist, 2)
									/ 1000
									+ 'Km</td></tr>'
									+ '<tr id="timetr"><th>Running Time</th><td id="timer"></td></tr>'
									+ '<tr id="updateTimetr"><th>Last Updated Time</th><td id="updateTime">00:00:00</td></tr>'
									+ '</table>';
							controlUI.appendChild(controlText);

							// Setup the click event listeners: simply set the
							// map to Chicago.
							google.maps.event.addDomListener(controlUI,
									'click', function() {

									});
							if (timerInterval == null) {
								startTime();
							}
							/**
							 * ********END CODE FOR TOOLTIP VEHICLE
							 * INFORMATION***********
							 */

							/** ****START OF FLIGHT CO-ORDINATES DRAW********** */
							latLong = new google.maps.LatLng(
									obj["aaData"][0][0], obj["aaData"][0][1]);
							map.setCenter(latLong);
							AllDevices.changeIcon();

							var flightPlanCoordinates = [];
							for (mrk = 0; mrk < markers.length; mrk++) {
								flightPlanCoordinates
										.push(markers[mrk].position);
							}
							var flightPath = new google.maps.Polyline({
								path : flightPlanCoordinates,
								geodesic : true,
								strokeColor : '#009933',
								strokeOpacity : 1.0,
								strokeWeight : 2,
								icons : [ {
									repeat : '70px', // CHANGE THIS VALUE
									// TO CHANGE THE
									// DISTANCE BETWEEN
									// ARROWS
									icon : iconsetngs,
									offset : '100%'
								} ]
							});
							flightPath.setMap(null);
							flightPath.setMap(map);

							/** ****END OF FLIGHT CO-ORDINATES DRAW********** */
						}
						// End of for loop
					}

				}
			});

}
/* :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
/*
 * :: This function creates a division for displaying livetracking information
 * ON SUBMIT::
 */
/* :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
function HomeControlOnSubmit(controlDiv, map, DEVICE_ID, ID, startDate,
		endDate, value, totdistonsubmit, vehicleno) {
	// var totdistkm=totdistonsubmit/1000;
	/** ********START CODE FOR TOOLTIP VEHICLE INFORMATION ON SUBMIT*********** */
	controlDiv.style.padding = '5px';

	// Set CSS for the control border.
	var controlUI = document.createElement('div');
	controlUI.style.backgroundColor = 'white';
	controlUI.style.borderStyle = 'solid';
	controlUI.style.borderWidth = '1px';
	controlUI.style.cursor = 'pointer';
	controlUI.style.textAlign = 'left';
	controlUI.title = 'Vehicle Details';
	controlDiv.appendChild(controlUI);

	// Set CSS for the control interior.
	var controlText = document.createElement('div');

	controlText.style.fontFamily = 'Arial,sans-serif';
	controlText.style.fontSize = '12px';
	controlText.style.paddingLeft = '4px';
	controlText.style.paddingRight = '4px';

	controlText.innerHTML = '<table cellspacing="3" cellpadding="3">'
			+ '<tr><th colspan="2" align="center">Vehicle Details</th></tr>'
			+ '<tr><th>Vehicle No:</th><td>'
			+ vehicleno
			+ '</td></tr>'
			+ '<tr><th>Total Distance Travelled:</th><td id="distanceonsubmit">'
			+ round(totdistonsubmit, 2) + 'Kms'+'</td></tr>'
			+ '<tr id="playbackspeedtr"><th>Vehicle Speed:</th><td id="speedofvehicle">'
			+ '</td></tr>'
			+ '<tr id="playbackvehicletime"><th>Time:</th><td id="timeofvehicle">'
			+ '</td></tr>'
			+ '</table>';
	controlUI.appendChild(controlText);

	// Setup the click event listeners: simply set the
	// map to Chicago.
	google.maps.event.addDomListener(controlUI, 'click', function() {

	});

	/** ********END CODE FOR TOOLTIP VEHICLE INFORMATION ON SUBMIT*********** */
}
/*
 * :: This function Calculates Distance betweeen two latitudes and
 * longititudes:on submit
 */
/*
 * :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 * **********
 */
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

// 863071013980609
var intervalID = null;var schCounter =0;
var AllDevices = function() {
	schNum = "";
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
			if(schCounter == 0){
				schCounter++;
//			$.ajax({
//				url : "getScheduledVehicle.action",
//				method : "post",
//				data : "deviceid=" + deviceid,
//				success : function(response) {
//					obj = jQuery.parseJSON(response);
//					if(obj!=null){
//					var length = obj["aaData"].length;
//					$('#schNumber').html(obj["aaData"]);
//					schNum = obj["aaData"];
//					schCounter =0;
//					}
//					
//				},
//				error : function() {
//					console.log("error");
//				}
//			});
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
		changeIconHistory : function() {
			for (i = 0; i < markers.length; i++) {
				if (i === 0) {
					markers[i].icon = "assets/images/bus-map-icon.png";
				} else {

				}
			}
			markers[i - 1].icon = "assets/images/BusIconRedFlag.png";
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

function setAllMap(map) { 
	for ( var i = 0; i < markers.length; i++) {
		markers[i].setMap(map);
	}
	for ( var i = 0; i < vehiclemarker.length; i++) {
		vehiclemarker[i].setMap(map);
		latLong = new google.maps.LatLng(vehiclemarker[i].latitude,
				vehiclemarker[i].longitude);
		// bounds.extend(latLong);
		// map.fitBounds(bounds);
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
function deleteMarkersvehicle() {
	clearMarkers();
	vehiclemarker = [];

}
function deletePolyLine() {
	clearMarkers();
	flightPlanCoordinates = [];
}
function getDashBoardCordinate(DEVICE_ID, ID) {
	function initializeMap() {
		setTimeout(initialize123, 1000);
	}
	$("#vehicleno").html("Vehicle Number:" + DEVICE_ID(ID));
	getCordinates(DEVICE_ID, 0, '', '', '');
}

function getCordinatesFromMarker(DEVICE_ID, ID, startdate, enddate, value) {	
	//
	// drawScheduleRoute(1234);
	if (tmeout != null && tmeout.length>0) {
//		alert(tmeout.length);
		for (var i =0; i < tmeout.length; i++){
			clearTimeout(tmeout[i]);
		}
	}
	if (intervalID != null) {
		clearInterval(intervalID);
	}deleteMarkers();
	deleteMarkersvehicle();
	stlatitudeonsubmit = 0;
	stlongitudeonsubmit = 0;
	endlatitudeonsubmit = 0;
	endlongitudeonsubmit = 0;
	var totdistonsubmit = 0;
	initializeMap();
	map = new google.maps.Map(document.getElementById('gmap_basic'), mapOptions);
	deleteMarkersvehicle();
	var mypos1=0;
	$('#loadingmessage').show();
	console.log("------3----"+$('#playbacksubmit').hide());
	$
			.ajax({
				url : "actgetCordinates.action",
				data : "DEVICE_ID=" + DEVICE_ID + "&ID=" + ID + "&startdate="
						+ startdate + "&enddate=" + enddate + "&value=" + value,
				method : "post",
				success : function(response) {
					$('#loadingmessage').hide(); 
					console.log("------4-----"+$('#playbacksubmit').show());
					infowindow = new google.maps.InfoWindow();
					var path = [];
					var info = "";
					var j, i = 0;
					var vehicleno = "";
					var vehiclespeed = "";
					// Parsing response
					var obj = jQuery.parseJSON(response);
					if (obj != null) {
						var objLength = obj["aaData"].length;
						for (i = 0; i < objLength; i++) {
							obj["aaData"][i][0];
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

								imgicon = 'assets/images/bus-map-icon.png';
								ID = obj["aaData"][i][5];
								lastLat = obj["aaData"][i][0];
								lastLong = obj["aaData"][i][1];
							} else {
								// G-Map image icon for Intermediate
								// geo-points
								imgicon = 'assets/images/red-marker-icon.png';
							}
							if (!isfirst && (objLength - i - 1) === 0) {
								// G-Map image icon for first
								// geo-points
								isfirst = true;
								imgicon = 'assets/images/BusIconRedFlag.png';
							}
							var latLong = new google.maps.LatLng(
									obj["aaData"][i][0], obj["aaData"][i][1]);
							var time = obj["aaData"][i][3].split(" ");
							var timeServer = obj["aaData"][i][18].split(" ");
							vehicleno = obj["aaData"][i][6];
							vehiclespeed = obj["aaData"][i][2];
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
												// + obj["aaData"][i][5]
												// + '</td></tr>'
												+ '<tr><td  align=""><B>Packet Header</B></th><td>'
												+ obj["aaData"][i][17]
												+ '</td></tr>'
												+ '<tr><td  align=""><B>IGN. Status</B></th><td>'
												+ obj["aaData"][i][19]
												+ '</td></tr>'
												+ '<tr><td  align=""><B>Server Time</B></th><td>'
												+ timeServer[0]
												+ " "
												+ timeServer[1]
												+ '</td></tr>'
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
												+ '<tr><td  align=""><B>Schedule No:</B></th><td>'
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
												// + obj["aaData"][i][0]
												// + '</td></tr>'
												// + '<tr><td
												// align=""><B>Longitude:</B></th><td>'
												// + obj["aaData"][i][1]
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
														infowindow.close();
													}
													infowindow
															.setContent(this.info);
													infowindow.open(map, this);
													AllDevices
															.get_reverse_geocode(
																	this.latitude,
																	this.longitude);

													AllDevices
															.get_schedule_number(DEVICE_ID);
												}
											})(marker, j));
							google.maps.event.addListener(map, 'click',
									function() {
										infowindow.open(null, null);
									});
							j++;
							// Push if Last Geocode Same...
							if (obj["aaData"][i][0] != '0.00000000') {
								markers.push(marker);
//								path.push(latLong);
							}
							path.push(latLong);
							var dist;
							if (i === 0) {

								dist = 0;
								endlatitudeonsubmit = obj["aaData"][i][0];
								endlongitudeonsubmit = obj["aaData"][i][1];
							} else {

								endlatitudeonsubmit = obj["aaData"][i][0];
								endlongitudeonsubmit = obj["aaData"][i][1];
								dist = getDistanceFromLatLonInKmonsubmit(
										stlatitudeonsubmit,
										stlongitudeonsubmit,
										endlatitudeonsubmit,
										endlongitudeonsubmit);
								if(obj["aaData"][i][2] < 70){
									var PathStyle = new google.maps.Polyline({
										path: [path[i],path[i-1]],
						            	strokeColor: '#009933',
						            	strokeOpacity: 1.0,
						            	strokeWeight: 8,
						            	icons : [ {
											repeat : '70px', // CHANGE THIS VALUE
											// TO CHANGE THE
											// DISTANCE BETWEEN
											// ARROWS
											icon : google.maps.SymbolPath.FORWARD_CLOSED_ARROW,
											offset : '100%'
										} ],
										draggable: false,
						            	map: map
									});									
								} else {
									var PathStyle = new google.maps.Polyline({
										path: [path[i],path[i-1]],
						            	strokeColor: '#CC0000',
						            	strokeOpacity: 1.0,
						            	strokeWeight: 8,
						            	icons : [ {
											repeat : '70px', // CHANGE THIS VALUE
											// TO CHANGE THE
											// DISTANCE BETWEEN
											// ARROWS
											icon : google.maps.SymbolPath.FORWARD_CLOSED_ARROW,
											offset : '100%'
										} ],
										draggable: false,
						            	map: map
									});
								}
								PathStyle.setMap(map);
							}

							stlatitudeonsubmit = endlatitudeonsubmit;
							stlongitudeonsubmit = endlongitudeonsubmit;
							
//							 totdistonsubmit+=dist;
                              // console.log("toatdis"+(obj["aaData"][0][13] - obj["aaData"][objLength - 1][13]) * 1.6);
							//totdistonsubmit = Math.round((obj["aaData"][0][13] - obj["aaData"][objLength - 1][13]) / 1000);							
							latLong = new google.maps.LatLng(obj["aaData"][0][0],
									obj["aaData"][0][1]);

							map.setCenter(latLong);
						}
						// End of for loop
					}
					/** ***CO0DE FOR TOOL TIP ON SUBMIT****** */
					var distinkm = totdistonsubmit;

					$('#vehicledetails').remove();
					var homeControlDivOnSubmit = document.createElement('div');
					homeControlDivOnSubmit.id = 'vehicledetailsonsubmit';
					var homeControlonSubmit = new HomeControlOnSubmit(
							homeControlDivOnSubmit, map, DEVICE_ID, ID,
							startdate, enddate, value, obj["aaData"][0][20], vehicleno);
					homeControlDivOnSubmit.index = 1;
					map.controls[google.maps.ControlPosition.RIGHT_TOP]
							.push(homeControlDivOnSubmit);
					//document.getElementById("playbackspeedtr").style.display = 'none';
					//document.getElementById("playbackvehicletime").style.display = 'none';
					/** ***END OF TOOL TIP ON SUBMIT******* */
					

					//AllDevices.changeIconHistory();
					

				}
			});

}

// Calling Methods to Plot on GMAP....
function getCordinates(DEVICE_ID, ID, startDate, endDate, value) {
	if ($plotvehicleRequest != null){ 
		$plotvehicleRequest.abort();
		$plotvehicleRequest = null;
	}
	if ($coordinateRequest != null){ 
		$coordinateRequest.abort();
		$coordinateRequest = null;
	}
	console.log("----1-----"+$('#playbacksubmit').hide());
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
	if (startDate != '') {
		initializeMap();
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
	if (value === 'Submit') {
		 
		getCordinatesFromMarker(DEVICE_ID, ID, startDate, endDate,	value);
		clearInterval(intervalID);
		return;
	}
	if (startDate == undefined || endDate == undefined) {
		startDate = $('#startdate').val();
		endDate = $('#enddate').val();
	}

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
				console.log("-----2--------"+$('#playbacksubmit').show());
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


function getCordinatesLocation(DEVICE_ID, ID, startDate, endDate, value) {
	if ($plotvehicleRequest != null){ 
		$plotvehicleRequest.abort();
		$plotvehicleRequest = null;
	}
	if ($coordinateRequest != null){ 
		$coordinateRequest.abort();
		$coordinateRequest = null;
	}
	console.log("----1-----"+$('#playbacksubmit').hide());
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
	if (startDate != '') {
		initializeMap();
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
	if (value === 'Submit') {
		 
		getCordinatesFromMarker(DEVICE_ID, ID, startDate, endDate,	value);
		clearInterval(intervalID);
		return;
	}
	if (startDate == undefined || endDate == undefined) {
		startDate = $('#startdate').val();
		endDate = $('#enddate').val();
	}

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
				console.log("-----2--------"+$('#playbacksubmit').show());
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
															+ '<div class="table-responsive" style="color:#000000"><table class="table table-hover">'
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

//-----------------for playback--------------
function getCordinatespb(DEVICE_ID, ID, startDate, endDate, value, timeframe) {
	
	map = new google.maps.Map(document.getElementById('gmap_basic'), mapOptions);
	deleteMarkersvehicle();
	if (intervalID != null) {
		clearInterval(intervalID);
	}
	if (startDate != '') {
		initializeMap();
	}
	deleteMarkers();
	deleteMarkersvehicle();
	isfirst = false;
	if (value === 'Submit') {
		getCordinatesFromMarker_playback(DEVICE_ID, ID, startDate, endDate,	value, timeframe);
		clearInterval(intervalID);
		return;
	}
	if (startDate == undefined || endDate == undefined) {
		startDate = $('#startdate').val();
		endDate = $('#enddate').val();
	}

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
	intervalID = setInterval(
			function() {
				if (value === 'Submit') {

				} else {
					changeDistanceValue(DEVICE_ID, ID, startDate, endDate,
							value);
				}
				var iconsetngs = {
					path : google.maps.SymbolPath.FORWARD_CLOSED_ARROW
				};
				$
						.ajax({
							url : "actgetCordinates.action",
							data : "DEVICE_ID=" + DEVICE_ID + "&ID=" + ID
									+ "&startdate=" + startDate + "&enddate="
									+ endDate + "&value=" + value,
							method : "post",
							async : true,
							success : function(response) {
								// Response Check

								infowindow = new google.maps.InfoWindow();
								poly = new google.maps.Polyline({
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
									} ]
								});
								poly.setMap(map);
								var path = poly.getPath();
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
											imgicon = 'assets/images/bus-map-icon.png';
											ID = obj["aaData"][i][5];
											lastLat = obj["aaData"][i][0];
											lastLong = obj["aaData"][i][1];
										} else {
											// G-Map image icon for Intermediate
											// geo-points
											imgicon = google.maps.SymbolPath.FORWARD_CLOSED_ARROW// 'assets/images/red-marker-icon.png';
										}
										if (!isfirst
												&& (objLength - i - 1) === 0) {
											// G-Map image icon for first
											// geo-points
											isfirst = true;
											imgicon = 'assets/images/BusIconRedFlag.png';
										}
										var latLong = new google.maps.LatLng(
												obj["aaData"][i][0],
												obj["aaData"][i][1]);
										var time = obj["aaData"][i][3]
												.split(" ");

										var device_id = obj["aaData"][i][4];
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
															+ ''
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

																AllDevices
																		.get_schedule_number(DEVICE_ID);
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

																AllDevices
																		.get_schedule_number(DEVICE_ID);
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

											// alert(latLong);
											path.push(latLong);
										}

									}
									// End of for loop
								}
								latLong = new google.maps.LatLng(
										obj["aaData"][0][0],
										obj["aaData"][0][1]);
								map.setCenter(latLong);
								AllDevices.changeIcon();

								var flightPlanCoordinates = [];
								for (mrk = 0; mrk < markers.length; mrk++) {
									flightPlanCoordinates
											.push(markers[mrk].position);
								}
								var flightPath = new google.maps.Polyline({
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
									} ]
								});
								flightPath.setMap(null);
								flightPath.setMap(map);
							}
						});
			}, 10000);

}
//------------endplayback--------------------
// url : "actgetCordinates.action",
// For All Vehicle..

function plotVehicle() {
	if (intervalID != null) {
		clearInterval(intervalID);
	}
	map = new google.maps.Map(document.getElementById('gmap_basic'), mapOptionsAll);
	deleteMarkersvehicle();
	// callActCordinate();
	plotVehilcesOnMap();
	intervalID = setInterval(function() {
		plotVehilcesOnMap();
	}, 40000);

}
function getjeepVehicle(){
	//if ($("#jeep").is(':checked')) {
		// plotVehicle();
	
	if (intervalID != null) {
		clearInterval(intervalID);
	}
	map = new google.maps.Map(document.getElementById('gmap_basic'), mapOptionsAll);
	
	deleteMarkersvehicle();
	plotJeepVehilcesOnMap();
	intervalID = setInterval(function() {
		plotJeepVehilcesOnMap();
	}, 20000);
//	} else {
//		clearInterval(intervalID);
//	}
}
function plotJeepVehilcesOnMap() {
	map = new google.maps.Map(document.getElementById('gmap_basic'), mapOptionsAll);
	var depotID=405;
	$('#loadingmessage').show(); 
	$plotvehicleRequest = $.ajax({
				url : "actgetCordinates.action",
				data : "DEVICE_ID=ALL&depotID="+depotID,
				method : "post",
				success : function(response) {
					for ( var i = 0; i < vehiclemarker.length; i++) {
						vehiclemarker[i].setMap(null);
						
					}
					$('#loadingmessage').hide();
					vehiclemarker = [];
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
							var bus_icon = "assets/images/icon-bus-orange.png";
							if (obj["aaData"][i][2] > 5) {
								bus_icon = 'assets/images/bus-map-icon.png';
							} else if ((obj["aaData"][i][2] <= 5)
									&& (obj["aaData"][i][2] > 0)) {
								bus_icon = 'assets/images/Icon-Bus-Orange.png';
							} else if ((obj["aaData"][i][2] == "0.00")) {
								bus_icon = 'assets/images/Icon-Bus-Blue.png';
							}
							if ((obj["aaData"][i][8] == "0")) {
								bus_icon = 'assets/images/grey.png';
							}
							if ((obj["aaData"][i][9] == "1")) {
								bus_icon = 'assets/images/fourbyfour.png';
							}
							if ((obj["aaData"][i][11] == "A")) {
//								bus_icon = 'assets/images/Icon-Bus-Red.png';
								bus_icon = 'assets/images/Icon-Bus-Red.gif';
							}
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
										latitude : obj["aaData"][i][0],
										longitude : obj["aaData"][i][1],
										deviceid : obj["aaData"][i][4],
										vehicledirection : obj["aaData"][i][8],
										schno : '<div class="portlet-body form">'
												+ '<div class="form-body">'
												+ '<div class="table-responsive" style="color:#000000;width: 200px;" ><table class="table table-hover"><tr>'
												+ '<tr><td align=""><B>Vehicle</B></th><td>'
												+ obj["aaData"][i][6]
												+ '</td></tr>'
												+ '<tr><td  align=""><B>Schedule</B></th><td id="schNumber">'
												+ obj["aaData"][i][10]
												+ '</td></tr></table>'
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
												+ time[0]
												+ " "
												+ time[1]
												+ '</td></tr>'
												+ '<tr><td align=""><B>Vehicle Number:</B></th><td>'
												+ obj["aaData"][i][6]
												+ '</td></tr>'
												+ '<tr><td  align=""><B>Depot Name:</B></th><td>'
												+ obj["aaData"][i][12]
												+ '</td></tr>'
												+ '<tr><td  align=""><B>Schedule No:</B></th><td id="schNumber">'
												+ ''
												+ '</td></tr>'
												+ '<tr><td align=""><B>Device Number:</B></th><td>'
												+ obj["aaData"][i][4]
												+ '</td></tr>'
												+ ''
												+ '</td></tr>'
												+ '<tr><td align=""><B>Phone Number:</B></th><td>'
												+ obj["aaData"][i][7]
												+ '</td></tr>'
												+ '<tr><td align=""><B>Speed:</B></th><td>'
												+ obj["aaData"][i][2]
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
												+ '<a data-toggle="modal" class="btn blue" role="button" '
												+ "onclick=\"javascript:getCordinates('"
												+ obj["aaData"][i][4]
												+ "',00,'all')\"/>"
												+ "Track Online </a></td>"
												+ ' <td><a data-toggle="modal" class="btn yellow" role="button" onclick="javascript:AllDevices.get_reverse_geocode('
												+ obj["aaData"][i][0]
												+ ','
												+ obj["aaData"][i][1]
												+ ')">Location</a></td>'
												+ ' <td><a data-toggle="modal" class="btn blue" role="button" href="#mymodal1" '
												+ "onclick=\"javascript:getVehData('"
												+ obj["aaData"][i][4]
												+ "','"
												+ obj["aaData"][i][6]
												+ "','"
												+ time[0] + " " + time[1]
												+ "')\"/>"
												+ "Change Vehicle Status</a></td>"
												+ '</tr></table>'
												+ '</div>'
												+ '</div>'
												+ '</div>'
									});// End of marker defination
							// Setting Info window
							google.maps.event
									.addListener(
											marker,
											'click',
											(function(marker, j) {
												return function() {
													infowindow.setContent(this.info);
													infowindow.open(map, this);
													AllDevices.get_reverse_geocode(this.latitude,this.longitude);
													AllDevices.get_schedule_number(this.deviceid);
												}
											})(marker, j));
							google.maps.event
									.addListener(
											marker,
											'mouseover',
											(function(marker, j) {
												return function() {
													infowindow
															.setContent(this.schno);
													infowindow.open(map, this);
													AllDevices
															.get_schedule_number(this.deviceid);
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
							if (obj["aaData"][i][0] != '0.00000000') {

								vehiclemarker.push(marker);
							}
							// Setting marker
							// on map
						} // End of for loop
						setAllMap(map);
					}
					// map.setCenter(latLong);
				}
			});
	

}



function plotVehilcesOnMap() {
	map = new google.maps.Map(document.getElementById('gmap_basic'), mapOptionsAll);
	var depotID=$('#depotlist option:selected').val();
	$('#loadingmessage').show(); 
	$plotvehicleRequest = $.ajax({
				url : "actgetCordinates.action",
				data : "DEVICE_ID=ALL&depotID="+depotID,
				method : "post",
				success : function(response) {
					for ( var i = 0; i < vehiclemarker.length; i++) {
						vehiclemarker[i].setMap(null);

					}
					$('#loadingmessage').hide();
					vehiclemarker = [];
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
							var bus_icon = "assets/images/icon-bus-orange.png";
							if (obj["aaData"][i][2] > 5) {
								bus_icon = 'assets/images/bus-map-icon.png';
							} else if ((obj["aaData"][i][2] <= 5)
									&& (obj["aaData"][i][2] > 0)) {
								bus_icon = 'assets/images/Icon-Bus-Orange.png';
							} else if ((obj["aaData"][i][2] == "0.00")) {
								bus_icon = 'assets/images/Icon-Bus-Blue.png';
							}
							if ((obj["aaData"][i][8] == "0")) {
								bus_icon = 'assets/images/grey.png';
							}
							if ((obj["aaData"][i][9] == "1")) {
								bus_icon = 'assets/images/fourbyfour.png';
							}
							if ((obj["aaData"][i][11] == "A")) {
//								bus_icon = 'assets/images/Icon-Bus-Red.png';
								bus_icon = 'assets/images/Icon-Bus-Red.gif';
							}
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
										latitude : obj["aaData"][i][0],
										longitude : obj["aaData"][i][1],
										deviceid : obj["aaData"][i][4],
										vehicledirection : obj["aaData"][i][8],
										schno : '<div class="portlet-body form">'
												+ '<div class="form-body">'
												+ '<div class="table-responsive" style="color:#000000;width: 200px;" ><table class="table table-hover"><tr>'
												+ '<tr><td align=""><B>Vehicle</B></th><td>'
												+ obj["aaData"][i][6]
												+ '</td></tr>'
												+ '<tr><td  align=""><B>Schedule</B></th><td id="schNumber">'
												+ obj["aaData"][i][10]
												+ '</td></tr></table>'
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
												+ time[0]
												+ " "
												+ time[1]
												+ '</td></tr>'
												+ '<tr><td align=""><B>Vehicle Number:</B></th><td>'
												+ obj["aaData"][i][6]
												+ '</td></tr>'
												+ '<tr><td  align=""><B>Depot Name:</B></th><td>'
												+ obj["aaData"][i][12]
												+ '</td></tr>'
												+ '<tr><td  align=""><B>Schedule No:</B></th><td id="schNumber">'
												+ obj["aaData"][i][10]												+ '</td></tr>'
												+ '<tr><td align=""><B>Device Number:</B></th><td>'
												+ obj["aaData"][i][4]
												+ '</td></tr>'
												+ ''
												+ '</td></tr>'
												+ '<tr><td align=""><B>Phone Number:</B></th><td>'
												+ obj["aaData"][i][7]
												+ '</td></tr>'
												+ '<tr><td align=""><B>Speed:</B></th><td>'
												+ obj["aaData"][i][2]
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
												+ '<a data-toggle="modal" class="btn blue" role="button" '
												+ "onclick=\"javascript:getCordinates('"
												+ obj["aaData"][i][4]
												+ "',00,'all')\"/>"
												+ "Track Online </a></td>"
												+ ' <td><a data-toggle="modal" class="btn yellow" role="button" onclick="javascript:AllDevices.get_reverse_geocode('
												+ obj["aaData"][i][0]
												+ ','
												+ obj["aaData"][i][1]
												+ ')">Location</a></td>'
												+ ' <td><a data-toggle="modal" class="btn blue" role="button" href="#mymodal1" '
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
												+ "Change Vehicle Status</a></td>"
												+ '</tr></table>'
												+ '</div>'
												+ '</div>'
												+ '</div>'
									});// End of marker defination
							// Setting Info window
							google.maps.event
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
											})(marker, j));
							google.maps.event
									.addListener(
											marker,
											'mouseover',
											(function(marker, j) {
												return function() {
													infowindow
															.setContent(this.schno);
													infowindow.open(map, this);
													AllDevices
															.get_schedule_number(this.deviceid);
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
							if (obj["aaData"][i][0] != '0.00000000') {

								vehiclemarker.push(marker);
							}
							// Setting marker
							// on map
						} // End of for loop
						setAllMap(map);
					}
					// map.setCenter(latLong);
				}
			});

}

function getRefresh() {
	if ($("#refreshID").is(':checked')) {
		plotVehicle();
	} else {
	}
}
function getVehicleAlertData(packet_code, misc_byte, deivice_id, licence_number) {
	if (packet_code == 'GF' && misc_byte == '01') {
		document.getElementById("withoutbusstop").style.display = 'none';
		document.getElementById("withbusstop").style.display = '';
		document.getElementById("devdetails").style.display = '';
		$('#vehicleDetails1234').dataTable(
				{
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					"iDisplayLength" : 5,
					"sAjaxSource" : "vehicleAlertDetails.action?packet_code="
							+ packet_code + "&misc_byte=" + misc_byte
							+ "&deivice_id=" + deivice_id + "&licence_number="
							+ licence_number,
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

		jQuery('#vehicleDetails1234_wrapper .dataTables_filter input')
				.addClass("form-control input-medium input-inline"); // modify
		// table
		// search input
		jQuery('#vehicleDetails1234_wrapper .dataTables_length select')
				.addClass("form-control input-xsmall input-inline");
	} else if (packet_code == 'DV' && misc_byte == '00') {
		document.getElementById("withbusstop").style.display = 'none';
		document.getElementById("withoutbusstop").style.display = 'none';
		document.getElementById("devdetails").style.display = '';
		/*
		 * Deviation Details
		 * 
		 */
		$('#vehicleDetailsDev').dataTable(
				{
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					"iDisplayLength" : 5,
					"sAjaxSource" : "vehicleAlertDetails.action?packet_code="
							+ packet_code + "&misc_byte=" + misc_byte
							+ "&deivice_id=" + deivice_id + "&licence_number="
							+ licence_number,
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
		jQuery('#vehicleDetailsDev_wrapper .dataTables_filter input').addClass(
				"form-control input-medium input-inline"); // modify table
		// search input
		jQuery('#vehicleDetailsDev_wrapper .dataTables_length select')
				.addClass("form-control input-xsmall input-inline");

	} else if (packet_code == '51' && misc_byte == '11') {
		console.log('TAmpering');
		document.getElementById("withbusstop").style.display = 'none';
		document.getElementById("withoutbusstop").style.display = 'none';
		document.getElementById("tamperingdetails").style.display = '';
		/*
		 * Tampering Details
		 * 
		 */
		$('#vehicleDetailsTampering').dataTable(
				{
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					"iDisplayLength" : 5,
					"sAjaxSource" : "vehicleAlertDetails.action?packet_code="
							+ packet_code + "&misc_byte=" + misc_byte
							+ "&deivice_id=" + deivice_id + "&licence_number="
							+ licence_number,
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
		jQuery('#vehicleDetailsTampering_wrapper .dataTables_filter input')
				.addClass("form-control input-medium input-inline"); // modify
																		// table
		// search input
		jQuery('#vehicleDetailsTampering_wrapper .dataTables_length select')
				.addClass("form-control input-xsmall input-inline");
	}

	else {
		document.getElementById("withbusstop").style.display = 'none';
		document.getElementById("devdetails").style.display = 'none';
		$('#vehicleDetails123').dataTable(
				{
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					"iDisplayLength" : 5,
					"sAjaxSource" : "vehicleAlertDetails.action?packet_code="
							+ packet_code + "&misc_byte=" + misc_byte
							+ "&deivice_id=" + deivice_id + "&licence_number="
							+ licence_number,
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
		jQuery('#vehicleDetails123_wrapper .dataTables_filter input').addClass(
				"form-control input-medium input-inline"); // modify table
		// search input
		jQuery('#vehicleDetails123_wrapper .dataTables_length select')
				.addClass("form-control input-xsmall input-inline");

	}
	return;
}

function initializea(DEVICE_ID, ID, schedule_no,depotName) {
	document.getElementById("deviceid").value = DEVICE_ID;
	document.getElementById("vehicleid").value = ID;
	document.getElementById("scheduleno").value = schedule_no;
	document.getElementById("depotName").value = depotName;
	window.open("pages/vts/livetracking.jsp", "Edit GeoFence",
			"width=500, height=500");
}

function getShowLiveBusDetailsAll(DEVICE_ID,ticketDate,vehicleid, depotName) {
	//document.getElementById("deviceid").value = DEVICE_ID;
	
	//document.getElementById("vehicleid").value = vehicleid;
	// ticketDate=document.getElementById("ticketDate").innerHTML;
	//alert(fromDate);
	$("#ticketDate1").val(ticketDate);
	//document.getElementById("depotName").value = depotName;
	window.open("ShowLiveBusDetailsAll?deviceser="+DEVICE_ID+"&orgname="+depotName+"&vehicleNo="+vehicleid+"&ticketdate="+ticketDate, "popup",
			"width=1000, height=1000");
}
function getShowLiveBusDetailsAllVts(DEVICE_ID,ticketDate,time,vehicleid, depotName) {
	//document.getElementById("deviceid").value = DEVICE_ID;
	
	document.getElementById("vehicleid").value = vehicleid;
	// ticketDate=document.getElementById("ticketDate").innerHTML;
	//alert(fromDate);
	$("#ticketDate1").val(ticketDate);
	document.getElementById("depotName").value = depotName;
	window.open("ShowLiveBusDetailsAllVts?deviceser="+DEVICE_ID+"&time="+time+"&orgname="+depotName+"&vehicleNo="+vehicleid+"&ticketdate="+ticketDate, "popup",
			"width=1000, height=1000");
}

function getShowLiveBusDetailsForStationaryVehicle(DEVICE_ID,ticketDate,time,vehicleid, depotName) {
	//document.getElementById("deviceid").value = DEVICE_ID;
	
	document.getElementById("vehicleid").value = vehicleid;
	// ticketDate=document.getElementById("ticketDate").innerHTML;
	//alert(fromDate);
	$("#ticketDate1").val(ticketDate);
	document.getElementById("depotName").value = depotName;
	window.open("ShowLiveBusDetailsForStationaryVehicle?deviceser="+DEVICE_ID+"&time="+time+"&orgname="+depotName+"&vehicleNo="+vehicleid+"&ticketdate="+ticketDate, "popup",
			"width=1000, height=1000");
}

function getShowLiveBusDetailsForStationaryVehicle1(DEVICE_ID,ticketDate,time,vehicleid, depotName) {
	//document.getElementById("deviceid").value = DEVICE_ID;
	console.log("hiiiii");
	document.getElementById("vehicleid").value = vehicleid;
	// ticketDate=document.getElementById("ticketDate").innerHTML;
	//alert(fromDate);
	$("#ticketDate1").val(ticketDate);
	document.getElementById("depotName").value = depotName;
	window.open("ShowLiveBusDetailsForStationaryVehicleLocation?deviceser="+DEVICE_ID+"&time="+time+"&orgname="+depotName+"&vehicleNo="+vehicleid+"&ticketdate="+ticketDate, "popup",
			"width=1000, height=1000");
}

function getShowLiveBusDetailsAllVtsData(DEVICE_ID,ticketDate,time,vehicleid, depotName) {

	document.getElementById("vehicleid").value = vehicleid;
	$("#ticketDate1").val(ticketDate);
	document.getElementById("depotName").value = depotName;
	window.open("ShowLiveBusDetailsAllVtsData?deviceser="+DEVICE_ID+"&time="+time+"&orgname="+depotName+"&vehicleNo="+vehicleid+"&ticketdate="+ticketDate, "popup",
			"width=1000, height=1000");
}

function getShowLiveBusDetailsAllVtsLateDeparture(DEVICE_ID,ticketDate,time,vehicleid, depotName) {
	//document.getElementById("deviceid").value = DEVICE_ID;
	
	document.getElementById("vehicleid").value = vehicleid;
	// ticketDate=document.getElementById("ticketDate").innerHTML;
	//alert(fromDate);
	$("#ticketDate1").val(ticketDate);
	document.getElementById("depotName").value = depotName;
	window.open("ShowLiveBusDetailsAllVtsLateDepData?deviceser="+DEVICE_ID+"&time="+time+"&orgname="+depotName+"&vehicleNo="+vehicleid+"&ticketdate="+ticketDate, "popup",
			"width=1000, height=1000");
}

function drawBusBunching(route_id, groupedData, start_point, end_point, routeno) {	
	document.getElementById("routeid").value = route_id;
    document.getElementById("startpoint").value = start_point;
	document.getElementById("endpoint").value =end_point;
	document.getElementById("groupedData").value = groupedData;
	document.getElementById("routeno").value = routeno;
	window.open("pages/vts/bunchtracking.jsp", "Bunch Tracking",
	"width=500, height=500");
}

function getLineTracking(routeNo) {

	//alert(routeNo);
	var redirectWindow =window.open("LinearRouteTrackingRouteNo.action?routeNo="+routeNo, "_blank");
	//var redirectWindow = window.open('http://google.com', '_blank');
}

function viewTripWithonlineTicket(date,wayBillNo,depotCd,depotId,deviceId,vehicleNo,shift,scheduleTripNo) {
console.log(depotId);
//	window.open("TripTicketOnline?date="+date+"&depotName="+depotName, "popup",
//	"width=1000, height=1000");
	
	window.open("showOnlineWaybillsGprsTripwise.action?waybillno="+wayBillNo+"&ticketdate="+date+"&depotid="+depotId+"&tripNo="+scheduleTripNo+"&shift_no="+shift+"", "popup",
			"width=1000, height=1000");
	
}

function viewVTUDeviceDetailsList(device_id,date1,hour,count){
	var date2; 
	switch (hour) {
	case 1:{
	   console.log("hour"+hour);
		date2= date1+" 00:59:59";
		date1=date1+" 00:00:00";
		break;
	}
	case 2:{
		 console.log("hour"+hour);
		date2=date1+" 01:59:59";
		date1=date1+" 01:00:00";
		break;
	}
	case 3:{
		date2=date1+" 02:59:59";
		date1=date1+" 02:00:00";
		break;
	}
	case 4:{
		date2=date1+" 03:59:59";
		date1=date1+" 03:00:00";
		break;
	}
	case 5:{
		date2=date1+" 04:59:59";
		date1=date1+" 04:00:00";
		break;
	}
	case 6:{
		date2=date1+" 05:59:59";
		date1=date1+" 05:00:00";
		break;
	}
	case 7:{
		date2=date1+" 06:59:59";
		date1=date1+" 06:00:00";
		break;
	}
	case 8:{
		date2=date1+" 07:59:59";
		date1=date1+" 07:00:00";
		break;
	}
	case 9:{
		date2=date1+" 08:59:59";
		date1=date1+" 08:00:00";
		break;
	}
	case 10:{
		date2=date1+" 09:59:59";
		date1=date1+" 09:00:00";
		break;
	}
	case 11:{
		date2=date1+" 10:59:59";
		date1=date1+" 10:00:00";
		break;
	}
	case 12:{
		date2=date1+" 11:59:59";
		date1=date1+" 11:00:00";
		break;
	}
	case 13:{
		date2=date1+" 12:59:59";
		date1=date1+" 12:00:00";
		break;
	}
	case 14:{
		date2=date1+" 13:59:59";
		date1=date1+" 13:00:00";
		break;
	}
	case 15:{
		date2=date1+" 14:59:59";
		date1=date1+" 14:00:00";
		break;
	}
	case 16:{
		date2=date1+" 15:59:59";
		date1=date1+" 15:00:00";
		break;
	}
	case 17:{
		date2=date1+" 16:59:59";
		date1=date1+" 16:00:00";
		break;
	}
	case 18:{
		date2=date1+" 17:59:59";
		date1=date1+" 17:00:00";
		break;
	}
	case 19:{
		date2=date1+" 18:59:59";
		date1=date1+" 18:00:00";
		break;
	}
	case 20:{
		date2=date1+" 19:59:59";
		date1=date1+" 19:00:00";
		break;
	}
	case 21:{
		date2=date1+" 20:59:59";
		date1=date1+" 20:00:00";
		break;
	}
	case 22:{
		date2=date1+" 21:59:59";
		date1=date1+" 21:00:00";
		break;
	}
	case 23:{
		date2=date1+" 22:59:59";
		date1=date1+" 22:00:00";
		break;
	}
	
	default:
		date2=date1+" 23:59:59";
	    date1=date1+" 23:00:00";
	    break;
		
	}
		
	console.log(device_id);
	document.getElementById("modalclass3").style.display = '';
	/*window.open("vtuDeviceDetails.action?date="+date1+"&device_id="+device_id+"", "popup",
    "width=1000, height=1000");*/
	
	$('#DeviceDetailTable').dataTable(
			{
				"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
				// per
				// page
				// values
				// here
				],
				// set the initial value
				"iDisplayLength" : 5,
				"sAjaxSource" : "vtuDeviceDetails.action?date1="+date1+"&device_id="+device_id+"&date2="+date2+"&hour="+hour+"&count="+count+"",
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
	jQuery('#DeviceDetailTable_wrapper .dataTables_filter input').addClass(
			"form-control input-medium input-inline"); // modify table
	// search input
	jQuery('#DeviceDetailTable_wrapper .dataTables_length select')
			.addClass("form-control input-xsmall input-inline");
}


function viewCancelledKMDetails(shNo,date,depot){
	
	console.log(shNo);
	document.getElementById("modalclass4").style.display = '';
	
	$('#kmdetailtable').dataTable(
			{
				"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
				// per
				// page
				// values
				// here
				],
				// set the initial value
				"iDisplayLength" : 5,
				"sAjaxSource" : "KMCancelDetails.action?scheduleNo="+shNo+"&depotId="+depot+"&date="+date+"",
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
	jQuery('#kmdetailtable_wrapper .dataTables_filter input').addClass(
			"form-control input-medium input-inline"); // modify table
	// search input
	jQuery('#kmdetailtable_wrapper .dataTables_length select')
			.addClass("form-control input-xsmall input-inline");
	
	
}


function viewZeroLatLongData(fromdate,todate,type,DeviceNo){
	
	console.log(fromdate);
	document.getElementById("modalclass40").style.display = '';
	
	$('#zerolat').dataTable(
			{
				"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
				// per
				// page
				// values
				// here
				],
				// set the initial value
				"iDisplayLength" : 5,
				"sAjaxSource" : "zerolatlongdetails.action?fromdate="+fromdate+"&todate="+todate+"&DeviceNo="+DeviceNo+"&type="+type,
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
	jQuery('#zerolat_wrapper .dataTables_filter input').addClass(
			"form-control input-medium input-inline"); // modify table
	// search input
	jQuery('#zerolat_wrapper .dataTables_length select')
			.addClass("form-control input-xsmall input-inline");
	
	
}


function viewDriverWiseHarshDetails(endDate,date,depotID,deviceId,depotName){
	
	console.log(depotName+"=="+endDate);
	document.getElementById("modalclass41").style.display = '';
	
	$('#harshData').dataTable(
			{
				"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
				// per
				// page
				// values
				// here
				],
				// set the initial value
				"iDisplayLength" : 5,
				"sAjaxSource" : "driverHarshDetails.action?endDate="+endDate+"&depotId="+depotName+"&date="+date+"&deviceId="+deviceId,
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
	jQuery('#harshData_wrapper .dataTables_filter input').addClass(
			"form-control input-medium input-inline"); // modify table
	// search input
	jQuery('#harshData_wrapper .dataTables_length select')
			.addClass("form-control input-xsmall input-inline");
	
	
}


function viewDriverWiseHarshBreaking(endDate,date,depotID,deviceId,depotName){
	
	console.log(depotName+"=="+endDate);
	document.getElementById("modalclass42").style.display = '';
	
	$('#hbreaking').dataTable(
			{
				"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
				// per
				// page
				// values
				// here
				],
				// set the initial value
				"iDisplayLength" : 5,
				"sAjaxSource" : "driverHarshBreaking.action?endDate="+endDate+"&depotId="+depotName+"&date="+date+"&deviceId="+deviceId,
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
	jQuery('#hbreaking_wrapper .dataTables_filter input').addClass(
			"form-control input-medium input-inline"); // modify table
	// search input
	jQuery('#hbreaking_wrapper .dataTables_length select')
			.addClass("form-control input-xsmall input-inline");
	
	
}


function jsFunctionForComparison(startdate,depotid){
	
	console.log(startdate);
	console.log(depotid);
	 $('#schedulevehicleDetails').attr("style", "display:''");
	//alert("here");
	$('#comparisonData').dataTable(
			{
				"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
				// per
				// page
				// values
				// here
				],
				// set the initial value
				"iDisplayLength" : 5,
				"sAjaxSource" : "AjaxScheduleVehicleDetails.action?startdate="+startdate+"&depotid="+depotid,
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
	jQuery('#comparisonData_wrapper .dataTables_filter input').addClass(
			"form-control input-medium input-inline"); // modify table
	// search input
	jQuery('#comparisonData_wrapper .dataTables_length select')
			.addClass("form-control input-xsmall input-inline");
	
	
}

function skipRoute() {
	// Code Goes Here..

}
//------------for playback-----------
var playbackData = [];
var time_date = [];
var time_hour = [];
var vehicle_num = [];
var phone_num = [];
var device_num = [];
var speed = [];
var totdistonsubmitpb = [];
var arrLength = 0;
var distinkm = 0;
var firstGen = 0;
var marker = null;
var tmeout = [];
var iconDirection = [];
var depotName =[];
var schNum =[];
function getCordinatesFromMarker_playback(DEVICE_ID, ID, startdate, enddate, value, timeframe) {
	$('#loadingmessage').show();
	$('#playbacksubmit').hide();
	$(fback).hide();
	$(sback).hide();
	$(mback).hide();
	var jqxhr = $.ajax({
		url : "actgetCordinates.action",
		data : "DEVICE_ID=" + DEVICE_ID + "&ID=" + ID + "&startdate="
				+ startdate + "&enddate=" + enddate + "&value=" + value,
		method : "post",
		global : false,
		async : false,
		success : function(response) {
			$('#loadingmessage').hide();
			$('#playbacksubmit').show();
			$(fback).show();
			$(sback).show();
			$(mback).show();
			return response;
		}
	}).responseText;
	playbackData = [];
	time_date = [];
	time_hour = [];
	vehicle_num = [];
	schedule_num =[];
	phone_num = [];
	device_num = [];
	speed = [];
	schNum =[];
	totdistonsubmitpb = [];
	iconDirection = [];
	depotName = [];
	arrLength = 0;
	distinkm = 0.00;
	firstGen = 0;
	marker = null;
		
		
	
	//-------------------
	var obj = jQuery.parseJSON(jqxhr);
	var objLength=0;
	if (obj != null) {
		objLength= obj["aaData"].length;
		for (i = 0; i < objLength; i++) {
			playbackData.push(new google.maps.LatLng(obj["aaData"][i][0], obj["aaData"][i][1]));
			var time = obj["aaData"][i][3].split(" ");
			time_date.push(time[0]);
			time_hour.push(time[1]);
			vehicle_num.push(obj["aaData"][i][6]);
			phone_num.push(obj["aaData"][i][7]);
			schedule_num.push(obj["aaData"][i][15]);
			device_num.push(obj["aaData"][i][4]);
			speed.push(obj["aaData"][i][2]);
			iconDirection.push(obj["aaData"][i][8]);
			depotName.push(obj["aaData"][i][14]);
			schNum.push(obj["aaData"][i][15]);
			if(i!=0){
//				totdistonsubmitpb.push(((obj["aaData"][i-1][13] - obj["aaData"][i][13]) * 1.60934).toFixed(2));
				totdistonsubmitpb.push(((obj["aaData"][i-1][13] - obj["aaData"][i][13]) / 1000));
			}else{
				totdistonsubmitpb.push(0);
			}
		}
		iconDirection.reverse();
		totdistonsubmitpb.reverse();
		playbackData.reverse();
		time_date.reverse();
		time_hour.reverse();
		vehicle_num.reverse();
		phone_num.reverse();
		device_num.reverse();
		speed.reverse();
		schedule_num.reverse();
		depotName.reverse();
		schNum.reverse();
		
	}
	
	if(objLength > 0){
	myLoop(DEVICE_ID, ID, startdate, enddate, value, timeframe,depotName,schNum);
	}
}
var idd = 0;
function myLoop(DEVICE_ID, ID, startdate, enddate, value, timeframe,depotName,schNum) {
	//console.log("Schedule No"+schNum);
	var markers = {};
	idd = 0;
	var stLatLong = String(playbackData[playbackData.length-1]).split(",");	
	var stLat = stLatLong[0].split("(");
	stLat = stLat[1];
	var stLong = stLatLong[1].split(")");
	stLong = stLong[0];
	var jk = 0;
	marker = new google.maps.Marker(
			{
				map : map,
				position : playbackData[playbackData.length-1],
				icon : 'assets/images/bus-map-icon.png',
				latitude : stLat,
				longitude : stLong,
				info : '<div class="portlet-body form">'
					+ '<div class="form-body">'
					+ '<div class="table-responsive" style="color:#000000"><table class="table table-hover"><tr>'
					+ '<tr><td  align=""><B>GPS Time:</B></th><td>'
					+ time_date[time_date.length-1]
					+ " "
					+ time_hour[time_hour.length-1]
					+ '</td></tr>'
					+ '<tr><td align=""><B>Vehicle Number:</B></th><td>'
					+ vehicle_num[vehicle_num.length-1]
					+ '</td></tr>'
					+ ''
					+ '</td></tr>'
					+ '<tr><td align=""><B>Phone Number:</B></th><td>'
					+ phone_num[phone_num.length-1]
					+ '</td></tr>'
					+ '<tr><td  align=""><B>Schedule No:</B></th><td>'
					+ schNum[0]
					+ '</td></tr>'
					+ '<tr><td align=""><B>Device Number:</B></th><td>'
					+ device_num[device_num.length-1]
					+ '</td></tr>'
					+ '<tr><td align=""><B>Speed:</B></th><td>'
					+ speed[speed.length-1]
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
			(function(marker, jk) {
				return function() {
					if (infowindow) {
						infowindow.close();
					}
					infowindow.setContent(this.info);
					infowindow.open(map, this);
					AllDevices.get_reverse_geocode(this.latitude,this.longitude);
					//AllDevices.get_schedule_number(DEVICE_ID);
				};
			})(marker, jk));
	
google.maps.event.addListener(map, 'click',
	function() {
		infowindow.open(null, null);
	});
jk++;
if (tmeout != null && tmeout.length>0) {
//	alert(tmeout.length);
	for (var i =0; i < tmeout.length; i++){
		clearTimeout(tmeout[i]);
	}
}
	for ( var j = 0; j < playbackData.length; j++) {
		 if(j!=0){
			 (function(j) {				 
					tmeout[j]  = window.setTimeout(function() {
						animate_it(playbackData[j],playbackData[j-1],j,playbackData.length,time_date[j],time_hour[j],vehicle_num[j],phone_num[j],device_num[j],speed[j],DEVICE_ID, ID, startdate, enddate, value,totdistonsubmitpb[j].valueOf(),iconDirection[j],depotName[j],schNum[j]);						
					}, j * (timeframe*1000));
				}(j));
		 }		
	}

}

function animate_it(dept, arr, startpoint, endpoint,date,hour,vehicle,phone,device,speed,DEVICE_ID, ID, startdate, enddate, value, totdistonsubmitpb, iconDirection,depotName,schNum) {
	//console.log("totalsubmit"+totdistonsubmitpb);
	var departure = dept; // Set to whatever lat/lng you need for your
							// departure location
	var arrival = arr; // Set to whatever lat/lng you need for your arrival
						// location
	var depLatLong = String(departure).split(",");	
	var depLat = depLatLong[0].split("(");
	depLat = depLat[1];
	var depLong = depLatLong[1].split(")");
	depLong = depLong[0];
	var arrLatLong = String(arrival).split(",");
	var arrLat = arrLatLong[0].split("(");
	arrLat = arrLat[0];
	var arrLong = arrLatLong[1].split(")");
	arrLong = arrLong[1];
	if(speed>70){
		var line = new google.maps.Polyline({
			path : [ departure, departure ],
			strokeColor : '#ff0000',
			strokeOpacity : 1.0,
			strokeWeight : 8,
			geodesic : true, // set to false if you want straight line instead of
								// arc
			map : map,
		});
	} else {
		var line = new google.maps.Polyline({
			path : [ departure, departure ],
			strokeColor : '#009933',
			strokeOpacity : 1.0,
			strokeWeight : 8,
			geodesic : true, // set to false if you want straight line instead of
								// arc
			map : map,
		});
	}	
	map.setCenter(new google.maps.LatLng(depLat, depLong));
	if(startpoint==1){
		departure = playbackData[0];
		imagicon = "assets/images/BusIconRedFlag.png";
	} else {
		imagicon = "assets/images/bus.png";
//		for Rotation of bus
		if(iconDirection>=0 && iconDirection<22.5){
			imagicon = "assets/images/busRotation/busN.png";
		} else if(iconDirection>=22.5 && iconDirection<67.5){
			imagicon = "assets/images/busRotation/busNE.png";
		} else if(iconDirection>=67.5 &&iconDirection<112.5){
			imagicon = "assets/images/busRotation/busE.png";
		} else if(iconDirection>=112.5 && iconDirection<157.5){
			imagicon = "assets/images/busRotation/busSE.png";
		} else if(iconDirection>=157.5 && iconDirection<202.5){
			imagicon = "assets/images/busRotation/busS.png";
		} else if(iconDirection>=202.5 && iconDirection<247.5){
			imagicon = "assets/images/busRotation/busSW.png";
		} else if(iconDirection>=247.5 && iconDirection<292.5){
			imagicon = "assets/images/busRotation/busW.png";
		} else if(iconDirection>=292.5 && iconDirection<337.5){
			imagicon = "assets/images/busRotation/busNW.png";
		} else if(iconDirection>=337.5 && iconDirection<361){
			imagicon = "assets/images/busRotation/busN.png";
		}
		iconDirection = 0;
	}
	
 	var j = 0;
	marker = new google.maps.Marker(
			{
				map : map,
				position : departure,
				icon : imagicon,
				latitude : depLat,
				longitude : depLong,
				info : '<div class="portlet-body form">'
					+ '<div class="form-body">'
					+ '<div class="table-responsive" style="color:#000000"><table class="table table-hover"><tr>'
					+ '<tr><td  align=""><B>GPS Time:</B></th><td>'
					+ date
					+ " "
					+ hour
					+ '</td></tr>'
					+ '<tr><td align=""><B>Vehicle Number:</B></th><td>'
					+ vehicle
					+ '</td></tr>'
					+ ''
					+ '</td></tr>'
					+ '<tr><td align=""><B>Phone Number:</B></th><td>'
					+ phone
					+ '</td></tr>'
					+ '<tr><td  align=""><B>Depot Name:</B></th><td>'
					+ depotName
					+ '</td></tr>'
					+ '<tr><td  align=""><B>Schedule No:</B></th><td>'
					+ schNum
					+ '</td></tr>'
					+ '<tr><td align=""><B>Device Number:</B></th><td>'
					+ device
					+ '</td></tr>'
					+ '<tr><td align=""><B>Speed:</B></th><td>'
					+ speed
					+ 'Kmph</td></tr>'
					+ '<tr><td align=""><B>Current Location:</B></th><td><div id="location"></div></td></tr></table>'
					+ '</div>'
					+ '</div>'
					+ '</div>'
		});// End of marker defination
	markers[idd] = marker;	
	google.maps.event
			.addListener(
					marker,
					'click',
					(function(marker, j) {
						return function() {
							if (infowindow) {
								infowindow.close();
							}
							infowindow.setContent(this.info);
							infowindow.open(map, this);
							AllDevices.get_reverse_geocode(this.latitude,this.longitude);
							AllDevices.get_schedule_number(DEVICE_ID);
						}
					})(marker, j));
	google.maps.event.addListener(map, 'click',
			function() {
				infowindow.open(null, null);
			});
	j++;
	idd++;
	if(idd>1){
		delMarker(idd-2);
	}	
	

	var step = 0;
	var numSteps = 1; // Change this to set animation resolution
	var timePerStep = 1; // Change this to alter animation speed
	var interval = setInterval(function() {
		step += 1;
		if (step > numSteps) {
			clearInterval(interval);
		} else {
			var are_we_there_yet = google.maps.geometry.spherical.interpolate(
					departure, arrival, step / numSteps);
			line.setPath([ departure, are_we_there_yet ]);
		}
	}, timePerStep);
	
	
	/** ***CO0DE FOR TOOL TIP ON SUBMIT****** */
	
//	distinkm = distinkm+parseFloat(totdistonsubmitpb);
	//console.log("totalind"+distinkm);
	if(firstGen==0){
		$('#vehicledetails').remove();
		var homeControlDivOnSubmit = document.createElement('div');
		homeControlDivOnSubmit.id = 'vehicledetailsonsubmit';
		var homeControlonSubmit = new HomeControlOnSubmit(
				homeControlDivOnSubmit, map, DEVICE_ID, ID,
				startdate, enddate, value, distinkm, vehicle);
		homeControlDivOnSubmit.index = 1;
		map.controls[google.maps.ControlPosition.RIGHT_TOP]
				.push(homeControlDivOnSubmit);
		distinkm = distinkm+parseFloat(totdistonsubmitpb);
		document.getElementById('speedofvehicle').innerHTML = 0+" kmph";
		document.getElementById('timeofvehicle').innerHTML = hour;		
		firstGen++;
	} else {
		distinkm = distinkm+parseFloat(totdistonsubmitpb);
		document.getElementById('distanceonsubmit').innerHTML = distinkm.toFixed(2)+" kms";
		document.getElementById('speedofvehicle').innerHTML = speed+" kmph";
		document.getElementById('timeofvehicle').innerHTML = hour;
	}
	
	/** ***END OF TOOL TIP ON SUBMIT******* */
}
var delMarker = function (idd) {
	if(idd>0){
		marker = markers[idd]; 
		marker.setIcon("assets/images/red-marker-icon.png");
	}
}
function deviationTracking(device_id, route_id, start_point, end_point,
		start_time, trip_status, duty_date, id, end_time, schedule_name){
	var selectedDate = duty_date;
	duty_date = selectedDate;
var routeData = $.ajax({
		url : "getRoutefence.action?routeid=" + route_id + "&start_id="
				+ start_point + "&end_id=" + end_point,
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

	if (length == 0) {
		
	} else {
		for ( var i = 0; i < encodedPolyLine.length; i++) {
			encodedPolyLine[i].setMap(null);
		}
		encodedPolyLine = [];
		for ( var i = 0; i < length; i++) {
			var latLong = new google.maps.LatLng(
					parseData["aaData"][i]["2"],
					parseData["aaData"][i]["3"]);
			var latLong_end = new google.maps.LatLng(
					parseData["aaData"][i]["5"],
					parseData["aaData"][i]["6"]);
			encodedPolyLine.push(addEncodedPath(
					parseData["aaData"][i]["0"], i, length,
					latLong, latLong_end,
					parseData["aaData"][i]["1"],
					parseData["aaData"][i]["4"]));
			$("#route_stops").html(
					$("#route_stops").html() + "<br />" + (i + 1)
							+ ". " + parseData["aaData"][i]["1"]);
		}
	}
	
	/*var routeData = $.ajax({
		url : "getRoutefence.action?routeid=" + route_id
				+ "&start_id=" + start_point + "&end_id=" + end_point,
		dataType : 'json',
		datatype : "post",
		global : false,
		async : false,
		success : function(response) {
			req_status = false;
			return response;
		}
	}).responseText;
	parseData = jQuery.parseJSON(routeData);
	var length = parseData["aaData"].length;

	if (length == 0) {
		$("#msg_schedule").html("Route Data Not Available !!!!");
		console.log("Route Data Not Available !!!!");
	} else {
		$("#msg_schedule").html("Route Plotted !!!!");
		for (i = 0; i < encodedPolyLine.length; i++) {
			encodedPolyLine[i].setMap(null);
		}
		encodedPolyLine = [];

		for ( var i = 0; i < length; i++) {
			var latLong = new google.maps.LatLng(
					parseData["aaData"][i]["2"],
					parseData["aaData"][i]["3"]);
			var latLong_end = new google.maps.LatLng(
					parseData["aaData"][i]["5"],
					parseData["aaData"][i]["6"]);
			encodedPolyLine.push(addEncodedPath(
					parseData["aaData"][i]["0"], i, length,
					latLong, latLong_end,
					parseData["aaData"][i]["1"],
					parseData["aaData"][i]["4"]));
			$("#route_stops").html(
					$("#route_stops").html() + "<br />" + (i + 1)
							+ ". " + parseData["aaData"][i]["1"]);
		}

	}*/
	getCordinates(device_id,'0',duty_date + " " + start_time,
			duty_date + " " + end_time, 'ScheduleSubmit', false,
			schedule_name);
		return;
	}

function getCordinatesDV(DEVICE_ID, ID, startDate, endDate, value,svalue,schedulename) {
	map = new google.maps.Map(document.getElementById('gmap_basic'), mapOptions);
	deleteMarkersvehicle();
	/*if (intervalID != null) {
		clearInterval(intervalID);
	}
	if (startDate != '') {
		initializeMap();
	}
	deleteMarkers();
	deleteMarkersvehicle();
	isfirst = false;
	if (value === 'Submit') {
		getCordinatesFromMarker(DEVICE_ID, ID, startDate, endDate,	value);
		clearInterval(intervalID);
		return;
	}
	if (startDate == undefined || endDate == undefined) {
		startDate = $('#startdate').val();
		endDate = $('#enddate').val();
	}

	if (maxId == null) {
		ID = 0;
	}
	if (intervalID != null) {
		clearInterval(intervalID);
	}
*/
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
	intervalID = setInterval(
			function() {
				if (value === 'Submit') {

				} else {
					changeDistanceValue(DEVICE_ID, ID, startDate, endDate,
							value);
				}
				var iconsetngs = {
					path : google.maps.SymbolPath.FORWARD_CLOSED_ARROW
				};
				$
						.ajax({
							url : "actgetCordinates.action",
							data : "DEVICE_ID=" + DEVICE_ID + "&ID=" + ID
									+ "&startdate=" + startDate + "&enddate="
									+ endDate + "&value=" + value,
							method : "post",
							async : true,
							success : function(response) {
								// Response Check

								infowindow = new google.maps.InfoWindow();
								poly = new google.maps.Polyline({
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
									} ]
								});
								poly.setMap(map);
								var path = poly.getPath();
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
											imgicon = 'assets/images/bus-map-icon.png';
											ID = obj["aaData"][i][5];
											lastLat = obj["aaData"][i][0];
											lastLong = obj["aaData"][i][1];
										} else {
											// G-Map image icon for Intermediate
											// geo-points
											imgicon = google.maps.SymbolPath.FORWARD_CLOSED_ARROW// 'assets/images/red-marker-icon.png';
										}
										if (!isfirst
												&& (objLength - i - 1) === 0) {
											// G-Map image icon for first
											// geo-points
											isfirst = true;
											imgicon = 'assets/images/BusIconRedFlag.png';
										}
										var latLong = new google.maps.LatLng(
												obj["aaData"][i][0],
												obj["aaData"][i][1]);
										var time = obj["aaData"][i][3]
												.split(" ");

										var device_id = obj["aaData"][i][4];
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
															+ '<tr><td  align=""><B>Schedule No:</B></th><td>'
															+ schedulename
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

																AllDevices
																		.get_schedule_number(DEVICE_ID);
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

																AllDevices
																		.get_schedule_number(DEVICE_ID);
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

											// alert(latLong);
											path.push(latLong);
										}

									}
									// End of for loop
								}
								latLong = new google.maps.LatLng(
										obj["aaData"][0][0],
										obj["aaData"][0][1]);
								map.setCenter(latLong);
								AllDevices.changeIcon();

								var flightPlanCoordinates = [];
								for (mrk = 0; mrk < markers.length; mrk++) {
									flightPlanCoordinates
											.push(markers[mrk].position);
								}
								var flightPath = new google.maps.Polyline({
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
									} ]
								});
								flightPath.setMap(null);
								flightPath.setMap(map);
							}
						});
			}, 10000);

}
function check() {
	var val = $("#vehicle_status option:selected").val();
	if (val == 1) {
		$("#call_type").val("1");
		document.getElementById("accident_type").style.display = 'none';
		document.getElementById("breakdown_type").style.display = 'none';
	} else if (val == 2) {
		$("#call_type").val("2");
		var divId = document.getElementById("breakdown_type");
		divId.style.display = 'none';
		var divId = document.getElementById("accident_type");
		divId.style.display = '';
		var type = 'accident';
		$.ajax({
			url : "getSOSType.action",
			data : "type=" + type,
			success : function(result) {
				document.getElementById('acctypelist').innerHTML = result;
			}
		});
	} else if (val == 3) {
		$("#call_type").val("3");
		var divId1 = document.getElementById("accident_type");
		divId1.style.display = 'none';
		var divId = document.getElementById("breakdown_type");
		divId.style.display = '';

		var type = 'breakdown';
		$.ajax({
			url : "getSOSType.action",
			data : "type=" + type,
			success : function(result) {
				document.getElementById('brkdwntypelist').innerHTML = result;
			}
		});

	} else {
		$("#call_type").val("4");		
		document.getElementById("accident_type").style.display = 'none';
		document.getElementById("breakdown_type").style.display = 'none';
	}
}
function getVehData(deviceId, vehNum, gpstime,lat,lng,depot_id,shift,token,depot_name,sch_name) {	
	document.getElementById("accident_type").style.display = 'none';
	document.getElementById("breakdown_type").style.display = 'none';
	
	$("#vehicle_no").val(vehNum);
	$("#device_id").val(deviceId);
	$("#gpstime").val(gpstime);
	$("#depotnm").val(depot_name);
	$("#scheduleno").val(sch_name);
	$("#vehicle_status").val(0);
	$("#description").val('');
	
	$("#lat").val(lat);
	$("#lng").val(lng);
	$("#depot_id").val(depot_id);
	$("#shift").val(shift);
	$("#drivertoken").val(token);
	//alert("vehicle_status: "+$("#vehicle_status").val());
}
function changeVehicleStatus(){
	var vehicle_no = $("#vehicle_no").val();
	var device_id = $("#device_id").val();
	var gpstime = $("#gpstime").val();
	var depotnm = $("#depotnm").val();
	var scheduleno = $("#scheduleno").val();
	var call_type = $("#call_type").val();
	var vehicle_status = $("#vehicle_status").val();
	var notes = $("#description").val();
	var acctypelist = $("#acctypelist").val();
	var brkdwntypelist = $("#brkdwntypelist").val();
	var schId=$("#scheduleId").val();
	//adding lat,lng,shift,Driver Token no,depot_id on 6th Jan
	var lat = $("#lat").val();
	var lng = $("#lng").val();
	var driverToken = $("#drivertoken").val();
	var shift = $("#shift").val();
	var depot_id = $("#depot_id").val();
	console.log(lat,lng,driverToken,shift,depot_id);
	//End Code
	if(vehicle_no==""||device_id==""){
		alert("Error.");
		return false;
	}
	if(vehicle_status==0){
		alert("Select Vehicle Status.");
		return false;
	}
	if(vehicle_status==2 && acctypelist==0){
		alert("Select Accident Type.");
		return false;
	}
	if(vehicle_status==3 && brkdwntypelist==0){
		alert("Select Breakdown Type.");
		return false;
	}
	
	$.ajax({
		url : "insertVehicleStatus.action",
		data : "vehicle_no=" + vehicle_no + "&device_id=" + device_id + "&gpstime="
				+ gpstime + "&depotnm=" + depotnm + "&scheduleno=" + scheduleno + "&call_type=" + call_type
				 + "&vehicle_status=" + vehicle_status + "&acctypelist=" + acctypelist + "&brkdwntypelist=" + brkdwntypelist
				 + "&notes=" + notes + "&depot_id=" + depot_id + "&shift=" + shift +
				 "&driverToken=" + driverToken + "&lat=" + lat + "&lng=" + lng+"&schId="+ schId,
		method : "post",
		type:"text",
		success : function(response) {			
				alert("Vehicle status updated successfully !!!");
				$('#mymodal1').modal('hide');
		},
		error : function(e){
			alert('error   '+e.toSource());
		}

	});
	
	
}

function getVehicleAccAlertData(deivice_id, licence_number) {
	document.getElementById("withbusstop").style.display = 'none';
	document.getElementById("devdetails").style.display = 'none';
	document.getElementById("vehiclealertDeacc").style.display = 'none';
	document.getElementById("vehiclealertAcc").style.display = '';
			$('#vehicleAlertAcc').dataTable(
				{
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					"iDisplayLength" : 5,
					"sAjaxSource" : "vehicleAlertAccDetails.action?device_id=" + deivice_id + "&licence_number="
							+ licence_number,
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

		jQuery('#vehicleAlertAcc_wrapper .dataTables_filter input')
				.addClass("form-control input-medium input-inline"); // modify
		// table
		// search input
		jQuery('#vehicleAlertAcc_wrapper .dataTables_length select')
				.addClass("form-control input-xsmall input-inline");
	}


function getVehicleDeaccAlertData(deivice_id, licence_number) {
	document.getElementById("withbusstop").style.display = 'none';
	document.getElementById("devdetails").style.display = 'none';
	document.getElementById("vehiclealertAcc").style.display = 'none';
	document.getElementById("vehiclealertDeacc").style.display = '';
	$('#vehicleAlertDeacc').dataTable(
		{
			"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
			// per
			// page
			// values
			// here
			],
			// set the initial value
			"iDisplayLength" : 5,
			"sAjaxSource" : "vehicleAlertDeaccDetails.action?device_id=" + deivice_id + "&licence_number="
			+ licence_number,
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

jQuery('#vehicleAlertDeacc_wrapper .dataTables_filter input')
		.addClass("form-control input-medium input-inline"); // modify
// table
// search input
jQuery('#vehicleAlertDeacc_wrapper .dataTables_length select')
		.addClass("form-control input-xsmall input-inline");
}


function viewVehicleWiseKMPLDetailsData(endDate,fromdate,depotID,vehicleNo){
	
	console.log(depotID+"=="+endDate);
	document.getElementById("modalclass43").style.display = '';
	
	$('#vehKMPL').dataTable(
			{
				"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
				// per
				// page
				// values
				// here
				],
				// set the initial value
				"iDisplayLength" : 5,
				"sAjaxSource" : "vehWiseKMPLData.action?endDate="+endDate+"&depotId="+depotID+"&fromdate="+fromdate+"&vehicleNo="+vehicleNo,
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
	jQuery('#vehKMPL_wrapper .dataTables_filter input').addClass(
			"form-control input-medium input-inline"); // modify table
	// search input
	jQuery('#vehKMPL_wrapper .dataTables_length select')
			.addClass("form-control input-xsmall input-inline");
	
}

	function viewScheduleWiseKMPLData(endDate,fromdate,depotID,scheduleNo){
		
		console.log(depotID+"=="+endDate);
		document.getElementById("modalclass44").style.display = '';
		
		$('#schedKMPL').dataTable(
				{
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					"iDisplayLength" : 5,
					"sAjaxSource" : "scheduleWiseKMPLData.action?endDate="+endDate+"&depotId="+depotID+"&fromdate="+fromdate+"&scheduleNo="+scheduleNo,
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
		jQuery('#schKMPL_wrapper .dataTables_filter input').addClass(
				"form-control input-medium input-inline"); // modify table
		// search input
		jQuery('#schKMPL_wrapper .dataTables_length select')
				.addClass("form-control input-xsmall input-inline");
	
	
}
	
	
	
	
	
	
	
