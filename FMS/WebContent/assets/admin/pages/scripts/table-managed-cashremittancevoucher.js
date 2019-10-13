var TableManagedCashremittancevoucher = function() {

	return {

		// main function to initiate the module
		init : function() {

			if (!jQuery().dataTable) {
				return;
			}

// Strat Code After This
			$('#Cashremittancevoucherid').dataTable({
				
	            	"aaSorting": [[1, 'asc']
	            	            ],
	                "aLengthMenu": [
	                    [10,25,50,100],
	                    [10,25,50,100], // change per page values here
	                ],
	                // set the initial value
	                "iDisplayLength": 10,
	                "bProcessing" : true,
	                "bServerSide" : true,
	                "sAjaxSource" : "CashRemittanceVoucher.action",
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
	                    { "sClass": "url", "aTargets": [ 9 ] },
	                ]
	                

	            });
			 	jQuery('#Cashremittancevoucherid_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#Cashremittancevoucherid_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
		
	            
		
		}

	};

}();
