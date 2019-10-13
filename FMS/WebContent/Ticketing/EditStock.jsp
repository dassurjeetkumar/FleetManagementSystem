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
	.centerize {display: block; margin: 0 auto; text-align: center;}
</style>
</head>
<body>
<script  src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js" type="text/javascript"></script>
<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.min.js" 	type="text/javascript"></script>
<form action="#" name="ticketeditform" method="post">
	<div class="page-content-wrapper">
		<div class="page-content">
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
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<h3 class="page-title">
						TICKET INVENTORY <small></small>
					</h3>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-edit"></i>Edit Stock Details
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-toolbar">
								<div class="portlet-title">
                        			<div class="caption" style="font-size:16px">
										<b>Ticket Type</b>
									</div>
								</div>
								<input type="hidden" name="succmsgedit" id="succmsgedit"/>						
									<div id="successmsgtick" style="display:none">
			                           <p class="success" id="succticket" ></p>
		                                     <span></span>
		                    		</div>
									<table class="table table-striped table-hover table-bordered" id="ticketEditTable">
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
												<th></th>
											</tr>
										</thead>	
									</table>
								</div>
								<div class="portlet-body">
									<div class="table-toolbar">
										<div class="portlet-title">
                        					<div class="caption" style="font-size:16px">
								 				<b>Pass Type</b>
											</div>
										</div>
										<div id="successmsgpass" style="display:none">
											<p class="success" id="succpass" ></p>
			                                     <span>
			                                     </span>
		                              	</div>
										<table class="table table-striped table-hover table-bordered" id="passEditTable">
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
													<th></th>
												</tr>
											</thead>	
										</table>
										<br> 
									</div>
									<div class="portlet-body">
										<div class="table-toolbar">
											<div class="portlet-title">
                       							<div class="caption" style="font-size:16px">
								 					<b>Luggage Type</b>
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
								<table class="table table-striped table-hover table-bordered" id="luggageEditTable">
									<thead>
										<tr>
							            	<th></th> 
											<th>Luggage ticket Key</th>
											<th>Start Number</th>
											<th>End Number</th>
											<th>No. of luggage ticket</th>
	                                        <th>No Of Books</th>
											<th>Value</th>                                        
											<th>Priority</th>
											<th></th>
										</tr>
									</thead>	
								</table>
								<button id="save_tkt" class="btn blue">Save Details</button>
								<button id="backbtn1" class="btn blue">Back</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>