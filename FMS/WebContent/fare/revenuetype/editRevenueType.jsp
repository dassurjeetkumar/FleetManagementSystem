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

	 SelectElement('<s:property value="revenueType.status"/>');

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
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "RevenueTypeAction!view");
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
						REVENUE TYPE <small></small>
					</h3>
					<b><font color="green"><s:actionmessage/></font></b>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
				</div>
			
					<div class="portlet box grey-cascade">

						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Edit Revenue Type
							</div>

						</div>
					
							<!-- BEGIN FORM-->
							
						<div class="portlet-body form">	
							
							<form action="EditRevenueTypeAction.action" class="form-horizontal" method="post">
							<span id="errorMsg" style="color:red;word-wrap: break-word;" ><s:actionerror/></span>
								
								<div class="form-group">
								<label class="col-md-3 control-label">Revenue Id:</label>
								<div class="col-md-4">
									<input type="text" name="revenueType.id" readonly="readonly" class="form-control" value="<s:property value='revenueType.id'/>" />
								</div>
							    </div>
								
								<div class="form-group">
								<label class="col-md-3 control-label">Revenue Type :<font color="red">*</font></label>
								<div class="col-md-4">
									<input type="text" class="form-control" name="revenueType.revenueTypeName" maxLength="20"
										value="<s:property value="revenueType.revenueTypeName"/>">
									 <s:if test="fieldErrors.get('revenueTypeName').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('revenueTypeName').get(0)" /></span>
									</s:if> 
								</div>
							    </div>
								
								
								
							  
								<div class="form-group">
								<label class="col-md-3 control-label">Remarks:</label>
								<div class="col-md-4">
								<textarea rows="3" class="form-control" id="notes" name="revenueType.notes" maxLength="100"><s:property value="revenueType.notes" /></textarea>
								</div>
							    </div>
							    
							    <div class="form-group">
										<label class="col-md-3 control-label">Status:</label>
										<div class="col-md-3">
											<select class="form-control" name="revenueType.status"  id="status">
												<option value="Active">Active</option>
												<option value="Inactive">Inactive</option> 
											</select>									    
										</div>
							    </div>
																						
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn blue">Save</button>
										<button type="button" class="btn default" onclick = "javascript:location.href='RevenueTypeAction!view';">Cancel</button>
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
<script>
$(document).ready(function() {
	//window.history.pushState("","", "ServiceTypeList.action");
	
	   var w=$('#errorMsg span').html();
	   //alert(w);
	    w=w.replace(/@/g,"<br>");

	   $('#errorMsg').html(''+w+'');
  
});
</script>