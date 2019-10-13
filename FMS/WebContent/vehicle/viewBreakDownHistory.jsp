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
accessrights=accessrightdao.accessRightsdetails(usrsessionid,"breakdownhistory.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();

String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
%>	
  <html>
<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
<script>

</script>
<style>parseInt
h1.intro {
	color: green; 
	font-size: 12px;
}
</style>
</head>
 <form>
<div class="page-content-wrapper">
		<div class="page-content">
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div class="modal fade" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">Break Down History</h4>
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
					BREAKDOWN  HISTORY<small></small>
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
								<i class="fa fa-globe"></i>View Breakdown History Data 
							</div>
							<div class="actions">
								<%if(create.equalsIgnoreCase("Y")){ %>
							<a href="#" class="btn green"  id="createShift" onclick="createBreakDownHistory()" >
								<i class="fa fa-plus"></i> Create </a>
							<%}%>
							<%if(edit.equalsIgnoreCase("Y")){ %>
								<a href="#" class="btn blue"  id="editShift" onclick="editBreakDownHistory()">
								<i class="fa fa-pencil"></i> Edit </a>
								<%}%>
								<%if(delete.equalsIgnoreCase("Y")){ %>
								<a href="#" class="btn red" id="deleteShiftType" onclick="deleteBreakDownHistory()">
									<i class="fa fa-pencil"></i> Delete
								</a>
									<%}%>
							
								
							</div>
						</div>
						
						<div class="portlet-body">
						
						
						<input type="hidden" name="requestType" id="requestType" value="text"/>
							<table class="table table-striped table-bordered table-hover" id="viewBreakDownHistory">
				            <font color="green" size="2px"><s:property value="msg" /></font>
							<thead>
							<tr>
								<th/>
								<th> 
									Breakdown Id
								</th>
								
						 		<th>
									Date
								</th> 
								<th>
									 Vehicle Id
								</th>
								<th>
									 Type
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
	<script>
	function deleteBreakDownHistory() {
		
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
			document.forms[0].action = 'deleteBreakDownHistory.action?breakdownid='+ val;
		    document.forms[0].submit();
						}
					});
			}else{
				bootbox.alert("Please Select Valid Record");
			}

								//alert("hello");
								
								/* var val = $("input[type='checkbox']").val(); */
								//alert(val);
								

							}

						
	}
	function editBreakDownHistory(){
	
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
					if(isEligibleForOpertion(val)){
					document.forms[0].action = 'editBreakDownHistory.action?breakdownid='+ val;
					document.forms[0].submit();
					}else{
						bootbox.alert("Please Select Valid Record");
					}


				}

			
	}
	/* function editShiftType()
	 {
		document.forms[0].action = 'showShiftType.action';
														document.forms[0].submit();
	 } */
	 
	 function createBreakDownHistory()
	 {
		 document.forms[0].action = "createBreakDownHistory.action";
		 document.forms[0].submit();
	 }
	 
	 function isEligibleForOpertion(id){
		 var isEligible = $('#isRocordEligible'+id).val();
		 if(isEligible == undefined || isEligible=='Y'){
			 return true;
		 }else{
			 return false;
		 }
	}
	</script>
	
	
	<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>