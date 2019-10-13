  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
<%--  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" /> --%>
 
<script>


	function getDepot(orgId){
		// $('#select2-chosen-2').html("Select");
		// $('#select2-chosen-3').html("Select");
			//alert('Here');
			 /* var selectedValue = $('#form-control').val(); */
			 var val=document.getElementById('divisionlist').value;
				 if(val!=0) {
		        $.ajax({
		            type: "post",
		            url: '<%=request.getContextPath()%>/getDepotList?val=' + val,
					success : function(result) {
						document.getElementById('depotlist1').innerHTML = result;
						
					}
				});
			}

		}

</script>
<script>
function getdepotwisevehicle(orgname){
	//$("acc66axisnotsett").empty();
	$('#vehicledetails').attr("style", "display:''");
	//var divid=$("#divisionlist").val();
    table = $('#vehicledetailstable');
    var oTable = table.dataTable({
    	"aLengthMenu" : [ [ 5,10, 15, 20, -1 ], [ 5,10, 15, 20, "All" ] // change
		// per
		// page
		// values
		// here
		],
		// set the initial value
		"iDisplayLength" : 5,
		"sAjaxSource" : "ajaxgetvehmappdepotdata.action?orgname="+orgname,
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

	jQuery('#clskmtable_wrapper .dataTables_filter input').addClass(
			"form-control input-xsmall input-inline"); // modify table
	// search input
	jQuery('#clskmtable_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");	
}
</script>
 <script>
	function getheader() {
		
		document.getElementById("startdate").innerHTML = new Date().toJSON().slice(0,10);
		}
 </script>
<script>

function getData(){
	//$("acc66axisnotsett").empty();
	//alert("hi");
	$('#vehmapstatus').attr("style", "display:''");
	var divid=$("#divisionlist").val();
    table = $('#vehmapstatustable');
    var oTable = table.dataTable({
    	"aLengthMenu" : [ [ 5,10, 15, 20, -1 ], [ 5,10, 15, 20, "All" ] // change
		// per
		// page
		// values
		// here
		],
		// set the initial value
		"iDisplayLength" : 5,
		"sAjaxSource" : "ajaxgetvehmappdata.action?divid="+divid,
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

	jQuery('#clskmtable_wrapper .dataTables_filter input').addClass(
			"form-control input-xsmall input-inline"); // modify table
	// search input
	jQuery('#clskmtable_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");	
}

function getdepotwiseexceptionreport(vehiclenumbe,orgname) {
	//alert(vehiclenumbe);
	$('#vehiclenumber').val(vehiclenumbe);
	$('#orgname').val(orgname);
// 	$('#deviceidentification').val(deviceid);
// 	$('#phonenumber').val(reason);
// 	$('#subreasonnumber').val(subreason);
// 	$('#descripnum').val(descrip);
// 	$('#readate').val(reasondate);
	
 
}

function savedata(){
	//alert("saveddata entry");
	  var vehiclenumber=$("#vehiclenumber").val();
	  var orgname=$("#orgname").val();
	  
		 
	   var resdescrip=$("#resdescrip").val();
	   
	if(resdescrip==""){alert("please enter Reason");}
        else {
//    		 $('#pageLoader1').show();
   		// alert("before inssert");
         $.ajax({
         
             type: "post",
             url: '<%=request.getContextPath()%>/AjaxSaveNotMappedReason.action?vehicleno='+vehiclenumber+'&reason='+resdescrip,
            	 success: function(result) {
                 	$('#pageLoader1').hide();
                     document.getElementById('ticketconsuptionid1').innerHTML=result;
                    // fixHeader();
                     //getdata();
                     getdepotwisevehicle(orgname);
             }
         });
//document.getElementById('ticketconsuptionid1').innerHTML="Record Inserted";
        
       // $("#vehmapstatus").close();
        $('#mymodal1').modal('hide');
        //$('#mymodal11').modal('hide');
       // getdata();
		}//alert("before calling");
/*   getdata();
hideing();  */ 
//alert("after calling");
}
function callCancell() {

	window.location.href = 'vehiclemappingstatus';
	
}

</script>
<script>
		
function printDiv() {     
	  document.getElementById("vehmapstatus").style.visibility='visible';  
	     document.getElementById("header").style.display='block';
	     document.getElementById("header").style.visibility='visible'; 
	    
	     
	     var divElements = document.getElementById("header").innerHTML;
	     divElements+= document.getElementById("vehmapstatus").innerHTML;
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
								<i class="fa fa-globe"></i>Vehicle Mapping Status
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
										<div class="col-md-4">
											<s:select list="divisionlist" id="divisionlist"
												name="orgchart.org_chart_id"
												cssClass="select2_category form-control" headerKey="0" headerValue="---All---"
												 ></s:select>
										</div>
									</div>
								</div>
								
                     <%--           <div class="form-group">
									<label class="col-md-3 control-label">Depot<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select list="depotlist" id="depotlist1" class="select2_category form-control" name="depotlist1"> 
											<option value="0">--All--</option>
 										</select> 
									</div>
 								</div>  --%>
 					
						<!-- end -->
						
					
                     </div>
                     </form>
				
					 <div align='center'>
					<button type="submit" class="btn blue" onClick="getData(),getheader()">Submit</button>
					<span><input type='button' class='btn blue' id='button1' onclick='printDiv()' value='Print' /></span>&nbsp;
					<button type="submit" class="btn blue" onClick="tabletoExcel()">Export</button>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
							<div class="portlet-body" id="vehmapstatus"
														style="display: none">
														<div style="text-align:left">
														<table
															class="table table-striped table-bordered table-hover"
															id="vehmapstatustable">
															<thead>
	<tr>
	<th>Sr No.</th><th>Depot</th>
	<th><center>Total vehicle</center></th><th><center>Device Not Mapp</center></th>	<th><center>Sim Not Mapp</center></th>
		<th><center>D/S Not Mapp</center></th><!-- <th><center>Total</center></th> -->	
	</tr>
									
																
															</thead>
														</table>
													</div>
                                                   </div>
			</div>
			
			<!-- END PAGE CONTENT-->
			
			
		
		</div>
	</div>
	
		<div style="display: none;" id="mymodal11" class="modal fade"
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
									<font color="green"><h3><div id="ticketconsuptionid1"></div></h3></font> 
										<div>
											<input type="hidden" name="requestType" id="requestType" />
											<div>
												<div class="portlet solid white">
													<div class="form-group">
														<label class="col-md-3 control-label"></label>
													</div>
													<div class="portlet-body" id="vehicledetails"
														style="display: none">
														<div style="text-align:center">
														<table
															class="table table-striped table-bordered table-hover"
															id="vehicledetailstable">
															<thead>
																<tr>
																	<th>Sr. NO</th>
												<th>Vehicle</th>
												<th>Device No</th>
												<th>Sim No</th>
												<th>Status</th>
												<th>Reason</th>
											
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
					
					
					 <div style="display: none;" id="mymodal1" class="modal fade"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
		aria-hidden="true">
		<div class="modal-dialog" id="mod21">
			<div class="modal-content" style="width: 600px; height: 630px;"
				align="justify">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 id="vehicleno123" class="modal-title"></h4>
				</div>
				<div id="pageLoader1" class="modal fade in" tabindex="-1" role="dialog" aria-labelledby="myModalLabel3" aria-hidden="false">
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
				<div>
					<p>
					<div class="portlet box white ">
						<div>
						<h4><span class="help-block" style="color: green; list-style: none"><div id="ticketconsuptionid1"></div></span></h4>
							<input type="hidden" name="shift" id="shift" />
	
							<div>
<!-- 							<input type="hidden" class="form-control" id="deviceidentification" -->
<!-- 													name="deviceid7" value=''></input> -->
<!-- 													<input type="hidden" class="form-control" id="phonenumber" -->
<!-- 													name="phone7" value=''></input> -->
													<input type="hidden" class="form-control" id="vehiclenumber"
													name="vehicle7" value=''></input>
													<input type="hidden" class="form-control" id="orgname"
													name="orgnameid7" value=''></input>
								<div class="portlet solid white">
									<div class="form-group">
										<label class="col-md-3 control-label"></label>
									</div>
									<div class="form-group"><form  method="post" class="form-horizontal">
									<table id ="tableid1">		
									<div>
<!-- 									<input type="hidden" name="scheduleId" id="scheduleId" /></div>						 -->
									
								
								<div class="form-group" id="resDescreption">
									<label class="col-md-3 control-label">Reason :<font
										color="red"></font></label>
										<div class="form-group">
								
										<div class="col-md-4"> 
										<textarea  class="form-control" id=resdescrip maxlength="100"	 autofocus="autofocus" name="description">
													<s:property value="notes" /></textarea>	
					          </div>
				            </div>
											</div>
								
											</table></form>
						   <div align='center' id="submitbutten">
					<button type="button" class="btn green" onclick="savedata();">Save</button> 
					<button type="button" id="btnExport" class="btn green" onclick="callCancell();">Cancel </button>
                   <%-- <span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span>&nbsp;<span>
					 <input type='button' class='btn green' id='button1' name='rawprint' onclick='rawPrint()' value='RawPrint' /></span></div> --%>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>  
						</div></div>			</div>
								</div>
							</div>
						</div>
						</p>
					</div>
				</div>
			</div>
					
					
					
					
					
					
					
					
					
		<div id="header" class="portlet-body" style="display: none; visibility: hidden;">
 <h3 style="margin-left:350px;">Vehicle Mapping Status</h3>
<br />
<b><font size="2"><label>Date: </label></font><font size="2"><b><span id="startdate" style="text-align:left;margin-left:50px;"></span></font></b>

</div>
	<script type="text/javascript">
    function tabletoExcel(btnExport){     
    	/*  document.getElementById("mapshow").style.visibility='hidden'; 
    		$(".mapClass").hide(); */
   	     
    	     var inputHTML = "<table border='1'>";
             inputHTML += "<tr>";
             inputHTML += "<th  align='center'colspan='8'>Bangalore Metropolitan Transport Corporation</th>";
             inputHTML += "</tr>";
            inputHTML += "<tr>";
             inputHTML += "<th  align='Center'colspan='8'>Vehicle Mapping Status</th>";
             inputHTML += "</tr>";
           
             inputHTML += "</table>";
    		   document.getElementById("vehmapstatus").style.visibility='visible';  
    	     document.getElementById("header").style.display='block';
    	     document.getElementById("header").style.visibility='visible';     
    	     var divElements = inputHTML+document.getElementById("header").innerHTML;
    	     divElements+= document.getElementById("vehmapstatus").innerHTML;
     
     var noOfTableExist = 0;
     try{
 		$("#vehmapstatus table").each(function(){
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
    downloadLink.download = "Vehicle Mapping Status.xls";
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
}
	</script>