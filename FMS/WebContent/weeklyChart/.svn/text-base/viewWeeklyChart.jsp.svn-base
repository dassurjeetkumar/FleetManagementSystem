
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
<script type="text/javascript">

	function getSheduleno()
	{	
		 $("#tableid").hide();
		 document.getElementById('select2-chosen-1').innerHTML='select';
		 document.getElementById('scheduleidlist').value=0;
	 //var len= document.getElementById('org_type_id').options.length;	 	 
	      <%--  $.ajax({
	           type: "get",
	           url: '<%=request.getContextPath()%>/findAllSchedule',
	           async:false,
	           
	           success: function(result) {
	        	   document.getElementById('scheduleidlist').innerHTML=result;
	        	  
	           }
	       });
	        --%>
	
	}
	
</script>


</head>
 <body onload="getSheduleno()">
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
	<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "viewWeeklyChart.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
%>
	<form> 
	<div class="page-content-wrapper">
		<div class="page-content">
<!-- 			BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM -->
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
<!-- 					/.modal-content -->
				</div>
<!-- 				/.modal-dialog -->
			</div>

<!-- 			END STYLE CUSTOMIZER -->
<!-- 			BEGIN PAGE HEADER -->
			<div class="row">
				<div class="col-md-12">
<!-- 					BEGIN PAGE TITLE & BREADCRUMB -->
					<h3 class="page-title">
						SCHEDULE WEEKLY CHART <small></small>
					</h3>

<!-- 					END PAGE TITLE & BREADCRUMB -->
				</div>
			</div>
<!-- 			END PAGE HEADER -->
			<!-- BEGIN PAGE CONTENT -->

					<div class="row"> 
				<div class="col-md-12">
<!-- 					BEGIN EXAMPLE TABLE PORTLET -->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>View Schedule Weekly Chart
							</div>
							<div class="actions">
								<!-- <a href="javascript:void(0)" class="btn green"
									onclick="callCreate()"> <i class="fa fa-plus"></i> Create

								</a>  -->
								<%if(edit.equalsIgnoreCase("Y")){ %>
								<a href="javascript:void(0)" class="btn blue"
									 onclick="callEdit()"> <i class="fa fa-pencil"></i> Edit
									 
								</a>
								<%}%> <!-- <a href="javascript:void(0)" class="btn red"
									onclick="calldelete()"> <i class="fa fa-times"></i> Delete
								</a> -->
								

							</div>
						</div>
						<div class="portlet-body"> 
							<input type="hidden" name="requestType" id="requestType"
								value="text" />
								<s:if test="%{insertstaus=='success'}">
									<B><font color="green"><s:actionmessage /></font></B>
								</s:if>
								<s:if test="%{updatestaus=='success'}">
									<B><font color="green"><s:actionmessage /></font></B>
								</s:if>

								<s:if test="%{insertstatus=='fail'}">
									<font color="green">Weekly Chart could not
										Inserted Please Try after Sometime!!</font>
								</s:if>
								<s:if test="%{deletestaus=='fail'}">
									<font color="red">Weekly Chart could not
										Deleted Please Try after Sometime!!</font>
								</s:if>
								<s:if test="%{deletestaus=='success'}">
									<B><font color="green"><s:actionmessage /></font></B>
								</s:if>
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Schedule Number.:</label>
										<div class="col-md-4">
											
											<s:select list="scheduleList" id="scheduleidlist"
													name="schedule.schedule_id"
													cssClass="select2_category form-control" headerKey="0">
													</s:select>
											
										</div>
									</div>
								</div>
								
								<br>
								<br>
							<div class="form-body" id="tableid">
							<table class="table table-striped table-bordered table-hover" id="weeklychartid">
								
								
								<thead>
								
									<tr>
<!-- 										<th style="width1: 8px;"></th> -->
										<th>Form Four Id</th>
										<th>Form Four Name</th>
										<th>Start Date</th>
										<th>End Date</th>
										<th>Monday</th>
										<th>Tuesday</th>
										<th>Wednesday</th>
										<th>Thursday</th>
										<th>Friday</th>
										<th>Saturday</th>
										<th>Sunday</th> 
										<th>Holiday</th> 
										</tr>
								</thead>

							</table>
							</div>

						</div>
					</div>
				</div>
<!-- 				END EXAMPLE TABLE PORTLET -->
			</div>
		</div>

		<!-- END PAGE CONTENT -->
	</div>
<script> 
 		function callEdit() {
 			var schid=document.getElementById("scheduleidlist").value;
 				if(schid==0){
					bootbox.alert("Please Select Schedule Number");
				}else{
					 document.getElementById("form1").submit();
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
				bootbox.confirm("Are you sure, you want to delete this record?",
						function(result) {

									if (result == true) {
										//alert('deleted');
										document.getElementById("delvoucherid").value = val;
										//document.getElementById("form1").action = "editDevice.action";
										document.getElementById("form2").submit();
										/* document.forms[0].action = "deleteDeviceType.action?deviceid="+ val;
										document.forms[0].submit(); */
									}
								});

			}

		}

		function callCreate() {
			document.forms[0].action = "createWeeklyChart.action";
			document.forms[0].submit();
		}
		$(document).ready(function() {
		window.history.pushState("", "", "viewWeeklyChart.action");
		});  
	</script> 
 </form>
 
 <form name="form1" id="form1" action="editWeeklyChart.action" method="POST">
	<input type="hidden" name="scheduleidlist" id="scheduleidlistidd" value="" />
</form> 
</body>

</html>













