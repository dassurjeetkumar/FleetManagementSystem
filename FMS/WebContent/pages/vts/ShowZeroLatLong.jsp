  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>

<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script> --%>
 <script src="assets/vts/js/vts.js" type="text/javascript"></script>
<%--  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" /> --%>
 
<script>

<%-- 
function getDepot(orgId){
	// $('#select2-chosen-2').html("Select");
	// $('#select2-chosen-3').html("Select");
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 var val=document.getElementById('divisionlist').value;
			 if(val!=0) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/getDepot?val=' + val,
				success : function(result) {
					document.getElementById('depotlist1').innerHTML = result;
					
				}
			});
		}

	} --%>

	
	function getZeroLatlong(){
			
		var dd1=$("#startdate").val();
		var dd2=$("#endate").val();
		var type=$("#type").val();
			 if( type == "0" ) {
		            bootbox.alert("Please select Type");
		           return false; 
		  }	
			 
			 var var1=$("#startdate").val();
				var1=var1.split("-");
				var1=var1[2]+"-"+var1[1]+"-"+var1[0];
				var var2=$("#endate").val();
				var2=var2.split("-");
				var2=var2[2]+"-"+var2[1]+"-"+var2[0];
				//alert(var1+""+var2);
				
		    var d1 = Date.parse(var1);		
			var d3=Date.parse(var2);
				if (d1 <= d3){		 
// 		alert("dd1=="+d1+"dd2=="+d3);
			
		        $('#zerolatlong').dataTable({
	    			"aLengthMenu" : [ [ 10, 15, 20, -1 ], [ 10, 15, 20, "All" ] // change
	    			// per
	    			// page
	    			// values
	    			// here
	    			],
	    			// set the initial value
	    			//"iDisplayLength" : 5,
	    			"sAjaxSource" : "getZeroLatLongDevices.action?startdate="+dd1+"&type="+type+"&endDate="+dd2,
	    			"sPaginationType" : "bootstrap",
	    			"bDestroy" : true,
//	    			"bSort" : false,
//	    			"bFilter" : false,
//	    			"bSelect" : false,
//	    			"bPaginate" : false,
//	    			"bInfo": false,

	    			 "oLanguage": {
		                    "sLengthMenu": "_MENU_ records",
		                    "oPaginate": {
		                        "sPrevious": "Prev",
		                        "sNext": "Next"
		                    }
		                },
		               "aoColumnDefs": [
		                    { 'bSortable': false, 'aTargets': [0] },
		                    { "bSearchable": false, "aTargets": [ 0 ]} ,
		                    { "sClass": "url", "aTargets": [ 3 ] },
		                ],
	    		});
	    		jQuery(
	    				'#zerolatlong_wrapper .dataTables_filter input')
	    				.addClass("form-control input-small input-inline"); // modify
	    		// table
	    		jQuery(
	    				'#zerolatlong_wrapper .dataTables_length select')
	    				.addClass("form-control input-xsmall input-inline"); // modify         

	}else{
			
			
			bootbox.alert("Start date Should Be greater Than End date");
// 			$('#pageLoader').hide();
// 			 document.getElementById('ticketconsuptionid').innerHTML="";
	}
	
	}

// function getZeroLatlong(){
	
	
// 		var dd1=$("#startdate").val();
// 		var type=$("#type").val();
//  		/* var var1=$("#startdate").val();
//  		var depotlist1=$("#depotlist1").val();
// 		var div=$('#divisionlist').val();endate
// 		var dd2=$('#endate').val(); */
		
// 	        $('#pageLoader').show();
//         $.ajax({
        
//             type: "post",
<%--             url: '<%=request.getContextPath()%>/getZeroLatLongDevices.action?startdate='+dd1+'&type='+type, --%>
//             success: function(result) {
//             	$('#pageLoader').hide();
//                 document.getElementById('ticketconsuptionid').innerHTML=result;
//                 fixHeader();
//             }
//         });


// }
</script>
<%--         url: '<%=request.getContextPath()%>/getZeroLatLongDevices.action?startdate='+dd1+'&depotlist1='+depotlist1+'&divisionlist1='+div+'&endDate='+dd2, --%>
<script>     
	function printDiv() {     
		 
		 document.getElementById("zerolatlong").style.visibility='visible';  
//		 console.log(vtsAlertReportDivId);
	   document.getElementById("header").style.display='block';
	   document.getElementById("header").style.visibility='visible';     
	   var divElements = document.getElementById("header").innerHTML;
	   divElements+= "<table>"+document.getElementById("zerolatlong").innerHTML+"</table>";
	   
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
							<div class="caption" id="header">
								<i class="fa fa-globe"></i>Zero Lat Long Devices Report
							</div>
						</div>
						
						<div class="portlet-body">
<!-- 						<div class="table-scrollable"> -->


							<!-- 						start -->

                     <form action="" method="post" class="form-horizontal">
                        <div class="form-body">
									<%-- <div class="form-group">
										<label class="col-md-3 control-label">Division<font
											color="red">*</font></label>
										<div class="col-md-4">
											<s:select list="divisionlist" id="divisionlist" 
											name="org_chart_id"  
												cssClass="select2_category form-control"  
												 onchange="getDepot(this.value)" headerKey="0" headerValue="--All--"></s:select>  
												 
										</div>
									</div> --%>
								
                              <%--  <div class="form-group">
									<label class="col-md-3 control-label">Depot<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select list="depotlist" id="depotlist1" class="select2_category form-control" name="depotlist1"
 											> 
											<option value="0">--All--</option>
 										</select> 
									</div>
 								</div>  --%>
 								
 								
 								
 										<div class="form-group">
										<label class="col-md-3 control-label">Type<font
											color="red">*</font></label>
										<div class="col-md-3">
											<select id="type" name="type"
												class="select2_category form-control">
												<option value="0">---Select---</option>
												<option value="1">Partial 0-lat long</option>
												<option value="2">Full day 0-lat long</option>
												<OPTION value="3">2004 Devices</OPTION>
												
											</select>

										</div>



									</div>
 								
 								
 								<table>
							  <tr>
							  <td><label class="control-label col-md-4">From:<font
							color="red">*</font></label>
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
								</div></td>   
							  
							  <td><label class="control-label col-md-3">To:<font
							color="red">*</font></label>
								<div class="col-md-4">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" ><!-- data-date-start-date="+0d"> -->
								<input id="endate" class="form-control" type="text" readonly="" size="16" name="rateMaster.effectiveendDate"
								value="<s:property value='rateMaster.effectiveendDate' />"
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
                                 var dtval=document.getElementById('endate').value;	 
                                
                                 if(dtval==''){
                              $('#endate').val(curr_date); 
                            
                                } 
								</script>
								
								
								</div>
								</div></td> 

							  </tr>
							  <tr><td></td></tr>
							</table>
 								
 								
 								
 								
 								
 								
                          
		<%-- 					<div class="form-group">
							  <label class="control-label col-md-3"> From :</label>
								<div class="col-md-3">
								
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" ><!-- data-date-start-date="+0d"> -->
								<input id="startdate" class="form-control" type="text"  size="16" name="rateMaster.effectiveStartDate"
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
								</div>
								</div>
											<div class="form-group"> 
							  <label class="control-label col-md-1"> To :</label>
								<div class="col-md-3">
								
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" ><!-- data-date-start-date="+0d"> -->
								<input id="endate" class="form-control" type="text"  size="16" name="rateMaster.effectiveStartDate"
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
                                 var dtval=document.getElementById('endate').value;	 
                                
                                 if(dtval==''){
                              $('#endate').val(curr_date); 
                            
                                } 
								</script>
								</div>
								</div>
                           	</div>  --%>
								
								
					
								
								
								
<!-- 								</div> -->

		
 
 							
						<!-- end -->
						
						<div class="form-group">
					 <label class="control-label col-md-3"></label>
                     <div class="col-md-3" id="">
                     </div>
                     </div>
                     </div>
                     </form>
				
					 <div align='center'>
					<button type="submit" class="btn green" onClick="getZeroLatlong()">Submit</button>
					<span>					
					<input type='button' class="btn green" id='button1' onclick='printDiv()' value='Print' />													
					</span>&nbsp;
					<button type="submit" id="btnExport" class="btn green" onClick="tabletoExcel()">Export</button>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
<!-- 					<div id="ticketconsuptionid"></div> -->
			</div>
			
			<!-- END PAGE CONTENT-->
		</div>
	</div></div>
	
	
	<div style="display: none;" id="mymodal40" class="modal fade"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel3"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 800px; height: 500px;"
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
									<div class="portlet-body" id="modalclass40"
										style="display: none">
										<div style="text-align:center">
				<table class="table table-striped table-bordered table-hover"
								id="zerolat">
								<thead >
											<tr>
												<th>Sr. NO</th>
												<th>Device No</th>
												<th>Vehicle No</th>
												<th>Schedule No</th>
												<th>Date</th>
												<th>From Time</th>
												<th>To Time</th>
												<th>Minutes</th>
<!-- 												<th>Dep. Status</th> -->
<!-- 												<th>Sch. Arrival Time</th> -->
<!-- 												<th>GPS Arrival Time</th> -->
<!-- 												<th>Arr. Status</th> -->
<!-- 												<th>Sch. Trip Duration</th> -->
												
<!-- 												<th>Gps. Trip Duration</th> -->
<!-- 												<th>Sch. Distance</th> -->
<!-- 												<th>Sch. Distance(In KM)</th> -->
<!-- 												<th>GPS Distance(In KM)</th> -->
<!-- 												<th>Trip Status</th> -->
<!-- 												<th id='mapshow'>Show On Map</th> -->
<!-- 												<th id='mapshow'>Playback</th> -->
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
	</div>
			
			<!-- END PAGE CONTENT-->

						<!-- end -->
							<div id="zeroId">		
							<table class="table table-striped table-bordered table-hover"
								id="zerolatlong" >
								<thead>
									<tr>	
										<th>Sr No.</th>
										<th>Device No </th>
										<th>Vehicle no</th>
										<th>Depot No</th> 
										<th>count</th>
<!-- 										<th>Cancel KM(%)</th> -->
									</tr>
								</thead>
							</table>	</div>
	
	
	
	
	
	
	
	
	
	
	

			<script type="text/javascript">
				function tabletoExcel() {
				  	var htmltable= document.getElementById('btnExport');   
				    $( "#header-fixed" ).remove();
				    var inputHTML = "<table border='1'>";
				    inputHTML += "<tr>";
				    inputHTML += "<th  align='left'colspan='9'>Bangalore Metropolitan Transport Corporation</th>";
				    inputHTML += "</tr>";
				    inputHTML += "<th  align='left'colspan='9'>0-lat long Report</th>";
				    inputHTML += "</tr>";
				    inputHTML += "<tr>";
				    inputHTML += "<th colspan='2' align='left'>Date Range:</th>";
				    inputHTML += "<th colspan='7' align='left'>" + $("#startdate").val() + " to " + $("#endate").val() + "</th>";
				    inputHTML += "</tr>";
				    inputHTML += "</table>";
				    var htmltable ="<table>"+ document.getElementById("zerolatlong").innerHTML+"</table>";
				    var html = inputHTML + htmltable;
// 				    divElements+= "<table>"+document.getElementById("vtsAlertReportId").innerHTML+"</table>";

				    var downloadLink = document.createElement("a");
				    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
				    downloadLink.download = "Zero lat long Report.xls";
				    document.body.appendChild(downloadLink);
				    downloadLink.click();
				    document.body.removeChild(downloadLink);

				}
			
				
			</script>