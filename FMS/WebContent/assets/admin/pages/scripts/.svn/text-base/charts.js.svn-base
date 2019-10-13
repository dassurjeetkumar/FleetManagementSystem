var baralertData;
var Charts = function() {
	var loadIntervalId = null;
	var loadBarchartIntervalId = null;
	var loadIntervalId = null;
	var loadBarchartIntervalId = null;
	return {
		// main function to initiate the module
		init : function() {
			Metronic.addResizeHandler(function() {
				Charts.initPieCharts();
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
		initBarCharts : function() {
			/*
			 * loadBarchartIntervalId = setInterval(function() {
			 * getAlertIntervalData(); }, 0000);
			 */
			// loadBarchartIntervalId = setInterval(getAlertIntervalData, 0000);
			function getAlertIntervalData() {
				// Ajax Call
				var totalalertData;

				
				
				
				$.ajax({
					url : "getAlertData!getAlertDetails",
					dataType : 'json',
					contentType : "application/json",
					global : false,
					async : true,
					success : function(response) {
						baralertData = response;
						totalalertData = $.ajax({
							url : "getAlertData!gettotalAlertDetails",
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
			}, 600000);

			loadBarchartIntervalId = setInterval(function() {
				getAlertIntervalData();
			}, 600000);
			
			$("#barchart_alert").bind("plotclick", barClickalert);
			$("#barchart_alert").bind("plothover", barHoveralert);
			function barClickalert(event, pos, obj) {
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
				
				//Accident
				if (obj.series.status == "Accident") {
					divId = document.getElementById("alertacc");
					table7 = $('#vehicle_accident');
					divId.style.display = '';
					alertId = "Accident";
					$("#busbunching").attr("style", "display:none");
					$("#HA").attr("style", "display:none");
					$("#alertLATE").attr("style", "display:none");
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
					var oTable7 = table7.dataTable({
						"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
						// per
						// page
						// values
						// here
						],
						// set the initial value
						"iDisplayLength" : 5,
						"sAjaxSource" : "getAccident.action?" + alertId,
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
					jQuery('#AccidentAlert_data_wrapper .dataTables_filter input').addClass(
							"form-control input-small input-inline"); // modify
					// table
					jQuery('#AccidentAlert_data_wrapper .dataTables_length select').addClass(
							"form-control input-xsmall input-inline"); // modify

					/*
					 * $("#vehicle_chart_alert").removeClass("collapse");
					 * $("#vehicle_chart_alert").addClass("expand");
					 * $("#alert_vehicle_display_chart").attr("style", "display:none");
					 */

				}
				
				if (obj.series.status == "BreakDown") {
					divId = document.getElementById("alertbreak");
					table8 = $('#vehicle_breakdown');
					divId.style.display = '';
					alertId = "BreakDown";
					$("#busbunching").attr("style", "display:none");
					$("#HA").attr("style", "display:none");
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
					$("#Accident").attr("style", "display:none");
					var oTable8 = table8.dataTable({
						"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
						// per
						// page
						// values
						// here
						],
						// set the initial value
						"iDisplayLength" : 5,
						"sAjaxSource" : "getBreakDown.action?" + alertId,
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
					jQuery('#BreakDownAlert_data_wrapper .dataTables_filter input').addClass(
							"form-control input-small input-inline"); // modify
					// table
					jQuery('#BreakDownAlert_data_wrapper .dataTables_length select').addClass(
							"form-control input-xsmall input-inline"); // modify

					/*
					 * $("#vehicle_chart_alert").removeClass("collapse");
					 * $("#vehicle_chart_alert").addClass("expand");
					 * $("#alert_vehicle_display_chart").attr("style", "display:none");
					 */

				}
				
				if (obj.series.status == "Depot In") {
					divId = document.getElementById("alert12");
					divId.style.display = '';
					table12 = $('#depot_InData');
					alertId = "Depot In";
					
					$("#alert").attr("style", "display:none");
					$("#alert1").attr("style", "display:none");
					$("#alert2").attr("style", "display:none");
					$("#alert3").attr("style", "display:none");
					$("#alert5").attr("style", "display:none");
					$("#alertLATE").attr("style", "display:none");
					$("#sosdetails").attr("style", "display:none");
					$("#vehicle").attr("style", "display:none");
					$("#alertDev").attr("style", "display:none");
					$("#overSpeeding").attr("style", "display:none");
					$("#alerttampering").attr("style", "display:none");
					$("#tamperingdetails").attr("style", "display:none");
					$("#vehicleOIB").attr("style", "display:none");
					$("#busbunching").attr("style", "display:none");
					$("#HA").attr("style", "display:none");
					$("#HD").attr("style", "display:none");
					$("#alert13").attr("style", "display:none");
					$("#alert4").attr("style", "display:none");
					$("#alert14").attr("style", "display:none");
					//window.scrollTo(0, 500);
					var oTable12 = table12.dataTable({

						"aLengthMenu" : [ [ 5, 15, 20, -1 ],
								[ 5, 15, 20, "All" ] // change
						// per
						// page
						// values
						// here
						],
						// set the initial value
						"iDisplayLength" : 5,
						"sAjaxSource" : "getDepotIn.action?alertID="
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
							'#depotInAlert_data_wrapper .dataTables_filter input')
							.addClass("form-control input-small input-inline"); // modify
					// table
					jQuery(
							'#depotInAlert_data_wrapper .dataTables_length select')
							.addClass("form-control input-xsmall input-inline"); // modify

//					$("#vehicle_chart_alert").removeClass("collapse");
//					$("#vehicle_chart_alert").addClass("expand");
//					$("#alert_vehicle_display_chart").attr("style",
//							"display:none");

				}
				
				
				if (obj.series.status == "Stationary Vehicles") {
					divId = document.getElementById("alert13");
					divId.style.display = '';
					table13 = $('#stationary_vehicle_Data');
					alertId = "Stationary Vehicles";
					
					$("#alert").attr("style", "display:none");
					$("#alert1").attr("style", "display:none");
					$("#alert2").attr("style", "display:none");
					$("#alert3").attr("style", "display:none");
					$("#alert5").attr("style", "display:none");
					$("#alertLATE").attr("style", "display:none");
					$("#sosdetails").attr("style", "display:none");
					$("#vehicle").attr("style", "display:none");
					$("#alertDev").attr("style", "display:none");
					$("#overSpeeding").attr("style", "display:none");
					$("#alerttampering").attr("style", "display:none");
					$("#tamperingdetails").attr("style", "display:none");
					$("#vehicleOIB").attr("style", "display:none");
					$("#busbunching").attr("style", "display:none");
					$("#HA").attr("style", "display:none");
					$("#HD").attr("style", "display:none");
					$("#alert12").attr("style", "display:none");
					$("#alert4").attr("style", "display:none");
					$("#alert14").attr("style", "display:none");
					
					//window.scrollTo(0, 500);
					var oTable13 = table13.dataTable({

						"aLengthMenu" : [ [ 5, 15, 20, -1 ],
								[ 5, 15, 20, "All" ] // change
						// per
						// page
						// values
						// here
						],
						// set the initial value
						"iDisplayLength" : 5,
						"sAjaxSource" : "getStationaryVehicle.action?alertID="
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
							'#stationaryvehicleAlert_data_wrapper .dataTables_filter input')
							.addClass("form-control input-small input-inline"); // modify
					// table
					jQuery(
							'#stationaryvehicleAlert_data_wrapper .dataTables_length select')
							.addClass("form-control input-xsmall input-inline"); // modify

//					$("#vehicle_chart_alert").removeClass("collapse");
//					$("#vehicle_chart_alert").addClass("expand");
//					$("#alert_vehicle_display_chart").attr("style",
//							"display:none");

				}
				
				
				if (obj.series.status == "WorkShop") {
					divId = document.getElementById("alert14");
					divId.style.display = '';
					table14 = $('#workshop_vehicle_Data');
					alertId = "WorkShop";
					
					$("#alert").attr("style", "display:none");
					$("#alert1").attr("style", "display:none");
					$("#alert2").attr("style", "display:none");
					$("#alert3").attr("style", "display:none");
					$("#alert5").attr("style", "display:none");
					$("#alertLATE").attr("style", "display:none");
					$("#sosdetails").attr("style", "display:none");
					$("#vehicle").attr("style", "display:none");
					$("#alertDev").attr("style", "display:none");
					$("#overSpeeding").attr("style", "display:none");
					$("#alerttampering").attr("style", "display:none");
					$("#tamperingdetails").attr("style", "display:none");
					$("#vehicleOIB").attr("style", "display:none");
					$("#busbunching").attr("style", "display:none");
					$("#HA").attr("style", "display:none");
					$("#HD").attr("style", "display:none");
					$("#alert12").attr("style", "display:none");
					$("#alert4").attr("style", "display:none");
					$("#alert13").attr("style", "display:none");
					$("#alertDev").attr("style", "display:none");
					
					//window.scrollTo(0, 500);
					var oTable14 = table14.dataTable({

						"aLengthMenu" : [ [ 5, 15, 20, -1 ],
								[ 5, 15, 20, "All" ] // change
						// per
						// page
						// values
						// here
						],
						// set the initial value
						"iDisplayLength" : 5,
						"sAjaxSource" : "getWorkShopVehicle.action?alertID="
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
							'#stationaryvehicleAlert_data_wrapper .dataTables_filter input')
							.addClass("form-control input-small input-inline"); // modify
					// table
					jQuery(
							'#stationaryvehicleAlert_data_wrapper .dataTables_length select')
							.addClass("form-control input-xsmall input-inline"); // modify

//					$("#vehicle_chart_alert").removeClass("collapse");
//					$("#vehicle_chart_alert").addClass("expand");
//					$("#alert_vehicle_display_chart").attr("style",
//							"display:none");

				}
				
				
				if (obj.series.status == "Deviated") {
					table = $('#vehicle_dashBoard_deviation_data');
					// $("#tbl_cnt").html("Distance(Meters)");
					$("#alert_dashBoard_data").removeClass("collapse");
					$("#alert_dashBoard_data").addClass("expand");
					$("#alertDev").attr("style", "display:''");
					$("#alertLATE").attr("style", "display:none");
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
					$("#busbunching").attr("style", "display:none");
					$("#HA").attr("style", "display:none");
					$("#HD").attr("style", "display:none");
					$("#alert12").attr("style", "display:none");
					$("#alert13").attr("style", "display:none");
					$("#alert14").attr("style", "display:none");
					window.scrollTo(0, 500);
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
						"sAjaxSource" : "alertPerticular.action?alertID="
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
							'#vehicle_dashBoard_deviation_data_wrapper .dataTables_filter input')
							.addClass("form-control input-small input-inline"); // modify
					// table
					jQuery(
							'#vehicle_dashBoard_deviation_data_wrapper .dataTables_length select')
							.addClass("form-control input-xsmall input-inline"); // modify
					// table
//					$("#vehicle_chart_alert").removeClass("collapse");
//					$("#vehicle_chart_alert").addClass("expand");
//					$("#alert_vehicle_display_chart").attr("style",
//							"display:none");

				}
				// Box Tampering Alert
				if (obj.series.status == "Tampering") {
					table = $('#vehicle_dashBoard_tampering_data');
					// $("#tbl_cnt").html("Distance(Meters)");
					$("#alerttampering").attr("style", "display:''");
					$("#alertDev").attr("style", "display:none");
					$("#alert1").attr("style", "display:none");
					$("#sosdetails").attr("style", "display:none");
					$("#alertLATE").attr("style", "display:none");
					$("#alert2").attr("style", "display:none");
					$("#alert3").attr("style", "display:none");
					$("#alert4").attr("style", "display:none");
					$("#alert5").attr("style", "display:none");
					$("#vehicle").attr("style", "display:none");
					$("#overSpeeding").attr("style", "display:none");
					$("#vehicleOIB").attr("style", "display:none");
					$("#busbunching").attr("style", "display:none");
					$("#HA").attr("style", "display:none");
					$("#HD").attr("style", "display:none");
					$("#alert12").attr("style", "display:none");
					$("#alert13").attr("style", "display:none");
					$("#alert14").attr("style", "display:none");
					window.scrollTo(0, 500);
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
						"sAjaxSource" : "alertPerticular.action?alertID="
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
							'#vehicle_dashBoard_tampering_data_wrapper .dataTables_filter input')
							.addClass("form-control input-small input-inline"); // modify
					// table
					jQuery(
							'#vehicle_dashBoard_tampering_data_wrapper .dataTables_length select')
							.addClass("form-control input-xsmall input-inline"); // modify
					// table
//					$("#vehicle_chart_alert").removeClass("collapse");
//					$("#vehicle_chart_alert").addClass("expand");
//					$("#alert_vehicle_display_chart").attr("style",
//							"display:none");

				}

				// Early Arrival
				if (obj.series.status == "Early Arrival") {
					document.getElementById("withoutbusstop").style.display = 'none';
					divId = document.getElementById("alert1");
					divId.style.display = '';
					table1 = $('#vehicle_earlyArrival_data');
					alertId = "Early Arrival";
					$('#alert_dashBoard_data').dataTable().fnClearTable();
					$('#alert_dashBoard_data').dataTable().fnDestroy();
					$("#alert_dashBoard_data").removeClass("collapse");
					$("#alert_dashBoard_data").addClass("expand");
					$("#alertLATE").attr("style", "display:none");
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
					$("#busbunching").attr("style", "display:none");
					$("#HA").attr("style", "display:none");
					$("#HD").attr("style", "display:none");
					$("#alert12").attr("style", "display:none");
					$("#alert13").attr("style", "display:none");
					$("#alert14").attr("style", "display:none");
					window.scrollTo(0, 500);
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
				if (obj.series.status == "Late Dept") {
					divId = document.getElementById("alertLATE");
					divId.style.display = '';
					table2 = $('#vehicle_lateDeparturedata_data');
					alertId = "Late Dept";
					$("#alert").attr("style", "display:none");
					$("#alert1").attr("style", "display:none");
					$("#alert2").attr("style", "display:none");
					$("#alert3").attr("style", "display:none");
					$("#sosdetails").attr("style", "display:none");
					$("#alert4").attr("style", "display:none");
					$("#alert5").attr("style", "display:none");
					$("#vehicle").attr("style", "display:none");
					$("#alertDev").attr("style", "display:none");
					$("#overSpeeding").attr("style", "display:none");
					$("#alerttampering").attr("style", "display:none");
					$("#vehicleOIB").attr("style", "display:none");
					$("#busbunching").attr("style", "display:none");
					$("#HA").attr("style", "display:none");
					$("#HD").attr("style", "display:none");
					$("#alert12").attr("style", "display:none");
					$("#alert13").attr("style", "display:none");
					$("#alert14").attr("style", "display:none");
					window.scrollTo(0, 500);
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
							'#vehicle_lateDeparturedata_data_wrapper .dataTables_filter input')
							.addClass("form-control input-small input-inline"); // modify
					// table
					jQuery(
							'#vehicle_lateDeparturedata_data_wrapper .dataTables_length select')
							.addClass("form-control input-xsmall input-inline"); // modify
					// table
					jQuery(
							'#vehicle_lateDeparturedata_data_wrapper .dataTables_length select')
							.addClass("form-control input-xsmall input-inline"); // modify

//					$("#vehicle_chart_alert").removeClass("collapse");
//					$("#vehicle_chart_alert").addClass("expand");
//					$("#alert_vehicle_display_chart").attr("style",
//							"display:none");

				}
				
				
				// Late Departure
				if (obj.series.status == "Sch.Not Departed") {
					divId = document.getElementById("alert2");
					divId.style.display = '';
					table2 = $('#vehicle_lateDeparture_data');
					alertId = "Delayed Dept";
					$("#alert").attr("style", "display:none");
					$("#alertLATE").attr("style", "display:none");
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
					$("#busbunching").attr("style", "display:none");
					$("#HA").attr("style", "display:none");
					$("#HD").attr("style", "display:none");
					$("#alert12").attr("style", "display:none");
					$("#alert13").attr("style", "display:none");
					$("#alert14").attr("style", "display:none");
					window.scrollTo(0, 500);
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
						"sAjaxSource" : "getDepotDelayedDepartureTime.action?alertID="
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

//					$("#vehicle_chart_alert").removeClass("collapse");
//					$("#vehicle_chart_alert").addClass("expand");
//					$("#alert_vehicle_display_chart").attr("style",
//							"display:none");

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
					$("#alertLATE").attr("style", "display:none");
					$("#sosdetails").attr("style", "display:none");
					$("#vehicle").attr("style", "display:none");
					$("#alertDev").attr("style", "display:none");
					$("#overSpeeding").attr("style", "display:none");
					$("#alerttampering").attr("style", "display:none");
					$("#tamperingdetails").attr("style", "display:none");
					$("#vehicleOIB").attr("style", "display:none");
					$("#busbunching").attr("style", "display:none");
					$("#HA").attr("style", "display:none");
					$("#HD").attr("style", "display:none");
					$("#alert12").attr("style", "display:none");
					$("#alert13").attr("style", "display:none");
					$("#alert14").attr("style", "display:none");
					window.scrollTo(0, 500);
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

//					$("#vehicle_chart_alert").removeClass("collapse");
//					$("#vehicle_chart_alert").addClass("expand");
//					$("#alert_vehicle_display_chart").attr("style",
//							"display:none");

				}

				// SOS
				if (obj.series.status == "SoS") {
					divId = document.getElementById("alert3");
					table3 = $('#sosAlert_data');
					divId.style.display = '';
					alertId = "SoS";
					$("#alert").attr("style", "display:none");
					$("#alert1").attr("style", "display:none");
					$("#alertLATE").attr("style", "display:none");
					$("#alert2").attr("style", "display:none");
					$("#sosdetails").attr("style", "display:none");
					$("#alert4").attr("style", "display:none");
					$("#alert5").attr("style", "display:none");
					$("#vehicle").attr("style", "display:none");
					$("#alertDev").attr("style", "display:none");
					$("#overSpeeding").attr("style", "display:none");
					$("#alerttampering").attr("style", "display:none");
					$("#vehicleOIB").attr("style", "display:none");
					$("#busbunching").attr("style", "display:none");
					$("#HA").attr("style", "display:none");
					$("#HD").attr("style", "display:none");
					window.scrollTo(0, 500);
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

//					$("#vehicle_chart_alert").removeClass("collapse");
//					$("#vehicle_chart_alert").addClass("expand");
//					$("#alert_vehicle_display_chart").attr("style",
//							"display:none");

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
					$("#alertLATE").attr("style", "display:none");
					$("#alert5").attr("style", "display:none");
					$("#vehicle").attr("style", "display:none");
					$("#alertDev").attr("style", "display:none");
					$("#alerttampering").attr("style", "display:none");
					$("#vehicleOIB").attr("style", "display:none");
					$("#busbunching").attr("style", "display:none");
					$("#HA").attr("style", "display:none");
					$("#HD").attr("style", "display:none");
					$("#alert12").attr("style", "display:none");
					$("#alert13").attr("style", "display:none");
					$("#alert14").attr("style", "display:none");
					
					window.scrollTo(0, 500);
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

//					$("#vehicle_chart_alert").removeClass("collapse");
//					$("#vehicle_chart_alert").addClass("expand");
//					$("#alert_vehicle_display_chart").attr("style",
//							"display:none");

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
					$("#alertLATE").attr("style", "display:none");
					$("#alert5").attr("style", "display:none");
					$("#vehicle").attr("style", "display:none");
					$("#alertDev").attr("style", "display:none");
					$("#alerttampering").attr("style", "display:none");
					$("#vehicleOIB").attr("style", "display:none");
					$("#overSpeeding").attr("style", "display:none");
					$("#HA").attr("style", "display:none");
					$("#HD").attr("style", "display:none");
					$("#alert12").attr("style", "display:none");
					$("#alert13").attr("style", "display:none");
					$("#alert14").attr("style", "display:none");
					window.scrollTo(0, 500);
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

//					$("#vehicle_chart_alert").removeClass("collapse");
//					$("#vehicle_chart_alert").addClass("expand");
//					$("#alert_vehicle_display_chart").attr("style",
//							"display:none");

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
					$("#alertLATE").attr("style", "display:none");
					$("#alert5").attr("style", "display:none");
					$("#vehicle").attr("style", "display:none");
					$("#alertDev").attr("style", "display:none");
					$("#alerttampering").attr("style", "display:none");
					$("#vehicleOIB").attr("style", "display:none");
					$("#overSpeeding").attr("style", "display:none");
					window.scrollTo(0, 500);
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

//					$("#vehicle_chart_alert").removeClass("collapse");
//					$("#vehicle_chart_alert").addClass("expand");
//					$("#alert_vehicle_display_chart").attr("style",
//							"display:none");

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
					$("#alertLATE").attr("style", "display:none");
					$("#alert5").attr("style", "display:none");
					$("#vehicle").attr("style", "display:none");
					$("#alertDev").attr("style", "display:none");
					$("#alerttampering").attr("style", "display:none");
					$("#vehicleOIB").attr("style", "display:none");
					$("#overSpeeding").attr("style", "display:none");
					window.scrollTo(0, 500);
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

//					$("#vehicle_chart_alert").removeClass("collapse");
//					$("#vehicle_chart_alert").addClass("expand");
//					$("#alert_vehicle_display_chart").attr("style",
//							"display:none");

				}
				
				// CWS
				if (obj.series.status == "CWS") {
					divId = document.getElementById("vehicleCWS");
					divId.style.display = '';
					table4 = $('#alert_dashBoardCWS_data');
					alertId = "CWS";
					$("#alert").attr("style", "display:none");
					$("#alert1").attr("style", "display:none");
					$("#alert2").attr("style", "display:none");
					$("#alert3").attr("style", "display:none");
					$("#alert5").attr("style", "display:none");
					$("#alertLATE").attr("style", "display:none");
					$("#sosdetails").attr("style", "display:none");
					$("#vehicle").attr("style", "display:none");
					$("#alertDev").attr("style", "display:none");
					$("#overSpeeding").attr("style", "display:none");
					$("#alerttampering").attr("style", "display:none");
					$("#tamperingdetails").attr("style", "display:none");
					$("#vehicleOIB").attr("style", "display:none");
					$("#busbunching").attr("style", "display:none");
					$("#HA").attr("style", "display:none");
					$("#HD").attr("style", "display:none");
					$("#alert12").attr("style", "display:none");
					$("#alert13").attr("style", "display:none");
					$("#alert14").attr("style", "display:none");
					$("#vehicleDWS").attr("style", "display:none");
					window.scrollTo(0, 500);
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
						"sAjaxSource" : "vehiclePerticular.action?vehicleStatus="
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
							'#alert_dashBoardCWS_data_wrapper .dataTables_filter input')
							.addClass("form-control input-small input-inline"); // modify
					// table
					jQuery(
							'#alert_dashBoardCWS_data_wrapper .dataTables_length select')
							.addClass("form-control input-xsmall input-inline"); // modify

//					$("#vehicle_chart_alert").removeClass("collapse");
//					$("#vehicle_chart_alert").addClass("expand");
//					$("#alert_vehicle_display_chart").attr("style",
//							"display:none");

				}
				
				// DWS
				if (obj.series.status == "DWS") {
					divId = document.getElementById("vehicleDWS");
					divId.style.display = '';
					table4 = $('#alert_dashBoardDWS_data');
					alertId = "DWS";
					$("#alert").attr("style", "display:none");
					$("#alert1").attr("style", "display:none");
					$("#alert2").attr("style", "display:none");
					$("#alert3").attr("style", "display:none");
					$("#alert5").attr("style", "display:none");
					$("#alertLATE").attr("style", "display:none");
					$("#sosdetails").attr("style", "display:none");
					$("#vehicle").attr("style", "display:none");
					$("#alertDev").attr("style", "display:none");
					$("#overSpeeding").attr("style", "display:none");
					$("#alerttampering").attr("style", "display:none");
					$("#tamperingdetails").attr("style", "display:none");
					$("#vehicleOIB").attr("style", "display:none");
					$("#busbunching").attr("style", "display:none");
					$("#HA").attr("style", "display:none");
					$("#HD").attr("style", "display:none");
					$("#alert12").attr("style", "display:none");
					$("#alert13").attr("style", "display:none");
					$("#alert14").attr("style", "display:none");
					$("#vehicleCWS").attr("style", "display:none");
					window.scrollTo(0, 500);
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
						"sAjaxSource" : "vehiclePerticular.action?vehicleStatus="
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

//					$("#vehicle_chart_alert").removeClass("collapse");
//					$("#vehicle_chart_alert").addClass("expand");
//					$("#alert_vehicle_display_chart").attr("style",
//							"display:none");

				}
				
				
				document.getElementById("vehicle").style.display = 'none';
				if(obj.series.status!='Deviated'){
				$("#vstatus").html(
						"<b>Alert Data For " + " (" + obj.series.status
								+ ")</b>");}else{
									var stst=obj.series.status+"/UnMapped Vehicles";
									$("#vstatus").html(
											"<b>Alert Data For " + " (" + stst
													+ ")</b>");
								}
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

		},
		initPieCharts : function() {
			/*
			 * loadIntervalId = setInterval(function() { getVehicleData(); },
			 * 0000);
			 */
			function getVehicleData() {
				var vehicleData = $.ajax({
					url : "getVehicleData!getVehicleDetails",
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
				getVehicleData();
			}, 600000);

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
				percent = parseFloat(obj.series.percent).toFixed(2);
				var table = $('#alert_dashBoard_data');
				var divId = document.getElementById("vehicle");
				divId.style.display = '';
				$('#alert_dashBoard_data').dataTable().fnClearTable();
				$('#alert_dashBoard_data').dataTable().fnDestroy();
				$("#alert_dashBoard_data").removeClass("collapse");
				$("#alert_dashBoard_data").addClass("expand");
				
				$("#alertDev").attr("style", "display:none");
				$("#sosdetails").attr("style", "display:none");
				$("#alerttampering").attr("style", "display:none");
				$("#vehicleDetailsDev").attr("style", "display:none");
				$("#alert").attr("style", "display:none");
				$("#alert2").attr("style", "display:none");
				$("#alert3").attr("style", "display:none");
				$("#alert4").attr("style", "display:none");
				$("#alert5").attr("style", "display:none");
				$("#alert1").attr("style", "display:none");
				$("#vehicleOIB").attr("style", "display:none");
				$("#overSpeeding").attr("style", "display:none");
				$("#alert12").attr("style", "display:none");
				$("#alertLATE").attr("style", "display:none");
				$("#alert13").attr("style", "display:none");
				$("#alert14").attr("style", "display:none");
				$("#vehicleDWS").attr("style", "display:none");
				$("#vehicleCWS").attr("style", "display:none");
				
				$("#vstatus").html(
						"Vehicle Data" + "(" + obj.series.status + ")");
				document.getElementById("alert").style.display = 'none';
				console.log(obj.series.status);
				if(obj.series.status!='Int.Battery'){
					$("#alert_dashBoard_data").attr("style", "display:''");
				var oTable = table.dataTable({
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					"iDisplayLength" : 5,
					"sAjaxSource" : "vehiclePerticular.action?vehicleStatus="
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
				jQuery('#alert_dashBoard_data_wrapper .dataTables_filter input')
						.addClass("form-control input-small input-inline"); // modify
				// table
				jQuery(
						'#alert_dashBoard_data_wrapper .dataTables_length select')
						.addClass("form-control input-xsmall input-inline"); // modify
				// table
			}
				
				else if(obj.series.status='Int.Battery'){
				$("#alert_dashBoard_data").attr("style", "display:none");
				$("#vehicleOIB").attr("style", "display:''");
				var table = $('#alert_dashBoardOIB_data');
				var oTable = table.dataTable({
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					"iDisplayLength" : 5,
					"sAjaxSource" : "vehiclePerticular.action?vehicleStatus=Int.Battery"
							,
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
				jQuery('#alert_dashBoardOIB_data_wrapper .dataTables_filter input')
						.addClass("form-control input-small input-inline"); // modify
				// table
				jQuery(
						'#alert_dashBoardOIB_data_wrapper .dataTables_length select')
						.addClass("form-control input-xsmall input-inline"); // modify
				// table
			}
				
//				else if(obj.series.status='CWS'){
//					$("#alert_dashBoard_data").attr("style", "display:none");
//					$("#vehicleCWS").attr("style", "display:''");
//					var table = $('#alert_dashBoardCWS_data');
//					var oTable = table.dataTable({
//						"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
//						// per
//						// page
//						// values
//						// here
//						],
//						// set the initial value
//						"iDisplayLength" : 5,
//						"sAjaxSource" : "vehiclePerticular.action?vehicleStatus=CWS"
//								,
//						"sPaginationType" : "bootstrap",
//						"bDestroy" : true,
//
//						"oLanguage" : {
//							"sLengthMenu" : "_MENU_ records",
//							"oPaginate" : {
//								"sPrevious" : "Prev",
//								"sNext" : "Next"
//							}
//						},
//						"aoColumnDefs" : [ {
//							'bSortable' : false,
//							'aTargets' : [ 0 ]
//						} ]
//					});
//					jQuery('#alert_dashBoardCWS_data_wrapper .dataTables_filter input')
//							.addClass("form-control input-small input-inline"); // modify
//					// table
//					jQuery(
//							'#alert_dashBoardCWS_data_wrapper .dataTables_length select')
//							.addClass("form-control input-xsmall input-inline"); // modify
//					// table
//				}
//				else if(obj.series.status='DWS'){
//					$("#alert_dashBoard_data").attr("style", "display:none");
//					$("#vehicleDWS").attr("style", "display:''");
//					var table = $('#alert_dashBoardDWS_data');
//					var oTable = table.dataTable({
//						"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
//						// per
//						// page
//						// values
//						// here
//						],
//						// set the initial value
//						"iDisplayLength" : 5,
//						"sAjaxSource" : "vehiclePerticular.action?vehicleStatus=DWS"
//								,
//						"sPaginationType" : "bootstrap",
//						"bDestroy" : true,
//
//						"oLanguage" : {
//							"sLengthMenu" : "_MENU_ records",
//							"oPaginate" : {
//								"sPrevious" : "Prev",
//								"sNext" : "Next"
//							}
//						},
//						"aoColumnDefs" : [ {
//							'bSortable' : false,
//							'aTargets' : [ 0 ]
//						} ]
//					});
//					jQuery('#alert_dashBoardDWS_data_wrapper .dataTables_filter input')
//							.addClass("form-control input-small input-inline"); // modify
//					// table
//					jQuery(
//							'#alert_dashBoardDWS_data_wrapper .dataTables_length select')
//							.addClass("form-control input-xsmall input-inline"); // modify
//					// table
//				}
/*				else if(obj.series.status='Scrap'){
					$("#alert_dashBoard_data").attr("style", "display:none");
					$("#vehicleS").attr("style", "display:''");
					var table = $('#alert_dashBoardS_data');
					var oTable = table.dataTable({
						"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
						// per
						// page
						// values
						// here
						],
						// set the initial value
						"iDisplayLength" : 5,
						"sAjaxSource" : "vehiclePerticular.action?vehicleStatus=Scrap"
								,
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
					jQuery('#alert_dashBoardS_data_wrapper .dataTables_filter input')
							.addClass("form-control input-small input-inline"); // modify
					// table
					jQuery(
							'#alert_dashBoardS_data_wrapper .dataTables_length select')
							.addClass("form-control input-xsmall input-inline"); // modify
					// table
				}*/
				
			}

		}
		

	};

}();

function loadActiveVehicles() {
	var table = $('#alert_dashBoard_data');
	var divId = document.getElementById("vehicle");
	var flag='true';
	document.getElementById("vehicle").style.display = '';
	$("#vstatus").html("<b>Vehicle Data" + "(" + "Not Reported" + ")</b>");
	var oTable = table.dataTable({
		"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
		// per
		// page
		// values
		// here
		],
		// set the initial value
		"iDisplayLength" : 5,
		"sAjaxSource" : "vehiclePerticular.action?vehicleStatus=NRD&limit="+flag,
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
function getVehicleData() {
	var vehicleData = $.ajax({
		url : "getVehicleData!getVehicleDetails",
		dataType : 'json',
		contentType : "application/json",
		global : false,
		async : true,
		success : function(response) {
			// alert(response);
			var plotVehicleResponseData = response;
			plotVehicleData(plotVehicleResponseData);
			return response;

		}
	}).responseText;

	var parseVehicleData = jQuery.parseJSON(vehicleData);

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
	window.scrollTo(0,500);
	var baralertData = $.ajax({
		url : "getAlertData!getAlertDetails",
		dataType : 'json',
		contentType : "application/json",
		global : false,
		async : true,
		success : function(response) {
			baralertData = response;
			totalalertData = $.ajax({
				url : "getAlertData!gettotalAlertDetails",
				contentType : "application/json",
				dataType : 'json',
				global : false,
				async : true,
				success : function(response) {
					getBarChartInfo(baralertData, response);
					//$("#alert_dashBoard_data").removeClass("collapse");
					//$("#alert_dashBoard_data").addClass("expand");
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
	var status = []; var test;
	// bar chart:
	for ( var i = 0; i < barseries; i++) {
		ticks.push([ i, barparseData["aaData"][i]["1"] ]);
		avlData.push(barparseData["aaData"][i]["1"]);
		data.push({
			// label : barparseData["aaData"][i]["2"] + "-"
			// + (barparseData["aaData"][i]["0"]),
			data : [ [ i, barparseData["aaData"][i]["0"] ] ],
			status : barparseData["aaData"][i]["1"],
			test : barparseData["aaData"][i]["0"]
		});
	}
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
				barWidth : 0.8,
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
			axisLabelPadding : 3
		},
		grid : {
			hoverable : true,
			clickable : true
		}
	};
	$.plot($("#barchart_alert"), data, options);
	var somePlot = $.plot($("#barchart_alert"), data, options);
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
	
	// $("#vehicle_chart_alert").addClass("collapse");

}
/*
 * Added by Rasmi ranjan Das FOR SYNCHRONZING PLOTTING VEHICLE DATA WITH OTHER
 * MENU DISPLAY
 */
//changed by Aditi removed Scrap,CWS ,DWS
function plotVehicleData(vehicleData) {
	var data = [];
	// var series = Math.floor(Math.random() * 10) + 1;
	series = vehicleData["aaData"].length;

	for ( var i = 0; i < series-4; i++) {
		data[i] = {
			label : vehicleData["aaData"][i]["2"] + "-"
					+ (vehicleData["aaData"][i]["0"]),
			data : vehicleData["aaData"][i]["1"] ,
			status : vehicleData["aaData"][i]["2"]
		}
	}
	$('#totalVehicle').text("Total Vehicles :"+(vehicleData["aaData"][6]["0"]-vehicleData["aaData"][5][0]));
	$('#totalActiveVehicle').text("Total Active Vehicles :"+(vehicleData["aaData"][0]["0"] ));
	$('#totalNrdVehicle').text("Total NRD Vehicles :"+vehicleData["aaData"][1]["0"]);
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
										return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
												+ arr[1] + '</div>';
									},
									background : {
										opacity : 0.8
									}
								}
							}

						},
						colors : [ "#088A08", "#FF0000",'#4d4da7','#DB7093','#000000','#9440ed',"#4da74d","#cb4b4b",'#FF0FFF', "#afd8f8", 
								, "#9440ed",  '#000000' ],
						grid : {
							hoverable : true,
							clickable : true
						}

					});



}
function getVehicleSkipStopData(device_id,shift) {
	$("#alert10").attr("style", "display:none");
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
		"sAjaxSource" : "alertSkippedStopDetails.action?alertID=" + device_id +"&dutyType="+shift,
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


function getVehicleOverSpeedData(device_id) {
	document.getElementById("alert10").style.display = '';
	var oTable = $('#OveSpeedAlert_data1').dataTable({
		"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
		// per
		// page
		// values
		// here
		],
		// set the initial value
		"iDisplayLength" : 5,
		"sAjaxSource" : "alertOverSpeedDataDetails.action?alertID=" + device_id,
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
	jQuery('#OveSpeedAlert_data1_wrapper .dataTables_filter input').addClass(
			"form-control input-small input-inline"); // modify
	// table
	jQuery('#OveSpeedAlert_data1_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline"); // modify
}



//sos Incident call fuction

function getVehicleSosChangeDetails(vehNum, deviceId, depot_id,token,schedule_no,gpstime,lat,lng,shift,depot_name,sch_name) {	
	document.getElementById("accident_type").style.display = 'none';
	document.getElementById("breakdown_type").style.display = 'none';
//	console.log("1----"+schedule_no);
	$("#vehicle_no").val(vehNum);
	$("#device_id").val(deviceId);
	$("#gpstime").val(gpstime);
	$("#depotnm").val(depot_name);
	$("#scheduleno").val(sch_name);
	$("#scheduleId").val(schedule_no);
	$("#vehicle_status").val(0);
	$("#description").val('');
	
	$("#lat").val(lat);
	$("#lng").val(lng);
	$("#depot_id").val(depot_id);
	//alert(depot_id);
	//alert(token);
	$("#shift").val(shift);
	$("#drivertoken").val(token);
	
	
	//alert("vehicle_status: "+$("#vehicle_status").val());
}

//end

function getLateDeptReasonData(vehNum, deviceId, schNo, tktDate) {	
//	document.getElementById("accident_type").style.display = 'none';
//	document.getElementById("breakdown_type").style.display = 'none';
	console.log("1----"+deviceId);
	$("#vehicleNo").val(vehNum);
	$("#deviceIdLate").val(deviceId);
	
//	alert(vehNum+"   "+deviceId);
}


function getEarlyArrivalReasonData(vehNum, deviceId, tktDate) {	
//	document.getElementById("accident_type").style.display = 'none';
//	document.getElementById("breakdown_type").style.display = 'none';
//	console.log("1----"+schedule_no);
	$("#vehicleNo1").val(vehNum);
	$("#deviceid1").val(deviceId);
	
//	alert(vehNum+"   "+deviceId);
}

function getStationaryReasonData(vehNum, deviceId, tktDate) {	
//	document.getElementById("accident_type").style.display = 'none';
//	document.getElementById("breakdown_type").style.display = 'none';
	console.log("1----"+deviceId);
	$("#vehicleNum").val(vehNum);
	$("#device_ids").val(deviceId);
	
//	alert(vehNum+"   "+deviceId);
}


function getSkipStopReasonData(vehNum, deviceId, tktDate) {	
//	document.getElementById("accident_type").style.display = 'none';
//	document.getElementById("breakdown_type").style.display = 'none';
	console.log("1----"+vehNum);
	$("#vehicleNo3").val(vehNum);
	$("#device_id").val(deviceId);
	
//	alert(vehNum+"   "+deviceId);
}

function getSOSReasonData(vehNum, deviceId, driverToken) {	
//	document.getElementById("accident_type").style.display = 'none';
//	document.getElementById("breakdown_type").style.display = 'none';
	console.log("1----"+vehNum);
	$("#vehicleNo4").val(vehNum);
	$("#device_id4").val(deviceId);
	
//	alert(vehNum+"   "+deviceId);
}



function getDeviatedReasonData(vehNum, deviceId, orgName) {	
//	document.getElementById("accident_type").style.display = 'none';
//	document.getElementById("breakdown_type").style.display = 'none';
	console.log("1----"+vehNum);
	$("#vehicleNo5").val(vehNum);
	$("#deviceid5").val(deviceId);
	
//	alert(vehNum+"   "+deviceId);
}


function getSchNotDepartedReason(vehNum, deviceId, orgName) {	
//	document.getElementById("accident_type").style.display = 'none';
//	document.getElementById("breakdown_type").style.display = 'none';
	console.log("1----"+vehNum);
	$("#vehicleNo6").val(vehNum);
	$("#device_id").val(deviceId);
	
//	alert(vehNum+"   "+deviceId);
}

function getNRDReasonData(vehNum, deviceId) {	
//	document.getElementById("accident_type").style.display = 'none';
//	document.getElementById("breakdown_type").style.display = 'none';
	console.log("1----"+vehNum);
	$("#vehicleNo7").val(vehNum);
	$("#deviceid7").val(deviceId);
	
//	alert(vehNum+"   "+deviceId);
}



function getVehicleSkipStopDataPerticular(device_id,date,dutyId) {
	document.getElementById("alert5").style.display = '';
	var givenDate=document.getElementById("alert5").value;
	console.log("==== "+dutyId);
	var oTable = $('#skipStopAlert_data1').dataTable({
		"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
		// per
		// page
		// values
		// here
		],
		// set the initial value
		"iDisplayLength" : 5,
		"sAjaxSource" : "alertSkippedStopDetails.action?alertID=" + device_id +"&givenDate=" +date +"&dutyType="+dutyId,
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

function getTotalSosCount(device_id,fromDate){
	console.log("in js totla count");
	$("#sosdetails").attr("style", "display:''");
	var oTable = $('#sosDetails').dataTable({
		"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
		// per
		// page
		// values
		// here
		],
		// set the initial value
		"iDisplayLength" : 5,
		"sAjaxSource" : "getSosDetails.action?device_ID=" + device_id+ "&fromDate=" + fromDate,
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
	jQuery('#sosDetails_wrapper .dataTables_filter input').addClass(
			"form-control input-small input-inline"); // modify
	// table
	jQuery('#sosDetails_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline"); // modify
	
}
$('#viewVehicleAlertConfig').dataTable({
	"aaSorting" : [ [ 1, 'asc' ] ],
	"aLengthMenu" : [ [ 10, 25, 50, 100 ], [ 10, 25, 50, 100 ], // change
	// per
	// page
	// values
	// here
	],
	// set the initial value
	"iDisplayLength" : 10,
	"bProcessing" : true,
	"bServerSide" : true,
	"sAjaxSource" : "showVehicleAlertConfigList.action",
	"sPaginationType" : "bootstrap",
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
	}, {
		"bSearchable" : false,
		"aTargets" : [ 0 ]
	} ]

});