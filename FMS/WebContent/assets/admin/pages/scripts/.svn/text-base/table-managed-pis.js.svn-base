var TableManagedPis = function() {

	return { 

		// main function to initiate the module
		init : function() {
 
			if (!jQuery().dataTable) {
				return;
			}

// Strat Code After This

			

			 $('#viewDisplayMapping').dataTable({
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
	                "sAjaxSource" : "showDisplayMappingList.action",
	                "sPaginationType": "bootstrap",
	                "oLanguage": {
	                    "sLengthMenu": "_MENU_ records",
	                    "oPaginate": {
	                        "sPrevious": "Prev",
	                        "sNext": "Next"
	                    }
	                },
	               "aoColumnDefs": [
	                    { 'bSortable': false, 'aTargets': [0,1] },
	                    { "bSearchable": false, "aTargets": [ 0 ] }
	                ]
	                
	            });
			 	jQuery('#viewDisplayMapping_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#viewDisplayMapping_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	            
	            $('#viewDisplayUnit').dataTable({
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
	                "sAjaxSource" : "showDisplayUnitList.action",
	                "sPaginationType": "bootstrap",
	                "oLanguage": {
	                    "sLengthMenu": "_MENU_ records",
	                    "oPaginate": {
	                        "sPrevious": "Prev",
	                        "sNext": "Next"
	                    }
	                },
	               "aoColumnDefs": [
	                    { 'bSortable': false, 'aTargets': [0,1] },
	                    { "bSearchable": false, "aTargets": [ 0 ] }
	                ]
	                
	            });
			 	jQuery('#viewDisplayUnit_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#viewDisplayUnit_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	           
	            $('#viewScrollMessage').dataTable({
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
	                "sAjaxSource" : "showScrollMessageList.action",
	                "sPaginationType": "bootstrap",
	                "oLanguage": {
	                    "sLengthMenu": "_MENU_ records",
	                    "oPaginate": {
	                        "sPrevious": "Prev",
	                        "sNext": "Next"
	                    }
	                },
	               "aoColumnDefs": [
	                    { 'bSortable': false, 'aTargets': [0,1] },
	                    { "bSearchable": false, "aTargets": [ 0 ] }
	                ]
	                
	            });
			 	jQuery('#viewScrollMessage_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#viewScrollMessage_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	           
	            var dataerange=$('#dashboard-report-range span').html();
	          
	            $('#viewVehicleDepotin-out').dataTable({
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
	                "sAjaxSource" : "showVehicleDepotin-outList.action?daterange="+dataerange,
	                "sPaginationType": "bootstrap",
	                "oLanguage": {
	                    "sLengthMenu": "_MENU_ records",
	                    "oPaginate": {
	                        "sPrevious": "Prev",
	                        "sNext": "Next"
	                    }
	                },
	               "aoColumnDefs": [
	                    { 'bSortable': false, 'aTargets': [] },
	                    { "bSearchable": false, "aTargets": [ 0 ] }
	                ]
	                
	            });
			 	jQuery('#viewVehicleDepotin-out_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#viewVehicleDepotin-out_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	            $('#viewAd').dataTable({
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
	                "sAjaxSource" : "showAdvertisementList.action",
	                "sPaginationType": "bootstrap",
	                "oLanguage": {
	                    "sLengthMenu": "_MENU_ records",
	                    "oPaginate": {
	                        "sPrevious": "Prev",
	                        "sNext": "Next"
	                    }
	                },
	               "aoColumnDefs": [
	                    { 'bSortable': false, 'aTargets': [0,1] },
	                    { "bSearchable": false, "aTargets": [ 0 ] }
	                ]
	                
	            });
			 	jQuery('#viewAd_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#viewAd_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	           
	          
	          //Schedule Vehicle Report..
	     		$('#viewVtsAlertConfigMaster').dataTable(
	     				{
	     					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
	     					// per
	     					// page
	     					// values
	     					// here
	     					],
	     					// set the initial value
	     					"iDisplayLength" : 5,
	     					"sAjaxSource" : "showVehicleAlertConfigMaster.action",
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
	     		jQuery('#viewVtsAlertConfigMaster_wrapper .dataTables_filter input').addClass(
	     				"form-control input-medium input-inline"); // modify table
	     		// search input
	     		jQuery('#viewVtsAlertConfigMaster_wrapper .dataTables_length select').addClass(
	     				"form-control input-xsmall input-inline");
	     		
	     		$('#pisCustomerView').dataTable({
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
	                "sAjaxSource" : "customerList.action",
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
			 	jQuery('#pisCustomerView_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#pisCustomerView_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	            

	            $('#playListView').dataTable({
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
	                "sAjaxSource" : "playListList.action",
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
			 	jQuery('#pisCustomerView_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#pisCustomerView_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown	        
	            
	        	$('#templateViewId').dataTable({
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
	     		    "sAjaxSource" : "viewPisTemplateList.action",
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
	     		    ]
	     		    
	     		});
	     		jQuery('#templateViewId_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	     		jQuery('#templateViewId_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown

//End Code Before This

		}

	};

}();
