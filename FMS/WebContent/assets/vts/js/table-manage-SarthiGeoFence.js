var TableManagedSarthiSectorGeofence = function() {

	return {

		// main function to initiate the module
		init : function() {

			if (!jQuery().dataTable) {
				return;
			}

			
			/*
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
			 	jQuery('#revenueSectorView_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#revenueSectorView_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	           
			*/
			
			
			
			
			//alert("Hii");
			
			
	          //  sarthiSectorGeofenceView
			
			
	            $('#sarthiSectorGeofenceView').dataTable({
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
	                "sAjaxSource" : "vehicleSectorData.action",
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
			 	jQuery('#sarthiSectorGeofenceView_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#sarthiSectorGeofenceView_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	           
	            $('#VehicleAllocatedToSector').dataTable({
	            	
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
	                "sAjaxSource" : "vehicleAllocatedToSector.action",
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
			 	jQuery('#VehicleAllocatedToSector_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#VehicleAllocatedToSector_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	           

	            
			
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

