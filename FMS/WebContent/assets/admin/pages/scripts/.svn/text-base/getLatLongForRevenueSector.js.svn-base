var map;
var firstcordinate;
function initialize(){
	setTimeout(initialize1, 1000);
}
function initialize1() {
	
	 var mapOptions = {
			    zoom: 13,
			    // Center the map on Bngalore
			    center: new google.maps.LatLng(12.97928309	,77.57205963)
			  };

	 
	 
	 
			  map = new google.maps.Map(document.getElementById('gmap_marker_revenueSector'), mapOptions);
			  //map1 = new google.maps.Map(document.getElementById('gmap_marker_division'), mapOptions);
			  var polyOptions = {
			    strokeColor: '#000000',
			    strokeOpacity: 1.0,
			    strokeWeight: 3
			  };
			  
			  
			  
			  
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
				    map.setZoom(15);
				  });
			  poly = new google.maps.Polyline(polyOptions);
			  poly.setMap(map);
			  //alert("hii");
			  // Add a listener for the click event
			  google.maps.event.addListener(map, 'click', addLatLng);

			  //poly = new google.maps.Polyline(polyOptions);
			 // poly.setMap(map);
			  //alert("hii");
			  // Add a listener for the click event
			  //google.maps.event.addListener(map, 'click', addLatLng);

}

var geoString="";
function addLatLng(event) {

	  var path = poly.getPath();
		//alert(path.getString());
	  // Because path is an MVCArray, we can simply append a new coordinate
	  // and it will automatically appear.
	  path.push(event.latLng);
	  geoString+=event.latLng.lat()+" "+event.latLng.lng()+",";
	  if(path.getLength()==1){
		  firstcordinate=event.latLng.lat()+" "+event.latLng.lng();
		 // alert("firstcordinate"+firstcordinate);
	  }
		//alert(event.latLng.lat());
	  // Add a new marker at the new plotted point on the polyline.
	  var marker = new google.maps.Marker({
	    position: event.latLng,
	    title: '#' + path.getLength(),
	    map: map
	  });
	}

	/*google.maps.event.addDomListener(window, 'load', initialize);*/

function submitMapForm()
{
	geoString=geoString.substring(0,(geoString.length-1));
	//alert(geoString[0]);
	var finalString="LINESTRING("+geoString+","+firstcordinate+")";
	if(geoString!=""){
	$('#geodata').val(finalString);
	}
	geoString="";
}