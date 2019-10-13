
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
<script src="https://code.jquery.com/jquery-1.11.3.min.js" type="text/javascript"></script>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
	<script src="https://cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/responsive/1.0.7/js/dataTables.responsive.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/fixedheader/3.0.0/js/dataTables.fixedHeader.min.js" type="text/javascript"></script>
<script src="assets/vts/js/vts.js"></script>
<script>

function printDiv() {     
	 
	 document.getElementById("KmCancellationTable").style.visibility='visible';  
    document.getElementById("header").style.display='block';
    document.getElementById("header").style.visibility='visible';     
    var divElements = document.getElementById("header").innerHTML;
    divElements+= "<table>"+document.getElementById("KmCancellationTable").innerHTML+"</table>";
    var mywindow = window.open("<%=request.getContextPath()%>/Print.jsp");
    mywindow.onload = function() {
        mywindow.document.body.innerHTML=divElements;
        mywindow.document.body.innerHTML=divElements;
    
        mywindow.print();
        mywindow.close();
    }
//     document.getElementById("header").style.display = 'none';
// 	 document.getElementById("header").style.visibility = 'hidden';      
}


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

	}

function getCancelledKMData(){
	
	var div=$("#divisionlist").val();
		var dd1=$("#startdate").val();
		var depotlist1=$("#depotlist1").val();
		 if( div == "0" )
	        {
	            bootbox.alert("Please select division");
	           return false;
	        }
	        if( depotlist1 == "0" )
	        {
	            bootbox.alert("Please select depot");
	           return false;
	        }   
	        $('#KmCancellationTable').dataTable({
    			"aLengthMenu" : [ [ 10, 15, 20, -1 ], [ 10, 15, 20, "All" ] // change
    			// per
    			// page
    			// values
    			// here
    			],
    			// set the initial value
    			//"iDisplayLength" : 5,
    			"sAjaxSource" : "getCancellationKMPercentReport.action?startdate="+dd1+"&depotlist1="+depotlist1,
    			"sPaginationType" : "bootstrap",
    			"bDestroy" : true,
//    			"bSort" : false,
//    			"bFilter" : false,
//    			"bSelect" : false,
//    			"bPaginate" : false,
//    			"bInfo": false,

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
    				'#KmCancellationTable_wrapper .dataTables_filter input')
    				.addClass("form-control input-small input-inline"); // modify
    		// table
    		jQuery(
    				'#KmCancellationTable_wrapper .dataTables_length select')
    				.addClass("form-control input-xsmall input-inline"); // modify         

}
</script>

<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />

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
				</div>
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
				<div id="header" >
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>20% KM Cancellation
							</div>
						</div>
						</div>
						</div>
						</div>
							<!-- 						start -->

                     <form action="" method="post" class="form-horizontal">
                        <div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Division<font
											color="red">*</font></label>
										<div class="col-md-4">
											<s:select list="divisionlist" id="divisionlist" 
											name="org_chart_id"  
												cssClass="select2_category form-control"  
												 onchange="getDepot(this.value)" headerKey="0" headerValue="--select--"></s:select>  
												 
										</div>
									</div>	
<%-- 									<script> --%>
<!-- 				getDepot(""); -->
<%-- 					</script> --%>
								
                               <div class="form-group">
									<label class="col-md-3 control-label">Depot<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select list="depotlist" id="depotlist1" class="select2_category form-control" name="depotlist1"
 											> 
											<option value="0">--select--</option>
 										</select> 
									</div>
 								</div> 

							<div class="form-group">
							  <label class="control-label col-md-3">Date:</label>
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
								</div></div>
						
						<div class="form-group">
					 <label class="control-label col-md-3"></label>
                     <div class="col-md-3" id="">
                     </div>
                     </div>
                     </div>
                     </form>
                     
     	                 
    <script>                 	
  	 function tabletoExcel(btnExport){     
  	var htmltable= document.getElementById('btnExport');   
    $( "#header-fixed" ).remove();
    var inputHTML = "<table border='1'>";
    inputHTML += "<tr>";
    inputHTML += "<th  align='left'colspan='9'>Bangalore Metropolitan Transport Corporation</th>";
    inputHTML += "</tr>";
    inputHTML += "<th  align='left'colspan='9'>Km Cancellation Table</th>";
    inputHTML += "</tr>";
    inputHTML += "<tr>";
    inputHTML += "<th colspan='2' align='left'>Date Range:</th>";
    inputHTML += "<th colspan='7' align='left'>" + $("#startdate").val() + " to " + $("#endate").val() + "</th>";
    inputHTML += "</tr>";
    inputHTML += "</table>";
    var htmltable = document.getElementById("KmCancellationTable");
    var html = inputHTML + htmltable.outerHTML;
    var downloadLink = document.createElement("a");
    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
    downloadLink.download = "KmCancellationTable.xls";
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
}</script>
     	                 
                     
					<div id="ticketconsuptionid"></div>
					 <div align='center'>
					<button type="submit" class="btn green" onClick="getCancelledKMData()">Submit</button>&nbsp;&nbsp;
					<span><input type='button' class='btn green' id='button1' onclick="printDiv()" value='Print' /></span>
				
				    			 <button type="button" class="btn green" id="btnExport" filename= "report" onclick="tabletoExcel();"> EXPORT </button>
                                </div>	<!-- END EXAMPLE TABLE PORTLET-->
				</div>
				
					<div style="display: none;" id="mymodal4" class="modal fade"
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
									<div class="portlet-body" id="modalclass4"
										style="display: none">
										<div style="text-align:center">
				<table class="table table-striped table-bordered table-hover"
								id="kmdetailtable">
								<thead >
											<tr>
												<th>Sr. NO</th>
												<th>Trip NO</th>
												<th>Duty Type</th>
												<th>From Stop</th>
												<th>To Stop</th>
												<th>Vehicle No</th>
												<th>Sch. Departure</th>
												<th>GPS Dep. Time</th>
												<th>Dep. Status</th>
												<th>Sch. Arrival Time</th>
												<th>GPS Arrival Time</th>
												<th>Arr. Status</th>
												<th>Sch. Trip Duration</th>
												
												<th>Gps. Trip Duration</th>
												<!-- <th>Sch. Distance</th> -->
												<th>Sch. Distance(In KM)</th>
												<th>GPS Distance(In KM)</th>
												<th>Trip Status</th>
												<th id='mapshow'>Show On Map</th>
												<th id='mapshow'>Playback</th>
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
							<div id="schid">		
							<table class="table table-striped table-bordered table-hover"
								id="KmCancellationTable" >
								<thead>
									<tr>	
										<th>Sr No.</th>
										<th>Schedule No. </th>
										<th>Depot Name</th>
										<th>Schedule KM</th> 
										<th>GPS KM</th>
										<th>Cancel KM(%)</th>
									</tr>
								</thead>
							</table>	</div>
					</div>
 </div>
 </div>



