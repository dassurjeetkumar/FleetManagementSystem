
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
<script src="https://code.jquery.com/jquery-1.11.3.min.js" type="text/javascript"></script>

<%-- <script> --%>
// <!-- // var tableToExcel = (function() { -->
// <!-- //   var uri = 'data:application/vnd.ms-excel;base64,' -->
// <!-- //     , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head>[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]</head><body><table>{table}</table></body></html>' -->
// <!-- //     , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) } -->
// <!-- //     , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) } -->
// <!-- //   return function(table, name) { -->
// <!-- //     if (!table.nodeType) table = document.getElementById(table) -->
// <!-- //     var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML} -->
// <!-- //     window.location.href = uri + base64(format(template, ctx)) -->
// <!-- //   } -->
// <!-- // })() -->
<%-- </script> --%>



<script>                 	
   function tabletoExcel(btnExport){     
  	var htmltable= document.getElementById('btnExport');   
    $( "#header-fixed" ).remove();
    var inputHTML = "<table border='1'>";
    inputHTML += "<tr>";
    inputHTML += "<th  align='left'colspan='13'>Bangalore Metropolitan Transport Corporation</th>";
    inputHTML += "</tr>";
    inputHTML += "<th  align='left'colspan='13'>Device Disconnected List</th>";
    inputHTML += "</tr>";
    inputHTML += "<tr>";
//     inputHTML += "<th colspan='3' align='left'>Date Range:</th>";
//     inputHTML += "<th colspan='4' align='left'>" + $("#startdate").val() + " to " + $("#endate").val() + "</th>";
    inputHTML += "</tr>";
    inputHTML += "</table>";
    var htmltable = document.getElementById("deviceDisconnectList");
    var html = inputHTML + htmltable.outerHTML;
    var downloadLink = document.createElement("a");
    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
    downloadLink.download = "Device Disconnected List.xls";
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
}</script>


<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
	<%
		AccessRightsDao accessrightdao = new AccessRightsDao();
		AccessRights accessrights = new AccessRights();
		int usrsessionid = (Integer) request.getSession().getAttribute(
				"userid");
		accessrights = accessrightdao.accessRightsdetails(usrsessionid,
				"deviceDisconnectList.action");
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





<div class="page-content-wrapper">
	<div class="page-content">
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
				<h3 class="page-title">
					Device Disconnected
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

							<i class="fa fa-globe"></i>View Device Disconected List
						</div>
						<div align="right">

 <span><button type="button" class="btn green" id="btnExport" onclick="tabletoExcel();"> EXPORT  </button></span>&nbsp;<span>
                      
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
								id="deviceDisconnectList">
								<thead>
									<tr>
										<th>Sr No</th>
										<th>Device No</th>
										<th>Vehicle No</th>
										<th>Depot No</th>
										<th>Lat</th>
										<th>Long</th>
										<th>IST Date</th>

										 
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
// $(document).ready(function() {
// 	window.history.pushState("","", "OnlineETMAvailabilityList.action");
// 	var w=$('#errorMsg span').html();
// 	   //alert(w);
// 	    w=w.replace(/@/g,"<br>");

// 	   $('#errorMsg').html(''+w+'');
// });
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


