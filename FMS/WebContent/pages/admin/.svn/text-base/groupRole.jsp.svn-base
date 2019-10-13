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
	<input type="text" id='a'>
<%if (access.equalsIgnoreCase("Y")){%>
	<div class="page-content-wrapper">
		<div class="page-content">
		<%if (create.equalsIgnoreCase("Y")){%>
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
					<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						USER GROUP <small></small>
					</h3>
					<font color="green"><s:actionmessage/></font>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
					<div class="portlet box grey-cascade">

						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Role Page
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
								<%
								String groupid="";
								String groupname="";
								 groupid=request.getParameter("groupid");
							
								
								%>
									<label class="col-md-3 control-label">Group Name:   <b> <s:property value="groupMaster.group_name"/>  </b>  </label>
									<div class="col-md-4">
									
											
											
											<input type="hidden" value='<%=groupid%>' name="groupid" id="groupid">
									
										
																			</div>
								</div>
						
						<div class="table-toolbar">
											<div class="btn-group">
												<button id="roleNew" class="btn green">
													Add New Role <i class="fa fa-plus"></i>
												</button>
											</div>

										</div>
										
										
											<div  id="successmsg"
											style="display: none">
										<b>	<p id="successgrouprole" style="color: green"></p>
											<span> </span> </b>
										</div>
										<div class="alert alert-danger" id="errormsg"
											style="display: none">
										<b>	<p class="intro" id="errorgrouprole"></p> </b>

										</div>
							<input type="hidden" name="requestType" id="requestType"
								value="text" />
							<table class="table table-striped table-bordered table-hover"
								id="role_editable">
								<thead>
									<tr>
												  
													<th>Role Id</th>
													<th>Role Name</th>
													<th>Add</th>
													<th>Delete</th>
												
												</tr>
								</thead>

							</table>
 							<!-- <div class="form-actions fluid"> -->
											<button id="cancelrolegroup" type="button" class="btn default" >Cancel</button>
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