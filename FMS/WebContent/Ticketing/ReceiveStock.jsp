
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
	<body>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"
	type="text/javascript"></script>

<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.min.js"
	type="text/javascript"></script>
	<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "receive.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
%>
<form action="saveTicketReturn.action">
	<div class="page-content-wrapper">
	<%if (access.equalsIgnoreCase("Y")){%>
		<div class="page-content">
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div class="modal fade" id="portlet-config" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true"></button>
							<h4 class="modal-title">Modal title</h4>
						</div>
						<div class="modal-body">Widget settings form goes here</div>
						<div ctransferTicketlass="modal-footer">
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
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
					 TICKET INVENTORY <small></small>
					</h3>

					<!-- END PtransferTicketAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->

			<%-- //<s:hidden name="busid" id="id" ></s:hidden> --%>
			<!-- <input type="hidden" name="busidtransferTicket" id="busid" value="22181"/> -->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>Receive Ticket Inventory
							</div>
							
							
						<div class="actions">

                                  <%if (create.equalsIgnoreCase("Y")){%>
                                   
								<a href="#" class="btn blue" id="issueTicket" onclick="acceptTicket()"> <i class="fa fa-pencil"></i>
									Accept
								</a>
                                <a href="#" class="btn blue" id="issueTicket" onclick="rejectTicket()"> <i class="fa fa-pencil"></i>
									Reject
								</a>
                               <%} %>
							</div>
						</div>

						<div class="portlet-body" id="step1Content">
                

							<input type="hidden" name="requestType" id="requestType"
								value="text" />
							<table class="table table-striped table-bordered table-hover"
								id="receiveticketInv">
								<font color="green" size="3px"><b><s:property value="msg" /></b></font>
								<thead>
									<tr>
										 <th style="width1: 8px;"></th> 
										
										<th>Issue Id</th>
                                        <th>Voucher Number</th>
										
										<th>Issued from Organization Type</th>
										 <th>Issued from Organization Name</th>
										 <th>Date of Issuance</th> 
										<th>value</th> 
                                       
										
										
									</tr>
								</thead>

							</table>
<!--  <label style="left:410px;width:400px;">Total Number of Books:</label> <label style="left:610px;width:400px;">Total value:</label> -->
						</div>
										<!-- END EXAMPLE TABLE PORTLET-->
			</div>
			
		</div>
<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>
		<!-- END PAGE CONTENT-->
	</div>
	</div>
	<script>
	function acceptTicket() {
		/* var cnt = $(":checkbox:checked").length;
		var val;
		if (cnt == 0) {
			bootbox.alert("Please Select Checkbox To Issue");
		} else if (cnt > 1) {
			bootbox
					.alert("Please Select One Checkbox To Issue");
		} else {
			$("input[type='checkbox']:checked").each(
					function() {

						val = this.value;
					});	 */
			var chkArray = [];

			/* look for all checkboes that have a class 'chk' attached to it and check if it was checked */
			$(".group-checkable:checked").each(function() {
				chkArray.push($(this).val());
			});

			/* we join the array separated by the comma */
			var selected;
			selected = chkArray.join(',') + ",";
          //  alert(selected);
			/* check if there is selected checkboxes, by default the length is 1 as it contains one single comma */
			if (selected.length > 1) {
				//alert("You have selected " + selected);DEPOT
			} else {
				alert("Please at least one of the checkbox");
				return false;
			}
			document.forms[0].action = "acceptTicket.action?tick="+selected;
			document.forms[0].submit();
					
	
	}
		function rejectTicket() {
			/* var cnt = $(":checkbox:checked").length;
			var val;
			if (cnt == 0) {
				bootbox.alert("Please Select Checkbox To Issue");
			} else if (cnt > 1) {
				bootbox
						.alert("Please Select One Checkbox To Issue");
			} else {
				$("input[type='checkbox']:checked").each(
						function() {

							val = this.value;
						});	 */
				var chkArray = [];

				/* look for all checkboes that have a class 'chk' attached to it and check if it was checked */
				$(".group-checkable:checked").each(function() {
					chkArray.push($(this).val());
				});

				/* we join the array separated by the comma */
				var selected;
				selected = chkArray.join(',') + ",";
	           // alert(selected);
				/* check if there is selected checkboxes, by default the length is 1 as it contains one single comma */
				if (selected.length > 1) {
					//alert("You have selected " + selected);
				} else {
					alert("Please at least one of the checkbox");
					return false;
				}
				document.forms[0].action = "rejectTicket.action?tick="+selected;
				document.forms[0].submit();
						
		
		}
	</script>
</form>
</body>