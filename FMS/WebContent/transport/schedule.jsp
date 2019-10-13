
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  
<html>
<head>
<script>
$(document).ready(function() {
	var orgtypeid_id=$("#orgtypeid_id").val();
	//$("#targetamounteditid").hide();
	//alert(orgtypeid_id);
	/*  if(orgtypeid_id==3)
		{
			//alert("orgtypeid_id111"+orgtypeid_id);
		$("#targetamounteditid").show();
		//$("#depotid").attr("style", "display:none");
		
		}  */
	//$('#targetamounteditid').hide();
	   window.history.pushState("","", "ShowScheduleDetails.action");
	 });
	 

function edit(){
	document.forms[0].action = 'EditSchedule.action';
	 document.forms[0].submit(); 
}
</script>

<script>
		function callEdit(){
			
			var cnt = $(":checkbox:checked").length;
			var val;
			if (cnt == 0) {
				bootbox.alert("Please Select Checkbox To Edit");
			} else if (cnt > 1) {
				bootbox.alert("Please Select One Checkbox To Edit");
			} else {
				$("input[type='checkbox']:checked").each(
						function() {
							id = this.id;
							val = this.value;
						});
				status = document.getElementById('status'+id).value;
				if(status=='Rationalized'){
					bootbox.alert("Selected schedule is already rationalised");
				}
				else{
				document.forms[0].action = "EditSchedule.action?sch="+ val;
				document.forms[0].submit();
				}
			

						
		}
		}
	</script>
	<script>
		function callTagetAmountEdit(){
			
			var cnt = $(":checkbox:checked").length;
			var val;
			if (cnt == 0) {
				bootbox.alert("Please Select Checkbox To Edit");
			} else if (cnt > 1) {
				bootbox.alert("Please Select One Checkbox To Edit");
			} else {
				$("input[type='checkbox']:checked").each(
						function() {
							id = this.id;
							val = this.value;
						});
				
				document.forms[0].action = "EditTargetAmount.action?sch="+ val;
				document.forms[0].submit();
				
			

						
		}
		}
		</script>
		<script>
		
		
		function serviceLimit(){
			
			var cnt = $(":checkbox:checked").length;
			var val;
			if (cnt == 0) {
				bootbox.alert("Please Select Checkbox To Enter Schedule Service Limit");
			} else if (cnt > 1) {
				bootbox.alert("Please Select One Checkbox To Edit");
			} else {
				$("input[type='checkbox']:checked").each(
						function() {
							id = this.id;
							val = this.value;
						});
				
				document.forms[0].action = "ScheduleServiceLimit.action?sch="+ val;
				document.forms[0].submit();
				
			

						
		}
		}
		
		
	</script>
	<script>
		function callCreateFormFour(){
			
			var cnt = $(":checkbox:checked").length;
			var val;
			if (cnt > 1) {
				bootbox.alert("Please Select One Checkbox To Create Form Four");
			}
			else
			{
				$("input[type='checkbox']:checked").each(
						function() {

							val = this.value;
						});
				document.forms[0].action = "FormFour.action?sch="+ val;
				document.forms[0].submit();
			}
		}
	</script>
	
	<script>
		function callviewformfour(){
			
			var cnt = $(":checkbox:checked").length;
			var val;
			if (cnt == 0) {
				bootbox.alert("Please Select Checkbox To View Related Form Four");
			} else if (cnt > 1) {
				bootbox.alert("Please Select One Checkbox To View Related Form Four");
			} else {
				$("input[type='checkbox']:checked").each(
						function() {

							val = this.value;
						});
				document.forms[0].action = "FormFourView.action?sch="+ val;
				document.forms[0].submit();
			

						
		}
		}
	</script>
	
	<script>
		function callCopy(){
			
			var cnt = $(":checkbox:checked").length;
			var val;
			if (cnt == 0) {
				bootbox.alert("Please Select Checkbox To Copy");
			} else if (cnt > 1) {
				bootbox.alert("Please Select One Checkbox To Copy");
			} else {
				$("input[type='checkbox']:checked").each(
						function() {

							val = this.value;
						});
				document.forms[0].action = "CopySchedule.action?sch="+ val;
				document.forms[0].submit();
			

						
		}
		}
	</script>
	
	<script>
		function callCurtail(){
			
			var cnt = $(":checkbox:checked").length;
			var id;
			var val;
			var effstartdate;
			if (cnt == 0) {
				bootbox.alert("Please Select Checkbox To Curtail");
			} else if (cnt > 1) {
				bootbox.alert("Please Select One Checkbox To Curtail");
			} else {
				$("input[type='checkbox']:checked").each(
						function() {
							id = this.id;
							val = this.value;
						});
				effstartdate = document.getElementById('effstartdate'+id).value;
				if(effstartdate=='-'){
					bootbox.alert("Effective Start Date For Schedule Is Mandatory To Curtail");
				}
				else{
				document.forms[0].action = "CurtailSchedule.action?sch="+ val;
				document.forms[0].submit();
				}
			

						
		}
		}
		
		
	</script>
<script type="text/javascript">

function callRationalizedSchedule(){
	
		document.forms[0].action = 'RationalizedSchedule.action';
		document.forms[0].submit();
}
function callRationalizedFormIV(){
	
	document.forms[0].action = 'RationalizedFormFour.action';
	document.forms[0].submit();
}
function callPartialFormIV(){
	
	document.forms[0].action = 'PartialFormFour.action';
	document.forms[0].submit();
}
</script>
</head>
<body>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
	<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ShowScheduleDetails.action");
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
 <form>
 
<div class="page-content-wrapper">
		<div class="page-content">
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div class="modal fade" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">Modal title</h4>
						</div>
						<div class="modal-body">

							 Widget settings form goes here
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue">Save changes</button>
							<button type="button" class="btn default" data-dismiss="modal">Close</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<%if (access.equalsIgnoreCase("Y")){%>
			<!-- END STYLE CUSTOMIZER -->
			<!-- BEGIN PAGE HEADER-->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
					 SCHEDULE
					</h3>
					
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			
			<%-- //<s:hidden name="busid" id="id" ></s:hidden> --%>
			 <!-- <input type="hidden" name="busid" id="busid" value="22181"/> -->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>View Schedule
							</div>
							<div class="actions">
							<div class="btn-group">
								<%if(create.equalsIgnoreCase("Y")){ %>
								<a href="CreateSchedule.action" class="btn green"  id="createschedule"> <!-- //onclick="callEdit()" -->
								<i class="fa fa-plus-square "></i> Create Schedule</a>
								
								<!-- <a href="FormFour.action" class="btn green"  id="createform4"> //onclick="callEdit()"
								<i class="fa fa-plus-square "></i> Create Form Four</a> -->
								<%}%>
								<%if(edit.equalsIgnoreCase("Y")){ %>
								<button type="button" class="btn green" onclick="callCreateFormFour();"><i class="fa fa-plus-square"></i>&nbsp;Create Form Four</button>
								<button type="button" class="btn blue" onclick="callEdit();"><i class="fa fa-pencil"></i>&nbsp;Rationalization(Edit)</button>
<!-- 								<button type="button" class="btn blue" id="targetamounteditid" onclick="callTagetAmountEdit();"><i class="fa fa-plus-square"></i>&nbsp;Edit Target Amount</button> -->
								<%}%>
								<%if(read.equalsIgnoreCase("Y")){ %>
								<button type="button" class="btn blue" onclick="callviewformfour();"><i class="fa fa-eye"></i>&nbsp;View Related Form IV`s</button>
								<!-- <button type="button" class="btn blue" onclick="callCopy();"><i class="fa fa-pencil"></i>&nbsp;Copy</button> -->
								<%} %>	
								<%if(create.equalsIgnoreCase("Y")){ %>						
								<a class="btn default" href="#" data-toggle="dropdown">
									Tools <i class="fa fa-angle-down"></i>
									</a>
									<div id="sample_4_column_toggler" class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">
									<label><button type="button" class="btn blue" onclick="callCopy();"><i class="fa fa-pencil"></i>&nbsp;Copy</button></label>
									<label><button type="button" class="btn blue" onclick="callCurtail();"><i class="fa fa-pencil"></i>&nbsp;Curtailment</button></label>
									<label><button type="button" class="btn blue" onclick="callRationalizedSchedule();"><i class="fa fa-eye"></i>&nbsp;View Rationalized Schedule</button></label>
								   <!--  <label><button type="button" class="btn blue" onclick="callRationalizedFormIV();"><i class="fa fa-eye"></i>&nbsp;View Rationalized Form IV</button></label> -->
								    <label><button type="button" class="btn blue" onclick="callPartialFormIV();"><i class="fa fa-eye"></i>&nbsp;View Partial Form IV</button>
								    <button type="button" class="btn blue" id="limit" onclick="serviceLimit();"><i class="fa fa-plus-square"></i>&nbsp;Service Limit</button>
								    
								</label>
						
							</div>
							<%} %>
									</div>
							</div>
							
						</div>
						<input type="hidden" name="id" value="201"/>
						<div class="portlet-body">
						
						<input type="hidden" name="formtype" value="schedule">
						<input type="hidden" name="requestType" id="requestType" value="text"/>
						<input type="hidden" name="orgtypeid" id="orgtypeid_id" value="<s:property value="orgtypeid"/>"/>
						<input type="hidden" name="orgchartid" id="org_id" value="<s:property value="orgchartid"/>"/>
						<b>
							<font color="green"> <s:actionmessage/></font></b>
							<table class="table table-striped table-bordered table-hover" id="scheduletable">
							<thead>
							<tr>
								<th >
									
								</th>
								<th>
									 Schedule Id 
								</th>
								<th>
									 Schedule No.
								</th>
								<th>
									 Brand Type
								</th>
								<th>
									 Effective Start Date
								</th>
								<th>
									 Effective End Date
								</th>
								<th>
									 Depot Code
								</th>
								<!-- <th>
									 Route No.
								</th>
								<th>
									 Origin
								</th>
								<th>
									 Destination
								</th> -->
								<th>
									Service Type
								</th>
								<th>
									 Trunk Schedule
								</th>
								 <th>
									Target Amount
								</th> 
								 <th>
									Service Limit
								</th> 
								
								<th>
									 Schedule Type
								</th>
								<th>
									 Conductor
								</th>
								<th>
									 Driver
								</th>
								<th>
									 Status
								</th>
								
								
							</tr>
							</thead>
							<%-- <tbody>
							<s:iterator value="list" id="list">
    <tr>
    <th style="width1:8px;">
									<input type="checkbox" class="group-checkable" data-set="#sample_2 .checkboxes" id='<s:property value="id"/>' value='<s:property value="id"/>'/>
								</th>
    <td align="center"><s:property value="id"/></td>
    <td align="center"><s:property<s:if test="hasActionErrors()">
   <div class="alert alert-danger">
			<button class="close" data-close="alert"></button>
			<span>
			<s:actionerror/> </span>
		</div>
      
   
</s:if> value="busStopName"/></td>
    <td align="center"><s:property value="bus_stop_code"/></td> 
    
    <td align="center"><s:property value="busStopNameKannada"/></td>
    <td align="center"><s:property value="bus_stop_code_kannada"/></td>
    <td align="center"><s:property value="description"/></td> 
    
    <td align="center"><s:property value="fareStage"/></td>
    <td align="center"><s:property value="sheltered"/></td>
    <td align="center"><s:property value="alias1"/></td> 
    
    <td align="center"><s:property value="alias2"/></td>
    <td align="center"><s:property value="alias3"/></td>
    <td align="center"><s:property value="alias4"/></td> 
    
     <td align="center"><s:property value="kalias1"/></td>
    <td align="center"><s:property value="k_alias_2"/></td> 
    
    <td align="center"><s:property value="locality"/></td>
    <td align="center"><s:property value="landmark"/></td>
    <td align="center"><s:property value="status"/></td>  
    
    <td align="center"><s:property value="deviceCode"/></td>
   
    
    <td align="center"><s:property value="tollZone"/></td>
    <td align="center"><s:property value="areaPopulation"/></td>
    <td align="center"><s:property value="cityLimit"/></td> 
    
    <td align="center"><s:property value="wardNumber"/></td>
    <td align="center"><s:property value="stopSize"/></td>
        
    
    </tr>
    </s:iterator>
							</tbody>  --%>
							</table>
							
							</div>
						</div>
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
				
						
			<!-- END PAGE CONTENT-->
			
		</div>
	</div>
	
	</form>
	
	<div id="responsive" class="modal fade" tabindex="-1" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
											<h4 class="modal-title">Schedule</h4>
										</div>
										<div class="modal-body">
											<div class="portlet-body form">
						<form class="form-horizontal" role="form"
							action="AddEditedBusStopMap.action" method="post">
							<input type="hidden" name="bustops.id" id="busid1" value="" /> <input
								type="hidden" name="requestType" id="requestType" value="map" />
							<div class="form-body">
								
								<div class="form-group">
									<label class="col-md-3 control-label">Schedule Number </label>
									<div class="col-md-4">
									<input class="form-control input-sm"
											placeholder="" type="text" id="alias1"
											name="bustops.alias1" maxlength="80">
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-3 control-label">Schedule Type </label>
									<div class="col-md-4">
									<input class="form-control input-sm"
											placeholder="" type="text" id="alias1"
											name="bustops.alias1" maxlength="80">
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-3 control-label">Service Type </label>
									<div class="col-md-4">
									<input class="form-control input-sm"
											placeholder="" type="text" id="alias1"
											name="bustops.alias1" maxlength="80">
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-3 control-label">Remarks</label>
									<div class="col-md-4">
									<input class="form-control input-sm"
											placeholder="" type="text" id="alias1"
											name="bustops.alias1" maxlength="80">
									</div>
								</div>
						
							</div>
							<div class="form-actions right1">
							<button type="button" class="btn green" onclick="submitAjaxForm()">Send For Approval</button>
								<button aria-hidden="true" data-dismiss="modal" class="btn default">Cancel</button>
								
							</div>
						</form>
					</div>
				</div>
				
			</div>
										</div>
									
									</div>
				<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>
								
	</body>
	</html>