<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
 
	function getDepotData()
	{
		var startdate=$("#startdate").val();
		var enddate=$("#enddate").val();
		
	        $.ajax({
	        
	            type: "get",
	            url: '<%=request.getContextPath()%>/AjaxDepotDataRevenue.action?startdate='+startdate+'&enddate='+enddate,
	            success: function(result) {
	            	$('#pageLoader').hide();
	                document.getElementById('depotrevenue').innerHTML=result;
	            }
	        });
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

<!-- 				<div class="row"> -->
<!-- 					<div class="col-md-12"> -->
<!-- 						BEGIN PAGE TITLE & BREADCRUMB -->
<!-- 						<h3 class="page-title"> -->
<%-- 							KMPL VEHICLE WISE REPORT <small></small> --%>
<!-- 						</h3> -->

<!-- 						END PAGE TITLE & BREADCRUMB -->
<!-- 					</div> -->
<!-- 				</div> -->
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Depot Wise Revenue
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

																	
								<div class="form-group">
									<label class="control-label col-md-3">Start Date<font
										color="red">*</font></label>
									<div class="col-md-3">
										<div class="input-group input-medium date date-picker"
											style="width: auto" data-date-format="dd-mm-yyyy"
											data-date-viewmode="years">
											<input type="text" class="form-control" id="startdate" /> <span
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
											<input type="text" class="form-control" id="enddate" /> <span
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
									<button type="button" class="btn default" onclick="getDepotData()" >Submit</button>
							 </div>
										</div>
										<div id="depotrevenue"></div>
								</div>	
								
								</form>
								
								</div>
			</div>
			</div></div></div></div>	 
							

</body>
</html>