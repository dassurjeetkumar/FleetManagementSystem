<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//www.google.com/jsapi"></script>

<script src="assets/vts/js/tripWiseVtuPacketAnalysis.js"></script>

<%-- <script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places,drawing,geometry"></script>
 --%>
 <Script>
 function getDepot(orgId){
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
	function getSchedule()
	{
		 var val=document.getElementById('depotlist').value;
		 var selectedDate=document.getElementById("selecteddate").value;
		 if(val!=0) {
     $.ajax({
         type: "POST",
         url: '<%=request.getContextPath()%>/getSchedule?val=' + val+'&selectedDate='+selectedDate,
			success : function(result) {
				document.getElementById('schedulelist').innerHTML = result;
			}
		});
	}
	}
	
	function getHide(){
		$('#vtuPacketTable').hide();
		$('#VtuPacketReceived').hide();
		
	}
	
	function printDiv() {     
		 
		 document.getElementById("tripstatus").style.visibility='visible';  
	    document.getElementById("header").style.display='block';
	    document.getElementById("header").style.visibility='visible';     
	    var divElements = document.getElementById("header").innerHTML;
	    divElements+= document.getElementById("tripstatus").innerHTML;
	    
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

 </Script>

<title>Insert title here</title>
</head>
<body onload="getHide()">
	<div class="page-content-wrapper">
		<div class="page-content">

			<div class="tab-content">
			
		<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						TRIP WISE VTU PACKET ANALYSIS <small></small>
					</h3>
					
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>View Trip Wise Vtu Packet Analysis Report
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
							<form action=""  method="post" class="form-horizontal">
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Division<font color="red">*</font></label>
										<div class="col-md-4">
											<s:select list="divisionlist" id="divisionlist"
									name="orgchart.org_chart_id"
									cssClass="select2_category form-control" headerKey="0"
									headerValue="Division" onchange="getDepot(this.value)"></s:select>							</div>
									</div>
								</div>
								<div class="form-body">
								<div class="form-group">
									<label class="col-md-3 control-label">Depot<font color="red">*</font></label>
									<div class="col-md-4">
										<select id="depotlist" class="select2_category form-control"
									>
									<option value="0">Depot</option>
								</select>
									</div>
								</div>
								</div>
								<div class="form-body">
								<div class="form-group">
									<label class="control-label col-md-3">Date<span class="required">* </span></label>
									<div class="col-md-4">
										<div class="input-group input-medium date date-picker"
									style="width: auto" data-date-format="yyyy-mm-dd"
									data-date-viewmode="years">
									<input type="text" class="form-control" id="selecteddate" onchange="getSchedule(this.value)"
										readonly name="selecteselecteddateddate" /> <span class="input-group-btn">
										<button class="btn default" type="button" >
											<i class="fa fa-calendar"></i>
											
										</button>
									</span>
									<%-- <script>
							var curr_date = new Date().toJSON().slice(0, 10);
							curr_date = curr_date.split("-");
							curr_date = curr_date[0] + "-" + curr_date[1] + "-"
									+ curr_date[2];
							$('#selecteddate').val(curr_date);
						</script> --%>
								</div>			
								</div>
								</div>
								</div>
								<div class="form-body">
								
								<div class="form-group">
									<label class="col-md-3 control-label">Schedule Number:-<font color="red">*</font></label>
										<div class="col-md-4">
											<select id="schedulelist" name="scheduleno" class="select2_category form-control"
									onchange="getTripVtuPacketTable(this.value)">
									<option value="0">Schedule</option>
								</select>														
									
										</div>
										<div class="col-md-1" id="">
										<button type="button" class="btn default" onclick="getTripVtuPacketTableOnSubmit()" style="position: static;">Submit</button>
								        </div>
								<div class="col-md-1"id="printbutton">
											<input type='button' class="btn default" id='button1' onclick='printDiv()' value='Print' />													
										</div>
								
								</div>
								
								</div>
								<!-- <div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<button type="button" class="btn blue" onclick="getTripVtuPacketTableOnSubmit()">Submit</button>
											
											
										</div>
									</div>  -->
								</form>
								<!-- END FORM-->
								
								
								<div id="header" class="portlet-body" style="display: none; visibility: hidden;">
										<h4 style="margin-left:350px;">Trip Wise VTU Packet Analysis Report</h4>
									<br />
									<div id="headerdetails" style="margin-left:0px;">
<!--                             	        <table ID="mytable" style="width:60%;border:none;"> -->
<!-- 														<tr> -->
<!-- 									 						<td ><b><label><font size="2">Schedule No :</font></label></b> -->
<!-- 															<td style="text-align: left;"> -->
<%-- 			 												<b><font size="2"><span  id="scheduleno" style="text-align: left;margin-left:10px;"></span></font></b></td> --%>
								
				
						 							
<!-- 															<td ><b><font size="2"><label> Schedule Type: </label></font></b> -->
<!-- 															<td style="text-align: left;"> -->
<%-- 																		 <b><font size="2"><span id="scheduletype" style="text-align:left;margin-left:10px;"></span></font></b></td> --%>
<!-- 														</tr> -->
<!-- 														<tr> -->
<!-- 															<td><font size="2"><label>Date:</label></font> -->
<%-- 															<!-- <td nowrap style="text-align: left;"> --><font size="2"><b><span id="selectdate" style="text-align:left;margin-left:50px;"></span></font></b></td> --%>
															
<%-- 															<td style="text-align: left;"><b><font size="2"><label>From:</label></font><font size="2"><b><span id="fromDate" style="text-align:left;margin-left:50px;"></span></font></b></td> --%>
<%-- 															<td><font size="2"><label><s:property --%>
<%-- 																		value="" /> </label></font></td> --%>
<%-- 																		<td nowrap style="text-align: left;"><b><font size="2"><label>To:</label></font><font size="2"><b><span id="toDate" style="text-align:left;margin-left:50px;"></span></font></b></td> --%>
<!-- 															<td nowrap></td> -->
<!-- 														</tr> -->
<!-- 														<tr> -->
															
<%-- 															<td nowrap><b><font size="2"><label>Base Route: </label></font><font size="2"><b><span id="baseRoute" style="text-align:left;margin-left:50px;"></span></font></b></td> --%>
<!-- 															<td nowrap></td> -->
<!-- 														</tr> -->
<!-- 													</table> -->
<!-- 													 <br/><br/>  -->
                                    
                                </div>
                                </div>
								
							<div class="portlet-body" id="vtuPacketTable">
							<div id="tripstatus">
								<table class="table table-striped table-bordered table-hover"
									id="vtuPacketAnalysis" style="display: none">
									<thead>
										<tr>
											<th>Sr. No</th>
											<th>Device ID</th>
											<th>Trip NO</th>
											<th>Duty Type</th>
											<th>From</th>
											<th>To</th>	
											<th>Sch. Departure Time</th>
											<th>Sch. Arrival Time</th>
											<th>GPS. Departure Time</th>
											<th>GPS Arrival Time</th>
											<!-- <th>Sch. Distance</th>
											<th>GPS. Distance</th>		 -->											
	                                        <th>VTU Packet Received</th>
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
	
	<div style="display: none;" id="mymodal7" class="modal fade"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
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
									
							
							<div class="portlet-body" id="VtuPacketReceived">
										<table class="table table-striped table-bordered table-hover"
											id="VtuPacketReceivedTable" >
											<thead>
												<tr>
													<th>Sr. No</th>
<!-- 													<th>Vehicle Number</th> -->
													<th>Device ID</th>													
													<th>Capture time (IST date/time)</th>
													<th>Location area name</th>
													<th>Server Date time</th>
													
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
		

							

</body>
</html>