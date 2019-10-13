var TableManagedTransport = function() {

	return {

		// main function to initiate the module
		init : function() {

			if (!jQuery().dataTable) {
				return;
			}

// Strat Code After This

			$('#tripType').dataTable({
				"aaSorting": [[1, 'asc']],
                "aLengthMenu": [[10,25, 50, 100],[10,25, 50, 100]],
                // set the initial value
                "iDisplayLength": 10,
                "bProcessing" : true,
                "bServerSide" : true,
                "sAjaxSource" : "TripTypeAction.action",
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
                    { "bSearchable": false, "aTargets": [0] },
                    { "sClass": "url", "aTargets": [ 4 ] }
                ]
                
            });
			jQuery('#tripType_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#tripType_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
            //jQuery('#tripType_wrapper .dataTables_length select').select2(); // initialize select2 dropdown

			
			$('#busStopType').dataTable({
				"aaSorting": [[1, 'asc']],
                "aLengthMenu": [[10,25, 50, 100],[10,25, 50, 100]],
                // set the initial value
                "iDisplayLength": 10,
                "bProcessing" : true,
                "bServerSide" : true,
                "sAjaxSource" : "BusStopTypeAction.action",
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
			jQuery('#busStopType_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#busStopType_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
            //jQuery('#busStopType_wrapper .dataTables_length select').select2(); // initialize select2 dropdown



			$('#sheduletypeshow').dataTable({
				"aaSorting" : [ [ 1, 'asc' ] ],
				"aLengthMenu" : [ [ 10,20, 50, 100 ], [ 10,20, 50, 100 ] 
							],
				// set the initial value
				"iDisplayLength" : 10,
				"bProcessing" : true,
				"bServerSide" : true,
				"bDestroy": true,
				"sAjaxSource" : "sheduleTypeview.action",
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
			jQuery('#sheduletypeshow_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#sheduletypeshow_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
            jQuery('#sheduletypeshow_wrapper .dataTables_length select').select2(); // initialize select2 dropdown
            /**********************************************************/

			$('#busstoptable').dataTable({
				"aaSorting" : [ [ 1, 'asc' ] ],
				"aLengthMenu" : [ [ 10, 25, 50, 100 ], [ 10, 25, 50, 100 ] // change
																			// per
																			// page
																			// values
																			// here
				],
				// set the initial value
				"iDisplayLength" : 10,
				"bProcessing" : true,
				"bServerSide" : true,
				"sAjaxSource" : "BusStopAjaxAction.action",
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
				jQuery('#busstoptable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#busstoptable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	            //jQuery('#sheduletypeshow_wrapper .dataTables_length select').select2(); // initialize select2 dropdown
			/*
			 * "oLanguage": { "sLengthMenu": "_MENU_ records", "oPaginate": {
			 * "sPrevious": "Prev", "sNext": "Next" } }, "aoColumnDefs": [ {
			 * 'bSortable': false, 'aTargets': [0] }, { "bSearchable": false,
			 * "aTargets": [ 0 ] } ]
			 */
			
			$('#busStopGroup').dataTable({
				"aaSorting" : [ [ 1, 'asc' ] ],
				"aLengthMenu" : [ [ 10, 25, 50, 100 ], [ 10, 25, 50, 100 ] // change
																			// per
																			// page
																			// values
																			// here
				],
				// set the initial value
				"iDisplayLength" : 10,
				"bProcessing" : true,
				"bServerSide" : true,
				"sAjaxSource" : "BusStopGroupAjaxAction.action",
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
				jQuery('#busStopGroup_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#busStopGroup_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	          
			/*
			 * "oLanguage": { "sLengthMenu": "_MENU_ records", "oPaginate": {
			 * "sPrevious": "Prev", "sNext": "Next" } }, "aoColumnDefs": [ {
			 * 'bSortable': false, 'aTargets': [0] }, { "bSearchable": false,
			 * "aTargets": [ 0 ] } ]
			 */
			
			
			 $('#scheduletable').dataTable({
				 "aaSorting" : [ [ 1, 'asc' ] ],
	                "aLengthMenu": [
	                    [10,25, 50, 100],
	                    [10,25, 50, 100] // change per page values here
	                ],
	                // set the initial value
	                "iDisplayLength": 10,
	                "bProcessing" : true,
	                "bServerSide" : true,
	                "sAjaxSource" : "GetScheduleList.action",
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
			 
			 jQuery('#scheduletable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#scheduletable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per pag
			 

				 $('#rscheduletable').dataTable({
					 "aaSorting" : [ [ 1, 'asc' ] ],
		                "aLengthMenu": [
		                    [10,25, 50, 100],
		                    [10,25, 50, 100] // change per page values here
		                ],
		                // set the initial value
		                "iDisplayLength": 10,
		                "bProcessing" : true,
		                "bServerSide" : true,
		                "sAjaxSource" : "GetRationalizedScheduleList.action",
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
				 
				 jQuery('#rscheduletable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
		            jQuery('#rscheduletable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per pag
				 
		            $('#rformfour').dataTable({
						 "aaSorting" : [ [ 1, 'asc' ] ],
			                "aLengthMenu": [
			                    [10,25, 50, 100],
			                    [10,25, 50, 100] // change per page values here
			                ],
			                // set the initial value
			                "iDisplayLength": 10,
			                "bProcessing" : true,
			                "bServerSide" : true,
			                "sAjaxSource" : "GetRationalizedFormFourList.action",
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
					 
					 jQuery('#rformfour_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
			            jQuery('#rformfour_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per pag
			            $('#pformfour').dataTable({
							 "aaSorting" : [ [ 1, 'asc' ] ],
				                "aLengthMenu": [
				                    [10,25, 50, 100],
				                    [10,25, 50, 100] // change per page values here
				                ],
				                // set the initial value
				                "iDisplayLength": 10,
				                "bProcessing" : true,
				                "bServerSide" : true,
				                "sAjaxSource" : "GetPartialFormIVList.action",
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
						 
						 jQuery('#pformfour_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
				            jQuery('#pformfour_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per pag
						 
		            
		            
			 $('#formFourRelatedtable').dataTable({
				 "aaSorting" : [ [ 1, 'asc' ] ],
	                "aLengthMenu": [
	                    [10,25, 50, 100],
	                    [10,25, 50, 100] // change per page values here
	                ],
	                // set the initial value
	                "iDisplayLength": 10,
	                "bProcessing" : true,
	                "bServerSide" : true,
	                "sAjaxSource" : "GetFormFourList.action",
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
			 
			 jQuery('#formFourRelatedtable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#formFourRelatedtable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
//	            jQuery('#scheduletable_wrapper .dataTables_length select').select2(); // initialize select2 dropdown
			
			
			$('#ticketType').dataTable({
				"aaSorting": [[1, 'asc']],
                "aLengthMenu": [[10,25, 50, 100],[10,25, 50, 100]],
                // set the initial value
                "iDisplayLength": 10,
                "bProcessing" : true,
                "bServerSide" : true,
                "sAjaxSource" : "TicketTypeAction.action",
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
			jQuery('#ticketType_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#ticketType_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
            //jQuery('#ticketType_wrapper .dataTables_length select').select2(); // initialize select2 dropdown
            
            
//            
//            //PeakHour start
//            
//            //rajesh complaint datatable start
           /* $('#PeakHourtable').dataTable({
            	"aaSorting": [
            	                [1, 'asc']
            	            ],
                "aLengthMenu": [
                    [10,25,50,100],
                    [10,25,50,100], // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 10,
                "bProcessing" : true,
                "bServerSide" : true,
                "sAjaxSource" : "PeakHourListAction.action",
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
            //end
            
		 	jQuery('#PeakHourtable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#PeakHourtable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
*/           // jQuery('#vehicletable_wrapper .dataTables_length select').select2(); // initialize select2 dropdown
//            
//            
            
            
//            $('#PeakHourtable').dataTable({
//				"aaSorting": [[1, 'asc']],
//                "aLengthMenu": [[10,25, 50, 100],[10,25, 50, 100]],
//                // set the initial value
//                "iDisplayLength": 10,
//                "bProcessing" : true,
//                "bServerSide" : true,
//                "sAjaxSource" : "PeakHourListAction.action",
//                "sPaginationType": "bootstrap",
//                "oLanguage": {
//                    "sLengthMenu": "_MENU_ records",
//                    "oPaginate": {
//                        "sPrevious": "Prev",
//                        "sNext": "Next"
//                    }
//                },
//               "aoColumnDefs": [
//                    { 'bSortable': false, 'aTargets': [0] },
//                    { "bSearchable": false, "aTargets": [0] }
//                ]
//                
//            });
//			jQuery('#ticketType_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
//            jQuery('#ticketType_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
            //jQuery('#ticketType_wrapper .dataTables_length select').select2(); // initialize select2 dropdown
            //peak Hour End
            
            
            $('#caseTypeTable').dataTable({
            	"aaSorting": [
            	                [0, 'asc'],[1, 'asc'],[2, 'asc'],[3, 'asc']
            	            ],
                "aLengthMenu": [
                    [10,25,50,100],
                    [10,25,50,100], // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 10,
                "bProcessing" : true,
                "bServerSide" : true,
                "sAjaxSource" : "CaseTypeListAjax.action",
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
                    { "bSearchable": false, "aTargets": [ 0 ]},
                    { "sClass": "url", "aTargets": [ 2, 3 ] }
                ]
                
            });
		 	jQuery('#caseTypeTable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#caseTypeTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
           // jQuery('#caseTypeTable_wrapper .dataTables_length select').select2(); // initialize select2 dropdown
            
            
            
            $('#peakHoursView').dataTable({
				"aaSorting": [[1, 'asc']],
                "aLengthMenu": [[10,25, 50, 100],[10,25, 50, 100]],
                // set the initial value
                "iDisplayLength": 10,
                "bProcessing" : true,
                "bServerSide" : true,
                "sAjaxSource" : "PeakHourListAction.action",
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
			jQuery('#peakHoursView_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#peakHoursView_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
			
            
            
            $('#happyHoursView').dataTable({
				"aaSorting": [[1, 'asc']],
                "aLengthMenu": [[10,25, 50, 100],[10,25, 50, 100]],
                // set the initial value
                "iDisplayLength": 10,
                "bProcessing" : true,
                "bServerSide" : true,
                "sAjaxSource" : "HappyHourListAction.action",
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
			jQuery('#happyHoursView_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#happyHoursView_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
			
            
//End Code Before This

		}

	};

}();
