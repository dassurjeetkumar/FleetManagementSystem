 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<script src="assets/global/plugins/jquery-1.11.0.min.js" value='1'type="text/javascript"></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.9.1/themes/black-tie/jquery-ui.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
</head>
<style type="text/css">

</style>

<input type="hidden" name="id" id="id" />
<div class="page-content-wrapper">
	<div class="page-content">
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
					Route Map
				</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN EXAMPLE TABLE PORTLET-->
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i> Route Map
						</div>
					</div>
					<div class="portlet-body form">
						<b>
							<font color="green"> <s:actionmessage/></font>
							<font color="red"> <s:actionerror/></font>
						</b>
						<form action="editRoutMapPisAction" class="form-horizontal form-row-seperated" method="post">
							<div class="form-body">
								<div class="form-group" >
									<label class="control-label col-md-3"> Route No :<span class="required"> * </span></label>
									<div class="col-md-3">
                                    <input class="form-control" tabindex="1"  id="project" name="project" type="text" value='<s:property  value="project"/>'	onkeyup="JavaScript:getRoute(this.value)" onblur="getttmcdetails(this.value)"  value='<s:property value="routeNo"/>'  />
										<input id="project-id" type="hidden" name="project-id">
										<input id="project-id1" type="hidden">
								 		<input id="project-id2" type="hidden">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3"> TTMC Name : <span class="required"> * </span></label>
									<div class="col-md-3">
									<select class="select2_category form-control" name="ttmcid" id="ttmcid" value='<s:property  value="ttmcid"/>' onchange="getfloordetails()">
											<option value="0">Select</option>
									</select> 
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3"> BusStop Name : <span class="required"> * </span></label>
									<div class="col-md-3">
									<select class="select2_category form-control" name="bsstop" id="bsstop" value='<s:property  value="bsstop"/>' >
											<option value="0">Select</option>
									</select> 
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">Floor : <span class="required"> * </span></label>
									<div class="col-md-3">
									<select class="select2_category form-control" name="floorid" id="floorid" value='<s:property  value="floorid"/>' onchange="getbaydetails()">
											<option value="0">Select</option>
									</select> 
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">Bay : <span class="required"> * </span></label>
									<div class="col-md-3">
									<select class="select2_category form-control" name="bayid" id="bayid" value='<s:property  value="bayid"/>' onchange="getplatformdetails()" >
											<option value="0">Select</option>
									</select> 
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">Platform: <span class="required"> * </span></label>
									<div class="col-md-3">
									<select class="select2_category form-control" name="platformid" id="platformid" value='<s:property  value="platformid"/>' onchange="getservicedetails()">
											<option value="0">Select</option>
									</select> 
									</div>
								</div>
							
							<div class="form-group">
									<label class="control-label col-md-3">Service type: <span class="required"> * </span></label>
									<div class="col-md-3">
									 <select multiple  class="select2_category form-control" name="serviceid" id="serviceid" value='<s:property  value="serviceid"/>'>
                                           <option value="0">Select</option>
									</select> 
									</div>
								</div>
							
							
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn blue">Save</button>
										<button type="button" class="btn default" onclick="callCancell()">Cancel</button>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
function callCancell(){
	document.forms[0].action = "viewRoutePis.action";
	document.forms[0].submit();
}
function getRoute(val){
	var result = "";
	var availableTags=[];	
	$.ajax({
	    type : 'POST',
	    data :'json',
	    url  :  "<s:url action='RouteDropDownList1'/>",
	    data :{
	    	routename: val,
	    },
	    success: function(data){
	        data =eval(data);
	        result=data;
	        $( "#project" ).autocomplete({
		        	minLength: 0,
		        	source: result,
		        	focus: function( event, ui ) {
		        	$( "#project" ).val( ui.item.route_number  );
		        	return false;
	        	},
	        	select: function( event, ui ) {
		        	$( "#project" ).val( ui.item.route_number );
		        	$( "#project-id" ).val( ui.item.route_name );
		        	return false;
	        	}
	        }).data( "ui-autocomplete" )._renderItem = function( ul, item ) {
	        	return $( "<li>" )
	        	.append( "<a>" + item.route_number + "</a>" )
	        	.appendTo( ul );
	       };
	       var det=$("#project-id").val().split("-");
	       
	   	// getttmcdetails(det[0]);
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

function getttmcdetails(routeid)
{
	//alert("val....."+strUser);
	//$('#select2-chosen-2').html("Select");
	var e = document.getElementById("project");
	//alert(e);
	var det=$("#project-id").val().split("-")
 console.log(routeid);
 	       $.ajax({
 	           type: "post",
 	           async:false,
 	           url: '<%=request.getContextPath()%>/getTTMC?val='+det[0], 
	           success: function(result) {
 	               document.getElementById('ttmcid').innerHTML=result;
 	           }
 	       });
 	       
 	       
 	    
}

function getfloordetails()
{
	
	var e = document.getElementById("ttmcid");
	var strUser = e.options[e.selectedIndex].value;
	//alert("val....."+strUser);
	
	  $.ajax({
	           type: "post",
	           async:false,
	           url: '<%=request.getContextPath()%>/getBusStopRoute?val='+strUser, 
	           success: function(result) {
	               document.getElementById('bsstop').innerHTML=result;
	           }
	       });
	
	
 	       $.ajax({
 	           type: "post",
 	           async:false,
 	           url: '<%=request.getContextPath()%>/getfloorpis?val='+strUser, 
	           success: function(result) {
 	               document.getElementById('floorid').innerHTML=result;
 	           }
 	       });
}

function getbaydetails()
{
	
	var e = document.getElementById("floorid");
	var strUser = e.options[e.selectedIndex].value;
	//alert("val....."+strUser);
	
 	       $.ajax({
 	           type: "post",
 	           async:false,
 	           url: '<%=request.getContextPath()%>/getBays?val='+strUser, 
	           success: function(result) {
 	               document.getElementById('bayid').innerHTML=result;
 	           }
 	       });
}
function getplatformdetails()
{
	
	var e = document.getElementById("bayid");
	var strUser = e.options[e.selectedIndex].value;
	//alert("val....."+strUser);
	
 	       $.ajax({
 	           type: "post",
 	           async:false,
 	           url: '<%=request.getContextPath()%>/getPlatForm?val='+strUser, 
	           success: function(result) {
 	               document.getElementById('platformid').innerHTML=result;
 	           }
 	       });
}
function getservicedetails()
{
	
	var e = document.getElementById("platformid");
	var strUser = e.options[e.selectedIndex].value;
	//alert("val....."+strUser);
	
 	       $.ajax({
 	           type: "post",
 	           async:false,
 	           url: '<%=request.getContextPath()%>/getService?val='+strUser, 
	           success: function(result) {
 	               document.getElementById('serviceid').innerHTML=result;
 	           }
 	       });
}

</script>