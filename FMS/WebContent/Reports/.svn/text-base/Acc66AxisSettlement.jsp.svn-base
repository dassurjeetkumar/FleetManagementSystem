  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
 <script src="https://code.jquery.com/jquery-1.11.3.min.js" type="text/javascript"></script>
	<script src="https://cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/responsive/1.0.7/js/dataTables.responsive.min.js" type="text/javascript"></script>
<script>

function getData(){
	//$("acc66axisnotsett").empty();
	$('#accamountsettlement').attr("style", "display:''");
	var dd1=$("#startdate").val();
    table = $('#accamountsettlementtable');
    var oTable = table.dataTable({
    	"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
		// per
		// page
		// values
		// here
		],
		// set the initial value
		"iDisplayLength" : 5,
		"sAjaxSource" : "ajaxgetaccaxis.action?startdate="+dd1,
		"sPaginationType" : "bootstrap",
		"bDestroy" : true,
		"bSort" : true,
		"bFilter" : true,
		"bSelect" : false,
		"bPaginate" : false,
		"bInfo": false,
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

	jQuery('#accamountsettlementtable_wrapper .dataTables_filter input').addClass(
			"form-control input-xsmall input-inline"); // modify table
	// search input
	jQuery('#accamountsettlementtable_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");	
}

function getDatanotsamedate(){
	//$("acc66axisnotsett").empty();
	$('#pastsettlement').attr("style", "display:''");
	var dd1=$("#startdate").val();
    table = $('#pastsettlementtable');
    var oTable = table.dataTable({
   	 "aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
		// per
		// page
		// values
		// here
		],
		// set the initial value
		"iDisplayLength" : 5,
		"sAjaxSource" : "ajaxgetaccaxissettlediffdate.action?startdate="+dd1,
		"sPaginationType" : "bootstrap",
		"bDestroy" : true,
		"bSort" : true,
		"bFilter" : true,
		"bSelect" : false,
		"bPaginate" : false,
		"bInfo": false,
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

	jQuery('#pastsettlementtable_wrapper .dataTables_filter input').addClass(
			"form-control input-xsmall input-inline"); // modify table
	// search input
	jQuery('#pastsettlementtable_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");	
}
</script>
<script>
<%-- function notsettleaccamt(){
	
  	var dd1=$("#startdate").val();
  	$("#ticketconsuptionid2").empty();
			// $('#pageLoader').show(); 
			 
    $.ajax({
    
        type: "post",
        url: '<%=request.getContextPath()%>/ajaxgetnotsettleaccamt.action?startdate='+dd1,
        success: function(result) {
        	//$('#pageLoader').hide();
            document.getElementById('ticketconsuptionid2').innerHTML=result;
            fixHeader();
        }
    });

} --%>
function notsettleaccamt(){
	//$("acc66axisnotsett").empty();
	$('#notettlement').attr("style", "display:''");
	var dd1=$("#startdate").val();
    table = $('#notettlementtable');
    var oTable = table.dataTable({
   	 "aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
		// per
		// page
		// values
		// here
		],
		// set the initial value
		"iDisplayLength" : 5,
		"sAjaxSource" : "ajaxgetnotsettleaccamt.action?startdate="+dd1,
		"sPaginationType" : "bootstrap",
		"bDestroy" : true,
		"bSort" : true,
		"bFilter" : true,
		"bSelect" : false,
		"bPaginate" : false,
		"bInfo": false,
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

	jQuery('#notettlementtable_wrapper .dataTables_filter input').addClass(
			"form-control input-xsmall input-inline"); // modify table
	// search input
	jQuery('#notettlementtable_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");	
}
</script>
<script>
function hflgetdepotwisenotsettleamt(depotid,startdate){
	//$("acc66axisnotsett").empty();
	$('#acc66axisnotsett').attr("style", "display:''");

    table = $('#notsettledata');
    var oTable = table.dataTable({
   	 "aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
		// per
		// page
		// values
		// here
		],
		// set the initial value
		"iDisplayLength" : 5,
		"sAjaxSource" : "ajaxgetdepotwisenotsettlement.action?depotid="+depotid+"&startdate="+startdate,
		"sPaginationType" : "bootstrap",
		"bDestroy" : true,
		"bSort" : true,
		"bFilter" : true,
		"bSelect" : false,
		"bPaginate" : false,
		"bInfo": false,
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

	jQuery('#notsettledata_wrapper .dataTables_filter input').addClass(
			"form-control input-xsmall input-inline"); // modify table
	// search input
	jQuery('#notsettledata_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");	
}
</script>
<script>
function getdepotwisereport(depotid,startdate){
	$("acc66axisdif").empty();
		$('#acc66axisdif').attr("style", "display:''");
		$("comparisonData").empty();
		     table = $('#comparisonData');
		     var oTable = table.dataTable({
		    	 "aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
		 		// per
		 		// page
		 		// values
		 		// here
		 		],
		 		// set the initial value
		 		"iDisplayLength" : 5,
		 		"sAjaxSource" : "ajaxgetdepotwiseaccaxis.action?depotid="+depotid+"&startdate="+startdate,
		 		"sPaginationType" : "bootstrap",
		 		"bDestroy" : true,
				"bSort" : false,
				"bFilter" : true,
				"bSelect" : false,
				"bPaginate" : false,
				"bInfo": false,
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

		 	jQuery('#comparisonData_wrapper .dataTables_filter input').addClass(
		 			"form-control input-xsmall input-inline"); // modify table
		 	// search input
		 	jQuery('#comparisonData_wrapper .dataTables_length select').addClass(
		 			"form-control input-xsmall input-inline");
	

	  }

</script>
<script>
function getdepotnotsettamt(depotid,startdate){
	$("acc66axisdif").empty();
		$('#acc66axisdif').attr("style", "display:''");
		$("comparisonData").empty();
		     table = $('#comparisonData');
		     var oTable = table.dataTable({
		    	 "aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
		 		// per
		 		// page
		 		// values
		 		// here
		 		],
		 		// set the initial value
		 		"iDisplayLength" : 5,
		 		"sAjaxSource" : "ajaxgetdeponotsettamt.action?depotid="+depotid+"&startdate="+startdate,
		 		"sPaginationType" : "bootstrap",
		 		"bDestroy" : true,
				"bSort" : false,
				"bFilter" : true,
				"bSelect" : false,
				"bPaginate" : false,
				"bInfo": false,
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

		 	jQuery('#comparisonData_wrapper .dataTables_filter input').addClass(
		 			"form-control input-xsmall input-inline"); // modify table
		 	// search input
		 	jQuery('#comparisonData_wrapper .dataTables_length select').addClass(
		 			"form-control input-xsmall input-inline");
	

	  }

</script>
<script>
function getdepotwisereportnotsameday(depotid,startdate){
	//$("acc66axisdif").empty();
	$('#acc66axisdif').attr("style", "display:''");
	//$("comparisonData").empty();
	     table = $('#comparisonData');
	     var oTable = table.dataTable({
	    	 "aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
	 		// per
	 		// page
	 		// values
	 		// here
	 		],
	 		// set the initial value
	 		"iDisplayLength" : 5,
	 		"sAjaxSource" : "ajaxgetdepotwiseaccaxisnotsameday.action?depotid="+depotid+"&startdate="+startdate,
	 		"sPaginationType" : "bootstrap",
	 		"bDestroy" : true,
			"bSort" : false,
			"bFilter" : true,
			"bSelect" : false,
			"bPaginate" : false,
			"bInfo": false,
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

 	 	jQuery('#comparisonData_wrapper .dataTables_filter input').addClass(
	 			"form-control input-xsmall input-inline"); // modify table
	 	// search input
	 	jQuery('#comparisonData_wrapper .dataTables_length select').addClass(
	 			"form-control input-xsmall input-inline");


  }
</script>
<script>
		
function printDiv() {     
	 
	 document.getElementById("accamountsettlementtable").style.visibility='visible'; 
	 document.getElementById("pastsettlementtable").style.visibility='visible';  
    document.getElementById("notettlementtable").style.visibility='visible';  

   document.getElementById("headerr").style.display='block';
   document.getElementById("headerr").style.visibility='visible';     
   var divElements = document.getElementById("headerr").innerHTML;
   divElements+="<font color=red>"+"Acc Amout Settlement Details" +"</font>";
   divElements+= "<table>"+document.getElementById("accamountsettlementtable").innerHTML+"</table>";
   divElements+= "<br><br>";
   divElements+="<font color=red>"+"Old Settlement Report" +"</font>";
   divElements+= "<table>"+document.getElementById("pastsettlementtable").innerHTML+"</table>";
   divElements+= "<br><br>";
   divElements+="<font color=red>"+"Details of Unsettlement Amount" +"</font>";
   divElements+= "<table>"+document.getElementById("notettlementtable").innerHTML+"</table>";

   
  //divElements += document.getElementById("schdeviation").innerHTML;

   var mywindow = window.open("<%=request.getContextPath()%>/Print.jsp");
  
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
 <script>
	function getheader() {
		
	
			var startdate =$("#startdate").val();
			


			$.ajax({
		           type: "get",
		           url: '<%=request.getContextPath()%>/getacc66HeaderData?startdate='+startdate,
		           success: function(result) {
		        	   var obj = jQuery.parseJSON(result);
		        	   console.log("==>"+obj["bbData"][0]);
		        	    	   document.getElementById('startdat').innerHTML=obj["bbData"][0];
		        	
						
		        	   
		           }
		       });}
 </script>
<div class="page-content-wrapper">
	<div class="page-content">
		<div id="pageLoader" class="modal fade in" tabindex="-1" role="dialog" aria-labelledby="myModalLabel3" aria-hidden="false">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						
							<img src="assets/images/loader1.gif" align="top"
								style="margin-left: 100px;" />
						<br>
						<p style='text-align: center'>Please Wait........<br>
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
								<i class="fa fa-globe"></i>Daily Recon Report 
							</div>
						</div>
						
						<div class="portlet-body">
						

							<!-- 						start -->

<!-- <table> -->
                   <form action="" method="post" class="form-horizontal">
                           <div class="form-body">
							
							 <div class="form-group">
							  <label class="control-label col-md-3">Date:</label>
								<div class="col-md-3">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" data-date-end-date="-2d"> <!-- data-date-start-date="+0d" -->
								<input id="startdate" class="form-control" type="text" readonly="" size="16" name="rateMaster.effectiveStartDate" placeholder="Plese Pick From Date"
								value="<s:property value='rateMaster.effectiveStartDate' />"
								>
								<span class="input-group-btn">
								<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
								</button>
								</span>
								<script>
// 										var curr_date=new Date().toJSON().slice(0,10);
//                                         curr_date=curr_date.split("-");
//                                         curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
//                                         var dtval=document.getElementById('startdate').value;	
                                        
//                                         if(dtval==''){
//                                         $('#startdate').val(curr_date);
//                                         }
								</script>
								</div>
								</div></div>
						  
					<%-- 		 <div class="form-group">
							  <label class="control-label col-md-3">To Date:</label>
								<div class="col-md-3">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years"  data-date-end-date="0d"> 
								<input id="enddate" class="form-control" type="text" readonly="" size="16" name="rateMaster.effectiveStartDate" placeholder="Plese Pick To Date"
								value="<s:property value='rateMaster.effectiveStartDate' />"
								>
								<span class="input-group-btn">
								<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
								</button>
								</span>
								<script>
// 										var curr_date=new Date().toJSON().slice(0,10);
//                                         curr_date=curr_date.split("-");
//                                         curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
//                                         var dtval=document.getElementById('enddate').value;	
                                        
//                                         if(dtval==''){
//                                         $('#enddate').val(curr_date);
//                                         }
								</script>
								</div>
								</div></div> --%>
							
								
						
						<!-- end -->
							
							
						 <script type="text/javascript"> 
 	                     function tabletoExcel(btnExport){
 	                    	 document.getElementById("accamountsettlementtable").style.visibility='visible'; 
 	                   	 document.getElementById("pastsettlementtable").style.visibility='visible';  
 	                       document.getElementById("notettlementtable").style.visibility='visible';  
 	                 	     document.getElementById("headerr").style.display='block';
 	                 	     document.getElementById("headerr").style.visibility='visible';     
 	                 	     var divElements = document.getElementById("headerr").innerHTML;
 	                 	   divElements+="<font color=red>"+"Acc Amout Settlement Details" +"</font>";
 	                 	   divElements+= "<table>"+document.getElementById("accamountsettlementtable").innerHTML+"</table>";
 	                 	   divElements+= "<br><br>";
 	                 	   divElements+="<font color=red>"+"Old Settlement Report" +"</font>";
 	                 	   divElements+= "<table>"+document.getElementById("pastsettlementtable").innerHTML+"</table>";
 	                 	   divElements+= "<br><br>";
 	                 	   divElements+="<font color=red>"+"Details of Unsettlement Amount" +"</font>";
 	                 	   divElements+= "<table>"+document.getElementById("notettlementtable").innerHTML+"</table>";
 	                  
 	                  var noOfTableExist = 0;
 	                  try{
 	              		$("#tripstatus table").each(function(){
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
 	                 downloadLink.download = "Daily Recon Report.xls";
 	                 document.body.appendChild(downloadLink);
 	                 downloadLink.click();
 	                 document.body.removeChild(downloadLink);
 	                  }</script>	
					
							
					 <div class="form-group">
					 <label class="control-label col-md-3"></label>
                     <div class="col-md-3" id="">
                     </div>
                     </div>
                     </div>
                     </form>
                     <div align='center'>
					<button type="submit" class="btn green" onClick="getData(),getDatanotsamedate(),notsettleaccamt(),getheader()">Submit</button> 
					<button type="button" id="btnExport" class="btn green" onclick="tabletoExcel();"> EXPORT </button>
					<button type="button" id="btnExport" class="btn green" onclick="printDiv();"> Print </button>
               <%--     <span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span> --%><%-- &nbsp;<span> --%>
<%-- 					 <input type='button' class='btn green' id='button1' name='rawprint' onclick='rawPrint()' value='RawPrint' /></span></div> --%>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
					<div id="ticketconsuptionid"></div>
					<div id="ticketconsuptionid1"></div>
                    <div id="ticketconsuptionid2"></div>
			
			
			<!-- END PAGE CONTENT-->
	
	
					
					 <div id="header" class="portlet-body">
										
									<br />
	<div style="display: none;" id="mymodal1" class="modal fade"
						tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
						aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content" style="width: 900px; height: 500px; overflow: scroll;"
								align="left">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true"></button>
									<h4 id="accdifff" class="modal-title"></h4>
								</div>
								<div>
									
									<div class="portlet box white ">
										<div>
											<input type="hidden" name="requestType" id="requestType" />
											<div>
												<div class="portlet solid white">
													<div class="form-group">
														<label class="col-md-3 control-label"></label>
													</div>
													<div class="portlet-body" id="acc66axisdif"
														style="display: none">
														<div style="text-align:left">
														<table
															class="table table-striped table-bordered table-hover"
															id="comparisonData">
															<thead>
													<tr>
													<th>Sr<br>No</th>
													<th>Depot</th>
													<th>Waybill<br>No</th>
													<th>Schedule<br>No</th>
													<th>Route<br>No</th>
													<th>Trip<br>No</th>
													<th>Etm<br>No</th>
													<th>Ticket<br> Date</th>
													<th>Its<br>Settlement</th>
												<!-- 	<th>Its Ticket no</th> -->
													<th>Axis<br> Waybill</th>
													<th>Axis<br> Ticket</th>
													<th>ACC-66<br>Amount</th>
													<th>Its<br>Amount</th>
													<!-- <th>Its Convinence Fee no</th> -->
													<th>Axis<br>Amount</th>
			<th>Axis<br>Convenience</th>
			<th>Differnce</th>
			<th>Status</th>
			<th>ACC-66<br>Date</th>
			<th>Axis<br> Settlement</th>
				</tr>
																
															</thead>
														</table>
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
					
										
					 <div id="header" class="portlet-body">
										
									<br />
	<div style="display: none;" id="mymodal11" class="modal fade"
						tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
						aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content" style="width: 900px; height: 500px; overflow: scroll;"
								align="justify">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true"></button>
									<h4 id="accdifff" class="modal-title"></h4>
								</div>
								<div>
									
									<div class="portlet box white ">
										<div>
											<input type="hidden" name="requestType" id="requestType" />
											<div>
												<div class="portlet solid white">
													<div class="form-group">
														<label class="col-md-3 control-label"></label>
													</div>
													
                                                   
                                                   	<div class="portlet-body" id="acc66axisnotsett"
														style="display: none">
														<div style="text-align:center">
														<table
															class="table table-striped table-bordered table-hover"
															id="notsettledata">
															<thead>
													<tr>
													<th>Sr No</th>
													<th>Depot</th>
													<th>waybill no</th>
													
													<th>Etm no</th>
													<th>ACC Amount</th>
													<th>Its Amount</th>
													<th>ACC Date</th>
													
													<th>Its Date</th>
													
													
			
				</tr>
																
															</thead>
														</table>
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
					
					
					<div class="portlet-body" id="accamountsettlement"
														style="display: none">
														<b><font color="red">ACC Amout Settlement Details</font></b>
														<div style="text-align:center">
														<table
															class="table table-striped table-bordered table-hover"
															id="accamountsettlementtable">
															<thead>
													<tr>
												<th>Sr No</th><th>Depot</th><th>ACC-66 Amount</th><th>Its Amount</th><th>Axis Amount</th>
			<th>Differnce</th><th>Status</th><th>Audited Date</th>
													</tr>
															</thead>
														</table>
													</div>
                                                   </div>
                                                   
                                                   	<div class="portlet-body" id="pastsettlement"
														style="display: none">
															<br><b><font color="red">Old Settlement Report</font></b>
														<div style="text-align:center">
														<table
															class="table table-striped table-bordered table-hover"
															id="pastsettlementtable">
															<thead>
													<tr>
												<th>Sr No</th><th>Depot</th><th>ACC-66 Amount</th><th>Its Amount</th><th>Axis Amount</th>
			<th>Differnce</th><th>Status</th><th>Audited Date</th>
													</tr>
															</thead>
														</table>
													</div>
                                                   </div>
                                                   
                                                    	<div class="portlet-body" id="notettlement"
														style="display: none">
																<br><b><font color="red">Details of Unsettlement Amount </font></b>
														<div style="text-align:center">
														<table
															class="table table-striped table-bordered table-hover"
															id="notettlementtable">
															<thead>
													<tr>
												<th>Sr No</th><th>Depot</th><th>Opening Amount</th><th>Past Settlement</th><th>Today Pending</th><th>Total Pending</th>
													</tr>
															</thead>
														</table>
													</div>
                                                   </div>
					
					
					</div>
	</div>
	</div>
	</div></div>
					</div>
	
	    	<div id="headerr" class="portlet-body" style="display: none; visibility: hidden;">
 <center><h4 style="margin-center:350px;">Daily Recon Report</h4></center>
<br />
<div id="headerdetails" style="margin-left:0px;">

<table ID="mytable" style="width:60%;border:none;">
				<tr>
	


				
					<td ><b><font size="2"><label>  Date: </label></font></b>
					<!-- <td style="text-align: left;"> -->
								 <b><font size="2"><span id="startdat" style="text-align:center;margin-center:10px;"></span></font></b></td>
				</tr>
			
			</table>
			 <br/><br/> 

</div>
</div> 
	
	</head>
	</html>
