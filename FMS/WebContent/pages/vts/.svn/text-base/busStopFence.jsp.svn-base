<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<head>
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Expires" content="-1">
<sx:head />
</head>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css"/> -->
<!-- <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css"> -->
<!-- <link rel="stylesheet" href="/resources/demos/style.css"> -->
<%-- <script src="assets/global/plugins/jquery-1.11.0.min.js"type="text/javascript"></script> --%>

  <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css"> 
<%-- <script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script> --%>
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="assets/global/plugins/jquery-1.11.0.min.js"
	type="text/javascript"></script>

<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places,drawing"></script>

<script src="assets/admin/pages/scripts/busStopFence.js" type="text/javascript" varsion="2014061"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>



<script type="text/javascript">
	// Load the Google Transliteration API
	google.load("elements", "1", {
		packages : "transliteration"
	});
	google.load("language", "1");
	
	function onLoad() {
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

		// Show the transliteration control which can be used to toggle between
		// English and Hindi.
		//control.showControl('translControl');
	}
	
	function convert(){
		//google.load("language", "1");
	//	alert("hiiii");
		//var a='["'+document.getElementById("busStopName").value+'"]';
		
//alert(a);
		google.language.transliterate([document.getElementById("busStopName").value], "en", "kn", function(result) {
		if (!result.error) {
		//var container = document.getElementById("transliteration");
		if (result.transliterations && result.transliterations.length > 0 &&
		result.transliterations[0].transliteratedWords.length > 0) {
			//alert(result.transliterations[0].transliteratedWords[0]);
			document.getElementById("busStopNameKannada").value = result.transliterations[0].transliteratedWords[0];
		}
		}
		});
	}
	
	function convertcode(){
		//google.load("language", "1");
	//	alert("hiiii");
		//var a='["'+document.getElementById("busStopName").value+'"]';
		
//alert(a);
		google.language.transliterate([document.getElementById("bus_stop_code").value], "en", "kn", function(result) {
		if (!result.error) {
		//var container = document.getElementById("transliteration");
		if (result.transliterations && result.transliterations.length > 0 &&
		result.transliterations[0].transliteratedWords.length > 0) {
			//alert(result.transliterations[0].transliteratedWords[0]);
			document.getElementById("bus_stop_code_kannada").value = result.transliterations[0].transliteratedWords[0];
		}
		}
		});
	}
	google.setOnLoadCallback(onLoad);
</script>
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "SaveBusStopMap.action");
String access=accessrights.getACCESS();
%>
<div class="page-content-wrapper">
	<div class="page-content">
	<%if (access.equalsIgnoreCase("Y")){%>
		<div class="form-group">
		<div class="row">
		<div class="col-md-6">
		
		<!-- <label class="col-md-3 control-label">Bus Stop Name</label> -->
				<input class="form-control input-lg" placeholder="Enter Bus Stop Name to Search" id="project" name="project" type="text" onkeyup="getDropStops(this.value)" onblur="locateMap()"  onKeyPress="enterpressalert(event, this)"/> 
				
				<input id="project-id" type="hidden">
				 <input id="project-id1" type="hidden">
				 <input id="project-id2" type="hidden">
			</div>
			<div class="col-md-6">
			<label class="col-md-3 control-label">Stop Type </label>
									<div class="col-md-5">
										<select class="form-control input-sm" id="stop_type"
											name="stop_type">
											<option value="0">All</option>
											<s:iterator value="listPoint" id="listPoint">
											<option value="<s:property value="point_type_id"/>"><s:property value="point_type_name"/></option>
											</s:iterator>
											
										</select>
									</div>
		<!-- <div class="portlet box yellow">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-cogs"></i>Color Marker Details
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse">
								</a>
								<a href="#portlet-config" data-toggle="modal" class="config">
								</a>
								<a href="javascript:;" class="reload">
								</a>
								<a href="javascript:;" class="remove">
								</a>
							</div>
						</div>
						<div style="display: block;" class="portlet-body">
							<p>
							<img src="assets/images/green.png" class="img-circle" alt="">Approved
							<img src="assets/images/red.png" class="img-circle" alt="">New
							<img src="assets/images/yellow.png" class="img-circle" alt="">Inactive
							<img src="assets/images/brown.png" class="img-circle" alt="">Connector Bus stop 
							<img src="assets/images/blue.png" class="img-circle" alt="">Toll bus stop	
							</p>
							
						</div>
					</div> -->
			</div>
			
					</div>
			
			

		</div>
		<div class="portlet solid white" style="min-height: 550px">
			<!-- <div class="portlet-title">
				<div class="caption">
								<i class="fa fa-gift"></i>Bus Stops
							</div>
				
			</div> -->
			<div class="portlet-body" style="min-height: 550px">
				<div id="gmap_marker" class="gmaps" style="min-height: 550px">
				</div>
			</div>
		</div>
		
		
		
		<div class="form-group">
		<div class="row">
		<div class="col-md-3">
		
		<div class="portlet box yellow">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-cogs"></i>Color Marker Details
							</div>
							<div class="tools">
								<a href="javascript:;" class="expand">
								</a>
								<!-- <a href="#portlet-config" data-toggle="modal" class="config">
								</a>
								<a href="javascript:;" class="reload">
								</a>
								<a href="javascript:;" class="remove">
								</a> -->
							</div>
						</div>
						<div style="display: none;" class="portlet-body">
							<p>
							<img src="assets/images/green.png" class="img-circle" alt="">::   Approved<br/>
							<img src="assets/images/red.png" class="img-circle" alt="">::   New<br/>
							<img src="assets/images/yellow.png" class="img-circle" alt="">::   Inactive<br/>
							<img src="assets/images/brown.png" class="img-circle" alt="">::   Connector Bus stop<br/> 
							<img src="assets/images/blue.png" class="img-circle" alt="">::   Toll bus stop<br/>
							<img src="assets/images/chart.png" class="img-circle" alt="">::   Charted bus stop<br/> 
							<img src="assets/images/bmtc.png" class="img-circle" alt="">::   BMTC bus stop<br/> 
							<img src="assets/images/grey.png" class="img-circle" alt="">::   Dummy bus stop	
							</p>
							
						</div>
					</div>
			</div>
			<div class="col-md-6">
		
		<!-- <div class="portlet box yellow">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-cogs"></i>Color Marker Details
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse">
								</a>
								<a href="#portlet-config" data-toggle="modal" class="config">
								</a>
								<a href="javascript:;" class="reload">
								</a>
								<a href="javascript:;" class="remove">
								</a>
							</div>
						</div>
						<div style="display: block;" class="portlet-body">
							<p>
							<img src="assets/images/green.png" class="img-circle" alt="">Approved
							<img src="assets/images/red.png" class="img-circle" alt="">New
							<img src="assets/images/yellow.png" class="img-circle" alt="">Inactive
							<img src="assets/images/brown.png" class="img-circle" alt="">Connector Bus stop 
							<img src="assets/images/blue.png" class="img-circle" alt="">Toll bus stop	
							</p>
							
						</div>
					</div> -->
			</div>
			
					</div>
			
			

		</div>

		
	</div>
</div>



<!-- Modal -->
<div style="display: none;" id="myModal1" class="modal fade"
	tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
	aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">BUS STOPS</h4>
			</div>
			<div class="modal-body">
				<p>
				<div class="portlet box purple ">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i> EDIT BUS STOPS
						</div>
						
					</div>
					<div class="alert alert-danger" >
					<!-- <button class="close" data-close="alert"></button> -->
					<span id="errmsg"> </span>
			
		</div>
					<div class="portlet-body form">
						<form class="form-horizontal" role="form"
							action="AddEditedBusStopMap.action" method="post" name="busstopmapform">
							<input type="hidden" name="bustops.id" id="busid1" value="" /> <input
								type="hidden" name="requestType" id="requestType" value="map" />
							<div class="form-body">
								<div class="form-group">
								<div class="row">
								<div class="col-md-3">
								<label class="col-md-10 control-label">Bus Stop Name *</label>
								</div>
								<div class="col-md-6">
									<!-- 
									<div class="col-md-9"> -->
										<input class="form-control input-lg" placeholder=""
											id="busStopName" name="bustops.busStopName" type="text"
											maxlength="80" onblur="spclchar(this);">
									<!-- </div> -->
									</div>
									<div class="col-md-3">
									
									<button class="btn purple" type="button" onclick="convert()">Convert</button>
								</div>
								</div>
								</div>
								
								
								<div class="form-group">
									<label class="col-md-3 control-label">Bus Stop Name
										(Kannada) </label>
									<div class="col-md-9">
										<input class="form-control input-lg" placeholder=""
											id="busStopNameKannada" name="bustops.busStopNameKannada"
											type="text" maxlength="80" />
									</div>
								</div>
								
								<div class="form-group">
								<div class="row">
								<div class="col-md-3">
								<label class="col-md-10 control-label">Bus Stop Code *</label>
								</div>
								<div class="col-md-6">
									<!-- 
									<div class="col-md-9"> -->
										<input class="form-control input-lg" placeholder=""
											id="bus_stop_code" name="bustops.bus_stop_code" type="text"
											maxlength="30" onblur="spclchar(this);">
									<!-- </div> -->
									</div>
									<div class="col-md-3">
									
									<button class="btn purple" type="button" onclick="convertcode()">Convert</button>
								</div>
								</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-3 control-label">Bus Stop
										Code(Kannada) </label>
									<div class="col-md-9">
										<input class="form-control input-lg" placeholder=""
											id="bus_stop_code_kannada" name="bustops.bus_stop_code_kannada"
											type="text" maxlength="30"/>
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
										<input class="hidden"
											placeholder="" type="text" id="latitude"
											name="bustops.latitude">
									</div>
								</div>

								<div class="form-group">
									<!-- <label class="col-md-3 control-label">Longitud(Currrent)</label> --><!--  -->
									<div class="col-md-9">
										<input class="hidden"
											placeholder="" type="text" id="longitude"
											name="bustops.longitude">
									</div>
								</div>
								
								
								
								<div class="form-group">
									<label class="col-md-3 control-label">Locality</label>
									<div class="col-md-9">
										<input class="form-control input-sm" placeholder=""
											type="text" id="locality" name="bustops.locality" maxlength="180" onblur="spclchar(this);">
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
										<input class="form-control input-sm"
											placeholder="" type="text" id="landmark"
											name="bustops.landmark" maxlength="40" onblur="spclchar(this);">
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
										
											<div class="col-md-3">
												<label class="col-md-10 control-label">Alias1 </label>
											</div>
											<div class="col-md-4">
												<!-- 
									<div class="col-md-9"> -->
												<input class="form-control input-sm" placeholder=""
													type="text" id="alias1" name="bustops.alias1" value='<s:property value="bustops.alias1"/>'
													maxlength="50" onblur="spclchar(this);">
												<!-- </div> -->
											</div>
											<div class="col-md-3">

												<button class="btn purple" type="button"
													onclick="convertcodeAlias1()">Convert</button>
											</div>
										
									</div>
								
								<div class="form-group">
									<label class="col-md-3 control-label">Kannada Alias 1</label>
									<div class="col-md-4">
										<input class="form-control input-sm" type="text" id="kalias1"
											name="bustops.kalias1"
											value='<s:property value="bustops.kalias11"/>'>
									</div>
								</div>
								<div class="form-group">
										<div class="row">
											<div class="col-md-3">
												<label class="col-md-10 control-label">Alias2 </label>
											</div>
											<div class="col-md-4">
												<!-- 
									<div class="col-md-9"> -->
												<input class="form-control input-sm" placeholder=""
													type="text" id="alias2" name="bustops.alias2" value='<s:property value="bustops.alias2"/>'
													maxlength="80" onblur="spclchar(this);">
												<!-- </div> -->
											</div>
											<div class="col-md-3">

												<button class="btn purple" type="button"
													onclick="convertcodeAlias2()">Convert</button>
											</div>
										</div>
									</div>
								
								<div class="form-group">
										<label class="col-md-3 control-label">Kannada Alias 2</label>
										<div class="col-md-4">
											<input class="form-control input-sm" type="text" id="kalias2"
												name="bustops.k_alias_2"
												value='<s:property value="bustops.k_alias_21"/>'>
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-md-3">
												<label class="col-md-10 control-label">Alias3 </label>
											</div>
											<div class="col-md-4">
												<!-- 
									<div class="col-md-9"> -->
												<input class="form-control input-sm" placeholder=""
													type="text" id="alias3" name="bustops.alias3" value='<s:property value="bustops.alias3"/>'
													maxlength="80" onblur="spclchar(this);">
												<!-- </div> -->
											</div>
											<div class="col-md-3">

												<button class="btn purple" type="button"
													onclick="convertcodeAlias3()">Convert</button>
											</div>
										</div>
									</div>
								
								<div class="form-group">
										<label class="col-md-3 control-label">Kannada Alias 3</label>
										<div class="col-md-4">
											<input class="form-control input-sm" type="text" id="kalias3"
												name="bustops.k_alias_3"
												value='<s:property value="bustops.k_alias_3"/>'>
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-md-3">
												<label class="col-md-10 control-label">Alias4 </label>
											</div>
											<div class="col-md-4">
												<!-- 
									<div class="col-md-9"> -->
												<input class="form-control input-sm" placeholder=""
													type="text" id="alias4" name="bustops.alias4" value='<s:property value="bustops.alias4"/>'
													maxlength="80" onblur="spclchar(this);">
												<!-- </div> -->
											</div>
											<div class="col-md-3">

												<button class="btn purple" type="button"
													onclick="convertcodeAlias4()">Convert</button>
											</div>
										</div>
									</div>
								
								<div class="form-group">
										<label class="col-md-3 control-label">Kannada Alias 4</label>
										<div class="col-md-4">
											<input class="form-control input-sm" type="text" id="kalias4"
												name="bustops.k_alias_4"
												value='<s:property value="bustops.k_alias_4"/>'>
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-md-3">
												<label class="col-md-10 control-label">Alias5 </label>
											</div>
											<div class="col-md-4">
												<!-- 
									<div class="col-md-9"> -->
												<input class="form-control input-sm" placeholder=""
													type="text" id="alias5" name="bustops.alias5" value='<s:property value="bustops.alias5"/>'
													maxlength="80" onblur="spclchar(this);">
												<!-- </div> -->
											</div>
											<div class="col-md-3">

												<button class="btn purple" type="button"
													onclick="convertcodeAlias5()">Convert</button>
											</div>
										</div>
									</div>
									<%-- <div class="form-group">
										<label class="col-md-3 control-label">Alias 5</label>
										<div class="col-md-4">
											<input type="text" class="form-control" name="bustops.alias5"
												value='<s:property value="bustops.alias5"/>'>

										</div>
									</div> --%>
									<div class="form-group">
										<label class="col-md-3 control-label">Kannada Alias 5</label>
										<div class="col-md-4">
											<input class="form-control input-sm" type="text" id="kalias5"
												name="bustops.k_alias_5"
												value='<s:property value="bustops.k_alias_5"/>'>
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-md-3">
												<label class="col-md-10 control-label">Alias6 </label>
											</div>
											<div class="col-md-4">
												<!-- 
									<div class="col-md-9"> -->
												<input class="form-control input-sm" placeholder=""
													type="text" id="alias6" name="bustops.alias6" value='<s:property value="bustops.alias6"/>'
													maxlength="80" onblur="spclchar(this);">
												<!-- </div> -->
											</div>
											<div class="col-md-3">

												<button class="btn purple" type="button"
													onclick="convertcodeAlias6()">Convert</button>
											</div>
										</div>
									</div>
									<%-- <div class="form-group">
										<label class="col-md-3 control-label">Alias 6</label>
										<div class="col-md-4">
											<input type="text" class="form-control" name="bustops.alias6"
												value='<s:property value="bustops.alias6"/>'>

										</div>
									</div> --%>
									<div class="form-group">
										<label class="col-md-3 control-label">Kannada Alias 6</label>
										<div class="col-md-4">
											<input class="form-control input-sm" type="text" id="kalias6"
												name="bustops.k_alias_6"
												value='<s:property value="bustops.k_alias_6"/>'>
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

								<div class="form-group" id="tollZone">
									<label class="col-md-3 control-label">Toll Zone</label>
									<div class="col-md-9">
										<select class="form-control input-sm" id="tollZone"
											name="bustops.tollZone">
											<option value="N">N</option>
											<option value="Y">Y</option>
											
										</select>
										<input  type="hidden" id="tollZone_old"	name="tollZone_old" vallue="">
									</div>
								</div>
								
								<div class="form-group" id="toll_fee_div">
									<label class="col-md-3 control-label">Toll Fee</label>
									<div class="col-md-9">
										<input class="form-control input-sm"
											placeholder="" type="text" id="toll_fee"
											name="bustops.toll_fee" maxlength="6" onblur="checkDecimal(this);" >
											<input  type="hidden" id="toll_fee_old"	name="toll_fee_old" vallue="">
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



								<div class="form-group">
									<label class="col-md-3 control-label">City Limit</label>
									<div class="col-md-9">
										<!-- <input class="form-control input-sm"
											placeholder="" type="text" id="cityLimit"
											name="bustops.cityLimit" maxlength="5"> -->
											<select class="form-control input-sm" id="cityLimit"
											name="bustops.cityLimit">
											<option value="N">N</option>
											<option value="Y">Y</option>
											
										</select>
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-3 control-label">Ward No</label>
									<div class="col-md-9">
										<input class="form-control input-sm"
											placeholder="" type="text" id="wardNumber"
											name="bustops.wardNumber" maxlength="10" onblur="spclchar(this);">
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-3 control-label">Area Population</label>
									<div class="col-md-9">
										<input class="form-control input-sm"
											placeholder="" type="text" id="areaPopulation"
											name="bustops.areaPopulation" maxlength="20" onblur="spclchar(this);">
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-3 control-label">Towards</label>
									<div class="col-md-9">
										<input class="form-control input-sm"
											placeholder="" type="text" id="stop_direction"
											name="bustops.stop_direction" maxlength="70" onblur="spclchar(this);">
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-3 control-label">Stop Size</label>
									<div class="col-md-9">
										<select class="form-control input-sm" id="stopSize"
											name="bustops.stopSize">
											<option value="SMALL">SMALL</option>
											<option value="MEDIUM">MEDIUM</option>
											<option value="LARGE">LARGE</option>
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
										<input class="form-control input-sm"
											placeholder="" type="text" id="description"
											name="bustops.description" maxlength="80" onblur="spclchar(this);">
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
											name="bustops.pointtype.point_type_id">
											<s:iterator value="listPoint" id="listPoint">
											<option value="<s:property value="point_type_id"/>"><s:property value="point_type_name"/></option>
											</s:iterator>
											
										</select>
									</div>
								</div>
								
								
								
								<div class="form-group" style="display: none;" id="org_chart_id_div">
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
											
											<!-- <option value="New">New</option>
											<option value="Inactive">Inactive</option>
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
							<button type="button" class="btn green" onclick="submitAjaxForm()">Save Changes</button>
								<button aria-hidden="true" data-dismiss="modal" class="btn default">Close</button>
								
							</div>
						</form>
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
<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>

<div id="myModal3" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel3" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
											<h4 class="modal-title">Delete Bus Stop</h4>
										</div>
										<div class="modal-body">
											<p>
												Are you sure you want to delete this Bus Stop?
											</p>
										</div>
										<div class="modal-footer">
											<button class="btn default" data-dismiss="modal" aria-hidden="true">NO</button>
											<button data-dismiss="modal" class="btn blue" id="clickMe">Yes</button>
										    </div>
									</div>
								</div>
							</div>



<div style="display: none;" id="routeInfo" class="modal fade"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
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
										<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Schedule Trip Deviation Detail
							</div>
							<!-- <div class="tools">
								<a href="javascript:;" class="collapse"> </a> 
								 <a href="javascript:;" class="reload"> </a> 
							</div> -->
						</div>
				
									<div class="portlet-body" id="withoutbusstop">
										

										<table class="table table-striped table-bordered table-hover"
											id="DeviatiedTrip">
											<thead>
												<tr>
													<th>#</th>

													<th>Deviated Distance</th>

													<th>Route Exit Location</th>

													<th>Route Entry Location</th>
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
		</div>
	</div>
</div>

<script>
$(function(){
	
});
var idval="'0'";
//alert(idval);
document.getElementById("clickMe").onclick = function () { 
	//document.deleteform.submit(); 
	$.ajax({
	    type        : 'POST',
	    url         :  "<s:url action='DeleteBusStop'/>",
	    data: {
	    	busid: $('#busid').val(),
	        requestType : 'map',
	        
	    },
	   
	    success: function(data){
	       // alert('successful'+data);
	        result=data;
	        alert(result);
	        if(result=="Busstop deleted Successfully"){
	        	location.reload(true);
	        }
	       
	    },
	    error : function(xhr, errmsg) {
	    	//alert("No values found..!!"+errmsg);
	    	}
	});
	};

var dynamicid="";
function locateMap(){
	var lag=document.getElementById("project-id").value;
	var lat=document.getElementById("project-id1").value;
	var id=document.getElementById("project-id2").value;
	dynamicid=id;
	var stop_type=$("#stop_type").val();
	var zoom=30;
	//alert(id);
	if(id==""){
		//alert("Invalid Stop");
		return false;
	}
	idval.indexOf(id);
	//alert(idval.indexOf(id));
	if(idval.indexOf(id)!=-1){
		//alert("hiiii"+lag+lat);
		var loc=lag+","+lat;
		map.setCenter(new google.maps.LatLng(lat,lag));
		map.setZoom(25);
		//initialize("",loc,zoom);
	}else{
	$.ajax({
	    type        : 'GET',
	    url         :  "<s:url action='GetBusStopMapLocate'/>",
	    data: {
	        id: id,
	        stop_type: stop_type,
	    },
	   
	    success: function(data){
	       // alert('successful'+data);
	        result=data;
	        var loc='<%= request.getSession().getAttribute("centerlat") %>'+","+'<%= request.getSession().getAttribute("centerlang") %>';
	     
	       // alert(dynamicid);
	       // alert(idval);
	      //idval.push(id);
	      idval=idval+",'"+dynamicid+"'";
	     // alert(idval);
	      //idval=idval+",'"+id+"'";
	      var names=idval.split(",");
	      var uniqueNames= [];
	  	$.each(names, function(i, el){
	  	    if($.inArray(el, uniqueNames) === -1) uniqueNames.push(el);
	  	});
	  	idval=uniqueNames.join();
	        initialize(result,loc,zoom);
	    },
	    error : function(xhr, errmsg) {
	    	//alert("No values found..!!"+errmsg);
	    	}
	});
	}
	document.getElementById("project-id").value="";
	lat=document.getElementById("project-id1").value="";
	id=document.getElementById("project-id2").value="";
}
var busstaselvalid="";
function getStopsDetails(id,latnew,langnew){
	//alert("getStopsDetails"+id);
	
	$.ajax({
	    type        : 'GET',
	    url         :  "<s:url action='GetBusStopDetail'/>",
	    data: {
	        id: id,
	        
	    },
	    //data: $('#target').serialize(),
	    //dataType    : 'json',
	    success: function(data){
	       // alert('successful'+data);
	        data=data.split("|");
	       /*  if(data[31]==="null "){
	        //alert("pre"+data[31]);
	    } */
	        //alert("pre"+data[31].length);
	       // alert('id value'+data[28]);
	       for(var i=0;i<data.length;i++){
	    	   if(data[i]==="null" || data[i]=="null"){
		        	//alert("hiiii");
		        	data[i]=" ";
		        }
	    	   if(data[i]==="undefined" || data[i]=="undefined"){
		        	//alert("hiiii");
		        	data[i]=" ";
		        }
	    	   if(data[i]==""){
		        	//alert("hiiii");
		        	data[i]=" ";
		        }
	    	   if(!data[i]){
		        	//alert("hiiii");
		        	data[i]=" ";
		        }
	       }
	       //alert(data[31]);
	        var select = document.getElementById("status");
	        document.getElementById('status').innerHTML = "";
	        document.getElementById("status").value="";
	        if(data[29].trim()=="Manual"){
	        	select.options[select.options.length] = new Option('Manual', 'Manual');
	        } else{
				select.options[select.options.length] = new Option('New', 'New');
				select.options[select.options.length] = new Option('Inactive', 'Inactive');
				select.options[select.options.length] = new Option('Approved', 'Approved');
	        }
	        document.getElementById("busStopName").value=data[0].trim();
	        document.getElementById("busStopNameKannada").value= $('<div/>').html(data[1].trim()).text();
	        document.getElementById("landmark").value=data[2].trim();
	        document.getElementById("latitude").value=latnew;
	        document.getElementById("longitude").value=langnew;
	        document.getElementById("sheltered").value=data[5].trim();
	      //makeSelected(sheltered,data[5].trim();
	        //document.getElementById("speed").value=data[6].trim();
	       //document.getElementById("accuracy").value=data[7].trim();
	       //document.getElementById("altitude").value=data[8].trim();
	       // document.getElementById("bearing").value=data[9].trim();
	      //  document.getElementById("deviceCode").value=data[10].trim();
	        document.getElementById("locality").value=data[11].trim();
	        document.getElementById("alias1").value=data[12].trim();
	        document.getElementById("alias2").value=data[13].trim();
	        document.getElementById("alias3").value=data[14].trim();
	        document.getElementById("alias4").value=data[15].trim();
	        document.getElementById("tollZone").value=data[16].trim();
	        document.getElementById("tollZone_old").value=data[16].trim();
	        //makeSelected(tollZone,data[16].trim();
	       /*  document.getElementById("code1").value=data[17].trim();
	        document.getElementById("code2").value=data[18].trim(); */
	        document.getElementById("cityLimit").value=data[19].trim();
	        document.getElementById("wardNumber").value=data[20].trim();
	        document.getElementById("areaPopulation").value=data[21].trim();
	        document.getElementById("stopSize").value=data[22].trim();
	        //alert(data[30].trim();
	         
	        document.getElementById("fareStage").value=data[23].trim(); //alert("id1 issss"+data[24].trim());
	        
	        var select1 = document.getElementById("sub_stage");
	        document.getElementById('sub_stage').innerHTML = "";
	        document.getElementById("sub_stage").value="";
	        if(data[23].trim()=="Y"){
	        	select1.options[select1.options.length] = new Option('N', 'N');
	        }
	        if(data[23].trim()=="N"){
	        	select1.options[select1.options.length] = new Option('N', 'N');
	        	select1.options[select1.options.length] = new Option('Y', 'Y');
	        	
	        }
	        document.getElementById("description").value=data[24].trim();// alert("id2 issss"+data[25].trim());
	        
	        
	        //makeSelected(bmtcStatus,data[25].trim();
	       
	        document.getElementById("kalias1").value=$('<div/>').html(data[26].trim()).text();//data[26].trim();
	        document.getElementById("kalias2").value=$('<div/>').html(data[27].trim()).text();//data[27].trim();
	        document.getElementById("kalias3").value=$('<div/>').html(data[40].trim()).text();//data[26].trim();
	        document.getElementById("kalias4").value=$('<div/>').html(data[41].trim()).text();//data[27].trim();
	        document.getElementById("kalias5").value=$('<div/>').html(data[42].trim()).text();//data[26].trim();
	        document.getElementById("kalias6").value=$('<div/>').html(data[43].trim()).text();//data[27].trim();
	       // alert("id2 issss"+data[28]);
	        document.getElementById("busid1").value=data[28].trim();
	       
	        document.getElementById("status").value=data[29].trim();
	        document.getElementById("stop_direction").value=data[30].trim();
	        document.getElementById("alias5").value=data[31].trim();
	        document.getElementById("alias6").value=data[32].trim();
	        document.getElementById("bus_stop_code").value=data[33].trim();
	        document.getElementById("bus_stop_code_kannada").value=$('<div/>').html(data[34].trim()).text();
	        document.getElementById("point_type_id").value=data[35];
	        busstaselvalid=data[36];
	        //document.getElementById("org_chart_id").value=data[36];
	        /* if(data[35]=='6'){
	    		document.getElementById( 'org_chart_id_div' ).style.display = 'block';
	    	}else{
	    		document.getElementById( 'org_chart_id_div' ).style.display = 'none';
	    	} */
	        
	        document.getElementById("errmsg").innerHTML="";
	        
	        
	        //makeSelected(stopSize,data[22]);
	        //alert(data[35]);
	        getOrgList(data[35]);
	        var selid=["sheltered","stopSize","fareStage","status","point_type_id","cityLimit","sub_stage"];
	        var seval=[data[5],data[22],data[23],data[29],data[35],data[19],data[37]];
	       // alert(data[16].trim());
	        if(data[16].trim()=='Y'){
	        	document.busstopmapform.toll_fee.readOnly=false;
	        	document.getElementById("toll_fee").value=data[39];	
	        	document.getElementById("toll_fee_old").value=data[39];	
	        }else{
	        	document.busstopmapform.toll_fee.readOnly=true;
	        	document.getElementById("toll_fee").value="0.00";	
	        	document.getElementById("toll_fee_old").value="0.00";
	        }
	        makeSelected(selid,seval);
	        //alert(data[16]);
	        $("#tollZone option[value="+data[16]+"]").attr("selected", "selected");
	        /* var selid=["tollZone"];
	        var seval=[data[16]];
	        makeSelectedByid(selid,seval); */
	        
	        
	        //makeSelected(fareStage,data[23]);
	        
	       
	       
	        
	        
	       
	        
	       
	        
	        	convert();
	      
	    },
	    error : function(xhr, errmsg) {
	    	//alert("No values found.aaaaa.!!"+errmsg);
	    	}
	});
}



function getOrgList(id){	
	$.ajax({
	    type        : 'POST',
	    data: {
	        id: id,
	        method:'1',
	    },
	    url         :  "<s:url action='GetOrgList'/>",
	   
	    success: function(data){
	    	//alert(data);
	    	$('#org_chart_id_select').html("");
	    	if(data!=""){
	    		$('#org_chart_id_div').show();
	    		$('#org_chart_id_select').html(data);
	    		 var selid=["org_chart_id"];
	    		
	    		
	    		//alert(busstaselvalid);
	 	        var seval=[busstaselvalid];
	 	       
	 	      setTimeout(function(){makeSelectedByid(selid,seval);}, 3000);
	    	}else{
	    		$('#org_chart_id_div').hide();
	    	}
	    	},
	    error : function(xhr, errmsg) {
	    	}
	});

	
}


$('#tollZone').on("change",function() {
	//alert("hiiii");
    var valtoll="";
    valtoll=$('#tollZone option:selected').text();
   // alert(valtoll);
    if(valtoll=='Y'){
    	//alert("hiiii");
    	document.busstopmapform.toll_fee.readOnly=false;
    	document.getElementById("toll_fee").value="0.00";
    }else{
    	document.busstopmapform.toll_fee.readOnly=true; 
    	document.getElementById("toll_fee").value="0.00";
    }
	});




$('#fareStage').on("change",function() {
	document.getElementById('sub_stage').innerHTML = "";
    document.getElementById("sub_stage").value="";
    var val= $("#fareStage").val();
    var select1 = document.getElementById("sub_stage");
    //alert(val);
    if(val=="Y"){
    	select1.options[select1.options.length] = new Option('N', 'N');
    }
    if(val=="N"){
    	select1.options[select1.options.length] = new Option('N', 'N');
    	select1.options[select1.options.length] = new Option('Y', 'Y');
    	
    }
	});



function makeSelected(id,value) {
	//alert(id+"============"+value);
	for(var k=0; k< id.length;k++){
		//alert("k====>"+k);
    var sel = document.getElementById(id[k]);
    var val = value[k];
    //alert(sel.value+"=====>"+sel.options);
    //alert(sel.options[0].innerHTML);
   // alert(sel.options.length);
    for(var i = 0, j = sel.options.length; i < j; ++i) {
    	//alert("j====>"+j);
    	//alert(sel.options[i].innerHTML +"==="+ val)
        if(sel.options[i].innerHTML === val) {
           sel.selectedIndex = i;
           break;
        }
    }
	}
}

function makeSelectedByid(id,value) {
	//alert(id+"============"+value);
	for(var k=0; k< id.length;k++){
	    var sel = document.getElementById(id[k]);
	    var val = value[k];
	    
	    for(var i = 0, j = sel.options.length; i < j; ++i) {
	    	//alert(sel.options[i].value+" ==="+ val);
	        if(sel.options[i].value === val) {
	           sel.selectedIndex = i;
	           break;
	        }
	    }
		}
}

function getDropStops(id){
	//alert(id);
	//alert("hiii in ready");
	var result = "";
	var availableTags=[];	
	var stop_type=$("#stop_type").val();
	if(id.length>=0){
	$.ajax({
	    type        : 'GET',
	    data :'json',
	    url         :  "<s:url action='GetBusDropStop'/>",
	    data: {
	        id: id,
	        stop_type: stop_type,
	    },
	   
	    success: function(data){
	      //alert('successful: '+data);
	        
	        //result=data;
	        //alert("result"+result);
	       // result=result.split("||");
	       //alert(data);
	       
	       //var json = JSON.stringify(eval("(" + data + ")"));
	        data =eval(data);
	        result=data;
	        //alert(result);
	       // alert("hiiiiiiiiiii"+data[2]);
	       
	    
	        $( "#project" ).autocomplete({
		        	minLength: 0,
		        	//change: function (event, ui) { locateMap(); },
		        	source: result,
		        	focus: function( event, ui ) {
		        	$( "#project" ).val( ui.item.busStopName );
		        	return false;
	        	},
	        	select: function( event, ui ) {
		        	$( "#project" ).val( ui.item.busStopName );
		        	$( "#project-id" ).val( ui.item.longitude );
		        	$( "#project-id1" ).val( ui.item.latitude );
		        	$( "#project-id2" ).val( ui.item.id );
		        	
		        	return false;
	        	}
	        	})
	        	.data( "ui-autocomplete" )._renderItem = function( ul, item ) {
	        	return $( "<li>" )
	        	.append( "<a>" + item.busStopName + "</a>" )
	        	.appendTo( ul );
	        	};
	        	
	        //	$( "#project" ).on( "autocompletechange", function( event, ui ) { locateMap(); } );
	       // initialize(result);
	    },
	    select: function (event, ui) {

	        alert("selected!");
	    },
	    change: function (event, ui) {

	        alert("changed!");
	    },
	    error : function(xhr, errmsg) {
	    	alert("No values found..!!"+errmsg);
	    	}
	});
}
	
}

//var uniqueid=[];

function getStopsLoad(lat,lng){
	var uniqueNames = [];
	var names=idval.split(",");
	var stop_type=$("#stop_type").val();
	$.each(names, function(i, el){
	    if($.inArray(el, uniqueNames) === -1) uniqueNames.push(el);
	});
	idval=uniqueNames.join();
	//alert(idval);
	$.ajax({
	    type        : 'POST',
	   /*  data: {
	        lat: lat,
	        lng: lng,
	        idvald:idval,
	        
	    }, */
	    data:'lat='+ lat+	
	    '&lng='+ lng+
	    '&stop_type='+stop_type+
	    '&idvald='+ idval,
	    
	    url         :  "<s:url action='GetBusStopMap'/>",
	   
	    success: function(data){
	       // alert('successful'+data);
	        result=data;
	        var zoom="15";
	       // var clat= '<%= request.getSession().getAttribute("centerlat") %>';
	       // var clang=	'<%= request.getSession().getAttribute("centerlang") %>';
	        var center = lat+","+lng;
	        //idvald=
	        <%
	        //session.removeAttribute("centerlate");
	        //session.removeAttribute("centerlange");
	        %> 
	        //alert(clat+"===="+clang);
	        var locations=result.split("@@@");
	        for (i = 0; i < locations.length-1; i++) {
	        	var dloc=locations[i].split("|");
	        	 for(var v=0;v<dloc.length;v++){
	        		 if(v==(dloc.length-1)){
	        			 idval=idval+",'"+dloc[3]+"'";
	        		 }else{
	        			 idval=idval+",'"+dloc[3]+"'";
	        		 }
	        		// idval=idval+"'"+dloc[3]+"',"
	        	 }
	        }
	        //alert(idval);
	        initialize(result,center,15);
	    },
	    error : function(xhr, errmsg) {
	    	//alert("No values found..!!"+errmsg);
	    	}
	});
}

function getStops(){
	//alert("hiii in ready");
	var uniqueNames = [];
	var names=idval.split(",");
	var stop_type=$("#stop_type").val();
	//alert("stop_type======"+stop_type);
	$.each(names, function(i, el){
	    if($.inArray(el, uniqueNames) === -1) uniqueNames.push(el);
	});
	//alert($("#stop_type").val());
	idval=uniqueNames.join();
	$.ajax({
	    type        : 'GET',
	   <%--  data: {
	        lat: '<%= request.getSession().getAttribute("centerlat") %>',
	        lng: '<%= request.getSession().getAttribute("centerlang") %>',
	        
	    }, --%>
	    data:'lat='+ <%= request.getSession().getAttribute("centerlat") %>+	
	    '&lng='+ <%= request.getSession().getAttribute("centerlang") %>+
	    '&stop_type='+stop_type+
	    '&idvald='+ idval,
	    url         :  "<s:url action='GetBusStopMap'/>",
	   
	    success: function(data){
	        //alert('successful'+data);
	        result=data;
	        var zoom="15";
	        var clat= '<%= request.getSession().getAttribute("centerlat") %>';
	        var clang=	'<%= request.getSession().getAttribute("centerlang") %>';
	        var center = clat+","+clang;
	        <%
	        session.removeAttribute("centerlate");
	        session.removeAttribute("centerlange");
	        %> 
	        //alert(clat+"===="+clang);
	        var locations=result.split("@@@");
	        for (i = 0; i < locations.length-1; i++) {
	        	var dloc=locations[i].split("|");
	        	 for(var v=0;v<dloc.length;v++){
	        		 if(v==(dloc.length-1)){
	        			 idval=idval+",'"+dloc[3]+"'";
	        		 }else{
	        			 idval=idval+",'"+dloc[3]+"'";
	        		 }
	        		// idval=idval+"'"+dloc[3]+"',"
	        	 }
	        }
	        //alert(idval);
	        initialize(result,center,15);
	    },
	    error : function(xhr, errmsg) {
	    	//alert("No values found..!!"+errmsg);
	    	}
	});
}
jQuery(document).ready(function() {
	/* $.ajax({
	    type        : 'POST',
	    url         :  "<s:url action='SetSessionStop'/>",
	    data: {
	        id: "1",
	        
	    },
	   
	    success: function(data){
	       // alert('successful'+data);
	    	//location.reload();
	    },
	    error : function(xhr, errmsg) {
	    	//alert("No values found..!!"+errmsg);
	    	}
	}); */
	getStops();
	

});



function enterpressalert(e, textarea){
	var code = (e.keyCode ? e.keyCode : e.which);
	if(code == 13) { //Enter keycode
	 //alert('enter press');
	//alert($("#project-id2").val());
		locateMap();
	}
	}
</script>

<!-- //form submit using ajax -->
<script type="text/javascript">
function submitAjaxForm(){
	//alert("hiiiii");
	document.getElementById("errmsg").innerHTML="";
	if($("#busStopName").val().trim()==""){
		document.getElementById("errmsg").innerHTML="Bus Stop Name is blank";
		//alert("1");
		return false;
	}
	if($("#bus_stop_code").val().trim()==""){
		document.getElementById("errmsg").innerHTML="Bus Stop Code is blank";
		//alert("2");
		return false;
	}
	//alert($("#org_chart_id").val());
	var orgchartids;
	if($("#org_chart_id").val()==undefined || $("#org_chart_id").val()==""){
		orgchartids=-1;
	}else{
		orgchartids=$("#org_chart_id").val();
	}
	//alert($("#tollZone option:selected").val());
	//return;
$.ajax({
    type        : 'POST',
    data: 'bustops.id='+ $("#busid1").val()+	
    '&requestType='+ $("#requestType").val()+
	'&bustops.busStopName='+  $("#busStopName").val()+
	'&bustops.busStopNameKannada='+  $("#busStopNameKannada").val()+
	'&bustops.bus_stop_code='+  $("#bus_stop_code").val().trim()+
	'&bustops.bus_stop_code_kannada='+  $("#bus_stop_code_kannada").val()+
	'&bustops.locality='+  $("#locality").val()+
	'&bustops.landmark='+  $("#landmark").val()+
	'&bustops.latitude='+  $("#latitude").val()+
	'&bustops.longitude='+  $("#longitude").val()+ 
	'&bustops.sheltered='+  $("#sheltered").val()+
  
	
	'&bustops.alias1='+  $("#alias1").val()+
	'&bustops.alias2='+  $("#alias2").val()+
	'&bustops.alias3='+  $("#alias3").val()+
	'&bustops.alias4='+  $("#alias4").val()+
	'&bustops.tollZone='+  $("#tollZone option:selected").val()+
   
	'&bustops.cityLimit='+  $("#cityLimit").val()+
	'&bustops.wardNumber='+  $("#wardNumber").val()+
	'&bustops.areaPopulation='+  $("#areaPopulation").val()+
	'&bustops.stopSize='+  $("#stopSize").val()+
   
     
	'&bustops.fareStage='+  $("#fareStage").val()+
	'&bustops.description='+  $("#description").val()+
	
	'&bustops.kalias1=' + $("#kalias1").val()
		+ '&bustops.k_alias_2=' + $("#kalias2").val() +
		'&bustops.k_alias_3=' + $("#kalias3").val() +
		'&bustops.k_alias_4=' + $("#kalias4").val() +
		'&bustops.k_alias_5=' + $("#kalias5").val() +
		'&bustops.k_alias_6=' + $("#kalias6").val() +
   
	'&bustops.kalias1='+  $("#kalias1").val()+
	'&bustops.k_alias_2='+  $("#kalias2").val()+ 
 
	//'&bustops.busid1='+  $("#busid1").val()+
   
	'&bustops.status='+  $("#status").val()+
	'&bustops.alias5='+  $("#alias5").val()+
	'&bustops.stop_direction='+  $("#stop_direction").val()+
	'&bustops.alias6='+  $("#alias6").val()+
	'&bustops.pointtype.point_type_id='+  $("#point_type_id").val()+
	'&bustops.bus_stations.org_chart_id='+  orgchartids+
	'&bustops.sub_stage='+  $("#sub_stage").val()+
	'&bustops.toll_fee='+  parseFloat($("#toll_fee").val())+
	'&toll_fee_old='+  parseFloat($("#toll_fee_old").val())+
	'&tollZone_old='+  parseFloat($("#tollZone_old").val()),
	

    url         :  "<s:url action='AddEditedBusStopMap'/>",
   
    success: function(data){
       // alert(data);
    	document.getElementById("errmsg").innerHTML=data;
    	if(data=="Saved Successfully"){
    		alert(data);
    		clearMarkers();
    		location.reload(true);
    	}
        
    },
    error : function(xhr, errmsg) {
    	document.getElementById("errmsg").innerHTML=errmsg;
    	}
});
}




$('#point_type_id').on("change",function() {
	getOrgList($('#point_type_id').val());
	/* /* if($('#point_type_id').val()=='6'){ */
		/* document.getElementById( 'org_chart_id_div' ).style.display = 'block';
	}else{
		document.getElementById( 'org_chart_id_div' ).style.display = 'none';
	} */ 
});


$('#stop_type').on("change",function() {
	
	var sess=$("#stop_type").val();
	clearMarkers();
	//alert("before++++"+idval);
	idval="'0'";
	//alert("after++++"+idval);
	getStops();
	    	//location.reload(true);

});

function checkDecimal(el){
	//alert(el.value);
	var v = parseFloat(el.value);
    if (isNaN(v)) {
    	el.value = '0.00';
    } else {
    	el.value = v.toFixed(2);
    }
	
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
function spclchar(el){
	var iChars = "!@$%^&*+=[]\\\’;,./{}|\":?~_";
	 for (var i = 0; i < el.value.length; i++) {
	 if (iChars.indexOf(el.value.charAt(i)) != -1) {
	 
		 el.value = '';
		 alert("Special Characters not allowed");
	 return false;
	     }
	   }
	 return true;
}

function getRouteInfo(busstopid,busStopName){
	//alert("aa"+busstopid);
	
	window.open("ShowRouteBusStop.action?busstopId="+busstopid+"&busStopName="+busStopName);
	
	
}
function plotCircle(id,Lat,Long,busStopName){
	
	window.open("ShowVehicles.action?id="+id+"&Lat="+Lat+"&Long="+Long+"&busStopName="+busStopName);
				}
			
			
			function plotVehicleOnMap(Lat,Long){
				  $.ajax({
			            type: "get",
			            url: '<%=request.getContextPath()%>/getVehicleDataToPlot.action?Lat='+Lat+'&Long='+Long,
						success : function(response) {
							//alert("response"+response);
							var infowindow = new google.maps.InfoWindow();
							var obj = response;
							var j = 0;
							var info = "";
							var i = 0;
							if (obj != null) {
								var objlength = obj["aaData"].length;
								for (i = 0; i < objlength; i++) {
									var latLong = new google.maps.LatLng(
											obj["aaData"][i][1], obj["aaData"][i][2]);
									//alert("Lat"+obj["aaData"][i][1]+"Long"+obj["aaData"][i][2]);
									//var time = obj["aaData"][i][3].split(" ");
									var bus_icon = 'assets/images/Icon-Bus-Orange.png';
									/* if (obj["aaData"][i][2] > 5) {
										bus_icon = 'assets/images/bus-map-icon.png';
									} else if ((obj["aaData"][i][2] <= 5)
											&& (obj["aaData"][i][2] > 0)) {
										bus_icon = 'assets/images/Icon-Bus-Orange.png';
									} else if ((obj["aaData"][i][2] == "0.00")) {
										bus_icon = 'assets/images/Icon-Bus-Blue.png';
									}
									if ((obj["aaData"][i][8] == "0")) {
										bus_icon = 'assets/images/grey.png';
									}
									if ((obj["aaData"][i][9] == "1")) {
										bus_icon = 'assets/images/fourbyfour.png';
									}
									if ((obj["aaData"][i][11] == "A")) {
//										bus_icon = 'assets/images/Icon-Bus-Red.png';
										bus_icon = 'assets/images/Icon-Bus-Red.gif';
									} */
									var marker = new google.maps.Marker(
											{
												map : map,
												position : latLong,
												icon : bus_icon,
												optimized: false,
												// icon : (obj["aaData"][i][2] >
												// 5)?'assets/images/Bus-Icon-Green.png':
												// //
												// (obj["aaData"][i][2]==="0.00")?'assets/images/Icon-Bus-Blue.png':
												// 'assets/images/Bus-Icon.png',
												latitude : obj["aaData"][i][1],
												longitude : obj["aaData"][i][2],
												deviceid : obj["aaData"][i][4],
												//vehicledirection : obj["aaData"][i][3],
												schno : '<div class="portlet-body form">'
														+ '<div class="form-body">'
														+ '<div class="table-responsive" style="color:#000000;width: 200px;" ><table class="table table-hover"><tr>'
														+ '<tr><td align=""><B>Vehicle</B></th><td>'
														+ obj["aaData"][i][4]
														+ '</td></tr>'
														+ '<tr><td align=""><B>Device Id</B></th><td>'
														+ obj["aaData"][i][3]
														+ '</td></tr></table>'
														+ '</div>'
														+ '</div>'
														+ '</div>'
												
											});// End of marker defination
									// Setting Info window
									google.maps.event
											.addListener(
													marker,
													'click',
													(function(marker, j) {
														return function() {
															infowindow.setContent(this.info);
															infowindow.open(map, this);
															//AllDevices.get_reverse_geocode(this.latitude,this.longitude);
															//AllDevices.get_schedule_number(this.deviceid);
														}
													})(marker, j));
									google.maps.event
											.addListener(
													marker,
													'mouseover',
													(function(marker, j) {
														return function() {
															infowindow
																	.setContent(this.schno);
															infowindow.open(map, this);
															//AllDevices
																	//.get_schedule_number(this.deviceid);
														}
													})(marker, j));
									// google.maps.event
									// .addListener(
									// marker,
									// 'mouseout',
									// (function(marker, j) {
									// return function() {
									// infowindow
									// .setContent(this.schno);
									// infowindow.open(map, this);
									// AllDevices
									// .get_schedule_number(this.deviceid);
									// }
									// })(marker, j));
									google.maps.event.addListener(map, 'click',
											function() {
												infowindow.open(null, null);
											});
									j++;
									// Setting Center location
									/* if (obj["aaData"][i][0] != '0.00000000') {

										vehiclemarker.push(marker);
									}
*/									// Setting marker
									// on map
								} // End of for loop
								setAllMap(map);
							}
							// map.setCenter(latLong);
						}
					});

				
				
			}
	
						
</script>