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

</head>
<body>
	<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ShowUserList.action");
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
		<div class="page-content">
			<%if (create.equalsIgnoreCase("Y")){%>
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						USER <small></small>
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
							
							<!-- BEGIN FORM-->
							<form  class="form-horizontal"
								method="post">
							
								<div class="form-group">
								<% String role_name="";
											 String roleid="";
											String useriddetails="";
											String username="";
											
											%>
											<%
												
											 role_name=request.getParameter("rolename");
											  roleid=request.getParameter("roleid");
											  useriddetails=request.getParameter("useriddetails");
											  username=request.getParameter("userloginnamedetails");
											  String rolename=(String)request.getAttribute("Activerolename");
											%>
											
											<label class="col-md-3 control-label">User Name:&nbsp;&nbsp;&nbsp;<b><%=username%></b></label>
									<label class="col-md-3 control-label">Role Name:&nbsp;&nbsp;&nbsp;<b><%=rolename %></b></label>
									<div class="col-md-4">
									
											
											<input type="hidden" name="roleid" id="roleid" value='<s:property value="userdetails.getRole().getRole_id()"/>' >
											<input type="hidden" name="useriddetails" id="useriddetails" value='<%=useriddetails%>' >
									
										
																			</div>
								</div>
					
						<div class="table-toolbar">
											<div class="btn-group">
												<button id="pageNewForUserRole" class="btn green">
													Add New Page <i class="fa fa-plus"></i>
												</button>
											</div>

										</div>
							
										<div  id="successmsg"
											style="display: none">
										<b>	<p id="successuserrolepage" style="color: green"></p>
											<span> </span> </b>
										</div>
										<div class="alert alert-danger" id="errormsg"
											style="display: none">
										<b>	<p class="intro" id="erroruserrolepage"></p> </b>

										</div>
						
							<table class="table table-striped table-bordered table-hover"
								id="viewuserrolepagedetails">
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
										
											
											<button id="canceluserrolepage" type="button" class="btn default" >Cancel</button>
										
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