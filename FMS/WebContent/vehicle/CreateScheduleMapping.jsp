<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
<link rel="stylesheet"	href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
<style type="text/css">
.help-block {
	color: red;
}
.help-block,ul,li {
	list-style: none;
}

</style>
<script>
function getVehicle(depotID)
{
// 	alert("in getVehicle");
	$('#select2-chosen-3').html("Select");
	var depotid=document.getElementById('depotId').value;
	 if(depotid!=0) {
		 $("#msg").html("Please wait...");
   	$.ajax({
	   type: "POST",
       url: '<%=request.getContextPath()%>/getDepotVehicle?val='+depotid,
       success: function(result) {
    	   $("#msg").html("");
    	   document.getElementById('vehiclelist').innerHTML=result;
       }
   });
}
}
function getDepotList(orgId){

	 var val=document.getElementById('divisionlist').value;
		 if(val!=0) {
      $.ajax({
          type: "post",
          url: '<%=request.getContextPath()%>/getDepotForScheduleMapping?val=' + val,
			success : function(result) {
				document.getElementById('depotlist1').innerHTML = result;
				getScheduleListForScheduleMapping("");
				getVehicleListForScheduleMapping("");
				
			}
		});
	}
}
function getScheduleListForScheduleMapping(orgId){

	 var val=document.getElementById('depotlist1').value;
		 if(val!=0) {
     $.ajax({
         type: "post",
         url: '<%=request.getContextPath()%>/getScheduleListForScheduleMapping?val=' + val,
			success : function(result) {
				document.getElementById('schedulelist1').innerHTML = result;
				
			}
		});
	}
}
// function getShiftListForScheduleMapping(orgId){

// 	 var val=document.getElementById('schedulelist1').value;
// 		 if(val!=0) {
//     $.ajax({
//         type: "post",
<%--         url: '<%=request.getContextPath()%>/getShiftListForScheduleMapping?val=' + val, --%>
// 			success : function(result) {
// 				document.getElementById('shiftlist1').innerHTML = result;
				
// 			}
// 		});
// 	}
// }
function getVehicleListForScheduleMapping(orgId){

	 var val=document.getElementById('depotlist1').value;
		 if(val!=0) {
   $.ajax({
       type: "post",
       url: '<%=request.getContextPath()%>/getDepotVehicleForScheduleMapping?val=' + val,
			success : function(result) {
				document.getElementById('vehiclelist1').innerHTML = result;
				
			}
		});
	}
}
function check(){
	if(document.getElementById("divisionlist").value=="0")
	{
		
	bootbox.alert("Please Select Division");
	return false;
	}
	if(document.getElementById("depotlist1").value=="0")
		{
		bootbox.alert("Please Select Depot");
		return false;
		}
	if(document.getElementById("schedulelist1").value=="0")
	{
		bootbox.alert("Please Select Schedule");
	return false;
	}
// 	if(document.getElementById("shiftlist1").value=="0")
// 	{
// 	bootbox.alert("Please Select Shift");
// 	return false;
// 	}
	if(document.getElementById("vehiclelist1").value=="0")
	{
	bootbox.alert("Please Select Vehicle No");
	return false;
	}
	var e = document.getElementById("vehiclelist1");
	var vehicle_no = e.options[e.selectedIndex].text;
	document.getElementById("vehicleNO").value=vehicle_no;
	document.getElementById("form1").action="saveScheduleMappingActionWebServiceCall.action";
	document.getElementById("form1").submit();
}
</script>

<input type="hidden" name="id" id="id" />
<div class="page-content-wrapper">
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ScheduleMappingList.action");
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
					SCHEDULE MAPPING
				</h3>
			</div>
		</div>
		<div class="row">
	
			<div class="col-md-12">
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i> Create Schedule Mapping 
						</div>
					</div>
					<div class="portlet-body form">
<!-- 						<form action="saveEditScheduleMappingAction" class="form-horizontal form-row-seperated" method="post"> -->
                     <form action="saveScheduleMappingActionWebServiceCall" class="form-horizontal form-row-seperated" method="post" name="f1" id="form1"> 
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
								
								<input type="hidden" name="schedule_type_id" value='<s:property value="schedulemp.schedule_type_id"/>'>
								<input type="hidden" name="shift_type_id" value='<s:property value="schedulemp.shift_type_id"/>'>
								<input type="hidden" name="depot_id" id="depotId" value='<s:property value="schedulemp.depot_id"/>'>
								
								<div class="form-group">
										<label class="col-md-3 control-label">Division<font
											color="red">*</font></label>
										<div class="col-md-3">
											<s:select list="divisionlist" id="divisionlist"
												name="org_chart_id" cssClass="select2_category form-control"
												onchange="getDepotList(this.value)" ></s:select>

										</div>
									</div>
                                 <script>
                                 getDepotList("");
					            </script>
									<div class="form-group">
										<label class="col-md-3 control-label">Depot<font
											color="red">*</font></label>
										<div class="col-md-3">
											<select list="depotlist" id="depotlist1"
												class="select2_category form-control" name="depotlist1" onchange="getScheduleListForScheduleMapping(this.value);getVehicleListForScheduleMapping(this.value)">
												<option value="0">select</option>
											</select>
										</div>
									</div>
								
									<div class="form-group">
										<label class="col-md-3 control-label">Schedule<font
											color="red">*</font></label>
										<div class="col-md-3">
											<select list="schedulelist" id="schedulelist1"
												class="select2_category form-control" name="schedulelist1" >
												<option value="0">select</option>
											</select>
										</div>
									</div>
									
<!-- 											<div class="form-group"> -->
<!-- 										<label class="col-md-3 control-label">Shift Type<font -->
<!-- 											color="red">*</font></label> -->
<!-- 										<div class="col-md-3"> -->
<%-- 											<select list="shiftlist" id="shiftlist1" --%>
<%-- 												class="select2_category form-control" name="shiftlist1" > --%>
<!-- 												<option value="0">select</option> -->
<%-- 											</select> --%>
<!-- 										</div> -->
<!-- 									</div> -->
										<div class="form-group">
										<label class="col-md-3 control-label">Vehicle No<font
											color="red">*</font></label>
										<div class="col-md-3">
										<input type='hidden' name="vehicleNO" id="vehicleNO"/>
											<select list="vehiclelist" id="vehiclelist1"
												class="select2_category form-control" name="vehiclelist1">
												<option value="0">select</option>
											</select>
										</div>
									</div>
									
								
									<div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9">
											<button type="button" class="btn blue" onclick="check()">Save</button>
											<button type="button" class="btn default"	onclick="callCancell()">Cancel</button>
										</div>
									</div>
								</div>
							</div>
						</form>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	function callCancell() {

		window.location.href = 'ScheduleMappingList.action';
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
