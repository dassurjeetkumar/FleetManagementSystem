
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="assets/vts/js/vehiclealert.js" type="text/javascript"></script>
<script>
function getDepot(orgId){
	//alert('Here');
	 /* var selectedValue = $('#form-control').val(); */
	 var division=document.getElementById('divisionlistid').value;
	 var val=document.getElementById('dockingTypeListid').value;
	 //alert(val);
		 if(division!=0) {
        $.ajax({
            type: "get",
            url: '<%=request.getContextPath()%>/getDepotList?division=' + division,
				success : function(result) {
					document.getElementById('depotlistid').innerHTML = result;
				}
			});
		}

	}
	/* function getTable(){
		$("#tableid").hide();
	} */
	function validateTripStatusReportFields(runningkm,depotId,divisonNo)
	{
		 if(divisonNo==0)
		 {
			alert("Please Select Divison");
			return false;
		 }
		 if(depotId==0)
		 {
			alert("Please Select Depot");
			return false;
		 }
		 if(runningkm==0)
	     {
		   alert("Please Select Docking Type");
		   return false;
	     }	     

	 return true;
	}
	function getvehicleMasterData() {
		//alert("hello");	
		$('#vehicleDockingTableid').hide();	
		var division = document.getElementById("divisionlistid").value;
		var runningkm = document.getElementById("dockingTypeListid").value;	          	
		var depotId = document.getElementById("depotlistid").value;
		var validateVar=validateTripStatusReportFields(runningkm,depotId,division);
		
if(validateVar==true){
	$('#vehicleDockingTableid').show();	
		$('#vehicleDockingId').dataTable(
				{

					"aaSorting" : [ [ 0, 'asc' ] ],
					"aLengthMenu" : [ [ 10, 25, 50, 100 ], [ 10, 25, 50, 100 ], // change
					// per page
					// values
					// here
					],
					// set the initial value
					"iDisplayLength" : 10,
					"bProcessing" : true,
					"bServerSide" : true,				
					"sAjaxSource" : "getVehicleDockingReportList.action?runningkm="
							+ runningkm + "&depot_id=" + depotId,
					"sPaginationType" : "bootstrap",
					"bDestroy"    :true,
					"oLanguage" : {
						"sLengthMenu" : "_MENU_ records",
						"oPaginate" : {
							"sPrevious" : "Prev",
							"sNext" : "Next"
						}
					},
					"aoColumnDefs" : [ {
						'bSortable' : false,
						'aTargets' : [  ]
					}, {
						"bSearchable" : false,
						"aTargets" : [  ]
					}, ]

				});
		jQuery('#vehicleDockingId_wrapper .dataTables_filter input').addClass(
				"form-control input-small input-inline"); // modify
		// table
		jQuery('#vehicleDockingId_wrapper .dataTables_length select').addClass(
				"form-control input-xsmall input-inline"); // modify
		// table
}
	}        
	
</script>
<body>
<div class="page-content-wrapper">
	<div class="page-content">
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
					VEHICLE MANAGEMENT <small></small>
				</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i>View Vehicle Docking Report
						</div>
					</div>
				
				<div class="portlet-body">
				<font color="red"><s:actionmessage/></font>			
				<s:if test="hasActionErrors()">
							<font color="red"><s:actionerror/></font>
						</s:if>
				<form action=""	method="post" class="form-horizontal">				
					<div class="form-body">
					
					<div class="form-group">
						<label class="control-label col-md-3">Docking Kms:<font
							color="red">*</font></label>
						<div class="col-md-4">
							<s:select list="dockingTypeList" id="dockingTypeListid"
								name="dokType.docking_type_id"
								cssClass="select2_category form-control" headerKey="0"
								headerValue="Select"></s:select>
								
						</div>

					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Division:<font
							color="red">*</font></label>
						<div class="col-md-4">
							<s:select list="divisionlist" id="divisionlistid"
								name="orgchart.org_chart_id"
								cssClass="select2_category form-control" headerKey="0"
								headerValue="Select" onchange="getDepot(this.value)"></s:select>
						</div>

					</div>

					<div class="form-group">
					 	<label class="control-label col-md-3">Depot:<font
							color="red">*</font></label>
						<div class="col-md-4">
							<select id="depotlistid" class="select2_category form-control"
								onchange="getvehicleMasterData()">
								<option value="0">Select</option>
							</select>
						</div>
					</div>
					
					<div class="form-group">
					<!-- <label class="control-label col-md-3">Running Distance:<font 
					color="red">*</font></label>
							<div class="col-md-4">
							<input type="text" id="runningkm" class="select2_category form-control" name="runningkm" >
							
							</div> -->
					
					<div class="col-md-4">
						<button type="button" class="btn default" value="Submit" name="buttonname" onClick="getvehicleMasterData()">Submit</button>					
						
					</div>
					</div>
			</div>
</form>
</div>
				<div id="vehicleDockingTableid" class="portlet-body" style="display: none">

					<table class="table table-striped table-bordered table-hover"
						id="vehicleDockingId">
						<thead>
							<tr>
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