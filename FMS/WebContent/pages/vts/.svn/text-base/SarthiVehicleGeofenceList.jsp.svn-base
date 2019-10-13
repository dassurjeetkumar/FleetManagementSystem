
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />

<form id="form123">
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
					SECTOR  <small></small>
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
								<i class="fa fa-globe"></i>View Sector
							</div>
							<div class="actions">
						
								<a   href="javascript:void(0)" class="btn green"   tabindex="1" onclick="callCreate()"> <i
									class="fa fa-plus"></i> Create

								</a> 
								
							
								<a    href="javascript:void(0)" class="btn blue"  tabindex="2"onclick="callEdit()"> <i
									class="fa fa-pencil"></i> Edit
								</a> 
							
						
								<a  href="javascript:void(0)"  class="btn red" tabindex="3" onclick="calldelete()"> <i
									class="fa fa-times"></i> Delete
						       </a>	
                                    <div class="btn-group">
								<a class="btn blue" href="#" data-toggle="dropdown">
									Tools <i class="fa fa-angle-down"></i>
								</a>
							
								
								<div id="sample_4_column_toggler" class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">
							    <label>
										<button type="button" class="btn blue" onclick="callSectorToVehicle();">
											<i class="fa fa-pencil"></i>
											&nbsp;Assign Vehicle
									</button>
								</label>
								<label>
										<button type="button" class="btn blue" onclick="callSectorVehicleRemove();">
											<i class="fa fa-pencil"></i>
											&nbsp;Remove Vehicle
									</button>
								</label>
							</div>
						</div>
						</div>
						</div>
						<div class="portlet-body">
							<input type="hidden" name="requestType" id="requestType"
								value="text" />
							<table class="table table-striped table-bordered table-hover"
								id="sarthiSectorGeofenceView">
								<b><span  id="result" style="color: green;"></span>
								<s:if test="%{insertstaus=='success'}">
									<B><font color="green"><s:actionmessage /></font></B>
								</s:if>
								<s:if test="%{updatestatus=='success'}">
									<B><font color="green"><s:actionmessage /></font></B>
								</s:if>

								<s:if test="%{insertstatus=='fail'}">
									<font color="green">Revenue Sector could not Inserted
										Please Try after Sometime!!</font>
								</s:if>
								<s:if test="%{deletestatus=='fail'}">
									<font color="red">Revenue Sector could not Deleted Please
										Try after Sometime!!</font>
								</s:if>
								<s:if test="%{deletestatus=='success'}">
									<B><font color="green"><s:actionmessage /></font></B>
								</s:if>
								<thead>
									<tr>
										<th style="width1: 8px;"></th>
										<th>Sector Id</th>
									
										<th>Sector Name</th>
									  
									    <th>Geo Fence</th> 
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
			if(isEligibleForOpertion(val)){
			//document.getElementById("chartid").value = val;
			document.forms[0].action = 'editVehicleSector.action?revenueSectorId='+val;
			//document.getElementById("form1").action = "editDevice.action";
			//document.getElementById("form1").submit();
			document.forms[0].submit();
			}else{
				bootbox.alert("Please Select Valid Record");
			}
			/* document.forms[0].action = "editDeviceType.action?chartid="+ val;
			document.forms[0].submit(); */

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
			/* bootbox.alert("All Vehicle Assigned To This Sector Will Be Deleted");
			$("input[type='checkbox']:checked").each(function() {

				val = this.value;
			}); */
			
			if(isEligibleForOpertion(val)){
				
				
				bootbox
						.confirm(
								
								"All Vehicle Assigned To This Sector Will Be Deleted",
								function(result) {

									if (result == true) {
 									$("input[type='checkbox']:checked").each(function() {

				val = this.value;
			});
										bootbox.confirm(
												
												"Are you sure, you want to delete this record?",
												function(result) {

													if (result == true) {
//					 									alert('deleted');
//					 									alert("val"+val);
														document.getElementById("delsectorid").value = val;
														
//					 									document.getElementById("form1").action = "editDevice.action";
														document.getElementById("form2")
																.submit();
														/* document.forms[0].action = "deleteDeviceType.action?deviceid="+ val;
														document.forms[0].submit(); */
													}
												});
									}
								});
				
			
			}else{
				bootbox.alert("Please Select Valid Record");
			}

		}

	}
	function callSectorToVehicle(){
		var val;
		//alert("HII");
		//$(function() {
			var val = [];
			$(':checkbox:checked').each(function(i) {
				val = $(this).val();
			});
		
			var s=check();
			if(s){
				$('#vehicleAction #sectorId').val(val);
				$('#vehicleAction').submit();
			}
		//});
	}
	
	
	function callSectorVehicleRemove(){
		var val;
		
		//$(function() {
			var val = [];
			$(':checkbox:checked').each(function(i) {
				val = $(this).val();
			});
		
			var s=check();
			
			if(s){
				$('#vehicleRemoveAction #sectorId').val(val);
				$('#vehicleRemoveAction').submit();
			}
		//});
	}
	function check() {
		var chckbxCount = $("input:checked").length;
		if (chckbxCount > 0 && chckbxCount > 1) {
			bootbox.alert("Select Only One Sector ...!")
			return false;
		} else if (chckbxCount > 0 && chckbxCount == 1) {
			
			return true;
		} else {
			bootbox.alert("Please Select Vehicle");
			return false;
		}
		
	}
		function callCreate() {
			
			document.forms[0].action = "createVehicleSector.action";
			document.forms[0].submit();
		}
		$(document).ready(function() {
			window.history.pushState("", "", "viewVehicleSector.action");
		});
		function isEligibleForOpertion(id){
			 var isEligible = $('#isRocordEligible'+id).val();
			 if(isEligible == undefined || isEligible=='Y'){
				 return true;
			 }else{
				 return false;
			 }
		}
	</script>
</form>
<form name="form1" id="form1" action="editvehicleSector.action"
	method="POST">
	<input type="hidden" name="chartid" id="chartid" value="" />
</form>
<form name="form2" id="form2" action="deleteVehicleSector.action"
	method="POST">
	<input type="hidden" name="delsectorid" id="delsectorid" />
</form>
<form id='vehicleAction' action="vehicleAllocationToSector" method="post">
	<input type="hidden" id='sectorId' name="sectorId" />
</form>

<form id='vehicleRemoveAction' action="vehicleToSectorList" method="post">
	<input type="hidden" id='sectorId' name="sectorId" />
</form>
