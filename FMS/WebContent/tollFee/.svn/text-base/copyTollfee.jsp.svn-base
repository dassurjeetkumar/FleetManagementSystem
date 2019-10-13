<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script>
function checkInt(data){
	
	var amount=document.getElementById('amount').value;
	if(/[^0-9\s]/.test(amount)){
	 
	    document.getElementById('integerVal').innerHTML='Amount should be number';
	    document.getElementById('amount').value='';
	    return false;
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
<body>
	<div class="page-content-wrapper">
		<div class="page-content">

			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
				<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						TOLL FEE MASTER <small></small>
					</h3>
					<font color="green"><s:actionmessage/></font>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
				</div>
					<div class="portlet box grey-cascade">
					
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Copy Toll Fee Master
							</div>
							<div class="tools">
								
							</div>
						</div>
						<div class="portlet-body form">
							&nbsp;&nbsp;&nbsp;&nbsp;<font color="red"><span id="integerVal"></span></font>
							
							<form action="CopyTollfeeaction.action"  class="form-horizontal" method="post">
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
									<input id="amount" type="text" class="form-control" name="tollfee.amount" maxLength="10"
									value="<s:property value="tollfee.amount"/>">
									 <s:if test="fieldErrors.get('amount').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('amount').get(0)" /></span>
									</s:if> 
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
									<input id="versionNo" type="text" class="form-control" name="tollfee.versionNo" maxLength="11"
										value="<s:property value="tollfee.versionNo"/>" >
									 <s:if test="fieldErrors.get('versionNo').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('versionNo').get(0)" /></span>
									</s:if> 
								</div>
							    </div>
							   <div class="form-group">
									<label class="col-md-3 control-label">Bus Stop:<font color="red">*</font></label>
									<div class="col-md-4">										
									<!-- <input class="form-control input-lg"  id="busStop" name="project" type="text" onkeyup="getDropStops(this.value)" /> -->
									<s:if test="tollfee.busstop.stop_direction!= null">
									<input id="busStop" type="text" class="form-control" name="tollfee.busstop.busStopName" maxLength="11" disabled="true"
										 value="<s:property value="tollfee.busstop.busStopName"/>-<s:property value="tollfee.busstop.stop_direction"/>">
										</s:if>
										<s:else>
										<input id="busStop" type="text" class="form-control" name="tollfee.busstop.busStopName" maxLength="11" disabled="true"
										 value="<s:property value="tollfee.busstop.busStopName"/>">
										</s:else>
										</div>
									</div> 
								
							  
									<%-- <div class="form-group">
									<label class="col-md-3 control-label">Bus Stop:<font color="red">*</font></label>
									<div class="col-md-4">										
									<!-- <input class="form-control input-lg"  id="busStop" name="project" type="text" onkeyup="getDropStops(this.value)" /> -->
									<input id="busStop" type="text" class="form-control" name="tollfee.busstop.busStopName" maxLength="11"
										onkeyup="getDropStops(this.value)" value="<s:property value="tollfee.busstop.busStopName"/>">
										</div>
									</div> --%>
								
								<input  name="tollfee.busstop.id" type="hidden" value="<s:property value='tollfee.busstop.id'/>">
								<input type="hidden" name="tollfee.id" value="<s:property value='tollfee.id'/>" />
								<input type="hidden" name="tollfee.busstop.busStopName" value="<s:property value='tollfee.busstop.busStopName'/>" />												
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn blue">Save</button>
										<button type="button" class="btn default" onclick = "javascript:location.href='Tollfeeaction!view';">Cancel</button>
									</div>
								</div>

								
							<s:token/>
							</form>
							<!-- END FORM-->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

<script>
function getDropStops(id){
	//alert(id);
	//alert("hiii in ready");
	var result = "";
	
	
	if(id.length>=1){
	$.ajax({
	    type        : 'GET',
	    data :'json',
	    url         :  "<s:url action='GetBusDropStop'/>",
	    data: {
	        id: id
	        
	    },
	   
	    success: function(data){
	     
	        data =eval(data);
	        result=data;
	        
	    
	        $( "#busStop" ).autocomplete({
		        	minLength: 0,
		        	//change: function (event, ui) { locateMap(); },
		        	source: result,
		        	focus: function( event, ui ) {
		        	$( "#busStop" ).val( ui.item.busStopName );
		        	return false;
	        	},
	        	select: function( event, ui ) {
		        	$( "#busStop" ).val( ui.item.busStopName );	        	
		        	
		        	return false;
	        	}
	        	})
	        	.data( "ui-autocomplete" )._renderItem = function( ul, item ) {
	        	return $( "<li>" )
	        	.append( "<a>" + item.busStopName + "</a>" )
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