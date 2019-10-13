<!--
	author : Satya
	date : 24/12/2014 
	purpose : View Stock
 -->
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Cache-control" content="no-cache">
	<style type="text/css">
	 table,table tr th,table tr td {
		border:1px solid #dddddd;
	}
	#table-responsive{
		color:#dddddd;
	}
	#table table-hover{
		border: 1px solid #dddddd;width:99%
	} 
	
	  table#normalTicketTable td,thead,tr,th
{
   
 	/* border:1;
 	cellspacing=1; */
 	border:1px solid #dddddd;
 
} 
	#scrollable-div{
		 max-height:200px;
		 overflow-y: scroll;
	}
	</style>
	 <script src="assets/global/plugins/jquery-1.11.0.min.js"
	type="text/javascript"></script>
	<script>
	
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
		            url: '<%=request.getContextPath()%>/getDepotNameForStock?val=' + val,
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
	            url: '<%=request.getContextPath()%>/getDepotNameForStock?val=' + val,
				success : function(result) {
					document.getElementById('depotlist').innerHTML = result;
					 document.getElementById("select2-chosen-4").innerHTML = "Select";
				}
			});
		}

	}
		function getTickets(){
			var orgchartid="";
			
			var orgname=$("#orgname").val();//Organization name 
			var stockDate = $("#stockDate").val();
		    var  cendivision=$("#divisionlist").val();//Central Division List
			var valcen=$("#depotlist").val(); //Central Depot List                
			var valdiv=$("#depotlistdiv").val();//Division Depot List		
			var valdep=$("#depotnameid").val();//Depot Level Id
			var orgtypeid=$("#orgtypeid").val();//Org Type From Session
			
			var sessorgchat=$("#orgchartnameid").val();//Org Chart From Session
			var valcenname=$("#depotlist option:selected").text();//Central Selected Depot Name
			var valcentdivision=$("#divisionlist option:selected").text();//Central Selected Division List;
			var valdivname=$("#depotlistdiv option:selected").text();//Division selected Depot List
		    var valdepname=$("#depotnameid option:selected").text();//Depot Name
		    
			 if(orgtypeid==1|| orgtypeid==11){
				//alert("valcen"+valcen+"cendive"+cendivision);
			
				  if(valcen==0 && cendivision==0){
						// alert("HI");headername
						 orgchartid=sessorgchat+"@C";
						// alert("de"+valcenname);
						 $("#headername").html(orgname);
						 $("#organization").html("Ticket Inventory :"+orgname);
						
						// alert("both"+orgchartid);
				        //bootbox.alert("Please Select Depot");
					 } 
				  if(cendivision!=0 && valcen==0 ){
						 orgchartid=cendivision+"@D";
						// alert("de"+valcenname);
					    // alert("div"+orgchartid)
						 $("#headername").html(valcentdivision);
						 $("#organization").html("<h4> Ticket Inventory :"+valcentdivision+"</h4>");
					} 
				  if(valcen!=0 &&  cendivision!=0){
						if( valcen!='all'){
							orgchartid=valcen+"@De";
					 		//alert("dep"+orgchartid); 
					 		$("#headername").html(valcenname);
					 		 $("#organization").html("<h4> Ticket Inventory :"+valcenname+"</h4>");
						}else{
							orgchartid=valcen+cendivision+"@De";
						 //	alert('devisiion'+orgchartid);
						 	$("#headername").html(valcentdivision+"-"+valcenname+" Depot");
						    $("#organization").html("<h4>Ticket Inventory :"+valcentdivision+"-"+valcenname+" Depot </h4>");
						
					 }
				 
				 
			 }
			 }
			 if(orgtypeid==2){
					 //alert("Valldiv"+valdiv);
					 if(valdiv==0){
						 orgchartid=sessorgchat+"@N";
					 }else{
						 
						 orgchartid=valdiv+"@Y";
						 
						// alert("valdivsd"+orgchartid);
					 if( valdiv!='all'){
						 orgchartid=valdiv+"@Y";
						// alert("dep"+orgchartid);
						     $("#headername").html(valdivname);
						    $("#organization").html("<h4>Ticket Inventory :"+valdivname+"</h4>");
					}else{
						    $("#headername").html(orgname+"-"+valdivname+" Depot");
						    $("#organization").html("<h4>Ticket Inventory :"+orgname+"-"+valdivname+" Depot</h4>");
					 
					}
						
				 }
				 
				
			 }
			
			 if(orgtypeid==3){
				 orgchartid=valdep;
			 }
			
			if(stockDate==""){
				bootbox.alert("Please Select Stock Date");
				
	
			}else{
				//alert("BYer");
				//$("#normalTicketTable tr").remove();
			var stockDateArray=stockDate.split("-");
            var headerDate=stockDateArray[0]+"/"+stockDateArray[1]+"/"+stockDateArray[2];
            $("#headerDate").html("<h5><b >Date: </b>&nbsp;<b>"+headerDate+"</b></h5>");
				var first = $('#normalTicketTable tbody tr').eq(0); // Take the first row
				$('#normalTicketTable tbody tr').remove(); //remove all rows
				$('#normalTicketTable tbody').html(first);
				
				var first = $('#passtTicketTable tbody tr').eq(0); // Take the first row
				$('#passtTicketTable tbody tr').remove(); //remove all rows
				$('#passtTicketTable tbody').html(first);
				
				var first = $('#luggageTicketTable tbody tr').eq(0); // Take the first row
				$('#luggageTicketTable tbody tr').remove(); //remove all rows
				$('#luggageTicketTable tbody').html(first);
				
				$("#noDataForNormal").remove();
				$("#noDataForPass").remove();
				$("#noDataForLuggage").remove();
				
				$.ajax({
					type:'get',
					data: {
						stockDate:stockDate,
						orgchartid:orgchartid
				    },
				    async:false,
			    	url:  "<s:url action='GetTicketStockByDate'/>",
			    	success: function(result){
			    	//alert(result);
			    	var ticketType = result.split("#");
			    	var passengerTicketArray = ticketType[0].split("@");
			    	var passengerData = JSON.parse(passengerTicketArray[0]);
			   
			    	if(passengerData.length>0){
			    		for ( var i = 0; i < passengerData.length; i++) {
			    			$("#normalTicketTable").append("<tr>");
			    			$("#normalTicketTable tr:last").append("<td style='text-align:right' >"+(i+1)+"</td>");
			    
			    			$("#normalTicketTable tr:last").append("<td  style='text-align:right'>"+passengerData[i].denominationValue+"</td>");
			    			$("#normalTicketTable tr:last").append("<td  style='text-align:left'>"+passengerData[i].keyNumber+"</td>");
			    			$("#normalTicketTable tr:last").append("<td  style='text-align:right'>"+passengerData[i].openingNumber+"</td>");
			    			$("#normalTicketTable tr:last").append("<td  style='text-align:right'>"+passengerData[i].closingNumber+"</td>");
			    			$("#normalTicketTable tr:last").append("<td  style='text-align:right'>"+passengerData[i].noOfBooks+"</td>");
			    			$("#normalTicketTable tr:last").append("<td  style='text-align:right'>"+passengerData[i].value+"</td>");
			    			$("#normalTicketTable tr:last").append("<td  style='text-align:left'>"+passengerData[i].currentStatus+"</td>");
			    			$("#normalTicketTable tr:last").append("</tr>");
			    			}	
			    		$("#normalTicketTable").append("<tr>");
			    		$("#normalTicketTable tr:last").append("<td >Total</td>");
			    		$("#normalTicketTable tr:last").append("<td colspan='5'></td>");
			    		$("#normalTicketTable tr:last").append("<td  style='text-align:right'>"+passengerTicketArray[1]+"</td>");
			    		$("#normalTicketTable tr:last").append("<td ></td>");
			    		$("#normalTicketTable tr:last").append("</tr>");
			    	}else{
			    		$("#normalTicketTable").append("<tr>");
			    		$("#normalTicketTable tr:last").append("<td  align='center' colspan='9'>No Data Found</td></tr>");
			    	}
			  
			    	var passTicketArray = ticketType[1].split("@");
			    	var passData = JSON.parse(passTicketArray[0]);
			    	if(passData.length>0){
			    		for ( var i = 0; i < passData.length; i++) {
			    			$("#passtTicketTable").append("<tr>");
			    			$("#passtTicketTable tr:last").append("<td style='text-align:right' >"+(i+1)+"</td>");
			    			$("#passtTicketTable tr:last").append("<td  style='text-align:right'>"+passData[i].denominationValue+"</td>");
			    			$("#passtTicketTable tr:last").append("<td  style='text-align:left'>"+passData[i].keyNumber+"</td>");
			    			$("#passtTicketTable tr:last").append("<td  style='text-align:left'>"+passData[i].passType+"</td>");
			    			$("#passtTicketTable tr:last").append("<td  style='text-align:right'>"+passData[i].openingNumber+"</td>");
			    			$("#passtTicketTable tr:last").append("<td style='text-align:right' >"+passData[i].closingNumber+"</td>");
			    			$("#passtTicketTable tr:last").append("<td  style='text-align:right'>"+passData[i].noOfBooks+"</td>");
			    			$("#passtTicketTable tr:last").append("<td  style='text-align:right'>"+passData[i].value+"</td>");
			    			$("#passtTicketTable tr:last").append("<td style='text-align:left' >"+passData[i].currentStatus+"</td>");
			    			$("#passtTicketTable tr:last").append("</tr>");
			    			}	
			    		$("#passtTicketTable").append("<tr>");
			    		$("#passtTicketTable tr:last").append("<td >Total</td>");
			    		$("#passtTicketTable tr:last").append("<td colspan='6'></td>");
			    		$("#passtTicketTable tr:last").append("<td style='text-align:right' >"+passTicketArray[1]+"</td>");
			    		$("#passtTicketTable tr:last").append("<td ></td>");
			    		$("#passtTicketTable tr:last").append("</tr>");
			    	}else{
			    		//alert("Hiis");
			    		$("#passtTicketTable").append("<tr>");
			    		$("#passtTicketTable tr:last").append("<td  align='center' colspan='9'>No Data Found</td></tr>");
			    	}
			    	var luggageTicketArray = ticketType[2].split("@");
			    	var luggageData = JSON.parse(luggageTicketArray[0]);
			    	if(luggageData.length>0){
			    		for ( var i = 0; i < luggageData.length; i++) {
			    			$("#luggageTicketTable").append("<tr>");
			    			$("#luggageTicketTable tr:last").append("<td  style='text-align:right' >"+(i+1)+"</td>");
			    			$("#luggageTicketTable tr:last").append("<td  style='text-align:left'>"+luggageData[i].keyNumber+"</td>");
			    			$("#luggageTicketTable tr:last").append("<td  style='text-align:right'>"+luggageData[i].openingNumber+"</td>");
			    			$("#luggageTicketTable tr:last").append("<td  style='text-align:right'>"+luggageData[i].closingNumber+"</td>");
			    			$("#luggageTicketTable tr:last").append("<td  style='text-align:right'>"+luggageData[i].noOfBooks+"</td>");
			    			$("#luggageTicketTable tr:last").append("<td  style='text-align:left'>"+luggageData[i].currentStatus+"</td>");
			    			$("#luggageTicketTable tr:last").append("</tr>");
			    			}	
			    		$("#luggageTicketTable").append("<tr>");
			    		$("#luggageTicketTable tr:last").append("<td >Total</td>");
			    		$("#luggageTicketTable tr:last").append("<td colspan='3'></td>");
			    		$("#luggageTicketTable tr:last").append("<td  style='text-align:right'>"+luggageTicketArray[1]+"</td>");
			    		$("#luggageTicketTable tr:last").append("<td ></td>");
			    		$("#luggageTicketTable tr:last").append("</tr>");
			    	}else{
			    		$("#luggageTicketTable").append("<tr>");
			    		$("#luggageTicketTable tr:last").append("<td  align='center' colspan='6'>No Data Found</td></tr>");
			    	}
			    		
			    	}
				});
				//bootbox.alert("Please Select Stock Date");
			}
		/* 	//}
		}
		 } */
		 }
		 function printDiv() {  
		    	
			    
		
		     
		        var divElements = document.getElementById("header").innerHTML;
			 divElements += document.getElementById("passengerHeader").innerHTML;
		        divElements += document.getElementById("PassengerData").innerHTML;
		        divElements += document.getElementById("luggageHeader").innerHTML;
		        divElements += document.getElementById("luggageData").innerHTML;
		        divElements += document.getElementById("passesHeader").innerHTML;
		        divElements += document.getElementById("passesData").innerHTML;
		     
		        var mywindow = window.open("<%=request.getContextPath()%>/Print.jsp");
		       
		        mywindow.onload = function() {
		        mywindow.document.body.innerHTML=divElements;
		            mywindow.document.body.innerHTML=divElements;
		            //   document.getElementById("print").style.visibility='';
		            mywindow.print();
		            mywindow.close();
		            
		        }
		 } 
		
	</script>
</head>
<BODY >
<div class="page-content-wrapper"> 
	<div class="page-content">
		<div class="row">
			<div class="col-md-12">
				<span class="page-title">
					TICKET INVENTORY : <b id="headername"><s:property value="orgName"/> </b>
				</span>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i> View Stock 
							
							
						</div>
						<div class="actions">
							 <a href="#" class="btn green"  id="create" onclick="printDiv()"> 
								<i class="fa "></i>Print</a>
								

								<!-- <a href="#" class="btn blue" id="editgroup" onclick="goView()">
								<i class="fa ">Back</i></a> -->
								
							
								
							</div>
					</div>
					<div class="portlet-body" >
					
					<div class="panel-body">
					<form action="#" class="form-horizontal form-row-seperated">
					<div class="form-body">
					
							<%!String orgchart=null,orgtype=null;  %>
							<% orgtype=(String)session.getAttribute("orgtypeid");
								orgchart=(String)session.getAttribute("orgchartid");
									session.removeAttribute("Depotid");
					   				
					          %>
					    <input type="hidden" name="orgname" id="orgname" value="<s:property value="orgName"/>"> 
					    <input type="hidden" name="orgtype" id="orgtypeid" value="<%=orgtype%>"> 
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
												name="orgchart.org_chart_id"
													cssClass="select2_category form-control" headerKey="0"
												headerValue="Select" onchange="getDepot(this.value)"></s:select>
										</div>
									</div>
									<div class="form-group " >
										<label class="col-md-3 control-label">Depot<font color="red"></font></label>
										<div class="col-md-3">
											<select id="depotlist" class="select2_category form-control">
										  <option  id="depotSelect" value="0">Select</option>  
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
						<div class="form-group" >
							<label class="control-label col-md-3"> Date </label>
							<div class="col-md-3">
								<div class="input-group input-medium date date-picker"	style="width: auto" data-date-format="dd-mm-yyyy" data-date-viewmode="years" >
									<input type="text" class="form-control" readonly id='stockDate'/> 
										<span class="input-group-btn">
											<button class="btn default" type="button">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
										
									</div>
								</div>
								<div class="col-md-3">
								<input type="button" class="btn grey" onclick="getTickets()" value="Get Records"/>
								</div>
							</div>
							</div>
							</form>
							</div>
						
						<div id="accordion3" class="panel-group accordion" >
							<div class="panel panel-default" >
								<div class="panel-heading" style="overflow:auto;" >
									<h4 class="panel-title">
										<a class="accordion-toggle accordion-toggle-styled" data-toggle="collapse" data-parent="#accordion3" href="#collapse_3_1"> Passenger Ticket </a>
									</h4>
								</div>
								<div id="passengerHeader" style="display: none; visibility: hidden;"><h4>Passenger Ticket</h4></div>
								<div class="panel-collapse collapse in" id="collapse_3_1"  style="color:#000000;overflow:auto;">
									<div class="panel-body">
										<div class="form-body">
										<div id="PassengerData">
											<div class="table-responsive">
													<div id="scrollable-div" >
														<table id="normalTicketTable"  class="table table-hover" style="width:100%">
															<tr>
																<th style='text-align:center' >Sl No</th>
																<th style='text-align:center'>Denom</th>
																<th  style='text-align:center'>Denom Key</th>
																<th style='text-align:center' >Opening No</th>
																<th style='text-align:center' >Closing No</th>
																<th  style='text-align:center'>Number of Books</th>
																<th style='text-align:center' >Value (Rs)</th>
															    <th style='text-align:center'>Status </th>
															</tr>
															<s:iterator value="model.getPassengerTicketObject()" id="object" status="ctr" >
																<tr>
																	<td style='text-align:right' ><s:property value="%{#ctr.index+1}"/></td>
																	<td  style='text-align:right' ><s:property value="#object.denominationValue"/></td>
																	<td  style='text-align:left'><s:property value="#object.keyNumber"/></td>
																	<td  style='text-align:right'><s:property value="#object.openingNumber"/></td>
																	<td  style='text-align:right'><s:property value="#object.closingNumber"/></td>
																	<td style='text-align:right' ><s:property value="#object.noOfBooks"/></td>
																	<td  style='text-align:right'><s:property value="#object.value"/></td>
																	<td  style='text-align:left'><s:property value="#object.currentStatus"/></td>
																</tr>  
															</s:iterator>
															<s:if test="%{model.getPassengerTicketObject() != null}">
															<tr>
																<th><b>Total</b></th>
																<td colspan="5"></td>
																<td id='totalNormalValue' style='text-align:right'><s:property  value="totalNormalvalue"/></td>
																<td></td>
															</tr>
															</s:if>
														</table>
													</div>
												<s:else>
													<table id="noDataForNormal" border="1" class="table table-hover" style="width:100%">
														<tr>
															<td align="center">No Data Found</td>
														</tr>
													</table>
												</s:else>
											</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div id="accordion3" class="panel-group accordion" >
							<div class="panel panel-default" >
								<div class="panel-heading" style="overflow:auto;"  >
									<h4 class="panel-title">
										<a class="accordion-toggle accordion-toggle-styled" data-toggle="collapse" data-parent="#accordion3" href="#collapse_3_2"> Luggauge Ticket</a>
									</h4>
								</div>
								<div id="luggageHeader" style="display: none; visibility: hidden;"><h4>Luggage Ticket</h4></div>
								<div class="panel-collapse collapse in" id="collapse_3_2"  style="color:#000000;overflow:auto;">
									<div class="panel-body">
										<div class="form-body">
										<div id="luggageData">
											<div class="table-responsive">
													<div id="scrollable-div">
														<table id="luggageTicketTable"  class="table table-hover" style="width:100%">
															<tr>
																<th style='text-align:center'>Sl No</th>
																<th style='text-align:center'>Denom Key</th>
																<th style='text-align:center' >Opening No</th>
																<th style='text-align:center'>Closing No</th>
																<th style='text-align:center'>Number of Books</th>
																<th style='text-align:center'>Status </th>
															</tr>
															<s:iterator value="model.getLugguageTicketObject()" id="object" status="ctr" >
																<tr>
																	<td style='text-align:right' ><s:property value="%{#ctr.index+1}"/></td>
																	<td  style='text-align:left'><s:property value="#object.keyNumber"/></td>
																	<td  style='text-align:right'><s:property value="#object.openingNumber"/></td>
																	<td  style='text-align:right'><s:property value="#object.closingNumber"/></td>
																	<td  style='text-align:right'><s:property value="#object.noOfBooks"/></td>
																	<td  style='text-align:left'><s:property value="#object.currentStatus"/></td>
																</tr>  
															</s:iterator>
															<s:if test="%{model.getLugguageTicketObject() != null}">
															<tr>
																<th>Total</th>
																<td colspan="3"></td>
																<td  style='text-align:right'><s:property value="totalNoOfLuggageBooks"/></td>
																<td></td>
															</tr>
															</s:if>
														</table>	
													</div>
												<s:else>
													<table id="noDataForLuggage"  class="table table-hover" style="width:100%">
														<tr>
															<td align="center">No Data Found</td>
														</tr>
													</table>
												</s:else>
											</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div id="accordion3" class="panel-group accordion" >
							<div class="panel panel-default" >
								<div class="panel-heading" style="overflow:auto;"  >
									<h4 class="panel-title">
										<a class="accordion-toggle accordion-toggle-styled" data-toggle="collapse" data-parent="#accordion3" href="#collapse_3_3"> Passes</a>
									</h4>
								</div>
								<div id="passesHeader" style="display: none; visibility: hidden;"><h4>Passes </h4></div>
								<div class="panel-collapse collapse in" id="collapse_3_3"  style="color:#000000;overflow:auto;">
									<div class="panel-body">
										<div class="form-body">
										<div id="passesData">
											<div class="table-responsive" style="color:#000000">
												
													<div  id="scrollable-div">
														<table id="passtTicketTable"   border='1' class='table table-striped table-bordered table-hover' style="width:100%">
															<tr>
																<th style='text-align:center'>Sl No</th>
																<th style='text-align:center'>Denom</th>
																<th style='text-align:center'>Denom Key</th>
																<th style='text-align:center'>Pass Type</th>
																<th style='text-align:center'>Opening No</th>
																<th style='text-align:center'>Closing No</th>
																<th style='text-align:center'>Number of Books</th>
																<th style='text-align:center'>Value (Rs)</th>
																<th style='text-align:center'>Status </th>
											     			 </tr> 
															<s:iterator value="model.getPassTypeTicketObject()" id="object" status="ctr" >
																<tr>
																	<td style='text-align:right' ><s:property value="%{#ctr.index+1}"/></td>
																	<td  style='text-align:right'><s:property value="#object.denominationValue"/></td>
																	<td style='text-align:left' ><s:property value="#object.keyNumber"/></td>
																	<td  style='text-align:left'><s:property value="#object.passType"/></td>
																	<td  style='text-align:right'><s:property value="#object.openingNumber"/></td>
																	<td  style='text-align:right'><s:property value="#object.closingNumber"/></td>
																	<td  style='text-align:right'><s:property value="#object.noOfBooks"/></td>
																	<td style='text-align:right' ><s:property value="#object.value"/></td>
																	<td  style='text-align:left'><s:property value="#object.currentStatus"/></td>
																</tr>  
															</s:iterator>
															<s:if test="%{model.getPassTypeTicketObject() != null}">
															<tr>
																<th>Total</th>
																<td colspan="6"></td>
																<td  style='text-align:right'><s:property value="totalPassValue"/></td>
																<td></td>
															</tr>
															</s:if>
														</table>	
													</div>
												<s:else>
													<table id="noDataForPass" border="1" class="table table-hover" style="width:100%">
														<tr>
															<td align="center">No Data Found</td>
														</tr>
													</table>
												</s:else>
											</div>
										</div>
									</div>
								</div>
							</div>
						 </div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div id='header' style="display:none" visibility="hidden">
					      
					    <center> 
					 	  <SPAN id="organization"><h4><b><s:property value="corporation"/></b></h4></SPAN></h4>
							<br>
						 <span id="headerDate"> <h5><b >Date: </b>&nbsp;<b><s:property value="dateHeader"/></b></h5></span>
					     </center>
					    <div align='right'><b>Run Date:-</b><s:property value="runDate"/></div>
 </div>
</body>						