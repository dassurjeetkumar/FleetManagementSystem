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
	
	 <script src="assets/admin/pages/scripts/getLatLongForvehicleSector.js"
	type="text/javascript"></script> 
	<script language="javascript" type="text/javascript">
function limitText(limitField, limitCount, limitNum) {
	if (limitField.value.length > limitNum) {
		limitField.value = limitField.value.substring(0, limitNum);
	} else {
		limitCount.value = limitNum - limitField.value.length;
	}
}
</script>
<script>


function check()
{
	if(document.getElementById("orgname").value=="")
	{
	bootbox.alert("Corporation	Name Cannot be Empty");
	return false;
	}
}
	function validate() {
		alert("in Validate");
		if (document.getElementById("sector_name").value == '') {
			alert('Please Enter a New Sector Name');
			return false;
		}
		if (document.getElementById("notes").value == '') {
			alert('Please Enter Remarks');
			return false;
		}

		document.forms[0].action = 'createVehicleSector.action';
		document.forms[0].submit();
	}
	function goView() {
		document.forms[0].action = 'viewVehicleSectorList.action';
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
					SECTOR   <small></small>
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
								<i class="fa fa-gift"></i>Create Sector 
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
							<form action="createVehicleSectorAction.action" name="RevenueSector"
								method="post" class="form-horizontal">
								<s:if test="%{insertstaus=='duplicate'}">
									<font color="red">Sector Name Already Exists</font>
								</s:if>
								<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Sector Name:<font
											color="red">*</font></label>

										<div class="col-md-4">
											<input type="text" class="form-control" id="sector_name"
												maxlength="20" name="revenue.sector_name"
												value='<s:property value="revenue.sector_name"/>'>
											<s:if
												test="fieldErrors.get('revenue_sector_name').size() > 0">
												<span style="color: red;">
												<s:property
														value="fieldErrors.get('revenue_sector_name').get(0)" /></span>
											</s:if>
										</div>
									</div>
								</div>

                              


                                    <div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Geo Fence <font
											color="red">*</font></label>

										<div class="col-md-4">
											<input type="text" class="form-control" id="geodata"
												name="newlineString" readonly="readonly"
												value='<s:property value="revenue.geo_fence"/>'>
												
												<s:if test="fieldErrors.get('revenue_geo_fence').size() > 0">
										<span style="color: red;"><s:property												
										value="fieldErrors.get('revenue_geo_fence').get(0)" /></span> 								
										</s:if> 
										</div>
										<a data-toggle="modal" class="btn blue" role="button"
											 onclick="initializea()">Capture Geocode </a>
									</div>

									

							       </div> 
                                <div class="form-group">
									<label class="col-md-3 control-label " id="status">Status<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select class="form-control" id="status" name="revenue.status">
											<option value="ACTIVE">ACTIVE</option>

										</select>

									</div>
								</div>

                                <div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Remarks:</label>

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
										<button type="submit" class="btn blue" onSubmit="check()">Save</button>
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
	tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
	aria-hidden="true" >
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">Capture GeoCode From Map</h4>
			</div>
			<div >
				<p>
				<div class="portlet box purple ">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i> Capture GeoCode
						</div>
					</div>
					<div >
						<form class="form-horizontal" role="form" action="#" method="post">
							 <input
								type="hidden" name="requestType" id="requestType" value="map" />
							<div>
								<div class="portlet solid purple" >
									<div class="form-group">
										<label class="col-md-3 control-label"></label>
									</div>
									<div class="portlet-body" >
									<input id="pac-input" class="form-control input-lg" size="100" type="text" placeholder="Search Box">
										<div id="gmap_marker_vehicleSector" class="gmaps" ></div>
									</div>
								</div>
							</div>
							<div style="position: center" >
								<center><button type="button" class="btn green"
									onclick="submitMapForm()" data-dismiss="modal">Save</button>
								<button aria-hidden="true" data-dismiss="modal"
									class="btn default">Cancel</button></center>
									
							</div>
							
						</form>
					</div>
				</div>
				</p>
			</div>
			
		</div>
	</div>
</div>
<script>
function initializea()
   {
	window.open("pages/vts/SarthiVehicleGeofenceMap.jsp", "Capture GeoFence", "width=500, height=500");
	}
</script>

</html>