var TableManagedVehicle = function() {

	return {

		// main function to initiate the module
		init : function() {

			if (!jQuery().dataTable) {
				return;
			}

// Strat Code After This
			 $('#componenttypeView').dataTable({
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
	                "sAjaxSource" : "componentType.action",
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
	                    { "sClass": "url", "aTargets": [ 3] },
	                ]
	                
	            });
			 	jQuery('#componenttypeView_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#componenttypeView_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	           
	           
			
			 $('#vehicletable').dataTable({
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
	                "sAjaxSource" : "VehicleDetailsAjaxAction.action",
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
			 	jQuery('#vehicletable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#vehicletable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	           // jQuery('#vehicletable_wrapper .dataTables_length select').select2(); // initialize select2 dropdown
	            $('#brandtypeListView').dataTable({
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
	                "sAjaxSource" : "viewBrandTypeList.action",
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
	                    { "sClass": "url", "aTargets": [ 4 ] }
	                ]
	                
	            });
	            jQuery('#brandtypeListView_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#brandtypeListView_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	           // jQuery('#brandtypeListView_wrapper .dataTables_length select').select2(); // initialize select2 dropdown
	         
	            //rajesh complaint datatable start
	            $('#complaintListView').dataTable({
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
	                "sAjaxSource" : "complaintListView.action",
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
	            
			 	jQuery('#complaintListView_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#complaintListView_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	           // jQuery('#vehicletable_wrapper .dataTables_length select').select2(); // initialize select2 dropdown

	            
	            $('#transferHistoryTable').dataTable({
	            	"aaSorting": [
	            	                [0, 'asc']
	            	            ],
	                "aLengthMenu": [
	                    [20, 50, 100],
	                    [20, 50, 100] // change per page values here
	                ],
	                // set the initial value
	                "iDisplayLength": 20,
	                "bProcessing" : true,
	                "bServerSide" : true,
	                "sAjaxSource" : "VehicleTransferHistoryAjaxAction.action",
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
	            $('#fcRenewalTable').dataTable({
	            	"aaSorting": [
	            	                [0, 'asc']
	            	            ],
	                "aLengthMenu": [
	                    [20, 50, 100],
	                    [20, 50, 100] // change per page values here
	                ],
	                // set the initial value
	                "iDisplayLength": 20,
	                "bProcessing" : true,
	                "bServerSide" : true,
	                "sAjaxSource" : "FcrenewalHistoryAjaxAction.action",
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
	            
	            
	            $('#dockingHistoryTable').dataTable({
	            	"aaSorting": [
	            	                [0, 'asc']
	            	            ],
	                "aLengthMenu": [
	                    [20, 50, 100],
	                    [20, 50, 100] // change per page values here
	                ],
	                // set the initial value
	                "iDisplayLength": 20,
	                "bProcessing" : true,
	                "bServerSide" : true,
	                "sAjaxSource" : "VehicleDockingHistoryAjaxAction.action",
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
	            $('#fuelHistoryTable').dataTable({
	            	"aaSorting": [
	            	                [0, 'asc']
	            	            ],
	                "aLengthMenu": [
	                    [20, 50, 100],
	                    [20, 50, 100] // change per page values here
	                ],
	                // set the initial value
	                "iDisplayLength": 20,
	                "bProcessing" : true,
	                "bServerSide" : true,
	                "sAjaxSource" : "VehicleFuelHistoryAjaxAction.action",
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

//	            vehicleTypeTable
	            $('#vehicleTypeTable').dataTable({
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
	                "sAjaxSource" : "VehicleTypeListAjax.action",
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
			 	jQuery('#vehicleTypeTable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#vehicleTypeTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	           // jQuery('#vehicleTypeTable_wrapper .dataTables_length select').select2(); // initialize select2 dropdown
	            
	            $('#causelationTable').dataTable({
	            	"aaSorting": [
	            	                [0, 'asc']
	            	            ],
	                "aLengthMenu": [
	                    [10,25,50,100],
	                    [10,25,50,100], 
	                ],
	              
	                "iDisplayLength": 10,
	                "bProcessing" : true,
	                "bServerSide" : true,
	                "sAjaxSource" : "cancellationListAjax",
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
			 	jQuery('#causelationTable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#causelationTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	            
	            $('#extraKMTable').dataTable({
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
	                "sAjaxSource" : "extraKmListAjax.action",
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
			 	jQuery('#extraKMTable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#cextraKMTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	           
	            
	            
	            $('#serviceTypeTable').dataTable({
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
	                "sAjaxSource" : "ServiceTypeListAjax.action",
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
	                    { "sClass": "url", "aTargets": [ 3 ] }
	                ]
	                
	            });
			 	jQuery('#serviceTypeTable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#serviceTypeTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	           // jQuery('#vehicleTypeTable_wrapper .dataTables_length select').select2(); // initialize select2 dropdown
	            
	            
	          
	            
	            
	            console.log("hiii");
	            $('#serviceTypeTaxTable').dataTable({
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
	                "sAjaxSource" : "ServiceTypeTaxListAjax.action",
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
	                    { "sClass": "url", "aTargets": [ 3 ] }
	                ]
	                
	            });
			 	jQuery('#serviceTypeTaxTable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#serviceTypeTaxTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	           // jQuery('#vehicleTypeTable_wrapper .dataTables_length select').select2(); // initialize select2 dropdown
	           
	            
	            $('#makeTypeTable').dataTable({
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
	                "sAjaxSource" : "MakeTypeListAjax.action",
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
	                    { "sClass": "url", "aTargets": [ 3 ] }
	                ]
	                
	            });
			 	jQuery('#makeTypeTable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#makeTypeTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	           // jQuery('#makeTypeTable_wrapper .dataTables_length select').select2(); // initialize select2 dropdown
	          
	            $('#dockingtypeView').dataTable({
	            	"aaSorting": [
	            	                [0, 'asc']
	            	            ],
	                "aLengthMenu": [
	                    [10, 20,50, 100],
	                    [10, 20,50, 100] // change per page values here
	                ],
	                // set the initial value
	                "pagingType": "scrolling",
	                
	                "iDisplayLength": 10,
	                "bProcessing" : true,
	                "bServerSide" : true,
	                "sAjaxSource" : "DockingTypeListAjax.action",
	                "sPaginationType": "bootstrap",
	                "oLanguage": {
	                    "sLengthMenu": "_MENU_ records",
	                    "oPaginate": {
	                        "sPrevious": "Prev",
	                        "sNext": "Next",
	                        "sPaginationType": "input"
	                    }
	                },
	               "aoColumnDefs": [
	                    { 'bSortable': false, 'aTargets': [0] },
	                    { "bSearchable": false, "aTargets": [ 0 ] }
	                ]
	                
	            }); 
	            jQuery('#dockingtypeView_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#dockingtypeView_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	            //jQuery('#makeTypeTable_wrapper .dataTables_length select').select2(); // initialize select2 dropdown

	            
	            $('#brandtypeList').dataTable({
	            	"aaSorting": [
	            	                [1, 'asc']
	            	            ],
	                "aLengthMenu": [
	                    [10, 25, 50, 100],
	                    [10, 25, 50, 100] // change per page values here
	                ],
	                // set the initial value
	                "iDisplayLength": 10,
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
	                    { "bSearchable": true, "aTargets": [ 0,1,2 ] },
	                    { "sClass": "url", "aTargets": [ 4 ] }
	                ]
	            });

	            jQuery('#brandtypeList_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#brandtypeList_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	           // jQuery('#brandtypeList_wrapper .dataTables_length select').select2(); // initialize select2 dropdown

	            $('#accidenttypeShow').dataTable({
					"aaSorting" : [ [ 1, 'asc' ] ],
					"aLengthMenu" : [ [ 10, 20, 50, 100 ], [ 10, 20, 50, 100 ] // change
																				// per
																				// page
																				// values
																				// here
					],
					// set the initial value
					"pagingType" : "scrolling",

					"iDisplayLength" : 10,
					"bProcessing" : true,
					"bServerSide" : true,
					"sAjaxSource" : "accidenttype.action",
					"sPaginationType" : "bootstrap",
					"oLanguage" : {
						"sLengthMenu" : "_MENU_ records",
						"oPaginate" : {
							"sPrevious" : "Prev",
							"sNext" : "Next",
							"sPaginationType" : "input"
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
	            
	            jQuery('#accidenttypeShow_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#accidenttypeShow_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	           // jQuery('#accidenttypeView_wrapper .dataTables_length select').select2(); // initialize select2 dropdown
	            
	            
	            $('#breakdowntypelistView').dataTable({
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
					"sAjaxSource" : "breakdowntype.action",
					"sPaginationType" : "bootstrap",
					"oLanguage" : {
						"sLengthMenu" : "_MENU_ records",
						"oPaginate" : {
							"sPrevious" : "Prev",
							"sNext" : "Next",
							"sPaginationType" : "input"
						}
					},
					"aoColumnDefs": [
					                    { 'bSortable': false, 'aTargets': [0] },
					                    { "bSearchable": true, "aTargets": [ 0 ] },
					                    { "sClass": "url", "aTargets": [ 6 ] },
					                ]
				});
	            jQuery('#breakdowntypelistView_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#breakdowntypelistView_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	           // jQuery('#breakdowntypelistView_wrapper .dataTables_length select').select2(); // initialize select2 dropdown
	            
	                        
	            $('#modelTypeTable').dataTable({
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
	                "sAjaxSource" : "ModelTypeListAjax.action",
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
	                    { "bSearchable": false, "aTargets": [ 0 ]} ,
	                    { "sClass": "url", "aTargets": [ 3 ] },
	                ],
	                
	            });
			 	jQuery('#modelTypeTable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#modelTypeTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	           // jQuery('#modelTypeTable_wrapper .dataTables_length select').select2(); // initialize select2 dropdown
	
	   
	            
	            
	            $('#scheduleMappingTableListservicecall').dataTable({
	    			"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
	    			// per
	    			// page
	    			// values
	    			// here
	    			],
	    			// set the initial value
	    			//"iDisplayLength" : 5,
	    			"sAjaxSource" : "ScheduleMappingListAjaxServiceCall",
	    			"sPaginationType" : "bootstrap",
	    			"bDestroy" : true,
//	    			"bSort" : false,
//	    			"bFilter" : false,
//	    			"bSelect" : false,
//	    			"bPaginate" : false,
//	    			"bInfo": false,

	    			 "oLanguage": {
		                    "sLengthMenu": "_MENU_ records",
		                    "oPaginate": {
		                        "sPrevious": "Prev",
		                        "sNext": "Next"
		                    }
		                },
		               "aoColumnDefs": [
		                    { 'bSortable': false, 'aTargets': [0] },
		                    { "bSearchable": false, "aTargets": [ 0 ]} ,
		                    { "sClass": "url", "aTargets": [ 3 ] },
		                ],
	    		});
	    		jQuery(
	    				'#scheduleMappingTableListservicecall_wrapper .dataTables_filter input')
	    				.addClass("form-control input-small input-inline"); // modify
	    		// table
	    		jQuery(
	    				'#scheduleMappingTableListservicecall_wrapper .dataTables_length select')
	    				.addClass("form-control input-xsmall input-inline"); // modify
	            
	    		
	    		
	    		
		    		
	            
	            

//	            $('#scheduleMappingTableList').dataTable({
//	            	"aaSorting": [
//	            	                [0, 'asc'],[1, 'asc'],[2, 'asc'],[3, 'asc']
//	            	            ],
//	                "aLengthMenu": [
//	                    [10,25,50,100],
//	                    [10,25,50,100], // change per page values here
//	                ],
//	                // set the initial value
//	                "iDisplayLength": 10,
//	                "bProcessing" : true,
//	                "bServerSide" : true,
//	                "sAjaxSource" : "ScheduleMappingListAjax.action",
//	                "sPaginationType": "bootstrap",
//	                "oLanguage": {
//	                    "sLengthMenu": "_MENU_ records",
//	                    "oPaginate": {
//	                        "sPrevious": "Prev",
//	                        "sNext": "Next"
//	                    }
//	                },
//	               "aoColumnDefs": [
//	                    { 'bSortable': false, 'aTargets': [0] },
//	                    { "bSearchable": false, "aTargets": [ 0 ]} ,
//	                    { "sClass": "url", "aTargets": [ 3 ] },
//	                ],
//	                
//	            });
//			 	jQuery('#scheduleMappingTableList_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
//	            jQuery('#scheduleMappingTableList_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
//	           // jQuery('#modelTypeTable_wrapper .dataTables_length select').select2(); // initialize select2 dropdown
//	            
	            
	            
	            $('#dailyscheduleMappingTableList').dataTable({
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
	                "sAjaxSource" : "AjaxgetDailyScheduleMapping.action",
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
	                    { "bSearchable": false, "aTargets": [ 0 ]} ,
	                    { "sClass": "url", "aTargets": [ 3 ] },
	                ],
	                
	            });
			 	jQuery('#dailyscheduleMappingTableList_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#dailyscheduleMappingTableList_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	           // jQuery('#modelTypeTable_wrapper .dataTables_length select').select2(); // initialize select2 dropdown
	

	          
	            
	            $('#DeviceDetailTable').dataTable({
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
	                "sAjaxSource" : "vtuDeviceDetailsAjax.action",
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
	                    { "bSearchable": false, "aTargets": [ 0 ]} ,
	                    { "sClass": "url", "aTargets": [ 3 ] },
	                ],
	                
	            });
			 	jQuery('#scheduleMappingTableList_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#scheduleMappingTableList_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	           // jQuery('#modelTypeTable_wrapper .dataTables_length select').select2(); // initialize select2 dropdown
	            
	            
	            $('#ETMAvailListable').dataTable({
	            	"aaSorting": [
	            	                [0, 'asc'],[1, 'asc'],[2, 'asc'],[3, 'asc']
	            	            ],
	                "aLengthMenu": [
	                    [10,25,50, -1 ],
	                    [10,25,50, "All" ], // change per page values here
	                ],
	                // set the initial value
	                "iDisplayLength": 10,
	                "bProcessing" : true,
	                "bServerSide" : true,
	                "sAjaxSource" : "OnlineETMAvailabilityListAjax.action",
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
	                    { "bSearchable": false, "aTargets": [ 0 ]} ,
	                    { "sClass": "url", "aTargets": [ 3 ] },
	                ],
	                
	            });
			 	jQuery('#ETMAvailListable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#ETMAvailListable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	           // jQuery('#modelTypeTable_wrapper .dataTables_length select').select2(); // initialize select2 dropdown
	            
	            

	            
	            $('#vehicleDeviceRelation').dataTable({
	    			"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
	    			// per
	    			// page
	    			// values
	    			// here
	    			],
	    			// set the initial value
	    			//"iDisplayLength" : 5,
	    			"sAjaxSource" : "VehicleDeviceRelationMappingAjax",
	    			"sPaginationType" : "bootstrap",
	    			"bDestroy" : true,
//	    			"bSort" : false,
//	    			"bFilter" : false,
//	    			"bSelect" : false,
//	    			"bPaginate" : false,
//	    			"bInfo": false,

	    			 "oLanguage": {
		                    "sLengthMenu": "_MENU_ records",
		                    "oPaginate": {
		                        "sPrevious": "Prev",
		                        "sNext": "Next"
		                    }
		                },
		               "aoColumnDefs": [
		                    { 'bSortable': false, 'aTargets': [0] },
		                    { "bSearchable": false, "aTargets": [ 0 ]} ,
		                    { "sClass": "url", "aTargets": [ 3 ] },
		                ],
	    		});
	    		jQuery(
	    				'#vehicleDeviceRelation_wrapper .dataTables_filter input')
	    				.addClass("form-control input-small input-inline"); // modify
	    		// table
	    		jQuery(
	    				'#vehicleDeviceRelation_wrapper .dataTables_length select')
	    				.addClass("form-control input-xsmall input-inline"); // modify
	
	            
	            
	            $('#ServiceLimitTable').dataTable({
	            	"aaSorting": [
	            	                [0, 'asc'],[1, 'asc'],[2, 'asc'],[3, 'asc']
	            	            ],
	                "aLengthMenu": [
	                    [10,25,50, -1 ],
	                    [10,25,50, "All" ], // change per page values here
	                ],
	                // set the initial value
	                "iDisplayLength": 10,
	                "bProcessing" : true,
	                "bServerSide" : true,
	                "sAjaxSource" : "BrandTypeServiceLimitAjax.action",
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
	                    { "bSearchable": false, "aTargets": [ 0 ]} ,
	                    { "sClass": "url", "aTargets": [ 3 ] },
	                ],
	                
	            });
			 	jQuery('#ServiceLimitTable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#ServiceLimitTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	           // jQuery('#modelTypeTable_wrapper .dataTables_length select').select2(); // initialize select2 dropdown
	            
	            

	            $('#vehilceCategoryListView').dataTable({
					"aaSorting" : [  [ 1, 'asc' ] ],
					"aLengthMenu" : [ [ 10, 25, 50, 100 ],
					                  [ 10, 25, 50, 100 ] // change per page values here
					],
					// set the initial value
					"iDisplayLength" : 10,
					"bProcessing" : true,
					"bServerSide" : true,
					"sAjaxSource" : "VehicleCategoryAjax.action",
					"sPaginationType" : "bootstrap",
					"oLanguage" : {
						"sLengthMenu" : "_MENU_ records",
						"oPaginate" : {
							"sPrevious" : "Prev",
							"sNext" : "Next",
							"sPaginationType" : "input"
						}
					},
					"aoColumnDefs" : [ {
						'bSortable' : false,
						'aTargets' : [ 0 ]
					
					}, {
						"bSearchable" : false,
						"aTargets" : [ 0 ]
					} ,
					
					 { "sClass": "url", "aTargets": [ 3] },
					]

				});

	            jQuery('#vehilceCategoryListView_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#vehilceCategoryListView_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	            //jQuery('#modelTypeTable_wrapper .dataTables_length select').select2(); // initialize select2 dropdown
	            
	            
	            
	            $('#dockingHistoryListView').dataTable({
					"aaSorting" : [  [ 1, 'asc' ] ],
					"aLengthMenu" : [ [ 10, 25, 50, 100 ],
					                  [ 10, 25, 50, 100 ] // change per page values here
					],
					// set the initial value
					"iDisplayLength" : 10,
					"bProcessing" : true,
					"bServerSide" : true,
					"sAjaxSource" : "DockingHistoryListAjaxAction.action",
					"sPaginationType" : "bootstrap",
					"oLanguage" : {
						"sLengthMenu" : "_MENU_ records",
						"oPaginate" : {
							"sPrevious" : "Prev",
							"sNext" : "Next",
							"sPaginationType" : "input"
						}
					},
					"aoColumnDefs" : [ 
					 {'bSortable' : false,'aTargets' : [ 0 ]},
					 {"bSearchable" : false,"aTargets" : [ 0 ]} ]

				});

	            jQuery('#dockingHistoryListView_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#dockingHistoryListView_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	            //jQuery('#modelTypeTable_wrapper .dataTables_length select').select2(); // initialize select2 dropdown
	            
	            
	            

//End Code Before This
	            
	            $('#listvehicledefect').dataTable({
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
	                "sAjaxSource" : "VehicleDefect.action",
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
			 	jQuery('#listvehicledefect_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#listvehicledefect_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	            $('#viewBreakDownHistory').dataTable({
	             	"aaSorting": [
	             	                [1, 'asc']
	             	            ],
	                 "aLengthMenu": [
	                     [10, 50, 100],
	                     [10, 50, 100] // change per page values here
	                 ],
	                 // set the initial value
	                 "iDisplayLength": 10,
	                 "bProcessing" : true,
	                 "bServerSide" : true,
	                 "sAjaxSource" : "viewBreakDownHistoryList.action",
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
	        	 jQuery('#viewBreakDownHistory_wrapper .dataTables_filter input').addClass("form-control input-medium input-inline"); // modify table search input
	             jQuery('#viewBreakDownHistory_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline");
	            
	            
   
//End Code Before This
	           
	            $('#listvehiclemaintence').dataTable({
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
	                "sAjaxSource" : "Vehiclemaintenance.action",
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
			 	jQuery('#listvehiclemaintence_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#listvehiclemaintence_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	           
	            
	            $('#dockingListOfVehicle').dataTable({
	            	
	            	"aaSorting": [
	            	                [0, 'asc']
	            	            ],
	                "aLengthMenu": [
	                    [10,25,50,100],
	                    [10,25,50,100], // change per page values here
	                ], // change per page values here
	                "bFilter": false,
	                // set the initial value
	                "iDisplayLength": 10,
//	                "bProcessing" : true,
//	                "bServerSide" : true,
//	                "sAjaxSource" : "Vehiclemaintenance.action",
	                "sPaginationType": "bootstrap",
	                "oLanguage": {
	                    "sLengthMenu": "_MENU_ records",
	                    "oPaginate": {
	                        "sPrevious": "Prev",
	                        "sNext": "Next"
	                    }
	                },
	               "aoColumnDefs": [
	                  
	                    { "sClass": "url", "aTargets": [ 3 ] }
	                ]
	                
	            });
			 	jQuery('#dockingListOfVehicle_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#dockingListOfVehicle_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	         
	       
	       
	            
	            $('#deviceDisconnectList').dataTable({
	    			"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
	    			// per
	    			// page
	    			// values
	    			// here
	    			],
	    			// set the initial value
	    			//"iDisplayLength" : 5,
	    			"sAjaxSource" : "getDeviceDisconectedData",
	    			"sPaginationType" : "bootstrap",
	    			"bDestroy" : true,
//	    			"bSort" : false,
//	    			"bFilter" : false,
//	    			"bSelect" : false,
//	    			"bPaginate" : false,
//	    			"bInfo": false,

	    			 "oLanguage": {
		                    "sLengthMenu": "_MENU_ records",
		                    "oPaginate": {
		                        "sPrevious": "Prev",
		                        "sNext": "Next"
		                    }
		                },
		               "aoColumnDefs": [
		                    { 'bSortable': false, 'aTargets': [0] },
		                    { "bSearchable": false, "aTargets": [ 0 ]} ,
		                    { "sClass": "url", "aTargets": [ 3 ] },
		                ],
	    		});
	    		jQuery(
	    				'#deviceDisconnectList_wrapper .dataTables_filter input')
	    				.addClass("form-control input-small input-inline"); // modify
	    		// table
	    		jQuery(
	    				'#deviceDisconnectList_wrapper .dataTables_length select')
	    				.addClass("form-control input-xsmall input-inline"); // modify
	            
	            
		}

	};

}();
