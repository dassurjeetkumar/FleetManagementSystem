    var encodedPolyLine = [];
	var busstopmarkers = [];
	var map;
	var mapOptions;
	var intervalID = null;
	var req_status = false;
	var bounds = null;
	function showBusMarkers() {
		setAllBusMap(map);
	}
	function setAllBusMap(map) {
		for ( var i = 0; i < busstopmarkers.length; i++) {
			busstopmarkers[i].setMap(map);
		}
	}
	function clearAllBusMap(map) {
		for ( var i = 0; i < busstopmarkers.length; i++) {
			busstopmarkers[i].setMap(null);
		}
		busstopmarkers = [];
	}
	var PlotCordinates = function() {

	}();

	function initialize() {
		
		mapOptions = {
			center : new google.maps.LatLng(12.9746, 77.5946),
			zoom : 13,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};
		map = new google.maps.Map(document.getElementById('route_map'),
				mapOptions);
		bounds = new google.maps.LatLngBounds();
	}
	
	var routeData;
	var routeData1;
	var startpoints = [];
	var endpoints = [];
	var path = "";
	var routeString = "";
	var parseData = "";
	var stpoint = "";
	var enpoint = "";
	var route_geoFence = "";
	var centerPoint = "";
	
	function getroute(routData){
		var rdata=routData;		
		var length = rdata["aaData"].length;
		var x = [];
		if (length == 0) {
			$("#route_name_display").html("Route Not Available !!!!");
		} else {
		for ( var r = 0; r < length; r++) {
			var pathofroute=rdata["aaData"][r]["3"];
			var route_no=rdata["aaData"][r]["4"];
			var latitudeFirstStop=rdata["aaData"][r]["1"];
			var longitudeFirstStop=rdata["aaData"][r]["2"];
			var icon1 = 'assets/images/BusIconRedFlag.png';
			
			x.push(pathofroute);
			$("#route_name_display").html(route_no +" Route is Plotted !!!!");			
			map.setZoom(15);
			map.setCenter(new google.maps.LatLng(latitudeFirstStop,longitudeFirstStop));
		
		marker = new google.maps.Marker(
				{
					position : new google.maps.LatLng(latitudeFirstStop, longitudeFirstStop),
					map : map,
					animation : google.maps.Animation.DROP,
					draggable : true,
					icon : new google.maps.MarkerImage(icon1),
					latitude : latitudeFirstStop,
					longitude : longitudeFirstStop,

					
				});
		}
		addEncodedPaths(x);
		}
	}
	
	function addEncodedPath(encodedPath, i, length, latLong, latLong_end,stopName, endStopName) {
		
		var iconsetngs = {
				path : google.maps.SymbolPath.FORWARD_CLOSED_ARROW,
				scale : 2,
				fillColor : "yellow",
				strokeColor : "green",
				strokeOpacity : 1,
				strokeWeight : 1
			};
		
		var path = google.maps.geometry.encoding.decodePath(encodedPath);
		
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
		polyline.setMap(map);
		map.setCenter(latLong);

	}

	function addEncodedPaths(encodedPaths) {
		for ( var i = 0, n = encodedPaths.length; i < n; i++) {
			addEncodedPath(encodedPaths[i]);
		}
	}
	function Route() {		
		var routeID = $("#routeListid").val();		
		initialize();
		clearAllBusMap(map);
		$("#msg_schedule").html(" ");

		 $.ajax({
			url : "getRouteonMap.action?routeid=" + routeID,
			dataType : 'json',
			datatype : "post",
			global : false,
			async : true,
			success : function(response) {	
			   getroute(response);
				return response;

			}
		}).responseText;
		
	}
	
	
	function getVehicle(vehicles){
		var vehicle=vehicles;
		//parseData = jQuery.parseJSON(routeData1);
		var length = vehicle["aaData"].length;
		
		var infowindow = new google.maps.InfoWindow();
		var icon1 = 'assets/images/bus-map-icon.png';
		if (length == 0) {
			$("#msg_schedule").html("Vehicles Not Available !!!!");

		} else {
			$("#msg_schedule").html("Vehicles Plotted !!!!");
			for (i = 0; i < encodedPolyLine.length; i++) {
				encodedPolyLine[i].setMap(null);
			}
			encodedPolyLine = [];

			  $("#route_info")
					.html(
							$("#route_info").html()
									+ "<table class='table table-hover'>"
									+ "<thead><tr>"
									+ "<th style='width1: 15px;'></th><th>Vehicle number</th>"
									+ "<th>Device Id</th><th>Schedule Name</th><th>Trip Number</th>"
									+"<th>Trip Time</th></tr><thead></table>"); 
			for ( var i = 0; i < length; i++) {

				 $("#route_info").html(
						$("#route_info").html()
								+ '<table class="table table-hover"><tr><td>'
								+ (i + 1) + '. </td>&nbsp&nbsp<td>'
								+ vehicle["aaData"][i]["0"]
								+ ' </td>&nbsp&nbsp<td>'
								+ vehicle["aaData"][i]["1"]
								+ '  </td>&nbsp&nbsp<td>'
								+ vehicle["aaData"][i]["2"]
								+ '  </td>&nbsp&nbsp<td>'
								+ vehicle["aaData"][i]["3"]
								+ '  </td>&nbsp&nbsp<td>'
								+ vehicle["aaData"][i]["4"]
								+ '</td> </tr></table>');
				 
				var source = vehicle["aaData"][i]["5"];
				var dest = vehicle["aaData"][i]["6"]; 
				
				//map.setCenter(source);
				marker = new google.maps.Marker(
						{
							position : new google.maps.LatLng(source, dest),
							map : map,
							animation : google.maps.Animation.DROP,
							draggable : true,
							icon : new google.maps.MarkerImage(icon1),
							latitude : source,
							longitude : dest,

							info : '<div class="portlet-body form">'
									+ '<div class="form-body" >'
									+ '<div class="table-responsive" style="color:#000000;width:200px"><table class="table table-hover"><tr>'
									+ '<td align=""><B>Vehicle Number:</B></th><td>'
									+ vehicle["aaData"][i]["0"]
									+ '</td></tr><tr>'
									+ '<td align=""><B>Schedule Number:</B></th><td>'
									+ vehicle["aaData"][i]["2"]
									+ '</td></tr></table>' + '</div>'
									+ '</div>' + '</div>' 
						});
				google.maps.event.addListener(marker, 'mouseover', function() {
					infowindow.setContent(this.info);
					infowindow.open(map, this);
				});
				google.maps.event.addListener(marker, 'mouseout', function() {
					infowindow.setContent(this.info);
					infowindow.open(map, this);
				});
				google.maps.event.addListener(map, 'click', function() {
					infowindow.open(null, null);
				});
				busstopmarkers.push(marker);
				showBusMarkers();
 
			}
		}
	}

	function vehicledetails() {
		clearAllBusMap(map);
		var routeID = $("#routeListid").val();
		var distanceid = $("#distanceid").val();
		$("#route_info").html(' ');			
		 $.ajax({
			url : "getRoutebunchInfo.action?routeid1="+routeID+"&distanceid="+distanceid,
			dataType : 'json',
			datatype : "post",
			global : false,
			async : true,
			success : function(response) {
				getVehicle(response)
				return response;
			}
		}).responseText;
		
	}
	function clearMarkers() {
		setAllMap(null);
	}
	function setAllMap(map) {
		for ( var i = 0; i < marker.length; i++) {
			marker[i].setMap(map);
		}
	}
	
	