<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="assets/vts/js/mismatch.js"
	type="text/javascript"></script>

<script type="text/javascript">
$(document).ready(function() {
	var curr_date=new Date().toJSON().slice(0,10);
    curr_date=curr_date.split("-");
    curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
    document.getElementById("seldate").value=curr_date;
	getMismatchReport();
});

function printDiv() {     
	 
	 document.getElementById("vtsAlertReportId").style.visibility='visible';  
    document.getElementById("header").style.display='block';
    document.getElementById("header").style.visibility='visible';     
    var divElements = document.getElementById("header").innerHTML;
    divElements+= "<table>"+document.getElementById("vtsAlertReportId").innerHTML+"</table>";
    
   //divElements += document.getElementById("schdeviation").innerHTML;
 
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


function getMismatchReport() {
		$('#vtsAlertReportDivId').attr("style", "display:''");
		var givendate = $("#seldate").val();
		     table = $('#vtsAlertReportId');
		     var oTable = table.dataTable({
		    	 "aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
		 		// per
		 		// page
		 		// values
		 		// here
		 		],
		 		// set the initial value
		 		"iDisplayLength" : 5,
		 		"sAjaxSource" : "scheduleMismatchingRecord.action?givendate=" + givendate,
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

		 	jQuery('#vtsAlertReportId_wrapper .dataTables_filter input').addClass(
		 			"form-control input-xsmall input-inline"); // modify table
		 	// search input
		 	jQuery('#vtsAlertReportId_wrapper .dataTables_length select').addClass(
		 			"form-control input-xsmall input-inline");
		}
</script>
</head>
<body>
	<div class="page-content-wrapper">
		<div class="page-content">
			<div class="row">
				<div class="col-md-12">
					<h3 class="page-title">
						VEHICLE CHANGE REPORT  <small></small>
					</h3>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>View Vehicle Change Report
							</div>
						</div>

					</div>
					<div class="portlet-body">
						<input type="hidden" name="requestType" id="requestType"
							value="text" />
						<div class="portlet-body form">
							<div class="form-group">
								<div class="col-md-3">
									<div class="input-group input-medium date date-picker"
										data-date-format="dd-mm-yyyy" data-date-viewmode="years">
										<input class="form-control" type="text" size="10" name=""
											id="seldate"  readonly>
										<span class="input-group-btn">
											<button class="btn default date-set form-control"
												type="button">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
									</div>
								</div>
							</div>
							
	<script>                 	
  	function tabletoExcel(btnExport){     
  	var htmltable= document.getElementById('btnExport');   
    $( "#header-fixed" ).remove();
    var inputHTML = "<table border='1'>";
    inputHTML += "<tr>";
    inputHTML += "<th  align='left'colspan='9'>Bangalore Metropolitan Transport Corporation</th>";
    inputHTML += "</tr>";
    inputHTML += "<th  align='left'colspan='9'>SCHEDULE MISMATCH REPORT</th>";
    inputHTML += "</tr>";
    inputHTML += "<tr>";
    inputHTML += "<th colspan='2' align='left'>Date Range:</th>";
    inputHTML += "<th colspan='7' align='left'>" + $("#startdate").val() + " to " + $("#endate").val() + "</th>";
    inputHTML += "</tr>";
    inputHTML += "</table>";
    var htmltable = document.getElementById("vtsAlertReportId");
    var html = inputHTML + htmltable.outerHTML;
    var downloadLink = document.createElement("a");
    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
    downloadLink.download = "SCHEDULE MISMATCH REPORT";
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
}</script>
		
		
							<div class="form-group">
								<div class="col-md-3">
									<!-- <div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9"> -->
									<button type="button" class="btn default"
										onclick="getMismatchReport()">Submit</button>
									<!-- </div>
									</div> -->
								</div>
								<div class="col-md-1"id="printbutton" >
											<input type='button' class="btn default" id='button1' onclick='printDiv()' value='Print' />													
										</div>
										<div class="col-md-1" id="">
								 <button type="button" class="btn default" id="btnExport" filename= "report" onclick="tabletoExcel();"> EXPORT </button>
                                </div>
							</div>
						</div>
					</div>
					<br />
					<br /> <br />
					<br />
					<div class="portlet-body" id="vtsAlertReportDivId"
						style="display: none">
						<div style="text-align:center">
						<table class="table table-striped table-bordered table-hover"
							id="vtsAlertReportId">

							<thead>
								<tr>
									<th>Sr.No.</th>
									<th>Depot No.</th>
									<th>Total Changed Vehicle</th>
								</tr>
							</thead>

						</table>
</div>
					</div>
					 <div id="header" class="portlet-body">
										
									<br />
							
					<div style="display: none;" id="mymodal1" class="modal fade"
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
										<div>
											<input type="hidden" name="requestType" id="requestType" />
											<div>
												<div class="portlet solid white">
													<div class="form-group">
														<label class="col-md-3 control-label"></label>
													</div>
													<div class="portlet-body" id="schedulemismatch"
														style="display: none">
														<div style="text-align:center">
														<table
															class="table table-striped table-bordered table-hover"
															id="schDetails">
															<thead>
																<tr>
																	<th>Sr.No.</th>
																	<th>Schedule Name</th>
																	<th>Shift Type</th>
																	<th>Waybill No.</th>
																	<th>Current Status</th>
																	<th>Schedule Mapping Vehicle</th>
																	<th>Current Vehicle</th>
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


				</div>
			</div>
		</div>
	</div>
</body>
</html>