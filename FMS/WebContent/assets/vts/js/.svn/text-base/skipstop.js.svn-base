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
var path=null;
var infowindow =null;
var busstopdist = [];
var busstopid = [];
var busstopid1 = [];

function deleteMarkers() {
	clearMarkers();
	markers = [];
}
function setAllMap(map) {
	for ( var i = 0; i < markers.length; i++) {
		markers[i].setMap(map);
	}
}

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
//			if(schCounter == 0){
//				schCounter++;
//
//			}
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

function drawSkipstop(DEVICE_ID,ticketDate,time,vehicleid, depotName,busstop_id,route_id,start_point,end_point) {
//	document.getElementById("deviceid").value = DEVICE_ID;
//	document.getElementById("routeid").value = route_id;
//	document.getElementById("startpoint").value = start_point;
//	document.getElementById("endpoint").value =end_point;
//	document.getElementById("starttime").value = start_time;
//	document.getElementById("endtime").value = end_time;
	console.log("inside deviated stop");
	window.open("ShowLiveBusSkipStopDetailsAllVts?deviceser="+DEVICE_ID+"&time="+time+"&orgname="+depotName+"&vehicleNo="+vehicleid+"&ticketdate="+ticketDate+"&busstopid="+busstop_id
			+"&routeid="+route_id+"&startpoint="+start_point+"&endpoint="+end_point, "popup","width=1000, height=1000");
}

function skipstopTracking(device_id, route_id, start_point, end_point,start_date, end_date,busstop_id){
	
	
	
	getCordinates(device_id, 0, start_date, end_date,'Submit',busstop_id,route_id);
	
	
	
		return;
	}


//Calling Methods to Plot on GMAP....
function getCordinates(DEVICE_ID, ID, startDate, endDate, value,busstop_id,busstop_name) {
	console.log("inside getcordinate()");
//	if ($plotvehicleRequest != null){ 
//		$plotvehicleRequest.abort();
//		$plotvehicleRequest = null;
//	}
//	if ($coordinateRequest != null){ 
//		$coordinateRequest.abort();
//		$coordinateRequest = null;
//	}
	$('#loadingmessage').hide();
	//map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
	
	if (value === 'Submit') {
		 
		
		clearInterval(intervalID);
		getCordinatesFromMarker(DEVICE_ID, ID, startDate, endDate,	value,busstop_id,busstop_name);
		return;
	}
}
function getCordinatesFromMarker(DEVICE_ID, ID, startdate, enddate, value,busstop_id,busstop_name) {	
	//deleteMarkers();
	//deleteMarkersvehicle();
	stlatitudeonsubmit = 0;
	stlongitudeonsubmit = 0;
	endlatitudeonsubmit = 0;
	endlongitudeonsubmit = 0;
	var totdistonsubmit = 0;
	//getskippedRouteTrac(busstop_id,map);
	var encodedPaths = [];
	$.ajax({
	    type        : 'POST',
	    data: {
	        //routeid:  routeID,
	       // start_point: start_point,
	       // end_point:  end_point,
	    	busstopid:  busstop_id,
	        
	    },
	    url         :  'showskipstoproute.action',
			success : function(response) {
             console.log("hii i m response");
            var obj = JSON.parse(response);
				if (obj != null) {
					var objLength = obj["aaData"].length;
					 console.log("hii i m response2"+objLength);
					for ( var i = 0; i < objLength; i++) {
						imgicon = 'assets/images/red-marker-icon.png';
						var latLong = new google.maps.LatLng(
								obj["aaData"][i][0], obj["aaData"][i][1]);
						 console.log("hii i m response"+obj["aaData"][i][0]);
						 var mapOptions = {
								  zoom: 10,
								  center: latLong
								}
						 infowindow = new google.maps.InfoWindow();
						 map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
		var marker = new google.maps.Marker(
				{
					position : latLong,
					info :'<div class="portlet-body form">'
						+ '<div class="form-body">'
						+ '<div class="table-responsive" style="color:#000000"><table class="table table-hover"><tr>'
						// + '<tr><td
						// align=""><B>ID:</B></th><td>'
						// +
						// obj["aaData"][i][5]
						// + '</td></tr>'
						+ '<tr><td  align=""><B>Bus Stop Name:</B></th><td>'
						+ busstop_name
						+ '</td></tr></table>'
					+ '</div>'
					+ '</div>'
					+ '</div>'
						
					//icon : imgicon,
					//vehicledirection : obj["aaData"][i][8],
				});
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
						
					}
				})(marker, i));

		console.log('marker'+marker);
		//markers.push(marker);
		marker.setMap(map);
					}
				}
	}
});
					
	
	
	
	//deleteMarkersvehicle();
	var mypos1=0;
	$('#loadingmessage').show(); 
	$
			.ajax({
				url : "actgetCordinates.action",
				data : "DEVICE_ID=" + DEVICE_ID + "&ID=" + ID + "&startdate="
						+ startdate + "&enddate=" + enddate + "&value=" + value,
				method : "post",
				success : function(response) {
					$('#loadingmessage').hide(); 
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
								
								if(obj["aaData"][i][2] < 60){
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
							totdistonsubmit = Math.round((obj["aaData"][0][13] - obj["aaData"][objLength - 1][13]) / 1000);							
							latLong = new google.maps.LatLng(obj["aaData"][0][0],
									obj["aaData"][0][1]);

							map.setCenter(latLong);
						}
						// End of for loop
					}
					/** ***CO0DE FOR TOOL TIP ON SUBMIT****** */
					
					/** ***END OF TOOL TIP ON SUBMIT******* */
					

					//AllDevices.changeIconHistory();
					

				}
			});

}


function clearMarkers() {
	setAllMap(null);
}




function getskippedRouteTrac(busstop_id,map)
{
//	console.log("inside deviated stop3"+routeID);
//	console.log("inside deviated stop3"+start_point);
//	console.log("inside deviated stop3"+end_point);
//	console.log("inside deviated stop3"+rparam);
	console.log("inside skip stop3"+busstop_id);
	
	
		
		 console.log("hii i m marker");
		// END

	
	
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
				//clearMarkers();
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
	

}