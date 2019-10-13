var TableTollfee = function() {

	return {

		// main function to initiate the module
		init : function() {

			if (!jQuery().dataTable) {
				return;
			}
			
			// Strat Code After This
			
			 $('#tollFeeTable').dataTable({
					"aaSorting": [[1, 'asc']],
	                "aLengthMenu": [
						[10,20, 50, 100],
						[10,20, 50, 100]  // change per page values here
	                ],
	                // set the initial value
	                "iDisplayLength": 10,
	                "bProcessing" : true,
	                "bServerSide" : true,
	                "sAjaxSource" : "Tollfeeaction.action",
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
	                    { "bSearchable": false, "aTargets": [ 0 ] }
	                ]
	                
	            });
				jQuery('#tollFeeTable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#tollFeeTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown

	            
	//End Code Before This
	           


			

			}

		};

	}();