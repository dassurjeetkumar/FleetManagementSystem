<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
<%-- <script type="text/javascript">$(document).ready(function () { --%>
<!-- // 	fixHeader(); -->
<%--                   });</script> --%>
<script>
// $(document).ready(function() {
// 	document.forms[0].action = 'showOnlineWaybillsGprs.action';
// 	document.forms[0].submit();
// 	});

	$(document).ready(function() {
		//alert("hiii");
		getTripOnLineTicket(this.value);
		});

	function goView() {
		document.forms[0].action = 'showOnlineWaybillsGprs.action';
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
</style>
<script>
function getTripOnLineTicket(value) {
		
	   var depotName = document.getElementById("depotName").value;
		var startdate = document.getElementById("startdate").value;
		//getShowWayBill(depotName, startdate,'Submit');
		 $.ajax({
	               type: "get",
	                url: '<%=request.getContextPath()%>/getTripTicketWayBill.action?depotName='+depotName+'&startdate='+startdate,
				    success : function(result) {
					document.getElementById('depotName').innerHTML = result;
				}
			});

	}
	
	
	</script>

<title>Insert title here</title>
</head>
<body>
	<div class="page-content-wrapper">
		<div class="page-content">

			<div class="tab-content">
				<div class="row"></div>
				<div class="row">
					<div class="col-md-12">
						<!-- BEGIN PAGE TITLE & BREADCRUMB-->
						<h3 class="page-title">
							GPRS Waybill Information <small></small>
						</h3>

						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>View GPRS Waybills
							</div>
							<!-- <div class="tools">
								<a href="javascript:;" class="collapse"> </a> 
								 <a href="javascript:;" class="reload"> </a> 
							</div> -->
						</div>

						<div class="portlet-body form">
							<div class="col-md-12" align="left" style="font-size: 1.1em">

								<%-- <span class="help-block" style="color: red; list-style: none"><s:actionerror /></span> --%>
								<span class="help-block" style="color: red; list-style: none"><s:actionmessage /></span>
							</div>

							<!-- BEGIN FORM-->
							<form action="showOnlineWaybillsGprs.action" method="post"
								class="form-horizontal">


<!-- 								<div class="form-group"> -->
<%-- 									<label class="col-md-3 control-label">Depot Code<sup><font --%>
<%-- 											color="red">*</font></sup></label> --%>
<!-- 									<div class="col-md-3"> -->
<%-- 										<s:select list="" id="depotId" name="depotId" --%>
<%-- 											cssClass="select2_category form-control"></s:select> --%>
<%-- 										<s:if test="fieldErrors.get('depotId').size() > 0"> --%>
<%-- 											<span style="color: red;"><s:property --%>
<%-- 													value="fieldErrors.get('depotId').get(0)" /></span> --%>
<%-- 										</s:if> --%>
<!-- 									</div> -->

<!-- 								</div> -->

<!-- 								<div class="form-group"> -->
<%-- 									<label class="col-md-3 control-label">Date<sup><font color="red">*</font></sup> --%>
<!-- 									</label> -->
<!-- 									<div class="col-md-3"> -->
<!-- 										<div class="input-group input-small date date-picker" -->
<!-- 											data-date-format="dd-mm-yyyy"> -->
<%-- 											<s:textfield name="ticketDate" id="ticketDate" cssClass="form-control input-small" readonly="readonly"></s:textfield> --%>
<%-- 											<s:textfield name= cssClass="form-control input-small" id="ticketDate" readonly></s:textfield> --%>
<%-- 											 <span --%>
<%-- 												class="input-group-btn"> --%>
<!-- 												<button class="btn default" type="button"> -->
<!-- 													<i class="fa fa-calendar"></i> -->
<!-- 												</button> -->
<%-- 											</span> --%>
<%-- 											<s:if test="%{ticketDate ==null}"> --%>
<%-- 											<script> --%>
<!-- // 												var curr_date = new Date() -->
<!-- // 														.toJSON().slice(0, 10); -->
<!-- // 												curr_date = curr_date -->
<!-- // 														.split("-"); -->
<!-- // 												curr_date = curr_date[2] + "-" -->
<!-- // 														+ curr_date[1] + "-" -->
<!-- // 														+ curr_date[0]; -->
<!-- // 												$('#ticketDate').val(curr_date); -->
<%-- 											</script> --%>
<%-- 											</s:if> --%>
<!-- 										</div> -->

<!-- 									</div> -->
<!-- 								</div> -->


<!-- 								<div class="form-actions fluid"> -->
<!-- 									<div class="col-md-offset-3 col-md-9"> -->
<!-- 										<button type="submit" class="btn blue">Submit</button> -->
<!-- 										<button type="button" class="btn default" onclick="goView()">Cancel</button> -->
<!-- 									</div> -->
<!-- 								</div> -->
							<div>
						<input type="hidden" name="formfourID"
							value="<s:property value="formfour.id" />" />
							
							<input type="hidden" name="depotName" value='<s:property value="depotNameForTicket"/>' id ="depotName">
						  <input type="hidden" name="startdate" value='<s:property value="dateforonlineticket"/>' id ="startdate">
						<table class="table table-striped table-bordered table-hover" id="triptable1" style="height: 50px" >

							<thead>
								<tr bgcolor="#819FF7">
								<th scope="col" align="center" width="70px">Sr. No.</th>
									<th scope="col" align="center" width="150px">Waybill No</th>
									<th scope="col" align="center" width="140px">Schedule No</th>
									<th scope="col" align="center" width="140px">Schedule Type</th>
									<!-- <th scope="col" align="center">Duty Type</th> -->
									<th scope="col" align="center" width="150px">ETM No</th>
									<!-- <th scope="col" align="center">Route No</th> -->
									<th scope="col" align="center" width="130px">Vehicle No</th>
									<th scope="col" align="center" width="150px">Total Amount</th>
									<th scope="col" align="center" width="140px">Passenger Count</th>
									<th scope="col" align="center" width="130px">Ticket Count</th>

								</tr>
							</thead>
							<tbody>

								<s:iterator value="showWaybill" var="trips" status="ctr">

									<tr>
										<td align="center" width="70px"><s:property value="%{#ctr.index + 1}" /></td>
										<td align="center" width="150px"><a href='ShowGPRSWaybillWiseDayWise.action?waybillno=<s:property value="waybillNo" />&ticketdate=<s:property value="ticketDate" />&depotid=<s:property value="depotId" />'><s:property value="waybillNo" /></a></td>
										<td align="center" width="140px"><s:property value="scheduleNo" /></td>
										<td align="center" width="140px"><s:property value="scheduleType" /></td>
										<%-- <td align="center"><s:property value="shiftType" /></td> --%>
										<td align="center" width="150px"><s:property value="etimNo" /></td>
										<%-- <td align="center"><s:property value="routeNo" /></td> --%>
<%-- 										<td align="center"><s:property value="vehicleNo" /></td> --%>
                                       <td align="center" width="130px"><a href="ShowLiveBusDetailsAll?vehicleNo=<s:property value='vehicleNo'/>&ticketdate=<s:property value="ticketDate" />&deviceser=<s:property value='device_serial_number' />&depotId=<s:property value='depotId'/>&orgname=<s:property value='orgname'/>"  target="popup"  onclick="window.open('ShowLiveBusDetailsAll?vehicleNo=<s:property value='vehicleNo'/>&ticketdate=<s:property value='ticketDate' />&deviceser=<s:property value='device_serial_number' />&depotId=<s:property value='depotId'/>&orgname=<s:property value='orgname'/>','popup','width=1000,height=1000,left=400,top=200,scrollbars=yes,resizable=yes,'); return false;"><s:property value="vehicleNo" /></a></td>
<!--                                   <a href="http://localhost:8080/hello.action" onclick="javascript:void window.open('http://localhost:8080/Strutslearning/hello.action','1379313271736','width=700,height=500,toolbar=0,menubar=1,location=0,status=1,scrollbars=1,resizable=1,left=0,top=0');return false;">Pop-up Window</a> -->
										<td align="center" width="150px"><s:property value="total_ticket_amount" /></td>
										<td align="center" width="140px"><s:property value="px_count" /></td>
										<td align="center" width="130px"><a href='showOnlineWaybillsGprswaybillwise.action?waybillno=<s:property value="waybillNo" />&ticketdate=<s:property value="ticketDate" />&vehicleNo=<s:property value='vehicleNo'/>&deviceser=<s:property value='device_serial_number' />&orgname=<s:property value='orgname'/>&depotid=<s:property value="depotId" />'><s:property value="noOfTickets" /></a></td>
									</tr>
								</s:iterator>
								<tr>
								<td colspan="6" align="center">Total</td>
								<td align="center"><s:property value="totalAmount" /></td>
								<td align="center"><s:property value="totalPassengers" /></td>
								<td align="center"><s:property value="totalTickets" /></td>
								
								</tr>
							</tbody>
						</table>





					</div>

							</form>
							<div id="alertDiv"></div>
							<!-- END FORM-->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>