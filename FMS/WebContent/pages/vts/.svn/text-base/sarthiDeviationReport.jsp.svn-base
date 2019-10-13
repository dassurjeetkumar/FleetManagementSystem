
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
//Code For Alert Drill Down..

function printDiv() {     
	 
	// document.getElementById("mapshow").style.visibility='hidden'; 
	//$(".mapClass").hide();
	   document.getElementById("tripextrastatus").style.visibility='visible';  
     document.getElementById("header").style.display='block';
     document.getElementById("header").style.visibility='visible';     
     var divElements = document.getElementById("header").innerHTML;
     divElements+= document.getElementById("tripextrastatus").innerHTML;
     
    //divElements += document.getElementById("schdeviation").innerHTML;
  
     var mywindow = window.open("<%=request.getContextPath()%>/Print.jsp");
    
     mywindow.onload = function() {
         mywindow.document.body.innerHTML=divElements;
         mywindow.document.body.innerHTML=divElements;
     //	mywindow.document.body.innerHTML = divElement;
       // document.getElementById("print").style.visibility='';
         mywindow.print();
         mywindow.close();
     }
     document.getElementById("header").style.display = 'none';
		document.getElementById("header").style.visibility = 'hidden';
		//document.getElementById("mapshow").style.visibility=''; 
	//	$(".mapClass").show();
     
             
 }

function getSarthiVehicleDeviationData()
{
	//alert("Hii");
	var givendate = $("#seldate").val();
	if(givendate==null||givendate=='')
		{
		bootbox.alert("Please Select Date");
		}else{
	var alertId =16;
	var depotID = $("#divisionlistid1").val();
	var table;
	
		var table;
		/*  $("#accidentAlertDivId").hide();
		 $("#vtsAlertReportDivId").hide(); */
		 $("#sarthiDeviationDataId").show();
	     table = $('#sarthiDeviationData');
	     var oTable = table.dataTable({
				"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
				// per
				// page
				// values
				// here
				],
				// set the initial value
				"iDisplayLength" : 5,
				
				"sAjaxSource" : "getSarthiDeviationData.action?alertID=" + 23
						+ "&depotID=" + depotID + "&givendate=" + givendate,
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
					'aTargets' : [ 0 ]
				} ],
				aaSorting:[],
				"bLengthChange" : false,
				"sDom" : '<"top">rt<"bottom"flp><"clear">'
			})
	.wrap(
			"<div style='position:auto;height:300px;width:100%'/>");
			jQuery('#sarthiDeviationData_wrapper .dataTables_filter input').addClass(
					"form-control input-small input-inline"); // modify
			// table
			jQuery('#sarthiDeviationData_wrapper .dataTables_length select').addClass(
					"form-control input-xsmall input-inline"); // modify
		}
	}


function getSarthiDeviatedInfo(deviceId){
	
	//alert("Hiivd"+deviceId);
	
	
	var givendate = $("#seldate").val();
	var alertId =16;
	var depotID = $("#divisionlistid1").val();
	var table;
	
		var table;
		 $("#accidentAlertDivId").hide();
		 $("#vtsAlertReportDivId").hide();
		 $("#sarthiDeviationInfoId").show();
	     table = $('#sarthiDeviationInfo');
	     var oTable = table.dataTable({
				"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
				// per
				// page
				// values
				// here
				],
				// set the initial value
				"iDisplayLength" : 5,
				"sAjaxSource" : "getSarthiDeviationInfo.action?alertID=" + 23
						+ "&depotID=" + depotID + "&givendate=" + givendate+"&deviceId="+deviceId,
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
					'aTargets' : [ 0 ]
				} ],
				aaSorting:[],
				"bLengthChange" : false,
				"sDom" : '<"top">rt<"bottom"flp><"clear">'
			});
			jQuery('#sarthiDeviationInfo_wrapper .dataTables_filter input').addClass(
					"form-control input-small input-inline"); // modify
			// table
			jQuery('#sarthiDeviationInfo_wrapper .dataTables_length select').addClass(
					"form-control input-xsmall input-inline"); // modify
}
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
	 					LC/SARTHI DEVIATION REPORT <small></small>
						</h3>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="portlet box grey-cascade">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-globe"></i>View LC/Sarthi Deviation Report
								</div>
							</div>
							
						</div>
						 <div class="portlet-body form">
						 	<form action="" method="post" class="form-horizontal">
									<input type="hidden" name="requestType" id="requestType"
									value="text" /> 
									<div class="portlet-body form">
										<div class="form-group">
											<div class="col-md-3">
												
											</div>
										</div>
										
										
										<div class="form-group">
									<label class="control-label col-md-3">Date<font
										color="red">*</font></label>
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
											<%-- <div class="col-md-3">
												<s:select list="alertlist1" id="alertlist_id"
													cssClass="select2_category form-control" headerKey="0"
														headerValue="--Alert Name--" ></s:select>

											</div> --%>
											</div>
											<%-- <div class="form-group">
											<div class="col-md-3">
												<s:select list="divisionlist1" id="divisionlistid1"
													cssClass="select2_category form-control" headerKey="00"
													headerValue="--Depot Name--" onchange="getAlertReport(this.value)">
												</s:select>
											</div>
										</div> --%>
										<div class="form-group">
											<label class="control-label col-md-3"><font
										color="red"></font></label>
											<div class="col-md-3">
										<!-- <div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9"> -->
											<button type="button" class="btn default" onclick="getSarthiVehicleDeviationData()">Submit</button>
										<!-- </div>
									</div> -->
									</div>
									<div class="col-md-1"id="printbutton">
											<input type='button' class="btn default" id='button1' onclick='printDiv()' value='Print' />													
										</div>
										</div>
									</div>
							
			 				<br/><br/>
							<br/><br/>
							
							 <div id="header" class="portlet-body" style="display: none; visibility: hidden;">
										<h4 style="margin-left:350px;">Sarthi Deviation Report</h4>
									<br />
									<div id="headerdetails" style="margin-left:0px;">
                            	        <table ID="mytable" style="width:60%;border:none;">
														<%-- <tr>
									 						<td ><b><label><font size="2">Schedule No :</font></label></b>
															<!-- <td style="text-align: left;"> -->
			 												<b><font size="2"><span  id="scheduleno" style="text-align: left;margin-left:10px;"></span></font></b></td>
								
				
						 							
															<td ><b><font size="2"><label> Schedule Type: </label></font></b>
															<!-- <td style="text-align: left;"> -->
																		 <b><font size="2"><span id="scheduletype" style="text-align:left;margin-left:10px;"></span></font></b></td>
														</tr>
														<tr>
															<td><font size="2"><label>Date:</label></font>
															<!-- <td nowrap style="text-align: left;"> --><font size="2"><b><span id="selectdate" style="text-align:left;margin-left:50px;"></span></font></b></td>
															
															<td style="text-align: left;"><b><font size="2"><label>From:</label></font><font size="2"><b><span id="fromDate" style="text-align:left;margin-left:50px;"></span></font></b></td>
															<td><font size="2"><label><s:property
																		value="" /> </label></font></td>
																		<td nowrap style="text-align: left;"><b><font size="2"><label>To:</label></font><font size="2"><b><span id="toDate" style="text-align:left;margin-left:50px;"></span></font></b></td>
															<td nowrap></td>
														</tr>
														<tr>
															
															<td nowrap><b><font size="2"><label>Base Route: </label></font><font size="2"><b><span id="baseRoute" style="text-align:left;margin-left:50px;"></span></font></b></td>
															<td nowrap></td>
														</tr>
														 --%>
														
														
														
														
													</table>
													 <br/><br/> 
                                    
                                </div>
                                </div>
							
									<div class="portlet-body" id="sarthiDeviationDataId" style='height:310px; overflow-y:scroll; display:block;'>
									<div id="tripextrastatus">
										<table class="table table-striped table-bordered table-hover"
											id="sarthiDeviationData">
											<thead>
												<tr>
													<th>Sr. No</th>
													<th>Vehicle No</th>
													<th>Total Count</th>
													
													
												</tr>
											</thead>
										</table>
                                    </div>
									</div>
									 </form>
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
									</div><div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i> LC /Sarthi Deviation Details
							</div>
							<!-- <div class="tools">
								<a href="javascript:;" class="collapse"> </a> 
								 <a href="javascript:;" class="reload"> </a> 
							</div> -->
						</div>
											<div class="portlet-body" id="sarthiDeviationInfoId"
										style="display: none">
										<table class="table table-striped table-bordered table-hover"
											id="sarthiDeviationInfo">
											<thead>
												<tr>
													<th>Sr. No</th>
													<th>Vehicle Number:</th>
													<th>Device ID:</th>
													<th>IST DATE</th>
													
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
