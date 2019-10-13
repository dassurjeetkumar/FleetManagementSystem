<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/global/plugins/clockface/css/clockface.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-colorpicker/css/colorpicker.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-datetimepicker/css/datetimepicker.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="<%=request.getContextPath()%>/vehicle/keyBoardEvents.js"
	type="text/javascript"></script>

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
					FC REVENUE VEHICLE
				</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-bars"></i>FC Renew Vehicle
					</div>
					</div>
					<div class="portlet-body">
						<div class="portlet-body form">
							<form action="SaveFcRenewAction"
								class="form-horizontal form-row-seperated" method="post">
								<b>
									<font color="green"> <s:actionmessage/></font>
									<font color="red"> <s:actionerror/></font>
								</b>
								<input type="hidden" name="vehicle.vehicleId"
									value="<s:property value='vehicle.vehicleId'/>" /> <input
									type="hidden" name="fcRenewVehicleId"
									value="<s:property value='vehicle.vehicleId'/>" />
								<div class="form-body">
									<div class="form-group">
										<label class="control-label col-md-3"> Vehicle No </label>
										<div class="col-md-9">
											<div class="input-group input-medium" style="width: auto" >
												<input type="text" class="form-control" readonly value='<s:property value="vehicle.vehicleRegistrationNumber"/>' />
											</div>		
										</div>
									</div>

									<div class="form-group">
										<label class="control-label col-md-3">
											FC Expiry Date
										</label>
										<div class="col-md-9">
											<div class="input-group input-medium" style="width: auto" >
												<input type="text" class="form-control" readonly
													name="vehicleFcRenew.fcExpiryDateString" id="expiryDate"
													value="<s:property value="vehicleFcRenew.fcExpiryDateString"/>"/>
												
				<s:token/>							</div>
											<script>
											var fcExpiryDate = "<s:property value='fcExpiryDate'/>";
											$('#expiryDate').val(fcExpiryDate);
											</script>
											<span class="help-block" style="color: red"> <s:property
													value="%{fieldErrors.get('vehicleFcRenew.fcExpiryDateString').get(0)}" /></span>
										</div>

									</div>
									<div class="form-group">
										<label class="control-label col-md-3"> FC Renew Date <span
											class="required"> * </span>
										</label>
										<div class="col-md-9">
											<div class="input-group input-medium date date-picker"
												style="width: auto" data-date-format="dd-mm-yyyy"
												data-date-viewmode="years" data-date-start-date="+0d">
												<input type="text" class="form-control" readonly
													name="vehicleFcRenew.fcRenewDateString"
													value="<s:property value="%{vehicleFcRenew.fcRenewDateString}"/>">
												<span class="input-group-btn">
													<button class="btn default" type="button">
														<i class="fa fa-calendar"></i>
													</button>
												</span>
											</div>
											
											<span class="help-block" style="color: red"> <s:property
													value="%{fieldErrors.get('vehicleFcRenew.fcRenewDateString').get(0)}" /></span>
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

	
</script>