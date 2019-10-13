<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
 
	

	function getScheduledTripStatusDataOnSubmit()
	{
		
		
		var etmno=$("#etmno").val();
		var startdate=$("#startdate").val();
		var enddate=$("#enddate").val();
		
	
	
	        $.ajax({
	        
	            type: "post",
	            url: '<%=request.getContextPath()%>/AjaxETMWiseTicketSaleDetailsReport.action?startdate='+startdate+'&enddate='+enddate+'&etmno='+etmno,
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
	

	
 </Script>
 <script type="text/javascript">
 function printDiv() {     
	    
	    //  document.getElementById("print").style.visibility='hidden';     
	    var divElements = document.getElementById("header").innerHTML;
	    divElements += document.getElementById("earlyarrivalsummary").innerHTML;

	    var mywindow = window.open("<%=request.getContextPath()%>/Print1.jsp");
	   
	    mywindow.onload = function() {
	    mywindow.document.body.innerHTML=divElements;
	    mywindow.document.body.innerHTML=divElements;
	    mywindow.print();
	    mywindow.close();
	    }
	            
	}
 </script>

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
							ETM Wise Sale Details report <small></small>
						</h3>

						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portle-title">
							<div class="caption">
								<i class="fa fa-gift"></i>ETM Wise Sale Details report
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
										<label class="col-md-3 control-label">ETM No.<font
											color="red">*</font></label>
										<div class="col-md-4">
											<s:select list="etmtype" id="etmno" name="etmno"
												cssClass="form-control select2me" headerKey="0"
												headerValue="select" ></s:select>
										</div>
									</div>
								

<!-- 								<div class="form-group"> -->
<!-- 									<label class="col-md-3 control-label">Depot<font -->
<!-- 										color="red">*</font></label> -->
<!-- 									<div class="col-md-4"> -->
<%-- 										<select id="depotlist" class="select2_category form-control" > --%>
<!-- 											<option value="0">select</option> -->
<%-- 										</select> --%>
<!-- 									</div> -->
<!-- 								</div> -->
							

									
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
								<div class="form-group">
									<label class="control-label col-md-3">End Date<font
										color="red">*</font></label>
									<div class="col-md-3">
										<div class="input-group input-medium date date-picker"
											style="width: auto" data-date-format="dd-mm-yyyy"
											data-date-viewmode="years">
											<input type="text" class="form-control" id="enddate"
												readonly name="selecteselecteddateddate" /> <span
												class="input-group-btn">
												<button class="btn default" type="button">
													<i class="fa fa-calendar"></i>
												</button> 
											</span>
											<script>
													var curr_date = new Date().toJSON().slice(0,10);
													curr_date = curr_date.split("-");
													curr_date = curr_date[2]+ "-"+ curr_date[1]+ "-"+ curr_date[0];
													var dtval = document.getElementById('enddate').value;
													if (dtval == '') {
														$('#enddate').val(curr_date);
														
													}
												</script>
													
										</div>
									</div>
										
								</div>
								
								<div class="form-group">
								<label class="control-label col-md-3"></label>
									<div class="col-md-3" id="">
									<button type="button" class="btn default" onclick="getScheduledTripStatusDataOnSubmit()" >Submit</button>
									</div>
									<div class="col-md-1"id="printbutton">
											<input type='button' class="btn default" id='button1' onclick='printDiv()' value='Print' />													
										</div>
									</div>
								</div>		
								</form>
								<div id="earlyarrivalsummary"></div>
								</div>
			</div>
			
<!-- 			<table class='table table-striped table-bordered table-hover' id='ticketTable'> -->
<!-- 												<tr> -->
<!-- 													<th>Sl No</th> -->
<!-- 													<th>Denomination</th> -->
<!-- 													<th>No. of tickets</th> -->
<!-- 													<th>Total Revenue</th> -->
													
<!-- 												</tr>   -->
												
<!-- 												<tr> -->
<!-- 													<th colspan="2" style="text-align: center" > Total</th>		 -->
<!-- 												</tr> -->
<!-- 											</table> -->
			
			</div></div></div></div>	 
							

</body>
</html>