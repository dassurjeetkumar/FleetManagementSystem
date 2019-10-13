	
	<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>


<!DOCTYPE body PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <script src="assets/global/plugins/jquery-1.11.0.min.js" type="text/javascript"></script>

</head>
<body onload="getdisabled(),getUnit()" >
<script>
function getAddDetail(){
	document.frm.action="addUserDetails.action";
	document.frm.submit();
}
function cancelform(){
	document.frm.action="ShowUserList.action";
	document.frm.submit();
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
	       $.ajax({
	           type: "post",
	           url: '<%=request.getContextPath()%>/findUnitNameActionForUser?orgid='+strUser,
	           async:false,	   
	           success: function(result) {
	        	   //$("#org_chart_id").val(" ");
	        	   
						 
						 document.getElementById('select2-chosen-6').innerHTML="select";
							
					
	        	   document.getElementById('org_chart_id').innerHTML=result;
	               }
	       });
	       
	   
}

function getUnit(){
	//alert("Df");
	document.getElementById("select2-chosen-2").innerHTML="select";
	document.getElementById("select2-chosen-3").innerHTML="select";
	document.getElementById("select2-chosen-4").innerHTML="select";
	document.getElementById("select2-chosen-5").innerHTML="select";
	document.getElementById("select2-chosen-6").innerHTML="select";
	 var usertypeid = document.getElementById("user_type_id").value;
	var user_type = user_type_id.options[user_type_id.selectedIndex].text;
		 if(user_type == 'Select') 
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
			 document.getElementById('select2-chosen-5').innerHTML=orgtype;
				
		 }
     
	
	
			 }
		 
		 if((user_type == 'Director') || (user_type == 'Guest'))
		 {   
			 $("#orgtypeforempid").hide();
			 $("#orgchartforempid").hide();
			 $("#designationid").hide();
			 $("#orgtypefordirectid").show();
			 $("#orgchartfordirectorid").show();
			// alert("HII");
				
			
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
					 document.getElementById('select2-chosen-5').innerHTML=orgtype;
						
				 }
		 }
		 
	
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
		
			 if(prevType1!=""){
				 document.getElementById("org_chart_id").value=prevType1;
				 var orgchartid = document.getElementById("org_chart_id").value;
				 var orgname = org_chart_id.options[org_chart_id.selectedIndex].text;
				 document.getElementById('select2-chosen-6').innerHTML=orgname;
				 
			 }  
//}
}
   
	function getdisabled(){
		//alert("NOW");
		 $("#orgtypeforempid").hide();
		 $("#orgchartforempid").hide();
		 $("#designationid").hide();
		 $("#orgtypefordirectid").show();
		 $("#orgchartfordirectorid").show();
		 $("#agentid").hide();
		 
	var empid= document.getElementById("empid").value;
	var usertypeid = document.getElementById("user_type_id").value;
	var user_type = user_type_id.options[user_type_id.selectedIndex].text;
	 if((user_type == 'Director') || (user_type == 'Guest'))
		{
		 $("#orgtypefordirectid").show();
		 $("#orgtypeforempid").hide();
		 $("#designationid").hide();
		 
		 document.frm.empid.value = 0;
		 var empiddetails= document.getElementById("empid").value;
			//document.getElementById("select2-chosen-2").innerHTML="select";
			document.getElementById("select2-chosen-2").innerHTML="select";
			document.getElementById("select2-chosen-3").innerHTML="select";
			document.getElementById("select2-chosen-4").innerHTML="select";
			document.getElementById("select2-chosen-5").innerHTML="select";
			document.getElementById("select2-chosen-6").innerHTML="select";
			 document.getElementById("empid").value='0';
			document.frm.empid.disabled =true;
			//document.getElementById('designationid').style.visibility = 'hidden';
				 $("#designationid").hide();
			document.frm.designation_type_id.value=0;
			   $.ajax({
			         type: "post",
			         async:false,
			         url: '<%=request.getContextPath()%>/findAllOrgTypeDetailsAction',
			         async:false,
			         success: function(result) {
			        	 document.getElementById("org_type_id").innerHTML=result;
			             
			         }
			     });
			     
			     var prevType="<s:property value='userdetails.orgnizationType.org_type_id'/>";
				 	 if(prevType!=""){
						 document.getElementById("org_type_id").value=prevType;
						 var orgtypeid = document.getElementById("org_type_id").value;
						 var orgtype = org_type_id.options[org_type_id.selectedIndex].text;
						 document.getElementById('select2-chosen-5').innerHTML=orgtype;
							
					 }
			     
			
			
			
		}else{
			 $("#orgtypefordirectid").hide();
			 $("#orgchartfordirectorid").hide();
			 $("#orgtypeforempid").show();
			 $("#orgchartforempid").show();
			 $("#designationid").show();
			//document.getElementById('designationid').style.visibility = 'visible';
			 $("#designationid").show();
			document.frm.empid.disabled =false;	
		} 
	 
	 if((user_type == 'Employee') || (user_type == 'Guest'))
		 {
		 $("#agentid").show();
		 }
	 
	 
	 var empid=document.getElementById("empid").value;
	if(empid!=0){
	     $("#orgtypefordirectid").hide();
		 $("#orgchartfordirectorid").hide();
		 $("#orgtypeforempid").show();
		 $("#orgchartforempid").show();
		 $("#designationid").show();
	  $.ajax({
         type: "POST",
         url: "getDesignationFromEmployee.action?empid="+empid,
         success: function(response){
       	 	var arr = response.split(',');
         	var designationdetails=arr[0].split('@');
        	if(user_type == 'Employee')
     		{
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

function getDisplayDesignation(empid){

	 var usertypeiddetails = document.getElementById("user_type_id").value;
	 var user_type = user_type_id.options[user_type_id.selectedIndex].text;
	
	  $.ajax({
          type: "POST",
          url: "getDesignationFromEmployee.action?empid="+empid,
          success: function(response){
        	var arr = response.split(',');
            var designationdetails=arr[0].split('@');
            if(user_type == 'Employee')
     		{
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
			<%if (create.equalsIgnoreCase("Y")){%>
			<div class="row">
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
											<i class="fa fa-gift"></i>Create User
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
										<form action="addUserDetails" class="form-horizontal" name="frm" method="post">
										
											<div class="form-body">
												<div class="form-group">
													<label class="col-md-3 control-label">User Type<font color="red">*</font>:</label>
													<div class="col-md-4">
													
															
														<s:select list="usertypelist" id="user_type_id" name="userdetails.usertypedetails.user_type_id" 
		cssClass="select2_category form-control" headerKey="0" headerValue="Select"  onchange="getdisabled()"></s:select>
															<s:if test="fieldErrors.get('user_type_id').size() > 0">
					     								<span style="color:red;"><s:property value="fieldErrors.get('user_type_id').get(0)" /></span>
														</s:if>
													
													
											</div>
												</div>
												
												
												<div  class="form-group">
													<label class="col-md-3 control-label">Employee Name</label>
													<div class="col-md-4">
													
													
														<s:select list="employeelist" id="empid" name="userdetails.empid" 
		cssClass="select2_category form-control" headerKey="0" headerValue="Select" onchange="getDisplayDesignation(this.value)"></s:select>
															<s:if test="fieldErrors.get('empid').size() > 0">
					     								<span style="color:red;"><s:property value="fieldErrors.get('empid').get(0)" /></span>
														</s:if>
													</div>
												</div>
												
												
											
												
												<div class="form-group">
													<label class="col-md-3 control-label">User Login Id<font color="red">*</font></label>
													<div class="col-md-4">
														<input type="text" class="form-control" name="userdetails.userloginname"  value='<s:property value="userdetails.userloginname" />' id="userloginname"  maxlength="50">
															<s:if test="fieldErrors.get('userloginname').size() > 0">
					     								<span style="color:red;"><s:property value="fieldErrors.get('userloginname').get(0)" /></span>
														</s:if>
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
														<input type="password" class="form-control"  name="userdetails.password"   value='<s:property value="userdetails.password" />' id="password"  maxlength="20" autocomplete="off">
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
												
												
												<div class="form-group" id="designationid">
													<label class="col-md-3 control-label">Working Designation</label>
													<div class="col-md-4">
														<input type="text" class="form-control" name="designation"    readonly="readonly"  value="" id="designation"  >
														<input type="hidden" name="designation_type_id" id="designation_type_id" />
																<s:if test="fieldErrors.get('designation').size() > 0">
					     								<span style="color:red;"><s:property value="fieldErrors.get('designation').get(0)" /></span>
														</s:if>
													</div>
												</div>
												
												
												
												 
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
								
												<!-- <div class="form-group">
												<label class="col-md-3 control-label">Status</label>
												<div class="col-md-4">
													<input type="text" class="form-control"  name="ven.status" id="ven.status"  value="ACTIVE" readonly="readonly" > 
											</div>
											</div> -->
										
											<div class="form-actions fluid">
														<div class="col-md-offset-3 col-md-9">
														<button class="btn blue" type="submit" >Save</button>
														<button class="btn default" type="button" onclick="cancelform()">Cancel</button>
														<!-- onclick="getAddDetail() -->
														</div>
											</div>
									
										</div>
										<s:token/>
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