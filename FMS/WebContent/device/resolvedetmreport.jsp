<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

 <script src="assets/global/plugins/jquery-1.11.0.min.js"
	type="text/javascript"></script>
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

function getData(){
	 var divid=$("#divisionlist").val();
		var depotid=$("#depotlist1").val();
		var statusid=$("#statusid").val();
		var strdate=$("#startdate").val();
		var enddate=$("#enddate").val();
		if(strdate=='' || strdate==null){
			alert("please select Form Date");
		}else if(enddate=='' || enddate==null){alert("please select End Date");}
		else {
	$('#resolvedetmreport').attr("style", "display:''");
	
    table = $('#resolvedetmreportTable');
    var oTable = table.dataTable({
    	"aLengthMenu" : [ [ 5,10, 15, 20, -1 ], [ 5,10, 15, 20, "All" ] // change
		// per
		// page
		// values
		// here
		],
		// set the initial value
		"iDisplayLength" : 5,
		"sAjaxSource" : "ajaxgetresolvedetmreport.action?divid="+divid+"&depot="+depotid+"&startdate="+strdate+"&enddate="+enddate,
		"sPaginationType" : "bootstrap",
		"bDestroy" : true,
		"bSort" : true,
		"bFilter" : true,
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

	jQuery('#resolvedetmreportTabletable_wrapper .dataTables_filter input').addClass(
			"form-control input-xsmall input-inline"); // modify table
	// search input
	jQuery('#resolvedetmreportTabletable_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");	
		}
}
</script>
<%-- <script type="text/javascript">
$(document).ready(function() {
getdata();
getheader();
});
</script> --%>
<script>

function printDiv() {     
	  document.getElementById("resolvedetmreport").style.visibility='visible';  
	     document.getElementById("header").style.display='block';
	     document.getElementById("header").style.visibility='visible'; 
	    
	     
	     var divElements = document.getElementById("header").innerHTML;
	     divElements+= document.getElementById("resolvedetmreport").innerHTML;
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
	<script type="text/javascript">
    function tabletoExcel(btnExport){     
    	/*  document.getElementById("mapshow").style.visibility='hidden'; 
    		$(".mapClass").hide(); */
   	     
    	     var inputHTML = "<table border='1'>";
             inputHTML += "<tr>";
             inputHTML += "<th  align='center'colspan='8'>Bangalore Metropolitan Transport Corporation</th>";
             inputHTML += "</tr>";
            inputHTML += "<tr>";
             inputHTML += "<th  align='Center'colspan='8'>Resolved Etm Report</th>";
             inputHTML += "</tr>";
           
             inputHTML += "</table>";
    		   document.getElementById("resolvedetmreport").style.visibility='visible';  
    	     document.getElementById("header").style.display='block';
    	     document.getElementById("header").style.visibility='visible';     
    	     var divElements = inputHTML+document.getElementById("header").innerHTML;
    	     divElements+= document.getElementById("resolvedetmreport").innerHTML;
     
     var noOfTableExist = 0;
     try{
 		$("#resolvedetmreportTable table").each(function(){
 			noOfTableExist++;
 		});
 		
 		console.log("Total no of tables : " + noOfTableExist);
 		/* If two table exist  then remove the last table on print click*/
 		/* if(noOfTableExist >= 2){
 			divElements = divElements.substring(0, divElements.indexOf("</table>") + 8) + "</div></div>";
 		} */
 	}catch(err){
 	    console.log("ExceptionCaught : " + err);
 	}
    
    var downloadLink = document.createElement("a");
    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(divElements);
    downloadLink.download = "Etm Device History.xls";
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
}
	</script>
	 <script>
	function getheader() {
		
		document.getElementById("startdat").innerHTML = new Date().toJSON().slice(0,10);
		}
 </script>

	


</head>
<body>
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
								<i class="fa fa-globe"></i>Resolved Etm Report
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
												 onchange="getDepot(this.value)"></s:select>
										</div>
									</div>
								</div>
								
                               <div class="form-group">
									<label class="col-md-3 control-label">Depot<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select list="depotlist" id="depotlist1" class="select2_category form-control" name="depotlist1" headerKey="0" headerValue="---All---" > 
												<option value="0">--All--</option>
 										</select> 
									</div>
 								</div> 
 								
 						
 							
									<div class="form-group">
							  <label class="control-label col-md-3">From Date:<font
										color="red">*</font></label>
								<div class="col-md-3">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" data-date-end-date="0d"> 
								<input id="startdate" name="dateaction" class="form-control" type="text" readonly="" size="16" name="rateMaster.effectiveStartDate" placeholder="Pick The Date" "
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
							  <label class="control-label col-md-3">End Date:<font
										color="red">*</font></label>
								<div class="col-md-3">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" data-date-end-date="0d"> 
								<input id="enddate" name="dateaction" class="form-control" type="text" readonly="" size="16" name="rateMaster.effectiveStartDate" placeholder="Pick The Date" "
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
                                        var dtval=document.getElementById('enddate').value;	
                                        
                                         if(dtval==''){
                                         $('#enddate').val(curr_date);
                                        }
								</script>
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
				<!-- <div id="ticketconsuptionid"></div>	 -->		
			</div>
			
			<!-- END PAGE CONTENT-->
			
		
	
		
	
	
				<div class="portlet-body" id="resolvedetmreport" style="display: none; visibility: hidden;">
						
						
                           
                                   <table class="table table-striped table-bordered table-hover"
								id="resolvedetmreportTable">
								
								<thead>
									<tr>
									<!-- 	<th style="width1: 8px;"></th> -->
										<th>SN</th>
										<th>Depot<br>No </th>
										<!-- <th>Depot<br>Name</th> -->
										<th>Etm No</th>
										<th>Issue</th>
										<th>Issue<br>Date</th>
										<th>Picked<br>Trimax</th>
										<th>Picked<br>Verifone</th>
										<th>Solved<br>Verifone</th>
											<th>Received<br>Trimax</th>				     
                                       <th>Solved<br>Remarks</th>
                                       <th>Remarks</th>
									</tr>
								</thead>

							</table>
                         
							</div>
							</div></div></div></div></div>
			<div id="header" class="portlet-body" style="display: none; visibility: hidden;">
 <h3 style="margin-left:350px;">Resolved Etm Report</h3>
<br />
<b><font size="2"><label>Date: </label></font><font size="2"><b><span id="startdat" style="text-align:left;margin-left:50px;"></span></font></b>

</div>
	
								
	</body>
	</html>