  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
 
<script>





function getDepot1(){
	
	var date1 = document.getElementById("startdate").value;
	var stddate=Date.parse(date1);
		var dd1=$("#startdate").val();
 			
	        $('#pageLoader').show();
	        
        $.ajax({
        
            type: "post",
            url: '<%=request.getContextPath()%>/getDailyPassReport.action?startdate='+dd1,
            success: function(result) {
            	$('#pageLoader').hide();
                document.getElementById('ticketconsuptionid').innerHTML=result;
                fixHeader();
            }
        });
// 		}
	
}
</script>

  <!--  by ashu--> 
<script>
function viewPassDetails(date,mobile_no){
	
	console.log(date);
	$('#modalclass4').attr("style", "display:''");
	//
	
	
	//alert(date);
	//alert(mobile_no);
	document.getElementById("modalclass4").style.display = '';
	
	 $('#pageLoader').show();
     
     $.ajax({
     
         type: "post",
         url: '<%=request.getContextPath()%>/getDailyPassDetails.action?startdate11='+date+'&Mobile_number='+mobile_no,
         success: function(result) {
         	$('#pageLoader').hide();
             document.getElementById('dailyPassDetailtable').innerHTML=result;
             fixHeader();
         }
     });
	
	
// 	$('#dailyPassDetailtable').dataTable(
// 			{
// 				"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
// 				// per
// 				// page
// 				// values
// 				// here
// 				],
// 				// set the initial value
// 				"iDisplayLength" : 5,
// 				"sAjaxSource" : "getDailyPassDetails.action?startdate11="+date+"&Mobile_number="+mobile_no ,
// 				"sPaginationType" : "bootstrap",
// 				"bDestroy" : true,
// 				"oLanguage" : {
// 					"sLengthMenu" : "_MENU_ records",
// 					"oPaginate" : {
// 						"sPrevious" : "Prev",
// 						"sNext" : "Next"
// 					}
// 				},
// 				"aoColumnDefs" : [ {
// 					'bSortable' : false,
// 					'aTargets' : [ 0 ]
// 				} ]
// 			});
// 	jQuery('#dailyPassDetailtable_wrapper .dataTables_filter input').addClass(
// 			"form-control input-medium input-inline"); // modify table
// 	// search input
// 	jQuery('#dailyPassDetailtable_wrapper .dataTables_length select')
// 			.addClass("form-control input-xsmall input-inline");
	
	
}

</script>

<script>
		
function printDiv() {     
    
    //  document.getElementById("print").style.visibility='hidden';     
    var divElements = document.getElementById("ticketconsuptionid").innerHTML;
    divElements += document.getElementById("ticketconsuptionid").innerHTML;
    
    var noOfTableExist = 0;
	try {
		$("#ticketconsuptionid table").each(function() {
			noOfTableExist++;
		});

// 		console.log("Total no of tables : " + noOfTableExist);
		/* If two table exist  then remove the last table on print click (for header fixed)*/
		if (noOfTableExist >= 2) {
			divElements = divElements.substring(0, divElements
					.indexOf("</table>") + 8)
					+ "</div></div>";
		}
	} catch (err) {
		console.log("ExceptionCaught : " + err);
	}

    

    var mywindow = window.open("<%=request.getContextPath()%>/Print1.jsp");
   
    mywindow.onload = function() {
    mywindow.document.body.innerHTML=divElements;
    mywindow.document.body.innerHTML=divElements;
    mywindow.print();
    mywindow.close();
    }
            
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
								<i class="fa fa-globe"></i>Daily Namma Pass Report
							</div>
						</div>
						
						<div class="portlet-body">
<!-- 						<div class="table-scrollable"> -->


							<!-- 						start -->

                     <form action="" method="post" class="form-horizontal">
                        <div class="form-body">
							
<!-- 							<div class="form-group"> -->
							  <label class="control-label col-md-3"> Date :</label>
								<div class="col-md-3">
								
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" ><!-- data-date-start-date="+0d"> -->
								<input id="startdate" class="form-control" type="text"  size="16" name="rateMaster.effectiveStartDate"
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
								</div>

 							
						<!-- end -->
						
						<div class="form-group">
					 <label class="control-label col-md-3"></label>
                     <div class="col-md-3" id="">
                     </div>
                     </div>
                     </div>
                     </form>
				
					 <div align='center'>
					<button type="submit" class="btn green" onClick="getDepot1()">Submit</button>
					<span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span>&nbsp;
					<button type="submit" id="btnExport" class="btn green" onClick="tabletoExcel()">Export</button>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
					<div id="ticketconsuptionid"></div>
			</div>
			<!-- added by ashu  -->
			<div style="display: none;" id="mymodal313" class="modal fade"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel3"
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
									<div class="portlet-body" id="modalclass4"
										style="display: none">
										<div style="text-align:center">
										<table id= "dailyPassDetailtable"></table>
<!-- 				<table class="table table-striped table-bordered table-hover" -->
<!-- 								id="dailyPassDetailtable"> -->
<!-- 								<thead > -->
<!-- 											<tr> -->
<!-- 												<th>Travel Date</th> -->
<!-- 												<th>Passenger Name</th> -->
<!-- 												<th>Gender</th> -->
<!-- 												<th>Mobile Number</th> -->
<!-- 												<th>Ticket Type</th> -->
<!-- 												<th>Ticket Number</th> -->
<!-- 												<th>Conductor id</th> -->
<!-- 												<th>Route Number</th> -->
<!-- 											</tr> -->
<!-- 										</thead> -->
							
<!-- 							</table> -->
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
			
			
			
			
			<!-- END PAGE CONTENT-->
		</div>
	</div>

			<script type="text/javascript">
				function tabletoExcel(btnExport) {
					var divElements = document
							.getElementById("ticketconsuptionid").innerHTML;

					var noOfTableExist = 0;
					try {
						$("#ticketconsuptionid table").each(function() {
							noOfTableExist++;
						});

// 						console.log("Total no of tables : " + noOfTableExist);
						/* If two table exist  then remove the last table on print click (for header fixed)*/
						if (noOfTableExist >= 2) {
							divElements = divElements.substring(0, divElements
									.indexOf("</table>") + 8)
									+ "</div></div>";
						}
					} catch (err) {
						console.log("ExceptionCaught : " + err);
					}

					var downloadLink = document.createElement("a");
					downloadLink.href = 'data:application/vnd.ms-excel,'
							+ encodeURIComponent(divElements);
					downloadLink.download = "ShowdailyPassReport.xls";
					document.body.appendChild(downloadLink);
					downloadLink.click();
					document.body.removeChild(downloadLink);
				}
			
				
			</script>