<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
 var i=0;
 function getDepot(orgId){
	// $('#select2-chosen-2').html("Select");
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 var val=document.getElementById('divisionlist').value;
			 if(val!=0) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/getDepot?val=' + val,
				success : function(result) {
					document.getElementById('depotlist').innerHTML = result;
				}
			});
		}

	}
 function getvehicle(orgId){
	 $('#select2-chosen-2').html("Select");
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 var val=document.getElementById('depotlist').value;
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
 
 function getSchedule()
	{
		console.log('here');
		 var val=document.getElementById('depotlist').value;
		 var selectedDate=document.getElementById("selecteddate").value;
		 if(val!=0) {
			 if(counter == 0){
				 counter ++;
     $.ajax({
         type: "post",
         url: '<%=request.getContextPath()%>/getSchedule?val=' + val+'&selectedDate='+selectedDate,
			success : function(result) {
				counter =0;
				document.getElementById('schedulelist').innerHTML = result;
			}
		});
			 }
	}
	}
	
	function validateTripStatusReportFields(scheduleNo,depotNo,divisonNo,selDate)
	{
		 if(divisonNo==0)
		 {
			alert("Please Select Divison");
			return false;
		 }
		 if(depotNo==0)
		 {
			alert("Please Select Depot");
			return false;
		 }
		 if(selDate==0)
	     {
		   alert("Please Select Date");
		   return false;
	     }
	     if(scheduleNo==0)
	     {
		   alert("Please Select Schedule Number");
		   return false;
	     }
	     

	 return true;
	}
	function getScheduledTripStatusDataOnSubmit()
	{
		
		var depotNo=$("#depotlist").val();
		var divisonNo=$("#divisionlist").val();
		var startdate=$("#startdate").val();
		var enddate=$("#enddate").val();
		var scheduleNo=$("#schedulelist").val();
	
	//	alert("depotNo..."+depotNo+"....divisonNo...."+divisonNo+"....startdate...."+startdate+"....enddate...."+enddate);
//	 	 var d1 = Date.parse(startdate);
	
// 		var d3=Date.parse(enddate);
// 			if (d1 <= d3){
	        $.ajax({
	        
	            type: "post",
	            url: '<%=request.getContextPath()%>/AjaxEtmandonlinerevenuemismatch.action?startdate='+startdate+'&enddate='+enddate+'&depotNo='+depotNo+'&divisonNo='+divisonNo+'&scheduleNo='+scheduleNo,
	            success: function(result) {
	            	$('#pageLoader').hide();
	                document.getElementById('earlyarrivalsummary').innerHTML=result;
	            }
	        });
// 			}else{
	 			
	 			
// 	 			alert("End Date Should Be greater Than Start Date");
// 	 			$('#pageLoader').hide();
// 	 			 document.getElementById('earlyarrivalsummary').innerHTML="";
// 	 		}
		
	}
	
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

	
 </Script>

<title>Insert title here</title>
</head>
<body>
	<div class="page-content-wrapper">
		<div class="page-content">
	

			<div class="tab-content">

				<div class="row">
					<div class="col-md-12">
						<!-- BEGIN PAGE TITLE & BREADCRUMB-->
						<h3 class="page-title">
							ETM & ONLINE REVENUE MISMATCH REPORT<small></small>
						</h3>

						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portle-title">
							<div class="caption">
								<i class="fa fa-gift"></i>ETM & Online Revenue Mismatch Report
							</div>
							<!-- <div class="tools">
								<a href="javascript:;" class="collapse"> </a> 
								 <a href="javascript:;" class="reload"> </a> 
							</div> -->
						</div>

						<div class="portlet-body form">

							<div class="col-md-12" align="left" style="font-size: 1.1em">

								<%-- <span class="help-block" style="color: red; list-style: none"><s:actionerror /></span> --%>
								<span class="help-block" style="color: red; list-style: none"><s:actionmessage /></span>
							</div>
							
							<!-- BEGIN FORM-->
							<form action="" method="post" class="form-horizontal">

								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Division<font
											color="red">*</font></label>
										<div class="col-md-4">
											<s:select list="divisionlist" id="divisionlist" name="orgchart.org_chart_id"
												cssClass="form-control select2me" headerKey="0"
												headerValue="select" onchange="getDepot(this.value)"></s:select>
										</div>
									</div>
								

								<div class="form-group">
									<label class="col-md-3 control-label">Depot<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select id="depotlist" class="select2_category form-control" >
											<option value="0">select</option>
										</select>
									</div>
								</div>
							
										
								<div class="form-group">
									<label class="control-label col-md-3">Start Date<font
										color="red">*</font></label>
									<div class="col-md-3">
										<div class="input-group input-medium date date-picker"
											style="width: auto" data-date-format="dd-mm-yyyy"
											data-date-viewmode="years">
											<input type="text" class="form-control" id="startdate" 
												readonly name="selecteselecteddateddate" /> <span
												class="input-group-btn">
												<button class="btn default" type="button">
													<i class="fa fa-calendar"></i>
												</button> 
											</span>
											<script>
													var curr_date = new Date().toJSON().slice(0,10);
													curr_date = curr_date.split("-");
													curr_date = curr_date[2] + "-" + curr_date[1] + "-" + curr_date[0];
													var dtval = document.getElementById('startdate').value;
													if (dtval == '') {
														$('#startdate').val(curr_date);
													}
												</script>	
										</div>
									</div>
								</div>		
								
		
							<div class="portlet box white ">
										<div>
											<input type="hidden" name="requestType" id="requestType" />
											<div>
												<div class="portlet solid white">
													<div class="form-group">
														<label class="col-md-3 control-label"></label>
													</div>
							                 </div>
							             </div>
							       </div>
								
								<div class="form-group">
								<label class="control-label col-md-3"></label>
									<div class="col-md-3" id="">
									<button type="button" class="btn default" onclick="getScheduledTripStatusDataOnSubmit()" >Submit</button>
									</div>
									</div>
								</div>		
								</form>
								<div id="earlyarrivalsummary"></div>
								</div>
			          </div>
			
	                        
       	                  </div>
       	</div></div></div>
       	</div></div></div>	 
	
</body>
</html>
