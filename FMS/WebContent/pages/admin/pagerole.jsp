<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
<script type="text/javascript">
</script>

<script type="text/javascript">


function windowopen() {
	//document.getElementById("deviceid").value = DEVICE_ID;
	
// 	var roleid=document.getElementById("roleid").value;
// 	alert(roleid);
// 	 $.ajax({
	        
//          type: "post",
<%--          url: '<%=request.getContextPath()%>/AjaxPageRoleAction.action?roleiddetails='+roleid, --%>
//          success: function(result) {
         	
//          }
//      });
	
	
// 	window.open("/AjaxPageRoleAction.action" ,"self");
	
	document.forms["rolenameform"].submit();
}

	$(document).ready(function(){
		console.log("jquery loaded...");
		var roleIdReceived = $("#roleid").val();
		console.log("roleIdReceived :"+roleIdReceived);
		$("#pageroleid").val(roleIdReceived);	
	});
</script>

</head>
<body>
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
String path="ShowRoleAction.action";
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
%>

<!--  multiple page role assigment form -->
	<s:form action="/AjaxPageRoleAction.action" name="rolenameform">
		<s:hidden value="" name="pageroleid" id="pageroleid"></s:hidden>
	</s:form>
	<!--  end multiple page role assigment form -->

	<input type="text" id='a'>

	<div class="page-content-wrapper">
		<div class="page-content">
			<%if (access.equalsIgnoreCase("Y")){%>
			<div class="tab-content">
				<%if (create.equalsIgnoreCase("Y")){%>
				<div class="tab-pane active" id="tab_0">
					<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						USER ROLE <small></small>
					</h3>
					<font color="green"><s:actionmessage/></font>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
					<div class="portlet box grey-cascade">

						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Page Access Form
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
							<form  class="form-horizontal"
								method="post">
							
								<div class="form-group">
								<% String rolename="";%>
											<% String roleid="";%>
											<%
											// rolename=request.getParameter("rolename");
											  roleid=request.getParameter("roleiddetails");
											if(roleid == null){
												  roleid=request.getParameter("pageroleid");
											  }
											%>
										<input type="hidden" name="userid" id="userid" value='<%=usrsessionid%>'>
										<input type="hidden" name="path" id="path" value='<%=path%>'>
									<label class="col-md-3 control-label">Role Name:   <b> <s:property value="role.role_name"/>  </b>  </label>
									<div class="col-md-4">
											<input type="hidden" name="roleid" id="roleid" value='<%=roleid%>' >
																			</div>
								</div>
						
						<div class="table-toolbar">
											<div class="btn-group">
												<button id="pageNew" class="btn green">
													Add New Page <i class="fa fa-plus"></i>
												</button>
											</div>
											
												<div class="btn-group">
												
												<a href="#" class="btn green" onclick="windowopen();">
													Add Multiple New Page <i class="fa fa-plus"></i>
												</a>
												
											</div>

										</div>
										
										
										<div  id="successmsg"
											style="display: none">
												<b>	<p id="successpagerole" style="color: green"></p>
											<span> </span> </b>
										</div>
										
										<div  id="successmsg"
											>
												<b>	<p style="color: green">
<%-- 													<s:property value="pageIdSuccessMessage"/> --%>
													<s:property value="onlySuccessMessage"/>
												</p>
											<span> </span> </b>
										</div>
										<div class="alert alert-danger" id="errormsg"
											style="display: none">
										<b>	<p class="intro" id="errorpagerole"></p> </b>

										</div>
										<div style="width: 100%; float: left; padding: 0px; color: red;">
											<b>	
												<p class="intro" >
													<s:property value="pageIdErrorMessage"/>
												</p> 
											</b>
										</div>
							<input type="hidden" name="requestType" id="requestType"
								value="text" />
							<table class="table table-striped table-bordered table-hover"
								id="viewpageroledetails">
								<thead>
									<tr>
									
										<th>Page Id</th>
										<th>Page Name</th>
										<th>Read</th>
										<th>Write</th>
										<th>Add</th>
										<th>Delete</th>
								
										
									</tr>
								</thead>

							</table>
 									<!-- <div class="form-actions fluid"> -->
											<button id="cancelpagerole" type="button" class="btn default" >Cancel</button>
									<!-- </div> -->
							</form>
							<!-- END FORM-->
							<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>

							<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>