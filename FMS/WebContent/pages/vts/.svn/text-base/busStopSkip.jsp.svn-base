
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script src="assets/vts/js/skiproute.js"></script>


<form>
<input type="hidden" id='deviceid' name='deviceid' /> <input
		type="hidden" id='vehicleid' name='vehicleid' />
		<input type="hidden" id='scheduleno' name='scheduleno' /> 
	<div class="page-content-wrapper">
		<div class="page-content">
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div class="modal fade" id="portlet-config" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true"></button>
							<h4 class="modal-title">Modal title</h4>
						</div>
						<div class="modal-body">Widget settings form goes here</div>
						<div class="modal-footer">
							<button type="button" class="btn blue">Save changes</button>
							<button type="button" class="btn default" data-dismiss="modal">Close</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>

			<!-- END STYLE CUSTOMIZER -->
			<!-- BEGIN PAGE HEADER-->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						Bus Stop Skip and Route Deviation Alert<small></small>
					</h3>

					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->

			<div class="row">
				<div class="col-md-6" id="chart_alert">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>View Bus Stop Skip
							</div>
							<div class="tools">
								<a class="collapse" id="vehicle_chart_alert" href="javascript:;">
								</a> <a class="config" data-toggle="modal" href="#portlet-config">
								</a> <a class="reload" href="javascript:;"> </a> <a class="remove"
									href="javascript:;"> </a>
							</div>
						</div>
						<div class="portlet-body" id="alert4">
							<input type="hidden" name="requestType" id="requestType"
								value="text" />
							<table class="table table-striped table-bordered table-hover"
								id="skipAlert">
								<thead>
									<tr>
										<th>VEHICLE NUMBER</th>
										<th>SCHEDULE NUMBER</th>
										<th>Total Stop Count</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<div class="col-md-7" >
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>View Route Deviation
							</div>
							<div class="tools">
								<a class="collapse" id="vehicle_chart_alert" href="javascript:;">
								</a> <a class="config" data-toggle="modal" href="#portlet-config">
								</a> <a class="reload" href="javascript:;"> </a> <a class="remove"
									href="javascript:;"> </a>
							</div>
						</div>
						<div class="portlet-body">
							<input type="hidden" name="requestType" id="requestType"
								value="text" />
							<table class="table table-striped table-bordered table-hover"
								id="deviationAlert">
								<thead>
									<tr>
										<th>VEHICLE NUMBER</th>
										<th>SCHEDULE NUMBER</th>
										<th>GPS TIME</th>
									</tr>
								</thead>
							</table>


						</div>

					</div>
				</div>
				<!-- END EXAMPLE TABLE PORTLET-->
			</div>

		</div>

		<!-- END PAGE CONTENT-->
	</div>
	<div style="display: none;" id="mymodal1" class="modal fade"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 800px; height: 500px;"
				align="justify">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 id="vehicleno123" class="modal-title"></h4>
				</div>
				<div>
					<p>
					<div class="portlet box white ">
						<div>
							<input type="hidden" name="requestType" id="requestType" />
							<div>
								<div class="portlet solid white">
									<div class="form-group">
										<label class="col-md-3 control-label"></label>
									</div>
									<div class="portlet-body" id="alert5" style="display: none">
										<table class="table table-striped table-bordered table-hover"
											id="skipStopAlert_data1">
											<thead>
												<tr>
													<th>#</th>
													<th>Trip No</th>
													<th>Time Of Skipping</th>
													<th>BUS STOP NAME SKIPPED</th>
												</tr>
											</thead>
										</table>

									</div>
								</div>
							</div>
						</div>
					</div>
					</p>
				</div>

			</div>
		</div>
	</div>
	
	<script type="text/javascript">
	$(document).ready(function() {
		skipRoute();

	});
</script>
</form>