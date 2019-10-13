
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "CaseTypeList.action");
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
<script>
	function callEdit() {
		var val;
		$(function() {
			var val = [];
			$(':checkbox:checked').each(function(i) {
				val = $(this).val();
			});
			if (check(0)) {
				if(isEligibleForOpertion(val)){
				document.getElementById("editCaseTypeId").value=val;
				document.getElementById("editAction").submit();
				}else{
					bootbox.alert("Please Select Valid Record");
				}
			}
		});

	}
	function callCreate() {
		document.forms[0].action = "CreateCaseType.action";
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
				
				if(isEligibleForOpertion(val)){
				bootbox.confirm("Are you sure you want to delete Case Type?",function(result) {
					if (result == true) {
				document.getElementById("caseTypeId").value=val;
				document.getElementById("deleteAction").submit();
					}
				});
				}else{
					bootbox.alert("Please Select Valid Record");
				}
			}
		});
	}
	function check() {

		var chckbxCount = $("input:checked").length;
		if (chckbxCount > 0 && chckbxCount > 1) {
			bootbox.alert("Select only one Case");
			return false;
		} else if (chckbxCount > 0 && chckbxCount == 1) {
			return true;
		} else {
			bootbox.alert("Please Select Case");
			return false;
		}

	}
</script>

<style>
.url {
    max-width: 150px;
    word-wrap: break-word;
}
</style>

<form name="editAction" id='editAction' action="EditCaseType.action" method="post">
	<input type="hidden" id=editCaseTypeId name="editCaseTypeId"/>
</form>
<form name="deleteAction" id="deleteAction" action="DeleteCaseType.action" method="post">
	<input type="hidden" id='caseTypeId' name="caseTypeId"/>
</form>


<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />

<div class="page-content-wrapper">
	<div class="page-content">
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
				<h3 class="page-title">
					CASE TYPE
				</h3>
			</div>
		</div>
		
		<%if (access.equalsIgnoreCase("Y")){%>
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN EXAMPLE TABLE PORTLET-->
				
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">

							<i class="fa fa-globe"></i>View Case Type
						</div>
						<div class="actions">
							<%if(create.equalsIgnoreCase("Y")){ %>
							<a href="#" class="btn green" onclick="callCreate()"> <i
								class="fa fa-plus"></i> Create
							</a> &nbsp;&nbsp;
							<%}%>
									<%if(edit.equalsIgnoreCase("Y")){ %>
							
							<a href="#" class="btn blue" onclick="callEdit()"> <i
								class="fa fa-pencil"></i> Edit
							</a> &nbsp;&nbsp;
								<%}%>
							  <%if(delete.equalsIgnoreCase("Y")){%>
							<a href="#" class="btn red" onclick="callDelete()"> <i
								class="fa fa-times"></i> Delete
							</a>&nbsp;&nbsp;
								<%}%>
<div class="btn-group">
                      <button class="btn green dropdown-toggle"    data-toggle="dropdown"><i class="fa fa-gears"></i> Tools <i class="fa fa-angle-down"></i> </button>
                                    <ul class="dropdown-menu pull-right ">
                                        <li>
                                        <%if(status.equalsIgnoreCase("ACTIVE")){ %>
                                    <a class="btn blue" href="javascript: void(0)" onclick="window.open('','_new').location.href='/its/generateReport?id=CASETYPERPT'">
                                        
                                       Report </a>
                                       <%} %>
                                     </li>

                                   </ul>
                           </div>
						
							</div>
						</div>
					
					<div class="portlet-body">
						<form action="" method="post">
							<b>
								<font color="green"> <s:actionmessage/></font>
								<font color="red"> <s:actionerror/></font>
							</b>
							<input type="hidden" id="" value="" name="vehicleTypeId">
							<table class="table table-striped table-bordered table-hover"
								id="caseTypeTable">
								<thead>
									<tr>
										<th></th>
										<th>Case Type Id</th>
										<th>Case Type</th>
										<th>Remarks</th>
										<th>Status</th>
										<%if(viewdeletedrecord.equalsIgnoreCase("Y")) {%>	
													<th>Record Status</th>
											<%}%>
									</tr>
								</thead>
								<%-- <tbody>
									<s:if test="%{CaseTypeList.isEmpty}%"></s:if>
									<s:else>
										<s:iterator value="CaseTypeList">
											<tr>
												<td><input type="checkbox" class="checkboxes"
													value="<s:property value="case_type_id" />" /></td>
												<td><s:property value="case_type_id" /></td>
												<td><s:property value="case_type_name" /></td>
												<td><s:property value="notes" /></td>
											</tr>
										</s:iterator>
									</s:else>
								</tbody> --%>
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
	window.history.pushState("","", "CaseTypeList.action");
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
