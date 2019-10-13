<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<html>
<head>

<style>
p.intro {color:red;font-size:14px;}
p.success{color:green; size:2px;}
.centerize {
    display: block;
    margin: 0 auto;
    text-align: center;
}
table tr th,table tr td{
border:1px solid #dddddd;
}
	</style>
	<script>
	/*  $(do
		cument).ready( function () {
	    var table = $('#addticketInv').DataTable();
	    new $.fn.dataTable.FixedHeader( table );
	} );  */
	</script>
	</head>
	
	<body> 
<script	src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js" 	type="text/javascript"></script>
<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.min.js" 	type="text/javascript"></script>
<form action="#" name="viewticketform">
	<div class="page-content-wrapper"> 
		<div class="page-content">
			<div class="row">
				<div class="col-md-12">
					<h3 class="page-title">
						TICKET INVENTORY <small></small>
					</h3>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>Initial Stock Entry : <b><s:property  value="orgName"/></b>
							</div>
						</div>
						
						<div class="portlet-body" >
						<form class="form-horizontal"  method="get">
						<font color="Red"> <b><s:actionmessage/></b></font>
							<div  id="resizable1" class="col-md-13">
								<div class="portlet box white" >
									<div class="portlet-body" >
										<div id="accordion3" class="panel-group accordion" >
											<div class="panel panel-default" >
												<div class="panel-heading" overflow:auto;" >
													<h4 class="panel-title">
													<a class="accordion-toggle accordion-toggle-styled" data-toggle="collapse" data-parent="#accordion3" href="#collapse_3_1"> Passenger Tickets </a>
													</h4>
												</div>
												<div class="panel-collapse collapse in" id="collapse_3_1"  style="color:#000000;overflow:auto;">
													<div class="panel-body">
														<div class="form-body">
															<div class="table-responsive" style="color:#000000">
																<table class="table table-hover" id="ticketdata" style='border:1px solid #dddddd'>
																	<tr>
																		<td style="border:none">
																			<button id="add" class="btn default" onclick="addTicketRow()" aria-hidden="true" type="button">Add <i class="fa fa-plus"></i></button>
																		</td>
																	</tr>
																	<tr>
																		<th align="right">Denomination</th>
																		<th align="right">Denomination Key</th>
																		<th align="right">Start Number</th>
																		<th align="right">End Number</th>
																		<th align="right">No Of Books</th>
																		<th align="right">Value</th>
																		<th align="right">Priority</th>
																		<th align="right">Add</th>
																		<th align="right">Delete</th>
																	</tr>
																</table>	
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div id="accordion4" class="panel-group accordion" >
											<div class="panel panel-default" >
												<div class="panel-heading" overflow:auto;" >
													<h4 class="panel-title">
														<a class="accordion-toggle accordion-toggle-styled" data-toggle="collapse" data-parent="#accordion4" href="#collapse_3_2"> 	Pass Details </a>
													</h4>
												</div>
												<div class="panel-collapse collapse in" id="collapse_3_2"  style="color:#000000;overflow:auto;">
													<div class="panel-body">
														<div class="form-body">
															<div class="table-responsive" style="color:#000000">
																<table class="table table-hover" id="passtable" style='border:1px solid #dddddd'>
																	<tr>
																		<td style="border:none">
																			<button id="add" class="btn default" onclick="addPassRow()" aria-hidden="true" type="button">Add <i class="fa fa-plus"></i></button>
																		</td>
																	</tr>
																	<tr>
																		<th align="right">Type of Pass</th>
																		<th align="right">Denomination </th>
																		<th align="right">Denomination Key</th>
																		<th align="right">Pass Day</th>
																		<th align="right">Start Number</th>
																		<th align="right">End Number</th>
																		<th align="right">No. of Passes</th>
																		<th align="right">No. Of Books</th>
																		<th align="right">Total Value</th>
																		<th align="right">Priority</th>
																		<th align="right">Add</th>
																		<th align="right">Delete</th>
																	</tr>
																</table>	
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div id="accordion5" class="panel-group accordion" >
											<div class="panel panel-default" >
												<div class="panel-heading" overflow:auto;" >
													<h4 class="panel-title">
													<a class="accordion-toggle accordion-toggle-styled" data-toggle="collapse" data-parent="#accordion5" href="#collapse_3_3"> Luggage Tickets </a>
													</h4>
												</div>
												<div class="panel-collapse collapse in" id="collapse_3_3"  style="color:#000000;overflow:auto;">
													<div class="panel-body">
														<div class="form-body">
															<div class="table-responsive" style="color:#000000">
																<table class="table table-hover" id="luggdata" style='border:1px solid #dddddd'>
																	<tr>
																		<td style="border:none">
																			<button id="add" class="btn default" onclick="addLuggageRow()" aria-hidden="true" type="button">Add <i class="fa fa-plus"></i></button>
																		</td>
																	</tr>
																	<tr>
																		<th align="right">Luggage ticket key</th>
																		<th align="right">Start Number</th>
																		<th align="right">End Number</th>
																		<th align="right">No. of luggage ticket</th>
																		<th align="right">No Of Books</th>
																		<th align="right">Priority</th>
																		<th align="right">Add</th>
																		<th align="right">Delete</th>
																	</tr>
																</table>	
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div id="accordion6" class="panel-group accordion" >
											<div class="panel panel-default" >
												<div class="panel-heading" overflow:auto;" >
													<h4 class="panel-title">
													<a class="accordion-toggle accordion-toggle-styled" data-toggle="collapse" data-parent="#accordion6" href="#collapse_3_4"> Trip Sheet Type </a>
													</h4>
												</div>
												<div class="panel-collapse collapse in" id="collapse_3_4"  style="color:#000000;overflow:auto;">
													<div class="panel-body">
														<div class="form-body">
															<div class="table-responsive" style="color:#000000">
																<table class="table table-hover" id="tsheet" style='border:1px solid #dddddd'>
																	<tr>
																		<td style="border:none">
																			<button id="add" class="btn default" onclick="addTSheetRow()" aria-hidden="true" type="button">Add <i class="fa fa-plus"></i></button>
																		</td>
																	</tr>
																	<tr>
																		<th align="right">Waybill key</th>
																		<th align="right">Start Number</th>
																		<th align="right">End Number</th>
																		<th align="right">Total Number of Waybill</th>
																		<th align="right">Add</th>
																		<th align="right">Delete</th>
																	</tr>
																</table>	
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="form-actions fluid">
											<div class="col-md-offset-3 col-md-3">
												<button class="btn blue" id="subform" type="button" onclick="saveAjaxForm()"> Save </button>
												<div style="display: none"><a href="GetTktInvView.action" class="btn grey"  id="cancel"> <!-- //onclick="callEdit()" -->
													 				Cancel </a></div>
												<button class="btn default" id="subform" type="button" onclick="moveBack()"> Cancel </button>
											</div>
										</div>
										
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
	</form>
<script type="text/javascript" value='1'>

	var denomdropdown="";
	var denomdropdownvalue = new Array();
	var passdropdown="";
	var passdaydropdown ='<select class="form-control" id="pass_day_" style="width:150px" ><option value="0">Select</option>';
	var denomval=[];
	var passdenomdropdown='<select id="passdenom" class="select2_category form-control passdenom" style="width:140px" ><option value="0">Select</option></select>';;
	var passdenomdropdownvalue=new Array();
	jQuery(document).ready(function() {
		getDayDropdown();
		getMonthDropDown();
		//getOrgType();
		//getDenomList();
		
		$('.deleteTicketRow').unbind().live('keypress',function(evt) {
			console.log(evt);
			var theEvent = evt || window.event;
	    	var key = theEvent.keyCode || theEvent.which;
	    	if(key == 13 ) {
				var index = $(this).parents('tr').closest('tr').index();
				$("#ticketdata tr:eq("+index+")").remove();
				ticketcounter--;
	    	}
		});
		$('.deleteTicketRow').unbind().live('click',function(evt) {
				var index = $(this).parents('tr').closest('tr').index();
				$("#ticketdata tr:eq("+index+")").remove();
				ticketcounter--;
		});
		$('.deletePassRow').unbind().live('keypress',function(e) {
			var theEvent = evt || window.event;
	    	var key = theEvent.keyCode || theEvent.which;
	    	if(key == 13 ) {
				var index = $(this).parents('tr').closest('tr').index();
				$("#passtable tr:eq("+index+")").remove();
				passcounter--;
	    	}
		});
		$('.deletePassRow').unbind().live('click',function(e) {
				var index = $(this).parents('tr').closest('tr').index();
				$("#passtable tr:eq("+index+")").remove();
				passcounter--;
		});
		$('.deleteLuggageRow').unbind().live('keypress',function(evt) {
			var theEvent = evt || window.event;
	    	var key = theEvent.keyCode || theEvent.which;
	    	if(key==13){
				var index = $(this).parents('tr').closest('tr').index();
				$("#luggdata tr:eq("+index+")").remove();
				luggcounter--;
	    	}
		});
		$('.deleteLuggageRow').unbind().live('click',function(evt) {
				var index = $(this).parents('tr').closest('tr').index();
				$("#luggdata tr:eq("+index+")").remove();
				luggcounter--;
		});
		$('.deleteTSheetRow').unbind().live('keypress',function(evt) {
			var theEvent = evt || window.event;
	    	var key = theEvent.keyCode || theEvent.which;
	    	if(key==13){
				var index = $(this).parents('tr').closest('tr').index();
				$("#tsheet tr:eq("+index+")").remove();
				tsheetcounter--;
	    	}
		});
		$('.deleteTSheetRow').unbind().live('click',function(evt) {
				var index = $(this).parents('tr').closest('tr').index();
				$("#tsheet tr:eq("+index+")").remove();
				tsheetcounter--;
		});
	});
	
	var ticketdupliarray=[];
	var passdupliarray=[];
	var luggdupliarray=[];
	var tsheetdupliarray=[];
	
	var ticketcounter=0;
	function addTicketRow(){
    	getDenomList(ticketcounter);
		$("#ticketdata tr").last().after('<tr id="'+ticketcounter+'"><td>'+denomdropdown+'</td><td><input class="form-control input-small denom_key" id="denom_key" value="" onblur="makeUppercase(this);validateTicketKey(this)" maxlength="10" style="width:300px;" type="text"></td><td class=""><input class="form-control input-small start_no" id="start_no" onkeyup="isInteger(this)" value="" onblur="validateStartTicketno(this,'+ticketcounter+')" maxlength="8" size="8" type="text"></td><td class=""><input class="form-control input-small end_no" id="end_no" value="" onkeyup="isInteger(this)" onblur="validateEndTicketNo(this,'+ticketcounter+')" maxlength="8" size="8" type="text"></td><td class=""><input class="form-control input-small no_of_books" id="no_of_books" value="" disabled="true" type="text" onblur="calculateEndNo(this,'+ticketcounter+',1)"></td><td class=""><input class="form-control input-small value" id="value" value="" type="text" disabled="true"></td><td class=""><input class="form-control input-small priority" id="priority"  disabled="true" type="text" onkeyup="isInteger(this)"></td>'
		+'<td><button class="btn btn-default" onclick="addTicketRow()"><i class="fa fa-plus"></i></td><td><button class="btn btn-default deleteTicketRow"><i class="fa fa-times"></i></td></tr>');
		$("#denom"+ticketcounter).focus();
		ticketcounter++;
	}

	var passcounter=0;
	function addPassRow(){
		getPassDropList(passcounter);
		
		passcounter++;		
		
	}function addPassesRow(evt){
 		var theEvent = evt || window.event;
    	var key = theEvent.keyCode || theEvent.which;
    	if(key == 13 ) {
    		addPassRow();
	    }
	}
	var luggcounter=0;
	function addLuggageRow(){
		$("#luggdata tr").last().after('<tr  id="'+luggcounter+'"><td><input class="form-control input-small lugg_ticket_key" id="lugg_ticket_key'+luggcounter+'" value="" onblur="makeUppercase(this);" maxlength="10" style="width:300px;" type="text"></td><td><input class="form-control input-small lugg_start_no" onkeyup="isInteger(this)" id="lugg_start_no" value=""  maxlength="8" size="8" style="width:300px;" type="text" onblur="validateLuggStartTicketno(this,'+luggcounter+')"></td><td class=""><input class="form-control input-small lugg_end_no" onkeyup="isInteger(this)" id="lugg_end_no" value=""  maxlength="8" size="8" type="text" onblur="validateLuggEndTicketNo(this,'+luggcounter+')"></td><td class=""><input class="form-control input-small lugg_no_of_tickets" id="lugg_no_of_tickets" disabled="true" value=""  maxlength="8" size="8" type="text" readonly></td><td class=""><input class="form-control input-small lugg_no_of_books" id="lugg_no_of_books" disabled="true" value="" type="text" onblur="calculateEndNo(this,'+luggcounter+',4)"></td><td class=""><input class="form-control input-small lugg_priority" id="lugg_priority" value="" type="text" disabled="true" onkeyup="isInteger(this)"></td>'
		+'<td><button class="btn btn-default" onclick="addLuggageRow()"><i class="fa fa-plus"></i></td><td><button class="btn btn-default deleteLuggageRow"><i class="fa fa-times"></i></td></tr>');
		$("#lugg_ticket_key"+luggcounter).focus();
		luggcounter++;	
	}
	function addLuggRow(evt){
 		var theEvent = evt || window.event;
    	var key = theEvent.keyCode || theEvent.which;
    	if(key == 13 ) {
    		addLuggageRow();
	    }
	}
	var tsheetcounter=0;
	function addTSheetRow(){
		$("#tsheet tr").last().after('<tr  id="'+tsheetcounter+'"><td><input class="form-control input-small waybill_key" id="waybill_key'+tsheetcounter+'" value="" onblur="makeUppercase(this);" maxlength="10" style="width:300px;" type="text"></td><td><input class="form-control input-small tsheet_start_no" onkeyup="isInteger(this)" id="tsheet_start_no" value=""  maxlength="8" size="8" style="width:300px;" type="text" onblur="validateTsheetStartTicketno(this,'+tsheetcounter+')"></td><td class=""><input class="form-control input-small tsheet_end_no" onkeyup="isInteger(this)" id="tsheet_end_no" value=""  maxlength="8" size="8" type="text" onblur="validateTSheetEndTicketNo(this,'+tsheetcounter+')"></td><td class=""><input class="form-control input-small no_of_tsheets" id="no_of_tsheets" disabled="true" value=""  maxlength="8" size="8" type="text" readonly></td>'
		+'<td><button class="btn btn-default" onclick="addTSheetRow()"><i class="fa fa-plus"></i></td><td><button class="btn btn-default deleteTSheetRow"><i class="fa fa-times"></i></td></tr>');
		$("#waybill_key"+tsheetcounter).focus();
		tsheetcounter++;	
	}
	function addTSheRow(evt){
 		var theEvent = evt || window.event;
    	var key = theEvent.keyCode || theEvent.which;
    	if(key == 13 ) {
    		addTSheetRow();
	    }
	}
	function getDenomList(iterationNo){
		$.ajax({
		    type : 'POST',
		    async:false,
		    data : {
		    	id:'1',
		    },
		    url  : "<s:url action='GetTicketDenomList'/>",
		    success: function(data){
		    	denomdropdown ='<select id="denom'+iterationNo+'" class="select2_category form-control denom" style="width:140px">';
		    	var result=data.split(",");
		    	denomdropdown+='<option value="0">Select</option>';
		    	for(var i=0;i<result.length-1;i++){
		    		var data=result[i].split("@");
		    		denomdropdown+='<option value="'+data[0]+'">'+data[1]+'</option>';
		    		denomdropdownvalue[data[0]] = data[1];
		    	}
		    	denomdropdown+="</select>";
		    },
		    error : function(xhr, errmsg) {
		    }
		});
	}
	
	function getPassDropList(rowid){
		$.ajax({
		    type : 'POST',
		    data :{
		       id:'1',
		    },
		    url : "<s:url action='GetPassDropList'/>",
		    success: function(data){
		    	passdropdown="";
		    	passdropdown+='<select id="passtype_'+rowid+'" class="select2_category form-control passtype" style="width:140px" onchange="GetPassDenomDropList(this.value,'+rowid+');loadPassDayOrMonth('+rowid+')">';
		    	var result=data.split(",");
		    	//passdropdown+='<option value="0">Select</option>';
		    	for(var i=0;i<result.length-1;i++){
		    		var data=result[i].split("@");
		    		passdropdown+='<option value="'+data[0]+'">'+data[1]+'</option>';
		    	}
		    	passdropdown+="</select>";
		    	$("#passtable tr").last().after('<tr id="'+passcounter+'"><td>'+passdropdown+'</td><td id="passdenom'+passcounter+'">'+passdenomdropdown+'</td>'+
		    			'<td><input class="form-control input-small pass_denom_no" id="pass_denom_no" value="" onblur="makeUppercase(this);"  maxlength="10" style="width:300px;" type="text"></td>'+
		    			'<td class="" id="passdaydropid'+rowid+'">'+passdaydropdown+'</td>'+
		    			'<td class=""><input class="form-control input-small pass_start_no" id="pass_start_no" onkeyup="isInteger(this)" value=""  maxlength="8" size="8" type="text" '+
		    			' onblur="validatePassStartTicketno(this,'+rowid+')"></td>'+
		    			'<td class=""><input class="form-control input-small pass_end_no" onkeyup="isInteger(this)" id="pass_end_no" value=""  maxlength="8" size="8" type="text" onblur="validatePassEndTicketNo(this,'+rowid+')"></td>'+
		    			'<td class=""><input class="form-control input-small pass_no_of_pass" disabled="true" id="pass_no_of_pass_'+rowid+'" value="" type="text" onblur="calculateMonthlyPassEndNo(this,'+rowid+')" ></td>'+
		    			'<td class=""><input class="form-control input-small pass_no_of_books" disabled="true" id="pass_no_of_books_'+rowid+'" value="" type="text" onblur="calculateEndNo(this,'+rowid+',2)" ></td>'+
		    			'<td class=""><input class="form-control input-small pass_total_pass" id="pass_total_pass" value="" type="text" disabled="true"></td>'+
		    			'<td class=""><input class="form-control input-small pass_priority" id="pass_priority" disabled="true"  type="text" onkeyup="isInteger(this)"></td>'+
		    			'<td><button class="btn btn-default" onclick="addPassRow()"><i class="fa fa-plus"></i></td><td><button class="btn btn-default deletePassRow"><i class="fa fa-times"></i></td></tr>');
		    	
		    	GetPassDenomDropList(2,rowid);
		    	loadPassDayOrMonth(rowid);
		    	$("#passtype_"+rowid).focus();
		    	},
		    error : function(xhr, errmsg) {
		    }
		});
	}
	
	function makeUppercase(val){
		val.value=val.value.toUpperCase()
	}
	function validateTicketKey(val){
	}
	
	function padLeft(nr, n, str){
	    return Array(n-String(nr).length+1).join(str||'0')+nr;
	}
	Number.prototype.padLeft = function (n,str){
	    return Array(n-String(this).length+1).join(str||'0')+this;
	}
	
	function validateStartTicketno(val,counter){
		val.value=padLeft(val.value,8);
		if(val.value.length>8){
			alert("Last two digits of Ticket Type Start number should be 00 ");
			val.focus();
			return false;
		}
		if(val.value.slice(-2)!='00'){
			alert("Last two digits of Ticket Type Start number should be 00 ");
			val.focus();
			return false;
		}
		
		var loccounter=0;
		$(".end_no").each(function() {
			if(loccounter==counter){
				//$(this).val(padLeft(val.value,8).substring(0, 6));
			}
		loccounter++;
		}); 
	}
	
	function validatePassStartTicketno(val,counter){
		var loccounter=0;
		var passtype;
		val.value=padLeft(val.value,8);
		var monendno=padLeft(val.value,8);
		$(".passtype").each(function() {
			if(loccounter==counter){
				passtype=$(this).val();
			}
		loccounter++;
		}); 
		//alert("passtype"+passtype);
		if(passtype==2){
			if(val.value.length>8){
				alert("Last two digits of Pass Type Start number should be 01 or 51");
				val.focus();
				return false;
			}
			if(val.value.slice(-2)=='01' || val.value.slice(-2)=='51'){
			}else{
				alert("Last two digits of Pass Type Start number should be 01 or 51");
				val.focus();
				return false;
			}
			var loccounter=0;
			$(".pass_end_no").each(function() {
				if(loccounter==counter){
					//$(this).val(padLeft(val.value,8).substring(0, 6));
				}
				loccounter++;
			}); 
		}
		if(passtype==3){
			var loccounter=0;
			$(".pass_end_no").each(function() {
				if(loccounter==counter){
					//$(this).val(padLeft(val.value,8).substring(0, 6));
				}
				loccounter++;
			}); 
		}
	}
	function isInt(value) {
		  return !isNaN(value) && (function(x) { return (x | 0) === x; })(parseFloat(value))
		}
	
	function validateEndTicketNo(val,counter){
		
		if(val.value.slice(-2)!='99'){
			alert("Last two digits of Ticket Type End number should be 99");
			//val.focus();
			return false;
		}
		var loccounter=0;
		var startno=0;
		var denom=0;
		var denomval=0;
		var denomkey="";
		var flag = true;
		$(".start_no").each(function() {
			
			if(loccounter==counter){
				startno=$(this).val();
				if($(this).val()>=val.value){
					alert("End number should greater than Start No");
					val.focus();
					flag = false;
					return false;
				}else{
					$(val).val(padLeft(val.value,8));
				}
			}
			loccounter++;
		}); 
		if(!flag){
			return false;
		}
		/* $(".end_no").each(function() {
			if(loccounter==counter){
				//$(this).val(padLeft(val.value,8).substring(0, 6));
			}
		loccounter++;
		});  */
		var loccounter=0;
		$(".denom").each(function() {
			if(loccounter==counter){
				denom=$(this).val();
				denomval=denomdropdownvalue[denom];
			}
			loccounter++;
		}); 
		var loccounter=0;
		$(".denom_key").each(function() {
			if(loccounter==counter){
				denomkey=$(this).val();
			}
			loccounter++;
		}); 
		if(denomval!=undefined){
			var value=(val.value-startno+1)*denomval;
			var book=(val.value-startno+1)/100;
			if(!isInt(book)){
				alert("Invalid Start Or End Ticket No.");
				return false;
			}else if(book<1){
				alert("End number should greater than Start No");
				return false;
			}
			var loccounter=0;
			$(".value").each(function() {
				if(loccounter==counter ){
					$(this).val(value);
				}
				loccounter++;
			}); 
			var loccounter=0;
			$(".no_of_books").each(function() {
				if(loccounter==counter){
					$(this).val(book);
				}
				loccounter++;
			}); 
			getPriority(denom,denomkey,1,counter);
		}
		
		if(!checkDuplicateRecords(1,val)){
			alert("Duplicate Entry In Ticket Type");
	    	val.value="";
	    	val.focus();
	    	return false;
		}
		getTicketsDuplicateCheck(denom,denomkey,startno,val.value,1).success(function (data) {
		    if(data=="Duplicate Data"){
		    	alert("Duplicate Entry");
		    	val.value="";
		    	val.focus();
		    	return false;
		    }
		});
	}
	function validatePassEndTicketNo(val, counter){
		var loccounter=0;
		var noofpass=0;
		var noofbook=0;
		var total_value=0;
		var passdenom=0;
		var passtype;
		var denomkey;
		$(".pass_denom_no").each(function() {
			if(loccounter==counter){
				denomkey=$(this).val();
				
			}
			loccounter++;
		}); 
		var loccounter=0;
		$(".passtype").each(function() {
			if(loccounter==counter){
				passtype=$(this).val();
			}
			loccounter++;
		}); 
		var loccounter=0;
		var passstartno;
		$(".pass_start_no").each(function() {
			if(loccounter==counter){
				passstartno=$(this).val();
			}
			loccounter++;
		}); 
		var loccounter=0;
		$(".passdenom").each(function() {
			if(loccounter==counter){
				passdenom=$(this).val();
			}
			loccounter++;
		});
		if(passtype=="3"){
			$(val).val(padLeft(val.value,8));
			if(passstartno>val.value){
				alert("Monthly Pass Type End No. Should Be Greater or equal to Than Start No.");
				//val.focus();
				return false;
			}
			
			else{
				$(val).val(padLeft(val.value,8));
			}
			noofpass=val.value-passstartno+1;
			noofbook="";
		
			if(passdenomdropdownvalue[passdenom]!=undefined){
				total_value=noofpass*passdenomdropdownvalue[passdenom];
				getPriority(passdenom,denomkey,3,counter);
				//getTicketsDuplicateCheck(passdenom,denomkey,passstartno,val.value,3);
				if(!checkDuplicateRecords(3,val)){
					alert("Duplicate Entry In Pass Type");
			    	val.value="";
			    	//val.focus();
			    	return false;
				}
				getTicketsDuplicateCheck(passdenom,denomkey,passstartno,val.value,3).success(function (data) {
				    if(data=="Duplicate Data"){
				    	alert("Duplicate Entry In Pass Type");
				    	val.value="";
				    	//val.focus();
				    	return false;
				    }
				});
			}
		}
		if(passtype=="2"){
			console.log(val.value);
			if(val.value.slice(-2)!='00' && val.value.slice(-2)!='50'){
				alert("Last two digits of Pass Type End number should be 00 or 50 ");
				//val.focus();
				return false;
			}
			
			if(loccounter==counter){
			if(passstartno>val.value){
				alert("Pass Type End number should be Greater Than Start No.");
				//val.focus();
				return false;
			}else{
				$(val).val(padLeft(val.value,8));
			}
			}
			loccounter++;
			noofpass=val.value-passstartno+1;
			noofbook=(val.value-passstartno+1)/50;
			if(passdenomdropdownvalue[passdenom]!=undefined){
				if(!isInt(noofbook)){
					alert("Invalid Start Or End Pass No.");
					return false;
				}else if(noofbook<1){
					alert("End number should greater than Start No");
					return false;
				}
				total_value=noofpass*passdenomdropdownvalue[passdenom];
				getPriority(passdenom,denomkey,2,counter);
				//getTicketsDuplicateCheck(passdenom,denomkey,passstartno,val.value,2);
				if(!checkDuplicateRecords(2,val)){
					alert("Duplicate Entry In Pass Type");
			    	val.value="";
			    	//val.focus();
			    	return false;
				}
				getTicketsDuplicateCheck(passdenom,denomkey,passstartno,val.value,2).success(function (data) {
					   // alert(data);
				    if(data=="Duplicate Data"){
				    	alert("Duplicate Entry In Pass Type.");
				    	val.value="";
				    	//val.focus();
				    	return false;
				    }
				});
			}
		}
		var loccounter=0;
		console.log(noofpass);
		if(noofpass<1){
			alert("End number should greater than Start No");
			return false;
		}
		$(".pass_no_of_pass").each(function() {
			if(loccounter==counter){
				$(this).val(noofpass);
			}
			loccounter++;
		});
		var loccounter=0;
		$(".pass_no_of_books").each(function() {
			if(loccounter==counter){
				$(this).val(noofbook);
			}
			loccounter++;
		});
		var loccounter=0;
		$(".pass_total_pass").each(function() {
			if(loccounter==counter){
				$(this).val(total_value);
			}
			loccounter++;
		});
	}
	
	function getPriority(denom,denomkey,tickettype,counter){
		
		$.ajax({
		    type : 'POST',
		    data : {
		    	denom:denom,
		    	denomkey:denomkey,
		    	tickettype:tickettype,
		        
		    },
		    url :  "<s:url action='GetTicketPriority'/>",
		    success: function(data){
		    	var result=parseInt(data);
		    	var loccounter=0;
		    	if(tickettype==1){
		    		var denom=[];
		    		var denomkey=[];
		    		$(".denom").each(function() {
		    			denom.push($(this).val());
					});
		    		$(".denom_key").each(function() {
		    			denom.push($(this).val());
					});
		    		for(var i=0;i<denom;i++){
		    			if(denom[i]==denom){
		    				if(denomkey[i]==denomkey){
		    					result++;
		    				}
		    			}
		    		}
					$(".priority").each(function() {
						if(loccounter==counter){
							$(this).val(result);
						}
						loccounter++;
					}); 
		    	}
		    	if(tickettype==4){
		    		var denom=[];
		    		$(".lugg_ticket_key").each(function() {
		    			denom.push($(this).val());
					});
		    		for(var i=0;i<denom;i++){
		    			if(denom[i]==denom){
	    					result++;
		    			}
		    		}
		    		$(".lugg_priority").each(function() {
						if(loccounter==counter){
							$(this).val(result);
						}
						loccounter++;
					});	
		    	}
		    	if(tickettype==2 || tickettype==3){
		    		var denom=[];
		    		var denomkey=[];
		    		$(".passdenom").each(function() {
		    			denom.push($(this).val());
					});
		    		$(".pass_denom_no").each(function() {
		    			denom.push($(this).val());
					});
		    		for(var i=0;i<denom;i++){
		    			if(denom[i]==denom){
		    				if(denomkey[i]==denomkey){
		    					result++;
		    				}
		    			}
		    		}
		    		$(".pass_priority").each(function() {
						if(loccounter==counter){
							$(this).val(result);
						}
						loccounter++;
					});	
		    	}
			},
		    error : function(xhr, errmsg) {
		    	//alert("Session out error..Please login again");
		    }
		});
	}
	function GetPassDenomDropList(passtypeval,rowid){
		
		$.ajax({
		    type : 'POST',
		    data : {
		    	tickettype:passtypeval,
		    },
		    url :  "<s:url action='GetPassDenomDropList'/>",
		    success: function(data){
		    	var passdropoption="";
		    	var result=data.split(",");
		    	var loccounter=0;
				document.getElementById('passdenom'+(rowid+1)).innerHTML = "";
		        document.getElementById("passdenom"+(rowid+1)).value="";
		        var passdropoption = '<select id="passdenom" class="select2_category form-control passdenom" style="width:140px" ><option value="0">Select</option>';
		        for(var i=0;i<result.length-1;i++){
		    		var data=result[i].split("@");
		    		passdropoption+='<option value="'+data[0]+'">'+data[1]+'</option>';
		    		passdenomdropdownvalue[data[0]]=data[1];
		    		//denomval.push()
		    	}
		        passdropoption+=' </select>';
		        document.getElementById("passdenom"+(rowid+1)).innerHTML=passdropoption;
		    },
		    error : function(xhr, errmsg) {
		    	alert("Session out error..Please login again");
		    }
		});
		$("#pass_no_of_pass_"+rowid).removeAttr("readonly");
		$("#pass_no_of_books_"+rowid).removeAttr("readonly");
	 	if(passtypeval==2){
			$("#pass_no_of_pass_"+rowid).attr("readonly","readonly");
			
		}else if(passtypeval==3){
			$("#pass_no_of_books_"+rowid).attr("readonly","readonly");
		}else{
			$("#pass_no_of_pass_"+rowid).removeAttr("readonly");
			$("#pass_no_of_books_"+rowid).removeAttr("readonly");
		}
	}
	function loadPassDayOrMonth(rec_id) {
		var tickettype = $('#passtype_'+ rec_id).val();
		$.ajax({
			type : "POST",
			url : "GetPassDayOrMonth.action?tickettype="+ tickettype,
			success : function(response) {
				var daymontharr = response.split(',');										
				var i = 0;
				var a1 = '<select class="form-control pass_day_" id="pass_day_'+ rec_id+ '" style="width:150px" "><option value="0">Select</option>';
				/* onblur="validatePassDays('+rec_id+') */
				for (i = 0; i < daymontharr.length - 1; i++) {
					var splitresult1 = daymontharr[i].split('@');
					a1 += '<option value="'	+ splitresult1[0]+ '">'	+ splitresult1[1]+ '</option>'
				}
				a1 += '</select>'
				$("#passdaydropid"+rec_id).html(a1);
			}
		});
	}
	function validatePassTypeDenomno(val){
	}
	
	function validateLuggStartTicketno(val,counter){
		val.value=padLeft(val.value,8);
		if(val.value.length>8){
			alert("Last two digits of Luggage Type Start number should be 00 or 50.");
			val.focus();
			return false;
		}
		if(val.value.slice(-2)=='00' || val.value.slice(-2)=='50'){
			
		} else{
			alert("Last two digits of Luggage Type Start number should be 00 or 50.");
			val.focus();
			return false;
		}
		var loccounter=0;
		$(".lugg_end_no").each(function() {
			if(loccounter==counter){
				//$(this).val(padLeft(val.value,8).substring(0, 6));
				
			}
			loccounter++;
		}); 
	}
	
	function validateTsheetStartTicketno(val,counter){
		val.value=padLeft(val.value,8);
		
	}
	function validateLuggEndTicketNo(val,counter){
		
		if(val.value.slice(-2)=='49' || val.value.slice(-2)=='99'){
			
		} else{
			alert("Last two digits of Luggage Type End number should be 49 or 99");
			val.focus();
			return false;
		}
		var loccounter=0;
		var startluggno=0;
		var denomkey="";
		$(".lugg_start_no").each(function() {
			if(loccounter==counter){
				startno=$(this).val();
				if($(this).val()>=val.value){
					alert(" Luggage Type End number should be greater Than Start No.");
					val.focus();
					return false;
				}
			}
			loccounter++;
		}); 
		var value=(val.value-startno+1);
		var book=(val.value-startno+1)/50;
		
		if(!isInt(book) || book<=0){
			alert("Invalid Start Or End Luggage No.");
			return false;
		}
		var loccounter=0;
		$(".lugg_no_of_tickets").each(function() {
			if(loccounter==counter){
				$(this).val(value);
			}
			loccounter++;
		}); 
		var loccounter=0;
		$(".lugg_no_of_books").each(function() {
			console.log(book+"\t loccounter:"+loccounter);
			if(loccounter==counter && book>0){
				$(this).val(book);
			}
			loccounter++;
		}); 
		var loccounter=0;

		$(".lugg_ticket_key").each(function() {
			if(loccounter==counter){
				denomkey=$(this).val();
			}
			loccounter++;
		}); 
		
		getPriority("nodenom",denomkey,4,counter);
		//getTicketsDuplicateCheck("nodenom",denomkey,startno,val.value,4);
		if(!checkDuplicateRecords(4,val)){
			alert("Duplicate Entry In Luggage Type.");
	    	val.value="";
	    	val.focus();
	    	return false;
		}
		getTicketsDuplicateCheck("nodenom",denomkey,startno,val.value,4).success(function (data) {
			   // alert(data);
		    if(data=="Duplicate Data"){
		    	alert("Duplicate Entry In Luggage Type.");
		    	val.value="";
		    	val.focus();
		    	return false;
		    }
		});
	}
	
function validateTSheetEndTicketNo(val,counter){
		
	val.value=padLeft(val.value,8);
		var endno=0;
		var loccounter=0;
		var starttsheetno=0;
		var denomkey="";
		$(".tsheet_start_no").each(function() {
			if(loccounter==counter){
				starttsheetno=$(this).val();
				endno=val.value;
				
				if($(this).val()>=val.value){
					
					alert(" Trip Sheet Type End number should be greater Than Start No.");
					//val.focus();
					return false;
					}
			}
			loccounter++;
		}); 
		
		var value=(val.value-starttsheetno+1);
		var loccounter=0;
		$(".no_of_tsheets").each(function() {
			if(loccounter==counter){
				$(this).val(value);
			}
			loccounter++;
		}); 
		$(".waybill_key").each(function() {
			if(loccounter==counter){
				denomkey=$(this).val();
			}
			loccounter++;
		}); 
		
		if(!checkDuplicateRecords(5,val)){
			alert("Duplicate Entry In Trip Sheet Type.");
	    	val.value="";
	    	//val.focus();
	    	return false;
		}
		
		getTicketsDuplicateCheck("nodenom",denomkey,starttsheetno,val.value,5).success(function (data) {
			  
		    if(data=="Duplicate Data"){
		    	alert("Duplicate Entry In Trip Sheet Type.");
		    	val.value="";
		    	//val.focus();
		    	return false;
		    }
		});
		
	}
	
	<%-- function getOrgType(){
		 var len= document.getElementById('unittypeid').options.length;
		 if(len<=1 ) {
	       $.ajax({
	           type: "get",
	           url: '<%=request.getContextPath()%>/findAllOrgTypeAction',
	           success: function(result) {
	               document.getElementById('unittypeid').innerHTML=result;
	           }
	       });
		}
	}
	
	function getOrgType(){
	 	var len= document.getElementById('unittypeid').options.length;
		if(len<=1 ) {
	       $.ajax({
	           type: "get",
	           url: '<%=request.getContextPath()%>/findAllOrgTypeActionForInventory',
	           success: function(result) {
	               document.getElementById('unittypeid').innerHTML=result;
	           }
	       });
		}
	} --%>
	
	<%-- function getUnitNames(){
		
		$('#select2-chosen-2').html("Select");
		var e = document.getElementById("unittypeid");
		var strUser = e.options[e.selectedIndex].value;
		var len= document.getElementById('unitnameid').options.length;
      	$.ajax({
	        type: "get",
	        url: '<%=request.getContextPath()%>/findUnitNameActionForInventory?orgid='+strUser,
	        success: function(result) {
	     	  
	            document.getElementById('unitnameid').innerHTML=result;
	        }
     	});
	} --%>
	
	function getDayDropdown(){
	    $.ajax({
           type: "post",
           url: '<%=request.getContextPath()%>/getDayDropdown',
           success: function(result) {
               document.getElementById('daydropdown').value=result;
           }
       });
	}
	
	function getMonthDropDown(){
	    $.ajax({
	        type: "post",
	        url: '<%=request.getContextPath()%>/getMonthDropDown',
	        success: function(result) {
	     	  
	            document.getElementById('monthdropdown').value=result;
	        }
	    });
	}
	
	function saveAjaxForm(){
		$('#subform').prop("disabled", true); 
		var tkt_denom_no=[];
		var tkt_denom_key=[];
		var tkt_denom_value=[];
		var tkt_start_no=[];
		var tkt_end_no=[];
		var tkt_priority=[];
		var tkt_value=[];
		var pass_pass_type=[];
		var pass_denom_no=[];
		var pass_denom_value=[];
		var pass_denom_key=[];
		var pass_pass_day=[];
		var pass_start_no=[];
		var pass_end_no=[];
		var pass_prority=[];
		var pass_value=[];
		var lugg_ticket_key=[];
		var lugg_start_no=[];
		var lugg_end_no=[];
		var lugg_priority=[];
		var waybill_key=[];
		var tsheet_start_no=[];
		var tsheet_end_no=[];
		//var unit_type=$('#unittypeid').val();
		//var unit_name=$('#unitnameid').val();
	/* 	if(unit_type==0){
			alert("Please Select Unit Type");
			$('#subform').prop("disabled", false); 
			return false;
		}
		if(unit_name==0){
			alert("Please Select Unit Name");
			$('#subform').prop("disabled", false); 
			return false;
		} */
		
		$(".denom").each(function() {
			tkt_denom_no.push($(this).val());
			}); 
		
		$(".denom_key").each(function() {
			tkt_denom_key.push($(this).val());
			tkt_denom_value.push(denomdropdownvalue[$(this).val()]);
			}); 
		
		$(".start_no").each(function() {
			tkt_start_no.push($(this).val());
			}); 
		
		$(".end_no").each(function() {
			tkt_end_no.push($(this).val());
			}); 
		
		$(".priority").each(function() {
			tkt_priority.push($(this).val());
			}); 
		
		$(".value").each(function() {
			tkt_value.push($(this).val());
			}); 
		var isPassengerTicketsAvailable = false;
		var isPassesAvailable = false;
		var isLuggageTicketsAvailable = false;
		for(var i=0; i<tkt_start_no.length;i++){
			if(tkt_start_no[i].slice(-2)!='00'){
				alert("Last two digits of Ticket Type Start number should be 00");
				$('#subform').prop("disabled", false); 
				return false;
			}
			if(tkt_end_no[i].slice(-2)!='99'){
				alert("Last two digits of Ticket Type End number should be 99 ");
				$('#subform').prop("disabled", false); 
				return false;
			} 
			if(parseFloat(tkt_end_no[i])< parseFloat(tkt_start_no[i])){
				alert("Last two digits of Ticket Type End number should be greater Than Start No.");
				$('#subform').prop("disabled", false); 
				return false;
			} 
		}
		$(".passtype").each(function() {
			pass_pass_type.push($(this).val());
		}); 
		
		$(".passdenom").each(function() {
			pass_denom_no.push($(this).val());
			pass_denom_value.push(passdenomdropdownvalue[$(this).val()]);
		}); 
		
		$(".pass_denom_no").each(function() {
			pass_denom_key.push($(this).val());
		}); 
		
		$(".pass_day_").each(function() {
			pass_pass_day.push($(this).val());
		}); 
		
		$(".pass_start_no").each(function() {
			pass_start_no.push($(this).val());
		}); 
		
		$(".pass_end_no").each(function() {
			pass_end_no.push($(this).val());
		}); 
		
		$(".pass_priority").each(function() {
			pass_prority.push($(this).val());
		});
		
		$(".pass_total_pass").each(function() {
			pass_value.push($(this).val());
		});
		
		
		for(var i=0; i<pass_start_no.length;i++){
			
			if(pass_pass_type[i]==2){
			if(pass_start_no[i].length>8){
				alert("Last two digits of Pass Type Start number should be 01 or 51");
				$('#subform').prop("disabled", false); 
				return false;
			}
			
			/* if(pass_start_no[i].slice(-2)!='00'){
				alert("Please Enter 8 Digit Start No Ending With 01 or 51 In Pass Type ");
				return false;
			} */
			
			 if(pass_start_no[i].slice(-2)=='01' || pass_start_no[i].slice(-2)=='51'){
				 
			 }else{
				 alert("Last two digits of Pass Type Start number should be 01 or 51 ");
				 $('#subform').prop("disabled", false); 
					return false; 
			 }
			/* if(pass_end_no[i].slice(-2)!='99'){
				alert("Please Enter 8 Digit End No Ending With 00 or 50 And Greater Than Start No. In Pass Type ");
				return false;
				}  */
				
				 if(pass_end_no[i].slice(-2)=='00' || pass_end_no[i].slice(-2)=='50'){
					 
				 }else{
					 alert("Last two digits of Pass Type End number should be 00 or 50");
					 $('#subform').prop("disabled", false); 
					 return false;
				 }
			
			if(parseFloat(pass_end_no[i])< parseFloat(pass_start_no[i])){
				alert("Pass Type End number should be greater than Start No.");
				$('#subform').prop("disabled", false); 
				return false;
				} 
			}
			
			if(pass_pass_type[i]==3){
				if(pass_start_no[i].length>8){
					alert("Please Enter 8 Digit Start No In Pass Type ");
					$('#subform').prop("disabled", false); 
					return false;
				}
				if(pass_end_no[i].length>8){
					 alert("Please Enter 8 Digit End No  In Pass Type ");
					 $('#subform').prop("disabled", false); 
					 return false; 
				}
				if(parseFloat(pass_end_no[i])< parseFloat(pass_start_no[i])){
					alert("Please Enter End No Greater Than Or Equal To  Start No. In Pass Type ");
					$('#subform').prop("disabled", false); 
					return false;
				} 
			}
		}
		$(".lugg_ticket_key").each(function() {
			lugg_ticket_key.push($(this).val());
		});
		
		$(".lugg_start_no").each(function() {
			lugg_start_no.push($(this).val());
		});
		
		$(".lugg_end_no").each(function() {
			lugg_end_no.push($(this).val());
		});
		
		$(".lugg_priority").each(function() {
			lugg_priority.push($(this).val());
		});
		
		
		for(var i=0; i<lugg_start_no.length;i++){
			if(lugg_start_no[i].length>8){
				alert("Last two digits of Luggage Type Start number should be 00 or 50.");
				$('#subform').prop("disabled", false); 
				return false;
			}
			
			/* if(lugg_start_no[i].slice(-2)!='00'){
				alert("Please Enter 8 Digit Start No Ending With 00 or 50 In Luggage Type ");
				return false;
			} */
			
			 if(lugg_start_no[i].slice(-2)=='00' || lugg_start_no[i].slice(-2)=='50'){
				 
			}else{
				 alert("Last two digits of Luggage Type Start number should be 00 or 50.");
				 $('#subform').prop("disabled", false); 
					return false;
			}
			
			/* if(lugg_end_no[i].slice(-2)!='99'){
				alert("Please Enter 8 Digit End No Ending With 49 or 99 And Greater Than Start No. In Luggage Type ");
				return false;
				}  */
			
			if(lugg_end_no[i].slice(-2)=='49' || lugg_end_no[i].slice(-2)=='99'){
				
			}else{
				alert("Last two digits of Luggage Type End number should be 49 or 99 ");
				$('#subform').prop("disabled", false); 
				return false;
			}
			
			if(parseFloat(lugg_end_no[i])< parseFloat(lugg_end_no[i])){
				alert("Luggage Type End number should be greater Than Start No.");
				$('#subform').prop("disabled", false); 
				return false;
			} 
			
		}
		
		$(".waybill_key").each(function() {
			waybill_key.push($(this).val());
		});
		
		$(".tsheet_start_no").each(function() {
			tsheet_start_no.push($(this).val());
		});
		
		$(".tsheet_end_no").each(function() {
			tsheet_end_no.push($(this).val());
		});
		var orgId ="<s:property value='orgId'/>";
		var orgTypeId ="<s:property value='orgTypeId'/>";
		$.ajax({
		    type : 'POST',
		    data: 'tkt_denom_no='+ tkt_denom_no+	
		    '&tkt_denom_key='+ tkt_denom_key+
		    '&tkt_denom_value='+ tkt_denom_value+
		    '&tkt_start_no='+ tkt_start_no+
		    '&tkt_end_no='+ tkt_end_no+
		    '&tkt_priority='+ tkt_priority+
		    '&tkt_value='+ tkt_value+
		    
		    '&pass_pass_type='+ pass_pass_type+
		    '&pass_denom_no='+ pass_denom_no+
		    '&pass_denom_value='+ pass_denom_value+
		    '&pass_denom_key='+ pass_denom_key+
		    '&pass_pass_day='+ pass_pass_day+
		    '&pass_start_no='+ pass_start_no+
		    '&pass_end_no='+ pass_end_no+
		    '&pass_prority='+ pass_prority+
		    '&pass_value='+ pass_value+
		    
		    '&lugg_ticket_key='+ lugg_ticket_key+
		    '&lugg_start_no='+ lugg_start_no+
		    '&lugg_end_no='+ lugg_end_no+
		    '&lugg_priority='+ lugg_priority+
		    
		    '&waybill_key='+ waybill_key+
		    '&tsheet_start_no='+ tsheet_start_no+
		    '&tsheet_end_no='+ tsheet_end_no+
		    
		    '&unit_type='+ orgTypeId+
		    '&unit_name='+ orgId,
			
		    url :  "<s:url action='SaveTicketInventory'/>",
		   
		    success: function(data){
		    	alert(data);
		    	if(data=="Inventory Added Successfully..."){
		    		document.getElementById("cancel").click();
		    	}else{
		    		$('#subform').prop("disabled", false); 
		    	}
		    },
		    error : function(xhr, errmsg) { 
		    	alert("Your Login Session Has Expired. Please Login Again.");
		    }
		});
	}
	
	function getTicketsDuplicateCheck(denom,denomkey,startno,endno,tickettype){
		var ajaxdata="";
		return $.ajax({
		    type  : 'POST',
		    data: {
		       id:'1',
		       denomno :denom,
		       denomkey :denomkey,
		       startno: startno,
		       endno :endno,
		       tickettype :tickettype,
		        
		    },
		    url :  "<s:url action='GetTicketsDuplicateCheck'/>",
		    success: function(data){
		    	ajaxdata= data;
		    },
		    error : function(xhr, errmsg) {
		    }
		});
	}
	
	function checkDuplicateRecords(tickettype,val){
		if(tickettype==1){
			var tkt_denom_no=[];
			var tkt_denom_key=[];
			var tkt_start_no=[];
			var tkt_end_no=[];
			
			$(".denom").each(function() {
				tkt_denom_no.push($(this).val());
			}); 
			$(".denom_key").each(function() {
				tkt_denom_key.push($(this).val());
				
			}); 
			$(".start_no").each(function() {
				tkt_start_no.push($(this).val());
			}); 
			$(".end_no").each(function() {
				tkt_end_no.push($(this).val());
			}); 
			var ticketdupliarray=[];
			for(var i=0;i<tkt_denom_no.length;i++){
				var count=(parseFloat(tkt_end_no[i])-parseFloat(tkt_start_no[i])+1)/100;
				var startno=parseFloat(tkt_start_no[i]);
				var endno=parseFloat(startno)+99;
				for(var j=0; j<count; j++){
					ticketdupliarray.push(tkt_denom_no[i]+""+tkt_denom_key[i]+""+startno+""+endno);
					startno=parseFloat(endno)+1;
					endno=parseFloat(startno)+99;
				}
			}
			return checkIfArrayIsUnique(ticketdupliarray);
		}
		if(tickettype==2 || tickettype==3){
			var pass_pass_type=[];
			var pass_denom_no=[];
			var pass_denom_key=[];
			var pass_start_no=[];
			var pass_end_no=[];
			$(".passtype").each(function() {
				pass_pass_type.push($(this).val());
			}); 
			
			$(".passdenom").each(function() {
				pass_denom_no.push($(this).val());
				//pass_denom_value.push(passdenomdropdownvalue[$(this).val()]);
			}); 
			
			$(".pass_denom_no").each(function() {
				pass_denom_key.push($(this).val());
			}); 
			 
			
			$(".pass_start_no").each(function() {
				pass_start_no.push($(this).val());
			}); 
			
			$(".pass_end_no").each(function() {
				pass_end_no.push($(this).val());
			}); 
			var passdupliarray=[];
			if(tickettype==2){
				for(var i=0;i<pass_pass_type.length;i++){
					if(pass_pass_type[i]==2){
						var count=(parseFloat(pass_end_no[i])-parseFloat(pass_start_no[i])+1)/50;
						var startno=parseFloat(pass_start_no[i]);
						var endno=parseFloat(startno)+49;
						for(var j=0; j<count; j++){
							passdupliarray.push(pass_pass_type[i]+""+pass_denom_no[i]+""+pass_denom_key[i]+""+startno+""+endno);
							startno=parseFloat(endno)+1;
							endno=parseFloat(startno)+49;
						}
					}
				}
			}
			if(tickettype==3){
		
					for(var i=0;i<pass_pass_type.length;i++){
						if(pass_pass_type[i]==3){
							var count=(parseFloat(pass_end_no[i])-parseFloat(pass_start_no[i])+1);
							var startno=parseFloat(pass_start_no[i]);
							var endno=parseFloat(startno)+1;
							for(var j=0; j<count; j++){
								passdupliarray.push(pass_pass_type[i]+""+pass_denom_no[i]+""+pass_denom_key[i]+""+pass_start_no[i]+""+endno);
								startno=parseFloat(endno);
								endno=parseFloat(startno)+1;
							}
						}
					}
				
			}
			
			return checkIfArrayIsUnique(passdupliarray);
		
		}
		if(tickettype==4){
			var lugg_ticket_key=[];
			var lugg_start_no=[];
			var lugg_end_no=[];
			$(".lugg_ticket_key").each(function() {
				lugg_ticket_key.push($(this).val());
				});
			
			$(".lugg_start_no").each(function() {
				lugg_start_no.push($(this).val());
				});
			
			$(".lugg_end_no").each(function() {
				lugg_end_no.push($(this).val());
				});
			var luggdupliarray=[];
			for(var i=0;i<lugg_ticket_key.length;i++){
				var count=(parseFloat(lugg_end_no[i])-parseFloat(lugg_start_no[i])+1)/50;
				var startno=parseFloat(lugg_start_no[i]);
				var endno=parseFloat(startno)+49;
				for(var j=0; j<count; j++){
					luggdupliarray.push(lugg_ticket_key[i]+""+startno+""+endno);
					startno=parseFloat(endno)+1;
					endno=parseFloat(startno)+49;
				}
			}
			return checkIfArrayIsUnique(luggdupliarray);
		}
		if(tickettype==5){
			var waybill_key=[];
			var tsheet_start_no=[];
			var tsheet_end_no=[];
			$(".waybill_key").each(function() {
				waybill_key.push($(this).val());
				});
			
			$(".tsheet_start_no").each(function() {
				tsheet_start_no.push($(this).val());
				});
			
			$(".tsheet_end_no").each(function() {
				tsheet_end_no.push($(this).val());
				});
			var tsheetdupliarray=[];
			for(var i=0;i<waybill_key.length;i++){
				var count=(parseFloat(tsheet_end_no[i])-parseFloat(tsheet_start_no[i])+1)/50;
				var startno=parseFloat(tsheet_start_no[i]);
				var endno=parseFloat(startno)+49;
				for(var j=0; j<count; j++){
					tsheetdupliarray.push(waybill_key[i]+""+startno+""+endno);
					startno=parseFloat(endno)+1;
					endno=parseFloat(startno)+49;
				}
			}
			return checkIfArrayIsUnique(tsheetdupliarray);
		}
		
	}
	function checkIfArrayIsUnique(myArray){
    	isUnique=true;
        for (var i = 0; i < myArray.length; i++)
        {
            for (var j = 0; j < myArray.length; j++)
            {
                if (i != j)
                {
                    if (myArray[i] == myArray[j])
                    {
                        isUnique=false;
                    }
                }
            }
        }
      
        return isUnique;
    }
	
	function isInteger(txb){
		txb.value = txb.value.replace(/[^0-9]/g, "");
	} 
	
	function calculateEndNo(val,counter,type){
		if(type==1 && val.value!=""){
			var startval=0;
			var endval=0;
			var localcounter=0;
			$(".start_no").each(function() {
				if(localcounter==counter){
					startval=$(this).val();
				}
				localcounter++;
			});
			endval=parseFloat(startval)+100*val.value-1;
			console.log(endval+"\t"+val.value +" end");
			var localcounter=0;
			$(".end_no").each(function() {
				if(localcounter==counter){
					$(this).val(padLeft(endval,8));
					return validateEndTicketNo(this,counter);
				}
				localcounter++;
			});
		}
		
		if(type==4){
			
			var startval=0;
			var endval=0;
			var localcounter=0;
			$(".lugg_start_no").each(function() {
				if(localcounter==counter){
					startval=$(this).val();
				}
				localcounter++;
			});
			
			endval=parseFloat(startval)+50*val.value-1;
			
			var localcounter=0;
			$(".lugg_end_no").each(function() {
				if(localcounter==counter){
					$(this).val(padLeft(endval,8));
					return validateLuggEndTicketNo(this,counter);
				}
				localcounter++;
			});
			
		}
		
		if(type==2 || type==3){
			
			var startval=0;
			var endval=0;
			var passtype=0;
			var localcounter=0;
			$(".passtype").each(function() {
				if(localcounter==counter){
					passtype=$(this).val();
				}
				localcounter++;
			});
			if(passtype==3){
				val.value=0;
				return false;
			}
			
			var localcounter=0;
			$(".pass_start_no").each(function() {
				if(localcounter==counter){
					startval=$(this).val();
				}
				localcounter++;
			});
			
			endval=parseFloat(startval)+50*val.value-1;
			
			var localcounter=0;
			$(".pass_end_no").each(function() {
				if(localcounter==counter){
					$(this).val(padLeft(endval,8));
					return validatePassEndTicketNo(this,counter);
				}
				localcounter++;
			});
			
		}
	}
	function calculateMonthlyPassEndNo(val,counter){
		var type=$("#passtype_"+counter).val();
		//alert(type)
		if(type=="3"){
			var startval=0;
			var endval=0;
			var passtype=0;
			var localcounter=0;
			$(".passtype").each(function() {
				if(localcounter==counter){
					passtype=$(this).val();
				}
				localcounter++;
			});
			var localcounter=0;
			$(".pass_start_no").each(function() {
				if(localcounter==counter){
					startval=$(this).val();
				}
				localcounter++;
			});
			
			endval=parseFloat(startval)+1*val.value-1;
			//alert(endval);
			var localcounter=0;
			$(".pass_end_no").each(function() {
				if(localcounter==counter){
					$(this).val(padLeft(endval,8));
					return validatePassEndTicketNo(this,counter);
				}
				localcounter++;
			});
		}
	}
	function moveBack(){
		document.forms[0].action = 'GetTktInvView.action';
		document.forms[0].submit();
	}
	function duplicateentrycheck(){
		$('#select2-chosen-2').html("Select");
		
		var orgtypid = "<s:property  value="orgTypeId"/>"
		
		var orgchartid = "<s:property  value="orgId"/>"
		var alertData2=$.ajax({
			type: "get",
			async : false,
			url: '<%=request.getContextPath()%>/checkDuplicateInitialStock?ortype='+orgtypid
                + "&orgcht="+ orgchartid,
			success: function(count) {
				if(count!=0){ 
					//alert("Initial Stock already exist.");
					$('#subform').prop("disabled", true); 
					
        	   	}
    	   		return count;
      		}
		}).responseText;
			
	}
	window.onload=duplicateentrycheck;
	</script>
</body>
</html>