<%@ taglib prefix="s" uri="/struts-tags"%>


<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid,"SimViewAjaxAction.action");
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

<head>

<link rel="stylesheet" type="text/css" 	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css" />
<link rel="stylesheet" type="text/css" 	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css" />
<link rel="stylesheet" type="text/css" 	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-colorpicker/css/colorpicker.css" />
<link rel="stylesheet" type="text/css" 	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" />
<link rel="stylesheet" type="text/css" 	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-datetimepicker/css/datetimepicker.css" />
<link rel="stylesheet" 	href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />



<style type="text/css">
.col-md-9,.col-md-8 {
	width: 30%;
}


</style>
<script>
	function callEdit() {
		var val;
		$(function() {
			var val = [];
			$(':checkbox:checked').each(function(i) {
				val = $(this).val();
			});
		
			var chckbxCount = $("input:checked").length;
			if (chckbxCount > 0 && chckbxCount > 1) {
				bootbox.alert("Select only one SimCard...!")
				return false;
			} else if (chckbxCount > 0 && chckbxCount == 1) {
				if(isEligibleForOpertion(val)){
				document.getElementById("value").value=val;
				document.getElementById("form1").action="EditSimDetails.action";
				document.getElementById("form1").submit();
				/* document.forms[0].action = "EditSimDetails.action?value="+ val;
				document.forms[0].submit(); */
				}else{
					bootbox.alert("Please Select Valid Record");
				}
			} else {
				bootbox.alert("Please select one SimCard");
				return false;
			}
		});
		
	}
	function callCreate() {
		document.forms[0].action = "CreateSimAction.action";
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
				bootbox	.confirm("Are you sure you want to delete this Record?",function(result) {
					if (result == true) {
						document.getElementById("value").value=val;
						document.getElementById("form1").action="DeleteSim.action";
						document.getElementById("form1").submit();
						/* document.forms[0].action = "DeleteSim.action?value="+ val;
						document.forms[0].submit(); */
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
			bootbox.alert("Select Only One SimCard ...!")
			return false;
		} else if (chckbxCount > 0 && chckbxCount == 1) {
			
			return true;
		} else {
			bootbox.alert("Please Select One SimCard");
			return false;
		}
		
	}
</script>
</head>
<div class="page-content-wrapper">
	<div class="page-content">
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
					SIMCARD<%-- <small> Battery List</small> --%>
				</h3>
				<!-- <ul class="page-breadcrumb breadcrumb">
					<li><i class="fa fa-home"></i> 
						Home <i	class="fa fa-angle-right"></i></li>
					<li>Device Management <i class="fa fa-angle-right"></i></li>
					<li>Battery List 
					</li>
				</ul> -->
			</div>
		</div>
		<%-- <div class="row">
			<div class="col-md-12" align="center" style="font-size: 1.2em">
				
				<span class="help-block" style="color: green; list-style: none"><s:actionmessage /></span>
			</div>
		</div> --%>
		<%if (access.equalsIgnoreCase("Y")){ %>
		<div class="row">
			<div class="col-md-12">
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-bars"></i>View SimCard 
						</div>
						<div class="actions">
								<%if(create.equalsIgnoreCase("Y")){ %>
							<a href="#" class="btn green" onclick="callCreate()"> 
								<i class="fa fa-plus"></i> Create
							</a> <%}%>
							<%if(edit.equalsIgnoreCase("Y")){ %><a href="#" class="btn blue" onclick="callEdit()"> 
								<i class="fa fa-pencil"></i> Edit
							</a> <%}%>
							<%if(edit.equalsIgnoreCase("Y")){ %><a href="#" class="btn red" onclick="callDelete()"> 
								<i class="fa fa-times"></i> Delete
							</a> <%}%>
							<div class="btn-group">
								
									<button class="btn green dropdown-toggle" data-toggle="dropdown"><i class="fa fa-gears"></i> Tools <i class="fa fa-angle-down"></i>
									</button>
									<ul class="dropdown-menu pull-right ">
										<li>
										<%if(status.equalsIgnoreCase("ACTIVE")){ %>
											<a class="btn blue" href="javascript: void(0)" onclick="window.open('','_new').location.href='/its/generateReport?id=SIMCARDRPT'">
											<!-- href="/its/ReportAction!generateReport?id=1"> -->
											Report </a>
											<%} %>
										</li>	
										
																			
									</ul> 
							</div>
						</div>
					</div>
					<div class="portlet-body" >
						<b>
							<font color="green"> <s:actionmessage/></font>
							<span id="errorMsg" style="color:red;"><s:actionerror/></span>
							
						</b>
						<table id="simcardList" class="table table-striped table-bordered table-hover" >
							<thead>
								<tr><th></th>
									<th>Sim Card Id</th>
									<th>Vendor Name</th>
 									<th>Sim Serial Number</th>
									<th>Phone Number</th>
									<th>Procured Date</th>
									<th>Status</th>
									<th>Remarks</th>
									<th>Service Plan</th>
									<%if(viewdeletedrecord.equalsIgnoreCase("Y")) {%>	
													<th>Record Status</th>
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
<form name="form1" id="form1" method="post">
	<input type="hidden" name="value" id="value"/>
</form>
</body>
<head>
<script>
$(document).ready(function() {
	   window.history.pushState("","", "SimViewAjaxAction.action");
	   var w=$('#errorMsg span').html();
	   //alert(w);
	    w=w.replace(/@/g,"<br>");

	   $('#errorMsg').html(''+w+'');
	 });
	function callCancell() {

		window.location.href = 'SimViewAjaxAction.action';
	}

	function isEligibleForOpertion(id){
		 var isEligible = $('#isRocordEligible'+id).val();
		 if(isEligible == undefined || isEligible=='Y'){
			 return true;
		 }else{
			 return false;
		 }
	}
</script>

<%}else
	
{%>
<%@ include file="/pages/admin/error.jsp" %>

<%}%>	