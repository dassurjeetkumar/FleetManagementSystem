<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<script src="assets/vts/js/RouteBoxer.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?client=gme-trimaxitinfrastructure&sensor=false&libraries=places,drawing&signature=CpSOeA5B6SzC2rxqlzd2JwenGX0="></script>
<%-- <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=geometry,places,drawing"></script> --%>

</head>
<script>
function hidedata(){
	$(fback).hide();
	$(sback).hide();
	$(mback).hide();
	$(playbacksubmit).show();
}
window.onload=hidedata;
function getshowthehidden(){
	$(fback).show();
	$(sback).show();
	$(mback).show();
	$(playbacksubmit).show();
}
function getDepot(orgId){
	 $('#select2-chosen-2').html("Select");
	 $('#select2-chosen-3').html("Select");
	//alert('Here');
	 /* var selectedValue = $('#form-control').val(); */
	 var val=document.getElementById('divisionlist').value;
	// alert(val);
		 if(val!=0) {
			 $("#msg").html("Please wait...");
        $.ajax({
            type: "get",
            url: '<%=request.getContextPath()%>/getDepot?val='+val,
            success: function(result) {
            //	alert(result);
            	$("#msg").html("");
                document.getElementById('depotlist').innerHTML=result;
                getVehicle("");
            }
        });
	 }
	
}
function getRefresh() {
	if ($("#depotlist").is(':checked')) {
		// plotVehicle();
	} else {
		clearInterval(intervalID);
	}
}
function getVehicle(depotID)
{
	$('#select2-chosen-3').html("Select");
	var val=document.getElementById('depotlist').value;
	 if(val!=0) {
		 $("#msg").html("Please wait...");
   	$.ajax({
	   type: "GET",
       url: '<%=request.getContextPath()%>/getLiveVehicle?val='+val,
       success: function(result) {
    	   $("#msg").html("");
    	   document.getElementById('vehiclelist').innerHTML=result;
       }
   });
}
}
function getDeviceId()
{
	var val=document.getElementById('vehiclelist').value;
	 if(val!=0) {
  $.ajax({
      type: "get",
      url: '<%=request.getContextPath()%>/getDevice?val=' + val,
				success : function(result) {
					document.getElementById('devicelist').innerHTML = result;
				}
			});
		}
	}
</script>
<script>
 
	var geocoder;
	var map;
	var places;
	function initializeMap() {
		// create the geocoder
		geocoder = new google.maps.Geocoder();
		// set some default map details, initial center point, zoom and style
		
		var tmpLatLng = new google.maps.LatLng(12.9746, 77.5946);
		// create the map and reference the div#map-canvas container		
		<s:iterator value="list" id="list">
		//alert(<s:property value="LAT" />);		
		tmpLatLng = new google.maps.LatLng(<s:property value="LAT" />,
				<s:property value="LONGITUDE" />);
		// make and place map maker.
		var indMarker = new google.maps.Marker({
			map : map,
			position : tmpLatLng,
			icon : 'assets/images/Bus-Icon.png',
			title : '#' + "<s:property value="DEVICE_ID" />",
		});
		contentString = "<s:property value="DEVICE_ID" />";
		google.maps.event.addListener(indMarker, 'click', function(
				contentString) {
			return function() {
				infowindow.setContent(contentString);//set the content
				infowindow.open(map, this);
		 	};
		});
		markers.push(indMarker);
		</s:iterator> 
	} 

	function getCordinatesvalue(value) {
		//Validate Date..

		var startdate = document.getElementById("startdate").value;

		var enddate = document.getElementById("enddate").value;
		 var date1= moment(startdate).format('DD-MM-YYYY-HH-mm');
		 var date2= moment(enddate).format('DD-MM-YYYY-HH-mm');
		 var data1=date1.split("-");
		 var dd1=data1[0];
		 var mm1=data1[1];
		 var yy1=data1[2];
		 var hh1=data1[3];
		 var min1=data1[4];
		 var d1 = new Date(yy1,mm1,dd1,hh1,min1,0,0);  //Date object creation in JS
// 		 alert("");
		 
		 var data2=date2.split("-");
		 var dd2=data2[0];
		 var mm2=data2[1];
		 var yy2=data2[2];
		 var hh2=data2[3];
		 var min2=data2[4];
		 var d2 = new Date(yy2,mm2,dd2,hh2,min2,0,0);   //Date object creation in JS
		 
		 var diff = d2.getTime() - d1.getTime();     // in milli second
		 var hDiff = diff / 3600 / 1000;              // in hours
// 		 alert("hours difference-- "+hDiff);
	   
		initialize();
		$("#vehicledetails").css("display", "block");
		var vehicleID = document.getElementById("vehiclelist").value;
		//console.log(vehicleID);
		var divisionlist=document.getElementById("divisionlist").value;
		var depolist=document.getElementById("depotlist").value;
		if(d1>d2){
			bootbox.alert("From Time should not be greater than to To Time");
			return false;
		}
		if(hDiff>12){
			 bootbox.alert("From Time and To Time should not be greater than 12 hours");
// 			alert("Difference between time can not be greater than 3 hours");
			return false;
		}
		if(divisionlist==0){
			alert("Please select division");
			return false;
		}
		if(depolist==0){
			alert("Please select depot no");
			return false;
		}
		if(vehicleID==0){
			alert("Please select vehicle no");
			return false;
		}
		if (vehicleID == 'ALL') {
// 			$("#vehicledetails").css("display", "none");
			
// 			var divId1 = document.getElementById("start");
// 			divId1.style.display='none';	
			plotVehicle();
		} else if (vehicleID != 0) {
			$("#vehicledetails").css("display", "block");
			
			var divId1 = document.getElementById("start");
			divId1.style.display='';	
			$("#msg").html("Please wait...");
			getCordinates(vehicleID, 0, startdate, enddate,value);
			$("#msg").html("");
		}
		$(fback).hide();
		$(sback).hide();
		$(mback).hide();
	}
	function getCordinatesvaluePB(value, timeframe) {
		initialize();
		$("#vehicledetails").css("display", "block");
		var vehicleID = document.getElementById("vehiclelist").value;
		var startdate = document.getElementById("startdate").value;
		var enddate = document.getElementById("enddate").value;
		var divisionlist=document.getElementById("divisionlist").value;
		var depolist=document.getElementById("depotlist").value;
		
		//alert(depolist+"depolist");
		if(divisionlist==0){
			alert("Please select division");
			return false;
		}
		if(depolist==0){
			alert("Please select depot no");
			return false;
		}
		if(vehicleID==0){
			alert("Please select vehicle no");
			return false;
		}
// 		if(timeframe==0 || timeframe==''){
// 			alert("Please enter time frame");
// 			timeframe=0;
// 			enterIntervalForPlayback(value);			
// 		}
		if (vehicleID == 'ALL') {
// 			$("#vehicledetails").css("display", "none");
			
// 			var divId1 = document.getElementById("start");
// 			divId1.style.display='none';	
			plotVehicle();
		} else if (vehicleID != 0) {
			$("#vehicledetails").css("display", "block");
			
			var divId1 = document.getElementById("start");
			divId1.style.display='';	
			$("#msg").html("Please wait...");
			getCordinatespb(vehicleID, 0, startdate, enddate,value,timeframe);
			$("#msg").html("");
		
		}
		}


	function enterIntervalForSlowPlayback(idvalue) {
		var value = idvalue;
			
	    var timeframe = 1;	
	    
	    var startdate = document.getElementById("startdate").value;

		var enddate = document.getElementById("enddate").value;
		 var date1= moment(startdate).format('DD-MM-YYYY-HH-mm');
		 var date2= moment(enddate).format('DD-MM-YYYY-HH-mm');
		 var data1=date1.split("-");
		 var dd1=data1[0];
		 var mm1=data1[1];
		 var yy1=data1[2];
		 var hh1=data1[3];
		 var min1=data1[4];
		 var d1 = new Date(yy1,mm1,dd1,hh1,min1,0,0);  //Date object creation in JS
		 
		 var data2=date2.split("-");
		 var dd2=data2[0];
		 var mm2=data2[1];
		 var yy2=data2[2];
		 var hh2=data2[3];
		 var min2=data2[4];
		 var d2 = new Date(yy2,mm2,dd2,hh2,min2,0,0);   //Date object creation in JS
		 
		 var diff = d2.getTime() - d1.getTime();     // in milli second
		 var hDiff = diff / 3600 / 1000;              // in hours
		 if(d1>d2){
				bootbox.alert("From Time should not be greater than to To Time");
				return false;
			}
			if(hDiff>12){
				 bootbox.alert("From Time and To Time should not be greater than 12 hours");
//	 			alert("Difference between time can not be greater than 3 hours");
				return false;
			}
	    
	    if (timeframe != null) {
	    	
	    	getCordinatesvaluePB(value, timeframe);
	    	
	    }
	}

	function enterIntervalForMediumPlayback(idvalue) {
		var value = idvalue;
			
	    var timeframe = 0.50;	
	    
	    var startdate = document.getElementById("startdate").value;

		var enddate = document.getElementById("enddate").value;
		 var date1= moment(startdate).format('DD-MM-YYYY-HH-mm');
		 var date2= moment(enddate).format('DD-MM-YYYY-HH-mm');
		 var data1=date1.split("-");
		 var dd1=data1[0];
		 var mm1=data1[1];
		 var yy1=data1[2];
		 var hh1=data1[3];
		 var min1=data1[4];
		 var d1 = new Date(yy1,mm1,dd1,hh1,min1,0,0);  //Date object creation in JS
		 
		 var data2=date2.split("-");
		 var dd2=data2[0];
		 var mm2=data2[1];
		 var yy2=data2[2];
		 var hh2=data2[3];
		 var min2=data2[4];
		 var d2 = new Date(yy2,mm2,dd2,hh2,min2,0,0);   //Date object creation in JS
		 
		 var diff = d2.getTime() - d1.getTime();     // in milli second
		 var hDiff = diff / 3600 / 1000;              // in hours
		 if(d1>d2){
				bootbox.alert("From Time should not be greater than to To Time");
				return false;
			}
			if(hDiff>3){
				 bootbox.alert("From Time and To Time should not be greater than 3 hours");
//	 			alert("Difference between time can not be greater than 3 hours");
				return false;
			}
	    if (timeframe != null) {
	    	
	    	getCordinatesvaluePB(value, timeframe);
	    	
	    }
	}

	function enterIntervalForFastPlayback(idvalue) {
		var value = idvalue;
			
	    var timeframe = 0.25;	
	    
	    var startdate = document.getElementById("startdate").value;

		var enddate = document.getElementById("enddate").value;
		 var date1= moment(startdate).format('DD-MM-YYYY-HH-mm');
		 var date2= moment(enddate).format('DD-MM-YYYY-HH-mm');
		 var data1=date1.split("-");
		 var dd1=data1[0];
		 var mm1=data1[1];
		 var yy1=data1[2];
		 var hh1=data1[3];
		 var min1=data1[4];
		 var d1 = new Date(yy1,mm1,dd1,hh1,min1,0,0);  //Date object creation in JS
		 
		 var data2=date2.split("-");
		 var dd2=data2[0];
		 var mm2=data2[1];
		 var yy2=data2[2];
		 var hh2=data2[3];
		 var min2=data2[4];
		 var d2 = new Date(yy2,mm2,dd2,hh2,min2,0,0);   //Date object creation in JS
		 
		 var diff = d2.getTime() - d1.getTime();     // in milli second
		 var hDiff = diff / 3600 / 1000;              // in hours
		 if(d1>d2){
				bootbox.alert("From Time should not be greater than to To Time");
				return false;
			}
			if(hDiff>12){
				 bootbox.alert("From Time and To Time should not be greater than 12 hours");
//	 			alert("Difference between time can not be greater than 3 hours");
				return false;
			}
	    if (timeframe != null) {
	    	
	    	getCordinatesvaluePB(value, timeframe);
	    	
	    }
	}
</script>
<div class="page-content-wrapper">
	<div class="page-content">
	
		<div class="row">
			<div class="col-md-6">
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
				<h3 class="page-title">
					Playback
				</h3>
				<!-- END PAGE TITLE & BREADCRUMB-->
			</div>
		</div>
		<div class="portlet-body form">
			<div class="form-body">
				<div class="form-group">
					<!-- <label class="col-md-3 control-label">Division Name: <font
						color="red">*</font>
					</label> -->

					<div class="col-md-4">
						<s:select list="divisionlist" id="divisionlist"
							name="orgchart.org_chart_id"
							cssClass="select2_category form-control" onchange="getDepot(this.value)" onclick="getRefresh()"></s:select>
									
					</div>
					
					<script>
					getDepot("");
					</script>
					<div class="col-md-4">
						<select id="depotlist"   class="select2_category form-control"
							 onchange="getVehicle(this.value)">
							 <option value="0">Depot Name</option>
							 </select>
					
					</div>
					<div class="col-md-4">
						<select  id="vehiclelist"  class="select2_category form-control"
							onchange="getCordinatesvalue(this.value)">
							<option value="0">Vehicle Number</option>
							</select>
					
					</div>
				</div>
			</div>
<!--  			<div class="form-body">
				<div class="form-group">
					<label class="col-md-3 control-label">Depot Name: <font
						color="red">*</font>
					</label>

					<div class="col-md-4">
						<select id="depotlist"   class="select2_category form-control"
							 onchange="getVehicle(this.value)">
							 <option value="0">-----Select----</option>
							 </select>

					</div>
				</div>
			</div>
			
			<div class="form-body">
				<div class="form-group">
					<label class="col-md-3 control-label">Vehicle Number: <font
						color="red">*</font>
					</label>

					<div class="col-md-4">
						<select  id="vehiclelist"  class="select2_category form-control"
							onchange="getCordinatesvalue(this.value)">
							<option value="0">-----Select----</option>
							</select>
					</div>
				</div>
			</div>
			-->
			<div class="form-body" id="start">
				<div class="form-group">
<!-- 				 <label class="control-label col-md-2">Start Date/ Time </label> -->
				
					<div class="col-md-4">
					
						<div class="input-group date form_datetime">
							<input type="text" size="16" readonly name="startdate"
								id="startdate" class="form-control" value='<s:property value="startdate"/>'> <span
								class="input-group-btn">
								<button class="btn default date-set" type="button">
									<i class="fa fa-calendar"></i>
								</button>
							</span>
						</div>
					</div>
					
<!-- 					<label class="control-label col-md-2">End Date/ Time </label> -->
					<div class="col-md-4">
					<div class="input-group date form_datetime">
						<input type="text" size="16" readonly name="enddate" id="enddate" value='<s:property value="enddate"/>'
							class="form-control"> <span
							class="input-group-btn">
							<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
							</button>
						</span>
					</div>
					</div>
					<div class="col-md-4">
					<div id="playbacksubmit">
 				<label><input type="radio" name="radio" value="Submit" id="Submit"  onClick="getCordinatesvalue(this.value)">Tracking</label>
				<label><input type="radio" name="radio" value="playback" id="playback"  onClick="getshowthehidden()"> Playback</label>
			    </div>
			    <button id="sback" type="button" class="btn default" value="Submit" style="height: 30px; width: 90px;" name="buttonname" onClick="enterIntervalForSlowPlayback(this.value)">S Playback</button>
				<button id="mback" type="button" class="btn default" value="Submit" style="height: 30px; width: 90px;" name="buttonname" onClick="enterIntervalForMediumPlayback(this.value)">M Playback</button>
				<button id="fback" type="button" class="btn default" value="Submit" style="height: 30px; width: 90px;" name="buttonname" onClick="enterIntervalForFastPlayback(this.value)">F Playback</button>

					
				</div>
			</div>
<!--  			<div class="form-body" id="end">
				<div class="form-group">
					<label class="control-label col-md-3">End Date/ Time </label>
					<div class="col-md-4">
					<div class="input-group date form_datetime">
						<input type="text" size="16" readonly name="enddate" id="enddate" value='<s:property value="enddate"/>'
							class="form-control"> <span
							class="input-group-btn">
							<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
							</button>
						</span>
					</div>
					</div>
					<button type="button" class="btn default" value="Submit" name="buttonname" onClick="getCordinatesvalue(this.value)">Submit</button>
				</div>
			</div>
-->			
			<div class="form-body">
				
				<div class="col-md-4">
				<label class="col-md-6 control-label">Auto Refresh <font
					color="red">*</font>
				</label>
					<input type="checkbox" checked="checked" id="refreshID"
						onclick="getRefresh()" />
					</div>
				<div class="col-md-4">		
				<span id="msg"></span>
						<img src="assets/images/bus-map-icon.png" > Speed > 5
						<img src="assets/images/Icon-Bus-Orange.png" > Speed < 5
						<img src="assets/images/Icon-Bus-Red.gif" > Accident/Breakdown			
				
				</div>
				    <img src="assets/images/Icon-Bus-Blue.png" > Stationary
					<img src="assets/images/grey.png" > NRD
					<img src="assets/images/fourbyfour.png" 
						onclick="getjeepVehicle()" />Track Jeep Vehicles
			</div>
			<div id='loadingmessage' style='display:none'>
 <center> Loading Please Wait...<img src='assets/images/loader1.gif' width="500px" align="middle"/></center> 
</div>
			<div class="form-group" id="mapdiv" >
				<table class="table table-striped">
					<tr>
						<td valign="top">
							<div class="portlet-body" style="height: 600px">
								<div id="gmap_basic" class="gmaps" style="height: 100%"></div>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>
<form action="#" class="form-horizontal ">
<div style="display: none;" id="mymodal1" class="modal fade"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 600px; height: 600px;"
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
							<input type="hidden" name="gpstime" id="gpstime" />
							<input type="hidden" name="depotnm" id="depotnm" />
							<input type="hidden" name="scheduleno" id="scheduleno" />
							<input type="hidden" name="call_type" id="call_type" />
							
							<input type="hidden" name="lat" id="lat" />
							<input type="hidden" name="lng" id="lng" />
							<input type="hidden" name="drivertoken" id="drivertoken" />
							<input type="hidden" name="depot_id" id="depot_id" />
							<input type="hidden" name="shift" id="shift" />
	
							<!-- <input type="hidden" name="depotid" id="depotid" /> -->
							<div>
								<div class="portlet solid white">
									<div class="form-group">
										<label class="col-md-3 control-label"></label>
									</div>
									<div class="form-group">									
										<div class="form-group">
											<label class="col-md-3 control-label">Device Id </label>
											<div class="col-md-4">
												<input type="text" class="form-control" id="device_id"
													name="device_id" readonly="readonly" value=''></input>
											</div>
										</div>										
											<div class="form-group">
												<label class="col-md-3 control-label">Vehicle No.</label>
												<div class="col-md-4">
													<input type="text" class="form-control" id="vehicle_no"
														name="vehicle_no" readonly="readonly" value=''></input>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">Vehicle Status</label>
												<div class="col-md-4">
													<select id="vehicle_status"
														class="select1_category form-control" onchange="check() "
														name="vehicle_status">
														<option value="0">-----Select----</option>
														<option value="1">Normal</option>
														<option value="2">Vehicle Accident</option>
														<option value="3">Vehicle Breakdown</option>
														<option value="4">Other</option>
													</select>
												</div>
											</div>
											<div class="form-body">
												<div class="form-group" id="accident_type"
													style="display: none;">
													<label class="col-md-3 control-label">Accident Type: </label>
													<div class="col-md-4">
														<select id="acctypelist"
															class="select2_category form-control" 
															name="acctypelist">
															<option value="0">-----Select----</option>
														</select>
													</div>
												</div>
											</div>
											<div class="form-body">
												<div class="form-group" id="breakdown_type"
													style="display: none;">
													<label class="col-md-3 control-label">Breakdown Type: </label>
													<div class="col-md-4">
														<select id="brkdwntypelist"
															class="select2_category form-control" name="brkdwntypelist">
															<option value="0">-----Select----</option>
														</select>
													</div>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">Description</label>
												<div class="col-md-4">
													<textarea class="form-control" id="description"
														maxlength="100" name="notes"></textarea>
												</div>
											</div>
										</div>
										<br>
										<br>
										<div class="form-group">
											<div class="form-actions fluid">
												<div class="col-md-offset-3 col-md-9">
													<button type="button" class="btn blue" onclick="changeVehicleStatus()">Save</button>
													<button type="button" class="btn default" data-dismiss="modal"	aria-hidden="true">Cancel</button>
												</div>
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
	</form>
<script>
	google.maps.event.addDomListener(window, 'load', initializeMap);
</script>