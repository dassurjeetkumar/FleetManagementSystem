<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//www.google.com/jsapi"></script>

<%-- <script src="assets/vts/js/scheduleroute.js"></script> --%>
<script src="assets/vts/js/vehiclealert.js"></script>
 <script src="assets/global/plugins/jquery-1.11.0.min.js"
	type="text/javascript"></script>
<script src="assets/vts/js/scheduledeviation.js"
	type="text/javascript"></script>
<%-- <script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places,drawing,geometry"></script>
 --%>
<Script>
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
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 var val=document.getElementById('divisionlist').value;
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
		 var val=document.getElementById('depotlist').value;
		 var selectedDate=document.getElementById("selecteddate").value;
		 var splitresult = selectedDate.split("-");
		 var resultDate = splitresult[2]+'-'+splitresult[1]+'-'+splitresult[0];
		 alert(resultDate);
		 if(val!=0) {
     $.ajax({
         type: "POST",
         url: '<%=request.getContextPath()%>/getSchedule?val=' + val+'&selectedDate='+resultDate,
			success : function(result) {
				
				document.getElementById('schedulelist').innerHTML = result;
			}
		});
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
	function getScheduledTripStatusDataOnSubmit()
	{
		var scheduleNo=$("#schedulelist").val();
		var depotNo=$("#depotlist").val();
		var divisonNo=$("#divisionlist").val();
		var selecteddate=$("#selecteddate").val();
		validateflag=validateTripStatusReportFields(scheduleNo,depotNo,divisonNo,selecteddate);
		if(validateflag==true)
		{
		$("#printbutton").show();
		$("#scheduleprint").show();
		var scheduleNo=$("#schedulelist").val();
	//alert("HII"+scheduleNo);
		document.getElementById("scheduleTripStatus").style.display = '';
		$("#schdeviation").hide();
		document.getElementById("scheduleTripStatus").style.display = '';
		var selectedDate=document.getElementById("selecteddate").value;
		
		$('#scheduleTripStatus')
				.dataTable(
						{
							"aLengthMenu" : [ [ 5, 15, 20, -1 ],
									[ 5, 15, 20, "All" ] // change
							],
							// set the initial value
							"iDisplayLength" : 20,
							"sAjaxSource" : "scheduletripstatuslist.action?scheduleNo="+ scheduleNo+"&selectedDate="+selectedDate,
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
		 document.getElementById('scheduleno').innerHTML=scheduleNo;
		}
	
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
		<%-- $.ajax({
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
	       }); --%>
		$('#scheduleTripStatus')
				.dataTable(
						{
							"aLengthMenu" : [ [ 5, 15, 20, -1 ],
									[ 5, 15, 20, "All" ] // change
							],
							// set the initial value
							"iDisplayLength" : 20,
							"sAjaxSource" : "showVehicles.action?scheduleno="+ scheduleNo+"&selectedDate="+selectedDate,
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
								'aTargets' : [ 3,4 ]
							} ],
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
		<%--  $.ajax({
	           type: "get",
	           url: '<%=request.getContextPath()%>/getScheduleHeaderData?scheduleNo='+ scheduleNo+'&selectedDate='+selectedDate,
	           success: function(result) {
	        	  
	        	   document.getElementById('scheduletype').innerHTML=result; 
	              
	               
	           }
	       });  --%>
		 document.getElementById('selectdate').innerHTML=selectedDate;
		 document.getElementById('scheduleno').innerHTML=scheduleName;
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
 
 <script>
 function changeVehicle(cnt,dutytype,oldvehicleno){
	
	 var id = 'vehicles'+cnt;
	 var vehicleno = document.getElementById(id).value;
	 var schNo= document.getElementById('schedulelist').value;
	 var selectedDate = document.getElementById('selecteddate').value;
	 
	 if(vehicleno != '0'){
	 bootbox.confirm("Are You Sure You Want To Change Vehicle?",
				function(result) {
		 if(result == true){
	 
	 $.ajax({
         type: "post",
         url: '<%=request.getContextPath()%>/updateVehicle?vehicleNo='+vehicleno+'&scheduleNo='+schNo+'&dutytype='+dutytype+'&oldVehicleNo='+oldvehicleno+'&selecteddate='+selectedDate,
         success: function(result) {
        	 getScheduledTripStatusData(schNo);
         }
     });
		 }
	 });
	 }
	 else{
		 alert('Please Select Vehicle Number');
	 }
 }
 </script>

<title>Insert title here</title>
</head>
<body>
	<div class="page-content-wrapper">
		<div class="page-content">
	<input type="hidden" id='deviceid' name='deviceid' /> <input
		type="hidden" id='vehicleid' name='vehicleid' /> <input type="hidden"
		id='scheduleno' name='scheduleno' /> <input type="hidden"
		id='routeid' name='routeid' /> <input type="hidden" id='schedulename'
		name='schedulename' /> <input type="hidden" id='startpoint'
		name='startpoint' /> <input type="hidden" id='endpoint'
		name='endpoint' /> <input type="hidden" id='starttime'
		name='starttime' /> <input type="hidden" id='endtime' name='endtime' />
	<input type="hidden" id='tripstatus1' name='tripstatus1' /> <input
		type="hidden" id='dutydate' name='dutydate' /> <input type="hidden"
		id='id' name='id' />
			<div class="tab-content">

				<div class="row">
					<div class="col-md-12">
						<!-- BEGIN PAGE TITLE & BREADCRUMB-->
						<h3 class="page-title">
							CHANGE VEHICLE NUMBER <small></small>
						</h3>

						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Change Vehicle Number
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
											<s:select list="divisionlist" id="divisionlist"
												name="orgchart.org_chart_id"
												cssClass="select2_category form-control" headerKey="0"
												headerValue="Division" onchange="getDepot(this.value)"></s:select>
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-3 control-label">Depot<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select id="depotlist" class="select2_category form-control"
											>
											<option value="0">Depot</option>
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
											class="select2_category form-control"
											onchange="getScheduledTripStatusData(this.value)">
											<option value="0">Schedule</option>
										</select>

									</div>
									<!-- <div class="col-md-1" id="">
									<button type="button" class="btn default" onclick="getScheduledTripStatusDataOnSubmit()" style="position: static;">Submit</button>
									</div>
										<div class="col-md-1"id="printbutton">
											<input type='button' class="btn default" id='button1' onclick='printDiv()' value='Print' />													
										</div> -->
								</div>
				 
								<!-- <div class="form-group">

									<label class="col-md-3 control-label">Show Only
										Deviated Trip<font color="red"></font>
									</label>
									<div class="col-md-4">

										<input type="checkbox" id="deviated" onclick="getRefresh();">
									</div>
								</div> -->
								  
                         <div id="header" class="portlet-body" style="display: none; visibility: hidden;">
										<h4 style="margin-left:350px;">Schedule Tripwise GPS details</h4>
									<br />
									<div id="headerdetails" style="margin-left:0px;">
                            	        <table ID="mytable" style="width:60%;border:none;">
														<tr>
									 						<td ><b><label><font size="2">Schedule No :</font></label></b>
															<!-- <td style="text-align: left;"> -->
			 												<b><font size="2"><span  id="scheduleno" style="text-align: left;margin-left:10px;"></span></font></b></td>
								
				
						 							
															<td ><b><font size="2"><label> Schedule Type: </label></font></b>
															<!-- <td style="text-align: left;"> -->
																		 <b><font size="2"><span id="scheduletype" style="text-align:left;margin-left:10px;"></span></font></b></td>
														</tr>
														<tr>
															<td><font size="2"><label>Date:</label></font>
															<!-- <td nowrap style="text-align: left;"> --><font size="2"><b><span id="selectdate" style="text-align:left;margin-left:50px;"></span></font></b></td>
															
															<td style="text-align: left;"><b><font size="2"><label>From:</label></font><font size="2"><b><span id="fromDate" style="text-align:left;margin-left:50px;"></span></font></b></td>
															<td><font size="2"><label><s:property
																		value="" /> </label></font></td>
																		<td nowrap style="text-align: left;"><b><font size="2"><label>To:</label></font><font size="2"><b><span id="toDate" style="text-align:left;margin-left:50px;"></span></font></b></td>
															<td nowrap></td>
														</tr>
														<tr>
															
															<td nowrap><b><font size="2"><label>Base Route: </label></font><font size="2"><b><span id="baseRoute" style="text-align:left;margin-left:50px;"></span></font></b></td>
															<td nowrap></td>
														</tr>
														
														
														
														
														
													</table>
													 <br/><br/> 
                                    
                                </div>
                                </div>
								<div class="portlet-body" id="scheduleprint" style='height:310px; overflow-y:scroll; display:block;'>
								<div id="tripstatus">
									<table class="table table-striped table-bordered table-hover"
										id="scheduleTripStatus" style="display: none">
										<thead>
											<tr>
												<th>#</th>
												<th>Duty Type</th>
												<th>Vechicle NO</th>
												<th>Change vehicle</th>
												<th></th>
												
												<!-- <th>From Stop</th>
												<th>To Stop</th>
												<th>Sch. Departure</th>
												<th>GPS Dep. Time</th>
												<th>Dep. Status</th>
												<th>Sch. Arrival Time</th>
												<th>GPS Arrival Time</th>
												<th>Sch. Trip Duration</th>
												<th>Arr. Status</th>
												<th>Gps. Trip Duration</th>
												<th>Sch. Distance</th>
												<th>Sch. Distance(In KM)</th>
												<th>GPS Distance(In KM)</th>
												<th>Trip Status</th>
												<th id='mapshow'>Show On Map</th> -->
											</tr>
										</thead>
									</table>
								</div>
								</div>
								<div id="schdeviation" class="portlet-body">

									<table class="table table-striped table-bordered table-hover"
										id="scheduleDevationdata" style="display: none">
										<thead>
											<tr>
												<th>#</th>
												<th>Trip NO</th>
												<th>Duty Type</th>
												<th>From Stop</th>
												<th>To Stop</th>
												<th>Sch. Departure</th>
												<th>Act. Departure</th>
												<th>Dept. Status</th>
												<th>Sch. Arrival</th>
												<th>GPS. Arrival</th>
												<th>Arrival Status</th>
												<th>GPS. Trip Duration</th>
												<th>Schedule Distance</th>
												<th>GPS Distance</th>
												<th>Trip Status</th>
												<!-- 	                                         <th>Deviated Trip</th> -->

											</tr>
										</thead>
									</table>
								</div>

							</form>
							<!-- END FORM-->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div style="display: none;" id="mymodal123" class="modal fade"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 800px; height: 500px;"
				align="justify">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 id="vehicleno123" class="modal-title"></h4>
				</div>
				<div>
					<p>
					<div class="portlet box white ">
						<div>
							<input type="hidden" name="requestType" id="requestType" />
							<div>
								<div class="portlet solid white">
									<div class="form-group">
										<label class="col-md-3 control-label"></label>
									</div><div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Schedule Trip Bus Stop Detail
							</div>
							<!-- <div class="tools">
								<a href="javascript:;" class="collapse"> </a> 
								 <a href="javascript:;" class="reload"> </a> 
							</div> -->
						</div>
									<div class="portlet-body" id="withoutbusstop">
										<table class="table table-striped table-bordered table-hover"
											id="tripReportDetails123">
											<thead>
												<tr>
													<th>#</th>
													<th>BusStopName:</th>
													<th>Entry Time</th>
													<th>Exit Time</th>
													<th>Status:</th>
												</tr>
											</thead>
										</table>


									
										</div>
										</div>
										</div>
										</div>
										</div>
										</div>
										</div>
										</div>
										</div>

										</div>
										</div>
										

	<div style="display: none;" id="deviationinfo1" class="modal fade"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 800px; height: 500px;"
				align="justify">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 id="vehicleno123" class="modal-title"></h4>
				</div>
				<div>
					<p>
					<div class="portlet box white ">
						<div>
							<input type="hidden" name="requestType" id="requestType" />
							<div>
								<div class="portlet solid white">
									<div class="form-group">
										<label class="col-md-3 control-label"></label>
									</div>
										<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Schedule Trip Deviation Detail
							</div>
							<!-- <div class="tools">
								<a href="javascript:;" class="collapse"> </a> 
								 <a href="javascript:;" class="reload"> </a> 
							</div> -->
						</div>
				
									<div class="portlet-body" id="withoutbusstop">
										

										<table class="table table-striped table-bordered table-hover"
											id="DeviatiedTrip">
											<thead>
												<tr>
													<th>#</th>

													<th>Deviated Distance</th>

													<th>Route Exit Location</th>

													<th>Route Entry Location</th>
												</tr>
											</thead>
										</table>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>