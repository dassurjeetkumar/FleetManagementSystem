  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
 
<script>



function getData(){
	
	
	var dd1=$("#startdate").val();
	var depot=$("#depotlist1").val();
	var type=$("#type").val();
//alert(dd1);

	 $.ajax({
	        
            type: "post",
            url: '<%=request.getContextPath()%>/AjaxetmDeviceHistoryReport.action?startdate='+dd1+'&depotid='+depot+'&type='+type,
            success: function(result) {
            	$('#pageLoader').hide();
                document.getElementById('ticketconsuptionid').innerHTML=result;
                fixHeader();
            }
        });

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
var i=0;
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
//					getVehicle("");
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
<!-- 						<div class="modal-footer"> -->
<!-- 							<button type="button" class="btn blue">Save changes</button> -->
<!-- 							<button type="button" class="btn default" data-dismiss="modal">Close</button> -->
<!-- 						</div> -->
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
								<i class="fa fa-globe"></i>Etm Device History Report
							</div>
						</div>
						
						<div class="portlet-body">
						


							<!-- 						start -->
                         <form action="" method="post" class="form-horizontal">
                             	<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Division<font
											color="red">*</font></label>
										<div class="col-md-4">
											<s:select list="divisionlist" id="divisionlist1" 
											name="divisionlist1"  
												cssClass="select2_category form-control"  
												 onchange="getDepot(this.value)" headerKey="0" headerValue="division"></s:select>  
												 
										</div>
									</div>
								</div>

								
<!-- 								<div class="form-group"> -->
<!-- 									<label class="col-md-3 control-label">Depot<font -->
<!-- 										color="red">*</font></label> -->
<!-- 									<div class="col-md-4"> -->
<%-- 										<s:select list="depotlist1" id="depotlist" class="select2_category form-control" name="depotlist" --%>
<%--  											headerKey="0" headerValue="--select--">  --%>
<!-- 											<option value="0">--select--</option> -->
<%--  										</s:select>  --%>
<!-- 									</div> -->
<!--  								</div>  -->
 								
 									<div class="form-group">
									<label class="col-md-3 control-label">Depot<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select id="depotlist1" class="select2_category form-control" name="depotlist1"
 											> 
											<option value="0">Depot</option>
 										</select> 
									</div>
 								</div> 
 								
 								<div class="form-group">
									<label class="col-md-3 control-label">Type<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select id="type" class="select2_category form-control" name="type"
 											> 
											<option value="0">Resolved</option>
											<option value="1">Unresolved</option>
 										</select> 
									</div>
 								</div> 
 					
								<div class="form-group">
							  <td><label class="col-md-3 control-label"> Month & Year:<font
										color="red">*</font></label>
								<div class="col-md-4">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="mm-yyyy"
															data-date-viewmode="years" ><!-- data-date-start-date="+0d"> -->
								<input id="startdate" class="form-control" type="text" readonly="" size="16" name="startdate"
								value="<s:property value='startdate' />"
								>
								<span class="input-group-btn">
								<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
								</button>
								</span>
								<script>
													var curr_date = new Date().toJSON().slice(0,10);
													curr_date = curr_date.split("-");
													curr_date = curr_date[1]+ "-"+ curr_date[0];
													var dtval = document.getElementById('startdate').value;
													if (dtval == '') {
														$('#startdate').val(curr_date);
													}
// 													getDepot();
												</script>
								</div>
								</div>
								</div>
								
							
						<!-- end -->
						
<%-- 						 <script type="text/javascript">  --%>
<!-- //  	                     function tabletoExcel(btnExport){ -->
<!-- //  	                     var htmltable= document.getElementById('btnExport'); -->
<!-- //  	                   //  var html = document.getElementById("header").innerHTML; -->
<!-- //  	                    var html = document.getElementById("ticketconsuptionid").innerHTML; -->
<!-- //                        window.open('data:application/vnd.ms-excel,' + encodeURIComponent(html));  -->
<!-- // 	               	   }  -->
<%-- 	                 </script>	 --%>
						
	<script type="text/javascript">               	
    function tabletoExcel(btnExport){     
//  	var htmltable= document.getElementById('btnExport');   
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
    downloadLink.download = "ETM Status Report.xls";
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
}</script>
  			
  			
  		
					<div class="form-group">
					 <label class="control-label col-md-3"></label>
                     <div class="col-md-3" id="">
                     <button type="button" class="btn green" onClick="getData()">Submit</button>
                     <span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span>&nbsp;<span>
                     </div>
                     </div>
<!--                      </div> -->
                     </form>
<!--                       <div align='center'> -->
<!-- 					<button type="submit" class="btn green" onClick="getDepot1()">Submit</button> -->
<!-- 					</div> -->
					<div id="ticketconsuptionid"></div>
					
					 <div align='center'>
<!-- 					<button type="submit" class="btn green" onClick="getDepot1()">Submit</button> -->
<!-- 					<button type="button" id="btnExport" class="btn green" onclick="tabletoExcel();"> EXPORT </button> -->
<!-- 					<button type="button" id="btnExport" class="btn green" onclick="rawPrint();"> RawPrint </button> -->
                      
<%-- 					<span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span>&nbsp;<span> --%>
<%-- 					 <input type='button' class='btn green' id='button1' name='rawprint' onclick='rawPrint()' value='RawPrint' /></span></div> --%>

			<!-- END PAGE CONTENT-->
		</div>
		<div id="dailyticketraw">
			</div>
	</div>
	
	
	
	
