<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<%-- <link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/global/plugins/select2/select2.css" /> --%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
<script>
	function goView() {
		document.forms[0].action = "BrandTypeList.action";
		document.forms[0].submit();
	}
	function getServiceTypeName(){
		var len= document.getElementById('service_type_id').options.length;

		 if(len<=1 ) {
	         $.ajax({
	             type: "post",
	             url: '<%=request.getContextPath()%>/VehicleDetailsAjaxAction!getServiceType',
						success : function(result) {
							document.getElementById('service_type_id').innerHTML = result;
						}
					})
		}
	}
</script>
<input type="hidden" name="id" id="id" />
<div class="page-content-wrapper">
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "BrandTypeList.action");
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
		<%if (edit.equalsIgnoreCase("Y")){%>
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
				<h3 class="page-title">
					 BRAND TYPE <%-- <small> Edit Brand Type </small> --%>
				</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN EXAMPLE TABLE PORTLET-->
			
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i> Edit Brand Type
						</div>
					</div>
					<div class="portlet-body form">
						<div class="portlet-body form">
						</div>
						<form action="saveEditBrandTypeAction" method="post"
							class="form-horizontal form-row-seperated">
							<span id="errorMsg" style="color:red;"><s:actionerror/></span>		
							<font color="red"><s:actionmessage/></font>
							<s:if test="%{updatestatus=='Fail'}">
								<font color="green"><s:actionmessage/> </font>
							</s:if>
							<input type="hidden" name="brandTypeDetails.brand_type_id"
								value='<s:property value="brandTypeDetails.brand_type_id"/>' />
							<div class="form-body">
								<div class="form-group">
									<label class="control-label col-md-4">Brand Type Name<font
										color="red">*</font></label>
									<div class="col-md-4">
										<input type="text" class="form-control"
											value='<s:property value="brandTypeDetails.brand_type_name"/>'
											placeholder="Enter text" maxlength="50"
											name="brandTypeDetails.brand_type_name">
											<s:if test="fieldErrors.get('brandnamename').size() > 0">
											<span style="color: red;"><s:property
													value="fieldErrors.get('brandnamename').get(0)" /></span>
											</s:if>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">Fare Category Type<font
										color="red">*</font></label>
									<div class="col-md-4">
										<%-- <select class="form-control" id="service_type_id"
											onclick="getServiceTypeName()"
											name="brandTypeDetails.serviceType.service_type_id">
											<option
												value='<s:property value="brandTypeDetails.serviceType.service_type_id"/>'>
												<s:property
													value="brandTypeDetails.serviceType.service_type_name" />
											</option>
										</select> --%>
										<s:select list="fareCatagoryList" id="service_type_id" name="brandTypeDetails.serviceType.service_type_id" 
		cssClass="select2_category form-control" headerKey="0" headerValue="Select"></s:select>
										<s:if test="fieldErrors.get('serviceType').size() > 0">
											<span style="color: red;"><s:property
													value="fieldErrors.get('serviceType').get(0)" /></span>
											</s:if>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-4">Effective Start
										Date:<font color="red">*</font>
									</label>
									<div class="col-md-4">
									<!-- 	<div class="input-group date date-picker"
											data-date-format="yyyy-mm-dd"> -->
											<div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy">
											<input class="form-control" type="text"
												value='<s:property value="brandTypeDetails.effective_start_date"/>'
												readonly="" size="16"
												name="brandTypeDetails.effective_start_date"> <span
												class="input-group-btn">
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
										</div>
										<s:if
											test="fieldErrors.get('startDate').size() > 0">
											<span style="color: red;"><s:property
													value="fieldErrors.get('startDate').get(0)" /></span>
										</s:if>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-4">Effective End
										Date:
									</label>
									<div class="col-md-4">
										<div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy">
											<input class="form-control" type="text"
												value='<s:property value="brandTypeDetails.effective_end_date"/>'
												readonly="" size="16"
												name="brandTypeDetails.effective_end_date"> <span
												class="input-group-btn">
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
										</div>
										<s:if test="fieldErrors.get('endDate').size() > 0">
											<span style="color: red;"><s:property
													value="fieldErrors.get('endDate').get(0)" /></span>
										</s:if>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-4">Remarks </label>
									<div class="col-md-4">

										<textarea class="form-control" placeholder="Remarks"  name="brandTypeDetails.note" maxlength="100"><s:property value="brandTypeDetails.note"/> </textarea>
									</div>
								</div>
								<%-- <div class="form-group">
									<label class="control-label col-md-4">Notes </label>
									<div class="col-md-4">
										<textarea class="form-control" placeholder="Notes" rows="4" maxlength="100"
											name="brandTypeDetails.note">
											<s:property value="brandTypeDetails.note" />
										</textarea>
									</div>
								</div> --%>
								<div class="form-group">
									<label class="control-label col-md-4">Status<font color="red">*</font></label>
									<div class="col-md-4">
										<select class="select2_category form-control"
											name="brandTypeDetails.status">
											<option value="ACTIVE" id="active">ACTIVE</option>
											<option value="INACTIVE" id="inactive">INACTIVE</option>

										</select>
										<script type="text/javascript">
											var a = "<s:property value='brandTypeDetails.status'/>";
											if (a == "ACTIVE") {
												document
														.getElementById("active").selected = true;
											} else {
												document
														.getElementById("inactive").selected = true;
											}
										</script>
									</div>
								</div>
								<div class="form-actions fluid">
									<div class="col-md-offset-4 col-md-9">
										<button type="submit" class="btn blue">SAVE</button>
										<button type="button" class="btn default" onclick="goView()">Cancel</button>
									</div>
								</div>
							</div>
							<s:token></s:token>
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

		window.location.href = 'BrandTypeList.action';
	}

	
</script>
<script>


	

	$(document).ready(function() {
		//window.history.pushState("","", "VehicleCategoryList.action");
		

		   var w=$('#errorMsg span').html();
		   //alert(w);
		    w=w.replace(/@/g,"<br>");

		   $('#errorMsg').html(''+w+'');
	});
</script>