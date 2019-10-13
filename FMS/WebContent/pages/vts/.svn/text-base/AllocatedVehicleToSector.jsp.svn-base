
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
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
						<small></small>VEHICLE ALLOCATED TO SECTOR
					</h3>
				
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
                            <%!String sectorName=null;  %>
							<% sectorName=(String)session.getAttribute("sectorName");
													   			  	
					          %>
			<%-- //<s:hidden name="busid" id="id" ></s:hidden> --%>
			<!-- <input type="hidden" name="busid" id="busid" value="22181"/> -->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>Vehicle Allocated To Sector :<%=sectorName %>
							</div>
							<div class="actions">
						
								<a href="#" class="btn red" id="getReport"
									> <i class="fa fa-minus"></i> Delete
								</a> 
								
								<a   href="javascript:void(0)" class="btn blue"   tabindex="1" onclick="goView()"> <i
									class="fa "></i>  Back

								</a> 
								<!-- <a href="#" class="btn blue" id="editPassMaster"
									> <i class="fa fa-pencil"></i>
									Edit
								</a> <a href="#" class="btn red" id="deletePassMaster"
									> <i class="fa fa-times"></i> Delete
								</a> -->
								<div class="btn-group">
								<!-- <div class="btn-group pull-right"> -->
									<!-- <button class="btn green dropdown-toggle" data-toggle="dropdown"><i class="fa fa-gears"></i> Tools <i class="fa fa-angle-down"></i>
									</button>
									<ul class="dropdown-menu pull-right ">
										<li>
											<a href="#">
											Print </a>
										</li>
										<li>
											<a href="#">
											Save as PDF </a>
										</li>
										<li>
											<a href="#">
											Export to Excel </a>
										</li>
									</ul> -->
								<!-- </div> -->
							</div>
							</div>
						</div>

						<div class="portlet-body">
						
                         <B><font color="green"><s:actionmessage /></font></B>
							<table class="table table-striped table-bordered table-hover"
								id="VehicleAllocatedToSector">
								<thead>
									<tr>
										<th style="width: 8px;"><input type="checkbox" id="selecctall" width="8px"/> Select All</th>
									<!-- 	<th>Vehicle Id</th> -->
										<th>Vehicle Name</th>
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
										
										

	<script src="assets/global/plugins/jquery-1.11.0.min.js"
	type="text/javascript"></script>
	<script>
		
	$(document).ready(function() {
		//alert("HII");
	    $('#selecctall').click(function(event) {  //on click
	        if(this.checked) { // check select status
	            $('.checkbox1').each(function() { //loop through each checkbox
	                this.checked = true;  //select all checkboxes with class "checkbox1"              
	            });
	        }else{
	            $('.checkbox1').each(function() { //loop through each checkbox
	                this.checked = false; //deselect all checkboxes with class "checkbox1"                      
	            });        
	        }
	    });
	   
	});
	
	
			

			$('#getReport')
					.click(
							
			function () {
				//alert("HII");
				var vehicleId=[];
				//var VehicleName=[];
				var cnt = $(":checkbox:checked").length;
				var val;
				if (cnt == 0) {
					alert("Please select checkbox to generate report");
				} 
					
				else {
					$("input:checkbox[class=checkbox1]:checked").each(
							function(i) {
								 vehicleId[i] = this.value;; 
								//this.value;
							});
					
					//alert("Value"+VehicleName);
					document.getElementById("id").value = val;
					if(isEligibleForOpertion(vehicleId)){
						bootbox
								.confirm(
										"Are you sure, you want to delete this record?",
										function(result) {

											if (result == true) {
//			 									alert('deleted');
//			 									alert("val"+val);
												document.getElementById("delsectorid").value = vehicleId;
												
//			 									document.getElementById("form1").action = "editDevice.action";
												document.getElementById("form2")
														.submit();
												/* document.forms[0].action = "deleteDeviceType.action?deviceid="+ val;
												document.forms[0].submit(); */
											}
										});
						}else{
							bootbox.alert("Please Select Valid Record");
						}

					//document.forms[0].action = "generateReport";
					//document.getElementById("form1").submit();
				
				//document.forms[1].submit();
	 			}
			});
			function isEligibleForOpertion(id){
				 var isEligible = $('#isRocordEligible'+id).val();
				 if(isEligible == undefined || isEligible=='Y'){
					 return true;
				 }else{
					 return false;
				 }
			}
			function goView() {
				document.forms[0].action = 'viewVehicleSectorList.action';
				document.forms[0].submit();
			}

	</script>
</form>
<form name="form1" id="form1" action="delete"
	method="POST">
	<input type="hidden" name="id" id="id" value="" />
</form>
<form name="form2" id="form2" action="deleteAssignedVehicle"
	method="POST">
	<input type="hidden" name="delsectorid" id="delsectorid" />
</form>