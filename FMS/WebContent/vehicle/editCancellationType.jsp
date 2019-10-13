<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
<%-- <link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/global/plugins/select2/select2.css" /> --%>
		<style type="text/css">
	.help-block
	{
	color:red;
	}
	</style>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
<input type="hidden" name="id" id="id" />
<div class="page-content-wrapper">
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "causeCancellation.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();

%>

	<div class="page-content">
	<%if (access.equalsIgnoreCase("Y")){%>
		<%if (edit.equalsIgnoreCase("Y")){%>
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
				<h3 class="page-title">
					Cause For Cancellation 
				</h3>
			</div>
		</div>
		<div class="row">
	
			<div class="col-md-12">
				<!-- BEGIN EXAMPLE TABLE PORTLET-->
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i> Edit Cause For Cancellation
						</div>
					</div>
					<div class="portlet-body form">
						<b>
							<font color="green"> <s:actionmessage/></font>
	
								<span id="errorMsg" style="color:red;word-wrap: break-word;" ><s:actionerror/></span>
							
													</b>
						<form action="saveEditCauseCanclAction" class="form-horizontal form-row-seperated" method="post">
							<div class="form-body">
								<div class="form-group">
								
								<s:hidden name="cancellationDetails.cancellation_id" value="%{cancellationDetails.cancellation_id}"/>
								<input type="hidden" name="editedCanclTypeId" class="form-control"
														value="<s:property value='cancellationDetails.cancellation_id'/>">
									<label class="control-label col-md-3">
										Cancellation Type
										<span class="required"> * </span>
									</label>
									<div class="col-md-3">
										<input type="text" class="form-control"
											placeholder="Enter text" name="cancellationDetails.cancellation_name"
											value='<s:property value="cancellationDetails.cancellation_name"/>' maxlength="20">
										
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">Remarks </label>
									<div class="col-md-3">
										<textarea class="form-control" placeholder="Remarks" rows="3" name='cancellationDetails.notes'  maxlength="100"><s:property value="cancellationDetails.notes"/></textarea>
										
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">Status</label>
									<div class="col-md-3">
										<select class="select2_category form-control" name="cancellationDetails.status">
											<option value="Y" id="active">ACTIVE</option>
											<option value="N" id="inacrive">INACTIVE</option>
										</select>
										<script type="text/javascript">
											var a = "<s:property value='cancellationDetails.status'/>";
											if (a == "Y") {
												document.getElementById("active").selected = true;
											} else {
												document.getElementById("inacrive").selected = true;
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
	
	window.location.href='causeCancellation.action';
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