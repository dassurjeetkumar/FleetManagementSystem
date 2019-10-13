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

	 SelectElement('<s:property value="tripType.status"/>');

});

function SelectElement(valueToSelect)
{ 
    var element = document.getElementById('status');
    element.value = valueToSelect;
}

$(document).ready(function() {

	window.history.pushState("", "", "TripTypeAction!add");
	
});

</script>
<title>Insert title here</title>
</head>
<body onload="getFareMaster();">
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "TripTypeAction!view");
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
						TRIP TYPE <small></small>
					</h3>
					<font color="green"><s:actionmessage/></font>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			
				</div>
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Create Trip Type
							</div>
							<div class="tools">
								
							</div>
						</div>
						
						<div class="portlet-body form">
							<!-- BEGIN FORM-->
							<form action="AddTripTypeAction" class="form-horizontal" method="post">
							<font color="red"><s:actionerror/></font>
							<br><br>	
																				
								<div class="form-group">
								<label class="col-md-3 control-label">Trip Type Name:<font color="red">*</font></label>
								<div class="col-md-4">
									<input type="text" class="form-control" name="tripType.tripTypeName" maxLength="60"
										value="<s:property value="tripType.tripTypeName"/>">
									<s:if test="fieldErrors.get('tripTypeName').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('tripTypeName').get(0)" /></span>
									</s:if>
								</div>
							    </div>
							    <div class="form-group">
									<label class="control-label col-md-3">
											Route Type : <span class="required"> * </span>
									</label>
									<div class="col-md-3">
										<select id="route_type_id" name="tripType.routetypedetails.route_type_id"  class="select2-container select2_category form-control"  >
											<s:iterator value="routeTypeList">
												<option id="<s:property   value="route_type_id" />" value='<s:property   value="route_type_id" />'>
													<s:property value="route_type_name" />
												</option>
											</s:iterator>
										</select>
										<script>
										var prevFormFourId = "<s:property  value='tripType.routetypedetails.route_type_id' />";
										if(prevFormFourId!=0){
											document.getElementById(prevFormFourId).selected = true;
										}
										</script>
										<s:if test="fieldErrors.get('routetype').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('routetype').get(0)" /></span>
									</s:if>
									</div>
								</div>
							    
							    
							    <div class="form-group">
										<label class="col-md-3 control-label">Status</label>
										<div class="col-md-3">
											<select id="status" class="form-control" name="tripType.status" >												
												<option value="Active">Active</option>
												 <option value="Inactive">Inactive</option> 
											</select>									    
										</div>
								</div>
								
								<div class="form-group">
								<label class="col-md-3 control-label">Remarks:</label>
								<div class="col-md-4">
									<%-- <input type="text" class="form-control" name="tripType.notes" maxLength="100"
										value="<s:property value="tripType.notes"/>"> --%>
									<textarea rows="3" class="form-control" id="notes" name="tripType.notes" maxLength="100"><s:property value="tripType.notes" /></textarea>								
									
									<s:if test="fieldErrors.get('notes').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('notes').get(0)" /></span>
									</s:if>
								</div>
							    </div>
							    			
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn blue" onSubmit="validate();">Save</button>
										<button type="button" class="btn default" onclick = "javascript:location.href='TripTypeAction!view';">Cancel</button>										
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