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
window.onload=getVehicle;
</script>

<input type="hidden" name="id" id="id" />
<div class="page-content-wrapper">
<%
// AccessRightsDao  accessrightdao=new AccessRightsDao();
// AccessRights accessrights=new AccessRights();
// int usrsessionid=(Integer)request.getSession().getAttribute("userid");
// accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ScheduleMappingList.action");
// String access=accessrights.getACCESS();
// String create=accessrights.getCREATE();
// String edit=accessrights.getEDIT();
// String delete=accessrights.getDELETE();
// String read=accessrights.getREAD();
// String error=accessrights.getERROR();
// String viewdelete=accessrights.getVIEWDELETE();
// String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
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
							<i class="fa fa-globe"></i> Edit Schedule Mapping 
						</div>
					</div>
					<div class="portlet-body form">
						<form action="saveEditScheduleMappingAction.action" class="form-horizontal form-row-seperated" method="post">
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
								
								<input type="hidden" name="schedule_type_id" value='<s:property value="schedulemp.schedule_type_id"/>'>
								<input type="hidden" name="shift_type_id" value='<s:property value="schedulemp.shift_type_id"/>'>
								<input type="hidden" name="depot_id" id="depotId" value='<s:property value="schedulemp.depot_id"/>'>
<%-- 								<input type="hidden" name="form_four_id" id="form_four_id" value='<s:property value="schedulemp.form_four_id"/>'> --%>
								
									<div class="form-group">
<%-- 										<s:hidden name="isUpdateScheduleMapping" value="1" /> --%>
<%-- 										<s:hidden name="updatedScheduleMappingId" cssClass="form-control" value='%{scheduleMapDetails.schedule_no}' /> --%>
										
										<label class="control-label col-md-3"> Schedule No.<span	class="required"> * </span></label>
										<div class="col-md-3">
											<input type="text" class="form-control"  maxlength="10" readonly="readonly"
													name="schedulemp.schedule_no"	value='<s:property value="schedulemp.schedule_no"/>'>

										</div>
									</div>
									
									<div class="form-group">
<%-- 										<s:hidden name="isUpdateScheduleMapping" value="1" /> --%>
<%-- 										<s:hidden name="updatedScheduleMappingId" cssClass="form-control" value='%{scheduleMapDetails.schedule_no}' /> --%>
										
										<label class="control-label col-md-3"> Shift Type<span	class="required"> * </span></label>
										<div class="col-md-3">
											<input type="text" class="form-control"  maxlength="10" readonly="readonly"
													name="schedulemp.shiftName"	value='<s:property value="schedulemp.shiftName"/>'>

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
									
									<div class="form-group">
										<label class="control-label col-md-3">Vehicle No.<font
										color="red">*</font></label>
											<div class="col-md-3">
										<select  id="vehiclelist"  class="select2_category form-control" name="vehicle_no"
 							              > 
							              <option value='<s:property value="schedulemp.vehicle_no"/>'><s:property value="schedulemp.vehicle_no" /></option>
 							           </select> 
									<script type="text/javascript">							
									var orgName= "<s:property value='schedulemp.vehicle_no'/>";
										if(orgName!=""){
											document.getElementById(orgName).selected=true;
											alert(orgname);
										}
									</script>
<!-- 														<input type="text" class="form-control"  maxlength="10"  -->
<%-- 													name="vehicle_no"	value='<s:property value="schedulemp.vehicle_no"/>'> --%>
										</div>
									</div>
									

							
							
									<div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9">
											<button type="submit" class="btn blue">Save</button>
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
