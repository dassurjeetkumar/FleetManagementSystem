function getRouteDeviationForDashBoard(fromdate,todate,depotid){
	//console.log("ddddd"+fromdate+todate+depotid);
	
			$('#routeDeviationId').dataTable({
				
	            	"aaSorting": [[1, 'asc']
	            	            ],
	                "aLengthMenu": [
	                    [10,25,50,100],
	                    [10,25,50,100], // change per page values here
	                ],
	                // set the initial value
	                "iDisplayLength": 10,
	                "bProcessing" : true,
	                "bServerSide" : true,
	                "bDestroy":true,
	                "sAjaxSource" : "routeDeviationForDashBoardReport.action?fromdate="+fromdate+"&todate="+todate+"&depotid="+depotid,
	                "sPaginationType": "bootstrap",
	                "oLanguage": {
	                    "sLengthMenu": "_MENU_ records",
	                    "oPaginate": {
	                        "sPrevious": "Prev",
	                        "sNext": "Next"
	                    }
	                },
	               "aoColumnDefs": [
	                    { 'bSortable': false, 'aTargets': [0,1,2,3,4,5,6] },
	                    { "bSearchable": false, "aTargets": [ 0 ] },
	                    	                ]
	                

	            });
			 	jQuery('#routeDeviationId_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#routeDeviationId_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
		
	            
}	

function getTripCancelationDashBoard(fromdate,todate,depotid){
	
			$('#tripCancelationId').dataTable({
				
	            	"aaSorting": [[1, 'asc']
	            	            ],
	                "aLengthMenu": [
	                    [10,25,50,100],
	                    [10,25,50,100], // change per page values here
	                ],
	                // set the initial value
	                "iDisplayLength": 10,
	                "bProcessing" : true,
	                "bServerSide" : true,
	                "bDestroy":true,
	                "sAjaxSource" : "showTripCancelationDashBoard.action?fromdate="+fromdate+"&todate="+todate+"&depotid="+depotid,
	                "sPaginationType": "bootstrap",
	                "oLanguage": {
	                    "sLengthMenu": "_MENU_ records",
	                    "oPaginate": {
	                        "sPrevious": "Prev",
	                        "sNext": "Next"
	                    }
	                },
	               "aoColumnDefs": [
	                    { 'bSortable': false, 'aTargets': [0,1,2,3,4,5,6] },
	                    { "bSearchable": false, "aTargets": [ 0 ] },
	                    	                ]
	                

	            });
			 	jQuery('#tripCancelationId_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#tripCancelationId_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
		
	            console.log("ddddd"+fromdate+todate+depotid);
	        	
}

function getKmCancelationDashBoard(fromdate,todate,depotid){
	
			$('#KMCancelationId').dataTable({
				
	            	"aaSorting": [[1, 'asc']
	            	            ],
	                "aLengthMenu": [
	                    [10,25,50,100],
	                    [10,25,50,100], // change per page values here
	                ],
	                // set the initial value
	                "iDisplayLength": 10,
	                "bProcessing" : true,
	                "bServerSide" : true,
	               "bDestroy":true,
	                "sAjaxSource" : "showKmCancelationDashBoard.action?fromdate="+fromdate+"&todate="+todate+"&depotid="+depotid,
	                "sPaginationType": "bootstrap",
	                "oLanguage": {
	                    "sLengthMenu": "_MENU_ records",
	                    "oPaginate": {
	                        "sPrevious": "Prev",
	                        "sNext": "Next"
	                    }
	                },
	               "aoColumnDefs": [
	                    { 'bSortable': false, 'aTargets': [0,1,2,3,4,5,6] },
	                    { "bSearchable": false, "aTargets": [ 0 ] },
	                    	                ]
	                

	            });
			 	jQuery('#KMCancelationId_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#KMCancelationId_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown        
}

function getAccidentRegisterDashBoard(fromdate,todate,depotid){

			$('#accidentRegisterId').dataTable({
				
	            	"aaSorting": [[1, 'asc']
	            	            ],
	                "aLengthMenu": [
	                    [10,25,50,100],
	                    [10,25,50,100], // change per page values here
	                ],
	                // set the initial value
	                "iDisplayLength": 10,
	                "bProcessing" : true,
	                "bServerSide" : true,
	                "bDestroy":true,
	                "sAjaxSource" : "showAccidentRegisterDashBoard.action?fromdate="+fromdate+"&todate="+todate+"&depotid="+depotid,
	                "sPaginationType": "bootstrap",
	                "oLanguage": {
	                    "sLengthMenu": "_MENU_ records",
	                    "oPaginate": {
	                        "sPrevious": "Prev",
	                        "sNext": "Next"
	                    }
	                },
	               "aoColumnDefs": [
	                    { 'bSortable': false, 'aTargets': [0,1,2,3,4,5,6] },
	                    { "bSearchable": false, "aTargets": [ 0 ] },
	                    	                ]
	                

	            });
			 	jQuery('#accidentRegisterId_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#accidentRegisterId_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown        
}

function getTotalScheduleDashBoard(fromdate,todate,depotid){
	//alert("Hello------");
	console.log("ddddd"+fromdate+todate+depotid);
	
			$('#scheduletableId').dataTable({
				
	            	"aaSorting": [[1, 'asc']
	            	            ],
	                "aLengthMenu": [
	                    [10,25,50,100],
	                    [10,25,50,100], // change per page values here
	                ],
	                // set the initial value
	                "iDisplayLength": 10,
	                "bProcessing" : true,
	                "bServerSide" : true,
	                "bDestroy":true,
	                "sAjaxSource" : "showTotalScheduleDashBoard.action?fromdate="+fromdate+"&todate="+todate+"&depotid="+depotid,
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
	                    { "bSearchable": false, "aTargets": [ 0 ] },
	                    	                ]
	                

	            });
			 	jQuery('#scheduletableId_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#scheduletableId_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown        
}

function getLicenceDetailsDashBoard(fromdate,todate,depotid){
	//alert("Hello------");
	console.log("ddddd"+fromdate+todate+depotid);
	
			$('#licenceDetailsId').dataTable({
				
	            	"aaSorting": [[1, 'asc']
	            	            ],
	                "aLengthMenu": [
	                    [10,25,50,100],
	                    [10,25,50,100], // change per page values here
	                ],
	                // set the initial value
	                "iDisplayLength": 10,
	                "bProcessing" : true,
	                "bServerSide" : true,
	                "bDestroy":true,
	                "sAjaxSource" : "showLicenceDetailsDashBoard.action?fromdate="+fromdate+"&todate="+todate+"&depotid="+depotid,
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
	                    { "bSearchable": false, "aTargets": [ 0 ] },
	                    	                ]
	                

	            });
			 	jQuery('#licenceDetailsId_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	            jQuery('#licenceDetailsId_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown        
}

