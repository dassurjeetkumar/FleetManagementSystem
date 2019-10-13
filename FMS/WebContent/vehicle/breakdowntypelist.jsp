
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
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "showBreakdowntypeList.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String status=accessrightdao.UserStatus(usrsessionid);
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
%>	
<style>
.url{
    max-width: 150px;
    word-wrap: break-word;
}

</style>
	
	 <form>
<body>
	<div class="page-content-wrapper">
		<div class="page-content">
		
		<%if (access.equalsIgnoreCase("Y")){ %>
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						BREAKDOWN TYPE
					</h3>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>View Breakdown Type
							</div>
							<div class="actions">
							<%if(create.equalsIgnoreCase("Y")){ %>
								<a href="#" class="btn green" onclick="callCreate()"> 
									<i	class="fa fa-plus"></i> Create
								</a>
								<%}%>
								<%if(edit.equalsIgnoreCase("Y")){ %>
								<a href="#" class="btn blue" onclick="callEdit()"> 
									<i	class="fa fa-pencil"></i> Edit
								</a> 
								<%}%>
								<%if(delete.equalsIgnoreCase("Y")){ %>
								<a href="#" class="btn red" onclick="calldelete()">
										<i class="fa fa-times"></i> Delete
								</a>
								<%}%>	
								<div class="btn-group">
                      <button class="btn green dropdown-toggle"	data-toggle="dropdown"><i class="fa fa-gears"></i> Tools <i class="fa
									fa-angle-down"></i> </button>
                                    <ul class="dropdown-menu pull-right "> 
                                        <li>
                                        <%if(status.equalsIgnoreCase("ACTIVE")){ %>
                                    <a class="btn blue" href="javascript: void(0)"onclick="window.open('','_new').location.href='/its/generateReport?id=BREAKDOWNTYPERPT'">
                                        
                                       Report </a>
                                       <%} %>
                                     </li>

                                   </ul>
                           </div>	
								
							</div>
						</div>
						<div class="portlet-body">
							<s:if test="hasActionMessages()">
								<font color="green"><b> <s:actionmessage /> </b>
								</font>
							</s:if>
							<input type="hidden" name="requestType" id="requestType" value="text" />
							<table  id="breakdowntypelistView" class="table table-striped table-bordered table-hover" >
								<thead>
									<tr>
											<th style="width1: 8px;"></th>
										<th>Breakdown Type Id   </th>
										<th>Breakdown Name  </th>
										<th>Breakdown Category  </th>
										<th>System Type   </th>
										<th>Sub System Type  </th>
										<th>Remarks  </th>
										<%if(viewdeletedrecord.equalsIgnoreCase("Y")) {%>	
										<th> Record Status</th>
										<%}%>
										
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
function calldelete() {
	var cnt = $(":checkbox:checked").length;
	if (cnt == 0) {
		bootbox.alert("Please Select Checkbox To Delete");
	}else if (cnt > 1) {
		bootbox.alert("Please Select One Checkbox To Delete");
	}else {
		$("input[type='checkbox']:checked").each(function() {
			val = this.value;
		});
		if(isEligibleForOpertion(val)){
		bootbox.confirm("Are you Sure, you want to delete this record ?",function(result) {
			if (result == true) {
				/* document.forms[0].action = "deleteBreakdownType.action?breakdowntypeid="+ val;
				document.forms[0].submit(); */
				 document.getElementById("breakdowntypeid").value = val;
     			document.getElementById("form1").submit(); 
			}
		});
		
		}else{
			bootbox.alert("Please Select Valid Record");
		}
	}
}
function callEdit(){
	var cnt = $(":checkbox:checked").length;
	if (cnt == 0) {
		bootbox.alert("Please Select Checkbox To Edit");
		} else if (cnt > 1) {
			bootbox.alert("Please Select One Checkbox To Edit");
		} else {
			$("input[type='checkbox']:checked").each(function() {
				val = this.value;
			});
		/* 	document.forms[0].action = "editBreakdownType.action?breakdowntypeid="+ val;
			document.forms[0].submit(); */
			if(isEligibleForOpertion(val)){
			 document.getElementById("breakdowntypeiddetails").value = val;
  			document.getElementById("form2").submit(); 
			}else{
				bootbox.alert("Please Select Valid Record");
			}
		}
	}
	function callCreate() {
		document.forms[0].action = "showbreakdowntype.action";
		document.forms[0].submit();
	}
	$(document).ready(function() {
	   window.history.pushState("","", "showBreakdowntypeList.action");
	});
	function isEligibleForOpertion(id){
		 var isEligible = $('#isRocordEligible'+id).val();
		 if(isEligible == undefined || isEligible=='Y'){
			 return true;
		 }else{
			 return false;
		 }
	}
</script>

</form>
<form name="form1" id="form1" action="deleteBreakdownType.action" method="POST">
	<input type="hidden" name="breakdowntypeid" id="breakdowntypeid" value="" />

</form>
<form name="form2" id="form2" action="editBreakdownType.action" method="POST">
	<input type="hidden" name="breakdowntypeiddetails" id="breakdowntypeiddetails" value="" />

</form>
<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>