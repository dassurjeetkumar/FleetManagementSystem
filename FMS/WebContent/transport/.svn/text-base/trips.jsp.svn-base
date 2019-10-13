
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
<s:head/>
<style>
table thead tr th {
	text-align: center;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="cache-control" content="no-cache" />
<title>Insert title here</title>
<link rel="stylesheet" href="assets/admin/pages/css/jquery.autocomplete.css" />
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

	<script src="assets/global/plugins/jquery-1.11.0.min.js" type="text/javascript"></script>
	<script src="assets/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
	
	<script type="text/javascript">
function cancel(){
	document.forms[0].action = 'ShowScheduleDetails.action';
	 document.forms[0].submit(); 
}

function run(field) {
    setTimeout(function() {
        var regex = /\d*\.?\d?/g;
        field.value = regex.exec(field.value);
    }, 0);
}

function setChange(id,formfourid,schid,nooftrips){
	setTimeout(function() {
		crewChange(id,formfourid,schid,nooftrips);
    }, 100);
}
</script>
	<script type="text/javascript">
	function hi(){
		
		 
		/* var rows = document.getElementById('triptable2').getElementsByTagName("tbody")[0].getElementsByTagName("tr").length; */
		
	}
	</script>
	
	<script type="text/javascript">
	$(document).ready(function(){
		var id='';
		var rows = document.getElementById("triptable1").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
		
		for(cnt=0;cnt<rows;cnt++){
			var value = document.getElementById('origin'+cnt+'A'+cnt).value;
			if(isEmpty(value)){
			document.getElementById('origin'+cnt+'A'+cnt).value = '0';
			}
			
			var runTimevalue = document.getElementById('journeytime'+cnt).value;
			if(isEmpty(runTimevalue)){
				document.getElementById('journeytime'+cnt).value = '00:00';
				}
			
			var breakTimevalue = document.getElementById('breakduration'+cnt).value;
			
			if(isEmpty(breakTimevalue)){
				document.getElementById('breakduration'+cnt).value = '00:05';
				}
			
			var startTimevalue = document.getElementById('fromtime'+cnt).value;
			if(isEmpty(startTimevalue)){
				document.getElementById('fromtime'+cnt).value = '00:00';
				}
			var toTimevalue = document.getElementById('totime'+cnt).value;
			if(isEmpty(toTimevalue)){
				document.getElementById('totime'+cnt).value = '00:00';
				}
			
			id=cnt;
		}
		
		var myElem = document.getElementById('triptable2');
		
		if(myElem !=null){
			var row1 = document.getElementById("triptable2").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
			var totalrows = parseInt(row1)+parseInt(rows);
			for(j=id;j<totalrows;j++){
				if(isEmpty(value)){
					document.getElementById('origin'+j+'A'+j).value = '0';
					}
				var runTimevalue = document.getElementById('journeytime'+j).value;
				if(isEmpty(runTimevalue)){
					document.getElementById('journeytime'+j).value = '00:00';
					}
				
				var breakTimevalue = document.getElementById('breakduration'+j).value;
				
				if(isEmpty(breakTimevalue)){
					document.getElementById('breakduration'+j).value = '00:05';
					}
			}
		}
		document.getElementById('crewchange').value = 'no';
		document.getElementById('nightchange').value = 'no';
		document.getElementById('crewChangeId').value = '';
		document.getElementById("schkms1").value='0.0';
		document.getElementById("totalkms1").value='0.0';
		
		var shiftTypeOptions = new Array();
		$("#shifttypelist option").each(function()
				{
			var $this = $(this);
			shiftTypeOptions.push($this.val());
			shiftTypeOptions.push($this.text());    
			// add $(this).val() to your list
				});
			
		/* for(var i=0;i<rows;i++){
			var elementid = '#shift'+i;
			$(elementid).empty();
			for(var j=0;j<shiftTypeOptions.length;j=j+2){
				var splitresult = shiftTypeOptions[j].split("-");
				if(splitresult[1] == 'DAY_1' || splitresult[1] == 'SHIFT_1' || splitresult[1] == 'NIGHT_SERVICE' || splitresult[1] == 'GENERAL_SHIFT'){
				$(elementid).append('<option value="'+splitresult[0]+'">'+shiftTypeOptions[j+1]+'</option>');
				}
				
			}
		} */
		
		disableLastRow();
		
		
		    
		
		});
	</script>
	<script>
	var PATH = '<%=request.getContextPath()%>';
	</script>
</head>
<body>
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
						<td><s:property value="routeName" /></td>
						</tr>
						<tr>
						<td><b>Traffic Order : </b><s:property value="formfour.traffic_order_no" /></td>
						</tr>
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
			<form id='tripform' action="SaveTripDetails" name='tripform' method="post">
			<div class="portlet box grey-cascade">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs"></i>Trip Details
					</div>
					<div class="actions">
					
							<button type="button" class="btn blue" onclick="submitform();">Save</button>
							<button type="button" class="btn blue" onclick="cancel();">Cancel</button>
					</div>
				</div>
				<div class="portlet-body">
				
					<div class="table-scrollable">
<%-- 					<input type="text" id="traffic_order_no" name="formFour.traffic_order_no" value="<s:property value="formfour.traffic_order_no" />"/> --%>
<%-- 					<input type="hidden" id="recordDate" name="formFour.recordDate" value="<s:property value="formfour.recordDate" />"/>	 --%>
					
					<input type="hidden" name="formfourID" value="<s:property value="formfour.id" />"/>
					<div style="width:100%;height:600px;overflow-y: auto;max-width:100%;max-height:600px;">
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
								<s:iterator value="trips" var="trips" status="ctr">
									<%
										
									if (i == 0) {
									%>

									<tr id='<%=i%>'>
									
										<td>
										<input type="text" class="form-control" name = "tripdetails[<%=i %>].listItemNumber"
											value='<%=i+1%>' style="width: 50px" id="listitem<%=i%>"
											readonly="readonly" />
										</td>									
										<td>
										<input type="hidden" id="traffic_order_no<%=i %>" name="formFour.traffic_order_no" value="<s:property value="formfour.traffic_order_no" />"/>
<%-- 										<input type="hidden" id="recordDate<%=i %>" name="formFour.recordDate" value="<s:property value="formfour.recordDate" />"/> --%>
										
										<input type="hidden" id="formfour<%=i %>" name="tripdetails[<%=i %>].formFour.id" value="<s:property value="formfour.id" />"/>
										<input type="hidden" id="scheduleid<%=i %>" name="tripdetails[<%=i %>].scheduleNumber.schedule_id" value="<s:property value="formfour.scheduleNumber.schedule_id" />"/>
										<input type="hidden" id="noofTrips<%=i %>" name="tripdetails[<%=i %>].noofTrips" value="<s:property value="%{getTrips().size()}"/>"/>
										<input type="text" class="form-control" name = "tripdetails[<%=i %>].tripNumber"
											value='<%=i+1%>' style="width: 50px" id="id<%=i%>"
											readonly="readonly" />
										</td>
										
																				
										<td><s:select list="tripMap" id="%{'trip' + #ctr.index}" name="%{'tripdetails[' + #ctr.index + '].tripType.id'}"
												cssClass="form-control" style="width: 90px" onchange="setTrip(this.id.substring(4));getDeadTripStops(this.id.substring(4));selectForCharter(this.id.substring(4));setKms('triptable1','0','0');" 
												theme="simple"></s:select>
												
										<input type="hidden" id = "selectedTrip<%=i %>" name="tripdetails[<%=i %>].selectedTrip" value='<s:property value="%{tripdetails[#ctr.index].selectedTrip}"/>'/>
										<input type="hidden" id = "deadtripval<%=i %>" name="tripdetails[<%=i %>].isDreadTrip" value="0"/>		
										</td>
										
										<td>
										
										<s:select list="CustomerMap" id="%{'customer' + #ctr.index}" name="%{'tripdetails[' + #ctr.index + '].customer.id'}"
												cssClass="form-control" style="width: 90px" onchange=""
												theme="simple"></s:select>
																
										</td>
										
										
										<%-- <td>
										 <s:checkbox name="%{'tripdetails[' + #ctr.index + '].deadTrip'}" id="%{'deadtrip' + #ctr.index}" fieldValue="true" value="false" onclick="getDeadTripStops(this.id.substring(8));" theme="simple" cssStyle="width: 13px;"/>
										</td> --%>
										<td>
										<input type="text" class="form-control input-medium" name = "tripdetails[<%=i %>].origin"
											placeholder="Enter text" style="width: 170px" value='<s:property value="%{tripdetails[#ctr.index].origin}"/>'
											id="origin<%=i%>" onblur="getRoutes('<s:property value="route"/>');getRouteById('triptable1','0','1');setroutedistance('triptable1','0','0');setKms('triptable1','0','0');">
											
											<input type="hidden" name = "tripdetails[<%=i %>].startPoint" value='0' id='origin0A0'/>
										</td>
										<td>
										<input type="text" class="form-control input-medium" name = "tripdetails[<%=i %>].destination"
											placeholder="Enter text" style="width: 170px" id="dest<%=i%>" value='<s:property value="%{tripdetails[#ctr.index].destination}"/>'
											onblur="getRoutes('<s:property value="route"/>');getRouteById('triptable1','0','1');setroutedistance('triptable1','0','0');setKms('triptable1','0','0');getGroupingStops(this.id.substring(4));">
											
											<input type="hidden" name = "tripdetails[<%=i %>].endPoint" value='0' id='dest0A0'/>
										</td>

										<td>
										<select id="route_no<%=i%>" data-placeholder="Select..." class="form-control"
													name="tripdetails[<%=i %>].routeNumber.route_id" style="width: 100px"
													onchange="getRouteById('triptable1',<%=i%>,<%=i + 1%>);setroutedistance('triptable1','0',<%=i%>);">
													<option value="0">Select</option>
										</select>
										<input type="hidden" id = "selectedRoute<%=i %>"  name="tripdetails[<%=i %>].selectedRoute" value='<s:property value="%{tripdetails[#ctr.index].selectedRoute}"/>'/>
										</td>
										
										<td>
										<input type="text" class="form-control" name="tripdetails[<%=i %>].distance" value='<s:property value="%{tripdetails[#ctr.index].distance}"/>'
											style="width: 70px" id="routelength<%=i%>" onblur="setKms('triptable1','0',<%=i%>);" onkeypress="run(this);">
										</td>
										
										<td>
										<input type="text"
											class="form-control timepicker timepicker-24i" name="tripdetails[<%=i %>].starttimeString" onblur="checkTime(this);"
											style="width: 70px" id="fromtime<%=i%>" value='<s:property value="formfour.starttimeString" />' /> <span class="input-group-btn">
										</span>
										</td>
										
										<td>
										<input type="text"
											class="form-control timepicker timepicker-24i" name="tripdetails[<%=i %>].endtimeString" value='<s:property value="%{tripdetails[#ctr.index].endtimeString}"/>'
											style="width: 70px" onblur="checkTime(this);validateTime('triptable1','0',<%=i%>);" id="totime<%=i%>"> <span class="input-group-btn" >
										</span>
										</td>
										
										<td><input type="text" class="form-control" name="tripdetails[<%=i %>].journeyTime" readonly="readonly"
											style="width: 70px" id="journeytime<%=i%>"  value='<s:property value="%{tripdetails[#ctr.index].journeyTime}"/>' >
										</td>
										
										<td><s:select list="breakMap" id="%{'break' + #ctr.index}"
												name="%{'tripdetails[' + #ctr.index + '].breakTypeString'}"
												cssClass="form-control" style="width: 90px"
												theme="simple" onchange="enablebreakLocation(%{#ctr.index});setFromTime(%{#ctr.index}); restforcrew();setJourneyTime(parseInt(this.id.substring(5))+1);setsteeringtime('triptable1','0',this.id.substring(5));setSpreadHours('triptable1','0',this.id.substring(5));" ></s:select></td>
										
										
										
										
										
										<td>
										<input type="text" class="form-control timepicker timepicker-24i" value="<s:property value="%{tripdetails[#ctr.index].breaktimeString}"/>" name="tripdetails[<%=i %>].breaktimeString"
										style="width: 70px" id="breakduration<%=i%>" onblur="checkTime(this);setFromTime(<%=i%>);restforcrew();setJourneyTime(parseInt(this.id.substring(13))+1);setsteeringtime('triptable1','0',this.id.substring(13));setSpreadHours('triptable1','0',this.id.substring(13));"/>
										<input type="text" class="form-control" value="<s:property value="%{tripdetails[#ctr.index].breakLocation}"/>" name="tripdetails[<%=i %>].breakLocation" readonly="readonly" style="display: none;"
										id="breaklocation<%=i%>" style="width: 90px"/> 
										</td>	
										<%-- <td>
										<input type="text" class="form-control" value="<s:property value="%{tripdetails[#ctr.index].breakLocation}"/>" name="tripdetails[<%=i %>].breakLocation" readonly="readonly"
										id="breaklocation<%=i%>" style="width: 90px"/> 
										</td> --%>
										<td><s:checkbox name="%{'tripdetails[' + #ctr.index + '].crewChangeStatus'}" id="%{'crewchange' + #ctr.index}" fieldValue="true" value="false" onclick='enablecrewLocation(%{#ctr.index});setChange(%{#ctr.index},%{formfour.id},%{formfour.scheduleNumber.schedule_id},%{getTrips().size()});' theme="simple" />
										
										<%-- <input type="checkbox" name="tripdetails[<%=i %>].isCrewChange" id="crewchange<%=i%>" value='crewchange' onclick='crewChange(<%=i%>,<s:property value="formfour.id" />,<s:property value="formfour.scheduleNumber.schedule_id" />,<s:property value="%{getTrips().size()}" />)'/> --%>
										
										</td>
										
										<td>
										<input type="text" class="form-control" value="" name="tripdetails[<%=i %>].crewChangeLocation" readonly="readonly"
										 id="crewChangelocation<%=i%>" style="width: 90px"/>
										</td>
										
										<td>
										<s:checkbox name="%{'tripdetails[' + #ctr.index + '].nightTrip'}" id="%{'nighttrip' + #ctr.index}" fieldValue="true" value="false" onclick='enablenightLocation(%{#ctr.index});setChange(%{#ctr.index},%{formfour.id},%{formfour.scheduleNumber.schedule_id},%{getTrips().size()});' theme="simple" />
										<input type="text" class="form-control" value="<s:property value="%{tripdetails[#ctr.index].nightHaltLocation}"/>" name="tripdetails[<%=i %>].nightHaltLocation" readonly="readonly" style="display: none"
										 id="nighttriplocation<%=i%>" style="width: 90px"/>
										</td>
										
										<%-- <td>
										<input type="text" class="form-control" value="<s:property value="%{tripdetails[#ctr.index].nightHaltLocation}"/>" name="tripdetails[<%=i %>].nightHaltLocation" readonly="readonly"
										 id="nighttriplocation<%=i%>" style="width: 90px"/>
										</td> --%>
										
										<td>
										
										<s:select list="shifttypeMap" id="%{'shift' + #ctr.index}" name="%{'tripdetails[' + #ctr.index + '].shiftType.id'}"
												cssClass="form-control" style="width: 90px" onchange="setShiftType(%{#ctr.index})"
												theme="simple"></s:select>
														
										</td>
										<td>
										
										<s:select list="#{'Obligatory':'Obligatory','Non-Obligatory':'Non-Obligatory'}" id="%{'operationtype' + #ctr.index}" name="%{'tripdetails[' + #ctr.index + '].operationType'}"
												cssClass="form-control" style="width: 90px"
												theme="simple"></s:select>
														
										</td>
												
										<td><input type="text" class="form-control" name="tripdetails[<%=i %>].remarks" value='<s:property value="%{tripdetails[#ctr.index].remarks}"/>'
											placeholder="Enter text" style="width: 100px" id="remarks<%=i%>" maxlength="99"></td>
										
										
										<td><a href="javascript:void(0)" id='del<%=i%>' class="btn default btn-xs black"> <i
												class="fa fa-trash-o"></i> Delete
										</a> &nbsp; <a href="javascript:void(0)" class="btn default btn-xs purple" onclick="addtrip(<%=i %>,'triptable1','<s:property value="formfour.id" />','<s:property value="formfour.scheduleNumber.schedule_id" />','<s:property value="%{getTrips().size()}"/>');"> <i
												class="fa fa-edit"></i> Add
										</a></td>

									</tr>
									
										
										
									<%
										} else {
									%>




									<tr id='<%=i%>'>
									<td>
										<input type="text" class="form-control" name = "tripdetails[<%=i %>].listItemNumber"
											value='<%=i+1%>' style="width: 50px" id="listitem<%=i%>"
											readonly="readonly" />
										</td>
										<td>
										<input type="hidden" id="formfour<%=i %>" name="tripdetails[<%=i %>].formFour.id" value="<s:property value="formfour.id" />"/>
										<input type="hidden" id="scheduleid<%=i %>" name="tripdetails[<%=i %>].scheduleNumber.schedule_id" value="<s:property value="formfour.scheduleNumber.schedule_id" />"/>
										<input type="hidden" id="noofTrips<%=i %>" name="tripdetails[<%=i %>].noofTrips" value="<s:property value="%{getTrips().size()}"/>"/>
										<input type="text" class="form-control" name = "tripdetails[<%=i %>].tripNumber"
											value='<%=i+1%>' style="width: 50px" id="id<%=i%>"
											readonly="readonly" />
										</td>
																			
										<td>
										<s:select list="tripMap" id="%{'trip' + #ctr.index}" name="%{'tripdetails[' + #ctr.index + '].tripType.id'}"
					
												cssClass="form-control" data-width="100px"
												theme="simple" onchange="setTrip(this.id.substring(4)); selectForCharter(this.id.substring(4));getDeadTripStops(this.id.substring(4));setKms('triptable1','0','0');"></s:select>
										<input type="hidden" id = "selectedTrip<%=i %>" name="tripdetails[<%=i %>].selectedTrip" value='<s:property value="%{tripdetails[#ctr.index].selectedTrip}"/>'/>
										<input type="hidden" id = "deadtripval<%=i %>" name="tripdetails[<%=i %>].isDreadTrip" value="0"/>		
										</td>
								
										<td><s:select list="CustomerMap" id="%{'customer' + #ctr.index}" name="%{'tripdetails[' + #ctr.index + '].customer.id'}"
												cssClass="form-control" style="width: 90px" onchange=""
												theme="simple"></s:select>
														
										</td>
										<%-- <td>
										<s:checkbox name="%{'tripdetails[' + #ctr.index + '].deadTrip'}" id="%{'deadtrip' + #ctr.index}" fieldValue="true" value="false" onclick="getDeadTripStops(this.id.substring(8));" theme="simple" />
										</td> --%>
	
										
										<td>
										<select data-placeholder="Select..." id="origin<%=i%>"  class="form-control input-medium"
													name="tripdetails[<%=i %>].origin" style="width: 100px" onchange="setOrigin(this.id.substring(6));getDepotsByNo('triptable1','0',this.id.substring(6));">
													<option value="0">Select</option>
										</select>
										
										
										<%-- <input type="text" class="form-control" style="width: 170px" name = "tripdetails[<%=i %>].origin"
											 id="origin<%=i%>" value='<s:property value="%{tripdetails[#ctr.index].origin}"/>' 
											readonly="readonly" /> --%>
											
											<s:hidden id="%{'origin' + #ctr.index +'A'+ #ctr.index}"  name="%{'tripdetails[' + #ctr.index + '].startPoint'}" ></s:hidden>
											
											
										</td>
										<td>
										<input type="text" class="form-control input-medium dest" name = "tripdetails[<%=i %>].destination"
											placeholder="Enter text" style="width: 170px" id="dest<%=i%>" value='<s:property value="%{tripdetails[#ctr.index].destination}"/>'
											onblur="getDepotsByNo('triptable1','0',this.id.substring(4));getGroupingStops(this.id.substring(4));" />
											
											<s:hidden id="%{'dest' + #ctr.index +'A'+ #ctr.index}"  name="%{'tripdetails[' + #ctr.index + '].endPoint'}" ></s:hidden>
											
										</td>

										<td>
										<select data-placeholder="Select..." id="route_no<%=i%>" class="form-control"
													name="tripdetails[<%=i %>].routeNumber.route_id" style="width: 90px" onchange="getRouteById('triptable1',this.id.substring(8),(parseInt(this.id.substring(8))+1));setroutedistance('triptable1','0',this.id.substring(8));">
													<option value="0">Select</option>
										</select>
										<input type="hidden" id = "selectedRoute<%=i %>" name="tripdetails[<%=i %>].selectedRoute" value='<s:property value="%{tripdetails[#ctr.index].selectedRoute}"/>'/>
										</td>
										<td>
										<input type="text" class="form-control" name="tripdetails[<%=i %>].distance" onblur="setKms('triptable1','0',<%=i%>);" onkeypress="run(this);"
											 style="width: 70px" id="routelength<%=i%>" value='<s:property value="%{tripdetails[#ctr.index].distance}"/>'/>
										</td>
										<td>
										<input type="text"
											class="form-control timepicker timepicker-24i" name="tripdetails[<%=i %>].starttimeString" value='<s:property value="%{tripdetails[#ctr.index].starttimeString}"/>'
											style="width: 70px" id="fromtime<%=i%>" onblur="validateStartTime('triptable1','0',<%=i%>);checkTime(this);"/> <span
											class="input-group-btn">
										</span>
										</td>
										
										<td>
										<input type="text"
											class="form-control timepicker timepicker-24i" id="totime<%=i%>" name="tripdetails[<%=i %>].endtimeString" value='<s:property value="%{tripdetails[#ctr.index].endtimeString}"/>'
											style="width: 70px" onblur="checkTime(this);validateTime('triptable1','0',this.id.substring(6));"/> <span class="input-group-btn">
										</span>
										</td>
										
										<td>
										<input type="text" class="form-control timepicker timepicker-24i" readonly="readonly" name="tripdetails[<%=i %>].journeyTime" value='<s:property value="%{tripdetails[#ctr.index].journeyTime}"/>'
										style="width: 70px" id="journeytime<%=i%>" onfocus="setJourneyTime(this.id.substring(11));setSpreadHours('triptable1','0',this.id.substring(11));addminstodest('triptable1',this.id.substring(11));">
										</td>
										
										<td><s:select list="breakMap" id="%{'break' + #ctr.index}" name="%{'tripdetails[' + #ctr.index + '].breakTypeString'}"
												cssClass="form-control" style="width: 90px"
												theme="simple" onchange="enablebreakLocation(this.id.substring(5));setFromTime(this.id.substring(5));restforcrew();setJourneyTime(parseInt(this.id.substring(5))+1);setsteeringtime('triptable','0',this.id.substring(5));setSpreadHours('triptable','0',this.id.substring(5));"></s:select></td>
										
										<td>
										<input type="text" class="form-control timepicker-24i"  value='<s:property value="%{tripdetails[#ctr.index].breaktimeString}"/>' name="tripdetails[<%=i %>].breaktimeString"
										style="width: 70px" id="breakduration<%=i%>" onblur="checkTime(this);setFromTime(this.id.substring(13));restforcrew();setJourneyTime(parseInt(this.id.substring(13))+1);setsteeringtime('triptable','0',this.id.substring(13));setSpreadHours('triptable','0',this.id.substring(13));"/>
										<input type="text" class="form-control"  value='<s:property value="%{tripdetails[#ctr.index].breakLocation}"/>' name="tripdetails[<%=i %>].breakLocation" readonly="readonly" style="display: none;"
										 id="breaklocation<%=i%>"/>
										 
										</td>	
										<%-- <td>
										<input type="text" class="form-control"  value='<s:property value="%{tripdetails[#ctr.index].breakLocation}"/>' name="tripdetails[<%=i %>].breakLocation" readonly="readonly"
										 id="breaklocation<%=i%>" style="width: 90px"/>
										</td> --%>
																		
										<td><s:checkbox name="%{'tripdetails[' + #ctr.index + '].crewChangeStatus'}" id="%{'crewchange' + #ctr.index}" fieldValue="true" value="false" onclick='enablecrewLocation(this.id.substring(10));setChange(this.id.substring(10),%{formfour.id},%{formfour.scheduleNumber.schedule_id},%{getTrips().size()})' theme="simple" /></td>
										
										<td>
										<input type="text" class="form-control" value='<s:property value="%{tripdetails[#ctr.index].crewChangeLocation}"/>' name="tripdetails[<%=i %>].crewChangeLocation" readonly="readonly"
										 id="crewChangelocation<%=i%>" style="width: 90px"/>
										</td>
											
										<td>
										<s:checkbox name="%{'tripdetails[' + #ctr.index + '].nightTrip'}" id="%{'nighttrip' + #ctr.index}" fieldValue="true" value="false" onclick='enablenightLocation(this.id.substring(9));setChange(this.id.substring(9),%{formfour.id},%{formfour.scheduleNumber.schedule_id},%{getTrips().size()});' theme="simple" />
										<input type="text" class="form-control" value='<s:property value="%{tripdetails[#ctr.index].nightHaltLocation}"/>' name="tripdetails[<%=i %>].nightHaltLocation" readonly="readonly" style="display: none"
										id="nighttriplocation<%=i%>" style="width: 90px"/>
										</td>
										
										<%-- <td>
										<input type="text" class="form-control" value='<s:property value="%{tripdetails[#ctr.index].nightHaltLocation}"/>' name="tripdetails[<%=i %>].nightHaltLocation" readonly="readonly"
										id="nighttriplocation<%=i%>" style="width: 90px"/>
										</td> --%>
										<td>
										<s:select list="shifttypeMap" id="%{'shift' + #ctr.index}" name="%{'tripdetails[' + #ctr.index + '].shiftType.id'}"
												cssClass="form-control" style="width: 90px" onchange="setShiftType(%{#ctr.index})"
												theme="simple"></s:select>
										</td>
										<td>
										
										<s:select list="#{'Obligatory':'Obligatory','Non-Obligatory':'Non-Obligatory'}" id="%{'operationtype' + #ctr.index}" name="%{'tripdetails[' + #ctr.index + '].operationType'}"
												cssClass="form-control" style="width: 90px" 
												theme="simple"></s:select>
														
										</td>
												
										<td><input type="text" class="form-control" id='remarks<%=i%>' name="tripdetails[<%=i %>].remarks" value='<s:property value="%{tripdetails[#ctr.index].remarks}"/>'
											placeholder="Enter text" style="width: 100px" maxlength="99"></td>
										
										
										<td><a href="javascript:void(0)" class="btn default btn-xs black" id="del<%=i%>" onclick="deleteRow(this,this.id.substring(3));restforcrew();"> <i
												class="fa fa-trash-o"></i> Delete
										</a> &nbsp; <a href="javascript:void(0)" id="add<%=i%>" class="btn default btn-xs purple" onclick="addtrip(this.id.substring(3),'triptable1','<s:property value="formfour.id" />','<s:property value="formfour.scheduleNumber.schedule_id" />','<s:property value="%{getTrips().size()}"/>');"> <i
												class="fa fa-edit"></i> Add
										</a></td>
									</tr>
									
										
										
									<%
										}
									 i=i+1;
									%>

								</s:iterator>

								<%i=i+1; %>

								
							</tbody>
						</table >
						
						<div id='table2'></div>
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
						<td><input type="text" class="form-control" style="width: 130px" id="schkms1"
											readonly="readonly" name="formfour.schkms1" value='0.0'/></td>
						<td><input type="text" class="form-control"  style="width: 130px" id="schkms2"
											readonly="readonly" name="formfour.schkms2" value='<s:property value="formfour.schkms2"/>'/></td>
						<td>SPREAD</td>
						<td><input type="text" class="form-control" style="width: 130px" id="spread1"
											readonly="readonly" name="formfour.spread1" value='<s:property value="formfour.spread1"/>'/></td>
						<td><input type="text" class="form-control"  style="width: 130px" id="spread2"
											readonly="readonly" name="formfour.spread2" value='<s:property value="formfour.spread2"/>'/></td>
						
						<td><input type="text" class="form-control" style="width: auto;" id="rest1" value='<s:property value="formfour.restForCrew1"/>'
											readonly="readonly" name="formfour.restForCrew1"/></td>
						</tr>
						
						<tr>
						<td>DEAD.KMS</td>
						<td><input type="text" class="form-control" style="width: 130px" id="dead1" value='<s:property value="formfour.dead1"/>'
											readonly="readonly" name="formfour.dead1"/></td>
						<td><input type="text" class="form-control" style="width: 130px" id="dead2" value='<s:property value="formfour.dead2"/>'
											readonly="readonly" name="formfour.dead2"/></td>
						<td>STEERING</td>
						<td><input type="text" class="form-control" style="width: 130px" id="steering1" value='<s:property value="formfour.steering1"/>'
											readonly="readonly" name="formfour.steering1"/></td>
						<td><input type="text" class="form-control" style="width: 130px" id="steering2" value='<s:property value="formfour.steering2"/>'
											readonly="readonly" name="formfour.steering2"/></td>
						<td><input type="text" class="form-control" style="width: 130px" id="rest2" value='<s:property value="formfour.rest2"/>'
											readonly="readonly" name="formfour.restForCrew2"/></td>
						</tr>
						
						<tr>
						<td>TOTAL KMS</td>
						<td><input type="text" class="form-control" style="width: 130px" id="totalkms1" name="formfour.totalKm" value='0.0'
											readonly="readonly" /></td>
						<td><input type="text" class="form-control"  style="width: 130px" id="totalkms2" value='<s:property value="formfour.totalkms"/>'
											readonly="readonly" /></td>
						<td>O.T.</td>
						<td><input type="text" class="form-control" style="width: 130px" id="ot1" value='<s:property value="formfour.ot1"/>'
											readonly="readonly" name="formfour.ot1"/> HRS</td>
						
						<td><input type="text" class="form-control" style="width: 130px" id="ot2" value='<s:property value="formfour.ot2"/>'
											readonly="readonly" name="formfour.ot2"/> HRS</td>
						<td><button type="button" class="btn blue" id="recalStrSpr" onclick="reCalculateSteeringSpreadhours();s">Recalculate Steering and Spread Hours</button></td>
						<td><input type="hidden" id="txtbk" value='<%=i%>' name='txbk'/></td>
						
						</tr>
						
						</table>
						
					</div>
					<table border="0" width="100%">
					<tr><td align="center"><button type="button" class="btn blue" onclick="submitform();">Save</button>
							<button type="button" class="btn blue" onclick="cancel();">Cancel</button></td></tr>
					</table>
					<input type="hidden" id="schtype" value="<s:property value="shiftCode" />"/>
					<input type="hidden" id="crewchange" value="no"/>
					<input type="hidden" id="nightchange" value="no"/>
					<input type="hidden" id="crewChangeId" name='crewChangeId' value='<s:property value="crewChangeId" />'/>
					<input type="hidden" name="formfour.starttimeString" value='<s:property value="formfour.starttimeString" />'/>
					<input type="hidden" name='id' id="formfourID" value='<s:property value="formfour.id" />'/>
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