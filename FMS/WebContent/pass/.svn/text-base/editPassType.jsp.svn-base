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

	 SelectElement('<s:property value="passType.status"/>');

});

function SelectElement(valueToSelect)
{ 
  var element = document.getElementById('status');
  element.value = valueToSelect;
}
</script>

</head>
<body>
	<input type="text" id='a'>
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "PassTypeAction!view");
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
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
				<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						PASS TYPE <small></small>
					</h3>
					<font color="green"><s:actionmessage/></font>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
				</div>
				<%if (edit.equalsIgnoreCase("Y")){%>
					<div class="portlet box grey-cascade">

						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Edit Pass Type
							</div>
							
						
							
						</div>

						<div class="portlet-body form">
							
							<form action="EditPassTypeAction.action" class="form-horizontal" method="post">
								<input type="hidden" name="passType.pass_type_id" 
								value="<s:property value='passType.pass_type_id'/>" />
							<span id="errorMsg" style="color:red;word-wrap: break-word;" ><s:actionerror/></span>
								
									<div class="form-group">
										<label class="col-md-3 control-label" 
										>Pass Type:<font color="red">*</font></label>

										<div class="col-md-4">
									<input type="text" class="form-control" name="passType.pass_type_name" maxLength="50"
										value="<s:property value="passType.pass_type_name"/>">
									<s:if test="fieldErrors.get('pass_type_name').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('pass_type_name').get(0)" /></span>
									</s:if>
								</div>
									</div>		
								
								
								<div class="form-group">
										<label class="col-md-3 control-label">Status:</label>
										<div class="col-md-3">
											<select class="form-control" name="passType.status" id="status">
												<option value="Active">Active</option>
												<option value="Inactive">Inactive</option> 
											</select>									    
										</div>
							    </div>
															
								<div class="form-group">
								<label class="col-md-3 control-label">Remarks:</label>
								<div class="col-md-4">
									<%-- <input type="text" class="form-control" name="passType.note" maxLength="30"
										value="<s:property value="passType.note"/>"> --%>
									<textarea rows="3" class="form-control" id="notes" name="passType.note" maxLength="30"><s:property value="passType.note" /></textarea>
									<s:if test="fieldErrors.get('note').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('note').get(0)" /></span>
									</s:if>
								</div>
							    </div>
															
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn blue" >Save</button>
										<button type="button" class="btn default" onclick = "javascript:location.href='PassTypeAction!view';">Cancel</button>
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