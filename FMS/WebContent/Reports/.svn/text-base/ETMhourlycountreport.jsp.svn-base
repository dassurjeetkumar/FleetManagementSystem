  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>

 
<script>

function getDeviceList()
{
	
	$('#select2-chosen-3').html("Select");
	 var val=document.getElementById('depotlist1').value;
	 if(val!=0) {
		
 $.ajax({
	 
     type: "POST",
     url: '<%=request.getContextPath()%>/getDeviceList?val=' + val,
		success : function(result) {
			
			document.getElementById('deviceId').innerHTML = result;
			
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

function getHourlyData(){
	
	
	var dd1=$("#startdate").val();
	var depotId=$("#depotlist1").val();
	var deviceName =$('#deviceId').find(":selected").text();
	var var1=$("#startdate").val();
	var1=var1.split("-");
	var1=var1[2]+"-"+var1[1]+"-"+var1[0];
	 $('#hourlyData').dataTable({
			"aLengthMenu" : [ [ 10, 15, 20, -1 ], [ 10, 15, 20, "All" ] // change
			
			],
		
			"sAjaxSource" : "AjaxETMhourlycountreport.action?date="+dd1+"&deviceNo="+deviceName+"&depotId="+depotId,
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
				'#hourlyData_wrapper .dataTables_filter input')
				.addClass("form-control input-small input-inline"); // modify
		// table
		jQuery(
				'#hourlyData_wrapper .dataTables_length select')
				.addClass("form-control input-xsmall input-inline"); // modify         

}

</script>

<script>

 
function exportToExcel1(){   
	
	
	 var inputHTML = "<table border='1'>";
	    inputHTML += "<tr>";
	    inputHTML += "<th  align='left'colspan='9'>Bangalore Metropolitan Transport Corporation</th>";
	    inputHTML += "</tr>";
	    inputHTML += "<th  align='left'colspan='9'>Daily ETM Packet Count Report</th>";
	    inputHTML += "</tr>";
	    inputHTML += "<tr>";
 	    inputHTML += "<th colspan='2' align='left'>Date:</th>";
 	    inputHTML += "<th colspan='7' align='left'>" + $("#startdate").val();
	    inputHTML += "</tr>";
	    inputHTML +="<tr>";
	    inputHTML +="</tr>";
	    inputHTML += "</table>";
	    var divElements  =inputHTML;
	     divElements += "<table><tr>"+document.getElementById("hourlyData").innerHTML+"</tr></table>";
		var noOfTableExist = 0;
		try {
			$("#hourlyData table").each(function() {
				noOfTableExist++;
			});

			console.log("Total no of tables : " + noOfTableExist);
			/* If two table exist  then remove the last table on print click (for header fixed)*/
			if (noOfTableExist >= 2) {
				divElements = divElements.substring(0, divElements
						.indexOf("</table>") + 8)
						+ "</div></div>";
			}
		} catch (err) {
			console.log("ExceptionCaught : " + err);
		}


		var downloadLink = document.createElement("a");
		downloadLink.href = 'data:application/vnd.ms-excel,'
				+ encodeURIComponent(divElements);
		downloadLink.download = "Daily ETM Transaction Count.xls";
		document.body.appendChild(downloadLink);
		downloadLink.click();
		document.body.removeChild(downloadLink);

 }
 



function printDiv() {     
    
     var inputHTML = "<table border='1'>";
    inputHTML += "<tr>";
    inputHTML += "<th  align='left'colspan='9'>Bangalore Metropolitan Transport Corporation</th>";
    inputHTML += "</tr>";
    inputHTML += "<th  align='left'colspan='9'>Daily ETM Transaction Count Report</th>";
    inputHTML += "</tr>";
    inputHTML += "<tr>";
    inputHTML += "<th colspan='2' align='left'>Date :</th>";
    inputHTML += "<th colspan='7' align='left'>" + $("#startdate").val() ;
    inputHTML += "</tr>";
    inputHTML += "</table>";
  
    var divElements = document.getElementById("header").innerHTML;
    divElements +=inputHTML;
    divElements += "<table><tr>"+document.getElementById("hourlyData").innerHTML+"</tr></table>";
    

    var noOfTableExist = 0;
    try{
		$("#hourlyData table").each(function(){
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
						<div class="portlet-title" id="header">
							<div class="caption" id="caption">
								<i class="fa fa-globe"></i>Daily ETM Transaction Count 
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
 											onChange="getDeviceList(this.value)"> 
											<option value="0">--Select Depot--</option>
 										</select> 
									</div>
 								</div> 
 								
 								 <div class="form-group">
									<label class="col-md-3 control-label">Device No.<font
										color="red">*</font></label>
									<div class="col-md-3">
										<select  id="deviceId" class="select2_category form-control" name="deviceId"> 
											<option value="0">--ALL--</option>
 										</select> 
									</div>
 								</div> 
							<div class="form-group">
							  <label class="control-label col-md-3"> Date:</label>
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
 							
						<!-- end -->
						
						<div class="form-group">
					 <label class="control-label col-md-3"></label>
                     <div class="col-md-3" id="">
                     </div>
                     </div>
                     </div>
                     </form>
                      <div align='center'>
					<button type="submit" class="btn green" onClick="getHourlyData()">Submit</button>
					<span><input type='button' class='btn green' id='button1' onclick="printDiv()" value='Print' /></span>&nbsp;
					<input type='button' class='btn green' id='button1' onclick="exportToExcel1()" value='Export' />
					<!-- END EXAMPLE TABLE PORTLET-->
				</div></div></div>

 </div>
                        <div id="hourlyETM">		
 
							<table class="table table-striped table-bordered table-hover"
								id="hourlyData" >
								<thead>
									<tr>	
										<th>SrNo</th>
										<th>ETIM_No</th>
										<th>Total</th>
										<th>1Hr</th>
										<th>2Hr</th>
										<th>3Hr</th>
										<th>4Hr</th>
										<th>5Hr</th>
										<th>6Hr</th>
										<th>7Hr</th>
										<th>8Hr</th>
										<th>9Hr</th>
										<th>10Hr</th>
										<th>11Hr</th>
										<th>12Hr</th>
										<th>13Hr</th>
										<th>14Hr</th>
										<th>15Hr</th>
										<th>16Hr</th>
										<th>17Hr</th>
										<th>18Hr</th>
										<th>19Hr</th>
										<th>20Hr</th>
										<th>21Hr</th>
										<th>22Hr</th>
										<th>23Hr</th>
										<th>24Hr</th>
									</tr>
								</thead>
							</table>	
							</div>
			</div>
			
			<!-- END PAGE CONTENT-->
		</div>
	</div>
	

	
	
