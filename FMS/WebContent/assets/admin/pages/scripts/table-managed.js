
var TableManaged = function() {

	return {

		// main function to initiate the module
		init : function() {

			if (!jQuery().dataTable) {
				return;
			}

			$('#sample_1').dataTable({
				"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
																			// per
																			// page
																			// values
																			// here
				],
				// set the initial value
				"iDisplayLength" : 5,
				"sPaginationType" : "bootstrap",

				"oLanguage" : {
					"sLengthMenu" : "_MENU_ records",
					"oPaginate" : {
						"sPrevious" : "Prev",
						"sNext" : "Next"
					}
				},
				"aoColumnDefs" : [ {
					'bSortable' : true,
					'aTargets' : [ 0 ]
				}, {
					"bSearchable" : false,
					"aTargets" : [ 0 ]
				} ]
			});

			jQuery('#sample_1 .group-checkable').change(function() {
				var set = jQuery(this).attr("data-set");
				var checked = jQuery(this).is(":checked");
				jQuery(set).each(function() {
					if (checked) {
						$(this).attr("checked", true);
					} else {
						$(this).attr("checked", false);
					}
				});
				jQuery.uniform.update(set);
			});

			jQuery('#sample_1_wrapper .dataTables_filter input').addClass(
					"form-control input-medium input-inline"); // modify table
																// search input
			jQuery('#sample_1_wrapper .dataTables_length select').addClass(
					"form-control input-xsmall input-inline"); // modify table
																// per page
																// dropdown
			// jQuery('#sample_1_wrapper .dataTables_length select').select2();
			// // initialize select2 dropdown

			

			

			/*$('#roleView').dataTable({
				"aaSorting" : [ [ 0, 'asc' ] ],
				"aLengthMenu" : [ [ 10,25,50,100 ], [ 10,25,50,100 ] // change
																	// per page
																	// values
																	// here
				],
				// set the initial value
				"iDisplayLength" : 10,
				"bProcessing" : true,
				"bServerSide" : true,
				"sAjaxSource" : "ShowRole.action",
				"sPaginationType" : "bootstrap",
			
			  "oLanguage": { "sLengthMenu": "_MENU_ records", "oPaginate": {
			  "sPrevious": "Prev", "sNext": "Next" } }, "aoColumnDefs": [ {
			  'bSortable': false, 'aTargets': [0] }, { "bSearchable": false,
			  "aTargets": [ 0 ] } ]
			 

			});
			jQuery('#roleView_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#roleView_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
			*/
			
		
			jQuery('#sample_2 .group-checkable').change(function() {
				var set = jQuery(this).attr("data-set");
				var checked = jQuery(this).is(":checked");
				jQuery(set).each(function() {
					if (checked) {
						$(this).attr("checked", true);
					} else {
						$(this).attr("checked", false);
					}
				});
				jQuery.uniform.update(set);
			});

			jQuery('#sample_2_wrapper .dataTables_filter input').addClass(
					"form-control input-small input-inline"); // modify table
																// search input
			jQuery('#sample_2_wrapper .dataTables_length select').addClass(
					"form-control input-xsmall input-inline"); // modify table
																// per page
																// dropdown
			jQuery('#sample_2_wrapper .dataTables_length select').select2(); // initialize
																				// select2
																				// dropdown

			// begin: third table
			$('#sample_3').dataTable({
				"aaSorting" : [ [ 1, 'asc' ] ],
				"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
																			// per
																			// page
																			// values
																			// here
				],
				// set the initial value
				"iDisplayLength" : 5,
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

			

			jQuery('#sample_3 .group-checkable').change(function() {
				var set = jQuery(this).attr("data-set");
				var checked = jQuery(this).is(":checked");
				jQuery(set).each(function() {
					if (checked) {
						$(this).attr("checked", true);
					} else {
						$(this).attr("checked", false);
					}
				});
				jQuery.uniform.update(set);
			});

			jQuery('#sample_3_wrapper .dataTables_filter input').addClass(
					"form-control input-small input-inline"); // modify table
																// search input
			jQuery('#sample_3_wrapper .dataTables_length select').addClass(
					"form-control input-xsmall input-inline"); // modify table
																// per page
																// dropdown
			jQuery('#sample_3_wrapper .dataTables_length select').select2(); // initialize
																				// select2
																				// dropdown


			
			

          
            
            
            $('#addShiftType').dataTable({
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
                "sAjaxSource" : "ShiftType.action",
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
           
                
           
            $('#addRoadType').dataTable({
            	"aaSorting": [
            	                [1, 'asc']
            	            ],
                "aLengthMenu": [
                    [10,25, 50, 100],
                    [10,25, 50, 100] // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 10,
                "bProcessing" : true,
                "bServerSide" : true,
                "sAjaxSource" : "RoadType.action",
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
          
            jQuery('#addRoadType_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#addRoadType_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown

			/*$('#addGroup').dataTable({
				"aLengthMenu" : [ [ 20, 50, 100 ], [ 20, 50, 100 ] // change
																	// per page
																	// values
																	// here
				],
				// set the initial value
				"iDisplayLength" : 20,
				"bProcessing" : true,
				"bServerSide" : true,
				"sAjaxSource" : "group.action",
				"sPaginationType" : "bootstrap",
			
			  "oLanguage": { "sLengthMenu": "_MENU_ records", "oPaginate": {
			  "sPrevious": "Prev", "sNext": "Next" } }, "aoColumnDefs": [ {
			  'bSortable': false, 'aTargets': [0] }, { "bSearchable": false,
			  "aTargets": [ 0 ] } ]
			 

			});
			
			jQuery('#addGroup_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#addGroup_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
*/
		/*	$('#addPage').dataTable({
				"aLengthMenu" : [ [ 20, 50, 100 ], [ 20, 50, 100 ] // change
																	// per page
																	// values
																	// here
				],
				// set the initial value
				"iDisplayLength" : 20,
				"bProcessing" : true,
				"bServerSide" : true,
				"sAjaxSource" : "page.action",
				"sPaginationType" : "bootstrap"
			
			 * "oLanguage": { "sLengthMenu": "_MENU_ records", "oPaginate": {
			 * "sPrevious": "Prev", "sNext": "Next" } }, "aoColumnDefs": [ {
			 * 'bSortable': false, 'aTargets': [0] }, { "bSearchable": false,
			 * "aTargets": [ 0 ] } ]
			 

			});

			*/


			/*$('#designationview').dataTable({
				"aaSorting" : [ [ 1, 'asc' ] ],
				"aLengthMenu" : [ [ 10, 20, 50, 100 ], [ 10, 20, 50, 100 ] // change
																	// per page
																	// values
																	// here
				],
				// set the initial value
				"iDisplayLength" : 10,
				"bProcessing" : true,
				"bServerSide" : true,
				"sAjaxSource" : "ShowDesignationlistAjax.action",
				"sPaginationType" : "bootstrap",
			
			  "oLanguage": { "sLengthMenu": "_MENU_ records", "oPaginate": {
			  "sPrevious": "Prev", "sNext": "Next" } }, "aoColumnDefs": [ {
			  'bSortable': false, 'aTargets': [0] }, { "bSearchable": false,
			  "aTargets": [ 0 ] } ]
			 

			});*/

			/*$('#employeetypeview').dataTable({
				"aaSorting" : [ [ 1, 'asc' ] ],
				"aLengthMenu" : [ [ 10, 20, 50, 100 ], [ 10, 20, 50, 100 ] // change
																	// per page
																	// values
																	// here
				],
				// set the initial value
				"iDisplayLength" : 10,
				"bProcessing" : true,
				"bServerSide" : true,
				"sAjaxSource" : "ShowEmployeeTypeAjax.action",
				"sPaginationType" : "bootstrap",
			
			 "oLanguage": { "sLengthMenu": "_MENU_ records", "oPaginate": {
			  "sPrevious": "Prev", "sNext": "Next" } }, "aoColumnDefs": [ {
			  'bSortable': false, 'aTargets': [0] }, { "bSearchable": false,
			  "aTargets": [ 0 ] } ]
			 

			});*/
			
           
			/*$('#vendorview').dataTable({
				"aaSorting" : [ [ 1, 'asc' ] ],
				"aLengthMenu" : [ [ 10, 25, 50, 100 ], [ 10, 25, 50, 100 ] // change
																	// per page
																	// values
																	// here
				],
				// set the initial value
				"iDisplayLength" : 10,
				"bProcessing" : true,
				"bServerSide" : true,
				"sAjaxSource" : "ShowVendorAjax.action",
				"sPaginationType" : "bootstrap",
			
			 "oLanguage": { "sLengthMenu": "_MENU_ records", "oPaginate": {
			 "sPrevious": "Prev", "sNext": "Next" } }, "aoColumnDefs": [ {
			 'bSortable': false, 'aTargets': [0] }, { "bSearchable": false,
			 "aTargets": [ 0 ] } ]
			

			});*/
			
			
			
			/*$('#employeeview').dataTable({
				"aaSorting" : [ [ 1, 'asc' ] ],
				"aLengthMenu" : [ [ 10, 25, 50, 100 ], [ 10, 25, 50, 100 ] // change
																	// per page
																	// values
																	// here
				],
				// set the initial value
				"iDisplayLength" : 10,
				"bProcessing" : true,
				"bServerSide" : true,
				"sAjaxSource" : "ShowEmployeeAjax.action",
				"sPaginationType" : "bootstrap",
			
			 "oLanguage": { "sLengthMenu": "_MENU_ records", "oPaginate": {
			 "sPrevious": "Prev", "sNext": "Next" } }, "aoColumnDefs": [ {
			 'bSortable': false, 'aTargets': [0] }, { "bSearchable": false,
			 "aTargets": [ 0 ] } ]
			

			});
			*/
		/*	$('#userview').dataTable({
				"aaSorting" : [ [ 1, 'asc' ] ],
				"aLengthMenu" : [ [ 10, 25, 50, 100 ], [ 10, 25, 50, 100 ] // change
																	// per page
																	// values
																	// here
				],
				// set the initial value
				"iDisplayLength" : 10,
				"bProcessing" : true,
				"bServerSide" : true,
				"sAjaxSource" : "showUserListAjax.action",
				"sPaginationType" : "bootstrap"
			
			 "oLanguage": { "sLengthMenu": "_MENU_ records", "oPaginate": {
			 "sPrevious": "Prev", "sNext": "Next" } }, "aoColumnDefs": [ {
			 'bSortable': false, 'aTargets': [0] }, { "bSearchable": false,
			 "aTargets": [ 0 ] } ]
			

			});*/
			
			
	            
	          /*  $('#addGroupdetaills').dataTable({
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
	                "sAjaxSource" : "group.action",
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
	            jQuery('#addGroupdetaills_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#addGroupdetaills_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	     */

			/** ******************************************************* */
		}

	};

}();

