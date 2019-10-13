<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<!DOCTYPE html>
<html>
<head>
<script>
function chekChar(){
	
	var title=document.getElementById('actName').value;
	
		var regExp =/!@#$^&%*()+=-[\x5B\x5D]\/{}|:<>?,./im;
		
			 if(/[-!$%^&*()@_+|~=`\\#{}\[\]:";'<>?,.\/]/.test(title))
			{
			//alert("check");
			document.getElementById('actName').value='';
	        document.getElementById('specialVal').innerHTML='Special Characters not allowed.';
	        return false;
	        }
	      else {
	    	  document.getElementById('specialVal').innerHTML='';
	           return true;
	        }
		
	
}



</script>
<title>Insert title here</title>
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
						Account Head <small></small>
					</h3>
					<font color="green"><s:actionmessage/></font>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
				</div>
					
					<div class="portlet box grey-cascade">
					
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Edit Account Head
							</div>
							<div class="tools">
								
							</div>
						</div>
						<div class="portlet-body form">
							&nbsp;&nbsp;&nbsp;&nbsp;<font color="red"><span id="integerVal"></span></font>
							
							<form action="EditAccountHeadaction.action"  class="form-horizontal" method="post">
								<font color="red"><s:actionerror/></font>  <br>
								<div class="form-body">
								<div class="form-group">
								<label class="col-md-3 control-label">Account Head ID:<font color="red">*</font></label>
								<div class="col-md-4">
									<input id="actName" type="text" class="form-control" name="actheadmaster.acthead_id" maxLength="20"
										value="<s:property value="actheadmaster.acthead_id"/>" disabled="disabled" >
									
								</div>
							    </div>
							    </div>
								<div class="form-body">
								<div class="form-group">
								<label class="col-md-3 control-label">Account Head Number:<font color="red">*</font></label>
								<div class="col-md-4">
									<input id="actName" type="text" class="form-control" name="actheadmaster.acthead_name" maxLength="20"
										value="<s:property value="actheadmaster.acthead_name"/>" >
									 <s:if test="fieldErrors.get('actName').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('actName').get(0)" /></span>
									</s:if> 
								</div>
							    </div>
								</div>
								
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Revenue Type:<font color="red">*</font></label>

										<div class="col-md-4">
										
									<s:select list="revenueTypeMap" id="id" name="actheadmaster.revenuetype.id" 
									cssClass="select2_category form-control" headerKey="0" headerValue="Select"></s:select>

											<s:if test="fieldErrors.get('revenuetype.id').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('revenuetype.id').get(0)" /></span>
											</s:if>	 

										</div>
									</div>
								</div>
								
								<%-- <div class="form-group">
										<label class="col-md-3 control-label">Status:</label>
										<div class="col-md-3">
											<select class="form-control" name="actheadmaster.status" >
												<option value="ACTIVE">ACTIVE</option>
												<option value="INACTIVE">INACTIVE</option>
											</select>									    
										</div>
							    </div> --%>
							   <div class="form-body">
								<div class="form-group">
									<label class="control-label col-md-3"> Status:<font color="red">*</font></label>
									<div class="col-md-4">
										<div class="input-group input-medium" style="width: auto">
											<select class="form-control" id="status"
												name="actheadmaster.status">

												<option id="ACTIVE" value="ACTIVE">ACTIVE</option>
												<option id="INACTIVE" value="INACTIVE">INACTIVE</option>
											</select>

										</div>
										<script>
											var status = "<s:property value="actheadmaster.status"/>";
											if (status != undefined) {
												if (status == "ACTIVE"|| status == "ACTIVE") {
													document.getElementById("ACTIVE").selected = true;
												} else {
													document.getElementById("INACTIVE").selected = true;
												}
											}
										</script>
									</div>
								</div>
								</div>
								<div class="form-body">
								<div class="form-group">
									<label class="control-label col-md-3"> CR/DB:<font color="red">*</font></label>
									<div class="col-md-4">
										<div class="input-group input-medium" style="width: auto">
											<select class="form-control" id="CR_DB"
												name="actheadmaster.CR_DB">

												<option id="CR" value="CR">CR</option>
												<option id="DB" value="DB">DB</option>
											</select>

										</div>
										<script>
											var CR_DB = "<s:property value="actheadmaster.CR_DB"/>";
											if (CR_DB != undefined) {
												if (CR_DB == "CR"|| CR_DB == "CR") {
													document.getElementById("CR").selected = true;
												} else {
													document.getElementById("DB").selected = true;
												}
											}
										</script>
									</div>
								</div>
								</div>
							  <div class="form-body">
								<div class="form-group">
								<label class="col-md-3 control-label">Nomenclature:</label>
								<div class="col-md-4">
								<textarea rows="3" class="form-control" id="note" name="actheadmaster.notes" maxLength="100"><s:property value="actheadmaster.notes" /></textarea>
								<%-- 	<input id="note" type="text" class="form-control" name="concession.note" maxLength="60"
										value="<s:property value="concession.note"/>"> --%>
									 <s:if test="fieldErrors.get('note').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('note').get(0)" /></span>
									</s:if> 
								</div>
							    </div>
							  </div>
							  
							  <div class="form-body">
								<div class="form-group">
								<label class="col-md-3 control-label">Remarks:</label>
								<div class="col-md-4">
								<textarea rows="3" class="form-control" id="remarks" name="actheadmaster.remarks" maxLength="100"><s:property value="actheadmaster.remarks" /></textarea>
								<%-- 	<input id="note" type="text" class="form-control" name="concession.note" maxLength="60"
										value="<s:property value="concession.note"/>"> --%>
									 <s:if test="fieldErrors.get('remarks').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('remarks').get(0)" /></span>
									</s:if> 
								</div>
							    </div>
							  </div>
							  
								<input type="hidden" name="actheadmaster.acthead_id" value="<s:property value='actheadmaster.acthead_id'/>" />
																						
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn blue">Save</button>
										<button type="button" class="btn default" onclick = "javascript:location.href='AccountHeadaction!view';">Cancel</button>
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