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
// 	function validate() {
		
// 		if (document.getElementById("device_type_name").value == '') {
// 			alert('Please Enter a New Device Type');
// 			return false;
// 		}
// 		if (document.getElementById("note").value == '') {
// 			alert('Please Enter Note');
// 			return false;
// 		}

// 		document.forms[0].action = 'createcomplaintAction.action';
// 		document.forms[0].submit();
// 	}
	function goView()
	{
		document.forms[0].action = 'memoNoticeAction.action';
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
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "memoNoticeAction.action");
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
	<%if (access.equalsIgnoreCase("Y")){%>
	<%if (edit.equalsIgnoreCase("Y")){%>
		<div class="page-content">
				<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
				MEMO NOTICE
				</h3>
			</div>
		</div>
			<div class="tab-content">
			
				<div class="tab-pane active" id="tab_0">
				<font color="red"><s:actionmessage/></font>
					<div class="portlet box grey-cascade">
					
						<div class="portlet-title">
						
							<div class="caption">
								<i class="fa fa-gift"></i>Create Memo Notice
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
							<form action="createMemoNoticeAction.action" method="post"  id="createComplaintId" class="form-horizontal">
							<s:if test="%{insertstatus=='duplicate'}"><font color="red">Could not insert Duplicate PeakHours</font></s:if>
								
								
								<div class="form-body"> 

									<div class="form-group">
										<label class="col-md-3 control-label">
											Memo Type:<font color="red">*</font>
										</label>

										<div class="col-md-4"> 
<%-- 											 <select class="form-control" id="devicetypeId"
<%-- 												onclick="getDeviceName()" 
<%-- 												name="device.device.device_type_id"> 

<%-- 												<option --%>
<%-- 													value='<s:property value="device.device.device_type_id"/>'> --%>
<%-- 													<s:property value="device.device.device_type_name" /> --%>
<!-- 												</option> --%> -->
<%-- 											</select>   --%> 
 											<s:select list="memotypelist" id="complainttakenId" name="memo.memotype.memo_type_id"  
													cssClass="select2_category form-control" headerKey="0" headerValue="Select"></s:select> 
 												<s:if test="fieldErrors.get('memo_type_id').size() > 0"> 
		     								<span style="color:red;"><s:property value="fieldErrors.get('memo_type_id').get(0)" /></span> 
 											</s:if> 
										</div>
									</div>
								</div>
								
								
								<div class="form-body"> 

									<div class="form-group">
										<label class="col-md-3 control-label">
											User Name:<font color="red">*</font>
										</label>

										<div class="col-md-4"> 
<%-- 											 <select class="form-control" id="devicetypeId"
<%-- 												onclick="getDeviceName()" 
<%-- 												name="device.device.device_type_id"> 

<%-- 												<option --%>
<%-- 													value='<s:property value="device.device.device_type_id"/>'> --%>
<%-- 													<s:property value="device.device.device_type_name" /> --%>
<!-- 												</option> --%> -->
<%-- 											</select>   --%> 
 											<s:select list="userlist" id="complainttakenId" name="memo.user.employee_id"  
													cssClass="select2_category form-control" headerKey="0" headerValue="Select"></s:select> 
 												<s:if test="fieldErrors.get('user_type_id').size() > 0"> 
		     								<span style="color:red;"><s:property value="fieldErrors.get('user_type_id').get(0)" /></span> 
 											</s:if> 
										</div>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-3 control-label">Status:<font color="red">*</font></label>
									<div class="col-md-4">
										<select class="form-control" id="status" name="memo.status">
											<option value="ACTIVE">ACTIVE</option>
											
										</select>

									</div>
								</div>
								
										
											
								
									<div class="form-group">
									<label class="control-label col-md-3">  Date:<font
										color="red">*</font></label>
									<div class="col-md-4">
										<div class="input-group input-medium date date-picker"	data-date-format="dd-mm-yyyy" data-date-viewmode="years" >
											<input class="form-control" type="text" size="16"
												name="memo.date"  id="memoiddate" 
												value='<s:property value="memo.date"/>'readonly>
											<span class="input-group-btn">
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
											<script>
											var curr_date=new Date().toJSON().slice(0,10);
											//alert(curr_date);
											curr_date=curr_date.split("-");
											
											curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
											$('#memoiddate').val(curr_date);
											</script>
										</div>
										<s:if test="fieldErrors.get('date').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('date').get(0)" /></span>
											</s:if>
									</div>
								</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Remarks</label>

										<div class="col-md-4">
											<textarea rows="3" class="form-control" id="notes" 
												name="memo.notes" maxlength="20" ><s:property value="memo.notes"/></textarea>
												<s:if test="fieldErrors.get('note').size() > 0"> 
		     								<span style="color:red;"><s:property value="fieldErrors.get('note').get(0)" /></span> 
 											</s:if> 
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