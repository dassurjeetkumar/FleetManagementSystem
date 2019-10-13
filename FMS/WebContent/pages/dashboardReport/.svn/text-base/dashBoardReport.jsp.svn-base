<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="assets/admin/pages/scripts/table-managed-dashboardReport.js"></script>

<script>
function displayData(){
	var from=document.getElementById('fromDate').value;
	//alert("from--------"+from);
	var to=document.getElementById('toDate').value;
	//alert("to--------"+to);
	var depotid=document.getElementById('depotid').value;
	//alert("depotid--------@@@@"+depotid);
	getRouteDeviationForDashBoard(from,to,depotid);
	summuryReport(from,to,depotid);
	getTripCancelationDashBoard(from,to,depotid);
	getKmCancelationDashBoard(from,to,depotid);
	getAccidentRegisterDashBoard(from,to,depotid);
	getTotalScheduleDashBoard(from,to,depotid);
	getLicenceDetailsDashBoard(from,to,depotid);
	console.log("ddddd"+from+to+depotid);

}

function getDepot(orgId){
		//var orgId=document.getElementById('divisionlist').value;
			 if(orgId!=0) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/getDepot?val='+orgId,
				success : function(result) {
					document.getElementById('depotid').innerHTML = result;
				}
			});
		}

	}
</script>
<form action="insertSOS.action" class="form-horizontal ">

	<div class="page-content-wrapper">

		<div class="page-content">
		<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>TRAFFIC SECTION
							</div>
							
						</div>
						
						<div class="portlet-body form">
						<s:if test="hasActionErrors()">
							<div class="alert alert-danger">
								<button class="close" data-close="alert"></button>
								<span> <s:actionerror />
								</span>
							</div>
						</s:if>
			
			<div class="portlet-body form">
				<div class="row" style="overflow: hidden;">
					<div class="col-md-2" style="overflow: hidden;"></div>
				</div>
				<input type="hidden" name="orgtypeid" id="orgtypeid_id" value="<s:property value="orgtypeid"/>"/>
				<input type="hidden" name="orgchartid" id="org_id" value="<s:property value="orgchartid"/>"/>
				<!-- row div ended here  -->
				<div class="form-body">
					<div class="form-group" id="divisionid">
						<label class="col-md-3 control-label">Division<font
							color="red">*</font></label>
						<div class="col-md-4">
							<s:select list="divisionList" id="divisionlist"
								name="orgchart.org_chart_id"
								cssClass="select2_category form-control" headerKey="0"
								headerValue="Division" onchange="getDepot(this.value)"></s:select>
						</div>
					</div>
				

				<div class="form-group" id="depotidd"> 
					<label class="col-md-3 control-label">Depot<font
						color="red">*</font></label>
					<div class="col-md-4">
						<select id="depotid" class="select2_category form-control">
							<option value="0">Depot</option>
						</select>
					</div>
				</div>


				<div class="form-group">
					<label class="control-label col-md-3">Select Date Range</label>
					<div class="col-md-3">
						<div class="input-group input-large date-picker input-daterange"
							data-date="2014-10-16" data-date-format="yyyy-mm-dd">
							<input type="text" class="form-control" id="fromDate"
								name="fromDate">
								<span class="input-group-addon">
								to </span> <input type="text" class="form-control" id="toDate"
								name="toDate">


						</div>

						<!-- /input-group -->

						<span class="help-block"> Select date range</span>

					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-md-3"> </label>
					<div class="col-md-3" style="text-align: center">
						<button type="button" class="btn blue" onClick="displayData();">Submit</button>
					</div>
				</div>
    </div>
				<div class="portlet box blue">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i>Route Deviation
						</div>
						<div class="tools">
							<a class="collapse" id="route_deviation_data" href="javascript:;"> </a>
						</div>
					</div>
					<div class="portlet-body" id="route_deviation_id"
						style="overflow: hidden;">
						<div class="portlet-body" id="scheduleprint" style='height:310px; overflow-y:scroll; display:block;'>
							<table class="table table-striped table-bordered table-hover"
								id="routeDeviationId" style="max-height: 60px;">
								<thead>
									<tr>
										<th>Sl.no</th>	 	 		 	
										<th>Schedule No </th>
										<th>Schedule type</th>
										<th>Vehicle No</th>
										<th>Deviated Trips </th>
										<th>Deviation From</th>
										<th>Deviation Till</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				
				<div class="portlet box blue">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i>Trip Cancelation
						</div>
						<div class="tools">
							<a class="collapse" id="sos_call_data" href="javascript:;"> </a>
						</div>
					</div>
					<div class="portlet-body" id="trip_Cancelation_id"
						style="overflow: hidden;">
						<div class="portlet-body" id="scheduleprint" style='height:310px; overflow-y:scroll; display:block;'>
									<table class="table table-striped table-bordered table-hover"
										id="tripCancelationId" style="max-height: 60px;">
										<thead>
											<tr>
												<th>Sl.no</th>
												<th>Schedule No</th>
												<th>Schedule type</th>
												<th>Vehicle No</th>
												<th>Total Trips</th>
												<th>Actual Trips</th>
												<th colspan="2">Cancelled trips/Partial trips</th>
											</tr>
											<tr>
											    <th></th>
											    <th></th>
											    <th></th>
											    <th></th>
											    <th></th>
											    <th></th>
											    <th>From</th>
												<th>To</th>
											</tr>
										</thead>
									</table>
								</div>
					</div>
				</div>
				 		
				<div class="portlet box blue">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i>K.M. Cancelation 
						</div>
						<div class="tools">
							<a class="collapse" id="sos_call_data" href="javascript:;"> </a>
						</div>
					</div>
					<div class="portlet-body" id="K_M_Cancelation_id"
						style="overflow: hidden;">
						<div class="portlet-body" id="scheduleprint" style='height:310px; overflow-y:scroll; display:block;'>
									<table class="table table-striped table-bordered table-hover"
										id="KMCancelationId" style="max-height: 60px;">
										<thead>
											<tr>
												<th>Sl.no</th>
												<th>Schedule No</th>
												<th>Schedule type</th>
												<th>Vehicle No</th>
												<th>Total KMS</th>
												<th>Actual KMs</th>
												<th>Cancelled KMs</th>
											</tr>
											
										</thead>
									</table>
								</div>
					</div>
				</div>
				
				<div class="portlet box blue">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i>Crew Positation of Traffic
						</div>
						<div class="tools">
							<a class="collapse" id="sos_call_data" href="javascript:;"> </a>
						</div>
					</div>
					<div class="portlet-body" id="crew_Positation_id"
						style="overflow: hidden;">
						<div class="portlet-body" id="scheduleprint" style='height:310px; overflow-y:scroll; display:block;'>
									<table class="table table-striped table-bordered table-hover"
										id="crewPositationId" style="max-height: 60px;">
										<thead>
											<tr>
												<th>Crew</th>
												<th>Regular</th>
												<th>Trinee</th>
												<th>Total</th>											
											</tr>
											<tr>
											<th>Conductors</th>
											</tr>
											<tr>
											<th>Drivers</th>
											</tr>
											<tr>
											<th>Driver-Cum-Conductors</th>
											</tr>
											<tr>
											<th>Grand total</th> 
											</tr>
										
	
										</thead>
									</table>
								</div>
					</div>							
				</div>
			
				<div class="portlet box blue">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i>	Total Schedules
						</div>
						<div class="tools">
							<a class="collapse" id="sos_call_data" href="javascript:;"> </a>
						</div>
					</div>
					<div class="portlet-body" id="schedules_id"
						style="overflow: hidden;">
						<div class="portlet-body" id="scheduleprint" style='height:310px; overflow-y:scroll; display:block;'>
									<table class="table table-striped table-bordered table-hover"
										id="scheduletableId" style="max-height: 60px;">
										<thead>
											<tr>
												<th>Schedule Type </th>
												<th>Total</th>	 	
	
											</tr>
											<!-- <tr>
											<th>Shifts</th>
											</tr>
											<tr>
											<th>General Shifts</th>
											</tr>
											<tr>
											<th>Night Out</th>
											</tr>
											<tr>
											<th>Night Service</th> 
											</tr> -->
											<!--  <tr>
											<th>Grand Total	</th> 
											</tr>  -->
										
										</thead>
									</table>
								</div>
					</div>							
				</div>
				
				<div class="portlet box blue">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i>	Crew Calculation 
							</div>
						<div class="tools">
							<a class="collapse" id="sos_call_data" href="javascript:;"> </a>
						</div>
					</div>
					<div class="portlet-body" id="crew_calculation_id"
						style="overflow: hidden;">
						<div class="portlet-body" id="scheduleprint" style='height:310px; overflow-y:scroll; display:block;'>
									<table class="table table-striped table-bordered table-hover"
										id="crewcalculationId" style="max-height: 60px;">
										<thead>
											<tr>
												<th colspan="">Schedule Type </th>
												<th colspan="">No Of Schedules </th>
												<th colspan="3">Crew Sanction</th>	 
												<th>Total</th>	
											</tr>	
											<tr>
												<th></th>
												<th></th>
												<th>Conductor </th>	 
												<th>Driver</th>	
												<th></th>
											</tr>
											<tr>
											<th>Shifts</th>
											</tr>
											<tr>
											<th>General Shifts</th>
											</tr>
											<tr>
											<th>Night Out</th>
											</tr>
											<tr>
											<th>Night Service</th> 
											</tr>
											<tr>
											<th rowspan="">Grand Total	</th> 
											</tr>
										
										</thead>
									</table>
								</div>
					</div>							
				</div>
				<div class="portlet box blue">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i>Accident Register
						</div>
						<div class="tools">
							<a class="collapse" id="sos_call_data" href="javascript:;"> </a>
						</div>
					</div>
					<div class="portlet-body" id="accident_Register_id"
						style="overflow: hidden;">
						<div class="portlet-body" id="scheduleprint" style='height:310px; overflow-y:scroll; display:block;'>
									<table class="table table-striped table-bordered table-hover"
										id="accidentRegisterId" style="max-height: 60px;">
										<thead>
											<tr>
												<th>Sl.no</th>	 	 	 	 	 		
												<th>Date</th>
												<th>Route no.</th>
												<th>Driver Name</th>
												<th>Token no.</th>
												<th>Vehicle no.</th>
												<th>Accident details </th>
												<th>Remarks</th>
											</tr>
											
										</thead>
									</table>
								</div>
					</div>
				</div> 		
				<div class="portlet box blue">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i>Licence Details 
						</div>
						<div class="tools">
							<a class="collapse" id="sos_call_data" href="javascript:;"> </a>
						</div>
					</div>
					<div class="portlet-body" id="licence_Details_id"
						style="overflow: hidden;">
						<div class="portlet-body" id="scheduleprint" style='height:310px; overflow-y:scroll; display:block;'>
									<table class="table table-striped table-bordered table-hover"
										id="licenceDetailsId" style="max-height: 60px;">
										<thead>
											<tr>
												<th>Sl.no</th>		 	 	 		
												<th>Name</th>
												<th>Token No</th>
												<th>Licence No </th>
												<th>Place of RTO </th>
												<th>Expire Date </th>
												<th>Till Date</th>
												<th>Remarks</th>
											</tr>
											
										</thead>
									</table>
								</div>
					</div>
				</div>
				
				
				<!-- <div class="portlet box blue">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i>	Summary of No of Crews
							</div>
						<div class="tools">
							<a class="collapse" id="sos_call_data" href="javascript:;"> </a>
						</div>
					</div>
					<div class="portlet-body" id="summary_id"
						style="overflow: hidden;">
						<div class="scroller" style="height: 200px"
							data-always-visible="1" data-rail-visible="1"
							data-rail-color="blue" data-handle-color="blue">
									<table class="table table-striped table-bordered table-hover"
										id="summaryId" style="max-height: 60px;">
										<thead>
											<tr>
											<th>1 </th>
												<th>No of Execess Crew 	 </th>
												</tr>
												<tr>
												<th>2 </th>
												<th>No of Shortage Crew </th>
												</tr>
												<tr>
												<th>3 </th>
												<th>OOD</th>	 
												</tr>
												<tr>
												<th>4 </th>
												<th>KOD/SPE</th>
												</tr>
												<tr>
												<th>5 </th>
												<th>weekly off</th>
												</tr>
												<tr>
												<th>6 </th>
												<th>Long leave </th>
												</tr>
												<tr>
												<th>7 </th>
												<th>Long Absent	</th>
												</tr>
												<tr>
												<th>8 </th>
												<th>Daily Absent</th>
												</tr>
												<tr>
												<th>9 </th>
												<th>Leave</th>
												</tr>
												<tr>
												<th>10 </th>
												<th>Enquiry</th>
												</tr>
												<tr>
												<th>11 </th>
												<th>OT</th>
												</tr>
												<tr>
												<th>12 </th>
												<th>Others</th>
												</tr>
										</thead>
									</table>
								</div>
					</div>							
				</div> -->
				
				<!-- //summer report start -->
				<div class="portlet box blue">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i>Summary Report :
							</div>
						<div class="tools">
							<a class="collapse" id="sos_call_data" href="javascript:;"> </a>
						</div>
					</div>
					<div class="portlet-body" id="route_deviation_id"
						style="overflow: hidden;">
						<div class="portlet-body" id="scheduleprint" style='height:310px; overflow-y:scroll; display:block;'>
									<table class="table table-striped table-bordered table-hover"
										id="summeryreport_id" style="max-height: 60px;">
										<thead>
<!-- 										<tr> -->
<!-- 										<th colspan="2">summary Report</th>  -->
										
<!-- 										</tr> -->
											<tr>
												<th id="th_1"></th>
												<th id="th_2"></th>
												 
												
											</tr> 
										</thead>
									</table>
								</div>
					</div>							
				</div>
				<!-- //end summury report -->
				
				
								
			</div>
		</div>
		<!-- page content div end  -->
	</div>	
	
	</div>
	</div>
</form>		
<script>
	$("#mnu_header li").removeClass("classic-menu-dropdown active");
	$("#mnu_dashboard").addClass("classic-menu-dropdown active");
	$("#mnu_header li").addClass("classic-menu-dropdown");
	$("#dashboard_selected").addClass("selected");
	$(document).ready(function(){
		var orgtypeid_id=$("#orgtypeid_id").val();
		var org_id=$("#org_id").val();
		//alert("orgtypeid_id"+orgtypeid_id+"org_id........."+org_id);
	  if(orgtypeid_id==3)
		{
		//  alert("orgtypeid_id@"+orgtypeid_id+"org_id@........."+org_id);
		$("#divisionid").attr("style", "display:none");
		$("#depotidd").attr("style", "display:none");
		//set depotid value
			$("#depotid").html("<option value='"+org_id+"' selected></option>");
		
		} 
	  if(orgtypeid_id==2)
		{
			//alert("orgtypeid_id111"+orgtypeid_id);
		$("#divisionid").attr("style", "display:none");
		//$("#depotid").attr("style", "display:none");
		getDepot(org_id);
		} 
	  $("#th_1").hide();
	  $("#th_2").hide();
	});
</script>
<script>
function summuryReport(fromdate,todate,depotid)
{
	//
	//alert("2"+fromdate+""+todate+""+depotid);
	//alert("depotid"+depotid);
	 $('#summeryreport_id').dataTable({
		 
		 "bPaginate": false,
	        "bFilter": false,
	        "bInfo": false,
			"aaSorting": [],
         "aLengthMenu": [[10,25, 50, 100],[10,25, 50, 100]],
         // set the initial value
           "bDestroy": true,
         "iDisplayLength": 10,
         "bProcessing" : true,
         "bServerSide" : true,
         "sAjaxSource" : "SummaryReportShow.action?fromdate="+fromdate+"&todate="+todate+"&depotid="+depotid,
         "sPaginationType": "bootstrap",
         "oLanguage": {
             "sLengthMenu": "_MENU_ records",
             "oPaginate": {
                 "sPrevious": "Prev",
                 "sNext": "Next"
             }
         },
         "columnDefs": [ { "targets": 0, "orderable": false } ],
        "aoColumnDefs": [
             { 'bSortable': false, 'aTargets': [0] },
             { "bSearchable": false, "aTargets": [0] }
         ]
         
     });
		jQuery('#memonoticeView_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
     jQuery('#memonoticeView_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	//
	

}


</script>

