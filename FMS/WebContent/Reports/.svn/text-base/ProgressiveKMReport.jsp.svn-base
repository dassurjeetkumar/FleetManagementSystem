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
	 $('#select2-chosen-3').html("Select");
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
	 $('#select2-chosen-3').html("Select");
	 $('#select2-chosen-4').html("Select");
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 var val=document.getElementById('depotlist').value;
			 if(val!=0) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/AjaxVehicleNo?val=' + val,
				success : function(result) {
					document.getElementById('vehiclelist').innerHTML = result;
				}
			});
		}

	}
	
	
	function getScheduledTripStatusDataOnSubmit()
	{
		
		var depotNo=$("#depotlist").val();
		var divisonNo=$("#divisionlist").val();
		var startdate=$("#startdate").val();
		//var enddate=$("#enddate").val();
		var vehiclelist=$("#vehiclelist").val();
	
		

		
// 		var d3=Date.parse(enddate);
// 			if (d1 <= d3){
	        $.ajax({
	        
	            type: "post",
	            url: '<%=request.getContextPath()%>/AjaxProgressiveKMReport.action?startdate='+startdate+'&depotlist1='+depotNo+'&divisionlist1='+divisonNo+'&vehiclelist='+vehiclelist,
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
								<i class="fa fa-gift"></i>Progressive KM Report
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
												headerValue="Division" onchange="getDepot(this.value)"></s:select>
										</div>
									</div>
								

								<div class="form-group">
									<label class="col-md-3 control-label">Depot<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select id="depotlist" class="select2_category form-control" onchange="getvehicle(this.value)">
											<option value="0">Depot</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">Vehicle <font
										color="red">*</font></label>
									<div class="col-md-4">
										<select id="vehiclelist" class="select2_category form-control" >
											<option value="0">Vehicle</option>
										</select>
									</div>
								</div>

                         
									
							<div class="form-group">
									<label class="control-label col-md-3">Month & Year:<font
										color="red">*</font></label>
										<div class="col-md-4">
											<div class="input-group input-medium date date-picker"
												style="width: auto" data-date-format="mm-yyyy"
												data-date-viewmode="years">
												<input id="startdate" class="form-control" type="text"
													readonly="" size="16" />
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
													getDepot();
												</script>
											</div>
										</div>
										
								</div>
								
								<script>                 	
   function tabletoExcel(btnExport){     
  	var htmltable= document.getElementById('btnExport');   
    $( "#header-fixed" ).remove();
    var inputHTML = "<table border='1'>";
    inputHTML += "<tr>";
    inputHTML += "<th  align='left'colspan='13'>Bangalore Metropolitan Transport Corporation</th>";
    inputHTML += "</tr>";
    inputHTML += "<th  align='left'colspan='13'>Progressive KM Report</th>";
    inputHTML += "</tr>";
    inputHTML += "<tr>";
    inputHTML += "<th colspan='3' align='left'>Date Range:</th>";
    inputHTML += "<th colspan='4' align='left'>" + $("#startdate").val() + " to " + $("#endate").val() + "</th>";
    inputHTML += "</tr>";
    inputHTML += "</table>";
    var htmltable = document.getElementById("printid");
    var html = inputHTML + htmltable.outerHTML;
    var downloadLink = document.createElement("a");
    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
    downloadLink.download = "Progressive KM Report.xls";
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
}</script>
  		
								
								<div class="form-group">
								<label class="control-label col-md-3"></label>
									<div class="col-md-3" id="">
									<button type="button" class="btn default" onclick="getScheduledTripStatusDataOnSubmit()" >Submit</button>
								<span><button type="button" class="btn default" id="btnExport" onclick="tabletoExcel();"> EXPORT </button></span>&nbsp;<span>
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