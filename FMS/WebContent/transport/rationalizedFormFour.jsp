
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  
<html>
<head>


<script>
function cancelform(){

	window.location.href = 'ShowScheduleDetails.action';
}
		
	</script>
	
	
</head>
<body>

 <form>
 
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
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
					 RATIONALIZED FORM IV
					</h3>
					
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			
			<%-- //<s:hidden name="busid" id="id" ></s:hidden> --%>
			 <!-- <input type="hidden" name="busid" id="busid" value="22181"/> -->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>View Rationalized Form IV
							</div>
							<div class="actions">
							<div class="btn-group">
								
								<button type="button" class="btn blue" onclick="cancelform();">Cancel</button>
								
								
									</div>
							</div>
							
						</div>
						<input type="hidden" name="id" value="201"/>
						<div class="portlet-body">
						
						
							<table class="table table-striped table-bordered table-hover" id="rformfour">
							<thead>
							<tr>
								<th>
								</th>
								<th>
									 Form Four Id
								</th>
								<th>
									 Form Four Name
								</th>
								<th>
									 Schedule Number
								</th>
								<th>
									 Schedule Number Name
								</th>
								
								<th>
									No. Of Trips
								</th>
								<th>
									 Route No
								</th>
							 	
								<th>
									 Start Time
								</th> 
								<th>
									Effective Start date
								</th>
								<th>
									Effective End Date
								</th>
								
								<th>
									Current Status
								</th>
								<th>
									 Remarks
								</th>
								<th>
								Updated By
								</th>
								<th>
									 Updated Date
								</th>
								
								
							</tr>
							</thead>
							
							</table>
							
							</div>
						</div>
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
				
						
			<!-- END PAGE CONTENT-->
			
		</div>
	</div>
	
	</form>
	
	
								
	</body>
	</html>