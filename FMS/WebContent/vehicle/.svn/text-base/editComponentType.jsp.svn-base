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

		if (document.getElementById("component_type_name").value == '') {
			alert('Please Enter a New Component');
			return false;
		}
		if (document.getElementById("note").value == '') {
			alert('Please Enter Remarks');
			return false;
		}

		document.forms[0].action = 'addeditedComponentType.action';
		document.forms[0].submit();
	}
	function goView()
	{
		document.forms[0].action = 'componentTypeView.action';
		document.forms[0].submit();
	}
	$(document).ready(function() {
		window.history.pushState("", "", "componentTypeView.action");
		
	}); 
</script>
</head>
<body>
	<input type="text" id='a'>

	<div class="page-content-wrapper">
	<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "componentTypeView.action");
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
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						COMPONENT TYPE <small></small>
					</h3>

					<!-- END PAGE TITLE & BREADCRUMB-->
					<div class="portlet box grey-cascade">
					

						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Edit Component Type
							</div>

						</div>

						<div class="portlet-body form">
							<s:if test="hasActionErrors()">
								<div class="alert alert-danger">
									<button class="close" data-close="alert"></button>
								<span id="errorMsg" style="color:red;word-wrap: break-word;" ><s:actionerror/></span>
									</span>
								</div>


							</s:if>
							<!-- BEGIN FORM-->
							<form action="addeditedComponentType.action" class="form-horizontal" method="post">
							<s:if test="%{updatestaus=='duplicate'}"><font color="red">Could not Update Duplicate Component Type</font></s:if>
							<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Id: </label>
										<div class="col-md-4">
											 <input type="text" class="form-control" name="componentType.component_type_id" 
											 id="component_type_id" maxlength="8" 
											 value='<s:property value="componentType.component_type_id"/>' readonly="readonly">
										</div>
									</div>
								</div>
															
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Component Type :<font color="red">*</font></label>

										<div class="col-md-4">
											<input type="text" class="form-control" id="component_type_name" maxlength="20"
												name="componentType.component_type_name" 
												value='<s:property value="componentType.component_type_name"/>'>
												
												 <%-- <input type="hidden" name="componentType.component_type_id" id="component_type_id" value='<s:property value="componentType.component_type_id"/>'> --%>
												 <s:if test="fieldErrors.get('componentTypeName').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('componentTypeName').get(0)" /></span>
											</s:if>
										</div>
									</div>
								</div>
								<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Remarks:</label>

										<div class="col-md-4">
											<textarea rows="3" class="form-control" id="notes" maxlength="100"
												name="componentType.notes"><s:property value="componentType.notes"/></textarea>
												<%-- <s:if test="fieldErrors.get('componentTypenotes').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('componentTypenotes').get(0)" /></span>
											</s:if> --%>
										</div>
									</div>
								</div>
								<div class="form-body">
				<div class="form-group">
								<label class="control-label col-md-3"> Status:</label>
								<div class="col-md-4">
									<div class="input-group input-medium" style="width: auto">
									<select class="form-control" id="status"
																	name="componentType.status">

																	<option id="active" value="ACTIVE">ACTIVE</option>
																	<option id="deactive" value="INACTIVE">INACTIVE</option>
																</select>
										<!-- <input type="text" class="form-control" readonly="readonly" 
											value="ACTIVE" name="battery.status" /> -->
									</div>
									<script>
															var status = "<s:property value="componentType.status"/>";
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