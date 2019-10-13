<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//www.google.com/jsapi"></script>

<script type="text/javascript">

function cancelform(){
// 	document.frm.action="ShowEmployeeListDetails.action";
// 	document.frm.submit();
	window.location.href = 'holidayMaster.action';
}

function findday(){
		var gsDayNames = new Array(
			  'Sunday',
			  'Monday',
			  'Tuesday',
			  'Wednesday',
			  'Thursday',
			  'Friday',
			  'Saturday'
			);
var var1=$("#holiday_date").val();
	var1=var1.split("-");
	var1=var1[2]+"-"+var1[1]+"-"+var1[0];
		var d = new Date(var1);
			var dayName = gsDayNames[d.getDay()];
			$('#day').val(dayName);
			$('#holiday_date').val(var1);
}

</script>



</head>
<body>

<div class="page-content-wrapper">
	<div class="page-content">
		<div class="tab-content">
		<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						HOLIDAY MASTER <small></small>
					</h3>
					
				
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			<div class="tab-pane active" id="tab_0">
				<div class="portlet box grey-cascade">
				<div class="row">
				
			</div>
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i>Create Holiday Master
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
						
					<form action="createHolidayMasterAction.action" class="form-horizontal" method="POST"  name ="frm" >
						
						
								<div class="form-group">
													<label class="col-md-3 control-label">Date :</label>
													<div class="input-group input-medium date date-picker"data-date-format="dd-mm-yyyy"  >
												<input type="text" class="form-control" name="holidayMasterModel.holiday_date" id="holiday_date" onchange="findday()"  >
												<span class="input-group-btn"   >
												<button class="btn default" type="button" ><i class="fa fa-calendar" ></i></button>
												</span>

											</div>
												</div>
												
												<div class="form-group">
													<label class="col-md-3 control-label">Day :</label>
													<div class="col-md-2">
													<input type="text" readonly="readonly" class="form-control" name="holidayMasterModel.holiday_day" id="day"  
													value='<s:property value="holidayMasterModel.holiday_day" /> '>

												</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">Name :</label>
													<div class="col-md-2">
													<input type="text" class="form-control" name="holidayMasterModel.holiday_name" id="name"  
													value='<s:property value="holidayMasterModel.holiday_name" /> '>

												</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">Type :</label>
													<div class="col-md-2">
													<s:select list="holidaytype" id="type" name="holidayMasterModel.holydaytype.holiday_id" cssClass="form-control input-medium select2me" onchange="getnamenclature()"></s:select>
								</div>
												</div>
										
										<div class="form-actions fluid">
											<div class="col-md-offset-3 col-md-9">
												<button type="submit" class="btn blue" >Save</button>
												<button class="btn default" type="button" onclick="cancelform()">Cancel</button>
														</div>
										</div>
										</div>	</div>
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