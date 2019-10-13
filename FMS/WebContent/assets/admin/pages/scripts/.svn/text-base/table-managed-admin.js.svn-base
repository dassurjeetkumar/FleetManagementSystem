var TableManagedAdmin = function() {

	return {

		// main function to initiate the module
		init : function() {

			if (!jQuery().dataTable) {
				return;
			}

// Strat Code After This

			$('#addPage').dataTable({
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
				"sAjaxSource" : "page.action",
				"sPaginationType" : "bootstrap",
			
			 "oLanguage": { "sLengthMenu": "_MENU_ records", "oPaginate": {
			 "sPrevious": "Prev", "sNext": "Next" } }, "aoColumnDefs": [ {
			 'bSortable': false, 'aTargets': [0] }, 
			 { "bSearchable": false, "aTargets": [ 0 ] } ]
			 

			});
			
			jQuery('#addPage_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#addPage_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
			

			
			$('#roleView').dataTable({
				"aaSorting" : [ [ 1, 'asc' ] ],
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
			
            
          
            $('#addGroup').dataTable({
            	"aaSorting" : [ [ 1, 'asc' ] ],
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


            
            $('#designationview').dataTable({
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
			 

			});
            jQuery('#designationview_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#designationview_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
            
            
            $('#employeetypeview').dataTable({
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
				"sAjaxSource" : "ShowEmployeeTypeAjax.action",
				"sPaginationType" : "bootstrap",
			
			 "oLanguage": { "sLengthMenu": "_MENU_ records", "oPaginate": {
			  "sPrevious": "Prev", "sNext": "Next" } }, "aoColumnDefs": [ {
			  'bSortable': false, 'aTargets': [0] }, { "bSearchable": false,
			  "aTargets": [ 0 ] } ]
			 

			});
            jQuery('#employeetypeview_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#employeetypeview_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
            
            
            $('#vendorview').dataTable({
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
			

			});
            jQuery('#vendorview_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#vendorview_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
            
            $('#employeeview').dataTable({
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
            jQuery('#employeeview_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#employeeview_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
            
            $('#userview').dataTable({
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
				"sPaginationType" : "bootstrap" ,
			
			 "oLanguage": { "sLengthMenu": "_MENU_ records", "oPaginate": {
			 "sPrevious": "Prev", "sNext": "Next" } }, "aoColumnDefs": [ {
			 'bSortable': false, 'aTargets': [0] }, { "bSearchable": false,
			 "aTargets": [ 0 ] } ]
			

			});
			
            jQuery('#userview_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#userview_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
            
            
            $('#addGroupdetaills').dataTable({
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
     


            
            $('#batalistView').dataTable({
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
                "sAjaxSource" : "batalistdetails.action",
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
            jQuery('#batalistView_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#batalistView_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
     
            
            
          
            
            $('#ShiftTypeList').dataTable({
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
				"sAjaxSource" : "ShiftType.action",
				"sPaginationType" : "bootstrap" ,
			
			 "oLanguage": { "sLengthMenu": "_MENU_ records", "oPaginate": {
			 "sPrevious": "Prev", "sNext": "Next" } }, "aoColumnDefs": [ {
			 'bSortable': false, 'aTargets': [0] }, { "bSearchable": false,
			 "aTargets": [ 0 ] } ]
			

			});
			
            jQuery('#ShiftTypeList_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#ShiftTypeList_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
            
//End Code Before This

		}

	};

}();
