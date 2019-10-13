
<%@ taglib prefix="s" uri="/struts-tags"%>
<%-- <script src="https://code.jquery.com/jquery-1.11.3.min.js" type="text/javascript"></script> --%>
<script src="assets/admin/pages/scripts/table-managed-vehicle.js"
	type="text/javascript"></script>
<script src="assets/admin/pages/scripts/charts.js"
	type="text/javascript"></script>
	<script src="assets/vts/js/vts.js" type="text/javascript"></script>
	<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places,drawing"></script>
<%-- <script src="assets/vts/js/vts.js" type="text/javascript"></script> --%>


<script type="text/javascript" src="//www.google.com/jsapi"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
	
	</script>

<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
<script type="text/javascript">
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
	</script>
<script>
var intervalID=null;


function callSos(){
	
	setInterval(function(){ 
	window.location.href ='sosVehiclesDashboard.action';
	}, 60000);
	
	
}
// function callSos(){
window.onload=callSosData;

	function callSosData(){
	
	$('#SOSList').dataTable({
// 		"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
		// per
		// page
		// values
		// here
// 		],
		// set the initial value
// 		"iDisplayLength" : 5,
		"sAjaxSource" : "sosVehiclesDashboardAjax.action",
// 		"sPaginationType" : "bootstrap",
		"bDestroy" : true,

		/* "oLanguage" : {
			"sLengthMenu" : "_MENU_ records",
			"oPaginate" : {
				"sPrevious" : "Prev",
				"sNext" : "Next"
			} */
// 		},
		"aoColumnDefs" : [ {
			'bSortable' : false,
			'aTargets' : [ 0 ]
		} ]
	});
	jQuery('#SOSList_wrapper .dataTables_filter input').addClass(
			"form-control input-small input-inline"); // modify
	// table
	jQuery('#SOSList_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline"); // modify
// }
// 	 if (intervalID != null) {
// 			clearInterval(intervalID);
// 		   }
// 		    intervalID = setInterval(function() {
// 		    	callSos();
// 			}, 50000);
	callSos();
}			

</script>

<script>
	google.maps.event.addDomListener(window, 'load', initialize);
	$("#mnu_header li").removeClass("classic-menu-dropdown active");
	$("#mnu_sos").addClass("classic-menu-dropdown active");
	$("#mnu_header li").addClass("classic-menu-dropdown");
	$("#sos_selected").addClass("selected");

	
</script>



<div class="page-content-wrapper">
	<div class="page-content">
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
				<h3 class="page-title">
					SOS Dashboard (Refresh in 60 sec)
				</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN EXAMPLE TABLE PORTLET-->
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i>View Total SOS : 
							<s:property value="totalSos" />
<%-- 							---Total Vehicles:<span id='tot' name='tot'></span>  --%>
						</div>
							<!-- <div class="actions">
						<div class="btn-group">
							 &nbsp;&nbsp;<a href="#" class="btn blue" onclick="callSos()"> <i
								class="fa fa-pencil"></i>Refresh
							</a> &nbsp;&nbsp; 
						</div>
							</div>		 -->
						<div align="right">
 						</div> 
					</div>
					
					<div class="portlet-body">
						<form action="" method="post">
						<B><font color="green"><s:actionmessage /></font></B>
							<s:if test="hasActionErrors()">
								<div class="alert alert-danger">
								
									<button class="close" data-close="alert"></button>
								<span id="errorMsg" style="color:red;word-wrap: break-word;"><s:actionerror/></span>
								</div>
								</s:if>
								<b>
							</b>
							<input type="hidden" id="" value="" name="vehicleTypeId">
							<table class="table table-striped table-bordered table-hover"
								id="SOSList" >
								<thead>
									<tr>
									<th>Sr.No.</th>
										<th>Vehicle No.</th>
										<th>Depot No.</th>
										<th>Device Sim No.</th>
										<th>Schedule No.</th>
<!-- 										<th>Call time</th> -->
										<th>Total SoS Count</th>
										<th>Call Epbx</th>
										<th>Driver Token No.</th>
										<th>Conductor Token No.</th>
										<th>Conductor Mob No.</th>
										<th>Reason</th>
										
									</tr>
								</thead>
								<tr>
							</table>
						</form>
						
						
<form action="#" class="form-horizontal ">
<div style="display: none;" id="mymodal18" class="modal fade"
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
						
							<input type="hidden" name="shift" id="shift" />
	
							<div>
							
								<div class="portlet solid white">
									<div class="form-group">
										<label class="col-md-3 control-label"></label>
									</div>
									<div class="form-group">
									<table id ="tableid1">		
									<div>
										<div class="form-group">
											<label class="col-md-3 control-label">Device Id </label>
											<div class="col-md-4">
												<input type="text" class="form-control" id="device_id4"
													name="device_id4" readonly="readonly" value=''></input>
											</div>
										</div>										
											<div class="form-group">
												<label class="col-md-3 control-label">Vehicle No.</label>
												<div class="col-md-4">
													<input type="text" class="form-control" id="vehicleNo4"
														name="vehicleNo4" readonly="readonly" value=''></input>
												</div>
											</div>
											<div class="form-group">
											
												<label class="col-md-3 control-label">Reason<font color="red">*</font></label>
												<div class="col-md-4" id="reason">
													<select id="vehicle_reason4"
														class="select1_category form-control" onChange="checkType4()"
														name="vehicle_reason4">
														<option value="0">-----Select----</option>
														<option value="1">Bus breakdown</option>
														<option value="2">Accident</option>
														<option value="3">Others</option>
													</select>
												</div>
											</div>
										
										
											<div class="form-group" style="display: none" id="desc4">
												<label class="col-md-3 control-label">Description<font color="red">*</font></label>
												<div class="col-md-4">
													<textarea class="form-control" id="description4"
														maxlength="100" name="notes"></textarea>
												</div>
											</div>
											</table>	
										</div>
										<br>
										<br>
										<div class="form-group">
											<div class="form-actions fluid">
												<div class="col-md-offset-3 col-md-9">
													<button type="button" class="btn blue" onclick="getSOSReason()">Save</button>
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
	
<!-- 	<div class="portlet-body" id="sosdetails" style="display: none">
										<div style="text-align:center">
										<table class="table table-striped table-bordered table-hover"
											id="sosDetails">
											<thead>
												<tr>
													<th>Sr.No.</th>
													<th>Vehicle No.</th>
													 <th>Time</th> 
													 <th>Status</th> 
													 <th>Incident</th> 
												</tr>
											</thead>
										</table>
									</div>
									</div>
									
									 -->
									
				<div style="display: none;" id="mymodal1" class="modal fade"
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
									<div class="portlet-body" id="sosdetails" style="display: none">
										<div style="text-align:center">
										<table class="table table-striped table-bordered table-hover"
											id="sosDetails">
											<thead>
												<tr>
													<th>Sr.No.</th>
													<th>Vehicle No.</th>
													 <th>Time</th> 
													 <th>Status</th> 
													 <th>Incident</th> 
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
									
									
									
				
<form action="#" class="form-horizontal ">
<div style="display: none;" id="mymodal2" class="modal fade"
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
								<!-- <div class="form-group">
											<label class="col-md-3 control-label">sch Id </label>
											<div class="col-md-4">
												<input type="text" class="form-control" id="scheduleId"
													name="scheduleId" readonly="readonly" value=''></input>
											</div>
										</div>	  -->
							
								<div class="portlet solid white">
									<div class="form-group">
										<label class="col-md-3 control-label"></label>
									</div>
									<div class="form-group">
									<table id ="tableid1">		
									<div>
									<input type="hidden" name="scheduleId" id="scheduleId" /></div>						
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
											</table>	
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
		
 				</div></div></div> 
			</div>
		</div>
	</div>
</div>
<script>

function getSOSReason(){
	var desc="";
	var vehicleId=document.getElementById('vehicleNo4').value;
	var reason=document.getElementById('vehicle_reason4').value;
	var text = document.getElementById('vehicle_reason4').selectedOptions[0].text;
	var device=document.getElementById('device_id4').value;
	
	if(reason == 0){
		bootbox.alert("Please Enter Reason for SOS");
		return false;
	}
	
	if(reason ==3){
		 desc=document.getElementById('description5').value;
	}else{
		desc = "";
	}
	
	if(reason == 3 && desc =="" ){
		bootbox.alert("Please Specify the Reason");
		return false;
	}
// alert(desc+"  "+text+"  ");
	
	
$.ajax({
    type: "get",
    url: '<%=request.getContextPath()%>/insertSOSReason.action?vehicleId='+device+'&reason='+reason+'&reasonName='+text+'&desc='+desc,
    success: function(result) {
 	  
 	   alert("Reason inserted Successfully");
//          document.getElementById('northDivisioneData').innerHTML=result;

     }
}); 
	
}

function checkType4(){
	// alert("  "+id);
	var val=document.getElementById("vehicle_reason4").value
	// alert(val);
	if(val==3){
		$(desc4).show();
	}else {
		$(desc4).hide();
	}
	}
	
	
	
function insertDataIntoVtsSos(deviceId,sosId,istDate){
	//alert(deviceId+"  "+sosId+"  "+istDate);
	
	//End Code
	

// 	 $.ajax({
	        
// //         type: "post",
<%-- //         url: '<%=request.getContextPath()%>/insertDataIntoVtsSos.action?deviceId='+deviceId+'&sosId='+sosId+'&istDate='+istDate, --%>
//          url : "insertDataIntoVtsSosForCall",
//  		data : "deviceId=" + deviceId + "&sosId=" + sosId + "&istDate="
//  				+ istDate ,
//  		method : "POST",
// // 		type:"text",
//          success: function(result) {
//          	$('#pageLoader').hide();
//          	alert("SOS call");
//          	console.log(result);
//          },
//      	/*error : function(e){
// 			alert('error   '+e.toSource());
// 		}*/

//      });
	
	
$.ajax({
    type: "get",
    url: '<%=request.getContextPath()%>/insertDataIntoVtsSosForCall.action?deviceId='+deviceId+'&sosId='+sosId+'&istDate='+istDate,
    success: function(result) {
 	   $('#pageLoader').hide();
 	   alert("Call initiated ! Successfully");
//          document.getElementById('northDivisioneData').innerHTML=result;
//          fixHeader();
         
     }
}); 
	
}
	

/* 
$(document).ready(function() {
	window.history.pushState("","", "sosVehiclesDashboard.action");
	var w=$('#errorMsg span').html();
	   //alert(w);
	    w=w.replace(/@/g,"<br>");

	   $('#errorMsg').html(''+w+'');
});
function isEligibleForOpertion(id){
	 var isEligible = $('#isRocordEligible'+id).val();
	 if(isEligible == undefined || isEligible=='Y'){
		 return true;
	 }else{
		 return false;
	 }
} */
</script>



