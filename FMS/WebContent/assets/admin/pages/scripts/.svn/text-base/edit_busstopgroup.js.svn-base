

// This example displays a marker at the center of Australia.
// When the user clicks the marker, an info window opens.
var busString; var map;var tempk,tempa;var markers=[];
var clickcounter=0;
var tempbusarr=[];
function putdata(id){
	tempbusarr.push(parseInt(id));
	//alert(tempbusarr);
}
$(function(){
	
	var mapOptions = {
		    zoom: 15,
		    center: new google.maps.LatLng("12.97928309", "77.57205963")
		  };
	map = new google.maps.Map(document.getElementById('gmap_marker'), mapOptions);
	$("#gmap_marker").css("height", "500px");
	google.maps.event.trigger(map, "resize");
	size=new google.maps.Size(0, 0);
	 image = {
		    url: 'images/beachflag.png',
		    // This marker is 20 pixels wide by 32 pixels tall.
		    size: new google.maps.Size(1, 1),
		    // The origin for this image is 0,0.
		    origin: new google.maps.Point(0,0),
		    // The anchor for this image is the base of the flagpole at 0,32.
		    anchor: new google.maps.Point(0, 32)
		  };
	 
	/* $('#gmap_marker').click(function() {
		  $('#gmap_marker').reveal();
		  google.maps.event.trigger(map, 'resize');
		       });*/
		         
	 

});
function initialize(result,center,zoom2, colo) {
	//alert('in initilize');
	center=center.split(",");
	//alert(center);
	//map.setCenter(new google.maps.LatLng(center[0],center[1]));//{lat: center[0], lng: center[1]});
	//new google.maps.Marker({position: {lat: center[0], lng: center[1]}, map: map});
	//map.setZoom(30)
	var zoom1=0;
	zoom1=zoom2;	
  var myLatlng = new google.maps.LatLng( center[0],center[1]);
 
  busString=result;
  /*var mapOptions = {
    zoom: zoom1,
    center: myLatlng
  };*/
  
  function getPopup(lating,latnew,langnew){
	  //alert('aaaaaaaaa'+lating);
	  //document.getElementById("stop").value=lating;
	  getStopsDetails(lating,latnew,langnew);
  }
  
  
//alert(contentString);
 
//  
  
  
  
  
  

  var infowindow = new google.maps.InfoWindow({
      content: "aaaa"
  });

  var locations=busString.split("@@@");
  
  var source="",dest="",name="",devicecode="",desc="",nameka="",loc="",status="",icon="",area="",landmark="",icon1="",mcolor="",dir="",id="",pointtype="";
  
 // alert('dloc'+locations.length);
 
  for (i = 0; i < locations.length; i++) {
	 // alert('dloc'+locations.length);
	 
	  var dloc=locations[i].split("|");
	  //dloc[v]==="undefined"
		 // alert("pre=="+dloc[12]);
	  if(locations.length==2){
		  map.setZoom(17);
		 // map.setCenter(new google.maps.LatLng(source,dest));
	  }
	  for(var v=0;v<dloc.length;v++){
		
		   if(dloc[v]==="null" || dloc[v]=="null"){
	       //	alert("null");
			   dloc[v]=" ";
	       }
		   if(dloc[v]==="undefined" || dloc[v]=="undefined"){
		       	//alert("undefinrd");
				   dloc[v]=" ";
		       }
		   if(dloc[v]==""){
		       //	alert("undefinrd");
				   dloc[v]=" ";
		       }
		   
	  }
	 // alert("post=="+dloc[12]);
	  //for(var j=0;j<dloc.length;j++){
	      
	      name =dloc[0];
		  source =dloc[1];
		  dest =dloc[2];
		  id=dloc[3];
		  devicecode=dloc[4];
		  nameka=dloc[5];
		  desc=dloc[6];
		  status=dloc[7];
		  loc=dloc[8];
		 // mcolor=dloc[8]);
		  
		  
		  
		  
		  dir=dloc[10];
		  landmark=dloc[11];
		  area=dloc[12];
		  pointtype=dloc[14];
		  //pointtype
		 
		//  alert("dloc[8]"+mcolor+dloc[8]);
	 // }
	 // alert('loc'+loc);
		 // alert()
		  switch (status) {
          case "New":
          case "NEW":
              icon = "red";
              break;
          case "Inactive":
              icon = "yellow";
              break;
          case "Approved":
              icon = "green";
              break;
          /*case "Connecter":
          icon = "brown";
          break;*/
      }
		  
		  if(dloc[13]=='Y'){
			  //alert(dloc[13]);
			  icon = "blue"; 
		  }
		 // alert(mcolor);
		  if(dloc[14]=="2"){
			  icon = "brown";  
		  }
		  if(dloc[14]=="13"){
			  icon = "grey";  
		  }
		  if(dloc[14]=="18"){
			  icon = "chart";  
		  }
		  if(dloc[14]=="99"){
			  icon = "bmtc";  
		  }
		 // alert(icon);
		  if(colo=="col"){
				icon = "Bus-Icon";  
			}  
      icon1 = "assets/images/"+icon+".png";
      //alert()
      if(pointtype!=2){
      marker = new google.maps.Marker({
          position: new google.maps.LatLng(source, dest),
          map: map,
          animation: google.maps.Animation.DROP,
          draggable: false,
          icon: new google.maps.MarkerImage(icon1),
          title: name+" ("+$('<div/>').html(nameka).text()+")"+" Towards:"+dir,
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
		  area :area

      });
      markers.push(marker);
      }
//var tbl='<div class="portlet-body flip-scroll">'+
//	'<label class="col-md-3 control-label">KAlias 1</label><div class="col-md-9"><input class="form-control input-sm" placeholder="Default Input" type="text" readonly></div></div>';
      var tbl="<b>aaaaa</b>";
     
      google.maps.event.addListener(marker, 'click', (function(marker, i) {
          return function() {
             // alert('inside click'+marker.landmark);
        	 // alert('inside click'+id);
        	  arrangesrno();
        	  tempbusarr=tempbusarr.toString().split(",");
        	  //alert(tempbusarr);
        	  //alert(marker.id);
        	 // alert(jQuery.inArray( marker.id, tempbusarr ));
        	  if(jQuery.inArray( marker.id, tempbusarr )!=-1 ){
    			  alert("Bus Stop "+marker.name+" Already Added To List");
    			  
    			  return false;
    		  }
        	  tempbusarr.push(marker.id);
        	  if(clickcounter==0){
        		  if($('#group_type').val()=="1"){
        			  //$('#bus_stop_name').val(marker.name);
            		  //$('#start_stop_id').val(marker.id);
        			  $('#listbusstop tr').last().after('<tr id="rm'+clickcounter+'"><td class="srno"></td><td>'+marker.name+'</td><td class="busstopid" style="display:none">'+marker.id+'</td><td><button class="close" aria-hidden="true" data-dismiss="alert" type="button" onclick="removerow('+marker.id+')" ></button></td></tr>');  
        		  }else{
        		 // $('#bus_stop_name').val(marker.name);
        		 // $('#start_stop_id').val(marker.id);
        		  $('#listbusstop tr').last().after('<tr id="rm'+clickcounter+'"><td class="srno"></td><td>'+marker.name+'</td><td class="busstopid" style="display:none">'+marker.id+'</td><td><button class="close" aria-hidden="true" data-dismiss="alert" type="button" onclick="removerow('+marker.id+')" ></button></td></tr>');
        		  }
        	  }else{
        		  $('#listbusstop tr').last().after('<tr id="rm'+clickcounter+'"><td class="srno"></td><td>'+marker.name+'</td><td class="busstopid" style="display:none">'+marker.id+'</td><td><button class="close" aria-hidden="true" data-dismiss="alert" type="button" onclick="removerow('+marker.id+')" ></button></td></tr>');
        	  }
        	  arrangesrno();
        	  clickcounter++;
             
          }
      })(marker, i));
      /*
      google.maps.event.addListener(marker, 'dragend', (function(marker, i) {
          return function() {
      //google.maps.event.addListener(marker,'dragend',function(event) {
        	  var point = marker.getPosition();
        	  map.panTo(point);
    	 // alert(point.lat()+" "+point.lng()+"======"+marker.id);
    	//  alert(event["ma"].toSource());
       // alert(event.id+"========"+event.latLng.lat());
        	  $('#latitude').val(point.lat());
        	  $('#longitude').val(point.lng());
        getPopup(marker.id,point.lat(),point.lng());
        infowindow.setContent(''+
        		'<div class="portlet-body form">'+
				'<form class="form-horizontal" role="form" name="deleteform" action="DeleteBusStop.action" method="post">'+
		        '<div class="form-body">'+
			    //'<div class="form-group">'+
		  '<input type="hidden" name="busid" id="busid" value="'+marker.id+'"/>'+ 
		  '<input type="hidden" name="requestType" id="requestType" value="map"/>'+ 
		  '<div class="table-responsive" style="color:#000000"><table class="table table-hover"><tr>'+
		  '<th>Device Code:</th><td>'+marker.devicecode+'</td></tr>'+
		  '<tr><th align="right">Bus Stop Id:</th><td>'+marker.id+'</td></tr>'+
  	      '<tr><th align="right">Bus Stop Name:</th><td>'+marker.name+'</td></tr>'+
  	    '<tr><th align="right">Bus Stop Name(kannada):</th><td>'+marker.nameka+'</td></tr>'+
  	  '<tr><th align="right">Towards:</th><td>'+marker.dir+'</td></tr>'+
  	'<tr><th align="right">Landmark:</th><td>'+marker.landmark+'</td></tr>'+
  	'<tr><th align="right">Area:</th><td>'+marker.area+'</td></tr>'+
  	'<tr><th align="right">Status:</th><td>'+marker.status+'</td></tr>'+
    	      '<tr><td>'+
			      '<a data-toggle="modal" class="btn blue" role="button" href="#myModal1" >EDIT </a></td>'+
			     ' <td><a data-toggle="modal" class="btn yellow" role="button" href="#myModal3">DELETE </a></td></tr></table>'+
			      //'<button type="submit" class="btn green">DELETE</button>'+
    	      '</div>'+
    	      '</form>'+
    	      '</div>'+
    	      '</div>'+
    	      '');
        infowindow.open(map, marker);
        
          }
      //});
      })(marker, i));*/
  }
  google.maps.event.addListener(marker, 'click', function() {
      map.setZoom(7);
      map.setCenter(marker.getPosition());
      //alert(marker.getPosition());

  });
  
  /*google.maps.event.addListener(map, "dbclick", function(event) {
	    var lat = event.latLng.lat();
	    var lng = event.latLng.lng();
	    // populate yor box/field with lat, lng
	    alert("Lat=" + lat + "; Lng=" + lng);
	    //putMarker(lat,lng);
	});*/
/*  google.maps.event.addListener(marker, "dragend", function (e) { 
	  var lat = e.latLng.lat();
	    var lng = e.latLng.lng();
	    alert("Lat=" + lat + "; Lng=" + lng);
	    getStopsLoad(lat,lng);
	    // populate yor box/field with lat, lng
	    //alert("Lat=" + lat + "; Lng=" + lng);
    });*/
  
  google.maps.event.addListener(map, 'dragend', getDragedPoint);

  function getDragedPoint()
  {
      /*geocoder.geocode({'latLng': map.getCenter()}, function(results, status)
      {
        if (status == google.maps.GeocoderStatus.OK)
        {*/
	  //alert("hiii before loop"+tempk+"="+map.getCenter().k+"========="+tempa+"="+map.getCenter().toSource());
	  if((tempk!=map.getCenter().lat()) && (tempa!=map.getCenter().lng())){
	  //alert("hiii before"+tempk+"="+map.getCenter().k+"========="+tempa+"="+map.getCenter().A);
	  getStopsLoad(map.getCenter().lat(),map.getCenter().lng());  
	  tempk=map.getCenter().lat() ;
	  tempa=map.getCenter().lng();
	  //alert("hiii after"+tempk+"="+map.getCenter().k+"========="+tempa+"="+map.getCenter().A);
  }
	 // alert("hiii");
         // map.getCenter() + "\r\n" + document.getElementById('area').value;
       /* }
      });*/
  }

  
}

function edit(){
	//alert('hiiii');
	href="#myModal1"
}
function clearMarkers() {
	  setAllMap(null);
	}
function setAllMap(map) {
	  for (var i = 0; i < markers.length; i++) {
	    markers[i].setMap(map);
	  }
	}
function removerow(id){
	$("#listbusstop").find("td:contains('" + id  + "')").parent().remove();
	//alert(tempbusarr);
	//alert("hiii"+tempbusarr.indexOf('24386'));
	tempbusarr.splice(tempbusarr.indexOf('" + id  + "'), 1);

	//alert(tempbusarr);
}

function arrangesrno(){
	var countersr=0;
	$(".srno").each(function() {
		countersr++;
		$(this).html(countersr);
		});	
}




