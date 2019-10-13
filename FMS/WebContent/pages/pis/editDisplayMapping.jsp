<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights"%>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao"%>

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
		document.forms[0].action = 'viewPisDisplayMapping.action';
		document.forms[0].submit();

 
}
function getBusStationNames(){
    $.ajax({
        type: "post",
        async:false,
        url: '<%=request.getContextPath()%>/findAllBusStationAction',
        async:false,
        success: function(result) {
     	  
            document.getElementById('bus_station_id').innerHTML=result;
        }
    });
    
    var prevType="<s:property value='displaymappingmodel.bus_station_id.org_chart_id'/>";	
    document.getElementById('s1').value=prevType;
		 if(prevType!=""){
			 document.getElementById("bus_station_id").value=prevType;
			 var orgtype = bus_station_id.options[bus_station_id.selectedIndex].text;
			 
			 document.getElementById('select2-chosen-1').innerHTML=orgtype;
			 
		 }
		 	 
		 var prevType1="<s:property value='displaymappingmodel.floor.floor_id'/>";
		 getFloorNames(prevType);
		 if(prevType1!=""){

			 $.ajax({
		         type: "post",
		         url: '<%=request.getContextPath()%>/findFloorNameAfterSubmit?floorid='+prevType1,
		         success: function(result) {
		            document.getElementById('select2-chosen-2').innerHTML=result;
		        	 
		         }
		     });
			 
		 }
		 
		 
		 var prevType2="<s:property value='displaymappingmodel.bay.bay_id'/>";	
		 getBayNames(prevType1);
		 if(prevType2!=""){
			// 
		 $.ajax({
		         type: "post",
		         url: '<%=request.getContextPath()%>/findBayNameAfterSubmit?bayid='+prevType2,
		         success: function(result) {		        	 
		             document.getElementById('select2-chosen-3').innerHTML=result;
		         }
		     }); 
			 
		 }
		 
		 var prevType3="<s:property value='displaymappingmodel.platform.platform_id'/>";
		 getPlatformNames(prevType2);
		 if(prevType3!=""){
			 $.ajax({
		         type: "post",
		         url: '<%=request.getContextPath()%>/findPlatformNameAfterSubmit?platformid='+prevType3,
		         success: function(result) {
		        	
		             document.getElementById('select2-chosen-4').innerHTML=result;
		         }
		     });
			 
		 }
		 
		 var prevType4="<s:property value='displaymappingmodel.display_unit.pis_display_unit_id'/>";
		 getDisplayUnitNames(prevType3);
		 if(prevType4!=""){
			 //
			 $.ajax({
		         type: "post",
		         url: '<%=request.getContextPath()%>/findDisplayUnitNameAfterSubmit?displayunitid='+prevType4,
		         success: function(result) {
		        	
		             document.getElementById('select2-chosen-5').innerHTML=result;
		         }
		     });
			 
		 } 
		 
		 onChangeDisplayUnitNames(prevType4);
}


function getFloorNames(val)
{
	var e = document.getElementById("bus_station_id");
	var strUser=val; 
	
	if(parseInt(val)==0){
	 strUser = e.options[e.selectedIndex].value;
	}
	
	document.getElementById("s1").value=strUser;	
	       $.ajax({
	           type: "post",
	           url: '<%=request.getContextPath()%>/findFloorNameAction?busstationid='+strUser,
	           success: function(result) {
	               document.getElementById('floorName').innerHTML=result;
	           }
	       });
}
function getBayNames(val)
{
	var e = document.getElementById("floorName");
	var strUser=val; 
	
	if(parseInt(val)==0){
	 strUser = e.options[e.selectedIndex].value;
	}

	document.getElementById("f1").value=strUser;
	
	       $.ajax({
	           type: "post",
	           url: '<%=request.getContextPath()%>/findBayNameAction?floorid='+strUser,
	           success: function(result) {
	               document.getElementById('bayName').innerHTML=result;
	           }
	       });
}
function getPlatformNames(val)
{
	var e = document.getElementById("bayName");
	var strUser=val;
	
	if(parseInt(val)==0){
	 strUser = e.options[e.selectedIndex].value;
	}
	
	document.getElementById("b1").value=strUser;
	       $.ajax({
	           type: "post",
	           url: '<%=request.getContextPath()%>/findPlatformNameAction?bayid='+strUser,
	           success: function(result) {
	        	   
	               document.getElementById('platformNo').innerHTML=result;
	           }
	       });

}
function getDisplayUnitNames(val)
{
	var e = document.getElementById("platformNo");
	var strUser=val;
	
	if(parseInt(val)==0){
	 strUser = e.options[e.selectedIndex].value;
	}
	
	document.getElementById("p1").value=strUser;
	
	       $.ajax({
	           type: "post",
	           url: '<%=request.getContextPath()%>/findDisplayUnitNameAction?platformid='+strUser,
	           success: function(result) {
	        	   
	               document.getElementById('display_unit_id').innerHTML=result;
	           }
	       });
}

function onChangeDisplayUnitNames(val)
{
	var e = document.getElementById("display_unit_id");
	var strUser=val;
	
	if(parseInt(val)==0){
	 strUser = e.options[e.selectedIndex].value;
	}
	
	document.getElementById("d1").value=strUser;
	
}

function validSubmit(){
	var bus_stn=$("#s1").val();
	var floor_name=$("#f1").val();
	var bay_name=$("#b1").val();
	var platform_name=$("#p1").val();
	var display_name=$("#d1").val();
	//alert(bus_stn+','+floor_name+','+bay_name+','+platform_name+','+display_name);
	if(bus_stn==0){
		alert("Please Select Bus Station Name");
		return false;
	}
	
	if(floor_name==0){
		alert("Please Select Floor Name");
		return false;
	 }
	
	if(bay_name==0){
		alert("Please Select Bay Name");
		return false;
	 }
	
	if(platform_name==0){
		alert("Please Select Platform Name");
		return false;
	 }
	
	if(display_name==0){
		alert("Please Select Display Unit Name");
		return false;
	 }
}
</script>
<script type="text/javascript" src="//www.google.com/jsapi">

</script>
</head>

<body onload="getBusStationNames()">
	<%
		AccessRightsDao accessrightdao = new AccessRightsDao();
		AccessRights accessrights = new AccessRights();
		int usrsessionid = (Integer) request.getSession().getAttribute(
				"userid");
		accessrights = accessrightdao.accessRightsdetails(usrsessionid,
				"viewPisDisplayMapping.action");
		String access = accessrights.getACCESS();
		String create = accessrights.getCREATE();
		String edit = accessrights.getEDIT();
		String delete = accessrights.getDELETE();
		String read = accessrights.getREAD();
		String error = accessrights.getERROR();
		String viewdelete = accessrights.getVIEWDELETE();
		String viewdeletedrecord = (String) request.getSession()
				.getAttribute("viewdeletedrecord");
	%>

	<div class="page-content-wrapper">
		<div class="page-content">
			<%
				if (access.equalsIgnoreCase("Y")) {
			%>
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<%
							if (create.equalsIgnoreCase("Y")) {
						%>
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Edit Display Mapping
							</div>
							<div class="tools"></div>
						</div>
						<div class="portlet-body form">
							<s:if test="hasActionErrors()">
								<div class="alert alert-danger">
									<button class="close" data-close="alert"></button>
									<span> <s:actionerror />
									</span>
								</div>

							</s:if>

							<!-- BEGIN FORM-->

							<form action="editPisDisplayMappingDetails.action" class="form-horizontal" onsubmit="return validSubmit()"
								method="post">
								<div class="form-body">
									<h1 class="intro">
										<s:property value="msg" />
									</h1>
									<div class="form-group">
										<label class="col-md-3 control-label">Bus Station Name<font
											color="red">*</font></label>
										<div class="col-md-4">
											<div class="input-group">

												<select class="select2_category form-control"
													name="org_chart_id"
													id="bus_station_id" onchange="getFloorNames(0)"
													style="width: 200px;">
													<option value="<s:property value="displaymappingmodel.bus_station_id.org_chart_id" />">
													<s:property value="displaymappingmodel.bus_station_id.org_name" /></option>
												</select> 
											<input type="hidden"
													name="displaymappingmodel.pis_display_platform_id"
													id="displaymapping_id"
													value="<s:property value="displaymappingmodel.pis_display_platform_id" />"
													maxlength="200">
												<s:if test="fieldErrors.get('bus_station_id').size() > 0">
													<span style="color: red;"><s:property
															value="fieldErrors.get('bus_station_id').get(0)" /></span>
												</s:if>

												<%--  <script>
															
															 </script> --%>

											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Floor Name<font
											color="red">*</font></label>
										<div class="col-md-4">
											<div class="input-group">
												<select class="select2_category form-control"
													name="floor_id1" id="floorName"
													style="width: 200px;" onchange="getBayNames(0)">
													<option value="<s:property value="displaymappingmodel.floor.floor_id" />">
													<s:property value="displaymappingmodel.floor.floor_name" /></option>
												</select>


												<s:if test="fieldErrors.get('floorName').size() > 0">
													<span style="color: red;"><s:property
															value="fieldErrors.get('floorName').get(0)" /></span>
												</s:if>


											</div>
											<script>
													
													</script>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">Bay Name<font
											color="red">*</font></label>
										<div class="col-md-4">
											<div class="input-group">
												<select class="select2_category form-control"
													name="bay_id1" id="bayName"
													style="width: 200px;" onchange="getPlatformNames(0)">
													<option value="<s:property value="displaymappingmodel.bay.bay_id" />">
													<s:property value="displaymappingmodel.bay.bay_name" /></option>
												</select>


												<s:if test="fieldErrors.get('bayName').size() > 0">
													<span style="color: red;"><s:property
															value="fieldErrors.get('bayName').get(0)" /></span>
												</s:if>

											</div>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">Platform Name<font
											color="red">*</font></label>
										<div class="col-md-4">
											<div class="input-group">
												<select class="select2_category form-control"
													name="platform_id1" id="platformNo"
													style="width: 200px;" onchange="getDisplayUnitNames(0)">
													<option value="<s:property value="displaymappingmodel.platform.platform_id" />">
													<s:property value="displaymappingmodel.platform.platform_name" /></option>
												</select>


												<s:if test="fieldErrors.get('platformNo').size() > 0">
													<span style="color: red;"><s:property
															value="fieldErrors.get('platformNo').get(0)" /></span>
												</s:if>

											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Display Unit Name<sup><font
												color="red">*</font></sup>
										</label>
										<div class="col-md-3">

											<select class="select2_category form-control"
												name="pis_display_unit_id1" id="display_unit_id"
												style="width: 200px;" onchange="onChangeDisplayUnitNames(0)">
												<option value="<s:property value="displaymappingmodel.display_unit.pis_display_unit_id" />">
													<s:property value="displaymappingmodel.display_unit.pis_display_unit_name" /></option>
												
											</select>


											<s:if test="fieldErrors.get('orgName').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('orgName').get(0)" /></span>
											</s:if>




										</div>
									</div>
									<input type="hidden" name="displaymappingmodel.bus_station_id.org_chart_id" id="s1" value='<s:property value="displaymappingmodel.bus_station_id.org_chart_id" />'/>
									<input type="hidden" name="displaymappingmodel.floor.floor_id" id="f1" value='<s:property value="displaymappingmodel.floor.floor_id" />'/>
									<input type="hidden" name="displaymappingmodel.bay.bay_id" id="b1" value='<s:property value="displaymappingmodel.bay.bay_id" />'/>
									<input type="hidden" name="displaymappingmodel.platform.platform_id" id="p1" value='<s:property value="displaymappingmodel.platform.platform_id" />'/>
									<input type="hidden" name="displaymappingmodel.display_unit.pis_display_unit_id" id="d1" value='<s:property value="displaymappingmodel.display_unit.pis_display_unit_id" />'/>

									<div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9">
											<button type="submit" class="btn blue">Save</button>
											<button type="button" id="cancel" class="btn default"
												onclick="goView()">Cancel</button>

										</div>
									</div>
								</div>
								<s:token />
							</form>
							<!-- END FORM-->
							<%
								} else {
							%>
							<%@ include file="/pages/admin/error.jsp"%>
							<%
								}
							%>

							<%
								} else {
							%>


							<%@ include file="/pages/admin/error.jsp"%>


							<%
								} 
							%>


						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

