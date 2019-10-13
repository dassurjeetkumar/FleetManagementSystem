var intervalID = null;
function skipRoute() {
	// Code Goes Here..
	getSkipRoute();
	getDeviation();
	intervalID = setInterval(function() {
		// $("#alert5").attr("style", "display:none");
		// $("#alert_dashBoard_data").removeClass("collapse");
		$("#alert4").removeClass("close");
		getSkipRoute();
		getDeviation();
	}, 30000);
}

function getSkipRoute() {
	/* if (obj.series.status == "Skip Stop") { */
	divId = document.getElementById("alert4");
	divId.style.display = '';
	table4 = $('#skipAlert');
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
	table4.dataTable({
		"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
		// per
		// page
		// values
		// here
		],
		// set the initial value
		"iDisplayLength" : 5,
		"sAjaxSource" : "getSkippedStopNew.action?alertID=" + alertId,
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
	jQuery('#skipAlert_wrapper .dataTables_filter input').addClass(
			"form-control input-medium input-inline"); // modify table
	// search input
	jQuery('#skipAlert_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");
}

function getDeviation() {
	$('#deviationAlert').dataTable({
		"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
		// per
		// page
		// values
		// here
		],
		// set the initial value
		"iDisplayLength" : 5,
		"sAjaxSource" : "getRouteDeviationData.action?packet_code=Deviated",
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
	jQuery('#deviationAlert_wrapper .dataTables_filter input').addClass(
			"form-control input-medium input-inline"); // modify table
	// search input
	jQuery('#deviationAlert_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");
}
function TrackVehicle(packet_code, schno, device_id, vehicle_no) {
	document.getElementById("deviceid").value = device_id;
	document.getElementById("vehicleid").value = vehicle_no;
	document.getElementById("scheduleno").value = schno;
	window.open("pages/vts/livetracking.jsp", "Edit GeoFence",
			"width=500, height=500");
}

function getArrivalDeparture(parameter) {
	var dataerange = $('#dashboard-report-range span').html();
	var parameter=$("#inoutValue").val();
	var depot_id=$("#depotlist").val();
	
	if (parameter != 0) {
		if (parameter == 'Arrival') {
			document.getElementById("12345").style.display = 'none';
			document.getElementById("1234").style.display = '';
			$('#viewVehicleDepotinEntry-out')
					.dataTable(
							{
								"aLengthMenu" : [ [ 10, 25, 30, -1 ],
										[ 10, 25, 30, "All" ] // change
								// per
								// page
								// values
								// here
								],
								// set the initial value
								"iDisplayLength": 10,
				                "bRetrive" :true,
				                "bDestroy" : true,
								"sAjaxSource" : "showVehicleDepotin-outList.action?daterange="
									+ dataerange+ "&parameter=" + parameter + "&depot_id=" + depot_id,
									"sPaginationType": "bootstrap",
					                "oLanguage": {
					                    "sLengthMenu": "_MENU_ records",
					                    "oPaginate": {
					                        "sPrevious": "Prev",
					                        "sNext": "Next"
					                    }
					                },
					               "aoColumnDefs": [
					                    { 'bSortable': false, 'aTargets': [0] },
					                    { "bSearchable": false, "aTargets": [ 0 ] },
					                    { "sClass": "url", "aTargets": [ 3] },
					                ],
								"fnRowCallback" : function(nRow, aaData,
										iDisplayIndex, iDisplayIndexFull) {
									var sDirectionClass;
									if (aaData[6] == "Early") {
										sDirectionClass = "success";
									} else if (aaData[6] == "Late") {
										sDirectionClass = "danger";
									} else {
										sDirectionClass = "outbound";
									}
									$(nRow).addClass(sDirectionClass);
									return nRow;
								}

							});
			jQuery(
					'#viewVehicleDepotinEntry-out_wrapper .dataTables_filter input')
					.addClass("form-control input-small input-inline"); // modify
			// table
			// search
			// input
			jQuery(
					'#viewVehicleDepotinEntry-out_wrapper .dataTables_length select')
					.addClass("form-control input-xsmall input-inline"); // modify
			// table
			// per
			// page
			// dropdown
		} else {
			document.getElementById("1234").style.display = 'none';
			document.getElementById("12345").style.display = '';
			$('#viewVehicleDepotinExit-out')
					.dataTable(
							{
								"aLengthMenu" : [ [ 10, 25, 30, -1 ],
										[ 10, 25, 30, "All" ] // change
								// per
								// page
								// values
								// here
								],
								// set the initial value
								"iDisplayLength" : 10,
								"sAjaxSource" : "showVehicleDepotin-outList.action?daterange="
										+ dataerange+ "&parameter=" + parameter + "&depot_id=" + depot_id,
								"sPaginationType" : "bootstrap",
								"bDestroy" : true,
								"bRetrive" :true,
								"oLanguage" : {
									"sLengthMenu" : "_MENU_ records",
									"oPaginate" : {
										"sPrevious" : "Prev",
										"sNext" : "Next"
									}
								},
								"fnRowCallback" : function(nRow, aaData,
										iDisplayIndex, iDisplayIndexFull) {
									var sDirectionClass;
									if (aaData[6] == "Early") {
										sDirectionClass = "success";
									} else if (aaData[6] == "Late") {
										sDirectionClass = "danger";
									} else {
										sDirectionClass = "outbound";
									}
									$(nRow).addClass(sDirectionClass);
									return nRow;
								},
								"aoColumnDefs" : [ {
									'bSortable' : false,
									'aTargets' : [ 0 ]
								} ]

							});
			jQuery(
					'#viewVehicleDepotinExit-out_wrapper .dataTables_filter input')
					.addClass("form-control input-small input-inline"); // modify
			// table
			// search
			// input
			jQuery(
					'#viewVehicleDepotinExit-out_wrapper .dataTables_length select')
					.addClass("form-control input-xsmall input-inline"); // modify
			// table
			// per
			// page
			// dropdown
		}
		$.ajax({
			type : "get",
			url : 'showVehicleDepotin-outList1.action?daterange=' + dataerange
					+ "&parameter1=" + parameter+ "&depot_id=" + depot_id
					+ "&iDisplayStart=0&iDisplayLength=10&sSearch=",
			success : function(result) {
				var obj = result;
				console.log(obj["countData"][0]);
				if (obj != null) {
					$("#Early").html(
							"<B><center>Total Early "+parameter+" Vehicles:</center><h4><center>"
									+ obj["countData"][1] + "</center>");
					$("#Late").html(
							"<B><center>Total Late "+parameter+" Vehicles:</center><h4><center>"
									+ obj["countData"][0] + "</center>");
					$("#Ontime").html(
							"<B><center>Total Ontime "+parameter+" Vehicles:</center><h4><center>"
									+ obj["countData"][2] + "</center>");
				}

			}
		});
	}
}

function getVehicleSkipStopData(device_id,shift) {
	$("#HA").attr("style", "display:none");
	$("#HD").attr("style", "display:none");
	document.getElementById("alert5").style.display = '';
	var oTable = $('#skipStopAlert_data1').dataTable({
		"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
		// per
		// page
		// values
		// here
		],
		// set the initial value
		"iDisplayLength" : 5,
		"sAjaxSource" : "alertSkippedStopDetails.action?alertID=" + device_id ,
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
	jQuery('#skipStopAlert_data1_wrapper .dataTables_filter input').addClass(
			"form-control input-small input-inline"); // modify
	// table
	jQuery('#skipStopAlert_data1_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline"); // modify
}

function getVehicleDepotDetailsData(deviceid,selecteTime,vehicleno){
	document.getElementById("depotdept").style.display = '';
	document.getElementById("depotArr").style.display = 'none';
	var oTable = $('#depotdeptdetails').dataTable({
		"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
		// per
		// page
		// values
		// here
		],
		// set the initial value
		"iDisplayLength" : 5,
		"sAjaxSource" : "depotdept.action?deviceID=" + deviceid + "&selecteTime=" + selecteTime + "&vehicleno=" + vehicleno,
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
	jQuery('#depotdeptdetails_wrapper .dataTables_filter input').addClass(
			"form-control input-small input-inline"); // modify
	// table
	jQuery('#depotdeptdetails_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline"); // modify
}
function getVehicleDepotArrDetailsData(deviceid,selecteTime,vehicleno){
	document.getElementById("depotdept").style.display = 'none';
	document.getElementById("depotArr").style.display = '';
	var oTable = $('#depotArrdetails').dataTable({
		"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
		// per
		// page
		// values
		// here
		],
		// set the initial value
		"iDisplayLength" : 5,
		"sAjaxSource" : "depotarr.action?deviceID=" + deviceid + "&selecteTime=" + selecteTime + "&vehicleno=" + vehicleno,
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
	jQuery('#depotArrdetails_wrapper .dataTables_filter input').addClass(
			"form-control input-small input-inline"); // modify
	// table
	jQuery('#depotArrdetails_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline"); // modify
}
