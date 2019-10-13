
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<html>
	<head>
	<meta http-equiv="Cache-control" content="no-cache">
	<meta http-equiv="Expires" content="-1">
	<style>
	    p.intro {color:red;font-size:14px;}
        p.success{color:green; size:2px;}
		.centerize {
    		display: block;
    		margin: 0 auto;
    		text-align: center;
		}
	</style>
	<script>
	/*  $(document).ready( function () {
	    var table = $('#addticketInv').DataTable();
	    new $.fn.dataTable.FixedHeader( table );
	} );  */
	</script>
	</head>
	<body onload="totalValueCalculate()"> 
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js" type="text/javascript"></script>
	<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.min.js" type="text/javascript"></script>
	<form action="saveTicketReturn.action" name="viewticketform">
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
								<i class="fa fa-globe"></i>Initial Stock View
							</div>
							<div class="actions">
								<a href="#" class="btn green" id="issueTicket" onClick="createTicket()">
								<i class="fa fa-plus"></i> Stock Entry  </a>
								<a href="#" class="btn blue" id="issueTicket" onClick="print()">
								<i class="fa fa-print"></i> Print  </a>
							</div>
						</div>
						<form class="form-horizontal" role="form" method="get">
							<div  id="resizable1" class="col-md-12">
								<div class="portlet box white" >
									<div class="portlet-body" >
										<div id="accordion6" class="panel-group accordion" >
											<div class="panel panel-default" >
												<div class="panel-collapse collapse in" id="collapse_3_4"  style="color:#000000;overflow:auto;">
													<div class="panel-body">
														<div class="form-body">
															<div class="table-responsive" style="color:#000000">
																<table class="table" style="width:80%;">
																	<tr>
																		<td>
																			<label class="col-md-5 control-label">Unit Type</label>
																		</td>
																		<td>
																			<select class="select2_category form-control" name="unittype" id="unittypeid"  onchange="getUnitNames()" style="width:200px;">
																				<option >Select</option>
														                 	</select>
																		</td>
																	</tr>
																	<tr>
																		<td>
																			<label class="col-md-5 control-label">Unit Name</label>
																		</td>
																		<td>
																			<select class="select2_category form-control" name="unitname" id="unitnameid"  style="width:200px;">
																				<option >Select</option>
															                </select>
																		</td>
																	</tr>
																	<tr>
																		<td align="center" colspan="2">
																			<button class="btn blue" type="button" onclick="getTicketInventoryViewData()"> Show Data</button>
																		</td>
																		<!-- <td style="display:none">
																			<a href="GetTktInvView.action" class="btn grey"  id="cancel"> //onclick="callEdit()"
																				 Cancel </a>
																		</td> -->
																	</tr>
																</table>	
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div id="accordion3" class="panel-group accordion" >
											<div class="panel panel-default" >
												<div class="panel-heading" overflow:auto;" >
													<h4 class="panel-title">
													<a class="accordion-toggle accordion-toggle-styled" data-toggle="collapse" data-parent="#accordion3" href="#collapse_3_1"> Ticket Type </a>
													</h4>
												</div>
												<div class="panel-collapse collapse in" id="collapse_3_1"  style="color:#000000;overflow:auto;">
													<div class="panel-body">
														<div class="form-body">
															<div class="table-responsive" style="color:#000000">
																<table class="table table-hover" id="ticketdata">
																	<tr>
																		<th align="right"></th>
																		<th align="right">Denom</th>
																		<th align="right">Denom Key</th>
																		<th align="right">Start Number</th>
																		<th align="right">End Number</th>
																		<th align="right">Number of tickets</th>
																		<th align="right">No Of Books</th>
																		<th align="right">Amount</th>
																		<th align="right">Priority</th>
																		<th align="right">Stock Date</th>
																		
																	</tr>
																</table>	
															</div>
															<div class="table-responsive" style="color:#000000" id="ticketdataDiv">
																<table class="table table-hover" id="hiddenTicketdata">
																	<tr>
																		<th align="right">Denom</th>
																		<th align="right">Denom Key</th>
																		<th align="right">Start Number</th>
																		<th align="right">End Number</th>
																		<th align="right">Number of tickets</th>
																		<th align="right">No Of Books</th>
																		<th align="right">Amount</th>
																		<th align="right">Priority</th>
																		<th align="right">Stock Date</th>
																		
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
													<a class="accordion-toggle accordion-toggle-styled" data-toggle="collapse" data-parent="#accordion4" href="#collapse_3_2"> 	Pass Type </a>
													</h4>
												</div>
												<div class="panel-collapse collapse in" id="collapse_3_2"  style="color:#000000;overflow:auto;">
													<div class="panel-body">
														<div class="form-body">
															<div class="table-responsive" style="color:#000000">
																<table class="table table-hover" id="passtable">
																	<tr>
																		<th align="right"></th>
																		<th align="right">Type of Pass</th>
																		<th align="right">Denom </th>
																		<th align="right">Denom Key</th>
																		<th align="right">Pass Day</th>
																		<th align="right">Start Number</th>
																		<th align="right">End Number</th>
																		<th align="right">No. of Passes</th>
																		<th align="right">No. Of Books</th>
																		<th align="right">Total Value</th>
																		<th align="right">Priority</th>
																		<th align="right">Stock Date</th>
																		
																	</tr>
																</table>	
															</div>
															<div  style="display: none;" id="passtableDiv">
																<table class="table table-hover" id="hiddenPasstable">
																	<tr>
																		<th align="right">Type of Pass</th>
																		<th align="right">Denom </th>
																		<th align="right">Denom Key</th>
																		<th align="right">Pass Day</th>
																		<th align="right">Start Number</th>
																		<th align="right">End Number</th>
																		<th align="right">No. of Passes</th>
																		<th align="right">No. Of Books</th>
																		<th align="right">Total Value</th>
																		<th align="right">Priority</th>
																		<th align="right">Stock Date</th>
																		
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
													<a class="accordion-toggle accordion-toggle-styled" data-toggle="collapse" data-parent="#accordion5" href="#collapse_3_3"> Luggage Type </a>
													</h4>
												</div>
												<div class="panel-collapse collapse in" id="collapse_3_3"  style="color:#000000;overflow:auto;">
													<div class="panel-body">
														<div class="form-body">
															<div class="table-responsive" style="color:#000000">
																<table class="table table-hover" id="luggdata">
																	<tr>
																		<th align="right"></th>
																		<th align="right">Luggage ticket key</th>
																		<th align="right">Start Number</th>
																		<th align="right">End Number</th>
																		<th align="right">No. of luggage ticket</th>
																		<th align="right">No Of Books</th>
																		<th align="right">Priority</th>
																		<th align="right">Stock Date</th>
																		
																	</tr>
																</table>	
															</div>
															<div  style="display:none" id="luggdataDiv">
																<table id="hiddenLuggdata">
																	<tr>
																		<th >Luggage ticket key</th>
																		<th >Start Number</th>
																		<th >End Number</th>
																		<th >No. of luggage ticket</th>
																		<th >No Of Books</th>
																		<th >Priority</th>
																		<th >Stock Date</th>
																		
																	</tr>
																</table>	
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									
									<div id="accordion7" class="panel-group accordion" >
											<div class="panel panel-default" >
												<div class="panel-heading" overflow:auto;" >
													<h4 class="panel-title">
													<a class="accordion-toggle accordion-toggle-styled" data-toggle="collapse" data-parent="#accordion7" href="#collapse_3_5"> Trip Sheet Type </a>
													</h4>
												</div>
												<div class="panel-collapse collapse in" id="collapse_3_5"  style="color:#000000;overflow:auto;">
													<div class="panel-body">
														<div class="form-body" >
															<div class="table-responsive" style="color:#000000" >
																<table class="table table-hover" id="tsheetdata">
																	<tr>
																		<th align="right"></th>
																		<th align="right">Waybill key</th>
																		<th align="right">Start Number</th>
																		<th align="right">End Number</th>
																		<th align="right">Total Number of Waybill</th>
																		<th align="right">Stock Date</th>
																		
																	</tr>
																</table>	
															</div>
															<div  style="display:none" id="tsheetdataDiv">
																<table class="table table-hover" id="hiddenTsheetdata">
																	<tr>
																		<th align="right">Waybill key</th>
																		<th align="right">Start Number</th>
																		<th align="right">End Number</th>
																		<th align="right">Total Number of Waybill</th>
																		<th align="right">Stock Date</th>
																		
																	</tr>
																</table>	
															</div>
									
														</div>
													</div>
												</div>
											</div>
										</div>
									
										<div id="header" style="display:none;text-align: center">
											<h4>BMTC</h4>
											<h6>Initial Stock Entry of : <label id="dep"></label></h6>
											<h6>Date : <s:property value="date"/></h6>
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
	<script>
	/* jQuery(document).ready(function() {
		//getTicketInventoryViewData();
	}); */
	</script>
	<script>
	function print(){
		var divElements = "<center>"+ document.getElementById("header").innerHTML+"</center>";
		divElements += "<br/><br/>";
		divElements += document.getElementById("ticketdataDiv").innerHTML;
		divElements += "<br/><br/>";
        divElements += document.getElementById("passtableDiv").innerHTML;
        divElements += "<br/><br/>";
        divElements += document.getElementById("luggdataDiv").innerHTML;
        divElements += "<br/><br/>";
        divElements += document.getElementById("tsheetdataDiv").innerHTML;
        //alert(divElements);
       /*  divElements += document.getElementById("passesHeader").innerHTML;
        divElements += document.getElementById("passesData").innerHTML; */
     
        var mywindow = window.open("<%=request.getContextPath()%>/inventory/Print.jsp");
       
        mywindow.onload = function() {
        mywindow.document.body.innerHTML=divElements;
	            mywindow.document.body.innerHTML=divElements;
	            //   document.getElementById("print").style.visibility='';
	            mywindow.print();
	            mywindow.close();
	            
	        }
	}
	function getTicketInventoryViewData(){
		$("#ticketdata").find("tr:gt(0)").remove();
        $("#passtable").find("tr:gt(0)").remove();
        $("#luggdata").find("tr:gt(0)").remove();
        $("#tsheetdata").find("tr:gt(0)").remove();
		var unittype=$('#unittypeid').val();
		var unitname=$('#unitnameid').val();
		if(unittype==null){
			unittype=0;
		}
		if(unitname==null){
			unitname=0;
		}
		$.ajax({
		    type : 'POST',
		    data : {
		       id:'1',
		       unittype:unittype,
		       unitname:unitname,
		    },
		    url  :  "<s:url action='GetTktInvViewData'/>",
		    success: function(data){
		        result=JSON.parse(data);
		        var ttltkt=0;
		        var ttltktval=0;
		        var ttlpass=0;
		        var ttlpassval=0;
		        var ttllugg=0;
		        var tttsheet=0;
		        for(var i=0;i<result.ticketdatasize;i++){
		        	$("#ticketdata tr").last().after('<tr><td><input id="ticketdatachk" class="group-checkable" type="checkbox" value="'+result.ticketdata[i][0]+'" data-set="#sample_2 .checkboxes" name="chkbox"></td>><td>'+result.ticketdata[i][1]+'</td><td>'+result.ticketdata[i][2]+'</td><td>'+padLeft(result.ticketdata[i][3],8)+'</td><td>'+padLeft(result.ticketdata[i][4],8)+'</td><td>'+result.ticketdata[i][5]+'</td><td>'+result.ticketdata[i][6]+'</td><td>'+result.ticketdata[i][7]+'</td><td>'+result.ticketdata[i][10]+'</td><td>'+result.ticketdata[i][11]+'</td></tr>');
		        	ttltkt=ttltkt+result.ticketdata[i][6];
			        ttltktval=ttltktval+result.ticketdata[i][7];
			        
			        $("#hiddenTicketdata tr").last().after('<tr><td>'+result.ticketdata[i][1]+'</td><td>'+result.ticketdata[i][2]+'</td><td>'+padLeft(result.ticketdata[i][3],8)+'</td><td>'+padLeft(result.ticketdata[i][4],8)+'</td><td>'+result.ticketdata[i][5]+'</td><td>'+result.ticketdata[i][6]+'</td><td>'+result.ticketdata[i][7]+'</td><td>'+result.ticketdata[i][10]+'</td><td>'+result.ticketdata[i][11]+'</td></tr>');
		        }
		        if(result.ticketdatasize==0){
		        	$("#hiddenTicketdata").html("");
		        }
		        $("#ticketdata tr").last().after('<tr><th colspan="3">Total Number of Books:'+ttltkt+'</th> <th colspan="2">Total Value:'+ttltktval+'</th> </tr>');
		        
		        for(var i=0;i<result.passdatasize;i++){
		        	$("#passtable tr").last().after('<tr><td><input id="passdatachk" class="group-checkable" type="checkbox" value="'+result.passdata[i][0]+'" data-set="#sample_2 .checkboxes" name="chkbox"></td><td>'+result.passdata[i][1]+'</td><td>'+result.passdata[i][2]+'</td><td>'+result.passdata[i][3]+'</td><td>'+result.passdata[i][4]+'</td><td>'+padLeft(result.passdata[i][5],8)+'</td><td>'+padLeft(result.passdata[i][6],8)+'</td><td>'+result.passdata[i][7]+'</td><td>'+result.passdata[i][8]+'</td><td>'+result.passdata[i][9]+'</td><td>'+result.passdata[i][10]+'</td><td>'+result.passdata[i][11]+'</td></tr>');
		        	$("#hiddenPasstable tr").last().after('<tr><td>'+result.passdata[i][1]+'</td><td>'+result.passdata[i][2]+'</td><td>'+result.passdata[i][3]+'</td><td>'+result.passdata[i][4]+'</td><td>'+padLeft(result.passdata[i][5],8)+'</td><td>'+padLeft(result.passdata[i][6],8)+'</td><td>'+result.passdata[i][7]+'</td><td>'+result.passdata[i][8]+'</td><td>'+result.passdata[i][9]+'</td><td>'+result.passdata[i][10]+'</td><td>'+result.passdata[i][11]+'</td></tr>');
		        	ttlpass=ttlpass+result.passdata[i][8];
			        ttlpassval=ttlpassval+result.passdata[i][9];
		        }
		        if(result.passdatasize==0){
		        	$("#hiddenPasstable").html("");
		        }
		        $("#passtable tr").last().after('<tr><th colspan="3">Total Number of Books:'+ttlpass+'</th> <th  colspan="2">Total Value:'+ttlpassval+'</th> </tr>');
		        
		        for(var i=0;i<result.luggdatasize;i++){
		        	$("#luggdata tr").last().after('<tr><td><input id="luggdatachk" class="group-checkable" type="checkbox" value="'+result.luggdata[i][0]+'" data-set="#sample_2 .checkboxes" name="chkbox"></td><td>'+result.luggdata[i][1]+'</td><td>'+padLeft(result.luggdata[i][2],8)+'</td><td>'+padLeft(result.luggdata[i][3],8)+'</td><td>'+result.luggdata[i][4]+'</td><td>'+result.luggdata[i][5]+'</td><td>'+result.luggdata[i][6]+'</td><td>'+result.luggdata[i][7]+'</td></tr>');
		        	$("#hiddenLuggdata tr").last().after('<tr><td>'+result.luggdata[i][1]+'</td><td>'+padLeft(result.luggdata[i][2],8)+'</td><td>'+padLeft(result.luggdata[i][3],8)+'</td><td>'+result.luggdata[i][4]+'</td><td>'+result.luggdata[i][5]+'</td><td>'+result.luggdata[i][6]+'</td><td>'+result.luggdata[i][7]+'</td></tr>');
		        	ttllugg=ttllugg+result.luggdata[i][5];
		        }
		        if(result.luggdatasize==0){
		        	$("#hiddenLuggdata").html("");
		        }
		        $("#luggdata tr").last().after('<tr><th colspan="3">Total Number of Books:'+ttllugg+'</th>  </tr>');
		        
		        for(var i=0;i<result.tsheetdatasize;i++){
		        	$("#tsheetdata tr").last().after('<tr><td><input id="tsheetdatachk" class="group-checkable" type="checkbox" value="'+result.tsheetdata[i][0]+'" data-set="#sample_2 .checkboxes" name="chkbox"></td><td>'+result.tsheetdata[i][1]+'</td><td>'+padLeft(result.tsheetdata[i][2],8)+'</td><td>'+padLeft(result.tsheetdata[i][3],8)+'</td><td>'+result.tsheetdata[i][4]+'</td><td>'+result.tsheetdata[i][5]+'</td></tr>');
		        	$("#hiddenTsheetdata tr").last().after('<tr><td>'+result.tsheetdata[i][1]+'</td><td>'+padLeft(result.tsheetdata[i][2],8)+'</td><td>'+padLeft(result.tsheetdata[i][3],8)+'</td><td>'+result.tsheetdata[i][4]+'</td><td>'+result.tsheetdata[i][5]+'</td></tr>');
		        }
		        if(result.tsheetdatasize==0){
		        	$("#hiddenTsheetdata").html("");
		        }
		       
		        
		    },
		    error : function(xhr, errmsg) {
		    	}
		});
	}
	function createTicket() {
		document.forms[0].action = "IssueTicketInventory.action";
		document.forms[0].submit();
	}

	function getOrgType(){
		var len= document.getElementById('unittypeid').options.length;
		if(len<=1 ) {
	       $.ajax({
	           type: "post",
	           async:false,
	           url: '<%=request.getContextPath()%>/findAllOrgTypeActionForInventory',
	           success: function(result) {
	               document.getElementById('unittypeid').innerHTML=result;
	           }
	       });
		 }
		 var orgTypeId = '<s:property value="#session.orgtypeid" />';
		 //<s:property value="#session.orgTypeId" />
		 $('#unittypeid').val(orgTypeId );
		/*  var orgName = $("#unittypeid option:selected").text().trim();
			$('#divis').val(orgName);
			alert(orgName); */
		 getUnitNames();
	}
	function getUnitNames(){
		$('#select2-chosen-2').html("Select");
		var e = document.getElementById("unittypeid");
		var strUser = e.options[e.selectedIndex].value;
		var len= document.getElementById('unitnameid').options.length;
		$.ajax({
	    	type: "post",
	        async:false,
	        url: '<%=request.getContextPath()%>/findUnitNameActionForInventory?orgid='+strUser,
	        success: function(result) {
	       		document.getElementById('unitnameid').innerHTML=result;
	       }
	 	});
	    var orgId = '<s:property value="#session.orgchartid" />';
		$('#unitnameid').val(orgId );
		var divisionName = $("#unitnameid option:selected").text().trim();
		$('#dep').html(divisionName);
		
		getTicketInventoryViewData();
			
	}
	
	jQuery(document).ready(function() {
		getOrgType();
	});
	
	function padLeft(nr, n, str){
	    return Array(n-String(nr).length+1).join(str||'0')+nr;
	}
	//or as a Number prototype method:
	Number.prototype.padLeft = function (n,str){
	    return Array(n-String(this).length+1).join(str||'0')+this;
	}
	</script>
</form>
</body>
</html>