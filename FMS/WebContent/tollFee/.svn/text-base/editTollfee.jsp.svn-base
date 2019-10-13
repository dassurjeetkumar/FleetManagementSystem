<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<!DOCTYPE html>
<html>
<head>
<script>
	
function checkInt(data){
	
	var amount=document.getElementById('amount').value;
	if(/[^0-9\s]/.test(amount)){
	 
	    document.getElementById('integerVal').innerHTML='Amount should be number';
	    document.getElementById('amount').value='';
	    return false;
	}
	else
	{
	document.getElementById('integerVal').innerHTML='';
   return true;
}	

}



</script>
<title>Toll fee master</title>
</head>
<body>
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "Tollfeeaction!view");
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
		<%if (edit.equalsIgnoreCase("Y")){%>
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
				<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						TOLL FEE MASTER <small></small>
					</h3>
					<b><font color="green"><s:actionmessage/></font></b>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
				</div>
			
					<div class="portlet box grey-cascade">
					
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Edit Toll Fee Master
							</div>
							<div class="tools">
								
							</div>
						</div>
						<div class="portlet-body form">
							&nbsp;&nbsp;&nbsp;&nbsp;<font color="red"><span id="integerVal"></span></font>
							
							<form action="EditTollfeeaction.action"  class="form-horizontal" method="post">
								<b><font color="red"><s:actionerror/></font>  <br></b>
								
								
								
								<div class="form-group">
										<label class="col-md-3 control-label" 
										>Service Type:<font color="red">*</font></label>

										<div class="col-md-4">
										
									<s:select list="serviceTypeMap" id="serId" name="tollfee.servicetype.service_type_id" 
									cssClass="select2_category form-control" headerKey="0" headerValue="Select"></s:select>

											<s:if test="fieldErrors.get('serId').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('serId').get(0)" /></span>
											</s:if>	 

										</div>
									</div>
								
								
								<div class="form-group">
								<label class="col-md-3 control-label">Amount:<font color="red">*</font></label>
								<div class="col-md-4">
									<input id="amount" type="text" class="form-control" name="tollfee.amount" maxLength="8"  value="<s:property value="tollfee.amount"/>">
									 <s:if test="fieldErrors.get('amount').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('amount').get(0)" /></span>
									</s:if> 
								</div>
							    </div>
								
								
								 <div class="form-group">
									<label class="control-label col-md-3">Effective Start Date:<font color="red">*</font></label>
									<div class="col-md-4">
										<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd/mm/yyyy"
															data-date-viewmode="years" data-date-start-date="+0d">
											<input type="text" size="16" readonly class="form-control"
												id="effectiveStartDate"
												name="tollfee.effect_start_date"
												value="<s:property value='tollfee.effect_start_date' />" > 
												<span class="input-group-btn" >
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>						
										</div>
										<s:if test="fieldErrors.get('effectiveStartDate').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('effectiveStartDate').get(0)" /></span>
										</s:if>
									</div>
								</div>
								
								<div class="form-group">
									<label class="control-label col-md-3">Effective End Date:<font color="red">*</font></label>
									<div class="col-md-4">
									<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd/mm/yyyy"
															data-date-viewmode="years" data-date-start-date="+0d">
											<input type="text" size="16" readonly class="form-control"
												id="effectiveEndDate" name="tollfee.effect_end_date"
												value="<s:property value='tollfee.effect_end_date' />" >
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
								<label class="col-md-3 control-label">Version Number:<font color="red">*</font></label>
								<div class="col-md-4">
									<input id="versionNo" type="text" class="form-control" name="tollfee.versionNo" maxLength="5"
										value="<s:property value="tollfee.versionNo"/>" >
									 <s:if test="fieldErrors.get('versionNo').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('versionNo').get(0)" /></span>
									</s:if> 
								</div>
							    </div>
							   <div class="form-group">
									<label class="col-md-3 control-label">Bus Stop:</label>
									<div class="col-md-4">										
									<!-- <input class="form-control input-lg"  id="busStop" name="project" type="text" onkeyup="getDropStops(this.value)" /> -->
									<s:if test="tollfee.busstop.stop_direction!= null">
									<input id="busStop" type="text" class="form-control" name="tollfee.busstop.busStopName" maxLength="11" disabled="true"
										 value="<s:property value="tollfee.busstop.busStopName"/>-<s:property value="tollfee.busstop.stop_direction"/>">
										</s:if>
										<s:else>
										<input id="busStop" type="text" class="form-control" name="tollfee.busstop.busStopName" maxLength="11" disabled="true"
										 value="<s:property value="tollfee.busstop.busStopName"/>">
										</s:else>
										</div>
									</div> 
								
								<input type="hidden" name="tollfee.busstop.busStopName" value="<s:property value='tollfee.busstop.busStopName'/>" />	
								<input type="hidden" name="tollfee.id" value="<s:property value='tollfee.id'/>" />	
								<input type="hidden"  name="tollfee.busstop.id"  value="<s:property value='tollfee.busstop.id'/>">										
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn blue">Save</button>
										<button type="button" class="btn default" onclick = "javascript:location.href='Tollfeeaction!view';">Cancel</button>
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