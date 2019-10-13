<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights"%>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao"%>


<!DOCTYPE html>
<html>
<head>
<script src="assets/pis/js/picker.js"></script>


<script src="assets/pis/js/scrollmessage.js" type="text/javascript"></script>
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
		document.forms[0].action = 'viewPisScrollMessage.action';
		document.forms[0].submit();


} 
function getBusStationNames()
{
    var len= document.getElementById('busstandid').options.length;
    
	 if(len<=1 ) {
       $.ajax({
           type: "post",
           async:false,
           url: '<%=request.getContextPath()%>/findAllBusStationAction',
           async:false,
           success: function(result) {
        	  
               document.getElementById('busstandid').innerHTML=result;
           }
       });
        var prevType="<s:property value='pisScrollMessageModel.busstand.org_chart_id'/>"
		 if(prevType!=""){
			
			 //document.getElementById("orgType"+prevType).selected=true;
			 document.getElementById("busstandid").value=prevType;
			 var orgtypeid = document.getElementById("busstandid").value;
			 var orgtype = busstandid.options[busstandid.selectedIndex].text;
		
			 document.getElementById('select2-chosen-1').innerHTML=orgtype;
			 
		 }
		 
	 } 
	  
	
$.ajax({
    type: "post",
    async:false,
    url: '<%=request.getContextPath()%>/findDisplayUnitAction',
    success: function(result) {
 	  
        document.getElementById('display_unit_id').innerHTML=result;
    }
});
		 var prevType1="<s:property value='pisScrollMessageModel.scroll_display_unit.pis_display_unit_id'/>";
	
		
		 if(prevType1!=""){
			 
			 document.getElementById("display_unit_id").value=prevType1;
			 var orgchartid = document.getElementById("display_unit_id").value;
			 var orgname = display_unit_id.options[display_unit_id.selectedIndex].text;
			
			 document.getElementById('select2-chosen-2').innerHTML=orgname;
			 
		 }   
       
	
}
function getOrgType()
{
 var len= document.getElementById('org_type_id').options.length;
 
	 if(len<=1 ) {
       $.ajax({
           type: "post",
           async:false,
           url: '<%=request.getContextPath()%>/findAllOrgTypeAction',
           async:false,
           success: function(result) {
        	//   alert(result);
               document.getElementById('org_type_id').innerHTML=result;
           }
       });
       var prevType="<s:property value='ticketbagmaster.orgtype.org_type_id'/>";
		//alert(prevType);
		 if(prevType!=""){
			 //document.getElementById("orgType"+prevType).selected=true;
			 document.getElementById("org_type_id").value=prevType;
			 var orgtypeid = document.getElementById("org_type_id").value;
			 var orgtype = org_type_id.options[org_type_id.selectedIndex].text;
			 document.getElementById('select2-chosen-1').innerHTML=orgtype;
		 }
		 
	 }
	
	     //var prevType1="<s:property value='ticketbagmaster.orgchart.org_chart_id'/>";
	    // alert(prevType1);
		 if(document.getElementById("orgType"+prevType).selected==true)
			{
				 $.ajax({
			           type: "post",
			           url: '<%=request.getContextPath()%>/findUnitNameAction?orgid='+prevType,
			           async:false,
			           success: function(result) {
			        	  // alert(result);
			               document.getElementById('org_chart_id').innerHTML=result;
			           }
			       });
			} 

		 var prevType1="<s:property value='ticketbagmaster.orgchart.org_chart_id'/>";
		// alert(prevType1);
		 if(prevType1!=""){
			 //document.getElementById("orgName"+prevType1).selected=true;
			 document.getElementById("org_chart_id").value=prevType1;
			 var orgchartid = document.getElementById("org_chart_id").value;
			 var orgname = org_chart_id.options[org_chart_id.selectedIndex].text;
			 document.getElementById('select2-chosen-2').innerHTML=orgname;
			 
		 }   
	}
function getUnitNames()
{
	var e = document.getElementById("org_type_id");
	var strUser = e.options[e.selectedIndex].value;
	//alert(strUser);
	var len= document.getElementById('org_chart_id').options.length;
	//alert(len);
	
	       $.ajax({
	           type: "post",
	           url: '<%=request.getContextPath()%>/findUnitNameAction?orgid='+strUser,
	           success: function(result) {
	               document.getElementById('org_chart_id').innerHTML=result;
	           }
	        });
	  
}
function validateScrollMessage()
{
	var message=document.getElementById("message").value;
	
	if(message==""){
		alert("Please provide message");
		document.getElementById("message").focus();
		return false;
	}
   var busstand=document.getElementById("busstandid").value;
	
	if(busstand==0){
		alert("Please Select Bus Stand");
		document.getElementById("busstandid").focus();
		return false;
	}
    var displayunit=document.getElementById("displayunit").value;
	
	if(displayunit==0){
		alert("Please  Select Display Unit");
		document.getElementById("displayunit").focus();
		return false;
	}
	var startDate=document.getElementById("startdate").value;
	
	if(startDate==""){
		alert("Please provide Start Date");
		document.getElementById("startdate").focus();
		return false;
	}
    var endDate=document.getElementById("enddate").value;
	
	if(endDate==""){
		alert("Please provide End Date");
		document.getElementById("enddate").focus();
		return false;
	}

	return true;
}
function getPlatformNames()
{
	var e = document.getElementById("bayName");
	var strUser = e.options[e.selectedIndex].value;
	var len= document.getElementById('platformNo').options.length;
	//alert(len);
	
	       $.ajax({
	           type: "post",
	           url: '<%=request.getContextPath()%>/findPlatformNameAction?bayid='
					+ strUser,
			success : function(result) {

				document.getElementById('platformNo').innerHTML = result;
			}
		});

	}
</script>
<script type="text/javascript" src="//www.google.com/jsapi">
	
</script>
</head>

<body onload="getBusStationNames();">
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
								<i class="fa fa-gift"></i>Create Scroll Message
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

							<form action="saveScrollMessage.action" class="form-horizontal"
								method="post" name="create_scroll">
								<div class="form-body">
									<h1 class="intro">
										<s:property value="msg" />
									</h1>
									<div class="form-group">
										<label class="col-md-3 control-label">Message<font
											color="red">*</font></label>
										<div class="col-md-4">
											<div class="input-group">
<textarea name="pisScrollMessageModel.message" id="message" class="form-control" maxlength="100" rows="3" ><s:property value="pisScrollMessageModel.message" /></textarea>
												<s:if test="fieldErrors.get('message').size() > 0">
													<span style="color: red;"><s:property
															value="fieldErrors.get('message').get(0)" /></span>
												</s:if>
											</div>
										</div>
									</div>

									<div class="row">
										<label class="col-md-3 control-label">Font Color<sup><font
												color="red">*</font></sup>
										</label>
										<div class="col-md-3">
											<div class="form-group">
												<input type="text" name="pisScrollMessageModel.font_color"
													id="font_color" class="form-control"
													value="<s:property value="pisScrollMessageModel.font_color" />"
													maxlength="11">
												<s:if test="fieldErrors.get('font_color').size() > 0">
													<span style="color: red;"><s:property value="fieldErrors.get('font_color').get(0)" /></span>
												</s:if>
											</div>
										</div>
										<div class="col-md-3">
											<a href="javascript:TCP.popup(document.forms['create_scroll'].elements['pisScrollMessageModel.font_color'])">
												<img width="15" height="13" border="0"
												alt="Click Here to Pick up the color" src="img/sel.gif">
											</a>
										</div>
									</div>

									<div class="row">

										<label class="col-md-3 control-label">Background Color<sup><font
												color="red">*</font></sup>
										</label>
										<div class="col-md-3">
											<div class="form-group">
												<input type="text"
													name="pisScrollMessageModel.background_color"
													id="background_color" class="form-control"
													value="<s:property value="pisScrollMessageModel.background_color" />"
													maxlength="11">
												<s:if test="fieldErrors.get('background_color').size() > 0">
													<span style="color: red;"><s:property
															value="fieldErrors.get('background_color').get(0)" /></span>
												</s:if>
											</div>
										</div>
										<div class="col-md-3">

											<a
												href="javascript:TCP.popup(document.forms['create_scroll'].elements['pisScrollMessageModel.background_color'])">
												<img width="15" height="13" border="0"
												alt="Click Here to Pick up the color" src="img/sel.gif">
											</a>

										</div>

									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Bus Stand Name<font
											color="red">*</font></label>
										<div class="col-md-4">
											<div class="input-group">

												<select class="select2_category form-control"
													name="pisScrollMessageModel.busstand.org_chart_id"
													id="busstandid" style="width: 200px;">
													<option value="0">select</option>
												</select>


												<s:if test="fieldErrors.get('busstandid').size() > 0">
													<span style="color: red;"><s:property
															value="fieldErrors.get('busstandid').get(0)" /></span>
												</s:if>

												<%--  <script>
															
															 </script> --%>

											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Display Unit
											Name<font color="red">*</font>
										</label>
										<div class="col-md-4">
											<div class="input-group">
												<select class="select2_category form-control"
													name="pisScrollMessageModel.scroll_display_unit.pis_display_unit_id"
													id="display_unit_id" style="width: 200px;"
													onclick="getDisplayUnit()">
													<option value="0">Select</option>

												</select>


												<s:if test="fieldErrors.get('displayunit').size() > 0">
													<span style="color: red;"><s:property
															value="fieldErrors.get('displayunit').get(0)" /></span>
												</s:if>


											</div>
											<script>
												
											</script>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">Effective Date<sup><font
												color="red">*</font></sup>
										</label>
										<div class="col-md-3">
											<div class="input-group input-medium date date-picker"
												data-date-format="dd-mm-yyyy" data-date-start-date="+0d">
												<input type="text" class="form-control" readonly
													name="pisScrollMessageModel.effective_date" id="startdate">
												<span class="input-group-btn">
													<button class="btn default" type="button">
														<i class="fa fa-calendar"></i>
													</button>
												</span>
												<script>
													var curr_date = new Date()
															.toJSON().slice(0,
																	10);
													curr_date = curr_date
															.split("-");
													curr_date = curr_date[2]
															+ "-"
															+ curr_date[1]
															+ "-"
															+ curr_date[0];
													$('#startdate').val(
															curr_date);
												</script>
                                           
											</div>

<%--                                              <s:if test="fieldErrors.get('startdate').size() > 0">
													<span style="color: red;"><s:property
															value="fieldErrors.get('startdate').get(0)" /></span>
												</s:if>  --%>

											<s:if test="fieldErrors.get('startdate').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('startdate').get(0)" /> <script>
															$('#startdate')
																	.val("");
														</script> </span>
											</s:if>

										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">End Date </label>
										<div class="col-md-3">
											<div class="input-group input-medium date date-picker"
												data-date-format="dd-mm-yyyy" data-date-start-date="+0d">
												<input type="text" class="form-control" readonly
													name="pisScrollMessageModel.end_date" id="enddate">
												<span class="input-group-btn"> <s:if
														test="fieldErrors.get('enddate').size() > 0">
														<span style="color: red;"><s:property
																value="fieldErrors.get('enddate').get(0)" /></span>
													</s:if>
													<button class="btn default" type="button">
														<i class="fa fa-calendar"></i>
													</button>
												</span>
												<script>
													var curr_date = new Date()
															.toJSON().slice(0,
																	10);
													curr_date = curr_date
															.split("-");
													curr_date = curr_date[2]
															+ "-"
															+ curr_date[1]
															+ "-"
															+ curr_date[0];
													//$('#enddate')	.val(curr_date);
												</script>

											</div>

										</div>
									</div>


									<div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9">
											<button type="submit" class="btn blue">
												<!-- onclick="return validateScrollMessage()"> -->
												Save
											</button>
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

