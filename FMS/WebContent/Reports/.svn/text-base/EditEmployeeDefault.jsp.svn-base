<%@page import="com.trimax.its.vehicle.model.Vehicle"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<head>
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/assets/global/plugins/clockface/css/clockface.css" />
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css" />
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css" />
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-colorpicker/css/colorpicker.css" />
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" />
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-datetimepicker/css/datetimepicker.css" />
<link rel="stylesheet"	href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
<link rel="stylesheet"	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="<%=request.getContextPath()%>/vehicle/keyBoardEvents.js" type="text/javascript"></script>

<style type="text/css">

.col-md-9,.col-md-8 {
	width: 30%;
}
.tab-pane { /* border-right: 1px solid #dddddd;
	border-left: 1px solid #dddddd;
	border-bottom: 1px solid #dddddd; */
}
.help-block,ul,li {
	list-style: none;
}

</style>
<script>
	function validate() {
		var shiftid=$('#dutytype').val();
		var shiftid1=$('#dutytype1').val();
		var wo1=$('#wo1').val();
		var wo2=$('#wo2').val();
		var section=$('#section').val();
		var d1=$('#effective_start_date').val();
		var d2=$('#effective_end_date').val();
		//alert($('#wo2').val());
		//alert(shiftid);
		//alert(shiftid !="GS1");
		//alert(shiftid !="SS");
		if(shiftid==0){
			alert("Please Select The DutyType");
		}
		else if(wo1==0){
			alert("Please Select The W/O1");
		}
		else if(section==0){
			alert("Please Select Section");
		}
		else if(wo2==0 && (shiftid =="2" || shiftid=="3" || shiftid=="5" || shiftid=="6" || shiftid=="7")){
			alert("Please Select The W/O2"); 
		}else if(shiftid =="2" && shiftid1 !="3" ){alert("Please select Next Duty Type NS");}
		else if(shiftid =="3" && shiftid1 !="2" ){alert("Please select Next Duty Type GS2");}
		else if((shiftid =="1" || shiftid=="4" || shiftid=="5" || shiftid=="6" || shiftid=="7") && shiftid1 !="0"){alert("Please select Next Duty Type --NO--");}
		else if(d1=="" || d2=="" || d1==null || d2==null ){
			alert("Please Pick The Dates");
		}
		else{
		/* 	document.forms[0].action = 'SaveEditEmployeeduty.action'
			document.forms[0].submit(); */
			document.getElementById("submitid").submit();
		}
	}
	</script>
	<script>
	function callCancell() {

		window.location.href = 'employeeDutyTypeview.action';
	}
</script>
<script>
function desdrop(){

	var shiftid=$('#dutytype').val();
	
	//alert(shiftid);
	if(shiftid=="1" || shiftid=="4"){
		//document.getElementById(empid+"wo1").disabled=true;
		document.getElementById("wo2").disabled=true;
		//document.getElementById("dutytype1").disabled=true;
		}
	else{
		
		//document.getElementById(empid+"wo1").disabled=false;
		document.getElementById("wo2").disabled=false;
	
		}

	//alert($("#dutytype1").val());
	//alert($("#dutytype11").val());
	}
	
	

</script>
</head>
<div class="page-content-wrapper">
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "VehicleDetails.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
int rollid=accessrightdao.getroleid(usrsessionid);
%>

	<div class="page-content">
	<%if (access.equalsIgnoreCase("Y")){%>
	<%if (create.equalsIgnoreCase("Y")){%>
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
					Edit Employee Duty Type
				</h3>
			</div>
		</div>
		<div class="row">
		
			<div class="col-md-12">
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-bars"></i>Edit Employee Duty
						</div>
					</div>
					<div class="portlet-body">
						<div class="portlet-body form">
							<b>
								<font color="green"> <s:actionmessage/></font>
							<span id="errorMsg" style="color:red;word-wrap: break-word;" ><s:actionerror/></span>
							</b>
							<form action="SaveEditEmployeeduty" class="form-horizontal form-row-seperated" method="post" id="submitid"><s:token/>	
								<div class="form-body">
								
					
									
									<div class="form-group">
									<s:hidden name="empid" value="%{empid}" />
									<s:hidden name="dutyrotaid" value="%{dutyrotaid}" />
										<label class="control-label col-md-3"> Emp Name :<span class="required"> * </span></label>
										<div class="col-md-3">
											<input type="text" class="form-control" name="name" value='<s:property value='name'/>' maxlength="50" readonly/>
											<span class="help-block" style="color: red"> 
												<s:property	value="%{fieldErrors.get('name').get(0)}" />
											</span>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"> Emp PF :<span class="required"> * </span></label>
										<div class="col-md-3">
											<input type="text" class="form-control" name="pf" value='<s:property value='pf'/>' maxlength="50" readonly/>
											<span class="help-block" style="color: red"> 
												<s:property	value="%{fieldErrors.get('pf').get(0)}" />
											</span>
										</div>
									</div>
										<div class="form-group">
										<label class="control-label col-md-3"> Emp Token :<span class="required"> * </span></label>
										<div class="col-md-3">
											<input type="text" class="form-control" name="token" value='<s:property value='token'/>' maxlength="50" readonly/>
											<span class="help-block" style="color: red"> 
												<s:property	value="%{fieldErrors.get('token').get(0)}" />
											</span>
										</div>
									</div>
								<%-- 					<div class="form-group">
										<label class="control-label col-md-3">Duty Type :<span class="required"> * </span></label>
										<div class="col-md-3">
											<s:select list="dutytypelist" id="dutytype" name="dutytype" class="select2-container select2_category form-control" onchange="desdrop()">
											
									</s:select>
											<script>
											var dutytype = "<s:property  value='dutytype'/>";
											//alert(dutytype);
											 if (dutytype != "") {
												 
										    $('select[name="dutytype"]:first').val(dutytype); 
	                                           }
											</script>
											<span class="help-block" style="color: red">
												<s:property value="%{fieldErrors.get('dutytype').get(0)}" />
											</span>
										</div>
									</div> --%>
									
															<div class="form-group">
										<label class="control-label col-md-3">Section :<span class="required"> * </span></label>
										
									<div class="col-md-3">
											<s:select list="section" id="section"
												name="section1" 
												cssClass="select2_category form-control" headerKey="0" headerValue="---Select---"
												 ></s:select>
										
											<script>
											var section = "<s:property  value='sectionvar'/>";
											//alert(section);
											 if (section != "") {
												 
										    $('select[name="section1"]:first').val(section); 
	                                           }
											</script>
											
										</div>
									</div>
									
													<div class="form-group">
										<label class="control-label col-md-3">Batch :<span class="required"> * </span></label>
										
									<div class="col-md-3">
											<s:select list="batchlist" id="batch"
												name="batch1" 
												cssClass="select2_category form-control" headerKey="0" headerValue="---Select---"
												 ></s:select>
										
											<script>
											var batchvar = "<s:property  value='batch'/>";
											//alert(batchvar);
											 if (batchvar != "") {
												 
										    $('select[name="batch1"]:first').val(batchvar); 
	                                           }
											</script>
											
										</div>
									</div>
									
									
									
															<div class="form-group">
										<label class="control-label col-md-3">Duty Type :<span class="required"> * </span></label>
										
									<div class="col-md-3">
											<s:select list="dutytypelist" id="dutytype"
												name="dutytype" onchange="desdrop()"
												cssClass="select2_category form-control" headerKey="0" headerValue="---Select---"
												 ></s:select>
										
											<script>
											var dutytype = "<s:property  value='dutytype'/>";
											//alert(dutytype);
											 if (dutytype != "") {
												 
										    $('select[name="dutytype"]:first').val(dutytype); 
	                                           }
											</script>
											
										</div>
									</div>
									
														<div class="form-group">
										<label class="control-label col-md-3">W/o1 :<span class="required"> * </span></label>
										<div class="col-md-3">
											<select id="wo1" name="wo1" class="select2-container select2_category form-control">
											<option value='0'>--select--</option> 
											<option value='sunday'>Sunday</option>
											<option value='monday'>Monday</option>
											<option value='tuesday'>Tuesday</option>
											<option value='wednesday'>Wednesday</option>
											<option value='thursday'>Thursday</option>
											<option value='friday'>Friday</option>
											<option value='saturday'>Saturday</option>
										
									</select>
											<script>
											var wo1 = "<s:property  value='wo1'/>";
											//alert(wo1);
											 if (wo1 != "") {
												 
										    $('select[name="wo1"]:first').val(wo1); 
	                                           }
											</script>
											<span class="help-block" style="color: red">
												<s:property value="%{fieldErrors.get('wo1').get(0)}" />
											</span>
										</div>
									</div>	
									<input type="hidden" id='dutytype11' name="dutytype11" />
															<div class="form-group">
										<label class="control-label col-md-3">Next Duty Type :<span class="required"> * </span></label>
										
									<div class="col-md-3">
											<s:select list="dutytypelist" id="dutytype1" 
												name="dutytype1" 
												cssClass="select2_category form-control" headerKey="0" headerValue="---NO---" 
												></s:select>
										
											<script>
											var dutytype1 = "<s:property  value='dutytype1'/>";
											//alert(dutytype);
											 if (dutytype1 != "") {
												 
										    $('select[name="dutytype1"]:first').val(dutytype1); 
	                                           }
											</script>
											
										</div>
									</div>
									
														<div class="form-group">
										<label class="control-label col-md-3">W/o2 :<span class="required"> * </span></label>
										<div class="col-md-3">
											<select id="wo2" name="wo2" class="select2-container select2_category form-control">
											<option value='0'>--select--</option> 
											<option value='sunday'>Sunday</option>
											<option value='monday'>Monday</option>
											<option value='tuesday'>Tuesday</option>
											<option value='wednesday'>Wednesday</option>
										<option value='thursday'>Thursday</option>
										<option value='friday'>Friday</option>
										<option value='saturday'>Saturday</option>
									</select>
											<script>
											var wo2 = "<s:property  value='wo2'/>";
											//alert(wo2);
											 if (wo2 != "") {
												 
										    $('select[name="wo2"]:first').val(wo2); 
	                                           }
											</script>
											<span class="help-block" style="color: red">
												<s:property value="%{fieldErrors.get('wo2').get(0)}" />
											</span>
										</div>
									</div>
									<div class="form-group">
									<label class="control-label col-md-3">Effective Start Date :<span	class="required"> *</span></label>
									<div class="col-md-9">
										<div class="input-group input-medium date date-picker"	style="width: auto" data-date-format="dd-mm-yyyy" data-date-viewmode="years" data-date-start-date="+0d">
												<input type="text" class="form-control"  value="<s:property value="effective_start_date"/>" name="effective_start_date" id="effective_start_date"/> 
												<span class="input-group-btn">
													<button class="btn default" type="button">
														<i class="fa fa-calendar"></i>
													</button>
												</span>
										</div>
											<span class="help-block" style="color: red">
												<s:property	value="%{fieldErrors.get('effective_start_date').get(0)}" />
											</span>
									</div>
								</div>
								
									<div class="form-group">
								<label class="control-label col-md-3">Effective End Date :<span class="required"> </span></label>
								<div class="col-md-3">
									<div class="input-group input-medium date date-picker"	data-date-format="dd-mm-yyyy" data-date-viewmode="years" data-date-start-date="+0d">
										<input type="text" class="form-control" name="effective_end_date"	id="effective_end_date" value="<s:property value='effective_end_date'/>">
										
										<span class="input-group-btn">
											<button class="btn default" type="button">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
									</div>
									<span class="help-block" style="color: red"> <s:property
											value="%{fieldErrors.get('effective_end_date').get(0)}" /></span>
								</div>
							</div>
								
								<div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9">
											<button type="button" class="btn blue" onclick="validate()">Save</button>
											<button type="button" class="btn default"onclick="callCancell()">Cancel</button>
										</div>
									</div>
								
								</div>
							</form>
							<%} %>
									
										
<%}%>

							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
<script>
$(document).ready(function () {
	//document.getElementById("dutytype1").disabled=true;
	//document.getElementById("wo2").disabled=true;
});
</script>

<!-- END PAGE CONTENT-->