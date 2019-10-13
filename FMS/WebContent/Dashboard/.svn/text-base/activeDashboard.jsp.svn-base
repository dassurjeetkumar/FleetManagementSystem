
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places,drawing"></script>
<%-- <script src="assets/vts/js/vts.js" type="text/javascript"></script> --%>
<script src="assets/etm/activeetmchart.js"
	type="text/javascript"></script>


<script type="text/javascript" src="//www.google.com/jsapi"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
	
	</script>
<script>
function getAllDevicesDetails() {
	alert("yess");	
	$('#Alldevice').show();	
		$('#alert_dashBoard_data_all').dataTable({
			"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
			// per
			// page
			// values
			// here
			],
			// set the initial value
			"iDisplayLength" : 5,
			"sAjaxSource" : "getforallDeviceData.action",
			"sPaginationType" : "bootstrap",
			"bDestroy" : true,

			"oLanguage" : {
				"sLengthMenu" : "_MENU_ records",
				"oPaginate" : {
					"sPrevious" : "Prev",
					"sNext" : "Next"
				}
			},
			"aoColumnDefs" : [ {
				'bSortable' : false,
				'aTargets' : [ 0 ]
			} ]
		});
		jQuery('#alert_dashBoard_data_all_wrapper .dataTables_filter input').addClass(
				"form-control input-small input-inline"); // modify
		// table
		jQuery('#alert_dashBoard_data_all_wrapper .dataTables_length select').addClass(
				"form-control input-xsmall input-inline"); // modify
		
	}



window.onload=getAllDevicesDetails;
</script>

	<input type="hidden" id='deviceid' name='deviceid' /> <input
		type="hidden" id='vehicleid' name='vehicleid' /> <input type="hidden"
		id='scheduleno' name='scheduleno' /> <input type="hidden"
		id='routeid' name='routeid' /> <input type="hidden" id='schedulename'
		name='schedulename' /> <input type="hidden" id='startpoint'
		name='startpoint' /> <input type="hidden" id='endpoint'
		name='endpoint' /> <input type="hidden" id='starttime'
		name='starttime' /> <input type="hidden" id='endtime' name='endtime' />
	<input type="hidden" id='tripstatus' name='tripstatus' /> <input
		type="hidden" id='dutydate' name='dutydate' /> <input type="hidden"
		id='id' name='id' />


	<div class="page-content-wrapper">
		<div class="page-content">
			<!-- <div class="row">
				<div class="col-md-12">
					<div class="portlet box red">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i><b>Alerts</b>
							</div>
							<div class="tools">
								<a class="collapse" id="vehicle_chart_alert" href="javascript:;">
								</a> <a class="config" data-toggle="modal" href="#portlet-config">
								</a> <a class="reload" href="javascript:;"> </a> <a class="remove"
									href="javascript:;"> </a>
							</div>
						</div>
						<div class="portlet-body" id="alert_vehicle_display_chart">
							<h4>ETM Alerts</h4>
							<div id="barchart_alert" class="chart"></div>

						</div>
					</div>
				</div>
			</div> -->
			<div class="row">
                    <div class="col-md-3">
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i><b>ACTIVE DEVICE </b>
							</div>
							<div class="tools">
								<a class="collapse" id="vehicle_chart_live" href="javascript:;">
								</a> <a class="config" data-toggle="modal" href="#portlet-config">
								</a> <a class="reload" href="javascript:;"> </a> <a class="remove"
									href="javascript:;"> </a>
							</div>
						</div>
						<div class="portlet-body" id="chart_live_display">
							<h4 id="totalVehicle"></h4>
							<p id="demo"></p>
							<div id="alertspie" class="chart"></div>
						</div>
					</div>
				</div> 
								
								<div class="col-md-12">
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption">
								<div style="width: 250px" id='vstatus'></div>
							</div>
							<div class="tools">
								<a href="javascript:;" class="reload"> </a> <a
									href="javascript:;" class="remove"> </a>
							</div>
						</div>
						
				
						
						<div class="portlet-body" id="vehicle" style="display: none">
							<table class="table table-striped table-bordered table-hover"
								id="alert_dashBoard_data">
								<thead>
									<tr>
										<th>Sr. No.</th>
										<th>Device No</th>
										<th>Depot Name</th>
										<th>Vehicle No</th>
<!-- 										<th></th> -->
<!-- 										<th>Vehicle No</th> -->
									</tr>
								</thead>
							</table>

						</div>
						
						
						<div class="portlet-body" id="Alldevice" style="display: none">
							<table class="table table-striped table-bordered table-hover"
								id="alert_dashBoard_data_all">
								<thead>
									<tr>
										<th>Sr. No.</th>
										<th>Device No</th>
										<th>Depot Name</th>
										<th>Vehicle No</th>
									</tr>
								</thead>
							</table>

						</div>
						
						
						<!-- <div class="portlet-body" id="vehicleOIB" style="display: none">
							<table class="table table-striped table-bordered table-hover"
								id="alert_dashBoardOIB_data">
								<thead>
									<tr>
										<th>Sr. No.</th>
										<th>Status</th>
										<th>Vehicle No</th>
										<th>Device ID</th>
										<th>Depot Name</th>
										<th>Speed Km/Hr</th>
										<th>Schedule No</th>
										<th>Shift</th>
										<th>Distance(Km)</th>
										<th>Internal Battery(Volt)</th>
										<th>External Battery(Volt)</th>
										<th>Signal Strength%</th>
										<th>Last Reported Time</th>
									</tr>
								</thead>
							</table>

						</div> -->
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var geocoder;
		var map;
		var places;
		function initialize() {
			// create the geocoder
			var mapOptions = {
				zoom : 13,
				// Center the map on Bngalore
				center : new google.maps.LatLng(12.97928309, 77.57205963)
			};
			map = new google.maps.Map(document.getElementById('gmap_basic'),
					mapOptions);
			//map1 = new google.maps.Map(document.getElementById('gmap_marker_division'), mapOptions);
			var polyOptions = {
				strokeColor : '#000000',
				strokeOpacity : 1.0,
				strokeWeight : 3
			};
			poly = new google.maps.Polyline(polyOptions);
			poly.setMap(map);
		}
	</script>
	<!-- END CONTENT -->
	<!-- TrackOnline Begins -->



<script>
	$(document).ready(function() {
		//loadActiveVehicles();
		//getAlertIntervalData();
		getDeviceDataDashboard();
	});
</script>
<script>
	google.maps.event.addDomListener(window, 'load', initialize);
	$("#mnu_header li").removeClass("classic-menu-dropdown active");
	$("#mnu_etm").addClass("classic-menu-dropdown active");
	$("#mnu_header li").addClass("classic-menu-dropdown");
	$("#etm_selected").addClass("selected");
	
</script>