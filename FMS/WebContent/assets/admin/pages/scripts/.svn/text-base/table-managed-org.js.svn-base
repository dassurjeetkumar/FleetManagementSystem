var TableManagedOrg = function() {

	return {

		// main function to initiate the module
		init : function() {

			if (!jQuery().dataTable) {
				return;
			}

// Strat Code After This
			
			$('#orgchart').dataTable({
				"aaSorting" : [ [ 1, 'asc' ] ],
				"aLengthMenu" : [ [ 10,25, 50, 100 ], [ 10,25, 50, 100 ] // change
																	// per page
																	// values
																	// here
				],
				// set the initial value
				"iDisplayLength" : 10,
				"bProcessing" : true,
				"bServerSide" : true,
				"sAjaxSource" : "orgChrart.action",
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
			
			
			jQuery('#orgchart_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table
														// search input
			jQuery('#orgchart_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table
														// per page
														// dropdown
			//jQuery('#orgchart_wrapper .dataTables_length select').select2();
			
			
			$('#orgType').dataTable({
				"aaSorting" : [ [ 1, 'asc' ] ],
				"aLengthMenu" : [ [ 10,25, 50, 100 ], [ 10,25, 50, 100 ] // change
																	// per page
																	// values
																	// here
				],
				// set the initial value
				"iDisplayLength" : 10,
				"bProcessing" : true,
				"bServerSide" : true,
				"sAjaxSource" : "orgtypeview.action",
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
			
			
			jQuery('#orgType_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table
														// search input
			jQuery('#orgType_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table
														// per page
														// dropdown
			//jQuery('#orgType_wrapper .dataTables_length select').select2();
			
			


			$('#corporation').dataTable({
				"aaSorting" : [ [ 1, 'asc' ] ],
				"aLengthMenu" : [ [ 10,25, 50, 100 ], [ 10,25, 50, 100 ] // change
																	// per page
																	// values
																	// here
				],
				// set the initial value
				"iDisplayLength" : 10,
				"bProcessing" : true,
				"bServerSide" : true,
				"sAjaxSource" : "ShowCorporationshow.action",
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
			
			
			jQuery('#corporation_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table
														// search input
			jQuery('#corporation_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table
														// per page
														// dropdown
			//jQuery('#corporation_wrapper .dataTables_length select').select2();

			$('#division').dataTable({
				"aaSorting" : [ [ 1, 'asc' ] ],
				"aLengthMenu" : [ [ 10,25, 50, 100 ], [ 10,25, 50, 100 ] // change
																	// per page
																	// values
																	// here
				],
				// set the initial value
				"iDisplayLength" : 10,
				"bProcessing" : true,
				"bServerSide" : true,
				"sAjaxSource" : "Showdivision.action",
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
			
			
			jQuery('#division_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table
														// search input
			jQuery('#division_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table
														// per page
														// dropdown
			//jQuery('#division_wrapper .dataTables_length select').select2();
			
			$('#Showdepots').dataTable({
				"aaSorting" : [ [ 1, 'asc' ] ],
				"aLengthMenu" : [ [ 10,25, 50, 100 ], [ 10,25, 50, 100 ] // change
																	// per page
																	// values
																	// here
				],
				// set the initial value
				"iDisplayLength" : 10,
				"bProcessing" : true,
				"bServerSide" : true,
				"sAjaxSource" : "Showdepots.action",
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
			
			
			jQuery('#Showdepots_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table
														// search input
			jQuery('#Showdepots_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table
														// per page
														// dropdown
			//jQuery('#Showdepots_wrapper .dataTables_length select').select2();
			
			
			$('#Showbusstations').dataTable({
				"aaSorting" : [ [ 1, 'asc' ] ],
				"aLengthMenu" : [ [ 10,25, 50, 100 ], [ 10,25, 50, 100 ] // change
																	// per page
																	// values
																	// here
				],
				// set the initial value
				"iDisplayLength" : 10,
				"bProcessing" : true,
				"bServerSide" : true,
				"sAjaxSource" : "Showbustations.action",
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
			
			
			jQuery('#Showdepots_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table
														// search input
			jQuery('#Showdepots_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table
														// per page
														// dropdown
			//jQuery('#Showdepots_wrapper .dataTables_length select').select2();
			

//End Code Before This

		}

	};

}();
