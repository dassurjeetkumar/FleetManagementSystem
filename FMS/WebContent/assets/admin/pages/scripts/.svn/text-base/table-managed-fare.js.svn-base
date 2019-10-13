var TableManagedFare = function() {

	return {

		// main function to initiate the module
		init : function() {

			if (!jQuery().dataTable) {
				return;
			}

// Strat Code After This

			 $('#rateTable').dataTable({
				 "aaSorting": [[1, 'asc']],
	                "aLengthMenu": [
					[10,25, 50, 100],
					[10,25, 50, 100]  // change per page values here
	                ],
	                // set the initial value
	                "iDisplayLength": 10,
	                "bProcessing" : true,
	                "bServerSide" : true,
	                "sAjaxSource" : "RateMasterAjaxAction.action",
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
			 jQuery('#rateTable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#rateTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	           // jQuery('#passRateTable_wrapper .dataTables_length select').select2(); // initialize select2 dropdown


			$('#fareChart').dataTable({
				"aaSorting": [[1, 'asc']],
                "aLengthMenu": [
					[10,25, 50, 100],
					[10,25, 50, 100]  // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 10,
                "bProcessing" : true,
                "bServerSide" : true,
                "sAjaxSource" : "FareChartAction.action",
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
			jQuery('#fareChart_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#fareChart_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
            //jQuery('#fareChart_wrapper .dataTables_length select').select2(); // initialize select2 dropdown

            
            $('#concessionTable').dataTable({
				"aaSorting": [[1, 'asc']],
                "aLengthMenu": [
					[10,25, 50, 100],
					[10,25, 50, 100]  // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 10,
                "bProcessing" : true,
                "bServerSide" : true,
                "sAjaxSource" : "ConcessionAction.action",
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
                    { "sClass": "url", "aTargets": [ 5 ] }
                ]
                
            });
			jQuery('#concessionTable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#concessionTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown

            
            $('#passengerTable').dataTable({
				"aaSorting": [[1, 'asc']],
                "aLengthMenu": [
					[10,25, 50, 100],
					[10,25, 50, 100]  // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 10,
                "bProcessing" : true,
                "bServerSide" : true,
                "sAjaxSource" : "PassengerTypeAction.action",
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
			jQuery('#passengerTable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#passengerTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown

            
            $('#luggageTypeTable').dataTable({
				"aaSorting": [[1, 'asc']],
                "aLengthMenu": [
					[10,25, 50, 100],
					[10,25, 50, 100]  // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 10,
                "bProcessing" : true,
                "bServerSide" : true,
                "sAjaxSource" : "LuggageTypeAction.action",
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
			jQuery('#luggageTypeTable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#luggageTypeTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown

            
            $('#revenueTypeTable').dataTable({
				"aaSorting": [[1, 'asc']],
                "aLengthMenu": [
					[10,25, 50, 100],
					[10,25, 50, 100]  // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 10,
                "bProcessing" : true,
                "bServerSide" : true,
                "sAjaxSource" : "RevenueTypeAction.action",
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
			jQuery('#revenueTypeTable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#revenueTypeTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown

            
            $('#btnHourChart').click(function (e) {
				
				 $("#tableid").show();

				  var serviceType = document.getElementById("service_type_id").value;
				  var routeNo = document.getElementById("route_id").value;
				  
					if (routeNo == '0') { 
						bootbox.alert('Select Route Number');
					} else if (serviceType == '0') {
						bootbox.alert('Select Service Type');
					}else{

					 $('#showHourlyChartTable').dataTable({
						"aaSorting" : [ [ 0, 'asc' ] ],
						"aLengthMenu" : [ [ 10, 25, 50, 100 ], [ 10, 25, 50, 100 ], 
						],
						"iDisplayLength" : 10,
						"bProcessing" : true,
						"bServerSide" : true,
						"bDestroy":true,
						"sAjaxSource" : "getHourlyChart.action?routeId="+ routeNo + "&serviceType=" + serviceType,
						"sPaginationType" : "bootstrap",
						"oLanguage" : {
							"sLengthMenu" : "_MENU_ records",
							"oPaginate" : {
								"sPrevious" : "Prev",
								"sNext" : "Next"
							}
						},
						"aoColumnDefs" : [
						 { 'bSortable': false, 'aTargets': [4,5,6,7,8,9,10] },
						{
							"bSearchable" : false,
							"aTargets" : [ 0 ]
						} ]
					});
					jQuery('#weeklychartid_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
					jQuery('#weeklychartid_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify
					}																// table per page dropdown
					
			 });
            
            $('#penaltyTicketTable').dataTable({
				"aaSorting": [[1, 'asc']],
                "aLengthMenu": [
					[10,25, 50, 100],
					[10,25, 50, 100]  // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 10,
                "bProcessing" : true,
                "bServerSide" : true,
                "sAjaxSource" : "PenaltyTicketAction.action",
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
			jQuery('#penaltyTicketTable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#penaltyTicketTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown


            
            $('#BankRemittanceDetailsTable').dataTable({
				"aaSorting": [[1, 'asc']],
                "aLengthMenu": [
					[10,25, 50, 100],
					[10,25, 50, 100]  // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 10,
                "bProcessing" : true,
                "bServerSide" : true,
                "sAjaxSource" : "BankRemittanceDetailsTableActions.action",
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
			jQuery('#BankRemittanceDetailsTable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#BankRemittanceDetailsTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown

//           

            $('#TollPassTable').dataTable({
            	"aaSorting": [
                              [1, 'asc']
                          ],
                "aLengthMenu": [
                    [10, 20, 50, 100],
                    [10, 20, 50, 100] // change per page values here
                ],
             // set the initial value
               "iDisplayLength": 10, 
                
                "bProcessing" : true,
                "bServerSide" : true,
                "sAjaxSource" : "AjaxTollPassTicketTableAction.action",
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
            jQuery('#TollPassTable_wrapper .dataTables_filter input').addClass("form-control input-medium input-inline"); // modify table search input
            jQuery('#TollPassTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
             

            $('#SpecialPassTable').dataTable({
            	"aaSorting": [
                              [1, 'asc']
                          ],
                "aLengthMenu": [
                    [10, 20, 50, 100],
                    [10, 20, 50, 100] // change per page values here
                ],
             // set the initial value
               "iDisplayLength": 10, 
                
                "bProcessing" : true,
                "bServerSide" : true,
                "sAjaxSource" : "AjaxspecialPassTicketTableAction.action",
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
            jQuery('#SpecialPassTable_wrapper .dataTables_filter input').addClass("form-control input-medium input-inline"); // modify table search input
            jQuery('#SpecialPassTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
             
   			
            
//End Code Before This

		}

	};

}();
