<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

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
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "DockingTypeList.action");
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
		<%if (create.equalsIgnoreCase("Y")){%>
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
					Docking Type 
				</h3>
			</div>
		</div>
		<div class="row">
	
			<div class="col-md-12">
				<!-- BEGIN EXAMPLE TABLE PORTLET-->
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i> Create Docking Type
						</div>
					</div>
					<div class="portlet-body form">
						<b>
							<font color="green"> <s:actionmessage/></font>
							<font color="red"> <s:actionerror/></font>
						</b>
						<form action="SaveDockingType" class="form-horizontal form-row-seperated" method="post">
							<div class="form-body">
								<div class="form-group">
									<label class="control-label col-md-3">Docking Type<span class="required"> * </span></label>
									<div class="col-md-3">
									<s:hidden name="isNewDockingType" value="1"/> 
										<input  value="<s:property	value='dockingTypeDetails.docking_type'/>"	 name="dockingTypeDetails.docking_type" type="text" class="form-control" maxlength="100">
										<span class="help-block"> 
											<s:property	value="%{fieldErrors.get('newDockingType').get(0)}" />
										</span>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">Docking Type KMs <span	class="required"> * </span></label>
									<div class="col-md-3">
										<input type="text" class="form-control"   maxlength="20" 	name="dockingTypeDetails.dockingTypeKms" value="<s:property value="dockingTypeDetails.dockingTypeKms"/>"/>
										<span class="help-block"> 
											<s:property	value="%{fieldErrors.get('dockingTypeDetails.dockingTypeKms').get(0)}" />
										</span>
									</div>
								</div>
								<div class="form-group">
										<label class="control-label col-md-3">KM limit for Alert <span	class="required"> * </span></label>
										<div class="col-md-3">
											<input type="text" class="form-control" maxlength="20"
													name='dockingTypeDetails.dockingKmAlert' value='<s:property value="dockingTypeDetails.dockingKmAlert"/>'>
											<span class="help-block"> 
												<s:property	value="%{fieldErrors.get('dockingTypeDetails.dockingKmAlert').get(0)}" />
											</span>
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
	
	window.location.href='DockingTypeList.action';
}
</script>
