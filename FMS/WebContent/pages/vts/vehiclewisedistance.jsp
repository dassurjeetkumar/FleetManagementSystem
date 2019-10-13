
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="assets/vts/js/vehiclealert.js" type="text/javascript"></script>
<script type="text/javascript" src="assets/report/jspdf.min.js"></script>
<script type="text/javascript" src="assets/report/deflate.js"></script>
<script type="text/javascript" src="assets/report/adler32cs.js"></script>
<script type="text/javascript" src="assets/report/jspdf.source.js"></script>
<script type="text/javascript" src="assets/report/jspdf.amd.min.js"></script>
<script type="text/javascript" src="assets/report/jspdf.plugin.cell.js"></script>

<script type="text/javascript" src="assets/report/jspdf.plugin.from_html.js"></script>
<script type="text/javascript" src="assets/report/jspdf.plugin.javascript.js"></script>
<script type="text/javascript" src="assets/report/jspdf.plugin.split_text_to_size.js"></script>
<script type="text/javascript" src="assets/report/jspdf.PLUGINTEMPLATE.js"></script>
<script type="text/javascript" src="assets/report/jspdf.plugin.standard_fonts_metrics.js"></script>
<script>
function getDepot(orgId){
	//alert('Here');
	 /* var selectedValue = $('#form-control').val(); */
	 var val=document.getElementById('divisionlistid').value;
		 if(val!=0) {
        $.ajax({
            type: "get",
            url: '<%=request.getContextPath()%>/getDepot?val=' + val,
				success : function(result) {
					document.getElementById('depotlistid').innerHTML = result;
				}
			});
		}
		
	}
	
	function getVehicleData1(){
// 		var val=document.getElementById('depotlistid').value;
// // 		alert();
// console.log(val);
// 		getVehicle("");
	}
	
function getVehicle(depotID)
{
	$('#select2-chosen-3').html("Select");
	var val=document.getElementById('depotlistid').value;
// 	alert(val);
	 if(val!=0) {
// 		 	alert();

		 $("#msg").html("Please wait...");
   	$.ajax({
	   type: "GET",
       url: '<%=request.getContextPath()%>/getLiveVehicle?val='+val,
       success: function(result) {
    	   $("#msg").html("");
    	   document.getElementById('vehiclelist').innerHTML=result;
       }
   });
}else{
// 	 getVehicle("");
// 	 alert();
}
}
	
	
function printDiv() {     
	 
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

    
            
}

	/* function getTable(){
		$("#tableid").hide();
	} */
</script>
<body onload="getTable()">
<div class="page-content-wrapper">
	<div class="page-content">
	
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
					VEHICLE WISE DISTANCE TRAVELLED<small></small>
				</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i>View Vehicle Wise Distance Travelled
						</div>
					</div>
				
				<div class="portlet-body">
				
				<!-- page loading start -->
				<div id="pageLoader" class="modal fade in" tabindex="-1" role="dialog" aria-labelledby="myModalLabel3" aria-hidden="false">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<p>
							<img src="assets/images/loader1.gif" align="top"
								style="margin-left: 100px;" />
						</p>
						<p style='text-align: center'>Please Wait........</p>
					</div>
				</div>
			</div>
		</div>
				<!-- //end page loading end -->
				<form action=""	method="post" class="form-horizontal">
					<%-- <div class="form-group">
						<label class="control-label col-md-3">Date <span
							class="required">* </span></label>
						<div class="col-md-4">
							<div class="input-group input-medium date date-picker"
								style="width: auto" data-date-format="yyyy-mm-dd"
								data-date-viewmode="years">
								<input type="text" class="form-control" id="selectdate" readonly
									name="selecteselecteddateddate" /> <span
									class="input-group-btn">
									<button class="btn default" type="button">
										<i class="fa fa-calendar"></i>

									</button>
								</span>
								<script>
									var curr_date = new Date().toJSON().slice(
											0, 10);
									curr_date = curr_date.split("-");
									curr_date = curr_date[0] + "-"
											+ curr_date[1] + "-" + curr_date[2];
									$('#selectdate').val(curr_date);
								</script>
							</div>
						</div>
					<%-- </div> --%>
                  <%--  <div class="form-group">
<!-- 				 <label class="control-label col-md-2">Start Date/ Time </label> -->
				
					<div class="col-md-3">
					
						<div class="input-group date form_datetime">
							<input type="text" size="16" readonly name="startdate"
								id="startdate" class="form-control" value='<s:property value="startdate"/>'> <span
								class="input-group-btn">
								<button class="btn default date-set" type="button">
									<i class="fa fa-calendar"></i>
								</button>
							</span>
						</div>
					</div>
					
<!-- 					<label class="control-label col-md-2">End Date/ Time </label> -->
					<div class="col-md-3">
					<div class="input-group date form_datetime">
						<input type="text" size="16" readonly name="enddate" id="enddate" value='<s:property value="enddate"/>'
							class="form-control"> <span
							class="input-group-btn">
							<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
							</button>
						</span>
					</div>
					</div>
				
				</div>  --%>
				
				

					<div class="form-group">
						<label class="control-label col-md-3">Division:<font
							color="red">*</font></label>
						<div class="col-md-4">
							<s:select list="divisionlist" id="divisionlistid"
								name="orgchart.org_chart_id"
								cssClass="select2_category form-control"  onchange="getDepot(this.value)"></s:select>
						</div>
						
						<script>
					getDepot("");
					</script>

					</div>


					<div class="form-group">
					 	<label class="control-label col-md-3">Depot:<font
							color="red">*</font></label>
						<div class="col-md-4">
							<select id="depotlistid" name="depotlistid" class="select2_category form-control" >
								
<!-- 								<option value="0">Depot</option> -->
							</select>
						</div>
						<script>
// 						var depot=document.getElementById('depotlistid').value;
// 						var dd1=$("#depotlistid").val();
// 						alert("depot=="+depot);
// 						getVehicle("");
// 						getVehicle("");
// 						getVehicle("");
						</script>
						</div>
						
							<table>
							  <tr>
							  <td><label class="control-label col-md-4">From:<font
							color="red">*</font></label>
								<div class="col-md-4">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" ><!-- data-date-start-date="+0d"> -->
								<input id="startdate" class="form-control" type="text" readonly="" size="16" name="rateMaster.effectiveStartDate" onchange="getVehicle(this.value);"
								value="<s:property value='rateMaster.effectiveStartDate' />"
								>
								<span class="input-group-btn">
								<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
								</button>
								</span>
								
								</div>
								</div></td>   
							  
							  <td><label class="control-label col-md-3">To:<font
							color="red">*</font></label>
								<div class="col-md-4">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" ><!-- data-date-start-date="+0d"> -->
								<input id="endate" class="form-control" type="text" readonly="" size="16" name="rateMaster.effectiveStartDate"
								value="<s:property value='rateMaster.effectiveStartDate' />"
								>
								<span class="input-group-btn">
								<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
								</button>
								</span>
								
								</div>
								</div></td> 
<!-- 								<td><button type="submit" class="btn green" onClick="getDepot()">Submit</button> -->
<%-- 								<span><button type="button" class="btn green" id="btnExport" filename= "report" onclick="tabletoExcel();"> EXPORT </button></span>&nbsp;<span>  --%>
                
<!-- 								</td>   -->
							  </tr>
							  <tr><td></td></tr>
							</table>
							<div class="form-group">
						
						<div class="col-md-4">
							
						</div>

					</div>
						
						
						<div class="form-group">
						<label class="control-label col-md-3">Vehicle No.:<font
							color="red">*</font></label>
						<div class="col-md-4">
							<select  id="vehiclelist"  class="select2_category form-control"
							onchange="getCordinatesvalue(this.value)">
							<option value="0">Vehicle Number</option>
							</select>
						</div>
						

					

						
						
							<script>  
					
							function tabletoPDF(){
								//var htmltable= document.getElementById('btnExport');   
							    $( "#header-fixed" ).remove();
							    var inputHTML = "<table border='1'>";
							    inputHTML += "<tr>";
							    inputHTML += "<th  align='left'colspan='9'>Bangalore Metropolitan Transport Corporation</th>";
							    inputHTML += "</tr>";
							    inputHTML += "<th  align='left'colspan='9'>VEHICLE WISE DISTANCE TRAVELLED</th>";
							    inputHTML += "</tr>";
							    inputHTML += "<tr>";
							    inputHTML += "<th colspan='2' align='left'>Date Range:</th>";
							    inputHTML += "<th colspan='7' align='left'>" + $("#startdate").val() + " to " + $("#endate").val() + "</th>";
							    inputHTML += "</tr>";
							    inputHTML += "</table>";
							    var htmltable = document.getElementById("tripstatus");
							    var html = inputHTML + htmltable.outerHTML;
							    console.log(html);
							    var table = tableToJson($('#vehiclewisedistance').get(0))
							    var doc = new jsPDF('p', 'pt', 'a4', true);
							    doc.cellInitialize();
							    $.each(table, function (i, row){
							      $.each(row, function (j, cell){
							        doc.cell(10, 200, 100, 20, cell, i);
							      })
							    })
							    doc.save();
							}
							function tableToJson(table) {
							    var data = [];

							    // first row needs to be headers
							    var headers = [];
							    for (var i=0; i<table.rows[0].cells.length; i++) {
							        headers[i] = table.rows[0].cells[i].innerHTML.toLowerCase().replace(/ /gi,'');
							    }


							    // go through cells
							    for (var i=0; i<table.rows.length; i++) {

							        var tableRow = table.rows[i];
							        var rowData = {};

							        for (var j=0; j<tableRow.cells.length; j++) {

							            rowData[ headers[j] ] = tableRow.cells[j].innerHTML;

							        }

							        data.push(rowData);
							    }       

							    return data;
							}
							
							
  	 function tabletoExcel(btnExport){     
  	var htmltable= document.getElementById('btnExport');   
    $( "#header-fixed" ).remove();
    var inputHTML = "<table border='1'>";
    inputHTML += "<tr>";
    inputHTML += "<th  align='left'colspan='9'>Bangalore Metropolitan Transport Corporation</th>";
    inputHTML += "</tr>";
    inputHTML += "<th  align='left'colspan='9'>VEHICLE WISE DISTANCE TRAVELLED</th>";
    inputHTML += "</tr>";
    inputHTML += "<tr>";
    inputHTML += "<th colspan='2' align='left'>Date Range:</th>";
    inputHTML += "<th colspan='7' align='left'>" + $("#startdate").val() + " to " + $("#endate").val() + "</th>";
    inputHTML += "</tr>";
    inputHTML += "</table>";
    var htmltable = document.getElementById("tripstatus");
    var html = inputHTML + htmltable.outerHTML;
    var downloadLink = document.createElement("a");
    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
    downloadLink.download = "VEHICLE WISE DISTANCE TRAVELLED.xls";
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
}</script>
						
						<div class="col-md-1" id="">
									<button type="button" class="btn default" onclick="getvehicleData(this.value)" style="position: static;">Submit</button>
									</div>
									<div class="col-md-1"id="printbutton">
											<input type='button' class="btn default" id='button1' onclick='printDiv()' value='Print' />													
										</div>
								<div class="col-md-1" id="">
								 <button type="button" class="btn default" id="btnExport" filename= "report" onclick="tabletoExcel();"> EXPORT </button>
                               		
					</div>
			
</form>
</div>

                     <div id="header" class="portlet-body" style="display: none; visibility: hidden;">
										<h4 style="margin-left:350px;">Vehicle Wise Distance Travelled Report</h4>
									<br />
									<div id="headerdetails" style="margin-left:0px;">
<!--                             	        <table ID="mytable" style="width:60%;border:none;"> -->
<!-- 														<tr> -->
<!-- 									 						<td ><b><label><font size="2">Schedule No :</font></label></b> -->
<!-- 															<td style="text-align: left;"> -->
<%-- 			 												<b><font size="2"><span  id="scheduleno" style="text-align: left;margin-left:10px;"></span></font></b></td> --%>
								
				
						 							
<!-- 															<td ><b><font size="2"><label> Schedule Type: </label></font></b> -->
<!-- 															<td style="text-align: left;"> -->
<%-- 																		 <b><font size="2"><span id="scheduletype" style="text-align:left;margin-left:10px;"></span></font></b></td> --%>
<!-- 														</tr> -->
<!-- 														<tr> -->
<!-- 															<td><font size="2"><label>Date:</label></font> -->
<%-- 															<!-- <td nowrap style="text-align: left;"> --><font size="2"><b><span id="selectdate" style="text-align:left;margin-left:50px;"></span></font></b></td> --%>
															
<%-- 															<td style="text-align: left;"><b><font size="2"><label>From:</label></font><font size="2"><b><span id="fromDate" style="text-align:left;margin-left:50px;"></span></font></b></td> --%>
<%-- 															<td><font size="2"><label><s:property --%>
<%-- 																		value="" /> </label></font></td> --%>
<%-- 																		<td nowrap style="text-align: left;"><b><font size="2"><label>To:</label></font><font size="2"><b><span id="toDate" style="text-align:left;margin-left:50px;"></span></font></b></td> --%>
<!-- 															<td nowrap></td> -->
<!-- 														</tr> -->
<!-- 														<tr> -->
															
<%-- 															<td nowrap><b><font size="2"><label>Base Route: </label></font><font size="2"><b><span id="baseRoute" style="text-align:left;margin-left:50px;"></span></font></b></td> --%>
<!-- 															<td nowrap></td> -->
<!-- 														</tr> -->
<!-- 													</table> -->
<!-- 													 <br/><br/>  -->
                                    
                                </div>
                                </div>

				<div id="tableid" class="portlet-body" style='height:310px; overflow-y:scroll; display:block;'>
                <div id="tripstatus">
					<table class="table table-striped table-bordered table-hover"
						id="vehiclewisedistance">
						<div align="center">
						<thead>
							<tr>
							    <th>Sr No</th>
								<th>Vehicle No.</th>
								<th>Device No.</th>
								<th>KM Travelled</th>
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
</body>