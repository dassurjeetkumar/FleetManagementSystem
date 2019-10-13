<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places,drawing,geometry"></script>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
<script src="assets/vts/js/vehiclealert.js"></script>
<script src="assets/global/plugins/jquery-1.11.0.min.js" type="text/javascript"></script>
<script src="assets/vts/js/busBunchingReport.js" type="text/javascript"></script>


<script type="text/javascript">
$(document).ready( function () {
	var _0x5853=["\x64\x61\x74\x61\x54\x61\x62\x6C\x65","\x23\x72\x6F\x75\x74\x65\x42\x75\x6E\x63\x68\x53\x74\x61\x74\x75\x73"];
	var oTable=$(_0x5853[1])[_0x5853[0]](); 
	new FixedHeader(oTable)
} );
</script>


<script>
/* 
 $(document).ready(function(){
	$("#printbutton").hide();
	$("#scheduleprint").hide();
	$("#schdeviation").hide();
	document.getElementById("scheduleDevationdata").style.display = '';
 }); */
 function printDiv() {	 
	 var _0xa4a3=["\x68\x69\x64\x65","\x2E\x6D\x61\x70\x43\x6C\x61\x73\x73","\x76\x69\x73\x69\x62\x69\x6C\x69\x74\x79","\x73\x74\x79\x6C\x65","\x62\x75\x6E\x63\x68\x73\x74\x61\x74\x75\x73","\x67\x65\x74\x45\x6C\x65\x6D\x65\x6E\x74\x42\x79\x49\x64","\x76\x69\x73\x69\x62\x6C\x65","\x64\x69\x73\x70\x6C\x61\x79","\x68\x65\x61\x64\x65\x72","\x62\x6C\x6F\x63\x6B","\x69\x6E\x6E\x65\x72\x48\x54\x4D\x4C","\x6F\x70\x65\x6E","\x6F\x6E\x6C\x6F\x61\x64","\x62\x6F\x64\x79","\x64\x6F\x63\x75\x6D\x65\x6E\x74","\x70\x72\x69\x6E\x74","\x63\x6C\x6F\x73\x65","\x6E\x6F\x6E\x65","\x68\x69\x64\x64\x65\x6E","\x73\x68\x6F\x77"];$(mapshow)[_0xa4a3[0]]();$(_0xa4a3[1])[_0xa4a3[0]]();document[_0xa4a3[5]](_0xa4a3[4])[_0xa4a3[3]][_0xa4a3[2]]= _0xa4a3[6];document[_0xa4a3[5]](_0xa4a3[8])[_0xa4a3[3]][_0xa4a3[7]]= _0xa4a3[9];document[_0xa4a3[5]](_0xa4a3[8])[_0xa4a3[3]][_0xa4a3[2]]= _0xa4a3[6];var divElements=document[_0xa4a3[5]](_0xa4a3[8])[_0xa4a3[10]];divElements+= document[_0xa4a3[5]](_0xa4a3[4])[_0xa4a3[10]];var mywindow=window[_0xa4a3[11]]('<%=request.getContextPath()%>/Print.jsp');mywindow[_0xa4a3[12]]= function(){mywindow[_0xa4a3[14]][_0xa4a3[13]][_0xa4a3[10]]= divElements;mywindow[_0xa4a3[14]][_0xa4a3[13]][_0xa4a3[10]]= divElements;mywindow[_0xa4a3[15]]();mywindow[_0xa4a3[16]]()};document[_0xa4a3[5]](_0xa4a3[8])[_0xa4a3[3]][_0xa4a3[7]]= _0xa4a3[17];document[_0xa4a3[5]](_0xa4a3[8])[_0xa4a3[3]][_0xa4a3[2]]= _0xa4a3[18];$(_0xa4a3[1])[_0xa4a3[19]]();$(mapshow)[_0xa4a3[19]]();document[_0xa4a3[5]](_0xa4a3[4])[_0xa4a3[3]][_0xa4a3[7]]= _0xa4a3[6]
 }
 var i=0;
 function getDepot(date){
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
	 var _0x12a7=["\x76\x61\x6C\x75\x65","\x64\x69\x76\x69\x73\x69\x6F\x6E\x6C\x69\x73\x74","\x67\x65\x74\x45\x6C\x65\x6D\x65\x6E\x74\x42\x79\x49\x64","\x67\x65\x74","\x69\x6E\x6E\x65\x72\x48\x54\x4D\x4C","\x64\x65\x70\x6F\x74\x6C\x69\x73\x74","\x61\x6A\x61\x78"];
		 var val=document[_0x12a7[2]](_0x12a7[1])[_0x12a7[0]];if(val!= 0){$[_0x12a7[6]]({type:_0x12a7[3],url:'<%=request.getContextPath()%>/getDepot?val='+ val,success:function(_0x513ax2){document[_0x12a7[2]](_0x12a7[5])[_0x12a7[4]]= _0x513ax2}})}
	}
	function validateBunchingReportFields(selDate,routeno) {
		var _0xc3e6=["\x50\x6C\x65\x61\x73\x65\x20\x53\x65\x6C\x65\x63\x74\x20\x44\x61\x74\x65","\x61\x6C\x65\x72\x74","\x50\x6C\x65\x61\x73\x65\x20\x53\x65\x6C\x65\x63\x74\x20\x52\x6F\x75\x74\x65\x20\x4E\x61\x6D\x65"];
		if(selDate== 0){bootbox[_0xc3e6[1]](_0xc3e6[0]);return false};if(routeno== 0){bootbox[_0xc3e6[1]](_0xc3e6[2]);
		return false;
		}
		return true;
	}
	function getRouteForBunching()
	{
		
		var _0x331f=["\x76\x61\x6C\x75\x65","\x73\x65\x6C\x65\x63\x74\x65\x64\x64\x61\x74\x65","\x67\x65\x74\x45\x6C\x65\x6D\x65\x6E\x74\x42\x79\x49\x64","\x50\x4F\x53\x54","\x69\x6E\x6E\x65\x72\x48\x54\x4D\x4C","\x72\x6F\x75\x74\x65\x6C\x69\x73\x74","\x61\x6A\x61\x78"];var selectedDate=document[_0x331f[2]](_0x331f[1])[_0x331f[0]];$[_0x331f[6]]({type:_0x331f[3],url:'<%=request.getContextPath()%>/getRouteNo?selectedDate='+ selectedDate,success:function(_0x1da1x2){document[_0x331f[2]](_0x331f[5])[_0x331f[4]]= _0x1da1x2}})
	}
	
	function getRouteBusBunchingData() {
		var _0x9e1d=["\x76\x61\x6C","\x23\x73\x65\x6C\x65\x63\x74\x65\x64\x64\x61\x74\x65","\x23\x72\x6F\x75\x74\x65\x6C\x69\x73\x74"];
		var selecteddate=$(_0x9e1d[1])[_0x9e1d[0]]();var routeno=$(_0x9e1d[2])[_0x9e1d[0]]()

		var _0x534a=["\x73\x68\x6F\x77","\x23\x70\x72\x69\x6E\x74\x62\x75\x74\x74\x6F\x6E","\x23\x72\x6F\x75\x74\x65\x42\x75\x6E\x63\x68\x53\x74\x61\x74\x75\x73","\x76\x61\x6C\x75\x65","\x73\x65\x6C\x65\x63\x74\x65\x64\x64\x61\x74\x65","\x67\x65\x74\x45\x6C\x65\x6D\x65\x6E\x74\x42\x79\x49\x64","\x3C\x64\x69\x76\x20\x73\x74\x79\x6C\x65\x3D\x27\x70\x6F\x73\x69\x74\x69\x6F\x6E\x3A\x61\x75\x74\x6F\x3B\x68\x65\x69\x67\x68\x74\x3A\x33\x30\x30\x70\x78\x3B\x77\x69\x64\x74\x68\x3A\x31\x30\x30\x25\x27\x2F\x3E","\x77\x72\x61\x70","\x41\x6C\x6C","\x62\x75\x73\x62\x75\x6E\x63\x68\x69\x6E\x67\x6C\x69\x73\x74\x2E\x61\x63\x74\x69\x6F\x6E\x3F\x72\x6F\x75\x74\x65\x49\x44\x3D","\x26\x73\x65\x6C\x65\x63\x74\x65\x64\x44\x61\x74\x65\x3D","\x62\x6F\x6F\x74\x73\x74\x72\x61\x70","\x5F\x4D\x45\x4E\x55\x5F\x20\x72\x65\x63\x6F\x72\x64\x73","\x50\x72\x65\x76","\x4E\x65\x78\x74","","\x64\x61\x74\x61\x54\x61\x62\x6C\x65","\x66\x6F\x72\x6D\x2D\x63\x6F\x6E\x74\x72\x6F\x6C\x20\x69\x6E\x70\x75\x74\x2D\x6D\x65\x64\x69\x75\x6D\x20\x69\x6E\x70\x75\x74\x2D\x69\x6E\x6C\x69\x6E\x65","\x61\x64\x64\x43\x6C\x61\x73\x73","\x23\x72\x6F\x75\x74\x65\x42\x75\x6E\x63\x68\x53\x74\x61\x74\x75\x73\x5F\x77\x72\x61\x70\x70\x65\x72\x20\x2E\x64\x61\x74\x61\x54\x61\x62\x6C\x65\x73\x5F\x66\x69\x6C\x74\x65\x72\x20\x69\x6E\x70\x75\x74","\x66\x6F\x72\x6D\x2D\x63\x6F\x6E\x74\x72\x6F\x6C\x20\x69\x6E\x70\x75\x74\x2D\x78\x73\x6D\x61\x6C\x6C\x20\x69\x6E\x70\x75\x74\x2D\x69\x6E\x6C\x69\x6E\x65","\x23\x72\x6F\x75\x74\x65\x42\x75\x6E\x63\x68\x53\x74\x61\x74\x75\x73\x5F\x77\x72\x61\x70\x70\x65\x72\x20\x2E\x64\x61\x74\x61\x54\x61\x62\x6C\x65\x73\x5F\x6C\x65\x6E\x67\x74\x68\x20\x73\x65\x6C\x65\x63\x74","\x23\x62\x75\x6E\x63\x68\x73\x74\x61\x74\x75\x73","\x69\x6E\x6E\x65\x72\x48\x54\x4D\x4C","\x73\x65\x6C\x65\x63\x74\x64\x61\x74\x65"];validateflag= validateBunchingReportFields(selecteddate,routeno);if(validateflag== true){$(_0x534a[1])[_0x534a[0]]();$(_0x534a[2])[_0x534a[0]]();var selectedDate=document[_0x534a[5]](_0x534a[4])[_0x534a[3]];var tabl1=$(_0x534a[2])[_0x534a[16]]({"\x61\x4C\x65\x6E\x67\x74\x68\x4D\x65\x6E\x75":[[5,15,20,-1],[_0x534a[8],5,15,20]],"\x69\x44\x69\x73\x70\x6C\x61\x79\x4C\x65\x6E\x67\x74\x68":20,"\x73\x41\x6A\x61\x78\x53\x6F\x75\x72\x63\x65":_0x534a[9]+ routeno+ _0x534a[10]+ selectedDate,"\x73\x50\x61\x67\x69\x6E\x61\x74\x69\x6F\x6E\x54\x79\x70\x65":_0x534a[11],"\x62\x44\x65\x73\x74\x72\x6F\x79":true,"\x6F\x4C\x61\x6E\x67\x75\x61\x67\x65":{"\x73\x4C\x65\x6E\x67\x74\x68\x4D\x65\x6E\x75":_0x534a[12],"\x6F\x50\x61\x67\x69\x6E\x61\x74\x65":{"\x73\x50\x72\x65\x76\x69\x6F\x75\x73":_0x534a[13],"\x73\x4E\x65\x78\x74":_0x534a[14]}},"\x62\x53\x6F\x72\x74":false,"\x62\x46\x69\x6C\x74\x65\x72":false,"\x62\x53\x65\x6C\x65\x63\x74":false,"\x62\x50\x61\x67\x69\x6E\x61\x74\x65":false,"\x6F\x4C\x61\x6E\x67\x75\x61\x67\x65":{"\x73\x5A\x65\x72\x6F\x52\x65\x63\x6F\x72\x64\x73":_0x534a[15],"\x73\x45\x6D\x70\x74\x79\x54\x61\x62\x6C\x65":_0x534a[15]},"\x61\x6F\x43\x6F\x6C\x75\x6D\x6E\x44\x65\x66\x73":[{"\x62\x53\x6F\x72\x74\x61\x62\x6C\x65":false,"\x61\x54\x61\x72\x67\x65\x74\x73":[0,1,2,3,4]}]})[_0x534a[7]](_0x534a[6]);jQuery(_0x534a[19])[_0x534a[18]](_0x534a[17]);jQuery(_0x534a[21])[_0x534a[18]](_0x534a[20]);$(_0x534a[22])[_0x534a[0]]();document[_0x534a[5]](_0x534a[24])[_0x534a[23]]= selectedDate}
	}
 </script>

<title>Insert title here</title>
</head>
<body>
	<div class="page-content-wrapper">
		<div class="page-content">	
	<input type="hidden" id='routeid' name='routeid' /> 
	<input type="hidden" id='routeno' name='routeno' /> 
	<input type="hidden" id='startpoint' name='startpoint' /> 
	<input type="hidden" id='endpoint' name='endpoint' /> 
	<input type="hidden" id='groupedData' name='groupedData' /> 
			<div class="tab-content">
				<div class="row">
					<div class="col-md-12">
						<!-- BEGIN PAGE TITLE & BREADCRUMB-->
						<h3 class="page-title">
							BUS BUNCHING REPORT <small></small>
						</h3>

						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>View Bus Bunching Report
							</div>
						</div>

						<div class="portlet-body form">

							<div class="col-md-12" align="left" style="font-size: 1.1em">
								<span class="help-block" style="color: red; list-style: none"><s:actionmessage /></span>
							</div>
							<!-- BEGIN FORM-->
							<form action="" method="post" class="form-horizontal">
								<div class="form-group">
									<label class="control-label col-md-3">Date<font color="red">*</font></label>
									<div class="col-md-3">
										<div class="input-group input-medium date date-picker"
											style="width: auto" data-date-format="dd-mm-yyyy" onchange="getRouteForBunching()"
											data-date-viewmode="years">
											<input type="text" class="form-control" id="selecteddate" readonly name="selecteselecteddateddate"/> 
												<span class="input-group-btn">
													<button class="btn default" type="button">
														<i class="fa fa-calendar"></i>
													</button> 
											</span>
										</div>
									</div>
										
								</div>
									
								<div class="form-group">
									<label class="col-md-3 control-label">Route Name<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select id="routelist" name="routeno" 
											class="select2_category form-control"
											>
											<option value="0">Route</option>
										</select>

									</div>
									<div class="col-md-1" id="">
										<button type="button" class="btn default" onclick="getRouteBusBunchingData()" style="position: static;">Submit</button>
									</div>
									<div class="col-md-1"id="printbutton">
											<input type='button' class="btn default" id='button1' onclick='printDiv()' value='Print' />													
									</div>
								</div>
								  
                         		<div id="header" class="portlet-body" style="display: none; visibility: hidden;">
										<h4 align="center">Daily Bunching of Buses MIS--01</h4>
										<h4 align="center">Date: <span id="selectdate"></span></h4>
								</div>
<!-- 								<div class="portlet-body" id="scheduleprint" style='height:310px; overflow-y:scroll; display:block;'> -->
									<div class="portlet-body" id="scheduleprint" style='height:310px;  display:block;'>
								<div style="text-align:center">
									<div id="bunchstatus">
										<table class="table table-striped table-bordered table-hover"
											id="routeBunchStatus" style="display: none">
											<thead>
												<tr>
													<th>Sr.No.</th>
													<th>Bus Registration No./Time</th>													
													<th>Route/Schedule No.</th>
													<th>Location</th>
													<th>Actual Alert Time</th>
													<th>Vehicles Count</th>
													<th id="mapshow">Show On Map</th>
													
												</tr>
											</thead>
										</table>
										</div>
									</div>
								</div>
								</form>
							<!-- END FORM-->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<%-- 	<script src="its/WebContent/js/FixedHeader.js" type="text/javascript"></script> --%>
</body>
</html>