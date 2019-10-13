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
		document.forms[0].action = 'componentTypeView.action';
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
<%if (create.equalsIgnoreCase("Y")){%>
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
								<i class="fa fa-gift"></i>Create Component Type
							</div>
							
						</div>
						
						<div class="portlet-body form">
						<s:if test="hasActionErrors()">
							<div class="alert alert-danger">
								<button class="close" data-close="alert"></button>
								<span> <s:actionerror />
								</span>
							</div>


						</s:if>
						
							<!-- BEGIN FORM-->
							<form action="createComponentTypeAction.action" method="POST"  class="form-horizontal">
							<s:if test="%{insertstaus=='duplicate'}"><font color="red">Could not insert Duplicate ComponentType</font></s:if>
								<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Component Type:<font color="red">*</font></label>

										<div class="col-md-4">
											<input type="text" class="form-control" id="component_type_name" maxlength="20"
												name="componentType.component_type_name" value='<s:property value="componentType.component_type_name"/>'>
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
											<textarea rows="3" class="form-control" id="notes" name="componentType.notes" maxlength="100" ><s:property value="componentType.notes"/></textarea>
<%-- 										<s:if test="fieldErrors.get('componentTypenotes').size() > 0"> --%>
<%-- 		     								<span style="color:red;"><s:property value="fieldErrors.get('componentTypenotes').get(0)" /></span> --%>
<%-- 											</s:if> --%>
										</div>
									</div>
								</div>
								<div class="form-body">
								<div class="form-group">
									<label class="col-md-3 control-label">Status:</label>
									<div class="col-md-4">
										<select class="form-control" id="status" name="componentType.status">
											<option value="ACTIVE">ACTIVE</option>
											
										</select>

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