
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<%
// AccessRightsDao  accessrightdao=new AccessRightsDao();
// AccessRights accessrights=new AccessRights();
// int usrsessionid=(Integer)request.getSession().getAttribute("userid");
// accessrights=accessrightdao.accessRightsdetails(usrsessionid,"HappyHours.action");
// String access=accessrights.getACCESS();
// String create=accessrights.getCREATE();
// String edit=accessrights.getEDIT();
// String delete=accessrights.getDELETE();
// String read=accessrights.getREAD();
// String error=accessrights.getERROR();
// String viewdelete=accessrights.getVIEWDELETE();
// String status=accessrightdao.UserStatus(usrsessionid);
// String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
%>
<html>
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
<%-- 			<%if (access.equalsIgnoreCase("Y")){ %> --%>
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						HAPPY HOURS <small></small>
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
								<i class="fa fa-globe"></i>View Happy Hours
							</div>
							<div class="actions">
<%-- 							<%if(create.equalsIgnoreCase("Y")){ %> --%>
							<a href="javascript:void(0)" class="btn green" onclick="callCreate()"> <i
								class="fa fa-plus"></i> Create
							</a>
<%-- 							<%}%><%if(edit.equalsIgnoreCase("Y")){ %> --%>
							 <a href="javascript:void(0)" class="btn blue" onclick="callEdit()"> <i
								class="fa fa-pencil"></i> Edit
							</a>
<%-- 							<%}%><%if(edit.equalsIgnoreCase("Y")){ %>  --%>
							<a href="javascript:void(0)" class="btn red" onclick="callDelete()"> <i
								class="fa fa-times"></i> Delete
							</a>
<%-- 							<%}%> --%>
							 <div class="btn-group">

<!--                                                                         <button class="btn green dropdown-toggle" -->
<!-- data-toggle="dropdown"><i class="fa fa-gears"></i> Tools <i class="fa -->
<!-- fa-angle-down"></i> -->
<!--                                                                         </button> -->
<!--                                                                         <ul class="dropdown-menu pull-right "> -->
<%--                                                                                 <li><%if(status.equalsIgnoreCase("ACTIVE")){ %> --%>
<!--                                                                                         <a class="btn blue" href="javascript: void(0)" -->
<!-- onclick="window.open('','_new').location.href='/its/generateReport?id=PEAKHOURRPT'"> -->
<!--                                                                                         href="/its/ReportAction!generateReport?id=1"> -->
<%--                                                                                         Report </a><%} %> --%>
                                                                                </li>


                                                                        </ul>
                                                        </div>
							</div>

							</div>
						

						<div class="portlet-body">
							<b><font color="green"> <s:actionmessage />  </font></b>
							<b><font color="red"> <s:actionerror />  </font></b>
							<table class="table table-striped table-bordered table-hover"
								id="happyHoursView">
								<thead>
									<tr>
										
										<th style="width1: 8px;"></th>
										<th>Happy Hour Id</th>
										<th>Happy Hour Type Name</th>
										<th>Service Type</th>
<!--  										<th>Schedule Type</th> -->
										<th>Start_Time</th>
										<th>End_Time</th>
										<th>Percentage</th>
										<th>Lumpsum_Amount</th>
<!-- 										<th>Incre</th> -->
										<th>Effective_Start_Date</th> 
 										<th>Effective_End_Date</th> 
 									
 										
<%--  										<%if(viewdeletedrecord.equalsIgnoreCase("Y")) {%>	 --%>
<!-- 													<th>Record Status</th> -->
<%-- 													<%}%> --%>
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
function callEdit(){
		
		var cnt = $(":checkbox:checked").length;
		var val;
		if (cnt == 0) {
			bootbox
					.alert("Please Select Checkbox To Edit");
		} else if (cnt > 1) {
			bootbox
					.alert("Please Select One Checkbox To Edit");
		} else {
			$("input[type='checkbox']:checked").each(
					function() {

						val = this.value;
					});
			if(isEligibleForOpertion(val)){
			document.forms[0].action = "ModifyHappyHourDetails.action?passid="+ val;
			document.forms[0].submit();
			}else{
				bootbox.alert("Please Select Valid Record");
			}

					
	}
	}
	function callCreate() {
		document.forms[0].action = "createHappyHour.action";
		document.forms[0].submit();
	}
	function callDelete(){
		
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
			document.forms[0].action = "deleteHappyHour.action?passid="+ val;
			document.forms[0].submit();
			}else{
				bootbox.alert("Please Select Valid Record");
			}

					
	}
	}
	function check() {

		var chckbxCount = $("input:checked").length;
		if (chckbxCount > 0 && chckbxCount > 1) {
			bootbox.alert("Select only one Peak Hours...!")
			return false;
		} else if (chckbxCount > 0 && chckbxCount == 1) {
			return true;
		} else {
			bootbox.alert("Please select Peak Hours");
			return false;
		}

	}
</script>	

<form name="form1" id="form1" action="ModifyPeakHourDetails.action" method="POST">
 	<input type="hidden" name="peakhouridd" id="peakhouridd" value="" /> 

 </form>
<form name="form2" id="form2" action="deletePeakHour.action" method="POST">
	<input type="hidden" name="peakhourid" id="peakhourid" value="" />

</form>
<%-- <%}else --%>
<%-- {%> --%>

<%-- <%@ include file="/pages/admin/error.jsp" %> --%>

<%-- <%}%> --%>
<script>
$(document).ready(function() {
	   window.history.pushState("","", "HappyHours.action");
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