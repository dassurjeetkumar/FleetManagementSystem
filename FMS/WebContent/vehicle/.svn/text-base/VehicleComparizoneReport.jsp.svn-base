<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>

<script src="https://code.jquery.com/jquery-1.11.3.min.js" type="text/javascript"></script>
	
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />


 <script src="assets/global/plugins/jquery-1.11.0.min.js"
 	type="text/javascript"></script>
	
<%-- <script src="assets/vts/js/scheduledeviation.js" --%>
<%-- 	type="text/javascript"></script> --%>
	<script src="assets/vts/js/vts.js" type="text/javascript"></script>


 
<Script>

// $(document).ready(function() {
// 	 $(function() {
// 		 getScheduledTripStatusDataOnSubmit();
// });
// });
 
 /* function jsFunctionForComparison(date){
	 
	 alert("hi"+date);
	 $('#schedulevehicleDetails').attr("style", "display:''");
	 table = $('#vehDetails');
		var oTable = table.dataTable({
			"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
			// per
			// page
			// values
			// here
			],
			// set the initial value
			"iDisplayLength" : 5,
			"sAjaxSource" : "AjaxScheduleVehicleDetails.action?startdate="+date,
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
		jQuery('#vehDetails_wrapper .dataTables_filter input').addClass(
				"form-control input-small input-inline"); // modify
		// table
		jQuery('#vehDetails_wrapper .dataTables_length select').addClass(
				"form-control input-xsmall input-inline"); // modify
	 
 } */
 
 
 

	function validateTripStatusReportFields(date)
	{
		
		 if(date==0)
		 {
			alert("Please Select Date");
			return false;
		 }
// 		 if(orderno==0)
// 		 {
// 			alert("Please Select OrderNO");
// 			return false;
// 		 }
	     

	 return true;
	}
	
	
	
	
	


	function printDiv() {     
		 
		 document.getElementById("scheduleVehicleReportId").style.visibility='visible';  
	    document.getElementById("header").style.display='block';
	    document.getElementById("header").style.visibility='visible';     
	    var divElements = document.getElementById("header").innerHTML;
	    divElements+= "<table>"+document.getElementById("scheduleVehicleReportId").innerHTML+"</table>";
	    
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
	    document.getElementById("header").style.display = 'none';
		 document.getElementById("header").style.visibility = 'hidden';

	    
	            
	}


	function getVehicleReport() {
		var date = $("#seldate").val();
		 if(date==0)
		 {
			alert("Please Select Date");
			return false;
		 }
			$('#vehicleChangeReportId').attr("style", "display:''");
			var givendate = $("#seldate").val();
			     table = $('#scheduleVehicleReportId');
			     var oTable = table.dataTable({
			    	 "aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
			 		// per
			 		// page
			 		// values
			 		// here
			 		],
			 		// set the initial value
			 		"iDisplayLength" : 5,
			 		"sAjaxSource" : "AjaxScheduleVehicleComparison?startdate="+givendate,
			 		"sPaginationType" : "bootstrap",
			 		"bDestroy" : true,
					"bSort" : true,
					"bFilter" : true,
					"bSelect" : false,
					"bPaginate" : false,
					"bInfo": false,
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

			 	jQuery('#scheduleVehicleReportId_wrapper .dataTables_filter input').addClass(
			 			"form-control input-xsmall input-inline"); // modify table
			 	// search input
			 	jQuery('#scheduleVehicleReportId_wrapper .dataTables_length select').addClass(
			 			"form-control input-xsmall input-inline");
			}
	
	



 </Script>

<title>Insert title here</title>
</head>
<div class="page-content-wrapper">
		<div class="page-content">
		
		
		
			<div class="row">
				<div class="col-md-12">
					<h3 class="page-title">
						SCHEDULE VEHICLE CHANGE REPORT  <small></small>
					</h3>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>View Schedule Vehicle Change Report
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
											id="seldate"  readonly>
										<span class="input-group-btn">
											<button class="btn default date-set form-control"
												type="button">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
											<script>
								var curr_date=new Date().toJSON().slice(0,10);
                                curr_date=curr_date.split("-"); 
                                 curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0]; 
                                 var dtval=document.getElementById('seldate').value;	 
                                
                                 if(dtval==''){
                              $('#seldate').val(curr_date); 
                            
                                } 
								</script>
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
    inputHTML += "<th  align='left'colspan='9'>SCHEDULE VEHICLE COMPARISON REPORT</th>";
    inputHTML += "</tr>";
//     inputHTML += "<tr>";
//     inputHTML += "<th colspan='2' align='left'>Date Range:</th>";
//     inputHTML += "<th colspan='7' align='left'>" + $("#startdate").val() + " to " + $("#endate").val() + "</th>";
//     inputHTML += "</tr>";
    inputHTML += "</table>";
    var htmltable = document.getElementById("scheduleVehicleReportId");
    var html = inputHTML + htmltable.outerHTML;
    var downloadLink = document.createElement("a");
    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
    downloadLink.download = "SCHEDULE VEHICLE COMPARISON REPORT";
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
}</script>
		
		
							<div class="form-group">
								<div class="col-md-3">
									<!-- <div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9"> -->
									<button type="button" class="btn default"
										onclick="getVehicleReport()">Submit</button>
									<!-- </div>
									</div> -->
								</div>
								<div class="col-md-1"id="printbutton" >
											<input type='button' class="btn default" id='button1' onclick='printDiv()' value='Print' />													
										</div>
										<div class="col-md-1" id="">
								 <button type="button" class="btn default" id="btnExport" filename= "report" onclick="tabletoExcel();"> EXPORT </button>
                                </div>
							</div>
						</div>
					</div>
					<br />
					<br /> <br />
					<br />
					<div class="portlet-body" id="vehicleChangeReportId"
						style="display: none">
						<div style="text-align:center">
						<table class="table table-striped table-bordered table-hover"
							id="scheduleVehicleReportId">

							<thead>
								<tr>
									<th>Sr.No.</th>
									<th>Depot Name</th>
									<th>Total Sch.</th>
									<th>Modified Sch.</th>
								</tr>
							</thead>

						</table>
</div>
					</div>
					 <div id="header" class="portlet-body">
										
									<br />
							
					<div style="display: none;" id="mymodal11" class="modal fade"
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
													<div class="portlet-body" id="schedulevehicleDetails"
														style="display: none">
														<div style="text-align:center">
														<table
															class="table table-striped table-bordered table-hover"
															id="comparisonData">
															<thead>
																<tr>
																	<th>Sr. NO</th>
												<th>Depot Name</th>
												<th>Schedule No</th>
												<th>Shift Type</th>
												<th>Trip No</th>
												<th>Vehicle 1</th>
												<th>Vehicle 2</th>
												<th>Updated Date</th>
												<th>Reason</th>
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


				</div>
			</div>
		</div>
	</div>
	
										

	

</body>
</html>