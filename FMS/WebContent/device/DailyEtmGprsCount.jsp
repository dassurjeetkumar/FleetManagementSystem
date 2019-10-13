  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
 
<script>
function jsFunction(dt){
	var dat=dt;
// 	alert("In js Function"+dat);
	document.getElementById("singledate").value=dat;
	serviceTaxDetails(dat);
<%--             var mywindow = window.open("<%=request.getContextPath()%>/AjaxserviceTaxCollectionReport1.action?singledate="+dat); --%>
<%--             var mywindow = window.load("<%=request.getContextPath()%>/AjaxserviceTaxCollectionReport1.action?singledate="+dat); --%>
   
        
        	}
            
function serviceTaxDetails(dt){
	var dat=dt;
	//alert("In js Function"+dat);
	document.getElementById("singledate").value=dat;
	document.forms[0].action = 'AjaxdetailsOfServiceTaxAmount.action?singledate='+dat;
	 document.forms[0].submit(); 
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
	
function getConductor(depotId){
	 $('#select2-chosen-2').html("Select");
	 $('#select2-chosen-3').html("Select");
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 var val=document.getElementById('depotlist1').value;
			 if(val!=0) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/getConductorToken?val=' + val,
				success : function(result) {
					document.getElementById('conductorid').innerHTML = result;
					
				}
			});
		}

	}
	

function validate()
{
	 
//    alert("hii");
	
	 var startdate=$('#startdate').val();
	 var name=$('#name').val();
	 
// 	 var curr_date=new Date().toJSON().slice(0,10);
//      curr_date=curr_date.split("-");
//      curr_date=curr_date[0]+"-"+curr_date[1]+"-"+curr_date[2];
// //      alert(curr_date);
//      var cur_date=Date.parse(curr_date);

//      var var1=$("#startdate").val();
// 		var1=var1.split("-");
// 		var1=var1[2]+"-"+var1[1]+"-"+var1[0];
// 		var var2=$("#enddate").val();
// 		var2=var2.split("-");
// 		var2=var2[2]+"-"+var2[1]+"-"+var2[0];
// 		//alert(var1+""+var2);
// // 		alert(var1+"  "+var2);
// 		 var start_date = Date.parse(var1);
// 		 var end_date= Date.parse(var2);

  
 if( name == "0" )
   {
      //alert( "Please select vehicle" );
       bootbox.alert("Please Select ETM/WAYBILL");
      return false;
   }
   if( startdate == "" )
   {
      //alert( "Please select vehicle" );
       bootbox.alert("Please Select Date");
      return false;
   }

   return( true );
   
}
	
	
	

function getData(){
	
	var valid=validate();
		var date=$("#startdate").val();
		var name=$("#name").val();
		

// alert(valid);
		if(valid==true){
// 			alert("hello");
// 	var d3=Date.parse(var2);
// 	alert(d1+"  "+d3);
// 		if (d1 <= d3){
			$('#pageLoader').show();
        $.ajax({
        
            type: "get",
            url: '<%=request.getContextPath()%>/AjaxgetEtmGprsCount.action?startdate='+date+'&name='+name,
            success: function(result) {
            	$('#pageLoader').hide();
                document.getElementById('ticketconsuptionid').innerHTML=result;
                fixHeader();
            }
        });
// 		}else{
 			
 			
//  			alert("Till Date Should Be greater Than Start Date");
//  			$('#pageLoader').hide();
//  			 document.getElementById('ticketconsuptionid').innerHTML="";
//  		}
		}
// 		$('#pageLoader').hide();
// 		 document.getElementById('ticketconsuptionid').innerHTML="";
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
								<i class="fa fa-globe"></i>DAILY ETM GPRS COUNT REPORT
							</div>
						</div>
						
						<div class="portlet-body">
						<div class="table-scrollable">
						
						
						
						
						
						
						 <form action="" method="post" class="form-horizontal" >
                           <div class="form-body">
									
                             
								<div class="form-group">
									<label class="col-md-3 control-label">ETM/WAYBILL<font
										color="red">*</font></label>
									<div class="col-md-3">
										<select  id="name" class="select3_category form-control" name="name" 
 											> 
											<option value="0">--select--</option>
											<option value="1">ETM</option>
											<option value="2">WAYBILL</option>
 										</select> 
									</div>
 								</div> 
 								
 								
                          
							 <div class="form-group">
							  <label class="control-label col-md-3">Date:<font
											color="red">*</font></label></label>
								<div class="col-md-3">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" data-date-end-date="-2d"><!-- data-date-start-date="+0d"> -->
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
//                                         $('#startdate').val(curr_date);
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
 	                  downloadLink.download = "DailyEtmGprsCountReport.xls"; 
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
	
	
	