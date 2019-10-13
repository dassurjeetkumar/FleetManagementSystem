  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  
  <%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
  
  
  <%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ShowBusStopGroup.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
%>
 <form >
<div class="page-content-wrapper">
		<div class="page-content">
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div class="modal fade" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">Modal title</h4>
						</div>
						<div class="modal-body">
							 Widget settings form goes here
						</div>
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
					BUS STOP MAPPING/GROUPING <small></small>
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
								<i class="fa fa-globe"></i>VIEW BUS STOP MAPPING/GROUPING
							</div>
							<div class="actions">
								<%if(create.equalsIgnoreCase("Y")){ %>
							    <a href="GetBusStopGroupMap.action" class="btn green"  id="create"> <!-- //onclick="callEdit()" -->
								<i class="fa fa-plus"></i>Create</a>
								<%}%>
									<%if(edit.equalsIgnoreCase("Y")){ %>
								<a href="#" class="btn blue" id="editgroup">
								<i class="fa fa-pencil">Edit</i></a>
								<%}%>
								<%if(edit.equalsIgnoreCase("Y")){ %>
								
								<a class="btn red" id="deletegroup" onclick="calldelete()">
								<i class="fa fa-times ">Delete</i></a>
								<%} %>
								<!-- <a href="#" class="btn purple"  id="copy"> onclick="callDelete()"
								<i class="fa fa-pencil"></i><i class="fa fa-plus"></i> Copy </a> -->
								
							</div>
						</div>
						
						<div class="portlet-body">
						<s:if test="hasActionErrors()">
   <div class="alert alert-danger">
			<button class="close" data-close="alert"></button>
			<span>
			<s:actionerror/> </span>
		</div>
      
   
</s:if>
						<div class="table-scrollable">
							<table class="table table-striped table-bordered table-hover" id="busStopGroup">
							<thead>
							<tr>
								<td style="width1:8px;">
									
								</td>
								<th>
									 Group Id 
								</th>
								<th>
									 Bus Stop Name
								</th>
								<th>
									 Group Type
								</th>
								<th>
									 Group Name
								</th>
								
								<th>
									 No. Of Bus Stops
								</th>
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
	</div>
	
	</form>
	
	<script>
	$( "#editgroup" ).click(function() {
			
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
				//alert("aaaaa"+ "EditBusStopGroup.action?id="+ val);
				document.forms[0].action = "EditBusStopGroup.action?busstopgroupid="+ val;
				document.forms[0].submit();
			

						
		}
	});
	
	    function calldelete(){
// 	$( "#deletegroup" ).click(function() {
		
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
				bootbox.confirm("Are you sure you want to Delete?",
						
				function(result) {
					if (result == true) {
			//alert("aaaaa"+ "EditBusStopGroup.action?id="+ val);
			document.forms[0].action = "DeleteBusStopGroup.action?busstopgroupid="+ val;
			document.forms[0].submit();
		

					
	}
});
			}else{
				bootbox.alert("Please Select Valid Record");
			}
		}
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
	
<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>
	
	