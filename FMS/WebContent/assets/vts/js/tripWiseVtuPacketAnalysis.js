
function getTripVtuPacketTable(scheduleNo) {
	$('#vtuPacketTable').show();
	document.getElementById("vtuPacketAnalysis").style.display = '';
	var selectedDate=document.getElementById("selecteddate").value;
	
	$('#vtuPacketAnalysis')
			.dataTable(
					{
						"aLengthMenu" : [ [ 5, 15, 20, -1 ],
								[ 5, 15, 20, "All" ] // change
						],
						// set the initial value
						"iDisplayLength" : 20,
						"sAjaxSource" : "showTripWiseVTUPacketAnalysis.action?scheduleNo="+ scheduleNo+"&selectedDate="+selectedDate,
						"sPaginationType" : "bootstrap",
						"bDestroy" : true,
						"oLanguage" : {
							"sLengthMenu" : "_MENU_ records",
							"oPaginate" : {
								"sPrevious" : "Prev",
								"sNext" : "Next"
							}
						},
						"bFilter" : false,
						"bSelect" : false,
						"bPaginate" : false,
						"oLanguage" : {
							"sZeroRecords" : "",
							"sEmptyTable" : ""
						},
						"aoColumnDefs" : [ {
							'bSortable' : false,
							'aTargets' : [ 0, 1, 2, 3, 4 ]
						} ],
						aaSorting:[],
						"bLengthChange" : false,
						"sDom" : '<"top">rt<"bottom"flp><"clear">'
		 			})
			.wrap(
					"<div style='position:auto;overflow:auto;height:300px;width:100%'/>");
	jQuery('#vtuPacketAnalysis_wrapper .dataTables_filter input').addClass(
			"form-control input-medium input-inline"); // modify table
	// search input
	jQuery('#vtuPacketAnalysis_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");

}
function validateTripVtuPacketFields(scheduleNo,depotNo,divisonNo,selectedDate)
{
	 if(divisonNo==0)
	 {
		alert("Please Select Divison");
		return false;
	 }
	 if(depotNo==0)
	 {
		alert("Please Select Depot");
		return false;
	 }
	 if(selectedDate==0)
     {
	   alert("Please Select Date");
	   return false;
     }
     if(scheduleNo==0)
     {
	   alert("Please Select Schedule Number");
	   return false;
     }

 return true;
}
function getTripVtuPacketTableOnSubmit() {
	
	var selectedDate=document.getElementById("selecteddate").value;
	var scheduleNo=$("#schedulelist").val();
	var depotNo=$("#depotlist").val();
	var divisonNo=$("#divisionlist").val();
	
	var validateflag=validateTripVtuPacketFields(scheduleNo,depotNo,divisonNo,selectedDate);
	if(validateflag==true)
	{
		$('#vtuPacketTable').show();
		document.getElementById("vtuPacketAnalysis").style.display = '';
	$('#vtuPacketAnalysis')
			.dataTable(
					{
						"aLengthMenu" : [ [ 5, 15, 20, -1 ],
								[ 5, 15, 20, "All" ] // change
						],
						// set the initial value
						"iDisplayLength" : 20,
						"sAjaxSource" : "showTripWiseVTUPacketAnalysis.action?scheduleNo="+ scheduleNo+"&selectedDate="+selectedDate,
						"sPaginationType" : "bootstrap",
						"bDestroy" : true,
						"oLanguage" : {
							"sLengthMenu" : "_MENU_ records",
							"oPaginate" : {
								"sPrevious" : "Prev",
								"sNext" : "Next"
							}
						},
						"bFilter" : false,
						"bSelect" : false,
						"bPaginate" : false,
						"oLanguage" : {
							"sZeroRecords" : "",
							"sEmptyTable" : ""
						},
						"aoColumnDefs" : [ {
							'bSortable' : false,
							'aTargets' : [ 0, 1, 2, 3, 4 ]
						} ],
						aaSorting:[],
						"bLengthChange" : false,
						"sDom" : '<"top">rt<"bottom"flp><"clear">'
					})
			.wrap(
					"<div style='position:auto;overflow:auto;height:300px;width:100%'/>");
	jQuery('#vtuPacketAnalysis_wrapper .dataTables_filter input').addClass(
			"form-control input-medium input-inline"); // modify table
	// search input
	jQuery('#vtuPacketAnalysis_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");

	}
	}
function getPacketReceived(deviceid,startTime,endTime){	
	$('#VtuPacketReceived').show();
	document.getElementById("VtuPacketReceivedTable").style.display = '';
	var selectedDate=document.getElementById("selecteddate").value;
	
	$('#VtuPacketReceivedTable')
			.dataTable(
					{
						"aLengthMenu" : [ [ 5, 15, 20, -1 ],
								[ 5, 15, 20, "All" ] // change
						],
						// set the initial value
						"iDisplayLength" : 20,
						"sAjaxSource" : "viewVtuPacketRecieved.action?deviceid="+deviceid+"&selectedDate="+selectedDate+"&startTime="+startTime+"&endTime="+endTime,
						"sPaginationType" : "bootstrap",
						"bDestroy" : true,
						"oLanguage" : {
							"sLengthMenu" : "_MENU_ records",
							"oPaginate" : {
								"sPrevious" : "Prev",
								"sNext" : "Next"
							}
						},
						"bFilter" : false,
						"bSelect" : false,
						"bPaginate" : false,
						"oLanguage" : {
							"sZeroRecords" : "",
							"sEmptyTable" : ""
						},
						"aoColumnDefs" : [ {
							'bSortable' : false,
							'aTargets' : [ 0, 1, 2, 3, 4 ]
						} ],
						aaSorting:[],
						"bLengthChange" : false,
						"sDom" : '<"top">rt<"bottom"flp><"clear">'
					})
			.wrap(
					"<div style='position:auto;overflow:auto;height:300px;width:100%'/>");
	jQuery('#VtuPacketReceivedTable_wrapper .dataTables_filter input').addClass(
			"form-control input-medium input-inline"); // modify table
	// search input
	jQuery('#VtuPacketReceivedTable_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");

}
	
