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
<script src="<%=request.getContextPath()%>/vehicle/keyBoardEvents.js"	type="text/javascript"></script>

<style type="text/css">
.col-md-9,.col-md-8 {
	width: 30%;
}

.help-block,ul,li {
	list-style: none;
}
</style>
</head>
<body onload='resetToDepot()'>
<div class="page-content-wrapper">
	<div class="page-content">
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
					TRANSFER VEHICLE
				</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-bars"></i>Transfer Vehicle
						</div>
					</div>
					<div class="portlet-body">
						<div class="portlet-body form">
						<form action="SaveTransferVehicleAction"
								class="form-horizontal form-row-seperated" method="post">
								<b>
									<font color="green"> <s:actionmessage/></font>
									<font color="red"> <s:actionerror/></font>
								</b>
								<input type="hidden" name="vehicle.vehicleId" value="<s:property value='vehicle.vehicleId'/>" />
								<input type="hidden" name="transfervehicleId" value="<s:property value='vehicle.vehicleId'/>" />
								<div class="form-body">
									<div class="form-group">
										<label class="control-label col-md-3">
											Vehicle No
										</label>
										<div class="col-md-9">
											<div class="input-group input-medium" style="width: auto" >
												<input type="text" class="form-control" readonly value='<s:property value="vehicle.vehicleRegistrationNumber"/>' />
											</div>
		<s:token/>								</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">
											Transfer Type
										</label>
										<div class="col-md-9">
											<select class="form-control" name="transferHistory.transferVehicleType" id="transferTypeList">
												<option id="Permanent" value="Permanent">Permanent</option>
												<option id="Temporary" value="Temporary">Temporary</option>
											</select>
											<script>
												var trnsfrType = "<s:property value='transferHistory.transferVehicleType'/>";
												if (trnsfrType != "") {
													if (trnsfrType=="Permanent")
														document.getElementById("Permanent").selected = true;
													else
														document.getElementById("Temporary").selected = true;
												}
											</script>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">From Date<span
											class="required"> * </span>
										</label>
										<div class="col-md-9">
											<div class="input-group input-medium date date-picker" 	style="width: auto" data-date-format="dd-mm-yyyy" data-date-viewmode="years">
												<input type="text" class="form-control" readonly
													name="transferHistory.fromDateString" value="<s:property value="%{transferHistory.fromDateString}"/>">
												<span class="input-group-btn">
													<button class="btn default" type="button">
														<i class="fa fa-calendar"></i>
													</button>
												</span>
											</div>
											<span class="help-block" style="color: red"> <s:property
														value="%{fieldErrors.get('transferHistory.fromDateString').get(0)}" /></span>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">Till Date</label>
										<div class="col-md-9">
											<div class="input-group input-medium date date-picker"	style="width: auto" data-date-format="dd-mm-yyyy" data-date-viewmode="years">
												<input type="text" class="form-control" readonly name="transferHistory.toDateString" value="<s:property value="transferHistory.toDateString"/>">
												<span class="input-group-btn">
													<button class="btn default" type="button">
														<i class="fa fa-calendar"></i>
													</button>
												</span>
											</div>
											<span class="help-block" style="color: red"> <s:property
													value="%{fieldErrors.get('transferHistory.toDateString').get(0)}" /></span>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">
											From Unit 
										</label>
										<div class="col-md-9">
											<%-- <s:select name="transferHistory.fromOrganisationId.org_chart_id" cssClass="select2_category form-control" list="depotList" listKey="org_chart_id" listValue="org_name">
											</s:select> --%>
											<select name="transferHistory.fromOrganisationId.org_chart_id"  id='fromUnitList' class="select2_category form-control" onchange='resetToDepot()'>
											<s:iterator value="depotList"> 
											<option id="fromOrg<s:property value='org_chart_id'/>" value="<s:property value='org_chart_id'/>"> <s:property value='org_name'/> </option>
											</s:iterator>
											</select>
										</div>
										<script>
										var fromDepot = "<s:property value='vehicle.depotOrUnit.org_chart_id'/>";
										if(fromDepot!=null)
											{
											document.getElementById("fromOrg"+fromDepot).selected = true;
											}
										</script>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">
											To Unit
										</label>
										<div class="col-md-9">
											<s:select name="transferHistory.toOrganisationId.org_chart_id" id='toUnitList'	cssClass="select2_category form-control" list="depotList" listKey="org_chart_id" listValue="org_name">
											</s:select>
											<span class="help-block" style="color: red"> <s:property
													value="%{fieldErrors.get('transferHistory.toOrganisationId.toOrganisationId').get(0)}" /></span>
										</div>
										
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">Description</label>

										<div class="col-md-9">
											<textarea class="form-control" name="transferHistory.description"	placeholder="Enter text" rows="3"></textarea>
										</div>
									</div>
									<div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9">
											<button type="submit" class="btn blue">Save</button>
											<button type="button" class="btn default"
												onclick="callCancell()">Cancel</button>
										</div>
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

var transferedType = '<s:property value="transferedType"/>';
if(transferedType=="temp"){
	var htmlCode  =$('#transferTypeList').html();
	
	htmlCode += "<option id='Return' value='Return'>Return</option>";
	$('#transferTypeList').html(htmlCode);
}

function resetToDepot()
{
	
	try{
		var fulldata = $("#fromUnitList").html();
		$('#select2-chosen-2').text('Select');
		$("#toUnitList").html(fulldata);
		var from =$('#fromUnitList').val();
		$("#toUnitList>option[value='"+from+"']").remove();
		console.log(from+';;;;');
	}catch(a){
		console.log("error"+a);
	}
}
</script>