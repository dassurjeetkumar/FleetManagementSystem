
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
accessrights=accessrightdao.accessRightsdetails(usrsessionid,"viewcomplaint.action");
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

<link rel="stylesheet" 	href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
<input type="hidden" name="id" id="id" />
<form>
<div class="page-content-wrapper">
	<div class="page-content">
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
				<h3 class="page-title">
					COMPLAINT
				</h3>
			</div>
		</div>
		<%if (access.equalsIgnoreCase("Y")){ %>
		<div class="row">
			<div class="col-md-12">
				<div class="portlet box grey-cascade">

					<div class="portlet-title">
						<div class="caption">

							<i class="fa fa-globe"></i> View Complaint
						</div>
						<div class="actions">
							<%if(create.equalsIgnoreCase("Y")){ %>
							<a href="#" class="btn green" onclick="callCreate()"> <i
								class="fa fa-plus"></i> Create
							</a> <%}%>
							<%if(edit.equalsIgnoreCase("Y")){ %><a href="#" class="btn blue" onclick="callEdit()"> <i
								class="fa fa-pencil"></i> Edit
							</a> <%}%>
							<%if(edit.equalsIgnoreCase("Y")){ %><a href="#" class="btn red" onclick="callDelete()"> <i
								class="fa fa-times"></i> Delete
							</a><%}%>
					 <div class="btn-group">
                      <button class="btn green dropdown-toggle"	data-toggle="dropdown"><i class="fa fa-gears"></i> Tools <i class="fa
									fa-angle-down"></i> </button>
                                    <ul class="dropdown-menu pull-right "> 
                                        <li>
                                        <%if(status.equalsIgnoreCase("ACTIVE")){ %>
                                    <a class="btn blue" href="javascript: void(0)"onclick="window.open('','_new').location.href='/its/generateReport?id=COMPLAINTRPT'">
                                        <!-- href="/its/ReportAction!generateReport?id=1"> -->
                                       Report </a>
                                       <%} %>
                                     </li>

                                   </ul>
                           </div>
						</div>
					</div>
					<div class="portlet-body">
						<form action="">
							<input type="hidden" id="" value="" name="vehicleTypeId"/>
							<b>
								<font color="green"> <s:actionmessage/></font>
								<font color="red"> <s:actionerror/></font>
							</b>
							<table class="table table-striped table-bordered table-hover"
								id="complaintListView">
								
								<thead>
									<tr>
										<tr>
										
										<th style="width: 8px;"></th>
										<th>Complaint Id</th>
										 <th>Complaint Type</th>
										<th>Incident Date</th>
										<th>Complaint Date</th> 
										<th>Identification Data</th> 
										<th>Complaint Description</th> 
										<th>Status</th> 
										<th>Complaint Media</th> 
										<th>Taken By</th> 
										<%if(viewdeletedrecord.equalsIgnoreCase("Y")) {%>	
													<th>Record Status</th>
													<%}%>
									</tr>
									
								</thead>
								
							</table>
						</form>
					</div>

				</div>
			</div>
		</div>
	</div>
</div>
		<!-- END PAGE CONTENT-->
	
	

	
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
				document.getElementById("complaintidd").value = val;
				document.getElementById("form1").submit();
				}else{
					bootbox.alert("Please Select Valid Record");
				}
			}
		//});

	}
	function callCreate() {
		document.forms[0].action = "createcomplaint.action";
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
				bootbox	.confirm("Are you sure you want to delete Complaint?",function(result) {
				if (result == true) {
					document.getElementById("complaintid").value = val;
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
			bootbox.alert("Select only one Complaint...!")
			return false;
		} else if (chckbxCount > 0 && chckbxCount == 1) {
			return true;
		} else {
			bootbox.alert("Please select Complaint");
			return false;
		}

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
</form>	
<form name="form1" id="form1" action="ModifyComplaintDetails.action"
	method="POST">
	<input type="hidden" name="complaintidd" id="complaintidd" value="" />
</form>
<form name="form2" id="form2" action="deleteComplaint.action"
	method="POST">
	<input type="hidden" name="complaintid" id="complaintid" />
</form>	
<%}else
	
{%>
<%@ include file="/pages/admin/error.jsp" %>

<%}%>	
