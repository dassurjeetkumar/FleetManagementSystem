<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
$(document).ready(function() {
	//window.history.pushState("","", "ServiceTypeList.action");
	
	   var w=$('#errorMsg span').html();
	  if(w!=undefined){
		  w=w.replace(/@/g,"<br>");

		   $('#errorMsg').html(''+w+'');
  
	  }
	      
});
	function validate() {
		document.editForm.action = 'addeditedDeviceType.action';
		document.editForm.submit();
	}
	function goView(){
		document.forms[0].action = 'showdevice.action';
		document.forms[0].submit();
	}
	function getDeviceName(){
		var len= document.getElementById('devicetypeId').options.length;
		 if(len<=1 ) {
	         $.ajax({
	             type: "post",
	             url: '<%=request.getContextPath()%>/deviceNameAjaxAction',
	             success: function(result) {
	                 document.getElementById('devicetypeId').innerHTML=result;
	             }
	         });
		 }
	 }
	function getVendorName(){
		var len= document.getElementById('vendorId').options.length;
		 if(len<=1 ) {
	         $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/deviceNameAjaxActionVen!getVendorName',
				success : function(result) {
					document.getElementById('vendorId').innerHTML = result;
				}
			});
		}
	}
	function callAllocated(val)
	{
		document.getElementById("simId").value=val;
		document.getElementById('allocatedSim').innerHTML = "<option value="+val+">"+val+"</option>";
		//alert(document.getElementById("simId").value);
		<%--  $.ajax({
             type: "get",
             url: '<%=request.getContextPath()%>/deviceNameAjaxActionVen!getSimCardAllotName',
					success : function(result) {
						
					}
				}) --%>
		
	}
	function changeFunction(id,id2){
		$("#"+id).val($("#"+id2).text().trim());
	}
	function onloadfunction(){
		 
	 	var editDeviceTab = "<s:property value='isEditDevice'/>";
	 	var simCardAllocationTab ="<s:property value='isSimCardAllocation'/>";
	 	var orgAllocationTab = "<s:property value='isOrgAllocation'/>";
	 	var batteryAllocation = "<s:property value='isBatteryAllocation'/>";
	 	
		$("#tab_0").attr('class', 'tab-pane');
		$("#tab_1").attr('class', 'tab-pane');
		$("#tab_2").attr('class', 'tab-pane');
		$("#tab_3").attr('class', 'tab-pane');
		
		$("#tab0").attr('class', '');
		$("#tab1").attr('class', '');
		$("#tab2").attr('class', '');
		$("#tab3").attr('class', '');
		
		if(editDeviceTab>0){
			$("#tab_0").addClass('active');
			$("#tab0").addClass('active');
		}else if(simCardAllocationTab>0){
			$("#tab_1").addClass('active');
			$("#tab1").addClass('active');
		}else  if(orgAllocationTab>0){
			$("#tab_2").addClass('active');
			$("#tab2").addClass('active');
		}else  if(batteryAllocation>0){
			$("#tab_3").addClass('active');
			$("#tab3").addClass('active');
		} else{
			$("#tab_0").addClass('active');
			$("#tab0").addClass('active');
		}
		getBatteryListAndPreSelectAssignedBattery();
		getSimCardListAndPreSelectAssignSimCard();
		getOrgListAndPreSelectAssignedOrg();
		
		
	} 
	function getSimCardListAndPreSelectAssignSimCard(){
		
		var assignedSimCardId = "<s:property value='assignedSimCard.simcard_id'/>";
		var assignedSimaCardPhoneNumber = "<s:property value='assignedSimCard.phone_number'/>";
		var simCardId = '<s:property value='orgChart.org_id'/>';
		if(assignedSimCardId!="" && assignedSimaCardPhoneNumber!="" ){
			var option = new Option(assignedSimaCardPhoneNumber, assignedSimCardId); 
			$('#simCardListId').prepend($(option));
			$("#simCardListId option[value='sim0']").remove();
			$('#select2-chosen-6').text(assignedSimaCardPhoneNumber);
			$('#assignedSim').val(assignedSimaCardPhoneNumber);
			$('#simCardListId option[value="'+assignedSimCardId+'"]').attr("selected", "selected");
		} else if(simCardId!=""){
			$('#select2-chosen-6').text($('#sim'+simCardId).text());
			//$('#assignedSim').val($('#sim'+simCardId).text());
			$('#simCardListId option[value="sim'+simCardId+'"]').attr("selected", "selected");
		}
	}
	function getOrgListAndPreSelectAssignedOrg(){
		
		var assignedOrgId = "<s:property value='assignedOrg.org_chart_id'/>";
		var assignedOrgName = "<s:property value='assignedOrg.org_name'/>";
		var orgId = '<s:property value='orgChart.org_id'/>';
		if(assignedOrgId!="" && assignedOrgName!="" ){
			var option = new Option(assignedOrgName, assignedOrgId); 
			$('#orgListId').prepend($(option));
			$("#orgListId option[value='org0']").remove();
			$('#select2-chosen-7').text(assignedOrgName);
			$("#assignedOrg").val(assignedOrgName);
			$('#orgListId option[value="'+assignedOrgId+'"]').attr("selected", "selected");
		} else if(orgId!=""){
			$('#select2-chosen-7').text($('#org'+orgId).text());
			//$('#assignedOrg').val($('#org'+orgId).text());
			$('#orgListId option[value="org'+orgId+'"]').attr("selected", "selected");
		}
	}
	function getBatteryListAndPreSelectAssignedBattery(){
		
		var assignedBatteryId = "<s:property value='assignedBattery.battery_id'/>";
		var assignedBatterySerialNumber = "<s:property value='assignedBattery.serial_number'/>";
		var batteryId = "<s:property value='battery.battery_id'/>";
		if(assignedBatteryId!="" && assignedBatterySerialNumber!="" ){
			var option = new Option(assignedBatterySerialNumber, assignedBatteryId); 
			$('#batteryListId').prepend($(option));
			$("#batteryListId option[value='0']").remove();
			$('#select2-chosen-8').text(assignedBatterySerialNumber);
			$("#assignedBat").val(assignedBatterySerialNumber);
			$('#batteryListId option[value="'+assignedBatteryId+'"]').attr("selected", "selected");
		} else if(batteryId!=""){
			$('#select2-chosen-8').text($('#bat'+batteryId).text());
			//$('#assignedBat').val($('#bat'+batteryId).text());
			$('#batteryListId option[value="bat'+batteryId+'"]').attr("selected", "selected");
		}
	}
	

</script>
</head>
<body onload="onloadfunction()">
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "showdevice.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
%>

	<div class="page-content-wrapper">
		<div class="page-content">
		<%if (access.equalsIgnoreCase("Y")){%>
			<%if (edit.equalsIgnoreCase("Y")){%>
			<div class="row">
				<div class="col-md-12">
					<h3 class="page-title">
						DEVICE <small></small>
					</h3>
				</div>
			</div>
			<div class="tab-content">
		
				<div class="tab-pane active">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Edit Device
							</div>
						</div>
						<div class="portlet-body">
							<div class="row">
								<b>
									<font color="green"> <s:actionmessage/></font>
									<font color="red"><s:actionerror/></font>		
								</b>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="tabbable tabbable-custom  tabbable-reversed">
										<ul class="nav nav-tabs">
											<li id='tab0'><a href="#tab_0" data-toggle="tab">Edit Device</a></li>
											<s:if test="%{tabstatus=='ALL'}">
												<li id='tab1'><a href="#tab_1" data-toggle="tab">Sim Card Allocation </a></li>
											</s:if>
												<li id='tab2'><a href="#tab_2" data-toggle="tab">Organization Allocation </a></li>
											<s:if test="%{tabstatus=='ALL'}">
												<li id='tab3'><a href="#tab_3" data-toggle="tab">Battery Allocation </a></li>
											</s:if>
										</ul>
										<div class="tab-content">
											<div id="tab_0">
												<div class="portlet-body form">
													<form action="EditdeviceAction.action" method="post" name="editForm" class="form-horizontal">
														<input type="hidden" name="isEditDevice" value="1"/>
														<s:token></s:token>
														<div class="form-body">
															<div class="form-group">
																<label class="col-md-3 control-label">Device Serial Number<font color="red">*</font></label>
																<div class="col-md-4">
																	<input type="hidden" name="device.device_id" value='<s:property value="device.device_id"/>'>
																	<input type="text" class="form-control"id="device_serial_number" maxlength="50" name="device.device_serial_number" readonly="readonly"
																			value='<s:property value="device.device_serial_number"/>'>
																		<span style="color: red;"><s:property value="fieldErrors.get('serialno').get(0)" /></span>
																</div>
															</div>
														</div>
														<div class="form-body">
															<div class="form-group">
																<label class="col-md-3 control-label">Device Type Name<font color="red">*</font></label>
																<div class="col-md-4">
																	<select id="devicetypeId" name="device.device.device_type_id" class="select2_category form-control">
																		<s:iterator value="deviceTypeList" status="aaa">
																			<option id='devType<s:property value="device_type_id"/>'value='<s:property value="device_type_id" />'>
																				<s:property value="device_type_name" />
																			</option>
																		</s:iterator>
																	</select>
																	<script>
																	var devType = "<s:property  value='device.device.device_type_id'/>";
																	if (devType != "") {
																		if(document.getElementById("devType"+ devType)!=null)
																			document.getElementById("devType"+ devType).selected = true;
																	}
																	</script>
																	<span style="color: red;"><s:property value="fieldErrors.get('devicetype').get(0)" /></span>
																</div>
															</div>
														</div>
														<div class="form-body">
															<div class="form-group">
																<label class="col-md-3 control-label">Model<font color="red">*</font></label>
																<div class="col-md-4">
																	<select id="devicetypeId" name="device.model.model_type_id" class="select2_category form-control">																																				
																		<s:iterator  value="modelTypeList" status="aaa">
																			<option id='model<s:property value="model_type_id"/>'value='<s:property value="model_type_id" />'>
																				<s:property value="model_type_name" />
																			</option>
																		</s:iterator>
																	</select>
																	<script>
																	var modelType = "<s:property  value='device.model.model_type_id'/>";
																	//alert(modelType);
																	if (modelType != "") {
																		if(document.getElementById("model"+ modelType)!=null)
																			document.getElementById("model"+ modelType).selected = true;
																	}
																	</script>
																	<span style="color: red;"><s:property	value="fieldErrors.get('modelno').get(0)" /></span>
																</div>
															</div>
														</div>
														<div class="form-body">
															<div class="form-group">
																<label class="col-md-3 control-label">Status<font color="red">*</font></label>
																<div class="col-md-4">
																	<select class="select2_category form-control" id="status" name="device.status">
																		<option id="active" value="ACTIVE">ACTIVE</option>
																		<option id="deactive" value="INACTIVE">INACTIVE</option>
																	</select>
																	<script>
																	var status = "<s:property value="device.status"/>";
																	if (status != undefined) {
																		if (status == "ACTIVE" || status == "ACTIVE") {
																			document.getElementById("active").selected = true;
																		} else {
																			document.getElementById("deactive").selected = true;
																		}
																	}
																	</script>
																</div>
															</div>
														</div>
														<div class="form-body">
															<div class="form-group">
																<label class="col-md-3 control-label">Vendor Name<font color="red">*</font></label>
																<div class="col-md-4">
																	<select name="device.vendor.id"
																		class="select2_category form-control"  >
																		<s:iterator  value="vendorList" status="aaa">
																			<option id='vendor<s:property value="id"/>'value='<s:property value="id" />'>
																				<s:property value="vendor_name" />
																			</option>
																		</s:iterator>
																	</select>
																	<script>
																	var vendorType = "<s:property  value='device.vendor.id'/>";
																	if (vendorType != "") {
																		if(document.getElementById("vendor"+ vendorType)!=null)
																			document.getElementById("vendor"+ vendorType).selected = true;
																	}
																	</script>
																	<span style="color: red;"><s:property value="fieldErrors.get('vendorname').get(0)" /></span>
																</div>
															</div>
														</div>
														<div class="form-group">
															<label class="control-label col-md-3">Purchased Date<font color="red">*</font></label>
															<div class="col-md-4">
																<div class="input-group date date-picker" data-date-format="dd-mm-yyyy">
																	<input class="form-control" type="text" size="16"
																		readonly="readonly" name="device.purchasedateString"
																		id="purchase_date"
																		value='<s:property value="device.purchasedateString"/>'>
																	<span class="input-group-btn">
																		<button class="btn default date-set" type="button">
																			<i class="fa fa-calendar"></i>
																		</button>
																	</span>
																</div>
																<span style="color: red;"><s:property value="fieldErrors.get('purchasedate').get(0)" /></span>
															</div>
														</div>
														<div class="form-body">
															<div class="form-group">
																<label class="col-md-3 control-label">Remarks</label>
																<div class="col-md-4">
																	<textarea class="form-control" name="device.notes" id="ven.note" maxlength="200"><s:property value="device.notes" />
																	</textarea>
																</div>
															</div>
														</div>
														<div class="form-actions fluid">
															<div class="col-md-offset-3 col-md-9">
																<button type="submit" class="btn blue"	onSubmit="validate()">Save</button>
																<button type="button" class="btn default" onclick="goView()">Cancel</button>
															</div>
														</div>
													</form>
												</div>
											</div>

											<div class="tab-pane" id="tab_1">
												<div class="tab-pane active" id="tab_0">
													<div class="portlet-body form">
														<form action="#" method="post" class="form-horizontal"	id="simform">
															<input type="hidden" name="isSimCardAllocation" value="1"/>
															<input type="hidden" name="simcard.phone_number" id="simId" value=0>
															<div class="form-group">
																<label class="control-label col-md-3">Sim cards</label>
																<div class="col-md-3">
																	<select name="simcard.simcard_id" id="simCardListId"class="select2_category form-control" onchange="changeFunction('assignedSim','select2-chosen-6')">
																		<option id="sim0" value='sim0'>--Select--</option>
																		<s:iterator value="simcardlist" status="aa">
																			<option id="sim<s:property value="simcard_id"/>" value="<s:property value="simcard_id"/>">
																				<s:property value="phone_number" />
																			</option>
																		</s:iterator>
																	</select>
																	<span style="color: red;">
																		<s:property	value="fieldErrors.get('simCard').get(0)" />
																	</span>
																</div>
															</div>
															<div class="form-group">
																<label class="control-label col-md-3">Assigned Sim Card</label>
																<div class="col-md-3">
																	<input type="text" disabled="disabled" id="assignedSim" class="form-control" />
																</div>
															</div>
															<input type="hidden" name="device.device_id" value='<s:property value="device.device_id"/>'>
															<div class="form-actions fluid">
																<div class="col-md-offset-3 col-md-9">
																	<button type="button" class="btn blue" onclick="goSim();">Save</button>
																	<button type="button" class="btn default" onclick="goView()">Cancel</button>
																</div>
															</div>
															<script>
															function goSim()
															{
																var assignType = $("#simCardListId option:selected").text().trim();
																var warningText = 'Assign';
																if(assignType =="NA"){
																	warningText = 'Release';
																}
																bootbox.confirm("Are you sure you want to "+warningText+"?",	function(result) {
																	if (result == true) {
																	//alert('deleted');
																		document.getElementById("simform").action="saveSimCard.action";
																		document.getElementById("simform").submit();
																	}
																});
															}
															</script>
														</form>
													</div>
												</div>
											</div>
											<div class="tab-pane" id="tab_2">
												<div class="portlet-body form">
													<form action="#" method="post" class="form-horizontal" id="orgForm">
														<input type="hidden" name="isOrgAllocation" value="1"/>
														<input type="hidden" name="orgchart.org_name" id="orgname">
														<div class="form-group">
															<label class="control-label col-md-3">Organizations</label>
															<div class="col-md-3">
																<select name="orgChart.org_chart_id" id="orgListId" class="select2_category form-control"  onchange="changeFunction('assignedOrg','select2-chosen-7')">
																	<option id="org0" value='org0'>--Select--</option>
																	<s:iterator value="orglist" status="aa">
																		<option id="org<s:property value="org_chart_id"/>" value="<s:property value="org_chart_id"/>">
																			<s:property value="org_name" />
																		</option>
																	</s:iterator>
																</select>
																<span style="color: red;">
																	<s:property	value="fieldErrors.get('orgchart').get(0)" />
																</span>
															</div>
														</div>
														<div class="form-group">
															<label class="control-label col-md-3">Assigned Organization</label>
															<div class="col-md-3">
																	<input type="text" disabled="disabled" id="assignedOrg" class="form-control" />
															</div>
														</div>
														<input type="hidden" name="device.device_id"value='<s:property value="device.device_id"/>'>
														<div class="form-actions fluid">
															<div class="col-md-offset-3 col-md-9">
																<button type="button" class="btn blue" onclick="goOrg();">Save</button>
																<button type="button" class="btn default" onclick="goView()">Cancel</button>
															</div>
														</div> 
														<script>
														function goOrg()
														{
															var assignType = $("#orgListId option:selected").text().trim();
															var warningText = 'Assign';
															if(assignType =="NA"){
																warningText = 'Release';
															}
															bootbox.confirm("Are you sure you want to "+warningText+"?",	function(result) {
																if (result == true) {
																	//alert('deleted');
																	document.getElementById("orgForm").action="addOrg.action";
																	document.getElementById("orgForm").submit();
																}
															});
														}
														</script>
													</form>
												</div>
											</div>
											<div class="tab-pane" id="tab_3">
												<div class="portlet-body form">
													<form action="#" id="batteryForm" method="post" class="form-horizontal">
														<input type="hidden" name="isBatteryAllocation" value="1"/>
														<!-- <input type="hidden" name="battery.serial_number" id="batname"> -->
														<div class="form-group">
															<label class="control-label col-md-3">Battery</label>
															<div class="col-md-3">
																<select name="battery.battery_id" id="batteryListId" class="select2_category form-control" onchange="changeFunction('assignedBat','select2-chosen-8')">
																	<option  id='bat0' value='0'>--Select--</option>
																		<s:iterator value="batteryList" status="aa">
																			<option id="bat<s:property value="battery_id"/>" value="<s:property value="battery_id"/>">
																				<s:property value="serial_number"/>
																			</option>
																		</s:iterator>
																	</select>
																	<span style="color: red;">
																		<s:property	value="fieldErrors.get('battery').get(0)" />
																	</span>
																</div>
															</div>
															<div class="form-group">
																<label class="control-label col-md-3">Assigned Battery</label>
																<div class="col-md-3">
																	<input type="text" disabled="disabled" id="assignedBat" class="form-control" />
																</div>
															</div>
															<input type="hidden" name="device.device_id" value='<s:property value="device.device_id"/>'>
															<div class="form-actions fluid">
																<div class="col-md-offset-3 col-md-9">
																	<button type="button" class="btn blue" onclick='goBattrery()'>Save</button>
																	<button type="button" class="btn default" onclick="goView()">Cancel</button>
																</div>
															</div>
															<script>
														function goBattrery()
														{
															var assignType = $("#batteryListId option:selected").text().trim();
															var warningText = 'Assign';
															if(assignType =="NA"){
																warningText = 'Release';
															}
															
															bootbox.confirm("Are you sure you want to "+warningText+"?",	function(result) {
																if (result == true) {
																	//alert('deleted');
																	document.getElementById("batteryForm").action="battery.action";
																	document.getElementById("batteryForm").submit(); 
																}
															});
														}
														</script>
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
						</div>
					</div>
				</div>
			</div>
		</div>
</body>
<head>
<script>

</script>
</head>
</html>
