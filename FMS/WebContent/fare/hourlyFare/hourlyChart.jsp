
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
<script type="text/javascript">

	function getSheduleno()
	{	
		// $("#tableid").hide();
		 //document.getElementById('select2-chosen-1').innerHTML='select';
		// document.getElementById('scheduleidlist').value=0;
	}
	
</script>


</head>
 <body onload="getSheduleno()">

	<form> 
	<div class="page-content-wrapper">
		<div class="page-content">
<!-- 			BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM -->
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
<!-- 					/.modal-content -->
				</div>
<!-- 				/.modal-dialog -->
			</div>

<!-- 			END STYLE CUSTOMIZER -->
<!-- 			BEGIN PAGE HEADER -->
			<div class="row">
				<div class="col-md-12">
<!-- 					BEGIN PAGE TITLE & BREADCRUMB -->
					<h3 class="page-title">
						FARE CHART <small></small>
					</h3>

<!-- 					END PAGE TITLE & BREADCRUMB -->
				</div>
			</div>
<!-- 			END PAGE HEADER -->
			<!-- BEGIN PAGE CONTENT -->

					<div class="row"> 
				<div class="col-md-12">
<!-- 					BEGIN EXAMPLE TABLE PORTLET -->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>Update Hourly Fare Chart
							</div>
							<div class="actions">
								<a href="javascript:void(0)" class="btn blue"
									 onclick="callEdit()"> <i class="fa fa-pencil"></i> Edit
								</a>
							</div>
						</div>
						<div class="portlet-body"> 
							<FONT color="green" style="font-weight:bold"><s:actionmessage /></FONT>
							<FONT color="red" style="font-weight:bold"><s:actionerror /></FONT>
							
							<div class="row">
							<div class="col-md-offset-3 col-md-3 form-group">
								<label class="col-md-3 control-label" style="width:150px">Route Number:<font color="red">*</font></label>
                                <div class="col-md-4">
								<s:select list="routeNumberMap" id="route_id" name="route_id" cssStyle="width:250px"
										cssClass="select2_category form-control" headerKey="0" headerValue="Select">
								</s:select> 
								</div>
							</div>
							</div>
							
						    <!-- <div class="row"><br></div>  -->
							
							<div class="row">
							<div class="col-md-offset-3 col-md-3 form-group">
								<label class="col-md-3 control-label" style="width:150px">Service Type:<font color="red">*</font></label>
								<div class="col-md-4">
								<s:select list="serviceTypeMap" id="service_type_id" name="service_type_id" cssStyle="width:250px"
									cssClass="select2_category form-control" headerKey="0" headerValue="Select">
								</s:select>	
								</div>
							</div>
							</div>
							
							<div class="row">
							<div class="form-actions fluid">
								<div class="col-md-offset-5 col-md-3">
									<button id="btnHourChart" type="button" class="btn blue" onclick="show_data();">Submit</button>
								</div>
							</div>
							</div>
							
								<br>
								<br>
							<div class="portlet-body" id="tableid" style="visibility: hidden">
							<table class="table table-striped table-bordered table-hover" id="showHourlyChartTable">
								<thead>
									<tr>
										<th>Fare Chart Id</th>											
											<th>Fare Chart Name</th>
											<th>Effective From</th>
											<th>Effective Till</th>
											<th>00-01 Hr</th>
											<th>01-02 Hr</th>
											<th>02-03 Hr</th>
											<th>03-04 Hr</th>
											<th>04-05 Hr</th>
											<th>05-06 Hr</th>
											<th>06-07 Hr</th>
											<th>07-08 Hr</th>
											<th>08-09 Hr</th>
											<th>09-10 Hr</th>
											<th>10-11 Hr</th>
											<th>11-12 Hr</th>
											<th>12-13 Hr</th>
											<th>13-14 Hr</th>
											<th>14-15 Hr</th>
											<th>15-16 Hr</th>
											<th>16-17 Hr</th>
											<th>17-18 Hr</th>
											<th>18-19 Hr</th>
											<th>19-20 Hr</th>
											<th>20-21 Hr</th>
											<th>21-22 Hr</th>
											<th>22-23 Hr</th>
											<th>23-00 Hr</th>
										</tr>
								</thead>

							</table>
							</div>

						</div>
					</div>
				</div>
<!-- 				END EXAMPLE TABLE PORTLET -->
			</div>
		</div>

		<!-- END PAGE CONTENT -->
	</div>
<script> 
 		function callEdit() {
 			var schid=document.getElementById("scheduleidlist").value;
 				if(schid==0){
					bootbox.alert("Please Select Schedule Number");
				}else{
					 document.getElementById("form1").submit();
				}
		}
		
		$(document).ready(function() {
		window.history.pushState("", "", "ShowHourlyChart.action");
		});  
	</script> 
 </form>
 
 <form name="form1" id="form1" action="editWeeklyChart.action" method="POST">
	<input type="hidden" name="scheduleidlist" id="scheduleidlistidd" value="" />
</form> 
</body>

</html>













