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
	function validateTripStatusReportFields(scheduleNo,depotNo,divisonNo,selDate)
	{
		 if(divisonNo==0)
		 {
			bootbox.alert("Please Select Divison");
			return false;
		 }
		 if(depotNo==0)
		 {
			 bootbox.alert("Please Select Depot");
			return false;
		 }
		 if(selDate==0)
	     {
			 bootbox.alert("Please Select Date");
		   return false;
	     }
	     if(scheduleNo==0)
	     {
	    	 bootbox.alert("Please Select Schedule Number");
		   return false;
	     }
	     

	 return true;
	}
	function getScheduledDepartureDataOnSubmit()
	{
		var scheduleNo=$("#schedulelist").val();
		var depotNo=$("#depotlist").val();
		var divisonNo=$("#divisionlist").val();
		var selecteddate=$("#selecteddate").val();
		
		
		var dummyDate=selecteddate.split("-");
		var choosenDate=dummyDate[2]+"-"+dummyDate[1]+"-"+dummyDate[0];
		var scheduleName=$("#schedulelist option:selected").text();
		var DepotName=$("#depotlist option:selected").text();
		var busStopId=$('#startpointId').val();
		validateflag=validateTripStatusReportFields(scheduleNo,depotNo,divisonNo,selecteddate);
		if(validateflag==true)
		{
		$("#printbutton").show();
		$("#scheduleprint").show();
		var scheduleNo=$("#schedulelist").val();
	//alert("HII"+scheduleNo);
		document.getElementById("scheduleDepartureData").style.display = '';
		$("#schdeviation").hide();
		document.getElementById("scheduleDepartureData").style.display = '';
		var selectedDate=document.getElementById("selecteddate").value;
		
		$('#scheduleDepartureData')
		.dataTable(
				{
					"aLengthMenu" : [ [ 5, 15, 20, -1 ],
							[ 5, 15, 20, "All" ] // change
					],
					// set the initial value
					"iDisplayLength" : 20,
					"sAjaxSource" : "getRouteDeviationData.action?depotid="+depotNo+"&selectedDate="+choosenDate,
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
						if (aaData[0] == " "){
							$('td:eq(0)', nRow).attr('colspan',10)
							//$('td:eq(0)').css('color', 'blue');
						
	                        for(var i =1; i<=10;i++){
	                        $('td:eq('+i+')', nRow).attr('colspan',0);
	                        $('td:eq('+i+')', nRow).hide();
	                        }
							console.log("GN"+aaData[0]);
							sDirectionClass = "dummy";
						} else if (aaData[0] == "Daily Departure Summary") {
							
							$('td:eq(0)', nRow).attr('colspan',10);
							
							
	                        for(var i =1; i<=10;i++){
	                        $('td:eq('+i+')', nRow).attr('colspan',0);
	                        $('td:eq('+i+')', nRow).hide();
	                        }
							console.log("NOdd"+aaData[0]);
							sDirectionClass = "success";
						}
						else if (aaData[0] == "Type") {
							
							console.log("DOaddtype"+aaData[0]);
							sDirectionClass = "type";
							
							
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
					        if ( aaData == " " ) {
					          $(nTd).css('color', 'red');
					          $(nTd).css('text-align', 'center');
					           $(nTd).css('font-family' ,'Times New Roman');
					           $(nTd).css('font-size' ,' 20px');
					           $(nTd).css('text-transform' ,'uppercase');
					           $(nTd).css('height ' ,'50px');
					           console.log("NO Data");
					          
					        }else if ( aaData == "Daily Departure Summary" ) {
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
jQuery('#scheduleDepartureData_wrapper .dataTables_filter input').addClass(
		"form-control input-medium input-inline"); // modify table
// search input
jQuery('#scheduleDepartureData_wrapper .dataTables_length select').addClass(
		"form-control input-xsmall input-inline");
$("#scheduleDeparture").show();
<%--  $.ajax({
       type: "get",
       url: '<%=request.getContextPath()%>/getScheduleHeaderData?scheduleNo='+ scheduleNo+'&selectedDate='+selectedDate,
       success: function(result) {
    	  
    	   document.getElementById('scheduletype').innerHTML=result; 
          
           
       }
   });  --%>
   document.getElementById('selectdate').innerHTML=selecteddate;
	 document.getElementById('scheduleno').innerHTML=scheduleNo;

	 document.getElementById('Depotname').innerHTML=DepotName;
		}
	
	}
	function getScheduledDepartureData(scheduleNo) {
		
		$("#printbutton").show();
		$("#scheduleprint").show();
		
		
	//alert("HII"+scheduleNo);
		document.getElementById("scheduleDepartureData").style.display = '';
		$("#schdeviation").hide();
		document.getElementById("scheduleDepartureData").style.display = '';
		
		
		var selectedDate=document.getElementById("selecteddate").value;
		var dummyDate=selectedDate.split("-");
		var choosenDate=dummyDate[2]+"-"+dummyDate[1]+"-"+dummyDate[0];
		var scheduleName=$("#schedulelist option:selected").text();
		var DepotName=$("#depotlist option:selected").text();
		
  
	  
	     var busStopId=$('#startpointId').val(); 
	      // alert("bus"+busStopId); 
	       
		$('#scheduleDepartureData')
				.dataTable(
						{
							"aLengthMenu" : [ [ 5, 15, 20, -1 ],
									[ 5, 15, 20, "All" ] // change
							],
							// set the initial value
							"iDisplayLength" : 20,
							"sAjaxSource" : "getScheduleDepartureData.action?scheduleNo="+scheduleNo+"&selectedDate="+selectedDate+"&busStopId="+busStopId,
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
								if (aaData[0] == " "){
									$('td:eq(0)', nRow).attr('colspan',10)
									//$('td:eq(0)').css('color', 'blue');
								
			                        for(var i =1; i<=10;i++){
			                        $('td:eq('+i+')', nRow).attr('colspan',0);
			                        $('td:eq('+i+')', nRow).hide();
			                        }
									console.log("GN"+aaData[0]);
									sDirectionClass = "dummy";
								} else if (aaData[0] == "Daily Departure Summary") {
									
									$('td:eq(0)', nRow).attr('colspan',10);
									
									
			                        for(var i =1; i<=10;i++){
			                        $('td:eq('+i+')', nRow).attr('colspan',0);
			                        $('td:eq('+i+')', nRow).hide();
			                        }
									console.log("NO"+aaData[0]);
									sDirectionClass = "success";
								}
								else if (aaData[0] == "Type") {
									
									console.log("DOaddtype"+aaData[0]);
									sDirectionClass = "type";
									
									
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
							        if ( aaData == " " ) {
							          $(nTd).css('color', 'red');
							          $(nTd).css('text-align', 'center');
							           $(nTd).css('font-family' ,'Times New Roman');
							           $(nTd).css('font-size' ,' 20px');
							           $(nTd).css('text-transform' ,'uppercase');
							           $(nTd).css('height ' ,'50px');
							           console.log("NO Data");
							          
							        }else if ( aaData == "Daily Departure Summary" ) {
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
		jQuery('#scheduleDepartureData_wrapper .dataTables_filter input').addClass(
				"form-control input-medium input-inline"); // modify table
		// search input
		jQuery('#scheduleDepartureData_wrapper .dataTables_length select').addClass(
				"form-control input-xsmall input-inline");
		$("#scheduleDeparture").show();
		
	       document.getElementById('selectdate').innerHTML=selectedDate;
			 document.getElementById('scheduleno').innerHTML=scheduleNo;
		
			 document.getElementById('Depotname').innerHTML=DepotName;
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
							ROUTE DEVIATION  REPORT <small></small>
						</h3>

						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>View  Route Deviation Report
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
											<input type="text" class="form-control" id="selecteddate" 
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
									
							   
     	               		
							<script>                 	
  	 function tabletoExcel(btnExport){     
  	var htmltable= document.getElementById('btnExport');   
    $( "#header-fixed" ).remove();
    var inputHTML = "<table border='1'>";
    inputHTML += "<tr>";
    inputHTML += "<th  align='left'colspan='9'>Bangalore Metropolitan Transport Corporation</th>";
    inputHTML += "</tr>";
    inputHTML += "<th  align='left'colspan='9'>ROUTE DEVIATION  REPORT </th>";
    inputHTML += "</tr>";
    inputHTML += "<tr>";
    inputHTML += "<th colspan='2' align='left'>Date Range:</th>";
    inputHTML += "<th colspan='7' align='left'>" + $("#startdate").val() + " to " + $("#endate").val() + "</th>";
    inputHTML += "</tr>";
    inputHTML += "</table>";
    var htmltable = document.getElementById("scheduleDeparture");
    var html = inputHTML + htmltable.outerHTML;
    var downloadLink = document.createElement("a");
    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
    downloadLink.download = "ROUTE DEVIATION  REPORT.xls ";
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
}</script>

									 <div class="col-md-1" id="">
									<button type="button" class="btn default" onclick="getScheduledDepartureDataOnSubmit()" style="position: static;">Submit</button>
									</div> 
										<div class="col-md-1"id="printbutton">
											<input type='button' class="btn default" id='button1' onclick='printDiv()' value='Print' />													
										</div>
										<div class="col-md-1" id="">
								 <button type="button" class="btn default" id="btnExport" filename= "report" onclick="tabletoExcel();"> EXPORT </button>
                                </div>	
								</div>
									
								<%-- <div class="form-group">
									<label class="col-md-3 control-label">Schedule Number<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select id="schedulelist" name="scheduleno" 
											class="select2_category form-control"
											onchange="getScheduledDepartureData(this.value)">
											<option value="0">Schedule</option>
										
										</select>

									</div>
							    <div class="col-md-1" id="">
									<button type="button" class="btn default" onclick="getScheduledDepartureDataOnSubmit()" style="position: static;">Submit</button>
									</div> 
										<div class="col-md-1"id="printbutton">
											<input type='button' class="btn default" id='button1' onclick='printDiv()' value='Print' />													
										</div>
								</div> --%>
				 
							
								  
                         <div id="header" class="portlet-body" style="display: none; visibility: hidden;">
										<center>	<h4>Route Deviation  Report</h4><br>
							        	<h4><span id="Depotname" ></span></b></h4>
							         	<h4>Date :<font size="2"><b><span id="selectdate" ></span></b></h4></font>
							         	</center>
								
                                </div>
								<div class="portlet-body" id="scheduleprint"
									style='height: 600px; overflow-y: scroll; display: block;'>
									<div style="text-align:center">
									<div id="scheduleDeparture">
											<table class="table table-striped table-bordered table-hover" width="100%";
											id="scheduleDepartureData" style="display: none" cellpadding="1">
												<thead>
													<tr bgcolor="#819FF7">
														<th>Sr.No.</th>
														<th>Sch. No.</th>
														<th>Depot No.</th>
														<th>Shift</th>
														<th>Bus No.</th>
														<th>Trip No.</th>
														<th>Driver Name</th>
														<th>Conductor Name</th>
														
											</tr>
												</thead>
											</table>
									</div>
									</div>
									</div>
								</div>
								<div id="schdeviation" class="portlet-body">
								<div style="text-align:center">
									<table class="table table-striped table-bordered table-hover"
										id="scheduleDevationdata" style="display: none">
										<thead>
											<tr>
												<th>#</th>
												<th>Trip No.</th>
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
									<div style="text-align:center">
								
										<table class="table table-striped table-bordered table-hover"
											id="tripReportDetails123">
											<thead>
												<tr>
													<th>#</th>
													<th>Bus Stop Name</th>
													<th>Entry Time</th>
													<th>Exit Time</th>
													<th>Status</th>
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
										<div style="text-align:center">

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

<script type="text/javascript" src="assets/global/plugins/data-tables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/global/plugins/data-tables/tabletools/js/dataTables.tableTools.min.js"></script>
</body>
</html>