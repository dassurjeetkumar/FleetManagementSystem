
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<%-- <script src="assets/admin/pages/scripts/viewindex.js" type="text/javascript"></script> --%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/assets/vts/js/skiproute.js"></script>
<script>

function printDiv() {     
	 
	document.getElementById("depotout").style.visibility='visible';  
    document.getElementById("header").style.display='block';
    document.getElementById("header").style.visibility='visible';  
    var divElements = document.getElementById("header").innerHTML;
    divElements+= document.getElementById("depotout").innerHTML;
    console.log(divElements);
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


function getDepot(orgId){
	 //$('#select2-chosen-2').html("Select");
	 $('#select2-chosen-3').html("Select");
	//alert('Here');
	 /* var selectedValue = $('#form-control').val(); */
	 var val=document.getElementById('divisionlist').value;
		 if(val!=0) {
			 $("#msg").html("Please wait...");
       $.ajax({
           type: "get",
           url: '<%=request.getContextPath()%>/getDepot?val='+val,
           success: function(result) {
           	$("#msg").html("");
               document.getElementById('depotlist').innerHTML=result;
           }
       });
	 }
	
}
</script>
<style>
h1.intro {
	color: green;
	font-size: 12px;
}
</style>
</head>
<body onload="setcurrDate()">
	<form>
		<div class="page-content-wrapper">
			<div class="page-content">
				<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
				<div class="modal fade" id="portlet-config" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true"></button>
								<h4 class="modal-title">Vehicle Depot in-out</h4>
							</div>
							<div class="modal-body">Widget settings form goes here</div>
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
				<div class="row">
					<div class="col-md-12">
						<!-- BEGIN PAGE TITLE & BREADCRUMB-->
						<h3 class="page-title">
							VEHICLE DEPOT IN-OUT <small></small>
						</h3>

						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>

				<!-- END PAGE HEADER-->
				<!-- BEGIN PAGE CONTENT-->

				<%-- //<s:hidden name="busid" id="id" ></s:hidden> --%>
				<!-- <input type="hidden" name="busid" id="busid" value="22181"/> -->
				<div class="row">
					<div class="col-md-12">
						<!-- BEGIN EXAMPLE TABLE PORTLET-->
						<div class="portlet box grey-cascade">

							<div class="portlet-title">
								
								<div class="form-group">
								<div class="col-md-2">
								<select class="select2_category form-control" id="inoutValue" >
									<option value="Arrival">Arrival</option>
									<option value="Departure">Departure</option>
									
								</select>
								</div>
								<div class="col-md-2">
								<s:select list="divisionlist" id="divisionlist"
							name="orgchart.org_chart_id"
							cssClass="select2_category form-control" 
							 onchange="getDepot(this.value)" ></s:select>
							</div>
							<script>
					getDepot("");
					</script>
							<div class="col-md-2">
							<select id="depotlist"   class="select2_category form-control"      
							 onchange="getArrivalDeparture(this.value)">
							 <option value="0">Depot Name</option>
							 </select>
								</div>
								
						
  	                 
  	               <script>                 	
  	 function tabletoExcel(btnExport){     
//   	var htmltable= document.getElementById('btnExport');   
    $( "#header-fixed" ).remove();
    var htmltable="";
    var inputHTML = "<table border='1'>";
    inputHTML += "<tr>";
    inputHTML += "<th  align='left'colspan='9'>Bangalore Metropolitan Transport Corporation</th>";
    inputHTML += "</tr>";
    inputHTML += "<th  align='left'colspan='9'>VEHICLE DEPOT IN-OUT</th>";
    inputHTML += "</tr>";
    inputHTML += "<tr>";
//     inputHTML += "<th colspan='2' align='left'>Date Range:</th>";
//     inputHTML += "<th colspan='7' align='left'>" + $("#span_Id").val() + " to " + $("#endate").val() + "</th>";
    inputHTML += "</tr>";
    inputHTML += "</table>";
   // alert($("#inoutValue").val());
    if($("#inoutValue").val() =="Arrival"){
    	  htmltable = document.getElementById("depotin");
    }else{
    	  htmltable = document.getElementById("depotout");
    }  
   //htmltable += document.getElementById("totalData");
    var html = inputHTML + htmltable.outerHTML;
    var downloadLink = document.createElement("a");
    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
    downloadLink.download = "VEHICLE DEPOT IN-OUT.xls";
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
}</script>
						
								<div class="col-md-2">
								<button type="button" class="btn default" value="Submit" name="buttonname" onClick="getArrivalDeparture(this.value)">Submit</button>
							     </div>
							     <div class="col-md-1" id="">
								 <button type="button" class="btn default" id="btnExport" filename= "report" onclick="tabletoExcel();"> EXPORT </button>
                                </div>
								</div>
								<ul>
									<li class="pull-right">
										<div id="dashboard-report-range"
											class="dashboard-date-range tooltips" data-placement="top"
											data-original-title="Change  date range">
											<i class="fa fa-calendar"></i> <span id="span_Id"></span> <i
												class="fa fa-angle-down"></i>
										</div>



									</li>
								</ul>

								<!-- 	<div class="actions">
							
								  <!-- <div class="btn-group">
								
									<button class="btn green dropdown-toggle" data-toggle="dropdown"><i class="fa fa-gears"></i> Tools <i class="fa fa-angle-down"></i>
									</button>
									<ul class="dropdown-menu pull-right ">
										<li>
											<a class="btn blue" href="javascript: void(0)" onclick="window.open('','_new').location.href='/its/ReportAction!generateReport?id=TICKETBAGMASTERRPT'">
											href="/its/ReportAction!generateReport?id=1">
											Report </a>
										</li>	
										
																			
									</ul> 
							</div> -->



							</div>
						</div>

						<div class="portlet-body">

							<input type="hidden" name="daterange" id="daterange_id"
								value="<s:property value="daterange_id" />" maxlength="200"><input
								type="hidden" name="requestType" id="requestType" value="text" />
								<div id='1234' style='height:none; overflow-y:none; display:block;display: none'>
							<div id="depotin" >
							<table class="table table-striped table-bordered table-hover"
								id="viewVehicleDepotinEntry-out">
								<font color="green" size="2px"><s:property value="msg" /></font>
								<thead>
									<tr>
									    <th>Sr.No.</th>
										<th>Vehicle No.</th>
										<th>Schedule No.</th>
										<th>Shift</th>
										<th>Schedule Time</th>
										<th>Actual Time</th> 
										<!-- <th>Depot Entry Date & Time</th> -->
										<th>Status</th>
									</tr>
								</thead>

							</table>
							</div>
							</div>
							
							<div id='12345' style='height:none; overflow-y:none; display:block;display: none'>
							<div id="depotout" >
							<table class="table table-striped table-bordered table-hover"
								id="viewVehicleDepotinExit-out">
								<font color="green" size="2px"><s:property value="msg" /></font>
								<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Vehicle No.</th>
										<th>Schedule No.</th>
										<th>Shift</th>
										<th>Schedule Time</th>
										<th>Actual Time</th> 
										<!-- <th>Depot Exit Date & Time </th> -->
										<th>Status</th>
									</tr>
								</thead>

							</table>
							</div>
							</div>
 
						
						
						<div class="portlet-body" id="totalData">
							<table class="table table-striped table-bordered table-hover"
								>
								<thead> 
									<tr>
									<td id='Early'></td>
									<td id='Late'></td>
									<td id='Ontime'></td>
									</tr>
								</thead>

							</table>
						</div>
					</div>
					<div class="col-md-4" ></div>
					<div class="col-md-1" id="printbutton" >
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
						<div align="center">				
						<input type='button' class="btn default" id='button1' onclick='printDiv()' value='Print' />													
										</div>
										</div>
										
					
				</div>
				<!-- END EXAMPLE TABLE PORTLET-->
			</div>
		</div>
        <div id="header" class="portlet-body" style="display: none; visibility: hidden;">
										<h4 style="margin-left:350px;">Vehicle Depot In-Out Report</h4>
									<br />
									</div>
		<!-- END PAGE CONTENT-->
		<div style="display: none;" id="mymodal1" class="modal fade"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 800px; height: 500px;"
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
									<div class="portlet-body" id="depotdept"
										style="display: none">
										<table class="table table-striped table-bordered table-hover"
											id="depotdeptdetails">
											<thead>
												<tr>
													<th>Sr.No.</th>
													<th>Vehicle No.</th>
													<th>Depot Exit Time</th>
												</tr>
											</thead>
										</table>
									</div>
									<div class="portlet-body" id="depotArr"
										style="display: none">
										<table class="table table-striped table-bordered table-hover"
											id="depotArrdetails">
											<thead>
												<tr>
													<th>Sr.No.</th>
													<th>Vehicle No.</th>
													<th>Depot Entry Time</th>
												</tr>
											</thead>
										</table>
									</div>
								</div>
								</div>
							</div>
						</div>
					</div>
					</p>
				</div>

			</div>
		</div>

		<script>
		
		function getRefresh() {
			if ($("#depotlist").is(':checked')) {
				// plotVehicle();
			} else {
				clearInterval(intervalID);
			}
		}
			function setcurrDate() {
				//var div = document.getElementById("dashboard-report-range span").value;
				//var spans = div.getElementsByTagName("span");
				//alert( $('#dashboard-report-range span').val());
			}
			/*  $(document).ready(function() {
				 //  window.history.pushState("","", "ticketbag.action");
				 
				 }); */
			function deleteVehicleAlertConfig() {

				var cnt = $(":checkbox:checked").length;
				var val;
				if (cnt == 0) {
					bootbox.alert("Please Select Checkbox To Delete");
				} else if (cnt > 1) {
					bootbox.alert("Please Select One Checkbox To Delete");
				} else {
					$("input[type='checkbox']:checked").each(function() {

						val = this.value;
					});
					bootbox
							.confirm(
									"Are you sure you want to delete?",
									function(result) {

										if (result == true) {
											document.forms[0].action = 'deleteVehicleAlertConfig.action?alertconfigid='
													+ val;
											document.forms[0].submit();
										}
									});

				}

			}

			function editVehicleAlertConfig() {

				var cnt = $(":checkbox:checked").length;
				var val;
				if (cnt == 0) {
					bootbox.alert("Please Select Checkbox To Edit");
				} else if (cnt > 1) {
					bootbox.alert("Please Select One Checkbox To Edit");
				} else {
					$("input[type='checkbox']:checked").each(function() {

						val = this.value;
					});
					/* var val = $("input[type='checkbox']").val(); */
					//	alert(val);
					document.forms[0].action = 'editVehicleAlertConfig.action?alertconfigid='
							+ val;
					document.forms[0].submit();

				}

			}
			function createVehicleAlertConfig() {
				document.forms[0].action = "createVehicleAlertConfig.action";
				document.forms[0].submit();
			}
		</script>
	</form>
</body>
</html>