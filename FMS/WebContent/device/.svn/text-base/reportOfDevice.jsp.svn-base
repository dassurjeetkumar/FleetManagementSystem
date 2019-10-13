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
			$.ajax({
 	        
            type: "post",
            url: '<%=request.getContextPath()%>/AjaxreportOfDevice.action?startdate='+dd1,
            success: function(result) {
            	$('#pageLoader').hide();
                document.getElementById('devicehistoryid').innerHTML=result;
                
            }
        });
		
}
</script>


<script>
		
function printDiv() {     
    
    //  document.getElementById("print").style.visibility='hidden';     
    var divElements = document.getElementById("header").innerHTML;
    divElements += document.getElementById("devicehistoryid").innerHTML;

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
								<i class="fa fa-globe"></i>ETM Device History Report
							</div>
						</div>
						
						<div class="portlet-body">
						<div class="table-scrollable">
							
							<table align="center">
							  	  <tr>
							  <td><label class="control-label col-md-4">Date:</label>
								<div class="col-md-4">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" ><!-- data-date-start-date="+0d"> -->
								<input id="startdate" class="form-control" type="text" readonly="" size="16" name="rateMaster.effectiveStartDate"
								value="<s:property value='rateMaster.effectiveStartDate' />"
								>
								<span class="input-group-btn">
								<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
								</button>
								</span>
								<script>
										var curr_date=new Date().toJSON().slice(0,10);
										curr_date = curr_date.split("-");
										curr_date = curr_date[2] + "-" + curr_date[1] + "-" + curr_date[0];
                                        var dtval=document.getElementById('startdate').value;	
                                        
                                        if(dtval==''){
                                        $('#startdate').val(curr_date);
                                        
                                        }
                                        getDepot();
								</script>
								</div>
								</div></td>   
							  
								<td><button type="submit" class="btn green" onClick="getDepot()">Submit</button>
								
								</td>  
							  </tr>
							  
							</table>
							
							</div>
							<div id="devicehistoryid"></div>
					 <div align='center'><span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span>&nbsp;
					 <span><button type="button" class="btn green" id="btnExport" filename= "report" onclick="tabletoExcel();"> EXPORT </button></span>&nbsp;
                </div>
							<script>                 	
   	        	function tabletoExcel(btnExport){      
   	           	var htmltable= document.getElementById('btnExport');    
   	            $( "#header-fixed" ).remove();
   	            var inputHTML = "<table border='1'>";
   	            inputHTML += "<tr>";
   	            inputHTML += "<th  align='left'colspan='11'>Bangalore Metropolitan Transport Corporation</th>";
   	            inputHTML += "</tr>";
   	            inputHTML += "<th  align='left'colspan='11'>ETM Device History Report</th>";
   	            inputHTML += "</tr>";
   	            inputHTML += "<tr>";
   	            inputHTML += "<th colspan='6' align='left'>Date Range:</th>";
   	            inputHTML += "<th colspan='5' align='left'>" + $("#startdate").val()  + "</th>";
   	            inputHTML += "</tr>";
   	            inputHTML += "</table>";
   	      
                var htmltable = document.getElementById("printid"); 
   	            var html = inputHTML +  htmltable.outerHTML; 
   	            var downloadLink = document.createElement("a"); 
   	            downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html); 
   	            downloadLink.download = "ETM Device History Report.xls";
   	            document.body.appendChild(downloadLink); 
   	            downloadLink.click(); 
   	            document.body.removeChild(downloadLink);
  	         }
   	           	 </script>
						</div>
					</div>
					
					
				</div>
			</div>
			
			</div>
			
		
		</div>
	</div>
	
	
	
	