
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<form>
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
						View Device History <small></small>
					</h3>

					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->

			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>View Device History
							</div>

						</div>
						<div class="portlet-body">
							<input type="hidden" name="requestType" id="requestType"
								value="text" />
							<table class="table table-striped table-bordered table-hover"
								id="deviceHistory">
								<thead>
									<tr>
										<th style="width1: 8px;"></th>
										<th>Device History Id</th>
										<th>Device Id</th>
										<th>On Boarding Date</th>
										<th>UserId</th>
										<th>Updated Date</th>
										<th>Purchased Date</th>
										<th>Malfunctioning Date</th>
										<th>Status</th>
										<th>Remarks</th>
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

	<script>
		function callEdit() {

			var cnt = $(":checkbox:checked").length;
			var val;
			if (cnt == 0) {
				bootbox.alert("Please Select Checkbox To Edit");
			} else if (cnt > 1) {
				bootbox.alert("Please Select One Checkbox To Edit");
			} else {
				$("input[type='checkbox']:checked").each(function() {

					val = this.value;
				});
				document.forms[0].action = "editDevice.action?deviceid=" + val;
				document.forms[0].submit();

			}
		}
		function calldelete() {
			var cnt = $(":checkbox:checked").length;
			var val;
			if (cnt == 0) {
				bootbox.alert("Please Select Checkbox To Delete");
			} else if (cnt > 1) {
				bootbox.alert("Please Select One Checkbox To Delete");
			} else {
				$("input[type='checkbox']:checked").each(function() {

					val = this.value;
				});
				document.forms[0].action = "deleteDevice.action?deviceid="
						+ val;
				document.forms[0].submit();

			}

		}

		function callCreate() {
			document.forms[0].action = "createDevice.action";
			document.forms[0].submit();
		}
	</script>
</form>