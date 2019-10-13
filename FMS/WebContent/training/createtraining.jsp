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
		document.forms[0].action = 'traininglist.action';
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

	<div class="page-content-wrapper">
		<div class="page-content">
<%if (access.equalsIgnoreCase("Y")){%>
<%if (create.equalsIgnoreCase("Y")){%>
			<div class="tab-content">
			
		<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						TRAINING <small></small>
					</h3>
					
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Create Training
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
							<form action="createTrainingAction.action"  method="post" class="form-horizontal">
								<div class="form-group">
									<label class="col-md-3 control-label">Training Type:<font color="red">*</font></label>
										<div class="col-md-4">
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
								
								<div class="form-group">
									<label class="col-md-3 control-label">Employee Name:<font color="red">*</font></label>
										<div class="col-md-4">
											<s:select list="employeelist" id="empId" name="training.employee.employee_id" 
  		cssClass="select2_category form-control" headerKey="0" headerValue="Select"></s:select>  
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
																if (status == "Close"
																		|| status == "Close") {
																	document
																			.getElementById("Close").selected = true;
																} else {
																	document
																			.getElementById("Open").selected = true;
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