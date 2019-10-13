  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  
 <form>
<div class="page-content-wrapper">
		<div class="page-content">
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div class="modal fade" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">Modal title</h4>
						</div>
						<div class="modal-body">
							 Widget settings form goes here
						</div>
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
					Fare Chart <small></small>
					</h3>
					
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			
			<%-- //<s:hidden name="busid" id="id" ></s:hidden> --%>
			 <!-- <input type="hidden" name="busid" id="busid" value="22181"/> -->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>Fare Chart
							</div>
							<div class="actions">
							<a href="#" class="btn blue"  id="createChart" onclick="callCreate()" >
								<i class="fa fa-plus"></i> Create </a>
							
								<a href="#" class="btn blue"  id="editGroupDetails" onclick="editGroup()">
								<i class="fa fa-pencil"></i> Edit </a>
							    <a href="#" class="btn blue"  id="userGroupMappingDetails" onclick="userGroupMap()">
								<i class="fa fa-pencil"></i>UserGroupMapping </a>
							
								
							</div>
						</div>
						
						<div class="portlet-body">
						
						
						<input type="hidden" name="requestType" id="requestType" value="text"/>
							<table class="table table-striped table-bordered table-hover" id="addUserGroup">
							<thead>
							<tr>
								<th style="width1:8px;">
									
								</th>
								<th>
									 User_Group Id
								</th>
						 		<th>
									 Group Id
								</th>
								
								<th>
									 User id
								</th>
								
								
								
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
	</div>
	<script>
	function editGroup(){
	$('#editGroupDetails')
	.click(
			function() {
				var cnt = $(":checkbox:checked").length;
				var val;
				if (cnt == 0) {
					bootbox.alert("Please Select Checkbox To Edit");
				} else if (cnt > 1) {
					bootbox
							.alert("Please Select One Checkbox To Edit");
				} else {
					$("input[type='checkbox']:checked").each(
							function() {

								val = this.value;
							});
					/* var val = $("input[type='checkbox']").val(); */
					alert(val);
					document.forms[0].action = 'editGroupPage.action?group_id='+ val;
					document.forms[0].submit();

				}

			});
	}
	
	 function callCreate()
	 {
		 document.forms[0].action = "createGroup.action";
		 document.forms[0].submit();
	 }
	</script>
	</form>