  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<style type="text/css">
	.table > tbody > tr > th,.table > tbody > tr > td{
		border:3px solid #dddddd;
	}
	th{
		text-align: center;
	}
</style>
<div class="page-content-wrapper">
	<div class="page-content">
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
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i><b id='heading'>Depot Wise Active ETM Count</b>
						</div>
					</div> 
					<div class="portlet-body">
                          <div class="portlet-body" style="width: 70%">
                               <div class="panel-body">
                                   <form action="showDepotWiseETMCount" class="form-horizontal form-row-seperated" method="post">
                                   		<div class="form-group">
		                                	<label class="control-label col-md-4">Date :</label>
											<div class="col-md-4">
												<div class="input-group input-medium date date-picker"
													style="width: auto" data-date-format="dd-mm-yyyy"
														data-date-viewmode="years" >
													<input id="startdate" class="form-control" type="text" readonly="true" size="16" name="reportDate" value="<s:property value='reportDate' />">
													<span class="input-group-btn">
														<button class="btn default date-set" type="button">
															<i class="fa fa-calendar"></i>
														</button>
													</span>
												</div>
											</div>
											<button type="submit" class="btn green" onClick="getDepot()">Submit</button>
										</div>
										<div class="form-group" align="center">
		                                	<table class='table table-striped table-bordered table-hover' style="text-align:center; width:50%">
		                                		<tr>
		                                			<th>Depot Name</th>
		                                			<th>No of Active ETM's</th>
		                                			<th><s:property value='totalCount'/></th>
		                                		</tr>
		                                		<s:iterator value='depotWiseCountList'> 
		                                			<tr>
		                                				<td><s:property value='org_name'/></td>
		                                				<td colspan='3'><s:property value='count'/></td>
		                                			</tr>
		                                		</s:iterator>
		                                	</table>
										</div>
                                   </form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
</head>