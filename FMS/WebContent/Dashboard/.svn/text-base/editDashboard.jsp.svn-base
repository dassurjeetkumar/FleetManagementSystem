<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
 
<script>
// 	function validate() {
		
// 		if (document.getElementById("device_type_name").value == '') {
// 			alert('Please Enter a New Device Type');
// 			return false;
// 		}
// 		if (document.getElementById("note").value == '') {
// 			alert('Please Enter Note');
// 			return false;
// 		}

// 		document.forms[0].action = 'createcomplaintAction.action';
// 		document.forms[0].submit();
// 	}
	function goView()
	{
		document.forms[0].action = 'dashboardAccess.action';
		document.forms[0].submit();
	}
</script>
	
	<script>
	
	function getGroup(){
		
		/* var div = document.getElementById('datatable');
		div.style.visibility = 'visible';
		var msgdiv = document.getElementById('msgdiv');
		msgdiv.style.visibility = 'hidden'; */
		
		var id=document.getElementById("dashboard_id").value;
		alert("dashboard_id..."+id);
	  $('#DashboardName').dataTable({
		 "aaSorting" : [ [ 1, 'asc' ] ],
	     "aLengthMenu": [
	         [10,20, 50, 100],
	         [10,20, 50, 100] // change per page values here
	     ],
	     // set the initial value
	     "iDisplayLength": 10,
	     "bProcessing" : true,
	     "bServerSide" : true,
	     "sAjaxSource" : "getDashboard.action?userId="+id,
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
	 
	 jQuery('#GroupName_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	 jQuery('#GroupName_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown

	}

	</script>




<title>Insert title here</title>
</head>
<body>
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "dashboardAccess.action");
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
	<%if (access.equalsIgnoreCase("Y")){%>
		<%if (edit.equalsIgnoreCase("Y")){%>
		<div class="page-content">
				<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
				DASHBOARD MASTER
				</h3>
			</div>
		</div>
			<div class="tab-content">
		
				<div class="tab-pane active" id="tab_0">
				<font color="red"><s:actionmessage/></font>
					<div class="portlet box grey-cascade">
					
						<div class="portlet-title">
						
							<div class="caption">
								<i class="fa fa-gift"></i>Edit Dashboard
							</div>
							
						</div>
						
						<div class="portlet-body form">
						<s:if test="hasActionErrors()">
							<div class="alert alert-danger">
								<button class="close" data-close="alert"></button>
								<span> <s:actionerror />
								</span>
							</div>


						</s:if>
							<!-- BEGIN FORM-->
							<form action="updateDashboard.action" id="createDashboardName" class="form-horizontal" method="post">
										
								<input type="hidden" name="dashboardmodel.id"  id="dashboard_id" value="<s:property value="dashboardmodel.id"/>"/>
									
									
								<div class="form-group">
										<label class="col-md-3 control-label">Dashboard Name:<font color="red">*</font></label>

										<div class="col-md-4">
											<input type="text" class="form-control" id="useriddetails" maxlength="15"
												name="dashboardmodel.dashboardname" value='<s:property value="dashboardmodel.dashboardname"/>'>
											<s:if test="fieldErrors.get('dashboardmodel.dashboardname').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('dashboardmodel.dashboardname').get(0)" /></span>
											</s:if>
<!-- 											<div class="col-md-offset-3 col-md-3"> -->
										<!-- <button type="button" class="btn blue" onClick="getDepot1()">Submit</button> -->
<!-- 									</div> -->
										</div>
								</div>
								<div class="form-group">
										<label class="col-md-3 control-label">Dashboard Description :</label>

										<div class="col-md-4">
											<textarea rows="3" class="form-control" id="notes" 
												name="dashboardmodel.dashboardnamedesc" maxlength="30"><s:property value="dashboardmodel.dashboardnamedesc"/></textarea>
												<s:if test="fieldErrors.get('dashboardmodel.dashboardnamedesc').size() > 0"> 
		     								<span style="color:red;"><s:property value="fieldErrors.get('dashboardmodel.dashboardnamedesc').get(0)" /></span> 
 											</s:if> 
										</div>
									</div>
									
									
									
									<div class="form-group">
									<label class="col-md-3 control-label">Status:<font color="red">*</font></label>
									<div class="col-md-4">
										<select class="form-control" id="status" name="dashboardmodel.status">
											<option id="active" value="ACTIVE">ACTIVE</option>
											<option id="deactive" value="INACTIVE">INACTIVE</option>
										</select>
											<script>
															var status = "<s:property value="dashboardmodel.status"/>";
															if (status != undefined) {
																if (status == "ACTIVE"
																		|| status == "ACTIVE") {
																	document
																			.getElementById("active").selected = true;
																} else {
																	document
																			.getElementById("deactive").selected = true;
																}
															}
														</script>
									</div>
								</div>
								
								<!-- <!-- //chart start -->
								<!-- div class="portlet-body" id="datatable" style="visibility: hidden">
											

									<input type="hidden" name="requestType" id="requestType"
										value="text" />
									
									<table class="table table-striped table-bordered table-hover"
										id="DashboardName">
										<thead>
											<tr>
												<th style="width1: 8px;"></th>
												<th>Charts</th>
											</tr>
										</thead>

									</table>
									</div> -->
								<!-- //end chart --> 
								
<!-- 								<div class="form-actions fluid"> -->
									
<!-- 								</div> -->
								
									<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
									
										<button  type="submit" class="btn blue" onSubmit="validate()">Save</button>
										<button type="button" class="btn default" onclick="goView()">Cancel</button>
									</div>
								</div>
									
									
							
								<s:token />
							</form>
					
							<!-- END FORM-->
							<%}else{ %>
<%@ include file="/pages/admin/error.jsp" %>
<%} %>									
										
<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
$(document).ready(function() {
//alert("heloooo");
	//getGroup();
	
}); 
</script>
</body>
</html>