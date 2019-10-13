<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<!DOCTYPE html>
<html>
<head>
<script>
function isInt(n) {
	   return typeof n === 'number' && n % 1 == 0;
	}
function checkInt(data){
	
	var amount=document.getElementById('amount').value;
	if(/[^0-9\s]/.test(amount)){
	 
	    document.getElementById('integerVal').innerHTML='Amount should be number';
	    document.getElementById('amount').value='';
	}
	else
	{
	document.getElementById('integerVal').innerHTML='';
   return true;
}	

}

</script>
<title>Toll fee master</title>
</head>
<link rel="stylesheet" href="/resources/demos/style.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css"> 

<body>
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "Tollfeeaction!view");
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
<%if (create.equalsIgnoreCase("Y")){%>
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
				<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						TOLL FEE MASTER <small></small>
					</h3>
					<b><FONT color="green"><s:actionmessage /></FONT></b>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
				</div>
				
					<div class="portlet box grey-cascade">
					
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Create Toll Fee Master
							</div>
							<div class="tools">
								
							</div>
						</div>
						<div class="portlet-body form">
							&nbsp;&nbsp;&nbsp;&nbsp;
							
							<form action="AddTollfeeaction.action"  class="form-horizontal" method="post">
								<font color="red"><s:actionerror/></font>  <br>
								
								
									<div class="form-group">
										<label class="col-md-3 control-label" 
										>Service Type:<font color="red">*</font></label>

										<div class="col-md-4">
										
									<s:select list="serviceTypeMap" id="serId" name="tollfee.servicetype.service_type_id" 
									cssClass="select2_category form-control" headerKey="0" headerValue="Select"></s:select>

											<s:if test="fieldErrors.get('serId').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('serId').get(0)" /></span>
											</s:if>	 

										</div>
									</div>
								
								
								<div class="form-group">
								<label class="col-md-3 control-label">Amount:<font color="red">*</font></label>
								<div class="col-md-4">
									<input id="amount" type="text" class="form-control" name="tollfee.amount" maxLength="8"  
									
										value="<s:property value="tollfee.amount"/>">
									 <s:if test="fieldErrors.get('amount').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('amount').get(0)" /></span>
									</s:if> 
									<font color="red"><span id="integerVal"></span></font>
								</div>
							    </div>
								
								
								 <div class="form-group">
									<label class="control-label col-md-3">Effective Start Date:<font color="red">*</font></label>
									<div class="col-md-4">
										<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd/mm/yyyy"
															data-date-viewmode="years" data-date-start-date="+0d">
											<input type="text" size="16" readonly class="form-control"
												id="effectiveStartDate"
												name="tollfee.effect_start_date"
												value="<s:property value='tollfee.effect_start_date' />" > 
												<span class="input-group-btn" >
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>		
											<script>
											var name = jQuery.trim($("#effectiveStartDate").val());
											if ((name.length == 0))
												{
										 var curr_date=new Date().toJSON().slice(0,10);
                                        curr_date=curr_date.split("-");
                                        curr_date=curr_date[2]+"/"+curr_date[1]+"/"+curr_date[0];
                                        $('#effectiveStartDate').val(curr_date); 
												}
                                      /*   $('#startdate').val($.datepicker.formatDate( "dd/mm/yy", new Date()));
                                        alert($('#startdate').val); */
										</script>				
										</div>
										<s:if test="fieldErrors.get('effectiveStartDate').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('effectiveStartDate').get(0)" /></span>
										</s:if>
									</div>
								</div>
								
								<div class="form-group">
									<label class="control-label col-md-3">Effective End Date:<font color="red">*</font></label>
									<div class="col-md-4">
									<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd/mm/yyyy"
															data-date-viewmode="years" data-date-start-date="+0d">
											<input type="text" size="16" readonly class="form-control"
												id="effectiveEndDate" name="tollfee.effect_end_date"
												value="<s:property value='tollfee.effect_end_date' />" >
											<span class="input-group-btn">
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
											
										</div>
										<s:if test="fieldErrors.get('effectiveEndDate').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('effectiveEndDate').get(0)" /></span>
										</s:if>
									</div>
								</div>
							  
								<div class="form-group">
								<label class="col-md-3 control-label">Version Number:<font color="red">*</font></label>
								<div class="col-md-4">
									<input id="versionNo" type="text" class="form-control" name="tollfee.versionNo" maxLength="5"
										value="<s:property value="tollfee.versionNo"/>" >
									 <s:if test="fieldErrors.get('versionNo').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('versionNo').get(0)" /></span>
									</s:if> 
									<font color="red"><span id="integerVal1"></span></font>
								</div>
							    </div>
							   <%--  <div class="form-group">
										<label class="col-md-3 control-label" 
										>Bus Stop:<font color="red">*</font></label>

										<div class="col-md-4">
										
									<s:select list="busTypeMap" id="busId" name="tollfee.busstop.id" 
									cssClass="select2_category form-control" headerKey="0" headerValue="Select"></s:select>

											<s:if test="fieldErrors.get('busId').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('busId').get(0)" /></span>
											</s:if>	 

										</div>
									</div> --%>
								
									 <div class="form-group">
									<label class="col-md-3 control-label">Bus Stop:<font color="red">*</font></label>
									<div class="col-md-4">										
									<!-- <input class="form-control input-lg"  id="busStop" name="project" type="text" onkeyup="getDropStops(this.value)" /> -->
									  <input id="busStop" type="text" class="form-control" name="tollfee.busstop.busStopName" maxLength="11"
										onkeypress="getDropStops1(this.value)" value="<s:property value="tollfee.busstop.busStopName"/>">
										 <s:if test="fieldErrors.get('busStop').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('busStop').get(0)" /></span>
									</s:if>
										
										<input id="busStop-id" name="tollfee.busstop.id" type="hidden" value="<s:property value='tollfee.busstop.id'/>">
										<input id="stop_direction" type="hidden" name="tollfee.busstop.stop_direction" value="<s:property value='tollfee.busstop.stop_direction'/>" />
<%-- 										<input type="hidden" name="tollfee.busstop.busStopName" value="<s:property value='tollfee.busstop.busStopName'/>" /> 									 --%>
										</div>
									</div>
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn blue">Save</button>
										<button type="button" class="btn default" onclick = "javascript:location.href='Tollfeeaction!view';">Cancel</button>
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
<script>
function getDropStops1(id){
	//alert(id);
	//alert("hiii in ready");
	var result = "";
	//alert(id.length);
	
	if(id.length>=0){
		//alert(id.length);
	$.ajax({
	    type  : 'GET',
	    data :'json',
	    url         : "<s:url action='GetBusStopName'/>",
	    data: {
	        id: id
	        
	    },
	   
	    success: function(data){
	     
	        data =eval(data);
	        result=data;
	       // alert(data);
	    
	        $( "#busStop" ).autocomplete({
	        	
		        	minLength: 0,
		        	//change: function (event, ui) { locateMap(); },
		        	source: result,
		        	focus: function( event, ui ) {
		        	$( "#busStop" ).val( ui.item.busStopName+''+ ui.item.stop_direction );
		        	
		        	return false;
	        	},
	        	select: function( event, ui ) {
	        		
		        	$( "#busStop" ).val( ui.item.busStopName+''+ ui.item.stop_direction );	
		        	$( "#busStop-id" ).val( ui.item.id );
		        	$( "#stop_direction" ).val( ui.item.stop_direction );
		        	/* $( "#project-id" ).val( ui.item.longitude );
		        	$( "#project-id1" ).val( ui.item.latitude ); */
		        	
		        	return false;
	        	}
	        	})
	        	.data( "ui-autocomplete" )._renderItem = function( ul, item ) {
	        	return $( "<li>" )
	        	 .append( "<a>" + item.busStopName +""+ item.stop_direction +"</a>" )
	        	.appendTo( ul );
	        	};
	        	
	    },
	    select: function (event, ui) {

	        alert("selected!");
	    },
	    change: function (event, ui) {

	        alert("changed!");
	    },
	    error : function(xhr, errmsg) {
	    	alert("No values found..!!"+errmsg);
	    	}
	});
}
} 

</script>

</body>
</html>