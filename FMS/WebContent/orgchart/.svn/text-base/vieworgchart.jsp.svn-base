
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
						View org Chart  <small></small>
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
								<i class="fa fa-globe"></i>View Org Chart
							</div>
							<div class="actions">
								<a href="#" class="btn green" onclick="callCreate()"> <i
								class="fa fa-plus"></i> Create
								
							</a>
							<a href="#" class="btn blue" onclick="callEdit()"> <i
								class="fa fa-pencil"></i> Edit
							</a>
								 <a  class="btn red" id="deleteDevice" onclick="calldelete()">
									<i class="fa fa-times"></i> Delete
								</a>


							</div>
						</div>
						<div class="portlet-body">
						<div class="row">
				<div class="col-md-6">
					<div class="portlet yellow-lemon box">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-cogs"></i>Contextual Menu with Drag & Drop
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse">
								</a>
								<a href="#portlet-config" data-toggle="modal" class="config">
								</a>
								<a href="javascript:;" class="reload">
								</a>
								<a href="javascript:;" class="remove">
								</a>
							</div>
						</div>
						 <div class="portlet-body"> 
							 <div id="tree_3" class="tree-demo">
							</div>
							<div class="alert alert-success no-margin margin-top-10">
								 Remarks! Opened and selected nodes will be saved in the user's browser, so when returning to the same tree the previous state will be restored.
							</div> 
						</div> 
					</div>
				</div>
				
			</div>
						
						
						
							<input type="hidden" name="requestType" id="requestType"
								value="text" />
								
							 <table class="table table-striped table-bordered table-hover"
								id="orgchart">
								<s:if test="%{insertmsg=='success'}"><b><FONT color="green"><s:actionmessage /></FONT></b></s:if>
								<s:if test="%{insertmsg=='database'}"><b><FONT color="red"><s:actionmessage /></FONT></b></s:if>
								<s:if test="%{updatemsg=='database'}"><b><FONT color="red"><s:actionmessage /></FONT></b></s:if>
								<s:if test="%{updatemsg=='success'}"><b><FONT color="green"><s:actionmessage /></FONT></b></s:if>
								<s:if test="%{deletestatus=='database'}"><b><FONT color="red"><s:actionmessage /></FONT></b></s:if>
								<s:if test="%{deletestatus=='success'}"><b><FONT color="green"><s:actionmessage /></FONT></b></s:if>
								<thead>
									<tr>
										<th style="width1: 8px;"></th>
										<th>Organization Chart Id</th>
										<th>Organization Name</th>
										
										<th>Organization Code</th>
										<th>Organization Code(Kannada)</th>
										<th>Parent Organization Name</th>
										<th>Organization Name(Kannada)</th>
										<th>Organization Website</th>
										<th>Organization Address</th>
										<th>Organization Phone Number</th>
										<th>City</th>
										<th>State</th>
										<th>Country</th>
										<!-- <th>Latitude</th> -->
										<th>Landmark</th>
										<!-- <th>Longitude</th> -->
										<th>Contact Person</th>
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
	function callEdit(){
		
		var cnt = $(":checkbox:checked").length;
		var val;
		if (cnt == 0) {
			bootbox
					.alert("Please Select Checkbox To Edit");
		} else if (cnt > 1) {
			bootbox
					.alert("Please Select One Checkbox To Edit");
		} else {
			$("input[type='checkbox']:checked").each(
					function() {

						val = this.value;
					});
			document.forms[0].action = "editorgchart.action?orgid="+ val;
			document.forms[0].submit();
		

					
	}
	}
	function calldelete() {
		var cnt = $(":checkbox:checked").length;
		var val;
		if (cnt == 0) {
			bootbox
					.alert("Please Select Checkbox To Delete");
		} else if (cnt > 1) {
			bootbox
					.alert("Please Select One Checkbox To Delete");
		} else {
			$("input[type='checkbox']:checked").each(
					function() {

						val = this.value;
					});
			bootbox.confirm("Are you sure, you want to delete this record?",
					function(result) {

						if (result == true) {
							//alert('deleted');
							document.forms[0].action = "deleteorg.action?orgid="+ val;
							document.forms[0].submit();
						}
					});
		

					
	}
		
	}
	function callCreate() {
		document.forms[0].action = "createOrg.action";
		document.forms[0].submit();
	}
	$(document).ready(function() {
		   window.history.pushState("","", "showorgChrart.action");
		 });
		
	</script>
</form>