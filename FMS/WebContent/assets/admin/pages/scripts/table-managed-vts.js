var TableManagedVts = function() {

	return {

		// main function to initiate the module
		init : function() {

			if (!jQuery().dataTable) {
				return;
			}

			// Strat Code After This

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
			jQuery('#viewVehicleAlertConfig_wrapper .dataTables_filter input')
					.addClass("form-control input-small input-inline"); // modify
			// table
			// search
			// input
			jQuery('#viewVehicleAlertConfig_wrapper .dataTables_length select')
					.addClass("form-control input-xsmall input-inline"); // modify
			// table
			// per
			// page
			// dropdown

			var dataerange = $('#dashboard-report-range span').html();
			var parameter = $("#inoutValue").val();
			var depot_id=$("#depotlist").val();
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
										if (aaData[7] == "Early") {
											sDirectionClass = "success";
										} else if (aaData[7] == "Late") {
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
										if (aaData[7] == "Early") {
											sDirectionClass = "success";
										} else if (aaData[7] == "Late") {
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
				dataType : 'json',
				url : 'showVehicleDepotin-outList1.action?daterange=' + dataerange
				+ "&parameter1=" + parameter+ "&depot_id=" + depot_id
				+ "&iDisplayStart=0&iDisplayLength=10&sSearch=",
				success : function(result) {
					var obj = result;
					
					if (obj != null) {
						$("#Early").html(
								"<B><center>Total Early Arrival Vehicles:</center><h4><center>"
										+ obj["countData"][1] + "</center>");
						$("#Late").html(
								"<B><center>Total Late Arrival Vehicles:</center><h4><center>"
										+ obj["countData"][0] + "</center>");
						$("#Ontime").html(
								"<B><center>Total Ontime Arrival Vehicles:</center><h4><center>"
										+ obj["countData"][2] + "</center>");
					}

				}
			});

			// Schedule Vehicle Report..
			$('#viewVtsAlertConfigMaster').dataTable({
				"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
				// per
				// page
				// values
				// here
				],
				// set the initial value
				"iDisplayLength" : 5,
				"sAjaxSource" : "showVehicleAlertConfigMaster.action",
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
			jQuery('#viewVtsAlertConfigMaster_wrapper .dataTables_filter input')
					.addClass("form-control input-medium input-inline"); // modify
			// table
			// search input
			jQuery(
					'#viewVtsAlertConfigMaster_wrapper .dataTables_length select')
					.addClass("form-control input-xsmall input-inline");

			// End Code Before This

		}

	};

}();
