<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<link rel="stylesheet" 	href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
<style type="text/css">
.help-block
{
	color:red;
}
</style>
<input type="hidden" name="id" id="id" />
<div class="page-content-wrapper">
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ServiceTypeList.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
%>

	<div class="page-content">
	<%if (access.equalsIgnoreCase("Y")){%>
		<%if (edit.equalsIgnoreCase("Y")){%>
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
				<h3 class="page-title">
					SERVICE TYPE
				</h3>
			</div>
		</div>
		<div class="row">
	
			<div class="col-md-12">
				<!-- BEGIN EXAMPLE TABLE PORTLET-->
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i> Edit Service Type
						</div>
					</div>
					<div class="portlet-body form">
						<b>
							<font color="green"> <s:actionmessage/></font>
							
							<b>
								<span id="errorMsg" style="color:red;word-wrap: break-word;" ><s:actionerror/></span>
							</b>
						</b>
						<form action="saveEditServiceTypeAction" class="form-horizontal form-row-seperated" method="post">
							<div class="form-body">
								<div class="form-group">
									<s:hidden name="updatedServiceTypeId" cssClass="form-control" value='%{serviceTypeDetails.service_type_id}' />
									<s:hidden name="serviceTypeDetails.service_type_id" cssClass="form-control" value='%{serviceTypeDetails.service_type_id}' />
									<s:hidden name="isUpdateServiceType" value="1"/>
									<label class="control-label col-md-3">
										Service Type <span class="required"> * </span>
									</label>
									<div class="col-md-3">
										<input type="text" class="form-control"  name="serviceTypeDetails.service_type_name"
											value='<s:property value="serviceTypeDetails.service_type_name"/>' maxlength="20">
											<span class="help-block" > 
												<s:property	value="%{fieldErrors.get('updatedServiceTypeName').get(0)}" />
											</span>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">
										Abbreviation 
									</label>
									<div class="col-md-3">
										<input type="text" class="form-control"
											 value="<s:property value='serviceTypeDetails.abbreviation'/>" 
											name="serviceTypeDetails.abbreviation" maxlength="20">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">
										Remarks 
									</label>
									<div class="col-md-3">
										<textarea class="form-control"  rows="3" name='serviceTypeDetails.note'maxlength="100"><s:property value="serviceTypeDetails.note"/></textarea>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">
										Status
									</label>
									<div class="col-md-3">
										<select class="select2_category form-control" name="serviceTypeDetails.status">
											<!-- <option value="NEW" id="new">NEW</option>
											 --><option value="ACTIVE" id="active">ACTIVE</option>
											<option value="INACTIVE" id="inactive">INACTIVE</option>
										</select>
										<script type="text/javascript">
											var a = "<s:property value='serviceTypeDetails.status'/>";
											
											if (a == "ACTIVE") {
												document.getElementById("active").selected = true;
											} else if(a=="INACTIVE"){
												document.getElementById("inactive").selected = true;
											}/* else{
												document.getElementById("new").selected = true;
											 }*/
										</script>
									</div>
								</div>
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn blue">Save</button>
										<button type="button" class="btn default" onclick="callCancell()">Cancel</button>
									</div>
								</div>
							</div>
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
<script>
function callCancell(){
	
	window.location.href='ServiceTypeList.action';
}
</script>

<script>
$(document).ready(function() {
	//window.history.pushState("","", "ServiceTypeList.action");
	
	   var w=$('#errorMsg span').html();
	   //alert(w);
	    w=w.replace(/@/g,"<br>");

	   $('#errorMsg').html(''+w+'');
  
});
</script>
