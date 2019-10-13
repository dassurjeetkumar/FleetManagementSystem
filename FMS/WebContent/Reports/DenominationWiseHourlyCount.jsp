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
		 var val=document.getElementById('divisionlist1').value;
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
	
	   var div=$("#divisionlist1").val();
	 
	   var depot=$("#depotlist1").val();
	   var servicetype1=$("#servicetype").val();
	   var tickettype1=$("#tickettype").val();
	   var daytype1=$("#daytype").val();
		var startdate=$("#startdate").val();
		var enddate=$("#enddate").val();
		
 		
		var var1=$("#startdate").val();
		var1=var1.split("-");
		var1=var1[2]+"-"+var1[1]+"-"+var1[0];
		var var2=$("#enddate").val();
		var2=var2.split("-");
		var2=var2[2]+"-"+var2[1]+"-"+var2[0];
		//alert(var1+""+var2);
		
		 var d1 = Date.parse(var1);

		
	var d3=Date.parse(var2);
		if (d1 <= d3){
 			 $('#pageLoader').show();
        $.ajax({
        
            type: "post",
            url: '<%=request.getContextPath()%>/AjaxDenominationWiseHourlyCountReport.action?startdate='+startdate+'&enddate='+enddate+'&servicetype='+servicetype1+'&depot='+depot+'&tickettype='+tickettype1+'&daytype='+daytype1,
            success: function(result) {
            	$('#pageLoader').hide();
                document.getElementById('ticketconsuptionid').innerHTML=result;
                fixHeader();
            }
        });
		}else{
 			
 			
 			alert("Start date Should Be greater Than Enddate");
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
								<i class="fa fa-globe"></i>Denomination Wise Hourly Report
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
											<s:select list="divisionlist" id="divisionlist1" 
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
 										</select> 
									</div>
 								</div> 
 								
 								<div class="form-group">
										<label class="col-md-3 control-label">Service Type<font
											color="red">*</font></label>
										<div class="col-md-4">
											<s:select list="servicetypemap" id="servicetype" 
											name="servicetype"  
												cssClass="select2_category form-control"  
												 headerKey="0" headerValue="--select--"></s:select>  
												 
										</div>
									</div>
<!-- 									<div class="form-group"> -->
<!-- 										<label class="col-md-3 control-label">Ticket Type<font -->
<!-- 											color="red">*</font></label> -->
<!-- 										<div class="col-md-4"> -->
<%-- 											<s:select list="{'AD':'Adult','CH':'Child', 'SC':'Senior City'}" id="tickettype"  --%>
<%-- 											name="tickettype"   --%>
<%-- 												cssClass="select2_category form-control"   --%>
<%-- 												 headerKey="0" headerValue="--select--"></s:select>   --%>
												 
<!-- 										</div> -->
<!-- 									</div> -->
									
										<div class="form-group">
									<label class="col-md-3 control-label">Ticket Type<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select  id="tickettype" class="select2_category form-control" name="tickettype"
 											> 
											<option value="-1">Select</option>
											<option value="AD">Adult</option>
											<option value="CH">Child</option>
											<option value="SC">Senior Citizen</option>
 										</select> 
									</div>
 								</div> 
									
									
									<div class="form-group">
										<label class="col-md-3 control-label">Days<font
											color="red">*</font></label>
										<div class="col-md-4">
<%-- 											<s:select   list="{'1':'Week Days','2':'Saturday', '3':'Sunday'}" id="daytype"  --%>
<%-- 											name="daytype"   --%>
<%-- 												cssClass="select2_category form-control"   --%>
<%-- 												 headerKey="0" headerValue="--select--"> --%>
												
<%-- 												 </s:select>   --%>
												 
												 <select  id="daytype" class="select2_category form-control" name="daytype"
 											> 
											<option value="-1">Select</option>
											<option value="1">Week Days</option>
											<option value="2">Saturday</option>
											<option value="3">Sunday</option>
 										</select> 
												 
										</div>
									</div>
                          
							  <div class="form-group">
							<label class="col-md-3 control-label">Effective Start Date<font
										color="red">*</font></label>
									<div class="col-md-4">
					
						<div class="col-md-3">
									<div class="input-group input-medium date date-picker"
										data-date-format="dd-mm-yyyy" data-date-viewmode="years">
										<input class="form-control" type="text" size="10" name=""
											id="startdate"  readonly>
										<span class="input-group-btn">
											<button class="btn default date-set form-control"
												type="button">
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
								</div>
					</div>
					</div>
					
							  <div class="form-group">
							<label class="col-md-3 control-label">Effective End Date<font
										color="red">*</font></label>
									<div class="col-md-4">
					
						<div class="col-md-3">
									<div class="input-group input-medium date date-picker"
										data-date-format="dd-mm-yyyy" data-date-viewmode="years">
										<input class="form-control" type="text" size="10" name=""
											id="enddate"  readonly>
										<span class="input-group-btn">
											<button class="btn default date-set form-control"
												type="button">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
											<script>
								var curr_date=new Date().toJSON().slice(0,10);
                                curr_date=curr_date.split("-"); 
                                 curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0]; 
                                 var dtval=document.getElementById('enddate').value;	 
                                
                                 if(dtval==''){
                              $('#enddate').val(curr_date); 
                            
                                } 
								</script>
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

	
	
	