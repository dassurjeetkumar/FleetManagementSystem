
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
							<h4 class="modal-title"></h4>
						</div>
						<div class="modal-body"></div>
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
						User  <small></small>
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
								<i class="fa fa-globe"></i>View User
							</div>
							<div class="actions">
								<a href="#" class="btn green" id="add"> 
								<i class="fa fa-plus"></i> Create
								</a> 
								
								<a href="#" class="btn blue" id="edit_"> 
								<i class="fa fa-pencil"></i>Edit
								</a> 
								
								<a href="#" class="btn red" id="delete_"> 
								<i class="fa fa-times"></i>Delete
								</a>						
							</div>

							</div>
						</div>

						<div class="portlet-body">
							<b><font color="green"> <s:actionmessage />  </font></b>
							<table class="table table-striped table-bordered table-hover"
								id="userviewTest">
								<thead>
									<tr>
										
										<th style="width1: 8px;"></th>
										<th>Device Type Id</th>
										<th>Device Type Name</th>
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
	
	
	<script>
		
			$('#add').click(	
			function callCreate() {
				document.forms[0].action = "ShowUser.action";
				document.forms[0].submit();
			});
			
			$('#edit_').click(
			function() {
				var cnt = $(":checkbox:checked").length;
				var val;
				if (cnt == 0) {
					alert("Please select checkbox to edit");
				} else if (cnt > 1) {
					alert("Please select one checkbox to edit");
				} else {
					$("input[type='checkbox']:checked").each(
						function() {
						val = this.value;
						});
				
				//document.forms[0].action = 'ModifyUserDetails.action?userdetailsid='+ val;
				//document.forms[0].submit();
					document.getElementById("userdetailsid").value = val;
					document.getElementById("form1").submit();
				}
			});
	         				
			$('#delete_').click(
					function() {
						var cnt = $(":checkbox:checked").length;
						var val;
						if (cnt == 0) {
							alert("Please select checkbox to delete");
						} else if (cnt > 1) {
							alert("Please select one checkbox to delete");
						} else {
							$("input[type='checkbox']:checked").each(
									function() {
										val = this.value;
										type = 'text';
									});
							if (confirm('Are you sure you want to delete this record?')) {
							//document.forms[0].action = 'DeleteUserDetails.action?userdetailsid='+val;
							//document.forms[0].submit();
								document.getElementById("userdetailsiddetails").value = val;
								document.getElementById("form2").submit();
							}

						}				
			});
	
			$(document).ready(function() {
				   window.history.pushState("","", "ShowUserList.action");
				 });

			
	</script>		
</form>
<form name="form1" id="form1" action="ModifyUserDetails.action" method="POST">
	<input type="hidden" name="userdetailsid" id="userdetailsid" value="" />

</form>
<form name="form2" id="form2" action="DeleteUserDetails.action" method="POST">
	<input type="hidden" name="userdetailsiddetails" id="userdetailsiddetails" value="" />

</form>