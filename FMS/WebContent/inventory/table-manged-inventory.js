var TableManagedInventory = function() {
	return {
	
		init : function() {
		if (!jQuery().dataTable) {
			return;
		}
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

	 }
		}
	 

	};

}();