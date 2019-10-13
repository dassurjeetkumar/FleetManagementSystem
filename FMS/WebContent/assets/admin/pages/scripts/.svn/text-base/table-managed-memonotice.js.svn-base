
var TableManagedMemoNotice = function() {

	return {

		// main function to initiate the module
		init : function() {

			if (!jQuery().dataTable) {
				return;
			}

			
		
//			$('#sample_3').dataTable({
//				"aaSorting" : [ [ 1, 'asc' ] ],
//				"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
//																			// per
//																			// page
//																			// values
//																			// here
//				],
//				// set the initial value
//				"iDisplayLength" : 5,
//				"sPaginationType" : "bootstrap",
//				"oLanguage" : {
//					"sLengthMenu" : "_MENU_ records",
//					"oPaginate" : {
//						"sPrevious" : "Prev",
//						"sNext" : "Next"
//					}
//				},
//				"aoColumnDefs" : [ {
//					'bSortable' : false,
//					'aTargets' : [ 0 ]
//				}, {
//					"bSearchable" : false,
//					"aTargets" : [ 0 ]
//				} ]
//			});
			  //memo start
            $('#memonoticeView').dataTable({
				"aaSorting": [[1, 'asc']],
                "aLengthMenu": [[10,25, 50, 100],[10,25, 50, 100]],
                // set the initial value
                "iDisplayLength": 10,
                "bProcessing" : true,
                "bServerSide" : true,
                "sAjaxSource" : "memoListAction.action",
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
                    { "bSearchable": false, "aTargets": [0] }
                ]
                
            });
			jQuery('#memonoticeView_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#memonoticeView_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
            //memo end
			

			

			/** ******************************************************* */
		}

	};

}();

