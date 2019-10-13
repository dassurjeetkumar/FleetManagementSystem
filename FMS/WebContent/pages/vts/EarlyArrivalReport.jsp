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
	 
	
	   document.getElementById("tripstatus").style.visibility='visible';  
     document.getElementById("header").style.display='block';
     document.getElementById("header").style.visibility='none';     
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
     
             
 }
 var i=0;
 function getDepot(orgId){
		
		 /* var selectedValue = $('#form-control').val(); */
		 var val=document.getElementById('divisionlist').value;
			 if(val!=0) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/getDepot?val=' + val,
				success : function(result) {
					if(val==-2){
						$("#select2-chosen-2").html(" ");
						$("#depotlist").html("<option value='"+-1+"' selected>ALL</option>");
					}else{
						$("#select2-chosen-2").html(" ");
					document.getElementById('depotlist').innerHTML = result;
					}
				}
			});
		}

	}
	function getSchedule()
	{
		//alert("hiii");
		 var val=document.getElementById('depotlist').value;
		 var selectedDate=document.getElementById("selecteddate").value;
		 <%-- if(val!=0 && val==42) {
     $.ajax({
         type: "POST",
         url: '<%=request.getContextPath()%>/getScheduleALL?val=' + val+'&selectedDate='+selectedDate,
			success : function(result) {
				
				document.getElementById('schedulelist').innerHTML = result;
			}
		});
	} --%>
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
		var depotName=$("#depotlist option:selected").text();
		var divisonName=$("#divisionlist option:selected").text();
		var selecteddate=$("#selecteddate").val();
		var dummyDate=selecteddate.split("-");
		var range=$("#range").val();
		var choosenDate=dummyDate[2]+"-"+dummyDate[1]+"-"+dummyDate[0];
		var selectedDepot="";
		var selectedDivision="";
		if(depotNo==-1){
			depotNo=depotNo;
			selectedDepot='All/Selected';
			selectedDivision='All/Selected';
		}else{
			depotNo=divisonName+"@"+depotName+"@"+depotNo;
			selectedDepot=depotName;
			selectedDivision=divisonName;
		}
  
		validateflag=validateTripStatusReportFields(scheduleNo,depotNo,divisonNo,selecteddate,range);
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
		
		 var busStopId=$('#startpointId').val(); 
	      // alert("bus"+busStopId); 
	       
		$('#scheduleTripStatus')
				.dataTable(
						{
							"aLengthMenu" : [ [ 5, 15, 20, -1 ],
									[ 5, 15, 20, "All" ] // change
							],
							// set the initial value
							"iDisplayLength" : 20,
							"sAjaxSource" : "getEarlyArrivalReportData.action?scheduleNo="+depotNo+"&selectedDate="+choosenDate+"&range="+range,
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
								"sEmptyTable" : "",
							},
							"fnRowCallback" : function(nRow, aaData,
									iDisplayIndex, iDisplayIndexFull) {
								var sDirectionClass;
								if (aaData[0] == "Grand Total"){
										sDirectionClass = "Total";
										console.log("Grand t"+aaData[0]);
								} else if (aaData[0] == "Division  Total") {
									
											console.log("Dive"+aaData[0]);
									sDirectionClass = "Total";
								}
								else if (aaData[1] == "blank") {
									$('td:eq(0)', nRow).attr('colspan',8);
									
									
			                        for(var i =1; i<=9;i++){
			                        $('td:eq('+i+')', nRow).attr('colspan',0);
			                        $('td:eq('+i+')', nRow).hide();
			                        }
									console.log("Daoo"+aaData[0]);
									sDirectionClass = "success";
								}
								
								else if (aaData[0] == "Day Out - Shift 2") {
									$('td:eq(0)', nRow).attr('colspan',19);
									
									
			                        for(var i =1; i<=19;i++){
			                        $('td:eq('+i+')', nRow).attr('colspan',0);
			                        $('td:eq('+i+')', nRow).hide();
			                        }
									console.log("DOshif2"+aaData[0]);
									sDirectionClass = "success";
								}
								/* else {
									sDirectionClass = "outbound";
								} */
								$(nRow).addClass(sDirectionClass);
								return nRow;
							},
							
							"aoColumnDefs" : [ {
								'bSortable' : false,
								'aTargets' : [ 0, 1, 2, 3, 4 ]
							} ,{ "sClass": "type","aTargets": [ 0 ] },{
							      "aTargets": [0],
							      "fnCreatedCell": function (nTd, aaData, oData, iRow, iCol) {
							        if ( aaData == "dd" ) {
							          $(nTd).css('color', 'red');
							          $(nTd).css('text-align', 'center');
							           $(nTd).css('font-family' ,'Times New Roman');
							           $(nTd).css('font-size' ,' 20px');
							           //$(nTd).css('text-transform' ,'uppercase');
							           $(nTd).css('font-weight' ,' bold');
							          
							        }else if ( aaData == "Divisoin  Total" ) {
								          $(nTd).css('color', 'red');
								          $(nTd).css('text-align', 'center');
								           $(nTd).css('font-family' ,'Times New Roman');
								           $(nTd).css('font-size' ,' 20px');
								          // $(nTd).css('text-transform' ,'uppercase');
								           $(nTd).css('font-weight' ,' bold');
								           
								           
								      
								        }else if ( aaData == "Day Out -Shift 1" ) {
									          $(nTd).css('color', 'red');
									          $(nTd).css('text-align', 'center');
									           $(nTd).css('font-family' ,'Times New Roman');
									           $(nTd).css('font-size' ,' 20px');
									           $(nTd).css('text-transform' ,'uppercase');
									           $(nTd).css('font-weight' ,' bold');
									      
									        }
								        else if ( aaData == "Day Out - Shift 2" ) {
									          $(nTd).css('color', 'red');
									          $(nTd).css('text-align', 'center');
									           $(nTd).css('font-family' ,'Times New Roman');
									           $(nTd).css('font-size' ,' 20px');
									           $(nTd).css('text-transform' ,'uppercase');
									           $(nTd).css('font-weight' ,' bold');
									      
									        }
							        
							      }
							    }  ],
							aaSorting:[],
							"bLengthChange" : false,
							"sDom" : '<"top">rt<"bottom"flp><"clear">',
							"oTableTools": {
				                "sSwfPath": "../../assets/global/plugins/data-tables/tabletools/swf/copy_csv_xls_pdf.swf",
				                "aButtons": [{
				                    "sExtends": "pdf",
				                    "sButtonText": "PDF"
				                }, {
				                    "sExtends": "csv",
				                    "sButtonText": "CSV"
				                }, {
				                    "sExtends": "xls",
				                    "sButtonText": "Excel"
				                }, {
				                    "sExtends": "print",
				                    "sButtonText": "Print",
				                    "sInfo": 'Please press "CTRL+P" to print or "ESC" to quit',
				                    "sMessage": "Generated by DataTables"
				                }]
				            }
						})
				.wrap(
						"<div style='position:auto;height:300px;width:100%'/>");
		jQuery('#scheduleTripStatus_wrapper .dataTables_filter input').addClass(
				"form-control input-medium input-inline"); // modify table
		// search input
		jQuery('#scheduleTripStatus_wrapper .dataTables_length select').addClass(
				"form-control input-xsmall input-inline");
		$("#tripstatus").show();
		
		  document.getElementById('selectdate').innerHTML=selecteddate;
		 document.getElementById('depId').innerHTML=selectedDepot;
		 document.getElementById('divId').innerHTML=selectedDivision;
	
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
		var dummyDate=selectedDate.split("-");
		var choosenDate=dummyDate[2]+"-"+dummyDate[1]+"-"+dummyDate[0];
  
	     var busStopId=$('#startpointId').val(); 
	      // alert("bus"+busStopId); 
	       
		$('#scheduleTripStatus')
				.dataTable(
						{
							"aLengthMenu" : [ [ 5, 15, 20, -1 ],
									[ 5, 15, 20, "All" ] // change
							],
							// set the initial value
							"iDisplayLength" : 20,
							"sAjaxSource" : "getScheduleWiseTripDistance.action?scheduleNo="+scheduleNo+"&selectedDate="+choosenDate+"&busStopId="+busStopId,
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
								if (aaData[0] == "General Shift"){
									$('td:eq(0)', nRow).attr('colspan',19);
									$('td:eq(0)').css('color', 'blue');
								
			                        for(var i =1; i<=19;i++){
			                        $('td:eq('+i+')', nRow).attr('colspan',0);
			                        $('td:eq('+i+')', nRow).hide();
			                        }
									console.log("GN"+aaData[0]);
									sDirectionClass = "success";
								} else if (aaData[0] == "Night Out") {
									
									$('td:eq(0)', nRow).attr('colspan',19);
									
									
			                        for(var i =1; i<=19;i++){
			                        $('td:eq('+i+')', nRow).attr('colspan',0);
			                        $('td:eq('+i+')', nRow).hide();
			                        }
									console.log("NO"+aaData[0]);
									sDirectionClass = "Total";
								}
								else if (aaData[0] == "Day Out -Shift 1") {
									$('td:eq(0)', nRow).attr('colspan',19);
									
									
			                        for(var i =1; i<=19;i++){
			                        $('td:eq('+i+')', nRow).attr('colspan',0);
			                        $('td:eq('+i+')', nRow).hide();
			                        }
									console.log("DOshift1"+aaData[0]);
									sDirectionClass = "success";
								}
								
								else if (aaData[0] == "Day Out - Shift 2") {
									$('td:eq(0)', nRow).attr('colspan',19);
									
									
			                        for(var i =1; i<=19;i++){
			                        $('td:eq('+i+')', nRow).attr('colspan',0);
			                        $('td:eq('+i+')', nRow).hide();
			                        }
									console.log("DOshif2"+aaData[0]);
									sDirectionClass = "success";
								}
								/* else {
									sDirectionClass = "outbound";
								} */
								$(nRow).addClass(sDirectionClass);
								return nRow;
							},
							
							"aoColumnDefs" : [ {
								'bSortable' : false,
								'aTargets' : [ 0, 1, 2, 3, 4 ]
							} ,{ "sClass": "type","aTargets": [ 0 ] },{
							      "aTargets": [0],
							      "fnCreatedCell": function (nTd, aaData, oData, iRow, iCol) {
							        if ( aaData == "General Shift" ) {
							          $(nTd).css('color', 'red');
							          $(nTd).css('text-align', 'center');
							           $(nTd).css('font-family' ,'Times New Roman');
							           $(nTd).css('font-size' ,' 20px');
							           $(nTd).css('text-transform' ,'uppercase');
							           $(nTd).css('font-weight' ,' bold');
							          
							        }else if ( aaData == "Night Out" ) { /* document.getElementById('selectdate').innerHTML=selectedDate;
							   		 document.getElementById('scheduleno').innerHTML=scheduleName; */
							        	
								          $(nTd).css('color', 'red');
								          $(nTd).css('text-align', 'center');
								           $(nTd).css('font-family' ,'Times New Roman');
								           $(nTd).css('font-size' ,' 20px');
								           $(nTd).css('text-transform' ,'uppercase');
								           $(nTd).css('font-weight' ,' bold');
								           
								           
								      
								        }else if ( aaData == "Day Out -Shift 1" ) {
									          $(nTd).css('color', 'red');
									          $(nTd).css('text-align', 'center');
									           $(nTd).css('font-family' ,'Times New Roman');
									           $(nTd).css('font-size' ,' 20px');
									           $(nTd).css('text-transform' ,'uppercase');
									           $(nTd).css('font-weight' ,' bold');
									      
									        }
								        else if ( aaData == "Day Out - Shift 2" ) {
									          $(nTd).css('color', 'red');
									          $(nTd).css('text-align', 'center');
									           $(nTd).css('font-family' ,'Times New Roman');
									           $(nTd).css('font-size' ,' 20px');
									           $(nTd).css('text-transform' ,'uppercase');
									           $(nTd).css('font-weight' ,' bold');
									      
									        }
							        
							      }
							    }  ],
							aaSorting:[],
							"bLengthChange" : false,
							"sDom" : '<"top">rt<"bottom"flp><"clear">',
							
						})
				.wrap(
						"<div style='position:auto;height:300px;width:100%'/>");
		jQuery('#scheduleTripStatus_wrapper .dataTables_filter input').addClass(
				"form-control input-medium input-inline"); // modify table
		// search input
		jQuery('#scheduleTripStatus_wrapper .dataTables_length select').addClass(
				"form-control input-xsmall input-inline");
		$("#tripstatus").show();
		 document.getElementById('selectdate').innerHTML=selectedDate;
		 document.getElementById('scheduleno').innerHTML=scheduleName;
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
						EARLY ARRIVAL  REPORT <small></small>
						</h3>

						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>View Early Arrival Report
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
									<label class="col-md-3 control-label">Time Difference<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select id="range" class="select2_category form-control"
											>
											<option value="0" selected="selected">--Select--</option>
											<option value="30">>30min &< 60min</option>
											<option value="60">> 60min & <120min</option>
											<option value="120">>120min&<180min</option>
											<option value="180">>180</option>
										</select>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-md-3">Date<font
										color="red">*</font></label>
									<div class="col-md-4">
										<div class="input-group input-medium date date-picker"
											style="width: auto" data-date-format="dd-mm-yyyy"
											data-date-viewmode="years">
											<input type="text" class="form-control" id="selecteddate" 
												readonly name="selecteselecteddateddate" /> <span
												class="input-group-btn">
												<button class="btn default" type="button">
													<i class="fa fa-calendar"></i>
												</button> 
											</span>
											
										</div>
										
									</div>
									<div class="col-md-1" id="">
									<button type="button" class="btn default" onclick="getScheduledTripStatusDataOnSubmit()" style="position: static;">Submit</button>
									</div> 
										<div class="col-md-1"id="printbutton">
											<input type='button' class="btn default" id='button1' onclick='printDiv()' value='Print' />													
										</div>		
								</div>
									
								<%-- <div class="form-group">
									<label class="col-md-3 control-label">Schedule Number<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select id="schedulelist" name="scheduleno" 
											class="select2_category form-control"
											onchange="getScheduledTripStatusData(this.value)">
											<option value="0">Schedule</option>
											<option value="-1">----ALL--</option>
										</select>

									</div>
									 
								</div> --%>
				 
																  
                         <div id="header" class="portlet-body" style="display: none; visibility: hidden;">
										<center>	<span><h4>Early Arrival Report</h4></span>
								
							         	
							         	<h4>Division :<font size="2"><b><span id="divId" ></span></b></h4></font> <h4>Depot :<font size="2"><b><span id="depId" ></span></b></h4></font><br>
							         	
							         	<h4>Date :<font size="2"><b><span id="selectdate" ></span></b></h4></font><</center>
							 </div>
								<div class="portlet-body" id="scheduleprint"
									style='height: 600px; overflow-y: scroll; display: block;'>
									<div id="tripstatus">
										
											<table class="table table-striped table-bordered table-hover" width="100%";
											id="scheduleTripStatus" style="display: none" cellpadding="1">
												<thead>
													
													<tr bgcolor="#819FF7">
                                                        <th>Division</th>
														<th>Depot No.</th>
														<th>Sr.No.</th>
														<th>Schedule No.</th>
														<th>Duty</th>
														<th>Sch. Arrival Time</th>
														<th>GPS Arrival Time</th>
														<th>Difference(Min)</th>
														<!-- <th>DIFFERENCE(HH:MM:SS)</th>
														<th>SCHEDULE</th>
														<th>ACTUAL</th>
														<th>DIFFERENCE(HH:MM:SS)</th>
														<th>SCHEDULE</th>
														<th>ACTUAL</th>
														<th>DIFFERENCE(HH:MM:SS)</th> -->
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
	
<script type="text/javascript" src="assets/global/plugins/data-tables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/global/plugins/data-tables/tabletools/js/dataTables.tableTools.min.js"></script>
</body>
</html>
<style>
.Total{
	 
	 text-align: center;
	 font-family: 'Times New Roman';
	font-size: 15px;
	 
	font-weight: bold;

	}
</style>