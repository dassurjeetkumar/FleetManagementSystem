<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>

<script src="https://code.jquery.com/jquery-1.11.3.min.js" type="text/javascript"></script>
	
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />


<%--  <script src="assets/global/plugins/jquery-1.11.0.min.js" --%>
<%-- 	type="text/javascript"></script> --%>
	
<%-- <script src="assets/vts/js/scheduledeviation.js" --%>
<%-- 	type="text/javascript"></script> --%>
<%-- 	<script src="assets/vts/js/vts.js" type="text/javascript"></script> --%>

 
<Script>


 
 
function printDiv1() {     
    
    //  document.getElementById("print").style.visibility='hidden';     
    var divElements = document.getElementById("header").innerHTML;
    divElements += document.getElementById("ticketconsuptionid").innerHTML;
//     alert("Hi");
    var noOfTableExist = 0;
    try{
		$("#northdivisionData table").each(function(){
			noOfTableExist++;
		});
		$("#southdivisionData table").each(function(){
			noOfTableExist++;
		});
		$("#eastdivisionData table").each(function(){
			noOfTableExist++;
		});
		$("#westdivisionData table").each(function(){
			noOfTableExist++;
		});
		$("#centraldivisionData table").each(function(){
			noOfTableExist++;
		});
		$("#scheduleAbstractdivisionData table").each(function(){
			noOfTableExist++;
		});
		
		
		console.log("Total no of tables : " + noOfTableExist);
		/* If two table exist  then remove the last table on print click*/
		if(noOfTableExist >= 2){
			divElements = divElements.substring(0, divElements.indexOf("</table>") + 8) + "</div></div>";
		}
	}catch(err){
	    console.log("ExceptionCaught : " + err);
	}

    var mywindow = window.open("<%=request.getContextPath()%>/Print.jsp");
   
    mywindow.onload = function() {
    mywindow.document.body.innerHTML=divElements;
    mywindow.document.body.innerHTML=divElements;
    mywindow.print();
    mywindow.close();
    }
            
}
 
 function printDiv() {     
	 
	 document.getElementById("mapshow").style.visibility='hidden'; 
	$(".mapClass").hide();
	   document.getElementById("tripstatus").style.visibility='visible';  
     document.getElementById("header").style.display='block';
     document.getElementById("header").style.visibility='visible';     
     var divElements = document.getElementById("header").innerHTML;
     divElements+= document.getElementById("tripstatus").innerHTML;
     
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
		document.getElementById("mapshow").style.visibility=''; 
		$(".mapClass").show();
     
             
 }
 var i=0;
 function getDepot(orgId){
	 $('#select2-chosen-2').html("Select");
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

	function validateTripStatusReportFields(orderno)
	{
		
		
		 if(orderno==0)
		 {
			alert("Please Select OrderNO");
			return false;
		 }
	     

	 return true;
	}
	
	function resetDate(){
		//$("#selecteddate").val()='';
		document.getElementById("selecteddate").value='';
	}
	
	
	function getScheduledTripStatusDataOnSubmit()
	{
		
// 		var scheduleNo=$("#schedulelist").val();
		var orderno=$("#orderno").val();
// 		var date=$("#startdate").val();
// 		var selecteddate=$("#selecteddate").val();
		validateflag=validateTripStatusReportFields(orderno);
		if(validateflag==true)
		{
// 		$("#printbutton").show();
// 		$("#scheduleprint").show();
		var orderno=$("#orderno").val();
// 		var date=$("#startdate").val();
// 		alert(selecteddate);
	//alert("HII"+scheduleNo);
// 		document.getElementById("scheduleTripStatus").style.display = '';
// 		$("#schdeviation").hide();
// 		document.getElementById("scheduleTripStatus").style.display = '';
// 		var selectedDate=document.getElementById("selecteddate").value;
		
	
// 		$("#tripstatus").show();
$('#pageLoader').show();
		 $.ajax({
	           type: "get",
	           url: '<%=request.getContextPath()%>/AjaxNorthDivisionWiseWeeklyReport.action?orderno='+orderno,
	           success: function(result) {
	        	   $('#pageLoader').hide();
// 	        	   alert("hi"+result);
	                document.getElementById('northdivisionData').innerHTML=result;
	                fixHeader();
	                
	            }
	       }); 
		 
		 $.ajax({
	           type: "get",
	           url: '<%=request.getContextPath()%>/AjaxSouthDivisionWiseWeeklyReport.action?orderno='+orderno,
	           success: function(result) {
	        	   $('#pageLoader').hide();
//	        	   alert("hi"+result);
	                document.getElementById('southdivisionData').innerHTML=result;
	                fixHeader();
	                
	            }
	       }); 
		 
		 $.ajax({
	           type: "get",
	           url: '<%=request.getContextPath()%>/AjaxEastDivisionWiseWeeklyReport.action?orderno='+orderno,
	           success: function(result) {
	        	   $('#pageLoader').hide();
//	        	   alert("hi"+result);
	                document.getElementById('eastdivisionData').innerHTML=result;
	                fixHeader();
	                
	            }
	       }); 
		 
		 $.ajax({
	           type: "get",
	           url: '<%=request.getContextPath()%>/AjaxWestDivisionWiseWeeklyReport.action?orderno='+orderno,
	           success: function(result) {
	        	   $('#pageLoader').hide();
//	        	   alert("hi"+result);
	                document.getElementById('westdivisionData').innerHTML=result;
	                fixHeader();
	                
	            }
	       }); 
		 
		 $.ajax({
	           type: "get",
	           url: '<%=request.getContextPath()%>/AjaxCentralDivisionWiseWeeklyReport.action?orderno='+orderno,
	           success: function(result) {
	        	   $('#pageLoader').hide();
//	        	   alert("hi"+result);
	                document.getElementById('centraldivisionData').innerHTML=result;
	                fixHeader();
	                
	            }
	       }); 
		 
		 $.ajax({
	           type: "get",
	           url: '<%=request.getContextPath()%>/AjaxScheduleAbstractDivisionWiseWeeklyReport.action?orderno='+orderno,
	           success: function(result) {
	        	   $('#pageLoader').hide();
//	        	   alert("hi"+result);
	                document.getElementById('scheduleAbstractdivisionData').innerHTML=result;
	                fixHeader();
	                
	            }
	       }); 
		 
		
		 
		 
		 
// 		 document.getElementById('selectdate').innerHTML=selectedDate;
// 		 document.getElementById('scheduleno').innerHTML=scheduleNo;
		 
		}
		 
	}

	



 </Script>

<title>Insert title here</title>
</head>
<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">
	<div class="page-content-wrapper">
		<div class="page-content">
	
			<div class="tab-content">
			
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
						<!-- BEGIN PAGE TITLE & BREADCRUMB-->
						<h3 class="page-title">
							DIVISION WISE WEEKLY REPORT <small></small>
						</h3>

						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>View Division Wise Weekly Report
							</div>
							<div class="actions">

						
						<input type='button' class='btn blue' id='button1' onclick='printDiv1()' value='Print' />													
										
						<button type="button" class='btn blue' id="btnExport" filename= "report" onclick="tabletoExcel();"> EXPORT </button>
                               

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

<!-- 								<div class="form-body"> -->
<!-- 									<div class="form-group"> -->
<!-- 										<label class="col-md-3 control-label">Division<font -->
<!-- 											color="red">*</font></label> -->
<!-- 										<div class="col-md-4"> -->
<%-- 											<s:select list="divisionlist" id="divisionlist" --%>
<%-- 												name="orgchart.org_chart_id" --%>
<%-- 												cssClass="select2_category form-control" --%>
<%-- 												 onchange="getDepot(this.value)"></s:select> --%>
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</div> -->
								<script>
					
					</script>
					

								<div class="form-group">
									<label class="col-md-3 control-label">Traffic Order No:<font
										color="red">*</font></label>
									<div class="col-md-4">

<%--                                       <select id="orderno" class="select2_category form-control" name="orderno" --%>
<%-- 											> --%>
<!-- 											<option value="0">Select</option> -->
<%--  										</select>  --%>


                                            <s:select list="orderlist" id="orderno" 
												name="orderno" headerKey="0" headerValue="<---Select--->"
												cssClass="select2_category form-control"
 												 ></s:select> 
									</div>
								</div>

							
								
								
						  <script>                 	
  	           	 function tabletoExcel(btnExport){     
  	           	var htmltable= document.getElementById('btnExport');   
  	             $( "#header-fixed" ).remove();
  	             var inputHTML = "<table border='1'>";
//   	             inputHTML += "<tr>";
//   	             inputHTML += "<th  align='left'colspan='9'>Bangalore Metropolitan Transport Corporation</th>";
//   	             inputHTML += "</tr>";
//   	             inputHTML += "<th  align='left'colspan='9'>DIVISION WISE WEEKLY REPORT</th>";
//   	             inputHTML += "</tr>";
//   	             inputHTML += "<tr>";
//   	             inputHTML += "<th colspan='2' align='left'>Date Range:</th>";
//   	             inputHTML += "<th colspan='7' align='left'>" + $("#startdate").val() + " to " + $("#endate").val() + "</th>";
//   	             inputHTML += "</tr>";
  	             inputHTML += "</table>";
  	             var htmltable = document.getElementById("ticketconsuptionid");
  	             var html = inputHTML + htmltable.outerHTML;
  	             var downloadLink = document.createElement("a");
  	             downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
  	             downloadLink.download = "DIVISION WISE WEEKLY REPORT.xls";
  	             document.body.appendChild(downloadLink);
  	             downloadLink.click();
  	             document.body.removeChild(downloadLink);
  	         }</script>
						
						
  	              	 
  	                              <div class="form-group">
  	                              <label class="col-md-3 control-label"></label>
									<div class="col-md-1" id="">
									<button type="button" class='btn green' onclick='getScheduledTripStatusDataOnSubmit()' style="position: static;">Submit</button>
									</div>
<!-- 									
								
								
					
							<!-- END FORM-->
						</div>
							
					</div>
					
					
					
					
					
<!-- 				 <div id="ticketconsuptionid"></div> -->
				</div>
				

				<div id="ticketconsuptionid">
<div class="portlet box blue">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i>NORTH DIVISION
						</div>
						<div class="tools">
							<a class="collapse" id="route_deviation_data" href="javascript:;"> </a>
						</div>
					</div>
					<div class="portlet-body" id="route_deviation_id"
						style="overflow: hidden;">
						<div class="portlet-body" id="scheduleprint" style='height:310px; overflow-y:scroll; display:block;'>
							<table class="table table-bordered sticky-thead"
								id="northdivisionData" style="max-height: 60px;">
								
							</table>
						</div>
					</div>
				</div>
				
				<div class="portlet box blue">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i>SOUTH DIVISION
						</div>
						<div class="tools">
							<a class="collapse" id="route_deviation_data" href="javascript:;"> </a>
						</div>
					</div>
					<div class="portlet-body" id="route_deviation_id"
						style="overflow: hidden;">
						<div class="portlet-body" id="scheduleprint" style='height:310px; overflow-y:scroll; display:block;'>
							<table class="table table-bordered sticky-thead"
								id="southdivisionData" style="max-height: 60px;">
								
							</table>
						</div>
					</div>
				</div>
				
				<div class="portlet box blue">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i>EAST DIVISION
						</div>
						<div class="tools">
							<a class="collapse" id="route_deviation_data" href="javascript:;"> </a>
						</div>
					</div>
					<div class="portlet-body" id="route_deviation_id"
						style="overflow: hidden;">
						<div class="portlet-body" id="scheduleprint" style='height:310px; overflow-y:scroll; display:block;'>
							<table class="table table-bordered sticky-thead"
								id="eastdivisionData" style="max-height: 60px;">
								
							</table>
						</div>
					</div>
				</div>
				
				<div class="portlet box blue">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i>WEST DIVISION
						</div>
						<div class="tools">
							<a class="collapse" id="route_deviation_data" href="javascript:;"> </a>
						</div>
					</div>
					<div class="portlet-body" id="route_deviation_id"
						style="overflow: hidden;">
						<div class="portlet-body" id="scheduleprint" style='height:310px; overflow-y:scroll; display:block;'>
							<table class="table table-bordered sticky-thead"
								id="westdivisionData" style="max-height: 60px;">
								
							</table>
						</div>
					</div>
				</div>
				
					<div class="portlet box blue">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i>CENTRAL DIVISION
						</div>
						<div class="tools">
							<a class="collapse" id="route_deviation_data" href="javascript:;"> </a>
						</div>
					</div>
					<div class="portlet-body" id="route_deviation_id"
						style="overflow: hidden;">
						<div class="portlet-body" id="scheduleprint" style='height:310px; overflow-y:scroll; display:block;'>
							<table class="table table-bordered sticky-thead"
								id="centraldivisionData" style="max-height: 60px;">
								
							</table>
						</div>
					</div>
				</div>
				
				<div class="portlet box blue">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i>Over All Form-v  Schedule Abstract 
						</div>
						<div class="tools">
							<a class="collapse" id="route_deviation_data" href="javascript:;"> </a>
						</div>
					</div>
					<div class="portlet-body" id="route_deviation_id"
						style="overflow: hidden;">
						<div class="portlet-body" id="scheduleprint" style='height:310px; overflow-y:scroll; display:block;'>
							<table class="table table-bordered sticky-thead"
								id="scheduleAbstractdivisionData" style="max-height: 60px;">
								
							</table>
						</div>
					</div>
				</div>
				
				
				</div>

	
			</div>
		</div>
</div>
</div>

	
										

	

</body>
</html>