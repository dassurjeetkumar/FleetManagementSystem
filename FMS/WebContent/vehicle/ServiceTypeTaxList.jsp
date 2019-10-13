<%-- <%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
	<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ServiceTypeTaxList.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();remark
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
String status=accessrightdao.UserStatus(usrsessionid);
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
%>  --%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script>
	function callEdit() {
// 		var val;
		//$(function() {
			var val = [];
			$(':checkbox:checked').each(function(i) {
				val = $(this).val();
			});
			if (check(0)) {
				if(isEligibleForOpertion(val)){
				document.getElementById("editServiceTypeId").value=val;
				document.getElementById("editAction").submit();
				}else{
					bootbox.alert("Please Select Valid Record");
				}
			}
		

	}
	function callCreate() {
		document.forms[0].action = "CreateTaxServiceType.action";
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
				bootbox	.confirm("Are you sure you want to delete Service Type?",function(result) {
				if (result == true) {
					document.getElementById("serviceTypeId").value=val;
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
			bootbox.alert("Select only one Service Type...!")
			return false;
		} else if (chckbxCount > 0 && chckbxCount == 1) {
			return true;
		} else {
			bootbox.alert("Please select Service Type");
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
<form name="editAction" id='editAction' action="EditTaxOfServiceType.action" method="post">
	<input type="hidden" id='editServiceTypeId' name="editServiceTypeId"/>
</form>
<form name="deleteAction" id="deleteAction" action="DeleteServiceTax.action" method="post">
	<input type="hidden" id='serviceTypeId' name="serviceTypeId"/>
</form>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />

<div class="page-content-wrapper">
	<div class="page-content">
		<%/* if (access.equalsIgnoreCase("Y")){ */%>
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
				<h3 class="page-title">
					SERVICE TAX
				</h3>
			</div>
		</div>
	
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN EXAMPLE TABLE PORTLET-->
				<div class="portlet box grey-cascade">
						<div class="portlet-title">
						<div class="caption">

							<i class="fa fa-globe"></i> View Service Tax
						</div>
						<div class="actions">
						<%/* if(create.equalsIgnoreCase("Y")){ */ %>
							<a href="#" class="btn green" onclick="callCreate()"> <i
								class="fa fa-plus"></i> Create
							</a> &nbsp;&nbsp;
							<%/* } */%>
<%/* if(edit.equalsIgnoreCase("Y")){ */ %><a href="#" class="btn blue" onclick="callEdit()"> <i
								class="fa fa-pencil"></i> Edit
							</a> &nbsp;&nbsp;<%/* } */%>

<%-- <%if(delete.equalsIgnoreCase("Y")){ %> --%>
						<a href="#" class="btn red" onclick="callDelete()"> 	<i	class="fa fa-times"></i> Delete
							</a>&nbsp;&nbsp;
							
<%/* } */%>
						<%-- 	<div class="btn-group">
								
									<button class="btn green dropdown-toggle" data-toggle="dropdown"><i class="fa fa-gears"></i> Tools <i class="fa fa-angle-down"></i>
									</button>
									<ul class="dropdown-menu pull-right ">
										<li>
										<%/* if(status.equalsIgnoreCase("ACTIVE")){  */%>
											<a class="btn blue" href="javascript: void(0)" onclick="window.open('','_new').location.href='/its/generateReport?id=SERVICETYPERPT'">
											<!-- href="/its/ReportAction!generateReport?id=1"> -->
											Report </a>
											<%/* } */ %>
										</li>	
										
																			
									</ul> 
							</div> --%>
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
								id="serviceTypeTaxTable">
								<thead>
									<tr>
										<th></th>
										<th>Service Type Id</th>
										<th>Service Type(Fare Category Type)</th>
										<th>Base Value (%)</th>
<!-- 										<th>Remarks</th> -->
										<th>Status</th>
<%-- 										<%/* if(viewdeletedrecord.equalsIgnoreCase("Y")) { */%>	 --%>
<!-- <th>Record Status</th> -->
<%-- <%/* } */%> --%>
									</tr>
								</thead>
								<tbody>
									<s:if test="%{ServiceTypeTaxList.isEmpty}%"></s:if>
									<s:else>
										<s:iterator value="ServiceTypeTaxList">
											<tr>
												<td><input type="checkbox" class="checkboxes"
													value="<s:property value="service_type_id" />" /></td>
												<td><s:property value="service_type_id" /></td>
												<td><s:property value="service_type_name" /></td>
												<td><s:property value="baseValue" /></td>
<%-- 												<td><s:property value="note" /></td> --%>
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
	window.history.pushState("","", "ServiceTypeTaxList.action");
	
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
<%/* }else{ */%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%/* } */%>