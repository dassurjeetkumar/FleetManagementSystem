
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script>
	function callEdit() {
		var val;
		//$(function() {
			var val = [];
			$(':checkbox:checked').each(function(i) {
				val = $(this).val();
			});
			if (check(0)) {
				if(isEligibleForOpertion(val)){
				document.getElementById("editCancelTypeId").value=val;
				document.getElementById("editCauseCancl").submit();
				}else{
					bootbox.alert("Please Select Valid Record");
				}
			}
		//});

	}
	function callCreate() {
		document.forms[0].action = "CreateCancelCause.action";
		document.forms[0].submit();
	}
	function callDelete() {
		var val;
		//$(function() {
			var val = [];
			$(':checkbox:checked').each(function(i) {
				val = $(this).val();
			});
			
			if (check(0)) {
				if(isEligibleForOpertion(val)){
				bootbox	.confirm("Are you sure want to delete vehicle type?",function(result) {
					if (result == true) {
						document.getElementById("CancCauseTypeId").value=val;
						document.getElementById("deleteAction").submit();
					}
			});
				}else{
					bootbox.alert("Please Select Valid Record");
				}

		}
		//});
	}
	function check() {

		var chckbxCount = $("input:checked").length;
		if (chckbxCount > 0 && chckbxCount > 1) {
			bootbox.alert("Select only one Cancellation Type...!")
			return false;
		} else if (chckbxCount > 0 && chckbxCount == 1) {
			return true;
		} else {
			bootbox.alert("Please select Cancellation Type");
			return false;
		}

	}
</script>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
	<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "causeCancellation.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
String status=accessrightdao.UserStatus(usrsessionid);

%>
<form name="editCauseCancl" id='editCauseCancl' action="EditCauseCanclType.action" method="post">
	<input type="hidden" id='editCancelTypeId' name="editCancelTypeId"/>
</form>
<form name="deleteAction" id="deleteAction" action="DeleteCanclCauseType.action" method="post">
	<input type="hidden" id='CancCauseTypeId' name="CancCauseTypeId"/>
</form>
<form>
<link rel="stylesheet" 	href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
<input type="hidden" name="id" id="id" />
<div class="page-content-wrapper">
	<div class="page-content">
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
				<h3 class="page-title">
					Cause For Cancellation 
				</h3>
			</div>
		</div>
		<%if (access.equalsIgnoreCase("Y")){%>
		<div class="row">
			<div class="col-md-12">
				<div class="portlet box grey-cascade">

					<div class="portlet-title">
						<div class="caption">

							<i class="fa fa-globe"></i> View Cause For Cancellation 
						</div>
						<div class="actions">
						<%if(create.equalsIgnoreCase("Y")){ %>
							<a href="#" class="btn green" onclick="callCreate()"> <i
								class="fa fa-plus"></i> Create
							</a> 
							<%}%>
<%if(edit.equalsIgnoreCase("Y")){ %>
							<a href="#" class="btn blue" onclick="callEdit()"> <i
								class="fa fa-pencil"></i> Edit
							</a> 
							<%}%>

<%if(delete.equalsIgnoreCase("Y")){ %><a href="#" class="btn red" onclick="callDelete()"> <i
								class="fa fa-times"></i> Delete
							</a>
							<%}%>
							

						</div>
					</div>
					<div class="portlet-body">
						
							<b>
								<font color="green"> <s:actionmessage/></font>
							<span id="errorMsg" style="color:red;"><s:actionerror/></span>
							</b>
							<table class="table table-striped table-bordered table-hover" id="causelationTable">
								
								<thead>
									<tr>
										<th></th>
										<th>Cancellation Id</th>
										<th>Cancellation Type</th>
										<th>Remarks</th>
										<th>Status</th>
									</tr>
								</thead>
								
							</table>
						
					</div>

				</div>
			</div>
		</div>
	</div>
</div>
</form>
<script>
$(document).ready(function() {
	window.history.pushState("","", "causeCancellation.action");
	var w=$('#errorMsg span').html();
	   //alert(w);
	    w=w.replace(/@/g,"<br>");

	   $('#errorMsg').html(''+w+'');
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
<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>

