<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.trimax.its.utility.model.AccessRights"%>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao"%>

<head>
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Expires" content="-1">
<sx:head />
</head>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css"/> -->
<!-- <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css"> -->
<!-- <link rel="stylesheet" href="/resources/demos/style.css"> -->
<%-- <script src="assets/global/plugins/jquery-1.11.0.min.js"type="text/javascript"></script> --%>

<style>
table, th, td {
    border: 1px solid black;
}
</style>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<%-- <script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script> --%>
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="assets/global/plugins/jquery-1.11.0.min.js"
	type="text/javascript"></script>

<script
	src="https://maps.googleapis.com/maps/api/js?client=gme-trimaxitinfrastructure&sensor=false&libraries=places,drawing&signature=CpSOeA5B6SzC2rxqlzd2JwenGX0="></script>

<script src="../assets/admin/pages/scripts/busStop.js"
	type="text/javascript" varsion="2014061"></script>
<script type="text/javascript" src="//www.google.com/jsapi"></script>

<script type="text/javascript">
function getHide(){
// 	alert("hide");
	$(Cbox).hide();
	$(button1).hide();
	$(btnExport).hide();
	$(accordion31).show();
	$(details).hide();
	
}

window.onload=getHide;
</script>

<script type="text/javascript">
	// Load the Google Transliteration API
	google.load("elements", "1", {
		packages : "transliteration"
	});
	google.load("language", "1");
	$(function() {

		mapOptions = {
			zoom : 15,
			center : new google.maps.LatLng(12.97916, 77.56443),
			scaleControl : true
		};
		mapOptionsAll = {
				zoom : 10,
				center : new google.maps.LatLng(12.97916, 77.56443),
				scaleControl : true
			};
		map = new google.maps.Map(document.getElementById('gmap_marker'), mapOptions);
		bounds = new google.maps.LatLngBounds();
	});
	function onLoad() {
		
		var Lat=$("#Lat").val();
		var Long=$("#Long").val();
      // alert("Lat"+Lat+"Long"+Long);
        plotCircle(Lat,Long);
				//control.showControl('translControl');
	}
	
	function convert(){
		//google.load("language", "1");
	//	alert("hiiii");
		//var a='["'+document.getElementById("busStopName").value+'"]';
		
//alert(a);
		google.language.transliterate([document.getElementById("busStopName").value], "en", "kn", function(result) {
		if (!result.error) {
		//var container = document.getElementById("transliteration");
		if (result.transliterations && result.transliterations.length > 0 &&
		result.transliterations[0].transliteratedWords.length > 0) {
			//alert(result.transliterations[0].transliteratedWords[0]);
			document.getElementById("busStopNameKannada").value = result.transliterations[0].transliteratedWords[0];
		}
		}
		});
	}
	
	
	google.setOnLoadCallback(onLoad);
</script>

<script type="text/javascript">

function getDetails(){
	
	 $('#Cbox').show();
	 $('#button1').show();
		$('#btnExport').show();
	var Lat=$("#Lat").val();
	var Long=$("#Long").val();
	var start_date=$("#startdate").val();
	var end_date= $("#enddate").val();
	 var dist= $("#dist").val();
	
	var date1= moment(start_date).format('DD-MM-YYYY-HH-mm');
	 var date2= moment(end_date).format('DD-MM-YYYY-HH-mm');
	var data1=date1.split("-");
	 var dd1=data1[0];
	 var mm1=data1[1];
	 var mnth=Number(mm1);
	 mnth = mnth-1;
	 var yy1=data1[2];
	 var hh1=data1[3];
	 var min1=data1[4];
	 var d1 = new Date(yy1,mnth,dd1,hh1,min1,0,0);  //Date object creation in JS
	 var intervalID = null;
	 var data2=date2.split("-");
	 var dd2=data2[0];
	 var mm2=data2[1];
	 var mnth2=Number(mm2);
	 mnth2 = mnth2-1;
	 var yy2=data2[2];
	 var hh2=data2[3];
	 var min2=data2[4];
	 var d2 = new Date(yy2,mnth2,dd2,hh2,min2,0,0);
	
        $('#pageLoader').show();
        $('#acc3').hide();
$('#Vehicledetails').dataTable({
	
	
	"aLengthMenu" : [ [ 10, 15, 20, -1 ], [ 10, 15, 20, "All" ] // change
	// per
	// page
	// values
	// here
	],
	// set the initial value
	//"iDisplayLength" : 5,
	"sAjaxSource" : "getVehicleDataDateTime.action?Lat="+Lat+"&Long="+Long+"&start_date="+d1+"&end_date="+d2+"&dist="+dist,
	"sPaginationType" : "bootstrap",
	"bDestroy" : true,
//	"bSort" : false,
//	"bFilter" : false,
//	"bSelect" : false,
//	"bPaginate" : false,
//	"bInfo": false,

	 "oLanguage": {
            "sLengthMenu": "_MENU_ records",
            "oPaginate": {
                "sPrevious": "Prev",
                "sNext": "Next"
            }
        },
       "aoColumnDefs": [
            { 'bSortable': false, 'aTargets': [0] },
            { "bSearchable": false, "aTargets": [ 0 ]} ,
            { "sClass": "url", "aTargets": [ 3 ] },
        ],
});
jQuery(
		'#Vehicledetails_wrapper .dataTables_filter input')
		.addClass("form-control input-small input-inline"); // modify
// table
jQuery(
		'#Vehicledetails_wrapper .dataTables_length select')
		.addClass("form-control input-xsmall input-inline"); // modify         


}



// $('#vehicleDetails').on('change',function() {

//     if(this.checked){
//     	alert("in checkbox");
//     	  $.ajax({
// 	            type: "post",
	           
//         success: function(data) {
//             alert('it worked');
//             $('#container').html(data);
//         },
//          error: function() {
//             alert('it broke');
//         },
//         complete: function() {
//             alert('it completed');
//         }
//     });

//     }
// });



function tabletoExcel(btnExport) {
	var htmltable = document
			.getElementById("Cbox").innerHTML;
	
	 var inputHTML = "<table border='1'>";
	    inputHTML += "<tr>";
	    inputHTML += "<th  align='left'colspan='9'>Bangalore Metropolitan Transport Corporation</th>";
	    inputHTML += "</tr>";
	    inputHTML += "<th  align='left'colspan='9'>Bus Stop Vehicle Details</th>";
	    inputHTML += "</tr>";
	    inputHTML += "<tr>";
	   
	    inputHTML += "</tr>";
	    inputHTML += "</table>";

	  var  divElements =inputHTML+htmltable;

	var downloadLink = document.createElement("a");
	downloadLink.href = 'data:application/vnd.ms-excel,'
			+ encodeURIComponent(divElements);
	downloadLink.download = "Bus Stop Vehicle Details.xls";
	document.body.appendChild(downloadLink);
	downloadLink.click();
	document.body.removeChild(downloadLink);
}



function printDiv() {     
    
//     var divElements = document.getElementById("header").innerHTML;
   var htmltable = document
			.getElementById("Cbox").innerHTML;
	
	 var inputHTML = "<table border='1'>";
	    inputHTML += "<th  align='center'colspan='9'>Bus Stop Vehicle Details</th>";
	    inputHTML += "</tr>";
	    inputHTML += "<tr>";
	    inputHTML += "</tr>";
	    inputHTML += "</table>";
	  var  divElements =inputHTML+htmltable;

    var mywindow = window.open("<%=request.getContextPath()%>/Print1.jsp");
   
    mywindow.onload = function() {
    mywindow.document.body.innerHTML=divElements;
    mywindow.document.body.innerHTML=divElements;
    mywindow.print();
    mywindow.close();
    }
            
}



</script>

<%
	AccessRightsDao accessrightdao = new AccessRightsDao();
	AccessRights accessrights = new AccessRights();
	int usrsessionid = (Integer) request.getSession().getAttribute(
			"userid");
	accessrights = accessrightdao.accessRightsdetails(usrsessionid,
			"SaveBusStopMap.action");
	String access = accessrights.getACCESS();
%>
<div class="page-content-wrapper">
	<div class="page-content">
		<%
			if (access.equalsIgnoreCase("Y")) {
		%>
		<div class="form-group">
			<div class="row">
				<div class="col-md-6">


					<input id="Lat" type="hidden"
						value='<s:property value="Latitutde"/>'> <input id="Long"
						type="hidden" value='<s:property value="Longitude"/>'> <input
						id="Long" type="hidden"> <input id="project-id2"
						type="hidden">
				</div>
				<div class="portlet box grey-cascade">

					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i>VIEW ONLINE VEHICLES FOR BUSSTOP:
							<s:property value="busStopName" />
							---Total Vehicles:<span id='tot'></span>
						</div>

					</div>
				</div>
				<div id='loadingmessage' style='display:none'>
 <center> Loading Please Wait...<img src='assets/images/loader1.gif' width="500px" align="middle"/></center> 
				</div>
				<div class="portlet solid white" style="min-height: 550px">
					<div class="col-md-4">

						<div class="input-group date form_datetime">
							<input type="text" size="16" readonly name="startdate"
								id="startdate" class="form-control" value=''> <span
								class="input-group-btn">
								<button class="btn default date-set" type="button">
									<i class="fa fa-calendar"></i>
								</button>
							</span>
						</div>
					</div>

					<!-- 					<label class="control-label col-md-2">End Date/ Time </label> -->
					<div class="col-md-4">
						<div class="input-group date form_datetime">
							<input type="text" size="16" readonly name="enddate" id="enddate"
								value='' class="form-control"> <span
								class="input-group-btn">
								<button class="btn default date-set" type="button">
									<i class="fa fa-calendar"></i>
								</button>
							</span>
						</div>
					</div>
					<div class="col-md-4">
						<input type="text" id="dist" name="dist" value='' onkeypress="return isNumber(event)">
					</div>
					<div class="col-md-4">
						<label><input type="button" value="Submit"
							onclick="plotCircleDateTime()"></label>
					</div>
	<!-- 				<div class="row" id="row">
		<div  id="resizable" class="col-md-9">
		
		<div class="portlet solid white" > -->
			<!-- <div class="portlet-title">
				<div class="caption">
								<i class="fa fa-gift"></i>Bus Stops
							</div>
				
			</div> -->
		<div class="portlet-body" style="min-height: 550px">
						<div id="gmap_marker" class="gmaps" style="min-height: 550px">
				</div>
			</div>
		</div>
			
<!-- 						<form class="form-horizontal" role="form" method="get"> -->
<div id="Cbox">
<input type="checkbox" name="vehicleDetails" onClick="getDetails();"><label class="searchtype1label">Vehicle Details</label></input>
																<div class="portlet-body" id="Totaldetails">
										<div style="text-align:center">
				<table class="table table-striped table-bordered table-hover"
								id="Vehicledetails">
								<thead >
											<tr>
												<th>Sr. NO</th>
												<th>Device Id</th>
												<th>Vehicle No.</th>
												<th>Schedule No.</th>
												<th>IST Date</th>
												<th>Depot Name</th>
												
											</tr>
										</thead>
							
							</table>
							</div>
							</div></div>
								
							
								<div align='center'>
							<span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span>&nbsp;
							<button type="submit" id="btnExport" class="btn green"	onClick="tabletoExcel()">Export</button>
											<!-- END EXAMPLE TABLE PORTLET-->
										</div>


							<div id="resizable1" class="col-md-3" style="height: 1000px; width:100%">

<div id="container">
								<div class="portlet box white">

									<div class="portlet-body">
										<div id="accordion31" class="panel-group accordion">
											<div class="panel panel-default" id="routeDef">
												<div class="panel-heading">
													<h4 class="panel-title">
														<a href="#collapse_3_1" data-parent="#accordion3"
															data-toggle="collapse"
															class="accordion-toggle accordion-toggle-styled collapsed">
															Vehicle Details </a>
													</h4>
												</div>
												
												<div class="panel-collapse collapse" id="collapse_3_1"
													style="color: #000000; overflow: auto; height: 1000px; width:100%">

												
							
							<div id="acc3"></div>  
							
							
					
							
							
							

									</div>
										</div>
									</div>
								</div>
								
								
	
							</div>
</div>

 

</div> 

					


					</div></div>
<!-- 					</form> -->
		<!-- Modal -->

		<%
			} else {
		%>



		<%@ include file="/pages/admin/error.jsp"%>


		<%
			}
		%>




		<script>
var vehiclemarker = [];
var intervalID = null;
var markers = [];
var cityCircle;
function deleteMarkers() {
	clearMarkers();
	markers = [];
}
function deleteMarkersvehicle() {
	clearMarkers();
	vehiclemarker = [];

}

function clearMarkers() {
	setAllMap(null);
}
		
function setAllMap(map) { 
	for ( var i = 0; i < markers.length; i++) {
		markers[i].setMap(map);
	}
	for ( var i = 0; i < vehiclemarker.length; i++) {
		vehiclemarker[i].setMap(map);
		latLong = new google.maps.LatLng(vehiclemarker[i].latitude,
				vehiclemarker[i].longitude);
		// bounds.extend(latLong);
		// map.fitBounds(bounds);
	}
}
function plotCircle(Lat,Long){
	 $('#Cbox').hide();
		$('#button1').hide();
		$('#btnExport').hide();
		$('#acc3').show();
	map.setCenter(new google.maps.LatLng(Lat, Long))
	map.setZoom(14);
	 var populationOptions = {
	      strokeColor: '#FF0000',
	      strokeOpacity: 0.8,
	      strokeWeight: 2,
	      fillColor: '#000000',
	      fillOpacity: 0.35,
	      map: map,
	      center:new google.maps.LatLng(Lat,Long),
	      radius:  200
	    };
	    // Add the circle for this city to the map.
	    cityCircle = new google.maps.Circle(populationOptions);
	    plotVehicleOnMap(Lat,Long);
	   if (intervalID != null) {
		clearInterval(intervalID);
	   }
	    intervalID = setInterval(function() {
		  plotVehicleOnMap(Lat,Long);
		}, 50000);
			}
			
function plotCircleDateTime(){
	$('#Cbox').show();
	$('#button1').show();
	$('#btnExport').show();
	$('#resizable1').hide();
	
	if (intervalID != null) {
		clearInterval(intervalID);
	}
	var Lat=$("#Lat").val();
		var Long=$("#Long").val();
		var start_date=$("#startdate").val();
		var end_date= $("#enddate").val();
		var date1= moment(start_date).format('DD-MM-YYYY-HH-mm');
		 var date2= moment(end_date).format('DD-MM-YYYY-HH-mm');
		var data1=date1.split("-");
		 var dd1=data1[0];
		 var mm1=data1[1];
		 var mnth=Number(mm1);
		 mnth = mnth-1;
		 var yy1=data1[2];
		 var hh1=data1[3];
		 var min1=data1[4];
		 var d1 = new Date(yy1,mnth,dd1,hh1,min1,0,0);  //Date object creation in JS
		 var intervalID = null;
		 var data2=date2.split("-");
		 var dd2=data2[0];
		 var mm2=data2[1];
		 var mnth2=Number(mm2);
		 mnth2 = mnth2-1;
		 var yy2=data2[2];
		 var hh2=data2[3];
		 var min2=data2[4];
		 var d2 = new Date(yy2,mnth2,dd2,hh2,min2,0,0);   //Date object creation in JS
		 var diff = d2.getTime() - d1.getTime();     // in milli second
		 var hDiff = diff / 3600 / 1000;              // in hours
		 if(d1>d2){
				bootbox.alert("From Time should not be greater than to To Time");
				return false;
			}
			if(hDiff> 1/2){
				 bootbox.alert("From Time and To Time should not be greater than 1/2 an  hours");
				return false;
			}
		
		
		
	    var dist= $("#dist").val();
	    if(dist >500){
	    	bootbox.alert("Distance can not be greater than 500 meters");
	    	return false;
	    }
	
	bootbox.confirm("Location Related Queries Are Very Server Load Sensitive, Are You Still Want To Query For This Bus Stop?",
			function(result) {


		if (result == true) {
			var no1= Number(min1);
			var no2= Number(min2);
			var start_min=0;
			var end_min=0;
			for(var i =no1 ; i<no2 ; i++){
				start_min = i;
				end_min= i+1;
				 var d1 = new Date(yy1,mnth,dd1,hh1,start_min,0,0);
				 var d2 = new Date(yy2,mnth2,dd2,hh2,end_min,0,0);
				 console.log("strt-- "+d1);
				 console.log("end--"+d2);
				 setTimeout(plotVehicleOnMapDateTime(Lat,Long,d1,d2,dist), 10000);
			}
				
					   /*  intervalID = setInterval(function() {
// 					    	alert("to plot"+intervalID);
					    	plotVehicleOnMapDateTime(Lat,Long,d1,d2,dist);
						}, 1000);
					    
					    
					    if (intervalID != null) {
// 							  alert("interval"+intervalID);
								clearInterval(intervalID);
							   }
// 				 plotVehicleOnMapDateTime(Lat,Long,d1,d2,dist);
			}
			 */
			 
// 			 plotVehicleOnMapDateTime(Lat,Long,d1,d2,dist);
// 			   if (intervalID != null) {
// 				clearInterval(intervalID);
// 			   }
// 			    intervalID = setInterval(function() {
// 			    	plotVehicleOnMapDateTime(Lat,Long,d1,d2,dist);
// 				}, 50000);
			    
// }
			
//				plotVehicleOnMapDateTime(Lat,Long,start_date,end_date,dist);
		}else{
			return false;
		}
			});
			}
			
			
function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}			
//With Date
function plotVehicleOnMap(Lat,Long){
	var details= "<div class='component'><table class='overflow-y' border='1' style='width:100%'><b><h4><th>Sr No</h4></b><b><th>Vehicle No</th><th>Device Id</th><th>IST Date</th><th>Depot Name</th> </b>";
	$('#loadingmessage').show();
  $.ajax({
        type: "post",
        url: '<%=request.getContextPath()%>/getVehicleDataToPlot.action?Lat='+Lat+'&Long='+Long,
		success : function(response) {
			$('#loadingmessage').hide(); 
			for ( var i = 0; i < vehiclemarker.length; i++) {
				vehiclemarker[i].setMap(null);

			}
			vehiclemarker = [];
			// console.log(response);
			deleteMarkers();

			//alert("response"+response);
			var infowindow = new google.maps.InfoWindow();
			var obj = response;
			var j = 0;
			var info = "";
			var i = 0;
			if (obj != null) {
				
				var objlength = obj["aaData"].length;
				document.getElementById("tot").innerHTML=objlength;
				
				for (i = 0; i < objlength; i++) {
					var latLong = new google.maps.LatLng(
							obj["aaData"][i][1], obj["aaData"][i][2]);
					//alert("Lat"+obj["aaData"][i][1]+"Long"+obj["aaData"][i][2]);
					//var time = obj["aaData"][i][3].split(" ");
					var bus_icon = '';
					if(obj["aaData"][i][9]>=0 && obj["aaData"][i][9]<22.5){
						bus_icon= "assets/images/busRotation/busN.png";
					} else if(obj["aaData"][i][9]>=22.5 && obj["aaData"][i][9]<67.5){
						bus_icon = "assets/images/busRotation/busNE.png";
					} else if(obj["aaData"][i][9]>=67.5 && obj["aaData"][i][9]<112.5){
						bus_icon = "assets/images/busRotation/busE.png";
					} else if(obj["aaData"][i][9]>=112.5 && obj["aaData"][i][9]<157.5){
						bus_icon = "assets/images/busRotation/busSE.png";
					} else if(obj["aaData"][i][9]>=157.5 && obj["aaData"][i][9]<202.5){
						bus_icon = "assets/images/busRotation/busS.png";
					} else if(obj["aaData"][i][9]>=202.5 && obj["aaData"][i][9]<247.5){
						bus_icon = "assets/images/busRotation/busSW.png";
					} else if(obj["aaData"][i][9]>=247.5 && obj["aaData"][i][9]<292.5){
						bus_icon = "assets/images/busRotation/busW.png";
					} else if(obj["aaData"][i][9]>=292.5 && obj["aaData"][i][9]<337.5){
						bus_icon = "assets/images/busRotation/busNW.png";
					} else if(obj["aaData"][i][9]>=337.5 && obj["aaData"][i][9]<361){
						bus_icon = "assets/images/busRotation/busN.png";
					}
					else{
						bus_icon = 'assets/images/bus.png';
					}
					
					
					
					
					/* if (obj["aaData"][i][2] > 5) {
						bus_icon = 'assets/images/bus-map-icon.png';
					} else if ((obj["aaData"][i][2] <= 5)
							&& (obj["aaData"][i][2] > 0)) {
						bus_icon = 'assets/images/Icon-Bus-Orange.png';
					} else if ((obj["aaData"][i][2] == "0.00")) {
						bus_icon = 'assets/images/Icon-Bus-Blue.png';
					}
					if ((obj["aaData"][i][8] == "0")) {
						bus_icon = 'assets/images/grey.png';
					}
					if ((obj["aaData"][i][9] == "1")) {
						bus_icon = 'assets/images/fourbyfour.png';
					}
					if ((obj["aaData"][i][11] == "A")) {
//						bus_icon = 
						bus_icon = 'assets/images/Icon-Bus-Red.gif';
					} */
					
					
					// display Vehicle Details info in div
				details +=	 '<tr><td>'
					+ obj["aaData"][i][0]
					+ '</td>'
					+ '<td >'
					+ obj["aaData"][i][4]
					+ '</td>'
					+ '<td id="device" >'
					+ obj["aaData"][i][3]
					+ '</td>'
					+ '<td id="date">'
					+  obj["aaData"][i][7]
					+ '</td>'
					+ '<td id="depot">'+ obj["aaData"][i][8]+'</td>'
					+' </tr>' ;

					// end

					
					var marker = new google.maps.Marker(
							{
								map : map,
								position : latLong,
								icon : bus_icon,
								optimized: false,
								// icon : (obj["aaData"][i][2] >
								// 5)?'assets/images/Bus-Icon-Green.png':
								// //
								// (obj["aaData"][i][2]==="0.00")?'assets/images/Icon-Bus-Blue.png':
								// 'assets/images/Bus-Icon.png',
								latitude : obj["aaData"][i][1],
								longitude : obj["aaData"][i][2],
								deviceid : obj["aaData"][i][4],
								devId:obj["aaData"][i][3],
								//vehicledirection : obj["aaData"][i][3],
								schno : '<div class="portlet-body form">'
										+ '<div class="form-body">'
										+ '<div class="table-responsive" style="color:#000000;width: 200px;" ><table class="table table-hover"><tr>'
										+ '<tr><td align=""><B>Vehicle</B></th><td>'
										+ obj["aaData"][i][4]
										+ '</td></tr>'
										+ '<tr><td align=""><B>Device Id</B></th><td>'
										+ obj["aaData"][i][3]
										+ '</td></tr><tr><td align=""><B>Schedule No</B></td><td id="schNumber">'
										+ obj["aaData"][i][5]
										+ '</td></tr><tr><td align=""><B>Speed</B></td><td id="">'
										+ obj["aaData"][i][6]
										+ '</td></tr><tr><td align=""><B>IST Date</B></td><td id="">'
										+ obj["aaData"][i][7]
										+ '</td></tr><tr><td align=""><B>Depot Name</B></td><td id="">'
										+ obj["aaData"][i][8]
										+ '</td></tr></table>'
										+ '</div>'
										+ '</div>'
										+ '</div>'
								
							});// End of marker defination
					// Setting Info window
					google.maps.event
							.addListener(
									marker,
									'click',
									(function(marker, j) {
										return function() {
											infowindow.setContent(this.info);
											infowindow.open(map, this);
											//getScheduleNo(this.devId);
											//AllDevices.get_reverse_geocode(this.latitude,this.longitude);
											//AllDevices.get_schedule_number(this.deviceid);
										}
									})(marker, j));
					google.maps.event
							.addListener(
									marker,
									'click',
									(function(marker, j) {
										return function() {
											infowindow
													.setContent(this.schno);
											infowindow.open(map, this);
											//AllDevices
													//.get_schedule_number(this.deviceid);
										}
									})(marker, j));
					// google.maps.event
					// .addListener(
					// marker,
					// 'mouseout',
					// (function(marker, j) {
					// return function() {
					// infowindow
					// .setContent(this.schno);
					// infowindow.open(map, this);
					// AllDevices
					// .get_schedule_number(this.deviceid);
					// }
					// })(marker, j));
					google.maps.event.addListener(map, 'click',
							function() {
								infowindow.open(null, null);
							});
					j++;
					// Setting Center location
					 if (obj["aaData"][i][1] != '0.00000000') {

						vehiclemarker.push(marker);
					}
					
				} // End of for loop
				setAllMap(map);
				details +='</table>';
// 				console.log(details);
				$('#acc3').html(details);
			}
			// map.setCenter(latLong);
		}
	});



}
//End
			
			//With Date
				function plotVehicleOnMapDateTime(Lat,Long,start_date,end_date,dist){
					var homeControlDiv;
					if (intervalID != null) {
						clearInterval(intervalID);
					}
					var details= "<div class='component'><table class='overflow-y' border='1' style='width:100%'><b><h4><th>Sr No</h4></b><b><th>Vehicle No</th><th>Device Id</th><th>IST Date</th><th>Depot Name</th> </b>";
					$('#loadingmessage').show();
				  $.ajax({
			            type: "post",
			            url: '<%=request.getContextPath()%>/getVehicleDataToPlotDateTime.action?Lat='
									+ Lat
									+ '&Long='
									+ Long
									+ '&start_date='
									+ start_date
									+ '&end_date='
									+ end_date
									+ '&dist=' + dist,
// 									 async:false,
							success : function(response) {
								$('#loadingmessage').hide(); 
								for ( var i = 0; i < vehiclemarker.length; i++) {
									vehiclemarker[i].setMap(null);

								}
								vehiclemarker = [];
								// console.log(response);
								deleteMarkers();

								//alert("response"+response);
								var infowindow = new google.maps.InfoWindow();
								var obj = response;
								var j = 0;
								var info = "";
								var i = 0;
								if (obj != null) {

									var objlength = obj["aaData"].length;
									document.getElementById("tot").innerHTML = objlength;
									
									for (i = 0; i < objlength; i++) {
										var latLong = new google.maps.LatLng(
												obj["aaData"][i][1],
												obj["aaData"][i][2]);
										//alert("Lat"+obj["aaData"][i][1]+"Long"+obj["aaData"][i][2]);
										//var time = obj["aaData"][i][3].split(" ");
										var bus_icon = '';
										if(obj["aaData"][i][9]>=0 && obj["aaData"][i][9]<22.5){
											bus_icon= "assets/images/busRotation/busN.png";
										} else if(obj["aaData"][i][9]>=22.5 && obj["aaData"][i][9]<67.5){
											bus_icon = "assets/images/busRotation/busNE.png";
										} else if(obj["aaData"][i][9]>=67.5 && obj["aaData"][i][9]<112.5){
											bus_icon = "assets/images/busRotation/busE.png";
										} else if(obj["aaData"][i][9]>=112.5 && obj["aaData"][i][9]<157.5){
											bus_icon = "assets/images/busRotation/busSE.png";
										} else if(obj["aaData"][i][9]>=157.5 && obj["aaData"][i][9]<202.5){
											bus_icon = "assets/images/busRotation/busS.png";
										} else if(obj["aaData"][i][9]>=202.5 && obj["aaData"][i][9]<247.5){
											bus_icon = "assets/images/busRotation/busSW.png";
										} else if(obj["aaData"][i][9]>=247.5 && obj["aaData"][i][9]<292.5){
											bus_icon = "assets/images/busRotation/busW.png";
										} else if(obj["aaData"][i][9]>=292.5 && obj["aaData"][i][9]<337.5){
											bus_icon = "assets/images/busRotation/busNW.png";
										} else if(obj["aaData"][i][9]>=337.5 && obj["aaData"][i][9]<361){
											bus_icon = "assets/images/busRotation/busN.png";
										}
										else{
											bus_icon = 'assets/images/bus.png';
										}
										
										// display Vehicle Details info in div
											details +=	 '<tr><td>'
					+ obj["aaData"][i][0]
					+ '</td>'
					+ '<td >'
					+ obj["aaData"][i][4]
					+ '</td>'
// 					+ '<td width="30%">'
// 					+ obj["aaData"][i][5]
// 				        + '</td>'
					+ '<td id="device" >'
					+ obj["aaData"][i][3]
					+ '</td>'
					+ '<td id="date">'
					+  obj["aaData"][i][7]
					+ '</td>'
					+ '<td id="depot">'+ obj["aaData"][i][8]+'</td>'
					+' </tr>' ;

					// end
										
										var marker = new google.maps.Marker(
												{
													map : map,
													position : latLong,
													icon : bus_icon,
													optimized : false,
													// icon : (obj["aaData"][i][2] >
													// 5)?'assets/images/Bus-Icon-Green.png':
													// //
													// (obj["aaData"][i][2]==="0.00")?'assets/images/Icon-Bus-Blue.png':
													// 'assets/images/Bus-Icon.png',
													latitude : obj["aaData"][i][1],
													longitude : obj["aaData"][i][2],
													deviceid : obj["aaData"][i][4],
													devId : obj["aaData"][i][3],
													//vehicledirection : obj["aaData"][i][3],
													schno : '<div class="portlet-body form">'
															+ '<div class="form-body">'
															+ '<div class="table-responsive" style="color:#000000;width: 200px;" ><table class="table table-hover"><tr>'
															+ '<tr><td align=""><B>Vehicle</B></th><td>'
															+ obj["aaData"][i][4]
															+ '</td></tr>'
															+ '<tr><td align=""><B>Device Id</B></th><td>'
															+ obj["aaData"][i][3]
															+ '</td></tr><tr><td align=""><B>Schedule No</B></td><td id="schNumber">'
															+ obj["aaData"][i][5]
															+ '</td></tr><tr><td align=""><B>Speed</B></td><td id="">'
															+ obj["aaData"][i][6]
															+ '</td></tr><tr><td align=""><B>IST Date</B></td><td id="">'
															+ obj["aaData"][i][7]
															+ '</td></tr><tr><td align=""><B>Depot Name</B></td><td id="">'
															+ obj["aaData"][i][8]
															+ '</td></tr></table>'
															+ '</div>'
															+ '</div>'
															+ '</div>'

												});// End of marker defination
										// Setting Info window
										google.maps.event
												.addListener(
														marker,
														'click',
														(function(marker, j) {
															return function() {
																infowindow
																		.setContent(this.info);
																infowindow
																		.open(
																				map,
																				this);
																//getScheduleNo(this.devId);
																//AllDevices.get_reverse_geocode(this.latitude,this.longitude);
																//AllDevices.get_schedule_number(this.deviceid);
															}
														})(marker, j));
										google.maps.event
												.addListener(
														marker,
														'click',
														(function(marker, j) {
															return function() {
																infowindow
																		.setContent(this.schno);
																infowindow
																		.open(
																				map,
																				this);
																//AllDevices
																//.get_schedule_number(this.deviceid);
															}
														})(marker, j));
										// google.maps.event
										// .addListener(
										// marker,
										// 'mouseout',
										// (function(marker, j) {
										// return function() {
										// infowindow
										// .setContent(this.schno);
										// infowindow.open(map, this);
										// AllDevices
										// .get_schedule_number(this.deviceid);
										// }
										// })(marker, j));
										google.maps.event
												.addListener(
														map,
														'click',
														function() {
															infowindow.open(
																	null, null);
														});
										j++;
										// Setting Center location
										if (obj["aaData"][i][1] != '0.00000000') {

											vehiclemarker.push(marker);
										}
										


									} // End of for loop
									setAllMap(map);
									details +='</table>';
// 									console.log(details);
									$('#acc3').html(details);
								}
								// map.setCenter(latLong);
							}
						});

			}
			//End
		
			
		</script>