var map;
var zoomlevel=25;
var clickcounter=0;
$(function(){
	 var markers = [];

	var mapOptions = {
		    zoom: 15,
		    center: new google.maps.LatLng("12.97928309", "77.57205963")
		  };
	map = new google.maps.Map(document.getElementById('gmap_marker'), mapOptions);
	//$("#gmap_marker").css("height", "500px");
	google.maps.event.trigger(map, "resize");
	
	var input = /** @type {HTMLInputElement} */(
		      document.getElementById('pac-input'));
	
		  map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

		  var searchBox = new google.maps.places.SearchBox(
		    /** @type {HTMLInputElement} */(input));

		  // [START region_getplaces]
		  // Listen for the event fired when the user selects an item from the
		  // pick list. Retrieve the matching places for that item.
		  google.maps.event.addListener(searchBox, 'places_changed', function() {
		    var places = searchBox.getPlaces();

		    if (places.length == 0) {
		      return;
		    }
		    for (var i = 0, marker; marker = markers[i]; i++) {
		      marker.setMap(null);
		    }

		    // For each place, get the icon, place name, and location.
		    markers = [];
		    var bounds = new google.maps.LatLngBounds();
		    for (var i = 0, place; place = places[i]; i++) {
		      var image = {
		        url: place.icon,
		        size: new google.maps.Size(71, 71),
		        origin: new google.maps.Point(0, 0),
		        anchor: new google.maps.Point(17, 34),
		        scaledSize: new google.maps.Size(0,0)
		      };

		      // Create a marker for each place.
		      var marker = new google.maps.Marker({
		        map: map,
		        icon: image,
		        title: place.name,
		        position: place.geometry.location
		      });
		      
		     // markers.push(marker);

		      bounds.extend(place.geometry.location);
		      
		    }

		    map.fitBounds(bounds);
		    map.setZoom(25);
		  });
		  // [END region_getplaces]

		  google.maps.event.addListener(map, 'bounds_changed', function() {
			    var bounds = map.getBounds();
			    searchBox.setBounds(bounds);
			  });
			

	
	/*google.maps.event.addListener(map, 'zoom_changed', function() {
	     zoomLevel = map.getZoom();
	     alert(zoomLevel);
	   // map.setCenter(myLatLng);
	   // infowindow.setContent('Zoom: ' + zoomLevel);
	  });*/
	 
	 

});

function initialize() {
  //var myLatlng = new google.maps.LatLng(	12.97928309	,77.57205963);
 /* var mapOptions = {
    zoom: 13,
    center: myLatlng
  }
  var map = new google.maps.Map(document.getElementById('gmap_marker'), mapOptions);*/
	google.maps.event.addListener(map, 'zoom_changed', function() {
		zoomlevel = map.getZoom();
	    //alert(zoomlevel);
	   // map.setCenter(myLatLng);
	   // infowindow.setContent('Zoom: ' + zoomLevel);
	  });
	
  google.maps.event.addListener(map, "click", function(event) {
	    var lat = event.latLng.lat();
	    var lng = event.latLng.lng();
	   // alert("hiiii"+zoomlevel);
	    map.setZoom(15);
	    // populate yor box/field with lat, lng
	  //  alert("Lat=" + lat + "; Lng=" + lng);
	    if(clickcounter==0){
	    putMarker(lat,lng);
	    }
	    clickcounter++;
	});
  
  

}

function putMarker(lat,lng){
	 var myLatlng = new google.maps.LatLng(	lat,lng);
	

	 var infowindow = new google.maps.InfoWindow({
	      content: "aaaa"
	  });
	  var mapOptions = {
	    zoom: 15,
	    center: myLatlng
	  }
	 // var map = new google.maps.Map(document.getElementById('gmap_marker'), mapOptions);

	  
	  
	  var marker = new google.maps.Marker({
	      position: myLatlng,
	      map: map,
	      draggable: true,
	      title: 'New Stop'
	  });
	  for (i = 0; i < 1; i++) {
		  
		  google.maps.event.addListener(marker, 'click', (function(marker, i) {
	          return function() {
	             // alert('inside click');
	        	 // alert('inside click'+id);
	        	  var point = marker.getPosition();
	        	  map.panTo(point);
	        	  map.setCenter(new google.maps.LatLng(	point.lat(),point.lng()));
	        	 // alert(zoomlevel);
	        	  map.setZoom(zoomlevel);
	        	 // alert(point.lat()+"======="+point.lng());
	              document.getElementById("latitude").value=point.lat();
	              document.getElementById("longitude").value=point.lng();
	        	 // getPopup(marker.id);
	              infowindow.setContent(''+
	            	      '<div class="portlet-body form">'+
							'<form class="form-horizontal" role="form" name="deleteform" action="DeleteBusStop.action" method="post">'+
					        '<div class="form-body">'+
						    //'<div class="form-group">'+
					
					  '<input type="hidden" name="requestType" id="requestType" value="map"/>'+ 
					  '<div class="table-responsive" style="color:#000000"><table class="table table-hover">'+
	          	      '<tr><th>Latitude:</th><td>'+point.lat()+'</td></tr>'+
	          	      '<tr><th>Longitude:</th><td>'+point.lng()+'</td></tr>'+
	          	     
	          	      '<tr><td>'+
					      '<a data-toggle="modal" class="btn blue" role="button" href="#myModal1" >CREATE </a></td>'+
					     ' <td><a data-toggle="modal" class="btn yellow" role="button" onclick="reloadPage()">DELETE </a></td></tr></table>'+
					      //'<button type="submit" class="btn green">DELETE</button>'+
	          	      '</div>'+
	          	      '</form>'+
	          	      '</div>'+
	          	      '</div>'+
	          	      '');
	              infowindow.open(map, marker);
	             
	          }
	      })(marker, i));
		  
		  google.maps.event.addListener(marker, 'dragend', (function(marker, i) {
	          return function() {
	           //   alert('inside click');
	        	 // alert('inside click'+id);
	        	  var point = marker.getPosition();
	        	  map.panTo(point);
	        	  map.setCenter(point);
	        	  map.setZoom(zoomlevel);
	        	 // alert(point.lat()+"======="+point.lng());
	              document.getElementById("latitude").value=point.lat();
	              document.getElementById("longitude").value=point.lng();
	        	 // getPopup(marker.id);
	              infowindow.setContent(''+
	            	      '<div class="portlet-body form">'+
							'<form class="form-horizontal" role="form" name="deleteform" action="DeleteBusStop.action" method="post">'+
					        '<div class="form-body">'+
						    //'<div class="form-group">'+
					
					  '<input type="hidden" name="requestType" id="requestType" value="map"/>'+ 
					  '<div class="table-responsive" style="color:#000000"><table class="table table-hover">'+
	          	      '<tr><th>Latitude:</th><td>'+point.lat()+'</td></tr>'+
	          	      '<tr><th>Longitude:</th><td>'+point.lng()+'</td></tr>'+
	          	     
	          	      '<tr><td>'+
					      '<a data-toggle="modal" class="btn blue" role="button" href="#myModal1" >EDIT </a></td>'+
					     ' <td><a data-toggle="modal" class="btn yellow" role="button" onclick="reloadPage()">DELETE </a></td></tr></table>'+
					      //'<button type="submit" class="btn green">DELETE</button>'+
	          	      '</div>'+
	          	      '</form>'+
	          	      '</div>'+
	          	      '</div>'+
	          	      '');
	              infowindow.open(map, marker);
	             
	          }
	      })(marker, i));
		  }
	  google.maps.event.addListener(marker, "dblclick", function (e) { 
		 // zoomlevel=map.getZoom();
         // alert("hiii");
          //href="#myModal3";
       });
	  
	 
}

google.maps.event.addDomListener(window, 'load', initialize);
