<style type="text/css">
.help-block,ul,li {
	list-style: none;
}
</style>

	<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
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
					Bus Stop Wise Online Vehicle
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
							<i class="fa fa-bars"></i> Bus Stop Wise Online Vehicles
						</div>
						<!-- <div class="tools">
							<a href="" class="collapse"></a> <a href="javascript:;"
								class="reload"> </a>
						</div> -->
				

					</div>
					
					<div class="portlet-body form">

						<form action="#" class="form-horizontal form-row-seperated"
							method="post">
							<FONT color="green" style="font-weight:bold"><s:actionmessage /></FONT>
							<FONT color="red" style="font-weight:bold"><s:actionerror /></FONT>

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
							
					
					<label class="col-md-2 control-label">Bus Stop :</label>
													<div class="col-md-3">
														<input class="form-control" tabindex="1" id="projectsource" placeholder="Enter Bus Stop Name to Search" 
														 name="projectsource" type="text"	onkeyup="getRoute(this.value)" />
													<input id="projectsource-id" type="hidden">
								 						<input id="projectsource-id1" type="hidden">
								 						<input id="projectsource-id2" type="hidden">
														</div> 
					

										
										
</div>
                            
                            
							<div class="form-actions fluid">
								<div class="col-md-offset-3 col-md-3">
									<button type="button" class="btn blue" onclick="getSkipStop();">Submit</button>
								

								</div>
								<div class="col-md-1"id="printbutton">
											<input type='button' class="btn blue" id='button1' onclick='printDiv()' value='Print' />													
										</div>
										<div class="col-md-1">
											<button type="button" class='btn blue' id="btnExport" filename= "report" onclick="tabletoExcel();"> EXPORT </button>
																							
										</div>
							</div>
							
							  <div id="header" class="portlet-body" style="display: none; visibility: hidden;">
										<h4 style="margin-left:350px;">Bus Stop Wise Vehicles Report</h4>
									<br />
									<div id="headerdetails" style="margin-left:0px;">
									 <table ID="mytable" style="width:60%;border:none;">

														
														
													</table>
                            	      

													 <br/><br/> 
                                    
                                </div>
                                </div>
                                
                                
                                <div class="portlet-body" id="viewRole12"
								style="visibility: hidden">
                        <div id="skipstopTable">
								<table class="table table-striped table-bordered table-hover"
									id="showBusStopWiseData">
									<thead>
										<tr>
											
											<th>Sr. NO</th>
												<th>Device Id</th>
												<th>Vehicle No.</th>
												<th>Schedule No.</th>
												<th>Speed(KMPH)</th>
												<th>IST Date</th>
												<th>Depot Name</th>
<!-- 												<th>Direction</th> -->
											
											
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


function getSkipStop() {
	
	
	var startdate = document.getElementById("startdate").value;

	var enddate = document.getElementById("enddate").value;
	 var date1= moment(startdate).format('DD-MM-YYYY-HH-mm');
	 var date2= moment(enddate).format('DD-MM-YYYY-HH-mm');
	 var data1=date1.split("-");
	 var dd1=data1[0];
	 var mm1=data1[1];
	 var yy1=data1[2];
	 var hh1=data1[3];
	 var min1=data1[4];
	 var d1 = new Date(yy1,mm1,dd1,hh1,min1,0,0);  //Date object creation in JS
//		 alert("");
	 
	 var data2=date2.split("-");
	 var dd2=data2[0];
	 var mm2=data2[1];
	 var yy2=data2[2];
	 var hh2=data2[3];
	 var min2=data2[4];
	 var d2 = new Date(yy2,mm2,dd2,hh2,min2,0,0);   //Date object creation in JS
	 
	 var diff = d2.getTime() - d1.getTime();     // in milli second
	 var hDiff = diff / 3600 / 1000;              // in hours

	
	if(d1>d2){
		bootbox.alert("Start Time should not be greater than to End Time");
		return false;
	}
	if(hDiff>1/2){
		 bootbox.alert("Start Time and End Time should not be greater than Half and hour");
		return false;
	}
	if($('#projectsource-id').val()==0){
		bootbox.alert("Please select Bus Stop");
		return false;
	}
	
	
	
	var dd1=$("#startdate").val();
//	alert(dd1);
var dd2=$("#enddate").val();
var busStopName=$('#projectsource').val();
var busStopId=$('#projectsource-id').val();
// alert("busStopId"+busStopId+"---"+busStopName);
			
				var divId = document.getElementById("viewRole12");
				divId.style.visibility = 'visible';
				
			
			$('#showBusStopWiseData').dataTable({
						"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
						                  
						],   
						
						"iDisplayLength" : 5,
						"sAjaxSource" : "getOnlineVehicleBusStop.action?startdate="+dd1+"&enddate="+dd2+"&busStopId="+busStopId,
						"sPaginationType" : "bootstrap",
						"bDestroy" : true,
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
						                  
						
	jQuery('#showBusStopWiseData_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	jQuery('#showBusStopWiseData_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline");
			
		
	}
	
	function printDiv() {     
		  document.getElementById("skipstopTable").style.visibility='visible';  
	     document.getElementById("header").style.display='block';
	     document.getElementById("header").style.visibility='visible'; 
	    
	     
	     var divElements = document.getElementById("header").innerHTML;
	     divElements+= document.getElementById("skipstopTable").innerHTML;
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
// 			document.getElementById("mapshow").style.visibility=''; 
// 			$(".mapClass").show();
	     
	             
	 }
	
	function getRoute(val){
		var result = "";
		var availableTags=[];	
// 		 $('#startdate').hide();
// 		$('#enddate').hide(); 
		$.ajax({
		    type : 'GET',
		    data :'json',
		    url  :  "<s:url action='BusStopdropDownList1'/>",
		    data :{
		    	stopName: val,
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


	
		
		function tabletoExcel(btnExport){     
          	var htmltable= document.getElementById('btnExport');   
            $( "#header-fixed" ).remove();
            var inputHTML = "<table border='1'>";
            inputHTML += "<tr>";
            inputHTML += "<th  align='left'colspan='9'>Bangalore Metropolitan Transport Corporation</th>";
            inputHTML += "</tr>";
            inputHTML += "<th  align='left'colspan='9'>Bus Stop Wise Online Vehicles List </th>";
            inputHTML += "</tr>";
            inputHTML += "<tr>";
            inputHTML += "<th colspan='2' align='left'>Date Range:</th>";
            inputHTML += "<th colspan='7' align='left'>" + $("#startdate").val() + " to " + $("#enddate").val() + "</th>";
            inputHTML += "</tr>";
            inputHTML += "</table>";
            var htmltable = document.getElementById("skipstopTable");
            var html = inputHTML + htmltable.outerHTML;
            var downloadLink = document.createElement("a");
            downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
            downloadLink.download = "Bus Stop Wise Online Vehicles.xls";
            document.body.appendChild(downloadLink);
            downloadLink.click();
            document.body.removeChild(downloadLink);
        }
		
	
		
		
</script>
