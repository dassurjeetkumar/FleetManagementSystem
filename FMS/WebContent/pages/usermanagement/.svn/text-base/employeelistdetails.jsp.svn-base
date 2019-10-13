<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ShowEmployeeListDetails.action");
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
<form>
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
						Employee  <small></small>
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
								<i class="fa fa-globe"></i>View Employee
							</div>
							 <!--  added by rama -->
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
								 <!--  ended by rama -->
								<!--	
								<a href="#" class="btn red" id="delete_"> 
								<i class="fa fa-times"></i>Delete
								</a>						
							</div> -->
							
							
							
					 <div class="btn-group">
                      <button class="btn green dropdown-toggle"	data-toggle="dropdown"><i class="fa fa-gears"></i> Tools <i class="fa
									fa-angle-down"></i> </button>
                                    <ul class="dropdown-menu pull-right "> 
                                        <li>
                                        <%if(status.equalsIgnoreCase("ACTIVE")){ %>
                                    <a class="btn blue" href="javascript: void(0)"onclick="window.open('','_new').location.href='/its/generateReport?id=EMPLOYEERPT'">
                                        <!-- href="/its/ReportAction!generateReport?id=1"> -->
                                       Report </a>
                                       <%} %>
                                     </li>

                                   </ul>
                           </div>
						</div>

							</div>
							
						</div>

						<div class="portlet-body">
			
							<b>
							<font color="green"> <s:actionmessage/></font></b>
							
							<table class="table table-striped table-bordered table-hover"
								id="employeeview">
								<thead>
									<tr>
										<th style="width1: 8px;"></th>
										<th>Employee Id</th>
										<th>Employee Name</th>
										<th>Employee Name In kannada</th>
										<th>Employee Designation</th>
										<th>PF</th>
										<th>Token</th>
										<th>Gender</th>
										<th>Working Depot</th>
										<th>Working_Dept</th>
										<th>Driving Expire Date</th>
										<th>Driving LC NO</th>
										<th>Date of Birth</th>
										<th>Cell phone</th>
										<th>Email</th>
										<th>Retirment Date</th>
										<th>Father Name</th>
										<th>Address</th>
										<th>Status</th>
										<th>DOA</th>
										<th>Conductor Licence Expire Date</th>
										<th>Employee Type Name</th>
										<th>Division Name</th>
<!-- 										<th>Effective End Date</th> -->
																
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
		 <!--  added by rama -->
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
			document.forms[0].action = "editEmployee.action?empid="+ val;
			document.forms[0].submit();
			}else{
				bootbox.alert("Please Select Valid Record");
			}

					
	}
	}
	function callCreate() {
		document.forms[0].action = "createEmployee.action";
		document.forms[0].submit();
	}
	
	$(document).ready(function() {
		   window.history.pushState("","", "ShowEmployeeListDetails.action");
		   
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
	 <!--  ended by rama -->
</form>
<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>



</body>
</html>