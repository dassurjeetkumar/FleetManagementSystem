<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
<!DOCTYPE html>
<html>
<head>
<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places,drawing"></script>
<script src="assets/admin/pages/scripts/getLatLongForOrg.js"
	type="text/javascript"></script>
<script type="text/javascript" src="//www.google.com/jsapi"></script>

	<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "orgtypeview!view");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
%>
<%-- <script> 
	
	
	function getAllParentsName(){
		var len= document.getElementById('orgmaster').options.length;

		 if(len<=1 ) {
	         $.ajax({
	             type: "get",
	             url: '<%=request.getContextPath()%>/deviceNameAjaxActionVen!getVendorName',
						success : function(result) {
							document.getElementById('orgmaster').text(result); 
						}
					})
		}
	}
 function getLatLong()
	{
		
	
	         $.ajax({
	             type: "get",
	             url: '<%=request.getContextPath()%>/getLatLong',
			success : function(result) {
				document.getElementById("mapdiv").text = result;
				//document.getElementById('orgtypeId').innerHTML = result;
			}
		})

	}
	function goView() {
		document.forms[0].action = 'showorgChrart.action';
		document.forms[0].submit();
	}

	google.load("elements", "1", {
		packages : "transliteration"
	});
	google.load("language", "1");

	function convert() {
		//google.load("language", "1");
		//	alert("hiiii");
		//var a='["'+document.getElementById("busStopName").value+'"]';
		convertName();
		//alert(a);
		google.language
				.transliterate(
						[ document.getElementById("orgcode").value ],
						"en",
						"kn",
						function(result) {
							if (!result.error) {
								//var container = document.getElementById("transliteration");
								if (result.transliterations
										&& result.transliterations.length > 0
										&& result.transliterations[0].transliteratedWords.length > 0) {
									//alert(result.transliterations[0].transliteratedWords[0]);
									document.getElementById("orgcodekanada").value = result.transliterations[0].transliteratedWords[0];
								}
							}
						});
	}
	function convertName() {
		//google.load("language", "1");
		//	alert("hiiii");
		//var a='["'+document.getElementById("busStopName").value+'"]';

		//alert(a);
		google.language
				.transliterate(
						[ document.getElementById("orgname").value ],
						"en",
						"kn",
						function(result) {
							if (!result.error) {
								//var container = document.getElementById("transliteration");
								if (result.transliterations
										&& result.transliterations.length > 0
										&& result.transliterations[0].transliteratedWords.length > 0) {
									//alert(result.transliterations[0].transliteratedWords[0]);
									document.getElementById("orgnamekanada").value = result.transliterations[0].transliteratedWords[0];
								}
							}
						});
	}
</script>
--%>
<title></title>
</head>
<body>
	

	<div class="page-content-wrapper">
		<div class="page-content">
		<%if (access.equalsIgnoreCase("Y")){%>

<%if (create.equalsIgnoreCase("Y")){%>
		
<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						ORGANIZATION TYPE <small></small>
					</h3>

					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Create New Organization Type
							</div>
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
							<form action="savecreateOrgType.action" method="post"
								class="form-horizontal">
								<div class="form-body">
								<div class="form-group">
										<label class="col-md-3 control-label">Organization Type Name:
											<font color="red">*</font>
										</label>
										<div class="col-md-4">
											<input type="text" class="form-control" id="orgname"
												name="organizationType.orgType" maxlength="100"
												value='<s:property value="organizationType.orgType"/>'>
												
										</div>
										<s:if test="fieldErrors.get('orgtypename').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('orgtypename').get(0)" /></span>
											</s:if>
											<font color="red"> <s:actionmessage/></font>
									</div>
									
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Remarks</label>
										<div class="col-md-4">
													<textarea  class="form-control" id="notes" maxlength="100"	name="organizationType.notes">
													<s:property value="organizationType.notes" /></textarea>	
												</div>
										
									</div>
									<div class="form-group">
									<label class="col-md-3 control-label">Status:<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select class="form-control" id="status" name="organizationType.status">
											<option value="ACTIVE">ACTIVE</option>
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
							<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>
<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
function goView() {
	document.forms[0].action = 'orgtypeview!view';
	document.forms[0].submit();
}
</script>

</body>


</html>