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
	/* function validate() {

		if (document.getElementById("role_name").value == '') {
			alert('Please Enter a New Role');
			return false;
		}
	
		document.forms[0].action = 'addeditedRole.action';
		document.forms[0].submit();
	} */

	/* function getSave(){
		var status=document.getElementById("status").value;
		var role_id=document.getElementById("role_id").value;
		var rolename=document.getElementById("role_name").value;
		if(status=='INACTIVE'){
			  $.ajax({
	                type: "POST",
	                url: "getInactiveForInformation.action?roleid="+role_id,
	                success: function(response){
	                	if(response ==  0 )
	                		{
	                		document.getElementById("roleid").value = role_id;
							document.getElementById("rolename").value=rolename;
							document.getElementById("statusdetails").value=status;
							document.getElementById("form1").submit();
	                		}else{
	                	bootbox.alert(response+" So can not Inactive",
								function(result) {
						
						if (result == true) {
							
						}else{
						
						}
						});
	                }
	                	
	                }
			  });
			
		}else{
			document.getElementById("roleid").value = role_id;
			document.getElementById("rolename").value=rolename;
			document.getElementById("statusdetails").value=status;
			document.getElementById("form1").submit();
		}
		
		
	} */

	function getSave() {
		var msg=document.getElementById("msg").value;
		
		var status = document.getElementById("status").value;
		var role_id = document.getElementById("role_id").value;
		var rolename = document.getElementById("role_name").value;
		
		if (status == 'INACTIVE') {
			if (msg == 0) {
			
				return true;
			} else {
				
				bootbox.alert(msg + " So can not Inactive");
				return false;
			}
		} else {
			
			return true;
		}
	}

	function getpageAccess() {
		var rolename = document.getElementById("role_name").value;
		var role_id = document.getElementById("role_id").value;
		document.getElementById("roleiddetails").value = role_id;
		document.getElementById("form2").submit();
	}
	function goView() {
		document.forms[0].action = 'ShowRoleAction.action';
		document.forms[0].submit();

	}
</script>
</head>
<body>
	<input type="text" id='a'>
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
			<%if (edit.equalsIgnoreCase("Y")){%>
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
					<div class="col-md-12">
						<h3 class="page-title">
							USER ROLE <small></small>
						</h3>
						<font color="green"><s:actionmessage /></font>
						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				
					<div class="portlet box grey-cascade">

						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Edit User Role
							</div>

						</div>

						<div class="portlet-body form">

							<!-- BEGIN FORM-->
							<s:if test="hasActionErrors()">
								<font color="Red"> <s:actionerror />
								</font>
							</s:if>

							<s:if test="hasActionMessages()">
								<font color="green"> <s:actionmessage />

								</font>
							</s:if>
							<form class="form-horizontal" method="post"
								action="addeditedRole.action">

								<div class="form-body">
									<%-- <font color="red"> <s:actionerror />  </font>
 --%>



									<div class="form-group">
										<label class="col-md-3 control-label">Role ID:</label>

										<div class="col-md-4">
											<input type="text" class="form-control" readonly="readonly"
												value='<s:property value="role.role_id"/>'> <input
												type="hidden" class="form-control" id="msg" name="role.msg"
												value='<s:property value="role.msg"/>'>



										</div>


									</div>


									<div class="form-group">
										<label class="col-md-3 control-label">Role Name<font
											color="red">*</font>:
										</label>

										<div class="col-md-4">
											<input type="text" class="form-control" id="role_name"
												maxlength="50" name="role.role_name"
												value='<s:property value="role.role_name"/>'> <input
												type="hidden" name="role.role_id" id="role_id"
												value='<s:property value="role.role_id"/>'>

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
								<%-- <div class="form-group">
									<label class="col-md-3 control-label">Status</label>
									<div class="col-md-4">
										<select class="form-control" id="status" name="role.status">
											<option value="ACTIVE">ACTIVE</option>
											<option value="ACTIVE">DACTIVE</option>
										</select>

									</div>
								</div> --%>

								<div class="form-group">
									<label class="col-md-3 control-label">Status<font
										color="red">*</font></label>
									<div class="col-md-4">
										<div class="input-group">


											<select class="form-control" name="role.status" id="status">
												<option id="select" value="0">select</option>
												<option id="ACTIVE" value="ACTIVE">ACTIVE</option>
												<option id="INACTIVE" value="INACTIVE">INACTIVE</option>
											</select>
											<script>
												var status = "<s:property value="role.status"/>";
												if (status != undefined) {
													if (status == "ACTIVE") {
														document
																.getElementById("ACTIVE").selected = true;
													} else if (status == "INACTIVE") {
														document
																.getElementById("INACTIVE").selected = true;
													} else {
														document
																.getElementById("select").selected = true;
													}
												}
											</script>
											<s:if test="fieldErrors.get('status').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('status').get(0)" /></span>
											</s:if>

										</div>
									</div>
								</div>


								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn blue" id="saveid"
											onclick="return getSave()">Save</button>
										<button type="button" class="btn default" id="cancel"
											onclick="goView()">Cancel</button>
										<button type="button" class="btn default" id="page Acess"
											onclick="getpageAccess()">Assign Page Access</button>
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

	<!-- <form name="form1" id="form1" action="addeditedRole.action" method="POST">
	<input type="hidden" name="rolename" id="rolename"  />
	<input type="hidden" name="roleid" id="roleid" />
	<input type="hidden" name="statusdetails" id="statusdetails"  />
</form> -->
	<form name="form2" id="form2" action="addPageAccess.action"
		method="POST">
		<!-- 	<input type="hidden" name="rolename" id="rolename" value="" /> -->
		<input type="hidden" name="roleiddetails" id="roleiddetails" value="" />
	</form>
</body>
</html>