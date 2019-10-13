<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
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
.url{
    max-width: 150px;
    word-wrap: break-word;
}
</style>

<style>
.url{
    max-width: 150px;
    word-wrap: break-word;
}

</style>
<script>
	function callCreate() {
		document.forms[0].action = "CreateVehicleCategoryAction.action";
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
				bootbox.confirm("Are you sure want to delete Vehicle Category Type?",function(result) {
					if (result == true) {
						document.getElementById("VehCatid").value = val;
						document.getElementById("form1").submit();
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
			bootbox.alert("Select Only One Vehicle Category...!")
			return false;
		} else if (chckbxCount > 0 && chckbxCount == 1) {
			
			return true;
		} else {
			bootbox.alert("Please Select Vehicle Category");
			return false;
		}
		
	}
</script>
</head>

	<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "VehicleCategoryList.action");
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


<div class="page-content-wrapper">
	<div class="page-content">
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
					Vehicle Category
				</h3>
			</div>
		</div>
<%-- 		<div class="row">
			<div class="col-md-12" align="center" style="font-size: 1.2em">
				<span class="help-block" style="color: red; f list-style: none"><s:actionerror /></span>
				<span class="help-block" style="color: green; list-style: none"><s:actionmessage /></span>
			</div>
		</div> --%>
		<%if (access.equalsIgnoreCase("Y")){%>
		
		<div class="row">
			<div class="col-md-12">
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-bars"></i>View Vehicle Category 
						</div>
						<div class="actions">
                     <%if(create.equalsIgnoreCase("Y")){ %>     
							<a href="javascript: void(0)" class="btn green" onclick="callCreate()"> 
								<i class="fa fa-plus"></i> Create
							</a> 
							<%}%>

        <%if(delete.equalsIgnoreCase("Y")){ %>

	
							
							<a href="javascript: void(0)" class="btn red" onclick="callDelete()"> 
								<i class="fa fa-times"></i> Delete
							</a> <%}%>
							 <div class="btn-group">
                      <button class="btn green dropdown-toggle"    data-toggle="dropdown"><i class="fa fa-gears"></i> Tools <i class="fa
                                    fa-angle-down"></i> </button>
                                    <ul class="dropdown-menu pull-right ">
                                        <li>
                                        <%if(status.equalsIgnoreCase("ACTIVE")){ %>
                                    <a class="btn blue" href="javascript: void(0)"onclick="window.open('','_new').location.href='/its/generateReport?id=VEHICLECATEGORYRPT'">
                                        
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
						<table id="vehilceCategoryListView" class="table table-striped table-bordered table-hover">
							<thead>
								<tr><th></th>
									<th>Vehicle Category Id</th>
									<th>Vehicle Category Name</th>
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
</body>
<head>
<script>


	function callCancell() {

		window.location.href = 'VehicleCategoryList.action';
	}

	$(document).ready(function() {
		window.history.pushState("","", "VehicleCategoryList.action");
		

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
<form name="form1" id="form1" action="DeleteVehicleCategory.action"
	method="POST">
	<input type="VehCatid" name="VehCatid" id="VehCatid" value="" />
</form>
