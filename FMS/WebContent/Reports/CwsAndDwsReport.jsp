<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
<%-- <link rel="stylesheet"	href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" /> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
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
		 if(val!=-1) {
			if(val!=0) {
				$(depotid).show();
      $.ajax({
          type: "post",
          url: '<%=request.getContextPath()%>/getDepotList?val=' + val,
			success : function(result) {
				document.getElementById('depotlist1').innerHTML = result;
				
			}
		});
			}else{
				
				$(depotid).hide();
			}
      
	} 
}
function getScheduleListForScheduleMapping(orgId){

	 var val=document.getElementById('depotlist1').value;
	 
		 if(val!=0) {
			 $('#pageLoader').show();
     $.ajax({
         type: "post",
         url: '<%=request.getContextPath()%>/getAllDataFromScheduleMapping',
			success : function(result) {
				  $('#pageLoader').hide();
				console.log(result);
				
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
	if(document.getElementById("divisionlist").value=="-1")
	{
		
	bootbox.alert("Please Select Division");
	return false;
	}
	if(document.getElementById("divisionlist").value=="0")
	{
		
	}else {
	if(document.getElementById("depotlist1").value=="-1")
		{
		bootbox.alert("Please Select Depot");
		return false;
		}
	}
	if(document.getElementById("reasontype").value=="0")
	{
	bootbox.alert("Please Select Reason Type");
	return false;
	}
	
	if(document.getElementById("startdate").value=="0")
	{
	bootbox.alert("Please Select From Date");
	return false;
	}
	if(document.getElementById("enddate").value=="0")
	{
	bootbox.alert("Please Select To Date");
	return false;
	}
	
// 	if(document.getElementById("schedulelist1").value=="0")
// 	{
// 		bootbox.alert("Please Select Schedule");
// 	return false;
// 	}
// 	if(document.getElementById("shiftlist1").value=="0")
// 	{
// 	bootbox.alert("Please Select Shift");
// 	return false;
// 	}
// 	if(document.getElementById("vehiclelist1").value=="0")
// 	{
// 	bootbox.alert("Please Select Vehicle No");
// 	return false;
// 	}
// 	var e = document.getElementById("vehiclelist1");
// 	var vehicle_no = e.options[e.selectedIndex].text;
// 	document.getElementById("vehicleNO").value=vehicle_no;

var dd1=$("#startdate").val();
var dd2=$("#enddate").val();
var division=$("#divisionlist").val();
var depot=$("#depotlist1").val();
var type=$("#reasontype").val();
var var1=$("#startdate").val();
var1=var1.split("-");
var1=var1[2]+"-"+var1[1]+"-"+var1[0];
	var var2=$("#enddate").val();
	var2=var2.split("-");
	var2=var2[2]+"-"+var2[1]+"-"+var2[0];
//alert(var1+""+var2);

 var d1 = Date.parse(var1);

 
var d3=Date.parse(var2);
	
	if (d1 <= d3){
		 $('#pageLoader').show();
   $.ajax({
   
       type: "post",
       url: '<%=request.getContextPath()%>/getAjaxcwsanddwsReport.action?startdate='+dd1+'&enddate='+dd2+'&depot='+depot+'&divison='+division+'&reason='+type,
       success: function(result) {
       	$('#pageLoader').hide();
           document.getElementById('ticketconsuptionid').innerHTML=result;
           fixHeader();
       }
   });
	}else{
		
		
		alert("Till Date Should Be greater Than Start Date");
		$('#pageLoader').hide();
		 document.getElementById('ticketconsuptionid').innerHTML="";
	}

	
	
	

	//document.getElementById("form1").action='AjaxDailyScheduleMapping.action?startdate='+dd1+'&division='+division+'&depot='+depot;
// 	document.getElementById("form1").action="ScheduleMappingList.action";
	//document.getElementById("form1").submit();
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
		
		 <div id="pageLoader" class="modal fade in" tabindex="-1" role="dialog" aria-labelledby="myModalLabel3" aria-hidden="false">
								<div class="modal-dialog">
									<div class="modal-content">
										
										<div class="modal-body">
											<p>
												<img src="assets/images/loader1.gif" align="top" style="margin-left:100px;"/> 
											</p>
											<p style='text-align:center'>Please Wait........</p>
										</div>
										
									</div>
								</div>
							</div>
		
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
					CWS AND DWS REPORT
				</h3>
			</div>
		</div>
		<div class="row">
	
			<div class="col-md-12">
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i> CWS and DWS Report 
						</div>
					</div>
					<div class="portlet-body form">
<!-- 						<form action="saveEditScheduleMappingAction,saveScheduleMappingActionWebServiceCall,AjaxDailyScheduleMapping" class="form-horizontal form-row-seperated" method="post"> -->
                     <form action="AjaxDailyScheduleMapping" class="form-horizontal form-row-seperated" method="post" name="f1" id="form1"> 
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
												onchange="getDepotList(this.value)" headerKey="-1"
												headerValue="Select"></s:select>

										</div>
									</div>

									<div class="form-group" id="depotid">
										<label class="col-md-3 control-label">Depot<font
											color="red">*</font></label>
										<div class="col-md-3">
											<select list="depotlist" id="depotlist1"
												class="select2_category form-control" name="depotlist1" >
												<option value="-1">select</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Reason Type<font
											color="red">*</font></label>
										<div class="col-md-3">
											<select  id="reasontype"
												class="select2_category form-control" name="reasontype" >
												<option value="0">select</option>
												<option value="CW">CWS</option>
												<option value="DW">DWS</option>
												
											</select>
										</div>
									</div>
								
									<div class="form-group">
											<label class="control-label col-md-3">From Date :<font
											color="red">*</font></label>
								<div class="col-md-4">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" ><!-- data-date-start-date="+0d"> -->
								<input id="startdate" class="form-control" type="text" readonly="" size="16" name="startdate"
								
								>
								<span class="input-group-btn">
								<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
								</button>
								</span>
								<script>
										var curr_date=new Date().toJSON().slice(0,10);
                                        curr_date=curr_date.split("-");
                                        curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
                                        var dtval=document.getElementById('startdate').value;	
                                        
                                        if(dtval==''){
                                        $('#startdate').val(curr_date);
                                        }
								</script>
								</div>
								</div>
									</div>
									
									
									<div class="form-group">
											<label class="control-label col-md-3">To Date :<font
											color="red">*</font></label>
								<div class="col-md-4">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" ><!-- data-date-start-date="+0d"> -->
								<input id="enddate" class="form-control" type="text" readonly="" size="16" name="enddate"
								
								>
								<span class="input-group-btn">
								<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
								</button>
								</span>
								<script>
										var curr_date=new Date().toJSON().slice(0,10);
                                        curr_date=curr_date.split("-");
                                        curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
                                        var dtval=document.getElementById('enddate').value;	
                                        
                                        if(dtval==''){
                                        $('#enddate').val(curr_date);
                                        }
								</script>
								</div>
								</div>
									</div>
									
									

									
									
								
									<div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9">
											<button type="button" class="btn blue" onclick="check()">Submit</button>
<!-- 											<button type="button" class="btn default"	onclick="callCancell()">Cancel</button> -->
										</div>
									</div>
								</div>
							</div>
						</form>
						<div id="ticketconsuptionid"></div>
					</div>
					
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	function callCancell() {

		window.location.href = 'getDailyScheduleMapping.action';
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
