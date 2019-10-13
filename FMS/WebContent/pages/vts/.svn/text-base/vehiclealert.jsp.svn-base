
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <script src="assets/vts/js/skiproute.js"></script>
  <script src="assets/vts/js/vehiclealert.js"></script>
<script src="assets/vts/js/table-managed-device.js"></script> 

<form>
	<div class="page-content-wrapper">
		<div class="page-content">
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div class="modal fade" id="portlet-config" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true"></button>
							<h4 class="modal-title">Modal title</h4>
						</div>
						<div class="modal-body">Widget settings form goes here</div>
						<div class="modal-footer">
							<button type="button" class="btn blue">Save changes</button>
							<button type="button" class="btn default" data-dismiss="modal">Close</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>

			<!-- END STYLE CUSTOMIZER -->
			<!-- BEGIN PAGE HEADER-->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						Vehicle Alert <small></small>
					</h3>

					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->

			<div class="row">
				<div class="col-md-12" id="chart_alert">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>View Alerts
							</div>
							<div class="tools">
								<a class="collapse" id="vehicle_chart_alert" href="javascript:;">
								</a> <a class="config" data-toggle="modal" href="#portlet-config">
								</a> <a class="reload" href="javascript:;"> </a> <a class="remove"
									href="javascript:;"> </a>
							</div>
						</div>						
						<div class="portlet-body">
							<input type="hidden" name="requestType" id="requestType"
								value="text" />
						
							<table class="table table-striped table-bordered table-hover"
								id="viewAlert">
							
								<thead>
									<tr>
										<th>Alert Count</th>
										<th>Alert Code</th>
										<th>Alert Description</th>
										
									</tr>
								</thead>

							</table>
							
						</div>
						
					</div>
				</div>
				<!-- END EXAMPLE TABLE PORTLET-->
			</div>
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<div style="width: 250px" id='vstatus'></div>
							</div>
							<div class="tools">
								<a class="collapse" id="vehicle_chart_alert_desc" href="javascript:;">
								</a> <a class="config" data-toggle="modal" href="#portlet-config">
								</a> <a class="reload" href="javascript:;"> </a> <a class="remove"
									href="javascript:;"> </a>
							</div>
						</div>		
						<div class="portlet-body" id="alert1" style="display: none">
 							<table class="table table-striped table-bordered table-hover"
								id="vehicle_earlyArrival_data">

								<thead>
									<tr>
										<th>Sr. No.</th>
										<th>Vehicle no</th>
										<th>Schedule no</th>
										<th>End Bus Stop Name</th>
										<th>Shift no</th>
										<th>Schedule Arrival time</th>
										<th>Actual arrival time</th>
										<th>Time difference</th>
									</tr>
								</thead>
							</table>

						</div>	
						<div class="portlet-body" id="alert3" style="display: none">

							<table class="table table-striped table-bordered table-hover"
								id="sosAlert_data">
								<thead>
									<tr>
										<th>Sr. No.</th>
										<th>Vehicle No</th>
										<th>Device Sim No</th>
										<th>Schedule No</th>
										<th>Call time</th>
										<th>Total Count</th>
										<th>Driver Token No</th>
										<th>Conductor Token No.</th>
										<th>Conductor Mob No.</th>
									</tr>
								</thead>
							</table>

						</div>
						<div class="portlet-body" id="alert4" style="display: none">

							<table class="table table-striped table-bordered table-hover"
								id="skipStopAlert_data">
								<thead>
									<tr>
										<th>Sr. No.</th>
										<th>Vehicle No</th>
										<th>Schedule No</th>
										<th>Driver Token No</th>
										<th>Shift No.</th>
										<th>Total Stop Skip Count</th>
									</tr>
								</thead>
							</table>

						</div>
						<div class="portlet-body" id="overSpeeding" style="display: none">

							<table class="table table-striped table-bordered table-hover"
								id="overSpeeding_data">
								<thead>
									<tr>
										<th>Sr. No.</th>
										<th>Vehicle No</th>
										<th>Schedule No</th>
										<th>Depot Name</th>
										<th>Location</th>
										<th>Date and Time</th>
										<th>Speed Km/Hr</th>
										<th>Sim Number</th>
									</tr>
								</thead>
							</table>

						</div>
						<div class="portlet-body" id="alerttampering" style="display: none">
							<table class="table table-striped table-bordered table-hover"
								id="vehicle_dashBoard_tampering_data">
								<thead>
									<tr>
										<th>Sr. No.</th>
										<th>Vehicle Number</th>
										<th>Date and Time</th>
										<th>Total Count</th>
									</tr>
								</thead>
							</table>

						</div>
						<div class="portlet-body" id="alert2" style="display: none">
							<table class="table table-striped table-bordered table-hover"
								id="vehicle_lateDeparture_data">
								<thead>
									<tr>
										<th>Sr. No.</th>
										<th>Vehicle no</th>
										<th>Schedule no</th>
										<th>Start Stop Name</th>
										<th>Shift no</th>
										<th>Schedule Departure time</th>
										<th>Actual Departure time</th>
										<th>Time difference</th>
									</tr>
								</thead>
							</table>
						</div>			
						<div class="portlet-body" id="alert" style="display: none">
							<table class="table table-striped table-bordered table-hover"
								id="vehicle_Alerrt_data">
								<thead>
									<tr>
										<th>#</th>
										<th>Vehicle Number</th>
										<th>Alert Code</th>
										<th>Alert Desc</th>
										<th>Count</th>
										<th>Sch. No.</th>
									</tr>
								</thead>
							</table>
						</div>
						<div class="portlet-body" id="alertDev" style="display: none">
							<table class="table table-striped table-bordered table-hover"
								id="vehicle_dashBoard_deviation_data">
								<thead>
									<tr>
		 								<th>Sr. No.</th>
		 								<th>Vehicle Number</th>
										<th>Sch. No.</th>
										<th>Shift</th>
									</tr>
								</thead>
							</table>

						</div>
						<div class="portlet-body" id="alert2" style="display: none">
						<div class="col-md-2">
						</div>
							<table class="table table-striped table-bordered table-hover"
								id="vehicle_lateDeparture_data">
								<thead>
									<tr>
										<th>#</th>
										<th>Vehicle no</th>
										<th>Schedule no</th>
										<th>Shift no</th>
										<th>Schedule Departure time</th>
										<th>Actual Departure time</th>
										<th>Time difference</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<!-- END EXAMPLE TABLE PORTLET-->
			</div>
		</div>

		<!-- END PAGE CONTENT-->
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
									<div class="portlet-body" id="withoutbusstop" style="display: none">
										<table class="table table-striped table-bordered table-hover"
											id="vehicleDetails123">
											<thead>
												<tr>
													<th>#</th>
													<th>Vehicle Number:</th>
													<th>Device ID:</th>
													<th>Speed:</th>
													<th>Lattitude:</th>
													<th>Longitude:</th>
													<th>GPS Date:</th>
													
												</tr>
											</thead>
										</table>
										</div>
										<div class="portlet-body" id="withbusstop" style="display: none">
										<table class="table table-striped table-bordered table-hover"
											id="vehicleDetails1234" >
											<thead>
												<tr>
													<th>#</th>
													<th>Vehicle Number:</th>
													<th>Device ID:</th>
													<th>Speed:</th>
													<th>Lattitude:</th>
													<th>Longitude:</th>
													<th>GPS Date:</th>
													<th>Stop Name:</th>
												</tr>
											</thead>
										</table>
									</div>
									<div class="portlet-body" id="alert5" style="display: none">
										<table class="table table-striped table-bordered table-hover"
											id="skipStopAlert_data1">
											<thead>
												<tr>
													<th>#</th>
													<th>Trip No</th>
													<th>Time Of Skipping</th>
													<th>BUS STOP NAME SKIPPED</th>
												</tr>
											</thead>
										</table>

									</div>
									<div class="portlet-body" id="tamperingdetails" style="display: none">
										<table class="table table-striped table-bordered table-hover"
											id="vehicleDetailsTampering">
											<thead>
												<tr>
													<th>#</th>
													<th>Vehicle no</th>
													<th>Date</th>
												</tr>
											</thead>
										</table>
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
