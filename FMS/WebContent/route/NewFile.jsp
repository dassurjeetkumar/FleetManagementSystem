

<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <title>Simple Polylines</title>
    <style>
      html, body, #map-canvas {
        height: 100%;
        margin: 0;
        padding: 0;
      }

    </style>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true"></script>
    <script>
// This example creates a 2-pixel-wide red polyline showing
// the path of William Kingsford Smith's first trans-Pacific flight between
// Oakland, CA, and Brisbane, Australia.

function initialize() {
  var mapOptions = {
    zoom: 15,
    center: new google.maps.LatLng("12.9751205", " 77.5095556"),
    scaleControl: true,
    mapTypeId: google.maps.MapTypeId.TERRAIN
  };

  var map = new google.maps.Map(document.getElementById('map-canvas'),
      mapOptions);

  var flightPlanCoordinates = [
                              
  //                             new google.maps.LatLng(12.9751205,77.5095556),new google.maps.LatLng(12.9722932,77.50800520000007),
 //new google.maps.LatLng(12.9722932,77.50800520000007),new google.maps.LatLng(12.9664547,77.50509209999996),
/*new google.maps.LatLng(12.9664547,77.50509209999996),new google.maps.LatLng(12.9630926,77.5051522),
new google.maps.LatLng(12.9630926,77.5051522),new google.maps.LatLng(12.960457,77.50459279999995),
new google.maps.LatLng(12.960457,77.50459279999995),new google.maps.LatLng(12.9607672,77.50748829999998),
new google.maps.LatLng(12.9607672,77.50748829999998),new google.maps.LatLng(12.9610475,77.50973669999996),
new google.maps.LatLng(12.9610475,77.50973669999996),new google.maps.LatLng(12.9608622,77.51261790000001),
new google.maps.LatLng(12.9608622,77.51261790000001),new google.maps.LatLng(12.9591873,77.51671950000002),
new google.maps.LatLng(12.9591873,77.51671950000002),new google.maps.LatLng(12.9595838,77.51988389999997),
new google.maps.LatLng(12.9595838,77.51988389999997),new google.maps.LatLng(12.9593218,77.5215895),
new google.maps.LatLng(12.9593218,77.5215895),new google.maps.LatLng(12.9582885,77.52634250000006),
new google.maps.LatLng(12.9582885,77.52634250000006),new google.maps.LatLng(12.9583193,77.52882380000005),
new google.maps.LatLng(12.9583193,77.52882380000005),new google.maps.LatLng(12.9604927,77.52973240000006),
new google.maps.LatLng(12.9604927,77.52973240000006),new google.maps.LatLng(12.9600496,77.53127500000005),
new google.maps.LatLng(12.9600496,77.53127500000005),new google.maps.LatLng(12.9578725,77.53395969999997),
new google.maps.LatLng(12.9578725,77.53395969999997),new google.maps.LatLng(12.9535159,77.53662829999996),
new google.maps.LatLng(12.9535159,77.53662829999996),new google.maps.LatLng(12.9533255,77.54189139999994),
new google.maps.LatLng(12.9533255,77.54189139999994),new google.maps.LatLng(12.9558838,77.54593980000004),
new google.maps.LatLng(12.9558838,77.54593980000004),new google.maps.LatLng(12.9573711,77.55176329999995),
new google.maps.LatLng(12.9573711,77.55176329999995),new google.maps.LatLng(12.960338,77.55681630000004),
new google.maps.LatLng(12.960338,77.55681630000004),new google.maps.LatLng(12.963781,77.56833819999997),
new google.maps.LatLng(12.963781,77.56833819999997),new google.maps.LatLng(12.9643883,77.57651199999998),
new google.maps.LatLng(12.9643883,77.57651199999998),new google.maps.LatLng(12.9637851,77.58433070000001),
new google.maps.LatLng(12.9637851,77.58433070000001),new google.maps.LatLng(12.9681869,77.58944650000001),
new google.maps.LatLng(12.9681869,77.58944650000001),new google.maps.LatLng(12.9703764,77.59429799999998),
new google.maps.LatLng(12.9681869,77.58944650000001),new google.maps.LatLng(12.9703764,77.59429799999998),
new google.maps.LatLng(12.967338,77.5996149),new google.maps.LatLng(12.9698139,77.60299029999999),
new google.maps.LatLng(12.9698139,77.60299029999999),new google.maps.LatLng(12.9733252,77.60989059999997),
new google.maps.LatLng(12.9733252,77.60989059999997),new google.maps.LatLng(12.9739827,77.61289740000007), */
/* new google.maps.LatLng(12.9739827,77.61289740000007),
new google.maps.LatLng(12.9727245,77.62066170000003),
new google.maps.LatLng(12.9727245,77.62066170000003),new google.maps.LatLng(12.9750986,77.62547180000001),
new google.maps.LatLng(12.9750986,77.62547180000001),new google.maps.LatLng(12.9784301,77.62910910000005),
new google.maps.LatLng(12.9784301,77.62910910000005),new google.maps.LatLng(12.9781806,77.63211720000004),
new google.maps.LatLng(12.9784444,77.62910959999999),new google.maps.LatLng(12.978229,77.63212190000002),
new google.maps.LatLng(12.9782172,77.63698729999999),new google.maps.LatLng(12.9783513,77.64107620000004),
new google.maps.LatLng(12.9783513,77.64107620000004),new google.maps.LatLng(12.9778239,77.6466987),
new google.maps.LatLng(12.9778239,77.6466987),new google.maps.LatLng(12.973688,77.64699109999992),
new google.maps.LatLng(12.973688,77.64699109999992),new google.maps.LatLng(12.9724918,77.65111969999998),
new google.maps.LatLng(12.9724918,77.65111969999998),new google.maps.LatLng(12.9720188,77.65411089999998),
new google.maps.LatLng(12.9720188,77.65411089999998),new google.maps.LatLng(12.971601,77.6585533),
new google.maps.LatLng(12.971601,77.6585533),new google.maps.LatLng(12.9758891,77.65565979999997),
new google.maps.LatLng(12.9759163,77.65563410000004),new google.maps.LatLng(12.9770171,77.65476979999994),
new google.maps.LatLng(12.9770685,77.65479920000007),new google.maps.LatLng(12.9789971,77.65324809999993),
new google.maps.LatLng(12.9789971,77.65324809999993),new google.maps.LatLng(12.9799425,77.65442770000004),
new google.maps.LatLng(12.9799425,77.65442770000004),new google.maps.LatLng(12.9807705,77.65657899999997),
new google.maps.LatLng(12.9807705,77.65657899999997),new google.maps.LatLng(12.9856372,77.66139229999999),
new google.maps.LatLng(12.9856372,77.66139229999999),new google.maps.LatLng(12.9873191,77.66224349999993),
new google.maps.LatLng(12.9873191,77.66224349999993),new google.maps.LatLng(12.985618,77.66383250000001), */
new google.maps.LatLng(12.9793207,77.51069860000007),new google.maps.LatLng(12.9751205,77.5095556),
    						
  ];
  var flightPath = new google.maps.Polyline({
    path: flightPlanCoordinates,
    geodesic: true,
    strokeColor: 'red',
    strokeOpacity: 1.5,
    strokeWeight: 1
  });

  flightPath.setMap(map);
}

google.maps.event.addDomListener(window, 'load', initialize);

    </script>
  </head>
  <body>
    <div id="map-canvas"></div>
  </body>
</html>

