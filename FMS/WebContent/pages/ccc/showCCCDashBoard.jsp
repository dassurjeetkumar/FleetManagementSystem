<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<html>
	<head>
	<style>
	#chartdiv {
	width		: 50%;
	height		: 435px;
	font-size	: 11px;
			}			
</style>	
<style>
			#chartdiv2 {
	width		: 50%;
	height		: 435px;
	font-size	: 11px;
			}	
</style>					
	</head>
	<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places,drawing"></script>
 <script type="text/javascript" src="assets/ccc/js/ccc.js"></script>
<script src="assets/vts/js/vts.js" type="text/javascript"></script>
<script type="text/javascript" src="assets/ccc/js/ccc-charts.js"></script> 
<script src="assets/vts/js/scheduledeviation.js" type="text/javascript"></script>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script> 
<%@page import="com.trimax.its.piechart.model.showPiechartModel" %>
<%@page import="com.trimax.its.piechart.doa.ShowPichartDoa" %>
<!-- //3d start -->
<script type="text/javascript" src="assets/ccc/js/amchart.js"></script>
<script type="text/javascript" src="assets/ccc/js/serial.js"></script>
<%-- <script type="text/javascript" src="http://www.amcharts.com/lib/3/pie.js"></script> --%>

<%-- <script type="text/javascript" src="http://www.amcharts.com/lib/3/themes/none.js"></script> --%>
<!-- //3d end -->

<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places,drawing"></script>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
<script>
function viewDepotVehicleData(depotid)
{
	//alert("viewDepotBreakdownData"+depotid);
	$('#vehicledepotwise_id').dataTable(
			{
				"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
				// per
				// page
				// values
				// here
				],
				// set the initial value
				"iDisplayLength" : 5,
				"sAjaxSource" : "getVehicleDepotdata.action?depotid="+depotid,
				
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
	jQuery('#empstatusdetails_id_wrapper .dataTables_filter input').addClass(
			"form-control input-medium input-inline"); // modify table
	// search input
	jQuery('#empstatusdetails_id_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");
	
//search input
	
}
function viewDepotBreakdownData(depotame)
{
	
	
	var depotid=depotame;
	
	$('#breakdowndepotwise_id').dataTable(
			{
				"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
				// per
				// page
				// values
				// here
				],
				// set the initial value
				"iDisplayLength" : 5,
				"sAjaxSource" : "getBreakdownstatuswisedata.action?depotid="+depotid,
				
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
	jQuery('#empstatusdetails_id_wrapper .dataTables_filter input').addClass(
			"form-control input-medium input-inline"); // modify table
	// search input
	jQuery('#empstatusdetails_id_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");
	
//search input
	
}
function viewDepotAccidentData(depotame)
{
	
	var depotid=depotame;
	
	
	
	$('#accidentdepotwise_id').dataTable(
			{
				"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
				// per
				// page
				// values
				// here
				],
				// set the initial value
				"iDisplayLength" : 5,
				"sAjaxSource" : "getAccidentstatuswisedata.action?depotid="+depotid,
				
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
	jQuery('#empstatusdetails_id_wrapper .dataTables_filter input').addClass(
			"form-control input-medium input-inline"); // modify table
	// search input
	jQuery('#empstatusdetails_id_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");
	
//search input
	
}
function viewDepotTicket(depotid)
{
//	alert("depotid+getLCDepotData"+depotid);
	$('#ticketdetailsdepotwise_id').dataTable(
			{
				"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
				// per
				// page
				// values
				// here
				],
				// set the initial value
				"iDisplayLength" : 5,
				"sAjaxSource" : "getTicketDepotData.action?depotid="+depotid,
				
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
	jQuery('#empstatusdetails_id_wrapper .dataTables_filter input').addClass(
			"form-control input-medium input-inline"); // modify table
	// search input
	jQuery('#empstatusdetails_id_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");
	
//search input
	
}
//pass details depotwise
function viewDepotPass(depotid)
{
//	alert("depotid+getLCDepotData"+depotid);
	$('#passdetailsdepotwise_id').dataTable(
			{
				"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
				// per
				// page
				// values
				// here
				],
				// set the initial value
				"iDisplayLength" : 5,
				"sAjaxSource" : "getPassDepotData.action?depotid="+depotid,
				
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
	jQuery('#empstatusdetails_id_wrapper .dataTables_filter input').addClass(
			"form-control input-medium input-inline"); // modify table
	// search input
	jQuery('#empstatusdetails_id_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");
	
//search input
	
}
//end pass details end
function viewDepotLC(depotid)
{
	//alert("depotid+getLCDepotData"+depotid);
	$('#lcdetailsdepotwise_id').dataTable(
			{
				"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
				// per
				// page
				// values
				// here
				],
				// set the initial value
				"iDisplayLength" : 5,
				"sAjaxSource" : "getLCDepotData.action?depotid=" + depotid,
				
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
	jQuery('#empstatusdetails_id_wrapper .dataTables_filter input').addClass(
			"form-control input-medium input-inline"); // modify table
	// search input
	jQuery('#empstatusdetails_id_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");
	
//search input
	
}
function viewDataDepotwise(depotid,status)
{
	//alert("depotid"+depotid+""+"status"+status);
	$('#empstatusdetails_id').dataTable(
			{
				"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
				// per
				// page
				// values
				// here
				],
				// set the initial value
				"iDisplayLength" : 5,
				"sAjaxSource" : "getEmployeeData.action?depotid=" + depotid
				+ "&employeestatus=" + status,
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
	jQuery('#empstatusdetails_id_wrapper .dataTables_filter input').addClass(
			"form-control input-medium input-inline"); // modify table
	// search input
	jQuery('#empstatusdetails_id_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");
	
//search input

	
	}
	
	//view depotwise data manual ticket
function viewTicketDataDepotwise(depotid)
{
	//alert("depotid"+depotid+""+"status"+status);
	$('#ticketstatusdetails_id').dataTable(
			{
				"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
				// per
				// page
				// values
				// here
				],
				// set the initial value
				"iDisplayLength" : 5,
				"sAjaxSource" : "getManualTicketDepotwiseData.action?depotid=" + depotid,
				//+ "&employeestatus=" + status,
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
	jQuery('#ticketstatusdetails_id_wrapper .dataTables_filter input').addClass(
			"form-control input-medium input-inline"); // modify table
	// search input
	jQuery('#ticketstatusdetails_id_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");
	
//search input

	
	}
	//view depotwise data manual ticket
function getLCCount()
{
	//alert("dddd");
	$('#LC_count_id').dataTable(
				{
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					"iDisplayLength" : 5,
					//"sAjaxSource" : "getLCCountData.action",
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
function getTotalTicketCount()
{
	//alert_ticket_count_display
	$('#ticket_count_id').dataTable(
				{
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					"iDisplayLength" : 5,
					"sAjaxSource" : "getTicketCountData.action",
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
//pass count

function getTotalPassCount()
{
	
	$('#pass_count_id').dataTable(
				{
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					"iDisplayLength" : 5,
					"sAjaxSource" : "getPassCountData.action",
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
function getComplaintCount()
{
	
	$('#complaint_count_id').dataTable(
				{
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					"iDisplayLength" : 5,
					"sAjaxSource" : "getComplaintCountData.action",
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
		jQuery('#complaint_count_id_wrapper .dataTables_filter input').addClass(
				"form-control input-medium input-inline"); // modify table
		// search input
		jQuery('#complaint_count_id_wrapper .dataTables_length select').addClass(
				"form-control input-xsmall input-inline");
		
// search input
}

//trip count
function getTripCount()
{
	
	$('#trip_count_id').dataTable(
				{
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					"iDisplayLength" : 5,
					"sAjaxSource" : "getTripCountData.action",
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
		jQuery('#complaint_count_id_wrapper .dataTables_filter input').addClass(
				"form-control input-medium input-inline"); // modify table
		// search input
		jQuery('#complaint_count_id_wrapper .dataTables_length select').addClass(
				"form-control input-xsmall input-inline");
		
// search input
}
//end trip
//schedule count
function getScheduleCount()
{
	
	$('#schedule_count_id').dataTable(
				{
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					"iDisplayLength" : 5,
					"sAjaxSource" : "getScheduleCountData.action",
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
		jQuery('#complaint_count_id_wrapper .dataTables_filter input').addClass(
				"form-control input-medium input-inline"); // modify table
		// search input
		jQuery('#complaint_count_id_wrapper .dataTables_length select').addClass(
				"form-control input-xsmall input-inline");
		
// search input
}
//end schedule

//Route count
function getRouteCount()
{
	
	$('#route_count_id').dataTable(
				{
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					"iDisplayLength" : 5,
					"sAjaxSource" : "getRouteCountData.action",
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
		jQuery('#route_count_id_wrapper .dataTables_filter input').addClass(
				"form-control input-medium input-inline"); // modify table
		// search input
		jQuery('#route_count_id_wrapper .dataTables_length select').addClass(
				"form-control input-xsmall input-inline");
		
// search input
}
//end Route

//bus skip count start

function getBusSkipCount()
{
	
	$('#busskip_count_id').dataTable(
				{
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					"iDisplayLength" : 5,
					"sAjaxSource" : "getBusSkipCountData.action",
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
		jQuery('#busskip_count_id_wrapper .dataTables_filter input').addClass(
				"form-control input-medium input-inline"); // modify table
		// search input
		jQuery('#busskip_count_id_wrapper .dataTables_length select').addClass(
				"form-control input-xsmall input-inline");
		
// search input
}
//bus skip count end
//manual ticket data
function getManualTicketData()
{
	
	$('#manualticketdepotid').dataTable(
				{
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					"iDisplayLength" : 5,
					"sAjaxSource" : "manualticketdepotdata.action",
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
		jQuery('#busskip_count_id_wrapper .dataTables_filter input').addClass(
				"form-control input-medium input-inline"); // modify table
		// search input
		jQuery('#busskip_count_id_wrapper .dataTables_length select').addClass(
				"form-control input-xsmall input-inline");
		
// search input
}

// manual ticket data end

</script>
<form action="insertSOS.action" class="form-horizontal ">
	<div class="page-content-wrapper">
		<div class="page-content">
		<%
 ShowPichartDoa  accessrightdao=new ShowPichartDoa();
showPiechartModel accessrights=new showPiechartModel();

accessrights=accessrightdao.AccessRightPiechart();

int usrsessionid=(Integer)request.getSession().getAttribute("userid");

 String accident=accessrights.getAccident();
String breakdown=accessrights.getBreakdown();
String employee=accessrights.getEmployee();
String vtu=accessrights.getVtudash();
String etm=accessrights.getEtmdash();
String tripcount=accessrights.getTripCountdash();
String schedule=accessrights.getSchedule();
String crewdata=accessrights.getCreawdata();
String depothealth=accessrights.getDepothealth(); 
//depothealth="N";
//out.println("depothealth=="+depothealth);
 /*
out.println("depothealth=="+depothealth);

 out.println("accident=="+accident);
 out.println("breakdown==="+breakdown);
 out.println("employee=="+employee);
 out.println("schedule=="+schedule);
 out.println("crewdata=="+crewdata);  
 out.println("depothealth=="+depothealth);   
 
/*  String accident="Y";
String breakdown="Y";
String employee="Y";
String vtu="Y";
String etm="Y";
String tripcount="Y";  */
 
 
 
%>


<%-- <%!String orgchart=null,orgtype=null;  %>
							<% orgtype=(String)session.getAttribute("orgtypeid");
								orgchart=(String)session.getAttribute("orgchartid");
									session.removeAttribute("Depotid");
					   			out.println("orgtype............"+orgtype);	
					   			out.println("orgchart............"+orgchart);	
					   			out.println();	
					          %> --%>


			<s:if test="hasActionMessages()">
   <div class="alert alert-success alert-dismissable">
								<strong> <s:actionmessage/> </strong>
							</div>
   </s:if>
 <!-- vehicle alert-->  
 <form>
 

 <input type="hidden" id='deviceid' name='deviceid' /> <input
		type="hidden" id='vehicleid' name='vehicleid' /> <input type="hidden"
		id='scheduleno' name='scheduleno' /> <input type="hidden"
		id='routeid' name='routeid' /> <input type="hidden" id='schedulename'
		name='schedulename' /> <input type="hidden" id='startpoint'
		name='startpoint' /> <input type="hidden" id='endpoint'
		name='endpoint' /> <input type="hidden" id='starttime'
		name='starttime' /> <input type="hidden" id='endtime' name='endtime' />  
				<div class="col-md-3" >
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i><b>VTU</b>
							</div>
							<div class="tools">
								<a class="collapse" id="vehicle_chart_live" href="javascript:;">
								</a> <a class="config" data-toggle="modal" href="#portlet-config">
								</a> <a class="reload" href="javascript:;"> </a> <a class="remove"
									href="javascript:;"> </a>
							</div>
						</div>
						<div class="portlet-body" id="chart_live_display">
							<h4>Total VTU Data</h4>
							<p id="demo"></p>
							<div id="alertspie" class="chart"></div>
						</div>
					</div>
				</div>
				<div class="col-md-3" style="display: none;" id="piechart_breakdown_id1">
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i><b>Breakdown</b>
							</div>
							<div class="tools">
								<a class="collapse" id="vehicle_chart_live" href="javascript:;">
								</a> <a class="config" data-toggle="modal" href="#portlet-config">
								</a> <a class="reload" href="javascript:;"> </a> <a class="remove"
									href="javascript:;"> </a>
							</div>
						</div>
						<div class="portlet-body" id="chart_live_display">
							<h4>Total Breakdown Data</h4>
							<p id="demo"></p>
							<div id="alertspie2" class="chart"></div>
						</div>
					</div>
				</div>
				
				<!-- 				//ETM count -->

				<div class="col-md-3" id="piechart_etm_id" style="display: none;">
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i><b>ETM </b>
							</div>
							<div class="tools">
								<a class="collapse" id="vehicle_chart_live" href="javascript:;">
								</a> <a class="config" data-toggle="modal" href="#portlet-config">
								</a> <a class="reload" href="javascript:;"> </a> <a class="remove"
									href="javascript:;"> </a>
							</div>
						</div>
						<div class="portlet-body" id="chart_live_display">
							<h4>Total ETM Count</h4>
							<p id="demo"></p>
							<div id="alertspie6" class="chart"></div>
						</div>
					</div>
				</div>
<!-- 	ETM count  End-->
				<div class="col-md-3"  id="piechart_accident_id" style="display: none;">
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i><b>Vehicle</b>
							</div>
							<div class="tools">
								<a class="collapse" id="vehicle_chart_live" href="javascript:;">
								</a> <a class="config" data-toggle="modal" href="#portlet-config">
								</a> <a class="reload" href="javascript:;"> </a> <a class="remove"
									href="javascript:;"> </a>
							</div>
						</div>
						<div class="portlet-body" id="chart_live_display">
							<h4>Total Vehicle Data</h4>
							<p id="demo"></p>
							<div id="alertspie3" class="chart"></div>
						</div>
					</div>
				</div>
				<div class="col-md-3" id="piechart_employeea_id" style="display: none;">
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i><b>Total Trip Data</b>
							</div>
							<div class="tools">
								<a class="collapse" id="vehicle_chart_live" href="javascript:;">
								</a> <a class="config" data-toggle="modal" href="#portlet-config">
								</a> <a class="reload" href="javascript:;"> </a> <a class="remove"
									href="javascript:;"> </a>
							</div>
						</div>
						<div class="portlet-body" id="chart_live_display">
							<h4>Total Employee Data</h4>
							<p id="demo"></p>
							<div id="alertspie4" class="chart"></div>
						</div>
					</div>
				</div>
<!-- 				//vtu count -->

				<div class="col-md-3" id="piechart_vtu_id" style="display: none;">
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i><b>Vehicle data</b>
							</div>
							<div class="tools">
								<a class="collapse" id="vehicle_chart_live" href="javascript:;">
								</a> <a class="config" data-toggle="modal" href="#portlet-config">
								</a> <a class="reload" href="javascript:;"> </a> <a class="remove"
									href="javascript:;"> </a>
							</div>
						</div>
						<div class="portlet-body" id="chart_live_display">
							<h4>Total Vehicle Data</h4>
							<p id="demo"></p>
							<div id="alertspie5" class="chart"></div>
						</div>
					</div>
				</div>
<!-- 				//vtu count -->


<!-- //total trip pie -->
<div class="col-md-3"  id="Trip_live_display" style="display: none;" >
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i><b>Trip</b>
							</div>
							<div class="tools">
								<a class="collapse" id="vehicle_chart_live" href="javascript:;">
								</a> <a class="config" data-toggle="modal" href="#portlet-config">
								</a> <a class="reload" href="javascript:;"> </a> <a class="remove"
									href="javascript:;"> </a>
							</div>
						</div>
						<div class="portlet-body" >
							<h4>Total Trip Data</h4>
							<p id="demo"></p>
							<div id="alertspie7" class="chart"></div>
						</div>
					</div>
				</div>
<!-- //total trip pie end -->

<!-- total schedule trip count pie -->
<div class="col-md-3" id="schedule_live_display" style="display: none;" >
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i><b>Schedule</b>
							</div>
							<div class="tools">
								<a class="collapse" id="vehicle_chart_live" href="javascript:;">
								</a> <a class="config" data-toggle="modal" href="#portlet-config">
								</a> <a class="reload" href="javascript:;"> </a> <a class="remove"
									href="javascript:;"> </a>
							</div>
						</div>
						<div class="portlet-body" >
							<h4>Total Schedule Data</h4>
							<p id="demo"></p>
							<div id="alertspie8" class="chart"></div>
						</div>
					</div>
				</div>
<!-- end total schedule trip count pie -->

<!-- crew aalocation piechart -->
<div class="col-md-3" id="crewdata_live_display" style="display: none;" >
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i><b>Crew Data</b>
							</div>
							<div class="tools">
								<a class="collapse" id="vehicle_chart_live" href="javascript:;">
								</a> <a class="config" data-toggle="modal" href="#portlet-config">
								</a> <a class="reload" href="javascript:;"> </a> <a class="remove"
									href="javascript:;"> </a>
							</div>
						</div>
						<div class="portlet-body" >
							<h4> Crew Allocation Data</h4>
							<p id="demo"></p>
							<div id="alertspie9" class="chart"></div>
						</div>
					</div>
				</div>

<!-- crew aalocation piechart end-->

<!-- online offline depot piechart -->
<div class="col-md-3" id="Depothealth_live_display" style="display: none;" >
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i><b>Depot Health</b>
							</div>
							<div class="tools">
								<a class="collapse" id="onlineofflinedepot_chart_live" href="javascript:;">
								</a> <a class="config" data-toggle="modal" href="#portlet-config">
								</a> <a class="reload" href="javascript:;"> </a> <a class="remove"
									href="javascript:;"> </a>
							</div>
						</div>
						<div class="portlet-body" >
							<h4> Depot Health</h4>
							<p id="demo"></p>
							<div id="alertspie10" class="chart"></div>
						</div>
					</div>
				</div>

<!-- online offline depot piechart end-->

<!-- //vehicle details -->
<!-- //3d start 1 -->
					<div id="chartdiv"></div>
<div class="container-fluid">
  <div class="row text-center" style="overflow:hidden;">
		<div class="col-sm-3" style="float: none !important;display: inline-block;">
			
			<input type="hidden" class="chart-input" data-property="angle" type="range" min="0" max="89" value="30" step="1"/>	
		</div>

		<div class="col-sm-3" style="float: none !important;display: inline-block;">
			
			<input type="hidden" class="chart-input" data-property="depth3D" type="range" min="1" max="120" value="20" step="1"/>
		</div>
	</div>
</div>													
					<!-- //3d end -->	
					
					<!-- 3d chart 2 start -->
					
  <!-- <div id="chartdiv2"></div> 
<div class="container-fluid">
  <div class="row text-center" style="overflow:hidden;">
		<div class="col-sm-3" style="float: none !important;display: inline-block;">
			<label class="text-left">Angle:</label>       
			<input type="hidden" class="chart-input" data-property="angle" type="range" min="0" max="60" value="30" step="1"/>	
		</div>

		<div class="col-sm-3" style="float: none !important;display: inline-block;">
			
			<input type="hidden" class="chart-input" data-property="depth3D" type="range" min="1" max="25" value="10" step="1"/>
		</div>
		<div class="col-sm-3" style="float: none !important;display: inline-block;">
			<label class="text-left">Inner-Radius:</label>
			<input type="hidden" class="chart-input" data-property="innerRadius" type="range" min="0" max="80" value="0" step="1"/>
		</div>
	</div>
</div>		 																		
		 -->		<!-- 	3d chart 2 end -->

<!-- vehicle start -->

<div class="portlet box blue" id="vehicle" style="display: none;">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i> VTU Details
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
								id="alert_dashBoard_data">
								<thead>
									<tr>
										<th>Sr. No.</th>
										<th>Status</th>
										<th>Vehicle No</th>
										<th>Device ID</th>
										<th>Depot Name</th>
										<th>Speed Km/Hr</th>
										<th>Schedule No</th>
										<th>Shift</th>
										<th>Distance(Km)</th>
										<th>Internal Battery(Volt)</th>
										<th>External Battery(Volt)</th>
										<th>Signal Strength%</th>
										<th>Last Reported Time</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				
				
				
				
				
				
				<div class="portlet box blue" id="vehicleOIB" style="display: none;">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i> VTU Details
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
								id="alert_dashBoardOIB_data">
								<thead>
									<tr>
										<th>Sr. No.</th>
										<th>Status</th>
										<th>Vehicle No</th>
										<th>Device ID</th>
										<th>Depot Name</th>
										<th>Speed Km/Hr</th>
										<th>Schedule No</th>
										<th>Shift</th>
										<th>Distance(Km)</th>
										<th>Internal Battery(Volt)</th>
										<th>External Battery(Volt)</th>
										<th>Signal Strength%</th>
										<th>Last Reported Time</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				
<!-- vehicle end -->


				 <!-- vehicle alert end--> 
			<div class="portlet-body form">
			<div class="portlet box blue" id="alert_accident_display" style="display: none;">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i> Accident Details
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
								id="accidentdata_id" style="max-height: 60px;">
								<thead>
									<tr>
														<th>Vehical No</th>
														<th>Device Id</th>
														<th>Call Time</th>
														<th>Driver Name</th>
														<th>Conductor Name</th>
														<th>Route No</th>
														<th>Schedual No</th>
														<th>Depot Name</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
<!-- 	//datatable accident data end -->

<!-- 	//datatable for Breakdown data -->


	<div class="portlet box blue" id="alert_breakdown_display" style="display: none;">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i> BreakDown Details
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
								id="breakdowndata_id" style="max-height: 60px;">
								<thead>
									<tr>
														<th>Vehical No</th>
														<th>Device Id</th>
														<th>Call Time</th>
														<th>Driver Name</th>
														<th>Conductor Name</th>
														<th>Route No</th>
														<th>Schedual No</th>
														<th>Depot Name</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<!-- 				//vehicle data -->

<div class="portlet box blue" id="Vehicle_data_id"  style="display: none;">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i>Vehicle Details
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
								id="vehicle_count_id" style="max-height: 60px;">
								<thead>
									<tr>
														<th> Depot Name</th>
														
														<th>  Count</th>
														
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				
<!-- 				//vehicle data end -->
<!-- trips data start -->

			<!-- 				Total trip data count -->
				<div class="portlet box blue" id="alert_Partial_display" style="display: none;">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i> Partial Trip
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
								id="partial_trip_id" style="max-height: 60px;">
								<thead>
									<tr>
														<th> Waybill NO.</th>
														<th>Schedule Name</th>
														<th>Vehicle No</th>
														
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				
				<div class="portlet box blue" id="alert_DeviatedTrip_display" style="display: none;">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i> Deviated Trip
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
								id="Trip_DeviatedTrip_id" style="max-height: 60px;">
								<thead>
									<tr>
														<th> Waybill NO.</th>
														<th>Schedule Name</th>
														<th>Vehicle No</th>
														
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				
				<div class="portlet box blue" id="alert_CompletedTrip_display" style="display: none;">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i> Completed Trip
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
								id="Trip_CompletedTrip_id" style="max-height: 60px;">
								<thead>
									<tr>
														<th> Waybill NO.</th>
														<th>Schedule Name</th>
														<th>Vehicle No</th>
														
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<!-- //missed trip -->
				
				<div class="portlet box blue" id="alert_MissedTrip_display" style="display: none;">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i> Misssed Trip
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
								id="Trip_MissedTrip_id" style="max-height: 60px;">
								<thead>
									<tr>
														<th> Waybill NO.</th>
														<th>Schedule Name</th>
														<th>Vehicle No</th>
														
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<!-- //end missed trip -->
				
				<!-- //delay trip -->
				<div class="portlet box blue" id="alert_DelayTrip_display" style="display: none;">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i> Delay Trip
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
								id="Trip_DelayTrip_id" style="max-height: 60px;">
								<thead>
									<tr>
														<th> Waybill NO.</th>
														<th>Schedule Name</th>
														<th>Vehicle No</th>
														
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<!-- //end delay trip -->
				
				
<!-- trip data end				 -->
<!-- //schedule trip start -->

	<!-- 				Total trip data count -->
				<div class="portlet box blue" id="alert_schedulePartial_display" style="display: none;">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i> Partial Schedule
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
								id="schedulepartial_trip_id" style="max-height: 60px;">
								<thead>
									<tr>
														
														<th>Schedule Name</th>
														<th>Vehicle No</th>
														
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				
				<div class="portlet box blue" id="alert_scheduleDeviatedTrip_display" style="display: none;">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i> Deviated Schedule
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
								id="Trip_scheduleDeviatedTrip_id" style="max-height: 60px;">
								<thead>
									<tr>
														
														<th>Schedule Name</th>
														<th>Vehicle No</th>
														
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				
				<div class="portlet box blue" id="alert_scheduleCompletedTrip_display" style="display: none;">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i> Completed Schedule
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
								id="Trip_scheduleCompletedTrip_id" style="max-height: 60px;">
								<thead>
									<tr>
														
														<th>Schedule Name</th>
														<th>Vehicle No</th>
														
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				
				
<!-- 		start crew  -->
		
				
				<div class="portlet box blue" id="alert_crewPartial_display" style="display: none;">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i> Crew Close 
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
								id="crewpartial_id" style="max-height: 60px;">
								<thead>
									<tr>
														
														<th>Waybill No</th>
<!-- 														<th>Schedule No</th> -->
														<th>Schedule Name</th>
														<th>Schedule Type Name</th>
														<th>Service Name</th>
														<th>Conductor Name</th>
														<th>Driver Name</th>
														
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				
				<div class="portlet box blue" id="alert_crewDeviatedTrip_display" style="display: none;">
					<div class="portlet-title">
						<div class="caption">Crew New
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
								id="Trip_crewDeviatedTrip_id" style="max-height: 60px;">
								<thead>
									<tr>
														
														<th>Waybill No</th>
<!-- 														<th>Schedule No</th> -->
														<th>Schedule Name</th>
														<th>Schedule Type Name</th>
														<th>Service Name</th>
														<th>Conductor Name</th>
														<th>Driver Name</th>
														
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				
				<div class="portlet box blue" id="alert_crewCompletedTrip_display" style="display: none;">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i>Crew Online
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
								id="Trip_crewCompletedTrip_id" style="max-height: 60px;">
								<thead>
									<tr>
														<th>Waybill No</th>
<!-- 														<th>Schedule No</th> -->
														<th>Schedule Name</th>
														<th>Schedule Type Name</th>
														<th>Service Name</th>
														<th>Conductor Name</th>
														<th>Driver Name</th>
														
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
		
<!-- 		end Crew		 -->
				

<!-- schedule trip end -->
<!--  start depot online offline -->
				<div class="portlet box blue" id="alert_Online_Depot_display" style="display: none;">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i> Online_Depot
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
								id="Trip_Online_Depot_id" style="max-height: 60px;">
								<thead>
									<tr>				<th>NO</th>
														<th>Depot Name</th>
														<th>IP Address</th>
														<th>Last Updated Time</th>
														
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<div class="portlet box blue" id="alert_Offline_Depot_display" style="display: none;">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i> Offline_Depot
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
								id="Trip_Offline_Depot_id" style="max-height: 60px;">
								<thead>
									<tr>				<th>NO</th>
														<th>Depot Name</th>
														<th>IP Address</th>
														<th>Last Updated Time</th>
														
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<!--  end depot online offline -->

				<!-- //employee data start
				 -->
				<div class="portlet box blue" id="alert_employee_display" style="display: none;" >
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i> Employee Details
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
								id="employeedata_id" style="max-height: 60px;">
								<thead>
									<tr>
														<th>Depot Name</th>
														<th>EMPLOYEE_DESIGNATION</th>
														<th>Count</th>
														
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<!-- //employee data end -->
				<!-- //VTU data start
				 -->
				<div class="portlet box blue" id="alert_VTU_display" style="display: none;" >
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i> VTU Details
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
								id="VTUdata_id" style="max-height: 60px;">
								<thead>
									<tr>
														<th>Employee Name</th>
														<th>Device Id</th>
														<th>TOKEN</th>
														
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<!-- //VTU data end -->
				
				
				<!-- //ETM data start
				 -->
				<div class="portlet box blue" id="alert_ETM_display" style="display: none;" >
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i> ETM Details
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
								id="ETMdata_id" style="max-height: 60px;">
								<thead>
									<tr>
														<th>Device Name</th>
														
														<th>TOKEN</th>
														
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
<!-- 				//etm fault data -->
				
				<div class="portlet box blue" id="alert_ETMFault_display" style="display: none;" >
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i> ETM Fault Details
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
								id="ETMFaultdata_id" style="max-height: 60px;">
								<thead>
									<tr>
														<th>Device Name</th>
														
														<th>TOKEN</th>
														<th>Depot Name</th>
														
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				
<!-- 				//end -->
				
				<div class="portlet box blue" id="alert_LC_display" >
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i> LC Count
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
								id="LC_count_id" style="max-height: 60px;">
								<thead>
									<tr>
														<th>Depot Name</th>
													
														<th>LC Ticket Count</th>
														
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<!-- //ETM data end -->
				
<!-- 				toatal ticket count -->
<div class="portlet box blue" id="alert_ticket_count_display" >
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i> Total Ticket Count
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
								id="ticket_count_id" style="max-height: 60px;">
								<thead>
									<tr>
														<th> Depot Name</th>
														<!-- <th>Ticket Etim Count</th> -->
														<th>Total Ticket Count</th>
														
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<!-- 				toatal ticket count -->


		<!-- 				toatal pass count -->
<div class="portlet box blue" id="alert_pass_count_display" >
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i> Total Pass Count
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
								id="pass_count_id" style="max-height: 60px;">
								<thead>
									<tr>
														<th> Depot Name</th>
														
														<th>Pass Ticket Count</th>
														
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				

				<!-- 				total pass count -->	
				
				
				<!-- 				toatal Trip count -->
				<div class="portlet box blue" id="alert_pass_count_display" style="display: none;">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i> Trip Count
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
								id="trip_count_id" style="max-height: 60px;">
								<thead>
									<tr>
														<th>Depote Name</th>
														<th>Total Trip</th>
														<th>Cancel Trip</th>
														<th>Completed Trip</th>
														
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<!-- 			end total trip count -->		
				
				<!-- 				toatal Schedule count -->
				<div class="portlet box blue" id="alert_pass_count_display"  style="display: none;">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i> Schedule Count
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
								id="schedule_count_id" style="max-height: 60px;">
								<thead>
									<tr>
														<th> Depot Name</th>
														<th>Total Schedule</th>
														
														
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<!-- 			end total Schedule count -->		
				
				<!-- 				toatal Route count -->
				<div class="portlet box blue" id="alert_pass_count_display"  >
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i> Route Count
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
								id="route_count_id" style="max-height: 60px;">
								<thead>
									<tr>
									
														<!-- <th> Depot Name</th> -->
														<th>Total Route</th>
														
														
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<!-- 			end total Route count -->	
				
				<!-- 				Total Bus Skip  count -->
				<div class="portlet box blue" id="alert_pass_count_display" style="display: none;" >
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i> Bus Skip Count
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
								id="busskip_count_id" style="max-height: 60px;">
								<thead>
									<tr>
									
														<th> Depote Name</th>
														<th>Total Bus Skip</th>
														
														
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<!-- 			end total Bus Skip count -->	
				
			<!-- 				toatal complaint count -->
				<div class="portlet box blue" id="alert_pass_count_display" >
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i> Complaint Count
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
								id="complaint_count_id" style="max-height: 60px;">
								<thead>
									<tr>
														<th> Complaint Type</th>
														
														<th>Complaint  Count</th>
														
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<!-- 			end total complaint count -->	
		
<!-- 			manual ticket data start -->

<div class="portlet box blue" id="alert_pass_count_display" >
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i> Manual Ticket Data
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
								id="manualticketdepotid" style="max-height: 60px;">
								<thead>
									<tr>
														<th>NO.</th>
														<th>Depot Name</th>
														<th>Manual Ticket </th>
														<th>Revenue</th>
														<!-- <th>Normal Ticket Cost</th>
														
														<th>Daily Ticket</th>
														<th>Daily Ticket Count</th>
														<th>Daily Ticket Cost</th>
														
														<th>Monthly Ticket</th>
														<th>Monthly Ticket Count</th>
														<th>Monthly Ticket Cost</th>
														
														<th>Luggage Ticket</th>
														<th>Luggage Ticket Count</th>
														<th>Luggage Ticket Cost</th> -->
														
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			
<!-- 			manual ticket data end -->
			
			
<!-- 				<model start> -->

	<div style="display: none;" id="mymodal123" class="modal fade"
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
								<i class="fa fa-gift"></i>Employee Detail
							</div>
							<!-- <div class="tools">
								<a href="javascript:;" class="collapse"> </a> 
								 <a href="javascript:;" class="reload"> </a> 
							</div> -->
						</div>
									<div class="portlet-body" id="withoutbusstop">
										<table class="table table-striped table-bordered table-hover"
											id="empstatusdetails_id">
											<thead>
												<tr>
												
													<th>Employee Name</th>
													<th>Designation</th>
													<th>Token</th>
													
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
<!-- 				<model end> -->

<!-- //lc model start -->


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
									</div><div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>LC Details
							</div>
							<!-- <div class="tools">
								<a href="javascript:;" class="collapse"> </a> 
								 <a href="javascript:;" class="reload"> </a> 
							</div> -->
						</div>
									<div class="portlet-body" id="withoutbusstop">
										<table class="table table-striped table-bordered table-hover"
											id="lcdetailsdepotwise_id">
											<thead>
												<tr>
												<th>Waybill No</th>
												<th>Schedule No</th>
												<th>Schedule Type</th>
												<th>Duty Type</th>
												<th>ETM No</th>
												<th>Vehicle No</th>
												 <th>Total Amount</th>
												<th>Ticket Count</th>
										
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
<!-- //end -->

<!-- //ticket depot data -->
<div style="display: none;" id="mymodal1235" class="modal fade"
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
								<i class="fa fa-gift"></i>GPRS Ticket Details
							</div>
							<!-- <div class="tools">
								<a href="javascript:;" class="collapse"> </a> 
								 <a href="javascript:;" class="reload"> </a> 
							</div> -->
						</div>
									<div class="portlet-body" id="withoutbusstop">
										<table class="table table-striped table-bordered table-hover"
											id="ticketdetailsdepotwise_id">
											<thead>
												<tr>
												<th>Waybill No</th>
												<th>Schedule No</th>
												<th>Schedule Type</th>
												<th>Duty Type</th>
												<th>ETM No</th>
												<th>Route NO.</th>
												
												 <th>Total Amount</th>
												<th>Ticket Count</th>
												<th>Vehicle No</th>
										
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
<!-- end -->
<!-- ticket pass data start -->
<div style="display: none;" id="mymodal123589" class="modal fade"
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
								<i class="fa fa-gift"></i>Pass Details
							</div>
							<!-- <div class="tools">
								<a href="javascript:;" class="collapse"> </a> 
								 <a href="javascript:;" class="reload"> </a> 
							</div> -->
						</div>
									<div class="portlet-body" id="withoutbusstop">
										<table class="table table-striped table-bordered table-hover"
											id="passdetailsdepotwise_id">
											<thead>
												<tr>
												<th>Waybill No</th>
												<th>Schedule No</th>
												<th>Schedule Type</th>
												<th>Duty Type</th>
												<th>ETM No</th>
												<th>Route NO.</th>
												
												 <th>Total Amount</th>
												<th>Ticket Count</th>
												<th>Vehicle No</th>
										
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
<!-- ticket pass data end -->
<!-- //breakdown data start -->


<div style="display: none;" id="mymodal1236" class="modal fade"
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
								<i class="fa fa-gift"></i>Breakdown Data
							</div>
							<!-- <div class="tools">
								<a href="javascript:;" class="collapse"> </a> 
								 <a href="javascript:;" class="reload"> </a> 
							</div> -->
						</div>
									<div class="portlet-body" id="withoutbusstop">
										<table class="table table-striped table-bordered table-hover"
											id="breakdowndepotwise_id">
											<thead>
												<tr>
														<th>Vehical No</th>
														<th>Device Id</th>
														<th>Call Time</th>
														<th>Driver Name</th>
														<th>Conductor Name</th>
														<th>Route No</th>
														<th>Schedule No</th>
														<th>Depot Name</th>
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
<!-- end -->
<!-- //end breakdown -->

<!-- accident data start -->
<!-- //ticket depot data -->
<div style="display: none;" id="mymodal1237" class="modal fade"
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
								<i class="fa fa-gift"></i>Accident Details
							</div>
							<!-- <div class="tools">
								<a href="javascript:;" class="collapse"> </a> 
								 <a href="javascript:;" class="reload"> </a> 
							</div> -->
						</div>
									<div class="portlet-body" id="withoutbusstop">
										<table class="table table-striped table-bordered table-hover"
											id="accidentdepotwise_id">
											<thead>
												<tr>
														<th>Vehical No</th>
														<th>Device Id</th>
														<th>Call Time</th>
														<th>Driver Name</th>
														<th>Conductor Name</th>
														<th>Route No</th>
														<th>Schedule No</th>
														<th>Depot Name</th>
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
<!-- end -->
<!-- accident data end -->

<!-- //vehicle data -->

<div style="display: none;" id="mymodal1238" class="modal fade"
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
								<i class="fa fa-gift"></i>Vehicle Data
							</div>
							<!-- <div class="tools">
								<a href="javascript:;" class="collapse"> </a> 
								 <a href="javascript:;" class="reload"> </a> 
							</div> -->
						</div>
									<div class="portlet-body" id="withoutbusstop">
										<table class="table table-striped table-bordered table-hover"
											id="vehicledepotwise_id">
											<thead>
												<tr>
														<th>Depot Name</th>
														<th>Vehicle No</th>
														
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
<!-- //vehicle data end -->

<!-- 	//manual ticket data			<model start> -->

	<div style="display: none;" id="mymodal12344" class="modal fade"
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
								<i class="fa fa-gift"></i>Manual Ticket data<s:property value="depotid"/>
							</div>
							<!-- <div class="tools">
								<a href="javascript:;" class="collapse"> </a> 
								 <a href="javascript:;" class="reload"> </a> 
							</div> -->
						</div>
									<div class="portlet-body" id="withoutbusstop">
										<table class="table table-striped table-bordered table-hover"
											id="ticketstatusdetails_id">
											<thead>
												<tr>
												<input type="text" name="1" value="<s:property value="depotid"/>"/>
													<th>Depot Name</th>
													<th>Passanger ticket</th>
													<th>Daily Pass</th>
													<th>Monthly Pass</th>
													<th>luggage Ticket</th>
													
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
										
		<!-- manaula ticket data end -->
			</div>
		</div>
		<!-- page content div end  -->
	</div>
	<!-- page wrapper div -->
	<!-- </div> -->
</form>
<script>
	/* google.maps.event.addDomListener(window, 'load', initialize); */
	$("#mnu_header li").removeClass("classic-menu-dropdown active");
	$("#mnu_ccc").addClass("classic-menu-dropdown active");
	$("#mnu_header li").addClass("classic-menu-dropdown");
	$("#ccc_selected").addClass("selected");
</script>
<script>
	$(document).ready(function() {
		/* var a=57;
		var b=89;
		var c=21;
		//loadActiveVehicles();
		//3d start
		//var chart;
		var data=[{
	        "country": "USA",
	        "visits": a,
	        "color": "#FF0F00"
	    },{
	        "country": "China",
	        "visits": b,
	        "color": "#FF6600"
	    },{
	        "country": "UK",
	        "visits":c,
	        "color": "#F8FF01"
	    },];
		
		
		
		var chart=AmCharts.makeChart("chartdiv", {
    "theme": "none",
    "type": "serial",
	"startDuration": 2,
   /*  "dataProvider": [{
        "country": "USA",
        "visits": 4025,
        "color": "#FF0F00"
    }, {
        "country": "China",
        "visits": 1882,
        "color": "#FF6600"
    }, {
        "country": "Japan",
        "visits": 1809,
        "color": "#FF9E01"
    },{
        "country": "UK",
        "visits": 1122,
        "color": "#F8FF01"
    }, {
        "country": "India",
        "visits": 984,
        "color": "#04D215"
    },  {
        "country": "Netherlands",
        "visits": 665,
        "color": "#0D52D1"
    },  {
        "country": "South Korea",
        "visits": 443,
        "color": "#8A0CCF"
    }, {
        "country": "Canada",
        "visits": 441,
        "color": "#CD0D74"
    }], */
    
//     "dataProvider": data,
//     "valueAxes": [{
//         "position": "left",
//         "title": "Visitors"
//     }],
//     "graphs": [{
//         "balloonText": "[[category]]: <b>[[value]]</b>",
//         "colorField": "color",
//         "fillAlphas": 0.85,
//         "lineAlpha": 0.1,
//         "type": "column",
//         "topRadius":1,
//         "valueField": "visits"
//     }],
//     "depth3D": 40,
// 	"angle": 30,
//     "chartCursor": {
//         "categoryBalloonEnabled": false,
//         "cursorAlpha": 0,
//         "zoomable": false
//     },    
//     "categoryField": "country",
//     "categoryAxis": {
//         "gridPosition": "start",
//         "labelRotation": 90
//     },
// 	"exportConfig":{
// 		"menuTop":"20px",
//         "menuRight":"20px",
//         "menuItems": [{
//         "icon": '/lib/3/images/export.png',
//         "format": 'png'	  
//         }]  
//     }
  
    
// });
		
// 	chart.addListener("clickGraphItem", handleClick)
// 	function handleClick(event)
// 	{
//     alert(event.item.category + ": " + event.item.values.value);
//     //start
    
//     var table = $('#employeedata_id');
				
// 				console.log(obj.series.status);
				
// 					$("#alert_employee_display").attr("style", "display:''");
					
// 					var table = $('#employeedata_id');
// 					var oTable = table.dataTable({
// 						"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
// 						// per
// 						// page
// 						// values
// 						// here
// 						],
// 						// set the initial value
// 						"iDisplayLength" : 5,
// 						"sAjaxSource" : "getEmployeestatuswisedata.action?employeestatus="
// 							+ obj.series.status,
// 						"sPaginationType" : "bootstrap",
// 						"bDestroy" : true,

// 						"oLanguage" : {
// 							"sLengthMenu" : "_MENU_ records",
// 							"oPaginate" : {
// 								"sPrevious" : "Prev",
// 								"sNext" : "Next"
// 							}
// 						},
// 						"aoColumnDefs" : [ {
// 							'bSortable' : false,
// 							'aTargets' : [ 0 ]
// 						} ]
// 					});
// 					jQuery('#employeedata_id_wrapper .dataTables_filter input').addClass(
// 							"form-control input-medium input-inline"); // modify table
// 					// search input
// 					jQuery('#employeedata_id_wrapper .dataTables_length select').addClass(
// 							"form-control input-xsmall input-inline");
//     //end
    
// 	} 
		

		//3d end
		
		//getAlertIntervalData();
		getVehicleData();
		
		<%if(accident.equalsIgnoreCase("Y")|| accident.equalsIgnoreCase("y")){ %>
		
		getAccidentData();
		<%}%>
		<%if(breakdown.equalsIgnoreCase("Y")){ %>
		//getVehicleBreakdownData();
		<%}%>
		<%if(employee.equalsIgnoreCase("Y")){ %>
		getEmployeeData();
		<%}%>
		<%if(vtu.equalsIgnoreCase("Y")){ %>
		//getVTUData();
		<%}%>
		<%if(etm.equalsIgnoreCase("Y")){ %>
		getETMData();
		<%}%>
		getLCCount();
		getTotalTicketCount();
		getTotalPassCount();
		getComplaintCount();
		getManualTicketData();
		// setInterval(function () {
			// getScheduleCount();
		  //  }, 10000); 
		//getScheduleCount();
		//getTripCount();
	
   // setInterval(function () {
    	//getRouteCount();
  //  }, 10000); 
		getRouteCount();
		<%if(tripcount.equalsIgnoreCase("Y")) {%>
// 		 setInterval(function () { 
		getTotalTripCountData();
// 		  }, 10000);  
		<%}%>
		
		
		<%if(schedule.equalsIgnoreCase("Y")) {%>
		getTotalScheduleTripCountData();
		<%}%>
		//getBusSkipCount();
		<%if(crewdata.equalsIgnoreCase("Y")) {%>
		getTotalCrewAllocationCountData();
		<%}%>
		<%if(depothealth.equalsIgnoreCase("Y")) {%>
		getTotalOnlineOfflineDepotCountData();
		<%}%>
	});
</script>
</html>