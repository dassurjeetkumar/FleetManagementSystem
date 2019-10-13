
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<script src="assets/vts/js/vts.js" type="text/javascript"></script>
<script>
function getDepot(orgId){
	 $('#select2-chosen-2').html("Select");
	 $('#select2-chosen-3').html("Select");
	//alert('Here');
	 /* var selectedValue = $('#form-control').val(); */
	 var val=document.getElementById('divisionlist').value;
		 if(val!=0) {
			 $("#msg").html("Please wait...");
        $.ajax({
            type: "get",
            url: '<%=request.getContextPath()%>/getDepot?val='+val,
            success: function(result) { 
            	$("#msg").html("");
                document.getElementById('depotlist').innerHTML=result;
            }
        });
	 }
	
}
function getRefresh() {
	if ($("#depotlist").is(':checked')) {
		// plotVehicle();
	} else {
		clearInterval(intervalID);
	}
}
</script>
</head>
<script src="assets/vts/js/vehiclealert.js" type="text/javascript"></script>
<form>

<div class="page-content-wrapper">
	<div class="page-content">
		<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
		<div class="modal fade" id="portlet-config" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true"></button>
						<h4 class="modal-title">Modal title</h4>
					</div>
					<div class="modal-body">Widget settings form goes here</div>
					<div class="modal-footer">
						<button type="button" class="btn blue">Save changes</button>
						<button type="button" class="btn default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>


		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
				<h3 class="page-title">
				 Daily VTU Packet Count<small></small>
				</h3>

			</div>
		</div>

		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN EXAMPLE TABLE PORTLET-->
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i>View Daily VTU Packet Count
						</div>
					</div>
					<div class="form-body">
				<div class="form-group">
					
					<div class="col-md-3">
								<div class="input-group input-medium date date-picker"
									data-date-format="dd-mm-yyyy" data-date-viewmode="years" data-date-end-date="0d">
									<input class="form-control" type="text" size="10" name=""
										id="selectedDate" value='<s:property value=""/>' readonly><span
										class="input-group-btn">

										<button class="btn default date-set form-control"
											type="button">
											<i class="fa fa-calendar"></i>
										</button>
									</span>
								</div>
								</div>
					<div class="col-md-3">
						<s:select list="divisionlist" id="divisionlist"
							name="orgchart.org_chart_id"
							cssClass="select2_category form-control" headerKey="0"
							headerValue="Division" onchange="getDepot(this.value)" onclick="getRefresh()"></s:select>
									
					</div>
					<div class="col-md-3">
						<select id="depotlist"   class="select2_category form-control"	>
							 <option value="0">Depot Name</option>
							 </select>
					
					</div>
					<label class="col-md-3">
								<button type="button" class="btn grey" onClick="getVTSReport();">Submit</button>
							</label>
					
				</div>
			</div>
					
					<div class="portlet-body" id="reportdevprint">
						<!-- style='height:310px; overflow-y:scroll; display:none;'> -->
						<div id="reportdevicetableid" class="portlet-body"
							style="display: none">
							<input type="hidden" name="requestType" id="requestType"
								value="text" />
							<table class="table table-striped table-bordered table-hover"
								id="reportdevice">
								<thead>
									<tr>
										<th>Sr No</th>
										<th>Depot No</th>
										<th>Device Id</th>
								<th> Vehicle No</th>
										<th>Schedule Name</th>
										<th>Sch Type</th>
										<th>Device Status</th>
										<th>Total Count</th>
										<th>1Hr</th>
										<th>2Hr</th>
										<th>3Hr</th>
										<th>4Hr</th>
										<th>5Hr</th>
										<th>6Hr</th>
										<th>7Hr</th>
										<th>8Hr</th>
										<th>9Hr</th>
										<th>10Hr</th>
										<th>11Hr</th>
										<th>12Hr</th>
										<th>13Hr</th>
										<th>14Hr</th>
										<th>15Hr</th>
										<th>16Hr</th>
										<th>17Hr</th>
										<th>18Hr</th>
										<th>19Hr</th>
										<th>20Hr</th>
										<th>21Hr</th>
										<th>22Hr</th>
										<th>23Hr</th>
										<th>24Hr</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div style="display: none;" id="mymodal3" class="modal fade"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel3"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 800px; height: 500px;"
				align="justify">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 id="vehicleno123" class="modal-title"></h4>
				</div>
				<div>
					<p>
					<div class="portlet box white ">
						<div>
							<input type="hidden" name="requestType" id="requestType" />
							<div>
								<div class="portlet solid white">
									<div class="form-group">
										<label class="col-md-3 control-label"></label>
									</div>
									<div class="portlet-body" id="modalclass3"
										style="display: none">
										<div style="text-align:center">
									<table class="table table-striped table-bordered table-hover"
								id="DeviceDetailTable">
								<thead>
									<tr>
										
										<th>Device Id</th>
										<th>Packet Header </th>
										<th>Packet Code</th>
										<th>Misc bytes</th>
										<th>Ign Status</th>
										<th>Signal Strength</th>
										<th>No. of Satellite</th>
										<th>Ext. Volt</th>
										<th>Int. Volt</th>
										<th>Speed km</th>
										<th>Lat</th>
										<th>Lng</th>
										<th>IST Date</th>
										<th>Created Date</th>
									
									</tr>
								</thead>
							
							</table>
							</div>
							</div>
							</div>
						
							</div>
						</div>
					</div>
					</p>
				</div>

			</div>
		</div>
	</div>	

</form>

</html>