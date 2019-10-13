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
<%/* 
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
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord"); */
%>
	<div class="page-content">
	<%/* if (access.equalsIgnoreCase("Y")){ */%>
		<%/* if (create.equalsIgnoreCase("Y")){ */%>
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
					SERVICE TAX
				</h3>
			</div>
		</div>
		<div class="row">
	
			<div class="col-md-12">
				<!-- BEGIN EXAMPLE TABLE PORTLET-->
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i> Create Service Tax
						</div>
					</div>
					<div class="portlet-body form">
						<b>
							<font color="green"> <s:actionmessage/></font>
							<font color="red"> <s:actionerror/></font>
						</b>
						<form action="SaveServiceTypeTax" class="form-horizontal form-row-seperated" method="post">
							<div class="form-body">
								<div class="form-group">
								<s:hidden name="isNewServiceTax" value="1"/>
									<label class="control-label col-md-3">
										Service Type
										<span class="required"> * </span>
									</label>
									<div class="col-md-3">
										<input type="text" class="form-control"
											 value="<s:property value='serviceTypeDetails.service_type_name'/>" 
											name="serviceTypeDetails.service_type_name" maxlength="20">
											<span class="help-block" > 
												<s:property	value="%{fieldErrors.get('newServiceType').get(0)}" />
											</span>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">
										Base Value 
									</label>
									<div class="col-md-3">
										<textarea class="form-control"  rows="3" name="serviceTypeDetails.baseValue" maxlength="100"><s:property value='serviceTypeDetails.note'/></textarea>
									</div>
										</div>
								<div class="form-group">
									<label class="control-label col-md-3">Status</label>
									<div class="col-md-3">
										<s:textfield name="serviceTypeDetails.status" cssClass="form-control" readonly="true" value="ACTIVE"/>
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
<%-- 						<%}else{ %> --%>
<%@ include file="/pages/admin/error.jsp" %>
<%/* } */ %>									
										
<%-- <%}else{%> --%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%-- <%}%> --%>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
function callCancell(){
	
	window.location.href='ServiceTypeTaxList.action';
}
</script>