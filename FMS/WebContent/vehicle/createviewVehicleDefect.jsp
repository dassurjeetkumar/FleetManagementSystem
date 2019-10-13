<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/struts-dojo-tags" prefix="sx"%>
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

<%-- <script type="text/javascript" src="//www.google.com/jsapi"></script> --%>

<script>

function goView()
{
	document.forms[0].action = 'vehicledefectlist.action';
	document.forms[0].submit();
}


</script>

<title>Insert title here</title>
</head>
<body onload="setVehicleid()">
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
String status=accessrightdao.UserStatus(usrsessionid);
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
%>

	<div class="page-content-wrapper">
	<%if (access.equalsIgnoreCase("Y")){%>
		<%if (create.equalsIgnoreCase("Y")){%>
		<div class="page-content">

			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						VEHICLE DEFECT <small></small>
					</h3>

					<!-- END PAGE TITLE & BREADCRUMB-->
				
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Create Vehicle Defect
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
							<form action="createVehicleDefectAction.action" method="POST"  class="form-horizontal">
							<s:if test="%{insertstatus=='duplicate'}"><font color="red">Could not insert Duplicate Vehicle Defect Type</font></s:if>
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Defect Type:<font color="red">*</font></label>
										<div class="col-md-4">
											<input type="text" class="form-control" id="defect_type_name" maxlength="20" value='<s:property value="vehicledefect.defect_type"/>'
												name="vehicledefect.defect_type">
												<s:if test="fieldErrors.get('defecttype').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('defecttype').get(0)" /></span>
											</s:if>
										</div>
									</div>
								</div>
							
<!-- 									<div class="form-body"> -->
<!-- 									 <div class="form-group"> -->
<!-- 									<label class="col-md-3 control-label">Vehicle Registration Number:<font color="red">*</font></label> -->
<!-- 									<div class="col-md-4">	 -->
<!-- 									<div id="pageLoader" class="modal fade in" tabindex="-1" role="dialog" aria-labelledby="myModalLabel3" aria-hidden="false"> -->
<!-- 								<div class="modal-dialog"> -->
<!-- 									<div class="modal-content"> -->
										
<!-- 										<div class="modal-body"> -->
<!-- 											<p> -->
<!-- 												<img src="assets/images/loader1.gif" align="top" style="margin-left:100px;"/> Please Wait -->
<!-- 											</p> -->
<!-- 										</div> -->
										
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div>									 -->
<!-- 									  <input placeholder="Enter Vehicle Number to Search" id="vehicledetails" type="text"  -->
<!-- 									  class="form-control" name="vehicledefect.vehicle.vehicleRegistrationNumber" maxLength="12" -->
<%-- 									  onkeypress="getVehicleDetails(this.value)" onkeydown="getVehicleDetails(this.value)" value="<s:property value="vehicledefect.vehicle.vehicleRegistrationNumber"/>"> --%>
									
<%-- 										<s:if test="fieldErrors.get('vehicleId').size() > 0"> --%>
<%-- 		     								<span style="color:red;"><s:property value="fieldErrors.get('vehicleId').get(0)" /></span> --%>
		     								
<%-- 											</s:if> --%>
<%-- 										<input id="vehicleiddetails" name="vehicledefect.vehicle.vehicleId" type="hidden" value="<s:property value='vehicledefect.vehicle.vehicleId'/>"> --%>
										
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 									</div> -->
													 		
								<div class="form-body">
								<div class="form-group">
									<label class="col-md-3 control-label">Date:<span class="required">* </span></label>
									<div class="col-md-4">
										<div class="input-group date date-picker"	data-date-format="dd-mm-yyyy" data-date-viewmode="years" data-date-start-date="+0d">
										<input type="text" class="form-control" name="vehicledefect.date"	value="<s:property value='vehicledefect.date'/>" readonly /> <span
														class="input-group-btn">
											<button class="btn default" type="button">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
										
									</div>
									
									<s:if test="fieldErrors.get('defect_date').size() > 0"> 
					     						<span style="color:red;"><s:property value="fieldErrors.get('defect_date').get(0)" /></span> 
 											</s:if> 
								</div>
								</div>
								</div>
								<div class="form-body">
								<div class="form-group">
									<label class="col-md-3 control-label">Status:<font color="red">*</font></label>
									<div class="col-md-4">
										<select class="form-control" id="status" name="vehicledefect.status" <s:property value="vehicledefect.status"/> >
											<option value="ACTIVE">ACTIVE</option>
											<!-- <option value="INACTIVE">INACTIVE</option> -->
										</select>
										
									</div>
								</div>
								</div>
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Remarks:</label>

										<div class="col-md-4">
											<textarea rows="3" class="form-control" id="notes" maxlength="100" name="vehicledefect.notes"><s:property value="vehicledefect.notes"/></textarea>
										</div>
									</div>
								</div>

								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button  type="submit" class="btn blue" onSubmit="myFunction()">Save</button>
										<button type="button" class="btn default" onclick="goView()">Cancel</button>
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
function myFunction() {
	/*  var x = document.getElementById("vehicledetails");
	 var y = document.getElementById("vehicleiddetails");
	    x.value = x.value.toUpperCase(); */
    $( "#vehicledetails" ).val("");
    $( "#vehicleiddetails" ).val(0);
}
function setVehicleid(){
	var vehicleId = $( "#vehicleiddetails" ).val();
	//alert("hi"+vehicleId+"end");
	if($( "#vehicleiddetails")=="")
	 $( "#vehicleiddetails" ).val(0);
}
function getVehicleDetails(id){
	
	var result = "";
	var zeroval =0;
	//alert("zerooo"+zeroval);	
	$('#pageLoader').show();
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
	   //  alert(data);
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
	        	//alert(item.vehicleRegistrationNumber);
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
	    	}  */
	});
}

} 

</script>

</body>
</html>





