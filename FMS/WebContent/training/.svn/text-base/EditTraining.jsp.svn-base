	
	<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<!DOCTYPE body PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body onload="getDetails()">
<script>
function getEditDetail(){
	//$('#empid').prop('disabled', false);
	//$('#user_type').prop('disabled', false);
	//$('#userloginname').prop('disabled', false);
	
	document.frm.action="updateTrainingDetails.action";
	document.frm.submit();
}
function cancelform(){
	document.frm.action="traininglist.action";
	document.frm.submit();
}
function getDetails(){
	
}


</script>

<div class="page-content-wrapper">
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "traininglist.action");
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
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div class="modal fade" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">Modal title</h4>
						</div>
						<div class="modal-body">
							 Widget settings form goes here
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue">Save changes</button>
							<button type="button" class="btn default" data-dismiss="modal">Close</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<!-- BEGIN STYLE CUSTOMIZER -->
			<div class="theme-panel hidden-xs hidden-sm">
				
				<div class="toggler-close">
				</div>
				<div class="theme-options">
					<div class="theme-option theme-colors clearfix">
						<span>
						THEME COLOR </span>
						<ul>
							<li class="color-default current tooltips" data-style="default" data-original-title="Default">
							</li>
							<li class="color-darkblue tooltips" data-style="darkblue" data-original-title="Dark Blue">
							</li>
							<li class="color-blue tooltips" data-style="blue" data-original-title="Blue">
							</li>
							<li class="color-grey tooltips" data-style="grey" data-original-title="Grey">
							</li>
							<li class="color-light tooltips" data-style="light" data-original-title="Light">
							</li>
							<li class="color-light2 tooltips" data-style="light2" data-html="true" data-original-title="Light 2">
							</li>
						</ul>
					</div>
					<div class="theme-option">
						<span>
						Layout </span>
						<select class="layout-option form-control input-small">
							<option value="fluid" selected="selected">Fluid</option>
							<option value="boxed">Boxed</option>
						</select>
					</div>
					<div class="theme-option">
						<span>
						Header </span>
						<select class="page-header-option form-control input-small">
							<option value="fixed" selected="selected">Fixed</option>
							<option value="default">Default</option>
						</select>
					</div>
					<div class="theme-option">
						<span>
						Sidebar </span>
						<select class="sidebar-option form-control input-small">
							<option value="fixed">Fixed</option>
							<option value="default" selected="selected">Default</option>
						</select>
					</div>
					<div class="theme-option">
						<span>
						Sidebar Position </span>
						<select class="sidebar-pos-option form-control input-small">
							<option value="left" selected="selected">Left</option>
							<option value="right">Right</option>
						</select>
					</div>
					<div class="theme-option">
						<span>
						Footer </span>
						<select class="page-footer-option form-control input-small">
							<option value="fixed">Fixed</option>
							<option value="default" selected="selected">Default</option>
						</select>
					</div>
				</div>
			</div>
			<!-- END STYLE CUSTOMIZER -->
			<!-- BEGIN PAGE HEADER-->
		
			<!-- END PAGE HEADER-->
	<div class="row">
	<%if (edit.equalsIgnoreCase("Y")){%>
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						TRAINING <small></small>
					</h3>
					
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
<div class="tab-content">
							<div class="tab-pane active" id="tab_0">


							<div class="portlet box grey-cascade">
									<div class="portlet-title">
										<div class="caption">
											<i class="fa fa-gift"></i>Edit Training
										</div>
										
									</div>
									<div class="portlet-body form">
										<!-- BEGIN FORM-->
											<s:if test="hasActionErrors()">
											<font color="Red"> <s:actionerror />
											</font>
											</s:if>
					
										<s:if test="hasActionMessages()">
										<font color="green"> <s:actionmessage />
										
									</font>
										</s:if>
										<form action="#" class="form-horizontal" name="frm" method="post">
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Training Id:<font color="red">*</font></label>
										<div class="col-md-4">
<%-- 										<input   name="maintenancetype.maintenance_type_id"  value='<s:property value="maintenancetype.maintenance_type_id" />'  id="maintenance_id" readonly> --%>
										<input id="training_id" type="text" class="form-control" name="training.training_id"	value="<s:property value='training.training_id'/>" readonly />
											
										</div>
									</div>
								</div>	
								<div class="form-group">
									<label class="col-md-3 control-label">Training Type:<font color="red">*</font></label>
										<div class="col-md-4">
<%-- 										<input type="hidden"   name="training.training_id"  value='<s:property value="training.training_id" />'  id="training_id" > --%>
											<s:select list="trainingtypelist" id="trainingtype" name="training.trainingtype.training_type_id" 
  		cssClass="select2_category form-control" headerKey="0" headerValue="Select"></s:select>  
 											<s:if test="fieldErrors.get('trainingtype').size() > 0">  
 					     						<span style="color:red;"><s:property value="fieldErrors.get('trainingtype').get(0)" /></span>  
  											</s:if>  
										</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">Training Date:<span class="required">* </span></label>
									<div class="col-md-3">
										<div class="input-group input-medium date date-picker"	data-date-format="dd-mm-yyyy" data-date-viewmode="years" data-date-start-date="+0d">
										<input type="text" class="form-control" name="training.stringtrainingdate"	value="<s:property value='training.stringtrainingdate'/>" readonly /> <span
														class="input-group-btn">
											<button class="btn default" type="button">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
									</div>
									<span class="help-block" style="color: red"> 
										<s:property	value="%{fieldErrors.get('tdate').get(0)}" />
									</span>
								</div>
								</div>
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Employee Id:<font color="red">*</font></label>
										<div class="col-md-4">

										<input id="emp_id" type="text" class="form-control" value="<s:property value='training.employee.employee_id'/>" readonly />
											
										</div>
									</div>
								</div>	
								<div class="form-group">
									<label class="col-md-3 control-label">Employee Name:<font color="red">*</font></label>
										<div class="col-md-4">
											<s:select list="employeelist" id="empId" name="training.employee.employee_id" 
  		cssClass="select2_category form-control" headerKey="0" headerValue="Select" onchange="document.getElementById('emp_id').value=this.value"></s:select>  
 											<s:if test="fieldErrors.get('empId').size() > 0">  
 					     						<span style="color:red;"><s:property value="fieldErrors.get('empId').get(0)" /></span>  
  											</s:if>  
										</div>
								</div>
								
								
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Duration:<font color="red">*</font></label>
										<div class="col-md-4">
											<input type="text" class="form-control" id="duration" maxlength="20" value='<s:property value="training.duration"/>'
												name="training.duration">
												<s:if test="fieldErrors.get('duration').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('duration').get(0)" /></span>
											</s:if>
										</div>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-3 control-label">Status :<font color="red">*</font></label>
									<div class="col-md-4">
										<select class="form-control" id="status" name="training.status" <s:property value="training.status"/> >	
											<option id="Open" value="Open">Open</option>
											<option id="Close" value="Close">Close</option>
										</select>
										<script>
															var status = "<s:property value="training.status"/>";
															if (status != undefined) {
																if (status == "Open"
																		|| status == "Open") {
																	document
																			.getElementById("Open").selected = true;
																} else {
																	document
																			.getElementById("Close").selected = true;
																}
															}
														</script>
										
									</div>
								</div>
								<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Remarks:</label>

										<div class="col-md-4">
											<textarea rows="3" class="form-control" id="notes" maxlength="100"	name="training.remarks"><s:property value="training.remarks"/></textarea>
										</div>
									</div>
								</div>
								
								
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button class="btn blue" type="submit" onclick="getEditDetail()">Save</button>
										<button class="btn default" type="button" onclick="cancelform()">Cancel</button>
									</div>
								</div>
									
							</div>
										<s:token/>
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