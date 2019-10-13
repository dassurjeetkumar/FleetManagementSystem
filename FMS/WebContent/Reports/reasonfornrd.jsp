  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<script src="https://code.jquery.com/jquery-1.11.3.min.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
 
<script>

var i=0;
function getDepotList(orgId){
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
					document.getElementById('depotlist1').innerHTML = result;
				}
			});
		}

	}
function getdepotwiseexceptionreport(depotid){
// 	alert("hi");
}
function getdata(){
	
	
// 	alert("get data");
	  	var dd1=$("#startdate").val();
	
		var div=$("#divisionlist").val();
		 var depot=$("#depotlist1").val();
	
 			if(div ==0){
 				alert("please select Division");
 			}else if(dd1==null || dd1=="" |dd1==" "){alert("please Select the date");}
 			else if(div !=0){
 			 $('#pageLoader').show();
        $.ajax({
        
            type: "post",
            url: '<%=request.getContextPath()%>/getdatafornrdsave.action?div='+div+'&startdate='+dd1+'&depot='+depot,
            success: function(result) {
            	$('#pageLoader').hide();
                document.getElementById('ticketconsuptionid').innerHTML=result;
                fixHeader();
            }
        });
 		}
	
}
function resetDate(){
	$('#startdate').val('');
	$('#enddate').val('');
}
$(document).ready(function() {
	//$("#Descreption").hide();
 	 $("#vsonroad").hide();
 	$("#vsoffroad").hide();
 	$("#reasonn").hide();
//  	$("#resDescreption").hide();
//  	$("#submitbutten").hide();
 	 $("#vsroadws").hide();
	    $("#vsCWS").hide();
	    $("#vsDWS").hide();
 	
});
</script>
<script>
var val;
function getdepotwiseexceptionreport(vehiclenumbe,reasondate,deviceid) {
	//alert(vehiclenumbe+reasondate+deviceid);
	$('#vehiclenumber').val(vehiclenumbe);
	$('#deviceidentification').val(deviceid);
	//var test="Gps Not Fitted";
	//var val=document.getElementById("reason").value;
	//alert(val);
    //$("#ticketconsuptionid1").hide();
    	// $("#Descreption").show();
    	 /* $("#vsonroad").hide();
    	 $("#vsoffroad").hide();
    	 $("#reasonn").hide();
    	 $("#devicestatus").show();
    	 $("#resDescreption").hide();
    		$("#submitbutten").hide(); */
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
function  reasontodis(){
	var val=document.getElementById("devicestat").value;
	//alert(val);
	 if(val=="Gps Not Fitted"){
	    	// $("#Descreption").show();
	    	 $("#vsonroad").hide();
	    	 $("#vsoffroad").hide();
	    	 //$("#reasonn").show();
	    	 $("#resDescreption").show();
	    	 $("#submitbutten").show();
	    	 $("#reasonn").hide();
	    	  $("#reason").val(0);
	    	  	//$("#resdescrip").val('');
	    	  	$("#vsor").val(0);
	    	  	$("#vsoffr").val(0);
	    	  	 $("#vsroadws").hide();
	    		    $("#vsCWS").hide();
	    		    $("#vsDWS").hide();
	    	 
	     } 
	 else if(val =="Gps Fitted")
	    {
	    /* 	$("#Descreption").hide();
	   	 $("#vsonroad").show()();
	   	 $("#vsoffroad").show()();
	   	 $("#resDescreption").show()();
	   	 $("#submitbutten").show()(); */
	   	$("#reasonn").show();
	    $("#vsroadws").show();
	    $("#vsCWS").show()();
	    $("#vsDWS").show();
	   }
	 
}
function showsubworkshops(){
	var val=document.getElementById("vsoffr").value;
	if(val=="CWS"){
		 $("#vsCWS").show();
		    $("#vsDWS").hide();
		
	}else if(val=="DWS"){
		 $("#vsCWS").hide();
		    $("#vsDWS").show();
	}
}     

          function showsubreason(){
        	  
        	  $("#vsonroad").show();
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/AjaxGetNrdSubReason',
				success : function(result) {
					$("#msg").html("");
					document.getElementById('vsor').innerHTML = result;
				}
			});
          }
/* function showsubreason(){
	//var test="Gps Not Fitted";
	var val=document.getElementById("reason").value;
	//alert(val);
 
    if(val =="Vehicle Status On Road")
    {
    	$("#Descreption").hide();
   	 $("#vsonroad").show();
   	 $("#vsoffroad").hide();
   	 $("#resDescreption").show();
   	 $("#submitbutten").show();
   	$("#vsCWS").hide();
    $("#vsDWS").hide();
   }
    else if(val =="Vechicle Status Off Road") 
    {
    	$("#Descreption").hide();
      	 $("#vsonroad").hide();
      	 $("#vsoffroad").show();
      	 $("#resDescreption").show();
      	 $("#submitbutten").show();
    }
    else if(val =="Road Worth Spare"){
    	$("#Descreption").hide();
      	 $("#vsonroad").hide();
      	 $("#vsoffroad").hide();
      	 $("#resDescreption").show();
      	 $("#submitbutten").show();
      	 $("#vsroadws").hide();
      	$("#vsCWS").hide();
	    $("#vsDWS").hide();
    }
}  */
function hideing(){  /* $("#vsonroad").hide(); 
 	 $("#vsoffroad").hide();
 	$("#reasonn").hide();
 	$("#resDescreption").hide();
 	$("#submitbutten").hide(); */
  /* $("#reason").val(0);
  	$("#resdescrip").val('');
  	$("#vsor").val(0);
  	$("#vsoffr").val(0); */ 
  	$("#ticketconsuptionid1").show(); 
  	
  	
 	}
$(document).ready(function() {
	//$("#Descreption").hide();
 	 $("#vsonroad").hide();
 	$("#vsoffroad").hide();
 	$("#reasonn").hide();
//  	$("#resDescreption").hide();
//  	$("#submitbutten").hide();
 	
});

function callCancell() {

	window.location.href = 'ReasonForNrdReport';
	
}
 </script>
<script>

function savedata(){
	  var div=$("#divisionlist").val();
	   var depot=$("#depotlist1").val();
	   var startdate=$("#startdate").val();
	  	var reason=$("#reason").val();
	  	var description=$("#resdescrip").val().trim();
	  	var vehno=$("#vehiclenumber").val();
	  	var fault1=$("#vsor").val();
	  	var devid=$("#deviceidentification").val();
	  	var worthreason = document.getElementById("reason").value;
	  	var devstatus=$("#devicestat").val();

//     alert("div--"+div+"depot---"+depot+"sytart---"+startdate+"reason==="+reason+"description===="+description+"vehno--"+vehno+"sub reason --"+fault1+"devid=="+devid);
    if(startdate ==null){
    	alert("please select Date");
    	return false;
    	}
     if(devstatus==0){
    	alert("please select Device Status");
    	return false;
    	}
     if(devstatus=='Gps Fitted'){
    		  if(reason==0){
    			alert("Please Select Vehicle Status");
    			return false;
    		} if(fault1==0){
    			alert("Please select Sub Reason");
    			return false;
    		}
    		}
       
	   		 $('#pageLoader').show();
	         $.ajax({
	         
	             type: "post",
	             url: '<%=request.getContextPath()%>/savereasonnrd.action?reasonname='+reason+'&devstatus='+devstatus+'&deviceid='+devid+'&vehno='+vehno+'&vsor='+fault1+'&description='+description+'&startdate='+startdate,
	             success: function(result) {
	            	 
	             	$('#pageLoader').hide();
	                document.getElementById('ticketconsuptionid1').innerHTML=result;
	                 fixHeader();
	                 getdata();
	         		hideing();
	             }
	         });
	         $('#mymodal1').modal('hide');
			
    

}

		function savedata1(){
			//alert("saveddata entry");
			  var div=$("#divisionlist").val();
				 
			   var depot=$("#depotlist1").val();
			   //var vehicleno=$("#vehiclelist").val();
			   var startdate=$("#startdate").val();
			  	var reason=$("#reason").val();
			  	var description=$("#resdescrip").val().trim();
			  	var fault1=$("#vsor").val();
			  	var fault2=$("#vsoffr").val();
			  	var devid=$("#deviceidentification").val();
			  	var vehno=$("#vehiclenumber").val();
			  	var devstatus=$("#devicestat").val();
			  	var cws=$("#vsincws").val();
			  	var dws=$("#vsindws").val();
			  	var vehOffRoad = $("#vsoffroad").val(); 
			  	var vehOnRoad = $("#vsonroad").val();
			  	var worthreason = document.getElementById("reason").value;
// 			  	alert("--"+document.getElementById("reason").value);
// 			  	alert("device id and vehno--"+fault1+"====vehno===="+fault2);
			//alert("fault is--"+fault);
	
		        //else if(vehicleno==0){alert("please select Vehicle No");}
		         if(startdate ==null){alert("please select Date");}
		    else if(devstatus==0){alert("please select Device Status");}
		        else if(reason ==0 && devstatus==0 ){alert("please select Reason");}
		        //else if(devid ==null){alert("please select Reason");}
// 		        else if(devstatus=="Gps Fitted" && worthreason =="Road Worth Spare"){
// 		        	return true;
// 		        	}
// 		        else if(devstatus !="Gps Not Fitted" && worthreason !="Road Worth Spare") { //&& (fault1 ==0 || fault2 ==0)){
		        	
// 		        alert("here");
// 		               if(fault1 !=0 || fault2 !=0 ){
		            	   
// 		               alert("herrr");
// 		            	   return true;
// 		               }else{
// 		            	alert("fault1=="+fault1+"fault2=="+fault2);   
		               
// 		            	   alert("please select Vehicle Status On Road or Vechicle Status Off Road");
// 		            	   }
// 		               alert("come");
// 		               }

		     //alert("check");  
		       // else if(dateaction !=null){alert("please select date of action");}
		        else {
		   		 $('#pageLoader').show();
		   		 //alert("before inssert");
		         $.ajax({
		         
		             type: "post",
		             url: '<%=request.getContextPath()%>/savereasonnrd.action?reasonname='+reason+'&devstatus='+devstatus+'&deviceid='+devid+'&vehno='+vehno+'&vsor='+fault1+'&vsoffr='+fault2+'&description='+description+'&startdate='+startdate+'&cws='+cws+'&dws='+dws,
		             success: function(result) {
		            	 
		             	$('#pageLoader').hide();
		                document.getElementById('ticketconsuptionid1').innerHTML=result;
		                 fixHeader();
		                 getdata();
		         		hideing();
		         		//break;
		             }
		         });
		         //getdata();
		         $('#mymodal1').modal('hide');
				}//alert("before calling");
		/*   getdata();
		hideing();  */ 
		//alert("after calling");
		}



</script>
<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">
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
								<i class="fa fa-globe"></i>NRD Reason Report(Depot)
							</div>
						</div>
						
						<div class="portlet-body">
						

							<!-- 						start -->

<!-- <table> -->
                   <form  method="post" class="form-horizontal">
                    <b>
								<%-- <font color="green"><h3> <s:actionmessage/></h3></font> --%>
								<span id="msg" style="color:red;word-wrap: break-word;" ><s:actionerror/></span>
							</b>
                           <div class="form-body">
									
                           	<div class="form-group">
										<label class="col-md-3 control-label">Division<font
											color="red">*</font></label>
										<div class="col-md-3">
											<s:select list="divisionlist" id="divisionlist" 
											name="org_chart_id"  
												cssClass="select2_category form-control"  
												 onchange="getDepotList(this.value)" ></s:select>  
												 
										</div>
<%-- 										<script> --%>
<!-- 									getDepotList(""); -->
<%-- 										</script> --%>
									</div>
								
                               <div class="form-group">
									<label class="col-md-3 control-label">Depot<font
										color="red">*</font></label>
									<div class="col-md-3">
										<select list="depotlist" id="depotlist1" class="select2_category form-control" name="depotlist1" 
 											> 
											<option value="0">--All--</option>
 										</select> 
									</div>
 								</div>
                          
						
						  
							<div class="form-group">
							  <label class="control-label col-md-3">Date Of Report:<font
										color="red">*</font></label>
								<div class="col-md-3">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" data-date-end-date="0d"> 
								<input id="startdate" name="dateaction" class="form-control" type="text" readonly="" size="16" name="rateMaster.effectiveStartDate" placeholder="Pick The Date" 
								value="<s:property value='rateMaster.effectiveStartDate' />"
								>
								<span class="input-group-btn">
								<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
								</button>
								</span>
								<%-- <script>
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
							
							
					 <div class="form-group">
					 <label class="control-label col-md-3"></label>
                     <div class="col-md-3" id="">
                     </div>
                     </div>
                     
<!--                      <div align='center'> -->
<!-- 					<button type="submit" class="btn green" onClick="getdata()">Submit</button> -->
<%-- 				 	<button type="button" id="btnExport" class="btn green" onclick="tabletoExcel();"> EXPORT </button>
<%--                    <span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span>&nbsp;<span> --%>
<%-- 					 <input type='button' class='btn green' id='button1' name='rawprint' onclick='rawPrint()' value='RawPrint' /></span></div>--%>
<!-- 					END EXAMPLE TABLE PORTLET -->
<!-- 				</div>  -->
				<font color="red"><h3><div id="ticketconsuptionid1"></div></h3></font>
					
					<!-- <div id="ticketconsuptionid1"></div> -->
				<!-- 	<div id="vehiclenumber"></div> -->
					<!-- <div id="deviceidentification"></div> -->
						<%-- <div class="form-group" id="reasonn">
									<label class="col-md-3 control-label">Reason:<font
										color="red">*</font></label>
									<div class="col-md-4">
						                <select id="reason"  class="select2_category form-control" name="reasonname" onchange="showsubreason()">
						                <option value="0">---select---</option>
											<option value="Gps Not Fitted">Gps Not Fitted</option>
											<option value="Vehicle Status On Road">Vehicle Status On Road</option>
											<option value="Vechicle Status Off Road">Vechicle Status Off Road</option>
																              
							           </select>
							          <script>
											var orgTypeId ="<s:property value='reason'/>";
											if(orgTypeId!=""){
 												document.getElementById(orgTypeId).selected= true; 
											
											}
											
											
										</script>
					</div>
											</div>
				         <div class="form-group" id="Descreption">
									<label class="col-md-3 control-label">Descreption:<font
										color="red">*</font></label>
										<div class="form-group">
								
										<div class="col-md-4"> 
										<textarea  class="form-control" id=descrip maxlength="100"	 autofocus="autofocus" name="descrip">
													<s:property value="notes" /></textarea>	
					          </div>
				            </div>
											</div>
											<div class="form-group" id="vsonroad">
									<label class="col-md-3 control-label">Vehicle Status On Road:<font
										color="red">*</font></label>
									<div class="col-md-4">
						                <select id="vsor"  class="select2_category form-control" name="vsor">
						                <option value="0">---select---</option>
											<option value="Battery Wire Connected">Battery Wire Connected</option>
											<option value="DEVICE PROBLEM">DEVICE PROBLEM</option>
																		              
							           </select>
							          
					</div>
											</div>
											<div class="form-group" id="vsoffroad">
									<label class="col-md-3 control-label">Vechicle Status Off Road:<font
										color="red">*</font></label>
									<div class="col-md-4">
						                <select id="vsoffr"  class="select2_category form-control" name="vsoffr">
						                <option value="0">---select---</option>
											<option value="Scheduled">Scheduled</option>
											<option value="Vechicle Under Maintenance">Vechicle Under Maintenance</option>
												<option value="D1,D2">D1,D2</option>
												<option value="Fc Renewal">Fc Renewal</option>
												<option value="Scrap">Scrap</option>
												<option value="Battery Problem">Battery Problem</option>	
												<option value="Heavy Body Repair">Heavy Body Repair</option>					              
							           </select>
							          
					</div>
											</div>
				           <div class="form-group" id="resDescreption">
									<label class="col-md-3 control-label">Descreption:<font
										color="red"></font></label>
										<div class="form-group">
								
										<div class="col-md-4"> 
										<textarea  class="form-control" id=resdescrip maxlength="100"	 autofocus="autofocus" name="description">
													<s:property value="notes" /></textarea>	
					          </div>
				            </div>
											</div>
						   <div align='center' id="submitbutten">
					<button type="button" class="btn green" onclick="savedata();">Save</button> 
					<button type="button" id="btnExport" class="btn green" onclick="callCancell();">Cancel </button>
                   <span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span>&nbsp;<span>
					 <input type='button' class='btn green' id='button1' name='rawprint' onclick='rawPrint()' value='RawPrint' /></span></div>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>  --%>
</div>
                 </form>  
                  <div align='center'>
					<button type="submit" class="btn green" onClick="getdata()">Submit</button>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>  
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
			<div class="modal-content" style="width: 600px; height: 600px;"
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
						<h4><span class="help-block" style="color: green; list-style: none"></span></h4>
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
										
													
										<div class="form-group" id="devicestatus">
									<label class="col-md-3 control-label">Device Status:<font
										color="red">*</font></label>
									<div class="col-md-4">
						                <select id="devicestat"  class="select2_category form-control" name="devicestat" onchange="reasontodis()">
						                <option value="0">---select---</option>
											<option value="Gps Not Fitted">GPS Not Fitted</option>
											<option value="Gps Fitted">Gps Fitted</option>
																              
							           </select>
							          <script>
											var orgTypeId ="<s:property value='reason'/>";
											if(orgTypeId!=""){
 												document.getElementById(orgTypeId).selected= true; 
											
											}
											
											
										</script>
					</div>
											</div>
										
										
										
											
										<div class="form-group" id="reasonn">
									<label class="col-md-3 control-label">Vehicle Status:<font
										color="red">*</font></label>
									<div class="col-md-4">
						                <select id="reason"  class="select2_category form-control" name="reasonname" onchange="showsubreason()">
						                <option value="0">---select---</option>
											<option value="Vehicle Status On Road">Vehicle Status On Road</option>
<!-- 											<option value="Vechicle Status Off Road">Vechicle Status Off Road</option> -->
<!-- 												<option value="Road Worth Spare">Road Worth Spare</option>				               -->
							           </select>
							          <script>
											var orgTypeId ="<s:property value='reason'/>";
											if(orgTypeId!=""){
 												document.getElementById(orgTypeId).selected= true; 
											
											}
											
											
										</script>
					</div>
											</div>
				        <%--  <div class="form-group" id="Descreption">
									<label class="col-md-3 control-label">Descreption:<font
										color="red">*</font></label>
										<div class="form-group">
								
										<div class="col-md-4"> 
										<textarea  class="form-control" id=descrip maxlength="100"	 autofocus="autofocus" name="descrip">
													<s:property value="notes" /></textarea>	
					          </div>
				            </div>
											</div> --%>
											<div class="form-group" id="vsonroad">
									<label class="col-md-3 control-label">Sub Reason:<font
										color="red">*</font></label>
									<div class="col-md-4">
						                <select id="vsor"  class="select2_category form-control" name="vsor">
						                <option value="0">---select---</option>
<!-- 											<option value="Battery Wire Connected">Battery Wire Connected </option> -->
<!-- 											<option value="Device Problem">Device Problem</option> -->
<!-- 											<option value="Fuse Issue">Fuse Issue</option> -->
																		              
							           </select>
							          
					</div>
											</div>
											
									<%-- 				<div class="form-group" id="vsroadws">
									<label class="col-md-3 control-label">Road Worth Spare:<font
										color="red">*</font></label>
									<div class="col-md-4">
						                <select id="vsrws"  class="select2_category form-control" name="vsrws" onchange="showsubworkshops()">
						                <option value="0">---select---</option>
											<option value="CWS">CWS</option>
											<option value="DWS">DWS</option>
																	              
							           </select>
							          
					</div>
											</div> --%>
								
											
									<%-- 		
											<div class="form-group" id="vsoffroad">
									<label class="col-md-3 control-label">Vechicle Status Off Road:<font
										color="red">*</font></label>
									<div class="col-md-4">
						                <select id="vsoffr"  class="select2_category form-control" name="vsoffr" onchange="showsubworkshops()">
						                <option value="0">---select---</option>
											<option value="CWS">CWS</option>
											<option value="DWS">DWS</option>					              
							           </select>
							          
					</div>
											</div> --%>
											
													<%-- 		<div class="form-group" id="vsCWS">
									<label class="col-md-3 control-label">CWS:<font
										color="red">*</font></label>
									<div class="col-md-4">
						                <select id="vsincws"  class="select2_category form-control" name="vsincws">
						                <option value="0">---select---</option>
											<option value="Heavy Body Repair/Other Engine Work">Heavy Body Repair/Other Engine Work</option>
											<option value="Fc Renewal">Fc Renewal</option>
											<option value="Scrap">Scrap</option>
											<option value="Battery Problem">Battery Problem</option>
											<option value="Scheduled/Planed">Scheduled/Planed</option>						              
							           </select>
							          
					</div>
								 --%>			</div>
											
										<%-- 		<div class="form-group" id="vsDWS">
									<label class="col-md-3 control-label">DWS:<font
										color="red">*</font></label>
									<div class="col-md-4">
						                <select id="vsindws"  class="select2_category form-control" name="vsindws">
						                <option value="0">---select---</option>
						                <option value="Vehicle Under Maintanice">Vehicle Under Maintanice</option>	
						                <option value="D1,D2">D1,D2</option>	
						                <option value="Battery Problem">Battery Problem</option>
						               <option value="Tyres">Tyres</option>	
						                	
											<option value="Want Of Engine">Want Of Engine</option>
											<option value="Want Of Spare">Want Of Spare</option>
											<option value="Want Of Assembly">Want Of Assembly</option>	
											<option value="Scrap Proposal">Scrap Proposal</option>
											<option value="FcDate Expired">FcDate Expired</option>					              
							           </select>
							          
					</div>
											</div> --%>
											
				           <div class="form-group" id="resDescreption">
									<label class="col-md-3 control-label">Description:<font
										color="red"></font></label>
										<div class="form-group">
								
										<div class="col-md-4"> 
										<textarea  class="form-control" id=resdescrip maxlength="100"	 autofocus="autofocus" name="description">
													<s:property value="notes" /></textarea>	
					          </div>
				            </div>
											</div></table></form>
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
	</body>
	</head>
	</html>

	
	
	
