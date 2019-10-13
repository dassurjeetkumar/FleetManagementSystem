<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
<!DOCTYPE html>
<html>
<head>
<script>
jQuery(document).ready(function(){

	 SelectElement('<s:property value="pisCustomer.status"/>');

});

function SelectElement(valueToSelect)
{ 
var element = document.getElementById('status');
element.value = valueToSelect;
}

</script>

</head>
<body>
	<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "customerView.action");
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
						PIS CUSTOMER<small></small>
					</h3>
					<font color="green"><s:actionmessage/></font>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
				</div>
			
					<div class="portlet box grey-cascade">

						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Edit PIS Customer
							</div>

						</div>
				
							<!-- BEGIN FORM-->
							
						<div class="portlet-body form">	
						&nbsp;&nbsp;&nbsp;&nbsp;<font color="red"><span id="integerVal"></span></font>
							
							<form action="UpdateCustomerAction.action" class="form-horizontal" method="post">
								<font color="red"><s:actionerror/></font>  
								<font color="red"><span id="integerVal"></span></font>
							
								
								<div class="form-group">
								<label class="col-md-3 control-label">Customer Id:</label>
								<div class="col-md-4">
									<input type="text" name="pisCustomer.id" readonly="readonly" class="form-control" value="<s:property value='pisCustomer.id'/>" />
								</div>
							    </div>
								
								<div class="form-group">
								<label class="col-md-3 control-label">Customer Name :<font color="red">*</font></label>
								<div class="col-md-4">
									<input type="text" class="form-control" name="pisCustomer.custName" maxLength="50"
										value="<s:property value="pisCustomer.custName"/>">
									 <s:if test="fieldErrors.get('custName').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('custName').get(0)" /></span>
									</s:if> 
								</div>
							    </div>
							    
							    <div class="form-group">
								<label class="col-md-3 control-label">Address :</label>
								<div class="col-md-4">
								<textarea rows="3" class="form-control" id="notes" name="pisCustomer.custAddress" maxLength="100"><s:property value="pisCustomer.notes" /></textarea>
								</div>
							    </div>
							    
							    <div class="form-group">
								<label class="col-md-3 control-label">City :</label>
								<div class="col-md-4">
									<input type="text" class="form-control" name="pisCustomer.custCity" maxLength="30"
										value="<s:property value="pisCustomer.custCity"/>">
									<s:if test="fieldErrors.get('custCity').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('custCity').get(0)" /></span>
									</s:if> 
								</div>
							    </div>
							    
							    <div class="form-group">
								<label class="col-md-3 control-label">State :</label>
								<div class="col-md-4">
									<input type="text" class="form-control" name="pisCustomer.custState" maxLength="30"
										value="<s:property value="pisCustomer.custState"/>">
									<s:if test="fieldErrors.get('custState').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('custState').get(0)" /></span>
									</s:if> 
								</div>
							    </div>
							    
							    <div class="form-group">
								<label class="col-md-3 control-label">Country :</label>
								<div class="col-md-4">
									<input type="text" class="form-control" name="pisCustomer.custCountry" maxLength="30"
										value="<s:property value="pisCustomer.custCountry"/>">
									<s:if test="fieldErrors.get('custCountry').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('custCountry').get(0)" /></span>
									</s:if> 
								</div>
							    </div>
							    
							    <div class="form-group">
								<label class="col-md-3 control-label">Email :<font color="red">*</font></label>
								<div class="col-md-4">
									<input type="text" class="form-control" name="pisCustomer.custEmail" maxLength="30"
										value="<s:property value="pisCustomer.custEmail"/>">
									 <s:if test="fieldErrors.get('custEmail').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('custEmail').get(0)" /></span>
									</s:if> 
								</div>
							    </div>
							    
							    <div class="form-group">
								<label class="col-md-3 control-label">Website :</label>
								<div class="col-md-4">
									<input type="text" class="form-control" name="pisCustomer.custWebsite" maxLength="100"
										value="<s:property value="pisCustomer.custWebsite"/>">	
									<s:if test="fieldErrors.get('custWebsite').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('custWebsite').get(0)" /></span>
									</s:if> 								
								</div>
							    </div>
							     
							     <div class="form-group">
								<label class="col-md-3 control-label">Telephone :</label>
								<div class="col-md-4">
									<input type="text" class="form-control" name="pisCustomer.custPhone" maxLength="12"
										value="<s:property value="pisCustomer.custPhone"/>">	
									<s:if test="fieldErrors.get('custPhone').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('custPhone').get(0)" /></span>
									</s:if> 						 
								</div>
							    </div>
							     
							     <div class="form-group">
								<label class="col-md-3 control-label">Mobile Number :</label>
								<div class="col-md-4">
									<input type="text" class="form-control" name="pisCustomer.custCell" maxLength="12"
										value="<s:property value="pisCustomer.custCell"/>">	
									<s:if test="fieldErrors.get('custCell').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('custCell').get(0)" /></span>
									</s:if> 								 
								</div>
							    </div>
							    
							    <div class="form-group">
								<label class="col-md-3 control-label">Contact Person Name :</label>
								<div class="col-md-4">
									<input type="text" class="form-control" name="pisCustomer.custContactPersonName" maxLength="30"
										value="<s:property value="pisCustomer.custContactPersonName"/>">
									<s:if test="fieldErrors.get('custContactPersonName').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('custContactPersonName').get(0)" /></span>
									</s:if> 
								</div>
							    </div>
							    
							    <div class="form-group">
								<label class="col-md-3 control-label">Contact Person Email :</label>
								<div class="col-md-4">
									<input type="text" class="form-control" name="pisCustomer.custContactPersonEmail" maxLength="30"
										value="<s:property value="pisCustomer.custContactPersonEmail"/>">	
									<s:if test="fieldErrors.get('custContactPersonEmail').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('custContactPersonEmail').get(0)" /></span>
									</s:if> 								
								</div>
							    </div>
							    
							    <div class="form-group">
								<label class="col-md-3 control-label">Contact Person Phone :</label>
								<div class="col-md-4">
									<input type="text" class="form-control" name="pisCustomer.custContactPersonPhone" maxLength="12"
										value="<s:property value="pisCustomer.custContactPersonPhone"/>">	
									<s:if test="fieldErrors.get('custContactPersonPhone').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('custContactPersonPhone').get(0)" /></span>
									</s:if> 								
								</div>
							    </div>	
							  
							  <div class="form-group">
										<label class="col-md-3 control-label">Status:</label>
										<div class="col-md-3">
											<select class="form-control" name="pisCustomer.status" >
												<option value="Active">Active</option>
												<option value="Inactive">Inactive</option>
											</select>									    
										</div>
							    </div>
																						
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn blue">Save</button>
										<button type="button" class="btn default" onclick = "javascript:location.href='customerView.action';">Cancel</button>
									</div>
								</div>

								
							<s:token/>
							</form>
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