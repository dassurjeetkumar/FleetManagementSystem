<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<!DOCTYPE html>
<html>
<head>
<script>

$(document).ready(function() {
 window.history.pushState("", "", "AddPlayListAction.action");
});

function getAdvertNames()
{
    var len= document.getElementById('advertId').options.length;
    
	 //if(len<=1 ) {
       $.ajax({
           type: "post",
           async:false,
           url: '<%=request.getContextPath()%>/getAdverList',
           async:false,
           success: function(result) {  	  
               document.getElementById('advertId').innerHTML=result;
           }
       });
}
</script>
<title></title>
</head>
<body onload="getAdvertNames()">
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "playListView.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
%>

	<div class="page-content-wrapper">
		<div class="page-content">
<%if (access.equalsIgnoreCase("Y")){%>
<%if(create.equalsIgnoreCase("Y")){ %>
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
				<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						PLAY LIST <small></small>
					</h3>
					<font color="green"><s:actionmessage/></font>
				</div>
				</div>
				
					<div class="portlet box grey-cascade">
					
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Create Play List
							</div>
							<div class="tools">
								
							</div>
						</div>
						<div class="portlet-body form">
							&nbsp;&nbsp;&nbsp;&nbsp;<font color="red"><span id="integerVal"></span></font>
							
							<form action="SavePlayListAction.action" class="form-horizontal" method="post">
								<font color="red"><s:actionerror/></font>  <br>
								
								<div class="form-group">
								<label class="col-md-3 control-label"> Play List Name :<font color="red">*</font></label>
								<div class="col-md-4">
									<input type="text" class="form-control" name="playList.playListName" maxLength="20"
										value="<s:property value="playList.playListName"/>">
									 <s:if test="fieldErrors.get('playListName').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('playListName').get(0)" /></span>
									</s:if> 
								</div>
							    </div>
							    
							    <div class="form-group">
								<label class="col-md-3 control-label">Select Advertisement Name:<font color="red">*</font></label>
								<div class="col-md-4">
								<%-- <s:select list="advertisementMap" id="advertisement_id" name="playList.advertisementList" 
						cssClass="select2_category form-control" headerKey="0" headerValue="Select" multiple ="multiple"> --%>
						<select class="select2_category form-control"
													name="playList.advertisementList"
													id="advertId" style="width: 200px;" multiple="multiple">
						</select>
						<s:if test="fieldErrors.get('advertisementList').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('advertisementList').get(0)" /></span>
									</s:if>
								<%-- </s:select> --%>
								</div>
							    </div>
							    
							    <div class="form-group">
								<label class="col-md-3 control-label">Start Date Time :<font color="red">*</font></label>
								<div class="col-md-4">
									<div class="input-group date form_datetime_bmtc_format" >
									<input type="text" size="16" readonly name="playList.playStartTime" class="form-control" value="<s:property value='playList.playStartTime'/>">
													<span class="input-group-btn">
														<button class="btn default date-set" type="button">
															<i class="fa fa-calendar"></i>
														</button>
													</span>
													<%-- <script>
												var curr_date=new Date().toJSON().slice(0,10);
												alert('curr_date='+curr_date);
		                                        curr_date=curr_date.split("-");
		                                        curr_date=curr_date[2]+"/"+curr_date[1]+"/"+curr_date[0];
		                                        $('#effect_start_date').val(curr_date);
											</script> --%>
								   </div>
									<s:if test="fieldErrors.get('playStartTime').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('playStartTime').get(0)" /></span>
									</s:if> 
								</div>
							    </div>
							    
							    <div class="form-group">
								<label class="col-md-3 control-label">End Date Time :</label>
								<div class="col-md-4">
									<div class="input-group date form_datetime_bmtc_format">
										<input type="text" size="16" readonly name="playList.playEndTime" class="form-control" value="<s:property value='playList.playEndTime'/>">
											<span class="input-group-btn">
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
								   </div>
									<s:if test="fieldErrors.get('playEndTime').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('playEndTime').get(0)" /></span>
									</s:if> 
								</div>
							    </div>
							  
							  <div class="form-group">
										<label class="col-md-3 control-label">Status:</label>
										<div class="col-md-3">
											<select class="form-control" name="playList.status" >
												<option value="Active">Active</option>
												<!-- <option value="INACTIVE">INACTIVE</option> -->
											</select>									    
										</div>
							    </div>																						
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn blue">Save</button>
										<button type="button" class="btn default" onclick = "javascript:location.href='playListView.action';">Cancel</button>
									</div>
								</div>
								
								
								
							<s:token/>
							</form>
							<!-- END FORM-->
							<%}else{ %>
<%@ include file="/pages/admin/error.jsp" %>
<%} %>									
										
<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>