
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<script>

$(document).ready(function() {
	   window.history.pushState("","", "user_group.action");
	 });

function goView() {
	$('#cancel').click(function() {

		/* var val = $("input[type='checkbox']").val(); */
		//alert(val);
		document.forms[0].action = 'viewGroup.action';
		document.forms[0].submit();

	});
}

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
</head>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
	<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "user_group.action");
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
<%if (access.equalsIgnoreCase("Y")){%>

		<!-- END STYLE CUSTOMIZER -->
		<!-- BEGIN PAGE HEADER-->
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
				<h3 class="page-title">
					USER-GROUP <small></small>
				</h3>

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
							<i class="fa fa-globe"></i>View User Group 
						</div>
						
					</div>

					<div class="portlet-body">
						<div class="portlet-body form">

							<!-- BEGIN FORM-->
							<form action="userGroupMap.action"  class="form-horizontal" method="post">
								<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">User Name</label>
										<div class="col-md-4">
											<div class="input-group">
												<%-- <select class="form-control" name="UserDetails.user_id"
													id="user_id" onclick="getUserName()" onchange="getGroup()">
													

												</select> --%>
												<%--   <s:if test="fieldErrors.get('user_id').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('user_id').get(0)" /></span>
									</s:if>	 --%>
										
									
										<s:select list="userlist" id="useriddetails" name="userdetails.user_id"  
		cssClass="select2_category form-control" headerKey="0" headerValue="Select" onclick="getGroup()"></s:select>
									
												 	<span class="input-group-addon">
															    <i class="fa fa-user"></i>
															</span>
								
												 
											</div>
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
										id="GroupName">
										<thead>
											<tr>
												<th style="width1: 8px;"></th>
											<!-- 	<th>Group Id</th> -->
												<th>Group Name</th>

												<!-- <th>User Id</th>

										<th>Status</th>
										 

												<th>Created By</th>

												<th>Updated By</th>


												<th>Updated Date</th>

												<th>Deleted Status</th> -->

											</tr>
										</thead>

									</table>
									</div>
									<div class="form-actions fluid">
										 <div class="col-md-offset-3 col-md-9"> 
											<button type="submit" class="btn blue" id="mapgroupid" onclick="return getDetails()">Map Group</button>
										
											<h1 class="intro">
												<s:property value="msg" />
											</h1>
										 </div>
									</div>
									</form>
								</div>
						</div>
					
						
						<!-- END FORM-->
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>



<script>


	function getGroup(){
		
		var div = document.getElementById('datatable');
		div.style.visibility = 'visible';
		var msgdiv = document.getElementById('msgdiv');
		msgdiv.style.visibility = 'hidden';
		
		var id=document.getElementById("useriddetails").value;
	 $('#GroupName').dataTable({
		 "aaSorting" : [ [ 1, 'asc' ] ],
         "aLengthMenu": [
             [20, 50, 100],
             [20, 50, 100] // change per page values here
         ],
         // set the initial value
         "iDisplayLength": 20,
         "bProcessing" : true,
         "bServerSide" : true,
         "sAjaxSource" : "getGroup.action?userId="+id,
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

	<%-- function getUserName(){
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 var len= document.getElementById('user_id').options.length;

		 if(len<=1 ) {
	        $.ajax({
	            type: "get",
	            url: '<%=request.getContextPath()%>	/findUserIdAction',
				success : function(result) {
					document.getElementById('user_id').innerHTML = result;
				}
			})
		}
	} --%>

	function userGroupMap() {
		var div = document.getElementById('selectusername');
		div.style.visibility = 'visible';

	}
	
	
	
</script>
</form>