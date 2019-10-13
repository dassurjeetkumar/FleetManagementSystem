
<%@ taglib prefix="s" uri="/struts-tags"%>
 <%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>	
<script>
$(document).ready(function() {
	   window.history.pushState("","", "ShowEmployeeTypeList.action");
	 });



function getDisplayEmployeeType() {
	
	document.forms[0].action = "ShowEmployeeType.action";
	document.forms[0].submit();

}

function getDeleteRecord() {
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

		bootbox.confirm("Are you sure want to Delete Employee Type?",
						function(result) {

							if (result == true) {
								//alert('deleted');
								document.forms[0].action = "DeleteEmployeeTypeRecord.action?employeeid="+val;
								document.forms[0].submit();
							}
						});

		
	}

}



function getmodifyrecord() {

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
		document.forms[0].action = "ModifyEmployeeTypeDetails.action?employeeid="+val;
		document.forms[0].submit();

	}
	
	
}
</script>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
<form>
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ShowEmployeeTypeList.action");
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
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
				<h3 class="page-title">
					EMPLOYEE TYPE
				</h3>
				
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN EXAMPLE TABLE PORTLET-->
				<div class="portlet box grey-cascade">
						<div class="portlet-title">
						<div class="caption">

							<i class="fa fa-globe"></i> View Employee Type
						</div>
						
 						<div class="actions">
 							<a href="#" class="btn green" onclick="getDisplayEmployeeType()"> <i 
 									class="fa fa-plus"></i> Create 
 								</a>
 							
 							 <a  class="btn red" id="employeeid" onclick="getDeleteRecord()"> 
 									<i class="fa fa-pencil"></i> Delete 
 								</a> 
							
 						</div> 
					</div>
					<div class="portlet-body">
						
					
					<s:if test="hasActionMessages()">
					<font color="green"> <s:actionmessage />
					
				</font>
					</s:if>
					
							
							<table class="table table-striped table-bordered table-hover"
								id="employeetypeview">
								<thead>
									<tr>
										<th></th>
										<th>EmployeeType Id</th>
										<th>EmployeeType Name</th>
										<th>Status</th>
										<th>Remarks</th>
									</tr>
								</thead>
								
							</table>
						
					</div>

				</div>
			</div>
		</div>
	</div>
</div>
<s:token/>
</form>
<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>
