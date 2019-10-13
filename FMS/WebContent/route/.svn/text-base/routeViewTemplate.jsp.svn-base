<!--
Author : Satya
Date : 09-09-2015 5:20PM
Purpose : For View Route wise Bus Stops 
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script src="assets/global/plugins/jquery-1.11.0.min.js" value='1'type="text/javascript"></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.9.1/themes/black-tie/jquery-ui.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
</head>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "RouteViewTemplate.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
%>
<body>
	<div class="page-content-wrapper">
		<div class="page-content">
		<%if (access.equalsIgnoreCase("Y")){ %>
		<%if(read.equalsIgnoreCase("Y")){ %>
			<div class="row">
				<div class="col-md-12">
					<h3 class="page-title">
						ROUTE <small></small>
					</h3>
				</div>
			</div>
			<div class="tab-content">
				<div class="tab-pane active">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Route Points View
							</div>
						</div>
						<div class="portlet-body">
							<div class="row">
								<b>
									<font color="green"> <s:actionmessage/></font>
									<font color="red"><s:actionerror/></font>		
								</b>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label class="col-md-2 control-label">Route Number</label>
										<div class="col-md-3">
											<input class="form-control" tabindex="1" placeholder="Enter Route No to Search" id="project" name="project" type="text"	onkeyup="getRoute(this.value)"   />
											<input id="project-id" type="hidden">
					 						<input id="project-id1" type="hidden">
					 						<input id="project-id2" type="hidden">
										</div>
										<button type="button" class="btn default" onclick="getRouteData()" style="position: static;">Get Data</button>
									</div>
								 	<div class="form-group">
										<p id='loading'>
										</p>
										<table id='stopNames' class="table table-striped table-bordered table-hover" style='width:50%'>
											<tr>
												<th>Sr No</th>
												<th>Bus Stop name</th>
												<th>Fare Stage</th>
											</tr>
										</table>
										</div>
									<div class="form-group">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>		
<script>	
function getRoute(val){
	var result = "";
	var availableTags=[];	
	$.ajax({
	    type : 'GET',
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

function getRouteData(){
	var det=$("#project-id").val().split("-");
	routeNo = $("#project").val();
	routePointsArray = [];
	aaa = 0;
	var data = $.ajax({
		type: "get",
   	   	async:false,
   	   	data : 'json',
		url :  "<%=request.getContextPath()%>/ShowRouteAjax?routeid="+ det[0]+"&start_point="+ det[1]+"&end_point="+ det[2],
		beforeSend:function(){
			$("#stopNames tr").slice(1).remove();
			$("#bus_stop_names").html('');
			$("#loading").html('<img src="<%=request.getContextPath()%>/inventory/loading-icon.gif" height="55px" width="55px" style="padding-right:2px"/><h4>loading.. </h4>');
		},
		complete:function(){
			$("#loading").html('');
		},
	    success: function(data){
	    	
	    }
	}).responseText;
	result=JSON.parse(data);
    console.log(result);
	categories =[];
	var i = 0;
	var routeMapList = result.routemap;
	var routePointList = result.routepoint;
	console.log(routePointList);
	for(var j=0;j<routeMapList.length;j++ ){
		if(getPonitType(routeMapList[j][1],routePointList)!=13 && getPonitType(routeMapList[j][1],routePointList)!=2){
			i++;
			routePointsArray.push({"busStopId":routeMapList[j][1],"accDistance":parseInt(aaa)});
			$("#stopNames").append("<tr>");
			$("#stopNames tr:last").append("<td>"+i+"</td><td>"+result.routepoint[j][6]+"</td><td>"+result.routepoint[j][4]+"</td>");
			$("#stopNames tr:last").append("</tr>");
			stopNames
		}
		aaa +=  routeMapList[j][7]/10;
	}
	i++;
	routePointsArray.push({"busStopId":routeMapList[routeMapList.length-1][2],"accDistance":parseInt(aaa)});
	$("#stopNames").append("<tr>");
	$("#stopNames tr:last").append("<td>"+i+"</td><td>"+result.routepoint[j][6]+"</td><td>"+result.routepoint[j][4]+"</td>");
	$("#stopNames tr:last").append("</tr>");
	console.log(routePointsArray);
	route_id = det[0];
}
function getPonitType(pointId,points){
	 var pointType = 0;
	 for( var i=0;i<points.length;i++){     
		 if(points[i][1]==pointId){
			 pointType = points[i][7];
		 }
	 }
	 return pointType;
}
</script>
</body>
<%}
}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>
</html>