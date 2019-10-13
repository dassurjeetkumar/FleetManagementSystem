<style type="text/css">
.help-block,ul,li {
	list-style: none;
}
</style>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="page-content-wrapper">
	<div class="page-content">
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
				<h3 class="page-title">
					ETM Count Report
				</h3>
				<!-- <ul class="page-breadcrumb breadcrumb">

					<li><i class="fa fa-home"></i> Home <i
						class="fa fa-angle-right"></i></li>
					<li>Duty Rota <i class="fa fa-angle-right"></i></li>
					<li>Duty Rota Home</li>
				</ul> -->
				<!-- END PAGE TITLE & BREADCRUMB-->
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-12">
				<div class="portlet box grey-cascade">

					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-bars"></i> View Daily ETM Count Repot
						</div>
						<!-- <div class="tools">
							<a href="" class="collapse"></a> <a href="javascript:;"
								class="reload"> </a>
						</div> -->
				

<!-- 						<div class="actions"> -->
<!-- 							<a href="#" class="btn green" id="createChart" -->
<!-- 								onclick="callCreate()"> <i class="fa fa-plus"></i> Create -->
								
<!-- 							</a> <a href="#" class="btn blue" id="editPageDetails" onclick="callEdit()" -->
<!-- 								> <i class="fa fa-pencil"></i> Edit -->
								
<!-- 							</a> <a href="#" class="btn red" id="deletePageDetails" onclick="callDelete()" -->
<!-- 								> <i class="fa fa-times"></i> Delete -->
<!-- 							</a> -->
<!-- 							<a href="#" class="btn green" id="viewPageDetails" onclick="callPrint()" -->
<!-- 								> <i class="fa "></i> View -->
<!-- 								changed inactive -->
<!-- 							</a> -->
<!-- 						<a href="#" class="btn blue" id="viewPageDetails" onclick="callInActive()" -->
<!-- 								> <i class="fa "></i> Show InActive Data -->
<!-- 							</a>  -->
<!-- 							<a href="#" class="btn green" id="viewPageDetails" onclick="callSearchSchedule()" -->
<!-- 								> <i class="fa fa-search"></i> Search Schedule -->
<!-- 							</a>  -->
<!-- 														<a href="#" class="btn green" id="editPageDetails"
<!-- 								onclick="CallPrint()"> <i class="fa fa-print"></i> Print -->
<!-- 							</a> -->
<!-- 							<a href="#" class="btn green" id="createChart" -->
<!-- 								onclick="callSavePdf()"> <i class="fa fa-save"></i> Save as -->
<!-- 								PDF -->
<!-- 							</a> -->
<!-- 							<a href="#" class="btn green" id="editPageDetails" -->
<!-- 								onclick="CallExportExcel()"> <i class="fa fa-save"></i> Export To -->
<!-- 								Excel -->
<!-- 							</a> --> 
<!-- 						</div> -->
					</div>
					
					<div class="portlet-body form">

						<form action="#" class="form-horizontal form-row-seperated"
							method="post">
							<FONT color="green" style="font-weight:bold"><s:actionmessage /></FONT>
							<FONT color="red" style="font-weight:bold"><s:actionerror /></FONT>
				
                            
<!--                             <div class="form-group"> -->
<%-- 								<label class="col-md-3 control-label">Division<sup><font --%>
<%-- 										color="red">*</font></sup></label> --%>
<!-- 								<div class="col-md-3"> -->
<%-- 									<s:select list="divisionlist" id="divisionlist"  --%>
<%-- 											name="org_chart_id"   --%>
<%-- 												cssClass="select2_category form-control"   --%>
<%-- 												 onchange="getDepot(this.value)" ></s:select>   --%>
<!-- 								</div> -->
<!-- 							</div> -->
                            
<!-- 							<div class="form-group"> -->
<%-- 								<label class="col-md-3 control-label">Depot<sup><font --%>
<%-- 										color="red">*</font></sup></label> --%>
<!-- 								<div class="col-md-3"> -->
<%-- 									<select list="depotlist" id="depotlist1" class="select2_category form-control" name="depotlist1" --%>
<%--  											>  --%>
<!-- 											<option value="0">ALL</option> -->
<%--  										</select>  --%>
<!-- 								</div> -->
<!-- 							</div> -->

							
							<div class="form-group">
									<label class="control-label col-md-3">Date<sup><font
										color="red">*</font></sup></label>
										<div class="col-md-3">
											<div class="input-group input-medium date date-picker"	style="width: auto" data-date-format="dd-mm-yyyy" data-date-viewmode="years">
												<input id="startdate" class="form-control" type="text"	readonly="" size="16" >
												<span class="input-group-btn">
													<button class="btn default date-set" type="button">
														<i class="fa fa-calendar"></i>
													</button>
												</span>
												<script>
													var curr_date = new Date().toJSON().slice(0,10);
													curr_date = curr_date.split("-");
													curr_date = curr_date[2]+ "-"+ curr_date[1]+ "-"+ curr_date[0];
													var dtval = document.getElementById('startdate').value;
													if (dtval == '') {
														$('#startdate').val(curr_date);
														
													}
													
												</script>
												
											</div>
										</div>
										</div>
										
										
											<div class="form-group"> 
								<label class="col-md-3 control-label">Depot<sup><font
										color="red">*</font></sup></label>
								<div class="col-md-3">
									<s:select list="divisionlist" id="divisionlist1" cssClass="select2_category form-control"  name="divisionlist1"
 											>  
											
  										</s:select>  
								</div>
							</div>


							<div class="form-actions fluid">
								<div class="col-md-offset-3 col-md-3">
									<button type="button" class="btn blue" onclick="show_data();">Submit</button>
								

								</div>
								<div class="col-md-1"id="printbutton">
											<input type='button' class="btn default" id='button1' onclick='printDiv()' value='Print' />													
										</div>
							</div>



                             <div id="header" class="portlet-body" style="display: none; visibility: hidden;">
										<h4 style="margin-left:350px;">ETM Usage Details</h4>
									<br />
									<div id="headerdetails" style="margin-left:0px;">
									 <table ID="mytable" style="width:60%;border:none;">
														
														<tr>
														<td style="border:none;"><b><label><font size="2"></font>Depot:</label></b>
															<!-- <td style="text-align: left;"> -->
			 												<b><font size="2"><span  id="Depotlistonheader" style="text-align: left;margin-left:10px;"></span></font></b></td>
														
														</tr>
														<tr>
															
															<td  style="border:none;"><b><font size="2"><label> Date:</label></font><font ><span id="startdate1" style="text-align:left;margin-left:10px;"></span></font></b>
															<font size="2"><label><s:property
																		value="" /> </label></font></td>
																		
														</tr>
														
													</table>
                            	      

													 <br/><br/> 
                                    
                                </div>
                                </div>
                          
							<div class="portlet-body" id="viewRole12"
								style="visibility: hidden">
                        <div id="tripstatus">
								<table class="table table-striped table-bordered table-hover"
									id="showETMCountTable">
									<thead>
										<tr>
											
											<th>SR No.</th>	
											<th>ETM No.</th>	
											<th>WayBill No.</th>									
<!-- 											<th>Schedule No</th> -->
											<th>ETM Amount</th>
											
											
											
											
										</tr>
									</thead>

								</table>
</div>
							</div>

						</form>
					</div>
		
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	
	
	function show_data() {

		document.getElementById('Depotlistonheader').innerHTML=$("#divisionlist1 option:selected").text();
		var startdate = document.getElementById("startdate").value;
		var depot = document.getElementById("divisionlist1").value;
		document.getElementById('startdate1').innerHTML=startdate;
	 	
	if(startdate==""){
		bootbox.alert('Select Date');
		}
	else{
			var divId = document.getElementById("viewRole12");
			divId.style.visibility = 'visible';
		
		$('#showETMCountTable')
		.dataTable(
				{
					"aaSorting" : [ [ 1, 'desc' ] ],
					"aLengthMenu" : [ [ 10, 50, 100 ], [ 10, 50, 100 ] 
					],
					
					"iDisplayLength" : 10,
					"bProcessing" : true,
					"bServerSide" : true,
					 "sAjaxSource" : "AjaxDailyEtmCount.action?startdate=" + startdate+'&depot=' + depot,
					"sPaginationType" : "bootstrap",
					"bDestroy" : true,
					 "bFilter": false,
					 "bPaginate" : false,
					
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
					}, {
						"bSearchable" : false,
						"aTargets" : [ 0 ]
					} ,  { "sClass": "url1", "aTargets": [ 0 ] },{ "sClass": "url2", "aTargets": [ 1 ] },
					{ "sClass": "url3", "aTargets": [ 2 ] },{ "sClass": "url4", "aTargets": [ 3 ] },
					 ]
				});
jQuery('#showETMCountTable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
jQuery('#showETMCountTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline");
		
	}
}
</script>

<script>

	
	
// 	function getDepot(orgId){
// 		 $('#select2-chosen-2').html("Select");
// 		 $('#select2-chosen-3').html("Select");
// 			//alert('Here');
// 			 /* var selectedValue = $('#form-control').val(); */
// 			 var val=document.getElementById('divisionlist').value;
// 				 if(val!=0) {
// 		        $.ajax({
// 		            type: "post",
<%-- 		            url: '<%=request.getContextPath()%>/getDepotForetm?val=' + val, --%>
// 					success : function(result) {
// 						document.getElementById('depotlist1').innerHTML = result;
// 						getVehicle("");
// 					}
// 				});
// 			}

// 		}
	
	function printDiv() {     
		  document.getElementById("tripstatus").style.visibility='visible';  
	     document.getElementById("header").style.display='block';
	     document.getElementById("header").style.visibility='visible'; 
	    
	     
	     var divElements = document.getElementById("header").innerHTML;
	     divElements+= document.getElementById("tripstatus").innerHTML;
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
			document.getElementById("mapshow").style.visibility=''; 
			$(".mapClass").show();
	     
	             
	 }
	
</script>
