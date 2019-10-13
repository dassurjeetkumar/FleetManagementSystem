var TableManagedBreakType = function() {

	return {

		// main function to initiate the module
		init : function() {

			if (!jQuery().dataTable) {
				return;
			}

// Strat Code After This

			$('#breaktype').dataTable({
				"aaSorting" : [ [ 1, 'asc' ] ],
				"aLengthMenu" : [ [ 10,20, 50, 100 ], [ 10,20, 50, 100 ] // change
																	// per page
																	// values
																	// here
				],
				// set the initial value
				"iDisplayLength" : 10,
				"bProcessing" : true,
				"bServerSide" : true,
				"sAjaxSource" : "breaktype.action",
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

			jQuery('#breaktype_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#breaktype_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
            jQuery('#breaktype_wrapper .dataTables_length select').select2(); // initialize select2 dropdown
            /**********************************************************/



//End Code Before This

		}

	};

}();
