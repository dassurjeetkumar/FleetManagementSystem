<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<!DOCTYPE html>
<html>
<head>
<script>
function resetForm(){
	/*document.getElementById('amount').value='';
	document.getElementById('effective_start_date').value='';
	document.getElementById('effect_end_date').value='';*/
	
	document.forms[0].action = "PassengerTypeAction!add";
	document.forms[0].submit();
}

$(document).ready(function() {

	window.history.pushState("", "", "PassengerTypeAction!add");
	
});
</script>
<title>Insert title here</title>
</head>
<body>
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "PassengerTypeAction!view");
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
						PASSENGER TYPE<small></small>
					</h3>
					<font color="green"><s:actionmessage/></font>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
					
				</div>
					<div class="portlet box grey-cascade">
					
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Create Passenger Type
							</div>
							<div class="tools">
								
							</div>
						</div>
						<div class="portlet-body form">
							&nbsp;&nbsp;&nbsp;&nbsp;<font color="red"><span id="integerVal"></span></font>
							
							<form action="AddPassengerTypeAction.action" class="form-horizontal" method="post">
								<font color="red"><s:actionerror/></font>  <br>
								
								
								<div class="form-group">
								<label class="col-md-3 control-label">Passenger Type:<font color="red">*</font></label>
								<div class="col-md-4">
									<input id="amount" type="text" class="form-control" name="passengerType.passengerTypeName" maxLength="60"
										value="<s:property value="passengerType.passengerTypeName"/>">
									 <s:if test="fieldErrors.get('passengerTypeName').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('passengerTypeName').get(0)" /></span>
									</s:if> 
								</div>
							    </div>
							  
							  <div class="form-group">
									<label class="control-label col-md-3">Effective Start Date:<font color="red">*</font></label>
									<div class="col-md-4">
										<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd/mm/yyyy"
															data-date-viewmode="years" data-date-start-date="+0d">
											<input type="text" size="16" readonly class="form-control"
												id="effective_start_date"
												name="passengerType.effectiveStartDate"
												value="<s:property value='passengerType.effectiveStartDate' />" > 
												<span class="input-group-btn" >
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
											<script>
												var curr_date=new Date().toJSON().slice(0,10);
		                                        curr_date=curr_date.split("-");
		                                        curr_date=curr_date[2]+"/"+curr_date[1]+"/"+curr_date[0];
		                                        $('#effective_start_date').val(curr_date);
											</script>							
										</div>
										<s:if test="fieldErrors.get('effectiveStartDate').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('effectiveStartDate').get(0)" /></span>
										</s:if>
										<!-- /input-group -->
									</div>
							  </div>
								
							  <div class="form-group">
									<label class="control-label col-md-3">Effective End Date:<font color="red">*</font></label>
									<div class="col-md-4">
									<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd/mm/yyyy"
															data-date-viewmode="years" data-date-start-date="+0d">
											<input type="text" size="16" readonly class="form-control"
												id="effect_end_date" name="passengerType.effectiveEndDate"
												value="<s:property value='passengerType.effectiveEndDate' />" >
											<span class="input-group-btn">
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
										</div>
										<s:if test="fieldErrors.get('effectiveEndDate').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('effectiveEndDate').get(0)" /></span>
										</s:if>
										<!-- /input-group -->
									</div>
								</div>

								
								<div class="form-group">
										<label class="col-md-3 control-label">Status:</label>
										<div class="col-md-3">
											<select class="form-control" name="passengerType.status" >
												<option value="Active">Active</option>
												<!-- <option value="INACTIVE">INACTIVE</option> -->
											</select>									    
										</div>
							    </div>
								
															
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn blue">Save</button>
										<button type="button" class="btn default" onclick="resetForm();">Reset</button>
										<button type="button" class="btn default" onclick = "javascript:location.href='PassengerTypeAction!view';">Cancel</button>
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