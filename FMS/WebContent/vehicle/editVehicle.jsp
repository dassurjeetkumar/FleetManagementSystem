<%@page import="com.trimax.its.vehicle.model.Vehicle"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<head>
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/assets/global/plugins/clockface/css/clockface.css" />
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css" />
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css" />
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-colorpicker/css/colorpicker.css" />
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" />
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-datetimepicker/css/datetimepicker.css" />
<link rel="stylesheet"	href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
<link rel="stylesheet"	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="<%=request.getContextPath()%>/vehicle/keyBoardEvents.js" type="text/javascript"></script>

<style type="text/css">

.col-md-9,.col-md-8 {
	width: 30%;
}
.tab-pane { /* border-right: 1px solid #dddddd;
	border-left: 1px solid #dddddd;
	border-bottom: 1px solid #dddddd; */
}
.help-block,ul,li {
	list-style: none;
}

</style>
<script>
	function callEditDocking() {
		var val;
		$(function() {
			var val = [];
			$(':checkbox:checked').each(function(i) {
				val = $(this).val();
			});
			var chckbxCount = $("input:checked").length;
			if (chckbxCount > 0 && chckbxCount > 1) {
				alert("Select only one vehicle...!")
				return false;
			} else if (chckbxCount > 0 && chckbxCount == 1) {
				document.forms[0].action = "EditDockingAction.action?dockingId="+ val;
				document.forms[0].submit();
			} else {
				alert("Please select one vehicle");
				return false;
			}
		});
	}
	function callCancell() {

		window.location.href = 'VehicleDetails.action';
	}
</script>
</head>
<div class="page-content-wrapper">
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "VehicleDetails.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
int rollid=accessrightdao.getroleid(usrsessionid);
%>

	<div class="page-content">
	<%if (access.equalsIgnoreCase("Y")){%>
	<%if (create.equalsIgnoreCase("Y")){%>
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
							<i class="fa fa-bars"></i>Edit Vehicle
						</div>
					</div>
					<div class="portlet-body">
						<div class="portlet-body form">
							<b>
								<font color="green"> <s:actionmessage/></font>
							<span id="errorMsg" style="color:red;word-wrap: break-word;" ><s:actionerror/></span>
							</b>
							<form action="SaveEditVehicleDetails" class="form-horizontal form-row-seperated" method="post"><s:token/>	
								<div class="form-body">
								<%if(rollid==5){ %>
									<div class="form-group">
										<s:hidden name="editedVehicleId" value="%{vehicle.vehicleId}" />
										<s:hidden name="vehicle.vehicleId" value="%{vehicle.vehicleId}" />
										<label class="control-label col-md-3">Registration Number<span class="required"> * </span></label>
										<div class="col-md-3">
											<input type="text" class="form-control" name="vehicle.vehicleRegistrationNumber"  value="<s:property value="vehicle.vehicleRegistrationNumber"/>" maxlength="12"/>
											<span class="help-block" style="color: red"> 
												<s:property	value="%{fieldErrors.get('registrationNumber').get(0)}" />
											</span>
										</div>
<!-- 										<label	class="control-label" style="color:#3b4455"><i>(KAXX-XX-XXXX)</i></label> -->
									</div>
								<%}else{ %>
									<div class="form-group">
										<s:hidden name="editedVehicleId" value="%{vehicle.vehicleId}" />
										<s:hidden name="vehicle.vehicleId" value="%{vehicle.vehicleId}" />
										<label class="control-label col-md-3">Registration Number<span class="required"> * </span></label>
										<div class="col-md-3">
											<input type="text" class="form-control" name="vehicle.vehicleRegistrationNumber"  value="<s:property value="vehicle.vehicleRegistrationNumber"/>" maxlength="12" readonly/>
											<span class="help-block" style="color: red"> 
												<s:property	value="%{fieldErrors.get('registrationNumber').get(0)}" />
											</span>
										</div>
<!-- 										<label	class="control-label" style="color:#3b4455"><i>(KAXX-XX-XXXX)</i></label> -->
									</div>
									<%}%>
									<div class="form-group">
										<label class="col-md-3 control-label">Organization Type<span class="required"> * </span></label>
										<div class="col-md-3">
										<select id="unit_type_id" name="vehicle.depotOrUnit.orgType.org_type_id" class="select2-container select2_category form-control"  onchange="getUnitNames()">
											<s:iterator value="orgTypeList" status="aaa">
												<option id="org<s:property   value="org_type_id" />" value='<s:property   value="org_type_id" />'>
													<s:property value="orgType" />
												</option>
											</s:iterator>
										</select>
										<script>
											var orgType ="<s:property value='vehicle.depotOrUnit.orgType.org_type_id'/>";
											if(orgType!=""){
												if(document.getElementById("org"+orgType)!=null)
												document.getElementById("org"+orgType).selected= true;
											}
										</script>
											<span class="help-block" style="color: red"> 
												<s:property value="%{fieldErrors.get('org_type_id').get(0)}"/>
											</span>
										</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">Organization Unit Name<span class="required"> * </span> </label>
									<div class="col-md-3">
										<select class="select2_category form-control" name="vehicle.depotOrUnit.org_chart_id" id="depot">
												<option value="0">Select</option>
										</select>  
										<span class="help-block" style="color: red"> 
										<s:property value="%{fieldErrors.get('orgName').get(0)}" />
									</span>
									</div>
								</div>
					
								<div class="form-group">
									<label class="control-label col-md-3">Progressive	Running KM</label>
									<div class="col-md-3">
										<s:textfield cssClass="form-control" name="vehicle.progressingKMs"	value='%{vehicle.progressingKMs}' maxlength="7"	onkeypress="onlyIntAndDot(event)" />
										<span class="help-block"style="color: red"> 
											<s:property	value="%{fieldErrors.get('progressingRunningKms').get(0)}" />
										</span>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">Progressive Schedule KM</label>
									<div class="col-md-3">
										<s:textfield cssClass="form-control" name="vehicle.scheduleKMs"	value='%{vehicle.scheduleKMs}' maxlength="7" onkeypress="onlyIntAndDot(event)" />
										<span class="help-block"	style="color: red"> 
											<s:property value="%{fieldErrors.get('progressingScheduleKms').get(0)}" />
										</span>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">Vehicle Type <span class="required"> * </span></label>
									<div class="col-md-3">
										<select id="s2id_autogen3" name="vehicle.vehicleType.vehicle_type_id" class="select2-container select2_category form-control">
											<s:iterator value="vehicleTypeList" status="aaa">
												<option	id="vehicleType<s:property value="vehicle_type_id" />"value='<s:property value="vehicle_type_id" />'>
													<s:property value="vehicle_type_name" />
												</option>
											</s:iterator>
										</select>
										<script>
											var vehicleType = "<s:property value='vehicle.vehicleType.vehicle_type_id'/>";
											if (vehicleType != "") {
												if(document.getElementById("vehicleType"+ vehicleType)!=null)
												document.getElementById("vehicleType"+ vehicleType).selected = true;
											}
										</script>
										<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('vehicle.vehicleType.vehicle_type_id').get(0)}" />
										</span>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3"> Brand Type <span class="required"> * </span> </label>
									<div class="col-md-3">
										<select id="s2id_autogen3" name="vehicle.brandType.brand_type_id" class="select2-container select2_category form-control">
											<s:iterator value="brandTypeList" status="aaa">
												<option id='brand<s:property value="brand_type_id"/>' value='<s:property value="brand_type_id" />'>
													<s:property value="brand_type_name" />
												</option>
											</s:iterator>
										</select>
										<script>
											var brandType = "<s:property  value='vehicle.brandType.brand_type_id'/>";
											if (brandType != "") {
												if(document.getElementById("brand"+ brandType)!=null)
												document.getElementById("brand"+ brandType).selected = true;
											}
										</script>
										<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('vehicle.brandType.brand_type_id').get(0)}" />
										</span>
									</div>
								</div>
									<div class="form-group">
										<label class="control-label col-md-3">A/C Availability <span class="required"> * </span>
										</label>
										<div class="col-md-3">
											<select class="select2_category form-control"name="vehicle.acAvailability">
												<option id="ac_yes" value="YES">YES</option>
												<option id="ac_no" value="NO">NO</option>
											</select>
											<script>
												var avialability = "<s:property value='vehicle.acAvailability'/>";
												if (avialability != undefined) {
													if (avialability == "YES") {
														document.getElementById("ac_yes").selected = true;
													} else {
														document.getElementById("ac_no").selected = true;
													}
												}
											</script>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">Make <span class="required"> * </span></label>
										<div class="col-md-3">
											<select id="s2id_autogen3" name="vehicle.makeType.make_type_id"	class="select2-container select2_category form-control">
												<s:iterator value="makeTypeList" status="aaa">
													<option id='make<s:property value="make_type_id"/>'value='<s:property value="make_type_id" />'>
														<s:property value="make_type_name" />
													</option>
												</s:iterator>
											</select>
											<script>
											var makeType = "<s:property  value='vehicle.makeType.make_type_id'/>";
											if (makeType != "") {
												if(document.getElementById("make"+ makeType)!=null)
												document.getElementById("make"+ makeType).selected = true;
											}
											</script>
											<span class="help-block" style="color: red">
												<s:property value="%{fieldErrors.get('vehicle.makeType.make_type_id').get(0)}" />
											</span>
										</div>
									</div>
									
												<div class="form-group">
										<label class="control-label col-md-3">Norm <span class="required"> * </span></label>
										<div class="col-md-3">
											<select id="norm" name="vehicle.norm" class="select2-container select2_category form-control">
											<option value="0">--Select--</option>
											<option value="BS-I">BS-I</option>
											<option value="BS-II">BS-II</option>
											<option value="BS-III">BS-III</option>
											<option value="BS-IV">BS-IV</option>
										
									</select>
											<script>
											var normtype = "<s:property  value='vehicle.norm'/>";
											//alert(normtype);
											 if (normtype != "") {
												 /* alert(normtype);
												 if(document.getElementById(norm)!=null)
												document.getElementById(norm).selected = true; 
													
											}  */
											//document.getElementsByName("normm")[0].value=normtype;
										    $('select[name="vehicle.norm"]:first').val(normtype); 
	                                           }
											</script>
											<span class="help-block" style="color: red">
												<s:property value="%{fieldErrors.get('norm').get(0)}" />
											</span>
										</div>
									</div>
									
									<div class="form-group">
										<label class="control-label col-md-3">Wheel Base(inch) <span class="required"> * </span></label>
										<div class="col-md-3">
											<select id="s2id_autogen3" name="vehicle.modelType.model_type_id" class="select2-container select2_category form-control">
												<s:iterator value="modelTypeList" status="aaa">
													<option id="model<s:property value="model_type_id"/>"value='<s:property value="model_type_id" />'>
														<s:property value="model_type_name" />
													</option>
												</s:iterator>
											</select>
											<script>
											var modelType = "<s:property  value='vehicle.modelType.model_type_id'/>";
												if (modelType != "") {
													if(document.getElementById("model"+ modelType)!=null)
													document.getElementById("model"+ modelType).selected = true;
												}
											</script>
											<span class="help-block" style="color: red"> 
												<s:property	value="%{fieldErrors.get('vehicle.modelType.model_type_id').get(0)}" />
											</span>
										</div>
									</div>
					<%-- 				<div class="form-group">
										<label class="control-label col-md-3">Body Type <span class="required"> * </span></label>
										<div class="col-md-3">
											<select id="s2id_autogen3" name="vehicle.bodyType.body_type_id"	class="select2-container select2_category form-control">
												<s:iterator value="bodyTypeList" status="aaa">
													<option id='body<s:property value="body_type_id"/>'	value='<s:property value="body_type_id" />'>
														<s:property value="body_type_name" />
													</option>
												</s:iterator>
											</select>
											<script>
											var bodyType = "<s:property  value='vehicle.bodyType.body_type_id'/>";
											if (bodyType != "") {
												if(document.getElementById("body"+ bodyType)!=null)
												document.getElementById("body"+ bodyType).selected = true;
											}
											</script>
											<span class="help-block" style="color: red"> 
												<s:property	value="%{fieldErrors.get('vehicle.bodyType.body_type_id').get(0)}" />
											</span>
										</div>
									</div> --%>
									
									
												<div class="form-group">
								<label class="control-label col-md-3">Floor Height(mm)<span class="required"> * </span></label>
								<div class="col-md-3">
									<select id="floorrHeigt" name="vehicle.floorrHeigt" class="select2-container select2_category form-control">
											<option value="0">--Select--</option>
											<option value="400">400</option>
											<option value="650">650</option>
											<option value="740">740</option>
											<option value="900">900</option>
											<option value="1100">1100</option>
											
										
									</select>
						
									<script>
										var floorhtype = "<s:property  value='vehicle.floorrHeigt'/>";
										if (floorhtype != "") {
											//document.getElementById("make"+ floorhtype).selected = true;
											 $('select[name="vehicle.floorrHeigt"]:first').val(floorhtype);
										}
									</script>
									<span class="help-block" style="color: red"> 
										<s:property	value="%{fieldErrors.get('floorrHeigt').get(0)}" />
									</span>
								</div>
							</div>
							
							
											<div class="form-group">
								<label class="control-label col-md-3">HP(Horse Power)<span class="required"> * </span></label>
								<div class="col-md-3">
									<select id="hp" name="vehicle.hp" class="select2-container select2_category form-control">
											<option value="0">--Select--</option>
											<option value="120">120</option>
											<option value="124">124</option>
											<option value="130">130</option>
											<option value="147">147</option>
											<option value="160">160</option>
											<option value="165">165</option>
											<option value="180">180</option>
											<option value="275">275</option>
											<option value="290">290</option>
									</select>
								
									
									<script>
										var horsetype = "<s:property  value='vehicle.hp'/>";
										if (horsetype != "") {
											//document.getElementById("make"+ horsetype).selected = true;
											 $('select[name="vehicle.hp"]:first').val(horsetype);

										}
									</script>
									<span class="help-block" style="color: red"> 
										<s:property	value="%{fieldErrors.get('hp').get(0)}" />
									</span>
								</div>
							</div>
							
							
														<div class="form-group">
								<label class="control-label col-md-3">Seating Capacity<span class="required"> * </span></label>
								<div class="col-md-3">
									<select id="seat_Capacity" name="vehicle.seat_Capacity" class="select2-container select2_category form-control">
											<option value="0">--Select--</option>
											<option value="30+1">30+1</option>
											<option value="31+1">31+1</option>
											<option value="32+1">32+1</option>
											<option value="35+1">35+1</option>
											<option value="41+1">41+1</option>
											<option value="42+1">42+1</option>
											<option value="44+1">44+1</option>
											<option value="52+1">52+1</option>
									</select>
								
									
									<script>
										var seattype = "<s:property  value='vehicle.seat_Capacity'/>";
										if (seattype != "") {
											//document.getElementById("make"+ seattype).selected = true;
											$('select[name="vehicle.seat_Capacity"]:first').val(seattype);
										}
									</script>
									<span class="help-block" style="color: red"> 
										<s:property	value="%{fieldErrors.get('seat_Capacity').get(0)}" />
									</span>
								</div>
							</div>
							
															<div class="form-group">
								<label class="control-label col-md-3">Unladen Weight(kg)<span class="required"> * </span></label>
								<div class="col-md-3">
									<select id="UnladenWeight" name="vehicle.UnladenWeight" class="select2-container select2_category form-control">
								<option value="0">--Select--</option>
											<option value="7000 to 7500">7000 to 7500</option>
											<option value="7501 to 8000">7501 to 8000</option>
											<option value="8001 to 9000">8001 to 9000</option>
											<option value="9001 to 9500">9001 to 9500</option>
											<option value="9501 to 10000">9501 to 10000</option>
											<option value="10001 to 10500">10001 to 10500</option>
											<option value="10501 to 11000">10501 to 11000</option>
											<option value="11001 to 11500">11001 to 11500</option>
									</select>
								
									
									<script>
										var unlwe = "<s:property  value='vehicle.UnladenWeight'/>";
										if (unlwe != "") {
											//document.getElementById("make"+ seattype).selected = true;
											$('select[name="vehicle.UnladenWeight"]:first').val(unlwe);
										}
									</script>
									<span class="help-block" style="color: red"> 
										<s:property	value="%{fieldErrors.get('UnladenWeight').get(0)}" />
									</span>
								</div>
							</div>
							
									
								<%-- 	<div class="form-group">
										<label class="control-label col-md-3">Docking Planning Date </label>
										<div class="col-md-9">
											<div class="input-group input-medium date date-picker"	style="width: auto" data-date-format="dd-mm-yyyy" data-date-viewmode="years" data-date-start-date="+0d">
												<s:textfield cssClass="form-control" readonly="true" name="vehicle.dockingPlanningDateString" value="%{vehicle.dockingPlanningDateString}" />
												<div class="input-group input-medium date date-picker"	data-date-format="dd-mm-yyyy" data-date-viewmode="years" >
										<input type="text" class="form-control" name="vehicle.dockingPlanningDateString"	readonly value="<s:property value='vehicle.dockingPlanningDateString'/>">
										
												<span class="input-group-btn">
													<button class="btn default" type="button">
														<i class="fa fa-calendar"></i>
													</button>
												</span>
											</div>
											<span class="help-block" style="color: red"> 
												<s:property	value="%{fieldErrors.get('dockingPlanningDate').get(0)}" />
											</span>
										</div>
									</div> --%>
										<div class="form-group">
										<label class="control-label col-md-3"> Docking Km <span class="required"> * </span></label>
										<div class="col-md-3">
											<input type="text" class="form-control" name="vehicle.docking_Kms" value="<s:property value='vehicle.docking_Kms'/>" />
											<span class="help-block" style="color: red"> 
												<s:property	value="%{fieldErrors.get('docking_Kms').get(0)}" />
											</span>
										</div>
									</div>
						<%-- 			<div class="form-group">
										<label class="control-label col-md-3">Service Type <span class="required"> * </span></label>
										<div class="col-md-3">
											<select id="s2id_autogen3" name="vehicle.serviceType.service_type_id" class="select2-container select2_category form-control">
												<s:iterator value="serviceTypeList" status="aaa">
													<option id="service<s:property   value="service_type_id" />"value='<s:property   value="service_type_id" />'>
														<s:property value="service_type_name" />
													</option>
												</s:iterator>
											</select>
											<script>
											var serviceType = "<s:property   value='vehicle.serviceType.service_type_id'/>";
											if (serviceType != "") {
												if(document.getElementById("service"+serviceType)!=null)
												document.getElementById("service"+serviceType).selected = true;
											}
											</script>
											<span class="help-block" style="color: red"> 
												<s:property	value="%{fieldErrors.get('vehicle.serviceType.service_type_id').get(0)}" />
											</span>
										</div>
									</div> --%>
							<%if(rollid==5){ %>
									<div class="form-group">
										<label class="control-label col-md-3"> Chassis Number <span class="required"> * </span></label>
										<div class="col-md-3">
											<input type="text" class="form-control" name="vehicle.chasisNumber" value='<s:property value='vehicle.chasisNumber'/>' maxlength="50"/>
											<span class="help-block" style="color: red"> 
												<s:property	value="%{fieldErrors.get('vehicle.chasisNumber').get(0)}" />
											</span>
										</div>
									</div>
									<%}else{ %>
										<div class="form-group">
										<label class="control-label col-md-3"> Chassis Number <span class="required"> * </span></label>
										<div class="col-md-3">
											<input type="text" class="form-control" name="vehicle.chasisNumber" value='<s:property value='vehicle.chasisNumber'/>' maxlength="50" readonly/>
											<span class="help-block" style="color: red"> 
												<s:property	value="%{fieldErrors.get('vehicle.chasisNumber').get(0)}" />
											</span>
										</div>
									</div>
									<%} %>
									<div class="form-group">
										<label class="control-label col-md-3"> Engine Number <span class="required"> * </span></label>
										<div class="col-md-3">
											<input type="text" class="form-control" name="vehicle.engineNumber" value='<s:property value='vehicle.engineNumber'/>' maxlength="50"/>
											<span class="help-block" style="color: red"> 
												<s:property	value="%{fieldErrors.get('engineNumber').get(0)}" />
											</span>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"> No. of Wheels <span class="required"> * </span></label>
										<div class="col-md-3">
											<input type="text" class="form-control" name="vehicle.wheelsCount" value="<s:property value='vehicle.wheelsCount'/>"	maxlength="3" onkeypress="onlyIntAndDot(event)" />
											<span class="help-block" style="color: red"> 
												<s:property	value="%{fieldErrors.get('wheelCount').get(0)}" />
											</span>
										</div>
									</div>
						<%-- 						<div class="form-group">
									<label class="control-label col-md-3">FC Expiry Date <span	class="required"> * </span></label>
									<div class="col-md-9">
										<div class="input-group input-medium date date-picker"	style="width: auto" data-date-format="dd-mm-yyyy" data-date-viewmode="years" data-date-start-date="+0d">
												<input type="text" class="form-control" readonly value="<s:property value="vehicle.fcExpiryDateString"/>" name="vehicle.fcExpiryDateString" /> 
												<span class="input-group-btn">
													<button class="btn default" type="button">
														<i class="fa fa-calendar"></i>
													</button>
												</span>
										</div>
											<span class="help-block" style="color: red">
												<s:property	value="%{fieldErrors.get('editedFcExpiryDate').get(0)}" />
											</span>
									</div>
								</div> --%>
									<div class="form-group">
									<label class="control-label col-md-3">FC Renewal Date <span	class="required"> *</span></label>
									<div class="col-md-9">
										<div class="input-group input-medium date date-picker"	style="width: auto" data-date-format="dd-mm-yyyy" data-date-viewmode="years" data-date-start-date="+0d">
												<input type="text" class="form-control" readonly value="<s:property value="vehicle.FcRenewalDateString"/>" name="vehicle.FcRenewalDateString" /> 
												<span class="input-group-btn">
													<button class="btn default" type="button">
														<i class="fa fa-calendar"></i>
													</button>
												</span>
										</div>
											<span class="help-block" style="color: red">
												<s:property	value="%{fieldErrors.get('editedFcExpiryDate').get(0)}" />
											</span>
									</div>
								</div>
								
									<div class="form-group">
								<label class="control-label col-md-3">Procured Date <span class="required"> </span></label>
								<div class="col-md-3">
									<div class="input-group input-medium date date-picker"	data-date-format="dd-mm-yyyy" data-date-viewmode="years" >
										<input type="text" class="form-control" name="vehicle.procuredDateString"	readonly value="<s:property value='vehicle.procuredDateString'/>">
										
										<span class="input-group-btn">
											<button class="btn default" type="button">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
									</div>
									<span class="help-block" style="color: red"> <s:property
											value="%{fieldErrors.get('proceduredDate').get(0)}" /></span>
								</div>
							</div>
									<div class="form-group">
										<label class="control-label col-md-3">Operational Date(Doo) <span class="required"> * </span></label>
										<div class="col-md-9">
											<%-- <div class="input-group input-medium date date-picker"style="width: auto" data-date-format="dd-mm-yyyy"	data-date-viewmode="years" data-date-start-date="+0d">
												<s:textfield cssClass="form-control" readonly="true" name="vehicle.operationalDateString" value="%{vehicle.operationalDateString}" />			
												 --%><div class="input-group input-medium date date-picker"	data-date-format="dd-mm-yyyy" data-date-viewmode="years" >
										<input type="text" class="form-control" name="vehicle.operationalDateString"	readonly value="<s:property value='vehicle.operationalDateString'/>">
										
												<span class="input-group-btn">
													<button class="btn default" type="button">
														<i class="fa fa-calendar"></i>
													</button>
												</span>
											</div>
											<span class="help-block" style="color: red"> 
												<s:property	value="%{fieldErrors.get('operationalDate').get(0)}"/>
											</span>
										</div>
									</div>
											<div class="form-group">
										<label class="control-label col-md-3"> Registration	Date(Doc) <span class="required"> * </span></label>
										<div class="col-md-9">
											<%-- <div class="input-group input-medium date date-picker"	style="width: auto" data-date-format="dd-mm-yyyy" data-date-viewmode="years" data-date-start-date="+0d">
												<s:textfield cssClass="form-control" readonly="true" name="vehicle.registrationDateString" value='%{vehicle.registrationDateString}' />
												 --%><div class="input-group input-medium date date-picker"	data-date-format="dd-mm-yyyy" data-date-viewmode="years" >
										<input type="text" class="form-control" name="vehicle.registrationDateString"	readonly value="<s:property value='vehicle.registrationDateString'/>">
										
												<span class="input-group-btn">
													<button class="btn default" type="button">
														<i class="fa fa-calendar"></i>
													</button>
												</span>
											</div>
											<span class="help-block" style="color: red"> 
												<s:property	value="%{fieldErrors.get('registrationDate').get(0)}" />
											</span>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">Vehicle Usage	Category <span class="required"> * </span></label>
										<div class="col-md-3">
											<select id="s2id_autogen3" name="vehicle.vehicleCategory.vehicleCategoryId"	class="select2-container select2_category form-control">
												<s:iterator value="vehicleCategoryList" status="aaa">
													<option id="category<s:property   value="vehicleCategoryId" />"	value='<s:property   value="vehicleCategoryId" />'>
														<s:property value="vehicleCategoryName" />
													</option>
												</s:iterator>
											</select>
											<script>
											var categoryType = "<s:property   value='vehicle.vehicleCategory.vehicleCategoryId'/>";
											if (categoryType != "") {
												if(document.getElementById("category"+ categoryType)!=null)
												document.getElementById("category"+ categoryType).selected = true;
											}
											</script>
											<span class="help-block" style="color: red">
												<s:property	value="%{fieldErrors.get('vehicle.vehicleCategory.vehicleCategoryId').get(0)}"/>
											</span>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">Under Warranty <span class="required"> * </span></label>
										<div class="col-md-3">
											<select class="select2_category form-control" name="vehicle.underWarranty" data-placeholder="Choose a Category"	tabindex="1">
												<option id="warrantyYes" value="YES">YES</option>
												<option id="warrantyNo" value="No">NO</option>
											</select>
											<script>
												var warranty = "<s:property value='vehicle.underWarranty'/>";
												if (warranty != undefined) {
													if (warranty == "yes"|| warranty == "YES") {
														document.getElementById("warrantyYes").selected = true;
													} else {
														document.getElementById("warrantyNo").selected = true;
													}
												}
											</script>
										</div>
									</div>
									<div class="form-group" hidden>
										<label class="control-label col-md-3"> Status <span class="required"> * </span></label>
										<div class="col-md-3">
											<select class="select2_category form-control" name="vehicle.status" id='status' onchange="getStatus()" >
												<option id="st_ACTIVE" value="ACTIVE">ACTIVE</option>
												<option id="st_INACTIVE" value="INACTIVE">INACTIVE</option>
												
											</select>
										</div>
										<script>S
											var status = "<s:property value="vehicle.status"/>";
											if (status != undefined) {
												if (status == "ACTIVE"|| status == "ACTIVE") {
													document.getElementById("st_ACTIVE").selected = true;
												} else {
													document.getElementById("st_INACTIVE").selected = true;
												}
											}
										</script>
									</div>
									<div class="form-group" style="display: none" id='scrap_date_div'>
										<label class="control-label col-md-3"> Scrapped	Date <span class="required"> * </span></label>
										<div class="col-md-9">
											<%-- <div class="input-group input-medium date date-picker"	style="width: auto" data-date-format="dd-mm-yyyy" data-date-viewmode="years" data-date-start-date="+0d">
												<s:textfield cssClass="form-control" readonly="true" name="vehicle.scrappedDateString" value='%{vehicle.scrappedDateString}' />
												 --%><div class="input-group input-medium date date-picker"	data-date-format="dd-mm-yyyy" data-date-viewmode="years" >
										<input type="text" class="form-control" name="vehicle.scrappedDateString"	readonly value="<s:property value='vehicle.scrappedDateString'/>">
										
												<span class="input-group-btn">
													<button class="btn default" type="button">
														<i class="fa fa-calendar"></i>
													</button>
												</span>
											</div>
											<span class="help-block" style="color: red"> 
												<s:property	value="%{fieldErrors.get('scrapDate').get(0)}" />
											</span>
										</div>
									</div>
									<div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9">
											<button type="submit" class="btn blue">Save</button>
											<button type="button" class="btn default"onclick="callCancell()">Cancel</button>
										</div>
									</div>
								</div>
							</form>
							<%}else{ %>
<%@ include file="/pages/admin/error.jsp" %>
<%} %>									
										
<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
<head>
<script type="text/javascript">

getUnitNames();

function getUnitNames()
{
	$('#select2-chosen-2').html("Select");
	var e = document.getElementById("unit_type_id");
	var strUser = e.options[e.selectedIndex].value;
	var len= document.getElementById('depot').options.length;
	$.ajax({
		type: "get",
	    async:false,
	    url: '<%=request.getContextPath()%>/findUnitName?orgid='+strUser,
	    success: function(result) {
	    	document.getElementById('depot').innerHTML=result;
	   	}
	});
		
}

var orgName= "<s:property value='vehicle.depotOrUnit.org_chart_id'/>";
if(orgName!=""){
	if(document.getElementById("orgName"+orgName)!=null)
	document.getElementById("orgName"+orgName).selected=true;
}

var isScrapped = "<s:property value='isScrapped'/>";
if(isScrapped=="YES"){
	$('#status').append('<option value="SCRAP" id="st_SCRAP">SCRAP</option>'); 
}
var status = "<s:property value='vehicle.status'/>";
//alert(status);
if (status != "") {
	if(document.getElementById("st_"+ status)!=null)
		document.getElementById("st_"+ status).selected = true;
	getStatus();
}

function getStatus(){
	var status= $('#status').val();
	if(status=="SCRAP"){
		$('#scrap_date_div').show();
	}else{
		$('#scrap_date_div').hide();
	}
}


</script>
</head>

<script>
$(document).ready(function() {
	//window.history.pushState("","", "ServiceTypeList.action");
	
	   var w=$('#errorMsg span').html();
	   //alert(w);
	    w=w.replace(/@/g,"<br>");

	   $('#errorMsg').html(''+w+'');
  
});
</script>
<!-- END PAGE CONTENT-->