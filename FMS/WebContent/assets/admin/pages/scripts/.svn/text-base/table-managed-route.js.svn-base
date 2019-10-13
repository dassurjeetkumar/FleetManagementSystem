var TableManagedRoute = function() {

	return {

		// main function to initiate the module
		init : function() {

			if (!jQuery().dataTable) {
				return;
			}

			// Strat Code After This

			$('#floor').dataTable({
				"aaSorting" : [ [ 0, 'desc' ] ],
				"aLengthMenu" : [ [ 10, 20, 50, 100 ], [ 10, 20, 50, 100 ] // change
				// per
				// page
				// values
				// here
				],
				// set the initial value
				"iDisplayLength" : 10,
				"bProcessing" : true,
				"bServerSide" : true,
				"sAjaxSource" : "FloorAction.action",
				"sPaginationType" : "bootstrap",
				"oLanguage" : {
					"sLengthMenu" : "_MENU_ records",
					"oPaginate" : {
						"sPrevious" : "Prev",
						"sNext" : "Next"
					}
				},
				"aoColumnDefs" : [ {
					'bSortable' : true,
					'aTargets' : [ 0 ]
				}, {
					"bSearchable" : false,
					"aTargets" : [ 0 ]
				} ]

			});

			jQuery('#floor_wrapper .dataTables_filter input').addClass(
					"form-control input-medium input-inline"); // modify
			// table
			// search
			// input
			jQuery('#floor_wrapper .dataTables_length select').addClass(
					"form-control input-small"); // modify table per
			// page dropdown
			jQuery('#floor_wrapper.dataTables_length select').select2({
				showSearchInput : true
			// hide search box with special css class
			}); // initialize select2 dropdown

			$('#bay').dataTable({
				"aaSorting" : [ [ 0, 'desc' ] ],
				"aLengthMenu" : [ [ 10, 20, 50, 100 ], [ 10, 20, 50, 100 ] // change
				// per
				// page
				// values
				// here
				],
				// set the initial value
				"iDisplayLength" : 10,
				"bProcessing" : true,
				"bServerSide" : true,
				"sAjaxSource" : "BayAction.action",
				"sPaginationType" : "bootstrap",
				"oLanguage" : {
					"sLengthMenu" : "_MENU_ records",
					"oPaginate" : {
						"sPrevious" : "Prev",
						"sNext" : "Next"
					}
				},
				"aoColumnDefs" : [ {
					'bSortable' : true,
					'aTargets' : [ 0 ]
				}, {
					"bSearchable" : false,
					"aTargets" : [ 0 ]
				} ]

			});
			jQuery('#bay_wrapper .dataTables_filter input').addClass(
					"form-control input-medium input-inline"); // modify
			// table
			// search
			// input
			jQuery('#bay_wrapper .dataTables_length select').addClass(
					"form-control input-small"); // modify table per
			// page dropdown
			jQuery('#bay_wrapper.dataTables_length select').select2({
				showSearchInput : true
			// hide search box with special css class
			}); // initialize select2 dropdown

			$('#platform').dataTable({
				"aaSorting" : [ [ 0, 'desc' ] ],
				"aLengthMenu" : [ [ 10, 20, 50, 100 ], [ 10, 20, 50, 100 ] // change
				// per
				// page
				// values
				// here
				],
				// set the initial value
				"iDisplayLength" : 10,
				"bProcessing" : true,
				"bServerSide" : true,
				"sAjaxSource" : "PlatformAction.action",
				"sPaginationType" : "bootstrap",
				"oLanguage" : {
					"sLengthMenu" : "_MENU_ records",
					"oPaginate" : {
						"sPrevious" : "Prev",
						"sNext" : "Next"
					}
				},
				"aoColumnDefs" : [ {
					'bSortable' : true,
					'aTargets' : [ 0 ]
				}, {
					"bSearchable" : false,
					"aTargets" : [ 0 ]
				} ]

			});
			jQuery('#platform_wrapper .dataTables_filter input').addClass(
					"form-control input-medium input-inline"); // modify
			// table
			// search
			// input
			jQuery('#platform_wrapper .dataTables_length select').addClass(
					"form-control input-small"); // modify table per
			// page dropdown
			jQuery('#platform_wrapper.dataTables_length select').select2({
				showSearchInput : true
			// hide search box with special css class
			}); // initialize select2 dropdown

			$('#route').dataTable({
				"aaSorting" : [ [ 1, 'asc' ] ],
				"aLengthMenu" : [ [ 10, 25, 50, 100 ], [ 10, 25, 50, 100 ] // change
				// per page
				// values
				// here
				],
				// set the initial value
				"iDisplayLength" : 10,
				"bProcessing" : true,
				"bServerSide" : true,
				"sAjaxSource" : "RouteAjaxAction.action",
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
			jQuery('#route_wrapper .dataTables_filter input').addClass(
					"form-control input-small input-inline"); // modify table
			// search input
			jQuery('#route_wrapper .dataTables_length select').addClass(
					"form-control input-xsmall input-inline"); // modify table
			// per page
			// dropdown
			// jQuery('#sheduletypeshow_wrapper .dataTables_length
			// select').select2(); // initialize select2 dropdown
			/*
			 * "oLanguage": { "sLengthMenu": "_MENU_ records", "oPaginate": {
			 * "sPrevious": "Prev", "sNext": "Next" } }, "aoColumnDefs": [ {
			 * 'bSortable': false, 'aTargets': [0] }, { "bSearchable": false,
			 * "aTargets": [ 0 ] } ]
			 */

			// End Code Before This
		var busStopId=$("#busstopIdRoute").val();
		$('#route_busstop').dataTable({
			"aaSorting" : [ [ 1, 'asc' ] ],
			"aLengthMenu" : [ [ 10, 25, 50, 100 ], [ 10, 25, 50, 100 ] // change
			// per page
			// values
			// here
			],
			// set the initial value
			"iDisplayLength" : 10,
			"bProcessing" : true,
			"bServerSide" : true,
			"sAjaxSource" : "getRouteInfo.action?busstopId="+busStopId,
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
		jQuery('#route_busstop .dataTables_filter input').addClass(
				"form-control input-small input-inline"); // modify table
		// search input
		jQuery('#route_busstop .dataTables_length select').addClass(
				"form-control input-xsmall input-inline"); // modify table
		
		}

	};

}();
