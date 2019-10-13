<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>


<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
<script>
	function validate() {
		if (document.getElementById("device_type_name").value == '') {
			alert('Please Enter a New Device Type');
			return false;
		}
		if (document.getElementById("note").value == '') {
			alert('Please Enter Remarks');
			return false;
		}
		document.forms[0].action = 'createDeviceTypeAction.action';
		document.forms[0].submit();
	}
	function getDeviceName(){
		var len= document.getElementById('devicetypeId').options.length;
		 if(len<=1 ) {
	         $.ajax({
	             type: "post",
	             url: '<%=request.getContextPath()%>/deviceNameAjaxAction',
	             success: function(result) {
	                 document.getElementById('devicetypeId').innerHTML=result;
	             }
	         });
		 }
	 }
	function getVendorName(type){
		//alert();
		//var len= document.getElementById('vendorId').options.length;
		//Sif(len<=1 ) {
	         $.ajax({
	             type: "post",
	             url: '<%=request.getContextPath()%>/deviceNameAjaxActionVen!getVendorName?type='+type,
					success : function(result) {
						//alert(result);
					document.getElementById('vendorList').innerHTML = result;
				}
			});
		//}
	}
	function goView() {
		document.forms[0].action = 'showdevice.action';
		document.forms[0].submit();
	}
</script>
<%-- <script>
function getDepot(orgId){
var val=document.getElementById('divisionlist').value;
			 if(val!=0) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/getDepotList?val=' + val,
				success : function(result) {
					document.getElementById('depotlist1').innerHTML = result;
					
				}
			});
		}

	}

</script> --%>
<title>Insert title here</title>
</head>
<body>
<div class="page-content-wrapper">
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "showdevice.action");
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
					<h3 class="page-title">
						DEVICE <small></small>
					</h3>
				</div>
			</div>
			<div class="tab-content">
		
				<div class="tab-pane active">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Create Device
							</div>
						</div>
						<div class="portlet-body form">
						<div class="row">
							<b>
								<font color="green"> <s:actionmessage/></font>
								<font color="red"> <s:actionerror/></font>
							</b>
						</div>
						<form action="deviceAction.action" method="post"class="form-horizontal">
							<div class="form-body">
							<input type="hidden" name="isCreateDevice" value="1"/>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Device Serial Number<font color="red">*</font></label>
											<div class="col-md-4">
												<input type="text" class="form-control" maxlength="50" id="device_serial_number" name="device.device_serial_number" value='<s:property value="device.device_serial_number"/>'>
		     									<span style="color:red;"><s:property value="fieldErrors.get('serialno').get(0)" /></span>
											</div>
										</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Device Type<font color="red">*</font></label>
										<div class="col-md-4">
										<select id="devicetypeId" name="device.device.device_type_id" class="select2_category form-control"  onchange="getVendorName(this.value)">
											<s:iterator value="deviceTypeList" status="aaa">
												<option id='devType<s:property value="device_type_id"/>'value='<s:property value="device_type_id" />'>
													<s:property value="device_type_name" />
												</option>
											</s:iterator>
										</select>
										<span style="color: red;"><s:property value="fieldErrors.get('devicetype').get(0)" /></span>
										</div>
										<script>
										var devType = "<s:property  value='device.device.device_type_id'/>";
										if (devType != "") {
											if(document.getElementById("devType"+ devType)!=null)
												document.getElementById("devType"+ devType).selected = true;
										}
										</script>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Model<font color="red">*</font></label>
										<div class="col-md-4">
											<select id="devicetypeId" name="device.model.model_type_id" class="select2_category form-control">																																				
												<s:iterator  value="modelTypeList" status="aaa">
													<option id='model<s:property value="model_type_id"/>'value='<s:property value="model_type_id" />'>
														<s:property value="model_type_name" />
													</option>
												</s:iterator>
											</select>
											<span style="color: red;"><s:property	value="fieldErrors.get('modelno').get(0)" /></span>
											<script>
											var modelType = "<s:property  value='device.model.model_type_id'/>";
											//alert(modelType);
											if (modelType != "") {
												if(document.getElementById("model"+ modelType)!=null)
													document.getElementById("model"+ modelType).selected = true;
												}
											</script>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Status:<font	color="red">*</font></label>
										<div class="col-md-4">
											<select class="select2_category form-control" id="status" name="device.status">
												<option value="ACTIVE">ACTIVE</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Vendor Name<font color="red">*</font></label>
										<div class="col-md-4">
											<select name="device.vendor.id"  id ="vendorList" class="select2_category form-control"  >
											<option value="0">--Select--</option>
											</select>
											<%-- 	<select list="depotlist" id="depotlist1" class="select2_category form-control" name="depotlist1" headerKey="0" headerValue="---All---" > 
												<option value="0">--All--</option>
 										</select>  --%>
											<span style="color: red;"><s:property value="fieldErrors.get('vendorname').get(0)" /></span>
											<script>
											var vendorType = "<s:property  value='device.vendor.id'/>";
											if (vendorType != "") {
												if(document.getElementById("vendor"+ vendorType)!=null)
													document.getElementById("vendor"+ vendorType).selected = true;
												}
											</script>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">Purchased Date:<font color="red">*</font></label>
										<div class="col-md-4">
											<div class="input-group date date-picker" data-date-format="dd-mm-yyyy">
												<input class="form-control" type="text" size="16" name="device.purchasedateString" id="purchase_date" readonly="readonly" value='<s:property value="device.purchasedateString"/>'>
												<span class="input-group-btn">
													<button class="btn default date-set" type="button">
														<i class="fa fa-calendar"></i>
													</button>
												</span>
											</div>
		     								<span style="color:red;"><s:property value="fieldErrors.get('purchasedate').get(0)" /></span>
										</div>
										<script>
										var curr_date=new Date().toJSON().slice(0,10);
										curr_date=curr_date.split("-");
										curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
										$('#purchase_date').val(curr_date);
										</script>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Remarks:</label>
										<div class="col-md-4">
											<textarea class="form-control" name="device.notes" id="ven.note" maxlength="200"><s:property value="device.notes"/></textarea>
										</div>
									</div>		
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn blue" onSubmit="validate()">Save</button>
										<button type="button" class="btn default" onclick="goView()">Cancel</button>
									</div>
								</div>
								</div>
								<s:token></s:token>
							</form>
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