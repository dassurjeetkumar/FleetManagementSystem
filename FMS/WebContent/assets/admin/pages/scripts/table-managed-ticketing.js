var TableManagedTicket = function() {

	return {
 
		// main function to initiate the module
		init : function() {

			if (!jQuery().dataTable) {
				return;
			}
		////////////////////////////
//			$
//			.ajax({
//				type : "post",
//				url : "",
//				success : function(
//						result) {
//
//					
//				}
//			});	
			
			
			
		////////////////////////////
			
			var tickTable = $('#addticketInv').dataTable({
			     	"aaSorting": [
			     	                [0, 'asc']
			     	            ],
			         "aLengthMenu": [
			             [10, 50, 100],
			             [10, 50, 100] // change per page values here
			         ],
			         // set the initial value
			         "iDisplayLength": 5,
			         "bProcessing" : true,
			         "bServerSide" : true,
			         "sAjaxSource" : "viewTicketInv.action", 
			         "sPaginationType": "bootstrap",
			         "oLanguage": {
			             "sLengthMenu": "_MENU_ records",
			             "oPaginate": {
			                  "sPrevious": "Prev", 
			                     "sNext": "Next"
			             }
			         },
			         "bFilter" : false,
					 "bSelect" : false,
			         "bPaginate":false, 		         
			         "aoColumnDefs": [
			             { 'bSortable': false, 'aTargets': [0,1,2,3,4,5,6,7,8] },
			             { "bSearchable": false, "aTargets": [ 0 ]},
			             { "sClass": "tick_no_books", "aTargets": [ 7 ] },
			             { "sClass": "tick_value", "aTargets": [ 8 ] },
			             
			          ],
			        /* "oLanguage" : {
							"sZeroRecords" : "No data available",
							"sEmptyTable" : ""
						},*/
						/*"columns": [
						            { "width": "5px" },
						            null,
						            null,
						            null,
		  				            null,
						            null,
						            null,
						            null
						            
						          ],*/

			         "bLengthChange" : false,
					 "sDom" : '<"top">rt<"bottom"flp><"clear">',
					
					 "fnInitComplete": function(oSettings, json) {
						 forTicketType(); passtable();
					 },
			     }).wrap("<div style='position:relative;overflow:auto;height:300px;width:100%'/>");
			jQuery('#addticketInv_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#addticketInv_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline");
			    
			    
					///alert('called a');	

	/* $('#addticketInv').dataTable({
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
         "sAjaxSource" : "viewTicketInv.action",
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
//         "bRetrieve":true,
//         "bDestroy":true,
//         
//         "fnInitComplete": function(oSettings, json) {
//             a();
//         }
     }); 

	 jQuery('#addticketInv_wrapper .dataTables_filter input').addClass("form-control input-medium input-inline"); // modify table search input
	  jQuery('#addticketInv_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline");
//	function a(){
		///alert('called a');
		
*/	 $('#viewTicketBag').dataTable({
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
   "sAjaxSource" : "viewTicketBagRecord.action",
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
jQuery('#viewTicketBag_wrapper .dataTables_filter input').addClass("form-control input-xsmall input-inline"); // modify table search input
jQuery('#viewTicketBag_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline");

		/* $('#viewTicketBag').dataTable({
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
         "sAjaxSource" : "viewTicketBagRecord.action",
         "sPaginationType": "bootstrap",
         "oLanguage": {
             "sLengthMenu": "_MENU_ records",
             "oPaginate": {
                 "sPrevious": "Prev",
                 "sNext": "Next"
             }
         },
         "oLanguage" : {
				"sZeroRecords" : "",
				"sEmptyTable" : ""
			},
			
      
     
		 
        "aoColumnDefs": [
             { 'bSortable': false, 'aTargets': [0] },
             { "bSearchable": false, "aTargets": [ 0 ] }
         ]
         
     });
	 jQuery('#viewTicketBag_wrapper .dataTables_filter input').addClass("form-control input-medium input-inline"); // modify table search input
	  jQuery('#viewTicketBag_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline");*/
	
	function passtable(){
		$('#viewPassInv').dataTable({
		
	     	"aaSorting": [
	     	                [0, 'asc']
	     	            ],
	         "aLengthMenu": [
	             [10, 50, 100], 
	             [10, 50, 100] // change per page values here
	         ],
	         // set the initial value
	         "iDisplayLength": 5,
	         "bProcessing" : true,
	         "bServerSide" : true, 
	       
	         "sAjaxSource" : "viewPassInv.action",
	         "sPaginationType": "bootstrap",
	         "oLanguage": {
	             "sLengthMenu": "_MENU_ records",
	             "oPaginate": {
	                 "sPrevious": "Prev",
	                 "sNext": "Next"
	             }
	          },
	        "bFilter" : false,
			 "bSelect" : false,
	         "bPaginate":false, 
	        /* "oLanguage" : {
					"sZeroRecords" : "",
					"sEmptyTable" : ""
				},*/
				
	         "bLengthChange" : false,
		"sDom" : '<"top">rt<"bottom"flp><"clear">',
				        "aoColumnDefs": [
	             { 'bSortable': false, 'aTargets': [0,1,2,3,4,5,6,7,8] },
	             { "bSearchable": false, "aTargets": [ 0 ], },
	             { "sClass": "pass_no_books", "aTargets": [ 9 ] },
	             { "sClass": "pass_value", "aTargets": [ 10 ] },
	         ],
//	         "bRetrieve":true,
//	         "bDestroy":true,
	         "fnInitComplete": function(oSettings, json) {
	        	 forPassType();luggageTbale();
		 },
	     }).wrap("<div style='position:relative;overflow:auto;height:300px;'/>");;
	  
	     jQuery('#viewPassInv_wrapper .dataTables_filter input').addClass("form-control input-xsmall input-inline"); // modify table search input
	     jQuery('#viewPassInv_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline");
	}
	function luggageTbale(){
		$('#viewLuggageInv').dataTable({
		
	     	"aaSorting": [
	     	                [0, 'asc']
	     	            ],
	         "aLengthMenu": [ 
	             [10, 50, 100], 
	             [10, 50, 100] // change per page values here
	         ],
	         // set the initial value
	         "iDisplayLength": 5,
	         "bProcessing" : true,
	         "bServerSide" : true,
	         "sAjaxSource" : "viewLuggageInv.action",
	         "sPaginationType": "bootstrap",
	         "oLanguage": {
	             "sLengthMenu": "_MENU_ records",
	             "oPaginate": {
	                 "sPrevious": "Prev",
	                 "sNext": "Next"
	             }
	         },
	         "bFilter" : false,
			 "bSelect" : false,
	         "bPaginate":false, 
	         /*"oLanguage" : {
					"sZeroRecords" : "",
					"sEmptyTable" : ""
				},*/
				
	         "bLengthChange" : false,
	         //"sDom":'flrtip',

		 "sDom" : '<"top">rt<"bottom"flp><"clear">',
             	        "aoColumnDefs": [
	             { 'bSortable': false, 'aTargets': [0,1,2,3,4,5,6] },
	             { "bSearchable": false, "aTargets": [ 0 ] },
	             { "sClass": "lugg_no_books", "aTargets": [ 6 ] },
	            
	         ], 
         "fnInitComplete": function(settings, json) {
        	 forLuggageType();getPrint();
			 //alert("hello");
        	 
			  },
	     }).wrap("<div style='position:relative;overflow:auto;height:300px;'/>");
	jQuery('#viewLuggageInv_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
    jQuery('#viewLuggageInv_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline");
}
	
		$('#viewTSheetInv').dataTable({
		
	     	"aaSorting": [
	     	                [0, 'asc']
	     	            ],
	         "aLengthMenu": [ 
	             [10, 50, 100], 
	             [10, 50, 100] // change per page values here
	         ],
	         // set the initial value
	         "iDisplayLength": 5,
	         "bProcessing" : true,
	         "bServerSide" : true,
	         "sAjaxSource" : "viewTSheetInv.action",
	         "sPaginationType": "bootstrap",
	         "oLanguage": {
	             "sLengthMenu": "_MENU_ records",
	             "oPaginate": {
	                 "sPrevious": "Prev",
	                 "sNext": "Next"
	             }
	         },
	         "bFilter" : false,
			 "bSelect" : false,
	         "bPaginate":false, 
	         /*"oLanguage" : {
					"sZeroRecords" : "",
					"sEmptyTable" : ""
				},*/
				
	         "bLengthChange" : false,
	         //"sDom":'flrtip',

		 "sDom" : '<"top">rt<"bottom"flp><"clear">',
             	        "aoColumnDefs": [
	             { 'bSortable': false, 'aTargets': [0,1,2,3,4] },
	             { "bSearchable": false, "aTargets": [ 0 ] },
	             
	            
	         ], 
        
	     }).wrap("<div style='position:relative;overflow:auto;height:300px;'/>");
	jQuery('#viewTSheetInv_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
    jQuery('#viewTSheetInv_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline");
	
	 
 $('#receiveticketInv').dataTable({
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
	         "sAjaxSource" : "receiveTicketInv.action",
	         "sPaginationType": "bootstrap",
	        "bFilter" : false,
			 "bSelect" : false,
	         "bPaginate":false, 
	         "oLanguage" : {
					"sZeroRecords" : "",
					"sEmptyTable" : ""
				},
				
	         "bLengthChange" : false,
			"sDom" : '<"top">rt<"bottom"flp><"clear">',
 
	         "oLanguage": {
	             "sLengthMenu": "_MENU_ records",
	             "oPaginate": {
	                 "sPrevious": "Prev",
	                 "sNext": "Next"
	             }
	         },
	         "aoColumnDefs": [
	         	             { 'bSortable': false, 'aTargets': [0,1,2,3,4,5,6] },
	         	             { "bSearchable": false, "aTargets": [ 0 ] }
	         	         ] 
	     }).wrap("<div style='position:relative;overflow:auto;height:600px;'/>"); 
 jQuery('#receiveticketInv_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
 jQuery('#receiveticketInv_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline");
 
 $('#viewDenomination').dataTable({
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
         "sAjaxSource" : "showDenomination.action",
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
	 jQuery('#viewDenomination_wrapper .dataTables_filter input').addClass("form-control input-xsmall input-inline"); // modify table search input
     jQuery('#viewDenomination_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline");
	 
	
	 

	
	$('#issueticket').dataTable({
     	"aaSorting":[[1, 'asc']],
	    "aLengthMenu": [[10, 50, 100],[10, 50, 100]],
	    "iDisplayLength": 10,
	    "bProcessing" : true,
	    "bServerSide" : true,
	    "sAjaxSource" : "issueStockdata.action",
	    "sPaginationType": "bootstrap",
	    "oLanguage": {
	    	"sLengthMenu": "_MENU_ records",
	        "oPaginate": {
	        	"sPrevious": "Prev",
	            "sNext": "Next"
	        }
	    },
    	"oLanguage" : {
        	 "sZeroRecords" : "No matching records found",
        	 "sEmptyTable" : ""
        },
        "bFilter" : false,
		"bSelect" : false,
		"bPaginate":false, 
		"bLengthChange" : false,
		"sDom" : '<"top">rt<"bottom"flp><"clear">',
		"aoColumnDefs": [{ 'bSortable': false, 'aTargets': [0,1,2,3,4,5,6,7] },{ "bSearchable": false, "aTargets": [ 0 ] } ,
		                 { "sClass": "normalBooks", "aTargets": [ 4 ] }, { "sClass": "normalValue", "aTargets": [ 6 ] }] ,
		"fnInitComplete": function(oSettings, json) {
			getNormalTotal(); ia();
		}
		}).wrap("<div style='position:relative;overflow:auto;height:300px;'/>");
		jQuery('#issueticket_wrapper .dataTables_filter input').addClass("form-control input-xsmall input-inline"); // modify table search input
		jQuery('#issueticket_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline");
     
		function ia(){
			$('#issuepass').dataTable({
				"aaSorting": [[1, 'asc']],
				"aLengthMenu": [[10, 50, 100],[10, 50, 100] ],
				"iDisplayLength": 10,
				 "bProcessing" : true,
				 "bServerSide" : true,
				 "sAjaxSource" : "issuePassStock.action",
				 "sPaginationType": "bootstrap",
				 "oLanguage": {
				 "sLengthMenu": "_MENU_ records",
				 "oPaginate": {
					 "sPrevious": "Prev",
					 "sNext": "Next"
				     }
				 },
				 "oLanguage" : {
					 "sZeroRecords" : "No matching records found",
					 "sEmptyTable" : ""
				 },
				 "bFilter" : false,
				 "bSelect" : false,
				 "bPaginate":false, 
				 "bLengthChange" : false,
				 "sDom" : '<"top">rt<"bottom"flp><"clear">',
				
				"aoColumnDefs": [{ 'bSortable': false, 'aTargets': [0,1,2,3,4,5,6,7,8,9,10] },{ "bSearchable": false, "aTargets": [ 0 ] },
				                 { "sClass": "passBooks", "aTargets": [7] }, { "sClass": "passValue", "aTargets": [9] }] ,
				"fnInitComplete": function(oSettings, json) {
				    getPassTotal(); ib();
				 }
	     }).wrap("<div style='position:relative;overflow:auto;height:300px;'/>"); 
	 jQuery('#issuepass_wrapper .dataTables_filter input').addClass("form-control input-xsmall input-inline"); // modify table search input
     jQuery('#issuepass_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline");
	 }
    
	 function ib(){
	 
		 $('#issueluggage').dataTable({
			 "aaSorting": [[1, 'asc']],
			 "aLengthMenu": [[10, 50, 100],[10, 50, 100]],
	         "iDisplayLength": 10,
	         "bProcessing" : true,
	         "bServerSide" : true,
	         "sAjaxSource" : "issueLuggageStock.action",
	         "sPaginationType": "bootstrap",
	         "oLanguage": {
	             "sLengthMenu": "_MENU_ records",
	             "oPaginate": {
	                 "sPrevious": "Prev",
	                 "sNext": "Next"
	             }
	         },
	         "oLanguage" : {
				"sZeroRecords" : "No matching records found",
				"sEmptyTable" : ""
	         },
	         "bFilter" : false,
			 "bSelect" : false,
	         "bPaginate":false, 
	         "bLengthChange" : false,
			 "sDom" : '<"top">rt<"bottom"flp><"clear">',
			 "aoColumnDefs": [
			                  { 'bSortable': false, 'aTargets': [0,1,2,3,4,5,6,7] },
			                  { "bSearchable": false, "aTargets": [ 0 ] }, { "sClass": "luggageBooks", "aTargets": [ 4 ] }, 
	             ] ,
	         "fnInitComplete": function(oSettings, json) {
				    getLuggageTotal(); 
				 }
	     }).wrap("<div style='position:relative;overflow:auto;height:300px;'/>"); 
	 jQuery('#issueluggage_wrapper .dataTables_filter input').addClass("form-control input-xsmall input-inline"); // modify table search input
     jQuery('#issueluggage_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline");
	 };
	 
	 $('#issuetsheet').dataTable({
		 "aaSorting": [[1, 'asc']],
		 "aLengthMenu": [[10, 50, 100],[10, 50, 100]],
         "iDisplayLength": 10,
         "bProcessing" : true,
         "bServerSide" : true,
         "sAjaxSource" : "issueTSheetStock.action",
         "sPaginationType": "bootstrap",
         "oLanguage": {
             "sLengthMenu": "_MENU_ records",
             "oPaginate": {
                 "sPrevious": "Prev",
                 "sNext": "Next"
             }
         },
         "oLanguage" : {
			"sZeroRecords" : "No matching records found",
			"sEmptyTable" : ""
         },
         "bFilter" : false,
		 "bSelect" : false,
         "bPaginate":false, 
         "bLengthChange" : false,
		 "sDom" : '<"top">rt<"bottom"flp><"clear">',
		 "aoColumnDefs": [
		                  { 'bSortable': false, 'aTargets': [0,1,2,3,4] },
		                  { "bSearchable": false, "aTargets": [ 0 ] }
             ] ,
        
     }).wrap("<div style='position:relative;overflow:auto;height:300px;'/>"); 
 jQuery('#issuetsheet_wrapper .dataTables_filter input').addClass("form-control input-xsmall input-inline"); // modify table search input
 jQuery('#issuetsheet_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline");

 $('#viewroutmappisdetails').dataTable({
	 "aaSorting" : [ [ 1, 'asc' ] ],
		"aLengthMenu" : [ [ 10, 25, 50, -1], [ 10, 25, 50, "All" ]],
		"iDisplayLength" : 10,
		"bProcessing" : true,
		"bServerSide" : true,
		"sAjaxSource" : "viewroutmapdetails.action",
		"sPaginationType" : "bootstrap",
	
	 "oLanguage": { "sLengthMenu": "_MENU_ records", "oPaginate": {
	 "sPrevious": "Prev", "sNext": "Next" } }, "aoColumnDefs": [ {
	 'bSortable': false, 'aTargets': [0] }, { "bSearchable": false,
	 "aTargets": [ 0 ] } ]
	

    
}); 
 jQuery('#viewroutmappisdetails_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
 jQuery('#viewroutmappisdetails_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown

	 
		}
	};
}();
