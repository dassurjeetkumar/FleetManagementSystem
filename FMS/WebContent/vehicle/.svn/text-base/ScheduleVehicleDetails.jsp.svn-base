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
<%-- 	<script src="assets/vts/js/vts.js" type="text/javascript"></script> --%>

 
<Script>

// $(document).ready(function() {
// 	 $(function() {
// 		 getScheduledTripStatusDataOnSubmit();
// });
// });
 
//  function jsFunction(date){
	 
// 	 alert("hi"+date);
	 
// 	//document.getElementById("schEarlydata1").style.display = '';
// 		$("#schdwiseLatedata1").show();
// 		$("#driverEarlydata1").hide();
// 		$("#schEarlydata1").hide();
// 		//$("#schdwiseLatedata1").hide();
// 		$("#driverwiseLatedata1").hide()
// 		$("#schddist80data1").hide();
// 		var oTable = $('#ScheduledetailsVehicle').dataTable({
// 			"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
// 			// per
// 			// page
// 			// values
// 			// here
// 			],
// 			// set the initial value
// 			"iDisplayLength" : 5,
// 			"sAjaxSource" : "AjaxScheduleVehicleDetails.action?depot="
// 							+ depot 
// 							+ "&startdate=" + startdate,
// 			"sPaginationType" : "bootstrap",
// 			"bDestroy" : true,

// 			"oLanguage" : {
// 				"sLengthMenu" : "_MENU_ records",
// 				"oPaginate" : {
// 					"sPrevious" : "Prev",
// 					"sNext" : "Next"
// 				}
// 			},
// 			"aoColumnDefs" : [ {
// 				'bSortable' : false,
// 				'aTargets' : [ 0 ]
// 			} ]
// 		});
// 		jQuery('#schdwiseLate1_wrapper .dataTables_filter input').addClass(
// 				"form-control input-small input-inline"); // modify
// 		// table
// 		jQuery('#schdwiseLate1_wrapper .dataTables_length select').addClass(
// 				"form-control input-xsmall input-inline"); // modify
// 	}
 
 
 function printDiv1() {     
	    
	    //  document.getElementById("print").style.visibility='hidden';     
	    var divElements = document.getElementById("header").innerHTML;
	   
	    divElements += document.getElementById("ticketconsuptionid").innerHTML;
	    
	    var noOfTableExist = 0;
	    try{
			$("#ticketconsuptionid table").each(function(){
				noOfTableExist++;
			});
			
			console.log("Total no of tables : " + noOfTableExist);
			/* If two table exist  then remove the last table on print click*/
			if(noOfTableExist >= 2){
				divElements = divElements.substring(0, divElements.indexOf("</table>") + 8) + "</div></div>";
			}
		}catch(err){
		    console.log("ExceptionCaught : " + err);
		}

	    var mywindow = window.open("<%=request.getContextPath()%>/Print.jsp");
	   
	    mywindow.onload = function() {
	    mywindow.document.body.innerHTML=divElements;
	    mywindow.document.body.innerHTML=divElements;
	    mywindow.print();
	    mywindow.close();
	    }
	            
	}
 
 
 function printDiv() {     
	 
	 document.getElementById("mapshow").style.visibility='hidden'; 
	$(".mapClass").hide();
	   document.getElementById("ticketconsuptionid").style.visibility='visible';  
     document.getElementById("header").style.display='block';
     document.getElementById("header").style.visibility='visible';     
     var divElements = document.getElementById("header").innerHTML;
     divElements+= document.getElementById("ticketconsuptionid").innerHTML;
     
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
		document.getElementById("mapshow").style.visibility=''; 
		$(".mapClass").show();
     
             
 }
 

	function validateTripStatusReportFields(date,orderno)
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
	
	function resetDate(){
		//$("#selecteddate").val()='';
		document.getElementById("selecteddate").value='';
	}
	
	
	function getScheduledTripStatusDataOnSubmit()
	{
		
// 		var scheduleNo=$("#schedulelist").val();
// 		var orderno=$("#orderno").val();
		var date=$("#startdate").val();
// 		var selecteddate=$("#selecteddate").val();
		validateflag=validateTripStatusReportFields(date);
// 		if(validateflag==true)
// 		{

		
	
// 		$("#tripstatus").show();
 $('#pageLoader').show();
		 $.ajax({
	           type: "get",
	           url: '<%=request.getContextPath()%>/AjaxScheduleVehicleDetails.action?startdate='+date,
	           success: function(result) {
	        	   $('#pageLoader').hide();
// 	        	   alert("hi"+result);
	                document.getElementById('ticketconsuptionid').innerHTML=result;
	                fixHeader();
	                
	            }
	       }); 
		 
		
		 
		 
		 
// 		 document.getElementById('selectdate').innerHTML=selectedDate;
// 		 document.getElementById('scheduleno').innerHTML=scheduleNo;
		 
// 		}
		 
	}

	



 </Script>

<title>Insert title here</title>
</head>
<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">
	<div class="page-content-wrapper">
		<div class="page-content">
	
			<div class="tab-content">
 
              <div id="pageLoader" class="modal fade in" tabindex="-1" role="dialog" aria-labelledby="myModalLabel3" aria-hidden="false">
								<div class="modal-dialog">
									<div class="modal-content">
										
										<div class="modal-body">
											<p>
												<img src="assets/images/loader1.gif" align="top" style="margin-left:100px;"/> 
											</p>
											<p style='text-align:center'>Please Wait........</p>
										</div>
										
									</div>
								</div>
							</div>
               

				<div class="row">
					<div class="col-md-12">
						<!-- BEGIN PAGE TITLE & BREADCRUMB-->
						<h3 class="page-title">
							VEHICLE CHANGE DETAILS REPORT<small></small>
						</h3>

						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>View Vehicle Change Details Report
							</div>
							<div class="actions">

						
						<input type='button' class='btn blue' id='button1' onclick='printDiv1()' value='Print' />													
										
						<button type="button" class='btn blue' id="btnExport" filename= "report" onclick="tabletoExcel();"> EXPORT </button>
                               

						</div>
							<!-- <div class="tools">
								<a href="javascript:;" class="collapse"> </a> 
								 <a href="javascript:;" class="reload"> </a> 
							</div> -->
						</div>
						
						

							<div class="portlet-body" id="schdwiseLatedata1" style="display: none">
			
				<div style="text-align:center">
					<table class="table table-striped table-bordered table-hover"
						id="ScheduledetailsVehicle">
						<thead>
							<tr>
							<th>Sr No</th>
								<th>Depot Name</th>
								<th>Schedule No.</th>
								<th>Vehicle No(As per Schedule Mapping)</th>
								<th>Vehicle No(As per Daily Schedule Mapping)</th>
								<th>Updated Date</th>
								
							</tr>
						</thead>
					</table>
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
//   	             inputHTML += "<tr>";
//   	             inputHTML += "<th colspan='2' align='left'>Date Range:</th>";
//   	             inputHTML += "<th colspan='7' align='left'>" + $("#startdate").val() + " to " + $("#endate").val() + "</th>";
//   	             inputHTML += "</tr>";
  	             inputHTML += "</table>";
  	             var htmltable = document.getElementById("ticketconsuptionid");
  	             var html = inputHTML + htmltable.outerHTML;
  	             var downloadLink = document.createElement("a");
  	             downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
  	             downloadLink.download = "SCHEDULE VEHICLE COMPARISON REPORT.xls";
  	             document.body.appendChild(downloadLink);
  	             downloadLink.click();
  	             document.body.removeChild(downloadLink);
  	         }</script>
						
						
  	              	 
<!--   	                              <div class="form-group"> -->
<!--   	                              <label class="col-md-3 control-label"></label> -->
<!-- 									<div class="col-md-1" id=""> -->
<!-- 									<button type="button" class='btn green' onclick='getScheduledTripStatusDataOnSubmit()' style="position: static;">Submit</button> -->
<!-- 									</div> -->
<!-- 									
								
								
					
							<!-- END FORM-->
<!-- 						</div> -->
							
					</div>
					
					
					
					
					
<!-- 				 <div id="ticketconsuptionid"></div> -->
				</div>
				

				<div id="ticketconsuptionid"></div>

	
			</div>
		</div>
</div>
</div>

	
										

	

</body>
</html>