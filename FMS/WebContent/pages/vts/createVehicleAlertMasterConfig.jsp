<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE html> 
<html>
<head>
<script>
</script> 
<style>
h1.intro {
	color: red;
	font-size: 14px;
}
</style>
<script>
function goView() {

		/* var val = $("input[type='checkbox']").val(); */
		//alert(val);
		document.forms[0].action = 'vtsalertmastconfig.action';
		document.forms[0].submit();


}


</script>
<script type="text/javascript" src="//www.google.com/jsapi">

</script>
</head> 

<body>
	<div class="page-content-wrapper">
		<div class="page-content">
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Create Vehicle Alert Configuration Master
							</div>
							<div class="tools">
								




							</div>
						</div>
						<div class="portlet-body form">
                         <s:if test="hasActionErrors()">
                                       <div class="alert alert-danger">
			                                <button class="close" data-close="alert"></button>
		                                     <span>
			                                 <s:actionerror/> </span>
		                              </div>
      
                                    </s:if>
		
							<!-- BEGIN FORM--> 
							
							<form action="saveAlertConfigMaster.action" class="form-horizontal"
								method="post">
								<div class="form-body">
								<h1 class="intro">
												<s:property value="msg" />
											</h1>
                                  	<div class="form-group">
										<label class="col-md-3 control-label">Packet Code<font color="red">*</font></label>
										<div class="col-md-4">
											<div class="input-group">
												<input type="text" name="vtsalertmaster.packet_code" id="packet_code"
													class="form-control"
													value="<s:property value="vtsalertmaster.packet_code" />" maxlength="11" >
												<s:if test="fieldErrors.get('packetcode').size() > 0">
													<span style="color: red;"><s:property
															value="fieldErrors.get('packetcode').get(0)" /></span>
												</s:if>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Misc Bytes<font color="red">*</font></label>
										<div class="col-md-4">
											<div class="input-group">
												<input type="text" name="vtsalertmaster.misc_bytes" id="mis_bytes"
													class="form-control"
													value="<s:property value="vtsalertmaster.misc_bytes" />" maxlength="11" >
												<s:if test="fieldErrors.get('mis_bytes').size() > 0">
													<span style="color: red;"><s:property
															value="fieldErrors.get('mis_bytes').get(0)" /></span>
												</s:if>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Alert Short Code<font color="red">*</font></label>
										<div class="col-md-4">
											<div class="input-group">
												<input type="text" name="vtsalertmaster.alert_short_code" id="alert_short_code"
													class="form-control"
													value="<s:property value="vtsalertmaster.alert_short_code" />" maxlength="11">
												<s:if test="fieldErrors.get('alert_short_code').size() > 0">
													<span style="color: red;"><s:property
															value="fieldErrors.get('alert_short_code').get(0)" /></span>
												</s:if>
											</div>
										</div>
									</div>
									 <div class="form-group">
										<label class="col-md-3 control-label">Alert Description<font color="red">*</font></label>
											<div class="col-md-4">
													<div class="input-group">
													
			 											<input type="text" name="vtsalertmaster.alert_desc" id="alert_desc"
													class="form-control"
													value="<s:property value="vtsalertmaster.alert_desc" />" maxlength="30">
												<s:if test="fieldErrors.get('alert_desc').size() > 0">
													<span style="color: red;"><s:property
															value="fieldErrors.get('alert_desc').get(0)" /></span>
												</s:if>
															<%--  <script>
															
															 </script> --%>
												 		
													</div>
											</div>
										</div>
										<div class="form-group">
										<label class="col-md-3 control-label">Dashboard View</label>
											<div class="col-md-4">
													<div class="input-group">
													
			 											<select class="select2_category form-control" name="vtsalertmaster.dashboard_view" id="dashboard_view" style="width:200px;">
												         <option value="0" >0</option>
												         <option value="1" >1</option>
										                 </select>
			 											 
													
															<s:if test="fieldErrors.get('dashboard_view').size() > 0">
     								                                   <span style="color:red;"><s:property value="fieldErrors.get('dashboard_view').get(0)" /></span>
									                        </s:if>	 
													        
															<%--  <script>
															
															 </script> --%>
												 		
			 										</div>
											</div>
										</div>
									<div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9">
											<button type="submit" class="btn blue">Save</button>
											<button type="button" id="cancel" class="btn default" onclick="goView()">Cancel</button>
											
										</div>
									</div>
								</div>
			
							</form>
							<!-- END FORM-->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

