<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%-- < sc="<%=request.getContextPath()%>/its/WebContent/assets/admin/pages/scrips/table-managed-admin.js" /> --%>

<!DOCTYPE html>
<html>
<head>
<%-- <script src="assets/admin/pages/scripts/table-managed-admin.js" type="text/javascript"></script> --%>
<script src="https://code.jquery.com/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/responsive/1.0.7/js/dataTables.responsive.min.js" type="text/javascript"></script>
<script type="text/javascript">


function callEdit(){
	
	var cnt = $(":checkbox:checked").length;
	var val;
	if (cnt == 0) {
		bootbox
				.alert("Please Select Checkbox To Edit");
	} else if (cnt > 1) {
		bootbox
				.alert("Please Select One Checkbox To Edit");
	} else {
		$("input[type='checkbox']:checked").each(
				function() {

					val = this.value;
				});
		if(isEligibleForOpertion(val)){
		document.forms[0].action = "editEmployee.action?empid="+ val;
		document.forms[0].submit();
		}else{
			bootbox.alert("Please Select Valid Record");
		}

				
}
}


function callCancel() {
var val = [];
$(':radio:checked').each(function(i) {
	val = $(this).val();
});

if (check(0)) {
		bootbox	.confirm("Are you sure want to delete ScheduleMapping?",function(result) {
		if (result == true) { 
			document.forms[0].action = "DeleteScheduleMapping.action?value="+ val;
		document.forms[0].submit();
		}
});
}
}

function check() {

var chckbxCount = $("input:checked").length;
if (chckbxCount > 0 && chckbxCount > 1) {
	bootbox.alert("Select only one ScheduleMapping...!")
	return false;
} else if (chckbxCount > 0 && chckbxCount == 1) {
	return true;
} else {
	bootbox.alert("Please select ScheduleMapping");
	return false;
}

}
 
 function getDepot(orgId){
	// $('#select2-chosen-2').html("Select");
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 alert(orgId);
		 var val=document.getElementById('divisionlist').value;
			 if(val!=0) {
	        $.ajax({
	            type: "get",
	            url: '<%=request.getContextPath()%>/getDepot?val=' + val,
				success : function(result) {
					document.getElementById('depotlist').innerHTML = result;
				}
			});
		}

	}
 function getvehicle(orgId){
	 $('#select2-chosen-2').html("Select");
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 var val=document.getElementById('depotlist').value;
			 if(val!=0) {
	        $.ajax({
	            type: "get",
	            url: '<%=request.getContextPath()%>/getVehicle?val=' + val,
				success : function(result) {
					document.getElementById('vehiclelist').innerHTML = result;
				}
			});
		}

	}
 
	function printDiv() {     
	    
	    //  document.getElementById("print").style.visibility='hidden';     
	    var divElements = document.getElementById("header").innerHTML;
	    divElements += document.getElementById("printid").innerHTML;

	    var mywindow = window.open("<%=request.getContextPath()%>/Print.jsp");
	   
	    mywindow.onload = function() {
	    mywindow.document.body.innerHTML=divElements;
	    mywindow.document.body.innerHTML=divElements;
	    mywindow.print();
	    mywindow.close();
	    }
	            
	}

	
// 	 function getScheduledTripStatusDataOnSubmit()
//   	{
//      $('#itsmapping').dataTable({
// 			"aaSorting" : [ [ 1, 'asc' ] ],
// 			"aLengthMenu" : [ [ 10, 25, 50, 100 ], [ 10, 25, 50, 100 ] // change
// 																// per page
// 																// values
// 																// here
// 			],
// 			// set the initial value
// 			"iDisplayLength" : 10,
// 			"bProcessing" : true,
// 			"bServerSide" : true,
// 			"sAjaxSource" : "ShowMappingitsscheduleAjax.action",
			
// 			"sPaginationType" : "bootstrap",
		
// 		 "oLanguage": { "sLengthMenu": "_MENU_ records", "oPaginate": {
// 		 "sPrevious": "Prev", "sNext": "Next" } }, "aoColumnDefs": [ {
// 		 'bSortable': false, 'aTargets': [0] }, { "bSearchable": false,
// 		 "aTargets": [ 0 ] } ]
		

// 		});
//      jQuery('#itsmapping_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
//      jQuery('#itsmapping_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
//   	}
	
	 function getScheduledTripStatusDataOnSubmit()
		{
			console.log('indside Function');
			var depotNo=$("#depotlist").val();
			$('#itsmapping').dataTable({
            	"aaSorting": [
            	                [1, 'asc']
            	            ],
                "aLengthMenu": [
                    [10,25,50,100],
                    [10,25,50,100], // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 10,
                "bProcessing" : true,
                "bServerSide" : true,
                "sAjaxSource" : "ScheduleMappingAjax.action",
                "sPaginationType": "bootstrap",
                "oLanguage": {
                    "sLengthMenu": "_MENU_ records",
                    "oPaginate": {
                        "sPrevious": "Prev",
                        "sNext": "Next"
                    }
                },
               "aoColumnDefs": [
                    { 'bSortable': false, 'aTargets': [0] },
                    { "bSearchable": false, "aTargets": [ 0 ] },
                   /*  { "sClass": "url", "aTargets": [ 3] }, */
                ]
                
            });
		    jQuery('#itsmapping_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#itsmapping_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
		 
		}
 

 </Script>

<title>Insert title here</title>
</head>
<body>
	<div class="page-content-wrapper">
		<div class="page-content">
	

			<div class="tab-content">

				<div class="row">
					<div class="col-md-12">
						<!-- BEGIN PAGE TITLE & BREADCRUMB-->
						<h3 class="page-title">
							Schedule Mapping<small></small>
						</h3>

						<!-- END PAGE TITLE & BREADCRUMB-->
					<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">

							<i class="fa fa-globe"></i>Schedule Mapping
						</div>
						<div class="actions">
						<div class="btn-group">
							
							 &nbsp;&nbsp;<a href="#" class="btn blue" onclick="callEdit()"> <i
								class="fa fa-pencil"></i> Edit
							</a> &nbsp;&nbsp; 
							<a href="#" class="btn red" onclick="callCancel()"> <i
								class="fa fa-times"></i> Delete
							</a> &nbsp;&nbsp;
							
						</div>
						
							</div>		
					</div>
					</div>
					</div>
							
							<!-- BEGIN FORM-->
							<form action="" method="post" class="form-horizontal">

								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Division<font
											color="red">*</font></label>
										<div class="col-md-4">
											<s:select list="divisionlist" id="divisionlist" name="orgchart.org_chart_id"
												cssClass="form-control select2me" headerKey="0"
												headerValue="select" onchange="getDepot(this.value)"></s:select>
										</div>
									</div>
								

								<div class="form-group">
									<label class="col-md-3 control-label">Depot<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select id="depotlist" name =depotlist1 class="select2_category form-control">
											<option value="0">select</option>
										</select>
									</div>
								</div>
							
								
								<div class="form-group">
								<label class="control-label col-md-3"></label>
									<div class="col-md-3" id="">
									<button type="button" class="btn default" onclick="getScheduledTripStatusDataOnSubmit()" >Submit</button>
									</div>
									</div>
								</div>		
							</div><div id="earlyarrivalsummary"></div> 	
					</div>
					
 			<div class="portlet-body"> 
			
 							<b>
 							<font color="green"> <s:actionmessage/></font></b> 
							<div id="tripstatus">
 							<table class="table table-striped table-bordered table-hover"
										id="itsmapping" >
 							 <thead> 
 										<tr>
										<th style="width1: 8px;"></th> 
										<th>schedule no</th> 
 										<th>schedule type</th> 
 										<th>vehicle no</th> 
									</tr>
										</thead>
									</table>
							</div>
 						</div>

						</div>
					</div>
			
</body>
</html>
