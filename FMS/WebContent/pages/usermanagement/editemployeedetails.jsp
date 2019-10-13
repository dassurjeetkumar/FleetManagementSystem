<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	
	<%@ taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE body PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="assets/global/plugins/jquery-1.11.0.min.js"
	type="text/javascript"></script>
	<script type="text/javascript" src="//www.google.com/jsapi"></script>

<script src="assets/admin/pages/scripts/kannada.js"
	type="text/javascript"></script>

<script src="assets/admin/pages/scripts/converter.js"
	type="text/javascript"></script>
	
<script>



function cancelform(){
// 	document.frm.action="ShowEmployeeListDetails.action";
// 	document.frm.submit();
	window.location.href = 'ShowEmployeeListDetails.action';
}
function saveform(){
	var chartid="<s:property value='employee.orgchart.org_chart_id'/>";
// 	alert("chartid"+chartid);
	document.frm.action="editEmployeeAction.action?chartid="+chartid;
 	document.frm.submit();
	
}
function getUnitNames()
{
	$('#select2-chosen-2').html("Select");
	var e = document.getElementById("OrgType");
	var strUser = e.options[e.selectedIndex].value;
	var len= document.getElementById('depot').options.length;
	       $.ajax({
	           type: "post",
	           async:false,
	           url: '<%=request.getContextPath()%>/findUnitName?orgid='+strUser,
	           success: function(result) {
	               document.getElementById('depot').innerHTML=result;
	           }
	       });
		
}
window.onload=getUnitNames;


function checkdesg(){
	 if(document.getElementById("DesignationType").value == "23" ) {
		 $("#TOKEN").hide();
		 $("#dl_expirt_date").hide();
		 $("#driving_license_number").hide();
		 $("#retirement_date").hide();
		 $("#FATHER_NAME").hide();
		 
		// document.getElementById('empname').readOnly=true; 
	 }else{
		// $("#tokn").hide();
		$("#TOKEN").show();
		 $("#dl_expirt_date").show();
		 $("#driving_license_number").show();
		 $("#retirement_date").show();
		 $("#FATHER_NAME").show();
		// document.getElementById('empname').readOnly=false; 
	 }
}

</script>
<script type="text/javascript">
	// Load the Google Transliteration API
	google.load("elements", "1", {
		packages : "transliteration"
	});
	google.load("language", "1");
	
	function onLoad() {
		var options = {
			sourceLanguage : 'en', // or google.elements.transliteration.LanguageCode.ENGLISH,
			destinationLanguage : [ 'kn' ], // or [google.elements.transliteration.LanguageCode.HINDI],
			shortcutKey : 'ctrl+g',
			transliterationEnabled : true
		};
		// Create an instance on TransliterationControl with the required
		// options.
		var control = new google.elements.transliteration.TransliterationControl(
				options);

		// Enable transliteration in the textfields with the given ids.
		//var ids = ["transl1", "transl2" ];
		var ids = [ "employee_name_in_kannada" ];
		control.makeTransliteratable(ids);
		
		

		// Show the transliteration control which can be used to toggle between
		// English and Hindi.
		//control.showControl('translControl');
	}
	
	function convert(){
		//google.load("language", "1");
		//alert("hiiii");
		//var a='["'+document.getElementById("busStopName").value+'"]';
		
//alert(a);
		google.language.transliterate([document.getElementById("employee_name").value], "en", "kn", function(result) {
		if (!result.error) {
		//var container = document.getElementById("transliteration");
		if (result.transliterations && result.transliterations.length > 0 &&
		result.transliterations[0].transliteratedWords.length > 0) {
			//alert(result.transliterations[0].transliteratedWords[0]);
			document.getElementById("employee_name_in_kannada").value = result.transliterations[0].transliteratedWords[0];
		}
		}
		});
	}
	
	
	google.setOnLoadCallback(onLoad);
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
			
			
<div class="tab-content">
							<div class="tab-pane active" id="tab_0">
<h3 class="page-title">
						Employee  <small></small>
					</h3>

								<div class="portlet box green">
									<div class="portlet-title">
										<div class="caption">
											<i class="fa fa-gift"></i>Edit Employee 
										</div>
										<div class="tools">
											
											
											
										</div>
									</div>
									<div class="portlet-body form">
										<div class="portlet-body form">
									
					<font color="Red"> <s:actionerror />
					</font>
					
					
					
					<font color="green"> <s:actionmessage />
					
				</font>
					
											
										<form  class="form-horizontal" name ="frm"  method="POST">
										<input type="hidden" name="createEmployee" value="2" >	
									<div class="form-group">
									<label class="col-md-3 control-label">Employee Id :</label>
									<div class="col-md-4">
											<input type="text" class="form-control" name="employee.employee_id" id="empid"   value='<s:property value="employee.employee_id" />'>
												</div>

								</div>
								
								
								<div class="form-group">
													<label class="col-md-3 control-label">Employee name:<span class="required"> * </span></label>
													<div class="col-md-4">
<%-- 													<input type="text" class="form-control" name="employee.employee_name" id="employee_name"    value='<s:property value="employee.employee_name" /> '  > --%>
												
												 <input class="form-control input-lg"
													id="employee_name" name="employee.employee_name" type="text"
													maxlength="80"
													 onFocus="javascript:print_many_words('employee_name','employee_name_in_kannada')"
                							         onKeyUp="javascript:print_many_words('employee_name','employee_name_in_kannada')"
                							        	 value="<s:property value="employee.employee_name"/>" onblur="spclchar(this);">
												
												
												</div>
												<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('employee_name').get(0)}" />
										</span>
<!-- 												<div class="col-md-3"> -->
									
<!-- 														<button class="btn purple" type="button" onclick="convert()">Convert</button> -->
<!-- 											</div> -->
												
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">Employee Name(Kannada):</label>
													<div class="col-md-4">
<!-- 												<input type="text" class="form-control" name="employee.EMPLOYEE_NAME_KANNADA" id="employee_name_in_kannada" -->
<%-- 										  value='<s:property value="employee.EMPLOYEE_NAME_KANNADA" /> ' id="employee_name_in_kannada"> --%>
										  
										   <input class="form-control input-lg"
												id="employee_name_in_kannada" name="employee.EMPLOYEE_NAME_KANNADA"
												type="text" maxlength="80" 
												value='<s:property value="employee.EMPLOYEE_NAME_KANNADA"/>' 
												/>
													<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('EMPLOYEE_NAME_KANNADA').get(0)}" />
										</span>
												</div>
											
												</div>
							
								
					
							
							<div class="form-group">
													<label class="col-md-3 control-label">Employee Designation:<span class="required"> * </span></label>
													<div class="col-md-4">
										
										<s:select list="designationMap" id="DesignationType" name="employee.WORKING_DESIGNATION.designation_type_id" cssClass="form-control input-medium select2me"  onchange="checkdesg(this)">
										<option id="<s:property   value="designation_type_id" />" value='<s:property   value="designation_type_id" />'>
													<s:property value="designationType" />
												</option>
										
										</s:select>
									
										<script>
											//var desgTypeId ="<s:property value='employee.WORKING_DESIGNATION'/>";
											var desgTypeId ="<s:property value='employee.WORKING_DESIGNATION.designation_type_id'/>";
											
											if(desgTypeId!=""){
												//if(document.getElementById("org"+orgType)!=null)
													//if(document.getElementById("org")+orgType!=null)
												
														document.getElementById(desgTypeId).selected= true;
												
												
											}
										</script>
									<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('EMPLOYEE_DESIGNATION').get(0)}" />
										</span>
									
												</div>
							
							</div>
							
							<div class="form-group">
													<label class="col-md-3 control-label">PF:<span class="required"> * </span></label>
													<div class="col-md-4">
												<input type="text" class="form-control" name="employee.PF" id="pf"  value='<s:property value="employee.PF" /> 'readonly="readonly">
												<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('PF').get(0)}" />
										</span>
												</div>
												</div>
						
						
							
							
									
									<div class="form-group" id="TOKEN">
													<label class="col-md-3 control-label">Token No:<span class="required"> * </span></label>
													<div class="col-md-4">
												<input type="text" class="form-control" name="employee.TOKEN" id="TOKEN" value='<s:property value="employee.TOKEN" /> 'readonly="readonly">
												<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('Token').get(0)}" />
										</span>
											
										</div>
												</div>
							
							
						
									
									
								
							<div class="form-group">
													<label class="col-md-3 control-label">Gender:<span class="required"> * </span></label>
													<div class="radio-list">
											
											
													<label><input type="radio" name="employee.GENDER" id="Male" value="Male"> Male</lable>
													<label> <input type="radio" name="employee.GENDER" id="Female" value="Female"> Female </label>
													<script>
											//var desgTypeId ="<s:property value='employee.WORKING_DESIGNATION'/>";
											var gender ="<s:property value='employee.GENDER'/>";
											
											if(gender!=""){
												//if(document.getElementById("org"+orgType)!=null)
													//if(document.getElementById("org")+orgType!=null)
												
														document.getElementById(gender).checked= true;
												
												
											}
										</script>
											<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('Gender').get(0)}" />
										</span>
										</div>
												</div>

								
									
								

							<div class="form-group">
													<label class="col-md-3 control-label">Working Department:<span class="required"> * </span></label>
													<div class="col-md-4">
												<select class="form-control input-medium select2me"  name="employee.WORKING_DEPT"  >
												<option><s:property value="employee.WORKING_DEPT" /> </option>
												<option value="Trafic Department">Trafic Department</option>
												<option value="Mechanic Department">Mechanic Department</option>
												<option value="Account Department">Account Department</option>
											</select>
											<script>
											var orgTypeId ="<s:property value='employee.WORKING_DEPT'/>";
											if(orgTypeId!=""){
 												document.getElementById(orgTypeId).selected= true;
 												
												
											}
										</script>
											
										<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('WORKING_DEPT').get(0)}" />
										</span>
												
												</div>
								
	</div>
												
												<div class="form-group" id="dl_expirt_date">
													<label class="col-md-3 control-label">DL Expiry Date :</label>
												<div class="input-group input-medium date date-picker"data-date-format="dd-mm-yyyy" >
												   <input type="text" class="form-control" name="employee.DL_Exp_Date" id="dl_expirt_date" value='<s:property value="employee.DL_Exp_Date" /> ' >
												<span class="input-group-btn">
												<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
												</span>
											</div>
											
								</div>
								
								<div class="form-group" id="driving_license_number">
													<label class="col-md-3 control-label">Driving License Number :<span class="required"> * </span></label>
													<div class="col-md-4">
													
													 <input type="text" class="form-control" name="employee.DRIVING_LC_NO" id="driving_license_number" value='<s:property value="employee.DRIVING_LC_NO" /> ' >
												<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('DRIVING_LC_NO').get(0)}" />
										</span>
												</div>
												</div>
								
								
								
											
												
												
												<div class="form-group">
													<label class="col-md-3 control-label">Date Of Birth :<span class="required"> * </span></label>
													<div class="input-group input-medium date date-picker"data-date-format="dd-mm-yyyy"  >
												<input type="text" class="form-control" name="employee.D_O_B" id="date_birth" value='<s:property value="employee.D_O_B" /> '  >
												<span class="input-group-btn"   >
												<button class="btn default" type="button" ><i class="fa fa-calendar" ></i></button>
												</span>
											<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('DOB').get(0)}" />
										</span>
											</div>
												</div>
												
												<div class="form-group">
													<label class="col-md-3 control-label">Cell Phone :</label>
													<div class="col-md-4">
													<input type="text" class="form-control" name="employee.CELL_PHONE" id="cell_phone"  value='<s:property value="employee.CELL_PHONE" /> '>
												
													<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('getCELL_PHONE').get(0)}" />
										</span></div>
												</div>
												
												
												<div class="form-group">
													<label class="col-md-3 control-label">Email :</label>
													<div class="col-md-4">
													<input type="text" class="form-control" name="employee.EMAIL" id="email"  value='<s:property value="employee.EMAIL" /> '>
												<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('getEMAIL').get(0)}" />
										</span>
												</div>
												</div>
												
												
												<div class="form-group" id="retirement_date">
													<label class="col-md-3 control-label">Retirement Date :<span class="required"> * </span></label>
													<div class="input-group input-medium date date-picker"data-date-format="dd-mm-yyyy"  >
												<input type="text" class="form-control" name="employee.RetirementDate" id="retirement_date"  value='<s:property value="employee.RetirementDate" /> ' >
												<span class="input-group-btn"   >
												<button class="btn default" type="button" ><i class="fa fa-calendar" ></i></button>
												</span>
												<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('RETIREMENT_DT').get(0)}" />
										</span>
												</div>
												</div>
												
												<div class="form-group" id="FATHER_NAME">
													<label class="col-md-3 control-label">Father Name :<span class="required"> * </span></label>
													<div class="col-md-4">
													 <s:textfield  cssClass="form-control"  name="employee.FATHER_NAME" id="FATHER_NAME"  >
													 </s:textfield>
												
												</div>
											</div>
											
											<div class="form-group">
													<label class="col-md-3 control-label">Address :</label>
													<div class="col-md-4">
													
													<s:textarea cssClass="form-control" name="employee.ADDRESS" ></s:textarea>
													
												</div>
												</div>
							
							
							<div class="form-group">
													<label class="col-md-3 control-label">Status :<span class="required"> * </span></label>
													<div class="col-md-4">
													
												<select class="form-control input-small select2me" data-placeholder="Select..." name="employee.STATUS">
												<option value="ACTIVE">Active</option>
												<option value="INACTIVE">InActive</option>
											</select>
												<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('getSTATUS').get(0)}" />
										</span>
												
												</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">Date of Appointment :</label>
													<div class="input-group input-medium date date-picker"data-date-format="dd-mm-yyyy" >
												<input type="text" class="form-control" name="employee.D_O_A" id="date_appoinment"  value='<s:property value="employee.D_O_A" /> '>
												<span class="input-group-btn">
												<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
												</span>
											</div>
											</div>
												<div class="form-group" id="cldate">
													<label class="col-md-3 control-label">Conductor License Expiry Date :</label>
													<div class="input-group input-medium date date-picker"data-date-format="dd-mm-yyyy" >
												<input type="text" class="form-control" name="employee.Con_Lic_Exp_Date" id="conductor_licence_expiry_date_value" value='<s:property value="employee.Con_Lic_Exp_Date" /> '>
												<span class="input-group-btn">
												<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
												</span>
											</div>
												</div>	
												
													<div class="form-group" id="CONDUCTOR_LC_RENDATE">
													<label class="col-md-3 control-label">Conductor License Renewal Date :</label>
													<div class="col-md-3">
									<div class="input-group input-medium date date-picker"	data-date-format="dd-mm-yyyy" data-date-viewmode="years" >
										<input type="text" class="form-control" name="employee.Conductor_Renewal_Date"	readonly value="<s:property value='employee.Conductor_Renewal_Date'/>">
										<span class="input-group-btn">
											<button class="btn default" type="button">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
									</div>
<%-- 									<span class="help-block" style="color: red"> <s:property --%>
<%-- 											value="%{fieldErrors.get('proceduredDate').get(0)}" /></span> --%>
								</div>
												</div>	
												
												<div class="form-group" id="DRIVER_LC_RENDATE">
													<label class="col-md-3 control-label">Driver License Renewal Date :</label>
														<div class="col-md-3">
									<div class="input-group input-medium date date-picker"	data-date-format="dd-mm-yyyy" data-date-viewmode="years" >
										<input type="text" class="form-control" name="employee.Driver_Renewal_Date"	readonly value="<s:property value='employee.Driver_Renewal_Date'/>">
										<span class="input-group-btn">
											<button class="btn default" type="button">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
									</div>
<%-- 									<span class="help-block" style="color: red"> <s:property --%>
<%-- 											value="%{fieldErrors.get('proceduredDate').get(0)}" /></span> --%>
								</div>
												</div>	
												
												<div class="form-group" id="ADHAR_CARD_NO">
													<label class="col-md-3 control-label">Adhar Card Number :<span class="required">  </span></label>
													<div class="col-md-4">
													
													 <input type="text" class="form-control" name="employee.ADHAR_CARD_NO" id="ADHAR_CARD_NO" 
													  value='<s:property value="employee.ADHAR_CARD_NO" /> '>
													  <span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('ADHAR_CARD_NO').get(0)}" />
										</span>
												</div>
												</div>
												
												<div class="form-group">
													<label class="col-md-3 control-label">Employee Type Name :</label>
													<div class="col-md-4">
													 <s:select list="emptype" name="employee.EMPLOYEE_TYPE_NAME" cssClass="form-control input-medium select2me" >
													   
 												</s:select>
											    <script>
											var emptype ="<s:property value='employee.EMPLOYEE_TYPE_NAME'/>";
											if(emptype!=""){
 												document.getElementById(emptype).selected= true;
 												
												
											}
										</script>
												</div>
												</div>
							
									 <div class="form-group">
													<label class="col-md-3 control-label">Organization Type :</label>
													<div class="col-md-4">
													 <s:select list="orgtypeMap" id="OrgType" name="employee.orgtype.org_type_id" cssClass="form-control input-medium select2me" onchange="getUnitNames()">
													 <option id="<s:property   value="org_type_id" />" value='<s:property   value="org_type_id" />'>
 													<s:property value="orgType" /> 
												</option>
													 </s:select>
									<script>
											var orgTypeId ="<s:property value='employee.orgtype.org_type_id'/>";
											if(orgTypeId!=""){
 												document.getElementById(orgTypeId).selected= true;
 												
												
											}
										</script>
											<span class="help-block" style="color: red"> 
										<s:property value="%{fieldErrors.get('orgtype').get(0)}" />
									</span>
									
												</div>
												</div>
																
											<div class="form-group">
								<label class="col-md-3 control-label">Organization Name </label>
								<div class="col-md-3">
									<select class="select2_category form-control" name="employee.orgchart.org_chart_id" id="depot">
											<option id='<s:property value="employee.orgchart.org_chart_id"/>' value='<s:property value="employee.orgchart.org_chart_id"/>'>
											<s:property value="employee.orgchart.org_name" />
											</option>
									</select>
									
							<script type="text/javascript">							
									var orgName= "<s:property value='employee.orgchart.org_chart_id'/>";
										if(orgName!=""){
											document.getElementById(orgName).selected=true;
											alert(orgname);
										}
									</script>	
									
									<span class="help-block" style="color: red"> 
										<s:property value="%{fieldErrors.get('orgchart').get(0)}" />
									</span>
								</div>
							</div>				
										
											
											<div class="form-actions fluid">
														<div class="col-md-offset-3 col-md-9">
														<button type="button" class="btn blue" onclick="saveform()">Save</button>
												<button class="btn default" type="button" onclick="cancelform()">Cancel</button>	
														
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