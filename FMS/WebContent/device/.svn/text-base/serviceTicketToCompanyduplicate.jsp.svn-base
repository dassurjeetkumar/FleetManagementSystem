  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
</head>
 <body>
<script>

function getDepot(){
	     $.ajax({
        
            type: "post",
            url: '<%=request.getContextPath()%>/AjaxserviceTicketToCompanyduplicateAction.action',
            success: function(result) {
            	$('#pageLoader').hide();
                document.getElementById('ticketconsuptionid').innerHTML=result;
                fixHeader();
            }
        });
  		
	
}
function callCancell(){
	
	window.location.href='getEtmDeviceView.action';
}
// window.onload=getDepot();
</script>

<script>
		
function printDiv() {     
    
    //  document.getElementById("print").style.visibility='hidden';     
    var divElements = document.getElementById("header").innerHTML;
    divElements += document.getElementById("ticketconsuptionid").innerHTML;
    divElements += document.getElementById("footer").innerHTML;
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

function rawPrint(){
	//alert("hdfdfdf");

	/* var htmlCode = "<applet archive='/doa/applet/IqPrint.jar' name='print' code='IqPrint' width=0 height=0><param name=httpUrl value='/doa/Ticketing/CashierReport.txt'><param name=printText value=''><param name=printDevice value='epson'><param name=printSubmitUrl value=''><param name=paginationRequired value='Y'><param name=directPrint value='Y'><param name=displayRequired value='N'></applet>";
	$("#resultset").html(htmlCode); */
        $.ajax({
          type: "get",
          url:"device/RawPrint.jsp",
          data:"ServiceTicketToCompany=new",
            success: function(result){
            	$("#serviceTicketToCompany").html(result);
            	//alert(result);
              
            }
        });
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
								<i class="fa fa-globe"></i>Service Ticket To Company
							</div>
						</div>
						
						<div class="portlet-body">
					<div class="form-group">
									<script>getDepot();</script>
								</div><div id="ticketconsuptionid"></div>
						</div>
					</div>
					
					<div align='center'><span><button type="button" class="btn green" id="btnExport" filename= "report" onclick="tabletoExcel();"> EXPORT </button></span>&nbsp; 
                     <span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span>&nbsp; 
                     <span><input type='button' class='btn blue' id='rawprint' onclick='rawPrint()' value='RawPrint' /></span>&nbsp; 
                     <span><input type='button' class='btn green' id='button1' onclick='callCancell()' value='Cancel' /></span></div>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
				
				<script> 
				
   	        	function tabletoExcel(btnExport){      
   	           	var htmltable= document.getElementById('btnExport');    
   	           
   	        // var divElements = document.getElementById("header").innerHTML;
   	      var  divElements = document.getElementById("ticketconsuptionid").innerHTML;
   	      // divElements += document.getElementById("footer").innerHTML;
   	      
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
   	            downloadLink.download = "Service Ticket To Company";
   	            document.body.appendChild(downloadLink); 
   	            downloadLink.click(); 
   	            document.body.removeChild(downloadLink);
  	         }
   	           	 </script>
				
			</div>
			
			
			<!-- END PAGE CONTENT-->
		</div>
		<div id="serviceTicketToCompany">
			</div>
	</div>
	
</body>	
	
	
