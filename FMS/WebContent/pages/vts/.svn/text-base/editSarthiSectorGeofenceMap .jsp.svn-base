

<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <style>
      html, body, #map-canvas {
        height: 95%;
        margin: 0px;
        padding: 0px
      }
      .controls {
        margin-top: 16px;
        border: 1px solid transparent;
        border-radius: 2px 0 0 2px;
        box-sizing: border-box;
        -moz-box-sizing: border-box;
        height: 32px;
        outline: none;
        box-shadow: 0 2px 6px rgba(0, 0, 0, 0.3);
      }

      #pac-input {
        background-color: #fff;
        padding: 0 11px 0 13px;
        width: 400px;
        font-family: Roboto;
        font-size: 15px;
        font-weight: 300;
        text-overflow: ellipsis;
      }

      #pac-input:focus {
        border-color: #4d90fe;
        margin-left: -1px;
        padding-left: 14px;  /* Regular padding-left + 1. */
        width: 401px;
      }

      .pac-container {
        font-family: Roboto;
      }

      #type-selector {
        color: #fff;
        background-color: #4d90fe;
        padding: 5px 11px 0px 11px;
      }

      #type-selector label {
        font-family: Roboto;
        font-size: 13px;
        font-weight: 300;
      }
      .botton {
	padding: 5px;
	background-color: #dcdcdc;
	border: 1px solid #666;
	color: #000;
	text-decoration: none;
}
}

    </style>
    <title>Places search box</title>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=places"></script>
    <script>
// This example adds a search box to a map, using the Google Place Autocomplete
// feature. People can enter geographical searches. The search box will return a
// pick list containing a mix of places and predicted search terms.
var map;
var plotedMap;
var firstcordinate;
var markers = [];
function initialize() {

//  var markers = [];
  var mapOptions = {
		    zoom: 13,
		    // Center the map on Bngalore
		    center: new google.maps.LatLng(12.97928309	,77.57205963)
		  };
     var geoString = window.opener.document.getElementById('geodatasarthi').value;
	if (geoString != "") {
		map = new google.maps.Map(document.getElementById('map-canvas'),
				mapOptions);
		var resparr;
		//alert(geoString);
		resparr = geoString.split("(");
		var arr = resparr[1].split(",");
		var polyCoords = [];
		//LINESTRING(12.94839988907909 77.59574890136719,12.948065296022738 77.61754989624023,12.959441207845698 77.61926651000977,12.968641932818803 77.61051177978516)
		for ( var x = 0; x < arr.length-1; x++) {
			var finalarr = arr[x].split(" ");
			polyCoords[x] = new google.maps.LatLng(finalarr[0], finalarr[1]);
			if(x==0){
				map.setCenter(new google.maps.LatLng(finalarr[0],finalarr[1]));
			}
			}
		plotedMap = new google.maps.Polygon({
			paths : polyCoords,
			strokeColor : '#FF0000',
			strokeOpacity : 1.0,
			strokeWeight : 3,
			
		});
		plotedMap.setMap(map);
	//	alert("Hii");
	} else {

		map = new google.maps.Map(document.getElementById('map-canvas'),
				mapOptions);
			var polyOptions = {
			strokeColor : '#000000',
			strokeOpacity : 1.0,
			strokeWeight : 3
		};
		poly = new google.maps.Polyline(polyOptions);
		poly.setMap(map);
		google.maps.event.addListener(map, 'click', addLatLng);
		//alert("Hii");
	}
	//	  map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);

  /* var defaultBounds = new google.maps.LatLngBounds(
		  new google.maps.LatLng(12.97928309	,77.57205963),
		  new google.maps.LatLng(12.97928309	,77.57205963));
  map.fitBounds(defaultBounds); */

  // Create the search box and link it to the UI element.
  var input = /** @type {HTMLInputElement} */(
      document.getElementById('pac-input'));
  map.controls[google.maps.ControlPosition.TOP_CENTER].push(input);

  var searchBox = new google.maps.places.SearchBox(
    /** @type {HTMLInputElement} */(input));
//alert(searchBox.toSource());
  // [START region_getplaces]
  // Listen for the event fired when the user selects an item from the
  // pick list. Retrieve the matching places for that item.
   var polyOptions = {
			    strokeColor: '#000000',
			    strokeOpacity: 1.0,
			    strokeWeight: 3
			  };
			  poly = new google.maps.Polyline(polyOptions);
			  poly.setMap(map);
  google.maps.event.addListener(map, 'click', addLatLng);
  google.maps.event.addListener(searchBox, 'places_changed', function() {
    var places = searchBox.getPlaces();

    if (places.length == 0) {
      return;
    }
    for (var i = 0, marker; marker = markers[i]; i++) {
      marker.setMap(null);
    }

    // For each place, get the icon, place name, and location.
    //markers = [];
    var bounds = new google.maps.LatLngBounds();
    for (var i = 0, place; place = places[i]; i++) {
      var image = {
        url: place.icon,
        size: new google.maps.Size(0, 0),
        origin: new google.maps.Point(0, 0),
        anchor: new google.maps.Point(17, 34),
        scaledSize: new google.maps.Size(0, 0)
      };

      // Create a marker for each place.
      var marker = new google.maps.Marker({
        map: map,
        icon: image,
        title: place.name,
        position: place.geometry.location
      });
      markers.push(marker);

     // markers.push(marker);

      bounds.extend(place.geometry.location);
    }

    map.fitBounds(bounds);
    map.setZoom(13);
  });
  // [END region_getplaces]

  // Bias the SearchBox results towards places that are within the bounds of the
  // current map's viewport.
  google.maps.event.addListener(map, 'bounds_changed', function() {
    var bounds = map.getBounds();
    searchBox.setBounds(bounds);
  });
}

google.maps.event.addDomListener(window, 'load', initialize);
function closeWin() {
	this.close();                                                  // Closes the new window
}

var geoString="";
var path="";
function addLatLng(event) {

	  path = poly.getPath();
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
	  markers.push(marker);
	  //alert("Hii1");
	}
function clearMapForm() {
	//window.opener.document.getElementById('geodatarevenue').value="";

window.opener.document.getElementById('geodatasarthi').value = "";
       
         for ( var i = 0; i < markers.length; i++) {
        	// alert("dfdf");
        	 markers[i].setMap(null);
    	}
        poly.setMap(null);
        var polyOptions = {
    			strokeColor : '#000000',
    			strokeOpacity : 1.0,
    			strokeWeight : 3
        }
        poly = new google.maps.Polyline(polyOptions);
		poly.setMap(map);
		
		plotedMap.setMap(null); 
		geoString="";
	//initialize();

}

    </script>
    <style>
      #target {
        width: 345px;
      }
    </style>
  </head>
  <body>
    <input id="pac-input" class="controls" type="text" placeholder="Search Box">
    								<div id="map-canvas"></div>
    <form class="form-horizontal" role="form" action="#" method="post">
							
							
									
									
								
							
							 <div style="position: center">
								<center>
									<button type="button" 
										onclick="submitMapForm()" class="botton">Save</button>
										<button type="button" class="botton"
										onclick="clearMapForm()" id="clearMap">Clear
										Map</button>
									<button aria-hidden="true" data-dismiss="modal" class="botton" onclick="closeWin()"
										>Cancel</button>
								</center>

							</div> 
							

						</form>
						<script>
						function submitMapForm(){
							geoString=geoString.substring(0,(geoString.length-1));
							//alert(geoString[0]);
							var finalString="LINESTRING("+geoString+","+firstcordinate+")";
							if(geoString!=""){
							//$('#geodata').val(finalString);
							window.opener.document.getElementById('geodatasarthi').value=finalString;
							}
						
						this.close();
						}
							</script>
  </body>
</html>

