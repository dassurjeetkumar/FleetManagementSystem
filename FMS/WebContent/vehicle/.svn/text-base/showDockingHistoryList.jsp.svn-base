<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid,"DockingHistoryList.action");
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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" type="text/css" 	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css" />
<link rel="stylesheet" type="text/css" 	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css" />
<link rel="stylesheet" type="text/css" 	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-colorpicker/css/colorpicker.css" />
<link rel="stylesheet" type="text/css" 	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" />
<link rel="stylesheet" type="text/css" 	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-datetimepicker/css/datetimepicker.css" />
<link rel="stylesheet" 	href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />

<head>

<style type="text/css">
.col-md-9,.col-md-8 {
	width: 30%;
}
</style>
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
				document.getElementById("dockingidd").value = val;
				document.getElementById("form1").submit();
				}else{
					bootbox.alert("Please Select Valid Record");
				}
			}
		//});

	}
	function callCreate() {
		document.forms[0].action = "createDockHistory.action";
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
				bootbox	.confirm("Are you sure you want to delete Docking History?",function(result) {
				if (result == true) {
					document.getElementById("dockid").value = val;
					document.getElementById("form2").submit();
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
			bootbox.alert("Select only one Docking History...!")
			return false;
		} else if (chckbxCount > 0 && chckbxCount == 1) {
			return true;
		} else {
			bootbox.alert("Please select Docking History");
			return false;
		}

	}
</script>	

<div class="page-content-wrapper">
	<div class="page-content">
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
					DOCKING HISTORY
				</h3>
			</div>
		</div>
				<%if (access.equalsIgnoreCase("Y")){ %>
		<div class="row">
			<div class="col-md-12">
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-bars"></i>View Docking History
						</div>
					<div class="actions">
					<%if(create.equalsIgnoreCase("Y")){ %>
							<a href="javascript:void(0)" class="btn green" onclick="callCreate()"> <i
								class="fa fa-plus"></i> Create
							</a><%}%><%if(edit.equalsIgnoreCase("Y")){ %> &nbsp;&nbsp;<a href="javascript:void(0)" class="btn blue" onclick="callEdit()"> <i
								class="fa fa-pencil"></i> Edit
							</a> <%}%><%if(edit.equalsIgnoreCase("Y")){ %>&nbsp;&nbsp;<a href="javascript:void(0)" class="btn red" onclick="callDelete()"> <i
								class="fa fa-times"></i> Delete
							</a><%}%>&nbsp;&nbsp;
							 <div class="btn-group">
                      <button class="btn green dropdown-toggle"	data-toggle="dropdown"><i class="fa fa-gears"></i> Tools <i class="fa
									fa-angle-down"></i> </button>
                                    <ul class="dropdown-menu pull-right "> 
                                        <li>
                                        <%if(status.equalsIgnoreCase("ACTIVE")){ %>
                                    <a class="btn blue" href="javascript: void(0)"onclick="window.open('','_new').location.href='/its/generateReport?id=DOCKINGHISTORYRPT'">
                                        
                                       Report </a>
                                       <%} %>
                                     </li>

                                   </ul>
                           </div>
						</div>
					</div>
					<div class="portlet-body" >
						<b>
							<font color="green"> <s:actionmessage/></font>
							<font color="red"> <s:actionerror/></font>
						</b>
						<table id="dockingHistoryListView" class="table table-striped table-bordered table-hover">
							<thead>
								<tr><th></th>
									<th>Docking History Id</th>
									<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Registration Number&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
									<th>Mechanic User Id</th>
									<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Start Time &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
									<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;End Time&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
									<th>Status</th>
									<th>Docking Type</th>
									<th>Docking Batch Name</th>
									<th>FIP Change</th>
									<th>EOC Change</th>
									<th>Component Types</th>
									<%if(viewdeletedrecord.equalsIgnoreCase("Y")) {%>	
									<th>Record Status</th>
									<%}%>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</head>
<script>
	function callCancell() {
		window.location.href = 'DockingHistoryList.action';
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
<form name="form1" id="form1" action="ModifyDockingHistoryDetails.action"
	method="POST">
	<input type="hidden" name="dockingidd" id="dockingidd" value="" />
</form>
<form name="form2" id="form2" action="deleteDockinghistory.action"
	method="POST">
	<input type="hidden" name="dockid" id="dockid" />
</form>	
</html>
<%}else
{%>
<%@ include file="/pages/admin/error.jsp" %>

<%}%>