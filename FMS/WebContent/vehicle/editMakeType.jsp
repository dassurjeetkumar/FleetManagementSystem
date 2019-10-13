<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<%-- <link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/global/plugins/select2/select2.css" /> --%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
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
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "MakeTypeList.action");
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
				<h3 class="page-title">
					Make Type  
				</h3>
			</div>
		</div>
		<div class="row">
		
			<div class="col-md-12">
				<!-- BEGIN EXAMPLE TABLE PORTLET-->
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i> Edit Make Type
						</div>
					</div>
					<div class="portlet-body form">
						<b>
							<font color="green"> <s:actionmessage/></font>
							<b>
								<span id="errorMsg" style="color:red;word-wrap: break-word;" ><s:actionerror/></span>
							</b>
						</b>
						<form action="saveEditMakeTypeAction" class="form-horizontal form-row-seperated" method="post">
							<div class="form-body">
								<s:hidden name="makeTypeDetails.make_type_id" cssClass="form-control" readonly="true"
														value='%{makeTypeDetails.make_type_id}' />
										
								<div class="form-group">
								<s:hidden name="isUpdateMakeType" value="1"/> 
								<s:hidden name="updatedMakeTypeId" cssClass="form-control"
														value='%{makeTypeDetails.make_type_id}' />
									<label class="control-label col-md-3">
										Make
										<span class="required"> * </span>
									</label>
									<div class="col-md-3">
										<input type="text" class="form-control"
											 name="makeTypeDetails.make_type_name"
											value='<s:property value="makeTypeDetails.make_type_name"/>' maxlength="20">
											<span class="help-block" > <s:property
														value="%{fieldErrors.get('updatedMakeTypeName').get(0)}" /></span>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">Remarks </label>
									<div class="col-md-3">

										<textarea class="form-control"  rows="3" name='makeTypeDetails.note'maxlength="100"><s:property value="makeTypeDetails.note"/> </textarea>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">Status</label>
									<div class="col-md-3">

										<select class="select2_category form-control" name="makeTypeDetails.status">

											<option value="ACTIVE" id="active">ACTIVE</option>
											<option value="INACTIVE" id="inactive">INACTIVE</option>

										</select>
										<script type="text/javascript">
											var a = "<s:property value='makeTypeDetails.status'/>";
											if (a == "ACTIVE") {
												document.getElementById("active").selected = true;
											} else {
												document.getElementById("inactive").selected = true;
											}
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
	
	window.location.href='MakeTypeList.action';
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
