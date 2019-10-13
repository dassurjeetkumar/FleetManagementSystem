  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.13/datatables.min.js"></script> 
<script type="text/javascript" src="https://cdn.datatables.net/fixedcolumns/3.2.6/js/dataTables.fixedColumns.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.2/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.colVis.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/select/1.2.7/js/dataTables.select.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/pdfmake.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/vfs_fonts.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.4/js/buttons.html5.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.4/js/buttons.print.min.js"></script>

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.13/datatables.min.css"/>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/fixedcolumns/3.2.6/css/fixedColumns.dataTables.min.css"/>

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.5.2/css/buttons.dataTables.min.css"/>
<script>
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
	            url: '<%=request.getContextPath()%>/getDepotList?val=' + val,
				success : function(result) {
					document.getElementById('depotlist1').innerHTML = result;
					
				}
			});
		}
	}


function getDepot(){
	
		var dd1=$("#startdate").val();
		var depotId=$("#depotlist1").val();
		var divId=$("#divisionlist").val();
		var scheduleNo=$("#scheduleId").val();
		var dd2=$("#endate").val();
		var var1=$("#startdate").val();
		var1=var1.split("-");
		var1=var1[2]+"-"+var1[1]+"-"+var1[0];
		var var2=$("#endate").val();
		var2=var2.split("-");
		var2=var2[2]+"-"+var2[1]+"-"+var2[0];
	 $('#pageLoader').show();
	 
	 if (var1 <= var2){
        <%-- $.ajax({
        
            type: "post",
            url: '<%=request.getContextPath()%>/getOperatedScheduleKM.action?endate='+dd2+'&scheduleNo='+scheduleNo+'&date='+dd1+'&depotId='+depotId+'&divId='+divId,
            success: function(result) {
            	$('#pageLoader').hide();
                document.getElementById('ticketconsuptionid').innerHTML=result;
                fixHeader();
            }
        }); --%>
        
      
      //  table.destroy();
        //$('#scheduleTripKM').DataTable().destroy();

       
        table = $('#scheduleTripKM').removeAttr('width').DataTable(
				{
					
					scrollY:        "400px",
			        scrollX:        true,
			        scrollCollapse: true,
			        paging:         true,
			        columnDefs: [
			            { width: "3px", targets: 0 },
			            { width: "3px", targets: 1 },
			            { width: "3px", targets: 2 },
			            { width: "3px", targets: 3 },
			            { width: "3px", targets: 4 }
			        ],
			        aLengthMenu : [[10, 25, 50, -1], [10, 25, 50, "All"]],
			        bDestroy : true,
			        dom: 'lBfrtip',
			        buttons: [
			            'colvis',
			            'excel',
			            'pdf',
	                    'print'
			        ],
			        sAjaxSource: "getOperatedScheduleKM.action?endate="+dd2+"&scheduleNo="+scheduleNo+"&date="+dd1+"&depotId="+depotId+"&divId="+divId
					
					
				});
        
       
				
	 }else
			
			
			alert("To Month Should Be greater Than From Month");
			$('#pageLoader').hide();

	
}


</script>

<script>

 
function exportToExcel(btnExport){   
   var htmltable = document.getElementById("scheduleTripKM");
   var html = htmltable.outerHTML;
   var noOfTableExist = 0;
   try{
		$("#scheduleTripKM table").each(function(){
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
   downloadLink.download = "ScheduleWise Distance Travelled  Report.xls";
   document.body.appendChild(downloadLink);
   downloadLink.click();
   document.body.removeChild(downloadLink);
 }

function printDiv() {     
    
    //  document.getElementById("print").style.visibility='hidden';     
    var divElements = "";//document.getElementById("header").innerHTML;
    divElements += document.getElementById("scheduleTripKM").innerHTML;
    
    var noOfTableExist = 0;
    try{
		$("#scheduleTripKM table").each(function(){
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
							<div class="caption">
								<i class="fa fa-globe"></i>Schedule wise Distance Travelled
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
												 onchange="getDepotList(this.value)" headerKey="0" headerValue="--ALL--"></s:select>  
												 
										</div>
									</div>
								
                               <div class="form-group">
									<label class="col-md-3 control-label">Depot<font
										color="red">*</font></label>
									<div class="col-md-3">
										<select list="depotlist" id="depotlist1" class="select2_category form-control" name="depotlist1"
 											onChange="getSchedule(this.value)"> 
											<option value="0">--ALL--</option>
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
				</div>
					<div id="">
					<table class="table table-striped table-bordered table-hover display nowrap" id="scheduleTripKM" style="width: 1731px;height: 400px;">
										<thead >
											<tr>
												<th>Sr.<br/> NO</th>
												<th>Depot<br/> Code</th>
												<th>Date</th>
												<th>Schedule<br/> Name</th>
												<th>Schedule<br/> Type</th>
												<th>Vehicle<br/> No</th>
												<th>Start Time<br/>( Vehicle exits <br/>depot as <br/>per VTU)</th>
												<th>Start Time<br/>( As per ETM)</th>
												<th>Diff In ETM <br/>start time<br/>( VTU start <br/>time- ETM <br/>start time)</th>
												<th>End Time<br/>(Vehicle enters<br/> depot as<br/> per VTU)</th>
												<th>End Time<br/>( As per ETM)</th>
												<th>Diff In<br/> ETM end<br/> time<br/>( VTU end<br/> time- ETM<br/> end time)</th>
												<th>Schedule <br/>KM</th>
												<th colspan="3">ITS Operated KM</th>
												<th>Logsheet <br/>Operated <br/>KM</th>
												<th>Difference</th>
												<th>Cancel KM<br/>(Schedule KM<br/>-ITS KM)<br/> (excluding<br/>completed<br/>trip)</th>
												<th>Difference<br/>(Schedule KM<br/>Distance-<br/>ITS operated KM<br/>( irrespective<br/>of trip<br/>status)</th>
												<th>Logshit <br/>Cancel <br/>KM</th>
												<th>Difference <br/>of <br/>Cancel <br/>KM</th>
												<th colspan="3">ITS Cancellation</th>
												<th>Logsheet <br/>Cancel <br/>Trips</th>
												<th>Vehicle <br/>Travelled<br/>(KM)</th>
												<th>Vehicle <br/>Deviated<br/>(KM)</th>
												<th colspan="3">Revenue</th>
												<th>Status</th>
											</tr>
											
											<tr>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th colspan="1">GPS<br/>Operated<br/>KM</th>
												<th colspan="1">ETM<br/>Operated<br/>KM<br/>(for<br/>non "0"<br/>revenue<br/>trips)</th>
												<th colspan="1">ETM<br/>+<br/>GPS KM</th>
												<th></th>
												<th>(Logsheet<br/>- ITS KM) </th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th colspan="1">ETM<br/>Cancel<br/>Trips</th>
												<th colspan="1">GPS<br/>Cancel<br/>Trips</th>
												<th colspan="1">ITS<br/>Cancel<br/>Trips</th>
												<th></th>
												<th></th>
												<th></th>
												<th colspan="1">ETM</th>
												<th colspan="1">Manual</th>
												<th colspan="1">Total</th>
												<th></th>
											</tr>
											
											<tr>
												<th>1</th>
												<th>2</th>
												<th>3</th>
												<th>4</th>
												<th>5</th>
												<th>6</th>
												<th>7</th>
												<th></th>
												<th>8</th>
												<th>9</th>
												<th>10</th>
												<th></th>
												<th>11</th>
												<th colspan="1">12A</th>
												<th colspan="1">12B</th>
												<th colspan="1">12C</th>
												<th>13</th>
												<th>14</th>
												<th>15</th>
												<th>16(13-12)</th>
												<th>17</th>
												<th>18</th>
												<th colspan="1">19A</th>
												<th colspan="1">19B</th>
												<th colspan="1">19C</th>
												<th>20</th>
												<th>21(12A+22)</th>
												<th>22</th>
												<th colspan="1">23A</th>
												<th colspan="1">23B</th>
												<th colspan="1">23C</th>
												<th>24</th>
											</tr>
												
										</thead>
									</table>
					</div>
					
			</div>
			
			<!-- END PAGE CONTENT-->
		</div>
	</div>
	<style>
	/* table{
  margin: 0 auto;
  width: 100%;
  clear: both;
  border-collapse: collapse;
  table-layout: fixed; // ***********add this
  word-wrap:break-word; // ***********and this */
}
	</style>
	
	
	
