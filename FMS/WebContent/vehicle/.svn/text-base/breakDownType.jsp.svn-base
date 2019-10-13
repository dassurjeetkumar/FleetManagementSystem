
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
						View Accident Type <small></small>
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
								<i class="fa fa-globe"></i>View Accident Type
							</div>
							<div class="actions">
							<a href="#" class="btn green" onclick="callCreate()"> <i
								class="fa fa-plus"></i> Create
								
							</a>
							<!-- <a href="#" class="btn blue" onclick="callEdit()"> <i
								class="fa fa-pencil"></i> Edit
							</a> -->
								 <a  class="btn red" id="deleteDevice" onclick="calldelete()">
									<i class="fa fa-times"></i> Delete
								</a>

							</div>
						</div>
						<div class="portlet-body">
						<s:if test="%{insertstatus=='success'}">
								<div class="alert alert-danger">
									<button class="close" data-close="alert"></button>
									<span> Accident Type Created SuccessFully
									</span>
								</div>


							</s:if>
							<s:if test="%{deletestatus=='success'}">
								<div class="alert alert-danger">
									<button class="close" data-close="alert"></button>
									<span> Accident Type Deleted SuccessFully
									</span>
								</div>


							</s:if>
							<s:if test="%{deletestatus=='fail'}">
								<div class="alert alert-danger">
									<button class="close" data-close="alert"></button>
									<span> Could Not Delete Accident Type 
									</span>
								</div>


							</s:if>
							<input type="hidden" name="requestType" id="requestType"
								value="text" />
							<table class="table table-striped table-bordered table-hover"
								id="brakdowntypeView">
								<thead>
									<tr>
										<th style="width1: 8px;"></th>
										<th>Accident Type Id</th>
										<th>Accident Type </th>
										<th>Remarks</th>
										<th>Status</th>
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
	
		function calldelete() {
			var cnt = $(":checkbox:checked").length;
	
			if (cnt == 0) {
				bootbox.alert("Please Select Checkbox To Delete");
			} else if (cnt > 1) {
				bootbox.alert("Please Select One Checkbox To Delete");
			} else {
				$("input[type='checkbox']:checked").each(function() {

					val = this.value;
				});
				
				bootbox.confirm("Are you sure?",
								function(result) {

									if (result == true) {
										//alert('deleted');
										document.forms[0].action = "deleteAccidentType.action?accid="+ val;
										document.forms[0].submit();
									}
								});

				//var conf=confirm("Are you sure to delete this  device");
				//alert(conf);

			}

		}

			

		function callCreate() {
			document.forms[0].action = "createAccidenttypeview.action";
			document.forms[0].submit();
		}
		$(document).ready(function() {
			   window.history.pushState("","", "accidenttypeView.action");
			 });
	</script>
</form>