<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<script src="assets/vts/js/routetracking.js"></script>

<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places,drawing,geometry"></script>

</head>

<script>
	var geocoder;
	var map;
	var places;
	function initializeMap123() {
		// create the geocoder
		geocoder = new google.maps.Geocoder();
		var tmpLatLng = new google.maps.LatLng(12.9746, 77.5946);
		<s:iterator value="list" id="list">
		tmpLatLng = new google.maps.LatLng(<s:property value="LAT" />,
				<s:property value="LONGITUDE" />);
		// make and place map maker.
		var indMarker = new google.maps.Marker({
			map : map,
			position : tmpLatLng,
			icon : 'assets/images/Bus-Icon.png',
			title : '#' + "<s:property value="DEVICE_ID" />",
		});
		contentString = "<s:property value="DEVICE_ID" />";
		google.maps.event.addListener(indMarker, 'click', function(
				contentString) {
			return function() {
				infowindow.setContent(contentString);//set the content
				infowindow.open(map, null);
			};
		});
		markers.push(indMarker);
		</s:iterator>
	}

	function getVehicleData(scheduleNo) {
		document.getElementById("scheduleTable").style.display = '';
		var selectedDate=document.getElementById("selecteddate").value;
		$('#scheduleTable')
				.dataTable(
						{
							"aLengthMenu" : [ [ 5, 15, 20, -1 ],
									[ 5, 15, 20, "All" ] // change
							],
							// set the initial value
							"iDisplayLength" : 20,
							"sAjaxSource" : "scheduleDetails.action?scheduleNo="+ scheduleNo+"&selectedDate="+selectedDate,
							"sPaginationType" : "bootstrap",
							"bDestroy" : true,
							"oLanguage" : {
								"sLengthMenu" : "_MENU_ records",
								"oPaginate" : {
									"sPrevious" : "Prev",
									"sNext" : "Next"
								}
							},
							"bFilter" : false,
							"bSelect" : false,
							"bPaginate" : false,
							"oLanguage" : {
								"sZeroRecords" : "",
								"sEmptyTable" : ""
							},
							"aoColumnDefs" : [ {
								'bSortable' : false,
								'aTargets' : [ 0, 1, 2, 3, 4 ]
							} ],
							"bLengthChange" : false,
							"sDom" : '<"top">rt<"bottom"flp><"clear">'
						})
				.wrap(
						"<div style='position:auto;overflow:auto;height:300px;width:100%'/>");
		jQuery('#scheduleTable_wrapper .dataTables_filter input').addClass(
				"form-control input-medium input-inline"); // modify table
		// search input
		jQuery('#scheduleTable_wrapper .dataTables_length select').addClass(
				"form-control input-xsmall input-inline");

	}
</script>

<div class="page-content-wrapper">
	<div class="page-content">
		<div class="row">
			<div class="col-md-6">
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
				<h3 class="page-title">
					Route Line Tracking 
				</h3>
				<!-- END PAGE TITLE & BREADCRUMB-->
			</div>
		</div>
		<div class="row">
			<div class="col-md-3">
				<div class="portlet box white">
					<div class="portlet-body form">
						<div class="form-group">
							<div class="col-md-8">
								<s:select list="routeList" id="routeList"
									name="orgchart.org_chart_id"
									cssClass="select2_category form-control" headerKey="0"
									headerValue="Route List" onchange="RouteTracking.init()"></s:select><b>Bus Stop Names</b>
							</div>
						</div>
					</div>
				</div>
				<div class="portlet box white">
					<div class="portlet-body form">
						<div class="form-group">							
							<div id="route_stops" style="overflow: scroll; height: 200px;">
							</div>
							<div class="portlet-body" id="vehicle_stop_list" style="overflow:scroll; height:200px;">
								<!-- <table class="table table-striped table-bordered table-hover"
									id="bus_stop_list" style="display: none">
									<thead>
										<tr>
											<th>Bus Stop Name</th>
										</tr>
									</thead>
								</table> -->
							
						</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-9">
				<div class="form-group" id="mapdiv">
					<table class="table table-striped">
						<tr>
							<td align="center" id="route_name_display" width="20%">&nbsp;</td>
							<td align="center" id="msg_schedule" style="" width="20%">&nbsp;</td>
							<td width="60%"><img alt="Start Of Route"
								src="assets/images/BusIconRedFlag.png"> Start Of Route <img
								alt="Bus Stop" src="assets/images/red-marker-icon.png">Bus Stop
								 <img alt="End Of Route"
								src="assets/images/bus-map-icon.png"> Vehicles</td>
						</tr>
						<tr>
							<td valign="top" colspan="3">
								<div class="portlet-body" style="height: 500px">
									<div id="route_map" class="gmaps"
										style="height: 100%; width: 100%;"></div>
								</div></td>
						</tr>
					</table>
					
				</div>
				
			</div>

		</div>
		<!-- <div class="row">
			<div class="col-md-12">
				<div class="form-group" id="mapdiv">
					<table class="table table-striped">
						<tr>
							<td align="center" id="route_name_display" width="35%">&nbsp;</td>
							<td align="center" id="msg_schedule" style="" width="35%">&nbsp;</td>
							<td width="30%">Start Of Route: <img alt="Start Of Route"
								src="assets/images/BusIconRedFlag.png"> Bus Stop: <img
								alt="Bus Stop" src="assets/images/red-marker-icon.png">
								End Route: <img alt="End Of Route"
								src="assets/images/bus-map-icon.png">
							</td>
						</tr>
						<tr>
							<td valign="top" colspan="3">
								<div class="portlet-body" style="height: 500px">
									<div id="route_map" class="gmaps"
										style="height: 100%; width: 100%;"></div>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div> -->
	</div>
</div>
