<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function copy(){
	document.forms[1].action = 'SaveCopySchedule.action';
	 document.forms[1].submit(); 
}
</script>
<script type="text/javascript">
function cancel(){
	document.forms[0].action = 'ShowScheduleDetails.action';
	 document.forms[0].submit(); 
}
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
							<i class="fa fa-gift"></i>Old Schedule Details
						</div>
						
					</div>
					<div class="portlet-body form">
					
					<br/>
						<!-- BEGIN FORM-->
						<form action="SaveFormFour.action" class="form-horizontal" method="post">
						
							<div class="form-group">
								<label class="col-md-3 control-label">Schedule Number</label>
								<div class="col-md-4">
									<s:textfield cssClass="form-control" name="schedule.scheduleNumber" id="scheduleNumber" theme="simple" readonly="true"></s:textfield>
									</div>
								
							</div>
							
							<div class="form-group">
								<label class="col-md-3 control-label">Schedule Type</label>
								<div class="col-md-4">
									<s:textfield cssClass="form-control" name="schedule.scheduletype.scheduleName" id="scheduleName" theme="simple" readonly="true"></s:textfield>
									</div>
								
							</div>
							
							<div class="form-group">
								<label class="col-md-3 control-label">Service Type</label>
								<div class="col-md-4">
									<s:textfield cssClass="form-control" name="schedule.servicetype.serviceTypeName" id="serviceTypeName" theme="simple" readonly="true"></s:textfield>
									</div>
								
							</div>
									<br/>	
						</form>
						<!-- END FORM-->
					</div>
				</div>
			</div>
		</div>
	
	
	
		<div class="tab-content">
			<div class="tab-pane active" id="tab_0">
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i>New Schedule
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
					<br/>
						<!-- BEGIN FORM-->
						<form action="SaveCopySchedule.action" class="form-horizontal" method="post">
						<s:hidden name="requestType" value="copy"></s:hidden>
							<div class="form-group">
								<label class="col-md-3 control-label">Schedule Number<sup><font color="red">*</font></sup></label>
								<div class="col-md-4">
								<s:textfield name="schedulenumber" id="schedulenumber" cssClass="form-control"></s:textfield>
							</div>
							</div>
					
										<div class="form-actions fluid">
											<div class="col-md-offset-3 col-md-9">
												<button type="submit" class="btn blue">Copy</button>
												<button type="button" class="btn default" onclick="cancel();">Cancel</button>
											</div>
										</div>
										<s:hidden name="id"></s:hidden>
										
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