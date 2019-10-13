<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<%-- <style  type="text/css">
table#linearRoute{
	max-width:500px;
	overflow-x:scroll; 
}
table#linearRoute th{
	heigth:10px;
}

</style> --%>
<head>
<script src="assets/global/plugins/jquery-1.11.0.min.js" value='1'type="text/javascript"></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.9.1/themes/black-tie/jquery-ui.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
</head>
<body>
	<div class="page-content-wrapper">
		<div class="page-content">
			<div class="row">
				<div class="col-md-12">
					<h3 class="page-title">
						TRIP LINEAR DIAGRAM <small></small>
					</h3>
				</div>
			</div>
			<div class="tab-content">
				<div class="tab-pane active">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Trip Linear Diagram
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
									<div class="tabbable tabbable-custom tabbable-reversed">
										<ul class="nav nav-tabs">
											<li id='tab0' class='active'><a href="#tab_0" data-toggle="tab">Route Wise</a></li>
											<!-- <li id='tab1'><a href="#tab_1" data-toggle="tab">Source & Destination Wise </a></li> -->
										</ul>
										<div class="tab-content">
											<div class="tab-pane active"   id="tab_0">
												<div class="form-group">
													<!-- <label class="col-md-2 control-label">Route Number</label> -->
													<div class="col-md-3">
														<input class="form-control" tabindex="1" id="project" name="project" type="hidden" value='<s:property value="routeNo"/>'/>
														
														<input id="project-id" type="hidden" value='<%=request.getParameter("routeNo")%>'>
														<script>
														jQuery(document).ready(function() {    
															getRouteData();
														});
														</script>
								 						<input id="project-id1" type="hidden">
								 						<input id="project-id2" type="hidden">
													</div>
													
													
  					
														<!-- <button type="button" class="btn default" onclick="getRouteData()" style="position: static;">Get Data</button> -->
														<button  type='button' class="btn grey" onclick="getdetails(route_id);"> Refresh Position</button>
														</div>
													<div class="table-scrollable"> 	
														<canvas id="myCanvas" width="1180px"  ></canvas>
													 </div>	
												
											 	<div class="form-group">
														<p id='loading'>
														</p>
														<p id='bus_stop_names'>
														</p>
												</div>
												<div class="form-group">
													
												</div>
											</div>
											
											<div class="tab-pane" id="tab_1">
												<br/>
												<br/>
												<br/>
											</div><a data-toggle="modal" style="display:none"  id="popup" href="#model" >sss</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<div style="display: none" id="model" class="modal fade"
	tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
	aria-hidden="true"   data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width:30%">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title"><b>Details</b></h3>
			</div>
			<div class="modal-body" >
				<div  id="scrollable-div2" >
					<p id="tipText"></p>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal"  class="btn default"  id='cancel' >Cancel</button>
				<!-- <button type="button" class="btn green"  onclick='return saveIds()'>Submit</button> -->
			</div>
		</div>
	</div>
</div> 
</body>									
<script>
var dataSet = [];
var routeNo;
function showvehdet(vehicle){
	$("#vehicledata").find("tr:gt(0)").remove();
	for(var i=0; i<vehdetroudata.etaModel.length; i++){
	   	if(vehdetroudata.etaModel[i].vehicleNo==vehicle){
     		$('#vehicledata tr').last().after('<tr><td>'+vehdetroudata.etaModel[i].busStopName+'</td><td>'+Math.round(parseInt(vehdetroudata.etaModel[i].eta)/60000)+'Min</td></tr>');
        }
 	}
 }
/* setInterval(function() {
	getdetails(route_id);
}, 10000); */

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

var routeidreldata=[];
var aaa = 0;
var route_id = 0;
var routePointsArray = [];
$("#myCanvas").html('');
var viewStartLeft = 20;
var viewStartTop = 20;
var viewEndRight = 20;

var canvasObj = document.getElementById("myCanvas");

var canvasW = parseInt(Math.round($("#myCanvas").width()))-viewStartLeft-viewEndRight;
var ratioOfPix = 0;
var canvasCntrl = canvasObj.getContext("2d");
function getRouteData(){
	//$("linearRoute").find("tr:gt(0)").remove();
	//$('linearRoute').children( 'tr:not(:first)' ).remove();
	var det=$("#project-id").val().split("-");
	routeNo = $("#project").val();
	routePointsArray = [];
	aaa = 0;
	canvasCntrl.clearRect(0, 0, canvasCntrl.width, canvasCntrl.height);
	var data = $.ajax({
		type: "get",
   	   	async:false,
   	   	data : 'json',
		url :  "<%=request.getContextPath()%>/ShowRouteAjax?routeid="+ det[0]+"&start_point="+ det[1]+"&end_point="+ det[2],
		beforeSend:function(){
			$("#bus_stop_names").html('');
			$("#loading").html('<img src="<%=request.getContextPath()%>/inventory/loading-icon.gif" height="55px" width="55px" style="padding-right:2px"/><h4>loading.. </h4>');
		},
		complete:function(){
			$("#loading").html('');
			//$.unblockUI();
		},
	    success: function(data){
	    	
	    }
	}).responseText;
	
	
	canvasCntrl.clearRect(0, 0, canvasObj.width, canvasObj.height);
	canvasCntrl.beginPath();
	canvasCntrl.moveTo(10,80);
	result=JSON.parse(data);
    console.log(result);
	categories =[];
	var i = 0;
	$("#myCanvas").html('');
	var routeMapList = result.routemap;
	var routePointList = result.routepoint;
	console.log(routePointList);
	for(var j=0;j<routeMapList.length;j++ ){
		//alert(getPonitType(routeMapList[j][1],routePointList)+" \t"+getPonitType(routeMapList[j][1],routePointList));
		if(getPonitType(routeMapList[j][1],routePointList)!=13 && getPonitType(routeMapList[j][1],routePointList)!=2){
			i++;
			routePointsArray.push({"busStopId":routeMapList[j][1],"accDistance":parseInt(aaa)});
			var busstopList = $("#bus_stop_names").html();
			$("#bus_stop_names").html(busstopList+"<br>"+i+"."+result.routepoint[j][6]);
		}
		aaa +=  routeMapList[j][7]/10;
	}
	i++;
	var busstopList = $("#bus_stop_names").html();
	$("#bus_stop_names").html(busstopList+"<br>"+i+"."+result.routepoint[routeMapList.length][6]);
	routePointsArray.push({"busStopId":routeMapList[routeMapList.length-1][2],"accDistance":parseInt(aaa)});
	console.log(routePointsArray);
	route_id = det[0];
	ratioOfPix = canvasW / aaa;
	//ratioOfPix = 1;
	console.log(aaa*ratioOfPix);
	canvasCntrl.lineTo(aaa*ratioOfPix,80);
	canvasCntrl.strokeStyle='black';
	canvasCntrl.stroke();
	
	
	
	canvasCntrl.beginPath();
	for(var i=0;i<routePointsArray.length;i++){
		if(i==0){
			canvasCntrl.moveTo(10,80);
			canvasCntrl.lineTo(10,105);
			canvasCntrl.fillText(""+(i+1),7, 120);
			canvasCntrl.stroke();
		}else{
		canvasCntrl.moveTo(((routePointsArray[i].accDistance)*ratioOfPix),80);
		canvasCntrl.lineTo(((routePointsArray[i].accDistance)*ratioOfPix),105);
		canvasCntrl.fillText(""+(i+1),((routePointsArray[i].accDistance)*ratioOfPix)-3, 120);
		canvasCntrl.stroke();
		}
	}
	getdetails(det[0]);
}
var vehdetroudata="";
var vehiclesData = [];
var dots = [];
function getdetails(routeid) {
	$("#myCanvas").html('');
	addListener();
	var data =  $.ajax({
		type: "get",
   	   	async:false,
   	   	data : 'json',
		url :  "<%=request.getContextPath()%>/VehicleList?routeid="+routeid,
	    success: function(data){}
	}).responseText;
	try{
		result=JSON.parse(data);
	    console.log(result);
	   	dataSet = [];
	   	vehiclesData =[];
		var c=document.getElementById("myCanvas");
		var ctrl=c.getContext("2d");
		ctrl.clearRect(0, 0, c.width, 75);
		//ctrl.beginPath();
		dots = [];
		if(result.length>0){
		   	for(var i=0;i<result.length;i++){
				for(var k=0;k<routePointsArray.length;k++){
					
					if(result[i]["lastBustopCoveredBusTopId"]==routePointsArray[k].busStopId){     
						
						var startpoint =  (10+parseInt(routePointsArray[k].accDistance)+parseInt(Math.round(result[i]["travelledDistance"])));
						ctrl.beginPath();
						ctrl.arc(startpoint*ratioOfPix, 60, 4, 0, Math.PI*2); 
						ctrl.lineWidth = 2;
						ctrl.strokeStyle = "red";
						ctrl.stroke();
						ctrl.restore(); 
						/* var imgBasket = new Image();
						imgBasket.src = "assets/images/track_blue.png";
						ctrl.drawImage(imgBasket,startpoint*ratioOfPix,50,20,20); */
						
						dots.push({
					        x: startpoint*ratioOfPix,
					        y: 60,
					        r: 4,
					        rXr: 7,
					        /* color: "red", */
					        tipText:"<h5>Vehilce No : <b>"+result[i].vehicleNo+" </b></h4>"
					        +"<h5>Last Covered stop : <b>"+result[i].lastBustopCoveredBusTop+"</b></h4>"
					        +"<h5>Next stop : <b>"+result[i].nextBusStop+"</b></h4>",
					    });
						console.log(startpoint*ratioOfPix);
					} 
				}
		   	}
		}else{
			bootbox.alert("No Vehicles Found");	
		}
	}catch(e){
		alert(e);
		bootbox.alert("Please check connection");	
	}
 }
var graph = document.getElementById("myCanvas");
var ctx = graph.getContext("2d");
var canvasOffset = $("#myCanvas").offset();


function addListener(){
	$("#myCanvas").mousemove(function (e) {
		//console.log(dots);
		var canvas = document.getElementById("myCanvas");
	   handleMouseMove(e,canvas);
	});
}
//show tooltip when mouse hovers over dot
function handleMouseMove(e,canvas) {
	var offsetX = canvasOffset.left;
	var offsetY = canvasOffset.top;
	//console.log(offsetX+" "+offsetY);
    var mx =  e.clientX +xMousePos - offsetX;
    var my = e.clientY + yMousePos- offsetY; 
    for (var i = 0; i < dots.length; i++) {
   // console.log((mx-dots[i].x)*(mx-dots[i].x)+ (my-dots[i].y)*(my-dots[i].y)+" "+(dots[i].rXr * dots[i].rXr));
		if (((mx-dots[i].x)*(mx-dots[i].x)+ (my-dots[i].y)*(my-dots[i].y)) < (dots[i].rXr * dots[i].rXr)){
            $("#tipText").html(dots[i].tipText);
            	   $("#popup").click();
	    }
    }
}
$(window).scroll(function(event) {
    if(lastScrolledLeft != $(document).scrollLeft()){
        xMousePos -= lastScrolledLeft;
        lastScrolledLeft = $(document).scrollLeft();
        xMousePos += lastScrolledLeft;
    }
    if(lastScrolledTop != $(document).scrollTop()){
        yMousePos -= lastScrolledTop;
        lastScrolledTop = $(document).scrollTop();
        yMousePos += lastScrolledTop;
    }
});
function getMousePos(canvas, evt) {
    var rect = canvas.getBoundingClientRect();
    return {
      x: evt.clientX - rect.left,
      y: evt.clientY - rect.top
    };
  } 
 function getPonitType(pointId,points){
	 var pointType = 0;
	 //console.log(points);
	 for( var i=0;i<points.length;i++){     
		 //alert(points[i]["1"] +" "+ pointId);
		 if(points[i][1]==pointId){
			 pointType = points[i][7];
		 }
	 }
	
	 return pointType;
 }
 var xMousePos = 0;
 var yMousePos = 0;
 var lastScrolledLeft = 0;
 var lastScrolledTop = 0;
</script>
</body>
</html>