
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
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "viewMasterReportList");
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
							<h4 class="modal-title">Modal title</h4>
						</div>
						<div class="modal-body">Widget settings form goes here</div>
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
						<small></small>
					</h3>
					<FONT color="green"><s:actionmessage /></FONT>
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
								<i class="fa fa-globe"></i>Master Report
							</div>
							<div class="actions">
							<%if (create.equalsIgnoreCase("Y")){%>
								<a href="#" class="btn green" id="getReport"
									> <i class="fa fa-plus"></i> Generate Report
								</a> 
								<%}%>
								<!-- <a href="#" class="btn blue" id="editPassMaster"
									> <i class="fa fa-pencil"></i>
									Edit
								</a> <a href="#" class="btn red" id="deletePassMaster"
									> <i class="fa fa-times"></i> Delete
								</a> -->
								<div class="btn-group">
								<!-- <div class="btn-group pull-right"> -->
									<!-- <button class="btn green dropdown-toggle" data-toggle="dropdown"><i class="fa fa-gears"></i> Tools <i class="fa fa-angle-down"></i>
									</button>
									<ul class="dropdown-menu pull-right ">
										<li>
											<a href="#">
											Print </a>
										</li>
										<li>
											<a href="#">
											Save as PDF </a>
										</li>
										<li>
											<a href="#">
											Export to Excel </a>
										</li>
									</ul> -->
								<!-- </div> -->
							</div>
							</div>
						</div>

						<div class="portlet-body">

							<table class="table table-striped table-bordered table-hover"
								id="masterReportTable">
								<thead>
									<tr>
										<th style="width1: 8px;"></th>
										<th>Report Name</th>
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
										
										
<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>
	
	<script>
		
			$('#editPassMaster')
					.click(
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
									/* var val = $("input[type='checkbox']").val(); */
									//alert(val);
									document.forms[0].action = 'PassRateAction!edit?id='+ val;
									document.forms[0].submit();

								}

							});
	
	
			$('#deletePassMaster')
					.click(
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
									document.forms[0].action ='DeletePassRateAction.action?id='+ val ;
									document.forms[0].submit();
								  }
								}
							});
	

			$('#getReport')
					.click(
							
			function () {
				//alert("HII");
				var cnt = $(":checkbox:checked").length;
				var val;
				if (cnt == 0) {
					alert("Please select checkbox to generate report");
				} else if (cnt > 1) {
					alert("Please select one checkbox to generate report");
				} else {
					$("input[type='checkbox']:checked").each(
							function() {

								val = this.value;
							});
					document.getElementById("id").value = val;
					//document.forms[0].action = "generateReport";
					document.getElementById("form1").submit();
				
				//document.forms[1].submit();
	 			}
			});
	</script>
</form>
<form name="form1" id="form1" action="generateReport"
	method="POST">
	<input type="hidden" name="id" id="id" value="" />
</form>
