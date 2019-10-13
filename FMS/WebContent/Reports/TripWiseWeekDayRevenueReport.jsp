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
// 	 $('#select2-chosen-2').html("Select");
// 	 $('#select2-chosen-3').html("Select");
 $('#select2-chosen-4').html("Select");
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 var val=document.getElementById('divisionlist').value;
			 if(val!=0) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/getDepotForMIS?val=' + val,
				success : function(result) {
					document.getElementById('depotlist1').innerHTML = result;
					
				}
			});
		}

	}

function getSchedule(){
	// $('#select2-chosen-2').html("Select");
	// $('#select2-chosen-3').html("Select");
	 $('#select2-chosen-4').html("Select");
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 var val=document.getElementById('depotlist1').value;
		 var shiftid=document.getElementById('shiftid').value;
		 //alert(val);
			 if(val!=0) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/getScheduleFromDepotforTripWise?val=' + val+'&shiftid=' + shiftid,
				success : function(result) {
					document.getElementById('scheduleid').innerHTML = result;
					
				}
			});
		}

	}
function getScheduleType(){
	// $('#select2-chosen-2').html("Select");
	// $('#select2-chosen-3').html("Select");
	 $('#select2-chosen-4').html("Select");
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 var val=document.getElementById('depotlist1').value;
			 if(val!=0) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/getScheduleTypeFromDepotforTripWise?depot=' + val,
				success : function(result) {
					document.getElementById('scheduletypeid').innerHTML = result;
					
				}
			});
		}

	}
	
function getShift(){
	// $('#select2-chosen-2').html("Select");
// 	 $('#select2-chosen-3').html("Select");
// 	 $('#select2-chosen-4').html("Select");
 $('#select2-chosen-4').html("Select");
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		  var depot=document.getElementById('depotlist1').value;
		  var scheduletypeid=document.getElementById('scheduletypeid').value;
		// var scheduleid=document.getElementById('scheduleid').value;
			
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/getShifttypeFromDepot?depot='+depot+'&scheduletypeid=' + scheduletypeid,
				success : function(result) {
					document.getElementById('shiftid').innerHTML = result;
					
				}
			});
		

	}

function getDepot1(){
	
	   var div=$("#divisionlist").val();
	 
	   var depot=$("#depotlist1").val();
		var dd1=$("#startdate").val();
		var dd2=$("#enddate").val();
 		var var1=$("#startdate").val();
 		var scheduleid=$("#scheduleid").val();
 		var shiftid=$("#shiftid").val();
 		var scheduletypeid=$("#scheduletypeid").val();
 		var days=$("#days").val();
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
            url: '<%=request.getContextPath()%>/AjaxTripWiseWeekDayRevenue.action?startdate='+dd1+'&enddate='+dd2+'&depotlist1='+depot+'&scheduleid='+scheduleid+'&days='+days+'&shiftid='+shiftid+'&divisionlist1='+div+'&scheduletypeid='+scheduletypeid,
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
						<p>Trip Wise Weekday Revenue Report
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
								<i class="fa fa-globe"></i>Trip Wise Weekday Revenue Report
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
										<select list="depotlist" id="depotlist1" class="select2_category form-control" name="depotlist1"   onchange="getScheduleType(this.value)"> 
											<option value="0">--select--</option>
 										</select> 
									</div>
 								</div> 
 								
 								  <div class="form-group" id="status">
									<label class="col-md-3 control-label">Day<font
										color="red"></font></label>
									<div class="col-md-4">
						                <select  id="days"  class="select4_category form-control" name="days" onchange="getShift('')" >
						                <option value="0">All</option>
							              <option value="sunday">Sunday</option>
							              <option value="monday">Monday</option>
							              <option value="tuesday">Tuesday</option>
							              <option value="wednesday">Wednesday</option>
							              <option value="thursday">Thursday</option>
							               <option value="friday">Friday</option>
							                <option value="saturday">Saturday</option>
							           </select>
							           <script>
											var orgTypeId ="<s:property value='days'/>";
											if(orgTypeId!=""){
 												document.getElementById(orgTypeId).selected= true; 
												
											}
											
											
										</script>
					</div>
											</div>
 								
 								  <div class="form-group">
									<label class="col-md-3 control-label">Schedule Type<font
										color="red"></font></label>
									<div class="col-md-4">
										<select id="scheduletypeid" name="scheduletypeid" 
											class="select4_category form-control"
											onchange="getShift(this.value)">
											<option value="0">All</option>
										</select>
                                     </div>
                                     </div>
 								   <div class="form-group">
									<label class="col-md-3 control-label">Shift Type<font
										color="red"></font></label>
									<div class="col-md-4">
										<select id="shiftid" name="shiftid" 
											class="select4_category form-control"
											onchange="getSchedule(this.value)">
											<option value="0">All</option>
										</select>
                                     </div>
                                     </div>
 								
 								<div class="form-group">
									<label class="col-md-3 control-label">Schedule Number<font
										color="red"></font></label>
									<div class="col-md-4">
										<select id="scheduleid" name="scheduleno" 
											class="select2_category form-control" >
											<option value="0">All</option>
										</select>
                                     </div>
                                     </div>
                                  
 								
 								
 								
                          
							 <div class="form-group">
							  <label class="control-label col-md-3">From Date:</label>
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
// 										var curr_date=new Date().toJSON().slice(0,10);
//                                         curr_date=curr_date.split("-");
//                                         curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
//                                         var dtval=document.getElementById('startdate').value;	
                                        
//                                         if(dtval==''){
//                                         $('#startdate').val(curr_date);
//                                         }
								</script>
								</div>
								</div></div>
						  
							 <div class="form-group">
							  <label class="control-label col-md-3">Till Date:</label>
								<div class="col-md-3">
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
								<script>
// 										var curr_date=new Date().toJSON().slice(0,10);
//                                         curr_date=curr_date.split("-");
//                                         curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
//                                         var dtval=document.getElementById('enddate').value;	
                                        
//                                         if(dtval==''){
//                                         $('#enddate').val(curr_date);
//                                         }
								</script>
								</div>
								</div></div>
							
								
						
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
 	                  downloadLink.download = "Trip Wise Weekday Revenue Report.xls"; 
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

	
	
	