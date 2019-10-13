
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<script>


function printDiv() {     
	 
	 document.getElementById("vtsAlertReportDivId").style.visibility='visible';  
     document.getElementById("header").style.display='block';
     document.getElementById("header").style.visibility='visible';     
     var divElements = document.getElementById("header").innerHTML;
     divElements+= document.getElementById("vtsAlertReportDivId").innerHTML;
     
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



function getAlertReportOnSubmit(){
	var givenDate = $("#seldate").val();
	var depotid = $("#divisionlistid").val();
        console.log("=="+givenDate);
      
	        $('#vtsAlertReportId').dataTable({
    			"aLengthMenu" : [ [ 10, 15, 20, -1 ], [ 10, 15, 20, "All" ] // change
    			
    			],
    		
    			"sAjaxSource" : "getPartialDetails.action?givendate=" + givenDate+"&depotid=" + depotid,
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
    				'#vtsAlertReportId_wrapper .dataTables_filter input')
    				.addClass("form-control input-small input-inline"); // modify
    		// table
    		jQuery(
    				'#vtsAlertReportId_wrapper .dataTables_length select')
    				.addClass("form-control input-xsmall input-inline"); // modify         

}

</script>
<style>
</style>
</head>
<%-- <script src="assets/vts/js/vehiclealert.js" type="text/javascript"></script> --%>
<body >
	<div class="page-content-wrapper">
		<div class="page-content">
			<div class="row">
					<div class="col-md-12">
						<h3 class="page-title">
	 					PARTIAL TRIP REPORT <small></small>
						</h3>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="portlet box grey-cascade">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-globe"></i>Partial Trip  Report
								</div>
							</div>
							
						</div>
						 <div class="portlet-body">
									<input type="hidden" name="requestType" id="requestType"
									value="text" /> 
									<div class="portlet-body form">
										<div class="form-group">
											<div class="col-md-3">
												<div class="input-group input-medium date date-picker"
													data-date-format="dd-mm-yyyy" data-date-viewmode="years">
													<input class="form-control" type="text" size="10" name=""
															id="seldate" value='<s:property value=""/>' readonly>
													<span class="input-group-btn">
														<button class="btn default date-set form-control" type="button">
															<i class="fa fa-calendar"></i>
														</button>
													</span>
												</div>
											</div>
										</div>
										<div class="form-group">
											<%-- <div class="col-md-3">
												<s:select list="alertlist1" id="alertlist_id"
													cssClass="select2_category form-control" headerKey="0"
														headerValue="--Alert Name--" ></s:select>

											</div> --%>
											</div>
											<div class="form-group">
											<div class="col-md-3">
												<s:select list="divisionlist" id="divisionlistid"
													cssClass="select2_category form-control" headerKey="00"
													headerValue="--Depot No.--" >
												</s:select>
											</div>
										</div>
											
						
	<script>                 	
  	 function tabletoExcel(btnExport){     
  	var htmltable= document.getElementById('btnExport');   
    $( "#header-fixed" ).remove();
    var inputHTML = "<table border='1'>";
    inputHTML += "<tr>";
    inputHTML += "<th  align='left'colspan='9'>Bangalore Metropolitan Transport Corporation</th>";
    inputHTML += "</tr>";
    inputHTML += "<th  align='left'colspan='9'>PARTIAL TRIP REPORT</th>";
    inputHTML += "</tr>";
    inputHTML += "<tr>";
    inputHTML += "<th colspan='2' align='left'>Date Range:</th>";
    inputHTML += "<th colspan='7' align='left'>" + $("#startdate").val() + " to " + $("#endate").val() + "</th>";
    inputHTML += "</tr>";
    inputHTML += "</table>";
    var htmltable = document.getElementById("vtsAlertReportDivId");
    var html = inputHTML + htmltable.outerHTML;
    var downloadLink = document.createElement("a");
    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
    downloadLink.download = "PARTIAL TRIP REPORT.xls";
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
}</script>

										<div class="form-group">
											<div class="col-md-3">
										<!-- <div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9"> -->
											<button type="button" class="btn default" onclick="getAlertReportOnSubmit()">Submit</button>
<!-- 										 </div> -->
									</div>
									<div class="col-md-1"id="printbutton">
											<input type='button' class="btn default" id='button1' onclick='printDiv()' value='Print' />													
										</div>
										<div class="col-md-1" id="">
								 <button type="button" class="btn default" id="btnExport" filename= "report" onclick="tabletoExcel();"> EXPORT </button>
                                </div></div>	
								 </div>
                                </div>
							
									</div>
									<br/><br/>
							<br/><br/>
							
							
							<div id="header" class="portlet-body" style="display: none; visibility: hidden;">
										<h4 style="margin-left:350px;">Partial Trip Report</h4>
									<br />
									<div id="headerdetails" style="margin-left:0px;">
                         	   
                                    
                                </div>
                                </div>
							 </div>
			 				<br/><br/>
							<br/><br/>
							<div class="portlet-body" id="vtsAlertReportDivId">
							<table class="table table-striped table-bordered table-hover"
								id="vtsAlertReportId">

								<thead>
									<tr>
										<th style="width1: 8px;">Sr No</th>
				 						<th>Schedule No</th>
										<th>Trip No</th>
										<th>Shift Type</th>
										<th>Route No</th>
										<th>Vehicle No</th>
										<th>Start Stop</th>
										<th>End Stop</th>
									</tr>
								</thead>

							</table>
						</div>
					
		
						
						
					</div>
				</div>
<!-- 				</div> -->
<!-- 				</div> -->
	</body>			
</html>
