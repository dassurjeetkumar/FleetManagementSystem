function getscheduleMismatching(depot_id,duty_dt){
	$('#schedulemismatch').attr("style", "display:''");
	 table = $('#schDetails');
     var oTable = table.dataTable({
    	 "aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
 		// per
 		// page
 		// values
 		// here
 		],
 		// set the initial value
 		"iDisplayLength" : 5,
 		"sAjaxSource" : "getscheduleMismatchingRecordDetails.action?givendate=" + duty_dt + "&depot_id=" + depot_id,
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

 	jQuery('#schDetails_wrapper .dataTables_filter input').addClass(
 			"form-control input-xsmall input-inline"); // modify table
 	// search input
 	jQuery('#schDetails_wrapper .dataTables_length select').addClass(
 			"form-control input-xsmall input-inline");

}