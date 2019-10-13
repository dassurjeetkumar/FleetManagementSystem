<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
<script src="assets/admin/pages/scripts/kannada.js"
	type="text/javascript"></script>

<script src="assets/admin/pages/scripts/converter.js"
	type="text/javascript"></script>
<script type="text/javascript">


function cancelform(){
	window.location.href = 'TicketSubType.action';
}

function check()
{
	//alert(document.getElementById("ticket_type_id").value);
	if(document.getElementById("ticket_type_id").value=="0")
	{
	bootbox.alert("Please select Ticket Type");
	return false;
	}
	
	if(document.getElementById("ticket_sub_type_name").value=="")
	{
	bootbox.alert("Ticket SubType Name Cannot be Empty");
	return false;
	}
	if(document.getElementById("ticket_sub_type_code").value=="")
		{
		bootbox.alert("Ticket SubType Code Cannot be Empty");
		return false;
		}
	if(document.getElementById("ticket_sub_type_name_kannada").value=="")
	{
	bootbox.alert("Ticket SubType Name(Kannada) Cannot be Empty");
	return false;
	}
	
	document.getElementById("form1").action="createTicketSubTypeAction.action";
	document.getElementById("form1").submit();
}

</script>


</head>
<body onload="convertName()">

<input type="text" id='a'>

<div class="page-content-wrapper">
	<div class="page-content">
		<div class="tab-content">
		<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						Ticket Sub Type  <small></small>
					</h3>
					
				
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			<div class="tab-pane active" id="tab_0">
				<div class="portlet box grey-cascade">
				<div class="row">
				
			</div>
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i>Create Ticket Sub Type
						</div>
						
					</div>
					
					<div class="portlet-body form">
					<s:if test="hasActionErrors()">
   <div class="alert alert-danger">
			<button class="close" data-close="alert"></button>
			<span>
			<s:actionerror/> </span>
		</div>
      
   
</s:if>
						<!-- BEGIN FORM-->
						
					<form action="" class="form-horizontal" method="POST"  name ="frm" id="form1">
						
						<input type="hidden" name="createPassIssuerType" value="1" >		
						
								
								<div></div>
								
								
			<font color="red"><s:actionerror/></font>  <br>
			
			
			
						<div class="form-group">
													<label class="col-md-3 control-label">Ticket Type:<span class="required"> * </span></label>
													<div class="col-md-4">
<%-- 												<input type="text" class="form-control" name="passduration.pass_type_id" id="pass_type_id" value='<s:property value="passduration.pass_type_id" /> '> --%>
												
												<s:select list="Tickettype" id="ticket_type_id" 
											name="ticketsubtype.ticket_type_id"  value='<s:property value="ticketsubtype.ticket_type_id" />'
												cssClass="select2_category form-control"  
												 headerKey="0" headerValue="--select--"></s:select> 
												
												<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('ticket_type_id').get(0)}" />
										</span>
												</div>
												</div>
							<div class="form-group">
													<label class="col-md-3 control-label">Ticket SubType Name<span class="required"> * </span></label>
													<div class="col-md-4">
												<input type="text" class="form-control" name="ticketsubtype.ticket_sub_type_name" id="ticket_sub_type_name" 
												onFocus="javascript:print_many_words('ticket_sub_type_name','ticket_sub_type_name_kannada')"
                							         onKeyUp="javascript:print_many_words('ticket_sub_type_name','ticket_sub_type_name_kannada')"
												value='<s:property value="ticketsubtype.ticket_sub_type_name" /> '>
												<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('ticket_sub_type_name').get(0)}" />
										</span>
												</div>
												</div>
												
												<div class="form-group">
													<label class="col-md-3 control-label">Ticket SubType Code<span class="required"> * </span></label>
													<div class="col-md-4">
												<input type="text" class="form-control" name="ticketsubtype.ticket_sub_type_code" id="ticket_sub_type_code" value='<s:property value="ticketsubtype.ticket_sub_type_code" /> '>
												<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('ticket_sub_type_code').get(0)}" />
										</span>
												</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">Ticket SubType Name(Kannada)<span class="required"> * </span></label>
													<div class="col-md-4">
												<input type="text" class="form-control input-lg" name="ticketsubtype.ticket_sub_type_name_kannada" id="ticket_sub_type_name_kannada" value='<s:property value="ticketsubtype.ticket_sub_type_name_kannada" /> '>
												<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('ticket_sub_type_name_kannada').get(0)}" />
										</span>
												</div>
												</div>
						
												
						
							
							
										
							<div class="form-group">
													<label class="col-md-3 control-label">Status :<span class="required"> * </span></label>
													<div class="col-md-4">
													
												<select class="form-control input-small select2me" data-placeholder="Select..." name="ticketsubtype.status">
												<option value="ACTIVE">Active</option>
												<option value="INACTIVE">InActive</option>
											</select>
												<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('getStatus').get(0)}" />
										</span>
												<script>
											var orgTypeId ="<s:property value='ticketsubtype.status'/>";
											if(orgTypeId!=""){
 												document.getElementById(orgTypeId).selected= true; 
												
											}
											
										</script>
												</div>
												</div>
													<div class="form-group">
													<label class="col-md-3 control-label">Notes :</label>
													<div class="col-md-4">
													
													<s:textarea cssClass="form-control" name="ticketsubtype.notes" ></s:textarea>
													<script>
											var orgTypeId ="<s:property value='ticketsubtype.notes'/>";
											if(orgTypeId!=""){
 												document.getElementById(orgTypeId).selected= true; 
												
											}
											
										</script>
												</div>
												</div>
											
													<div class="form-actions fluid">
											<div class="col-md-offset-3 col-md-9">
												<button type="submit" class="btn blue"  onclick="return check();">Save</button>
												<button class="btn default" type="button" onclick="cancelform()">Cancel</button>
														</div>
										</div>
										</form>	
										</div>	</div>
						
					
						<!-- END FORM-->
						

					</div>
					
					<script type="text/javascript">
					
					function onLoad() {
						var options = {
							sourceLanguage : 'en', // or google.elements.transliteration.LanguageCode.ENGLISH,
							destinationLanguage : [ 'kn' ], // or [google.elements.transliteration.LanguageCode.HINDI],
							shortcutKey : 'ctrl+g',
							transliterationEnabled : true
						};
						var control = new google.elements.transliteration.TransliterationControl(
								options);
						var ids = [ "ticket_sub_type_name_kannada" ];
						control.makeTransliteratable(ids);
						
// 						var ids = [ "orgcodekanada" ];
// 						control.makeTransliteratable(ids);
					}

				google.setOnLoadCallback(onLoad);
					google.load("elements", "1", {
						packages : "transliteration"
					});
					google.load("language", "1");

// 					function convert() {
// 						google.language
// 								.transliterate(
// 										[ document.getElementById("orgcode").value ],
// 										"en",
// 										"kn",
// 										function(result) {
// 											if (!result.error) {
// 												//var container = document.getElementById("transliteration");
// 												if (result.transliterations
// 														&& result.transliterations.length > 0
// 														&& result.transliterations[0].transliteratedWords.length > 0) {
// 													//alert(result.transliterations[0].transliteratedWords[0]);
// 													document.getElementById("orgcodekanada").value = result.transliterations[0].transliteratedWords[0];
// 												}
// 											}
// 										});
// 					}
					function convertName() {
						google.language
								.transliterate(
										[ document.getElementById("ticket_sub_type_name").value ],
										"en",
										"kn",
										function(result) {
											if (!result.error) {
												//var container = document.getElementById("transliteration");
												if (result.transliterations
														&& result.transliterations.length > 0
														&& result.transliterations[0].transliteratedWords.length > 0) {
													//alert(result.transliterations[0].transliteratedWords[0]);
													document.getElementById("ticket_sub_type_name_kannada").value = result.transliterations[0].transliteratedWords[0];
												}
											}
										});
					}
				</script>
					
				</div>
			</div>
		</div>
	


</body>
</html>