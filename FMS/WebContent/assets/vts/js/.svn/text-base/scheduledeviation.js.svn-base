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


var busString;
var map, ren, ser;
var ren=[];
var polyline=[];
var directionResult = [];
var directionsDisplay;
var wayA = [];
var wayB = [];
var tempk,tempa;
var drArr = [];
var drsArr = [];
var pArr = [];
var drArrcnt=0;
var pArrcnt=0;
var temp=[];
var tempbusstop=[];
var tempnum=[];
var tempid="";
var flag="";
var image;
var z=1;var y=1;var v=1;
var flightPathpath;
var encodeString;
var b="b";
var a="a";
var fare="fare";
var substage1="substage";
var prestopid="";
var tempbuusstop=[];
var frmbusid="";
var tobusid="";
var cntrl=0;
var markers=[];
var encodedPaths=[];
var origins="";
var destination="";
var ori_busids;
var ori_tran_busids;



$(function() {

	mapOptions = {

		center : new google.maps.LatLng(12.9746, 77.5946),
		zoom : 13,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};
	map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
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
				success : function(response) {
					response = $.parseJSON(response);
					var length = response["aaData"].length;
					for (i = 0; i < length; i++) {
						if (response["aaData"][i][3] === deviceid) {
							$("#schNumber").html(response["aaData"][i][1]);
							break;
						}
					}
					if ($("#schNumber").html() == "") {
						$("#schNumber").html("Not Mapped");
					}
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
					/*if (markers[i].vehicledirection > 0
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
					}*/
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
//					if (markers[i].vehicledirection > 0
//							&& markers[i].vehicledirection <= 90) {
//						markers[i].icon = "assets/images/bus_stop_right.png";
//					} else if (markers[i].vehicledirection > 90
//							&& markers[i].vehicledirection <= 180) {
//						markers[i].icon = "assets/images/bus_stop_down.png";
//					} else if (markers[i].vehicledirection > 180
//							&& markers[i].vehicledirection <= 270) {
//						markers[i].icon = "assets/images/bus_stop_left.png";
//					} else if (markers[i].vehicledirection > 270
//							&& markers[i].vehicledirection <= 360) {
//						markers[i].icon = "assets/images/bus_stop_up.png";
//					}
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
			//clearMarkers();
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
				$("#route_stops").html( $("#route_stops").html() + "<br />" +
						  (count + 1) + ". " + dloc[0]);
				count++;
				}
				
			/*	if (laststopid == dloc[3]) {
					
					endstopcord = dloc[1] + "," + dloc[2];
				}*/
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
		},
		error : function(xhr, errmsg) {
			alert(errmsg);
		}
	});
}
// 863071013980609
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
		strokeWeight : 2,
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

function getRouteTrac(routeID,start_point,end_point,rparam)
{
	//var encodedPaths = [];//var cntrl=0;
	$.ajax({
	    type        : 'POST',
	    data: {
	    	routeid:  routeID
	        
	    },
	    url         :  'editRouteAjax1',
	   
	success : function(response) {
		// req_status = false;
		// return response;
		// RND
		result = JSON.parse(response);
		console.log(result);
		var bussopslist = "";
		points = result.routemap;
		if(result !=null){
			plot_edit_route(result,start_point,end_point);
			for(var i=0;i<result.routepointlen;i++){
                bussopslist=bussopslist+result.routepoint[i][1]+",";
                if(i==result.routepointlen-1){
                     //alert("hiiii");
                     bussopslist=bussopslist.substring(0, bussopslist.length-1);
                     getStopsForRoute(bussopslist,start_point,end_point);
                 }
            }
		// END
			
	}else{
		//$("#msg_schedule").html("Route Information Not Available !!!!");
	}
	}
});	
 return 1;
}




function initialize(result,center,zoom2, colo,originorderid,ori_trangitorder_id) {
	
	center=center.split(",");
	var zoom1=0;
	zoom1=zoom2;
	origins=originorderid;
	destinations=ori_trangitorder_id;
	var myLatlng = new google.maps.LatLng( center[0],center[1]);
	busString=result;
	 var mapOptions = {
			    zoom: zoom1,
			    center: myLatlng
			  };
	 var infowindow = null;
      
	 
	 
    var locations=busString.toString().split("@@@");
  
    var source="",dest="",name="",devicecode="",desc="",nameka="",loc="",status="",icon="",area="",landmark="",icon1="",mcolor="",dir="",id="",pointtype="", farestage="", busstationid="", busstoporder="";
  var j="0";
 
    for (i = 0; i < locations.length; i++) {
	  var dloc=locations[i].split("|");
	  for(var v=0;v<dloc.length;v++){
		
		   if(dloc[v]==="null" || dloc[v]=="null"){
	  	   dloc[v]=" ";
	       }
		   if(dloc[v]==="undefined" || dloc[v]=="undefined"){
				   dloc[v]=" ";
		       }
		   if(dloc[v]==""){
				   dloc[v]=" ";
		       }
		   
	  }
	      name =dloc[0];
		  source =dloc[1];
		  dest =dloc[2];
		  id=dloc[3];
		  devicecode=dloc[4];
		  nameka=dloc[5];
		  desc=dloc[6];
		  status=dloc[7];
		  loc=dloc[8];
		  dir=dloc[10];
		  landmark=dloc[11];
		  area=dloc[12];
		  pointtype=dloc[14];
		  farestage=dloc[15];
		  busstationid=dloc[16];
		  busstoporder=dloc[18];
		  if(busstoporder==="undefined" || busstoporder=="undefined"){
			  busstoporder="";
	       }

		  if(busstoporder==="undefined" || busstoporder=="undefined"){
			  busstoporder="";
	       }
		  if(busstoporder >= ori_busids && busstoporder <= ori_tran_busids){
			
		  switch (status) {
          case "New":
              icon = "red";
              break;
          case "Inactive":
              icon = "yellow";
              break;
          case "Approved":
              icon = "green";
              break;
         
      }
		  if(dloc[13]=='Y'){
			  icon = "blue"; 
		  }
		  if(dloc[14]=="2"){
			  icon = "brown";  
		  }
		  if(dloc[14]=="13"){
			  icon = "grey";  
		  }
		  if(dloc[14]=="18"){
			  icon = "chart";  
		  }
		  
		if(colo=="col"){
			if(id==origins){
				
				icon = "start&endBusStop"; 
			}else if(id==destinations){
				icon = "red-flag-icon"; 
			}else{
			icon = "Bus-Icon";
		}
		}  
		
	  icon1 = "images/"+icon+".png";
      marker = new google.maps.Marker({
          position: new google.maps.LatLng(source, dest),
          title: "hiii",
          map: map,
          icon: new google.maps.MarkerImage(icon1),
          title: name,
          id : id,
          source :source,
		  dest :dest,
		  name : name,
		  devicecode :devicecode,
		  desc :desc,
		  nameka :nameka,
		  loc : loc,
		  status :status,
		  dir :dir,
		  landmark :landmark,
		  area :area,
		  pointtype :pointtype,
		  farestage:farestage,
		  busstationid : busstationid
      });
      if(colo!="col"){
    	  markers.push(marker);
		}  
     
      
     
      
      google.maps.event.addListener(marker, 'dragend', (function(marker, i) {
          return function() {
              	  var point = marker.getPosition();
        	  map.panTo(point);
          }
      })(marker, i));
	////		  
		  }
  }

  google.maps.event.addListener(map, 'dragend', getDragedPoint);
  
  function chng_dir(cntr){
	  google.maps.event.addListener(ren[cntr], 'directions_changed',
  			  function() {
    		//alert(cntr)
  		var strwaydata=save_waypoints(cntr);
      	 //alert(strwaydata);
      	strwaydata=strwaydata.replace(/"/g, '@');
      	strwaydata=strwaydata.replace(/,/g, '*');
  				//alert(y+"======="+ren[cntrl].getDirections().routes[0].overview_polyline);
  				$('#polyp'+(cntr+1)).val(ren[cntr].getDirections().routes[0].overview_polyline);
  				$('#editpath'+(cntr+1)).val(strwaydata);
  				$('#dkm'+(cntr+1)).val(parseFloat(ren[cntr].getDirections().routes[0].legs[0].distance.text.replace(" km","")));
  				$('#dm'+(cntr+1)).val(parseFloat(ren[cntr].getDirections().routes[0].legs[0].distance.value));
  				$('#tm'+(cntr+1)).val(parseFloat(ren[cntr].getDirections().routes[0].legs[0].duration.text.replace(" min","").replace("s","")));
  				$('#ts'+(cntr+1)).val(parseFloat(ren[cntr].getDirections().routes[0].legs[0].duration.value));
  				$('#tdg'+(cntr+1)).val(parseFloat(ren[cntr].getDirections().routes[0].legs[0].distance.text.replace(" km","")));
  				$('#tt'+(cntr+1)).val(parseFloat(ren[cntr].getDirections().routes[0].legs[0].duration.text.replace(" min","").replace("s","")));
  				var tempdis=0;
  				var temptm=0;
  				$(".dkm").each(function() {
  					tempdis=tempdis+parseFloat($(this).html());
  					});
  				tempdis=Math.round(tempdis);
  				$('#maptotdistm').html(tempdis);
  				$(".tm").each(function() {
  					temptm=temptm+parseFloat($(this).html());
  					});
                //alert($('#maptottimesectm').html()+"===="+ren[cnt].getDirections().routes[0].legs[0].duration.text.replace(" min","").replace("s",""));
  				//alert(( (parseFloat($('#maptottimesectm').html()))   +parseFloat(parseFloat(ren[cnt].getDirections().routes[0].legs[0].duration.text.replace(" min","").replace("s","")))).toFixed(2));
  				$('#maptottimesectm').html(temptm);
  				//$('#maptotdistm').html((parseFloat($('#maptotdistm').html())+parseFloat(ren[cntr].getDirections().routes[0].legs[0].distance.text.replace(" km",""))).toFixed(2));
               // $('#maptottimesectm').html(( (parseFloat($('#maptottimesectm').html()))   +parseFloat(parseFloat(ren[cntr].getDirections().routes[0].legs[0].duration.text.replace(" min","").replace("s","")))).toFixed(2));

  			
  	});
		ren[cntr].setMap(map);
  }
  
  function getDragedPoint()
  {
     
	  if((tempk!=map.getCenter().lat()) && (tempa!=map.getCenter().lng())){
	
	 // getStopsLoad(map.getCenter().lat(),map.getCenter().lng());  
	  tempk=map.getCenter().lat() ;
	  tempa=map.getCenter().lng();
	
  }
	
  }
}



function plot_edit_route(resulte,ori_busid,ori_tran_busid){
	ori_busids=ori_busid;
	ori_tran_busids=ori_tran_busid;
	edit_arr=resulte;
	 var result=resulte;
	 
	for(var s=0;s<result.routeplatrel;s++){
    	var temp="";
    	//alert("S==="+result.routeplatrellen);
		temp=temp+result.routeplatrel[s][1]+"|"+result.routeplatrel[s][3]+"|"+result.routeplatrel[s][4]+"|"+result.routeplatrel[s][5]+"|"+result.routeplatrel[s][6];
		platverarr.push(temp);
    	   $('#platformdetail tr').last().after('<tr id="1"><td  style="display: none;">'+result.routeplatrel[s][1]+""+result.routeplatrel[s][3]+""+result.routeplatrel[s][4]+""+result.routeplatrel[s][5]+""+result.routeplatrel[s][6]+""+'</td><td><input type="hidden" class="busplatformid" id="busplatformid'+platformcounter+'" value="'+result.routeplatrel[s][1]+'">    </td>              <td><input type="hidden" class="depotp" id="depotp'+platformcounter+'" value="'+result.routeplatrel[s][2]+'">    </td><td><input type="hidden" class="floorp" id="floorp'+platformcounter+'" value="'+result.routeplatrel[s][3]+'"></td>    </td><td><input type="hidden" class="bayp" id="bayp'+platformcounter+'" value="'+result.routeplatrel[s][4]+'"></td>    </td><td><input type="hidden" class="platformp" id="platformp'+platformcounter+'" value="'+result.routeplatrel[s][5]+'"></td>    </td><td><input type="hidden" class="servicep" id="servicep'+platformcounter+'" value="'+result.routeplatrel[s][6]+'"></td></tr>');
	       tableplatarr.push({id:result.routeplatrel[s][1],name:result.routeplatrel[s][1]+""+result.routeplatrel[s][3]+""+result.routeplatrel[s][4]+""+result.routeplatrel[s][5]+""+result.routeplatrel[s][6]+"",planame:result.routeplatrel[s][8],sername:result.routeplatrel[s][7],   depot:result.routeplatrel[s][2],floor:result.routeplatrel[s][3],bay:result.routeplatrel[s][4],platform:result.routeplatrel[s][5],service:result.routeplatrel[s][6]});
	       uniqueid++;
    }
	busstoplist=edit_arr.routepoint;
	maplist=edit_arr.routemap;

	//$("#pageLoader").show();
	var x=plot_route();
	
}

function plot_route(){
	if(cntrl== edit_arr.routepointlen){
		addEncodedPaths( encodedPaths );
	return;
	}
	//map.setCenter(new google.maps.LatLng(busstoplist[0][9],busstoplist[0][10]));
	map.setCenter(new google.maps.LatLng(busstoplist[0][9],busstoplist[0][10]));
	map.setZoom(14);
	var roadtype=$('#road').val();
	var cnt=cntrl-1;
	//alert("hiiii"+cntrl);
	//alert(maplist);
	var b="b";
	var a="a";
	if(cntrl==0){
		//alert(busstoplist);
		if(busstoplist[cntrl][7]==6){
			$('#bus_stops tr').last().after('<tr id="'+y+'"><td class="seq">'+y+'</td><td class="busstopid" id="busstopid'+y+'" style="display: none;">'+busstoplist[(cntrl)][1]+'</td><td class="cstage"></td><td class="busstopname" id="busstopname'+y+'">'+busstoplist[(cntrl)][6]+'</td><td>  <input type="checkbox" disabled="disabled" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"> </td><td>  <input type="checkbox"  disabled="disabled" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"> </td><td style="display: none;"><select class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td><a data-toggle="modal" class="btn white" role="button" href="#myModal1" onclick="addstop('+busstoplist[(cntrl)][1]+','+z+','+busstoplist[(cntrl)][8]+')">Add Platform</a></td> <td></td></td><td></td></tr>');
		}else{
			$('#bus_stops tr').last().after('<tr id="'+y+'"><td class="seq">'+y+'</td><td class="busstopid" id="busstopid'+y+'" style="display: none;">'+busstoplist[(cntrl)][1]+'</td><td class="cstage"></td><td class="busstopname" id="busstopname'+y+'">'+busstoplist[(cntrl)][6]+'</td><td>  <input type="checkbox"  disabled="disabled" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"> </td><td>  <input type="checkbox"  disabled="disabled" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"> </td><td style="display: none;"><select class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td></td></td><td></td></tr>');	
		}  
		tempbuusstop.push(busstoplist[(cntrl)][1]);
		  cntrl++;
		  reset_seq();
		  //arrange_footer();
		  y++;z++;z++;
		  plot_route();
	}else{
		console.log("cntrl====="+cntrl);
		var wp = [];
		var points=maplist;
		console.log("points[cntrl-1][14]"+points[cntrl-1][14]);
		var path=points[cntrl-1][14];
//		alert(points[0][12]);
    	path=path.replace(/@/g, '"');
    	path=path.replace(/\*/g, ',');
    	path=JSON.parse(path);
//    	alert("length=="+path.waypoints.length);
		for(var i=0;i<path.waypoints.length;i++){
			wp[i] = {'location': new google.maps.LatLng(path.waypoints[i][0], path.waypoints[i][1]),'stopover':false }
		}
		
		if(points[cntrl-1][15]=="GOOGLE"){
		
			encodedPaths.push(maplist[cntrl-1][13]);
			//alert("A===="+busstoplist[cntrl][7]);
				if(busstoplist[cntrl][7]==6){
					$('#bus_stops tr').last().after('<tr id="'+y+'"><td class="seq">'+y+'</td><td id="busstopid'+y+'" class="busstopid" style="display: none;">'+busstoplist[cntrl][1]+'</td><td class="cstage"></td><td  class="busstopname" id="busstopname'+y+'">'+busstoplist[cntrl][6]+'</td><td>  <input type="checkbox" disabled="disabled" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"> </td><td>  <input type="checkbox"  disabled="disabled" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"> </td><td style="display: none;"><select  class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td></td></td>    <td><a data-toggle="modal" class="btn white" role="button" href="#myModal1" onclick="addstop('+busstoplist[cntrl][1]+','+z+','+busstoplist[cntrl][8]+')">Add Platform</a></td>     <td></td></tr>');	
				}else{
					$('#bus_stops tr').last().after('<tr id="'+y+'"><td class="seq">'+y+'</td><td id="busstopid'+y+'" class="busstopid" style="display: none;">'+busstoplist[cntrl][1]+'</td><td class="cstage"></td><td  class="busstopname" id="busstopname'+y+'">'+busstoplist[cntrl][6]+'</td><td>  <input type="checkbox" disabled="disabled" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"> </td><td>  <input type="checkbox" disabled="disabled" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"> </td><td style="display: none;"><select  class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td></td></td><td></td></tr>');
				}
				if(busstoplist[cntrl][4]=="Y"){
          		  //alert("hiii");
          		  $('#fare'+y).prop('checked', true);
          	  }
          	if(busstoplist[cntrl][5]=="Y"){
          		  //alert("hiii");
          		  $('#substage'+y).prop('checked', true);
          	  }
          	
				tempbuusstop.push(busstoplist[(cntrl)][1]);
				$('#bus_stops_map tr').last().before('<tr id='+b+y+'><td colspan="7">'+maplist[cntrl-1][16]+' To '+maplist[cntrl-1][17]+'</td><td class="removefrmstop" style="display: none;">'+maplist[cntrl-1][1]+'</td><td class="removetostop" style="display: none;">'+maplist[cntrl-1][2]+'</td></tr>');
				$('#bus_stops_map tr').last().before('<tr id='+a+y+'><td class="dkm" id="dkm'+y+'">'+maplist[cntrl-1][7]/1000+'</td><td style="display: none;"  class="dm" id="dm'+y+'">'+maplist[cntrl-1][7]+'</td><td  class="tm" id="tm'+y+'">'+ Math.round(maplist[cntrl-1][10]/60)+'</td><td style="display: none;"  class="ts" id="ts'+y+'">'+maplist[cntrl-1][10]+'</td><td>'+maplist[cntrl-1][4]+'</td><td  class="tdg" id="tdg'+y+'">'+(maplist[cntrl-1][5]/1000)+'</td><td  class="tdv"  id="tdv'+y+'">'+maplist[cntrl-1][6]+'</td><td class="tt"  id="tt'+y+'">'+ Math.round(maplist[cntrl-1][10]/60)+'</td><td><input disabled="disabled" type="text" size="5" maxlength="6" class="tds" id="tds'+y+'" value="'+maplist[cntrl-1][18]+'"></td><td><input type="text" disabled="disabled" size="5" maxlength="6" class="tts" id="tts'+y+'" value="'+maplist[cntrl-1][19]+'"></td><td><input type="hidden" class="polyp"  id="polyp'+y+'" value="'+maplist[cntrl-1][13]+'"></td><td><input type="hidden" class="editpath" id="editpath'+y+'" value="'+maplist[cntrl-1][14]+'"></td> <td><input type="hidden" class="start_bus_stop_id"   id="start_bus_stop_id'+y+'" value="'+maplist[cntrl-1][1]+'"></td><td><input type="hidden" class="end_bus_stop_id"  id="end_bus_stop_id'+y+'" value="'+maplist[cntrl-1][2]+'"></td><td style="display: none;">'+maplist[cntrl-1][2]+'</td><td><input type="hidden" class="route_type"  id="route_type'+y+'" value="GOOGLE"></td><td class="removefrmstop" style="display: none;">'+maplist[cntrl-1][1]+'</td><td class="removetostop" style="display: none;">'+maplist[cntrl-1][2]+'</td></tr>');
				//$('#maptotdistm').html((   parseFloat($('#maptotdistm').html())    +   (maplist[7]/1000)).toFixed(2));
		       // $('#maptottimesectm').html(( parseInt($('#maptottimesectm').html())+  Math.round((maplist[10]/60) )) );
				drArrcnt++;
			
	             	cntrl++;
	             	//alert(cntrl);
	             	reset_seq();
	            	//alert(busstoplist[cntrl][4]);
	            	
	             //	arrange_footer();
	             	y++;z++;z++;
	             	plot_route();
	                 
		}else{
			////////////////////////connector///////////////////////////
//			alert("B===="+busstoplist[cntrl][7]);

			if(busstoplist[cntrl][7]==6){
				$('#bus_stops tr').last().after('<tr id="'+y+'"><td class="seq">'+y+'</td><td  class="busstopid" id="busstopid'+y+'" style="display: none;">'+busstoplist[cntrl][1]+'</td><td class="cstage"></td><td id="busstopname'+y+'">'+busstoplist[cntrl][6]+'</td><td>  <input type="checkbox" disabled="disabled" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"> </td><td>  <input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"> </td> </td><td style="display: none;"><select class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td><a data-toggle="modal" class="btn white" role="button" href="#myModal1" onclick="addstop('+busstoplist[cntrl][1]+','+z+','+busstoplist[cntrl][8]+')">Add Platform</a></td><td></td></tr>');
			}else{
				$('#bus_stops tr').last().after('<tr id="'+y+'"><td class="seq">'+y+'</td><td  class="busstopid" id="busstopid'+y+'" style="display: none;">'+busstoplist[cntrl][1]+'</td><td class="cstage"></td><td id="busstopname'+y+'">'+busstoplist[cntrl][6]+'</td><td>  <input type="checkbox" disabled="disabled" class="fare"  id="fare'+y+'" name="fare'+y+'" value="fare" onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"> </td><td>  <input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"> </td> </td><td style="display: none;"><select class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td></td><td></td></tr>');	
			}
			
			$('#bus_stops_map tr').last().before('<tr id='+b+y+'><td colspan="7">'+maplist[cntrl-1][16]+' To '+maplist[cntrl-1][17]+'</td><td class="removefrmstop" style="display: none;">'+maplist[cntrl-1][1]+'</td><td class="removetostop" style="display: none;">'+maplist[cntrl-1][2]+'</td></tr>');
			//alert("point type"+busstoplist[7]);
			tempbuusstop.push(busstoplist[(cntrl)][1]);
			if(busstoplist[cntrl][7]==2){
				 $('#fare'+y).prop('checked', false);
				 $('#substage'+y).prop('checked', false);
//				 document.getElementById("fare"+y).disabled = true;
//				 document.getElementById("substage"+y).disabled = true;
			}
			if(busstoplist[cntrl][4]=="Y"){
      		  //alert("hiii");
      		  $('#fare'+y).prop('checked', true);
      	  	}
			if(busstoplist[cntrl][5]=="Y"){
      		  //alert("hiii");
      		  $('#substage'+y).prop('checked', true);
      	  	}
			
			//$('#bus_stops_map tr').last().before('<tr id='+a+y+'><td id="dkm'+y+'">'+maplist[7]/1000+'</td><td style="display: none;" id="dm'+y+'">'+maplist[7]+'</td><td id="tm'+y+'">'+maplist[10]/60+'</td><td style="display: none;" id="ts'+y+'">'+maplist[10]+'</td><td><select id="road_type'+y+'">'+roadtype+'</select></td><td id="tdg'+y+'">'+maplist[5]+'</td><td id="tdv'+y+'">'+maplist[6]/1000+'</td><td id="tt'+y+'">'+maplist[10]/60+'</td><td><input type="textbox" id="tds'+y+'"></td><td><input type="textbox" class="tts" id="tts'+y+'"></td><td><input type="text" id="polyp'+y+'" value=""></td><td><input type="hidden" id="start_bus_stop_id'+y+'" value="'+maplist[1]+'"></td><td><input type="hidden" id="end_bus_stop_id'+y+'" value="'+maplist[2]+'"></td><td style="display: none;">'+maplist[2]+'</td><td><input type="hidden" id="route_type'+y+'" value="GOOGLE"></td></tr>');
			$('#bus_stops_map tr').last().before('<tr id='+a+y+'><td class="dkm" id="dkm'+y+'">'+maplist[cntrl-1][7]/1000+'</td><td style="display: none;"  class="dm" id="dm'+y+'">'+maplist[cntrl-1][7]+'</td><td  class="tm" id="tm'+y+'">'+ Math.round(maplist[cntrl-1][10]/60)+'</td><td style="display: none;"  class="ts" id="ts'+y+'">'+maplist[cntrl-1][10]+'</td><td><select  class="road_type" id="road_type'+y+'"  disabled="disabled">'+roadtype+'</select></td><td  class="tdg" id="tdg'+y+'">'+(maplist[cntrl-1][5])+'</td><td  class="tdv"  id="tdv'+y+'">'+maplist[cntrl-1][6]/1000+'</td><td class="tt"  id="tt'+y+'">'+  Math.round(maplist[cntrl-1][10]/60) +'</td><td><input type="textbox" disabled="disabled" class="tds" size="5"  maxlength="6" id="tds'+y+'" value="'+maplist[cntrl-1][18]+'"></td><td><input type="textbox" disabled="disabled" size="5" maxlength="6" class="tts" id="tts'+y+'" value="'+maplist[cntrl-1][19]+'"></td><td><input type="hidden" class="polyp"  id="polyp'+y+'" value="'+maplist[cntrl-1][13]+'"></td><td><input type="hidden" class="editpath" id="editpath'+y+'" value="'+maplist[cntrl-1][14]+'"></td> <td><input type="hidden" class="start_bus_stop_id"   id="start_bus_stop_id'+y+'" value="'+maplist[cntrl-1][1]+'"></td><td><input type="hidden" class="end_bus_stop_id"  id="end_bus_stop_id'+y+'" value="'+maplist[cntrl-1][2]+'"></td><td style="display: none;">'+maplist[cntrl-1][2]+'</td><td><input type="hidden" class="route_type"  id="route_type'+y+'" value="TRIMAX"></td><td class="removefrmstop" style="display: none;">'+maplist[cntrl-1][1]+'</td><td class="removetostop" style="display: none;">'+maplist[cntrl-1][2]+'</td></tr>');
			$('#maptotdistm').html((   parseFloat($('#maptotdistm').html())    +   (maplist[cntrl-1][7]/1000)).toFixed(2));
	        $('#maptottimesectm').html(( parseInt($('#maptottimesectm').html())+  Math.round((maplist[cntrl-1][10]/60)) ) );
	        drArr[cntrl-1]=polyline[cnt];
	        console.log(maplist[cntrl-1][13]);
	        encodedPaths.push(maplist[cntrl-1][13]);

		}
	}

	//}
	drArrcnt++;
	if(cntrl < edit_arr.routepointlen){
	cntrl++;
	}
	reset_seq();
	
	y++;z++;z++;
 	//alert(cntrl);
	
 	plot_route();
}



function check_stage1(){
	//alert("hiii");
	var farecnt=0;
	var farearr=[];
	var stage=0;
	$(".fare").each(function() {

			if($(this).is(':checked')){
				$('.cstage').html(farecnt);
				farearr.push("Y");
				 //farecnt++
			}else{
				$('.cstage').html(farecnt);
				farearr.push("N");	
				}
			
		
		});
	//alert(farearr);
	$(".cstage").each(function() {

		
		//alert(farecnt);
		$(this).html(farecnt);
			// fare_P.push("Y");
			//alert(farearr[stage]);
			if(farearr[stage]=="Y"){
				 farecnt++;
				// alert(farecnt);
			}
			
		
		stage++;
	
	});
	
}

function reset_seq(){
	//alert("in seq");
	var seqno=1;
	$(".seq").each(function() {
		$(this).html(seqno);
		seqno++;
			});	
}

function arrange_footer(){
	var tempdis=0;
		var temptm=0;
		$(".dkm").each(function() {
			tempdis=tempdis+parseFloat($(this).html());
			});
		tempdis=tempdis.toFixed(2);
		$('#maptotdistm').html(tempdis);
		$(".tm").each(function() {
			temptm=temptm+parseFloat($(this).html());
			});
		$('#maptottimesectm').html(temptm);
}

function clearMarkers() {
	  setAllMap(null);
	}
function setAllMap(map) {
	  for (var i = 0; i < markers.length; i++) {
	    markers[i].setMap(map);
	  }
	  getStopsReLoad("12","77");
	}

/*function addEncodedPath( encodedPath ) {
	//alert("in funct"+encodedPath);
	//alert("hiii");
	  
    var path = google.maps.geometry.encoding.decodePath( encodedPath );
    var polyline = new google.maps.Polyline({
       path: path,
       geodesic: true,
       strokeColor: '#ffffcc',
       strokeOpacity: 0.35,
       strokeWeight: 10,
       draggable: false
   });
   polyline.setMap( map ); 
 
}*/

function addEncodedPaths( encodedPaths ) {
	//alert("hiiiii"+encodedPaths.length);
   for( var i = 0, n = encodedPaths.length;  i < n;  i++ ) {
	   setTimeout(function(){}, 100);
	   addEncodedPath( encodedPaths[i] );
   }
}



function drawScheduleRouteDeviation(device_id, route_id, start_point, end_point,
		start_time, trip_status, duty_date, id, end_time, schedule_name) {
	document.getElementById("deviceid").value = device_id;
	document.getElementById("routeid").value = route_id;
	document.getElementById("schedulename").value = schedule_name;
	document.getElementById("startpoint").value = start_point;
	document.getElementById("endpoint").value =end_point;
	document.getElementById("starttime").value = start_time;
	document.getElementById("endtime").value = end_time;
	document.getElementById("tripstatus").value = trip_status;
	document.getElementById("dutydate").value = duty_date;
	document.getElementById("id").value = id;
	window.open("pages/vts/deviationtracking.jsp", "Deviation Tracking",
	"width=1500, height=1000");
}
function deviationTracking(device_id, route_id, start_point, end_point,
		start_time, trip_status, duty_date, id, end_time, schedule_name){
	var selectedDate = duty_date;
	duty_date = selectedDate;
	getRouteTrac(route_id,start_point,end_point,0);
	
		getCordinates(device_id,'0',duty_date + " " + start_time,
				duty_date + " " + end_time, 'ScheduleSubmit', false,
				schedule_name);
	
		return;
	}

function addEncodedPaths(encodedPaths) {
	for ( var i = 0, n = encodedPaths.length; i < n; i++) {
		setTimeout(function() {
		}, 100);
		addEncodedPath(encodedPaths[i]);
	}
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
function getCordinates(DEVICE_ID, ID, startDate, endDate, value,svalue, schedule_name) {
	
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
									strokeWeight : 2,
//									icons : [ {
//										repeat : '70px', // CHANGE THIS VALUE
//										// TO CHANGE THE
//										// DISTANCE BETWEEN
//										// ARROWS
//										icon : iconsetngs,
//										offset : '100%'
//									} ]
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
											imgicon = 'assets/images/red-marker-icon.png';
											//imgicon = google.maps.SymbolPath.FORWARD_CLOSED_ARROW;//imgicon = 'assets/images/red-marker-icon.png';//google.maps.SymbolPath.FORWARD_CLOSED_ARROW;// 'assets/images/red-marker-icon.png';
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
															+ '<tr><td  align=""><B>Schedule No:</B></th><td id="">'
															+ schedule_name
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

										google.maps.event.addListener(marker,'click',(function(marker, j) {
															return function() {
																if (infowindow) {
																	infowindow.close();
																	}
																infowindow.setContent(this.info);
																infowindow.open(map,this);
																AllDevices.get_reverse_geocode(this.latitude,this.longitude);
																AllDevices.get_schedule_number(this.deviceid);
															}
														})(marker, j));
										google.maps.event.addListener( map, 'click', function() { 
										    infowindow.open( null, null ); 
										} );
										j++;
										// Push if Last Geocode Same...
										if (obj["aaData"][i][0] != '0.00000000') {
											markers.push(marker);
											path.push(latLong);
										}

									}
									markers.reverse();
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
//								flightPath.setMap(null);
								//console.log(flightPlanCoordinates.length);
								//flightPlanCoordinates=flightPlanCoordinates.reverse();
								var flightPath = new google.maps.Polyline({
									path : flightPlanCoordinates,
									geodesic : true,
									strokeColor : 'green',
									strokeOpacity : 1.0,
									strokeWeight : 2,
									 icons : [ {
											repeat : '200px', // CHANGE THIS VALUE TO CHANGE THE
																// DISTANCE BETWEEN ARROWS
											icon : iconsetngs,
											offset : '100%'
										} ],
								       draggable: false
								});
								
								
								flightPath.setMap(map);
								setAllMap(map);
							}
						});
			}



function getCordinatesFromMarker(DEVICE_ID, ID, startdate, enddate, value,
		completedflag, schedule_name) {
	$("#msg_schedule").html("Processing !!!");
	$.ajax({
		url : "actgetCordinates.action",
		data : "DEVICE_ID=" + DEVICE_ID + "&ID=" + ID + "&startdate="
				+ startdate + "&enddate=" + enddate + "&value=" + value,
		method : "post",
		success : function(response) {
			$("#msg_schedule").html("");
			infowindow = new google.maps.InfoWindow();
			poly = new google.maps.Polyline({
				strokeColor : '#009933',
				strokeOpacity : 1.0,
				strokeWeight : 2
//				,
//				icons : [ {
//					repeat : '70px', // CHANGE THIS VALUE TO CHANGE THE
//										// DISTANCE BETWEEN ARROWS
//					icon : iconsetngs,
//					offset : '100%'
//				} 
//				]
			});
			poly.setMap(map);
			var path = poly.getPath();

			var info = path;
			var j, i = 0;

			// Parsing response
			var obj = jQuery.parseJSON(response);
			if (obj != null) {
				var objLength = obj["aaData"].length;

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

					/*
					 * var marker = new google.maps.Marker( { map : map,
					 * position : latLong, icon : imgicon, latitude :
					 * obj["aaData"][i][0], longitude : obj["aaData"][i][1],
					 * vehicledirection : obj["aaData"][i][8], info : '<div
					 * class="portlet-body form">' + '<div class="form-body">' + '<div
					 * class="table-responsive" style="color:#000000"><table
					 * class="table table-hover"><tr>' // + '<tr><td  align=""><B>ID:</B></th><td>' // +
					 * obj["aaData"][i][5] // + '</td></tr>' + '<tr><td  align=""><B>GPS
					 * Time:</B></th><td>' + time[0] + " " + time[1] + '</td></tr>' + '<tr><td align=""><B>Vehicle
					 * Number:</B></th><td>' + obj["aaData"][i][6] + '</td></tr>' + '<tr><td  align=""><B>Depot
					 * Name:</B></th><td>' + 'Depot 11' + '</td></tr>' + '<tr><td  align=""><B>Schedule
					 * No:</B></th><td>' + schedule_name + '</td></tr>' + '<tr><td align=""><B>Device
					 * Number:</B></th><td>' + obj["aaData"][i][4] + '</td></tr>' + '<tr><td align=""><B>Speed:</B></th><td>' +
					 * obj["aaData"][i][2] + 'Kmph</td></tr>' // + '<tr><td align=""><B>Latitude:</B></th><td>' // +
					 * obj["aaData"][i][0] // + '</td></tr>' // + '<tr><td align=""><B>Longitude:</B></th><td>' // +
					 * obj["aaData"][i][1] // + '</td></tr>' +'<tr><td align=""><B>Current
					 * Location:</B></th><td><div id="location"></div></td></tr></table>' + '</div>' + '</div>' + '</div>'
					 * });// End of marker defination
					 * 
					 * google.maps.event.addListener(marker, 'click',
					 * (function(marker, j) { return function() {
					 * infowindow.setContent(this.info); infowindow.open(map,
					 * this); AllDevices.get_reverse_geocode( this.latitude,
					 * this.longitude); } })(marker, j)); j++;
					 */
					// Push if Last Geocode Same...
					if (obj["aaData"][i][0] != '0.00000000') {
						// markers.push(marker);

						// alert(latLong);
						path.push(latLong);
						/*
						 * var step = 0; var numSteps = 5; //Change this to set
						 * animation resolution var timePerStep = 5; //Change
						 * this to alter animation speed var departure = new
						 * google.maps.LatLng( obj["aaData"][i][0],
						 * obj["aaData"][i][1]); var arrival = new
						 * google.maps.LatLng( obj["aaData"][i-1][0],
						 * obj["aaData"][i-1][1]); var interval =
						 * setInterval(function() { step += 1; if (step >
						 * numSteps) { clearInterval(interval); } else {
						 * console.log("imhere"); var are_we_there_yet =
						 * google.maps.geometry.spherical.interpolate(departure,arrival,step/numSteps);
						 * poly.setPath([departure, are_we_there_yet]); } },
						 * timePerStep);
						 */
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
			flightPatharr.push(flightPath);
			setFlightMap();
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
function drawRoute(route_id,startpoint,endpoint)
{
	var routeData = $.ajax({
		url : "getRoutefence.action?routeid=" + route_id + "&start_id="
				+ startpoint + "&end_id=" + endpoint,
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
}

//------------for playback-----------

function openWindowfor_playback(device_id, duty_date, route_id,start_id,end_id,start_time, end_time, schNo, tripNo, fromStop, endStop,
		schDep, gpsDep, schArr, gpsArr, schDistance) {
	
	document.getElementById("deviceid").value = device_id;
	document.getElementById("starttime").value = duty_date+" "+start_time;
	document.getElementById("endtime").value = duty_date+" "+end_time;
	document.getElementById("routeid").value = route_id;
	document.getElementById("startpoint").value = start_id;
	document.getElementById("endpoint").value = end_id;
	document.getElementById("schNo").value = schNo;
	document.getElementById("tripNo").value = tripNo;
	document.getElementById("fromStop").value = fromStop;
	document.getElementById("endStop").value = endStop;
	document.getElementById("schDep").value = schDep;
	document.getElementById("gpsDep").value = gpsDep;
	document.getElementById("schArr").value = schArr;
	document.getElementById("gpsArr").value = gpsArr;
	document.getElementById("schDistance").value = schDistance;
	window.open("pages/vts/schTripWisePlayback.jsp", "Deviation Tracking Playback",
	"width=1500, height=1000");
}
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
function getCordinatesFromMarker_playback(DEVICE_ID, ID, startdate, enddate, value, timeframe,route_id,startpoint,endpoint) {
	//drawRoute(route_id,startpoint,endpoint);
	getRouteTrac(route_id,startpoint,endpoint,0);
	var jqxhr = $.ajax({
		url : "actgetCordinates.action",
		data : "DEVICE_ID=" + DEVICE_ID + "&ID=" + ID + "&startdate="
				+ startdate + "&enddate=" + enddate + "&value=" + value,
		method : "post",
		global : false,
		async : false,
		success : function(response) {
			return response;
		}
	}).responseText;
	playbackData = [];
	time_date = [];
	time_hour = [];
	vehicle_num = [];
	phone_num = [];
	device_num = [];
	speed = [];
	totdistonsubmitpb = [];
	iconDirection = [];
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
			device_num.push(obj["aaData"][i][4]);
			speed.push(obj["aaData"][i][2]);
			iconDirection.push(obj["aaData"][i][8]);
			if(i!=0){
				totdistonsubmitpb.push(((obj["aaData"][i-1][13] - obj["aaData"][i][13]) /1000));
//				console.log(obj["aaData"][i-1][13]+"----"+obj["aaData"][i][13]+"----"+(obj["aaData"][i-1][13] - obj["aaData"][i][13]) * 1.60934);
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
		
	}
	
	if(objLength > 0){
	myLoop(DEVICE_ID, ID, startdate, enddate, value, timeframe);
	}
}
var idd = 0;
function myLoop(DEVICE_ID, ID, startdate, enddate, value, timeframe) {
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
					+ '<tr><td  align=""><B>Depot Name:</B></th><td>'
					+ 'Depot 11'
					+ '</td></tr>'
					+ '<tr><td  align=""><B>Schedule No:</B></th><td id="schNumber">'
					+ ''
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
					AllDevices.get_schedule_number(DEVICE_ID);
				}
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
						animate_it(playbackData[j],playbackData[j-1],j,playbackData.length,time_date[j],time_hour[j],vehicle_num[j],phone_num[j],device_num[j],speed[j],DEVICE_ID, ID, startdate, enddate, value,totdistonsubmitpb[j].valueOf(),iconDirection[j]);						
					}, j * (timeframe*1000));
				}(j));
		 }		
	}

}

function animate_it(dept, arr, startpoint, endpoint,date,hour,vehicle,phone,device,speed,DEVICE_ID, ID, startdate, enddate, value, totdistonsubmitpb, iconDirection) {
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
	if(speed>60){
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
					+ 'Depot 11'
					+ '</td></tr>'
					+ '<tr><td  align=""><B>Schedule No:</B></th><td id="schNumber">'
					+ ''
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
			+ totdistonsubmit + 'Kms'+'</td></tr>'
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
//------------end playback-----------