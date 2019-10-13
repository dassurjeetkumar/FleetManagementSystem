<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places,drawing"></script>
<%-- <script src="assets/vts/js/vts.js" type="text/javascript"></script> --%>
<script src="assets/etm/etmChart.js"
	type="text/javascript"></script>

<script type="text/javascript" src="//www.google.com/jsapi"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
	
	</script>

<form>
	<input type="hidden" id='deviceid' name='deviceid' /> <input
		type="hidden" id='vehicleid' name='vehicleid' /> <input type="hidden"
		id='scheduleno' name='scheduleno' /> <input type="hidden"
		id='routeid' name='routeid' /> <input type="hidden" id='schedulename'
		name='schedulename' /> <input type="hidden" id='startpoint'
		name='startpoint' /> <input type="hidden" id='endpoint'
		name='endpoint' /> <input type="hidden" id='starttime'
		name='starttime' /> <input type="hidden" id='endtime' name='endtime' />
	<input type="hidden" id='tripstatus' name='tripstatus' /> <input
		type="hidden" id='dutydate' name='dutydate' /> <input type="hidden"
		id='id' name='id' />


	<div class="page-content-wrapper">
		<div class="page-content">
			<!-- <div class="row">
				<div class="col-md-12">
					<div class="portlet box red">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i><b>Alerts</b>
							</div>
							<div class="tools">
								<a class="collapse" id="vehicle_chart_alert" href="javascript:;">
								</a> <a class="config" data-toggle="modal" href="#portlet-config">
								</a> <a class="reload" href="javascript:;"> </a> <a class="remove"
									href="javascript:;"> </a>
							</div>
						</div>
						<div class="portlet-body" id="alert_vehicle_display_chart">
							<h4>ETM Alerts</h4>
							<div id="barchart_alert" class="chart"></div>

						</div>
					</div>
				</div>
			</div> -->
			<form action="etmDashboard" method="post">
				<center>		<div class="col-md-4" align="right">
							  <label class="control-label col-md-3">Date:<font
										color="red">*</font></label>
								<div class="col-md-4">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" data-date-end-date="0d"> 
								<input id="startdate" name="dateaction" class="form-control" type="text" readonly="" size="16" name="rateMaster.effectiveStartDate" placeholder="Pick The Date" "
								value="<s:property value='rateMaster.effectiveStartDate' />"
								>
								<span class="input-group-btn">
								<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
								</button>
								</span>
						<%-- 		<script>
 										var curr_date=new Date().toJSON().slice(0,10);
                                        curr_date=curr_date.split("-");
                                         curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
                                        var dtval=document.getElementById('startdate').value;	
                                        
                                         if(dtval==''){
                                         $('#startdate').val(curr_date);
                                        }
								</script> --%>
								</div>
								</div></div></center>
								
								<button type="submit" class="btn blue">submit</button>
								
								</form>
								
								<div class="header" align="right">
								<b><font size="4" color="red"><label>Report Date: </label></font><font size="4"  color="red" ><b><s:property   value='reportdate' /></font></b>
								
								</div>
								 <BR><BR><BR>
								
			<div class="row">
                    <div class="col-md-3">
					<div class="portlet box blue-chambray">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i><b>ETM </b>
							</div>
							<div class="tools">
								<a class="collapse" id="vehicle_chart_live" href="javascript:;">
								</a> <a class="config" data-toggle="modal" href="#portlet-config">
								</a> <a class="reload" href="javascript:;"> </a> <a class="remove"
									href="javascript:;"> </a>
							</div>
						</div>
						<div class="portlet-body" id="chart_live_display">
							<h4 id="totalVehicle"></h4>
							<p id="demo"></p>
							<div id="alertspie" class="chart"></div>
						</div>
					</div>
				</div> 
				 <div class="col-md-3">
					<div class="portlet box blue-chambray">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i><b>VOLVO</b>
							</div>
							<div class="tools">
								<a class="collapse" id="vehicle_chart_live" href="javascript:;">
								</a> <a class="config" data-toggle="modal" href="#portlet-config">
								</a> <a class="reload" href="javascript:;"> </a> <a class="remove"
									href="javascript:;"> </a>
							</div>
						</div>
						<div class="portlet-body" id="chart_live_display">
							<h4 id="totalVehiclevolvo"></h4>
							<p id="demo"></p>
							<div id="alertspievolvo" class="chart"></div>
						</div>
					</div>
				</div> 
				 <div class="col-md-3">
					<div class="portlet box blue-chambray">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i><b>Ordinary </b>
							</div>
							<div class="tools">
								<a class="collapse" id="vehicle_chart_live" href="javascript:;">
								</a> <a class="config" data-toggle="modal" href="#portlet-config">
								</a> <a class="reload" href="javascript:;"> </a> <a class="remove"
									href="javascript:;"> </a>
							</div>
						</div>
						<div class="portlet-body" id="chart_live_display">
							<h4 id="totalVehicleord"></h4>
							<p id="demo"></p>
							<div id="alertspieord" class="chart"></div>
						</div>
					</div>
				</div> 
				
				
						 <div class="col-md-3">
					<div class="portlet box  blue-chambray">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i><b>INGENICO</b>
							</div>
							<div class="tools">
								<a class="collapse" id="ingenico_chart_live" href="javascript:;">
								</a> <a class="config" data-toggle="modal" href="#portlet-config">
								</a> <a class="reload" href="javascript:;"> </a> <a class="remove"
									href="javascript:;"> </a>
							</div>
						</div>
						<div class="portlet-body" id="ingenico_live_display">
							<h4 id="totalingenico"></h4>
							<p id="demo"></p>
							<div id="alerteingenico" class="chart"></div>
						</div>
					</div>
				</div> 
				
						 <div class="col-md-3">
					<div class="portlet box  blue-chambray">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i><b>Verifone</b>
							</div>
							<div class="tools">
								<a class="collapse" id="verifone_chart_live" href="javascript:;">
								</a> <a class="config" data-toggle="modal" href="#portlet-config">
								</a> <a class="reload" href="javascript:;"> </a> <a class="remove"
									href="javascript:;"> </a>
							</div>
						</div>
						<div class="portlet-body" id="verifone_live_display">
							<h4 id="totalverifone"></h4>
							<p id="demo"></p>
							<div id="alerteverifone" class="chart"></div>
						</div>
					</div>
				</div>   
				
				
				
				 <div class="col-md-3">
					<div class="portlet box  blue-chambray">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i><b>On Duty ETIM Ping Status</b>
							</div>
							<div class="tools">
								<a class="collapse" id="etmping_chart_live" href="javascript:;">
								</a> <a class="config" data-toggle="modal" href="#portlet-config">
								</a> <a class="reload" href="javascript:;"> </a> <a class="remove"
									href="javascript:;"> </a>
							</div>
						</div>
						<div class="portlet-body" id="etmping_live_display">
							<h4 id="totaletmping"></h4>
							<p id="demo"></p>
							<div id="alertetmping" class="chart"></div>
						</div>
					</div>
				</div>
				
				
				
				<div class="col-md-12">
					<div class="portlet box blue" id="totalVehicleDetails" style="display: none">
						<div class="portlet-title">
							<div class="caption">
								<div style="width: 250px"  id='vstatus'></div>
							</div>
							<div class="tools">
								<a href="javascript:;" class="reload"> </a> <a
									href="javascript:;" class="remove"> </a>
							</div>
						</div>
		
	
						<div class="portlet-body" id="vehicle" style="display: none">
							<table class="table table-striped table-bordered table-hover"
								id="alert_dashBoard_data">
								<thead>
									<tr>
										<th>Sr. No.</th>
<!-- 										<th>ETIM Number</th> -->
										<th>Depot Name</th>
<!-- 										<th>Schedule Name</th> -->
<!-- 										<th>Shift</th> -->
<!-- 										<th>Vehicle No</th> -->
											<th>Total</th>
									</tr>
								</thead>
							</table>

						</div>
						<div class="portlet-body" id="deviceGprs" style="display: none">
							<table class="table table-striped table-bordered table-hover"
								id="alert_gprs_data">
								<thead>
									<tr>
										<th>Sr. No.</th>
<!-- 										<th>ETIM Number</th> -->
										<th>Depot Name</th>
										<th>Total</th>
									</tr>
								</thead>
							</table>

						</div>
						<div class="portlet-body" id="deviceTampered" style="display: none">
							<table class="table table-striped table-bordered table-hover"
								id="alert_tampered_data">
								<thead>
									<tr>
										<th>Sr. No.</th>
										<th>Device Number</th>
										
										
									</tr>
								</thead>
							</table>

						</div>
						
						
						
						
										

					
					
					
					
					<div style="display: none;" id="mymodal13" class="modal fade"
						tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
						aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content" style="width: 800px; height: 500px; overflow: scroll;"
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
<!-- 													<div class="form-group"> -->
<!-- 														<label class="col-md-3 control-label"></label> -->
<!-- 													</div> -->
													<div class="portlet-body" id="InactivevehicleDetails"
														style="display: none">
														<div align='center'>
				
					<input type='button' class='btn blue' id='button1' onclick="exportToExcel2()" value='Export' />
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>			
														<div style="text-align:center">
   											<table border='1'
															class="table table-striped table-bordered table-hover"
															id="InActiveVehicleDetails">
															<thead>
																<tr>
													<th>Sr. No.</th>
										<th>ETIM Number</th>
										<th>Depot Name</th>
<!-- 										<th>Schedule Name</th> -->
<!-- 										<th>Shift</th> -->
<!-- 										<th>Vehicle No</th> -->
																</tr>
															</thead>
														</table>
													</div>
                                                   </div>
												</div>
											</div>
										</div>
									</div>
									</p>
								</div>

							</div>
						</div>
					</div>
						
						
						
						
		
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
											
					<div style="display: none;" id="mymodal12" class="modal fade"
						tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
						aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content" style="width: 800px; height: 500px; overflow: scroll;"
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
<!-- 													<div class="form-group"> -->
<!-- 														<label class="col-md-3 control-label"></label> -->
<!-- 													</div> -->
													<div class="portlet-body" id="activevehicleDetails"
														style="display: none">
														<div align='center'>
				
					<input type='button' class='btn blue' id='button1' onclick="exportToExcel1()" value='Export' />
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>			
														<div style="text-align:center">
   											<table border='1'
															class="table table-striped table-bordered table-hover"
															id="ActiveVehicleDetails">
															<thead>
																<tr>
													<th>Sr. No.</th>
										<th>ETIM Number</th>
										<th>Depot Name</th>
										<th>Schedule Name</th>
										<th>Shift</th>
										<th>Vehicle No</th>
																</tr>
															</thead>
														</table>
													</div>
                                                   </div>
												</div>
											</div>
										</div>
									</div>
									</p>
								</div>

							</div>
						</div>
					</div>
					
					
					
					
					<div style="display: none;" id="mymodal13" class="modal fade"
						tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
						aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content" style="width: 800px; height: 500px; overflow: scroll;"
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
<!-- 													<div class="form-group"> -->
<!-- 														<label class="col-md-3 control-label"></label> -->
<!-- 													</div> -->
													<div class="portlet-body" id="InactivevehicleDetails"
														style="display: none">
														<div align='center'>
				
					<input type='button' class='btn blue' id='button1' onclick="exportToExcel2()" value='Export' />
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>			
														<div style="text-align:center">
   											<table  border='1'
															class="table table-striped table-bordered table-hover"
															id="InActiveVehicleDetails">
															<thead>
																<tr>
													<th>Sr. No.</th>
										<th>ETIM Number</th>
										<th>Depot Name</th>
<!-- 										<th>Schedule Name</th> -->
<!-- 										<th>Shift</th> -->
<!-- 										<th>Vehicle No</th> -->
																</tr>
															</thead>
														</table>
													</div>
                                                   </div>
												</div>
											</div>
										</div>
									</div>
									</p>
								</div>

							</div>
						</div>
					</div>
	
	
	<%-- <script type="text/javascript">
		var geocoder;
		var map;
		var places;
		function initialize() {
			// create the geocoder
			var mapOptions = {
				zoom : 13,
				// Center the map on Bngalore
				center : new google.maps.LatLng(12.97928309, 77.57205963)
			};
			map = new google.maps.Map(document.getElementById('gmap_basic'),
					mapOptions);
			//map1 = new google.maps.Map(document.getElementById('gmap_marker_division'), mapOptions);
			var polyOptions = {
				strokeColor : '#000000',
				strokeOpacity : 1.0,
				strokeWeight : 3
			};
			poly = new google.maps.Polyline(polyOptions);
			poly.setMap(map);
		}
	</script> --%>
	<!-- END CONTENT -->
	<!-- TrackOnline Begins -->

	<!-- <div style="display: none;" id="mymodal" class="modal fade"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
				</div>
				<div>
					<p>
					<div class="portlet box white ">
						<div>
							<input type="hidden" name="requestType" id="requestType"
								value="map" />
							<div>
								<div class="portlet solid white">
									<div class="form-group">
										<label class="col-md-3 control-label"></label>
									</div>
									<div class="portlet-body">
										<div id="gmap_basic" class="gmaps"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
					</p>
				</div>

			</div>
		</div>
	</div> -->
		<!--<div style="display: none;" id="mymodal1" class="modal fade"
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
		<!-- 						<div class="portlet solid white">
									<div class="form-group">
										<label class="col-md-3 control-label"></label>
									</div>
									<div class="portlet-body" id="withoutbusstop"
										style="display: none">
										<table class="table table-striped table-bordered table-hover"
											id="vehicleDetails123">
											<thead>
												<tr>
													<th>Sr. No.</th>
													<th>Vehicle Number:</th>
													<th>Device ID:</th>
													<th>Speed:</th>
													<th>Trip number:</th>
													<th>Schedule Time:</th>
													<th>Actual Time:</th>
													<th>GPS Date:</th>

												</tr>
											</thead>
										</table>
									</div> -->
							<!--  		<div class="portlet-body" id="devdetails" style="display: none">
										<table class="table table-striped table-bordered table-hover"
											id="vehicleDetailsDev">
											<thead>
												<tr>
													<th>Sr. No.</th>
													<th>Exit point Location:</th>
													<th>Entry point Location:</th>
													<th>Trip No:</th>
													<th>Deviated Distance Km:</th>
													<th>Exit Time:</th>
													<th>Entry Time:</th>
												</tr>
											</thead>
										</table>
									</div> -->
				<!-- 					<div class="portlet-body" id="tamperingdetails" style="display: none">
										<table class="table table-striped table-bordered table-hover"
											id="vehicleDetailsTampering">
											<thead>
												<tr>
													<th>Sr. No.</th>
													<th>Vehicle no</th>
													<th>Date</th>
												</tr>
											</thead>
										</table>
									</div> -->
									
				<!-- 					<div class="portlet-body" id="withbusstop"
										style="display: none">
										<table class="table table-striped table-bordered table-hover"
											id="vehicleDetails1234">
											<thead>
												<tr>
													<th>Sr. No.</th>
													<th>Vehicle Number:</th>
													<th>Device ID:</th>
													<th>Speed:</th>
													<th>Schedule_No:</th>
													<th>GPS Date:</th>
													<th>Stop Name:</th>
												</tr>
											</thead>
										</table>
									</div> -->
					<!-- 				<div class="portlet-body" id="alert5" style="display: none">
										<table class="table table-striped table-bordered table-hover"
											id="skipStopAlert_data1">
											<thead>
												<tr>
													<th>Sr. No.</th>
													<th>Trip No</th>
													<th>Time Of Skipping</th>
													<th>BUS STOP NAME SKIPPED</th>
												</tr>
											</thead>
										</table>

									</div> -->
						<!-- 			<div class="portlet-body" id="sosdetails" style="display: none">
										<table class="table table-striped table-bordered table-hover"
											id="sosDetails">
											<thead>
												<tr>
													<th>Sr. No.</th>
													<th>Vehicle no</th>
													<th>Date</th>
												</tr>
											</thead>
										</table>
									</div> -->
								</div>
							</div>
						</div>
					</div>
					</p>
				</div>

			</div>
		</div>
	</div>
</form>
<script>
	$(document).ready(function() {
		//loadActiveVehicles();
		//getAlertIntervalData();
/*  		getEtmData();
		getEtmDataVolvo();
		getEtmDataOrd();  */
		setTimeout(getEtmData,0);
		setTimeout(getEtmDataVolvo,0);
		setTimeout(getEtmDataOrd,0); 
		setTimeout(getingenico,0); 
		setTimeout(getverifone,0);
		setTimeout(getetmpingdata,0); 
		//getetmpingdata();
		
	});
	
	
	function exportToExcel1(btnExport){   
		
		
		 var inputHTML = "<table border='1'>";
		    inputHTML += "<tr>";
		    inputHTML += "<th  align='center'colspan='6'><center>Bangalore Metropolitan Transport Corporation</center></th>";
		    inputHTML += "</tr>";
		    inputHTML += "<th  align='center'colspan='6'><center>ETIM Active Vehicle Details Report</center></th>";
		    inputHTML += "</tr>";
		    inputHTML += "<tr>";
//	 	    inputHTML += "<th colspan='2' align='left'>Date Range:</th>";
//	 	    inputHTML += "<th colspan='7' align='left'>" + $("#startdate").val() + " to " + $("#endate").val() + "</th>";
		    inputHTML += "</tr>";
		    inputHTML +="<tr>";
		    inputHTML +="</tr>";
		    inputHTML += "</table>";
		
		
	    //  document.getElementById("print").style.visibility='hidden';     
//	     var divElements = document.getElementById("caption").innerHTML;
//	     divElements += document.getElementById("kmplSchedData").innerHTML;
		
		
	   var htmltable = document.getElementById("ActiveVehicleDetails");
	   var html =inputHTML+ htmltable.outerHTML;
	   var noOfTableExist = 0;
	   try{
			$("#ActiveVehicleDetails table").each(function(){
				noOfTableExist++;
			});
			
			/* If two table exist  then remove the last table on print click*/
			if(noOfTableExist >= 2){
				html = html.substring(0, html.indexOf("</table>") + 8) + "</div></div>";
			}
		}catch(err){
		    console.log("ExceptionCaught : " + err);
		}
	   
	   var downloadLink = document.createElement("a");
	   downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
	   downloadLink.download = "ETIMActiveVehicleReporDetails.xls";
	   document.body.appendChild(downloadLink);
	   downloadLink.click();
	   document.body.removeChild(downloadLink);
	 }
	
	function exportToExcel2(btnExport){   
		
		
		 var inputHTML = "<table border='1'>";
		    inputHTML += "<tr>";
		    inputHTML += "<th  align='center'colspan='3'><center>Bangalore Metropolitan Transport Corporation</center></th>";
		    inputHTML += "</tr>";
		    inputHTML += "<th  align='center'colspan='3'><center>ETIM Inactive Vehicle Details Report</center></th>";
		    inputHTML += "</tr>";
		    inputHTML += "<tr>";
//	 	    inputHTML += "<th colspan='2' align='left'>Date Range:</th>";
//	 	    inputHTML += "<th colspan='7' align='left'>" + $("#startdate").val() + " to " + $("#endate").val() + "</th>";
		    inputHTML += "</tr>";
		    inputHTML +="<tr>";
		    inputHTML +="</tr>";
		    inputHTML += "</table>";
		
		
	    //  document.getElementById("print").style.visibility='hidden';     
//	     var divElements = document.getElementById("caption").innerHTML;
//	     divElements += document.getElementById("kmplSchedData").innerHTML;
		
		
	   var htmltable = document.getElementById("InActiveVehicleDetails");
	   var html =inputHTML+ htmltable.outerHTML;
	   var noOfTableExist = 0;
	   try{
			$("#InActiveVehicleDetails table").each(function(){
				noOfTableExist++;
			});
			
			/* If two table exist  then remove the last table on print click*/
			if(noOfTableExist >= 2){
				html = html.substring(0, html.indexOf("</table>") + 8) + "</div></div>";
			}
		}catch(err){
		    console.log("ExceptionCaught : " + err);
		}
	   
	   var downloadLink = document.createElement("a");
	   downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
	   downloadLink.download = "ETIMInactiveVehicleReporDetails.xls";
	   document.body.appendChild(downloadLink);
	   downloadLink.click();
	   document.body.removeChild(downloadLink);
	 }
	
</script>
<script>
	google.maps.event.addDomListener(window, 'load', initialize);
	$("#mnu_header li").removeClass("classic-menu-dropdown active");
	$("#mnu_etm").addClass("classic-menu-dropdown active");
	$("#mnu_header li").addClass("classic-menu-dropdown");
	$("#etm_selected").addClass("selected");

	
</script>