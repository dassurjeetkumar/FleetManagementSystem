<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Schedule Related Form IV</title>

<script type="text/javascript">
function cancel(){
	document.forms[0].action = 'ShowScheduleDetails.action';
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

							val = this.value;
						});
				status = document.getElementById("status_rat").value;
				if(status=='Rationalized'){
					bootbox.alert("Selected Form four schedule is already rationalised");
				}
				else{
				var formfourtype = document.getElementById("formfourtype").value;
				document.forms[0].action = "EditFormFour.action?formfourtype="+formfourtype+"&formfour="+ val;
				document.forms[0].submit();
				}
			

						
		}
		}
	</script>
	
	<script>
		function callDelete(){
			
			var cnt = $(":checkbox:checked").length;
			var val;
			if (cnt == 0) {
				bootbox.alert("Please Select Checkbox To Delete");
			} else if (cnt > 1) {
				bootbox.alert("Please Select One Checkbox To Delete");
			} else {
				status = document.getElementById("status_rat").value;
				//alert("rationalized_status"+status);
				if(status=='Rationalized'){
					bootbox.alert("Selected Form Four schedule is already rationalised");
				}
				else{
				bootbox.confirm("Are You Sure You Want To Delete This Form Four?",
						function(result) {

							if (result == true) {
								$("input[type='checkbox']:checked").each(
										function() {
											val = this.value;
										});
								
								 $.ajax({
									    type : 'POST',
									    data: {
									        id: val
									       },
									    url :  "<s:url action='DeleteFormFour'/>",
									   
									    success: function(data){
									    	var schid = document.getElementById('schid').value;
									    	
									    	 bootbox.alert("Form Four id "+val+" deleted successfully. Please wait for page to refresh", function() {
									    		 document.forms[0].action = "FormFourView.action?sch="+ schid;
													document.forms[0].submit(); 
								                });  
											
									    	}
									});
							}
				});
				
				
			}
			}
		}
	</script>
	<script>
		function callEditTrips(){
			
			var cnt = $(":checkbox:checked").length;
			var val;
			if (cnt == 0) {
				bootbox.alert("Please Select Checkbox To Edit Trips");
			} else if (cnt > 1) {
				bootbox.alert("Please Select One Checkbox To Edit Trips");
			} else {
				$("input[type='checkbox']:checked").each(
						function() {

							val = this.value;
						});
				
				document.forms[0].action = "EditTrips.action?formfour="+val;
				document.forms[0].submit();
			

						
		}
		}
	</script>
	
	<script>
		function callView(){
			
			var cnt = $(":checkbox:checked").length;
			var val;
			if (cnt == 0) {
				bootbox.alert("Please Select Checkbox To View Trips");
			} else if (cnt > 1) {
				bootbox.alert("Please Select One Checkbox To View Trips");
			} else {
				$("input[type='checkbox']:checked").each(
						function() {

							val = this.value;
						});
				
				document.forms[0].action = "TripView.action?id="+val;
				document.forms[0].submit();
			

						
		}
		}
		
		
		function callActivate(){
			var cnt = $(":checkbox:checked").length;
			var val;
			if (cnt == 0) {
				bootbox.alert("Please Select Checkbox To Activate");
			} else if (cnt > 1) {
				bootbox.alert("Please Select One Checkbox To Activate");
			} else {
				status = document.getElementById("status_rat").value;
				//check code rationalize edit condition (edit by rajesh)
				if(status=='Rationalized'){
					bootbox.alert("Selected Form Four schedule is already rationalised");
				}
				else{
				
				$("input[type='checkbox']:checked").each(
						function() {

							val = this.value;
						});
				
				var status = document.getElementById('status'+val).value;
				
				if(status == 'Partial'){
				bootbox.confirm("Are You Sure You Want To Activate This Form Four?",
						function(result) {

							if (result == true) {
								$("input[type='checkbox']:checked").each(
										function() {
											val = this.value;
										});
								
								
								document.forms[0].action = "ActivateFormFour.action?id="+val;
								document.forms[0].submit();
								
								
							
							}
				
					
				});
				}
				else{
					bootbox.alert("Form four is already activated");
				}
				
		}
			}
		}
		
		function callRationalizedFormIV(){
			
			document.forms[0].action = 'RationalizedFormFour.action';
			document.forms[0].submit();
		}
	</script>


</head>
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
					FORM FOUR  <small></small>
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
								<i class="fa fa-globe"></i>View Form Four
							</div>
							<div class="actions">
							    <%if(create.equalsIgnoreCase("Y")){ %>
							    <a href="#" class="btn blue"  onclick="callRationalizedFormIV()"> 
								<i class="fa fa-eye"></i>&nbsp; View Rationalized Form IV </a>
								<%} %>
								   <%if(read.equalsIgnoreCase("Y")){ %>
								<a href="#" class="btn blue"  onclick="callView()"> 
								<i class="fa fa-eye"></i>&nbsp; View Trips </a>
								<%} %>
								<%if(edit.equalsIgnoreCase("Y")){ %>
								<a href="#" class="btn blue"  onclick="callActivate()"> 
								<i class="fa fa-eye"></i>&nbsp; Activate </a>
								
								 <a href="#" class="btn blue"  onclick="callEdit()"> 
								<i class="fa fa-pencil"></i> Edit Form IV </a>
								<%} %>
									<%if(delete.equalsIgnoreCase("Y")){ %>
								 <a href="#" class="btn blue"  onclick="callDelete()"> 
								<i class="fa fa-pencil"></i> Delete Form IV </a>
								 <!-- <a href="#" class="btn blue"  onclick="callEditTrips()"> 
								<i class="fa fa-pencil"></i> Edit Trips</a>  -->
								<%} %>
			
								<button type="button" class="btn default" onclick="cancel();">Cancel</button>
								
							</div>
						</div>
						
						<div class="portlet-body">
						
						<%if(session.getAttribute("activatemessage") != null) {%>
						<b><font color="green"><%=session.getAttribute("activatemessage") %></font></b>
						<%} %>
						<b><font color="green"> <s:actionmessage/></font></b>
						<div>
						<input type="hidden" name="" id="status_rat" value=<s:property value="schedule.status"/>> 
		
							<table class="table table-striped table-bordered table-hover" id="formFourRelatedtable">
							<thead>
							
							<tr>
								<th style="width:8px;">
									<input type="hidden" name="id" id="schid" value='<s:property value="id"/> '>
								</th>
								<th>
									 Form Four Id
								</th>
								<th>
									 Form Four Name
								</th>
								<th>
									 Schedule Number
								</th>
								<th>
									 Schedule Number Name
								</th>
								
								<th>
									No. Of Trips
								</th>
								<th>
									 Route No
								</th>
							 	
								<th>
									 Start Time
								</th> 
								<th>
									Effective Start date
								</th>
								<th>
									Effective End Date
								</th>
								
								<th>
									Current Status
								</th>
								<th>
									 Remarks
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
    <td align="center"><s:property value="busStopName"/></td>
    <td align="center"><s:property value="busStopNameKannada"/></td>
    
    <td align="center"><s:property value="bus_stop_code"/></td> 
    <td align="center"><s:property value="bus_stop_code_kannada"/></td>
    
    <td align="center"><s:property value="locality"/></td>
    <td align="center"><s:property value="landmark"/></td>
    
    <td align="center"><s:property value="sheltered"/></td>
    
    <td align="center"><s:property value="alias1"/></td> 
    <td align="center"><s:property value="alias2"/></td>
    <td align="center"><s:property value="alias3"/></td>
    <td align="center"><s:property value="alias4"/></td> 
    <td align="center"><s:property value="alias5"/></td>
    <td align="center"><s:property value="alias6"/></td>
    
    <td align="center"><s:property value="kalias1"/></td>
    <td align="center"><s:property value="k_alias_2"/></td>
    
    <td align="center"><s:property value="tollZone"/></td>
    <td align="center"><s:property value="cityLimit"/></td> 
    
    <td align="center"><s:property value="wardNumber"/></td>
    <td align="center"><s:property value="areaPopulation"/></td>
    
    <td align="center"><s:property value="stop_direction"/></td>
    
    
    <td align="center"><s:property value="stopSize"/></td>
    <td align="center"><s:property value="fareStage"/></td>
    <td align="center"><s:property value="description"/></td> 
    <td align="center"><s:property value="status"/></td>  
  
    </tr>
    </s:iterator>
							</tbody>  --%>
							</table>
							
							</div>
						</div>
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
			</div>
			
			<!-- END PAGE CONTENT-->
		</div>
	</div>
	<input type="hidden" name="formtype" id = "formfourtype"value="editformfour">
	<%session.removeAttribute("activatemessage"); %>
	
	<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>
						
	</form>
</html>