<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE html>
<html>
<head>
 <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css"> 
<%-- <script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script> --%>
<link rel="stylesheet" href="/resources/demos/style.css">

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
		document.forms[0].action = 'breakdownhistory.action';
		document.forms[0].submit();


}


</script>
<script type="text/javascript" src="//www.google.com/jsapi">

</script>
</head> 

<body onload="getOrgType()">
	<div class="page-content-wrapper">
		<div class="page-content">
		<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
					BREAKDOWN  HISTORY<small></small>
					</h3>
					
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Edit BreakDown History
							</div>
							<div class="tools">
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
							
							<form action="editBreakDownDetails.action" class="form-horizontal"
								method="post">
								<div class="form-body">
                                   		
								<div class="form-group">
									<label class="control-label col-md-3">Date:<font
										color="red">*</font></label>
									<div class="col-md-4">
										<div class="input-group input-medium date date-picker"
											data-date-format="dd-mm-yyyy" data-date-viewmode="years">
											<input class="form-control" type="text" size="16"
												name="breakdownhistory.date" id="date"
												value='<s:property value="breakdownhistory.date"/>' readonly>
											<span class="input-group-btn">
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>

										</div>
										<%-- <s:if test="fieldErrors.get('voucher_date').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('voucher_date').get(0)" /></span>
											</s:if>  --%>
									</div>
								</div>
									
									
									<%-- <div class="form-group">
								<label class="col-md-3 control-label">
									Date<sup><font color="red">*</font></sup>
								</label>
								<div class="col-md-3">
									<div class="input-group input-medium date date-picker"
										data-date-format="dd/mm/yyyy" data-date-start-date="+0d" >
										<input type="text" class="form-control" readonly 
											name="breakdownhistory.date" id="date"> <span
											class="input-group-btn">
											<button class="btn default" type="button">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
										<script>
										var curr_date=new Date().toJSON().slice(0,10);
                                        curr_date=curr_date.split("-");
                                        curr_date=curr_date[2]+"/"+curr_date[1]+"/"+curr_date[0];
                                        $('#date').val(curr_date);
										</scrip t>
										
									</div>

								</div>
							</div> --%>
							 <div class="form-group">
									<label class="col-md-3 control-label">Vehicle Id:<font color="red">*</font></label>
									<div class="col-md-4">										
									<!-- <input class="form-control input-lg"  id="busStop" name="project" type="text" onkeyup="getDropStops(this.value)" /> -->
									  <input id="vehicle" type="text" class="form-control" name="breakdownhistory.vehicle.vehicleRegistrationNumber" maxLength="11"
					 					onkeyup="getDropStops1(this.value)" value="<s:property value="breakdownhistory.vehicle.vehicleRegistrationNumber"/>">
										
										<input id="vehicle-id" name="breakdownhistory.vehicle.vehicleId" type="hidden" value="<s:property value='breakdownhistory.vehicle.vehicleId'/>">
										<input type="hidden" name="breakdownhistory.vehicle.vehicleRegistrationNumber" value="<s:property value='breakdownhistory.vehicle.vehicleRegistrationNumber'/>" /> 									
										<input type="hidden" name="breakdownhistory.breakdownId" id="breakdownhistoryid" 
				                           value="<s:property value="breakdownhistory.breakdownId" />" maxlength="200">
										</div>
									</div>
									<div class="form-group">
									<label class="col-md-3 control-label">Type:<font color="red">*</font></label>
									<div class="col-md-4">										
									<!-- <input class="form-control input-lg"  id="busStop" name="project" type="text" onkeyup="getDropStops(this.value)" /> -->
									  <input id="breakdownType" type="text" class="form-control" name="breakdownhistory.breakDownTypeDetails.breakdown_name" maxLength="11"
										onkeyup="getDropType1(this.value)" value="<s:property value="breakdownhistory.breakDownTypeDetails.breakdown_name"/>">
										
										<input id="breakdowntype-id" name="breakdownhistory.breakDownTypeDetails.breakdown_type_Id" type="hidden" value="<s:property value='breakdownhistory.breakDownTypeDetails.breakdown_type_Id'/>">
										<input type="hidden" name="breakdownhistory.breakDownTypeDetails.breakdown_name" value="<s:property value='breakdownhistory.breakDownTypeDetails.breakdown_name'/>" /> 									
										</div>
									</div>
															
                            			
 									
									<div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9">
											<button type="submit" class="btn blue">Save</button>
											<button type="button" id="cancel" class="btn default" onclick="goView()">Cancel</button>
											<h1 class="intro">
												<s:property value="msg" />
											</h1>
										</div>
									</div>
								</div>
								<s:token />
							</form>
							<!-- END FORM-->
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
	    type        : 'GET',
	    data :'json',
	    url         :  "<s:url action='GetVehicleId'/>",
	    data: {
	        id: id
	        
	    },
	   
	    success: function(data){
	     
	        data =eval(data);
	        result=data;
	        
	       //alert(data);
	        $( "#vehicle" ).autocomplete({
	        	
		        	minLength: 0,
		        	//change: function (event, ui) { locateMap(); },
		        	source: result,
		        	focus: function( event, ui ) {
		        	$( "#vehicle" ).val( ui.item.vehicleRegistrationNumber );
		        	
		        	return false;
	        	},
	        	select: function( event, ui ) {
	        		//alert(ui.item.vehicleId);
		        	$( "#vehicle" ).val( ui.item.vehicleRegistrationNumber );	
		        	$( "#vehicle-id" ).val( ui.item.vehicleId);
		        	/* $( "#project-id" ).val( ui.item.longitude );
		        	$( "#project-id1" ).val( ui.item.latitude ); */
		        	
		        	return false;
	        	}
	        	})
	        	.data( "ui-autocomplete" )._renderItem = function( ul, item ) {
	        	return $( "<li>" )
	        	.append( "<a>" + item.vehicleRegistrationNumber + "</a>" )
	        	.appendTo( ul );
	        	};
	        	
	       
	    },
	    select: function (event, ui) {

	     //  alert("selected!");
	    },
	    change: function (event, ui) {

	       // alert("changed!");
	    },
	    error : function(xhr, errmsg) {
	    	
	    	//alert("No values found..!!"+errmsg);
	    	}
	});
}
	
} 
function getDropType1(id){
	//alert(id);
	//alert("hiii in ready");
	var result = "";
	//alert(id.length);
	
	if(id.length>=0){
		//alert(id.length);
	$.ajax({
	    type        : 'GET',
	    data :'json',
	    url         :  "<s:url action='GetBreakDownId'/>",
	    data: {
	        id: id
	        
	    },
	   
	    success: function(data){
	    //  alert(data);
	        data =eval(data);
	        result=data;
	        
	    
	        $( "#breakdownType" ).autocomplete({
	        	
		        	minLength: 0,
		        	//change: function (event, ui) { locateMap(); },
		        	source: result,
		        	focus: function( event, ui ) {
		        	$( "#breakdownType" ).val( ui.item.breakdownname );
		        	
		        	return false;
	        	},
	        	select: function( event, ui ) {
	        		//alert( ui.item.breakdowntypeId);
		        	$( "#breakdownType" ).val( ui.item.breakdownname );	
		        	$( "#breakdowntype-id" ).val( ui.item.breakdowntypeId );
		        	/* $( "#project-id" ).val( ui.item.longitude );
		        	$( "#project-id1" ).val( ui.item.latitude ); */
		        	
		        	return false;
	        	}
	        	})
	        	.data( "ui-autocomplete" )._renderItem = function( ul, item ) {
	        	return $( "<li>" )
	        	.append( "<a>" + item.breakdownname + "</a>" )
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

