
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script>
	function callEdit() {
		var val;
		//$(function() {
			var val = [];
			$(':checkbox:checked').each(function(i) {
				val = $(this).val();
			});
			if (check(0)) {
				if(isEligibleForOpertion(val)){
				document.getElementById("editVehicleTypeId").value=val;
				document.getElementById("editAction").submit();
				}else{
					bootbox.alert("Please Select Valid Record");
				}
			}
		//});

	}
	function callCreate() {
		document.forms[0].action = "CreateVehicleType.action";
		document.forms[0].submit();
	}
	function callDelete() {
		var val;
		//$(function() {
			var val = [];
			$(':checkbox:checked').each(function(i) {
				val = $(this).val();
			});
			
			if (check(0)) {
				if(isEligibleForOpertion(val)){
				bootbox	.confirm("Are you sure want to delete vehicle type?",function(result) {
					if (result == true) {
						document.getElementById("vehicleTypeId").value=val;
						document.getElementById("deleteAction").submit();
					}
			});
				}else{
					bootbox.alert("Please Select Valid Record");
				}

		}
		//});
	}
	function check() {

		var chckbxCount = $("input:checked").length;
		if (chckbxCount > 0 && chckbxCount > 1) {
			bootbox.alert("Select only one Vehicle Type...!")
			return false;
		} else if (chckbxCount > 0 && chckbxCount == 1) {
			return true;
		} else {
			bootbox.alert("Please select Vehicle Type");
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
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "VehicleType.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
String status=accessrightdao.UserStatus(usrsessionid);

%>
<form name="editAction" id='editAction' action="EditVehicleType.action" method="post">
	<input type="hidden" id='editVehicleTypeId' name="editVehicleTypeId"/>
</form>
<form name="deleteAction" id="deleteAction" action="DeleteVehicleType.action" method="post">
	<input type="hidden" id='vehicleTypeId' name="vehicleTypeId"/>
</form>

<link rel="stylesheet" 	href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
<input type="hidden" name="id" id="id" />
<div class="page-content-wrapper">
	<div class="page-content">
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
				<h3 class="page-title">
					Vehicle Type 
				</h3>
			</div>
		</div>
		<%if (access.equalsIgnoreCase("Y")){%>
		<div class="row">
			<div class="col-md-12">
				<div class="portlet box grey-cascade">

					<div class="portlet-title">
						<div class="caption">

							<i class="fa fa-globe"></i> View Vehicle Type
						</div>
						<div class="actions">
						<%if(create.equalsIgnoreCase("Y")){ %>
							<a href="#" class="btn green" onclick="callCreate()"> <i
								class="fa fa-plus"></i> Create
							</a> 
							<%}%>
<%if(edit.equalsIgnoreCase("Y")){ %>
							<a href="#" class="btn blue" onclick="callEdit()"> <i
								class="fa fa-pencil"></i> Edit
							</a> 
							<%}%>

<%if(delete.equalsIgnoreCase("Y")){ %><a href="#" class="btn red" onclick="callDelete()"> <i
								class="fa fa-times"></i> Delete
							</a>
							<%}%>
							 <div class="btn-group">
                      <button class="btn blue dropdown-toggle"    data-toggle="dropdown"><i class="fa fa-gears"></i> Tools <i class="fa
                                    fa-angle-down"></i> </button>
                                    <ul class="dropdown-menu pull-right ">
                                    
                                        <li>
                                        <%if(status.equalsIgnoreCase("ACTIVE")){ %>
                                    <a class="btn blue" href="javascript: void(0)"onclick="window.open('','_new').location.href='/its/generateReport?id=VEHICLETYPERPT'">
                                        
                                       Report </a>
                                       <%} %>
                                     </li>

                                   </ul>
                           </div>

						</div>
					</div>
					<div class="portlet-body">
						<form action="">
							<input type="hidden" id="" value="" name="vehicleTypeId"/>
							<b>
								<font color="green"> <s:actionmessage/></font>
							<span id="errorMsg" style="color:red;"><s:actionerror/></span>
							</b>
							<table class="table table-striped table-bordered table-hover" id="vehicleTypeTable">
								
								<thead>
									<tr>
										<th></th>
										<th>Vehicle Type Id</th>
										<th>Vehicle Type</th>
										<th>Remarks</th>
										<th>Status</th>
										<%if(viewdeletedrecord.equalsIgnoreCase("Y")) {%>	
<th>Record Status</th>
<%}%>
										
									</tr>
								</thead>
								<%--<tbody>
									 <s:if test="%{vehicleTypeList.isEmpty}%"></s:if>
									<s:else>
										<s:iterator value="vehicleTypeList">
											<tr>
												<td><input type="checkbox" class="checkboxes"
													value="<s:property value="vehicle_type_id" />" /></td>
												<td><s:property value="vehicle_type_id" /></td>
												<td><s:property value="vehicle_type_name" /></td>
												<td><s:property value="notes" /></td>
												<td><s:property value="status" /></td>
											</tr>
										</s:iterator>
									</s:else> 
								</tbody>--%>
							</table>
						</form>
					</div>

				</div>
			</div>
		</div>
	</div>
</div>
<script>
$(document).ready(function() {
	window.history.pushState("","", "VehicleType.action");
	var w=$('#errorMsg span').html();
	 
	    w=w.replace(/@/g,"<br>");

	   $('#errorMsg').html(''+w+'');
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
<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>

