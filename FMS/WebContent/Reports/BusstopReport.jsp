<%@ taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE body PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<script>
function getdisplayexcel(){
	document.form.action = "excelfile.action?status="+status;
	document.form.submit();
}
function getdisplaypdf(){
	document.form.action = "pdffile.action?status="+status;
	document.form.submit();
}



</script>




<body >
<div class="page-content-wrapper">
		<div class="page-content">
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div class="modal fade" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">Modal title</h4>
						</div>
						<div class="modal-body">
							 Widget settings form goes here
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue">Save changes</button>
							<button type="button" class="btn default" data-dismiss="modal">Close</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<!-- BEGIN STYLE CUSTOMIZER -->
			<div class="theme-panel hidden-xs hidden-sm">
				
				<div class="toggler-close">
				</div>
				<div class="theme-options">
					<div class="theme-option theme-colors clearfix">
						<span>
						THEME COLOR </span>
						<ul>
							<li class="color-default current tooltips" data-style="default" data-original-title="Default">
							</li>
							<li class="color-darkblue tooltips" data-style="darkblue" data-original-title="Dark Blue">
							</li>
							<li class="color-blue tooltips" data-style="blue" data-original-title="Blue">
							</li>
							<li class="color-grey tooltips" data-style="grey" data-original-title="Grey">
							</li>
							<li class="color-light tooltips" data-style="light" data-original-title="Light">
							</li>
							<li class="color-light2 tooltips" data-style="light2" data-html="true" data-original-title="Light 2">
							</li>
						</ul>
					</div>
					<div class="theme-option">
						<span>
						Layout </span>
						<select class="layout-option form-control input-small">
							<option value="fluid" selected="selected">Fluid</option>
							<option value="boxed">Boxed</option>
						</select>
					</div>
					<div class="theme-option">
						<span>
						Header </span>
						<select class="page-header-option form-control input-small">
							<option value="fixed" selected="selected">Fixed</option>
							<option value="default">Default</option>
						</select>
					</div>
					<div class="theme-option">
						<span>
						Sidebar </span>
						<select class="sidebar-option form-control input-small">
							<option value="fixed">Fixed</option>
							<option value="default" selected="selected">Default</option>
						</select>
					</div>
					<div class="theme-option">
						<span>
						Sidebar Position </span>
						<select class="sidebar-pos-option form-control input-small">
							<option value="left" selected="selected">Left</option>
							<option value="right">Right</option>
						</select>
					</div>
					<div class="theme-option">
						<span>
						Footer </span>
						<select class="page-footer-option form-control input-small">
							<option value="fixed">Fixed</option>
							<option value="default" selected="selected">Default</option>
						</select>
					</div>
				</div>
			</div>
			<!-- END STYLE CUSTOMIZER -->
			<!-- BEGIN PAGE HEADER-->
			
			<!-- END PAGE HEADER-->
			
			
			
			<!-- BEGIN PAGE CONTENT-->
			<form name="form" action="">
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box blue-hoki">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>Bus Stop list
							</div>
							
							
						    	<!-- div class="tools"-->
						    	<!-- button type="button" class="btn blue"  onclick="getdisplayexcel()">test</button-->
						
								<!-- /div-->
								
									<div class="tools">
							  <button id="sample_editable_1_new" class="btn blue"   onclick="getdisplayexcel()"> Excel </button>
							    <button id="sample_editable_1_new" class="btn blue"   onclick="getdisplaypdf()"> PDF </button>
							  </div>
									
								
								<a href="javascript:;" class="reload">
								</a>
								<a href="javascript:;" class="remove">
								</a>
							</div>
						</div>
						<div class="portlet-body">
						
						
						<div class="form-body">
												<div class="form-group">
													<label class="col-md-3 control-label">Status :</label>
													<div class="col-md-4">
													 <select class="form-control" id="status" name="status" >
														<option value="New">New</option>
														<option value="Inactive">Inactive</option>
														<option value="Active">Active</option>
														<option value="Approved">Approved</option>
														<option value="All">All</option>
													</select>
																									
												</div>
												<div>
													<button type="button" class="btn blue" onclick="testing()">Submit</button>
													</div>
												
												</div>
												
						
						
						
							<div class="portlet-body" id="datatableforbusstop" style="visibility: hidden">
					
							<input type="hidden" name="requestType" id="requestType"  value="text" />
												<table  id="busstopreportdetails" class="table table-striped table-bordered table-hover" >
										<thead>
									
											<tr >
												
												 <th>Bus Stop Name</th>
												<th>Bus Stop Name in Kannada</th>
												<th>Bus Stop code</th>
												<th>Bus Stop code in Kannada</th>
												<th>locality</th>
												<th>landmark</th>
												<th>is_sheltered</th>
												<th>alias1</th>
												<th>alias2</th>
												<th>alias3</th>
												<th>alias4</th>
												<th>alias5</th>
												<th>alias6</th>
												<th>Kannada in alias1</th>
												<th>Kannada in alias2</th>
												<th>Area Ppulation</th>
												<th>Stop Size</th>
												<th>Fare Stage</th>
												<th>Description</th>
												<th>Status</th>
												
													</tr>
										</thead>

									</table>
									</div>
						</div>
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
					
					
					
				</div>
				</form>
			</div>
			<!-- END PAGE CONTENT-->
			
		</div>
	
	</body>
	<script>

function testing(){
	
	var div = document.getElementById('datatableforbusstop');
	div.style.visibility = 'visible';
	
	var status=document.getElementById("status").value;
	$('#busstopreportdetails').dataTable({
        "aLengthMenu": [
            [20, 50, 100],
            [20, 50, 100] // change per page values here
        ],
        // set the initial value
        "iDisplayLength": 20,
        "bProcessing" : true,
        "bServerSide" : true,
        "sAjaxSource" :"showBusstopList.action?status="+status,
        "sPaginationType": "bootstrap",
        "bDestroy": true
        /*"oLanguage": {
            "sLengthMenu": "_MENU_ records",
            "oPaginate": {
                "sPrevious": "Prev",
                "sNext": "Next"
            }
        },*/
       
        
    });
}
</script>
	</html>