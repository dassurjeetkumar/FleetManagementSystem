<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.trimax.its.model.User"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
<!DOCTYPE html>
<html>
<head>
<script>


$(document).ready(function() {
	   window.history.pushState("","", "changepasswordAction.action?");
	 });
	//function submitForm() 
	//{
		
		//alert("hello"+document.getElementById("Oldpass").value);
		
	
	       // document.forms[0].action = "changepass.action";
			//document.forms[0].submit();
	//}
	function clearForm() 
	{
		
		frm.Oldpass.focus();
		 
		 document.getElementById("Oldpass").value="";
    }

	
	function cancelForm() 
	{
		document.frm.action = "DashboardAdmin.action";
		document.frm.submit();
	}

</script>
<style>
h1.intro {color:red;font-size:14px;}

</style>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
</head>
<body onload="clearForm()">
	<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");

String access=accessrightdao.UserStatus(usrsessionid);

%>
<div class="page-content-wrapper">
	<div class="page-content">
	<%if (access.equalsIgnoreCase("ACTIVE")){%>
<div class="tab-content">
							<div class="tab-pane active" id="tab_0">
								<div class="portlet box grey-cascade">
									<div class="portlet-title">
										<div class="caption">
											<i class="fa fa-gift"></i>Change Password
										</div>
									
									</div>
									<div class="portlet-body form">
									<%-- <s:if test="hasActionErrors()">
                                       <div class="alert alert-danger">
			                                <button class="close" data-close="alert"></button>
		                                     <span>
			                                 <s:actionerror/> </span>
		                              </div>
      
                                    </s:if> --%>
                                    <s:if test="hasActionErrors()">
					<font color="Red"> <b>  <s:actionerror /> </b>
					</font>
					</s:if>
					
					<s:if test="hasActionMessages()">
					<font color="green"> <b> <s:actionmessage /> </b>
					
				</font>
					</s:if>
                                    
										<!-- BEGIN FORM-->
										<form action="ChangePassWord.action" class="form-horizontal" name="frm" method="post" onsubmit="validate()">
											<div class="form-body">
												
												<div class="form-group">
													<label class="col-md-3 control-label">Old Password</label>
													<div class="col-md-4">
															 <input type="password" name="oldpass" id="oldpass" class="form-control"  maxlength="20" value="<s:property value="oldpass" />" autocomplete="off" >
															 <s:if test="fieldErrors.get('oldpass').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('oldpass').get(0)" /></span>
											</s:if>
													
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">New Password</label>
													<div class="col-md-4">
														
															<input type="password" name="newPass" id="newPass" class="form-control"  value="<s:property value="newPass" />"  maxlength="20"  autocomplete="off" >
															   
														
															<s:if test="fieldErrors.get('newPass').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('newPass').get(0)" />
														</span>
											</s:if>
														
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">Confirm Password</label>
													<div class="col-md-4">
														
															<input type="password" name="confirmPass" id="confirmnewPass" class="form-control"  value="<s:property value="confirmPass" />" maxlength="20" autocomplete="off" >
															
															   <s:if test="fieldErrors.get('confirmPass').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('confirmPass').get(0)" /></span>
											</s:if>
														
													</div>
												</div>
											</div>
											<div class="form-actions fluid">
												<div class="col-md-offset-3 col-md-9">
													<button type="submit" class="btn blue">Save</button>
													<button type="button" class="btn default" onclick="cancelForm()">Cancel</button>
													<%-- <h1 class="intro"><s:property value="msg" /></h1> --%>
												</div>
											</div>
										</form>
										<!-- END FORM-->
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
								
