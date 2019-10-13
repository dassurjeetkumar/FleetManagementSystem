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
		document.forms[0].action = 'viewAdMannagement.action';
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
	/* function uploadFile()
	{
		var photo = document.getElementById("photo");
		
	    // the file is the first element in the files property
	    var file = photo.files[0];
	    var f1=document.forms[0].myFile.value
        alert(f1);
        alert("File name: " + file.fileName);
        alert("File name: " + file.fileSize); 
		/*  document.forms[0].action = "uploadFile.action";
		 document.forms[0].submit(); 
	} */
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
	    /*    var prevType1="<s:property value='ticketbagmaster.orgchart.org_chart_id'/>";
			//alert(prevType);
			 if(prevType1!=""){
				 document.getElementById("orgName"+prevType1).selected=true;
			 }    */
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
	           url: '<%=request.getContextPath()%>/findPlatformNameAction?bayid='+strUser,
	           success: function(result) {
	        	   
	               document.getElementById('platformNo').innerHTML=result;
	           }
	       });
	    /*    var prevType1="<s:property value='ticketbagmaster.orgchart.org_chart_id'/>";
			//alert(prevType);
			 if(prevType1!=""){
				 document.getElementById("orgName"+prevType1).selected=true;
			 }    */
}
function getAdvertisementTypes()
{
	 var len= document.getElementById('advertisement_type_id').options.length;
	    
	 if(len<=1 ) {
       $.ajax({
           type: "post",
           async:false,
           url: '<%=request.getContextPath()%>/findAllAdvertisementTypesAction',
           async:false,
           success: function(result) {
        	  
               document.getElementById('advertisement_type_id').innerHTML=result;
           }
       });
        var prevType="<s:property value='advetisement.advetisetype.advertisement_type_id'/>"
		 if(prevType!=""){
			
			 //document.getElementById("orgType"+prevType).selected=true;
			 document.getElementById("advertisement_type_id").value=prevType;
			 var orgtypeid = document.getElementById("advertisement_type_id").value;
			 var orgtype = advertisement_type_id.options[advertisement_type_id.selectedIndex].text;
		
			document.getElementById('select2-chosen-2').innerHTML=orgtype;
		 }
		 
	 } 	  
}
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
								<i class="fa fa-gift"></i>Create Ad
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

							<form action="saveAd.action" class="form-horizontal"
								method="post" enctype="multipart/form-data">
								<div class="form-body">
									<h1 class="intro">
										<s:property value="msg" />
									</h1>
									<div class="form-group">
										<label class="col-md-3 control-label">Advertisement
											Name<sup><font color="red">*</font></sup>
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
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Customer Name<font
											color="red">*</font></label>
										<div class="col-md-4">
											<div class="input-group">

												<select class="select2_category form-control"
													name="advetisement.cust.id" id="cust_id"
													style="width: 200px;">
													<option value="0">select</option>
												</select>


												<s:if test="fieldErrors.get('cust_id').size() > 0">
													<span style="color: red;"><s:property
															value="fieldErrors.get('cust_id').get(0)" /></span>
												</s:if>

												<%--  <script>
															
															 </script> --%>

											</div>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">Start Date<sup><font
												color="red">*</font></sup></label>
										<div class="col-md-3">
											<div class="input-group input-medium date date-picker"
												data-date-format="dd-mm-yyyy" data-date-start-date="+0d">
												<input type="text" class="form-control" readonly
													name="advetisement.start_date" id="startdate"> <span
													class="input-group-btn"> <s:if
														test="fieldErrors.get('startdate').size() > 0">
														<span style="color: red;"><s:property
																value="fieldErrors.get('startdate').get(0)" /></span>
													</s:if>
													<button class="btn default" type="button">
														<i class="fa fa-calendar"></i>
													</button>
												</span>
												<script>
										var curr_date=new Date().toJSON().slice(0,10);
                                        curr_date=curr_date.split("-");
                                        curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
                                        $('#startdate').val(curr_date);
										</script>

											</div>

										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">End Date </label>
										<div class="col-md-3">
											<div class="input-group input-medium date date-picker"
												data-date-format="dd-mm-yyyy" data-date-start-date="+0d">
												<input type="text" class="form-control" readonly
													name="advetisement.end_date" id="enddate"> <span
													class="input-group-btn"> <s:if
														test="fieldErrors.get('enddate').size() > 0">
														<span style="color: red;"><s:property
																value="fieldErrors.get('enddate').get(0)" /></span>
													</s:if>
													<button class="btn default" type="button">
														<i class="fa fa-calendar"></i>
													</button>
												</span>
												<script>
										var curr_date=new Date().toJSON().slice(0,10);
                                        curr_date=curr_date.split("-");
                                        curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
                                    //   $('#enddate').val(curr_date);
										</script>

											</div>

										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Advertisement
											Type<font color="red">*</font>
										</label>
										<div class="col-md-4">
											<div class="input-group">
												<select class="select2_category form-control"
													name="advetisement.advetisetype.advertisement_type_id "
													id="advertisement_type_id" style="width: 200px;">
													<option value="0">Select</option>

												</select>


												<s:if
													test="fieldErrors.get('advertisement_type_id').size() > 0">
													<span style="color: red;"><s:property
															value="fieldErrors.get('advertisement_type_id').get(0)" /></span>
												</s:if>


											</div>
											<script>
													
													</script>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Upload your file<font
											color="red">*</font></label>
										<div class="col-md-4">
											<div class="input-group">
												<!-- <label for="myFile">Upload your file</label> -->
												<%-- <s:file name="myFile" label="Choose File" size="40" /> --%>
												<input type="file" name="myFile" id="myfile" />
												<s:if test="fieldErrors.get('myfile').size() > 0">
													<span style="color: red;"><s:property
															value="fieldErrors.get('myfile').get(0)" /></span>
												</s:if>
												<!-- <button type="button" class="btn default"
												style="position: absolute; left:300px;bottom:8px;" onclick="uploadFile()">Upload</button> -->
											</div>
										</div>

									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Duration<sup><font
												color="red">*</font></sup>
										</label>
										<div class="col-md-3">

											<input type="text" name="advetisement.duration" id="duration"
												class="form-control"
												value="<s:property value="advetisement.duration" />"
												maxlength="3">
											<s:if test="fieldErrors.get('duration').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('duration').get(0)" /></span>
											</s:if>



										</div>
									</div>

									<script>
												
															var duration= "<s:property value="advetisement.duration"/>";
															if (duration != undefined) {
																if (parseInt(duration) == 0
																		|| duration == "0") {
																	document
																			.getElementById("duration").value= "";
																} 
															} 
															
															
									</script>

									<div class="form-group">
										<label class="col-md-3 control-label">Frequency<sup><font
												color="red">*</font></sup></label>
										<div class="col-md-3">

											<input type="text" name="advetisement.frequency"
												id="frequency" class="form-control"
												value="<s:property value="advetisement.frequency" />"
												maxlength="2">
											<s:if test="fieldErrors.get('frequency').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('frequency').get(0)" /></span>
											</s:if>



										</div>
									</div>
									<script>
												
															var frequency= "<s:property value="advetisement.frequency"/>";
															if (frequency != undefined) {
																if (parseInt(frequency) == 0
																		|| frequency == "0") {
																	document
																			.getElementById("frequency").value= "";
																} 	
																} 
									</script>
									<div class="form-group">
										<label class="control-label col-md-3">Display in Peak
											Hour</label>
										<div class="col-md-9">
											<div class="radio-list">
												<label> <input type="radio"
													name="advetisement.display_peak_hour" value="1"
													checked="checked" /> Yes
												</label> <label> <input type="radio"
													name="advetisement.display_peak_hour" value="0" /> No
												</label>

											</div>
										</div>
									</div>

									<%-- <div class="form-group">
										<label class="col-md-3 control-label">Display in Peak
											Hour </label>
										<div class="col-md-3">

											<input type="text" name="advetisement.display_peak_hour"
												id="display_peak_hour" class="form-control"
												value="<s:property value="advetisement.display_peak_hour" />"
												maxlength="11">
											<s:if test="fieldErrors.get('display_peak_hour').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('display_peak_hour').get(0)" /></span>
											</s:if>



										</div>
									</div> --%>
									<div class="form-group">
										<label class="col-md-3 control-label">Amount<sup><font
												color="red">*</font></sup> </label>
										<div class="col-md-3">

											<input type="text" name="advetisement.amount" id="amount"
												class="form-control"
												value="<s:property value="advetisement.amount" />"
												maxlength="10">
											<s:if test="fieldErrors.get('amount').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('amount').get(0)" /></span>
											</s:if>



										</div>
									</div>
									<%-- 	<div class="form-group">
										<label class="col-md-3 control-label">Airtime </label>
										<div class="col-md-3">

										<div class="input-group date form_datetime">
											<input type="text" size="16" readonly name="advetisement.airtime"
												id="startdate" class="form-control"
												value='<s:property value="advetisement.airtime"/>'> <span
												class="input-group-btn">
												
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
											<script>
										var curr_date=new Date().toJSON().slice(0,10);
                                        curr_date=curr_date.split("-");
                                        curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
                                        $('#startdate ').val(curr_date);
										</script>
										</div>
									</div> --%>

									<div class="form-group">
										<label class="col-md-3 control-label">Airtime<sup><font
												color="red">*</font></sup>
										</label>
										<div class="col-md-3">

											<input type="text" name="advetisement.airtime" id="airtime"
												class="form-control"
												value="<s:property value="advetisement.airtime" />"
												maxlength="4">
											<s:if test="fieldErrors.get('airtime').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('airtime').get(0)" /></span>
											</s:if>



										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Remarks</label>
										<div class="col-md-4">
											<div class="input-group">
<textarea name="advetisement.notes" id="notes" class="form-control" maxlength="100"><s:property value="advetisement.notes" /></textarea>
												<s:if test="fieldErrors.get('notes').size() > 0">
													<span style="color: red;"><s:property
															value="fieldErrors.get('notes').get(0)" /></span>
												</s:if>
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

