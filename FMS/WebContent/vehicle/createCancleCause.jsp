<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<style type="text/css">
	.help-block
	{
	color:red;
	}
	</style>
<input type="hidden" name="id" id="id" />
<div class="page-content-wrapper">
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "causeCancellation.action");
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
	<%if (create.equalsIgnoreCase("Y")){%>
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
				Cause For Cancellation 
				</h3>
			</div>
		</div>
		<div class="row">
		
			<div class="col-md-12">
				<!-- BEGIN EXAMPLE TABLE PORTLET-->
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i> Create Cause For Cancellation
						</div>
					</div>
					<div class="portlet-body form">
						<b>
							<font color="green"> <s:actionmessage/></font>
							<font color="red"> <s:actionerror/></font>
						</b>
						<form action="SaveCancellationCause" class="form-horizontal form-row-seperated" method="post">
							<div class="form-body">
								<div class="form-group">
								
									<label class="control-label col-md-3">
										Cancellation Type
										<span	class="required"> * </span>
									</label>
									<div class="col-md-3">
										<input type="text" class="form-control" maxlength="20"
											placeholder="Enter text"  name="cancellationDetails.cancellation_name" value="<s:property value='cancellationDetails.cancellation_name'/>">
											<span class="help-block" > <s:property
														value="%{fieldErrors.get('newCancellationCause').get(0)}" /></span>
									</div>
									
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">Remarks </label>
									<div class="col-md-3">
										<textarea class="form-control" placeholder="Remarks" rows="3" name="cancellationDetails.notes" maxlength="100"><s:property value='cancellationDetails.notes'/></textarea>
										<span class="help-block" > 
											<s:property	value="%{fieldErrors.get('notes').get(0)}" />
										</span>
									</div>
									
								</div>
<!-- 								<div class="form-group"> -->
<!-- 									<label class="control-label col-md-3">Status</label> -->
<!-- 									<div class="col-md-3"> -->
<%-- 										<select class="select2_category form-control"  name="cancellationDetails.status"> --%>
<!-- 											<option value="Y" >ACTIVE</option>		 -->
<!--  											<option value="N">INACTIVE</option> --> 
<%-- 										</select> --%>
<!-- 									</div> -->
									
<!-- 								</div> -->
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn blue">Save</button>
										<button type="button" class="btn default" onclick="callCancell()">Cancel</button>
									</div>
								</div>
							</div>
						</form>
						
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
<script>
function callCancell(){
	
	window.location.href='causeCancellation.action';
}
</script>