<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE html>
<html>
<head>
<script>

$(document).ready(function() {
	//window.history.pushState("","", "ServiceTypeList.action");
	
	   var w=$('#errorMsg span').html();
	   //alert(w);
	    w=w.replace(/@/g,"<br>");

	   $('#errorMsg').html(''+w+'');
  
});
jQuery(document).ready(function(){

	 SelectElement('<s:property value="passissuercenter.status"/>');

});

function SelectElement(valueToSelect)
{ 
  var element = document.getElementById('status');
  element.value = valueToSelect;
}

function cancelform(){
	window.location.href = 'PassSubType.action';
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
						Pass Issuer Center  <small></small>
					</h3>
					
				
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			<div class="tab-pane active" id="tab_0">
				<div class="portlet box grey-cascade">
				<div class="row">
				
			</div>
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i>Edit Pass Sub Type
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
						
					<form action="editPassSubTypeAction.action" class="form-horizontal" method="POST"  name ="frm" >
						
						<input type="hidden" name="passsubtype.pass_subtype_id" 
								value="<s:property value='passsubtype.pass_subtype_id'/>" />
							<span id="errorMsg" style="color:red;word-wrap: break-word;" ><s:actionerror/></span>
						
								
								<div></div>
								
								
			
							<div class="form-body"> 
									<div class="form-group">
										<label class="col-md-3 control-label">
											Pass Type Name:<font color="red">*</font>
										</label>
										<div class="col-md-4"> 
 											<%-- <s:select list="servicelist" id="service_type_id" name="peakhour.servicetype.service_type_id"  
													cssClass="select2_category form-control" headerKey="0" headerValue="Select"
													onchange="getRateMaster()"></s:select>  --%>
													
									<s:select list="passtypelist" cssClass="select2_category form-control" id="pass_type_id" name="passsubtype.pass_type_id">
									
										</s:select>
 												<s:if test="fieldErrors.get('pass_type_id').size() > 0"> 
		     								<span style="color:red;"><s:property value="fieldErrors.get('pass_type_id').get(0)" /></span> 
 											</s:if> 
										</div>
									</div>
								</div>
								
								 <div class="form-group">
													<label class="col-md-3 control-label">Pass Sub Type:<span class="required"> * </span></label>
													<div class="col-md-4">
												<input type="text" class="form-control" name="passsubtype.sub_type_name" id="sub_type_name" value='<s:property value="passsubtype.sub_type_name" /> '>
												<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('sub_type_name').get(0)}" />
										</span>
												</div>
												</div>
						
							
										
							<div class="form-group">
													<label class="col-md-3 control-label">Status :<span class="required"> * </span></label>
													<div class="col-md-4">
													
												<select class="form-control input-small select2me" data-placeholder="Select..." name="passsubtype.status">
												<option value="Y">Active</option>
												<option value="N">InActive</option>
											</select>
												<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('status').get(0)}" />
										</span>
												<script>
											var orgTypeId ="<s:property value='passsubtype.status'/>";
											if(orgTypeId!=""){
 												document.getElementById(orgTypeId).selected= true; 
												
											}
											
										</script>
												</div>
												</div>
													<div class="form-group">
													<label class="col-md-3 control-label">Remarks :</label>
													<div class="col-md-4">
													
													<s:textarea cssClass="form-control" name="passsubtype.remarks" ></s:textarea>
													<script>
											var orgTypeId ="<s:property value='passsubtype.remarks'/>";
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