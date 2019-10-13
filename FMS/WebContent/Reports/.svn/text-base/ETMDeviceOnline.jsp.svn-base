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
		  var val=document.getElementById('depotlist').value;
		  alert("val...."+val)
		  if(val!=0) {
	        $.ajax({
	            type: "get",
	            url: '<%=request.getContextPath()%>/getDriverTokenNo?val=' + val,
				success : function(result) {
					document.getElementById('drivertoken').innerHTML = result;
				}
			});
		}

	}

	
	
	function getScheduledTripStatusDataOnSubmit()
	{
		
		var depotNo=$("#depotlist").val();
		var startdate=$("#startdate").val();
		var enddate=$("#enddate").val();
		var token=$("#drivertoken").val();
		
		
	//alert("depotNo..."+depotNo+"....divisonNo...."+divisonNo+"....startdate...."+startdate+"....enddate...."+enddate+"...vehiclelist..."+vehiclelist);
// 	 var d1 = Date.parse(startdate);

		
// 		var d3=Date.parse(enddate);
// 			if (d1 <= d3){
	        $.ajax({
	        
	            type: "get",
	            url: '<%=request.getContextPath()%>/AjaxETMDeviceOnline.action?startdate='+startdate,
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
								<i class="fa fa-gift"></i>ETM Online Device Report
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
<!-- 									<div class="form-group"> -->
<!-- 										<label class="col-md-3 control-label">Depot<font -->
<!-- 											color="red">*</font></label> -->
<!-- 										<div class="col-md-4"> -->
<%-- 											<s:select list="depotlist" id="depotlist" name="depot" --%>
<%-- 												cssClass="form-control select2me" headerKey="0" --%>
<%-- 												headerValue="Division" onchange="getDepot(this.value)"></s:select> --%>
<!-- 										</div> -->
<!-- 									</div> -->
								

<!-- 								<div class="form-group"> -->
<!-- 									<label class="col-md-3 control-label">Driver Token No:<font -->
<!-- 										color="red">*</font></label> -->
<!-- 									<div class="col-md-4"> -->
<%-- 										<select id="drivertoken" name = "token" class="select2_category form-control" > --%>
<!-- 											<option value="0">token</option> -->
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
												
										</div>
									</div>
										
								</div>
<!-- 								<div class="form-group"> -->
<!-- 									<label class="control-label col-md-3">End Date<font -->
<!-- 										color="red">*</font></label> -->
<!-- 									<div class="col-md-3"> -->
<!-- 										<div class="input-group input-medium date date-picker" -->
<!-- 											style="width: auto" data-date-format="dd-mm-yyyy" -->
<!-- 											data-date-viewmode="years"> -->
<!-- 											<input type="text" class="form-control" id="enddate" -->
<%-- 												readonly name="selecteselecteddateddate" /> <span --%>
<%-- 												class="input-group-btn"> --%>
<!-- 												<button class="btn default" type="button"> -->
<!-- 													<i class="fa fa-calendar"></i> -->
<!-- 												</button>  -->
<%-- 											</span> --%>
												
<!-- 										</div> -->
<!-- 									</div> -->
										
<!-- 								</div> -->
								
					
 	                   <script>                 	
   function tabletoExcel(btnExport){     
  	var htmltable= document.getElementById('btnExport');   
    $( "#header-fixed" ).remove();
    var inputHTML = "<table border='1'>";
    inputHTML += "<tr>";
    inputHTML += "<th  align='left'colspan='5'>Bangalore Metropolitan Transport Corporation</th>";
    inputHTML += "</tr>";
    inputHTML += "<th  align='left'colspan='5'>ETM Online Device Report</th>";
    inputHTML += "</tr>";
    inputHTML += "<tr>";
    inputHTML += "<th colspan='3' align='left'>Date Range:</th>";
    inputHTML += "<th colspan='3' align='left'>" + $("#startdate").val() + " to " + $("#endate").val() + "</th>";
    inputHTML += "</tr>";
    inputHTML += "</table>";
    var htmltable = document.getElementById("printid");
    var html = inputHTML + htmltable.outerHTML;
    var downloadLink = document.createElement("a");
    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
    downloadLink.download = "ETM Online Device Report.xls";
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
}</script>
  				
								
								<div class="form-group">
								<label class="control-label col-md-3"></label>
									<div class="col-md-3" id="">
									<button type="button" id="btnExport" class="btn green" onclick="getScheduledTripStatusDataOnSubmit()" >Submit</button>
									 <button type="button" id="btnExport" class="btn green" onclick="tabletoExcel();"> EXPORT </button>
                      
									</div>
									</div>
								</div>		
								</form>
								<div id="earlyarrivalsummary"></div>
								</div>
			</div>
			</div></div></div></div>	 
							

</body>
</html>