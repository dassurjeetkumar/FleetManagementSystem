  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script> --%>
<%--  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" /> --%>
 
<script>

function getDepot(orgId){
	// $('#select2-chosen-2').html("Select");
	 //$('#select2-chosen-3').html("Select");
	 $('#select2-chosen-4').html("Select");
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 var val=document.getElementById('divisionlist').value;
			 if(val!=0) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/stoneits!getdepot?val=' + val,
				success : function(result) {
					document.getElementById('depotlist1').innerHTML = result;
					
				}
			});
		}

	}


	

function getDepot1(){
	
	   var div=$("#divisionlist").val();
	 
	   var depot=$("#depotlist1").val();
		var dd1=$("#startdate").val();
	

 	//	var scheduleid=$("#scheduleid").val();


 	if(div==0){
 		alert("Please Select The Division");
 	}
 	else if(depot==0){
 		alert("Please Select The Depot");
 	}
/* 	else if(scheduleid==0){
 		alert("Please Select The Schedule");
 	} */
	else if(dd1==null || dd1==''){
 		alert("Please Select The  Date");
 	}

 	else{
 			$('#pageLoader').show();
        $.ajax({
        
            type: "post",
            url: '<%=request.getContextPath()%>/stoneits!getdata.action?startdate='+dd1+'&depot='+depot+'&division='+div,
            success: function(result) {
            	$('#pageLoader').hide();
                document.getElementById('ticketconsuptionid').innerHTML=result;
                //fixHeader();
            }
        });
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
		divElements = divElements.replace("overflow-y:scroll", "");
		/* If two table exist  then remove the last table on print click*/
		/* if(noOfTableExist >= 2){
			divElements = divElements.substring(0, divElements.indexOf("</table>") + 8) + "</div></div>";
			
			consol.log(divElements);
		} */
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
<script>
function getcanceltrips(vehicle,waybill){
	$('#ticketconsuptionid1').empty();
  //  $('#mymodal1').modal('hide');
	//$('#mymodal1').hide();
	$('#ticketconsuptionid1').val("Please Wait !!!!!!!!!!!!");
		
     $.ajax({
     
         type: "post",
         url: '<%=request.getContextPath()%>/stone!getcanceltrip.action?vehicle='+vehicle+'&waybill='+waybill,
         success: function(result) {
        	 $('#ticketconsuptionid1').empty();
             document.getElementById('ticketconsuptionid1').innerHTML=result;
            
             //fixHeader();
         }
     });
     //$('#mymodal1').modal('show');
		
}
</script>
<SCRIPT type="text/javascript">
function getcanceltripslogsheet(formfour,date,depot){
	$('#ticketconsuptionid1').empty();
	  //  $('#mymodal1').modal('hide');
		//$('#mymodal1').hide();
		$('#ticketconsuptionid1').val("Please Wait !!!!!!!!!!!!");
			
	     $.ajax({
	     
	         type: "post",
	         url: '<%=request.getContextPath()%>/stone!getcanceltriplogsheet.action?date='+date+'&formfour='+formfour+'&depot='+depot,
	         success: function(result) {
	        	 $('#ticketconsuptionid1').empty();
	             document.getElementById('ticketconsuptionid1').innerHTML=result;
	            
	             //fixHeader();
	         }
	     });
	     //$('#mymodal1').modal('show');
			
	}
</SCRIPT>
 
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
								<i class="fa fa-globe"></i><font color=" 	#191970">DAILY OPERATIONAL STATISTICS (ST-1)</font>
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
										<div class="col-md-3">
											<s:select list="divisionlist" id="divisionlist" 
											name="divisionlist1"  
												cssClass="select2_category form-control"  
												 onchange="getDepot(this.value)" headerKey="0" headerValue="--select--"></s:select>  
												 
										</div>
									</div>
								
                             
								<div class="form-group">
									<label class="col-md-3 control-label">Depot<font
										color="red">*</font></label>
									<div class="col-md-3">
										<select list="depotlist" id="depotlist1" class="select2_category form-control" name="depotlist1" > 
											<option value="0">--Select--</option>
 										</select> 
									</div>
 								</div> 
 								

 								
             
                                     						
 			<%-- 					  <div class="form-group" id="section">
									<label class="col-md-3 control-label">Section<font
										color="red"></font></label>
									<div class="col-md-3">
						                <select  id="days"  class="select2_category form-control" name="days" >
						                <option value="0">--Select--</option>
							              <option value="statistics">statistics</option>
							              <option value="traffic">traffic</option>
							        
							           </select>
							      
					</div>
											</div> --%>
							 <div class="form-group">
							  <label class="control-label col-md-3">Date:</label>
								<div class="col-md-3">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" data-date-end-date="-2d">
								<input id="startdate" class="form-control" type="text" readonly="" size="16" name="rateMaster.effectiveStartDate"
								value="<s:property value='rateMaster.effectiveStartDate' />"
								>
								<span class="input-group-btn">
								<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
								</button>
								</span>
			<%-- 					<script>
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
						  
				<%-- 			 <div class="form-group">
							  <label class="control-label col-md-3">To Date:</label>
								<div class="col-md-3">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years"  data-date-end-date="-2d"> 
								<input id="enddate" class="form-control" type="text" readonly="" size="16" name="rateMaster.effectiveStartDate"
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
                                        var dtval=document.getElementById('enddate').value;	
                                        
                                        if(dtval==''){
                                        $('#enddate').val(curr_date);
                                        }
								</script>
								</div>
								</div></div> --%>
							
								
						
						<!-- end -->
							
							
						 <script type="text/javascript"> 
 	                     function tabletoExcel(btnExport){
 	                    
 	                    	// var divElements = document.getElementById("header").innerHTML;
 	                      var  divElements1 = document.getElementById("ticketconsuptionid").innerHTML;
 	                    // divElements1=divElements1.replace("header","");
 	                    divElements =divElements1;
 	                        var noOfTableExist = 0;
 	                        try{
 	                    		$("#ticketconsuptionid table").each(function(){
 	                    			noOfTableExist++;
 	                    		});
 	                    		
 	                    		console.log("Total no of tables : " + noOfTableExist);
 	                    		/* If two table exist  then remove the last table on print click*/
 	                    /* 		if(noOfTableExist >= 2){
 	                    			divElements = divElements.substring(0, divElements.indexOf("</table>") + 8) + "</div></div>";
 	                    			//divElements=divElements.remove("scroll");
 	                    			
 	                    		} */
 	                    	}catch(err){
 	                    	    console.log("ExceptionCaught : " + err);
 	                    	}

 	                    	 
 	                   var downloadLink = document.createElement("a"); 
 	                  downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(divElements); 
 	                  downloadLink.download = "ST-1 Report.xls"; 
 	                  document.body.appendChild(downloadLink); 
 	                  downloadLink.click(); 
 	                  document.body.removeChild(downloadLink);
 	         //        $("#header1").hide();
 	                  }</script>	
					
							
					 <div class="form-group">
					 <label class="control-label col-md-3"></label>
                     <div class="col-md-3" id="">
                     </div>
                     </div>
                     </div>
                     </form>
					
					 <div align='center'>
					<button type="submit" class="btn blue-chambray" onClick="getDepot1()">Submit</button> 
					<button type="button" id="btnExport" class="btn blue-chambray" onclick="tabletoExcel();"> EXPORT </button>
                   <span><input type='button' class='btn blue-chambray' id='button1' onclick='printDiv()' value='Print' /></span>&nbsp;<span>
<%-- 					 <input type='button' class='btn green' id='button1' name='rawprint' onclick='rawPrint()' value='RawPrint' /></span></div> --%>
					<!-- END EXAMPLE TABLE PORTLET-->
					<div id="ticketconsuptionid" ></div>
				</div>
			</div>
	

			<!-- END PAGE CONTENT-->
		</div>	
	</div>
	</div>
	</div>
	</div>
	</div>
		


<%-- 
	 <div style="display: none;" id="mymodal1" class="modal fade"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
		aria-hidden="true">
		<div class="modal-dialog" id="mod21">
			<div class="modal-content" style="width: 600px; height: 400px; overflow-y:scroll" 
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
									<div class="form-group">
					 <div id="ticketconsuptionid1" ></div>
						</div></div>			</div>
								</div>
							</div>
						</div>
						</p>
					</div>
				</div>
			</div> --%>
	</head>
	<style>
	
 	table {
    border-collapse: collapse;
    margin-bottom: 3em;
    width: 100%;
    background: #fff;
} 
table  tbody tr td, tbody tr td,
table  thead tr th, thead tr th {
    padding: 0.75em 1.5em;
    text-align: left;
    border:2px solid #
}

table thead tr th, thead tr th {
    background-color: #31bc86;
    font-weight: bold;
    color: black;

} 

	
	</style>
	</html>

	
	
	
