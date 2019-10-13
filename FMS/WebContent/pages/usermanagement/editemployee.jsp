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

	
<script>

function getAgeFromDate(){
	var dob=document.getElementById("date_birth").value;
	var str=dob.split('-');
	var year = str[0];
	var month = str[1];
	var day=str[2];
	var numbinyears =eval(year);
	var numinday=eval(day);
	var numinmonth=eval(month);
	var retirementyear = numbinyears + 60;
	
	 if(numinday==1)
		 {
		 var finalmonth=numinmonth-1
			var days = Math.round(((new Date(year, finalmonth))-(new Date(year, finalmonth-1)))/86400000);
		 day=days;
	     var numinfinalmonth=eval(finalmonth);
	     if(numinfinalmonth==0){
	    	 finalmonth=12; 
	     }
	    var finaldate=retirementyear+"-"+finalmonth+"-"+day;
	    document.getElementById("retirement_date").value=finaldate;
		 }else{
			 var day = Math.round(((new Date(year, month))-(new Date(year, month-1)))/86400000);
			 var finaldate=retirementyear+"-"+month+"-"+day;
			 document.getElementById("retirement_date").value=finaldate;
		 }

}


function getDetails(){
	
	var empid=document.getElementById("empid").valueOf();
	var selectedText = empid.options[empid.selectedIndex].text;
	if(selectedText ==  'Conductor')
		{
		    document.getElementById("driverid").style.visibility = 'hidden';
		     document.getElementById("dlexpirydate").style.visibility = 'hidden';
		     document.getElementById("cldate").style.visibility = 'visible';
			}
	else if(selectedText == 'Driver')
		{
		document.getElementById("cldate").style.visibility = 'hidden';
		document.getElementById("driverid").style.visibility = 'visible';
	    document.getElementById("dlexpirydate").style.visibility = 'visible';
		}
	else{
		
		document.getElementById("driverid").style.visibility = 'visible';
	    document.getElementById("dlexpirydate").style.visibility = 'visible';
	    document.getElementById("cldate").style.visibility = 'visible';
	}
	
	
}


function getEditDetail(){
	var user_name=document.getElementById("user_name").value;

	if(user_name==""){
		alert("Please enter username");
		return false;
		
	}
	var employee_name=document.getElementById("employee_name").value;
	if(employee_name == "" ){
		alert("Please enter Employeename");
		return false;
		
	}
	var employee_name_in_kannada=document.getElementById("employee_name_in_kannada").value;
	if(employee_name_in_kannada == "" ){
		alert("Please enter Employeename in kannada");
		return false;
		
	}
	
	
	var type_of_unit_attached = document.getElementById("type_of_unit_attached").value;
	if(type_of_unit_attached == 0 ){
		alert("Please enter type of unit attached");
		return false;
		
	}
	var parent_depot_unit_name=document.getElementById("parent_depot_unit_name").value;
	if(parent_depot_unit_name == "" ){
		alert("Please enter Parent Depot Unit Name");
		return false;
		
	}
	var date_joining_parent_unit=document.getElementById("date_joining_parent_unit").value;
	if(date_joining_parent_unit == "" ){
		alert("Please enter Date Joining Parent Unit");
		return false;
		
	}
	
	var date_birth=document.getElementById("date_birth").value;
	if(date_birth == "" ){
		alert("Please enter Date Birth");
		return false;
	}
	var cell_phone=document.getElementById("cell_phone").value;
	if(cell_phone == ""){
		
	}else{
	if(isNaN(cell_phone)){
		alert("Only numeric value allowed in cell phone no");
		return false;
	    }
	}
	
	
	
	if(isNaN(type_of_unit_attached)){
		alert("Only numeric value allowed to Type of unit attached ");
		return false;
	}
	
	var Phone=document.getElementById("Phone").value;
	if(Phone == ""){
		
	}else{
	if(isNaN(Phone)){
		alert("Only numeric value allowed to Phone ");
		return false;
	    }
	}
	var emergency_contact_no = document.getElementById("emergency_contact_no").value;
	if(emergency_contact_no == ""){
		
	}else{
	if(isNaN(emergency_contact_no)){
		alert("Only numeric value allowed to Emergency contact no ");
		return false;
	    }
	}
	

var retirement_date=document.getElementById("retirement_date").value;
	
	if(retirement_date == ""){
		alert("Please enter the Retirement Date");
		return false;
	}
	
	
	var email=document.getElementById("email").value;
	
	if(email == 0 ){
		
	}
	else{
var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
if (!filter.test(email)) {
    alert('Please provide a valid email address');
    return false;
 }
}
	
	var empid=document.getElementById("empid").valueOf();
	
	var selectedText = empid.options[empid.selectedIndex].text;
	
	if((!(selectedText ==  'Conductor')) && (!(selectedText ==  'Driver')))
			{
	
		var dl_expirt_date = document.getElementById("dl_expirt_date").value;
			
		
		if(dl_expirt_date == 0 ){
			  alert("Please provide a expire Date");
			  return false;
		}
		var driving_license_number =document.getElementById("driving_license_number").value;
		
		if(driving_license_number == 0 ){
			 alert('Please provide a Drivining Licence Number');
			  return false;
		}
		var conductor_licence_expiry_date_value =document.getElementById("conductor_licence_expiry_date_value").value;
		if(conductor_licence_expiry_date_value == 0 ){
			 alert('Please provide Conductor Expire Date');
			  return false;
		}
	   
			}
	
	document.frm.action="updateEmployeeDetails.action";
	document.frm.submit();
}
function cancelform(){
	document.frm.action="ShowEmployeeList.action";
	document.frm.submit();
}
function getParentOrgUnit(){
	var depotid=document.getElementById("depoid").value;
	$.ajax({
		type : "post",
		url : "/its/pages/usermanagement/parentorgdetails.jsp",
		data : "depotid=" + depotid  ,
		success : function(result) {
			var str=result.split('|');
			var id=str[0];
			var name=str[1];
			document.getElementById("parent_depot_unit").value=id;		
		   document.getElementById("parent_depot_unit_name").value=name;	
			
		}
	});
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
		alert("hiiii");
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
			<!-- BEGIN STYLE CUSTOMIZER -->
			<div class="theme-panel hidden-xs hidden-sm">
				<div class="toggler">
				</div>
				<div class="toggler-close">
				</div>
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
					Employee Details <small></small>
					</h3>
					<ul class="page-breadcrumb breadcrumb">
						<li class="btn-group">
							<button type="button" class="btn blue dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="1000" data-close-others="true">
							<span>Actions</span><i class="fa fa-angle-down"></i>
							</button>
							<ul class="dropdown-menu pull-right" role="menu">
								<li>
									<a href="#">Action</a>
								</li>
								<li>
									<a href="#">Another action</a>
								</li>
								<li>
									<a href="#">Something else here</a>
								</li>
								<li class="divider">
								</li>
								<li>
									<a href="#">Separated link</a>
								</li>
							</ul>
						</li>
						<li>
							<i class="fa fa-home"></i>
							<a href="index.html">Home</a>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<a href="#">Data Tables</a>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<a href="#">Advanced Datatables</a>
						</li>
					</ul>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
	
<div class="tab-content">
							<div class="tab-pane active" id="tab_0">


								<div class="portlet box green">
									<div class="portlet-title">
										<div class="caption">
											<i class="fa fa-gift"></i>Ediit Employee 
										</div>
										<div class="tools">
											<a href="javascript:;" class="collapse">
											</a>
											<a href="#portlet-config" data-toggle="modal" class="config">
											</a>
											<a href="javascript:;" class="reload">
											</a>
											<a href="javascript:;" class="remove">
											</a>
										</div>
									</div>
									<div class="portlet-body form">
										<!-- BEGIN FORM-->
										<form  class="form-horizontal" name ="frm">
										
									<div class="form-group">
									
									<input type="hidden" name="userid" value='<s:property value="emp.user_id" />' id="userid" />
									
									
									
													<label class="col-md-3 control-label">User Name(m) :</label>
													<div class="col-md-4">
												
													
													<input type="text" class="form-control" name="user_name" id="user_name"   value='<s:property value="emp.user_name" /> ' >
												</div>
												</div>
												
												<div class="form-group">
													<label class="col-md-3 control-label">Employee name(m)</label>
													<div class="col-md-4">
													<input type="text" class="form-control" name="employee_name" id="employee_name"    value='<s:property value="emp.name_in_english" /> '  >
												</div>
												
												<div class="col-md-3">
									
														<button class="btn purple" type="button" onclick="convert()">Convert</button>
											</div>
												</div>
												
												<div class="form-group">
													<label class="col-md-3 control-label">Employee Name(Kannada)(ma)</label>
													<div class="col-md-4">
												<input type="text" class="form-control" name="employee_name_in_kannada" id="employee_name_in_kannada"
										 value='' id="employee_name_in_kannada">
													
												</div>
											
												</div>
												
												
											<div class="form-group">
													<label class="col-md-3 control-label">Employee Designation(m):</label>
													<div class="col-md-4">
													 <select class="form-control" id="designationtypeid" name="designationtypeid" >
														<s:iterator value="designationtypelist" var="a" status="designationtypelist_test" >
																<option id='designationtypeid' value="<s:property value="designationtypeid"/>"><s:property value="designationtypename"/></option>
														</s:iterator>
														
													</select>
												</div>
												</div>
												
												<div class="form-group">
													<label class="col-md-3 control-label">Working Designation(m) :</label>
													<div class="col-md-4">
													 <select class="form-control" id="designationtypeid_for_w" name="designationtypeid_for_w" >
														<s:iterator value="designationtypelist" var="a" status="designationtypelisttest" >
														
										 				<option id='designationtypeid' value="<s:property value="designationtypeid"/>"><s:property value="designationtypename"/></option>
														</s:iterator>
													</select>
												</div>
												</div>
												
												<div class="form-group">
													<label class="col-md-3 control-label">Token(o)</label>
													<div class="col-md-4">
												<input type="text" class="form-control" name="token" id="token"  value='<s:property value="emp.token" /> '>
													
												</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">PF</label>
													<div class="col-md-4">
												<input type="text" class="form-control" name="pf" id="pf"  value='<s:property value="emp.pf_number" /> '>
												
												</div>
												</div>
											
											  <div class="form-group">
													<label class="col-md-3 control-label">Gender</label>
													<div class="radio-list">
											
											
													<label><input type="radio" name="gender" id="gender" value="Male"> Male</lable>
													<label> <input type="radio" name="gender" id="gender" value="Female"> Female </label>
											
										</div>
												</div>
												
												
												 <div class="form-group">
													<label class="col-md-3 control-label">Type of Unit attached(m)</label>
													<div class="col-md-4">
												<input type="text" class="form-control" name="type_of_unit_attached" id="type_of_unit_attached"  value='<s:property value="emp.Org_unit_attached" /> '>
													
													
												</div>
												</div>
												
												<div class="form-group">
													<label class="col-md-3 control-label">Working depot/Division/Unit(m):</label>
													<div class="col-md-4">
													 <select class="form-control" id="depoid" name="depoid"  onchange="getParentOrgUnit()">
														<s:iterator value="workingdepotlist" var="a" status="workingdepotlist"  >
										 				<option id='depoid' value="<s:property value="depoid"/>"><s:property value="orgworkingdivisionname"/></option>
														</s:iterator>
													</select>
												</div>
												</div>
												
										
										<div class="form-group">
													<label class="col-md-3 control-label">Working Department(m):</label>
													<div class="col-md-4">
													 <select class="form-control" id="workingdeptid" name="workingdeptid" >
														<s:iterator value="workingdepartmentlist" var="a" status="workingdepartmentlist" >
										 				<option id='workingdeptid' value="<s:property value="workingdeptid"/>"><s:property value="workingdeptname"/></option>
														</s:iterator>
													</select>
												</div>
												</div>
										<div class="form-group">
													<label class="col-md-3 control-label">Working section:</label>
													<div class="col-md-4">
													 <select class="form-control" id="sectiontypeid" name="sectiontypeid" >
														<s:iterator value="sectiontypelist" var="a" status="sectiontypelistdetails" >
										 				<option id='sectiontypeid' value="<s:property value="sectiontypeid"/>"><s:property value="sectiontypename"/></option>
														</s:iterator>
													</select>
												</div>
												</div>
												
													<div class="form-group">
													<label class="col-md-3 control-label">Type of employment(m):</label>
													<div class="col-md-4">
													 <select class="form-control" id="empid" name="empid"  onclick=" getDetails()">
														<s:iterator value="typeofemploymentlist" var="a" status="typeofemploymentlist" >
										 				<option id='empid' value="<s:property value="empid"/>"><s:property value="employee_type"/></option>
														</s:iterator>
													</select>
												</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">Working Type(m)- :</label>
													<div class="col-md-4">
													 <select class="form-control" id="working_type" name="working_type" >
														<option>Loan based</option>
														<option>Regular</option>
													</select>
												</div>
												</div>
												
												<div class="form-group" id="dlexpirydate">
													<label class="col-md-3 control-label">DL Expiry Date(o) :</label>
												<div class="input-group input-medium date date-picker"data-date-format="yyyy-mm-dd" >
												   <input type="text" class="form-control" name="dl_expirt_date" id="dl_expirt_date"  value='<s:property value="emp.driver_license_expiry_date" /> '>
												<span class="input-group-btn">
												<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
												</span>
											</div>
											
												</div>
												
												
												
												
												
												
												<div class="form-group" id="driverid">
													<label class="col-md-3 control-label">Driving License Number(o) :</label>
													<div class="col-md-4">
													
													 <input type="text" class="form-control" name="driving_license_number" id="driving_license_number"  value='<s:property value="emp.driving_license_number" /> '>
												</div>
												</div>
												
												<div class="form-group">
													<label class="col-md-3 control-label">Parent Depot/Division or unit(m) :</label>
													<div class="col-md-4">
													<input type="hidden" name="parent_depot_unit" id="parent_depot_unit" value="">
													 <s:textfield  cssClass="form-control" value="" name="parent_depot_unit_name" id="parent_depot_unit_name" placeholder="Parent Depot/Division or unit"  readonly="true"></s:textfield>
												</div>
												</div>
												
												<div class="form-group">
													<label class="col-md-3 control-label">Date of joining the parent unit :</label>
													<div class="input-group input-medium date date-picker"data-date-format="yyyy-mm-dd" >
												<input type="text" class="form-control" name="date_joining_parent_unit" id="date_joining_parent_unit" >
												<span class="input-group-btn">
												<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
												</span>
											</div>
											
												</div>
												
												
												<div class="form-group" id="cldate">
													<label class="col-md-3 control-label">Conductor License Expiry Date(o) :</label>
													<div class="input-group input-medium date date-picker"data-date-format="yyyy-mm-dd" >
												<input type="text" class="form-control" name="conductor_licence_expiry_date_value" id="conductor_licence_expiry_date_value"  value='<s:property value="emp.conductor_licence_expiry_date_value" /> '>
												<span class="input-group-btn">
												<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
												</span>
											</div>
												</div>
										
										<div class="form-group">
													<label class="col-md-3 control-label">Date Of Birth(m) :</label>
													<div class="input-group input-medium date date-picker"data-date-format="yyyy-mm-dd"  >
												<input type="text" class="form-control" name="date_birth" id="date_birth"  value='<s:property value="emp.birth_date" /> ' onblur="getAgeFromDate()">
												<span class="input-group-btn"   >
												<button class="btn default" type="button" ><i class="fa fa-calendar" ></i></button>
												</span>
											</div>
												</div>
												
												<div class="form-group">
													<label class="col-md-3 control-label">Date of Appointment(o) :</label>
													<div class="input-group input-medium date date-picker"data-date-format="yyyy-mm-dd" >
												<input type="text" class="form-control" name="date_appoinment" id="date_appoinment"  value='<s:property value="emp.date_of_appointment" /> '>
												<span class="input-group-btn">
												<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
												</span>
											</div>
												</div>
												
												<div class="form-group">
													<label class="col-md-3 control-label">Cell Phone(o) :</label>
													<div class="col-md-4">
													<input type="text" class="form-control" name="cell_phone" id="cell_phone"  value='<s:property value="emp.cell_phone" /> '>
												</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">Phone(o) :</label>
													<div class="col-md-4">
													 <input type="text" class="form-control" name="Phone" id="Phone"  value='<s:property value="emp.phone" /> '>
												</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">Email(o) :</label>
													<div class="col-md-4">
													<input type="text" class="form-control" name="email" id="email"  value='<s:property value="emp.email" /> '>
												</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">Retirement Date(o) :</label>
													<div class="col-md-4">
													 <s:textfield  cssClass="form-control" value="" name="retirement_date" id="retirement_date" placeholder="Enter Retirement Date"  readonly="true"></s:textfield>
													</div>
												</div>
												
												
												<div class="form-group">
													<label class="col-md-3 control-label">Father Name(o) :</label>
													<div class="col-md-4">
													 <s:textfield  cssClass="form-control" value="" name="father_name" id="father_name"  ></s:textfield>
												</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">Address(o) :</label>
													<div class="col-md-4">
													
													<s:textarea cssClass="form-control" name="address" ></s:textarea>
													
												</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">Emergency Contact(o) :</label>
													<div class="col-md-4">
													<input type="text" class="form-control" name="emergency_contact_no" id="emergency_contact_no"  value='<s:property value="emp.emergency_contact" /> '>
												</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">RFID Card Number(o) :</label>
													<div class="col-md-4">
													<input type="text" class="form-control" name="rfid_card_no" id="rfid_card_no"  value='<s:property value="emp.card_serial_number" /> '>
												</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">Status(m) :</label>
													<div class="col-md-4">
													 <s:textfield  cssClass="form-control" value="NEW" name="status" id="status"  ></s:textfield>
												</div>
												</div>
												
											<div class="form-actions fluid">
														<div class="col-md-offset-3 col-md-9">
														<input class="btn default" name="Save" value="Save" type="submit" onclick="return getEditDetail()" />
														<input class="btn default" name="Cancel" value="Cancel" type="submit" onclick="cancelform()" />
															
														
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