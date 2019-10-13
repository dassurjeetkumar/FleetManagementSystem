<style type="text/css">
.help-block,ul,li {
	list-style: none;
}
</style>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<script src="<%=request.getContextPath()%>/vehicle/keyBoardEvents.js"
	type="text/javascript"></script>
	
	<script>
	function validateAlphaNumeric(){
		document.getElementById("alphaNumericError").style.visibility = "hidden";
		var input = document.getElementById("capacity");
		if(input.value.match(/[^a-z0-9]/gi)){
			//alert("Error");			
			document.getElementById("alphaNumericError").innerHTML = "Special characters are not allowed"
			document.getElementById("alphaNumericError").style.visibility = "visible";
			input.style.border = "1px solid red";
			return false;
		}
	}
</script>
<div class="page-content-wrapper">
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "BatteryView.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
%>

	<div class="page-content">
	<%if (access.equalsIgnoreCase("Y")){%>
	<%if (edit.equalsIgnoreCase("Y")){%>
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
					BATTERY
					<%-- <small> Create Battery</small> --%>
				</h3>
				<!-- <ul class="page-breadcrumb breadcrumb">

					<li><i class="fa fa-home"></i> Home <i
						class="fa fa-angle-right"></i></li>
					<li>Device Management <i class="fa fa-angle-right"></i></li>
					<li>Create Batterye</li>
				</ul> -->
			</div>
		</div>
		<%-- <div class="row">
			<div class="col-md-12" align="center" style="font-size: 1.1em">

				<span class="help-block" style="color: red; list-style: none"><s:actionerror /></span>
				<span class="help-block" style="color: red; list-style: none"><s:actionmessage /></span>
			</div>
		</div> --%>
		<div class="row">
		
			<div class="col-md-12">
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-bars"></i>Edit Battery
						</div>
					</div>
					<div class="portlet-body form">
						<form action="SaveEditedBatteryAction"
							class="form-horizontal form-row-seperated" method="post">
							<font color="red"><s:actionmessage /></font>
							<span id="errorMsg" style="color:red;word-wrap: break-word;" ><s:actionerror/></span>
							<input type="hidden" name="battery.battery_id" value='<s:property value="battery.battery_id"/>'/>
							<div class="form-group">
								<label class="control-label col-md-3">Vendor Name:<font
									color="red">*</font></label>
								<div class="col-md-4">
								<div class="input-group input-medium" style="width: auto">
									<s:select list="vendorList" id="vendortypeId"
										name="battery.vendorDetails.id"
										cssClass="select2_category form-control" headerKey="0"
										headerValue="Select"></s:select>
								</div>
								<s:if test="fieldErrors.get('battery.vendor').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('battery.vendor').get(0)" /></span>
											</s:if>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-3"> Serial Number:<font
									color="red">*</font></label>
								<div class="col-md-4">
									<div class="input-group input-medium" style="width: auto">
										<input type="text" class="form-control" maxlength="50" value='<s:property value="battery.serial_number"/>'
											name="battery.serial_number" />
									</div>
									<s:if test="fieldErrors.get('battery.serial_number').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('battery.serial_number').get(0)" /></span>
											</s:if>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3"> Capacity:<font
									color="red">*</font></label>
								<div class="col-md-4">
									<div class="input-group input-medium" style="width: auto">
										<input type="text" class="form-control" maxlength="50" value='<s:property value="battery.capacity"/>'
											name="battery.capacity" id="capacity"/>
											<span id="alphaNumericError" style="color: red;"></span>
									</div>
									<s:if test="fieldErrors.get('battery.capacity').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('battery.capacity').get(0)" /></span>
											</s:if>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3"> Procured Date:<font
									color="red">*</font> </label>
								<div class="col-md-4">
									<div class="input-group input-medium date date-picker"
										style="width: auto" data-date-format="dd-mm-yyyy"
										data-date-viewmode="years">
										<input type="text"  class="form-control" value='<s:property value="battery.procuredDateString"/>' readonly
											name="battery.procuredDateString" /> <span
											class="input-group-btn">
											<button class="btn default" type="button">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
										
									</div>
									<s:if test="fieldErrors.get('procureddate').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('procureddate').get(0)" /></span>
											</s:if>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3"> Status</label>
								<div class="col-md-4">
									<div class="input-group input-medium" style="width: auto">
									<select class="select2_category form-control" id="status"
																	name="battery.status">

																	<option id="active" value="ACTIVE">ACTIVE</option>
																	<option id="deactive" value="INACTIVE">INACTIVE</option>
																</select>
										<!-- <input type="text" class="form-control" readonly="readonly" 
											value="ACTIVE" name="battery.status" /> -->
									</div>
									<script>
															var status = "<s:property value="battery.status"/>";
															if (status != undefined) {
																if (status == "ACTIVE"
																		|| status == "ACTIVE") {
																	document
																			.getElementById("active").selected = true;
																} else {
																	document
																			.getElementById("deactive").selected = true;
																}
															}
														</script>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3"> Remarks</label>
								<div class="col-md-4">
									<div class="input-group input-medium" style="width: auto">
										<textarea class="form-control" name="battery.notes" maxlength="200"><s:property value="battery.notes"/></textarea>
									</div>
								</div>
							</div>
							<div class="form-actions fluid">
								<div class="col-md-offset-3 col-md-9">
									<button type="submit" class="btn blue" onclick="return validateAlphaNumeric();">Save</button>
									<button type="button" class="btn default"
										onclick="callCancell()">Cancel</button>
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
<script>
	function callCancell() {
		window.location.href = 'BatteryView.action';
	}
</script>
<script>
$(document).ready(function() {
	//window.history.pushState("","", "ServiceTypeList.action");
	
	   var w=$('#errorMsg span').html();
	   //alert(w);
	    w=w.replace(/@/g,"<br>");

	   $('#errorMsg').html(''+w+'');
  
});
</script>
