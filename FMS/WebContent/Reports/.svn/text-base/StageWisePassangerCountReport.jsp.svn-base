  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
<script>

function getWaybill(){
	 $('#select2-chosen-2').html("Select");
	 $('#select2-chosen-3').html("Select");
	 $('#select2-chosen-4').html("Select");
	 $('#select2-chosen-5').html("Select");
	 $('#select2-chosen-6').html("Select");
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 var val=document.getElementById('divisionlist').value;
		 var dd1=$("#startdate").val();
			 if(val!=0) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/getWaybill?startdate='+dd1+'&val=' + val,
				success : function(result) {
					document.getElementById('depotlist1').innerHTML = result;
					
				}
			});
		}

	}
	
function getShiftNoForStage(){
	$('#select2-chosen-3').html("Select");
	$('#select2-chosen-4').html("Select");
	 $('#select2-chosen-5').html("Select");
	 $('#select2-chosen-6').html("Select");
	    var depot=document.getElementById('divisionlist').value;
		 var val=document.getElementById('depotlist1').value;
		// alert(depot);
		// alert(val);
			 if(val!=0) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/getShiftNoForStage?depot='+depot+'&val=' + val,
				success : function(result) {
					document.getElementById('shiftlist1').innerHTML = result;
					
				}
			});
		}

	}
function getTripNoForStage(){
	$('#select2-chosen-4').html("Select");
	 $('#select2-chosen-5').html("Select");
	 $('#select2-chosen-6').html("Select");
	var depot=document.getElementById('divisionlist').value;
    var shift=document.getElementById('shiftlist1').value;
	 var val=document.getElementById('depotlist1').value;
	// alert(depot);
	// alert(val);
		 if(val!=0) {
        $.ajax({
            type: "post",
            url: '<%=request.getContextPath()%>/getTripNoForStage?shift='+shift+'&val=' + val+'&depot=' + depot,
			success : function(result) {
				document.getElementById('triplist1').innerHTML = result;
				
			}
		});
	}

}


function getStageNo(){
	 $('#select2-chosen-5').html("Select");
	 $('#select2-chosen-6').html("Select");
	var depot=document.getElementById('divisionlist').value;
	 var waybill=document.getElementById('depotlist1').value;
    var shift=document.getElementById('shiftlist1').value;
	 var val=document.getElementById('triplist1').value;
	 //alert(depot);
	// alert(val);
		 if(val!=0) {
        $.ajax({
            type: "post",
            url: '<%=request.getContextPath()%>/getStageNo?shift='+shift+'&val=' + val+'&depot=' + depot+'&waybill=' + waybill,
			success : function(result) {
				document.getElementById('stagelist1').innerHTML = result;
				
			}
		});
	}

}

function getDepot(){
	
	
// 		var dd1=$("#startdate").val();
// 		var dd2=$("#endate").val();
// 		var depotlist1=$("#depotlist1").val();
// 		var div=$('#divisionlist').val();
// 		var var1=$("#startdate").val();
// 		var1=var1.split("-");
// 		var1=var1[2]+"-"+var1[1]+"-"+var1[0];
// 		var var2=$("#endate").val();
// 		var2=var2.split("-");
// 		var2=var2[2]+"-"+var2[1]+"-"+var2[0];
// 		//alert(var1+""+var2);
		
// 		 var d1 = Date.parse(var1);
// //		 d = new Date();
		
// 	var d3=Date.parse(var2);

// 		if (d1 <= d3){
	var depot=document.getElementById('divisionlist').value;
	 var waybill=document.getElementById('depotlist1').value;
    var shift=document.getElementById('shiftlist1').value;
	 var val=document.getElementById('triplist1').value;
	 var routeorder=document.getElementById('stagelist1').value;
        $.ajax({
        
            type: "get",
            url: '<%=request.getContextPath()%>/StageWisePassangerCountSubmitAction.action?shift='+shift+'&val=' + val+'&depot=' + depot+'&waybill=' + waybill+'&routeorder=' + routeorder,
            success: function(result) {
            	$('#pageLoader').hide();
                document.getElementById('operatedid').innerHTML=result;
               // fixHeader();
            }
        });
// 		}else{
 			
 			
//  			alert("Till Date Should Be greater Than Start Date");
//  			$('#pageLoader').hide();
//  			 document.getElementById('operatedid').innerHTML="";
//  			fixHeader();
//  		}
		fixHeader();
}
</script>

<script>
function jsFunction(Sctype,shftype){
	var dd1=$("#startdate").val();
	var dd2=$("#endate").val();
	var depotlist1=$("#depotlist1").val();
	var div=$('#divisionlist').val();
	var scheduletyp=Sctype;
	var shifttype=shftype;
	 $.ajax({
	        
         type: "get",
         url: '<%=request.getContextPath()%>/TotalSchedules.action?startdate='+dd1+'&enddate='+dd2+'&scheduletyp='+scheduletyp+'&shifttype='+shifttype+'&depotlist1='+depotlist1+'&division='+div,
         success: function(result) {
         	$('#pageLoader').hide();
             document.getElementById('operatedid1').innerHTML=result;
             fixedHeader();
         }
     });
	
}


</script>
<script>

function printDiv() {     
    
    //  document.getElementById("print").style.visibility='hidden';     
    var divElements = document.getElementById("header").innerHTML;
    divElements += document.getElementById("printid").innerHTML;
   

    var mywindow = window.open("<%=request.getContextPath()%>/Print.jsp");
   
    mywindow.onload = function() {
    mywindow.document.body.innerHTML=divElements;
    mywindow.document.body.innerHTML=divElements;
    mywindow.print();
    mywindow.close();
    }
            
}


function rawPrint1(){
	//alert("hdfdfdf");

	/* var htmlCode = "<applet archive='/doa/applet/IqPrint.jar' name='print' code='IqPrint' width=0 height=0><param name=httpUrl value='/doa/Ticketing/CashierReport.txt'><param name=printText value=''><param name=printDevice value='epson'><param name=printSubmitUrl value=''><param name=paginationRequired value='Y'><param name=directPrint value='Y'><param name=displayRequired value='N'></applet>";
	$("#resultset").html(htmlCode); */
        $.ajax({
          type: "get",
          url:"Ticketing/RawPrint.jsp",
          data:"OperatedAndNotOperated=new",
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
			
			
<!-- 			<div class="row"> -->
<!-- 				<div class="col-md-12"> -->
<!-- 					BEGIN PAGE TITLE & BREADCRUMB -->
<!-- 					<h3 class="page-title"> -->
<%-- 					Operated And Not Operated Schedules <small></small> --%>
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
								<i class="fa fa-globe"></i> StageWise  Passenger Count Report
							</div>
							
						</div>
						
						<div class="portlet-body">
						<div class="table-scrollable">
				             <form action="" method="post" class="form-horizontal">
                        <div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Depot<font
											color="red">*</font></label>
										<div class="col-md-4">
											<s:select list="divisionlist" id="divisionlist" 
											name="org_chart_id"  
												cssClass="select2_category form-control"  
												  headerKey="0" ></s:select>  
												 
										</div>
									</div>
								

 								
 								
                          
							<div class="form-group">
							  <label class="control-label col-md-3">Date:</label>
								<div class="col-md-3">
								
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" ><!-- data-date-start-date="+0d"> -->
								<input id="startdate" class="form-control" type="text"  size="16" name="effectiveStartDate"
								value="<s:property value='rateMaster.effectiveStartDate' />" onchange="getWaybill()"
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
									<label class="col-md-3 control-label">Waybill No<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select list="depotlist" id="depotlist1" class="select2_category form-control" name="depotlist1"
  											onchange="getShiftNoForStage()">  
											<option value="0">select</option>
  										</select>  
									</div>
 								</div> 
 									                               <div class="form-group">
									<label class="col-md-3 control-label">Shift Type<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select list="shiftlist" id="shiftlist1" class="select2_category form-control" name="shiftlist1"
  											onchange="getTripNoForStage()">  
											<option value="0">select</option>
  										</select>  
									</div>
 								</div> 
 									                               <div class="form-group">
									<label class="col-md-3 control-label">Trip No<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select list="triplist" id="triplist1" class="select2_category form-control" name="triplist1"
  											onchange="getStageNo()">  
											<option value="0">select</option>
  										</select>  
									</div>
 								</div> 
 										
 										 <div class="form-group">
									<label class="col-md-3 control-label">Stage<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select list="stagelist" id="stagelist1" class="select2_category form-control" name="stagelist1"
  											>  
											<option value="0">select</option>
  										</select>  
									</div>
 								</div> 	                              
 					
						
							
						
			 <script type="text/javascript"> 
 	                     function tabletoExcel(btnExport){
 	                     var htmltable= document.getElementById('btnExport');
 	                     var html = document.getElementById("header").innerHTML;
 	                    var html = document.getElementById("operatedid").innerHTML;
 	                   var downloadLink = document.createElement("a"); 
 	                  downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html); 
 	                  downloadLink.download = "Stagewise Passenger Count.xls"; 
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
					<button type="submit" class="btn green" onClick="getDepot()">Submit</button>
								 <button type="button" class="btn green" id="btnExport" filename= "report" onclick="tabletoExcel();"> EXPORT </button>
                                
					<!-- END EXAMPLE TABLE PORTLET-->

							</div>
						
					<div id="operatedid"></div>
					<div id="operatedid1"></div>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
			</div>
			<div id="dailyticketraw">
			</div>
			</div>
					</div>
			<!-- END PAGE CONTENT-->
		</div>
	</div>
	
	
	
	