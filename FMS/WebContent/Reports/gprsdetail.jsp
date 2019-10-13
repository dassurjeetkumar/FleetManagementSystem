  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
 
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
  function onchangedate(date){
	 // $('#enddate').datepicker('setStartDate', date);
	     // alert();
	     
	  $('#startdate').datepicker({
		    
		// update "toDate" defaults whenever "fromDate" changes
		}).on('changeDate', function(){
		    // set the "toDate" start to not be later than "fromDate" ends:
		    $("#enddate").datepicker('setStartDate', '02-08-2018');
		});
} 
    
    $(document).ready(function(){

/*         $("#startdate").datepicker({
            todayBtn:  1,
            autoclose: true,
        }).on('changeDate', function (selected) {
            var minDate = new Date(selected.date.valueOf());
            $('#enddate').datepicker('setStartDate', minDate);
        });
alert(1);
        $("#enddate").datepicker()
            .on('changeDate', function (selected) {
                var maxDate = new Date(selected.date.valueOf());
                $('#startdate').datepicker('setEndDate', maxDate);
            }); */

    });
 
</script>
<script>
function getdepotwise(orgname,date1,date2){
	//alert(orgname +"---"+date1+"-----"+date2);
	//$("acc66axisnotsett").empty();
	$('#detrev').attr("style", "display:''");
	//var divid=$("#divisionlist").val();
    table = $('#detrevtable');
    var oTable = table.dataTable({
    	"aLengthMenu" : [ [ 5,10, 15, 20, -1 ], [ 5,10, 15, 20, "All" ] // change
		// per
		// page
		// values
		// here
		],
		// set the initial value
		"iDisplayLength" : 5,
		"sAjaxSource" : "ajaxgetdepotwiserevcount.action?orgname="+orgname+"&date1="+date1+"&date2="+date2,
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
		//document.getElementById("startdat").innerHTML = new Date().toJSON().slice(0,10);
		var startdate=$("#startdate").val();
		var enddate=$("#enddate").val();
	
		document.getElementById("startdat").innerHTML = startdate;
		document.getElementById("enddat").innerHTML = enddate;
	}
 </script>
<script>

function getData(){
	//$("acc66axisnotsett").empty();
	
	var startdate=$("#startdate").val();
	var enddate=$("#enddate").val();

	
 	var var1=$("#startdate").val();
		var1=var1.split("-");
	var1=var1[2]+"-"+var1[1]+"-"+var1[0];
		var var2=$("#enddate").val();
		var2=var2.split("-");
		var2=var2[2]+"-"+var2[1]+"-"+var2[0]; 
	//alert(var1+""+var2);
	
 	 var d1 = Date.parse(var1);
	 var depot=$("#depotlist").val();
	 
	var d3=Date.parse(var2); 
	
	
	
	if(startdate=='' || startdate==null){
		alert("please select Form Date");
	}else 	if(enddate=='' || enddate==null){
		alert("please select end Date");
	}
	else if (d1>d3){
		alert("end date should more than start date");
	}
	else {
		$('#getdatafortpr').attr("style", "display:''");
    table = $('#getdatafortprtable');
    var oTable = table.dataTable({
    	"aLengthMenu" : [ [ 5,10, 15, 20, -1 ], [ 5,10, 15, 20, "All" ] // change
		// per
		// page
		// values
		// here
		],
		// set the initial value
		"iDisplayLength" : 5,
		"sAjaxSource" : "ajaxgetdataforgprs.action?startdate="+startdate+"&enddate="+enddate,
		"sPaginationType" : "bootstrap",
		"bDestroy" : true,
		"bSort" : true,
		"bFilter" : false,
		"bSelect" : true,
		"bPaginate" : false,
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

	jQuery('#getdatafortprtable_wrapper .dataTables_filter input').addClass(
			"form-control input-xsmall input-inline"); // modify table
	// search input
	jQuery('#getdatafortprtable_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");	
}
}
</script>
<script>
		
function printDiv() {     
	  document.getElementById("getdatafortpr").style.visibility='visible';  
	     document.getElementById("header").style.display='block';
	     document.getElementById("header").style.visibility='visible'; 
	    
	     
	     var divElements = document.getElementById("header").innerHTML;
	     divElements+= document.getElementById("getdatafortpr").innerHTML;
	     divElements = divElements.replace(/#mymodal11/g, "");
	 		divElements = divElements.replace(/href/g, "");

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


function printDivpop() {     
	  document.getElementById("detrev").style.visibility='visible';  
	     document.getElementById("header").style.display='block';
	     document.getElementById("header").style.visibility='visible'; 
	    
	     
	     var divElements = document.getElementById("header").innerHTML;
	     divElements+= document.getElementById("detrev").innerHTML;
	 		
 	   /*   divElements = divElements.replace(/records/g, "");
	     divElements = divElements.replace(/Search:/g, "");  
	     divElements = divElements.substring(0, divElements.indexOf("Showing")-7);
	      */
	     var divElementslist = divElements.substring(0,divElements.indexOf("</table>"));
	      divElementslist += divElements.substring(divElements.indexOf("table-scrollable")-28,divElements.lastIndexOf("</tbody></table>"));
	     /*  divElementslist = divElementslist.replace(/records/g, "");
	      divElementslist = divElementslist.replace(/Search:/g, ""); 
	      divElementslist = divElementslist.substring(0, divElements.indexOf("Showing")-7); */
	     console.log(divElementslist);
	     
	    
	     var mywindow = window.open("<%=request.getContextPath()%>/Print1.jsp");
	     mywindow.onload = function() {
	         mywindow.document.body.innerHTML=divElementslist;
	         mywindow.document.body.innerHTML=divElementslist;
	         
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
								<i class="fa fa-globe"></i>Ticket,Revenue and Passenger Count
							</div>
						</div>
						
						<div class="portlet-body">
<!-- 						


							<!-- 						start -->

                     <form action="" method="post" class="form-horizontal">
                        <div class="form-body">
								   						
									<div class="form-group">
							  <label class="control-label col-md-3">From Date:<font
										color="red">*</font></label>
								<div class="col-md-3">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" data-date-start-date="25-07-2018" data-date-end-date="-2d"> 
								<input id="startdate" name="dateaction" class="form-control" type="text" readonly="" size="16" name="rateMaster.effectiveStartDate" placeholder="Pick The From Date"  onchange="onchangedate(this.value)"  "
								value="<s:property value='rateMaster.effectiveStartDate' />"
								>
								<span class="input-group-btn">
								<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
								</button>
								</span>
				<%-- 				<script>
 										var curr_date=new Date().toJSON().slice(0,10);
                                        curr_date=curr_date.split("-");
                                         curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
                                        var dtval=document.getElementById('startdate').value;	
                                        
                                         if(dtval==''){
                                         $('#startdate').val(curr_date);
                                        }
								</script> --%>
								</div>
								</div></div>
											
									<div class="form-group">
							  <label class="control-label col-md-3">To Date:<font
										color="red">*</font></label>
								<div class="col-md-3">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" data-date-start-date="25-07-2018"  data-date-end-date="-2d" > 
								<input id="enddate" name="dateaction" class="form-control" type="text" readonly="" size="16" name="rateMaster.effectiveStartDate" placeholder="Pick The End Date" "
								value="<s:property value='rateMaster.effectiveStartDate' />"
								>
								<span class="input-group-btn">
								<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
								</button>
								</span>
					<%-- 			<script>
 										var curr_date=new Date().toJSON().slice(0,10);
                                        curr_date=curr_date.split("-");
                                         curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
                                        var dtval=document.getElementById('enddate').value;	
                                        
                                         if(dtval==''){
                                         $('#enddate').val(curr_date);
                                        }
								</script> --%>
								</div>
								</div></div>
 					
						<!-- end -->
						
					
                     </div>
                     </form>
				
					 <div align='center'>
					<button type="submit" class="btn blue" onClick="getData(),getheader()">Submit</button>
					<span><input type='button' class='btn blue' id='button1' onclick='printDiv()' value='Print' /></span>&nbsp;
					<button type="submit" class="btn blue" onClick="tabletoExcel()">Export</button>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
							<div class="portlet-body" id="getdatafortpr"
														style="display: none">
														<div style="text-align:left">
														<table
															class="table table-striped table-bordered table-hover"
															id="getdatafortprtable" width="500" border='1'>
															<thead>
	<tr>
	<th>SN</th><th><center>Depot</center></th>
	<th><center>Planned<br>Schedule</center></th>
	<th><center>Operated<br>Schedule</center></th>
	<th><center>Ticket<br>Count</center></th>
	<th><center>Passenger<br>Count</center></th><th><center>Amount(RS)</center></th>
	
		
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
							<div class="modal-content" style="width: 800px; height: 500px; overflow: scroll; "
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
														<div style="text-align:right">
														<span><input type='button' class='btn blue' id='button1' onclick='printDivpop()' value='Print' /></span>&nbsp;
					<button type="submit" class="btn green" onClick="tabletoExcelpop()">Export</button>
													</div>
													<div class="portlet-body" id="detrev"
														style="display: none">
														<div style="text-align:center">
														<table
															class="table table-striped table-bordered table-hover"
															id="detrevtable" width="500" border='1'>
															<thead>
																<tr>
																	<th>Sr. NO</th>
												<th>waybill no</th>
													<th>Date</th>
													
												<th>schedule</th>
												<th>schedule <br>name</th>
												<th>schedule <br>type</th>
												<th>bag no</th>
													<th>amt</th>
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
					
		<div id="header" class="portlet-body" style="display: none; visibility: hidden;">
<table  width="500" border='1' ><tr ><td  colspan='7'>
<center><div width="500px">
	<h3><span>Ticket,Passenger And Revenue Report</span></h3>
</div></center>
</td></tr>

<tr><td align='center'colspan='4'><center><b><font size="2"><label>From Date: </label></font><font size="2"><b><span id="startdat" style="text-align:left;margin-left:50px;"></span></font></b></center></td>

<td align='center'colspan='3'><center><b><font size="2"><label>To Date: </label></font><font size="2"><b><span id="enddat" style="text-align:left;margin-left:50px;"></span></font></b></center></td></tr>
</table></div><BR /><BR />
</div>
	<script type="text/javascript">
    function tabletoExcel(btnExport){     
    	//alert();
    	/*  document.getElementById("mapshow").style.visibility='hidden'; 
    		$(".mapClass").hide(); */
   	     
    	/*      var inputHTML = "<table border='1'>";
             inputHTML += "<tr>";
             inputHTML += "<th  align='center'colspan='8'>Bangalore Metropolitan Transport Corporation</th>";
             inputHTML += "</tr>";
            inputHTML += "<tr>";
             inputHTML += "<th  align='Center'colspan='8'>Ticket,Revenue and Passenger Count</th>";
             inputHTML += "</tr>";
           
             inputHTML += "</table>"; */
    		   document.getElementById("getdatafortpr").style.visibility='visible';  
    	     document.getElementById("header").style.display='block';
    	     document.getElementById("header").style.visibility='visible';     
    	     var divElements = document.getElementById("header").innerHTML;
    	     divElements+= document.getElementById("getdatafortpr").innerHTML;
     
     var noOfTableExist = 0;
     try{
 		$("#getdatafortpr table").each(function(){
 			noOfTableExist++;
 		});
 		
 		console.log("Total no of tables : " + noOfTableExist);
 		/* If two table exist  then remove the last table on print click*/
 	/* 	if(noOfTableExist >= 2){
 			divElements = divElements.substring(0, divElements.indexOf("</table>") + 8) + "</div></div>";
 		} */
 		//alert(1);
 		divElements = divElements.replace(/#mymodal11/g, "");
 		divElements = divElements.replace(/href/g, "");
 		//alert(1);
 	}catch(err){
 	    console.log("ExceptionCaught : " + err);
 	}
    
    var downloadLink = document.createElement("a");
    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(divElements);
    downloadLink.download = "Ticket,Revenue and Passenger Count.xls";
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
    //alert();
    $("#header").hide();
}
	</script>
		<script type="text/javascript">
    function tabletoExcelpop(btnExport){     
    	//alert();
    	/*  document.getElementById("mapshow").style.visibility='hidden'; 
    		$(".mapClass").hide(); */
   	     
    	/*      var inputHTML = "<table border='1'>";
             inputHTML += "<tr>";
             inputHTML += "<th  align='center'colspan='8'>Bangalore Metropolitan Transport Corporation</th>";
             inputHTML += "</tr>";
            inputHTML += "<tr>";
             inputHTML += "<th  align='Center'colspan='8'>Ticket,Revenue and Passenger Count</th>";
             inputHTML += "</tr>";
           
             inputHTML += "</table>"; */
    		   document.getElementById("detrev").style.visibility='visible';  
    	     document.getElementById("header").style.display='block';
    	     document.getElementById("header").style.visibility='visible';     
    	     var divElements = document.getElementById("header").innerHTML;
    	     divElements+= document.getElementById("detrev").innerHTML;
     
     var noOfTableExist = 0;
     try{
 		$("#getdatafortpr table").each(function(){
 			noOfTableExist++;
 		});
 		
 		console.log("Total no of tables : " + noOfTableExist);
 		/* If two table exist  then remove the last table on print click*/
 	/*  	if(noOfTableExist >=2){
 			//divElements = divElements.substring(0, divElements.indexOf("</table>"));
 	 		//Showing
 			divElements = divElements.substring(0, divElements.indexOf("Showing")-7);
 		}  */
 		//alert(1);
 	 	divElements = divElements.replace(/records/g, "");
 		divElements = divElements.replace(/Search:/g, ""); 
 		 			divElements = divElements.substring(0, divElements.indexOf("Showing")-7);
 		//alert(1);
 	}catch(err){
 	    console.log("ExceptionCaught : " + err);
 	}
   // console.log(divElements);
    var downloadLink = document.createElement("a");
    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(divElements);
    downloadLink.download = "Ticket,Revenue and Passenger Count Detail.xls";
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
    //alert();
    $("#header").hide();
}
	</script>