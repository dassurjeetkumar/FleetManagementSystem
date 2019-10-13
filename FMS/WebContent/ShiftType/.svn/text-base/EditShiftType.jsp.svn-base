<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>


<!DOCTYPE html>
<html>
<head>
<script>
</script>
<style>
h1.intro {
	color: red;
	font-size: 14px;
}
</style>
<script>
function goView() {

		/* var val = $("input[type='checkbox']").val(); */
		//alert(val);
		document.forms[0].action = 'showShiftType.action';
		document.forms[0].submit();


}







</script>
<script type="text/javascript" src="//www.google.com/jsapi">

</script>
</head>
<body>
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "showShiftType.action");
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
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
				
				<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
					DUTY TYPE <small></small>
					</h3>
					
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Edit Duty Type
							</div>
							<div class="tools">
								
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
							<form action="editShift.action" class="form-horizontal"
								method="post">
								<input type="hidden" value='<s:property value="shiftype.id"/>' id="id"  name="shiftype.id" >
								<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Duty Type Name<font color="red">*</font></label>
										<div class="col-md-4">
											<div class="input-group">
												<input type="text" name="shiftype.ShiftTypeName"
													id="ShiftTypeName" class="form-control"
													value="<s:property value="shiftype.ShiftTypeName" />" maxlength="50">
												<s:if test="fieldErrors.get('ShiftTypeName').size() > 0">
													<span style="color: red;"><s:property
															value="fieldErrors.get('ShiftTypeName').get(0)" /></span>
												</s:if>
											</div>
										</div>
									</div>
									
									
									<div class="form-group">
										<label class="col-md-3 control-label">Schedule Type<font color="red">*</font></label>
										<div class="col-md-4">
										
													 <s:select list="sheduletypelist" id="schedule_type_id" name="shiftype.schedule_type_id.schedule_type_id" 
													cssClass="select2_category form-control" headerKey="0" headerValue="Select"></s:select> 
													
												<s:if test="fieldErrors.get('schedule_type_id').size() > 0">
													<span style="color: red;"><s:property
															value="fieldErrors.get('schedule_type_id').get(0)" /></span>
												</s:if>
												
												
										
										</div>
									</div>
									
									
									<div class="form-group">
										<label class="col-md-3 control-label">Duration<font color="red">*</font></label>
										<div class="col-md-4">
											
												<input type="text" class="form-control input-small timepicker timepicker-24" name="shiftype.duration" value='<s:property value="shiftype.duration"/>' readonly="readonly">
												<s:if test="fieldErrors.get('duration').size() > 0">
													<span style="color: red;"><s:property
															value="fieldErrors.get('duration').get(0)" /></span>
												</s:if>
										
										</div>
									</div>
									
									<div class="form-group">
												<label class="col-md-3 control-label">Remarks</label>
												<div class="col-md-4">
													<textarea class="form-control"   name="shiftype.notes"   id="notes"  maxlength="100"  ><s:property value="shiftype.notes" /></textarea>
														<s:if test="fieldErrors.get('notes').size() > 0">
					     								<span style="color:red;"><s:property value="fieldErrors.get('notes').get(0)" /></span>
														</s:if>
												</div>
											</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">status<font color="red">*</font></label>
										<div class="col-md-4">
											
												<select class="form-control" name="shiftype.status" id="status">
												<option id="ACTIVE" selected="selected" value="ACTIVE">ACTIVE</option>
													<option id="INACTIVE" value="INACTIVE">INACTIVE</option>
												</select>
												
													<script>
														var status = "<s:property value="shiftype.status"/>";
															if (status != undefined) {
																if (status == "ACTIVE" || status == "active" || status == "Active") {
																	document.getElementById("ACTIVE").selected = true;
																} else {
																	document.getElementById("INACTIVE").selected = true;
																}
															}
															</script>
											
													
										</div>
									</div>
									<div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9">
											<button type="submit" class="btn blue">Save</button>
											<button type="button" id="cancel" class="btn default" onclick="goView()">Cancel</button>
											
										</div>
									</div>
								</div>
								<s:token />
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
