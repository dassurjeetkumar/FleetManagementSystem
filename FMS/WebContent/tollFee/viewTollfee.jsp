
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
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "Tollfeeaction!view");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String status=accessrightdao.UserStatus(usrsessionid);
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");

%>
<form method="post">
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
			<%if (access.equalsIgnoreCase("Y")){ %>
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						TOLL FEE MASTER <small></small>
					</h3>
				
				
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
								<i class="fa fa-globe"></i>View Toll Fee Master
								
							</div>
							<div class="actions">
								<%if(create.equalsIgnoreCase("Y")){ %>
								<a href="#" class="btn green" id="createTollfee"
									onclick="callCreate()"> <i class="fa fa-plus"></i> Create
								</a> 
								<%}%>
								<%if(edit.equalsIgnoreCase("Y")){ %>
								<a href="#" class="btn blue" id="editTollfee"
									> <i class="fa fa-pencil"></i>
									Edit
								</a>
								<%}%>
							
								 <a href="#" class="btn blue"  id="copyTollfee" onclick="callCopy()">
								<i class="fa fa-pencil"></i> Copy </a> 
								
									<%if(delete.equalsIgnoreCase("Y")){ %>
								<a href="#" class="btn red" id="deleteTollfee"> <i class="fa fa-times"></i> Delete
								</a>
								<%}%>	
								
								<!-- <div class="btn-group">
								<a class="btn blue" href="#" data-toggle="dropdown">
									Tools <i class="fa fa-angle-down"></i>
								</a> -->

						<div class="btn-group">
                      <button class="btn green dropdown-toggle"	data-toggle="dropdown"><i class="fa fa-gears"></i> Tools <i class="fa
									fa-angle-down"></i> </button>
                                    <ul class="dropdown-menu pull-right "> 
                                        <li>
                                        <%if(status.equalsIgnoreCase("ACTIVE")){ %>
                                    <a class="btn blue" href="javascript: void(0)"onclick="window.open('','_new').location.href='/its/generateReport?id=TOLLFEEMASTERRPT'">
                                        
                                       Report </a>
                                       <%} %>
                                     </li>

                                   </ul>
                           </div>

							</div>

							</div>
						

						<div class="portlet-body">
							<b><FONT color="green"><s:actionmessage /></FONT></b>
							
							<table class="table table-striped table-bordered table-hover"
								id="tollFeeTable">
								<thead>
									<tr>
										<th style="width1: 8px;"></th>
										<th>Toll Fee Master Id</th>
										<th>Service Type</th>
										<th>Amount</th>
										<th>Effective Start Date</th> 
										<th>Effective End Date</th>
										<th>Version Number</th>	
										<th>Bus Stop</th>
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
		
			$('#editTollfee')
					.click(
							function() {
								var cnt = $(":checkbox:checked").length;
								var val;
								if (cnt == 0) {
									alert("Please select checkbox to edit");
								} else if (cnt > 1) {
									alert("Please select one checkbox to edit");
								} else {
									$("input[type='checkbox']:checked").each(
											function() {

												val = this.value;
												if(isEligibleForOpertion(val)){
												//document.getElementById("tid").value=val;
												//document.getElementById("form2").submit();
												document.getElementById("tid").value = val;
												document.getElementById("form2").submit();
												}else{
													bootbox.alert("Please Select Valid Record");
												}

											});
									
									//"<input type=hidden name=aid  value="+val+" />";  
									/* var val = $("input[type='checkbox']").val(); */
									//alert(cid);
									
									
									
									//type: 'post',
									/* document.forms[0].action = 'AccountHeadaction!edit';
									
									document.forms[0].submit(); */
									

								}

							});
	         
			$('#deleteTollfee')
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
								
							//document.forms[0].action ='DeleteTollfeeaction.action?cid='+ val ;
							
							document.getElementById("tid3").value = val;
							document.getElementById("form1").submit();
							//document.forms[0].submit();
										   }
								});
							}else{
								bootbox.alert("Please Select Valid Record");
							}
						}
						
					});		
			$('#copyTollfee')
			.click(
					function() {
						var cnt = $(":checkbox:checked").length;
						var val;
						if (cnt == 0) {
							bootbox.alert("Please select checkbox to copy");
						} else if (cnt > 1) {
							bootbox.alert("Please select one checkbox to copy");
						} else {
							

							$("input[type='checkbox']:checked").each(
									function() {

										val = this.value;
										if(isEligibleForOpertion(val)){
										document.getElementById("tid1").value=val;
										document.getElementById("form3").submit();
										}else{
											bootbox.alert("Please Select Valid Record");
										}
									});
							
							//"<input type=hidden name=aid  value="+val+" />";  
							/* var val = $("input[type='checkbox']").val(); */
							//alert(cid);
// 							if(isEligibleForOpertion(val)){
// 							//document.getElementById("tid1").value = val;
// 							document.getElementById("form3").submit();
							
							
// 							//type: 'post',
// 							/* document.forms[0].action = 'AccountHeadaction!edit';
							
// 							document.forms[0].submit(); */
// 							}else{
// 								bootbox.alert("Please Select Valid Record");
// 							}

						}

					});
	
			
	
			
			
		function callCreate() {
			document.forms[0].action = "Tollfeeaction!add";
			document.forms[0].submit();
		}
		
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
<form name="form2" id="form2" action="Tollfeeaction!edit" method="POST">
	<input type="hidden" name="tid" id="tid" />
	</form>
	<form name="form3" id="form3" action="Tollfeeaction!copy" method="POST">
	<input type="hidden" name="tid1" id="tid1" />
	</form>
	
	<form name="form1" id="form1" action="DeleteTollfeeaction.action" method="POST">
	<input type="hidden" name="tid3" id="tid3" />
	</form>
	
	<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>