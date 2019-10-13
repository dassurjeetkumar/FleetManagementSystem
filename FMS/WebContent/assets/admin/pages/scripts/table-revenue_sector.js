
var TableManagedRevenueSector = function() {

	return {

		// main function to initiate the module
		init : function() {

			if (!jQuery().dataTable) {
				return;
			}

			
			
			 $('#revenueSectorView').dataTable({
	            	"aaSorting": [
	            	                [0, 'asc']
	            	            ],
	                "aLengthMenu": [
	                    [10,25,50,100],
	                    [10,25,50,100], // change per page values here
	                ],
	                // set the initial value
	                "iDisplayLength": 10,
	                "bProcessing" : true,
	                "bServerSide" : true,
	                "sAjaxSource" : "viewRevenueData.action",
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
			 
			 $('#lineSectorView').dataTable({
	            	"aaSorting": [
	            	                [0, 'asc']
	            	            ],
	                "aLengthMenu": [
	                    [10,25,50,100],
	                    [10,25,50,100], // change per page values here
	                ],
	                // set the initial value
	                "iDisplayLength": 10,
	                "bProcessing" : true,
	                "bServerSide" : true,
	                "sAjaxSource" : "viewLineData.action",
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
			 
			 
			 
			 	jQuery('#revenueSectorView_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#revenueSectorView_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	           
	            $('#etmdevicehistoryTable').dataTable({
	            	"aaSorting": [
	            	                [1, 'asc']
	            	            ],
	                "aLengthMenu": [
	                    [10,25,50,100,-1],
	                    [10,25,50,100, "All"], 
	                    // change per page values here
	                ],
	                // set the initial value
	                "iDisplayLength": 10,
	                "bProcessing" : true,
	                "bServerSide" : true,
	                "sAjaxSource" : "AjaxEtmDeviceView.action",
	                "sPaginationType": "bootstrap",
	                "oLanguage": {
	                    "sLengthMenu": "_MENU_ records",
	                    "oPaginate": {
	                        "sPrevious": "Prev",
	                        "sNext": "Next"
	                    }
	                },
	               "aoColumnDefs": [
	                    { "bSortable": false, "aTargets": [0] },
	                    { "bSearchable": false, "aTargets": [0]  }
	                ]
	                
	            });
	         jQuery('#etmdevicehistoryTable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	         jQuery('#etmdevicehistoryTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline");
	        	
	            $('#etmpicupfromdepotTable').dataTable({
	            	"aaSorting": [
	            	                [1, 'asc']
	            	            ],
	                "aLengthMenu": [
	                    [10,25,50,100,-1],
	                    [10,25,50,100, "All"], 
	                    // change per page values here
	                ],
	                // set the initial value
	                "iDisplayLength": 10,
	                "bProcessing" : true,
	                "bServerSide" : true,
	                "sAjaxSource" : "ajaxgetetmpickdepot.action",
	                "sPaginationType": "bootstrap",
	                "oLanguage": {
	                    "sLengthMenu": "_MENU_ records",
	                    "oPaginate": {
	                        "sPrevious": "Prev",
	                        "sNext": "Next"
	                    }
	                },
	               "aoColumnDefs": [
	                    { "bSortable": false, "aTargets": [0] },
	                    { "bSearchable": false, "aTargets": [0]  }
	                ]
	                
	            });
	         jQuery('#etmpicupfromdepotTable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	         jQuery('#etmpicupfromdepotTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline");
	        	
	         $('#etmresolvetrimaxTable').dataTable({
	            	"aaSorting": [
	            	                [1, 'asc']
	            	            ],
	                "aLengthMenu": [
	                    [10,25,50,100,-1],
	                    [10,25,50,100, "All"], 
	                    // change per page values here
	                ],
	                // set the initial value
	                "iDisplayLength": 10,
	                "bProcessing" : true,
	                "bServerSide" : true,
	                "sAjaxSource" : "ajaxgetetmnotreslove.action",
	                "sPaginationType": "bootstrap",
	                "oLanguage": {
	                    "sLengthMenu": "_MENU_ records",
	                    "oPaginate": {
	                        "sPrevious": "Prev",
	                        "sNext": "Next"
	                    }
	                },
	               "aoColumnDefs": [
	                    { "bSortable": false, "aTargets": [0] },
	                    { "bSearchable": false, "aTargets": [0]  }
	                ]
	                
	            });
	         jQuery('#etmpicupfromdepotTable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	         jQuery('#etmpicupfromdepotTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline");
	        	
	         $('#etmrecfromverifTable').dataTable({
	            	"aaSorting": [
	            	                [1, 'asc']
	            	            ],
	                "aLengthMenu": [
	                    [10,25,50,100,-1],
	                    [10,25,50,100, "All"], 
	                    // change per page values here
	                ],
	                // set the initial value
	                "iDisplayLength": 10,
	                "bProcessing" : true,
	                "bServerSide" : true,
	                "sAjaxSource" : "ajaxgetrecfromverifone.action",
	                "sPaginationType": "bootstrap",
	                "oLanguage": {
	                    "sLengthMenu": "_MENU_ records",
	                    "oPaginate": {
	                        "sPrevious": "Prev",
	                        "sNext": "Next"
	                    }
	                },
	               "aoColumnDefs": [
	                    { "bSortable": false, "aTargets": [0] },
	                    { "bSearchable": false, "aTargets": [0]  }
	                ]
	                
	            });
	         jQuery('#etmpicupfromdepotTable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	         jQuery('#etmpicupfromdepotTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline");
	        	
	         $('#etmrecfromtrimaxTable').dataTable({
	            	"aaSorting": [
	            	                [1, 'asc']
	            	            ],
	                "aLengthMenu": [
	                    [10,25,50,100,-1],
	                    [10,25,50,100, "All"], 
	                    // change per page values here
	                ],
	                // set the initial value
	                "iDisplayLength": 10,
	                "bProcessing" : true,
	                "bServerSide" : true,
	                "sAjaxSource" : "ajaxgetrecfromtrimax.action",
	                "sPaginationType": "bootstrap",
	                "oLanguage": {
	                    "sLengthMenu": "_MENU_ records",
	                    "oPaginate": {
	                        "sPrevious": "Prev",
	                        "sNext": "Next"
	                    }
	                },
	               "aoColumnDefs": [
	                    { "bSortable": false, "aTargets": [0] },
	                    { "bSearchable": false, "aTargets": [0]  }
	                ]
	                
	            });
	         jQuery('#etmpicupfromdepotTable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	         jQuery('#etmpicupfromdepotTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline");
	        
	         $('#etmresolveTable').dataTable({
	            	"aaSorting": [
	            	                [1, 'asc']
	            	            ],
	                "aLengthMenu": [
	                    [10,25,50,100,-1],
	                    [10,25,50,100, "All"], 
	                    // change per page values here
	                ],
	                // set the initial value
	                "iDisplayLength": 10,
	                "bProcessing" : true,
	                "bServerSide" : true,
	                "sAjaxSource" : "ajaxgetetmresolved.action",
	                "sPaginationType": "bootstrap",
	                "oLanguage": {
	                    "sLengthMenu": "_MENU_ records",
	                    "oPaginate": {
	                        "sPrevious": "Prev",
	                        "sNext": "Next"
	                    }
	                },
	               "aoColumnDefs": [
	                    { "bSortable": false, "aTargets": [0] },
	                    { "bSearchable": false, "aTargets": [0]  }
	                ]
	                
	            });
	         jQuery('#etmpicupfromdepotTable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	         jQuery('#etmpicupfromdepotTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline");
	        
	         
	         
	         $('#serviceTable').dataTable({
	            	"aaSorting": [
	            	                [1, 'asc']
	            	            ],
	                "aLengthMenu": [
	                    [10,25,50,100,-1],
	                    [10,25,50,100,"All"], 
	                    // change per page values here
	                ],
	                // set the initial value
	                "iDisplayLength": 10,
	                "bProcessing" : true,
	                "bServerSide" : true,
	                "sAjaxSource" : "AjaxserviceTableAction.action",
	                "sPaginationType": "bootstrap",
	                "oLanguage": {
	                    "sLengthMenu": "_MENU_ records",
	                    "oPaginate": {
	                        "sPrevious": "Prev",
	                        "sNext": "Next"
	                    }
	                },
	               "aoColumnDefs": [
	                    { "bSortable": false, "aTargets": [0] },
	                    { "bSearchable": false, "aTargets": [0]  }
	                ]
	                
	            });
	         jQuery('#serviceTable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	         jQuery('#serviceTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline");
			
	         $('#serviceTableDuplicate').dataTable({
	            	"aaSorting": [
	            	                [1, 'asc']
	            	            ],
	                "aLengthMenu": [
	                    [10,25,50,100],
	                    [10,25,50,100], 
	                    // change per page values here
	                ],
	                // set the initial value
	                "iDisplayLength": 10,
	                "bProcessing" : true,
	                "bServerSide" : true,
	                "sAjaxSource" : "AjaxserviceTableDuplicateAction.action",
	                "sPaginationType": "bootstrap",
	                "oLanguage": {
	                    "sLengthMenu": "_MENU_ records",
	                    "oPaginate": {
	                        "sPrevious": "Prev",
	                        "sNext": "Next"
	                    }
	                },
	               "aoColumnDefs": [
	                    { "bSortable": false, "aTargets": [0] },
	                    { "bSearchable": false, "aTargets": [0]  }
	                ]
	                
	            });
	         jQuery('#serviceTableDuplicate_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	         jQuery('#serviceTableDuplicate_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline");
			
			
			
	         $('#etmreceivedbyDepotTable').dataTable({
	            	"aaSorting": [
	            	                [1, 'asc']
	            	            ],
	                "aLengthMenu": [
	                    [10,25,50,100],
	                    [10,25,50,100], 
	                    // change per page values here
	                ],
	                // set the initial value
	                "iDisplayLength": 10,
	                "bProcessing" : true,
	                "bServerSide" : true,
	                "sAjaxSource" : "AjaxEtmReceivedByDepot.action",
	                "sPaginationType": "bootstrap",
	                "oLanguage": {
	                    "sLengthMenu": "_MENU_ records",
	                    "oPaginate": {
	                        "sPrevious": "Prev",
	                        "sNext": "Next"
	                    }
	                },
	               "aoColumnDefs": [
	                    { "bSortable": false, "aTargets": [0] },
	                    { "bSearchable": false, "aTargets": [0]  }
	                ]
	                
	            });
	         jQuery('#etmreceivedbyDepotTable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	         jQuery('#etmreceivedbyDepotTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline");
	        	 
			
			
//			Customer feedBack and Complaint section
	         
	         $('#customerFeedBackTable').dataTable({
	            	"aaSorting": [
	            	                [1, 'asc']
	            	            ],
	                "aLengthMenu": [
	                    [10,25,50,100],
	                    [10,25,50,100], 
	                    // change per page values here
	                ],
	                // set the initial value
	                "iDisplayLength": 10,
	                "bProcessing" : true,
	                "bServerSide" : true,
	                "sAjaxSource" : "AjaxFeedBack.action",
	                "sPaginationType": "bootstrap",
	                "oLanguage": {
	                    "sLengthMenu": "_MENU_ records",
	                    "oPaginate": {
	                        "sPrevious": "Prev",
	                        "sNext": "Next"
	                    }
	                },
	               "aoColumnDefs": [
	                    { "bSortable": false, "aTargets": [0] },
	                    { "bSearchable": false, "aTargets": [0]  }
	                ]
	                
	            });
	         jQuery('#customerFeedBackTable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	         jQuery('#customerFeedBackTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline");
	        	 
	         
	         
	         $('#customerComplaintTable').dataTable({
	            	"aaSorting": [
	            	                [1, 'asc']
	            	            ],
	                "aLengthMenu": [
	                    [10,25,50,100],
	                    [10,25,50,100], 
	                    // change per page values here
	                ],
	                // set the initial value
	                "iDisplayLength": 10,
	                "bProcessing" : true,
	                "bServerSide" : true,
	                "sAjaxSource" : "AjaxCustomerComplaint.action",
	                "sPaginationType": "bootstrap",
	                "oLanguage": {
	                    "sLengthMenu": "_MENU_ records",
	                    "oPaginate": {
	                        "sPrevious": "Prev",
	                        "sNext": "Next"
	                    }
	                },
	               "aoColumnDefs": [
	                    { "bSortable": false, "aTargets": [0] },
	                    { "bSearchable": false, "aTargets": [0]  }
	                ]
	                
	            });
	         jQuery('#customerComplaintTable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	         jQuery('#customerComplaintTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline");
	        	 
	         
	         
	         
//	         Customer feedBack and Complaint section
			
			
			


			
//			$('#revenueSectorView').dataTable({
//				"aaSorting" : [ [ 0, 'asc' ] ],
//				"aLengthMenu" : [ [ 10, 25, 50, 100 ], [ 10, 25, 50, 100 ] // change
//																	// per page
//																	// values
//																	// here
//				],
//				// set the initial value
//				"iDisplayLength" : 10,
//				"bProcessing" : true,
//				"bServerSide" : true,
//				"sAjaxSource" : "viewRevenueData.action",
//				"sPaginationType" : "bootstrap"
//			
//			/* "oLanguage": { "sLengthMenu": "_MENU_ records", "oPaginate": {
//			 "sPrevious": "Prev", "sNext": "Next" } }, "aoColumnDefs": [ {
//			 'bSortable': false, 'aTargets': [0] }, { "bSearchable": false,
//			 "aTargets": [ 0 ] } ]*/
//			
//			});
//			jQuery('#revenueSectorView_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
//            jQuery('#revenueSectorView_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
//			
//	            
	          
			/** ******************************************************* */
		}

	};

}();

