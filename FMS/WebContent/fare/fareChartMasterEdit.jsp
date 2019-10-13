<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
<script type="text/javascript">
$(document).ready(function(){
	getFareMasterBody1();
});


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
 
function getFareMasterBody1(){
	var val= document.getElementById('service_type_id').value;
	var len= document.getElementById('ratemaster').options.length;
	var selectedRate = '<s:property value="fareChartMaster.rate_master_id" />';
	//alert("selectedRate"+selectedRate);
	 if(len<2 ) {
         $.ajax({
             type: "post",
             url: '<%=request.getContextPath()%>/FareChartAction!getRateMasterVersion?id='+val,
             success: function(result) {
            	 //alert("kle"+result);
                 document.getElementById('ratemaster').innerHTML=result;
                 document.getElementById('ratemaster').value=selectedRate;
             }
         })
	 }
 	 if(selectedRate!=""){
		 //document.getElementById("orgName"+prevType1).selected=true;
		 document.getElementById("ratemaster").value=selectedRate;
		 var orgchartid = document.getElementById("ratemaster").value;
		 var orgname = ratemaster.options[ratemaster.selectedIndex].text;
		 //document.getElementById('select2-chosen-6').innerHTML=orgname;
		 
	 }  
 	$("#rateMasterId").val(selectedRate);
    $("#oldRateMasterId").val(selectedRate);
 }
 
	function callFareChart() {
			var val='<s:property value="fareChartMaster.farechart_master_id" />';

			document.forms[0].action = 'FarechartTri?id='+ val;
			document.forms[0].submit();

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
	
	function applyCeil(){
		var oldCeil='<s:property value="oldCeilValue"/>';
		var newCeil=document.getElementById('ceilVal').value;
		
		if(parseInt(newCeil)!=0 && parseInt(oldCeil)!=parseInt(newCeil)){
	       if (confirm("Do you want to apply Ceiling Fare changes in Fare Chart Detail") == true) {
	    	   document.getElementById('applyCeilValue').value='1';
	       } else {
	    	   document.getElementById('applyCeilValue').value='0';
	       }
		}else{
			document.getElementById('applyCeilValue').value='0';
		}
		
		return true;
	}
	function setRateMasterId(){
		var selectedRate=$("#ratemaster").val();
		alert("selectedRate"+selectedRate);
				$("#rateMasterId").val(selectedRate);
				console.log(selectedRate);
	}
</script>

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

	<input type="text" id='a'>

	<div class="page-content-wrapper">
		<div class="page-content">
		<%if (access.equalsIgnoreCase("Y")){%>
		<%if(edit.equalsIgnoreCase("Y")){ %>
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
								<i class="fa fa-gift"></i>Edit Fare Chart
							</div>
							
							<div class="actions">
							
							<a href="#" class="btn blue"  id="edits" onclick="callFareChart()"> 
								<i class="fa fa-pencil"></i> Fare Chart </a>

							</div>
							
						</div>

						<div class="portlet-body form">
							<%-- <s:if test="hasActionErrors()">
								<div class="alert alert-danger"> --%>
									<%-- <button class="close" data-close="alert"></button>
									<span>  --%>
								<font color="red">	<s:actionerror /></font>
									<%-- </span> --%>
							<%-- 	</div>


							</s:if> --%>
							<!-- BEGIN FORM-->
							
							
							<form action="AddEditedFareChart.action" class="form-horizontal"
								method="post" onsubmit="return applyCeil()">
								<input type="hidden" name="fareChartMaster.farechart_master_id" 
								value="<s:property value='fareChartMaster.farechart_master_id'/>" />
								<br>
									<div class="form-group">
										<label class="col-md-3 control-label" 
										>Route Number:<font color="red">*</font></label>
										
										<div class="col-md-4">
										<s:select list="routeNumberMap" id="route_id" name="fareChartMaster.route.route_id" 
		cssClass="select2_category form-control"  headerKey="0" headerValue="Select"></s:select>
										<%-- <select class="form-control" name="fareChartMaster.route_id" 
											 id="route_id" onclick="getRouteNo()">
										<option value="<s:property value='fareChartMaster.route_id'/>"><s:property value='routeNumber'/></option>	
										</select>--%>
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
											<option value="<s:property value='fareChartMaster.service_type_id'/>"><s:property value='serviceType'/></option>
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
									 onclick="getFareMasterBody()"  > 
									 <!-- onselect="getFareMaster()"> -->
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
		cssClass="select2_category form-control" value="2"></s:select> --%>
			<select class="form-control" id="passenger_type_id"
											name="fareChartMaster.passenger_type_id">
												<option value="2">Adult</option>
												<!-- <option value="1">Child</option>
												<option value="3">Senior Citizen</option> -->
											</select> 
										<%-- <s:if test="fieldErrors.get('passenger_type_id').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('passenger_type_id').get(0)" /></span>
											</s:if>	 --%>
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
								<!-- <div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Fare Chart Name</label>
										<div class="col-md-4">
											<input type="text" class="form-control"
												name="fareChartMaster.farechart_name"
												>

										</div>
									</div>
								</div> -->
								
								<div class="form-group">
									<label class="control-label col-md-3">Effective Start
										Date:<font color="red">*</font></label>
									<div class="col-md-4">
										<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd/mm/yyyy"
															data-date-viewmode="years" data-date-start-date="+0d">
											<input type="text" size="16" class="form-control"
												id="effect_start_date" readonly="readonly"
												name="fareChartMaster.effect_start_date" 
												value="<s:property value='fareChartMaster.effect_start_date'/>"
												>
												 <span class="input-group-btn" >
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
										</div> 
										<s:if test="fieldErrors.get('effect_start_date').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('effect_start_date').get(0)" /></span>
											</s:if>
										<!-- /input-group -->
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">Effective End
										Date:</label>
									<div class="col-md-4">
										<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd/mm/yyyy"
															data-date-viewmode="years" data-date-start-date="+0d">
											<input type="text" size="16"  class="form-control" readonly="readonly"
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
									</s:if><br><font color="red"><span id="ceilFareVal"></span></font>
									<input type="hidden" value="0" name="applyCeilValue" id="applyCeilValue"/>
									<input type="hidden" value="<s:property value="oldCeilValue"/>" name="oldCeilValue" />
								</div>
							    </div>
								
									<div class="form-group">
										<label class="col-md-3 control-label">Night Service:</label>
										<div class="col-md-3">
										<s:select list="nightServiceMap" id="nignt_service" name="fareChartMaster.nignt_service" 
		cssClass="select2_category form-control"></s:select>
	
										</div>
									</div>

									
									
									<div class="form-group">
										<label class="col-md-3 control-label">Peak Hours:</label>
										<div class="col-md-3">
<s:select list="peakHoursMap" id="peak_time" name="fareChartMaster.peak_time" 
		cssClass="select2_category form-control"></s:select>										
		
										</div>
									</div>
									
								<div class="form-group">
										<label class="col-md-3 control-label">Flexi Fare:</label>
										<div class="col-md-3">
								<s:select list="flexiFareMap" id="peak_time" name="fareChartMaster.flexi_fare" 
								cssClass="select2_category form-control" ></s:select>										
										</div>
									
								</div>
<!-- 								<input type="hidden" id="rateMasterId" name="fareChartMaster.rate_master_id" value="">	 -->
								<input type="hidden" id="oldRateMasterId" name="oldRateMasterId" value="">
								<%-- <div class="form-group">
									<label class="col-md-3 control-label">Start Point<font color="red">*</font></label>
									<div class="col-md-4">
									<select class="form-control" id="passenger_type_id"
											name="fareChartMaster.passenger_type_id" onclick="getpassengerId()">
											<option value="<s:property value='fareChartMaster.passenger_type_id'/>"><s:property value='passengerType'/></option>
											</select>
										<s:if test="fieldErrors.get('passenger_type_id').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('passenger_type_id').get(0)" /></span>
											</s:if>	
										</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-3 control-label">End Point<font color="red">*</font></label>
									<div class="col-md-4">
									<select class="form-control" id="passenger_type_id"
											name="fareChartMaster.passenger_type_id" onclick="getpassengerId()">
											<option value="<s:property value='fareChartMaster.passenger_type_id'/>"><s:property value='passengerType'/></option>
											</select>
										<s:if test="fieldErrors.get('passenger_type_id').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('passenger_type_id').get(0)" /></span>
											</s:if>	
										</div>
								</div>



									<div class="form-group">
										<label class="col-md-3 control-label">No. Of kms(m)</label>
										<div class="col-md-4">
											<input class="form-control input-sm" type="text" id="no_of_km"
												name="fareChartMaster">
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">Fare Amount(m)</label>
										<div class="col-md-3">
											<input type="text" class="form-control"
												name="fareChartMaster"
												>
										</div>
									</div>


									<div class="form-group">
										<label class="col-md-3 control-label">Toll Fee(m)</label>
										<div class="col-md-3">
											<input type="text" class="form-control"
												name="fareChartMaster"
												>
										</div>
									</div> --%>

									




									<div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9">
											<button type="submit" class="btn blue">Save</button>
											<!-- <button type="reset" class="btn default" >Reset</button> -->
											<button type="button" class="btn default" onclick = "javascript:location.href='ShowFareChartAction.action';">Cancel</button>
										</div>
									</div>
									
							</form>
							<!-- END FORM-->
<%}else{ %>
<%@ include file="/pages/admin/error.jsp" %>
<%} %>									
										
<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>


						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>