
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
	
<script>

window.location.hash = "";
window.onhashchange = function() {
    window.location.hash = "";
}

</script>
	<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "AllBusStations.action");
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
						BUS STATIONS <small></small>
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
								<i class="fa fa-globe"></i>View Bus Stations
							</div>
							<div class="actions">
							<%if(create.equalsIgnoreCase("Y")){ %>
								<a href="javascript:void(0)" class="btn green" onclick="callCreate()"> <i
								class="fa fa-plus"></i> Create
								
							</a>
							<%}%>
<%if(edit.equalsIgnoreCase("Y")){ %>
							<a href="#" class="btn blue" onclick="callEdit()"> <i
								class="fa fa-pencil"></i> Edit
							</a><%}%>

<%if(delete.equalsIgnoreCase("Y")){ %>
								 <a href="javascript:void(0)" class="btn red" id="deleteDevice" onclick="calldelete()">
									<i class="fa fa-times"></i> Delete
								</a><%}%>	
							<a href="#" class="btn green" onclick="callFloor()"> <i
								class="fa fa-plus"></i> AddFloors
							</a>
							<a href="#" class="btn green" onclick="callBay()"> <i
								class="fa fa-plus"></i> AddBays
							</a>
							<a href="#" class="btn green" onclick="callPlatform()"> <i
								class="fa fa-plus"></i> AddPlatForm
							</a>

							</div>
						</div>
					
				
			</div>
						
						
						
							<input type="hidden" name="requestType" id="requestType"
								value="text" />
								
							 <table class="table table-striped table-bordered table-hover"
								id="Showbusstations">
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
										<th>Bus Station Id</th>
										<th>Bus Station Name</th>
										<th>Bus Station Name(Kannada)</th>
										<th>Bus Station Code</th>
										<th>Bus Station Code(Kannada)</th>
										<th>Depot/Division Name</th>
										<th>Bus Station Website</th>
										<th>Bus Station Address</th>
										<th>Bus Station Phone Number</th>
										<th>City</th>
										<th>State</th>
										<th>Country</th>
										
										<th>Landmark</th>
										<th>Contact Person</th>
										 <th>Sector Layer</th>
										<th>Sector for Line Checking</th>
 									<%if(viewdeletedrecord.equalsIgnoreCase("Y")) {%>	<th>Record Status</th>
 <%}%>
	 							 -
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
			/* document.forms[0].action = "editbusstation.action?orgid="+ val;
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
							document.getElementById("delorgid").value=val;
							document.getElementById("form2").submit();
							/* document.forms[0].action = "deleteorg.action?orgid="+ val;
							document.forms[0].submit(); */
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
		document.forms[0].action = "createBusstations.action";
		document.forms[0].submit();
	}
	
	function callFloor() {
		var cnt = $(":checkbox:checked").length;
		var val;
		if (cnt == 0) {
			bootbox
					.alert("Please Select One Bus Stations to Add Floor");
		} else if (cnt > 1) {
			bootbox
					.alert("Please Select One Bus Stations to Add Floor");
		} else {
			$("input[type='checkbox']:checked").each(
					function() {

						val = this.value;
					});
			if(isEligibleForOpertion(val)){
			document.getElementById("florgid").value=val;
			document.getElementById("form3").submit();
			/* document.forms[0].action = "FloorAction!addbus?orgid="+ val;
			document.forms[0].submit(); */
			}else{
				bootbox.alert("Please Select Valid Record");
			}

					
	}
		
	}

	function callBay() {
		var cnt = $(":checkbox:checked").length;
		var val;
		if (cnt == 0) {
			bootbox
					.alert("Please Select One Bus Stations to Add Bay");
		} else if (cnt > 1) {
			bootbox
					.alert("Please Select One Bus Stations to Add Bay");
		} else {
			$("input[type='checkbox']:checked").each(
					function() {

						val = this.value;
					});
			if(isEligibleForOpertion(val)){
			document.getElementById("bayorgid").value=val;
			document.getElementById("form4").submit();
			/* document.forms[0].action = "BayAction!addBus?orgid="+ val;
			document.forms[0].submit(); */
			}else{
				bootbox.alert("Please Select Valid Record");
			}

					
	}
		
	}
	function callPlatform() {
		var cnt = $(":checkbox:checked").length;
		var val;
		if (cnt == 0) {
			bootbox
					.alert("Please Select One Bus Stations to Add Platform");
		} else if (cnt > 1) {
			bootbox
					.alert("Please Select One Bus Stations to Add Platform");
		} else {
			$("input[type='checkbox']:checked").each(
					function() {

						val = this.value;
					});
			if(isEligibleForOpertion(val)){
			document.getElementById("plorgid").value=val;
			document.getElementById("form5").submit();
			/* document.forms[0].action = "PlatformActionForBusStation?orgid="+ val;
			document.forms[0].submit(); */
			}else{
				bootbox.alert("Please Select Valid Record");
			}

					
	}
		
	}
	
	$(document).ready(function() {
		   window.history.pushState("","", "AllBusStations.action");
		   
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
<form name="form1" id="form1" action="editbusstation.action" method="POST">
<input type="hidden" name="orgid" id="orgid" value=""/>
</form>
<form name="form2" id="form2" action="deletebus.action" method="POST">
<input type="hidden" name="delorgid" id="delorgid" />
</form>
<form name="form3" id="form3" action="FloorAction!addbus" method="POST">
<input type="hidden" name="florgid" id="florgid" value=""/>
</form>
<form name="form4" id="form4" action="BayAction!addBus" method="POST">
<input type="hidden" name="bayorgid" id="bayorgid" />
</form>
<form name="form5" id="form5" action="PlatformActionForBusStation" method="POST">
<input type="hidden" name="plorgid" id="plorgid" />
</form>
<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>

