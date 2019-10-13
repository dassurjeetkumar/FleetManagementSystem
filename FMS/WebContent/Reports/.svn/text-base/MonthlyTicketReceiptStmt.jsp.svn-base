  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
 
<script>

function getDepotList(orgId){

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


function getDepot(){
	
		var dd1=$("#startdate").val();
		var depotId=$("#depotlist1").val();
		var divId=$("#divisionlist").val();
	//	var scheduleNo=$("#scheduleId").val();
		var dd2=$("#endate").val();
		var var1=$("#startdate").val();
		var1=var1.split("-");
		var1=var1[2]+"-"+var1[1]+"-"+var1[0];
		var var2=$("#endate").val();
		var2=var2.split("-");
		var2=var2[2]+"-"+var2[1]+"-"+var2[0];
	 
	 if (var1 <= var2){
		 $('#pageLoader').show();

        $.ajax({
        
            type: "post",
            url: '<%=request.getContextPath()%>/AjaxmonthlyTicketReceiptstmt.action?enddate='+dd2+'&startdate='+dd1+'&depotlist1='+depotId+'&divisionlist1='+divId,
            success: function(result) {
            	$('#pageLoader').hide();
//             	$('#scheduleDetail').hide();
                document.getElementById('ticketconsuptionid').innerHTML=result;
            	$('#scheduleDetail').show();
                fixHeader();
            }
        });
   
	 }else{
			bootbox.alert("To Date Should Be greater than from From Date");
	 $('#pageLoader').hide();
	 }

	
}
</script>

<script>

 
function exportToExcel(btnExport){   
   var htmltable = document.getElementById("ticketconsuptionid");
   var html = htmltable.outerHTML;
   var noOfTableExist = 0;
   try{
		$("#ticketconsuptionid table").each(function(){
			noOfTableExist++;
		});
		
		/* If two table exist  then remove the last table on print click*/
		if(noOfTableExist >= 2){
			html = html.substring(0, html.indexOf("</table>") + 8) + "</div></div>";
		}
	}catch(err){
	    console.log("ExceptionCaught : " + err);
	}
   
   var downloadLink = document.createElement("a");
   downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
   downloadLink.download = "Monthly Ticket Receipt Stmt Report.xls";
   document.body.appendChild(downloadLink);
   downloadLink.click();
   document.body.removeChild(downloadLink);
 }

function printDiv() {     
	$('#scheduleDetail').show();

	var headerVal=" Monthly Ticket Receipt Stmt Report ";
    var  divElements = headerVal+document.getElementById("ticketconsuptionid").innerHTML;
    divElements += document.getElementById("scheduleDetail").innerHTML;

    var mywindow = window.open("<%=request.getContextPath()%>/Print.jsp");

		mywindow.onload = function() {
			mywindow.document.body.innerHTML = divElements;
			mywindow.document.body.innerHTML = divElements;
			mywindow.print();
			mywindow.close();
		}
		$('#scheduleDetail').show();
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
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			
			
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>Monthly Ticket Receipt Statement
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
										<div class="col-md-3">
											<s:select list="divisionlist" id="divisionlist" 
											name="org_chart_id"  
												cssClass="select2_category form-control"  
												 onchange="getDepotList(this.value)" headerKey="0" headerValue="--Select--"></s:select>  
												 
										</div>
									</div>
								
                               <div class="form-group">
									<label class="col-md-3 control-label">Depot<font
										color="red">*</font></label>
									<div class="col-md-3">
										<select list="depotlist" id="depotlist1" class="select2_category form-control" name="depotlist1"
 											onChange="getSchedule(this.value)"> 
											<option value="0">--Select--</option>
 										</select> 
									</div>
 								</div> 
 								
  								
<!-- 							<div class="form-group"> -->
							  <label class="control-label col-md-3">From:</label>
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
								</div>
<!-- 								</div> -->
								
								<div class="form-group">
							  <label class="control-label col-md-1">To:</label>
								<div class="col-md-3">
								
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" ><!-- data-date-start-date="+0d"> -->
								<input id="endate" class="form-control" type="text"  size="16" name="effectiveStartDate"
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
                                 var dtval=document.getElementById('endate').value;	 
                                
                                 if(dtval==''){
                              $('#endate').val(curr_date); 
                            
                                } 
								</script>
								</div>
								</div></div>
 							
						<!-- end -->
						
						<div class="form-group">
					 <label class="control-label col-md-3"></label>
                     <div class="col-md-3" id="">
                     </div>
                     </div>
                     </div>
                     </form>
                      <div align='center'>
					<button type="submit" class="btn green" onClick="getDepot()">Submit</button>
					<span><input type='button' class='btn green' id='button1' onclick="printDiv()" value='Print' /></span>&nbsp;
					<input type='button' class='btn green' id='button1' onclick="exportToExcel()" value='Export' />
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
				<div>
					<div id="ticketconsuptionid"></div>
					<div id="scheduleDetail" style="display: none;"></div>
					</div>
			</div>
			
			<!-- END PAGE CONTENT-->
		</div>
	</div>
	</div>
	</div>
	</div>
	</head>
	
	
	
	
