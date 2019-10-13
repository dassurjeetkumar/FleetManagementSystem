<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places,drawing,geometry"></script>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
<script src="https://code.jquery.com/jquery-1.11.3.min.js" type="text/javascript"></script>
	<script src="https://cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/responsive/1.0.7/js/dataTables.responsive.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/fixedheader/3.0.0/js/dataTables.fixedHeader.min.js" type="text/javascript"></script>
 <script src="assets/vts/js/scheduleroute.js"></script> 
<script src="assets/vts/js/vehiclealert.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
<%--  <script src="assets/global/plugins/jquery-1.11.0.min.js" --%>
<%-- 	type="text/javascript"></script> --%>
	
<script src="assets/vts/js/scheduledeviation.js"
	type="text/javascript"></script>
	<script src="assets/vts/js/vts.js" type="text/javascript"></script>

 
<Script>

var counter=0;
 $(document).ready(function(){
 	 
	$("#printbutton").hide();
	$("#scheduleprint").hide();
$("#schdeviation").hide();
$("#scheduleprint").hide();
	 document.getElementById("scheduleDevationdata").style.display = '';
 });
 function getRefresh() {
//	 alert("HEllo");
		if ($("#deviated").is(':checked')) {
		//	$("#scheduleDevationdata").show();
			// plotVehicle();
	//		alert("Bye");
			$('#deviated').prop('checked', true);
			//alert("dff");
			getDeviatedSchedule();
		} else {
	//	alert("hee");
		$("#schdeviation").hide();
		$("#tripstatus").show();
		$("#scheduleprint").show();
		
	//	alert("hnn");
			//clearInterval(intervalID);
		}
	}
 
 function printDiv1() {     
	    
	    //  document.getElementById("print").style.visibility='hidden';     
	    var divElements = document.getElementById("header").innerHTML;
	    divElements += document.getElementById("tripstatus").innerHTML;
	    
	    var noOfTableExist = 0;
	    try{
			$("#tripstatus table").each(function(){
				noOfTableExist++;
			});
			
			console.log("Total no of tables : " + noOfTableExist);
			/* If two table exist  then remove the last table on print click*/
			if(noOfTableExist >= 2){
				divElements = divElements.substring(0, divElements.indexOf("</table>") + 8) + "</div></div>";
			}
		}catch(err){
		    console.log("ExceptionCaught : " + err);
		}

	    var mywindow = window.open("<%=request.getContextPath()%>/Print.jsp");
	   
	    mywindow.onload = function() {
	    mywindow.document.body.innerHTML=divElements;
	    mywindow.document.body.innerHTML=divElements;
	    mywindow.print();
	    mywindow.close();
	    }
	            
	}
 
 
 function printDiv() {     
	 
	 document.getElementById("mapshow").style.visibility='hidden'; 
	$(".mapClass").hide();
	   document.getElementById("tripstatus").style.visibility='visible';  
     document.getElementById("header").style.display='block';
     document.getElementById("header").style.visibility='visible';     
     var divElements = document.getElementById("header").innerHTML;
     divElements+= document.getElementById("tripstatus").innerHTML;
     
    //divElements += document.getElementById("schdeviation").innerHTML;
  
     var mywindow = window.open("<%=request.getContextPath()%>/Print.jsp");
    
     mywindow.onload = function() {
         mywindow.document.body.innerHTML=divElements;
         mywindow.document.body.innerHTML=divElements;
     //	mywindow.document.body.innerHTML = divElement;
         //   document.getElementById("print").style.visibility='';
         mywindow.print();
         mywindow.close();
     }
     document.getElementById("header").style.display = 'none';
		document.getElementById("header").style.visibility = 'hidden';
		document.getElementById("mapshow").style.visibility=''; 
		$(".mapClass").show();
     
             
 }
 var i=0;
 function getDepot(orgId){
	 $('#select2-chosen-2').html("Select");
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 var val=document.getElementById('divisionlist1').value;
			 if(val!=0) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/getDepot?val=' + val,
				success : function(result) {
					document.getElementById('depotlist').innerHTML = result;
				}
			});
		}

	}
	function getSchedule()
	{
		//alert("hiii");
		//console.log("counter"+counter);
		$('#select2-chosen-3').html("Select");
		 var val=document.getElementById('depotlist').value;
		 var selectedDate=document.getElementById("selecteddate").value;
		 if(val!=0) {
			 if(counter==0){
				 counter++;
     $.ajax({
    	 
         type: "POST",
         url: '<%=request.getContextPath()%>/getScheduleno?val=' + val+'&selectedDate='+selectedDate,
			success : function(result) {
				
				document.getElementById('schedulelist').innerHTML = result;
				
				counter=0;
			}
		});
     
    
			 }
	}
	}
	function validateTripStatusReportFields(scheduleNo,depotNo,divisonNo,selDate)
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
		 if(selDate==0)
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
	
	function resetDate(){
		//$("#selecteddate").val()='';
		document.getElementById("selecteddate").value='';
	}
	
	function getScheduledTripStatusDataOnUpdate(){
		//console.log(document.getElementById("vehicleno").value);
		var vehicleno=document.getElementById("vehicleno").value;
		var selecteddate= $("#selecteddate").val();
		var schedule_name= $('#schedulelist option:selected').text();
		var scheduleNo=$("#schedulelist").val();
		var depotNo=$("#depotlist").val();
		var divisonNo=$("#divisionlist1").val();
		var selecteddate=$("#selecteddate").val();
		validateflag=validateTripStatusReportFields(scheduleNo,depotNo,divisonNo,selecteddate);
		if(validateflag==true )
			if(vehicleno ==''){
				bootbox.alert('Please Submit the Schedule First');
				return false;
			}
		{
		$('#loadingmessage').show(); 
		$.ajax({
	           type: "get",
	           url: '<%=request.getContextPath()%>/updateScheduleData?vehicleno='+vehicleno+'&selectedDate='+selecteddate+'&schedule_name='+schedule_name+'&depot_id='+depotNo,
	           success: function(result) {
	        	   $('#loadingmessage').hide(); 
	        	   getScheduledTripStatusDataOnSubmit();
	        	   bootbox.alert('Schedule Updated Successfully');
	        	  // document.getElementById('scheduletype').innerHTML=result; 
	              
	               
	            }
	       }); 
		}
		
	}
	function getScheduledTripStatusDataOnSubmit()
	{
		
		var scheduleNo=$("#schedulelist").val();
		var depotNo=$("#depotlist").val();
		var divisonNo=$("#divisionlist").val();
		var selecteddate=$("#selecteddate").val();
		validateflag=validateTripStatusReportFields(scheduleNo,depotNo,divisonNo,selecteddate);
		if(validateflag==true)
		{
// 		$("#printbutton").show();
// 		$("#scheduleprint").show();
		var scheduleNo=$("#schedulelist").val();
		var depotName=$("#depotlist").val();
// 		alert(selecteddate);
	//alert("HII"+scheduleNo);
// 		document.getElementById("scheduleTripStatus").style.display = '';
// 		$("#schdeviation").hide();
// 		document.getElementById("scheduleTripStatus").style.display = '';
// 		var selectedDate=document.getElementById("selecteddate").value;
		
	
// 		$("#tripstatus").show();
		 $.ajax({
	           type: "get",
	           url: '<%=request.getContextPath()%>/AjaxtripwiseEarlyArrivalReport.action?scheduleNo='+ scheduleNo+'&selectedDate='+selecteddate+'&depotName='+depotName,
	           success: function(result) {
	        	   $('#pageLoader').hide();
	                document.getElementById('tripstatus').innerHTML=result;
	                fixHeader();
	                
	            }
	       }); 
// 		 document.getElementById('selectdate').innerHTML=selectedDate;
// 		 document.getElementById('scheduleno').innerHTML=scheduleNo;
		 
		}
	
	}
	function summury(){
		var schdDist = [];
		var gpsDist=[];
		var vehiceno=[];
		var summarySchdDist=0.00;
		var summaryGpsDist=0.00;
		var vehicleNO="";
		$(".sch_distance").each(function(index, value) {
			schdDist.push($(this).text());

		});
		$(".gps_distance").each(function(index, value) {
			gpsDist.push($(this).text());
		});
		$(".vehicleno").each(function(index, value) {
			//console.log($(this).text());
			vehiceno.push($(this).text());
		});
		for (var i = 0; i < schdDist.length; i++) {
			if (!isNaN((schdDist[i]))) {
				summarySchdDist += parseFloat(schdDist[i]);
			}
		}
		for (var k = 0; k < gpsDist.length; k++) {
			if (!isNaN((gpsDist[k]))) {
				summaryGpsDist += parseFloat(gpsDist[k]);
			}
		}
		
		for (var l = 0; l < vehiceno.length; l++) {
			//if (!isNaN((vehiceno[l]))) {
				//console.log(vehiceno[l]);
				vehicleNO = vehiceno[l];
				//console.log(vehicleNO);
			//}
		}
		//console.log(vehicleNO);
		document.getElementById("vehicleno").value=vehicleNO;
		
		var diff=summarySchdDist - summaryGpsDist;
		if(diff < 0){
			$("#status").html("<B>Extra Distance:</B> "+ Math.abs(diff.toFixed(2)));
		}else{
			$("#status").html("<B>Deviated Distance:</B> "+ diff.toFixed(2));
		}
		$("#summarySchdDistance").html("<B>Schedule Distance:</B> "+summarySchdDist.toFixed(2));
		$("#summaryGpsDistance").html("<B>Gps Distance:</B> "+summaryGpsDist.toFixed(2));
		}
	function getScheduledTripStatusData(scheduleNo) {
		
		$("#printbutton").show();
		$("#scheduleprint").show();
		
	//alert("HII"+scheduleNo);
		document.getElementById("scheduleTripStatus").style.display = '';
		$("#schdeviation").hide();
		document.getElementById("scheduleTripStatus").style.display = '';
		var selectedDate=document.getElementById("selecteddate").value;
		var scheduleName=$("#schedulelist option:selected").text();
		var depotName=$("#depotlist option:selected").text();
// 		alert(depotName);
		$.ajax({
	           type: "get",
	           url: '<%=request.getContextPath()%>/getScheduleHeaderData?scheduleNo='+ scheduleNo+'&selectedDate='+selectedDate,
	           success: function(result) {
	        	   var obj = jQuery.parseJSON(result);
	        	   console.log("==>"+obj["bbData"][0]);
	        	    	   document.getElementById('fromDate').innerHTML=obj["bbData"][0];
	        	   document.getElementById('fromDate').innerHTML=obj["bbData"][1];
	        	   document.getElementById('toDate').innerHTML=obj["bbData"][2];
					 document.getElementById('scheduletype').innerHTML=obj["bbData"][3];
					 document.getElementById('baseRoute').innerHTML=obj["bbData"][4];
	           }
	       });<%-- 
		$('#scheduleTripStatus')
				.dataTable(
						{
							"aLengthMenu" : [ [ 5, 15, 20, -1 ],
									[ 5, 15, 20, "All" ] // change
							],
							// set the initial value
							"iDisplayLength" : 20,
							"sAjaxSource" : "scheduletripstatuslist.action?scheduleNo="+ scheduleNo+"&selectedDate="+selectedDate+"&depotName="+depotName,
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
								'aTargets' : [ 0, 1, 2, 3, 4 ]},
							{ "sClass": "sch_distance", "aTargets": [ 14 ] },
							{ "sClass": "gps_distance", "aTargets": [ 15 ] },
							],
							"fnInitComplete": function(oSettings, json) {
								summury();
								},
							aaSorting:[],
							"bLengthChange" : false,
							"sDom" : '<"top">rt<"bottom"flp><"clear">'
						})
				.wrap(
						"<div style='position:auto;height:300px;width:100%'/>");
		jQuery('#scheduleTripStatus_wrapper .dataTables_filter input').addClass(
				"form-control input-medium input-inline"); // modify table
		// search input
		jQuery('#scheduleTripStatus_wrapper .dataTables_length select').addClass(
				"form-control input-xsmall input-inline");
		$("#tripstatus").show();
		
		 $.ajax({
	           type: "get",
	           url: '<%=request.getContextPath()%>/getScheduleHeaderData?scheduleNo='+ scheduleNo+'&selectedDate='+selectedDate,
	           success: function(result) {
	        	  
	        	   document.getElementById('scheduletype').innerHTML=result; 
	              
	               
	           }
	       }); 
		 document.getElementById('selectdate').innerHTML=selectedDate;
		 document.getElementById('scheduleno').innerHTML=scheduleName; --%>
	}
	function getTripDeviation(selectedDate) {
		
		// getDeviatedScheduleInfo(selectedDate);
			/* if ($("#deviated").is(':checked')) {
			//	$("#scheduleDevationdata").show();
				// plotVehicle();
		//	alert("Bye");
	
				$('#deviated').prop('checked', true);
				getDeviatedSchedule();
				//alert("dff");
				$('#deviated').prop('checked', false);
			} else {
			//alert("hee");
			
			
			} */
		}
function getDeviatedSchedule() {
	$("#scheduleprint").hide();
		$("#tripstatus").hide();
		document.getElementById("scheduleTripStatus").style.display = '';
	//	document.getElementById("scheduleTripStatus").style.display = '';
	document.getElementById("scheduleDevationdata").style.display = '';
		var schedule=$("#schedulelist option:selected").text();
	//	alert("Schedule"+schedule);
		var ScheduleNumber=[];
		ScheduleNumber=schedule.split("(");
		//alert("SCdulArr"+ScheduleNumber);
	var	scheduleNo=ScheduleNumber[0];
		var division=$("#divisionlist").val();
		var depot=$("#depotlist").val();
		//alert("Hiiscc"+scheduleNo+depot+division);
		var selectedDate=document.getElementById("selecteddate").value;
		if(division==0){
			bootbox.alert("Please Select Division");
		}else if(depot==0){
			bootbox.alert("Please Select Depot");
		}else if(scheduleNo=='Schedule'){
			bootbox.alert("Please Select Schedule Number");
		}else{
		$('#scheduleDevationdata')
				.dataTable(
						{
							"aLengthMenu" : [ [ 5, 15, 20, -1 ],
									[ 5, 15, 20, "All" ] // change
							],
							// set the initial value
							"iDisplayLength" : 20,
							"sAjaxSource" : "scheduledevaitionlist.action?scheduleNo="+ scheduleNo+"&selectedDate="+selectedDate,
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
		jQuery('#scheduleDevationdata_wrapper .dataTables_filter input').addClass(
				"form-control input-medium input-inline"); // modify table
		// search input
		jQuery('#scheduleDevationdata_wrapper .dataTables_length select').addClass(
				"form-control input-xsmall input-inline");
		$("#schdeviation").show();
		}
	}

function getDeviatedScheduleInfo(tripno) {
	//document.getElementById("DeviatiedTrip").style.display = '';
	var selectedDate=document.getElementById("selecteddate").value;
//	
	var schedule=$("#schedulelist option:selected").text();
//
	var ScheduleNumber=[];
	ScheduleNumber=schedule.split("(");
	var tripno1=tripno;
	//alert("TRip"+tripno1);
	
	var	scheduleNo=ScheduleNumber[0];
	var division=$("#divisionlist").val();
	var depot=$("#depotlist").val();
	///alert("Hiiscc"+scheduleNo+depot+division);
	$('#DeviatiedTrip').dataTable({
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
        
		"bDestroy" : true,
        "sAjaxSource" : "deviationinfo.action?scheduleNo="+ scheduleNo+"&selectedDate="+selectedDate+"&tripno="+tripno1,
        "sPaginationType": "bootstrap",
        "oLanguage": {
            "sLengthMenu": "_MENU_ records",
            "oPaginate": {
                "sPrevious": "Prev",
                "sNext": "Next"
            }
        },"bFilter" : false,
		"bSelect" : false,
		"bPaginate" : false,
	"oLanguage" : {
				"sZeroRecords" : "",
	"sEmptyTable" : ""
		},aaSorting:[],
			"bLengthChange" : false,
	"sDom" : '<"top">rt<"bottom"flp><"clear">',
       "aoColumnDefs": [
            { 'bSortable': false, 'aTargets': [0,1,2 ]},
            { "bSearchable": false, "aTargets": [ 0 ] }
        ]
        
    }).wrap(
				"<div style='position:auto;overflow:auto;height:300px;width:100%'/>");
   	 	jQuery('#DeviatiedTrip_wrapper .dataTables_filter input').addClass(
    	 			"form-control input-medium input-inline"); // modify table
    	 	// search input
    	 	jQuery('#DeviatiedTrip_wrapper .dataTables_length select').addClass(
  	 			"form-control input-xsmall input-inline");
	}

 </Script>

<title>Insert title here</title>
</head>
<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">
	<div class="page-content-wrapper">
		<div class="page-content">
	<input type="hidden" id='deviceid' name='deviceid' /> 
	<input type="hidden" id='vehicleid' name='vehicleid' /> 
	<input type="hidden" id='scheduleno' name='scheduleno' /> 
	<input type="hidden" id='routeid' name='routeid' /> 
	<input type="hidden" id='schedulename'	name='schedulename' /> 
	<input type="hidden" id='startpoint'	name='startpoint' /> 
	<input type="hidden" id='endpoint'	name='endpoint' /> 
	<input type="hidden" id='starttime'	name='starttime' /> 
	<input type="hidden" id='endtime' name='endtime' />
	<input type="hidden" id='tripstatus1' name='tripstatus1' /> 
	<input	type="hidden" id='dutydate' name='dutydate' /> 
	<input type="hidden" id='id' name='id' />
	
	<input type="hidden" id='schNo' name='schNo' /> 
	<input	type="hidden" id='tripNo' name='tripNo' /> 
	<input type="hidden" id='fromStop' name='fromStop' />
	<input type="hidden" id='endStop' name='endStop' /> 
	<input	type="hidden" id='schDep' name='schDep' /> 
	<input type="hidden" id='gpsDep' name='gpsDep' />
	<input type="hidden" id='schArr' name='schArr' /> 
	<input	type="hidden" id='gpsArr' name='gpsArr' /> 
	<input type="hidden" id='schDistance' name='schDistance' />
	<input type="hidden" id='vehicleno' name='vehicleno' />
			<div class="tab-content">

				<div class="row">
					<div class="col-md-12">
						<!-- BEGIN PAGE TITLE & BREADCRUMB-->
						<h3 class="page-title">
							TRIP WiSE EARLY ARRIVAL REPORT <small></small>
						</h3>

						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>View Trip wise Early Arrival Report
							</div>
							<div class="actions">

						
						<input type='button' class='btn blue' id='button1' onclick='printDiv1()' value='Print' />													
										
						<button type="button" class='btn blue' id="btnExport" filename= "report" onclick="tabletoExcel();"> EXPORT </button>
                               

						</div>
							<!-- <div class="tools">
								<a href="javascript:;" class="collapse"> </a> 
								 <a href="javascript:;" class="reload"> </a> 
							</div> -->
						</div>

						<div class="portlet-body form">

							<div class="col-md-12" align="left" style="font-size: 1.1em">

								<%-- <span class="help-block" style="color: red; list-style: none"><s:actionerror /></span> --%>
								<span class="help-block" style="color: red; list-style: none"><s:actionmessage /></span>
							</div>
							<!-- BEGIN FORM-->
							<form action="" method="post" class="form-horizontal">

								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Division<font
											color="red">*</font></label>
										<div class="col-md-4">
											<s:select list="divisionlist" id="divisionlist1"
												name="orgchart.org_chart_id"
												cssClass="select2_category form-control" headerKey="0" headerValue="---select---"
												 onchange="getDepot(this.value)"></s:select>
										</div>
									</div>
								</div>
								<script>
// 					getDepot("");
					</script>

								<div class="form-group">
									<label class="col-md-3 control-label">Depot<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select id="depotlist" class="select2_category form-control"
											onchange="resetDate()">
											<option value="0">---Select---</option>
										</select>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-md-3">Date<font
										color="red">*</font></label>
									<div class="col-md-3">
										<div class="input-group input-medium date date-picker"
											style="width: auto" data-date-format="dd-mm-yyyy"
											data-date-viewmode="years">
											<input type="text" class="form-control" id="selecteddate" onchange="getSchedule(this.value)"
												readonly name="selecteselecteddateddate" /> <span
												class="input-group-btn">
												<button class="btn default" type="button">
													<i class="fa fa-calendar"></i>
												</button> 
											</span>
												<%-- <script>
							var curr_date = new Date().toJSON().slice(0, 10);
							curr_date = curr_date.split("-");
							curr_date = curr_date[0] + "-" + curr_date[1] + "-"
									+ curr_date[2];
							$('#selecteddate').val(curr_date);
						</script> --%>
										</div>
									</div>
										
								</div>
									
								<div class="form-group">
									<label class="col-md-3 control-label">Schedule Number<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select id="schedulelist" name="scheduleno" 
											class="select2_category form-control">
											<option value="0">---Select---</option>
										</select>
                                     </div>
						  <script>                 	
  	           	 function tabletoExcel(btnExport){     
  	           	var htmltable= document.getElementById('btnExport');   
  	             $( "#header-fixed" ).remove();
  	             var inputHTML = "<table border='1'>";
  	             inputHTML += "<tr>";
  	             inputHTML += "<th  align='left'colspan='9'>Bangalore Metropolitan Transport Corporation</th>";
  	             inputHTML += "</tr>";
  	             inputHTML += "<th  align='left'colspan='9'>TRIP WISE EARLY ARRIVAL REPORT</th>";
  	             inputHTML += "</tr>";
  	             inputHTML += "<tr>";
  	             inputHTML += "<th colspan='2' align='left'>Date:</th>";
  	             inputHTML += "<th colspan='7' align='left'>" + $("#selecteddate").val() + "</th>";
  	             inputHTML += "</tr>";
  	             inputHTML += "</table>";
  	             var htmltable = document.getElementById("tripstatus");
  	             var html = inputHTML + htmltable.outerHTML;
  	             var downloadLink = document.createElement("a");
  	             downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
  	             downloadLink.download = "TRIP WISE EARLY ARRIVAL REPORT.xls";
  	             document.body.appendChild(downloadLink);
  	             downloadLink.click();
  	             document.body.removeChild(downloadLink);
  	         }</script>
						
						
  	              	 
  	         
									<div class="col-md-1" id="">
									<button type="button" class='btn green' onclick='getScheduledTripStatusDataOnSubmit()' style="position: static;">Submit</button>
									</div>
<!-- 									
								
								
					
							<!-- END FORM-->
						</div>
							
					</div>
					
				
				</div>
				<div id="tripstatus"></div>
			</div>
		</div>

	
										

	

</body>
</html>