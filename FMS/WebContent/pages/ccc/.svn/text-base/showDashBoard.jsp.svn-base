<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <script type="text/javascript" src="assets/ccc/js/ccc.js"></script>
<script type="text/javascript" src="assets/ccc/js/ccc-charts.js"></script> --%>
<script>
function getSosCount()
{
	var fromDate=document.getElementById("fromDate").value;
	var toDate=document.getElementById("toDate").value;
	document.getElementById("alert_soscount_display").style.display = '';
	if(fromDate =="" && toDate=="")
		{
			bootbox.alert("Please Select Date Range");
			return false;
		}
	$('#SOS_ATTENDED').dataTable(
				{
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					"iDisplayLength" : 5,
					"sAjaxSource" : "getSosattendedUnattended.action?fromDate="+fromDate+"&toDate="+toDate,
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
		jQuery('#SOS_ATTENDED_wrapper .dataTables_filter input').addClass(
				"form-control input-medium input-inline"); // modify table
		// search input
		jQuery('#SOS_ATTENDED_wrapper .dataTables_length select').addClass(
				"form-control input-xsmall input-inline");
		
// search input
}
function getSosUnAttendedDetails()
{
	document.getElementById("alert_sosdetails_display").style.display = '';
	var fromDate=document.getElementById("fromDate").value;
	var toDate=document.getElementById("toDate").value;
	if(fromDate =="" && toDate=="")
		{
			bootbox.alert("Please Select Date Range");
			return false;
		}
	$('#SOS_ATTENDED_UNATTENDED').dataTable(
				{
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					"iDisplayLength" : 5,
					"sAjaxSource" : "getSosUnAttendedDetails.action?fromDate="+fromDate+"&toDate="+toDate,
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
		jQuery('#SOS_ATTENDED_UNATTENDED_wrapper .dataTables_filter input').addClass(
				"form-control input-medium input-inline"); // modify table
		// search input
		jQuery('#SOS_ATTENDED_UNATTENDED_wrapper .dataTables_length select').addClass(
				"form-control input-xsmall input-inline");
		
// search input
}
function getSOSData(call_id, device_id, call_time, driver_name,
		conductor_name, vehicle_no, route_no, sch_no, depot_no) {
	$("#vehicle_no").val(vehicle_no);
	/* var  vehicle ='abc';
		//$("#vehicle_no").val();
	alert(vehicle);
	$
	.ajax({
		url : "getSOSData.action",
		data : "vehical="+vehicle,
		type: 'json',
		success : function(result) {
			//alert(result);
			var obj = jQuery.parseJSON(result);
			//alert(obj);
			alert(obj["aaData1"][0][0]+"and "+obj["aaData1"][0][1]);
			document.getElementById("depot_name").value =obj["aaData"][0][0];
			document.getElementById("route_no").value =obj["aaData1"][0][1];
			document.getElementById("schedule_no").value =obj["aaData1"][0][0];
		}
	}); */
	$("#call_id").val(call_id);
	$("#device_id").val(device_id);
	$("#call_time").val(call_time);
	$("#driver_name").val(driver_name);
	$("#conductor_name").val(conductor_name);
	$("#vehicle_no").val(vehicle_no);
	$("#route_no").val(route_no);
	$("#schedule_no").val(sch_no);
	$("#depot_name").val(depot_no);

}
function getSosAttendedDetails()
{
	document.getElementById("alert_sosdetails_display").style.display = '';
	var fromDate=document.getElementById("fromDate").value;
	var toDate=document.getElementById("toDate").value;
	if(fromDate =="" && toDate=="")
		{
			bootbox.alert("Please Select Date Range");
			return false;
		}
	$('#SOS_ATTENDED_UNATTENDED').dataTable(
				{
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					"iDisplayLength" : 5,
					"sAjaxSource" : "getSosAttendedDetails.action?fromDate="+fromDate+"&toDate="+toDate,
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
		jQuery('#SOS_UNATTENDED_wrapper .dataTables_filter input').addClass(
				"form-control input-medium input-inline"); // modify table
		// search input
		jQuery('#SOS_UNATTENDED_wrapper .dataTables_length select').addClass(
				"form-control input-xsmall input-inline");
		
// search input
}
function check() {
	var val = $("#call_type option:selected").val();

	if (val == 1) {
		var divId = document.getElementById("breakdown_type");
		divId.style.display = 'none';
		var divId = document.getElementById("accident_type");
		divId.style.display = '';
		var type = 'accident';
		$.ajax({
			url : "getSOSType.action",
			data : "type=" + type,
			success : function(result) {

				document.getElementById('depotlist').innerHTML = result;
			}
		});
	} else {
		var divId1 = document.getElementById("accident_type");
		divId1.style.display = 'none';
		var divId = document.getElementById("breakdown_type");
		divId.style.display = '';

		var type = 'breakdown';
		$.ajax({
			url : "getSOSType.action",
			data : "type=" + type,
			success : function(result) {

				document.getElementById('vehiclelist').innerHTML = result;
			}
		});

	}
}
</script>
<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places,drawing"></script>
<script type="text/javascript" src="//www.google.com/jsapi"></script>

<form action="insertSOS.action" class="form-horizontal ">

	<div class="page-content-wrapper"
		>
	
		<div class="page-content">
			<s:if test="hasActionMessages()">
   <div class="alert alert-success alert-dismissable">
								<strong> <s:actionmessage/> </strong>
							</div>
      
   </s:if>
			<div class="portlet-body form">
				<div class="row" style="overflow: hidden;">
					<div class="col-md-2" style="overflow: hidden;">
					<!-- 	<div class="portlet box blue">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-gift"></i> Vehicle
								</div>
								<div class="tools"></div>
							</div>
							<div class="portlet-body" id="vehicle_live_display">
								<H4> Total Vehicle Data</H4>
								<div class="scroller" style="height: 200px"
									data-always-visible="1" data-rail-visible="1"
									data-rail-color="blue" data-handle-color="blue">
									<div id="vehicle_display" class="chart"></div>
								</div>
							</div>
						</div>
					</div> -->
					<!-- <div class="col-md-4" style="height: 10%;">
						<div class="portlet box red" style="height: 20%;">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-alert"></i>Alerts
								</div>
								<div class="tools"></div>
							</div>
							<div class="portlet-body">
								<div class="scroller" style="height: 200px"
									data-always-visible="1" data-rail-visible="1"
									data-rail-color="blue" data-handle-color="red">
									<table class="table table-striped table-bordered table-hover"
										id="Alert">
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
					</div> -->
					<!-- <div class="col-md-4">
						<div class="portlet box green">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-gift"></i> Live Tracking
								</div>
							</div>
							<div class="portlet-body" id="alert_vehicle_display_chart">
								<div class="portlet-body"  align="center">
								<div class="scroller" style="height: 200px"
									data-always-visible="1" data-rail-visible="1"
									data-rail-color="blue" data-handle-color="green">
									<div id="gmap_basic_1" class="gmaps" style="height: 200%"></div>
								</div>
							</div>
						</div>-->
					</div> 
				</div>
				<!-- row div ended here  -->
				<!-- SOS ATTENDED/UNATTENDED CALL DETAILS -->
				<div class="form-group">
					<label class="control-label col-md-3" >Select Date Range</label>
					<div class="col-md-3" >
						<div class="input-group input-large date-picker input-daterange"
							data-date="2014-10-16" data-date-format="yyyy-mm-dd">
							<input type="text" class="form-control" id="fromDate"
								name="fromDate"><span class="input-group-addon">
								to </span> <input type="text" class="form-control" id="toDate"
								name="toDate">
								
								
						</div>
						
						<!-- /input-group -->
						
						<span class="help-block"> Select date range for Sos Count </span>
						
					</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3" >                                         </label>
						<div class="col-md-3" style="text-align: center">
							<button type="button" class="btn blue" onClick="getSosCount();">Submit</button>
						</div>
					</div>
				<div class="portlet box blue" id="alert_soscount_display"
					style="display: none;">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i> SOS Counts
						</div>
						<div class="tools">
							<a class="collapse" id="sos_attended_data" href="javascript:;">
							</a>
						</div>
					</div>
					<div class="portlet-body" style="overflow: hidden;">
						<div class="scroller" style="height: 200px"
							data-always-visible="1" data-rail-visible="1"
							data-rail-color="blue" data-handle-color="blue">
							<table class="table table-striped table-bordered table-hover"
								id="SOS_ATTENDED" style="max-height: 60px;">
								<thead>
									<tr>
										<th>Attended Calls</th>
										<th>Unattended Calls</th>
										<th>Toal</th>
										<!-- <th>#</th>
										<th>Call Id </th>
										<th>Operator Name </th>
										<th>Status</th> -->
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<div class="portlet box blue" id="alert_sosdetails_display"
					style="display: none;">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i> SOS ATTENDED/UNATTENDED CALL DETAILS
						</div>
						<div class="tools">
							<a class="collapse" id="sos_attendeddetails_data"
								href="javascript:;"> </a>
						</div>
					</div>
					<div class="portlet-body" style="overflow: hidden;">
						<div class="scroller" style="height: 200px"
							data-always-visible="1" data-rail-visible="1"
							data-rail-color="blue" data-handle-color="blue">

							<table class="table table-striped table-bordered table-hover"
								id="SOS_ATTENDED_UNATTENDED" style="max-height: 60px;">
								<thead>
									<tr>
										<th>Attended by</th>
										<th>Status</th>
										<th>Vehicle No.</th>
										<th>Device ID</th>
										<th>Start Time</th>
										<th>END TIME</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<!-- <div class="row" style="overflow: hidden;">
					<div class="col-md-12" style="overflow: hidden;"> -->
				<div class="portlet box blue">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i> SOS Call Data
						</div>
						<div class="tools">
							<a class="collapse" id="sos_call_data" href="javascript:;"> </a>
						</div>
					</div>
					<div class="portlet-body" id="alert_vehicle_display_chart"
						style="overflow: hidden;">
						<div class="scroller" style="height: 200px"
							data-always-visible="1" data-rail-visible="1"
							data-rail-color="blue" data-handle-color="blue">
							<table class="table table-striped table-bordered table-hover"
								id="SOS_Alert" style="max-height: 60px;">
								<thead>
									<tr>
										<th>Call Id</th>
										<th>Device ID</th>
										<th>VehiCle Number</th>
										<th>Call Time</th>
										<th>Driver Name</th>
										<th>Conductor Name</th>
										<th>Status</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<!-- </div>
				</div> -->
				<div class="row" style="overflow: hidden;">
					<div class="col-md-12" style="overflow: hidden;">
						<div class="portlet box blue">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-gift"></i> Revenue Details
								</div>
								<div class="tools">
									<a class="collapse" id="sos_call_data" href="javascript:;">
									</a>
								</div>
							</div>
							<div class="portlet-body" id="alert_vehicle_display_chart"
								style="overflow: hidden;">
								<div class="scroller" style="height: 200px"
									data-always-visible="1" data-rail-visible="1"
									data-rail-color="blue" data-handle-color="blue">
									<table class="table table-striped table-bordered table-hover"
										id="revenue_org">
										<thead>
											<tr>
												<th>Organization Name</th>
												<th>Total Revenue</th>
												<th>Total Passangers</th>
											</tr>
										</thead>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row" style="overflow: hidden;">
					<div class="col-md-12" style="overflow: hidden;">
						<div class="portlet box blue">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-gift"></i> Manual Ticketing
								</div>
								<div class="tools">
									<a class="collapse" id="Manual_ticketing" href="javascript:;">
									</a>
								</div>
							</div>
							<div class="portlet-body" id="alert_vehicle_display_chart"
								style="overflow: hidden;">
								<div class="scroller" style="height: 200px"
									data-always-visible="1" data-rail-visible="1"
									data-rail-color="blue" data-handle-color="blue">
									<table class="table table-striped table-bordered table-hover"
										id="manu_ticket">
										<thead>
											<tr>
												<th>Serial No</th>
												<th>Organization Name</th>
												<th>Total Revenue</th>
												<th>Total Passangers</th>
											</tr>
										</thead>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- page content div end  -->
	</div>
	<!-- page wrapper div -->
	<!-- </div> -->
	<div style="display: none;" id="mymodal1" class="modal fade"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 800px; height: 900px;"
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
									<div class="form-group">
									
									<div class="form-group">
										<label class="col-md-3 control-label">Call Id </label>
										<div class="col-md-4">
											<input type="text" class="form-control" id="call_id"
												name="call_id" readonly="readonly" value=''></input>
										</div>
										</div><input type="hidden" id="trip_no" name="trip_no"/>
										<input type="hidden" id="sch_dt" name="sch_dt"/>
										<div class="form-group">
											<label class="col-md-3 control-label">Device Id </label>
											<div class="col-md-4">
												<input type="text" class="form-control" id="device_id"
													name="device_id" readonly="readonly" value=''></input>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-3 control-label">Call Time</label>
											<div class="col-md-4">
												<input type="text" class="form-control" id="call_time"
													name="call_time" readonly="readonly" value=''></input>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-3 control-label">Driver Name </label>

											<div class="col-md-4">
												<input type="text" class="form-control" id="driver_name"
													name="driver_name" readonly="readonly" value=''></input>
											</div>
										</div>
										<div class="form-group">
										<div class="form-group">
											<label class="col-md-3 control-label">Conductor Name</label>

											<div class="col-md-4">
												<input type="text" class="form-control" id="conductor_name"
													name="conductor_name" readonly="readonly" value=''></input>
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
												<label class="col-md-3 control-label">Route No </label>

												<div class="col-md-4">
													<input type="text" class="form-control" id="route_no"
														name="route_no" readonly="readonly" value=''></input>
												</div>
											</div>

											<div class="form-group">
												<label class="col-md-3 control-label">Depot Name</label>

												<div class="col-md-4">
													<input type="text" class="form-control" id="depot_name"
														name="depot_name" readonly="readonly"></input>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">Schedule No</label>
												<div class="col-md-4">
													<input type="text" class="form-control" id="schedule_no"
														name="schedule_no" readonly="readonly" value=''></input>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">Call Type </label>
												<div class="col-md-4">
													<select id="call_type"
														class="select2_category form-control" onchange="check() "
														name="call_type">
														<option value="0">-----Select----</option>
														<option value="1">Vehicle Accident</option>
														<option value="2">Vehicle Breakdown</option>
													</select>
												</div>
											</div>
											<div class="form-body">
												<div class="form-group" id="accident_type"
													style="display: none;">
													<label class="col-md-3 control-label">Accident
														Type: </label>
													<div class="col-md-4">
														<select id="depotlist"
															class="select2_category form-control" onchange=""
															name="depotlist">
															<option value="0">-----Select----</option>
														</select>
													</div>
												</div>
											</div>
											<div class="form-body">
												<div class="form-group" id="breakdown_type"
													style="display: none;">
													<label class="col-md-3 control-label">Breakdown
														Type: </label>
													<div class="col-md-4">
														<select id="vehiclelist"
															class="select2_category form-control" name="vehiclelist">
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
										</div>
										<br>
										<br>
										<div class="form-group">
											<div class="form-actions fluid">
												<div class="col-md-offset-3 col-md-9">
													<button type="submit" class="btn blue">Save</button>
													<button type="button" class="btn default" onclick="window.close()">Cancel</button>
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
	<div style="display: none;" id="mymodal_depot" class="modal fade"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content"
				style="width: 800px; height: 400px; overflow: auto;" align="justify">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 id="vehicleno123" class="modal-title">Revenue By Depot</h4>
				</div>
				<div>
					<p>
					<div class="portlet box white ">
						<div>
							<input type="hidden" name="requestType" id="requestType" />
							<div>
								<div class="portlet solid white">
									<div class="form-group">
										<div class="portlet-body" id="depot_revenue">
											<table class="table table-striped table-bordered table-hover"
												id="revenue_depot">
												<thead>
													<tr>
														<th>Depot Name</th>
														<th>Total Revenue</th>
														<th>Total Passangers</th>
													</tr>
												</thead>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div style="display: none;" id="mymodal_waybill" class="modal fade"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content"
				style="width: 800px; height: 400px; overflow: auto;" align="justify">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 id="vehicleno123" class="modal-title">Revenue By WayBill</h4>
				</div>
				<div>
					<p>
					<div class="portlet box white ">
						<div>
							<input type="hidden" name="requestType" id="requestType" />
							<div>
								<div class="portlet solid white">
									<div class="form-group">
										<div class="portlet-body" id="depot_revenue">
											<table class="table table-striped table-bordered table-hover"
												id="revenue_way">
												<thead>
													<tr>
														<th>Way Bill_NO</th>
														<th>Total Revenue</th>
														<th>Total Passangers</th>
													</tr>
												</thead>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div style="display: none;" id="mymodal_ticket_depot"
		class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel1" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content"
				style="width: 800px; height: 400px; overflow: auto;" align="justify">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 id="vehicleno123" class="modal-title">Ticket Detail Depot</h4>
				</div>
				<div>
					<p>
					<div class="portlet box white ">
						<div>
							<input type="hidden" name="requestType" id="requestType" />
							<div>
								<div class="portlet solid white">
									<div class="form-group">
										<div class="portlet-body" id="depot_revenue">
											<table class="table table-striped table-bordered table-hover"
												id="ticket_depot">
												<thead>
													<tr>
														<th>Serial No.</th>
														<th>Depot Name</th>
														<th>Total Revenue</th>
														<th>Total Passangers</th>
													</tr>
												</thead>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div style="display: none;" id="mymodal_ticket_waybill"
		class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel1" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content"
				style="width: 800px; height: 400px; overflow: auto;" align="justify">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 id="vehicleno123" class="modal-title">Ticket By WayBill</h4>
				</div>
				<div>
					<p>
					<div class="portlet box white ">
						<div>
							<input type="hidden" name="requestType" id="requestType" />
							<div>
								<div class="portlet solid white">
									<div class="form-group">
										<div class="portlet-body" id="depot_revenue">
											<table class="table table-striped table-bordered table-hover"
												id="ticket_way">
												<thead>
													<tr>
														<th>Serial No.</th>
														<th>Way Bill No.</th>
														<th>Total Revenue</th>
														<th>Total Passangers</th>
													</tr>
												</thead>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form>
<script>
	/* google.maps.event.addDomListener(window, 'load', initialize); */
	$("#mnu_header li").removeClass("classic-menu-dropdown active");
	$("#mnu_ccc").addClass("classic-menu-dropdown active");
	$("#mnu_header li").addClass("classic-menu-dropdown");
	$("#ccc_selected").addClass("selected");

	
</script>