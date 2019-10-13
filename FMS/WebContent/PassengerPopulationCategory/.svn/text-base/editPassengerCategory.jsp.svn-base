<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
<script type="text/javascript">
	function validate() {

		/* if (document.getElementById("passenger_category_name").value == '') {
			alert('Please Enter a Passenger Category');
			return false;
		}
		if (document.getElementById("note").value == '') {
			alert('Please Enter Note');
			return false;
		}
 */
		document.forms[0].action = 'editPassengerCategoryAction.action';
		document.forms[0].submit();
	}
	function goView()
	{
		document.forms[0].action = 'viewPassengerCategory.action';
		document.forms[0].submit();
	}
	/* $(document).ready(function() {
		   window.history.pushState("","", "editDeviceType.action");
		 }); */
</script>
</head>
<body>
	<input type="text" id='a'>

	<div class="page-content-wrapper">
		<div class="page-content">
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						PASSENGER POPULATION CATEGORY <small></small>
					</h3>

					<!-- END PAGE TITLE & BREADCRUMB-->
					<div class="portlet box grey-cascade">
					

						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Edit Passenger Population Category
							</div>

						</div>

						<div class="portlet-body form">
							<s:if test="hasActionErrors()">
								<div class="alert alert-danger">
									<button class="close" data-close="alert"></button>
									<span> <s:actionerror />
									</span>
								</div>


							</s:if>
							<!-- BEGIN FORM-->
							<form action="editPassengerCategoryAction.action" class="form-horizontal" method="post">
							<s:if test="%{updatestaus=='duplicate'}"><font color="red">Could not Update Duplicate Passenger Category</font></s:if>
							<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Passenger Category Id: </label>
										<div class="col-md-4">
											 <input type="text" class="form-control" name="passengercategory.passenger_category_id" 
											 id="passenger_category_id" maxlength="8" 
											 value='<s:property value="passengercategory.passenger_category_id"/>' readonly="readonly">
										</div>
									</div>
								</div>
															
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Passenger Category :<font color="red">*</font></label>

										<div class="col-md-4">
											<input type="text" class="form-control" id="passenger_category_name" maxlength="20"
												name="passengercategory.passenger_category_name" 
												value='<s:property value="passengercategory.passenger_category_name"/>'>
												
												 <s:if test="fieldErrors.get('Passenger_category').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('Passenger_category').get(0)" /></span>
											</s:if>
										</div>
									</div>
								</div>
								
								<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Remarks:</label>

										<div class="col-md-4">
											<textarea rows="3" class="form-control" id="notes" name="passengercategory.notes" maxlength="100" ><s:property value="passengercategory.notes"/></textarea>
<%-- 										<s:if test="fieldErrors.get('componentTypenotes').size() > 0"> --%>
<%-- 		     								<span style="color:red;"><s:property value="fieldErrors.get('componentTypenotes').get(0)" /></span> --%>
<%-- 											</s:if> --%>
										</div>
									</div>
								</div>
								<div class="form-body">
				<div class="form-group">
								<label class="control-label col-md-3"> Status:</label>
								<div class="col-md-4">
									<div class="input-group input-medium" style="width: auto">
									<select class="form-control" id="status"
																	name="passengercategory.status">

																	<option id="active" value="ACTIVE">ACTIVE</option>
																	<option id="deactive" value="INACTIVE">INACTIVE</option>
																</select>
										<!-- <input type="text" class="form-control" readonly="readonly" 
											value="ACTIVE" name="battery.status" /> -->
									</div>
									<script>
															var status = "<s:property value="passengercategory.status"/>";
															if (status != undefined) {
																if (status == "ACTIVE"
																		|| status == "ACTIVE") {
																	document
																			.getElementById("active").selected = true;
																} else {
																	document
																			.getElementById("deactive").selected = true;
																}
															}
														</script>
								</div>
							</div>
							</div>
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn blue" onsubmit="validate()">Save</button>
										<button type="button" class="btn default" onclick="goView()">Cancel</button>
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

</body>
</html>