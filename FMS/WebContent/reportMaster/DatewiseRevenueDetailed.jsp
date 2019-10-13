<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
 var i=0;
 function getDepot(orgId){
	 $('#select2-chosen-2').html("Select");
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
	
 function printDiv() {     
	    
	    //  document.getElementById("print").style.visibility='hidden';     
	  //  var divElements = document.getElementById("header").innerHTML;
	    var divElements= document.getElementById("printid").innerHTML;

	    
	    var mywindow = window.open("<%=request.getContextPath()%>/Print.jsp");
	   
	    mywindow.onload = function() {
	    mywindow.document.body.innerHTML=divElements;
	    mywindow.document.body.innerHTML=divElements;
	    mywindow.print();
	    mywindow.close();
	    }
	            
	}
 
	function validateTripStatusReportFields(depotNo,divisonNo,dd1,dd2,servicelist)
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
		 if(dd1==0)
	     {
		   alert("Please Select start Date");
		   return false;
	     }
	     if(dd2==0)
	     {
		   alert("Please Select end date");
		   return false;
	     }
	     if(servicelist==0)
	     {
		   alert("Please Select Service Type");
		   return false;
	     }
	     

	 return true;
	}
	function getScheduledTripStatusDataOnSubmit()
	{
		
		var depotNo=$("#depotlist").val();
		var divisonNo=$("#divisionlist").val();
		var servicelist=$("#servicelist").val();
		
		
		var dd1=$("#startdate").val();
		var dd2=$("#enddate").val();
	 	

	 		
	 		
	 		
        $.ajax({
        
            type: "post",
            url: '<%=request.getContextPath()%>/AjaxDatewiseRevenueDetailed.action?startdate='+dd1+'&enddate='+dd2+'&depot='+depotNo+'&division='+divisonNo+'&service='+servicelist,
            success: function(result) {
            	$('#pageLoader').hide();
                document.getElementById('collectionid').innerHTML=result;
                fixHeader();
            }
        });
	 		
// 	 		else{
	 			
	 			
// 	 			alert("Till Date Should Be greater Than Start Date");
// 	 			$('#pageLoader').hide();
// 	 			 document.getElementById('collectionid').innerHTML="";
// 	 		}
	
	}
	



 </Script>

<title>Insert title here</title>
</head>
<body>
	<div class="page-content-wrapper">
		<div class="page-content">
	

			<div class="tab-content">

<!-- 				<div class="row"> -->
<!-- 					<div class="col-md-12"> -->
<!-- 						BEGIN PAGE TITLE & BREADCRUMB -->
<!-- 						<h3 class="page-title"> -->
<%-- 							EARLY ARRIVAL SUMMARY REPORT <small></small> --%>
<!-- 						</h3> -->

<!-- 						END PAGE TITLE & BREADCRUMB -->
<!-- 					</div> -->
<!-- 				</div> -->
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>View Date wise Revenue Detailed Report
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
											<s:select list="divisionlist" id="divisionlist"
												
												cssClass="select2_category form-control" headerKey="0"
												headerValue="Division" onchange="getDepot(this.value)" name="division"></s:select>
										</div>
									</div>
								

								<div class="form-group">
									<label class="col-md-3 control-label">Depot<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select id="depotlist" class="select2_category form-control" name="depot">
											<option value="0">Depot</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">Service Type<font
										color="red">*</font></label>
									<div class="col-md-4">
<%-- 										<s:select list="servicelist" id="servicelist" name="servicelist" class="select2_category form-control" ></s:select> --%>
											<s:select list="servicelist" id="servicelist" name="service"
												
												cssClass="select2_category form-control" headerKey="0"
												headerValue="service" ></s:select>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-md-3">Start Date<font
										color="red">*</font></label>
									<div class="col-md-3">
										<div class="input-group input-medium date date-picker"
											style="width: auto" data-date-format="dd-mm-yyyy"
											data-date-viewmode="years">
											<input type="text" class="form-control" id="startdate" onchange="getSchedule(this.value)"
												readonly name="selecteselecteddateddate" /> <span
												class="input-group-btn">
												<button class="btn default" type="button">
													<i class="fa fa-calendar"></i>
												</button> 
											</span>
												
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
											<input type="text" class="form-control" id="enddate" onchange="getSchedule(this.value)"
												readonly name="selecteselecteddateddate" /> <span
												class="input-group-btn">
												<button class="btn default" type="button">
													<i class="fa fa-calendar"></i>
												</button> 
											</span>
												
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
								</div>
								
			</div>
			
			</div></div>
			<div id="collectionid"></div>
			</div>
			
			</div>	 
							

</body>
</html>