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
	<style type="text/css">


.btn_blue:hover, .btn_blue:focus, .btn_blue:active, .btn_blue.active {
  color: white;
  background-color: #2e7af7;
}

.Schedule {
 text-align: 'center';
 font-family: 'Times New Roman';
font-size: 15px;
 text-transform :lowercase;
font-weight: bold;
}
.dummy{
    
    color: white;
    height:50px;
    text-shadow: none;
}
.type{
 
 text-align: center;
 font-family: 'Times New Roman';
font-size: 15px;
 
font-weight: bold;

}
</style>


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
		$("#scheduleDeparture").show();
		$("#scheduleprint").show();
		
	//	alert("hnn");
			//clearInterval(intervalID);
		}
	}
 function printDiv() {     
	 
	
	   document.getElementById("scheduleDeparture").style.visibility='visible';  
     document.getElementById("header").style.display='block';
     document.getElementById("header").style.visibility='none';     
     var divElements = document.getElementById("header").innerHTML;
     divElements+= document.getElementById("scheduleDeparture").innerHTML;
     
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
		 if(val!=0) {
     $.ajax({
         type: "POST",
         url: '<%=request.getContextPath()%>/getScheduleALL?val='+val+'&selectedDate='+selectedDate,
			success : function(result) {
				
				document.getElementById('schedulelist').innerHTML = result;
			}
		});
	}
		 var depotids=$("#depotlist").val();
			//alert("depo"+depotids);
	  $.ajax({
		           type: "post",
		           url: '<%=request.getContextPath()%>/getStartPoint?depotid='+ depotids+'&selectedDate='+selectedDate,
		           success: function(result) {
	                      
		        	   
		        	   $("#startpointId").val(result);
		        	  // alert();
		        	  /*  var obj = jQuery.parseJSON(result);
		        	   console.log("==>"+obj["bbData"][0]);
		        	    	   document.getElementById('fromDate').innerHTML=obj["bbData"][0];
		        	   document.getElementById('fromDate').innerHTML=obj["bbData"][1];
		        	   document.getElementById('toDate').innerHTML=obj["bbData"][2];
						 document.getElementById('scheduletype').innerHTML=obj["bbData"][3];
						 document.getElementById('baseRoute').innerHTML=obj["bbData"][4]; */
		           }
		       });  
	}
	function validateTripStatusReportFields(depotNo,divisonNo,fromDate,tillDate)
	{
		  if(divisonNo==0)
		 {
			bootbox.alert("Please Select Divison");
			return false;
		 }
		  else if(depotNo==0)
		 {
			 bootbox.alert("Please Select Depot");
			return false;
		 } 
		 
		  else if(fromDate==0 ||tillDate==0)
	     {
			  bootbox.alert("Please Select From Date and Till Date");
		   return false;
	     }
	      if(daydiff(fromDate, tillDate) !=8)
	     {
	    	  bootbox.alert("Difference between two dates should be equal to 9 days");
		    	 return false;
	    	 
		   
	     }else{
	    	 
	     } 
	     

	 return true;
	}
	
	function daydiff(first, second) {
		
		date1 = first.split('-');
		date2 = second.split('-');
		date1 = new Date(date1[2], date1[1]-1, date1[0]);
		date2 = new Date(date2[2], date2[1]-1, date2[0]);
		date1_unixtime = parseInt(date1.getTime() / 1000);
		date2_unixtime = parseInt(date2.getTime() / 1000);
		var timeDifference = date2_unixtime - date1_unixtime;
		var timeDifferenceInHours = timeDifference / 60 / 60;
		var timeDifferenceInDays = timeDifferenceInHours  / 24;
		console.log("date1"+date1+"\t"+"date2"+date2 +"\t"+timeDifferenceInDays);
	    return timeDifferenceInDays;
	}
	function getScheduledDepartureDataOnSubmit()
	{
		var DepotName=$("#depotlist option:selected").text();
		var depotID=$('#depotlist').val();
		var fromdate=$('#fromdate').val();
		var tilldate=$('#tilldate').val();
		var divisonNo=$("#divisionlist").val();
		validateflag=validateTripStatusReportFields(depotID,divisonNo,fromdate,tilldate);
		if(  validateflag==true){
		$("#printbutton").show();
		$("#scheduleprint").show();
		var scheduleNo=$("#schedulelist").val();
		//document.getElementById("scheduleDepartureData").style.display = '';
		//$("#schdeviation").hide();
		//document.getElementById("scheduleDepartureData").style.display = '';
		$('#scheduleDepartureDaily')
		.dataTable(
				{
					"aLengthMenu" : [ [ 5, 15, 20, -1 ],
							[ 5, 15, 20, "All" ] // change
					],
					// set the initial value
					"iDisplayLength" : 20,
					"sAjaxSource" : "getScheduleDepartureDataDaily.action?fromDate="+fromdate+"&tillDate="+tilldate+"&depotID="+depotID,
					"sPaginationType" : "bootstrap",
					"bDestroy" : true,
					"oLanguage" : {
						"sLengthMenu" : "_MENU_ records",
						"oPaginate" : {
							"sPrevious" : "Prev",
							"sNext" : "Next"
						}
					},
					"bSort" : false,
					"bFilter" : false,
					"bSelect" : false,
					"bPaginate" : false,
					"oLanguage" : {
						"sZeroRecords" : "",
						"sEmptyTable" : ""
					},
					"fnRowCallback" : function(nRow, aaData,
							iDisplayIndex, iDisplayIndexFull) {
						var sDirectionClass;
								 if (aaData[0] == "Sr No.") {
							
							console.log("DOaddtype"+aaData[0]);
							sDirectionClass = "type";
							
							
						}
						$(nRow).addClass(sDirectionClass);
						return nRow;
					}, 
					
					"aoColumnDefs" : [ {
						'bSortable' : false,
						'aTargets' : [ 0, 1, 2, 3, 4,5,6,7,8,9,10,11,12 ]
					} ,{ "sClass": "type","aTargets": [ 0 ] },{
					      "aTargets": [0],
					      
					    }  ],
					aaSorting:[],
					"bLengthChange" : false,
					"sDom" : '<"top">rt<"bottom"flp><"clear">',
					
				})
		.wrap(
				"<div style='position:auto;height:300px;width:100%'/>");
jQuery('#scheduleDepartureDaily_wrapper .dataTables_filter input').addClass(
		"form-control input-medium input-inline"); // modify table
// search input
jQuery('#scheduleDepartureDaily_wrapper .dataTables_length select').addClass(
		"form-control input-xsmall input-inline");
$("#scheduleDeparture").show();
$("#scheduleDepartureDaily").show();


    document.getElementById('selectdate').innerHTML=fromdate;
    document.getElementById('tillDate').innerHTML=tilldate;
	// document.getElementById('scheduleno').innerHTML=scheduleNo;

	 document.getElementById('Depotname').innerHTML=DepotName;
		}
}
	function SetDate(){
	var fromdate=$("#fromDate").val();
	$("#monthDetail").val(fromdate);
	
}
 </Script>

<title>Insert title here</title>
</head>
<body>
	<div class="page-content-wrapper">
		<div class="page-content">
	<input type="hidden" id='deviceid' name='deviceid' /> <input
		type="hidden" id='vehicleid' name='vehicleid' /> <input type="hidden"
		id='scheduleno' name='scheduleno' /> <input type="hidden"
		id='routeid' name='routeid' /> <input type="hidden" id='schedulename'
		name='schedulename' /> <input type="hidden" id='endpoint'
		name='endpoint' /> <input type="hidden" id='starttime'
		name='starttime' /> <input type="hidden" id='endtime' name='endtime' />
	<input type="hidden" id='tripstatus1' name='tripstatus1' /> <input
		type="hidden" id='dutydate' name='dutydate' /> <input type="hidden"
		id='id' name='id' />
		
		 <input type="hidden" id='startpointId' name='startpoint' value="" />
			<div class="tab-content">

				<div class="row">
					<div class="col-md-12">
						<!-- BEGIN PAGE TITLE & BREADCRUMB-->
						<h3 class="page-title">
           SCHEDULEWISE DAILY PUNCTUALITY (IN DEPARTURE) REPORT<small></small>
						</h3>

						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>View  ScheduleWise Daily Punctuality (In Departure) Report
							</div>
						</div>
						<div class="portlet-body form">
							<div class="col-md-12" align="left" style="font-size: 1.1em">
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
									<label class="col-md-3 control-label">
										From Date<font color="red">*</font>
									</label>
									<div class="col-md-3">
										<div class="input-group input-medium date date-picker"
											style="width: auto" data-date-format="dd-mm-yyyy"
											data-date-viewmode="years">
											<input type="text" class="form-control" id="fromdate" 
												readonly name="selecteselecteddateddate" /> <span
												class="input-group-btn">
												<button class="btn default" type="button">
													<i class="fa fa-calendar"></i>
												</button> 
											</span>
											
										</div>
										<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('fromDate').get(0)}" />
										</span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">
										Till Date<font color="red">*</font>
									</label>
									<div class="col-md-3">
										<div class="input-group input-medium date date-picker"
											style="width: auto" data-date-format="dd-mm-yyyy"
											data-date-viewmode="years">
											<input type="text" class="form-control" id="tilldate" 
												readonly name="selecteselecteddateddate" /> <span
												class="input-group-btn">
												<button class="btn default" type="button">
													<i class="fa fa-calendar"></i>
												</button> 
											</span>
											</div>	
												
										</div>
										
										<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('tillDate').get(0)}" />
										</span>
										 <div class="col-md-1" id="">
									<button type="button" class="btn default" onclick="getScheduledDepartureDataOnSubmit()" style="position: static;">Submit</button>
									</div> 
										<div class="col-md-1"id="printbutton">
											<input type='button' class="btn default" id='button1' onclick='printDiv()' value='Print' />													
										</div>	
									</div>
								
								<div class="form-group">
									
									
								</div>
																	  
                         <div id="header" class="portlet-body" style="display: none; visibility: hidden;">
										<center>	<h4>SCHEDULEWISE DAILY PUNCTUALITY (IN ARRIVAL) REPORT</h4><br>
							        	<h4><span id="Depotname" ></span></b></h4>
							         	     	<table ID="arrivalHeader" style="width: 50%; border: none;">
											<tr>
												<td><b><label><font size="2">From Date :</font></label></b> <!-- <td style="text-align: left;"> --> 
												<b><font size="2"><span id="selectdate"
															style="text-align: left; margin-left: 10px;"></span></font></b></td>
												<td><b><label><font size="2">Till Date :</font></label></b> <!-- <td style="text-align: left;"> -->
															<b><font size="2"><span id="tillDate"
															style="text-align: left; margin-left: 10px;"></span></font></b></td>
											</tr>
										</table>
										<br>
							         	</center>
								
                                </div>
								<div class="portlet-body" id="scheduleprint"
									style='height: 600px; overflow-y: scroll; display: block;'>
									<div id="scheduleDeparture">
											<table class="table table-striped table-bordered table-hover" width="100%";
											id="scheduleDepartureDaily" style="display: none" cellpadding="1">
											<thead style="display: none">
												<th style='text-align: center'>sss</th>
												<th style='text-align: center'>DUTY TYPE</th>
												<th style='text-align: center'>opera</th>
												<th style='text-align: center'></th>
												<th style='text-align: center' class="month1">3</th>
												<th style='text-align: center' class="month1"></th>
												<th style='text-align: center' class="month1"></th>
												<th style='text-align: center' class="month1"></th>
												<th style='text-align: center' class="month1"></th>
												<th style='text-align: center' class="month1"></th>
												<th style='text-align: center' class="month1"></th>
												<th style='text-align: center' class="month1"></th>
												<th style='text-align: center' class="month1"></th>

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
	
<script type="text/javascript" src="assets/global/plugins/data-tables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/global/plugins/data-tables/tabletools/js/dataTables.tableTools.min.js"></script>
</body>
</html>