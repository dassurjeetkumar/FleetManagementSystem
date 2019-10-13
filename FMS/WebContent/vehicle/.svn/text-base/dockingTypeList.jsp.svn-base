
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<script>
	function callEdit() {
		var val;
		$(function() {
			var val = [];
			$(':checkbox:checked').each(function(i) {
				val = $(this).val();
			});
			if (check(0)) {

				document.getElementById("dockingTypeId").value=val;
				document.getElementById("editAction").submit();
			}
		});

	}
	function callCreate() {
		document.forms[0].action = "CreateDockingType.action";
		document.forms[0].submit();
	}
	function callDelete() {
		var val;
		$(function() {
			var val = [];
			$(':checkbox:checked').each(function(i) {
				val = $(this).val();
			});
			if (check(0)) {
				bootbox.confirm("Are you sure you want to delete Docking Type?",function(result) {
					if (result == true) {
				document.forms[0].action = "DeleteModelType.action?dockinTypeId="+ val;
				document.forms[0].submit();
					}
				});
			}
		});
	}
	function check() {

		var chckbxCount = $("input:checked").length;
		if (chckbxCount > 0 && chckbxCount > 1) {
			bootbox.alert("Select only one Docking Type");
			return false;
		} else if (chckbxCount > 0 && chckbxCount == 1) {
			return true;
		} else {
			bootbox.alert("Please Select Docking Type");
			return false;
		}

	}
</script>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
	<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "DockingTypeList.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String status=accessrightdao.UserStatus(usrsessionid);
String viewdelete=accessrights.getVIEWDELETE();
%>

<form name="editAction" id='editAction' action="EditDockingType.action" method="post">
	<input type="hidden" id=dockingTypeId name="dockingTypeId"/>
</form>
<div class="page-content-wrapper">
		<div class="page-content">
			<div class="row">
				<div class="col-md-12">
					<h3 class="page-title">
						Docking Type
					</h3>
			</div>
			<%if (access.equalsIgnoreCase("Y")){%>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>
								View Docking Type
							</div>
							<div class="actions">
							<%if(create.equalsIgnoreCase("Y")){ %>
							<a href="javascript: void(0)" class="btn green" onclick="callCreate()"> 
								<i class="fa fa-plus"></i> Create
							</a><%}%>
<%if(edit.equalsIgnoreCase("Y")){ %>
							<a href="javascript: void(0)" class="btn blue" onclick="callEdit()">
								<i	class="fa fa-pencil"></i> Edit
							</a>
							<%}%>

							
								<!--  <a  class="btn red" id="deleteDevice" onclick="calldelete()">
									<i class="fa fa-pencil"></i> Delete
								</a> -->
								
								 <div class="btn-group">
                      <button class="btn green dropdown-toggle"    data-toggle="dropdown"><i class="fa fa-gears"></i> Tools <i class="fa
                                    fa-angle-down"></i> </button>
                                    <ul class="dropdown-menu pull-right ">
                                    <%if(status.equalsIgnoreCase("ACTIVE")){ %>
                                        <li>
                                    <a class="btn blue" href="javascript: void(0)"onclick="window.open('','_new').location.href='/its/generateReport?id=DOCKINGTYPERPT'">
                                        
                                       Report </a>
                                     </li>
                                        <%} %>
                                   </ul>
                           </div>

							</div>
						</div>
						<div class="portlet-body">
						<%-- <s:if test="%{insertstatus=='success'}">
								<div class="alert alert-danger">
									<button class="close" data-close="alert"></button>
									<span> Docking Type Created SuccessFully
									</span>
								</div>


							</s:if>
							<input type="hidden" name="requestType" id="requestType"
								value="text" /> --%>
								<s:if test="%{returnString=='success'}">
								<B><font color="green"><s:actionmessage /></font></B>
							</s:if>						

							<%-- <s:if test="%{returnString=='fail'}">
								<font color="green">Docking Type could not Inserted
									Please Try after Sometime!!</font>
							</s:if> --%>
							 <s:if test="%{returnString=='fail'}">
								<font color="red">Docking Type could not Deleted Please
									Try after Sometime!!</font>
							</s:if> 
							
								
							<table class="table table-striped table-bordered table-hover"
								id="dockingtypeView">
								<thead>
									<tr>
										<th style="width1: 8px;"></th>
										<th>Docking Type Id</th>
										<th>Docking Type Name</th>
										<th>Docking KM </th>
										<th>KM limit for Alert</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
</div>
<script>
$(document).ready(function() {
	window.history.pushState("","", "DockingTypeList.action");
	
});

</script>
<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>