var map;
var firstcordinate;
function initialize(){
	setTimeout(initialize1, 1000);
}
function initialize1() {
	var mapOptions = {
		zoom : 13,
		// Center the map on Bngalore
		center : new google.maps.LatLng(12.97928309, 77.57205963)
	};
	
	var plotedMap;
	var geoString = $('#geodatarevenue').val();
	if (geoString != "") {
		map = new google.maps.Map(document.getElementById('edit-revenue-map'),
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
			}
		plotedMap = new google.maps.Polygon({
			paths : polyCoords,
			strokeColor : '#FF0000',
			strokeOpacity : 1.0,
			strokeWeight : 3,
			
		});
		plotedMap.setMap(map);
	} else {

		map = new google.maps.Map(document.getElementById('edit-revenue-map'),
				mapOptions);
			var polyOptions = {
			strokeColor : '#000000',
			strokeOpacity : 1.0,
			strokeWeight : 3
		};
		poly = new google.maps.Polyline(polyOptions);
		poly.setMap(map);
		google.maps.event.addListener(map, 'click', addLatLng);

	}
	
}
var geoString = "";

function clearMapForm() {
	$('#geodatarevenue').val("");
	
	geoString="";
	var mapOptions = {
		zoom : 13,
		// Center the map on Bngalore
		center : new google.maps.LatLng(12.97928309, 77.57205963)
	};

	map = new google.maps.Map(document.getElementById('edit-revenue-map'),
			mapOptions);
	var polyOptions = {
		strokeColor : '#000000',
		strokeOpacity : 1.0,
		strokeWeight : 3
	};
	poly = new google.maps.Polyline(polyOptions);
	poly.setMap(map);
	google.maps.event.addListener(map, 'click', addLatLng);
}

function addLatLng(event) {

	var path = poly.getPath();
	path.push(event.latLng);
	if(path.getLength()==1){
		  firstcordinate=event.latLng.lat()+" "+event.latLng.lng();
		 // alert("firstcordinate"+firstcordinate);
	  }
	geoString += event.latLng.lat() + " " + event.latLng.lng() + ",";
	var marker = new google.maps.Marker({
		position : event.latLng,
		title : '#' + path.getLength(),
		map : map
	});
	
}

function submitMapForm() {
	/*if(geoString!=""){*/
	geoString = geoString.substring(0, (geoString.length - 1));
	var finalString="LINESTRING("+geoString+","+firstcordinate+")";
	if (geoString != "") {
		$('#geodatarevenue').val(finalString);
	}
	/*var divId=document.getElementById("myModal1");
		divId.style.display='none';*/
		geoString = "";
	/*}else{
		alert("Please Select GeoFence");
		$('.myModal1').show();
		return false;
	}*/
}
//google.maps.event.addDomListener(window, 'load', initialize);