<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places,drawing,geometry"></script>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
<script src="https://code.jquery.com/jquery-1.11.3.min.js" type="text/javascript"></script>
	<script src="https://cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/responsive/1.0.7/js/dataTables.responsive.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/fixedheader/3.0.0/js/dataTables.fixedHeader.min.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />


	<script src="assets/vts/js/vts.js" type="text/javascript"></script>

 
<Script>

 

function printDiv() {     
    
    //  document.getElementById("print").style.visibility='hidden';     
    var divElements = document.getElementById("header").innerHTML;
    divElements += document.getElementById("tripstatus").innerHTML;
    
    var noOfTableExist = 0;
	try {
		$("#tripstatus table").each(function() {
			noOfTableExist++;
		});

		console.log("Total no of tables : " + noOfTableExist);
		/* If two table exist  then remove the last table on print click (for header fixed)*/
		if (noOfTableExist >= 2) {
			divElements = divElements.substring(0, divElements
					.indexOf("</table>") + 8)
					+ "</div></div>";
		}
	} catch (err) {
		console.log("ExceptionCaught : " + err);
	}


    var mywindow = window.open("<%=request.getContextPath()%>/Print1.jsp");
   
    mywindow.onload = function() {
    mywindow.document.body.innerHTML=divElements;
    mywindow.document.body.innerHTML=divElements;
    mywindow.print();
    mywindow.close();
    }
            
}


	function getHaltData()
	{
		
		var divisonNo=$("#divisionlist").val();
		var selecteddate=$("#selecteddate").val();
// 		validateflag=validateTripStatusReportFields(scheduleNo,depotNo,divisonNo,selecteddate);
// 		if(validateflag==true)
// 		{
	$('#pageLoader').show();
		 $.ajax({
	           type: "get",
	           url: '<%=request.getContextPath()%>/AjaxLateDutyStartFromHalting.action?divison='+ divisonNo+'&selectedDate='+selecteddate,
	           success: function(result) {
	        	   $('#pageLoader').hide();
	                document.getElementById('tripstatus').innerHTML=result;
	                fixHeader();
	                
	            }
	       }); 
// 		 document.getElementById('selectdate').innerHTML=selectedDate;
// 		 document.getElementById('scheduleno').innerHTML=scheduleNo;
		 
// 		}
	
	}
	
	



 </Script>

<title>Insert title here</title>
</head>
<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">
	<div class="page-content-wrapper">
		<div class="page-content">

			<div class="tab-content">
			
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
						<!-- BEGIN PAGE TITLE & BREADCRUMB-->
						<h3 class="page-title">
							Late Duty End From Halting Location <small></small>
						</h3>

						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>View Late Duty End From Halting Location Report
							</div>

							<div class="actions">

						
						<input type='button' class='btn blue' id='button1' onclick='printDiv()' value='Print' />													
										
						<button type="button" class='btn blue' id="btnExport" filename= "report" onclick="tabletoExcel();"> EXPORT </button>
                               

						</div>
						
						</div>

						<div class="portlet-body form">

							<div class="col-md-12" align="left" style="font-size: 1.1em">

								<%-- <span class="help-block" style="color: red; list-style: none"><s:actionerror /></span> --%>
								<span class="help-block" style="color: red; list-style: none"><s:actionmessage /></span>
							</div>
							<!-- BEGIN FORM-->
							<form action="" method="post" class="form-horizontal">

							<%-- 	<div class="form-group">
										<label class="col-md-3 control-label">Division :<font
											color="red">*</font></label>
										<div class="col-md-4">
											<s:select list="divisionlist" id="divisionlist" name="orgchart.org_chart_id"
- 												cssClass="form-control select2me" headerKey="0" 
												headerValue="All" ></s:select> 
										</div>
									</div> --%>



	            <div class="form-group">
									<label class="col-md-3 control-label">Division :<font
										color="red">*</font></label>
									<div class="col-md-4">
						                <select  id="divisionlist"  class="select2_category form-control" name="">
						                <option value="0">--All--</option>
							             
							           </select>
							      
					</div>
											</div>
								

						 		<div class="form-group">
									<label class="control-label col-md-3">Date :<font
										color="red">*</font></label>
									<div class="col-md-3">
										<div class="input-group input-medium date date-picker"
											style="width: auto" data-date-format="dd-mm-yyyy"
											data-date-viewmode="years">
											<input id="selecteddate" class="form-control" type="text" readonly="" size="16" name="rateMaster.effectiveStartDate"
								value="<s:property value='rateMaster.effectiveStartDate' />"
								> <span
												class="input-group-btn">
												<button class="btn default" type="button">
													<i class="fa fa-calendar"></i>
												</button> 
											</span>
												<script>
										var curr_date=new Date().toJSON().slice(0,10);
                                        curr_date=curr_date.split("-");
                                        curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
                                        var dtval=document.getElementById('selecteddate').value;	
                                        
                                        if(dtval==''){
                                        $('#selecteddate').val(curr_date);
                                        }
								</script>
										</div>
									</div>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<div class="col-md-1" id=""> 
									
									
									<button type="button" class='btn green' onclick='getHaltData()' style="position: static;">Submit</button>	
								</div></div> 
								
								
				<%-- 				<label class="control-label col-md-4">Date :</label>
								<div class="col-md-4">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" ><!-- data-date-start-date="+0d"> -->
								<input id="startdate" class="form-control" type="text" readonly="" size="16" name="rateMaster.effectiveStartDate"
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
								</div> --%>
								
								
								
								
									
								<div class="form-group">
								
	<script type="text/javascript">
      function tabletoExcel(btnExport){     
            
		var divElements = document.getElementById("tripstatus").innerHTML;
			var noOfTableExist = 0;
			try {
				$("#tripstatus table").each(function() {
					noOfTableExist++;
				});

				console.log("Total no of tables : " + noOfTableExist);
				/* If two table exist  then remove the last table on print click (for header fixed)*/
				if (noOfTableExist >= 2) {
					divElements = divElements.substring(0, divElements
							.indexOf("</table>") + 8)
							+ "</div></div>";
				}
			} catch (err) {
				console.log("ExceptionCaught : " + err);
			}

			var downloadLink = document.createElement("a");
			downloadLink.href = 'data:application/vnd.ms-excel,'
					+ encodeURIComponent(divElements);
			downloadLink.download = "Late Duty End From Halting Location Report.xls";
			document.body.appendChild(downloadLink);
			downloadLink.click();
			document.body.removeChild(downloadLink);
		}
	</script>
						
						
  	              	 
  	         
<!-- 									<div class="col-md-1" id=""> -->
									
<!-- 									</div> -->
								
					
							<!-- END FORM-->
						</div>
							
					</div>
					
				
				</div>
				<div id="tripstatus"></div>
			</div>
		</div>

	
										

	

</body>
</html>