<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<%-- <link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/global/plugins/select2/select2.css" /> --%>
<%-- <link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" /> --%>
<script>
							function goView()
							{
								document.forms[0].action="BrandTypeList.action";
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
		<%if (create.equalsIgnoreCase("Y")){%>
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
					BRAND TYPE <%-- <small>Create Brand Type</small> --%>
				</h3>
				<!-- <ul class="page-breadcrumb breadcrumb">
					<li><i class="fa fa-home"></i> Home <i
						class="fa fa-angle-right"></i></li>
					<li>Vehicle Management <i class="fa fa-angle-right"></i>
					</li>
					<li>Brand Type <i class="fa fa-angle-right"></i>
					</li>
					<li>Create Brand Type</li>
				</ul> -->
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN EXAMPLE TABLE PORTLET-->
			
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i> Create Brand Type
						</div>
						
					</div>
					<div class="portlet-body form">
						<div class="portlet-body form">
						<font color="red"><s:actionmessage/></font>
							<%-- <s:if test="hasActionErrors()">
								<div class="alert alert-danger">
									<button class="close" data-close="alert"></button>
									<span> <s:actionerror />
									</span>
								</div>
							</s:if> --%>

						</div>
						<form action="SaveBrandType" method="post"
							class="form-horizontal form-row-seperated">
							<div class="form-body">
								<div class="form-group">
									<label class="control-label col-md-4">Brand Type Name<font
										color="red">*</font></label>
									<div class="col-md-4">
										<input type="text" class="form-control"
											placeholder="Enter text"
											name="brandTypeDetails.brand_type_name" maxlength="50"
											value='<s:property value="brandTypeDetails.brand_type_name"/>'>
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
									<s:select list="fareCatagoryList" id="service_type_id" name="brandTypeDetails.serviceType.service_type_id" 
		cssClass="select2_category form-control" headerKey="0" headerValue="Select"></s:select>
										<%-- <select class="form-control" id="service_type_id"
											onclick="getServiceTypeName()"
											name="brandTypeDetails.serviceType.service_type_id">
											<option value='0'>---Select---</option>
										</select> --%>
										<s:if test="fieldErrors.get('serviceType').size() > 0">
											<span style="color: red;"><s:property
													value="fieldErrors.get('serviceType').get(0)" /></span>
											</s:if>
									</div>
								</div>   
								<div class="form-group">
									<label class="control-label col-md-4">Effective Start
										Date<font color="red">*</font>
									</label>
									
									<div class="col-md-6">
									<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd/mm/yyyy"
															data-date-viewmode="years">
											<input class="form-control" type="text"  readonly="readonly" id="effStartdate"
												value='<s:property value="brandTypeDetails.effective_start_date"/>'
												name="brandTypeDetails.effective_start_date" > <span
												class="input-group-btn" >
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>	
											<script>
											var curr_date=new Date().toJSON().slice(0,10);
											curr_date=curr_date.split("-");
											curr_date=curr_date[2]+"/"+curr_date[1]+"/"+curr_date[0];
											$('#effStartdate').val(curr_date);
											</script>
										</div>
										<%-- <div class="input-group date date-picker" data-date-format="dd/mm/yyyy">
											<input class="form-control" type="text" readonly size="16"
												value='<s:property value="brandTypeDetails.effective_start_date"/>'
												name="brandTypeDetails.effective_start_date"> <span
												class="input-group-btn">
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
											
										</div> --%>
										<s:if
											test="fieldErrors.get('startDate').size() > 0">
											<span style="color: red;"><s:property
													value="fieldErrors.get('startDate').get(0)" /></span>
										</s:if>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-4">Effective End
										Date<font color="red">*</font>
									</label>
									<div class="col-md-4">
										<div class="input-group input-medium date date-picker" data-date-format="dd/mm/yyyy">
											<input class="form-control" type="text" readonly="" size="16"
												value='<s:property value="brandTypeDetails.effective_end_date"/>'
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
										<textarea class="form-control" placeholder="Remarks" rows="3" maxlength="100"
											name="brandTypeDetails.note"><s:property value="brandTypeDetails.note"/></textarea>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-4">Status<font color="red">*</font></label>
									<div class="col-md-4">
										<select class="select2_category form-control"
											name="brandTypeDetails.status">
											<option value="ACTIVE">ACTIVE</option>
										</select>
									</div>
								</div>
								<div class="form-actions fluid">
									<div class="col-md-offset-4 col-md-9">
										<button type="submit" class="btn blue">SAVE</button>
										<button type="button" class="btn default" onclick="goView()">Cancel</button>
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