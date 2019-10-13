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

	window.history.pushState("", "", "PenaltyTicketAction!add");
	
});

</script>
<title>Insert title here</title>
</head>
<body>
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "PenaltyTicketAction!view");
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
						PENALTY TICKET TYPE<small></small>
					</h3>
					<font color="green"><s:actionmessage/></font>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
				</div>
				
					<div class="portlet box grey-cascade">
					
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Create Penalty Ticket Type
							</div>
							<div class="tools">
								
							</div>
						</div>
						<div class="portlet-body form">
							&nbsp;&nbsp;&nbsp;&nbsp;<font color="red"><span id="integerVal"></span></font>
							
							<form action="AddPenaltyTicketAction.action" class="form-horizontal" method="post">
								<font color="red"><s:actionerror/></font>  <br>
								
								<div class="form-group">
								<label class="col-md-3 control-label">Penalty Ticket Type :<font color="red">*</font></label>
								<div class="col-md-4">
									<input type="text" class="form-control" name="penaltyTicket.penaltyTicketName" maxLength="20"
										value="<s:property value="penaltyTicket.penaltyTicketName"/>">
									 <s:if test="fieldErrors.get('penaltyTicketName').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('penaltyTicketName').get(0)" /></span>
									</s:if> 
								</div>
							    </div>
							     
								<div class="form-group">
								<label class="col-md-3 control-label">Remarks:</label>
								<div class="col-md-4">
								<textarea rows="3" class="form-control" id="notes" name="penaltyTicket.notes" maxLength="100"><s:property value="penaltyTicket.notes" /></textarea>
								</div>
							    </div>
							  
							  <div class="form-group">
										<label class="col-md-3 control-label">Status:</label>
										<div class="col-md-3">
											<select class="form-control" name="penaltyTicket.status" >
												<option value="Active">Active</option>
												<!-- <option value="INACTIVE">INACTIVE</option> -->
											</select>									    
										</div>
							    </div>
																						
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn blue">Save</button>
										<button type="button" class="btn default" onclick = "javascript:location.href='PenaltyTicketAction!view';">Cancel</button>
									</div>
								</div>

								
							<s:token/>
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