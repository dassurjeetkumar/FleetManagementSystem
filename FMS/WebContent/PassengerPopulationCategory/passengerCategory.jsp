
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%-- <%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>


<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "viewPassengerCategory.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
%> --%>
	
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
<%-- <%if (access.equalsIgnoreCase("Y")){ %> --%>
			<!-- END STYLE CUSTOMIZER -->
			<!-- BEGIN PAGE HEADER-->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						PASSENGER POPULATION CATEGORY <small></small>
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
								<i class="fa fa-globe"></i>View Passenger Population Category
							</div>
							<div class="actions">
							<%-- <%if(create.equalsIgnoreCase("Y")){ %> --%>
								<a href="javascript:void(0)" class="btn green" onclick="callCreate()"> <i
									class="fa fa-plus"></i> Create

								</a> 
							<%-- 	<%}%>
								<%if(edit.equalsIgnoreCase("Y")){ %> --%>
								<a href="javascript:void(0)" class="btn blue" onclick="callEdit()"> <i
									class="fa fa-pencil"></i> Edit
								</a> 
								<%-- <%}%>
								<%if(delete.equalsIgnoreCase("Y")){ %> --%>
								<a href="javascript:void(0)" class="btn red" onclick="calldelete()"> <i
									class="fa fa-times"></i> Delete
								</a>
								<%-- <%}%>	 --%>
					<div class="btn-group">
                      <button class="btn green dropdown-toggle"    data-toggle="dropdown"><i class="fa fa-gears"></i> Tools <i class="fa
                                    fa-angle-down"></i> </button>
                                    <ul class="dropdown-menu pull-right ">
                                        <li>
                                    <a class="btn blue" href="javascript: void(0)"onclick="window.open('','_new').location.href='its/ReportAction!generateReport?id=PASSENGERPOPULATIONCATRPT'">
                                        
                                       Report </a>
                                     </li>

                                   </ul>
                           </div>

							</div>
						</div>
						<div class="portlet-body">
							<input type="hidden" name="requestType" id="requestType"
								value="text" />
							<table class="table table-striped table-bordered table-hover"
								id="Passengercat_id">
								<s:if test="%{insertstaus=='success'}">
									<B><font color="green"><s:actionmessage /></font></B>
								</s:if>
								<s:if test="%{updatestaus=='success'}">
									<B><font color="green"><s:actionmessage /></font></B>
								</s:if>

								<s:if test="%{insertstatus=='fail'}">
									<font color="green">Passenger Category could not Inserted
										Please Try after Sometime!!</font>
								</s:if>
								<s:if test="%{deletestaus=='fail'}">
									<font color="red">Passenger Category could not Deleted Please
										Try after Sometime!!</font>
								</s:if>
								<s:if test="%{deletestaus=='success'}">
									<B><font color="green"><s:actionmessage /></font></B>
								</s:if>
								<thead>
									<tr>
										<th style="width1: 8px;"></th>
										<th>Passenger Category Id</th>
										 <th>Passenger Category</th> 
										<th>Remarks</th> 
   									    <th>Status</th>   
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
		function callEdit() {

			var cnt = $(":checkbox:checked").length;
			var val;
			if (cnt == 0) {
				bootbox.alert("Please Select Checkbox To Edit");
			} else if (cnt > 1) {
				bootbox.alert("Please Select One Checkbox To Edit");
			} else {
				$("input[type='checkbox']:checked").each(function() {

					val = this.value;
				});
				document.getElementById("categoryid").value = val;
				//document.getElementById("form1").action = "editDevice.action";
				document.getElementById("form1").submit();

				/* document.forms[0].action = "editDeviceType.action?chartid="+ val;
				document.forms[0].submit(); */

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
				$("input[type='checkbox']:checked").each(function() {

					val = this.value;
				});
				bootbox
						.confirm(
								"Are you sure, you want to delete this record?",
								function(result) {

									if (result == true) {
										//alert('deleted');
										document.getElementById("PassengerCategoryid").value = val;
										///*  */document.getElementById("form1").action = "editDevice.action";
										document.getElementById("form2")
												.submit();
										/* document.forms[0].action = "deleteDeviceType.action?deviceid="+ val;
										document.forms[0].submit(); */
									}
								});

			}

		}

		function callCreate() {
			document.forms[0].action = "createPassengerCategory.action";
			document.forms[0].submit();
		}
		/*  $(document).ready(function() {
			window.history.pushState("", "", "passengerCategoryView.action");
		});   */ 
	</script>
</form>
<form name="form1" id="form1" action="editPassengerCategory.action"
	method="POST">
	<input type="hidden" name="categoryid" id="categoryid" value="" />
</form>
<form name="form2" id="form2" action="deletePassengerCategory.action"
	method="POST">
	<input type="hidden" name="PassengerCategoryid" id="PassengerCategoryid" />
</form>
<%-- <%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%> --%>