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
	// $('#select2-chosen-2').html("Select");
	// $('#select2-chosen-3').html("Select");
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
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

function getServicetype(orgId){
	// $('#select2-chosen-2').html("Select");
	 $('#select2-chosen-3').html("Select");
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 var val=document.getElementById('depotlist1').value;
			 if(val!=0) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/getServicetypeFortargetAverage?val=' + val,
				success : function(result) {
					document.getElementById('servicetype1').innerHTML = result;
					
				}
			});
		}

	}

function getDepot1(){
	
	   var div=$("#divisionlist").val();
	 
	   var depot=$("#depotlist1").val();
	   var dutytype=$("#dutytype").val();
	   var daytype=$("#daytype").val();
		var starttime=$("#start_time").val();
		var endtime=$("#end_time").val();
 		var var1=$("#start_time").val();
 	
		var1=var1.split(":");//var change
		var1=var1[0]+var1[1];
 		var var2=$("#end_time").val();
 		var2=var2.split(":");
 		var2=var2[0]+var2[1];
		//alert(var1+""+var2);
		
		 //var d1 = Date.parse(var1);

		 
 	//var d3=Date.parse(var2);
 		if (Number(var1)<Number(var2)){
 			 $('#pageLoader').show();
        $.ajax({
        
            type: "post",
            url: '<%=request.getContextPath()%>/AjaxFormFourDetailsReport.action?starttime='+starttime+'&endtime='+endtime+'&daytype='+daytype+'&depot='+depot+'&dutytype='+dutytype+'&division='+div,
            success: function(result) {
            	$('#pageLoader').hide();
                document.getElementById('ticketconsuptionid').innerHTML=result;
                fixHeader();
            }
        });
		}else{
 			
 			
 			alert("Start Time End  Should Be greater Than Start Time From");
 			$('#pageLoader').hide();
 			 document.getElementById('ticketconsuptionid').innerHTML="";
		}
	
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
			
			
			
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>Form-IV-Details Report
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
										<select list="depotlist" id="depotlist1" class="select2_category form-control" name="depotlist1" > 
											<option value="0">--select--</option>
											<!-- <option value="1">All</option> -->
 										</select> 
									</div>
 								</div> 
 								
 								<div class="form-group">
										<label class="col-md-3 control-label">Duty Type<font
											color="red">*</font></label>
										<div class="col-md-4">
											<s:select list="dutytypemap" id="dutytype" 
											name="dutytype"  
												cssClass="select2_category form-control"  
												 headerKey="0" headerValue="--select--"></s:select>  
												 
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Day<font
											color="red">*</font></label>
										<div class="col-md-4">
											<s:select list="daymap" id="daytype" 
											name="daytype"  
												cssClass="select2_category form-control"  
												 headerKey="0" headerValue="--select--"></s:select>  
												 
										</div>
									</div>
                          
							  <div class="form-group">
							<label class="col-md-3 control-label">Start Time From</label>
									<div class="col-md-4">
					
						<div class="col-md-3">
									<div class="input-group">
												<input type="text" class="form-control input-small timepicker timepicker-24" name="formFour.start_time" id="start_time" 
												value="<s:property value="formFour.start_time"/>">
												 <s:if test="fieldErrors.get('start_time').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('start_time').get(0)" /></span>
									</s:if> 
											</div>

								</div>
					</div>
					</div>
					
							  <div class="form-group">
							<label class="col-md-3 control-label">Start Time End</label>
									<div class="col-md-4">
					
						<div class="col-md-3">
									<div class="input-group">
												<input type="text" class="form-control input-small timepicker timepicker-24" name="formFour.end_time" id="end_time" 
												value="<s:property value="formFour.end_time"/>">
												 <s:if test="fieldErrors.get('end_time').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('pass_end_time').get(0)" /></span>
									</s:if> 
											</div>

								</div>
					</div>
					</div>
						  
							
							
								
						
						<!-- end -->
							
							
						 <script type="text/javascript"> 
 	                     function tabletoExcel(btnExport){
 	                    
 	                    	 var divElements = document.getElementById("header").innerHTML;
 	                      var  divElements = document.getElementById("ticketconsuptionid").innerHTML;
 	                        
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
 	                  downloadLink.download = "Form-IV-Details Report.xls"; 
 	                  document.body.appendChild(downloadLink); 
 	                  downloadLink.click(); 
 	                  document.body.removeChild(downloadLink);
 	                  }</script>	
					
							
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
				</div>
					<div id="ticketconsuptionid"></div>
					
<!-- 					 <div align='center'> -->
<!-- 					<button type="submit" class="btn green" onClick="getDepot1()">Submit</button>  -->
<!-- 					<button type="button" id="btnExport" class="btn green" onclick="tabletoExcel();"> EXPORT </button> -->
<%--                    <span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span>&nbsp;<span> --%>
<%-- <%-- 					 <input type='button' class='btn green' id='button1' name='rawprint' onclick='rawPrint()' value='RawPrint' /></span></div> --%>
<!-- 					END EXAMPLE TABLE PORTLET -->
<!-- 				</div> -->
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

	
	
	
