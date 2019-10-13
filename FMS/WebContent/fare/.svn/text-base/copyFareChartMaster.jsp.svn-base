<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//www.google.com/jsapi"></script>

<script>
function getRouteNo(){
	var len= document.getElementById('route_id').options.length;
	
	 if(len<=1 ) {
         $.ajax({
             type: "post",
             url: '<%=request.getContextPath()%>/FareChartajaxAction',
             success: function(result) {
                 document.getElementById('route_id').innerHTML=result;
             }
         })
	 }
 }
function getServiceId(){
	var len= document.getElementById('service_type_id').options.length;

	 if(len<=1 ) {
         $.ajax({
             type: "post",
             url: '<%=request.getContextPath()%>/RateMasterAction!getServiceTypes',
             success: function(result) {
                 document.getElementById('service_type_id').innerHTML=result;
             }
         })
	 }
 }
function getpassengerId(){
	var len= document.getElementById('passenger_type_id').options.length;

	 if(len<=1 ) {
         $.ajax({
             type: "post",
             url: '<%=request.getContextPath()%>/FareChartajaxAction!getPassengerTypes',
             success: function(result) {
                 document.getElementById('passenger_type_id').innerHTML=result;
             }
         })
	 }
 }
 
function getFareMaster(){
	var val= document.getElementById('service_type_id').value;

         $.ajax({
             type: "post",
             url: '<%=request.getContextPath()%>/FareChartAction!getRateMasterVersion?id='+val,
             success: function(result) {
                 document.getElementById('ratemaster').innerHTML=result;
             }
         })
	
 }
 
function getFareChartList(){
	var route= document.getElementById('route_id').value;
	var service= document.getElementById('service_type_id').value;
	var version= document.getElementById('ratemaster').value;
//alert(route+":"+service+":"+version);
        $.ajax({
             type: "post",
             url: '<%=request.getContextPath()%>/FareChartAction!getFareChartNameList?rou='+route+'&ser='+service+'&ver='+version,
             success: function(result) {
                 document.getElementById('farechartlist').innerHTML=result;
             }
         });
	
 }
 
function getFareChartListOnclick(){
	var route= document.getElementById('route_id').value;
	var service= document.getElementById('service_type_id').value;
	var version= document.getElementById('ratemaster').value;

	var len= document.getElementById('farechartlist').options.length;

	 if(len<=1 ) {
        $.ajax({
             type: "post",
             url: '<%=request.getContextPath()%>/FareChartAction!getFareChartNameList?rou='+route+'&ser='+service+'&ver='+version,
             success: function(result) {
                 document.getElementById('farechartlist').innerHTML=result;
             }
         });
	 }
 }
 
function getFareMasterOnClick(){
	var val= document.getElementById('service_type_id').value;
	var len= document.getElementById('ratemaster').options.length;

	 if(len<=1 ) {
         $.ajax({
             type: "post",
             url: '<%=request.getContextPath()%>/FareChartAction!getRateMasterVersion?id='+val,
             success: function(result) {
                 document.getElementById('ratemaster').innerHTML=result;
             }
         })
	 }
 }
 
function getFareMasterBody(){
	var val= document.getElementById('service_type_id').value;
	var len= document.getElementById('ratemaster').options.length;

	 if(len<2 ) {
         $.ajax({
             type: "post",
             url: '<%=request.getContextPath()%>/FareChartAction!getRateMasterVersion?id='+val,
             success: function(result) {
                 document.getElementById('ratemaster').innerHTML=result;
             }
         })
	 }
 }
</script>
<title>Insert title here</title>
</head>
<body>
	<div class="page-content-wrapper">
		<div class="page-content">

			<div class="tab-content">
			<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
					 FARE CHART <small></small> 
					
					</h3>
					<FONT color="green"><s:actionmessage /></FONT>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Copy Fare Chart
							</div>
							<div class="tools">
								
							</div>
						</div>
						<div class="portlet-body form">
							<!-- BEGIN FORM-->
							<font color="red"> <s:actionerror/>  </font>
							<form action="copyFarechart.action" class="form-horizontal" method="post">
							<input type="hidden" name="masterId" value="<s:property value='masterId' />" />
								<br>
									<div class="form-group">
										<label class="col-md-3 control-label" 
										>Route Number:<font color="red">*</font></label>

										<div class="col-md-4">
										
		<s:select list="routeNumberMap" id="route_id" name="fareChartMaster.route.route_id" 
		cssClass="select2_category form-control" headerKey="0" headerValue="Select"></s:select>
																		
										<%--  <select class="form-control" name="fareChartMaster.route_id" 
											 id="route_id" onclick="getRouteNo()">
											
											</select> --%>
											<s:if test="fieldErrors.get('route.route_id').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('route.route_id').get(0)" /></span>
											</s:if>	 
											<!-- <input type="text" class="form-control"
												placeholder="Enter text" id="route_id"
												name="fareChartMaster.route_id"> -->

										</div>
									</div>
							    
								<div class="form-group">
									<label class="col-md-3 control-label">Service Type:<font color="red">*</font></label>
									<div class="col-md-4">
		<s:select list="serviceTypeMap" id="service_type_id" name="fareChartMaster.service_type_id" 
		cssClass="select2_category form-control" onchange="getFareMaster()" headerKey="0" headerValue="Select"></s:select>
									<%-- <select class="form-control" id="service_type_id" name="fareChartMaster.service_type_id" onclick="getServiceId()"
									 onchange="getFareMaster()">
									</select> --%>
										<s:if test="fieldErrors.get('service_type_id').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('service_type_id').get(0)" /></span>
											</s:if>	
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-3 control-label">Rate Master Version:<font color="red">*</font></label>
									<div class="col-md-4">
									<select class="form-control" id="ratemaster" 
									name="fareChartMaster.rate_master_id" 
									 onclick="getFareMasterBody()" onchange="getFareChartList()">
									<option value="<s:property value='fareChartMaster.rate_master_id'/>"><s:property value="rateMasterName"/></option>
												
									</select>
										<s:if test="fieldErrors.get('rate_master_id').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('rate_master_id').get(0)" /></span>
											</s:if>	
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-3 control-label">Fare Chart List:<font color="red">*</font></label>
									<div class="col-md-4">
									<select class="form-control" id="farechartlist" name="farechartlist" onclick="getFareChartListOnclick()">
									  <option value="0">Select</option>	
									</select>
									<s:if test="fieldErrors.get('farechartlist').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('farechartlist').get(0)" /></span>
									</s:if> 	
									</div>
								</div>
								
								<div class="form-group">
								<label class="col-md-3 control-label">Fare Chart Name:<font color="red">*</font></label>
								<div class="col-md-4">
									<input type="text" class="form-control" name="fareChartMaster.farechart_name"
										value="<s:property value="fareChartMaster.farechart_name"/>">
									 <s:if test="fieldErrors.get('farechart_name').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('farechart_name').get(0)" /></span>
									</s:if> 
								</div>
							    </div>
								
							
								<div class="form-group">
									<label class="control-label col-md-3">Effective Start Date:<font color="red">*</font></label>
									<div class="col-md-4">
										<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd/mm/yyyy"
															data-date-viewmode="years" data-date-start-date="+0d">
											<input type="text" size="16" readonly class="form-control"
												id="effect_start_date" name="fareChartMaster.effect_start_date" 
												value="<s:property value='fareChartMaster.effect_start_date'/>"
												>
												<span
												class="input-group-btn" >
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>	
											<script>
												var curr_date=new Date().toJSON().slice(0,10);
		                                        curr_date=curr_date.split("-");
		                                        curr_date=curr_date[2]+"/"+curr_date[1]+"/"+curr_date[0];
		                                       
												var dtval=document.getElementById('effect_start_date').value;	
		                                        
		                                        if(dtval==''){
		                                        $('#effect_start_date').val(curr_date);
		                                        }
											</script>					
										</div>
										<s:if test="fieldErrors.get('effect_start_date').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('effect_start_date').get(0)" /></span>
											</s:if>
										<!-- /input-group -->
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">Effective End Date:</label>
									<div class="col-md-4">
										<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd/mm/yyyy"
															data-date-viewmode="years" data-date-start-date="+0d">
											<input type="text" size="16" readonly class="form-control"
												id="effect_end_date" name="fareChartMaster.effect_end_date"
												value="<s:property value='fareChartMaster.effect_end_date'/>"	
												>
											<span class="input-group-btn">
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
										</div>
										<!-- /input-group -->
									</div>
								</div>
								<%-- 
								<div class="form-group">
								<label class="col-md-3 control-label">Ceiling Fare:</label>
								<div class="col-md-4">
									<input type="text" class="form-control" name="fareChartMaster.ceiling_fare"
										value="<s:property value="fareChartMaster.ceiling_fare"/>">
									<s:if test="fieldErrors.get('ceiling_fare').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('ceiling_fare').get(0)" /></span>
									</s:if>
								</div>
							    </div> --%>
								
								<%-- <div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Operating From:</label>
										<div class="col-md-3">
											<div class="input-group">
												<input type="text"
													class="form-control timepicker timepicker-24"
													id="opfrom_time" name="fareChartMaster.opfrom_time">
												<span class="input-group-btn">
													<button class="btn default" type="button">
														<i class="fa fa-clock-o"></i>
													</button>
												</span>
											</div>
										</div>
									</div>
								</div>
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Operating To:</label>
										<div class="col-md-3">
											<div class="input-group">
												<input type="text"
													class="form-control timepicker timepicker-24"
													id="opto_time" name="fareChartMaster.opto_time"> <span
													class="input-group-btn">
													<button class="btn default" type="button">
														<i class="fa fa-clock-o"></i>
													</button>
												</span>
											</div>
										</div>
									</div>
								</div> --%>

								<%-- <div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Night Service:</label>
										<div class="col-md-3">
<s:select list="nightServiceMap" id="nignt_service" name="fareChartMaster.nignt_service" 
		cssClass="select2_category form-control"></s:select>
											select class="form-control" id="schedule_service"
												name="fareChartMaster.schedule_service">
												<option value="">Select</option>
												<option value="Yes">Yes</option>
												<option value="No">No</option>

											</select>
										</div>
									</div>
									
								</div>
								
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Peak Hours:</label>
										<div class="col-md-3">
<s:select list="peakHoursMap" id="peak_time" name="fareChartMaster.peak_time" 
		cssClass="select2_category form-control" ></s:select>										
											<select class="form-control" 
												name="fareChartMaster.peak_time">
												<option value="">Select</option>
												<option value="Yes">Yes</option>
												<option value="No">No</option>
											</select>
										</div>
									</div>
									
								</div> --%>
								
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn blue" onSubmit="validate();">Save</button>
										<!-- <button type="reset" class="btn default" >Reset</button> -->
										<button type="button" class="btn default" onclick = "javascript:location.href='ShowFareChartAction.action';">Cancel</button>
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