
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
<style>
p.intro {color:red;font-size:20px;}
p.success{color:green; font-size:10px;}
.SmallInput { width: 90px; height: 35px; }
</style>
<link rel="stylesheet"href="<%=request.getContextPath()%>/assets/global/jAlert/jquery.alerts.css" />
<script src="<%=request.getContextPath()%>/assets/global/jAlert/jquery.alerts.js"></script>
<script src="<%=request.getContextPath()%>/assets/global/jAlert/jQuery.js"></script>
<script src="<%=request.getContextPath()%>/assets/global/jAlert/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/inventory/jquery.blockUI.js"></script>
<script>
/* $('html').bind('keypress', function(e){
	if(e.keyCode == 13){
		return false;
	}
}); *
/* $("#addStockEntry1").bind('keypress', function(e1){
	alert("enter key pressed");
	   if(e1.keyCode==13){          // if user is hitting enter
	       return false;
	   }
}); */
$(document).ready(function()	    {
	        // Stop user to press enter in textbox
	        $("input:text").keypress(function(event) {
	            if (event.keyCode == 13) {
	                event.preventDefault();
	                return false;
	            }
	        });
	});
function aaa(){
	 $.blockUI({ overlayCSS: { backgroundColor: '#00f' } }); 
}
function unBlockScreen(){
	$.unblockUI();
}
var stockEntryTempId = "<s:property value='stockEntryTempRefId'/>";
</script>
</head>
<body>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js" type="text/javascript"></script>
<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.min.js" type="text/javascript"></script>
	<form action="#" name="stockentryform" method="post" id="myForm">
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
							<div class="portlet box grey-cascade" id="floordiv">
								<div class="portlet-title">
									<div class="caption">
										<i class="fa fa-edit"></i>Central Stock Receipts
									</div>
								</div>
								<div class="portlet-body">
								<table  cellpadding="10" style="width: 95%;text-align: center;" >
									<tr>
										<td><label>Indent number : </label></td>
										<td><input type="text" class="form-control" name="indentNumber" maxlength="12" id="indentNumber"/></td>
										<td><label>Indent date : </label></td>
										<td><div class="input-group input-medium date date-picker"	style="width: auto" data-date-format="dd-mm-yyyy" data-date-viewmode="years" >
												<input type="text" class="form-control" readonly value="" name="indentDate"  id="indentDate"/> 
												<span class="input-group-btn">
													<button class="btn default" type="button">
														<i class="fa fa-calendar"></i>
													</button>
												</span>
											</div>
										</td>
										<td><label>Receipt voucher number</label></td>
										<td><input type="text" class="form-control" name="receiptVoucherNo" id="receiptVoucherNo" maxlength="12"/></td>
									</tr>
									<tr>
										<td>&nbsp;&nbsp;</td>
									</tr>
									<tr>
										<td colspan="6" style="text-align: left"><h5><b>Vendor / Press details</b></h5></td>
									</tr>
									<tr>
										<td><label>Name : </label></td>
										<td><input type="text" class="form-control" name="vendorName" maxlength="12" id="vendorName"/></td>
										<td><label>Contact number :</label> </td>
										<td><input type="text" class="form-control" name="vendorContact" maxlength="12" id="vendorContact"/></td>
										<td><label>Address : </label></td>
										<td><textarea class="form-control" name="vendorAddress" id="vendorAddress"></textarea></td>
										
									</tr>
								</table>
								
									<br/>									<br/> <br/>
									<div>	
										<div class="portlet-title">
                        					<div class="caption" style="font-size:16px">
								 				<b>Ticket Type</b><br/><br/>
											</div>
										</div>
								 		<input type="hidden" name="stockentrysuccmsg" id="stockentrysuccmsg"/>
										<div class="table-toolbar">
											<div class="btn-group">
												<b><s:actionerror/></b>
												<button id="addStockEntry1" class="btn green">	Add Ticket Type  <i class="fa fa-plus"></i>
												</button>
												<div class="portlet-body form">
												</div>
											</div>
                                     		<div class="alert alert-danger" id="errormsg" style="display:none">
                                       			<b><p class="intro" id="error" ></p></b>
                                      			<b><p class="intro" id="errorticket1" ></p></b>
			                               		<b><p class="intro" id="errorticket2" ></p></b>
			                              		<b><p class="intro" id="errorticket3" ></p></b>
			                                	<b><p class="intro" id="errorticket4" ></p></b>
			                                	<b><p class="intro" id="errorticket5" ></p></b>
		                                     	<span>
			                                  	</span>
		                              		</div> 
		                             		<div id="successmsgticket" style="display:none">
			                           			<b><p class="intro" id="succticket" ></p></b>
		                                   		<span>
			                                 	</span>
		                              		</div>
											<table class="table table-striped table-hover table-bordered"
												id="stockentry">
												<thead>
													<tr>
														<th width="12%">Denom</th>
														<th width="15%">Denom key</th>
														<th width="12%">Start No</th>
														<th width="12%">End No</th>
														<th width="13%">No.of Books</th>
														<th width="13%">Value</th>
                                                		<th width="13%">Priority</th>
                                                	    <th width="13%">Delete</th>
                                                		
													</tr>
												</thead>
											</table>
											<br><br><br>
										</div>	
									</div>
									<div class="portlet-body">
									<div>	
										<div class="portlet-title">
                        					<div class="caption" style="font-size:16px">
								 				<b>Pass Type</b><br/><br/>
											</div>
										</div>
									</div>		
									</div>
									<div class="btn-group">
										<button id="addPassStockEntry" class="btn green">
												Add Pass Type <i class="fa fa-plus"></i>
										</button>
									</div>
                                    <div class="alert alert-danger" id="errormsgpass" style="display:none">
                                    	<b><p class="intro" id="error1" ></p></b>
			                            <b><p class="intro" id="errorpass1" ></p></b>		                                    
										<b><p class="intro" id="errorpass2" ></p></b>		    
										<b><p class="intro" id="errorpass3" ></p></b>		    
										<b><p class="intro" id="errorpass4" ></p></b>		    
										<b><p class="intro" id="errorpass5" ></p></b>		    
										<span>    
			                        	</span>
		                           	</div>
		                              <div id="successmsgpass" style="display:none">
			                           <b><p class="intro" id="succpass" ></p></b>
		                                     <span>
			                                  </span>
		                              </div>
									<table class="table table-striped table-hover table-bordered" 
										id="stockPassEntry" style="width: auto;">
										<thead>
										   <tr>
                                                <th width="12%">Passtype</th>
												<th width="15%">Denom</th>
												<th width="12%">Denom Key</th>
												<th width="15%">Day/Month</th>
												<th width="12%">Start No</th>
												<th width="13%">End No</th>
												<th width="12%">No.of Passes</th>
												<th width="13%">No.of Books</th>
												<th width="13%">Total Value</th>
												<th width="13%">Priority</th>
												<th width="13%">Delete </th>
											</tr>
										</thead>
									</table>
									<br><br><br>
								</div>
										<div class="portlet-body">
								<div>	
							<div class="portlet-title">
                       			<div class="caption" style="font-size:16px">
									<b>Luggage Type</b><br/><br/>
								</div>
							</div>
								</div>
									<div class="table-toolbar">
										<div class="btn-group">
											<button id="addLuggageStockEntry" class="btn green">
												Add Luggage Type <i class="fa fa-plus"></i>
											</button>
										</div>
									</div>
                                     <div class="alert alert-danger" id="errormsgluggage" style="display:none">
                                       <b><p class="intro" id="errorluggage1" ></p></b>
			                           <b><p class="intro" id="errorluggage2" ></p></b>
			                           <b><p class="intro" id="errorluggage3" ></p></b>
			                           <b><p class="intro" id="errorluggage4" ></p></b>
			                           <b><p class="intro" id="errorluggage5" ></p></b>
		                                     <span>
			                                  </span>
		                              </div>
		                               <div id="successmsgluggage" style="display:none">
			                           <b><p class="intro" id="succluggage" ></p></b>
		                                     <span>
			                                  </span>
		                              </div>
									<table class="table table-striped table-hover table-bordered"
										id="stockLuggageEntry">
										<thead>
                                           <tr>
												<th width="15%">Ticket Key</th>
												<th width="15%">Start No</th>
												<th width="15%">End No</th>
												<th width="17%">No.of Tickets</th>
												<th width="14%">No.of Books</th>
                                                <th width="15%">Priority</th>
                                                <th width="13%">Delete</th>
											</tr>
										</thead>
									</table>
									<div>	
							<div class="portlet-title">
                       			<div class="caption" style="font-size:16px">
									<b>Trip Sheet Type</b><br/><br/>
								</div>
							</div>
								</div>
								<div class="table-toolbar">
										<div class="btn-group">
											<button id="addTripSheetStockEntry" class="btn green">
												Add Trip Sheet Type <i class="fa fa-plus"></i>
											</button>
										</div>
									</div>
									 <div class="alert alert-danger" id="errormsgtsheet" style="display:none">
                                       <b><p class="intro" id="errortsheet1" ></p></b>
			                           <b><p class="intro" id="errortsheet2" ></p></b>
			                           <b><p class="intro" id="errortsheet3" ></p></b>
			                           <b><p class="intro" id="errortsheet4" ></p></b>
			                           <b><p class="intro" id="errortsheet5" ></p></b>
		                              </div>
		                              <div id="successmsgtsheet" style="display:none">
			                           <b><p class="intro" id="succtsheet" ></p></b>
		                              </div>
									<table class="table table-striped table-hover table-bordered"
										id="stockTsheetEntry">
										<thead>
                                           <tr>
												<th width="15%">Waybill key</th>
												<th width="15%">Start No</th>
												<th width="15%">End No</th>
												<th width="17%">No.of Waybill</th>
												<th width="13%">Delete</th>
											</tr>
										</thead>
									</table>
									<button id="stockentry_save_tab"  class="btn blue">Save</button>
									<button id="backbtn1" class="btn blue">Back</button><div id="submitButton"> &nbsp;&nbsp;</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	<script>
	function startProcessing(){
		$("#stockentry_save_tab").attr('disabled','disabled');
		$("#submitButton").html("<img src='../its/inventory/loading-icon.gif' height='50px' width='70px'/>");
	}
	function endProcessing(){
		$("#stockentry_save_tab").attr('disabled',false);
		$("#submitButton").html("");
	}
/**************Validating Start Number of Ticket Entry*************************/
function validateStartTicketno(rec_id,tickarrlength){
	
		if($('#start_no_'+tickarrlength).attr('readonly') == undefined){
		document.getElementById('no_of_books_' + rec_id).value="";
		document.getElementById('value_' + rec_id).value="";
		document.getElementById('priority_' + rec_id).value="";
					
		var start_no = $('#start_no_' + rec_id).val();
		var denom_key = $('#denom_key_' + rec_id).val();
		var end_no = $('#end_no_' + rec_id).val();
		var denomid=$('#denomi_no_'+rec_id+' option:selected').val();
		var denom_no = $('#denomi_no_' +rec_id ).val();
		if(start_no==""){
			alert("Start no of is manadatory");
		}
	 	var numbersOnly = /^\d+$/;   
		if(start_no!="" && !start_no.match(numbersOnly)){   
			alert('Start number should be numerical'); 
		    document.getElementById('start_no_' + rec_id).value="";
		}
		var firsttwostart = start_no % 100;  
		if(firsttwostart!=00){
			alert("Ticket Type  last two digits of the Start number should be 00");
			document.getElementById('value_' + rec_id).focus();
			setTimeout(function() {
				document.getElementById('start_no_' + rec_id).focus();
			}, 100); 
			return false;
		}
		if(start_no!=""){
			$('#start_no_' + rec_id).val(padLeft(start_no,8));
		}
		if((start_no!="")&&(end_no!="")){
			if(parseInt(end_no)>parseInt(start_no)){
				var denomid=$('#denomi_no_'+rec_id+' option:selected').val();
				var denom_key = $('#denom_key_' + rec_id).val();
				var alertData=$.ajax({
			        type: "post",
			        dataType : 'json',
					global : false,
					async : false,
			        url: '<%=request.getContextPath()%>/checkDuplicateDenomAction?denomval='+denomid
				        + "&denom_key="+denom_key
	        	        + "&start_no="+ start_no
	                    + "&end_no="+ end_no,
					success: function(count) {
		        	   	if(count!=0){
					  		alert("Ticket type already exist.");
					  		//jAlert('<font size="4" color="red"><b>Ticket type already exist.!</b></font>');
			    			document.getElementById('no_of_books_' + rec_id).readOnly = "true";
							document.getElementById('value_' + rec_id).readOnly = "true";
							document.getElementById('priority_' + rec_id).readOnly = "true";
		        	   }
		        	   return count;
		          }
				}).responseText;
				var duplflag=true;
				var arravalue="";
				var arr = [];
				var passduplflag=false;
				if(parseInt(tickarrlength)>=1){
					for(var duplttkt=0;duplttkt<tickarrlength;duplttkt++){
						arravalue="";						
						arravalue= $('#denomi_no_' +duplttkt).val()+""+ $('#denom_key_' + duplttkt).val()+""+$('#start_no_' + duplttkt).val()+""+$('#end_no_' + duplttkt).val();
							//or you could use: var arr = [];
					    if(($('#denomi_no_'+duplttkt+' option:selected').val()==denomid)&&($('#denom_key_' + duplttkt).val()==denom_key)){
					    	if((parseInt(start_no)>= parseInt($('#start_no_' + duplttkt).val()))&&(parseInt(start_no)<=parseInt($('#end_no_' + duplttkt).val()))){
				  		  		passduplflag=true;
					         }
				    		 if((parseInt(end_no)>= parseInt($('#start_no_' + duplttkt).val()))&&(parseInt(end_no)<=parseInt($('#end_no_' + duplttkt).val()))){
				    			 passduplflag=true; 
				   		 	 }
				  		}
						arr.push(arravalue);
					}
				}
		    	if(passduplflag==true){
					alert("Ticket already exist.");
			 		document.getElementById('no_of_books_' + rec_id).readOnly = "true";
					document.getElementById('value_' + rec_id).readOnly = "true";
					document.getElementById('priority_' + rec_id).readOnly = "true";
				}
				if((alertData==0)&&(passduplflag==false)){
					document.getElementById('no_of_books_' + rec_id).readOnly = false;
					document.getElementById('value_' + rec_id).readOnly = false;
					document.getElementById('priority_' + rec_id).readOnly = false;
					var diff=(end_no-start_no)+1;
					var blockno = diff / 100;
					var partblock = diff % 100;
					var noofbooks=blockno;
					var denomval=$('#denomi_no_'+rec_id+' option:selected').text().toString();
					var val=diff*parseInt(denomval);
					document.getElementById('no_of_books_' + rec_id).value=noofbooks;
					document.getElementById('value_' + rec_id).value=val;
					$.ajax({
						type: "post",
						url: '<%=request.getContextPath()%>/findPriority?denomid='+denomid+'&denom_key='+ denom_key,
						success: function(result) {
							for(var i=0;i<tickarrlength;i++){
								            	/* alert($('#denomi_no_'+i+' option:selected').val()+"====="+denomid); */
								if(($('#denomi_no_'+i+' option:selected').val()==denomid)){
									result++;
								}
							}
							document.getElementById('priority_' + rec_id).value=result;
		         	   }
		    		});
		    	}
			}
		}
	}
}			    
			      
function validateEndTicketNo(rec_id,tickarrlength){
	if($('#end_no_'+tickarrlength).attr('readonly') == undefined){
		document.getElementById('no_of_books_' + rec_id).value="";
		document.getElementById('value_' + rec_id).value="";
		document.getElementById('priority_' + rec_id).value="";
		var start_no = $('#start_no_' + rec_id).val();
		var firsttwostart = start_no % 100;
	    var end_no = $('#end_no_' + rec_id).val();
		if(end_no==""){
			alert("Ticket Type end no of  is manadatory");
		}
		var numbersOnly = /^\d+$/;   
		if(end_no!="" && !end_no.match(numbersOnly)){   
			alert('Ticket Type End Number should not be decimal'); 
		}  
		if(end_no!=""){		 
			var endlasttwo=end_no%100;
			if(endlasttwo!=99){
				alert("Ticket Type last two digits of the End number should be 99");
				document.getElementById('value_' + rec_id).focus();
				setTimeout(function() {
					document.getElementById('end_no_' + rec_id).focus();
				}, 100);
			return false; 
			}
		}
		var denomval=$('#denomi_no_'+rec_id+' option:selected').text().toString();
		var end = $('#end_no_' + rec_id).val();
		
		if((start_no!="")&&(end_no!="")){
			if(parseInt(end_no)>parseInt(start_no)){
				var formattedEndNo=padLeft(end,8);
				$('#end_no_'+ rec_id).val(formattedEndNo);
				var denomid=$('#denomi_no_'+rec_id+' option:selected').val();
			    var denom_key = $('#denom_key_' + rec_id).val();
				var alertData=$.ajax({
					type: "post",
					dataType : 'json',
					global : false,
					async : false,
					url: '<%=request.getContextPath()%>/checkDuplicateDenomAction?denomval='+denomid
					    + "&denom_key="+denom_key
		                + "&start_no="+ start_no
		                + "&end_no="+ end_no,
					success: function(count) {
					if(count!=0){
						alert("Ticket type already exist.");
						document.getElementById('no_of_books_' + rec_id).readOnly = "true";
						document.getElementById('value_' + rec_id).readOnly = "true";
						document.getElementById('priority_' + rec_id).readOnly = "true";
						}
					return count;
					}
				}).responseText;
				var duplflag=true;
				var arravalue="";
				var arr = [];
				var passduplflag=false;
				if(parseInt(tickarrlength)>=1){
					for(var duplttkt=0;duplttkt<tickarrlength;duplttkt++){
						arravalue="";						
						arravalue= $('#denomi_no_' +duplttkt).val()+""+ $('#denom_key_' + duplttkt).val()+""+$('#start_no_' + duplttkt).val()+""+$('#end_no_' + duplttkt).val();
					    if(($('#denomi_no_'+duplttkt+' option:selected').val()==denomid)&&($('#denom_key_' + duplttkt).val()==denom_key)){
					    	if((parseInt(start_no)>= parseInt($('#start_no_' + duplttkt).val()))&&(parseInt(start_no)<=parseInt($('#end_no_' + duplttkt).val()))){
						    		passduplflag=true;
					        }
					    	if((parseInt(end_no)>= parseInt($('#start_no_' + duplttkt).val()))&&(parseInt(end_no)<=parseInt($('#end_no_' + duplttkt).val()))){
						    		 passduplflag=true; 
						    }
						}
						arr.push(arravalue);
					}
				}
				if(passduplflag==true){
			 		alert("Ticket type already exist.");
			 		document.getElementById('no_of_books_' + rec_id).readOnly = "true";
					document.getElementById('value_' + rec_id).readOnly = "true";
					document.getElementById('priority_' + rec_id).readOnly = "true";
					return false;
				}
				if((alertData==0)&&(passduplflag==false)){
					/* document.getElementById('no_of_books_' + rec_id).readOnly = false;
					document.getElementById('value_' + rec_id).readOnly = false;
					document.getElementById('priority_' + rec_id).readOnly = false; */
				    var diff=(end_no-start_no)+1;
					var blockno = diff / 100;
					var partblock = diff % 100;
					var noofbooks=blockno;
					var val=diff*parseInt(denomval);
					document.getElementById('no_of_books_' + rec_id).value=noofbooks;
					document.getElementById('value_' + rec_id).value=val;
					$.ajax({
			            type: "post",
				        url: '<%=request.getContextPath()%>/findPriority?denomid='+denomid+'&denom_key='+ denom_key,
			            success: function(result) {
			           		for(var i=0;i<tickarrlength;i++){
							  		/* alert($('#denomi_no_'+i+' option:selected').val()+"====="+denomid); */
								if(($('#denomi_no_'+i+' option:selected').val()==denomid)){
									result++;
								}
							}
							document.getElementById('priority_' + rec_id).value=result;
						}
					});
			   }
			}else{
				alert("Ticket Type End Number should be greater than start number");
				document.getElementById('value_' + rec_id).focus();
					setTimeout(function() {
						document.getElementById('end_no_' + rec_id).focus();
					}, 100);
				return false;
			}
		}
	}
}			      
          /*************Validatidating Ticket Key ****************************************************/
function validateTicketKey(rec_id){
		var denom_no = $('#denomi_no_' +rec_id ).val();
		if(denom_no==0){
			alert("Please select a denomination of Ticket type record");
			/* document.getElementById('value_' + rec_id).focus();
			setTimeout(function() {
				document.getElementById('denomi_no_' + rec_id).focus();
			}, 100);
			return false; */
			//jAlert('<font size="4" color="red">Please select a denomination of Ticket type record!</font>');
		}
		var denom_key = $('#denom_key_' + rec_id).val();
		if(denom_key==""){
			alert("Ticket type  key  is manadatory");
			/* document.getElementById('value_' + rec_id).focus();
			setTimeout(function() {
				document.getElementById('denom_key_' + rec_id).focus();
		    }, 200);
			return false; */
			//jAlert('<font size="4" color="red"><b>Ticket type  key  is manadatory!</b></font>');
		}
	} 
	function validateTicketDenomno(rec_id){
    	var denom_no = $('#denomi_no_' +rec_id ).val();
		if(denom_no==0){
			alert("Please select a denomination of Ticket type record");
			//jAlert('<font size="4" color="red"><b>Please select a denomination of Ticket type record!</b></font>');
			/* document.getElementById('value_' + rec_id).focus();
				setTimeout(function() {
					document.getElementById('denomi_no_' + rec_id).focus();
				}, 100);
			return false; */
		}
	}
	function clearAllDataOfRow(rec_id){
	
		document.getElementById('no_of_books_' + rec_id).value="";
		document.getElementById('value_' + rec_id).value="";
		document.getElementById('priority_' + rec_id).value="";
					
		var start_no = $('#start_no_' + rec_id).val("");
		var denom_key = $('#denom_key_' + rec_id).val("");
		var end_no = $('#end_no_' + rec_id).val("");
		//var denomid=$('#denomi_no_'+rec_id+' option:selected').val();
		//var denom_no = $('#denomi_no_' +rec_id ).val();
	}
	/***********************Validating Luggage Key*****************************************/
	
function validateLuggageKey(rec_id){
	var denom_key = $('#luggage_ticket_key_' + rec_id).val();
	if(denom_key==""){
		alert("Luggage ticket key of is manadatory");
	}
}
function validateTsheetKey(rec_id){
	var denom_key = $('#waybill_key' + rec_id).val();
	if(denom_key==""){
		alert("waybill_key of is manadatory");
	}
}	
	
function validatePassDays(rec_id){
	var passday= $('#pass_day_' + rec_id).val();
	if(passday=="0"){ 
		alert("please select a Pass Day");
	}
}

function validatePassTypeList(rec_id){
	var passtype= $('#ticket_type_id_' + rec_id).val();
	if(passtype=="0"){ 
		alert("Please select a Pass Type");
	}
}
function clearAllPassDataRow(rec_id){
	 document.getElementById('no_of_passes_' + rec_id).value="";
	 document.getElementById('total_value_' + rec_id).value="";
	 document.getElementById('no_of_books_' + rec_id).value="";
	 document.getElementById('priority_' + rec_id).value="";
	 $('#end_no_' + rec_id).val("");
	 $('#start_no_' + rec_id).val("");
	 $('#denom_key_' + rec_id).val("");
	 $("#no_of_passes_"+rec_id).removeAttr("readonly");
	 $("#no_of_books_"+rec_id).removeAttr("readonly");
	 if($("#ticket_type_id_"+rec_id).val()=="2"){
		 $("#no_of_passes_"+rec_id).attr("readonly","readonly");
		
	 }else{
		 
		 $("#no_of_books_"+rec_id).attr("readonly","readonly");
	 }
}
function validatePassTypeDenomno(rec_id){
    var denom_no = $('#denom_no_' +rec_id ).val();
	if(denom_no==0){
		alert("please select a denomination");
	}
}
/***************************Validating Pass Key***********************************/
function validatePassKey(rec_id){
	var passtype= $('#ticket_type_id_' + rec_id).val();
	if(passtype=="0"){ 
		alert("Please select a Pass Type");
	}
	var denom_no = $('#denom_no_' +rec_id ).val();
	if(denom_no==0){
		alert("please select a denomination");
	}
	var denom_key = $('#denom_key_' + rec_id).val();
	if(denom_key==""){
		alert("pass type key of is manadatory");
	}
} 
				
function validateEndNoLuggage(rec_id,luggarrlength){
	
	if($('#end_no_'+luggarrlength).attr('readonly') == undefined){   
		var tickettype = $('#ticket_type_id_'+ rec_id).val();
		var lugg_key = $('#luggage_ticket_key_' + rec_id).val();
		document.getElementById('no_of_luggage_ticket_' + rec_id).value="";
		document.getElementById('no_of_books_' + rec_id).value="";
		document.getElementById('priority_' + rec_id).value="";
		var denom_key = $('#luggage_ticket_key_' + rec_id).val();
		if(denom_key==""){
			alert("Luggage ticket key of is manadatory");
		}
		var start_no = $('#start_no_' + rec_id).val();
		var end_no = $('#end_no_' + rec_id).val();
		if(end_no==""){
			alert("Luggage Type end no of is manadatory");
		}
	    var numbersOnly = /^\d+$/;   
		if(end_no!="" &&  !end_no.match(numbersOnly)){  
			alert('Luggage Type End Number should not be decimal'); 
		}  
		var endluggagelasttwo=end_no%100;
		if(!((endluggagelasttwo==50)||(endluggagelasttwo==00))){
			alert("Luggage Type last two digits of Closing Number should be 00 or 50");
			document.getElementById('end_no_' + rec_id).focus();
			setTimeout(function() {
				document.getElementById('end_no_' + rec_id).focus();
			}, 100); 
			return false;
		}
	    if((start_no!="")&&(end_no!="")){
			if(parseInt(end_no)>parseInt(start_no)){
				var end=padLeft(end_no,8);
				$('#end_no_' + rec_id).val(end);
				var denom_key = $('#luggage_ticket_key_' + rec_id).val();
				var alertData2=$.ajax({
					type: "post",
					dataType : 'json',
					global : false,
					async : false,
					url: '<%=request.getContextPath()%>/checkDuplicateLuggageAction?denom_key='+denom_key
						+ "&start_no="+ start_no
		                + "&end_no="+ end_no,
					success: function(count) {
						if(count!=0){ 
							alert("Luggage type already exist.");
		        		   	document.getElementById('no_of_books_' + rec_id).readOnly = "true";
						   	document.getElementById('priority_' + rec_id).readOnly = "true";
		        	   	}
	        	   		return count;
	          		}
				}).responseText;
				var arrlugg=[];
                var arraluggvalue1="";
                var luggflag=false;
		        if(luggarrlength>=1){
					for(var dupl=0;dupl<luggarrlength;dupl++){
						arraluggvalue1="";
				       	arraluggvalue1= $('#luggage_ticket_key_' + dupl).val()+""+ $('#start_no_' + dupl).val()+""+ $('#end_no_' + dupl).val();
				     	arrlugg.push(arraluggvalue1);
						if( $('#luggage_ticket_key_' + dupl).val()==lugg_key){
					    	 if((parseInt(start_no)>= parseInt($('#start_no_' + dupl).val()))&&(parseInt(start_no)<=parseInt($('#end_no_' + dupl).val()))){
					    		 luggflag=true;
					         }
					    	 if((parseInt(end_no)>= parseInt($('#start_no_' + dupl).val()))&&(parseInt(end_no)<=parseInt($('#end_no_' + dupl).val()))){
					    		 luggflag=true; 
					    	 }
					     }
					} 
		      	} 
			    if(luggflag==true){ 
					alert("Luggage type contains duplicate entries");
					document.getElementById('no_of_books_' + rec_id).readOnly = "true";
					document.getElementById('priority_' + rec_id).readOnly = "true";
					return false;
				}
				if((alertData2==0)&&(luggflag==false)){
					document.getElementById('no_of_books_' + rec_id).readOnly = false;
					document.getElementById('priority_' + rec_id).readOnly = false;
					var diff=(end_no-start_no)+1;
					var blockno = diff/50;
					var partblock = diff % 50;
					var noofbooks=0;
					noofbooks=blockno;
					document.getElementById('no_of_books_' + rec_id).value=noofbooks;
					document.getElementById('no_of_luggage_ticket_' + rec_id).value=diff;
					$.ajax({
						type: "post",
						url: '<%=request.getContextPath()%>/getLuggagePriority?denom_key='+denom_key,
						success: function(result) {
							for(var k=0;k<luggarrlength;k++){
								if($('#ticket_type_id_'+ k).val()=='L'){
									result++;
								}
							}
							document.getElementById('priority_' + rec_id).value=result;
						}
					});
				}
			} else{
				alert("Luggage Type End Number should be greater than start number");
				$('#end_no_' + rec_id).val("");
				document.getElementById('no_of_books_' + rec_id).focus();
			    setTimeout(function() {
					document.getElementById('start_no_' + rec_id).focus();
				 }, 100);
			} 
		}else{
			var luggendflag=validateLuggageKey(rec_id);
			if(luggendflag==true){
				 alert("Luggage Type start no of  is manadatory");
			}
	    }
	}  
}
function checkIfArrayIsUnique(myArray) 
   {
     isUnique=false;
      

       for (var i = 0; i < myArray.length; i++) 
       {
           for (var j = 0; j < myArray.length; j++) 
           {
               if (i != j) 
               {
                   if (myArray[i] == myArray[j]) 
                   {
                       isUnique=true;
                   }
               }
           }
       }
     
       return isUnique;
   }

function validateStartNoLuggage(rec_id,luggarrlength){ 
	
	if($('#start_no_'+luggarrlength).attr('readonly') == undefined){    
		var tickettype = $("#ticket_type_id_"+ rec_id).val();
		var lugg_key = $('#luggage_ticket_key_' + rec_id).val();
		document.getElementById('no_of_luggage_ticket_' + rec_id).value="";
		document.getElementById('no_of_books_' + rec_id).value="";
		document.getElementById('priority_' + rec_id).value="";
		var start_no = $('#start_no_' + rec_id).val();
		var end_no = $('#end_no_' + rec_id).val();
		var denom_key = $('#luggage_ticket_key_' + rec_id).val();
		var numbersOnly = /^\d+$/;
		if(denom_key==""){
			alert("Luggage ticket key of is manadatory");
		}
		if(start_no==""){
			alert("Luggage Type start no of  is manadatory");
		}
		
		if(start_no!="" && !start_no.match(numbersOnly)){  
			alert('Invalid end no');
		}
		var endluggagestarttwo=start_no%100;
		if(start_no!=""&& !((endluggagestarttwo==01)||(endluggagestarttwo==51))){
			alert("Luggage Type of  last two digits of Starting Number should be 01 or 51");
			document.getElementById('start_no_' + rec_id).focus();
			setTimeout(function() {
				document.getElementById('start_no_' + rec_id).focus();
			}, 100); 
			return false;
		}
		if(start_no!=""){
			$('#start_no_' + rec_id).val(padLeft(start_no,8));
			/* var end=padLeft(start_no,8).substring(0, 6);
			$('#end_no_' + rec_id).val(end); */
		}
		end_no=$('#end_no_' + rec_id).val();
		if((start_no!="")&&(end_no!="")){
			if(parseInt(end_no)>parseInt(start_no)){
				var denom_key = $('#luggage_ticket_key_' + rec_id).val();
				var alertData2=$.ajax({
					type: "post",
					dataType : 'json',
					global : false,
					async : false,
					url: '<%=request.getContextPath()%>/checkDuplicateLuggageAction?denom_key='+denom_key
					 		+ "&denom_key"+denom_key
					 		+ "&start_no="+ start_no
							+ "&end_no="+ end_no,
						success: function(count) {
							if(count!=0){
								alert("Luggage type already exist.");
								/*  document.getElementById('no_of_books_' + rec_id).focus();
								setTimeout(function() {
									document.getElementById('end_no_' + rec_id).focus();
								}, 100); */
								document.getElementById('no_of_books_' + rec_id).readOnly = "true";
								document.getElementById('no_of_luggage_ticket_' + rec_id).readOnly = "true";
								document.getElementById('priority_' + rec_id).readOnly = "true";
							}
							return count;
						}
					}).responseText;
				var arrlugg=[];
				var arraluggvalue1="";
				arluggflag=false;
				if(luggarrlength>=1){
					for(var dupl=0;dupl<luggarrlength;dupl++){
						arraluggvalue1="";
						// arraluggvalue1=""+ $('#luggage_ticket_key_' + idArrLugg[i]).val()+""+ $('#start_no_' + idArrLugg[i]).val()+""+$('#end_no_' + idArrLugg[i]).val();
						arraluggvalue1= $('#luggage_ticket_key_' + dupl).val()+""+ $('#start_no_' + dupl).val()+""+ $('#end_no_' + dupl).val();
						arrlugg.push(arraluggvalue1);
						if( $('#luggage_ticket_key_' + dupl).val()==lugg_key){
							if((parseInt(start_no)>= parseInt($('#start_no_' + dupl).val()))&&(parseInt(start_no)<=parseInt($('#end_no_' + dupl).val()))){
								luggflag=true;
							}
							if((parseInt(end_no)>= parseInt($('#start_no_' + dupl).val()))&&(parseInt(end_no)<=parseInt($('#end_no_' + dupl).val()))){
								luggflag=true;
							}
						}
					} 
				} 
				if(luggflag==true){
					alert("Luggage type already exist.");
					document.getElementById('no_of_books_' + rec_id).readOnly = "true";
					document.getElementById('no_of_luggage_ticket_' + rec_id).readOnly = "true";
					document.getElementById('priority_' + rec_id).readOnly = "true";
					return false;
				}
				if((alertData2==0)&&(luggflag==false)){
					document.getElementById('no_of_books_' + rec_id).readOnly = false;
					document.getElementById('no_of_luggage_ticket_' + rec_id).readOnly = false;
					ocument.getElementById('priority_' + rec_id).readOnly = false;
					var diff=(end_no-start_no)+1;
					var blockno = diff/50;
					var partblock = diff % 50;
					var noofbooks=0;
					noofbooks=blockno;
					document.getElementById('no_of_books_' + rec_id).value=noofbooks;
					document.getElementById('no_of_luggage_ticket_' + rec_id).value=diff;
					$.ajax({
						type: "post",
						url: '<%=request.getContextPath()%>/getLuggagePriority',
						success: function(result) {
							for(var k=0;k<luggarrlength;k++){
								if($('#luggage_ticket_key_' + k).val()==lugg_key){
									result++;
								}
							}
							document.getElementById('priority_' + rec_id).value=result;
						}
					});
				}
			}
	 	}
	 }
}

function validateStartno(rec_id){
	var start_no = $('#start_no_' + rec_id).val();
	var end_no = $('#end_no_' + rec_id).val();
	if(start_no=="") if(end_no==""){
		alert("Luggage Type end no of is manadatory");
	}
	var endluggagelasttwo=end_no%100;
	if(!((endluggagelasttwo==49)||(endluggagelasttwo==99))){
		alert("Luggage Type of  last two digits of Closing Number should be 49 or 99");
		setTimeout(function() {
			document.getElementById('denom_key_' + rec_id).focus();
		}, 100);
		return false;
	}
	if((start_no!="")&&(end_no!="")){
		if(parseInt(end_no)>parseInt(start_no)){
			var diff=(end_no-start_no)+1;
			var blockno = diff/50;
			var partblock = diff % 50;
			var noofbooks=0;
			noofbooks=blockno;
			document.getElementById('no_of_books_' + rec_id).value=noofbooks;
			document.getElementById('no_of_luggage_ticket_' + rec_id).value=diff;
		}else{
			alert("Luggage Type End Number should be greater than start number");
		}
	}
}
				
				/********************Validating  Start Number of Pass Entry**********************/
function validatePassStartno(rec_id,passarrlength){
	if($('#start_no_'+passarrlength).attr('readonly') == undefined){ 
		var start_no = $('#start_no_' + rec_id).val();
		document.getElementById('no_of_passes_' + rec_id).value="";
		document.getElementById('total_value_' + rec_id).value="";
		document.getElementById('no_of_books_' + rec_id).value="";
		document.getElementById('priority_' + rec_id).value="";
		
		var end_no = $('#end_no_' + rec_id).val();
		var denomid=$('#denom_no_'+rec_id+' option:selected').val();
		var passtype= $('#ticket_type_id_' + rec_id).val();
		if(start_no==""){
			alert("Pass Type start no of  is manadatory");
		}
		var numbersOnly = /^\d+$/;   
		if(start_no!="" && !start_no.match(numbersOnly)){   
			alert('Pass Type Start Number should not be decimal'); 
		}  
	   	var tickettype = $('#ticket_type_id_'+ rec_id).val();
	   	var passDay =  $("#pass_day_"+rec_id).val()
		var duplicateConditionForDailyPass ='';
			if(tickettype==2){
				var endpassstarttwo=start_no%100;
			    if(start_no!="" && !((endpassstarttwo==01)||(endpassstarttwo==51))){
					alert("Pass Type last two digits of the Start number should be 01 or 51");
					document.getElementById('total_value_' + rec_id).focus();
						setTimeout(function() {
							document.getElementById('start_no_' + rec_id).focus();
					}, 100);
					return false;
				}
				if((start_no!="")&&(end_no!="")){
					if(parseInt(end_no)>parseInt(start_no)){
					}else{
					 	 alert("Pass Type End Number should be greater than start number");
					} 
				}
				duplicateConditionForDailyPass =   "&passDay="+ passDay;
			}
			if(start_no!=""){
				$('#start_no_' + rec_id).val(padLeft(start_no,8));
				/* var end=padLeft(start_no,8).substring(0, 6);
				$('#end_no_' + rec_id).val(end); */
			}
			if((start_no!="")&&(end_no!="")){ 
				var tickettype = $('#ticket_type_id_'+ rec_id).val();
				var denomid=$('#denom_no_'+rec_id+' option:selected').val();
				var denom_key = $('#denom_key_' + rec_id).val();
				var alertData1=$.ajax({
				    type: "post",
				    dataType : 'json',
					global : false,
					async : false,
					url: '<%=request.getContextPath()%>/checkDuplicatePassAction?denomval='+denomid
		                 + "&end_no="+ end_no
		                 + "&denom_key="+denom_key
		                 + "&start_no="+ start_no
		                 + duplicateConditionForDailyPass,
					success: function(count) {
						if(count!=0){
							alert("Pass type already exists.");
							return false;
							if(tickettype=="2"){
								 $("#no_of_passes_"+rec_id).attr("readonly","readonly");
							}else{
								 $("#no_of_passes_"+rec_id).removeAttr("readonly");
							}
							//document.getElementById('total_value_' + rec_id).readOnly = "true";
							document.getElementById('no_of_books_' + rec_id).readOnly = "true";
							document.getElementById('priority_' + rec_id).readOnly = "true";
						}
						return count;
					}
				}).responseText;
				var arrpass=[];
		        var arravalue1="";
		        var passduplicateflag=false;
		        if(parseInt(passarrlength)>=1){
		        	for(var duplpass=0;duplpass<passarrlength;duplpass++){
	                	arravalue1="";
						arravalue1=$('#denom_no_' + duplpass).val()+""+  $('#denom_key_' + duplpass).val()+""+ $('#start_no_' + duplpass).val()+""+ $('#end_no_' + duplpass).val();
						if(tickettype=="2"){
			        		arravalue1 += $('#pass_day_' + duplpass).val();
			        	}
						if(tickettype=="2"){
						   	if(($('#denom_no_'+duplpass+' option:selected').val()==denomid)&&($('#denom_key_' + duplpass).val()==denom_key) && $('#pass_day_' + duplpass).val()==passDay){
					        	if((parseInt(start_no)>= parseInt($('#start_no_' + duplpass).val()))&&(parseInt(start_no)<=parseInt($('#end_no_' + duplpass).val()))){
					        		passduplicateflag=true;
					        		if(tickettype=="2"){
										 $("#no_of_passes_"+rec_id).attr("readonly","readonly");
									}else{
										 $("#no_of_passes_"+rec_id).removeAttr("readonly");
									}
							 		//document.getElementById('total_value_' + rec_id).readOnly = "true";
									document.getElementById('no_of_books_' + rec_id).readOnly = "true";
								 	document.getElementById('priority_' + rec_id).readOnly = "true";
								}
								if((parseInt(end_no)>= parseInt($('#start_no_' + duplpass).val()))&&(parseInt(end_no)<=parseInt($('#end_no_' + duplpass).val()))){							    		 
									passduplicateflag=true; 
									if(tickettype=="2"){
										 $("#no_of_passes_"+rec_id).attr("readonly","readonly");
									}else{
										 $("#no_of_passes_"+rec_id).removeAttr("readonly");
									}
									//document.getElementById('total_value_' + rec_id).readOnly = "true";
									document.getElementById('no_of_books_' + rec_id).readOnly = "true";
									document.getElementById('priority_' + rec_id).readOnly = "true";
								}
							}
						}
						else{
							if(($('#denom_no_'+duplpass+' option:selected').val()==denomid)&&($('#denom_key_' + duplpass).val()==denom_key)){
					        	if((parseInt(start_no)>= parseInt($('#start_no_' + duplpass).val()))&&(parseInt(start_no)<=parseInt($('#end_no_' + duplpass).val()))){
					        		passduplicateflag=true;
					        		if(tickettype=="2"){
										 $("#no_of_passes_"+rec_id).attr("readonly","readonly");
									}else{
										 $("#no_of_passes_"+rec_id).removeAttr("readonly");
									}
							 		//document.getElementById('total_value_' + rec_id).readOnly = "true";
									document.getElementById('no_of_books_' + rec_id).readOnly = "true";
								 	document.getElementById('priority_' + rec_id).readOnly = "true";
								}
								if((parseInt(end_no)>= parseInt($('#start_no_' + duplpass).val()))&&(parseInt(end_no)<=parseInt($('#end_no_' + duplpass).val()))){							    		 
									passduplicateflag=true; 
									if(tickettype=="2"){
										 $("#no_of_passes_"+rec_id).attr("readonly","readonly");
									}else{
										 $("#no_of_passes_"+rec_id).removeAttr("readonly");
									}
									//document.getElementById('total_value_' + rec_id).readOnly = "true";
									document.getElementById('no_of_books_' + rec_id).readOnly = "true";
									document.getElementById('priority_' + rec_id).readOnly = "true";
								}
							}
						}
			        arrpass.push(arravalue1);
				}
		  	} 
			if(passduplicateflag==true){
				alert("Pass type already exists.");
			}
		    if((alertData1==0)&&(passduplicateflag==false)){
		    	if(tickettype=="2"){
					 $("#no_of_passes_"+rec_id).attr("readonly","readonly");
				}else{
					 $("#no_of_passes_"+rec_id).removeAttr("readonly");
				}
				//document.getElementById('total_value_' + rec_id).readOnly = false;
				document.getElementById('no_of_books_' + rec_id).readOnly = false;
				document.getElementById('priority_' + rec_id).readOnly = false;
				if(tickettype==2){
					var endpassstarttwo=start_no%100;
					if(!((endpassstarttwo==01)||(endpassstarttwo==51))){
						alert("Pass Type last two digits of the Start number should be 01 or 51");
					}
					if(parseInt(end_no)>parseInt(start_no)){
						var diff=(end_no-start_no)+1;
						var blockno = diff/50;
						var partblock = diff % 50;
						var noofbooks=0;
						noofbooks=blockno;
						var conceptName=$('#denom_no_'+rec_id+' option:selected').text().toString();
					    var val=diff*parseInt(conceptName);
	
					    document.getElementById('no_of_passes_' + rec_id).value=diff;
						document.getElementById('total_value_' + rec_id).value=val;
						document.getElementById('no_of_books_' + rec_id).value=noofbooks;
						var passDay = $("#pass_day_"+rec_id).val();
						$.ajax({
					    	type: "post",
					        url: '<%=request.getContextPath()%>/getPassPriority?denomid='+denomid+'&denom_key='+ denom_key+'&passDay='+passDay,
					        success: function(result) {
					        	for(var j=0;j<passarrlength;j++){
				            	       //alert($('#denom_no_'+j+' option:selected').val()+"====="+denomid);
				            		if(($('#denom_no_'+j+' option:selected').val()==denomid)){
				            			result++;
		 		            		}
				                 }	
					             document.getElementById('priority_' + rec_id).value=result;
				            }
				        });
					 }	
				}
				else{ 
					if((parseInt(end_no)>=parseInt(start_no))){ 
						var denomval=$('#denom_no_'+rec_id+' option:selected').text().toString();
						var denom_no = $('#denomi_no_' + rec_id).val();
						var diff=(end_no-start_no)+1;
						if(denom_no!=0){
							var val=diff*parseInt(denomval);
						}
						document.getElementById('no_of_passes_' + rec_id).value=diff;
						document.getElementById('total_value_' + rec_id).value=val;
						document.getElementById('no_of_books_' + rec_id).disabled=true;
						$.ajax({
							type: "post",
							url: '<%=request.getContextPath()%>/getPassPriority?denomid='+denomid+'&denom_key='+ denom_key,
							success: function(result) {
							   	for(var j=0;j<passarrlength;j++){
				 		        	//alert($('#denom_no_'+j+' option:selected').val()+"====="+denomid);
						            if(($('#denom_no_'+j+' option:selected').val()==denomid)){
						            	result++;
				 		            }
						    	}	
							    document.getElementById('priority_' + rec_id).value=result;
							}
				        });
					} 
				}
			}
		}
	}
}
				

function validatePassEndno(rec_id,passarrlength){
	
		var start_no = $('#start_no_' + rec_id).val();
		document.getElementById('no_of_passes_' + rec_id).value="";
		document.getElementById('total_value_' + rec_id).value="";
		document.getElementById('no_of_books_' + rec_id).value="";
		document.getElementById('priority_' + rec_id).value="";
		var end_no = $('#end_no_' + rec_id).val();
		if(end_no==""){
			alert("Pass Type end no of is manadatory");
		}
		var numbersOnly = /^\d+$/;   
		if(end_no !="" && !end_no.match(numbersOnly)){   
			alert('Pass Type Start Number should not be decimal'); 
		}  
		var tickettype = $('#ticket_type_id_'+ rec_id).val();
		var passDay =  $("#pass_day_"+rec_id).val()
		var duplicateConditionForDailyPass ='';
		if(tickettype==2){
			var endpasslasttwo=end_no%100;
			if(!((endpasslasttwo==00)||(endpasslasttwo==50))){
			    alert("Pass Type last two digits of the End number should be 00 or 50");
			    document.getElementById('total_value_' + rec_id).focus();
			    setTimeout(function() {
				 	document.getElementById('end_no_' + rec_id).focus();
				}, 100);
				return false; 
	       	}
			if((start_no!="")&&(end_no!="")){
				if(parseInt(end_no)>parseInt(start_no)){
				}else{
					alert("Pass Type End Number should be greater than start number");
				} 
			}
			duplicateConditionForDailyPass =   "&passDay="+ passDay;
		}
		var denomid=$('#denom_no_'+rec_id+' option:selected').val();
		if((start_no!="")&&(end_no!="")){
			var tickettype = $('#ticket_type_id_'+ rec_id).val();
			var denomid=$('#denom_no_'+rec_id+' option:selected').val();
			var denom_key = $('#denom_key_' + rec_id).val();
			var alertData1=$.ajax({
					type: "post",
					dataType : 'json',
					global : false,
					async : false,
					url: '<%=request.getContextPath()%>/checkDuplicatePassAction?denomval='+denomid
					 	+ "&denom_key="+denom_key
		                + "&start_no="+ start_no
		                + duplicateConditionForDailyPass
		                + "&end_no="+ end_no,
					success: function(count) {
					if(count!=0){
						alert("Pass type already exists.");
						return  false;
						if(tickettype=="2"){
							 $("#no_of_passes_"+rec_id).attr("readonly","readonly");
						}else{
							 $("#no_of_passes_"+rec_id).removeAttr("readonly");
						}
						//document.getElementById('total_value_' + rec_id).readOnly = "true";
						document.getElementById('no_of_books_' + rec_id).readOnly = "true";
						document.getElementById('priority_' + rec_id).readOnly = "true";
					}
					return count;
				}
			}).responseText;
			var arrpass=[];
		    var arravalue1="";
		    var passduplicateflag=false;
		    if(parseInt(passarrlength)>=1){
		        for(var duplpass=0;duplpass<passarrlength;duplpass++){
		        	arravalue1="";
		        	arravalue1=$('#denom_no_' + duplpass).val()+""+ $('#denom_key_' + duplpass).val()+""+ $('#start_no_' + duplpass).val()+""+ $('#end_no_' + duplpass).val();
		        	if(tickettype==2){
		        		arravalue1 += $('#pass_day_' + duplpass).val();
		        	}
		        	if(tickettype==2){
			        	if(($('#denom_no_'+duplpass+' option:selected').val()==denomid)&& $('#pass_day_' + duplpass).val()==passDay){ /* &&($('#denom_key_' + duplpass).val()==denom_key)  */
			        		if((parseInt(start_no)>= parseInt($('#start_no_' + duplpass).val()))&&(parseInt(start_no)<=parseInt($('#end_no_' + duplpass).val()))&&($('#denom_key_' + duplpass).val()==denom_key)){
			        			passduplicateflag=true;
			        			if(tickettype=="2"){
									 $("#no_of_passes_"+rec_id).attr("readonly","readonly");
								}else{
									 $("#no_of_passes_"+rec_id).removeAttr("readonly");
								}
			        			//document.getElementById('total_value_' + rec_id).readOnly = "true";
			        			document.getElementById('no_of_books_' + rec_id).readOnly = "true";
			        			document.getElementById('priority_' + rec_id).readOnly = "true";
			        		}
			        		if((parseInt(end_no)>= parseInt($('#start_no_' + duplpass).val()))&&(parseInt(end_no)<=parseInt($('#end_no_' + duplpass).val()))&&($('#denom_key_' + duplpass).val()==denom_key)){
			        			passduplicateflag=true;
			        			if(tickettype=="2"){
									 $("#no_of_passes_"+rec_id).attr("readonly","readonly");
								}else{
									 $("#no_of_passes_"+rec_id).removeAttr("readonly");
								}
			        			//document.getElementById('total_value_' + rec_id).readOnly = "true";							  
			        			document.getElementById('no_of_books_' + rec_id).readOnly = "true";
			        			document.getElementById('priority_' + rec_id).readOnly = "true";
			        		}
			        	}
		        	}else{
		        		if(($('#denom_no_'+duplpass+' option:selected').val()==denomid)){/* &&($('#denom_key_' + duplpass).val()==denom_key) */
			        		if((parseInt(start_no)>= parseInt($('#start_no_' + duplpass).val()))&&(parseInt(start_no)<=parseInt($('#end_no_' + duplpass).val()))&&($('#denom_key_' + duplpass).val()==denom_key)){
			        			passduplicateflag=true;
			        			if(tickettype=="2"){
									 $("#no_of_passes_"+rec_id).attr("readonly","readonly");
								}else{
									 $("#no_of_passes_"+rec_id).removeAttr("readonly");
								}
			        			//document.getElementById('total_value_' + rec_id).readOnly = "true";
			        			document.getElementById('no_of_books_' + rec_id).readOnly = "true";
			        			document.getElementById('priority_' + rec_id).readOnly = "true";
			        		}
			        		if((parseInt(end_no)>= parseInt($('#start_no_' + duplpass).val()))&&(parseInt(end_no)<=parseInt($('#end_no_' + duplpass).val()))&&($('#denom_key_' + duplpass).val()==denom_key)){
			        			passduplicateflag=true;
			        			if(tickettype=="2"){
									 $("#no_of_passes_"+rec_id).attr("readonly","readonly");
								}else{
									 $("#no_of_passes_"+rec_id).removeAttr("readonly");
								}
			        			//document.getElementById('total_value_' + rec_id).readOnly = "true";							  
			        			document.getElementById('no_of_books_' + rec_id).readOnly = "true";
			        			document.getElementById('priority_' + rec_id).readOnly = "true";
			        		}
			        	}
		        	}	
					arrpass.push(arravalue1);
				}
			    if(passduplicateflag==true){
			    	alert("Pass type already exists");
			    	 return false;
			    }
			}
			if((alertData1==0)&&(passduplicateflag==false)){
				if(tickettype=="2"){
					 $("#no_of_passes_"+rec_id).attr("readonly","readonly");
				}else{
					 $("#no_of_passes_"+rec_id).removeAttr("readonly");
				}
				//document.getElementById('total_value_' + rec_id).readOnly = false;
				document.getElementById('no_of_books_' + rec_id).readOnly = false;
				document.getElementById('priority_' + rec_id).readOnly = false;
				if(tickettype==2){
					var endpasslasttwo=end_no%100;
					if(!((endpasslasttwo==00)||(endpasslasttwo==50))){
						alert("Pass Type last two digits of the End number should be 00 or 50");
						document.getElementById('total_value_' + rec_id).focus();
						setTimeout(function() {
							document.getElementById('end_no_' + rec_id).focus();
							}, 100);
						return false;  
					}
					if(parseInt(end_no)>parseInt(start_no)){
						var end=padLeft(end_no,8);
						$('#end_no_' + rec_id).val(end);
						var diff=(end_no-start_no)+1;
						var blockno = diff/50;
						var partblock = diff % 50;
						var noofbooks=0;
						noofbooks=blockno;
						var conceptName=$('#denom_no_'+rec_id+' option:selected').text().toString();
						var val=diff*parseInt(conceptName);
						document.getElementById('no_of_passes_' + rec_id).value=diff;
						document.getElementById('total_value_' + rec_id).value=val;
						document.getElementById('no_of_books_' + rec_id).value=noofbooks;
						var passDay = $("#pass_day_"+rec_id).val();
						$.ajax({
							type: "get",
							url: '<%=request.getContextPath()%>/getPassPriority?denomid='+denomid+'&passDay='+passDay,
							success: function(result) {
								for(var j=0;j<passarrlength;j++){
									if(($('#denom_no_'+j+' option:selected').val()==denomid)){ 
										result++;
									}
			                    }
				             	document.getElementById('priority_' + rec_id).value=result;
				            }
				        });
				    }	
				}else{
					if((parseInt(end_no)>=parseInt(start_no))){
						var end=padLeft(end_no,8);
						$('#end_no_' + rec_id).val(end);
						var denomval=$('#denom_no_'+rec_id+' option:selected').text().toString();						 
						var denom_no = $('#denomi_no_' + rec_id).val();
						var diff=(end_no-start_no)+1;
						if(denom_no!=0){
							var val=diff*parseInt(denomval);
						}
						document.getElementById('no_of_passes_' + rec_id).value=diff;
						document.getElementById('total_value_' + rec_id).value=val;
						document.getElementById('no_of_books_' + rec_id).disabled=true;
						$.ajax({
							type: "post",
							url: '<%=request.getContextPath()%>/getPassPriority?denomid='+denomid,
									success: function(result) {
										for(var j=0;j<passarrlength;j++){
					            	       	if(($('#denom_no_'+j+' option:selected').val()==denomid)){
					            	    	   result++;
					            	    	}
					                    }
						    		document.getElementById('priority_' + rec_id).value=result;
						    		}
						});
					}else{
						alert("Pass Type End Number should be greater or equal to  start number");
					}
					//checkIfArrayIsUnique()dfgdfg
				}
		 	}
		}
}	
				
/****function for padding left *************/
function padLeft(nr, n, str){
    return Array(n-String(nr).length+1).join(str||'0')+nr;
}
	 			

function isInteger(txb){
	txb.value = txb.value.replace(/[^0-9]/g, "");
} 
				
function calculateEndNo(val,counter,tickarrlength,type){                

	if(parseInt(val.value)<=0){
		alert("Enter Valid no.of books");
		resetValues(counter,tickarrlength,type);
		/* setTimeout(function() {
			if(type!="3"){
				document.getElementById('no_of_books_' + counter).focus();
			}else{
				document.getElementById('no_of_passes_' + counter).focus();
			}
		}, 100); */
		return false;  
		
	} 
	if(type==1){
		if(val.value!="" && parseInt(val.value)>0){
			var startval=$('#start_no_' + counter).val();
			var endval=0;
			endval=parseFloat(startval)+100*val.value-1;
			var end=padLeft(endval,8);
			$('#end_no_' + counter).val(end);
			validateEndTicketNo(counter,tickarrlength);
		}
	
	} 
	if(type==4){
		if(val.value!="" && parseInt(val.value)>0){
			var startval=$('#start_no_' + counter).val();
			var endval=0;
			endval=parseFloat(startval)+50*val.value-1;
			var end=padLeft(endval,8);
			$('#end_no_' + counter).val(end);
			validateEndNoLuggage(counter,tickarrlength);
		}
	}
	if(type==2 ){
		var noofpasses=$('#no_of_books_' + counter).val();
		if(noofpasses!="" && parseInt(noofpasses)>0){
			var startval=$('#start_no_' + counter).val();
			var endval=0;
			endval=parseFloat(startval)+50*val.value-1;
			var end=padLeft(endval,8);
			$('#end_no_' + counter).val(end);
			validatePassEndno(counter,tickarrlength);
		}
	}
	if(type==3 ){
		var noofpasses=$('#no_of_passes_' + counter).val();
		if(noofpasses!="" && parseInt(noofpasses)>0){
			var startval=$('#start_no_' + counter).val();
			var endval=0;
			endval=parseFloat(startval)+1*val.value-1;
			var end=padLeft(endval,8);
			if(startval!="")
			$('#end_no_' + counter).val(end);
			validatePassEndno(counter,tickarrlength);
		}
	}
}
function resetValues(counter,length,type){
	$("#end_no_"+counter).val("");
	if(type=="1"){
		$("#value_"+counter).val("");
	}else if(type=="2" || type=="3"){
		$("#total_value_"+counter).val("");
		$("#no_of_passes_"+counter).val("");
	}else if(type=="4"){
		$("#no_of_luggage_ticket_"+counter).val("");
	}
}

function validateStartNoTsheet(rec_id,tsheetarrlength){ 
	
	if($('#start_no_'+tsheetarrlength).attr('readonly') == undefined){    
		var tickettype = $("#ticket_type_id_"+ rec_id).val();
		var tsheet_key = $('#waybill_key' + rec_id).val();
		var start_no = $('#start_no_' + rec_id).val();
		var denom_key = $('#waybill_key' + rec_id).val();
		var numbersOnly = /^\d+$/;
		if(denom_key==""){
			alert("Waybill key of is manadatory");
		}
		if(start_no==""){
			alert("Trip Sheet Type start no of  is manadatory");
		}
		
		if(start_no!="" && !start_no.match(numbersOnly)){  
			alert('Invalid end no');
		}
		
		if(start_no!=""){
			$('#start_no_' + rec_id).val(padLeft(start_no,8));
			/* var end=padLeft(start_no,8).substring(0, 6);
			$('#end_no_' + rec_id).val(end); */
		}
		

	 }
}
function validateEndNoTsheet(rec_id,tsheetarrlength){
	
	if($('#end_no_'+tsheetarrlength).attr('readonly') == undefined){   
		var tickettype = $('#ticket_type_id_'+ rec_id).val();
		var tsheet_key = $('#waybill_key' + rec_id).val();
		
		document.getElementById('no_of_tsheets_' + rec_id).value="";
		var denom_key = $('#waybill_key' + rec_id).val();
		if(denom_key==""){
			alert("Waybill key is manadatory");
		}
		var start_no = $('#start_no_' + rec_id).val();
		var end_no = $('#end_no_' + rec_id).val();
		if(end_no==""){
			alert("Trip Sheet Type end no of is manadatory");
		}
	    var numbersOnly = /^\d+$/;   
		if(end_no!="" &&  !end_no.match(numbersOnly)){  
			alert('Trip Sheet Type End Number should not be decimal'); 
		}  
		
	    if((start_no!="")&&(end_no!="")){
	    	var diff=(end_no-start_no)+1;
	    	document.getElementById('no_of_tsheets_' + rec_id).value=diff;
			if(parseInt(end_no)>parseInt(start_no)){
				var end=padLeft(end_no,8);
				$('#end_no_' + rec_id).val(end);
				var denom_key = $('#waybill_key' + rec_id).val();
				var alertData3=$.ajax({
					type: "post",
					dataType : 'json',
					global : false,
					async : false,
					url: '<%=request.getContextPath()%>/checkDuplicateTsheetAction?denom_key='+denom_key
						+ "&start_no="+ start_no
		                + "&end_no="+ end_no,
					success: function(count) {
						if(count!=0){ 
							alert("Trip Sheet type already exist.");
		        		   	
		        	   	}
	        	   		return count;
	          		}
				}).responseText;
				var arrtsheet=[];
                var arratsheetvalue1="";
                var tsheetflag=false;
		        if(tsheetarrlength>=1){
					for(var dupl=0;dupl<tsheetarrlength;dupl++){
						arratsheetvalue1="";
						arratsheetvalue1= $('#waybill_key' + dupl).val()+""+ $('#start_no_' + dupl).val()+""+ $('#end_no_' + dupl).val();
						arrtsheet.push(arratsheetvalue1);
						if( $('#waybill_key' + dupl).val()==tsheet_key){
							 if((parseInt(start_no)>= parseInt($('#start_no_' + dupl).val()))&&(parseInt(start_no)<=parseInt($('#end_no_' + dupl).val()))){
					    		 tsheetflag=true;
					    	 }
					    	 if((parseInt(end_no)>= parseInt($('#start_no_' + dupl).val()))&&(parseInt(end_no)<=parseInt($('#end_no_' + dupl).val()))){
					    		tsheetflag=true; 
					    	 }
					     }
				    }
					
		      	} 
		         if(tsheetflag==true){ 
					alert("Trip Sheet type contains duplicate entries");
					tsheetflag=false;
					return false;
				}else{
					 tsheetflag=checkIfArrayIsUnique(arrtsheet);
					 if(tsheetflag==true){ 
							alert("Trip Sheet type contains duplicate entries");
							tsheetflag=false;
							return false;
						}
				}
				
			} 
			else{
				alert("Trip Sheet Type End Number should be greater to start number");
			}
		}
	}  
}	      
		      /*************************Validating End Number Of TIcket Entry*************************/

			   	
		
				</script>
	</form>
</body>