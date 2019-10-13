
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places,drawing,geometry"></script>
<%-- <script src="assets/vts/js/vts.js" type="text/javascript"></script> --%>
<script src="assets/etm/sosChart.js" type="text/javascript"></script>
<%-- assets/etm/sosChart.js --%>
	
<%-- <script src="assets/vts/js/scheduledeviation.js" type="text/javascript"></script> --%>
<%-- <script src="assets/vts/js/skipstop.js" type="text/javascript"></script> --%>
<%-- <script src="assets/vts/js/deviatedtracking.js" type="text/javascript"></script> --%>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

<form>
	<input type="hidden" id='deviceid' name='deviceid' />
	 <input	type="hidden" id='vehicleid' name='vehicleid' />
	 <input type="hidden" id='scheduleno' name='scheduleno' />
	 <input type="hidden" id='routeid' name='routeid' />
     <input type="hidden" id='schedulename' name='schedulename' /> 
     <input type="hidden" id='startpoint' name='startpoint' />
	 <input type="hidden" id='endpoint' name='endpoint' />
	 <input type="hidden" id='starttime' name='starttime' /> 
    <input type="hidden" id='endtime' name='endtime' />
	<input type="hidden" id='tripstatus' name='tripstatus' />
	 <input	type="hidden" id='dutydate' name='dutydate' /> 
	<input type="hidden" id='id' name='id' />
	<input type="hidden" id='depotName' name='depotName' />

	<input type="hidden" id='routeid' name='routeid' /> 
	<input type="hidden" id='routeno' name='routeno' /> 
	<input type="hidden" id='startpoint' name='startpoint' /> 
	<input type="hidden" id='endpoint' name='endpoint' /> 
	<input type="hidden" id='groupedData' name='groupedData' /> 

	<div class="page-content-wrapper">
		<div class="page-content">
			<div class="row">
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
							<h4>SOS Vehicle Alerts</h4>
							<div id="barchartsos_alert" class="chart"></div>

						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i><b>SOS Vehicle</b>
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
<!-- 							<h5 id="totalActiveVehicle"></h5> -->
<!-- 							<h5 id="totalNrdVehicle"></h5> -->
						</div>
					</div>
				</div>
				<div class="col-md-8">
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption">
<!-- 								<div style="width: 500px" id='vstatus'></div> -->
                               SOS Reports
							</div>
							<div class="tools">
								<a href="javascript:;" class="reload"> </a> <a
									href="javascript:;" class="remove"> </a>
							</div>
						</div>
						<div class="portlet-body" id="alert" style="display: none">
						<div style="text-align:center">
							<table class="table table-striped table-bordered table-hover"
								id="vehicle_dashBoard_data">
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Vehicle No.</th>
										<th>Alert Code</th>
										<th>Alert Desc</th>
										<th>Schedule No.</th>
										<th>Count</th>
									</tr>
								</thead>
							</table>
                          </div>
						</div>
						
						
						<!-- <div class="portlet-body" id="alert12" style="display: none">
							<div style="text-align:center">
							<table class="table table-striped table-bordered table-hover"
								id="depot_InData">
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Vehicle No.</th>
										<th>Device Id</th>
										<th>Depot No.</th>
									</tr>
								</thead>
							</table>
                           </div>
						</div> -->
						
						<!-- <div class="portlet-body" id="alert13" style="display: none">
							<div style="text-align:center">
							<table class="table table-striped table-bordered table-hover"
								id="stationary_vehicle_Data">
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Vehicle No.</th>
										<th>Start Time</th>
										<th>Device Id</th>
										<th>Schedule Name</th>
										<th>Places</th>
										<th>Depot No.</th>
										<th>Reason</th>
									</tr>
								</thead>
							</table>
                          </div>
						</div> -->
						
						<!-- <div class="portlet-body" id="alert14" style="display: none">
						<div style="text-align:center">
							<table class="table table-striped table-bordered table-hover"
								id="workshop_vehicle_Data">
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Vehicle No.</th>
										<th>Start Time</th>
										<th>Device Id</th>
										<th>Depot No.</th>
									</tr>
								</thead>
							</table>
                          </div>
						</div> -->

						<%-- <div class="portlet-body" id="alert1" style="display: none">
                        <div style="text-align:center">
							<div class="col-md-2">
								<s:select list="divisionlist1" id="divisionlistid1"
									cssClass="select2_category form-control" headerKey="0"
									headerValue="Depots" onchange="getDepoEarlyArrival()"></s:select>

							</div>
							<table class="table table-striped table-bordered table-hover"
								id="vehicle_earlyArrival_data">

								<thead>
									<tr>
										<th>Sr.No.</th>
<!-- 										<th>Status</th> -->
										<th>Vehicle No.</th>
										<th>Schedule No.</th>
										<th>End Bus Stop No./Name</th>
										<th>Shift No.</th>
										<th>Schedule Arrival Time</th>
										<th>Actual Arrival Time</th>
										<th>Time Difference</th>
										<th>Reason</th>
									</tr>
								</thead>
							</table>
                            </div>
						</div> --%>
					<%-- 	<div class="portlet-body" id="alert2" style="display: none">
							<div style="text-align:center">
							<div class="col-md-2">
								<s:select list="divisionlist1" id="divisionlistid2"
									cssClass="select2_category form-control" headerKey="0"
									headerValue="Depots" onchange="getDepoLateDeparture()"></s:select>
							</div>
							
							<table class="table table-striped table-bordered table-hover"
								id="vehicle_lateDeparture_data">
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Vehicle No.</th>
										<th>Schedule No.</th>
										<th>Start Stop No./Name</th>
										<th>Shift No.</th>
										<th>Schedule Departure Time</th>
										<!-- <th>Actual Departure time</th> -->
										<th>Time Difference</th>
										<th>Reason</th>
									</tr>
								</thead>
							</table>
						</div>
						</div> --%>
						<%-- <div class="portlet-body" id="alertLATE" style="display: none">
							<div style="text-align:center">
							<div class="col-md-2">
								<s:select list="divisionlist1" id="divisionlistid2"
									cssClass="select2_category form-control" headerKey="0"
									headerValue="Depots" onchange="getDepoLateDeparture()"></s:select>
							</div>
							
							<table class="table table-striped table-bordered table-hover"
								id="vehicle_lateDeparturedata_data">
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Vehicle No.</th>
										<th>Schedule No.</th>
										<th>Start Stop No./Name</th>
										<th>Shift No.</th>
										<th>Schedule Departure Time</th>
										<th>Actual Departure Time</th>
										<th>Time Difference</th>
										<th>Reason</th>
									</tr>
								</thead>
							</table>
						</div>
						</div> --%>
						<div class="portlet-body" id="alert3" style="display: none">
                        <div style="text-align:center">
							<table class="table table-striped table-bordered table-hover"
								id="sosAlert_data">
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Vehicle No.</th>
										<th>Depot No.</th>
										<th>Device Sim No.</th>
										<th>Schedule No.</th>
<!-- 										<th>Call time</th> -->
										<th>Total SoS Count</th>
										<th>Call Epbx</th>
										<th>Driver Token No.</th>
										<th>Conductor Token No.</th>
										<th>Conductor Mob No.</th>
										<th>Reason</th>
										
									</tr>
								</thead>
							</table>
                          </div>
						</div>
						<!-- <div class="portlet-body" id="alert4" style="display: none">
                         <div style="text-align:center">
                        
							<table class="table table-striped table-bordered table-hover"
								id="skipStopAlert_data">
								
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Vehicle No.</th>
										<th>Depot No.</th>
										<th>Schedule No.</th>
										<th>Driver Token No.</th>
										<th>Shift No.</th>
										<th>Total Stop Skip Count</th>
										<th>Reason</th>
									</tr>
								</thead>
							</table>
                        </div>
						</div> -->
					<!-- <div class="portlet-body" id="overSpeeding" style="display: none">
                      <div style="text-align:center">
							<table class="table table-striped table-bordered table-hover"
								id="overSpeeding_data">
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Vehicle No.</th>
										<th>Schedule No.</th>
										<th>Driver Name </th>
										<th>Driver Token No.</th>
										<th>Depot No.</th>
										<th>Location</th>
										<th>Date and Time</th>
										<th>Speed Km/Hr</th>
										<th>Sim Number</th>
										<th>Total Count</th>
									</tr>
								</thead>
							</table>
                         </div>
						</div> -->
						<!-- <div class="portlet-body" id="busbunching" style="display: none">
                            <div style="text-align:center">
							<table class="table table-striped table-bordered table-hover"
								id="busbunching_data">
								<thead>
									<tr>
										<th>Sr. No.</th>
										<th>Vehicle No</th>
										<th>Schedule No</th>
										<th>Trip No</th>
                                            <th>Sr.No.</th> 
									    	<th>Bus Registration No./Depot No.</th>													
											<th>Route No.</th>
 										   <th>Actual Alert Time</th>
										   <th>Vehicles Count</th>
										   <th id='mapshow'>Show On Map</th>
									</tr>
								</thead>
							</table>
                             </div>
						</div> -->
					<!-- 	<div class="portlet-body" id="HA" style="display: none">
                           <div style="text-align:center">
							<table class="table table-striped table-bordered table-hover"
								id="HA_data">
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Vehicle No.</th>
										<th>Depot No.</th>
										<th>Driver Name</th>
										<th>Waybill NO.</th>
										<th>Total Count</th>
										<th>Device Id</th>
										 <th>Time</th>
									</tr>
								</thead>
							</table>
                            </div>
						</div> -->
						<!-- <div class="portlet-body" id="HD" style="display: none">
                          <div style="text-align:center">
							<table class="table table-striped table-bordered table-hover"
								id="HD_data">
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Vehicle No.</th>
										<th>Depot No.</th>
										<th>Driver Name</th>
										<th>Driver Token</th>
										<th>Schedule Name</th>
										<th>Total Count</th>
										<th>Device Id</th>
										 <th>Time</th>
									</tr>
								</thead>
							</table>
                          </div>
						</div> -->
						<!-- <div class="portlet-body" id="alertDev" style="display: none">
							<div style="text-align:center">
							<table class="table table-striped table-bordered table-hover"
								id="vehicle_dashBoard_deviation_data">
								<thead>
									<tr>
		 								<th>Sr.No.</th>
		 								
		 								<th>Vehicle No.</th>
		 								<th>Depot No.</th>
										<th>Route No.</th>
										<th>Trip No.</th>
										<th>Start Time</th>
										<th>Shift</th>
										<th>Reason</th>
										
									</tr>
								</thead>
							</table>
                             </div>
						</div>  -->
						<!-- <div class="portlet-body" id="alerttampering" style="display: none">
							<div style="text-align:center">
							<table class="table table-striped table-bordered table-hover"
								id="vehicle_dashBoard_tampering_data">
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Vehicle No.</th>
										<th>Depot No.</th>
										<th>Date and Time</th>
										<th>Location</th>
									</tr>
								</thead>
							</table>
                          </div>
						</div> -->
						<div class="portlet-body" id="vehicle" style="display: none">
							<div style="text-align:center">
							<table class="table table-striped table-bordered table-hover"
								id="sosAlert_data">
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Vehicle No.</th>
										<th>Depot No.</th>
										<th>Device Sim No.</th>
										<th>Schedule No.</th>
<!-- 										<th>Call time</th> -->
										<th>Total SoS Count</th>
										<th>Call Epbx</th>
										<th>Driver Token No.</th>
										<th>Conductor Token No.</th>
										<th>Conductor Mob No.</th>
										<th>Reason</th>
									</tr>
								</thead>
							</table>
                         </div>
						</div>
						<div class="portlet-body" id="vehicleOIB" style="display: none">
							<div style="text-align:center">
							<table class="table table-striped table-bordered table-hover"
								id="alert_dashBoardOIB_data">
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Status</th>
										<th>Vehicle No.</th>
										<th>Device Id</th>
										<th>Depot No.</th>
										<th>Speed Km/Hr</th>
										<th>Schedule No.</th>
										<th>Shift</th>
										<th>Distance(Km)</th>
										<th>Internal Battery(Volt)</th>
										<th>External Battery(Volt)</th>
										<th>Ign. Status</th>
										<th>Signal Strength%</th>
										<th>Last Reported Time</th>
									</tr>
								</thead>
							</table>
                           </div>
						</div>
						<div class="portlet-body" id="vehicleCWS" style="display: none">
						<!-- 	<div style="text-align:center">
							<table class="table table-striped table-bordered table-hover"
								id="alert_dashBoardCWS_data">
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Status</th>
										<th>Vehicle No.</th>
										<th>Device Id</th>
										<th>Depot No.</th>
										<th>Speed Km/Hr</th>
										<th>Schedule No.</th>
										<th>Shift</th>
										<th>Distance(Km)</th>
										<th>Internal Battery(Volt)</th>
										<th>External Battery(Volt)</th>
										<th>Ign. Status</th>
										<th>Signal Strength%</th>
										<th>Last Reported Time</th>
									</tr>
								</thead>
							</table>
                         </div>
						</div> -->
						
						<div class="portlet-body" id="vehicleDWS" style="display: none">
							<div style="text-align:center">
						<!-- 	<table class="table table-striped table-bordered table-hover"
								id="alert_dashBoardDWS_data">
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Status</th>
										<th>Vehicle No.</th>
										<th>Device Id</th>
										<th>Depot No.</th>
										<th>Speed Km/Hr</th>
										<th>Schedule No.</th>
										<th>Shift</th>
										<th>Distance(Km)</th>
										<th>Internal Battery(Volt)</th>
										<th>External Battery(Volt)</th>
										<th>Ign. Status</th>
										<th>Signal Strength%</th>
										<th>Last Reported Time</th>
									</tr>
								</thead>
							</table>
                         </div>
						</div> -->
						
						<!-- <div class="portlet-body" id="vehicleS" style="display: none">
							<div style="text-align:center">
							<table class="table table-striped table-bordered table-hover"
								id="alert_dashBoardS_data">
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Status</th>
										<th>Vehicle No.</th>
										<th>Device Id</th>
										<th>Depot No.</th>
										<th>Speed Km/Hr</th>
										<th>Schedule No.</th>
										<th>Shift</th>
										<th>Distance(Km)</th>
										<th>Internal Battery(Volt)</th>
										<th>External Battery(Volt)</th>
										<th>Ign. Status</th>
										<th>Signal Strength%</th>
										<th>Last Reported Time</th>
									</tr>
								</thead>
							</table>
                         </div>
						</div> -->
					<!-- 	<div class="portlet-body" id="alertacc" style="display: none">
							<div style="text-align:center">
							<table class="table table-striped table-bordered table-hover"
								id="vehicle_accident">
								<thead>
									<tr>
		 								<th>Sr.No.</th>
		 								
		 								<th>Vehicle No.</th>
		 								<th>Device Id</th>
		 								<th>Depot No.</th>
		 								<th>Driver Token No.</th>
		 								<th>Driver Name</th>
		 								<th>Schedule No.</th>
		 								
										
									</tr>
								</thead>
							</table>
                            </div>
						</div> -->
						
						
						<!-- <div class="portlet-body" id="alertbreak" style="display: none">
							<div style="text-align:center">
							<table class="table table-striped table-bordered table-hover"
								id="vehicle_breakdown">
								<thead>
									<tr>
		 								<th>Sr.No.</th>
		 								
		 								<th>Vehicle No.</th>
		 								<th>Device Id</th>
		 								<th>Depot No.</th>
		 								<th>Driver Token No.</th>
		 								<th>Driver Name</th>
		 								<th>Schedule No.</th>
		 								
										
									</tr>
								</thead>
							</table>
                         </div>
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

	<div style="display: none;" id="mymodal" class="modal fade"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
				</div>
				<div>
					<p>
					<div class="portlet box white ">
						<div>
							<input type="hidden" name="requestType" id="requestType"
								value="map" />
							<div>
								<div class="portlet solid white">
									<div class="form-group">
										<label class="col-md-3 control-label"></label>
									</div>
									<div class="portlet-body">
										<div id="gmap_basic" class="gmaps"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
					</p>
				</div>

			</div>
		</div>
	</div>
	<div style="display: none;" id="mymodal1" class="modal fade"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 800px; height: 500px;"
				align="justify">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 id="vehicleno123" class="modal-title"></h4>
				</div>
				<div>
					<p>
					<div class="portlet box white ">
						<div>
							<input type="hidden" name="requestType" id="requestType" />
							<div>
								<div class="portlet solid white">
									<div class="form-group">
										<label class="col-md-3 control-label"></label>
									</div>
									<div class="portlet-body" id="withoutbusstop"
										style="display: none">
										<div style="text-align:center">
										<table class="table table-striped table-bordered table-hover"
											id="vehicleDetails123">
											<thead>
												<tr>
													<th>Sr.No.</th>
													<th>Vehicle No.</th>
													<th>Device Id</th>
													<th>Speed</th>
													<th>Trip No.</th>
													<th>Schedule Time</th>
													<th>Actual Time</th>
													<th>GPS Date</th>

												</tr>
											</thead>
										</table>
									</div>
									</div>
									<div class="portlet-body" id="devdetails" style="display: none">
									<div style="text-align:center">
										<table class="table table-striped table-bordered table-hover"
											id="vehicleDetailsDev">
											<thead>
												<tr>
													<th>Sr.No.</th>
													<th>Exit Point Location</th>
													<th>Entry Point Location</th>
													<th>Trip No.</th>
													<th>Deviated Distance(KM)</th>
													<th>Exit Time</th>
													<th>Entry Time</th>
												</tr>
											</thead>
										</table>
									</div>
									</div>
									<div class="portlet-body" id="tamperingdetails" style="display: none">
									<div style="text-align:center">
										<table class="table table-striped table-bordered table-hover"
											id="vehicleDetailsTampering">
											<thead>
												<tr>
													<th>Sr.No.</th>
													<th>Vehicle No.</th>
													
													<th>Date</th>
													<th>Location</th>
												</tr>
											</thead>
										</table>
									</div>
									</div>
									<div class="portlet-body" id="withbusstop"
										style="display: none">
										<div style="text-align:center">
										<table class="table table-striped table-bordered table-hover"
											id="vehicleDetails1234">
											<thead>
												<tr>
													<th>Sr.No.</th>
													<th>Vehicle No.</th>
													<th>Device Id</th>
													<th>Speed</th>
													<th>Schedule No.</th>
													<th>GPS Date</th>
													<th>Stop Name</th>
												</tr>
											</thead>
										</table>
									</div>
									</div>
									<div class="portlet-body" id="alert5" style="display: none">
										<div style="text-align:center">
										<table class="table table-striped table-bordered table-hover"
											id="skipStopAlert_data1">
											
											<thead>
												<tr>
													<th>Sr.No.</th>
													<th>Trip No.</th>
													<th>Vehicle No.</th>
													<th>Time Of Skipping</th>
													<th>Bus Stop Skipped Name</th>
												</tr>
											</thead>
										</table>
                                     </div>
									</div>
									
									
									<div class="portlet-body" id="alert10" style="display: none">
										<div style="text-align:center">
										<table class="table table-striped table-bordered table-hover"
											id="OveSpeedAlert_data1">
											<thead>
												<tr>
													<th>Sr.No.</th>
													<th>Vehicle No.</th>
													<th>Start Time</th>
<!-- 													<th>Bus Stop Skipped Name</th> -->
												</tr>
											</thead>
										</table>
                                     </div>
									</div>
								
									
									
									<div class="portlet-body" id="sosdetails" style="display: none">
										<div style="text-align:center">
										<table class="table table-striped table-bordered table-hover"
											id="sosDetails">
											<thead>
												<tr>
													<th>Sr.No.</th>
													<th>Vehicle No.</th>
													 <th>Time</th> 
													 <th>Status</th> 
													 <th>Incident</th> 
												</tr>
											</thead>
										</table>
									</div>
									</div>
									<div class="portlet-body" id="vehiclealertAcc" style="display: none">
										<div style="text-align:center">
										<table class="table table-striped table-bordered table-hover"
											id="vehicleAlertAcc">
											<thead>
												<tr>
													<th>Sr.No.</th>
													<th>Vehicle No.</th>
													<th>Time</th>
												</tr>
											</thead>
										</table>
									</div>
									</div>
									<div class="portlet-body" id="vehiclealertDeacc" style="display: none">
									<div style="text-align:center">
										<table class="table table-striped table-bordered table-hover"
											id="vehicleAlertDeacc">
											<thead>
												<tr>
													<th>Sr.No.</th>
													<th>Vehicle No.</th>
													<th>Time</th>
												</tr>
											</thead>
										</table>
									</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					</p>
				</div>

			</div>
		</div>
	</div>
</form>


<form action="#" class="form-horizontal ">
<div style="display: none;" id="mymodal2" class="modal fade"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 600px; height: 600px;"
				align="justify">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 id="vehicleno123" class="modal-title"></h4>
				</div>
				<div>
					<p>
					<div class="portlet box white ">
						<div>
							<input type="hidden" name="gpstime" id="gpstime" />
							<input type="hidden" name="depotnm" id="depotnm" />
							<input type="hidden" name="scheduleno" id="scheduleno" />
							<input type="hidden" name="call_type" id="call_type" />
							
							<input type="hidden" name="lat" id="lat" />
							<input type="hidden" name="lng" id="lng" />
							<input type="hidden" name="drivertoken" id="drivertoken" />
							<input type="hidden" name="depot_id" id="depot_id" />
							<input type="hidden" name="shift" id="shift" />
	
							<!-- <input type="hidden" name="depotid" id="depotid" /> -->
							<div>
								<!-- <div class="form-group">
											<label class="col-md-3 control-label">sch Id </label>
											<div class="col-md-4">
												<input type="text" class="form-control" id="scheduleId"
													name="scheduleId" readonly="readonly" value=''></input>
											</div>
										</div>	  -->
							
								<div class="portlet solid white">
									<div class="form-group">
										<label class="col-md-3 control-label"></label>
									</div>
									<div class="form-group">
									<table id ="tableid1">		
									<div>
									<input type="hidden" name="scheduleId" id="scheduleId" /></div>						
										<div class="form-group">
											<label class="col-md-3 control-label">Device Id </label>
											<div class="col-md-4">
												<input type="text" class="form-control" id="device_id"
													name="device_id" readonly="readonly" value=''></input>
											</div>
										</div>										
											<div class="form-group">
												<label class="col-md-3 control-label">Vehicle No.</label>
												<div class="col-md-4">
													<input type="text" class="form-control" id="vehicle_no"
														name="vehicle_no" readonly="readonly" value=''></input>
												</div>
											</div>
											<div class="form-group">
											
												<label class="col-md-3 control-label">Vehicle Status</label>
												<div class="col-md-4">
													<select id="vehicle_status"
														class="select1_category form-control" onchange="check() "
														name="vehicle_status">
														<option value="0">-----Select----</option>
														<option value="1">Normal</option>
														<option value="2">Vehicle Accident</option>
														<option value="3">Vehicle Breakdown</option>
														<option value="4">Other</option>
													</select>
												</div>
											</div>
											<div class="form-body">
												<div class="form-group" id="accident_type"
													style="display: none;">
													<label class="col-md-3 control-label">Accident Type: </label>
													<div class="col-md-4">
														<select id="acctypelist"
															class="select2_category form-control" 
															name="acctypelist">
															<option value="0">-----Select----</option>
														</select>
													</div>
												</div>
											</div>
											<div class="form-body">
												<div class="form-group" id="breakdown_type"
													style="display: none;">
													<label class="col-md-3 control-label">Breakdown Type: </label>
													<div class="col-md-4">
														<select id="brkdwntypelist"
															class="select2_category form-control" name="brkdwntypelist">
															<option value="0">-----Select----</option>
														</select>
													</div>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">Description</label>
												<div class="col-md-4">
													<textarea class="form-control" id="description"
														maxlength="100" name="notes"></textarea>
												</div>
											</div>
											</table>	
										</div>
										<br>
										<br>
										<div class="form-group">
											<div class="form-actions fluid">
												<div class="col-md-offset-3 col-md-9">
													<button type="button" class="btn blue" onclick="changeVehicleStatus()">Save</button>
													<button type="button" class="btn default" data-dismiss="modal"	aria-hidden="true">Cancel</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	</form>
	
	
	
<form action="#" class="form-horizontal ">
<div style="display: none;" id="mymodal14" class="modal fade"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 600px; height: 600px;"
				align="justify">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 id="vehicleno123" class="modal-title"></h4>
				</div>
				<div>
					<p>
					<div class="portlet box white ">
						<div>
						
							<input type="hidden" name="shift" id="shift" />
	
							<div>
							
								<div class="portlet solid white">
									<div class="form-group">
										<label class="col-md-3 control-label"></label>
									</div>
									<div class="form-group">
									<table id ="tableid1">		
									<div>
<!-- 									<input type="hidden" name="scheduleId" id="scheduleId" /></div>						 -->
<!-- 										<div class="form-group"> -->
<!-- 											<label class="col-md-3 control-label">Device Id </label> -->
<!-- 											<div class="col-md-4"> -->
<!-- 												<input type="text" class="form-control" id="device_id" -->
<!-- 													name="device_id" readonly="readonly" value=''></input> -->
<!-- 											</div> -->
<!-- 										</div>										 -->
											<div class="form-group">
												<label class="col-md-3 control-label">Vehicle No.</label>
												<div class="col-md-4">
													<input type="text" class="form-control" id="vehicleNo"
														name="vehicleNo" readonly="readonly" value=''></input>
												</div>
											</div>
											<div class="form-group">
											
												<label class="col-md-3 control-label">Reason<font color="red">*</font></label>
												<div class="col-md-4" id="reason">
													<select id="vehicle_reason"
														class="select1_category form-control" onChange="checkType()"
														name="vehicle_reason">
														<option value="0">-----Select----</option>
														<option value="1">Want of Vehicle</option>
														<option value="2">Want of Crew</option>
														<option value="3">Other</option>
													</select>
												</div>
											</div>
										
										
											<div class="form-group" style="display: none" id="desc">
												<label class="col-md-3 control-label">Description<font color="red">*</font></label>
												<div class="col-md-4">
													<textarea class="form-control" id="description1"
														maxlength="100" name="description1"></textarea>
												</div>
											</div>
											</table>	
										</div>
										<br>
										<br>
										<div class="form-group">
											<div class="form-actions fluid">
												<div class="col-md-offset-3 col-md-9">
													<button type="button" class="btn blue" onclick="insertLateDeptReason()">Save</button>
													<button type="button" class="btn default" data-dismiss="modal"	aria-hidden="true">Cancel</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	</form>
	
 	
<form action="#" class="form-horizontal ">
<div style="display: none;" id="mymodal15" class="modal fade"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 600px; height: 600px;"
				align="justify">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 id="vehicleno123" class="modal-title"></h4>
				</div>
				<div>
					<p>
					<div class="portlet box white ">
						<div>
						
							<input type="hidden" name="shift" id="shift" />
	
							<div>
							
								<div class="portlet solid white">
									<div class="form-group">
										<label class="col-md-3 control-label"></label>
									</div>
									<div class="form-group">
									<table id ="tableid1">		
									<div>
<!-- 									<input type="hidden" name="scheduleId" id="scheduleId" /></div>						 -->
<!-- 										<div class="form-group"> -->
<!-- 											<label class="col-md-3 control-label">Device Id </label> -->
<!-- 											<div class="col-md-4"> -->
<!-- 												<input type="text" class="form-control" id="device_id" -->
<!-- 													name="device_id" readonly="readonly" value=''></input> -->
<!-- 											</div> -->
<!-- 										</div>										 -->
											<div class="form-group">
												<label class="col-md-3 control-label">Vehicle No.</label>
												<div class="col-md-4">
													<input type="text" class="form-control" id="vehicleNo1"
														name="vehicleNo1" readonly="readonly" value=''></input>
												</div>
											</div>
											<div class="form-group">
											
												<label class="col-md-3 control-label">Reason<font color="red">*</font></label>
												<div class="col-md-4" id="reason1">
													<select id="vehicle_reason1"
														class="select1_category form-control" onChange="checkTypeEarly()"
														name="vehicle_reason1">
														<option value="0">-----Select----</option>
														<option value="1">Want of Vehicle</option>
														<option value="2">Want of Crew</option>
														<option value="3">Other</option>
													</select>
												</div>
											</div>
										
										
											<div class="form-group" style="display: none" id="desc11">
												<label class="col-md-3 control-label">Description<font color="red">*</font></label>
												<div class="col-md-4">
													<textarea class="form-control" id="description11"
														maxlength="100" name="description11"></textarea>
												</div>
											</div>
											</table>	
										</div>
										<br>
										<br>
										<div class="form-group">
											<div class="form-actions fluid">
												<div class="col-md-offset-3 col-md-9">
													<button type="button" class="btn blue" onclick="getEarlyArrivalReason()">Save</button>
													<button type="button" class="btn default" data-dismiss="modal"	aria-hidden="true">Cancel</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	</form>	

		
<form action="#" class="form-horizontal ">
<div style="display: none;" id="mymodal16" class="modal fade"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 600px; height: 600px;"
				align="justify">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 id="vehicleno123" class="modal-title"></h4>
				</div>
				<div>
					<p>
					<div class="portlet box white ">
						<div>
						
							<input type="hidden" name="shift" id="shift" />
	
							<div>
							
								<div class="portlet solid white">
									<div class="form-group">
										<label class="col-md-3 control-label"></label>
									</div>
									<div class="form-group">
									<table id ="tableid1">		
									<div>
<!-- 									<input type="hidden" name="scheduleId" id="scheduleId" /></div>						 -->
<!-- 										<div class="form-group"> -->
<!-- 											<label class="col-md-3 control-label">Device Id </label> -->
<!-- 											<div class="col-md-4"> -->
<!-- 												<input type="text" class="form-control" id="device_id" -->
<!-- 													name="device_id" readonly="readonly" value=''></input> -->
<!-- 											</div> -->
<!-- 										</div>										 -->
											<div class="form-group">
												<label class="col-md-3 control-label">Vehicle No.</label>
												<div class="col-md-4">
													<input type="text" class="form-control" id="vehicleNum"
														name="vehicleNum" readonly="readonly" value=''></input>
												</div>
											</div>
											<div class="form-group">
											
												<label class="col-md-3 control-label">Reason<font color="red">*</font></label>
												<div class="col-md-4" id="reason">
													<select id="vehicle_reason2"
														class="select1_category form-control" onChange="checkTypeStationary()"
														name="vehicle_reason2">
														<option value="0">-----Select----</option>
														<option value="1">Taking a scheduled break</option>
														<option value="2">Breakdown</option>
														<option value="3">Accident</option>
														<OPTION value="4">Early arrival at the scheduled pick-up point</OPTION>
														<OPTION value="5">Others</OPTION>
													</select>
												</div>
											</div>
										
										
											<div class="form-group" style="display: none" id="desc2">
												<label class="col-md-3 control-label">Description<font color="red">*</font></label>
												<div class="col-md-4">
													<textarea class="form-control" id="description2"
														maxlength="100" name="description2"></textarea>
												</div>
											</div>
											</table>	
										</div>
										<br>
										<br>
										<div class="form-group">
											<div class="form-actions fluid">
												<div class="col-md-offset-3 col-md-9">
													<button type="button" class="btn blue" onclick="getStationaryReason()">Save</button>
													<button type="button" class="btn default" data-dismiss="modal"	aria-hidden="true">Cancel</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	</form>
	
	
	<%-- <form action="#" class="form-horizontal ">
<div style="display: none;" id="mymodal17" class="modal fade"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 600px; height: 600px;"
				align="justify">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 id="vehicleno123" class="modal-title"></h4>
				</div>
				<div>
					<p>
					<div class="portlet box white ">
						<div>
						
							<input type="hidden" name="shift" id="shift" />
	
							<div>
							
								<div class="portlet solid white">
									<div class="form-group">
										<label class="col-md-3 control-label"></label>
									</div>
									<div class="form-group">
									<table id ="tableid1">		
									<div>
<!-- 									<input type="hidden" name="scheduleId" id="scheduleId" /></div>						 -->
<!-- 										<div class="form-group"> -->
<!-- 											<label class="col-md-3 control-label">Device Id </label> -->
<!-- 											<div class="col-md-4"> -->
<!-- 												<input type="text" class="form-control" id="device_id" -->
<!-- 													name="device_id" readonly="readonly" value=''></input> -->
<!-- 											</div> -->
<!-- 										</div>										 -->
											<div class="form-group">
												<label class="col-md-3 control-label">Vehicle No.</label>
												<div class="col-md-4">
													<input type="text" class="form-control" id="vehicleNo3"
														name="vehicleNo3" readonly="readonly" value=''></input>
												</div>
											</div>
											<div class="form-group">
											
												<label class="col-md-3 control-label">Reason<font color="red">*</font></label>
												<div class="col-md-4" id="reason">
													<select id="vehicle_reason3"
														class="select1_category form-control" onChange="checkTypeSkip()"
														name="vehicle_reason3">
														<option value="0">-----Select----</option>
														<option value="1">No passengers at the stop</option>
														<option value="2">Enountered another vehicle opeating on the same route at the stop</option>
														<option value="3">Skipped stop due to planned route deviation</option>
														<OPTION value="4">skipped stop due to route deviation undertaken to avoid congestion</OPTION>
														<OPTION value="5">Other</OPTION>
													</select>
												</div>
											</div>
										
										
											<div class="form-group" style="display: none" id="desc3">
												<label class="col-md-3 control-label">Description<font color="red">*</font></label>
												<div class="col-md-4">
													<textarea class="form-control" id="description3"
														maxlength="100" name="notes"></textarea>
												</div>
											</div>
											</table>	
										</div>
										<br>
										<br>
										<div class="form-group">
											<div class="form-actions fluid">
												<div class="col-md-offset-3 col-md-9">
													<button type="button" class="btn blue" onclick="insertSkipStopReason()">Save</button>
													<button type="button" class="btn default" data-dismiss="modal"	aria-hidden="true">Cancel</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	</form>
	 --%>
	
		
<form action="#" class="form-horizontal ">
<div style="display: none;" id="mymodal18" class="modal fade"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 600px; height: 600px;"
				align="justify">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 id="vehicleno123" class="modal-title"></h4>
				</div>
				<div>
					<p>
					<div class="portlet box white ">
						<div>
						
							<input type="hidden" name="shift" id="shift" />
	
							<div>
							
								<div class="portlet solid white">
									<div class="form-group">
										<label class="col-md-3 control-label"></label>
									</div>
									<div class="form-group">
									<table id ="tableid1">		
									<div>
										<div class="form-group">
											<label class="col-md-3 control-label">Device Id </label>
											<div class="col-md-4">
												<input type="text" class="form-control" id="device_id4"
													name="device_id4" readonly="readonly" value=''></input>
											</div>
										</div>										
											<div class="form-group">
												<label class="col-md-3 control-label">Vehicle No.</label>
												<div class="col-md-4">
													<input type="text" class="form-control" id="vehicleNo4"
														name="vehicleNo4" readonly="readonly" value=''></input>
												</div>
											</div>
											<div class="form-group">
											
												<label class="col-md-3 control-label">Reason<font color="red">*</font></label>
												<div class="col-md-4" id="reason">
													<select id="vehicle_reason4"
														class="select1_category form-control" onChange="checkType4()"
														name="vehicle_reason4">
														<option value="0">-----Select----</option>
														<option value="1">Bus breakdown</option>
														<option value="2">Accident</option>
														<option value="3">Others</option>
													</select>
												</div>
											</div>
										
										
											<div class="form-group" style="display: none" id="desc4">
												<label class="col-md-3 control-label">Description<font color="red">*</font></label>
												<div class="col-md-4">
													<textarea class="form-control" id="description4"
														maxlength="100" name="notes"></textarea>
												</div>
											</div>
											</table>	
										</div>
										<br>
										<br>
										<div class="form-group">
											<div class="form-actions fluid">
												<div class="col-md-offset-3 col-md-9">
													<button type="button" class="btn blue" onclick="getSOSReason()">Save</button>
													<button type="button" class="btn default" data-dismiss="modal"	aria-hidden="true">Cancel</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	</form>

	
<form action="#" class="form-horizontal ">
<div style="display: none;" id="mymodal19" class="modal fade"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
		aria-hidden="true" id="devId">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 600px; height: 600px;"
				align="justify">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 id="vehicleno123" class="modal-title"></h4>
				</div>
				<div>
					<p>
					<div class="portlet box white ">
						<div>
						
							<input type="hidden" name="shift" id="shift" />
	
							<div>
							
								<div class="portlet solid white">
									<div class="form-group">
										<label class="col-md-3 control-label"></label>
									</div>
									<div class="form-group">
									<table id ="tableid1">		
									<div>
<!-- 									<input type="hidden" name="scheduleId" id="scheduleId" /></div>						 -->
<!-- 										<div class="form-group"> -->
<!-- 											<label class="col-md-3 control-label">Device Id </label> -->
<!-- 											<div class="col-md-4"> -->
<!-- 												<input type="text" class="form-control" id="device_id" -->
<!-- 													name="device_id" readonly="readonly" value=''></input> -->
<!-- 											</div> -->
<!-- 										</div>										 -->
											<div class="form-group">
												<label class="col-md-3 control-label">Vehicle No.</label>
												<div class="col-md-4">
													<input type="text" class="form-control" id="vehicleNo5"
														name="vehicleNo5" readonly="readonly" value=''></input>
												</div>
											</div>
											<div class="form-group">
											
												<label class="col-md-3 control-label">Reason<font color="red">*</font></label>
												<div class="col-md-4" id="reason">
													<select id="vehicle_reason5"
														class="select1_category form-control" onChange="checkTypeDeviated()"
														name="vehicle_reason5">
														<option value="0">-----Select----</option>
														<option value="1">Route deviations as planned by Traffic police</option>
														<option value="2">Route deviation undertaken to avoid congestion</option>
														<option value="3">Route deviation undertaken to complete the trip within scheduled trip completion time as per Form-4</option>
														<option value="4">Others</option>
													</select>
												</div>
											</div>
										
										
											<div class="form-group" style="display: none" id="desc5">
												<label class="col-md-3 control-label">Description<font color="red">*</font></label>
												<div class="col-md-4">
													<textarea class="form-control" id="description5"
														maxlength="100" name="notes"></textarea>
												</div>
											</div>
											</table>	
										</div>
										<br>
										<br>
										<div class="form-group">
											<div class="form-actions fluid">
												<div class="col-md-offset-3 col-md-9">
													<button type="button" class="btn blue" onclick="getDeviatedReason()">Save</button>
													<button type="button" class="btn default" data-dismiss="modal"	aria-hidden="true">Cancel</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	</form>	
	
	
		
<form action="#" class="form-horizontal ">
<div style="display: none;" id="mymodal20" class="modal fade"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 600px; height: 600px;"
				align="justify">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 id="vehicleno123" class="modal-title"></h4>
				</div>
				<div>
					<p>
					<div class="portlet box white ">
						<div>
						<h4><span class="help-block" style="color: green; list-style: none"><s:actionmessage /></span></h4>
							<input type="hidden" name="shift" id="shift" />
	
							<div>
							
								<div class="portlet solid white">
									<div class="form-group">
										<label class="col-md-3 control-label"></label>
									</div>
									<div class="form-group">
									<table id ="tableid1">		
									<div>
<!-- 									<input type="hidden" name="scheduleId" id="scheduleId" /></div>						 -->
<!-- 										<div class="form-group"> -->
<!-- 											<label class="col-md-3 control-label">Device Id </label> -->
<!-- 											<div class="col-md-4"> -->
<!-- 												<input type="text" class="form-control" id="device_id" -->
<!-- 													name="device_id" readonly="readonly" value=''></input> -->
<!-- 											</div> -->
<!-- 										</div>										 -->
											<div class="form-group">
												<label class="col-md-3 control-label">Vehicle No.</label>
												<div class="col-md-4">
													<input type="text" class="form-control" id="vehicleNo6"
														name="vehicleNo6" readonly="readonly" value=''></input>
												</div>
											</div>
											<div class="form-group">
											
												<label class="col-md-3 control-label">Reason<font color="red">*</font></label>
												<div class="col-md-4" id="reason">
													<select id="vehicle_reason6"
														class="select1_category form-control" onChange="checkTypeSchNotDept()"
														name="vehicle_reason6">
														<option value="0">-----Select----</option>
														<option value="1">Want of Vehicle</option>
														<option value="2">Want of Crew</option>
														<OPTION value="3">Others</OPTION>
													</select>
												</div>
											</div>
										
										
											<div class="form-group" style="display: none" id="desc6">
												<label class="col-md-3 control-label">Description<font color="red">*</font></label>
												<div class="col-md-4">
													<textarea class="form-control" id="description6"
														maxlength="100" name="notes"></textarea>
												</div>
											</div>
											</table>	
										</div>
										<br>
										<br>
										<div class="form-group">
											<div class="form-actions fluid">
												<div class="col-md-offset-3 col-md-9">
													<button type="button" class="btn blue" onclick="getSchNotDeptReason()">Save</button>
													<button type="button" class="btn default" data-dismiss="modal"	aria-hidden="true">Cancel</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	</form>	

		
<form action="#" class="form-horizontal ">
<div style="display: none;" id="mymodal21" class="modal fade"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 600px; height: 600px;"
				align="justify">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 id="vehicleno123" class="modal-title"></h4>
				</div>
				<div>
					<p>
					<div class="portlet box white ">
						<div>
						<h4><span class="help-block" style="color: green; list-style: none"><s:actionmessage /></span></h4>
							<input type="hidden" name="shift" id="shift" />
	
							<div>
							
								<div class="portlet solid white">
									<div class="form-group">
										<label class="col-md-3 control-label"></label>
									</div>
									<div class="form-group">
									<table id ="tableid1">		
									<div>
<!-- 									<input type="hidden" name="scheduleId" id="scheduleId" /></div>						 -->
<!-- 										<div class="form-group"> -->
<!-- 											<label class="col-md-3 control-label">Device Id </label> -->
<!-- 											<div class="col-md-4"> -->
<!-- 												<input type="text" class="form-control" id="device_id" -->
<!-- 													name="device_id" readonly="readonly" value=''></input> -->
<!-- 											</div> -->
<!-- 										</div>										 -->
											<div class="form-group">
												<label class="col-md-3 control-label">Vehicle No.</label>
												<div class="col-md-4">
													<input type="text" class="form-control" id="vehicleNo7"
														name="vehicleNo7" readonly="readonly" value=''></input>
												</div>
											</div>
											<div class="form-group">
											
												<label class="col-md-3 control-label">Reason<font color="red">*</font></label>
												<div class="col-md-4" id="reason">
													<select id="vehicle_reason7"
														class="select1_category form-control" onChange="checkTypeNRD()"
														name="vehicle_reason7">
														<option value="0">-----Select----</option>
														<option value="1">Docking</option>
														<option value="2">At CWS</option>
														<option value="3">Road Worthy Spare Vehicles</option>
														<option value="4">Off Road at Depot/ Depot Off Road</option>
														<option value="5">Water Log</option>
														<option value="6">Power Supply Problem</option>
														<option value="7">Battery Problem</option>
														<option value="8">Others</option>
													</select>
												</div>
											</div>
										
										
											<div class="form-group" style="display: none" id="desc7">
												<label class="col-md-3 control-label">Description<font color="red">*</font></label>
												<div class="col-md-4">
													<textarea class="form-control" id="description7"
														maxlength="100" name="notes"></textarea>
												</div>
											</div>
											</table>	
										</div>
										<br>
										<br>
										<div class="form-group">
											<div class="form-actions fluid">
												<div class="col-md-offset-3 col-md-9">
													<button type="button" class="btn blue" onclick="getNRDReason()">Save</button>
													<button type="button" class="btn default" data-dismiss="modal"	aria-hidden="true">Cancel</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	</form>	

<script>
	$(document).ready(function() {
		loadActiveVehicles();
		getAlertIntervalData();
		getVehicleData();
	});
</script>
<script>
	google.maps.event.addDomListener(window, 'load', initialize);
	$("#mnu_header li").removeClass("classic-menu-dropdown active");
	$("#mnu_vtu").addClass("classic-menu-dropdown active");
	$("#mnu_header li").addClass("classic-menu-dropdown");
	$("#vtu_selected").addClass("selected");
	
</script>

<script>

function insertDataIntoVtsSos(deviceId,sosId,istDate){
	//alert(deviceId+"  "+sosId+"  "+istDate);
	
	//End Code
	

// 	 $.ajax({
	        
// //         type: "post",
<%-- //         url: '<%=request.getContextPath()%>/insertDataIntoVtsSos.action?deviceId='+deviceId+'&sosId='+sosId+'&istDate='+istDate, --%>
//          url : "insertDataIntoVtsSosForCall",
//  		data : "deviceId=" + deviceId + "&sosId=" + sosId + "&istDate="
//  				+ istDate ,
//  		method : "POST",
// // 		type:"text",
//          success: function(result) {
//          	$('#pageLoader').hide();
//          	alert("SOS call");
//          	console.log(result);
//          },
//      	/*error : function(e){
// 			alert('error   '+e.toSource());
// 		}*/

//      });
	
	
$.ajax({
    type: "get",
    url: '<%=request.getContextPath()%>/insertDataIntoVtsSosForCall.action?deviceId='+deviceId+'&sosId='+sosId+'&istDate='+istDate,
    success: function(result) {
 	   $('#pageLoader').hide();
 	   alert("Call initiated ! Successfully");
//          document.getElementById('northDivisioneData').innerHTML=result;
//          fixHeader();
         
     }
}); 
	
}


</script>

<script>

function insertLateDeptReason(){

	var vehicleId=document.getElementById('vehicleNo').value;
	var reason=document.getElementById('vehicle_reason').value;
	var text = document.getElementById('vehicle_reason').selectedOptions[0].text;
	if(reason == 0){
		bootbox.alert("Please Enter Reason for Late Departure");
		return false;
	}
	
		if(reason ==3){
// 		var desc=$("#description").val();
		  var desc = document.getElementById("description1").value;
// 		 alert("  "+desc);
		}else{
		var	desc = "";
		}
		
		if(reason == 3 && desc =="" ){
			bootbox.alert("Please Specify the Reason");
			return false;
		}
// 	alert(desc+"  "+text+"  ");
	
	
$.ajax({
    type: "get",
    url: '<%=request.getContextPath()%>/insertLateDeptReason.action?vehicleId='+vehicleId+'&reason='+reason+'&reasonName='+text+'&desc='+desc,
    success: function(result) {
 	  
 	   alert("Reason inserted Successfully");
//          document.getElementById('northDivisioneData').innerHTML=result;

     }
}); 
	
}


function getEarlyArrivalReason(){
	var desc="";
	var vehicleId=document.getElementById('vehicleNo1').value;
	var reason=document.getElementById('vehicle_reason1').value;
	var text = document.getElementById('vehicle_reason1').selectedOptions[0].text;
	
	if(reason == 0){
		bootbox.alert("Please Enter Reason for Early Arrival");
		return false;
	}
	if(reason ==3){
		 desc=document.getElementById('description11').value;
	}else{
		desc = "";
	}
	if(reason == 3 && desc =="" ){
		bootbox.alert("Please Specify the Reason");
		return false;
	}
	
// alert(desc+"  "+text+"  ");
	
	
$.ajax({
    type: "get",
    url: '<%=request.getContextPath()%>/insertEarlyArrReason.action?vehicleId='+vehicleId+'&reason='+reason+'&reasonName='+text+'&desc='+desc,
    success: function(result) {
 	  
 	   alert("Reason inserted Successfully");
//          document.getElementById('northDivisioneData').innerHTML=result;

     }
}); 
	
}


function getStationaryReason(){
	var desc="";
	var vehicleId=document.getElementById('vehicleNum').value;
	var reason=document.getElementById('vehicle_reason2').value;
	var text = document.getElementById('vehicle_reason2').selectedOptions[0].text;
	if(reason == 0){
		bootbox.alert("Please Enter Reason for Stationary");
		return false;
	}
	
	if(reason ==5){
	 desc=document.getElementById('description2').value;
	}else{
		desc = "";
	}
	
	if(reason == 5 && desc =="" ){
		bootbox.alert("Please Specify the Reason");
		return false;
	}
// alert(desc+"  "+text+"  ");
	
	
$.ajax({
    type: "get",
    url: '<%=request.getContextPath()%>/insertStationaryReason.action?vehicleId='+vehicleId+'&reason='+reason+'&reasonName='+text+'&desc='+desc,
    success: function(result) {
 	  
 	   alert("Reason inserted Successfully");
//          document.getElementById('northDivisioneData').innerHTML=result;

     }
}); 
	
}

function insertSkipStopReason(){
	var desc="";
	var vehicleId=document.getElementById('vehicleNo3').value;
	var text = document.getElementById('vehicle_reason3').selectedOptions[0].text;
	var reason=document.getElementById('vehicle_reason3').value;
	if(reason == 0){
		bootbox.alert("Please Enter Reason for Skip Stop");
		return false;
	}
	
	if(reason ==5){
		 desc=document.getElementById('description3').value;
	}else{
		desc = "";
	}
	
	if(reason == 5 && desc =="" ){
		bootbox.alert("Please Specify the Reason");
		return false;
	}
// alert(desc+"  "+text+"  ");
	
	
$.ajax({
    type: "get",
    url: '<%=request.getContextPath()%>/insertSkipStopReason.action?vehicleId='+vehicleId+'&reason='+reason+'&reasonName='+text+'&desc='+desc,
    success: function(result) {
 	  
 	   alert("Reason inserted Successfully");
//          document.getElementById('northDivisioneData').innerHTML=result;

     }
}); 
	
}

function getSOSReason(){
	var desc="";
	var vehicleId=document.getElementById('vehicleNo4').value;
	var reason=document.getElementById('vehicle_reason4').value;
	var text = document.getElementById('vehicle_reason4').selectedOptions[0].text;
	var device=document.getElementById('device_id4').value;
	
	if(reason == 0){
		bootbox.alert("Please Enter Reason for SOS");
		return false;
	}
	
	if(reason ==3){
		 desc=document.getElementById('description5').value;
	}else{
		desc = "";
	}
	
	if(reason == 3 && desc =="" ){
		bootbox.alert("Please Specify the Reason");
		return false;
	}
// alert(desc+"  "+text+"  ");
	
	
$.ajax({
    type: "get",
    url: '<%=request.getContextPath()%>/insertSOSReason.action?vehicleId='+device+'&reason='+reason+'&reasonName='+text+'&desc='+desc,
    success: function(result) {
 	  
 	   alert("Reason inserted Successfully");
//          document.getElementById('northDivisioneData').innerHTML=result;

     }
}); 
	
}

function getSchNotDeptReason(){
	var desc="";
	var vehicleId=document.getElementById('vehicleNo6').value;
	var reason=document.getElementById('vehicle_reason6').value;
	var text = document.getElementById('vehicle_reason6').selectedOptions[0].text;
	
	if(reason == 0){
		bootbox.alert("Please Enter Reason for Schedule Not Departed");
		return false;
	}
	
	if(reason ==3){
		 desc=document.getElementById('description6').value;
		 
	}else{
		desc = "";
	}
	if(reason == 3 && desc =="" ){
		bootbox.alert("Please Specify the Reason");
		return false;
	}
	
// alert(desc+"  "+text+"  ");
	
	
$.ajax({
    type: "get",
    url: '<%=request.getContextPath()%>/insertSchNotDeptReason.action?vehicleId='+vehicleId+'&reason='+reason+'&reasonName='+text+'&desc='+desc,
    success: function(result) {
 	  
 	   alert("Reason inserted Successfully");
//          document.getElementById('northDivisioneData').innerHTML=result;

     }
}); 
	
}

function getDeviatedReason(){
	var desc="";
	var vehicleId=document.getElementById('vehicleNo5').value;
	var reason=document.getElementById('vehicle_reason5').value;
	var text = document.getElementById('vehicle_reason5').selectedOptions[0].text;
	
	if(reason == 0){
		bootbox.alert("Please Enter Reason for Deviation");
		return false;
	}
	if(reason ==4){
		 desc=document.getElementById('description5').value;
	}else{
		desc = "";
	}
	if(reason == 4 && desc =="" ){
		bootbox.alert("Please Specify the Reason");
		return false;
	}
	$("#devId").show();
// alert(desc+"  "+text+"  ");
	
	
$.ajax({
    type: "get",
    url: '<%=request.getContextPath()%>/insertDeviatedReason.action?vehicleId='+vehicleId+'&reason='+reason+'&reasonName='+text+'&desc='+desc,
    success: function(result) {
 	  
 	   alert("Reason inserted Successfully");
//          document.getElementById('northDivisioneData').innerHTML=result;
 	  $("#devId").show();
     }
}); 
	
}


function getNRDReason(){
	var desc="";
	var vehicleId=document.getElementById('vehicleNo7').value;
	var reason=document.getElementById('vehicle_reason7').value;
	var text = document.getElementById('vehicle_reason7').selectedOptions[0].text;
	
	if(reason == 0){
		bootbox.alert("Please Enter Reason for NRD");
		return false;
	}
	if(reason ==8){
		 desc=document.getElementById('description7').value;
	}else{
		desc = "";
	}
	if(reason == 8 && desc =="" ){
		bootbox.alert("Please Specify the Reason");
		return false;
	}
	
// alert(desc+"  "+text+"  ");
	
	
$.ajax({
    type: "get",
    url: '<%=request.getContextPath()%>/insertNRDReason.action?vehicleId='+vehicleId+'&reason='+reason+'&reasonName='+text+'&desc='+desc,
    success: function(result) {
 	  
 	   alert("Reason inserted Successfully");

 	  
     }
}); 
	
}


function checkType(){
// alert("  "+id);
var val=document.getElementById("vehicle_reason").value
// alert(val);
if(val==3){
	$(desc).show();
}else {
	$(desc).hide();
}
}

function checkTypeEarly(){
	// alert("  "+id);
	var val=document.getElementById("vehicle_reason1").value
	// alert(val);
	if(val==3){
		$(desc11).show();
	}else {
		$(desc11).hide();
	}
	}
function checkType4(){
	// alert("  "+id);
	var val=document.getElementById("vehicle_reason4").value
	// alert(val);
	if(val==3){
		$(desc4).show();
	}else {
		$(desc4).hide();
	}
	}

function checkTypeSkip(){
	// alert("  "+id);
	var val=document.getElementById("vehicle_reason3").value
	// alert(val);
	if(val==5){
		$(desc3).show();
	}else {
		$(desc3).hide();
	}
	}
	
function checkTypeSchNotDept(){
	// alert("  "+id);
	var val=document.getElementById("vehicle_reason6").value
	// alert(val);
	if(val==3){
		$(desc6).show();
	}else {
		$(desc6).hide();
	}
	}
	

	
	
function checkTypeDeviated(){

	var val=document.getElementById("vehicle_reason5").value
// 	alert(val);
	if(val==4){
		$(desc5).show();
	}else {
		$(desc5).hide();
	}
	}
	
function checkTypeStationary(){
	// alert("  "+id);
	var val=document.getElementById("vehicle_reason2").value
	// alert(val);
	if(val==5){
		$(desc2).show();
	}else {
		$(desc2).hide();
	}
	}


function checkTypeNRD(){

	var val=document.getElementById("vehicle_reason7").value
// 	alert(val);
	if(val==8){
		$(desc7).show();
	}else {
		$(desc7).hide();
	}
	}

<%-- function getLateReason(){

	
    $.ajax({
        type: "post",
        url: '<%=request.getContextPath()%>/getLateDeptReason',
		success : function(result) {
			document.getElementById('vehicle_reason').innerHTML = result;
			
		}
	});


} --%>


</script>
