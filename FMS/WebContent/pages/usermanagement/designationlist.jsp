<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>	

<!DOCTYPE body PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<script>



/* function getDisplayVenodrForm(){
	document.forms[0].action="ShowDesignationType.action";
	document.forms[0].submit();
	
}


function getDeleteRecord(){
	var cnt = $(":checkbox:checked").length;
	var val;
	if (cnt == 0) {
		bootbox.alert("Please Select Checkbox To Delete");
	} else if (cnt > 1) {
		bootbox.alert("Please Select One Checkbox To Delete");
	} else {
		$("input[type='checkbox']:checked").each(function() {

			val = this.value;
		});

		bootbox.confirm("Are you sure?",
						function(result) {

							if (result == true) {
								document.forms[0].action = "DeleteDesignationTypeRecord.action?designation_type_id="+val;
								document.forms[0].submit();
							}
						});

		
	}

	
	
		
}

function getmodifyrecord(){
	var cnt = $(":checkbox:checked").length;
	var val;
	if (cnt == 0) {
		bootbox.alert("Please Select Checkbox To Edit");
	} else if (cnt > 1) {
		bootbox.alert("Please Select One Checkbox To Edit");
	} else {
		$("input[type='checkbox']:checked").each(function() {

			val = this.value;
		});
		document.forms[0].action = "ModifyDesignation.action?designation_type_id="+val;
		document.forms[0].submit();

	}
	
	
	

}
 */





</script>


<div class="page-content-wrapper">
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ShowDesignationlist.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
%>
		<div class="page-content">
		<div class="row">
		<%if (access.equalsIgnoreCase("Y")){%>
			<div class="col-md-12">
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
				<h3 class="page-title">
					DESIGNATION TYPE
				</h3>
				
			</div>
		</div>
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div class="modal fade" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">Modal title</h4>
						</div>
						<div class="modal-body">
							 Widget settings form goes here
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue">Save changes</button>
							<button type="button" class="btn default" data-dismiss="modal">Close</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<!-- BEGIN STYLE CUSTOMIZER -->
			<div class="theme-panel hidden-xs hidden-sm">
				
				<div class="toggler-close">
				</div>
				<div class="theme-options">
					<div class="theme-option theme-colors clearfix">
						<span>
						THEME COLOR </span>
						<ul>
							<li class="color-default current tooltips" data-style="default" data-original-title="Default">
							</li>
							<li class="color-darkblue tooltips" data-style="darkblue" data-original-title="Dark Blue">
							</li>
							<li class="color-blue tooltips" data-style="blue" data-original-title="Blue">
							</li>
							<li class="color-grey tooltips" data-style="grey" data-original-title="Grey">
							</li>
							<li class="color-light tooltips" data-style="light" data-original-title="Light">
							</li>
							<li class="color-light2 tooltips" data-style="light2" data-html="true" data-original-title="Light 2">
							</li>
						</ul>
					</div>
					<div class="theme-option">
						<span>
						Layout </span>
						<select class="layout-option form-control input-small">
							<option value="fluid" selected="selected">Fluid</option>
							<option value="boxed">Boxed</option>
						</select>
					</div>
					<div class="theme-option">
						<span>
						Header </span>
						<select class="page-header-option form-control input-small">
							<option value="fixed" selected="selected">Fixed</option>
							<option value="default">Default</option>
						</select>
					</div>
					<div class="theme-option">
						<span>
						Sidebar </span>
						<select class="sidebar-option form-control input-small">
							<option value="fixed">Fixed</option>
							<option value="default" selected="selected">Default</option>
						</select>
					</div>
					<div class="theme-option">
						<span>
						Sidebar Position </span>
						<select class="sidebar-pos-option form-control input-small">
							<option value="left" selected="selected">Left</option>
							<option value="right">Right</option>Designationlist
						</select>
					</div>
					<div class="theme-option">
						<span>
						Footer </span>
						<select class="page-footer-option form-control input-small">
							<option value="fixed">Fixed</option>
							<option value="default" selected="selected">Default</option>
						</select>
					</div>
				</div>
			</div>
			<!-- END STYLE CUSTOMIZER -->
			<!-- BEGIN PAGE HEADER-->
			
			
			
			
			<!-- BEGIN PAGE CONTENT-->
			
			<form  method="post" >
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>View Designation Type 
							</div>
							
							
							
						<div class="actions">
									<a href="#" class="btn green" id="add"> <i
										class="fa fa-plus"></i> Create
									</a> <a href="#" class="btn blue" id="edit_"> <i
										class="fa fa-pencil"></i> Edit
									</a> <a class="btn red" id="delete_"> <i class="fa fa-pencil"></i>
										Delete
									</a>

								</div>
							
							</div>
						
						
						
						
						<div class="portlet-body">
					
					
					<s:if test="hasActionMessages()">
					<font color="green"> <s:actionmessage />
					
				</font>
					</s:if>
							<input type="hidden" name="requestType" id="requestType"
								value="text" />
							<table class="table table-striped table-bordered table-hover"
								id="designationview">
								<thead>
									<tr>
										<th style="width1: 8px;"></th>
										<th>Designation Id</th>
										<th>Designation type Name</th>
										<th>Designation Type Code</th>
										<th>Status</th>
										<th>Remarks</th>
									</tr>
								</thead>

							</table>

						</div>
					</div>
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
					
					
					
				</div>
		<script>		
				
				$('#add').click(	
		function callCreate() {
			document.forms[0].action = "ShowDesignationType.action";
			document.forms[0].submit();
		});
		
		$('#edit_').click(
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
			
			document.forms[0].action = 'ModifyDesignation.action?designation_type_id='+ val;
			document.forms[0].submit();
			}
		});
         				
		$('#delete_').click(
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
						document.forms[0].action = 'DeleteDesignationTypeRecord.action?designation_type_id='+val;
						document.forms[0].submit();
						}

					}				
		});
		
		$(document).ready(function() {
			   window.history.pushState("","", "ShowDesignationlist.action");
			 });

		
		</script>
				
	</form>			
			</div>
			<!-- END PAGE CONTENT-->
			
		</div>
	<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>
	
	</body>
	</html>
