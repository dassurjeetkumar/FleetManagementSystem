<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
<!DOCTYPE html>
<html>
<head>


<script>
$(document).ready(function() {

	window.history.pushState("", "", "TicketTypeAction!add");
	
});

</script>
<title>Insert title here</title>
</head>
<body onload="getFareMaster();">
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "TicketTypeAction!view");
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
	<%if(create.equalsIgnoreCase("Y")){ %>
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
				<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						TICKET TYPE <small></small>
					</h3>
					<font color="green"><s:actionmessage/></font>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
				</div>
							
				
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Create Ticket Type
							</div>
							<div class="tools">
								
							</div>
						</div>
						
						<div class="portlet-body form">
							<!-- BEGIN FORM-->
							<form action="AddTicketTypeAction" class="form-horizontal" method="post">
							<font color="red"><s:actionerror/></font>
							<br><br>	
																				
								<div class="form-group">
								<label class="col-md-3 control-label">Ticket Type Name:<font color="red">*</font></label>
								<div class="col-md-4">
									<input type="text" class="form-control" name="ticketType.ticketTypeName" maxLength="60"
										value="<s:property value="ticketType.ticketTypeName"/>">
									<s:if test="fieldErrors.get('ticketTypeName').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('ticketTypeName').get(0)" /></span>
									</s:if>
								</div>
							    </div>
							    
							    
							    
							    <div class="form-group">
										<label class="col-md-3 control-label">Status</label>
										<div class="col-md-3">
											<select id="status" class="form-control" name="ticketType.status" >												
												<option value="Active">Active</option>
												<!-- <option value="Inactive">Inactive</option>  -->
											</select>									    
										</div>
								</div>
								
								
							    			
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn blue" >Save</button>
										<button type="reset" class="btn default" onclick="resetform()">Reset</button>
										<button type="button" class="btn default" onclick = "javascript:location.href='TicketTypeAction!view';">Cancel</button>										
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