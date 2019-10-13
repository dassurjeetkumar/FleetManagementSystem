<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
<link rel="stylesheet"	href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
<style type="text/css">
.help-block {
	color: red;
}
.help-block,ul,li {
	list-style: none;
}

</style>
<script>
function getVehicle(depotID)
{
// 	alert("in getVehicle");
	$('#select2-chosen-3').html("Select");
	var depotid=document.getElementById('depotId').value;
	 if(depotid!=0) {
		 $("#msg").html("Please wait...");
   	$.ajax({
	   type: "POST",
       url: '<%=request.getContextPath()%>/getDepotVehicle?val='+depotid,
       success: function(result) {
    	   $("#msg").html("");
    	   document.getElementById('vehiclelist').innerHTML=result;
       }
   });
}
}
function getDepotList(orgId){
//alert("Hi=="+orgId);
$('#select2-chosen-2').html("Select");
//$('#select2-chosen-3').html("Select");
	 var val=document.getElementById('divisionlist').value;
		 if(val!=0) {
      $.ajax({
          type: "post",
          url: '<%=request.getContextPath()%>/getScheduleMappingNew!getDepotList?val=' + val,
			success : function(result) {
				document.getElementById('depotlist1').innerHTML = result;
				
			}
		});
	}
}
// function getScheduleListForScheduleMapping(orgId){

// 	 var val=document.getElementById('depotlist1').value;
	 
// 		 if(val!=0) {
// 			 $('#pageLoader').show();
//      $.ajax({
//          type: "post",
<%--          url: '<%=request.getContextPath()%>/getAllDataFromScheduleMapping', --%>
// 			success : function(result) {
// 				  $('#pageLoader').hide();
// 				console.log(result);
				
// 			}
// 		});
// 	}
// }
// function getShiftListForScheduleMapping(orgId){

// 	 var val=document.getElementById('schedulelist1').value;
// 		 if(val!=0) {
//     $.ajax({
//         type: "post",
<%--         url: '<%=request.getContextPath()%>/getShiftListForScheduleMapping?val=' + val, --%>
// 			success : function(result) {
// 				document.getElementById('shiftlist1').innerHTML = result;
				
// 			}
// 		});
// 	}
// }

function check(){
	$('#schedulemappingnewdatatable').show();
	$('#save').show();
	if(document.getElementById("divisionlist").value=="0")
	{
		
	bootbox.alert("Please Select Division");
	return false;
	}
	if(document.getElementById("depotlist1").value=="0")
		{
		bootbox.alert("Please Select Depot");
		return false;
		}
// 	if(document.getElementById("startdate").value=="0")
// 	{
// 	bootbox.alert("Please Select Date");
// 	return false;
// 	}
	
// 	if(document.getElementById("schedulelist1").value=="0")
// 	{
// 		bootbox.alert("Please Select Schedule");
// 	return false;
// 	}
// 	if(document.getElementById("shiftlist1").value=="0")
// 	{
// 	bootbox.alert("Please Select Shift");
// 	return false;
// 	}
// 	if(document.getElementById("vehiclelist1").value=="0")
// 	{
// 	bootbox.alert("Please Select Vehicle No");
// 	return false;
// 	}
// 	var e = document.getElementById("vehiclelist1");
// 	var vehicle_no = e.options[e.selectedIndex].text;
// 	document.getElementById("vehicleNO").value=vehicle_no;


var division=$("#divisionlist").val();
var depot=$("#depotlist1").val();
var type=$("#scheduletype").val();

//alert("aa");
$('#schedulemappingnewdatatable').dataTable({
	"aaSorting": [
                  [0, 'asc']
              ],
    "aLengthMenu": [
        [10, 20, 50, 100],
        [10, 20, 50, 100] // change per page values here
    ],
    // set the initial value
    "iDisplayLength": 10, 
    "bProcessing" : false,
    "bServerSide" : true,
    "bDestroy" : true,
    "searching": false,
    "bFilter" : false,
    "sAjaxSource" : "getScheduleMappingNew!getScheduleDetails?division="+division+"&depot="+depot+"&type="+type,
    "sPaginationType": "bootstrap",
    "bSelect" : false,
	"bPaginate" : false,
	"bInfo": false,
	"fnDrawCallback": function (oSettings) {
		//alert( 'DataTables has redrawn the table' );
		 $('.select2_category1').select2();
		// alert( 'DataTables has redrawn the table111' );
		},
    "oLanguage": {
        "sLengthMenu": "_MENU_ records",
        "oPaginate": {
            "sPrevious": "Prev",
            "sNext": "Next"
        }
    },
   "aoColumnDefs": [
        { 'bSortable': false, 'aTargets': [0] },
        { "bSearchable": false, "aTargets": [ 0 ] }
    ]
    
   

    
});            
//jQuery('#schedulemappingnewdatatable_wrapper .dataTables_filter input').addClass("form-control input-medium input-inline"); // modify table search input
//jQuery('#schedulemappingnewdatatable_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown






	//document.getElementById("form1").action='AjaxDailyScheduleMapping.action?startdate='+dd1+'&division='+division+'&depot='+depot;
// 	document.getElementById("form1").action="ScheduleMappingList.action";
	//document.getElementById("form1").submit();
}



function getVehicleNo(id,value,size){
	//var data=value.split("@");
	//alert("id=="+id+"==="+value+"===="+size);
	
	for(var i=0;i<=size;i++){
		if(i!=id && value !=0){
			
		var dropdownElement = $("#"+i+"");
		dropdownElement.find('option[value='+value+']').remove();
		}
	}
}

</script>

<input type="hidden" name="id" id="id" />
<div class="page-content-wrapper">
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "getScheduleMappingNew.action");
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
	<%/* if (access.equalsIgnoreCase("Y")){ */%>
		<%-- %if (edit.equalsIgnoreCase("Y")){ --%>
		
		 <div id="pageLoader" class="modal fade in" tabindex="-1" role="dialog" aria-labelledby="myModalLabel3" aria-hidden="false">
								<div class="modal-dialog">
									<div class="modal-content">
										
										<div class="modal-body">
											<p>
												<img src="assets/images/loader1.gif" align="top" style="margin-left:100px;"/> 
											</p>
											<p style='text-align:center'>Please Wait........</p>
										</div>
										
									</div>
								</div>
							</div>
		
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
					SCHEDULE MAPPING NEW
				</h3>
			</div>
		</div>
		<div class="row">
	
			<div class="col-md-12">
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i> Create Schedule Mapping New 
						</div>
					</div>
					<div class="portlet-body form">
<!-- 						<form action="saveEditScheduleMappingAction,saveScheduleMappingActionWebServiceCall,AjaxDailyScheduleMapping" class="form-horizontal form-row-seperated" method="post"> -->
                     <form action="getScheduleMappingNew!getsavedata" class="form-horizontal form-row-seperated" method="post" name="f1" id="form1"> 
							<b>
								<font color="green"> <s:actionmessage/></font>
								<span id="errorMsg" style="color:red;word-wrap: break-word;" ><s:actionerror/></span>
							</b>
							<div class="form-body">
							
								<%-- <div class="form-group">
									<label class="control-label col-md-3">Schedule No.</label>
									<div class="col-md-3">
										<input type="text" class="form-control" readonly="" name="scheduleMapDetails.schedule_no"
												value='<s:property value="scheduleMapDetails.schedule_no"/>'>
									</div>
								</div> --%>
								
								<div class="form-group">
								
								<input type="hidden" name="schedule_type_id" value='<s:property value="schedulemp.schedule_type_id"/>'>
								<input type="hidden" name="shift_type_id" value='<s:property value="schedulemp.shift_type_id"/>'>
								<input type="hidden" name="depot_id" id="depotId" value='<s:property value="schedulemp.depot_id"/>'>
								
								<div class="form-group">
										<label class="col-md-3 control-label">Division<font
											color="red">*</font></label>
										<div class="col-md-3">
											<s:select list="divisionlist" id="divisionlist"
												name="org_chart_id" cssClass="select2_category form-control"
												onchange="getDepotList(this.value)" headerKey="0"
												headerValue="Select"></s:select>

										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">Depot<font
											color="red">*</font></label>
										<div class="col-md-3">
											<select id="depotlist1"
												class="select2_category form-control" name="depotlist1" >
												<option value="0">select</option>
											</select>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Schedule Type<font
											color="red">*</font></label>
										<div class="col-md-3">
<%-- 											<select list="scheduletypelist" id="scheduletype" --%>
<%-- 												class="select2_category form-control" name="scheduletype" > --%>
<!-- 												<option value="0">select</option> -->
<%-- 											</select> --%>
											<s:select list="scheduletypelist" id="scheduletype"
												name="scheduletype" cssClass="select3_category form-control"
												 headerKey="0"
												headerValue="--ALL--"></s:select>
											
										</div>
									</div>
								
								
									
								
									<div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9">
											<button type="button" class="btn blue" onclick="check()">Submit</button>
											<button type="button" class="btn default"	onclick="callCancell()">Cancel</button>
										</div>
									</div>
								</div>
							</div>
							
							<div>
							<table class="table table-striped table-bordered table-hover" id="schedulemappingnewdatatable" style="display: none">
								<thead>
							<tr>
<!-- 								<th > -->
<!-- 								</th> -->
								<th>Sr No</th>
							 <th>Schedule No</th>
								<th>Vehicle No</th>
<!-- 								<th>Reason</th> -->
							</tr>
							</thead>
							</table>
					</div>
					<div align="center" style="display: none" id="save">
					<button type="submit" class="btn green" >Save</button>
					</div>
							
						</form>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	function callCancell() {

		window.location.href = 'getScheduleMappingView.action';
	}
</script>
<script>
$(document).ready(function() {
	
	
	   var w=$('#errorMsg span').html();
	   //alert(w);
	    w=w.replace(/@/g,"<br>");

	   $('#errorMsg').html(''+w+'');
  
});
</script>
