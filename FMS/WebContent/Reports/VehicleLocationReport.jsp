
  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
 
<script>


function printDiv() {     
	  var divElements = document.getElementById("header").innerHTML;
	
// 	  divElements +="Vehicle Device No." + $('#ctoken').val();
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

	    var mywindow = window.open("<%=request.getContextPath()%>/Print.jsp");
	    
	    mywindow.onload = function() {
	    mywindow.document.body.innerHTML=divElements;
	    mywindow.document.body.innerHTML=divElements;
	    mywindow.print();
	    mywindow.close();
	    }
    
}



function getDepot(orgId){
	// $('#select2-chosen-2').html("Select");
	// $('#select2-chosen-3').html("Select");
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 var val=document.getElementById('divisionlist').value;
			 if(val!=0) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/getDepotData?val=' + val,
				success : function(result) {
					document.getElementById('depotlist1').innerHTML = result;
					
				}
			});
		}

	}

function getVehicle(depotID)
{
	//$('#select2-chosen-3').html("Select");
	var val=document.getElementById('depotlist1').value;
	alert(val);
	 if(val!=0) {
		// $("#msg").html("Please wait...");
   	$.ajax({
	   type: "GET",
       url: '<%=request.getContextPath()%>/getLiveVehicles?val='+val,
       success: function(result) {
    	 //  $("#msg").html("");
    	   document.getElementById('ctoken').innerHTML=result;
       }
   });
}
}

function getDepot1(){

		var dd1=$("#startdate").val();
// 		alert(dd1);
		var dd2=$("#enddate").val();
 		var vehno=$("#ctoken").val();
 		var depotlist1=$("#depotlist1").val();
		var div=$('#divisionlist').val();
		
		 var date1= moment(dd1).format('DD-MM-YYYY-HH-mm');
		 var date2= moment(dd2).format('DD-MM-YYYY-HH-mm');
		 var data1=date1.split("-");
		 var ddd1=data1[0];
		 var mm1=data1[1];
		 var yy1=data1[2];
		 var hh1=data1[3];
		 var min1=data1[4];
		 var d1 = new Date(yy1,mm1,ddd1,hh1,min1,0,0);  //Date object creation in JS
		 
		 var data2=date2.split("-");
		 var ddd2=data2[0];
		 var mm2=data2[1];
		 var yy2=data2[2];
		 var hh2=data2[3];
		 var min2=data2[4];
		 var d2 = new Date(yy2,mm2,ddd2,hh2,min2,0,0); 
		 
		 var diff = d2.getTime() - d1.getTime();     // in milli second
		 var hDiff = diff / 3600 / 1000;              // in hours
		 if(d1>d2){
				bootbox.alert("From Time should not be greater than to To Time");
				return false;
			}
		 if( div == "0" )
	        {
	            bootbox.alert("Please select division");
	           return false;
	        }
	        if( depotlist1 == "0" )
	        {
	            bootbox.alert("Please select depot");
	           return false;
	        }
	        if( vehno == "0" )
	        {
	            bootbox.alert("Please select Vehicle Number");
	           return false;
	        }
	        if(hDiff>24)
	        	{
	        	bootbox.alert("Difference should not be greater than 24 Hours");
	        	}
	       
	        $('#pageLoader').show();
	        $.ajax({
	            
	            type: "post",
	            url: '<%=request.getContextPath()%>/AjaxVehicleLocation.action?startdate='+dd1+'&enddate='+dd2+'&ctoken='+vehno+'&depotlist1='+depotlist1+'&divisionlist1='+div,
	            success: function(result) {
	            	$('#pageLoader').hide();
	                document.getElementById('ticketconsuptionid').innerHTML=result;
	                fixHeader();
	                
	            }
	        });
	        
	
}
</script>

<script>
		

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
			<div id="header" >
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>Vehicle Location Report
							</div>
						</div></div></div></div>
						
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
											name="org_chart_id"  
												cssClass="select2_category form-control"  
												 onchange="getDepot(this.value)" headerKey="0" headerValue="--select--"></s:select>  
												 
										</div>
									</div>
								
                               <div class="form-group">
									<label class="col-md-3 control-label">Depot<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select list="depotlist" id="depotlist1" class="select2_category form-control" name="depotlist1"
 											onchange="getVehicle(this.value)" onselect="getVehicle(this.value)" > 
											<option value="0">--select--</option>
 										</select> 
									</div>
									<SCRIPT type="text/javascript">
									//getVehicle("");
									</SCRIPT>
 								</div> 

 								<div class="form-group">
									<label class="control-label col-md-3"> Vehicle No. </label>
									<div class="col-md-4">
<%-- 									<s:select list="vehiclelistname" id="ctoken" name="ctoken"  cssClass="select2_category form-control " > --%>
<!-- 											<option value=0 id='0'></option> -->
<%-- 										</s:select> --%>
									<select  id="ctoken" class="select2_category form-control" name="ctoken"
 											> 
											<option value="0">--select--</option>
 										</select> 
									
									</div>
								</div>
								
							<div class="form-group">
							  <label class="control-label col-md-3">From Date:</label>
								<div class="col-md-4">
					
						<div class="input-group date form_datetime">
							<input type="text" size="16" readonly name="startdate"
								id="startdate" class="form-control" value='<s:property value="startdate"/>'> <span
								class="input-group-btn">
								<button class="btn default date-set" type="button">
									<i class="fa fa-calendar"></i>
								</button>
							</span>
						</div>
					</div>
								</div>
 							<div class="form-group">
							<label class="control-label col-md-3">To Date:</label> 
 								<div class="col-md-4">
					<div class="input-group date form_datetime">
						<input type="text" size="16" readonly name="enddate" id="enddate" value='<s:property value="enddate"/>'
							class="form-control"> <span
							class="input-group-btn">
							<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
							</button>
						</span>
					</div>
					</div>
							
						</div> 
						<!-- end -->
						
						<div class="form-group">
					 <label class="control-label col-md-3"></label>
                     <div class="col-md-3" id="">
                     </div>
                     </div>
                     </div></div>
                     </form>
					
					 <script type="text/javascript">    
					 function tabletoExcel(btnExport){   
						 
					    var htmltable = document.getElementById("ticketconsuptionid");
					    var html = htmltable.outerHTML;
					    
						var noOfTableExist = 0;
						try {
							$("#ticketconsuptionid table").each(function() {
								noOfTableExist++;
							});

							console.log("Total no of tables : " + noOfTableExist);
							/* If two table exist  then remove the last table on print click (for header fixed)*/
							if (noOfTableExist >= 2) {
								html = html.substring(0, html
										.indexOf("</table>") + 8)
										+ "</div></div>";
							}
						} catch (err) {
							console.log("ExceptionCaught : " + err);
						}
					    
					    var downloadLink = document.createElement("a");
					    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
					    downloadLink.download = "Vehicle Location Report.xls";
					    document.body.appendChild(downloadLink);
					    downloadLink.click();
					    document.body.removeChild(downloadLink);
					  }
					
     	                </script>		
					
					 <div align='center'>
					<button type="submit" class="btn green" onClick="getDepot1()">Submit</button>
					
					<span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span>&nbsp;
								<button type="button" class="btn green" id="btnExport" filename= "report" onclick="tabletoExcel();"> EXPORT </button>
                                </div>
<%-- 					 <input type='button' class='btn green' id='button1' name='rawprint' onclick='rawPrint()' value='RawPrint' /></span></div> --%>
					<!-- END EXAMPLE TABLE PORTLET-->
				<div id="ticketconsuptionid"></div>
				</div>
				
	
			
			<!-- END PAGE CONTENT-->
		</div>
	</div>
	
	
	
	



