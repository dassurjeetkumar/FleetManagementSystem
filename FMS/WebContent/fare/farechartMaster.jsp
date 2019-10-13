
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
	<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ShowFareChartAction.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
%>
<form>
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
<%if (access.equalsIgnoreCase("Y")){%>
			<!-- END STYLE CUSTOMIZER -->
			<!-- BEGIN PAGE HEADER-->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						FARE CHART <small></small>
					</h3>
<%-- <FONT color="green"> <s:actionerror/> </FONT> --%>
				
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
								<i class="fa fa-globe"></i>View Fare Chart
								
							</div>
							<div class="actions">
								<%if(create.equalsIgnoreCase("Y")){ %>
								<a href="#" class="btn green" id="createChart"
									onclick="callCreate()"> <i class="fa fa-plus"></i> Create
								</a>
								<%}%>
								<%if(edit.equalsIgnoreCase("Y")){ %> <a href="#" class="btn blue" id="editFareChart"
									> <i class="fa fa-pencil"></i>
									Edit
								</a>
								<%}%>
								<%if(delete.equalsIgnoreCase("Y")){ %> <a href="#" class="btn red" id="deleteFareChart"
									> <i class="fa fa-times"></i> Delete
								</a>
								<%}%>
								<%if(create.equalsIgnoreCase("Y")){ %>	
								<a href="#" class="btn blue"  id="copy" onclick="callCopy()">
								<i class="fa fa-pencil"></i> Copy </a>
								
								<a href="#" class="btn blue"  id="fareTraingle"> 
								<i class="fa fa-pencil"></i> Fare Chart </a>
                                 <%} %>
							</div>

							</div>
						

						<div class="portlet-body">
							
						<FONT color="red"> <s:actionerror/> </FONT>	
						<FONT color="green"> <s:actionmessage/> </FONT>
							<table class="table table-striped table-bordered table-hover"
								id="fareChart">
								<thead>
									<tr>
										<th style="width1: 8px;"></th>
										<th>Fare Chart Id</th>
										<th>Fare Chart Name</th>
										<th>Rate Master Id</th>
										<th>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										Route Number
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</th>
										<th>Service Type</th>
										<th>Passenger Type</th>
										<th>Effective Start Date</th>
										<th>Effective End Date</th>										
										<th>Night service</th>
										<th>Peak Hours</th>
										<th>Flexi Fare</th>
<%if(viewdeletedrecord.equalsIgnoreCase("Y")) {%>	
<th>Record Status</th>
<%}%>

									</tr>
								</thead>

							</table>

						</div>
					</div>
				
				<!-- END EXAMPLE TABLE PORTLET-->
			</div>
		

		<!-- END PAGE CONTENT-->
	</div>
	</div>
	</div>
	
	<script>
		
			$('#editFareChart')
					.click(
							function() {
								var cnt = $(":checkbox:checked").length;
								var val;
								if (cnt == 0) {
									bootbox.alert("Please select checkbox to edit");
								} else if (cnt > 1) {
									bootbox.alert("Please select one checkbox to edit");
								} else {
									$("input[type='checkbox']:checked").each(
											function() {

												val = this.value;
											});
									/* var val = $("input[type='checkbox']").val(); */
									//alert(val);
									/* document.forms[0].action = 'EditFareChartMaser.action?chartid='
											+ val;
									
									document.forms[0].submit(); */
									if(isEligibleForOpertion(val)){
									document.getElementById("eid").value = val;
									document.getElementById("form2").submit();
									}else{
										bootbox.alert("Please Select Valid Record");
									}

								}

							});
	         
			$('#fareTraingle')
			.click(
					function() {
						var cnt = $(":checkbox:checked").length;
						var val;
						if (cnt == 0) {
							bootbox.alert("Please select checkbox to view fare chart");
						} else if (cnt > 1) {
							bootbox.alert("Please select one checkbox to view fare chart");
						} else {
							$("input[type='checkbox']:checked").each(
									function() {

										val = this.value;
									});
							/* var val = $("input[type='checkbox']").val(); */
							//alert(val);
							/* document.forms[0].action = 'FarechartTri?id='+ val;
							document.forms[0].submit(); */
							if(isEligibleForOpertion(val)){
							document.getElementById("fid").value = val;
							document.getElementById("form4").submit();
							}else{
								bootbox.alert("Please Select Valid Record");
							}

						}

					});
			
	
			$('#deleteFareChart')
					.click(
						
			function() {
				var cnt = $(":checkbox:checked").length;
				var val;
				if (cnt == 0) {
					bootbox.alert("Please select checkbox to delete");
				} else if (cnt > 1) {
					bootbox.alert("Please select one checkbox to delete");
				} else {
					$("input[type='checkbox']:checked").each(
							function() {

								val = this.value;
								type = 'text';
							});
					if(isEligibleForOpertion(val)){
					bootbox.confirm("Are you Sure, you want to delete this record?",
					function(result) {
					if (result == true) {
					
					/* document.forms[0].action ='deleteFareChart.action?id='+ val;
					document.forms[0].submit(); */
					document.getElementById("did").value = val;
					document.getElementById("form1").submit();
				   }
				});
					}else{
						bootbox.alert("Please Select Valid Record");
					}
				}
			});
	
			function callCopy() {
				var cnt = $(":checkbox:checked").length;
				var val;
				/* if (cnt == 0) {
					alert("Please Select Checkbox To Copy");
				} else if (cnt > 1) {
					alert("Please Select One Checkbox To Copy")
				} else { */
					$("input[type='checkbox']:checked").each(function() {

						val = this.value;
					});

						/* document.forms[0].action = 'FareChartAction!copyFareChart?id='+val;
						document.forms[0].submit(); */
						if(isEligibleForOpertion(val)){
						document.getElementById("cid").value = val;
						document.getElementById("form3").submit();
						}else{
							bootbox.alert("Please Select Valid Record");
						}
				/* } */
			}	
			
		function callCreate() {
			document.forms[0].action = "createRateChart.action";
			document.forms[0].submit();
		}
		
		$(document).ready(function() {

			window.history.pushState("", "", "ShowFareChartAction.action");
			
		});
		function isEligibleForOpertion(id){
			 var isEligible = $('#isRocordEligible'+id).val();
			 if(isEligible == undefined || isEligible=='Y'){
				 return true;
			 }else{
				 return false;
			 }
		}
	</script>
</form>

<form name="form1" id="form1" action="deleteFareChart.action" method="POST">
 	<input type="hidden" name="id" id="did" value="" /> 
 </form>
<form name="form2" id="form2" action="EditFareChartMaser.action" method="POST">
	<input type="hidden" name="chartid" id="eid" value="" />
</form>
<form name="form3" id="form3" action="FareChartAction!copyFareChart" method="POST">
 	<input type="hidden" name="id" id="cid" value="" /> 
 </form>
<form name="form4" id="form4" action="FarechartTri" method="POST">
	<input type="hidden" name="id" id="fid" value="" />
</form>
<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>
