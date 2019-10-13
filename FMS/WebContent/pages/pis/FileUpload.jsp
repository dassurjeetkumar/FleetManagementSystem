
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.trimax.its.utility.model.AccessRights"%>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao"%>
<html>
<head>
<style>
h1.intro {
	color: red;
	font-size: 14px;
}
</style>
<script>
	
</script>
</head>

<script src="assets/vts/js/vehiclealert.js" type="text/javascript"></script>
<body>
	<%
		AccessRightsDao accessrightdao = new AccessRightsDao();
		AccessRights accessrights = new AccessRights();
		int usrsessionid = (Integer) request.getSession().getAttribute(
				"userid");
		accessrights = accessrightdao.accessRightsdetails(usrsessionid,
				"uploadfileform.action");
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
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div class="modal fade" id="portlet-config" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true"></button>
							<h4 class="modal-title">Modal title</h4>
						</div>
						<div class="modal-body">Widget settings form goes here</div>
						<div class="modal-footer">
							<button type="button" class="btn blue">Save changes</button>
							<button type="button" class="btn default" data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>


			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						PIS FILE UPLOAD <small></small>
					</h3>

				</div>
			</div>

			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>Pis File Upload
							</div>
						</div>

						<div class="portlet-body form">
							


							<!-- BEGIN FORM-->  
							<form action="uploadfile.action"
								class="form-horizontal" method="post" enctype="multipart/form-data">
								<div class="form-body">
									<%-- <h1 class="intro">
										<s:property value="msg" />
									</h1> --%>
 <font color="green" size="2px"><s:property value="msg" /></font>
									<div class="form-group">
										<%-- <label class="control-label col-md-3">File input<span
											class="required"> </span></label> --%>
										<div class="col-md-4">
										    <label for="myFile">Upload your file</label>
										      <%-- <s:file name="myFile" label="Choose File" size="40" /> --%>
                                               <input type="file" name="myFile" /> 
											
										</div>
										<button type="submit" class="btn default"
											style="position: absolute; left: 600px;">Upload</button>
											<%-- <embed src="<s:property value="myFileFileName"/>" pluginspage="http://www.microsoft.com/Windows/MediaPlayer/" name="mediaplayer1" ShowStatusBar="true" EnableContextMenu="false" width="700" height="500" autostart="false" loop="false" align="middle" volume="60"></embed> --%>
									</div>


								</div>
							</form>
							<!-- END FORM-->
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