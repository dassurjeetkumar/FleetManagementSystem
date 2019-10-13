<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
<link rel="stylesheet"	href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
<style type="text/css">
.help-block {
	color: red;
}
.help-block,ul,li {
	list-style: none;
}

</style>


<input type="hidden" name="id" id="id" />
<div class="page-content-wrapper">
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ScheduleMappingList.action");
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
	<%/* if (access.equalsIgnoreCase("Y")){ */%>
		<%-- %if (edit.equalsIgnoreCase("Y")){ --%>
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
					SCHEDULE SERVICE LIMIT
				</h3>
			</div>
		</div>
		<div class="row">
	
			<div class="col-md-12">
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i> Create Schedule Service Limit
						</div>
					</div>
					<div class="portlet-body form">
                     <form action="saveEditServiceLimit" class="form-horizontal form-row-seperated" method="post"> 
							<b>
								<font color="green"> <s:actionmessage/></font>
								<span id="errorMsg" style="color:red;word-wrap: break-word;" ><s:actionerror/></span>
							</b>
							<div class="form-body">
							
								<div class="form-group">
								
<!-- 								<div class="form-group"> -->
<%-- 										<s:hidden name="isUpdateScheduleMapping" value="1" /> --%>
<%-- 										<s:hidden name="updatedScheduleMappingId" cssClass="form-control" value='%{scheduleMapDetails.schedule_no}' /> --%>
										
										<label class="control-label col-md-3"> Schedule No.<span	class="required"> * </span></label>
										<div class="col-md-3">
											<input type="text" class="form-control"  maxlength="10" readonly="readonly" id="schNo"
													name="schNo"	value='<s:property value="schedulemp.scheduleNumber"/>'>

										</div>
									</div>
									
									
									
														<div class="form-group">
													<label class="col-md-3 control-label">Brand Service<font color="red">*</font>:</label>
													<div class="col-md-4">
														<s:select list="brandlist" id="service" name="service" 
		cssClass="select2_category form-control" headerKey="0"  ></s:select>
															<s:if test="fieldErrors.get('service').size() > 0">
					     								<span style="color:red;"><s:property value="fieldErrors.get('service').get(0)" /></span>
														</s:if>
													
													
											</div>
												</div>


									
<!-- 								<div class="form-group"> -->
<!-- 									<label class="col-md-3 control-label">Brand Service </label> -->
<!-- 									<div class="col-md-4"> -->
<!-- 										<input type="text" class="form-control input-small" name="service_limit"> -->
<!-- 									</div> -->
<!-- 								</div> -->
								
							<!-- 	<div class="form-group">
									<label class="col-md-3 control-label">Service Limit</label>
									<div class="col-md-4">
									<input type="text" class="form-control input-small" name="service_limit" id="service_limit"> 

									</div>
								</div> -->
								<div class="form-group">
										<label class="col-md-3 control-label">Service Limit<font
										color="red">*</font></label>
									<div class="col-md-4">
						                <select  id="service_limit"  class="select2_category form-control" name="service_limit">
						                <option value="0">--select--</option>
							              <option value="1">City</option>
							              <option value="2">Suburban</option>
							             
							           </select>
							          
					</div>
											</div>
								
							
									<div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9">
											<button type="submit" class="btn blue">Save</button>
											<button type="button" class="btn default"	onclick="callCancell()">Cancel</button>
										</div>
									</div>
								</div>
							</div>
						</form>
						<%/* }else{ */ %>
<%-- <%@ include file="/pages/admin/error.jsp" %> --%>
<%/* } */ %>									
										
<%/* }else{ */%>
 


<%-- <%@ include file="/pages/admin/error.jsp" %> --%>


<%/* } */%>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	function callCancell() {

		window.location.href = 'ShowScheduleDetails.action';
	}
</script>
<script>
$(document).ready(function() {
	
	
	   var w=$('#errorMsg span').html();
	   //alert(w);
	    w=w.replace(/@/g,"<br>");

	   $('#errorMsg').html(''+w+'');
  
});
</script>
