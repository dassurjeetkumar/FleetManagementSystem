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
		document.forms[0].action = 'viewReportMaster.action';
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
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "viewReportMaster.action");
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
						REPORT MASTER <small></small>
					</h3>

					<!-- END PAGE TITLE & BREADCRUMB-->
					<div class="portlet box grey-cascade">
					

						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Edit Report Master
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
							<form action="editReportMasterAction.action" class="form-horizontal" method="post">
							<s:if test="%{updatestaus=='duplicate'}"><font color="red">Could not insert Duplicate Report Master Name</font></s:if>
							<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Report Master Id: </label>
										<div class="col-md-4">
											 <input type="text" class="form-control" name="reportmaster.report_master_id" 
											 id="report_master_id" maxlength="8" 
											 value='<s:property value="reportmaster.report_master_id"/>' readonly="readonly">
										</div>
									</div>
								</div>
															
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Report Master Name :<font color="red">*</font></label>

										<div class="col-md-4">
											<input type="text" class="form-control" id="report_master_name" maxlength="20"
												name="reportmaster.report_master_name" 
												value='<s:property value="reportmaster.report_master_name"/>'>												
												<s:if test="fieldErrors.get('report_master_name').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('report_master_name').get(0)" /></span>
											</s:if>
										</div>
									</div>
								</div>
								<div class="form-body">
				                <div class="form-group">
								<label class="control-label col-md-3"> Status:</label>
								<div class="col-md-4">
									<div class="input-group input-medium" style="width: auto">
									<select class="form-control" id="status" name="reportmaster.status">

																	<option id="active" value="ACTIVE">ACTIVE</option>
																	<option id="deactive" value="INACTIVE">INACTIVE</option>
																</select>
										<!-- <input type="text" class="form-control" readonly="readonly" 
											value="ACTIVE" name="battery.status" /> -->
									</div>
									<script>
															var status = "<s:property value="reportmaster.status"/>";
															if (status != undefined) {
																if (status == "ACTIVE" || status == "ACTIVE") {
																	document.getElementById("active").selected = true;
																} else {
																	document.getElementById("deactive").selected = true;
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

<%-- <script>
$(document).ready(function() {
	//window.history.pushState("","", "ServiceTypeList.action");
	
	   var w=$('#errorMsg span').html();
	   //alert(w);
	    w=w.replace(/@/g,"<br>");

	   $('#errorMsg').html(''+w+'');
  
});
</script> --%>