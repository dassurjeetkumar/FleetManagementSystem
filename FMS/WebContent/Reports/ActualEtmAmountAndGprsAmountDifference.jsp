  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
 
<script>

function getDepot(orgId){
	 $('#select2-chosen-2').html("Select");
	 $('#select2-chosen-3').html("Select");
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
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
	
function validateTripStatusReportFields()
{
	var div=$("#divisionlist").val();
	 
	   var depot=$("#depotlist1").val();
		var dd1=$("#startdate").val();
		var value=$("#compaire").val();
		
	
	 if(div==0)
	 {
		alert("Please Select Division");
		return false;
	 }
	 if(depot==0)
	 {
		alert("Please Select Depot");
		return false;
	 }
	 if(dd1==0)
	 {
		alert("Please Select Date");
		return false;
	 }
	 if(value==-1)
	 {
		alert("Please Select Condition");
		return false;
	 }
//		 if(orderno==0)
//		 {
//			alert("Please Select OrderNO");
//			return false;
//		 }
     

 return true;
}
	
	

function getDepot1(){
	
	   var div=$("#divisionlist").val();
	 
	   var depot=$("#depotlist1").val();
		var dd1=$("#startdate").val();
		var value=$("#compaire").val();
// 		var dd2=$("#endate").val();
var type=validateTripStatusReportFields();
 		var var1=$("#startdate").val();
		var1=var1.split("-");
		var1=var1[2]+"-"+var1[1]+"-"+var1[0];
//  		var var2=$("#endate").val();
// 		var2=var2.split("-");
// 		var2=var2[2]+"-"+var2[1]+"-"+var2[0];
		//alert(var1+""+var2);
		
		 //var d1 = Date.parse(var1);

		$('#pageLoader').show();
// 	var d3=Date.parse(var2);
		if (type==true){
        $.ajax({
        
            type: "post",
            url: '<%=request.getContextPath()%>/AjaxEtmAmountAndGprsAmountDiffReport.action?startdate='+dd1+'&depotlist1='+depot+'&divisionlist1='+div+'&value='+value,
            success: function(result) {
            	$('#pageLoader').hide();
                document.getElementById('ticketconsuptionid').innerHTML=result;
                fixHeader();
            }
        });
		}else
 			
 			
//  			alert("Please Select...");
 			$('#pageLoader').hide();
 			 document.getElementById('ticketconsuptionid').innerHTML="";
 		
	
}
</script>

<script>
		
function printDiv() {     
    
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

function rawPrint(){
	//alert("hdfdfdf");

	/* var htmlCode = "<applet archive='/doa/applet/IqPrint.jar' name='print' code='IqPrint' width=0 height=0><param name=httpUrl value='/doa/Ticketing/CashierReport.txt'><param name=printText value=''><param name=printDevice value='epson'><param name=printSubmitUrl value=''><param name=paginationRequired value='Y'><param name=directPrint value='Y'><param name=displayRequired value='N'></applet>";
	$("#resultset").html(htmlCode); */
        $.ajax({
          type: "post",
          url:"Ticketing/RawPrint.jsp",
          data:"SchedulewiseEarnings=new",
            success: function(result){
            	$("#dailyticketraw").html(result);
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
								<i class="fa fa-globe"></i>GPRS And ACC-66 Comparision Report
							</div>
						</div>
						
						<div class="portlet-body">
						

							<!-- 						start -->

<!-- <table> -->
                   <form action="" method="post" class="form-horizontal">
                           <div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Division<font
											color="red">*</font></label>
										<div class="col-md-4">
											<s:select list="divisionlist" id="divisionlist" 
											name="divisionlist1"  
												cssClass="select2_category form-control"  
												 onchange="getDepot(this.value)" headerKey="0" headerValue="--select--"></s:select>  
												 
										</div>
									</div>
								
                             
								<div class="form-group">
									<label class="col-md-3 control-label">Depot<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select list="depotlist" id="depotlist1" class="select2_category form-control" name="depotlist1"
 											> 
											<option value="0">--select--</option>
 										</select> 
									</div>
 								</div> 
 								
 								<div class="form-group">
									<label class="col-md-3 control-label">Condition<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select  id="compaire" class="select2_category form-control" name="compaire"
 											> 
											<option value="-1">Select</option>
											<option value="0">ETM Amount Equals To GPRS Amount</option>
											<option value="1">ETM Amount More Than GPRS Amount</option>
											<option value="2">ETM Amount Less Than GPRS Amount</option>
 										</select> 
									</div>
 								</div> 
 								
                          
							 <div class="form-group">
							  <label class="control-label col-md-3">Date:</label>
								<div class="col-md-3">
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
                                        curr_date=curr_date.split("-");
                                        curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
                                        var dtval=document.getElementById('startdate').value;	
                                        
                                        if(dtval==''){
                                        $('#startdate').val(curr_date);
                                        }
								</script>
								</div>
								</div></div>
						  
                     
							
						 <script type="text/javascript"> 
//  	                     function tabletoExcel(btnExport){
 	                    
//  	                    	 var divElements = document.getElementById("header").innerHTML;
//  	                      var  divElements = document.getElementById("ticketconsuptionid").innerHTML;
 	                        
//  	                        var noOfTableExist = 0;
//  	                       var htmltable = document.getElementById("ticketconsuptionid");
//  	       	             var html = inputHTML + htmltable.outerHTML;
//  	                   var downloadLink = document.createElement("a"); 
//  	                  downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html); 
//  	                  downloadLink.download = "GPRS And ACC-66 Comparision Report.xls"; 
//  	                  document.body.appendChild(downloadLink); 
//  	                  downloadLink.click(); 
//  	                  document.body.removeChild(downloadLink);
//  	                  }
 	                  
//  	                    function tabletoExcel(btnExport){   
//  	                       var htmltable = document.getElementById("ticketconsuptionid");
//  	                       var html = htmltable.outerHTML;
//  	                       var noOfTableExist = 0;
//  	                       try{
//  	                    		$("#ticketconsuptionid table").each(function(){
//  	                    			noOfTableExist++;
//  	                    		});
 	                    		
//  	                    		/* If two table exist  then remove the last table on print click*/
//  	                    		if(noOfTableExist >= 2){
//  	                    			html = html.substring(0, html.indexOf("</table>") + 8) + "</div></div>";
//  	                    		}
//  	                    	}catch(err){
//  	                    	    console.log("ExceptionCaught : " + err);
//  	                    	}
 	                       
//  	                       var downloadLink = document.createElement("a");
//  	                       downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
//  	                       downloadLink.download = "GPRS And ACC-66 Comparision Report.xls";
//  	                       document.body.appendChild(downloadLink);
//  	                       downloadLink.click();
//  	                       document.body.removeChild(downloadLink);
//  	                     }
 	                    
 	              	 function tabletoExcel(btnExport){     
 	      	           	var htmltable= document.getElementById('btnExport');   
 	      	             $( "#header-fixed" ).remove();
 	      	             var inputHTML = "<table border='1'>";
 	      	             inputHTML += "<tr>";
 	      	             inputHTML += "<th  align='left'colspan='9'>Bangalore Metropolitan Transport Corporation</th>";
 	      	             inputHTML += "</tr>";
 	      	             inputHTML += "<th  align='left'colspan='9'>GPRS AND ACC-66 COMPARISION REPORT</th>";
 	      	             inputHTML += "</tr>";
// 	       	             inputHTML += "<tr>";
// 	       	             inputHTML += "<th colspan='2' align='left'>Date Range:</th>";
// 	       	             inputHTML += "<th colspan='7' align='left'>" + $("#startdate").val() + " to " + $("#endate").val() + "</th>";
// 	       	             inputHTML += "</tr>";
 	      	             inputHTML += "</table>";
 	      	             var htmltable = document.getElementById("ticketconsuptionid");
 	      	             var html = inputHTML + htmltable.outerHTML;
 	      	             var downloadLink = document.createElement("a");
 	      	             downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
 	      	             downloadLink.download = "GPRS And ACC-66 Comparision Report.xls";
 	      	             document.body.appendChild(downloadLink);
 	      	             downloadLink.click();
 	      	             document.body.removeChild(downloadLink);
 	      	         }
 	                    
 	                     
 	                     
 	                     </script>	
					
							
					 <div class="form-group">
					 <label class="control-label col-md-3"></label>
                     <div class="col-md-3" id="">
                     </div>
                     </div>
                     </div>
                     </form>
					
					 <div align='center'>
					<button type="submit" class="btn green" onClick="getDepot1()">Submit</button> 
					<button type="button" id="btnExport" class="btn green" onclick="tabletoExcel();"> EXPORT </button>
                   <span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span>&nbsp;<span>
<%-- 					 <input type='button' class='btn green' id='button1' name='rawprint' onclick='rawPrint()' value='RawPrint' /></span></div> --%>
					<!-- END EXAMPLE TABLE PORTLET-->
					<div id="ticketconsuptionid"></div>
				</div>
			</div>
			
			<!-- END PAGE CONTENT-->
		</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	</head>
	</html>

	
	
	
