  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  
  
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "viewGroup.action");
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
	<%if (access.equalsIgnoreCase("Y")){%>		
			<!-- END STYLE CUSTOMIZER -->
			<!-- BEGIN PAGE HEADER-->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
					USER GROUP<small></small>
					</h3>
					
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>View User Group
							</div>
							<div class="actions">
							<%if(create.equalsIgnoreCase("Y")){ %>
							<a href="createGroup.action" class="btn green"  id="createChart" onclick="callCreate()" >
								<i class="fa fa-plus"></i> Create </a>
								<%}%>
									<%if(edit.equalsIgnoreCase("Y")){ %>
								<a href="javascript: void(0)" class="btn blue"  id="editGroupDetails" onclick="editGroup()">
								<i class="fa fa-pencil"></i> Edit </a>
									<%}%>
							  <%if(delete.equalsIgnoreCase("Y")){%>
								<a href="javascript: void(0)" class="btn red"  id="deleteGroupDetails">
								<i class="fa fa-pencil"></i>Delete </a>
								<%}%>
								<!-- <div class="btn-group">
                      <button class="btn green dropdown-toggle"	data-toggle="dropdown"><i class="fa fa-gears"></i> Tools <i class="fa
									fa-angle-down"></i> </button>
                                    <ul class="dropdown-menu pull-right "> 
                                        <li>
                                    <a class="btn blue" href="javascript: void(0)" onclick="window.open('','_new').location.href='/its/generateReport?id=MENUGROUPMASTERRPT'">
                                        
                                       Report </a>
                                     </li>

                                   </ul>
                           </div>	
 -->								<!-- <a href="#" class="btn blue"  id="groupRoleMap" onclick="mapGroupRole()">
								<i class="fa fa-pencil"></i> Assign Role to group </a> -->
								
							</div>
						</div>
						
						<div class="portlet-body">
						<b><font color="green"> <s:actionmessage />  </font></b>
						<input type="hidden" name="requestType" id="requestType" value="text"/>
						<table class="table table-striped table-bordered table-hover" id="addGroupdetaills">
							<thead>
							<tr>
								<th>
									
								</th>
								<th>
									 Group Id
								</th>
						 		<th>
									 Group Name
								</th>
								
								<th>
									Status
								</th>
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
	</div>
	<script>
	 function editGroup(){
		 /*$('#editGroupDetails')
	.click(
			function() { */
				var cnt = $(":checkbox:checked").length;
				var val;
				if (cnt == 0) {
					bootbox.alert("Please Select Checkbox To Edit");
				} else if (cnt > 1) {
					bootbox
							.alert("Please Select One Checkbox To Edit");
				} else {
					$("input[type='checkbox']:checked").each(
							function() {

								val = this.value;
							});
					document.getElementById("group_id").value = val;
					document.getElementById("form1").submit();

				}

				/* 	}); */
	}
	function mapGroupRole()
	 {
		 document.forms[0].action = "grouprole.action";
		 document.forms[0].submit();
	 }
	 function callCreate()
	 {
		 document.forms[0].action = "createGroup.action";
		 document.forms[0].submit();
	 }
	 
	 $('#deleteGroupDetails')
		.click(
			
function() {
	
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
		
		
		
		
			/* document.getElementById("id").value = val;
			document.getElementById("form2").submit(); */
			//alert("test///");
			 $.ajax({
                 type: "POST",
                 url: "getInactiveForGroup.action?groupid="+val,
                 success: function(response){
                 	if(response ==  0 )
                 		{
                 	
                 		
            			
                 		bootbox.confirm("Are you Sure, you want to delete this record?",
								function(result) {
						
						if (result == true) {
							 document.getElementById("id").value = val;
		            			document.getElementById("form2").submit(); 
						}else{
						
						}
						});
            			
            			
                 		}else{
                 	
                 	bootbox.alert(response+"So can not delete ",
								function(result) {
						
						if (result == true) {
							/*  document.getElementById("id").value = val;
		            			document.getElementById("form2").submit();  */
						}else{
						
						}
						});
                 }
                 	
                 	
                 }
			  });
			
			
	  
	}
});
	 
	 $(document).ready(function() {
		   window.history.pushState("","", "viewGroup.action");
		 });

	 
	 
	</script>
	</form>
	
	
	<form name="form1" id="form1" action="editGroupPage.action" method="POST">
	<input type="hidden" name="group_id" id="group_id" value="" />

</form>
<form name="form2" id="form2" action="createGroupView!deleteGroup" method="POST">
	<input type="hidden" name="id" id="id" value="" />

</form>
<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>
