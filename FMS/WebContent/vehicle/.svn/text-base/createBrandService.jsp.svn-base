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
					BRAND SERVICE
				</h3>
			</div>
		</div>
		<div class="row">
	
			<div class="col-md-12">
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i> Create Brand Service
						</div>
					</div>
					<div class="portlet-body form">
                     <form action="saveEditBrandService" class="form-horizontal form-row-seperated" method="post"> 
							<b>
								<font color="green"> <s:actionmessage/></font>
								<span id="errorMsg" style="color:red;word-wrap: break-word;" ><s:actionerror/></span>
							</b>
							<div class="form-body">
							
								<div class="form-group">
								
											<%-- <div class="form-group">
										<label class="control-label col-md-3">Brand Type Name<font
										color="red">*</font></label>
											<div class="col-md-3">
										<select  id="brand_type_name"  class="select2_category form-control" name="brand_type_name"
 							              > 
							              <option value='<s:property value="serviceLimit.brand_type_name"/>'><s:property value="serviceLimit.brand_type_name" /></option>
 							           </select> 
									<script type="text/javascript">							
									var orgName= "<s:property value='serviceLimit.brand_type_name'/>";
										if(orgName!=""){
											document.getElementById(orgName).selected=true;
											alert(orgname);
										}
									</script>
<!-- 														<input type="text" class="form-control"  maxlength="10"  -->
													name="vehicle_no"	value='<s:property value="schedulemp.vehicle_no"/>'>
										</div>
									</div> --%>
									
									
									
														<div class="form-group">
													<label class="col-md-3 control-label">Brand Type<font color="red">*</font>:</label>
													<div class="col-md-4">
													
															
														<s:select list="brandlist" id="brand_type_name" name="brand_type_name" 
		cssClass="select2_category form-control"  ></s:select>
															<s:if test="fieldErrors.get('brand_type_name').size() > 0">
					     								<span style="color:red;"><s:property value="fieldErrors.get('brand_type_name').get(0)" /></span>
														</s:if>
													
													
											</div>
												</div>


									
								<div class="form-group">
									<label class="col-md-3 control-label">Brand Service </label>
									<div class="col-md-4">
										<input type="text" class="form-control input-small" name="service_limit">
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-3 control-label">Note</label>
									<div class="col-md-4">
									<s:textfield name="note" cssClass="form-control"></s:textfield>
<%-- 									<s:hidden name="requestType" value="update"></s:hidden> --%>

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

		window.location.href = 'BrandTypeServiceLimit.action';
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
