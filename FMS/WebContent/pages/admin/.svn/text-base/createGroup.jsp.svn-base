<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.trimax.its.model.User"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<!DOCTYPE html>
<html>
<head>
<script>
function goView() {
	

		/* var val = $("input[type='checkbox']").val(); */
		//alert(val);
		document.forms[0].action = 'viewGroup.action';
		document.forms[0].submit();

	
}


<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "viewGroup.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
%>

</script>
<style>
h1.intro {color:red;font-size:14px;}

	
</style>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
</head>
<body onload="clearForm()">
<div class="page-content-wrapper">
	<div class="page-content">
	<%if (access.equalsIgnoreCase("Y")){%>
		<%if(create.equalsIgnoreCase("Y")){ %>
<div class="tab-content">
							<div class="tab-pane active" id="tab_0">
							<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						USER GROUP <small></small>
					</h3>
					<font color="green"><s:actionmessage/></font>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
				
			
									<div class="portlet box grey-cascade">
									<div class="portlet-title">
										<div class="caption">
											<i class="fa fa-gift"></i>Create User Group
										</div>
										
									</div>
									<div class="portlet-body form">
								
										<!-- BEGIN FORM-->
											<s:if test="hasActionErrors()">
					<font color="Red"> <s:actionerror />
					</font>
					</s:if>
					
					<s:if test="hasActionMessages()">
					<font color="green"> <s:actionmessage />
					
				</font>
					</s:if>
										<form action="addNewGroup.action" class="form-horizontal" method="post">
											<div class="form-body">
											
												<div class="form-group">
													<label class="col-md-3 control-label">Group name<font color="red">*</font></label>
													   <div class="col-md-4">
														 <div class="input-group">
															 <input type="text" name="groupMaster.group_name" id="groupName" maxlength="50" class="form-control" value="<s:property value="groupMaster.group_name" />">
														    <s:if test="fieldErrors.get('groupName').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('groupName').get(0)" /></span>
									</s:if>	 	
														</div>
													 </div>
												 </div>
												 <div class="form-group">
													<label class="col-md-3 control-label">Status<font color="red">*</font></label>
													   <div class="col-md-4">
														 <div class="input-group">
															
												
													<%--  <select class="form-control" id="groupMaster.status" name="groupMaster.status" >
													 	<option id="select"  value="0">select</option>
														<option id="ACTIVE"  value="ACTIVE">ACTIVE</option>
														<option id="INACTIVE" value="INACTIVE">INACTIVE</option>
													</select> 
												 <s:if test="fieldErrors.get('status').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('status').get(0)" /></span>
									</s:if>	 	 --%>
														 <select class="form-control" id="groupMaster.status"
											name="groupMaster.status">
											<s:if test="groupMaster.status=='ACTIVE'">
												<option value="ACTIVE" selected="selected">ACTIVE</option>
												<option value="INACTIVE">INACTIVE</option>
											</s:if>
											<s:elseif test="groupMaster.status=='INACTIVE'">
												<option value="ACTIVE">ACTIVE</option>
												<option value="INACTIVE" selected="selected">INACTIVE</option>
											</s:elseif>
											<s:else>
												<option id="select" value="0">select</option>
												<option value="ACTIVE">ACTIVE</option>
												<option value="INACTIVE">INACTIVE</option>
											</s:else>
											
										</select>
														  <s:if test="fieldErrors.get('status').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('status').get(0)" /></span>
									</s:if>	
														    	
														</div>
													 </div>
												 </div>										
											
											<div class="form-actions fluid">
												<div class="col-md-offset-3 col-md-9">
													<button type="submit" class="btn blue">Save</button>
													<button type="button" id="cancel" class="btn default" onclick="goView()">Cancel</button>
													<h1 class="intro"><s:property value="msg" /></h1>
												</div>
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
								
