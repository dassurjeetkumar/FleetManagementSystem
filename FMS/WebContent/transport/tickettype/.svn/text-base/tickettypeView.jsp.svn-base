
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "TicketTypeAction!view");
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
			<div class="modal fade" id="portlet-config" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true"></button>
							<h4 class="modal-title"></h4>
						</div>
						<div class="modal-body"></div>
						<div class="modal-footer">
							<button type="button" class="btn blue">Save changes</button>
							<button type="button" class="btn default" data-dismiss="modal">Close</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>

			<!-- END STYLE CUSTOMIZER -->
			<!-- BEGIN PAGE HEADER-->
<%if (access.equalsIgnoreCase("Y")){%>
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						TICKET TYPE <small></small>
					</h3>
					<%-- <font color="green"><s:actionmessage/></font> --%>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->

			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>View Ticket Type
							</div>
							<div class="actions">
								<%if(create.equalsIgnoreCase("Y")){ %>
								<a href="#" class="btn green" id="add"> 
								<i class="fa fa-plus"></i> Create
								</a> 
								<%}%>
									<%if(edit.equalsIgnoreCase("Y")){ %>
								<a href="#" class="btn blue" id="edit_"> 
								<i class="fa fa-pencil"></i>Edit
								</a> 
									<%}%>
							  <%if(delete.equalsIgnoreCase("Y")){%>
								<a href="#" class="btn red" id="delete_"> 
								<i class="fa fa-times"></i>Delete
								</a>
								<%}%>
								<div class="btn-group">
								
									<button class="btn blue dropdown-toggle" data-toggle="dropdown"><i class="fa fa-gears"></i> Tools <i class="fa fa-angle-down"></i>
									</button>
									<ul class="dropdown-menu pull-right ">
										<li>
										<%if(status.equalsIgnoreCase("ACTIVE")){ %>
											<a class="btn blue" href="javascript: void(0)" onclick="window.open('','_new').location.href='/its/generateReport?id=TICKETTYPERPT'">
											<!-- href="/its/ReportAction!generateReport?id=1"> -->
											Report </a><%} %>
										</li>	
										
																			
									</ul> 
							   </div>						
							</div>

							</div>
						

						<div class="portlet-body">
						<font color="green"><s:actionmessage/></font>
							<table class="table table-striped table-bordered table-hover"
								id="ticketType">
								<thead>
									<tr>
										<!-- <th style="width1: 8px;"></th> -->
										<th></th>
										<th>Ticket Type Id</th>
										<th>Ticket Type Name</th>
										<th>Status</th>			
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
</div>
		<!-- END PAGE CONTENT-->
	
	
	<script>
		
			$('#add').click(	
			function callCreate() {
				document.forms[0].action = "TicketTypeAction!add";
				document.forms[0].submit();
			});
			
			$('#edit_').click(
			function() {
				var cnt = $(":checkbox:checked").length;
				var val;
				if (cnt == 0) {
					bootbox.alert("Please select checkbox to edit");
				} else if (cnt > 1) {
					bootbox.alert("Please select one checkbox to edit");
				} else {
					$("input[type='checkbox']:checked").each(
						function() {
						val = this.value;
						});
				
				/* document.forms[0].action = 'TicketTypeAction!edit?id='+ val;
				document.forms[0].submit(); */
				if(isEligibleForOpertion(val)){
					document.getElementById("eid").value = val;
					document.getElementById("form2").submit();
				}else{
					bootbox.alert("Please Select Valid Record");
				}
				}
			});
	         				
			$('#delete_').click(
			function() {
				var cnt = $(":checkbox:checked").length;
				var val;
				if (cnt == 0) {
					bootbox.alert("Please select checkbox to delete");
				} else if (cnt > 1) {
					bootbox.alert("Please select one checkbox to delete");
				} else {
					$("input[type='checkbox']:checked").each(
							function() {

								val = this.value;
								type = 'text';
							});
					if(isEligibleForOpertion(val)){
					bootbox.confirm("Are you Sure, you want to delete this record?",
					function(result) {
					if (result == true) {
					
					/* document.forms[0].action = 'DeleteTicketTypeAction?id='+ val;
					document.forms[0].submit(); */
						document.getElementById("did").value = val;
						document.getElementById("form1").submit();
				   }
				});
					}else{
						bootbox.alert("Please Select Valid Record");
					}
				}
			});
	
			$(document).ready(function() {

				window.history.pushState("", "", "TicketTypeAction!view");
				
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
</form>
<form name="form1" id="form1" action="DeleteTicketTypeAction" method="POST">
 	<input type="hidden" name="id" id="did" value="" /> 

 </form>
<form name="form2" id="form2" action="TicketTypeAction!edit" method="POST">
	<input type="hidden" name="id" id="eid" value="" />

</form>

<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>

