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
jQuery(document).ready(function(){

	 SelectElement('<s:property value="displayUnitModel.template_name"/>');

});

function SelectElement(valueToSelect)
{ 
var element = document.getElementById('template_name');
element.value = valueToSelect;
}

function goView() {

		/* var val = $("input[type='checkbox']").val(); */
		//alert(val);
		document.forms[0].action = 'viewDisplayUnit.action';
		document.forms[0].submit();

 
}
function getBusStationNames()
{
    var len= document.getElementById('bus_station_id').options.length;
    
	 if(len<=1 ) {
       $.ajax({
           type: "post",
           async:false,
           url: '<%=request.getContextPath()%>/findAllBusStationAction',
           async:false,
           success: function(result) {
        	  
               document.getElementById('bus_station_id').innerHTML=result;
           }
       });
        
	 }
	 var prevType="<s:property value='displayUnitModel.display_bus_station_id.org_chart_id'/>";
		
	 if(prevType!=""){
		
		 //document.getElementById("orgType"+prevType).selected=true;
		 document.getElementById("bus_station_id").value=prevType;
		 var orgtypeid = document.getElementById("bus_station_id").value;
		 var orgtype = bus_station_id.options[bus_station_id.selectedIndex].text;
		 
		 document.getElementById('select2-chosen-1').innerHTML=orgtype;
	 }
	     //var prevType1="<s:property value='ticketbagmaster.orgchart.org_chart_id'/>";
	    //alert(prevType1);
		 if(document.getElementById("bus_station_id"+prevType).selected==true)
			{
			
			 
		} 

		 var prevType1="<s:property value='displayUnitModel.display_floor.floor_id'/>";
			
			
		 if(prevType1!=""){
			 $.ajax({
		         type: "post",
		         url: '<%=request.getContextPath()%>/findFloorNameAfterSubmit?floorid='+prevType1,
		         success: function(result) {
		           
		             document.getElementById('select2-chosen-2').innerHTML=result;
		         }
		     });
			 
		 }
		 var prevType2="<s:property value='displayUnitModel.display_bay.bay_id'/>";
			
		
		 if(prevType2!=""){
			 $.ajax({
		         type: "post",
		         url: '<%=request.getContextPath()%>/findBayNameAfterSubmit?bayid='+prevType2,
		         success: function(result) {
		             
		             document.getElementById('select2-chosen-3').innerHTML=result;
		         }
		     });
			 
		 }
		 var prevType3="<s:property value='displayUnitModel.display_platform.platform_id'/>";
			
		
		 if(prevType3!=""){
			 $.ajax({
		         type: "post",
		         url: '<%=request.getContextPath()%>/findPlatformNameAfterSubmit?platformid='+prevType3,
		         success: function(result) {
		             
		             document.getElementById('select2-chosen-4').innerHTML=result;
		         }
		     });
			 
		 }
 
		<%-- 
		 if(document.getElementById("floorName"+prevType1).selected==true)
			{
			
			 alert("st");
			 $.ajax({
		           type: "get",
		           url: '<%=request.getContextPath()%>/findBayNameAction?floorid='+prevType1 ,
		           success: function(result) {
		               document.getElementById('bayName').innerHTML=result;
		           }
		       });
			 alert("end");
			}   --%>
    
	}
function getFloorNames()
{
	var e = document.getElementById("bus_station_id");
	var strUser = e.options[e.selectedIndex].value;
	//alert(strUser);
	var len= document.getElementById('floorName').options.length;
	//alert(len);
	
	       $.ajax({
	           type: "post",
	           url: '<%=request.getContextPath()%>/findFloorNameAction?busstationid='+strUser,
	           success: function(result) {
	               document.getElementById('floorName').innerHTML=result;
	           }
	       });
	       var prevType1="<s:property value='displayUnitModel.display_floor.floor_id'/>";
			
			 if(prevType1!=""){
				 //document.getElementById("orgName"+prevType1).selected=true;
				 document.getElementById("floorName").value=prevType1;
				 //var floorName= document.getElementById("floorName").value;
				
				 var floorTypeName=floorName.options[floorName.selectedIndex].text;
				
				 document.getElementById('select2-chosen-2').innerHTML=floorTypeName ;
				 
			 }   
}
function goCancel()
{
	document.forms[0].action = 'viewDisplayUnit.action';
	document.forms[0].submit();
}

function getBayNames()
{
	var e = document.getElementById("floorName");
	var strUser = e.options[e.selectedIndex].value;
	//alert(strUser);
	var len= document.getElementById('bayName').options.length;
	//alert(len);
	
	       $.ajax({
	           type: "post",
	           url: '<%=request.getContextPath()%>/findBayNameAction?floorid='+strUser,
	           success: function(result) {
	               document.getElementById('bayName').innerHTML=result;
	           }
	       });
	       var prevType1="<s:property value='displayUnitModel.display_bay.bay_id'/>";
			
			 if(prevType1!=""){

				 var floorTypeName=bayName.options[bayName.selectedIndex].text;
				  
				 document.getElementById('select2-chosen-3').innerHTML=floorTypeName ;
				 
			 }    
}
function getPlatformNames()
{
	var e = document.getElementById("bayName");
	var strUser = e.options[e.selectedIndex].value;
	var len= document.getElementById('platformNo').options.length;
	//alert(len);
	
	       $.ajax({
	           type: "post",
	           url: '<%=request.getContextPath()%>/findPlatformNameAction?bayid='+strUser,
	           success: function(result) {
	        	   
	               document.getElementById('platformNo').innerHTML=result;
	           }
	       });
	      var prevType1="<s:property value='ticketbagmaster.orgchart.org_chart_id'/>";
			//alert(prevType);
			 if(prevType1!=""){
				 document.getElementById("orgName"+prevType1).selected=true;
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
				"viewPisScrollMessage.action");
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
								<i class="fa fa-gift"></i>Edit Display Unit
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

							<form action="editDisplayUnitData.action" class="form-horizontal"
								method="post">
								<div class="form-body">
									<h1 class="intro">
										<s:property value="msg" />
									</h1>
									<div class="form-group">
										<label class="col-md-3 control-label">Display Unit
											Name Name<sup><font color="red">*</font></sup>
										</label>
										<div class="col-md-3">

											<input type="text"
												name="displayUnitModel.pis_display_unit_name"
												id="display_unitname" class="form-control"
												value="<s:property value="displayUnitModel.pis_display_unit_name" />"
												maxlength="11">
											<s:if test="fieldErrors.get('display_unitname').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('display_unitname').get(0)" /></span>
											</s:if>
											<input type="hidden"
												name="displayUnitModel.pis_display_unit_id"
												id="displaymapping_id"
												value="<s:property value="displayUnitModel.pis_display_unit_id" />"
												maxlength="200">


										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">Bus Station Name<font
											color="red">*</font></label>
										<div class="col-md-4">
											<div class="input-group">

												<select class="select2_category form-control"
													name="displayUnitModel.display_bus_station_id.org_chart_id"
													id="bus_station_id" onchange="getFloorNames()"
													style="width: 200px;">
													<option
														value="<s:property value="displayUnitModel.display_bus_station_id.org_chart_id" />">
														<s:property
															value="displayUnitModel.display_bus_station_id.org_name" />
													</option>
												</select>

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
													name="displayUnitModel.display_floor.floor_id"
													id="floorName" style="width: 200px;"
													onchange="getBayNames()">
													<option
														value="<s:property value="displayUnitModel.display_floor.floor_id" />">
														<s:property
															value="displayUnitModel.display_floor.floor_name" />
													</option>
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
													name="displayUnitModel.display_bay.bay_id" id="bayName"
													style="width: 200px;" onchange="getPlatformNames()">
													<option
														value="<s:property value="displayUnitModel.display_bay.bay_id" />">
														<s:property value="displayUnitModel.display_bay.bay_name" />
													</option>
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
													name="displayUnitModel.display_platform.platform_id"
													id="platformNo" style="width: 200px;">
													<option
														value="<s:property value="displayUnitModel.display_platform.platform_id" />">

														<s:property
															value="displayUnitModel.display_platform.platform_name" />

													</option>
												</select>


												<s:if test="fieldErrors.get('platformNo').size() > 0">
													<span style="color: red;"><s:property
															value="fieldErrors.get('platformNo').get(0)" /></span>
												</s:if>

											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Refresh Rate<sup><font
												color="red">*</font></sup>
										</label>
										<div class="col-md-3">

											<input type="text" name="displayUnitModel.refresh_rate"
												id="refresh_rate" class="form-control" 
												value="<s:property value="displayUnitModel.refresh_rate" />"
												maxlength="6">
											<s:if test="fieldErrors.get('refresh_rate').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('refresh_rate').get(0)" /></span>
											</s:if>



										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Template Name:</label>
										<div class="col-md-3">
											<select class="form-control" name="displayUnitModel.template_name" id="template_name">
												<option value="ETA">ETA</option>
												<option value="ETD">ETD</option>
											</select>									    
										</div>
							       </div>

									<div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9">
											<button type="submit" class="btn blue">Save</button>
											<button type="button" id="cancel" class="btn default"
												onclick="goCancel()">Cancel</button>

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

