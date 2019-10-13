	
	<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>


<!DOCTYPE body PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<script>
function getEditDetail(){
	var vendorid=document.getElementById("venid").value;
	
	document.frm.action="EditVendorDetails.action";
	document.frm.submit();
}

function cancelform(){
	document.frm.action="ShowVendorList.action";
	document.frm.submit();
}

</script>

<div class="page-content-wrapper">
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ShowVendorList.action");
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
	<div class="tab-pane active" id="tab_0">
				
				<div class="row">
					<%if (edit.equalsIgnoreCase("Y")){%>
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
					VENDOR <small></small>
					</h3>
					
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
							<div class="portlet box grey-cascade">
									<div class="portlet-title">
										<div class="caption">
											<i class="fa fa-gift"></i>Edit Vendor Details
										</div>
										
									</div>
									<div class="portlet-body form">
										<!-- BEGIN FORM-->
										
										<s:if test="hasActionErrors()">
											<span id="errorMsg" style="color:red;word-wrap: break-word;" ><s:actionerror/></span>
				
					</font>
					</s:if>
					
					<s:if test="hasActionMessages()">
					<font color="green"> <s:actionmessage />
					
				</font>
					</s:if>
										<form  class="form-horizontal" name="frm" method="POST">
										
											<div class="form-body">
													<div class="form-group">
													<label class="col-md-3 control-label">Vendor Name<font color="red">*</font> :</label>
													<div class="col-md-4">
													<input type="hidden"   name="ven.id"   value='<s:property value="ven.id" />'  id="venid" >
													<input type="text" class="form-control" name="ven.vendor_name"   value='<s:property value="ven.vendor_name" />' maxlength="50" id="ven.vendor_name" >
													<s:if test="fieldErrors.get('vendor_name').size() > 0">
					     								<span style="color:red;"><s:property value="fieldErrors.get('vendor_name').get(0)" /></span>
														</s:if>
												</div>
												</div>
												
												
												<div class="form-group">
													<label class="col-md-3 control-label">Vendor Contact Person<font color="red">*</font> </label>
													<div class="col-md-4">
														<input type="text" class="form-control"  name="ven.vendor_contact_person" value='<s:property value="ven.vendor_contact_person" />' id="ven.vendor_contact_person" maxlength="50">
														<s:if test="fieldErrors.get('vendor_contact_person').size() > 0">
					     								<span style="color:red;"><s:property value="fieldErrors.get('vendor_contact_person').get(0)" /></span>
														</s:if>
													</div>
												</div>
												
												<div class="form-group">
												<label class="col-md-3 control-label">Remarks</label>
												<div class="col-md-4">
													<textarea class="form-control"  name="ven.note" id="ven.note" maxlength="100"><s:property value="ven.note"/></textarea>
													<s:if test="fieldErrors.get('note').size() > 0">
					     								<span style="color:red;"><s:property value="fieldErrors.get('note').get(0)" /></span>
														</s:if>
												</div>
											</div>
								
												<div class="form-group">
												<label class="col-md-3 control-label">Status</label>
												<div class="col-md-4">
													 <select class="form-control" id="ven.status" name="ven.status" >
														<option  id="ACTIVE" value="ACTIVE" >ACTIVE</option>
														<option id="INACTIVE" value="INACTIVE">INACTIVE</option>
													</select>  
													<script>
														var status = "<s:property value="ven.status"/>";
															if (status != undefined) {
																if (status == "ACTIVE") {
																	document
																			.getElementById("ACTIVE").selected = true;
																} else {
																	document
																			.getElementById("INACTIVE").selected = true;
																}
															}
															</script>
													
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
<script>
$(document).ready(function() {
	//window.history.pushState("","", "ServiceTypeList.action");
	
	   var w=$('#errorMsg span').html();
	   //alert(w);
	    w=w.replace(/@/g,"<br>");

	   $('#errorMsg').html(''+w+'');
  
});
</script>
