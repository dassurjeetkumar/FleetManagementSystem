  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
 <script>

 </script>
<script>

function getHealthData(){
	//$("acc66axisnotsett").empty();
	$('#data').attr("style", "display:''");
	var startdate=$("#startdate").val();
	var reason=$("#reason").val();
// alert(startdate+"======="+reason);
// $("#pageLoader").show();
    table = $('#healthdevicesData');
    var oTable = table.dataTable({
    	"aLengthMenu" : [ [ 5,10, 15, 20, -1 ], [ 5,10, 15, 20, "All" ] // change
		// per
		// page
		// values
		// here
		],
		// set the initial value
		"iDisplayLength" : -1,
		"sAjaxSource" : "AjaxGetHealthDevices.action?startdate="+startdate+"&reason="+reason,
		"sPaginationType" : "bootstrap",
		"bDestroy" : true,
		"bSort" : true,
		"bFilter" : true,
		"bSelect" : true,
		"bPaginate" : false,
		"bInfo": true,
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

	jQuery('#healthdevicesData_wrapper .dataTables_filter input').addClass(
			"form-control input-xsmall input-inline"); // modify table
	// search input
	jQuery('#healthdevicesData_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");	
}

</script>
<script>
		
function printDiv() {     
	  document.getElementById("data").style.visibility='visible';  
	     document.getElementById("header").style.display='block';
	     document.getElementById("header").style.visibility='visible'; 
	    
	     
	     var divElements = document.getElementById("header").innerHTML;
	     divElements+= "<table>"+document.getElementById("healthdevicesData").innerHTML+"</table>";
	     var mywindow = window.open("<%=request.getContextPath()%>/Print1.jsp");
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
			
			
			
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption" id="header">
								<i class="fa fa-globe"></i>Weekly Health Status
							</div>
						</div>
						
						<div class="portlet-body">
<!-- 						


							<!-- 						start -->

                     <form action="" method="post" class="form-horizontal">
                        <div class="form-body">

								
                           <div class="form-group">
									<label class="col-md-3 control-label">Reason :<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select list="reason" id="reason" class="select2_category form-control" name="reason"> 
											<option value="0">--Select--</option>
											<option value="1">NRD</option>
											<option value="2">Zero Lat/Long</option>
											<option value="3">>1000 Packets</option>
 										</select> 
									</div>
 								</div>  
 					
						<!-- end -->
								<div class="form-group">
							  <label class="control-label col-md-3">Date:<font
										color="red">*</font></label>
								<div class="col-md-3">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" data-date-end-date="0d"> 
								<input id="startdate" name="dateaction" class="form-control" type="text" readonly="" size="16" name="rateMaster.effectiveStartDate" placeholder="Pick The Date" "
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
					
                     </div>
                     </form>
				
					 <div align='center'>
					<button type="submit" class="btn blue" onClick="getHealthData()">Submit</button>
					<span><input type='button' class='btn blue' id='button1' onclick='printDiv()' value='Print' /></span>&nbsp;
					<button type="submit" class="btn blue" onClick="tabletoExcel()">Export</button>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
							<div class="portlet-body" id="data"
														style="display: none">
														<div style="text-align:left">
														<table
															class="table table-striped table-bordered table-hover"
															id="healthdevicesData">
															<thead>
													<tr>
														<th>Sc No</th>
														<th>Depot</th>
														<th>Vehicle No</th>
														<th>Device No</th>
														
														
														
	</tr>
				</thead>
														</table>
													</div>
                                                   </div>
			</div>
			
			<!-- END PAGE CONTENT-->
			
			
		
		</div>
	</div></div></div></div></head>
	
	<script type="text/javascript">
    function tabletoExcel(btnExport){     
    	/*  document.getElementById("mapshow").style.visibility='hidden'; 
    		$(".mapClass").hide(); */
   	     
    	     var inputHTML = "<table border='1'>";
             inputHTML += "<tr>";
             inputHTML += "<th  align='center'colspan='8'>Bangalore Metropolitan Transport Corporation</th>";
             inputHTML += "</tr>";
            inputHTML += "<tr>";
             inputHTML += "<th  align='Center'colspan='8'>Weekly VTU Health</th>";
             inputHTML += "</tr>";
           
             inputHTML += "</table>";
    		   document.getElementById("data").style.visibility='visible';  
    	     document.getElementById("header").style.display='block';
    	     document.getElementById("header").style.visibility='visible';     
    	     var divElements = inputHTML+document.getElementById("header").innerHTML;
    	     divElements+= "<table>"+document.getElementById("healthdevicesData").innerHTML+"</table>";
     
     var noOfTableExist = 0;
     try{
 		$("#data table").each(function(){
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
    
    var downloadLink = document.createElement("a");
    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(divElements);
    downloadLink.download = "Active Etm Data.xls";
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
}
	</script>