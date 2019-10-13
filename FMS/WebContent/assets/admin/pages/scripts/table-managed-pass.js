var TableManagedPass = function() {

	return {

		// main function to initiate the module
		init : function() {

			if (!jQuery().dataTable) {
				return;
			}

// Strat Code After This

			$('#passRateTable').dataTable({
				"aLengthMenu" : [ [10, 25, 50, 100 ], [10, 25, 50, 100 ] // change
																	// per page
																	// values
																	// here
				],
				// set the initial value
				"iDisplayLength" : 10,
				"bProcessing" : true,
				"bServerSide" : true,
				"sAjaxSource" : "PassRateAction.action",
				"sPaginationType" : "bootstrap",
			
			  "oLanguage": { "sLengthMenu": "_MENU_ records", "oPaginate": {
			  "sPrevious": "Prev", "sNext": "Next" } }, 
			  "aoColumnDefs": [ 
			  { 'bSortable': false, 'aTargets': [0] }, 
			  { "bSearchable": false,"aTargets": [ 0,8 ] },
			  { "sClass": "url", "aTargets": [ 8 ] }
			  ]
			 

			});
			jQuery('#passRateTable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#passRateTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
            //jQuery('#passRateTable_wrapper .dataTables_length select').select2(); // initialize select2 dropdown


			$('#passTypeTable').dataTable({
				"aLengthMenu" : [ [10, 25, 50, 100 ], [10, 25, 50, 100 ] // change
																	// per page
																	// values
																	// here
				],
				// set the initial value
				"iDisplayLength" : 10,
				"bProcessing" : true,
				"bServerSide" : true,
				"sAjaxSource" : "PassTypeAction.action",
				"sPaginationType" : "bootstrap",
			
			  "oLanguage": { "sLengthMenu": "_MENU_ records", "oPaginate": {
			  "sPrevious": "Prev", "sNext": "Next" } }, "aoColumnDefs": [ {
			  'bSortable': false, 'aTargets': [0] }, { "bSearchable": false,
			  "aTargets": [ 0,4] },{ "sClass": "url", "aTargets": [ 4 ] } ]
			 

			});
			jQuery('#passTypeTable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#passTypeTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
            //jQuery('#passTypeTable_wrapper .dataTables_length select').select2(); // initialize select2 dropdown

//End Code Before This
            $('#passissueryypeview').dataTable({
				"aLengthMenu" : [ [10, 25, 50, 100 ], [10, 25, 50, 100 ] // change
																	// per page
																	// values
																	// here
				],
				// set the initial value
				"iDisplayLength" : 10,
				"bProcessing" : true,
				"bServerSide" : true,
				"sAjaxSource" : "passissueryypeviewAction.action",
				"sPaginationType" : "bootstrap",
			
			  "oLanguage": { "sLengthMenu": "_MENU_ records", "oPaginate": {
			  "sPrevious": "Prev", "sNext": "Next" } }, "aoColumnDefs": [ {
			  'bSortable': false, 'aTargets': [0] }, { "bSearchable": false,
			  "aTargets": [ 0,4] },{ "sClass": "url", "aTargets": [ 4 ] } ]
			 

			});
			jQuery('#passissueryypeview_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#passissueryypeview_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
            
            
            $('#passdistancetypeview').dataTable({
				"aLengthMenu" : [ [10, 25, 50, 100 ], [10, 25, 50, 100 ] // change
																	// per page
																	// values
																	// here
				],
				// set the initial value
				"iDisplayLength" : 10,
				"bProcessing" : true,
				"bServerSide" : true,
				"sAjaxSource" : "passdistnacetypeviewAction.action",
				"sPaginationType" : "bootstrap",
			
			  "oLanguage": { "sLengthMenu": "_MENU_ records", "oPaginate": {
			  "sPrevious": "Prev", "sNext": "Next" } }, "aoColumnDefs": [ {
			  'bSortable': false, 'aTargets': [0] }, { "bSearchable": false,
			  "aTargets": [ 0,4] },{ "sClass": "url", "aTargets": [ 4 ] } ]
			 

			});
			jQuery('#passdistancetypeview_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#passdistancetypeview_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
            
            
            $('#passpurchasetypeview').dataTable({
				"aLengthMenu" : [ [10, 25, 50, 100 ], [10, 25, 50, 100 ] // change
																	// per page
																	// values
																	// here
				],
				// set the initial value
				"iDisplayLength" : 10,
				"bProcessing" : true,
				"bServerSide" : true,
				"sAjaxSource" : "passpurchasetypeviewAction.action",
				"sPaginationType" : "bootstrap",
			
			  "oLanguage": { "sLengthMenu": "_MENU_ records", "oPaginate": {
			  "sPrevious": "Prev", "sNext": "Next" } }, "aoColumnDefs": [ {
			  'bSortable': false, 'aTargets': [0] }, { "bSearchable": false,
			  "aTargets": [ 0,4] },{ "sClass": "url", "aTargets": [ 4 ] } ]
			 

			});
			jQuery('#passpurchasetypeview_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#passpurchasetypeview_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
            
            
		
	
	 $('#passdurationtypeview').dataTable({
			"aLengthMenu" : [ [10, 25, 50, 100 ], [10, 25, 50, 100 ] // change
																// per page
																// values
																// here
			],
			// set the initial value
			"iDisplayLength" : 10,
			"bProcessing" : true,
			"bServerSide" : true,
			"sAjaxSource" : "passdurationtypeviewAction.action",
			"sPaginationType" : "bootstrap",
		
		  "oLanguage": { "sLengthMenu": "_MENU_ records", "oPaginate": {
		  "sPrevious": "Prev", "sNext": "Next" } }, "aoColumnDefs": [ {
		  'bSortable': false, 'aTargets': [0] }, { "bSearchable": false,
		  "aTargets": [ 0,4] },{ "sClass": "url", "aTargets": [ 4 ] } ]
		 

		});
		jQuery('#passdurationtypeview_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
     jQuery('#passdurationtypeview_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
     
     
     $('#passcastetypeview').dataTable({
			"aLengthMenu" : [ [10, 25, 50, 100 ], [10, 25, 50, 100 ] // change
																// per page
																// values
																// here
			],
			// set the initial value
			"iDisplayLength" : 10,
			"bProcessing" : true,
			"bServerSide" : true,
			"sAjaxSource" : "passCasteTypeActionviewAction.action",
			"sPaginationType" : "bootstrap",
		
		  "oLanguage": { "sLengthMenu": "_MENU_ records", "oPaginate": {
		  "sPrevious": "Prev", "sNext": "Next" } }, "aoColumnDefs": [ {
		  'bSortable': false, 'aTargets': [0] }, { "bSearchable": false,
		  "aTargets": [ 0,4] },{ "sClass": "url", "aTargets": [ 4 ] } ]
		 

		});
		jQuery('#passcastetypeview_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
     jQuery('#passcastetypeview_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
     
     $('#passinstitutiontypeview').dataTable({
			"aLengthMenu" : [ [10, 25, 50, 100 ], [10, 25, 50, 100 ] // change
																// per page
																// values
																// here
			],
			// set the initial value
			"iDisplayLength" : 10,
			"bProcessing" : true,
			"bServerSide" : true,
			"sAjaxSource" : "passInstitutionTypeActionviewAction.action",
			"sPaginationType" : "bootstrap",
		
		  "oLanguage": { "sLengthMenu": "_MENU_ records", "oPaginate": {
		  "sPrevious": "Prev", "sNext": "Next" } }, "aoColumnDefs": [ {
		  'bSortable': false, 'aTargets': [0] }, { "bSearchable": false,
		  "aTargets": [ 0,4] },{ "sClass": "url", "aTargets": [ 4 ] } ]
		 

		});
		jQuery('#passinstitutiontypeview_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
        jQuery('#passinstitutiontypeview_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
  
        $('#passsubtypeviewjs').dataTable({
			"aLengthMenu" : [ [10, 25, 50, 100 ], [10, 25, 50, 100 ] // change
																// per page
																// values
																// here
			],
			// set the initial value
			"iDisplayLength" : 10,
			"bProcessing" : true,
			"bServerSide" : true,
			"sAjaxSource" : "PassSubTypeviewAction.action",
			"sPaginationType" : "bootstrap",
		
		  "oLanguage": { "sLengthMenu": "_MENU_ records", "oPaginate": {
		  "sPrevious": "Prev", "sNext": "Next" } }, "aoColumnDefs": [ {
		  'bSortable': false, 'aTargets': [0] }, { "bSearchable": false,
		  "aTargets": [ 0,4] },{ "sClass": "url", "aTargets": [ 4 ] } ]
		 

		});
		jQuery('#passsubtypeviewjs_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
        jQuery('#passsubtypeviewjs_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
  
        $('#passissuercentertypeview').dataTable({
			"aLengthMenu" : [ [10, 25, 50, 100 ], [10, 25, 50, 100 ] // change
																// per page
																// values
																// here
			],
			// set the initial value
			"iDisplayLength" : 10,
			"bProcessing" : true,
			"bServerSide" : true,
			"sAjaxSource" : "PassIssuerCenterviewAction.action",
			"sPaginationType" : "bootstrap",
		
		  "oLanguage": { "sLengthMenu": "_MENU_ records", "oPaginate": {
		  "sPrevious": "Prev", "sNext": "Next" } }, "aoColumnDefs": [ {
		  'bSortable': false, 'aTargets': [0] }, { "bSearchable": false,
		  "aTargets": [ 0,4] },{ "sClass": "url", "aTargets": [ 4 ] } ]
		 

		});
		jQuery('#passissuercentertypeview_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
        jQuery('#passissuercentertypeview_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
  
        $('#passissuercountertypeview').dataTable({
			"aLengthMenu" : [ [10, 25, 50, 100 ], [10, 25, 50, 100 ] // change
																// per page
																// values
																// here
			],
			// set the initial value
			"iDisplayLength" : 10,
			"bProcessing" : true,
			"bServerSide" : true,
			"sAjaxSource" : "PassCounterMasterviewAction.action",
			"sPaginationType" : "bootstrap",
		
		  "oLanguage": { "sLengthMenu": "_MENU_ records", "oPaginate": {
		  "sPrevious": "Prev", "sNext": "Next" } }, "aoColumnDefs": [ {
		  'bSortable': false, 'aTargets': [0] }, { "bSearchable": false,
		  "aTargets": [ 0,4] },{ "sClass": "url", "aTargets": [ 4 ] } ]
		 

		});
		jQuery('#passissuercountertypeview_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
        jQuery('#passissuercountertypeview_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
  
     
        $('#ticketSubTypeView').dataTable({
			"aLengthMenu" : [ [10, 25, 50, 100 ], [10, 25, 50, 100 ] // change
																// per page
																// values
																// here
			],
			// set the initial value
			"iDisplayLength" : 10,
			"bProcessing" : true,
			"bServerSide" : true,
			"sAjaxSource" : "TicketSubTypeviewAction.action",
			"sPaginationType" : "bootstrap",
		
		  "oLanguage": { "sLengthMenu": "_MENU_ records", "oPaginate": {
		  "sPrevious": "Prev", "sNext": "Next" } }, "aoColumnDefs": [ {
		  'bSortable': false, 'aTargets': [0] }, { "bSearchable": false,
		  "aTargets": [ 0,4] },{ "sClass": "url", "aTargets": [ 4 ] } ]
		 

		});
		jQuery('#ticketSubTypeView_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
     jQuery('#ticketSubTypeView_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
     
    
        
	}

	};

}();
