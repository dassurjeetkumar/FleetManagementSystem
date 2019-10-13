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
 jQuery(document).ready(function(){

	 SelectElement('<s:property value="rateMaster.status"/>');
	
		
		   
		   var w=$('#errorMsg span').html();
		   //alert(w);
		    w=w.replace(/@/g,"<br>");

		   $('#errorMsg').html(''+w+'');
		


});

 function isInteger(n) {
		if(parseFloat(n) != parseInt(n, 10)){
		   return true;
		}else{
			return false;
		}
		} 
 
 function SelectElement(valueToSelect)
 { 
     var element = document.getElementById('status');
     element.value = valueToSelect;
 }

function getServiceId(){
	 /* var selectedValue = $('#form-control').val(); */
	 var len= document.getElementById('servicetypes').options.length;

	 if(len<=1 ) {
         $.ajax({
             type: "post",
             url: '<%=request.getContextPath()%>/RateMasterAction!getServiceTypes',
             success: function(result) {
                 document.getElementById('servicetypes').innerHTML=result;
             }
         })
	 }
 }
 
function callEdit(btn) {
	
	var val=document.getElementById('rateid').value;
	
			document.forms[0].action = 'RateMasterAction!rateDetailEdit?id='
					+ val;
			document.forms[0].submit();
		
	

}

function isInt(n) {
	   return typeof n === 'number' && n % 1 == 0;
	}

function checkInt(){
	
	var val=document.getElementById('version').value;
	
	if(isNaN(val) ||isInteger(val) || isInt(val)){
		
		if(val==""){
			 document.getElementById('integerVal').innerHTML='Please enter Version Number';   
		   }
		   else{
		   document.getElementById('integerVal').innerHTML='Version Number should be Integer value';
		   }
	   return false;
	}
}

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
	<%if(edit.equalsIgnoreCase("Y")){ %>
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
							<i class="fa fa-gift"></i>Edit Rate
						</div>
					
						<div class="actions">
							<!-- <a href="javascript:;" class="collapse"> </a> <a
								href="#portlet-config" data-toggle="modal" class="config"> </a>
							<a href="javascript:;" class="reload"> </a> <a
								href="javascript:;" class="remove"> </a> -->
								
								<a href="#" class="btn blue"  id="edits" onclick="callEdit(2)"> 
								<i class="fa fa-pencil"></i> Rate Master Details </a>
								
						</div>
					</div>
					<div class="portlet-body form">
					
					<s:actionmessage/>
						<span id="errorMsg" style="color:red;word-wrap: break-word;"><s:actionerror/></span>
					<%-- <font color="red"><span id="integerVal"></span></font> --%>
						<!-- BEGIN FORM-->
						<form action="RateMasterAction!editRate" onsubmit="return checkInt()" class="form-horizontal" method="post">
						<s:hidden name="effStartDate"></s:hidden>
						<s:hidden name="effEndDate"></s:hidden>
						<s:hidden name="rateId"></s:hidden>
							<br><br><br>
							<input id="rateid" type="hidden" class="form-control" name="rateMaster.rateMasterId"
							 value="<s:property value="rateMaster.rateMasterId"/>"/>
							<div class="form-group">
								<label class="col-md-3 control-label">Version Number:<font color="red">*</font></label>
								<div class="col-md-4">
									<input id="version" type="text" class="form-control" name="rateMaster.versionNumber"
										value="<s:property value="rateMaster.versionNumber"/>" readonly="readonly">
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
								<!-- <div class="input-group date date-picker"> -->
								<input class="form-control" type="text" readonly="" size="16" name="rateMaster.effectiveStartDate"
								value='<s:property value="rateMaster.effectiveStartDate"/>' readonly="readonly">
								
								<%-- <span class="input-group-btn">
								<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
								</button>
								</span> --%>
								<!-- </div> -->
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
															data-date-viewmode="years" ><!-- data-date-start-date="+0d"> -->
								<input class="form-control" type="text" readonly="" size="16" name="rateMaster.effectiveEndDate"
								value='<s:property value="rateMaster.effectiveEndDate"/>'>
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
										<label class="col-md-3 control-label">Status</label>
										<div class="col-md-3">
											<select id="status" class="form-control" name="rateMaster.status" >
												
												<option value="Active">Active</option>
												<option value="Inactive">Inactive</option> 
												
											</select>									    
										</div>
							</div>

							


										<div class="form-actions fluid">
											<div class="col-md-offset-3 col-md-9">
												<button type="submit" class="btn blue">Save</button>
												<button type="button" class="btn default" onclick = "javascript:location.href='RateMasterAction';">Cancel</button>
											</div>
										</div>
										
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
