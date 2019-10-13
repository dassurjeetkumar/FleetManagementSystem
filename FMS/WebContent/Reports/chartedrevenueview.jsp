<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<link rel="stylesheet"	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<style type="text/css">
.help-block
{
	color:red;
}
#ui-id-1 
{ 
	max-height: 300px;overflow-y:scroll;
}
</style>

<div class="page-content-wrapper">
	<div class="page-content">
		<div class="row">
			<div class="col-md-12">
			
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN EXAMPLE TABLE PORTLET-->
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i> COMPANY/FACTORY CHARTERED SERVICE
						</div>
						<div class="actions">
							<a href="charted" class="btn green"> 
								<i class="fa fa-plus"></i> Create
							</a> </div>
					</div>
					<div class="portlet-body form">
						<b>
							<font color="green"> <s:actionmessage/></font>
							<font color="red"> <s:actionerror/></font>
						</b>
						<form action="" class="form-horizontal form-row-seperated" method="post">
							<div class="form-body">
							
												<div class="form-group">
									<label class="control-label col-md-3">Depot:</label>
									<div class="col-md-3">
									<div class="input-group input-medium"  >
								<s:select list="depotlist" id="depotid" name="depotname" cssClass="form-control input-medium select2me"  headerKey="0" headerValue="--select--"></s:select>
								
										</div>
									</div>
							</div>
						<%-- 						<div class="form-group">
									<label class="control-label col-md-3">Charted Type:</label>
									<div class="col-md-3">
									<div class="input-group input-medium"  >
								<s:select list="charttypelist" id="chartid" name="chartname" cssClass="form-control input-medium select2me"  headerKey="0" headerValue="--select--"></s:select>
								
										</div>
									</div>
							</div> --%>
							<div class="form-group">
							<label class="control-label col-md-3">Date:</label>
										<div class="col-md-3">
											<div class="input-group input-medium date date-picker" style="width: auto" data-date-format="dd-mm-yyyy" data-date-viewmode="years">
												<input id="startdate" class="form-control" type="text" readonly="" size="16" name="startdate" value="<s:property value='effectiveStartDate' />">
												<span class="input-group-btn">
													<button class="btn default date-set" type="button">
														<i class="fa fa-calendar"></i>
													</button>
												</span>
												<script>
													var curr_date = new Date().toJSON().slice(0,10);
													curr_date = curr_date.split("-");
													curr_date = curr_date[2] + "-" + curr_date[1] + "-" + curr_date[0];
													var dtval = document.getElementById('startdate').value;
													if (dtval == '') {
														$('#startdate').val(curr_date);
													}
												</script>
											</div>
										</div>
							</div>
									<%-- 				<div class="form-group">
							<label class="control-label col-md-3">To Date:</label>
										<div class="col-md-3">
											<div class="input-group input-medium date date-picker" style="width: auto" data-date-format="dd-mm-yyyy" data-date-viewmode="years">
												<input id="enddate" class="form-control" type="text" readonly="" size="16" name="enddate" value="<s:property value='effectiveStartDate' />">
												<span class="input-group-btn">
													<button class="btn default date-set" type="button">
														<i class="fa fa-calendar"></i>
													</button>
												</span>
												<script>
													var curr_date = new Date().toJSON().slice(0,10);
													curr_date = curr_date.split("-");
													curr_date = curr_date[2] + "-" + curr_date[1] + "-" + curr_date[0];
													var dtval = document.getElementById('enddate').value;
													if (dtval == '') {
														$('#enddate').val(curr_date);
													}
												</script>
											</div>
										</div>
							</div> --%>
		

								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="button" class="btn blue" onclick="getdata()">Submit</button>
										<!-- <button type="button" class="btn default" onclick="callCancell()">Cancel</button> -->
									</div>
								</div>
													<div class="portlet-body" id="createchart"
														style="display: none">
														<div style="text-align:left">
														<table
															class="table table-striped table-bordered table-hover"
															id="createcharttable" width="50">
															<thead>
	<tr>
	<th>SN</th><th><center>Effective Start<br>Date</center></th><th><center>Effective End<br>Date</center></th>
	<th><center>Sch No</center></th><th><center>Schedule <br>Type</center></th><th><center>Shift</center></th>
	<th><center>Route No</center></th><th><center>Company<br>/School</center></th>
	<th><center>Trip No</center></th><th><center>KMS Per<br>Day</center></th><th><center>Rate/KMS</center></th><th><center>Amount/Day</center></th>
	
		
	</tr>
									
																
															</thead>
														</table>
													</div>
                                                   </div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

                                                   
                                                   
<script>

function callCancell(){
	
	window.location.href='DedicatedCharteredRevenue.action';
}



</script>

<script>
function getdata(){
	var depot=$("#depotid").val();
	//var chart=$("#chartid").val();
	var startdate=$("#startdate").val();
// 	var enddate=$("#enddate").val(); 

	//$('#createchart').show();
	$('#createchart').attr("style", "display:''");
    table = $('#createcharttable');
    var oTable = table.dataTable({
    	"aLengthMenu" : [ [ 5,10, 15, 20, -1 ], [ 5,10, 15, 20, "All" ] // change
		// per
		// page
		// values
		// here
		],
		// set the initial value
		"iDisplayLength" : 5,
		"sAjaxSource" : "chartedview!getdata.action?depot="+depot+"&startdate="+startdate,
		"sPaginationType" : "bootstrap",
		"bDestroy" : true,
		"bSort" : true,
		"bFilter" : true,
		"bSelect" : true,
		"bPaginate" : true,
		"bInfo": true,
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

	jQuery('#createcharttable_wrapper .dataTables_filter input').addClass(
			"form-control input-xsmall input-inline"); // modify table
	// search input
	jQuery('#createcharttable_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");	

}
</script>