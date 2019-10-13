
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
	 
	 document.getElementById("vtsAlertReportId").style.visibility='visible';  
//	 console.log(vtsAlertReportDivId);
   document.getElementById("header").style.display='block';
   document.getElementById("header").style.visibility='visible';     
   var divElements = document.getElementById("header").innerHTML;
   divElements+= "<table>"+document.getElementById("vtsAlertReportId").innerHTML+"</table>";
   
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



//Code For Alert Drill Down..

function getVTSReport() {
	//$('#reportdevprint').show();
	$('#reportdevicetableid').show();
	var selectedDate = document.getElementById("selectedDate").value;
	var stddate=Date.parse(selectedDate);
    var date = selectedDate.substring(0, 2);
    var month = selectedDate.substring(3, 5);
    var year = selectedDate.substring(6, 10);
    var today = new Date();
    if (stddate > today) {
        alert("Entered date is greater than today's date ");
        return false;
    }
	if (selectedDate == "") {
		bootbox.alert("Date Cannot Be Blank");
		return false;
	}
	$('#reportdevice').dataTable({
		"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
		// per
		// page
		// values
		// here
		],
		// set the initial value
		"iDisplayLength" : 5,
		"sAjaxSource" : "getVtuReport?selectedDate=" + selectedDate,
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

	jQuery('#reportdevice_wrapper .dataTables_filter input').addClass(
			"form-control input-xsmall input-inline"); // modify table
	// search input
	jQuery('#reportdevice_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");

}

function getvehicleData(depotId) {
	$('#tableid').show();
	//var vehicle1 = document.getElementById("vehiclewisedistance").value;
	
	var selectedDate = document.getElementById("selectdate").value;
	
	//var depot_id = document.getElementById("divisionlistid1").value;
	$('#vehiclewisedistance').dataTable(
			{
				"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
				// per
				// page
				// values
				// here
				],
				// set the initial value
				"iDisplayLength" : 5,
				"sAjaxSource" : "getVehicleDataReports.action?selectedDate="
						+ selectedDate + "&depot_id=" + depotId,
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
	jQuery('#vehiclewisedistance_wrapper .dataTables_filter input').addClass(
			"form-control input-small input-inline"); // modify
	// table
	jQuery('#vehiclewisedistance_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline"); // modify
	// table

}

function getAlertDetails(alertID) {
	alertID=2;
	var alertDesc = alertID.replace("%20"," ");
	$("#vstatus").html("<b>Alert Data For " + " (" + alertDesc + ")</b>");
	if (alertDesc == "Deviated") {
		table = $('#vehicle_dashBoard_deviation_data');
		// $("#tbl_cnt").html("Distance(Meters)");
		/*$("#alert_dashBoard_data").removeClass("collapse");
		$("#alert_dashBoard_data").addClass("expand");
		$("#alertDev").attr("style", "display:''");
		$("#alert1").attr("style", "display:none");
		$("#alert2").attr("style", "display:none");
		$("#alert3").attr("style", "display:none");
		$("#alert4").attr("style", "display:none");
		$("#alert5").attr("style", "display:none");
		$("#vehicle").attr("style", "display:none");
		$("#overSpeeding").attr("style", "display:none");
		$("#vehicleDetailsTampering").attr("style", "display:none");
		$("#alerttampering").attr("style", "display:none");*/
		
		table.dataTable({
			"aLengthMenu" : [ [ 5, 15, 20, -1 ],
					[ 5, 15, 20, "All" ] // change
			// per
			// page
			// values
			// here
			],
			// set the initial value
			"iDisplayLength" : 5,
			"sAjaxSource" : "alertPerticular.action?alertID="
					+ alertDesc,
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
		jQuery(
				'#vehicle_dashBoard_deviation_data_wrapper .dataTables_filter input')
				.addClass("form-control input-small input-inline"); // modify
		// table
		jQuery(
				'#vehicle_dashBoard_deviation_data_wrapper .dataTables_length select')
				.addClass("form-control input-xsmall input-inline"); // modify
		// table
		/*$("#vehicle_chart_alert").removeClass("collapse");
		$("#vehicle_chart_alert").addClass("expand");
		$("#alert_vehicle_display_chart").attr("style",
				"display:none");*/

	}
	// Box Tampering Alert
	if (alertDesc == "Tampering") {
		table = $('#vehicle_dashBoard_tampering_data');
		// $("#tbl_cnt").html("Distance(Meters)");
		$("#alerttampering").attr("style", "display:''");
		$("#alertDev").attr("style", "display:none");
		$("#alert1").attr("style", "display:none");
		$("#alert2").attr("style", "display:none");
		$("#alert3").attr("style", "display:none");
		$("#alert4").attr("style", "display:none");
		$("#alert5").attr("style", "display:none");
		$("#vehicle").attr("style", "display:none");
		$("#overSpeeding").attr("style", "display:none");

		table.dataTable({
			"aLengthMenu" : [ [ 5, 15, 20, -1 ],
					[ 5, 15, 20, "All" ] // change
			// per
			// page
			// values
			// here
			],
			// set the initial value
			"iDisplayLength" : 5,
			"sAjaxSource" : "alertPerticular.action?alertID="
					+ alertDesc,
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
		jQuery(
				'#vehicle_dashBoard_tampering_data_wrapper .dataTables_filter input')
				.addClass("form-control input-small input-inline"); // modify
		// table
		jQuery(
				'#vehicle_dashBoard_tampering_data_wrapper .dataTables_length select')
				.addClass("form-control input-xsmall input-inline"); // modify
		// table
		/*$("#vehicle_chart_alert").removeClass("collapse");
		$("#vehicle_chart_alert").addClass("expand");
		$("#alert_vehicle_display_chart").attr("style",
				"display:none");*/

	}

	// Early Arrival
	if (alertDesc == "Early Arrival") {
		document.getElementById("withoutbusstop").style.display = 'none';
		divId = document.getElementById("alert1");
		divId.style.display = '';
		table1 = $('#vehicle_earlyArrival_data');
		alertId = "Early Arrival";
		$("#alert").attr("style", "display:none");
		$("#alert2").attr("style", "display:none");
		$("#alert3").attr("style", "display:none");
		$("#alert4").attr("style", "display:none");
		$("#alert5").attr("style", "display:none");
		$("#vehicle").attr("style", "display:none");
		$("#alertDev").attr("style", "display:none");
		$("#overSpeeding").attr("style", "display:none");
		$("#alerttampering").attr("style", "display:none");
		// $("#alert_vehicle_display_chart").attr("style",
		// "display:none");
		// alert("table id is..."+table1);
		var oTable1 = table1.dataTable({
			"aLengthMenu" : [ [ 5, 15, 20, -1 ],
					[ 5, 15, 20, "All" ] // change
			// per
			// page
			// values
			// here
			],
			// set the initial value
			"iDisplayLength" : 5,
			"sAjaxSource" : "getDepotArrivalTime.action?alertID="
					+ alertId + "&depotID=",
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
		jQuery(
				'#vehicle_earlyArrival_data_wrapper .dataTables_filter input')
				.addClass("form-control input-small input-inline"); // modify
		// table
		jQuery(
				'#vehicle_earlyArrival_data_wrapper .dataTables_length select')
				.addClass("form-control input-xsmall input-inline"); // modify
		/*
		 * $('#vehicle_earlyArrival_data').dataTable()
		 * .fnClearTable();
		 * $('#vehicle_earlyArrival_data').dataTable().fnDestroy();
		 * $("#vehicle_chart_alert").removeClass("collapse");
		 * $("#vehicle_chart_alert").addClass("expand");
		 * $("#alert_vehicle_display_chart").attr("style",
		 * "display:none");
		 */
	}

	// Late Departure
	if (alertDesc == "Late Dept") {
		divId = document.getElementById("alert2");
		divId.style.display = '';
		table2 = $('#vehicle_lateDeparture_data');
		alertId = "Late Dept";
		$("#alert").attr("style", "display:none");
		$("#alert1").attr("style", "display:none");
		$("#alert3").attr("style", "display:none");
		$("#alert4").attr("style", "display:none");
		$("#alert5").attr("style", "display:none");
		$("#vehicle").attr("style", "display:none");
		$("#alertDev").attr("style", "display:none");
		$("#overSpeeding").attr("style", "display:none");
		$("#alerttampering").attr("style", "display:none");
		var oTable2 = table2.dataTable({
			"aLengthMenu" : [ [ 5, 15, 20, -1 ],
					[ 5, 15, 20, "All" ] // change
			// per
			// page
			// values
			// here
			],
			// set the initial value
			"iDisplayLength" : 5,
			"sAjaxSource" : "getDepotDepartureTime.action?alertID="
					+ alertId + "&depotID=",
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
		jQuery(
				'#vehicle_lateDeparture_data_wrapper .dataTables_filter input')
				.addClass("form-control input-small input-inline"); // modify
		// table
		jQuery(
				'#vehicle_lateDeparture_data_wrapper .dataTables_length select')
				.addClass("form-control input-xsmall input-inline"); // modify
		// table
		jQuery(
				'#vehicle_lateDeparture_data_wrapper .dataTables_length select')
				.addClass("form-control input-xsmall input-inline"); // modify

		$("#vehicle_chart_alert").removeClass("collapse");
		$("#vehicle_chart_alert").addClass("expand");
		$("#alert_vehicle_display_chart").attr("style",
				"display:none");

	}

	// Skip Stop
	if (alertDesc == "Skip Stop") {
		divId = document.getElementById("alert4");
		divId.style.display = '';
		table4 = $('#skipStopAlert_data');
		alertId = "Skip Stop";
		$("#alert").attr("style", "display:none");
		$("#alert1").attr("style", "display:none");
		$("#alert2").attr("style", "display:none");
		$("#alert3").attr("style", "display:none");
		$("#alert5").attr("style", "display:none");
		$("#vehicle").attr("style", "display:none");
		$("#alertDev").attr("style", "display:none");
		$("#overSpeeding").attr("style", "display:none");
		$("#alerttampering").attr("style", "display:none");
		$("#tamperingdetails").attr("style", "display:none");
		var oTable4 = table4.dataTable({

			"aLengthMenu" : [ [ 5, 15, 20, -1 ],
					[ 5, 15, 20, "All" ] // change
			// per
			// page
			// values
			// here
			],
			// set the initial value
			"iDisplayLength" : 5,
			"sAjaxSource" : "getSkippedStop.action?alertID="
					+ alertId,
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

		jQuery(
				'#skipStopAlert_data_wrapper .dataTables_filter input')
				.addClass("form-control input-small input-inline"); // modify
		// table
		jQuery(
				'#skipStopAlert_data_wrapper .dataTables_length select')
				.addClass("form-control input-xsmall input-inline"); // modify
/*
		$("#vehicle_chart_alert").removeClass("collapse");
		$("#vehicle_chart_alert").addClass("expand");
		$("#alert_vehicle_display_chart").attr("style",
				"display:none");*/

	}

	// SOS
	if (alertDesc == "SoS") {
		divId = document.getElementById("alert3");
		table3 = $('#sosAlert_data');
		divId.style.display = '';
		alertId = "SoS";
		$("#alert").attr("style", "display:none");
		$("#alert1").attr("style", "display:none");
		$("#alert2").attr("style", "display:none");
		$("#alert4").attr("style", "display:none");
		$("#alert5").attr("style", "display:none");
		$("#vehicle").attr("style", "display:none");
		$("#alertDev").attr("style", "display:none");
		$("#overSpeeding").attr("style", "display:none");
		$("#alerttampering").attr("style", "display:none");
		var oTable3 = table3.dataTable({
			"aLengthMenu" : [ [ 5, 15, 20, -1 ],
					[ 5, 15, 20, "All" ] // change
			// per
			// page
			// values
			// here
			],
			// set the initial value
			"iDisplayLength" : 5,
			"sAjaxSource" : "alertSosData.action?" + alertId,
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
		jQuery('#sosAlert_data_wrapper .dataTables_filter input')
				.addClass("form-control input-small input-inline"); // modify
		// table
		jQuery('#sosAlert_data_wrapper .dataTables_length select')
				.addClass("form-control input-xsmall input-inline"); // modify

		/*$("#vehicle_chart_alert").removeClass("collapse");
		$("#vehicle_chart_alert").addClass("expand");
		$("#alert_vehicle_display_chart").attr("style",
				"display:none");*/

	}

	if (alertDesc == "Over Speed") {
		divId = document.getElementById("overSpeeding");
		divId.style.display = '';
		table4 = $('#overSpeeding_data');
		alertId = "Over Speed";
		$("#alert").attr("style", "display:none");
		$("#alert1").attr("style", "display:none");
		$("#alert2").attr("style", "display:none");
		$("#alert3").attr("style", "display:none");
		$("#alert4").attr("style", "display:none");
		$("#alert5").attr("style", "display:none");
		$("#vehicle").attr("style", "display:none");
		$("#alertDev").attr("style", "display:none");
		$("#alerttampering").attr("style", "display:none");
		var oTable4 = table4.dataTable({
			"aLengthMenu" : [ [ 5, 15, 20, -1 ],
					[ 5, 15, 20, "All" ] // change
			// per
			// page
			// values
			// here
			],
			// set the initial value
			"iDisplayLength" : 5,
			"sAjaxSource" : "getOverSpeed.action?alertID="
					+ alertId,
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
		jQuery(
				'#overSpeeding_data_wrapper .dataTables_filter input')
				.addClass("form-control input-small input-inline"); // modify
		// table
		jQuery(
				'#overSpeeding_data_wrapper .dataTables_length select')
				.addClass("form-control input-xsmall input-inline"); // modify

		/*$("#vehicle_chart_alert").removeClass("collapse");
		$("#vehicle_chart_alert").addClass("expand");
		$("#alert_vehicle_display_chart").attr("style",
				"display:none");*/

	}
}
function getVehicleData(packet_code, misc_byte, deivice_id, licence_number) {
	if (packet_code == 'GF' && misc_byte == '01') {
		document.getElementById("withoutbusstop").style.display = 'none';
		document.getElementById("withbusstop").style.display = '';
		$('#vehicleDetails1234').dataTable(
				{
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					"iDisplayLength" : 5,
					"sAjaxSource" : "vehicleAlertDetails.action?packet_code="
							+ packet_code + "&misc_byte=" + misc_byte
							+ "&deivice_id=" + deivice_id + "&licence_number="
							+ licence_number,
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

		jQuery('#vehicleDetails1234_wrapper .dataTables_filter input')
				.addClass("form-control input-medium input-inline"); // modify
		// table
		// search input
		jQuery('#vehicleDetails1234_wrapper .dataTables_length select')
				.addClass("form-control input-xsmall input-inline");
	} else {

		document.getElementById("withbusstop").style.display = 'none';
		document.getElementById("withoutbusstop").style.display = '';
		$('#vehicleDetails123').dataTable(
				{
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					"iDisplayLength" : 5,
					"sAjaxSource" : "vehicleAlertDetails.action?packet_code="
							+ packet_code + "&misc_byte=" + misc_byte
							+ "&deivice_id=" + deivice_id + "&licence_number="
							+ licence_number,
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
		jQuery('#vehicleDetails123_wrapper .dataTables_filter input').addClass(
				"form-control input-medium input-inline"); // modify table
		// search input
		jQuery('#vehicleDetails123_wrapper .dataTables_length select')
				.addClass("form-control input-xsmall input-inline");
	}
	// getting VTU Report..

}

function viewTripWithBusstopname(id) {
	
	
		$('#tripReportDetails123').dataTable(
				{
					
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
	 				// here
					],
					// set the initial value
					"iDisplayLength" : 20,
					"sAjaxSource" : "tripReportDetails123.action?id="+id,
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
						'aTargets' : [ 0,1 ]
					} ],
					aaSorting:[],
					"bLengthChange" : false,
					"sDom" : '<"top">rt<"bottom"flp><"clear">'
				}).wrap(
				"<div style='position:auto;overflow:auto;height:300px;width:100%'/>");

		jQuery('#tripReportDetails_wrapper .dataTables_filter input')
				.addClass("form-control input-medium input-inline"); // modify
		// table
		// search input
		jQuery('#tripReportDetails_wrapper .dataTables_length select')
				.addClass("form-control input-xsmall input-inline");

	// getting VTU Report..

}
function getAlertReportOnSubmit()
{
	var givenDate = $("#divisionlistid1").val();
	
	if (givenDate != 00) {
		var givendate = $("#seldate").val();
		var alertId =20;
		//var depotID = $("#divisionlistid1").val();
		var table;
		if(alertId==20||alertId==21){	
			var table;
			 $("#accidentAlertDivId").hide();
			 $("#vtsAlertReportDivId").hide();
			 $("#vtsAlertReportDivId").show();
		     table = $('#vtsAlertReportId');
		     var oTable = table.dataTable({
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					//"iDisplayLength" : 5,
					 "bFilter" : false,
					 "bSelect" : false,
					 "bPaginate":false,
					"sAjaxSource" : "getTripCancelReport.action?givendate=" + givendate,
					"sPaginationType" : "bootstrap",
					"bDestroy" : true,

					"oLanguage" : {
						"sLengthMenu" : "_MENU_ records",
// 						"oPaginate" : {
// 							"sPrevious" : "Prev",
// 							"sNext" : "Next"
// 						}
					},
					"aoColumnDefs" : [ {
						'bSortable' : false,
						'aTargets' : [ 0 ]
					} ]
				});
				jQuery('#vtsAlertReportId_wrapper .dataTables_filter input').addClass(
						"form-control input-small input-inline"); // modify
				// table
				jQuery('#vtsAlertReportId_wrapper .dataTables_length select').addClass(
						"form-control input-xsmall input-inline"); // modify
		}else{
		if(alertId==22||alertId==23){
			 $("#vtsAlertReportDivId").hide();
			 $("#scheduleReport").hide();
			 $("#accidentAlertDivId").show();			
		table = $('#accidentAlertid');
		}else{
			 $("#accidentAlertDivId").hide();
			 $("#scheduleReport").hide();
			 $("#vtsAlertReportDivId").show();
			 table = $('#vtsAlertReportId');
		}
		var oTable = table.dataTable({
			"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
			// per
			// page
			// values
			// here
			],
			// set the initial value
			//"iDisplayLength" : 5,
			 "bFilter" : false,
			 "bSelect" : false,
			 "bPaginate":false,
			"sAjaxSource" : "getAlertReport.action?alertID=" + 2
					+ "&depotID=" + depotID + "&givendate=" + givendate,
			"sPaginationType" : "bootstrap",
			"bDestroy" : true,

			"oLanguage" : {
				"sLengthMenu" : "_MENU_ records",
// 				"oPaginate" : {
// 					"sPrevious" : "Prev",
// 					"sNext" : "Next"
// 				}
			},
			"aoColumnDefs" : [ {
				'bSortable' : false,
				'aTargets' : [ 0 ]
			} ]
		});
		jQuery('#vtsAlertReportId_wrapper .dataTables_filter input').addClass(
				"form-control input-small input-inline"); // modify
		// table
		jQuery('#vtsAlertReportId_wrapper .dataTables_length select').addClass(
				"form-control input-xsmall input-inline"); // modify
		
		jQuery('#accidentAlertid_wrapper .dataTables_filter input').addClass(
		"form-control input-small input-inline"); // modify
// table
        jQuery('#accidentAlertid_wrapper .dataTables_length select').addClass(
		"form-control input-xsmall input-inline");


	}
	}
}
function getAlertReport(givenDate) {
 
	if (givenDate != 00) {
		var givendate = $("#seldate").val();
		var alertId = 2;
		var depotID = $("#divisionlistid1").val();
		if(alertId==20||alertId==21){	
			var table;
			 $("#accidentAlertDivId").hide();
			 $("#vtsAlertReportDivId").hide();
			 $("#vtsAlertReportDivId").show();
		     table = $('#schAlertid');
		     var oTable = table.dataTable({
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					"iDisplayLength" : 5,
					"sAjaxSource" : "getAlertReport.action?alertID=" + 2
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
					"oTableTools": {
		                "sSwfPath": "<c:url value='../../swf/copy_csv_xls_pdf.swf'/>",
		                "aButtons": [
		                "csv",
		                {
		                    "sExtends": "pdf",
		                    "sPdfOrientation": "landscape"
		                },
		                { "sExtends": "print",
		                "sMessage": "<br/>"
		                    + " <input id='returnButton' type='button' value='Return to Group Requests' onclick='TableTools.fnGetInstance(\"example\")._fnPrintEnd({keyCode:27, preventDefault:function(){}})' />"}
		            ]
		            },
					"aoColumnDefs" : [ {
						'bSortable' : false,
						'aTargets' : [ 0 ]
					} ]
				});
				jQuery('#schAlertid_wrapper .dataTables_filter input').addClass(
						"form-control input-small input-inline"); // modify
				// table
				jQuery('#schAlertid_wrapper .dataTables_length select').addClass(
						"form-control input-xsmall input-inline"); // modify
		}else{
		var table;
		if(alertId==22||alertId==23){			
			 $("#accidentAlertDivId").show();
			 $("#vtsAlertReportDivId").hide();
			 $("#scheduleReport").hide();
		     table = $('#accidentAlertid');
		}else{
			 $("#accidentAlertDivId").hide();
			 $("#scheduleReport").hide();
			 $("#vtsAlertReportDivId").show();
			 table = $('#vtsAlertReportId');
		}
		
		var oTable = table.dataTable({
			"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
			// per
			// page
			// values
			// here
			],
			// set the initial value
			"iDisplayLength" : 5,
			"sAjaxSource" : "getAlertReport.action?alertID=" + 2
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
			"aoColumnDefs" : [ {
				'bSortable' : false,
				'aTargets' : [ 0 ]
			} ]
		});
		jQuery('#vtsAlertReportId_wrapper .dataTables_filter input').addClass(
				"form-control input-small input-inline"); // modify
		// table
		jQuery('#vtsAlertReportId_wrapper .dataTables_length select').addClass(
				"form-control input-xsmall input-inline"); // modify
		
		jQuery('#accidentAlertid_wrapper .dataTables_filter input').addClass(
		"form-control input-small input-inline"); // modify
// table
        jQuery('#accidentAlertid_wrapper .dataTables_length select').addClass(
		"form-control input-xsmall input-inline");

	}
	}

}


function getHADataPerticular(deviceID,ist_date){
	$("#withoutbusstop").attr("style", "display:''");
	$('#hadetails').dataTable(
			{
				"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
				// per
				// page
				// values
				// here
				],
				// set the initial value
				"iDisplayLength" : 5,
				"sAjaxSource" : "vehicleAlertAccDetails!vehicleAlertAccDetailsReport?device_id="
						+ deviceID + "&ist_date=" + ist_date,
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
	jQuery('#hadetails_wrapper .dataTables_filter input').addClass(
			"form-control input-medium input-inline"); // modify table
	// search input
	jQuery('#hadetails_wrapper .dataTables_length select')
			.addClass("form-control input-xsmall input-inline");


}


</script>
<style>
</style>
</head>
<%-- <script src="assets/vts/js/vehiclealert.js" type="text/javascript"></script> --%>
<body onload="getHide()">
<div id="header" class="portlet-body" style="display: none; visibility: hidden;">
										<h4 style="margin-left:350px;">Trip Cancel Report</h4>
									<br />
									</div>
	<div class="page-content-wrapper">
		<div class="page-content">
			<div class="row">
					<div class="col-md-12">
						<h3 class="page-title">
	 					TRIP CANCEL REPORT <small></small>
						</h3>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="portlet box grey-cascade">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-globe"></i>Trip Cancel  Report
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
											
									
      <script>	                
    function tabletoExcel(btnExport){     
  	var htmltable= document.getElementById('btnExport');   
    $( "#header-fixed" ).remove();
    var inputHTML = "<table border='1'>";
    inputHTML += "<tr>";
    inputHTML += "<th  align='left'colspan='9'>Bangalore Metropolitan Transport Corporation</th>";
    inputHTML += "</tr>";
    inputHTML += "<th  align='left'colspan='9'>TRIP CANCEL REPORT</th>";
    inputHTML += "</tr>";
    inputHTML += "<tr>";
    inputHTML += "<th colspan='2' align='left'>Date Range:</th>";
    inputHTML += "<th colspan='7' align='left'>" + $("#startdate").val() + " to " + $("#endate").val() + "</th>";
    inputHTML += "</tr>";
    inputHTML += "</table>";
    var htmltable = document.getElementById("vtsAlertReportId");
    var html = inputHTML + htmltable.outerHTML;
    var downloadLink = document.createElement("a");
    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
    downloadLink.download = "TRIP CANCEL REPORT.xls";
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
}</script>   
     				
										<div class="form-group">
											<div class="col-md-3">
										<!-- <div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9"> -->
											<button type="button" class="btn default" onclick="getAlertReportOnSubmit()">Submit</button>
										
									</div> 
									<div class="col-md-1" id="printbutton">
											<input type='button' class="btn default" id='button1' onclick='printDiv()' value='Print' />													
										</div>
										<div>
								<button type="button" class="btn default" id="btnExport" filename= "report" onclick="tabletoExcel();"> EXPORT </button>
                                </div>
										
										
									</div>
										</div>
									</div>
							 </div>
			 				<br/><br/>
							<br/><br/>
							<div class="portlet-body" id="vtsAlertReportDivId" style="display: none">
							<table class="table table-striped table-bordered table-hover"
								id="vtsAlertReportId">

								<thead>
									<tr>
										<th style="width1: 8px;">Sr.No.</th>
				 						<th>Schedule No.</th>
										<th>Trip No.</th>
										<th>Route No.</th>
										<th>Vehicle No.</th>
									</tr>
								</thead>

							</table>
						</div>
						<div class="portlet-body" id="accidentAlertDivId" style="display: none">
										<table class="table table-striped table-bordered table-hover"
											id="accidentAlertid">
											<thead>
												<tr>
													<th>Sr.No.</th>
													<th>Vehicle No.</th>
													<th>Device No.</th>
													<th>Call Time</th>
													<th>Route No.</th>
													<th>Driver Name</th>
													<th>Conductor Name</th>
													<th>Schedule No.</th>
													
												</tr>
											</thead>
										</table>

									</div>
									<div class="portlet-body" id="scheduleReport" style="display: none">
										<table class="table table-striped table-bordered table-hover"
											id="schAlertid">
											<thead>
												<tr>
													<th>Sr.No.</th>
													<th>Vehicle No.</th>
													<th>Device Id</th>
													<th>Sch. Name</th>
													<th>Start Time</th>
													<th>Act. Time</th>
													<th>Time Diff</th>
													<th>Start Stop Name</th>
													<th>End Stop Name</th>
													
												</tr>
											</thead>
										</table>

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
											id="hadetails">
											<thead>
												<tr>
													<th>Sr.No.</th>
													<th>Vehicle No.</th>
													<th>Time</th>

												</tr>
											</thead>
										</table>
									</div>
									<div class="portlet-body" id="devdetails" style="display: none">
										<table class="table table-striped table-bordered table-hover"
											id="vehicleDetailsDev">
											<thead>
												<tr>
													<th>Sr.No.</th>
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
													<th>Sr.No.</th>
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
													<th>Sr.No.</th>
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
													<th>Sr.No.</th>
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
