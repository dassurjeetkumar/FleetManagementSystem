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
		document.forms[0].action = 'maintenancetypelist.action';
		document.forms[0].submit();
	}
</script>

<title>Insert title here</title>
</head>
<body>
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
	
	<%if (access.equalsIgnoreCase("Y")){%>
		<div class="page-content">

			<div class="tab-content">
			<div class="row">
			
		</div>
		<div class="row">
		<%if (create.equalsIgnoreCase("Y")){%>
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						MAINTENANCE TYPE<small></small>
					</h3>
					
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Create Maintenance Type
							</div>
							<!-- <div class="tools">
								<a href="javascript:;" class="collapse"> </a> 
								 <a href="javascript:;" class="reload"> </a> 
							</div> -->
						</div>
						
						<div class="portlet-body form">
						<div class="col-md-12" align="left" style="font-size: 1.1em">

				<%-- <span class="help-block" style="color: red; list-style: none"><s:actionerror /></span> --%>
				<span class="help-block" style="color: red; list-style: none"><s:actionmessage /></span>
			</div>
						
							<!-- BEGIN FORM-->
							<form action="CreateMaintenanceTypeAction.action"  method="post" class="form-horizontal">
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Maintenance Type:<font color="red">*</font></label>
										<div class="col-md-4">
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
										<select class="form-control" id="status" name="maintenancetype.status"  >
											<option id="active" value="ACTIVE">ACTIVE</option>
											<option id="deactive" value="INACTIVE">INACTIVE</option>
										</select>
										<script>
															var status = "<s:property value="maintenancetype.status"/>";
															if (status != undefined) {
																if (status == "INACTIVE"
																		|| status == "INACTIVE") {
																	document
																			.getElementById("deactive").selected = true;
																} else {
																	document
																			.getElementById("active").selected = true;
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
										<button  type="submit" class="btn blue" onSubmit="validate()">Save</button>
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