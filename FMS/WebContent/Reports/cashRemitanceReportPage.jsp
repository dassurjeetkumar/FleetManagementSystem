<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<style>
 td,th { 
 	  white-space: nowrap;
 	
 }
</style>
<script type="text/javascript" src="//www.google.com/jsapi"></script>


<script src="assets/vts/js/vehiclealert.js"></script>
<script src="assets/vts/js/vehiclealert.js"></script>
<script src="assets/global/plugins/jquery-1.11.0.min.js"
	type="text/javascript"></script>


<Script>
 $(document).ready(function(){
	$("#printbutton").hide();
 });
 
 function validateTripStatusReportFields(selDate,selDate1) {
		
		if (selDate == 0) {
			bootbox.alert("Please Select From  Date");
			return false;
		}
		if (selDate1 == 0) {
			bootbox.alert("Please Select To Date");
			return false;
		}
		return true;
	}

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
	function getCashRemittanceReportOnSubmit() {
		var selecteddate1 = $("#selecteddate1").val();
		var selecteddate2 = $("#selecteddate2").val();
		document.getElementById("scheduleprint").style.display = '';
		validateflag = validateTripStatusReportFields(selecteddate1,selecteddate2);
		if (validateflag == true) {
			//jQuery('#scheduleprint').css("display", "block");
			//jQuery('#printbutton').css("display", "block");
			$('#cashRemitance').dataTable({
			"aLengthMenu" : [ [ 40, -1 ], [40,"All" ] // change
			// per
			// page
			// values
			// here
			],
			// set the initial value
			"iDisplayLength" : 5,
			"sAjaxSource" : "cashRemitanceReportAjax.action?selectedDate1="
				+ selecteddate1+ "&selectedDate2="
				+ selecteddate2,
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
			 },
			{ "sClass": "totpax", "aTargets": [ 2 ] },
			{ "sClass": "totamt", "aTargets": [ 3 ] },
			],
			"fnInitComplete": function(oSettings, json) {
				summury();
				}
			
		});
			
			jQuery(
					'#cashRemitance_wrapper .dataTables_filter input')
					.addClass("form-control input-medium input-inline"); // modify table
			// search input
			jQuery(
					'#cashRemitance_wrapper .dataTables_length select')
					.addClass("form-control input-xsmall input-inline");
			

		}

	}
	function summury(){
		var schdDist = [];
		var gpsDist=[];
		var summarySchdDist=0.00;
		var summaryGpsDist=0.00;
		$(".totpax").each(function(index, value) {
			schdDist.push($(this).text());
		});
		$(".totamt").each(function(index, value) {
			console.log($(this).text());
			gpsDist.push($(this).text());
		});
		
		for (var i = 0; i < schdDist.length; i++) {
			if (!isNaN((schdDist[i]))) {
				summarySchdDist += parseFloat(schdDist[i]);
			}
		}
		for (var k = 0; k < gpsDist.length; k++) {
			if (!isNaN((gpsDist[k]))) {
				summaryGpsDist += parseFloat(gpsDist[k]);
			}
		}
		//$("#summaryTotalPassenger").html("<B>Total Passenger:</B> "+summarySchdDist.toFixed(2));
		//$("#summaryTotalAmount").html("<B>Total Amount:</B> "+summaryGpsDist.toFixed(2));
		}

</Script>

<title>Cash Remittance</title>
</head>
<body>
	<div class="page-content-wrapper">
		<div class="page-content">

			<div class="tab-content">

				<div class="row">
					<div class="col-md-12">
						<!-- BEGIN PAGE TITLE & BREADCRUMB-->
						<h3 class="page-title">
							CASH REMITTANCE REPORT<small></small>
						</h3>

						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Cash Remittance Report
								
							</div>

						</div>

						<div class="portlet-body form">

							<div class="col-md-12" align="left" style="font-size: 1.1em">

								<%-- <span class="help-block" style="color: red; list-style: none"><s:actionerror /></span> --%>
								<span class="help-block" style="color: red; list-style: none"><s:actionmessage /></span>
							</div>
							<!-- BEGIN FORM-->
							<form action="" method="post" class="form-horizontal">
								<div class="form-group">
									<label class="control-label col-md-3">From Date:<font
										color="red">*</font></label>
									<div class="col-md-3">
										<div class="input-group input-medium date date-picker"
											data-date-format="dd-mm-yyyy" data-date-viewmode="years">
											<input class="form-control" type="text" size="10" name=""
												id="selecteddate1" value='<s:property value=""/>' readonly>
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
									<label class="control-label col-md-3">To Date:<font
										color="red">*</font></label>
									<div class="col-md-3">
										<div class="input-group input-medium date date-picker"
											data-date-format="dd-mm-yyyy" data-date-viewmode="years">
											<input class="form-control" type="text" size="10" name=""
												id="selecteddate2" value='<s:property value=""/>' readonly>
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
											onclick="getCashRemittanceReportOnSubmit()">Submit</button>
									</div>
									<!-- <div class="col-md-1" id="printbutton"
										style="left: 360px; position: relative;display:none;">
										<input type='button' class="btn default" id='button1'
											onclick='printDiv()' value='Print' />
									</div> -->
								</div>


								<div id="header" class="portlet-body"
									style="display: none; visibility: hidden;">
									<h4 style="margin-left: 350px;">Cash Remittance Report</h4>
									<br />

								</div>
								<br />
								
								<div class="portlet-body" id="scheduleprint"
									style='display: none;'>
										<table class="table table-striped table-bordered table-hover"
											id="cashRemitance" >
											<thead>
												<tr>
													<th>Sr.No.</th>
													<th>Depot Name</th>
													<th>Total Passenger</th>
													<th>Total Revenue</th>
												</tr>
											</thead>
										</table>
								</div>
								<table class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
									<td id=''></td>
									<td id="summaryTotalPassenger"></td>
									<td id="summaryTotalAmmount"></td>
									</tr>
								</thead>
							</table>

							</form>
							<!-- END FORM-->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>