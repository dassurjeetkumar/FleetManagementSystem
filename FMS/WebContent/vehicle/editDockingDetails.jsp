<%@ taglib prefix="s" uri="/struts-tags"%>
<%-- <link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/global/plugins/select2/select2.css" /> --%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
<input type="hidden" name="id" id="id" />
<div class="page-content-wrapper">
	<div class="page-content">
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
				<h3 class="page-title">
					Edit Docking History <small> Edit Docking History</small>
				</h3>
				<ul class="page-breadcrumb breadcrumb">
					<li><i class="fa fa-home"></i> <a href="index.html">Home</a> <i
						class="fa fa-angle-right"></i></li>
					<li><a href="#">Vehicle Management</a> <i
						class="fa fa-angle-right"></i></li>
					<li><a href="#">Docking</a> <i class="fa fa-angle-right"></i>
					<li><a href="#">Edit Docking History</a></li>
				</ul>
				<!-- END PAGE TITLE & BREADCRUMB-->
			</div>
		</div>
		<div class="row">
			<div class="col-md-12" align="center" style="font-size: 1.1em">

				<span class="help-block" style="color: red"><s:actionerror /></span>
				<span class="help-block" style="color: green"><s:actionmessage /></span>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN EXAMPLE TABLE PORTLET-->
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i> Edit Docking
						</div>
						<div class="tools">
							<a href="javascript:;" class="collapse"> </a> <a
								href="#portlet-config" data-toggle="modal" class="config"> </a>
							<!-- <a href="javascript:;" class="reload">
								</a>
								<a href="javascript:;" class="remove">
								</a> -->
						</div>
					</div>
					<div class="portlet-body form">
						<form action="saveEditDockingAction"
							class="form-horizontal form-row-seperated">
							<div class="form-body">

								<div class="form-group">
									<s:hidden name="updatedDockingId" cssClass="form-control"
										value='%{dockingDetails.docking_id}' />
										<s:hidden name="updatedDockingVehicleId" cssClass="form-control"
										value='%{dockingDetails.vehicle.vehicleId}' />
									<label class="control-label col-md-3">Start Time</label>
									<div class="col-md-3">
										<div class="input-group date form_datetime">
											<input type="text" size="16" readonly
												name="upadtedDockingStartTime"
												value="<s:property value='dockingDetails.start_time'/>"
												class="form-control"> <span class="input-group-btn">
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
										</div>

									</div>
								</div>

								<div class="form-group">

									<label class="control-label col-md-3">End Time</label>
									<div class="col-md-3">
										<div class="input-group date form_datetime">
											<input type="text" size="16" readonly
												name="upadtedDockingEndTime"
												value="<s:property value='dockingDetails.end_time'/>"
												class="form-control"> <span class="input-group-btn">
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
										</div>

									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">Docking Type</label>
									<div class="col-md-3">

										<select class="select2_category form-control"
											name="updatedDockingTypeId">

											<s:iterator value="dockingTypeList" status="aaa">
												<option value='<s:property value="dockinTypeId"/>'
													id="dockingId${dockinTypeId}">
													<s:property value="dockingType" />
												</option>
											</s:iterator>
											<script type="text/javascript">
												var dockingTypeId = "<s:property value='dockingDetails.dockingType.docking_type_id'/>";
												document
														.getElementById("dockingId"
																+ dockingTypeId).selected = true;
											</script>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">Docking Batch
										name</label>
									<div class="col-md-3">
										<input type="text" class="form-control"
											name="updatedDockingBatchName" placeholder="Enter text" value='<s:property value='dockingDetails.docking_batch_name'/>'>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">FIP Change</label>
									<div class="col-md-3">

										<select class="select2_category form-control"
											name="updatedFipChange">

											<option value="YES" id="fipYes">YES</option>
											<option value="NO" id="fipNo">NO</option>

										</select>
										<script type="text/javascript">
											var a = "<s:property value='dockingDetails.fip_change'/>";
											if (a == "YES") {
												document
														.getElementById("fipYes").selected = true;
											} else {
												document
														.getElementById("fipNo").selected = true;
											}
										</script>

									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">EOC Change</label>
									<div class="col-md-3">

										<select class="select2_category form-control"
											name="updatedEocChange">

											<option value="YES" id="eocYes">YES</option>
											<option value="NO" id="eocNo">NO</option>

										</select>
										<script type="text/javascript">
											var a = "<s:property value='dockingDetails.eoc_change'/>";
											if (a == "YES") {
												document
														.getElementById("eocYes").selected = true;
											} else {
												document
														.getElementById("eocNo").selected = true;
											}
										</script>

									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">Component Type</label>
									<div class="col-md-3">

										<select name="updatedComponentTypeId"
											class="select2_category form-control">
											<s:iterator value="componenetsTypeList" status="aa">
												<option value="<s:property value="componentTypeId"/>"
													id="componentTypeId${componentTypeId}">
													<s:property value="componentName" />
												</option>
											</s:iterator>
										</select> `
										<script type="text/javascript">
											var componentId = "<s:property value='dockingDetails.componenetType.componentTypeId'/>";
											document
													.getElementById("componentTypeId"
															+ componentId).selected = true;
										</script>
									</div>
								</div>

								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn blue">Submit</button>
										<button type="button" class="btn default" onclick="callCancell()">Cancel</button>
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
<script>
function callCancell(){
	
	window.location.href='VehicleDetails.action';
}
</script>