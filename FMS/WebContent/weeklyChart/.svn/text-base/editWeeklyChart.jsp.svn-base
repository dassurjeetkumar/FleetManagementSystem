<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
function isUncheck1() {
	var rows = document.getElementById("weeklyid2").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;	
	
	var schname = "";
	var x;
	var cnt = 0;
// 	for (i = 0; i < rows; i++) {
// 		var check=false;
// 		var val = document.getElementById("mon_"+i).value;
// 		 var x = document.getElementById("mon_"+i).checked;	
	
// 		 if(x==true)
// 		{		
// 			 check=true;
// 			 $('#mon_'+i).val(check);
// 		}else{ 
// 			$('#mon_'+i).val(check);
			
// 		}
// 	}	
	for (i = 0; i < rows; i++) {
		x = document.getElementById("mon_" + (i)).checked;
		if (x == true) {
			cnt = cnt + 1;
			schname = document.getElementById("mon1" + (i)).value;
			
		}
	}

	for (i = 0; i < rows; i++) {		
		var val = document.getElementById("mon1" + (i)).value;
		if (schname != val) {
			document.getElementById("mon_" + (i)).disabled = true;
			document.getElementById("mon_" + (i)).checked = false;
		}else{
			document.getElementById("mon_" + (i)).disabled = false;
			
		} 
		
	}
	if (cnt == 0) {
		for (i = 0; i < rows; i++) {
			document.getElementById("mon_" + (i)).disabled = false;
			
		}
	}
	
}
function isUncheck2() {
	var rows = document.getElementById("weeklyid2").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
	var schname = "";
	var cnt = 0;
	/* for (i = 0; i < rows; i++) {
		var check=false;
		var val = document.getElementById("tue_"+i).value;
		 var x = document.getElementById("tue_"+i).checked;
		 if(x==true)
		{
			 check=true;
			 $('#tue_'+i).val(check);
		}else{ 
			$('#tue_'+i).val(check);
		}
	} */
	for (i = 0; i < rows; i++) {
		var x = document.getElementById("tue_" + (i)).checked;
		if (x == true) {
			cnt = cnt + 1;
			schname = document.getElementById("mon1" + (i)).value;
		}
	}

	for (i = 0; i < rows; i++) {
		var val = document.getElementById("mon1" + (i)).value;
		if (schname != val) {
			document.getElementById("tue_" + (i)).disabled = true;
			document.getElementById("tue_" + (i)).checked = false;
		}else{
			document.getElementById("tue_" + (i)).disabled = false;
		} 
	}
	if (cnt == 0) {
		for (i = 0; i < rows; i++) {
			document.getElementById("tue_" + (i)).disabled = false;
		}
	}
}
function isUncheck3() {
	var rows = document.getElementById("weeklyid2").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;	
	var schname = "";
	var cnt = 0;
	/* for (i = 0; i < rows; i++) {
		var check=false;
		var val = document.getElementById("wed_"+i).value;
		 var x = document.getElementById("wed_"+i).checked;
		 if(x==true)
		{
			 check=true;
			 $('#wed_'+i).val(check);
		}else{ 
			$('#wed_'+i).val(check);
		}
	} */
	for (i = 0; i < rows; i++) {		
		var x = document.getElementById("wed_" + (i)).checked;
		if (x == true) {
			cnt = cnt + 1;
			schname = document.getElementById("mon1" + (i)).value;
			
		}
	}

	for (i = 0; i < rows; i++) {
		var val = document.getElementById("mon1" + (i)).value;
		if (schname != val) {
			document.getElementById("wed_" + (i)).disabled = true;
			document.getElementById("wed_" + (i)).checked = false;
		}else{
			document.getElementById("wed_" + (i)).disabled = false;
		} 
	}
	if (cnt == 0) {
		for (i = 0; i < rows; i++) {
			document.getElementById("wed_" + (i)).disabled = false;
		}
	}
}
function isUncheck4() {
	var rows = document.getElementById("weeklyid2").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;	
	var schname = "";
	var cnt = 0;
	/* for (i = 0; i < rows; i++) {
		var check=false;
		var val = document.getElementById("thu_"+i).value;
		 var x = document.getElementById("thu_"+i).checked;
		 if(x==true)
		{
			 check=true;
			 $('#thu_'+i).val(check);
		}else{ 
			$('#thu_'+i).val(check);
		}
	} */
	for (i = 0; i < rows; i++) {		
		var x = document.getElementById("thu_" + (i)).checked;
		if (x == true) {
			cnt = cnt + 1;
			schname = document.getElementById("mon1" + (i)).value;
		}
	}

	for (i = 0; i < rows; i++) {
		var val = document.getElementById("mon1" + (i)).value;
		if (schname != val) {
			document.getElementById("thu_" + (i)).disabled = true;
			document.getElementById("thu_" + (i)).checked = false;
		}else{
			document.getElementById("thu_" + (i)).disabled = false;
		} 
	}
	if (cnt == 0) {
		for (i = 0; i < rows; i++) {
			document.getElementById("thu_" + (i)).disabled = false;
		}
	}
}
function isUncheck5() {
	var rows = document.getElementById("weeklyid2").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;	
	var schname = "";
	var cnt = 0;
	/* for (i = 0; i < rows; i++) {
		var check=false;
		var val = document.getElementById("fri_"+i).value;
		 var x = document.getElementById("fri_"+i).checked;
		 if(x==true)
		{
			 check=true;
			 $('#fri_'+i).val(check);
		}else{ 
			$('#fri_'+i).val(check);
		}
	} */
	for (i = 0; i < rows; i++) {
		var x = document.getElementById("fri_" + (i)).checked;
		if (x == true) {
			cnt = cnt + 1;
			schname = document.getElementById("mon1" + (i)).value;
		}
	}

	for (i = 0; i < rows; i++) {
		var val = document.getElementById("mon1" + (i)).value;
		if (schname != val) {
			document.getElementById("fri_" + (i)).disabled = true;
			document.getElementById("fri_" + (i)).checked = false;
		}else{
			document.getElementById("fri_" + (i)).disabled = false;
		} 
	}
	if (cnt == 0) {
		for (i = 0; i < rows; i++) {
			document.getElementById("fri_" + (i)).disabled = false;
		}
	}
}
function isUncheck6() {
	var rows = document.getElementById("weeklyid2").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;	
	var schname = "";
	var cnt = 0;
	/* for (i = 0; i < rows; i++) {
		var check=false;
		var val = document.getElementById("sat_"+i).value;
		 var x = document.getElementById("sat_"+i).checked;
		 if(x==true)
		{
			 check=true;
			 $('#sat_'+i).val(check);
		}else{ 
			$('#sat_'+i).val(check);
		}
	} */
	for (i = 0; i < rows; i++) {	
		var x = document.getElementById("sat_" + (i)).checked;
		if (x == true) {
			cnt = cnt + 1;
			schname = document.getElementById("mon1" + (i)).value;
		}
	}

	for (i = 0; i < rows; i++) {
		var val = document.getElementById("mon1" + (i)).value;
		if (schname != val) {
			document.getElementById("sat_" + (i)).disabled = true;
			document.getElementById("sat_" + (i)).checked = false;
		}else{
			document.getElementById("sat_" + (i)).disabled = false;
		} 
	}
	if (cnt == 0) {
		for (i = 0; i < rows; i++) {
			document.getElementById("sat_" + (i)).disabled = false;
		}
	}
}
function isUncheck7() {
	var rows = document.getElementById("weeklyid2").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;	
	var schname = "";
	var cnt = 0;
	/* for (i = 0; i < rows; i++) {
		var check=false;
		var val = document.getElementById("sun_"+i).value;
		 var x = document.getElementById("sun_"+i).checked;
		 if(x==true)
		{
			 check=true;
			 $('#sun_'+i).val(check);
		}else{ 
			$('#sun_'+i).val(check);
		}
	} */
	for (i = 0; i < rows; i++) {		
		var x = document.getElementById("sun_" + (i)).checked;
		if (x == true) {
			cnt = cnt + 1;
			schname = document.getElementById("mon1" + (i)).value;
		}
	}

	for (i = 0; i < rows; i++) {
		var val = document.getElementById("mon1" + (i)).value;
		if (schname != val) {
			document.getElementById("sun_" + (i)).disabled = true;
			document.getElementById("sun_" + (i)).checked = false;
		}else{
			document.getElementById("sun_" + (i)).disabled = false;
		} 
	}
	if (cnt == 0) {
		for (i = 0; i < rows; i++) {
			document.getElementById("sun_" + (i)).disabled = false;
		}
	}
}

function isUncheck8() {
	var rows = document.getElementById("weeklyid2").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;	
	var schname = "";
	var cnt = 0;
	/* for (i = 0; i < rows; i++) {
		var check=false;
		var val = document.getElementById("sun_"+i).value;
		 var x = document.getElementById("sun_"+i).checked;
		 if(x==true)
		{
			 check=true;
			 $('#sun_'+i).val(check);
		}else{ 
			$('#sun_'+i).val(check);
		}
	} */
	for (i = 0; i < rows; i++) {		
		var x = document.getElementById("holi_" + (i)).checked;
		if (x == true) {
			cnt = cnt + 1;
			schname = document.getElementById("mon1" + (i)).value;
		}
	}

	for (i = 0; i < rows; i++) {
		var val = document.getElementById("mon1" + (i)).value;
		if (schname != val) {
			document.getElementById("holi_" + (i)).disabled = true;
			document.getElementById("holi_" + (i)).checked = false;
		}else{
			document.getElementById("holi_" + (i)).disabled = false;
		} 
	}
	if (cnt == 0) {
		for (i = 0; i < rows; i++) {
			document.getElementById("holi_" + (i)).disabled = false;
		}
	}
}

function editWeekly(){

	var rows = document.getElementById("weeklyid2").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
	var check=false;
	var cnot=0;
	for (i = 0; i < rows; i++) {
		
		var val = document.getElementById("mon_"+i).value;
		 var x = document.getElementById("mon_"+i).checked;		
		 if(x==true)
		{
			 //alert("hello");
			 check=true;
			 $('#mon_'+i).val(check);
		}else{ 
			$('#mon_'+i).val(check);
			}
	}
	for (i = 0; i < rows; i++) {
		//var check=false;
		var val = document.getElementById("tue_"+i).value;
		 var x = document.getElementById("tue_"+i).checked;
		 if(x==true)
		{
			 check=true;
			 $('#tue_'+i).val(check);
		}else{ 
			$('#tue_'+i).val(check);
		}
	}
	for (i = 0; i < rows; i++) {
		//var check=false;
		var val = document.getElementById("wed_"+i).value;
		 var x = document.getElementById("wed_"+i).checked;
		 if(x==true)
		{
			 check=true;
			 $('#wed_'+i).val(check);
		}else{ 
			$('#wed_'+i).val(check);
		}
	}
	for (i = 0; i < rows; i++) {
		//var check=false;
		var val = document.getElementById("thu_"+i).value;
		 var x = document.getElementById("thu_"+i).checked;
		 if(x==true)
		{
			 check=true;
			 $('#thu_'+i).val(check);
		}else{ 
			$('#thu_'+i).val(check);
		}
	}
	for (i = 0; i < rows; i++) {
		//var check=false;
		var val = document.getElementById("fri_"+i).value;
		 var x = document.getElementById("fri_"+i).checked;
		 if(x==true)
		{
			 check=true;
			 $('#fri_'+i).val(check);
		}else{ 
			$('#fri_'+i).val(check);
		}
	}
	for (i = 0; i < rows; i++) {
		//var check=false;
		var val = document.getElementById("sat_"+i).value;
		 var x = document.getElementById("sat_"+i).checked;
		 if(x==true)
		{
			 check=true;
			 $('#sat_'+i).val(check);
		}else{ 
			$('#sat_'+i).val(check);
		}
	}
	for (i = 0; i < rows; i++) {
		//var check=false;
		var val = document.getElementById("sun_"+i).value;
		 var x = document.getElementById("sun_"+i).checked;
		 if(x==true)
		{			
			 check=true;
			 $('#sun_'+i).val(check);
		}else{ 
			$('#sun_'+i).val(check);
			
		}
		
	}
	for (i = 0; i < rows; i++) {
		//var check=false;
		var val = document.getElementById("holi_"+i).value;
		 var x = document.getElementById("holi_"+i).checked;
		 if(x==true)
		{			
			 check=true;
			 $('#holi_'+i).val(check);
		}else{ 
			$('#holi_'+i).val(check);
			
		}
		
	}
	
	console.log($('input[class="checksun"]:checked').length);
	console.log($('input[class="checksun"]:checked'));
	var isMondayChecked = $('input[class="checkmon"]:checked').length;
	var isTuesdayChecked = $('input[class="checktue"]:checked').length
	var isWednesdayChecked = $('input[class="checkwed"]:checked').length
	var isThursayChecked = $('input[class="checkthu"]:checked').length
	var isFridayChecked = $('input[class="checkfri"]:checked').length
	var isSaturadyChecked = $('input[class="checksat"]:checked').length
	var isSundayChecked = $('input[class="checksun"]:checked').length
	var isHolidayChecked = $('input[class="checkholi"]:checked').length;
		//alert("count"+cnt);
	if (isMondayChecked==0&&isTuesdayChecked==0&&isWednesdayChecked==0&&isThursayChecked==0&&isFridayChecked==0&&isSaturadyChecked==0&&isSundayChecked==0&&isHolidayChecked==0) {
		bootbox.alert("Please Select at least one FormFour per Day");
	} else { 
		var schedulenumber = schedule_id.options[schedule_id.selectedIndex].text;
		document.getElementById("schedulenumber").value = schedulenumber;
		$('#form1').submit(); 
				
			
	 } 
	
}
</script>
<script>

function getShedule()
{		
	   $.ajax({
	           type: "post",
	           url: '<%=request.getContextPath()%>/findAllSchedule',
	           async:false,	           
	           success: function(result) {
	        	   document.getElementById('schedule_id').innerHTML=result;
	        	  
	           }
	       });
	       var prevType="<s:property value="scheduleidlist"/>";			
			 if(prevType!=""){
				 document.getElementById("schedule_id").value=prevType;
				 var orgtypeid = document.getElementById("schedule_id").value;
				 var orgtype = schedule_id.options[schedule_id.selectedIndex].text;
				 document.getElementById('select2-chosen-1').innerHTML=orgtype;
			 }
			 
	
	}
function goView() {
	document.forms[0].action = 'viewWeeklyChart.action';
	document.forms[0].submit();
}		
$(document).ready(function() {
	
	var val = document.getElementById("scheduleidlistid").value;	
	doAjaxCall1();
	document.getElementById("schedule_id").value=val;
	//window.history.pushState("", "", "editWeeklyChart.action");
	});
	</script>
	<script>
	function doAjaxCall() {		
		var dd = document.getElementById("schedule_id").value;		
		if (window.XMLHttpRequest) {
			reqObj = new XMLHttpRequest();
		} else {
			reqObj = new ActiveXObject("Microsoft.XMLHTTP");
		}
		reqObj.onreadystatechange = process1;		
		reqObj.open("POST", "FormFourViewForEdit?scheduleid1=" + dd, true);
		reqObj.send(null);
		
		
	}
	function doAjaxCall1() {		
		var dd = document.getElementById("scheduleidlistid").value;		
		if (window.XMLHttpRequest) {
			reqObj = new XMLHttpRequest();
		} else {
			reqObj = new ActiveXObject("Microsoft.XMLHTTP");
		}

		reqObj.onreadystatechange = process1;
		
		reqObj.open("POST", "FormFourViewForEdit?scheduleid1=" + dd, true);
		reqObj.send(null);
		
		
	}
	function process1() {

		if (reqObj.readyState == 4) {
			document.getElementById("editpage").innerHTML = reqObj.responseText;
			
		}

	}
	
</script>

</head>
<!-- <body onload="getShedule()"> -->
	<body>

	<div class="page-content-wrapper">
		<div class="page-content">
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						SCHEDULE WEEKLY CHART <small></small>
					</h3>

					<!-- END PAGE TITLE & BREADCRUMB-->
					<div class="portlet box grey-cascade">


						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Update Schedule Weekly Chart
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
							<!-- BEGIN FORM-->
						<form action="addEditWeeklyChartAction" id="form1" class="form-horizontal" method="post">	
							<div class="portlet-body">
								<input type="hidden" name="scheduleidlist" id="scheduleidlistid" value='<s:property value="scheduleidlist"/>' />
									<input type="hidden" name="schedulenumber" id="schedulenumber" value="" />
								
									<s:if test="%{updatestaus=='EmptyDataforupdate'}">
									<font color="red">FormFour is not Available for This Schedule</font>
								</s:if>
							
								<div class="form-body">
								<div class="form-group">
										<label class="col-md-3 control-label">Schedule Number.:</label>
										<div class="col-md-4">
											<s:select list="scheduleList" id="schedule_id"
													name="schedule.schedule_id"
													cssClass="select2_category form-control" headerKey="0"
													 onchange="doAjaxCall()" ></s:select>
 											<%-- <select class="select2_category form-control"
 												name="schedule.schedule_id" id="schedule_id" 
												onchange="doAjaxCall()" > 
												<option value="0">select</option> 
 											</select>  --%>
											
										</div>
									</div>
								</div>
								
								<div id="editpage">
								
								</div>
	
							</div>
							<div class="form-actions fluid">
								<div class="col-md-offset-3 col-md-9">
									<button type="button" class="btn blue" onclick="editWeekly()">Save
									</button>
									<button type="button" class="btn default" onclick="goView()">Cancel</button>
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

</body>
</html>




<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
<script type="text/javascript">
function editWeekly(){
	alert("jjjjjjjjjjjj");
	var lent=document.getElementById("checkidd").value;
	//var checkboxes = document.getElementsByName("wed");
	//var lengthbox = lent.length;
	alert("jjjjjjjjjjjj"+lent);
	//var check=false;
	for (i = 0; i < lent; i++) {
		var check=false;
		var val = document.getElementById("mon0"+i).value;
		alert("value"+val);
		 var x = document.getElementById("mon0"+i).checked;
		 alert("value"+x);
		 if(x==true)
		{
			 alert("hello");
			 check=true;
			 $('#mon0'+i).val(check);
		}else{ 
			$('#mon0'+i).val(check);
			//var a=$('#mon0'+i).val();
	
			alert("hellofdsf");
		}
	}
	$('#form1').submit();
	
}
	function goView() {
		document.forms[0].action = 'viewWeeklyChart.action';
		document.forms[0].submit();
	}	
	
	function getShedule()
	{		
	 //var len= document.getElementById('org_type_id').options.length;	 	 
	       $.ajax({
	           type: "get",
	           url: '<%=request.getContextPath()%>/findAllSchedule',
	           async:false,
	           
	           success: function(result) {
	        	   document.getElementById('schedule_id').innerHTML=result;
	        	  // alert(result);
	           }
	       });
	       
	
	}
	function getEditChart()	{
		//alert("jfjjjjjjjjjjjjjj");
		var e = document.getElementById("schedule_id");
		var strUser = e.options[e.selectedIndex].value;
		alert("Hiiiiiiiiii"+strUser);
		 //document.getElementById("tableid").getElementsByTagName("tbody")[0].getElementsByTagName("tr").innerHTML=result;   	  
		//var len= document.getElementById('tableid').options.length;
		//alert(len);		
		       $.ajax({		    	 
		           type: "get",
		          // url: '<%=request.getContextPath()%>/FormFourViewForEdit?schedule_id1='//+strUser,
		           async:false,
		           success: function(result) {
		        	    	   //document.getElementById("tableid").getElementsByTagName("tbody")[0].getElementsByTagName("tr").innerHTML=result;
		        	   alert("resulttttttt"+result);
		        	  document.getElementById('tableid').innerHTML=result;
		           }
		       });			
	}
</script>

<script type="text/javascript">
	function doEditCall() {
			var rolid=document.getElementById("schedule_id").value;
		//var r=document.getElementById("weekid").value;
		//document.getElementById("editweeklyid").getElementsByTagName("thead")[0].getElementsByTagName("tr").innerHTML=result;
		//var cnt = $(":checkbox:checked").length ;
		alert("test---"+rolid);
// 		var rows = document.getElementById("editweeklyid").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
		$('#weeklyChartTableId').dataTable(
				{
					"aaSorting": [
			     	                [1, 'asc']
			     	            ],
					//"aLengthMenu" : [ [ 10, 25, 50, 100 ], [ 10, 25, 50, 100 ], ],
					// set the initial value
					"iDisplayLength" : 10,
					
					"sAjaxSource" : "editFormFourView.action?scheduleid2="+rolid,
					"sPaginationType" : "bootstrap",
					"bDestroy" :true,
					 "bFilter" : false,
					 "bSelect" : false,
					 "bLengthChange" : false,
					 "bPaginate":false, 
					 "oLanguage" : {
							"sZeroRecords" : "",
							"sEmptyTable" : ""
						},
						"aoColumnDefs": [
							             { 'bSortable': false, 'aTargets': [0,3,4,5,6,7,8,9] },
							             { "bSearchable": false, "aTargets": [ 0 ] }
							          ],
					"oLanguage" : {
						"sLengthMenu" : "_MENU_ records",
						"oPaginate" : {
							"sPrevious" : "Prev",
							"sNext" : "Next"
						}
					}

				}).wrap("<div style='position:relative;overflow:auto;height:300px;'/>");
	}
		
</script>
</head>
<body onload="getShedule()">
	<input type="text" id='a'>

	<div class="page-content-wrapper">
		<div class="page-content">
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						WEEKLY CHART <small></small>
					</h3>

					<!-- END PAGE TITLE & BREADCRUMB-->
					<div class="portlet box grey-cascade">


						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Edit Weekly Chart
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
							<!-- BEGIN FORM-->
						<form action="addEditWeeklyChartAction" id="form1" class="form-horizontal" method="post">	
							<div class="portlet-body">
								<input type="hidden" name="requestType" id="requestType"
									value="text" />
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Schedule No.:</label>
										<div class="col-md-4">
 											<select class="select2_category form-control"
 												name="schedule.schedule_id" id="schedule_id" 
												onchange="doEditCall()"> 
												<option value="0">select</option> 
 											</select> 
											
										</div>
									</div>
								</div>
								<br>
								<br>
								
								<table class="table table-striped table-bordered table-hover" id="weeklyChartTableId">
										<thead>
								
									<tr>
										<th style="width1: 8px;"></th>
										<th>Weekly Chart Id</th>
										<th>Form Four Name</th>
										<th>Monday</th>
										<th>Tuesday</th>
										<th>Wednesday</th>
										<th>Thursday</th>
										<th>Friday</th>
										<th>Saturday</th>
										<th>Sunday</th> 
										</tr>
								</thead>

							</table>
								

								<!-- <br>
								<br>
								<br>
								<div id="editpage">
								</div> -->
								


							</div>
							<div class="form-actions fluid">
								<div class="col-md-offset-3 col-md-9">
									<button type="button" class="btn blue" onclick="editWeekly()">Save
									</button>
									<button type="button" class="btn default" onclick="goView()">Cancel</button>
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

</body>
</html>

 --%>


