<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
<script type="text/javascript">
	function validate() {

		if (document.getElementById("role_name").value == '') {
			alert('Please Enter a New Role');
			return false;
		}
		if (document.getElementById("note").value == '') {
			alert('Please Enter Remarks');
			return false;
		}

		document.forms[0].action = 'addeditedDeviceType.action';
		document.forms[0].submit();
	}
	function goView()
	{
		document.forms[0].action = 'showdeviceType.action';
		document.forms[0].submit();
	}
	/* $(document).ready(function() {
		   window.history.pushState("","", "editDeviceType.action");
		 }); */
</script>
</head>
<body>
	<input type="text" id='a'>
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "showdeviceType.action");
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
		<%if(access.equalsIgnoreCase("Y")){ %>
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
<%if(edit.equalsIgnoreCase("Y")){ %>
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Edit Device Type
							</div>

						</div>

						<div class="portlet-body form">
							<s:if test="hasActionErrors()">
								<div class="alert alert-danger">
									<button class="close" data-close="alert"></button>
									<b>
								<span id="errorMsg" style="color:red;word-wrap: break-word;" ><s:actionerror/></span>
							</b>
									</span>
								</div>


							</s:if>
							<!-- BEGIN FORM-->
							<form action="addeditedDeviceType.action" class="form-horizontal" method="post">
							<s:if test="%{updatestaus=='duplicate'}"><font color="red">Could not Update Duplicate DeviceType</font></s:if>
															
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Device Type 
											Name:<font color="red">*</font></label>

										<div class="col-md-4">
											<input type="text" class="form-control" id="role_name" maxlength="50"
												name="deviceType.device_type_name" 
												value='<s:property value="deviceType.device_type_name"/>'>
												
												 <input type="hidden" name="deviceType.device_type_id" id="device_type_id" value='<s:property value="deviceType.device_type_id"/>'>
												 <s:if test="fieldErrors.get('devicename').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('devicename').get(0)" /></span>
											</s:if>
										</div>
									</div>
								</div>
								<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Remarks</label>

										<div class="col-md-4">
											<textarea rows="3" class="form-control" id="notes" maxlength="100"
												name="deviceType.notes"><s:property value="deviceType.notes"/></textarea>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">Status</label>
									<div class="col-md-4">
										<select class="form-control" id="status" name="deviceType.status">
											<option id="active" value="ACTIVE">ACTIVE</option>
															<option id="deactive" value="INACTIVE">INACTIVE</option>
										</select>
			<script>
															var status = "<s:property value="deviceType.status"/>";
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
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn blue" onsubmit="validate()">Save</button>
										<button type="button" class="btn default" onclick="goView()">Cancel</button>
									</div>
								</div>
							</form>
							<!-- END FORM-->
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

</body>
</html>
<script>
$(document).ready(function() {
	//window.history.pushState("","", "ServiceTypeList.action");
	
	   var w=$('#errorMsg span').html();
	   //alert(w);
	    w=w.replace(/@/g,"<br>");

	   $('#errorMsg').html(''+w+'');
  
});
</script>