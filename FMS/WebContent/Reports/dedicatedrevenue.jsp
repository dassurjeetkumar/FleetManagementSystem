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
							<i class="fa fa-globe"></i>Create Dedicated Service
						</div>
							<div class="actions">
							<a href="dedicatedview" class="btn green"> 
								<i class="fa fa-eye"></i> View
							</a> </div>
					</div>
					<div class="portlet-body form">
						<b>
							<font color="green"> <s:actionmessage/></font>
							<font color="red"> <s:actionerror/></font>
						</b>
						<form action="dedicated!getsavedata" class="form-horizontal form-row-seperated" method="post">
							<div class="form-body">
							
												<div class="form-group">
									<label class="control-label col-md-3">Depot:</label>
									<div class="col-md-3">
									<div class="input-group input-medium"  >
								<s:select list="depotlist" id="depotid" name="depotname" cssClass="form-control input-medium select2me"  headerKey="0" headerValue="--select--"></s:select>
								
										</div>
									</div>
							</div>
												<div class="form-group">
									<label class="control-label col-md-3">Service Type:</label>
									<div class="col-md-3">
									<div class="input-group input-medium"  >
								<s:select list="servicetypelist" id="dedicatedid" name="dedicatedname" cssClass="form-control input-medium select2me"  headerKey="0" headerValue="--select--"></s:select>
								
										</div>
									</div>
							</div>
							<div class="form-group">
							<label class="control-label col-md-3">From Date:</label>
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
													<div class="form-group">
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
							</div>
		

								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="button" class="btn blue" onclick="getdata()">Submit</button>
										<!-- <button type="button" class="btn default" onclick="callCancell()">Cancel</button> -->
									</div>
								</div>
								
								
									<div class="portlet-body" id="creatededicate"
														style="display: none">
														<div style="text-align:left">
														<table
															class="table table-striped table-bordered table-hover"
															id="creatededicatetable" width="50">
															<thead>
	<tr>
	<th>SN</th><th><center>Sch No</center></th><th><center>Sch Type</center></th><th><center>Shift Type</center></th>
	<th><center>Route No</center></th>	<th><center>Company</center></th>
	<th><center>Trip No</center></th><th><center>Sch Kms</center></th><th><center>KMS Per<br>Day</center></th><th><center>Pass Rate</center></th>
<!-- 	<center>Amount/Day</center></th> -->
	
		
	</tr>
									
																
															</thead>
														</table>
													</div>
                                                   </div>
                                                   
                                                   	<div class="form-actions fluid" id='save'  style="display: none;">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn green" >save</button>
										<!-- <button type="button" class="btn default" onclick="callCancell()">Cancel</button> -->
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
	var  service=$("#dedicatedid").val();
	var startdate=$("#startdate").val();
	var enddate=$("#enddate").val();
	var var1=$("#startdate").val();
	var1=var1.split("-");
var1=var1[2]+"-"+var1[1]+"-"+var1[0];
	var var2=$("#enddate").val();
	var2=var2.split("-");
	var2=var2[2]+"-"+var2[1]+"-"+var2[0];
	 
	var d1 = Date.parse(var1);

	 
	var d3=Date.parse(var2);
	
if(depot==0){
	alert("Please select the depot");
}
else if(service==0){
	alert("Please select the Service Type");
}
else if(startdate=='' || startdate==null){
	alert("Please select the from date");
}
else if(enddate=='' || enddate==null){
	alert("Please select the to date");
}else if(d1 > d3){
	alert("To Date Should Be greater or Equal  Start Date");
}
else {
	$('#creatededicate').attr("style", "display:''");
	$("#save").show();
    table = $('#creatededicatetable');
    var oTable = table.dataTable({
    	"aLengthMenu" : [ [ 5,10, 15, 20, -1 ], [ 5,10, 15, 20, "All" ] // change
		// per
		// page
		// values
		// here
		],
		// set the initial value
		"iDisplayLength" : 5,
		"sAjaxSource" : "dedicated!getdata.action?depot="+depot+"&service="+service+"&startdate="+startdate+"&enddate="+enddate,
		"sPaginationType" : "bootstrap",
		"bDestroy" : true,
		"bSort" : false,
		"bFilter" : true,
		"bSelect" : true,
		"bPaginate" : false,
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

	jQuery('#creatededicatetable_wrapper .dataTables_filter input').addClass(
			"form-control input-xsmall input-inline"); // modify table
	// search input
	jQuery('#creatededicatetable_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");	
}


}
</script>