<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<!DOCTYPE html>
<html>
<head>
<script>
function isInt(n) {
	   return typeof n === 'number' && n % 1 == 0;
	}

function isInteger(n) {
	if(parseFloat(n) != parseInt(n, 10)){
	   return true;
	}else{
		return false;
	}
	} 

function getSubtypeDetails(){
		 var val=document.getElementById('pass_type_id').value;
		// alert(val);
		
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/passsubtypeAction?val=' + val,
				success : function(result) {
					document.getElementById('pass_sub_type_id').innerHTML = result;
					
				}
			});
		

	}

function checkInt(){
	
	var val=document.getElementById('amount').value;
	var val2=document.getElementById('versionNo').value;

	if(isNaN(val) || isInteger(val) || val<0){
		if(val<0){
			document.getElementById('integerVal').innerHTML='Invalid Amount value';	
		}else{
		document.getElementById('integerVal').innerHTML='Amount should be in Integer';
		}
	   return false;
	}
	else if(isNaN(val2) || isInteger(val2) || val2<0){
		document.getElementById('integerVal2').innerHTML='Version Number should be an Integer value';
		   return false;
	}
	else{
		return true;
	}

	
}

function getCalcu(){
	var pass_amount = parseFloat($('#pass_amount').val());
	var service_tax_amount =  parseFloat($('#service_tax_amount').val());
	var processing_amount =  parseFloat($('#processing_amount').val());
	
 	var result = pass_amount + processing_amount + service_tax_amount;
     $('#total_amount').val(result.toFixed(2));
    
  
}

$(document).ready(function() {

	window.history.pushState("", "", "PassRateAction!add");
	
}); 
</script>
<title>Insert title here</title>
</head>
<body>
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "PassRateAction!view");
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
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
				<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						PASS RATE MASTER <small></small>
					</h3>
					<font color="green"><s:actionmessage/></font>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
				</div>
					<%if (create.equalsIgnoreCase("Y")){%>
					<div class="portlet box grey-cascade">
					
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Create Pass Rate Master
							</div>
							<div class="tools">
								
							</div>
						</div>
						<div class="portlet-body form">
							<%-- &nbsp;&nbsp;&nbsp;&nbsp;<font color="red"><span id="integerVal"></span></font> --%>
							
							<form action="AddPassRateAction.action" onsubmit="return checkInt()" class="form-horizontal" method="post">
								<font color="red"><s:actionerror/></font>  <br>
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label" 
										>Passenger Type:<font color="red">*</font></label>

										<div class="col-md-4">
										
		                           <s:select list="passPassengerTypeMap" id="passenger_type" name="passRate.passenger_type" 
		                             cssClass="select2_category form-control" headerKey="0" headerValue="Select"></s:select>

											<s:if test="fieldErrors.get('passenger_type').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('passenger_type').get(0)" /></span>
											</s:if>	 

										</div>
									</div>
								</div>
								
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label" 
										>Pass Issuer Type:<font color="red">*</font></label>

										<div class="col-md-4">
										
		                           <s:select list="passissuerTypeMap" id="pass_issuer_type" name="passRate.pass_issuer_type" 
		                             cssClass="select2_category form-control" headerKey="0" headerValue="Select"></s:select>

											<s:if test="fieldErrors.get('pass_issuer_type').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('pass_issuer_type').get(0)" /></span>
											</s:if>	 

										</div>
									</div>
								</div>
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label" 
										>Pass Type:<font color="red">*</font></label>

										<div class="col-md-4">
										
		                                 <s:select list="passTypeMap" id="pass_type_id" name="passRate.pass_type_id" 
												cssClass="select2_category form-control" headerKey="0" headerValue="Select" onchange="getSubtypeDetails()"></s:select>

											<s:if test="fieldErrors.get('pass_type_id').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('pass_type_id').get(0)" /></span>
											</s:if>	 

										</div>
									</div>
								</div>
								<div class="form-group">
								<label class="col-md-3 control-label">Pass Sub Type:<font color="red">*</font></label>
<!-- 								<div class="col-md-4"> -->
<!-- 									<input id="pass_sub_type_id" type="text" class="form-control" name="passRate.pass_sub_type_id" maxLength="8" -->
<%-- 										value="<s:property value="passRate.pass_sub_type_id"/>"> --%>
<%-- 									 <s:if test="fieldErrors.get('pass_sub_type_id').size() > 0"> --%>
<%--      								<span style="color:red;"><s:property value="fieldErrors.get('pass_sub_type_id').get(0)" /></span> --%>
<%-- 									</s:if>  --%>
<%-- 									<font color="red"><span id="integerVal"></span></font> --%>
<!-- 								</div> -->
								<div class="col-md-4">
										
		                                <select id="pass_sub_type_id" class=" form-control" >
											<option value=0 id='0'>Select</option>
										</select>

											<s:if test="fieldErrors.get('pass_sub_type_id').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('pass_sub_type_id').get(0)" /></span>
											</s:if>	 

										</div>
								
							    </div>
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label" 
										>Pass Cast Category:<font color="red">*</font></label>

										<div class="col-md-4">
										
		                                 <s:select list="castcategory" id="pass_cast_id" name="passRate.pass_cast_id" 
												cssClass="select2_category form-control" headerKey="0" headerValue="Select"></s:select>

											<s:if test="fieldErrors.get('pass_cast_id').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('pass_cast_id').get(0)" /></span>
											</s:if>	 

										</div>
									</div>
								</div>
								
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label" 
										>Pass Purchase Type:<font color="red">*</font></label>

										<div class="col-md-4">
										
		                           <s:select list="passpurchaseTypeMap" id="pass_purchase_type" name="passRate.pass_purchase_type" 
		                             cssClass="select2_category form-control" headerKey="0" headerValue="Select"></s:select>

											<s:if test="fieldErrors.get('pass_purchase_type').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('pass_purchase_type').get(0)" /></span>
											</s:if>	 

										</div>
									</div>
								</div>
								
									<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label" 
										>Pass Distance Type:<font color="red"></font></label>

										<div class="col-md-4">
										
		                           <s:select list="passdistanceTypeMap" id="pass_distance_type" name="passRate.pass_distance_type" 
		                             cssClass="select2_category form-control" headerKey="0" headerValue="Select"></s:select>

											<s:if test="fieldErrors.get('pass_distance_type').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('pass_distance_type').get(0)" /></span>
											</s:if>	 

										</div>
									</div>
								</div>
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label" 
										>Pass Duration Type:<font color="red"></font></label>

										<div class="col-md-4">
										
		                           <s:select list="passdurationTypeMap" id="pass_duration_type" name="passRate.pass_duration_type" 
		                             cssClass="select2_category form-control" headerKey="0" headerValue="Select"></s:select>

											<s:if test="fieldErrors.get('pass_duration_type').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('pass_duration_type').get(0)" /></span>
											</s:if>	 

										</div>
									</div>
								</div>
								
								<div class="form-group">
								<label class="col-md-3 control-label">Validity Month<font color="red">*</font></label>
								<div class="col-md-4">
									<input id="validity_months" type="text" class="form-control" name="passRate.validity_months" maxLength="8"
										value="<s:property value="passRate.validity_months"/>">
									 <s:if test="fieldErrors.get('validity_months').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('validity_months').get(0)" /></span>
									</s:if> 
									<font color="red"><span id="integerVal"></span></font>
								</div>
							    </div>
								<div class="form-group">
								<label class="col-md-3 control-label">Pass Amount:<font color="red">*</font></label>
								<div class="col-md-4">
									<input id="pass_amount" type="text" class="form-control" name="passRate.pass_amount" maxLength="8"
										value="<s:property value="passRate.pass_amount"/>">
									 <s:if test="fieldErrors.get('pass_amount').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('pass_amount').get(0)" /></span>
									</s:if> 
									<font color="red"><span id="integerVal"></span></font>
								</div>
							    </div>
							    	<div class="form-group">
								<label class="col-md-3 control-label">Service Tax Amount:<font color="red">*</font></label>
								<div class="col-md-4">
									<input id="service_tax_amount" type="text" class="form-control" name="passRate.service_tax_amount" maxLength="8"
										value="<s:property value="passRate.service_tax_amount"/>">
									 <s:if test="fieldErrors.get('service_tax_amount').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('service_tax_amount').get(0)" /></span>
									</s:if> 
									<font color="red"><span id="integerVal"></span></font>
								</div>
							    </div>
							  
							  <div class="form-group">
								<label class="col-md-3 control-label">Processing Amount:<font color="red">*</font></label>
								<div class="col-md-4">
									<input id="processing_amount" type="text" class="form-control" name="passRate.processing_amount" maxLength="8"
										value="<s:property value="passRate.processing_amount"/>">
									 <s:if test="fieldErrors.get('processing_amount').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('processing_amount').get(0)" /></span>
									</s:if> 
									<font color="red"><span id="integerVal"></span></font>
								</div>
							    </div>
							      <div class="form-group">
								<label class="col-md-3 control-label">Total Amount:<font color="red">*</font></label>
								<div class="col-md-4">
									<input id="total_amount" type="text" class="form-control" name="passRate.total_amount" maxLength="8"
										value="<s:property value="passRate.total_amount"/>" onblur="getCalcu()">
									 <s:if test="fieldErrors.get('total_amount').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('total_amount').get(0)" /></span>
									</s:if> 
									<font color="red"><span id="integerVal"></span></font>
								</div>
							    </div>
							  
<!-- 							  <div class="form-group"> -->
<!-- 									<label class="control-label col-md-3">Validity Start Date:<font color="red">*</font></label> -->
<!-- 									<div class="col-md-4"> -->
<!-- 										<div class="input-group input-medium date date-picker" -->
<!-- 															style="width: auto" data-date-format="dd/mm/yyyy" -->
<!-- 															data-date-viewmode="years" data-date-start-date="+0d"> -->
<!-- 											<input type="text" size="16" readonly class="form-control" -->
<!-- 												id="effective_start_date" -->
<!-- 												name="passRate.effective_start_date" -->
<%-- 												value="<s:property value='passRate.effective_start_date' />" >  --%>
<%-- 												<span class="input-group-btn" > --%>
<!-- 												<button class="btn default date-set" type="button"> -->
<!-- 													<i class="fa fa-calendar"></i> -->
<!-- 												</button> -->
<%-- 											</span>	 --%>
<%-- 											<script> --%>
<!-- // 												var curr_date=new Date().toJSON().slice(0,10); -->
<!-- // 		                                        curr_date=curr_date.split("-"); -->
<!-- // 		                                        curr_date=curr_date[2]+"/"+curr_date[1]+"/"+curr_date[0]; -->
<!-- // 		                                        $('#effective_start_date').val(curr_date); -->
<%-- 											</script>						 --%>
<!-- 										</div> -->
<%-- 										<s:if test="fieldErrors.get('effective_start_date').size() > 0"> --%>
<%-- 		     								<span style="color:red;"><s:property value="fieldErrors.get('effective_start_date').get(0)" /></span> --%>
<%-- 											</s:if> --%>
<!-- 										/input-group -->
<!-- 									</div> -->
<!-- 								</div> -->
							  
							  <div class="form-group">
							<label class="col-md-3 control-label">Pass Start Time</label>
									<div class="col-md-4">
					
						<div class="col-md-3">
									<div class="input-group">
												<input type="text" class="form-control input-small timepicker timepicker-24" name="passRate.pass_start_time" id="pass_start_time" 
												value="<s:property value="passRate.pass_start_time"/>">
												 <s:if test="fieldErrors.get('pass_start_time').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('pass_start_time').get(0)" /></span>
									</s:if> 
											</div>

								</div>
					</div>
					</div>
					
							  <div class="form-group">
							<label class="col-md-3 control-label">Pass End Time</label>
									<div class="col-md-4">
					
						<div class="col-md-3">
									<div class="input-group">
												<input type="text" class="form-control input-small timepicker timepicker-24" name="passRate.pass_end_time" id="pass_end_time" 
												value="<s:property value="passRate.pass_end_time"/>">
												 <s:if test="fieldErrors.get('pass_end_time').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('pass_end_time').get(0)" /></span>
									</s:if> 
											</div>

								</div>
					</div>
					</div>
					<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label" 
										>Service Type:<font color="red">*</font></label>

										<div class="col-md-4">
										
		                           <s:select list="ServiceTypeMap" id="service_type_id" name="passRate.service_type_id" 
		                             cssClass="select2_category form-control" headerKey="0" headerValue="Select"></s:select>

											<s:if test="fieldErrors.get('service_type_id').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('service_type_id').get(0)" /></span>
											</s:if>	 

										</div>
									</div>
								</div>
								
									<div class="form-group">
													<label class="col-md-3 control-label">Upgrade Availability :<span class="required"> * </span></label>
													<div class="col-md-4">
													
												<select class="form-control input-small select2me" data-placeholder="Select..." name="passRate.upgrade_availability">
												<option value="Y">Yes</option>
												<option value="N">No</option>
											</select>
												<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('upgrade_availability').get(0)}" />
										</span>
												<script>
											var orgTypeId ="<s:property value='passRate.upgrade_availability'/>";
											if(orgTypeId!=""){
 												document.getElementById(orgTypeId).selected= true; 
												
											}
											
										</script>
												</div>
												</div>
												<div class="form-group">
																<label class="col-md-3 control-label">Pass to be validated :<span class="required"> * </span></label>
													<div class="col-md-4">
													
												<select class="form-control input-small select2me" data-placeholder="Select..." name="passRate.pass_to_validate">
												<option value="Y">Yes</option>
												<option value="N">No</option>
											</select>
												<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('pass_to_validate').get(0)}" />
										</span>
												<script>
											var orgTypeId ="<s:property value='passRate.pass_to_validate'/>";
											if(orgTypeId!=""){
 												document.getElementById(orgTypeId).selected= true; 
												
											}
											
										</script>
												</div>
												</div>
												<div class="form-group">
																<label class="col-md-3 control-label">Route to be validated :<span class="required"> * </span></label>
													<div class="col-md-4">
													
												<select class="form-control input-small select2me" data-placeholder="Select..." name="passRate.route_to_validate">
												<option value="Y">Yes</option>
												<option value="N">No</option>
											</select>
												<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('route_to_validate').get(0)}" />
										</span>
												<script>
											var orgTypeId ="<s:property value='passRate.route_to_validate'/>";
											if(orgTypeId!=""){
 												document.getElementById(orgTypeId).selected= true; 
												
											}
											
										</script>
												</div>
												</div>
												<div class="form-group">
																<label class="col-md-3 control-label">Exception Day :<span class="required"> * </span></label>
													<div class="col-md-4">
													
												<select class="form-control input-small select2me" data-placeholder="Select..." name="passRate.exception_day">
												<option value="Y">Yes</option>
												<option value="N">No</option>
											</select>
												<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('exception_day').get(0)}" />
										</span>
												<script>
											var orgTypeId ="<s:property value='passRate.exception_day'/>";
											if(orgTypeId!=""){
 												document.getElementById(orgTypeId).selected= true; 
												
											}
											
										</script>
												</div>
												</div>
<!-- 												<div class="form-group"> -->
<%-- 																<label class="col-md-3 control-label">Exception Day of the week :<span class="required"> * </span></label> --%>
<!-- 													<div class="col-md-4"> -->
													
<%-- 												<select class="form-control input-small select2me" data-placeholder="Select..." name="passRate.exception_day"> --%>
<!-- 												<option value="Y">Yes</option> -->
<!-- 												<option value="N">No</option> -->
<%-- 											</select> --%>
<%-- 												<span class="help-block" style="color: red">  --%>
<%-- 											<s:property	value="%{fieldErrors.get('exception_day').get(0)}" /> --%>
<%-- 										</span> --%>
<%-- 												<script> --%>
<%-- // 											var orgTypeId ="<s:property value='passRate.exception_day'/>"; --%>
<!-- // 											if(orgTypeId!=""){ -->
<!-- //  												document.getElementById(orgTypeId).selected= true;  -->
												
<!-- // 											} -->
											
<%-- 										</script> --%>
<!-- 												</div> -->
<!-- 												</div> -->
							  <div class="form-group">
									<label class="control-label col-md-3">Effective Start Date:<font color="red">*</font></label>
									<div class="col-md-4">
										<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" data-date-start-date="+0d">
											<input type="text" size="16" readonly class="form-control"
												id="effective_start_date"
												name="passRate.effective_start_date"
												value="<s:property value='passRate.effective_start_date' />" > 
												<span class="input-group-btn" >
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>	
											<script>
												var curr_date=new Date().toJSON().slice(0,10);
		                                        curr_date=curr_date.split("-");
		                                        curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
		                                        $('#effective_start_date').val(curr_date);
											</script>						
										</div>
										<s:if test="fieldErrors.get('effective_start_date').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('effective_start_date').get(0)" /></span>
											</s:if>
										<!-- /input-group -->
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">Effective End Date:</label>
									<div class="col-md-4">
									<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" data-date-start-date="+0d">
											<input type="text" size="16" readonly class="form-control"
												id="effect_end_date" name="passRate.effective_end_date"
												value="<s:property value='passRate.effective_end_date' />" >
											<span class="input-group-btn">
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
										</div>
										<!-- /input-group -->
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-3 control-label">Version Number:<font color="red">*</font></label>
									<div class="col-md-4">
									<input id="version_number" type="text" class="form-control" name="passRate.version_number" maxLength="5"
										value="<s:property value="passRate.version_number"/>">
										<s:if test="fieldErrors.get('version_number').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('version_number').get(0)" /></span>
											</s:if>	
									<font color="red"><span id="integerVal2"></span></font>		
									</div>
								</div>
								
								 <div class="form-group">
									<label class="control-label col-md-3">Pass Start Date:<font color="red">*</font></label>
									<div class="col-md-4">
										<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="yyyy-mm-dd"
															data-date-viewmode="years" data-date-start-date="+0d">
											<input type="text" size="16" readonly class="form-control"
												id="pass_start_date"
												name="passRate.pass_start_date"
												value="<s:property value='passRate.pass_start_date' />" > 
												<span class="input-group-btn" >
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>	
											<script>
// 												var curr_date=new Date().toJSON().slice(0,10);
// 		                                        curr_date=curr_date.split("-");
// 		                                        curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
// 		                                        $('#pass_start_date').val(curr_date);
											</script>						
										</div>
										<s:if test="fieldErrors.get('pass_start_date').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('pass_start_date').get(0)" /></span>
											</s:if>
										<!-- /input-group -->
									</div>
								</div>
<!-- 								<div class="form-group"> -->
<!-- 									<label class="control-label col-md-3">Pass Start Date:<font color="red">*</font></label> -->
<!-- 									<div class="col-md-4"> -->
<!-- 										<div class="input-group input-medium date date-picker" -->
<!-- 															style="width: auto" data-date-format="dd/mm/yyyy" -->
<!-- 															data-date-viewmode="years" data-date-start-date="+0d"> -->
<!-- 											<input type="text" size="16" readonly class="form-control" -->
<!-- 												id="effective_start_date" -->
<!-- 												name="passRate.pass_start_date" -->
<%-- 												value="<s:property value='passRate.pass_start_date' />" >  --%>
<%-- 												<span class="input-group-btn" > --%>
<!-- 												<button class="btn default date-set" type="button"> -->
<!-- 													<i class="fa fa-calendar"></i> -->
<!-- 												</button> -->
<%-- 											</span>	 --%>
<%-- 											<script> --%>
<!-- // 												var curr_date=new Date().toJSON().slice(0,10); -->
<!-- // 		                                        curr_date=curr_date.split("-"); -->
<!-- // 		                                        curr_date=curr_date[2]+"/"+curr_date[1]+"/"+curr_date[0]; -->
<!-- // 		                                        $('#pass_start_date').val(curr_date); -->
<%-- 											</script>						 --%>
<!-- 										</div> -->
<%-- 										<s:if test="fieldErrors.get('pass_start_date').size() > 0"> --%>
<%-- 		     								<span style="color:red;"><s:property value="fieldErrors.get('pass_start_date').get(0)" /></span> --%>
<%-- 											</s:if> --%>
<!-- 										/input-group -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 								<div class="form-group"> -->
<!-- 										<label class="col-md-3 control-label">Status:</label> -->
<!-- 										<div class="col-md-3"> -->
<%-- 											<select class="form-control" name="passRate.status" > --%>
<!-- 												<option value="Active">Active</option> -->
<!-- 												<option value="INACTIVE">INACTIVE</option> -->
<%-- 											</select>									     --%>
<!-- 										</div> -->
<!-- 							    </div> -->
								
								<div class="form-group">
								<label class="col-md-3 control-label">Notes:</label>
								<div class="col-md-4">
									<%-- <input type="text" class="form-control" name="passRate.notes" maxLength="30"
										value="<s:property value="passRate.notes"/>"> --%>
									<textarea rows="3" class="form-control" id="notes" name="passRate.notes" maxLength="30"><s:property value="passRate.notes" /></textarea>								
									<s:if test="fieldErrors.get('notes').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('notes').get(0)" /></span>
									</s:if>
								</div>
							    </div>
															
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn blue">Save</button>
										<button type="button" class="btn default" onclick = "javascript:location.href='PassRateAction!view';">Cancel</button>
									</div>
								</div>

								
							<s:token/>
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