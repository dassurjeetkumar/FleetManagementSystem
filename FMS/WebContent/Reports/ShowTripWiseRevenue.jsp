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

	}

function getScheduleNo(orgId){
	// $('#select2-chosen-2').html("Select");
	// $('#select2-chosen-3').html("Select");
// 		alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 var val=document.getElementById('depotlist1').value;
			 if(val!=0) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/getScheduleId?val=' + val,
				success : function(result) {
					document.getElementById('ctoken').innerHTML = result;
					
				}
			});
		}

	}

function getDepot1(){
	
	
		var dd1=$("#startdate").val();
		var dd2=$("#endate").val();
 		var var1=$("#startdate").val();
 		var schdno=$("#ctoken").val();
 		var depotlist1=$("#depotlist1").val();
//  		var divisionlist1=$("#divisionlist1").val();
		var1=var1.split("-");
		var1=var1[2]+"-"+var1[1]+"-"+var1[0];
		var var2=$("#endate").val();
		var2=var2.split("-");
		var2=var2[2]+"-"+var2[1]+"-"+var2[0];
		//alert(var1+""+var2);
		var div=$('#divisionlist').val();
		 var d1 = Date.parse(var1);
// 		 alert("from"+var1);
		 var d3=Date.parse(var2);
// 		 alert("To"+var2);
		 if( div == "0" )
	        {
//	            alert( "Please select vehicle" );
	            bootbox.alert("Please select division");
	           return false;
	        }
	        if( depotlist1 == "0" )
	        {
	           //alert( "Please select vehicle" );
	            bootbox.alert("Please select depot");
	           return false;
	        }
	        if( schdno == "0" )
	        {
	          
	            bootbox.alert("Please select Schedule Number");
	           return false;
	        }
	       
// 	        return( true );
		 if (var1 <= var2){
        $.ajax({
        
            type: "post",
            url: '<%=request.getContextPath()%>/AjaxTripRevenueData.action?enddate='+dd2+'&startdate='+dd1+'&ctoken='+schdno+'&depotlist1='+depotlist1+'&divisionlist1='+div,
            success: function(result) {
            	$('#pageLoader').hide();
                document.getElementById('ticketconsuptionid').innerHTML=result;
                fixHeader();
            }
        });
		}else
 			
 			
 			alert("To Monthe Should Be greater Than From Month");
 			$('#pageLoader').hide();

	
}
</script>

<script>
		
function printDiv() {     
    
    //  document.getElementById("print").style.visibility='hidden';     
    var divElements = document.getElementById("header").innerHTML;
    divElements += document.getElementById("ticketconsuptionid").innerHTML;
    
//     var noOfTableExist = 0;
//     try{
// 		$("#ticketconsuptionid table").each(function(){
// 			noOfTableExist++;
// 		});
		
// 		console.log("Total no of tables : " + noOfTableExist);
// 		/* If two table exist  then remove the last table on print click*/
// 		if(noOfTableExist >= 2){
// 			divElements = divElements.substring(0, divElements.indexOf("</table>") + 8) + "</div></div>";
// 		}
// 	}catch(err){
// 	    console.log("ExceptionCaught : " + err);
// 	}

    var mywindow = window.open("<%=request.getContextPath()%>/Print1.jsp");
   
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
								<i class="fa fa-globe"></i>TripWise Revenue Report
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
 											onchange="getScheduleNo(this.value)"> 
											<option value="0">--select--</option>
 										</select> 
									</div>
 								</div> 
 								
<!--  								   <div class="form-group"> -->
<!-- 									<label class="col-md-3 control-label">Conductor Token No.<font -->
<!-- 										color="red">*</font></label> -->
<!-- 									<div class="col-md-4"> -->
									
<%-- 										<select list="cndtokenlist" id="ctoken" class="select2_category form-control" name="ctoken" headerKey="0" headerValue="--select--">  --%>
										
<%--  										</select>  --%>
<!-- 									</div> -->

 								<div class="form-group">
									<label class="control-label col-md-3"> Schedule No. </label>
									<div class="col-md-3">
									<select id="ctoken" name="ctoken" class=" form-control" >
											<option value=0 id='0'>Select</option>
										</select>
									</div>
								</div>
<!--  								  <div class="form-group"> -->
<!-- 									<label class="col-md-3 control-label">Conductor Name<font -->
<!-- 										color="red">*</font></label> -->
<!-- 									<div class="col-md-4"> -->
<%-- 										<select list="cname" id="cname1" class="select2_category form-control" name="cname1" > --%>
										
<%--  										</select>  --%>
<!-- 									</div> -->
<!--  								</div>   -->
 								
                          
							<div class="form-group">
							  <label class="control-label col-md-3">From Date:</label>
								<div class="col-md-3">
								
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" ><!-- data-date-start-date="+0d"> -->
								<input id="startdate" class="form-control" type="text"  size="16" name="effectiveStartDate"
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
                              $('#startdate').val(curr_date); 
                            
                                } 
								</script>
								</div>
								</div></div>
 							<div class="form-group">
							<label class="control-label col-md-3">To Date:</label> 
 								<div class="col-md-3"> 
 								<div class="input-group input-medium date date-picker" 
 															style="width: auto" data-date-format="dd-mm-yyyy" 
 															data-date-viewmode="years" ><!-- data-date-start-date="+0d"> --> 
								<input id="endate" class="form-control" type="text"  size="16" name="effectiveStartDate" 
 								value="<s:property value='rateMaster.effectiveStartDate' />" 	
 								>							
								<span class="input-group-btn"> 
								<button class="btn default date-set" type="button"> 
								<i class="fa fa-calendar"></i> 							</button> 
 								</span> 
								<script> 
								var curr_date=new Date().toJSON().slice(0,10);
                                curr_date=curr_date.split("-"); 
                                curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];  
                                 var dtval=document.getElementById('endate').value;	 
                                
                                 if(dtval==''){
                              $('#endate').val(curr_date); 
                            
                                } 
								</script> 
							</div> 
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
					<div id="ticketconsuptionid"></div>
					 <div align='center'>
					<button type="submit" class="btn green" onClick="getDepot1()">Submit</button>
					<span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span>&nbsp;
<%-- 					 <input type='button' class='btn green' id='button1' name='rawprint' onclick='rawPrint()' value='RawPrint' /></span></div> --%>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
			</div>
			
			<!-- END PAGE CONTENT-->
		</div>
	</div>
	
	
	
	
