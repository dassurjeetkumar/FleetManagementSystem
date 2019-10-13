<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>

<script src="https://code.jquery.com/jquery-1.11.3.min.js" type="text/javascript"></script>
	
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />

<Script>

 function printDiv1() {     
	    
	    //  document.getElementById("print").style.visibility='hidden';     
	    var divElements = document.getElementById("header").innerHTML;
	    divElements += document.getElementById("ticketconsuptionid").innerHTML;
	    
	    var noOfTableExist = 0;
	    try{
			$("#ticketconsuptionid table").each(function(){
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


	function getsettlementreport(){
		
	  	var dd1=$("#startdate").val();
		var dd2=$("#enddate").val();
 		var var1=$("#startdate").val();
 		var1=var1.split("-");
		var1=var1[2]+"-"+var1[1]+"-"+var1[0];
 		var var2=$("#enddate").val();
 		var2=var2.split("-");
 		var2=var2[2]+"-"+var2[1]+"-"+var2[0];
		//alert(var1+""+var2);
		
		 var d1 = Date.parse(var1);
		 //var depot=$("#depotlist").val();
		 var selectTag='';
 	var d3=Date.parse(var2);
 		if (d1 <= d3){
 			 $('#pageLoader').show();
 			/* while( selectTag[0] ) {
        	    selectTag[0].parentNode.removeChild( selectTag[0] );
        	    
        	} */
        $.ajax({
        
            type: "post",
            url: '<%=request.getContextPath()%>/getaxissettlementdetailreport.action?startdate='+dd1+'&enddate='+dd2,
            success: function(result) {
            	$('#pageLoader').hide();
            	
            	 selectTag=document.getElementById('ticketconsuptionid').innerHTML=result;
                //var selectTag = document.getElementsByClassName('ticketconsuptionid');

  // Remove all of them.

fixHeader();   
            }
        });
		}else{
 			
 			
 			alert("To Date Should Be greater Than Start Date");
 			$('#pageLoader').hide();
 			 document.getElementById('ticketconsuptionid').innerHTML="";
		}
	
}
function getdepotwiseexceptionreport(depotid,startdate,enddate){
	  $.ajax({
	        
          type: "post",
          url: '<%=request.getContextPath()%>/Alertdepotwiseaxissettlementreport.action?startdate='+startdate+'&enddate='+enddate+'&depotid='+depotid,
          success: function(result) {
          	$('#pageLoader').hide();
          	
          	 selectTag=document.getElementById('ticketconsuptionid1').innerHTML=result;
              //var selectTag = document.getElementsByClassName('ticketconsuptionid');

// Remove all of them.

fixHeader();   
          }
      });

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
							Axis Settlement Detail Report (ITS-AXIS) <small></small>
						</h3>

						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Axis Settlement Detail Report
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
						
						

						<div class="portlet-body">
						<div class="table-scrollable">
							
							<table align="center">
							  <tr>
							  <td align="center">
							  <label class="control-label col-md-4">From Date:</label>
								<div class="col-md-4">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" data-date-end-date="-2d">
								<input id="startdate" class="form-control" type="text" readonly="" size="16" name="rateMaster.effectiveStartDate"
								value="<s:property value='rateMaster.effectiveStartDate' />"
								>
								<span class="input-group-btn">
								<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
								</button>
								</span>
						<%-- 		<script>
										var curr_date=new Date().toJSON().slice(0,10);
                                        curr_date=curr_date.split("-");
                                        curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
                                        var dtval=document.getElementById('startdate').value;	
                                        
                                        if(dtval==''){
                                        $('#startdate').val(curr_date);
                                        }
								</script> --%>
								</div>
								</div></td>   
						<!-- 	 ======== -->
								 	  <td align="center">
							  <label class="control-label col-md-4">To Date:</label>
								<div class="col-md-4">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" data-date-end-date="-2d">
								<input id="enddate" class="form-control" type="text" readonly="" size="16" name="rateMaster.effectiveStartDate"
								value="<s:property value='rateMaster.effectiveStartDate' />"
								>
								<span class="input-group-btn">
								<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
								</button>
								</span>
							<%-- 	<script>
										var curr_date=new Date().toJSON().slice(0,10);
										//curr_date=curr_date.getDate()-2;
                                        curr_date=curr_date.split("-");
                                        curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
                                        var dtval=document.getElementById('enddate').value;	
                                        
                                        if(dtval==''){
                                        $('#enddate').val(curr_date);
                                        }
								</script>  --%>
								</div>
								</div></td>  
								
								<td><button type="submit" class="btn green" onClick="getsettlementreport()">Submit</button>
								</td> 
							 </tr>
							</table>
							
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
  	             inputHTML += "<th  align='left'colspan='9'>AXIS SETTLEMENT  Detail  REPORT</th>";
  	             inputHTML += "</tr>";
//   	             inputHTML += "<tr>";
//   	             inputHTML += "<th colspan='2' align='left'>Date Range:</th>";
//   	             inputHTML += "<th colspan='7' align='left'>" + $("#startdate").val() + " to " + $("#endate").val() + "</th>";
//   	             inputHTML += "</tr>";
  	             inputHTML += "</table>";
  	             var htmltable = document.getElementById("ticketconsuptionid");
  	             var html = inputHTML + htmltable.outerHTML;
  	             var downloadLink = document.createElement("a");
  	             downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
  	             downloadLink.download = "AXIS SETTLEMENT Detail REPORT.xls";
  	             document.body.appendChild(downloadLink);
  	             downloadLink.click();
  	             document.body.removeChild(downloadLink);
  	         }</script>
						
						
  	              	 
<!--   	                              <div class="form-group"> -->
<!--   	                              <label class="col-md-3 control-label"></label> -->
<!-- 									<div class="col-md-1" id=""> -->
<!-- 									<button type="button" class='btn green' onclick='getScheduledTripStatusDataOnSubmit()' style="position: static;">Submit</button> -->
<!-- 									</div> -->
<!-- 									
								
								
					
							<!-- END FORM-->
<!-- 						</div> -->
							
					</div>
					
					
					
					
					
<!-- 				 <div id="ticketconsuptionid"></div> -->
				</div>
				

				<div id="ticketconsuptionid">

				
				</div>
				<div id="ticketconsuptionid1">

				
				</div>
					</div>
</div>
	<!-- <div style="display: none;" id="mymodal1" class="modal fade"
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
			<div class="portlet-body" id="tripwiserevenuecummulativedata1" style="display: none">
			
				<div style="text-align:center">
					<table class="table table-striped table-bordered table-hover"
						id="tripwiserevenuecummulative">
						<thead>
						<td><button type="submit" class="btn green" onClick="printDiv1()">print</button>
								</td>
							<tr>
						<th>Sl.No</th><th>Org Name</th><th>Waybill No</th><th>Schedule No</th><th>Route No</th><th>Trip No</th><th>Etm No</th>
						<th>Ticket No</th><th>Ticket Time</th><th>Its Ticket</th><th>Axis_Waybill</th><th>Axis_Ticket</th>
						<th>ITS_Transaction_Amt</th><th>ITS_Convinence</th><th>Axis_Transaction_Amt</th><th>Axis_Convinence</th>
						<th>Settlement_status</th><th>Settlement_Date</th>
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
			</div>
			</div> -->
			</div>
	
<!-- </div> -->

	
										

	

</body>
</html>