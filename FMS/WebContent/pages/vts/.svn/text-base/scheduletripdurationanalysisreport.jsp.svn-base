<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
<script src="https://code.jquery.com/jquery-1.11.3.min.js" type="text/javascript"></script>
	<script src="https://cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/responsive/1.0.7/js/dataTables.responsive.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/fixedheader/3.0.0/js/dataTables.fixedHeader.min.js" type="text/javascript"></script>
<%-- <script src="assets/vts/js/scheduleroute.js"></script> --%>
<%-- <script src="assets/vts/js/vehiclealert.js"></script> --%>
<%--  <script src="assets/global/plugins/jquery-1.11.0.min.js" --%>
<%-- 	type="text/javascript"></script> --%>
	
<%-- <script src="assets/vts/js/scheduledeviation.js" --%>
<%-- 	type="text/javascript"></script> --%>
<%-- 	<script src="assets/vts/js/vts.js" type="text/javascript"></script> --%>
	
<%-- <script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places,drawing,geometry"></script>
 --%>
<%--  <script> --%>

<%--  </script> --%>
 
 
<Script>

var counter=0;
 $(document).ready(function(){
 	 
	$("#printbutton").hide();
	$("#scheduleprint").hide();
$("#schdeviation").hide();
$("#scheduleprint").hide();
	 
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
	  document.getElementById("tripstatus").style.visibility='visible';  
     document.getElementById("header").style.display='block';
     document.getElementById("header").style.visibility='visible'; 
    
     
     var divElements = document.getElementById("header").innerHTML;
     divElements+= document.getElementById("tripstatus").innerHTML;
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
		//console.log("counter"+counter);
		$('#select2-chosen-3').html("Select");
		 var val=document.getElementById('depotlist').value;
		// var selectedDate=document.getElementById("selecteddate").value;
		 if(val!=0) {
			 if(counter==0){
				 counter++;
     $.ajax({
    	 
         type: "POST",
         url: '<%=request.getContextPath()%>/getScheduleDuration?val=' + val,
			success : function(result) {
				
				document.getElementById('schedulelist').innerHTML = result;
				counter=0;
			}
		});
			 }
	}
	}
	function validateTripStatusReportFields(scheduleNo,depotNo,divisonNo,selectedstartdate,selectedenddate)
	{
		
// 		var fromDateEntered = selectedstartdate;
// 		var toDateEntered = selectedenddate;
		
// 		if(fromDateEntered == null || fromDateEntered == "" || toDateEntered == null || toDateEntered == ""){
// 			alert("Invalid Date...");
// 			return false;
// 		}
// 		else{
// 			try{
// 				var curr = new Date;
				
// 				var firstday = new Date(curr.setDate(curr.getDate() - curr.getDay()));
// 				var lastday = new Date(curr.setDate(curr.getDate() - curr.getDay() + 7));
				
// 				//get date in ISO format
// 				var fromDay = fromDateEntered.substring(0, fromDateEntered.indexOf("-"));
// 				var fromMonth = fromDateEntered.substring(fromDateEntered.indexOf("-") + 1, fromDateEntered.lastIndexOf("-"));
// 				var fromYear = fromDateEntered.substring(fromDateEntered.lastIndexOf("-") + 1, fromDateEntered.length);
// 				//date in yyyy-mm-dd format
// 				var formattedFromDate = fromYear + "-" + fromMonth + "-" + fromDay;			
// 				var fromDateInDateFormat = new Date(formattedFromDate);
				
// 				var toDay = toDateEntered.substring(0, toDateEntered.indexOf("-"));
// 				var toMonth = toDateEntered.substring(toDateEntered.indexOf("-") + 1, toDateEntered.lastIndexOf("-"));
// 				var toYear = toDateEntered.substring(toDateEntered.lastIndexOf("-") + 1, toDateEntered.length);
// 				//date in yyyy-mm-dd format
// 				var formattedToDate = toYear + "-" + toMonth + "-" + toDay;			
// 				var toDateInDateFormat = new Date(formattedToDate);
				
// 				var isInvalidDate = false;
// 				if(fromDateInDateFormat.getTime() < firstday.getTime() || fromDateInDateFormat.getTime() > lastday.getTime()){
// 					isInvalidDate = true
// 				}
// 				if(toDateInDateFormat.getTime() < firstday.getTime() || toDateInDateFormat.getTime() > lastday.getTime()){
// 					isInvalidDate = true;
// 				}
				
// 				if(isInvalidDate){
// 					alert("Invalid Date Entered");
// 					return false;
// 				}
// 			}
// 			catch(err){
// 				alert("Invalid Date...");
// 				return false;
// 			}
// 		}			
	
		
		
		
		
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
// 		 if(selDate==0)
// 	     {
// 		   alert("Please Select Date");
// 		   return false;
// 	     }
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
		var selectedstartdate=$("#selectedstartdate").val();
		var selectedenddate=$("#selectedenddate").val();
		
		validateflag=validateTripStatusReportFields(scheduleNo,depotNo,divisonNo,selectedstartdate,selectedenddate);
		if(validateflag==true)
		{
		$("#printbutton").show();
		$("#scheduleprint").show();
		var scheduleNo=$("#schedulelist").val();
		var depotName=$("#depotlist").val();
// 		alert(depotName);
	//alert("HII"+scheduleNo);
		document.getElementById("scheduleTripStatusduration").style.display = '';
	//	$("#schdeviation").hide();
		document.getElementById("scheduleTripStatusduration").style.display = '';
		var selectedstartdate=document.getElementById("selectedstartdate").value;
		var selectedenddate=document.getElementById("selectedenddate").value;
		//alert(selectedstartdate);
		 document.getElementById('fromDate').innerHTML=selectedstartdate;
	        	   document.getElementById('toDate').innerHTML=selectedenddate;
					 document.getElementById('schedulenoheader').innerHTML=$("#schedulelist option:selected").text();
		

		$('#scheduleTripStatusduration').dataTable({
			"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
			// per
			// page
			// values
			// here
			],
			// set the initial value
			//"iDisplayLength" : 5,
			"sAjaxSource" : "scheduletripdurationanalysislist.action?scheduleNo="+ scheduleNo+"&selectedstartdate="+selectedstartdate+"&selectedenddate="+selectedenddate+"&depotName="+depotName,
			"sPaginationType" : "bootstrap",
			"bDestroy" : true,
			"bSort" : false,
			"bFilter" : false,
			"bSelect" : false,
			"bPaginate" : false,
			"bInfo": false,

			"oLanguage" : {
				"sLengthMenu" : "_MENU_ records",
				"oPaginate" : {
					"sPrevious" : "Prev",
					"sNext" : "Next"
				}
			},
			
			"aoColumnDefs" : [ {
				'bSortable' : false,
				'aTargets' : [ 0 ]},
			{ "sClass": "sch_distance", "aTargets": [ 5 ] },
			{ "sClass": "gps_distance", "aTargets": [ 6 ] },
			 ],
			 "fnInitComplete": function(oSettings, json) {
					summury();
					},
		});
		jQuery(
				'#scheduleTripStatusduration_wrapper .dataTables_filter input')
				.addClass("form-control input-small input-inline"); // modify
		// table
		jQuery(
				'#scheduleTripStatusduration_wrapper .dataTables_length select')
				.addClass("form-control input-xsmall input-inline"); // modify
		
		
		}
	
	}
	
	function summury(){
		var schdDist = [];
		var gpsDist=[];
		var summarySchdDist=0.00;
		var summarySchdDistAVG=0.00;
		var summaryGpsDist=0.00;
		var summaryGpsDistAVG=0.00;
		$(".sch_distance").each(function(index, value) {
			schdDist.push($(this).text());

		});
		$(".gps_distance").each(function(index, value) {
			gpsDist.push($(this).text());
		});
		for (var i = 0; i < schdDist.length; i++) {
			if (!isNaN((schdDist[i]))) {
				console.log(schdDist.length);
				summarySchdDist += parseFloat(schdDist[i]);
				
			}
		}
		summarySchdDistAVG = summarySchdDist/(schdDist.length)-1;
		
		for (var k = 0; k < gpsDist.length; k++) {
			if (!isNaN((gpsDist[k]))) {
				summaryGpsDist += parseFloat(gpsDist[k]);
				
			}
		}
		summaryGpsDistAVG =summaryGpsDist/(gpsDist.length)-1;
		$("#summarySchdDistance").html("<B>Avg. Of Schedule Hours:</B>"+summarySchdDistAVG.toFixed(2));
		$("#summaryGpsDistance").html("<B>Avg. Of Actual Hours:</B>"+summaryGpsDistAVG.toFixed(2));
		$("#Schedule_no").html("<B>Schedule No:</B> "+$("#schedulelist option:selected").text());
		}
	
	function getScheduledTripStatusData(scheduleNo) {
		
		$("#printbutton").show();
		$("#scheduleprint").show();
		document.getElementById("scheduleTripStatus").style.display = '';
		$("#schdeviation").hide();
		document.getElementById("scheduleTripStatus").style.display = '';
		var selectedDate=document.getElementById("selecteddate").value;
		var scheduleName=$("#schedulelist option:selected").text();
		var depotName=$("#depotlist option:selected").text();
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
	       });
	}
	function getTripDeviation(selectedDate) {
		
		
		}

function getDeviatedScheduleInfo(tripno) {
	var selectedDate=document.getElementById("selecteddate").value;
	var schedule=$("#schedulelist option:selected").text();
	var ScheduleNumber=[];
	ScheduleNumber=schedule.split("(");
	var tripno1=tripno;
	var	scheduleNo=ScheduleNumber[0];
	var division=$("#divisionlist").val();
	var depot=$("#depotlist").val();
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

 </Script>

<title>Insert title here</title>
</head>
<body>
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
			<div class="tab-content">

				<div class="row">
					<div class="col-md-12">
						<!-- BEGIN PAGE TITLE & BREADCRUMB-->
						<h3 class="page-title">
							SCHEDULE TRIP DURATION ANALYSIS REPORT <small></small>
						</h3>

						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>View Schedule Trip Duration Analysis Report
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
												cssClass="select2_category form-control"
												 onchange="getDepot(this.value)"></s:select>
										</div>
									</div>
								</div>
								<script>
					getDepot("");
					</script>

								<div class="form-group">
									<label class="col-md-3 control-label">Depot<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select id="depotlist" class="select2_category form-control"
											onchange="getSchedule(this.value)">
											<option value="0">Depot</option>
										</select>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-3 control-label">Schedule Number<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select id="schedulelist" name="scheduleno" 
											class="select2_category form-control"
											>
											<option value="0">Schedule</option>
										</select>

									</div>
									
								</div>

								<div class="form-group">
									<label class="control-label col-md-3">From Date<font
										color="red">*</font></label>
									<div class="col-md-3">
										<div class="input-group input-medium date date-picker"
											style="width: auto" data-date-format="dd-mm-yyyy"
											data-date-viewmode="years">
											<input type="text" class="form-control" id="selectedstartdate" 
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
									<label class="control-label col-md-3">To Date<font
										color="red">*</font></label>
									<div class="col-md-3">
										<div class="input-group input-medium date date-picker"
											style="width: auto" data-date-format="dd-mm-yyyy"
											data-date-viewmode="years">
											<input type="text" class="form-control" id="selectedenddate" 
												readonly name="selecteselectedstartdate" /> <span
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
									
					
	<script>                 	
  	 function tabletoExcel(btnExport){     
  	var htmltable= document.getElementById('btnExport');   
    $( "#header-fixed" ).remove();
    var inputHTML = "<table border='1'>";
    inputHTML += "<tr>";
    inputHTML += "<th  align='left'colspan='9'>Bangalore Metropolitan Transport Corporation</th>";
    inputHTML += "</tr>";
    inputHTML += "<th  align='left'colspan='9'>SCHEDULE TRIP DURATION ANALYSIS REPORT</th>";
    inputHTML += "</tr>";
    inputHTML += "<tr>";
    inputHTML += "<th colspan='2' align='left'>Date Range:</th>";
    inputHTML += "<th colspan='7' align='left'>" + $("#selectedstartdate").val() + " to " + $("#selectedenddate").val() + "</th>";
    inputHTML += "</tr>";
    inputHTML += "</table>";
    var htmltable = document.getElementById("tripstatus");
    var html = inputHTML + htmltable.outerHTML;
    var downloadLink = document.createElement("a");
    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
    downloadLink.download = "SCHEDULE TRIP DURATION ANALYSIS REPORT.xls";
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
}</script>
										<div class="col-md-1" id="">
									<button type="button" class="btn default" onclick="getScheduledTripStatusDataOnSubmit()" style="position: static;">Submit</button>
									</div>
										<div class="col-md-1"id="printbutton">
											<input type='button' class="btn default" id='button1' onclick='printDiv()' value='Print' />													
										</div>
										<div class="col-md-1"id="printbutton">
											<input type="reset"  class="btn default" value="Reset" onClick="window.location.reload()">												
										</div>
								
								<div class="col-md-1" id="">
								 <button type="button" class="btn default" id="btnExport" filename= "report" onclick="tabletoExcel();"> EXPORT </button>
                                </div>
				                </div>
								
								  
                         <div id="header" class="portlet-body" style="display: none; visibility: hidden;">
										<h4 style="margin-left:350px;">Schedule Tripwise GPS details</h4>
									<br />
									<div id="headerdetails" style="margin-left:0px;">
                            	        <table ID="mytable" style="width:60%;border:none;">
														<tr>
									 						<td ><b><label><font size="2">Schedule No :</font></label></b>
															<!-- <td style="text-align: left;"> -->
			 												<b><font size="2"><span  id="schedulenoheader" style="text-align: left;margin-left:10px;"></span></font></b></td>
														</tr>
														<tr>
															
															<td style="text-align: left;"><b><font size="2"><label>From Date:</label></font><font size="2"><b><span id="fromDate" style="text-align:left;margin-left:50px;"></span></font></b></td>
															<td><font size="2"><label><s:property
																		value="" /> </label></font></td>
																		<td nowrap style="text-align: left;"><b><font size="2"><label>To Date:</label></font><font size="2"><b><span id="toDate" style="text-align:left;margin-left:50px;"></span></font></b></td>
															<td nowrap></td>
														</tr>
														
													</table>
													 <br/><br/> 
                                    
                                </div>
                                </div>
<!-- 								<div class="portlet-body" id="scheduleprint" style='height:310px; overflow-y:scroll; display:block;'> -->
								<div class="portlet-body" id="scheduleprint" style=' display:block;'>
								<div id="tripstatus">
									<table class="table table-striped table-bordered table-hover"
										id="scheduleTripStatusduration" style="display: none">
										<thead >
											<tr>
												<th>Sr. NO</th>
												<th>Schedule Name</th>
												<th>Trip NO</th>
												<th>Start Time</th>
												<th>End Time</th>
												<th>Avg Of Schedule</th>
												<th>Actual Time</th>
											</tr>
										</thead>
									</table>
							
								
								
								<table class="table table-striped table-bordered table-hover"  style="width: 76%">
								<thead>
									<tr>
									<td   width="66%" id="Schedule_no"></td>
									<td   width="15%" id="summarySchdDistance" align="right"></td>
									<td   width="15%" id="summaryGpsDistance" align="right"></td>
									</tr>
								</thead>
							</table>
							</div>
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
													<th>BusStopName</th>
													<th>Entry Time</th>
													<th>Exit Time</th>
													<th>Status</th>
													<th>Accumulated Distance</th>
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
</div>

</body>
</html>