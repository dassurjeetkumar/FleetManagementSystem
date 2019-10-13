
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="fare/cssFile/wordwrapcolumn.css" rel="stylesheet" type="text/css"/>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
	<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "LuggageTypeAction!view");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
String status=accessrightdao.UserStatus(usrsessionid);
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
						LUGGAGE TYPE<small></small>
					</h3>
					
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->

			<%-- //<s:hidden name="busid" id="id" ></s:hidden> --%>
			<!-- <input type="hidden" name="busid" id="busid" value="22181"/> -->
			<div class="row">
			<%-- <FONT color="green" style="font-weight:bold"><s:actionmessage /></FONT> --%>
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>View Luggage Type 
							</div>
							<div class="actions">
							<%if(create.equalsIgnoreCase("Y")){ %>
								<a href="#" class="btn green" id="addLuggage"
									onclick="callCreate()"> <i class="fa fa-plus"></i> Create
								</a> 
								<%}%>
                  <%if(edit.equalsIgnoreCase("Y")){ %>
                  <a href="#" class="btn blue" id="editLuggage"
									> <i class="fa fa-pencil"></i>
									Edit
								</a>
								<%}%>

                    <%if(delete.equalsIgnoreCase("Y")){ %> <a href="#" class="btn red" id="deleteLuggage"
									> <i class="fa fa-times"></i> Delete
								</a>
								<%}%>	
								<div class="btn-group">
								
									<button class="btn blue dropdown-toggle" data-toggle="dropdown"><i class="fa fa-gears"></i> Tools <i class="fa fa-angle-down"></i>
									</button>
									<ul class="dropdown-menu pull-right ">
										<li><%if(status.equalsIgnoreCase("ACTIVE")){ %>
											<a class="btn blue" href="javascript: void(0)" onclick="window.open('','_new').location.href='/its/generateReport?id=LUGGAGETYPERPT'">
											<!-- href="/its/ReportAction!generateReport?id=1"> -->
											Report </a><%} %>
										</li>	
										
																			
									</ul> 
							   </div>
							</div>
						</div>

						<div class="portlet-body">
						<FONT color="green" style="font-weight:bold"><s:actionmessage /></FONT>
							<table class="table table-striped table-bordered table-hover"
								id="luggageTypeTable">
								<thead>
									<tr>
										<th style="width1: 8px;"></th>
										<th>Luggage Id</th>
										<th>Luggage Type</th>
										<th>Remarks</th>	
										<th>Status</th>	
										<%if(viewdeletedrecord.equalsIgnoreCase("Y")) {%>	
                                <th>Record Status</th>
                                   <%}%>									
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
	
	<script>
		
			$('#editLuggage')
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
									/* document.forms[0].action = 'LuggageTypeAction!edit?cid='+ val;
									document.forms[0].submit(); */
									if(isEligibleForOpertion(val)){
									document.getElementById("eid").value = val;
									document.getElementById("form2").submit();
									}else{
										bootbox.alert("Please Select Valid Record");
									}
								}

							});
	
	
			$('#deleteLuggage')
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
					
					/* document.forms[0].action ='DeleteLuggageTypeAction.action?cid='+ val ;
					document.forms[0].submit();*/
						document.getElementById("did").value = val;
						document.getElementById("form1").submit();
					}
				});
					}else{
						bootbox.alert("Please Select Valid Record");
					}
				}
			});
	
			function callCreate() {
				document.forms[0].action = "LuggageTypeAction!add";
				document.forms[0].submit();
			}
			
			$(document).ready(function() {

				window.history.pushState("", "", "LuggageTypeAction!view");
				
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
<form name="form1" id="form1" action="DeleteLuggageTypeAction.action" method="POST">
 	<input type="hidden" name="cid" id="did" value="" /> 

 </form>
<form name="form2" id="form2" action="LuggageTypeAction!edit" method="POST">
	<input type="hidden" name="cid" id="eid" value="" />

</form>
<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>

