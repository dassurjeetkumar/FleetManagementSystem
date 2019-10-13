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
var directionsDisplay;
var directionsService;
var chkdeviceid=0;
var intervalID =null;
$(function() {

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
	
	 directionsService = new google.maps.DirectionsService();
});
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
			markers[i - 1].icon = "assets/images/bus-map-icon.png";
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

// 863071013980609
function addEncodedPath(encodedPath, bus_stop_name, lat, lng, i, length,
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
	google.maps.event.addListener(marker, 'mouseover', function() {
		if (infowindow) {
			infowindow.close();
			}
		infowindow.setContent(this.info);
		infowindow.open(map, this);
		// AllDevices.get_reverse_geocode(path[0].lat(), path[0].lng());
	});
	google.maps.event.addListener( map, 'mouseover', function() { 
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
		google.maps.event.addListener(marker, 'mouseover', function() {
			if (infowindow) {
				infowindow.close();
				}
			infowindow.setContent(this.info);
			infowindow.open(map, this);
			// AllDevices.get_reverse_geocode(path[0].lat(), path[0].lng());
		});
		google.maps.event.addListener( map, 'mouseover', function() { 
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
		url : "getRoutefence.action?routeid=" + route_id + "&start_id="
				+ start_point + "&end_id=" + end_point,
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
			var latLong_end = new google.maps.LatLng(
					parseData["aaData"][i]["4"], parseData["aaData"][i]["5"]);
			encodedPolyLine.push(addEncodedPath(parseData["aaData"][i]["0"],
					parseData["aaData"][i]["1"], parseData["aaData"][i]["2"],
					parseData["aaData"][i]["3"], i, length - 1, latLong_end));
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
			var latLong_end = new google.maps.LatLng(
					parseData["aaData"][i]["4"], parseData["aaData"][i]["5"]);
			encodedPolyLine.push(addEncodedPath(parseData["aaData"][i]["0"],
					parseData["aaData"][i]["1"], parseData["aaData"][i]["2"],
					parseData["aaData"][i]["3"], i, length - 1, latLong_end));
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
			var latLong_end = new google.maps.LatLng(
					parseData["aaData"][i]["4"], parseData["aaData"][i]["5"]);
			encodedPolyLine.push(addEncodedPath(parseData["aaData"][i]["0"],
					parseData["aaData"][i]["1"], parseData["aaData"][i]["2"],
					parseData["aaData"][i]["3"], i, length - 1, latLong_end));
		}
		getCordinatesFromMarker(device_id, 0, duty_date + " " + start_time,
				duty_date + " " + end_time, 'ScheduleSubmit', true,
				schedule_name);
		clearInterval(intervalID);
		return;
	}

}*/


var busstopdist=[];
var busstopid=[];
var busstopid1=[];
var ttldistroute=0;
var laststopid=0;
function drawScheduleRoute(device_id, route_id, start_point, end_point,start_time, trip_status, duty_date, id, end_time, schedule_name) {
	
		clearInterval(intervalID);
	
	console.log(intervalID);
	initializeMap();
	var selectedDate = document.getElementById("selecteddate").value;
	duty_date = selectedDate;
	$('#bus_stop_list').html('');
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
	      
	    //    ttldistroute=(parseInt(result.route[0][9])+parseInt(result.route[0][11]))/1000;
	       // alert(result.route[0][9]+"="+result.route[0][11]+"=="+ttldistroute);
	        for(var i=0;i<result.routemaplen;i++){
	        	busstopid.push(result.routemap[i][2]);
	        	calcdist=calcdist+result.routemap[i][6]+result.routemap[i][7];
	        	if(result.routemap[i][15]=="TRIMAX"){
	        		calcdist=calcdist+result.routemap[i][6]+result.routemap[i][7];
	        	}else{
	        	busstopdist.push(calcdist);
	        	//calcdist=0;
	        	}
	        }
	       // console.log("busstopid"+busstopid);
	      //  console.log("busstopdist"+busstopdist);
	        for(var i=0;i<result.routemaplen;i++){
	        	encodedPaths.push(result.routemap[i][13]);
	        }
	        addEncodedPaths( encodedPaths );
	        getCordinatesFromMarker(device_id, 0, duty_date + " " + start_time,duty_date + " " + end_time, 'ScheduleSubmit', false,schedule_name,result);
	        	    },
	    error : function(xhr, errmsg) {
	    	alert("No values found..!!"+errmsg);
	    	}
	});
	
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

function addEncodedPaths( encodedPaths ) {
	//alert(encodedPaths);
   for( var i = 0, n = encodedPaths.length;  i < n;  i++ ) {
	   setTimeout(function(){}, 100);
	   addEncodedPath( encodedPaths[i] );
   }
}
var endstopcord="";
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



// Shows any markers currently in the array.
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
 var d1;var d2;
function getCordinates(DEVICE_ID, ID, startDate, endDate, value, schedule_name) {
	//alert("hii");
	if (startDate != '') {
		initializeMap();
	}
	//alert("start"+tripstartaccdist);
	// deleteMarkers();
	isfirst = false;
	if (intervalID != null) {
		clearInterval(intervalID);
	}
	d1 = new Date();
	var google_eta="";
/*	intervalID = setInterval(
			function() {*/
				$
						.ajax({
							url : "actgetCordinates.action",
							data : "DEVICE_ID=" + DEVICE_ID + "&ID=" + ID
									+ "&startdate=" + startDate + "&enddate="
									+ endDate + "&value=" + value,
							method : "post",
							success : function(response) {
								d2 = new Date();
								if($("#refreshID").prop('checked') == true){
								    //do something
									//alert(chkdeviceid+"========"+DEVICE_ID);
									if(chkdeviceid==DEVICE_ID){
										//alert(chkdeviceid+"========"+DEVICE_ID);
										var seconds =  (d2- d1)/1000;
										console.log("seconds"+seconds);
										if(parseInt(seconds)>10){
											console.log("in if");
										getCordinates(DEVICE_ID, ID, startDate, endDate, value, schedule_name);
										}else{
											console.log("in else");
											intervalID=setTimeout(function(){ getCordinates(DEVICE_ID, ID, startDate, endDate, value, schedule_name); }, 20000);
											//setTimeout(function(){ alert("Hello"); }, 1000);
											
										}
									}
									
								}
								  
								  
								//getCordinates(DEVICE_ID, ID, startDate, endDate, value, schedule_name);
								var obj = jQuery.parseJSON(response);
									//////////////////////////////////////
								var endroutelatlng=endstopcord.split(",");
								var start = new google.maps.LatLng( obj["aaData"][0][0],obj["aaData"][0][1]);;
								  var end = new google.maps.LatLng( endroutelatlng[0],endroutelatlng[1]);;
								  console.log("Start End"+start+"\t"+end);
								  var request = {
								    origin: start,
								    destination: end,
								    travelMode: google.maps.TravelMode.TRANSIT
								  };
								  directionsService.route(request, function(response, status) {
								    if (status == google.maps.DirectionsStatus.OK) {
								      //alert(response.toSource());
								    	google_eta=response.routes[0].legs[0].duration.text;
								    	console.log("google_etagoogle_eta===="+google_eta);
								     $("#c").html(response.routes[0].legs[0].duration.text);
								     $('#a').html(response.routes[0].legs[0].duration.text);
								     if(ttldistroute<totaltravdist){
								    	 //$('#a').html(google_eta);
								     }
								    }
								  });
								  ////////////////////////////////////////
								var currbuslocdist=0;
								var totaltravdist=0;
								if(obj!=null){
									currbuslocdist=parseFloat(obj["aaData"][0][10]);
									//alert(tripstartaccdist);
									totaltravdist=(currbuslocdist-tripstartaccdist)*1.852;
									console.log("currbuslocdist"+currbuslocdist+"tripstartaccdist"+tripstartaccdist);
								}
								var timetoreach=0;
								if(obj['aaData'][0][2]!=0.00){
									timetoreach=(ttldistroute-totaltravdist)/obj['aaData'][0][2];
									timetoreach=timetoreach*60;
									//console.log("timetoreach==>"+busstopid);
									for(var x=0;x<busstopid1.length;x++){
										if(x==busstopid1.length-1){
											//alert(busstopid1[x]);
											
											$('#a').html("");
											var calctime=Math.round(timetoreach);
											var min=0;
											var hour=0;
											console.log("route distance:"+ttldistroute+"tra dist:"+totaltravdist+"dist_trveled"+(ttldistroute-totaltravdist)+"avg speed==>"+obj["aaData"][0][2]+"calctime==>"+calctime);
											if(calctime!=0){
												hour=Math.floor(calctime/60);
												min=Math.round(calctime%60);
											}
											var today = new Date();
											today.setHours(today.getHours()+hour);
											today.setMinutes(today.getMinutes()+min);
											if(ttldistroute>totaltravdist){
												google_eta
											//$('#a').html(hour+" hours "+min+" mins");
											}else{
												$('#a').html(google_eta);
												console.log("google_etagoogle_eta after===="+google_eta);
											}
											//$('#a').html(hour+":"+min+" Min");
										}
									}
								}
								
								var objLength = obj["aaData"].length;
								if (liveTrackingDeviceId == null) {
									liveTrackingDeviceId = DEVICE_ID;
								}
								if (liveTrackingDeviceId !== DEVICE_ID) {
									isfirst = false;
									liveTrackingDeviceId = DEVICE_ID;
									ID = 0;
								} else {
								}
								ID = obj["aaData"][objLength-1][5];
								var latLong = new google.maps.LatLng(
										obj["aaData"][objLength-1][0],
										obj["aaData"][objLength-1][1]);
								var time = obj["aaData"][objLength-1][3].split(" ");
								imgicon = 'assets/images/track_blue.png';
								clearMarkers();
								map.setCenter(new google.maps.LatLng(obj["aaData"][objLength-1][0],obj["aaData"][objLength-1][1]));
								marker = new google.maps.Marker(
										{
											map : map,
											position : latLong,
											icon : imgicon,
											latitude : obj["aaData"][objLength-1][0],
											longitude : obj["aaData"][objLength-1][1],
											vehicledirection : obj["aaData"][objLength-1][8],
											info : '<div class="portlet-body form">'
													+ '<div class="form-body">'
													+ '<div class="table-responsive" style="color:#000000"><table class="table table-hover"><tr>'
													+ '<tr><td  align=""><B>GPS Time:</B></th><td>'
													+ time[0]
													+ " "
													+ time[1]
													+ '</td></tr>'
													+ '<tr><td align=""><B>Vehicle Number:</B></th><td>'
													+ obj["aaData"][objLength-1][6]
													+ '</td></tr>'
													+ '<tr><td  align=""><B>Depot Name:</B></th><td>'
													+ 'Depot 11'
													+ '</td></tr>'
													+ '<tr><td  align=""><B>Schedule No:</B></th><td>'
													+ schedule_name
													+ '</td></tr>'
													+ '<tr><td align=""><B>Device Number:</B></th><td>'
													+ obj["aaData"][objLength-1][4]
													+ '</td></tr>'
													+ '<tr><td align=""><B>Speed:</B></th><td>'
													+ obj["aaData"][objLength-1][2]
													+ 'Kmph</td></tr>'
													+ '<tr><td align=""><B>Current Location:</B></th><td><div id="location"></div></td></tr></table>'
													+ '</div>'
													+ '</div>'
													+ '</div>'
										});// End of marker defination
								/*google.maps.event.addListener(marker,'mouseover',(function(marker, j) {
									return function() {
										if (infowindow) {
											infowindow.close();
											}
										infowindow.setContent(this.info);
										infowindow.open(map,this);
										AllDevices.get_reverse_geocode(this.latitude,this.longitude);
									}
								})(marker, j));
				google.maps.event.addListener( map, 'click', function() { 
				    infowindow.open( null, null ); 
				} );*/
								interval = setInterval(function() { toggleMarker() }, 1000);

							    function toggleMarker() {
							      if (marker.getVisible()) {
							        marker.setVisible(false);
							      } else {
							        marker.setVisible(true);
							      }
							    }
								//marker.setAnimation(google.maps.Animation.BOUNCE);
								markers.push(marker);
						for(var i=0;i<busstopid.length;i++){
							$("#"+busstopid[i]).html("000");
							
						}

								
							},
						error : function(xhr, errmsg) {
							getCordinates(DEVICE_ID, ID, startDate, endDate, value, schedule_name);
					    	//alert("No values found..!!");
					    	}
						// Response Check End
						});
			/*}, 10000);*/

}
var tripstartaccdist;
function getCordinatesFromMarker(DEVICE_ID, ID, startdate, enddate, value,completedflag, schedule_name, result) {
	//alert("hiii");
	$("#msg_schedule").html("Processing !!!");
	chkdeviceid=DEVICE_ID;
	$.ajax({
		url : "actgetCordinates.action",
		data : "DEVICE_ID=" + DEVICE_ID + "&ID=" + ID + "&startdate="
				+ startdate + "&enddate=" + enddate + "&value=" + value,
		method : "post",
		success : function(response) {
			$("#msg_schedule").html("");
			//alert(response);
			var infowindow = new google.maps.InfoWindow();
			poly = new google.maps.Polyline({
				strokeColor : '#009933',
				strokeOpacity : 3.0,
				strokeWeight : 3,
				icons : [ {
					repeat : '70px', // CHANGE THIS VALUE TO CHANGE THE
										// DISTANCE BETWEEN ARROWS
					icon : iconsetngs,
					offset : '100%'
				} ]
			});
			poly.setMap(map);
			var path = poly.getPath();

			var info = path;
			var j, i = 0;

			// Parsing response
			var obj = jQuery.parseJSON(response);
			var distancearr=[];
			var busstoppointsarray=[];
			if (obj != null && obj["aaData"].length!=0 ) {
				tripstartaccdist=0;
				
				var objLength = obj["aaData"].length;
				tripstartaccdist = parseFloat(obj["aaData"][objLength - 1][10]);
				//alert(tripstartaccdist);
				currbuslocdist= parseInt(obj["aaData"][0][10]);
				console.log("tripstartaccdist==>"+tripstartaccdist+"currbuslocdist=====>"+currbuslocdist);
				for(var m=0;m<result.routepointlen-1;m++){
					if(result.routepoint[m][7]!=2 && result.routepoint[m][7]!=13){
					var from = new google.maps.LatLng(result.routepoint[m][9], result.routepoint[m][10]);
	        	  	var to   = new google.maps.LatLng(obj["aaData"][objLength - 1][0], obj["aaData"][objLength - 1][1]);
	        	  	//alert(from.toSource()+"======"+to.toSource());
	        	  	var d = google.maps.geometry.spherical.computeDistanceBetween(from, to);
	        	  	d=Math.round(d);
	        	  	distancearr.push(d);
	        	  	busstoppointsarray.push(result.routepoint[m][1]);
				}
				}
				//alert(distancearr);
				var maxdistval=findmin(distancearr);
				var mindistbusstopid=busstoppointsarray[distancearr.indexOf(maxdistval)];
				var startcalc=0;
				ttldistroute=0;
				for(var m=0;m<result.routemaplen-1;m++){
					if((result.routemap[m][1]==mindistbusstopid) || startcalc==1){
						startcalc=1;
					}
					if(startcalc==1){
						ttldistroute=ttldistroute+(result.routemap[m][7]/1000);
					}
				}
				console.log("startstopid==>"+mindistbusstopid+" ttldistroute"+ttldistroute)
				totaltravdist=(currbuslocdist-tripstartaccdist)*1.852;
				var timetoreach=0;
				
				timetoreach=(ttldistroute-totaltravdist)/obj["aaData"][objLength - 1][2];
				$('#a').html("");
				var calctime=Math.round(timetoreach);
				var min=0;
				var hour=0;
				if(calctime!=0){
					hour=Math.floor(calctime/60);
					min=Math.round(calctime%60);
				}
				var today = new Date();
				today.setHours(today.getHours()+hour);
				today.setMinutes(today.getMinutes()+min);
				if(ttldistroute>totaltravdist){
				//$('#a').html(hour+" hour "+min+" mins");
				}
				//console.log("distance:"+(ttldistroute-totaltravdist)+"speed:"+obj["aaData"][objLength - 1][2]+"avg speed==>"+obj["aaData"][objLength - 1][11]+"calctime==>"+calctime);
				if(objLength > 0 ){
				for (i = objLength - 1; i > 0; i--) {

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

						imgicon = 'assets/images/BusIconRedFlag.png';
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
						imgicon = 'assets/images/bus-map-icon.png';
					}
					var latLong = new google.maps.LatLng(obj["aaData"][i][0],
							obj["aaData"][i][1]);
					var time = obj["aaData"][i][3].split(" ");

					
					// Push if Last Geocode Same...
					if (obj["aaData"][i][0] != '0.00000000') {
						// markers.push(marker);

						// alert(latLong);
						path.push(latLong);
						
					}

				}
				// End of for loop
				if (!completedflag) {
					getCordinates(DEVICE_ID, obj["aaData"][objLength - 1][5],
							'', '', 'Running1', schedule_name);
				}
				if (objLength > 0) {
					latLong = new google.maps.LatLng(obj["aaData"][0][0],
							obj["aaData"][0][1]);
					map.setCenter(latLong);
				}
				
			}
			}
			// AllDevices.changeIcon();
			// var flightPlanCoordinates = [];
			for (mrk = 0; mrk < markers.length; mrk++) {
				flightPlanCoordinates.push(markers[mrk].position);
			}

			var flightPath = new google.maps.Polyline({
				path : flightPlanCoordinates,
				icons : [ {
					repeat : '70px', // CHANGE THIS VALUE TO CHANGE THE
										// DISTANCE BETWEEN ARROWS
					icon : iconsetngs,
					offset : '100%'
				} ],

				strokeColor : '#009933',
				strokeOpacity : 1.0,
				strokeWeight : 2
			});
			// flightPath.setMap(null);
			// flightPath.setMap(map);
			//flightPatharr.push(flightPath);
			//setFlightMap();
		}
	});
}
function getRefresh() {
	if ($("#refreshID").is(':checked')) {
		// plotVehicle();
	} else {
		clearInterval(intervalID);
	}
}

function findmin(array)
{
     var min = array[0];
         var a = array.length;
   for (counter=0;counter<a;counter++)
   {
      if (array[counter] < min)
      {
        min = array[counter];
      }
   }
return min;

}