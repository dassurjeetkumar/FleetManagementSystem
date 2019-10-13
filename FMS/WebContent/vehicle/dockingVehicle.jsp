<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link rel="stylesheet" 	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />

<style type="text/css">
.col-md-9,.col-md-8 {
	width: 30%;
}
.help-block,ul,li {
	list-style: none;
}
.url{
	max-width:500px;
	word-wrap:break-word;
}
.dateTime{
	width: 250px;
	word-wrap:break-word;
}
</style>
</head>
<body onload="onloadfunction()">
<div class="page-content-wrapper">
	<div class="page-content">
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
					Docking Vehicle 
				</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-bars"></i>Docking Vehicle
						</div>
					</div>
					<div class="portlet-body">
				
						<div class="tabbable tabbable-custom tabbable-reversed">
										<div class="form-group " style='padding-left: 200px' >	
										<label class="control-label col-mod-9" style="color: #83aac8;font-weight: bolder"> Vehicle No &nbsp;&nbsp;&nbsp; <s:property value="vehicle.vehicleRegistrationNumber"/></label>
												
										</div>
							<ul class="nav nav-tabs">
								<li id='tab0'><a href="#tab_0" data-toggle="tab">Docking History </a></li>
								<li id='tab1'><a href="#tab_1" data-toggle="tab"> Start Docking  </a></li>
								<li id='tab2'><a href="#tab_2" data-toggle="tab"> Finish Docking </a></li>
							</ul>
							<div class="tab-content">
							
							
	
		<!-- ***********************    START OF LIST VEIW DOCKING HISTORY 		***********************	-->	
										
								<div class="tab-pane" id="tab_0">
									<div class="portlet-body form">
									<form action="" id="" class="form-horizontal form-row-seperated" >
									<div class="form-group">
										<table class="table table-striped table-bordered table-hover scrollable" id="dockingListOfVehicle" style="width:auto;">
											<thead>
												<tr>
													<th>Sr No</th>
													<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Start Time&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </th>
													<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;End Time&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
													<th>Status</th>
													<th>Docking Type</th>
													<th>Docking Kms</th>
													<th>Docking Batch Name</th>
													<th>FIP Change</th>
													<th>EOC Change</th>
													<th>Component Type</th>
												</tr>
											</thead>
											<tbody>
											<s:iterator value="dockingHistoryList" var='a' status="iteration">
												<tr>
													<td>
														<s:property value='%{#iteration.index+1}'/>
													</td>
													<td>
														<s:property value='#a.startDateString'/>
													</td>
													<td>
														<s:property value='#a.endDateString'/>
													</td>
													<td>
														<s:property value='#a.status'/>
													</td>
													<td>
														<s:property value='#a.dockingType.docking_type'/>
													</td>
													<td>
														<s:property value='#a.docking_kms'/>
													</td>
													<td>
														<s:property value='#a.docking_batch_name'/>
													</td>
													<td>
														<s:property value='#a.fip_change'/>
													</td>
													<td>
														<s:property value='#a.eoc_change'/>
													</td>
													<td>
														<s:property value='#a.componenetType.componentName'/>
													</td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
								</div>
								<div class="" style='align:center'>
										<button type="button" class="btn default"  onclick="callCancell()">Cancel</button>
								 </div>
							</form>
						</div>
					</div> 
								
		<!-- *****************    END OF LIST VEIW DOCKING HISTORY 		********************************	-->


					
								
								
		<!-- ******************    START OF START DOCKING PAGE 			*********************************   -->								
								
								
								<div class="tab-pane" id="tab_1">
									<div class="portlet-body form">
									<form action="StartDockingVehicle" id="dockForm" class="form-horizontal form-row-seperated" method="post">
									<s:if test="%{noHistoryFound==1||latestDockingObj.status=='FINISHED'||latestDockingObj.status==''||latestDockingObj.status=='SKIPPED'}">
										<b>
											<font color="green"> <s:actionmessage/></font>
											<font color="red"> <s:actionerror/></font>
										</b>
									<s:token/>
									<input type="hidden" name="vehicle.vehicleId" value="<s:property value='vehicle.vehicleId'/>" /> 
									<input type="hidden" name="dockingHistory.status" value="STARTED" />
									<input type="hidden" name="startDocking" value="1"/>
									<div class="form-group">
										<label class="control-label col-md-3">Start Date/ Time <span class="required"> * </span> </label>
											<div class="col-md-9">
												<div class="input-group date form_datetime" >
													<input type="text" size="16" readonly name="dockingHistory.startDateString" class="form-control" value="<s:property value='dockingHistory.startDateString'/>">
													<span class="input-group-btn">
														<button class="btn default date-set" type="button">
															<i class="fa fa-calendar"></i>
														</button>
													</span>
												</div>
											<span class="help-block" style="color: red"> <s:property
													value="%{fieldErrors.get('dockingHistory.startDateString').get(0)}" /></span>
											</div>
									</div>
									<div class="form-group">
										<label	class="control-label col-md-3">Docking Type <span class="required"> * </span></label>
										<div class="col-md-9">						
											<select name="dockingHistory.dockingType.docking_type_id" id="dockingname" class="select2_category form-control" onchange="getName()">
												<s:iterator value="dockingTypeList" >
													<option  id="dockType<s:property value='dockinTypeId'/>" value="<s:property value='dockinTypeId'/>"><s:property value='dockingType'/></option>
												</s:iterator>
											</select>
											<script>
											var docktype="<s:property value='dockingHistory.dockingType.docking_type_id'/>";
												if(docktype!=""){
												 	 document.getElementById('dockType'+docktype).selected=true;
												}
												
											</script>
											<s:hidden name="dockingName"  id="dockingName"/>
											<span class="help-block" style="color: red"> 
												<s:property	value="%{fieldErrors.get('dockingHistory.dockingType.docking_type').get(0)}" />
											</span>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">Docking Batch name</label>
										<div class="col-md-9">
											<input type="text" class="form-control" name="dockingHistory.docking_batch_name" placeholder="Enter text" value='<s:property value="dockingHistory.docking_batch_name"/>' maxlength="100">
											<span class="help-block" style="color: red"> 
												<s:property	value="%{fieldErrors.get('dockingHistory.docking_batch_name').get(0)}" />
											</span>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">FIP Change</label>
										<div class="col-md-9">
											<select class="select2_category form-control" name="dockingHistory.fip_change" data-placeholder="Choose a Category" tabindex="1">
												<option id="fipYes" value="Yes">Yes</option>
												<option id="fipNo" value="No">No</option>
											</select>
											<script>
												var fipChange = "<s:property value='dockingHistory.fip_change'/>";
												if (fipChange != "") {
													document.getElementById('fip' + fipChange).selected = true;
												}
											</script>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">EOC Change</label>
										<div class="col-md-9"> 
											<select class="select2_category form-control" name="dockingHistory.eoc_change" data-placeholder="Choose a Category" tabindex="1">
												<option id="eoc_Yes" value="Yes">Yes</option>
												<option id="eoc_No" value="No">No</option>
											</select>
											<script>
												var eocChange = "<s:property value='dockingHistory.eoc_change'/>";
												if (eocChange != "") {
													document.getElementById('eoc_'+ eocChange).selected = true;
												}
											</script>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">Componenet Type</label>
										<div class="col-md-9">
											<select name="dockingHistory.componenetType.componentTypeId" class="select2_category form-control">
												<s:iterator value="componenetsTypeList">
													<option id="componenet<s:property value='componentTypeId'/>" value="<s:property value='componentTypeId'/>">
														<s:property value='componentName'/>
													</option>
												</s:iterator>
											</select>
											<script>
												var compType = "<s:property value='dockingHistory.componenetType.componentTypeId'/>";
												if(compType!=""){
													document.getElementById("componenet"+compType).selected=true;
												}
											</script>
										</div>
									</div>
								</s:if>
								<s:else>
									<div class="form-group">
										<label class="control-label col-md-3">
											Docking in progress
										</label>
									</div>
								</s:else>
								<s:if test="%{noHistoryFound==1||latestDockingObj.status=='FINISHED'||latestDockingObj.status==''||latestDockingObj.status=='SKIPPED'}">
									<div class="form-group">
										<div class="form-actions fluid">
											<div class="col-md-offset-3 col-md-9">
												<input type="button"   class="btn blue" value="Save" onclick="saveData()"/> 
												<button type="button" class="btn default" 	onclick="callCancell()">Cancel</button>
											</div>
										</div>
									</div>
								</s:if>
								<s:else>
									<div class="form-group">
										<div class="form-actions fluid">
											<div class="col-md-offset-3 col-md-9">
												<button type="button" class="btn default" 	onclick="callCancell()">Cancel</button>
											</div>
										</div>
									</div>
								</s:else>
								</form>
							</div>
						</div>
						
		<!--  ****************************      END OF START DOCKING PAGE 			***************************** -->
						
						
						
						
						
		<!--  ****************************    START OF FINISH DOCKING PAGE 			***************************** -->
		
						<div class="tab-pane" id="tab_2">
							<div class="portlet-body form">
								<form action="EndDockingVehicle"  class="form-horizontal form-row-seperated" method="post">
									<s:if test="%{latestDockingObj.status=='STARTED'}">
									<input type='hidden' value='${latestDockingObj.docking_id}' name='latestDockingObj.docking_id'/>
									<input type='hidden' value='${latestDockingObj.vehicle.vehicleId}' name='latestDockingObj.vehicle.vehicleId'/>
									<input type='hidden' value='${latestDockingObj.startDateString}' name='latestDockingObj.startDateString'/>
									
									<!-- <input type="text" value=''/> -->
									
									<s:set name="latestDockingObj" value="latestDockingObj" scope="request" />
									<input type='hidden' name="endDocking" value="1"/>
									<div class="form-group">
										<label class="control-label col-md-3">Docking End Date/Time <span class="required"> * </span></label>
											<div class="col-md-9">
												<div class="input-group date form_datetime" >
													<input type="text" size="16" readonly  	name="dockingEndDateString" class="form-control" value="<s:property value='dockingEndDateString'/>">
													<span class="input-group-btn">
														<button class="btn default date-set" type="button">
															<i class="fa fa-calendar"></i>
														</button>
													</span>
												</div>
												<span class="help-block" style="color: red">
										 			<s:property value="%{fieldErrors.get('dockingEndDateString').get(0)}" />
												</span>
										</div>
									</div>
								</s:if>
								<s:else>
									<div class="form-group">
										<label class="control-label col-md-3">
											No Docking progress 
										</label>
									</div>
								</s:else>
								<s:if test="%{latestDockingObj.status=='STARTED'}">
									<div class="form-group">
										<div class="form-actions fluid">
											<div class="col-md-offset-3 col-md-9">
												<input type="submit"   class="btn blue"  value="Save"/> <!-- " -->
												<button type="button" class="btn default" 	onclick="callCancell()">Cancel</button>
											</div>
										</div>
									</div>
								</s:if>
								<s:else>
									<div class="form-group">
										<div class="form-actions fluid">
											<div class="col-md-offset-3 col-md-9">
												<button type="button" class="btn default" 	onclick="callCancell()">Cancel</button>
											</div>
										</div>
									</div>
								</s:else>
								</form>	
							</div>
						</div>
						
		<!-- **************************      END OF FINISH DOCKING PAGE 			***************************  -->	
						
						
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
	</div>
</div>
<!-- </body> -->
<script>
	function callCancell() {

		window.location.href = 'VehicleDetails.action';
	}
	function getName(){
		var dockName=$('#dockingname option:selected').text();
		document.getElementById('dockingName').value=dockName;
	}
	
	function saveData()
	{
		var prevDockType="<s:property value='dockingHisOfVehicle.dockingType.docking_type'/>";
		var currDockType=$('#dockingname option:selected').text();
		
		if(currDockType=="Select"){
			document.forms['dockForm'].submit();
		}else if(prevDockType==""){
			document.forms['dockForm'].submit();
		}else{
			if(prevDockType=="D1"&&currDockType=="D2" || prevDockType=="D2"&&currDockType=="D3" || 
					prevDockType=="D3"&&currDockType=="D4" || prevDockType=="D4"&&currDockType=="D1"){
				document.forms['dockForm'].submit();
			}else{
				bootbox.confirm("Are you sure want to skip docking sequence?",function(result) {
					if (result == true) {
						document.forms['dockForm'].submit();
					}
				});
			}	
		}
	}
 function onloadfunction(){
 
 	var startDockingPage = "<s:property value='startDocking'/>";
 	var endDockingPage = "<s:property value='endDocking'/>";
 	
	$("#tab_0").attr('class', 'tab-pane');
	$("#tab_1").attr('class', 'tab-pane');
	$("#tab_2").attr('class', 'tab-pane');
	
	$("#tab0").attr('class', '');
	$("#tab1").attr('class', '');
	$("#tab2").attr('class', '');
	
	if(startDockingPage>0){
		$("#tab_1").addClass('active');
		$("#tab1").addClass('active');
	}else if(endDockingPage>0){
		$("#tab_2").addClass('active');
		$("#tab2").addClass('active');
	}else{
		$("#tab_0").addClass('active');
		$("#tab0").addClass('active');
	}
} 
</script>