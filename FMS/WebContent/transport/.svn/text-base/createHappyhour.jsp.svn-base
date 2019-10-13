<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript" src="assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script>
function validate() {

	document.forms[0].action = 'createHappyHourAction.action';
	document.forms[0].submit();
} 
	function goView()
	{
		document.forms[0].action = 'HappyHours.action';
		document.forms[0].submit();
	}
	$(document).ready(function(){
// 		$('#starttimeStringid').val('0:00');
// 		$('#endtimeStringid').val('0:00');
// 		$("#starttimeStringid").inputmask("mask", {
// 			"mask": "99:99",
// 			"greedy" : "false"
// 	    });
// 		$("#endtimeStringid").inputmask("mask", {
// 			"mask": "99:99",
// 			"greedy" : "false"
// 	    });

		//jQuery('#starttimeStringid').datetimepicker();
	});
	
	function checkTime(ele) {
		  var id = ele.id;
		  var value=document.getElementById(id).value;
		  //alert(value);
		  var newtime='';
		  var arr = value.split(':');
		  var newhour='';
		  var newmin ='';
		  var res1 = arr[0].split('');
		  //alert("res1[1]"+res1[1]);
		 // alert('Res[0]-------> '+res1[0]+'  Res[1]------> '+res1[1]);
		  if(res1[0] == '_'){
			 
			  newhour = '0'+res1[1];
		  }
		  else if(res1[1] =='_'){
			 
		//alert("res1[1]"+res1[1]);
			  
			  newhour = '0'+res1[0];
		  }
		  
		  else if(res1[0] == undefined && res1[1] == undefined){
			 
			  newhour = '0'+'0';
		  }
		  else{
			  //alert(4);
			  newhour = res1[0]+res1[1];
		  }
		  if(isNaN(res1[1]) && isNaN(res1[0])){
			 // alert(5);
				//alert('Inside------ ');
			  newhour = '0'+'0';
			  }
		  
		if(arr[1] == undefined){
			//alert(6)
			newmin = '0'+'0';
		}
		else{
		  var res2 = arr[1].split('');
		  
		/* // alert('Res[0]------->'+arr[1]+'Res[0]-------> '+res2[0]+'  Res[1]------> '+res2[1]+'  hh----'+isNaN(res2[1])); */
		  if(res2[0] == '_'){
			  //alert("heloo");
			  newmin='0'+res2[1];
			  
		  }
		  else  if(res2[1] =='_'){
			 
			  newmin = '0'+res2[0];
			 
		  }
		  else{
			  newmin = res2[0] + res2[1];
			 
		  }
		  if(isNaN(res2[1]) && isNaN(res2[0])){
			//alert('Inside------ ');
			  newmin = '0'+'0';
		  }
		  
		  
		}
		if(value == '24:00'){
			newtime = '24:00';
		}
		else{
		  if(parseInt(newhour)>23){
			  newtime = '24:';
		  }
		  else{
			  newtime=newhour+':';
		  }
		  
		  if(parseInt(newmin)>59){
			  newtime +='00';
		  }
		  else{
			  newtime += newmin;
		  }
		}
		
		 
		  $("#"+id).val(newtime);
		}
</script>
<script>
function getServiceype(){

    $.ajax({
        type: "post",
        async:false,
        url: '<%=request.getContextPath()%>/findServiceTypeAction1',
        success: function(result) {
     	   
            document.getElementById('service_type_id').innerHTML=result;
        }
    });
	 var prevType="<s:property value='happy.servicetype.service_type_id'/>";
		//alert("prevType"+prevType);
		 if(prevType!=""){
			 document.getElementById("service_type_id").value=prevType;
			 var orgtypeid = document.getElementById("service_type_id").value;
			 var orgtype = service_type_id.options[service_type_id.selectedIndex].text;
			 document.getElementById('select2-chosen-1').innerHTML=orgtype;
		 }
	

}


 function getLumsum(){	
//alert("hello");
var val= document.getElementById('percentId').value;
var result=0;
if(val>0){
	//alert("hlloooo");
		
	 $("#lumpsumId").val(0);
}
}
function getPercent(){	
	//alert("hello");
	var val= document.getElementById('lumpsumId').value;
	
	if(val>0){
					
		 $("#percentId").val(0.0);
	}

	}  
</script>
<title>Insert title here</title>
</head>
<body onload="getServiceype()">
<%
// AccessRightsDao  accessrightdao=new AccessRightsDao();
// AccessRights accessrights=new AccessRights();
// int usrsessionid=(Integer)request.getSession().getAttribute("userid");
// accessrights=accessrightdao.accessRightsdetails(usrsessionid, "HappyHours.action");
// String access=accessrights.getACCESS();
// String create=accessrights.getCREATE();
// String edit=accessrights.getEDIT();
// String delete=accessrights.getDELETE();
// String read=accessrights.getREAD();
// String error=accessrights.getERROR();
// String viewdelete=accessrights.getVIEWDELETE();
// String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
%>

	<div class="page-content-wrapper">
		<div class="page-content">
<%-- 		<%if (access.equalsIgnoreCase("Y")){%> --%>
<%-- 		<%if(create.equalsIgnoreCase("Y")){ %> --%>
				<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
				HAPPY HOURS 
				</h3>
			</div>
		</div>
		
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
				
					<div class="portlet box grey-cascade">
					
						<div class="portlet-title">
						
							<div class="caption">
								<i class="fa fa-gift"></i>Create Happy Hours
							</div>
							
						</div>
						
						<div class="portlet-body form">
						<font color="red"><s:actionmessage/></font>						
						<s:if test="hasActionErrors()">
							<font color="red"><s:actionerror/></font>
						</s:if>
							<!-- BEGIN FORM-->
							<form action="createHappyHourAction.action" method="post"  id="createComplaintId" class="form-horizontal">
							<s:if test="%{insertstatus=='duplicate'}"><font color="red">Could not insert Duplicate HappyHours</font></s:if>
							<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Happy Hour Type Name:<font color="red">*</font></label>

										<div class="col-md-4">
											<input type="text" class="form-control" id="name" maxlength="50"
												name="happy.name" value='<s:property value="happy.name"/>'>
												<s:if test="fieldErrors.get('name').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('name').get(0)" /></span>
											</s:if>
										</div>
									</div>
								</div>
								
								<div class="form-body">
								<div class="form-group">
								<label class="col-md-3 control-label">Start Time<sup><font color="red">*</font></sup></label>
								<div class="col-md-3">					
						         <div class="input-group input-medium date form_datetime_bmtc_format" data-date-viewmode="years" data-date-start-date="+0d">
						        	<input type="text" size="16" name="happy.start_time" id="starttimeStringid" class="form-control" 
						        	value='<s:property value="happy.start_time"/>' >
						        	 <span class="input-group-btn">
								        <button class="btn default date-set" type="button">
								  	<i class="fa fa-calendar"></i>
								</button>
							         </span>
						       </div>
						       	<s:if test="fieldErrors.get('starttimeString').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('starttimeString').get(0)" /></span>
											</s:if>
					          </div>
								
							</div>
							</div>
							
							<div class="form-body">	
								<div class="form-group">
								<label class="col-md-3 control-label">End Time<sup><font color="red">*</font></sup></label>
								<div class="col-md-3">					
						         <div class="input-group input-medium date form_datetime_bmtc_format" data-date-viewmode="years" data-date-start-date="+0d">
						        	<input type="text" size="16" name="happy.end_time" id="endtimeStringid" class="form-control" 
						        	value='<s:property value="happy.end_time"/>' >
						        	 <span class="input-group-btn">
								        <button class="btn default date-set" type="button">
								  	<i class="fa fa-calendar"></i>
								</button>
							         </span>
						       </div>
						       <s:if test="fieldErrors.get('endtimeString').size() > 0"> 
		     								<span style="color:red;"><s:property value="fieldErrors.get('endtimeString').get(0)" /></span> 
 											</s:if> 
					          </div>
																	
								
							</div>
						</div>		
								
 								<div class="form-body"> 
									<div class="form-group">
										<label class="col-md-3 control-label">
											Service Type:<font color="red">*</font>
										</label>
										<div class="col-md-4"> 
 											<%-- <s:select list="servicelist" id="service_type_id" name="peakhour.servicetype.service_type_id"  
													cssClass="select2_category form-control" headerKey="0" headerValue="Select"
													onchange="getRateMaster()"></s:select>  --%>
													
									<select class="select2_category form-control" id="service_type_id" name="happy.service_type_id" >
									 <option value="0" >Select</option>
										</select>
 												<s:if test="fieldErrors.get('service_type_id').size() > 0"> 
		     								<span style="color:red;"><s:property value="fieldErrors.get('service_type_id').get(0)" /></span> 
 											</s:if> 
										</div>
									</div>
								</div>
								
								
<!-- 								<div class="form-body">  -->
<!-- 									<div class="form-group"> -->
<!-- 										<label class="col-md-3 control-label"> -->
<!-- 											Schedule Type:<font color="red">*</font> -->
<!-- 										</label> -->
<!-- 										<div class="col-md-4">  -->
<%--  											<s:select list="servicelist" id="service_type_id" name="peakhour.servicetype.service_type_id"  
<%-- 													cssClass="select2_category form-control" headerKey="0" headerValue="Select" --%>
<%-- 													onchange="getRateMaster()"></s:select>  --%> --%>
													
<%-- 									<s:select list="schedulelist" cssClass="select2_category form-control" id="schedule_type_id" name="happy.schedule_type_id"> --%>
									
<%-- 										</s:select> --%>
<%--  												<s:if test="fieldErrors.get('schedule_type_id').size() > 0">  --%>
<%-- 		     								<span style="color:red;"><s:property value="fieldErrors.get('schedule_type_id').get(0)" /></span>  --%>
<%--  											</s:if>  --%>
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</div> -->
								
								
								<div class="form-body">
									<div class="form-group">
									
										<label class="col-md-3 control-label">Percentage:<font color="red">*</font></label>
									<div class="col-md-2">
											<input type="text" class="form-control input-small" id="percentId" maxlength="5"
												name="happy.percentage" onblur="getLumsum()" onkeypress="getLumsum()"
												 value='<s:property value="happy.percentage"/>'>
											<s:if test="fieldErrors.get('percentage').size() > 0"> 
		     								<span style="color:red;"><s:property value="fieldErrors.get('percentage').get(0)" /></span> 
 											</s:if> 
										</div>
										<div class="col-md-1">
											<label class="col-md-1 control-label"> OR</label>
											 																					
										</div>
										<div class="col-md-2">
										<label class="control-label">Lumpsum Amount:<font color="red">*</font></label>
										</div>	
									<div class="col-md-2">	
									   	<input type="text" class="form-control input-small" id="lumpsumId" maxlength="8"
												name="happy.lumpsum_amount" onkeypress="getPercent()" onblur="getPercent()"
												value='<s:property value="happy.lumpsum_amount"/>'>
											<s:if test="fieldErrors.get('lumpsum_amount').size() > 0"> 
		     								<span style="color:red;"><s:property value="fieldErrors.get('lumpsum_amount').get(0)" /></span> 
 											</s:if> 
										</div>
									</div>
								</div>
								<div class="form-body">
								<div class="form-group">
									<label class="col-md-3 control-label">Impact on Fare:<font color="red">*</font></label>
									<div class="col-md-4">
										<select class="select2_category form-control" id="increaseDecreaseId"
											name="happy.increase_decrease">
											<option id="sel" value="2">Select</option>
													<option id="inc" value="1">Increase</option>
													<option id="dec" value="0">Decrease</option>
										</select>
										<s:if test="fieldErrors.get('increase_decrease').size() > 0"> 
		     								<span style="color:red;"><s:property value="fieldErrors.get('increase_decrease').get(0)" /></span> 
 											</s:if> 
                                          <script>
											var status = "<s:property value="happy.increase_decrease"/>";
											if (status != undefined) {
												if (status == "1" || status == "1") {
													document.getElementById("inc").selected = true;
												} else if (status == "2" || status == "2") {
													document.getElementById("dec").selected = true;
												} else {
													document.getElementById("sel").selected = true;
												}
											}
										</script>
									</div>
								</div>
                                </div>
								
								<div class="form-body">
									<div class="form-group">
									<label class="control-label col-md-3">Effective Start  Date:<font
										color="red">*</font></label>
									<div class="col-md-4">
										<div class="input-group input-medium date date-picker"	data-date-format="yyyy-mm-dd" data-date-viewmode="years" >
											<input class="form-control" type="text" size="16"
												name="happy.Effective_start_date" id="procuredDate1" 
												value='<s:property value="happy.Effective_start_date"/>' readonly>
											<span class="input-group-btn">
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
											<script>	
											
// 											var curr_date=new Date().toJSON().slice(0,10);
// 											//alert(curr_date);
// 											curr_date=curr_date.split("-");											
// 											curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
// 											$('#procuredDate1').val(curr_date);
											</script>
										</div>
										<s:if test="fieldErrors.get('Effective_start_date').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('Effective_start_date').get(0)" /></span>
											</s:if>
									</div>
								</div>
									</div>
									<div class="form-body">
									<div class="form-group">
									<label class="control-label col-md-3">Effective End Date:<font color="red"></font></label>
									<div class="col-md-4">
										<div class="input-group input-medium date date-picker"	data-date-format="yyyy-mm-dd" data-date-viewmode="years" >
											<input class="form-control" type="text" size="16"
												name="happy.Effective_end_date" id="complaint_date" 
												value='<s:property value="happy.Effective_end_date"/>' readonly>
											<span class="input-group-btn">
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
										<script>
											var curr_date=new Date().toJSON().slice(0,10);
											//alert(curr_date);
											curr_date=curr_date.split("-");
											
											curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
											//$('#complaint_date').val(curr_date);
											</script> 
										</div>
										
										<s:if test="fieldErrors.get('Effective_end_date').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('Effective_end_date').get(0)" /></span>
											</s:if>
									</div>
								</div>
								</div>

								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
									
										<button  type="submit" class="btn blue" onSubmit="validate()">Save</button>
										<button type="button" class="btn default" onclick="goView()">Cancel</button>
									</div>
								</div>


							</form>
<%-- 								<%}else{ %> --%>
<%-- <%@ include file="/pages/admin/error.jsp" %> --%>
<%-- <%} %>									 --%>
										
<%-- <%}else{%> --%>
 


<%-- <%@ include file="/pages/admin/error.jsp" %> --%>


<%-- <%}%> --%>
							
							<!-- END FORM-->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>