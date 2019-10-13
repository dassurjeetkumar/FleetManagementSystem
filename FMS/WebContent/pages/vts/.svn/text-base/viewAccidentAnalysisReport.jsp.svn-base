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
	
	function validateTripStatusReportFields(depotNo, divisonNo, selDate,selDate1,acctype) {
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
		if (selDate1 == 0) {
			bootbox.alert("Please Select Date");
			return false;
		}
		if (acctype == 0) {
			bootbox.alert("Please Select Date");
			return false;
		}

		return true;
	}

	function getUnauthorizedBreakLocationReportOnSubmit() {
		
		var depotNo = $("#depotlist").val();
		var divisonNo = $("#divisionlist").val();
		var selecteddate1 = $("#selecteddate1").val();
		var selecteddate2 = $("#selecteddate2").val();
		var acctype = $("#acctypelist").val();
		validateflag = validateTripStatusReportFields(depotNo, divisonNo,
				selecteddate1,selecteddate2,acctype);
		if (validateflag == true) {
			jQuery('#scheduleprint').css("display", "block");
			jQuery('#printbutton').css("display", "block");
			//$("#printbutton").show();
			//$("#scheduleprint").show();

			document.getElementById("accidentAnalysisList").style.display = '';

			//var selectedDate = document.getElementById("selecteddate").value;
			$('#accidentAnalysisList')
					.dataTable(
							{
								"aLengthMenu" : [ [ 5, 15, 20, -1 ],
										[ 5, 15, 20, "All" ] // change
								],
								// set the initial value
// 								"iDisplayLength" : 20,
								 "bFilter" : false,
									"bSelect" : false,
					                "bPaginate":false,
								"sAjaxSource" : "accidentAnalysisReportData.action?depotNo="
									+ depotNo
									+ "&selectedDate1="
									+ selecteddate1+ "&selectedDate2="
									+ selecteddate2+ "&acctype="
									+ acctype,
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
										} ],
										aaSorting : [],
										"bLengthChange" : false,
										"sDom" : '<"top">rt<"bottom"flp><"clear">'
									})
							.wrap(
									"<div style='position:auto;height:300px;width:100%'/>");
									

			jQuery(
					'#accidentAnalysisList_wrapper .dataTables_filter input')
					.addClass("form-control input-medium input-inline"); // modify table
			// search input
			jQuery(
					'#accidentAnalysisList_wrapper .dataTables_length select')
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
							ACCIDENT ANALYSIS REPORT<small></small>
						</h3>

						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>View Accident Analysis Report
								
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
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Accident Type<font
											color="red">*</font></label>
										<div class="col-md-3">
											<s:select list="acctypelist" id="acctypelist"
												name="acctypelist" cssClass="select2_category form-control"
												></s:select>
										</div>
									</div>
								</div>

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
							
     	<script>                 	
  	 function tabletoExcel(btnExport){     
  	var htmltable= document.getElementById('btnExport');   
    $( "#header-fixed" ).remove();
    var inputHTML = "<table border='1'>";
    inputHTML += "<tr>";
    inputHTML += "<th  align='left'colspan='9'>Bangalore Metropolitan Transport Corporation</th>";
    inputHTML += "</tr>";
    inputHTML += "<th  align='left'colspan='9'>ACCIDENT ANALYSIS REPORT</th>";
    inputHTML += "</tr>";
    inputHTML += "<tr>";
    inputHTML += "<th colspan='2' align='left'>Date Range:</th>";
    inputHTML += "<th colspan='7' align='left'>" + $("#selecteddate1").val() + " to " + $("#selecteddate2").val() + "</th>";
    inputHTML += "</tr>";
    inputHTML += "</table>";
    var htmltable = document.getElementById("tripstatus");
    var html = inputHTML + htmltable.outerHTML;
    var downloadLink = document.createElement("a");
    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
    downloadLink.download = "ACCIDENT ANALYSIS REPORT.xls";
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
}</script>
								
								<div class="form-group">
									<div class="col-md-1" id=""
										style="left: 350px; position: relative;">
										<button type="button" class="btn default"
											onclick="getUnauthorizedBreakLocationReportOnSubmit()">Submit</button>
									</div>
									 <div class="col-md-1" id="printbutton" style="left: 350px; position: relative;">
											<input type='button' class="btn default" id='button1' onclick='printDiv()' value='Print' />													
										</div>
											
									<div class="col-md-1" id="printbutton"
										style="left: 360px; position: relative;display:none;">
										<input type='button' class="btn default" id='button1'
											onclick='printDiv()' value='Print' />
									</div>
									
									<div class="form-group">
									<div class="col-md-1" id=""
										style="left: 350px; position: relative;">
										<button type="button" class="btn default"
										id="btnExport" filename= "report"
											onclick="tabletoExcel();"> EXPORT </button>
											</div>
									
									
<!-- 									<div class="col-md-1" id=""> -->
<!-- 								 <button type="button" class="btn default" id="btnExport" filename= "report" onclick="tabletoExcel();"> EXPORT </button> -->
<!--                                 </div> -->
										
								</div>


								<div id="header" class="portlet-body"
									style="display: none; visibility: hidden;">
									<h4 style="margin-left: 350px;">Accident Analysis Report</h4>
									<br />

								</div>
								<br />
<!-- 								<div class="portlet-body" id="scheduleprint" -->
<!-- 									style='height: 310px; overflow-y: scroll; display: none;'> -->
									<div class="portlet-body" id="scheduleprint"
									style='height: 310px;  display: none;'>
									<div style="text-align:center">
									<div id="tripstatus">
									
										<table class="table table-striped table-bordered table-hover"
											id="accidentAnalysisList" style="display: none">
											<thead>
												<tr>
													<th>Sr.No.</th>
													<th>Vehicle No.</th>
													<th>Schedule No.</th>
													<th>Driver Name</th>
													<th>Driver Token No.</th>
													<th>Accident Type</th>
												</tr>
											</thead>
										</table>
									</div>
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
</body>
</html>