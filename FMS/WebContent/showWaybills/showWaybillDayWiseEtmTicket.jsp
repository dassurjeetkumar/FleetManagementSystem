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
	document.forms[0].action = 'showOnlineWaybillsGprsEtmTicket.action?ticket_date=<s:property value="ticketDate" />&depotid=<s:property value="depotId" />';
	document.forms[0].submit();
}
</script>
</head>
<body>
<form method="get">
	<div class="page-content-wrapper">
		<div class="page-content">
			<div class="portlet box grey-cascade">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs"></i>Waybill Wise Date Wise Details
					</div>
					<div class="actions">
						<button type="button" class="btn blue" onclick="goView();">Back</button>
					</div>
				</div>
				<br />
				<div class="row thumbnails">
				</div>

				<div class="portlet-body">



					<div class="table-scrollable">
						<s:hidden name="depotId"></s:hidden>
						<s:hidden name="ticketDate"></s:hidden>
						<table class="table table-striped table-bordered table-hover"
							id="triptable1" style="height: 50px">

							<thead>
								<tr bgcolor="#819FF7">
								<th scope="col" align="center">Sr.No.</th>
									<th scope="col" align="center">Waybill No.</th>
									<th scope="col" align="center">Ticket Date</th>
									<th scope="col" align="center">Schedule No.</th>
									<th scope="col" align="center">Schedule Type</th>
									<th scope="col" align="center">Duty Type</th>
									<th scope="col" align="center">ETM No.</th>
									<!-- <th scope="col" align="center">Route No</th>
									<th scope="col" align="center">Shift No</th>
									<th scope="col" align="center">Trip No</th> -->
									<th scope="col" align="center">Total Amount</th>
									<th scope="col" align="center">Ticket Count</th>

								</tr>
							</thead>
							<tbody>

								<s:iterator value="showWaybill" var="trips" status="ctr">

									<tr>
										<td align="center"><s:property value="%{#ctr.index + 1}" /></td>
										<td align="center"><s:property value="waybillNo" /></td>
										<td align="center"><s:property value="ticket_date" /></td>
										<td align="center"><s:property value="scheduleNo" /></td>
										<td align="center"><s:property value="scheduleType" /></td>
										<td align="center"><s:property value="shiftType" /></td>
										<td align="center"><s:property value="etimNo" /></td>
										<%-- <td align="center"><s:property value="routeNo" /></td>
										<td align="center"><s:property value="shift_No" /></td>
										<td align="center"><s:property value="tripno" /></td> --%>
										<td align="center"><s:property value="total_ticket_amount" /></td>
										<td align="center"><%-- <a href='showOnlineWaybillsGprsTripwise.action?
										waybillno=<s:property value="waybillNo" />
										&ticketdate=<s:property value="ticketDate" />
										&depotid=<s:property value="depotId" />
										&tripNo=<s:property value="tripno" />
										&shift_no=<s:property value="shift_No" />
										'> --%>
										<s:property value="noOfTickets" /><!-- </a> --></td>
									</tr>
								</s:iterator>
								
								<tr>
								<td colspan="7" align="center">Total</td>
								<td align="center"><s:property value="totalAmount" /></td>
								<td align="center"><s:property value="totalTickets" /></td>
								
								</tr>
								
							</tbody>
						</table>





					</div>


				</div>
			</div>
		</div>
	</div>
	</form>
</body>
</html>