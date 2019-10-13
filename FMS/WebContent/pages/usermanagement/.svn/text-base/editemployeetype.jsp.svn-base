	
	<%@ taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE body PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<script>
function getAddDetail(){
	
	
	document.frm.action="UpdateEmployeeTypeDetails.action";
	document.frm.submit();
}
function cancelform(){
	document.frm.action="ShowEmployeeTypeList.action";
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
			
			<!-- END PAGE HEADER-->
			
	
           <div class="tab-content">
							<div class="tab-pane active" id="tab_0">
						<div class="portlet box green">
									<div class="portlet-title">
										<div class="caption">
											<i class="fa fa-gift"></i>Edit Employee Type
										</div>
										
									</div>
									<div class="portlet-body form">
										<!-- BEGIN FORM-->
										<s:if test="hasActionErrors()">
					<font color="Red"> <s:actionerror />
					</font>
					</s:if>
					
					<s:if test="hasActionMessages()">
					<font color="green"> <s:actionmessage />
					
				</font>
					</s:if>
										<form action="#" class="form-horizontal" name="frm" method="post">
										
											<div class="form-body">
												<div class="form-group">
													<label class="col-md-3 control-label">Employee Type name :<font color="red">*</font></label>
													<div class="col-md-4">
														<input type="hidden"   name="e.emp_id"    maxlength="200"  value='<s:property value="e.emp_id" />'  id="emp_id" >
													<input type="text" class="form-control"   value='<s:property value="e.employee_typename" />' name="e.employee_typename" id="employeetypename"  >
													<s:if test="fieldErrors.get('employee_typename').size() > 0">
					     								<span style="color:red;"><s:property value="fieldErrors.get('employee_typename').get(0)" /></span>
														</s:if>
												</div>
												</div>
												
												
												
												
												<div class="form-group">
												<label class="col-md-3 control-label">Remarks:</label>
												<div class="col-md-4">
													<textarea class="form-control"  maxlength="200" name="e.employee_note"   id="employeenotes"   ><s:property value="e.employee_note" /></textarea>
													<s:if test="fieldErrors.get('employee_note').size() > 0">
					     								<span style="color:red;"><s:property value="fieldErrors.get('employee_note').get(0)" /></span>
														</s:if>
													
												</div>
											</div>
								
												<div class="form-group">
												<label class="col-md-3 control-label">Status:</label>
												<div class="col-md-4">
													
													 <select class="form-control" id="e.employee_status" name="e.employee_status" >
														<option id="ACTIVE"  value="ACTIVE">ACTIVE</option>
														<option id="INACTIVE" value="INACTIVE">INACTIVE</option>
													</select> 
													<script>
														var status = "<s:property value="e.employee_status"/>";
															if (status != undefined) {
																if (status == "ACTIVE") {
																	document.getElementById("ACTIVE").selected = true;
																} else {
																	document.getElementById("INACTIVE").selected = true;
																}
															}
															</script>
											</div>
											</div>
										
											<div class="form-actions fluid">
														<div class="col-md-offset-3 col-md-9">
														<button class="btn blue" type="submit" onclick="getAddDetail()">Save</button>
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