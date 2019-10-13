<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="assets/global/plugins/jquery-1.11.0.min.js"
	type="text/javascript"></script>
	<link rel="stylesheet" href="resources/cssFiles/jquery-ui.css"/>
	<script type="text/javascript" src="resources/jsFiles/jquery-ui-1.9.2.min.js"></script>
<script type="text/javascript" src="resources/jsFiles/jquery-ui-1.9.2.js"></script>
<STYLE type="text/css">
body { height: 1000px; }
#header-fixed {
    position: fixed;
    top: 3px; display:block;
    background-color:white;
}
</STYLE>

<script>

// function hidedata()
// {
// 	$(tableContainer).hide();
// 	}
// 	window.onload=hidedata;

$(document).ready(function() {
	$(tableContainer).hide();
	$(header1).hide();
	
});
	
var tableOffset;
var $header;
var $fixedHeader;



 var i=0;
 function getDepot(orgId){
	// $('#select2-chosen-2').html("Select");
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 var val=document.getElementById('divisionlist').value;
			 if(val!=0) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/getDepot?val=' + val,
				success : function(result) {
					document.getElementById('depotlist').innerHTML = result;
				}
			});
		}

	}
 function getvehicle(orgId){
	 $('#select2-chosen-2').html("Select");
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 var val=document.getElementById('depotlist').value;
			 if(val!=0) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/getVehicle?val=' + val,
				success : function(result) {
					document.getElementById('vehiclelist').innerHTML = result;
				}
			});
		}

	}
	
	function validateTripStatusReportFields(scheduleNo,depotNo,divisonNo,selDate)
	{
		 if(divisonNo==0)
		 {
			alert("Please Select Divison");
			return false;
		 }
		 if(depotNo==0)
		 {
			alert("Please Select Depot");
			return false;
		 }
		 if(selDate==0)
	     {
		   alert("Please Select Date");
		   return false;
	     }
	     if(scheduleNo==0)
	     {
		   alert("Please Select Schedule Number");
		   return false;
	     }
	     

	 return true;
	}
	function getScheduledTripStatusDataOnSubmit()
	{
		
		var depotNo=$("#depotlist").val();
		var divisonNo=$("#divisionlist").val();
		var startdate=$("#startdate").val();
		var enddate=$("#enddate").val();
		
	
	//	alert("depotNo..."+depotNo+"....divisonNo...."+divisonNo+"....startdate...."+startdate+"....enddate...."+enddate);
//	 	 var d1 = Date.parse(startdate);
	
// 		var d3=Date.parse(enddate);
// 			if (d1 <= d3){
	$(tableContainer).show();
	        $.ajax({
	        
	            type: "post",
	            url: '<%=request.getContextPath()%>/AjaxSeniorcitizenETMticketconsumptionreport.action?startdate='+startdate+'&enddate='+enddate+'&depotNo='+depotNo+'&divisonNo='+divisonNo,
	            success: function(result) {
	            	$('#pageLoader').hide();
	                document.getElementById('earlyarrivalsummary').innerHTML=result;
	                
// 	                var t=$("#table");
// 	                tableOffset = $("#table-1").offset().top;
// 	               $header = $("#table-1 > thead").clone();
// 	                $fixedHeader = $("#header-fixed").append($header);
	            }

// 	                }

	        });
// 			}else{
	 			
	 			
// 	 			alert("End Date Should Be greater Than Start Date");
// 	 			$('#pageLoader').hide();
// 	 			 document.getElementById('earlyarrivalsummary').innerHTML="";
// 	 		}
		
	}
	function printDiv() {     
		$(header1).show();
	    //  document.getElementById("print").style.visibility='hidden';     
	    var divElements = document.getElementById("header1").innerHTML;
// 	    divElements += document.getElementById("tableContainer").innerHTML;
	    divElements += document.getElementById("printid").innerHTML;

	    var mywindow = window.open("<%=request.getContextPath()%>/Print.jsp");
	   
	    mywindow.onload = function() {
	    mywindow.document.body.innerHTML=divElements;
	    mywindow.document.body.innerHTML=divElements;
	    mywindow.print();
	    mywindow.close();
	    }
	            
	}
	
 </Script>

<title>Insert title here</title>
</head>
<body>
	<div class="page-content-wrapper">
		<div class="page-content">
	

			<div class="tab-content">

				<div class="row">
					<div class="col-md-12">
						<!-- BEGIN PAGE TITLE & BREADCRUMB-->
						<h3 class="page-title">
							Seniorcitizen ETM ticket consumption report <small></small>
						</h3>

						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portle-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Senior citizen ETM ticket consumption report
							</div>
							<!-- <div class="tools">
								<a href="javascript:;" class="collapse"> </a> 
								 <a href="javascript:;" class="reload"> </a> 
							</div> -->
						</div>

						<div class="portlet-body form">

							<div class="col-md-12" align="left" style="font-size: 1.1em">

								<%-- <span class="help-block" style="color: red; list-style: none"><s:actionerror /></span> --%>
								<span class="help-block" style="color: red; list-style: none"><s:actionmessage /></span>
							</div>
							<!-- BEGIN FORM-->
							<form action="" method="post" class="form-horizontal">

								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Division<font
											color="red">*</font></label>
										<div class="col-md-4">
											<s:select list="divisionlist" id="divisionlist" name="orgchart.org_chart_id"
												cssClass="form-control select2me" headerKey="0"
												headerValue="All" onchange="getDepot(this.value)"></s:select>
										</div>
									</div>
								

								<div class="form-group">
									<label class="col-md-3 control-label">Depot<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select id="depotlist" class="select2_category form-control" >
											<option value="0">All</option>
										</select>
									</div>
								</div>
							

									
								<div class="form-group">
									<label class="control-label col-md-3">Start Date<font
										color="red">*</font></label>
									<div class="col-md-3">
										<div class="input-group input-medium date date-picker"
											style="width: auto" data-date-format="dd-mm-yyyy"
											data-date-viewmode="years">
											<input type="text" class="form-control" id="startdate" 
												readonly name="selecteselecteddateddate" /> <span
												class="input-group-btn">
												<button class="btn default" type="button">
													<i class="fa fa-calendar"></i>
												</button> 
											</span>
											<script>
													var curr_date = new Date().toJSON().slice(0,10);
													curr_date = curr_date.split("-");
													curr_date = curr_date[2] + "-" + curr_date[1] + "-" + curr_date[0];
													var dtval = document.getElementById('startdate').value;
													if (dtval == '') {
														$('#startdate').val(curr_date);
													}
												</script>	
										</div>
									</div>
										
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">End Date<font
										color="red">*</font></label>
									<div class="col-md-3">
										<div class="input-group input-medium date date-picker"
											style="width: auto" data-date-format="dd-mm-yyyy"
											data-date-viewmode="years">
											<input type="text" class="form-control" id="enddate"
												readonly name="selecteselecteddateddate" /> <span
												class="input-group-btn">
												<button class="btn default" type="button">
													<i class="fa fa-calendar"></i>
												</button> 
											</span>
											<script>
													var curr_date = new Date().toJSON().slice(0,10);
													curr_date = curr_date.split("-");
													curr_date = curr_date[2]+ "-"+ curr_date[1]+ "-"+ curr_date[0];
													var dtval = document.getElementById('enddate').value;
													if (dtval == '') {
														$('#enddate').val(curr_date);
														
													}
												</script>
										</div></div></div>
			<div align='center'>
			 
				    <button type="button" class="btn green" onclick="getScheduledTripStatusDataOnSubmit()" >Submit</button>
				     <button type="button" class="btn green" onclick="printDiv()" >Print</button>
                    <span><button type="button" class="btn green"id="btnExport" filename= "report" onclick="tabletoExcel();"> EXPORT </button></span>&nbsp;<span>
 
                    </div>
			       				
       			  
 	               			 
 	               	    <script>                 	
  	 function tabletoExcel(btnExport){     
  	var htmltable= document.getElementById('btnExport');   
    $( "#header-fixed" ).remove();
    $(header1).show();
    var inputHTML = "<table border='1'>";
    inputHTML += "<tr>";
    inputHTML += "<th  align='left'colspan='4'>Bangalore Metropolitan Transport Corporation</th>";
    inputHTML += "</tr>";
    inputHTML += "<th  align='left'colspan='4'>Seniorcitizen ETM ticket consumption report</th>";
    inputHTML += "</tr>";
    inputHTML += "<tr>";
    inputHTML += "<th colspan='2' align='left'>Date Range:</th>";
    inputHTML += "<th colspan='2' align='left'>" + $("#startdate").val() + " to " + $("#endate").val() + "</th>";
    inputHTML += "</tr>";
    inputHTML += "</table>";
    var header = document.getElementById("header1");
    var htmltable = document.getElementById("printid");
    
    var html = inputHTML +header.outerHTML + htmltable.outerHTML;
    var downloadLink = document.createElement("a");
    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
    downloadLink.download = "Seniorcitizen ETM ticket consumption report.xls";
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
}</script>
  				
  				                   
					<div class="form-group">
					<label class="control-label col-md-3"></label>
					<div class="col-md-3" id="">
					</div> </div> </div>
                    </form>

							<div id="tableContainer" class="tableContainer">
								<table class="scrollTable" border="0"  
									style= "width:80%">
									<thead class="fixedHeader">
										<tr class="alternateRow">
											<th style= "width:15%">Sl No</th>
											<th style= "width:30%">DENOMINATION</th>
											<th style= "width:30%">NO. OF TICKETS</th>
											<th style= "width:40%">TOTAL REVENUE</th>
										</tr>
									</thead></table></div>
									
<!-- 									<tbody class="scrollContent"> -->
<!-- 	                           <tr class="normalRow"> -->
<!-- 										<div id="earlyarrivalsummary"></div> -->
<!-- 									</tr> -->
<!-- </tbody> -->

					<div id="earlyarrivalsummary"></div>
					
                    </div> 
                    
                    <div id="header1">
								<table class="scrollTable" border="1"  
									style= "width:80%">
									<thead>
										<tr>
											<th style= "width:6%">Sl No</th>
											<th style= "width:20%">DENOMINATION</th>
											<th style= "width:50%">NO. OF TICKETS</th>
											<th style= "width:60%">TOTAL REVENUE</th>
										</tr>
									</thead></table></div>


							</div>

						</div></div></div></div>	 
</body>
</html>