<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<!DOCTYPE html>
<html>
<head>
<script>
</script>
<script src="assets/global/plugins/jquery-1.11.0.min.js"
	type="text/javascript"></script>
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
	document.forms[0].action = 'denomination.action';
	document.forms[0].submit();


}
function showDenomination()
{
	var t=document.getElementById('tickettype');
	var tickettype = t.options[t.selectedIndex].value;
	
	//alert("hello"+tickettype);
	if(tickettype==4)
	{
   
	var divId = document.getElementById('denomno'); 
	
    divId.style.display = 'none';
	} 
	else
	{
		var divId = document.getElementById('denomno'); 
		
	    divId.style.display = 'block';
	}
}
function getAllTickets()
{
	//alert("hello");
 
	// alert(document.getElementById('tickettype').options.length);
	  $.ajax({
          type: "post",
          async:false,
          url: '<%=request.getContextPath()%>/findAllTicketTypeAction',
          success: function(result) {
       	//   alert(result);
              document.getElementById('tickettype').innerHTML=result;
          }
	  });
	  
	  var prevType="<s:property value='denominationtype.ticketTypeManual.ticketTypeId'/>";
		//alert(prevType);
		 if(prevType!=""){
			 document.getElementById("ticketType"+prevType).selected=true;
			 if(prevType==4)
			{
				 var divId = document.getElementById('denomno'); 
					
				    divId.style.display = 'none';
			} 
			else
			{
						var divId = document.getElementById('denomno'); 
						
					    divId.style.display = 'block';
			}	 
			
			 
			
		 }
	
	}
	
$(document).ready(function(){
	
	/*  $("#percentId").val("0.0");
	 $("#lumpsumId").val(0); */
});

/* function getLumsum(){	
	//alert("hello");
	var val= document.getElementById('percentId').value;
	var result=0;
	if(val>0){
		//alert("hlloooo");
			
		 $("#lumpsumId").val(0);
	}
	}
	function getPercent(){	
		//alert("hello");
		var val= document.getElementById('lumpsumId').value;
		
		if(val>0){
						
			 $("#percentId").val(0.0);
		}

		}   */
</script>
<script type="text/javascript" src="//www.google.com/jsapi">

</script>
</head>

<body onload="getAllTickets()">
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "denomination.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
%>
<div class="page-content-wrapper">
		<div class="page-content">
		<%if (access.equalsIgnoreCase("Y")){ %>
				<%if (create.equalsIgnoreCase("Y")){ %>
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						DENOMINATION TYPE  <small></small>
					</h3>

					<!-- END PAGE TITLE & BREADCRUMB-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Create Denomination Type
							</div>
						</div>

						<div class="portlet-body form">
							<s:if test="hasActionErrors()">
								<div class="alert alert-danger">
									<button class="close" data-close="alert"></button>
									<span> <b><s:actionerror /></b>
									</span>
								</div>

							</s:if>

	

							<!-- BEGIN FORM-->

							<form action="saveDenomination.action" method="post" class="form-horizontal">
								
								<font color="red">
												<b><s:property value="msg" /></b></font>
											
								<div class="form-body">
								<div class="form-group">
										<label class="col-md-3 control-label">Ticket Type<font color="red">*</font></label>
										<div class="col-md-4">
											<!-- <div class="input-group"> -->
                                               
												<select class="form-control"
													name="denominationtype.ticketTypeManual.ticketTypeId" id="tickettype"
													onchange="showDenomination()" style="width:200px;">
													<!-- <option value="0" >select</option> -->
												</select>


											   <s:if test="fieldErrors.get('TicketType').size() > 0">
													<span style="color: red;"><b><s:property value="fieldErrors.get('TicketType').get(0)" /></b></span>
												</s:if>
												
												

										</div> 
										</div>
									</div>
								<div class="form-body">
									<div class="form-group" id="denomno" style="display:block">
										<label class="col-md-3 control-label">Denomination no<font
											color="red">*</font></label>
										<div class="col-md-4">
											<div class="input-group">
												<input type="text" name="denominationtype.denomtype"
													id="denomno" class="form-control"
													value="<s:property value="denominationtype.denomtype" />"
													>
												<s:if test="fieldErrors.get('denomno').size() > 0">
													<span style="color: red;"><b><s:property
															value="fieldErrors.get('denomno').get(0)" /></b></span>
												</s:if>
											</div>
										</div>
									</div>
									</div>
									<div class="form-body">
									<div class="form-group">
									
										<label class="col-md-3 control-label">Incentive Percentage:<font color="red">*</font></label>
									<div class="col-md-2">
											<input type="text" class="form-control input-small" id="percentId" maxlength="5"
												name="denominationtype.percentage" onblur="getLumsum()" onkeypress="getLumsum()"
												 value='<s:property value="denominationtype.percentage"/>'>
											<s:if test="fieldErrors.get('percentage').size() > 0"> 
		     								<span style="color:red;"><s:property value="fieldErrors.get('percentage').get(0)" /></span> 
 											</s:if> 
										</div>
										<div class="col-md-1">
											<label class="col-md-1 control-label"> OR</label>
											 																					
										</div>
										<div class="col-md-2">
										<label class="control-label">Incentive Amount:<font color="red">*</font></label>
										</div>	
									<div class="col-md-2">	
									   	<input type="text" class="form-control input-small" id="lumpsumId" maxlength="8"
												name="denominationtype.amount" onkeypress="getPercent()" onblur="getPercent()"
												value='<s:property value="denominationtype.amount"/>'>
											<s:if test="fieldErrors.get('lumpsum_amount').size() > 0"> 
		     								<span style="color:red;"><s:property value="fieldErrors.get('lumpsum_amount').get(0)" /></span> 
 											</s:if> 
										</div>
									</div>
								</div>
								
								<div class="form-body">
									<div class="form-group" id="denomno" style="display:block">
										<label class="col-md-3 control-label">Service Tax<font
											color="red"></font></label>
										<div class="col-md-4">
											<div class="input-group">
												<input type="text" name="denominationtype.servicetax" 
													id="servicetax" class="form-control"
													value="<s:property value="denominationtype.servicetax" />"
													>
												<s:if test="fieldErrors.get('servicetax').size() > 0">
													<span style="color: red;"><b><s:property
															value="fieldErrors.get('servicetax').get(0)" /></b></span>
												</s:if>
											</div>
										</div>
									</div>
									</div>
								
<%-- <select class="form-control" name="denominationtype.status"
												id="status" >
												<option id="active" selected="selected" value="ACTIVE" disabled="disabled">ACTIVE</option>
												<!-- <option id="deactive" value="INACTIVE">INACTIVE</option> -->



											</select>
 --%>
									<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">status<font
											color="red">*</font></label>
										<div class="col-md-4">  
                                            
											<select class="form-control" name="denominationtype.status"
												id="status" disabled style="width:200px;">
												<option id="active" selected="selected" value="ACTIVE" >ACTIVE</option>
												<!-- <option id="deactive" value="INACTIVE">INACTIVE</option> -->



											</select>
											<%-- <script>
												
															var status = "<s:property value="denominationtype.status"/>";
															/* if (status != undefined) {
																if (status == "ACTIVE"
																		|| status == "ACTIVE") {
																	document
																			.getElementById("active").selected = true;
																} else {
																	document
																			.getElementById("deactive").selected = true;
																}
															} */
															if((document
															.getElementById("ticket").value==0)||(document
																	.getElementById("status").value==-1))
																{
															document
																	.getElementById("status").value="";
																}
												</script> --%>

										</div>
									</div>
									</div>
									<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Remarks</label>
										<div class="col-md-4">
											<div class="input-group">
											<textarea name="denominationtype.notes"
													id="notes" class="form-control"	value="<s:property value="denominationtype.notes" />" maxlength="500"></textarea>
												<%-- <textarea name="denominationtype.notes" id="notes"
													class="form-control"
													value="<s:property value="denominationtype.notes" />"
													maxlength="11"/> --%>
												<s:if test="fieldErrors.get('Notes').size() > 0">
													<span style="color: red;"><b><s:property
															value="fieldErrors.get('Notes').get(0)" /></b></span>
												</s:if>
											</div>
										</div>
									</div>
									</div>
									
									<div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9">
											<button type="submit" class="btn blue">Save</button>
											<button type="button" id="cancel" class="btn default"
												onclick="goView()">Cancel</button>
											
										</div>
									</div>
								
								<s:token />
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
</body>
</html>

