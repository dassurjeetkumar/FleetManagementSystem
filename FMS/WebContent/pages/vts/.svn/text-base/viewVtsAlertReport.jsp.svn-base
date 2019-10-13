
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<script src="assets/admin/pages/scripts/charts.js"
	type="text/javascript"></script>
<script src="assets/admin/pages/scripts/vehiclealert.js"
	type="text/javascript"></script>
<script>
function getHide(){	
	 $("#vtsAlertReportDivId").hide();
	 $("#accidentAlertDivId").hide();
		
}

</script>
<style>
</style>
</head>
<script src="assets/vts/js/vehiclealert.js" type="text/javascript"></script>
<body onload="getHide()">
	<div class="page-content-wrapper">
		<div class="page-content">
			<div class="row">
					<div class="col-md-12">
						<h3 class="page-title">
	 					VTS ALERT REPORT <small></small>
						</h3>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="portlet box grey-cascade">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-globe"></i>View VTS Alert Report
								</div>
							</div>
							
						</div>
						 <div class="portlet-body">
									<input type="hidden" name="requestType" id="requestType"
									value="text" /> 
									<div class="portlet-body form">
										<div class="form-group">
											<div class="col-md-3">
												<div class="input-group input-medium date date-picker"
													data-date-format="dd-mm-yyyy" data-date-viewmode="years">
													<input class="form-control" type="text" size="10" name=""
															id="seldate" value='<s:property value=""/>' readonly>
													<span class="input-group-btn">
														<button class="btn default date-set form-control" type="button">
															<i class="fa fa-calendar"></i>
														</button>
													</span>
												</div>
											</div>
										</div>
										<div class="form-group">
											<div class="col-md-3">
												<s:select list="alertlist1" id="alertlist_id"
													cssClass="select2_category form-control" headerKey="0"
														headerValue="--Alert Name--" ></s:select>

											</div>
											</div>
											<div class="form-group">
											<div class="col-md-3">
												<s:select list="divisionlist1" id="divisionlistid1"
													cssClass="select2_category form-control" headerKey="00"
													headerValue="--Depot Name--" onchange="getAlertReport(this.value)">
												</s:select>
											</div>
										</div>
										<div class="form-group">
											<div class="col-md-3">
										<!-- <div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9"> -->
											<button type="button" class="btn default" onclick="getAlertReportOnSubmit()">Submit</button>
										<!-- </div>
									</div> -->
									</div>
										</div>
									</div>
							 </div>
			 				<br/><br/>
							<br/><br/>
							<div class="portlet-body" id="vtsAlertReportDivId" style="display: none">
							<table class="table table-striped table-bordered table-hover"
								id="vtsAlertReportId">

								<thead>
									<tr>
										<th style="width1: 8px;">SR. NO.</th>
				 						<th>Vehicle No</th>
										<th>Schedule No</th>
										<th>Depot Name</th>
										<th>Time</th>
										<th>Sim Number</th>
										<th>Location</th>

									</tr>
								</thead>

							</table>

						</div>
						<div class="portlet-body" id="accidentAlertDivId" style="display: none">
										<table class="table table-striped table-bordered table-hover"
											id="accidentAlertid">
											<thead>
												<tr>
													<th>Sr. No</th>
													<th>Vehicle No</th>
													<th>Device No</th>
													<th>Call Time</th>
													<th>Route No</th>
													<th>Driver Name</th>
													<th>Conductor Name</th>
													<th>Schedule No</th>
													
												</tr>
											</thead>
										</table>

									</div>
									<div class="portlet-body" id="scheduleReport" style="display: none">
										<table class="table table-striped table-bordered table-hover"
											id="schAlertid">
											<thead>
												<tr>
													<th>Sr. No</th>
													<th>Vehicle No</th>
													<th>Device ID</th>
													<th>Sch. Name</th>
													<th>Start Time</th>
													<th>Act. Time</th>
													<th>Time Diff</th>
													<th>Start Stop Name</th>
													<th>End Stop Name</th>
													
												</tr>
											</thead>
										</table>

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
										<table class="table table-striped table-bordered table-hover"
											id="vehicleDetails123">
											<thead>
												<tr>
													<th>Sr. No</th>
													<th>Vehicle Number:</th>
													<th>Device ID:</th>
													<th>Speed:</th>
													<th>Trip number:</th>
													<th>Schedule Time:</th>
													<th>Actual Time:</th>
													<th>GPS Date:</th>

												</tr>
											</thead>
										</table>
									</div>
									<div class="portlet-body" id="devdetails" style="display: none">
										<table class="table table-striped table-bordered table-hover"
											id="vehicleDetailsDev">
											<thead>
												<tr>
													<th>Sr. No</th>
													<th>Exit point Location:</th>
													<th>Entry point Location:</th>
													<th>Trip No:</th>
													<th>Deviated Distance Km:</th>
													<th>Exit Time:</th>
													<th>Entry Time:</th>
												</tr>
											</thead>
										</table>
									</div>
									<div class="portlet-body" id="tamperingdetails" style="display: none">
										<table class="table table-striped table-bordered table-hover"
											id="vehicleDetailsTampering">
											<thead>
												<tr>
													<th>Sr. No</th>
													<th>Vehicle no</th>
													<th>Date</th>
												</tr>
											</thead>
										</table>
									</div>
									
									<div class="portlet-body" id="withbusstop"
										style="display: none">
										<table class="table table-striped table-bordered table-hover"
											id="vehicleDetails1234">
											<thead>
												<tr>
													<th>Sr. No</th>
													<th>Vehicle Number:</th>
													<th>Device ID:</th>
													<th>Speed:</th>
													<th>Schedule_No:</th>
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
													<th>Sr. No</th>
													<th>Trip No</th>
													<th>Time Of Skipping</th>
													<th>BUS STOP NAME SKIPPED</th>
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
						
						
					</div>
				</div>
				</div>
				</div>
	</body>			
</html>
