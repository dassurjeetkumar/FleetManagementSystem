<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//www.google.com/jsapi"></script>


<script src="assets/vts/js/vehiclealert.js"></script>
<script src="assets/vts/js/vehiclealert.js"></script>
<script src="assets/global/plugins/jquery-1.11.0.min.js"
	type="text/javascript"></script>


<Script>
 $(document).ready(function(){
	 
	$("#printbutton").hide();



	 
 });
 
 function printDiv() {     
	 
	
	   document.getElementById("tripstatus").style.visibility='visible';  
     document.getElementById("header").style.display='block';
     document.getElementById("header").style.visibility='visible';     
     var divElements = document.getElementById("header").innerHTML;
     divElements+= document.getElementById("tripstatus").innerHTML;
     
    
  
     var mywindow = window.open("<%=request.getContextPath()%>/Print1.jsp");
    
     mywindow.onload = function() {
         mywindow.document.body.innerHTML=divElements;
         mywindow.document.body.innerHTML=divElements;
     
         mywindow.print();
         mywindow.close();
     }
     document.getElementById("header").style.display = 'none';
		document.getElementById("header").style.visibility = 'hidden';
		
     
             
 }
 var i=0;
 function getDepot(orgId){
		
		 var val=document.getElementById('divisionlist').value;
			 if(val!=0) {
	        $.ajax({
	            type: "get",
	            url: '<%=request.getContextPath()%>/getDepot?val=' + val,
				success : function(result) {
					document.getElementById('depotlist').innerHTML = result;
					 document.getElementById('select2-chosen-2').innerHTML="Depot";
				}
			});
		}

	}
	
	function validateTripStatusReportFields(depotNo, divisonNo, selDate) {
		if (divisonNo == 0) {
			bootbox.alert("Please Select Divison");
			return false;
		}
		if (depotNo == 0) {
			bootbox.alert("Please Select Depot");
			return false;
		}
		if (selDate == 0) {
			bootbox.alert("Please Select Date");
			return false;
		}

		return true;
	}

	function getUnauthorizedBreakLocationReportOnSubmit() {
		
		var depotNo = $("#depotlist").val();
		var divisonNo = $("#divisionlist").val();
		var selecteddate = $("#selecteddate").val();
		validateflag = validateTripStatusReportFields(depotNo, divisonNo,
				selecteddate);
		if (validateflag == true) {
			jQuery('#scheduleprint').css("display", "block");
			jQuery('#printbutton').css("display", "block")
			//$("#printbutton").show();
			//$("#scheduleprint").show();

			document.getElementById("unAuthorizedBreakLocationList").style.display = '';

			var selectedDate = document.getElementById("selecteddate").value;

			$('#unAuthorizedBreakLocationList')
					.dataTable(
							{
								"aLengthMenu" : [ [ 5, 15, 20, -1 ],
										[ 5, 15, 20, "All" ] // change
								],
								// set the initial value
								"iDisplayLength" : 20,
								"sAjaxSource" : "unAuthorizedBreakLocationList.action?depotNo="
										+ depotNo
										+ "&selectedDate="
										+ selectedDate,
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
									'aTargets' : [ 0, 1, 2, 3, 4, 5, 6 ]
								} ],
								aaSorting : [],
								"bLengthChange" : false,
								"sDom" : '<"top">rt<"bottom"flp><"clear">'
							})
					.wrap(
							"<div style='position:auto;height:300px;width:100%'/>");
			jQuery(
					'#unAuthorizedBreakLocationList_wrapper .dataTables_filter input')
					.addClass("form-control input-medium input-inline"); // modify table
			// search input
			jQuery(
					'#unAuthorizedBreakLocationList_wrapper .dataTables_length select')
					.addClass("form-control input-xsmall input-inline");
			$("#tripstatus").show();

		}

	}
</Script>

<title>Insert title here</title>
</head>
<body>
	<div class="page-content-wrapper">
		<div class="page-content">

			<div class="tab-content">

				<div class="row">
					<div class="col-md-12">
						<!-- BEGIN PAGE TITLE & BREADCRUMB-->
						<h3 class="page-title">
							UNAUTHORIZED BREAK LOCATION REPORT <small></small>
						</h3>

						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>View Unauthorized Break Location
								Report
							</div>

						</div>

						<div class="portlet-body form">

							<div class="col-md-12" align="left" style="font-size: 1.1em">

								<%-- <span class="help-block" style="color: red; list-style: none"><s:actionerror /></span> --%>
								<span class="help-block" style="color: red; list-style: none"><s:actionmessage /></span>
							</div>
							<!-- BEGIN FORM-->
							<form action="" method="post" class="form-horizontal">

								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Division<font
											color="red">*</font></label>
										<div class="col-md-3">
											<s:select list="divisionlist" id="divisionlist"
												name="divisionname" cssClass="select2_category form-control"
												headerKey="0" headerValue="Division"
												onchange="getDepot(this.value)"></s:select>
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-3 control-label">Depot<font
										color="red">*</font></label>
									<div class="col-md-3">
										<select id="depotlist" class="select2_category form-control">
											<option value="0">Depot</option>
										</select>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-md-3">Date<font
										color="red">*</font></label>
									<div class="col-md-3">
										<div class="input-group input-medium date date-picker"
											data-date-format="dd-mm-yyyy" data-date-viewmode="years">
											<input class="form-control" type="text" size="10" name=""
												id="selecteddate" value='<s:property value=""/>' readonly>
											<span class="input-group-btn">
												<button class="btn default date-set form-control"
													type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
										</div>
									</div>


								</div>
								<div class="form-group">


									<div class="col-md-1" id=""
										style="left: 350px; position: relative;">
										<button type="button" class="btn default"
											onclick="getUnauthorizedBreakLocationReportOnSubmit()">Submit</button>
									</div>
									<div class="col-md-1" id="printbutton"
										style="left: 360px; position: relative;display:none;">
										<input type='button' class="btn default" id='button1'
											onclick='printDiv()' value='Print' />
									</div>
								</div>


								<div id="header" class="portlet-body"
									style="display: none; visibility: hidden;">
									<h4 style="margin-left: 350px;">Unauthorized Break
										Location Report</h4>
									<br />

								</div>
								<br />
								<div class="portlet-body" id="scheduleprint"
									style='height: 310px; overflow-y: scroll; display: none;'>
									<div id="tripstatus">
										<table class="table table-striped table-bordered table-hover"
											id="unAuthorizedBreakLocationList" style="display: none">
											<thead>
												<tr>
													<th>SR. No.</th>
													<th>Vehicle No.</th>
													<th>Count</th>
													<th>Driver Name</th>
													<th>Conductor Name</th>
													<th>Schedule Name</th>
													<th>Duty Type</th>

												</tr>
											</thead>
										</table>
									</div>
								</div>

							</form>
							<!-- END FORM-->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div style="display: none;" id="mymodal1234" class="modal fade"
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
									<div class="tab-pane active" id="tab_0">
										<div class="portlet box grey-cascade">
											<div class="portlet-title">
												<div class="caption">
													<i class="fa fa-gift"></i>Unauthorized Break Location
													Details
												</div>

											</div>
											<div class="portlet-body" id="breaklocation">
												<table
													class="table table-striped table-bordered table-hover"
													id="breaklocations">
													<thead>
														<tr>
															<th>SR. No.</th>
															<th>Device Id</th>
															<th>Start Time</th>
															<th>End Time</th>
															<th>Location</th>
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
	</div>





</body>
</html>