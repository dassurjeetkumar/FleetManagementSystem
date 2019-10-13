<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link rel="stylesheet" type="text/css" 	href="<%=request.getContextPath()%>/assets/global/plugins/clockface/css/clockface.css" />
<link rel="stylesheet" type="text/css" 	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css" />
<link rel="stylesheet" type="text/css" 	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css" />
<link rel="stylesheet" type="text/css" 	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-colorpicker/css/colorpicker.css" />
<link rel="stylesheet" type="text/css" 	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" />
<link rel="stylesheet" type="text/css" 	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-datetimepicker/css/datetimepicker.css" />
<link rel="stylesheet" 	href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
<link rel="stylesheet" 	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="<%=request.getContextPath()%>/vehicle/keyBoardEvents.js" 	type="text/javascript"></script>

<style type="text/css">
.col-md-9,.col-md-8 {
	width: 30%;
}
</style>
</head>
<div class="page-content-wrapper">
	<div class="page-content">
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
					Vehicle
				</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-bars"></i>Assign Device
						</div>
					</div>
					<div class="portlet-body">
						<div class="portlet-body form">
							<form action="assignDeviceToVehilce" class="form-horizontal form-row-seperated" method="post">
								<b>
									<font color="green"> <s:actionmessage/></font>
									<font color="red"> <s:actionerror/></font>
								</b>
								<input type="hidden" name="vehicle.vehicleId" 	id="vehicleId" value="<s:property value='vehicle.vehicleId'/>" /> 
								<input	type="hidden" name="isDeviceAllocation" value="1" />
								<div class="form-body">
									<div class="form-group">
										<label class="control-label col-md-3">
											 Vehicle No  
										</label>
										<div class="col-md-9">
											<div class="input-group input-medium" style="width: auto" >
												<input type="text" class="form-control" readonly value='<s:property value="vehicle.vehicleRegistrationNumber"/>' />
											</div>		
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">
											Device Type <span class="required"> * </span>
										</label>
										<div class="col-md-9">
											<select name="device.device.device_type_id" class="select2_category form-control" id="deviceType" onchange="getDeviceNumbers()">
												<s:iterator  value="deviceTypeList"> 
													<option id="<s:property value='device_type_name'/>" value="<s:property value='device_type_id'/>"> 
														<s:property value='device_type_name'/> 
													</option>
												</s:iterator>
											</select>
											<span class="help-block" style="color: red" >
											 	<s:property value="%{fieldErrors.get('device.device.device_type_id').get(0)}" />
											</span>
										</div>
										<script>
											 /* var devType = "<s:property value='device.device.device_type_name'/>";
											 alert(devType);
											if(devType!=""){ */
											 	document.getElementById("VTU").selected = true;
											/*  }else{
												document.getElementById("devType1").selected = true;
											}  */
										</script>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">
											Device Serial Number <span class="required"> * </span>
										</label>
										<div class="col-md-9">
											<select name="device.device_id" class="select2_category form-control" id="devNumbers" >
												<option id="0">Select</option>
											</select>
											<span class="help-block" style="color: red">
												 <s:property value="%{fieldErrors.get('device.device_id').get(0)}" />
											</span>
										</div>
										
										<%-- <script>
											var devType = "<s:property value='device.device_id'/>";
											if(devType!=""){
												document.getElementById("dev"+devType).selected = true;
											}
										</script> --%>
									</div>
									<div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9">
											<button type="submit" class="btn blue">Save</button>
											<button type="button" class="btn default" onclick="callCancell()">Cancel</button>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

</body>
<head>
<script>
	function callCancell() {
		window.location.href = 'VehicleDetails.action';
	}
	
	getDeviceNumbers();
	function getDeviceNumbers()
	{
		$('#select2-chosen-2').html("Select");
		var e = document.getElementById("deviceType");
		var vehicleId = document.getElementById("vehicleId").value;
		
		var strUser = e.options[e.selectedIndex].value;
		var len= document.getElementById('devNumbers').options.length;
		       $.ajax({
		           type: "post",
		          async:false,
		           url: '<%=request.getContextPath()%>/DeviceNumbers?devType='+strUser+'&vehicleId='+vehicleId,
		           success: function(result) {
		               document.getElementById('devNumbers').innerHTML=result;
		               
		               var aaa="<s:property  value='device.device_id'/>";
					   	//alert(aaa);
					   	if(aaa=!"" && aaa!=""){
					   		 document.getElementById('org0').selected=true;
					   	}
		           }
		      });
		     
	}
	
	
	/* var devNum = "<s:property value='allocatedDeviceToVehicle'/>"; 
	//alert(devNum);
	document.getElementById(devNum).selected = true; */
</script>