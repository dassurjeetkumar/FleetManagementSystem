<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
 <script src="assets/vts/js/busBunching.js"></script> 
<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places,drawing,geometry"></script>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css"> 
	<link rel="stylesheet" href="/resources/demos/style.css">

<script src="../../assets/global/plugins/jquery-1.11.0.min.js"
	type="text/javascript"></script>

<script type="text/javascript">

function getRoute() {
	var fromBusStop = $("#frombusStopid").val();	
	var toBusStop = $("#tobusStopid").val();	
	$.ajax({
        type: "get",
        url: '<%=request.getContextPath()%>/getRoutList.action?frombusStopid='+fromBusStop+'&tobusStopid='+toBusStop,
        success: function(result) {
        	//$("#msg").html("");
            document.getElementById('routeListid').innerHTML=result;
            document.getElementById('select2-chosen-1').innerHTML="Select";
        }
    });
	
}
	
</script>
<script>
function getBusStopDetails(id){	
	var result = "";
	var zeroval =0;	
	if(id.length>=0){
		$('#pageLoader').show();
	$.ajax({
	    type        : 'GET',
	    data :'json',	 
	    url         :  "<s:url action='getBusStopListforBunch'/>",
	    data: {
	        id: id
	    },
	    
	    success: function(data){	   
	        data =eval(data);
	        result=data;	      
	        $( "#frombusStopList" ).autocomplete({	        	 
		        	minLength: 0,		        	
		        	source: result,		        	
		        	focus: function( event, ui ) {		        		
		        	$( "#frombusStopList" ).val( ui.item.id );
		         	$( "#frombusStopList" ).val( ui.item.stopName );		        			        	 
		        	return false;
	        	},	        	
	        	select: function( event, ui ) {	        		
	        		$( "#frombusStopid" ).val( ui.item.id );
		        	$( "#frombusStopList" ).val( ui.item.stopName );  	
		           	return false;
	        	}
	        	}).data( "ui-autocomplete" )._renderItem = function( ul, item ) {
		        	return $( "<li>" ).append( "<a>" + item.stopName+ "</a>" )
		        	.appendTo( ul );		        	
		        	};
		        	
		        	$( "#tobusStopList" ).autocomplete({	        	 
			        	minLength: 0,		        	
			        	source: result,		        	
			        	focus: function( event, ui ) {		        		
			        	$( "#tobusStopList" ).val( ui.item.id );
			         	$( "#tobusStopList" ).val( ui.item.stopName );		        			        	 
			        	return false;
		        	},	        	
		        	select: function( event, ui ) {	        		
		        		$( "#tobusStopid" ).val( ui.item.id );
			        	$( "#tobusStopList" ).val( ui.item.stopName );  	
			           	return false;
		        	}
		        	}).data( "ui-autocomplete" )._renderItem = function( ul, item ) {
			        	return $( "<li>" ).append( "<a>" + item.stopName+ "</a>" )
			        	.appendTo( ul );		        	
			        	};
			        	  $('#pageLoader').hide();	    	
	    },
	    select: function (event, ui) {

	        alert("selected!");
	    },
	    change: function (event, ui) {

	        alert("changed!");
	    },
	    /*  error : function(xhr, errmsg) {
	    	alert("No values found..!!"+errmsg);
	    	}   */
	});
}

} 




</script>
</head>

<body onload="initialize()">
	<div class="page-content-wrapper">
		<div class="page-content">
			<div class="row">
				<div class="col-md-6">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						Bus Bunching<small> current status</small>
					</h3>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>

			<div class="col-md-5">
				<div class="portlet-body form">
						<form  class="form-horizontal"
								method="post">
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">From Bus Stop:</label>
										<div class="col-md-7">
											<div id="pageLoader" class="modal fade in" tabindex="-1" role="dialog" aria-labelledby="myModalLabel3" aria-hidden="false">
								<div class="modal-dialog">
									<div class="modal-content">
										
										<div class="modal-body">
											<p>
												<img src="pages/vts/lastsearch.gif" align="top" style="margin-left:80px;"/> 
											</p>
										</div>
										
									</div>
								</div>
							</div>		
									  <input placeholder="Enter Bus Stop to Search" id="frombusStopList" type="text" 
									  class="form-control" name="frombusStopName" maxLength="50"
									  onkeypress="getBusStopDetails(this.value)" onkeydown="getBusStopDetails(this.value)" value="<s:property value="busstops.busStopName"/>">
										<input id="frombusStopid" name="frombusStopid" type="hidden" value="<s:property value='busstops.id'/>">
															
									</div>
										</div>
										</div>
									<div class="form-body">
									<div class="form-group">
										 <label class="col-md-3 control-label">To Bus Stop:</label>
										<div class="col-md-7">
											 <input placeholder="Enter Bus Stop to Search" id="tobusStopList" type="text" 
									  class="form-control" name="tobusStopName" maxLength="50"
									  onkeypress="getBusStopDetails(this.value)" onkeydown="getBusStopDetails(this.value)" value="<s:property value="busstops.busStopName"/>">
										<input id="tobusStopid" name="tobusStopid" type="hidden" value="<s:property value='busstops.id'/>">
										
										</div> 
									</div>
								</div>
								<div class="form-body">
									<div class="form-group">
								<div class="col-md-4">
									 <button class="btn btn-sm yellow filter-submit margin-bottom"
										type="button" value="Submit" name="buttonname" onclick="getRoute()">Search</button> 
										
								</div>																	
									</div>
								</div>
								</form>
								<form class="form-horizontal"
								method="post">
								<div class="form-body">
									<div class="form-group">
									<label class="col-md-3 control-label">Route:</label>
									<div class="col-md-7">
										<%-- <s:select id="routeListid"
											cssClass="select2_category form-control" headerKey="0"
											headerValue="Select" list="listrout" onchange="Route()">
										</s:select> --%>
										<select id="routeListid"   class="select2_category form-control"
							 onchange="Route()">
							 <option value="0">Select</option>
							 </select>
									</div>
									</div>
								</div>
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Distance:</label>
										<div class="col-md-7">
											<input type="text" class="form-control" id="distanceid"
												placeholder="Enter Distance" maxlength="20" name="distance" />

										</div>
									</div>
								</div>
								<div class="form-body">
									<div class="form-group">
								<div class="col-md-4">
									<button class="btn btn-sm yellow filter-submit margin-bottom"
										type="button" value="Submit" name="buttonname" onclick="vehicledetails()">Search</button>
								</div>
								</div>
								</div>
							</form>
							
					</div>
					
					
				<div class="portlet box white">
					<div class="portlet-body form">
						<div class="form-group">
							<div id="route_info" style="overflow: scroll; height: 200px;">
							
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="col-md-7">
				<div class="form-group" id="mapdiv">
					<table class="table table-striped table-bordered table-hover">
						<tr>
							<td align="center" id="route_name_display" width="25%">&nbsp;</td>
							<td align="center" id="msg_schedule" style="" width="25%">&nbsp;</td>
						    <td width="50%">
							 <img src="assets/images/BusIconRedFlag.png" alt="Start Of Route"> Start Of Route 
							 <img src="assets/images/bus-map-icon.png" alt="Bus"> Bus
							 <!-- <img src="" alt="End Of Route"> End Route -->
							</td>
						</tr>
						<tr>
							<td valign="top" colspan="3">
								<div class="portlet-body" style="height: 500px">
									<div id="route_map" class="gmaps"
										style="height: 100%; width: 100%;"></div>
								</div>
							</td>
						</tr>
					</table>
				</div>

			</div>
		</div>
	</div>


</body>
<style>
.ui-autocomplete {
max-height: 100px;
overflow-y: auto;
/* prevent horizontal scrollbar */
overflow-x: hidden;
/* add padding for vertical scrollbar */
    padding-right: 5px;
}
/* IE 6 doesn't support max-height
* we use height instead, but this forces the menu to always be this tall
*/
 html .ui-autocomplete {
height: 100px;
}
</style>

