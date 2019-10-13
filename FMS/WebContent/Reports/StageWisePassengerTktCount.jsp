
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>

<head>
<script src="assets/global/plugins/jquery-1.11.0.min.js" value='1'
	type="text/javascript"></script>
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.9.1/themes/black-tie/jquery-ui.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/js/stickyheader.css" />

<script>

<%-- function getRoute(val){
    $.ajax({
        type: "post",
        url: '<%=request.getContextPath()%>/getRouteList?routename='+val,
		success : function(result) {
// 		console.log("data---"+result)
			document.getElementById('routeId').innerHTML = result;
			
		}
	});
}  --%>

function validate()
{
	var depotId=$("#divisionlist").val();
	var routeId=$("#project").val();
	var stage1Id=$("#stage1").val();
    var	stage2Id=$("#stage2").val();
  
  
   
	
	}


function getStageWiseData(){
	
	var depotId=$("#divisionlist").val();
	var routeId=$("#project").val();
	var stage1Id=$("#stage1").val();
    var	stage2Id=$("#stage2").val();
    var fromdate=$("#startdate").val();
    var todate=$("#endate").val();

//      validate();

    if(depotId==0){
    	bootbox.alert("Please Select Depot Name");
		return false;
	}
    
    if(routeId==0){
    	bootbox.alert("Please Select Route Name");
		return false;
	}
    if(stage1Id==0){
    	bootbox.alert("Please Select From Stage");
		return false;
	}
    if(stage2Id==0){
    	bootbox.alert("Please Select End Stage");
		return false;
	}



    $('#pageLoader').show();
        $.ajax({
            
            type: "post",
            url: '<%=request.getContextPath()%>/getStagewisePassengerTicketCountDetails.action?depotId='+depotId+'&routeId='+routeId+'&fromStageId='+stage1Id+'&toStageId='+stage2Id+'&date1='+fromdate+'&date2='+todate,
            success: function(result) {
            	$('#pageLoader').hide();
                document.getElementById('ticketid').innerHTML=result;
                fixHeader();
            }
        }); 

}

function tabletoExcel(btnExport){   
	 
    var htmltable = document.getElementById("ticketid");
    var html = htmltable.outerHTML;
    
	var noOfTableExist = 0;
	try {
		$("#ticketid table").each(function() {
			noOfTableExist++;
		});

		console.log("Total no of tables : " + noOfTableExist);
		/* If two table exist  then remove the last table on print click (for header fixed)*/
		if (noOfTableExist >= 2) {
			html = html.substring(0, html
					.indexOf("</table>") + 8)
					+ "</div></div>";
		}
	} catch (err) {
		console.log("ExceptionCaught : " + err);
	}
    
    var downloadLink = document.createElement("a");
    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
    downloadLink.download = "Stage Wise Passenger/TicketReport.xls";
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
  }




</script>

<script>
		
function printDiv() {     
    
    //  document.getElementById("print").style.visibility='hidden';     
    var divElements = document.getElementById("header").innerHTML;
    divElements += document.getElementById("ticketid").innerHTML;
    
    var noOfTableExist = 0;
    try{
		$("#ticketid table").each(function(){
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

    var mywindow = window.open("<%=request.getContextPath()%>/Print.jsp");
   
    mywindow.onload = function() {
    mywindow.document.body.innerHTML=divElements;
    mywindow.document.body.innerHTML=divElements;
    mywindow.print();
    mywindow.close();
    }
            
}

function getRoute(val){
	var result = "";
	var availableTags=[];	
	$('#startdate').hide();
	$('#endate').hide();
	$.ajax({
	    type : 'GET',
	    data :'json',
	    url  :  "<s:url action='getRouteLists'/>",
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

/* 
function getStage1(val){
	var result = "";
	var availableTags=[];	
	var routeId = $("#project").val();
alert("route ---"+routeId);	
	$.ajax({
	    type : 'GET',
	    data :'json',
	    url  :  "<s:url action='getStageList'/>",
	    data :{
	    	routename: val,
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
 */
function getStage1(val){
	// $('#select2-chosen-2').html("Select");
	// $('#select2-chosen-3').html("Select");
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 var routename=document.getElementById('project').value;
// 		 alert("id is"+val);
		 
		 $('#startdate').show();
	$('#endate').show();
		 
			 if(val!=0) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/getStageList?routename=' + val,
				success : function(result) {
					document.getElementById('stage1').innerHTML = result;
					document.getElementById('stage2').innerHTML = result;
					
				}
			});
		}

	}


function getStage2(val){
	var result = "";
	var availableTags=[];	
	$.ajax({
	    type : 'GET',
	    data :'json',
	    url  :  "<s:url action='TripPlannerDownList1'/>",
	    data :{
	    	routename: val,
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


</script>

<div class="page-content-wrapper">
	<div class="page-content">
		<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
		<div class="modal fade" id="portlet-config" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true"></button>
						<h4 class="modal-title">Modal title</h4>
					</div>
					<div class="modal-body">Widget settings form goes here</div>
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

		<div id="pageLoader" class="modal fade in" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel3" aria-hidden="false">
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


		<!-- END PAGE HEADER-->
		<!-- BEGIN PAGE CONTENT-->


		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN EXAMPLE TABLE PORTLET-->
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i>StageWise passenger/Ticket Count Report
						</div>
					</div>

					<div class="portlet-body">
						<form action="" method="post" class="form-horizontal">
							<div class="form-body">
				<%-- 				<div class="form-group">
									<label class="control-label col-md-3">Month :</label>
									<div class="col-md-3">
										<div class="input-group input-medium date date-picker"
											style="width: auto" data-date-format="mm-yyyy"
											data-date-viewmode="years">
											<input id="startdate" class="form-control" type="text"
												readonly="" size="16" name="rateMaster.effectiveStartDate"
												value="<s:property value='rateMaster.effectiveStartDate' />"><span
												class="input-group-btn">
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
											<script>
										var curr_date = new Date().toJSON().slice(0,10);
											curr_date = curr_date.split("-");
											curr_date = curr_date[1]+ "-"+ curr_date[0];
											var dtval = document.getElementById('startdate').value;
											if (dtval == '') {
												$('#startdate').val(curr_date);
												
											}
												</script>
										</div>
									</div>
								</div> --%>
								
								
								<div class="form-group">
										<label class="col-md-3 control-label">Depot :<font
											color="red">*</font></label>
										<div class="col-md-4">
											<s:select list="divisionlist" id="divisionlist" 
											name="org_chart_id"  
												cssClass="select2_category form-control"  
												  headerKey="0" headerValue="--select--"></s:select>  
												 
										</div>
									</div>
								

								<div class="form-group">
									<label class="control-label col-md-3">Route Number :</label>
									<div class="col-md-3">
										<input class="form-control" tabindex="1" id="project"
											placeholder="Enter Route No to Search" name="project" 
									
											type="text" onkeyup="getRoute(this.value)"  onChange="getStage1(this.value)"
											 /> <input id="project-id"
											type="hidden"><input id="project-id1" type="hidden"><input
											id="project-id2" type="hidden">
									</div>

								</div>
<!-- 									<div class="tab-content"> -->
<!-- 											<div class="tab-pane active"   id="tab_0"> -->
<!-- 												<div class="form-group"> -->
<!-- 													<label class="col-md-2 control-label">From Stage</label> -->
<!-- 													<div class="col-md-3"> -->
<!-- 														<input class="form-control" tabindex="1" placeholder="Enter Stage to Search" id="projectsource" name="projectsource" type="text"   /> -->
<!-- 														<input id="projectsource-id" type="hidden"> -->
<!-- 								 						<input id="projectsource-id1" type="hidden"> -->
<!-- 								 						<input id="projectsource-id2" type="hidden"> -->
<!-- 													</div> -->
<!-- 													<label class="col-md-2 control-label">To Stage</label> -->
<!-- 													<div class="col-md-3"> -->
<!-- 														<input class="form-control" tabindex="1" placeholder="Enter stage to Search" id="projectdest" name="projectdest" type="text"	onkeyup="getStage2(this.value)"   /> -->
<!-- 														<input id="projectdest-id" type="hidden"> -->
<!-- 								 						<input id="projectdest-id1" type="hidden"> -->
<!-- 								 						<input id="projectdest-id2" type="hidden"> -->
<!-- 													</div> -->
<!-- <!-- 														<button type="button" class="btn default" onclick="getRouteDataMap(0)" style="position: static;">Submit</button> --> 
<!-- <!-- 														<button  type='button' class="btn grey" onclick="getRouteDataMap(1);"> Refresh</button> --> 
<!-- 														</div> -->
		
											
<!-- 											</div> -->
											
										
<!-- 										</div> -->

	                           <div class="form-group">
									<label class="col-md-3 control-label">From Stage :</label>
									<div class="col-md-3">
										<select id="stage1" class="select2_category form-control" >
											<option value="0">Select From Stage</option>
										</select>
									</div>
								</div>
								
								  <div class="form-group">
									<label class="col-md-3 control-label">To Stage :</label>
									<div class="col-md-3">
										<select id="stage2" class="select2_category form-control" >
											<option value="0">Select To Stage</option>
										</select>
									</div>
								</div>


		<table>
							  <tr>
							  <td><label class="control-label col-md-4">Start Date:</label>
								<div class="col-md-4">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" >

								<input id="startdate" class="form-control" type="text" readonly="" size="16" name="effectiveStartDate"
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
							  
							  <td><label class="control-label col-md-3">Till Date:</label>
								<div class="col-md-4">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" >
								<input id="endate" class="form-control" type="text" readonly="" size="16" name="effectiveStartDate"
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
								</div></td> 

							  </tr>
							  <tr><td></td></tr>
							</table>


							

							</div>
							
						</form>
						
						<div align="center">
								<button type="submit" class="btn green"
									onClick="getStageWiseData()">Submit</button>
								<span><input type='button' class='btn green' id='button1'
									onclick='printDiv()' value='Print' /></span>&nbsp;
									<button type="button" class="btn green" id="btnExport" filename= "report" onclick="tabletoExcel();"> EXPORT </button>
							</div>
					</div>
				</div>

				<!-- END EXAMPLE TABLE PORTLET-->
<!-- 			</div> -->
			<div id="ticketid"></div>
		</div>
</div>
		<!-- END PAGE CONTENT-->
	</div>
</div>