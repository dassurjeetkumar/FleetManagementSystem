<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<div class="page-content-wrapper">
		<div class="page-content">

			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Create Floor
							</div>
							
						</div>

						<div class="portlet-body form">
							<!-- BEGIN FORM-->
							<form action="#" class="form-horizontal" method="post">
								<font color="red"><s:actionerror /></font> <br>
								<br>
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Bus
											Station Name:<font color="red">*</font>
										</label>

										<div class="col-md-4">

											<s:select list="orgNameMap" id="parent_id"
												name="floor.orgChart.org_chart_id"
												cssClass="select2_category form-control" 
												></s:select>

											<%--  <select class="form-control" name="fareChartMaster.route_id" 
											 id="route_id" onclick="getRouteNo()">
											
											</select> --%>
											<s:if test="fieldErrors.get('orgChart.parent_id').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('orgChart.parent_id').get(0)" /></span>
											</s:if>
										</div>
									</div>
								</div>

								<div class="portlet box blue" id="floordiv">
									<div class="portlet-title">
										<div class="caption">
											<i class="fa fa-edit"></i>Floor Details
										</div>

									</div>
									<div class="portlet-body">
										<div class="table-toolbar">
											<div class="btn-group">
												<button id="floorNew" class="btn green">
													Add New Floor <i class="fa fa-plus"></i>
												</button>
											</div>

										</div>
										<div class="alert alert-danger" id="successmsg"	style="display: none">
											<p id="successfloor" style="color: green"></p>
											<span> </span>
										</div>	
											
										<div class="alert alert-danger" id="errormsg" style="display: none">
											<p id="errorfloor" style="color: red"></p>
										<span >	
										</span>
										</div>
										
										<table class="table table-striped table-hover table-bordered"
											id="sample_editable_1">
											<thead>
												<tr>
												
													<th>Floor Id</th>
													<th>Floor Name</th>
													<th>Status</th>
													<th>Edit</th>
													<th>Delete</th>			
												</tr>
												
											</thead>
											
										</table>
										<button id="floor_save_tab_bus" class="btn green">
													Back
												</button>
										<!-- <table id="floor_save_tab" ><tr><td><a class="save">Save</a></td></tr></table> -->
													
												
										<!-- <button  class="edit" >
													SaveFloor <i class="fa fa-plus"></i>
												</button> -->
									</div>
								</div>


								
							</form>

							<!-- END FORM-->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
$('#edit_').click(
		function() {
			var cnt = $(":checkbox:checked").length;
			var val;
			if (cnt == 0) {
				alert("Please select checkbox to edit");
			} else if (cnt > 1) {
				alert("Please select one checkbox to edit");
			} else {
				$("input[type='checkbox']:checked").each(
					function() {
					val = this.value;
					});
			
			document.forms[0].action = 'FloorAction!edit?id='+ val;
			document.forms[0].submit();
			}
		});
         				
		$('#delete_').click(
				function() {
					var cnt = $(":checkbox:checked").length;
					var val;
					if (cnt == 0) {
						alert("Please select checkbox to delete");
					} else if (cnt > 1) {
						alert("Please select one checkbox to delete");
					} else {
						$("input[type='checkbox']:checked").each(
								function() {
									val = this.value;
									type = 'text';
								});
						if (confirm('Are you sure you want to delete this record?')) {
						document.forms[0].action = 'DeleteFloorAction?id='+ val;
						document.forms[0].submit();
						}

					}				
		});
</script>

</body>
</html>