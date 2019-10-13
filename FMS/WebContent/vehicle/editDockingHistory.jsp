<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
<script>

function goView()
{
		document.forms[0].action = 'DockingHistoryList.action';
		document.forms[0].submit();
}
</script>
<script>
function getDepot(orgId){
	 var val=document.getElementById('vehicleidlistid').value;
	 
	 	 if(val!=0) {
	 		
       $.ajax({
           type: "post",
           url: '<%=request.getContextPath()%>/getdocktype.action?val='+val,
           success: function(result) {
           	//alert(result);
               document.getElementById('docktypeid').value=result;
           }
       });
	 }
	
}
</script>
<script type="text/javascript">
$( document ).ready(function() {
	//alert("hellllllllllllllllllllll");
	 var val=document.getElementById('vehicleidlistid').value;
	 
 	 if(val!=0) {
 		
   $.ajax({
       type: "post",
       url: '<%=request.getContextPath()%>/getdocktype.action?val='+val,
       success: function(result) {
       	//alert(result);
          // document.getElementById('docktypeid').value=result;
       }
   });
 }

	
       
});

	function goView()
	{
		document.forms[0].action = 'DockingHistoryList.action';
		document.forms[0].submit();
	}
</script>
<title>Insert title here</title>
</head>
<body>
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "DockingHistoryList.action");
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
					<h3 class="page-title">DOCKING HISTORY</h3>
				</div>
			</div>
			<div class="tab-content">
		
				<div class="tab-pane active" id="tab_0">
					
					<div class="portlet box grey-cascade">

						<div class="portlet-title">

							<div class="caption">
								<i class="fa fa-gift"></i>Edit Docking History
							</div>

						</div>

						<div class="portlet-body form">
						<font color="red"><s:actionmessage /></font>
							<s:if test="hasActionErrors()">
								<div class="alert alert-danger">
									<button class="close" data-close="alert"></button>
									<span> <s:actionerror />
									</span>
								</div>


							</s:if>
							<!-- BEGIN FORM-->
							<form action="AddEditDockingHistory.action" method="post"
								id="createComplaintId" class="form-horizontal">
								<input type="hidden" name="dochistory.docking_id"
									value="<s:property value="dochistory.docking_id"/>" />
									<input type="hidden" name="checkreq"  value="2" /> 
								<s:if test="%{insertstatus=='duplicate'}">
									<font color="red">Could not insert Duplicate PeakHours</font>
								</s:if>


								<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label"> Registration Number: <span class="required"> * </span></label>

										<div class="col-md-4">

											<s:select list="vehicleidlist" id="vehicleidlistid"
												name="dochistory.vehicle.vehicleId"
												cssClass="select2_category form-control" headerKey="0"
												headerValue="Select" onchange="getDepot(this.value)"></s:select>
											<s:if test="fieldErrors.get('vehicleId').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('vehicleId').get(0)" /></span>
											</s:if>
										</div>
									</div>
								</div>


								<div class="form-group">
									<label class="control-label col-md-3 control-label">Start Date/ Time
										
									</label>
									<div class="col-md-4">
										<div class="input-group date form_datetime">
											<input type="text" size="16" readonly
												name="dochistory.startDateString" class="form-control"
												value="<s:property value='dochistory.startDateString'/>">
											<span class="input-group-btn">
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
										</div>
										<span class="help-block" style="color: red"> <s:property
												value="%{fieldErrors.get('startDateString').get(0)}" /></span>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-md-3 control-label">End Date/Time <span
										class="required"> * </span></label>
									<div class="col-md-4">
										<div class="input-group date form_datetime">
											<input type="text" size="16" readonly
												name="dochistory.endDateString" class="form-control"
												value="<s:property value='dochistory.endDateString'/>">
											<span class="input-group-btn">
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
										</div>
										<span class="help-block" style="color: red"> <s:property
												value="%{fieldErrors.get('endDateString').get(0)}" />
										</span>
									</div>
								</div>


								<div class="form-group">
									<label class="col-md-3 control-label">Status:<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select class="form-control" id="status"
											name="dochistory.status">
											<option id="active" value="ACTIVE">ACTIVE</option>
											<option id="deactive" value="INACTIVE">INACTIVE</option>
										</select>
										<script>
															var status = "<s:property value="dochistory.status"/>";
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

								<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label"> Docking Type:<font
											color="red">*</font>
										</label>

										<div class="col-md-4">
										<s:select list="doctypelist" id="docktypeid" name="dochistory.dockingType.docking_type_id"  
													cssClass="form-control" headerKey="0" headerValue="Select"></s:select> 
<%--  											<select id="doctypelistid"   name="dochistory.dockingType.docking_type_id" class="select2_category form-control" > --%>
<%-- 												 <option value="<s:property value="dochistory.dockingType.docking_type_id"/>"><s:property value="dochistory.dockingType.docking_type"/></option> --%>
<%-- 											 </select> --%>

<%-- 											<s:select list="doctypelist" id="complainttakenId" --%>
<%-- 												name="dochistory.dockingType.docking_type_id" --%>
<%-- 												cssClass="select2_category form-control" headerKey="0" --%>
<%-- 												headerValue="Select"></s:select> --%>
											<s:if test="fieldErrors.get('docking_type_id').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('docking_type_id').get(0)" /></span>
											</s:if>
										</div>
									</div>
								</div>




								<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Docking Batch
											Name:
										</label>

										<div class="col-md-4">
											<input type="text" class="form-control" id="device_type_name"
												maxlength="50" name="dochistory.docking_batch_name"
												value='<s:property value="dochistory.docking_batch_name"/>'>
											<s:if test="fieldErrors.get('docking_batch_name').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('docking_batch_name').get(0)" /></span>
											</s:if>
										</div>
									</div>
								</div>


								<div class="form-group">
									<label class="col-md-3 control-label">FIP Change</label>
									<div class="col-md-4">
										<select class="form-control" id="status"
											name="dochistory.fip_change">
											<option id="phone" value="Yes">yes</option>
											<option id="email" value="No">No</option>
										</select>
										<script>
															var media = "<s:property value="dochistory.fip_change"/>";
															if (media != undefined) {
																if (media == "Yes"
																		|| media == "Yes") {
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

								<div class="form-group">
									<label class="col-md-3 control-label">EOC Change<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select class="form-control" id="status"
											name="dochistory.eoc_change">
											<option id="phones" value="Yes">Yes</option>
											<option id="emails" value="No">No</option>
										</select>
										<script>
											var media = "<s:property value="dochistory.eoc_change"/>";
											if (media != undefined) {
												if (media == "Yes"
														|| media == "Yes") {
													document
															.getElementById("phones").selected = true;
												} else {
													document
															.getElementById("emails").selected = true;
												}
											}
										</script>
									</div>
								</div>



								<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label"> Component Type:<font
											color="red">*</font>
										</label>

										<div class="col-md-4">

											<s:select list="componentlist" id="complainttakenId"
												name="dochistory.componenetType.componentTypeId"
												cssClass="select2_category form-control" headerKey="0"
												headerValue="Select"></s:select>
											<s:if test="fieldErrors.get('componentTypeId').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('componentTypeId').get(0)" /></span>
											</s:if>
										</div>
									</div>
								</div>



								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">

										<button type="submit" class="btn blue" onSubmit="validate()">Save</button>
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