  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script> --%>
<%--  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" /> --%>
 <script src="assets/vts/js/vts.js"></script>
 
<script>
// function hidedata(){
// 	$(button11).hide();
	
// }
// window.onload=hidedata;

function getSchedule()
{
	
	$('#select2-chosen-3').html("Select");
	 var val=document.getElementById('depotlist1').value;
	 if(val!=0) {
		
 $.ajax({
	 
     type: "POST",
     url: '<%=request.getContextPath()%>/getScheduleList?val=' + val,
		success : function(result) {
			
			document.getElementById('scheduleId').innerHTML = result;
			
		}
	});
		 
}
}

function getDepotList(orgId){

		 var val=document.getElementById('divisionlist').value;
			 if(val!=0) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/getDepot?val=' + val,
				success : function(result) {
					document.getElementById('depotlist1').innerHTML = result;
					
				}
			});
		}
	}

function getDepot(){
	
	var dd1=$("#startdate").val();
	var depotId=$("#depotlist1").val();
	var scheduleNo=$("#scheduleId").val();
// 	var vehicleName =$('#vehicleId').find(":selected").text();
	var dd2=$("#endate").val();
	var var1=$("#startdate").val();
	var1=var1.split("-");
	var1=var1[2]+"-"+var1[1]+"-"+var1[0];
	var var2=$("#endate").val();
	var2=var2.split("-");
	var2=var2[2]+"-"+var2[1]+"-"+var2[0];
	 $('#kmplSchedData').dataTable({
			"aLengthMenu" : [ [ 10, 15, 20, -1 ], [ 10, 15, 20, "All" ] // change
			
			],
		
			"sAjaxSource" : "getKMPLScheduleWiseNewReport.action?date="+dd1+"&scheduleNo="+scheduleNo+"&depotId="+depotId+"&endate="+dd2,
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
				'#kmplSchedData_wrapper .dataTables_filter input')
				.addClass("form-control input-small input-inline"); // modify
		// table
		jQuery(
				'#kmplSchedData_wrapper .dataTables_length select')
				.addClass("form-control input-xsmall input-inline"); // modify         

}

</script>

<script>

 
function exportToExcel(btnExport){   
   var htmltable = document.getElementById("kmplSchedData");
   var html = htmltable.outerHTML;
   var noOfTableExist = 0;
   try{
		$("#kmplSchedData table").each(function(){
			noOfTableExist++;
		});
		
		/* If two table exist  then remove the last table on print click*/
		if(noOfTableExist >= 2){
			html = html.substring(0, html.indexOf("</table>") + 8) + "</div></div>";
		}
	}catch(err){
	    console.log("ExceptionCaught : " + err);
	}
   
   var downloadLink = document.createElement("a");
   downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
   downloadLink.download = "ScheduleWise KMPL  Report.xls";
   document.body.appendChild(downloadLink);
   downloadLink.click();
   document.body.removeChild(downloadLink);
 }
 

function exportToExcel1(btnExport){   
	
	
	 var inputHTML = "<table border='1'>";
	    inputHTML += "<tr>";
	    inputHTML += "<th  align='left'colspan='9'>Bangalore Metropolitan Transport Corporation</th>";
	    inputHTML += "</tr>";
	    inputHTML += "<th  align='left'colspan='9'>Schedule Wise KMPL Report</th>";
	    inputHTML += "</tr>";
	    inputHTML += "<tr>";
// 	    inputHTML += "<th colspan='2' align='left'>Date Range:</th>";
// 	    inputHTML += "<th colspan='7' align='left'>" + $("#startdate").val() + " to " + $("#endate").val() + "</th>";
	    inputHTML += "</tr>";
	    inputHTML +="<tr>";
	    inputHTML +="</tr>";
	    inputHTML += "</table>";
	
	
    //  document.getElementById("print").style.visibility='hidden';     
//     var divElements = document.getElementById("caption").innerHTML;
//     divElements += document.getElementById("kmplSchedData").innerHTML;
	
	
   var htmltable = document.getElementById("schedKMPL");
   var html =inputHTML+ htmltable.outerHTML;
   var noOfTableExist = 0;
   try{
		$("#schedKMPL table").each(function(){
			noOfTableExist++;
		});
		
		/* If two table exist  then remove the last table on print click*/
		if(noOfTableExist >= 2){
			html = html.substring(0, html.indexOf("</table>") + 8) + "</div></div>";
		}
	}catch(err){
	    console.log("ExceptionCaught : " + err);
	}
   
   var downloadLink = document.createElement("a");
   downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
   downloadLink.download = "ScheduleWise KMPL  Report Details.xls";
   document.body.appendChild(downloadLink);
   downloadLink.click();
   document.body.removeChild(downloadLink);
 }
 



function printDiv() {     
    
    //  document.getElementById("print").style.visibility='hidden';     

    divElements += document.getElementById("kmplSchedData").innerHTML;
    
    var noOfTableExist = 0;
    try{
		$("#kmplSchedData table").each(function(){
			noOfTableExist++;
		});
		
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
</script>
 
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
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			
			
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption" id="caption">
								<i class="fa fa-globe"></i>Schedule Wise KMPL Report
							</div>
						</div>
						
						<div class="portlet-body">
<!-- 						<div class="table-scrollable"> -->


							<!-- 						start -->
  <form action="" method="post" class="form-horizontal">
                        <div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Division<font
											color="red">*</font></label>
										<div class="col-md-3">
											<s:select list="divisionlist" id="divisionlist" 
											name="org_chart_id"  
												cssClass="select2_category form-control"  
												 onchange="getDepotList(this.value)" headerKey="0" headerValue="--Select--"></s:select>  
												 
										</div>
									</div>
								
                               <div class="form-group">
									<label class="col-md-3 control-label">Depot<font
										color="red">*</font></label>
									<div class="col-md-3">
										<select list="depotlist" id="depotlist1" class="select2_category form-control" name="depotlist1"
 											onChange="getSchedule(this.value)"> 
											<option value="0">--Select Depot--</option>
 										</select> 
									</div>
 								</div> 
 								
 								 <div class="form-group">
									<label class="col-md-3 control-label">Schedule No.<font
										color="red">*</font></label>
									<div class="col-md-3">
										<select  id="scheduleId" class="select2_category form-control" name="scheduleId"> 
											<option value="0">--ALL--</option>
 										</select> 
									</div>
 								</div> 
							<div class="form-group">
							  <label class="control-label col-md-3">From Date:</label>
								<div class="col-md-3">
								
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" ><!-- data-date-start-date="+0d"> -->
								<input id="startdate" class="form-control" type="text"  size="16" name="effectiveStartDate"
								value="<s:property value='rateMaster.effectiveStartDate' />"
								>
								<span class="input-group-btn">
								<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
								</button>
								</span>
								<script>
								var curr_date=new Date().toJSON().slice(0,10);
                                curr_date=curr_date.split("-"); 
                                 curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0]; 
                                 var dtval=document.getElementById('startdate').value;	 
                                
                                 if(dtval==''){
                              $('#startdate').val(curr_date); 
                            
                                } 
								</script>
								</div>
								</div></div>
								
								<div class="form-group">
							  <label class="control-label col-md-3">To Date:</label>
								<div class="col-md-3">
								
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" ><!-- data-date-start-date="+0d"> -->
								<input id="endate" class="form-control" type="text"  size="16" name="effectiveStartDate"
								value="<s:property value='rateMaster.effectiveStartDate' />"
								>
								<span class="input-group-btn">
								<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
								</button>
								</span>
								<script>
								var curr_date=new Date().toJSON().slice(0,10);
                                curr_date=curr_date.split("-"); 
                                 curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0]; 
                                 var dtval=document.getElementById('endate').value;	 
                                
                                 if(dtval==''){
                              $('#endate').val(curr_date); 
                            
                                } 
								</script>
								</div>
								</div></div>
 							
						<!-- end -->
						
						<div class="form-group">
					 <label class="control-label col-md-3"></label>
                     <div class="col-md-3" id="">
                     </div>
                     </div>
                     </div>
                     </form>
                      <div align='center'>
					<button type="submit" class="btn green" onClick="getDepot()">Submit</button>
					<span><input type='button' class='btn green' id='button1' onclick="printDiv()" value='Print' /></span>&nbsp;
					<input type='button' class='btn green' id='button1' onclick="exportToExcel()" value='Export' />
					<!-- END EXAMPLE TABLE PORTLET-->
				</div></div></div>
<!-- 					<div id="ticketconsuptionid"></div> -->

<div id="">		
 
							<table class="table table-striped table-bordered table-hover"
								id="kmplSchedData" >
								<thead>
									<tr>	
										<th>Sr No.</th>
										<th>Depot No</th>
										<th>Schedule No</th>
										<th>Sch KM</th>
										<th>Log KM</th>
										<th>GPS KM</th>
										<th>Fuel</th>
										<th>Log KMPL</th>
										<th>GPS KMPL</th>
										
									</tr>
								</thead>
							</table>	</div>
					
			</div>
			
			<!-- END PAGE CONTENT-->
		</div>
	</div>
	
			<div style="display: none;" id="mymodal44" class="modal fade"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel3"
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

									<div class="portlet-body" id="modalclass44">
									   <div align='center'>
				
					<input type='button' class='btn blue' id='button1' onclick="exportToExcel1()" value='Export' />
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
				<div></div>
				
									<div class="portlet-body" id="modalclass44">

										<div style="text-align:center">
				<table class="table table-striped table-bordered table-hover"
								id="schedKMPL">
								<thead >
										<tr>
												<th align="center">Sr. NO</th>
										      
												<th align="center">Schedule No</th>
												<th>Sch KM</th>
												<th>Log KM</th>
												<th>GPS KM</th>
												<th>Fuel</th>
												<th>Log KMPL</th>
												<th>GPS KMPL</th>
												<th align="center">Date</th>
											
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
	</div>
	
	</div>
	
	
