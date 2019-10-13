<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function goView() {
	//document.forms[0].action = 'showOnlineWaybillsGprswaybillwise.action?waybillno=<s:property value="waybillNo" />&ticketdate=<s:property value="ticketDate" />&depotid=<s:property value="depotId" />';
	document.forms[0].action = 'Showlcdepotwaybill.action?divisionId=<s:property value="divisionId" />&ticketdate=<s:property value="ticketDate" />&depotId=<s:property value="depotId" />';
	document.forms[0].submit();
}
</script>
</head>
<body>
	<div class="page-content-wrapper">
		<div class="page-content">
			<div class="portlet box grey-cascade">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs"></i>Line Checking Ticket Status Report
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
									
									<th scope="col" align="center">Sr. No.</th>
									<th scope="col" align="center">Ticket No</th>
									<th scope="col" align="center">Transaction No</th>
									<th scope="col" align="center">Ticket Type</th>
									<th scope="col" align="center">Ticket Sub Type</th>
									<th scope="col" align="center">Schedule No</th>
									<th scope="col" align="center">Schedule Type</th>
									<th scope="col" align="center">Duty Type</th>
									<th scope="col" align="center">Route No</th>
									<th scope="col" align="center">From Stop Name</th>
									<th scope="col" align="center">Till Stop Name</th>
									<th scope="col" align="center">Ticket Time</th>
									
									<th scope="col" align="center">passenger Count</th>
									<th scope="col" align="center">Passenger Amount</th>
									<th scope="col" align="center">Luggage Count</th>
									<th scope="col" align="center">Luggage Amount</th>
									<th scope="col" align="center">Total Amount</th>

								</tr>
							</thead>
							<tbody>

								<s:iterator value="showWaybill" var="trips" status="ctr">

									<tr>
										<td align="center"><s:property value="%{#ctr.index + 1}" /></td>
										<td align="center"><s:property value="ticket_no" /></td>
										<td align="center"><s:property value="transaction_no" /></td>
										<td align="center"><s:property value="ticket_type_name" /></td>
										<td align="center"><s:property value="ticket_sub_type_name" /></td>
										<td align="center"><s:property value="schedule_no" /></td>
										<td align="center"><s:property value="scheduleType" /></td>
										<td align="center"><s:property value="shiftType" /></td>
										<td align="center"><s:property value="routeNo" /></td>
										<td align="center"><s:property value="from_stop_name" /></td>
										<td align="center"><s:property value="till_stop_name" /></td>
										<td align="center"><s:property value="ticket_time" /></td>
										
										<td align="center"><s:property value="px_count" /></td>
										<td align="center"><s:property value="px_total_amount" /></td>
										<td align="center"><s:property value="lugg_units" /></td>
										<td align="center"><s:property value="lugg_total_amount" /></td>
										<td align="center"><s:property value="total_ticket_amount" /></td>

										<%-- <td align="center"><a
											href='showOnlineWaybillsGprswaybillwise.action?waybillno=<s:property value="waybillNo" />&ticketdate=<s:property value="ticketDate" />&depotid=<s:property value="depotId" />'><s:property
													value="noOfTickets" /></a></td> --%>
									</tr>
								</s:iterator>
								
								<tr>
								<td colspan="12" align="center">Total</td>
								<td align="center"><s:property value="totalPassengerCnt" /></td>
								<td align="center"><s:property value="totalPassengerAmt" /></td>
								<td align="center"><s:property value="totallugCnt" /></td>
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