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
	function validate() {

		if (document.getElementById("device_type_name").value == '') {
			alert('Please Enter a New Device Type');
			return false;
		}
		if (document.getElementById("note").value == '') {
			alert('Please Enter Remarks');
			return false;
		}

		document.forms[0].action = 'createDeviceTypeAction.action';
		document.forms[0].submit();
	}
	function goView() {
		document.forms[0].action = 'sheduleTypeAction.action';
		document.forms[0].submit();
	}
	function getStatus() {
		result = "<option value='ACTIVE'>ACTIVE</option><option value='INACTIVE'>INACTIVE</option>";
		document.getElementById('status').innerHTML = result;
	}
</script>

<title>Insert title here</title>
</head>
<body>
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "sheduleTypeAction.action");
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
		<%if(edit.equalsIgnoreCase("Y")){ %>
	<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						SCHEDULE TYPE  <small></small>
					</h3>

					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Edit Schedule Type
							</div>
							
						</div>

						<div class="portlet-body form">
							<s:if test="hasActionErrors()">
								<div class="alert alert-danger">
									<button class="close" data-close="alert"></button>
							<span id="errorMsg" style="color:red;word-wrap: break-word;" ><s:actionerror/></span>
									
								</div>


							</s:if>
							<!-- BEGIN FORM-->
							<form action="editscheduletypeAction.action" method="post"
								class="form-horizontal">
								<s:if test="%{updatemsg=='duplicate'}"><font color="red">Could not insert Duplicate ScheduleType</font></s:if>
								<input type="hidden" name="scheduletype.schedule_type_id"
									value='<s:property value="scheduletype.schedule_type_id"/>' />
								<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Schedule Type
											Name:<font color="red">*</font></label>
									
										<div class="col-md-4">
											<input type="text" class="form-control" id="scheduletypename" maxlength="50"
												name="scheduletype.scheduleName"
												value='<s:property value="scheduletype.scheduleName"/>'>
												<s:if test="fieldErrors.get('schedulename').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('schedulename').get(0)" /></span>
											</s:if>	
											
										</div>
										
									</div>
									
								</div>

								<div class="form-group">
									<label class="col-md-3 control-label">Status</label>
									<div class="col-md-4">
										<select class="form-control" id="status"
											name="scheduletype.status" >
											<option id="active" value="ACTIVE">ACTIVE</option>
											<option id="deactive" value="INACTIVE">INACTIVE</option>
										</select>
										<script>
											var status = "<s:property value="scheduletype.status"/>";
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
										<label class="col-md-3 control-label">Remarks</label>
										<div class="col-md-4">
													<textarea class="form-control"   name="scheduletype.notes"  id="ven.note"  maxlength="100"  ><s:property value="scheduletype.notes" /></textarea>
														
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
