<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>


<script>
jQuery(document).ready(function(){

	 SelectElement('<s:property value="bay.status"/>');

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

</script>
<title>Insert title here</title>
</head>
<body >
	<div class="page-content-wrapper">
		<div class="page-content">

			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Edit Bay
							</div>
							<div class="tools">
								
							</div>
						</div>
						
						<div class="portlet-body form">
							<!-- BEGIN FORM-->
							<form action="EditBayAction" class="form-horizontal" method="post">
							<font color="red"><s:actionerror/></font>
							<input type="hidden" name="bay.bay_id" value="<s:property value="bay.bay_id"/>" />
							
							<br><br>	
							<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label" 
										>Bus Station Name:<font color="red">*</font></label>

										<div class="col-md-4">
										
		<s:select list="orgNameMap" id="parent_id" name="bay.floor.orgChart.org_chart_id" 
		cssClass="select2_category form-control" headerKey="0" headerValue="Select" onchange="loadfloor(this.value);"></s:select>
																		
										<%--  <select class="form-control" name="fareChartMaster.route_id" 
											 id="route_id" onclick="getRouteNo()">
											
											</select> --%>
											<s:if test="fieldErrors.get('floor.orgChart.org_chart_id').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('floor.orgChart.org_chart_id').get(0)" /></span>
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
		
		<select class="form-control" id="floor_id" name="bay.floor.floor_id" onclick="loadfloorOnpgLoad()">
		<option value="<s:property value="bay.floor.floor_id"/>"><s:property value="bay.floor.floor_name"/></option>
		</select>
																		
											<s:if test="fieldErrors.get('floor.floor_id').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('floor.floor_id').get(0)" /></span>
											</s:if>	 
										</div>
									</div>
								</div>
													
										<div class="form-group">
								<label class="col-md-3 control-label">Bay Name:<font color="red">*</font></label>
								<div class="col-md-4">
									<input type="text" class="form-control" name="bay.bay_name" maxLength="50"
										value="<s:property value="bay.bay_name"/>">
									<s:if test="fieldErrors.get('bay_name').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('bay_name').get(0)" /></span>
									</s:if>
								</div>
							    </div>
							    
							    
							    
							    <div class="form-group">
										<label class="col-md-3 control-label">Status</label>
										<div class="col-md-3">
											<select id="status" class="form-control" name="bay.status" >												
												<option value="Active">Active</option>
												<option value="Inactive">Inactive</option> 
											</select>									    
										</div>
								</div>
							    			
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn blue" onSubmit="validate();">Save</button>
										<button type="button" class="btn default" onclick = "javascript:location.href='BayAction!view';">Cancel</button>										
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