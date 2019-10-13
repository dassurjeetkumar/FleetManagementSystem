<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//www.google.com/jsapi"></script>

<script>


function getOrgType()
{
       $.ajax({
           type: "post",
           async:false,
           url: '<%=request.getContextPath()%>/findAllOrgTypeAction1',
           success: function(result) {
        	   
               document.getElementById('org_type_id').innerHTML=result;
           }
       });
       var prevType="<s:property value='cashRemittanceVoucher.orgTypeCash.org_type_id'/>";
		
		 if(prevType!=""){
			 document.getElementById("org_type_id").value=prevType;
			 var orgtypeid = document.getElementById("org_type_id").value;
			 var orgtype = org_type_id.options[org_type_id.selectedIndex].text;
			 document.getElementById('select2-chosen-2').innerHTML=orgtype;
		 }
	
	 if(document.getElementById("orgType"+prevType).selected==true)
	{
		 $.ajax({
	           type: "post",
	           url: '<%=request.getContextPath()%>/findUnitNameAction1?orgid='+prevType,
	           async:false,
	           success: function(result) {
	               document.getElementById('org_chart_id').innerHTML=result;
	           }
	       });
	}
	  var prevType1="<s:property value='cashRemittanceVoucher.orgChartCash.org_chart_id'/>";
	  
		 if(prevType1!=""){
			
			 document.getElementById("org_chart_id").value=prevType1;
			 var orgchartid = document.getElementById("org_chart_id").value;
			 var orgname = org_chart_id.options[org_chart_id.selectedIndex].text;
			 document.getElementById('select2-chosen-3').innerHTML=orgname;
			 
		 }   
	
}
function getUnitNames()
{
	var e = document.getElementById("org_type_id");
	var strUser = e.options[e.selectedIndex].value;
	var len= document.getElementById('org_chart_id').options.length;
	
	       $.ajax({
	           type: "post",
	           url: '<%=request.getContextPath()%>/findUnitNameAction1?orgid='+strUser,
	           success: function(result) {
	               document.getElementById('org_chart_id').innerHTML=result;
	               document.getElementById('select2-chosen-3').innerHTML="Select";
	           }
	       });
	  
}
</script>
<script>
	function validate() {

		
		document.forms[0].action = 'createCashRemittanceVoucherAction.action';
		document.forms[0].submit();
	} 
	function goView() {
		document.forms[0].action = 'cashRemittanceVoucherView.action';
		document.forms[0].submit();
	}
	/* $(document).ready(
			function() {
				window.history.pushState("", "",
						"createCashRemittanceVoucherAction.action");
			}); */
</script>

<title>Insert title here</title>
</head>
<body onload="getOrgType()">
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "cashRemittanceVoucherView.action");
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
<%if (create.equalsIgnoreCase("Y")){%>
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						CASH REMITTANCE VOUCHER <small></small>
					</h3>

					<!-- END PAGE TITLE & BREADCRUMB-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Create Cash Remittance Voucher
							</div>
						</div>

						<div class="portlet-body form">
							<s:if test="hasActionErrors()">
								<div class="alert alert-danger">
									<button class="close" data-close="alert"></button>
									<span> <s:actionerror />
									</span>
								</div>


							</s:if>

							<!-- BEGIN FORM-->
							<form action="createCashRemittanceVoucherAction.action"
								method="post" class="form-horizontal">
								<s:if test="%{insertstaus=='duplicate'}">
									<font color="red">Could not insert Duplicate
										Voucher Number</font>
								</s:if>
								<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Voucher Number:<font
											color="red">*</font></label>

										<div class="col-md-4">
											<input type="text" class="form-control" id="voucher_number"
												maxlength="20" name="cashRemittanceVoucher.voucher_number"
												value='<s:property value="cashRemittanceVoucher.voucher_number"/>'>
											<s:if test="fieldErrors.get('Vouchernumber').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('Vouchernumber').get(0)" /></span>
											</s:if>
										</div>
									</div>
								</div>
								<div class="form-body">
								<div class="form-group">
									<label class="control-label col-md-3">Date:<font
										color="red">*</font></label>
									<div class="col-md-4">
										<div class="input-group input-medium date date-picker"
											data-date-format="dd-mm-yyyy" data-date-viewmode="years">
											<input class="form-control" type="text" size="16"
												name="cashRemittanceVoucher.voucher_date" id="voucher_date"
												value='<s:property value="cashRemittanceVoucher.voucher_date"/>' readonly>
											<span class="input-group-btn">
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>

										</div>
										<s:if test="fieldErrors.get('voucher_date').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('voucher_date').get(0)" /></span>
											</s:if> 
									</div>
								</div>
								</div>
								<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Remitted By:<font
											color="red">*</font></label>

										<div class="col-md-4">
										<s:select list="employeeList" id="remmittedbyid"
													name="cashRemittanceVoucher.employeeCash.employee_id"
													cssClass="select2_category form-control" headerKey="0"
													headerValue="Select"></s:select>
											<%-- <input type="text" class="form-control" id="remmitted_by"
												maxlength="20" name="cashRemittanceVoucher.employeeCash.employee_id"
												value='<s:property value="cashRemittanceVoucher.employeeCash.employee_id"/>'>
									 --%>	 <s:if test="fieldErrors.get('remmittedby').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('remmittedby').get(0)" /></span>
											</s:if>
										</div>
									</div>
								</div>
								<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Bank Name:<font
											color="red">*</font></label>

										<div class="col-md-4">
											<input type="text" class="form-control" id="bank_name"
												maxlength="20" name="cashRemittanceVoucher.bank_name"
												value='<s:property value="cashRemittanceVoucher.bank_name"/>'>
											<s:if test="fieldErrors.get('BankName').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('BankName').get(0)" /></span>
											</s:if>
										</div>
									</div>
								</div>
								<div class="form-body">
										<div class="form-group">
											<label class="col-md-3 control-label">Organization Type:<font color="red">*</font>
											</label>
											<div class="col-md-4">
										<select class="select2_category form-control" name="cashRemittanceVoucher.orgTypeCash.org_type_id" id="org_type_id"  onchange="getUnitNames()">
												         <option value="0" >Select</option>
										                 </select>
												
												 <s:if test="fieldErrors.get('orgTypeId').size() > 0">
													<span style="color: red;"><s:property
															value="fieldErrors.get('orgTypeId').get(0)" /></span>
												</s:if>
											</div>
										</div>
									</div>
								
									<div class="form-body">
										<div class="form-group">
											<label class="col-md-3 control-label">Organization Name:<font color="red">*</font>
											</label>
											<div class="col-md-4">	
											<select class="select2_category form-control" name="cashRemittanceVoucher.orgChartCash.org_chart_id" id="org_chart_id">
												             <option value="0">Select</option>
										                 </select>
														 														 
										
													 <s:if test="fieldErrors.get('OrganizationName').size() > 0">
													<span style="color: red;"><s:property value="fieldErrors.get('OrganizationName').get(0)" /></span>
												</s:if>  
												
											</div>
										</div>
									</div>
									<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Amount:<font color="red">*</font></label>
										<div class="col-md-4">
											<input type="text" class="form-control" id="amount"
												maxlength="8" name="cashRemittanceVoucher.amount"
												value='<s:property value="cashRemittanceVoucher.amount"/>'>
											<s:if test="fieldErrors.get('Amount').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('Amount').get(0)" /></span>
											</s:if>
										</div>
									</div>
								</div>
								<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Remarks:</label>

										<div class="col-md-4">
										<textarea class="form-control"   name="cashRemittanceVoucher.notes"   id="notes"  rows="3"  maxlength="100"  ><s:property value="cashRemittanceVoucher.notes" /></textarea>
										<%-- 
											<textarea rows="3" class="form-control" id="notes" name="cashRemittanceVoucher.notes" maxlength="100" ><s:property value="cashRemittanceVoucher.notes"/></textarea>
 										 --%>
										</div>
									</div>
								</div>
							
								
								<div class="form-body">
								<div class="form-group">
									<label class="col-md-3 control-label">Status:<font color="red">*</font></label>
									<div class="col-md-4">
										<select class="form-control" id="status"
											name="cashRemittanceVoucher.status">
											<option value="ACTIVE">ACTIVE</option>
										</select>

									</div>
								</div>

</div>

								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn blue" onSubmit="validate()">Save</button>
										<button type="button" class="btn default" onclick="goView()">Cancel</button>
									</div>
								</div>


							</form>
							<!-- END FORM-->
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


</body>
</html>