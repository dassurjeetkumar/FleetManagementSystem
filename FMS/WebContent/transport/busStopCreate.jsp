<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.trimax.its.utility.model.AccessRights"%>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao"%>
<%
	AccessRightsDao accessrightdao = new AccessRightsDao();
	AccessRights accessrights = new AccessRights();
	int usrsessionid = (Integer) request.getSession().getAttribute(
			"userid");
	accessrights = accessrightdao.accessRightsdetails(usrsessionid,
			"SaveBusStopMap.action");
	String access = accessrights.getACCESS();
	String create = accessrights.getCREATE();
%>
<head>
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Expires" content="-1">
<sx:head />
</head>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css"/> -->
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="assets/global/plugins/jquery-1.11.0.min.js"
	type="text/javascript"></script>
<%--  <script src="assets/global/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script> --%>
<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<%-- <script src="assets/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script> --%>
<%-- <script --%>
<%-- 	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places,drawing"></script> --%>
<script src="https://maps.googleapis.com/maps/api/js?client=gme-trimaxitinfrastructure&sensor=false&libraries=places,drawing&signature=CpSOeA5B6SzC2rxqlzd2JwenGX0="></script>
<script src="assets/admin/pages/scripts/busStopCreate.js"
	type="text/javascript"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script src="assets/admin/pages/scripts/kannada.js"
	type="text/javascript"></script>

<script src="assets/admin/pages/scripts/converter.js"
	type="text/javascript"></script>
	
	<script type="text/javascript" src="assets/admin/pages/scripts/jquery-1.5.js"></script>
    <script type="text/javascript" src="assets/admin/pages/scripts/map_nudi_baraha.js?v=3"></script>
    <script type="text/javascript" src="assets/admin/pages/scripts/helper.js?v=3"></script>
    <script type="text/javascript" src="assets/admin/pages/scripts/a2u.js?v=4"></script>


<div class="page-content-wrapper">
	<div class="page-content">
		<%
			if (access.equalsIgnoreCase("Y")) {
		%>

		<div class="portlet solid white" style="min-height: 574px">
			<%
				if (create.equalsIgnoreCase("Y")) {
			%>
			<div class="form-group">
				<label class="col-md-3 control-label"></label>


			</div>
			<div class="portlet-body" style="min-height: 574px">
				<input id="pac-input" class="form-control input-lg" size="100"
					type="text" placeholder="Search Box">

				<div id="gmap_marker" class="gmaps" style="min-height: 574px">
				</div>
			</div>
		</div>
	</div>
</div>



<div style="display: none;" id="myModal1" class="modal fade"
	tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
	aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
			</div>
			<div class="modal-body">
				<p>
				<div class="portlet box purple ">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i> CREATE BUS STOPS
						</div>

					</div>

					<div class="alert alert-danger">
						<!-- <button class="close" data-close="alert"></button> -->
						<span id="errmsg"> </span>

					</div>

					<div class="portlet-body form">
						<form class="form-horizontal" role="form" action="AddStop.action"
							method="post" name="busstopmapform">
							<input type=hidden name="bustops.id" id="busid1" value="" /> <input
								type="hidden" name="requestType" id="requestType" value="map" />
							<div class="form-body">
								<div class="form-body">
									<div class="form-group">
										<div class="row">
											<div class="col-md-3">
												<label class="col-md-10 control-label">Bus Stop Name
													*</label>
											</div>
											<div class="col-md-6">
												<!-- 
									<div class="col-md-9"> -->
												<input class="form-control input-lg" placeholder=""
													id="busStopName" name="bustops.busStopName" type="text"
													maxlength="80"
													 onFocus="javascript:print_many_words('busStopName','busStopNameKannada')"
                							         onKeyUp="javascript:print_many_words('busStopName','busStopNameKannada')"
                							        	 value="<s:property value="bustops.busStopName"/>" onblur="spclchar(this);"
													 >
												<!-- </div> -->
											</div>
<!-- 											<div class="col-md-3"> -->

<!-- 												<button class="btn purple" type="button" onclick="convert()">Convert</button> -->
<!-- 											</div> -->
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Bus Stop Name
											(Nudi) </label>
										<div class="col-md-6">
											<input class="form-control input-lg" placeholder=""
												id="busStopNames" name="bustops.bus_stop_name_nudi"
												type="text" maxlength="80" 
												value='<s:property value="bustops.bus_stop_name_nudi"/>' 
												/>
										</div>
<!-- 											<div class="col-md-3"> -->

												<button class="btn purple" type="button" id="convert-button" onFocus="javascript:converter_data_init('busStopNames','busStopNameKannada')"
													>Convert</button>
<!-- 											</div> -->
										
									</div>


									<div class="form-group">
										<label class="col-md-3 control-label">Bus Stop Name
											(Kannada) </label>
										<div class="col-md-9">
											<input class="form-control input-lg" placeholder=""
												id="busStopNameKannada" name="bustops.busStopNameKannada"
												type="text" maxlength="80" 
												value='<s:property value="bustops.busStopNameKannada1"/>' 
												/>
										</div>
									</div>

									<div class="form-group">
										<div class="row">
											<div class="col-md-3">
												<label class="col-md-10 control-label">Bus Stop Code
													*</label>
											</div>
											<div class="col-md-6">
												<!-- 
									<div class="col-md-9"> -->
												<input class="form-control input-lg" placeholder=""
													id="bus_stop_code" name="bustops.bus_stop_code" 
													 onFocus="javascript:print_many_words('bus_stop_code','bus_stop_code_kannada')"
                							          onKeyUp="javascript:print_many_words('bus_stop_code','bus_stop_code_kannada')"
													type="text" onblur="spclchar(this);"
													maxlength="30" value='<s:property value="bustops.bus_stop_code"/>' >
												<!-- </div> -->
											</div>
<!-- 											<div class="col-md-3"> -->

<!-- 												<button class="btn purple" type="button" -->
<!-- 													onclick="convertcode()">Convert</button> -->
<!-- 											</div> -->
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">Bus Stop
											Code(Kannada) </label>
										<div class="col-md-9">
											<input class="form-control input-lg" placeholder=""
												id="bus_stop_code_kannada"
												name="bustops.bus_stop_code_kannada" type="text"
												maxlength="30" value='<s:property value="bustops.busStopCodeKannada1"/>' />
										</div>
									</div>




									<!-- <div class="form-group">
									<label class="col-md-3 control-label">Bus Stop Code</label>
									<div class="col-md-9">
										<input class="form-control input-lg" placeholder=""
											id="bus_stop_code" name="bustops.bus_stop_code" type="text"
											onblur="convertcode()">
									</div>
								</div> -->




									<div class="form-group">
										<!-- <label class="col-md-3 control-label">Lattitude(Currrent)</label> -->
										<div class="col-md-9">
											<input class="hidden" placeholder="" type="text"
												id="latitude" name="bustops.latitude">
										</div>
									</div>

									<div class="form-group">
										<!-- <label class="col-md-3 control-label">Longitud(Currrent)</label> -->
										<!--  -->
										<div class="col-md-9">
											<input class="hidden" placeholder="" type="text"
												id="longitude" name="bustops.longitude">
										</div>
									</div>



									<div class="form-group">
										<label class="col-md-3 control-label">Locality</label>
										<div class="col-md-9">
											<input class="form-control input-sm" placeholder=""
												type="text" id="locality" name="bustops.locality"
												maxlength="180" onblur="spclchar(this);">
										</div>
									</div>


									<!-- <div class="form-group">
										<label class="col-md-3 control-label">Lattitude(New)</label>
										<div class="col-md-9">
											<input class="form-control input-sm" placeholder="Default Input" type="text" id="" name="">
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Longitude(New)</label>
										<div class="col-md-9">
											<input class="form-control input-sm" placeholder="Default Input" type="text" id="" name="">
										</div>
									</div> -->

									<div class="form-group">
										<label class="col-md-3 control-label">Landmark</label>
										<div class="col-md-9">
											<input class="form-control input-sm" placeholder=""
												type="text" id="landmark" name="bustops.landmark"
												maxlength="40" onblur="spclchar(this);">
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">Is Sheltered</label>
										<div class="col-md-9">
											<select class="form-control input-sm" id="sheltered"
												name="bustops.sheltered">
												<option value="N">N</option>
												<option value="Y">Y</option>

											</select>
										</div>
									</div>

									<!-- <div class="form-group">
									<label class="col-md-3 control-label">Speed</label>
									<div class="col-md-9">
										<input class="form-control input-sm"
											placeholder="Default Input" type="text" id="speed"
											name="bustops.speed">
									</div>
								</div> -->

									<!-- <div class="form-group">
									<label class="col-md-3 control-label">Accuracy</label>
									<div class="col-md-9">
										<input class="form-control input-sm"
											placeholder="Default Input" type="text" id="accuracy"
											name="bustops.accuracy">
									</div>
								</div> -->

									<!-- <div class="form-group">
									<label class="col-md-3 control-label">Altitude</label>
									<div class="col-md-9">
										<input class="form-control input-sm"
											placeholder="Default Input" type="text" id="altitude"
											name="bustops.altitude">
									</div>
								</div> -->

									<!-- <div class="form-group">
									<label class="col-md-3 control-label">Bearing</label>
									<div class="col-md-9">
										<input class="form-control input-sm"
											placeholder="Default Input" type="text" id="bearing"
											name="bustops.bearing">
									</div>
								</div> -->

									<!-- <div class="form-group">
									<label class="col-md-3 control-label">Device Code</label>
									<div class="col-md-9">
										<input class="form-control input-sm"
											placeholder="Default Input" type="text" id="deviceCode"
											name="bustops.deviceCode">
									</div>
								</div> -->

									<div class="form-group">
										<div class="row">
											<div class="col-md-3">
												<label class="col-md-10 control-label">Alias1 </label>
											</div>
											<div class="col-md-6">
												<!-- 
									<div class="col-md-9"> -->
												<input class="form-control input-sm" placeholder=""
													type="text" id="alias1" name="bustops.alias1"
													 onFocus="javascript:print_many_words('alias1','kalias1')"
                							          onKeyUp="javascript:print_many_words('alias1','kalias1')"
													maxlength="80" onblur="spclchar(this);" value='<s:property value="bustops.alias1"/>' >
												<!-- </div> -->
											</div>
<!-- 											<div class="col-md-3"> -->

<!-- 												<button class="btn purple" type="button" -->
<!-- 													onclick="convertcodeAlias1()">Convert</button> -->
<!-- 											</div> -->
										</div>
									</div>


									<div class="form-group">
										<label class="col-md-3 control-label">Kannada Alias 1</label>
										<div class="col-md-9">
											<input class="form-control input-sm" placeholder=""
												type="text" id="kalias1" name="bustops.kalias1"
												maxlength="80" value='<s:property value="bustops.kalias11"/>' >

										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-md-3">
												<label class="col-md-10 control-label">Alias2 </label>
											</div>
											<div class="col-md-6">
												<!-- 
									<div class="col-md-9"> -->
												<input class="form-control input-sm" placeholder=""
													type="text" id="alias2" name="bustops.alias2"
													onFocus="javascript:print_many_words('alias2','kalias2')"
                							          onKeyUp="javascript:print_many_words('alias2','kalias2')"
													maxlength="80" onblur="spclchar(this);" value='<s:property value="bustops.alias2"/>' >
												<!-- </div> -->
											</div>
<!-- 											<div class="col-md-3"> -->

<!-- 												<button class="btn purple" type="button" -->
<!-- 													onclick="convertcodeAlias2()">Convert</button> -->
<!-- 											</div> -->
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">Kannada Alias 2</label>
										<div class="col-md-9">
											<input class="form-control input-sm" placeholder=""
												type="text" id="kalias2" name="bustops.k_alias_2"
												maxlength="80" value='<s:property value="bustops.k_alias_21"/>' >
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-md-3">
												<label class="col-md-10 control-label">Alias3 </label>
											</div>
											<div class="col-md-6">
												<!-- 
									<div class="col-md-9"> -->
												<input class="form-control input-sm" placeholder=""
													type="text" id="alias3" name="bustops.alias3"
													onFocus="javascript:print_many_words('alias3','kalias3')"
                							          onKeyUp="javascript:print_many_words('alias3','kalias3')"
													maxlength="80" onblur="spclchar(this);" value='<s:property value="bustops.alias3"/>' >
												<!-- </div> -->
											</div>
<!-- 											<div class="col-md-3"> -->

<!-- 												<button class="btn purple" type="button" -->
<!-- 													onclick="convertcodeAlias3()">Convert</button> -->
<!-- 											</div> -->
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">Kannada Alias 3</label>
										<div class="col-md-9">
											<input class="form-control input-sm" placeholder=""
												type="text" id="kalias3" name="bustops.k_alias_3"
												maxlength="80" value='<s:property value="bustops.k_alias_31"/>'  >
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-md-3">
												<label class="col-md-10 control-label">Alias4 </label>
											</div>
											<div class="col-md-6">
												<!-- 
									<div class="col-md-9"> -->
												<input class="form-control input-sm" placeholder=""
													type="text" id="alias4" name="bustops.alias4"
													onFocus="javascript:print_many_words('alias4','kalias4')"
                							          onKeyUp="javascript:print_many_words('alias4','kalias4')"
													maxlength="80" onblur="spclchar(this);" value='<s:property value="bustops.alias4"/>' >
												<!-- </div> -->
											</div>
<!-- 											<div class="col-md-3"> -->

<!-- 												<button class="btn purple" type="button" -->
<!-- 													onclick="convertcodeAlias4()">Convert</button> -->
<!-- 											</div> -->
										</div>
									</div>


									<div class="form-group">
										<label class="col-md-3 control-label">Kannada Alias 4</label>
										<div class="col-md-9">
											<input class="form-control input-sm" placeholder=""
												type="text" id="kalias4" name="bustops.k_alias_4"
												maxlength="80" value='<s:property value="bustops.k_alias_41"/>' >

										</div>
									</div>

									<div class="form-group">
										<div class="row">
											<div class="col-md-3">
												<label class="col-md-10 control-label">Alias5 </label>
											</div>
											<div class="col-md-6">
												<!-- 
									<div class="col-md-9"> -->
												<input class="form-control input-sm" placeholder=""
													type="text" id="alias5" name="bustops.alias5"
													onFocus="javascript:print_many_words('alias5','kalias5')"
                							          onKeyUp="javascript:print_many_words('alias5','kalias5')"
													maxlength="80" onblur="spclchar(this);" value='<s:property value="bustops.alias5"/>' >
												<!-- </div> -->
											</div>
<!-- 											<div class="col-md-3"> -->

<!-- 												<button class="btn purple" type="button" -->
<!-- 													onclick="convertcodeAlias5()">Convert</button> -->
<!-- 											</div> -->
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">Kannada Alias 5</label>
										<div class="col-md-9">
											<input class="form-control input-sm" placeholder=""
												type="text" id="kalias5" name="bustops.k_alias_5"
												maxlength="80" value='<s:property value="bustops.k_alias_51"/>'>
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-md-3">
												<label class="col-md-10 control-label">Alias6 </label>
											</div>
											<div class="col-md-6">
												<!-- 
									<div class="col-md-9"> -->
												<input class="form-control input-sm" placeholder=""
													type="text" id="alias6" name="bustops.alias6"
													onFocus="javascript:print_many_words('alias6','kalias6')"
                							          onKeyUp="javascript:print_many_words('alias6','kalias6')"
													maxlength="80" onblur="spclchar(this);" value='<s:property value="bustops.alias6"/>' >
												<!-- </div> -->
											</div>
<!-- 											<div class="col-md-3"> -->

<!-- 												<button class="btn purple" type="button" -->
<!-- 													onclick="convertcodeAlias6()">Convert</button> -->
<!-- 											</div> -->
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Kannada Alias 6</label>
										<div class="col-md-9">
											<input class="form-control input-sm" placeholder=""
												type="text" id="kalias6" name="bustops.k_alias_6"
												maxlength="80" value='<s:property value="bustops.k_alias_61"/>' >
										</div>
									</div>




									<!-- <div class="form-group">
										<label class="col-md-3 control-label">Geocode</label>
										<div class="col-md-9">
											<input class="form-control input-sm" placeholder="Default Input" type="text" id="" name="">
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Geofence</label>
										<div class="col-md-9">
											<input class="form-control input-sm" placeholder="Default Input" type="text" id="" name="">
										</div>
									</div> -->

									<div class="form-group">
										<label class="col-md-3 control-label">Toll Zone</label>
										<div class="col-md-9">
											<select class="form-control input-sm" id="tollZone"
												name="bustops.tollZone">
												<option value="N">N</option>
												<option value="Y">Y</option>

											</select>
										</div>
									</div>

									<div class="form-group" id="toll_fee_div">
										<label class="col-md-3 control-label">Toll Fee</label>
										<div class="col-md-9">
											<input class="form-control input-sm" placeholder=""
												type="text" id="toll_fee" value="0.00"
												name="bustops.toll_fee" maxlength="6"
												onblur="checkDecimal(this);">
										</div>
									</div>

									<!-- <div class="form-group">
									<label class="col-md-3 control-label">Code1</label>
									<div class="col-md-9">
										<input class="form-control input-sm"
											placeholder="" type="text" id="code1"
											name="bustops.code1">
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-3 control-label">Code2</label>
									<div class="col-md-9">
										<input class="form-control input-sm"
											placeholder="" type="text" id="code2"
											name="bustops.code2">
									</div>
								</div> -->



									<!-- <div class="form-group">
									<label class="col-md-3 control-label">City Limit</label>
									<div class="col-md-9">
										<input class="form-control input-sm"
											placeholder="" type="text" id="cityLimit"
											name="bustops.cityLimit" maxlength="5">
									</div>
								</div>
 -->
									<div class="form-group">
										<label class="col-md-3 control-label">City Limit</label>
										<div class="col-md-9">
											<!-- <input class="form-control input-sm"
											placeholder="" type="text" id="cityLimit"
											name="bustops.cityLimit" maxlength="5"> -->
											<select class="form-control input-sm" id="cityLimit"
												name="bustops.cityLimit">
												<option value="Y">Y</option>
												<option value="N">N</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Ward No</label>
										<div class="col-md-9">
											<input class="form-control input-sm" placeholder=""
												type="text" id="wardNumber" name="bustops.wardNumber"
												maxlength="10" onblur="spclchar(this);">
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">Area Population</label>
										<div class="col-md-9">
											<input class="form-control input-sm" placeholder=""
												type="text" id="areaPopulation"
												name="bustops.areaPopulation" maxlength="20"
												onblur="spclchar(this);">
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">Towards</label>
										<div class="col-md-9">
											<input class="form-control input-sm" placeholder=""
												type="text" id="stop_direction"
												name="bustops.stop_direction" maxlength="70"
												onblur="spclchar(this);">
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">Stop Size</label>
										<div class="col-md-9">
											<select class="form-control input-sm" id="stopSize"
												name="bustops.stopSize">
												<option value="SMALL">SMALL</option>
												<option value="MEDIUM">MEDIUM</option>
												<!-- <option value="LARGE">LARGE</option> -->
											</select>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">Fare Stage</label>
										<div class="col-md-9">
											<select class="form-control input-sm" id="fareStage"
												name="bustops.fareStage">
												<option value="N">N</option>
												<option value="Y">Y</option>

											</select>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">Sub Stage</label>
										<div class="col-md-9">
											<select class="form-control input-sm" id="sub_stage"
												name="bustops.sub_stage">


											</select>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">Description</label>
										<div class="col-md-9">
											<input class="form-control input-sm" placeholder=""
												type="text" id="description" name="bustops.description"
												maxlength="80" onblur="spclchar(this);">
										</div>
									</div>





									<%-- <div class="form-group">
									<label class="col-md-3 control-label">BMTC Status</label>
									<div class="col-md-9">
										<select class="form-control input-sm" id="bmtc_status"
											name="bustops.bmtc_status">
											
											<option value="New">New</option>
											<option value="Inactive">Inactive</option>
											<option value="Approved">Approved</option>
										</select>
									</div>
								</div> --%>

									<div class="form-group">
										<label class="col-md-3 control-label">Bus Stop Type</label>
										<div class="col-md-9">
											<select class="form-control input-sm" id="point_type_id"
												name="bustops.point_type_id">
												<s:iterator value="listPoint" id="listPoint">
													<option value="<s:property value="point_type_id"/>">
														<s:property value="point_type_name" />
													</option>
												</s:iterator>
												<!-- <option value="New">New</option>
											<option value="Inactive">Inactive</option>
											<option value="Approved">Approved</option> -->
											</select>
										</div>
									</div>



									<div class="form-group" style="display: none;"
										id="org_chart_id_div">
										<label class="col-md-3 control-label">Bus Station</label>
										<div class="col-md-4" id="org_chart_id_select">
											<%-- <select class="form-control input-sm" id="org_chart_id"
											name="bustops.bus_stations.org_chart_id">
											<option value="-1">Select</option>
											<s:iterator value="listOrgcharts" id="listOrgcharts">
											<option value="<s:property value="org_chart_id"/>"><s:property value="org_name"/></option>
											</s:iterator>
											
										</select> --%>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">Status</label>
										<div class="col-md-9">
											<select class="form-control input-sm" id="status"
												name="bustops.status">

												<option value="New">New</option>
												<!-- <option value="Inactive">Inactive</option>
											<option value="Approved">Approved</option> -->
											</select>
										</div>
									</div>

									<%-- <div class="form-group">
										<label class="col-md-3 control-label">Local Bus Stop</label>
										<div class="col-md-9">
											<select class="form-control input-sm" id="" name="">
												<option>Y</option>
												<option>N</option>
											</select>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Point Type</label>
										<div class="col-md-9">
											<select class="form-control input-sm" id="" name="">
												<option>Y</option>
												<option>N</option>
											</select>
										</div>
									</div> --%>




								</div>
								<div class="form-actions right1">
									<button type="button" class="btn green"
										onclick="submitAjaxForm()">Submit</button>
									<button aria-hidden="true" data-dismiss="modal"
										class="btn default">Cancel</button>

								</div>
						</form>
						<%
							} else {
						%>



						<%@ include file="/pages/admin/error.jsp"%>


						<%
							}
						%>

						<%
							} else {
						%>



						<%@ include file="/pages/admin/error.jsp"%>


						<%
							}
						%>


					</div>
				</div>
				</p>
			</div>
			<!-- <div class="modal-footer">
											<button class="btn default" data-dismiss="modal" aria-hidden="true">Close</button>
											<button class="btn yellow">Save</button>
										</div> -->
		</div>
	</div>
</div>


<script>
	google.load("elements", "1", {
		packages : "transliteration"
	});
	google.load("language", "1");
	jQuery(document).ready(function() {
		initialize();
	});
	function reloadPage() {
		location.reload(true);
	}
	function convert() {
		//google.load("language", "1");
		//	alert("hiiii");
		//var a='["'+document.getElementById("busStopName").value+'"]';

		//alert(a);
		google.language
				.transliterate(
						[ document.getElementById("busStopName").value ],
						"en",
						"kn",
						function(result) {
							if (!result.error) {
								//var container = document.getElementById("transliteration");
								if (result.transliterations
										&& result.transliterations.length > 0
										&& result.transliterations[0].transliteratedWords.length > 0) {
									//alert(result.transliterations[0].transliteratedWords[0]);
									document
											.getElementById("busStopNameKannada").value = result.transliterations[0].transliteratedWords[0];
								}
							}
						});
	}

	function convertcode() {
		//google.load("language", "1");
		//	alert("hiiii");
		//var a='["'+document.getElementById("busStopName").value+'"]';

		//alert(a);
		google.language
				.transliterate(
						[ document.getElementById("bus_stop_code").value ],
						"en",
						"kn",
						function(result) {
							if (!result.error) {
								//var container = document.getElementById("transliteration");
								if (result.transliterations
										&& result.transliterations.length > 0
										&& result.transliterations[0].transliteratedWords.length > 0) {
									//alert(result.transliterations[0].transliteratedWords[0]);
									document
											.getElementById("bus_stop_code_kannada").value = result.transliterations[0].transliteratedWords[0];
								}
							}
						});
	}
	function convertcodeAlias1() {
		//google.load("language", "1");
		//	alert("hiiii");
		//var a='["'+document.getElementById("busStopName").value+'"]';

		
		google.language
				.transliterate(
						[ document.getElementById("alias1").value ],
						"en",
						"kn",
						function(result) {
							if (!result.error) {
								//var container = document.getElementById("transliteration");
								if (result.transliterations
										&& result.transliterations.length > 0
										&& result.transliterations[0].transliteratedWords.length > 0) {
									//alert(result.transliterations[0].transliteratedWords[0]);
									document
											.getElementById("kalias1").value = result.transliterations[0].transliteratedWords[0];
								}
							}
						});
	}
	function convertcodeAlias2() {
		//google.load("language", "1");
		//	alert("hiiii");
		//var a='["'+document.getElementById("busStopName").value+'"]';

		
		google.language
				.transliterate(
						[ document.getElementById("alias2").value ],
						"en",
						"kn",
						function(result) {
							if (!result.error) {
								//var container = document.getElementById("transliteration");
								if (result.transliterations
										&& result.transliterations.length > 0
										&& result.transliterations[0].transliteratedWords.length > 0) {
									//alert(result.transliterations[0].transliteratedWords[0]);
									document
											.getElementById("kalias2").value = result.transliterations[0].transliteratedWords[0];
								}
							}
						});
	}
	function convertcodeAlias3() {
		//google.load("language", "1");
		//	alert("hiiii");
		//var a='["'+document.getElementById("busStopName").value+'"]';

		//alert(id);
		
		google.language
				.transliterate(
						[ document.getElementById("alias3").value ],
						"en",
						"kn",
						function(result) {
							if (!result.error) {
								//var container = document.getElementById("transliteration");
								if (result.transliterations
										&& result.transliterations.length > 0
										&& result.transliterations[0].transliteratedWords.length > 0) {
									//alert(result.transliterations[0].transliteratedWords[0]);
									document
											.getElementById("kalias3").value = result.transliterations[0].transliteratedWords[0];
								}
							}
						});
	}
	function convertcodeAlias4() {
		//google.load("language", "1");
		//	alert("hiiii");
		//var a='["'+document.getElementById("busStopName").value+'"]';

		//alert(id);
		//if(id=="")
		google.language
				.transliterate(
						[ document.getElementById("alias4").value ],
						"en",
						"kn",
						function(result) {
							if (!result.error) {
								//var container = document.getElementById("transliteration");
								if (result.transliterations
										&& result.transliterations.length > 0
										&& result.transliterations[0].transliteratedWords.length > 0) {
									//alert(result.transliterations[0].transliteratedWords[0]);
									document
											.getElementById("kalias4").value = result.transliterations[0].transliteratedWords[0];
								}
							}
						});
	}
	function convertcodeAlias5() {
		//google.load("language", "1");
		//	alert("hiiii");
		//var a='["'+document.getElementById("busStopName").value+'"]';

		
		google.language
				.transliterate(
						[ document.getElementById("alias5").value ],
						"en",
						"kn",
						function(result) {
							if (!result.error) {
								//var container = document.getElementById("transliteration");
								if (result.transliterations
										&& result.transliterations.length > 0
										&& result.transliterations[0].transliteratedWords.length > 0) {
									//alert(result.transliterations[0].transliteratedWords[0]);
									document
											.getElementById("kalias5").value = result.transliterations[0].transliteratedWords[0];
								}
							}
						});
	}
	function convertcodeAlias6() {
		//google.load("language", "1");
		//	alert("hiiii");
		//var a='["'+document.getElementById("busStopName").value+'"]';

		
		google.language
				.transliterate(
						[ document.getElementById("alias6").value ],
						"en",
						"kn",
						function(result) {
							if (!result.error) {
								//var container = document.getElementById("transliteration");
								if (result.transliterations
										&& result.transliterations.length > 0
										&& result.transliterations[0].transliteratedWords.length > 0) {
									//alert(result.transliterations[0].transliteratedWords[0]);
									document
											.getElementById("kalias6").value = result.transliterations[0].transliteratedWords[0];
								}
							}
						});
	}
	function onLoad() {
		$("#busStopName").val("");
		$("#busStopNameKannada").val("");
		$("#bus_stop_code").val("");
		$("#bus_stop_code_kannada").val("");

		var options = {
			sourceLanguage : 'en', // or google.elements.transliteration.LanguageCode.ENGLISH,
			destinationLanguage : [ 'kn' ], // or [google.elements.transliteration.LanguageCode.HINDI],
			shortcutKey : 'ctrl+g',
			transliterationEnabled : true
		};
		// Create an instance on TransliterationControl with the required
		// options.
		var control = new google.elements.transliteration.TransliterationControl(
				options);

		// Enable transliteration in the textfields with the given ids.
		//var ids = ["transl1", "transl2" ];
		var ids = [ "busStopNameKannada" ];
		control.makeTransliteratable(ids);

		var ids = [ "bus_stop_code_kannada" ];
		control.makeTransliteratable(ids);

		var ids = [ "kalias1" ];
		control.makeTransliteratable(ids);

		var ids = [ "kalias2" ];
		control.makeTransliteratable(ids);
		
		var ids = [ "kalias3" ];
		control.makeTransliteratable(ids);

		var ids = [ "kalias4" ];
		control.makeTransliteratable(ids);
		var ids = [ "kalias5" ];
		control.makeTransliteratable(ids);

		var ids = [ "kalias6" ];
		control.makeTransliteratable(ids);

		document.getElementById("errmsg").innerHTML = "";

		// Show the transliteration control which can be used to toggle between
		// English and Hindi.
		//control.showControl('translControl');
	}
	google.setOnLoadCallback(onLoad);
</script>
<!-- //form submit using ajax -->
<script type="text/javascript">
	document.getElementById('sub_stage').innerHTML = "";
	document.getElementById("sub_stage").value = "";
	var select1 = document.getElementById("sub_stage");
	if ($("#fareStage").val() == "Y") {
		select1.options[select1.options.length] = new Option('N', 'N');
	}
	if ($("#fareStage").val() == "N") {
		select1.options[select1.options.length] = new Option('N', 'N');
		select1.options[select1.options.length] = new Option('Y', 'Y');

	}

	$('#fareStage').on("change", function() {
		document.getElementById('sub_stage').innerHTML = "";
		document.getElementById("sub_stage").value = "";
		var val = $("#fareStage").val();
		var select1 = document.getElementById("sub_stage");
		//alert(val);
		if (val == "Y") {
			select1.options[select1.options.length] = new Option('N', 'N');
		}
		if (val == "N") {
			select1.options[select1.options.length] = new Option('N', 'N');
			select1.options[select1.options.length] = new Option('Y', 'Y');

		}
	});

	$('#tollZone').on("change", function() {
		//alert("hiiii");
		var valtoll = "";
		valtoll = $('#tollZone option:selected').text();
		// alert(valtoll);
		if (valtoll == 'Y') {
			//alert("hiiii");
			document.busstopmapform.toll_fee.readOnly = false;
			document.getElementById("toll_fee").value = "0.00";
		} else {
			document.busstopmapform.toll_fee.readOnly = true;
			document.getElementById("toll_fee").value = "0.00";
		}
	});

	function submitAjaxForm() {
		//alert("hiiiii");
		document.getElementById("errmsg").innerHTML = "";
		if ($("#busStopName").val().trim() == "") {
			document.getElementById("errmsg").innerHTML = "Bus Stop Name is blank";
			//alert("1");
			return false;
		}
		if ($("#bus_stop_code").val().trim() == "") {
			document.getElementById("errmsg").innerHTML = "Bus Stop Code is blank";
			//alert("2");
			return false;
		}
		var orgchartids;
		if ($("#org_chart_id").val() == undefined
				|| $("#org_chart_id").val() == "") {
			orgchartids = -1;
		} else {
			orgchartids = $("#org_chart_id").val();
		}
		//alert(orgchartids);
		$.ajax({
			type : 'POST',
			data : 'requestType=' + $("#requestType").val()
					+ '&bustops.busStopName=' + $("#busStopName").val()
					+ '&bustops.bus_stop_name_nudi='
					+ $("#busStopNames").val()
					+ '&bustops.busStopNameKannada='
					+ $("#busStopNameKannada").val()
					+ '&bustops.bus_stop_code='
					+ $("#bus_stop_code").val().trim()
					+ '&bustops.bus_stop_code_kannada='
					+ $("#bus_stop_code_kannada").val() + '&bustops.locality='
					+ $("#locality").val() + '&bustops.landmark='
					+ $("#landmark").val() + '&bustops.latitude='
					+ $("#latitude").val() + '&bustops.longitude='
					+ $("#longitude").val() + '&bustops.sheltered='
					+ $("#sheltered").val() +

					'&bustops.alias1=' + $("#alias1").val()
					+ '&bustops.alias2=' + $("#alias2").val()
					+ '&bustops.alias3=' + $("#alias3").val()
					+ '&bustops.alias4=' + $("#alias4").val()
					+ '&bustops.tollZone='
					+ $("#tollZone option:selected").val() +

					'&bustops.cityLimit=' + $("#cityLimit").val()
					+ '&bustops.wardNumber=' + $("#wardNumber").val()
					+ '&bustops.areaPopulation=' + $("#areaPopulation").val()
					+ '&bustops.stopSize=' + $("#stopSize").val() +

					'&bustops.fareStage=' + $("#fareStage").val()
					+ '&bustops.description=' + $("#description").val() +

					'&bustops.kalias1=' + $("#kalias1").val()
					+ '&bustops.k_alias_2=' + $("#kalias2").val() +
					'&bustops.k_alias_3=' + $("#kalias3").val() +
					'&bustops.k_alias_4=' + $("#kalias4").val() +
					'&bustops.k_alias_5=' + $("#kalias5").val() +
					'&bustops.k_alias_6=' + $("#kalias6").val() +
					//'&bustops.busid1='+  $("#busid1").val()+

					'&bustops.status=' + $("#status").val()
					+ '&bustops.alias5=' + $("#alias5").val()
					+ '&bustops.stop_direction=' + $("#stop_direction").val()
					+ '&bustops.alias6=' + $("#alias6").val()
					+ '&bustops.pointtype.point_type_id='
					+ $("#point_type_id").val()
					+ '&bustops.bus_stations.org_chart_id=' + orgchartids
					+ '&bustops.sub_stage=' + $("#sub_stage").val()
					+ '&bustops.toll_fee=' + parseFloat($("#toll_fee").val()),

			url : "<s:url action='AddStop'/>",

			success : function(data) {
				// alert(data);
				document.getElementById("errmsg").innerHTML = data;
				if (data == "Saved Successfully") {
					alert(data);
					location.reload(true);
				}

			},
			error : function(xhr, errmsg) {
				document.getElementById("errmsg").innerHTML = errmsg;
			}
		});
	}

	$('#point_type_id').on("change", function() {
		//alert("hii");
		getOrgList($('#point_type_id').val());
		/* /* if($('#point_type_id').val()=='6'){ */
		/* document.getElementById( 'org_chart_id_div' ).style.display = 'block';
		}else{
		document.getElementById( 'org_chart_id_div' ).style.display = 'none';
		} */
	});

	function getOrgList(id) {
		$.ajax({
			type : 'POST',
			data : {
				id : id,
				method : '1',
			},
			url : "<s:url action='GetOrgList'/>",

			success : function(data) {
				//alert(data);
				$('#org_chart_id_select').html("");
				if (data != "") {
					$('#org_chart_id_div').show();
					$('#org_chart_id_select').html(data);
					var selid = [ "org_chart_id" ];

					//alert(busstaselvalid);
					var seval = [ busstaselvalid ];

					// setTimeout(function(){makeSelectedByid(selid,seval);}, 3000);
				} else {
					$('#org_chart_id_div').hide();
				}
			},
			error : function(xhr, errmsg) {
			}
		});

	}
	function checkDecimal(el) {
		//alert(el.value);
		var v = parseFloat(el.value);
		if (isNaN(v)) {
			el.value = '0.00';
		} else {
			el.value = v.toFixed(2);
		}

	}

	function spclchar(el) {
		var iChars = "!@$%^&*+=[]\\\’;,./{}|\":?~_";
		for ( var i = 0; i < el.value.length; i++) {
			if (iChars.indexOf(el.value.charAt(i)) != -1) {

				el.value = '';
				alert("Special Characters not allowed");
				return false;
			}
		}
		return true;
	}
</script>


