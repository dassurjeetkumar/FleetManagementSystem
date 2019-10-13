
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<form id="form123">
	<div class="page-content-wrapper">
		<div class="page-content">
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div class="modal fade" id="portlet-config" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true"></button>
							<h4 class="modal-title">Modal title</h4>
						</div>
						<div class="modal-body">Widget settings form goes here</div>
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
						VEHICLE WISE DISTANCE TRAVELED <small></small>
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
								<i class="fa fa-globe"></i>View Vehicle Wise Distance Traveled
							</div>
							
						</div>
						<div class="portlet-body form">
										<div class="form-group">
											<div class="col-md-3">
												<div class="input-group input-medium date date-picker"
													data-date-format="yyyy-mm-dd" data-date-viewmode="years">
													<input class="form-control" type="text"  name=""
															id="seldate" value='<s:property value=""/>' readonly>
													<span class="input-group-btn">
														<button class="btn default date-set form-control" type="button">
															<i class="fa fa-calendar"></i>
														</button>
													</span>
												</div>
											</div>
										</div>
											<div class="form-group">
											<div class="col-md-3">
												<s:select list="divisionlist1" id="divisionlistid1"
													cssClass="select2_category form-control" headerKey="00"
													headerValue="--Select--" onchange="getAlertReport(this.value)">
												</s:select>
											</div>
										</div>
									</div>
						
						<div class="portlet-body">
							<input type="hidden" name="requestType" id="requestType"
								value="text" />
							<table class="table table-striped table-bordered table-hover"
								id="vehiclewisedistance">
								<thead>
									<tr>
										<th style="width1: 8px;"></th>
										<th>Vehicle Number</th>
										<th>Device Number</th>
									     <th>KM Traveled</th> 
									</tr>
								</thead>

							</table>

						</div>
					</div>
				</div>
				<!-- END EXAMPLE TABLE PORTLET-->
			</div>
		</div>

		<!-- END PAGE CONTENT-->
	</div>

	
	</form>



