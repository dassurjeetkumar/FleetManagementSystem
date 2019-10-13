<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@page import="com.trimax.its.usermanagement.dao.SMSPageDAO" %>
<%@page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
<script src="https://code.jquery.com/jquery-1.11.3.min.js" type="text/javascript"></script>
<%-- 	<script src="https://cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js" type="text/javascript"></script> --%>
<%-- <script src="https://cdn.datatables.net/responsive/1.0.7/js/dataTables.responsive.min.js" type="text/javascript"></script> --%>
<%-- <script src="https://cdn.datatables.net/fixedheader/3.0.0/js/dataTables.fixedHeader.min.js" type="text/javascript"></script> --%>
<script type="text/javascript">

$(document).ready(function(){
	 
// 	$("#smsfordriver0").hide();
// 	$("#okfordriver0").hide();
	

	
// 	$("#smsforconductor").hide();
// 	$("#okforconductor").hide();
// 	$("#smsfordriverconductor").hide();
// 	$("#okfordriverconductor").hide();
	
	$("#selectAll").click(function(e) {
	    //e.preventDefault();
	    if($("#selectAll").prop('checked')){
	    	$('.driverId').prop('checked', true);
// 	    	$('.conductorId').prop('checked', true);
// 	    	$('.conductordriverId').prop('checked', true);
	    }else{
	    	$('.driverId').prop('checked', false);
// 	    	$('.conductorId').prop('checked', false);
// 	    	$('.conductordriverId').prop('checked', false);
	    }
	    
	
	});
	$("#selectAll1").click(function(e) {
	    //e.preventDefault();
	    if($("#selectAll1").prop('checked')){
	    
 	    	$('.conductorId').prop('checked', true);
// 	    	$('.conductordriverId').prop('checked', true);
	    }else{
	    	//$('.driverId').prop('checked', false);
 	    	$('.conductorId').prop('checked', false);
// 	    	$('.conductordriverId').prop('checked', false);
	    }
	});
	$("#selectAll2").click(function(e) {
	    //e.preventDefault();
	    if($("#selectAll2").prop('checked')){
	   
//  	    	$('.conductorId').prop('checked', true);
	    	$('.conductordriverId').prop('checked', true);
	    	
	    }else{
	    	//$('.driverId').prop('checked', false);
//  	    	$('.conductorId').prop('checked', false);
	    	$('.conductordriverId').prop('checked', false);
	    }
	});
	
 });

var flag = 0;
var flag1 = 0;
var flag2 = 0;
function myFunction(el,group_nm) {
	if(flag==0){
	  
	
	$("#okfordriver"+el+"").show();
	$("#smsfordriver"+el+"").show();
	$('#smsfordriver'+el+'').dataTable({
    	"aaSorting": [
                      [0, 'asc']
                  ],
        "aLengthMenu": [
            [10, 20, 50, 100],
            [10, 20, 50, 100] // change per page values here
        ],
        // set the initial value
       // "iDisplayLength": 10, 
       "bPaginate": false,
        "bProcessing" : true,
        "bServerSide" : true,
        "bDestroy": true,
        "bFilter": false,
        "bInfo": false,
        "sAjaxSource" : "SMSForDriver.action?groupName="+group_nm,
        "sPaginationType": "bootstrap",
        "oLanguage": {
            "sLengthMenu": "_MENU_ records",
            "oPaginate": {
                "sPrevious": "Prev",
                "sNext": "Next"
            }
        },
       "aoColumnDefs": [
            { 'bSortable': false, 'aTargets': [0] },
            { "bSearchable": false, "aTargets": [ 0 ] }
        ]
        
    });            
    jQuery('#smsfordriver'+el+'_wrapper .dataTables_filter input').addClass("form-control input-medium input-inline"); // modify table search input
    jQuery('#smsfordriver'+el+'_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
    
    flag=1;
	}
	else if(flag==1){
		//$("#smsfordriver").hide();
		$("#smsfordriver"+el+"").hide();
	 $("#okfordriver"+el+"").hide();
// 	    document.getElementById("okfordriver"+el+"").style.display="none";
// 	    document.getElementById("smsfordriver"+el+"").style.display="none";
	    flag=0;
	}
} 

function myFunction11(el,ll) {
	if(flag==0){
	  
	
	$("#okfordriver"+ll+"").show();
	$("#smsfordriver"+ll+"").show();
	$('#smsfordriver'+ll+'').dataTable({
    	"aaSorting": [
                      [0, 'asc']
                  ],
        "aLengthMenu": [
            [10, 20, 50, 100],
            [10, 20, 50, 100] // change per page values here
        ],
        // set the initial value
       // "iDisplayLength": 10, 
       "bPaginate": false,
        "bProcessing" : true,
        "bServerSide" : true,
        "bDestroy": true,
        "bFilter": false,
        "bInfo": false,
        "sAjaxSource" : "SMSForDriver.action",
        "sPaginationType": "bootstrap",
        "oLanguage": {
            "sLengthMenu": "_MENU_ records",
            "oPaginate": {
                "sPrevious": "Prev",
                "sNext": "Next"
            }
        },
       "aoColumnDefs": [
            { 'bSortable': false, 'aTargets': [0] },
            { "bSearchable": false, "aTargets": [ 0 ] }
        ]
        
    });            
    jQuery('#smsfordriver'+ll+'_wrapper .dataTables_filter input').addClass("form-control input-medium input-inline"); // modify table search input
    jQuery('#smsfordriver'+ll+'_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
    
    flag=1;
	}
	else if(flag==1){
		//$("#smsfordriver").hide();
// 	    document.getElementById("okfordriver"+el+"").style.display="none";
// 	    document.getElementById("smsfordriver"+el+"").style.display="none";
     $("#smsfordriver"+ll+"").hide();
	 $("#okfordriver"+ll+"").hide();
	    flag=0;
	}
} 



function myFunction1() {
	if(flag1==0){
	$("#okforconductor").show();
	$("#smsforconductor").show();
	$('#smsforconductor').dataTable({
 	"aaSorting": [
                   [0, 'asc']
               ],
     "aLengthMenu": [
         [10, 20, 50, 100],
         [10, 20, 50, 100] // change per page values here
     ],
     // set the initial value
    // "iDisplayLength": 10, 
    "bPaginate": false,
     "bProcessing" : true,
     "bServerSide" : true,
     "bDestroy": true,
     "bFilter": false,
     "bInfo": false,
     "sAjaxSource" : "SMSForConductor.action",
     "sPaginationType": "bootstrap",
     "oLanguage": {
         "sLengthMenu": "_MENU_ records",
         "oPaginate": {
             "sPrevious": "Prev",
             "sNext": "Next"
         }
     },
    "aoColumnDefs": [
         { 'bSortable': false, 'aTargets': [0] },
         { "bSearchable": false, "aTargets": [ 0 ] }
     ]
     
 });            
 jQuery('#smsforconductor_wrapper .dataTables_filter input').addClass("form-control input-medium input-inline"); // modify table search input
 jQuery('#smsforconductor_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown

 flag1=1;
	}
	else if(flag1==1){
		$("#okforconductor").hide();
	    document.getElementById("smsforconductor").style.display="none";
	    flag1=0;
	}

} 

function myFunction2() {
	if(flag2==0){
	
	$("#okfordriverconductor").show();
	$("#smsfordriverconductor").show();
	$('#smsfordriverconductor').dataTable({
 	"aaSorting": [
                   [0, 'asc']
               ],
     "aLengthMenu": [
         [10, 20, 50, 100],
         [10, 20, 50, 100] // change per page values here
     ],
     // set the initial value
    // "iDisplayLength": 10, 
    "bPaginate": false,
     "bProcessing" : true,
     "bServerSide" : true,
     "bDestroy": true,
     "bFilter": false,
     "bInfo": false,
     "sAjaxSource" : "SMSForConductorDriver.action",
     "sPaginationType": "bootstrap",
     "oLanguage": {
         "sLengthMenu": "_MENU_ records",
         "oPaginate": {
             "sPrevious": "Prev",
             "sNext": "Next"
         }
     },
    "aoColumnDefs": [
         { 'bSortable': false, 'aTargets': [0] },
         { "bSearchable": false, "aTargets": [ 0 ] }
     ]
     
 });            
 jQuery('#smsfordriverconductor_wrapper .dataTables_filter input').addClass("form-control input-medium input-inline"); // modify table search input
 jQuery('#smsfordriverconductor_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
 
 
 flag2=1;
	}
	else if(flag2==1){
		$("#okfordriverconductor").hide();
	    document.getElementById("smsfordriverconductor").style.display="none";
	    flag2=0;
	}
} 

function sendSms(){
	
	var sub=$("#subject").val();
	var des=$("#description").val();
	var driver=$("#selectAll").val(); 
	var cond=$("#selectAll1").val(); 
	var dri_cond=$("#selectAll2").val();
	alert(driver);
	alert(cond);
	
	
}

</script>

</head>
<body>

<form action="SendSMSForemployee.action" class="form-horizontal" method="POST"  name ="" >
<div class="page-content-wrapper">
	<div class="page-content">
		<div class="tab-content">
		
		<b>
								<font color="green"> <s:actionmessage/></font>
								<span id="errorMsg" style="color:red;word-wrap: break-word;" ><s:actionerror/></span>
							</b>
		<div class="row">
				<div class="col-md-5 ">
				
				<div class="portlet box grey-cascade">
				
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i>SMS
						</div>
						
					</div>
					
					<div class="portlet-body form">
					
								
								<div class="form-group">
													<label class="col-md-3 control-label">Subject:<span class="required"> * </span></label>
													<div class="col-md-4">

                                                      <input class="form-control input-lg" placeholder=""
													id="subject" name="subject" type="text"
													/>
												
												</div>
												
												
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">Description:</label>
													<div class="col-md-4">
								  
										       <input class="form-control input-lg" placeholder="" id="description" name="description" type="text" /> 
												
												</div>
											  </div>
						            
														
								
										<div class="form-actions fluid">
											<div class="col-md-offset-3 col-md-9">
												<button type="submit" class="btn blue"  >send sms</button>
												<button class="btn default" type="button" onclick="cancelform()">send email</button>
														</div>
										</div>
										
									
						</div>
						</div></div>
						<div class="col-md-7 ">
						<div class="portlet box grey-cascade">
						<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i>Employee Type
						</div>
						</div>
					</div>
					
					<%
					
					SMSPageDAO dao = new SMSPageDAO();
					List<String> group_list=dao.getEmpGroupDetails();
					
					System.out.println("size of====="+group_list.size());
					
					%>
					
						<table class="table" border="1">
							<thead>
							<%
							for(int i=0;i<1;i++){
								
							String group_name=group_list.get(i);
							System.out.println("group_name==="+group_name);
						
							
							
							%>
								<tr>
									<th><%= group_name%> </th>
									<th ><button type="button" class='btn blue' onclick="myFunction(<%=i%>,'<%= group_name%>')" ><b>+</b></button><button type="button" class='btn blue' onclick="myFunction(<%=i%>,'<%= group_name%>')" ><b>-</b></button></th>
									</tr>
<!-- 									<tr style="" id="myDIV"> -->
                                     <tr>
									<th>
<!-- 									<div class="portlet-body" id="vehicleMaster"> -->
						
									<table class="table table-striped table-bordered table-hover"
										id="smsfordriver<%=i%>" style=" display:none">
										<thead>
											<tr>
												<th><input type='checkbox' id='selectAll'  onclick="select(this.value)"/>&nbsp;&nbsp;</th>
												<th>Name</th>
												<th>Mobile Number</th>
												<th>Depot</th>
											
											</tr>
										</thead>
										
									</table>
<!-- 					</div> -->
									</th>
									</tr>
									
									<tr>
									  <th id="okfordriver<%=i%>" style="text-align: center; display:none"><button type="button" class='btn blue' onclick="myFunction11(this,<%=i%>)" style="Position: static; text-align:center;"><b>OK</b></button></th>
									</tr>
									<%} %>
									<tr>
<!-- 									<th>Conductor </th> -->
<!-- 									<th ><button type="button" class='btn blue' onclick="myFunction1()" ><b>+</b></button></th> -->
<!-- 									</tr> -->
 <!-- 									<tr style="" id="myDIV"> --> 
<!--                                      <tr> -->
<!-- 									<th> -->
 <!-- 									<div class="portlet-body" id="vehicleMaster"> --> 
						
<!-- 									<table class="table table-striped table-bordered table-hover" -->
<!-- 										id="smsforconductor"> -->
<!-- 										<thead> -->
<!-- 											<tr> -->
<!-- 												<th><input type='checkbox' id='selectAll1'  onclick="select(this.value)"/>&nbsp;&nbsp;</th> -->
<!-- 												<th>Name</th> -->
<!-- 												<th>Mobile Number</th> -->
<!-- 												<th>Depot</th> -->
											
<!-- 											</tr> -->
<!-- 										</thead> -->
										
<!-- 									</table> -->
 <!-- 					</div> --> 
<!-- 									</th> -->
<!-- 									</tr> -->
<!-- 									<tr> -->
<!-- 									  <th id="okforconductor" style="text-align: center;"><button type="button" class='btn blue' onclick="myFunction(this)" style="position: static; text-align:center;"><b>OK</b></button></th> -->
<!-- 									</tr> -->
							
<!-- 									<tr> -->
<!-- 									<th>DriverConductor </th> -->
<!-- 									<th ><button type="button" class='btn blue' onclick="myFunction2()" ><b>+</b></button></th> -->
<!-- 									</tr> -->
 <!-- 									<tr style="" id="myDIV"> --> 
<!--                                      <tr> -->
<!-- 									<th> -->
 <!-- 									<div class="portlet-body" id="vehicleMaster"> --> 
						
<!-- 									<table class="table table-striped table-bordered table-hover" -->
<!-- 										id="smsfordriverconductor"> -->
<!-- 										<thead> -->
<!-- 											<tr> -->
<!-- 												<th><input type='checkbox' id='selectAll2'  onclick="select(this.value)"/>&nbsp;&nbsp;</th> -->
<!-- 												<th>Name</th> -->
<!-- 												<th>Mobile Number</th> -->
<!-- 												<th>Depot</th> -->
											
<!-- 											</tr> -->
<!-- 										</thead> -->
										
<!-- 									</table> -->
 <!-- 					</div> --> 
<!-- 									</th> -->
<!-- 									</tr> -->
<!-- 									<tr> -->
<!-- 									  <th id="okfordriverconductor" style="text-align: center;"><button type="button" class='btn blue' onclick="myFunction(this)" style="position: static; text-align:center;"><b>OK</b></button></th> -->
<!-- 									</tr> -->
							
								
								
							</thead>
							
						</table>
					</div>
										</div>
						
					</div>
				</div>
			</div>
</form>	
</body>
</html>