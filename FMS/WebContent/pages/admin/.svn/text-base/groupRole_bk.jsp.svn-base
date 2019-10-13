
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


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
					Group Role Mapping<small></small>
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
							<i class="fa fa-globe"></i>Group Role Mapping
						</div>
						
					</div>

					<div class="portlet-body">
						<div class="portlet-body form">

							<!-- BEGIN FORM-->
							<form action="activegroup.action" class="form-horizontal"
								method="post">
								<div class="form-body">
									<div class="form-group">
									<label class="col-md-3 control-label">Group Name</label>
									<div class="col-md-4" id="errormsg">
										<select class="form-control" name="groupMaster.group_id"
													id="group_id" onclick="getGroupName()" onchange="getRole()">


												</select>
												<s:if test="fieldErrors.get('errormsg').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('errormsg').get(0)" /></span>
											</s:if>
									</div>
								</div>
									
								</div>

							<!-- 	<div class="portlet-body" id="datatable" style="visibility: hidden">


									<input type="hidden" name="requestType" id="requestType"
										value="text" />
									<table class="table table-striped table-bordered table-hover"
										id="roleName">
										<thead>
											<tr>
												<th style="width1: 8px;"></th>
												<th>Role Id</th>
												<th>Role Name</th>
												<th>Status</th>
											</tr>
										</thead>

									</table>
									</div> -->
									
										<div class="portlet box blue" id="floordiv" style="visibility: hidden">
									
									<div class="portlet-body">
										<div class="table-toolbar">
											<div class="btn-group">
												<button id="floorNew" class="btn green">
													Add New Role <i class="fa fa-plus"></i>
												</button>
											</div>

										</div>
										
										<table class="table table-striped table-hover table-bordered"
											id="role_editable">
											
											<thead>
												<tr>
												
													<th>Role Id</th>
													<th>Role Name</th>
														
												</tr>
												
											</thead>
											
										</table>
										<button id="floor_save_tab" class="btn green">
													Cancel
												</button>
																		</div>
								</div>
									
									<div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9">
											<button type="submit" class="btn blue">MapGroup-Role</button>
											<button type="button" class="btn default"
												 id="cancel" onclick="goView()">Cancel</button>
											<h1 class="intro">
												<s:property value="msg" />
											</h1>
										</div>
									</div>
									</form>
								</div>
						</div>
					
						
						<!-- END FORM-->
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<script>
	function getRole(){
		var div = document.getElementById('datatable');
		div.style.visibility = 'visible';
		var id=document.getElementById("group_id").value;
	 $('#roleName').dataTable({
         "aLengthMenu": [
             [20, 50, 100],
             [20, 50, 100] // change per page values here
         ],
         // set the initial value
         "iDisplayLength": 20,
         "bProcessing" : true,
         "bServerSide" : true,
         "sAjaxSource" : "getRole.action?group_id="+id,
         "sPaginationType": "bootstrap",
         "bDestroy": true
         /*"oLanguage": {
             "sLengthMenu": "_MENU_ records",
             "oPaginate": {
                 "sPrevious": "Prev",
                 "sNext": "Next"
             }
         },
        "aoColumnDefs": [
             { 'bSortable': false, 'aTargets': [0] },
             { "bSearchable": false, "aTargets": [ 0 ] }
         ]*/
         
     });
	}

	function getGroupName(){
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 var len= document.getElementById('group_id').options.length;

		 if(len<=1 ) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>	/findGroupIdAction',
				success : function(result) {
					document.getElementById('group_id').innerHTML = result;
				}
			})
		}
	}

	function userGroupMap() {
		var div = document.getElementById('selectusername');
		div.style.visibility = 'visible';

	}
	function goView() {
		$('#cancel').click(function() {

			/* var val = $("input[type='checkbox']").val(); */
			//alert(val);
			document.forms[0].action = 'grouprole.action';
			document.forms[0].submit();

		});
	}
</script>
</form>