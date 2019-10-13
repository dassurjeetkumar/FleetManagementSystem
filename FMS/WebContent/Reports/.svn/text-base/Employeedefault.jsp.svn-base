  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
 


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
								<i class="fa fa-globe"></i>Employee Default Rank
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
										<th>Rank</th>
									
										

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
		
		window.location.href='Employeedefault.action';
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
					"sAjaxSource" : "getEmployeeDefaultTypeList.action?designationId="+designationId+"&orgchartdid="+orgchartdid,
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

	
	
	
