  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
 
<script>


var i=0;
function getDepot(orgId){
	 $('#select2-chosen-2').html("Select");
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 var val=document.getElementById('divisionlist').value;
			 if(val!=0) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/getDepotList?val=' + val,
				success : function(result) {
					document.getElementById('depotlist').innerHTML = result;
				}
			});
		}

	}

</script> 
<script>
	function getheader() {
		var startdate=$("#startdate").val();
		
		var todate=$("#enddate").val();
	//alert(startdate);
		document.getElementById("startdat").innerHTML = startdate;
		document.getElementById("todat").innerHTML = startdate;
		}
 </script>
 <SCRIPT type="text/javascript"></SCRIPT>
<script>

function getData() {
//alert();
	var div=$("#divisionlist").val();
	var depot=$("#depotlist").val();
	var strdate=$("#startdate").val();
	var edate=$("#enddate").val();
	
	var var1=$("#startdate").val();
		var1=var1.split("-");
	var1=var1[2]+"-"+var1[1]+"-"+var1[0];
		var var2=$("#enddate").val();
		var2=var2.split("-");
		var2=var2[2]+"-"+var2[1]+"-"+var2[0];
	//alert(var1+""+var2);
	
	 var d1 = Date.parse(var1);

	 
	var d3=Date.parse(var2);
	
	if(div==0){
		alert("please select Devision");
	}
	else if(strdate=='' || strdate==null){
		alert("please select From  Date");
	}
		else if(edate=='' || edate==null){
			alert("please select  End Date");
		}
		

		else if (d1 > d3){
 			alert("to date should be more than start date");
 		}
		else{
	$('#logsheetdashbord').attr("style", "display:''");
	
    table = $('#logsheettable');
  
    var oTable = table.dataTable({
    	"aLengthMenu" : [ [ 5,10, 15, 20, -1 ], [ 5,10, 15, 20, "All" ] // change
		// per
		// page
		// values
		// here
		],
		// set the initial value
		"iDisplayLength" : 5,
		"sAjaxSource" : "etmdailypass!getdata.action?startdate="+strdate+"&div="+div+"&depot="+depot+"&enddate="+edate,
		"sPaginationType" : "bootstrap",
		"bDestroy" : true,
		"bSort" : false,
		"bFilter" : false,
		"bSelect" : true,
		"bPaginate" : false,
		"bInfo": true,
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

	jQuery('#clskmtable_wrapper .dataTables_filter input').addClass(
			"form-control input-xsmall input-inline"); // modify table
	// search input
	jQuery('#clskmtable_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");	
}}
</script>
<script>
		
function printDiv() {     
	  document.getElementById("logsheetdashbord").style.visibility='visible';  
	     document.getElementById("header").style.display='block';
	     document.getElementById("header").style.visibility='visible'; 
	    
	     
	     var divElements = document.getElementById("header").innerHTML;
	     divElements+= document.getElementById("logsheetdashbord").innerHTML;
	     console.log(divElements);
	     divElements = divElements.replace(/#mymodal11/g, "");
	 		divElements = divElements.replace(/href/g, "");
	      
	     var mywindow = window.open("<%=request.getContextPath()%>/Print1.jsp");
	     mywindow.onload = function() {
	         mywindow.document.body.innerHTML=divElements;
	         mywindow.document.body.innerHTML=divElements;
	     //	mywindow.document.body.innerHTML = divElement;
	         //   document.getElementById("print").style.visibility='';
	         mywindow.print();
	         mywindow.close();
	     }
	     document.getElementById("header").style.display = 'none';
			document.getElementById("header").style.visibility = 'hidden';
          
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
			
			
			
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>Etm Daily Pass
							</div>
						</div>
						
						<div class="portlet-body">
<!-- 						


							<!-- 						start -->

                    <form action="" method="post" class="form-horizontal">
                        <div class="form-body">
						
								
                        
 								
 						         <div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Division<font
											color="red">*</font></label>
										<div class="col-md-2">
											<s:select list="divisionlist" id="divisionlist"
												name="orgchart.org_chart_id"
												cssClass="select2_category form-control" headerKey="0" headerValue="---select---" onchange="getDepot(this.value)"></s:select>
										</div>
									</div>
								</div>
 							
 									<div class="form-group">
									<label class="col-md-3 control-label">Depot<font
										color="red">*</font></label>
									<div class="col-md-2">
										<select id="depotlist" class="select2_category form-control">
											<option value="0">---Select---</option>
										</select>
									</div>
								</div>
								
									<div class="form-group">
							  <label class="control-label col-md-3">Start Date:<font
										color="red">*</font></label>
								<div class="col-md-3">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" data-date-end-date="0d"> 
								<input id="startdate" name="dateaction" class="form-control" type="text" readonly="" size="16" name="rateMaster.effectiveStartDate" placeholder="Pick The Date" "
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
								</div></div>
										
																			<div class="form-group">
							  <label class="control-label col-md-3">End Date:<font
										color="red">*</font></label>
								<div class="col-md-3">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" data-date-end-date="0d"> 
								<input id="enddate" name="enddate" class="form-control" type="text" readonly="" size="16" name="rateMaster.effectiveStartDate" placeholder="Pick The Date" "
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
                                        var dtval=document.getElementById('enddate').value;	
                                        
                                         if(dtval==''){
                                         $('#enddate').val(curr_date);
                                        }
								</script>
								</div>
								</div></div>	
				
 					
						<!-- end -->
						
					
                     </div>
                     </form>
				
					 <div align='center'>
					<button type="submit" class="btn blue" onClick="getData(),getheader()">Submit</button>
					<span><input type='button' class='btn blue' id='button1' onclick='printDiv()' value='Print' /></span>&nbsp;
					<button type="submit" class="btn blue" onClick="tabletoExcel()">Export</button>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
				<!-- <div id="ticketconsuptionid"></div>	 -->		
			</div>
	
			<!-- END PAGE CONTENT-->
			
		
	
		
	
	
				<div class="portlet-body" id="logsheetdashbord" style="display: none; visibility: hidden;">
						
						
                           
                           <table class="table table-striped table-bordered table-hover"
								id="logsheettable"  border='1' width="500" >
								
								<thead>
									<tr>
									<!-- 	<th style="width1: 8px;"></th> -->
										<th>SN No</th>
										<th>Depot No </th>
										<th>Date </th>
										 <th>Denom</th>
										<th>Etm Pass</th>
							<th>Etm Pass Amount</th>
							<th>Manual Pass</th>
							<th>Manual Pass Amount</th>
							<th>Total Pass</th>
							<th>Total Pass Amount</th>
									</tr>
								</thead>

							</table>
                         
							</div>
							</div></div></div></div></div>
							
			<!-- 				<div style="display: none;" id="mymodal11" class="modal fade"
						tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
						aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content" style="width: 800px; height: 500px; overflow:scroll;"
								align="justify">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true"></button>
									<h4 id="vehicleno123" class="modal-title"></h4>
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
													<div class="portlet-body" id="depotwiseetmdetails"
														style="display: none">
														<right><button type="submit" class="btn blue" onClick="subtabletoExcel()">Export</button></right>
														<div style="text-align:center">
														<table
															class="table table-striped table-bordered table-hover"
															id="depotwiseetmdetailstable">
															<thead>
																<tr>
																	<th>Sr</th>
												<th>Depot</th>
												<th>Device <br>No</th>
												<th>Issue</th>
												<th>Created<br>Date</th>
													<th>Pick Up<br>Fme</th>
												<th>Etm Resolved<br>Fme</th>
												<th>Pick<BR>verifone</th>
												<th>Solved <br>verifone</th>
													<th>Receive<br>fme</th>
											
											
																</tr>
															</thead>
														</table>
													</div>
                                                   </div>
												</div>
											</div>
										</div>
									</div>
									</p>
								</div>

							</div>
						</div>
					</div> -->
							
							
<%-- 										<div id="header" class="portlet-body" style="display: none; visibility: hidden;">
 <h3 style="margin-left:350px;">Sync Revenue Info</h3>
<br />
<b><font size="2"><label>Date: </label></font><font size="2"><b><span id="startdat" style="text-align:left;margin-left:50px;"></span></font></b>

</div> --%>

<div id="header" class="portlet-body" style="display: none; visibility: hidden;">
<table  width="500" border='1' ><tr ><td  colspan='10'>
<center><div width="500px">
	<h3><span>Etm Daily Pass</span></h3>
</div></center>
</td></tr>

<tr><td align='center'colspan='5'><center><b><font size="2"><label>From Date: </label></font><font size="2"><b><span id="startdat" style="text-align:left;margin-left:50px;"></span></font></b></center></td>
<td align='center'colspan='5'><center><b><font size="2"><label>To Date: </label></font><font size="2"><b><span id="todat" style="text-align:left;margin-left:50px;"></span></font></b></center></td>

</tr>
</table></div>


	<script type="text/javascript">
	function tabletoExcel(btnExport){
         
    	 var divElements = document.getElementById("header").innerHTML;
        divElements =divElements+ document.getElementById("logsheetdashbord").innerHTML;
        
        var noOfTableExist = 0;
        try{
    		$("#logsheetdashbord table").each(function(){
    			noOfTableExist++;
    		});
    		
    		console.log("Total no of tables : " + noOfTableExist);
    		/* If two table exist  then remove the last table on print click*/
    		/* if(noOfTableExist >= 2){
    			divElements = divElements.substring(0, divElements.indexOf("</table>") + 8) + "</div></div>";
    		} */
    	
     		divElements = divElements.replace(/#mymodal11/g, "");
     		divElements = divElements.replace(/href/g, "");
    	}catch(err){
    	    console.log("ExceptionCaught : " + err);
    	}

    	 
   var downloadLink = document.createElement("a"); 
  downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(divElements); 
  downloadLink.download = "etmdailypass.xlsx"; 
  document.body.appendChild(downloadLink); 
  downloadLink.click(); 
  document.body.removeChild(downloadLink);
  }	</script>
<%--   <script>
  function getdepotwiselist(depot,startdate,enddate){
		
		$('#depotwiseetmdetails').attr("style", "display:''");
		//var divid=$("#divisionlist").val();
	    table = $('#depotwiseetmdetailstable');
	    var oTable = table.dataTable({
	    	"aLengthMenu" : [ [ 5,10, 15, 20, -1 ], [ 5,10, 15, 20, "All" ] // change
			// per
			// page
			// values
			// here
			],
			// set the initial value
			"iDisplayLength" : 5,
			"sAjaxSource" : "ajaxgetdepotwisedata.action?orgname="+depot+"&startdate="+startdate+"&enddate="+enddate,
			"sPaginationType" : "bootstrap",
			"bDestroy" : true,
			"bSort" : true,
			"bFilter" : true,
			"bSelect" : true,
			"bPaginate" : true,
			"bInfo": true,
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

		jQuery('#depotwiseetmdetailstable_wrapper .dataTables_filter input').addClass(
				"form-control input-xsmall input-inline"); // modify table
		// search input
		jQuery('#depotwiseetmdetailstable_wrapper .dataTables_length select').addClass(
				"form-control input-xsmall input-inline");
  }
  </script> --%>
  
  	<script type="text/javascript">
	function subtabletoExcel(btnExport){
         
    	 var divElements = document.getElementById("header").innerHTML;
        divElements =divElements+ document.getElementById("depotwiseetmdetails").innerHTML;
        
        var noOfTableExist = 0;
        try{
    		$("#depotwiseetmdetails table").each(function(){
    			noOfTableExist++;
    		});
    		
    		console.log("Total no of tables : " + noOfTableExist);
    		/* If two table exist  then remove the last table on print click*/
    		if(noOfTableExist >= 2){
    			divElements = divElements.substring(0, divElements.indexOf("</table>") + 8) + "</div></div>";
    		}
    	}catch(err){
    	    console.log("ExceptionCaught : " + err);
    	}

    	 
   var downloadLink = document.createElement("a"); 
  downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(divElements); 
  downloadLink.download = "etmdailypass.xlsx"; 
  document.body.appendChild(downloadLink); 
  downloadLink.click(); 
  document.body.removeChild(downloadLink);
  }	</script>
