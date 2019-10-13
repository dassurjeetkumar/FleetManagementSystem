<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link rel="stylesheet" type="text/css" 	href="<%=request.getContextPath()%>/assets/global/plugins/clockface/css/clockface.css" />
<link rel="stylesheet" type="text/css" 	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css" />
<link rel="stylesheet" type="text/css" 	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css" />
<link rel="stylesheet" type="text/css" 	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-colorpicker/css/colorpicker.css" />
<link rel="stylesheet" type="text/css" 	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" />
<link rel="stylesheet" type="text/css" 	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-datetimepicker/css/datetimepicker.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
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
					SCRAP Vehicle
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
							<i class="fa fa-bars"></i>Scrap Vehicle
					</div>
					</div>
					<div class="portlet-body">
						<div class="portlet-body form">
							<b>
								<font color="green"> <s:actionmessage/></font>
								<font color="red"> <s:actionerror/></font>
							</b>
							<form action="SaveScrapVehicle" class="form-horizontal form-row-seperated" method="post"> 
							
								<input type="hidden" name="vehicle.vehicleId" value="<s:property value='vehicle.vehicleId'/>" /> 
								<input type="hidden" name="editedScrappedVehicleId" value="<s:property value='vehicle.vehicleId'/>" />
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
							  <label class="control-label col-md-3">Scrap Date:<font
										color="red">*</font></label>
								<div class="col-md-3">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" data-date-end-date="0d" > 
								<input id="scrapdate" name="scrapdate" class="form-control" type="text" readonly="" size="16" name="rateMaster.effectiveStartDate" placeholder="Pick The Date" required="required"
								value="<s:property value='rateMaster.effectiveStartDate' />"
								>

								<span class="input-group-btn">
								<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
								</button>
								</span>
								<script>
 										var curr_date=new Date().toJSON().slice(0,10);
                                        curr_date=curr_date.split("-");
                                         curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
                                        var dtval=document.getElementById('startdate').value;	
                                        
                                         if(dtval==''){
                                         $('#startdate').val(curr_date);
                                        }
								</script>
								</div>
																	<span class="help-block" style="color: red"> 
												<s:property	value="%{fieldErrors.get('scrapdate').get(0)}" /></span>
								</div></div>
								
									<div class="form-group">
										<label class="control-label col-md-3"> Scrap Order <span class="required"> * </span></label>
										<div class="col-md-9">
											<textarea class="form-control" name="vehicleScrap.reason"  maxlength="200" rows="3"><s:property value="vehicleScrap.reason"/></textarea>
											<span class="help-block" style="color: red"> 
												<s:property	value="%{fieldErrors.get('vehicleScrap.reason').get(0)}" /></span>
										</div>
									</div>

									<div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9">
											<button type="submit" class="btn blue">Save</button>
											<button type="button" class="btn default" 	onclick="callCancell()">Cancel</button>
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