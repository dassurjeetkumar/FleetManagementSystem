<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<style type="text/css">
.help-block,ul,li {
	list-style: none;
}
</style>

<script src="<%=request.getContextPath()%>/device/keyBoardEvents.js"
	type="text/javascript"></script>
<div class="page-content-wrapper">
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "SimViewAjaxAction.action");
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
					SIMCARD
				</h3>
			</div>
		</div>
		<div class="row">

			<div class="col-md-12">
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-bars"></i>Create SimCard
						</div>
					</div>
					<div class="portlet-body form">
						<form action="SaveNewSimcardAction"
							class="form-horizontal form-row-seperated" method="post">
							<font color="red"><s:actionmessage /></font>
							<div class="form-group">
								<label class="control-label col-md-3">Vendor Name:<font
									color="red">*</font></label>
								<div class="col-md-4">
								<div class="input-group input-medium" style="width: auto">
									<s:select list="vendorlist" id="vendortypeId"
										name="simcard.vendorDetailsSim.id"
										cssClass="select2_category form-control" headerKey="0"
										headerValue="Select"></s:select>
								</div>
								<s:if test="fieldErrors.get('simcard.vendor').size() > 0">
		     						<span style="color:red;"><s:property value="fieldErrors.get('simcard.vendor').get(0)" /></span>
								</s:if>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-3">Sim Serial Number:<font
									color="red">*</font></label>
								<div class="col-md-4">
									<div class="input-group input-medium" style="width: auto">
										<input type="text"  class="form-control" maxlength="20" value='<s:property value="simcard.serial_number"/>' 
											name="simcard.serial_number" />
									</div>
									<s:if test="fieldErrors.get('simcard.serial_number').size() > 0">
		     							<span style="color:red;"><s:property value="fieldErrors.get('simcard.serial_number').get(0)" /></span>
									</s:if>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3"> Phone Number:<font
									color="red">*</font></label>
								<div class="col-md-4">
									<div class="input-group input-medium" style="width: auto">
										<input type="text" class="form-control" maxlength="10" value='<s:property value="simcard.phone_number"/>' onkeypress="onlyInt(event)"
											name="simcard.phone_number" />
									</div>
									<s:if test="fieldErrors.get('simcard.phone_number').size() > 0">
		     							<span style="color:red;"><s:property value="fieldErrors.get('simcard.phone_number').get(0)" /></span>
									</s:if>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3"> Procured Date:<font
									color="red">*</font> </label>
								<div class="col-md-4">
									<div class="input-group input-medium date date-picker"
										style="width: auto" data-date-format="dd-mm-yyyy"
										data-date-viewmode="years">
										<input type="text"  class="form-control" id="procuredDate" value='<s:property value="simcard.procuredDateString"/>' readonly
											name="simcard.procuredDateString" /> <span
											class="input-group-btn">
											<button class="btn default" type="button">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
									</div>
									<s:if test="fieldErrors.get('simcard.procuredDateString').size() > 0">
		     							<span style="color:red;"><s:property value="fieldErrors.get('simcard.procuredDateString').get(0)" /></span>
									</s:if>
								</div>
							</div>
							<script>
							var procuredDate = "<s:property value='simcard.procuredDateString'/>";
							if(procuredDate =="" ){
								var curr_date=new Date().toJSON().slice(0,10);
								curr_date=curr_date.split("-");
								curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
								$('#procuredDate').val(curr_date);
							}else if(procuredDate=="blank"){
								$('#procuredDate').val("");
							}
							</script>
							<div class="form-group">
								<label class="control-label col-md-3"> Status</label>
								<div class="col-md-4">
									<div class="input-group input-medium" style="width: auto">
										<input type="text" class="form-control" readonly="readonly" 
											value="ACTIVE" name="simcard.status" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3"> Remarks</label>
								<div class="col-md-4">
									<div class="input-group input-medium" style="width: auto">
										<textarea class="form-control" name="simcard.notes" maxlength="200"><s:property value="simcard.notes"/></textarea>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3">Service Plan:</label>
								<div class="col-md-4">
									<div class="input-group input-medium" style="width: auto">
										<input type="text" class="form-control" maxlength="10" value='<s:property value="simcard.service_plan"/>'
											name="simcard.service_plan" />
									</div>
									<s:if test="fieldErrors.get('simcard.service_plan').size() > 0">
		     							<span style="color:red;"><s:property value="fieldErrors.get('simcard.service_plan').get(0)" /></span>
									</s:if>
								</div>
							</div>
							<div class="form-actions fluid">
								<div class="col-md-offset-3 col-md-9">
									<button type="submit" class="btn blue">Save</button>
									<button type="button" class="btn default" onclick="callCancell()">Cancel</button>
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
	function callCancell() {
		window.location.href = 'SimViewAjaxAction.action';
	}
</script>
