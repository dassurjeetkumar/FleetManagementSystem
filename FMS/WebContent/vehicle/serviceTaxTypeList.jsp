<head>
<script src="assets/admin/pages/scripts/table-managed-device.js" type="text/javascript"></script>
</head>

<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
	<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ServiceTypeList.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
String status=accessrightdao.UserStatus(usrsessionid);
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
%>
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
				document.getElementById("editServiceTaxTypeId").value=val;
				document.getElementById("editAction").submit();
				}else{
					bootbox.alert("Please Select Valid Record");
				}
			}
		//});

	}
	function callCreate() {
		document.forms[0].action = "CreateServiceTaxType.action";
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
				bootbox	.confirm("Are you sure you want to delete Service Tax Type?",function(result) {
				if (result == true) {
					document.getElementById("serviceTaxTypeId").value=val;
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
			bootbox.alert("Select only one Service Tax Type...!")
			return false;
		} else if (chckbxCount > 0 && chckbxCount == 1) {
			return true;
		} else {
			bootbox.alert("Please select Service Tax Type");
			return false;
		}

	}
	
</script>
<style>
.url {
    width: 350px;
    word-wrap: break-word;
}
</style>
<form name="editAction" id='editAction' action="EditServiceTaxTypeList.action" method="post">
	<input type="hidden" id='editServiceTaxTypeId' name="editServiceTaxTypeId"/>
</form>
<form name="deleteAction" id="deleteAction" action="DeleteServiceTaxTypeList.action" method="post">
	<input type="hidden" id='serviceTaxTypeId' name="serviceTaxTypeId"/>
</form>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />

<div class="page-content-wrapper">
	<div class="page-content">
		<%if (access.equalsIgnoreCase("Y")){%>
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
				<h3 class="page-title">
					SERVICE TAX TYPE
				</h3>
			</div>
		</div>
	
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN EXAMPLE TABLE PORTLET-->
				<div class="portlet box grey-cascade">
						<div class="portlet-title">
						<div class="caption">

							<i class="fa fa-globe"></i> View Service Tax Type
						</div>
						<div class="actions">
						<%if(create.equalsIgnoreCase("Y")){ %>
							<a href="#" class="btn green" onclick="callCreate()"> <i
								class="fa fa-plus"></i> Create
							</a> &nbsp;&nbsp;
							<%}%>
<%if(edit.equalsIgnoreCase("Y")){ %><a href="#" class="btn blue" onclick="callEdit()"> <i
								class="fa fa-pencil"></i> Edit
							</a> &nbsp;&nbsp;<%}%>

<%if(delete.equalsIgnoreCase("Y")){ %><a href="#" class="btn red" onclick="callDelete()"> <i
								class="fa fa-times"></i> Delete
							</a>&nbsp;&nbsp;
							
<%}%>
<!-- 							<div class="btn-group"> -->
								
<!-- 									<button class="btn green dropdown-toggle" data-toggle="dropdown"><i class="fa fa-gears"></i> Tools <i class="fa fa-angle-down"></i> -->
<!-- 									</button> -->
<!-- 									<ul class="dropdown-menu pull-right "> -->
<!-- 										<li> -->
<%-- 										<%if(status.equalsIgnoreCase("ACTIVE")){ %> --%>
<!-- 											<a class="btn blue" href="javascript: void(0)" onclick="window.open('','_new').location.href='/its/generateReport?id=SERVICETYPERPT'"> -->
<!-- 											href="/its/ReportAction!generateReport?id=1"> -->
<!-- 											Report </a> -->
<%-- 											<%} %> --%>
<!-- 										</li>	 -->
										
																			
<!-- 									</ul>  -->
<!-- 							</div> -->
						</div>
					</div>
					<div class="portlet-body">
						<form>
							<b>
								<font color="green"> <s:actionmessage/></font>
								<span id="errorMsg" style="color:red;"><s:actionerror/></span>
								
							</b>
							<input type="hidden" id="" value="" name="vehicleTypeId">
							<table class="table table-striped table-bordered table-hover"
								id="serviceTaxTypeTable">
								<thead>
									<tr>
										<th></th>
										<th>Service Tax Id</th>
										<th>Tax Type</th>
										<th>Service Type</th>
										<th>Ticket Type</th>
										<th>Base Value(in %)</th>
										<th>Service Tax(in %)</th>
										<th>Effective Start Date</th>
										<th>Effective End Date</th>
										<th>Status</th>
<!-- 										<th>Record  Status</th> -->
										<%if(viewdeletedrecord.equalsIgnoreCase("Y")) {%>	
<th>Record Status</th>
<%}%>
									</tr>
								</thead>
								<tbody>
									<s:if test="%{serviceTypeList.isEmpty}%"></s:if>
									<s:else>
										<s:iterator value="serviceTypeList">
											<tr>
												<td><input type="checkbox" class="checkboxes"
													value="<s:property value="service_tax_id" />" /></td>
												<td><s:property value="service_type_id" /></td>
												<td><s:property value="tax_type_name" /></td>
												<td><s:property value="service_type_name" /></td>
												<td><s:property value="ticket_type_name" /></td>
												<td><s:property value="base_value" /></td>
												<td><s:property value="service_tax_percentage" /></td>
												<td><s:property value="effective_start_date" /></td>
												<td><s:property value="effective_end_date" /></td>
												<td><s:property value="status" /></td>
											</tr>
										</s:iterator>
									</s:else>
								</tbody>
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
	window.history.pushState("","", "ServiceTaxTypeList.action");
	
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
<script>
								
								function errMsg(){
								
								}
								</script>
<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>