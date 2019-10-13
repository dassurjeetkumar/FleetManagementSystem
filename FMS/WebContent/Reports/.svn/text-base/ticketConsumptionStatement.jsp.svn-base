  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<style type="text/css">
	.table > tbody > tr > th,.table > tbody > tr > td{
		border:3px solid #dddddd;
	}
</style>

<script>
function printDiv() {     
    
// 	document.getElementById("printid").style.visibility='visible';  
//	document.getElementById("header").style.display='block';
// 	document.getElementById("header").style.visibility='none';     
	//var divElements = document.getElementById("header").innerHTML;
	var divElements = document.getElementById("reportData").innerHTML;
    var mywindow = window.open("<%=request.getContextPath()%>/Print.jsp");
   
    mywindow.onload = function() {
	    mywindow.document.body.innerHTML=divElements;
	    mywindow.document.body.innerHTML=divElements;
	    mywindow.print();
	    mywindow.close();
    }
            
}

function rawPrint(){
	//alert("hdfdfdf");

	/* var htmlCode = "<applet archive='/doa/applet/IqPrint.jar' name='print' code='IqPrint' width=0 height=0><param name=httpUrl value='/doa/Ticketing/CashierReport.txt'><param name=printText value=''><param name=printDevice value='epson'><param name=printSubmitUrl value=''><param name=paginationRequired value='Y'><param name=directPrint value='Y'><param name=displayRequired value='N'></applet>";
	$("#resultset").html(htmlCode); */
    $.ajax({
      type: "post",
      url:"Ticketing/RawPrint.jsp",
      data:"TicketConsumptionReportStatement=new",
        success: function(result){
        	$("#TicketConsumptionReportStatementRaw").html(result);
        	//alert(result);
          
        }
    });
}
function reloadHeading(){
	var divisionId = $("#divisionId").val();
	if(divisionId == '-1'){
		$("#heading").html("Ticket Consumption Statement (G-3)");
	}else{
		$("#heading").html("Ticket Consumption Statement (G-2)");
	}
	
}
</script>
 
<div class="page-content-wrapper">
	<div class="page-content">
		<div id="pageLoader" class="modal fade in" tabindex="-1" role="dialog" aria-labelledby="myModalLabel3" aria-hidden="false">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<p>
							<img src="assets/images/loader1.gif" align="top" style="margin-left:100px;"/> 
						</p>
						<p style='text-align:center'>Please Wait........</p>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i><b id='heading'>Ticket Consumption Statement (G-3)</b>
						</div>
					</div> 
					<div class="portlet-body">
                          <div class="portlet-body" >
                               <div class="panel-body">
                                   <form action="#" class="form-horizontal form-row-seperated" method="post">
                              <!-- <form action="getTicketConsumptionStatement" class="form-horizontal form-row-seperated" method="post">  -->
                                       <div class="form-body">
                                       		<div class="form-group">
                                       			<label class="control-label col-md-4">Division </label>
                                       			<div class="col-md-3">
                                       				<select class= 'select2-container select2_category form-control'id='divisionId' name='a.' onchange="reloadHeading(this)">
                                       					<option value='-1'>All</option>
                                       					<s:iterator value="divisionList">
                                       						<option value="<s:property value='org_chart_id' />">
                                       						<s:property value="org_name" /></option>
                                       					</s:iterator>
                                       				</select>
                                       			</div>
                                       		</div>
                                       		<div class="form-group">
                                       			<label class="control-label col-md-4">Month - Year:</label>
												<div class="col-md-4">
													<div class="input-group input-medium date date-picker"
														style="width: auto" data-date-format="mm-yyyy"
															data-date-viewmode="years" >
														<input id="startdate" class="form-control" type="text" readonly="true" size="16" name="reportDate" value="<s:property value='reportDate' />">
														<span class="input-group-btn">
															<button class="btn default date-set" type="button">
																<i class="fa fa-calendar"></i>
															</button>
														</span>
													</div>
												</div>
											<button type="submit" class="btn green" onClick="getDepot()">Submit</button>
										</div>
										<div class="form-group" id='reportData'>
											<table class='table table-striped table-bordered table-hover' id='ticketTable'>
												<tr>
													<th>Division name</th>
													<th>Denm</th>
													<th>O.B.</th>
													<th>Reciepts</th>
													<th>From other depot</th>
													<th>Total receipt</th>
													<th>Tr to other depot</th>
													<th>Discrepant books</th>
													<th>Total return</th>
													<th>Nett stock</th>
													<th>Depot stock</th>
													<th>Bag stock</th>
													<th>Total C/B</th>
													<th>Total consumption</th>
													<th>Value of consumption Rs</th>
												</tr>  
												<tr>
													<td>0</td>
													<td>0</td>
													<td>0</td>
													<td>0</td>
													<td>0</td>
													<td>0</td>
													<td>0</td>
													<td>0</td>
													<td>0</td>
													<td>0</td>
													<td>0</td>
													<td>0</td>
													<td>0</td>
													<td>0</td>
													<td>0</td>
												</tr>
												<tr>
													<th colspan="14" style="text-align: center" > Revenue realised</th>
													<th >0</th>		
												</tr>
											</table>
											<br/>
											<table class='table table-striped table-bordered table-hover' id="pass65Table">
												<tr>
													<th colspan="15"  style="text-align: center" >Rs.65/-DAILY PASS CONSUMPTION STATEMENT MONTH OF 01/2016</th> 
												</tr>
												<tr>
													<th>Division name</th>
													<th>Denm</th>
													<th>O.B.</th>
													<th>Reciepts</th>
													<th>From other depot</th>
													<th>Total receipt</th>
													<th>Tr to other depot</th>
													<th>Discrepant books</th>
													<th>Total return</th>
													<th>Nett stock</th>
													<th>Depot stock</th>
													<th>Bag stock</th>
													<th>Total C/B</th>
													<th>Total consumption</th>
													<th>Value of consumption Rs</th>
												</tr>  
												<tr>
													<td>0</td>
													<td>0</td>
													<td>0</td>
													<td>0</td>
													<td>0</td>
													<td>0</td>
													<td>0</td>
													<td>0</td>
													<td>0</td>
													<td>0</td>
													<td>0</td>
													<td>0</td>
													<td>0</td>
													<td>0</td>
													<td>0</td>
												</tr>
												<tr>
													<th colspan="14" style="text-align: center" > Revenue realised</th>
													<th >0</th>		
												</tr>
											</table>
											<br/> 
											<table class='table table-striped table-bordered table-hover' id='pass70Table'>
												<tr>
													<th colspan="15"  style="text-align: center" >Rs.70/-DAILY PASS CONSUMPTION STATEMENT MONTH OF 01/2016</th> 
												</tr>
												<tr>
													<th>Division name</th>
													<th>Denm</th>
													<th>O.B.</th>
													<th>Reciepts</th>
													<th>From other depot</th>
													<th>Total receipt</th>
													<th>Tr to other depot</th>
													<th>Discrepant books</th>
													<th>Total return</th>
													<th>Nett stock</th>
													<th>Depot stock</th>
													<th>Bag stock</th>
													<th>Total C/B</th>
													<th>Total consumption</th>
													<th>Value of consumption Rs</th>
												</tr>  
												<tr>
													<td>0</td>
													<td>0</td>
													<td>0</td>
													<td>0</td>
													<td>0</td>
													<td>0</td>
													<td>0</td>
													<td>0</td>
													<td>0</td>
													<td>0</td>
													<td>0</td>
													<td>0</td>
													<td>0</td>
													<td>0</td>
													<td>0</td>
												</tr>
												<tr>
													<th colspan="14" style="text-align: center" > Revenue realised</th>
													<th >0</th>		
												</tr>
											</table>
											<!-- <center><input type="button" class= 'btn green' value='Print' onclick="printDiv()"/></center> -->
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
	</div>
</head>