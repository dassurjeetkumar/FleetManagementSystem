<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">

<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places,drawing"></script>
<script src="assets/admin/pages/scripts/editGeoCodeForOrg.js"
	type="text/javascript"></script>
	<script src="assets/admin/pages/scripts/kannada.js"
	type="text/javascript"></script>

<script src="assets/admin/pages/scripts/converter.js"
	type="text/javascript"></script>

<script type="text/javascript" src="//www.google.com/jsapi"></script>

<script>
function check()
{
	if(document.getElementById("orgname").value=="")
	{
	bootbox.alert("Corporation	Name Cannot be Empty");
	return false;
	}
	if(document.getElementById("orgcode").value=="")
		{
		bootbox.alert("Corporation	Code Cannot be Empty");
		return false;
		}
	if(document.getElementById("orgnamekanada").value=="")
	{
	bootbox.alert("Corporation	Name(Kannada) Cannot be Empty");
	return false;
	}
	if(document.getElementById("orgcodekanada").value=="")
		{
		bootbox.alert("Corporation	Code(Kannada) Cannot be Empty");
		return false;
		}
	
	document.getElementById("form1").action="saveeditOrgchart.action";
	document.getElementById("form1").submit();
}
function onLoad() {
		var options = {
			sourceLanguage : 'en', // or google.elements.transliteration.LanguageCode.ENGLISH,
			destinationLanguage : [ 'kn' ], // or [google.elements.transliteration.LanguageCode.HINDI],
			shortcutKey : 'ctrl+g',
			transliterationEnabled : true
		};
		var control = new google.elements.transliteration.TransliterationControl(
				options);
		var ids = [ "orgnamekanada" ];
		control.makeTransliteratable(ids);
		
		var ids = [ "orgcodekanada" ];
		control.makeTransliteratable(ids);
	}

google.setOnLoadCallback(onLoad);

	function getDeviceName(){
		var len= document.getElementById('devicetypeId').options.length;

		 if(len<=1 ) {
	         $.ajax({
	             type: "post",
	             url: '<%=request.getContextPath()%>/deviceNameAjaxAction',
	             success: function(result) {
	                 document.getElementById('devicetypeId').innerHTML=result;
	             }
	         })
		 }
	 }
	function getVendorName(){
		var len= document.getElementById('vendorId').options.length;

		 if(len<=1 ) {
	         $.ajax({
	             type: "post",
	             url: '<%=request.getContextPath()%>/deviceNameAjaxActionVen!getVendorName',
						success : function(result) {
							document.getElementById('vendorId').innerHTML = result;
						}
					})
		}
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
	
	function spclchar(el) {
		var iChars = "!@$%^&*+=[]\\\’;,./{}|\":?~_";
		for ( var i = 0; i < el.value.length; i++) {
			if (iChars.indexOf(el.value.charAt(i)) != -1) {

				el.value = '';
				alert("Special Characters not allowed");
				return false;
			}
		}
		return true;
	}
</script>

<title>Insert title here</title>
</head>
<body >
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ShowCorporation.action");
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
		<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						CORPORATION  <small></small>
					</h3>

					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
		
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Edit Corporation
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
							<form   id="form1" method="post"
								class="form-horizontal">
								<s:if test="%{updatemsg=='duplicate'}">
															<font color="red"><s:actionmessage/></font>
														</s:if>
								<input type="hidden" name="orgchart.org_chart_id"
									value="<s:property value="orgchart.org_chart_id"/>" /> <input
									type="hidden" id="paredntid" name="orgchart.parent_id"
									value="00000" />

								<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Corporation
											Name:<font color="red">*</font>
										</label>

										<div class="col-md-4">
<!-- 											<input type="text" class="form-control" id="orgname" -->
<!-- 												maxlength="100" name="orgchart.org_name" -->
<%-- 												value='<s:property value="orgchart.org_name"/>'> --%>
												
												<input class="form-control input-lg"
													id="orgname" name="orgchart.org_name" type="text"
													maxlength="80"
													 onFocus="javascript:print_many_words('orgname','orgnamekanada')"
                							         onKeyUp="javascript:print_many_words('orgname','orgnamekanada')"
                							        	 value="<s:property value="orgchart.org_name"/>" onblur="spclchar(this);">
										</div>
										
<!-- 										<div class="col-md-3"> -->

<!-- 											<button class="btn purple" type="button" -->
<!-- 												onclick="convertName()">Convert</button> -->

<!-- 										</div> -->

									</div>
									
								</div>
								<s:if test="fieldErrors.get('org_name').size() > 0">
											<span style="color: red;"><s:property
													value="fieldErrors.get('org_name').get(0)" /></span>
										</s:if>
								<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Corporation
											Name(Kannada):<font color="red">*</font>
										</label>
										<div class="col-md-4">
<!-- 											<input type="text" class="form-control" id="orgnamekanada" maxlength="100" -->
<!-- 												name="orgchart.org_name_kannada" -->
<%-- 												value='<s:property value="orgchart.orgnamekannada"/>'> --%>
												
												<input class="form-control input-lg" maxlength="100"
												id="orgnamekanada" name="orgchart.org_name_kannada"
												type="text" maxlength="80" 
												value='<s:property value="orgchart.orgnamekannada"/>' 
												/>
												
										</div>
										<s:if test="fieldErrors.get('org_name_kannada').size() > 0">
											<span style="color: red;"><s:property
													value="fieldErrors.get('org_name_kannada').get(0)" /></span>
										</s:if>
									</div>
									
								</div>
								<s:if test="fieldErrors.get('orgType.org_type_id').size() > 0">
										<span style="color: red;"><s:property
												value="fieldErrors.get('orgType.org_type_id').get(0)" /></span>
									</s:if>
								<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Corporation
											Code:<font color="red">*</font>
										</label>

										<div class="col-md-4">
<!-- 											<input type="text" class="form-control" id="orgcode" -->
<!-- 												name="orgchart.org_code" maxlength="30" -->
<%-- 												value='<s:property value="orgchart.org_code"/>' > --%>
												
												<input class="form-control input-lg" 
													id="orgcode" name="orgchart.org_code" type="text" maxlength="30"
													 onFocus="javascript:print_many_words('orgcode','orgcodekanada')"
                							         onKeyUp="javascript:print_many_words('orgcode','orgcodekanada')"
                							        	 value="<s:property value="orgchart.org_code"/>" onblur="spclchar(this);">
												
										</div>
										
<!-- 										<div class="col-md-3"> -->
<!-- 											<button class="btn purple" type="button" onclick="convert()">Convert</button> -->
<!-- 										</div> -->
										
									</div>

								</div>
								<s:if test="fieldErrors.get('org_code').size() > 0">
											<span style="color: red;"><s:property
													value="fieldErrors.get('org_code').get(0)" /></span>
										</s:if>
								
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Corporation
											Code(Kannada):<font color="red">*</font>
										</label>

										<div class="col-md-4">
<!-- 											<input type="text" class="form-control" id="orgcodekanada" -->
<!-- 												name="orgchart.org_code_kannada" -->
<%-- 												value='<s:property value="orgchart.orgcodekannada"/>' 	> --%>
												
													<input class="form-control input-lg"
												id="orgcodekanada" name="orgchart.org_code_kannada"
												type="text" maxlength="30" 
												value='<s:property value="orgchart.orgcodekannada"/>' 
												/>
												
										</div>
										<s:if test="fieldErrors.get('org_code_kannada').size() > 0">
											<span style="color: red;"><s:property
													value="fieldErrors.get('org_code_kannada').get(0)" /></span>
										</s:if>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">Corporation
										Website:<font color="red"></font>
									</label>
									<div class="col-md-4">
										<input type="text" class="form-control" id="website"
											name="orgchart.website" maxlength="50"
											value='<s:property value="orgchart.website"/>'>
									</div>
								</div>


								<div class="form-group">
									<label class="control-label col-md-3">Corporation
										Address: </label>
										<div class="col-md-4">

										<textarea class="form-control" placeholder="Remarks"  name="orgchart.address" maxlength="200"><s:property value="orgchart.address"/> </textarea>
									</div>
									
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">Corporation Phone
										Number:<font color="red"></font>
									</label>
									<div class="col-md-4">
										<input type="text" class="form-control" id="phone_number"
											name="orgchart.phone_number" maxlength="20"
											value='<s:property value="orgchart.phone_number"/>'>
									</div>
								</div>
								<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">City</label>

										<div class="col-md-4">
											<input type="text" class="form-control" id="city"
												name="orgchart.city" maxlength="20"
												value='<s:property value="orgchart.city"/>'>
										</div>
									</div>
								</div>

								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">State:<font
											color="red"></font></label>

										<div class="col-md-4">
											<input type="text" class="form-control" id="state"
												name="orgchart.state" maxlength="20"
												value='<s:property value="orgchart.state"/>'>
										</div>
									</div>
								</div>
								<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Country:<font
											color="red"></font></label>

										<div class="col-md-4">
											<input type="text" class="form-control" id="country"
												name="orgchart.country" maxlength="20"
												value='<s:property value="orgchart.country"/>'>
										</div>
									</div>
								</div>
								<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Geocode Data:<font
											color="red"></font></label>

										<div class="col-md-4">
											<input type="text" class="form-control" id="geodata"
												name="orgchart.location_string" readonly="readonly"
												value='<s:property value="orgchart.location_string"/>'>
										</div>
										<a data-toggle="modal" class="btn blue" role="button"
											 onclick="initializea()">Edit Geocode </a>
									</div>

									<s:if test="fieldErrors.get('latitude').size() > 0">
										<span style="color: red;"><s:property
												value="fieldErrors.get('latitude').get(0)" /></span>
									</s:if>

								</div>
							
								<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Landmark:</label>

										<div class="col-md-4">
											<input type="text" class="form-control" id="landmark"
												name="orgchart.landmark" maxlength="20"
												value='<s:property value="orgchart.landmark"/>'>
										</div>
									</div>
								</div>

								<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Contact Person:<font
											color="red"></font></label>

										<div class="col-md-4">
											<input type="text" class="form-control" id="contact_person"
												name="orgchart.contact_person" maxlength="30"
												value='<s:property value="orgchart.contact_person"/>'>
										</div>
									</div>
								</div>
								<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Sector Layer:<font
											color="red"></font></label>

										<div class="col-md-4">
											<input type="text" class="form-control" id="website" maxlength="20"
												name="orgchart.sector_layer"
												value='<s:property value="orgchart.sector_layer"/>'>
										</div>
									</div>
									<%-- <s:if test="fieldErrors.get('country').size() > 0">
										<span style="color: red;"><s:property
												value="fieldErrors.get('country').get(0)" /></span>
									</s:if> --%>
								</div>
								<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Sector for Line Checking:<font
											color="red"></font></label>

										<div class="col-md-4">
											<input type="text" class="form-control" id="website" maxlength="20"
												name="orgchart.sector_for_line_checking"
												value='<s:property value="orgchart.sector_for_line_checking"/>'>
										</div>
									</div>
								</div>
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="button" class="btn blue" onclick="check()">Save</button>
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
<div style="display: none;" id="myModal1" class="modal fade"
	 role="dialog" aria-labelledby="myModalLabel1"
	>
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">Capture GeoCode From Map</h4>
			</div>
			<div>
						<form class="form-horizontal" role="form" action="#" method="post">
							<input type="hidden" name="requestType" id="requestType"
								value="map" />
							<div>
								
									<div class="form-group">
										<label class="col-md-4 control-label"></label>
									</div>
									<div class="portlet-body">
										<div id="edit-org-map" class="gmaps" ></div>

									</div>
								
							</div>
							<div style="position: center">
								<center>
									<button type="button" class="btn green"
										onclick="submitMapForm()" data-dismiss="modal">Save</button>
									<button type="button" class="btn green"
										onclick="clearMapForm()" id="clearMap" onclick="clearForm()">Clear
										Map</button>
										<button type="button" class="btn green"
										 id="showForm" onclick="initialize()" style="display:none">Show
										Map</button>
									<button aria-hidden="true" data-dismiss="modal"
										class="btn default">Cancel</button>
								</center>

							</div>

						</form>
				
			</div>

		</div>
	</div>
</div>
<script>
function initializea()
{	var param = $('#geodata').val();
	window.open("orgchart/EditGeoFence.jsp","Edit GeoFence", "width=500, height=500");
	}
</script>
</html>