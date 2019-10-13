
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
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ShowRoleAction.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
%>


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
						USER ROLE <small></small>
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
								<i class="fa fa-globe"></i>View User Role
							</div>
							<!-- <div class="actions">
								<a  class="btn green" id="add_"> <i class="fa fa-plus"></i> Create
								</a> <a  class="btn blue" id="editRole_"   > <i class="fa fa-pencil"></i>
									Edit
								</a> 
								
								
								<a href="#" class="btn red" id="deleterole_"> 
								<i class="fa fa-times"></i>Delete
								</a>	
								<a  class="btn blue" id="pageRole"
									onclick="pageRoleMapping()"> <i class="fa fa-pencil"></i>
									Assign Page Access
								</a> 

							</div> -->
							
							<div class="actions">
								<%if(create.equalsIgnoreCase("Y")){ %>
							<a href="createRole.action" class="btn green"  id="createrole_" onclick="callCreate()"  >
								<i class="fa fa-plus"></i> Create </a>
								<%}%>
									<%if(edit.equalsIgnoreCase("Y")){ %>
								<a href="javascript: void(0)" class="btn blue"  id="editrole_" onclick="editRole()">
								<i class="fa fa-pencil"></i> Edit </a>
								<%}%>
							  <%if(delete.equalsIgnoreCase("Y")){ %>
								<a href="javascript: void(0)" class="btn red"  id="deleterole_" onclick="deleteRole()"> 
								<i class="fa fa-times"></i>Delete
								</a>
								<%}%>		
								<!-- <div class="btn-group">
                      <button class="btn green dropdown-toggle"	data-toggle="dropdown"><i class="fa fa-gears"></i> Tools <i class="fa
									fa-angle-down"></i> </button>
                                    <ul class="dropdown-menu pull-right "> 
                                        <li>
                                    <a class="btn blue" href="javascript: void(0)" onclick="window.open('','_new').location.href='/its/generateReport?id=MENUROLEMAStERRPT'">
                                        
                                       Report </a>
                                     </li>

                                   </ul>
                           </div>	
 -->							</div>
							
						</div>
					<div class="portlet-body">
							<input type="hidden" name="requestType" id="requestType"
								value="text" />
								<b><font color="green"> <s:actionmessage />  </font></b>
							<table class="table table-striped table-bordered table-hover"
								id="roleView">
								<thead>
									<tr>
										<th ></th>
										<th>Role Id</th>
										<th>Role Name</th>
										<th>Status</th>
										<%if(viewdeletedrecord.equalsIgnoreCase("Y")) {%>	
													<th>Record Status</th>
													<%}%>	
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
	</div>
	
	<script>
		
			$('#editrole_').click(
							function () {
								var cnt = $(":checkbox:checked").length;
								var val;
								if (cnt == 0) {
									bootbox.alert("Please Select Checkbox To Edit");
								} else if (cnt > 1) {
									bootbox.alert("Please Select One Checkbox To Edit");
								} else {
									$("input[type='checkbox']:checked").each(
											function() {
												val = this.value;
											});
									document.getElementById("chartid").value = val;
									document.getElementById("form2").submit();
								}

							});
		
		
			$('#createrole_').click(	
				function () {
					document.forms[0].action = "createRole.action";
					document.forms[0].submit();
				});
		
		$('#deleterole_').click(
				function () {
					var cnt = $(":checkbox:checked").length;
					var val;
					if (cnt == 0) {
						bootbox.alert("Please select checkbox to delete");
					} else if (cnt > 1) {
						bootbox.alert("Please select one checkbox to delete");
					} else {
						$("input[type='checkbox']:checked").each(
								function() {
									val = this.value;
									type = 'text';
								});
						
						
									
									
									 $.ajax({
						                    type: "POST",
						                    url: "getInactiveForInformation.action?roleid="+val,
						                    success: function(response){
						                    	if(response ==  0 )
						                    		{
						                    		bootbox.confirm("Are you Sure, you want to delete this record?",
															function(result) {
													
													if (result == true) {
														document.getElementById("roleid").value = val;
														document.getElementById("form1").submit();
													}else{
													
													}
													});
						                    		
						                    		
						                    	
						                    		
						                    		}else{
						                    	
						                    	bootbox.alert(response+" <br> So can not delete",
														function(result) {
												
												if (result == true) {
													/* document.getElementById("roleid").value = val;
													document.getElementById("form1").submit();  */
												}else{
												
												}
												});
						                    }
						                    	
						                    	
						                    }
									  });
								
									
							   
						
						

					}				
		});
		
		$(document).ready(function() {
			   window.history.pushState("","", "ShowRoleAction.action");
			 });

	</script>
</form>
<form name="form1" id="form1" action="DeleteroleRecord.action"
	method="POST">
	<input type="hidden" name="roleid" id="roleid" value="" />
</form>
<form name="form2" id="form2" action="editRole.action"
	method="POST">
	<input type="hidden" name="chartid" id="chartid" value="" />
</form>
<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>
