
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script type="text/javascript">
function cancel(){
	var value = document.getElementById("schid").value;
	document.forms[0].action = 'FormFourView.action?sch='+value;
	 document.forms[0].submit(); 
}

$(document).ready(function(){
$('#starttime').timepicker({
    autoclose: true,
    minuteStep: 5,
    showSeconds: false,
    showInputs: true,
    showMeridian: false,
    defaultTime : false
});
});
function getRouteNo(event) {
	
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
					 return false;
				}
			}
	   });
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
    
    
</script>
<title>Insert title here</title>
</head>
<body>
<div class="page-content-wrapper">
	<div class="page-content">
		<div class="tab-content">
			<div class="tab-pane active" id="tab_0">
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i>Edit Form Four
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
					<br/>
						<!-- BEGIN FORM-->
						<form action="SaveEditFormFour.action" class="form-horizontal" method="post">

						<input type="hidden" id="formfourid" name="formfourid" value='<s:property value="formfourid"/>'/>
						<input type="hidden" id="schid" name="schid" value='<s:property value="schid"/>'/>
						<input type="hidden" name="serviceid" id="service1_id" value="<s:property value="serviceid"/>"/>
						<input type="hidden" name="formtype" value="">
						<s:hidden name="formfourtype"></s:hidden>
							<div class="form-group">
										<label class="col-md-3 control-label">Schedule Number<sup><font color="red">*</font></sup></label>
										<div class="col-md-3">
										<s:select list="scheduleMap" id="Schedule" name="formfour.scheduleNumber.schedule_id" cssClass="form-control input-medium select2me"></s:select>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Form Four Name<sup><font color="red">*</font></sup></label>
										<div class="col-md-3">
											<s:select list="formFourMap" id="Formfour" name="formfour.formFourType.id" cssClass="form-control input-small select2me"></s:select>
											
										</div>
									</div>
									
							<div class="form-group">
								<label class="col-md-3 control-label">Number Of Trips<sup><font color="red">*</font></sup></label>
								<div class="col-md-2">
								<s:textfield cssClass="form-control input-xsmall" name="formfour.NoofTrips" onkeypress="return isNumber(event);" maxlength="3"></s:textfield>
									<!-- <input type="text" class="form-control input-xsmall" name="formfour.NoofTrips" value="8"> -->

								</div>
							</div>

							<div class="form-group">
								<label class="col-md-3 control-label">Route Number<sup><font color="red">*</font></sup></label>
								 <div class="col-md-2">
										<%--  <s:select list="routeMap" id="Route" name="formfour.formFourRoute.route_id" cssClass="form-control input-small select2me"></s:select> 
										 --%>	
									<s:hidden  name='searchRouteId' value='%{searchRouteId}' id='searchRouteId' cssClass="form-control"/>
									<s:textfield  placeholder="Type Route No" id='searchRouteNo' name="searchRouteNo" value='%{searchRouteNo}' onkeyup="getRouteNo(event)" cssClass="form-control" />
								</div> 
							</div>

							<div class="form-group">
								<label class="col-md-3 control-label">Start Time<sup><font color="red">*</font></sup></label>
								<div class="col-md-3">
									<div class="input-group">
									<s:textfield cssClass="form-control input-small" id="starttime" name="formfour.starttimeString"></s:textfield>
								</div>

								</div>
							</div>

							<div class="form-group">
								<label class="col-md-3 control-label">Effective Start Date<sup><font color="red">*</font></sup></label>
								<div class="col-md-3">
											<div class="input-group input-small date date-picker" data-date-format="dd-mm-yyyy">
											<s:textfield cssClass="form-control input-small" readonly="readonly" name="formfour.startDate"></s:textfield>
												<span class="input-group-btn">
												<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
												</span>
											</div>
											
										</div>
							</div>
							
							
							<div class="form-group">
										<label class="col-md-3 control-label">Effective End Date</label>
										<div class="col-md-3">
											<div class="input-group input-small date date-picker" data-date-format="dd-mm-yyyy">
											<s:textfield cssClass="form-control input-small" readonly="readonly"  name="formfour.endDate"></s:textfield>
												<span class="input-group-btn">
												<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
												</span>
											</div>
										</div>
									</div>
							
							<div class="form-group">
										<label class="col-md-3 control-label">Toll Zone</label>
										<div class="col-md-2">
										
										<s:select list="#{'0':'No','1':'Yes'}" cssClass="form-control input-small select2me" name="formfour.tollZone"></s:select>
											<%-- <select class="form-control input-small select2me" data-placeholder="Select..." name="formfour.tollZone">
												<option value="1">Yes</option>
												<option value="0">No</option>
											</select> --%>
											
										</div>
									</div>
								
								<div class="form-group">
										<label class="col-md-3 control-label">Current Status</label>
										<div class="col-md-2">
										<s:textfield cssClass="form-control" name="formfour.currentStatus" readonly="true"></s:textfield>
									<!-- <input type="text" class="form-control" name="formfour.currentStatus" readonly="readonly"/> -->

								</div>
									</div>

									<div class="form-group">
									<label class="col-md-3 control-label">Traffic Order No<font color="red">*</font></sup></label>
									<div class="col-md-4">
										<%-- <input type="text" class="form-control input-small" name="formfour.traffic_order_no">
										<s:hidden name="requestType" value="create"></s:hidden> --%>
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
                                        	
<!--                                         	} -->
<%-- 										</script> --%>
<!-- 											</div> -->
											
<!-- 										</div> -->
<!-- 							</div> -->


								<div class="form-group">
									<label class="col-md-3 control-label">Remarks</label>
									<div class="col-md-4">
									<s:textfield name="formfour.remarks" cssClass="form-control input-small"></s:textfield>
									<s:hidden name="requestType" value="update"></s:hidden>
									<s:hidden name="startDate"></s:hidden>
									<s:hidden name="endDate"></s:hidden>
									</div>
								</div>

								

										<div class="form-actions fluid">
											<div class="col-md-offset-3 col-md-9">
												<button type="submit" class="btn blue">Edit Trips</button>
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
</body>
</html>