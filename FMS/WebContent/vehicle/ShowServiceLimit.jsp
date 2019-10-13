
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
<script src="https://code.jquery.com/jquery-1.11.3.min.js" type="text/javascript"></script>


<script>                 	

   
function callCreate() {
	document.forms[0].action = "getCreateServiceLimit.action";
	document.forms[0].submit();
}
// $(document).ready(function() {
// 	   window.history.pushState("","", "BrandTypeServiceLimit.action");
// 	 });

 function isEligibleForOpertion(id){
	 var isEligible = $('#isRocordEligible'+id).val();
	 if(isEligible == undefined || isEligible=='Y'){
		 return true;
	 }else{
		 return false;
	 }
 }
	function check() {
		// alert("in check");
				var chckbxCount = $("input:checked").length;
				if (chckbxCount > 0 && chckbxCount > 1) {
					bootbox.alert("Select only one Brand Type");
					return false;
				} else if (chckbxCount > 0 && chckbxCount == 1) {
					return true;
				} else {
					bootbox.alert("Please Select Brand Type ");
					return false;
				}

			}
   
   
   
   </script>


<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
	<%
		AccessRightsDao accessrightdao = new AccessRightsDao();
		AccessRights accessrights = new AccessRights();
		int usrsessionid = (Integer) request.getSession().getAttribute(
				"userid");
		accessrights = accessrightdao.accessRightsdetails(usrsessionid,
				"BrandTypeServiceLimit.action");
		String access = accessrights.getACCESS();
		String create = accessrights.getCREATE();
		String edit = accessrights.getEDIT();
		String delete = accessrights.getDELETE();
		String read = accessrights.getREAD();
		String error = accessrights.getERROR();
		String viewdelete = accessrights.getVIEWDELETE();
		String status = accessrightdao.UserStatus(usrsessionid);

		String viewdeletedrecord = (String) request.getSession()
				.getAttribute("viewdeletedrecord");
	%>

<style>
.url {
    max-width: 150px;
    word-wrap: break-word;
}

</style>
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />

<form name="createAction" id='createAction'  method="post">
	<input type="hidden" id='createMapId' name="createMapId"/>
</form>

<div class="page-content-wrapper">
	<div class="page-content">
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
				<h3 class="page-title">
					Service Limit
				</h3>
			</div>
		</div>
		
<%-- <%if (access.equalsIgnoreCase("Y")){%> --%>
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN EXAMPLE TABLE PORTLET-->
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">

							<i class="fa fa-globe"></i>View Service Limit
						</div>
						<div class="actions">
<%-- 						<%if(create.equalsIgnoreCase("Y")){ %> --%>
							<a href="#" class="btn green" onclick="callCreate()"> <i
								class="fa fa-plus"></i> Create
							</a> &nbsp;&nbsp;
<%-- 							<%}%> --%>

                      
 						</div> 
					</div>
					
					<div class="portlet-body">
						<form action="" method="post">
						<B><font color="green"><s:actionmessage /></font></B>
							<s:if test="hasActionErrors()">
								<div class="alert alert-danger">
								
									<button class="close" data-close="alert"></button>
								<span id="errorMsg" style="color:red;word-wrap: break-word;"><s:actionerror/></span>
								</div>
								</s:if>
								<b>
<%-- 								<span id="errorMsg" style="color:red;word-wrap: break-word;" ><s:actionerror/></span> --%>
							</b>
							<input type="hidden" id="" value="" name="vehicleTypeId">
							<table class="table table-striped table-bordered table-hover"
								id="ServiceLimitTable" name="ServiceLimitTable">
								<thead>
									<tr>
										<th></th>
										<th>Brand Type Id</th>
										<th>Brand Type Name </th>
										<th>Brand Service</th>
										<th>Note</th>
									<%-- 	<th>Status</th>
										<%if(viewdeletedrecord.equalsIgnoreCase("Y")) {%>	
<th>Record Status</th>
<%}%>
										 --%>
									</tr>
									
								</thead>
								<tr>
<!-- 									<button type="button" class="btn blue" onClick="ExportFile()">Export To Excel</button></td> -->
									</tr>
							<tr>
								<td colspan='2'><div id="serverdetails"></td>
								</tr>
							</table>
						</form>
					
<!-- 					<div align="Center"> -->
<!--           <button type="button" align="center" class="btn blue" onclick="tableToExcel('ETMAvailListable', 'W3C Example Table')" >Export to Excel</button> -->
 				</div></div></div> 
			</div>
		</div>
	</div>
</div>
<script>
$(document).ready(function() {
	window.history.pushState("","", "BrandTypeServiceLimit.action");
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
<%-- <%}else{%> --%>
 


<%-- <%@ include file="/pages/admin/error.jsp" %> --%>


<%-- <%}%> --%>


