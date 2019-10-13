<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<html>
	<head>
	<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Expires" content="-1">

	<style>
	
          p.intro {color:red;font-size:14px;}
          p.success{color:green; size:2px;}

   
	.centerize {
    display: block;
    margin: 0 auto;
    text-align: center;
}
	</style>
	</head>
	
	<body>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"
	type="text/javascript"></script>

<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.min.js"
	type="text/javascript"></script>
<!-- END SIDEBAR -->
	<!-- BEGIN CONTENT -->
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
			
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
				             TICKET INVENTORY<small></small>
					</h3>
					
					<!-- END PAGE TITLE & BREADCRUMB-->
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
								<i class="fa fa-globe"></i>Delete Stock Details
							</div>
													</div>
						<div class="portlet-body">
							<div class="table-toolbar">
								<div>	
						<!-- <h3 class="page-title" style="font-size:22px">
                        Ticket Type
                        </h3>  -->
                        <div class="portlet box grey-cascade">
						<div class="portlet-title">
                        <div class="caption" style="font-size:16px">
								 Ticket Type
						</div>
							</div>
							</div>		
								</div>			
								
							</div>
							<div id="successmsgtick" style="display:none">
                                      
			                           <p class="success" id="succticket" ></p>
			                         
		                                     <span>
		                                     
											 
			                                  </span>
		                              </div>
							<table class="table table-striped table-hover table-bordered" id="ticketDeleteTable">
							<thead>
							<tr>
										 <th></th>
										<th>Denomination</th>
										<th>Denomination Key</th>
										<th>Start Number</th>
										<th>End Number</th>
									
                                        <th>No Of Books</th>
										<th>Value</th>                                        
										
										<th>Priority</th>
										 
										<th>Delete</th>
										<th>Cancel</th>
									</tr>
								</thead>	
							
							</table>
													</div>
						<div class="portlet-body">
							<div class="table-toolbar">
								<div>	
						<!-- <h3 class="page-title" style="font-size:22px">
                        Ticket Type
                        </h3>  -->
                        <div class="portlet box grey-cascade">
						<div class="portlet-title">
                        <div class="caption" style="font-size:16px">
								 Pass Type
						</div>
							</div>
							</div>		
								</div>		
							</div>
							<div id="successmsgpass" style="display:none">
                                      
			                           <p class="success" id="succpass" ></p>
			                         
		                                     <span>
		                                     
											 
			                                  </span>
		                              </div>
							<table class="table table-striped table-hover table-bordered" id="passDeleteTable">
							<thead>
							<tr>
										 <th></th>
							             <th>Type Of Pass</th>
										<th>Denomination</th>
										<th>Denomination Key</th>
										<th>Pass Day</th>
										<th>Start Number</th>
				 						<th>End Number</th>
									    <th>No Of Passes</th>
                                        <th>No Of Books</th>
										<th>Value</th>                                        
										<th>Priority</th>
										<th>Delete</th>
										<th>Cancel</th>
									</tr>
								</thead>	
							
							</table>
							
						</div>
						<div class="portlet-body">
							<div class="table-toolbar">
																<div>	
						<!-- <h3 class="page-title" style="font-size:22px">
                        Ticket Type
                        </h3>  -->
                        <div class="portlet box grey-cascade">
						<div class="portlet-title">
                        <div class="caption" style="font-size:16px">
								 Luggage Type
						</div>
							</div>
							</div>		
								</div>							
							</div>
							<div id="successmsglugg" style="display:none">
                                      
			                           <p class="success" id="succlugg" ></p>
			                         
		                                     <span>
		                                     
											 
			                                  </span>
		                              </div>
							<table class="table table-striped table-hover table-bordered" id="luggageDeleteTable">
							<thead>
							<tr>
										 <th></th>
										<th>Denomination</th>
										<th>Denomination Key</th>
										<th>Start Number</th>
										<th>End Number</th>
									
                                        <th>No Of Books</th>
										<th>Value</th>                                        
										
										<th>Priority</th>
										 
										<th>Delete</th>
										<th>Cancel</th>
									</tr>
								</thead>	
							
							</table>
							<br>
							<button id="save_deltkt" class="btn green">
													Save  Details
												</button>
												<button id="backbtn1" class="btn green">Back</button>
						</div>
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
			</div>
			<!-- END PAGE CONTENT -->
		</div>
	</div>
	<!-- END CONTENT -->
</div>
</body>
</html>