  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
 <script src="https://code.jquery.com/jquery-1.11.3.min.js" type="text/javascript"></script>
	<script src="https://cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/responsive/1.0.7/js/dataTables.responsive.min.js" type="text/javascript"></script>

<script>

function getData(){
	//$("acc66axisnotsett").empty();
	$('#servcietrip').attr("style", "display:''");
	var serviceid=$("#servicetypemap").val();
    table = $('#servcietriptable');
    var oTable = table.dataTable({
    	"aLengthMenu" : [ [ 5,10, 15, 20, -1 ], [ 5,10, 15, 20, "All" ] // change
		// per
		// page
		// values
		// here
		],
		// set the initial value
		"iDisplayLength" : 5,
		"sAjaxSource" : "ajaxgetserviceroutelist.action?serviceid="+serviceid,
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

	jQuery('#servcietriptable_wrapper .dataTables_filter input').addClass(
			"form-control input-xsmall input-inline"); // modify table
	// search input
	jQuery('#servcietriptable_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");	
}

</script>



<script>
		
function printDiv() {     
	  document.getElementById("servcietrip").style.visibility='visible';  
	     document.getElementById("header").style.display='block';
	     document.getElementById("header").style.visibility='visible'; 
	    
	     
	     var divElements = document.getElementById("header").innerHTML;
	     divElements+= document.getElementById("servcietrip").innerHTML;
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


</script>
 <script>
	function getheader() {
		
		document.getElementById("startdate").innerHTML = new Date().toJSON().slice(0,10);
		}
 </script>
<div class="page-content-wrapper">
	<div class="page-content">
		<div id="pageLoader" class="modal fade in" tabindex="-1" role="dialog" aria-labelledby="myModalLabel3" aria-hidden="false">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						
							<img src="assets/images/loader1.gif" align="top"
								style="margin-left: 100px;" />
						<br>
						<p style='text-align: center'>Please Wait........<br>
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
								<i class="fa fa-globe"></i>Service Wise Route Report 
							</div>
						</div>
						
						<div class="portlet-body">
						

							<!-- 						start -->

<!-- <table> -->
                   <form action="" method="post" class="form-horizontal">
                           <div class="form-body">
							
						<div class="form-group">
										<label class="col-md-3 control-label">Service Type<font
											color="red">*</font></label>
										<div class="col-md-3">
											<s:select list="servicetypemap" id="servicetypemap" 
											name="org_chart_id"  
												cssClass="select2_category form-control" ></s:select>  
												 
										</div>
									
									</div>
		
							
					<script>		
				    function tabletoExcel(btnExport){     
    	/*  document.getElementById("mapshow").style.visibility='hidden'; 
    		$(".mapClass").hide(); */
   	     
    	     var inputHTML = "<table border='1'>";
             inputHTML += "<tr>";
             inputHTML += "<th  align='center'colspan='8'>Bangalore Metropolitan Transport Corporation</th>";
             inputHTML += "</tr>";
            inputHTML += "<tr>";
             inputHTML += "<th  align='Center'colspan='8'>SERVICE WISE ROUTE LIST REPORT</th>";
             inputHTML += "</tr>";
           
             inputHTML += "</table>";
    		   document.getElementById("servcietrip").style.visibility='visible';  
    	     document.getElementById("header").style.display='block';
    	     document.getElementById("header").style.visibility='visible';     
    	     var divElements = inputHTML+document.getElementById("header").innerHTML;
    	     divElements+= document.getElementById("servcietrip").innerHTML;
     
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
    downloadLink.download = "Service Wise Route List Report.xls";
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
}	
		</script>			
							
					 <div class="form-group">
					 <label class="control-label col-md-3"></label>
                     <div class="col-md-3" id="">
                     </div>
                     </div>
                     </div>
                     </form>
                     <div align='center'>
					<button type="submit" class="btn green" onClick="getData(),getheader()">Submit</button> 
					<button type="button" id="btnExport" class="btn green" onclick="tabletoExcel();"> EXPORT </button>
					<button type="button" id="btnExport" class="btn green" onclick="printDiv();"> Print </button>
               <%--     <span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span> --%><%-- &nbsp;<span> --%>
<%-- 					 <input type='button' class='btn green' id='button1' name='rawprint' onclick='rawPrint()' value='RawPrint' /></span></div> --%>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
				
			
			
			<!-- END PAGE CONTENT-->
	
							<div class="portlet-body" id="servcietrip"
														style="display: none">
														<div style="text-align:left">
														<table
															class="table table-striped table-bordered table-hover"
															id="servcietriptable">
															<thead>
													<tr>
													<th>Sr<br>No</th>
													
													<th>Route<br>No</th>
													<th>No Of <br>Schedules</th>
													<th>Number of<br>Trips</th>
													
													<th>From</th>
													<th>To</th>
													<th>direction</th>
													<th>Route<br>Distance</th>
													<th>No of<br>Stages</th>
												<!-- 	<th>Its Ticket no</th> -->
													<th>No pf<br>Stops</th>
													
													<th>Service<br>Type</th>
		
				</tr>
																
															</thead>
														</table>
													</div>
                                                   </div>
					

					
		
					
					
					</div>
	</div>
	</div>
	</div></div>
					</div>
	
	    	<div id="header" class="portlet-body" style="display: none; visibility: hidden;">
 <center><h4 style="margin-center:350px;">SERVICE WISE ROUTE LIST REPORT</h4></center>
<br />
<div id="headerdetails" style="margin-left:0px;">

<table ID="mytable" style="width:60%;border:none;">
				<tr>
	


				
					<td ><b><font size="2"><label>  Date: </label></font></b>
					<!-- <td style="text-align: left;"> -->
					
								 <b><font size="2"><span id="startdate" style="text-align:center;margin-center:10px;"></span>
								 
								 </font></b></td>
				
				</tr>
			
			</table>
			 <br/><br/> 

</div>
</div> 
	
	</head>
	</html>
