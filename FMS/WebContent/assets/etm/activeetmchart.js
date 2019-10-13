var baralertData;
var activeChart = function() {
	var loadIntervalId = null;
	var loadBarchartIntervalId = null;
	var loadIntervalId = null;
	var loadBarchartIntervalId = null;
	return {
		// main function to initiate the module
		init : function() {
			Metronic.addResizeHandler(function() {
				activeChart.initPieCharts();
			});
		},
		initCharts : function() {
			if (!jQuery.plot) {
				return;
			}
			var data = [];
			var totalPoints = 250;
			// random data generator for plot charts
			function getRandomData() {
				if (data.length > 0)
					data = data.slice(1);
				// do a random walk
				while (data.length < totalPoints) {
					var prev = data.length > 0 ? data[data.length - 1] : 50;
					var y = prev + Math.random() * 10 - 5;
					if (y < 0)
						y = 0;
					if (y > 100)
						y = 100;
					data.push(y);
				}
				// zip the generated y values with the x values
				var res = [];
				for ( var i = 0; i < data.length; ++i)
					res.push([ i, data[i] ])
				return res;
			}
		},

		initPieCharts : function() {
			function getDeviceDataDashboard() {
				var vehicleData = $.ajax({
					url : "getDeviceDashboardData!getDevicePieChartDetails",
					dataType : 'json',
					contentType : "application/json",
					global : false,
					async : true,
					success : function(response) {
						var plotVehicleResponseData = response;
						plotVehicleData(plotVehicleResponseData);
						return response;
					}
				}).responseText;
			}

			if (loadIntervalId != null) {
				clearInterval(loadIntervalId);
			}
			;
			loadIntervalId = setInterval(function() {
				getDeviceDataDashboard();
			}, 1000000);

			$("#alertspie").bind("plothover", pieHoveralert);
			$("#alertspie").bind("plotclick", pieClickalert);

			function pieHoveralert(event, pos, obj) {
				if (!obj)
					return;
				percent = parseFloat(obj.series.percent).toFixed(2);
				$("#hover").html(
						'<span style="font-weight: bold; color: '
								+ obj.series.color + '">' + obj.series.label
								+ ' (' + percent + '%)</span>');
			}

			function pieClickalert(event, pos, obj) {
				if (!obj)
					return;
				var table ;
				var divId ;
				percent = parseFloat(obj.series.percent).toFixed(2);
				console.log(obj.series.status);
				if(obj.series.status=='Trimax'){
				  table = $('#alert_dashBoard_data');
				  divId = document.getElementById("vehicle");
				  $("#deviceGprs").attr("style", "display:none");
				  $("#vehicle").attr("style", "display:''");
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
						"sAjaxSource" : "getDeviceDashboardData!getDeviceDetails?alertID="
								+ obj.series.status,
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
							'#alert_dashBoard_data_wrapper .dataTables_filter input')
							.addClass("form-control input-small input-inline"); // modify
					// table
					jQuery(
							'#alert_dashBoard_data_wrapper .dataTables_length select')
							.addClass("form-control input-xsmall input-inline"); // modify
					// table

				}else if(obj.series.status=='Hanover'){
					  table = $('#alert_dashBoard_data');
					  divId = document.getElementById("vehicle");
					  $("#deviceGprs").attr("style", "display:none");
					  $("#vehicle").attr("style", "display:''");
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
							"sAjaxSource" : "getDeviceDashboardData!getDeviceDetails?alertID="
									+ obj.series.status,
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
								'#alert_dashBoard_data_wrapper .dataTables_filter input')
								.addClass("form-control input-small input-inline"); // modify
						// table
						jQuery(
								'#alert_dashBoard_data_wrapper .dataTables_length select')
								.addClass("form-control input-xsmall input-inline"); // modify
				  
				}else if(obj.series.status=='Volvo'){
//					alert("------"+obj.series.status);
							  table = $('#alert_dashBoard_data');
							  divId = document.getElementById("vehicle");
							  $("#deviceGprs").attr("style", "display:none");
							  $("#vehicle").attr("style", "display:''");
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
									"sAjaxSource" : "getDeviceDashboardData!getDeviceDetails?alertID="
											+ obj.series.status,
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
										'#alert_dashBoard_data_wrapper .dataTables_filter input')
										.addClass("form-control input-small input-inline"); // modify
								// table
								jQuery(
										'#alert_dashBoard_data_wrapper .dataTables_length select')
										.addClass("form-control input-xsmall input-inline"); // modify
				}
				  
				/*  
				}else{
				  table = $('#alert_dashBoard_data');
				  divId = document.getElementById("deviceGprs");
				  $("#vehicle").attr("style", "display:none");
				  $("#deviceGprs").attr("style", "display:''");
				  
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
						"sAjaxSource" : "getDeviceDashboardData!getInactiveDetails?alertID="
								+ obj.series.status,
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
							'#alert_gprs_data_wrapper .dataTables_filter input')
							.addClass("form-control input-small input-inline"); // modify
					// table
					jQuery(
							'#alert_gprs_data_wrapper .dataTables_length select')
							.addClass("form-control input-xsmall input-inline"); // modify
					// table

				}*/
				
				
		
				
				
				$("#vehicle_chart_alert").removeClass("collapse");
				$("#vehicle_chart_alert").addClass("expand");
				$("#alert_vehicle_display_chart").attr("style", "display:none");
			}

		},
		initBarCharts : function() {
			/*
			 * loadBarchartIntervalId = setInterval(function() {
			 * getAlertIntervalData(); }, 0000);
			 */
			// loadBarchartIntervalId = setInterval(getAlertIntervalData, 0000);
		///	alert("HIOI@@");
			function getAlertIntervalData() {
				// Ajax Call
				var totalalertData;

				$.ajax({
					//url : "getAlertDataForEtm!getAlertDetailsForEtm",
					dataType : 'json',
					contentType : "application/json",
					global : false,
					async : true,
					success : function(response) {
						baralertData = response;
						totalalertData = $.ajax({
							//url : "getAlertDataForEtm!gettotalAlertDetails",
							contentType : "application/json",
							dataType : 'json',
							global : false,
							async : true,
							success : function(response) {
								getBarChartInfo(baralertData, response);
								return response;
								
							}
						}).responseText;
						return response;
						 
					}
				}).responseText;

			}
             
			if (loadBarchartIntervalId != null) {
				clearInterval(loadBarchartIntervalId);
			}

			if (loadBarchartIntervalId != null) {
				clearInterval(loadBarchartIntervalId);
			}
			loadBarchartIntervalId = setInterval(function() {
				getAlertIntervalData();
				// loadActiveVehicles();
			}, 1000000);

		   loadBarchartIntervalId = setInterval(function() {
				getAlertIntervalData();
			}, 1000000);

			$("#barchart_alert").bind("plotclick", barClickalert);
			$("#barchart_alert").bind("plothover", barHoveralert);
			function barClickalert(event, pos, obj) {
				
				console.log(obj.series.status);
				
				if (!obj)
					return;

				if (!obj)
					return;//				
				percent = parseFloat(obj.series.percent).toFixed(2);
				/* Will initialise Chart */

				var table = "";
				var depotId = "";
				var alertId = "";
				// $('#vehicle_dashBoard_data');
				var table1 = "";
				var table2 = "";
				var table3 = "";
				var table4 = "";
				var divId = "";
				// if(obj.series.status=="Deviated"){
				// divId.style.display='none';
				// divIddev.style.display = '';
				//					 
				//					
				// }
				if (obj.series.status == "Battery Status") {
					table = $('#device_dashBoard_battery_status');
					// $("#tbl_cnt").html("Distance(Meters)");
					$("#alert_dashBoard_data").removeClass("collapse");
					$("#alert_dashBoard_data").addClass("expand");
					$("#alertDev").attr("style", "display:''");
					$("#sosdetails").attr("style", "display:none");
					$("#alert1").attr("style", "display:none");
					$("#alert2").attr("style", "display:none");
					$("#alert3").attr("style", "display:none");
					$("#alert4").attr("style", "display:none");
					$("#alert5").attr("style", "display:none");
					$("#vehicle").attr("style", "display:none");
					$("#overSpeeding").attr("style", "display:none");
					$("#vehicleDetailsTampering").attr("style", "display:none");
					$("#alerttampering").attr("style", "display:none");
					$("#vehicleOIB").attr("style", "display:none");
					$("#vstatus").html(
							"Vehicle Data" + "(" + obj.series.status + ")");
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
						"sAjaxSource" : "getBatteryVehicles.action?alertID="
								+ obj.series.status,
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
							'#device_dashBoard_battery_status_wrapper .dataTables_filter input')
							.addClass("form-control input-small input-inline"); // modify
					// table
					jQuery(
							'#device_dashBoard_battery_status_wrapper .dataTables_length select')
							.addClass("form-control input-xsmall input-inline"); // modify
					// table
					$("#vehicle_chart_alert").removeClass("collapse");
					$("#vehicle_chart_alert").addClass("expand");
					$("#alert_vehicle_display_chart").attr("style",
							"display:none");

				}
				// Box Tampering Alert
				if (obj.series.status == "Tampered") {
					table = $('#device_dashBoard_tampering_data');
					// $("#tbl_cnt").html("Distance(Meters)");
					$("#alerttampering").attr("style", "display:''");
					$("#alertDev").attr("style", "display:none");
					$("#alert1").attr("style", "display:none");
					$("#sosdetails").attr("style", "display:none");
					$("#alert2").attr("style", "display:none");
					$("#alert3").attr("style", "display:none");
					$("#alert4").attr("style", "display:none");
					$("#alert5").attr("style", "display:none");
					$("#vehicle").attr("style", "display:none");
					$("#overSpeeding").attr("style", "display:none");
					$("#vehicleOIB").attr("style", "display:none");
					$("#vstatus").html(
							"Vehicle Data" + "(" + obj.series.status + ")");
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
						"sAjaxSource" : "getTamperedVehicles.action?alertID="
								+ obj.series.status,
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
							'#device_dashBoard_tampering_data_wrapper .dataTables_filter input')
							.addClass("form-control input-small input-inline"); // modify
					// table
					jQuery(
							'#device_dashBoard_tampering_data_wrapper .dataTables_length select')
							.addClass("form-control input-xsmall input-inline"); // modify
					// table
					$("#vehicle_chart_alert").removeClass("collapse");
					$("#vehicle_chart_alert").addClass("expand");
					$("#alert_vehicle_display_chart").attr("style",
							"display:none");

				}

				// Early Arrival
				if (obj.series.status == "GPRS") {
					document.getElementById("withoutbusstop").style.display = 'none';
					divId = document.getElementById("alert1");
					divId.style.display = '';
					table1 = $('#device_gprs_data');
					alertId = "Early Arrival";
					$('#alert_dashBoard_data').dataTable().fnClearTable();
					$('#alert_dashBoard_data').dataTable().fnDestroy();
					$("#alert_dashBoard_data").removeClass("collapse");
					$("#alert_dashBoard_data").addClass("expand");
					$("#alert").attr("style", "display:none");
					$("#alert2").attr("style", "display:none");
					$("#alert3").attr("style", "display:none");
					$("#alert4").attr("style", "display:none");
					$("#sosdetails").attr("style", "display:none");
					$("#alert5").attr("style", "display:none");
					$("#vehicle").attr("style", "display:none");
					$("#alertDev").attr("style", "display:none");
					$("#overSpeeding").attr("style", "display:none");
					$("#alerttampering").attr("style", "display:none");
					$("#vehicleOIB").attr("style", "display:none");
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
						"sAjaxSource" : "getGprsSignalVehicles.action?alertID="
								+ alertId + "&depotID=" + depotId,
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
							'#device_gprs_data_wrapper .dataTables_filter input')
							.addClass("form-control input-small input-inline"); // modify
					// table
					jQuery(
							'#device_gprs_data_wrapper .dataTables_length select')
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
				if (obj.series.status == "Late Dept") {
					divId = document.getElementById("alert2");
					divId.style.display = '';
					table2 = $('#vehicle_lateDeparture_data');
					alertId = "Late Dept";
					$("#alert").attr("style", "display:none");
					$("#alert1").attr("style", "display:none");
					$("#alert3").attr("style", "display:none");
					$("#sosdetails").attr("style", "display:none");
					$("#alert4").attr("style", "display:none");
					$("#alert5").attr("style", "display:none");
					$("#vehicle").attr("style", "display:none");
					$("#alertDev").attr("style", "display:none");
					$("#overSpeeding").attr("style", "display:none");
					$("#alerttampering").attr("style", "display:none");
					$("#vehicleOIB").attr("style", "display:none");
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
								+ alertId + "&depotID=" + depotId,
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
				if (obj.series.status == "Skip Stop") {
					divId = document.getElementById("alert4");
					divId.style.display = '';
					table4 = $('#skipStopAlert_data');
					alertId = "Skip Stop";
					$("#alert").attr("style", "display:none");
					$("#alert1").attr("style", "display:none");
					$("#alert2").attr("style", "display:none");
					$("#alert3").attr("style", "display:none");
					$("#alert5").attr("style", "display:none");
					$("#sosdetails").attr("style", "display:none");
					$("#vehicle").attr("style", "display:none");
					$("#alertDev").attr("style", "display:none");
					$("#overSpeeding").attr("style", "display:none");
					$("#alerttampering").attr("style", "display:none");
					$("#tamperingdetails").attr("style", "display:none");
					$("#vehicleOIB").attr("style", "display:none");
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

					$("#vehicle_chart_alert").removeClass("collapse");
					$("#vehicle_chart_alert").addClass("expand");
					$("#alert_vehicle_display_chart").attr("style",
							"display:none");

				}

				// SOS
				if (obj.series.status == "SoS") {
					divId = document.getElementById("alert3");
					table3 = $('#sosAlert_data');
					divId.style.display = '';
					alertId = "SoS";
					$("#alert").attr("style", "display:none");
					$("#alert1").attr("style", "display:none");
					$("#alert2").attr("style", "display:none");
					$("#sosdetails").attr("style", "display:none");
					$("#alert4").attr("style", "display:none");
					$("#alert5").attr("style", "display:none");
					$("#vehicle").attr("style", "display:none");
					$("#alertDev").attr("style", "display:none");
					$("#overSpeeding").attr("style", "display:none");
					$("#alerttampering").attr("style", "display:none");
					$("#vehicleOIB").attr("style", "display:none");
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

					$("#vehicle_chart_alert").removeClass("collapse");
					$("#vehicle_chart_alert").addClass("expand");
					$("#alert_vehicle_display_chart").attr("style",
							"display:none");

				}

				if (obj.series.status == "Over Speed") {
					divId = document.getElementById("overSpeeding");
					divId.style.display = '';
					table4 = $('#overSpeeding_data');
					alertId = "Over Speed";
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

					$("#vehicle_chart_alert").removeClass("collapse");
					$("#vehicle_chart_alert").addClass("expand");
					$("#alert_vehicle_display_chart").attr("style",
							"display:none");

				}
				document.getElementById("vehicle").style.display = 'none';
				$("#vstatus").html(
						"<b>Alert Data For " + " (" + obj.series.status
								+ ")</b>");
				depotId = $('#depot_id').val();

				/*
				 * var oTable = table.dataTable({ "aLengthMenu" : [ [ 5, 15, 20,
				 * -1 ], [ 5, 15, 20, "All" ] // change // per // page // values //
				 * here ], // set the initial value "iDisplayLength" : 5,
				 * "sAjaxSource" : "alertPerticular.action?alertID=" +
				 * obj.series.status, "sPaginationType" : "bootstrap",
				 * "bDestroy" : true,
				 * 
				 * "oLanguage" : { "sLengthMenu" : "_MENU_ records", "oPaginate" : {
				 * "sPrevious" : "Prev", "sNext" : "Next" } }, "aoColumnDefs" : [ {
				 * 'bSortable' : false, 'aTargets' : [ 0 ] } ] }); jQuery(
				 * '#vehicle_dashBoard_data_wrapper .dataTables_filter input')
				 * .addClass("form-control input-small input-inline"); // modify //
				 * table jQuery( '#vehicle_dashBoard_data_wrapper
				 * .dataTables_length select') .addClass("form-control
				 * input-xsmall input-inline"); // modify
				 * $('#vehicle_dashBoard_data').dataTable() .fnClearTable();
				 * $('#vehicle_dashBoard_data').dataTable().fnDestroy();
				 * $("#vehicle_chart_alert").removeClass("collapse");
				 * $("#vehicle_chart_alert").addClass("expand");
				 * $("#alert_vehicle_display_chart").attr("style",
				 * "display:none");
				 */

				/*
				 * 
				 * 
				 * 
				 * 
				 */
			}
			function barHoveralert(event, pos, obj) {

			}

		}

	};

}();

function loadActiveVehicles() {
	var table = $('#alert_dashBoard_data');
	var divId = document.getElementById("vehicle");
	document.getElementById("vehicle").style.display = '';
	$("#vstatus").html("<b>Vehicle Data" + "(" + "Active" + ")</b>");
	var oTable = table.dataTable({
		"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
		// per
		// page
		// values
		// here
		],
		// set the initial value
		"iDisplayLength" : 5,
		"sAjaxSource" : "getallDeviceData.action?vehicleStatus=all",
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
	jQuery('#alert_dashBoard_data_wrapper .dataTables_filter input').addClass(
			"form-control input-small input-inline"); // modify
	// table
	jQuery('#alert_dashBoard_data_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline"); // modify
	// table
}
function getDeviceDataDashboard() {
	
	
	var vehicleData = $.ajax({
		url : "getDeviceDashboardData!getDevicePieChartDetails",
		dataType : 'json',
		contentType : "application/json",
		global : false,
		async : true,
		success : function(response) {
			var plotVehicleResponseData = response;
			plotVehicleData(plotVehicleResponseData);
			return response;
		}
	}).responseText;
//	alert("Hii");
//	var vehicleData = $.ajax({
//		//url : "getEtmDetails!getEtmPieChart",
//		dataType : 'json',
//		contentType : "application/json",
//		global : false,
//		async : true,
//		success : function(response) {
//			// alert(response);
//			var plotVehicleResponseData = response;
//			plotVehicleData(plotVehicleResponseData);
//			return response;
//
//		}
//	}).responseText;
//
//	var parseVehicleData = jQuery.parseJSON(vehicleData);

	/*
	 * var data = []; // var series = Math.floor(Math.random() * 10) + 1; series =
	 * parseVehicleData["aaData"].length;
	 * 
	 * for ( var i = 0; i < series; i++) { data[i] = { label :
	 * parseVehicleData["aaData"][i]["2"] + "-" +
	 * (parseVehicleData["aaData"][i]["0"]), data :
	 * Math.floor(parseVehicleData["aaData"][i]["1"]) - 1, status :
	 * parseVehicleData["aaData"][i]["2"] } } $ .plot( $("#alertspie"), data, {
	 * series : { pie : { show : true, radius : 1, // tilt : 0.5, label : { show :
	 * true, radius : 0.6, formatter : function(label, series) { var arr =
	 * label.split("-"); return '<div
	 * style="font-size:8pt;text-align:center;padding:2px;color:white;">' +
	 * arr[1] + '</div>'; }, background : { opacity : 0.8 } } } }, colors : [
	 * "#088A08", "#FF0000", "#afd8f8", "#cb4b4b", "#4da74d", "#9440ed",
	 * '#FF0FFF', '#000000' ], grid : { hoverable : true, clickable : true }
	 * 
	 * });
	 */}
function getAlertIntervalData() {
	// Ajax Call
	//alert("Hii in get6");
	var baralertData = $.ajax({
		//url : "getAlertDataForEtm!getAlertDetailsForEtm",
		dataType : 'json',
		contentType : "application/json",
		global : false,
		async : true,
		success : function(response) {
			//alert(response);
			baralertData = response;
			totalalertData = $.ajax({
				//url : "getAlertDataForEtm!gettotalAlertDetails",
				contentType : "application/json",
				dataType : 'json',
				global : false,
				async : true,
				success : function(response) {
					getBarChartInfo(baralertData, response);
					return response;
				}
			}).responseText;
			return response;
		}
	}).responseText;
}
function getDepoEarlyArrival() {
	window.scrollTo(1000, 0);
	var depotId = $('#divisionlistid1').val();
	document.getElementById("withoutbusstop").style.display = 'none';
	divId = document.getElementById("alert1");
	depo_list = document.getElementById('depotlist');
	table1 = $('#vehicle_earlyArrival_data');
	alertId = "Early Arrival";
	$('#alert_dashBoard_data').dataTable().fnClearTable();
	$('#alert_dashBoard_data').dataTable().fnDestroy();
	$("#alert_dashBoard_data").removeClass("collapse");
	$("#alert_dashBoard_data").addClass("expand");
	$("#alert").attr("style", "display:none");
	$("#alert2").attr("style", "display:none");
	$("#alert3").attr("style", "display:none");
	$("#alert4").attr("style", "display:none");
	$("#sosdetails").attr("style", "display:none");
	$("#alert5").attr("style", "display:none");
	$("#vehicle").attr("style", "display:none");
	$("#vehicleOIB").attr("style", "display:none");
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
				+ "&depotID=" + depotId,
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
function getDepoLateDeparture() {

	var depotId = $('#divisionlistid2').val();
	// alert("divisionlistid2"+depotId);
	divId = document.getElementById("alert2");
	table2 = $('#vehicle_lateDeparture_data');
	alertId = "Late Dept";
	$("#alert").attr("style", "display:none");
	$("#alert1").attr("style", "display:none");
	$("#alert3").attr("style", "display:none");
	$("#alert4").attr("style", "display:none");
	$("#sosdetails").attr("style", "display:none");
	$("#alert5").attr("style", "display:none");
	$("#vehicle").attr("style", "display:none");
	$("#vehicleOIB").attr("style", "display:none");
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
				+ "&depotID=" + depotId,
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
	// $("#vehicle_chart_live").removeClass("collapse");
	// $("#vehicle_chart_live").addClass("expand");
	// $("#chart_live_display").attr("style", "display:none");

}
function getBarChartInfo(baData, totalalertData) {
	var barparseData = baData;
	barseries = barparseData["aaData"].length;

	var totalalert = totalalertData;
	var data = [], ticks = [], avlData = [];
	var status = [];
	// bar chart:
	//alert("bserie"+JSON.stringify(barseries));
	
	for ( var i = 0; i < barseries; i++) {
	
		ticks.push([ i, (barparseData["aaData"][i]["1"]) ]);
		avlData.push(barparseData["aaData"][i]["1"]);
		data.push({
			// label : barparseData["aaData"][i]["2"] + "-"
			// + (barparseData["aaData"][i]["0"]),
			data : [ [ i, barparseData["aaData"][i]["0"] ] ],
			status : barparseData["aaData"][i]["1"]
		  
		});
		 // alert("bseried"+ barparseData["aaData"][i]["1"]+"status"+status);
	}
	//alert("bseriess"+JSON.stringify(status));
	var allAlerts = totalalert["bbData"].length;
	for ( var i = 0; i < allAlerts; i++) {
		if ($.inArray(totalalert["bbData"][i][1], avlData) == -1) {
			ticks.push([ (ticks.length), totalalert["bbData"][i]["1"] ]);
			data.push({
				// label : totalalert["bbData"][i]["2"],
				data : [ [ (data.length), 0 ] ],
				status : ""
			});
		} else {
		}
	}
	var options = {
		series : {
			lines : {
				lineWidth : 1,
			},
			shadowSize : 0,
			bars : {
				show : true,
				barWidth : 0.5,
				align : 'center'
			}
		},
		seriesColors : [ '#85802b', '#00749F', '#73C774', '#C7754C', '#17BDB8' ],
		xaxis : {
			ticks : ticks,
			axisLabel : "World Cities",
			axisLabelUseCanvas : true,
			axisLabelFontSizePixels : 12
		},
		yaxis : {
			axisLabel : "Average Temperature",
			axisLabelUseCanvas : true,
			axisLabelFontSizePixels : 12,
			axisLabelFontFamily : 'Verdana, Arial',
			axisLabelPadding : 1
		},
		grid : {
			hoverable : true,
			clickable : true
		}
	};
	$.plot($("#barchart_alert"), data, options);
	var somePlot = $.plot($("#barchart_alert"), data, options);
//	alert("s"+somePlot);
	var ctx = somePlot.getCanvas().getContext("2d");
	var series = somePlot.getData();
	var xaxis = somePlot.getXAxes()[0];
	var yaxis = somePlot.getYAxes()[0];
	var offset = somePlot.getPlotOffset();
	ctx.font = "16px 'Segoe UI'";

	var text = "";
	for ( var i = 0; i < series.length; i++) {
		if (i < barseries) {
			text = barparseData["aaData"][i]["0"];
		} else {
			text = 0;
		}
		ctx.fillStyle = options.seriesColors[i % 5];

		var metrics = ctx.measureText(text);
		var xPos = (xaxis.p2c(series[i].data[0][0]) + offset.left);
		var yPos = yaxis.p2c(series[i].data[0][1]) + offset.top - 10;
		ctx.fillText(text, xPos, yPos);
	}

}
/*
 * Added by Rasmi ranjan Das FOR SYNCHRONZING PLOTTING VEHICLE DATA WITH OTHER
 * MENU DISPLAY
 */
function plotVehicleData(vehicleData) {
	var data = [];
	// var series = Math.floor(Math.random() * 10) + 1;
	series = vehicleData["aaData"].length;

	for ( var i = 0; i < series -1; i++) {
		data[i] = {
			label : vehicleData["aaData"][i]["2"] + "-"
					+ (vehicleData["aaData"][i]["0"]),
			data : vehicleData["aaData"][i]["1"] ,
			status : vehicleData["aaData"][i]["2"]
		}
	}
	$('#totalVehicle').text("Total Devices:"+vehicleData["aaData"][3]["0"]);
	$
			.plot(
					$("#alertspie"),
					data,
					{
						series : {
							pie : {
								show : true,
								radius : 1,
								// tilt : 0.5,
								label : {
									show : true,
									radius : 0.8,
									formatter : function(label, series) {
										var arr = label.split("-");
										//console.log(arr[1]);
										return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
												+ arr[1] + '</div>';
									},
									background : {
										opacity : 0.8
									}
								}
							}

						},
						colors : [ "#088A08", "#FF0000",'#000000',"#4da74d","#cb4b4b",'#FF0FFF', "#afd8f8", 
								, "#9440ed",  '#000000' ],
						grid : {
							hoverable : true,
							clickable : true
						}

					});

	

}
