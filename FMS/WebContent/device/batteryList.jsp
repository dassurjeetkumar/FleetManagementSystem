<%@ taglib prefix="s" uri="/struts-tags"%>

<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid,"BatteryView.action");
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

/* .help-block,ul,li {
	list-style: none;
} */
</style>
<script>
	function callEdit() {
		var cnt = $(":checkbox:checked").length;
		var val;
		if (cnt == 0) {
			bootbox.alert("Please Select Checkbox To Edit");
		} else if (cnt > 1) {
			bootbox.alert("Please Select One Checkbox To Edit");
		} else {
			$("input[type='checkbox']:checked").each(function() {

				val = this.value;
			});
			if(isEligibleForOpertion(val)){
			document.getElementById("value").value=val;
			document.getElementById("form1").action="EditBatteryDetails.action";
			document.getElementById("form1").submit();
			}else{
				bootbox.alert("Please Select Valid Record");
			}

		}
		/* var val;
		$(function() {
			var val = [];
			$(':checkbox:checked').each(function(i) {
				val = $(this).val();
			});
		
			var chckbxCount = $("input:checked").length;
			if (chckbxCount > 0 && chckbxCount > 1) {
				bootbox.alert("Please Select Checkbox To Edit");
				return false;
			} else if (chckbxCount > 0 && chckbxCount == 1) {
				document.getElementById("value").value=val;
				document.getElementById("form1").action="EditBatteryDetails.action";
				document.getElementById("form1").submit();
			} else {
				bootbox.alert("Please Select One Checkbox To Edit");
				return false;
			}
		});
		 */
	}
	function callCreate() {
		document.forms[0].action = "CreateBatteryAction.action";
		document.forms[0].submit();
	}

	function callDelete() {
		var cnt = $(":checkbox:checked").length;
		var val;
		if (cnt == 0) {
			bootbox.alert("Please Select Checkbox To Delete");
		} else if (cnt > 1) {
			bootbox.alert("Please Select One Checkbox To Delete");
		} else {
			$("input[type='checkbox']:checked").each(function() {

				val = this.value;
			});
			
			if(isEligibleForOpertion(val)){
			bootbox
					.confirm(
							"Are you sure, you want to delete this record?",
							function(result) {

								if (result == true) {
									//alert('deleted');
									document.getElementById("value").value=val;
									document.getElementById("form1").action="DeleteBattery.action";
									document.getElementById("form1").submit();
								}
							});
			}else{
				bootbox.alert("Please Select Valid Record");
			}

		}
		/* var val;
		$(function() {
			var val = [];
			$(':checkbox:checked').each(function(i) {
				val = $(this).val();
			});
			if (check(0)) {
				bootbox	.confirm("Are you sure you want to delete this Record?",function(result) {
					if (result == true) {
						document.getElementById("value").value=val;
						document.getElementById("form1").action="DeleteBattery.action";
						document.getElementById("form1").submit();
					}
				});

			}
		}); */

	}
function check() {

		
		var chckbxCount = $("input:checked").length;
		if (chckbxCount > 0 && chckbxCount > 1) {
			bootbox.alert("Please Select Checkbox To Delete");
			return false;
		} else if (chckbxCount > 0 && chckbxCount == 1) {
			
			return true;
		} else {
			bootbox.alert("Please Select One Checkbox To Delete");
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
					 BATTERY <%-- <small> Battery List</small> --%>
				</h3>
			</div>
		</div>
		<%if (access.equalsIgnoreCase("Y")){ %>
		<div class="row">
			<div class="col-md-12">
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-bars"></i>View Battery
						</div>
						<div class="actions">
								<%if(create.equalsIgnoreCase("Y")){ %>
							<a href="javascript:void(0)" class="btn green" onclick="callCreate()"> 
								<i class="fa fa-plus"></i> Create
							</a>  <%}%>
							<%if(edit.equalsIgnoreCase("Y")){ %>
							<a href="javascript:void(0)" class="btn blue" onclick="callEdit()"> 
								<i class="fa fa-pencil"></i> Edit
							</a> <%}%>
							<%if(edit.equalsIgnoreCase("Y")){ %><a href="javascript:void(0)" class="btn red" onclick="callDelete()"> 
								<i class="fa fa-times"></i> Delete
							</a> <%}%>
							<div class="btn-group">
								
									<button class="btn green dropdown-toggle" data-toggle="dropdown"><i class="fa fa-gears"></i> Tools <i class="fa fa-angle-down"></i>
									</button>
									<ul class="dropdown-menu pull-right ">
										<li>
										<%if(status.equalsIgnoreCase("ACTIVE")){ %>
											<a class="btn blue" href="javascript: void(0)" onclick="window.open('','_new').location.href='/its/generateReport?id=BATTERYRPT'">
											<!-- href="/its/ReportAction!generateReport?id=1"> -->
											Report </a>
											<%} %>
										</li>	
										
																			
									</ul> 
							</div>
						</div>
					</div>
					<div class="portlet-body" style="word-wrap: break-word;">
					<input type="hidden" name="requestType" id="requestType"
								value="text" />
							<b>
							<font color="green"> <s:actionmessage/></font>
							<span id="errorMsg" style="color:red;"><s:actionerror/></span>
							
						</b>
						<table id="batterListView" class="table table-striped table-bordered table-hover">
						
							<thead>
								<tr>
								<th style="width1: 8px;"></th>
									<th>Battery Id</th>
									<th>Vendor Name</th>
									<th>Serial Number</th>
									<th>Capacity</th>
									<th>Procured Date</th>
									<th>Status</th>
									<th>Remarks</th>
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
	   window.history.pushState("","", "BatteryView.action");
	   var w=$('#errorMsg span').html();
	   //alert(w);
	    w=w.replace(/@/g,"<br>");

	   $('#errorMsg').html(''+w+'');
	 });
	function callCancell() {

		window.location.href = 'BatteryView.action';
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

