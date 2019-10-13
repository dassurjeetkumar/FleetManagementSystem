
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<form>
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "FloorAction!view");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
%>
	<div class="page-content-wrapper">
		<div class="page-content">
		<%if (access.equalsIgnoreCase("Y")){%>
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
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						Floor <small></small>
					</h3>
					<font color="green"> <s:actionmessage />  </font>
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
								<i class="fa fa-globe"></i>View Floor
							</div>
							
							<div class="actions">
							<%if (create.equalsIgnoreCase("Y")){%>
								<a href="#" class="btn green" id="add"> 
								<i class="fa fa-plus"></i> Create
								</a> 
								<%} %>
								<!-- <a href="#" class="btn blue" id="edit_"> 
								<i class="fa fa-pencil"></i>Edit
								</a> 
								
								<a href="#" class="btn red" id="delete_"> 
								<i class="fa fa-times"></i>Delete
								</a>	 -->					
							</div>

							</div>
						</div>

						<div class="portlet-body">

							<table class="table table-striped table-bordered table-hover"
								id="floor">
								<thead>
									<tr>
										<!-- <th style="width1: 8px;"></th> -->
										<th>Floor Id</th>
										<th>Floor Name</th>
										<th>Bus Station Name</th>
										<th>Status</th>	
																
									</tr>
								</thead>

							</table>

						</div>
					</div>
				</div>
				<!-- END EXAMPLE TABLE PORTLET-->
			</div>
			<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>
	
		</div>

		<!-- END PAGE CONTENT-->
	
	<script>
		
			$('#add').click(	
			function callCreate() {
				document.forms[0].action = "FloorAction!add";
				document.forms[0].submit();
			});
			
			$('#edit_').click(
			function() {
				var cnt = $(":checkbox:checked").length;
				var val;
				if (cnt == 0) {
					alert("Please select checkbox to edit");
				} else if (cnt > 1) {
					alert("Please select one checkbox to edit");
				} else {
					$("input[type='checkbox']:checked").each(
						function() {
						val = this.value;
						});
				
				document.forms[0].action = 'FloorAction!edit?id='+ val;
				document.forms[0].submit();
				}
			});
	         				
			$('#delete_').click(
					function() {
						var cnt = $(":checkbox:checked").length;
						var val;
						if (cnt == 0) {
							alert("Please select checkbox to delete");
						} else if (cnt > 1) {
							alert("Please select one checkbox to delete");
						} else {
							$("input[type='checkbox']:checked").each(
									function() {
										val = this.value;
										type = 'text';
									});
							if (confirm('Are you sure you want to delete this record?')) {
							document.forms[0].action = 'DeleteFloorAction?id='+ val;
							document.forms[0].submit();
							}

						}				
			});
	
		
			
	</script>
</form>