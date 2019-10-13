  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
 
<script>

function getDepot(){
	
	var division=$("#divisionlist1").val();
	var depot=$("#depotlist1").val();
	var startdate = $("#startdate").val();
	if(startdate==null || startdate==""){
		bootbox.alert("Please select date");
		return false;
	}
		$('#pageLoader').show();
    $.ajax({
    
        type: "get",
        url: '<%=request.getContextPath()%>/AjaxcrewpositionReport.action?divisionlist1='+division+'&depotlist1='+depot+'&startdate='+startdate,
        success: function(result) {
        	$('#pageLoader').hide();
            document.getElementById('ticketconsuptionid').innerHTML=result;
            fixHeader();
        }
    });
	

	
}

function jsFunction(depot){
	//alert(startdate);
	var startdate=$("#startdate").val();
	//alert(startdate);
	var mywindow = window.open("<%=request.getContextPath()%>/getdriveconddetails.action?depotlist1="+depot+"&startdate="+startdate);
		
}
function jsFunction1(division){
	var startdate=$("#startdate").val();
	//alert(depot);
	//var depotlist1=$("#depotlist1").val();
	var mywindow = window.open("<%=request.getContextPath()%>/gettotaldriveconddetails.action?divisionlist1="+division+"&startdate="+startdate);
		
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

function getDepotList(orgId){
	 $('#select2-chosen-2').html("Select");
	 $('#select2-chosen-3').html("Select");
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 var val=document.getElementById('divisionlist1').value;
			 if(val!=0) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/getDepotForCrewPosition?val=' + val,
				success : function(result) {
					document.getElementById('depotlist1').innerHTML = result;
					
				}
			});
		}

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
			
			
<!-- 			<div class="row"> -->
<!-- 				<div class="col-md-12"> -->
<!-- 					BEGIN PAGE TITLE & BREADCRUMB -->
<!-- 					<h3 class="page-title"> -->
<%-- 					SCHEDULEWISE EARNINGS REPORT<small></small> --%>
<!-- 					</h3> -->
					
<!-- 					END PAGE TITLE & BREADCRUMB -->
<!-- 				</div> -->
<!-- 			</div> -->
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			
			
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>Crew Position Report 
							</div>
						</div>
						
						<div class="portlet-body">
					<div class="form-body">
						<div class="form-group">
										<label class="col-md-3 control-label">Division<font
											color="red">*</font></label>
										<div class="col-md-3">
											<s:select list="divisionlist" id="divisionlist1" 
											name="org_chart_id"  
												cssClass="select2_category form-control"  
												 onchange="getDepotList(this.value)" headerKey="0" headerValue="All"></s:select>  
												 
										</div>
									</div>
								
                               <div class="form-group">
									<label class="col-md-3 control-label">Depot<font
										color="red">*</font></label>
									<div class="col-md-3">
										<select list="depotlist" id="depotlist1" class="select2_category form-control" name="depotlist1"
 											> 
											<option value="0">All</option>
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
								
								</div>
								</div></div>
					
					
					
									
					
								<div class="form-group">
										<button type="button" class="btn green" onClick="getDepot()">Submit</button>
										<span><button type="button" class="btn green" id="btnExport" filename= "report" onclick="tabletoExcel();"> EXPORT </button></span>&nbsp;<span> 
                       
										</div>
							</div>
						</div>
					</div>
					<div id="ticketconsuptionid"></div>
<%-- 					 <div align='center'><span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span>&nbsp;<span><input type='button' class='btn green' id='button1' name='rawprint' onclick='rawPrint()' value='RawPrint' /></span></div> --%>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
				
				<script> 
				
   	        	function tabletoExcel(btnExport){      
   	           	var htmltable= document.getElementById('btnExport');    
   	           
   	         var divElements = document.getElementById("header").innerHTML;
   	         var divElements = document.getElementById("ticketconsuptionid").innerHTML;
   	      
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
   	            downloadLink.download = "Crew Position Report";
   	            document.body.appendChild(downloadLink); 
   	            downloadLink.click(); 
   	            document.body.removeChild(downloadLink);
  	         }
   	           	 </script>
				
			</div>
			<div id="dailyticketraw">
			</div>
			
			<!-- END PAGE CONTENT-->
		</div>
	</div>
	
	
	
	
