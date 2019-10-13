<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<!DOCTYPE html>
<html>
<head>
<script>
function goView() {

	/* var val = $("input[type='checkbox']").val(); */
	 //alert(val);
	document.forms[0].action = 'Customerdetails.action';
	document.forms[0].submit();


} 


</script>
<title>Edit customer</title>
</head>
<body>
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "AccountHeadaction!view");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
%>

	<div class="page-content-wrapper">
		<div class="page-content">
<%if (access.equalsIgnoreCase("Y")){%>
<%if (edit.equalsIgnoreCase("Y")){%>
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
				<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						 Customer details <small></small>
					</h3>
					<font color="green"><s:actionmessage/></font>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
				</div>
					
					<div class="portlet box grey-cascade">
					
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Edit Customer
							</div>
							<div class="tools">
								
							</div>
						</div>
						
						<div class="portlet-body form">
							&nbsp;&nbsp;&nbsp;&nbsp;<font color="red"><span id="integerVal"></span></font>
							
							<form action="updatecustomer"  class="form-horizontal" method="post">
								
								<div class="form-body">
								<div class="form-group">
								<label class="col-md-3 control-label">Customer Id:</label>
								<div class="col-md-4">
									<input id="C_Address" type="text" class="form-control" name="customeridd" maxLength="50"
										value="<s:property value="C_cid"/>" readonly="readonly" >
									</div>
							    </div>
								</div>	
							
								<div class="form-body">
								<div class="form-group">
								<label class="col-md-3 control-label">Customer name:</label>
								<div class="col-md-4">
									<input id="C_Address" type="text" class="form-control" name="customername" maxLength="50"
										value="<s:property value="Customername"/>"  >
									</div>
							    </div>
								</div>		
								
								<div class="form-body">
								<div class="form-group">
								<label class="col-md-3 control-label">Code:</label>
								<div class="col-md-4">
									<input id="C_Address" type="text" class="form-control" name="customername" maxLength="50"
										value="<s:property value="Code"/>"  >
									</div>
							    </div>s
								</div>	
								
							<div class="form-body">
								<div class="form-group">
								<label class="col-md-3 control-label">Address:</label>
								<div class="col-md-4">
									<input id="C_city" type="text" class="form-control" name="C_Address" maxLength="50"
										value="<s:property value="Address"/>" >
									 <s:if test="fieldErrors.get('actName').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('actName').get(0)" /></span>
									</s:if> 
								</div>
							    </div>
								</div>
								<div class="form-body">
								<div class="form-group">
								<label class="col-md-3 control-label">City:</label>
								<div class="col-md-4">
									<input id="C_city" type="text" class="form-control" name="C_city" maxLength="50"
										value="<s:property value="City"/>" >
									 <s:if test="fieldErrors.get('actName').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('actName').get(0)" /></span>
									</s:if> 
								</div>
							    </div>
								</div>
								
									<div class="form-body">
								<div class="form-group">
								<label class="col-md-3 control-label">State:</label>
								<div class="col-md-4">
									<input id="C_city" type="text" class="form-control" name="C_state" maxLength="20"
										value="<s:property value="State"/>" >
									 <s:if test="fieldErrors.get('actName').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('actName').get(0)" /></span>
									</s:if> 
								</div>
							    </div>
								</div>
								
								<div class="form-body">
								<div class="form-group">
								<label class="col-md-3 control-label">Country:</label>
								<div class="col-md-4">
									<input id="C_city" type="text" class="form-control" name="C_email" maxLength="20"
										value="<s:property value="Country"/>" >
									 <s:if test="fieldErrors.get('actName').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('actName').get(0)" /></span>
									</s:if> 
								</div>
							    </div>
								</div>
								
								<div class="form-body">
								<div class="form-group">
								<label class="col-md-3 control-label">Email:</label>
								<div class="col-md-4">
									<input id="C_city" type="text" class="form-control" name="C_email" maxLength="20"
										value="<s:property value="Email"/>" >
									 <s:if test="fieldErrors.get('actName').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('actName').get(0)" /></span>
									</s:if> 
								</div>
							    </div>
								</div>
								
								
								<div class="form-body">
								<div class="form-group">
								<label class="col-md-3 control-label">Website:</label>
								<div class="col-md-4">
									<input id="C_city" type="text" class="form-control" name="Website" maxLength="20"
										value="<s:property value="Website"/>" >
									 <s:if test="fieldErrors.get('actName').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('actName').get(0)" /></span>
									</s:if> 
								</div>
							    </div>
								</div>
								
								<div class="form-body">
								<div class="form-group">
								<label class="col-md-3 control-label">Phone:</label>
								<div class="col-md-4">
									<input id="C_city" type="text" class="form-control" name="C_Phone" maxLength="20"
										value="<s:property value="Phone"/>" >
									 <s:if test="fieldErrors.get('actName').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('actName').get(0)" /></span>
									</s:if> 
								</div>
							    </div>
								</div>
								<div class="form-body">
								<div class="form-group">
								<label class="col-md-3 control-label">cell:</label>
								<div class="col-md-4">
									<input id="C_city" type="text" class="form-control" name="C_cell" maxLength="20"
										value="<s:property value="cell"/>" >
									 <s:if test="fieldErrors.get('actName').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('actName').get(0)" /></span>
									</s:if> 
								</div>
							    </div>
								</div>
								
								<div class="form-body">
								<div class="form-group">
								<label class="col-md-3 control-label">Contact person name :</label>
								<div class="col-md-4">
									<input id="C_city" type="text" class="form-control" name="Contact_person_name" maxLength="20"
										value="<s:property value="Contactpersonname"/>" >
									 <s:if test="fieldErrors.get('actName').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('actName').get(0)" /></span>
									</s:if> 
								</div>
							    </div>
								</div>
								
									<div class="form-body">
								<div class="form-group">
								<label class="col-md-3 control-label">Contact person Email :</label>
								<div class="col-md-4">
									<input id="C_city" type="text" class="form-control" name="Contact_person_Email" maxLength="20"
										value="<s:property value="ContactpersonEmail"/>" >
									 <s:if test="fieldErrors.get('actName').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('actName').get(0)" /></span>
									</s:if> 
								</div>
							    </div>
								</div>
								
									<div class="form-body">
								<div class="form-group">
								<label class="col-md-3 control-label">Contact person Phone :</label>
								<div class="col-md-4">
									<input id="C_city" type="text" class="form-control" name="Contact_person_Phone" maxLength="20"
										value="<s:property value="ContactpersonPhone"/>" >
									 <s:if test="fieldErrors.get('actName').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('actName').get(0)" /></span>
									</s:if> 
								</div>
							    </div>
								</div>
									 <div class="form-group">
							  <label class="control-label col-md-3">Customer Edit Date:</label>
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


</body>
</html>