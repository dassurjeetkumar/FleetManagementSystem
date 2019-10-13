<style type="text/css">
.help-block,ul,li {
	list-style: none;
}
</style>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="page-content-wrapper">
	<div class="page-content">
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
				<h3 class="page-title">
					FARE CHART
				</h3>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-12">
				<div class="portlet box grey-cascade">

					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-bars"></i> Update Hourly Fare Chart
						</div>

						<div class="actions">
							
						</div>
					</div>
					
					<div class="portlet-body form">

						<form action="#" class="form-horizontal form-row-seperated"
							method="post">
							<FONT color="green" style="font-weight:bold"><s:actionmessage /></FONT>
							<FONT color="red" style="font-weight:bold"><s:actionerror /></FONT>
				

							<div class="form-group">
								<label class="col-md-3 control-label">Route Number:<font color="red">*</font></label>
                                <div class="col-md-4">
								<s:select list="routeNumberMap" id="route_id" name="route_id" 
										cssClass="select2_category form-control" headerKey="0" headerValue="Select">
								</s:select> 
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-3 control-label">Service Type:<font color="red">*</font></label>
								<div class="col-md-4">
								<s:select list="serviceTypeMap" id="service_type_id" name="service_type_id" 
									cssClass="select2_category form-control" headerKey="0" headerValue="Select">
								</s:select>	
								</div>
							</div>


							<div class="form-actions fluid">
								<div class="col-md-offset-3 col-md-3">
									<button type="button" class="btn blue" onclick="show_data();">Submit</button>
									<button type="button" class="btn default"
										onclick="callCancell()">Cancel</button>

								</div>
							</div>


							<div class="portlet-body" id="viewRole12"
								style="visibility: hidden">

								<table class="table table-striped table-bordered table-hover"
									id="showHourlyChartTable">
									<thead>
										<tr>
											<th style="width1: 8px;"></th>
											<th>Fare Chart Id</th>											
											<th>Fare Chart Name</th>
											<th>Effective From</th>
											<th>Effective Till</th>
											<th>00-01 Hr</th>
											<th>01-02 Hr</th>
											<th>02-03 Hr</th>
											<th>03-04 Hr</th>
											<th>04-05 Hr</th>
											<th>05-06 Hr</th>
											<th>06-07 Hr</th>
											<th>07-08 Hr</th>
											<th>08-09 Hr</th>
											<th>09-10 Hr</th>
											<th>10-11 Hr</th>
											<th>11-12 Hr</th>
											<th>12-13 Hr</th>
											<th>13-14 Hr</th>
											<th>14-15 Hr</th>
											<th>15-16 Hr</th>
											<th>16-17 Hr</th>
											<th>17-18 Hr</th>
											<th>18-19 Hr</th>
											<th>19-20 Hr</th>
											<th>20-21 Hr</th>
											<th>21-22 Hr</th>
											<th>22-23 Hr</th>
											<th>23-00 Hr</th>
										</tr>
									</thead>

								</table>

							</div>

						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>


	function show_data() {

		var serviceType = document.getElementById("service_type_id").value;
		var routeNo = document.getElementById("route_id").value;

		if (routeNo == '0') { //bootbox.alert("Please Select One Checkbox To Edit");
			bootbox.alert('Select Route Number');
		} else if (serviceType == '0') {
			bootbox.alert('Select Service Type');
		} else {
			$('#showHourlyChartTable').dataTable(
			{
						"aaSorting": [[1, 'asc']],
						"aLengthMenu" : [ [ 10, 25, 50, 100 ],[ 10, 25, 50, 100 ] ],
						"iDisplayLength" : 10,
						"bProcessing" : true,
						"bServerSide" : true,
						"sAjaxSource" : "getHourlyChart.action?routeId="+ routeNo + "&serviceType=" + serviceType,
						"sPaginationType" : "bootstrap",
						"bDestroy" : true,

					
					 "aoColumnDefs": [
					                    { 'bSortable': false, 'aTargets': [0] },
					                    { "bSearchable": false, "aTargets": [ 0 ] }
					                ]
		});
            jQuery('#showDutyRotaTable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#showDutyRotaTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
		}
	}
</script>

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
		document.forms[0].action = "editDutyRota.action?dutyRotaId=" + val;
		document.forms[0].submit();
	}

	
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
		bootbox.confirm("Are you sure you want to Delete?",
				function(result) {

					if (result == true) {
						document.forms[0].action = "DeleteDutyRota.action?rotaId="+ val;
						document.forms[0].submit(); 
					}
				});
		
	}
}
	function check() {

		var chckbxCount = $("input:checked").length;
		if (chckbxCount > 0 && chckbxCount > 1) {
			bootbox.alert("Select Only One Duty Rota.")
			return false;
		} else if (chckbxCount > 0 && chckbxCount == 1) {
			return true;
		} else {
			bootbox.alert("Please Select One Duty Rota.");
			return false;
		}

	} 
</script>
