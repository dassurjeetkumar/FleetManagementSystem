
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>


<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "trainingtypelist.action");
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

			<!-- END STYLE CUSTOMIZER -->
<%if (access.equalsIgnoreCase("Y")){ %>
			<!-- BEGIN PAGE HEADER-->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						TRAINING TYPE<small></small>
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
								<i class="fa fa-globe"></i>View Training Type
							</div>
							<div class="actions">
							<%if(create.equalsIgnoreCase("Y")){ %>
							<a href="javascript:void(0)" class="btn green" onclick="callCreate()"> <i
								class="fa fa-plus"></i> Create
								
							</a>
								<%}%>
								<%if(edit.equalsIgnoreCase("Y")){ %>
							 <a href="javascript:void(0)" class="btn blue" onclick="callEdit()"> <i
								class="fa fa-pencil"></i> Edit
							</a>
							<%}%>
								<%if(delete.equalsIgnoreCase("Y")){ %>
								 <a href="javascript:void(0)" class="btn red" id="deletetraining" onclick="calldelete()">
									<i class="fa fa-times"></i> Delete
								</a>
								<%}%>	
								
							<div class="btn-group">
								
									<button class="btn green dropdown-toggle" data-toggle="dropdown"><i class="fa fa-gears"></i> Tools <i class="fa fa-angle-down"></i>
									</button>
									<ul class="dropdown-menu pull-right ">
										<li>
											<a class="btn blue" href="javascript: void(0)" onclick="window.open('','_new').location.href='/its/generateReport?id=TRAININGTYPERPT'">
											<!-- href="/its/ReportAction!generateReport?id=1"> -->
											Report </a>
										</li>										
									</ul> 
							</div>
							</div>
						</div>
						<div class="portlet-body">
						<span id="errorMsg" style="color:red;"><s:actionerror/></span>
							<input type="hidden" name="requestType" id="requestType"
								value="text" />
								<div class="col-md-12" style="color: green; list-style: none;font-weight:bold;font-size:1.1em" align="left" >
							<s:if test="%{insertstatus=='success'}">
								<FONT color="green"><s:actionmessage/></FONT>
									</s:if>
							<s:if test="%{deletestatus=='success'}">
							<FONT color="green"><s:actionmessage/></FONT>
								</s:if>
							<s:if test="%{updatestatus=='success'}">
							<FONT color="green"><s:actionmessage/></FONT>
								</s:if>
							<s:if test="%{deletestatus=='fail'}">
								Could Not Delete Training Type 
							</s:if>
							</div>
							<table class="table table-striped table-bordered table-hover"
								id="listtrainingtype">
								<thead>
									<tr>
										<th style="width1: 8px;"></th>
										<th>Training Type Id</th>
										<th>Training Type Name</th>
										<th>Status</th>
										<th>Remarks</th>
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
	
	function calldelete() {
		var cnt = $(":checkbox:checked").length;

		if (cnt == 0) {
			bootbox.alert("Please Select Checkbox To Delete");
		} else if (cnt > 1) {
			bootbox.alert("Please Select One Checkbox To Delete");
		} else {
			$("input[type='checkbox']:checked").each(function() {

				val = this.value;
			});
			if(isEligibleForOpertion(val)){

			bootbox.confirm("Are you Sure, you want to delete this record?",
							function(result) {

								if (result == true) {
									document.getElementById("deltrainingtypeid").value = val;
									document.getElementById("form2")
											.submit();
								}
							});
			}else{
				bootbox.alert("Please Select Valid Record");
			}
		}

	}


	function callEdit() {
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
			if(isEligibleForOpertion(val)){
			document.getElementById("edittrainingtypeid").value = val;
			document.getElementById("form1").submit();
			}else{
				bootbox.alert("Please Select Valid Record");
			}
		}
	
		
	}		

		function callCreate() {
			document.forms[0].action = "createTrainingType.action";
			document.forms[0].submit();
		}
		$(document).ready(function() {
			   window.history.pushState("","", "trainingtypelist.action");
			   var w=$('#errorMsg span').html();
			   //alert(w);
			    w=w.replace(/@/g,"<br>");

			   $('#errorMsg').html(''+w+'');
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
<form name="form1" id="form1" action="ModifyTrainingTypeDetails" method="POST">
	<input type="hidden" name="edittrainingtypeid" id="edittrainingtypeid" value="" />

</form>
<form name="form2" id="form2" action="DeleteTrainingTypeDetails.action"
	method="POST">
	<input type="hidden" name="deltrainingtypeid" id="deltrainingtypeid" />
</form>

<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>

<%}%>