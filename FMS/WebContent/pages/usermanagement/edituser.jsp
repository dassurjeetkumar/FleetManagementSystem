	
	<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>


<!DOCTYPE body PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body onload="getDetails(), getUnit()">
<script>
function getEditDetail(){
	$('#empid').prop('disabled', false);
	$('#user_type_id').prop('disabled', false);
	$('#userloginname').prop('disabled', false);
	//$('#userloginname').prop('disabled', false);
	document.frm.action="updateUserDetails.action";
	document.frm.submit();
}
function getUnit(){
	//alert("Bye");
	var usertypeid = document.getElementById("user_type_id").value;
	//var org='orgType';
	var user_type = user_type_id.options[user_type_id.selectedIndex].text;
	
		
		 if((user_type == 'Select') || (user_type == 'Director') || (user_type == 'Guest')) 
			 {
		
			 $("#orgtypeforempid").hide();
			 $("#orgchartforempid").hide();
			 $("#designationid").hide();
			 $("#orgtypefordirectid").show();
			 $("#orgchartfordirectorid").show();
			 
     $.ajax({
         type: "post",
         async:false,
         url: '<%=request.getContextPath()%>/findAllOrgTypeDetailsAction',
         success: function(result) {
      	     document.getElementById('org_type_id').innerHTML=result;
         }
     });
     
     var prevType="<s:property value='userdetails.orgnizationType.org_type_id'/>";
		 if(prevType!=""){
			 document.getElementById("org_type_id").value=prevType;
			 var orgtypeid = document.getElementById("org_type_id").value;
			 var orgtype = org_type_id.options[org_type_id.selectedIndex].text;
			// alert("orgType"+orgtype);
			 document.getElementById('select2-chosen-5').innerHTML=orgtype;
		 }
     
	 }
	

//alert("kk"+org+prevType);
	 if(document.getElementById("orgType"+prevType).selected==true)
		{
			 $.ajax({
		           type: "post",
		           url: '<%=request.getContextPath()%>/findUnitNameActionForUser?orgid='+prevType,
		        		   async:false,
		           success: function(result) {
		               document.getElementById('org_chart_id').innerHTML=result;
		           }
		       });
		}
		  var prevType1="<s:property value='userdetails.orgchartdetails.org_chart_id'/>";
		 // alert("1"+prevType1);
		 	 if(prevType1!=""){
				 //document.getElementById("orgName"+prevType1).selected=true;
				 document.getElementById("org_chart_id").value=prevType1;
				 var orgchartid = document.getElementById("org_chart_id").value;
				 var orgname = org_chart_id.options[org_chart_id.selectedIndex].text;
				 document.getElementById('select2-chosen-6').innerHTML=orgname;
				 
			 }  
			 

}
function getOrgType()
{
	
 var len= document.getElementById('org_type_id').options.length;
 	 if(len<=1 ) {
       $.ajax({
           type: "post",
           url: '<%=request.getContextPath()%>/findAllOrgTypeDetailsAction',
           success: function(result) {
               document.getElementById('org_type_id').innerHTML=result;
           }
       });
     
	 }
	
	
}
function getUnitNames()
{
	 $("#orgtypeforempid").hide();
	 $("#orgchartforempid").hide();
	 $("#designationid").hide();
	 $("#orgtypefordirectid").show();
	 $("#orgchartfordirectorid").show();
	var e = document.getElementById("org_type_id");
	var strUser = e.options[e.selectedIndex].value;
	//var len= document.getElementById('org_chart_id').options.length;
	       $.ajax({
	           type: "post",
	           url: '<%=request.getContextPath()%>/findUnitNameActionForUser?orgid='+strUser,
	           success: function(result) {
	        	   document.getElementById('select2-chosen-6').innerHTML="select";
	        	  document.getElementById('org_chart_id').innerHTML=result;
	           }
	       });
		
}
function cancelform(){
	document.frm.action="ShowUserList.action";
	document.frm.submit();
}
/* function getdisabled(){
	var user_type=document.frm.user_type.value;
	if((user_type == 'Director') || (user_type == 'Guest'))
		{
		document.frm.employee_id.disabled =true;
		}else{
			document.frm.employee_id.disabled =false;
		} 
}*/
function getDetails(){
	 $("#agentid").hide();
var usertypeid = document.getElementById("user_type_id").value;
	
	var user_type = user_type_id.options[user_type_id.selectedIndex].text;
	
	
	 if((user_type == 'Director') || (user_type == 'Guest'))
		{
		 $("#orgtypeforempid").hide();
		 $("#orgchartforempid").hide();
		 $("#designationid").hide();
		 $("#orgtypefordirectid").show();
		 $("#orgchartfordirectorid").show();
		//document.getElementById('designationid').style.visibility = 'hidden';
		 $("#designationid").hide();
		}else{
			
			//document.getElementById('designationid').style.visibility = 'visible';
			 $("#designationid").show();
		} 
	 if((user_type == 'Employee') || (user_type == 'Guest'))
	 {
	 $("#agentid").show();
	 }
	 
	 var empid=document.getElementById("empid").value;
	 var userid=document.getElementById("user_id").value;
	
	if(empid!=0){
		   $("#orgtypefordirectid").hide();
			 $("#orgchartfordirectorid").hide();
			 $("#orgtypeforempid").show();
			 $("#orgchartforempid").show();
			 $("#designationid").show();
	  $.ajax({
         type: "POST",
         url: "getDesignationFromUser.action?userid="+userid,
         success: function(response){
         	var arr = response.split(',');
            var designationdetails=arr[0].split('@');
         	  if(user_type == 'Employee')
      		{
         		/*  var result=arr[1];
           		var orgunittypedetails=result.split('@');
           		// $('#orgunitid').replaceWith(orgunittypedetails[1]);
           		var result1='<div id="orgunitid" > <input type="text" class="form-control"  maxlength="50" readonly="readonly"   value='+orgunittypedetails[1]+' id="org_type_id"  > </div>';
          		 $('#orgunitid').replaceWith(result1);
           		
           		document.frm.org_type_id_emp.value=orgunittypedetails[0];
           		var result1=arr[2];
           		var orgchartdetails=result1.split('@');
           		// $('#orgchartid').replaceWith(orgchartdetails[1]);
           		 var result4='<div id="orgunitid" > <input type="text" class="form-control"  maxlength="50"  readonly="readonly"   value='+orgchartdetails[1]+' id="org_chart_id"  > </div>';
          		 $('#orgchartid').replaceWith(result4);
           		document.frm.org_chart_id_emp.value=orgchartdetails[0]; */
         		  $("#orgtypefordirectid").hide();
          		 $("#orgchartfordirectorid").hide();
          		 $("#orgtypeforempid").show();
          		 $("#orgchartforempid").show();
          		 $("#designationid").show();
         		 var result=arr[1];
           		var orgunittypedetails=result.split('@');
           		document.getElementById("org_type_name").value=orgunittypedetails[1];
            	document.frm.org_type_id_emp.value=orgunittypedetails[0];
           		var result1=arr[2];
           		var orgchartdetails=result1.split('@');
           		document.getElementById("org_chart_name").value=orgchartdetails[1];
             	document.frm.org_chart_id_emp.value=orgchartdetails[0];
      		}else{
      			
      		}  
         	 document.frm.designation_type_id.value=designationdetails[0];
           	 document.getElementById('designation').value=(designationdetails[1]);
         	 
         	 
         	 
         }
	  }); 
   }
	 
	 
}

function getAdditonPageAccess(){
	var useriddetails=document.getElementById("user_id").value;
	var roleid=document.getElementById("role_id").value;
	var rolename = role_id.options[role_id.selectedIndex].text;
	var userloginname=document.getElementById("userloginname").value
	
	//document.forms[0].action='showUserRolePageAccess.action';
	//document.forms[0].submit();
	//alert("userloginname-----"+userloginname);
	document.getElementById("useriddetails").value = useriddetails;
	document.getElementById("roleid").value = roleid;
	document.getElementById("userloginnamedetails").value = userloginname;
	//alert("test----------"+document.getElementById("userloginnamedetails").value);
	document.getElementById("rolename").value = rolename;
	document.getElementById("form1").submit();
}
</script>

<div class="page-content-wrapper">
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ShowUserList.action");
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
			<%if (edit.equalsIgnoreCase("Y")){%>
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						USER  <small></small>
					</h3>
				
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
	
<div class="tab-content">
							<div class="tab-pane active" id="tab_0">


							<div class="portlet box grey-cascade">
									<div class="portlet-title">
										<div class="caption">
											<i class="fa fa-gift"></i>Edit User
										</div>
										
									</div>
									<div class="portlet-body form">
										<!-- BEGIN FORM-->
											<s:if test="hasActionErrors()">
					<font color="Red"> <s:actionerror />
					</font>
					</s:if>
					
					<s:if test="hasActionMessages()">
					<font color="green"> <s:actionmessage />
					
				</font>
					</s:if>
										<form action="#" class="form-horizontal" name="frm" method="post">
										
											<div class="form-body">
												<div class="form-group">
												
													<label class="col-md-3 control-label">User Type<font color="red">*</font>:</label>
													<div class="col-md-4">
														<input type="hidden"   name="userdetails.user_id"  value='<s:property value="userdetails.user_id" />'  id="user_id" >
														<input type="hidden"   name="userdetails.oldGroupID"  value='<s:property value="userdetails.oldGroupID" />'  id="oldGroupID" >
													<%--  <select class="form-control" id="user_type" name="userdetails.user_type"  >
													 <option  id="select" value="0" >select</option>
														<option  id="Employee" value="Employee" >Employee</option>
														<option id="Director" value="Director">Director</option>
														<option id="Guest" value="Guest">Guest</option>
													</select>   --%>
													<%-- <script>
														var user_type = "<s:property value='userdetails.user_type'/>";
															if (user_type != undefined) {
																if (user_type == "Employee") {
																	document.getElementById("Employee").selected = true;
																} else if (user_type == "Director"){
																	document.getElementById("Director").selected = true;
																} else if (user_type == "Guest"){
																	document.getElementById("Guest").selected = true;
																}else{
																	document.getElementById("select").selected = true;
																}
															}
															
															$('#user_type').prop('disabled', true);
															
															</script>
															 --%>
													
													
														<s:select list="usertypelist" id="user_type_id" name="userdetails.usertypedetails.user_type_id" 
		cssClass="select2_category form-control" headerKey="0" headerValue="Select" ></s:select>
														<script>
													$('#user_type_id').prop('disabled', true);
													</script>
													
											</div>
												</div>
												
												
												<div class="form-group">
													<label class="col-md-3 control-label">Employee Name</label>
													<div class="col-md-4">
														<s:select list="employeelistforedit" id="empid" name="userdetails.empid" 
		cssClass="select2_category form-control" headerKey="0" headerValue="Select" ></s:select>
													<script>
													$('#empid').prop('disabled', true);
													</script>
													</div>
												</div>
												
												<div class="form-group">
													<label class="col-md-3 control-label">User Login Id<font color="red">*</font></label>
													<div class="col-md-4">
														<input type="text" class="form-control" name="userdetails.userloginname"  value='<s:property value="userdetails.userloginname" />' id="userloginname"  maxlength="50">
															<s:if test="fieldErrors.get('userloginname').size() > 0">
					     								<span style="color:red;"><s:property value="fieldErrors.get('userloginname').get(0)" /></span>
														</s:if>
														<script>
													$('#userloginname').prop('disabled', true);
													</script>
													</div>
												</div>
												
													<div class="form-group" id="agentid">
													<label class="col-md-3 control-label">Call Agent Id</label>
													<div class="col-md-4">
														<input type="text" class="form-control" name="userdetails.agent_id"  value='<s:property value="userdetails.agent_id" />' id="agent_id"  maxlength="50">
															<s:if test="fieldErrors.get('agent_id').size() > 0">
					     								<span style="color:red;"><s:property value="fieldErrors.get('agent_id').get(0)" /></span>
														</s:if>
													</div>
												</div>
												
												<div class="form-group">
													<label class="col-md-3 control-label">Password<font color="red">*</font></label>
													<div class="col-md-4">
														<input type="password" class="form-control"  name="userdetails.password"  id="password" value='<s:property value="userdetails.password" />' maxlength="20" autocomplete="off">
															<s:if test="fieldErrors.get('password').size() > 0">
					     								<span style="color:red;"><s:property value="fieldErrors.get('password').get(0)" /></span>
														</s:if>
													</div>
												</div>
												
												<div class="form-group">
													<label class="col-md-3 control-label">Role<font color="red">*</font></label>
													<div class="col-md-4">
														<s:select list="rolelist" id="role_id" name="userdetails.role.role_id" 
		cssClass="select2_category form-control" headerKey="0" headerValue="Select"></s:select>
															<s:if test="fieldErrors.get('role_id').size() > 0">
					     								<span style="color:red;"><s:property value="fieldErrors.get('role_id').get(0)" /></span>
														</s:if>
													</div>
												</div>
												
													<div class="form-group">
													<label class="col-md-3 control-label">User Group</label>
													<div class="col-md-4">
														<s:select list="grouplist" id="group_id" name="userdetails.usergroupid" 
		cssClass="select2_category form-control" headerKey="0" headerValue="Select"></s:select>
															
													</div>
												</div>
												<%-- <div class="form-group">
													<label class="col-md-3 control-label">Working Designation<font color="red">*</font></label>
													<div class="col-md-4">
														<s:select list="designationlist" id="designation_type_id" name="userdetails.designationtypeid.designation_type_id" 
		cssClass="select2_category form-control" headerKey="0" headerValue="Select"></s:select>
															<s:if test="fieldErrors.get('designation_type_id').size() > 0">
					     								<span style="color:red;"><s:property value="fieldErrors.get('designation_type_id').get(0)" /></span>
														</s:if>
													</div>
												</div> --%>
												
												
												<!-- <div class="form-group" id="designationid">
													<label class="col-md-3 control-label">Working Designation<font color="red">*</font></label>
													<div class="col-md-4">
													<h5  id="designation"></h5>
														
															
													</div>
														<input type="hidden" name="designation_type_id" id="designation_type_id" />
												</div> -->
												<div class="form-group" id="designationid">
													<label class="col-md-3 control-label">Working Designation</label>
													<div class="col-md-4">
														<input type="text" class="form-control" name=""    readonly="readonly"  value="" id="designation"  >
														<input type="hidden" name="designation_type_id" id="designation_type_id" />
															
													</div>
												</div>
												
												<%-- <div class="form-group">
													<label class="col-md-3 control-label">Orgnization Unit<font color="red">*</font></label>
													 <input type="hidden" name="org_type_id_emp" id="org_type_id_emp" />
													<div class="col-md-4" id="orgunitid">
														
		
										                 
										                 <select Class="select2_category form-control" name="userdetails.orgnizationType.org_type_id" id="org_type_id" onclick="getOrgType()"  onchange="getUnitNames()">
												        <option value="<s:property value="userdetails.orgnizationType.org_type_id" />">
												         <s:property value="userdetails.orgnizationType.orgType" />
										                 </select>
										                 
															<s:if test="fieldErrors.get('org_type_id').size() > 0">
					     								<span style="color:red;"><s:property value="fieldErrors.get('org_type_id').get(0)" /></span>
														</s:if>
													</div>
													 <div class="col-md-4" >
													<div  id="orgunitid">
													
														<select Class="select2_category form-control"    name="userdetails.orgnizationType.org_type_id" id="org_type_id"  onchange="getUnitNames()">
												         <option value="0" >select</option>
										                 </select>
										              
										             </div>  
										              <div class="form-group">
										                <s:if test="fieldErrors.get('org_type_id').size() > 0">
					     								<span style="color:red;"><s:property value="fieldErrors.get('org_type_id').get(0)" /></span>
														</s:if> 
														</div>
													</div>
												</div>
												
												
												<div class="form-group">
										<label class="col-md-3 control-label"> Orgnization Name<font color="red">*</font></label>
										 <input type="hidden" name="org_chart_id_emp" id="org_chart_id_emp" />
													<div class="col-md-4" >
												<div id="orgchartid">
														 <select Class="select2_category form-control" name="userdetails.orgchartdetails.org_chart_id" id="org_chart_id">
												         <option value="0" >select</option>
										                 </select>
													</div>
													    <div class="form-group">
															<s:if test="fieldErrors.get('org_chart_id').size() > 0">
     								                                   <span style="color:red;"><s:property value="fieldErrors.get('org_chart_id').get(0)" /></span>
									                        </s:if>	 
													        </div>
												 		
													</div>
												
											</div>
										 --%>
										 
										 
										  <div class="form-group" id="orgtypeforempid">
													<label class="col-md-3 control-label">Organization Unit<font color="red">*</font></label>
													 <input type="hidden" name="org_type_id_emp" id="org_type_id_emp" />
													  <div class="col-md-4" >
													 <input type="text" class="form-control" name="org_type_name"    readonly="readonly"  value="" id="org_type_name"  >
													
														<s:if test="fieldErrors.get('org_type_id').size() > 0">
					     								<span style="color:red;"><s:property value="fieldErrors.get('org_type_id').get(0)" /></span>
														</s:if> 
														</div>
													</div>
												
												
												<div class="form-group" id="orgtypefordirectid">
													<label class="col-md-3 control-label">Organization Unit<font color="red">*</font></label>
													 <div class="col-md-4" >
														<select Class="select2_category form-control"    name="userdetails.orgnizationType.org_type_id" id="org_type_id"  onchange="getUnitNames()">
												         <option value="0" >select</option>
										                 </select>
										                <s:if test="fieldErrors.get('org_type_id').size() > 0">
					     								<span style="color:red;"><s:property value="fieldErrors.get('org_type_id').get(0)" /></span>
														</s:if> 
														</div>
													</div>
												 
												 
												 
												 <div class="form-group" id="orgchartforempid">
													<label class="col-md-3 control-label">Organization Name<font color="red">*</font></label>
													 <input type="hidden" name="org_chart_id_emp" id="org_chart_id_emp" />
														<div class="col-md-4" >
														 <input type="text" class="form-control" name="org_chart_name"    readonly="readonly"  value="" id="org_chart_name"  >
															<s:if test="fieldErrors.get('org_chart_id').size() > 0">
     								                                   <span style="color:red;"><s:property value="fieldErrors.get('org_chart_id').get(0)" /></span>
									                        </s:if>	 
													        </div>
													</div>
													
													
													 <div class="form-group" id="orgchartfordirectorid">
													<label class="col-md-3 control-label">Organization Name<font color="red">*</font></label>
													
														<div class="col-md-4" >
														 <select Class="select2_category form-control" name="userdetails.orgchartdetails.org_chart_id" id="org_chart_id">
												         <option value="0" >select</option>
										                 </select>
															<s:if test="fieldErrors.get('org_chart_id').size() > 0">
     								                                   <span style="color:red;"><s:property value="fieldErrors.get('org_chart_id').get(0)" /></span>
									                        </s:if>	 
													        </div>
													</div>
											
												 
												
												<div class="form-group">
													<label class="col-md-3 control-label">Email Id</label>
													<div class="col-md-4">
														<input type="text" class="form-control"  name="userdetails.emailid" value='<s:property value="userdetails.emailid" />' id="emailid"  maxlength="30">
														<s:if test="fieldErrors.get('emailid').size() > 0">
					     								<span style="color:red;"><s:property value="fieldErrors.get('emailid').get(0)" /></span>
														</s:if>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">Contact No.</label>
													<div class="col-md-4">
														<input type="text" class="form-control"  name="userdetails.contactno" value='<s:property value="userdetails.contactno" />' id="contactno"  maxlength="10">
															<s:if test="fieldErrors.get('contactno').size() > 0">
					     								<span style="color:red;"><s:property value="fieldErrors.get('contactno').get(0)" /></span>
														</s:if>
													</div>
												</div>
												
												
												<div class="form-group">
												<label class="col-md-3 control-label">Remarks</label>
												<div class="col-md-4">
													<textarea class="form-control"   name="userdetails.note"   id="note"  maxlength="100"  ><s:property value="userdetails.note" /></textarea>
														<s:if test="fieldErrors.get('note').size() > 0">
					     								<span style="color:red;"><s:property value="fieldErrors.get('note').get(0)" /></span>
														</s:if>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-md-3 control-label">Status:</label>
												<div class="col-md-4">
												
													 <select class="form-control" id="userdetails.status" name="userdetails.status" >
															<option id="ACTIVE"  value="ACTIVE">ACTIVE</option>
														<option id="INACTIVE" value="INACTIVE">INACTIVE</option>
													</select>   
														<script>
														var status = "<s:property value="userdetails.status"/>";
															if (status != undefined) {
																if (status == "ACTIVE" || status == "active" || status == "Active") {
																	document.getElementById("ACTIVE").selected = true;
																} else {
																	document.getElementById("INACTIVE").selected = true;
																}
															}
															</script>
											</div>
											</div>
										
											<div class="form-actions fluid">
														<div class="col-md-offset-3 col-md-9">
														<button class="btn blue" type="submit" onclick="getEditDetail()">Save</button>
														<button class="btn default" type="button" onclick="cancelform()">Cancel</button>
														<button class="btn default" type="button" onclick="getAdditonPageAccess()">Additional Page Access</button>
														</div>
											</div>
									
										</div>
										<s:token/>
										</form>
										
										<form name="form1" id="form1" action="showUserRolePageAccess.action" method="POST">
	<input type="hidden" name="useriddetails" id="useriddetails" value="" />
	<input type="hidden" name="roleid" id="roleid" value="" />
	<input type="hidden" name="userloginnamedetails" id="userloginnamedetails" value="" />
	<input type="hidden" name="rolename" id="rolename" value="" />

</form>

										
										
										<!-- END FORM-->
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