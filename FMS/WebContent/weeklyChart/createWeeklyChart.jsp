<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">

function createWeekly(){
	var _0x7773=["\x6C\x65\x6E\x67\x74\x68","\x74\x72","\x67\x65\x74\x45\x6C\x65\x6D\x65\x6E\x74\x73\x42\x79\x54\x61\x67\x4E\x61\x6D\x65","\x74\x62\x6F\x64\x79","\x77\x65\x65\x6B\x6C\x79\x69\x64","\x67\x65\x74\x45\x6C\x65\x6D\x65\x6E\x74\x42\x79\x49\x64","\x5B\x6E\x61\x6D\x65\x3D\x27\x77\x65\x65\x6B\x6C\x79\x63\x68\x61\x72\x74\x4C\x69\x73\x74\x5B","\x5D\x2E\x6D\x6F\x6E\x64\x61\x79\x73\x74\x61\x74\x75\x73\x27\x5D\x3A\x63\x68\x65\x63\x6B\x65\x64","\x6C\x65\x6E\x74\x67\x68","\x73\x75\x62\x6D\x69\x74","\x23\x66\x6F\x72\x6D\x31"];var rows=document[_0x7773[5]](_0x7773[4])[_0x7773[2]](_0x7773[3])[0][_0x7773[2]](_0x7773[1])[_0x7773[0]];var len;for(i= 0;i< rows;i++){len= $(_0x7773[6]+ (i)+ _0x7773[7])[_0x7773[0]];alert(_0x7773[8]+ len)};alert(_0x7773[8]+ len);$(_0x7773[10])[_0x7773[9]]()
}
	function goView()
	{
		var _0xb5ad=["\x61\x63\x74\x69\x6F\x6E","\x66\x6F\x72\x6D\x73","\x76\x69\x65\x77\x57\x65\x65\x6B\x6C\x79\x43\x68\x61\x72\x74\x2E\x61\x63\x74\x69\x6F\x6E","\x73\x75\x62\x6D\x69\x74"];document[_0xb5ad[1]][0][_0xb5ad[0]]= _0xb5ad[2];document[_0xb5ad[1]][0][_0xb5ad[3]]()
	}
	function getSheduleno()
	{		
	 	 
		var _0xbd0e=["\x70\x6F\x73\x74","\x69\x6E\x6E\x65\x72\x48\x54\x4D\x4C","\x73\x63\x68\x65\x64\x75\x6C\x65\x69\x64\x31","\x67\x65\x74\x45\x6C\x65\x6D\x65\x6E\x74\x42\x79\x49\x64","\x61\x6A\x61\x78"];$[_0xbd0e[4]]({type:_0xbd0e[0],url:'<%=request.getContextPath()%>/findAllSchedule',async:false,success:function(_0xf0f2x1){document[_0xbd0e[3]](_0xbd0e[2])[_0xbd0e[1]]= _0xf0f2x1}})

	}
	$(document).ready(function() {
		var _0x58c5=["","\x63\x72\x65\x61\x74\x65\x57\x65\x65\x6B\x6C\x79\x43\x68\x61\x72\x74\x2E\x61\x63\x74\x69\x6F\x6E","\x70\x75\x73\x68\x53\x74\x61\x74\x65","\x68\x69\x73\x74\x6F\x72\x79"];
		window[_0x58c5[3]][_0x58c5[2]](_0x58c5[0],_0x58c5[0],_0x58c5[1])
	});
</script>

<script>
	function doAjaxCall() {
		//alert("hiiii111111111111i");
		var _0x34fb=["\x76\x61\x6C\x75\x65","\x73\x63\x68\x65\x64\x75\x6C\x65\x69\x64\x31","\x67\x65\x74\x45\x6C\x65\x6D\x65\x6E\x74\x42\x79\x49\x64","\x58\x4D\x4C\x48\x74\x74\x70\x52\x65\x71\x75\x65\x73\x74","\x4D\x69\x63\x72\x6F\x73\x6F\x66\x74\x2E\x58\x4D\x4C\x48\x54\x54\x50","\x6F\x6E\x72\x65\x61\x64\x79\x73\x74\x61\x74\x65\x63\x68\x61\x6E\x67\x65","\x50\x4F\x53\x54","\x46\x6F\x72\x6D\x46\x6F\x75\x72\x56\x69\x65\x77\x6C\x69\x73\x74\x66\x6F\x72\x77\x65\x65\x6B\x6C\x79\x3F\x73\x63\x68\x65\x64\x75\x6C\x65\x69\x64\x3D","\x6F\x70\x65\x6E","\x73\x65\x6E\x64"];var dd=document[_0x34fb[2]](_0x34fb[1])[_0x34fb[0]];if(window[_0x34fb[3]]){reqObj=  new XMLHttpRequest()}else {reqObj=  new ActiveXObject(_0x34fb[4])};reqObj[_0x34fb[5]]= process1;reqObj[_0x34fb[8]](_0x34fb[6],_0x34fb[7]+ dd,true);reqObj[_0x34fb[9]](null)
	}
	function process1() {

		var _0xa175=["\x72\x65\x61\x64\x79\x53\x74\x61\x74\x65","\x69\x6E\x6E\x65\x72\x48\x54\x4D\x4C","\x63\x72\x65\x61\x74\x65\x70\x61\x67\x65","\x67\x65\x74\x45\x6C\x65\x6D\x65\x6E\x74\x42\x79\x49\x64","\x72\x65\x73\x70\x6F\x6E\x73\x65\x54\x65\x78\x74"];if(reqObj[_0xa175[0]]== 4){document[_0xa175[3]](_0xa175[2])[_0xa175[1]]= reqObj[_0xa175[4]]}
	}
</script>

<script>
	function isUncheck1(elementId) {
		var _0xe68e=["\x6C\x65\x6E\x67\x74\x68","\x74\x72","\x67\x65\x74\x45\x6C\x65\x6D\x65\x6E\x74\x73\x42\x79\x54\x61\x67\x4E\x61\x6D\x65","\x74\x62\x6F\x64\x79","\x77\x65\x65\x6B\x6C\x79\x69\x64","\x67\x65\x74\x45\x6C\x65\x6D\x65\x6E\x74\x42\x79\x49\x64","","\x63\x68\x65\x63\x6B\x65\x64","\x6D\x6F\x6E\x5F","\x76\x61\x6C\x75\x65","\x6D\x6F\x6E","\x64\x69\x73\x61\x62\x6C\x65\x64"];var rows=document[_0xe68e[5]](_0xe68e[4])[_0xe68e[2]](_0xe68e[3])[0][_0xe68e[2]](_0xe68e[1])[_0xe68e[0]];var schname=_0xe68e[6];var x;var cnt=0;for(i= 0;i< rows;i++){x= document[_0xe68e[5]](_0xe68e[8]+ (i))[_0xe68e[7]];if(x== true){cnt= cnt+ 1;schname= document[_0xe68e[5]](_0xe68e[10]+ (i))[_0xe68e[9]]}};for(i= 0;i< rows;i++){var val=document[_0xe68e[5]](_0xe68e[10]+ (i))[_0xe68e[9]];if(schname!= val){document[_0xe68e[5]](_0xe68e[8]+ (i))[_0xe68e[11]]= true}};if(cnt== 0){for(i= 0;i< rows;i++){document[_0xe68e[5]](_0xe68e[8]+ (i))[_0xe68e[11]]= false}}
		

	}

	function isUncheck2(elementId) {
		var _0x4368=["\x6C\x65\x6E\x67\x74\x68","\x74\x72","\x67\x65\x74\x45\x6C\x65\x6D\x65\x6E\x74\x73\x42\x79\x54\x61\x67\x4E\x61\x6D\x65","\x74\x62\x6F\x64\x79","\x77\x65\x65\x6B\x6C\x79\x69\x64","\x67\x65\x74\x45\x6C\x65\x6D\x65\x6E\x74\x42\x79\x49\x64","","\x63\x68\x65\x63\x6B\x65\x64","\x74\x75\x65\x5F","\x76\x61\x6C\x75\x65","\x6D\x6F\x6E","\x64\x69\x73\x61\x62\x6C\x65\x64"];var rows=document[_0x4368[5]](_0x4368[4])[_0x4368[2]](_0x4368[3])[0][_0x4368[2]](_0x4368[1])[_0x4368[0]];var schname=_0x4368[6];var cnt=0;for(i= 0;i< rows;i++){var x=document[_0x4368[5]](_0x4368[8]+ (i))[_0x4368[7]];if(x== true){cnt= cnt+ 1;schname= document[_0x4368[5]](_0x4368[10]+ (i))[_0x4368[9]]}};for(i= 0;i< rows;i++){var val=document[_0x4368[5]](_0x4368[10]+ (i))[_0x4368[9]];if(schname!= val){document[_0x4368[5]](_0x4368[8]+ (i))[_0x4368[11]]= true}};if(cnt== 0){for(i= 0;i< rows;i++){document[_0x4368[5]](_0x4368[8]+ (i))[_0x4368[11]]= false}}

	}
	function isUncheck3(elementId) {
		var _0x7f3f=["\x6C\x65\x6E\x67\x74\x68","\x74\x72","\x67\x65\x74\x45\x6C\x65\x6D\x65\x6E\x74\x73\x42\x79\x54\x61\x67\x4E\x61\x6D\x65","\x74\x62\x6F\x64\x79","\x77\x65\x65\x6B\x6C\x79\x69\x64","\x67\x65\x74\x45\x6C\x65\x6D\x65\x6E\x74\x42\x79\x49\x64","","\x63\x68\x65\x63\x6B\x65\x64","\x77\x65\x64\x5F","\x76\x61\x6C\x75\x65","\x6D\x6F\x6E","\x64\x69\x73\x61\x62\x6C\x65\x64"];var rows=document[_0x7f3f[5]](_0x7f3f[4])[_0x7f3f[2]](_0x7f3f[3])[0][_0x7f3f[2]](_0x7f3f[1])[_0x7f3f[0]];var schname=_0x7f3f[6];var cnt=0;for(i= 0;i< rows;i++){var x=document[_0x7f3f[5]](_0x7f3f[8]+ (i))[_0x7f3f[7]];if(x== true){cnt= cnt+ 1;schname= document[_0x7f3f[5]](_0x7f3f[10]+ (i))[_0x7f3f[9]]}};for(i= 0;i< rows;i++){var val=document[_0x7f3f[5]](_0x7f3f[10]+ (i))[_0x7f3f[9]];if(schname!= val){document[_0x7f3f[5]](_0x7f3f[8]+ (i))[_0x7f3f[11]]= true}};if(cnt== 0){for(i= 0;i< rows;i++){document[_0x7f3f[5]](_0x7f3f[8]+ (i))[_0x7f3f[11]]= false}}
	}
	function isUncheck4(elementId) {
		var _0xb504=["\x6C\x65\x6E\x67\x74\x68","\x74\x72","\x67\x65\x74\x45\x6C\x65\x6D\x65\x6E\x74\x73\x42\x79\x54\x61\x67\x4E\x61\x6D\x65","\x74\x62\x6F\x64\x79","\x77\x65\x65\x6B\x6C\x79\x69\x64","\x67\x65\x74\x45\x6C\x65\x6D\x65\x6E\x74\x42\x79\x49\x64","","\x63\x68\x65\x63\x6B\x65\x64","\x74\x68\x75\x5F","\x76\x61\x6C\x75\x65","\x6D\x6F\x6E","\x64\x69\x73\x61\x62\x6C\x65\x64"];var rows=document[_0xb504[5]](_0xb504[4])[_0xb504[2]](_0xb504[3])[0][_0xb504[2]](_0xb504[1])[_0xb504[0]];var schname=_0xb504[6];var cnt=0;for(i= 0;i< rows;i++){var x=document[_0xb504[5]](_0xb504[8]+ (i))[_0xb504[7]];if(x== true){cnt= cnt+ 1;schname= document[_0xb504[5]](_0xb504[10]+ (i))[_0xb504[9]]}};for(i= 0;i< rows;i++){var val=document[_0xb504[5]](_0xb504[10]+ (i))[_0xb504[9]];if(schname!= val){document[_0xb504[5]](_0xb504[8]+ (i))[_0xb504[11]]= true}};if(cnt== 0){for(i= 0;i< rows;i++){document[_0xb504[5]](_0xb504[8]+ (i))[_0xb504[11]]= false}}
	}
	function isUncheck5(elementId) {
		var _0xabd1=["\x6C\x65\x6E\x67\x74\x68","\x74\x72","\x67\x65\x74\x45\x6C\x65\x6D\x65\x6E\x74\x73\x42\x79\x54\x61\x67\x4E\x61\x6D\x65","\x74\x62\x6F\x64\x79","\x77\x65\x65\x6B\x6C\x79\x69\x64","\x67\x65\x74\x45\x6C\x65\x6D\x65\x6E\x74\x42\x79\x49\x64","","\x63\x68\x65\x63\x6B\x65\x64","\x66\x72\x69\x5F","\x76\x61\x6C\x75\x65","\x6D\x6F\x6E","\x64\x69\x73\x61\x62\x6C\x65\x64"];var rows=document[_0xabd1[5]](_0xabd1[4])[_0xabd1[2]](_0xabd1[3])[0][_0xabd1[2]](_0xabd1[1])[_0xabd1[0]];var schname=_0xabd1[6];var cnt=0;for(i= 0;i< rows;i++){var x=document[_0xabd1[5]](_0xabd1[8]+ (i))[_0xabd1[7]];if(x== true){cnt= cnt+ 1;schname= document[_0xabd1[5]](_0xabd1[10]+ (i))[_0xabd1[9]]}};for(i= 0;i< rows;i++){var val=document[_0xabd1[5]](_0xabd1[10]+ (i))[_0xabd1[9]];if(schname!= val){document[_0xabd1[5]](_0xabd1[8]+ (i))[_0xabd1[11]]= true}};if(cnt== 0){for(i= 0;i< rows;i++){document[_0xabd1[5]](_0xabd1[8]+ (i))[_0xabd1[11]]= false}}
	}
	function isUncheck6(elementId) {
		var _0x36c7=["\x6C\x65\x6E\x67\x74\x68","\x74\x72","\x67\x65\x74\x45\x6C\x65\x6D\x65\x6E\x74\x73\x42\x79\x54\x61\x67\x4E\x61\x6D\x65","\x74\x62\x6F\x64\x79","\x77\x65\x65\x6B\x6C\x79\x69\x64","\x67\x65\x74\x45\x6C\x65\x6D\x65\x6E\x74\x42\x79\x49\x64","","\x63\x68\x65\x63\x6B\x65\x64","\x73\x61\x74\x5F","\x76\x61\x6C\x75\x65","\x6D\x6F\x6E","\x64\x69\x73\x61\x62\x6C\x65\x64"];var rows=document[_0x36c7[5]](_0x36c7[4])[_0x36c7[2]](_0x36c7[3])[0][_0x36c7[2]](_0x36c7[1])[_0x36c7[0]];var schname=_0x36c7[6];var cnt=0;for(i= 0;i< rows;i++){var x=document[_0x36c7[5]](_0x36c7[8]+ (i))[_0x36c7[7]];if(x== true){cnt= cnt+ 1;schname= document[_0x36c7[5]](_0x36c7[10]+ (i))[_0x36c7[9]]}};for(i= 0;i< rows;i++){var val=document[_0x36c7[5]](_0x36c7[10]+ (i))[_0x36c7[9]];if(schname!= val){document[_0x36c7[5]](_0x36c7[8]+ (i))[_0x36c7[11]]= true}};if(cnt== 0){for(i= 0;i< rows;i++){document[_0x36c7[5]](_0x36c7[8]+ (i))[_0x36c7[11]]= false}}
	}
	function isUncheck7(elementId) {
		var _0x9891=["\x6C\x65\x6E\x67\x74\x68","\x74\x72","\x67\x65\x74\x45\x6C\x65\x6D\x65\x6E\x74\x73\x42\x79\x54\x61\x67\x4E\x61\x6D\x65","\x74\x62\x6F\x64\x79","\x77\x65\x65\x6B\x6C\x79\x69\x64","\x67\x65\x74\x45\x6C\x65\x6D\x65\x6E\x74\x42\x79\x49\x64","","\x63\x68\x65\x63\x6B\x65\x64","\x73\x75\x6E\x5F","\x76\x61\x6C\x75\x65","\x6D\x6F\x6E","\x64\x69\x73\x61\x62\x6C\x65\x64"];var rows=document[_0x9891[5]](_0x9891[4])[_0x9891[2]](_0x9891[3])[0][_0x9891[2]](_0x9891[1])[_0x9891[0]];var schname=_0x9891[6];var cnt=0;for(i= 0;i< rows;i++){var x=document[_0x9891[5]](_0x9891[8]+ (i))[_0x9891[7]];if(x== true){cnt= cnt+ 1;schname= document[_0x9891[5]](_0x9891[10]+ (i))[_0x9891[9]]}};for(i= 0;i< rows;i++){var val=document[_0x9891[5]](_0x9891[10]+ (i))[_0x9891[9]];if(schname!= val){document[_0x9891[5]](_0x9891[8]+ (i))[_0x9891[11]]= true}};if(cnt== 0){for(i= 0;i< rows;i++){document[_0x9891[5]](_0x9891[8]+ (i))[_0x9891[11]]= false}}
	}
</script>
</head>
<body onload="getSheduleno()">
	<div class="page-content-wrapper">
		<div class="page-content">

			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						SCHEDULE WEEKLY CHART<small></small>
					</h3>
	<!-- END PAGE TITLE & BREADCRUMB-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Create Schedule Weekly Chart
							</div>
						</div>

						<div class="portlet-body form">
							<s:if test="hasActionErrors()">
								<div class="alert alert-danger">
									<button class="close" data-close="alert"></button>
									<span> <s:actionerror />
									</span>
								</div>


							</s:if>
					

					<form action="saveWeeklyChart" id="form1" class="form-horizontal" method="post">
						<div class="portlet-body">
							<input type="hidden" name="requestType" id="requestType"
								value="text" />
							<s:if test="%{insertstaus=='duplicate'}">
								<font color="red">Could not Create Duplicate Weekly Chart
									for This Schedule</font>
							</s:if>
							<s:if test="%{insertstaus=='EmptyData'}">
								<font color="red">FormFour is not Available for This
									Schedule</font>
							</s:if>
							<div class="form-body">
								<div class="form-group">
									<label class="col-md-3 control-label">Schedule Number.:</label>
									<div class="col-md-4">
										<select class="select2_category form-control"
											name="schedule.schedule_id" id="scheduleid1"
											onchange="doAjaxCall()">
											<option value="0">select</option>
										</select>
										
									</div>
								</div>
							</div>
							<div id="createpage"></div>
						</div>
						<div class="form-actions fluid">
							<div class="col-md-offset-3 col-md-9">
								<button type="button" id="save" class="btn blue" onclick="createWeekly()">Save</button>
								<button type="button" class="btn default" onclick="goView()">Cancel</button>
							</div>
						</div>

					</form>
</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>


<!-- 
function isUncheck1(elementId) {
		/* var checkb=document.getElementById(elementId);
		alert("hhhhhhh"+checkb); */
		alert("hhhhhhh"+elementId); 
		var w;
		var rows = document.getElementById("weeklyid").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
		/* alert("hhhhhhh"+value);
		var v=document.getElementById("weeklyid").rows.item(1).innerHTML;
		alert("hhhhhhh"+document.getElementById("schedulename10").value); */
		for (j = 1; j < rows; j++) {
			w = document.getElementById("weeklyid").rows[i].cells[0].innerHTML;
		}
			for (i = 0; i < rows; i++) {
				var val = document.getElementById("schedulename" + (i)).value;
				
				alert( w.trim());
				alert( val);
				if( (val == w.trim())) {
					alert("222");
					document.getElementById("stat_" + (i)).disabled = true;
					//document.getElementById("stat_" + (i)).disabled = false;
				}
			}
		
	}
	


function isUncheck2(aa) {

		var checkboxes = document.getElementsByName("mon");
		var lengthbox = checkboxes.length;
		for (i = 0; i < lengthbox; i++) {
			var val = document.getElementById("mon" + (i)).value;
			if (val != aa) {
				
				document.getElementById("mon" + (i)).disabled = true;
			}
			
			

		}
	}
	function isUncheck3(aa) {

		var checkboxes = document.getElementsByName("tue");
		var lengthbox = checkboxes.length;
		for (i = 0; i < lengthbox; i++) {
			var val = document.getElementById("tue" + (i)).value;
			if (val != aa) {
				
				document.getElementById("tue" + (i)).disabled = true;
			}
		}
	}
	function isUncheck4(aa) {

		var checkboxes = document.getElementsByName("wed");
		var lengthbox = checkboxes.length;
		for (i = 0; i < lengthbox; i++) {
			var val = document.getElementById("wed" + (i)).value;
			if (val != aa) {
				
				document.getElementById("wed" + (i)).disabled = true;
			}
		}
	}
	function isUncheck5(aa) {

		var checkboxes = document.getElementsByName("thu");
		var lengthbox = checkboxes.length;
		for (i = 0; i < lengthbox; i++) {
			var val = document.getElementById("thu" + (i)).value;
			if (val != aa) {
				
				document.getElementById("thu" + (i)).disabled = true;
			}
		}
	}
	function isUncheck6(aa) {

		var checkboxes = document.getElementsByName("fri");
		var lengthbox = checkboxes.length;
		for (i = 0; i < lengthbox; i++) {
			var val = document.getElementById("fri" + (i)).value;
			if (val != aa) {
				
				document.getElementById("fri" + (i)).disabled = true;
			}
		}
	}
	function isUncheck7(aa) {

		var checkboxes = document.getElementsByName("sat");
		var lengthbox = checkboxes.length;
		for (i = 0; i < lengthbox; i++) {
			var val = document.getElementById("sat" + (i)).value;
			if (val != aa) {
				
				document.getElementById("sat" + (i)).disabled = true;
			}
		}
	} -->
