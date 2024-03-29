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
	function goView() {
		document.forms[0].action = 'breaktypeview.action';
		document.forms[0].submit();
	}
</script>
</head>
<body>
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "breaktypeview.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
%>

	<input type="text" id='a'>

	<div class="page-content-wrapper">
		<div class="page-content">
		<%if (access.equalsIgnoreCase("Y")){%>
		<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						BREAK TYPE  <small></small>
					</h3>

					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<%if(edit.equalsIgnoreCase("Y")){ %>
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">

						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Edit Break Type
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
							<form action="addeditedbreaktype.action" class="form-horizontal"
								method="post">
								<s:if test="%{updatestatus=='duplicate'}">
									<font color="red">Could not insert Duplicate BreakType</font>
								</s:if>
								<div class="form-body">
									<input type="hidden" name="breaktype.id"
										value='<s:property value="breaktype.id"/>' />
									<div class="form-group">
										<label class="col-md-3 control-label">Break Type Name:<font
											color="red">*</font></label>

										<div class="col-md-3">
											<input type="text" class="form-control" id="device_type_name"
												maxlength="60" name="breaktype.breakTypeName"
												value='<s:property value="breaktype.breakTypeName"/>'>
											<s:if test="fieldErrors.get('breaktypename').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('breaktypename').get(0)" /></span>
											</s:if>
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-md-3">Duration:<font
										color="red">*</font></label>
									<div class="col-md-2">
										<div class="input-group">
											<input type="text"
												value='<s:property value="breaktype.duration"/>'
												name="breaktype.duration" readonly="readonly"
												class="form-control timepicker timepicker-24"> <span
												class="input-group-btn">
												<button class="btn default" type="button">
													<i class="fa fa-clock-o"></i>
												</button>
											</span>
										</div>
										<s:if test="fieldErrors.get('duration').size() > 0">
											<span style="color: red;"><s:property
													value="fieldErrors.get('duration').get(0)" /></span>
										</s:if>
									</div>
								</div>



								<div class="form-group">
									<label class="col-md-3 control-label">Steering Hours
										Applicable:<font color="red">*</font></label>
									<div class="col-md-2">
										<select class="form-control" id="sphours"
											name="breaktype.steeringHours">
											<option value="1" id="styes">Yes</option>
											<option value="0" id="stno">No</option>
										</select>
										<script>
											var status = "<s:property value="breaktype.steeringHours"/>";
											if (status != undefined) {
												if (status == "1"
														|| status == "1") {
													document
															.getElementById("styes").selected = true;
												} else {
													document
															.getElementById("stno").selected = true;
												}
											}
										</script>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">Spread Hours
										Applicable:<font color="red">*</font></label>
									<div class="col-md-2">
										<select class="form-control" id="sphours"
											name="breaktype.spreadHours">
											<option value="1" id="spyes">Yes</option>
											<option value="0" id="spno">No</option>
										</select>
										<script>
											var status = "<s:property value="breaktype.spreadHours"/>";
											if (status != undefined) {
												if (status == "1"
														|| status == "1") {
													document
															.getElementById("spyes").selected = true;
												} else {
													document
															.getElementById("spno").selected = true;
												}
											}
										</script>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">OT Hours
										Applicable:<font color="red">*</font></label>
									<div class="col-md-2">
										<select class="form-control" id="othours"
											name="breaktype.ot_hours">
											<option value="1" id="otyes">Yes</option>
											<option value="0" id="otno">No</option>
										</select>
										<script>
											var status = "<s:property value="breaktype.ot_hours"/>";
											if (status != undefined) {
												if (status == "1"
														|| status == "1") {
													document
															.getElementById("otyes").selected = true;
												} else {
													document
															.getElementById("otno").selected = true;
												}
											}
										</script>
									</div>
								</div>
								
								
								<div class="form-group">
									<label class="col-md-3 control-label">Rest:<font color="red">*</font></label>
									<div class="col-md-2">
										<select class="form-control" id="rest"
											name="breaktype.rest"  >
											<option id="yothours" value="1">Yes</option>
											<option id="nothours" value="0">No</option>
										</select>
										<script>
															var status = "<s:property value="breaktype.rest"/>";
															if (status != undefined) {
																if (status == "1"
																		|| status == "1") {
																	document
																			.getElementById("yothours").selected = true;
																} else {
																	document
																			.getElementById("nothours").selected = true;
																}
															}
														</script>
									</div>
								</div>
								
								
								
								<div class="form-group">
									<label class="col-md-3 control-label">Status</label>
									<div class="col-md-2">
										<select class="form-control" id="status"
											name="breaktype.status">
											<option id="active" value="ACTIVE">ACTIVE</option>
											<option id="deactive" value="INACTIVE">INACTIVE</option>

										</select>
										<script>
											var status = "<s:property value="breaktype.status"/>";
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
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn blue" onsubmit="validate()">Save</button>
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