<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">


function cancelform(){
	window.location.href = 'PassDurationTypeAction.action';
}

</script>


</head>
<body>

<input type="text" id='a'>

<div class="page-content-wrapper">
	<div class="page-content">
		<div class="tab-content">
		<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						Pass Duration Type  <small></small>
					</h3>
					
				
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			<div class="tab-pane active" id="tab_0">
				<div class="portlet box grey-cascade">
				<div class="row">
				
			</div>
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i>Create Pass Duration Type
						</div>
						
					</div>
					
					<div class="portlet-body form">
					<s:if test="hasActionErrors()">
   <div class="alert alert-danger">
			<button class="close" data-close="alert"></button>
			<span>
			<s:actionerror/> </span>
		</div>
      
   
</s:if>
						<!-- BEGIN FORM-->
						
					<form action="createPassDurationTypeAction.action" class="form-horizontal" method="POST"  name ="frm" >
						
						<input type="hidden" name="createPassIssuerType" value="1" >		
						
								
								<div></div>
								
								
			<font color="red"><s:actionerror/></font>  <br>
							<div class="form-group">
													<label class="col-md-3 control-label">Pass Duration Name:<span class="required"> * </span></label>
													<div class="col-md-4">
												<input type="text" class="form-control" name="passduration.pass_duration_name" id="pass_duration_name" value='<s:property value="passduration.pass_duration_name" /> '>
												<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('pass_duration_name').get(0)}" />
										</span>
												</div>
												</div>
						
						<div class="form-group">
													<label class="col-md-3 control-label">Pass Type:<span class="required"> * </span></label>
													<div class="col-md-4">
<%-- 												<input type="text" class="form-control" name="passduration.pass_type_id" id="pass_type_id" value='<s:property value="passduration.pass_type_id" /> '> --%>
												
												<s:select list="passtype1" id="pass_type_id" 
											name="passduration.pass_type_id"  value='<s:property value="passduration.pass_type_id" />'
												cssClass="select2_category form-control"  
												 headerKey="0" headerValue="--select--"></s:select> 
												
												<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('pass_type_id').get(0)}" />
										</span>
												</div>
												</div>
												
												<div class="form-group">
													<label class="col-md-3 control-label">Pass Sub Type:<span class="required"> * </span></label>
													<div class="col-md-4">
<%-- 												<input type="text" class="form-control" name="passduration.pass_sub_type_id" id="pass_sub_type_id" value='<s:property value="passduration.pass_sub_type_id" /> '> --%>
												
												<s:select list="passsubtype" id="pass_sub_type_id" 
											name="passduration.pass_sub_type_id"  value='<s:property value="passduration.pass_sub_type_id" />'
												cssClass="select2_category form-control"  
												 headerKey="0" headerValue="--select--"></s:select> 
												
												<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('pass_sub_type_id').get(0)}" />
										</span>
												</div>
												</div>
						
						
							 <div class="form-group">
							  <label class="control-label col-md-3">Pass Start Date:<span class="required"> * </span></label>
								<div class="col-md-3">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" ><!-- data-date-start-date="+0d"> -->
								<input id="pass_start_date" class="form-control" type="text" readonly="" size="16" name="passduration.pass_start_date"
								value="<s:property value='passduration.pass_start_date' />"
								>
								<span class="input-group-btn">
								<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
								</button>
								</span>
								</div>
								</div></div>
								
								
								 <div class="form-group">
							  <label class="control-label col-md-3">Pass Expiry Date:<span class="required"> * </span></label>
								<div class="col-md-3">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" ><!-- data-date-start-date="+0d"> -->
								<input id="pass_expiry_date" class="form-control" type="text" readonly="" size="16" name="passduration.pass_expiry_date"
								value="<s:property value='passduration.pass_expiry_date' />"
								>
								<span class="input-group-btn">
								<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
								</button>
								</span>
								</div>
								</div></div>
								
							
							
							
										
							<div class="form-group">
													<label class="col-md-3 control-label">Status :<span class="required"> * </span></label>
													<div class="col-md-4">
													
												<select class="form-control input-small select2me" data-placeholder="Select..." name="passduration.status">
												<option value="ACTIVE">Active</option>
												<option value="INACTIVE">InActive</option>
											</select>
												<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('getStatus').get(0)}" />
										</span>
												<script>
											var orgTypeId ="<s:property value='passduration.status'/>";
											if(orgTypeId!=""){
 												document.getElementById(orgTypeId).selected= true; 
												
											}
											
										</script>
												</div>
												</div>
													<div class="form-group">
													<label class="col-md-3 control-label">Remarks :</label>
													<div class="col-md-4">
													
													<s:textarea cssClass="form-control" name="passduration.remarks" ></s:textarea>
													<script>
											var orgTypeId ="<s:property value='passduration.remarks'/>";
											if(orgTypeId!=""){
 												document.getElementById(orgTypeId).selected= true; 
												
											}
											
										</script>
												</div>
												</div>
											
													<div class="form-actions fluid">
											<div class="col-md-offset-3 col-md-9">
												<button type="submit" class="btn blue" >Save</button>
												<button class="btn default" type="button" onclick="cancelform()">Cancel</button>
														</div>
										</div>
										</form>	
										</div>	</div>
						
					
						<!-- END FORM-->
						

					</div>
				</div>
			</div>
		</div>
	


</body>
</html>