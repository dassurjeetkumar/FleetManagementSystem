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
	function validate() {

		if (document.getElementById("role_name").value == '') {
			alert('Please Enter a New Role');
			return false;
		}
		if (document.getElementById("note").value == '') {
			alert('Please Enter Remarks');
			return false;
		}
		//alert("hello");

		document.forms[0].action = 'createRoleAction.action';
		document.forms[0].submit();
	}
	function goView() {

		/* var val = $("input[type='checkbox']").val(); */
		//alert(val);
		document.forms[0].action = 'ShowRoleAction.action';
		document.forms[0].submit();

	}
</script>

<title>Insert title here</title>
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
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
%>
	<div class="page-content-wrapper">
		<div class="page-content">
<%if (access.equalsIgnoreCase("Y")){%>
<%if (create.equalsIgnoreCase("Y")){%>
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">

					<div class="row">
						<div class="col-md-12">
							<!-- BEGIN PAGE TITLE & BREADCRUMB-->
							<h3 class="page-title">
								USER ROLE <small></small>
							</h3>
							<font color="green"><s:actionmessage /></font>
							<!-- END PAGE TITLE & BREADCRUMB-->
						</div>
					</div>

					<div class="portlet box grey-cascade">
						<div class="portlet-title">

							<div class="caption">
								<i class="fa fa-gift"></i>Create User Role
							</div>

						</div>

						<div class="portlet-body form">
							<!-- BEGIN FORM-->
							<form action="createRoleAction.action" class="form-horizontal">
								<div class="form-body">
									<font color="red"> <s:actionerror />
									</font>
									<div class="form-group">
										<label class="col-md-3 control-label">Role Name<font
											color="red">*</font></label>

										<div class="col-md-4">
											<input type="text" class="form-control" id="role_name"
												maxlength="50" value='<s:property value="role.role_name"/>'
												name="role.role_name">
											<s:if test="fieldErrors.get('rolename').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('rolename').get(0)" /></span>
											</s:if>
										</div>
									</div>
								</div>


								<%-- <div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Note</label>

										<div class="col-md-4">
											<textarea rows="3" class="form-control" id="note"
												name="role.note"></textarea>
											<s:if test="fieldErrors.get('note').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('note').get(0)" /></span>
											</s:if>
										</div>
									</div>
								</div> --%>
								<div class="form-group">
									<label class="col-md-3 control-label">Status<font
										color="red">*</font>:
									</label>
									<div class="col-md-4">
										<select class="form-control" id="role.status"
											name="role.status">
											<s:if test="role.status=='ACTIVE'">
												<option value="ACTIVE" selected="selected">ACTIVE</option>
												<option value="INACTIVE">INACTIVE</option>
											</s:if>
											<s:elseif test="role.status=='INACTIVE'">
												<option value="ACTIVE">ACTIVE</option>
												<option value="INACTIVE" selected="selected">INACTIVE</option>
											</s:elseif>
											<s:else>
												<option id="select" value="0">select</option>
												<option value="ACTIVE">ACTIVE</option>
												<option value="INACTIVE">INACTIVE</option>
											</s:else>
											
										</select>
										
										<s:if test="fieldErrors.get('status').size() > 0">
											<span style="color: red;"><s:property
													value="fieldErrors.get('status').get(0)" /></span>
										</s:if>



									</div>
								</div>
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn blue">Save</button>
										<!-- <button type="submit" class="btn default" id="cancel"onclick="goView()">Cancel</button> -->
										<button type="button" id="cancel" class="btn default"
											onclick="goView()">Cancel</button>
									</div>
								</div>


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


</body>
</html>