  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
 
<script>






// function getDutyType(){
	
// 	   var designationlist=$("#designationlist").val();
	 
	   
// 		var dd1=$("#startdate").val();
// 		var dd2=$("#enddate").val();
//  		var var1=$("#startdate").val();
 		
// 		var1=var1.split("-");
// 		var1=var1[2]+"-"+var1[1]+"-"+var1[0];
//  		var var2=$("#enddate").val();
//  		//alert(var2);
//  		var2=var2.split("-");
//  		var2=var2[2]+"-"+var2[1]+"-"+var2[0];
// 		//alert(var1+""+var2);
		
// 		 var d1 = Date.parse(var1);

		
//  	var d3=Date.parse(var2);
//  		if (d1 <= d3){
//  			  $('#pageLoader').show();
//         $.ajax({
        
//             type: "post",
<%--             url: '<%=request.getContextPath()%>/getEmployeeDutyTypeList.action?startdate='+dd1+'&enddate='+dd2+'&designationlist='+designationlist, --%>
//             success: function(result) {
//             	$('#pageLoader').hide();
//                 document.getElementById('ticketconsuptionid').innerHTML=result;
//                 fixHeader();
//             }
//         });
		
	
// }
</script>
<script>
function desdrop(empvalue,shiftvalue){
//alert(empvalue+shiftvalue);
	var shiftid=shiftvalue;
	var empid=empvalue.replace("dtype","");
//	alert(empid);
//	alert(document.getElementById(empid+"wo1"));
	if(shiftid=="1" || shiftid=="4" || shiftid=="5" || shiftid=="6" || shiftid=="7"){
		//alert("hi");
	
		//document.getElementById(empid+"wo1").disabled=true;
		document.getElementById(empid+"wo2").disabled=true;
		}
	else{
		//document.getElementById(empid+"wo1").disabled=false;
		document.getElementById(empid+"wo2").disabled=false;
		}
	//alert(empid+"dtype1");
if(shiftid=="2"){
		var s=document.getElementById(empid+"dtype1").selectedIndex=3;
		$("#"+empid+"dtype11").val(s);
}else if (shiftid=="3"){
	var s=document.getElementById(empid+"dtype1").selectedIndex=2;
	$("#"+empid+"dtype11").val(s);
			//var r=$("#"+empid+"dtype11").val();
//alert(r);
}else{
	var s=document.getElementById(empid+"dtype1").selectedIndex=0;
	$("#"+empid+"dtype11").val(s);
}
//alert($("#"+empid+"dtype11").val());
	  
	  
	}
	
	

</script>
<script>
/* function getdata(){
	
} */
	


</script>

 
<div class="page-content-wrapper">
 <% 

 String  orgtypeid=(String)request.getSession().getAttribute("orgtypeid");
 String  orgchartid=(String)request.getSession().getAttribute("orgchartid");
 out.println(orgtypeid+"==="+orgchartid);

 %> 
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
						<!-- <div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>Employee Duty Type
							</div>
							<a href="#" class="btn blue" onclick="callEdit()"> <i
								class="fa fa-pencil"></i> Edit
							</a>
						</div> -->
					
											<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>Employee Duty Type
							</div>
							
							<div class="actions">
								<%if(!orgtypeid.equals("1") && !orgchartid.equals("1")){ %>
								<a href="#" class="btn red" onclick="callCancel()"> <i
								class="fa fa-cross"></i> Cancel
								</a>
								<%}%>
							<%-- 		<%if(!orgtypeid.equals("1") && !orgchartid.equals("1")){ %>
							<a href="#" class="btn blue" onclick="callEdit()"> <i
								class="fa fa-pencil"></i> Edit
							</a>

								<% }%> --%>
														
							</div> 
							
					
						</div>
						
						<div class="portlet-body">
						

							<!-- 						start -->

<!-- <table> -->
                   <form action="saveDutyTypeEmployee.action" method="post" class="form-horizontal">
                           <div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Designation<font
											color="red">*</font></label>
										<div class="col-md-4">
											<s:select list="designationlist" id="designationlist" 
											name="designationlist1"  
												cssClass="select2_category form-control"  
												  headerKey="0" headerValue="--select--" onclick="getEmployeeDetails()"></s:select>  
												 
										</div>
									</div>
								
                             
								
                          
							 <div class="form-group">
							  <label class="control-label col-md-3">Effective Start Date:</label>
								<div class="col-md-3">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" data-date-start-date="+0d">
								<input id="startdate" class="form-control" type="text" readonly="" size="16" name="startdate"
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
							  <label class="control-label col-md-3">Effective End Date:</label>
								<div class="col-md-3">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" data-date-start-date="+0d">
								<input id="enddate" class="form-control" type="text" readonly="" size="16" name="enddate"
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
							
							
						 <input type="hidden" name="requestType" id="requestType"
								value="text" />
								<input type="hidden" id="orgchartdid" value='<%=orgchartid %>'>
						<table class="table table-striped table-bordered table-hover"
								id="employeedetailsTableId">
								
								<thead>
									<tr>
									
										<th>Token No</th>
										<th>PF</th>
										<th>Employee Name</th>
										<th>Section</th>
										<th>Batch</th>
										<th>Duty Type</th>
										<th>W/O1</th>
										<th>Duty Type</th>
										<th>W/O2</th>
										

									</tr>
								</thead>

							</table>
							
					 <div class="form-group">
					 <label class="control-label col-md-3"></label>
                     <div class="col-md-3" id="">
                     </div>
                     </div>
                     </div>
                      <div align='center'>
					<button type="submit" class="btn green" >Save</button> 
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
                     </form>
                    
					<div id="ticketconsuptionid"></div>
					

			</div>
			
			<!-- END PAGE CONTENT-->
		</div>
	</div>
	</div>
	<script>
	function callCancel(){
		
		window.location.href='Employeerank.action';
	}
	</script>
	<SCRIPT type="text/javascript">
	
	function getEmployeeDetails(){
		//$("#attendanceDetails").show();
		
		var designationId=document.getElementById("designationlist").value;
		var orgchartdid=document.getElementById("orgchartdid").value;
		//alert(designationId);
	
	        $('#employeedetailsTableId').dataTable({
	        	"aaSorting" : [ [ 1, 'asc' ] ],
 				"aLengthMenu" : [ [ 5,10,25, 50, 100 ], [ 5,10,25, 50, 100 ] // change per page	// values// here
 				],
					 //set the initial value
					"iDisplayLength" : 5,
                    "bPaginate":true,
					"bProcessing" : true,
					"bProcessing" : true,
					"bServerSide" : true,
					"bDestroy" : true,
					"sAjaxSource" : "getEmployeeDutyTypeList.action?designationId="+designationId+"&orgchartdid="+orgchartdid,
					"sPaginationType" : "bootstrap",
					"oLanguage" : {
						"sLengthMenu" : "_MENU_ records",
 						"oPaginate" : {
 							"sPrevious" : "Prev",
 							"sNext" : "Next"
 						}
					},
					 "aoColumnDefs": [
					                    { 'bSortable': false, 'aTargets': [0] },
					                    { "bSearchable": false, "aTargets": [ 0 ] }
					                ]

				});

				jQuery('#employeedetailsTableId_wrapper.dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
 	            jQuery('#employeedetailsTableId_wrapper.dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
 	            jQuery('#employeedetailsTableId_wrapper .dataTables_length select').select2(); // initialize select2 dropdown
	      //desdrop();      
		}
	
	</SCRIPT>
	</div>
	</div>
	</div>
	</head>
	</html>

	
	
	
