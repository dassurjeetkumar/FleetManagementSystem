<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>

<STYLE type="text/css">
body { height: 1000px; }
#header-fixed {
    position: fixed;
    top: 3px; display:none;
    background-color:white;
}
</STYLE>

<script type="text/javascript">
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
					document.getElementById('divisionlist').innerHTML = result;
				}
			});
		}}


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
		}}
var i=0;


	function getDailypassDataOnSubmit()
	{
		
		var depotNo=$("#depotlist").val();
		var divisonNo=$("#divisionlist").val();
		var startdate=$("#startdate").val();
		var enddate=$("#enddate").val();
		
		
//	alert("depotNo..."+depotNo+"....divisonNo...."+divisonNo+"....startdate...."+startdate+"....enddate...."+enddate);
//	 var d1 = Date.parse(startdate);

		
//		var d3=Date.parse(enddate);
//			if (d1 <= d3){
	        $.ajax({
	        
	            type: "post",
	            url: '<%=request.getContextPath()%>/AjaxNooftripswith0revenue.action?startdate='+startdate+'&enddate='+enddate+'&depotNo='+depotNo+' &divisonNo='+divisonNo,
	            success: function(result) {
	            	$('#pageLoader').hide();
	                document.getElementById('earlyarrivalsummary').innerHTML=result;
	                var html = document.getElementById("header").innerHTML;
	         	   
	            }
	        });
//			}else{
	 			
	 			
//	 			alert("End Date Should Be greater Than Start Date");
//	 			$('#pageLoader').hide();
//	 			 document.getElementById('earlyarrivalsummary').innerHTML="";
//	 		}
		
	}
	function printDiv() {     
	    
	    //  document.getElementById("print").style.visibility='hidden';     
	    var divElements = document.getElementById("header").innerHTML;
	    divElements += document.getElementById("printid").innerHTML;

	    var mywindow = window.open("<%=request.getContextPath()%>/Print.jsp");
	   
	    mywindow.onload = function() {
	    mywindow.document.body.innerHTML=divElements;
	    mywindow.document.body.innerHTML=divElements;
	    mywindow.print();
	    mywindow.close();
	    }
	            
	}
	
	function jsFunction(waybillid){
		var depotNo=$("#depotlist").val();
		window.open("ShowTripdetailsAll?waybillid="+waybillid+"&depotNo="+depotNo, "popup",
        "width=200, height=200,left=400,top=200,resizable=yes");

		
	}
</Script>

<title>Insert title here</title>
</head>
<body>
	<div class="page-content-wrapper">
		<div class="page-content">
	

			<div class="tab-content">
<%-- 
				<div class="row">
					<div class="col-md-12">
						<!-- BEGIN PAGE TITLE & BREADCRUMB-->
						<h3 class="page-title">
							No of trips with '0' revenue <small></small>
						</h3>

						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div> --%>
<!-- 				<div class="tab-pane active" id="tab_0"> -->
<!-- 					<div class="portlet box grey-cascade"> -->
<!-- 						<div class="portle-title"> -->
<!-- 							<div class="caption"> -->
<!-- 								<i class="fa fa-gift"></i>No of trips with '0' revenue -->
<!-- 							</div> -->
<!-- 						</div> -->
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i>No. Of Trips With '0' Revenue
						</div>
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
										<s:select list="divisionlist" id="divisionlist"
											name="orgchart.org_chart_id"
											cssClass="form-control select2me" headerKey="0"
											headerValue="select" onchange="getDepot(this.value)"></s:select>
									</div>
								</div>


								<div class="form-group">
									<label class="col-md-3 control-label">Depot<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select id="depotlist" class="select2_category form-control">
											<option value="select">select</option>
										</select>
									</div>
								</div>


								<div class="form-group">
									<label class="control-label col-md-3">Date:</label>
									<div class="col-md-3">
										<div class="input-group input-medium date date-picker"
											style="width: auto" data-date-format="dd-mm-yyyy"
											data-date-viewmode="years">
											<input id="enddate" class="form-control" type="text"
												readonly="" size="16" name="enddate"
												value="<s:property value='enddate' />"> <span
												class="input-group-btn">
												<button class="btn default date-set" type="button">
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

										</div>
									</div>
								</div>

							<div align='center'>	
								<button type="button" class="btn green" onclick="getDailypassDataOnSubmit()">Submit</button>&nbsp;&nbsp;
									<input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' />&nbsp;&nbsp;
									<button type="button" class="btn green" id="btnExport" onclick="tabletoExcel();">Export</button>
									<!-- END EXAMPLE TABLE PORTLET-->
								</div>

								<script>                 	
   function tabletoExcel(btnExport){     
  	var htmltable= document.getElementById('btnExport');   
    $( "#header-fixed" ).remove();
    var inputHTML = "<table border='1'>";
    inputHTML += "<tr>";
    inputHTML += "<th  align='left'colspan='6'>Bangalore Metropolitan Transport Corporation</th>";
    inputHTML += "</tr>";
    inputHTML += "<th  align='left'colspan='6'>No of trips with '0' revenue</th>";
    inputHTML += "</tr>";
    inputHTML += "<tr>";
    inputHTML += "<th colspan='3' align='left'>Date:</th>";
    inputHTML += "<th colspan='3' align='left'>" + $("#enddate").val() +"</th>";
//     + " to " + $("#endate").val() + "</th>";
    inputHTML += "</tr>";
    inputHTML += "</table>";
    var htmltable = document.getElementById("printid");
    var html = inputHTML + htmltable.outerHTML;
    var downloadLink = document.createElement("a");
    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
    downloadLink.download = "No of trips with '0' revenue.xls";
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
}</script>

							</div>
						</form>
<!-- 						<div id="tableContainer" class="tableContainer"> -->
<!-- 								<table class="scrollTable" border="0"   --><!-- 
								style='width:50%;border-collapse: collapse;'> -->
<!-- 									<thead class="fixedHeader"> -->
<!-- 										<tr class="alternateRow"> -->
<!-- 											<th style= "width:10%">Sr.No.</th> -->
<!-- 											<th style= "width:10%">Date</th> -->
<!-- 											<th style= "width:20%">Schedule No.</th> -->
<!-- 											<th style= "width:20%">Duty Type</th> -->
<!-- 											<th style= "width:20%">No.Of Trips</th> -->
<!-- 											<th style= "width:10%">ETM No.</th> -->
<!-- 											<th style= "width:20%">Conductor Name</th> -->
<!-- 											<th style= "width:20%">Conductor Token</th> -->
<!-- 										</tr> -->
<!-- 									</thead></table></div> -->
						
						<div id="earlyarrivalsummary"></div>
					</div>
				</div>

			</div>
			</div></div></div>	 

</body>
</html>
