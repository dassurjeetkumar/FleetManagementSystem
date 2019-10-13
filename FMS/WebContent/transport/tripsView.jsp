<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function cancel() {
		var id = document.getElementById('schid').value
		document.forms[0].action = "FormFourView.action?sch="+ id;
		document.forms[0].submit();
	}
</script>

<script>
	$(document).ready(function() {
		   window.history.pushState("","", SaveTripView);
		 });
	
	
// 	function printDiv(divName) {
// 	     var printContents = document.getElementById(divName).innerHTML;
// 	     var originalContents = document.body.innerHTML;

// 	     document.body.innerHTML = printContents;

// 	     window.print();

// 	     document.body.innerHTML = originalContents;
// 	}
	
	
	
	
	
	function printDiv(divName) {     
	    
	     // document.getElementById(headerinfoid).style.visibility='hidden';  
	     $('#pid').hide();
	     $('#cid').hide();
	     $('#ExpId').hide();
	     $('#headerinfoid').hide();
	     $('#header').show();
	     $('#captionid').show();
	    var divElements = document.getElementById(divName).innerHTML;
	    divElements += document.getElementById("header").innerHTML;

	    var mywindow = window.open("<%=request.getContextPath()%>/Print.jsp");
	   
	    mywindow.onload = function() {
	    mywindow.document.body.innerHTML=divElements;
	    mywindow.document.body.innerHTML=divElements;
	    mywindow.print();
	    mywindow.close();
	    }
	    $('#headerinfoid').show();
	    $('#pid').show();
	    $('#cid').show();
	    $('#header').hide();   
	    $('#captionid').hide();
	    $('#ExpId').show();
	}
	
	
	function tabletoExcel(divName){   
		 
		  $('#pid').hide();
	       $('#cid').hide();
	     $('#headerinfoid').hide();
	     $('#header').show();
	     $('#captionid').show();
	     
	     
	     var inputHTML = "<table border='1'>";
         inputHTML += "<tr>";
         inputHTML += "<th  align='left'colspan='8'>Bangalore Metropolitan Transport Corporation</th>";
         inputHTML += "</tr>";
         inputHTML += "<th  align='Center'colspan='8'>Form Four Trip Details</th>";
         inputHTML += "</tr>";
         inputHTML += "<tr>";
         inputHTML += "<th colspan='8' align='left'></th>";
         inputHTML += "<th colspan='8' align='left'></th>";
         inputHTML += "</tr>";
         inputHTML += "</table>";
		    
		 var divElements = inputHTML+document.getElementById(divName).innerHTML;
		    divElements += document.getElementById("header").innerHTML;
	    
	    var downloadLink = document.createElement("a");
	    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(divElements);
	    downloadLink.download = "Form Four Trip Details.xls";
	    document.body.appendChild(downloadLink);
	    downloadLink.click();
	    document.body.removeChild(downloadLink);
	    
	    $('#pid').show();
	       $('#cid').show();
	     $('#headerinfoid').show();
	     $('#header').hide();
	     $('#captionid').hide();
	  }

	
	</script>
</head>
<body>

	<div class="page-content-wrapper">
		<div class="page-content">
			<div class="portlet box grey-cascade">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs"></i>Trip Details
					</div>
					<div class="actions">
					 <button type="button" id="pid" class="btn blue" onclick="printDiv('printableArea');">Print</button>&nbsp;
					  <button type="button" id="ExpId" class="btn blue" onclick="tabletoExcel('printableArea');">Export</button>&nbsp;
						<button type="button" id="cid" class="btn blue" onclick="cancel();">Cancel</button>
					</div>
				</div>
				</div>
				<br />
				<div id="printableArea"> 
				<div id="printTable">
				<div class="caption" id="captionid" style='display:none'>
						<b>Trip Details:</b>
				</div>
				<div class="row thumbnails" id="headerinfoid">
					<div class="col-md-4">
						<div class="meet-our-team">
							<div class="team-info" align="center">
								<table>
									<tr>
										<td><b>Service Type : </b> <s:property value="service" />
										</td>
									</tr>
									<tr>
										<td><b>Route Number : </b> <s:property
												value="routeNumber" /></td>
									</tr>
									<tr>
										<td><b>Schedule Number : </b> <s:property
												value="schedule" /></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="meet-our-team">
							<div class="team-info" align="center">
								<table>
									<tr>
										<td><b>Form Four Name : </b>
										<s:property value="formfourname" /></td>
									</tr>
									<tr>
						<td align="center"><s:property value="routeName" /></td>
						</tr>
						<tr>
						<td><b>Traffic Order No : </b>
						 <s:property value="traffic_order" />
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
										<td><b>Division : </b>
										<s:property value="division" /></td>
									</tr>
									<tr>
										<td><b>Depot : </b>
										<s:property value="depot" /></td>
									</tr>
									<tr>
										<td><b>Schedule Type : </b>
										<s:property value="shift" /></td>
									</tr>
									<tr>
										<td><b>W.E.F : </b>
										<s:property value="effectstartdate" /></td>
									</tr>
								</table>
							</div>
						</div>
					</div>

				</div>
<!-- print header info -->
<div class="row thumbnails" id="header" style="display: none;">
					<div class="">
						<div class="">
							<div class="" align="">
								<table  class='' id='sample_6' style='width:100%;'>
									<tr>
										<td align="left"><b>Service Type : </b> <s:property value="service" />
										</td>
									
										<td align="center"><b>Route Number : </b> <s:property
												value="routeNumber" /></td>
									
										<td align="right"><b>Schedule Number : </b> <s:property
												value="schedule" /></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
					<div class="">
						<div class="">
							<div class="" align="">
								<table  style='width:100%;'>
									<tr>
										<td align="left"><b>Form Four Name : </b>
										<s:property value="formfourname" /></td>
									
						<td align="center"><s:property value="routeName" /></td>
						</tr>
								</table>
							</div>
						</div>
					</div>
					<div class="">
						<div class="">

							<div class="" align="">

								<table  id='sample_6' style='width:100%;'>
									<tr>
										<td align="left"><b>Division : </b>
										<s:property value="division" /></td>
									
										<td align="center"><b>Depot : </b>
										<s:property value="depot" /></td>
									
										<td align="center"><b>Schedule Type : </b>
										<s:property value="shift" /></td>
									
										<td align="right"><b>W.E.F : </b>
										<s:property value="effectstartdate" /></td>
									</tr>
								</table>
							</div>
						</div>
					</div>

				</div>
<!-- print header info end -->
				<div class="portlet-body">

					<div class="table-scrollable">
						<input type="hidden" name="formfourID"
							value="<s:property value="formfour.id" />" />
						<table class="table table-striped table-bordered table-hover"
							id="triptable1" style="height: 50px">

							<thead>
								<tr bgcolor="#819FF7">
									<th scope="col">List</th>
									<th scope="col">Trip</th>
									<th scope="col" align="center">Trip</th>
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
									<th scope="col" align="center">Item No.</th>
									<th scope="col" align="center">No.</th>
									<th scope="col" align="center">Type</th>
									<th scope="col" align="center">Name</th>
									<!-- <th scope="col" align="center">Trip</th> -->
									<th scope="col" align="center">Start Point</th>
									<th scope="col" align="center">End Point</th>
									<th scope="col" align="center">Number</th>
									<th scope="col" align="center"></th>
									<th scope="col" align="center">Start Time</th>
									<th scope="col" align="center">&nbsp;&nbsp;End Time</th>
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
								<s:set var="crewchange" value="0"></s:set>
								<s:set var="nightchange" value="0"></s:set>
								<s:if test="scheduleCode == 1">
								<s:iterator value="tripslist" var="trips" begin='%{crewChangeId}'>
									<s:if test="#crewchange == 1">
										<tr>
											<td colspan='14' align='center'>CREW CHANGE, FUELLING
												MAINTENANCE AT DEPOT</td>
										</tr>
									</s:if>
									<s:if test="#nightchange == 1">
									<tr>
											<td colspan='14' align='center'>CREW CHANGE, FUELLING
												MAINTENANCE AT DEPOT</td>
										</tr>
									</s:if>
									<tr>
										<td align="center"><s:property value="listItemNumber" /></td>
										<td align="center"><s:property value="#trips.tripNumber" /></td>
										<td align="center"><s:property
												value="#trips.tripType.tripTypeName" /></td>
										<td align="center"><s:property
												value="#trips.customer.name" /></td>
										<%-- <s:if test="#trips.isDreadTrip == 1">
											<td align="center">Yes</td>
										</s:if>
										<s:else>
											<td align="center">No</td>
										</s:else> --%>
										<td align="center"><s:property value="#trips.origin" /></td>
										<td align="center"><s:property value="#trips.destination" /></td>

										<td align="center"><s:property
												value="#trips.routeNumber.routeNumber" />&nbsp;(<s:property
												value="#trips.routeNumber.routeDirection" />)</td>
										<td align="center"><s:property value="#trips.distance" /></td>
										<td align="center"><s:property
												value="#trips.starttimeString" /></td>
										<td align="center"><s:property
												value="#trips.endtimeString" /></td>
										<td align="center"><s:property value="#trips.journeyTime" /></td>
										<td align="center"><s:property
												value="#trips.breakType.breakTypeName" /></td>
										<td align="center"><s:property
												value="#trips.breaktimeString" /></td>
										<%-- <td align="center"><s:property
												value="#trips.breakLocation" /></td> --%>

										<s:if test="#trips.crewChange == 1">
											<td align="center">Yes</td>
											<s:set var="crewchange" value="1" />
										</s:if>
										<s:else>
											<td align="center">No</td>
											<s:set var="crewchange" value="0" />
										</s:else>

										<td align="center"><s:property
												value="#trips.crewChangeLocation" /></td>

										<s:if test="#trips.nightHalt == 1">
											<td align="center">Yes</td>
											<s:set var="nightchange" value="1" />
										</s:if>
										<s:else>
											<td align="center">No</td>
											<s:set var="nightchange" value="0" />
										</s:else>

										<%-- <td align="center"><s:property
												value="#trips.nightHaltLocation" /></td> --%>
										<td align="center"><s:property
												value="#trips.shiftType.shiftTypeName" /></td>
										<td align="center"><s:property
												value="#trips.operationType" /></td>
										<td align="center"><s:property value="#trips.remarks" /></td>
									</tr>
								</s:iterator>
								
								<tr>
											<td colspan='14' align='center'>CREW CHANGE, FUELLING
												MAINTENANCE AT DEPOT</td>
										</tr>
								
								<s:iterator value="tripslist" var="trips" begin='0' end='%{crewChangeId - 1}'>
									<tr>
										<td align="center"><s:property value="listItemNumber" /></td>
										<td align="center"><s:property value="#trips.tripNumber" /></td>
										<td align="center"><s:property
												value="#trips.tripType.tripTypeName" /></td>
										<td align="center"><s:property
												value="#trips.customer.name" /></td>
										<%-- <s:if test="#trips.isDreadTrip == 1">
											<td align="center">Yes</td>
										</s:if>
										<s:else>
											<td align="center">No</td>
										</s:else> --%>
										<td align="center"><s:property value="#trips.origin" /></td>
										<td align="center"><s:property value="#trips.destination" /></td>

										<td align="center"><s:property
												value="#trips.routeNo" />&nbsp;-<s:property
												value="#trips.routeNumber.routeDirection" /></td>
										<td align="center"><s:property value="#trips.distance" /></td>
										<td align="center"><s:property
												value="#trips.starttimeString" /></td>
										<td align="center"><s:property
												value="#trips.endtimeString" /></td>
										<td align="center"><s:property value="#trips.journeyTime" /></td>
										<td align="center"><s:property
												value="#trips.breakType.breakTypeName" /></td>
										<td align="center"><s:property
												value="#trips.breaktimeString" /></td>
										<%-- <td align="center"><s:property
												value="#trips.breakLocation" /></td> --%>

										<s:if test="#trips.crewChange == 1">
											<td align="center">Yes</td>
											<s:set var="crewchange" value="1" />
										</s:if>
										<s:else>
											<td align="center">No</td>
											<s:set var="crewchange" value="0" />
										</s:else>

										<td align="center"><s:property
												value="#trips.crewChangeLocation" /></td>

										<s:if test="#trips.nightHalt == 1">
											<td align="center">Yes</td>
											<s:set var="nightchange" value="1" />
										</s:if>
										<s:else>
											<td align="center">No</td>
											<s:set var="nightchange" value="0" />
										</s:else>

										<%-- <td align="center"><s:property
												value="#trips.nightHaltLocation" /></td> --%>
										<td align="center"><s:property
												value="#trips.shiftType.shiftTypeName" /></td>
										<td align="center"><s:property
												value="#trips.operationType" /></td>
										<td align="center"><s:property value="#trips.remarks" /></td>
									</tr>
								</s:iterator>
								
								
								</s:if>
								
								 <s:else>
								<s:iterator value="tripslist" var="trips">
									<s:if test="#crewchange == 1">
										<tr>
											<td colspan='14' align='center'>CREW CHANGE, FUELLING
												MAINTENANCE AT DEPOT</td>
										</tr>
									</s:if>
									<s:if test="#nightchange == 1">
									<tr>
											<td colspan='14' align='center'>CREW CHANGE, FUELLING
												MAINTENANCE AT DEPOT</td>
										</tr>
									</s:if>
									<tr>
										<td align="center"><s:property value="listItemNumber" /></td>
										<td align="center"><s:property value="#trips.tripNumber" /></td>
										<td align="center"><s:property
												value="#trips.tripType.tripTypeName" /></td>
										<td align="center"><s:property
												value="#trips.customer.name" /></td>
										<%-- <s:if test="#trips.isDreadTrip == 1">
											<td align="center">Yes</td>
										</s:if>
										<s:else>
											<td align="center">No</td>
										</s:else> --%>
										<td align="center"><s:property value="#trips.origin" /></td>
										<td align="center"><s:property value="#trips.destination" /></td>

										<td align="center"><s:property
												value="#trips.routeNo" />&nbsp;(<s:property
												value="#trips.routeNumber.routeDirection" />)</td>
										<td align="center"><s:property value="#trips.distance" /></td>
										<td align="center"><s:property
												value="#trips.starttimeString" /></td>
										<td align="center"><s:property
												value="#trips.endtimeString" /></td>
										<td align="center"><s:property value="#trips.journeyTime" /></td>
										<td align="center"><s:property
												value="#trips.breakType.breakTypeName" /></td>
										<td align="center"><s:property
												value="#trips.breaktimeString" /></td>
										<%-- <td align="center"><s:property
												value="#trips.breakLocation" /></td> --%>

										<s:if test="#trips.crewChange == 1">
											<td align="center">Yes</td>
											<s:set var="crewchange" value="1" />
										</s:if>
										<s:else>
											<td align="center">No</td>
											<s:set var="crewchange" value="0" />
										</s:else>

										<td align="center"><s:property
												value="#trips.crewChangeLocation" /></td>

										<s:if test="#trips.nightHalt == 1">
											<td align="center">Yes</td>
											<s:set var="nightchange" value="1" />
										</s:if>
										<s:else>
											<td align="center">No</td>
											<s:set var="nightchange" value="0" />
										</s:else>

										<%-- <td align="center"><s:property
												value="#trips.nightHaltLocation" /></td> --%>
										<td align="center"><s:property
												value="#trips.shiftType.shiftTypeName" /></td>
										<td align="center"><s:property
												value="#trips.operationType" /></td>
										<td align="center"><s:property value="#trips.remarks" /></td>
									</tr>
								</s:iterator>
								</s:else>
							</tbody>
						</table>

						<div id='table2'></div>
						<table class="table table-striped table-bordered table-hover">
							<tr>
								<td></td>
							</tr>
						</table>

						<table class="table table-striped table-bordered table-hover" id="tripView">
							<thead>
								<tr bgcolor="#819FF7">
									<th colspan="3" style="align: center"><b>VEHICLE
											UTILISATION</b></th>
									<th colspan="3" style="align: center"><b>CREW DUTY
											HOURS</b></th>
									<!-- <th style="align: center"><b>O.T</b></th> -->
									<th style="align: center"><b>REST FOR CREW</b></th>
								</tr>
								<tr bgcolor="#819FF7">
									<th style="align: center"></th>
									<th style="align: center"><s:property value="shiftHeader1" /></th>
									<th style="align: center"><s:property value="shiftHeader2" /></th>
									<th style="align: center"></th>
									<th style="align: center"><s:property value="shiftHeader1" /></th>
									<th style="align: center"><s:property value="shiftHeader2" /></th>
									<!-- <th style="align: center"><b>O.T</b></th> -->
									<th style="align: center"><b></b></th>
								</tr>
							</thead>
							<tr>
								<td>SCH.KMS</td>
								<td><s:property value="formFour.schkms1" /></td>
								<td><s:property value="formFour.schkms2" /></td>
								<td>SPREAD</td>
								<td><s:property value="formFour.spread1" /></td>
								<td><s:property value="formFour.spread2" /></td>

								<td><s:property value="formFour.restForCrew1" /></td>
							</tr>

							<tr>
								<td>DEAD.KMS</td>
								<td><s:property value="formFour.dead1" /></td>
								<td><s:property value="formFour.dead2" /></td>
								<td>STEERING</td>
								<td><s:property value="formFour.steering1" /></td>
								<td><s:property value="formFour.steering2" /></td>
								<td><s:property value="formFour.restForCrew2" /></td>
							</tr>

							<tr>
								<td>TOTAL KMS</td>
								<td><s:property value="formFour.totalKm" /></td>
								<td></td>
								<td>O.T.</td>
								<td><s:property value="formFour.ot1" /></td>

								<td><s:property value="formFour.ot2" /></td>
								<td>Halt At : <s:property value="nightHaltLocation"/><input type="hidden" id="schid" value='<s:property value="formFour.scheduleNumber.schedule_id" />'/></td>
								

							</tr>
							
						</table>

					</div>
<%try{ %>
<h3><center>Fare Stages</center></h3>
<table style='width:100%;'>
<tbody>
<tr>
	<s:iterator begin="0" end="5"  value='listOfFareStages' id='object'>
		<s:if test="#object.get(1).size()>1">
		<td valign="top">
			<%-- <s:property value='#object.get(1).size()'/> --%>
			<ol class="fare"><b><s:property value='#object.get(0)'/></b>
				<s:iterator value="#object.get(1)" var='ab' id='farestages'>
					<li><s:property value='stopname'/></li>
				</s:iterator>
			</ol>
			</td>
		</s:if>
	</s:iterator>
</tr>
<tr>
	<s:iterator begin="6" end="11"  value='listOfFareStages' id='object'>
		<s:if test="#object.get(1).size()>1">
		<td valign="top">
			<%-- <s:property value='#object.get(1).size()'/> --%>
			<ol class="fare"><b><s:property value='#object.get(0)'/></b>
				<s:iterator value="#object.get(1)" id='farestages'>
					<li><s:property value='stopname' /></li>
				</s:iterator>
			</ol>
			</td>
		</s:if>
	</s:iterator>
</tr>
<tr>
	<s:iterator begin="12" end="17"  value='listOfFareStages' id='object'>
		<s:if test="#object.get(1).size()>1">
		<td valign="top">
			<%-- <s:property value='#object.get(1).size()'/> --%>
			<ol class="fare"><b><s:property value='#object.get(0)'/></b>
				<s:iterator value="#object.get(1)" var="a" id='farestages'>
					<li><s:property value='stopname'/></li>
				</s:iterator>
			</ol>
			</td>
		</s:if>
	</s:iterator>
</tr>
<tr>
	<s:iterator begin="18" end="24"  value='listOfFareStages' id='object'>
		<s:if test="#object.get(1).size()>1">
			<td valign="top">
				<%-- <s:property value='#object.get(1).size()'/> --%>
				<ol class="fare"><b><s:property value='#object.get(0)'/></b>
					<s:iterator value="#object.get(1)" id='farestages'>
						<li><s:property value='stopname' /></li>
					</s:iterator>
				</ol>
			</td>
		</s:if>
	</s:iterator>
</tr>
</tbody>
</table>
<%}catch(Exception  e){
	e.printStackTrace();
}%>

 <%--  <s:iterator value="finalstageslist" var="s">
     <ul>
 
        <s:iterator value="#{stages}">
        <li><s:property /></li>
        </s:iterator>   
    </ul>
  </s:iterator>  --%>

					

				</div>
			</div>
		</div>
	</div>
</body>
</html>