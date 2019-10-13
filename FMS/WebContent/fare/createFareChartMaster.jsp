<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<!DOCTYPE html>
<html>
<head>


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
 
 /* function isInteger(){
	 var val=document.getElementById('ceilVal').value;

	 if(isNaN(val)){
		 bootbox.alert('Ceiling fare '+val+' is invalid');
		 document.getElementById('ceilVal').value="";
	 }
 } */
 
 function resetform(){
	//location.reload();
	//02/09/14
	document.forms[0].action = "createRateChart.action";
	document.forms[0].submit();
	// document.getElementById("route_id").value="0";
 }
 
 function isInteger(n) {
		if(parseFloat(n) != parseInt(n, 10)){
		   return true;
		}else{
			return false;
		}
 } 
 
 function checkInt(){
		
		var val=document.getElementById('ceilVal').value;
       if(val!=''){
		if(isNaN(val) || isInteger(val) || val<0){
			if(val<0){
				document.getElementById('ceilFareVal').innerHTML='Invalid Ceiling Fare value';	
			}else{
			document.getElementById('ceilFareVal').innerHTML='Ceiling Fare should be in Integer';
			}
		   return false;
		}else{
			return true;
		}
       }else{
			return true;
		}

		
	}
 
 $(document).ready(function() {

		window.history.pushState("", "", "createRateChart.action");
		
	});
 
 
 
 function getRoute(val){
		var result = "";
		var availableTags=[];	
		$('#startdate').hide();
		$('#endate').hide();
		$.ajax({
		    type : 'GET',
		    data :'json',
		    url  :  "<s:url action='getRouteLists'/>",
		    data :{
		    	routename: val,
		    },
		    success: function(data){
		        data =eval(data);
		        result=data;
		        $( "#project" ).autocomplete({
			        	minLength: 0,
			        	source: result,
			        	focus: function( event, ui ) {
			        	$( "#project" ).val( ui.item.route_number  );
			        	return false;
		        	},
		        	select: function( event, ui ) {
			        	$( "#project" ).val( ui.item.route_number );
			        	$( "#project-id" ).val( ui.item.route_name );
			        	return false;
		        	}
		        }).data( "ui-autocomplete" )._renderItem = function( ul, item ) {
		        	return $( "<li>" )
		        	.append( "<a>" + item.route_number + "</a>" )
		        	.appendTo( ul );
		       };
		    },
		    select: function (event, ui) {

		        alert("selected!");
		    },
		    change: function (event, ui) {

		        alert("changed!");
		    },
		    error : function(xhr, errmsg) {
		    	alert("No values found..!!"+errmsg);
		    }
		});
	}
 
 
 
 
</script>
<title>Insert title here</title>
</head>
<body>
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ShowFareChartAction.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
%>

	<div class="page-content-wrapper">
		<div class="page-content">
<%-- 	<%if (access.equalsIgnoreCase("Y")){%> --%>
	<%if(create.equalsIgnoreCase("Y")){ %>
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
					<div class="row">
				
			</div>
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Create Fare Chart
							</div>
							<div class="tools">
								
							</div>
						</div>
						<div class="portlet-body form">
							<!-- BEGIN FORM-->
							<FONT color="red"><s:actionerror/></FONT>
							<form name="myform" action="createFarechart.action" onsubmit="return checkInt()" class="form-horizontal" method="post" >
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
									<select class="form-control" id="ratemaster" name="fareChartMaster.rate_master_id" 
									onclick="getFareMasterOnClick()">
									<option value="<s:property value='fareChartMaster.rate_master_id'/>"><s:property value="rateMasterName"/></option>
										
									</select>
										<s:if test="fieldErrors.get('rate_master_id').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('rate_master_id').get(0)" /></span>
											</s:if>	
									</div>
								</div>
								
								
								
							 	<div class="form-group">
									<label class="col-md-3 control-label">Passenger Type:</label>
									<div class="col-md-4">
		<%-- <s:select list="passengerTypeMap" id="passenger_type_id" name="fareChartMaster.passenger_type_id" 
		cssClass="form-control" value="2" ></s:select> --%>
									  <select class="form-control" id="passenger_type_id"
											name="fareChartMaster.passenger_type_id">
												<option value="2">Adult</option>
												<!-- <option value="1">Child</option>
												<option value="3">Senior Citizen</option> -->
											</select> 
										<s:if test="fieldErrors.get('passenger_type_id').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('passenger_type_id').get(0)" /></span>
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
												id="effect_start_date"
												name="fareChartMaster.effect_start_date"
												value="<s:property value='fareChartMaster.effect_start_date' />"
												> <span
												class="input-group-btn" >
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
											<script>
												var curr_date=new Date().toJSON().slice(0,10);
		                                        curr_date=curr_date.split("-");
		                                        curr_date=curr_date[2]+"/"+curr_date[1]+"/"+curr_date[0];
		                                        $('#effect_start_date').val(curr_date);
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
												value="<s:property value='fareChartMaster.effect_end_date' />"
												>
											<span class="input-group-btn">
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
										</div>
										<!-- /input-group -->
										<s:if test="fieldErrors.get('effect_end_date').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('effect_end_date').get(0)" /></span>
											</s:if>
									</div>
								</div>
								
								<div class="form-group">
								<label class="col-md-3 control-label">Ceiling Fare:</label>
								<div class="col-md-4">
									<input type="text" class="form-control" name="fareChartMaster.ceiling_fare" id="ceilVal" maxLength="8"
										value="<s:property value="fareChartMaster.ceiling_fare"/>"> <!--  onchange="isInteger()"> -->
									<s:if test="fieldErrors.get('ceiling_fare').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('ceiling_fare').get(0)" /></span>
									</s:if><br>
									<font color="red"><span id="ceilFareVal"></span></font>
								</div>
							    </div>
								
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

							
									<div class="form-group">
										<label class="col-md-3 control-label">Night Service:</label>
										<div class="col-md-3">
<s:select list="nightServiceMap" id="nignt_service" name="fareChartMaster.nignt_service" 
		cssClass="select2_category form-control"></s:select>
											<%-- select class="form-control" id="schedule_service"
												name="fareChartMaster.schedule_service">
												<option value="">Select</option>
												<option value="Yes">Yes</option>
												<option value="No">No</option>

											</select> --%>
										</div>
									</div>	
								
									<div class="form-group">
										<label class="col-md-3 control-label">Peak Hours:</label>
										<div class="col-md-3">
<s:select list="peakHoursMap" id="peak_time" name="fareChartMaster.peak_time" 
		cssClass="select2_category form-control" ></s:select>										
											<%-- <select class="form-control" 
												name="fareChartMaster.peak_time">
												<option value="">Select</option>
												<option value="Yes">Yes</option>
												<option value="No">No</option>
											</select> --%>
										</div>
									
								</div>
								
								<div class="form-group">
										<label class="col-md-3 control-label">Flexi Fare:</label>
										<div class="col-md-3">
								<s:select list="flexiFareMap" id="peak_time" name="fareChartMaster.flexi_fare" 
								cssClass="select2_category form-control" ></s:select>										
										</div>
									
								</div>
								
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn blue">Save</button>
										<s:a><button type="reset" id="resetbtn" class="btn default" onclick="resetform()">Reset</button></s:a>
										<button type="button" class="btn default" onclick = "javascript:location.href='ShowFareChartAction.action';">Cancel</button>
										
										
									</div>
								</div>

								

							</form>
							<!-- END FORM-->
							<%}else{ %>
<%@ include file="/pages/admin/error.jsp" %>
<%} %>									
										
<%-- <%}else{%> --%>
 


<%-- <%@ include file="/pages/admin/error.jsp" %> --%>


<%-- <%}%> --%>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>