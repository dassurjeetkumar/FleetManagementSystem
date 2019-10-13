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
				<div class="portlet box grey-cascade">

					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-bars"></i> AVERAGE RUNING TIME FORMATE REPORT
						</div>
					
					</div>
					
					<div class="portlet-body form">

						<form action="#" class="form-horizontal form-row-seperated"
							method="post">
							

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
														 name="project" type="text"	onkeyup="getRoute(this.value)"/>
														 <input id="project-id" type="hidden">
								 						<input id="project-id1" type="hidden">
								 						<input id="project-id2" type="hidden">
														</div> 
					

										
										
                            </div>
                            
                            
                      

					

							<div class="form-actions fluid">
								<div class="col-md-offset-3 col-md-3">
									<button type="button" class="btn green" onclick="show_data();getheader();">Submit</button>
								</div>
								<div class="col-md-1"id="printbutton">
											<input type='button' class="btn green" id='button1' onclick='printDiv()' value='Print' />													
										</div>
										<div class="col-md-1">
											<button type="button" class='btn green' id="btnExport" filename= "report" onclick="tabletoExcel();"> EXPORT </button>
																							
										</div>
							</div>



                           <%--    <div id="header" class="portlet-body" style="display: none; visibility: hidden;">
										<h4 style="margin-left:350px;">AVERAGE RUNNING TIME FORMATE Report</h4>
									<br />
									<div id="headerdetails" style="margin-left:0px;">
									 <table ID="mytable" style="width:60%;border:none;">
<!-- 														<tr> -->
<!-- 									 						<td style="border:none;"><b><label><font size="2"></font>Division:</label></b> -->
<!-- 															<td style="text-align: left;"> -->
			 												<b><font size="2"><span  id="Divisionlistonheader" style="text-align: left;margin-left:10px;"></span></font></b></td>
<!-- 														</tr> -->
<!-- 														<tr> -->
<!-- 														<td style="border:none;"><b><label><font size="2"></font>Depot:</label></b> -->
<!-- 															<td style="text-align: left;"> -->
			 												<b><font size="2"><span  id="Depotlistonheader" style="text-align: left;margin-left:10px;"></span></font></b></td>
														
<!-- 														</tr> -->
														
														
													</table>
                            	      

													 <br/><br/> 
                                    
                                </div>
                                </div>  --%>
                          
							<div class="portlet-body" id="viewRole12"
								style="visibility: hidden">
                        <div id="tripstatus">
								<table class="table table-striped table-bordered table-hover"
									id="averagerunningtimeformateTable">
									<thead>
										<tr>
											
											<th>SR No.</th>	
											<th>ROUTE NO</th>										
											<th>NO DAYS</th>
											<th>DIST</th>
											<th>START POINT </th>
											<th>END POINT </th>
											<th>F-IV NO TRIPS</th>
											<th>F-IV AVG TIME IN MIN </th>
											<th>ACT NO TRIPS</th>
											<th>ACT AVG TIME IN MIN</th>
											<!-- <th>END Point</th>
											<th>Schedule type</th>
											<th>Depot</th> -->
											
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
//alert("hiiii");
		///var det=$("#projectsource-id").val();
		//var det1=$("#projectdest-id").val();
		//var busstop = $("#projectsource").val();
		var startdate = document.getElementById("startdate").value;
		var enddate = document.getElementById("enddate").value;
		var routeid=$("#project").val();
		//var schedulelist=$("#schedulelist").val();
		
		//alert(det);
		//alert(det1);
		

			var divId = document.getElementById("viewRole12");
			divId.style.visibility = 'visible';
			
		
		$('#averagerunningtimeformateTable')
		.dataTable(
				{
					"aaSorting" : [ [ 1, 'desc' ] ],
					"aLengthMenu" : [ [ 10, 50, 100 ], [ 10, 50, 100 ] 
					],
					
					"iDisplayLength" : 10,
					"bProcessing" : true,
					"bServerSide" : true,
					 "sAjaxSource" : "showaveragerunnigtimeformate.action?startdate="+startdate+"&enddate="+enddate+"&routeid="+routeid,
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
jQuery('#averagerunningtimeformateTable_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
jQuery('#averagerunningtimeformateTable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline");
		
	
}
</script>

<div id="header" class="portlet-body" style="display: none; visibility: hidden;">
 <h3 style="margin-left:350px;">Average Runnig Time Formate Report</h3>
<br />
<div id="headerdetails" style="margin-left:0px;">

<table ID="mytable" style="width:60%;border:none;">
				<tr>
						<td ><b><label><font size="2">ROute NO :</font></label></b>
					<!-- <td style="text-align: left;"> -->
						<b><font size="2"><span  id="routeiid" style="text-align: left;margin-left:10px;"></span></font></b></td>


				
					<td ><b><font size="2"><label> From Date: </label></font></b>
					<!-- <td style="text-align: left;"> -->
								 <b><font size="2"><span id="startdat" style="text-align:left;margin-left:10px;"></span></font></b></td>
				</tr>
				
					
					<td nowrap><b><font size="2"><label>To Date: </label></font><font size="2"><b><span id="enddat" style="text-align:left;margin-left:50px;"></span></font></b></td>
					<td nowrap></td>
				</tr>
			</table>
			 <br/><br/> 

</div>
</div> 

<script>

	
	
<%-- 	function getDepot(orgId){
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
	 --%>
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
	

	function getRoute(val){
		var result = "";
		var availableTags=[];	
		$.ajax({
		    type : 'GET',
		    data :'json',
		    url  :  "<s:url action='getroutedata'/>",
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
			        	$( "#project-id" ).val( ui.item.value );
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


	<%-- function getRoute1(val){
		$("#projectdest").val("");
		var result = "";
		var availableTags=[];	
		console.log("getRoute1 called");
		var routeid=document.getElementById('project').value;
		$.ajax({
		    type : 'GET',
		    data :'json',
		    url  :  "<s:url action='BusStopFixedDest'/>",
		    data :{
		    	routename: routeid,
		    	buststop:val,
		    },
		    success: function(data){
		        data =eval(data);
		      
		        result=data;
		        
		        console.log("My result data1 : " + JSON.stringify(result));
		        if(result != null && result.length > 0){
		        	console.log("My Result source : " + result[0]["busStopName"]);
			        $("#projectdest").val(result[0]["busStopName"]);
			        $("#projectdest-id").val(result[0]["bus_stop_code"]);
		        }else{
		        	console.log("No data returned from server1");
		        }
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
	
	
	function getDepot(orgId){
		 $('#select2-chosen-2').html("Select");
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
		function getSchedule()
		{
			//alert("hiii");
			//console.log("counter"+counter);
			$('#select2-chosen-3').html("Select");
			 var val=document.getElementById('depotlist1').value;
			// var selectedDate=document.getElementById("selecteddate").value;
			//alert
	     $.ajax({
	    	 
	         type: "POST",
	         url: '<%=request.getContextPath()%>/getScheduleForTripTiming?val=' + val,
				success : function(result) {
					
					document.getElementById('schedulelist').innerHTML = result;
					
					counter=0;
				}
			});
	     
	    
				 } --%>
		
		
		/* function tabletoExcel(btnExport){     
          	var htmltable= document.getElementById('btnExport');   
            $( "#header-fixed" ).remove();
            var inputHTML = "<table border='1'>";
            inputHTML += "<tr>";
            inputHTML += "<th  align='left'colspan='9'>Bangalore Metropolitan Transport Corporation</th>";
            inputHTML += "</tr>";
            inputHTML += "<th  align='left'colspan='9'>AVERAGE RUNNIG TIME FORMATE REPORT </th>";
            inputHTML += "</tr>";
            inputHTML += "<tr>";
//            inputHTML += "<th colspan='2' align='left'>Date Range:</th>";
//             inputHTML += "<th colspan='7' align='left'>" + $("#startdate").val() + " to " + $("#endate").val() + "</th>";
            inputHTML += "</tr>";
            inputHTML += "</table>";
            var htmltable = document.getElementById("tripstatus");
            var html = inputHTML + htmltable.outerHTML;
            var downloadLink = document.createElement("a");
            downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
            downloadLink.download = "AVERAGE RINNING TIME FORMATE REPORT.xls";
            document.body.appendChild(downloadLink);
            downloadLink.click();
            document.body.removeChild(downloadLink);
        } */
      /*   function tabletoExcel(btnExport){     
       	 /* document.getElementById("mapshow").style.visibility='hidden'; 
       		$(".mapClass").hide(); 
       		   document.getElementById("tripstatus").style.visibility='visible';  
       	     document.getElementById("header").style.display='block';
       	     document.getElementById("header").style.visibility='visible';     
       	     var divElements = document.getElementById("header").innerHTML;
       	     divElements+= document.getElementById("tripstatus").innerHTML;
        
        var noOfTableExist = 0;
        try{
    		$("#tripstatus table").each(function(){
    			noOfTableExist++;
    		});
    		
    		console.log("Total no of tables : " + noOfTableExist);
    		/* If two table exist  then remove the last table on print click
    		if(noOfTableExist >= 2){
    			divElements = divElements.substring(0, divElements.indexOf("</table>") + 8) + "</div></div>";
    		}
    	}catch(err){
    	    console.log("ExceptionCaught : " + err);
    	}}
       
       var downloadLink = document.createElement("a");
       downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(divElements);
       downloadLink.download = "Schedule Trip Status Report.xls";
       document.body.appendChild(downloadLink);
       downloadLink.click();
       document.body.removeChild(downloadLink);
   } */
		function getheader() {
			
		/* 	$("#printbutton").show();
			$("#scheduleprint").show();
			
		//alert("HII"+scheduleNo);
			document.getElementById("scheduleTripStatus").style.display = '';
			$("#schdeviation").hide();
			document.getElementById("scheduleTripStatus").style.display = ''; */
			var startdate = document.getElementById("startdate").value;
			var enddate = document.getElementById("enddate").value;
			var routeid=$("#project").val();
			
//	 		alert(depotName);
			$.ajax({
		           type: "get",
		           url: '<%=request.getContextPath()%>/getAveragerunnigHeaderData?startdate='+ startdate+'&enddate='+enddate+'&routeid='+routeid,
		           success: function(result) {
		        	   var obj = jQuery.parseJSON(result);
		        	   console.log("==>"+obj["bbData"][0]);
		        	    	   document.getElementById('routeiid').innerHTML=obj["bbData"][0];
		        	   document.getElementById('startdat').innerHTML=obj["bbData"][1];
		        	   document.getElementById('enddat').innerHTML=obj["bbData"][2];
						
		        	   
		           }
		       });}
	/* 
		function getRoute2(val){
		//	alert(val);
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
			        	var itemData = item.route_number.trim();
			        	
			        	return $( "<li>" )
			        	.append( '<a onclick="getRoute(\'' + itemData + '\');getRoute1(\'' + itemData + '\');">' + item.route_number + '</a>' )
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
 */
		
</script>
 	         	<script type="text/javascript">               	
    function tabletoExcel(btnExport){     
    	/*  document.getElementById("mapshow").style.visibility='hidden'; 
    		$(".mapClass").hide(); */
    		   document.getElementById("tripstatus").style.visibility='visible';  
    	     document.getElementById("header").style.display='block';
    	     document.getElementById("header").style.visibility='visible';     
    	     var divElements = document.getElementById("header").innerHTML;
    	     divElements+= document.getElementById("tripstatus").innerHTML;
     
     var noOfTableExist = 0;
     try{
 		$("#tripstatus table").each(function(){
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
    downloadLink.download = "Average Running Time Formate Report.xls";
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
}
    </script>