var TableManagedDevice = function() {

	return {

		// main function to initiate the module
		init : function() {

			if (!jQuery().dataTable) {
				return;
			}

// Strat Code After This

			 /**********Starting  Battery List View ************************************/
			
			 // console.log("hiii");
	            $('#serviceTaxTypeTable').dataTable({
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
	                "sAjaxSource" : "ServiceTaxTypeListAjax.action",
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
			 	jQuery('#serviceTaxTypeTable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#serviceTaxTypeTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	           // jQuery('#vehicleTypeTable_wrapper .dataTables_length select').select2(); // initialize select2 dropdown
            $('#batterListView').dataTable({
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
                "sAjaxSource" : "BatteryViewAjaxAction.action",
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
		 	jQuery('#batterListView_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#batterListView_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
            //jQuery('#batterListView_wrapper .dataTables_length select').select2(); // initialize select2 dropdown
           /**********Ending Battery List View ************************************/

            $('#simcardList').dataTable({
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
                "sAjaxSource" : "SimView.action",
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
		 	jQuery('#simcardList_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#simcardList_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
            //jQuery('#batterListView_wrapper .dataTables_length select').select2(); // initialize select2 dropdown
           /**********Ending Battery List View ************************************/
            
            
            
			$('#devicetypeView').dataTable({
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
				"sAjaxSource" : "deviceType.action",
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
			jQuery('#devicetypeView_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#devicetypeView_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
           // jQuery('#devicetypeView_wrapper .dataTables_length select').select2(); // initialize select2 dropdown
            /**********************************************************/

			$('#device').dataTable({
				"aaSorting" : [ [ 1, 'asc' ] ],
				"aLengthMenu" : [ [ 10,25, 50, 100 ], [ 10,25, 50, 100 ]  // change
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
				"sAjaxSource" : "deviceList.action",
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
					"bSearchable" : true,
					"aTargets" : [ 1 ]
				} ]

			});
			jQuery('#device_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#device_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
            //jQuery('#device_wrapper .dataTables_length select').select2(); // initialize select2 dropdown
            /**********************************************************/
			$('#deviceHistory').dataTable({
				"aaSorting" : [ [ 1, 'asc' ] ],
				"aLengthMenu" : [ [ 10,25, 50, 100 ], [10,25, 50, 100 ] // change
																	// per page
																	// values
																	// here
				],
				// set the initial value
				"iDisplayLength" : 10,
				"bProcessing" : true,
				"bServerSide" : true,
				"sAjaxSource" : "showHistory.action",
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

			jQuery('#deviceHistory_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#deviceHistory_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
            //jQuery('#deviceHistory_wrapper .dataTables_length select').select2(); // initialize select2 dropdown
            /**********************************************************/

            $('#viewAlert').dataTable(
     				{
     					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
     					// per
     					// page
     					// values
     					// here
     					],
     					// set the initial value
     					"iDisplayLength" : 5,
     					"sAjaxSource" : "alertData.action",
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
     		jQuery('#viewAlert_wrapper .dataTables_filter input').addClass(
     				"form-control input-medium input-inline"); // modify table
     		// search input
     		jQuery('#viewAlert_wrapper .dataTables_length select').addClass(
     				"form-control input-xsmall input-inline");
//            $('#viewAlert').dataTable({
//				"aaSorting" : [ [ 1, 'asc' ] ],
//				"aLengthMenu" : [ [ 10,25, 50, 100 ], [10,25, 50, 100 ] // change
//																	// per page
//																	// values
//																	// here
//				],
//				// set the initial value
//				"iDisplayLength" : 10,
//				"bProcessing" : true,
//				"bServerSide" : true,
//				"sAjaxSource" : "alertData.action",
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
//
//			});
//
//			jQuery('#viewAlert_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
//            jQuery('#viewAlert_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
//            //jQuery('#deviceHistory_wrapper .dataTables_length select').select2(); // initialize select2 dropdown
//            /**********************************************************/
            
            $('#listdatacard').dataTable({
				"aaSorting" : [ [ 1, 'asc' ] ],
				"aLengthMenu" : [ [ 10,25, 50, 100 ], [10,25, 50, 100 ] // change
																	// per page
																	// values
																	// here
				],
				// set the initial value
				"iDisplayLength" : 10,
				"bProcessing" : true,
				"bServerSide" : true,
				"sAjaxSource" : "DataCard.action",
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

			jQuery('#listdatacard_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#listdatacard_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown

            $('#listrfidcard').dataTable({
				"aaSorting" : [ [ 1, 'asc' ] ],
				"aLengthMenu" : [ [ 10,25, 50, 100 ], [10,25, 50, 100 ] // change
																	// per page
																	// values
																	// here
				],
				// set the initial value
				"iDisplayLength" : 10,
				"bProcessing" : true,
				"bServerSide" : true,
				"sAjaxSource" : "RFIDCard.action",
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

			jQuery('#listrfidcard_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#listrfidcard_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown

            $('#listmaintenancetype').dataTable({
				"aaSorting" : [ [ 1, 'asc' ] ],
				"aLengthMenu" : [ [ 10,25, 50, 100 ], [10,25, 50, 100 ] // change
																	// per page
																	// values
																	// here
				],
				// set the initial value
				"iDisplayLength" : 10,
				"bProcessing" : true,
				"bServerSide" : true,
				"sAjaxSource" : "MaintenanceType.action",
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

			jQuery('#listmaintenancetype_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#listmaintenancetype_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
            
        /*    $('#Alert')
			.dataTable(
					{
						"aLengthMenu" : [ [ 5, 15, 20, -1 ],
								[ 5, 15, 20, "All" ] // change
						],
						// set the initial value
						"iDisplayLength" : 20,
						"sAjaxSource" : "alertCCCData.action",
						"sPaginationType" : "bootstrap",
						"bDestroy" : true,
						"oLanguage" : {
							"sLengthMenu" : "_MENU_ records",
							"oPaginate" : {
								"sPrevious" : "Prev",
								"sNext" : "Next"
							}
						},
						"bFilter" : false,
						"bSelect" : false,
						"bPaginate" : false,
						"oLanguage" : {
							"sZeroRecords" : "",
							"sEmptyTable" : ""
						},
						"aoColumnDefs" : [ {
							'bSortable' : false,
							'aTargets' : [ 0, 1, 2, 3, 4 ]
						} ],
						"bLengthChange" : false,
						"sDom" : '<"top">rt<"bottom"flp><"clear">'
					})
			.wrap(
					"<div style='position:auto;overflow:auto;height:300px;width:100%'/>");
	jQuery('#Alert_wrapper .dataTables_filter input').addClass(
			"form-control input-medium input-inline"); // modify table
	// search input
	jQuery('#Alert_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");*/

         /*  $('#Alert').dataTable({
    	 		"aaSorting" : [ [ 1, 'asc' ] ],
    	 		"aLengthMenu" : [ [ 5,25, 50, 100 ], [5,25, 50, 100 ] // change
												// per page
												// values
												// here
    	 		],
    	 		// set the initial value
    	 		"iDisplayLength" : 5,
    	 		"bProcessing" : true,
    	 		"bServerSide" : true,
    	 		"sAjaxSource" : "alertCCCData.action",
    	 		"sPaginationType" : "bootstrap",
    	 		"oLanguage" : {
					"sLengthMenu" : "_MENU_ records",
					"oPaginate" : {
						"sPrevious" : "Prev",
						"sNext" : "Next"
					}
				},
				"bFilter" : false,
				"bSelect" : false,
				"bPaginate" : false,
				"oLanguage" : {
					"sZeroRecords" : "",
					"sEmptyTable" : ""
				},
				
     }).wrap("<div style='position:relative;overflow:auto;height:300px;'/>");
     jQuery('#Alert_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
     jQuery('#Alert_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline");*/
     var oTable = $('#SOS_Alert').dataTable({
    	 		"aaSorting" : [ [ 1, 'asc' ] ],
    	 		"aLengthMenu" : [ [ 10,25, 50, 100 ], [10,25, 50, 100 ] // change
												// per page
												// values
												// here
    	 		],
				// set the initial value
				"iDisplayLength" : 10,
				"bProcessing" : true,
				"bServerSide" : true,
				"sAjaxSource" : "sosData.action",
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
			}).wrap("<div style='position:relative;overflow:auto;height:300px;'/>");
     

     		jQuery('#SOS_Alert_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
     		jQuery('#SOS_Alert_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline");

     		$('#revenue_org').dataTable({
    	 		"aaSorting" : [ [ 1, 'asc' ] ],
    	 		"aLengthMenu" : [ [ 10,25, 50, 100 ], [10,25, 50, 100 ] // change
												// per page
												// values
												// here
    	 		],
				// set the initial value
				"iDisplayLength" : 10,
				"bProcessing" : true,
				"bServerSide" : true,
				"sAjaxSource" : "getRevenueData.action",
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
			
			}).wrap("<div style='position:relative;overflow:auto;height:300px;'/>");

     		jQuery('#revenue_org_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
     		jQuery('#revenue_org_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline");
            
     		
     		
     		  $('#scheduleMappingTableList').dataTable({
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
	                "sAjaxSource" : "ScheduleMappingListAjax.action",
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
	
	            $('#scheduleMappingTableViewList').dataTable({
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
	                "sAjaxSource" : "ScheduleMappingListViewAjax.action",
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
			 	jQuery('#scheduleMappingTableViewList_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#scheduleMappingTableViewList_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	           // jQuery('#modelTypeTable_wrapper .dataTables_length select').select2(); // initialize select2 dropdown
     		
     		$('#manu_ticket').dataTable({
    	 		"aaSorting" : [ [ 1, 'asc' ] ],
    	 		"aLengthMenu" : [ [ 10,25, 50, 100 ], [10,25, 50, 100 ] // change
												// per page
												// values
												// here
    	 		],
				// set the initial value
				"iDisplayLength" : 10,
				"bProcessing" : true,
				"bServerSide" : true,
				"sAjaxSource" : "getTicketData.action",
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
			
			}).wrap("<div style='position:relative;overflow:auto;height:300px;'/>");

     		jQuery('#manu_ticket_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
     		jQuery('#manu_ticket_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline");

     		//Schedule Vehicle Report..
     		$('#scheduled_vehicle').dataTable(
     				{
     					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
     					// per
     					// page
     					// values
     					// here
     					],
     					// set the initial value
     					"iDisplayLength" : 5,
     					"sAjaxSource" : "getScheduledVehicleDetails.action",
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
     		jQuery('#scheduled_vehicle_wrapper .dataTables_filter input').addClass(
     				"form-control input-medium input-inline"); // modify table
     		// search input
     		jQuery('#scheduled_vehicle_wrapper .dataTables_length select').addClass(
     				"form-control input-xsmall input-inline");
     		}
	};

}();
