var TableManagedWeeklyChart = function() {

	return {
		
		// main function to initiate the module
		init : function() {			
			 $('#scheduleidlist').click(function (e) {
				
				 $("#tableid").show();
				  var rolid=document.getElementById("scheduleidlist").value;
				 // alert("sffasdfs"+rolid);
				  $('#scheduleidlistidd').val(rolid);
				  var rolid1=document.getElementById("scheduleidlistidd").value;
				 // alert("sffasdfssd"+rolid1);
					 $('#weeklychartid').dataTable({
						"aaSorting" : [ [ 0, 'asc' ] ],
						"aLengthMenu" : [ [ 10, 25, 50, 100 ], [ 10, 25, 50, 100 ], // change per page values here
						],
						// set the initial value
						"iDisplayLength" : 10,
						"bProcessing" : true,
						"bServerSide" : true,
//						"bRetrieve":true,
						"bDestroy":true,
						"sAjaxSource" : "viewWeeklyChartAction.action?scheduleid2="+rolid,
						"sPaginationType" : "bootstrap",
					   // "bPaginate":false, 
						 /*"oLanguage" : {
								"sZeroRecords" : "",
								"sEmptyTable" : ""
							},*/
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
																					// table per page dropdown
					
			 });
 				// Strat Code After This
				
//			
			// End Code Before This 
			 
		}
	};

}();

	/*return {

		// main function to initiate the module
		init : function() {

			if (!jQuery().dataTable) {
				return;
			}

			// Strat Code After This

			$('#weeklychartviewid').dataTable({
				"aaSorting" : [ [ 1, 'asc' ] ],
				"aLengthMenu" : [ [ 10, 25, 50, 100 ], [ 10, 25, 50, 100 ], // change
																			// per
																			// page
																			// values
																			// here
				],
				// set the initial value
				"iDisplayLength" : 10,
				"bProcessing" : true,
				"bServerSide" : true,
				"sAjaxSource" : "viewWeeklyChartAction.action",
				"sPaginationType" : "bootstrap",
				"oLanguage" : {
					"sZeroRecords" : "",
					"sEmptyTable" : ""
				},
				"oLanguage" : {
					"sLengthMenu" : "_MENU_ records",
					"oPaginate" : {
						"sPrevious" : "Prev",
						"sNext" : "Next"
					}
				},
				"aoColumnDefs": [
					             { 'bSortable': false, 'aTargets': [0,2,3,4,5,6,7,8] },
					             { "bSearchable": false, "aTargets": [0,2,3,4,5,6,7,8] }
					          ],

			});
			jQuery('#weeklychartviewid_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify
																		// table
																		// search
																		// input
			jQuery('#weeklychartviewid_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify
																			// table
																			// per
																			// page
																			// dropdown

			
			// End Code Before This

		}

	};

}();

	*/