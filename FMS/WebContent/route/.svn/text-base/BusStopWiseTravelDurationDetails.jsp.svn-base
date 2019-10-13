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
					BusStopWise Travel Duration Details Report
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
							<i class="fa fa-bars"></i> View BusStopWise Travel Duration Details Report
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
				        <div class="form-body">
									
                            
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
														
														
                            
                            <div class="form-group">
							
					
					<label class="col-md-2 control-label">Route Number :</label>
													<div class="col-md-3">
														<input class="form-control" tabindex="1" id="project" placeholder="Enter Route No to Search" 
														 name="project" type="text"	onkeyup="getRoute(this.value)"  onChange="getSchedule(this.value)" />
														 <input id="project-id" type="hidden">
								 						<input id="project-id1" type="hidden">
								 						<input id="project-id2" type="hidden">
														</div> 
					
					
<!-- 						 <label class="col-md-2 control-label">Date:</label> -->
<!-- 								<div class="col-md-3"> -->
								
<!-- 								<div class="input-group input-medium date date-picker" -->
<!-- 															style="width: auto" data-date-format="dd-mm-yyyy" -->
<!-- 															data-date-viewmode="years" >data-date-start-date="+0d"> -->
<!-- 								<input id="effectiveStartDate" class="form-control" type="text"  size="16" name="effectiveStartDate" -->
<!-- 								value="" > -->
<%-- 								<span class="input-group-btn"> --%>
<!-- 								<button class="btn default date-set" type="button"> -->
<!-- 								<i class="fa fa-calendar"></i> -->
<!-- 								</button> -->
<%-- 								</span> --%>
<%-- 								<script> --%>
<!-- // 								var curr_date=new Date().toJSON().slice(0,10); -->
<!-- //                                 curr_date=curr_date.split("-");  -->
<!-- //                                  curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];  -->
<!-- //                                  var dtval=document.getElementById('"effectiveStartDate"').value;	  -->
                                
<!-- //                                  if(dtval==''){ -->
<!-- //                               $('#"effectiveStartDate"').val(curr_date);  -->
                            
<!-- //                                 }  -->
<%-- 								</script> --%>
<!-- 								</div> -->
<!-- 								</div> -->
										
										
</div>

 <div class="form-group">
							
					
										
								
</div>
                            
                            
                            <div class="form-group">
								<label class="col-md-2 control-label">Origin</label>
													<div class="col-md-3">
														<input class="form-control" tabindex="1" placeholder="Enter Bus Stop to Search" id="projectsource" name="projectsource" type="text"	onkeyup="getRoute2(this.value)"   />
														<input id="projectsource-id" type="hidden">
								 						<input id="projectsource-id1" type="hidden">
								 						<input id="projectsource-id2" type="hidden">
													</div>
							
                            
							
								<label class="col-md-2 control-label">Destination Stop</label>
													<div class="col-md-3">
														<input class="form-control" tabindex="1" placeholder="Enter Bus Stop to Search" id="projectdest" name="projectdest" type="text"	onkeyup="getRoute1(this.value)"   />
														<input id="projectdest-id" type="hidden">
								 						<input id="projectdest-id1" type="hidden">
								 						<input id="projectdest-id2" type="hidden">
													</div>
							</div>

					
							<div class="form-actions fluid">
								<div class="col-md-offset-3 col-md-3">
									<button type="button" class="btn blue" onclick="show_data();">Submit</button>
								

								</div>
								<div class="col-md-1"id="printbutton">
											<input type='button' class="btn default" id='button1' onclick='printDiv()' value='Print' />													
										</div>
										<div class="col-md-1">
											<button type="button" class='btn blue' id="btnExport" filename= "report" onclick="tabletoExcel();"> EXPORT </button>
																							
										</div>
							</div>



                             <div id="header" class="portlet-body" style="display: none; visibility: hidden;">
										<h4 style="margin-left:350px;">BusStopWise Travel Duration Details Report</h4>
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
									id="StopWiseTravelDurationDetailsTable">
									<thead>
										<tr>
											
											<th>SR No.</th>											
											<th>Schedule No</th>
											<th>Trip No </th>
											<th>From </th>
											<th>To </th>
											<th>Start Time</th>
											<th>End Time</th>
											<th>Actual Deparature Time </th>
											<th>Actual Arrival Time</th>
											<th>Schedule Running Time</th>
<!-- 											<th>GPS Time</th> -->
<!-- 											<th>GPS Trip Diff</th> -->
                                           <th>Actual Running Time</th>
											
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
    // alert("hiiii");
		var det=$("#projectsource-id").val();
		var det1=$("#projectdest-id").val();
		var busstop = $("#project").val();
		var startdate = document.getElementById("startdate").value;
		var enddate = document.getElementById("enddate").value;
		var res = startdate.split("-"); 
		var res1 = enddate.split("-"); 
		//var effectiveStartDate = document.getElementById("effectiveStartDate").value;
		//var schedulelist=$("#schedulelist").val();
		
		//alert(det);
		//alert(det1);
		//alert(res[0]);
		if(res[0]==res1[0]){

			var divId = document.getElementById("viewRole12");
			divId.style.visibility = 'visible';
			var table;
      table = $('#StopWiseTravelDurationDetailsTable');
     var oTable = table.dataTable({
		"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
		// per
		// page
		// values
		// here
		],
		// set the initial value
		//"iDisplayLength" : 5,
		"bFilter" : false,
        "bSelect" : false,
       "bPaginate":false,
		"sAjaxSource" : "StopWiseTravelDurationDetails.action?source="+det+"&dest="+det1+"&startdate="+startdate+"&enddate="+enddate+"&busstop="+busstop,
		"sPaginationType" : "bootstrap",
		"bDestroy" : true,

		"oLanguage" : {
			"sLengthMenu" : "_MENU_ records",
			"oPaginate" : {
				"sPrevious" : "Prev",
				"sNext" : "Next"
			}
		},
		"oLanguage" : {
			"sZeroRecords" : "",
			"sEmptyTable" : ""
		},
		"aoColumnDefs" : [ {
			'bSortable' : false,
			'aTargets' : [ 0 ]
		} ],
		
		aaSorting:[],
		"bLengthChange" : false,
		"sDom" : '<"top">rt<"bottom"flp><"clear">'
	})
// .wrap(
// 	"<div style='position:auto;overflow:auto;height:300px;width:100%'/>");
	jQuery('#StopWiseTravelDurationDetailsTable_wrapper .dataTables_filter input').addClass(
			"form-control input-small input-inline"); // modify
	// table
	jQuery('#StopWiseTravelDurationDetailsTable_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline"); // modify
}else{
	//alert("StartDate and EndDate must be Same");
	bootbox.alert("StartDate and EndDate must be Same");
}
}
	
// 	function show_data() {
//       // alert("hiiii");
// 		var det=$("#projectsource-id").val();
// 		var det1=$("#projectdest-id").val();
// 		var busstop = $("#project").val();
// 		var startdate = document.getElementById("startdate").value;
// 		var enddate = document.getElementById("enddate").value;
// 		//var schedulelist=$("#schedulelist").val();
		
// 		//alert(det);
// 		//alert(det1);
		

// 			var divId = document.getElementById("viewRole12");
// 			divId.style.visibility = 'visible';
			
		
// 		$('#StopWiseTravelDurationDetailsTable')
// 		.dataTable(
// 				{
// 					"aaSorting" : [ [ 1, 'desc' ] ],
// 					"aLengthMenu" : [ [ 10, 50, 100 ], [ 10, 50, 100 ] 
// 					],
					
// 					"iDisplayLength" : 10,
// 					"bProcessing" : true,
// 					"bServerSide" : true,
// 					 "sAjaxSource" : "StopWiseTravelDurationDetails.action?source="+det+"&dest="+det1+"&startdate="+startdate+"&enddate="+enddate+"&busstop="+busstop,
// 					"sPaginationType" : "bootstrap",
// 					"bDestroy" : true,
// 					 "bFilter": false,
// 					 "bPaginate" : false,
					
// 					"oLanguage" : {
// 						"sLengthMenu" : "_MENU_ records",
// 						"oPaginate" : {
// 							"sPrevious" : "Prev",
// 							"sNext" : "Next"
// 						}
// 					},
// 					"aoColumnDefs" : [ {
// 						'bSortable' : false,
// 						'aTargets' : [ 0 ]
// 					}, {
// 						"bSearchable" : false,
// 						"aTargets" : [ 0 ]
// 					} ,  
// 					 ]
// 				});
// jQuery('#StopWiseTravelDurationDetailsTable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
// jQuery('#StopWiseTravelDurationDetailsTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline");
		
	
// }
</script>

<script>

	
	
	function getDepot(orgId){
		alert("hiii");
		 $('#select2-chosen-2').html("Select");
		 $('#select2-chosen-3').html("Select");
			alert('Here');
			 /* var selectedValue = $('#form-control').val(); */
			 var val=document.getElementById('divisionlist').value;
				 if(val!=0) {
		        $.ajax({
		            type: "post",
		            url: '<%=request.getContextPath()%>/getDepot?val=' + val,
					success : function(result) {
						document.getElementById('depotlist1').innerHTML = result;
						//getVehicle("");
					}
				});
			}

		}
	
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
	
	function getRoute2(val){
		var result = "";
		var availableTags=[];	
		 var routeid=document.getElementById('project').value;
		// alert(routeid);
		$.ajax({
		    type : 'GET',
		    data :'json',
		    url  :  "<s:url action='BusStopDropDownList1'/>",
		    data :{
		    	routename: routeid,
		    	buststop:val,
		    },
		    success: function(data){
		        data =eval(data);
		      
		        result=data;
		        //console.log(data);
		        $( "#projectsource" ).autocomplete({
			        	minLength: 0,
			        	source: result,
			        	focus: function( event, ui ) {
			        		//console.log("ui.item.stopName"+ui.item.id);
			        	$( "#projectsource" ).val( ui.item.busStopName  );
			        	return false;
		        	},
		        	select: function( event, ui ) {
		        		//console.log(ui.item.busStopCode);
			        	$( "#projectsource" ).val( ui.item.busStopName );
			        	$( "#projectsource-id" ).val( ui.item.bus_stop_code );
			        	return false;
		        	}
		        }).data( "ui-autocomplete" )._renderItem = function( ul, item ) {
		        	//console.log(item.bus_stop_code);
		        	return $( "<li>" )
		        	.append( "<a>" + item.busStopName + "</a>" )
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


	function getRoute1(val){
		var result = "";
		var availableTags=[];	
		 var routeid=document.getElementById('project').value;
		// alert(routeid);
		$.ajax({
		    type : 'GET',
		    data :'json',
		    url  :  "<s:url action='BusStopDropDownList1'/>",
		    data :{
		    	routename: routeid,
		    	buststop:val,
		    },
		    success: function(data){
		        data =eval(data);
		      
		        result=data;
		        //console.log(data);
		        $( "#projectdest" ).autocomplete({
			        	minLength: 0,
			        	source: result,
			        	focus: function( event, ui ) {
			        		//console.log("ui.item.stopName"+ui.item.id);
			        	$( "#projectdest" ).val( ui.item.busStopName  );
			        	return false;
		        	},
		        	select: function( event, ui ) {
		        		//console.log(ui.item.busStopCode);
			        	$( "#projectdest" ).val( ui.item.busStopName );
			        	$( "#projectdest-id" ).val( ui.item.bus_stop_code );
			        	return false;
		        	}
		        }).data( "ui-autocomplete" )._renderItem = function( ul, item ) {
		        	//console.log(item.bus_stop_code);
		        	return $( "<li>" )
		        	.append( "<a>" + item.busStopName + "</a>" )
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

	
		function tabletoExcel(btnExport){     
          	var htmltable= document.getElementById('btnExport');   
            $( "#header-fixed" ).remove();
            var inputHTML = "<table border='1'>";
            inputHTML += "<tr>";
            inputHTML += "<th  align='left'colspan='9'>Bangalore Metropolitan Transport Corporation</th>";
            inputHTML += "</tr>";
            inputHTML += "<th  align='left'colspan='9'>Bus Stop Wise Travel Duration Report </th>";
            inputHTML += "</tr>";
            inputHTML += "<tr>";
//             inputHTML += "<th colspan='2' align='left'>Date Range:</th>";
//             inputHTML += "<th colspan='7' align='left'>" + $("#startdate").val() + " to " + $("#endate").val() + "</th>";
            inputHTML += "</tr>";
            inputHTML += "</table>";
            var htmltable = document.getElementById("tripstatus");
            var html = inputHTML + htmltable.outerHTML;
            var downloadLink = document.createElement("a");
            downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
            downloadLink.download = "Bus Stop Wise Travel Duration.xls";
            document.body.appendChild(downloadLink);
            downloadLink.click();
            document.body.removeChild(downloadLink);
        }
		
</script>
