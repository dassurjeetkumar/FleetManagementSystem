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

function goView() {
	

		/* var val = $("input[type='checkbox']").val(); */
		//alert(val);
		document.forms[0].action = 'viewGroup.action';
		document.forms[0].submit();

	
}



function grouprole(){
	//$('#grouproledetails').click(function() {

		var groupid=document.getElementById("group_id").value;
		var groupname =document.getElementById("groupName").value;
		document.getElementById("groupid").value = groupid;
		//document.getElementById("groupname").value = groupname;
		document.getElementById("form1").submit();

	//});
}


/* function getSave(){
	var status=document.getElementById("status").value;
	var groupid=document.getElementById("group_id").value;
	var groupname =document.getElementById("groupName").value;
	if(status=='INACTIVE'){
		alert("test");
		  $.ajax({
                type: "POST",
                url: "getInactiveForGroup.action?groupid="+groupid,
                success: function(response){
                	alert(response);
                	if(response ==  0 )
                		{
                		document.getElementById("groupiddetails").value = groupid;
						document.getElementById("groupname").value=groupname;
						document.getElementById("statusdetails").value=status;
						document.getElementById("form2").submit();
                		}else{
                	bootbox.alert(response+"So can not able Inactive",
							function(result) {
					
					if (result == true) {
						
					}else{
					
					}
					});
                }
                	
                }
		  });
		
	}else{
		document.getElementById("groupiddetails").value = groupid;
		document.getElementById("groupname").value=groupname;
		document.getElementById("statusdetails").value=status;
		document.getElementById("form2").submit();
	}
	
	
} */



function getSave() {
	var msg=document.getElementById("msg").value;
	
	var status=document.getElementById("status").value;
	var groupid=document.getElementById("group_id").value;
	var groupname =document.getElementById("groupName").value;
	
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

</script>
<style>
h1.intro {color:red;font-size:14px;}

</style>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
</head>
<body onload="clearForm()">
<div class="page-content-wrapper">
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

	<div class="page-content">
<div class="tab-content">
<%if (access.equalsIgnoreCase("Y")){%>
	<%if(edit.equalsIgnoreCase("Y")){ %>
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
											<i class="fa fa-gift"></i>Edit User Group
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
										<form  class="form-horizontal" method="post" action="editGroupDetails.action">
											<div class="form-body">
											
											<div class="form-group">
													<label class="col-md-3 control-label">Group Id</label>
													   <div class="col-md-4">
														 <div class="input-group">
																<input type="text" class="form-control"  value="<s:property value="groupMaster.group_id" />"  readonly="readonly"  >
														    <input type="hidden" class="form-control" id="msg" name="groupMaster.msg"
												value='<s:property value="groupMaster.msg"/>'>
														</div>
													 </div>
												 </div>
											
											
											
												<div class="form-group">
													<label class="col-md-3 control-label">Group name<font color="red">*</font></label>
													   <div class="col-md-4">
														 <div class="input-group">
															 <input type="text" name="groupMaster.group_name"  maxlength="50" id="groupName" class="form-control" value="<s:property value="groupMaster.group_name" />">
														    	<input type="hidden" name="groupMaster.group_id" id="group_id" value="<s:property value="groupMaster.group_id" />">
														    	 <s:if test="fieldErrors.get('groupName').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('groupName').get(0)" /></span>
									</s:if>	 	
															
														</div>
													 </div>
												 </div>
												 <div class="form-group">
													<label class="col-md-3 control-label">Status<font color="red">*</font></label>
													   <div class="col-md-4">
														 <div class="input-group">
															
												
													 <select class="form-control" id="status" name="groupMaster.status" >
													 	<option id="select"  value="0">select</option>
														<option id="ACTIVE"  value="ACTIVE">ACTIVE</option>
														<option id="INACTIVE" value="INACTIVE">INACTIVE</option>
													</select> 
													<script>
														var status = "<s:property value="groupMaster.status"/>";
															if (status != undefined) {
																if (status == "ACTIVE") {
																	document.getElementById("ACTIVE").selected = true;
																} else if (status == "INACTIVE"){
																	document.getElementById("INACTIVE").selected = true;
																}else {
																	document.getElementById("select").selected = true;
																}
															}
															</script>
												 <s:if test="fieldErrors.get('status').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('status').get(0)" /></span>
									</s:if>	 	
														    	
														</div>
													 </div>
												 </div>											
											
											<div class="form-actions fluid">
												<div class="col-md-offset-3 col-md-9">
													<button type="submit" class="btn blue" onclick="return getSave()">Save</button>
													<button type="button" class="btn default" id="cancel" onclick="goView()">Cancel</button>
														<button type="button" class="btn default" id="grouproledetails" onclick="grouprole()">Assign Role to Group</button>
													
												</div>
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
								
				
								
					
								
								<form name="form1" id="form1" action="grouprole.action" method="POST">
	<input type="hidden" name="groupid" id="groupid" value="" />
	

</form>		

<form name="form2" id="form2" action="editGroupDetails.action" method="POST">
	<input type="hidden" name="groupname" id="groupname"  />
	<input type="hidden" name="groupiddetails" id="groupiddetails" />
	<input type="hidden" name="statusdetails" id="statusdetails"  />
</form> 		
			
			
			</body>
								</html>	
								
