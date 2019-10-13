<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>


<!DOCTYPE html>
<html>
<head>
 <style>
.ui-autocomplete {
max-height: 100px;
overflow-y: auto;
/* prevent horizontal scrollbar */
overflow-x: hidden;
/* add padding for vertical scrollbar */
    padding-right: 5px;
}
/* IE 6 doesn't support max-height
* we use height instead, but this forces the menu to always be this tall
*/
 html .ui-autocomplete {
height: 100px;
}
</style>
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css"> 
<%-- <script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script> --%>
<link rel="stylesheet" href="/resources/demos/style.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
<%-- <script type="text/javascript" src="//www.google.com/jsapi"></script> --%>
<script src="assets/global/plugins/jquery-1.11.0.min.js"
	type="text/javascript"></script>
<script>

 function getDetails(){
		var userid=document.getElementById("useriddetails").value;
		if(userid==0)
			{
			bootbox.alert("Please Select User Name");
			return false;
			}else{
				/* document.forms[0].action = '';
				document.forms[0].submit(); */
				return true;
			}
	}


</script>


</script>
</head>
<body>
	

<div class="page-content-wrapper">

		<div class="page-content">

			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						DASHBOARD MASTER<small></small>
					</h3>
					
					<!-- END PAGE TITLE & BREADCRUMB-->
					
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Create Dashboard Access
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
							<form action="saveDashboard.action" class="form-horizontal" method="post">
										<div class="form-body">
										<div class="panel-body">

										<div class="form-group">
											<label class="col-md-3 control-label">Dashboard</label>
											<div class="col-md-4">
												<div class="input-group">
													<s:select list="userlist" id="useriddetails"
														name="userdetails.user_id"
														cssClass="select2_category form-control" headerKey="0"
														headerValue="Select" onclick="getGroup()"></s:select>

													<span class="input-group-addon"> <i
														class="fa fa-user"></i>
													</span>
												</div>
											</div>
										</div>
									
								
								
								<div id="msgdiv">
										<b><font color="green"> <s:actionmessage />  </font></b>
										</div>
								<div class="portlet-body" id="datatable" style="visibility: hidden">
											

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
									</div>
								
									
									<div class="form-actions fluid">	
									
										<div class="col-md-offset-3 col-md-9">
											<button type="submit" class="btn blue" id="mapgroupid" onclick="return getDetails()">Map Dashboard</button>
										
											<h1 class="intro">
												<s:property value="msg" />
											</h1>
										</div>
									</div>
							
								<s:token />
							</form>
					
	
							
							<!-- END FORM-->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
<script>
function getGroup(){
	
	var div = document.getElementById('datatable');
	div.style.visibility = 'visible';
	var msgdiv = document.getElementById('msgdiv');
	msgdiv.style.visibility = 'hidden';
	
	var id=document.getElementById("useriddetails").value;
  $('#DashboardName').dataTable({
	 "aaSorting" : [ [ 1, 'asc' ] ],
     "aLengthMenu": [
         [20, 50, 100],
         [20, 50, 100] // change per page values here
     ],
     // set the initial value
     "iDisplayLength": 20,
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



function userGroupMap() {
	var div = document.getElementById('selectusername');
	div.style.visibility = 'visible';

}

</script>