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
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "reasonExtraKM.action");
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
					Cause For Extra KM 
				</h3>
			</div>
		</div>
		<div class="row">
	
			<div class="col-md-12">
				<!-- BEGIN EXAMPLE TABLE PORTLET-->
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i> Edit Cause For Extra KM
						</div>
					</div>
					<div class="portlet-body form">
						<b>
							<font color="green"> <s:actionmessage/></font>
	
								<span id="errorMsg" style="color:red;word-wrap: break-word;" ><s:actionerror/></span>
							
													</b>
						<form action="saveEditExtraKmAction" class="form-horizontal form-row-seperated" method="post">
							<div class="form-body">
								<div class="form-group">
								
								<s:hidden name="extrkm.extrakm_id" value="%{extrkm.extrakm_id}"/>
								<input type="hidden" name="editedExtakmTypeId" class="form-control"
														value="<s:property value='extrkm.extrakm_id'/>">
									<label class="control-label col-md-3">
										Extra KM Type
										<span class="required"> * </span>
									</label>
									<div class="col-md-3">
										<input type="text" class="form-control"
											placeholder="Enter text" name="extrkm.extrakm_name"
											value='<s:property value="extrkm.extrakm_name"/>' maxlength="20">
										
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">Remarks </label>
									<div class="col-md-3">
										<textarea class="form-control" placeholder="Remarks" rows="3" name='extrkm.notes'  maxlength="100"><s:property value="extrkm.notes"/></textarea>
										
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">Status</label>
									<div class="col-md-3">
										<select class="select2_category form-control" name="extrkm.status">
											<option value="Y" id="active">ACTIVE</option>
											<option value="N" id="inacrive">INACTIVE</option>
										</select>
										<script type="text/javascript">
											var a = "<s:property value='extrkm.status'/>";
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
	
	window.location.href='reasonExtraKM.action';
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