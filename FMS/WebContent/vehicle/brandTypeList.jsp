
<%@ taglib prefix="s" uri="/struts-tags"%>
<style>
.url{
    max-width: 150px;
    word-wrap: break-word;
}

</style>
<script>
	function callEdit() {
		var val;
		var cnt = $(":checkbox:checked").length;
		
		if (cnt == 0) {
			bootbox.alert("Please Select Checkbox To Edit");
		} else if (cnt > 1) {
			bootbox.alert("Please Select One Checkbox To Edit");
		} else {
			$("input[type='checkbox']:checked").each(function() {

				val = this.value;
			});
			
			if(isEligibleForOpertion(val)){
			document.getElementById("brandTypeId").value = val;
			//document.getElementById("form1").action = "editDevice.action";
			document.getElementById("form1").submit();
			}else{
				bootbox.alert("Please Select Valid Record");
			}
			/* document.forms[0].action = "EditBrandType.action?brandTypeId="
				+ val;
		document.forms[0].submit(); */
		}

		
	}
	function callCreate() {
		document.forms[0].action = "CreateBrandType.action";
		document.forms[0].submit();
	}
	function callDelete() {
		var val;
		var cnt = $(":checkbox:checked").length;
		
		if (cnt == 0) {
			bootbox.alert("Please Select Checkbox To Delete");
		} else if (cnt > 1) {
			bootbox.alert("Please Select One Checkbox To Delete");
		} else {
			$("input[type='checkbox']:checked").each(function() {

				val = this.value;
			});
			if(isEligibleForOpertion(val)){
			bootbox.confirm("Are you sure you want to Delete?",
					function(result) {

						if (result == true) {
							//alert('deleted');
							document.getElementById("delbrandTypeId").value = val;
										//document.getElementById("form1").action = "editDevice.action";
										document.getElementById("form2").submit();
							/* document.forms[0].action = "DeleteBrandType.action?delbrandTypeId="+ val;
							document.forms[0].submit(); */
						}
					});
			}else{
				bootbox.alert("Please Select Valid Record");
			}
			
		}
	}
	function check() {

		var chckbxCount = $("input:checked").length;
		if (chckbxCount > 0 && chckbxCount > 1) {
			alert("Select Only One Vehicle Type...!")
			return false;
		} else if (chckbxCount > 0 && chckbxCount == 1) {
			return true;
		} else {
			alert("Please Select One Brand Type");
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
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "BrandTypeList.action");
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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />

<div class="page-content-wrapper">
	<div class="page-content">
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
				<h3 class="page-title">
					BRAND TYPE <%-- <small> View Brand Type </small> --%>
				</h3>
			</div>
		</div>
		<%if (access.equalsIgnoreCase("Y")){%>
		<%-- <div class="row">
			<div class="col-md-12" style="color: green; list-style: none;font-weight:bold;font-size:1.1em" align="center" >
				<s:if test="%{insertstatus=='success'}">
					<font ><s:actionmessage/></font>
				</s:if>
				<s:if test="%{updatestatus=='success'}">
					<font ><s:actionmessage/></font>
				</s:if>
				<s:if test="%{deletestatus=='Record Deleted SuccessFully!'}">
					<font ><s:actionmessage/></font>
				</s:if>
			</div>
		</div> --%>
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN EXAMPLE TABLE PORTLET-->
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">

							<i class="fa fa-globe"></i>View Brand Type
						</div>
						<div class="actions">
						<%if(create.equalsIgnoreCase("Y")){ %>
							<a href="#" class="btn green" onclick="callCreate()"> <i
								class="fa fa-plus"></i> Create
							</a> &nbsp;&nbsp;
							<%}%>
                    <%if(edit.equalsIgnoreCase("Y")){ %>
                    <a  class="btn blue" onclick="callEdit()">
								<i class="fa fa-pencil"></i> Edit
							</a> &nbsp;&nbsp;
							<%}%>

                    <%if(delete.equalsIgnoreCase("Y")){ %>
							<a  class="btn red" onclick="callDelete()">
								<i class="fa fa-times"></i> Delete
							</a>
							<%}%>	
							&nbsp;&nbsp;
							<div class="btn-group">
								
									<button class="btn blue dropdown-toggle" data-toggle="dropdown"><i class="fa fa-gears"></i> Tools <i class="fa fa-angle-down"></i>
									</button>
									<ul class="dropdown-menu pull-right ">
										<li>
										<%if(status.equalsIgnoreCase("ACTIVE")){ %>
											<a class="btn blue" href="javascript: void(0)" onclick="window.open('','_new').location.href='/its/generateReport?id=BRANDTYPERPT'">
											<!-- href="/its/ReportAction!generateReport?id=1"> -->
											Report </a>
											<%} %>
										</li>	
										
																			
									</ul> 
							</div>
						</div>
					</div>
					<div class="portlet-body">
					
							<b>
								
							</b>
						<div class="portlet-body form">
							<s:if test="hasActionErrors()">
								<div class="alert alert-danger">
									<button class="close" data-close="alert"></button>
								<span id="errorMsg" style="color:red;"><s:actionerror/></span>
								</div>
							</s:if>

						</div>
						<form action="">
							<input type="hidden" id="" value="" name="vehicleTypeId">
							<font color="green" ><s:actionmessage/></font>
							<table class="table table-striped table-bordered table-hover"
								id="brandtypeListView">
								<thead>
									<tr>
										<th></th>
										<th>Brand Type Id</th>
										<th>Brand Type(Fare Type)</th>
										<th>Fare Category Type</th>
										<th>Remarks</th>
										<th>Effective Start Date</th>
										<th>Effective End Date</th>
										<th>Status</th>
										<%if(viewdeletedrecord.equalsIgnoreCase("Y")) {%>	
                              <th>Record Status</th>
<%}%>
									</tr>
								</thead>
								
							</table>
						</form>
						
					</div>
<form name="form1" id="form1" action="EditBrandType.action"
	method="POST">
	<input type="hidden" name="brandTypeId" id="brandTypeId" value="" />
</form>
<form name="form2" id="form2" action="DeleteBrandType.action"
	method="POST">
	<input type="hidden" name="delbrandTypeId" id="delbrandTypeId" />
</form>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
$(document).ready(function() {
	window.history.pushState("","", "BrandTypeList.action");
	

	   var w=$('#errorMsg span').html();
	   //alert(w);
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
