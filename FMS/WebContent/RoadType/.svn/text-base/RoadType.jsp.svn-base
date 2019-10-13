  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid,"roadType.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String status=accessrightdao.UserStatus(usrsessionid);
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord"); 
%>	
	
	
	<html>
	<head>
	<script>
	function deleteRoadType() {
	
							var cnt = $(":checkbox:checked").length;
							var val;
							if (cnt == 0) {
								bootbox
										.alert("Please Select Checkbox To Delete");
							} else if (cnt > 1) {
								bootbox
										.alert("Please Select One Checkbox To Delete");
							} else {
								$("input[type='checkbox']:checked").each(
										function() {

											val = this.value;
													});
								if(isEligibleForOpertion(val)){
								bootbox.confirm("Are you sure you want to delete?",
										function(result)
										{

											if (result == true) 
											{
								document.forms[0].action = 'deleteRoadType.action?roadid='+ val;
							    document.forms[0].submit();
											}
										});
								//alert("hello");
								
								/* var val = $("input[type='checkbox']").val(); */
								//alert(val);
								}else{
									bootbox.alert("Please Select Valid Record");
								}

							}

						
	}
	function editRoadType(){
	
				var cnt = $(":checkbox:checked").length;
				var val;
				if (cnt == 0) {
					bootbox.alert("Please Select Checkbox To Edit");
				} else if (cnt > 1) {
					bootbox
							.alert("Please Select One Checkbox To Edit");
				} else {
					$("input[type='checkbox']:checked").each(
							function() {

								val = this.value;
							});
					/* var val = $("input[type='checkbox']").val(); */
					//alert(val);
					if(isEligibleForOpertion(val)){
					document.forms[0].action = 'editRoadType.action?road_type_id='+ val;
					document.forms[0].submit();
					}else{
						bootbox.alert("Please Select Valid Record");
					}

				}

			
	}
	/* function editShiftType()
	 {
		 document.forms[0].action = "editShiftType.action";
		 document.forms[0].submit();
	 } */
	 function callCreate()
	 {
		 document.forms[0].action = "createRoadType.action";
		 document.forms[0].submit();
	 }
	 /* function show()
	 {
		 document.forms[0].action = "#";
		 document.forms[0].submit();
	 }
	  */
	  $(document).ready(function() {
		   window.history.pushState("","", "roadType.action");
		 });
	  
	  function isEligibleForOpertion(id){
			 var isEligible = $('#isRocordEligible'+id).val();
			 if(isEligible == undefined || isEligible=='Y'){
				 return true;
			 }else{
				 return false;
			 }
		}
	</script>
	
	
  <style>
h1.intro {
	color: red;
	font-size: 14px;
}
</style>
</head>
<body>
 <form>
<div class="page-content-wrapper">
		<div class="page-content">
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div class="modal fade" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">Road Type</h4>
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
			<%if (access.equalsIgnoreCase("Y")){ %>
			<!-- END STYLE CUSTOMIZER -->
			<!-- BEGIN PAGE HEADER-->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
					Road Type  <small></small>
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
								<i class="fa fa-globe"></i>Road Type
							</div>
							<div class="actions">
							<%if(create.equalsIgnoreCase("Y")){ %>
							<a href="#" class="btn green"  id="createRoad" onclick="callCreate()" >
								<i class="fa fa-plus"></i> Create </a>
							<%}%>
							<%if(edit.equalsIgnoreCase("Y")){ %>
								<a href="#" class="btn blue"  id="editRoad" onclick="editRoadType()">
								<i class="fa fa-pencil"></i> Edit </a>
									<%}%>
								<%if(delete.equalsIgnoreCase("Y")){ %>
								 <a href="#" class="btn red" id="deleteRoadType" onclick="deleteRoadType()">
									<i class="fa fa-pencil"></i> Delete
									
								</a>
								<%}%>
							
								  <div class="btn-group">
								
									<button class="btn green dropdown-toggle" data-toggle="dropdown"><i class="fa fa-gears"></i> Tools <i class="fa fa-angle-down"></i>
									</button>
									<ul class="dropdown-menu pull-right ">
									<%if(status.equalsIgnoreCase("ACTIVE")){ %>
										<li>
											<a class="btn blue" href="javascript: void(0)" onclick="window.open('','_new').location.href='/its/generateReport?id=ROADTYPERPT'">
											<!-- href="/its/ReportAction!generateReport?id=1"> -->
											Report </a>
										</li>	
										<%} %>
																			
									</ul> 
							</div>
								
							
								
							</div>
						</div>
						
						<div class="portlet-body">
						
						
						<input type="hidden" name="requestType" id="requestType" value="text"/>
						<font color="green" size="2px"><s:property value="msg" /></font>
							<table class="table table-striped table-bordered table-hover" id="addRoadType">
							
							<thead>
							<tr>
								<th style="width1:8px;">
									
								</th>
								<th>
									 Road Type Id
								</th>
								
						 		<th>
									 Road Type Name
								</th>
								<th>
									 Remarks
								</th>
								<th>
									 Status						
							     </th>
								<%if(viewdeletedrecord.equalsIgnoreCase("Y")) {%>	
													<th>Record Status</th>
													<%}%>
								 
								
							</tr>
							</thead>
	
							</table>
							
							</div>
						</div>
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
			</div>
			
			<!-- END PAGE CONTENT-->
		</div>
	
	
	</form>
	<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>
	
	</body></html>
	