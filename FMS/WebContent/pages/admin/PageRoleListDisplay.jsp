<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
	<head>
		<script type="text/javascript" src="//www.google.com/jsapi"></script>
		<script type="text/javascript">
			function windowopen() {
				//document.getElementById("deviceid").value = DEVICE_ID;
				
			// 	var roleid=document.getElementById("roleid").value;
			// 	alert(roleid);
			// 	 $.ajax({
				        
			//          type: "post",
			<%--          url: '<%=request.getContextPath()%>/AjaxPageRoleAction.action?roleiddetails='+roleid, --%>
			//          success: function(result) {
			         	
			//          }
			//      });
				
				
			// 	window.open("/AjaxPageRoleAction.action" ,"self");
				document.forms["rolenameform"].submit();
			}
			
				$(document).ready(function(){
					console.log("Jquery loaded");
					getAllRecords();
					$("input[type='checkbox']").each(function(){
						$(this).attr('checked', true);
					});
					
					$("#selectAllReadCheckBox").click(function(){
						console.log("Checkbox clicked...");
						if($(this).prop("checked")){
							console.log("All read cb selected");
							$(".readCBClass").attr('checked', true);
							$(".readCBClass").parent().addClass("checked");
						}
						else{
							console.log("All read cb de selected");
							$(".readCBClass").attr('checked', false);
							$(".readCBClass").parent().removeClass("checked");
						}
					});
					
					$("#selectAllWriteCheckBox").click(function(){
						console.log("Checkbox clicked...");
						if($(this).prop("checked")){
							console.log("All read cb selected");
							$(".writeCBClass").attr('checked', true);
							$(".writeCBClass").parent().addClass("checked");
						}
						else{
							console.log("All read cb de selected");
							$(".writeCBClass").attr('checked', false);
							$(".writeCBClass").parent().removeClass("checked");
						}
					});
					
					$(".readCBClass").click(function(){
						console.log("Checkbox clicked...");
						var areAllChecked = $('.readCBClass:not(#selectAllReadCheckBox):checked').length == $('.readCBClass:not(#selectAllReadCheckBox)').length;
			            $('#selectAllReadCheckBox').attr('checked',areAllChecked);
			            
			            if(areAllChecked){
			            	$('#selectAllReadCheckBox').parent().addClass("checked");	
			            }
			            else{
			            	$('#selectAllReadCheckBox').parent().removeClass("checked");
			            }
					});
					
					$(".writeCBClass").click(function(){
						console.log("Checkbox clicked...");
						var areAllChecked = $('.writeCBClass:not(#selectAllWriteCheckBox):checked').length == $('.writeCBClass:not(#selectAllWriteCheckBox)').length;
			            $('#selectAllWriteCheckBox').attr('checked',areAllChecked);
			            if(areAllChecked){
			            	$('#selectAllWriteCheckBox').parent().addClass("checked");	
			            }
			            else{
			            	$('#selectAllWriteCheckBox').parent().removeClass("checked");
			            }
					});
					
				});
				
				function getAllRecords() {
					var rolid=document.getElementById("pageroleid").value;
					//alert("rolid--"+document.getElementById("pageroleid").value);
					$.ajax({
						type: "POST",
						async:false,
						url: "GetPagelist.action?rolid="+rolid,
						success: function(response){
							var arr = response.split(',');
							//alert("arr--"+arr);
							
							var tableData = "";
							var totalNoOfRecords = 0;
							for(var i=0; i<arr.length; i++){
								var pageId = (arr[i]).substring(0, (arr[i]).indexOf("@"));
								var roleData = (arr[i]).substring((arr[i]).indexOf("@") + 1);
								console.log("pageId : " + pageId);
								
								if(pageId != null && pageId != "" && pageId != undefined){
									tableData += "<tr><td> " + pageId + "<input type='hidden' name='pageid" + i + "' value='" + pageId + "'/></td><td>" + roleData + "<input type='hidden' name='roleData" + i + "' value='" + roleData + "'/></td><td><input type='checkbox' class='readCBClass' name='readCB" + i +"' value='1'/></td><td><input type='checkbox' class='writeCBClass' name='writeCB" + i + "' value='1'/></td></tr>";
									totalNoOfRecords++;
								}	
							}
							
							
							
							$("#noOfRecords").val(totalNoOfRecords - 1);
							console.log("response data : " + arr);
							console.log("tableData data : " + tableData);
							$("#viewpageroledetails tbody").html(tableData);
						}
					});
				}
		</script>
		<script>
		$('#cancelpagerole').click(function (e) {
			 document.forms[0].action = "cancelRole.action?roleid="+roleid;
				document.forms[0].submit();
		 });
		</script>
	</head>
	<body>
		<input type="text" id='a'>
		<div class="page-content-wrapper">
			<div class="page-content">
				<div class="tab-content">
					<div class="tab-pane active" id="tab_0">
						<div class="col-md-12">
							<!-- BEGIN PAGE TITLE & BREADCRUMB-->
							<h3 class="page-title">
								USER ROLE <small></small>
							</h3>
							<font color="green">
								<s:actionmessage/>
							</font>
							<!-- END PAGE TITLE & BREADCRUMB-->
						</div>
						<div class="portlet box grey-cascade">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-gift"></i>Page Access Form
								</div>
							</div>
							<div class="portlet-body form" style="position: relative;">
								<s:if test="hasActionErrors()">
									<div class="alert alert-danger">
										<button class="close" data-close="alert"></button>
										<span>
											<s:actionerror />
										</span>
									</div>
								</s:if>
								<!-- BEGIN FORM-->
								<!--<form  class="form-horizontal"
									method="post">-->
								<div class="form-group">
									<% String rolename="";%>
									<% String roleid="";%>
									<%
										// rolename=request.getParameter("rolename");
										  roleid=request.getParameter("pageroleid");
											//String pageRoleId = request.getParameter("pageroleid");
										%>
									<label class="col-md-3 control-label">
										Role Name:   
										<b>
											<s:property value="role.role_name"/>
										</b>
									</label>
									<div class="col-md-4">
										<input type="hidden" name="roleid" id="roleid" value='<%=roleid%>' >
									</div>
								</div>
								<!--<div class="table-toolbar">
									<div class="btn-group">
										<button id="pageNew" class="btn green">
										Add New Page <i class="fa fa-plus"></i>
										</button>
									</div>
									<div class="btn-group">
										<a href="#" class="btn green" onclick="windowopen();">
										Add New Page <i class="fa fa-plus"></i>
										</a>
									</div>
								</div>-->
								<div  id="successmsg"
									style="display: none">
									<b>
										<p id="successpagerole" style="color: green"></p>
										<span> </span> 
									</b>
								</div>
								<div class="alert alert-danger" id="errormsg"
									style="display: none">
									<b>
										<p class="intro" id="errorpagerole"></p>
									</b>
								</div>
								<input type="hidden" name="requestType" id="requestType"
									value="text" />
								<s:form action="/addMultiplePageRoledetails.action"  method="post">
									<s:hidden name="noOfRecords" value="" id="noOfRecords"></s:hidden>
									<s:hidden name="pageroleid" value="%{pageroleid}" id="pageroleid"></s:hidden>
									<table class="table table-striped table-bordered table-hover"
										id="viewpageroledetails">
										<thead>
											<tr>
												<th>Page Id</th>
												<th>Page Name</th>
												<th><input type='checkbox' class="selectAllReadCheckBox" id="selectAllReadCheckBox"/>&nbsp;&nbsp;Read</th>
												<th><input type='checkbox' class="selectAllWriteCheckBox" id="selectAllWriteCheckBox"/>&nbsp;&nbsp;Write</th>
<!-- 												<th>Add</th> -->
<!-- 												<th>Delete</th> -->
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
									<s:submit value="save" cssClass="btn green"></s:submit>
								</s:form>
								<!-- <div class="form-actions fluid"> -->
								<!-- <button id="cancelpagerole" type="button" class="btn default" ></button>-->
								<div style="width: auto; position: absolute; bottom: 0px;">
									<s:form action="/cancelMultiplePageRoledetails.action"  method="post">
										<s:hidden name="noOfRecords" value="" id="noOfRecords"></s:hidden>
										<s:hidden name="pageroleid" value="%{pageroleid}" id="pageroleid"></s:hidden>
										
									
										<s:submit value="cancel" cssClass="btn default"></s:submit>
									</s:form>
								</div>
<!-- 								<a href="addPageAccess"  class="btn default" >Cancel</a> -->
								<!-- </div> -->
								<!--</form>-->
								<!-- END FORM-->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
