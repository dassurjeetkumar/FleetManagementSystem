//Code For Alert Drill Down..

function getVTSReport() {
	// $('#reportdevprint').show();
	$('#reportdevicetableid').show();
	var depot_id=$('#depotlist').val();
	 var div_id=document.getElementById('divisionlist').value;
//		alert("depot_id==="+depot_id+"div_id"+div_id);

	var selectedDate = document.getElementById("selectedDate").value;
	var stddate = Date.parse(selectedDate);
	var date = selectedDate.substring(0, 2);
	var month = selectedDate.substring(3, 5);
	var year = selectedDate.substring(6, 10);
	var today = new Date();
	/*if (new Date(stddate) > today) {
		alert("Entered date is greater than today's date ");
		return false;
	}*/
	if (selectedDate == "") {
		bootbox.alert("Date Cannot Be Blank");
		return false;
	}
	$('#reportdevice').dataTable({
		"aLengthMenu" : [ [ 10, 20, 30, -1 ], [ 10, 20, 30, "All" ] // change
		// per
		// page
		// values
		// here
		],
		// set the initial value
		"iDisplayLength" : 10,
		"sAjaxSource" : "getVtuReport?selectedDate=" + selectedDate +"&depot_id=" +depot_id+"&divId="+div_id,
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

function getvehicleData(depotid) {
	var depotNo=$("#depotlistid").val();
	var divisonNo=$("#divisionlistid").val();

	var startdate = document.getElementById("startdate").value;
	var enddate = document.getElementById("endate").value;
	var vehNo = document.getElementById("vehiclelist").value;
//	alert("st"+startdate+"End"+enddate);
	var validateFlag=validateDistanceReportFields(depotNo,divisonNo,startdate,enddate);
	if(validateFlag ){
	$('#tableid').show();
	 var depotId = document.getElementById("depotlistid").value;

	//var selectedDate = document.getElementById("selectdate").value;

	// var depot_id = document.getElementById("divisionlistid1").value;
	$('#vehiclewisedistance').dataTable(
			{
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
				"bPaginate" : false,
				"sAjaxSource" : "getVehicleDataReports.action?startdate="
				+ startdate + "&enddate=" + enddate + "&depot_id=" + depotId+ "&vehicleNo="+vehNo,
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
				aaSorting:[],
				"bLengthChange" : false,
				"sDom" : '<"top">rt<"bottom"flp><"clear">'
			});
	jQuery('#vehiclewisedistance_wrapper .dataTables_filter input').addClass(
			"form-control input-small input-inline"); // modify
	// table
	jQuery('#vehiclewisedistance_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline"); // modify
	// table
	}
}
function validateDistanceReportFields(depotNo,divisonNo,selDate)
{
	 if(divisonNo==0)
	 {
		alert("Please Select Divison");
		return false;
	 }
	 if(depotNo==0)
	 {
		alert("Please Select Depot");
		return false;
	 }
//	 if(startdate==0)
//     {
//	   alert("Please Select Date");
//	   return false;
//     }
//	 if(enddate==0)
//     {
//	   alert("Please Select Date");
//	   return false;
//     }
     

 return true;
}

function getAlertDetails(alertID) {
	var alertDesc="";
	if(alertID.match("%20")){
		alertDesc = alertID.replace("%20", " ");
	}else{
		alertDesc=alertID;
	}
	$("#vstatus").html("<b>Alert Data For " + " (" + alertDesc + ")</b>");
	if (alertDesc == "Deviated") {
		table = $('#vehicle_dashBoard_deviation_data');
		$("#alertDev").attr("style", "display:''");
		$("#alerttampering").attr("style", "display:none");
		$("#alert1").attr("style", "display:none");
		$("#alert2").attr("style", "display:none");
		$("#alert3").attr("style", "display:none");
		$("#alert4").attr("style", "display:none");
		$("#alert5").attr("style", "display:none");
		$("#vehicle").attr("style", "display:none");
		$("#overSpeeding").attr("style", "display:none");
		

		table.dataTable({
			"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
			// per
			// page
			// values
			// here
			],
			// set the initial value
			"iDisplayLength" : 5,
			"sAjaxSource" : "alertPerticular.action?alertID=" + alertDesc,
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
		/*
		 * $("#vehicle_chart_alert").removeClass("collapse");
		 * $("#vehicle_chart_alert").addClass("expand");
		 * $("#alert_vehicle_display_chart").attr("style", "display:none");
		 */

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
			"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
			// per
			// page
			// values
			// here
			],
			// set the initial value
			"iDisplayLength" : 5,
			"sAjaxSource" : "alertPerticular.action?alertID=" + alertDesc,
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
		/*
		 * $("#vehicle_chart_alert").removeClass("collapse");
		 * $("#vehicle_chart_alert").addClass("expand");
		 * $("#alert_vehicle_display_chart").attr("style", "display:none");
		 */

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
			"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
			// per
			// page
			// values
			// here
			],
			// set the initial value
			"iDisplayLength" : 5,
			"sAjaxSource" : "getDepotArrivalTime.action?alertID=" + alertId
					+ "&depotID=",
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
		jQuery('#vehicle_earlyArrival_data_wrapper .dataTables_filter input')
				.addClass("form-control input-small input-inline"); // modify
		// table
		jQuery('#vehicle_earlyArrival_data_wrapper .dataTables_length select')
				.addClass("form-control input-xsmall input-inline"); // modify
		/*
		 * $('#vehicle_earlyArrival_data').dataTable() .fnClearTable();
		 * $('#vehicle_earlyArrival_data').dataTable().fnDestroy();
		 * $("#vehicle_chart_alert").removeClass("collapse");
		 * $("#vehicle_chart_alert").addClass("expand");
		 * $("#alert_vehicle_display_chart").attr("style", "display:none");
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
			"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
			// per
			// page
			// values
			// here
			],
			// set the initial value
			"iDisplayLength" : 5,
			"sAjaxSource" : "getDepotDepartureTime.action?alertID=" + alertId
					+ "&depotID=",
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
		jQuery('#vehicle_lateDeparture_data_wrapper .dataTables_filter input')
				.addClass("form-control input-small input-inline"); // modify
		// table
		jQuery('#vehicle_lateDeparture_data_wrapper .dataTables_length select')
				.addClass("form-control input-xsmall input-inline"); // modify
		// table
		jQuery('#vehicle_lateDeparture_data_wrapper .dataTables_length select')
				.addClass("form-control input-xsmall input-inline"); // modify

		$("#vehicle_chart_alert").removeClass("collapse");
		$("#vehicle_chart_alert").addClass("expand");
		$("#alert_vehicle_display_chart").attr("style", "display:none");

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

			"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
			// per
			// page
			// values
			// here
			],
			// set the initial value
			"iDisplayLength" : 5,
			"sAjaxSource" : "getSkippedStop.action?alertID=" + alertId,
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

		jQuery('#skipStopAlert_data_wrapper .dataTables_filter input')
				.addClass("form-control input-small input-inline"); // modify
		// table
		jQuery('#skipStopAlert_data_wrapper .dataTables_length select')
				.addClass("form-control input-xsmall input-inline"); // modify
		/*
		 * $("#vehicle_chart_alert").removeClass("collapse");
		 * $("#vehicle_chart_alert").addClass("expand");
		 * $("#alert_vehicle_display_chart").attr("style", "display:none");
		 */

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
			"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
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
		jQuery('#sosAlert_data_wrapper .dataTables_filter input').addClass(
				"form-control input-small input-inline"); // modify
		// table
		jQuery('#sosAlert_data_wrapper .dataTables_length select').addClass(
				"form-control input-xsmall input-inline"); // modify

		/*
		 * $("#vehicle_chart_alert").removeClass("collapse");
		 * $("#vehicle_chart_alert").addClass("expand");
		 * $("#alert_vehicle_display_chart").attr("style", "display:none");
		 */

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
			"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
			// per
			// page
			// values
			// here
			],
			// set the initial value
			"iDisplayLength" : 5,
			"sAjaxSource" : "getOverSpeed.action?alertID=" + alertId,
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
		jQuery('#overSpeeding_data_wrapper .dataTables_filter input').addClass(
				"form-control input-small input-inline"); // modify
		// table
		jQuery('#overSpeeding_data_wrapper .dataTables_length select')
				.addClass("form-control input-xsmall input-inline"); // modify

		/*
		 * $("#vehicle_chart_alert").removeClass("collapse");
		 * $("#vehicle_chart_alert").addClass("expand");
		 * $("#alert_vehicle_display_chart").attr("style", "display:none");
		 */

	}
	if (obj.series.status == "Bus Bunching") {
		divId = document.getElementById("busbunching");
		divId.style.display = '';
		table4 = $('#busbunching_data');
		alertId = "Bus Bunching";
		$("#alert").attr("style", "display:none");
		$("#alert1").attr("style", "display:none");
		$("#alert2").attr("style", "display:none");
		$("#alert3").attr("style", "display:none");
		$("#sosdetails").attr("style", "display:none");
		$("#alert4").attr("style", "display:none");
		$("#alert5").attr("style", "display:none");
		$("#vehicle").attr("style", "display:none");
		$("#alertDev").attr("style", "display:none");
		$("#alerttampering").attr("style", "display:none");
		$("#vehicleOIB").attr("style", "display:none");
		$("#overSpeeding").attr("style", "display:none");
		$("#HA").attr("style", "display:none");
		$("#HD").attr("style", "display:none");
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
			"sAjaxSource" : "getBusbunching.action?alertID="
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
				'#busbunching_data_wrapper .dataTables_filter input')
				.addClass("form-control input-small input-inline"); // modify
		// table
		jQuery(
				'#busbunching_data_wrapper .dataTables_length select')
				.addClass("form-control input-xsmall input-inline"); // modify

		$("#vehicle_chart_alert").removeClass("collapse");
		$("#vehicle_chart_alert").addClass("expand");
		$("#alert_vehicle_display_chart").attr("style",
				"display:none");

	}
	if (obj.series.status == "HA") {
		divId = document.getElementById("HA");
		divId.style.display = '';
		table4 = $('#HA_data');
		alertId = "HA";
		$("#busbunching").attr("style", "display:none");
		$("#HD").attr("style", "display:none");
		$("#alert").attr("style", "display:none");
		$("#alert1").attr("style", "display:none");
		$("#alert2").attr("style", "display:none");
		$("#alert3").attr("style", "display:none");
		$("#sosdetails").attr("style", "display:none");
		$("#alert4").attr("style", "display:none");
		$("#alert5").attr("style", "display:none");
		$("#vehicle").attr("style", "display:none");
		$("#alertDev").attr("style", "display:none");
		$("#alerttampering").attr("style", "display:none");
		$("#vehicleOIB").attr("style", "display:none");
		$("#overSpeeding").attr("style", "display:none");
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
			"sAjaxSource" : "getHA.action?alertID="
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
				'#HA_data_wrapper .dataTables_filter input')
				.addClass("form-control input-small input-inline"); // modify
		// table
		jQuery(
				'#HA_data_wrapper .dataTables_length select')
				.addClass("form-control input-xsmall input-inline"); // modify

		$("#vehicle_chart_alert").removeClass("collapse");
		$("#vehicle_chart_alert").addClass("expand");
		$("#alert_vehicle_display_chart").attr("style",
				"display:none");

	}
	if (obj.series.status == "HD") {
		divId = document.getElementById("HD");
		divId.style.display = '';
		table4 = $('#HD_data');
		alertId = "HD";
		$("#busbunching").attr("style", "display:none");
		$("#HA").attr("style", "display:none");
		$("#alert").attr("style", "display:none");
		$("#alert1").attr("style", "display:none");
		$("#alert2").attr("style", "display:none");
		$("#alert3").attr("style", "display:none");
		$("#sosdetails").attr("style", "display:none");
		$("#alert4").attr("style", "display:none");
		$("#alert5").attr("style", "display:none");
		$("#vehicle").attr("style", "display:none");
		$("#alertDev").attr("style", "display:none");
		$("#alerttampering").attr("style", "display:none");
		$("#vehicleOIB").attr("style", "display:none");
		$("#overSpeeding").attr("style", "display:none");
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
			"sAjaxSource" : "getHD.action?alertID="
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
				'#HD_data_wrapper .dataTables_filter input')
				.addClass("form-control input-small input-inline"); // modify
		// table
		jQuery(
				'#HD_data_wrapper .dataTables_length select')
				.addClass("form-control input-xsmall input-inline"); // modify

		$("#vehicle_chart_alert").removeClass("collapse");
		$("#vehicle_chart_alert").addClass("expand");
		$("#alert_vehicle_display_chart").attr("style",
				"display:none");

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

	$('#tripReportDetails123')
			.dataTable({

				"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
				// per
				// page
				// values
				// here
				],
				// set the initial value
				"iDisplayLength" : 20,
				"sAjaxSource" : "tripReportDetails123.action?id=" + id,
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
					'aTargets' : [ 0, 1 ]
				} ],
				aaSorting : [],
				"bLengthChange" : false,
				"sDom" : '<"top">rt<"bottom"flp><"clear">'
			})
			.wrap(
					"<div style='position:auto;overflow:auto;height:300px;width:100%'/>");

	jQuery('#tripReportDetails_wrapper .dataTables_filter input').addClass(
			"form-control input-medium input-inline"); // modify
	// table
	// search input
	jQuery('#tripReportDetails_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");

	// getting VTU Report..

}
function viewBreakLocations(id) {

	$('#breaklocations')
			.dataTable({

				"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
				// per
				// page
				// values
				// here
				],
				// set the initial value
				"iDisplayLength" : 20,
				"sAjaxSource" : "breaklocationList.action?id=" + id,
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
					'aTargets' : [ 0, 1, 2, 3, 4 ]
				} ],
				aaSorting : [],
				"bLengthChange" : false,
				"sDom" : '<"top">rt<"bottom"flp><"clear">'
			})
			.wrap(
					"<div style='position:auto;overflow:auto;height:300px;width:100%'/>");

	jQuery('#breaklocations_wrapper .dataTables_filter input').addClass(
			"form-control input-medium input-inline"); // modify
	// table
	// search input
	jQuery('#breaklocations_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");

	// getting VTU Report..

}
function getAlertReportOnSubmit() {
	var givenDate = $("#divisionlistid1").val();

	if (givenDate != 00) {
		var givendate = $("#seldate").val();
		var alertId = $("#alertlist_id").val();
		var depotID = $("#divisionlistid1").val();
		var todate=$("#endate").val();
		alert("todate --"+todate);
		var table;
		if (alertId == 20 || alertId == 21) {
			var table;
			$("#accidentAlertDivId").hide();
			$("#vtsAlertReportDivId").hide();
			$("#scheduleReport").show();
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
				"sAjaxSource" : "getAlertReport.action?alertID=" + alertId
						+ "&depotID=" + depotID + "&givendate=" + givendate +"&todate="+todate,
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
			jQuery('#schAlertid_wrapper .dataTables_filter input').addClass(
					"form-control input-small input-inline"); // modify
			// table
			jQuery('#schAlertid_wrapper .dataTables_length select').addClass(
					"form-control input-xsmall input-inline"); // modify
		} else {
			if (alertId == 22 || alertId == 23) {
				$("#vtsAlertReportDivId").hide();
				$("#scheduleReport").hide();
				$("#accidentAlertDivId").show();
				table = $('#accidentAlertid');
			} else {
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
				"sAjaxSource" : "getAlertReport.action?alertID=" + alertId
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
			jQuery('#vtsAlertReportId_wrapper .dataTables_filter input')
					.addClass("form-control input-small input-inline"); // modify
			// table
			jQuery('#vtsAlertReportId_wrapper .dataTables_length select')
					.addClass("form-control input-xsmall input-inline"); // modify

			jQuery('#accidentAlertid_wrapper .dataTables_filter input')
					.addClass("form-control input-small input-inline"); // modify
			// table
			jQuery('#accidentAlertid_wrapper .dataTables_length select')
					.addClass("form-control input-xsmall input-inline");

		}
	}
}
function getAlertReport(givenDate) {

	if (givenDate != 00) {
		var givendate = $("#seldate").val();
		var alertId = $("#alertlist_id").val();
		var depotID = $("#divisionlistid1").val();
		if (alertId == 20 || alertId == 21) {
			var table;
			$("#accidentAlertDivId").hide();
			$("#vtsAlertReportDivId").hide();
			$("#scheduleReport").show();
			table = $('#schAlertid');
			var oTable = table
					.dataTable({
						"aLengthMenu" : [ [ 5, 15, 20, -1 ],
								[ 5, 15, 20, "All" ] // change
						// per
						// page
						// values
						// here
						],
						// set the initial value
						"iDisplayLength" : 5,
						"sAjaxSource" : "getAlertReport.action?alertID="
								+ alertId + "&depotID=" + depotID
								+ "&givendate=" + givendate,
						"sPaginationType" : "bootstrap",
						"bDestroy" : true,

						"oLanguage" : {
							"sLengthMenu" : "_MENU_ records",
							"oPaginate" : {
								"sPrevious" : "Prev",
								"sNext" : "Next"
							}
						},
						"oTableTools" : {
							"sSwfPath" : "<c:url value='../../swf/copy_csv_xls_pdf.swf'/>",
							"aButtons" : [
									"csv",
									{
										"sExtends" : "pdf",
										"sPdfOrientation" : "landscape"
									},
									{
										"sExtends" : "print",
										"sMessage" : "<br/>"
												+ " <input id='returnButton' type='button' value='Return to Group Requests' onclick='TableTools.fnGetInstance(\"example\")._fnPrintEnd({keyCode:27, preventDefault:function(){}})' />"
									} ]
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
		} else {
			var table;
			if (alertId == 22 || alertId == 23) {
				$("#accidentAlertDivId").show();
				$("#vtsAlertReportDivId").hide();
				$("#scheduleReport").hide();
				table = $('#accidentAlertid');
			} else {
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
				"sAjaxSource" : "getAlertReport.action?alertID=" + alertId
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
			jQuery('#vtsAlertReportId_wrapper .dataTables_filter input')
					.addClass("form-control input-small input-inline"); // modify
			// table
			jQuery('#vtsAlertReportId_wrapper .dataTables_length select')
					.addClass("form-control input-xsmall input-inline"); // modify

			jQuery('#accidentAlertid_wrapper .dataTables_filter input')
					.addClass("form-control input-small input-inline"); // modify
			// table
			jQuery('#accidentAlertid_wrapper .dataTables_length select')
					.addClass("form-control input-xsmall input-inline");

		}
	}

}
function getVehicleAlertData(packet_code, misc_byte, deivice_id, licence_number) {
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
	} else if (packet_code == 'DV' && misc_byte == '00') {
		document.getElementById("withbusstop").style.display = 'none';
		document.getElementById("withoutbusstop").style.display = 'none';
		document.getElementById("devdetails").style.display = '';
		/*
		 * Deviation Details
		 * 
		 */
		$('#vehicleDetailsDev').dataTable(
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
		jQuery('#vehicleDetailsDev_wrapper .dataTables_filter input').addClass(
				"form-control input-medium input-inline"); // modify table
		// search input
		jQuery('#vehicleDetailsDev_wrapper .dataTables_length select')
				.addClass("form-control input-xsmall input-inline");

	} else if (packet_code == '51' && misc_byte == '11') {
		document.getElementById("withbusstop").style.display = 'none';
		document.getElementById("withoutbusstop").style.display = 'none';
		document.getElementById("tamperingdetails").style.display = '';
		/*
		 * Tampering Details
		 * 
		 */
		$('#vehicleDetailsTampering').dataTable(
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
		jQuery('#vehicleDetailsTampering_wrapper .dataTables_filter input')
				.addClass("form-control input-medium input-inline"); // modify
		// table
		// search input
		jQuery('#vehicleDetailsTampering_wrapper .dataTables_length select')
				.addClass("form-control input-xsmall input-inline");
	}

	else {
		document.getElementById("withbusstop").style.display = 'none';
		document.getElementById("devdetails").style.display = 'none';
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
	return;
}
// schedule wise Extract Trip report
function viewScheduleWiseExtractReport(id, selectdate, schno) {

	// alert("schedulename ......"+schno);
	// var schedulename="hello";
	$("#showschedulename").html('Trip  Details For Schedule No :' + schno);
	// document.getElementById('showschedulename').html('SCHNO');
	$('#tripExtractReportDetails123')
			.dataTable(
					{

						"aLengthMenu" : [ [ 5, 15, 20, -1 ],
								[ 5, 15, 20, "All" ] // change
						// per
						// page
						// values
						// here
						],
						// set the initial value
						"iDisplayLength" : 20,
						"sAjaxSource" : "scheduleWisetripReport.action?scheduleno="
								+ id
								+ "&selectdate="
								+ selectdate
								+ "&schedulename=" + schedulename,
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
							'aTargets' : [ 0, 1 ]
						} ],
						aaSorting : [],
						"bLengthChange" : false,
						"sDom" : '<"top">rt<"bottom"flp><"clear">'
					})
			.wrap(
					"<div style='position:auto;overflow:auto;height:300px;width:100%'/>");

	jQuery('#tripExtractReportDetails123_wrapper .dataTables_filter input')
			.addClass("form-control input-medium input-inline"); // modify
	// table
	// search input
	jQuery('#tripExtractReportDetails123_wrapper .dataTables_length select')
			.addClass("form-control input-xsmall input-inline");

	// getting VTU Report..

}
function getVehicleDepotDetailsData(deviceid, selecteTime) {
	alert(deviceid);
}



