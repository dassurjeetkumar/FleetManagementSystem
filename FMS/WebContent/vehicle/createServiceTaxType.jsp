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
<%-- 		<%if (create.equalsIgnoreCase("Y")){%> --%>
		<div class="row">
			<div class="col-md-12">
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
							<i class="fa fa-globe"></i> Create Service Tax Type
						</div>
					</div>
					<div class="portlet-body form">
						<b>
							<font color="green"> <s:actionmessage/></font>
							<font color="red"> <s:actionerror/></font>
						</b>
						<form action="SaveServiceTaxType" class="form-horizontal form-row-seperated" method="post">
							<div class="form-body">
							
							<div class="form-group">
<%-- 								<s:hidden name="isNewServiceType" value="1"/> --%>
									<label class="control-label col-md-3">
										Tax Type
										<span class="required"> * </span>
									</label>
									<div class="col-md-3">
										  <s:select list="taxtypList" id="tax_type_id" name="serviceTypeDetails.tax_type_name"
        cssClass="select2_category form-control" headerKey="0" headerValue="Select"></s:select>
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
        cssClass="select2_category form-control" headerKey="0" headerValue="Select"></s:select>
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
        cssClass="select2_category form-control" headerKey="0" headerValue="Select"></s:select>
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
											name="serviceTypeDetails.base_value" maxlength="20">
										</div>
								</div>
								
<!-- 								<div class="form-group"> -->
<!-- 									<label class="control-label col-md-3"> -->
<!-- 										Base Value  -->
<!-- 									</label> -->
<!-- 									<div class="col-md-3"> -->
<!-- 									<input type="text" class="form-control" -->
<%-- 											 value="<s:property value='serviceTypeDetails.base_value'/>"  --%>
<!-- 											name="serviceTypeDetails.abbreviation" maxlength="20"> -->
<!-- 										</div> -->
<!-- 								</div> -->
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
										Date<font color="red">*</font>
									</label>
									
									<div class="col-md-6">
									<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd/mm/yyyy"
															data-date-viewmode="years">
											<input class="form-control" type="text"  readonly="readonly" id="effStartdate"
												value='<s:property value="serviceTypeDetails.effective_start_date"/>'
												name="serviceTypeDetails.effective_start_date" > <span
												class="input-group-btn" >
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>	
											<script>
											var curr_date=new Date().toJSON().slice(0,10);
											curr_date=curr_date.split("-");
											curr_date=curr_date[2]+"/"+curr_date[1]+"/"+curr_date[0];
											$('#effStartdate').val(curr_date);
											</script>
										</div>
										<%-- <div class="input-group date date-picker" data-date-format="dd/mm/yyyy">
											<input class="form-control" type="text" readonly size="16"
												value='<s:property value="brandTypeDetails.effective_start_date"/>'
												name="brandTypeDetails.effective_start_date"> <span
												class="input-group-btn">
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
											
										</div> --%>
										<s:if
											test="fieldErrors.get('startDate').size() > 0">
											<span style="color: red;"><s:property
													value="fieldErrors.get('startDate').get(0)" /></span>
										</s:if>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-4">Effective End
										Date
									</label>
									<div class="col-md-4">
										<div class="input-group input-medium date date-picker" data-date-format="dd/mm/yyyy">
											<input class="form-control" type="text" readonly="" size="16"
												value='<s:property value="serviceTypeDetails.effective_end_date"/>'
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
								
<!-- 								<div class="form-group"> -->
<!-- 									<label class="control-label col-md-3">Remarks </label> -->
<!-- 									<div class="col-md-3"> -->
<%-- 										<textarea class="form-control"  rows="3" name="serviceTypeDetails.note" maxlength="100"><s:property value='serviceTypeDetails.note'/></textarea> --%>
<!-- 									</div> -->
<!-- 								</div> -->
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


function getServiceTypeName(){
    var len= document.getElementById('service_type_id').options.length;

     if(len<=1 ) {
         $.ajax({
             type: "post",
             url: '<%=request.getContextPath()%>/VehicleDetailsAjaxAction!getServiceType',
success : function(result) {
document.getElementById('service_type_id').innerHTML = result;
}
})
}
}



</script>