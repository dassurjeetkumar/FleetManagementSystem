	
	<%@ taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE body PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<script>

function cancelform(){
	document.frm.action="createBataConfig.action";
	document.frm.submit();
}






</script>

<div class="page-content-wrapper">
		<div class="page-content">
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div class="modal fade" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">Modal title</h4>
						</div>
						<div class="modal-body">
							 Widget settings form goes here
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue">Save changes</button>
							<button type="button" class="btn default" data-dismiss="modal">Close</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<!-- BEGIN STYLE CUSTOMIZER -->
			<div class="theme-panel hidden-xs hidden-sm">
				
				
				<div class="theme-options">
					<div class="theme-option theme-colors clearfix">
						<span>
						THEME COLOR </span>
						<ul>
							<li class="color-default current tooltips" data-style="default" data-original-title="Default">
							</li>
							<li class="color-darkblue tooltips" data-style="darkblue" data-original-title="Dark Blue">
							</li>
							<li class="color-blue tooltips" data-style="blue" data-original-title="Blue">
							</li>
							<li class="color-grey tooltips" data-style="grey" data-original-title="Grey">
							</li>
							<li class="color-light tooltips" data-style="light" data-original-title="Light">
							</li>
							<li class="color-light2 tooltips" data-style="light2" data-html="true" data-original-title="Light 2">
							</li>
						</ul>
					</div>
					<div class="theme-option">
						<span>
						Layout </span>
						<select class="layout-option form-control input-small">
							<option value="fluid" selected="selected">Fluid</option>
							<option value="boxed">Boxed</option>
						</select>
					</div>
					<div class="theme-option">
						<span>
						Header </span>
						<select class="page-header-option form-control input-small">
							<option value="fixed" selected="selected">Fixed</option>
							<option value="default">Default</option>
						</select>
					</div>
					<div class="theme-option">
						<span>
						Sidebar </span>
						<select class="sidebar-option form-control input-small">
							<option value="fixed">Fixed</option>
							<option value="default" selected="selected">Default</option>
						</select>
					</div>
					<div class="theme-option">
						<span>
						Sidebar Position </span>
						<select class="sidebar-pos-option form-control input-small">
							<option value="left" selected="selected">Left</option>
							<option value="right">Right</option>
						</select>
					</div>
					<div class="theme-option">
						<span>
						Footer </span>
						<select class="page-footer-option form-control input-small">
							<option value="fixed">Fixed</option>
							<option value="default" selected="selected">Default</option>
						</select>
					</div>
				</div>
			</div>
			<!-- END STYLE CUSTOMIZER -->
			<!-- BEGIN PAGE HEADER-->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						BATACONFIG DETAILS   <small></small>
					</h3>
				
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
	
<div class="tab-content">
							<div class="tab-pane active" id="tab_0">


							<div class="portlet box grey-cascade">
									<div class="portlet-title">
										<div class="caption">
											<i class="fa fa-gift"></i>Create BataConfig
										</div>
										
									</div>
									<div class="portlet-body form">
									<s:if test="hasActionErrors()">
					<font color="Red"> <s:actionerror />
					</font>
					</s:if>
					
					<s:if test="hasActionMessages()">
					<font color="green"> <s:actionmessage />
					
				</font>
					</s:if>
										<!-- BEGIN FORM-->
										<form action="updateBataConfigDetails.action" name="frm" class="form-horizontal"  method="post">
										
											<input type="hidden" value='<s:property value="bataconfig.bata_config_id"/>' id="bataid"  name="bataconfig.bata_config_id" >
										
										
											<div class="form-body">
													<div class="form-group">
												<label class="col-md-3 control-label">Designation List:<font color="red">*</font></label>
												<div class="col-md-4">
													<s:select list="designationlist" id="designation_type_id" name="bataconfig.designation_type_id.designation_type_id" 
													cssClass="select2_category form-control" headerKey="0" headerValue="Select"></s:select>
													
												<s:if test="fieldErrors.get('designation_type_id').size() > 0">
					     								<span style="color:red;"><s:property value="fieldErrors.get('designation_type_id').get(0)" /></span>
														</s:if>	
														
											</div>
											</div>
											
											
											<div class="form-group">
												<label class="col-md-3 control-label">Shedule Type:<font color="red">*</font></label>
												<div class="col-md-4">
													<!-- <input type="text" class="form-control"  name="designationtype.status" id="status"  value="NEW" readonly="readonly" > -->
													 <s:select list="schedultypelist" id="schedule_type_id" name="bataconfig.schedule_type_id.schedule_type_id" 
													cssClass="select2_category form-control" headerKey="0" headerValue="Select"></s:select> 
													
													<s:if test="fieldErrors.get('schedule_type_id').size() > 0">
					     								<span style="color:red;"><s:property value="fieldErrors.get('schedule_type_id').get(0)" /></span>
														</s:if>	
														
											</div>
											</div>
											
											
												
													<div class="form-group">
													<label class="col-md-3 control-label">Minmum Km:<font color="red">*</font></label>
													<div class="col-md-4">
													
													<input type="text" class="form-control" maxlength="10"  name="bataconfig.min_km" id="min_km" value='<s:property value="bataconfig.min_km"/>'  >
														<s:if test="fieldErrors.get('min_km').size() > 0">
					     								<span style="color:red;"><s:property value="fieldErrors.get('min_km').get(0)" /></span>
														</s:if>
												
												</div>
												</div>
												
												
												<div class="form-group">
													<label class="col-md-3 control-label">Maxmum Km:<font color="red">*</font></label>
													<div class="col-md-4">
													
													<input type="text" class="form-control" maxlength="10"  name="bataconfig.max_km" id="max_km" value='<s:property value="bataconfig.max_km"/>'  >
														<s:if test="fieldErrors.get('max_km').size() > 0">
					     								<span style="color:red;"><s:property value="fieldErrors.get('max_km').get(0)" /></span>
														</s:if>
												
												</div>
												</div>
												
													<div class="form-group">
													<label class="col-md-3 control-label">Day Data:<font color="red">*</font></label>
													<div class="col-md-4">
													
													<input type="text" class="form-control" maxlength="10"  name="bataconfig.day_bata" id="day_bata" value='<s:property value="bataconfig.day_bata"/>'  >
														<s:if test="fieldErrors.get('day_bata').size() > 0">
					     								<span style="color:red;"><s:property value="fieldErrors.get('day_bata').get(0)" /></span>
														</s:if>
												
												</div>
												</div>
												
												
												<div class="form-group">
													<label class="col-md-3 control-label">Day Allowance:<font color="red">*</font></label>
													<div class="col-md-4">
													
													<input type="text" class="form-control" maxlength="10"  name="bataconfig.day_allowance" id="day_allowance" value='<s:property value="bataconfig.day_allowance"/>'  >
														<s:if test="fieldErrors.get('day_allowance').size() > 0">
					     								<span style="color:red;"><s:property value="fieldErrors.get('day_allowance').get(0)" /></span>
														</s:if>
												
												</div>
												</div>
												
												<div class="form-group">
													<label class="col-md-3 control-label">Special Allowance:<font color="red">*</font></label>
													<div class="col-md-4">
													
													<input type="text" class="form-control" maxlength="10"  name="bataconfig.spcl_allowance" id="spcl_allowance" value='<s:property value="bataconfig.spcl_allowance"/>'  >
														<s:if test="fieldErrors.get('spcl_allowance').size() > 0">
					     								<span style="color:red;"><s:property value="fieldErrors.get('spcl_allowance').get(0)" /></span>
														</s:if>
												
												</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">Night Halt Allowance:<font color="red"></font></label>
													<div class="col-md-4">
													
													<input type="text" class="form-control" maxlength="8"  name="bataconfig.spcl_allowance" id="spcl_allowance" value='<s:property value="bataconfig.spcl_allowance"/>'  >
														<s:if test="fieldErrors.get('spcl_allowance').size() > 0">
					     								<span style="color:red;"><s:property value="fieldErrors.get('spcl_allowance').get(0)" /></span>
														</s:if>
												
												</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">Night service allowance:<font color="red">*</font></label>
													<div class="col-md-4">
													
													<input type="text" class="form-control" maxlength="8"  name="bataconfig.nightsevice_allowance" id="spcl_allowance" value='<s:property value="bataconfig.nightsevice_allowance"/>'  >
														<s:if test="fieldErrors.get('bataconfig.nightsevice_allowance').size() > 0">
					     								<span style="color:red;"><s:property value="fieldErrors.get('bataconfig.nightsevice_allowance').get(0)" /></span>
														</s:if>
												
												</div>
												</div>
	 											<div class="form-actions fluid">
														<div class="col-md-offset-3 col-md-9">
														
														<button type="submit" class="btn blue">Save</button>
														<button class="btn default" type="button" onclick="cancelform()">Cancel</button>
														</div>
											</div>
									
										</div>
									  <s:token/>
										</form>
										<!-- END FORM-->
									</div>
								</div>
								</div>
								</div>
								

</div>
</div>