<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

 <script src="assets/global/plugins/jquery-1.11.0.min.js"
	type="text/javascript"></script>
	<script>
	function getpassdetails(date){
		$('#studentInfo').show();
		var date1=date.split("-");
		var strdate=date1[2]+"-"+date1[1]+"-"+date1[0];
		
		 $('#studentInfo').dataTable({
				"aLengthMenu" : [ [ 10, 50, 100, -1 ], [ 10, 50, 100, "All" ] // change
				
				],
				 "sAjaxSource" : "AjaxstudentDetailview.action?startdate="+strdate,
				"sPaginationType" : "bootstrap",
				"bDestroy" : true,

				 "oLanguage": {
	                    "sLengthMenu": "_MENU_ records",
	                    "oPaginate": {
	                        "sPrevious": "Prev",
	                        "sNext": "Next"
	                    }
	                },
	               "aoColumnDefs": [
	                    { 'bSortable': false, 'aTargets': [0] },
	                    { "bSearchable": false, "aTargets": [ 0 ]} ,
	                    { "sClass": "url", "aTargets": [ 3 ] },
	                ],
			});
			jQuery(
					'#studentInfo_wrapper .dataTables_filter input')
					.addClass("form-control input-small input-inline"); // modify
			// table
			jQuery(
					'#studentInfo_wrapper .dataTables_length select')
					.addClass("form-control input-xsmall input-inline"); // modify         

	}
</script>
<script>

function studentEdit(){
	
	var cnt = $(":checkbox:checked").length;
	var val;
	var id;
	var type;
	if (cnt == 0) {
		bootbox.alert("Please Select Checkbox To Edit");
	} else if (cnt > 1) {
		bootbox.alert("Please Select One Checkbox To Edit");
	} else {
		$("input[type='checkbox']:checked").each(
				function() {

					val = $(this).val();
//							val=$("#id").val();
//						id=$("#checkbox").val();
//						alert(val);
					
				});
		document.forms[0].action = 'editstudentpassdetails.action?value='+val;
		document.forms[0].submit();
	

				
}
}



function getData(){
		var strdate=$("#startdate").val();
		var enddate=$("#enddate").val();
		if(strdate=='' || strdate==null){
			alert("please select Form Date");
		}else if(enddate=='' || enddate==null){alert("please select End Date");}
		else {
	$('#studentpasscount').attr("style", "display:''");
	
    table = $('#studentpasscountTable');
    var oTable = table.dataTable({
    	"aLengthMenu" : [ [ 5,10, 15, 20, -1 ], [ 5,10, 15, 20, "All" ] // change
		// per
		// page
		// values
		// here
		],
		// set the initial value
		"iDisplayLength" : 5,
		"sAjaxSource" : "ajaxgetstudentcount.action?startdate="+strdate+"&enddate="+enddate,
		"sPaginationType" : "bootstrap",
		"bDestroy" : true,
		"bSort" : true,
		"bFilter" : true,
		"bSelect" : true,
		"bPaginate" : false,
		"bInfo": true,
		"oLanguage" : {
			"sLengthMenu" : "_MENU_ records",
			"oPaginate" : {
				"sPrevious" : "Prev",
				"sNext" : "Next"
			}
		},
		"aoColumnDefs" : [ {
			'bSortable' : false,
			'aTargets' : [ 0 ]
		} ]
	});
//alert("hi");
	jQuery('#studentpasscountTabletable_wrapper .dataTables_filter input').addClass(
			"form-control input-xsmall input-inline"); // modify table
	// search input
	jQuery('#studentpasscountTabletable_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");	
		}
}
</script>
<%-- <script type="text/javascript">
$(document).ready(function() {
getdata();
getheader();
});
</script> --%>
<script>

function printDiv() {     
	  document.getElementById("studentdetails").style.visibility='visible';  
	     document.getElementById("header").style.display='block';
	     document.getElementById("header").style.visibility='visible'; 
	    
	     
	     var divElements = document.getElementById("header").innerHTML;
	     divElements+= document.getElementById("studentdetails").innerHTML;
	     var mywindow = window.open("<%=request.getContextPath()%>/Print1.jsp");
	     mywindow.onload = function() {
	         mywindow.document.body.innerHTML=divElements;
	         mywindow.document.body.innerHTML=divElements;
	     //	mywindow.document.body.innerHTML = divElement;
	         //   document.getElementById("print").style.visibility='';
	         mywindow.print();
	         mywindow.close();
	     }
	     document.getElementById("header").style.display = 'none';
			document.getElementById("header").style.visibility = 'hidden';
            
}
</script>
	<script type="text/javascript">
    function tabletoExcel(btnExport){     
    	/*  document.getElementById("mapshow").style.visibility='hidden'; 
    		$(".mapClass").hide(); */
   	     
    	     var inputHTML = "<table border='1'>";
             inputHTML += "<tr>";
             inputHTML += "<th  align='center'colspan='8'>Bangalore Metropolitan Transport Corporation</th>";
             inputHTML += "</tr>";
            inputHTML += "<tr>";
             inputHTML += "<th  align='Center'colspan='8'>Student Pass Details</th>";
             inputHTML += "</tr>";
           
             inputHTML += "</table>";
    		   document.getElementById("studentdetails").style.visibility='visible';  
    	     document.getElementById("header").style.display='block';
    	     document.getElementById("header").style.visibility='visible';     
    	     var divElements = inputHTML+document.getElementById("header").innerHTML;
    	     divElements+= document.getElementById("studentdetails").innerHTML;
     
     var noOfTableExist = 0;
     try{
 		$("#studentdetailsTable table").each(function(){
 			noOfTableExist++;
 		});
 		
 		console.log("Total no of tables : " + noOfTableExist);
 		/* If two table exist  then remove the last table on print click*/
 		/* if(noOfTableExist >= 2){
 			divElements = divElements.substring(0, divElements.indexOf("</table>") + 8) + "</div></div>";
 		} */
 	}catch(err){
 	    console.log("ExceptionCaught : " + err);
 	}
    
    var downloadLink = document.createElement("a");
    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(divElements);
    downloadLink.download = "studentdetails.xls";
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
}
	</script>
	 <script>
	function getheader() {
		
		document.getElementById("startdat").innerHTML = new Date().toJSON().slice(0,10);
		}
function studentdata(){
		
		var id=$("#studentid").val();
		
			document.forms[0].action = 'editstudentpassdetails.action?value='+id;
			document.forms[0].submit();
		

					
	
	}
 </script>

	


</head>
<body>
<div class="page-content-wrapper">
		<div class="page-content">
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div class="modal fade" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">Modal title</h4>
						</div>
						<div class="modal-body">
							 Widget settings form goes here
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue">Save changes</button>
							<button type="button" class="btn default" data-dismiss="modal">Close</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			
			<!-- END STYLE CUSTOMIZER -->
			<!-- BEGIN PAGE HEADER-->
			
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
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>Student Pass Details
							</div>
						</div>
						
						<div class="portlet-body">
 <B><font color="green"><s:actionmessage /></font></B>
                            <s:if test="hasActionErrors()">
                                <div class="alert alert-danger">
                               
                                    <button class="close" data-close="alert"></button>
                                <span id="errorMsg" style="color:red;word-wrap: break-word;"><s:actionerror/></span>
                                </div>
                                </s:if>
                               

                     <form action="" method="post" class="form-horizontal">
								    <div class="form-body"> 							
									<div class="form-group">
							  <label class="control-label col-md-3">Student RollNo :<font
										color="red">*</font></label>
								<div class="col-md-3">
								
								<input type="text" class="form-control" id="studentid"
													name="studentid" maxlength="30"></input>
								</div></div>
								
						
											
								
 					
						<!-- end -->
						
					
                     </div>
                     </form>
				
					 <div align='center'>
					<button type="button" class="btn blue" onClick="studentdata()">Submit</button>
					
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
				<!-- <div id="ticketconsuptionid"></div>	 -->		
			</div>
			
			<!-- END PAGE CONTENT-->
			
		
	
		
	
	
				<div class="portlet-body" id="studentpasscount" style="display: none; visibility: hidden;">
						
						
                           
                                   <table class="table table-striped table-bordered table-hover"
								id="studentpasscountTable">
								
								<thead>
									<tr>
									<!-- 	<th style="width1: 8px;"></th> -->
										<th>SN</th>
										<th>Date</th>
										<th>Count</th>
												     

									</tr>
								</thead>

							</table>
                         
							</div>
							</div></div></div></div></div>
			<div id="header" class="portlet-body" style="display: none; visibility: hidden;">
 <h3 style="margin-left:350px;">Student Pass Count</h3>
<br />
<b><font size="2"><label>Date: </label></font><font size="2"><b><span id="startdat" style="text-align:left;margin-left:50px;"></span></font></b>

</div>
			<div style="display: none;" id="mymodal11" class="modal fade"
						tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
						aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content" style="width: 1500px; height: 500px;"
								align="justify">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true"></button>
									<h4 id="vehicleno123" class="modal-title"></h4>
								</div>
								<div>
									<p>
									<div class="portlet box white ">
										<div>
											<input type="hidden" name="requestType" id="requestType" />
											<div>
												<div class="portlet solid white">
													<div class="form-group">
														<label class="col-md-3 control-label"></label>
													</div>
													<span><input type='button' class='btn blue' id='button1' onclick='printDiv()' value='Print' /></span>&nbsp;
					<button type="button" class="btn blue" onClick="tabletoExcel()">Export</button>
					<button type="button" class="btn blue" onClick="studentEdit()">Edit</button>
									<div id="studentdetails">		
 
							<table class="table table-striped table-bordered table-hover"
								id="studentInfo" style="display: none">
								<thead>
									<tr>	
									<th></th>
									<th>Sr No.</th>
										<th>Student Roll No</th>
										<th>Student Name</th>
										<th>Father Name</th>
										<th>Mother Name</th>
										<th>School Address</th>
										<th>School Pincode</th>
										<th>School Name</th>
										<th>Standard</th>
										<th>Gender</th>
										<th>Image</th>
										<th>Caste</th>
										<th>DOB</th>
										
										<th>Residence Address</th>
										<th>Mobile No</th>
										<th>Email</th>
										<th>From</th>
										<th>To</th>
										<th>Via</th>
										<th>Change Over Stop</th>
										
										
									</tr>
								</thead>
							</table>	</div>
												</div>
											</div>
										</div>
									</div>
									</p>
								</div>

							</div>
						</div>
					</div>
								
	</body>
	</html>