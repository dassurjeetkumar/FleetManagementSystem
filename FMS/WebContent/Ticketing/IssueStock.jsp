<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.trimax.its.model.User"%>
<!DOCTYPE html>
<html>
<head>
<script>
</script>
<style>
	.centerize {display: block; margin: 0 auto;	text-align: center;}
	h1.intro {color: red;font-size: 14px;}
	p.intro {color:red;font-size:14px;}
	p.success{color:green; size:2px;}
	.centerize { display: block;  margin: 0 auto; text-align: center;}
</style>
<script src="assets/global/plugins/jquery-1.11.0.min.js" type="text/javascript"></script>
<script>

	/* $().ready(function() {		
		var totalnoOBooksluggage=document.getElementById('totalnumberofbooksluggage').value;
		document.getElementById('totalvalue').innerHTML=totalnoOBooksluggage;
	}); */
	
	
			
			$(document).ready(function(){
			 	 
				var orgtypeid=document.getElementById("orgtypeid").value;
					if(orgtypeid!=3 ){
						$("#Depotdiv").hide();
						
					}
					if(orgtypeid==2){
				
					
						 var val=document.getElementById('orgchartnameid').value;
						 if(val!=0) {
				        $.ajax({
				            type: "post",
				            url: '<%=request.getContextPath()%>/getDepotNameForIssue?val=' + val,
							success : function(result) {
								document.getElementById('depotlistdiv').innerHTML = result;
							}
						});
					}
					}
			 });
			
			
			function getDepot(orgId){
				//alert('Here');
				 /* var selectedValue = $('#form-control').val(); */
				 var val=document.getElementById('divisionlist').value;
				 document.getElementById("select2-chosen-4").innerHTML = "Select";
					$("#depotlist").val('0');
					 if(val!=0) {
			        $.ajax({
			            type: "post",
			            url: '<%=request.getContextPath()%>/getDepotNameForIssue?val=' + val,
						success : function(result) {
							document.getElementById('depotlist').innerHTML = result;
						}
					});
				}

			}
			
	function processing(){
		$("#saveButton").attr('disabled','disabled');
		$("#loading").html("<img src='<%=request.getContextPath()%>/inventory/loading-icon.gif' height='50px' width='70px'/>Issueing records....please wait!!!");
		
	}
	function saveIssue(){
		var orgchartid = "";
		var orgname = $("#orgname").val();//session orgname
		var stockDate = $("#stockDate").val();
	    var cendivision = $("#divisionlist").val();//centraldivisionlist
		var valcen =$("#depotlist").val();  //central depotlist               
		var valdiv =$("#depotlistdiv").val();//division depotlist		
		var valdep =$("#depotnameid").val();// Depot name 
		var orgtypeid = $("#orgtypeid").val();// organization type
		var sessorgchat = $("#orgchartnameid").val();// Organization chart hidden
		var valcenname = $("#depotlist option:selected").text();// central depotlist
		var valcentdivision = $("#divisionlist option:selected").text(); //central division name
		var valdivname = $("#depotlistdiv option:selected").text(); // Division depotlist name
	    var valdepname = $("#depotnameid option:selected").text(); //Depot level depot name
		if(orgtypeid==1|| orgtypeid==11){
			if(valcen==0 && cendivision==0){
		 		orgchartid=sessorgchat;
		 		$("#unitNameId").val(orgchartid);
			        bootbox.alert("Please Select Division or Depot");
			        return false;
			} 
			if(cendivision!=0 && valcen==0 ){
				orgchartid=cendivision;
				$("#unitNameId").val(orgchartid);
				processing();
				return true;
			} 
			if(valcen!=0 &&  cendivision!=0){
				if( valcen!='all'){
					orgchartid=valcen;
					$("#unitNameId").val(orgchartid);
					processing();
					return true;
				}else{
					orgchartid=valcen+cendivision+"@De";
				 	$("#headername").html(valcentdivision+"-"+valcenname);
				 	return false;
				}
		 	}
	 	}
	    if(orgtypeid==2){
	 		if(valdiv==0){
		 		orgchartid=sessorgchat+"@N";
				bootbox.alert("Please Select  Depot");
				return false;
			}else{
			 	orgchartid=valdiv+"@Y";
			 	if( valdiv!='all'){
			   		orgchartid=valdiv;
					$("#unitNameId").val(orgchartid);
					processing();
					return true;
				}else{
					orgchartid=valdiv+sessorgchat+"@Y";
					return false;
				}
			 }
		 }
		 if(orgtypeid==3){
			 orgchartid=valdep;
		 }
			/* document.forms[0].action ="saveIssueStock.action";
			document.forms[0].submit(); */
	}
	function goView() {
		document.forms[0].action = 'ticketinv.action';
		document.forms[0].submit();
	}
	function issueStock() {
		var chkArray = [];
	
		var unitTypeId = $("#unittypeid").val();
		var unitId = $("#unitnameid").val();
		if(unitTypeId!=0){
			if(unitId!=0 && unitId!=undefined){
				//document.forms[0].action = "addStock.action?tick="+selected+"&unitname="+strUser;
				document.forms[0].submit();
			}else{
				bootbox.alert("Please Select Unit Type");
				return false;
			}
		}else{
			bootbox.alert("Please Select Unit Name");
			return false;
		}
		
	}
	
	function getOrgType(){
 		var len= document.getElementById('unittypeid').options.length;
 		$('#select2-chosen-1').html("Select");
 		$('#select2-chosen-2').html("Select");
		if(len<=1 ) {
       		$.ajax({
        		type: "get",
           		url: '<%=request.getContextPath()%>/findSpecificOrgTypeAction',
           		success: function(result) {
	           		document.getElementById('unittypeid').innerHTML=result;
    	   		}
    		});
	 	}
	}
	function getUnitNames(){
		var e = document.getElementById("unittypeid");
		var strUser = e.options[e.selectedIndex].value;
		$('#select2-chosen-2').html("Select");
	       $.ajax({
	           type: "post",
	           url: '<%=request.getContextPath()%>/findUnitNameAction?orgid='+strUser,
	           success: function(result) {
	               document.getElementById('unitnameid').innerHTML=result;
	           }
	       });
	}
	
</script>
<script type="text/javascript" src="//www.google.com/jsapi">
</script>

</head>
<body onload="getOrgType()">
 <form action="saveIssueStock" class="form-horizontal"	method="post" onsubmit="return saveIssue()">
	<div class="page-content-wrapper">
			<div class="page-content">
				<div class="row">
					<div class="col-md-12">
						<h3 class="page-title">
							TICKET INVENTORY : <b id="headername"><s:property value="orgName"/> </b>
						</h3>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="portlet box grey-cascade">
							<div class="portlet box grey-cascade" id="floordiv">
								<div class="portlet-title">
									<div class="caption">
										<i class="fa fa-edit"></i>Issue Stock 
									</div>
								</div>
	                         	<div class="portlet-body" id="step1Content">
	                          		<b><font color="red" size="3px"><s:property value="msg" /></font></b>
	                          		<b><font color="red"> <s:actionerror/></font></b>
	                          		
										<div class="form-body">
					
							<%!String orgchart=null,orgtype=null;  %>
							<% orgtype=(String)session.getAttribute("orgtypeid");
								orgchart=(String)session.getAttribute("orgchartid");
									session.removeAttribute("Depotid");
					   				
					          %>
					    <input type="hidden" name="orgname" id="orgname" value="<s:property value="orgName"/>"> 
					    <input type="hidden" name="orgtype" id="orgtypeid" value="<%=orgtype%>"> 
					     <input type="hidden" name="unitType" id="unitType" value="<%=orgtype%>"> 
					    <input type="hidden" name="unitName" id="unitNameId" value=''> 
					    <input type="hidden" name="orgtchartname" id="orgchartnameid" value="<%=orgchart%>"> 
							<%if(orgtype.trim().equals("1")|| orgtype.trim().equals("11")) {%>
						        	 <div class="form-group">
										<label class="col-md-3 control-label">Central/Store  <font	color="red"></font></label>
											<div class="col-md-3">
											<select name="Division" class="select2_category form-control" disabled="disabled">
												<option value="0">Central/Store</option>
											</select>
											<!-- 	<input type="text"  name="Division" value='Central/Store'/> -->
											</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Division<font	color="red"></font></label>
										<div class="col-md-3">
											<s:select list="divisionlist" id="divisionlist"
												name="org_chart_id"
													cssClass="select2_category form-control" headerKey="0"
												headerValue="Select" onchange="getDepot(this.value)"></s:select>
										</div>
									</div>
									<div class="form-group " >
										<label class="col-md-3 control-label">Depot<font color="red"></font></label>
										<div class="col-md-3">
											<select id="depotlist" class="select2_category form-control">
										 	<option value="0">Select</option> 
											</select>
										</div>
									</div>
								<%} %>
								<%if(orgtype.trim().equals("2")) {%>
									<div class="form-group">
										<label class="col-md-3 control-label">Division<font color="red"></font></label>
										<div class="col-md-3">
										<select name="Division" class="select2_category form-control" disabled="disabled">
												<option value="0"><s:property value='orgName'/></option>
											</select>
											<%-- <input type="text"  name="Division" value="<s:property value='orgName'/>" > --%>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Depot<font color="red">*</font></label>
										<div class="col-md-3">
											<select id="depotlistdiv" class="select2_category form-control">
												<option value="0">Select</option>
											</select>
										</div>
									</div>
								<%} %>
								<%if(orgtype.trim().equals("3")) {%>
									<div class="form-group" id="Depotdiv">
										<label class="col-md-3 control-label"  >Depot<font 	color="red"></font></label>
										<div class="col-md-3">
										<select name="depot" class="select2_category form-control" disabled="disabled">
												<option value="0"><s:property value='orgName'/></option>
											</select>
											<%-- <input type="text"  name="depot" value="<s:property value='orgName'/>" > --%>
											<input type="hidden" name="depotname" id="depotnameid" value="<%=orgchart%>"> 	
											</div>
										</div>
								<%} %>
										<div class="portlet-title">
	                       					<div class="caption" style="font-size:16px">
								 				<b>Ticket Type</b><br/><br/>
											</div>
										</div>
										<input type="hidden" name="requestType" id="requestType" value="text" />
										<table class="table table-striped table-bordered table-hover" id="issueticket">
										<thead>
											<tr>
												<th>Denom</th>
												<th>Denom key</th>
												<th>Start number</th>
												<th>End number</th>
		                                        <th>No.of books present</th>
		                                        <th style="width1: 20px;">No.of books to issue</th>
		                                        <th>Total value</th>
												<th>Priority</th>
											</tr>
										</thead>
									</table>
									<table style="width:45%">
										<tr>
											<td><label >Total number of books : &nbsp;&nbsp; </label><span id='normalTotalBooks'></span>&nbsp;&nbsp;&nbsp;</td>
											<td><label >Total value : &nbsp;&nbsp; </label> <span id='normalTotalValue'></span></td>
										</tr>
									</table>
		                           	<br><br>
								</div>
								<hr/>
								<div class="portlet-title">
		                       		<div class="caption" style="font-size:16px">
									 	<b>Pass Type</b><br/><br/>
									</div>
								</div>
								<input type="hidden" name="requestType" id="requestType" value="text" />
								<table class="table table-striped table-bordered table-hover" id="issuepass" style="width:auto;min-width:1000px">
									<thead>
										<tr>
											<th width="130px">Type of Pass</th>
											<th>Denom</th>
											<th>Denom key</th>
											<th>Pass day/month</th>
											<th>Start number</th>
											<th>End number</th>
											<th>No.of passes</th> 
		                                    <th>No.of books present</th>
		                                    <th style="width1: 20px;">No.of books/passes to issue</th>
											<th>Total value</th>
											<th>Priority</th>
										</tr>
									</thead>
								</table>
		                       	<table >
									<tr>
										<td><label>Total number of books :&nbsp;&nbsp; </label><span id='passTotalBooks'></span>&nbsp;&nbsp;&nbsp;</td>
										<td><label>Total value : &nbsp;&nbsp; </label> <span id='passTotalValue'></span></td>
									</tr>
								</table>
							</div>
							<div class="portlet-body" id="step1Content">
                           		<div class="table-toolbar">
				                   <div class="portlet-title">
                       					<div class="caption" style="font-size:16px">
							 				<b>Luggage Type</b><br/><br/>
										</div>
									</div>		
								</div>
								<input type="hidden" name="requestType" id="requestType" value="text" />
								<table class="table table-striped table-bordered table-hover" id="issueluggage">
									<thead>
										<tr>
											<th>Ticket key</th>
											<th>Start number</th>
											<th>End number</th>
											<th>No.of tickets</th>
	                                        <th>No.of books present</th>
	                                        <th style="width1: 20px;">No.of books to issue</th>
											<th>Total value</th>
											<th>Priority</th>
										</tr>
									</thead>
								</table>
	                            <table >
									<tr>
										<td><label >Total number of books : &nbsp;&nbsp;</label><span id='luggageTotalBooks'></span></td>
										<td></td>
									</tr>
								</table>    
								<span id="totalvalue"></span>
							</div>
							
							<div class="portlet-body" id="step1Content">
                           		<div class="table-toolbar">
				                   <div class="portlet-title">
                       					<div class="caption" style="font-size:16px">
							 				<b>Trip Sheet Type</b><br/><br/>
										</div>
									</div>		
								</div>
								<input type="hidden" name="requestType" id="requestType" value="text" />
								<table class="table table-striped table-bordered table-hover" id="issuetsheet">
									<thead>
										<tr>
											<th>Waybill key</th>
											<th>Start number</th>
											<th>End number</th>
											<th>No.of Trip Sheets</th>
	                                        <th style="width1: 20px;">No.of Trip Sheets to issue</th>
										</tr>
									</thead>
								</table>
	                        </div>
							
						</div>
					</div>
				</div>
				 <div class="form-actions fluid">
					<div class="col-md-offset-5 col-md-9">
						<br>
							&nbsp;&nbsp;<input type="submit" class="btn blue"  value='Save' id="saveButton"> 
							&nbsp;&nbsp;<button type="button" id="cancel" class="btn default" onclick="goView()">Cancel</button>
							<label id='loading'></label>
					</div>
				</div>
			</div>
		</div>
	</div>
</form>
</body>
<script type="text/javascript">
function showAllDenomTypes(){
	var len= document.getElementById('denomination').options.length;
	if(len<=1 ) {
    	$.ajax({
           type: "post",
           url: '<%=request.getContextPath()%>/findAllDenomAction',
           success: function(result) {
               document.getElementById('denomination').innerHTML=result;
           }
     	});
	}
}
function validateREquiredAmount(val){
	var noOBooks=document.getElementById('booksCount'+val).value;
	var reqBooks = document.getElementById('requiredBooks'+val).value;
	if(reqBooks=="" || Number(reqBooks)=='0'){
		alert("Please provide No. of books to be issued");
	 	document.getElementById('ticktypepriority'+val).focus();
		setTimeout(function() {
			document.getElementById('requiredamount'+val).focus();
	    }, 100);
		return false;
	}
	var numbersOnly = /^\d+$/;   
	if(!reqBooks.match(numbersOnly)){   
		alert('No. of books to be issued should not be decimal'); 
		document.getElementById('ticktypepriority'+val).focus();
		setTimeout(function() {
			document.getElementById('requiredamount'+val).focus();
	    }, 100);
		return false;  
	}
	if(reqBooks<0){
		alert("No. of books to be issued should be a positive value");
		document.getElementById('ticktypepriority'+val).focus();
		setTimeout(function() {
			document.getElementById('requiredamount'+val).focus();
	    }, 100);
		return false;
	}

	if(Number(reqBooks)>Number(noOBooks)){
		alert("No. of books count to be issued should be lesser the available no. of books");
		document.getElementById('ticktypepriority'+val).focus();
		setTimeout(function() {
			document.getElementById('requiredamount'+val).focus();
	    }, 100);
		return false;
	}
}
function validateREquiredPassAmount(val){
	
	var noOBookspass=document.getElementById('booksCount'+val).value;
	var reqBookspass = document.getElementById('requiredBooks'+val).value;
	var noOfPasses = document.getElementById('passCount'+val).value; 
	var typeOfPass = document.getElementById('ticketType'+val).value;
	if(reqBookspass=="" || Number(reqBookspass)=="0")
	{
		alert("Please provide No. of books to be issued");
		document.getElementById('priority'+val).focus();
		setTimeout(function() {
			document.getElementById('requiredpassamount'+val).focus();
	    }, 100);
		return false;
	}
	var numbersOnly = /^\d+$/;   
	if(!reqBookspass.match(numbersOnly)){   
		alert('No. of books to be issued should not be decimal'); 
		document.getElementById('priority'+val).focus();
		setTimeout(function() {
			document.getElementById('requiredpassamount'+val).focus();
	    }, 100);
		return false;  
	}
	if(reqBookspass<0){
		alert("No. of books to be issued should be a positive value");
		document.getElementById('priority'+val).focus();
		setTimeout(function() {
			document.getElementById('requiredpassamount'+val).focus();
	    }, 100);
		return false;
	}
	if(Number(reqBookspass)>Number(noOBookspass) && typeOfPass==2){
		alert("No.of books count to be issued should be lesser than the available no.of books");
		document.getElementById('priority'+val).focus();
		setTimeout(function() {
			document.getElementById('requiredpassamount'+val).focus();
	    }, 100);
		return false;
	}
	if(Number(reqBookspass)>Number(noOfPasses) && typeOfPass==3){
		alert("No.of passes count to be issued should be lesser than the available no.of books");
		document.getElementById('priority'+val).focus();
		setTimeout(function() {
			document.getElementById('requiredpassamount'+val).focus();
	    }, 100);
		return false;
	}
}
function validateREquiredAmountLuggage(val){
	var noOBooksluggage=document.getElementById('booksCount'+val).value;
	var reqBooksluggage = document.getElementById('requiredBooks'+val).value;
	if(reqBooksluggage=="" || Number(reqBooksluggage)=="0"){
		alert("Please provide No. of books to be issued");
		document.getElementById('luggagepriority'+val).focus();
		setTimeout(function() {
			document.getElementById('requiredluggageamount'+val).focus();
	    }, 100);
		return false;
	}
	var numbersOnly = /^\d+$/;   
	if(!reqBooksluggage.match(numbersOnly)){   
		alert('No. of books to be issued should not be decimal'); 
		document.getElementById('luggagepriority'+val).focus();
		setTimeout(function() {
			document.getElementById('requiredluggageamount'+val).focus();
	    }, 100);
		return false;  
	}
	if(Number(reqBooksluggage)<0){
		alert("No. of books to be issued should be a positive value");
		document.getElementById('luggagepriority'+val).focus();
		setTimeout(function() {
			document.getElementById('requiredluggageamount'+val).focus();
	    }, 100);
		return false;
	}
	if(Number(reqBooksluggage)>Number(noOBooksluggage)){
		alert("No. of books count to be issued should be lesser the available no. of books.");
		document.getElementById('luggagepriority'+val).focus();
		setTimeout(function() {
			document.getElementById('requiredluggageamount'+val).focus();
	    }, 100);
		return false;
	}
}
function validateREquiredAmountTSheet(val){
	var noOBookstsheet=document.getElementById('booksCount'+val).value;
	var reqBookstsheet = document.getElementById('requiredBooks'+val).value;
	if(reqBookstsheet=="" || Number(reqBookstsheet)=="0"){
		alert("Please provide No. of Trip Sheets to be issued");
		
		
		return false;
	}
	var numbersOnly = /^\d+$/;   
	if(!reqBookstsheet.match(numbersOnly)){   
		alert('No. of Trip Sheets to be issued should not be decimal'); 
		
		return false;  
	}
	if(Number(reqBookstsheet)<0){
		alert("No. of Trip Sheet to be issued should be a positive value");
		
		return false;
	}
	if(Number(reqBookstsheet)>Number(noOBookstsheet)){
		alert("No. of Trip Shets count to be issued should be lesser the available no. of Trip Sheets.");
		
		return false;
	}
}
function getNormalTotal(){
	var bookvalues = [];
	var ticketValues=[];
	var ticketNumberBooks=0;
	var ticketValue=0;
	$(".normalBooks").each(function(index, value) { 
		bookvalues.push($(this).text());
		
	});
	$(".normalValue").each(function(index, value) { 
		ticketValues.push($(this).text());
	});
  	for(var i = 0; i < bookvalues.length; i++) {
		if (!isNaN((bookvalues[i]))) {
			ticketNumberBooks += Number(bookvalues[i]);
		}
	}
	for(var k = 0; k < ticketValues.length; k++) {
		if (!isNaN((ticketValues[k]))) {
			ticketValue += Number(ticketValues[k]);
		}
	}
	$("#normalTotalBooks").html(ticketNumberBooks);
	$("#normalTotalValue").html(ticketValue);
}
function getPassTotal(){
	
	var bookvalues = [];
	var ticketValues=[];
	var ticketNumberBooks=0;
	var ticketValue=0;
	$(".passBooks").each(function(index, value) { 
		bookvalues.push($(this).text());
		
	});
	$(".passValue").each(function(index, value) { 
		ticketValues.push($(this).text());
	});
  	for(var i = 0; i < bookvalues.length; i++) {
		if (!isNaN((bookvalues[i]))) {
			ticketNumberBooks += Number(bookvalues[i]);
		}
	}
	for(var k = 0; k < ticketValues.length; k++) {
		if (!isNaN((ticketValues[k]))) {
			ticketValue += Number(ticketValues[k]);
		}
	}
	$("#passTotalBooks").html(ticketNumberBooks);
	$("#passTotalValue").html(ticketValue);
}
function getLuggageTotal(){
	
	var bookvalues = [];
	var ticketNumberBooks=0;
	$(".luggageBooks").each(function(index, value) { 
		bookvalues.push($(this).text());
		
	});
  	for(var i = 0; i < bookvalues.length; i++) {
		if (!isNaN((bookvalues[i]))) {
			ticketNumberBooks += Number(bookvalues[i]);
		}
	}
	$("#luggageTotalBooks").html(ticketNumberBooks);
}
</script>
</html>
