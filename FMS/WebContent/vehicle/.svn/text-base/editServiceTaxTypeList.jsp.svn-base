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
// AccessRightsDao  accessrightdao=new AccessRightsDao();
// AccessRights accessrights=new AccessRights();
// int usrsessionid=(Integer)request.getSession().getAttribute("userid");
// accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ServiceTaxTypeList.action");
// String access=accessrights.getACCESS();
// String create=accessrights.getCREATE();
// String edit=accessrights.getEDIT();
// String delete=accessrights.getDELETE();
// String read=accessrights.getREAD();
// String error=accessrights.getERROR();
// String viewdelete=accessrights.getVIEWDELETE();
// String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
%>

	<div class="page-content">
<%-- 	<%if (access.equalsIgnoreCase("Y")){%> --%>
<%-- 		<%if (edit.equalsIgnoreCase("Y")){%> --%>
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
				<h3 class="page-title">
					SERVICE TAX TYPE
				</h3>
			</div>
		</div>
		<div class="row">
	
			<div class="col-md-12">
				<!-- BEGIN EXAMPLE TABLE PORTLET-->
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i> Edit Service Tax Type
						</div>
					</div>
					<div class="portlet-body form">
						<b>
							<font color="green"> <s:actionmessage/></font>
							
							<b>
								<span id="errorMsg" style="color:red;word-wrap: break-word;" ><s:actionerror/></span>
							</b>
						</b>
						<form action="saveEditServiceTaxTypeAction" class="form-horizontal form-row-seperated" method="post">
							<div class="form-body">
<!-- 								<div class="form-group"> -->
<%-- 									<s:hidden name="updatedServiceTypeId" cssClass="form-control" value='%{serviceTypeDetails.service_type_id}' /> --%>
<%-- 									<s:hidden name="serviceTypeDetails.service_type_id" cssClass="form-control" value='%{serviceTypeDetails.service_type_id}' /> --%>
<%-- 									<s:hidden name="isUpdateServiceType" value="1"/> --%>
<!-- 									<label class="control-label col-md-3"> -->
<%-- 										Service Type <span class="required"> * </span> --%>
<!-- 									</label> -->
<!-- 									<div class="col-md-3"> -->
<!-- 										<input type="text" class="form-control"  name="serviceTypeDetails.service_type_name" -->
<%-- 											value='<s:property value="serviceTypeDetails.service_type_name"/>' maxlength="20"> --%>
<%-- 											<span class="help-block" >  --%>
<%-- 												<s:property	value="%{fieldErrors.get('updatedServiceTypeName').get(0)}" /> --%>
<%-- 											</span> --%>
<!-- 									</div> -->
<!-- 								</div> -->
								
								
								<div class="form-group">
								
								<s:hidden name="updatedServiceTypeId" cssClass="form-control" value='%{serviceTypeDetails.service_tax_id}' /> 
									<s:hidden name="serviceTypeDetails.service_tax_id" cssClass="form-control" value='%{serviceTypeDetails.service_tax_id}' />
<%-- 									<s:hidden name="isUpdateServiceType" value="1"/> --%>
<%-- 								<s:hidden name="isNewServiceType" value="1"/> --%>
									<label class="control-label col-md-3">
										Tax Type
										<span class="required"> * </span>
									</label>
									<div class="col-md-3">
										  <s:select list="taxtypList" id="tax_type_id" name="serviceTypeDetails.tax_type_name"
        cssClass="select2_category form-control"  headerKey="0" headerValue="Select" value="taxid"></s:select>
                                     <%-- <script>
											var servicetax ="<s:property value='serviceTypeDetails.tax_type_name' />";
											if(servicetax!=""){
 												document.getElementById(servicetax).selected= true;
 												
												
											}
										</script> --%>
        <s:if test="fieldErrors.get('ServiceTaxName').size() > 0">
											<span style="color: red;"><s:property
													value="fieldErrors.get('ServiceTaxName').get(0)" /></span>
											</s:if>
									</div>
								</div>
								
								<div class="form-group">
<%-- 								<s:hidden name="isNewServiceType" value="1"/> --%>
									<label class="control-label col-md-3">
										Service Type
										<span class="required"> * </span>
									</label>
									<div class="col-md-3">
										  <s:select list="fareCatagoryList" id="service_type_id" name="serviceTypeDetails.service_type_name"
        cssClass="select2_category form-control" headerKey="0" headerValue="Select" value="servid"></s:select>
        
        
<%--                                          <script type="text/javascript"> --%>
<%-- // 											var servicename = "<s:property value='serviceTypeDetails.service_type_name'/>"; --%>
<!-- // 											if(servicename!=""){ -->
<!-- //  												document.getElementById(servicename).selected= true; -->
 												
												
<!-- // 											} -->
<%-- 										</script> --%>
										<s:if test="fieldErrors.get('ServiceTypeName').size() > 0">
											<span style="color: red;"><s:property
													value="fieldErrors.get('ServiceTypeName').get(0)" /></span>
											</s:if>
									</div>
								</div>
								
								<div class="form-group">
<%-- 								<s:hidden name="isNewServiceType" value="1"/> --%>
									<label class="control-label col-md-3">
										Ticket Type
										<span class="required"> * </span>
									</label>
									<div class="col-md-3">
										  <s:select list="tickettypList" id="ticket_type_id" name="serviceTypeDetails.ticket_type_name"
        cssClass="select2_category form-control" headerKey="0" headerValue="Select" value="ticketid"></s:select>
        
        
<%--                                          <script type="text/javascript"> --%>
<%-- // 											var servicename1 = "<s:property value='serviceTypeDetails.ticket_type_name'/>"; --%>
<!-- // 											if(servicename1!=""){ -->
<!-- //  												document.getElementById(servicename1).selected= true; -->
 												
												
<!-- // 											} -->
<%-- 										</script> --%>
										<s:if test="fieldErrors.get('TicketTypeName').size() > 0">
											<span style="color: red;"><s:property
													value="fieldErrors.get('TicketTypeName').get(0)" /></span>
											</s:if>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">
										Base Value 
									</label>
									<div class="col-md-3">
									<input type="text" class="form-control"
											 value="<s:property value='serviceTypeDetails.base_value'/>" 
											name="serviceTypeDetails.base_value" maxlength="20" value='<s:property value="serviceTypeDetails.base_value"/>'>
										</div>
								</div>
								
								
									<div class="form-group">
									<label class="control-label col-md-3">
										Service Tax Percentage:
									</label>
									<div class="col-md-3">
									<input type="text" class="form-control"
											 value="<s:property value='serviceTypeDetails.service_tax_percentage'/>" 
											name="serviceTypeDetails.service_tax_percentage" maxlength="20">
										</div>
								</div>
								
								<div class="form-group">
									<label class="control-label col-md-4">Effective Start
										Date:<font color="red">*</font>
									</label>
									<div class="col-md-4">
									<!-- 	<div class="input-group date date-picker"
											data-date-format="yyyy-mm-dd"> -->
											<div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy">
											<input class="form-control" type="text"
												value='<s:property value="serviceTypeDetails.effective_start_date"/>'
												readonly="" size="16"
												name="serviceTypeDetails.effective_start_date"> <span
												class="input-group-btn">
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
										</div>
										<s:if
											test="fieldErrors.get('startDate').size() > 0">
											<span style="color: red;"><s:property
													value="fieldErrors.get('startDate').get(0)" /></span>
										</s:if>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-4">Effective End
										Date:
									</label>
									<div class="col-md-4">
										<div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy">
											<input class="form-control" type="text"
												value='<s:property value="serviceTypeDetails.effective_end_date"/>'
												readonly="" size="16"
												name="serviceTypeDetails.effective_end_date"> <span
												class="input-group-btn">
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
										</div>
										<s:if test="fieldErrors.get('endDate').size() > 0">
											<span style="color: red;"><s:property
													value="fieldErrors.get('endDate').get(0)" /></span>
										</s:if>
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
<%-- 						<%}else{ %> --%>
<%-- <%@ include file="/pages/admin/error.jsp" %> --%>
<%-- <%} %>									 --%>
										
<%-- <%}else{%> --%>
 


<%-- <%@ include file="/pages/admin/error.jsp" %> --%>


<%-- <%}%> --%>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
function callCancell(){
	
	window.location.href='ServiceTaxTypeList.action';
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
