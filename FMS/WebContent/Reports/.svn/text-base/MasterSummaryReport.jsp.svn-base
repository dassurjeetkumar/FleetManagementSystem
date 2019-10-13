  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
 
<script>

function getvehicle(orgId){
	

	 $('#select2-chosen-2').html("Select");
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 var val=document.getElementById('depotlist1').value;
			 if(val!=0) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/getVehicle?val=' + val,
				success : function(result) {
					document.getElementById('vehiclelist').innerHTML = result;
				}
			});
		}

	}
	
function getShowVehicleCause()
{
	  var type=$("#type").val();
	  if(type==1){
		  $(causeList).show();	
		  $(desgList).hide();
	  }else if(type == 2 ){
		  $(causeList).hide();	  
		  $(desgList).hide();	  
	  }else if(type ==3)	{
		  $(causeList).hide();	  
		  $(desgList).show();	
	  }else if(type ==4){
		  $(causeList).show();	
		  $(desgList).hide();
	  }
	
	}



<%--  function getDepot(orgId){
	// $('#select2-chosen-2').html("Select");
	// $('#select2-chosen-3').html("Select");
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

	}  --%>
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

function getDataOnClick(){
	
	    var type=$("#type").val();
 		var depotid=$("#depotlist1").val();
 
var div=$("#divisionlist").val();
		//var divId=document.getElementById('divisionlist1');
		//alert(div);
/* 
		 if( div == "0" )
	        {
	            bootbox.alert("Please select Division");
	           return false;
	        }
	     if( depotlist1 == "0" )
	        {
	            bootbox.alert("Please select Depot");
	           return false;
	        } */
	        if(type == 0){
	        	bootbox.alert("Please select Depot Master")
	        }
	       
	        if(type==1){
	        	 $('#pageLoader').show();
	        	$(cause).show();
	        	 var cause=$("#cause").val();
	        	// alert(cause);
        $.ajax({
        
            type: "post",
            url: '<%=request.getContextPath()%>/getSummaryDataForVehicle.action?depot='+depotid+'&divison='+div+'+&cause='+cause+'+&type='+type,
            success: function(result) {
            	$('#pageLoader').hide();
                document.getElementById('ticketconsuptionid').innerHTML=result;
                fixHeader();
            }
        });
	        }
	        if(type==4){
	        	 $('#pageLoader').show();
	        	$(cause).show();
	        	 var cause=$("#cause").val();
	        	// alert(cause);
       $.ajax({
       
           type: "post",
           url: '<%=request.getContextPath()%>/getSummaryDataForVehicle.action?depot='+depotid+'&divison='+div+'+&cause='+cause+'+&type='+type,
           success: function(result) {
           	$('#pageLoader').hide();
               document.getElementById('ticketconsuptionid').innerHTML=result;
               fixHeader();
           }
       });
	        }
	        if(type==2){
	        	 $('#pageLoader').show();
// 	        	$(causeList).hide();
             
	        	 $.ajax({
	        	        
	                 type: "post",
	                 url: '<%=request.getContextPath()%>/getSummaryDataForSchedule.action?depotlist1='+depotid+'&divisionlist1='+div,
	                 success: function(result) {
	                 	$('#pageLoader').hide();
	                     document.getElementById('ticketconsuptionid').innerHTML=result;
	                     fixHeader();
	                 }
	             });
	        }
	     
	        if(type==3){
	        	 $('#pageLoader').show();
	        	  var desg=$("#desg").val();
	               console.log(desg);
	        	$(causeList).hide();
	        	 $.ajax({
	        	        
	                 type: "post",
	                 url: '<%=request.getContextPath()%>/getSummaryDataForEmployee.action?depotlist1='+depotid+'&divisionlist1='+div+'&desg='+desg,
	                 success: function(result) {
	                 	$('#pageLoader').hide();
	                     document.getElementById('ticketconsuptionid').innerHTML=result;
	                     fixHeader();
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
	try {
		$("#ticketconsuptionid table").each(function() {
			noOfTableExist++;
		});

		console.log("Total no of tables : " + noOfTableExist);
		/* If two table exist  then remove the last table on print click (for header fixed)*/
		if (noOfTableExist >= 2) {
			divElements = divElements.substring(0, divElements
					.indexOf("</table>") + 8)
					+ "</div></div>";
		}
	} catch (err) {
		console.log("ExceptionCaught : " + err);
	}


    var mywindow = window.open("<%=request.getContextPath()%>/Print1.jsp");
   
    mywindow.onload = function() {
    mywindow.document.body.innerHTML=divElements;
    mywindow.document.body.innerHTML=divElements;
    mywindow.print();
    mywindow.close();
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
			
			
			
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>Master Summary Report
							</div>
						</div>
						
						<div class="portlet-body">
<!-- 						<div class="table-scrollable"> -->


							<!-- 						start -->

                     <form action="" method="post" class="form-horizontal">
                        <div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Division<font
											color="red">*</font></label>
										<div class="col-md-4">
											<s:select list="divisionlist" id="divisionlist" 
											name="division"  
												cssClass="select2_category form-control"  
												 onchange="getDepot(this.value)" headerKey="0" headerValue="--All--"></s:select>  
												 
										</div>
										
									</div>
								
                               <div class="form-group">
									<label class="col-md-3 control-label">Depot<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select list="depotlist" id="depotlist1" class="select2_category form-control" name="depotlist1"> 
											<option value="0">--All--</option>
 										</select> 
									</div>
 								</div> 
 								<div class="form-group">
									<label class="col-md-3 control-label">Depot Master <font
										color="red">*</font></label>
									<div class="col-md-4">
										<select id="type" name="type" class="select2_category form-control" onClick="getShowVehicleCause()" >
											<option value="0">--Select--</option>
											<option value="1">Vehicle(device active)</option>
											<option value="4">Vehicle(device Inactive)</option>
											<option value="2">Schedule</option>
											<option value="3">Employee</option>
										</select>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-3 control-label"></label>
									<div class="col-md-4" id="causeList" style="display: none">
										<select id="cause" class="select2_category form-control" >
												<option value="0">-----Select Cause----</option>
							<option value="1">Normal</option>
							<option value="2">Scrap</option>
							<option value="3">Central Workshop</option>
							<option value="4">Depot Workshop</option>
							<option value="5">Breakdown</option>
							<option value="6">Accident</option>
										</select>
									</div>
								</div>
								
					
								<div class="form-group">
									<label class="col-md-3 control-label"></label>
									<div class="col-md-4" id="desgList" style="display: none">
										<select id="desg"  class="select2_category form-control" >
							<option value="0">--Select Designation--</option>
							<option value="1">Driver</option>
							<option value="2">Conductor</option>
							<option value="3">DriverConductor</option>
										</select>
									</div>
								</div>
                          
							
						<!-- end -->
						
						<div class="form-group">
					 <label class="control-label col-md-3"></label>
                     <div class="col-md-3" id="">
                     </div>
                     </div>
                     </div>
                     </form>
				
					 <div align='center'>
					<button type="submit" class="btn green" onClick="getDataOnClick()">Submit</button>
					<span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span>&nbsp;
					<button type="submit" class="btn green" onClick="tabletoExcel()">Export</button>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
					<div id="ticketconsuptionid"></div>
			</div>
			
			<!-- END PAGE CONTENT-->
		</div>
	</div>
	
	<script type="text/javascript">
      function tabletoExcel(btnExport){     
            
		var divElements = document.getElementById("ticketconsuptionid").innerHTML;
			var noOfTableExist = 0;
			try {
				$("#ticketconsuptionid table").each(function() {
					noOfTableExist++;
				});

				console.log("Total no of tables : " + noOfTableExist);
				/* If two table exist  then remove the last table on print click (for header fixed)*/
				if (noOfTableExist >= 2) {
					divElements = divElements.substring(0, divElements
							.indexOf("</table>") + 8)
							+ "</div></div>";
				}
			} catch (err) {
				console.log("ExceptionCaught : " + err);
			}

			var downloadLink = document.createElement("a");
			downloadLink.href = 'data:application/vnd.ms-excel,'
					+ encodeURIComponent(divElements);
			downloadLink.download = "Master Summary Report.xls";
			document.body.appendChild(downloadLink);
			downloadLink.click();
			document.body.removeChild(downloadLink);
		}
	</script>