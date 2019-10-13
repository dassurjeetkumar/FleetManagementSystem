
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<link rel="stylesheet"	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script type="text/javascript">
function cancel(){
	document.forms[0].action = 'ShowScheduleDetails.action';
	document.forms[0].submit(); 
}


</script>
<script>
function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}
function getRouteNo() {
	var result = "";
	var id = $('#searchRouteNo').val();
	if (id.length > 0) {
		$.ajax({
			type : 'GET',
			data : 'json',
			url : "FormFour!getRouteNoAjax",
			data : {
					id : id,
			},
			success : function(data) {
				//alert(data);
				data = eval(data);
				result = data;
				$("#searchRouteNo").autocomplete({
					minLength : 0,
					source : result,
					focus : function(event, ui) {
						$("#searchRouteNo").val(ui.item.searchCriteriaName);
						$("#searchRouteId").val(ui.item.alias1);
						return false;
					},
					select : function(event, ui) {
						$("#searchRouteNo").val(ui.item.searchCriteriaName);
						$("#searchRouteId").val(ui.item.alias1);
					return false;
					}
				}).data("ui-autocomplete")._renderItem = function(ul, item) {
					return $("<li id='autocomp'>").append("<a>" + item.searchCriteriaName + "</a>").appendTo(ul);
				};
			},
		});
	}
} 
function validateRouteNo(){
	   var routeNo = $("#searchRouteNo").val();
	   var routeId = $("#searchRouteId").val();
	   $.ajax({
		   type : 'GET',
			data : 'json',
			url : "FormFour!checkValidRoute",
			data : {
				routeNo:routeNo,
				routeId:routeId		
			},
			success : function(data) {
				//alert(data);
				if(data=="false"){
					 alert("Select Proper Route");
				}
			}
	   });
}
</script>
<script>

function checkRouteExit()
{
	
var id = document.getElementById("route").value;
var serviceid=document.getElementById("service1_id").value.split('');	


	$.ajax({
        type: "POST",
        url: "checkRouteExitFare.action",
        data: "routeid="+id+"&serviceid="+serviceid[0],
        
        success: function(response){
        if(response==0)
        		{
        	bootbox.alert("Fare chart does not exist for the selected Route.");
        		}
        	
        	
        	
    
        }
	});	
	}
	
</script>
<script>

function getServiceID()
{
	
	var scheduleid=document.getElementById("schedule").value;	
	
		$.ajax({
	        type: "POST",
	        url: "getServiceidByscheduleid.action",
	        data: "scheduleid="+scheduleid,
	        
	        success: function(response){
	      	
	     
	        	$('#service1_id').val(response);
	        	
	    
	        }
		});	
	
	
}
</script>
<script>

$(document).ready(function()
{

	var schedule=document.getElementById("schedule").value;
	
	if(schedule=='undefine' || schedule=='0')
		{
		
		}
	else{
	
		getServiceID();
		
	}
	
});
</script>
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
							<i class="fa fa-gift"></i>Create Form Four
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
					<br/>
						<!-- BEGIN FORM-->
						
						<form action="SaveFormFour.action" class="form-horizontal" method="post">
						<input type="hidden" name="formtype" value="saveformfour">
						<input type="hidden" name="serviceid" id="service1_id" value=""/>
						<!-- <input type="text" name="h" value=<s:property value="formfour.scheduleNumber.servicetype.service_type_id" />/> -->
							<div class="form-group">
										<label class="col-md-3 control-label">Schedule Number<sup><font color="red">*</font></sup></label>
										<div class="col-md-3">
										<s:select list="scheduleMap" id="schedule" name="formfour.scheduleNumber.schedule_id" cssClass="form-control input-medium select2me" onchange="getServiceID()"></s:select>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Form Four Name<sup><font color="red">*</font></sup></label>
										<div class="col-md-3">
											<s:select list="formFourMap" id="formfour" name="formfour.formFourType.id" cssClass="form-control input-small select2me"></s:select>
											
										</div>
									</div>
									
							<div class="form-group">
								<label class="col-md-3 control-label">Number Of Trips<sup><font color="red">*</font></sup></label>
								<div class="col-md-2">
								<s:textfield name="formfour.NoofTrips"  cssClass="form-control input-xsmall" onkeypress="return isNumber(event);" maxlength="3"></s:textfield>
									 
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-3 control-label">Route Number<sup><font color="red">*</font></sup></label>
								 <div class="col-md-2">
								 	<s:hidden name='searchRouteId' value='%{searchRouteId}' id='searchRouteId' cssClass="form-control"/>
									<s:textfield  placeholder="Type Route No" id='searchRouteNo' name="searchRouteNo" value='%{searchRouteNo}' onkeyup="getRouteNo()" cssClass="form-control"/>
								
										 <%-- <s:select list="routeMap" id="route" name="formfour.formFourRoute.route_id" cssClass="form-control input-small select2me" onchange="checkRouteExit()"></s:select> --%> 
											<s:if test="fieldErrors.get('routeid').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('routeid').get(0)" /></span>
											</s:if>
										</div> 
							</div>

							<div class="form-group">
								<label class="col-md-3 control-label">Start Time<sup><font color="red">*</font></sup></label>
								<div class="col-md-3">
									<div class="input-group">
												<input type="text" class="form-control input-small timepicker timepicker-24" name="formfour.starttimeString" >
												
											</div>

								</div>
							</div>

							<div class="form-group">
								<label class="col-md-3 control-label">Effective Start Date<sup><font color="red">*</font></sup></label>
								<div class="col-md-3">
											<div class="input-group input-small date date-picker" data-date-format="dd-mm-yyyy">
												<input type="text" class="form-control input-small" id="startdate" readonly name="formfour.startDate">
												<span class="input-group-btn">
												<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
												</span>
												<script>
										 var curr_date=new Date().toJSON().slice(0,10);
                                        curr_date=curr_date.split("-");
                                        curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0]; 
                                        $('#startdate').val('<s:property value="scheduleEffStart"/>' );
                                        if($('#startdate').val()=='')
                                        	{
                                        	 $('#startdate').val(curr_date);
                                        	
                                        	}
										</script>
											</div>
											
										</div>
							</div>
							
							
							<div class="form-group">
										<label class="col-md-3 control-label">Effective End Date</label>
										<div class="col-md-3">
											<div class="input-group input-small date date-picker" data-date-format="dd-mm-yyyy">
												<input type="text" class="form-control input-small" readonly name="formfour.endDate">
												<span class="input-group-btn">
												<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
												</span>
											</div>
										</div>
									</div>
							
							<div class="form-group">
										<label class="col-md-3 control-label">Toll Zone</label>
										<div class="col-md-2">
											<select class="form-control input-small select2me" data-placeholder="Select..." name="formfour.tollZone">
												<option value="0">No</option>
												<option value="1">Yes</option>
											</select>
											
										</div>
									</div>
								
								<div class="form-group">
										<label class="col-md-3 control-label">Current Status</label>
										<div class="col-md-2">
									<input type="text" class="form-control" name="formfour.currentStatus" readonly="readonly" value="Partial"/>

								</div>
									</div>
									
									<div class="form-group">
									<label class="col-md-3 control-label">Traffic Order No<font color="red">*</font></sup></label>
									<div class="col-md-4">
<%-- 									<s:textfield cssClass="form-control input-xsmall" id="trafficOrder" name="formfour.traffic_order_no" ></s:textfield> --%>
<!-- 										<input type="text" class="form-control input-small" id="trafficOrder" name="formfour.traffic_order_no"> -->
<%-- 										<s:hidden name="requestType" value="create"></s:hidden> --%>
<s:textfield cssClass="form-control input-xsmall" id="trafficOrder" name="formfour.traffic_order_no" ></s:textfield>
									</div>
								</div>
								
<!-- 									<div class="form-group"> -->
<%-- 								<label class="col-md-3 control-label"> Date :<sup><font color="red">*</font></sup></label> --%>
<!-- 								<div class="col-md-3"> -->
<!-- 											<div class="input-group input-small date date-picker" data-date-format="dd-mm-yyyy"> -->
<!-- 												<input type="text" class="form-control input-small" id="recordDate" readonly name="recordDate"> -->
<%-- 												<span class="input-group-btn"> --%>
<!-- 												<button class="btn default" type="button"><i class="fa fa-calendar"></i></button> -->
<%-- 												</span> --%>
<%-- 												<script> --%>
<!--  										 var curr_date=new Date().toJSON().slice(0,10); -->
<!--                                          curr_date=curr_date.split("-"); -->
<!--                                          curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];  -->
<%--                                          $('#recordDate').val('<s:property value="scheduleEffStart"/>' ); --%>
<!--                                          if($('#recordDate').val()=='') -->
<!--                                          	{ -->
<!--                                          	 $('#recordDate').val(curr_date); -->
                                        	
<!--                                          	} -->
<%-- 										</script> --%>
<!-- 											</div> -->
											
<!-- 										</div> -->
<!-- 							</div> -->

								<div class="form-group">
									<label class="col-md-3 control-label">Remarks</label>
									<div class="col-md-4">
										<input type="text" class="form-control input-small" name="formfour.remarks">
										<s:hidden name="requestType" value="create"></s:hidden>
									</div>
								</div>
								
								
							
								

										<div class="form-actions fluid">
											<div class="col-md-offset-3 col-md-9">
												<button type="submit" class="btn blue">Save</button>
												<button type="button" class="btn default" onclick="cancel();">Cancel</button>
											</div>
										</div>
						</form>
						<!-- END FORM-->
					</div>
				</div>
			</div>
		</div>
	</div>
</div>