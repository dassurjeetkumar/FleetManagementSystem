<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places,drawing"></script>
 <script src="assets/admin/pages/scripts/editGeofenceForVehicleSector.js"
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

		document.forms[0].action = 'addEditedVehicleSector.action';
		document.forms[0].submit();
	}
	function goView()
	{
		document.forms[0].action = 'viewVehicleSectorList.action';
		document.forms[0].submit();
	}
	
	
</script>

<title>Insert title here</title>
</head>
<body>

	<div class="page-content-wrapper">

		<div class="page-content">
		<%-- <%if (access.equalsIgnoreCase("Y")){%>
		<%if (edit.equalsIgnoreCase("Y")){%> --%>
			<!-- BEGIN PAGE HEADER-->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
					 SECTOR <small></small>
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
								<i class="fa fa-gift"></i>Edit Sector 
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
							<form action="addEditedVehicleSector.action" method="post"
								class="form-horizontal">
								<s:if test="%{updatestatus=='duplicate'}">
									<font color="red">Sector Name Already Exists</font>
								</s:if>
								

								
								<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Sector Name:<font
											color="red">*</font></label>

										<div class="col-md-4">
											<input type="text" class="form-control" id="sector_name"
												 name="revenue.sector_name"  maxlength="20"
												value='<s:property value="revenue.sector_name  "/>'>
												
												
												 <input
												type="hidden" id="sector_id" maxlength="20"
												name="revenue.sector_id"
												value='<s:property value="revenue.sector_id"/>'>
											<s:if
												test="fieldErrors.get('revenue_sector_name').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('revenue_sector_name').get(0)" /></span>
											</s:if>
										</div>
									</div>
								</div>
                                  <%-- <div class="form-body"> 

									<div class="form-group">
										<label class="col-md-3 control-label">
											 Vehicle Number:	<span class="required"> * </span></label>

										<div class="col-md-4"> 
 											
 											<s:select list="vehicleidlist" id="vehicleidlistid" name="revenue.vehicle.vehicleId"   
													cssClass="select2_category form-control" headerKey="0" headerValue="Select"></s:select>   
 											<s:if test="fieldErrors.get('vehicleId').size() > 0"> 
		     								<span style="color:red;"><s:property value="fieldErrors.get('vehicleId').get(0)" /></span> 
 											</s:if> 
										</div>
									</div>
								</div> --%>


								<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Geo Fence:<font
											color="red">*</font></label>

										<div class="col-md-4">
											<input type="text" class="form-control" id="geodatasarthi"
												name="newlineString" readonly="readonly"
												value='<s:property value="revenue.geo_fence"/>'>
												<s:if test="fieldErrors.get('revenue_geo_fence').size() > 0">
										<span style="color: red;"><s:property
												value="fieldErrors.get('revenue_geo_fence').get(0)" /></span>
									</s:if>
										</div>
										<a data-toggle="modal" class="btn blue" role="button"
											 onclick="initializea()">Edit Geocode </a>
									</div>

									

								</div>

								<div class="form-group">
									<label class="col-md-3 control-label">Status</label>
									<div class="col-md-4">
									<s:select cssClass="form-control" id="status" name="revenue.status" list="{'ACTIVE','INACTIVE'}"></s:select>
<%-- 										<select class="form-control" id="status" name="revenue.status"> --%>
										     
<!-- 											<option value="ACTIVE"></option> -->
<!-- 											<option value="">INACTIVE</option> -->
<%-- 										</select> --%>

									</div>
								</div>
								<script>
															var status = "<s:property value="revenue.status"/>";
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

<!-- 								<div class="form-body"> -->

<!-- 									<div class="form-group"> -->
<!-- 										<label class="col-md-3 control-label">Note</label> -->
<!-- 									 <div class="col-md-4"> -->
<%-- 											<textarea  class="form-control" id="notes" maxlength="100" name="revenue.notes"><s:property value="revenue.notes"/></textarea> --%>
<!-- 										</div> -->

<!-- 									</div> -->
<!-- 								</div> -->
                                       <div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Notes:</label>

										<div class="col-md-4">
											<textarea rows="3" class="form-control" id="notes" maxlength="100"	name="revenue.notes"><s:property value="revenue.notes"/></textarea>
											<s:if test="fieldErrors.get('revenue_note').size() > 0">
										       <span style="color: red;"><s:property
												value="fieldErrors.get('revenue_note').get(0)" /></span>
									</s:if>
										</div>
									</div>
								</div>



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


<div style="display: none;" id="myModal1" class="modal fade"
	role="dialog" aria-labelledby="myModalLabel1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">Capture GeoCode From Map</h4>
			</div>
			<div class="portlet box purple ">
			<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i> Capture GeoCode
						</div>
					</div>
			<div>
				<form class="form-horizontal" role="form" action="#" method="post">
					<input type="hidden" name="requestType" id="requestType"
						value="map" />
					<div>

<!-- 						<div class="form-group"> -->
<!-- 							<label class="col-md-4 control-label"></label> -->
<!-- 						</div> -->
<!-- 						<div class="portlet-body"> -->
<!-- 							<div id="edit-revenue-map" class="gmaps"></div> -->

<!-- 						</div> -->

                                <div class="portlet solid purple" >
									<div class="form-group">
										<label class="col-md-3 control-label"></label>
									</div>
									<div class="portlet-body" >
										<div id="edit-VehicleSector-map" class="gmaps" ></div>
									</div>
								</div>

					</div>
						<div style="position: center">
						<center>
							<button type="button" class="btn green" onclick="submitMapForm()"
								data-dismiss="modal">Save</button>
							<button type="button" class="btn green" onclick="clearMapForm()"
								id="clearMap" onclick="clearForm()">Clear Map</button>
							<button type="button" class="btn green" id="showForm"
								onclick="initialize()" style="display: none">Show Map</button>
							<button aria-hidden="true" data-dismiss="modal"
								class="btn default">Cancel</button>
						</center>

					</div>

				</form>

			</div>
</div>
		</div>
	</div>
</div>
<script>
function initializea()
{	var param = $('#geodata').val();
	window.open("pages/vts/editSarthiSectorGeofenceMap .jsp","Edit GeoFence", "width=500, height=500");
	}
</script>

</html>