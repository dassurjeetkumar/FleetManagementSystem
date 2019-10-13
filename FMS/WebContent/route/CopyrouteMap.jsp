<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<head>
<sx:head />
</head>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css"/> -->
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
	<link rel="stylesheet" href="/resources/demos/style.css">
	<script src="assets/global/plugins/jquery-ui-1.9.2.js" type="text/javascript"></script>
	<%-- <script src="assets/global/plugins/jquery-1.11.0.min.js" type="text/javascript"></script> --%>
	<script
	src="https://maps.googleapis.com/maps/api/js?client=gme-trimaxitinfrastructure&sensor=false&libraries=places,drawing&signature=CpSOeA5B6SzC2rxqlzd2JwenGX0="></script>
<script src="assets/admin/pages/scripts/copy_route.js" type="text/javascript"></script>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
 <style>
 /* table {
    background-color: #aaa;
}
tbody {
    background-color: #ddd;
    height: 350px;
    overflow: auto;
}


thead > tr, tbody{
    display:block;}  */
div#resizable1 {
    height: 500px;
    overflow-y:auto;
    /* overflow-x:auto; */
   
    padding: 1px;
}
</style>
<script>
/* $(function() {
$( "#resizable" ).resizable({
animate: true,
aspectRatio: true,

});
});
$(function() {
	$( "#resizable1" ).resizable({
	animate: true,
	aspectRatio: true,

	});
	
	}); */
	
</script>

<div class="page-content-wrapper">
	<div class="page-content">
		<div class="form-group">
		<div class="row">
		<div class="col-md-4">
		
				<input class="form-control input-lg" placeholder="Enter Bus Stop Name to Search" id="project" name="project" type="text" onkeyup="getDropStops(this.value)"   onKeyPress="enterpressalert(event, this)" onblur="locateMap()" /> 
				
				<input id="project-id" type="hidden">
				 <input id="project-id1" type="hidden">
				 <input id="project-id2" type="hidden">
			</div>
			<div class="col-md-2">
		<button type="button" id="go" aria-hidden="true"  class="btn white" >Toggle Size</button>
				<!-- <button id="go">Toggle Size</button> -->
			</div>
			<div class="col-md-2">
		<button type="button" id="ReloadStop" aria-hidden="true"  class="btn white" >Reload Stops</button>
				<!-- <button id="go">Toggle Size</button> -->
			</div>
			
			<div class="col-md-4">
			<div class="portlet-body">
			<ul class="list-group">
			<li class="list-group-item bg-white" id="selectbusstopname"></li>
			</ul>
			</div>
			</div>
			
			<script>
// Using multiple unit types within one animation.
var flag=1;
$( "#go" ).click(function() {
	//alert(flag);
	
	if(flag==0){
		$( "#resizable" ).animate({
			width: "30%",

			//marginLeft: "0.6in",

			//borderWidth: "20px"
			}, 1500 );
			
		$( "#resizable1" ).animate({
	width: "70%",

	//marginLeft: "0.6in",

	//borderWidth: "10px"
	}, 1500 );
	
	}
	else
	{
		$( "#resizable" ).animate({
			width: "70%",

			//marginLeft: "0.6in",

			//borderWidth: "10px"
			}, 1500 );
			
		$( "#resizable1" ).animate({
	width: "30%",

	//marginLeft: "0.6in",

	//borderWidth: "10px"
	}, 1500 );
	
	}
	if(flag==0){
		flag=1;
	}else{
		flag=0;
	}
});

$( "#ReloadStop" ).click(function() {
	clearMarkers();
});
</script>
					</div>
					
					<div id="pageLoader" class="modal fade in" tabindex="-1" role="dialog" aria-labelledby="myModalLabel3" aria-hidden="false">
								<div class="modal-dialog">
									<div class="modal-content">
										
										<div class="modal-body">
											<p id="loadercontent">
												<img src="assets/images/loader1.gif" align="top" style="margin-left:100px;"/> Please Wait(0%)</p>
											
										</div>
										
									</div>
								</div>
							</div>
		<div class="row">
		<div  id="resizable" class="col-md-8">
		
		<div class="portlet solid white" >
			<!-- <div class="portlet-title">
				<div class="caption">
								<i class="fa fa-gift"></i>Bus Stops
							</div>
				
			</div> -->
			<div class="portlet-body"  >
				<div id="gmap_marker" class="gmaps"  >
				</div>
			</div>
		</div>
			</div>
			<form class="form-horizontal" role="form" method="get">
			<div  id="resizable1" class="col-md-4">
		
		<div class="portlet box white" >
						
						<div class="portlet-body" >
						
							<div id="accordion3" class="panel-group accordion" >
								<div class="panel panel-default" >
									<div class="panel-heading" overflow:auto;" >
										<h4 class="panel-title">
										<a href="#collapse_3_1" data-parent="#accordion3" data-toggle="collapse" class="accordion-toggle accordion-toggle-styled collapsed">
										Route Definition </a>
										</h4>
									</div>
									<div class="panel-collapse collapse" id="collapse_3_1"  style="color:#000000;overflow:auto;">
										<div class="panel-body">
											<div class="form-body">
											
											<div class="table-responsive" style="color:#000000">
											<table class="table table-hover"  border="1" RULES=NONE FRAME=BOX>
											<tr><th align="right">Route No: <font color="red">*</font></th><td><input class="form-control input-sm" placeholder="" type="text" id="route_number" name="route_number" maxlength="50"></td></tr>
											<tr><th align="right">Route Description:</th><td><input class="form-control input-sm" placeholder="" type="text" id="route_description" name="route_description" maxlength="200"></td></tr>
											<tr><th align="right">Via Place:</th><td><input class="form-control input-sm" placeholder="" type="text" id="via" name="route.via" maxlength="100"></td></tr>
											<tr><th align="right">Route Type:</th>
											<td>
											<select class="form-control input-sm" id="route_type_id"
											name="route_type_id">
											<s:iterator value="listRouteType" id="listRouteType">
											<option value="<s:property value="route_type_id"/>"><s:property value="route_type_name"/></option>
											</s:iterator>
											
										</select>
											</td></tr>
											<tr><th align="right">Route Direction:</th><td>
											<select class="form-control input-sm" id="route_direction" name="route_direction">
												<option value="UP">UP</option>
												<option value="DOWN">DOWN</option>
											</select>
											</td>
											<input class="form-control input-sm"  type="hidden" id="skipval" name="skipval" >
											<input class="form-control input-sm"  type="hidden" id="counter" name="counter" >
											<input class="form-control input-sm"  type="hidden" id="start_point_id" name="start_point_id" >
											<input class="form-control input-sm"  type="hidden" id="end_point_id" name="end_point_id" />
											<input class="form-control input-sm"  type="hidden" id="route_name" name="route_name" />
											<input class="form-control input-sm"  type="hidden" id="polypath" name="polypath" />
											<input class="form-control input-sm"  type="hidden" id="route_string" name="route_string" />
											<input class="form-control input-sm"  type="hidden" id="road" name="road" />
											<td style="display:none">
<a class="btn white disaddroute" onclick="AddCopyRoute(21948)" href="#routeaddmodel" id="copyroutebutton" role="button" data-toggle="modal">Add Route</a>
</td>
											</tr>
											<tr>
											<th>Effective Start Date:</th>
									<td>
										<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd/mm/yyyy"
															data-date-viewmode="years">
											<input type="text" size="16" readonly class="form-control"
												id="effect_start_date_route"
												name="effect_start_date_route"> <span
												class="input-group-btn" >
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>						
										</div>
										</td>
										
										<!-- /input-group -->
									
								
											</tr>
											<script>
											$('#route_number').val("");
											$('#route_description').val("");
											$('#via').val("");
											var curr_date=new Date().toJSON().slice(0,10);
											curr_date=curr_date.split("-");
											curr_date=curr_date[2]+"/"+curr_date[1]+"/"+curr_date[0];
											$('#effect_start_date_route').val(curr_date);
											</script>
											<tr>
											<th>Effective End Date:</th>
									<td>
										<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd/mm/yyyy"
															data-date-viewmode="years">
											<input type="text" size="16" readonly class="form-control"
												id="effect_end_date_route"
												name="effect_end_date_route"> <span
												class="input-group-btn" >
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>						
										</div>
										</td>
										
										<!-- /input-group -->
									
								
											</tr>
											</table>	
											</div>
										</div>
									</div>
								</div>
								</div>
								<div class="panel panel-default"  style="color:#000000;">
									<div class="panel-heading">
										<h4 class="panel-title">
										<a href="#collapse_3_2" data-parent="#accordion3" data-toggle="collapse" class="accordion-toggle accordion-toggle-styled collapsed">
										Bus Stops </a>
										</h4>
									</div>
									<div class="panel-collapse collapse" id="collapse_3_2" style="height: 0px;overflow-y:auto;overflow-x:auto;">
										<div class="panel-body">
											<div class="form-body">
											<div class="table-responsive" style="color:#000000;">
											<table class="table table-hover" id="bus_stops"  border="1" RULES=NONE FRAME=BOX>
											<thead>
											<tr><th>Stop No</th><th style="display: none;">Stop Id</th><th>Chargable Stage</th><th>Stop Name</th><th>fare Stage</th><th>Sub Stage</th><th style="display: none;">Status</th><th></th></tr>
											</thead>
											<tbody>
											<tr><th style="display: none;">Stop No</th><th style="display: none;">Stop Id</th><th style="display: none;">Stop Name</th><th style="display: none;">fare Stage</th><th style="display: none;">Sub Stage</th><th style="display: none;">Status</th><th></th></tr>
											</tbody>
											</table>	
											</div>
										</div>
									</div>
									</div>
								</div>
								<div class="panel panel-default"  style="color:#000000;">
									<div class="panel-heading">
										<h4 class="panel-title">
										<a href="#collapse_3_3" data-parent="#accordion3" data-toggle="collapse" class="accordion-toggle accordion-toggle-styled collapsed">
										Route Map</a>
										</h4>
									</div>
									<div class="panel-collapse collapse" id="collapse_3_3" style="height: 0px;overflow-y:auto;overflow-x:auto;">
										<div class="panel-body">
											<div class="form-body">
											<div class="table-responsive" style="color:#000000;">
											<table class="table table-hover" id="bus_stops_map"  border="1" RULES=NONE FRAME=BOX>
											<thead>
											<tr><th colspan="1">Distance between two stops</th><th colspan="1">Time taken between two stops </th><th>Road Type</th><th>Total Distance(Google)</th><th>Total distance (VTU)</th><th>Total Time</th><th>Total Dist. in KM(Sch.)</th><th>Total Time in Min(Sch.)</th></tr>
											<tr><th>In Kms</th>
											<!-- <th>In Meters</th> -->
											<th>In Minutes</th>
											<!-- <th>In Seconds</th> -->
											<th></th><th></th><th></th><th></th><th></th></tr>
											</thead>
											<tbody>
											<tr><th id="maptotdistm">0</th>
											<!-- <th></th> -->
											<th id="maptottimesectm">0</th><th></th><th></th><th></th><th></th><th></th></tr>
											</tbody>
											</table>	
											</div>
										</div>
									</div>
									</div>
									
									
									
									<div class="form-actions right1" style="dispaly:none">
											<div class="form-body">
											<div class="table-responsive" id="platformdetails" style="color:#000000;overflow:auto;display:none;">
											<table class="table table-hover" id="platformdetail">
											<tr><input type="hidden" id="platformcounter" name="platformcounter"></tr>
											
											</table>	
											</div>
										</div>
									</div>
									
									
									
								</div>
								<div class="form-actions right1">
								<button type="button" id="done" aria-hidden="true"  class="btn blue" onclick="submitAjaxForm()">Save Changes</button>
								<!-- <button type="button" id="create" aria-hidden="true"  class="btn blue"  onclick="">Create Route</button> -->
								<a href="ShowRoute.action" class="btn grey"  id="cancel"> <!-- //onclick="callEdit()" -->
								 Cancel </a>
								 <a data-toggle="modal" id="farechart" class="btn blue" role="button" href="#myModal11" >EDIT </a> 
								 <button type="button" id="reset" aria-hidden="true"  class="btn grey" onclick="resetfo()">Reset</button>
							</div>
							</div>
						</div>
					</div>
			</div>
			
					</div>
					</form>
			
			

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
				<h4 class="modal-title"></h4>
			</div>
			<div class="modal-body">
				<p>
				<div class="portlet box purple ">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i> Add Platform
						</div>
						
					</div>
					
			
		</div>
					<div class="portlet-body form">
					<div class="row">
					<div class="col-md-6">
						<form class="form-horizontal" role="form"
							action="AddEditedBusStopMap.action" method="post">
							<input type="hidden" name="bustops" id="busid1" value="" /> 
							<input type="hidden" name="requestType" id="requestType" value="map" />
							<input type="hidden" name="trid" id="trid" value="" />
							<input type="hidden" name="busstationid" id="busstationid" value="" />
								<div class="form-body">
								<div class="form-group">
								<div class="row">
								<input type="hidden" name="orgchart" id="orgchart" value="" />
								<%-- <div class="col-md-6">
								<label class="col-md-10 control-label">Depot Name</label>
								</div>
								<div class="col-md-6">
									<select class="form-control input-sm" id="orgchart" name="orgchart" >
											<option value="0">Select</option>
											<s:iterator value="orgchart" id="orgchart">
 												<option value="<s:property value="org_chart_id"/>"><s:property value="org_name"/></option>
 											</s:iterator>
										</select>
									</div>
									<div class="col-md-3">
									
					
								</div> --%>
								</div>
								
								
								<div class="row" id="floordiv" style="display: none;">
								<div class="col-md-6">
								<label class="col-md-10 control-label">Floor Name</label>
								</div>
								<div class="col-md-6">
									<select class="form-control input-sm" id="floor" name="floor" >
											
									</select>
									</div>
									<div class="col-md-3">
									
					
								</div>
								</div>
								
								
								<div class="row" id="baydiv" style="display: none;">
								<div class="col-md-6">
								<label class="col-md-10 control-label">Bay Name</label>
								</div>
								<div class="col-md-6">
									<select class="form-control input-sm" id="bay" name="bay" >
											
									</select>
									</div>
									<div class="col-md-3">
									
					
								</div>
								</div>
								
								
								
								<div class="row" id="platformdiv" style="display: none;">
								
								</div>
								
								<div class="row" id="submitdiv" style="display: none;" >
								<div class="col-md-3">
								</div>
								<div class="col-md-6">
								<button type="button" id="done" aria-hidden="true"  class="btn blue" onclick="show_detail()">Add To List</button>
								</div>
								</div>
								
								
								</div>
								
							
							</div>
							
							
						</form>
						</div>
						<div class="col-md-6">
						
						<div class="table-responsive"  style="color:#000000;overflow:auto;">
											<table class="table table-hover" id="platform_details">
											<tr><th colspan="1">Platform No</th><th colspan="1">Service Type</th></tr>
											</table>	
											<table class="table table-hover" id="btn">
											<tr><td colspan="2">
											<button class="btn blue" id="platform" data-dismiss="modal" aria-hidden="true" onclick="add_platform()">CONTINUE TO ROUTE</button>
											<!-- <button type="button"  aria-hidden="true"  class="btn blue" ></button> -->
											
											</td></tr>
											</table>
											
											
											</div>
						
						
						</div>
						<div class="col-md-6">
						
						<div class="table-responsive" style="color:#000000;overflow:auto;">
											<!-- <table class="table table-hover" id="btn">
											<tr><td colspan="2">
											<button class="btn blue" id="platform" data-dismiss="modal" aria-hidden="true" onclick="add_platform()">Save Changes</button>
											<button type="button"  aria-hidden="true"  class="btn blue" ></button>
											
											</td></tr>
											</table> -->
											</div>
						
						
						</div>
						
						
						
						
					</div>
					</div>
					
					
					
					
					
					
					
					
				</div>
				</p>
			</div>
			
		</div>
	</div>







<div style="display: none;" id="routeaddmodel" class="modal fade"
	tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
	aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" id="closeroutediv" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title"></h4>
			</div>
			<div class="modal-body">
				<p>
				<div class="portlet box purple ">
					<div class="portlet-title">
						<div class="caption" id="routeheading">
							<i class="fa fa-gift"></i> Route List
						</div>
						
					</div>
					
			
		</div>
					<div class="portlet-body form">
					<div class="row">
					<div class="col-md-12">
						<form class="form-horizontal" role="form"
							action="AddEditedBusStopMap.action" method="post">
							
							
								<div class="form-body">
											<div class="table-responsive" style="color:#000000;">
											<input type="hidden" id="copyroutebusstopid" id="copyroutebusstopid" value="">
											<input type="hidden" id="copyrouteid" id="copyrouteid" value="">
											<table class="table table-hover" id="copy_route_list"  border="1" RULES=NONE FRAME=BOX>
											
											</table>	
											</div>
										</div>
							</form>
						</div>
						</div>
					</div>
					</div>
				</p>
			</div>
			
		</div>
	</div>
	
	
	
	
	
	<div style="display: none;" id="busstopshowmodel" class="modal fade"
	tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
	aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title"></h4>
			</div>
			<div class="modal-body">
				<p>
				<div class="portlet box purple ">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i> Bus Stop List
						</div>
						
					</div>
					
			
		</div>
					<div class="portlet-body form">
					<div class="row">
					<div class="col-md-12">
						<form class="form-horizontal" role="form"
							action="AddEditedBusStopMap.action" method="post">
							
							
								<div class="form-body">
											<div class="table-responsive" style="color:#000000;">
											<input type="hidden" id="copyroutebusstopid" id="copyroutebusstopid" value="">
											<table class="table table-hover" id="copy_busstop_list"  border="1" RULES=NONE FRAME=BOX>
											
											</table>	
											</div>
										</div>
							</form>
						</div>
						</div>
					</div>
					</div>
				</p>
			</div>
			
		</div>
	</div>




</div>










<script>
var idval="'0'";
var idvalrelo="'0'";

function resetfo(){
	//alert("hiii");
	location.reload(true);
}
function enterpressalert(e, textarea){
	var code = (e.keyCode ? e.keyCode : e.which);
	if(code == 13) { //Enter keycode
	 //alert('enter press');
	//alert($("#project-id2").val());
		locateMap();
	}
	}
	
	
	
function locateMap(){
	var lag=document.getElementById("project-id").value;
	var lat=document.getElementById("project-id1").value;
	var id=document.getElementById("project-id2").value;
	var stop_type=0;
	var loc=lat+","+lag;
	var zoom=18;
	//alert("aaa"+loc);
	if(id==""){
		//alert("Invalid Stop");
		return false;
	}
	idval.indexOf(id);
	//alert(idval.indexOf(id));
	if(idval.indexOf(id)!=-1){
		//alert("hiiii"+lag+lat);
		
		map.setCenter(new google.maps.LatLng(lat,lag));
		map.setZoom(18);
		//initialize("",loc,zoom);
	}else{
	$.ajax({
	    type        : 'POST',
	    url         :  "<s:url action='GetBusStopMapLocate'/>",
	    data: {
	        id: id,
	        stop_type: stop_type,
	    },
	   
	    success: function(data){
	       
	        result=data;
	     
	      idval=idval+",'"+id+"'";
	      idvalrelo=idvalrelo+",'"+id+"'";
	      var names=idval.split(",");
	      var uniqueNames= [];
	  	$.each(names, function(i, el){
	  	    if($.inArray(el, uniqueNames) === -1) uniqueNames.push(el);
	  	});
	  	idval=uniqueNames.join();
	  	
	  	var names=idvalrelo.split(",");
	      var uniqueNames= [];
	  	$.each(names, function(i, el){
	  	    if($.inArray(el, uniqueNames) === -1) uniqueNames.push(el);
	  	});
	  	idvalrelo=uniqueNames.join();
	  	
	  	
	  	// alert('successful'+loc);
	        initialize(result,loc,zoom);
	    },
	    error : function(xhr, errmsg) {
	    	//alert("Session out error..Please login again!!");
	    	}
	});
	}
	document.getElementById("project-id").value="";
	lat=document.getElementById("project-id1").value="";
	id=document.getElementById("project-id2").value="";
}


function getDropStops(id){
	//alert(id);
	//alert("hiii in ready");
	var result = "";
	var availableTags=[];	
	var stop_type=0;
	if(id.length>=0){
	$.ajax({
	    type        : 'POST',
	    data :'json',
	    url         :  "<s:url action='GetBusDropStop'/>",
	    data: {
	        id: id,
	        stop_type: stop_type,
	    },
	   
	    success: function(data){
	        data =eval(data);
	        result=data;
	       
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

	       // alert("selected!");
	    },
	    change: function (event, ui) {

	       // alert("changed!");
	    },
	    error : function(xhr, errmsg) {
	    	//alert("Session out error..Please login again");
	    	}
	});
}
	
}





</script>
<script>


getRoadType();
var uniqueid=0;platformcounter=0;busplatid=0;
function addstop(id,trid, busstationid){
	//alert("hiiiiii"+id);
	$('#trid').val(trid);
	$('#busstationid').val(busstationid);
	$('#orgchart').val(busstationid);
	$('#busid1').val(id);
	getfloor();
	busplatid=id;
	document.getElementById("orgchart").selectedIndex = 0;
	document.getElementById("floor").selectedIndex = 0;
	document.getElementById("bay").selectedIndex = 0;
	//document.getElementById("platformdiv").value="";
	$( "#platformdiv" ).empty();
   // $("#platform_details tbody tr").remove(); 
   $("#platform_details tr:gt(0)").remove();
   uniqueid=0;
  // alert("hiiiiii"+tableplatarr);
	for(s=0;s<tableplatarr.length;s++){
		//alert(tableplatarr[s].id+"======>"+id+"ID:- "  + tableplatarr[s].planame + " Name:- "  + tableplatarr[s].sername );
		if(id==tableplatarr[s].id){
		//$('#platform_details tr').last().after('<tr><td colspan="1">'+tableplatarr[s].planame+'</td><td>'+tableplatarr[s].sername+'</td>');
		$('#platform_details tr').last().after('<tr><td colspan="1">'+tableplatarr[s].planame+'</td><td>'+tableplatarr[s].sername+'</td><td><input type="hidden" class="depot" id="depot'+uniqueid+'" value="'+tableplatarr[s].depot+'"></td><td><input type="hidden" class="floor" id="floor'+uniqueid+'" value="'+tableplatarr[s].floor+'"></td><td><input type="hidden" class="bay" id="bay'+uniqueid+'" value="'+tableplatarr[s].bay+'"></td><td><input type="hidden" class="platform" id="platform'+uniqueid+'" value="'+tableplatarr[s].platform+'"></td><td><input type="hidden" class="service" id="service'+uniqueid+'" value="'+tableplatarr[s].service+'"></td><td style="display: none;">'+busplatid+""+""+tableplatarr[s].floor+""+tableplatarr[s].bay+""+tableplatarr[s].platform+""+tableplatarr[s].service+'</td><td><button class="close" aria-hidden="true" data-dismiss="alert" type="button" onclick="removeplatform('+busplatid+""+tableplatarr[s].floor+""+tableplatarr[s].bay+""+tableplatarr[s].platform+""+tableplatarr[s].service+""+',\''+busplatid+"|"+tableplatarr[s].floor+"|"+tableplatarr[s].bay+"|"+tableplatarr[s].platform+"|"+tableplatarr[s].service+'\')"></button></td></tr>');
		//$('#platform_details tr').last().after('<tr id="'+uniqueid+'"><td colspan="1">'+platform+'</td><td>'+$(this).html()+'</td><td><input type="hidden" class="depot" id="depot'+uniqueid+'" value="'+depotname+'"></td><td><input type="hidden" class="floor" id="floor'+uniqueid+'" value="'+floorname+'"></td><td><input type="hidden" class="bay" id="bay'+uniqueid+'" value="'+bayname+'"></td><td><input type="hidden" class="platform" id="platform'+uniqueid+'" value="'+platformnos[i]+'"></td><td><input type="hidden" class="service" id="service'+uniqueid+'" value="'+$(this).val()+'"></td><td style="display: none;">'+busplatid+""+floorname+""+bayname+""+platformnos[i].replace(",", "")+""+$(this).val()+'</td><td><button class="close" aria-hidden="true" data-dismiss="alert" type="button" onclick="removeplatform('+busplatid+""+floorname+""+bayname+""+platformnos[i].replace(",", "")+""+$(this).val()+""+',\''+busplatid+"|"+floorname+"|"+bayname+"|"+platformnos[i].replace(",", "")+"|"+$(this).val()+'\')"></button></td></tr>');
		}
	}
   // $('#platform_details').children( 'tr:not(:first)' ).remove();


//	$( "#" ).empty();
	
	//document.getElementById("bay").selectedIndex = 0;
}

function add_platform(){
	
	var depo=[];
	var floor=[];
	var bay=[];
	var platform=[];
	var service=[];
	var trid=+$('#trid').val();
	$("#platform_details .depot").each(function() {
		//alert("hiii");
		depo.push($(this).val());
		});
	$("#platform_details .floor").each(function() {
		floor.push($(this).val());
		});
	$("#platform_details .bay").each(function() {
		bay.push($(this).val());
		});
	$("#platform_details .platform").each(function() {
		platform.push($(this).val());
		});
	$("#platform_details .service").each(function() {
		service.push($(this).val());
		});
	//alert("depo.length"+depo.length);
	for(var m=0;m<depo.length;m++){
	/* 	//alert("in loop");
		depo.push($('#depot'+m).val());
		floor.push($('#floor'+m).val());
		bay.push($('#bay'+m).val());
		platform.push($('#platform'+m).val());
		service.push($('#service'+m).val()); */
		//service.push("|");
		/* if($('#platform'+m).val()!=""){ */
		//alert($('#platform'+m).val());
		var add=0;
		$("#platformdetail tr td:contains('" +busplatid+""+platform[m]+""+service[m]+""+ "')").each(function() {
     		var compfact=busplatid+""+platform[m]+""+service[m]+"";    
			if ($(this).text() == compfact) {
				add=1;
     		    }  
     		});
		if(add==0){
		$('#platformdetail tr').last().after('<tr id="1"><td  style="display: none;">'+busplatid+""+platform[m]+""+service[m]+""+'</td><td><input type="hidden" class="busplatformid" id="busplatformid'+platformcounter+'" value="'+busplatid+'">    </td>              <td><input type="hidden" class="depotp" id="depotp'+platformcounter+'" value="'+depo[m]+'">    </td><td><input type="hidden" class="floorp" id="floorp'+platformcounter+'" value="'+floor[m]+'"></td>    </td><td><input type="hidden" class="bayp" id="bayp'+platformcounter+'" value="'+bay[m]+'"></td>    </td><td><input type="hidden" class="platformp" id="platformp'+platformcounter+'" value="'+platform[m]+'"></td>    </td><td><input type="hidden" class="servicep" id="servicep'+platformcounter+'" value="'+service[m]+'"></td></tr>');
		$('#platformcounter').val(platformcounter);
		platformcounter++;
		}
		/* } */
	}
	
	
	//$('tr#'+$('#trid').val()).append('<td><input type="text" id="depotp'+trid+'" value="'+depo+'">    </td><td><input type="text" id="floorp'+trid+'" value="'+floor+'"></td>    </td><td><input type="text" id="bayp'+trid+'" value="'+bay+'"></td>    </td><td><input type="text" id="platformp'+trid+'" value="'+platform+'"></td>    </td><td><input type="text" id="servicep'+trid+'" value="'+service+'"></td>');
	//$("#myModal1").hide();
	//document.getElementById( 'myModal1' ).style.display = 'none';

}
var platverarr=[];
var tableplatarr = [];
function show_detail(){
	/* alert("hiiiiii"+tableplatarr);
	for(s=0;s<tableplatarr.length;s++){
		//alert("ID:- "  + employees[i].id + " Name:- "  + employees[i].name + " Age:- " + employees[i].age);
		if(busstopd==tableplatarr[s].id){
		$('#platform_details tr').last().after('<tr><td colspan="1">'+tableplatarr[s].planame+'</td><td>'+tableplatarr[s].sername+'</td>');
		}
	} */
	var depotname=$('#orgchart').val();
	var floorname=$('#floor').val();
	var bayname=$('#bay').val();
	var busstopd=$('#busid1').val();
	var platformsize=$('#platformsize').val();
	if(platformsize!="0" && platformsize!=""){
		var platformnos= $('#platformnos').val();
		platformnos=platformnos.split(",");
		//alert(platformnos);
		//if(platformsize)
		for(var i=0;i<platformsize;i++){
			
			var platform=$('#platform'+platformnos[i]).html();
			var serviceid=$('#servicetype-'+platformnos[i]).val();
			var service=$( "#servicetype-"+ platformnos[i] +" option:selected" ).text();
			//alert("platverarr"+platverarr);
			var flag=0;
			$(".service").each(function() {
				if($(this).val()==serviceid){
					alert("Selected Service Type "+service+" is Already Assign To Another Platform");
					flag=1;
					return false;
				}
				});
			if(flag==1){
				return false;
			}
			if(serviceid!=null){
			for(var k=0;k<serviceid.length;k++){
				/* if(serviceid[k]==serviceid){
					alert("Selected Service Type "+service+" is Already Assign To Another Platform");
					return false;
				} */
				var temp="";
				temp=temp+busstopd+"|"+platformnos[i]+"|"+serviceid[k];
				if(platverarr.length!=0){
					for(var m=0;m<platverarr.length;m++){
						if(platverarr[m]==temp){
							alert("Duplicate Entry");
							
							return false;
						}
					}
				}
				
			//	alert(temp);
			}
			}
			if(service!=""){
				//alert("hiii"+platform);
				//alert('<tr id="'+uniqueid+'"><td colspan="1">'+platform+'</td><td>'+service+'</td><td><input type="hidden" id="depot'+uniqueid+'" value="'+depotname+'"></td><td><input type="hidden" id="floor'+uniqueid+'" value="'+floorname+'"></td><td><input type="hidden" id="bay'+uniqueid+'" value="'+bayname+'"></td><td><input type="hidden" id="platform'+uniqueid+'" value="'+platformnos[i]+'"></td><td><input type="hidden" id="service'+uniqueid+'" value="'+serviceid+'"></td></tr>');
			$("#servicetype-"+ platformnos[i] +" :selected").each(function(){
			       //$(this).val(); 
			     //  alert($(this).html()+"======"+$(this).val()+"========"+platformnos[i]);
				var flag=0;
	 			$(".service").each(function() {
	 				if($(this).val()==serviceid){
	 					alert("Selected Service Type "+service+" is Already Assign To Another Platform");
	 					flag=1;
	 					return false;
	 				}
	 				});
	 			if(flag==1){
	 				return false;
	 			}
			       $('#platform_details tr').last().after('<tr id="'+uniqueid+'"><td colspan="1">'+platform+'</td><td>'+$(this).html()+'</td><td><input type="hidden" class="depot" id="depot'+uniqueid+'" value="'+depotname+'"></td><td><input type="hidden" class="floor" id="floor'+uniqueid+'" value="'+floorname+'"></td><td><input type="hidden" class="bay" id="bay'+uniqueid+'" value="'+bayname+'"></td><td><input type="hidden" class="platform" id="platform'+uniqueid+'" value="'+platformnos[i]+'"></td><td><input type="hidden" class="service" id="service'+uniqueid+'" value="'+$(this).val()+'"></td><td style="display: none;">'+busplatid+""+floorname+""+bayname+""+platformnos[i].replace(",", "")+""+$(this).val()+'</td><td><button class="close" aria-hidden="true" data-dismiss="alert" type="button" onclick="removeplatform('+busplatid+""+floorname+""+bayname+""+platformnos[i].replace(",", "")+""+$(this).val()+""+',\''+busplatid+"|"+floorname+"|"+bayname+"|"+platformnos[i].replace(",", "")+"|"+$(this).val()+'\')"></button></td></tr>');
			    //   tableplatarr.push({id:busstopd,planame:platform,sername:$(this).html()});
			      tableplatarr.push({id:busstopd,name:busplatid+""+floorname+""+bayname+""+platformnos[i].replace(",", "")+""+$(this).val()+"",planame:platform,sername:$(this).html(),   depot:depotname,floor:floorname,bay:bayname,platform:platformnos[i],service:$(this).val()});
			     var temp="";
			      temp=temp+busstopd+"|"+platformnos[i].replace(",", "")+"|"+$(this).val();
			      platverarr.push(temp);
			      uniqueid++;
			    });
					
			
			 
			   
			
			}
			
		}
	}
	
}

function removeplatform(findval,searchval){
	//alert(searchval);
	//alert(tableplatarr);
	removeItem(tableplatarr,'name',findval);
	//alert(platverarr[0]);
	platverarr.toString().split(",");
	//alert("platverarr before"+platverarr);
	 var index = platverarr.indexOf(searchval);
	// alert(index);
	 platverarr.splice(index, 1);
	//alert("platverarr afetr"+platverarr);
	$("#platform_details").find("td:contains('" + findval  + "')").parent().remove();
	 /* $("#platform_details tr td:contains('" + findval  + "')").each(function() {
		alert($(this).html());
		 if ($(this).html() == findval) {
		        $(this).parent().remove();
		 }
		}); */
		$("#platformdetail").find("td:contains('" + findval  + "')").parent().remove();
	 /* $("#platformdetail tr:contains('" + findval  + "')").each(function() {
		 alert($(this).html());  
		 if ($(this).html() == findval) {
		        $(this).parent().remove();
		    }  
		}); */
}

function removeItem(obj, prop, val) {
    var c, found=false;
    for(c in obj) {
        if(obj[c][prop] == val) {
            found=true;
            break;
        }
    }
    if(found){
        delete obj[c];
    }
}



function getfloor() {
	  // alert("hii changed");
	   $.ajax({
		    type        : 'POST',
		    data: {
		        id: $('#orgchart').val(),
		        method:'1',
		    },
		    url         :  "<s:url action='GetFloor'/>",
		   
		    success: function(data){
		    	//alert(data);
		    	if(data!=""){
		    		$('#floordiv').show();
		    		$('#floor').html(data);
		    	}else{
		    		$('#floordiv').hide();
		    	}
		    	document.getElementById("floor").selectedIndex = 0;
		    	getBay();
		    	
		    },
		    error : function(xhr, errmsg) {
		    	alert("Please Provide the Floor Details");
		    	}
		});
	};
	
	function getBay(){
		$.ajax({
		    type        : 'POST',
		    data: {
		        id: $('#floor').val(),
		        method:'1',
		    },
		    url         :  "<s:url action='GetBay'/>",
		   
		    success: function(data){
		    	//alert(data);
		    	if(data!=""){
		    		$('#baydiv').show();
		    		$('#bay').html(data);
		    		document.getElementById("bay").selectedIndex = 0;
		    	}else{
		    		$('#baydiv').hide();
		    	}
		    	
		    },
		    error : function(xhr, errmsg) {
		    	alert("No detail found");
		    	}
		});
	}
$('#floor').on("change",function() {
	  // alert("hii changed");
	   $.ajax({
		    type        : 'POST',
		    data: {
		        id: $('#floor').val(),
		        method:'1',
		    },
		    url         :  "<s:url action='GetBay'/>",
		   
		    success: function(data){
		    	//alert(data);
		    	if(data!=""){
		    		$('#baydiv').show();
		    		$('#bay').html(data);
		    		document.getElementById("bay").selectedIndex = 0;
		    	}else{
		    		$('#baydiv').hide();
		    	}
		    	
		    },
		    error : function(xhr, errmsg) {
		    	alert("No detail found");
		    	}
		});
	});
	
	
$('#bay').on("change",function() {
	  // alert("hii changed");
	   $.ajax({
		    type        : 'POST',
		    data: {
		        id: $('#bay').val(),
		        method:'1',
		    },
		    url         :  "<s:url action='GetPlatform'/>",
		   
		    success: function(data){
		    	//alert(data);
		    	if(data!=""){
		    		$('#platformdiv').show();
		    		$('#submitdiv').show();
		    		$('#platformdiv').html(data);
		    	}else{
		    		$('#platformdiv').hide();
		    	}
		    	
		    },
		    error : function(xhr, errmsg) {
		    	alert("No detail found");
		    	}
		});
	});
	
	
function getServicetbl() {
	  // alert("hii changed");
	   $.ajax({
		    type        : 'POST',
		    data: {
		        id: '1',
		        },
		    url         :  "<s:url action='GetServiceTable'/>",
		   
		    success: function(data){
		    	//alert(data);
		    	
		    		$('#serviceTypeTbl').html(data);

		    },
		    error : function(xhr, errmsg) {
		    	alert("No detail found");
		    	}
		});
	};
	
	
	
	function getRoadType() {
		  // alert("hii changed");
		   $.ajax({
			    type        : 'POST',
			    data: {
			        id: '1',
			        },
			    url         :  "<s:url action='GetRoadType'/>",
			   
			    success: function(data){
			    	$('#road').val(data);

			    },
			    error : function(xhr, errmsg) {
			    	//alert("Session out error..Please login again");
			    	}
			});
		};
</script>



<script>

function getStops(){	
	//alert("hiiii");
	var uniqueNames = [];
	var names=idval.split(",");
	$.each(names, function(i, el){
	    if($.inArray(el, uniqueNames) === -1) uniqueNames.push(el);
	});
	idval=uniqueNames.join();
	
	var uniqueNames = [];
	var names=idvalrelo.split(",");
	$.each(names, function(i, el){
	    if($.inArray(el, uniqueNames) === -1) uniqueNames.push(el);
	});
	idvalrelo=uniqueNames.join();
	
	
	$.ajax({
	    type        : 'POST',
	    data: {
	        lat: '12',
	        lng: '77',
	        
	    },
	    url         :  "<s:url action='GetRouteStopMap'/>",
	   
	    success: function(data){
	        //alert('successful'+data);
	        result=data;
	        var zoom="8";
	        var clat= '12.97928309';
	        var clang=	'77.57205963';
	        var center = clat+","+clang;
	        var locations=result.split("@@@");
	        /* for (i = 0; i < locations.length-1; i++) {
	        	var dloc=locations[i].split("|");
	        	 for(var v=0;v<dloc.length;v++){
	        		 if(v==(dloc.length-1)){
	        			 idval=idval+",'"+dloc[3]+"'";
	        			 idvalrelo=idvalrelo+",'"+dloc[3]+"'";
	        		 }else{
	        			 idval=idval+",'"+dloc[3]+"'";
	        			 idvalrelo=idvalrelo+",'"+dloc[3]+"'";
	        		 }
	        		// idval=idval+"'"+dloc[3]+"',"
	        	 }
	        } */
	        <%
	       // session.removeAttribute("centerlate");
	       // session.removeAttribute("centerlange");
	        %> 
	        //alert(clat+"===="+clang);
	        /* initialize(result,center,13); */
	        $('#copyroutebutton').click();
	    },
	    error : function(xhr, errmsg) {
	    	//alert("Session out error..Please login again");
	    	}
	});
}
var draglat;var draglng;
function getStopsReLoad(lat,lng){
	var uniqueNames = [];
	var names=idval.split(",");
	$.each(names, function(i, el){
	    if($.inArray(el, uniqueNames) === -1) uniqueNames.push(el);
	});
	idval=uniqueNames.join();
	
	
	var uniqueNames = [];
	var names=idvalrelo.split(",");
	$.each(names, function(i, el){
	    if($.inArray(el, uniqueNames) === -1) uniqueNames.push(el);
	});
	idvalrelo=uniqueNames.join();
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
	    '&idvald='+ idvalrelo,
	    url         :  "<s:url action='GetBusStopReloadRoute'/>",
	   
	    success: function(data){
	       // alert('successful'+data);
	        result=data;
	        var zoom="15";
	        var clat= '<%= request.getSession().getAttribute("centerlat") %>';
	        var clang=	'<%= request.getSession().getAttribute("centerlang") %>';
	        if(draglat!="" && draglat!= null){
	        	var center = draglat+","+draglng;	
	        }else{
	        	var center = "12.97928309"+","+"77.57205963";
	        }
	        
	        //idvald=
	        <%
	        //session.removeAttribute("centerlate");
	        //session.removeAttribute("centerlange");
	        %> 
	        //alert(clat+"===="+clang);
	        var locations=result.split("@@@");
	        /* for (i = 0; i < locations.length-1; i++) {
	        	var dloc=locations[i].split("|");
	        	 for(var v=0;v<dloc.length;v++){
	        		 if(v==(dloc.length-1)){
	        			 idval=idval+",'"+dloc[3]+"'";
	        		 }else{
	        			 idval=idval+",'"+dloc[3]+"'";
	        		 }
	        		// idval=idval+"'"+dloc[3]+"',"
	        	 }
	        } */
	        //alert(idval);
	        initialize(result,center,13);
	    },
	    error : function(xhr, errmsg) {
	    	//alert("Session out error..Please login again");
	    	}
	});
}

function getStopsLoad(lat,lng){
	draglat=lat;draglng=lng;
	var uniqueNames = [];
	var names=idval.split(",");
	$.each(names, function(i, el){
	    if($.inArray(el, uniqueNames) === -1) uniqueNames.push(el);
	});
	idval=uniqueNames.join();
	
	var uniqueNames = [];
	var names=idvalrelo.split(",");
	$.each(names, function(i, el){
	    if($.inArray(el, uniqueNames) === -1) uniqueNames.push(el);
	});
	idvalrelo=uniqueNames.join();
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
	    '&idvald='+ idval,
	    url         :  "<s:url action='GetBusStopRoute'/>",
	   
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
	        			 idvalrelo=idvalrelo+",'"+dloc[3]+"'";
	        		 }else{
	        			 idval=idval+",'"+dloc[3]+"'";
	        			 idvalrelo=idvalrelo+",'"+dloc[3]+"'";
	        		 }
	        		// idval=idval+"'"+dloc[3]+"',"
	        	 }
	        }
	        //alert(idval);
	        initialize(result,center,13);
	    },
	    error : function(xhr, errmsg) {
	    	//alert("Session out error..Please login again");
	    	}
	});
}


jQuery(document).ready(function() {
	getStops();
	
	$('#done').prop("disabled", false); 
	$('#create').prop("disabled", true); 
	

});

function unable_done(){
	$('#done').prop("disabled", true); 
	$('#create').prop("disabled", false);
}


function submitAjaxForm(){
	$('#done').prop("disabled", true);
	/*  var busstoparr = [];
	 $(".busstopid").each(function() {
		 alert("hii");
		 busstoparr.push($(this).html());
		});
	 alert("busstoparr"+busstoparr); */
	
	var route_number=$('#route_number').val();
	if(route_number.trim()==""){
		alert("Please Enter Route No");
		$('#done').prop("disabled", false);
		return false;
	}
	if(parseInt($('#counter').val())<2){
		alert("Route Selection is not proper");
		$('#done').prop("disabled", false);
		return false;
	}
	
	
	var start_point_id=$('#start_point_id').val();
	var end_point_id=$('#end_point_id').val();
	var route_type_id=$('#route_type_id').val();
	var route_name=$('#route_name').val();
	var effect_start_date_route=$('#effect_start_date_route').val();
	var effect_end_date_route=$('#effect_end_date_route').val();
	var route_description=$('#route_description').val();
	if(effect_start_date_route!=""){
		effect_start_date_route=effect_start_date_route.split("/");
		effect_start_date_route=effect_start_date_route[2]+"-"+effect_start_date_route[1]+"-"+effect_start_date_route[0];
	}
	if(effect_end_date_route!=""){
		effect_end_date_route=effect_end_date_route.split("/");
		effect_end_date_route=effect_end_date_route[2]+"-"+effect_end_date_route[1]+"-"+effect_end_date_route[0];
	}
	 
	 if(effect_start_date_route!="" && effect_end_date_route!=""){
		 var d1 = Date.parse(effect_start_date_route);
		 var d2 = Date.parse(effect_end_date_route);
		 
		
		 
		 if (d1 > d2) {
	     alert ("Effective End date should be greater than Effective Start Date");
	     $('#done').prop("disabled", false);
	     return false;
	 }
	 }
	 
	 if(effect_start_date_route!=""){
		 var d1 = Date.parse(effect_start_date_route);
		 d = new Date();
			
		 var d3=Date.parse(d.yyyymmdd());
		 if(d1<d3){
			 alert ("Effective Start date should be greater than or Equal to Today's Date");
			 $('#done').prop("disabled", false);
			 return false;
		 }
		}
	 
	if(effect_start_date_route==""){
		d = new Date();
		effect_start_date_route=d.yyyymmdd();
	}
	if(effect_end_date_route==""){
		effect_end_date_route="0000-00-00";
	}
	
	
	/* if(route_name.match(/[^a-z\d]+/)) {
	    alert("Please Enter Proper Route no.");
	    return false;
	} */
	 if (/^[a-zA-Z0-9- ]*$/.test(route_number) == false) {
		 alert("Please Enter Proper Route no.");
		 $('#done').prop("disabled", false);
		 return false;
		 }
	var status="Active";
	var via=$('#via').val();
	var deleted_status="0";
	var route_direction=$('#route_direction').val();
	var route_string=$('#route_string').val();
	
	if(!spclchar(via)){
		alert("Special Characters Are not allowed in Via Field");
		$('#done').prop("disabled", false);
		return false;
	}
	if(!spclchar(route_description)){
		alert("Special Characters Are not allowed in Route Description Field");
		$('#done').prop("disabled", false);
		return false;
	}
	
	var busstopid_P=[];
	var fare_P=[];
	var substage_P=[];
	var status_P=[];
	var start_bus_stop_id_M=[];
	var end_bus_stop_id_M=[];
	var road_type_M=[];
	var google_distance_M=[];	
	var vtu_distance_M=[];	
	var distance_M=[];	
	var google_time_M=[];	
	var vtu_time_M=[];	
	var time_to_travel_M=[];	
	var status_M=[]; 
	var path_M=[];	
	var route_type_M=[];
	var schedule_time_M=[];
	var schedule_distance_M=[];
	var edit_path_M=[];
	var busstopname=[];
	
	
	
	var depotpl=[];
	var floorpl=[];
	var baypl=[];
	var platformpl=[];
	var servicepl=[];
	var busplatformidpl=[];
	
	var full_busstopname=[];
	$(".busstopname").each(function() {
		
		full_busstopname.push($(this).html());
		
		});
	
	
	var verno=$('#counter').val();
	var maxval=parseInt($('#counter').val())+1;
	/* if((parseInt(start_point_id)!= $('#busstopid1').html() )|| (parseInt(end_point_id)!= $('#busstopid'+verno).html())){
		//alert((parseInt(start_point_id)+"========"+$('#busstopid1').html() )+"+++++++++++"+(parseInt(end_point_id)+"======"+$('#busstopid'+verno).html()));
		alert("Bus Stops Selection is not proper");
		return false;
	} */
	var delno=$('#skipval').val();
	//alert(delno);
	delno=delno.split(",");
	//alert(delno);
	if(delno.length==0){
		delno.push(0);
	}
	var skip;
	var busstopid_P1=[];
	$(".busstopid").each(function() {
		// alert("hii");
		 busstopid_P.push($(this).html().replace('uniquebusstop', '').replace('uniquebusstop', ''));
		 busstopid_P1.push($(this).html().replace('uniquebusstop', '').replace('uniquebusstop', ''));
		});
	
	$(".busstopname").each(function() {
		// alert("hii");
		 busstopname.push($(this).html());
		 
		});
	//console.log(busstopname.length+"=>"+busstopname);
	//sreturn false;
	//alert("1111111111"+busstopid_P);
	var sorted_arr = busstopid_P1.sort(); // You can define the comparing function here. 
    // JS by default uses a crappy string compare.
	//var results = [];
	//alert("2222222"+busstopid_P);
	for (var i = 0; i < sorted_arr.length - 1; i++) {
		if (sorted_arr[i + 1] == sorted_arr[i]) {
			alert("Duplicate Bus Stop "+sorted_arr[i]+" In Route Creation");
			$('#done').prop("disabled", false);
			return false;
		}
	}

	
	$(".fare").each(function() {
		// alert("hii");
		/* if($('#fare'+j).attr('checked')){ */
			if($(this).is(':checked')){
				 fare_P.push("Y");
			}else{
				fare_P.push("N");	
				}
			
		
		});
	
	$(".substage").each(function() {
		// alert("hii");
		if($(this).is(':checked')){
			substage_P.push("Y");
		}else{
			substage_P.push("N");	
			}
		 /* substage_P.push($(this).html()); */
		});
	
	$(".status").each(function() {
		// alert("hii");
		 status_P.push($(this).val());
		});
	/* for(var j=1;j<=$('#counter').val();j++){
		skip=0;
		for(var m=0;m<delno.length;m++){
			if(j==delno[m]){
				skip=1;
			}
		}
		if(skip==0){
			
		
		busstopid_P.push($('#busstopid'+j).html());
		if($('#fare'+j).attr('checked')){
			fare_P.push("Y");
		}else{
			fare_P.push("N");
		}
		if($('#substage'+j).attr('checked')){
			substage_P.push("Y");
		}else{
			substage_P.push("N");
		}
		status_P.push($('#status'+j).val());
		
		
		//var tempstr="";
		//tempstr=$('#depotp'+j).val();
		///var tempstr
		//tempstr="depotp"+j;
		//alert(tempstr);
		//depotpl.push(document.getElementById(tempstr).value.replace(",", "@"));
		//depotpl.push($('#'+tempstr).val().replace(",", "@"));
		
		
		
		}
	} */
	//alert("$('#platformcounter').val()"+$('#platformcounter').val());
	if($('#platformcounter').val()!=""){
	for(var n=0;n<=$('#platformcounter').val();n++){
		//alert(n);
		depotpl.push(($('#depotp'+n).val()));
		floorpl.push(($('#floorp'+n).val()));
		baypl.push(($('#bayp'+n).val()));
		platformpl.push(($('#platformp'+n).val()));
		busplatformidpl.push(($('#busplatformid'+n).val()));
		servicepl.push(($('#servicep'+n).val()).replace(/,/g , "@")); 
	}
	}
	//alert(maxval);
	$(".start_bus_stop_id").each(function() {
		 start_bus_stop_id_M.push($(this).val());
		});
	
	$(".end_bus_stop_id").each(function() {
		end_bus_stop_id_M.push($(this).val());
		});
	if(start_bus_stop_id_M.length==0){
		alert("Route Creation is not Proper");
		$('#done').prop("disabled", false);
		return false;	
		}
	//alert(start_bus_stop_id_M+"=========="+end_bus_stop_id_M);
	var start_bus_stop_name="";
	var end_bus_stop_name="";
	
		start_bus_stop_name=busstopname[0];
		end_bus_stop_name=busstopname[busstopname.length-1];
	
//	alert(full_busstopname);
	
	for(var v=0;v<start_bus_stop_id_M.length;v++){
		//alert("start"+start_bus_stop_id_M[0]+"==============="+start_point_id);
		/* if(start_bus_stop_id_M[0]!=start_point_id){
			alert("Start Point Selection is not Proper");
			return false;
		} */
		start_point_id=start_bus_stop_id_M[0];
		//start_bus_stop_name=busstopname[0];
		//alert("End"+end_bus_stop_id_M[start_bus_stop_id_M.length-1]+"==============="+end_point_id);
		/* if(end_bus_stop_id_M[start_bus_stop_id_M.length-1]!=end_point_id){
			alert("End Point Selection is not Proper");
			return false;
		} */
		end_point_id=end_bus_stop_id_M[start_bus_stop_id_M.length-1];
		//end_bus_stop_name=busstopname[start_bus_stop_id_M.length-2];
		if(start_bus_stop_id_M.length>1){
		//alert(start_bus_stop_id_M[v+1]+"=========="+end_bus_stop_id_M[v]);
		if(v<start_bus_stop_id_M.length-1){
		if(start_bus_stop_id_M[v+1]!=end_bus_stop_id_M[v]){
			alert("Bus Stop Linking Is not Proper At "+full_busstopname[v+1]);
			$('#done').prop("disabled", false);
			return false;
		}
		}
		}
	}
	
	
	route_name=start_bus_stop_name+" To "+end_bus_stop_name;
	//console.log("route_name"+route_name);
	//return false;
	$(".road_type").each(function() {
		road_type_M.push($(this).val());
		});
	
	$(".tdg").each(function() {
		google_distance_M.push($(this).html());
		});
	
	$(".tdv").each(function() {
		vtu_distance_M.push($(this).html());
		});
	var cm=0;
	 $(".tdg").each(function() {
		distance_M.push(parseInt($('.tdg['+cm+']').html())+parseInt($('.tdv['+cm+']').html()));
		cm++;
		}); 
	 var temptime=[];
	 $(".ts").each(function() {
		// alert($(this).html());

		 temptime.push($(this).html());
			
			}); 
	// alert("hii"+temptime+"aaa"+temptime[0]+"hii"+temptime[1]+"hii"+temptime[2]);//return false;
	 var cm=0;
	 $(".route_type").each(function() {
		//google_time_M.push($(this).val());
		//alert("hiiiiii====="+$(this).val());
		 if($(this).val()=='GOOGLE'){
			//alert(temptime[cm]+"========="+temptime[1]);
				google_time_M.push(temptime[cm]);	
			}else{
				google_time_M.push(0);	
			}
		cm++;
		}); 
	// return false;
	 var cm=0;
	 $(".route_type").each(function() {
		//google_time_M.push($(this).val());
		 if($(this).val()=='TRIMAX'){
			 vtu_time_M.push(temptime[cm]);	
			}else{
				vtu_time_M.push(0);	
			}
		cm++;
		}); 
	
	$(".tt").each(function() {
		time_to_travel_M.push($(this).html());
		});
	
	$(".start_bus_stop_id").each(function() {
		status_M.push("Active");
		});
	
	$(".polyp").each(function() {
		path_M.push($(this).val());
		});
	
	$(".route_type").each(function() {
		route_type_M.push($(this).val());
		});
	
	 $(".tts").each(function() {
		 if($(this).val()!=""){
			 schedule_time_M.push($(this).val());
			}else{
				schedule_time_M.push(0);
			}
	
		});
	 
	 for(var v=0;v<schedule_time_M.length;v++){
		try{
			console.log(schedule_time_M[v]);
			parseInt(schedule_time_M[v])
			if(isNaN(schedule_time_M[v])){
				alert("Please Enter Numeric Schedule Time In Minutes");
				$('#done').prop("disabled", false);
				return false;
			}
			}catch(err){
				alert("Please Enter Numeric Schedule Time In Minutes");
				$('#done').prop("disabled", false);
				return false;
			}
		 }
	
	 
	
	$(".tds").each(function() {
		if($(this).val()!=""){
			schedule_distance_M.push($(this).val());
		}else{
			schedule_distance_M.push(0.0);
		}
			
		
		}); 
	
	for(var w=0;w<schedule_distance_M.length;w++){
		try{
			console.log(schedule_distance_M[w]);
			parseFloat(schedule_distance_M[w])
			if(isNaN(schedule_distance_M[w])){
				alert("Please Enter Numeric Schedule Distance In KM");
				$('#done').prop("disabled", false);
				return false;
			}
			}catch(err){
				alert("Please Enter Decimal Schedule Distance In KM");
				$('#done').prop("disabled", false);
				return false;
			}
		 }
		
	$(".editpath").each(function() {
		edit_path_M.push($(this).val());
			});	
		
	/* for(var k=2;k<=(maxval);k++){
		//alert(k);
		skip=0;
		for(var m=0;m<delno.length;m++){
			if(k==(parseInt(delno[m]))){
				skip=1;
			}
		}
		if(skip==0){
		start_bus_stop_id_M.push($('#start_bus_stop_id'+k).val());
		end_bus_stop_id_M.push($('#end_bus_stop_id'+k).val());
		road_type_M.push($('#road_type'+k).val());
		google_distance_M.push($('#tdg'+k).html());	
		vtu_distance_M.push($('#tdv'+k).html());	
		distance_M.push(parseInt($('#tdg'+k).val())+parseInt($('#tdv'+k).val()));	
		if($('#route_type'+k).val()=='GOOGLE'){
			google_time_M.push($('#ts'+k).html());	
		}else{
			google_time_M.push('0');	
		}
		if($('#route_type'+k).val()=='TRIMAX'){
			vtu_time_M.push($('#ts'+k).html());	
		}else{
			vtu_time_M.push('0');	
		}
		//google_time_M.push($('#ts'+k).html());	
		//vtu_time_M.push($('#ts'+k).html());	
		time_to_travel_M.push($('#ts'+k).val());	
		status_M.push('Active'); 
		path_M.push($('#polyp'+k).val());	
		route_type_M.push($('#route_type'+k).val());
		//schedule_time_M.push($('#tds'+k).val());
		if($('#tds'+k).val()!=""){
			 schedule_time_M.push($('#tds'+k).val());

		 }else{
			schedule_time_M.push("0");
		}
		if($('#tts'+k).val()!=""){
			schedule_distance_M.push($('#tts'+k).val());
		}else{
			schedule_distance_M.push("0.0");
		}
		
		
			edit_path_M.push($('#editpath'+k).val());
		
		
		
		
		
		}
	} */
	
	
	
	//alert("hiiii");
	
$.ajax({
    type        : 'POST',
    data: 'route_number='+ route_number+	
    '&start_point_id='+ start_point_id+
    '&end_point_id='+ end_point_id+
    '&route_type_id='+ route_type_id+
    '&route_name='+ route_name+
    '&status='+ status+
    '&via='+ via+
    '&deleted_status='+ deleted_status+
    '&route_direction='+ route_direction+
    '&polypath='+ polypath+
    '&route_string='+ route_string +
    '&effect_start_date_route='+ effect_start_date_route +
    '&effect_end_date_route='+ effect_end_date_route +
    '&route_description='+ route_description +
    
    '&busstopid_P='+ busstopid_P+
    '&fare_P='+ fare_P+
    '&status_P='+ status_P+
    '&substage_P='+ substage_P+
    
    '&start_bus_stop_id_M='+ start_bus_stop_id_M+
    '&end_bus_stop_id_M='+ end_bus_stop_id_M+
    '&road_type_M='+ road_type_M+
    '&google_distance_M='+ google_distance_M+
    '&vtu_distance_M='+ vtu_distance_M+
    '&distance_M='+ distance_M+
    '&google_time_M='+ google_time_M+
    '&vtu_time_M='+ vtu_time_M+
    '&time_to_travel_M='+ time_to_travel_M+
    '&status_M='+ status_M+
    '&path_M='+ path_M+
    '&route_type_M='+ route_type_M+
    '&schedule_time_M='+ schedule_time_M+
    '&schedule_distance_M='+ schedule_distance_M+
    '&edit_path_M='+ edit_path_M+
    
    
    '&depotpl='+ depotpl+
    '&floorpl='+ floorpl+
    '&baypl='+ baypl+
    '&platformpl='+ platformpl+
    '&servicepl='+ servicepl+
    '&busplatformidpl='+busplatformidpl
	,
	
	

    url         :  "<s:url action='SaveRoute'/>",
   
    success: function(data){
        
    	//document.getElementById("errmsg").innerHTML=data;
    	var result=data.split("@");
    	
    	//alert(result)
    	if(result[1]=="Route Created Successfully"){
    		alert("Route "+$('#route_number').val()+" Created Successfully");
    		getServicetbl();
    		$('#route_id').val(result[3]);
    		//alert(result[3]);
    		document.getElementById("farechart").click();
    		
    		//location.reload();
    	}else{
    		alert(result[1]);
    		$('#done').prop("disabled", false);
    	}
        
    },
    error : function(xhr, errmsg) {
    	alert("Your Login Session Has Expired. Please Login Again.");
    	$('#done').prop("disabled", false);
    	}
});
}
//$("#myModal11").css("display", "block");



function getStopsForRoute(bussops){	
	//alert("hiiii");
	 var uniqueNames = [];
	var names=idval.split(",");
	$.each(names, function(i, el){
	    if($.inArray(el, uniqueNames) === -1) uniqueNames.push(el);
	});
	idval=uniqueNames.join(); 
	$.ajax({
	    type        : 'POST',
	    data: {
	        lat: '12',
	        lng: '77',
	        bussopslist:bussops,
	        
	    },
	    url         :  "<s:url action='GetRouteStopMapForRoute'/>",
	   
	    success: function(data){
	        //alert('successful'+data);
	        result=data;
	        var zoom="8";
	        var clat= '12.97928309';
	        var clang=	'77.57205963';
	        var center = clat+","+clang;
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
	        <%
	       // session.removeAttribute("centerlate");
	       // session.removeAttribute("centerlange");
	        %> 
	        //alert(clat+"===="+clang);
	        initialize(result,center,13,"col");
	       // getStopsLoad('12.97928309','77.57205963');
	        //getRoute();
	    },
	    error : function(xhr, errmsg) {
	    	//alert("Session out error..Please login again");
	    	}
	});
}
</script>


<!--**********************************
**********************************--->

 <div style="display: none;" id="myModal11" class="modal fade"
	tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
	aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button> -->
				<h4 class="modal-title">Fare Chart</h4>
			</div>
			<div class="modal-body">
				<p>
				<div class="portlet box purple ">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i>Create Fare Chart
						</div>
						
					</div>
					<div class="alert alert-danger" >
					<!-- <button class="close" data-close="alert"></button> -->
					<span id="errmsg"> </span>
			
		</div>
					<div class="portlet-body form">
<form name="myform" action="createFarechartRoute.action" class="form-horizontal" method="post" >
								<div class="form-body">
									<div class="form-group">
										<!-- <label class="col-md-3 control-label" 
										>Route No:<font color="red">*</font></label> -->

										<div class="col-md-4">
										<input class="form-control input-sm"
											placeholder="" type="hidden" id="route_id"
											name="fareChartMaster.route.route_id" maxlength="80">																
											
										</div>
									</div>
								</div>
								
								
								
								<div class="form-group">
								<label class="col-md-3 control-label">Fare Chart Name:<font color="red">*</font></label>
								<div class="col-md-4">
									<input type="text" class="form-control" name="fareChartMaster.farechart_name" id="farechart_name"
										value="" >
									
								</div>
							    </div>
							    
								<div class="form-group">
									
									<div class="col-md-12">
									<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
										<a href="#collapse_3_6" data-parent="#accordion3" data-toggle="collapse" class="accordion-toggle accordion-toggle-styled collapsed">
										Service Type Selection <font color="red">*</font></a>
										</h4>
									</div>
									<div class="panel-collapse collapse" id="collapse_3_6" style="height: 0px;">
										<div class="panel-body">
											<div class="form-body">
											<div class="table-responsive" style="color:#000000;overflow:auto;">
											<table class="table table-hover" id="serviceTypeTbl">
											</table>
											
											
									</div>
								</div>
								</div>
								</div>
								</div>
								</div>
								</div>
								
								
								
								
							
								<%-- <div class="form-group">
									<label class="control-label col-md-3">Effective Start Date:<font color="red">*</font></label>
									<div class="col-md-4">
										<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd/mm/yyyy"
															data-date-viewmode="years">
											<input type="text" size="16" readonly class="form-control"
												id="effect_start_date"
												name="fareChartMaster.effect_start_date"> <span
												class="input-group-btn" >
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>						
										</div>
										<s:if test="fieldErrors.get('effect_start_date').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('effect_start_date').get(0)" /></span>
											</s:if>
										<!-- /input-group -->
									</div>
								</div> --%>
								<%-- <div class="form-group">
									<label class="control-label col-md-3">Effective End Date:</label>
									<div class="col-md-4">
										<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd/mm/yyyy"
															data-date-viewmode="years">
											<input type="text" size="16" readonly class="form-control"
												id="effect_end_date" name="fareChartMaster.effect_end_date">
											<span class="input-group-btn">
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
										</div>
										<!-- /input-group -->
									</div>
								</div> --%>
								
								<div class="form-group">
								<label class="col-md-3 control-label">Ceiling Fare:</label>
								<div class="col-md-4">
									<input type="text" class="form-control" name="fareChartMaster.ceiling_fare" maxlength="8" id="ceiling_fare"
										value="0" onKeyUp="isInteger(this)">
									<s:if test="fieldErrors.get('ceiling_fare').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('ceiling_fare').get(0)" /></span>
									</s:if>
								</div>
							    </div>
								
								

								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Night Service:</label>
										<div class="col-md-3">
<s:select list="nightServiceMap" id="nignt_service" name="fareChartMaster.nignt_service" 
		cssClass="select2_category form-control"></s:select>
											
										</div>
									</div>
									
								</div>
								
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Peak Hours:</label>
										<div class="col-md-3">
<s:select list="peakHoursMap" id="peak_time" name="fareChartMaster.peak_time" 
		cssClass="select2_category form-control" ></s:select>										
											
										</div>
									</div>
									
								</div>
								
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Flexi Fare:</label>
										<div class="col-md-3">
<s:select list="peakHoursMap" id="flexi_fare" name="fareChartMaster.flexi_fare" 
		cssClass="select2_category form-control" ></s:select>										
											
										</div>
									</div>
									
								</div>
								
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="button" class="btn blue" onclick="submitFare();" id="subfarechart">Save Changes</button>
										<button type="reset" id="resetbtn" class="btn grey" onclick="resetform()">Reset</button>
										<!-- <button type="button" class="btn default" onclick = "javascript:location.href='ShowFareChartAction.action';">Cancel</button> -->
										<a href="ShowRoute.action" class="btn grey"  id="cancel"> <!-- //onclick="callEdit()" -->
								 Cancel </a>
										
									</div>
								</div>

								

							</form>
</div>
				</div>
				</p>
			</div>
			
		</div>
	</div>
</div> 


<!--**********************************
**********************************--->

<script>
document.getElementById("farechart").style.display = "none";

 function getRouteNo(){
	var len= document.getElementById('route_id').options.length;
	
	 if(len<=1 ) {
         $.ajax({
             type: "get",
             url: '<%=request.getContextPath()%>/FareChartajaxAction',
             success: function(result) {
                 document.getElementById('route_id').innerHTML=result;
             }
         })
	 }
 }
function getServiceId(){
	var len= document.getElementById('service_type_id').options.length;

	 if(len<=1 ) {
         $.ajax({
             type: "get",
             url: '<%=request.getContextPath()%>/RateMasterAction!getServiceTypes',
             success: function(result) {
                 document.getElementById('service_type_id').innerHTML=result;
             }
         })
	 }
 }
function getpassengerId(){
	var len= document.getElementById('passenger_type_id').options.length;

	 if(len<=1 ) {
         $.ajax({
             type: "get",
             url: '<%=request.getContextPath()%>/FareChartajaxAction!getPassengerTypes',
             success: function(result) {
                 document.getElementById('passenger_type_id').innerHTML=result;
             }
         })
	 }
 }
 
function getFareMaster(){
	var val= document.getElementById('service_type_id').value;

         $.ajax({
             type: "get",
             url: '<%=request.getContextPath()%>/FareChartAction!getRateMasterVersion?id='+val,
             success: function(result) {
                 document.getElementById('ratemaster').innerHTML=result;
             }
         })
	
 }
 
function getFareMasterOnClick(){
	var val= document.getElementById('service_type_id').value;
	var len= document.getElementById('ratemaster').options.length;

	 if(len<=1 ) {
         $.ajax({
             type: "get",
             url: '<%=request.getContextPath()%>/FareChartAction!getRateMasterVersion?id='+val,
             success: function(result) {
                 document.getElementById('ratemaster').innerHTML=result;
             }
         })
	 }
 }
 
 function isInteger(txb){
	 txb.value = txb.value.replace(/[^0-9]/g, "");
 }
 
 function resetform(){
	 //location.reload();
	 document.getElementById('route_id').value="";
	 var element = document.getElementById('route_id');
	    element.index = 0; 
 } 
 
 function submitFare(){
		// alert("hiii");
		$('#subfarechart').prop("disabled", true); 
		 var route_id=$('#route_id').val();
		 var farechart_name=$('#farechart_name').val();
		 if(farechart_name==""){
			 alert("Please Enter Fare Chart Name");
			 $('#subfarechart').prop("disabled", false);
			 return false;
		 }
		 
		 var iChars = "!@$%^&*+=\\\’;,./{}|\":?~";
		 for (var i = 0; i < farechart_name.length; i++) {
		 if (iChars.indexOf(farechart_name.charAt(i)) != -1) {
		 alert("Special Characters Are not allowed in Fare Chart Name");
		 $('#subfarechart').prop("disabled", false);
		 return false;
		     }
		   }
		 
		 if(farechart_name!=""){
			 if(farechart_name.trim()==""){
				 alert("Please Enter Fare Chart Name");
				 $('#subfarechart').prop("disabled", false);
				 return false;
			 }
		 }
		 var effect_start_date=$('#effect_start_date_route').val();
		 var effect_end_date=$('#effect_end_date_route').val();
		 var ceiling_fare=$('#ceiling_fare').val();
		 
		 try{
				//console.log(schedule_time_M[v]);
				parseInt(ceiling_fare)
				if(isNaN(ceiling_fare)){
					alert("Please Enter Numeric Ceiling Fare");
					$('#subfarechart').prop("disabled", false);
					return false;
				}
				}catch(err){
					alert("Please Enter Numeric Ceiling Fare");
					$('#subfarechart').prop("disabled", false);
					return false;
				}
				ceiling_fare=Math.round(ceiling_fare);
		 var nignt_service=$('#nignt_service').val();
		 var peak_time=$('#peak_time').val();
		 var flexi_fare=$('#flexi_fare').val();
		 if(peak_time==""|| peak_time==null){
			 peak_time='N';
		 }
		 if(flexi_fare==""|| flexi_fare==null){
			 flexi_fare='N';
		 }
		 var servicetypeid=$('#servicetypeid').val();
		//alert(effect_start_date+"===="+effect_end_date);
		 if(effect_start_date!=""){
				effect_start_date=effect_start_date.split("/");
				effect_start_date=effect_start_date[2]+"-"+effect_start_date[1]+"-"+effect_start_date[0];
			}
			if(effect_end_date!=""){
				effect_end_date=effect_end_date.split("/");
				effect_end_date=effect_end_date[2]+"-"+effect_end_date[1]+"-"+effect_end_date[0];
			}
			 
			 if(effect_start_date!="" && effect_end_date!=""){
				 var d1 = Date.parse(effect_start_date);
				 var d2 = Date.parse(effect_end_date);
				 
				
				 
				 if (d1 > d2) {
			     alert ("Effective End date should be greater than Effective Start Date");
			     $('#subfarechart').prop("disabled", false);
			     return false;
			 }
			 }
			 
			 if(effect_start_date!=""){
				 var d1 = Date.parse(effect_start_date);
				 d = new Date();
					
				 var d3=Date.parse(d.yyyymmdd());
				 if(d1<d3){
					 alert ("Effective Start date should be greater than or Equal to Today's Date");
					 $('#subfarechart').prop("disabled", false);
				     return false;
				 }
				}
			 
			if(effect_start_date==""){
				d = new Date();
				effect_start_date=d.yyyymmdd();
			}
			/* if(effect_end_date==""){
				effect_end_date="0000-00-00";
			} */
			
		 var i;
		 var j=0;
		 var ins = document.getElementsByName('servicetypeid')
		 // var vals= new Array();
		 var chkArray = [];
		 $(".chk:checked").each(function() {
				chkArray.push($(this).val());
			});
		 var vals;
		 vals = chkArray.join(',');
			if(vals.length > 1){
				//alert("You have selected " + vals);	
			}else{
				alert("Please at least one Service Type");	
				$('#subfarechart').prop("disabled", false);
				return false;
			}
		 /* for(i=0;i<ins.length;i++)
		 {
			 alert(ins[i].id);
			 alert($('#'+ins[i]).is(':checked'));//servicetypeid[]
			 if($('#servicetypeid['+i+']').is(':checked')){
				 vals[j]=ins[i].value;
			 }
		
		 j++;
		 } */
		// alert(vals);
		// return false;
		 //fareChartMaster;
		 if(effect_end_date==""){
			 effect_end_date="";
		 }else{
			 effect_end_date =effect_end_date+" 00:00:00";
		 }
		 
		 
		 $.ajax({
			    type        : 'POST',
			    data:'fareChartMaster.route.route_id='+ route_id+	
			    '&fareChartMaster.farechart_name='+ farechart_name+
			    '&fareChartMaster.effect_start_date='+ effect_start_date+" 00:00:00"+
			    '&fareChartMaster.effect_end_date='+ effect_end_date+
			    '&fareChartMaster.ceiling_fare='+ ceiling_fare+
			    '&fareChartMaster.nignt_service='+ nignt_service+
			    '&fareChartMaster.passenger_type_id='+"2"+
			    '&fareChartMaster.peak_time='+peak_time+
			    '&fareChartMaster.flexi_fare='+flexi_fare+
			    '&vals='+ vals,
			    url         :  "<s:url action='CreateFarechartRoute'/>",
			   
			    success: function(data){

			        result=data;
			       // alert(result);
			      //  alert(result.search("successfully"));
			        if(result.search("successfully")!="-1"){
			        	document.getElementById("cancel").click();
			        }
			        if(result.indexOf('ERROR: ') >= 0){
			        	$('#subfarechart').prop("disabled", false);
			        }
			        alert(result);
			        
			    },
			    error : function(xhr, errmsg) {
			    	$('#subfarechart').prop("disabled", false);
			    	alert("Your Login Session Has Expired. Please Login Again.");
			    	}
			});
		 
	 }
 
 function checkstage(id,type,type2){
	 //alert("yyyyyyyyyy"+$('#'+type+id).is(':checked'));
	 
	 if($('#'+type+id).is(':checked')){
		 //alert("hiii");
		 $('#'+type2+id).prop('checked', false);
	 }
 }
 
 Date.prototype.yyyymmdd = function() {         
     
     var yyyy = this.getFullYear().toString();                                    
     var mm = (this.getMonth()+1).toString(); // getMonth() is zero-based         
     var dd  = this.getDate().toString();             
                         
     return yyyy + '-' + (mm[1]?mm:"0"+mm[0]) + '-' + (dd[1]?dd:"0"+dd[0]);
};

function dbaNameCheck(input) 
{
  if (input.value.length > 0)
   {
    if( input.value.match(/[0-9A-Za-z]|[\`\^\=\"\<\>\|]*/g))   
     {
         input.value = input.value.replace(/[\`\^\=\"\<\>\|]*/g, '');
         input.focus();
     }
   }
}
function spclchar(textval){
	var iChars = "!@$%^&*+=[]\\\’;,./{}|\":?~_";
	 for (var i = 0; i < textval.length; i++) {
	 if (iChars.indexOf(textval.charAt(i)) != -1) {
	 
	
	 return false;
	     }
	   }
	 return true;
}

function AddCopyRoute(id){
	//alert("id======>"+id);
	/* $('#copyroutebusstopid').val(id);
	$.ajax({
	    type        : 'POST',
	    data: {
	        busstopid: id,
	        
	    },
	    url         :  "<s:url action='GetBusStopRouteList'/>",
	   
	    success: function(data){
	    	//alert(data);
	        result=data;
	        $('#copy_route_list').html("");
	        $('#copy_route_list').html(result);
	        $("input:radio[name=routeid]").click(getRouteStops);
	       
	    },
	    error : function(xhr, errmsg) {
	    	//alert("Session out error..Please login again");
	    	}
	}); */
}

jQuery(function ($) {
	//alert("hii1");
   // $("input:radio[name=routeid]").click(getRouteStops);
   getRouteStops();
})

function getRouteStops() {
	//alert("hii");
    //console.log(document.querySelector('input[name="routeid"]:checked').value);
   // var routeid=document.querySelector('input[name="routeid"]:checked').value;
   // var busstopid=$('#copyroutebusstopid').val();
    $('#copyrouteid').val('<%= request.getSession().getAttribute("copyrouteid") %>');
    $('#routeheading').html("Bus Stops List");
    $.ajax({
	    type        : 'POST',
	    data: {
	    	
	        
	    },
	    url         :  "<s:url action='GetRouteBusStopsList'/>",
	   
	    success: function(data){
	    	//alert(data);
	        result=data;
	       
	        $('#copy_route_list').html("");
	        $('#copy_route_list').html(result);
	       // $("input:radio[name=routeid]").click(getRouteStops);
	       
	    },
	    error : function(xhr, errmsg) {
	    	//alert("Session out error..Please login again");
	    	}
	});
}

function getcopyrouteinfo(){
	 
	 var i=0;
	 var endbusstopid;
	 var startbusstopid;
	 $(".selbusstopid").each(function() {
				if($(this).is(':checked')){
					if(i==0){
						startbusstopid=$(this).val();
					}
					
					if(i==1){
						endbusstopid=$(this).val();
					}
				
					
					 i++;
				}
			
			});
	 if(i!=2){
		 alert("Please Select Only Two Bus Stops");
		 return false;
	 }
	 $('#closeroutediv').click();
	//var endbusstopid=document.querySelector('input[name="endbusstopid"]:checked').value;
    //var startbusstopid=$('#copyroutebusstopid').val();
    $("#pageLoader").show();
    $('#loadercontent')
	.html("<img src='assets/images/loader1.gif' align='top' style='margin-left:100px;'/> Please Wait (0%)");
    var routeid=$('#copyrouteid').val();
    $.ajax({
	    type        : 'POST',
	    data: {
	    	routeid: routeid,
	    	startbusstopid: startbusstopid,
	    	endbusstopid: endbusstopid,
	        
	    },
	    url         :  "<s:url action='GetRouteCopyInfo'/>", 
	   
	    success: function(data){
	    	//alert(data);
	        result=data;
	       
	        //$('#copy_route_list').html("");
	       // $('#copy_route_list').html(result);
	        result=JSON.parse(data);
	        //alert(result.route.toSource())
	        var bussopslist="";
	        
	        
	        points=result.routemap;
	        
	        plot_edit_route(data);
	        console.log("result.routepointlen====="+result.routepointlen);
	        for(var i=0;i<result.routepointlen;i++){
	        	bussopslist=bussopslist+result.routepoint[i][1]+",";
	        	if(i==result.routepointlen-1){
 					bussopslist=bussopslist.substring(0, bussopslist.length-1);
 			        getStopsForRoute(bussopslist);
 			       } 
	        }
	       
	       
	    },
	    error : function(xhr, errmsg) {
	    	//alert("Session out error..Please login again");
	    	}
	});

	
}
	
</script>