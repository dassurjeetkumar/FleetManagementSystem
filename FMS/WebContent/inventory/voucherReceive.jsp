<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
<style>
th,td{
	text-align: center;
}
#voucherTable th{
	font-size: 16px;
}
</style>
<body onload="getData('true')">
<!-- <div id="pageLoader" class="modal fade in" tabindex="-1" role="dialog" aria-labelledby="myModalLabel3" aria-hidden="false">
	<div class="modal-dialog">
		<div class="modal-content">
			
			<div class="modal-body" id="geterror">
				<p>
					<img src="assets/images/loader1.gif" align="top" style="margin-left:10px;"/> Please wait synchronization in process..
				</p>
			</div>
			
		</div>
	</div>
</div> -->
<div class="page-content-wrapper">
	<div class="page-content">
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
					TICKET INVENTORY
				</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i>Voucher Receive
						</div>
					</div>
					<div class="portlet-body">
						<form action="#" class="form-horizontal form-row-seperated" method="post">	
							<div class="portlet-body form">
								<%-- <div class="form-group">
									<label class="control-label col-md-3">Division</label>
									<div class="col-md-3">
										<select class="select2_category form-control" id="divisions" onchange="getDepot()">
											<option value="0">Select</option>
											<s:iterator value="divisionList">  
												<option value="<s:property value="key"/>"><s:property value="value" /></option>  
											</s:iterator>  
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">Depot</label>
									<div class="col-md-3">
										<select class="select2_category form-control" id='depot'>
											<option value="0">Select</option>
										</select>
									</div>
									&nbsp;&nbsp;<input type="button" class="btn grey" onclick="getData('true')" value="Get Data"/>
								</div>
								<br/><br/> --%>
								<div class="form-group" >
									<center><table id="voucherTable" class="table table-striped table-bordered table-hover" style="width:90%;">
										<tr>
											<th>Voucher Number</th>
											<th>Voucher Type</th>
											<th>From Organization</th>
											<th>To Organization</th>
											<th>Accept</th>
											<th>Reject</th>
											<th>View Details</th>
										</tr>
										<tr></tr>
									</table>
									</center>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div style="display: none" id="myModal11" class="modal fade"
	tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
	aria-hidden="true"   data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width:70%">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title"><b>Details</b></h3>
			</div>
			<div class="modal-body" >
				<table class="table table-bordered   table-striped">
						<tr>
							<th>Voucher number</th>
							<th>From Organiztion</th>
							<th>To Organiztion</th>
						</tr>
					<tr>
						<th id='voucherNum'></th>
						<th id='fromOrg'></th>
						<th id='toOrg'></th>
					</tr>
				</table>
				<div  id="scrollable-div2" >
					<table class="table table-striped table-bordered table-hover" id="popUpDetailTable" >
						<tr>
							<th>Sr No</th>
							<th>Ticket Type</th>
							<th>Denom Value</th>
							<th>Denom Key</th>
							<th>Opening No</th>
							<th>Closing No</th>
							<th>No.of Tickets</th>
							<th>No.of Books</th>
							<th>Details</th>
						</tr>
					</table>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn default"  id='saveButton' ><i class="fa fa-times"></i></button>
				<!-- <button type="button" class="btn green"  onclick='return saveIds()'>Submit</button> -->
			</div>
		</div>
	</div>
</div>
<div style="display: none" id="myModal12" class="modal fade"
	tabindex="-1" role="dialog" aria-labelledby="myModalLabel2"
	aria-hidden="true"   data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width:70%">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title"><b>Details</b></h3>
			</div>
			<div class="modal-body" >
				<div  id="scrollable-div2" >
					<table class="table table-striped table-bordered table-hover" id="popdUpDetailTable" >
						<tr>
							<th>Sr No</th>
							<!-- <th>Denom Value</th>
							<th>Denom Key</th> -->
							<th>Opening No</th>
							<th>Closing No</th>
							<th>No.of Tickets/Passes</th>
							<!-- <th>No.of Books</th> -->
						</tr>
						
					</table>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn default" ><i class="fa fa-times"></i></button>
				<!-- <button type="button" class="btn green"  onclick='return saveIds()'>Submit</button> -->
			</div>
		</div>
	</div>
</div>
</body>
<head>
<script>
var resultArray;
function voucherHeaderDetails(voucherId,depotId){
	var resultdata = $.ajax({
		type:"get",
		dataType : 'json',
		contentType : "application/json",
		global : false,
		async : false,
		data:{
			voucherId:voucherId,
			depotId:depotId
		},
		url:"VoucherDetails",
		success:function(result){
			return result;
		}
	}).responseText;
	//alert(resultdata);
	resultArray =jQuery.parseJSON(resultdata);
	$("#voucherNum").html(resultArray.voucherNumber);
	$("#fromOrg").html(resultArray.issueFromOrgName);
	$("#toOrg").html(resultArray.issueToOrgName);
	$('#popUpDetailTable > tbody > tr:gt(0)').remove();
	for(var i=0;i<resultArray.listOfMasterModel.length;i++){
		
		var invoiceMasterObject = resultArray.listOfMasterModel[i];
		$("#popUpDetailTable").append("<tr>");
		$("#popUpDetailTable tr:last").append("<td>"+(i+1)+"</td>");
		$("#popUpDetailTable tr:last").append("<td>"+invoiceMasterObject.ticketTypeManualName+"</td>");
		$("#popUpDetailTable tr:last").append("<td>"+invoiceMasterObject.denominationTypeManualName+"</td>");
		$("#popUpDetailTable tr:last").append("<td>"+invoiceMasterObject.denominationKey+"</td>");
		$("#popUpDetailTable tr:last").append("<td>"+padLeft(invoiceMasterObject.openingNumber,8)+"</td>");
		$("#popUpDetailTable tr:last").append("<td>"+padLeft(invoiceMasterObject.closingNumber,8)+"</td>");
		$("#popUpDetailTable tr:last").append("<td>"+invoiceMasterObject.noOfTickets+"</td>");
		$("#popUpDetailTable tr:last").append("<td>"+invoiceMasterObject.noOfBooks+"</td>");
		var button3 = '<a data-toggle="modal"  href="#myModal12" ><button type="button" class="btn grey" onclick="voucherFullDetails('+i+');">Details</button><a>';
		$("#popUpDetailTable tr:last").append("<td>"+button3+"</td>");
		$("#popUpDetailTable tr:last").append("</tr>");
	}
}
function voucherFullDetails(iterationNo){

	$('#popdUpDetailTable > tbody > tr:gt(0)').remove();
	for(var i=0;i<resultArray.listOfMasterModel[iterationNo].listOfTicketDetails.length;i++){
		
		var detailObject = resultArray.listOfMasterModel[iterationNo].listOfTicketDetails[i];
		$("#popdUpDetailTable").append("<tr>");
		$("#popdUpDetailTable tr:last").append("<td>"+(i+1)+"</td>");
		//$("#popdUpDetailTable tr:last").append("<td>"+detailObject.denominationTypeManualName+"</td>");
		//$("#popdUpDetailTable tr:last").append("<td>"+detailObject.denominationKey+"</td>");
		$("#popdUpDetailTable tr:last").append("<td>"+padLeft(detailObject.openingNumber,8)+"</td>");
		$("#popdUpDetailTable tr:last").append("<td>"+padLeft(detailObject.closingNumber,8)+"</td>");
		$("#popdUpDetailTable tr:last").append("<td>"+detailObject.noOfTickets+"</td>");
		//$("#popdUpDetailTable tr:last").append("<td>"+detailObject.noOfBooks+"</td>");
		$("#popdUpDetailTable tr:last").append("</tr>");
	}
}
function getDepot(){
	var divisionId = $("#divisions").val();
	console.log(divisionId);
	if(divisionId!="0"){
		var resultdata = $.ajax({
			type:"get",
			dataType : 'json',
			contentType : "application/json",
			global : false,
			async : false,
			data:{
				divisionId:divisionId
			},
			url:"GetDepots",
			success:function(result){
				return result;
			}
		}).responseText;
	
		var arrayResult =  resultdata.split(":"); 
		var dropDown="<option value='0'>Select</option>";
		for(var i=0;i<arrayResult.length;i++ ){
			var tokens = arrayResult[i].split(",");
			dropDown +="<option value='"+tokens[0]+"'>"+tokens[1]+"</option>";
		}
		$("#depot").html(dropDown);
	}else{
		$("#select2-chosen-2").html("Select");
		$("#depot").html("");
	}
	
}
function getData(firstTime){
	var depotId = $("#depot").val();
	if(depotId!='0'){
		var resultdata = $.ajax({
			type:"get",
			dataType : 'json',
			contentType : "application/json",
			global : false,
			async : false,
			data:{
				depotId:depotId
			},
			beforeSend:function(){
				//alert($("#voucherTable  > tbody > tr:eq(1)").html());
				$("#voucherTable  > tbody > tr:eq(1)").html('<td colspan="7"><img src="<%=request.getContextPath()%>/inventory/loading-icon.gif" height="55px" width="85px" style="padding-right:2px"/><h4>loading.. </h4></td>');
			},
			complete:function(){
				$("#voucherTable  > tbody > tr:eq(1)").html('');
				//$.unblockUI();
			},
			url:"GetVouchersOfDepot",
			success:function(result){
				return result;
			},
			error:function(e,x){
				alert(e+" "+x);
			}
		}).responseText;
	}else{
		bootbox.alert("Please Select Organization");
		return false;
	}
	$('#voucherTable > tbody > tr:gt(0)').remove();
	console.log("resultdata \t"+resultdata);
	var resultArray =jQuery.parseJSON(resultdata);
	console.log("resultArray \t"+resultArray);
	if(resultArray.length==0 && firstTime){
	//	bootbox.alert("No Vouchers Available");
		$("#voucherTable").append("<tr>");
		$("#voucherTable tr:last").append('<td colspan="7"><b>No Vouchers Available</b></td>');
		return false;
	}
	if(resultArray.length==0 && !firstTime){
		$("#voucherTable").append("<tr>");
		$("#voucherTable tr:last").append('<td colspan="7"><b>No Vouchers Available</b></td>');
		return false;
	}
	if(resultArray[0].error!= undefined){
		var alertString = (JSON.stringify(resultArray[0].error));
		//.replace("\"","").replace("\"","");
		bootbox.alert(alertString);
		return false;
	}
	$("#voucherTable").append("<tbody>");
	
	for(var i=0;i<resultArray.length;i++){
		$("#voucherTable").append("<tr>");
		$("#voucherTable tr:last").append('<td><b>'+resultArray[i].VoucherNumber+'</b></td>');
		$("#voucherTable tr:last").append('<td><b>'+resultArray[i].VoucherType+'</b></td>');
		$("#voucherTable tr:last").append('<td><b>'+resultArray[i].fromOrg+'</b></td>');
		$("#voucherTable tr:last").append('<td><b>'+resultArray[i].toOrg+'</b></td>');
		var object = new Array();
		var button1 = '<button type="button" class="btn blue" onclick="acceptVoucher('+resultArray[i].VoucherId+','+resultArray[i].depotId+',\''+resultArray[i].VoucherNumber+'\');" >Accept</button>';
		$('#voucherTable tr:last').append('<td id="accept'+resultArray[i].VoucherId+'">'+button1+'</td></tr>'); 
		var button2 = '<button type="button" class="btn red" onclick="rejectVoucher('+resultArray[i].VoucherId+','+resultArray[i].depotId+',\''+resultArray[i].VoucherNumber+'\');">Reject</button>';
		$('#voucherTable tr:last').append('<td id="reject'+resultArray[i].VoucherId+'">'+button2+'</td></tr>'); 
		var button3 = '<a data-toggle="modal"  href="#myModal11" ><button type="button" class="btn grey" onclick="voucherHeaderDetails('+resultArray[i].VoucherId+','+resultArray[i].depotId+');">Details</button><a>';
		$('#voucherTable tr:last').append('<td>'+button3+'</td></tr>'); 
		$("#voucherTable tr:last").append("</tr>");
	}
	$("#voucherTable").append("</tbody>");
}

function acceptVoucher(voucherId,depotId,voucherNo){
	bootbox.confirm("Are you sure want to Accept Voucher No <b> "+voucherNo+" </b>?",
	function(result) {
		if (result == true) {
			var resultdata = $.ajax({
				type:"get",
				dataType : 'json',
				contentType : "application/json",
				global : false,
				async : false,
				data:{
					voucherId:voucherId,
					depotId:depotId
				},
				url:"AcceptVoucher",
				success:function(result){
					return result;
				},beforeSend:function(){
					//alert($("#voucherTable  > tbody > tr:eq(1)").html());
					$("#accept"+voucherId).html('<img src="<%=request.getContextPath()%>/inventory/loading-icon.gif" height="55px" width="85px" style="padding-right:2px"/>');
				},
				complete:function(){
					$("#accept"+voucherId).html('');
					//$.unblockUI();
				}
			}).responseText;
			bootbox.alert("Voucher Number '"+voucherNo+"' Accepted successfully ");
			getData(false);
		}
	});
}
function rejectVoucher(voucherId,depotId,voucherNumber){
	bootbox.confirm("Are you sure want to Reject Voucher No <b> "+voucherNumber+" </b>?",
	function(result) {
		if (result == true) {
			var resultdata = $.ajax({
				type:"get",
				dataType : 'json',
				contentType : "application/json",
				global : false,
				async : false,
				data:{
					voucherId:voucherId,
					depotId:depotId
				},
				url:"RejectVoucher",
				success:function(result){
					return result;
				},beforeSend:function(){
					//alert($("#voucherTable  > tbody > tr:eq(1)").html());
					$("#reject"+voucherId).html('<img src="<%=request.getContextPath()%>/inventory/loading-icon.gif" height="55px" width="85px" style="padding-right:2px"/>');
				},
				complete:function(){
					$("#reject"+voucherId).html('');
					//$.unblockUI();
				}
			}).responseText;
			bootbox.alert("Voucher Number '"+voucherNumber+"' rejected successfully ");
			getData(false);
		}
	});
}
function padLeft(nr, n, str){
    return Array(n-String(nr).length+1).join(str||'0')+nr;
}
</script>
</head>
</html>