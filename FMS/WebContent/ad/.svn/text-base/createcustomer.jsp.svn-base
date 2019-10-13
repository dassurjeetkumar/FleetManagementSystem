<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights"%>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao"%>

<!DOCTYPE html>
<html>
<head>
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
$( document ).ready(function() {
	
	
	});
function goView() {

		/* var val = $("input[type='checkbox']").val(); */
		 //alert(val);
		document.forms[0].action = 'Customerdetails.action';
		document.forms[0].submit();


} 
function getCustomerNames()
{
    var len= document.getElementById('cust_id').options.length;
    
	 if(len<=1 ) {
       $.ajax({
           type: "post",
           async:false,
           url: '<%=request.getContextPath()%>/findCustomerNameAction',
           async:false,
           success: function(result) {
        	  
               document.getElementById('cust_id').innerHTML=result;
           }
       });
        var prevType="<s:property value='advetisement.cust.id'/>";
       
		 if(prevType!=""){
			
			 //document.getElementById("orgType"+prevType).selected=true;
			 document.getElementById("cust_id").value=prevType;
			 var orgtypeid = document.getElementById("cust_id").value;
			 var orgtype = cust_id.options[cust_id.selectedIndex].text;
		    
			 document.getElementById('select2-chosen-1').innerHTML=orgtype;
		 }
		 
	 } 
	  
	 <%-- 
	     //var prevType1="<s:property value='ticketbagmaster.orgchart.org_chart_id'/>";
	    // alert(prevType1);
		 if(document.getElementById("orgType"+prevType).selected==true)
			{
				 $.ajax({
			           type: "get",
			           url: '<%=request.getContextPath()%>/findUnitNameAction?orgid='+prevType,
			           async:false,
			           success: function(result) {
			        	  // alert(result);
			               document.getElementById('org_chart_id').innerHTML=result;
			           }
			       });
			} 
--%>

       
	
}

function validateScrollMessage()
{
	var message=document.getElementById("cust_id").value;
	
	if(message==""){
		alert("Please provide message");
		document.getElementById("cust_id").focus();
		return false;
	}
  
}

// function getAdvertisementTypes()
// {
// 	 var len= document.getElementById('advertisement_type_id').options.length;
	    
// 	 if(len<=1 ) {
//        $.ajax({
//            type: "post",
//            async:false,
<%--            url: '<%=request.getContextPath()%>/findAllAdvertisementTypesAction', --%>
//            async:false,
//            success: function(result) {
        	  
//                document.getElementById('advertisement_type_id').innerHTML=result;
//            }
//        });
//         var prevType="<s:property value='advetisement.advetisetype.advertisement_type_id'/>"
// 		 if(prevType!=""){
			
// 			 //document.getElementById("orgType"+prevType).selected=true;
// 			 document.getElementById("advertisement_type_id").value=prevType;
// 			 var orgtypeid = document.getElementById("advertisement_type_id").value;
// 			 var orgtype = advertisement_type_id.options[advertisement_type_id.selectedIndex].text;
		
// 			document.getElementById('select2-chosen-2').innerHTML=orgtype;
// 		 }
		 
// 	 } 	  
// }
</script>
<script type="text/javascript" src="//www.google.com/jsapi">

</script>
</head>

<body onload="getCustomerNames();getAdvertisementTypes()">
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
								<i class="fa fa-gift"></i>Create Customer Details
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

							<form action="savecustomerdetails" class="form-horizontal"
								method="post" enctype="multipart/form-data">
								<div class="form-body">
									<h1 class="intro">
										<s:property value="msg" />
									</h1>
									</div>
						
									
								<%-- 	<div class="form-group">
										<label class="col-md-3 control-label">Id 
											<sup><font color="red">*</font></sup>
										</label>
										<div class="col-md-3">

											<input type="text" name="advetisement.advertisement_name"
												id="advertisement_name" class="form-control"
												value="<s:property value="advetisement.advertisement_name" />"
												maxlength="50">
											<s:if test="fieldErrors.get('advertisement_name').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('advertisement_name').get(0)" /></span>
											</s:if>

										</div>
									</div> --%>
									<div class="form-group">
										<label class="col-md-3 control-label">Customer 
											Name<sup><font color="red">*</font></sup>
										</label>
										<div class="col-md-3">

											<input type="text" name="customername"
												id="advertisement_name" class="form-control"
												value="<s:property value="advetisement.advertisement_name" />"
												maxlength="50">
											<s:if test="fieldErrors.get('advertisement_name').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('advertisement_name').get(0)" /></span>
											</s:if>

										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Code 
<%-- 											<sup><font color="red">*</font></sup> --%>
										</label>
										<div class="col-md-3">

											<input type="text" name="C_code"
												id="advertisement_name" class="form-control"
												value="<s:property value="advetisement.advertisement_name" />"
												maxlength="50">
											<s:if test="fieldErrors.get('advertisement_name').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('advertisement_name').get(0)" /></span>
											</s:if>

										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Address 
 							<%-- <sup><font color="red">*</font></sup> --%> 
										</label>
										<div class="col-md-3">

											<input type="text" name="C_Address"
												id="advertisement_name" class="form-control"
												value="<s:property value="advetisement.advertisement_name" />"
												maxlength="50">
											<s:if test="fieldErrors.get('advertisement_name').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('advertisement_name').get(0)" /></span>
											</s:if>

										</div>
									</div>
											<div class="form-group">
										<label class="col-md-3 control-label">City 
 						<%-- 	<sup><font color="red">*</font></sup>  --%>
										</label>
										<div class="col-md-3">

											<input type="text" name="C_city"
												id="advertisement_name" class="form-control"
												value="<s:property value="advetisement.advertisement_name" />"
												maxlength="50">
											<s:if test="fieldErrors.get('advertisement_name').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('advertisement_name').get(0)" /></span>
											</s:if>

										</div>
									</div>
											<div class="form-group">
										<label class="col-md-3 control-label">State
 						<%-- 	<sup><font color="red">*</font></sup>  --%>
										</label>
										<div class="col-md-3">

											<input type="text" name="C_state"
												id="advertisement_name" class="form-control"
												value="<s:property value="advetisement.advertisement_name" />"
												maxlength="50">
											<s:if test="fieldErrors.get('advertisement_name').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('advertisement_name').get(0)" /></span>
											</s:if>

										</div>
									</div>
											<div class="form-group">
										<label class="col-md-3 control-label">Country
 							<%-- <sup><font color="red">*</font></sup> --%> 
										</label>
										<div class="col-md-3">

											<input type="text" name="C_Country"
												id="advertisement_name" class="form-control"
												value="<s:property value="advetisement.advertisement_name" />"
												maxlength="50">
											<s:if test="fieldErrors.get('advertisement_name').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('advertisement_name').get(0)" /></span>
											</s:if>

										</div>
									</div>
											<div class="form-group">
										<label class="col-md-3 control-label">Email 
 						<%-- 	<sup><font color="red">*</font></sup>  --%>
										</label>
										<div class="col-md-3">

											<input type="text" name="C_email"
												id="advertisement_name" class="form-control"
												value="<s:property value="advetisement.advertisement_name" />"
												maxlength="50">
											<s:if test="fieldErrors.get('advertisement_name').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('advertisement_name').get(0)" /></span>
											</s:if>

										</div>
									</div>
											<div class="form-group">
										<label class="col-md-3 control-label">Website 
 						<%-- 	<sup><font color="red">*</font></sup>  --%>
										</label>
										<div class="col-md-3">

											<input type="text" name="Website"
												id="advertisement_name" class="form-control"
												value="<s:property value="advetisement.advertisement_name" />"
												maxlength="50">
											<s:if test="fieldErrors.get('advertisement_name').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('advertisement_name').get(0)" /></span>
											</s:if>

										</div>
									</div>
											<div class="form-group">
										<label class="col-md-3 control-label">Phone 
 							<%-- <sup><font color="red">*</font></sup>  --%>
										</label>
										<div class="col-md-3">

											<input type="text" name="C_Phone"
												id="advertisement_name" class="form-control"
												value="<s:property value="advetisement.advertisement_name" />"
												maxlength="50">
											<s:if test="fieldErrors.get('advertisement_name').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('advertisement_name').get(0)" /></span>
											</s:if>

										</div>
									</div>
											<div class="form-group">
										<label class="col-md-3 control-label">cell 
 						<%-- 	<sup><font color="red">*</font></sup>  --%>
										</label>
										<div class="col-md-3">

											<input type="text" name="C_cell"
												id="advertisement_name" class="form-control"
												value="<s:property value="advetisement.advertisement_name" />"
												maxlength="50">
											<s:if test="fieldErrors.get('advertisement_name').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('advertisement_name').get(0)" /></span>
											</s:if>

										</div>
									</div>
											<div class="form-group">
										<label class="col-md-3 control-label">Contact person name 
 					<%-- 		<sup><font color="red">*</font></sup>  --%>
										</label>
										<div class="col-md-3">

											<input type="text" name="Contact_person_name"
												id="advertisement_name" class="form-control"
												value="<s:property value="advetisement.advertisement_name" />"
												maxlength="50">
											<s:if test="fieldErrors.get('advertisement_name').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('advertisement_name').get(0)" /></span>
											</s:if>

										</div>
									</div>
												<div class="form-group">
										<label class="col-md-3 control-label">Contact person Email 
 							<%-- <sup><font color="red">*</font></sup>  --%>
										</label>
										<div class="col-md-3">

											<input type="text" name="Contact_person_Email"
												id="advertisement_name" class="form-control"
												value="<s:property value="advetisement.advertisement_name" />"
												maxlength="50">
											<s:if test="fieldErrors.get('advertisement_name').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('advertisement_name').get(0)" /></span>
											</s:if>

										</div>
									</div>
												<div class="form-group">
										<label class="col-md-3 control-label">Contact person Phone 
 					<%-- 		<sup><font color="red">*</font></sup>  --%>
										</label>
										<div class="col-md-3">

											<input type="text" name="Contact_person_Phone"
												id="advertisement_name" class="form-control"
												value="<s:property value="advetisement.advertisement_name" />"
												maxlength="50">
											<s:if test="fieldErrors.get('advertisement_name').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('advertisement_name').get(0)" /></span>
											</s:if>

										</div>
									</div>
								
									
										 <div class="form-group">
							  <label class="control-label col-md-3">Customer Create Date:</label>
								<div class="col-md-3">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" data-date-end-date="0d"> <!-- data-date-start-date="+0d" -->
								<input id="startdate" class="form-control" type="text" readonly="" size="16" name="startdate"
								value="<s:property value='rateMaster.effectiveStartDate' />"
								>
								<span class="input-group-btn">
								<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
								</button>
								</span>
								<script>
// 										var curr_date=new Date().toJSON().slice(0,10);
//                                         curr_date=curr_date.split("-");
//                                         curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
//                                         var dtval=document.getElementById('startdate').value;	
                                        
//                                         if(dtval==''){
//                                         $('#startdate').val(curr_date);
//                                         }
								</script>
								</div>
								</div></div>
									
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

