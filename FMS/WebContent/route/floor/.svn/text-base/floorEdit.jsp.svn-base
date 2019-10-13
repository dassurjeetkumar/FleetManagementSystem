<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>


<script>
jQuery(document).ready(function(){

	 SelectElement('<s:property value="floor.status"/>');

});

function SelectElement(valueToSelect)
{ 
    var element = document.getElementById('status');
    element.value = valueToSelect;
}

</script>
<title>Insert title here</title>
</head>
<body onload="getFareMaster();">
	<div class="page-content-wrapper">
		<div class="page-content">

			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Edit Floor
							</div>
							<div class="tools">
								
							</div>
						</div>
						
						<div class="portlet-body form">
							<!-- BEGIN FORM-->
							<form action="EditFloorAction" class="form-horizontal" method="post">
							<font color="red"><s:actionerror/></font>
							<input type="hidden" name="floor.floor_id" value="<s:property value="floor.floor_id"/>" />
							
							<br><br>	
							<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label" 
										>Bus Station Name:<font color="red">*</font></label>

										<div class="col-md-4">
										
		<s:select list="orgNameMap" id="parent_id" name="floor.orgChart.org_chart_id" 
		cssClass="select2_category form-control" headerKey="0" headerValue="Select"></s:select>
																		
										<%--  <select class="form-control" name="fareChartMaster.route_id" 
											 id="route_id" onclick="getRouteNo()">
											
											</select> --%>
											<s:if test="fieldErrors.get('orgChart.parent_id').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('orgChart.parent_id').get(0)" /></span>
											</s:if>	 
											<!-- <input type="text" class="form-control"
												placeholder="Enter text" id="route_id"
												name="fareChartMaster.route_id"> -->

										</div>
									</div>
								</div>
													
								<div class="form-group">
								<label class="col-md-3 control-label">Floor Name:<font color="red">*</font></label>
								<div class="col-md-4">
									<input type="text" class="form-control" name="floor.floor_name" maxLength="50"
										value="<s:property value="floor.floor_name"/>">
									<s:if test="fieldErrors.get('floor_name').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('floor_name').get(0)" /></span>
									</s:if>
								</div>
							    </div>
							    
							    
							    
							    <div class="form-group">
										<label class="col-md-3 control-label">Status</label>
										<div class="col-md-3">
											<select id="status" class="form-control" name="floor.status" >												
												<option value="Active">Active</option>
												<option value="Inactive">Inactive</option> 
											</select>									    
										</div>
								</div>
							    			
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn blue" onSubmit="validate();">Save</button>
										<button type="button" class="btn default" onclick = "javascript:location.href='FloorAction!view';">Cancel</button>										
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