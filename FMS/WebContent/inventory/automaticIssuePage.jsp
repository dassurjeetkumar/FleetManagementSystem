<!--
	author : Satya
	date : 26/12/2014 
	purpose : Automatic Issue
 -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<style type="text/css">

#control-label{/* for label style*/
 	width: 100px;
}
.table-hover{
	border:1px solid #dddddd;
	width:99%
}
#table-responsive{
	color:#000000;
}
#scrollable-div{
	max-height:200px;
	/* overflow-y: scroll; */ 
}	
</style>
<script>
function deleteThisRow(event,className,denom){
	try{
		if(denom!="")
		$("."+className+""+denom).closest('tr').remove();
		else
			$("."+className).closest('tr').remove();
	}catch(e){
		alert(e);
	}
}
function isNormalDenomAlreadyThere(denom){
	$(".normalDenom"+denom).closest('tr').remove();
	$(".hiddenNormalDenom"+denom).closest('tr').remove();
}
function isPassAlreadyThere(denom){
	
	$(".passDenom"+denom).closest('tr').remove();
	$(".hiddenPassDenom"+denom).closest('tr').remove();
}
function  clearAllLuggague(){
	$("#luggageTicketTable tbody tr:gt(0)").remove();
	$("#luggageHiddenTicketTable tbody tr").remove();
	
}
function getNormalTickets(tableId,hiddenTableId){
	var count = $("#countOfBooksForNormalTicket").val();
	var denomType = $("#normalDenomId").val();
	var orgId = "<s:property value='orgId'/>";
	var denomvalue = $("#normalDenomId  option:selected").text().trim();
	
	 //alert(count);
	if(denomType!="0"){
		if(Math.round(count) == count && count!='') {
			count = Math.round(count);
		$('#'+tableId).show();
		var total;
		if($("#totalNormalTicketsValue").val()==undefined)
			total = 0;
		else
			 total = $("#totalNormalTicketsValue").html();
		//$('#'+tableId+' tr').slice(1).remove();
		//$('#'+hiddenTableId).show();
		//$('#'+tableId).closest('tr').remove();
		var responsData = $.ajax({
		    type: 'POST',
		    async:false,
		    data: {
		       count:count,
		       orgId:orgId,
		       denomType:denomType,
		    },
		    url:  "<s:url action='normalTicketsByNoOfBooks'/>",
		    success: function(result){
		    	var resultArray = result.split("@");
		    	var finalData = JSON.parse(resultArray[0]);
		    	if(finalData.length>0){
		    		isNormalDenomAlreadyThere(denomvalue);
			    	for ( var i = 0; i < finalData.length; i++) {
			    		$("#"+tableId).append("<tr>");
			    		$("#"+tableId+" tr:last").append("<td class='normalDenom"+finalData[i].denominationValue+"'>"+finalData[i].denominationValue+"</td>");
			    		$("#"+tableId+" tr:last").append("<td>"+finalData[i].keyNumber+"</td>");
			    		$("#"+tableId+" tr:last").append("<td>"+finalData[i].openingNumber+"</td>");
			    		$("#"+tableId+" tr:last").append("<td>"+finalData[i].closingNumber+"</td>");
			    		$("#"+tableId+" tr:last").append("<td>"+finalData[i].noOfBooks+"</td>");
			    		$("#"+tableId+" tr:last").append("<td>"+finalData[i].value+"</td>");
			    		$("#"+tableId+" tr:last").append("<td onclick='deleteThisRow(this,\"normalDenom\","+finalData[i].denominationValue+")'><i class='fa fa-times'></i></td>");
			    		$("#"+tableId+" tr:last").append("</tr>");
			    		
			    		$("#"+hiddenTableId).append("<tr>");
			    		$("#"+hiddenTableId+" tr:last").append("<td><input type='text' name='normalSrNo' value='"+(i+1)+"'"+"/></td>");
			    		$("#"+hiddenTableId+" tr:last").append("<td class='hiddenNormalDenom"+finalData[i].denominationValue+"'><input type='text' name='normalDenomValue' value='"+finalData[i].denominationValue+"'"+"/></td>");
			    		$("#"+hiddenTableId+" tr:last").append("<td><input type='text' name='normalKeyNo' value='"+finalData[i].keyNumber+"'"+"/></td>");
			    		$("#"+hiddenTableId+" tr:last").append("<td><input type='text' name='normalOpeningNo' value='"+finalData[i].openingNumber+"'"+"/></td>");
			    		$("#"+hiddenTableId+" tr:last").append("<td><input type='text' name='normalClosingNo' value='"+finalData[i].closingNumber+"'"+"/></td>");
			    		$("#"+hiddenTableId+" tr:last").append("<td><input type='text' name='normalNoOfBooks' value='"+finalData[i].noOfBooks+"'"+"/></td>");
			    		$("#"+hiddenTableId+" tr:last").append("<td><input type='text' name='normalValue' value='"+finalData[i].value+"'"+"/></td>");
			    		$("#"+hiddenTableId+" tr:last").append("<td onclick='deleteThisRow(this,normalDenom,"+finalData[i].denominationValue+")'><i class='fa fa-times'></i></td>");
			    		$("#"+hiddenTableId+" tr:last").append("</tr>");
			    	}
		    	//total = parseInt(total)+parseInt(resultArray[1]);
		    	/* $("#"+tableId).append("<tr>");
		    	$("#"+tableId+" tr:last").append("<td colspan='6'>Total</td>");
		    	$("#"+tableId+" tr:last").append("<td id='totalNormalTicketsValue'>"+total+"</td>");
		    	$("#"+tableId+" tr:last").append("</tr>");
		    	
		    	$("#"+hiddenTableId).append("<tr>");
		    	$("#"+hiddenTableId+" tr:last").append("<td colspan='6'>Total</td>");
		    	$("#"+hiddenTableId+" tr:last").append("<td ><input type='text' name='totalNormalValue' value='"+resultArray[1]+"'"+"/></td>");
		    	$("#"+hiddenTableId+" tr:last").append("</tr>"); */
		    	}else{
		    		bootbox.alert("No Records Found");
		    	//console.log($("#passenegerTicketTable").html());
		    }
		    }
		    	
		});
	
		}else{
			bootbox.alert('Please Enter valid number'); 
			$("#countOfBooksForNormalTicket").focus();
		}
	}else{
		bootbox.alert("Please Select Dernom Type");
		$("#s2id_normalDenomId").focus();
	}
	
}
	
function getLuggageTickets(tableId,hiddenTableId){
	var count = $("#countOfBooksForLuggageTicket").val();
	var orgId = "<s:property value='orgId'/>";
	if(Math.round(count) == count && count!='') {
		count = Math.round(count);
		$("#"+tableId).show();
		$("#"+tableId+" tr").slice(1).remove();
		$('#'+hiddenTableId+' tr').remove();
		var responsData = $.ajax({
		    type: 'POST',
		    async:false,
		    data: {
		    	count:count,
		    	orgId:orgId,
		    },
		    url:  "<s:url action='luggageTicketsByNoOfBooks'/>",
		    success: function(result){
		    	var resultArray = result.split("@");
		    	var finalData = JSON.parse(resultArray[0]);
		    	if(finalData.length>0){
		    		clearAllLuggague();
		    	for ( var i = 0; i < finalData.length; i++) {
		    		$("#"+tableId).append("<tr>");
		    		$("#"+tableId+" tr:last").append("<td>"+finalData[i].keyNumber+"</td>");
		    		$("#"+tableId+" tr:last").append("<td>"+finalData[i].openingNumber+"</td>");
		    		$("#"+tableId+" tr:last").append("<td>"+finalData[i].closingNumber+"</td>");
		    		$("#"+tableId+" tr:last").append("<td>"+finalData[i].noOfBooks+"</td>");
		    		$("#"+tableId+" tr:last").append("<td class='luggageDenom' id='denom'"+finalData[i].denominationValue+" onclick='deleteThisRow(this,\"luggageDenom\",\"\")'><i class='fa fa-times'></i></td>");
		    		$("#"+tableId+" tr:last").append("</tr>");
		    		
		    		$("#"+hiddenTableId).append("<tr>");
		    		$("#"+hiddenTableId+" tr:last").append("<td><input type='text' name='luggageSrNo' value='"+(i+1)+"'"+"/></td>");
		    		$("#"+hiddenTableId+" tr:last").append("<td><input type='text' name='luggageKeyNo' value='"+finalData[i].keyNumber+"'"+"/></td>");
		    		$("#"+hiddenTableId+" tr:last").append("<td><input type='text' name='luggageDenominationValue' value='"+finalData[i].denominationValue+"'"+"/></td>");
		    		$("#"+hiddenTableId+" tr:last").append("<td><input type='text' name='luggageOpeningNo' value='"+finalData[i].openingNumber+"'"+"/></td>");
		    		$("#"+hiddenTableId+" tr:last").append("<td><input type='text' name='luggageClosingNo' value='"+finalData[i].closingNumber+"'"+"/></td>");
		    		$("#"+hiddenTableId+" tr:last").append("<td><input type='text' name='luggageNoOfBooks' value='"+finalData[i].noOfBooks+"'"+"/></td>");
		    		$("#"+hiddenTableId+" tr:last").append("<td onclick='deleteThisRow(this,\"luggageDenom\",\"\")'><i class='fa fa-times'></i></td>");
		    		$("#"+hiddenTableId+" tr:last").append("</tr>");
		   
		    	}
		    	/* $("#"+tableId).append("<tr>");
		    	$("#"+tableId+" tr:last").append("<td colspan='4'>Total</td>");
		    	$("#"+tableId+" tr:last").append("<td >"+resultArray[1]+"</td>");
		    	$("#"+tableId+" tr:last").append("</tr>");
		    	
		    	$("#"+hiddenTableId).append("<tr>");
		    	$("#"+hiddenTableId+" tr:last").append("<td colspan='4'>Total</td>");
		    	$("#"+hiddenTableId+" tr:last").append("<td ><input type='text' name='totalLuggageValue' value='"+resultArray[1]+"'"+"/></td>");
		    	$("#"+hiddenTableId+" tr:last").append("</tr>"); */
		    	//console.log($("#"+tableId).html());
		    }else{
	    		//$("#"+tableId).append("<tr><td colspan='7' style='text-align:center'>No Records Found</td></tr>");
		    	bootbox.alert("No Records Found");
	    	}
		}
		    
	});
	}else{
		bootbox.alert('Please enter valid number');
	}
}
	
function getPassTypeTickets(tableId,hiddenTableId){
		var count = $("#countOfBooksForPassTicket").val();
		var orgId = "<s:property value='orgId'/>";
		var denomType = $("#passDenomId").val();
		var denomvalue = $("#passDenomId  option:selected").text().trim();
		var passday = $("#passDay").val();
		if(denomType!="0"){
			if(Math.round(count) == count && count!='') {
				count = Math.round(count);
		$("#"+tableId).show();
		//$("#"+tableId+" tr").slice(1).remove();
		//$('#'+hiddenTableId+' tr').remove();
		var responsData = $.ajax({
		    type: 'POST',
		    async:false,
		    data: {
		    	 count:count,
		    	 orgId:orgId,
			     denomType:denomType,
			     passday:passday,
			},
		    url:  "<s:url action='passTypeTicketsByNoOfBooks'/>",
		    success: function(result){
		    	var resultArray = result.split("@");
		    	var finalData = JSON.parse(resultArray[0]);
		    	if(finalData.length>0){
		    		isPassAlreadyThere(denomvalue+"-"+passday);
		    	for ( var i = 0; i < finalData.length; i++) {
		    		$("#"+tableId).append("<tr>");
		    		$("#"+tableId+" tr:last").append("<td class='passDenom"+finalData[i].denominationValue+"-"+passday+"'>"+finalData[i].denominationValue+"</td>");
		    		$("#"+tableId+" tr:last").append("<td>"+finalData[i].keyNumber+"</td>");
		    		$("#"+tableId+" tr:last").append("<td>"+finalData[i].passDay+"</td>");
		    		$("#"+tableId+" tr:last").append("<td>"+finalData[i].passType+"</td>");
		    		$("#"+tableId+" tr:last").append("<td>"+finalData[i].openingNumber+"</td>");
		    		$("#"+tableId+" tr:last").append("<td>"+finalData[i].closingNumber+"</td>");
		    	//	$("#"+tableId+" tr:last").append("<td>"+finalData[i].noOfBooks+"</td>");
		    		if(finalData[i].ticketType=='2')
						$("#"+tableId+" tr:last").append("<td>"+finalData[i].noOfBooks+"</td>");
					else if(finalData[i].ticketType=='3')
						$("#"+tableId+" tr:last").append("<td>"+finalData[i].noOfPasses+"</td>");
		    		$("#"+tableId+" tr:last").append("<td>"+finalData[i].value+"</td>");
		    		$("#"+tableId+" tr:last").append("<td onclick='deleteThisRow(this,\"passDenom\",\""+finalData[i].denominationValue+"-"+passday+"\")'><i class='fa fa-times'></i></td>");
		    		$("#"+tableId+" tr:last").append("</tr>");
		    		
		    		$("#"+hiddenTableId).append("<tr>");
		    		$("#"+hiddenTableId+" tr:last").append("<td><input type='text' name='passSrNo' value='"+(i+1)+"'"+"/></td>");
		    		$("#"+hiddenTableId+" tr:last").append("<td class='hiddenPassDenom"+finalData[i].denominationValue+"-"+passday+"'><input type='text' name='passDenomValue' value='"+finalData[i].denominationValue+"'"+"/></td>");
		    		$("#"+hiddenTableId+" tr:last").append("<td><input type='text' name='passKeyNo' value='"+finalData[i].keyNumber+"'"+"/></td>");
		    		$("#"+hiddenTableId+" tr:last").append("<td><input type='text' name='passDay' value='"+finalData[i].passDay+"'"+"/></td>");
		    		$("#"+hiddenTableId+" tr:last").append("<td><input type='text' name='passType' value='"+finalData[i].passType+"'"+"/></td>");
		    		$("#"+hiddenTableId+" tr:last").append("<td><input type='text' name='passOpeningNo' value='"+finalData[i].openingNumber+"'"+"/></td>");
		    		$("#"+hiddenTableId+" tr:last").append("<td><input type='text' name='passClosingNo' value='"+finalData[i].closingNumber+"'"+"/></td>");
		    		var books = 0;
		    		if(finalData[i].ticketType=='2')
		    			books = finalData[i].noOfBooks;
		    		else if(finalData[i].ticketType=='3')
		    			books = finalData[i].noOfPasses;
		    		
		    		$("#"+hiddenTableId+" tr:last").append("<td><input type='text' name='passNoOfBooks' value='"+books+"'"+"/></td>");
		    		$("#"+hiddenTableId+" tr:last").append("<td><input type='text' name='passValue' value='"+finalData[i].value+"'"+"/></td>");
		    		$("#"+hiddenTableId+" tr:last").append("<td onclick='deleteThisRow(this,\"passDenom\","+finalData[i].denominationValue+")'><i class='fa fa-times'></i></td>");
		    		$("#"+hiddenTableId+" tr:last").append("</tr>");
		    	}
		    	
		    	/* $("#"+tableId).append("<tr>");
		    	$("#"+tableId+" tr:last").append("<td colspan='7'>Total</td>");
		    	$("#"+tableId+" tr:last").append("<td >"+resultArray[1]+"</td>");
		    	$("#"+tableId+" tr:last").append("</tr>");
		    	
		    	$("#"+hiddenTableId).append("<tr>");
		    	$("#"+hiddenTableId+" tr:last").append("<td colspan='7'>Total</td>");
		    	$("#"+hiddenTableId+" tr:last").append("<td ><input type='text' name='totalPassValue' value='"+resultArray[1]+"'"+"/></td>");
		    	$("#"+hiddenTableId+" tr:last").append("</tr>"); */
		    
		    }else{
	    	//	$("#"+tableId).append("<tr><td colspan='8' style='text-align:center'>No Records Found</td></tr>");
		    	bootbox.alert("No Records Found");
	    	}
		}
	});
			}else{
				bootbox.alert('Please Enter valid number'); 
				$("#countOfBooksForPassTicket").focus();
			}
		}else{
			bootbox.alert('Please Select Denom Type'); 
			$("#select2-chosen-4").focus();
		}
	}
	function getPassDays(value){
		var denomId = $("#passDenomId").val();
		if(denomId!='0'){
			$.ajax({
				type: 'GET',
			    async:false,
			    data: {
			    	 denomId:denomId
				},
				url :"<s:url action='getPassDay'/>",
				success: function(result){
					if(result!=""){
						$("#passDay").prop("disabled", false);  
						$("#passDayLabel").prop("disabled", false);  ;
						$("#passDay").html(result);
					}
					else{
						$("#passDay").prop("disabled", true);  ;
						$("#passDay").html("");
						$("#passDayLabel").prop("disabled", true);
					}
					getStock('2');
				}
			});
		}else{
			$("#availablePassCount").val("0");
		}			
	}
	function getToDepotId(value){
		$('#issueToId').val(value);
	}
	function clearTickets(tableId){
	//	$('#'+tableId+' tr').slice(1).remove();
	}
	$(document).ready(function() {
		window.history.pushState("","", "AutomaticIssue.action");
	});
	function getStock(type){
		var displayCountId = '';
		var passday = '';
		if(type=='1'){
			var denom =	$("#normalDenomId").val();
			displayCountId = 'availablePassengerCount';
		}else if(type=='4'){
			var denom = '';
			displayCountId =  'availableLuggCount';
		}else if(type=='2'){
			var denom =	$("#passDenomId").val();
			passday = $("#passDay").val();
			displayCountId = 'availablePassCount';
		}
		$.ajax({
			type:'GET',
			data:{
				denomId : denom,
				ticketType:type,
				passday:passday
			},
			url:'AutomaticIssue!getCentralStockCountOfDenom',
			success:function(result){
				$('#'+displayCountId).val(result);
			}
		});
	}
	getStock('4');
</script>
<div class="page-content-wrapper">
	<div class="page-content">
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
					AUTOMATIC ISSUE 
				</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-bars"></i>Automatic Issue From :  
							<b>	Central Office </b> <%-- <s:property value="orgName"/> --%>
						</div>
					</div>
					<div class="portlet-body">
					<div class="form-group">
						<div class="form-group" >
							<b>
								<font color="green"> <s:actionmessage/></font>
								<font  style="color:red;"><s:actionerror/></font>
							</b>
						</div>
					</div>
					<div class="panel-body">
						<div class="form-group" >
							<label class="control-label col-md-3" style="text-align: right">Issue To</label>   
							<div class="col-md-3">
								<select class="select2-container select2_category form-control" id="issueTo" onchange="getToDepotId(this.value)">
									<s:iterator value="orgList" var="aaa">
										<option id="org<s:property   value="%{#aaa.get(0)}" />" value='<s:property   value="%{#aaa.get(1)}" />'>
											<s:property   value="%{#aaa.get(0)}" />
										</option>
									</s:iterator>
								</select>
							</div>
							<label class="control-label col-md-3" style="text-align: right">Indent Number (Reference if any)</label>   
							<div class="col-md-3">
								<input type="text" id="indentNumber" name='indentNumber' class="form-control" />
							</div>
						</div>
					</div>
					<div class="portlet-body form">
						<form action="SaveAutomaticIssue" id='mainForm' method="post">
							<div class="form-body">
							<input type='hidden' value='<s:property value="orgId"/>' name="issueFrom"/>
							<input type='hidden' value='' id='issueToId' name="issueTo"/>
<!--                                 Start Of Normal Tickets                          -->

								<div id="accordion3" class="panel-group accordion" >
									<div class="panel panel-default" >
										<div class="panel-heading" style="overflow:auto;" >
											<h4 class="panel-title">
												<a class="accordion-toggle"  data-parent="#accordion3" > Passenger Ticket </a>
											</h4>
										</div>
										<div class="panel-collapse collapse in"  style="color:#000000;overflow:auto;">
											<div class="panel-body">
												<div class="table-responsive">
													<table width="400px">
														<tr>
															<td>
																<label class="control-label" >Denom</label>
															</td>
															<td>
																<label class="control-label" >No.of Books</label>
															</td>
															<td>
																<label class="control-label"  onload="">Available</label>
															</td>
														</tr>
														<tr>
															<td>
																<div style="width: 120px">
																	<select class="form-control" id="normalDenomId" onchange="getStock('1')">
																		<s:iterator value="normalTypeDenomList" var="aaa">
																			<option id="<s:property   value="%{#aaa.get(0)}" />" value='<s:property   value="%{#aaa.get(1)}" />'>
																				<s:property   value="%{#aaa.get(0)}" />
																			</option>
																		</s:iterator>
																	</select>
																</div>
													
															</td>
															<td>
																<div class="" style="width: 120px">
																	<input type="text" id="countOfBooksForNormalTicket" class="form-control" onblur="" onkeypress="getBooks('1',event)"/>
																</div>
															</td>
															<td>
																<!-- <div class="" style="width: 80px">
																	<input type="button" class="btn grey" onclick="getNormalTickets('passenegerTicketTable','passenegerHiddenTable')" value="Get Records"/>
																</div>  -->
																<div class="" style="width: 80px">
																	<input type="text" id="availablePassengerCount" class="form-control" disabled='true'/>
																</div>
															</td>
														</tr>
													</table>
													<br/>
													<div id="scrollable-div" >
													<table class="table table-hover" style="display:none" id="passenegerTicketTable" >
														<tr>
															<!-- <th align="right" >Sl No</th> -->
															<th align="right" >Denom</th>
															<th align="right" >Denom Key</th>
															<th align="right" >Opening No</th>
															<th align="right" >Closing No</th>
															<th align="right" >Number of Books</th>
															<th align="right" >Value (Rs)</th>
														</tr>
													</table>
													<table style="display:none" id="passenegerHiddenTable" style="width:800px"></table>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div id="accordion3" class="panel-group accordion" >
									<div class="panel panel-default" >
										<div class="panel-heading" style="overflow:auto;" >
											<h4 class="panel-title">
												<a class="accordion-toggle"  data-parent="#accordion3" > Luggage </a>
											</h4>
										</div>
										<div class="panel-collapse collapse in"  style="color:#000000;overflow:auto;">
											<div class="panel-body">
												<div class="table-responsive">
													<table width="300px">
														<tr>
															<td>
																<label class="control-label"  onload="">No.of Books</label>
															</td>
															<td>
																<label class="control-label"  onload="">Available</label>
															</td>
														</tr>
														<tr>
															<td>
																<div class="" style="width: 80px">
																	<input type="text" id="countOfBooksForLuggageTicket" class="form-control" onblur="" onkeypress="getBooks('2',event)"/>
																</div>
															</td>
															<td>
																<!-- <div class="" style="width: 80px">
																	<input type="button" class="btn grey" onclick="getLuggageTickets('luggageTicketTable','luggageHiddenTicketTable')" value="Get Records"/>
																</div> -->
																
																<div class="" style="width: 80px">
																	<input type="text" id="availableLuggCount" class="form-control" disabled='true'/>
																</div>
														
															</td>
														</tr>
													</table>
													<br/>
													<div id="scrollable-div" >
													<table class="table table-hover" style="display:none"  id="luggageTicketTable">
														<tr>
															<!-- <th align="right" >Sl No</th> -->
															<th align="right" >Key No</th>
															<th align="right" >Opening No</th>
															<th align="right" >Closing No</th>
															<th align="right" >Number of Books</th>
														</tr>
													</table>
													<table style="display:none" id="luggaugeHiddenTable"></table>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div id="accordion3" class="panel-group accordion" >
									<div class="panel panel-default" >
										<div class="panel-heading" style="overflow:auto;" >
											<h4 class="panel-title">
												<a class="accordion-toggle"  data-parent="#accordion3" > Passes </a>
											</h4>
										</div>
										<div class="panel-collapse collapse in"  style="color:#000000;overflow:auto;">
											<div class="panel-body">
												<div class="table-responsive">
													<table width="500px">
														<tr>
															<td>
																<label class="control-label" >Denom</label>
															</td>
															<td >
																<label class="control-label" id='passDayLabel' >Pass Day</label>
															</td>
															<td>
																<label class="control-label" >No.of Passes</label>
															</td>
															<td>
																<label class="control-label"  onload="">Available</label>
															</td>
														</tr>
														<tr>
															<td>
																<div style="width: 120px">
																	<select class="form-control"  id="passDenomId" onchange='getPassDays(this)' >
																		<s:iterator value="passTypeDenomList" var="aaa">
																			<option id="<s:property   value="%{#aaa.get(0)}" />" value='<s:property   value="%{#aaa.get(1)}" />'>
																				<s:property   value="%{#aaa.get(0)}" />
																			</option>
																		</s:iterator>
																	</select>
																</div>
													
															</td>
															<td>
																<div style="width: 120px">
																	<select class="form-control"  id="passDay" onload="getStock('2')" onchange="getStock('2')">
																	</select>
																</div>
													
															</td>
															<td>
																<div class="" style="width: 120px">
																	<input type="text" id="countOfBooksForPassTicket" class="form-control" onkeypress="getBooks('3',event)" />
																</div>
															</td>
															<td>
																<!-- <div class="" style="width: 80px">
																	<input type="button" class="btn grey" onclick="getPassTypeTickets('passTypeTicketTable','luggageHiddenTable')" value="Get Records"/>
																</div> -->
																<div class="" style="width: 80px">
																	<input type="text" id="availablePassCount" class="form-control" disabled='true'/>
																</div>
															</td>
														</tr>
													</table>
													<br/>
													<div id="scrollable-div" >
													<table class="table table-hover" style="display:none" id="passTypeTicketTable">
														<tr>
															<!-- <th align="right" >Sl No</th> -->
															<th align="right" >Denom</th>
															<th align="right" >Denom Key</th>
															<th align="right" >Day/Month </th>
															<th align="right" >Pass Type </th>
															<th align="right" >Opening No</th>
															<th align="right" >Closing No</th>
															<th align="right" >No of Books/ Passes</th>
															<th align="right" >Value (Rs)</th>
														</tr>
													</table>
													<table style="display:none" id="passTypeHiddenTable"></table>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
									<div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-12">
											<button type="button" class="btn blue" onclick="return validateAll();">Save</button>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
<head>
<script>
function getBooks(ticketType,evt){
	console.log(evt);
	var mainTableId,hiddentableId,textboxId,selectBoxId;
	if(ticketType==1){
		mainTableId = "passenegerTicketTable";
		hiddentableId = "passenegerHiddenTable";
		textboxId = "countOfBooksForNormalTicket";
		selectBoxId = "normalDenomId";
	}else if(ticketType==2){
		mainTableId = "luggageTicketTable";
		hiddentableId = "luggaugeHiddenTable";
		textboxId = "countOfBooksForLuggageTicket";
	//	selectBoxId = "normalDenomId";
	}else if(ticketType==3){
		mainTableId = "passTypeTicketTable";
		hiddentableId = "passTypeHiddenTable";
		textboxId = "countOfBooksForPassTicket";
		selectBoxId = "passDenomId";
	}
	
	var code = 0;
	if(Number(evt.charCode)!=0){
		code = evt.charCode;
	}
	if(Number(evt.keyCode)!=0){
		code = evt.keyCode;
	}
	var theEvent = evt || window.event;
    var key = theEvent.keyCode || theEvent.which;
    key = String.fromCharCode( key );
    
    var regex = /[0-9\b]/;
    if(key==9 || key == 37 || key == 38 || key == 39 || key == 40 || key == 8 || key == 35 || key == 36 || key ==9 ) { // Left / Up / Right / Down Arrow, Backspace, Delete keys
        return;
    }
    if(code==107 || code==9){
    	var value = $("#"+textboxId).val();
    	if(ticketType==1 && value!=""){
        	getNormalTickets(mainTableId,hiddentableId);
        }else if(ticketType==2 && value!=""){
        	getLuggageTickets(mainTableId,hiddentableId);
        }
        else if(ticketType==3 && value!=""){
        	getPassTypeTickets(mainTableId,hiddentableId);
        }else{
        	return true;
        }
        $("#"+textboxId).val('');
       // if(ticketType!=2)
       	//	$("#"+selectBoxId).focus();
       // theEvent.preventDefault();
        
    } else if(code==43 || code==9){
    	if(ticketType==1 && value!=""){
        	getNormalTickets(mainTableId,hiddentableId);
        }else if(ticketType==2 && value!=""){
        	getLuggageTickets(mainTableId,hiddentableId);
        }
        else if(ticketType==3 && value!=""){
        	getPassTypeTickets(mainTableId,hiddentableId);
        }
        else{
        	return true;
        }
        $("#"+textboxId).val('');
        if(ticketType!=2)
       		$("#"+selectBoxId).focus();
        theEvent.preventDefault();
	}
    else if( !regex.test(key) ) {
        theEvent.returnValue = false;
        if(theEvent.preventDefault) theEvent.preventDefault();
    }
   
    
 }
function validateAll(){
		 //= $("#").attr('rows').length;
		var normalTicketSize = $("#passenegerTicketTable tr").length;
		var luggageTicketSize = $("#luggageTicketTable tr").length;
		var passTicketSize = $("#passTypeTicketTable tr").length;
		var issueTo = $("#issueTo").val();
		if(issueTo!="0"){
		if((normalTicketSize+luggageTicketSize+passTicketSize)>3){
			document.forms["mainForm"].action= "SaveAutomaticIssue.action";
			document.forms["mainForm"].submit();
		} else{
			bootbox.alert("No Records to Issue");
		}
		}else{
			bootbox.alert("Please Select Issue To organization");
		}
	}
	</script>
	</head>
