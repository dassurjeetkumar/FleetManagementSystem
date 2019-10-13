<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places,drawing"></script>
<script src="assets/admin/pages/scripts/editGeoCodeForRevenueSector.js"
	type="text/javascript"></script>
<script>
	function validate() {
		
		if (document.getElementById("sector_name").value == '') {
			alert('Please Enter a New Sector Name');
			return false;
		}
		if (document.getElementById("notes").value == '') {
			alert('Please Enter Remarks');
			return false;
		}

		document.forms[0].action = 'addEditedRevenueSector.action';
		document.forms[0].submit();
	}
	function goView()
	{
		document.forms[0].action = 'viewInventoryTicketPassTypeList.action';
		document.forms[0].submit();
	}
	
	
</script>

<title>Insert title here</title>
</head>
<body>
	<div class="page-content-wrapper">

		<div class="page-content">
			<!-- BEGIN PAGE HEADER-->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
							  TICKET  INVENTORY PASS DAY <small></small>
					</h3>

					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->

			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Edit Ticket Inventory  Pass Day
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
							<s:actionmessage />
							<!-- BEGIN FORM-->
							<form action="addEditedInventoryTicketPassType.action" method="post"
								class="form-horizontal">
								<s:if test="%{updatestatus=='duplicate'}">
									<font color="red">Day_Month Already Exists</font>
								</s:if>
								
<!-- 								<div class="form-body"> -->

<!-- 									<div class="form-group"> -->
<!-- 										<label class="col-md-3 control-label">Sector Id:<font -->
<!-- 											color="red"></font></label> -->

<!-- 										<div class="col-md-4"> -->
<!-- 											<input type="text" class="form-control" id="sector_id"    -->
<!-- 												 name="revenue.sector_id"  -->
<%-- 											value='<s:property value="revenue.sector_id" />'>   --%>
<%-- 											<s:if --%>
<%-- 												test="fieldErrors.get('revenue_sector_name').size() > 0"> --%>
<%-- 												<span style="color: red;"><s:property --%>
<%-- 														value="fieldErrors.get('revenue_sector_name').get(0)" /></span> --%>
<%-- 											</s:if> --%>
<!-- 										</div>  -->
<!--  									</div>  -->
<!-- 							</div>  -->
								
								<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Day_Month<font
											color="red">*</font></label>

										<div class="col-md-4">
											<input type="text" class="form-control" id="day_month"
												maxlength="20" name="inventoryticketpasstype.day_month"
												value='<s:property value="inventoryticketpasstype.day_month"/>'>
												 <input
												type="hidden" id="user_idd" maxlength="20"
												name="inventoryticketpasstype.inventorypass_id"
												value='<s:property value="inventoryticketpasstype.inventorypass_id"/>'>
											<s:if	test="fieldErrors.get('inventoryticketpasstype_daymonth').size() > 0">
												<span style="color: red;"> 
												<s:property value="fieldErrors.get('inventoryticketpasstype_daymonth').get(0)" /></span> 
													 											</s:if> 										</div>
									</div>
								</div>
								
								
								
								
								
								
								
								
								
								    <div class="form-group">
									<label class="col-md-3 control-label " id="status">Pass Type<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select class="form-control" id="status" name="inventoryticketpasstype.pass_type">
											<option value="1">Daily Pass </option>
											<option value="2"> Monthly Pass</option>

										</select>

									</div>
								</div>


							

								<div class="form-group">
									<label class="col-md-3 control-label">Status</label>
									<div class="col-md-4">
									<s:select cssClass="form-control" id="status" name="inventoryticketpasstype.status" list="{'ACTIVE','INACTIVE'}"></s:select>
<%-- 										<select class="form-control" id="status" name="revenue.status"> --%>
										     
<!-- 											<option value="ACTIVE"></option> -->
<!-- 											<option value="">INACTIVE</option> -->
<%-- 										</select> --%>

									</div>
								</div>
								<script>
															var status = "<s:property value="inventoryticketpasstype.status"/>";
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



								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn blue" onSubmit="validate()">Save</button>
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