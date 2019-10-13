<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
<link rel="stylesheet"	href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />

<script>
	function goView() {
		document.forms[0].action = 'showOnlineWaybillsGprs.action';
		document.forms[0].submit();
	}
</script>

<script type="text/javascript">
function printDiv() {     
	
	   document.getElementById("triptable1").style.visibility='visible';  
	   
  document.getElementById("header").style.display='block';
  document.getElementById("header").style.visibility='visible';     
var divElements=document.getElementById("header").innerHTML;
    divElements += "Date :"+$('#ticketDate').val();
    divElements+= "<table>"+document.getElementById("triptable1").innerHTML+"</table>";
  
 //divElements += document.getElementById("schdeviation").innerHTML;

  var mywindow = window.open("<%=request.getContextPath()%>/Print.jsp");
 
  mywindow.onload = function() {
      mywindow.document.body.innerHTML=divElements;
      mywindow.document.body.innerHTML=divElements;
  //	mywindow.document.body.innerHTML = divElement;
      //   document.getElementById("print").style.visibility='';
      mywindow.print();
      mywindow.close();
  }
  document.getElementById("header").style.display = 'none';
		document.getElementById("header").style.visibility = 'hidden';
		
  
          
}


function tabletoExcel(){     
      
	$('triptable1.preview *').removeAttr('style');
	document.getElementById("header").style.display='block';
	  document.getElementById("header").style.visibility='visible';     
	var divElements=document.getElementById("header").innerHTML;
	    divElements += "Date :"+$('#ticketDate').val();
	 divElements += "<table>"+document.getElementById("triptable1").innerHTML+"</table>";
		var noOfTableExist = 0;
		try {
			$("#triptable1 table").each(function() {
				noOfTableExist++;
			});

			console.log("Total no of tables : " + noOfTableExist);
			/* If two table exist  then remove the last table on print click (for header fixed)*/
			if (noOfTableExist >= 2) {
				divElements = divElements.substring(0, divElements
						.indexOf("</table>") + 8)
						+ "</div></div>";
			}
		} catch (err) {
			console.log("ExceptionCaught : " + err);
		}

		var downloadLink = document.createElement("a");
		downloadLink.href = 'data:application/vnd.ms-excel,'
				+ encodeURIComponent(divElements);
		downloadLink.download = "Online ETM GPRS Report.xls";
		document.body.appendChild(downloadLink);
		downloadLink.click();
		document.body.removeChild(downloadLink);
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
							<div class="caption" id="caption">
								<i class="fa fa-gift"></i>View GPRS Waybills
							</div>
							
								<div class="actions">
						<input type='button' class='btn blue' id='button1' onclick='printDiv()' value='Print' />													
						<button type="button" class='btn blue' id="btnExport" filename= "report" onclick="tabletoExcel();"> EXPORT </button>
						</div>
						</div>

						<div class="portlet-body form">
							<div class="col-md-12" align="left" style="font-size: 1.1em">

								<%-- <span class="help-block" style="color: red; list-style: none"><s:actionerror /></span> --%>
								<span class="help-block" style="color: red; list-style: none"><s:actionmessage /></span>
							</div>

							<!-- BEGIN FORM-->
							<form action="showOnlineWaybillsGprs.action" method="post"
								class="form-horizontal">


								<div class="form-group">
									<label class="col-md-3 control-label">Depot Code<sup><font
											color="red">*</font></sup></label>
									<div class="col-md-3">
										<s:select list="depotMap" id="depotId" name="depotId"
											cssClass="select2_category form-control"></s:select>
										<s:if test="fieldErrors.get('depotId').size() > 0">
											<span style="color: red;"><s:property
													value="fieldErrors.get('depotId').get(0)" /></span>
										</s:if>
									</div>

								</div>

								<div class="form-group">
									<label class="col-md-3 control-label">Date<sup><font color="red">*</font></sup>
									</label>
									<div class="col-md-3">
										<div class="input-group input-small date date-picker"
											data-date-format="dd-mm-yyyy">
											<s:textfield name="ticketDate" id="ticketDate" cssClass="form-control input-small" readonly="readonly"></s:textfield>
											<%-- <s:textfield name= cssClass="form-control input-small" id="ticketDate" readonly></s:textfield> --%>
											 <span
												class="input-group-btn">
												<button class="btn default" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
											<s:if test="%{ticketDate ==null}">
											<script>
												var curr_date = new Date()
														.toJSON().slice(0, 10);
												curr_date = curr_date
														.split("-");
												curr_date = curr_date[2] + "-"
														+ curr_date[1] + "-"
														+ curr_date[0];
												$('#ticketDate').val(curr_date);
											</script>
											</s:if>
										</div>

									</div>
								</div>
								


								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn blue">Submit</button>
										<button type="button" class="btn default" onclick="goView()">Cancel</button>
									</div>
								</div>
							<div>
						<input type="hidden" name="formfourID"
							value="<s:property value="formfour.id" />" />
						<table class="table table-striped table-bordered table-hover" id="triptable1" style="height: 50px" >

							<thead>
								<tr bgcolor="#819FF7">
								<th scope="col" align="center" width="55px">S No</th>
									<th  align="center" width="400px">Waybill &nbsp;&nbsp;No.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
									<th scope="col" align="center">Schedule No.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
									<th scope="col" align="center" width="128px">Shift Type&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
									<!-- <th scope="col" align="center">Duty Type</th> -->
									<th scope="col" align="center">ETM No.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
									<th scope="col" align="center">Version No.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
									<!-- <th scope="col" align="center">Route No</th> -->
									<th scope="col" align="center" >Vehicle No.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
									<th scope="col" align="right" >Conductor Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
									<th scope="col" align="center" >Cash Amt&nbsp;&nbsp;&nbsp;</th>
									<th scope="col" align="center" width="250px">Card Amt</th>
									<th scope="col" align="center" width="250px">Service Tax Amt</th>
									<th scope="col" align="center" width="132px">Total Amount</th>
									<th scope="col" align="center" width="129px">Passenger Count</th>
									<th scope="col" align="center" width="130px">Ticket Count</th>
									<th scope="col" align="center" width="130px">Battery Status</th>

								</tr>
							</thead>
							<tbody>

								<s:iterator value="showWaybill" var="trips" status="ctr">

									<tr>
										<td align="center" width="45px"><s:property value="%{#ctr.index + 1}" /></td>
										<td align="left" width="140px"><a href='ShowGPRSWaybillWiseDayWise.action?waybillno=<s:property value="waybillNo" />&ticketdate=<s:property value="ticketDate" />&depotid=<s:property value="depotId" />'><s:property value="waybillNo" /></a></td>
										<td align="center" width="143px"><s:property value="scheduleNo" /></td>
										<td align="center" width="302px"><s:property value="scheduleType" /></td>
										<%-- <td align="center"><s:property value="shiftType" /></td> --%>
										<td align="center" width="144px"><s:property value="etimNo" /></td>
										<td align="center" width="156px"><s:property value="ticket_code" /></td>
										<%-- <td align="center"><s:property value="routeNo" /></td> --%>
<%-- 										<td align="center"><s:property value="vehicleNo" /></td> --%>
<%--                                        <td align="center" width="130px"><a href="ShowLiveBusDetailsAll?vehicleNo=<s:property value='vehicleNo'/>&ticketdate=<s:property value="ticketDate" />&deviceser=<s:property value='device_serial_number' />&depotId=<s:property value='depotId'/>&orgname=<s:property value='orgname'/>"  target="popup"  onclick="window.open('ShowLiveBusDetailsAll?vehicleNo=<s:property value='vehicleNo'/>&ticketdate=<s:property value='ticketDate' />&deviceser=<s:property value='device_serial_number' />&depotId=<s:property value='depotId'/>&orgname=<s:property value='orgname'/>','popup','width=1000,height=1000,left=400,top=200,scrollbars=yes,resizable=yes,'); return false;"><s:property value="vehicleNo" /></a></td> --%>
                                      <td align="center" width="130px"><s:property value="vehicleNo" /></td>
                                      <td align="center" width="135px"><s:property value="conductorname" /></td>
                                     <td align="center"><s:property value="cash_amt" /></td>
										<td align="center"><s:property value="card_amt" /></td>
										<td align="center"><s:property value="service_tax_amt" /></td>
<!--                                   <a href="http://localhost:8080/hello.action" onclick="javascript:void window.open('http://localhost:8080/Strutslearning/hello.action','1379313271736','width=700,height=500,toolbar=0,menubar=1,location=0,status=1,scrollbars=1,resizable=1,left=0,top=0');return false;">Pop-up Window</a> -->
										<td align="center" width="150px"><s:property value="total_ticket_amount" /></td>
										<td align="center" width="155px"><s:property value="px_count" /></td>
										<td align="center" width="130px"><a href='showOnlineWaybillsGprswaybillwise.action?waybillno=<s:property value="waybillNo" />&ticketdate=<s:property value="ticketDate" />&vehicleNo=<s:property value='vehicleNo'/>&deviceser=<s:property value='device_serial_number' />&orgname=<s:property value='orgname'/>&depotid=<s:property value="depotId" />'><s:property value="noOfTickets" /></a></td>
									
									  <s:if test="%{battery_status > 50}">
									    <td align="center"  ><s:property value="battery_status" /></td>
									   </s:if>
									   <s:else>
									    <td align="center" style="background-color:orange"><s:property value="battery_status" /></td>
									    </s:else>
									
									</tr>
								</s:iterator>
								<tr>
								<td colspan="7" align="center">Total</td>
								<td></td>
								<td align="center"><s:property value="totalCashAmt"/></td>
								<td align="center"><s:property value="totalCardAmt"/></td>
								<td align="center"><s:property value="totalServiceTaxAmt"/></td>
								<td align="center"><s:property value="totalAmount" /></td>
								<td align="center"><s:property value="totalPassengers" /></td>
								<td align="center"><s:property value="totalTickets" /></td>
								
								</tr>
							</tbody>
						</table>
					</div>
							</form>
							
							<div id='header' style='display: none;'>
							<br><div align='center'><h4>ONLINE WAYBILL INFORMATION REPORT</div>
							<!-- END FORM-->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>