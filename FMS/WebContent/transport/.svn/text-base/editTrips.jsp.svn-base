
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="cache-control" content="no-cache" />
<title>Edit Trips</title>
<link rel="stylesheet"
	href="assets/admin/pages/css/jquery.autocomplete.css" />
	
	<style type="text/css">
 
	.transparentCover
	{
		display:none;
		position:absolute;
		left:0px;
		top:0px;
		width:100%;
		height:100%;
		background:#000000;
		opacity:0.4;
		filter:alpha(opacity=40);
		-moz-opacity:0.4;
		-khtml-opacity:0.4;
		z-index:100;
	}
 
	.loading
	{
		display:none;
		position: fixed;
  		left: 50%;
   		top: 50%;
    		background-color: white;
    		height: 400px;
    		margin-top: -200px;
    		width: 600px;
    		margin-left: -300px;
		text-align:center;
		background:url('assets/images/299.GIF') no-repeat center 50%;
		z-index:101;
	}

	.loading1
	{
		display:none;
		position: fixed;
  		left: 50%;
   		top: 50%;
    		background-color: white;
    		height: 400px;
    		margin-top: -200px;
    		width: 600px;
    		margin-left: -300px;
		text-align:center;
		background:url('assets/images/2999.GIF') no-repeat center 50%;
		z-index:101;
	}
 
</style>
	
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="assets/admin/pages/scripts/tripsedit.js" type="text/javascript"></script>
	<script>
	function submitform(){
		var tablerows = document.getElementById("triptable1").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
		var myElem = document.getElementById('triptable2');
		
		if(myElem !=null){
			var table2rows =   document.getElementById("triptable2").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
			tablerows = tablerows+table2rows;
			tablerows = parseInt(tablerows)-1;
		}
		//alert(tablerows);
		for(i=0;i<(parseInt(tablerows));i++){
			var validate = true;
			var startpoint = document.getElementById("origin"+i+"A"+i).value;
			var endpoint = document.getElementById("dest"+i+"A"+i).value;
			var route = document.getElementById("route_no"+i).value;
		//alert('startpoint -------> '+startpoint+'  endpoint -----> '+endpoint+'  route ---> '+route+'  || isEmpty(endpoint)  ---> '+isEmpty(endpoint));
			if((endpoint == '0' && route == '0') || (endpoint != '0' && route != '0') || (isEmpty(endpoint) && route == '0') || (!isEmpty(endpoint) && route != '0') ){
				validate = true;
				}
			else{
				alert("Please check the details of trip no. "+(i+1));
				validate = false;
				return false;
			}
			
		}
		if(validate == true){
			$('button').attr("disabled","disabled");
			reCalculateSteeringSpreadhours();
			document.tripform.action = 'saveEditTrips.action';
			 document.tripform.submit();
		} 
	}
	
	function cancel(){
		var sch = document.getElementById('scheduleno').value;
		document.forms[0].action = 'FormFourView.action?sch='+sch;
		 document.forms[0].submit(); 
	}
	
	function run(field) {
	    setTimeout(function() {
	        var regex = /\d*\.?\d?/g;
	        field.value = regex.exec(field.value);
	    }, 0);
	}
	</script>
	<script>
	function isNumber(evt) {
	    evt = (evt) ? evt : window.event;
	    var charCode = (evt.which) ? evt.which : evt.keyCode;
	    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
	        return false;
	    }
	    return true;
	}
	</script>
<script>
var routeArray = new Array();
var cnt = 0;
var cnt1 = 0;

<s:iterator value="tripdetails" var="trips" status="ctr">
var routeno = '<s:property value="#trips.routeNumber.route_id"/>';
routeArray[cnt1] = routeno;
cnt1 = parseInt(cnt1)+1;
</s:iterator>

function getData(id){
	var triptable2row = 0;
	var triptable1row = document.getElementById("triptable1").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
	var myElem = document.getElementById('triptable2');
	if(myElem !=null){
	triptable2row = document.getElementById("triptable2").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
	}
	
	
	var buspoint = document.getElementById('dest'+id+'A'+id).value;
	var elementid = '#origin'+(parseInt(id)+1);
	var value = document.getElementById('dest'+id).value;
	
	if(!isEmpty(value)){
	$.ajax({
        type: "POST",
        url: "GetGroupStops.action",
        data: "buspoint="+buspoint,
        success: function(response){
        	if(response.length == 1){
        		alert('No Bus Stop Grouping Created');
        		
        	}
        	
        	var arr = response.split(',');
        	 var i=0;
        	 $(elementid).empty();
        	$(elementid).append('<option value="0">Select</option>'); 
        	for (i = 0; i < arr.length-1; i++) {
        		var splitresult = arr[i].split('|');
        		if(buspoint == splitresult[0]){
        			$(elementid).append('<option value="'+splitresult[0]+'" selected="selected">'+splitresult[1]+'</option>');
        			if((document.getElementById('origin'+(parseInt(id)+1)+'A'+(parseInt(id)+1)))!=null){
        			document.getElementById('origin'+(parseInt(id)+1)+'A'+(parseInt(id)+1)).value = buspoint;
        			}
        		}
        		else{
        			
        			$(elementid).append('<option value="'+splitresult[0]+'">'+splitresult[1]+'</option>');
        		}
        	}
			cnt = parseInt(cnt)+1;
      		if(cnt<=(parseInt(cnt1)+1)){
      			getData(cnt);
      		}
      		
        }
	});
	}
	
	
	
	
}


$(document).ready(function(){

	getGroupData();
	setTimeout(function() {
		setElements();
    }, 2000);
	getRouteData();
	
	/* $('button').click(function(){ 
		$('button').attr("disabled","disabled");
	}); */
});


/* $(document).ready(function(){
	/* var cnt = 0;
	var cnt1 = 0;
	var triptable2row = 0;
	var triptable1row = document.getElementById("triptable1").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
	var myElem = document.getElementById('triptable2');
	if(myElem !=null){
	triptable2row = document.getElementById("triptable2").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
	}
	
	var routeArray = new Array();
	
	<s:iterator value="tripdetails" var="trips" status="ctr">
	var routeno = '<s:property value="#trips.routeNumber.route_id"/>';
	routeArray[cnt1] = routeno;
	cnt1 = parseInt(cnt1)+1;
	</s:iterator>
	
	
	
	setTimeout(function() {
		setElements();
    }, 5000);
	 
});
 */
function setChange(id,formfourid,schid,nooftrips){
	setTimeout(function() {
		crewChange(id,formfourid,schid,nooftrips);
    }, 100);
	
}
 
 function getRouteData(){
	 var count = 0;
	 <s:iterator value="tripdetails" var="trips" status="ctr">
	 var route = '<s:property value="#trips.routeDetails"/>';
	 var selectedroute = '<s:property value="#trips.routeNumber.route_id"/>';
	 var elementid = '#route_no'+count;
	 var arr = route.split(',');
	 var i=0;
	 $(elementid).empty();
 	$(elementid).append('<option value="0">Select</option>');
 	
 	for (i = 0; i < arr.length-1; i++) {
		var splitresult = arr[i].split('@');
		
		var dates = splitresult[2]+' to '+splitresult[3];
		if(splitresult[0] == selectedroute){
			$(elementid).append('<option value="'+splitresult[0]+'" selected= "selected" class="tooltips" title="'+dates+'">'+splitresult[1]+'</option>');
		}
		else{
			$(elementid).append('<option value="'+splitresult[0]+'" class="tooltips" title="'+dates+'">'+splitresult[1]+'</option>');	
		}
		
	}
 	
	 count = parseInt(count)+1;
	 </s:iterator>
 }
 
 function getGroupData(){
	 var count = 0;
	 
	 <s:iterator value="tripdetails" var="trips" status="ctr">
	 var groupStops = "<s:property value='#trips.groupStops'/>";
	 var selectedstop =  "<s:property value='#trips.groupStartPoint'/>";
	
	 var elementid = '#origin'+(parseInt(count)+1);
	 var arr = groupStops.split(',');
	 var i=0;
	 $(elementid).empty();
	$(elementid).append('<option value="0">Select</option>'); 
	for (i = 0; i < arr.length-1; i++) {
		var splitresult = arr[i].split('|');
		if(selectedstop == splitresult[0]){
			$(elementid).append('<option value="'+splitresult[0]+'" selected="selected">'+splitresult[1]+'</option>');
			}
		else{
			
			$(elementid).append('<option value="'+splitresult[0]+'">'+splitresult[1]+'</option>');
		}
	}
	 
	 
	 count = parseInt(count)+1;
	 </s:iterator>
 }



 function setElements(){
		var myElem = document.getElementById('triptable2');
		var triptable1row = document.getElementById("triptable1").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
		var count = 0;
		<s:iterator value="tripdetails" var="trips" status="ctr">
		var triptype = '<s:property value="#trips.tripType.id"/>';
		document.getElementById('trip'+count).value = triptype;
		
		var customer = '<s:property value="#trips.customer.id"/>';
		document.getElementById('customer'+count).value = customer;
		
		var distance = '<s:property value="#trips.distance"/>';
		document.getElementById('routelength'+count).value = distance;
		
		var breakid = '<s:property value="#trips.breakType.id"/>';
		var duration = '<s:property value="#trips.breakType.duration"/>';
		var steering = '<s:property value="#trips.breakType.steeringHours"/>';
		var spread = '<s:property value="#trips.breakType.spreadHours"/>';
		var rest = '<s:property value="#trips.breakType.rest"/>';
		var othours = '<s:property value="#trips.breakType.ot_hours"/>';
		
		document.getElementById('break'+count).value = duration+'-'+breakid+'-'+steering+'-'+spread+'-'+rest+'-'+othours;
		
		var dutytype = '<s:property value="#trips.shiftType.id"/>';
		document.getElementById('shift'+count).value = dutytype;
		
		var operationType = '<s:property value="#trips.operationType"/>';
		document.getElementById('operationtype'+count).value = operationType;
		
		var deadtripvalue = '<s:property value="#trips.isDreadTrip"/>';
		document.getElementById('deadtripval'+count).value = deadtripvalue;
		count = parseInt(count)+1;
		</s:iterator>
		setKms('triptable1','0','0');
		disableLastRow();
		if(myElem !=null){
		setKms('triptable2',parseInt(triptable1row)-1,'0');
		}
		var  id= '<s:property value="crewChangeId" />';
		if(id!=''){
			var table1rows = document.getElementById("triptable1").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
			var myElem = document.getElementById('triptable2');
			
			
			if(myElem !=null){
				var table2rows = document.getElementById("triptable2").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
				table1rows = parseInt(table1rows)+parseInt(table2rows);
				table1rows = parseInt(table1rows)-1;
			}
			disableCrewChange(id,(parseInt(table1rows)-1));
		}
	}
 
 
</script>
<script>
	var PATH = '<%=request.getContextPath()%>';
	</script>
</head>
<body>
<div class="transparentCover"></div>
 
	<div class="loading"></div>
	<div class="loading1"></div>
<div class="transparentCover"></div>
 
	<div class="loading"></div>
	<div class="loading1"></div>
	<div class="page-content-wrapper">
		<div class="page-content">
		<s:if test="hasActionErrors()">
   <div class="alert alert-danger">
			<button class="close" data-close="alert"></button>
			<span>
			<s:actionerror/> </span>
		</div>
      
   
</s:if>
			<div class="row thumbnails">
				<div class="col-md-4">
					<div class="meet-our-team">
						<div class="team-info" align="center">
						<table >
						<tr><td >
						<b>Service Type:</b>
						<s:property value="service" />
						<input type="hidden" name="" id="service1_id" value="<s:property value="serviceid"/>"/> 
						</td></tr>
						<tr><td>
						<b>Route Number:</b>
							<s:property value="routeNumber" />
						</td></tr>
						<tr><td>
						<b>Schedule Number:</b>
							<s:property value="schedule" />
						</td></tr>
						</table>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="meet-our-team">
						<div class="team-info" align="center">
						<table>
						<tr>
						<td><b>Form Four Name : </b><s:property value="formfourname" /></td>
						</tr>
						<tr>
						<td align="center"><s:property value="routeName" /></td>
						</tr>
						<tr>
						<td><b>Traffic Order No : </b>
						 <s:property value="traffic_order_no" />
						</td></tr>
						</table>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="meet-our-team">

						<div class="team-info" align="center">
							
							<table>
						<tr><td>
						<b>Division</b> : <s:property value="division" />
						<input type="hidden" id="scheduleno"  value="<s:property value="formFour.scheduleNumber.schedule_id" />"/>
						</td></tr>
						<tr><td>
						<b>Depot :</b>
							<s:property value="depot" />

						</td></tr>
						<tr><td>
						<b>Schedule Type</b> : <s:property value="shift" />
						</td></tr>
						<tr><td><b>W.E.F :</b>
							<s:property value="effectstartdate" /></td></tr>
						</table>
						</div>
					</div>
				</div>

			</div>

			<br />
			<form id='tripform' action="saveEditTrips" name='tripform' method="post">
			<div class="portlet box grey-cascade">
				<div class="portlet-title">
					<div class="caption">
					<s:hidden name="startDate"></s:hidden>
					<s:hidden name="endDate"></s:hidden>
						<i class="fa fa-cogs"></i>Trip Details
					</div>
					<div class="actions">
					
							<button type="button" class="btn blue" onclick="submitform();">Save</button>
							<button type="button" class="btn blue" onclick="cancel();">Cancel</button>
					</div>
				</div>
				<div class="portlet-body">
				
					<div class="table-scrollable">
					<input type="hidden" name="formfourID" value="<s:property value="formFour.id" />"/>
						<table class="table table-striped table-bordered table-hover" id="triptable1" style="height: 50px">
						
								<thead>
								<tr bgcolor="#819FF7">
									<th scope="col">#</th>
									<th scope="col">Trip</th>
									<th scope="col"  align="center">Trip</th>
									<th scope="col" align="center">Customer</th>
									<!-- <th scope="col" align="center">Is Dead</th> -->
									<th scope="col" colspan="2" style="align: center">Place</th>
									<th scope="col" align="center">Route</th>
									<th scope="col" align="center">Distance</th>
									<th scope="col" colspan="2" align="center">Timings</th>
									<th scope="col" align="center">Running</th>
									<th scope="col" align="center">Break</th>
									<th scope="col" align="center">Break</th>
									<!-- <th scope="col" align="center">Break</th> -->
									<th scope="col" align="center">Is Crew</th>
									<th scope="col" align="center">Crew Change</th>
									<th scope="col" align="center">Is Night</th>
									<!-- <th scope="col" align="center">Night Halt</th> -->
									<th scope="col" align="center">Duty</th>
									<th scope="col" align="center">Operation</th>
									<th scope="col" align="center">Remarks</th>
									


								</tr>
								<tr bgcolor="#819FF7">
									<th scope="col" align="center"></th>
									<th scope="col" align="center">No.</th>
									<th scope="col" align="center">Type</th>
									<th scope="col" align="center">Name</th>
									<!-- <th scope="col" align="center">Trip</th> -->
									<th scope="col" align="center">Start Point</th>
									<th scope="col" align="center">End Point</th>
									<th scope="col" align="center">Number</th>
									<th scope="col" align="center"></th>
									<th scope="col" align="center">Start Time</th>
									<th scope="col" align="center">End Time</th>
									<th scope="col" align="center">Time</th>
									<th scope="col" align="center">Type</th>
									<th scope="col" align="center">Time</th>
									<!-- <th scope="col" align="center">Location</th> -->
									<th scope="col" align="center">Change</th>
									<th scope="col" align="center">Location</th>
									<th scope="col" align="center">Halt</th>
									<!-- <th scope="col" align="center">Location</th> -->
									<th scope="col" align="center">Type</th>
									<th scope="col" align="center">Type</th>
									<th scope="col" align="center"></th>
									

								</tr>
							</thead>
							<tbody>

	<%
									int i = 0;
								%>
							<%-- <s:hidden name="formfourid" value='<s:property value="formFour.id"/>'></s:hidden> --%>
								<s:set var="crewchange" value="0"></s:set>
								<s:set var="tablechange" value="0"></s:set>
								<s:set var="start" value="0"></s:set>
								<s:set var="breakLoop" value="%{false}" />
								<s:iterator value="tripdetails" var="trips" status="ctr">

									<s:if test="!#breakLoop">
										<tr>
										
											<td align="center">
										
										<input type="hidden" id="traffic_order_no<%=i %>" name="tripdetails[<%=i %>].formFour.traffic_order_no" value="<s:property value="formfour.traffic_order_no" />"/>
<%-- 										<input type="hidden" id="recordDate<%=i %>" name="tripdetails[<%=i %>].formFour.recordDate" value="<s:property value="formfour.recordDate" />"/>	 --%>
											
											 <input type="hidden" id="formFour<%=i%>" name="tripslist[<%=i%>].formFour.id" value="<s:property value="formFour.id" />"/>
										<input type="hidden" id="scheduleid<%=i%>" name="tripslist[<%=i%>].scheduleNumber.schedule_id" value="<s:property value="formFour.scheduleNumber.schedule_id" />"/>
										<input type="hidden" id="noofTrips<%=i%>" name="tripslist[<%=i%>].noofTrips" value="<s:property value="formFour.NoofTrips"/>"/> 
											
										<%-- <input type="hidden" id="scheduleid<%=i %>" name="tripslist[<%=i %>].scheduleNumber.schedule_id" value="<s:property value="formFour.scheduleNumber.schedule_id" />"/>
										<input type="hidden" id="noofTrips<%=i %>" name="tripslist[<%=i %>].noofTrips" value="<s:property value="formFour.NoofTrips"/>"/> --%>
											<input type="hidden" name="tripslist[<%=i%>].id" id="tripid<%=i%>" value='<s:property value="#trips.id"  />'>
											<input type="text"
												class="form-control"
												name="tripslist[<%=i%>].listItemNumber"
												value='<s:property value="listItemNumber" />'
												style="width: 50px" id="listitem<%=i%>" readonly="readonly" /></td>
											<td align="center"> <input
												type="text" class="form-control"
												name="tripslist[<%=i%>].tripNumber"
												value='<s:property value="#trips.tripNumber" />'
												style="width: 50px" id="id<%=i%>" readonly="readonly" /></td>
											<td align="center"><s:select list="tripMap"
													id="%{'trip' + #ctr.index}"
													name="%{'tripslist[' + #ctr.index + '].tripType.id'}"
													cssClass="form-control input-small"
													onchange="selectForCharter(this.id.substring(4));setKms('triptable1','0',%{#ctr.index});getDeadTripStops(this.id.substring(4));"
													theme="simple"></s:select> <%-- <s:property value="#trips.tripType.tripTypeName" /> --%>
													
													<s:hidden id="%{'deadtripval' + #ctr.index}" name="%{'tripslist[' + #ctr.index + '].isDreadTrip'}"></s:hidden>
											</td>

											<td align="center"><s:select list="CustomerMap"
													id="%{'customer' + #ctr.index}"
													name="%{'tripslist[' + #ctr.index + '].customer.id'}"
													cssClass="form-control input-small" theme="simple"></s:select>
												<%-- <s:property value="#trips.customer.name" /> --%></td>

											<%-- <s:if test="#trips.isDreadTrip == 1">
												<td align=""><s:checkbox
														name="%{'tripslist[' + #ctr.index + '].deadTrip'}" onclick="getDeadTripStops(this.id.substring(8));"
														id="%{'deadtrip' + #ctr.index}" fieldValue="true"
														value="false" theme="simple" checked="checked" /> <s:checkbox name="%{'tripslist[' + #ctr.index + '].deadTrip'}" id="%{'deadtrip' + #ctr.index}" fieldValue="true" value="false" onclick="getDeadTripStops(this.id.substring(8));" theme="simple" cssStyle="width: 13px;" checked="checked"/>
												</td>
											</s:if>
											<s:else>
												<td align=""><s:checkbox
														name="%{'tripslist[' + #ctr.index + '].deadTrip'}"
														id="%{'deadtrip' + #ctr.index}" fieldValue="true"
														value="false"
														onclick="getDeadTripStops(this.id.substring(8));"
														theme="simple" cssStyle="width: 13px;" /></td>
											</s:else> --%>
											<%
												if (i == 0) {
											%>
											<td align="center"><input type="text"
												class="form-control" name="tripslist[<%=i%>].origin"
												value='<s:property value="#trips.origin" />'
												style="width: 240px" id="origin<%=i%>"  /> 
											<%
												} else {
											%>
											<td align="center"><select data-placeholder="Select..."
												id="origin<%=i%>" class="form-control input-medium"
												name="tripslist[<%=i%>].origin" style="width: 100px"
												onchange="setOrigin(this.id.substring(6));getDepotsByNo('triptable1','0',this.id.substring(6));">
													<option value="0">Select</option>
											</select>
						
											<%
												}
											%>
											<input
												type="hidden" name="tripslist[<%=i%>].startPoint"
												id="origin<%=i%>A<%=i%>"
												value='<s:property value="#trips.startPoint" />' /></td>
											<td align="center"><input type="text"
												class="form-control" name="tripslist[<%=i%>].destination"
												value='<s:property value="#trips.destination" />'
												style="width: 220px" id="dest<%=i%>" onblur="getDepotsByNo('triptable1','0',this.id.substring(4),'');" /> <%-- <s:property value="#trips.destination" /> --%>
												<input type="hidden" name="tripslist[<%=i%>].endPoint"
												id="dest<%=i%>A<%=i%>"
												value='<s:property value="#trips.endPoint" />' /></td>

											<td><select data-placeholder="Select..."
												id="route_no<%=i%>" class="form-control input-small"
												name="tripslist[<%=i%>].routeNumber.route_id"
												style="width: 100px"
												onchange="getRouteById('triptable1',this.id.substring(8),(parseInt(this.id.substring(8))+1));setroutedistance('triptable1','0',this.id.substring(8));">
													<option value="0">Select</option>
											</select> <input type="hidden" id="selectedRoute<%=i%>"
												name="tripslist[<%=i%>].selectedRoute"
												value='<s:property value="%{tripdetails[#ctr.index].selectedRoute}"/>' />
											</td>

											<td align="center"><input type="text"
												class="form-control" name="tripslist[<%=i%>].distance"
												style="width: 70px" id="routelength<%=i%>" onkeypress="run(this);" onblur="setKms('triptable1','0',<%=i%>);"
												
												value='<s:property value="#trips.distance" />' /> <%-- <s:property value="#trips.distance" /> --%>
											</td>
											<td align="center"><input type="text"
												class="form-control timepicker timepicker-24i"
												name="tripslist[<%=i%>].starttimeString"
												value='<s:property	value="#trips.starttimeString" />'
												style="width: 70px" id="fromtime<%=i%>"
												onblur="validateStartTime('triptable1','0',<%=i%>);checkTime(this);" />
												<%-- <s:property	value="#trips.starttimeString" /> --%></td>
											<td align="center"><input type="text"
												class="form-control timepicker timepicker-24i"
												id="totime<%=i%>" name="tripslist[<%=i%>].endtimeString"
												value='<s:property value="#trips.endtimeString" />'
												style="width: 70px"
												onblur="checkTime(this);validateTime('triptable1','0',this.id.substring(6));" />

											</td>
											<td align="center"><input type="text"
												class="form-control timepicker timepicker-24i"
												readonly="readonly" name="tripslist[<%=i%>].journeyTime"
												value='<s:property value="#trips.journeyTime" />'
												style="width: 70px" id="journeytime<%=i%>"
												onfocus="setJourneyTime(this.id.substring(11));setSpreadHours('triptable1','0',this.id.substring(11));addminstodest('triptable1',this.id.substring(11));">
											</td>
											<td align="center"><s:select list="breakMap"
													id="%{'break' + #ctr.index}"
													name="%{'tripslist[' + #ctr.index + '].breakTypeString'}"
													cssClass="form-control input-small" theme="simple"
													onchange="enablebreakLocation(this.id.substring(5));setFromTime(this.id.substring(5));restforcrew();setJourneyTime(parseInt(this.id.substring(5))+1);setsteeringtime('triptable','0',this.id.substring(5));setSpreadHours('triptable','0',this.id.substring(5));">
												</s:select> <%-- <s:property value="#trips.breakType.breakTypeName" /> --%>
											</td>
											<td align="center"><input type="text"
												class="form-control timepicker-24i"
												value='<s:property value="#trips.breaktimeString" />'
												name="tripslist[<%=i%>].breaktimeString"
												style="width: 70px" id="breakduration<%=i%>"
												onblur="checkTime(this);setFromTime(this.id.substring(13));restforcrew();setJourneyTime(parseInt(this.id.substring(13))+1);setsteeringtime('triptable','0',this.id.substring(13));setSpreadHours('triptable','0',this.id.substring(13));" />
												
											<input type="text"
												class="form-control"
												value='<s:property value="#trips.breakLocation" />'
												name="tripslist[<%=i%>].breakLocation"
												readonly="readonly" id="breaklocation<%=i%>"
												style="width: 90px;display: none;" />	
											</td>
											<%-- <td align="center"><input type="text"
												class="form-control"
												value='<s:property value="#trips.breakLocation" />'
												name="tripslist[<%=i%>].breakLocation"
												readonly="readonly" id="breaklocation<%=i%>"
												style="width: 90px" /></td> --%>

											<s:if test="#trips.crewChange == 1">
												<td align=""><s:checkbox
														name="%{'tripslist[' + #ctr.index + '].crewChangeStatus'}"
														id="%{'crewchange' + #ctr.index}" fieldValue="true"
														value="false"
														onclick='enablecrewLocation(this.id.substring(10));setChange(this.id.substring(10),%{formFour.id},%{formFour.scheduleNumber.schedule_id},%{formFour.NoofTrips})'
														theme="simple" checked="checked" /></td>
												<s:set var="breakLoop" value="%{true}" />
											</s:if>
											<s:else>
												<td align=""><s:checkbox
														name="%{'tripslist[' + #ctr.index + '].crewChangeStatus'}"
														id="%{'crewchange' + #ctr.index}" fieldValue="true"
														value="false"
														onclick='enablecrewLocation(this.id.substring(10));setChange(this.id.substring(10),%{formFour.id},%{formFour.scheduleNumber.schedule_id},%{formFour.NoofTrips})'
														theme="simple" /></td>
												
											</s:else>

											<td align="center"><input type="text"
												class="form-control"
												value='<s:property value="#trips.crewChangeLocation" />'
												name="tripslist[<%=i%>].crewChangeLocation"
												readonly="readonly" id="crewChangelocation<%=i%>"
												style="width: 90px" /></td>

											<s:if test="#trips.nightHalt == 1">
												<td align=""><s:checkbox
														name="%{'tripslist[' + #ctr.index + '].nightTrip'}"
														id="%{'nighttrip' + #ctr.index}" fieldValue="true"
														value="false"
														onclick='enablenightLocation(this.id.substring(9));setChange(this.id.substring(9),%{formFour.id},%{formFour.scheduleNumber.schedule_id},%{formFour.NoofTrips});'
														theme="simple" checked="checked" />
												<input type="text"
												class="form-control"
												value='<s:property value="#trips.nightHaltLocation" />'
												name="tripslist[<%=i%>].nightHaltLocation"
												readonly="readonly" id="nighttriplocation<%=i%>"
												style="width: 90px;display: none;" />		
												<s:set var="breakLoop" value="%{true}" />
														</td>
											</s:if>
											<s:else>
												<td align=""><s:checkbox
														name="%{'tripslist[' + #ctr.index + '].nightTrip'}"
														id="%{'nighttrip' + #ctr.index}" fieldValue="true"
														value="false"
														onclick='enablenightLocation(this.id.substring(9));setChange(this.id.substring(9),%{formFour.id},%{formFour.scheduleNumber.schedule_id},%{formFour.NoofTrips});'
														theme="simple" />
														<input type="text"
												class="form-control"
												value='<s:property value="#trips.nightHaltLocation" />'
												name="tripslist[<%=i%>].nightHaltLocation"
												readonly="readonly" id="nighttriplocation<%=i%>"
												style="width: 90px;display: none;" />
														</td>
											</s:else>
											<s:if test="#trips.crewChange == 1">
											<s:set var="crewchange" value="1" />
											</s:if>
											<s:else>
											<s:if test="#trips.nightHalt == 1">
											<s:set var="crewchange" value="1" />
											</s:if>
											<s:else>
											<s:set var="crewchange" value="0" />
											</s:else>
											</s:else>

											<%-- <td align="center"><input type="text"
												class="form-control"
												value='<s:property value="#trips.nightHaltLocation" />'
												name="tripslist[<%=i%>].nightHaltLocation"
												readonly="readonly" id="nighttriplocation<%=i%>"
												style="width: 90px" /></td> --%>
											<td align="center"><s:select list="shifttypeMap"
													id="%{'shift' + #ctr.index}"
													name="%{'tripslist[' + #ctr.index + '].shiftType.id'}"
													cssClass="form-control input-small"
													onchange="setShiftType(this.id.substring(5))" theme="simple"></s:select>
												<%-- <s:property value="#trips.shiftType.shiftTypeName" /> --%>

											</td>
											<td align="center"><s:select
													list="#{'Obligatory':'Obligatory','Non-Obligatory':'Non-Obligatory'}"
													id="%{'operationtype' + #ctr.index}"
													name="%{'tripslist[' + #ctr.index + '].operationType'}"
													cssClass="form-control input-small" theme="simple"></s:select>
												<%-- <s:property value="#trips.operationType" /> --%></td>
											<td align="center"><input type="text"
												class="form-control" id='remarks<%=i%>'
												name="tripslist[<%=i%>].remarks"
												value='<s:property value="#trips.remarks" />'
												placeholder="Enter text" style="width: 100px" maxlength="99"></td>
										<td>
										<%-- <input type="hidden" name="tripid" id="tripid<%=i%>" value='<s:property value="#trips.id" />'/> --%>
										<%
												if (i == 0) {
											%>
											<a href="javascript:void(0)" class="btn default btn-xs black" id="del<%=i%>"> <i
												class="fa fa-trash-o"></i> Delete</a>
											<%} else { %>
										<a href="javascript:void(0)" class="btn default btn-xs black" id="del<%=i%>" onclick='deleteRow(this,this.id.substring(3),"triptable1",<s:property value="#trips.id" />);restforcrew();'> <i
												class="fa fa-trash-o"></i> Delete</a>
												<%} %>
										 &nbsp; <a href="javascript:void(0)" id="add<%=i%>" class="btn default btn-xs purple" onclick="addtrip(this.id.substring(3),'triptable1','<s:property value="formFour.id" />','<s:property value="formFour.scheduleNumber.schedule_id" />','<s:property value="formFour.NoofTrips"/>');"> <i
												class="fa fa-edit"></i> Add
										</a></td>
										</tr>

										<%
											i = i + 1;
										%>
										<s:set var="start" value="%{ #ctr.index + 1}" />
									</s:if>
								</s:iterator>
								<s:if test="#crewchange == 1">
									<tr id='<s:property value="#start" />'>
										<td colspan='14' align='center'>CREW CHANGE, FUELLING
											MAINTENANCE AT DEPOT</td>
									</tr>
								</s:if>

							</tbody>
						</table >
						
						<s:set var="crewchange" value="0" />
						<div id='table2'>
						<s:if test="crewChangeStatus == 1">
							<table id="triptable2"
								class="table table-striped table-bordered table-hover">
								<tbody>
									<s:iterator value="tripdetails" var="trips" status="ctr">
										
										<s:if test="#crewchange == 1">
										<tr>
											<td align="center">
										
											 <input type="hidden" id="formFour<%=i%>" name="tripslist[<%=i%>].formFour.id" value="<s:property value="formFour.id" />"/>
										<input type="hidden" id="scheduleid<%=i%>" name="tripslist[<%=i%>].scheduleNumber.schedule_id" value="<s:property value="formFour.scheduleNumber.schedule_id" />"/>
										<input type="hidden" id="noofTrips<%=i%>" name="tripslist[<%=i%>].noofTrips" value="<s:property value="formFour.NoofTrips"/>"/> 
											<input type="hidden" name="tripslist[<%=i%>].id" id="tripid<%=i%>" value='<s:property value="#trips.id" />'>
											<input type="text"
												class="form-control"
												name="tripslist[<%=i%>].listItemNumber"
												value='<s:property value="listItemNumber" />'
												style="width: 50px" id="listitem<%=i%>" readonly="readonly" /></td>
											<td align="center"> <input
												type="text" class="form-control"
												name="tripslist[<%=i%>].tripNumber"
												value='<s:property value="#trips.tripNumber" />'
												style="width: 50px" id="id<%=i%>" readonly="readonly" /></td>
											<td align="center"><s:select list="tripMap"
													id="%{'trip' + #ctr.index}"
													name="%{'tripslist[' + #ctr.index + '].tripType.id'}"
													cssClass="form-control input-small"
													onchange="setTrip(this.id.substring(4));selectForCharter(this.id.substring(4));getDeadTripStops(this.id.substring(4));setKms('triptable2','%{crewChangeStartId}','0');"
													theme="simple"></s:select> <%-- <s:property value="#trips.tripType.tripTypeName" /> --%>
													<s:hidden id="%{'deadtripval' + #ctr.index}" name="%{'tripslist[' + #ctr.index + '].isDreadTrip'}"></s:hidden>
											</td>

											<td align="center"><s:select list="CustomerMap"
													id="%{'customer' + #ctr.index}"
													name="%{'tripslist[' + #ctr.index + '].customer.id'}"
													cssClass="form-control input-small" theme="simple"></s:select>
												<%-- <s:property value="#trips.customer.name" /> --%></td>

											<%-- <s:if test="#trips.isDreadTrip == 1">
												<td align=""><s:checkbox
														name="%{'tripslist[' + #ctr.index + '].deadTrip'}"
														id="%{'deadtrip' + #ctr.index}" fieldValue="true"
														value="false" theme="simple" checked="checked" /> <s:checkbox name="%{'tripslist[' + #ctr.index + '].deadTrip'}" id="%{'deadtrip' + #ctr.index}" fieldValue="true" value="false" onclick="getDeadTripStops(this.id.substring(8));" theme="simple" cssStyle="width: 13px;" checked="checked"/>
												</td>
											</s:if>
											<s:else>
												<td align=""><s:checkbox
														name="%{'tripslist[' + #ctr.index + '].deadTrip'}"
														id="%{'deadtrip' + #ctr.index}" fieldValue="true"
														value="false"
														onclick="getDeadTripStops(this.id.substring(8));"
														theme="simple" cssStyle="width: 13px;" /></td>
											</s:else> --%>
											<%
												if (i == 0) {
											%>
											<td align="center"><input type="text"
												class="form-control" name="tripslist[<%=i%>].origin"
												value='<s:property value="#trips.origin" />'
												style="width: 170px" id="origin<%=i%>" /> <input
												type="hidden" name="tripslist[<%=i%>].startPoint"
												id="origin<%=i%>A<%=i%>"
												value='<s:property value="#trips.startPoint" />' /></td>
											<%
												} else {
											%>
											<td align="center"><select data-placeholder="Select..."
												id="origin<%=i%>" class="form-control input-medium"
												name="tripslist[<%=i%>].origin" style="width: 100px"
												onchange="setOrigin(this.id.substring(6));getDepotsByNo('triptable2','<s:property value="crewChangeStartId" />',this.id.substring(6));">
													<option value="0">Select</option>
											</select>
											<input
												type="hidden" name="tripslist[<%=i%>].startPoint"
												id="origin<%=i%>A<%=i%>"
												value='<s:property value="#trips.startPoint" />' />
											</td>
											<%
												}
											%>
											<td align="center"><input type="text"
												class="form-control" name="tripslist[<%=i%>].destination"
												value='<s:property value="#trips.destination" />'
												style="width: 220px" id="dest<%=i%>" onblur="getDepotsByNo('triptable2','<s:property value="crewChangeStartId" />',this.id.substring(4),'');" /> <%-- <s:property value="#trips.destination" /> --%>
												<input type="hidden" name="tripslist[<%=i%>].endPoint"
												id="dest<%=i%>A<%=i%>"
												value='<s:property value="#trips.endPoint" />' /></td>

											<td><select data-placeholder="Select..."
												id="route_no<%=i%>" class="form-control input-small"
												name="tripslist[<%=i%>].routeNumber.route_id"
												style="width: 100px"
												onchange="getRouteById('triptable2',this.id.substring(8),(parseInt(this.id.substring(8))+1));setroutedistance('triptable2','<s:property value="crewChangeStartId" />',this.id.substring(8));">
													<option value="0">Select</option>
											</select> <input type="hidden" id="selectedRoute<%=i%>"
												name="tripslist[<%=i%>].selectedRoute"
												value='<s:property value="%{tripdetails[#ctr.index].selectedRoute}"/>' />
											</td>

											<td align="center"><input type="text"
												class="form-control" name="tripslist[<%=i%>].distance"
												style="width: 70px" id="routelength<%=i%>"
												onkeypress="run(this);" onblur="setKms('triptable2',<s:property value="crewChangeStartId" />,<%=i%>);"
												value='<s:property value="#trips.distance" />' /> <%-- <s:property value="#trips.distance" /> --%>
											</td>
											<td align="center"><input type="text"
												class="form-control timepicker timepicker-24i"
												name="tripslist[<%=i%>].starttimeString"
												value='<s:property	value="#trips.starttimeString" />'
												style="width: 70px" id="fromtime<%=i%>"
												onblur="checkTime(this);" />
												<%-- <s:property	value="#trips.starttimeString" /> --%></td>
											<td align="center"><input type="text"
												class="form-control timepicker timepicker-24i"
												id="totime<%=i%>" name="tripslist[<%=i%>].endtimeString"
												value='<s:property value="#trips.endtimeString" />'
												style="width: 70px"
												onblur="checkTime(this);validateTime('triptable2','<s:property value="crewChangeStartId" />',this.id.substring(6));" />

											</td>
											<td align="center"><input type="text"
												class="form-control timepicker timepicker-24i"
												readonly="readonly" name="tripslist[<%=i%>].journeyTime"
												value='<s:property value="#trips.journeyTime" />'
												style="width: 70px" id="journeytime<%=i%>"
												onfocus="setJourneyTime(this.id.substring(11));setSpreadHours('triptable2','<s:property value="crewChangeStartId" />',this.id.substring(11));addminstodest('triptable2',this.id.substring(11));">
											</td>
											<td align="center"><s:select list="breakMap"
													id="%{'break' + #ctr.index}"
													name="%{'tripslist[' + #ctr.index + '].breakTypeString'}"
													cssClass="form-control input-small" theme="simple"
													onchange="enablebreakLocation(this.id.substring(5));setFromTime(this.id.substring(5));restforcrew();setJourneyTime(parseInt(this.id.substring(5))+1);setsteeringtime('triptable2','0',this.id.substring(5));setSpreadHours('triptable2','0',this.id.substring(5));">
												</s:select> <%-- <s:property value="#trips.breakType.breakTypeName" /> --%>
											</td>
											<td align="center"><input type="text"
												class="form-control timepicker-24i"
												value='<s:property value="#trips.breaktimeString" />'
												name="tripslist[<%=i%>].breaktimeString"
												style="width: 70px" id="breakduration<%=i%>"
												onblur="checkTime(this);setFromTime(this.id.substring(13));restforcrew();setJourneyTime(parseInt(this.id.substring(13))+1);setsteeringtime('triptable2','<s:property value="crewChangeStartId" />',this.id.substring(13));setSpreadHours('triptable2','<s:property value="crewChangeStartId" />',this.id.substring(13));" />
												
												<input type="text"
												class="form-control"
												value='<s:property value="#trips.breakLocation" />'
												name="tripslist[<%=i%>].breakLocation"
												readonly="readonly" id="breaklocation<%=i%>"
												style="width: 90px;display: none;" />
											</td>
											<%-- <td align="center"><input type="text"
												class="form-control"
												value='<s:property value="#trips.breakLocation" />'
												name="tripslist[<%=i%>].breakLocation"
												readonly="readonly" id="breaklocation<%=i%>"
												style="width: 90px" /></td> --%>

											<s:if test="#trips.crewChange == 1">
												<td align=""><s:checkbox
														name="%{'tripslist[' + #ctr.index + '].crewChangeStatus'}"
														id="%{'crewchange' + #ctr.index}" fieldValue="true"
														value="false"
														onclick='enablecrewLocation(this.id.substring(10));setChange(this.id.substring(10),%{formFour.id},%{formFour.scheduleNumber.schedule_id},%{formFour.NoofTrips})'
														theme="simple" checked="checked" /></td>
											</s:if>
											<s:else>
												<td align=""><s:checkbox
														name="%{'tripslist[' + #ctr.index + '].crewChangeStatus'}"
														id="%{'crewchange' + #ctr.index}" fieldValue="true"
														value="false"
														onclick='enablecrewLocation(this.id.substring(10));setChange(this.id.substring(10),%{formFour.id},%{formFour.scheduleNumber.schedule_id},%{formFour.NoofTrips})'
														theme="simple" /></td>
											</s:else>

											<td align="center"><input type="text"
												class="form-control"
												value='<s:property value="#trips.crewChangeLocation" />'
												name="tripslist[<%=i%>].crewChangeLocation"
												readonly="readonly" id="crewChangelocation<%=i%>"
												style="width: 90px" /></td>

											<s:if test="#trips.nightHalt == 1">
												<td align=""><s:checkbox
														name="%{'tripslist[' + #ctr.index + '].nightTrip'}"
														id="%{'nighttrip' + #ctr.index}" fieldValue="true"
														value="false"
														onclick='enablenightLocation(this.id.substring(9));setChange(this.id.substring(9),%{formFour.id},%{formFour.scheduleNumber.schedule_id},%{formFour.NoofTrips});'
														theme="simple" checked="checked" />
												<input type="text"
												class="form-control"
												value='<s:property value="#trips.nightHaltLocation" />'
												name="tripslist[<%=i%>].nightHaltLocation"
												readonly="readonly" id="nighttriplocation<%=i%>"
												style="width: 90px;display: none;" />			
												</td>
											</s:if>
											<s:else>
												<td align=""><s:checkbox
														name="%{'tripslist[' + #ctr.index + '].nightTrip'}"
														id="%{'nighttrip' + #ctr.index}" fieldValue="true"
														value="false"
														onclick='enablenightLocation(this.id.substring(9));setChange(this.id.substring(9),%{formFour.id},%{formFour.scheduleNumber.schedule_id},%{formFour.NoofTrips});'
														theme="simple" />
												<input type="text"
												class="form-control"
												value='<s:property value="#trips.nightHaltLocation" />'
												name="tripslist[<%=i%>].nightHaltLocation"
												readonly="readonly" id="nighttriplocation<%=i%>"
												style="width: 90px;display: none;" />		
												</td>
											</s:else>

											<%-- <td align="center"><input type="text"
												class="form-control"
												value='<s:property value="#trips.nightHaltLocation" />'
												name="tripslist[<%=i%>].nightHaltLocation"
												readonly="readonly" id="nighttriplocation<%=i%>"
												style="width: 90px" /></td> --%>
											<td align="center"><s:select list="shifttypeMap"
													id="%{'shift' + #ctr.index}"
													name="%{'tripslist[' + #ctr.index + '].shiftType.id'}"
													cssClass="form-control input-small"
													onchange="setShiftType(this.id.substring(5))" theme="simple"></s:select>
												<%-- <s:property value="#trips.shiftType.shiftTypeName" /> --%>

											</td>
											<td align="center"><s:select
													list="#{'Obligatory':'Obligatory','Non-Obligatory':'Non-Obligatory'}"
													id="%{'operationtype' + #ctr.index}"
													name="%{'tripslist[' + #ctr.index + '].operationType'}"
													cssClass="form-control input-small" theme="simple"></s:select>
												<%-- <s:property value="#trips.operationType" /> --%></td>
											<td align="center"><input type="text"
												class="form-control" id='remarks<%=i%>'
												name="tripslist[<%=i%>].remarks"
												value='<s:property value="#trips.remarks" />'
												placeholder="Enter text" style="width: 100px" maxlength="99"></td>
											<td>
											<%-- <input type="hidden" name="tripid" id="tripid<%=i%>" value='<s:property value="#trips.id" />'/> --%>
											<a href="javascript:void(0)" class="btn default btn-xs black" id="del<%=i%>" onclick='deleteRow(this,this.id.substring(3),"triptable2",<s:property value="#trips.id" />);restforcrew();'> <i
												class="fa fa-trash-o"></i> Delete
										</a> &nbsp; <a href="javascript:void(0)" id="add<%=i%>" class="btn default btn-xs purple" onclick="addtrip(this.id.substring(3),'triptable2','<s:property value="formfour.id" />','<s:property value="formfour.scheduleNumber.schedule_id" />','<s:property value="formFour.NoofTrips"/>');"> <i
												class="fa fa-edit"></i> Add
										</a>
										
										</td>
										</tr>
										<%i=i+1; %>
										</s:if>
										<s:if test="#trips.crewChange == 1">
											<s:set var="crewchange" value="1" />
										</s:if>
										
										<s:if test="#trips.nightHalt == 1">
											<s:set var="crewchange" value="1" />
										</s:if>
									</s:iterator>
								</tbody>
							</table>
							</s:if>
						</div>

						<table class="table table-striped table-bordered table-hover"><tr><td></td></tr></table>
						<table class="table table-striped table-bordered table-hover">
						<thead>
						<tr bgcolor="#819FF7">
						<th colspan="3" style="align: center"><b>VEHICLE UTILISATION</b></th>
						<th colspan="3" style="align: center"><b>CREW DUTY HOURS</b></th>
						<!-- <th style="align: center"><b>O.T</b></th> -->
						<th style="align: center"><b>REST FOR CREW</b></th>
						</tr>
						<tr bgcolor="#819FF7">
						<th style="align: center"></th>
						<th style="align: center"><s:property value="shiftHeader1"/></th>
						<th style="align: center"><s:property value="shiftHeader2"/></th>
						<th style="align: center"></th>
						<th style="align: center"><s:property value="shiftHeader1"/></th>
						<th style="align: center"><s:property value="shiftHeader2"/></th>
						<!-- <th style="align: center"><b>O.T</b></th> -->
						<th style="align: center"><b></b></th>
						</tr>
						</thead>
						<tr>
						<td>SCH.KMS</td>
						<td>
						<input type="hidden" name="formFour.id" value='<s:property value="formFour.id"/>'/>
						<input type="text" class="form-control" style="width: 130px" id="schkms1"
											readonly="readonly" name="formFour.schkms1" value='0.0'/></td>
						<td><input type="text" class="form-control"  style="width: 130px" id="schkms2"
											readonly="readonly" name="formFour.schkms2" value='<s:property value="formFour.schkms2"/>'/></td>
						<td>SPREAD</td>
						<td><input type="text" class="form-control" style="width: 130px" id="spread1"
											readonly="readonly" name="formFour.spread1" value='<s:property value="formFour.spread1"/>'/></td>
						<td><input type="text" class="form-control"  style="width: 130px" id="spread2"
											readonly="readonly" name="formFour.spread2" value='<s:property value="formFour.spread2"/>'/></td>
						
						<td><input type="text" class="form-control" style="width: auto;" id="rest1" value='<s:property value="formFour.restForCrew1"/>'
											readonly="readonly" name="formFour.restForCrew1"/></td>
						</tr>
						
						<tr>
						<td>DEAD.KMS</td>
						<td><input type="text" class="form-control" style="width: 130px" id="dead1" value='<s:property value="formFour.dead1"/>'
											readonly="readonly" name="formFour.dead1"/></td>
						<td><input type="text" class="form-control" style="width: 130px" id="dead2" value='<s:property value="formFour.dead2"/>'
											readonly="readonly" name="formFour.dead2"/></td>
						<td>STEERING</td>
						<td><input type="text" class="form-control" style="width: 130px" id="steering1" value='<s:property value="formFour.steering1"/>'
											readonly="readonly" name="formFour.steering1"/></td>
						<td><input type="text" class="form-control" style="width: 130px" id="steering2" value='<s:property value="formFour.steering2"/>'
											readonly="readonly" name="formFour.steering2"/></td>
						<td><input type="text" class="form-control" style="width: 130px" id="rest2" value='<s:property value="formFour.restForCrew2"/>'
											readonly="readonly" name="formFour.restForCrew2"/></td>
						</tr>
						
						<tr>
						<td>TOTAL KMS</td>
						<td><input type="text" class="form-control" style="width: 130px" id="totalkms1" name="formFour.totalKm" value='0.0'
											readonly="readonly" /></td>
						<td><input type="text" class="form-control"  style="width: 130px" id="totalkms2" value='<s:property value="formFour.totalkms"/>'
											readonly="readonly" /></td>
						<td>O.T.</td>
						<td><input type="text" class="form-control" style="width: 130px" id="ot1" value='<s:property value="formFour.ot1"/>'
											readonly="readonly" name="formFour.ot1"/> HRS</td>
						
						<td><input type="text" class="form-control" style="width: 130px" id="ot2" value='<s:property value="formFour.ot2"/>'
											readonly="readonly" name="formFour.ot2"/> HRS</td>
							<td><button type="button" class="btn blue" id="recalStrSpr" onclick="reCalculateSteeringSpreadhours();">Recalculate Steering and Spread Hours</button></td>
						<td><input type="hidden" id="txtbk" value='<%=i%>' name='txbk'/></td>
						
						</tr>
						
						</table>
						
					</div>
					<table border="0" width="100%">
					<tr><td align="center"><button type="button" class="btn blue" onclick="submitform();">Save</button>
							<button type="button" class="btn blue" onclick="cancel();">Cancel</button></td></tr>
					</table>
					<input type="hidden" id="deletedTrips" name="deletedTrips" value=""/>
					<input type="hidden" id="schtype" value="<s:property value="shiftCode" />"/>
					<s:if test="crewChangeStatus == 1">
					<input type="hidden" id="crewchange" value="yes"/>
					</s:if>
					<s:else>
					<input type="hidden" id="crewchange" value="no"/>
					</s:else>
					
					<s:if test="crewChangeStatus == 1">
					<input type="hidden" id="nightchange" value="yes"/>
					</s:if>
					<s:else>
					<input type="hidden" id="nightchange" value="no"/>
					</s:else>
					
					<input type="hidden" id="crewChangeId" name='crewChangeId' value='<s:property value="crewChangeId" />'/>
					<input type="hidden" name="formFour.starttimeString" value='<s:property value="formFour.starttimeString" />'/>
					<input type="hidden" name='id' id="formfourID" value='<s:property value="formFour.id"/>'/>
					
				</div>
				
			</div>
			<s:select list="CustomerCharterMap" id="charter" name=""
												cssClass="form-control input-medium" cssStyle="visibility:hidden;" disabled="disabled"
												theme="simple"></s:select>
						
			<s:select list="shifttypeMap" id="shifttypelist"
												cssClass="form-control" cssStyle="visibility:hidden;" disabled="disabled"
												theme="simple"></s:select>
												
			</form>
		</div>
	</div>
	<s:token/>
	
</body>
</html>