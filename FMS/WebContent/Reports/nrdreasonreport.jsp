  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
 
<script>

var i=0;
<%-- function getDepot(orgId){
	 $('#select2-chosen-2').html("Select");
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 var val=document.getElementById('divisionlist').value;
			 if(val!=0) {
				 $("#msg").html("Please wait...");
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/getDepotfornrdreason?val=' + val,
				success : function(result) {
					$("#msg").html("");
					document.getElementById('depotlist').innerHTML = result;
				}
			});
		}

	} --%>
	function getDepot(orgId){
		 $('#select2-chosen-2').html("Select");
			//alert('Here');
			 /* var selectedValue = $('#form-control').val(); */
			 var val=document.getElementById('divisionlist').value;
				 if(val!=0) {
					 $("#msg").html("Please wait...");
		        $.ajax({
		            type: "post",
		            url: '<%=request.getContextPath()%>/AjexnrdgetDepot?val=' + val,
					success : function(result) {
						$("#msg").html("");
						document.getElementById('depotlist').innerHTML = result;
					}
				});
			}

		}
function getdata(){
	
	  	var dd1=$("#startdate").val();
		var dd2=$("#enddate").val();
 		var var1=$("#startdate").val();
 		var1=var1.split("-");
		var1=var1[2]+"-"+var1[1]+"-"+var1[0];
 		var var2=$("#enddate").val();
 		var2=var2.split("-");
 		var2=var2[2]+"-"+var2[1]+"-"+var2[0];
		//alert(var1+""+var2);
		var div=$("#divisionlist").val();
		 var d1 = Date.parse(var1);
		 var depot=$("#depotlist").val();
		 
 	var d3=Date.parse(var2);
 	if(div==0){
 		alert("Please Select The Division");
 	}
 	else if (d1 <= d3){
 			 $('#pageLoader').show();
        $.ajax({
        
            type: "post",
            url: '<%=request.getContextPath()%>/getnrdreason.action?startdate='+dd1+'&enddate='+dd2+'&depot='+depot+'&divison='+div,
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
function resetDate(){
	$('#startdate').val('');
	$('#enddate').val('');
}
</script>
<SCRIPT>
/* function showsubreason(){
	//var test="GPS Fitted or Not";
	var val=document.getElementById("reason").value;
	//alert(val);
    if(val=="GPS Fitted or Not"){
    	// $("#Descreption").show();
    	 $("#vsonroad").hide();
    	 $("#vsoffroad").hide();
    	 //$("#reasonn").show();
    	 $("#resDescreption").show();
    	 $("#submitbutten").show();
    	 $("#slovedate").show();
     } 
    else if(val =="Vehicle Status On Road")
    {
    	$("#Descreption").hide();
   	 $("#vsonroad").show();
   	 $("#vsoffroad").hide();
   	 $("#resDescreption").show();
   	 $("#submitbutten").show();
   	 $("#slovedate").show();
   }
    else if(val =="Vechicle Status Off Road") 
    {
    	$("#Descreption").hide();
      	 $("#vsonroad").hide();
      	 $("#vsoffroad").show();
      	 $("#resDescreption").show();
      	 $("#submitbutten").show();
      	 $("#slovedate").show();
    } 
} function hideing(){ $("#vsonroad").hide();
 	$("#vsoffroad").hide();
 	$("#reasonn").hide();
 	$("#resDescreption").hide();
 	$("#submitbutten").hide();
  $("#reason").val(0);
  	$("#resdescrip").val('');
  	$("#vsor").val(0);
  	$("#vsoffr").val(0); 
  	$("#ticketconsuptionid1").show();
  	 $("#slovedate").hide();
 	}
$(document).ready(function() {
	//$("#Descreption").hide();
 	 $("#vsonroad").hide();
 	$("#vsoffroad").hide();
 	$("#reasonn").hide();
 	$("#resDescreption").hide();
 	$("#submitbutten").hide();
 	
}); */

function callCancell() {

	window.location.href = 'NRDReasonReport';
	
}
</SCRIPT>
<script>

var val;
function getdepotwiseexceptionreport(vehiclenumbe,deviceid,reason,subreason,descrip,reasondate) {
	//alert(vehiclenumbe+reasondate+deviceid);
	$('#vehiclenumber').val(vehiclenumbe);
	$('#deviceidentification').val(deviceid);
	$('#reasonnumber').val(reason);
	$('#subreasonnumber').val(subreason);
	$('#descripnum').val(descrip);
	$('#readate').val(reasondate);
	//var test="GPS Fitted or Not";
	//var val=document.getElementById("reason").value;
	//alert(val);
    //$("#ticketconsuptionid1").hide();
    	// $("#Descreption").show();
//     	 $("#vsonroad").hide();
//     	 $("#vsoffroad").hide();
//     	 $("#reasonn").show();
//     	 $("#resDescreption").hide();
  /*    } 
    else if(val =="Vehicle Status On Road")
    {
    	$("#Descreption").hide();
   	 $("#vsonroad").show();
   	 $("#vsoffroad").hide();
   }
    else if(val =="Vechicle Status Off Road") 
    {
    	$("#Descreption").hide();
      	 $("#vsonroad").hide();
      	 $("#vsoffroad").show();
    } */
 
}
/*     $(document).ready(function() {
    	//$("#Descreption").hide();
     	 $("#vsonroad").hide();
     	$("#vsoffroad").hide();
     	$("#reasonn").hide();
     	$("#resDescreption").hide();
     	$("#submitbutten").hide();
     	$("#slovedate").hide(); */

   // });
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
function savedata(){
	//alert("saveddata entry");
	  var sloveingdate=$("#sloveingdate").val();
		 
	   var resdescrip=$("#resdescrip").val();
	   var devid=$("#deviceidentification").val();
	   var vehid=$("#vehiclenumber").val();
	   var readate=$("#readate").val();
	if(sloveingdate==""){alert("please select slove date");}
        else {
   		 $('#pageLoader').show();
   		 //alert("before inssert");
         $.ajax({
         
             type: "post",
             url: '<%=request.getContextPath()%>/savereasoninreasonnrd.action?sloveingdate='+sloveingdate+'&resdescrip='+resdescrip+'&devid='+devid+'&vehid='+vehid+'&readate='+readate,
            	 success: function(result) {
                 	$('#pageLoader').hide();
                     document.getElementById('ticketconsuptionid1').innerHTML=result;
                     fixHeader();
                     getdata();
             }
         });
         
         
       // $("#mymodal1").close();
        $('#mymodal1').modal('hide');
		}//alert("before calling");
/*   getdata();
hideing();  */ 
//alert("after calling");
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
								<i class="fa fa-globe"></i>NRD Reason Report(I Triangle)
							</div>
						</div>
						
						<div class="portlet-body">
						

							<!-- 						start -->

<!-- <table> -->
                   <form action="" method="post" class="form-horizontal">
                    <b>
								<%-- <font color="green"><h3> <s:actionmessage/></h3></font> --%>
								<span id="msg" style="color:red;word-wrap: break-word;" ><s:actionerror/></span>
							</b>
                           <div class="form-body">
									
                             <div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Division<font
											color="red">*</font></label>
										<div class="col-md-4">
											<s:select list="divisionlist" id="divisionlist"
												name="orgchart.org_chart_id"
												cssClass="select2_category form-control" headerKey="0" headerValue="---Select---"
												 onchange="getDepot(this.value)"></s:select>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">Depot<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select id="depotlist" class="select2_category form-control"
											onchange="resetDate()">
											<option value="0">---All---</option>
										</select>
									</div>
								</div>
                          
							 <div class="form-group">
							  <label class="control-label col-md-3">From Date:</label>
								<div class="col-md-3">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" data-date-end-date="0d"> <!-- data-date-start-date="+0d" -->
								<input id="startdate" class="form-control" type="text" readonly="" size="16" name="rateMaster.effectiveStartDate" placeholder="Pick The Date"
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
															data-date-viewmode="years"  data-date-end-date="0d"> 
								<input id="enddate" class="form-control" type="text" readonly="" size="16" name="rateMaster.effectiveStartDate" placeholder="Pick The Date"
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
 	                  downloadLink.download = "NRD Reason Report.xls"; 
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
					<button type="submit" class="btn green" onClick="getdata()">Submit</button> 
					<button type="button" id="btnExport" class="btn green" onclick="tabletoExcel();"> EXPORT </button>
                   <span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span>&nbsp;<span>
<%-- 					 <input type='button' class='btn green' id='button1' name='rawprint' onclick='rawPrint()' value='RawPrint' /></span></div> --%>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
				
			<!-- 		<div id="ticketconsuptionid1"></div> -->
					<font color="red"><h3><div id="ticketconsuptionid1"></div></h3></font> 
					<div id="ticketconsuptionid"></div>

			</div>
			
			<!-- END PAGE CONTENT-->
		</div>
	</div>
	</div>
	</div>
		 <div style="display: none;" id="mymodal1" class="modal fade"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
		aria-hidden="true">
		<div class="modal-dialog" id="mod21">
			<div class="modal-content" style="width: 600px; height: 630px;"
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
						<h4><span class="help-block" style="color: green; list-style: none"><div id="ticketconsuptionid1"></div></span></h4>
							<input type="hidden" name="shift" id="shift" />
	
							<div>
							
								<div class="portlet solid white">
									<div class="form-group">
										<label class="col-md-3 control-label"></label>
									</div>
									<div class="form-group"><form  method="post" class="form-horizontal">
									<table id ="tableid1">		
									<div>
<!-- 									<input type="hidden" name="scheduleId" id="scheduleId" /></div>						 -->
										<div class="form-group">
											<label class="col-md-3 control-label">Device Id </label>
											<div class="col-md-4">
												<input type="text" class="form-control" id="deviceidentification"
													name="deviceid7" readonly="readonly" value=''></input>
											</div>
										</div>										
											<div class="form-group">
												<label class="col-md-3 control-label">Vehicle No.</label>
												<div class="col-md-4">
													<input type="text" class="form-control" id="vehiclenumber"
														name="vehicleNo7" readonly="readonly" value=''></input>
												</div>
											</div>
						<div class="form-group">
												<label class="col-md-3 control-label">Reason.</label>
												<div class="col-md-4">
													<input type="text" class="form-control" id="reasonnumber"
														name="vehicleNo7" readonly="readonly" value=''></input>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">Subreason.</label>
												<div class="col-md-4">
													<input type="text" class="form-control" id="subreasonnumber"
														name="vehicleNo7" readonly="readonly" value=''></input>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label">Reason Description.</label>
												<div class="col-md-4">
													<textarea  class="form-control" id=descripnum maxlength="100"	readonly="readonly" autofocus="autofocus" name="description">
													<s:property value="notes" /></textarea>	
												</div>
											</div>
				                              <div class="form-group">
												<label class="col-md-3 control-label">Reason Date.</label>
												<div class="col-md-4">
													<input type="text" class="form-control" id="readate"
														name="vehicleNo7" readonly="readonly" value=''></input>
												</div>
											</div>
										
										
				      
											<div class="form-group" id="slovedate">
							  <label class="control-label col-md-3">Date Of Solved:<font
										color="red">*</font></label>
								<div class="col-md-3">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years"  data-date-end-date="0d"> 
								<input id="sloveingdate" name="dateaction" class="form-control" type="text" readonly="" size="16" name="rateMaster.effectiveStartDate" placeholder="Pick The Date" 
								value="<s:property value='rateMaster.effectiveStartDate' />"
								>
								<span class="input-group-btn">
								<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
								</button>
								</span>
							<%-- 	<script>
 										var curr_date=new Date().toJSON().slice(0,10);
                                        curr_date=curr_date.split("-");
                                         curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
                                        var dtval=document.getElementById('startdate').value;	
                                        
                                         if(dtval==''){
                                         $('#startdate').val(curr_date);
                                        }
								</script> --%>
								</div>
								</div></div>
								
								<div class="form-group" id="resDescreption">
									<label class="col-md-3 control-label">Solved Description:<font
										color="red"></font></label>
										<div class="form-group">
								
										<div class="col-md-4"> 
										<textarea  class="form-control" id=resdescrip maxlength="100"	 autofocus="autofocus" name="description">
													<s:property value="notes" /></textarea>	
					          </div>
				            </div>
											</div>
								
											</table></form>
						   <div align='center' id="submitbutten">
					<button type="button" class="btn green" onclick="savedata();">Save</button> 
					<button type="button" id="btnExport" class="btn green" onclick="callCancell();">Cancel </button>
                   <%-- <span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span>&nbsp;<span>
					 <input type='button' class='btn green' id='button1' name='rawprint' onclick='rawPrint()' value='RawPrint' /></span></div> --%>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>  
						</div></div>			</div>
								</div>
							</div>
						</div>
						</p>
					</div>
				</div>
			</div>
	</div>
	</div>
	</head>
	</html>

	
	
	
