<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<script src="<%=request.getContextPath()%>/vehicle/keyBoardEvents.js" type="text/javascript"></script>
<div class="page-content-wrapper" >
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
%>
	<div class="page-content">
	<%if (access.equalsIgnoreCase("Y")){%>
		<%if (create.equalsIgnoreCase("Y")){%>
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
					Vehicle 
				</h3>
			</div>
		</div>
		<div class="row">
	
			<div class="col-md-12">
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-bars"></i>Create Vehicle
						</div>
					</div>
					<div class="portlet-body form">
						<b>
							<font color="green"> <s:actionmessage/></font>
							<font color="red"> <s:actionerror/></font>
						</b>
						<form action="AjaxCreateNewVehicleAction" class="form-horizontal form-row-seperated" method="post">
							<div class="form-group">
								<input type="hidden" name="createNewVehicle" value="yes" /> 
								
								<label	class="control-label col-md-3">Registration No<span	class="required"> * </span></label>
								<div class="col-md-3">
									<input type="text" class="form-control" placeholder="Enter text" name="reigistrationNumber"	value="<s:property value='%{reigistrationNumber}'/>"  maxlength="12"/> 
										<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('reigistrationNumber').get(0)}" />
										</span>
								</div>
<!-- 								<label	class="control-label" style="color:#3b4455"><i>(KAXX-XX-XXXX)</i></label> -->
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">Organization Type<span class="required"> * </span></label>
									<div class="col-md-3">
										<select id="unit_type_id" name="unitType" class="select2-container select2_category form-control"  onchange="getUnitNames()">
											<s:iterator value="orgTypeList" status="aaa">
												<option id="org<s:property   value="org_type_id" />"
													value='<s:property   value="org_type_id" />'>
													<s:property value="orgType" />
												</option>
											</s:iterator>
										</select>
										<script>
											var orgType ="<s:property value='unitType'/>";
											if(orgType!=""){
												document.getElementById("org"+orgType).selected= true;
											}
										</script>
											<span class="help-block" style="color: red"> <s:property value="%{fieldErrors.get('org_type_id').get(0)}" /></span>
									</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">Organization Unit Name<span class="required"> * </span> </label>
								<div class="col-md-3">
									<select class="select2_category form-control" name="orgChartId" id="depot" onload="getUnitNames()">
											<option value="0">--Select--</option>
									</select> 
									<span class="help-block" style="color: red"> 
										<s:property value="%{fieldErrors.get('orgName').get(0)}" />
									</span>
								</div>
							</div>	
					
							<div class="form-group">
								<label class="control-label col-md-3">Progressive Running KM </label>
								<div class="col-md-3">
									<input type="text" class="form-control"	name="progressingRunningKms" placeholder="Enter number"	value='<s:property value='progressingRunningKms'/>'
												maxlength="7" onkeypress="onlyIntAndDot(event)">
										<span class="help-block"style="color: red">
											<s:property value="%{fieldErrors.get('progressingRunningKms').get(0)}" />
										</span>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3">Progressive Schedule KM </label>
								<div class="col-md-3">
									<input type="text" class="form-control" name="progressingScheduleKms" placeholder="Enter number" 
												value='<s:property value='progressingScheduleKms'/>' maxlength="7" onkeypress="onlyIntAndDot(event)"/> 
										<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('progressingScheduleKms').get(0)}" />
										</span>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3">Vehicle Type <span class="required"> * </span></label>
								<div class="col-md-3">
									<select id="s2id_autogen3" name="vehicleTypeId" class="select2-container select2_category form-control">
										<s:iterator value="vehicleTypeList" status="aaa">
											<option id="vehicleType<s:property value="vehicle_type_id" />"	value='<s:property value="vehicle_type_id" />'>
												<s:property value="vehicle_type_name" />
											</option>
										</s:iterator>
									</select>
									<script>
										var vehicleType = "<s:property value='vehicleTypeId'/>";
										if (vehicleType != "") {
											document.getElementById("vehicleType"+ vehicleType).selected = true;
										}
									</script>
									<span class="help-block" style="color: red"> 
										<s:property	value="%{fieldErrors.get('vehicleTypeId').get(0)}" />
									</span>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3">Brand Type <span class="required"> * </span></label>
								<div class="col-md-3">
									<select id="s2id_autogen3" name="brandTypeId" class="select2-container select2_category form-control">
										<s:iterator value="brandTypeList" status="aaa">
											<option id='brand<s:property value="brand_type_id"/>' value='<s:property value="brand_type_id" />'>
												<s:property value="brand_type_name" />
											</option>
										</s:iterator>
									</select>
									<script>
										var brandType = "<s:property  value='brandTypeId'/>";
										if (brandType != "") {
											document.getElementById("brand"+ brandType).selected = true;
										}
									</script>
									<span class="help-block" style="color: red">
										 <s:property value="%{fieldErrors.get('brandTypeId').get(0)}" />
									</span>
								</div>
							</div>
														
							<div class="form-group">
									<label class="col-md-3 control-label">A/C Availability:</label>
									<div class="col-md-3">
										<select class="form-control select2_category" id="acAvailability" name="acAvailability">
											<option id="phone" value="YES">YES </option>
											<option id="email" value="NO">NO </option>
										</select>
												<script>
															var media = "<s:property value="acAvailability"/>";
															if (media != undefined) {
																if (media == "YES" || media == "YES") {
																	document.getElementById("phone").selected = true;
																} else {
																	document.getElementById("email").selected = true;
																}
															}
														</script>
									</div>
								</div>
							
							<div class="form-group">
								<label class="control-label col-md-3">Make <span class="required"> * </span></label>
								<div class="col-md-3">
									<select id="s2id_autogen3" name="makeTypeId" class="select2-container select2_category form-control">
										<s:iterator value="makeList" status="aaa">
											<option id='make<s:property value="make_type_id"/>'	value='<s:property value="make_type_id" />'>
												<s:property value="make_type_name" />
											</option>
										</s:iterator>
									</select>
									<script>
										var makeType = "<s:property  value='makeTypeId'/>";
										if (makeType != "") {
											document.getElementById("make"+ makeType).selected = true;
										}
									</script>
									<span class="help-block" style="color: red"> 
										<s:property	value="%{fieldErrors.get('makeTypeId').get(0)}" />
									</span>
								</div>
							</div>
							
								<div class="form-group">
								<label class="control-label col-md-3">Norm <span class="required"> * </span></label>
								<div class="col-md-3">
									<select id="norm" name="norm" class="select2-container select2_category form-control">
											<option value="0">--Select--</option>
											<option value="BS-I">BS-I</option>
											<option value="BS-II">BS-II</option>
											<option value="BS-III">BS-III</option>
											<option value="BS-IV">BS-IV</option>
											
											
										
									</select>
							
									
									<script>
										var normtype = "<s:property  value='norm'/>";
										 if (normtype != "") {
											
										
										 $('select[name="norm"]:first').val(normtype);} 
									</script>
									<span class="help-block" style="color: red"> 
										<s:property	value="%{fieldErrors.get('norm').get(0)}" />
									</span>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-3">Wheel Base(Inch)<span class="required"> * </span></label>
								<div class="col-md-3">
									<select id="s2id_autogen3" name="modelTypeId"	class="select2-container select2_category form-control">
										<s:iterator value="modelList" status="aaa">
											<option id="model<s:property value="model_type_id"/>"  value='<s:property value="model_type_id" />'>
												<s:property value="model_type_name" />
											</option>
										</s:iterator>
									</select>
									<script>
										var modelType = "<s:property  value='modelTypeId'/>";
										if (modelType != "") {
											document.getElementById("model"
													+ modelType).selected = true;
										}
									</script>
									<span class="help-block" style="color: red">
										<s:property	value="%{fieldErrors.get('modelTypeId').get(0)}" />
									</span>
								</div>
							</div>
							
							
								<div class="form-group">
								<label class="control-label col-md-3">Floor Height(mm)<span class="required"> * </span></label>
								<div class="col-md-3">
									<select id="floorrHeigt" name="floorrHeigt" class="select2-container select2_category form-control">
											<option value="0">--Select--</option>
											<option value="400">400</option>
											<option value="650">650</option>
											<option value="740">740</option>
											<option value="900">900</option>
											<option value="1100">1100</option>
											
										
									</select>
						
									<script>
										var floorhtype = "<s:property  value='floorrHeigt'/>";
										 if (floorhtype != "") {
											
										
										 $('select[name="floorrHeigt"]:first').val(floorhtype);} 
									</script>
									<span class="help-block" style="color: red"> 
										<s:property	value="%{fieldErrors.get('floorrHeigt').get(0)}" />
									</span>
								</div>
							</div>
							
									<div class="form-group">
								<label class="control-label col-md-3">HP(Horse Power)<span class="required"> * </span></label>
								<div class="col-md-3">
									<select id="hp" name="hp" class="select2-container select2_category form-control">
											<option value="0">--Select--</option>
											<option value="120">120</option>
											<option value="124">124</option>
											<option value="130">130</option>
											<option value="147">147</option>
											<option value="160">160</option>
											<option value="165">165</option>
											<option value="180">180</option>
											<option value="275">275</option>
											<option value="290">290</option>
									</select>
								
									
									<script>
										var horsetype = "<s:property  value='hp'/>";
										 if (horsetype != "") {
											 $('select[name="hp"]:first').val(horsetype);
										} 
									

									</script>
									<span class="help-block" style="color: red"> 
										<s:property	value="%{fieldErrors.get('hp').get(0)}" />
									</span>
								</div>
							</div>
							
											<div class="form-group">
								<label class="control-label col-md-3">Seating Capacity<span class="required"> * </span></label>
								<div class="col-md-3">
									<select id="seat_Capacity" name="seat_Capacity" class="select2-container select2_category form-control">
											<option value="0">--Select--</option>
											<option value="30+1">30+1</option>
											<option value="31+1">31+1</option>
											<option value="32+1">32+1</option>
											<option value="35+1">35+1</option>
											<option value="41+1">41+1</option>
											<option value="42+1">42+1</option>
											<option value="44+1">44+1</option>
											<option value="52+1">52+1</option>
									</select>
								
									
									<script>
										var seattype = "<s:property  value='seat_Capacity'/>";
										 if (seattype != "") {
										 $('select[name="seat_Capacity"]:first').val(seattype);
										} 
										

									</script>
									<span class="help-block" style="color: red"> 
										<s:property	value="%{fieldErrors.get('seat_Capacity').get(0)}" />
									</span>
								</div>
							</div>
							
													<div class="form-group">
								<label class="control-label col-md-3">Unladen Weight(kg)<span class="required"> * </span></label>
								<div class="col-md-3">
									<select id="UnladenWeight" name="UnladenWeight" class="select2-container select2_category form-control">
											<option value="0">--Select--</option>
											<option value="7000 to 7500">7000 to 7500</option>
											<option value="7501 to 8000">7501 to 8000</option>
											<option value="8001 to 9000">8001 to 9000</option>
											<option value="9001 to 9500">9001 to 9500</option>
											<option value="9501 to 10000">9501 to 10000</option>
											<option value="10001 to 10500">10001 to 10500</option>
											<option value="10501 to 11000">10501 to 11000</option>
											<option value="11001 to 11500">11001 to 11500</option>
									</select>
								
									
									<script>
										var unladetype = "<s:property  value='UnladenWeight'/>";
										 if (unladetype != "") {
									 $('select[name="UnladenWeight"]:first').val(unladetype);
										} 
									

									</script>
									<span class="help-block" style="color: red"> 
										<s:property	value="%{fieldErrors.get('UnladenWeight').get(0)}" />
									</span>
								</div>
							</div>
							
					
			<%-- 				<div class="form-group">
								<label class="control-label col-md-3">Body Type <span class="required"> * </span></label>
								<div class="col-md-3">
									<select id="s2id_autogen3" name="bodyTypeId" class="select2-container select2_category form-control">
										<s:iterator value="bodyTypeList" status="aaa">
											<option id='body<s:property value="body_type_id"/>'
												value='<s:property value="body_type_id" />'>
												<s:property value="body_type_name" />
											</option>
										</s:iterator>
									</select>
									<script>
										var bodyType = "<s:property  value='bodyTypeId'/>";
										if (bodyType != "") {
											document.getElementById("body"+ bodyType).selected = true;
										}
									</script>
									<span class="help-block" style="color: red"> 
										<s:property	value="%{fieldErrors.get('bodyTypeId').get(0)}" />
									</span>
								</div>
							</div> --%>
					
					<%-- 		<div class="form-group">
								<label class="control-label col-md-3">Docking Planning	Date </label>
								<div class="col-md-3">
									<div class="input-group input-medium date date-picker"	data-date-format="dd-mm-yyyy" data-date-viewmode="years">
										<input type="text" class="form-control"	name="dockingPlanningDate" readonly	value="<s:property value='dockingPlanningDate'/>"> 
										<span class="input-group-btn">
											<button class="btn default" type="button">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
									</div>
									<span class="help-block" style="color: red">
										<s:property	value="%{fieldErrors.get('dockingPlanningDate').get(0)}" />
									</span>
								</div>
							</div> --%>
							
							<div class="form-group">
								<label class="control-label col-md-3">Docking Km<span class="required"> * </span></label>
								<div class="col-md-3">
									<input type="text" class="form-control" name="dockingklms" placeholder="Enter Doking Klms" value="<s:property value='dockingklms'/>" maxlength="50"/>
									<span class="help-block" style="color: red"> 
										<s:property	value="%{fieldErrors.get('dockingklms').get(0)}" />
									</span>
								</div>
							</div>
							
					<%-- 		<div class="form-group">
								<label class="control-label col-md-3">Service Type  <span class="required"> * </span></label>
								<div class="col-md-3">
									<select id="s2id_autogen3" name="serviceTypeId" class="select2-container select2_category form-control">
										<s:iterator value="serviceTypeList" status="aaa">
											<option id="service<s:property   value="service_type_id" />" value='<s:property   value="service_type_id" />'>
												<s:property value="service_type_name" />
											</option>
										</s:iterator>
									</select>
									<script>
										var serviceType = "<s:property   value='serviceTypeId'/>";
										if (serviceType != "") {
											document.getElementById("service"+ serviceType).selected = true;
										}
									</script>
									<span class="help-block" style="color: red"> <s:property
											value="%{fieldErrors.get('serviceTypeId').get(0)}" /></span>
								</div>
							</div> --%>
					
							<div class="form-group">
								<label class="control-label col-md-3">Chassis Number <span class="required"> * </span></label>
								<div class="col-md-3">
									<input type="text" class="form-control" name="chasisNumber" placeholder="Enter number" value="<s:property value='chasisNumber'/>" maxlength="50"/>
									<span class="help-block" style="color: red"> 
										<s:property	value="%{fieldErrors.get('chasisNumber').get(0)}" />
									</span>
								</div>
							</div>
								<div class="form-group">
								<label class="control-label col-md-3">Engine Number <span class="required"> * </span></label>
								<div class="col-md-3">
									<input type="text" class="form-control" name="engineNumber" placeholder="Enter Engine number" value="<s:property value='engineNumber'/>" maxlength="50"/>
									<span class="help-block" style="color: red"> 
										<s:property	value="%{fieldErrors.get('engineNumber').get(0)}" />
									</span>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3">No of Wheels <span class="required"> * </span></label>
								<div class="col-md-3">
									<input type="text" class="form-control" name="wheelCount" placeholder="Enter number"	value='<s:property value="wheelCount"/>' maxlength="3" onkeypress="onlyIntAndDot(event)">
									<span class="help-block" style="color: red"> 
										<s:property	value="%{fieldErrors.get('wheelCount').get(0)}" />
									</span>
								</div>
							</div>
							
						<%-- 			<div class="form-group">
									<label class="control-label col-md-3">FC Expiry date<span class="required"> * </span></label>
									<div class="col-md-3">
										<div class="input-group input-medium date date-picker"	data-date-format="dd-mm-yyyy" data-date-viewmode="years" data-date-start-date="+0d">
										<input type="text" class="form-control" name="fcExpiryDate"	value="<s:property value='fcExpiryDate'/>" readonly /> <span
														class="input-group-btn">
											<button class="btn default" type="button">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
									</div>
									<span class="help-block" style="color: red"> 
										<s:property	value="%{fieldErrors.get('fcExpiryDate').get(0)}" />
									</span>
								</div>
							</div> --%>
							<div class="form-group">
									<label class="control-label col-md-3">FC Renewal date<span class="required">*</span></label>
									<div class="col-md-3">
										<div class="input-group input-medium date date-picker"	data-date-format="dd-mm-yyyy" data-date-viewmode="years" data-date-start-date="+0d">
										<input type="text" class="form-control" name="fcRenewalDate"	value="<s:property value='fcRenewalDate'/>" readonly /> <span
														class="input-group-btn">
											<button class="btn default" type="button">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
									</div>
									<span class="help-block" style="color: red"> 
										<s:property	value="%{fieldErrors.get('fcRenewalDate').get(0)}" />
									</span>
								</div>
							</div>
							
									<div class="form-group">
								<label class="control-label col-md-3">Procured Date <span class="required"> </span></label>
								<div class="col-md-3">
									<div class="input-group input-medium date date-picker"	data-date-format="dd-mm-yyyy" data-date-viewmode="years" >
										<input type="text" class="form-control" name="procuredDate"	readonly value="<s:property value='procuredDate'/>">
										<span class="input-group-btn">
											<button class="btn default" type="button">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
									</div>
									<span class="help-block" style="color: red"> <s:property
											value="%{fieldErrors.get('proceduredDate').get(0)}" /></span>
								</div>
							</div>
							
									<div class="form-group">
								<label class="control-label col-md-3">Operational Date(Doo) <span class="required"> * </span></label>
								<div class="col-md-3">
									<div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" data-date-viewmode="years">
										<input type="text" class="form-control" name="operationalDate" readonly value="<s:property value='operationalDate'/>">
										<span class="input-group-btn">
											<button class="btn default" type="button">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
									</div>
									<span class="help-block" style="color: red">
										 <s:property value="%{fieldErrors.get('operationalDate').get(0)}" />
									</span>
								</div>
							</div>
									<div class="form-group">
								<label class="control-label col-md-3">Registration Date(Doc) <span class="required"> * </span></label>
								<div class="col-md-3">
									<div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" data-date-viewmode="years">
										<input type="text" class="form-control"	name="registrationDate" readonly value="<s:property value='registrationDate'/>"> 
										<span class="input-group-btn">
											<button class="btn default" type="button">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
									</div>
									<span class="help-block" style="color: red"> 
										<s:property	value="%{fieldErrors.get('registrationDate').get(0)}" />
									</span>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3">Vehicle Usage	Category <span class="required"> * </span></label>
									<div class="col-md-3">
										<select id="s2id_autogen3" name="vehicleUsageCatageryId" class="select2-container select2_category form-control">
										<s:iterator value="vehicleCategoryList" status="aaa">
											<option id="category<s:property   value="vehicleCategoryId" />"	value='<s:property   value="vehicleCategoryId" />'>
												<s:property value="vehicleCategoryName" />
											</option>
										</s:iterator>
									</select>
									<script>
										var categoryType = "<s:property   value='vehicleUsageCatageryId'/>";
										if (categoryType != "") {
											document.getElementById("category"+ categoryType).selected = true;
										}
									</script>
									<span class="help-block" style="color: red"> 
										<s:property	value="%{fieldErrors.get('vehicleUsageCatageryId').get(0)}" />
									</span>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3">Under Warranty <span class="required"> * </span></label>
								<div class="col-md-3">
									<select class="form-control select2_category" id="acAvailability" name="underWarranty">
										<option id="uwYes" value="YES">YES</option>
										<option id="uwNo" value="NO">NO</option>
									</select>
									<script>
										var media = "<s:property value="underWarranty"/>";
										if (media != "") {
											if (media == "YES"|| media == "YES") {
												document.getElementById("uwYes").selected = true;
											} else {
												document.getElementById("uwNo").selected = true;
											}
										}
									</script>
								</div>
							</div>
						
							<div class="form-group">
								<label class="control-label col-md-3">Status <span class="required"> * </span></label>
								<div class="col-md-3">
									<select class="form-control select2_category" name="status">
										<option id="active" value="ACTIVE">ACTIVE</option>
										<option id="inactive" value="INACTIVE">INACTIVE</option>
										<!-- <option value="BREAKDOWN">BREAKDOWN</option>
										<option value="BURNT">BURNT</option>
										<option value="SCRAP">SCRAP</option>
										<option value="TRANSFERED">TRANSFERED</option>
										<option value="ACCIDENT">ACCIDENT</option> -->
									</select>
									<script>
										var media = "<s:property value="status"/>";
											if (media != "") {
												if (media == "ACTIVE"|| media == "ACTIVE") {
													document.getElementById("active").selected = true;
												} else {
													document.getElementById("inactive").selected = true;
												}
										}
									</script>
								</div>
							</div>
							<div class="form-actions fluid">
								<div class="col-md-offset-3 col-md-3">
									<button type="submit" class="btn blue">Save</button>
									<button type="button" class="btn default" onclick="callCancell()">Cancel</button>
								</div>
							</div>
						</form>
						<%}else{ %>
<%@ include file="/pages/admin/error.jsp" %>
<%} %>									
										
<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	function callCancell() {
		window.location.href = 'VehicleDetails.action';
	}
	getUnitNames();
	
	function getUnitNames()
	{
		$('#select2-chosen-2').html("Select");
		var e = document.getElementById("unit_type_id");
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
	
	var orgName= "<s:property value='orgChartId'/>";
	if(orgName!=""){
		document.getElementById("orgName"+orgName).selected=true;
	}
</script>
