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
// window.onload=getVehicle;
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
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ShowRoute.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
%>

	<div class="page-content">
	<%/* if (access.equalsIgnoreCase("Y")){ */%>
		<%-- %if (edit.equalsIgnoreCase("Y")){ --%>
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
					EDIT ROUTE NAME
				</h3>
			</div>
		</div>
		<div class="row">
	
			<div class="col-md-12">
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i> Edit Route Name 
						</div>
					</div>
					<div class="portlet-body form">
						<form action="SaveRouteName.action" class="form-horizontal form-row-seperated" method="post" name="f1" >
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
								
								 <input type="hidden" id="routeid" name="routeid" value='<s:property value="routeid"/>'>
								
								<div class="form-group">
								
							
									<div class="form-group">
<%-- 										<s:hidden name="isUpdateScheduleMapping" value="1" /> --%>
<%-- 										<s:hidden name="updatedScheduleMappingId" cssClass="form-control" value='%{scheduleMapDetails.schedule_no}' /> --%>
										
										<label class="control-label col-md-3"> Route Name.<span	class="required"> * </span></label>
										<div class="col-md-3">
											<input type="text" class="form-control"    id="route_name"
													name="route_name"	value='<s:property value="route_name"/>'>

										</div>
									</div>
									
									
							
							
									<div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9">
											<button type="Submit" class="btn blue"  >Save</button>
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
		
// 		alert(dd1+division+depot);
		
		window.location.href = 'ShowRoute.action';
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
