<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function isEmpty(str) {
	  str = trim(str);
	  return ((str == null) || (str.length == 0));
	}

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
							<i class="fa fa-gift"></i>Schedule Curtailment
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
						<form action="SaveCurtailSchedule.action" class="form-horizontal" method="post">
						<s:hidden name="requestType" value="curtail"></s:hidden>
						<s:hidden name="effectiveStartDate"></s:hidden>
							<div class="form-group">
								<label class="control-label col-md-3">Schedule Number</label>
								<div class="col-md-4">
									<s:textfield cssClass="form-control input-medium" name="schedule.scheduleNumber" id="scheduleNumber" theme="simple" readonly="true"></s:textfield>
									</div>
								
							</div>
						
							<div class="form-group">
								<label class="control-label col-md-3">Effective Start Date</label>
								<div class="col-md-4">
									<s:textfield cssClass="form-control input-medium" name="schedule.startdate" id="startdate" theme="simple" readonly="true"></s:textfield>
									</div>
								
							</div>
							
							
							<div class="form-group">
								<label class="control-label col-md-3">Curtail Type</label>
								<div class="col-md-4">
									<s:select list="#{'permanent':'Permanent','temporary':'Temporary'}" name="curtailType" id="curtailType" cssClass="form-control input-small select2me"></s:select>
									</div>
								
							</div>
							
							<div class="form-group">
										<label class="col-md-3 control-label">Effective End Date</label>
										<div class="col-md-3">
											<div class="input-group input-small date date-picker" data-date-format="dd-mm-yyyy">
												<input type="text" class="form-control input-small" name="startdate" id="startdate" readonly>
												<span class="input-group-btn">
												<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
												</span>
											</div>
												<%-- <input type="text" class="form-control form-control-inline input-medium date-picker" data-date-format="dd-mm-yyyy" size="16" readonly name="startdate" id="startdate">
												<span class="input-group-btn">
												<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
												</span> --%>
											<span class="help-block">
											(Mandatory for Temporary Curtailment) </span>
										</div>
									</div>
									
									<div class="form-actions fluid">
											<div class="col-md-offset-3 col-md-9">
												<button type="submit" class="btn blue">Save</button>
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