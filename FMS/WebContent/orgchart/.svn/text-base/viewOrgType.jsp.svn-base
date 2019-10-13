
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
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "orgtypeview!view");
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
<%if (access.equalsIgnoreCase("Y")){%>
	<div class="page-content-wrapper">
		<div class="page-content">
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<!-- END STYLE CUSTOMIZER -->
			<!-- BEGIN PAGE HEADER-->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						ORGANIZATION TYPE <small></small>
					</h3>

					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div> <small></small>
					</h3>

					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey-cascade">
					<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>View Organization Type
							</div>
							<div class="actions">
							<%if(create.equalsIgnoreCase("Y")){ %>
								<a href="#" class="btn green" onclick="callCreate()"> <i
								class="fa fa-plus"></i> Create
								
							</a><%}%>
<%if(edit.equalsIgnoreCase("Y")){ %>
							
							<a href="#" class="btn blue" onclick="callEdit()"> <i
								class="fa fa-pencil"></i> Edit
							</a><%}%>

<%if(delete.equalsIgnoreCase("Y")){ %>
							
								 <a  class="btn red" id="deleteDevice" onclick="calldelete()">
									<i class="fa fa-times"></i> Delete
								</a>
								<%}%>	
							<div class="btn-group">
								
									<button class="btn green dropdown-toggle" data-toggle="dropdown"><i class="fa fa-gears"></i> Tools <i class="fa fa-angle-down"></i>
									</button>
									<ul class="dropdown-menu pull-right ">
										<li>
											<a class="btn blue" href="javascript: void(0)" onclick="window.open('','_new').location.href='/its/generateReport?id=ORGTYPERPT'">
											<!-- href="/its/ReportAction!generateReport?id=1"> -->
											Report </a>
										</li>	
										
																			
									</ul> 
							</div>

							</div>
						</div>
						
					</div>
					<b>
<%-- 								<font color="green"> <s:actionmessage/></font> --%>
								<font color="red"> <s:actionerror/></font>
							</b>
					<input type="hidden" name="requestType" id="requestType"
						value="text" />
					<input type="hidden" id="orgid"/>
					<table class="table table-striped table-bordered table-hover"
						id="orgType">
						 <s:if test="%{insertmsg=='success'}"><b><FONT color="green"><s:actionmessage /></FONT></b></s:if>
								<s:if test="%{insertmsg=='database'}"><b><FONT color="red"><s:actionmessage /></FONT></b></s:if>
								<s:if test="%{updatemsg=='database'}"><b><FONT color="red"><s:actionmessage /></FONT></b></s:if>
								<s:if test="%{updatemsg=='success'}"><b><FONT color="green"><s:actionmessage /></FONT></b></s:if>
								<s:if test="%{deletestatus=='database'}"><b><FONT color="red"><s:actionmessage /></FONT></b></s:if>
								<s:if test="%{deletestatus=='success'}"><b><FONT color="green"><s:actionmessage /></FONT></b></s:if> 
								 <s:if test="%{deletestatus=='fail'}"><b><FONT color="red"><span id="errorMsg"><s:actionmessage /></span></FONT></b></s:if>
						<thead>
							<tr>
								<th style="width1: 8px;"></th>
								<th>Organization Type Id</th>
								<th>Organization Type Name</th>
								<th>Remarks</th>
								<th>Status</th>
<%-- 								<%if(viewdeletedrecord.equalsIgnoreCase("Y")) {%>	 --%>
<!-- <th>Record Status</th> -->
<%-- <%}%> --%>
								
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
		 function callEdit(){
			
			var cnt= $(":checkbox:checked").length;
			var val;
			if (cnt == 0) {
				bootbox
						.alert("Please Select Checkbox To Edit");
			} else if (cnt > 1) {
				bootbox
						.alert("Please Select One Checkbox To Edit");
			} else {
				$("input[type='checkbox']:checked").each(
						function() {

							val = this.value;
						});
				//document.getElementById("orgid").value=val;
				document.forms[0].action = "editorgtype.action?orgid="+ val;
				document.forms[0].submit();
			

						
		}
		}
		function calldelete() {
			var cnt = $(":checkbox:checked").length;
			var val;
			if (cnt == 0) {
				bootbox.alert("Please Select Checkbox To Delete");
			} else if (cnt > 1) {
				bootbox.alert("Please Select One Checkbox To Delete");
			} else {
				$("input[type='checkbox']:checked").each(
						function() {

							val = this.value;
						});
				if(isEligibleForOpertion(val)){
				bootbox.confirm("Are you sure, you want to delete this record?",
						function(result) {

							if (result == true) {
								//alert('deleted');
								document.getElementById("orgid").value=val;
								document.getElementById("form1").submit();
							    document.forms[0].action = "deleteorgType.action?orgid="+ val;
								document.forms[0].submit(); 
							}
						});
				}else{
					bootbox.alert("Please Select Valid Record");
				}
			
						
		}// window.history.forward();
			//window.history.pushState("", "", "AllBusStations.action");
			//window.location.reload(true);	
		}
		function callCreate() {
			document.forms[0].action = "createOrgType.action";
			document.forms[0].submit();
		} 
		$(document).ready(function() {
			window.history.pushState("", "", "orgtypeview!view");
// 			  var w=$('#errorMsg span').html();
// 			   //alert(w);
// 			    w=w.replace(/@/g,"<br>");

// 			   $('#errorMsg').html(''+w+'');
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
<form name="form1" id="form1" action="deleteorgType.action" method="POST">
<input type="hidden" name="orgid" id="orgid" value=""/>
</form>
<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>