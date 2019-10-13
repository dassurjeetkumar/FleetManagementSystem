
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Expires" content="-1">
<style>
p.intro {
	color: red;
	font-size: 14px;
}

p.success {
	color: green;
	size: 2px;
}

.centerize {
	display: block;
	margin: 0 auto;
	text-align: center;
}

#table tr td,th {
	text-align: center;
}
</style>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"
	type="text/javascript"></script>
<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.min.js"
	type="text/javascript"></script>
<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.min.js"
	type="text/javascript"></script>
</head>
<body>
	<form action="saveTicketReturn.action" name="viewticketform">
		<div class="page-content-wrapper">
			<div class="page-content">
				<div id="vouncherid">
					<div class="row">
						<div class="col-md-12">
							<h3 class="page-title">
								<small></small>
							</h3>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="portlet box grey-cascade">
								<div class="portlet-title">
									<div class="caption">
										<i class="fa fa-globe"></i><b>Voucher Details  </b>
									</div>
								</div>
								<div class="portlet-body" id="step1Content">
									<div id="vouncherdisplayid"></div>
								</div>
								<div class="portlet-body" id="step1Content">
									<div>
									<br/>
									<div class=" grey-cascade">
										<div class="portlet-title">
											<div class="caption" style="font-size: 16px">
													<b>Voucher Number : </b><s:property value='voucherNumber'/>
													<br/><br/><br/>
													<div id="issuedTo" style="font-size: 16px">
											</div>
											
										</div>
									</div>
									<br/>
									<div class=" grey-cascade">
											<div class="portlet-title">
												<div class="caption" style="font-size: 16px">
													<b>Ticket Type</b>
												</div>
												<br/>
											</div>
										</div>
									</div>
									<input type="hidden" name="requestType" id="requestType"
										value="text" />
									<div id="vouncherticketmanualid"></div>
								</div>
								<div class="portlet-body" id="step1Content">
								<br/>
									<div class=" grey-cascade">
										<div class="portlet-title">
											<div class="caption" style="font-size: 16px">
												<b> Pass Type</b>
											</div>
											<br/>
										</div>
									</div>
									<div id="successmsgpass" style="display: none">
									<b>	<p class="success" id="succpass"></p></b>
										<span> </span>
									</div>
									<div id="ticketVouncherpassid"></div>
								</div>
								<div class="portlet-body" id="step1Content">
								<br/>
									<div class="grey-cascade">
										<div class="portlet-title">
											<div class="caption" style="font-size: 16px">
												<b>Luggage Type</b>
											</div>
											<br/>
										</div>
									</div>
									<input type="hidden" name="requestType" id="requestType"
										value="text" />
									<div id="successmsgluggage" style="display: none">
										<b><p class="success" id="succluggage"></p></b>
										<span> </span>
									</div>
									<div id="ticketVouncherLuggageid"></div>
									<div>
									<div class="portlet-body" id="step1Content">
								<br/>
									<div class=" grey-cascade">
										<div class="portlet-title">
											<div class="caption" style="font-size: 16px">
												<b> Trip Sheet Type</b>
											</div>
											<br/>
										</div>
									</div>
									<div id="successmsgtsheet" style="display: none">
									<b>	<p class="success" id="succtsheet"></p></b>
										<span> </span>
									</div>
									<div id="ticketVounchertsheetid"></div>
								</div>
									<br/>
									<br/>
									<div class="grey-cascade">
										<div class="portlet-title">
											<div class="caption" style="font-size: 16px">
											<b>Total</b>
											</div>
											<br/>
										</div>
									<table border='1'  class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;border-collapse: collapse;'>
										<tr>
										    <td style='text-align: center'> </td>
											<td style='text-align: center' colspan='4'><b>Total No.of ticket</b></td>
											<td style='text-align: center'><b>Total no.of books</b></td>
											<td style='text-align: center'><b>Total value</b></td>
										</tr>
										<tr>
										<td >Ticket Type </td>
					
											<td style='text-align: center' id='totalNormalTicket' colspan='4'></td>
											<td style='text-align: center' id='totalNormalbook'></td>
											<td style='text-align: center' id='totalNormalValue'></td>
										</tr>
										<tr>
										<td>Pass Type</td>
											<td style='text-align: center' id='totalPassTicket' colspan='4'></td>
											<td style='text-align: center' id='totalPassBook'></td>
											<td style='text-align: center' id='totalPassValue'></td>
										</tr>
										<tr>
										<td>Luggage Type </td>
											<td style='text-align: center' id='totalLuggageTicket' colspan='4'></td>
											<td style='text-align: center' id='totalLuggageBook'></td>
											<td style='text-align: center' id='totalLuggageValue'> - </td>
										</tr>
										<tr>
										<td>Trip Sheet Type </td>
											<td style='text-align: center' id='totalTsheets' colspan='4'></td>
											<td style='text-align: center' id='totalTsheets'></td>
											<td style='text-align: center' id='totaTsheetValue'> - </td>
										</tr>
										<tr><td></td>
											<td style='text-align: center' id='finalTotalTickets' colspan='4'></td>
											<td style='text-align: center' id='finalTotalBooks'></td>
											<td style='text-align: center' id='finalTotalValue'></td>
										</tr>
										</table>
									</div>
									<br>
									<div id='pid' align='center'>
										<table>
											<tr>
												<td><input type='button' class='btn green' id='button1' 	onclick='printDiv()' value='Print' /></td>
												<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
												<td><input type='button' class='btn green' onclick='backDiv()' value='Back' /></td>
											</tr>
										</table>
									</div>
									<br>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
	</form>
	<form id="backshowid" action="viewVouncherlist.action"></form>
</body>
<script>
function printDiv() {     
     document.getElementById("pid").style.visibility='hidden';     
     var divElements = document.getElementById("vouncherid").innerHTML;
     var mywindow = window.open("<%=request.getContextPath()%>/Print.jsp");
		mywindow.onload = function() {
			mywindow.document.body.innerHTML = divElements;
			document.getElementById("pid").style.visibility = '';
			mywindow.print();
			mywindow.close();
		}
	}
	function backDiv() {
		$('#backshowid').submit();
	}
	$(document).ready(function() {
		
		voucherDetails();
		VouncherShowManual();
		//VouncherShowDetails();

	});
	function voucherDetails(){
		var voucherId = "<s:property value='voucherId'/>";
		$.ajax({
			type:'GET',
			data : {
				voucherId:voucherId
			},
			url : "viewticketVouncherRecordsManual!getVoucherHeaderDetails",
			success : function(data) {
				$("#issuedTo").html(data);
			},
			
		});
	}
	function VouncherShowManual() {
		var voucherId = "<s:property value='voucherId'/>";
		$.ajax({
			type : 'POST',
			data : {
				id : 1,
				voucherId : voucherId,
			},
			url : "<s:url action='viewticketVouncherRecordsManual'/>",
			success : function(data) {
				console.log("in ajax");
				$("#vouncherticketmanualid").html(data);
				VouncherPassShow();
			},
			error : function(xhr, errmsg) {
				console.log("in ajax error" + errmsg);
			}
		});
	}
	function VouncherPassShow() {

		var voucherId = "<s:property value='voucherId'/>";
		$.ajax({
			type : 'POST',
			data : {
				id : 1,
				voucherId : voucherId,
			},
			url : "<s:url action='viewticketVouncherRecordsPass'/>",
			success : function(data) {
				console.log("in ajax");
				$("#ticketVouncherpassid").html(data);
				VouncherLuggageShow();
			},
			error : function(xhr, errmsg) {
				console.log("in ajax error" + errmsg);
			}
		});
	}

	function VouncherLuggageShow() {

		var voucherId = "<s:property value='voucherId'/>";
		$.ajax({
			type : 'POST',
			data : {
				id : 1,
				voucherId : voucherId,
			},
			url : "<s:url action='viewticketVouncherRecordsLuggage'/>",
			success : function(data) {
				console.log(data);
				$("#ticketVouncherLuggageid").html(data);
				VouncherTsheetShow();
			}, 
			error : function(xhr, errmsg) {
				console.log("in ajax error" + errmsg);
			}
		});
	}
	function VouncherTsheetShow() {

		var voucherId = "<s:property value='voucherId'/>";
		$.ajax({
			type : 'POST',
			data : {
				id : 1,
				voucherId : voucherId,
			},
			url : "<s:url action='viewticketVouncherRecordsTsheet'/>",
			success : function(data) {
				console.log(data);
				$("#ticketVounchertsheetid").html(data);
				calculateTotal();
			}, 
			error : function(xhr, errmsg) {
				console.log("in ajax error" + errmsg);
			}
		});
	}
	
	function calculateTotal(){
		$("#totalNormalTicket").html(Number($("#subtotmanualticket").text()));
		$("#totalNormalbook").html(Number($("#subtotmanualbook").text()));
		$("#totalNormalValue").html(Number($("#subtotmanualvalue").text()));
		
		$("#totalPassTicket").html(Number($("#subtotpassticket").text()));
		$("#totalPassBook").html(Number($("#subtotpassbook").text()));
		$("#totalPassValue").html(Number($("#subtotpassvalue").text()));
		
		$("#totalLuggageTicket").html(Number($("#subtotluggageticket").text()));
		$("#totalLuggageBook").html(Number($("#subtotluggagebook").text()));
		//$("#totalNormalTicket").html(Number($("#subtotmanualticket").text()));
		$("#totalTsheets").html(Number($("#subtottsheet").text()));
		
		
		//totalNormalbook
		var finalTotalTickets = Number($("#subtotmanualticket").text())+Number($("#subtotpassticket").text())+Number($("#subtotluggageticket").text())+Number($("#subtottsheet").text());
		$("#finalTotalTickets").html(finalTotalTickets);
		var finalTotalBooks =  Number($("#subtotmanualbook").text())+Number($("#subtotpassbook").text())+Number($("#subtotluggagebook").text());
		$("#finalTotalBooks").html(finalTotalBooks);
		var finalTotalValue =  Number($("#subtotmanualvalue").text())+Number($("#subtotpassvalue").text());
		$("#finalTotalValue").html(finalTotalValue);
	}
	/* function VouncherShowDetails(){
	 $.ajax({
	 type : 'POST',
	 data: {
	 id: 1,
	 },
	 url : "<s:url action='viewticketVouncherRecordsShow'/>",
	 success: function(data){
	 console.log("in ajax");
	 $("#vouncherdisplayid").html(data);
	 },
	 error : function(xhr, errmsg) {
	 console.log("in ajax error"+errmsg);
	 }
	 });
	 }  */
</script>

</html>
