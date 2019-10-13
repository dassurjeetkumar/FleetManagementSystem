	
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
	
	document.frm.action="updateMaintenanceTypeDetails.action";
	document.frm.submit();
}
function cancelform(){
	document.frm.action="maintenancetypelist.action";
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
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "maintenancetypelist.action");
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
	
					<div class="tab-content">
					<div class="row">
						<div class="col-md-12">
						<%if (edit.equalsIgnoreCase("Y")){%>
							<!-- BEGIN PAGE TITLE & BREADCRUMB-->
							<h3 class="page-title">
								MAINTENANCE TYPE <small></small>
							</h3>
							
							<!-- END PAGE TITLE & BREADCRUMB-->
						</div>
					</div>
							<div class="tab-pane active" id="tab_0">


							<div class="portlet box grey-cascade">
									<div class="portlet-title">
										<div class="caption">
											<i class="fa fa-gift"></i>Edit Maintenance Type
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
										<label class="col-md-3 control-label">Maintenance Type Id:<font color="red">*</font></label>
										<div class="col-md-4">
<%-- 										<input   name="maintenancetype.maintenance_type_id"  value='<s:property value="maintenancetype.maintenance_type_id" />'  id="maintenance_id" readonly> --%>
										<input id="maintenance_id" type="text" class="form-control" name="maintenancetype.maintenance_type_id"	value="<s:property value='maintenancetype.maintenance_type_id'/>" readonly />
											
										</div>
									</div>
								</div>
										
									<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Maintenance Type:<font color="red">*</font></label>
										<div class="col-md-4">
<%-- 										<input type="hidden"   name="maintenancetype.maintenance_type_id"  value='<s:property value="maintenancetype.maintenance_type_id" />'  id="maintenance_id" > --%>
											<input type="text" class="form-control" id="maintenance_type_name" maxlength="20" value='<s:property value="maintenancetype.maintenance_type"/>'
												name="maintenancetype.maintenance_type">
												<s:if test="fieldErrors.get('maintenancetype').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('maintenancetype').get(0)" /></span>
											</s:if>
										</div>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-3 control-label">Status :<font color="red">*</font></label>
									<div class="col-md-4">
										<select class="form-control" id="status" name="maintenancetype.status" <s:property value="maintenancetype.status"/> >
											<option id="active" value="ACTIVE">ACTIVE</option>
											<option id="deactive" value="INACTIVE">INACTIVE</option>
										</select>
										<script>
															var status = "<s:property value="maintenancetype.status"/>";
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
										<label class="col-md-3 control-label">Remarks:</label>

										<div class="col-md-4">
											<textarea rows="3" class="form-control" id="notes" maxlength="100"	name="maintenancetype.notes"><s:property value="maintenancetype.notes"/></textarea>
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