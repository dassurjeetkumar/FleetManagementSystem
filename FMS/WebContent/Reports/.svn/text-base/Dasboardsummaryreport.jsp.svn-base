<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
<script src="https://code.jquery.com/jquery-1.11.3.min.js" type="text/javascript"></script>
	<script src="https://cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/responsive/1.0.7/js/dataTables.responsive.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/fixedheader/3.0.0/js/dataTables.fixedHeader.min.js" type="text/javascript"></script>
<script src="assets/admin/pages/scripts/charts.js" type="text/javascript"></script>
<%-- <script src="assets/vts/js/scheduleroute.js"></script> --%>
<%-- <script src="assets/vts/js/vehiclealert.js"></script> --%>
<%--  <script src="assets/global/plugins/jquery-1.11.0.min.js" --%>
<%-- 	type="text/javascript"></script> --%>
	
<%-- <script src="assets/vts/js/scheduledeviation.js" --%>
<%-- 	type="text/javascript"></script> --%>
<%-- 	<script src="assets/vts/js/vts.js" type="text/javascript"></script> --%>
	
<%-- <script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places,drawing,geometry"></script>
 --%>
<%--  <script> --%>

<%--  </script> --%>
 
 
<Script>


function getDepotList(orgId){

	 var val=document.getElementById('divisionlist').value;
		 if(val!=0) {
       $.ajax({
           type: "post",
           url: '<%=request.getContextPath()%>/getDepotList?val=' + val,
			success : function(result) {
				document.getElementById('depotlist1').innerHTML = result;
				
			}
		});
	}
}

/* function hidedata(){
	$("#scheduleprint").hide();
 	$("#partial").hide();
  	$("#running").hide();
  	$("#printbutton1").hide();
  	$("#printbutton2").hide();
}

window.onload=hidedata; */

var counter=0;
 $(document).ready(function(){
	 
	 	$("#partial").hide();
	  	$("#running").hide();
	$("#printbutton").hide();
	$("#scheduleprint").hide();
$("#schdeviation").hide();
$("#summaryDashboardNRD").hide();
$("#skipstop").hide();
$("#deviated").hide();
$("#schLate").hide();
$("#DriverLate").hide();
$("#schEarlydata").hide();
$("#Driverearlydata").hide();
$("#partialOp").hide();
    
 });
 
 function getRefresh() {
		if ($("#deviated").is(':checked')) {
			$('#deviated').prop('checked', true);
			getDeviatedSchedule();
		} else {
		$("#schdeviation").hide();
		$("#tripstatus").show();
		$("#scheduleprint").show();
		
		}
	}
 function printDiv() {     
	  document.getElementById("tripstatus").style.visibility='visible';  
     document.getElementById("header").style.display='block';
     document.getElementById("header").style.visibility='visible'; 
    
     var divElements="";
     
     var type=$("#type").val();
     if(type ==1 || type ==2 || type ==3 )
    	 {
    	  divElements = document.getElementById("header").innerHTML;
         divElements+= document.getElementById("tripstatus").innerHTML;
    	 }
     if(type== 4){
    	 divElements = document.getElementById("header").innerHTML;
         divElements+= document.getElementById("printData").innerHTML;
    	 
     }
     if(type==5){
    	 divElements = document.getElementById("header").innerHTML;
         divElements+= document.getElementById("printDatarunning").innerHTML;
     }  
     if(type==6){
    	 divElements = document.getElementById("header").innerHTML;
         divElements+= document.getElementById("nrd").innerHTML;
     }
     if(type==7){
    	 divElements = document.getElementById("header").innerHTML;
         divElements+= document.getElementById("skip").innerHTML;
     }
     if(type==8){
    	 divElements = document.getElementById("header").innerHTML;
         divElements+= document.getElementById("deviate").innerHTML;
     }    
     if(type==9){
    	 divElements = document.getElementById("header").innerHTML;
         divElements+= document.getElementById("schwiselate").innerHTML;
     }
     if(type==10){
    	 divElements = document.getElementById("header").innerHTML;
         divElements+= document.getElementById("driverwiselate").innerHTML;
     }
     if(type==11){
    	 divElements = document.getElementById("header").innerHTML;
         divElements+= document.getElementById("schEarly").innerHTML;
     }
     if(type==12){
    	 divElements = document.getElementById("header").innerHTML;
         divElements+= document.getElementById("driverwiseEarly").innerHTML;
     }
     if(type==13){
    	 divElements = document.getElementById("header").innerHTML;
         divElements+= document.getElementById("partialopsch").innerHTML;
     }
     
     
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
 

	function getScheduledTripStatusDataOnSubmit()
	{
		var type=$("#type").val();
		$("#printbutton").show();
	
		document.getElementById("summarydashboardreport").style.display = '';
		document.getElementById("summarydashboardreport").style.display = '';
		
		var selectedenddate=document.getElementById("selectedenddate").value;
		var end = document.getElementById("endate").value;
		var divId=document.getElementById("divisionlist").value;
		var depotId=document.getElementById("depotlist1").value;
// 		alert("div and depot"+divId+"=="+depotId);
// 		schLate DriverLate schEarlydata Driverearlydata partialOp
		if(type == 1 || type== 2 || type == 3){
			
			$("#scheduleprint").show();
	      	$("#partial").hide();
	      	$("#running").hide();
	    	$("#printbutton1").hide();
			$("#printbutton2").hide();
			$("#Nrd").hide();
			$("#skipstop").hide();
			$("#deviated").hide();
			$("#schLate").hide();
			$("#DriverLate").hide();
			$("#schEarlydata").hide();
			$("#Driverearlydata").hide();
			$("#partialOp").hide();
	    	$('#summarydashboardreport').dataTable({
				"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
				// per
				// page
				// values
				// here
				],
				// set the initial value
				//"iDisplayLength" : 5,
				"sAjaxSource" : "getdasboardsummaryreport.action?type="+ type+"&selectedenddate="+selectedenddate+"&endDate="+end+"&divId="+divId+"&depotId="+depotId,
				"sPaginationType" : "bootstrap",
				"bDestroy" : true,
				"bSort" : false,
				"bFilter" : false,
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
					'aTargets' : [ 0 ]},
				{ "sClass": "sch_distance", "aTargets": [ 5 ] },
				{ "sClass": "gps_distance", "aTargets": [ 6 ] },
				 ],
//	 			 "fnInitComplete": function(oSettings, json) {
//	 					summury();
//	 					},
			});
			jQuery(
					'#summarydashboardreport_wrapper .dataTables_filter input')
					.addClass("form-control input-small input-inline"); // modify
			// table
			jQuery(
					'#summarydashboardreport_wrapper .dataTables_length select')
					.addClass("form-control input-xsmall input-inline"); // modify
			
			}
			if(type ==4){
				var divId=document.getElementById("divisionlist").value;
				var depotId=document.getElementById("depotlist1").value;
				$("#printbutton").show();
				$("#partial").show();
                document.getElementById("summarydashboardActual").style.display = ''; 
			 	$("#scheduleprint").hide();
			 	$("#running").hide();
			 	$("#partial").show();
				$("#Nrd").hide();
				$("#skipstop").hide();
				$("#deviated").hide();
				$("#schLate").hide();
				$("#DriverLate").hide();
				$("#schEarlydata").hide();
				$("#Driverearlydata").hide();
				$("#partialOp").hide();
				$('#summarydashboardActual').dataTable({
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					//"iDisplayLength" : 5,
					"sAjaxSource" : "getDasboardsummaryPartial.action?type="+ type+"&selectedenddate="+selectedenddate+"&endDate="+end+"&divId="+divId+"&depotId="+depotId,
					"sPaginationType" : "bootstrap",
					"bDestroy" : true,
					"bSort" : false,
					"bFilter" : false,
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
						'aTargets' : [ 0 ]},
					{ "sClass": "sch_distance", "aTargets": [ 5 ] },
					{ "sClass": "gps_distance", "aTargets": [ 6 ] },
					 ],
//		 			 "fnInitComplete": function(oSettings, json) {
//		 					summury();
//		 					},
				});
				jQuery(
						'#summarydashboardActual_wrapper .dataTables_filter input')
						.addClass("form-control input-small input-inline"); // modify
				// table
				jQuery(
						'#summarydashboardActual_wrapper .dataTables_length select')
						.addClass("form-control input-xsmall input-inline"); // modify
				
				
				}
			 	
			
			if(type == 5){
				var divId=document.getElementById("divisionlist").value;
				var depotId=document.getElementById("depotlist1").value;
              document.getElementById("summarydashboardRunning").style.display = ''; 
				$("#scheduleprint").hide();
				$("#running").show();
				$("#partial").hide();
				$("#printbutton").show();
				$("#Nrd").hide();
				$("#skipstop").hide();
				$("#deviated").hide();
				$("#schLate").hide();
				$("#DriverLate").hide();
				$("#schEarlydata").hide();
				$("#Driverearlydata").hide();
				$("#partialOp").hide();
				$('#summarydashboardRunning').dataTable({
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					//"iDisplayLength" : 5,
					"sAjaxSource" : "getdasboardsummaryRunningReport.action?type="+ type+"&selectedenddate="+selectedenddate+"&endDate="+end+"&divId="+divId+"&depotId="+depotId,
					"sPaginationType" : "bootstrap",
					"bDestroy" : true,
					"bSort" : false,
					"bFilter" : false,
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
						'aTargets' : [ 0 ]},
					{ "sClass": "sch_distance", "aTargets": [ 5 ] },
					{ "sClass": "gps_distance", "aTargets": [ 6 ] },
					 ],
//		 			 "fnInitComplete": function(oSettings, json) {
//		 					summury();
//		 					},
				});
				jQuery(
						'#summarydashboardreport_wrapper .dataTables_filter input')
						.addClass("form-control input-small input-inline"); // modify
				// table
				jQuery(
						'#summarydashboardreport_wrapper .dataTables_length select')
						.addClass("form-control input-xsmall input-inline"); // modify
				
				
				} 
			if(type ==6){
				var divId=document.getElementById("divisionlist").value;
				var depotId=document.getElementById("depotlist1").value;
				$("#printbutton").show();
				$("#Nrd").show();
                document.getElementById("summaryDashboardNRD").style.display = ''; 
			 	$("#scheduleprint").hide();
			 	$("#running").hide();
			 	$("#partial").hide();
			 	$("#skipstop").hide();
			 	$("#deviated").hide();
			 	$("#schLate").hide();
				$("#DriverLate").hide();
				$("#schEarlydata").hide();
				$("#Driverearlydata").hide();
				$("#partialOp").hide();
				$('#summaryDashboardNRD').dataTable({
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					//"iDisplayLength" : 5,
					"sAjaxSource" : "getDashboardSummaryNRD.action?type="+ type+"&selectedenddate="+selectedenddate+"&endDate="+end+"&divId="+divId+"&depotId="+depotId,
					"sPaginationType" : "bootstrap",
					"bDestroy" : true,
					"bSort" : false,
					"bFilter" : false,
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
						'aTargets' : [ 0 ]},
					
					 ],
//		 			 "fnInitComplete": function(oSettings, json) {
//		 					summury();
//		 					},
				});
				jQuery(
						'#summarydashboardActual_wrapper .dataTables_filter input')
						.addClass("form-control input-small input-inline"); // modify
				// table
				jQuery(
						'#summarydashboardActual_wrapper .dataTables_length select')
						.addClass("form-control input-xsmall input-inline"); // modify
				
				
				}
			 
			if(type ==7){
				var divId=document.getElementById("divisionlist").value;
				var depotId=document.getElementById("depotlist1").value;
				$("#printbutton").show();
				$("#Nrd").hide();
                document.getElementById("summarySkipStop").style.display = ''; 
			 	$("#scheduleprint").hide();
			 	$("#running").hide();
			 	$("#partial").hide();
			 	$("#skipstop").show();
			 	$("#deviated").hide();
			 	$("#schLate").hide();
				$("#DriverLate").hide();
				$("#schEarlydata").hide();
				$("#Driverearlydata").hide();
				$("#partialOp").hide();
				$('#summarySkipStop').dataTable({
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					//"iDisplayLength" : 5,
					"sAjaxSource" : "getDashboardSummarySkipStop.action?type="+ type+"&selectedenddate="+selectedenddate+"&endDate="+end+"&divId="+divId+"&depotId="+depotId,
					"sPaginationType" : "bootstrap",
					"bDestroy" : true,
					"bSort" : false,
					"bFilter" : false,
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
						'aTargets' : [ 0 ]},
					{ "sClass": "sch_distance", "aTargets": [ 5 ] },
					{ "sClass": "gps_distance", "aTargets": [ 6 ] },
					 ],
//		 			 "fnInitComplete": function(oSettings, json) {
//		 					summury();
//		 					},
				});
				jQuery(
						'#summarydashboardActual_wrapper .dataTables_filter input')
						.addClass("form-control input-small input-inline"); // modify
				// table
				jQuery(
						'#summarydashboardActual_wrapper .dataTables_length select')
						.addClass("form-control input-xsmall input-inline"); // modify
				
				
				}
			
			
			if(type ==8){
				var divId=document.getElementById("divisionlist").value;
				var depotId=document.getElementById("depotlist1").value;
				$("#printbutton").show();
				$("#Nrd").hide();
                document.getElementById("routeDeviated").style.display = ''; 
			 	$("#scheduleprint").hide();
			 	$("#running").hide();
			 	$("#partial").hide();
			 	$("#skipstop").hide();
			 	$("#deviated").show();
			 	$("#schLate").hide();
				$("#DriverLate").hide();
				$("#schEarlydata").hide();
				$("#Driverearlydata").hide();
				$("#partialOp").hide();
				$('#routeDeviated').dataTable({
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					//"iDisplayLength" : 5,
					"sAjaxSource" : "getDashboardSummaryRouteDeviated.action?type="+ type+"&selectedenddate="+selectedenddate+"&endDate="+end+"&divId="+divId+"&depotId="+depotId,
					"sPaginationType" : "bootstrap",
					"bDestroy" : true,
					"bSort" : false,
					"bFilter" : false,
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
						'aTargets' : [ 0 ]},
					{ "sClass": "sch_distance", "aTargets": [ 5 ] },
					{ "sClass": "gps_distance", "aTargets": [ 6 ] },
					 ],
//		 			 "fnInitComplete": function(oSettings, json) {
//		 					summury();
//		 					},
				});
				jQuery(
						'#summarydashboardActual_wrapper .dataTables_filter input')
						.addClass("form-control input-small input-inline"); // modify
				// table
				jQuery(
						'#summarydashboardActual_wrapper .dataTables_length select')
						.addClass("form-control input-xsmall input-inline"); // modify
				
				
				}
			
			
			
			if(type ==9){
				var divId=document.getElementById("divisionlist").value;
				var depotId=document.getElementById("depotlist1").value;
				$("#printbutton").show();
				$("#schLate").show();
				console.log(schLate);
				$("#Nrd").hide();
                document.getElementById("schedulewiseLate").style.display = ''; 
			 	$("#scheduleprint").hide();
			 	$("#running").hide();
			 	$("#partial").hide();
			 	$("#skipstop").hide();
			 	$("#deviated").hide();
			 
				$("#DriverLate").hide();
				$("#schEarlydata").hide();
				$("#Driverearlydata").hide();
				$("#partialOp").hide();
				$('#schedulewiseLate').dataTable({
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					//"iDisplayLength" : 5,
					"sAjaxSource" : "getschedulewiseLateData.action?type="+ type+"&selectedenddate="+selectedenddate+"&endDate="+end+"&divId="+divId+"&depotId="+depotId,
					"sPaginationType" : "bootstrap",
					"bDestroy" : true,
					"bSort" : false,
					"bFilter" : false,
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
						'aTargets' : [ 0 ]},
					{ "sClass": "sch_distance", "aTargets": [ 1] },
					{ "sClass": "gps_distance", "aTargets": [ 2 ] },
					 ],
//		 			 "fnInitComplete": function(oSettings, json) {
//		 					summury();
//		 					},
				});
				jQuery(
						'#schedulewiseLate_wrapper .dataTables_filter input')
						.addClass("form-control input-small input-inline"); // modify
				// table
				jQuery(
						'#schedulewiseLate_wrapper .dataTables_length select')
						.addClass("form-control input-xsmall input-inline"); // modify
				
				
				}
			
			if(type ==10){
				var divId=document.getElementById("divisionlist").value;
				var depotId=document.getElementById("depotlist1").value;
				$("#printbutton").show();
				$("#Nrd").hide();
                document.getElementById("DriverwiseLate").style.display = ''; 
			 	$("#scheduleprint").hide();
			 	$("#running").hide();
			 	$("#partial").hide();
			 	$("#skipstop").hide();
			 	$("#deviated").hide();
			 	$("#schLate").hide();
				$("#DriverLate").show();
				$("#schEarlydata").hide();
				$("#Driverearlydata").hide();
				$("#partialOp").hide();
				$('#DriverwiseLate').dataTable({
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					//"iDisplayLength" : 5,
					"sAjaxSource" : "getDriverwiseLateDept.action?type="+ type+"&selectedenddate="+selectedenddate+"&endDate="+end+"&divId="+divId+"&depotId="+depotId,
					"sPaginationType" : "bootstrap",
					"bDestroy" : true,
					"bSort" : false,
					"bFilter" : false,
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
						'aTargets' : [ 0 ]},
					{ "sClass": "sch_distance", "aTargets": [ 1 ] },
					{ "sClass": "gps_distance", "aTargets": [ 2 ] },
					 ],
//		 			 "fnInitComplete": function(oSettings, json) {
//		 					summury();
//		 					},
				});
				jQuery(
						'#DriverwiseLate_wrapper .dataTables_filter input')
						.addClass("form-control input-small input-inline"); // modify
				// table
				jQuery(
						'#DriverwiseLate_wrapper .dataTables_length select')
						.addClass("form-control input-xsmall input-inline"); // modify
				
				
				}
			if(type ==11){
				var divId=document.getElementById("divisionlist").value;
				var depotId=document.getElementById("depotlist1").value;
				$("#printbutton").show();
				$("#Nrd").hide();
                document.getElementById("schedulewiseEarly").style.display = ''; 
			 	$("#scheduleprint").hide();
			 	$("#running").hide();
			 	$("#partial").hide();
			 	$("#skipstop").hide();
			 	$("#deviated").hide();
			 	$("#schLate").hide();
				$("#DriverLate").hide();
				$("#schEarlydata").show();
				$("#Driverearlydata").hide();
				$("#partialOp").hide();
				$('#schedulewiseEarly').dataTable({
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					//"iDisplayLength" : 5,
					"sAjaxSource" : "getschedulewiseEarlyArrival.action?type="+ type+"&selectedenddate="+selectedenddate+"&endDate="+end+"&divId="+divId+"&depotId="+depotId,
					"sPaginationType" : "bootstrap",
					"bDestroy" : true,
					"bSort" : false,
					"bFilter" : false,
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
						'aTargets' : [ 0 ]},
					{ "sClass": "sch_distance", "aTargets": [ 1 ] },
					{ "sClass": "gps_distance", "aTargets": [ 2 ] },
					 ],
//		 			 "fnInitComplete": function(oSettings, json) {
//		 					summury();
//		 					},
				});
				jQuery(
						'#schedulewiseEarly_wrapper .dataTables_filter input')
						.addClass("form-control input-small input-inline"); // modify
				// table
				jQuery(
						'#schedulewiseEarly_wrapper .dataTables_length select')
						.addClass("form-control input-xsmall input-inline"); // modify
				
				
				}
			if(type ==12){
				var divId=document.getElementById("divisionlist").value;
				var depotId=document.getElementById("depotlist1").value;
				$("#printbutton").show();
				$("#Nrd").hide();
                document.getElementById("DriverwiseEarly").style.display = ''; 
			 	$("#scheduleprint").hide();
			 	$("#running").hide();
			 	$("#partial").hide();
			 	$("#skipstop").hide();
			 	$("#deviated").hide();
			 	$("#schLate").hide();
				$("#DriverLate").hide();
				$("#schEarlydata").hide();
				$("#Driverearlydata").show();
				$("#partialOp").hide();
				$('#DriverwiseEarly').dataTable({
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					//"iDisplayLength" : 5,
					"sAjaxSource" : "getDriverWiseEarlyArrival.action?type="+ type+"&selectedenddate="+selectedenddate+"&endDate="+end+"&divId="+divId+"&depotId="+depotId,
					"sPaginationType" : "bootstrap",
					"bDestroy" : true,
					"bSort" : false,
					"bFilter" : false,
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
						'aTargets' : [ 0 ]},
					{ "sClass": "sch_distance", "aTargets": [ 1 ] },
					{ "sClass": "gps_distance", "aTargets": [ 2 ] },
					 ],
//		 			 "fnInitComplete": function(oSettings, json) {
//		 					summury();
//		 					},
				});
				jQuery(
						'#DriverwiseEarly_wrapper .dataTables_filter input')
						.addClass("form-control input-small input-inline"); // modify
				// table
				jQuery(
						'#DriverwiseEarly_wrapper .dataTables_length select')
						.addClass("form-control input-xsmall input-inline"); // modify
				
				
				}
			if(type ==13){
				var divId=document.getElementById("divisionlist").value;
				var depotId=document.getElementById("depotlist1").value;
				$("#printbutton").show();
				$("#Nrd").hide();
                document.getElementById("PartialOpe").style.display = ''; 
			 	$("#scheduleprint").hide();
			 	$("#running").hide();
			 	$("#partial").hide();
			 	$("#skipstop").hide();
			 	$("#deviated").hide();
			 	$("#schLate").hide();
				$("#DriverLate").hide();
				$("#schEarlydata").hide();
				$("#Driverearlydata").hide();
				$("#partialOp").show();
				$('#PartialOpe').dataTable({
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					//"iDisplayLength" : 5,
					"sAjaxSource" : "getPartialOperatedData.action?type="+ type+"&selectedenddate="+selectedenddate+"&endDate="+end+"&divId="+divId+"&depotId="+depotId,
					"sPaginationType" : "bootstrap",
					"bDestroy" : true,
					"bSort" : false,
					"bFilter" : false,
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
						'aTargets' : [ 0 ]},
					{ "sClass": "sch_distance", "aTargets": [ 1 ] },
					{ "sClass": "gps_distance", "aTargets": [ 2 ] },
					 ],
//		 			 "fnInitComplete": function(oSettings, json) {
//		 					summury();
//		 					},
				});
				jQuery(
						'#PartialOpe_wrapper .dataTables_filter input')
						.addClass("form-control input-small input-inline"); // modify
				// table
				jQuery(
						'#PartialOpe_wrapper .dataTables_length select')
						.addClass("form-control input-xsmall input-inline"); // modify
				
				}
			
			}
	
	function getAlertScheduleWiseEarlyArrival(depot,schedulename,startdate,enddate) {
		
		//document.getElementById("schEarlydata1").style.display = '';
		$("#schEarlydata1").show();
		$("#driverEarlydata1").hide();
		$("#schdwiseLatedata1").hide();
		$("#driverwiseLatedata1").hide()
		$("#schddist80data1").hide();
		var oTable = $('#schedulewiseEarly1').dataTable({
			"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
			// per
			// page
			// values
			// here
			],
			// set the initial value
			"iDisplayLength" : 5,
			"sAjaxSource" : "AlertScheduleWiseEarlyArrival.action?depot="
							+ depot + "&schedulename=" + schedulename
							+ "&startdate=" + startdate + "&enddate="
							+ enddate,
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
		jQuery('#schedulewiseEarly1_wrapper .dataTables_filter input').addClass(
				"form-control input-small input-inline"); // modify
		// table
		jQuery('#schedulewiseEarly1_wrapper .dataTables_length select').addClass(
				"form-control input-xsmall input-inline"); // modify
	}
	
function getAlertDriverWiseEarlyArrival(depot,drivertokenno,startdate,enddate) {
		
		//document.getElementById("schEarlydata1").style.display = '';
		$("#driverEarlydata1").show();
		$("#schEarlydata1").hide();
		$("#schdwiseLatedata1").hide();
		$("#driverwiseLatedata1").hide()
		$("#schddist80data1").hide();
		var oTable = $('#driverwiseEarly1').dataTable({
			"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
			// per
			// page
			// values
			// here
			],
			// set the initial value
			"iDisplayLength" : 5,
			"sAjaxSource" : "AlertDriverWiseEarlyArrival.action?depot="
							+ depot + "&drivertokenno=" + drivertokenno
							+ "&startdate=" + startdate + "&enddate="
							+ enddate,
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
		jQuery('#driverwiseEarly1_wrapper .dataTables_filter input').addClass(
				"form-control input-small input-inline"); // modify
		// table
		jQuery('#driverwiseEarly1_wrapper .dataTables_length select').addClass(
				"form-control input-xsmall input-inline"); // modify
	}
	
function getAlertSchWiseLateDept(depot,schedulename,startdate,enddate) {
	
	//document.getElementById("schEarlydata1").style.display = '';
	$("#schdwiseLatedata1").show();
	$("#driverEarlydata1").hide();
	$("#schEarlydata1").hide();
	//$("#schdwiseLatedata1").hide();
	$("#driverwiseLatedata1").hide()
	$("#schddist80data1").hide();
	var oTable = $('#schdwiseLate1').dataTable({
		"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
		// per
		// page
		// values
		// here
		],
		// set the initial value
		"iDisplayLength" : 5,
		"sAjaxSource" : "AlertSchWiseLateDept.action?depot="
						+ depot + "&schedulename=" + schedulename
						+ "&startdate=" + startdate + "&enddate="
						+ enddate,
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
	jQuery('#schdwiseLate1_wrapper .dataTables_filter input').addClass(
			"form-control input-small input-inline"); // modify
	// table
	jQuery('#schdwiseLate1_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline"); // modify
}

function getAlertDriverWiseLateDept(depot,drivertokenno,startdate,enddate) {
	
	//document.getElementById("schEarlydata1").style.display = '';
	$("#driverwiseLatedata1").show();
	$("#driverEarlydata1").hide();
	$("#schEarlydata1").hide();
	$("#schdwiseLatedata1").hide();
	$("#schddist80data1").hide();
	var oTable = $('#driverwiseLate1').dataTable({
		"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
		// per
		// page
		// values
		// here
		],
		// set the initial value
		"iDisplayLength" : 5,
		"sAjaxSource" : "AlertDriverWiseLateDept.action?depot="
						+ depot + "&drivertokenno=" + drivertokenno
						+ "&startdate=" + startdate + "&enddate="
						+ enddate,
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
	jQuery('#driverwiseLate1_wrapper .dataTables_filter input').addClass(
			"form-control input-small input-inline"); // modify
	// table
	jQuery('#driverwiseLate1_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline"); // modify
}

function getAlertScheduledist80percent(depot,schdulename,startdate,enddate,dutytypeid) {
	
	//document.getElementById("schEarlydata1").style.display = '';
	$("#schddist80data1").show();
	$("#driverwiseLatedata1").hide();
	$("#driverEarlydata1").hide();
	$("#schEarlydata1").hide();
	$("#schdwiseLatedata1").hide();
	
	var oTable = $('#schddist80').dataTable({
		"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
		// per
		// page
		// values
		// here
		],
		// set the initial value
		"iDisplayLength" : 5,
		"sAjaxSource" : "AlertScheduledist80percent.action?depot="
						+ depot + "&schdulename=" + schdulename+ "&dutytypeid=" + dutytypeid
						+ "&startdate=" + startdate + "&enddate="
						+ enddate,
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
	jQuery('#schddist80_wrapper .dataTables_filter input').addClass(
			"form-control input-small input-inline"); // modify
	// table
	jQuery('#schddist80_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline"); // modify
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
							DashBoard Summary Report <small></small>
						</h3>

						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>View Dashboard Summary Report
							</div>
							<div class="actions">
								<input type='button' class='btn blue' id='button1'
									onclick='printDiv()' value='Print' />
								<button type="button" class='btn blue' id="btnExport"
									filename="report" onclick="tabletoExcel();">EXPORT</button>
							</div>

						</div>

						<div class="portlet-body">
							<!-- 						<div class="table-scrollable"> -->


							<!-- 						start -->
							<form action="" method="post" class="form-horizontal">
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Division<font
											color="red">*</font></label>
										<div class="col-md-3">
											<s:select list="divisionlist" id="divisionlist"
												name="org_chart_id" cssClass="select2_category form-control"
												onchange="getDepotList(this.value)" headerKey="0"
												headerValue="--ALL--"></s:select>

										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">Depot<font
											color="red">*</font></label>
										<div class="col-md-3">
											<select list="depotlist" id="depotlist1"
												class="select2_category form-control" name="depotlist1">
												<option value="0">--ALL--</option>
											</select>
										</div>
									</div>



									<!-- 								<div class="form-group"> -->
									<!-- 									<label class="control-label col-md-2">Type:</label> -->
									<div class="form-group">
										<label class="col-md-3 control-label">Type<font
											color="red">*</font></label>
										<div class="col-md-3">
											<select id="type" name="type"
												class="select2_category form-control">
												<option value="0">---Select---</option>
												<option value="1">Late Deparature</option>
												<option value="2">Early Arrival</option>
												<option value="3">Schedule Not Departed</option>
												<option value="4">Partial Cancellation</option>
												<option value="5">Actual Running Time of Schedule</option>
												<option value="6">NRD Vehicle</option>
												<option value="7">Bus Skip Stop</option>
												<option value="8">Deviated Route</option>
												<option value="9">Schedule wise Late Departure</option>
												<option value="10">Driver wise Late Departure</option>
												<option value="11">Schedule wise Early Arrival</option>
												<option value="12">Driver wise Early Arrival</option>
												<option value="13">80% Distance Operated(Schedule
													wise)</option>

											</select>

										</div>



									</div>


									<!-- 									<div class="form-group">  -->
									<label class="control-label col-md-3">From:</label>
									<div class="col-md-3">
										<!-- 								<div class="col-md-4"> -->
										<div class="input-group input-medium date date-picker"
											style="width: auto" data-date-format="dd-mm-yyyy"
											data-date-viewmode="years">
											<!-- data-date-start-date="+0d"> -->
											<input id="selectedenddate" class="form-control" type="text"
												readonly="" size="16" name="rateMaster.effectiveStartDate"
												value="<s:property value='rateMaster.effectiveStartDate' />">
											<span class="input-group-btn">
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
											<script>
										var curr_date=new Date().toJSON().slice(0,10);
                                        curr_date=curr_date.split("-");
                                        curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
                                        var dtval=document.getElementById('selectedenddate').value;	
                                        
                                        if(dtval==''){
                                        $('#selectedenddate').val(curr_date);
                                        }
								</script>
										</div>
									</div>
									<!-- 								</div> -->

									<div class="form-group">
										<label class="control-label col-md-1">To:</label>
										<div class="col-md-3">

											<div class="input-group input-medium date date-picker"
												style="width: auto" data-date-format="dd-mm-yyyy"
												data-date-viewmode="years">
												<!-- data-date-start-date="+0d"> -->
												<input id="endate" class="form-control" type="text"
													readonly="" size="16" name="rateMaster.effectiveStartDate"
													value="<s:property value='rateMaster.effectiveStartDate' />">
												<span class="input-group-btn">
													<button class="btn default date-set" type="button">
														<i class="fa fa-calendar"></i>
													</button>
												</span>
												<script>
										var curr_date=new Date().toJSON().slice(0,10);
                                        curr_date=curr_date.split("-");
                                        curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
                                        var dtval=document.getElementById('endate').value;	
                                        
                                        if(dtval==''){
                                        $('#endate').val(curr_date);
                                        
                                        }
								</script>
											</div>
										</div>
									</div>
									<div align="Center">
										<button type="button" class="btn green"
											onclick="getScheduledTripStatusDataOnSubmit()"
											style="position: static;">Submit</button>
									</div>

									<!-- end -->
								</div>
							</form>
						</div>
						<script>                 	
  	 function tabletoExcel(btnExport){     
  	var htmltable= document.getElementById('btnExport');   
    $( "#header-fixed" ).remove();
    var inputHTML = "<table border='1'>";
    inputHTML += "<tr>";
    inputHTML += "<th  align='left'colspan='9'>Bangalore Metropolitan Transport Corporation</th>";
    inputHTML += "</tr>";
    inputHTML += "<th  align='left'colspan='9'>DashBoard Summary Report</th>";
    inputHTML += "</tr>";
    inputHTML += "<tr>";
    inputHTML += "<th colspan='2' align='left'>Date Range:</th>";
    inputHTML += "<th colspan='7' align='left'>"  + $("#selectedenddate").val() + "</th>";
    inputHTML += "</tr>";
    inputHTML += "</table>";
    var type=$("#type").val();
// 	alert("type val"+type);
var htmltable="";
if(type == 1|| type ==2|| type==3){
	 htmltable = document.getElementById("tripstatus");
	 var html = inputHTML + htmltable.outerHTML;
	    var downloadLink = document.createElement("a");
	    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
	    downloadLink.download = "DashBoard Summary Report.xls";
	    document.body.appendChild(downloadLink);
	    downloadLink.click();
	    document.body.removeChild(downloadLink);
}
    if(type == 4){
    	 htmltable = document.getElementById("printData");
    	 var html = inputHTML + htmltable.outerHTML;
    	    var downloadLink = document.createElement("a");
    	    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
    	    downloadLink.download = "DashBoard Summary Partial Cancellation Report.xls";
    	    document.body.appendChild(downloadLink);
    	    downloadLink.click();
    	    document.body.removeChild(downloadLink);
    }
    
    if(type ==5){
    	 htmltable = document.getElementById("printDatarunning");
    	 var html = inputHTML + htmltable.outerHTML;
    	    var downloadLink = document.createElement("a");
    	    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
    	    downloadLink.download = "DashBoard Summary Actual Running Report.xls";
    	    document.body.appendChild(downloadLink);
    	    downloadLink.click();
    	    document.body.removeChild(downloadLink);
    }
      
    if(type ==6){
   	 htmltable = document.getElementById("nrd");
   	 var html = inputHTML + htmltable.outerHTML;
   	    var downloadLink = document.createElement("a");
   	    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
   	    downloadLink.download = "DashBoard Summary NRD Report.xls";
   	    document.body.appendChild(downloadLink);
   	    downloadLink.click();
   	    document.body.removeChild(downloadLink);
   }
    
    if(type ==7){
   	 htmltable = document.getElementById("skip");
   	 var html = inputHTML + htmltable.outerHTML;
   	    var downloadLink = document.createElement("a");
   	    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
   	    downloadLink.download = "DashBoard Summary Skip Stop Report.xls";
   	    document.body.appendChild(downloadLink);
   	    downloadLink.click();
   	    document.body.removeChild(downloadLink);
   }
        
    if(type ==8){
   	 htmltable = document.getElementById("deviate");
   	 var html = inputHTML + htmltable.outerHTML;
   	    var downloadLink = document.createElement("a");
   	    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
   	    downloadLink.download = "DashBoard Summary Route Deviated Report.xls";
   	    document.body.appendChild(downloadLink);
   	    downloadLink.click();
   	    document.body.removeChild(downloadLink);
   }
    if(type ==9){
      	 htmltable = document.getElementById("schwiselate");
      	 var html = inputHTML + htmltable.outerHTML;
      	    var downloadLink = document.createElement("a");
      	    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
      	    downloadLink.download = "DashBoard Summary Schedulewise Late Dept Report.xls";
      	    document.body.appendChild(downloadLink);
      	    downloadLink.click();
      	    document.body.removeChild(downloadLink);
      }
    if(type ==10){
      	 htmltable = document.getElementById("driverwiselate");
      	 var html = inputHTML + htmltable.outerHTML;
      	    var downloadLink = document.createElement("a");
      	    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
      	    downloadLink.download = "DashBoard Summary Driverwise Late Dept Report.xls";
      	    document.body.appendChild(downloadLink);
      	    downloadLink.click();
      	    document.body.removeChild(downloadLink);
      }
    if(type ==11){
      	 htmltable = document.getElementById("schEarly");
      	 var html = inputHTML + htmltable.outerHTML;
      	    var downloadLink = document.createElement("a");
      	    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
      	    downloadLink.download = "DashBoard Summary Schedulewise Early Arrival Report.xls";
      	    document.body.appendChild(downloadLink);
      	    downloadLink.click();
      	    document.body.removeChild(downloadLink);
      }
    if(type ==12){
      	 htmltable = document.getElementById("driverwiseEarly");
      	 var html = inputHTML + htmltable.outerHTML;
      	    var downloadLink = document.createElement("a");
      	    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
      	    downloadLink.download = "DashBoard Summary Driverwise Early Arrival Report.xls";
      	    document.body.appendChild(downloadLink);
      	    downloadLink.click();
      	    document.body.removeChild(downloadLink);
      }
    if(type ==13){
      	 htmltable = document.getElementById("partialopsch");
      	 var html = inputHTML + htmltable.outerHTML;
      	    var downloadLink = document.createElement("a");
      	    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
      	    downloadLink.download = "DashBoard Summary Schedulewise Partial Operated(80%)Report.xls";
      	    document.body.appendChild(downloadLink);
      	    downloadLink.click();
      	    document.body.removeChild(downloadLink);
      }
    
   
}</script>

					</div>
				</div>
			</div>
			

			<div id="header" class="portlet-body"
				style="display: none; visibility: hidden;">
				<h4 style="margin-left: 350px;">DashBoard Summary Report</h4>
				<br />
				<div id="headerdetails" style="margin-left: 0px;">
					<table ID="mytable" style="width: 60%; border: none;">


					</table>
					<br />
					<br />

				</div>
			</div>
			<!-- 								<div class="portlet-body" id="scheduleprint" style='height:310px; overflow-y:scroll; display:block;'> -->
			<div class="portlet-body" id="scheduleprint" style='display: block;'>
				<div id="tripstatus">
					<table class="table table-striped table-bordered table-hover"
						id="summarydashboardreport" style="display: none">
						<thead>
							<tr>
								<th>Sr. NO</th>
								<th>Depot Name</th>
								<th>No. Of Schedules</th>
								<th>General Shift</th>
								<th>Shift 1</th>
								<th>Split Service</th>
								<th>Night Out</th>
								<th>Shift 2</th>
								<th>Night Service</th>
								<th>Total</th>
								<!-- <th>percentage(%)</th> -->
							</tr>
						</thead>
					</table>
				</div>
			</div>
			<!-- 							</form> -->
			<!-- END FORM-->

			<div class="portlet-body" id="partial" style='display: block;'>
				<div id="printData">
					<table class="table table-striped table-bordered table-hover"
						id="summarydashboardActual" style="display: none">
						<thead>
							<tr>
								<th>Sr. NO</th>
								<th>Depot Name</th>
								<th>Schedule Name</th>
								<th>Shift Name</th>
								<th>Schedule distance</th>
								<th>Total Distance</th>
								<th>percentage(%)</th>
							</tr>
						</thead>
					</table>

				</div>
			</div>

			<div class="portlet-body" id="running" style='display: block;'>
				<div id="printDatarunning">
					<table class="table table-striped table-bordered table-hover"
						id="summarydashboardRunning" style="display: none">
						<thead>
							<tr>
								<th>Sr.NO</th>
								<th>Depot Name</th>
								<th>Schedule No</th>
								<th>Shift</th>
								<th>From</th>
								<th>To</th>
								<th>Running Time</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>


			<div class="portlet-body" id="Nrd" style='display: block;'>
				<div id="nrd">
					<table class="table table-striped table-bordered table-hover"
						id="summaryDashboardNRD" style="display: none">
						<thead>
							<tr>
								<th>Sr.NO</th>
								<th>Depot Name</th>
								<th>Total Vehicles</th>
								<th>Total NRD</th>
								<th>percentage(%)</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
			<div class="portlet-body" id="skipstop" style='display: block;'>
				<div id="skip">
					<table class="table table-striped table-bordered table-hover"
						id="summarySkipStop" style="display: none">
						<thead>
							<tr>
								<th>Sr. NO</th>
								<th>Depot Name</th>
								<th>No. Of Schedules</th>
								<th>General Shift</th>
								<th>Day1</th>
								<th>Shift 1</th>
								<th>Split Service</th>
								<th>Day2</th>
								<th>Shift 2</th>
								<th>Night Service</th>
								<th>Total</th>

							</tr>
						</thead>
					</table>
				</div>
			</div>


			<div class="portlet-body" id="deviated" style='display: block;'>
				<div id="deviate">
					<table class="table table-striped table-bordered table-hover"
						id="routeDeviated" style="display: none">
						<thead>
							<tr>
								<th>Sr.NO</th>
								<th>Depot Name</th>
								<th>Total Schedules</th>
								<th>General Shift</th>
								<th>Day1</th>
								<th>Shift 1</th>
								<th>Split Service</th>
								<th>Day2</th>
								<th>Shift 2</th>
								<th>Night Service</th>
								<th>Total</th>

							</tr>
						</thead>
					</table>
				</div>
			</div>

			<div class="portlet-body" id="schLate" style='display: block;'>
				<div id="schwiselate">
					<table class="table table-striped table-bordered table-hover"
						id="schedulewiseLate" style="display: none">
						<thead>
							<tr>
								<th>Sr.NO</th>
								<th>Depot Name</th>
								<th>Schedule Name</th>
								<th>Count</th>


							</tr>
						</thead>
					</table>
				</div>
			</div>
			<div class="portlet-body" id="DriverLate" style='display: block;'>
				<div id="driverwiselate">
					<table class="table table-striped table-bordered table-hover"
						id="DriverwiseLate" style="display: none">
						<thead>
							<tr>
								<th>Sr.NO</th>
								<th>Depot Name</th>
<!-- 								<th>Schedule Name</th> -->
								<th>Driver Name</th>
								<th>Token No</th>
								<th>Count</th>

							</tr>
						</thead>
					</table>
				</div>
			</div>
			<div class="portlet-body" id="schEarlydata" style='display: block;'>
				<div id="schEarly">
					<table class="table table-striped table-bordered table-hover"
						id="schedulewiseEarly" style="display: none">
						<thead>
							<tr>
								<th>Sr.NO</th>
								<th>Depot Name</th>
								<th>Schedule No.</th>
								<th>Count</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		
			<div class="portlet-body" id="Driverearlydata"
				style='display: block;'>
				<div id="driverwiseEarly">
					<table class="table table-striped table-bordered table-hover"
						id="DriverwiseEarly" style="display: none">
						<thead>
							<tr>
								<th>Sr.NO</th>
								<th>Depot Name</th>
								<th>Schedule Name</th>
								<th>Driver Name</th>
								<th>Token No.</th>
								<th>Count</th>

							</tr>
						</thead>
					</table>
				</div>
			</div>
			<div class="portlet-body" id="partialOp" style='display: block;'>
				<div id="partialopsch">
					<table class="table table-striped table-bordered table-hover"
						id="PartialOpe" style="display: none">
						<thead>
							<tr>
								<th>Sr.NO</th>
								<th>Depot Name</th>
								<th>Schedule Name</th>
								<th>Avg Distance</th>
								<th>Count</th>

							</tr>
						</thead>
					</table>
				</div>
			</div>

		</div>
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
			<div class="portlet-body" id="schEarlydata1" style="display: none">
			
				<div style="text-align:center">
					<table class="table table-striped table-bordered table-hover"
						id="schedulewiseEarly1">
						<thead>
							<tr>
							<th>Sr No</th>
								<th>Schedule No</th>
								<th>Vehicle No</th>
								<th>End Time</th>
								<th>Actual Time</th>
								<th>Diff (min)</th>
								<th>Duty Date</th>
<!-- 								<th>Depot</th> -->
							</tr>
						</thead>
					</table>
				</div>
				
			</div>
			
			<div class="portlet-body" id="driverEarlydata1" style="display: none">
			
				<div style="text-align:center">
					<table class="table table-striped table-bordered table-hover"
						id="driverwiseEarly1">
						<thead>
							<tr>
							<th>Sr No</th>
								<th>Token No</th>
								<th>Schedule No</th>
								<th>End Time</th>
								<th>Actual Time</th>
								<th>Diff</th>
								<th>Duty Date</th>
								
							</tr>
						</thead>
					</table>
				</div>
				
			</div>
			
			<div class="portlet-body" id="schdwiseLatedata1" style="display: none">
			
				<div style="text-align:center">
					<table class="table table-striped table-bordered table-hover"
						id="schdwiseLate1">
						<thead>
							<tr>
							<th>Sr No</th>
								<th>Schedule No</th>
								<th>Vehicle No</th>
								<th>Start Time</th>
								<th>Actual Time</th>
								<th>Diff</th>
								<th>Duty Date</th>
								
							</tr>
						</thead>
					</table>
				</div>
				
			</div>
			
				<div class="portlet-body" id="driverwiseLatedata1" style="display: none">
			
				<div style="text-align:center">
					<table class="table table-striped table-bordered table-hover"
						id="driverwiseLate1">
						<thead>
							<tr>
							    <th>Sr No</th>
								<th>Token No</th>
								<th>Schedule No</th>
								<th>Start Time</th>
								<th>Actual Time</th>
								<th>Diff</th>
								<th>Duty Date</th>
								
							</tr>
						</thead>
					</table>
				</div>
				
			</div>
			<div class="portlet-body" id="schddist80data1" style="display: none">
			
				<div style="text-align:center">
					<table class="table table-striped table-bordered table-hover"
						id="schddist80">
						<thead>
							<tr>
							   <th>Sr No</th>
								<th>Schedule No</th>
								<th>Duty Date</th>
								<th>Schedule Distance</th>
								<th>Total Distance</th>
								<th>Percentage</th>
								<th>Depot</th>
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

</body>
</html>