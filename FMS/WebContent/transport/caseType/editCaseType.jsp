<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<link rel="stylesheet"	href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
<style type="text/css">
.help-block {
	color: red;
}
.help-block,ul,li {
	list-style: none;
}
</style>
<input type="hidden" name="id" id="id" />
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "CaseTypeList.action");
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
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
					 CASE TYPE
				</h3>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-12">
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i> Edit Case Type
						</div>
					</div>
					<div class="portlet-body form">
						<form action="saveEditCaseTypeAction" class="form-horizontal form-row-seperated" method="post">
							<b>
								<font color="green"> <s:actionmessage/></font>
								<font color="red"> <s:actionerror/></font>
							</b>
							<div class="form-body">
							
								<div class="form-group">
									<label class="control-label col-md-3">Case Type Id </label>
									<div class="col-md-3">
										<input type="text" class="form-control" readonly="readonly"	 name="caseTypeDetails.caseTypeId"
												value='<s:property value="caseTypeDetails.caseTypeId"/>'>
									</div>
								</div>
								
								<div class="form-group">
									<div class="form-group">
										<s:hidden name="isUpdateCaseType" value="1" />
										<s:hidden name="updatedCaseTypeId" cssClass="form-control" value='%{caseTypeDetails.caseTypeId}' />
										
										<label class="control-label col-md-3"> Case Type<span	class="required"> * </span></label>
										<div class="col-md-3">
											<input type="text" class="form-control"  maxlength="50"
													name="caseTypeDetails.caseTypeName"	value='<s:property value="caseTypeDetails.caseTypeName"/>'>
											<span class="help-block"> 
												<s:property	value="%{fieldErrors.get('updatedCaseTypeName').get(0)}" />
											</span>
										</div>
									</div>
									
									<div class="form-group">
										<label class="control-label col-md-3">Remarks</label>
										<div class="col-md-3">
											<textarea class="form-control"  rows="3" maxlength="100"
													name='caseTypeDetails.notes'><s:property value="caseTypeDetails.notes"/></textarea>
										</div>
									</div>
									
									<div class="form-group">
										<label class="control-label col-md-3">Status</label>
										<div class="col-md-3">
											<select class="select2_category form-control" name="caseTypeDetails.status">
												<option value="ACTIVE" id="active">ACTIVE</option>
												<option value="INACTIVE" id="inactive">INACTIVE</option>
											</select>
											<script type="text/javascript">
												var a = "<s:property value='caseTypeDetails.status'/>";
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
											<button type="button" class="btn default"	onclick="callCancell()">Cancel</button>
										</div>
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
	function callCancell() {

		window.location.href = 'CaseTypeList.action';
	}
</script>
