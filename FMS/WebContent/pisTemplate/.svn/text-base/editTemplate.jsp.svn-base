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
// 	function validate() {

// // 		if (document.getElementById("component_type_name").value == '') {
// // 			alert('Please Enter a New Component');
// // 			return false;
// // 		}
// // 		if (document.getElementById("note").value == '') {
// // 			alert('Please Enter Note');
// // 			return false;
// // 		}

// 		document.forms[0].action = 'editPisTemplateAction.action';
// 		document.forms[0].submit();
// 	}
	function goView()
	{
		document.forms[0].action = 'viewPisTemplate.action';
		document.forms[0].submit();
	}
// 	$(document).ready(function() {
// 		window.history.pushState("", "", "componentTypeView.action");
		
// 	}); 
</script>
</head>
<body>
	<input type="text" id='a'>

	<div class="page-content-wrapper">
 <%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "viewPisTemplate.action");
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
						PIS TEMPLATE MASTER <small></small>
					</h3>

					<!-- END PAGE TITLE & BREADCRUMB-->
					<div class="portlet box grey-cascade">
					

						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Edit PIS Template Master
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
							<form action="editPisTemplateAction.action" class="form-horizontal" method="post">
							<s:if test="%{updatestaus=='duplicate'}"><font color="red">Could not Update Duplicate PIS Template Name</font></s:if>
							<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">PIS Template Id: </label>
										<div class="col-md-4">
											 <input type="text" class="form-control" name="pisTemplateMaster.template_id" 
											 id="template_id" maxlength="8" 
											 value='<s:property value="pisTemplateMaster.template_id"/>' readonly="readonly">
										</div>
									</div>
								</div>
															
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">PIS Template Name :<font color="red">*</font></label>

										<div class="col-md-4">
											<input type="text" class="form-control" id="template_name" maxlength="20"
												name="pisTemplateMaster.template_name" 
												value='<s:property value="pisTemplateMaster.template_name"/>'>												
												<s:if test="fieldErrors.get('template_name').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('template_name').get(0)" /></span>
											</s:if>
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

<%-- <script>
$(document).ready(function() {
	//window.history.pushState("","", "ServiceTypeList.action");
	
	   var w=$('#errorMsg span').html();
	   //alert(w);
	    w=w.replace(/@/g,"<br>");

	   $('#errorMsg').html(''+w+'');
  
});
</script> --%>