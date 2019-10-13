<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="com.trimax.its.utility.model.AccessRights"%>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao"%>
<head>
<!--<script type="text/javascript" language="javascript" src="../js/dataTables.tableTools.js"></script>-->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js" type="text/javascript"></script>
<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.min.js"	type="text/javascript"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
<script>
	$(document).ready(function() {
// 		//alert("hello1");
 		VouncherShow();
 	});
	//vouncher show start 
	function VouncherShow() {
		//alert("hello");
		var dd1=$("#startdate").val();
		var dd2=$("#endate").val();
		//alert(dd1);
		//alert(dd2);
		//alert("hello");
		$('#ticketVouncherid')
				.dataTable(
						{
							"aaSorting" : [ [ 1, 'desc' ] ],
							"aLengthMenu" : [ [ 10, 50, 100 ], [ 10, 50, 100 ] // change per page values here
							],
							// set the initial value
							"iDisplayLength" : 10,
							"bProcessing" : true,
							"bServerSide" : true,
							"sAjaxSource" : "viewticketVouncherRecords.action?startdate="+dd1+"&enddate="+dd2,
							"sPaginationType" : "bootstrap",
							"bDestroy" : true,
							/* "sDom" : 'T<"clear">lfrtip',selectedDate="+selectedDate+"&depotNo="+depotNo,
							"oTableTools" : {
								"aButtons" : [ {
									"sExtends" : "print",
									"bShowAll" : false,
									"sInfo" : "Please press escape when done"
								} ]
							}, */
							/* "fnRowCallback" : function(nRow, aData,
									iDisplayIndex) {
								$('td:eq(2)', nRow)
										.html(
												'<a data-toggle="modal" id="addRow" role="button" href="#mymodal1" onclick="DisplayVouncherDetail(\''
														+ aData[1]
														+ '\',\''
														+ aData[2]
														+ '\',\''
														+ aData[3]
														+ '\',\''
														+ aData[4]
														+ '\',\''
														+ aData[5]
														+ '\',\''
														+ aData[6]
														+ '\');"><u>'
														+ aData[2] + '</u></a>');
								return nRow;
							}, */
							"oLanguage" : {
								"sLengthMenu" : "_MENU_ records",
								"oPaginate" : {
									"sPrevious" : "Prev",
									"sNext" : "Next"
								}
							},
							"aoColumnDefs" : [ {
								'bSortable' : false,
								'aTargets' : [ 0 ]
							}, {
								"bSearchable" : false,
								"aTargets" : [ 0 ]
							} ,  { "sClass": "url1", "aTargets": [ 0 ] },{ "sClass": "url2", "aTargets": [ 1 ] },
							{ "sClass": "url3", "aTargets": [ 2 ] },{ "sClass": "url4", "aTargets": [ 3 ] },
							{ "sClass": "url5", "aTargets": [ 4 ] },{ "sClass": "url6", "aTargets": [ 5 ] },{ "sClass": "url7", "aTargets": [ 6 ] } ]
						});
		jQuery('#ticketVouncherid_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
        jQuery('#ticketVouncherid_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline");
	}
	
	
	
	function DisplayVouncherDetail(aData1, aData2, aData3, aData4, aData5,
			aData6) {
		$('#vehicleDetailsDev').dataTable(
				{
					"bDestroy" : true,
					"aaSorting" : [ [ 1, 'asc' ] ],
					"aLengthMenu" : [ [ 5, 10, 15 ], [ 5, 10, 15 ] ], // change per page values here,
					//'iDisplayLength': 10,
					"sPaginationType" : "bootstrap",
					"sDom" : 'T<"clear">lfrtip',
					//"sDom": '<"search-box"r><"H"lf>t<"F"ip>',
					"aaData" : [ [ "", aData1, aData2, aData3, aData4, aData5,
							aData6 ] ],
							
					

					"aoColumns" : [ /* {
						"sTitle" : "",
						"sClass" : "center"
					}, {
						"sTitle" : "Vouncher_Id",
						"sClass" : "center"
					}, {
						"sTitle" : "Vouncher_No",
						"sClass" : "center"
					}, {
						"sTitle" : "Vouncher_Date",
						"sClass" : "center"
					}, {
						"sTitle" : "From Organization",
						"sClass" : "center"
					}, {
						"sTitle" : "To_Organization",
						"sClass" : "center"
					}, {
						"sTitle" : "Status",
						"sClass" : "center"
					}, */  ]
				});
	}
	//vouncher end
</script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
<style type="text/css">
.url1{
	width:10px;
}
.url2{
	width:40px;
}
.url3{
	width:100px;
}
.url4{
	width:150px;
}
.url7{
	width:80px;
}
</style>
</head>

<form>
<input type="hidden" name="id" id="id" />
	<div class="page-content-wrapper">
		<div class="page-content">
			<div class="row">
				<div class="col-md-12">
					<h3 class="page-title">TICKET INVENTORY</h3>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>Voucher List
							</div>
							<div class="actions">
								<a href="#" class="btn blue" onclick="callEdit()"> <i></i>
									View
								</a> <a href="#" class="btn blue" onclick="callBack()"> <i></i>
									Back
								</a>
							</div>
						</div>
						<div class="portlet-body">
							<div class="form-group">
								<label class="col-md-1 control-label">Start Date:</label>
									<div class="col-md-3">
										<div class="input-group input-medium date date-picker"
														style="width: auto" data-date-format="dd-mm-yyyy"
														data-date-viewmode="years" ><!-- data-date-start-date="+0d"> -->
											<input id="startdate" class="form-control" type="text" readonly="" size="16" name="startdate" onblur="VouncherShow()"
												value="<s:property value='rateMaster.effectiveStartDate' />">
											<span class="input-group-btn">
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
											<script>
												var curr_date=new Date().toJSON().slice(0,10);
		                                       	curr_date=curr_date.split("-");
		                                       	curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
												var dtval=document.getElementById('startdate').value;	
		                                       	if(dtval==''){
			                                    	$('#startdate').val(curr_date);
			                                    }
											</script>
										</div>
									</div>
									<label class="control-label col-md-1">Till Date:</label>
									<div class="col-md-3">
										<div class="input-group input-medium date date-picker" syle="width: auto" data-date-format="dd-mm-yyyy" data-date-viewmode="years" >
											<input id="endate" class="form-control" type="text" readonly="" size="16" name="endate"
												value="<s:property value='rateMaster.effectiveStartDate' />">
											<span class="input-group-btn">
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
											<script>
												var curr_date=new Date().toJSON().slice(0,10);
		                                        curr_date=curr_date.split("-");
		                                        curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
		                                        var dtval=document.getElementById('endate').value;	
		                                        if(dtval==''){
		                                        	$('#endate').val(curr_date);
		                                        }
											</script>
										</div>
									<%-- <label class="col-md-3 control-label">Organization Unit Name </label>
									<div class="col-md-3">
										<select class="select2_category form-control" name="vehicle.depotOrUnit.org_chart_id" id="depot">
												<option value="0">Select</option>
										</select>   --%>
									</div>
									<button type="button" class="btn grey" onClick="VouncherShow()">Submit</button>
								</div>
								<%-- <table>
									<tr>
										<td>
											<label class="control-label col-md-4">Start Date:</label>
											<div class="col-md-4">
												<div class="input-group input-medium date date-picker"
																style="width: auto" data-date-format="dd/mm/yyyy"
																data-date-viewmode="years" ><!-- data-date-start-date="+0d"> -->
													<input id="startdate" class="form-control" type="text" readonly="" size="16" name="startdate" onblur="VouncherShow()"
														value="<s:property value='rateMaster.effectiveStartDate' />">
													<span class="input-group-btn">
														<button class="btn default date-set" type="button">
															<i class="fa fa-calendar"></i>
														</button>
													</span>
													<script>
														var curr_date=new Date().toJSON().slice(0,10);
				                                       	curr_date=curr_date.split("-");
				                                       	curr_date=curr_date[2]+"/"+curr_date[1]+"/"+curr_date[0];
														var dtval=document.getElementById('startdate').value;	
				                                       	if(dtval==''){
					                                    	$('#startdate').val(curr_date);
					                                    }
													</script>
												</div>
											</div>
										</td>   
								  		<td>
								  			<label class="control-label col-md-3">Till Date:</label>
											<div class="col-md-4">
												<div class="input-group input-medium date date-picker" syle="width: auto" data-date-format="dd/mm/yyyy" data-date-viewmode="years" >
													<input id="endate" class="form-control" type="text" readonly="" size="16" name="endate"
														value="<s:property value='rateMaster.effectiveStartDate' />">
													<span class="input-group-btn">
														<button class="btn default date-set" type="button">
															<i class="fa fa-calendar"></i>
														</button>
													</span>
													<script>
														var curr_date=new Date().toJSON().slice(0,10);
				                                        curr_date=curr_date.split("-");
				                                        curr_date=curr_date[2]+"/"+curr_date[1]+"/"+curr_date[0];
				                                        var dtval=document.getElementById('endate').value;	
				                                        if(dtval==''){
				                                        	$('#endate').val(curr_date);
				                                        }
													</script>
												</div>
											</div>
									</td> 
									<td>
										
									</td>  
								</tr>
							</table> --%>
					<!-- </div>
					<div class="portlet-body"> -->
					<br/>
					<br/>
						<input type="hidden" id="" value="" name="vehicleTypeId" /> 
							<b>
								<font color="green"> <s:actionmessage /></font> 
								<font color="red"> <s:actionerror /></font>
							</b>
						<table class="table table-striped table-bordered table-hover" id="ticketVouncherid">
							<thead>
								<tr>
									<th style="width: 8px;"></th>
									<th> Id</th>
									<th>Voucher No.</th>
									<th>Voucher Date</th>
									<th>From Organization</th>
									<th>To Organization</th>
									<th>Status</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	function callEdit() {
		var val;
		//$(function() {
		var val = [];
		$(':checkbox:checked').each(function(i) {
			val = $(this).val();
		});
		if (check(0)) {
			document.getElementById("voincheridd").value = val;
			var check1 = document.getElementById("voincheridd").value;
			//alert(check1);
			document.getElementById("form1").submit();
		}
		//});
	}
	function callBack() {
		document.forms[0].action = "ticketinv.action";
		document.forms[0].submit();
	}
	function callCreate() {
		document.forms[0].action = "createcomplaint.action";
		document.forms[0].submit();
	}
	function callDelete() {
		var val;
		//$(function() {
		var val = [];
		$(':checkbox:checked').each(function(i) {
			val = $(this).val();
		});
		if (check(0)) {
			bootbox
					.confirm(
							"Are you sure you want to delete Complaint?",
							function(result) {
								if (result == true) {
									document.getElementById("complaintid").value = val;
									document.getElementById("form2")
											.submit();
								}
							});
		}
		//});
	}
	function check() {

		var chckbxCount = $("input:checked").length;
		if (chckbxCount > 0 && chckbxCount > 1) {
			bootbox.alert("Select only one voucher...!")
			return false;
		} else if (chckbxCount > 0 && chckbxCount == 1) {
			return true;
		} else {
			bootbox.alert("Please select voucher");
			return false;
		}

	}
	$(document).ready(function() {
		window.history.pushState("","", "viewVouncherlist.action");
	});
</script>
</form>
<form name="form1" id="form1" action="viewVouncherlistmanual.action" method="POST">
	<input type="hidden" name="voincheridd" id="voincheridd" value="" />
</form>
<form name="form2" id="form2" action="deleteComplaint.action" method="POST">
	<input type="hidden" name="complaintid" id="complaintid" />
</form>
