<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
<link rel="stylesheet"	href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
 <script src="assets/global/plugins/jquery-1.11.0.min.js"
	type="text/javascript"></script>
<style type="text/css">
.help-block {
	color: red;
}
.help-block,ul,li {
	list-style: none;
}

</style>
<script>

$(function(){
	 $("#reason").hide();
	});
function getVehicle(depotID)
{
// 	alert("in getVehicle");
	$('#select2-chosen-3').html("Select");
	var depotid=document.getElementById('depot_id').value;
// 	var vehicle_no=document.getElementById('vehicleid').value;
// 	alert("depotid=="+depotid);
	 if(depotid!=0) {
		 $("#msg").html("Please wait...");
   	$.ajax({
	   type: "POST",
       url: '<%=request.getContextPath()%>/getDepotVehicleList?val='+depotid,
       success: function(result) {
    	   $("#msg").html("");
    	   document.getElementById('vehiclelist').innerHTML=result;
       }
   });
}
}
window.onload=getVehicle;
var value=false;
function save(){
// alert("submit");
if(value==true){
			document.f1.action = 'SaveDailyEditScheduleMapping.action';
   		 	document.f1.submit();
}
   		 /*}
		
	}*/
        
}
	var i=0;
	 var count=0;
	 var value=false;
	 function ValidateVehicleNo(vehicleno){

			var val= $('#vehiclelist').val();
			var dat= $('#startdate1').val();
			var shift_type=$('#shiftName').val();
// 			alert("val==="+shift_type);
			
// 	 		 alert(val+"=="+dat);
// 	 		bootbox.confirm("This is the default confirm!", function(result){ console.log('This was logged in the callback: ' + result); });				 
	 	
 
 		if(shift_type == "Night Service"){
//  			alert("val2==="+shift_type);
 			value=true;
 		}else {
//  			alert("val1==="+shift_type);
			if(val!=0) {
		        $.ajax({
		            type: "post",
		            datatype : "text",
		            url: '<%=request.getContextPath()%>/AjaxValidateVehicleNo?vehicleno='+val+'&date='+dat,
					success : function(result) {
// 	 					alert("result=="+result);
						 if(result == "" ){
// 							 count=0;
// 							 etm=result;
                             value=true;
// 							 return value;
						 }else{
// 					           alert( "Please select vehicle enter else" );
					          
//	 		 					document.getElementById('etmnumberid').innerHTML = result;
	 		
	 		
	 		 bootbox.confirm("This Vehicle Number is Already assigned to "+result + ".   Do you want to Continue...?", function(result1){ console.log('This was logged in the callback: ' + result1); 
	 		 
	  	  
	 		 value=result1;
// 	 		alert("val5==="+value);
// 	 		 return result1;
	 		 });	
	 		 
// 	  	    bootbox.confirm({
// 	 	        title: "Destroy planet?",
// 	 	        message: "Do you want to activate the Deathstar now? This cannot be undone.",
// 	 	        buttons: {
// 	 	            cancel: {
// 	 	                label: '<i class="fa fa-times"></i> Cancel'
// 	 	            },
// 	 	            confirm: {
// 	 	                label: '<i class="fa fa-check"></i> Confirm'
// 	 	            }
// 	 	        },
// 	 	        callback: function (result) {
// 	 	            console.log('This was logged in the callback: ' + result);
// 	 	        }
// 	 	    });

// 						    	 bootbox.confirm("This Vehicle Number is Already assigned to "+mappedSchedule + "Do you want to Continue");
					        }
					}
				});
			
	
					 }
 		}
 

				 return value;
		}
// 	    bootbox.confirm({
// 	        title: "Destroy planet?",
// 	        message: "Do you want to activate the Deathstar now? This cannot be undone.",
// 	        buttons: {
// 	            cancel: {
// 	                label: '<i class="fa fa-times"></i> Cancel'
// 	            },
// 	            confirm: {
// 	                label: '<i class="fa fa-check"></i> Confirm'
// 	            }
// 	        },
// 	        callback: function (result) {
// 	            console.log('This was logged in the callback: ' + result);
// 	        }
// 	    });
	
	function change(value){
		//alert(value);
		if(value=="0"){
			 $("#reason").hide();
			
		}else {
			 $("#reason").show();
		}
		
	}
	
</script>

<input type="hidden" name="id" id="id" />
<div class="page-content-wrapper">
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "getDailyScheduleMapping.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
%>

	<div class="page-content">
	<%/* if (access.equalsIgnoreCase("Y")){ */%>
		<%-- %if (edit.equalsIgnoreCase("Y")){ --%>
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
					EDIT DAILY SCHEDULE MAPPING
				</h3>
			</div>
		</div>
		<div class="row">
	
			<div class="col-md-12">
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i> Edit Daily Schedule Mapping 
						</div>
					</div>
					<div class="portlet-body form">
						<form action="SaveDailyEditScheduleMapping.action" class="form-horizontal form-row-seperated" method="post" name="f1" onsubmit="return ValidateVehicleNo();">
<!--                      <form action="saveEditScheduleMappingActionWebServiceCall" class="form-horizontal form-row-seperated" method="post">  -->
							<b>
								<font color="green"> <s:actionmessage/></font>
								<span id="errorMsg" style="color:red;word-wrap: break-word;" ><s:actionerror/></span>
							</b>
							<div class="form-body">
							
								<%-- <div class="form-group">
									<label class="control-label col-md-3">Schedule No.</label>
									<div class="col-md-3">
										<input type="text" class="form-control" readonly="" name="scheduleMapDetails.schedule_no"
												value='<s:property value="scheduleMapDetails.schedule_no"/>'>
									</div>
								</div> --%>
								
								<div class="form-group">
								
								<input type="hidden" id="division1"  name="division1" value='<s:property value="division1"/>'>
                            <input type="hidden" id="depot1" name="depot1" value='<s:property value="depot1"/>'>
                            <input type="hidden" id="startdate1" name="startdate1" value='<s:property value="startdate1"/>'>
								
								<input type="hidden" id="schedule_id" name="schedule_id" value='<s:property value="schedule_id"/>'>
<%-- 								<input type="hidden" name="shift_type_id" value='<s:property value="schedulemp.shift_type_id"/>'> --%>
								<input type="hidden" name="depot_id" id="depot_id" value='<s:property value="depot_id"/>'>
<%-- 								<input type="hidden" name="form_four_id" id="form_four_id" value='<s:property value="schedulemp.form_four_id"/>'> --%>
								
									<div class="form-group">
<%-- 										<s:hidden name="isUpdateScheduleMapping" value="1" /> --%>
<%-- 										<s:hidden name="updatedScheduleMappingId" cssClass="form-control" value='%{scheduleMapDetails.schedule_no}' /> --%>
										
										<label class="control-label col-md-3"> Schedule No.<span	class="required"> * </span></label>
										<div class="col-md-3">
											<input type="text" class="form-control"  maxlength="10" readonly="readonly" id="schedule_no"
													name="schedule_no"	value='<s:property value="schedule_no"/>'>

										</div>
									</div>
									
									<div class="form-group">
<%-- 										<s:hidden name="isUpdateScheduleMapping" value="1" /> --%>
<%-- 										<s:hidden name="updatedScheduleMappingId" cssClass="form-control" value='%{scheduleMapDetails.schedule_no}' /> --%>
										
										<label class="control-label col-md-3"> Shift Type<span	class="required"> * </span></label>
										<div class="col-md-3">
											<input type="text" class="form-control"  maxlength="10" readonly="readonly" id="shiftName"
													name="shiftName"	value='<s:property value="shiftName"/>'>

										</div>
									</div>
									<input type="hidden" class="form-control"  maxlength="10" list="depotlist" id="depotlist1"
													name="depot_name" onload="getVehicle(this.value)"	value='<s:property value="schedulemp.depot_name"/>'>
													
									
									<div class="form-group">
										<label class="control-label col-md-3">Trip Number<font
										color="red">*</font></label>
											<div class="col-md-3">
										<s:select list="triplist" id="triplists"
												name="trip_no" cssClass="select2_category form-control"
												 headerKey="0" headerValue="---ALL---" onchange="change(this.value)"></s:select>
								
<!-- 														<input type="text" class="form-control"  maxlength="10"  -->
<%-- 													name="vehicle_no"	value='<s:property value="schedulemp.vehicle_no"/>'> --%>
										</div>
									</div>
									
									<div class="form-group" id="reason">
										<label class="control-label col-md-3">Reason<font
										color="red">*</font></label>
											<div class="col-md-3">
										<textarea id="reasondata" name="reasondata" rows="5"  ></textarea>
								
<!-- 														<input type="text" class="form-control"  maxlength="10"  -->
<%-- 													name="vehicle_no"	value='<s:property value="schedulemp.vehicle_no"/>'> --%>
										</div>
									</div>
									
									
									
									<!-- To get depot name in hidden form -->
<!-- 							<div class="form-group"> -->
<!-- 										<label class="control-label col-md-3">Depot Name</label> -->
<!-- 										<div class="col-md-3"> -->
														<input type="hidden" class="form-control"  maxlength="10" list="depotlist" id="depotlist1"
													name="depot_name" onload="getVehicle(this.value)"	value='<s:property value="schedulemp.depot_name"/>'>
													
													
<!-- 										</div> -->
<!-- 									</div> -->
                                 <input type="hidden" id="vehicleid" name="vehicleNo" value='<s:property value="vehicle_no"/>'>
									
									<div class="form-group">
										<label class="control-label col-md-3">Vehicle No.<font
										color="red">*</font></label>
											<div class="col-md-3">
										<select  id="vehiclelist"  class="select2_category form-control" name="vehicle_no" onchange="return ValidateVehicleNo();"
 							              > 
<!--  							              <option value="-1">Select</option> -->
							              <option value='<s:property value="vehicle_no"/>'><s:property value="vehicle_no" /></option>
 							           </select> 
									<script type="text/javascript">							
									var orgName= "<s:property value='vehicle_no'/>";
										if(orgName!=""){
											document.getElementById(vehiclelist).selected=true;
											alert(vehiclelist);
										}
									</script>
<!-- 														<input type="text" class="form-control"  maxlength="10"  -->
<%-- 													name="vehicle_no"	value='<s:property value="schedulemp.vehicle_no"/>'> --%>
										</div>
									</div>
									

							
							
									<div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9">
											<button type="Submit" class="btn blue" onclick="save()" >Save</button>
											<button type="button" class="btn default"	onclick="callCancell()">Cancel</button>
										</div>
									</div>
								</div>
							</div>
						</form>
						<%/* }else{ */ %>
<%-- <%@ include file="/pages/admin/error.jsp" %> --%>
<%/* } */ %>									
										
<%/* }else{ */%>
 


<%-- <%@ include file="/pages/admin/error.jsp" %> --%>


<%/* } */%>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	function callCancell() {
		var dd1=$("#startdate1").val();
		var division=$("#division1").val();
		var depot=$("#depot1").val();
// 		alert(dd1+division+depot);
		
		window.location.href = 'AjaxDailyScheduleMapping.action?startdate='+dd1+'&division='+division+'&depot='+depot;
// 		window.location.href = 'getDailyScheduleMapping.action';

		
	}
</script>
<script>
$(document).ready(function() {
	
	
	   var w=$('#errorMsg span').html();
	   //alert(w);
	    w=w.replace(/@/g,"<br>");

	   $('#errorMsg').html(''+w+'');
  
});
</script>
