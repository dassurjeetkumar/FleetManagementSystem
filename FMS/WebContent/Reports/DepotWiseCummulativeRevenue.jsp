  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
<script>

function getDepot(){
	
	
		var dd1=$("#startdate").val();
		var dd2=$("#endate").val();
 		
		var var1=$("#startdate").val();
		var1=var1.split("-");
		var1=var1[2]+"-"+var1[1]+"-"+var1[0];
		var var2=$("#endate").val();
		var2=var2.split("-");
		var2=var2[2]+"-"+var2[1]+"-"+var2[0];
		//alert(var1+""+var2);
		
		 var d1 = Date.parse(var1);

		 $('#pageLoader').show();
	var d3=Date.parse(var2);
		if (d1 <= d3){
        $.ajax({
        	
            type: "get",
            url: '<%=request.getContextPath()%>/getDepotWiseCummulativeRevenue.action?startdate='+dd1+'&enddate='+dd2,
            success: function(result) {
            	$('#pageLoader').hide();
                document.getElementById('ticketconsuptionid').innerHTML=result;
                fixHeader();
            }
        });
		}else{
 			
 			
 			alert("Till Date Should Be greater Than Start Date");
 			$('#pageLoader').hide();
 			 document.getElementById('ticketconsuptionid').innerHTML="";
 		}
	
}
</script>

<script>
		
function printDiv() {     
    
  //    document.getElementById("print").style.visibility='hidden';     
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
			//alert("divElements"+divElements);
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
			
			
			
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>DepotWise Cummulative Revenue Report 
							</div>
						</div>
						
						<div class="portlet-body">
					<div class="form-body">
						<div class="form-group">
									<label class="control-label col-md-3">Start Date:</label>
										<div class="col-md-3">
											<div class="input-group input-medium date date-picker" style="width: auto" data-date-format="dd-mm-yyyy" data-date-viewmode="years">
												<input id="startdate" class="form-control" type="text" readonly="" size="16" name="rateMaster.effectiveStartDate" value="<s:property value='rateMaster.effectiveStartDate' />">
												<span class="input-group-btn">
													<button class="btn default date-set" type="button">
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
									<label class="control-label col-md-3">Till Date:</label>
										<div class="col-md-3">
											<div class="input-group input-medium date date-picker"	style="width: auto" data-date-format="dd-mm-yyyy" data-date-viewmode="years">
												<input id="endate" class="form-control" type="text"	readonly="" size="16" name="rateMaster.effectiveStartDate"	value="<s:property value='rateMaster.effectiveStartDate' />">
												<span class="input-group-btn">
													<button class="btn default date-set" type="button">
														<i class="fa fa-calendar"></i>
													</button>
												</span>
												<script>
													var curr_date = new Date().toJSON().slice(0,10);
													curr_date = curr_date.split("-");
													curr_date = curr_date[2]+ "-"+ curr_date[1]+ "-"+ curr_date[0];
													var dtval = document.getElementById('endate').value;
													if (dtval == '') {
														$('#endate').val(curr_date);
														
													}
													
												</script>
												
											</div>
										</div>
										</div>
														
						<div class="form-group">
									
								<div class="form-group">
										<button type="submit" class="btn green" onClick="getDepot()">Submit</button>
									<span><button type="button" class="btn green" id="btnExport" filename= "report" onclick="tabletoExcel();"> EXPORT </button></span>&nbsp;<span> 
                             </div>
							</div>
						</div>
					
					<div id="ticketconsuptionid"></div>
					 <div align='center'><span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span>
					</div>
					
					<!-- END EXAMPLE TABLE PORTLET--></div>
				</div>
				
				<script>                 	
				               	
   	        	function tabletoExcel(btnExport){ 
   	        		
   	        	    var htmltable= document.getElementById('btnExport'); 
   	        	  var divElements = document.getElementById("header").innerHTML;
   	         var divElements = document.getElementById("ticketconsuptionid").innerHTML;
   	           
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
   	        	  
   	          
                var downloadLink = document.createElement("a"); 
   	            downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(divElements); 
   	            downloadLink.download = "DepotWise Cummulative Revenue Report .xls";
   	            document.body.appendChild(downloadLink); 
   	            downloadLink.click(); 
   	            document.body.removeChild(downloadLink);
   	        	}
   	           	 </script>
				
				
			</div>
			<div id="bagWiseRevenueraw">
			</div>
			
			<!-- END PAGE CONTENT-->
		</div>
	</div>
	
	
	
	
