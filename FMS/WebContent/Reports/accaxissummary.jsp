  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
 <script src="https://code.jquery.com/jquery-1.11.3.min.js" type="text/javascript"></script>
	<script src="https://cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/responsive/1.0.7/js/dataTables.responsive.min.js" type="text/javascript"></script>
<script>



function getData(){
	
  	var dd1=$("#startdate").val();
	var dd2=$("#enddate").val();
		var var1=$("#startdate").val();
		var1=var1.split("-");
	var1=var1[2]+"-"+var1[1]+"-"+var1[0];
		var var2=$("#enddate").val();
		var2=var2.split("-");
		var2=var2[2]+"-"+var2[1]+"-"+var2[0];
	//alert(var1+""+var2);
	
	 var d1 = Date.parse(var1);
	 var depot=$("#depotlist").val();
	 
	var d3=Date.parse(var2);
		if (d1 <= d3){
    	$('#settlementsummary').attr("style", "display:''");
    	
        table = $('#settlementsummaryttable');
        var oTable = table.dataTable({
        	"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
    		// per
    		// page
    		// values
    		// here
    		],
    		// set the initial value
    		"iDisplayLength" : 5,
    		"sAjaxSource" : "ajaxgetaccamoutpendingsettlement.action?startdate="+dd1+"&enddate="+dd2,
    		"sPaginationType" : "bootstrap",
    		"bDestroy" : true,
    		"bSort" : true,
    		"bFilter" : true,
    		"bSelect" : false,
    		"bPaginate" : false,
    		"bInfo": false,
    		"oLanguage" : {
    			"sLengthMenu" : "_MENU_ records",
    			"oPaginate" : {
    				"sPrevious" : "Prev",
    				"sNext" : "Next"
    			}
    		},
    		"aoColumnDefs" : [ {
    			'bSortable' : false,
    			'aTargets' : [ 0 ]
    		} ]
    	});

    	jQuery('#settlementsummaryttable_wrapper .dataTables_filter input').addClass(
    			"form-control input-xsmall input-inline"); // modify table
    	// search input
    	jQuery('#settlementsummaryttable_wrapper .dataTables_length select').addClass(
    			"form-control input-xsmall input-inline");
		}else{
			alert("Till Date Should Be greater Than Start Date");
		}
	
}
</script>
<script>
var oTable;
function getdepotwisereport(depotid,startdate,enddate){
	
	$('#acc66axisnotsett').attr("style", "display:''");
// 	$('#mymodal1').attr("style", "display:''");
	
    var table = $('#notsettledata');
    //alert(depotid);
     oTable = table.dataTable({
   	 "aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
		// per
		// page
		// values
		// here
		],
		// set the initial value
		"iDisplayLength" : 5,
		"sAjaxSource" : "ajaxgetdepotwiseaccaxisdata.action?depotid="+depotid+"&startdate="+startdate+"&enddate="+enddate,
		"sPaginationType" : "bootstrap",
		"bDestroy" : true,
		"bSort" : false,
		"bFilter" : true,
		"bSelect" : false,
		"bPaginate" : false,
		"bInfo": false,
		"oLanguage" : {
			"sLengthMenu" : "_MENU_ records",
			"oPaginate" : {
				"sPrevious" : "Prev",
				"sNext" : "Next"
			}
		},
		"aoColumnDefs" : [ {
			'bSortable' : false,
			'aTargets' : [ 0 ]
		} ]
	});

	jQuery('#notsettledata_wrapper .dataTables_filter input').addClass(
			"form-control input-xsmall input-inline"); // modify table
	// search input
	jQuery('#notsettledata_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");
}
</script>

<script>
var oTable;
function getdepotnotsettamt(depotid,startdate,enddate){
	
	$('#acc66axisnotsett').attr("style", "display:''");
// 	$('#mymodal1').attr("style", "display:''");
	
    var table = $('#notsettledata');
    //alert(depotid);
     oTable = table.dataTable({
   	 "aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
		// per
		// page
		// values
		// here
		],
		// set the initial value
		"iDisplayLength" : 5,
		"sAjaxSource" : "ajaxgetaccamoutsummarynotamt.action?depotid="+depotid+"&startdate="+startdate+"&enddate="+enddate,
		"sPaginationType" : "bootstrap",
		"bDestroy" : true,
		"bSort" : false,
		"bFilter" : true,
		"bSelect" : false,
		"bPaginate" : false,
		"bInfo": false,
		"oLanguage" : {
			"sLengthMenu" : "_MENU_ records",
			"oPaginate" : {
				"sPrevious" : "Prev",
				"sNext" : "Next"
			}
		},
		"aoColumnDefs" : [ {
			'bSortable' : false,
			'aTargets' : [ 0 ]
		} ]
	});

	jQuery('#notsettledata_wrapper .dataTables_filter input').addClass(
			"form-control input-xsmall input-inline"); // modify table
	// search input
	jQuery('#notsettledata_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");
}
</script>

<script>
		
function printDiv() {     
	 
	 document.getElementById("settlementsummaryttable").style.visibility='visible'; 
	 

  document.getElementById("headerr").style.display='block';
  document.getElementById("headerr").style.visibility='visible';     
  var divElements = document.getElementById("headerr").innerHTML;
 
  divElements+= "<table>"+document.getElementById("settlementsummaryttable").innerHTML+"</table>";
  

  
 //divElements += document.getElementById("schdeviation").innerHTML;

  var mywindow = window.open("<%=request.getContextPath()%>/Print.jsp");
 
  mywindow.onload = function() {
      mywindow.document.body.innerHTML=divElements;
      mywindow.document.body.innerHTML=divElements;
  //	mywindow.document.body.innerHTML = divElement;
      //   document.getElementById("print").style.visibility='';
      mywindow.print();
      mywindow.close();
  }
  document.getElementById("headerr").style.display = 'none';
	 document.getElementById("headerr").style.visibility = 'hidden'; 
}


</script>
  <script>
	function getheader() {
		
	
			var startdate =$("#startdate").val();
			var enddate =$("#enddate").val();


			$.ajax({
		           type: "get",
		           url: '<%=request.getContextPath()%>/ajaxreconheader?startdate='+startdate+'&enddate='+enddate,
		           success: function(result) {
		        	   var obj = jQuery.parseJSON(result);
		        	   console.log("==>"+obj["bbData"][0]);
		        	    	   document.getElementById('startdat').innerHTML=obj["bbData"][0];
		        	    	   document.getElementById('enddat').innerHTML=obj["bbData"][1];
						
		        	   
		           }
		       });}
 </script>
<div class="page-content-wrapper">
	<div class="page-content">
		<div id="pageLoader" class="modal fade in" tabindex="-1" role="dialog" aria-labelledby="myModalLabel3" aria-hidden="false">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<p>
							<img src="assets/images/loader1.gif" align="top"
								style="margin-left: 100px;" />
						</p>
						<p style='text-align: center'>Please Wait........</p>
					</div>
				</div>
			</div>
		</div>
			
			
			
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>Recon Summary Report
							</div>
						</div>
						
						<div class="portlet-body">
						

							<!-- 						start -->

<!-- <table> -->
                   <form action="" method="post" class="form-horizontal">
                           <div class="form-body">
							
							 <div class="form-group">
							  <label class="control-label col-md-3">From Date:</label>
								<div class="col-md-3">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" data-date-end-date="-2d"> 
								<input id="startdate" class="form-control" type="text" readonly="" size="16" name="rateMaster.effectiveStartDate" placeholder="Pick The From Date"
								value="<s:property value='rateMaster.effectiveStartDate' />"
								>
								<span class="input-group-btn">
								<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
								</button>
								</span>
								<script>
// 										var curr_date=new Date().toJSON().slice(0,10);
//                                         curr_date=curr_date.split("-");
//                                         curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
//                                         var dtval=document.getElementById('startdate').value;	
                                        
//                                         if(dtval==''){
//                                         $('#startdate').val(curr_date);
//                                         }
								</script>
								</div>
								</div></div>
								
									 <div class="form-group">
							  <label class="control-label col-md-3">End Date:</label>
								<div class="col-md-3">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" data-date-end-date="-2d"> 
								<input id="enddate" class="form-control" type="text" readonly="" size="16" name="rateMaster.effectiveStartDate" placeholder="Pick The End Date"
								value="<s:property value='rateMaster.effectiveStartDate' />"
								>
								<span class="input-group-btn">
								<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
								</button>
								</span>
								<script>
// 										var curr_date=new Date().toJSON().slice(0,10);
//                                         curr_date=curr_date.split("-");
//                                         curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
//                                         var dtval=document.getElementById('startdate').value;	
                                        
//                                         if(dtval==''){
//                                         $('#startdate').val(curr_date);
//                                         }
								</script>
								</div>
								</div></div>
					
							
								
						
						
							
							
						 <script type="text/javascript"> 
 	                     function tabletoExcel(btnExport){
 	                    	 document.getElementById("settlementsummaryttable").style.visibility='visible'; 
 
 	 	                 	     document.getElementById("headerr").style.display='block';
 	 	                 	     document.getElementById("headerr").style.visibility='visible';     
 	 	                 	     var divElements = document.getElementById("headerr").innerHTML;
 	 	                 	  // divElements+="<font color=red>"+"Acc Amout Settlement Details" +"</font>";
 	 	                 	   divElements+= "<table>"+document.getElementById("settlementsummaryttable").innerHTML+"</table>";
 	 	                 	 
 	 	                  
 	 	                  var noOfTableExist = 0;
 	 	                  try{
 	 	              		$("#tripstatus table").each(function(){
 	 	              			noOfTableExist++;
 	 	              		});
 	 	              		
 	 	              		console.log("Total no of tables : " + noOfTableExist);
 	 	              		/* If two table exist  then remove the last table on print click*/
 	 	              		/* if(noOfTableExist >= 2){
 	 	              			divElements = divElements.substring(0, divElements.indexOf("</table>") + 8) + "</div></div>";
 	 	              		} */
 	 	              	}catch(err){
 	 	              	    console.log("ExceptionCaught : " + err);
 	 	              	}
 	 	                 
 	 	                 var downloadLink = document.createElement("a");
 	 	                 downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(divElements);
 	 	                 downloadLink.download = "Recon Summary Report.xls";
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
					<button type="submit" class="btn green" onClick="getData(),getheader()">Submit</button> 
					<button type="button" id="btnExport" class="btn green" onclick="tabletoExcel();"> EXPORT </button>
                   <span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span>&nbsp;<span>

				</div>
						   	<div class="portlet-body" id="settlementsummary"
														style="display: none">
														<div style="text-align:center">
														<table
															class="table table-striped table-bordered table-hover"
															id="settlementsummaryttable">
															<thead>
													<tr>
												<th>Sr No</th><th>Depot</th><th>ACC-66 Amount</th><th>Its Amount</th><th>Axis Amount</th>
			<th>Differnce</th><th>Status</th><th>Audited Date</th>
													</tr>
															</thead>
														</table>
													</div>
                                                   </div>
					

			</div>
			
			
		</div>
	</div>
	</div>
	</div>
		 <div id="header" class="portlet-body">
										
									<br />
	<div style="display: none;" id="mymodal1" class="modal fade"
						tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
						aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content" style="width: 1200px; height: 500px;"
								align="justify">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true"></button>
									<h4 id="accdifff" class="modal-title"></h4>
								</div>
								<div>
									<p>
									<div class="portlet box white ">
										<div>
											<input type="hidden" name="requestType" id="requestType" />
											<div>
												<div class="portlet solid white">
													<div class="form-group">
														<label class="col-md-3 control-label"></label>
													</div>
													
                                                   
                                                   	<div class="portlet-body" id="acc66axisnotsett"
														style="display: none">
														<div style="text-align:center">
														<table
															class="table table-striped table-bordered table-hover"
															id="notsettledata">
															<thead>
													<tr>
													<th>Sr<br> No</th>
													<th>Depot</th>
													<th>Waybill<br>No</th>
													
													<th>Etm<br>No</th>
													<th>ACC<br> Amount</th>
													<th>Its<br>Amount</th>
													<th>Axis<br>Amount</th>
													<th>Status</th>
													<th>Acc<br> Date</th>
													<th>Its<br>Settlement</th>
													<th>Axis<br>Settlement</th>
													</tr>
																
															</thead>
														</table>
													</div>
                                                   </div>
                                                   
                                                   
												</div>
											</div>
										</div>
									</div>
									
								</div>

							</div>
						</div>
					</div>
					</div>
					
					    	<div id="headerr" class="portlet-body" style="display: none; visibility: hidden;">
 <center><h4 style="margin-center:350px;">Recon Summary Report</h4></center>
<br />
<div id="headerdetails" style="margin-left:0px;">

<table ID="mytable" style="width:60%;border:none;">
				<tr>
	


				
					<td ><b><font size="2"><label> From Date: </label></font></b>
					<!-- <td style="text-align: left;"> -->
								 <b><font size="2"><span id="startdat" style="text-align:center;margin-center:10px;"></span></font></b></td>
							<td ><b><font size="2"><label> End Date: </label></font></b>
					<!-- <td style="text-align: left;"> -->
								 <b><font size="2"><span id="enddat" style="text-align:center;margin-center:10px;"></span></font></b></td>
				
				</tr>
			
			</table>
			 <br/><br/> 

</div>
</div>
	
	</div>
	
	</head>
	</html>

	
	
	
