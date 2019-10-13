
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
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "createBataConfig.action");
String access=accessrights.getACCESS();
out.println("access--------"+access);
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
%>
<form>
<body>


	<div class="page-content-wrapper">
		<div class="page-content">
<%if (access.equalsIgnoreCase("Y")){ %>
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						BATA CONFIG DETAILS
					</h3>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>View BataConfig 
							</div>
							<div class="actions">
							<%if(create.equalsIgnoreCase("Y")){ %>
								<a href="#" class="btn green" onclick="callCreate()"> 
									<i	class="fa fa-plus"></i> Create
								</a>
								<%}%>
							<%-- 	<%if(edit.equalsIgnoreCase("Y")){ %>
								<a href="#" class="btn blue" onclick="callEdit()"> 
									<i	class="fa fa-pencil"></i> Edit
								</a> 
								<%}%> --%>
								<%if(delete.equalsIgnoreCase("Y")){ %>
								<a href="#" class="btn red" onclick="calldelete()">
										<i class="fa fa-times"></i> Delete
								</a>
								<%}%>
								<!-- <div class="btn-group">
                      <button class="btn green dropdown-toggle"	data-toggle="dropdown"><i class="fa fa-gears"></i> Tools <i class="fa
									fa-angle-down"></i> </button>
                                    <ul class="dropdown-menu pull-right "> 
                                        <li>
                                   
                                        
                                       Report </a>
                                     </li>

                                   </ul>
                           </div>	 -->
								
							</div>
						</div>
						<div class="portlet-body">
							<s:if test="hasActionMessages()">
								<font color="green"><b> <s:actionmessage /> </b>
								</font>
							</s:if>
							<input type="hidden" name="requestType" id="requestType" value="text" />
							<table  id="batalistView" class="table table-striped table-bordered table-hover" >
								<thead>
								
								
								
									<tr>
											<th></th>
										<th>Bata Config Id</th>
										<th>Crew Duty Type</th>
										<th>Schedule Type</th>
										<th>Service Type</th>
										<th>Min Km </th>
										<th>Max Km</th>
										<th>Day Bata</th>
										<th>Day Allowance</th>
										<th>Special Allowance</th>
										<th>Night Halt Allowance</th>
										<th>Night Sevice Allowance</th>
										<th>Effective Start Date</th>
										<th>Effective End Date</th>
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
		bootbox.confirm("Are you Sure, you want to delete this record ?",function(result) {
			if (result == true) {
				
				 document.getElementById("bataconfigiddetails").value = val;
     			document.getElementById("form2").submit(); 
			}
		});
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
		
			 document.getElementById("bataconfigid").value = val;
  			document.getElementById("form1").submit(); 
		}
	} 
	
	
	
	function callCreate() {
		document.forms[0].action = "showBataConfig.action";
		document.forms[0].submit();
	}
	$(document).ready(function() {
	   window.history.pushState("","", "createBataConfig.action");
	});
</script>

</form>
<form name="form1" id="form1" action="editBataConfig.action" method="POST">
	<input type="hidden" name="bataconfigid" id="bataconfigid" value="" />

</form>
<form name="form2" id="form2" action="deleteBataConfig.action" method="POST">
	<input type="hidden" name="bataconfigiddetails" id="bataconfigiddetails" value="" />

</form>

<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>
