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
	 	 
		
	var stockDate=$("#selectedStockDate").val();
	$("#stockDate").val(stockDate);
	//alert("StockDate"+stockDate);
	$("#referenceNumberSelect").val(' ');
		var referenceNumber=$("#referenceNumber").val();
		//alert("StockDate"+stockDate+"ref"+referenceNumber);
		//document.getElementById('select2-chosen-1').innerHTML =" ";
		$("#referenceNumberSelect").val( );
		$.ajax({
			type:'get',
			data: {
				stockDate:stockDate
				
		    },
		    async:false,
	    	url:  "<s:url action='getReferenceNumber'/>",
	    	success: function(result){
	    		document.getElementById('referenceNumberSelect').innerHTML = result;
	    	}
		});
		
		if(referenceNumber!=0){
			//document.getElementById('select2-chosen-1').innerHTML =referenceNumber;
			$("#referenceNumberSelect").val(referenceNumber);
		}
		
	 });
	
	
	function getReferenceNumber( ){
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 $("#select2-chosen-1").html("Select");
		 var stockDate = $("#stockDate").val();
	///	 alert('Here'+stockDate);
		
			
			 
	       <%--  $.ajax({
	            type: "get",
	            url: '<%=request.getContextPath()%>/getReferenceNumber?stockDate='+ stockDate,
				success : function(result) {
					document.getElementById('referenceNumberSelect').innerHTML = result;
				
				}
			}); --%>
	        
	        $.ajax({
				type:'post',
				data: {
					stockDate:stockDate
					
			    },
			    async:false,
		    	url:  "<s:url action='getReferenceNumber'/>",
		    	success: function(result){
		    		document.getElementById('referenceNumberSelect').innerHTML = result;
		    	}
			});
	

	}
	function getStockDetails(){
		var stockDate = $("#stockDate").val();
		var referenceNumber = $("#referenceNumberSelect").val();
		//alert("refer"+referenceNumber);
		
		if(stockDate==""){
			bootbox.alert("Please Select Stock Date");
		}else if(referenceNumber==0){
			bootbox.alert("Please Select Reference Number");
			return false;
		}
		else if(stockDate!="" &&referenceNumber!=0 ){
			//alert("referdf"+referenceNumber);
			$("#selectedStockDate").val(stockDate);
			$("#referenceNumber").val(referenceNumber);
		
			return true;
			
		}
			
			
	}
	
	function getDepot(orgId){
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		
	
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
 <form action="getStockEntryRecords" class="form-horizontal"	method="post">
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
							<i class="fa fa-globe"></i> Stock Entry View
							
							
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
					      <input type="hidden" name="stockDate" id="selectedStockDate" value="<s:property value='stockDate'/>"> 
					     <input type="hidden" name="referenceNumber" id="referenceNumber" value='<s:property value='referenceNumber'/>'> 
					    <input type="hidden" name="orgname" id="orgname" value="<s:property value="orgName"/>"> 
					    <input type="hidden" name="orgtype" id="orgtypeid" value="<%=orgtype%>"> 
					   <input type="hidden" name="orgtchartname" id="orgchartnameid" value="<%=orgchart%>"> 
							
						<div class="form-group" >
							<label class="control-label col-md-3"> Date </label>
							<div class="col-md-3">
								<div class="input-group input-medium date date-picker"	style="width: auto" data-date-format="dd-mm-yyyy" data-date-viewmode="years" >
									<input type="text" class="form-control" readonly id='stockDate'  onchange="getReferenceNumber()"/> 
										<span class="input-group-btn">
											<button class="btn default" type="button">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
										<script>
										var date = new Date();
										alert(date.getDate()+"-"+date.getFullMonth()+"-"+date.getFullYear());
										</script>
									</div>
								</div>
								<!-- <div class="col-md-3">
								<input type="button" class="btn grey" onclick="getReferenceNumber()" value="Get ReferenceNumber"/>
								</div> -->
							</div>
							
							<div class="form-group" >
							<label class="control-label col-md-3"> Reference Number </label>
							
			
							
										
										<div class="col-md-3">
											<select id="referenceNumberSelect" class="select2_category form-control">
										  <option  id="depotSelect" value="0">Select</option>  
											</select>
										</div>
										<div class="col-md-3">
						    			 <input type="submit" class="btn grey" onclick="return getStockDetails()" value='Get Record'> 
								          </div>
									</div>
							<%-- <div class="col-md-3">
								<div class="input-group input-medium date date-picker"	style="width: auto" data-date-format="dd-mm-yyyy" data-date-viewmode="years" >
									<input type="text" class="form-control" readonly id='stockDate'/> 
										<span class="input-group-btn">
											<button class="btn default" type="button">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
										
									</div>
								</div> --%>
								
							</div>
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
															    <th style='text-align:center'>Priority  </th>
															</tr>
															<s:iterator value="model.getPassengerTicketObject()" id="object" status="ctr" >
																<tr>
																	<td style='text-align:center' ><s:property value="%{#ctr.index+1}"/></td>
																	<td  style='text-align:center' ><s:property value="#object.denominationValue"/></td>
																	<td  style='text-align:center'><s:property value="#object.keyNumber"/></td>
																	<td  style='text-align:center'><s:property value="#object.openingNumber"/></td>
																	<td  style='text-align:center'><s:property value="#object.closingNumber"/></td>
																	<td style='text-align:center' ><s:property value="#object.noOfBooks"/></td>
																	<td  style='text-align:right;padding-right: 50px'><s:property value="#object.value"/></td>
																	<td  style='text-align:center'><s:property value="#object.priority"/></td>
																</tr>  
															</s:iterator>
															<s:if test="%{model.getPassengerTicketObject() != null}">
															<tr>
																<th style='text-align:center'><b>Total</b></th>
																<td colspan="5"></td>
																<td id='totalNormalValue'  style='text-align:right;padding-right:50px'><s:property  value="totalNormalvalue"/></td>
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
																<th style='text-align:center'>Priority  </th>
															</tr>
															<s:iterator value="model.getLugguageTicketObject()" id="object" status="ctr" >
																<tr>
																	<td style='text-align:center' ><s:property value="%{#ctr.index+1}"/></td>
																	<td  style='text-align:center'><s:property value="#object.keyNumber"/></td>
																	<td  style='text-align:center'><s:property value="#object.openingNumber"/></td>
																	<td  style='text-align:center'><s:property value="#object.closingNumber"/></td>
																	<td  style='text-align:center'><s:property value="#object.noOfBooks"/></td>
																	<td  style='text-align:center'><s:property value="#object.priority"/></td>
																</tr>  
															</s:iterator>
															<s:if test="%{model.getLugguageTicketObject() != null}">
															<tr>
																<th style='text-align:center'>Total</th>
																<td colspan="3"></td>
																<td  style='text-align:center'><s:property value="totalNoOfLuggageBooks"/></td>
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
																<th style='text-align:center'>Priority </th>
											     			 </tr> 
															<s:iterator value="model.getPassTypeTicketObject()" id="object" status="ctr" >
																<tr>
																	<td style='text-align:center' ><s:property value="%{#ctr.index+1}"/></td>
																	<td  style='text-align:center'><s:property value="#object.denominationValue"/></td>
																	<td style='text-align:center' ><s:property value="#object.keyNumber"/></td>
																	<td  style='text-align:center'><s:property value="#object.passType"/></td>
																	<td  style='text-align:center'><s:property value="#object.openingNumber"/></td>
																	<td  style='text-align:center'><s:property value="#object.closingNumber"/></td>
																	<td  style='text-align:center'><s:property value="#object.noOfBooks"/></td>
																	<td style='text-align:right;padding-right: 40px'><s:property value="#object.value"/></td>
																	<td  style='text-align:center'><s:property value="#object.priority"/></td>
																</tr>  
															</s:iterator>
															<s:if test="%{model.getPassTypeTicketObject() != null}">
															<tr>
																<th style='text-align:center'>Total</th>
																<td colspan="6"></td>
																<td  style='text-align:right;padding-right: 40px'><s:property value="totalPassValue"/></td>
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
					</div></div>
				</div>
			</div>
		</div>
		
	</div>
</form>
</div>
<div id='header' style="display:none" visibility="hidden">
					      
					    <center> 
					 	  <SPAN id="organization"><h4>TICKET INVENTORY :<b><s:property value="orgName"/></b></h4></SPAN></h4>
							<br>
						 <span id="headerDate"> <h5><b >Date: </b>&nbsp;<b><s:property value="dateHeader"/></b></h5></span>
					     </center>
					    <div align='right'><b>Run Date:-</b><s:property value="runDate"/></div>
 </div>
</body>						