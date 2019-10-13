<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
<%-- <script type="text/javascript">$(document).ready(function () { --%>
<!-- // 	fixHeader(); -->
<%--                   });</script> --%>
<script>
	function goView() {
		document.forms[0].action = 'showOnlineWaybillsGprsEtmTicket.action';
		document.forms[0].submit();
	}
</script>
<script type="text/javascript">
function goView() {
	document.forms[0].action = 'showOnlineWaybillsGprswaybillwiseEtmTicket.action?waybillno=<s:property value="waybillNo" />&ticketdate=<s:property value="ticketDate" />&depotid=<s:property value="depotId" />';
	document.forms[0].submit();
}
</script>
<style type="text/css">


 table thead tr{ 
     display:block; 
 } 



 table  tbody{ 
   display:block; 
   height:400px; 
   overflow:auto; 
 } 


/* only using !important to override your styles below */
td {
    margin-left: 0 !important;
/*     width: 60px !important; */
}

</style>
</head>
<body>
	<div class="page-content-wrapper">
		<div class="page-content">
			<div class="portlet box grey-cascade">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs"></i>Waybill Wise Trip Wise Ticket Details
					</div>
					<div class="actions">
						<button type="button" class="btn blue" onclick="goView();">Back</button>
					</div>
				</div>
				<br />
				<div class="row thumbnails">
				<div class="meet-our-team">
							<div class="team-info" align="center">
								<b>Depot Name : </b> <s:property value="depotName" />
										&nbsp;&nbsp;&nbsp;&nbsp;<b>Schedule Number : </b> <s:property
												value="scheduleNumber" />
									
							</div>
						</div>
				
				</div>

				<div class="portlet-body">



					<div class="table-scrollable">
						<input type="hidden" name="formfourID"
							value="<s:property value="formfour.id" />" />
						<table class="table table-striped table-bordered table-hover"
							id="triptable1" style="height: 50px">

							<thead>
								<tr bgcolor="#819FF7">
									
									<th scope="col" align="center">S No.</th>
<!-- 									<th scope="col" align="center">Status</th> -->
									<th scope="col" align="center">Ticket No</th>
									<th scope="col" align="center">Transaction No</th>
									<th scope="col" align="center">Ticket Type</th>
									<th scope="col" align="center">Ticket Sub Type</th>
									<th scope="col" align="center">Print Flag</th>
									<th scope="col" align="center">Schedule No</th>
									<th scope="col" align="center">Schedule Type</th>
									<th scope="col" align="center">Duty Type</th>
									<th scope="col" align="center">Route No</th>
									<th scope="col" align="center">From Stop Name</th>
									<th scope="col" align="center">Till  Stop  Name  </th>
									<th scope="col" align="center">Ticket Time</th>
									<th scope="col" align="center">passenger Count</th>
									<th scope="col" align="center">Passenger Amt</th>
									<th scope="col" align="center">Luggage Count</th>
									<th scope="col" align="center">Cash Amt</th>
									<th scope="col" align="center">Card Amt</th>
									<th scope="col" align="center">Luggage Amt</th>
									<th scope="col" align="center">Total Amt</th>

								</tr>
							</thead>
							<tbody>

								<s:iterator value="showWaybill" var="trips" status="ctr">

									<tr>
										<td align="center" width="50px"><s:property value="%{#ctr.index + 1}" /></td>
<%-- 										<td align="center"><a href="ShowLiveBusDetailsAll?vehicleNo=<s:property value='vehicleNo'/>&ticketdate=<s:property value="ticket_time" />&deviceser=<s:property value='device_serial_number' />&depotId=<s:property value='depotId'/>&orgname=<s:property value='orgname'/>"  target="popup"  onclick="window.open('ShowLiveBusDetailsAll?vehicleNo=<s:property value='vehicleNo'/>&ticketdate=<s:property value='ticketDate' />&deviceser=<s:property value='device_serial_number' />&depotId=<s:property value='depotId'/>&orgname=<s:property value='orgname'/>','popup','width=1000,height=1000,left=400,top=200,scrollbars=yes,resizable=yes,'); return false;"><img src='assets/images/Bus-Icon.png'></a></td> --%>
										<td align="center" width="83px"><s:property value="ticket_no" /></td>
										<td align="center" width="120px"><s:property value="transaction_no" /></td>
										<td align="center" width="93px"><s:property value="ticket_type_name" /></td>
										<td align="center" width="125px"><s:property value="ticket_sub_type_name" /></td>
										<td align="center" width="85px"><s:property value="printed_flag" /></td>
										<td align="center" width="101px"><s:property value="schedule_no" /></td>
										<td align="center" width="115px"><s:property value="scheduleType" /></td>
										<td align="center" width="88px"><s:property value="shiftType" /></td>
										<td align="center" width="80px"><s:property value="routeNo" /></td>
										<td align="center" width="132px"><s:property value="from_stop_name" /></td>
										<td align="center" width="120px"><s:property value="till_stop_name" /></td>
										<td align="center" width="92px"><s:property value="ticket_time" /></td>
										
										<td align="center" width="133px"><s:property value="px_count" /></td>
										<td align="center" width="116px"><s:property value="px_total_amount" /></td>
										<td align="center" width="124px"><s:property value="lugg_units" /></td>
										 <td align="center" width="82px"><s:property value="cash_amt" /></td>
										<td align="center" width="82px" ><s:property value="card_amt" /></td>
										<td align="center" width="100px"><s:property value="lugg_total_amount" /></td>
										<td align="center" width="88px"><s:property value="total_ticket_amount" /></td>

										<%-- <td align="center"><a
											href='showOnlineWaybillsGprswaybillwise.action?waybillno=<s:property value="waybillNo" />&ticketdate=<s:property value="ticketDate" />&depotid=<s:property value="depotId" />'><s:property
													value="noOfTickets" /></a></td> --%>
									</tr>
								</s:iterator>
								
								<tr>
								<td colspan="13" align="center">Total</td>
								<td align="center"><s:property value="totalPassengerCnt" /></td>
								<td align="center"><s:property value="totalPassengerAmt" /></td>
								<td align="center"><s:property value="totallugCnt" /></td>
								<td align="center"><s:property value="totalCashAmt"/></td>
								<td align="center"><s:property value="totalCardAmt"/></td>
								<td align="center"><s:property value="totalLugAmount" /></td>
								<td align="center"><s:property value="totaltotalAmount" /></td>							
								</tr>
							</tbody>
						</table>





					</div>


				</div>
			</div>
		</div>
	</div>
</body>
</html>