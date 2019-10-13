  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
 
<script>
	$(function() {
$(document).ready(function () {
	$("#nightout").hide();
	$("#dayout").hide();
});
});
            
function ShowType(schedule_id){
	
	//alert("In js Function"+dat);
	 var scheduletype=$('#scheduletypeid').val();
	 
	 if(scheduletype == "2"){
 	    document.getElementById("nightout").style.display='block';

	 }else if(scheduletype == "3"){
		 document.getElementById("dayout").style.display='block';
	 }else{
		 $("#nightout").hide();
			$("#dayout").hide();
	 }
}

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
	
function getScheduleType(depotId){
	 $('#select2-chosen-2').html("Select");
	 $('#select2-chosen-3').html("Select");
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 var val=document.getElementById('depotlist1').value;
			 if(val!=0) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/getScheduleTypes?val=' + val,
				success : function(result) {
					document.getElementById('scheduletypeid').innerHTML = result;
					
				}
			});
		}

	}
	

function validate()
{
	 
//    alert("hii");
	 var divisionlist=$('#divisionlist1').val();
	 var depotlist=$('#depotlist1').val();
// 	 var scheduleno=$('#scheduleid').val();
   var scheduleid=$("#scheduletypeid").val();
		var nightshift=$("#shifttypeid").val();
		var dayshift=$("#shifttypeid1").val();
	 var startdate=$('#startdate').val();
	 var enddate=$('#enddate').val();
	 
	 var curr_date=new Date().toJSON().slice(0,10);
     curr_date=curr_date.split("-");
     curr_date=curr_date[0]+"-"+curr_date[1]+"-"+curr_date[2];
//      alert(curr_date);
     var cur_date=Date.parse(curr_date);

     var var1=$("#startdate").val();
		var1=var1.split("-");
		var1=var1[2]+"-"+var1[1]+"-"+var1[0];
		var var2=$("#enddate").val();
		var2=var2.split("-");
		var2=var2[2]+"-"+var2[1]+"-"+var2[0];
		//alert(var1+""+var2);
// 		alert(var1+"  "+var2);
		 var start_date = Date.parse(var1);
		 var end_date= Date.parse(var2);

     
   if( divisionlist == "0" )
   {
//       alert( "Please select vehicle" );
       bootbox.alert("Please select division");
      return false;
   }
   if( depotlist == "0" )
   {
      //alert( "Please select vehicle" );
       bootbox.alert("Please select depot");
      return false;
   }
   if( scheduleid == "0" )
   {
      //alert( "Please select vehicle" );
       bootbox.alert("Please select Schedule Type");
      return false;
   }else if(scheduleid == "2"){
	   if(nightshift == "0"){
		 //alert( "Please select vehicle" );
	       bootbox.alert("Please select Shift Type");
	      return false;
	   }
   }else if(scheduleid == "3"){
	   if(dayshift == "0"){
			 //alert( "Please select vehicle" );
		       bootbox.alert("Please select Shift Type");
		      return false;
		   }
   }
 
   
   if( start_date > cur_date-2 )
   {
      //alert( "Please select vehicle" );
		  bootbox.alert("Please Provide Start Date two days before from Current Date");
      return false;
   }
   if( end_date > cur_date-2 )
   {
      //alert( "Please select vehicle" );
       bootbox.alert("Please Provide Start Date two days before from Current Date");
      return false;
   }

 

   return( true );
   
}
	
	
	

function getData(){
	
	var valid=validate();
		var dd1=$("#startdate").val();
		var dd2=$("#enddate").val();
		var division=$("#divisionlist1").val();
		var depot=$("#depotlist1").val();
		var scheduleid=$("#scheduletypeid").val();
		var nightshift=$("#shifttypeid").val();
		var dayshift=$("#shifttypeid1").val();


//  		alert(depot+scheduleno);
		var var1=$("#startdate").val();
		var1=var1.split("-");
		var1=var1[2]+"-"+var1[1]+"-"+var1[0];
		var var2=$("#enddate").val();
		var2=var2.split("-");
		var2=var2[2]+"-"+var2[1]+"-"+var2[0];
		//alert(var1+""+var2);
		
		 var d1 = Date.parse(var1);

// alert(valid);
		if(valid==true){
// 			alert("hello");
	var d3=Date.parse(var2);
		if (d1 <= d3){
			
			if(scheduleid == "2"){
				
				 $.ajax({
				        
			            type: "get",
			            url: '<%=request.getContextPath()%>/AjaxShiftWiseEPKMReport.action?startdate='+dd1+'&enddate='+dd2+'&scheduleid='+scheduleid+'&division='+division+'&depot='+depot+'&nightshift='+nightshift,
			            success: function(result) {
			            	$('#pageLoader').hide();
			                document.getElementById('ticketconsuptionid').innerHTML=result;
			                fixHeader();
			            }
			        });
				
			}else if(scheduleid == "3"){
				$.ajax({
			        
		            type: "get",
		            url: '<%=request.getContextPath()%>/AjaxShiftWiseEPKMReport.action?startdate='+dd1+'&enddate='+dd2+'&scheduleid='+scheduleid+'&division='+division+'&depot='+depot+'&dayshift='+dayshift,
		            success: function(result) {
		            	$('#pageLoader').hide();
		                document.getElementById('ticketconsuptionid').innerHTML=result;
		                fixHeader();
		            }
		        });
				
			}else{
				
				$.ajax({
			        
		            type: "get",
		            url: '<%=request.getContextPath()%>/AjaxShiftWiseEPKMReport.action?startdate='+dd1+'&enddate='+dd2+'&scheduleid='+scheduleid+'&division='+division+'&depot='+depot,
		            success: function(result) {
		            	$('#pageLoader').hide();
		                document.getElementById('ticketconsuptionid').innerHTML=result;
		                fixHeader();
		            }
		        });
				
			}
		
		}else{
 			
 			
 			alert("Till Date Should Be greater Than Start Date");
 			$('#pageLoader').hide();
 			 document.getElementById('ticketconsuptionid').innerHTML="";
 		}
		}
}
</script>

<script>
		
function printhiv() {     
    
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

	/* var htmlCode = "<applet archive='/doa/applet/IqPrint.jar' name='print' code='IqPrint' width=0 height=0><param name=httpUrl value='/doa/Ticketing/CashierReport.txt'><param name=printText value=''><param name=printhevice value='epson'><param name=printSubmitUrl value=''><param name=paginationRequired value='Y'><param name=directPrint value='Y'><param name=displayRequired value='N'></applet>";
	$("#resultset").html(htmlCode); */
        $.ajax({
          type: "get",
          url:"Ticketing/RawPrint.jsp",
          data:"DetailsOfServiceTaxAmount=new",
            success: function(result){
            	$("#dailyticketraw").html(result);
            	//alert(result);
              
            }
        });
}
</script>
 
 <form>
 <INPUT type="hidden" id="singledate" name="targetamount.singledate" />
 </form>
 
<div class="page-content-wrapper">
		<div class="page-content" >
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
								<i class="fa fa-globe"></i>SHIFT-WISE EPKM STATEMENT
							</div>
						</div>
						
						<div class="portlet-body">
						<div class="table-scrollable">
						
						
						
						
						
						
						 <form action="" method="post" class="form-horizontal" >
                           <div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Division<font
											color="red">*</font></label>
										<div class="col-md-4">
											<s:select list="divisionlist" id="divisionlist1" 
											name="divisionlist1"  
												cssClass="select2_category form-control"  
												 onchange="getDepot(this.value)" headerKey="0" headerValue="---select---"></s:select>  
												 
										</div>
									</div>
								
                             
								<div class="form-group">
									<label class="col-md-3 control-label">Depot<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select list="depotlist" id="depotlist1" class="select2_category form-control" name="depotlist1" onchange="getScheduleType(this.value)"
 											> 
											<option value="0">--select--</option>
 										</select> 
									</div>
 								</div> 
 								
 								
 								<div class="form-group">
									<label class="col-md-3 control-label">Schedule Type<font
										color="red">*</font></label>
									<div class="col-md-4">
										 <select  id="scheduletypeid" class="select2_category form-control" name="scheduletypeid" onchange="ShowType(this.value)"
 											> 
											<option value="0">--select--</option>
 										</select> 
                                       

										<script> 
 											var conductorno ="<s:property value='conductorid'/>";
 											if(conductorno!=""){ 
 												document.getElementById(conductorno).selected= true; 
 											} 
 											
										</script> 
									</div>
 								</div> 
 								
 								
 								
 								<div class="form-group" id="nightout">
									<label class="col-md-3 control-label">Shift Type<font
										color="red">*</font></label>
									<div class="col-md-4">
										 <select  id="shifttypeid" class="select2_category form-control" name="shifttypeid"
 											> 
											<option value="0">--select--</option>
											<option value="2">Day1</option>
											<option value="3">Day2</option>
 										</select> 
                                       

										<script> 
 											var nightout ="<s:property value='shifttypeid'/>";
 											if(nightout!=""){ 
 												document.getElementById(nightout).selected= true; 
 											} 
 											
										</script> 
									</div>
 								</div> 
 								
 								<div class="form-group" id="dayout">
									<label class="col-md-3 control-label">Shift Type<font
										color="red">*</font></label>
									<div class="col-md-4">
										 <select  id="shifttypeid1" class="select2_category form-control" name="shifttypeid1"
 											> 
											<option value="0">--select--</option>
											<option value="4">Shift 1</option>
											<option value="5">Shift 2</option>
 										</select> 
                                       

										<script> 
 											var dayout ="<s:property value='shifttypeid1'/>";
 											if(dayout!=""){ 
 												document.getElementById(dayout).selected= true; 
 											} 
 											
										</script> 
									</div>
 								</div> 
 								
 								
 								
 								
                          
							 <div class="form-group">
							  <label class="control-label col-md-3">Start Date:</label>
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
                                        curr_date=curr_date[2]-2+"-"+curr_date[1]+"-"+curr_date[0];
                                        var dtval=document.getElementById('startdate').value;	
                                        
                                        if(dtval==''){
                                        $('#startdate').val(curr_date);
                                        }
								</script>
								</div>
								</div></div>
								
								 <div class="form-group">
							  <label class="control-label col-md-3">Till Date:</label>
								<div class="col-md-3">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" ><!-- data-date-start-date="+0d"> -->
								<input id="enddate" class="form-control" type="text" readonly="" size="16"
								>
								<span class="input-group-btn">
								<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
								</button>
								</span>
								<script>

										var curr_date=new Date().toJSON().slice(0,10);
                                        curr_date=curr_date.split("-");
                                        curr_date=curr_date[2]-2+"-"+curr_date[1]+"-"+curr_date[0];
                                        var dtval=document.getElementById('enddate').value;	
                                        
                                        if(dtval==''){
                                        $('#enddate').val(curr_date);
//                                         $( "#enddate" ).datepicker({  maxDate: curr_date});
                                        }
								</script>
								</div>
								</div></div>
								
						  
                     
							
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
 	                  downloadLink.download = "Shift Wise EPKM Report.xls"; 
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
						
						
						
						
						
							

<%-- 							<div id="NoData" align="center"><span><lable>No Data Found</lable></span></div> --%>

                         <div align='center'>
					<button type="button" class="btn green" onClick="getData()">Submit</button> 
					<button type="button" id="btnExport" class="btn green" onclick="tabletoExcel();"> EXPORT </button>
                   <span><input type='button' class='btn green' id='button1' onclick='printhiv()' value='Print' /></span>&nbsp;<span>
<%-- 					 <input type='button' class='btn green' id='button1' name='rawprint' onclick='rawPrint()' value='RawPrint' /></span></div> --%>
					<!-- END EXAMPLE TABLE PORTLET-->
					
				</div>
                        
							</div>
							
						</div>
					</div>
					<div id="ticketconsuptionid"></div>
<!-- 					 <div id="ticketconsuptionid"></div> -->
<%-- 					<div align='center'><span><input type='button' class='btn green' id='button1' onclick='printhiv()' value='Print' /></span>&nbsp;<span><input type='button' class='btn green' id='button1' name='rawprint' onclick='rawPrint()' value='RawPrint' /></span></div> --%>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
 			</div>
			<div id="dailyticketraw">
			</div>
			
			<!-- END PAGE CONTENT-->
		</div>
		
	</div>
	
	
	