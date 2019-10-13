<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>


<script>
jQuery(document).ready(function(){

	 SelectElement('<s:property value="platform.status"/>');

});

function SelectElement(valueToSelect)
{ 
    var element = document.getElementById('status');
    element.value = valueToSelect;
}
function loadfloor(val){

    $.ajax({
        type: "post",
        url: '<%=request.getContextPath()%>/BayAction!getFloorName?orgid='+ val,
			success : function(result) {
				document.getElementById('floor_id').innerHTML = result;
			}
		});
}

function loadfloorOnpgLoad(){

	var e = document.getElementById("parent_id");
	var val = e.options[e.selectedIndex].value;

	var len= document.getElementById('floor_id').options.length;

	 if(len<=1 ) {
	
    $.ajax({
        type: "post",
        url: '<%=request.getContextPath()%>/BayAction!getFloorName?orgid='+ val,
			success : function(result) {
				document.getElementById('floor_id').innerHTML = result;
			}
		});
	 }
}

function loadbay(val){

    $.ajax({
        type: "post",
        url: '<%=request.getContextPath()%>/PlatformAction!getBayName?orgid='+ val,
			success : function(result) {
				document.getElementById('bay_id').innerHTML = result;
			}
		});
}

function loadbayOnpgLoad(){

	var e = document.getElementById("floor_id");
	var val = e.options[e.selectedIndex].value;

	var len= document.getElementById('bay_id').options.length;

	 if(len<=1 ) {
	
    $.ajax({
        type: "post",
        url: '<%=request.getContextPath()%>/PlatformAction!getBayName?orgid='+ val,
			success : function(result) {
				document.getElementById('bay_id').innerHTML = result;
			}
		});
	 }
}

function checkInt(){
	
	var val=document.getElementById('latitude').value;
	
	if(isNaN(val)){
	   //document.getElementById('integerVal').innerHTML='Invalid value';
	   alert('Please enter valid latitude value');
	   return false;
	}
	
	val=document.getElementById('longitude').value;
	
	if(isNaN(val)){
	   //document.getElementById('integerVal').innerHTML='Invalid value';
	   alert('Please enter valid longitude value');
	   return false;
	}
}
</script>
<title>Insert title here</title>
</head>
<body>
	<div class="page-content-wrapper">
		<div class="page-content">

			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Edit Platform
							</div>
							<div class="tools">
								
							</div>
						</div>
						
						<div class="portlet-body form">
							<!-- BEGIN FORM-->
							<form action="EditPlatformAction" onsubmit="return checkInt()" class="form-horizontal" method="post">
							<font color="red"><s:actionerror/></font>
							<input type="hidden" name="platform.platform_id" value="<s:property value="platform.platform_id"/>" />
							<br><br>
							
							<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label" 
										>Bus Station Name:<font color="red">*</font></label>

										<div class="col-md-4">
										
		<s:select list="orgNameMap" id="parent_id" name="platform.bay.floor.orgChart.org_chart_id" 
		cssClass="select2_category form-control" headerKey="0" headerValue="Select" onchange="loadfloor(this.value);"></s:select>
																		
										<%--  <select class="form-control" name="fareChartMaster.route_id" 
											 id="route_id" onclick="getRouteNo()">
											
											</select> --%>
											<s:if test="fieldErrors.get('bay.floor.orgChart.org_chart_id').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('bay.floor.orgChart.org_chart_id').get(0)" /></span>
											</s:if>	 
										

										</div>
									</div>
								</div>
	
	 							<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label" 
										>Floor Name:<font color="red">*</font></label>

										<div class="col-md-4">
										
		<%-- <s:select list="floorNameMap" id="floor_id" name="bay.floor.floor_id" 
		cssClass="select2_category form-control" headerKey="0" headerValue="Select"></s:select> --%>
		
		<select class="form-control" id="floor_id" name="platform.bay.floor.floor_id" onchange="loadbay(this.value)" onclick="loadfloorOnpgLoad()">
		  <option value="<s:property value="platform.bay.floor.floor_id"/>"><s:property value="platform.bay.floor.floor_name"/></option>
		</select>
																		
											<s:if test="fieldErrors.get('bay.floor.floor_id').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('bay.floor.floor_id').get(0)" /></span>
											</s:if>	 
										</div>
									</div>
								</div>
														
								
							    		
				 <div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label" 
										>Bay Name:<font color="red">*</font></label>

										<div class="col-md-4">
										
		<%-- <s:select list="bayNameMap" id="bay_id" name="platform.bay.bay_id" 
		cssClass="select2_category form-control" headerKey="0" headerValue="Select"></s:select>
		 --%>		
		 <select class="form-control" id="bay_id" name="platform.bay.bay_id" onclick="loadbayOnpgLoad()">
		  <option value="<s:property value="platform.bay.bay_id"/>"><s:property value="platform.bay.bay_name"/></option>
		</select>						
											<s:if test="fieldErrors.get('bay.bay_id').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('bay.bay_id').get(0)" /></span>
											</s:if>	 
											
										</div>
									</div>
								</div>								
								<div class="form-group">
								<label class="col-md-3 control-label">Platform Name:<font color="red">*</font></label>
								<div class="col-md-4">
									<input type="text" class="form-control" name="platform.platform_name" maxLength="50"
										value="<s:property value="platform.platform_name"/>">
									<s:if test="fieldErrors.get('platform_name').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('platform_name').get(0)" /></span>
									</s:if>
								</div>
							    </div>
							    
							    
							    
							    <div class="form-group">
								<label class="col-md-3 control-label">Longitude:</label>
								<div class="col-md-4">
									<input id="longitude" type="text" class="form-control" name="platform.longitude" maxLength="12"
										value="<s:property value="platform.longitude"/>">
									<s:if test="fieldErrors.get('longitude').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('longitude').get(0)" /></span>
									</s:if>
								</div>
							    </div>
							    
							    <div class="form-group">
								<label class="col-md-3 control-label">Latitude:</label>
								<div class="col-md-4">
									<input id="latitude" type="text" class="form-control" name="platform.latitude" maxLength="12"
										value="<s:property value="platform.latitude"/>">
									<s:if test="fieldErrors.get('latitude').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('latitude').get(0)" /></span>
									</s:if>
								</div>
							    </div>
							    
							    						    
							    <div class="form-group">
								<label class="col-md-3 control-label">Geo Fence:</label>
								<div class="col-md-4">
									<input type="text" class="form-control" name="platform.geo_fence_data" maxLength="50"
										value="<s:property value="platform.geo_fence_data"/>">
									<s:if test="fieldErrors.get('geo_fence_data').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('geo_fence_data').get(0)" /></span>
									</s:if>
								</div>
							    </div>
							    
							    <div class="form-group">
								<label class="col-md-3 control-label">Landmark:</label>
								<div class="col-md-4">
									<input type="text" class="form-control" name="platform.landmark" maxLength="50"
										value="<s:property value="platform.landmark"/>">
									<s:if test="fieldErrors.get('landmark').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('landmark').get(0)" /></span>
									</s:if>
								</div>
							    </div>
							    
							    <div class="form-group">
								<label class="col-md-3 control-label">Platform Description:</label>
								<div class="col-md-4">
									<input type="text" class="form-control" name="platform.platform_description" maxLength="100"
										value="<s:property value="platform.platform_description"/>">
									<s:if test="fieldErrors.get('platform_description').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('platform_description').get(0)" /></span>
									</s:if>
								</div>
							    </div>
							    
							    <div class="form-group">
										<label class="col-md-3 control-label">Status</label>
										<div class="col-md-3">
											<select id="status" class="form-control" name="platform.status" >												
												<option value="Active">Active</option>
												<option value="Inactive">Inactive</option> 
											</select>									    
										</div>
								</div>
							    			
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn blue" onSubmit="validate();">Save</button>
										<button type="button" class="btn default" onclick = "javascript:location.href='PlatformAction!view';">Cancel</button>										
									</div>
								</div>
								<s:token />
							</form>
							<!-- END FORM-->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>