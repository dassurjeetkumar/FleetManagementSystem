
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>


<script>



	function callEdit() {
		var val;
		//$(function() {
			var val = [];
			$(':checkbox:checked').each(function(i) {
				val = $(this).val();
			});
		
			var chckbxCount = $("input:checked").length;
			if (chckbxCount > 0 && chckbxCount > 1) {
				bootbox.alert("Select only one vehicle...!")
				return false;
			} else if (chckbxCount > 0 && chckbxCount == 1) {
				
				//if(EditableVehicle(val)){
					if(isEligibleForOpertion(val)){
					$('#editAction #vehicleId').val(val);
					$('#editAction').submit();
					}else{
						bootbox.alert("Please Select Valid Record");
					}
					/*}
				 else{
					bootbox.alert("This vehicle scrapped");
				} */
			} else {
				bootbox.alert("Please select one vehicle");
				return false;
			}
	//	});
		
	}
	function callCreate() {
		document.forms[0].action = "CreateVehicleDetails.action";
		document.forms[0].submit();
	}
	function callFleetInsert() {
		document.forms[0].action = "fleetInsert.action";
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
				bootbox.confirm("Are you Sure, You Want To Delete This Record?",function(result) {
					if (result == true) {
						$('#deleteAction #vehicleId').val(val);
						$('#deleteAction').submit();
					}
				});
				}else{
					bootbox.alert("Please Select Valid Record");
				}
			}
		//});

	}
	
	function callScrapList() {
		$('#vehicleMaster').hide();
		$('#scrapMaster').show();
		$('#vehicleHeader').hide();
		$('#scrapHeader').show();
		
		$('#scraptable').dataTable({
        	"aaSorting": [
        	                [1, 'asc']
        	            ],
            "aLengthMenu": [
                [10,25,50,100],
                [10,25,50,100], // change per page values here
            ],
            // set the initial value
            "iDisplayLength": 10,
            "bProcessing" : true,
            "bServerSide" : true,
            "sAjaxSource" : "ScrapVehicleMaster.action",
            "sPaginationType": "bootstrap",
            "oLanguage": {
                "sLengthMenu": "_MENU_ records",
                "oPaginate": {
                    "sPrevious": "Prev",
                    "sNext": "Next"
                }
            },
           "aoColumnDefs": [
                { 'bSortable': false, 'aTargets': [0] },
                { "bSearchable": false, "aTargets": [ 0 ] },
               /*  { "sClass": "url", "aTargets": [ 3] }, */
            ]
            
        });
	    jQuery('#scrapList_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
        jQuery('#scrapList_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
		//$(function() {
		
		//});

	}
	
	
	
	function callTransfer(){
		var val;
		//$(function() {
			var val = [];
			$(':checkbox:checked').each(function(i) {
				val = $(this).val();
			});
			var s=check();
			if(s){
			
				$('#transferAction #vehicleId').val(val);
				$('#transferAction').submit();
			}
			//});
	}function callFcRenew(){
		var val;
		//$(function() {
			var val = [];
			$(':checkbox:checked').each(function(i) {
				val = $(this).val();
			});
			var s=check();
			if(s){
			
				$('#fcRenewAction #vehicleId').val(val);
				$('#fcRenewAction').submit();
			}
		//	});
	}
	function callDocking(){
		var val;
		//$(function() {
			var val = [];
			$(':checkbox:checked').each(function(i) {
				val = $(this).val();
			});
			var s=check();
			if(s){
			
				$('#dockingAction #vehicleId').val(val);
				$('#dockingAction').submit();
			}
		//	});
	}
	function callOrgAllocation(){
		var val;
		//$(function() {
			var val = [];
			$(':checkbox:checked').each(function(i) {
				val = $(this).val();
			});
			var s=check();
			if(s){
			
				$('#assignOrgAction #vehicleId').val(val);
				$('#assignOrgAction').submit();
			}
			//});
	}
	function callScrap(){
		var val;
		//$(function() {
			var val = [];
			$(':checkbox:checked').each(function(i) {
				val = $(this).val();
			});
			var s=check();
			if(s){
			
				$('#scrapAction #vehicleId').val(val);
				$('#scrapAction').submit();
			}
			//});
	}function callVTUAllocation(){
		var val;
		//$(function() {
			var val = [];
			$(':checkbox:checked').each(function(i) {
				val = $(this).val();
			});
			var s=check();
			if(s){
				$('#deviceAction #vehicleId').val(val);
				$('#deviceAction').submit();
			}
		//});
	}
	function check() {
		var chckbxCount = $("input:checked").length;
		if (chckbxCount > 0 && chckbxCount > 1) {
			bootbox.alert("Select Only One Vehicle ...!")
			return false;
		} else if (chckbxCount > 0 && chckbxCount == 1) {
			
			return true;
		} else {
			bootbox.alert("Please Select Vehicle");
			return false;
		}
		
	}
	function EditableVehicle(vehicleId)
	{
		var status= $.ajax({
	   		type: "get",
	        async:false,
	        url: '<%=request.getContextPath()%>/GetStatusOfVehicle?vehicleId='+vehicleId,
	        success: function(result) {
	      	}
	    }).responseText;
		if(status=="SCRAP"){
			return false;
		}else{
			return true;
		}
	}
</script>
	<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "VehicleDetails.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
String status=accessrightdao.UserStatus(usrsessionid);
int rollid=accessrightdao.getroleid(usrsessionid);
System.out.println("user role id---------"+rollid);
%>
<form id='editAction' action="EditVehicleDetails" method="post">
	<input type="hidden" id='vehicleId' name="value" />
</form>
<form id='deleteAction' action="DeleteVehicle" method="post">
	<input type="hidden" id='vehicleId' name="deleteVehicleId" />
</form>
<form id='transferAction' action="TransferVehicleAction" method="post">
	<input type="hidden" id='vehicleId' name="vehicleId" />
</form>
<form id='fcRenewAction' action="FcRenewAction" method="post">
	<input type="hidden" id='vehicleId' name="vehicleId" />
</form>
<form id='dockingAction' action="DockingVehicle" method="post">
	<input type="hidden" id='vehicleId' name="vehicleId" />
</form>
<form id='assignOrgAction' action="OrgAllocation" method="post">
	<input type="hidden" id='vehicleId' name="vehicleId" />
</form>
<form id='scrapAction' action="ScrapVehicleAction" method="post">
	<input type="hidden" id='vehicleId' name="vehicleId" />
</form>
<form id='deviceAction' action="DeviceAllocationToVehicleAction" method="post">
	<input type="hidden" id='vehicleId' name="vehicleId" />
</form>
<!-- <form id='scrapList' action="ScrapVehicleMaster" method="post">
	<input type="hidden" id='vehicleId' name="vehicleId" />
</form> -->

<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
<input type="hidden" name="id" id="id" />
<div class="page-content-wrapper">
	<div class="page-content">
		<div class="row">
			<div class="col-md-12" id="vehicleHeader">
				<h3 class="page-title">
					 VEHICLE DETAILS
				</h3>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-12" id="scrapHeader" style="display: none">
				<h3 class="page-title">
					SCRAP DETAILS
				</h3>
			</div>
			
		</div>
		<%if (access.equalsIgnoreCase("Y")){%>
		<div class="row">
			<div class="col-md-12">
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i>Vehicle
						</div>
						<div class="actions">
						<%if(rollid==5 || rollid==1){ %>
							<a href="#" class="btn green" onclick="callCreate()" > 
								<i class="fa fa-plus"></i> Create
							</a> 
							<%}%>
<%if(edit.equalsIgnoreCase("Y")){ %>
							
							<a href="#" class="btn blue" onclick="callEdit()"> 
							<i class="fa fa-pencil"></i> Edit
							</a>
							<%}%>

<%if(delete.equalsIgnoreCase("Y")){ %>
							 <%if(rollid==5 || rollid==1){ %>
							<a href="#" class="btn red" onclick="callDelete()"> 
								<i class="fa fa-times"></i> Delete
							</a> 
							<%}}%>
								
							<%if(edit.equalsIgnoreCase("Y")){ %>
							
							<a href="#" class="btn green" onclick=""> 
								<i class="fa fa-plus"></i> Sync Vehicle
							</a>
							<%}%>
							<a class="btn red" href="#" onclick="callScrapList()">
									Scrap <i class="fa fa-angle-down"></i>
								</a>
							<div class="btn-group">
							
	<%if(status.equalsIgnoreCase("ACTIVE")){ %>
								<a class="btn blue" href="#" data-toggle="dropdown">
									Tools <i class="fa fa-angle-down"></i>
								</a>
								<%} %>
								
								
								<div id="sample_4_column_toggler" class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">
								
								 
                                   <label>
										<button type="button" class="btn blue" onclick="window.open('','_new').location.href='/its/generateReport?id=VEHICLERPT'">
											<i class="fa fa-pencil"></i>
											&nbsp;Report
										</button>
									</label>
									<%if(rollid==5 || rollid==1){ %>	
									<label>
										<button type="button" class="btn blue" onclick="callTransfer();">
											<i class="fa fa-pencil"></i>
											&nbsp;Transfer Vehicle
										</button>
									</label>
									<%}%>
									<label>
										<button type="button" class="btn blue" onclick="callFcRenew();">
											<i class="fa fa-pencil"></i>
											&nbsp;FC Renew
										</button>
									</label>	
									<label>
										<button type="button" class="btn blue" onclick="callDocking();">
											<i class="fa fa-pencil"></i>
											&nbsp;Docking
									</button>
									</label>
									<%if(rollid==5 || rollid==1){ %>	
									<label>
										<button type="button" class="btn blue" onclick="callScrap();">
											<i class="fa fa-pencil"></i>
											&nbsp;Scrap
										</button>
									</label>
											<%}%>	
									<%if(edit.equalsIgnoreCase("Y")){ %>
									<label>
										<button type="button" class="btn blue" onclick="callOrgAllocation();">
											<i class="fa fa-pencil"></i>
											&nbsp;Assign Organization
										</button>
									</label>		
									<label>
										<button type="button" class="btn blue" onclick="callVTUAllocation();">
											<i class="fa fa-pencil"></i>
											&nbsp;Assign Device
									</button>
									
								</label>
								<%} %>
								
							</div>
							<label><button type="button" class="btn green" onclick="goBack();">
						<i class="fa fa-arrow-left"></i>Back</label>
						</div>
						
					</div>
<s:token/>
				</div>
					<div class="portlet-body" id="vehicleMaster">
						<b>
							<font color="green"> <s:actionmessage/></font>
							<span id="errorMsg" style="color:red;"><s:actionerror/></span>
							
						</b>
						<table class="table table-striped table-bordered table-hover"
							id="vehicletable">
							<thead>
								<tr>
									<th></th>
									<th>Vehicle Id</th>
									<th>Registration Number</th>
									<th>Organisation Type</th>
									<th>Organisation Unit Name</th>
									<th>FC ExpiryDate</th>
									<th>Progressive Running KM</th>
									<th>Progressive Schedule KM</th>
									<th>Vehicle Type</th>
									<th>Brand Type</th>
									<th>A/C Availability</th>
									<th>Make</th>
									<th>Wheel Base(Inch)</th>
									<th>Procured Date</th>
									<th>Body Type</th>
									<th>Operational Date</th>
									<th>Docking Planning Date</th>
									<th>Service Type</th>
									<th>Registration Date</th>
									<th>Chassis Number</th>
									<th>Engine Number</th>
									<th>Norm</th>
									<th>Floor Hight</th>
									<th>Hp(Horse Power)</th>
									<th>Seat Capcity</th>
									<th>Docking km</th>
									<th>Unladen Weight</th>
									<th>No. of Wheels</th>
									<th>Vehicle Usage Category</th>
									<th>Under Warranty</th>
									<th>Status</th>
									<th>Approved for Scrap</th>
									<th>Scrap Date</th>
									<th>Created Date  &nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;&nbsp;  </th>
									<th>Created By</th>
									<th>Updated Date         &nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;&nbsp;</th>
									<th>Updated By</th>
<%-- 									<%if(viewdeletedrecord.equalsIgnoreCase("Y")) {%>	 --%>
<!-- 													<th>Record Status</th> -->
<%-- 													<%}%> --%>
								</tr>
							</thead>
							<!-- <tfoot>
								<tr>
									<td></td>
									<td>Vehicle Id</td>
									<td>Registration Number</td>
									<td>Organisation Type</td>
									<td>Organisation Unit Name</td>
									<td>FC ExpiryDate</td>
									<td>Progressive Running KM</td>
									<td>Progressive Schedule KM</td>
									<td>Vehicle Type</td>
									<td>Brand Type</td>
									<td>A/C Availability</td>
									<td>Make</td>
									<td>Model</td>
									<td>Procured Date</td>
									<td>Body Type</td>
									<td>Operational Date</td>
									<td>Docking Planning Date</td>
									<td>Service Type</td>
									<td>Registration Date</td>
									<td>Chassis Number</td>
									<td>No. of Wheels</td>
									<td>Vehicle Usage Category</td>
									<td>Under Warranty</td>
									<td>Status</td>
									<td>Approved for Scrap</td>
									<td>Scrap Date</td>
									<td>Created Date</td>
									<td>Created By</td>
									<td>Updated Date</td>
									<td>Updated By</td>
								</tr>
							</tfoot> -->
						</table>
					</div>
					
					
										<div class="portlet-body" style="display: none" id="scrapMaster">
						<b>
							<font color="green"> <s:actionmessage/></font>
							<span id="errorMsg" style="color:red;"><s:actionerror/></span>
							
						</b>
						<table class="table table-striped table-bordered table-hover"
							id="scraptable">
							<thead>
								<tr>
									<th></th>
									<th>Vehicle Id</th>
									<th>Registration Number</th>
									<th>Organisation Type</th>
									<th>Organisation Unit Name</th>
									<th>FC ExpiryDate</th>
									<th>Progressive Running KM</th>
									<th>Progressive Schedule KM</th>
									<th>Vehicle Type</th>
									<th>Brand Type</th>
									<th>A/C Availability</th>
									<th>Make</th>
									<th>Model</th>
									<th>Procured Date</th>
									<th>Body Type</th>
									<th>Operational Date</th>
									<th>Docking Planning Date</th>
									<th>Service Type</th>
									<th>Registration Date</th>
									<th>Chassis Number</th>
									<th>No. of Wheels</th>
									<th>Vehicle Usage Category</th>
									<th>Under Warranty</th>
									<th>Status</th>
									<th>Approved for Scrap</th>
									<th>Scrap Date</th>
										<th>Scrap Order</th>
									<th>Created Date  &nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;&nbsp;  </th>
									<th>Created By</th>
									<th>Updated Date         &nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;&nbsp;</th>
									<th>Updated By</th>
<%-- 									<%if(viewdeletedrecord.equalsIgnoreCase("Y")) {%>	 --%>
<!-- 													<th>Record Status</th> -->
<%-- 													<%}%> --%>
								</tr>
							</thead>
			
						</table>
					</div>
					
				</div>
			</div>
			</div>
		</div>
	</div>
	<script>
$(document).ready(function() {
	window.history.pushState("","", "VehicleDetails.action");
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

function goBack(){
window.location.href = 'VehicleDetails.action';
		}

</script>
	<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>
	

