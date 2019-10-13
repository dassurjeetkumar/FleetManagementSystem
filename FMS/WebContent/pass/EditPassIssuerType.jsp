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

	 SelectElement('<s:property value="passissuer.status"/>');

});

function SelectElement(valueToSelect)
{ 
  var element = document.getElementById('status');
  element.value = valueToSelect;
}

function cancelform(){
	window.location.href = 'PassIssuerTypeAction.action';
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
						Pass Issuer Type  <small></small>
					</h3>
					
				
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			<div class="tab-pane active" id="tab_0">
				<div class="portlet box grey-cascade">
				<div class="row">
				
			</div>
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i>Create Pass Issuer Type
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
						
					<form action="editPassIssuerTypeAction.action" class="form-horizontal" method="POST"  name ="frm" >
						
						<input type="hidden" name="passissuer.pass_issuer_id" 
								value="<s:property value='passissuer.pass_issuer_id'/>" />
							<span id="errorMsg" style="color:red;word-wrap: break-word;" ><s:actionerror/></span>
						
								
								<div></div>
								
								
			
							<div class="form-group">
													<label class="col-md-3 control-label">Pass Issuer Name:<span class="required"> * </span></label>
													<div class="col-md-4">
												<input type="text" class="form-control" name="passissuer.pass_issuer_name" id="pass_issuer_name" value='<s:property value="passissuer.pass_issuer_name" /> '>
												<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('getPass_issuer_name').get(0)}" />
										</span>
												</div>
												</div>
						
						
							
										
							<div class="form-group">
													<label class="col-md-3 control-label">Status :<span class="required"> * </span></label>
													<div class="col-md-4">
													
												<select class="form-control input-small select2me" data-placeholder="Select..." name="passissuer.status">
												<option value="ACTIVE">Active</option>
												<option value="INACTIVE">InActive</option>
											</select>
												<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('getStatus').get(0)}" />
										</span>
												<script>
											var orgTypeId ="<s:property value='passissuer.status'/>";
											if(orgTypeId!=""){
 												document.getElementById(orgTypeId).selected= true; 
												
											}
											
										</script>
												</div>
												</div>
													<div class="form-group">
													<label class="col-md-3 control-label">Remarks :</label>
													<div class="col-md-4">
													
													<s:textarea cssClass="form-control" name="passissuer.remarks" ></s:textarea>
													<script>
											var orgTypeId ="<s:property value='passissuer.remarks'/>";
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