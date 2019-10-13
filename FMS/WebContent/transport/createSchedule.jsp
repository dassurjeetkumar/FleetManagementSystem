<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script src="assets/global/plugins/jquery-1.11.0.min.js" type="text/javascript"></script>
<script type="text/javascript">
function isEmpty(str) {
	  str = trim(str);
	  return ((str == null) || (str.length == 0));
	}

function cancel(){
	document.forms[0].action = 'ShowScheduleDetails.action';
	 document.forms[0].submit(); 
}
</script>
<script src="assets/admin/pages/scripts/trips.js"
	type="text/javascript"></script>
<script>
function getBrandType(){
	var id = document.getElementById("servicetype").value;
	
	$.ajax({
        type: "POST",
        url: "getBrandType.action",
        data: "serviceId="+id,
        success: function(response){
        	
        	var arr = response.split(',');
        	 var i=0;
        	var select = $('#brandtype');
        	select.empty();
        	$('#brandtype').append('<option value="0">Select</option>'); 
        	for (i = 0; i < arr.length-1; i++) {
        		var splitresult = arr[i].split('@');
        		
        		$('#brandtype').append('<option value="'+splitresult[0]+'" class="tooltips">'+splitresult[1]+'</option>');
        		
        		
        	}
        	
    
        }
	});
}
</script>
<script>
$(document).ready(function(){

var id = document.getElementById("servicetype").value;
	
	$.ajax({
        type: "POST",
        url: "getBrandType.action",
        data: "serviceId="+id,
        success: function(response){
        	
        	var arr = response.split(',');
        	 var i=0;
        	var select = $('#brandtype');
        	select.empty();
        	$('#brandtype').append('<option value="0">Select</option>'); 
        	for (i = 0; i < arr.length-1; i++) {
        		var splitresult = arr[i].split('@');
        		
        		$('#brandtype').append('<option value="'+splitresult[0]+'" class="tooltips">'+splitresult[1]+'</option>');
        		
        		
        	}
        	
        	var brand = '<s:property value="schedule.brand.brand_type_id" />';
        	var trunk = '<s:property value="schedule.isTrunkSchedule" />';
        	if(!isEmpty(brand)){
        	$('#brandtype').val(brand);
        	}
        }
	});
	
	
	
	//$('#brandtype').val(brand);
	//document.getElementById("brandtype").value=brand;
});
</script>
</head>
<body>

<div class="page-content-wrapper">
	<div class="page-content">
	<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
					 SCHEDULE
					</h3>
					
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
		<div class="tab-content">
			<div class="tab-pane active" id="tab_0">
				<div class="portlet box grey-cascade">
				
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i>Create Schedule
						</div>
						
					</div>
					
					<div class="portlet-body form">
					<s:if test="hasActionErrors()">
   <div class="alert alert-danger">
			<button class="close" data-close="alert"></button>
			<span>
			<s:actionerror/> </span>
		</div>
      
   
</s:if>
					<s:if test="hasActionMessages()">
   <div class="alert alert-success alert-dismissable">
								<strong> <s:actionmessage/> </strong>
							</div>
      
   </s:if>

						<!-- BEGIN FORM-->
						<form action="SaveSchedule.action" class="form-horizontal" method="post">
							<input type="hidden" name="bustops.id" id="busid"
								value="<s:property value="bustops.id"/>" />
								<input type="hidden" name="requestType" id="requestType" value="text"/>
								<input type="hidden" name="edittargetamt" value="bcd"/>
								<br/>
								
						
							
								
							<div class="form-group">
								<label class="col-md-3 control-label">Schedule Number<sup><font color="red">*</font></sup></label>
								<div class="col-md-4">
								<s:textfield theme="simple" cssClass="form-control input-medium" name="schedule.scheduleNumber" id="scheduleNumber" maxlength="59"></s:textfield>
								</div>
								
							</div>
							
							<div class="form-group">
										<label class="col-md-3 control-label">Schedule Type<sup><font color="red">*</font></sup></label>
										<div class="col-md-3">
										<s:select list="scheduleTypeMap" id="scheduletype" name="schedule.scheduletype.schedule_type_id" cssClass="form-control input-medium"></s:select>
											</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Service Type<sup><font color="red">*</font></sup></label>
										<div class="col-md-3">
										
										<s:select list="serviceTypeMap" id="servicetype" name="schedule.servicetype.service_type_id" cssClass="form-control input-medium" onchange="getBrandType();"></s:select>
											</div>
									</div>
							
							<div class="form-group">
										<label class="col-md-3 control-label">Brand Type<sup><font color="red">*</font></sup></label>
										<div class="col-md-3">
										<select id="brandtype" class="form-control input-medium" name="schedule.brand.brand_type_id"  title="">
										<option value="0">Select</option>
										</select>
										<%-- <s:select list="brandTypeMap" id="brandtype" name="schedule.brand.brand_type_id" cssClass="select2_category form-control input-medium"></s:select> --%>
											</div>
									</div>

							

							<div class="form-group">
										<label class="col-md-3 control-label">Depot Code<sup><font color="red">*</font></sup></label>
										<div class="col-md-3">
										<s:select list="depotMap" id="depot" name="schedule.depot.org_chart_id" cssClass="form-control input-medium"></s:select>
											</div>
									</div>
							
							<%-- <div class="form-group">
										<label class="col-md-3 control-label">Route Number<sup><font color="red">*</font></sup></label>
										<div class="col-md-3">
										<s:select list="routeMap" id="route1" name="schedule.routeno.route_id" cssClass="select2_category form-control"></s:select>
											</div>
									</div> --%>
									
							<s:hidden name='schedule.routeId' value='0'></s:hidden>		
									
							<!-- <div class="form-body">
										<div class="form-group">
											<label class="col-md-3 control-label">Customer Name</label>
											<div class="col-md-4">
												<input type="text" class="form-control">

											</div>
										</div>
							</div>
 -->							
							<div class="form-group">
										<label class="col-md-3 control-label">Trunk Schedule</label>
										<div class="col-md-3">
										<s:select list="#{'Y':'Regular', 'N':'Feeder'}" cssClass="form-control input-small" name="schedule.isTrunkSchedule"></s:select>
											</div>
			
									</div>
									
										
							<div class="form-group">
								<label class="col-md-3 control-label">Target Amount<sup><font color="red"></font></sup></label>
								<div class="col-md-4">
								<s:textfield theme="simple" cssClass="form-control input-medium" name="targetamount" id="targetamount" maxlength="8"></s:textfield>
								<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('targetamount').get(0)}" />
										</span>
								</div>
								
							</div>
								
								 <div class="form-group">
										<label class="col-md-3 control-label">Effective Start Date<sup><font color="red">*</font></sup></label>
										<div class="col-md-3">
											<div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" >
												<input type="text" class="form-control" readonly name="schedule.startdate" id="startdate">
												<span class="input-group-btn">
												<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
												</span>
												<script>
										var curr_date=new Date().toJSON().slice(0,10);
                                        curr_date=curr_date.split("-");
                                        curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
                                        $('#startdate').val(curr_date);
										</script>
											</div>
											
										</div>
									</div>

								<div class="form-group">
										<label class="col-md-3 control-label">Effective End Date</label>
										<div class="col-md-3">
											<div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" >
												<input type="text" class="form-control" readonly name="schedule.endDate" id="enddate">
												<span class="input-group-btn">
												<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
												</span>
											</div>
											
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">Status<sup><font color="red">*</font></sup></label>
										<div class="col-md-4">
											<input type="text" class="form-control input-small" name="schedule.status"
												value='NEW' readonly="readonly">

										</div>
									</div>
									
						 <div class="form-group">
								<label class="col-md-3 control-label">Conductor</label>
								<div class="col-md-2">
									<input type="text" class="form-control" name="schedule.conductor" id="conductor"/></div>
								
							</div> 
							 <div class="form-group">
								<label class="col-md-3 control-label">Driver</label>
								<div class="col-md-2">
									<input type="text" class="form-control" name="schedule.driver" id="driver"/></div>
								
							</div> 
								
									<div class="form-group">
										<label class="col-md-3 control-label">Remarks</label>
										<div class="col-md-4">
										<s:textfield cssClass="form-control input-medium" name="schedule.remarks"></s:textfield>
										</div>
									</div>	
									
									
										<div class="form-actions fluid">
											<div class="col-md-offset-3 col-md-9">
												<button type="submit" class="btn blue">Save</button>
												<button type="button" class="btn default" onclick="cancel();">Cancel</button>
											</div>
										</div>
										<s:token/>
						</form>
						<!-- END FORM-->
						

					</div>
				</div>
			</div>
		</div>
	</div>
</div>

</body>
</html>