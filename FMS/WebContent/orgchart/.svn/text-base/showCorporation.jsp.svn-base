
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>

window.location.hash = "";
window.onhashchange = function() {
    window.location.hash = "";
}

</script>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
	<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ShowCorporation.action");
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
						CORPORATION  <small></small>
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
								<i class="fa fa-globe"></i>View Corporation
							</div>
							<div class="actions">
							<%if(create.equalsIgnoreCase("Y")){ %>
								<a  class="btn green" tabindex="1" onclick="callCreate()"> <i
								class="fa fa-plus"></i> Create
								
							</a>
							<%}%>
<%if(edit.equalsIgnoreCase("Y")){ %>
							
							<a  class="btn blue" tabindex="2" onclick="callEdit()"> <i
								class="fa fa-pencil"></i> Edit
							</a>
							<%}%>

                <%if(delete.equalsIgnoreCase("Y")){ %>
							
								 <input type="button" class="btn red" tabindex="3" value="Delete"  onclick="calldelete()">
								 <%}%>
						</div>
						</div>
			</div>
						
						
						
							<input type="hidden" name="requestType" id="requestType"
								value="text" />
								
							 <table class="table table-striped table-bordered table-hover"
								id="corporation">
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
										<th>Corporation Id</th>
										<th>Corporation Name</th>
										<th>Corporation Name(Kannada)</th>
										<th>Corporation Code</th>
										<th>Corporation Code(Kannada)</th>
										<th>Corporation Website</th>
										<th>Corporation Address</th>
										<th>Corporation Phone Number</th>
										<th>City</th>
										<th>State</th>
										<th>Country</th>
										<th>Landmark</th>
										<th>Contact Person</th>
										<th>Sector Layer</th>
										<th>Sector for Line Checking</th>
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
	function callEdit(){
		
		var cnt = $(":checkbox:checked").length;
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
			if(isEligibleForOpertion(val)){
			document.getElementById("orgid").value=val;
			document.getElementById("form1").submit();
			/* document.forms[0].action = "editorgchart.action?orgid="+ val;
			document.forms[0].submit(); */
			}else{
				bootbox.alert("Please Select Valid Record");
			}

					
	}
	}
	function calldelete() {
		var cnt = $(":checkbox:checked").length;
		var val;
		if (cnt == 0) {
			bootbox
					.alert("Please Select Checkbox To Delete");
		} else if (cnt > 1) {
			bootbox
					.alert("Please Select One Checkbox To Delete");
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
							document.getElementById("delorgid").value=val;
							document.getElementById("form2").submit();
							/* document.forms[0].action = "deleteorg.action?orgid="+ val;
							document.forms[0].submit(); */
						}
					});
			}else{
				bootbox.alert("Please Select Valid Record");
			}
		

					
	}
		
	}
	function callCreate() {
		document.forms[0].action = "createOrg.action";
		document.forms[0].submit();
	}
	$(document).ready(function() {
		   window.history.pushState("","", "showorgChrart.action");
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
<form name="form1" id="form1" action="editorgchart.action" method="POST">
<input type="hidden" name="orgid" id="orgid" value=""/>
</form>
<form name="form2" id="form2" action="deleteorg.action" method="POST">
<input type="hidden" name="delorgid" id="delorgid" />
</form>
<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>

