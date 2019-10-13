<%@ taglib prefix="s" uri="/struts-tags"%>
<head>

<%-- <script src="https://maps.google.com/maps/api/js?sensor=false" --%>
<%-- 	type="text/javascript"></script> --%>
<script src="https://maps.googleapis.com/maps/api/js?client=gme-trimaxitinfrastructure&sensor=false&libraries=places,drawing&signature=CpSOeA5B6SzC2rxqlzd2JwenGX0="></script>
	<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script type="text/javascript" src="assets/vts/js/vts.js"></script>
</head>



<script>
$(document).ready(function() {
	getCordinatesvalue(this.value);
	});
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
		initialize();
		$("#vehicledetails").css("display", "block");
	   var vehicleID = document.getElementById("serialno").value;
		var startdate = document.getElementById("startdate").value;
		var enddate = document.getElementById("enddate").value;
		var orgname=document.getElementById("orgname").value;
// 		}
		if (vehicleID == 'ALL') {
			$("#vehicledetails").css("display", "none");
			
			var divId1 = document.getElementById("start");
			divId1.style.display='none';	
			plotVehicle();
		} else if (vehicleID != 0) {
			$("#vehicledetails").css("display", "block");
			
			
			getCordinates(vehicleID, 0, startdate, enddate,'Submit');
			$("#msg").html("");
		}
	}
	function getCordinatesvaluePB(value, timeframe) {
		initialize();
		$("#vehicledetails").css("display", "block");
		var vehicleID = document.getElementById("serialno").value;
		var startdate = document.getElementById("startdate").value;
		var enddate = document.getElementById("enddate").value;
		if(timeframe==0 || timeframe==''){
			alert("Please enter time frame");
			timeframe=0;
			enterIntervalForPlayback(value);			
		}
		if (vehicleID == 'ALL') {
			$("#vehicledetails").css("display", "none");
			
			var divId1 = document.getElementById("start");
			divId1.style.display='none';	
			plotVehicle();
		} else if (vehicleID != 0) {
			$("#vehicledetails").css("display", "block");
			
			
			getCordinatespb(vehicleID, 0, startdate, enddate,value,timeframe);
			$("#msg").html("");
		}
	}
	function enterIntervalForPlayback(idvalue) {
		var value = idvalue;
	    var timeframe = prompt("Please enter time frame in seconds", "1");	    
	    if (timeframe != null) {
	    	getCordinatesvaluePB(value, timeframe);
	    }
	}
</script>
<!-- <div class="page-content-wrapper"> -->
	<div class="page-content">
	
		<div class="row">
			<div class="col-md-6">
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
<!-- 				<h3 class="page-title"> -->
<%-- 					Bus status<small></small> --%>
<!-- 				</h3> -->
				<!-- END PAGE TITLE & BREADCRUMB-->
			</div>
		</div>
		<div class="portlet-body form">
			<div class="form-body"> 
			<label class="col-md-6 control-label"><b>Depot No.:</b> <font 
 					
						color="red"></font> <s:property value="orgname"/>
  					</label> 
  					<label class="col-md-6 control-label"><b>Vehicle No.: </b><font 
 					
						color="red"></font> <s:property value="vehicleNo"/>
  					</label> <br>
  					<label class="col-md-6 control-label"><b>Start Point:</b><font 
 					
						color="red"></font> <img src="assets/images/BusIconRedFlag.png" >
  					</label> 
  					<label class="col-md-6 control-label"><b>End Point: </b>
  					<font 
 					
						color="red"></font> <img src="assets/images/bus-map-icon.png" >
  					</label>
 				<div class="form-group"> 
<!--  				<div class="col-md-4"> -->
<!--  					<label class="col-md-6 control-label">Depot Name: <font -->
 					
<%-- 						color="red"></font> <s:property value="orgname"/><br> --%>
<!--  					</label>  -->
 					<input type="hidden" name="orgname" value='<s:property value="orgname"/>' id ="orgname">
<!--  					</div></div> -->
<!--  					<div class="form-group"> -->
<!--  					<div class="col-md-4"> -->
<!--  					<label class="col-md-6 control-label">Vehicle No: <font -->
<%-- 						color="red"></font> <s:property value="vehicleNo"/><br> --%>
						<input type="hidden" name="vehicleNo" value='<s:property value="vehicleNo"/>' id ="vehicleNo">
						
						 <input type="hidden" name="serialno" value='<s:property value="serialno"/>' id ="serialno">
						  <input type="hidden" name="startdate" value='<s:property value="startdate"/>' id ="startdate">
						   <input type="hidden" name="enddate" value='<s:property value="enddate"/>' id ="enddate">
<!--  					</label>  -->
<!--                 </div> -->
<!-- 					<div class="col-md-4">  -->
<%-- 						<s:select list="divisionlist" id="divisionlist" 
<%-- 							name="orgchart.org_chart_id" 
<%-- 							cssClass="select2_category form-control" onchange="getDepot(this.value)" onclick="getRefresh()"></s:select> 
<%--                    <select> --%>
                      
<%--                    </select> --%>
									
					</div> 
					
<!-- 					<div class="col-md-4"> -->
<%-- 						<select id="depotlist"   class="select2_category form-control" --%>
<%-- 							 onchange="getVehicle(this.value)"> --%>
<!-- 							 <option value="0">Depot Name</option> -->
<%-- 							 </select> --%>
					
<!-- 					</div> -->
<!-- 					<div class="col-md-4"> -->
<%-- 						<select  id="vehiclelist"  class="select2_category form-control" --%>
<%-- 							onchange="getCordinatesvalue(this.value)"> --%>
<!-- 							<option value="0">Vehicle Number</option> -->
<%-- 							</select> --%>
					
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
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
			
				<div class="form-group">
<!-- 				 <label class="control-label col-md-2">Start Date/ Time </label> -->
				
					<div class="col-md-4">
					
<!-- 						<div class="input-group date form_datetime"> -->
<!-- 							<input type="text" size="16" readonly name="startdate" -->
<%-- 								id="startdate" class="form-control" value='<s:property value="startdate"/>'> <span --%>
<%-- 								class="input-group-btn"> --%>
<!-- 								<button class="btn default date-set" type="button"> -->
<!-- 									<i class="fa fa-calendar"></i> -->
<!-- 								</button> -->
<%-- 							</span> --%>
<!-- 						</div> -->
					</div>
					
<!-- 					<label class="control-label col-md-2">End Date/ Time </label> -->
					<div class="col-md-4">
<!-- 					<div class="input-group date form_datetime"> -->
<%-- 						<input type="text" size="16" readonly name="enddate" id="enddate" value='<s:property value="enddate"/>' --%>
<%-- 							class="form-control"> <span --%>
<%-- 							class="input-group-btn"> --%>
<!-- 							<button class="btn default date-set" type="button"> -->
<!-- 								<i class="fa fa-calendar"></i> -->
<!-- 							</button> -->
<%-- 						</span> --%>
<!-- 					</div> -->
					</div>
					<div class="col-md-4">
<!-- 						<button type="button" class="btn default" value="Submit" name="buttonname" onClick="getCordinatesvalue(this.value)">Submit</button> -->
					
<!-- 						<button type="button" class="btn default" value="Submit" name="buttonname" onClick="enterIntervalForPlayback(this.value)">Playback</button> -->
					</div>
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
<!-- 			<div class="form-body"> -->
				
<!-- 				<div class="col-md-4"> -->
<!-- 				<label class="col-md-6 control-label">Auto Refresh <font -->
<!-- 					color="red">*</font> -->
<!-- 				</label> -->
<!-- 					<input type="checkbox" checked="checked" id="refreshID" -->
<!-- 						onclick="getRefresh()" /> -->
<!-- 					</div> -->
<!-- 				<div class="col-md-4">		 -->
<%-- 				<span id="msg"></span> --%>
<!-- 						<img src="assets/images/bus-map-icon.png" > Speed > 5 -->
<!-- 						<img src="assets/images/Icon-Bus-Orange.png" > Speed < 5 -->
<!-- 						<img src="assets/images/Icon-Bus-Red.gif" > Accident/Breakdown			 -->
				
<!-- 				</div> -->
<!-- 				    <img src="assets/images/Icon-Bus-Blue.png" > Stationary -->
<!-- 					<img src="assets/images/grey.png" > NRD -->
<!-- 					<img src="assets/images/fourbyfour.png"  -->
<!-- 						onclick="getjeepVehicle()" /><blink>Track Jeep Vehicles</blink> -->
<!-- 			</div> -->
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
<!-- </div> -->
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