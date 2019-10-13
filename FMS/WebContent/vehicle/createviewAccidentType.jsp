<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>


<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//www.google.com/jsapi"></script>

<script>
	function goView() {
		document.forms[0].action = 'accidenttypeView.action';
		document.forms[0].submit();
	}
</script>

<title></title>
</head>
<body>
	<div class="page-content-wrapper">
		<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "accidenttypeView.action");
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
			<%if (create.equalsIgnoreCase("Y")){%>
			<div class="row">
				<div class="col-md-12">
					<h3 class="page-title">ACCIDENT TYPE</h3>
				</div>
			</div>
			<div class="tab-content">
			
				<div class="row">
			
					<div class="col-md-12" align="center" style="font-size: 1.1em">

						<%-- <span class="help-block" style="color: red; list-style: none"><s:actionerror /></span> --%>
						<%-- <span class="help-block" style="color: red; list-style: none"><s:actionmessage /></span> --%>
					</div>
				</div>
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Create New Accident Type
							</div>
							<!-- <div class="tools">
								<a href="javascript:;" class="collapse"> </a> 
								 <a href="javascript:;" class="reload"> </a> 
							</div> -->
						</div>

						<div class="portlet-body form">


							<!-- BEGIN FORM-->
							<form action="createAccidentTypeAction.action" method="post"
								class="form-horizontal">
								<font color="red"><s:actionmessage /></font>
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Accident Type
											Name:<font color="red">*</font>
										</label>
										<div class="col-md-4">
											<input type="text" class="form-control"
												id="accident_type_name" placeholder="Minor, Major, Fatal"
												maxlength="20"
												value='<s:property value="accidenttype.accident_type_name"/>'
												name="accidenttype.accident_type_name">
											<s:if test="fieldErrors.get('accname').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('accname').get(0)" /></span>
											</s:if>
										</div>
									</div>
								</div>
								<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Remarks:</label>

										<div class="col-md-4">
											<textarea rows="3" class="form-control" id="notes" maxlength="100" wrap="soft" name="accidenttype.notes"><s:property value="accidenttype.notes" /></textarea>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">Status:</label>
									<div class="col-md-4">
										<select class="form-control" id="status"
											name="accidenttype.status"
											<s:property value="accidenttype.status"/>>
											<option value="NEW">NEW</option>
										</select>
									</div>
								</div>
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn blue" onSubmit="validate()">Save</button>
										<button type="button" class="btn default" onclick="goView()">Cancel</button>
									</div>
								</div>


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