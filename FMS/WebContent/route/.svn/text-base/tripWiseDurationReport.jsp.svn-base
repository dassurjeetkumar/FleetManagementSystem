<style type="text/css">
.help-block,ul,li {
	list-style: none;
}
</style>

<script src="https://maps.googleapis.com/maps/api/js?client=gme-trimaxitinfrastructure&sensor=false&libraries=places,drawing&signature=CpSOeA5B6SzC2rxqlzd2JwenGX0="></script>
	<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
 	<script type="text/javascript" src="assets/vts/js/vts.js"></script> 
<script src="assets/global/plugins/jquery-1.11.0.min.js" value='1' type="text/javascript"></script>

<link rel="stylesheet" href="https://code.jquery.com/ui/1.9.1/themes/black-tie/jquery-ui.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />

	
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="page-content-wrapper">
	<div class="page-content">
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
				<h3 class="page-title">
					Trip Wise Travel Duration Report
				</h3>
				
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-12">
				<div class="portlet box grey-cascade">

					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-bars"></i> View Trip Wise Travel Duration Report
						</div>
						
					</div>
					
					<div class="portlet-body form">

						<form action="#" class="form-horizontal form-row-seperated"
							method="post">
							<FONT color="green" style="font-weight:bold"><s:actionmessage /></FONT>
							<FONT color="red" style="font-weight:bold"><s:actionerror /></FONT>
				        <div class="form-body">
									
								<div class="form-group">
													<label class="control-label col-md-3">Route Number :</label>
													<div class="col-md-3">
														<input class="form-control" tabindex="1" id="project" placeholder="Enter Route No to Search" 
														 name="project" type="text"	onkeyup="getRoute(this.value)"  onChange="getSchedule(this.value)" />
														 <input id="project-id" type="hidden">
								 						<input id="project-id1" type="hidden">
								 						<input id="project-id2" type="hidden">
														</div> 
													
														</div>
                            		
							<div class="form-group">
							<label class="col-md-2 control-label">StartTime</label>
									<div class="col-md-3">
					
						<div class="input-group date form_datetime">
							<input type="text" size="16" readonly name="startdate"
								id="startdate" class="form-control" value='<s:property value="startdate"/>'> <span
								class="input-group-btn">
								<button class="btn default date-set" type="button">
									<i class="fa fa-calendar"></i>
								</button>
							</span>
						</div>
					</div>
										
										<label class="col-md-2 control-label">EndTime</label>
								<div class="col-md-3">
					<div class="input-group date form_datetime">
						<input type="text" size="16" readonly name="enddate" id="enddate" value='<s:property value="enddate"/>'
							class="form-control"> <span
							class="input-group-btn">
							<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
							</button>
						</span>
					</div>
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
										<h4 style="margin-left:350px;">RouteWise Trips Timing Details For VTU Report</h4>
									<br />
									<div id="headerdetails" style="margin-left:0px;">
									 <table ID="mytable" style="width:60%;border:none;">
<!-- 														<tr> -->
<!-- 									 						<td style="border:none;"><b><label><font size="2"></font>Division:</label></b> -->
<!-- 															<td style="text-align: left;"> -->
<%-- 			 												<b><font size="2"><span  id="Divisionlistonheader" style="text-align: left;margin-left:10px;"></span></font></b></td> --%>
<!-- 														</tr> -->
<!-- 														<tr> -->
<!-- 														<td style="border:none;"><b><label><font size="2"></font>Depot:</label></b> -->
<!-- 															<td style="text-align: left;"> -->
<%-- 			 												<b><font size="2"><span  id="Depotlistonheader" style="text-align: left;margin-left:10px;"></span></font></b></td> --%>
														
<!-- 														</tr> -->
														
														
													</table>
                            	      

													 <br/><br/> 
                                    
                                </div>
                                </div>
                          
							<div class="portlet-body" id="viewRole12"
								style="visibility: hidden">
                        <div id="tripstatus">
								<table class="table table-striped table-bordered table-hover"
									id="showTripwiseTimingVtuTable">
									<thead>
										<tr>
											
											<th>SR No.</th>											
											<th>Schedule No</th>
											<th>Route No</th>
											<th>Trip No </th>
											<th>Duty Date</th>
											<th>Start Time </th>
											<th>End Time</th>
											<th>Act.Start Time</th>
											<th>Act.END Time</th>
											<th>Time Duration</th>
											<th>GPS Time Duration</th>
											
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
function getRoute(val){
	var result = "";
	var availableTags=[];	
	$.ajax({
	    type : 'GET',
	    data :'json',
	    url  :  "<s:url action='getRouteList'/>",
	    data :{
	    	routename: val,
	    },
	    success: function(data){
	        data =eval(data);
	        result=data;
	        $( "#project" ).autocomplete({
		        	minLength: 0,
		        	source: result,
		        	focus: function( event, ui ) {
		        	$( "#project" ).val( ui.item.route_number  );
		        	return false;
	        	},
	        	select: function( event, ui ) {
		        	$( "#project" ).val( ui.item.route_number );
		        	$( "#project-id" ).val( ui.item.route_name );
		        	return false;
	        	}
	        }).data( "ui-autocomplete" )._renderItem = function( ul, item ) {
	        	return $( "<li>" )
	        	.append( "<a>" + item.route_number + "</a>" )
	        	.appendTo( ul );
	       };
	    },
	    select: function (event, ui) {

	        alert("selected!");
	    },
	    change: function (event, ui) {

	        alert("changed!");
	    },
	    error : function(xhr, errmsg) {
	    	alert("No values found..!!"+errmsg);
	    }
	});
}

	
	function show_data() {
alert("hiiii");
		var route = $("#project").val();
		var startdate = document.getElementById("startdate").value;
		var enddate = document.getElementById("enddate").value;
		
		alert("......"+route);
		//alert(det);
		//alert(det1);
		

			var divId = document.getElementById("viewRole12");
			divId.style.visibility = 'visible';
			
		
		$('#showTripwiseTimingVtuTable')
		.dataTable(
				{
					"aaSorting" : [ [ 1, 'desc' ] ],
					"aLengthMenu" : [ [ 10, 50, 100 ], [ 10, 50, 100 ] 
					],
					
					"iDisplayLength" : 10,
					"bProcessing" : true,
					"bServerSide" : true,
					 "sAjaxSource" : "showTripwiseTravelduration.action?startdate="+startdate+"&enddate="+enddate+"&route="+route,
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
					} ,  
					 ]
				});
jQuery('#showTripwiseTimingVtuTable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
jQuery('#showTripwiseTimingVtuTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline");
		
	
}
</script>

<script>

	
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
