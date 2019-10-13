<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
<link rel="stylesheet"	href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
 <script src="assets/global/plugins/jquery-1.11.0.min.js"
	type="text/javascript"></script>
<style type="text/css">
.help-block {
	color: red;
}
.help-block,ul,li {
	list-style: none;
}

</style>
<script>
function getVehicle(depotID)
{
// 	alert("in getVehicle");
	$('#select2-chosen-3').html("Select");
	var depotid=document.getElementById('depot_id').value;
// 	var vehicle_no=document.getElementById('vehicleid').value;
// 	alert("depotid=="+depotid);
	 if(depotid!=0) {
		 $("#msg").html("Please wait...");
   	$.ajax({
	   type: "POST",
       url: '<%=request.getContextPath()%>/getDepotVehicleList?val='+depotid,
       success: function(result) {
    	   $("#msg").html("");
    	   document.getElementById('vehiclelist').innerHTML=result;
       }
   });
}
}

//var value=false;
function save(){
//  alert("submit");
 var valu=Validate();
//  alert(valu);
      if(valu==true){
			document.f1.action = 'savestudentpassdetails.action';
   		 	document.f1.submit();
}
}
   		 /*}
		
	}*/
        

	var i=0;
	 var count=0;
	 var value=true;
	 function Validate(){

		 var source=$("#sourcelists").val();
		 var destination=$("#destinationlists").val();
		 var via=$("#vialists").val();
		 var via2=$("#changeoverlists").val();
		 var address=$("#student_adds").val();
		 //alert(source);
		 if(source==0)
		 {
			alert("Please Select Source Stop");
			return false;
		 }
		 if(destination==0)
		 {
			alert("Please Select Destination Stop");
			return false;
		 }
		 if(via==0)
		 {
			alert("Please Select Via Stop");
			return false;
		 }
		 if(via2==0)
		 {
			alert("Please Select Change Over Stop");
			return false;
		 }
		 if(address==0)
		 {
			alert("Please Insert Student Address");
			return false;
		 }


				 return value;
		}

	 function UploadImage()
	 { 
	 	
	 	// var val=document.getElementById('edit-route-number-int').value;
//	  	 alert("Hi="+val);
//	  	var oruteid=document.getElementById("routeid0").value;
//	  	var busno=document.getElementById("busno").value;
	 // alert("Hello=="+routeid+"=="+routenumber+"++++"+originid);
	 popupWindow =window.open('Reports/UploadImage.jsp',"_blank","directories=no, status=no, menubar=no, scrollbars=yes, resizable=no,width=600, height=280,top=200,left=200");
	 }
	
</script>

<input type="hidden" name="id" id="id" />
<div class="page-content-wrapper">
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "getDailyScheduleMapping.action");
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
	<%/* if (access.equalsIgnoreCase("Y")){ */%>
		<%-- %if (edit.equalsIgnoreCase("Y")){ --%>
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
					EDIT STUDENT DETAILS
				</h3>
			</div>
		</div>
		<div class="row">
	
			<div class="col-md-12">
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i> Edit Student Details
						</div>
					</div>
					<div class="portlet-body form">
						<form action="savestudentpassdetails.action" class="form-horizontal form-row-seperated" method="post" name="f1"  ">
<!--                      <form action="saveEditScheduleMappingActionWebServiceCall" class="form-horizontal form-row-seperated" method="post">  -->
							<b>
								<font color="green"> <s:actionmessage/></font>
								<span id="errorMsg" style="color:red;word-wrap: break-word;" ><s:actionerror/></span>
							</b>
							<div class="form-body">
							
								<%-- <div class="form-group">
									<label class="control-label col-md-3">Schedule No.</label>
									<div class="col-md-3">
										<input type="text" class="form-control" readonly="" name="scheduleMapDetails.schedule_no"
												value='<s:property value="scheduleMapDetails.schedule_no"/>'>
									</div>
								</div> --%>
								
								<div class="form-group">
								
<%-- 								<input type="hidden" id="division1"  name="division1" value='<s:property value="division1"/>'> --%>
<%--                             <input type="hidden" id="depot1" name="depot1" value='<s:property value="depot1"/>'> --%>
<%--                             <input type="hidden" id="startdate1" name="startdate1" value='<s:property value="startdate1"/>'> --%>
								
<%-- 								<input type="hidden" id="schedule_id" name="schedule_id" value='<s:property value="schedule_id"/>'> --%>
<%-- 								<input type="hidden" name="shift_type_id" value='<s:property value="schedulemp.shift_type_id"/>'> --%>
								<input type="hidden" name="studentno" id="studentno" value='<s:property value="studentno"/>'>
<%-- 								<input type="hidden" name="form_four_id" id="form_four_id" value='<s:property value="schedulemp.form_four_id"/>'> --%>
								
									<div class="form-group">
										<label class="col-md-3 control-label">Source Stop<font
											color="red">*</font></label>
										<div class="col-md-3">
											<s:select list="sourcelist" id="sourcelists"
												name="sourcelists" cssClass="select2_category form-control"
												 headerKey="0" headerValue="Select"></s:select>

										</div>
										<div class="col-md-4">
											<input type="text" class="form-control"  maxlength="10" readonly="readonly" id="source_no"
													name="source_no"	value='<s:property value="source_no"/>'>

										</div>
									</div>
								
									<div class="form-group">
										<label class="col-md-3 control-label">Destination Stop<font
											color="red">*</font></label>
										<div class="col-md-3">
											<s:select list="destinationlist" id="destinationlists"
												name="destinationlists" cssClass="select2_category form-control"
												 headerKey="0" headerValue="Select"></s:select>

										</div>
										<div class="col-md-4">
											<input type="text" class="form-control"  maxlength="10" readonly="readonly" id="destination_no"
													name="destination_no"	value='<s:property value="destination_no"/>'>

										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Via Stop<font
											color="red">*</font></label>
										<div class="col-md-3">
											<s:select list="vialist" id="vialists"
												name="vialists" cssClass="select2_category form-control"
												 headerKey="0" headerValue="Select"></s:select>

										</div>
										<div class="col-md-4">
											<input type="text" class="form-control"  maxlength="10" readonly="readonly" id="via_no"
													name="via_no"	value='<s:property value="via_no"/>'>

										</div>
									</div>
								<div class="form-group">
										<label class="col-md-3 control-label">ChangeOver Stop<font
											color="red">*</font></label>
										<div class="col-md-3">
											<s:select list="changeoverlist" id="changeoverlists"
												name="changeoverlists" cssClass="select2_category form-control"
												 headerKey="0" headerValue="Select"></s:select>

										</div>
										<div class="col-md-4">
											<input type="text" class="form-control"  maxlength="10" readonly="readonly" id="changestop_no"
													name="changestop_no"	value='<s:property value="changestop_no"/>'>

										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Student Address<font
											color="red">*</font></label>
										<div class="col-md-3">
											<input type="text" class="form-control"  maxlength="100" id="student_adds"
													name="student_adds"	value='NA' >

										</div>
										<div class="col-md-4">
											<input type="text" class="form-control"  maxlength="10" readonly="readonly" id="student_adds1"
													name="student_adds1"	value='<s:property value="student_adds"/>'>

										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">School Name<font
											color="red">*</font></label>
										<div class="col-md-3">
											<input type="text" class="form-control"  maxlength="100" id="school_name"
													name="school_name"	value='<s:property value="school_name"/>' >

										</div>
										
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Student Caste<font
											color="red">*</font></label>
										<div class="col-md-3">
											<input type="text" class="form-control"  maxlength="100" id="student_caste"
													name="student_caste"	value='<s:property value="student_caste"/>' >

										</div>
										
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Gender<font
											color="red">*</font></label>
										<div class="col-md-3">
											<input type="text" class="form-control"  maxlength="100" id="gender"
													name="gender"	value='<s:property value="gender"/>' >

										</div>
										
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Student Name<font
											color="red">*</font></label>
										<div class="col-md-3">
											<input type="text" class="form-control"  maxlength="100" id="student_name"
													name="student_name"	value='<s:property value="student_name"/>' >

										</div>
										
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Mobile No<font
											color="red">*</font></label>
										<div class="col-md-3">
											<input type="text" class="form-control"  maxlength="10" id="mobile_no"
													name="mobile_no"	value='<s:property value="mobile_no"/>' >

										</div>
										
									</div>
									
									<!-- <div class="form-group">
										<label class="col-md-3 control-label">Photo Path<font
											color="red">*</font></label>
											<a href="javascript:UploadImage()">Upload Image</a>
										<div class="col-md-3">
											<input type="text"   id="photo" name="photo" />

										</div>
										
									</div> -->
					
									
									
									<!-- To get depot name in hidden form -->
<!-- 							<div class="form-group"> -->
<!-- 										<label class="control-label col-md-3">Depot Name</label> -->
<!-- 										<div class="col-md-3"> -->
													
													
<!-- 										</div> -->
<!-- 									</div> -->
									
	
									

							
							
									<div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9">
											<button type="button" class="btn blue" onclick="save()" >Save</button>
											<button type="button" class="btn default"	onclick="callCancell()">Cancel</button>
										</div>
									</div>
								</div>
							</div>
						</form>
						
					
						
						<%/* }else{ */ %>
<%-- <%@ include file="/pages/admin/error.jsp" %> --%>
<%/* } */ %>									
										
<%/* }else{ */%>
 


<%-- <%@ include file="/pages/admin/error.jsp" %> --%>


<%/* } */%>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	function callCancell() {
// 		var dd1=$("#startdate1").val();
// 		var division=$("#division1").val();
// 		var depot=$("#depot1").val();
// 		alert(dd1+division+depot);
		
		window.location.href = 'StudentpassdetailsAction.action';
// 		window.location.href = 'getDailyScheduleMapping.action';

		
	}
</script>
<script>
$(document).ready(function() {
	
	
	   var w=$('#errorMsg span').html();
	   //alert(w);
	    w=w.replace(/@/g,"<br>");

	   $('#errorMsg').html(''+w+'');
  
});
</script>
