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

// 		if (document.getElementById("role_name").value == '') {
// 			alert('Please Enter a New Role');
// 			return false;
// 		}
// 		if (document.getElementById("note").value == '') {
// 			alert('Please Enter Note');
// 			return false;
// 		}
//alert("inside validate");
		document.forms[0].action = 'addeditedcomplaint.action';
		document.forms[0].submit();
	}
	function goView()
	{
		document.forms[0].action = 'viewcomplaint.action';
		document.forms[0].submit();
	}
	/* $(document).ready(function() {
		   window.history.pushState("","", "editcomplaint.action");
		 }); */
</script>
</head>
<body>
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "viewcomplaint.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
String status=accessrightdao.UserStatus(usrsessionid);
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
%>

	<input type="text" id='a'>

	<div class="page-content-wrapper">
		<div class="page-content">
		<%if (access.equalsIgnoreCase("Y")){%>
		<%if(edit.equalsIgnoreCase("Y")){ %>
			<div class="col-md-12">
				<h3 class="page-title">
				COMPLAINT
				</h3>
			</div>
			
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
				
					<div class="portlet box grey-cascade">

						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Edit Complaint
							</div>

						</div>

						<div class="portlet-body form">
						<font color="red"><s:actionmessage/></font>
							<s:if test="hasActionErrors()">
								<div class="alert alert-danger">
									<button class="close" data-close="alert"></button>
									<span> <s:actionerror />
									</span>
									
								</div>


							</s:if>
							
										<!-- BEGIN FORM-->
							<form action="addeditedcomplaint.action" method="post"  class="form-horizontal">
							<input type="hidden" name="complaint.complaint_id" value="<s:property value="complaint.complaint_id"/>"/>
							<s:if test="%{insertstaus=='duplicate'}"><font color="red">Could not insert Duplicate complaint</font></s:if>
								
								
								
								
								<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Complaint Type:<font color="red">*</font></label>

										<div class="col-md-4">
											<input type="text" class="form-control" id="device_type_name" maxlength="50"
												name="complaint.compliant_type" value='<s:property value="complaint.compliant_type"/>'>
												<s:if test="fieldErrors.get('Compliant_type').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('Compliant_type').get(0)" /></span>
											</s:if>
										</div>
									</div>
								</div>
									

								
									<div class="form-body">

								<div class="form-group">
				<label class="control-label col-md-3">Incident Date:<span class="required"> * </span></label>
				<div class="col-md-3">
				<div class="input-group input-medium date date-picker"	data-date-format="dd-mm-yyyy" data-date-viewmode="years" >
				<input type="text" class="form-control" name="complaint.incident_date"	value="<s:property value='complaint.incident_date'/>" readonly /> <span
			class="input-group-btn">
			<button class="btn default" type="button">
				<i class="fa fa-calendar"></i>
				</button>
					</span>
			</div>
			<span class="help-block" style="color: red"> 
				<s:property	value="%{fieldErrors.get('Incident_date').get(0)}" />
			</span>
		</div>
</div>
									
	</div>
								
									<div class="form-body">
									
									
		<div class="form-group">
				<label class="control-label col-md-3">complaint Date:<span class="required"> * </span></label>
				<div class="col-md-3">
				<div class="input-group input-medium date date-picker"	data-date-format="dd-mm-yyyy" data-date-viewmode="years" >
				<input type="text" class="form-control" name="complaint.complaint_date"	value="<s:property value='complaint.complaint_date'/>" readonly /> <span
			class="input-group-btn">
			<button class="btn default" type="button">
				<i class="fa fa-calendar"></i>
				</button>
					</span>
			</div>
			<span class="help-block" style="color: red"> 
				<s:property	value="%{fieldErrors.get('complaint_date').get(0)}" />
			</span>
		</div>
</div>
									
			</div>						
									
								
								
									<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Identification Data:<font color="red">*</font></label>

										<div class="col-md-4">
											<input type="text" class="form-control" id="device_type_name" maxlength="15"
												name="complaint.identification_data" value='<s:property value="complaint.identification_data"/>'>
										<s:if test="fieldErrors.get('identification_data').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('identification_data').get(0)" /></span>
											</s:if>
										</div>
									</div>
								


									</div>
								
									<div class="form-body">
									
									<div class="form-group">
										<label class="col-md-3 control-label">Complaint Description:</label>

										<div class="col-md-4">
											<textarea rows="3" class="form-control" id="notes" 
												name="complaint.complaint_description" maxlength="50" ><s:property value="complaint.complaint_description"/></textarea>
												<s:if test="fieldErrors.get('complaint_description').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('complaint_description').get(0)" /></span>
											</s:if>
										</div>
									</div>
									</div>
								
									<div class="form-body">
									
									<div class="form-group">
									<label class="col-md-3 control-label">Status:<font color="red">*</font></label>
									<div class="col-md-4">
										<select class="form-control" id="status" name="complaint.status">
											<option id="active" value="ACTIVE">ACTIVE</option>
											<option id="deactive" value="INACTIVE">INACTIVE</option>
										</select>
														<script>
															var status = "<s:property value="complaint.status"/>";
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
								</div>
								
									<div class="form-body">
								
								<div class="form-group">
									<label class="col-md-3 control-label">Complaint Media:</label>
									<div class="col-md-4">
										<select class="form-control" id="media" name="complaint.complaint_media">
											<option id="phone" value="phone">Phone</option>
											<option id="email" value="email">Email</option>
										</select>
														<script>
															var media = "<s:property value="complaint.complaint_media"/>";
															if (media != undefined) {
																if (media == "phone"
																		|| media == "phone") {
																	document
																			.getElementById("phone").selected = true;
																} else {
																	document
																			.getElementById("email").selected = true;
																}
															}
														</script>
									</div>
								</div>
									
								</div>
																
									<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Taken By
											Name:<font color="red">*</font>
										</label>

										<div class="col-md-4">
											 <%-- <select class="form-control" id="devicetypeId"
												onclick="getDeviceName()"
												name="device.device.device_type_id">

												<option
													value='<s:property value="device.device.device_type_id"/>'>
													<s:property value="device.device.device_type_name" />
												</option>
											</select>  --%>
											<s:select list="userlist" id="complainttakenId" name="complaint.taken_by" 
		cssClass="select2_category form-control" headerKey="0" headerValue="Select Name"></s:select>
		<s:if test="fieldErrors.get('taken_by').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('taken_by').get(0)" /></span>
											</s:if>
										</div>
									</div>
								</div>


								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button  type="submit" class="btn blue" onSubmit="validate()">Save</button>
										<button type="button" class="btn default" onclick="goView()">Cancel</button>
									</div>
								</div>


							</form>
							<%}else{ %>
<%@ include file="/pages/admin/error.jsp" %>
<%} %>									
										
<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>
							<!-- END FORM-->


						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>