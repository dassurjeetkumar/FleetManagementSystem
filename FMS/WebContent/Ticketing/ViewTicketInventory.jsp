
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<script src="assets/global/plugins/jquery-1.11.0.min.js"
	type="text/javascript"></script>
<script>


	function forTicketType(){
		var bookvalues = [];
		var ticketValues=[];
		var ticketNumberBooks=0;
		var ticketValue=0;
		$(".tick_no_books").each(function(index, value) { 
			bookvalues.push($(this).text());
			
		});
		$(".tick_value").each(function(index, value) { 
			ticketValues.push($(this).text());
		});
	  	for (var i = 0; i < bookvalues.length; i++) {
			if (!isNaN((bookvalues[i]))) {
				ticketNumberBooks += parseInt(bookvalues[i]);
			}
		}
		for (var k = 0; k < ticketValues.length; k++) {
			if (!isNaN((ticketValues[k]))) {
				ticketValue += parseInt(ticketValues[k]);
			}
		}
		$("#totalticket").html(ticketNumberBooks);
		$("#totalvalue").html(ticketValue);
	}
	function forPassType(){
		
		var passValues = [];
		var passTicketValues=[];
		var passNumberBooks=0;
		var passTotalValue=0;
		$(".pass_no_books").each(function(index, value) { 
			passTicketValues.push($(this).text());
	 	});
		$(".pass_value").each(function(index, value) { 
			passValues.push($(this).text());
 		});
	  	for(var i = 0; i < passValues.length; i++) {
			if (!isNaN((passValues[i]))) {
				passTotalValue += parseInt(passValues[i]);
			}
		}
		for(var k = 0; k < passTicketValues.length; k++) {
			if ( passTicketValues[k]!="" && !isNaN(passTicketValues[k])) {
				passNumberBooks += parseInt(passTicketValues[k]);
			}
		}
		$("#totalnopass").html(passNumberBooks);
		$("#totalpassvalue").html(passTotalValue);
	}
	function forLuggageType(){
		
		var LuggageBookValues=[];
        var LuggageBookCount=0;	
		$(".lugg_no_books").each(function(index, value) { 
			LuggageBookValues.push($(this).text());
	 	});
		for (var i = 0; i < LuggageBookValues.length; i++) {
			if (!isNaN((LuggageBookValues[i]))) {
				LuggageBookCount += parseInt(LuggageBookValues[i]);
			}
		}
		$("#totalnoluggage").html(LuggageBookCount);
	}
	function printReport(){
		//var divElements = document.getElementById("header").innerHTML;
		var divElements = document.getElementById("addticketInv").innerHTML;
        divElements += document.getElementById("viewPassInv").innerHTML;
        divElements += document.getElementById("viewLuggageInv").innerHTML;
        divElements += document.getElementById("viewLuggageInv").innerHTML;
        alert(divElements);
       /*  divElements += document.getElementById("passesHeader").innerHTML;
        divElements += document.getElementById("passesData").innerHTML; */
     
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
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js" type="text/javascript"></script>
<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.min.js" 	type="text/javascript"></script>
</head>
<body > 
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
        	<%!String orgchart=null,orgtype=null;  %>
 			<% orgtype=(String)session.getAttribute("orgtypeid");
			orgchart=(String)session.getAttribute("orgchartid");
			session.removeAttribute("Depotid");
        	%>
			<input type="hidden" name="orgname" id="orgname" value="<s:property value="orgName"/>"> 
			<input type="hidden" name="orgtype" id="orgtypeid" value="<%=orgtype%>"> 
			<div class="row">
				<div class="col-md-12">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i><b>Main Stock  </b>
							</div>
							<div class="actions">
								<a href="#" class="btn green" id="issueTicket" onClick="createTicket()">
									<i class="fa fa-plus"></i> Stock Entry </a>
                                <a href="#" class="btn blue" id="issueTicket" onclick="editTicket()"> <i class="fa fa-pencil"></i>
									Edit
								</a>
                                <a href="#" class="btn red" id="issueTicket" onClick="deleteTicketByCheckBox()"> <i class="fa fa-pencil"></i>
									Delete
								</a>
								<%if(Integer.parseInt(orgtype)!=3){ %>
								<a href="#" class="btn blue" id="issueTicket" onClick="issueTicket()"> <i class="fa fa-pencil"></i>
									Issue(Manual)
								</a>
								<%} %>
								<a href="#" class="btn blue" id="issueTicket" onClick="showVouncher()"> <i></i>
									Voucher
								</a>
								<!-- <a href="#" class="btn red" id="issueTicket" onClick="print()"> <i></i>
									<i class="fa fa-print"></i> Print 
								</a> -->
							<!-- 	<a href="#" class="btn blue" id="issueTicket" onClick="printReport()"> <i></i>
									Print
								</a> -->
							</div>
						</div>
						<div class="portlet-body" id="step1Content">
							<div class="portlet-title">
                       			 <div class="caption" style="font-size:16px">
										<b> Ticket Type</b>
									<br/><br/>
								</div>
							</div>
						<input type="hidden" name="requestType" id="requestType" value="text" />
						<table class="table table-striped table-bordered table-hover" id="addticketInv" style="width:100%" >
							<font color="green" ><b><s:property value="msg" /></b></font>
							<font color="green" ><b><s:property value="ticktmsg" /></b></font>
							<font color="green" ><b><s:property value="tickteditmsg" /></b></font>
							<font color="green" ><b><s:property value="ticktdeletemsg" /></b></font>
							<thead>
								<tr>
									<th style="width:1px;"><a href="#" class="check-box-machine btn grey-cascade">All</a></th>
									<th>Denom</th>
									<th>Created date</th>
									<th>Denom key</th>
									<th>Start number</th>
									<th>End number</th>
									<th>No.of tickets</th>
                                    <th>No.of books</th>
									<th>Amount</th>
									<th>Priority</th>
								</tr>
							</thead>
						</table>
						<br>
						<div class="row">
							<div class="col-md-6">
							 	<label >Total no.of books:</label>
								<span  id="totalticket"></span>					
						    </div>
						    <div class="col-md-6">
								<label >Total value:</label>
								<span id="totalvalue"></span>
							</div>
							<br><br>
						</div>							
					</div>									
					<div class="portlet-body" id="step1Content">
						<div class="portlet-title">
                       		<div class="caption" style="font-size:16px">
								<b> Pass Type</b>
								<br/><br/>
							</div>
						</div>
						<input type="hidden" name="requestType" id="requestType"value="text" />
						<div id="successmsgpass" style="display:none">
			            	<b><p class="success" id="succpass" ></p></b>
		                    	<span>
			                    </span>
		            	</div>
		                <div style="position:relative;overflow:auto;height:300px;">
							<table class="table table-striped table-bordered table-hover" id="viewPassInv" style="width:auto;min-width:1300px">
								<font color="green" ><b><s:property value="passmsg" /></b></font>
								<font color="green" ><b><s:property value="passdeletemsg" /></b></font>
								<font color="green" ><b><s:property value="passeditmsg" /></b></font>
								<thead>
									<tr>
										<th style="width:1px;"><a href="#" class="check-box-pass btn grey-cascade"> All</a>
										<th>Type of pass</th>
										<th>Denom</th>
										<th>Created date</th>
                                        <th>Denom key</th>
										<th>Pass day/month</th> 
                                        <th>Start number</th>
										<th>End number</th>
										<th>No.of passes</th>
                                        <th>No.of books</th>
										<th>Total value</th>
                                        <th>Priority</th> 
								    </tr>
								</thead>
							</table>
						</div>
						<br>
						<div class="row">
							<div class="col-md-6">
						 		<label >Total no.of books:</label>
							 	<span  id="totalnopass"></span>					
						    </div>
						    <div class="col-md-6">
								<label >Total value:</label>
							 	<span id="totalpassvalue"></span>
							</div>
							<br><br>
						</div>	
					</div>		 
					<div class="portlet-body" id="step1Content">
                    	<div>	
							<div class="portlet-title">
                        		<div class="caption" style="font-size:16px">
									<b>Luggage Type</b>
									<br/><br/>
								</div>
							</div>
						</div>							
						<input type="hidden" name="requestType" id="requestType" value="text" />
						<div id="successmsgluggage" style="display:none">
							<b><p class="success" id="succluggage" ></p></b>
		                    	<span>
			                    </span>
		                </div>
						<table class="table table-striped table-bordered table-hover" id="viewLuggageInv">
							<font color="green" ><b><s:property value="luggmsg" /></b></font>
							<font color="green" ><b><s:property value="luggeditmsg" /></b></font>
							<font color="green" ><b><s:property value="luggagedeletemsg" /></b></font>
							<thead>
								<tr>
									<th><a href="#" class="check-box-lugg btn grey-cascade">All</a>
									<th>Created date</th>
									<th>Ticket key</th>
									<th>Start number</th>
									<th>End number</th>
									<th>No.of tickets</th>
                                    <th>No.of books</th>
									<th>Priority</th> 
								</tr>
							</thead>
						</table>
						<br>
						<div class="row">
							<div class="col-md-6">
								<label >Total no.of books:</label>
							 	<span  id="totalnoluggage"></span>					
						    </div>
						</div>			
					</div> 
					<div class="portlet-body" id="step1Content">
                    	<div>	
							<div class="portlet-title">
                        		<div class="caption" style="font-size:16px">
									<b>Waybill Sheet</b>
									<br/><br/>
								</div>
							</div>
						</div>
						<input type="hidden" name="requestType" id="requestType" value="text" />
						<div id="successmsgtsheet" style="display:none">
							<b><p class="success" id="succtsheet" ></p></b>
		                </div>
						<table class="table table-striped table-bordered table-hover" id="viewTSheetInv">
							<font color="green" ><b><s:property value="tsheetmsg" /></b></font>
							<font color="green" ><b><s:property value="tsheetdeletemsg" /></b></font>
							<thead>
								<tr>
									<th><a href="#" class="check-box-tsheet btn grey-cascade">All</a>
									<th>Created date</th>
									<th>Waybill key</th>
									<th>Start number</th>
									<th>End number</th>
									<th>No.of Sheets</th>
                                </tr>
							</thead>
						</table>
					</div> 
				</div> 		
				<div id="print" style="display: none">
				</div>							
			</div>
		</div>
	</div>
</div>
	
<script>
function print(){
	var divElements = document.getElementById("print").innerHTML;
  
 
    var mywindow = window.open("<%=request.getContextPath()%>/inventory/Print.jsp");
   
    mywindow.onload = function() {
    mywindow.document.body.innerHTML=divElements;
            mywindow.document.body.innerHTML=divElements;
            //   document.getElementById("print").style.visibility='';
            mywindow.print();
            mywindow.close();
            
        }
}
	function getPrint(){
		$.ajax({
			type : 'POST',
		    url  :  "<s:url action='viewTicketInv!getForPrint'/>",
		    success: function(data){
		    	//alert(data);
		    	 $("#print").html(data);
		    }
		});
	}
	$(document).ready(function() {
		var ischecked = false;
		$(".check-box-machine").click(function(e) {
		    e.preventDefault();
		    if (ischecked == false) {
		        $("input:checkbox.checkbox1").attr("checked","checked");
		        ischecked = true;
		    } else {
		        $("input:checkbox.checkbox1").removeAttr("checked");
		        ischecked = false;
		    }
		});
		var ischeckedpass = false;
		$(".check-box-pass").click(function(e) {
		    e.preventDefault();
		    if (ischeckedpass == false) {
		        $("input:checkbox.checkboxpass").attr("checked","checked");
		        ischeckedpass = true;
		    } else {
		        $("input:checkbox.checkboxpass").removeAttr("checked");
		        ischeckedpass = false;
		    }
		});
		var ischeckedlugg = false;
		$(".check-box-lugg").click(function(e) {
		    e.preventDefault(); 
		    if (ischeckedlugg == false) {
		        $("input:checkbox.checkboxlugg").attr("checked","checked");
		        ischeckedlugg = true;
		    } else {
		        $("input:checkbox.checkboxlugg").removeAttr("checked");
		        ischeckedlugg = false;
		    }
		});
		var ischeckedtsheet = false;
		$(".check-box-tsheet").click(function(e) {
		    e.preventDefault(); 
		    if (ischeckedtsheet == false) {
		        $("input:checkbox.checkboxtsheet").attr("checked","checked");
		        ischeckedtsheet = true;
		    } else {
		        $("input:checkbox.checkboxtsheet").removeAttr("checked");
		        ischeckedtsheet = false;
		    }
		});
	});
	
	function totalValueCalculate()
	{
		 $.ajax({ 
	           type: "post",
	           url: '<%=request.getContextPath()%>/getTotalNumberOfBooks',
	           success: function(result) {
	        	   var s=result.split("@");
	               document.getElementById('totalticket').innerHTML=s[0];
	               document.getElementById('totalvalue').innerHTML=s[1];
	           }
	       });  
		 $.ajax({
	           type: "post",
	           url: '<%=request.getContextPath()%>/getTotalNumberOfPasses',
	           success: function(result) {
	        	   var s1=result.split("@");
	               document.getElementById('totalnopass').innerHTML=s1[0];
	               document.getElementById('totalpassvalue').innerHTML=s1[1];
	           }
	       });  
		 $.ajax({
	           type: "post",
	           url: '<%=request.getContextPath()%>/getTotalNumberOfLuggagess',
	           success: function(result) {
	               document.getElementById('totalnoluggage').innerHTML=result;
	               
	           }
	       });  
	}
	function createTicket() {
		document.forms[0].action = "issueTicket.action";
		document.forms[0].submit();
	}
	function showVouncher(){
		document.forms[0].action = "viewVouncherlist.action";
		document.forms[0].submit();
	}
	function issueTicket() {
		var chkArray = [];
	
		$(".group-checkable:checked").each(function() {
			chkArray.push($(this).val());
		});
		var selected;
		selected = chkArray.join(',') + ",";
		//alert(selected.length);
		if (selected.length ==1) {
			bootbox.alert("Please select at least one record.");
			return false;
		}else{
			var newdiv = document.createElement('div');
		    newdiv.innerHTML = "<input type='hidden' name='tick' value='"+selected+"'>";
		    document.forms[0].appendChild(newdiv);
		    document.forms[0].method="POST";
			document.forms[0].action = "issueStock.action";
			document.forms[0].submit();
		}			
	
	}
	function deleteTicketByCheckBox(){
		
		var checkedValues = [];
		var count = 0;
		$(".group-checkable:checked").each(function() {
			//alert(checkedValues);
			if(count != 0){
				checkedValues.join(',');
			}
			checkedValues.push($(this).val());
			count++;
		});
		if (!checkedValues.length > 0) {
			bootbox.alert("Please select at least one record.");
			return false;
		}else {
			bootbox.confirm("Are you Sure, you want to delete this record?",function(result) {
				if (result == true) {
					alert("checkedValues..."+checkedValues)
					document.forms[0].action = "deleteCheckedStock.action?tick="+checkedValues;
					//alert(checkedValues);
					//document.forms[0].type='post';
					document.forms[0].submit();
			   }
			});
		}
	}
	function deleteTicket() {
		var chkArray = [];

		$(".group-checkable:checked").each(function() {
			chkArray.push($(this).val());
		});

		var selected;
		selected = chkArray.join(',') + ",";
		if (selected.length > 1) {
		} else {
			bootbox.alert("Please at least  one Checkbox To Delete");
			return false;
		}
		document.forms[0].action = "deleteStock.action?tick="+selected;
		document.forms[0].submit();
	}	
	function editTicket() {
		var chkArray = [];
		$(".group-checkable:checked").each(function() {
			chkArray.push($(this).val());
		});
		var selected;
		selected = chkArray.join(',') + ",";
		if (selected.length > 1) {
		} else {
			bootbox.alert("Please at least  one Checkbox To Edit");
			return false;
		}
		document.forms[0].action = "editStock.action?tick="+selected;
		document.forms[0].submit();
	}	
	$(document).ready(function() {
		window.history.pushState("","", "ticketinv.action");
	});
	</script>
</form>
</body>
</html>