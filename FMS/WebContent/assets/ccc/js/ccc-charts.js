var baralertData;
var Charts_ccc = function() {
	var loadIntervalId = null;
	var loadBarchartIntervalId = null;
	var loadIntervalId = null;
	var loadBarchartIntervalId = null;
	return {
		// main function to initiate the module
		init : function() {
			Metronic.addResizeHandler(function() {
				//Charts.initPieCharts();
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
			/*
			 * loadIntervalId = setInterval(function() { getVehicleData(); },
			 * 0000);
			 */
			function getVehicleData() {
				//alert("response"+response);
				var vehicleData = $.ajax({
					url : "getVehicleData!getVehicleDetails",
					dataType : 'json',
					contentType : "application/json",
					global : false,
					async : false,
					success : function(response) {
						//alert("response"+response);
						var plotVehicleResponseData = response;
						plotVehicleData(plotVehicleResponseData);
						//plotVehicleData2(plotVehicleResponseData);
						//plotVehicleData3(plotVehicleResponseData);
						//plotVehicleData4(plotVehicleResponseData);
						return response;
					}
				}).responseText;
			}

			if (loadIntervalId != null) {
				clearInterval(loadIntervalId);
			}
			;
		/*loadIntervalId = setInterval(function() {
				getVehicleData();
			}, 10000);*/

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
				$("#Vehicle_data_id").attr("style", "display:none");
				$("#alert_ETM_display").attr("style", "display:none");
				$("#alert_employee_display").attr("style", "display:none");
				$("#alert_CompletedTrip_display").attr("style", "display:none");
				$("#alert_DeviatedTrip_display").attr("style", "display:none");
				$("#alert_Partial_display").attr("style", "display:none");
				$("#alert_employee_display").attr("style", "display:none");
				$("#vehicleOIB").attr("style", "display:none");
				$("#alert_ETMFault_display").attr("style", "display:none");
				$("#alert_scheduleCompletedTrip_display").attr("style", "display:none");
				$("#alert_scheduleDeviatedTrip_display").attr("style", "display:none");
				$("#alert_schedulePartial_display").attr("style", "display:none");
				
				$("#alert_crewCompletedTrip_display").attr("style", "display:none");
				$("#alert_crewDeviatedTrip_display").attr("style", "display:none");
				$("#alert_crewPartial_display").attr("style", "display:none");
				
				$("#alert_Online_Depot_display").attr("style", "display:none");
				$("#alert_Offline_Depot_display").attr("style", "display:none");

				$("#alert_MissedTrip_display").attr("style", "display:none");
				$("#alert_DelayTrip_display").attr("style", "display:none");
				
				 window.scrollBy(0,700);
				
//				$("#vstatus").html(
//						"Vehicle Data" + "(" + obj.series.status + ")");
				//document.getElementById("alert").style.display = 'none';
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
			}else{
				$("#vehicle").attr("style", "display:none");
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
				$("#vehicle_chart_alert").removeClass("collapse");
				$("#vehicle_chart_alert").addClass("expand");
				$("#alert_vehicle_display_chart").attr("style", "display:none");
			}

		},
		//initchartForAccident
		
		initPieAccidentCharts : function() {
			
			/*
			 * loadIntervalId = setInterval(function() { getVehicleData(); },
			 * 0000);
			 */
				function getAccidentData() {
				
				var vehicleData = $.ajax({
					url : "getAccidentpiedata!getVehicleCount",
					dataType : 'json',
					contentType : "application/json",
					global : false,
					async : true,
					success : function(response) {
						// alert(response);
						var plotVehicleResponseData = response;
						plotVehicleData1(plotVehicleResponseData);
						return response;

					}
				}).responseText;
			}
			if (loadIntervalId != null) {
				clearInterval(loadIntervalId);
			}
			;
			/*loadIntervalId = setInterval(function() {
				getAccidentData();
			}, 10000);*/

			$("#alertspie3").bind("plothover", pieHoveralert);
			$("#alertspie3").bind("plotclick", pieClickalert);

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
				
				
				
				//$("#Vehicle_data_id").attr("style", "display:none");
				$("#alert_ETM_display").attr("style", "display:none");
				$("#alert_employee_display").attr("style", "display:none");
				$("#alert_CompletedTrip_display").attr("style", "display:none");
				$("#alert_DeviatedTrip_display").attr("style", "display:none");
				$("#alert_Partial_display").attr("style", "display:none");
				 $("#alert_employee_display").attr("style", "display:none");
				 $("#alert_ETMFault_display").attr("style", "display:none");
				 $("#alert_scheduleCompletedTrip_display").attr("style", "display:none");
				 $("#alert_scheduleDeviatedTrip_display").attr("style", "display:none");
				 $("#alert_schedulePartial_display").attr("style", "display:none");
				 
				 $("#alert_crewCompletedTrip_display").attr("style", "display:none");
				 $("#alert_crewDeviatedTrip_display").attr("style", "display:none");
				 $("#alert_crewPartial_display").attr("style", "display:none");
				 $("#alert_Online_Depot_display").attr("style", "display:none");
				 $("#alert_Offline_Depot_display").attr("style", "display:none");
				$("#vehicle").attr("style", "display:none");
				$("#alert_MissedTrip_display").attr("style", "display:none");
				$("#alert_DelayTrip_display").attr("style", "display:none");
				//alert("status---"+obj.series.status);
				//alert("status---"+obj.series.lable);
				 window.scrollBy(0,700);
			
				if (!obj)
					return;
				percent = parseFloat(obj.series.percent).toFixed(2);
				var table = $('#vehicle_count_id');
				$("#Vehicle_data_id").attr("style", "display:''");
				//console.log(obj.series.status);
				if(obj.series.status=='vehicle'){
					//alert("minor"+obj.series.status);
				
					var table = $('#vehicle_count_id');
					var oTable = table.dataTable({
						"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
						// per
						// page
						// values
						// here
						],
						// set the initial value
						"iDisplayLength" : 5,
						"sAjaxSource" : "getVehicleDepotwiseCount.action",
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
					jQuery('#accidentdata_id_wrapper .dataTables_filter input').addClass(
							"form-control input-medium input-inline"); // modify table
					// search input
					jQuery('#accidentdata_id_wrapper .dataTables_length select').addClass(
							"form-control input-xsmall input-inline");
					
					
			} if(obj.series.status=='vehicle_Accident'){
				//$("#accident_data").attr("style", "display:none");
				$("#vehicle_count_id").attr("style", "display:''");
				var table = $('#vehicle_count_id');
				var oTable = table.dataTable({
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					"iDisplayLength" : 5,
					"sAjaxSource" : "getAccidentcountdata.action",
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
				
				// table
			}
			if(obj.series.status=='vehicle_Breakdown'){
				//$("#accident_data").attr("style", "display:none");
				$("#vehicle_count_id").attr("style", "display:''");
				var table = $('#vehicle_count_id');
				var oTable = table.dataTable({
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					"iDisplayLength" : 5,
					"sAjaxSource" : "getBreakdownpiedata.action",
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
				
				// table
			}
			}

		},
		
		//InitiBarchartForAccident End
		
		
		//InitPiechartForBreakdown start

		
		initPieBreakdownCharts : function() {
			//alert("hiiiiiiiiiiiiiiiiii");
			/*
			 * loadIntervalId = setInterval(function() { getVehicleData(); },
			 * 0000);
			 */
			function getVehicleBreakdownData() {
				var vehicleData = $.ajax({
					url : "getBreakdownpiedata!getBreakdownpiechart",
					dataType : 'json',
					contentType : "application/json",
					global : false,
					async : true,
					success : function(response) {
						// alert(response);
						var plotVehicleResponseData = response;
						plotVehicleData2(plotVehicleResponseData);
						return response;

					}
				}).responseText;

				var parseVehicleData = jQuery.parseJSON(vehicleData);

				 }
			if (loadIntervalId != null) {
				clearInterval(loadIntervalId);
			}
			;
			/*loadIntervalId = setInterval(function() {
				getAccidentData();
			}, 10000);*/

			$("#alertspie2").bind("plothover", pieHoveralert);
			$("#alertspie2").bind("plotclick", pieClickalert);

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
				var table = $('#breakdowndata_id');
				
				console.log(obj.series.status);
				if(obj.series.status!='UnAvoidable'){
					$("#alert_breakdown_display").attr("style", "display:''");
					
					var table = $('#breakdowndata_id');
					var oTable = table.dataTable({
						"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
						// per
						// page
						// values
						// here
						],
						// set the initial value
						"iDisplayLength" : 5,
						"sAjaxSource" : "getBreakdownstatuswisedata.action?breakdownStatus="
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
					jQuery('#breakdowndata_id_wrapper .dataTables_filter input').addClass(
							"form-control input-medium input-inline"); // modify table
					// search input
					jQuery('#breakdowndata_id_wrapper .dataTables_length select').addClass(
							"form-control input-xsmall input-inline");
					
					
			}else{
				
				//$("#accident_data").attr("style", "display:none");
				$("#alert_breakdown_display").attr("style", "display:''");
				var table = $('#breakdowndata_id');
				var oTable = table.dataTable({
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					"iDisplayLength" : 5,
					"sAjaxSource" : "getBreakdownstatuswisedata.action?breakdownStatus="
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
				jQuery('#breakdowndata_id_wrapper .dataTables_filter input').addClass(
						"form-control input-medium input-inline"); // modify table
				// search input
				jQuery('#breakdowndata_id_wrapper .dataTables_length select').addClass(
						"form-control input-xsmall input-inline");
			}
				/*$("#vehicle_chart_alert").removeClass("collapse");
				$("#vehicle_chart_alert").addClass("expand");
				$("#alert_vehicle_display_chart").attr("style", "display:none");*/
			}

		},
		//InitPiechartForBreakdown end
		
		//employee data start
		
		initPieEmployeeCharts : function() {
			//alert("hiiiiiiiiiiiiiiiiii");
			/*
			 * loadIntervalId = setInterval(function() { getVehicleData(); },
			 * 0000);
			 */
			function getEmployeeData() {
				//$("#piechart_accident_id").attr("style", "display:''");
				//alert("getEmployeeData");
				var vehicleData = $.ajax({
					url : "getConductorDriverpiedata!getDriverConductorCountpiechart",
					dataType : 'json',
					contentType : "application/json",
					global : false,
					async : true,
					success : function(response) {
						// alert(response);
						var plotVehicleResponseData = response;
						//plotVehicleData3(plotVehicleResponseData);
						return response;

					}
				}).responseText;

				var parseVehicleData = jQuery.parseJSON(vehicleData);

				}
			if (loadIntervalId != null) {
				clearInterval(loadIntervalId);
			}
			;
			/*loadIntervalId = setInterval(function() {
				getAccidentData();
			}, 10000);*/

			$("#alertspie4").bind("plothover", pieHoveralert);
			$("#alertspie4").bind("plotclick", pieClickalert);

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
				var table = $('#employeedata_id');
				
				console.log(obj.series.status);
				
					$("#alert_employee_display").attr("style", "display:''");
					
					var table = $('#employeedata_id');
					var oTable = table.dataTable({
						"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
						// per
						// page
						// values
						// here
						],
						// set the initial value
						"iDisplayLength" : 5,
						"sAjaxSource" : "getEmployeestatuswisedata.action?employeestatus="
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
					jQuery('#employeedata_id_wrapper .dataTables_filter input').addClass(
							"form-control input-medium input-inline"); // modify table
					// search input
					jQuery('#employeedata_id_wrapper .dataTables_length select').addClass(
							"form-control input-xsmall input-inline");
					
					
			
			}

		},
		
		//employe data end
		//emt data 
		
		initPieETMCharts : function() {
			//alert("hiiiiiiiiiiiiiiiiii");
			/*
			 * loadIntervalId = setInterval(function() { getVehicleData(); },
			 * 0000);
			 */
			function getETMData() {
				//$("#piechart_accident_id").attr("style", "display:''");
				//alert("getEmployeeData");
				var vehicleData = $.ajax({
					url : "getETMpiedata!getETMCountpiechart",
					dataType : 'json',
					contentType : "application/json",
					global : false,
					async : true,
					success : function(response) {
						// alert(response);
						var plotVehicleResponseData = response;
						plotETMData(plotVehicleResponseData);
						return response;

					}
				}).responseText;
			}
			if (loadIntervalId != null) {
				clearInterval(loadIntervalId);
			}
			;
			/*loadIntervalId = setInterval(function() {
				getAccidentData();
			}, 10000);*/

			$("#alertspie6").bind("plothover", pieHoveralert);
			$("#alertspie6").bind("plotclick", pieClickalert);

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
				
				
				$("#Vehicle_data_id").attr("style", "display:none");
				$("#alert_ETM_display").attr("style", "display:none");
				$("#alert_employee_display").attr("style", "display:none");
				$("#alert_CompletedTrip_display").attr("style", "display:none");
				$("#alert_DeviatedTrip_display").attr("style", "display:none");
				$("#alert_Partial_display").attr("style", "display:none");
				$("#alert_employee_display").attr("style", "display:none");
				$("#vehicleOIB").attr("style", "display:none");
				$("#vehicle").attr("style", "display:none");
				$("#alert_ETMFault_display").attr("style", "display:none");
				$("#alert_scheduleCompletedTrip_display").attr("style", "display:none");
				$("#alert_scheduleDeviatedTrip_display").attr("style", "display:none");
				$("#alert_schedulePartial_display").attr("style", "display:none");
				
				$("#alert_crewCompletedTrip_display").attr("style", "display:none");
				$("#alert_crewDeviatedTrip_display").attr("style", "display:none");
				$("#alert_crewPartial_display").attr("style", "display:none");
				
				$("#alert_Online_Depot_display").attr("style", "display:none");
				$("#alert_Offline_Depot_display").attr("style", "display:none");
				
				$("#alert_MissedTrip_display").attr("style", "display:none");
				$("#alert_DelayTrip_display").attr("style", "display:none");
				window.scrollBy(0,700);
				
				var table = $('#ETMdata_id');
				
				console.log(obj.series.status);
				
					
					if(obj.series.status=='ETM')
						{
						$("#alert_ETM_display").attr("style", "display:''");
					var table = $('#ETMdata_id');
					var oTable = table.dataTable({
						"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
						// per
						// page
						// values
						// here
						],
						// set the initial value
						"iDisplayLength" : 5,
						"sAjaxSource" : "getEtmstatuswisedata.action",
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
					jQuery('#ETMdata_id_wrapper .dataTables_filter input').addClass(
							"form-control input-medium input-inline"); // modify table
					// search input
					jQuery('#ETMdata_id_wrapper .dataTables_length select').addClass(
							"form-control input-xsmall input-inline");
					
					
						}
					else{
						$("#alert_ETMFault_display").attr("style", "display:''");

						
						var table = $('#ETMFaultdata_id');
						var oTable = table.dataTable({
							"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
							// per
							// page
							// values
							// here
							],
							// set the initial value
							"iDisplayLength" : 5,
							"sAjaxSource" : "getETMFaultyData.action",
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
						jQuery('#ETMFaultdata_id_wrapper .dataTables_filter input').addClass(
								"form-control input-medium input-inline"); // modify table
						// search input
						jQuery('#ETMFaultdata_id_wrapper .dataTables_length select').addClass(
								"form-control input-xsmall input-inline");
						
						
						
						
						
					}
			}

		},
		//etm data end
		
		//vtu start
		
		initPieVTUCharts : function() {
			//alert("hiiiiiiiiiiiiiiiiii");
			/*
			 * loadIntervalId = setInterval(function() { getVehicleData(); },
			 * 0000);
			 */
			function getVTUData() {
				//$("#piechart_accident_id").attr("style", "display:''");
				//alert("getEmployeeData");
				var vehicleData = $.ajax({
					url : "getVTUpiedata!getVTUCountpiechart",
					dataType : 'json',
					contentType : "application/json",
					global : false,
					async : true,
					success : function(response) {
						// alert(response);
						var plotVehicleResponseData = response;
						//plotVehicleData3(plotVehicleResponseData);
						return response;

					}
				}).responseText;

				var parseVehicleData = jQuery.parseJSON(vehicleData);

				}
			if (loadIntervalId != null) {
				clearInterval(loadIntervalId);
			}
			;
			/*loadIntervalId = setInterval(function() {
				getAccidentData();
			}, 10000);*/

			$("#alertspie5").bind("plothover", pieHoveralert);
			$("#alertspie5").bind("plotclick", pieClickalert);

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
				var table = $('#VTUdata_id');
				
				console.log(obj.series.status);
				
					$("#alert_VTU_display").attr("style", "display:''");
					
					var table = $('#VTUdata_id');
					var oTable = table.dataTable({
						"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
						// per
						// page
						// values
						// here
						],
						// set the initial value
						"iDisplayLength" : 5,
						"sAjaxSource" : "getVTUstatuswisedata.action",
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
					jQuery('#VTUdata_id_wrapper .dataTables_filter input').addClass(
							"form-control input-medium input-inline"); // modify table
					// search input
					jQuery('#VTUdata_id_wrapper .dataTables_length select').addClass(
							"form-control input-xsmall input-inline");
					
					
			
			}

		},
		//vtu end
		
		//trip count pie chart data
		initPieTripCountCharts : function() {
			//alert("hiiiiiiiiiiiiiiiiii");
			/*
			 * loadIntervalId = setInterval(function() { getVehicleData(); },
			 * 0000);
			 */
			function getTotalTripCountData() {
				var vehicleData = $.ajax({
					//url : "getTripCounttpiechart!getTripCounttpiechart",
					dataType : 'json',
					contentType : "application/json",
					global : false,
					async : true,
					success : function(response) {
						// alert(response);
						var plotVehicleResponseData = response;
						plotTotalTripPie(plotVehicleResponseData);
						return response;

					}
				}).responseText;

				var parseVehicleData = jQuery.parseJSON(vehicleData);

				 }
			if (loadIntervalId != null) {
				clearInterval(loadIntervalId);
			}
			;
			/*loadIntervalId = setInterval(function() {
				getTotalTripCountData();
			}, 10000);*/

			$("#alertspie7").bind("plothover", pieHoveralert);
			$("#alertspie7").bind("plotclick", pieClickalert);

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
				console.log(obj.series.status);
				
				$("#Vehicle_data_id").attr("style", "display:none");
				$("#alert_ETM_display").attr("style", "display:none");
				$("#alert_employee_display").attr("style", "display:none");
				$("#alert_CompletedTrip_display").attr("style", "display:none");
				$("#alert_DeviatedTrip_display").attr("style", "display:none");
				$("#alert_Partial_display").attr("style", "display:none");
				$("#alert_employee_display").attr("style", "display:none");
				$("#vehicleOIB").attr("style", "display:none");
				$("#vehicle").attr("style", "display:none");
				$("#alert_ETMFault_display").attr("style", "display:none");
				$("#alert_scheduleCompletedTrip_display").attr("style", "display:none");
				$("#alert_scheduleDeviatedTrip_display").attr("style", "display:none");
				$("#alert_schedulePartial_display").attr("style", "display:none");
				
				$("#alert_crewCompletedTrip_display").attr("style", "display:none");
				$("#alert_crewDeviatedTrip_display").attr("style", "display:none");
				$("#alert_crewPartial_display").attr("style", "display:none");
				
				$("#alert_Online_Depot_display").attr("style", "display:none");
				$("#alert_Offline_Depot_display").attr("style", "display:none");
				
				
				$("#alert_MissedTrip_display").attr("style", "display:none");
				$("#alert_DelayTrip_display").attr("style", "display:none");
				
				
				window.scrollBy(0,700);
				
				if(obj.series.status=='Partial'){
					
					
					$("#alert_CompletedTrip_display").attr("style", "display:none");
					$("#alert_DeviatedTrip_display").attr("style", "display:none");

					$("#alert_Partial_display").attr("style", "display:''");
					
					var table = $('#partial_trip_id');
					var oTable = table.dataTable({
						"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
						// per
						// page
						// values
						// here
						],
						// set the initial value
						"iDisplayLength" : 5,
						"sAjaxSource" : "getTripstatuswisedata.action?tripStatus="
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
					jQuery('#Trip_Partial_id_wrapper .dataTables_filter input').addClass(
							"form-control input-medium input-inline"); // modify table
					// search input
					jQuery('#Trip_Partial_id_wrapper .dataTables_length select').addClass(
							"form-control input-xsmall input-inline");
					
					
			}
				if(obj.series.status=='DeviatedTrip')
			{
					$("#alert_CompletedTrip_display").attr("style", "display:none");
					$("#alert_Partial_display").attr("style", "display:none");

				
				
				$("#alert_DeviatedTrip_display").attr("style", "display:''");
				var table = $('#Trip_DeviatedTrip_id');
				var oTable = table.dataTable({
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					"iDisplayLength" : 5,
					"sAjaxSource" : "getTripstatuswisedata.action?tripStatus="
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
				jQuery('#Trip_completed_id_wrapper .dataTables_filter input').addClass(
						"form-control input-medium input-inline"); // modify table
				// search input
				jQuery('#Trip_completed_id_wrapper .dataTables_length select').addClass(
						"form-control input-xsmall input-inline");
			}
				/*$("#vehicle_chart_alert").removeClass("collapse");
				$("#vehicle_chart_alert").addClass("expand");
				$("#alert_vehicle_display_chart").attr("style", "display:none");*/
				
				if(obj.series.status=='CompletedTrip')
				{
					$("#alert_DeviatedTrip_display").attr("style", "display:none");
					$("#alert_Partial_display").attr("style", "display:none");

					
					//$("#accident_data").attr("style", "display:none");
					$("#alert_CompletedTrip_display").attr("style", "display:''");
					var table = $('#Trip_CompletedTrip_id');
					var oTable = table.dataTable({
						"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
						// per
						// page
						// values
						// here
						],
						// set the initial value
						"iDisplayLength" : 5,
						"sAjaxSource" : "getTripstatuswisedata.action?tripStatus="
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
					jQuery('#Trip_completed_id_wrapper .dataTables_filter input').addClass(
							"form-control input-medium input-inline"); // modify table
					// search input
					jQuery('#Trip_completed_id_wrapper .dataTables_length select').addClass(
							"form-control input-xsmall input-inline");
				}
					/*$("#vehicle_chart_alert").removeClass("collapse");
					$("#vehicle_chart_alert").addClass("expand");
					$("#alert_vehicle_display_chart").attr("style", "display:none");*/
				//missed
				if(obj.series.status=='MissedTrip')
				{
						$("#alert_CompletedTrip_display").attr("style", "display:none");
						$("#alert_Partial_display").attr("style", "display:none");
						$("#alert_DeviatedTrip_display").attr("style", "display:none");
					
					
					$("#alert_MissedTrip_display").attr("style", "display:''");
					var table = $('#Trip_MissedTrip_id');
					var oTable = table.dataTable({
						"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
						// per
						// page
						// values
						// here
						],
						// set the initial value
						"iDisplayLength" : 5,
						"sAjaxSource" : "getTripstatuswisedata.action?tripStatus="
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
					jQuery('#Trip_completed_id_wrapper .dataTables_filter input').addClass(
							"form-control input-medium input-inline"); // modify table
					// search input
					jQuery('#Trip_completed_id_wrapper .dataTables_length select').addClass(
							"form-control input-xsmall input-inline");
				}
				//end missed
				
				//delay
				
				if(obj.series.status=='DelayTrip')
				{
						$("#alert_CompletedTrip_display").attr("style", "display:none");
						$("#alert_Partial_display").attr("style", "display:none");
						$("#alert_DeviatedTrip_display").attr("style", "display:none");
					
					
					$("#alert_DelayTrip_display").attr("style", "display:''");
					var table = $('#Trip_DelayTrip_id');
					var oTable = table.dataTable({
						"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
						// per
						// page
						// values
						// here
						],
						// set the initial value
						"iDisplayLength" : 5,
						"sAjaxSource" : "getTripstatuswisedata.action?tripStatus="
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
					jQuery('#Trip_completed_id_wrapper .dataTables_filter input').addClass(
							"form-control input-medium input-inline"); // modify table
					// search input
					jQuery('#Trip_completed_id_wrapper .dataTables_length select').addClass(
							"form-control input-xsmall input-inline");
				}
				//end delay
			}

		},
		//trip count data end
		
		//schedule trip count start
		
		
		initPieScheduleTripCountCharts : function() {
			//alert("hiiiiiiiiiiiiiiiiii");
			/*
			 * loadIntervalId = setInterval(function() { getVehicleData(); },
			 * 0000);
			 */
			function getTotalScheduleTripCountData() {
				var vehicleData = $.ajax({
					url : "getScheduleTripCounttpiechart!getScheduleTripCounttpiechart",
					dataType : 'json',
					contentType : "application/json",
					global : false,
					async : true,
					success : function(response) {
						// alert(response);
						var plotVehicleResponseData = response;
						plotScheduleTotalTripPie(plotVehicleResponseData);
						return response;

					}
				}).responseText;

				var parseVehicleData = jQuery.parseJSON(vehicleData);

				 }
			if (loadIntervalId != null) {
				clearInterval(loadIntervalId);
			}
			;
			/*loadIntervalId = setInterval(function() {
				getTotalTripCountData();
			}, 10000);*/

			$("#alertspie8").bind("plothover", pieHoveralert);
			$("#alertspie8").bind("plotclick", pieClickalert);

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
				console.log(obj.series.status);
				//alert("data"+obj.series.status);
				
				$("#Vehicle_data_id").attr("style", "display:none");
				$("#alert_ETM_display").attr("style", "display:none");
				$("#alert_employee_display").attr("style", "display:none");
				$("#alert_CompletedTrip_display").attr("style", "display:none");
				$("#alert_DeviatedTrip_display").attr("style", "display:none");
				$("#alert_Partial_display").attr("style", "display:none");
				$("#alert_scheduleCompletedTrip_display").attr("style", "display:none");
				$("#alert_scheduleDeviatedTrip_display").attr("style", "display:none");
				$("#alert_schedulePartial_display").attr("style", "display:none");
				$("#alert_ETMFault_display").attr("style", "display:none");
				$("#alert_employee_display").attr("style", "display:none");
				$("#vehicleOIB").attr("style", "display:none");
				$("#vehicle").attr("style", "display:none");
				
				$("#alert_crewCompletedTrip_display").attr("style", "display:none");
				$("#alert_crewDeviatedTrip_display").attr("style", "display:none");
				$("#alert_crewPartial_display").attr("style", "display:none");
				
				$("#alert_Online_Depot_display").attr("style", "display:none");
				$("#alert_Offline_Depot_display").attr("style", "display:none");
				$("#alert_MissedTrip_display").attr("style", "display:none");
				$("#alert_DelayTrip_display").attr("style", "display:none");
				
				window.scrollBy(0,300);
				
				
				
				if(obj.series.status=='PartialSchedule'){
					
					//alert("partial"+obj.series.status);
					$("#alert_scheduleCompletedTrip_display").attr("style", "display:none");
					$("#alert_scheduleDeviatedTrip_display").attr("style", "display:none");

					$("#alert_schedulePartial_display").attr("style", "display:''");
					
					var table = $('#schedulepartial_trip_id');
					var oTable = table.dataTable({
						"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
						// per
						// page
						// values
						// here
						],
						// set the initial value
						"iDisplayLength" : 5,
						"sAjaxSource" : "getScheduleTripstatuswisedata.action?tripStatus="
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
					jQuery('#Trip_Partial_id_wrapper .dataTables_filter input').addClass(
							"form-control input-medium input-inline"); // modify table
					// search input
					jQuery('#Trip_Partial_id_wrapper .dataTables_length select').addClass(
							"form-control input-xsmall input-inline");
					
					
			}
				if(obj.series.status=='DeviatedSchedule')
			{	
					//alert("deviated"+obj.series.status);
					
					$("#alert_scheduleCompletedTrip_display").attr("style", "display:none");
					$("#alert_schedulePartial_display").attr("style", "display:none");

				
				
				$("#alert_scheduleDeviatedTrip_display").attr("style", "display:''");
				var table = $('#Trip_scheduleDeviatedTrip_id');
				var oTable = table.dataTable({
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					"iDisplayLength" : 5,
					"sAjaxSource" : "getScheduleTripstatuswisedata.action?tripStatus="
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
				jQuery('#Trip_completed_id_wrapper .dataTables_filter input').addClass(
						"form-control input-medium input-inline"); // modify table
				// search input
				jQuery('#Trip_completed_id_wrapper .dataTables_length select').addClass(
						"form-control input-xsmall input-inline");
			}
				/*$("#vehicle_chart_alert").removeClass("collapse");
				$("#vehicle_chart_alert").addClass("expand");
				$("#alert_vehicle_display_chart").attr("style", "display:none");*/
				
				if(obj.series.status=='CompletedSchedule')
				{
					
					//alert("completed"+obj.series.status);
					$("#alert_scheduleDeviatedTrip_display").attr("style", "display:none");
					$("#alert_schedulePartial_display").attr("style", "display:none");

					
					//$("#accident_data").attr("style", "display:none");
					$("#alert_scheduleCompletedTrip_display").attr("style", "display:''");
					var table = $('#Trip_scheduleCompletedTrip_id');
					var oTable = table.dataTable({
						"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
						// per
						// page
						// values
						// here
						],
						// set the initial value
						"iDisplayLength" : 5,
						"sAjaxSource" : "getScheduleTripstatuswisedata.action?tripStatus="
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
					jQuery('#Trip_completed_id_wrapper .dataTables_filter input').addClass(
							"form-control input-medium input-inline"); // modify table
					// search input
					jQuery('#Trip_completed_id_wrapper .dataTables_length select').addClass(
							"form-control input-xsmall input-inline");
				}
					/*$("#vehicle_chart_alert").removeClass("collapse");
					$("#vehicle_chart_alert").addClass("expand");
					$("#alert_vehicle_display_chart").attr("style", "display:none");*/
				
			}

		},
		//trip count data end
		//schedule trip count end
		
		//crew allocation data start
		initPieCrewAllocationCountCharts : function() {
		//alert("hiiiiiiiiiiiiiiiiii2");
			/*
			 * loadIntervalId = setInterval(function() { getVehicleData(); },
			 * 0000);
			 */
			function getTotalCrewAllocationCountData() {
				var vehicleData = $.ajax({
					url : "getCrewAllocationCounttpiechart!getCrewAllocationCountpiechart",
					dataType : 'json',
					contentType : "application/json",
					global : false,
					async : true,
					success : function(response) {
						// alert(response);
						var plotVehicleResponseData = response;
						plotCrewAlloactionPie(plotVehicleResponseData);
						return response;

					}
				}).responseText;

				var parseVehicleData = jQuery.parseJSON(vehicleData);

				 }
			if (loadIntervalId != null) {
				clearInterval(loadIntervalId);
			}
			;
			/*loadIntervalId = setInterval(function() {
				getTotalTripCountData();
			}, 10000);*/

			$("#alertspie9").bind("plothover", pieHoveralert);
			$("#alertspie9").bind("plotclick", pieClickalert);

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
				console.log(obj.series.status);
				//alert("data"+obj.series.status);
				
				$("#Vehicle_data_id").attr("style", "display:none");
				$("#alert_ETM_display").attr("style", "display:none");
				$("#alert_employee_display").attr("style", "display:none");
				$("#alert_CompletedTrip_display").attr("style", "display:none");
				$("#alert_DeviatedTrip_display").attr("style", "display:none");
				$("#alert_Partial_display").attr("style", "display:none");
				$("#alert_scheduleCompletedTrip_display").attr("style", "display:none");
				$("#alert_scheduleDeviatedTrip_display").attr("style", "display:none");
				$("#alert_schedulePartial_display").attr("style", "display:none");
				$("#alert_ETMFault_display").attr("style", "display:none");
				$("#alert_employee_display").attr("style", "display:none");
				$("#vehicleOIB").attr("style", "display:none");
				$("#vehicle").attr("style", "display:none");
				
				
				$("#alert_crewCompletedTrip_display").attr("style", "display:none");
				$("#alert_crewDeviatedTrip_display").attr("style", "display:none");
				$("#alert_crewPartial_display").attr("style", "display:none");
				
				$("#alert_Online_Depot_display").attr("style", "display:none");
				$("#alert_Offline_Depot_display").attr("style", "display:none");
				
				$("#alert_MissedTrip_display").attr("style", "display:none");
				$("#alert_DelayTrip_display").attr("style", "display:none");
				
				window.scrollBy(0,300);
				
				
				
				if(obj.series.status=='Close'){
					
					//alert("partial"+obj.series.status);
					$("#alert_crewCompletedTrip_display").attr("style", "display:none");
					$("#alert_crewDeviatedTrip_display").attr("style", "display:none");

					$("#alert_crewPartial_display").attr("style", "display:''");
					
					var table = $('#crewpartial_id');
					var oTable = table.dataTable({
						"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
						// per
						// page
						// values
						// here
						],
						// set the initial value
						"iDisplayLength" : 5,
						"sAjaxSource" : "getCrewAlloactionstatuswisedata.action?tripStatus="
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
					jQuery('#crewpartial_id_wrapper .dataTables_filter input').addClass(
							"form-control input-medium input-inline"); // modify table
					// search input
					jQuery('#crewpartial_id_wrapper .dataTables_length select').addClass(
							"form-control input-xsmall input-inline");
					
					
			}
				if(obj.series.status=='New')
			{	
					//alert("deviated"+obj.series.status);
					
					$("#alert_crewCompletedTrip_display").attr("style", "display:none");
					$("#alert_crewPartial_display").attr("style", "display:none");

				
				
				$("#alert_crewDeviatedTrip_display").attr("style", "display:''");
				var table = $('#Trip_crewDeviatedTrip_id');
				var oTable = table.dataTable({
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					"iDisplayLength" : 5,
					"sAjaxSource" : "getCrewAlloactionstatuswisedata.action?tripStatus="
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
				jQuery('#crew_completed_id_wrapper .dataTables_filter input').addClass(
						"form-control input-medium input-inline"); // modify table
				// search input
				jQuery('#crew_completed_id_wrapper .dataTables_length select').addClass(
						"form-control input-xsmall input-inline");
			}
				/*$("#vehicle_chart_alert").removeClass("collapse");
				$("#vehicle_chart_alert").addClass("expand");
				$("#alert_vehicle_display_chart").attr("style", "display:none");*/
				
				if(obj.series.status=='Online')
				{
					
					//alert("completed"+obj.series.status);
					$("#alert_crewDeviatedTrip_display").attr("style", "display:none");
					$("#alert_crewPartial_display").attr("style", "display:none");

					
					//$("#accident_data").attr("style", "display:none");
					$("#alert_crewCompletedTrip_display").attr("style", "display:''");
					var table = $('#Trip_crewCompletedTrip_id');
					var oTable = table.dataTable({
						"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
						// per
						// page
						// values
						// here
						],
						// set the initial value
						"iDisplayLength" : 5,
						"sAjaxSource" : "getCrewAlloactionstatuswisedata.action?tripStatus="
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
					jQuery('#crew_completed_id_wrapper .dataTables_filter input').addClass(
							"form-control input-medium input-inline"); // modify table
					// search input
					jQuery('#Tcrew_completed_id_wrapper .dataTables_length select').addClass(
							"form-control input-xsmall input-inline");
				}
					/*$("#vehicle_chart_alert").removeClass("collapse");
					$("#vehicle_chart_alert").addClass("expand");
					$("#alert_vehicle_display_chart").attr("style", "display:none");*/
				
			}

		},
		//crew alocation data end
		
		//online offline depot count pie chart data
		initPieOnlineOfflineDepotCountCharts : function() {
			function getTotalOnlineOfflineDepotCountData(){
				var vehicleData = $.ajax({
					url : "getOnlineOfflineDepotCounttpiechart!getOnlineOfflineDepotCounttpiechart",
					dataType : 'json',
					contentType : "application/json",
					global : false,
					async : true,
					success : function(response) {
						// alert(response);
						var plotVehicleResponseData = response;
						plotTotalOnlineOfflineDepotPie(plotVehicleResponseData);
						return response;

					}
				}).responseText;

				var parseVehicleData = jQuery.parseJSON(vehicleData);
		}
				
			if (loadIntervalId != null) {
				clearInterval(loadIntervalId);
			}
			;
			/*loadIntervalId = setInterval(function() {
				getTotalTripCountData();
			}, 10000);*/

			$("#alertspie10").bind("plothover", pieHoveralert);
			$("#alertspie10").bind("plotclick", pieClickalert);

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
				console.log(obj.series.status);
				
				$("#Vehicle_data_id").attr("style", "display:none");
				$("#alert_ETM_display").attr("style", "display:none");
				$("#alert_employee_display").attr("style", "display:none");
				$("#alert_CompletedTrip_display").attr("style", "display:none");
				$("#alert_DeviatedTrip_display").attr("style", "display:none");
				$("#alert_Partial_display").attr("style", "display:none");
				$("#alert_employee_display").attr("style", "display:none");
				$("#vehicleOIB").attr("style", "display:none");
				$("#vehicle").attr("style", "display:none");
				$("#alert_ETMFault_display").attr("style", "display:none");
				$("#alert_scheduleCompletedTrip_display").attr("style", "display:none");
				$("#alert_scheduleDeviatedTrip_display").attr("style", "display:none");
				$("#alert_schedulePartial_display").attr("style", "display:none");
				
				$("#alert_crewCompletedTrip_display").attr("style", "display:none");
				$("#alert_crewDeviatedTrip_display").attr("style", "display:none");
				$("#alert_crewPartial_display").attr("style", "display:none");
				
				
				$("#alert_Online_Depot_display").attr("style", "display:none");
				$("#alert_Offline_Depot_display").attr("style", "display:none");
				
				$("#alert_MissedTrip_display").attr("style", "display:none");
				$("#alert_DelayTrip_display").attr("style", "display:none");
				
				window.scrollBy(0,700);
				
				if(obj.series.status=='Online_Depot'){
					
					
					
					$("#alert_Online_Depot_display").attr("style", "display:''");
					
					var table = $('#Trip_Online_Depot_id');
					var oTable = table.dataTable({
						"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
						// per
						// page
						// values
						// here
						],
						// set the initial value
						"iDisplayLength" : 5,
						"sAjaxSource" : "getOnlineDepotData.action",
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
					jQuery('#Trip_Partial_id_wrapper .dataTables_filter input').addClass(
							"form-control input-medium input-inline"); // modify table
					// search input
					jQuery('#Trip_Partial_id_wrapper .dataTables_length select').addClass(
							"form-control input-xsmall input-inline");
			}
				
			if(obj.series.status=='Offline_Depot')
			{
									
				$("#alert_Offline_Depot_display").attr("style", "display:''");
				var table = $('#Trip_Offline_Depot_id');
				var oTable = table.dataTable({
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					// per
					// page
					// values
					// here
					],
					// set the initial value
					"iDisplayLength" : 5,
					"sAjaxSource" : "getOfflineDepotData.action",
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
				jQuery('#Trip_completed_id_wrapper .dataTables_filter input').addClass(
						"form-control input-medium input-inline"); // modify table
				// search input
				jQuery('#Trip_completed_id_wrapper .dataTables_length select').addClass(
						"form-control input-xsmall input-inline");
			}
			}
		},
		//online offline depot count data end
		
		
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
								//getBarChartInfo(baralertData, response);
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
			/*loadBarchartIntervalId = setInterval(function() {
				getAlertIntervalData();
				// loadActiveVehicles();
			}, 10000);

			loadBarchartIntervalId = setInterval(function() {
				getAlertIntervalData();
			}, 10000);*/

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
				if (obj.series.status == "Deviated") {
					table = $('#vehicle_dashBoard_deviation_data');
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
					$("#vehicle_chart_alert").removeClass("collapse");
					$("#vehicle_chart_alert").addClass("expand");
					$("#alert_vehicle_display_chart").attr("style",
							"display:none");

				}
				// Box Tampering Alert
				if (obj.series.status == "Tampering") {
					table = $('#vehicle_dashBoard_tampering_data');
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
					$("#vehicle_chart_alert").removeClass("collapse");
					$("#vehicle_chart_alert").addClass("expand");
					$("#alert_vehicle_display_chart").attr("style",
							"display:none");

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
		"sAjaxSource" : "vehiclePerticular.action?vehicleStatus=Active",
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
//get Accident data
function getAccidentData() {
	
	var vehicleData = $.ajax({
		url : "getAccidentpiedata!getVehicleCount",
		dataType : 'json',
		contentType : "application/json",
		global : false,
		async : true,
		success : function(response) {
			// alert(response);
			var plotVehicleResponseData = response;
			plotVehicleData1(plotVehicleResponseData);
			return response;

		}
	}).responseText;

	var parseVehicleData = jQuery.parseJSON(vehicleData);
}

//get breakdown piechart start

function getVehicleBreakdownData() {
	//$("#piechart_accident_id").attr("style", "display:''");
	
	var vehicleData = $.ajax({
		url : "getBreakdownpiedata!getBreakdownpiechart",
		dataType : 'json',
		contentType : "application/json",
		global : false,
		async : true,
		success : function(response) {
			// alert(response);
			var plotVehicleResponseData = response;
			plotVehicleData2(plotVehicleResponseData);
			return response;

		}
	}).responseText;

	var parseVehicleData = jQuery.parseJSON(vehicleData);

	}
//end breakdown piechart

//get Driver cum conductor piechart start

function getEmployeeData() {
	//$("#piechart_accident_id").attr("style", "display:''");
	//alert("getEmployeeData");
	var vehicleData = $.ajax({
		url : "getConductorDriverpiedata!getDriverConductorCountpiechart",
		dataType : 'json',
		contentType : "application/json",
		global : false,
		async : true,
		success : function(response) {
			// alert(response);
			var plotVehicleResponseData = response;
			plotVehicleData3(plotVehicleResponseData);
			//plotVehicleData33(plotVehicleResponseData);
			return response;

		}
	}).responseText;

	var parseVehicleData = jQuery.parseJSON(vehicleData);

	}
//end VTU count
function getVTUData() {
	//$("#piechart_accident_id").attr("style", "display:''");
	//alert("getEmployeeData");
	var vehicleData = $.ajax({
		url : "getVTUpiedata!getVTUCountpiechart",
		dataType : 'json',
		contentType : "application/json",
		global : false,
		async : true,
		success : function(response) {
			// alert(response);
			var plotVehicleResponseData = response;
			plotVTUData(plotVehicleResponseData);
			return response;

		}
	}).responseText;

	var parseVehicleData = jQuery.parseJSON(vehicleData);

	}
//end VTU count
//start ETM count
function getETMData() {
	//$("#piechart_accident_id").attr("style", "display:''");
	//alert("getEmployeeData");
	var vehicleData = $.ajax({
		url : "getETMpiedata!getETMCountpiechart",
		dataType : 'json',
		contentType : "application/json",
		global : false,
		async : true,
		success : function(response) {
			// alert(response);
			var plotVehicleResponseData = response;
			plotETMData(plotVehicleResponseData);
			return response;

		}
	}).responseText;
}
	
//end ETM count

//get Trip Count start

function getTotalTripCountData() {
	
	var vehicleData = $.ajax({
		url : "getTripCounttpiechart!getTripCounttpiechart",
		dataType : 'json',
		contentType : "application/json",
		global : false,
		async : true,
		success : function(response) {
			// alert(response);
			var plotVehicleResponseData = response;
			plotTotalTripPie(plotVehicleResponseData);
			return response;

		}
	}).responseText;



	 }
//trip count end
//totala schedule data


function getTotalScheduleTripCountData() {
	
	var vehicleData = $.ajax({
		url : "getScheduleTripCounttpiechart!getScheduleTripCounttpiechart",
		dataType : 'json',
		contentType : "application/json",
		global : false,
		async : true,
		success : function(response) {
			// alert(response);
			var plotVehicleResponseData = response;
			plotScheduleTotalTripPie(plotVehicleResponseData);
			return response;

		}
	}).responseText;



	 }
//total schedule data end

//toatal crew alloacation data

function getTotalCrewAllocationCountData() {
	
	var vehicleData = $.ajax({
		url : "getCrewAllocationCounttpiechart!getCrewAllocationCountpiechart",
		dataType : 'json',
		contentType : "application/json",
		global : false,
		async : true,
		success : function(response) {
			// alert(response);
			var plotVehicleResponseData = response;
			plotCrewAlloactionPie(plotVehicleResponseData);
			return response;

		}
	}).responseText;



	 }

//end total crew allocation data

//total online offline depot data


function getTotalOnlineOfflineDepotCountData() {
	
	var vehicleData = $.ajax({
		url : "getOnlineOfflineDepotCounttpiechart!getOnlineOfflineDepotCounttpiechart",
		dataType : 'json',
		contentType : "application/json",
		global : false,
		async : true,
		success : function(response) {
			// alert(response);
			var plotVehicleResponseData = response;
			plotTotalOnlineOfflineDepotPie(plotVehicleResponseData);
			return response;

		}
	}).responseText;



	 }
//total online offline depot data end

function getAlertIntervalData() {
	// Ajax Call
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
					//getBarChartInfo(baralertData, response);
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
	for ( var i = 0; i < barseries; i++) {
		ticks.push([ i, barparseData["aaData"][i]["1"] ]);
		avlData.push(barparseData["aaData"][i]["1"]);
		data.push({
			// label : barparseData["aaData"][i]["2"] + "-"
			// + (barparseData["aaData"][i]["0"]),
			data : [ [ i, barparseData["aaData"][i]["0"] ] ],
			status : barparseData["aaData"][i]["1"]
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

}
/*
 * Added by Rasmi ranjan Das FOR SYNCHRONZING PLOTTING VEHICLE DATA WITH OTHER
 * MENU DISPLAY
 */
function plotVehicleData(vehicleData) {
	//alert("vehicleData"+vehicleData);
	var data = [];
	// var series = Math.floor(Math.random() * 10) + 1;
	series = vehicleData["aaData"].length;

	for ( var i = 0; i < series; i++) {
		data[i] = {
			label : vehicleData["aaData"][i]["2"] + "-"
					+ (vehicleData["aaData"][i]["0"]),
			data : vehicleData["aaData"][i]["1"] ,
			status : vehicleData["aaData"][i]["2"]
		}
		//alert(vehicleData["aaData"][i]["2"]);
		//alert(vehicleData["aaData"][i]["1"]);
	}
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
						colors : [ "#088A08", "#FF0000",'#000000',"#4da74d","#cb4b4b",'#FF0FFF', "#afd8f8", 
								, "#9440ed",  '#000000' ],
						grid : {
							hoverable : true,
							clickable : true
						}

					});



}
function plotVehicleData1(vehicleData) {
	$("#piechart_accident_id").attr("style", "display:''");
	var data = [];
	// var series = Math.floor(Math.random() * 10) + 1;
	series = vehicleData["aaData"].length;
	

	for ( var i = 0; i < series; i++) {
		data[i] = {
			label : vehicleData["aaData"][i]["2"] + "-"
					+ (vehicleData["aaData"][i]["0"]),
			data : vehicleData["aaData"][i]["1"] ,
			status : vehicleData["aaData"][i]["2"]
		}
		
	}
	$
			.plot(
					$("#alertspie3"),
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
						colors : [ "#088A08", "#FF0000",'#FF6600',"#4da74d","#cb4b4b",'#FF0FFF', "#afd8f8", 
								, "#9440ed",  '#000000' ],
						grid : {
							hoverable : true,
							clickable : true
						}

					});



}

//plot data 2

function plotVehicleData2(vehicleData) {
	
	
	$("#piechart_breakdown_id").attr("style", "display:''");
	var data = [];
	
	// var series = Math.floor(Math.random() * 10) + 1;
	series = vehicleData["aaData"].length;

	for ( var i = 0; i < series; i++) {
		data[i] = {
			label : vehicleData["aaData"][i]["2"] + "-"
					+ (vehicleData["aaData"][i]["0"]),
			data : vehicleData["aaData"][i]["1"] ,
			status : vehicleData["aaData"][i]["2"]
		}
	}
	$
			.plot(
					$("#alertspie2"),
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
						colors : [ "#088A08", "#FF0000",'#000000',"#4da74d","#cb4b4b",'#FF0FFF', "#afd8f8", 
								, "#9440ed",  '#000000' ],
						grid : {
							hoverable : true,
							clickable : true
						}

					});



}
//plot data 2 end

//plot data piechart 3

function plotVehicleData3(vehicleData) {
	$("#piechart_employee_id").attr("style", "display:''");
	var data = [];
	var a0="";
	var a1="";
	var a2="";
	var no=[];
	//Employee.................{"aaData":[[229,229,"Conductor"],[445,445,"Driver"],[335,335,"DriverConductor"]]}
	// var series = Math.floor(Math.random() * 10) + 1;
	series = vehicleData["aaData"].length;

	for ( var i = 0; i < series; i++) {
		//alert("i --"+vehicleData["aaData"][i]["1"]);
		no[i]=vehicleData["aaData"][i]["1"];
		
		data[i] = {
			label : vehicleData["aaData"][i]["2"] + "-"
					+ (vehicleData["aaData"][i]["0"]),
			data : vehicleData["aaData"][i]["1"] ,
			status : vehicleData["aaData"][i]["2"] 
		
		}
	}
	for(var i=0;i<no.length;i++)
		{
		
		//alert("hhh"+i+""+no[i]);
		}
	a0=no[0];
	a1=no[1];
	a2=no[2];
//	$
//			.plot(
//					$("#alertspie4"),
//					data,
//					{
//						series : {
//							pie : {
//								show : true,
//								radius : 1,
//								// tilt : 0.5,
//								label : {
//									show : true,
//									radius : 0.8,
//									formatter : function(label, series) {
//										var arr = label.split("-");
//										return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
//												+ arr[1] + '</div>';
//									},
//									background : {
//										opacity : 0.8
//									}
//								}
//							}
//
//						},
//						colors : [ "#088A08", "#FF0000",'#000000',"#4da74d","#cb4b4b",'#FF0FFF', "#afd8f8", 
//								, "#9440ed",  '#000000' ],
//						grid : {
//							hoverable : true,
//							clickable : true
//						}
//
//					});

	
	//start
	
	
	//loadActiveVehicles();
	//3d start
	//var chart;
	//Employee.................{"aaData":[[229,229,"Conductor"],[445,445,"Driver"],[335,335,"DriverConductor"]]}
	var data=[{
        "country": "Conductor",
        "visits": a0,
        "color": "#FF0F00"
    },{
        "country": "Driver",
        "visits": a1,
        "color": "#FF6600"
    },{
        "country": "DriverConductor",
        "visits":a2,
        "color": "#F8FF01"
    },];
	
	
	
	var chart=AmCharts.makeChart("chartdiv", {
"theme": "none",
"type": "serial",
"startDuration": 2,


"dataProvider": data,
"valueAxes": [{
    "position": "left",
    "title": "Employee Range"
}],
"graphs": [{
    "balloonText": "[[category]]: <b>[[value]]</b>",
    "colorField": "color",
    "fillAlphas": 0.85,
    "lineAlpha": 0.1,
    "type": "column",
    "topRadius":1,
    "valueField": "visits"
}],
"depth3D": 40,
"angle": 30,
"chartCursor": {
    "categoryBalloonEnabled": false,
    "cursorAlpha": 0,
    "zoomable": false
},    
"categoryField": "country",
"categoryAxis": {
    "gridPosition": "start",
    "labelRotation": 90
}/*,
"exportConfig":{
	"menuTop":"20px",
    "menuRight":"20px",
    "menuItems": [{
    "icon": 'Employee Details',
    "format": 'png'	  
    }]  
}*/


});
	
chart.addListener("clickGraphItem", handleClick)
function handleClick(event)
{
	//alert(event.toSource());
//alert(event.item.category + ": " + event.item.values.value);
	$("#Vehicle_data_id").attr("style", "display:none");
	$("#alert_ETM_display").attr("style", "display:none");
	$("#alert_employee_display").attr("style", "display:none");
	$("#alert_CompletedTrip_display").attr("style", "display:none");
	$("#alert_DeviatedTrip_display").attr("style", "display:none");
	$("#alert_Partial_display").attr("style", "display:none");
	$("#alert_employee_display").attr("style", "display:none");
	$("#vehicleOIB").attr("style", "display:none");
	$("#vehicle").attr("style", "display:none");
	
	$("#alert_ETMFault_display").attr("style", "display:none");
	$("#alert_scheduleCompletedTrip_display").attr("style", "display:none");
	$("#alert_scheduleDeviatedTrip_display").attr("style", "display:none");
	$("#alert_schedulePartial_display").attr("style", "display:none");
	
	$("#alert_crewCompletedTrip_display").attr("style", "display:none");
	$("#alert_crewDeviatedTrip_display").attr("style", "display:none");
	$("#alert_crewPartial_display").attr("style", "display:none");
	
	$("#alert_Online_Depot_display").attr("style", "display:none");
	$("#alert_Offline_Depot_display").attr("style", "display:none");
	
	
	$("#alert_MissedTrip_display").attr("style", "display:none");
	$("#alert_DelayTrip_display").attr("style", "display:none");
	//$("#vehicle").attr("style", "display:none");
window.scrollBy(0,300);
	
	
	
	
var table = $('#employeedata_id');

//console.log(obj.series.status);

	$("#alert_employee_display").attr("style", "display:''");
	
	var table = $('#employeedata_id');
	var oTable = table.dataTable({
		"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
		// per
		// page
		// values
		// here
		],
		// set the initial value
		"iDisplayLength" : 5,
		"sAjaxSource" : "getEmployeestatuswisedata.action?employeestatus="
			+ event.item.category,
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
	jQuery('#employeedata_id_wrapper .dataTables_filter input').addClass(
			"form-control input-medium input-inline"); // modify table
	// search input
	jQuery('#employeedata_id_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");
}
	

	//3d end
	
	//end


}
//piechart 3 end
//pichart 3d start
function plotVehicleData33(vehicleData) {
	//alert("alert11");
//	$("#piechart_employee_id").attr("style", "display:''");
	var data = [];
	var a0="";
	var a1="";
	var a2="";
	var no=[];
	// var series = Math.floor(Math.random() * 10) + 1;
	//series = vehicleData["aaData"].length;

	for ( var i = 0; i < series; i++) {
		//alert("i --"+vehicleData["aaData"][i]["1"]);
		no[i]=vehicleData["aaData"][i]["1"];
		
		data[i] = {
			label : vehicleData["aaData"][i]["2"] + "-"
					+ (vehicleData["aaData"][i]["0"]),
			data : vehicleData["aaData"][i]["1"] ,
			status : vehicleData["aaData"][i]["2"] 
		
		}
		
	}
	//alert("data--"+data[0].data);
	//alert("data--"+data[0].status);
	for(var i=0;i<no.length;i++)
		{
		
		//alert("hhh"+i+""+no[i]);
		}
	a0=no[0];
	a1=no[1];
	a2=no[2];
//	$
//			.plot(
//					$("#alertspie4"),
//					data,
//					{
//						series : {
//							pie : {
//								show : true,
//								radius : 1,
//								// tilt : 0.5,
//								label : {
//									show : true,
//									radius : 0.8,
//									formatter : function(label, series) {
//										var arr = label.split("-");
//										return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
//												+ arr[1] + '</div>';
//									},
//									background : {
//										opacity : 0.8
//									}
//								}
//							}
//
//						},
//						colors : [ "#088A08", "#FF0000",'#000000',"#4da74d","#cb4b4b",'#FF0FFF', "#afd8f8", 
//								, "#9440ed",  '#000000' ],
//						grid : {
//							hoverable : true,
//							clickable : true
//						}
//
//					});

	
	//start
	
	var a=57;
	var b=89;
	var c=21;
	//loadActiveVehicles();
	//3d start
	//var chart;
//	var data=[{
//        "country": "Conductor",
//        "value":a0
//        
//    },{
//        "country": "Driver",
//        "value": a1
//        
//    },{
//        "country": "DriverConductor",
//        "value":a2
//       
//    }];
	var data=[{
        "percents": "Conductor",
        "value": a0
        
    },{
        "percents": "Driver",
        "value": a1
    },{
        "percents": "DriverConductor",
        "value":a2
    },];
	
	
	var charts =AmCharts.makeChart("chartdiv2", {
	    "type": "pie",
	    "theme": "none",
	    "dataProvider":data,
	    "valueField": "value",
	    "titleField": "country",
	    "outlineAlpha": 0.4,
	    "depth3D": 15,
	    "balloonText": "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>",
	    "angle": 30,
	    "exportConfig":{	
	      menuItems: [{
	      icon: '/lib/3/images/export.png',
	      format: 'png'	  
	      }]  
	
		}
	});
	jQuery('.chart-input').off().on('input change',function() {
		var property	= jQuery(this).data('property');
		var target		= charts;
		var value		= Number(this.value);
		charts.startDuration = 0;

		if ( property == 'innerRadius') {
			value += "%";
		}

		target[property] = value;
		charts.validateNow();
	});
	/*$("#chartdiv2").click( 
            function(event){
            alert(event.toSource());
            	//alert("1"+event);
            	//console.log("event:"+event);
            	//console.log("event:"+event.source);
            	alert(event.item.category + ": " + event.item.values.value);
            	
            }
        ); */
	
	$('#chartdiv2').click(function(){
		//alert(this.toSource());
	});
chart.addListener("clickItem", handleClick)
function handleClick(event)
{
	//	alert(event);
//alert(event.item.category + ": " + event.item.values.value);
var table = $('#employeedata_id');

//console.log(obj.series.status);

	$("#alert_employee_display").attr("style", "display:''");
	
	var table = $('#employeedata_id');
	var oTable = table.dataTable({
		"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
		// per
		// page
		// values
		// here
		],
		// set the initial value
		"iDisplayLength" : 5,
		"sAjaxSource" : "getEmployeestatuswisedata.action?employeestatus="
			+ event.item.category,
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
	jQuery('#employeedata_id_wrapper .dataTables_filter input').addClass(
			"form-control input-medium input-inline"); // modify table
	// search input
	jQuery('#employeedata_id_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");
}
	

	//3d end
	
	//end


}
//pichart 3d end
//plot pie chart 4 for VTU
function plotVTUData(vehicleData) {
	$("#piechart_vtu_id").attr("style", "display:''");
	var data = [];
	// var series = Math.floor(Math.random() * 10) + 1;
	series = vehicleData["aaData"].length;

	for ( var i = 0; i < series; i++) {
		data[i] = {
			label : vehicleData["aaData"][i]["2"] + "-"
					+ (vehicleData["aaData"][i]["0"]),
			data : vehicleData["aaData"][i]["1"] ,
			status : vehicleData["aaData"][i]["2"]
		}
	}
	$
			.plot(
					$("#alertspie5"),
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
						colors : [ "#088A08", "#FF0000",'#000000',"#4da74d","#cb4b4b",'#FF0FFF', "#afd8f8", 
								, "#9440ed",  '#000000' ],
						grid : {
							hoverable : true,
							clickable : true
						}

					});



}
//plot pie chart 4 end

//plot pie chart 4 for ETM
function plotETMData(vehicleData) {
	$("#piechart_etm_id").attr("style", "display:''");
	var data = [];
	// var series = Math.floor(Math.random() * 10) + 1;
	series = vehicleData["aaData"].length;

	for ( var i = 0; i < series; i++) {
		data[i] = {
			label : vehicleData["aaData"][i]["2"] + "-"
					+ (vehicleData["aaData"][i]["0"]),
			data : vehicleData["aaData"][i]["1"] ,
			status : vehicleData["aaData"][i]["2"]
		}
	}
	$
			.plot(
					$("#alertspie6"),
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
						colors : [ "#088A08", "#FF0000",'#000000',"#4da74d","#cb4b4b",'#FF0FFF', "#afd8f8", 
								, "#9440ed",  '#000000' ],
						grid : {
							hoverable : true,
							clickable : true
						}

					});



}


//plot pie chart FOR ETM end

//plot pie chart for total trip count
function plotTotalTripPie(vehicleData) {
	
	
	$("#Trip_live_display").attr("style", "display:''");
	var data = [];
	//alert("1"+vehicleData);
	// var series = Math.floor(Math.random() * 10) + 1;
	series = vehicleData["aaData"].length;
	//alert("2"+series);

	for ( var i = 0; i < series; i++) {
		data[i] = {
			label : vehicleData["aaData"][i]["2"] + "-"
					+ (vehicleData["aaData"][i]["0"]),
			data : vehicleData["aaData"][i]["1"] ,
			status : vehicleData["aaData"][i]["2"]
		}
	}
	$
			.plot(
					$("#alertspie7"),
					data,
					{
						series : {
							pie : {
								show : true,
								radius : 1,
								// tilt : 0.5,
								label : {
									show : true,
									radius : 0.5,
									formatter : function(label, series) {
										var arr = label.split("-");
										return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
												+ arr[1] + '</div>';
									},
									background : {
										opacity :0.8
									}
								}
							}

						},
						colors : [ '#FF6600', "#FF0000","#088A08","#afd8f8","#cb4b4b",'#FF0FFF', "#afd8f8", 
								, "#9440ed",  '#000000' ],
						grid : {
							hoverable : true,
							clickable : true
						}

					});



}
//end total trip count plot

//start schedule trip count piechart
//plot pie chart for total trip count
function plotScheduleTotalTripPie(vehicleData) {
	
	
	$("#schedule_live_display").attr("style", "display:''");
	var data = [];
	
	// var series = Math.floor(Math.random() * 10) + 1;
	series = vehicleData["aaData"].length;

	for ( var i = 0; i < series; i++) {
		data[i] = {
			label : vehicleData["aaData"][i]["2"] + "-"
					+ (vehicleData["aaData"][i]["0"]),
			data : vehicleData["aaData"][i]["1"] ,
			status : vehicleData["aaData"][i]["2"]
		}
	}
	$
			.plot(
					$("#alertspie8"),
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
										
										return '<div style="font-size:8pt;text-align:right;color:white; ">'
												+ arr[1] + '</div>';
									},
									background : {
										opacity : 0.8
									}
								}
							}

						},
						colors : [ "#088A08", "#FF0000",'#FF6600',"#4da74d","#cb4b4b",'#FF0FFF', "#afd8f8", 
								, "#9440ed",  '#000000' ],
						grid : {
							hoverable : true,
							clickable : true
						}

					});



}
//end total trip count plot

//end schedule pie chart
//start crew allocation
function plotCrewAlloactionPie(vehicleData) {
	
	
	$("#crewdata_live_display").attr("style", "display:''");
	var data = [];
	
	// var series = Math.floor(Math.random() * 10) + 1;
	series = vehicleData["aaData"].length;

	for ( var i = 0; i < series; i++) {
		data[i] = {
			label : vehicleData["aaData"][i]["2"] + "-"
					+ (vehicleData["aaData"][i]["0"]),
			data : vehicleData["aaData"][i]["1"] ,
			status : vehicleData["aaData"][i]["2"]
		}
	}
	$
			.plot(
					$("#alertspie9"),
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
						colors : [ "#088A08", "#FF0000",'#FF6600',"#4da74d","#cb4b4b",'#FF0FFF', "#afd8f8", 
								, "#9440ed",  '#000000' ],
						grid : {
							hoverable : true,
							clickable : true
						}

					});



}
//end crew aalocation

//start crew allocation
function plotTotalOnlineOfflineDepotPie(vehicleData) {
	
	
	$("#Depothealth_live_display").attr("style", "display:''");
	var data = [];
	
	// var series = Math.floor(Math.random() * 10) + 1;
	series = vehicleData["aaData"].length;

	for ( var i = 0; i < series; i++) {
		data[i] = {
			label : vehicleData["aaData"][i]["2"] + "-"
					+ (vehicleData["aaData"][i]["0"]),
			data : vehicleData["aaData"][i]["1"] ,
			status : vehicleData["aaData"][i]["2"]
		}
	}
	$
			.plot(
					$("#alertspie10"),
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
						colors : [ "#088A08", "#FF0000",'#FF6600',"#4da74d","#cb4b4b",'#FF0FFF', "#afd8f8", 
								, "#9440ed",  '#000000' ],
						grid : {
							hoverable : true,
							clickable : true
						}

					});



}
//end crew aalocation

