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
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ShowRoute.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
%>
  
 <form method="post">
 <input type="hidden" name="routefence" id="routefence"/>
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
					ROUTE <small></small>
					</h3>
					
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			
			<input type="hidden" name="busstopid" id="busstopIdRoute" value='<s:property value="busstopId"/>'/>
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>VIEW ROUTE DETAIL FOR BUSSTOP:<s:property value="busStopName"/>
							</div>
							<div class="actions">
							<%if(create.equalsIgnoreCase("Y")){ %>
							    <a href="GetRouteMap.action" class="btn green"  id="create"> <!-- //onclick="callEdit()" -->
								<i class="fa fa-plus"></i>Create</a>
								<%}%>
							<%if(edit.equalsIgnoreCase("Y")){ %>
								<a href="#" class="btn blue" onclick="callEdit()">
								<i class="fa fa-pencil"></i>Edit</a>
								<%}%>
								<a href="#" class="btn blue" onclick="callView()">
								<i class="fa fa-eye"></i>View</a>
								
								<a href="#" class="btn purple"  id="copy" onclick="callCopy()">   
								<i class="fa fa-pencil"></i><i class="fa fa-plus"></i> Copy </a>
								
								<a href="#" class="btn blue"  onclick="callFence()" >
								<i class="fa fa-pencil"></i> Fence </a> 
									<%if(delete.equalsIgnoreCase("Y")){ %>
								<a href="#" class="btn red" onclick="callDelete()">
								<i class="fa fa-times"></i>Delete</a>
								<%}%>
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
<s:if test="hasActionMessages()">
					<font color="green"> <s:actionmessage />
					
				</font>
					</s:if>
						<div class="table-scrollable">
							<table class="table table-striped table-bordered table-hover" id="route_busstop">
							<thead >
							
								<tr role="row" class="filter">
									<td colspan="3">
									</td>
									
									<td colspan="1">
										 <b>Select Date</b> 
									</td>
									<td colspan="1">  
										<div class="input-group date date-picker margin-bottom-5" data-date-format="dd/mm/yyyy">
											<input type="text" class="form-control form-filter input-sm" readonly name="datecri" id="datecri">
											<span class="input-group-btn">
											<button class="btn btn-sm default" type="button"><i class="fa fa-calendar"></i></button>
											</span>
										</div>
										
									 </td> 
									 <td colspan="1">
										 <div class="margin-bottom-5">
											<button class="btn btn-sm yellow filter-submit margin-bottom"><i class="fa fa-search"></i> Search</button>
										</div> 
									</td>
									<td colspan="5">
										
										
									</td>
								</tr>
								<tr role="row" class="heading">
								<th style="width1:8px;">
									
								</th>
								<th>
									 Route Id 
								</th>
								<th>
									 Route Number
								</th>
								<th>
									 Route Name
								</th>
								<th>
									 Start Bus Stop Name
								</th>
								
								<th>
									 End Bus Stop Name
								</th>
								<th>
									 Route Type
								</th>
								<th>
									 Status
								</th>
								<th>
									 Direction
								</th>
								<th>
									Via
								</th>
								<th>
									Effective From
								</th>
								<th>
									Effective Till
								</th>
								<th>
									GeoFence
								</th>
								</tr>
							</thead>
							<%-- <tbody>
							<s:iterator value="route" id="route">
    <tr>
    <th style="width1:8px;">
									<input type="checkbox" class="group-checkable" data-set="#sample_2 .checkboxes" id='<s:property value="route_id"/>' value='<s:property value="route_id"/>'/>
								</th>
    <td align="center"><s:property value="route_id"/></td>
    <td align="center"><s:property value="route_number"/></td>
    <td align="center"><s:property value="route_name"/></td>
    
    <td align="center"><s:property value="start_point_id"/></td> 
    <td align="center"><s:property value="end_point_id"/></td>
    
    <td align="center"><s:property value="route_type_id"/></td>
    <td align="center"><s:property value="status"/></td>
    
    <td align="center"><s:property value="route_direction"/></td>
    
    <td align="center"><s:property value="via"/></td> 
    
    </tr>
    </s:iterator>
							</tbody>  --%>
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
<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>	<script>
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
				document.forms[0].action = "editRoute.action?routeid="+ val;
				document.forms[0].submit();
		}
		}
		
		function callFence(){
			var cnt = $(":checkbox:checked").length;
			var val;
			if (cnt == 0) {
				bootbox
						.alert("Please Select Checkbox For Fence");
			} else if (cnt > 1) {
				bootbox
						.alert("Please Select One Checkbox For Fence");
			} else {
				$("input[type='checkbox']:checked").each(
						function() {

							val = this.value;
						});
			document.getElementById("routefence").value=val;
			window.open("pages/vts/routeFence.jsp", "GeoFence", "width=500, height=500");	
			}
		}
		
		
		function callView(){
			
			var cnt = $(":checkbox:checked").length;
			var val;
			if (cnt == 0) {
				bootbox
						.alert("Please Select Checkbox To View The Route");
			} else if (cnt > 1) {
				bootbox
						.alert("Please Select One Checkbox To View The Route");
			} else {
				$("input[type='checkbox']:checked").each(
						function() {

							val = this.value;
						});
				document.forms[0].action = "viewRoute.action?routeid="+ val;
				document.forms[0].submit();
			

						
		}
		}
		
		
	function callCopy(){
			
		var cnt = $(":checkbox:checked").length;
		var val;
		if (cnt == 0) {
			bootbox
					.alert("Please Select Checkbox To Copy The Route");
		} else if (cnt > 1) {
			bootbox
					.alert("Please Select One Checkbox To Copy The Route");
		} else {
			$("input[type='checkbox']:checked").each(
					function() {

						val = this.value;
					});
			document.forms[0].action = "copyRoute.action?routeid="+ val;
			document.forms[0].submit();
				//document.forms[0].action = "copyRoute.action";
				//document.forms[0].submit();
			

						
		}
	}
	
	function callDelete(){
		
		var cnt = $(":checkbox:checked").length;
		var val;
		if (cnt == 0) {
			bootbox
					.alert("Please Select Checkbox To Delete The Route");
		} else if (cnt > 1) {
			bootbox
					.alert("Please Select One Checkbox To Delete The Route");
		} else {
			$("input[type='checkbox']:checked").each(
					function() {

						val = this.value;
					});
			/* document.forms[0].action = "deleteRoute.action?routeid="+ val;
			document.forms[0].submit(); */
			bootbox.confirm("Are You Sure You Want To Delete This Route?",
					function(result) {

						if (result == true) {
							document.forms[0].action = "deleteRoute.action?routeid="+ val;
							document.forms[0].submit();
						}
			});
		

					
	}
	}
	
	function callajax(){
		 $('#route').dataTable({
         	"aaSorting" : [ [ 1, 'asc' ] ],
				"aLengthMenu" : [ [ 10, 25, 50, 100 ], [ 10, 25, 50, 100 ] // change
																	// per page
																	// values
																	// here
				],
				
				// set the initial value
				"iDisplayLength" : 10,
				"bProcessing" : true,
				"bServerSide" : true,
				"sAjaxSource" : "RouteAjaxAction.action?datecri="+document.getElementById("datecri").value,
				"sPaginationType" : "bootstrap",
				"oLanguage" : {
					"sLengthMenu" : "_MENU_ records",
					"oPaginate" : {
						"sPrevious" : "Prev",
						"sNext" : "Next"
					}
				},
				"aoColumnDefs" : [ {
					'bSortable' : false,
					'aTargets' : [ 0 ]
				}, {
					"bSearchable" : false,
					"aTargets" : [ 0 ]
				} ]
				/*"datecri" : $("#datecri").val()*/
			});
			jQuery('#route_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
         jQuery('#route_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
      
         
	}
		
	</script>
	
	<script>
	var date='<%= request.getSession().getAttribute("route_filter_date") %>';
	//alert(date);
	$("#datecri").val(date);	
	//document.getElementById("datecri").value(date);
	</script>
	