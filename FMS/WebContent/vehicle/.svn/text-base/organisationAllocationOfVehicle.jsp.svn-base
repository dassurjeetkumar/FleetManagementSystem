<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link rel="stylesheet" type="text/css" 	href="<%=request.getContextPath()%>/assets/global/plugins/clockface/css/clockface.css" />
<link rel="stylesheet" type="text/css" 	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css" />
<link rel="stylesheet" type="text/css" 	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css" />
<link rel="stylesheet" type="text/css" 	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-colorpicker/css/colorpicker.css" />
<link rel="stylesheet" type="text/css" 	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" />
<link rel="stylesheet" type="text/css" 	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-datetimepicker/css/datetimepicker.css" />
<link rel="stylesheet" 	href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
<link rel="stylesheet" 	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="<%=request.getContextPath()%>/vehicle/keyBoardEvents.js" type="text/javascript"></script>

<style type="text/css">
.col-md-9,.col-md-8 {
	width: 30%;
}

.help-block,ul,li {
	list-style: none;
}
</style>
</head>

<div class="page-content-wrapper">
	<div class="page-content">
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
					Assign Organization 
				</h3>
			</div>
		</div>
<%-- 		<div class="row">
			<div class="col-md-12" align="center" style="font-size: 1.2em">
				<span class="help-block" style="color: red; f list-style: none"><s:actionerror /></span>
				<span class="help-block" style="color: green; list-style: none"><s:actionmessage /></span>
			</div>
		</div> --%>
		<div class="row">
			<div class="col-md-12">
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-bars"></i>Assign Organization
						</div>
					</div>
					<div class="portlet-body">
						<div class="portlet-body form">
							<form action="AllocateOrgToVehicle"	class="form-horizontal form-row-seperated" method="post">
								<b>
									<font color="green"> <s:actionmessage/></font>
									<font color="red"> <s:actionerror/></font>
								</b>
								<s:hidden name="orgEditedVehicleId" value="%{orgEditedVehicleId}"/>
								<s:hidden name="IsEditedVehicle" value="1"/>
								<div class="form-group">
									<label class="col-md-3 control-label">Organization Type<span class="required"> * </span></label>
									<div class="col-md-3">
										<select id="unit_type_id" name="vehicleOrg.depotOrUnit.orgType.org_type_id" class="select2-container select2_category form-control"  onchange="getUnitNames()">
											<s:iterator value="orgTypeList" status="aaa">
												<option id="org<s:property   value="org_type_id" />" value='<s:property   value="org_type_id" />'>
													<s:property value="orgType" />
												</option>
											</s:iterator>
										</select>
										<script>
											var orgType ="<s:property value='vehicleOrg.depotOrUnit.orgType.org_type_id'/>";
											if(orgType!=""){
												document.getElementById("org"+orgType).selected= true;
											}
										</script>
											<span class="help-block" style="color: red">
												<s:property value="%{fieldErrors.get('vehicleOrg.depotOrUnit.orgType').get(0)}" />
											</span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">Organization Unit Name <span class="required"> * </span></label>
									<div class="col-md-3">
										<select class="select2_category form-control" name="vehicleOrg.depotOrUnit.org_chart_id" id="depot" onload="getUnitNames()">
											<option value="0">Select</option>
										</select> 
										<span class="help-block" style="color: red"> 
											<s:property value="%{fieldErrors.get('vehicleOrg.depotOrUnit.org_chart_id').get(0)}" />
										</span>
									</div>
								</div>
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-3">
										<button type="submit" class="btn blue">Save</button>
										<button type="button" class="btn default" onclick="callCancell()">Cancel</button>
									</div>
								</div>
								
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
<head>
<script>
	function callCancell() {
		window.location.href = 'VehicleDetails.action';
	}
	getUnitNames();
	function getUnitNames()
	{
		$('#select2-chosen-2').html("Select");
		var e = document.getElementById("unit_type_id");
		var strUser = e.options[e.selectedIndex].value;
		
		var len= document.getElementById('depot').options.length;
		       $.ajax({
		           type: "post",
		           async:false,
		           url: '<%=request.getContextPath()%>/findUnitName?orgid='+strUser,
		           success: function(result) {
		               document.getElementById('depot').innerHTML=result;
		           }
		       });
		
	}
	
	var orgName= "<s:property value='vehicleOrg.depotOrUnit.org_chart_id'/>";
	if(orgName!=""){
		document.getElementById("orgName"+orgName).selected=true;
	}
</script>
