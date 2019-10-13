<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<head>
<sx:head />
</head>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css"/> -->
	<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
	<link rel="stylesheet" href="/resources/demos/style.css">
	<script src="assets/global/plugins/jquery-ui-1.9.2.js" type="text/javascript"></script>
<%-- 	<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=geometry,places,drawing"></script> --%>
<script src="https://maps.googleapis.com/maps/api/js?client=gme-trimaxitinfrastructure&sensor=false&libraries=places,drawing&signature=CpSOeA5B6SzC2rxqlzd2JwenGX0="></script>
	<script src="assets/admin/pages/scripts/create_busstopgroup.js" type="text/javascript"></script>
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
 <style>
div#resizable1 {
    height: 500px;
    overflow-y:auto;
    /* overflow-x:auto; */
   
    padding: 1px;
}
</style>

<div class="page-content-wrapper">
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ShowBusStopGroup.action");
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
	<%if (access.equalsIgnoreCase("Y")){ %>
		<div class="form-group">
		<div class="row">
		<%if (create.equalsIgnoreCase("Y")){ %>
		<div class="col-md-4">
	
				<input class="form-control input-lg" placeholder="Enter Bus Stop Name to Search" id="project" name="project" type="text" onkeyup="getDropStops(this.value)"   onKeyPress="enterpressalert(event, this)" onblur="locateMap()" /> 
				
				<input id="project-id" type="hidden">
				 <input id="project-id1" type="hidden">
				 <input id="project-id2" type="hidden">
			</div>
			<div class="col-md-2">
		<button type="button" id="go" aria-hidden="true"  class="btn white" >Toggle Size</button>
				<!-- <button id="go">Toggle Size</button> -->
			</div>
			<div class="col-md-6">
			<div class="portlet-body">
			<ul class="list-group">
			<li class="list-group-item bg-white" id="selectbusstopname"></li>
			</ul>
			</div>
			</div>
			
			<script>
// Using multiple unit types within one animation.
var flag=1;
$( "#go" ).click(function() {
	//alert(flag);
	
	if(flag==0){
		$( "#resizable" ).animate({
			width: "68%",
			}, 1500 );
			
		$( "#resizable1" ).animate({
	width: "25%",
	}, 1500 );
	
	}
	else
	{
		$( "#resizable" ).animate({
			width: "25%",
			}, 1500 );
			
		$( "#resizable1" ).animate({
	width: "68%",
	}, 1500 );
	
	}
	if(flag==0){
		flag=1;
	}else{
		flag=0;
	}
});
</script>
					</div>
		<div class="row">
		<div  id="resizable" class="col-md-8">
		
		<div class="portlet solid white" >
				
			</div> 
			<div class="portlet-body"  >
				<div id="gmap_marker" class="gmaps"  >
				</div>
			</div>
		</div>
			<!-- </div> -->
			<form class="form-horizontal" role="form" method="get">
			<div  id="resizable1" class="col-md-4">
		
		<div class="portlet box white" >
						
						<div class="portlet-body" >
							<div id="accordion3" class="panel-group accordion" >
								<div class="panel panel-default" >
									<div class="panel-heading" overflow:auto;" >
										<h4 class="panel-title">
										<a class="accordion-toggle accordion-toggle-styled" data-toggle="collapse" data-parent="#accordion3" href="#collapse_3_1"> Bus Stop Mapping/Grouping </a>
										</h4>
									</div>
									<div class="panel-collapse collapse in" id="collapse_3_1"  style="color:#000000;overflow:auto;">
										<div class="panel-body">
											<div class="form-body">
											
											<div class="table-responsive" style="color:#000000">
											<table class="table table-hover">
											<tr><th align="right">Group Type:</th>
											<td>
											<select class="form-control input-sm" id="group_type"
											name="group_type">
											<s:iterator value="listGroupType" id="listGroupType">
											<option value="<s:property value="group_type_id"/>"><s:property value="group_type_name"/></option>
											</s:iterator>
											
										</select>
											</td>
											</tr>
											<tr id="showbusstopname"><th align="right">Bus Stop Name: </th><td><input class="form-control input-sm" placeholder="" type="text" id="bus_stop_name" name="bus_stop_name" maxlength="200" readonly></td></tr>
											<tr id="showbusstopname"><td style="display:none"><input class="form-control input-sm" placeholder="" type="text" id="start_stop_id" name="start_stop_id" maxlength="200" readonly></td></tr>
											<tr id="showgroupname"><th align="right">Group Name:<font color="red">*</font></th><td><input class="form-control input-sm" placeholder="" type="text" id="group_name" name="group_name" maxlength="100"></td></tr>
											
											<script>
											$('#bus_stop_name').val("");
											$('#group_name').val("");
											</script>
											<tr>
											<td colspan="2">
											<table class="table table-hover" id="listbusstop">
											<th colspan="2">Group Bus Stop List View</th>
											<tr><th>Sr. No.</th><th>Bus Stop Name</th><th></th></tr>
											</table>
											</td>
											</tr>
											</table>	
											</div>
										</div>
									</div>
								</div>
								
								
								<div class="form-actions right1">
								<button type="button" id="done" aria-hidden="true"  class="btn blue" onclick="submitAjaxForm()">Save</button>
								
								<a href="ShowBusStopGroup.action" class="btn grey"  id="cancel"> <!-- //onclick="callEdit()" -->
								 Cancel </a>
								 
							</div>
							</div>
						</div>
					</div>
			</div>
			
					</div>
					</form>
			<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>
			<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>
			
			

		</div>
		

		
	</div>
</div>















<script>
$('#showbusstopname').hide();
$('#group_type').on("change",function() {
	if($('#group_type').val()=="1"){
		$('#showgroupname').show();
		$('#showbusstopname').hide();
	}else{
		$('#showgroupname').hide();
		$('#showbusstopname').show();
		$('#group_name').val("");
	}
});



var idval="'0'";

function resetfo(){
	//alert("hiii");
	location.reload(true);
}
function enterpressalert(e, textarea){
	var code = (e.keyCode ? e.keyCode : e.which);
	if(code == 13) { //Enter keycode
	 //alert('enter press');
	//alert($("#project-id2").val());
		locateMap();
	}
	}
	
	
	
function locateMap(){
	var lag=document.getElementById("project-id").value;
	var lat=document.getElementById("project-id1").value;
	var id=document.getElementById("project-id2").value;
	var stop_type=0;
	var loc=lat+","+lag;
	var zoom=18;
	//alert("aaa"+loc);
	if(id==""){
		//alert("Invalid Stop");
		return false;
	}
	idval.indexOf(id);
	//alert(idval.indexOf(id));
	if(idval.indexOf(id)!=-1){
		//alert("hiiii"+lag+lat);
		
		map.setCenter(new google.maps.LatLng(lat,lag));
		map.setZoom(18);
		//initialize("",loc,zoom);
	}else{
	$.ajax({
	    type        : 'GET',
	    url         :  "<s:url action='GetBusStopMapLocate'/>",
	    data: {
	        id: id,
	        stop_type: stop_type,
	    },
	   
	    success: function(data){
	       
	        result=data;
	     
	      idval=idval+",'"+id+"'";
	      var names=idval.split(",");
	      var uniqueNames= [];
	  	$.each(names, function(i, el){
	  	    if($.inArray(el, uniqueNames) === -1) uniqueNames.push(el);
	  	});
	  	idval=uniqueNames.join();
	  	// alert('successful'+loc);
	        initialize(result,loc,zoom);
	    },
	    error : function(xhr, errmsg) {
	    	//alert("Session out error..Please login again!!");
	    	}
	});
	}
	document.getElementById("project-id").value="";
	lat=document.getElementById("project-id1").value="";
	id=document.getElementById("project-id2").value="";
}


function getDropStops(id){
	//alert(id);
	//alert("hiii in ready");
	var result = "";
	var availableTags=[];	
	var stop_type=0;
	if(id.length>=0){
	$.ajax({
	    type        : 'GET',
	    data :'json',
	    url         :  "<s:url action='GetBusDropStop'/>",
	    data: {
	        id: id,
	        stop_type: stop_type,
	    },
	   
	    success: function(data){
	        data =eval(data);
	        result=data;
	       
	        $( "#project" ).autocomplete({
		        	minLength: 0,
		        	//change: function (event, ui) { locateMap(); },
		        	source: result,
		        	focus: function( event, ui ) {
		        	$( "#project" ).val( ui.item.busStopName );
		        	return false;
	        	},
	        	select: function( event, ui ) {
		        	$( "#project" ).val( ui.item.busStopName );
		        	$( "#project-id" ).val( ui.item.longitude );
		        	$( "#project-id1" ).val( ui.item.latitude );
		        	$( "#project-id2" ).val( ui.item.id );
		        	
		        	return false;
	        	}
	        	})
	        	.data( "ui-autocomplete" )._renderItem = function( ul, item ) {
	        	return $( "<li>" )
	        	.append( "<a>" + item.busStopName + "</a>" )
	        	.appendTo( ul );
	        	};
	        	
	        //	$( "#project" ).on( "autocompletechange", function( event, ui ) { locateMap(); } );
	       // initialize(result);
	    },
	    select: function (event, ui) {

	       // alert("selected!");
	    },
	    change: function (event, ui) {

	       // alert("changed!");
	    },
	    error : function(xhr, errmsg) {
	    	//alert("Session out error..Please login again");
	    	}
	});
}
	
}





</script>
<script>


getRoadType();
var uniqueid=0;platformcounter=0;busplatid=0;
function addstop(id,trid, busstationid){
	//alert("hiiiiii"+id);
	$('#trid').val(trid);
	$('#busstationid').val(busstationid);
	$('#orgchart').val(busstationid);
	$('#busid1').val(id);
	getfloor();
	busplatid=id;
	document.getElementById("orgchart").selectedIndex = 0;
	document.getElementById("floor").selectedIndex = 0;
	document.getElementById("bay").selectedIndex = 0;
	//document.getElementById("platformdiv").value="";
	$( "#platformdiv" ).empty();
   // $("#platform_details tbody tr").remove(); 
   $("#platform_details tr:gt(0)").remove();
   uniqueid=0;
  // alert("hiiiiii"+tableplatarr);
	for(s=0;s<tableplatarr.length;s++){
		//alert(tableplatarr[s].id+"======>"+id+"ID:- "  + tableplatarr[s].planame + " Name:- "  + tableplatarr[s].sername );
		if(id==tableplatarr[s].id){
		$('#platform_details tr').last().after('<tr><td colspan="1">'+tableplatarr[s].planame+'</td><td>'+tableplatarr[s].sername+'</td>');
		}
	}
   // $('#platform_details').children( 'tr:not(:first)' ).remove();


//	$( "#" ).empty();
	
	//document.getElementById("bay").selectedIndex = 0;
}


</script>



<script>

function getStops(){	
	//alert("hiiii");
	var uniqueNames = [];
	var names=idval.split(",");
	$.each(names, function(i, el){
	    if($.inArray(el, uniqueNames) === -1) uniqueNames.push(el);
	});
	idval=uniqueNames.join();
	$.ajax({
	    type        : 'POST',
	    data: {
	        lat: '12',
	        lng: '77',
	        
	    },
	    url         :  "<s:url action='GetRouteStopMap'/>",
	   
	    success: function(data){
	        //alert('successful'+data);
	        result=data;
	        var zoom="8";
	        var clat= '12.97928309';
	        var clang=	'77.57205963';
	        var center = clat+","+clang;
	        var locations=result.split("@@@");
	        for (i = 0; i < locations.length-1; i++) {
	        	var dloc=locations[i].split("|");
	        	 for(var v=0;v<dloc.length;v++){
	        		 if(v==(dloc.length-1)){
	        			 idval=idval+",'"+dloc[3]+"'";
	        		 }else{
	        			 idval=idval+",'"+dloc[3]+"'";
	        		 }
	        		// idval=idval+"'"+dloc[3]+"',"
	        	 }
	        }
	        <%
	       // session.removeAttribute("centerlate");
	       // session.removeAttribute("centerlange");
	        %> 
	        //alert(clat+"===="+clang);
	        initialize(result,center,13);
	    },
	    error : function(xhr, errmsg) {
	    	//alert("Session out error..Please login again");
	    	}
	});
}



function getStopsLoad(lat,lng){
	var uniqueNames = [];
	var names=idval.split(",");
	$.each(names, function(i, el){
	    if($.inArray(el, uniqueNames) === -1) uniqueNames.push(el);
	});
	idval=uniqueNames.join();
	//alert(idval);
	$.ajax({
	    type        : 'POST',
	   /*  data: {
	        lat: lat,
	        lng: lng,
	        idvald:idval,
	        
	    }, */
	    data:'lat='+ lat+	
	    '&lng='+ lng+
	    '&idvald='+ idval,
	    url         :  "<s:url action='GetBusStopRoute'/>",
	   
	    success: function(data){
	       // alert('successful'+data);
	        result=data;
	        var zoom="15";
	       // var clat= '<%= request.getSession().getAttribute("centerlat") %>';
	       // var clang=	'<%= request.getSession().getAttribute("centerlang") %>';
	        var center = lat+","+lng;
	        //idvald=
	        <%
	        //session.removeAttribute("centerlate");
	        //session.removeAttribute("centerlange");
	        %> 
	        //alert(clat+"===="+clang);
	        var locations=result.split("@@@");
	        for (i = 0; i < locations.length-1; i++) {
	        	var dloc=locations[i].split("|");
	        	 for(var v=0;v<dloc.length;v++){
	        		 if(v==(dloc.length-1)){
	        			 idval=idval+",'"+dloc[3]+"'";
	        		 }else{
	        			 idval=idval+",'"+dloc[3]+"'";
	        		 }
	        		// idval=idval+"'"+dloc[3]+"',"
	        	 }
	        }
	        //alert(idval);
	        initialize(result,center,13);
	    },
	    error : function(xhr, errmsg) {
	    	//alert("Session out error..Please login again");
	    	}
	});
}


jQuery(document).ready(function() {
	getStops();
	
	

});



function submitAjaxForm(){

	
	var group_type_id=$('#group_type').val();
	var bus_stop_group=[];
	var group_name="";
	var start_stop_id="";
	if(group_type_id==1){
		$(".busstopid").each(function() {
			bus_stop_group.push($(this).html());
			});	
		group_name=$('#group_name').val();
		if(group_name.length==0){
			alert("Please Enter Bus Stop Group Name");
			return false;
		}else{
			if(group_name.trim().length==0){
				alert("Please Enter Bus Stop Group Name");
				return false;
			}
		}
		start_stop_id=$('#start_stop_id').val();
	
	}else{
			
		$(".busstopid").each(function() {
			bus_stop_group.push($(this).html());
			});	
		group_name="";
		start_stop_id=$('#start_stop_id').val();
		
		}
	if(bus_stop_group.length==0){
		alert("Please Select atleast One Stop");
		return false;
	}
	if(!checkIfArrayIsUnique(bus_stop_group) ){
		alert('Bus Stop Group contains duplicate Values');
		return false;
	}
	
$.ajax({
    type        : 'POST',
    data: 'group_type_id='+ group_type_id+	
    '&group_name='+ group_name+
    '&bus_stop_group='+ bus_stop_group+
    '&start_stop_id='+ start_stop_id
	,
	
	

    url         :  "<s:url action='SaveBusStopGroup'/>",
   
    success: function(data){
    	if(data=="Saved Successfully"){ 
    		///alert(data);
    		if(group_type_id==1){
    			alert("Bus Stop Group "+group_name+" Created Successfully");
    		}else{
    			alert("Trip Mapping for "+$('#bus_stop_name').val()+" Created Successfully");
    		}
    		document.getElementById("cancel").click();
    	}else{
    		alert(data);
    	}
    	
        
    },
    error : function(xhr, errmsg) {
    	alert("Your Login Session Has Expired. Please Login Again.");
    	}
});
}

function checkIfArrayIsUnique(myArray) 
{
    for (var i = 0; i < myArray.length; i++) 
    {
        if (myArray.indexOf(myArray[i]) !== myArray.lastIndexOf(myArray[i])) { 
            return false; 
        } 
    } 
    return true;   // this means not unique
}
</script>

