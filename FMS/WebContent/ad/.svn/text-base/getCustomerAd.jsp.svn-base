
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
				bootbox.alert("Select only one Customer to Edit...!")
				return false;
			} else if (chckbxCount > 0 && chckbxCount == 1) {
				
				//if(EditableVehicle(val)){
					if(isEligibleForOpertion(val)){
					$('#editAction #C_Id').val(val);
					$('#editAction').submit();
					}else{
						bootbox.alert("Please Select Valid Record");
					}
					/*}
				 else{
					bootbox.alert("This vehicle scrapped");
				} */
			} else {
				bootbox.alert("Please select one customer");
				return false;
			}
	//	});
		
	}
	function callCreate() {
		document.forms[0].action = "Createcustomer.action";
		document.forms[0].submit();
	}

	
	
	function callDelete() {
		var val;
		//$(function() {
			var val = [];
			$(':checkbox:checked').each(function(i) {
				val = $(this).val();
			});
		
			var chckbxCount = $("input:checked").length;
			if (chckbxCount > 0 && chckbxCount > 1) {
				bootbox.alert("Select only one Customer to Delete...!")
				return false;
			} else if (chckbxCount > 0 && chckbxCount == 1) {
				
				//if(EditableVehicle(val)){
					if(isEligibleForOpertion(val)){
						bootbox.confirm("Are you Sure, You Want To Delete This Record?",function(result) {
							if (result == true) {
					$('#deleteAction #C_Id').val(val);
					$('#deleteAction').submit();
					}

					});
					}
						else{
						bootbox.alert("Please Select Valid Record");
					}
					/*}
				 else{
					bootbox.alert("This vehicle scrapped");
				} */
			} else {
				bootbox.alert("Please select one customer");
				return false;
			}
	//	});

	}
	

</script>
<script>
function show_data() {
	
			//alert(det1);
			
/* 
				var divId = document.getElementById("viewRole1265");
				divId.style.visibility = 'visible';
				
			
			$('#allcustomerdetailsTable').dataTable({
			 	"aaSorting": [
          	                [0, 'asc']
          	            ],
              "aLengthMenu": [
                  [10,25,50,100],
                  [10,25,50,100], // change per page values here
              ],
              // set the initial value
              "iDisplayLength": 10,
						"bProcessing" : true,
						"bServerSide" : true,
						 "sAjaxSource" : "getallcustomerdetais.action?startdate=",
						"sPaginationType" : "bootstrap",
						"bDestroy" : true,
						 "bFilter": false,
						 "bPaginate" : false,
						
						"oLanguage" : {
							"sLengthMenu" : "_MENU_ records",
							"oPaginate" : {
								"sPrevious" : "Prev",
								"sNext" : "Next"
							}
						},
						"aoColumnDefs" : [ {
							'bSortable' : false,
							'aTargets' : [ 0 ]
						}, {
							"bSearchable" : false,
							"aTargets" : [ 0 ]
						} ,  
						 ]
					}); */
					
					$('#viewRole1265').attr("style", "display:''");
//				 	$('#mymodal1').attr("style", "display:''");
					
				    var table = $('#allcustomerdetailsTable');
				    //alert(depotid);
				     oTable = table.dataTable({
				   	 "aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
						// per
						// page
						// values
						// here
						],
						// set the initial value
						"iDisplayLength" : 5,
						 "sAjaxSource" : "getallcustomerdetais.action?",
						"sPaginationType" : "bootstrap",
						"bDestroy" : true,
						"bSort" : true,
						"bFilter" : true,
						"bSelect" : false,
						"bPaginate" : false,
						"bInfo": false,
						"oLanguage" : {
							"sLengthMenu" : "_MENU_ records",
							"oPaginate" : {
								"sPrevious" : "Prev",
								"sNext" : "Next"
							}
						},
						"aoColumnDefs" : [ {
							'bSortable' : false,
							'aTargets' : [ 0 ]
						} ]
					});

	jQuery('#customerdetailsTable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	jQuery('#customerdetailsTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline");
			
		
	}
	</script>
	
<%
/* AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid,"Customerdetails.action"); */
String access="Y";
String create="Y";
String edit="Y";
String delete="Y";
out.println("ok");
%>
<form id='CreateAction' action="CreateCustomer" method="post">
	<input type="hidden" id='vehicleId' name="value"/>
	</form>
<form id='editAction' action="Editcustomer" method="post">
	<input type="hidden" id='C_Id' name="value"/>
</form>
<form id='deleteAction' action="DeleteCustomer" method="post">
	<input type="hidden" id='C_Id' name="DeleteId"/>
</form>

<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />

<div class="page-content-wrapper">
	<div class="page-content">
		<div class="row">
			<div class="col-md-12" id="vehicleHeader">
				<h3 class="page-title">
					 Customer Details
				</h3>
			</div>
		</div>
	

		<%if (access.equalsIgnoreCase("Y")){%>
		<div class="row">
			<div class="col-md-12">
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i>Customer
						</div>
						<div class="actions">
						<%if(create.equalsIgnoreCase("Y")){ %>
						
							<a href="#" class="btn green" onclick="callCreate()"> 
								<i class="fa fa-plus"></i> Create
							</a> 
							<%}%>
<%if(edit.equalsIgnoreCase("Y")){ %>
							
							<a href="#" class="btn blue" onclick="callEdit()"> 
								<i class="fa fa-pencil"></i> Edit
							</a>
							<%}%>

<%if(delete.equalsIgnoreCase("Y")){ %>
							 
							<a href="#" class="btn red" onclick="callDelete()"> 
								<i class="fa fa-times"></i> Delete
							</a> 
							<%}%>
						</div>
						</div>			
				</div>
				</div>
				</div>
					<div class="portlet-body" id="viewRole1265"
								style="visibility: hidden">
								<b><font color="green"> <s:actionmessage/></font></b>
                        <div id="allcustomerdetailsT">
								<table class="table table-striped table-bordered table-hover"
									id="allcustomerdetailsTable">
									<thead>
										<tr>
											<th></th>
											<th>Id</th>	
											<th>Name</th>
											<th>Code</th>										
											<th>Address</th>
											<th>City</th>
											<th>State </th>
											<th>Country</th>
											<th>Email</th>
											<th>Website</th>
											<th>Phone</th>
											<th>Cell</th>
											<th>Contact Person Namee</th>
											<th>Contact Person Email</th>
											<th>Pontact Person Phone</th>
											<th>Status</th>
											
											<!-- <th>END Point</th>
											<th>Schedule type</th>
											<th>Depot</th> -->
											
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
show_data();
window.history.pushState("","", "Customerdetails.action");
	 var w=$('#errorMsg span').html();
	   //alert(w);
	    //w=w.replace(/@/g,"<br>");

	   $('#errorMsg').html(''+w+''); 
// 	   -->
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
window.location.href = 'Customerdetails';
		}

</script>
	<%}else{%>
 


<%-- <%@ include file="/pages/admin/error.jsp" %> --%>


<%}%>
	

