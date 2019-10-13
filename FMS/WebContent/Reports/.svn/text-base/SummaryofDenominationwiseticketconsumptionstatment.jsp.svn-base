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
		}}
 var i=0;
 function getdenom(orgId){
	// $('#select2-chosen-2').html("Select");
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 var val=document.getElementById('denom').value;
			 if(val!=0) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/getdenom?val=' + val,
				success : function(result) {
					document.getElementById('all').innerHTML = result;
					document.getElementById('ETM').innerHTML = result;
					document.getElementById('MANUAL').innerHTML = result;
				}
			});
		}}
 
 
	function getDailypassDataOnSubmit()
	{
		
		var depotNo=$("#depotlist").val();
		var divisonNo=$("#divisionlist").val();
        var startdate=$("#startdate").val();
		var enddate=$("#enddate").val();
		var var1=$("#startdate").val();
		var denom=$("#denom").val();
		var1=var1.split("-");
		var1=var1[2]+"-"+var1[1]+"-"+var1[0];
 		var var2=$("#enddate").val();
 		var2=var2.split("-");
 		var2=var2[2]+"-"+var2[1]+"-"+var2[0];
		//alert(var1+""+var2);
		
		 var d1 = Date.parse(var1);

		 
 	var d3=Date.parse(var2);
 		if (d1 <= d3){
 			 $('#pageLoader').show();
	        $.ajax({
	        
	            type: "post",
	            url: '<%=request.getContextPath()%>/AjaxSummaryofDenominationwiseticketconsumptionstatment.action?startdate='+startdate+'&enddate='+enddate+'&depotNo='+depotNo+' &denom='+denom+'&divisonNo='+divisonNo,
	            success: function(result) {
	            	$('#pageLoader').hide();
	                document.getElementById('earlyarrivalsummary').innerHTML=result;
	           //     document.getElementById('printid').innerHTML=result;
			             
	            }
	        });
			}else{
	 			
	 			
	 			alert("End Date Should Be greater Than Start Date");
	 			$('#pageLoader').hide();
	 			 document.getElementById('earlyarrivalsummary').innerHTML="";
	 		}
		
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
							Summary of Denomination wise ticket consumption statement<small></small>
						</h3>

						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portle-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Summary of Denomination wise ticket consumption statement
							</div>
							
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
												headerValue="All" onchange="getDepot(this.value)"></s:select>
										</div>
									</div>

								<div class="form-group">
									<label class="col-md-3 control-label">Depot<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select id="depotlist" class="select2_category form-control" >
											<option value="0">All</option>
										</select>
									</div>
								</div>
								
							<div class="form-group">
								<label class="col-md-3 control-label">Denominiation<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select id="denom" class="select2_category form-control" name="denom">
											
									<option value="0">All(ETM+MANUAL)</option>
									<option value="ETM">ETM</option>
									<option value="Normal">MANUAL</option>
									<option value="AD">ADULT</option>
									<option value="CH">CHILD</option>
										</select>
									</div>
							</div>
							</div>
		
							
				 			<div class="portlet-body form">
					      <div class="form-body">
						     <div class="form-group">
									<label class="control-label col-md-3">From:</label>
										<div class="col-md-3">
											<div class="input-group input-medium date date-picker" style="width: auto" data-date-format="dd-mm-yyyy" data-date-viewmode="years">
												<input id="startdate" class="form-control" type="text" readonly="" size="16" name="rateMaster.effectiveStartDate" value="<s:property value='rateMaster.effectiveStartDate' />">
												<span class="input-group-btn">
													<button class="btn default date-set" type="button">
														<i class="fa fa-calendar"></i>
													</button>
												</span>
												<script>
// 													var curr_date = new Date().toJSON().slice(0,10);
// 													curr_date = curr_date.split("-");
// 													curr_date = curr_date[2] + "-" + curr_date[1] + "-" + curr_date[0];
// 													var dtval = document.getElementById('startdate').value;
// 													if (dtval == '') {
// 														$('#startdate').val(curr_date);
// 													}
												</script>
											</div>
										</div>
										</div>
								<div class="form-group">
									<label class="control-label col-md-3">To:</label>
										<div class="col-md-3">
											<div class="input-group input-medium date date-picker"	style="width: auto" data-date-format="dd-mm-yyyy" data-date-viewmode="years" data-date-end-date="-2d">
												<input id="enddate" class="form-control" type="text"	readonly="" size="16" name="enddate"	value="<s:property value='enddate' />" >
												<span class="input-group-btn">
													<button class="btn default date-set" type="button">
														<i class="fa fa-calendar"></i>
													</button>
												</span>
												<script>
// 													var curr_date = new Date().toJSON().slice(0,10);
// 													curr_date = curr_date.split("-");
// 													curr_date = curr_date[2]+ "-"+ curr_date[1]+ "-"+ curr_date[0];
// 													var dtval = document.getElementById('enddate').value;
// 													if (dtval == '') {
// 														$('#enddate').val(curr_date);
													
// 													}
												</script>
												</div></div></div>
											
											
   <script>                 	
    function tabletoExcel(btnExport){     
  	var htmltable= document.getElementById('btnExport');   
    $( "#header-fixed" ).remove();
    var inputHTML = "<table border='1'>";
    inputHTML += "<tr>";
    inputHTML += "<th  align='left'colspan='3'>Bangalore Metropolitan Transport Corporation</th>";
    inputHTML += "</tr>";
    inputHTML += "<th  align='left'colspan='3'>Summary of Denomination wise ticket consumption statement</th>";
    inputHTML += "</tr>";
    inputHTML += "<tr>";
    inputHTML += "<th colspan='2' align='left'>Date Range:</th>";
    inputHTML += "<th colspan='2' align='left'>" + $("#startdate").val() + " to " + $("#endate").val() + "</th>";
    inputHTML += "</tr>";
    inputHTML += "</table>";
    var htmltable = document.getElementById("printid");
    var html = inputHTML + htmltable.outerHTML;
    var downloadLink = document.createElement("a");
    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
    downloadLink.download = "Summary of Denomination wise ticket consumption statement.xls";
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
}</script>
                                                               
								<div class="form-group">       
								<label class="control-label col-md-3"></label>
									<div class="col-md-3" id="">
									<button type="button" class="btn green" onclick="getDailypassDataOnSubmit()" >Submit</button>
								  <span><button type="button" class="btn green" id="btnExport" onclick="tabletoExcel();"> EXPORT </button></span>&nbsp;<span>
                                </div></div></div>
								</form>
								<div id="earlyarrivalsummary"></div>
								
			
	</div></div></div></div></div>	 
</body></html> 