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
accessrights=accessrightdao.accessRightsdetails(usrsessionid,"dashboardAccess.action");
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
<html>
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
			<%if (access.equalsIgnoreCase("Y")){ %>
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						DASHBOARD MASTER <small></small>
					</h3>
				
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
								<i class="fa fa-globe"></i>View Dashboard 
							</div>
							<div class="actions">
														<%if(create.equalsIgnoreCase("Y")){ %>
							<a href="javascript:void(0)" class="btn green" onclick="callCreate()"> <i
								class="fa fa-plus"></i> Create
							</a><%}%><%if(edit.equalsIgnoreCase("Y")){ %> &nbsp;&nbsp;<a href="javascript:void(0)" class="btn blue" onclick="callEdit()"> <i
								class="fa fa-pencil"></i> Edit
							</a><%}%> <%if(edit.equalsIgnoreCase("Y")){ %>&nbsp;&nbsp;<a href="javascript:void(0)" class="btn red" onclick="callDelete()"> <i
								class="fa fa-times"></i> Delete
							</a><%}%>&nbsp;&nbsp;
							
							
						</div>

							</div>
						

						<div class="portlet-body">
							<b><font color="green"> <s:actionmessage />  </font></b>
							<table class="table table-striped table-bordered table-hover"
								id="DashboardView">
								<thead>
									<tr>
										<th style="width1: 8px;"></th>
										<th>ID</th>
										<th>Dashboard Name</th>
										<th>Dashboard Description</th>
										<th>Status</th>
										
									</tr>
								</thead>

							</table>

						</div>
					</div>
				</div>
				<!-- END EXAMPLE TABLE PORTLET-->
				</div>
<!-- 			</div> -->
		</div>

		<!-- END PAGE CONTENT-->
	
	

	<script>
	function callEdit() {
		var val;
		//$(function() {
			var val = [];
			$(':checkbox:checked').each(function(i) {
				val = $(this).val();
				//alert("val"+val);
			});
			if (check(0)) {
				
				document.getElementById("memoidd").value = val;
				document.getElementById("form1").submit();
				
			}
		//});

	}
	function callCreate() {
		document.forms[0].action = "dashboardAccess1.action";
		document.forms[0].submit();
	}
	function callDelete() {
		var val;
		//$(function() {
			var val = [];
			$(':checkbox:checked').each(function(i) {
				val = $(this).val();
				//alert("val"+val)
			});
			if (check(0)) {
				
				bootbox	.confirm("Are you sure you want to delete Dashboard?",function(result) {
				if (result == true) {
					document.getElementById("memoid").value = val;
					document.getElementById("form2").submit();
					}
				});
				
			}
		//});
	}
	function check() {

		var chckbxCount = $("input:checked").length;
		if (chckbxCount > 0 && chckbxCount > 1) {
			bootbox.alert("Select only one Dashboard...!")
			return false;
		} else if (chckbxCount > 0 && chckbxCount == 1) {
			return true;
		} else {
			bootbox.alert("Please select Dashboard");
			return false;
		}

	}
	$(document).ready(function() {

		//window.history.pushState("", "", "memoNoticeAction.action");
		
		
		
		
			
			
		  $('#DashboardView').dataTable({
			 "aaSorting" : [ [ 1, 'asc' ] ],
		     "aLengthMenu": [
		         [10,25, 50, 100],
		         [10,25, 50, 100] // change per page values here
		     ],
		     // set the initial value
		     "iDisplayLength": 10,
		     "bProcessing" : true,
		     "bServerSide" : true,
		     "sAjaxSource" : "getDashboardlist.action",
		     "sPaginationType": "bootstrap",
		     "bDestroy": true,
		     "oLanguage": {
		         "sLengthMenu": "_MENU_ records",
		         "oPaginate": {
		             "sPrevious": "Prev",
		             "sNext": "Next"
		         }
		     },
		    "aoColumnDefs": [
		         { 'bSortable': false, 'aTargets': [0] },
		         { "bSearchable": false, "aTargets": [ 0 ] }
		     ]
		     
		 });
		 
		 jQuery('#DashboardView_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
		 jQuery('#DashboardView_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown

		

		
		
		
		
		
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
<form name="form1" id="form1" action="ModifyDashboardDetails.action" method="POST">
 	<input type="hidden" name="memoidd" id="memoidd" value="" /> 

 </form>
<form name="form2" id="form2" action="deleteDashboard.action" method="POST">
	<input type="hidden" name="memoid" id="memoid" value="" />

</form>
</html>
<%}else
{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>