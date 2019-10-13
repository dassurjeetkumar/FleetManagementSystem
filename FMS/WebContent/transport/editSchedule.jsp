<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script src="assets/global/plugins/jquery-1.11.0.min.js" type="text/javascript"></script>
<script type="text/javascript">
function cancel(){
	document.forms[0].action = 'ShowScheduleDetails.action';
	 document.forms[0].submit(); 
}
</script>
<script type="text/javascript">

function process(date){
	   var parts = date.split("-");
	   return new Date(parts[2], parts[1] - 1, parts[0]);
	}

function save(){
	//Receive the value of driver and conductor, if blank then provide 0 as the value
	var conductorValue = $("#conductor").val();
	conductorValue = conductorValue.trim();
	var driverValue = $("#driver").val();
	driverValue = driverValue.trim();
	
	if(conductorValue == undefined || conductorValue == null || conductorValue == ""){
		//alert("conductorValue : " + conductorValue);
		$("#conductor").val("0");
	}
	
	if(driverValue == undefined || driverValue == null || driverValue == ""){
		//alert("driverValue : " + driverValue);
		$("#driver").val("0");
	}
	var d1 = document.getElementById("startdate").value;
	var d2 = new Date();
	
	if(process(d1).setHours(0,0,0,0) < d2.setHours(0,0,0,0) ){
		bootbox.alert ("Effective Start date should be greater than or Equal to Today's Date");
	}
	else{
		var d3 = document.getElementById("effstartdate").value;
		if(process(d3).setHours(0,0,0,0) < d2.setHours(0,0,0,0)){
				/* bootbox.alert("Please create new form four for Active Schedule", function() {
	    		 document.f1.action = 'UpdateSchedule.action';
	    		 document.f1.submit(); 
               });   */
               
               //rajesh 
               //added bootbox.dialog for yes and No functinality instead of cancle and ok
               //################################################# new code start//23/7/2015 
               bootbox.dialog({
            	   message: "Do you want to copy form four from existing schedule?",
            	   title: "",
            	   buttons: {
            	     success: {
            	       label: "YES",
            	       className: "",
            	       callback: function() {

							document.f1.action = 'UpdateSchedule.action?ff=y';
				    		 document.f1.submit();
            	       }
            	     },
            	     danger: {
            	       label: "NO",
            	       className: "",
            	       callback: function() {
            	    	   document.f1.action = 'UpdateSchedule.action?ff=n';
				    		 document.f1.submit();
            	       }
            	     }/* ,
            	     main: {
            	       label: "Click ME!",
            	       className: "btn-primary",
            	       callback: function() {
            	         Example.show("Primary button");
            	       }
            	     } */
            	   }
            	 });
               //#############################################new code start//23/7/2015 
//------------------------------------------------------------old code start
/* // 			bootbox.confirm("Do you want to copy form four from existing schedule?",
// 					function(result) {

// 						if (result == true) {
							
// 							document.f1.action = 'UpdateSchedule.action?ff=y';
// 				    		 document.f1.submit();
// 						}
// 						else{
							
// 							document.f1.action = 'UpdateSchedule.action?ff=n';
// 				    		 document.f1.submit();
// 						}
// 			}); */
//.............................................................old code start end
	
               
		}
		else{
			document.f1.action = 'UpdateSchedule.action?ff=n';
   		 	document.f1.submit();
		}
		
	}
        
}
</script>

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
	//hide readonly property of text field
	//$("#targetamount").prop("readonly",false);
	
	/* alert("brand ------> ");
	getBrandType();
	/* var brand = '<s:property value="schedule.brand.brand_type_id" />';

	var trunk = '<s:property value="schedule.isTrunkSchedule" />';
	setTimeout(function() {
		$('#brandtype').val(brand);
		    }, 1000); */
	//$('#brandtype').val(brand);
	//document.getElementById("brandtype").value=brand; */
	
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
        		var brand = '<s:property value="schedule.brand.brand_type_id" />';
        		$('#brandtype').val(brand);
        	}
        	
    
        }
	});
	
	
});
</script>
</head>
<body>
<input type="text" id='a'>

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
							<i class="fa fa-gift"></i>Edit Schedule
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
						<form action="UpdateSchedule.action" class="form-horizontal" method="post" name="f1">
							<s:hidden name="schedule.schedule_id"></s:hidden>
							<s:hidden name="requestType" value="update"></s:hidden>
							<s:hidden name="schedulenumber"></s:hidden>
							<s:hidden name="startdate" id="effstartdate"></s:hidden>
								<br/>
								<input type="hidden" name="edittargetamt" value="abc"/>
							
							
								
							<div class="form-group">
								<label class="col-md-3 control-label">Schedule Number<sup><font color="red">*</font></sup></label>
								<div class="col-md-4">
								<s:textfield cssClass="form-control input-medium" name="schedule.scheduleNumber" id="scheduleNumber" theme="simple" readonly="true"></s:textfield>
								</div>
							</div>
							
							<div class="form-group">
										<label class="col-md-3 control-label">Schedule Type<sup><font color="red">*</font></sup></label>
										<div class="col-md-3">
										<s:select list="scheduleTypeMap" id="scheduletype" name="schedule.scheduletype.schedule_type_id" cssClass="select2_category form-control"></s:select> 
										<s:hidden id="scheduletypeId" name="scheduletypeId"></s:hidden>
										</div>
							</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Service Type<sup><font color="red">*</font></sup></label>
										<div class="col-md-3">
										<s:select list="serviceTypeMap" id="servicetype" name="schedule.servicetype.service_type_id" cssClass="select2_category form-control" onchange="getBrandType();"></s:select>
											</div>
									</div>
							
							<%-- <div class="form-group">
										<label class="col-md-3 control-label">Brand Type<sup><font color="red">*</font></sup></label>
										<div class="col-md-3">
										<s:select list="brandTypeMap" id="brandtype" name="schedule.brand.brand_type_id" cssClass="select2_category form-control"></s:select>
											</div>
									</div> --%>

									<div class="form-group">
										<label class="col-md-3 control-label">Brand Type<sup><font color="red">*</font></sup></label>
										<div class="col-md-3">
										<select id="brandtype" class="form-control input-medium" name="schedule.brand.brand_type_id" tabindex="-1" title="" >
										<option value="0">Select</option>
										</select>
										<%-- <s:select list="brandTypeMap" id="brandtype" name="schedule.brand.brand_type_id" cssClass="select2_category form-control input-medium"></s:select> --%>
											</div>
									</div>
																

							<div class="form-group">
										<label class="col-md-3 control-label">Depot Code<sup><font color="red">*</font></sup></label>
										<div class="col-md-3">
										<s:select list="depotMap" id="depot" name="schedule.depot.org_chart_id" cssClass="select2_category form-control"></s:select>
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
										<s:select list="#{'Y':'Regular', 'N':'Feeder'}" name="schedule.isTrunkSchedule" cssClass="form-control"></s:select>
											<%-- <select class="form-control" name="schedule.isTrunkSchedule">
												<option value="Y">Regular</option>
												<option value="N">Feeder</option>
												
											</select> --%>
										</div>
									</div>
									
<!-- 									<div class="form-group"> -->
<%-- 								<label class="col-md-3 control-label">Target Amount<sup><font color="red"></font></sup></label> --%>
<!-- 								<div class="col-md-4"> -->
<%-- 								<s:textfield theme="simple" cssClass="form-control input-medium" name="schedule.targetamount" id="targetamount" maxlength="59" ></s:textfield> --%>
<!-- 								</div> -->
								
<!-- 							</div> -->
								
								 <div class="form-group">
										<label class="col-md-3 control-label">Effective Start Date<sup><font color="red">*</font></sup></label>
										<div class="col-md-3">
											<div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy">
											
												<s:textfield cssClass="form-control" readonly="readonly" name="schedule.startdate" id="startdate"></s:textfield>
												<span class="input-group-btn">
												<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
												</span>
											</div>
											
										</div>
									</div>

								<div class="form-group">
										<label class="col-md-3 control-label">Effective End Date</label>
										<div class="col-md-3">
											<div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy">
											<s:textfield cssClass="form-control" readonly="readonly" name="schedule.endDate" id="enddate"></s:textfield>
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
									<!-- 
									<div class="form-group">
								<label class="col-md-3 control-label">Schedule Group Name</label>
								<div class="col-md-4">
									<input type="text" class="form-control" name="" id="scheduleGroupName"/></div>
								
							</div> -->
							
							 <div class="form-group">
								<label class="col-md-3 control-label">Conductor</label>
								<div class="col-md-4">
								<s:textfield cssClass="form-control input-medium" name="schedule.conductor" id="conductor" theme="simple" ></s:textfield>
									</div>
								
							</div> 
							 <div class="form-group">
								<label class="col-md-3 control-label">Driver</label>
								<div class="col-md-4">
								<s:textfield cssClass="form-control input-medium" name="schedule.driver" id="driver" theme="simple" ></s:textfield>
									
									</div>
								
							</div> 
								
								
									<div class="form-group">
										<label class="col-md-3 control-label">Remarks</label>
										<div class="col-md-4">
										<s:textfield name="schedule.remarks" cssClass="form-control input-small" theme="simple" id="remarks"></s:textfield>

										</div>
									</div>	
									
									
										<div class="form-actions fluid">
											<div class="col-md-offset-3 col-md-9">
												<button type="button" class="btn blue" onclick="save();">Save</button>
												<button type="button" class="btn default" onclick="cancel();">Cancel</button>
											</div>
										</div>
										<s:hidden name="schedule.currentStatus"></s:hidden>
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