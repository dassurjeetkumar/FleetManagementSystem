<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<!DOCTYPE html>
<html>
<head>
<style>
.ui-autocomplete {
max-height: 100px;
overflow-y: auto;
/* prevent horizontal scrollbar */
overflow-x: hidden;
/* add padding for vertical scrollbar */
    padding-right: 5px;
}
/* IE 6 doesn't support max-height
* we use height instead, but this forces the menu to always be this tall
*/
 html .ui-autocomplete {
height: 100px;
}
</style>
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css"> 
<%-- <script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script> --%>
<link rel="stylesheet" href="/resources/demos/style.css">
<script type="text/javascript" src="//www.google.com/jsapi"></script>
<script type="text/javascript">
function getEditDetail(){
	//$('#empid').prop('disabled', false);
	//$('#user_type').prop('disabled', false);
	//$('#userloginname').prop('disabled', false);
	
	document.frm.action="updateVehicleDefectDetails.action";
	document.frm.submit();
}
function cancelform(){
	document.frm.action="vehicledefectlist.action";
	document.frm.submit();
}

</script>
</head>
<body onload="setVehicleid()">
	<input type="text" id='a'>
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "vehicledefectlist.action");
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
		<%if (access.equalsIgnoreCase("Y")){%>
		<%if (edit.equalsIgnoreCase("Y")){%>
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						VEHICLE DEFECT<small></small>
					</h3>

					<!-- END PAGE TITLE & BREADCRUMB-->
					
					<div class="portlet box grey-cascade">
					

						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Edit Vehicle Defect
							</div>

						</div>

						<div class="portlet-body form">
							<s:if test="hasActionErrors()">
								<div class="alert alert-danger">
									<button class="close" data-close="alert"></button>
									<span> <s:actionerror />
									</span>
								</div>


							</s:if>
							<!-- BEGIN FORM-->
						<form action="#" class="form-horizontal" name="frm" method="post">
						
							<s:if test="%{updatestatus=='duplicate'}"><font color="red">Could not Update Duplicate Vehicle Defect Type</font></s:if>
							<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Defect Type:<font color="red">*</font></label>
										<div class="col-md-4">
										<input type="hidden"   name="vehicledefect.defect_id"  value='<s:property value="vehicledefect.defect_id" />'  id="defect_id" >
											<input type="text" class="form-control" id="defect_type_name" maxlength="20" value='<s:property value="vehicledefect.defect_type"/>'
												name="vehicledefect.defect_type">
												<s:if test="fieldErrors.get('defecttype').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('defecttype').get(0)" /></span>
											</s:if>
										</div>
									</div>
								</div>
								
<!-- 								<div class="form-body"> -->
<!-- 									 <div class="form-group"> -->
<!-- 									<label class="col-md-3 control-label">Vehicle Registration Number:<font color="red">*</font></label> -->
<!-- 									<div class="col-md-4">										 -->
<!-- 									<input class="form-control input-lg"  id="busStop" name="project" type="text" onkeyup="getDropStops(this.value)" /> -->
<!-- 									  <input placeholder="Enter Vehicle Number to Search" id="vehicledetails" type="text" class="form-control" name="vehicledefect.vehicle.vehicleRegistrationNumber" maxLength="20" -->
<%-- 										onkeypress="getVehicleDetailsEdit(this.value)" value="<s:property value="vehicledefect.vehicle.vehicleRegistrationNumber"/>"> --%>
																										
<%-- 									<s:if test="fieldErrors.get('vehicleId').size() > 0">  --%>
<%--  					     						<span style="color:red;"><s:property value="fieldErrors.get('vehicleId').get(0)" /></span>  --%>
<%--  											</s:if> --%>
										
<!-- 										<input id="vehicleiddetails" name="vehicledefect.vehicle.vehicleId" type="hidden" -->
<%-- 										 value="<s:property value='vehicledefect.vehicle.vehicleId'/>" > --%>
									
										
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 									</div> -->
																
								<div class="form-body">
								<div class="form-group">
									<label class="control-label col-md-3">Date:<font color="red">*</font></label>
									<div class="col-md-4">
										<div class="input-group date date-picker"	data-date-format="dd-mm-yyyy" data-date-viewmode="years" data-date-start-date="+0d">
										<input type="text" class="form-control" name="vehicledefect.date"	value="<s:property value='vehicledefect.date'/>" readonly /> <span
														class="input-group-btn">
											<button class="btn default" type="button">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
										
									</div>
									
									<%-- <span class="help-block" style="color: red"> 
										<s:property	value="%{fieldErrors.get('defectdate').get(0)}" />
									</span> --%>
									<s:if test="fieldErrors.get('defect_date').size() > 0"> 
					     						<span style="color:red;"><s:property value="fieldErrors.get('defect_date').get(0)" /></span> 
 											</s:if> 
								</div>
								</div>
								</div>
								<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Remarks:</label>

										<div class="col-md-4">
											<textarea rows="3" 
											class="form-control" id="notes" maxlength="100"	name="vehicledefect.notes"><s:property value="vehicledefect.notes"/></textarea>
										</div>
									</div>
								</div>
								
								
								<div class="form-body">
				<div class="form-group">
								<label class="control-label col-md-3">Status:<font color="red">*</font></label>
								<div class="col-md-4">
									
									<select class="form-control" id="status"
																	name="vehicledefect.status">

																	<option id="active" value="ACTIVE">ACTIVE</option>
																	<option id="deactive" value="INACTIVE">INACTIVE</option>
																</select>
										<!-- <input type="text" class="form-control" readonly="readonly" 
											value="ACTIVE" name="battery.status" /> -->
									</div>
									<script>
															var status = "<s:property value="vehicledefect.status"/>";
															if (status != undefined) {
																if (status == "ACTIVE"
																		|| status == "ACTIVE") {
																	document
																			.getElementById("active").selected = true;
																} else {
																	document
																			.getElementById("deactive").selected = true;
																}
															}
														</script>
								</div>
							</div>
							
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
									<button class="btn blue" type="submit" onclick="getEditDetail()">Save</button>
										<button class="btn default" type="button" onclick="cancelform()">Cancel</button>
										<!-- <button type="submit" class="btn blue" onsubmit="validate()">Save</button>
										<button type="button" class="btn default" onclick="goView()">Cancel</button> -->
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

<script>
function setVehicleid(){
	var vehicleId = $( "#vehicleiddetails" ).val();
	//alert("hi"+vehicleId+"end");
	if($( "#vehicleiddetails")=="")
	 $( "#vehicleiddetails" ).val(0);
}
function getVehicleDetailsEdit(id){
	$('#pageLoader').show();
	var result = "";
	$( "#vehicleiddetails" ).val(-1);
	if(id.length>=0){
		
	$.ajax({
	    type        : 'GET',
	    data :'json',
	    url         :  "<s:url action='GetvehicleName'/>",
	    data: {
	        id: id
	    },
	  
	    success: function(data){
	       data =eval(data);
	        result=data;
	       
	       
	       
	        $( "#vehicledetails" ).autocomplete({
	        	 
		        	minLength: 0,
		        	source: result,
		        	focus: function( event, ui ) {
		        	
		        	 $( "#vehicledetails" ).val( ui.item.vehicleNumber );
		        	$( "#vehicleiddetails" ).val( ui.item.id );
		        	
		        	return false;
	        	},
	        	select: function( event, ui ) {
	        		
		        	$( "#vehicledetails" ).val( ui.item.vehicleNumber );	
		        	$( "#vehicleiddetails" ).val( ui.item.id );
		        
		        	return false;
	        	}
	        	})
	        	.data( "ui-autocomplete" )._renderItem = function( ul, item ) {
	        	return $( "<li>" )
	        	.append( "<a>" + item.vehicleNumber + "</a>" )
	        	.appendTo( ul );
	        	};
	        	 $('#pageLoader').hide();
	    },
	    
	    select: function (event, ui) {

	        alert("selected!");
	    },
	    change: function (event, ui) {

	        alert("changed!");
	    },
	   /*  error : function(xhr, errmsg) {
	    	alert("No values found..!!"+errmsg);
	    	}
	     */
	});
	
}
} 

</script>
</body>
</html>




