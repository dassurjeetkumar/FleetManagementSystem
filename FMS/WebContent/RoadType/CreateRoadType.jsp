<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.trimax.its.model.User"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>


<!DOCTYPE html>
<html>
<head>
<script>
</script>
<style>
h1.intro {
	color: red;
	font-size: 14px;
}
</style>
<script>
function goView() {
	
		/* var val = $("input[type='checkbox']").val(); */
		//alert(val);
		document.forms[0].action = 'roadType.action';
		document.forms[0].submit();

	
}
<%-- function getRoadTypeId(){
	//alert('Here');
	 /* var selectedValue = $('#form-control').val(); */
	 
	 var len= document.getElementById('roadtypeid').options.length;

	 if(len<=1 ) {
        $.ajax({
            type: "get",
            url: '<%=request.getContextPath()%>/findRoadTypeIdAction',
            success: function(result) {
                document.getElementById('roadtypeid').innerHTML=result;
            }
        });
	 }
	// alert("end");
}
 --%>






</script>
<script type="text/javascript" src="//www.google.com/jsapi">

</script>
</head>
<body>
	<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "roadType.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
%>

<div class="page-content-wrapper">

		<div class="page-content">
<%if (access.equalsIgnoreCase("Y")){%>
<%if(create.equalsIgnoreCase("Y")){ %>
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						ROAD TYPE <small></small>
					</h3>
					
					<!-- END PAGE TITLE & BREADCRUMB-->
					
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Create Road Type
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
	<%-- <div class="page-content-wrapper">
		<div class="page-content">
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box green">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Create Road Type
							</div>
							<div class="tools">
								
							</div>
						</div>
						<div class="portlet-body form">
							<div class="col-md-12" align="left" style="font-size: 1.1em">

				<span class="help-block" style="color: red; list-style: none"><s:actionerror /></span>
				
				<span class="help-block" style="color: red; list-style: none">
												<s:property value="msg" />
											</span>
			</div> --%>
							<!-- BEGIN FORM-->
							<form action="saveRoadType.action" class="form-horizontal" method="post">
								<span style="color: red;"><s:property value="msg" /></span>
								<div class="form-body">
                                   <div class="form-group">
										<label class="col-md-3 control-label">Road Type Name:<font color="red">*</font></label>
										<div class="col-md-4">
											
												<input type="text" name="roadType.road_type_name"
													id="roadtypename" class="form-control"
													value="<s:property value="roadType.road_type_name"/>" maxlength="60">
												
												 <s:if test="fieldErrors.get('roadtypename').size() > 0">
													<span style="color: red;"><s:property
															value="fieldErrors.get('roadtypename').get(0)"/></span>
												</s:if> 
											
										</div>
									</div>
									</div>
									<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Remarks:</label>
										<div class="col-md-4">
											
											<textarea rows="3" class="form-control" id="notes" name="roadType.note" maxlength="100" ><s:property value="roadType.note"/></textarea>
																						
												<%-- <s:if test="fieldErrors.get('Note').size() > 0">
													<span style="color: red;"><s:property
															value="fieldErrors.get('Note').get(0)" /></span>
												</s:if> --%>
											
										</div>
									</div>
									</div>
									<div class="form-body">
								<div class="form-group">
									<label class="col-md-3 control-label">Status:<font color="red">*</font></label>
									<div class="col-md-4">
										<select class="form-control" id="status" name="roadType.status">
											<option value="ACTIVE">ACTIVE</option>
											
										</select>

									</div>
								</div>

                               </div>
									<%-- <div class="form-group">
										<label class="col-md-3 control-label">Status<font color="red">*</font></label>
										<div class="col-md-4">
											
												<select class="form-control" name="roadType.status" id="status">
													<option id="active" value="ACTIVE" selected="selected">ACTIVE</option>
													<option id="deactive" value="INACTIVE">INACTIVE</option>

												</select>
												
												<script>
															var status = "<s:property value="roadType.status"/>";
															 if (status != undefined) {
																if (status == "ACTIVE"
																		|| status == "ACTIVE") {
																	document.getElementById("active").selected = true;
																} else {
																	document.getElementById("deactive").selected = true;
																}
															} 
												</script>


											
										</div>
									</div> --%>
									<div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9">
											<button type="submit" class="btn blue">Save</button>
											<button type="button" id="cancel" class="btn default" onclick="goView()">Cancel</button>
											
										</div>
									</div>
							
								<s:token />
							</form>
						<%}else{ %>
<%@ include file="/pages/admin/error.jsp" %>
<%} %>									
	<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>

	
							
							<!-- END FORM-->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
										
