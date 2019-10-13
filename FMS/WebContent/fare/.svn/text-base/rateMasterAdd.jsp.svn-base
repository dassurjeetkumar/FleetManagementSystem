<%-- 
    Document   : rateMasterAdd.jsp
    Created on : 26th May 2014
    Author     : Manoj Vishwakarma
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<!DOCTYPE html>

<%-- <s:if test="hasFieldErrors()"> --%>
<script>
/* jQuery(document).ready(function(){
	getServiceId();
}); */

function isInteger(n) {
	if(parseFloat(n) != parseInt(n, 10)){
	   return true;
	}else{
		return false;
	}
	} 

function getServiceId(){
	var len= document.getElementById('servicetypes').options.length;

	 if(len<=1 ) {
         $.ajax({
             type: "post",
             url: '<%=request.getContextPath()%>/RateMasterAction!getServiceTypes',
             success: function(result) {
                 document.getElementById('servicetypes').innerHTML=result;
             }
         });
	 }
 }

function isInt(n) {
	   return typeof n === 'number' && n % 1 == 0;
	}

function checkInt(){
	
	var val=document.getElementById('version').value;
	
	if(isNaN(val) || isInteger(val) || isInt(val)){
		
	   if(val==""){
		 document.getElementById('integerVal').innerHTML='Please enter Version Number';   
	   }
	   else{
	   document.getElementById('integerVal').innerHTML='Version Number should be Integer value';
	   }
	   return false;
	}
}
$(document).ready(function() {

	window.history.pushState("", "", "RateMasterAction!add");
	
});  
</script>
       
   
<div class="page-content-wrapper">
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "RateMasterAction.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
%>
	<div class="page-content">
		<%if (access.equalsIgnoreCase("Y")){%>
			<%if(create.equalsIgnoreCase("Y")){ %>
		<div class="tab-content">
		<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
					 RATE MASTER <small></small> 
					
					</h3>
					<FONT color="green"><s:actionmessage /></FONT>
					<!-- END PAGE TITLE & BREADCRUMB-->
		</div>
		
			<div class="tab-pane active" id="tab_0">
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i>Create Rate Master
						</div>
						<div class="tools">
							
						</div>
					</div>
					<div class="portlet-body form">
					<span style="color:red;"><s:actionerror/>
					<s:actionmessage/></span>
					<%-- <font color="red"><span id="integerVal"></span></font> --%>
						<!-- BEGIN FORM-->
						<form action="RateMasterAction!addRate" onsubmit="return checkInt()" class="form-horizontal" method="post">
						    <s:if test="formType==2">
						    <%int rateId=(Integer)session.getAttribute("rateId"); %>
						    <input name="id" type="hidden" value="<%=rateId%>"/>
						    </s:if>
						    <s:else>
						     <input name="id" type="hidden" value="0"/>
						    </s:else>
							<br>
							<div class="form-group">
								<label class="col-md-3 control-label">Version Number:<font color="red">*</font></label>
								<div class="col-md-4">
									<input id="version" type="text" class="form-control" maxLength="50" name="rateMaster.versionNumber"
										value="<s:property value="rateMaster.versionNumber"/>">
									<s:if test="fieldErrors.get('versionNumber').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('versionNumber').get(0)" /></span>
									</s:if>
									<font color="red"><span id="integerVal"></span></font>
								</div>
							</div>
							<div class="form-group">
										<label class="col-md-3 control-label">Fare Category type:<font color="red">*</font></label>
										<div class="col-md-3">
										    
											<select class="form-control" name="rateMaster.serviceTypeId" 
											id="servicetypes" onclick="getServiceId()">
											<option value="<s:property value='rateMaster.serviceTypeId'/>"><s:property value='rateMaster.versionNoServiceType'/></option>
											<%-- <option value="">Select</option>
											<s:iterator value="serviceTypeIds">
												<option value="<s:property/>"> <s:property/>   </option>
											</s:iterator> --%>
											</select>
											<s:if test="fieldErrors.get('serviceTypeId').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('serviceTypeId').get(0)" /></span>
											</s:if>									    
										</div>
							</div>
							
								<div class="form-group">
								<label class="control-label col-md-3">Effective Start Date:<font color="red">*</font></label>
								<div class="col-md-4">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd/mm/yyyy"
															data-date-viewmode="years" ><!-- data-date-start-date="+0d"> -->
								<input id="startdate" class="form-control" type="text" readonly="" size="16" name="rateMaster.effectiveStartDate"
								value="<s:property value='rateMaster.effectiveStartDate' />"
								>
								<span class="input-group-btn">
								<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
								</button>
								</span>
								<script>
										var curr_date=new Date().toJSON().slice(0,10);
                                        curr_date=curr_date.split("-");
                                        curr_date=curr_date[2]+"/"+curr_date[1]+"/"+curr_date[0];
                                        var dtval=document.getElementById('startdate').value;	
                                        
                                        if(dtval==''){
                                        $('#startdate').val(curr_date);
                                        }
								</script>
								</div>
								<s:if test="fieldErrors.get('effectiveStartDate').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('effectiveStartDate').get(0)" /></span>
								</s:if>
								</div>
								</div>
								
								<div class="form-group">
								<label class="control-label col-md-3">Effective End Date:</label>
								<div class="col-md-4">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd/mm/yyyy"
															data-date-viewmode="years">  <!-- data-date-start-date="+0d"> -->
								<input class="form-control" type="text" readonly="" size="16" name="rateMaster.effectiveEndDate"
								value="<s:property value='rateMaster.effectiveEndDate' />"
								>
								<span class="input-group-btn">
								<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
								</button>
								</span>
								</div>
								<s:if test="fieldErrors.get('effectiveEndDate').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('effectiveEndDate').get(0)" /></span>
								</s:if>
								</div>
								</div>

							<div class="form-group">
										<label class="col-md-3 control-label">Status:</label>
										<div class="col-md-3">
											<select class="form-control" name="rateMaster.status" >
												<option value="Active">Active</option>
												<!-- <option value="Inactive">Inactive</option>  -->
											</select>									    
										</div>
							</div>

							


										<div class="form-actions fluid">
											<div class="col-md-offset-3 col-md-9">
												<button type="submit" class="btn blue" >Save</button>
												<button type="button" class="btn default" onclick = "javascript:location.href='RateMasterAction';">Cancel</button>
											</div>
										</div>
								
						</form>
						<%}else{ %>
<%@ include file="/pages/admin/error.jsp" %>
<%} %>									
										
<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>
						
						<!-- END FORM-->
					</div>
				</div>
			</div>
		</div>
	</div>
</div>