
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
 <script src="assets/admin/pages/scripts/charts.js"
	type="text/javascript"></script>
	<%--
<script src="assets/admin/pages/scripts/vehiclealert.js"
	type="text/javascript"></script> --%>
<script>
function getHide(){	
	 $("#vtsAlertReportDivId").hide();
	 $("#accidentAlertDivId").hide();
		
}

function printDiv() {     
	 
	 document.getElementById("breakDownReport").style.visibility='visible';  
    document.getElementById("header").style.display='block';
    document.getElementById("header").style.visibility='visible';     
    var divElements = document.getElementById("header").innerHTML;
    divElements+= "<table>"+document.getElementById("breakDownReport").innerHTML+"</table>";
    
   //divElements += document.getElementById("schdeviation").innerHTML;
 
    var mywindow = window.open("<%=request.getContextPath()%>/Print.jsp");
   
    mywindow.onload = function() {
        mywindow.document.body.innerHTML=divElements;
        mywindow.document.body.innerHTML=divElements;
    //	mywindow.document.body.innerHTML = divElement;
        //   document.getElementById("print").style.visibility='';
        mywindow.print();
        mywindow.close();
    }
//     document.getElementById("header").style.display = 'none';
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
					if(val==-2){
						$("#select2-chosen-2").html(" ");
						$("#depotlist").html("<option value='"+-1+"' selected>ALL</option>");
					}else{
						$("#select2-chosen-2").html(" ");
					document.getElementById('depotlist').innerHTML = result;
					}
				}
			});
		}

	}
	
	function validateTripStatusReportFields(depotNo, divisonNo, selDate,acctype) {
		if (divisonNo == 0) {
			bootbox.alert("Please Select Divison");
			return false;
		}
		if (depotNo == 0) {
			bootbox.alert("Please Select Depot");
			return false;
		}

		if (acctype == 0) {
			bootbox.alert("Please Select BreakDown Type");
			return false;
		}
		if (selDate == 0) {
			bootbox.alert("Please Select Date");
			return false;
		}
		return true;
	}
	
	function getBreakDownReportOnSubmit(){
		var depotNo = $("#depotlist").val();
		var divisonNo = $("#divisionlist").val();
		var selecteddate = $("#selecteddate").val();
		var brktype = $("#breakdownlist").val();
		validateflag = validateTripStatusReportFields(depotNo, divisonNo,
				selecteddate,brktype);
		if (validateflag == true) {
			jQuery('#scheduleprint').css("display", "block");
			jQuery('#printbutton').css("display", "block")
			document.getElementById("tripstatus").style.display = '';
			$('#breakDownReport')
					.dataTable(
							{
								"aLengthMenu" : [ [ 5, 15, 20, -1 ],
										[ 5, 15, 20, "All" ] // change
								],
								// set the initial value
								//"iDisplayLength" : 20,
								 "bFilter" : false,
									"bSelect" : false,
					                "bPaginate":false,
								"sAjaxSource" : "breakDownReportData.action?depotNo="
										+ depotNo
										+ "&selectedDate="
										+ selecteddate+ "&breakdowntype="
										+ brktype,
										"sPaginationType" : "bootstrap",
										"bDestroy" : true,
										"oLanguage" : {
											"sLengthMenu" : "_MENU_ records",
// 											"oPaginate" : {
// 												//"sPrevious" : "Prev",
// 												//"sNext" : "Next"
// 											}
										},
										"aoColumnDefs" : [ {
											'bSortable' : false,
											'aTargets' : [ 0 ]
										} ]
									});
			jQuery(
					'#breakDownReport_wrapper .dataTables_filter input')
					.addClass("form-control input-medium input-inline"); // modify table
			// search input
			jQuery(
					'#breakDownReport_wrapper .dataTables_length select')
					.addClass("form-control input-xsmall input-inline");
			//$("#tripstatus").show();

		}
	}
//Code For Alert Drill Down..


</script>
<style>
</style>
</head>
<%-- <script src="assets/vts/js/vehiclealert.js" type="text/javascript"></script> --%>
<body onload="getHide()">
	<div class="page-content-wrapper">
		<div class="page-content">
			<div class="row">
					<div class="col-md-12">
						<h3 class="page-title">
	 					BREAKDOWN REPORT <small></small>
						</h3>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="portlet box grey-cascade">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-globe"></i>View BreakDown Report
								</div>
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
												headerKey="0" headerValue="Select"
												onchange="getDepot(this.value)"></s:select>
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-3 control-label">Depot<font
										color="red">*</font></label>
									<div class="col-md-3">
										<select id="depotlist" class="select2_category form-control"  >
											<option value="0" >Depot</option>
										</select>
									</div>
								</div>
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">BreakDown Type<font
											color="red">*</font></label>
										<div class="col-md-3">
											<s:select list="breakdownlist" id="breakdownlist"
												name="breakdownlist" cssClass="select2_category form-control"
												></s:select>
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-md-3">Date:<font
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
								
								
     	              	<script>                 	
  	 function tabletoExcel(btnExport){     
  	var htmltable= document.getElementById('btnExport');   
    $( "#header-fixed" ).remove();
    var inputHTML = "<table border='1'>";
    inputHTML += "<tr>";
    inputHTML += "<th  align='left'colspan='9'>Bangalore Metropolitan Transport Corporation</th>";
    inputHTML += "</tr>";
    inputHTML += "<th  align='left'colspan='9'>BREAKDOWN REPORT</th>";
    inputHTML += "</tr>";
    inputHTML += "<tr>";
    inputHTML += "<th colspan='2' align='left'>Date Range:</th>";
    inputHTML += "<th colspan='7' align='left'>" + $("#startdate").val() + " to " + $("#endate").val() + "</th>";
    inputHTML += "</tr>";
    inputHTML += "</table>";
    var htmltable = document.getElementById("breakDownReport");
    var html = inputHTML + htmltable.outerHTML;
    var downloadLink = document.createElement("a");
    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
    downloadLink.download = "BREAKDOWN REPORT.xls";
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
}</script>
 	               			 
								<div class="form-group">
								<div>
								<div class="col-md-1" id=""style="left: 350px; position: relative;">
								
							<button type="button" class="btn default" id="btnExport" filename= "report" onclick="tabletoExcel();"> EXPORT </button>
                            </div>
								 <div class="col-md-1" id=""style="left: 350px; position: relative;">
										<button type="button" class="btn default" onclick="getBreakDownReportOnSubmit()">Submit</button>
							        </div> 
							<div>
                                <div class="col-md-1"id="printbutton" style="left: 350px; position: relative;">
								   <input type='button' class="btn default" id='button1' onclick='printDiv()' value='Print' />													
								</div>
                                </div></div></div>
                                
								<div id="header" class="portlet-body"
									style="display: none; visibility: hidden;">
									<h4 style="margin-left: 350px;">BreakDown Report</h4>
									<br />

								</div>
								<br />
<!-- 								<div class="portlet-body" id="scheduleprint" -->
<!-- 									style='height: 310px; overflow-y: scroll; display: none;'> -->
									<div class="portlet-body" id="scheduleprint"
									style='height: 310px; display: none;'>
									<div id="tripstatus">
										<table class="table table-striped table-bordered table-hover"
								id="breakDownReport">

								<thead>
									<tr>
										<th style="width1: 8px;">Sr.No.</th>
				 						<th>Vehicle No.</th>
										<th>Driver Token No.</th>
										<th>Schedule No.</th>
										<th>Shift</th>
										<th>BreakDown Location</th>
										<th>BreakDown Reason</th>
									</tr>
								</thead>

							</table>
									</div>
								</div>

							</form>
							<!-- END FORM-->
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
													<th>Sr. No.</th>
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
									<div class="portlet-body" id="devdetails" style="display: none">
										<table class="table table-striped table-bordered table-hover"
											id="vehicleDetailsDev">
											<thead>
												<tr>
													<th>Sr. No.</th>
													<th>Exit Point Location</th>
													<th>Entry Point Location</th>
													<th>Trip No.</th>
													<th>Deviated Distance Km</th>
													<th>Exit Time</th>
													<th>Entry Time</th>
												</tr>
											</thead>
										</table>
									</div>
									<div class="portlet-body" id="tamperingdetails" style="display: none">
										<table class="table table-striped table-bordered table-hover"
											id="vehicleDetailsTampering">
											<thead>
												<tr>
													<th>Sr. No.</th>
													<th>Vehicle No.</th>
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
													<th>Sr. No.</th>
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
																		
									<div class="portlet-body" id="alert5" style="display: none">
										<table class="table table-striped table-bordered table-hover"
											id="skipStopAlert_data1">
											<thead>
												<tr>
													<th>Sr. No.</th>
													<th>Trip No.</th>
													<th>Time Of Skipping</th>
													<th>Bus Stop Skipped Name</th>
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
