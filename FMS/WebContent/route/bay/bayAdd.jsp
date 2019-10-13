<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<!DOCTYPE html>
<html>
<head>


<script>
jQuery(document).ready(function(){

	 SelectElement('<s:property value="bay.status"/>');

});

function SelectElement(valueToSelect)
{ 
    var element = document.getElementById('status');
    element.value = valueToSelect;
}

function loadfloor(val){

    $.ajax({
        type: "post",
        url: '<%=request.getContextPath()%>/BayAction!getFloorName?orgid='+ val,
			success : function(result) {
				document.getElementById('floor_id').innerHTML = result;
			}
		});
}

function loadfloorOnpgLoad(){
	
	
	

	var e = document.getElementById("parent_id");
	var val = e.options[e.selectedIndex].value;

	var len= document.getElementById('floor_id').options.length;

	 if(len<=1 ) {
	
    $.ajax({
        type: "post",
        url: '<%=request.getContextPath()%>/BayAction!getFloorName?orgid='+ val,
			success : function(result) {
				document.getElementById('floor_id').innerHTML = result;
			}
		});
	 }
}

</script>
<title>Insert title here</title>	
</head>
<body>
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "BayAction!view");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
%>
	<div class="page-content-wrapper">
		<div class="page-content">
<%if (access.equalsIgnoreCase("Y")){%>
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Create Bay
							</div>
							<div class="tools">
								
							</div>
						</div>
						<%if (create.equalsIgnoreCase("Y")){%>
						<div class="portlet-body form">
							<!-- BEGIN FORM-->
							<form action="#" class="form-horizontal" method="post">
							<font color="red"><s:actionerror/></font>
							<br><br>
							<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label" 
										>Bus Station Name:<font color="red">*</font></label>

										<div class="col-md-4">
										
		<s:select list="orgNameMap" id="parent_id" name="bay.floor.orgChart.org_chart_id"  
		cssClass="select2_category form-control" headerKey="0" headerValue="Select" onchange="loadfloor(this.value);"></s:select>
																		
										<%--  <select class="form-control" name="fareChartMaster.route_id" 
											 id="route_id" onclick="getRouteNo()">
											
											</select> --%>
											<s:if test="fieldErrors.get('floor.orgChart.org_chart_id').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('floor.orgChart.org_chart_id').get(0)" /></span>
											</s:if>	 
										

										</div>
									</div>
								</div>
	
	 							<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label" 
										>Floor Name:<font color="red">*</font></label>

										<div class="col-md-4">
										
		<%-- <s:select list="floorNameMap" id="floor_id" name="bay.floor.floor_id" 
		cssClass="select2_category form-control" headerKey="0" headerValue="Select"></s:select> --%>
		
		<select class="form-control" id="floor_id" name="bay.floor.floor_id" onclick="loadfloorOnpgLoad()" onchange="getBayData(this.value)">
		 <option value="<s:property value='bay.floor.floor_id' />"><s:property value="bay.floor.floor_name"/></option>
		</select>
																		
											<s:if test="fieldErrors.get('floor.floor_id').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('floor.floor_id').get(0)" /></span>
											</s:if>	 
										</div>
									</div>
								</div>
								
								<div class="portlet box blue" id="baydiv" style="visibility: hidden">
									<div class="portlet-title">
										<div class="caption">
											<i class="fa fa-edit"></i>Bay Details
										</div>

									</div>
									<div class="portlet-body">
										<div class="table-toolbar">
											<div class="btn-group">
												<button id="add_new_bay" class="btn green">
													Add New Bay <i class="fa fa-plus"></i>
												</button>
											</div>
											
										</div>
										<div  id="successmsgbay"
											style="display: none">
											<p id="successbay" style="color: green"></p>
											<span> </span>
										</div>
										<div class="alert alert-danger" id="errormsgbay"
											style="display: none">
											<p class="intro" id="errorbay"></p>

											<span> </span>
										</div>
										<div id="floorMoreDetails" style="visibility: hidden" >
										<div  id="busname">
										
													
											</div>	</div>	
											
										<div id="floorDetails" style="visibility: hidden"> </div>
										
										<table class="table table-striped table-hover table-bordered"
											id="bayDatatable">
											<thead>
												<tr>
												
													<th>Bay Id</th>
													<th>Bay Name</th>
													<th>Status</th>
													<th>Edit</th>
													<th>Delete</th>			
												</tr>
												
											</thead>
											
										</table>
										<button id="bay_save_tab" class="btn green">
													Back
												</button>
										
									</div>
								</div>
								
						
								<s:token />
							</form>
							<!-- END FORM-->
							<%}else{ %>
<%@ include file="/pages/admin/error.jsp" %>
<%} %>									
										
<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

<script type="text/javascript">
var oTable;
function getBayData(busstation_idd) {
	
	$("#successmsgbay").hide();
	$("#errormsgbay").hide();

	var div = document.getElementById('baydiv');
	div.style.visibility = 'visible';
	
	document.getElementById('floorMoreDetails').style.visibility = 'visible';
	parentID = $('#parent_id').find(":selected").text();
	floorId = $('#floor_id').find(":selected").text();
	//var xx=document.getElementById('parent_id');
	$("#busname").css("display","block");
	//document.getElementById('busname').style=BOLD;
	document.getElementById('busname').innerHTML="<B>"+parentID+"-->"+floorId+"</B>";
	
	//$("#busname").(":display").val("block");
	var oTable = $('#bayDatatable').dataTable(
			{
				"aLengthMenu" : [ [ 5, 15, 20, -1 ],
						[ 5, 15, 20, "All" ] // change
				// per
				// page
				// values
				// here
				],
				// set the initial value
				"iDisplayLength" : 5,
				"sAjaxSource" : "bayAjaxAction!getBayData?busid="
						+ busstation_idd,
				"sPaginationType" : "bootstrap",
				"bDestroy" :true,
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
				} ]
			});
	jQuery('#bayDatatable_wrapper .dataTables_filter input')
			.addClass("form-control input-medium input-inline"); 
	jQuery('#bayDatatable_wrapper .dataTables_length select')
			.addClass("form-control input-small"); // modify table per
	// page dropdown
	jQuery('#bayDatatable_wrapper .dataTables_length select')
			.select2({
				showSearchInput : false
			// hide search box with special css class
			}); // initialize select2 dropdown
	
	var update_id = "";
	var new_id = 0;
	var edit_id=0;
	var insert_id = "";
	var deletedid = "";
	var newrow;
	function restoreRow(oTable, nRow) {
		var aData = oTable.fnGetData(nRow);
		var jqTds = $('>td', nRow);

		for ( var i = 0, iLen = jqTds.length; i < iLen; i++) {
			// alert(aData[i]);
			oTable.fnUpdate(aData[i], nRow, i, false);
		}

		oTable.fnDraw();
	}
	

	function editRow(oTable, nRow, rec_id, newdata) {
		
		var aData = oTable.fnGetData(nRow);
		var jqTds = $('>td', nRow);
		var status = aData[2];
		//alert(status);
		if (status != undefined) {
			
			if (status == "ACTIVE" || status == "Active") {
				
				jqTds[2].innerHTML = '	<select id="floor_status_'
						+ rec_id
						+ '" class="form-control"><option  id="active" value="ACTIVE">ACTIVE</option><option id="inactive" value="INACTIVE">INACTIVE</option></select>'
			} else {
				jqTds[2].innerHTML = '<select id="floor_status_'
						+ rec_id
						+ '" class="form-control"><option id="inactive" value="INACTIVE">INACTIVE</option><option  id="active" value="ACTIVE">ACTIVE</option></select>'
			}
		}
		
		jqTds[0].innerHTML = '<input type="test" id="new_floor_id_'
				+ newdata + '" value="' + aData[0] + '" readonly="readonly" class="form-control input-small" >';
		jqTds[1].innerHTML = '<input type="text" class="form-control input-small" id="floor_txt_'
				+ rec_id + '" value="' + aData[1] + '">';
				
		jqTds[3].innerHTML = '<a class="saveEdit" href="" value="' + rec_id +","+ newdata +'" >Save</a>';
		
		jqTds[4].innerHTML = '<a class="cancel" href="">Cancel</a>';
		
		if (status != '') {
			update_id += rec_id + ",";
		} else {
			insert_id += newdata + ",";
		}
		
	}
	function savRow(oTable, nRow, rec_id, newdata) {
		var aData = oTable.fnGetData(nRow);
		var jqTds = $('>td', nRow);
		jqTds[2].innerHTML = '<select id="floor_status_'
						+ rec_id
						+ '" class="form-control"><option id="active" value="ACTIVE">ACTIVE</option></select>';
		jqTds[0].innerHTML = '<input type="text" readonly="readonly" id="new_floor_id_'
				+ newdata + '" value="' + aData[1]
				+ '" class="form-control input-small" >';
		jqTds[1].innerHTML = '<input type="text" class="form-control input-small" id="floor_txt_'
				+ rec_id + '" value="' + aData[1] + '">';
		jqTds[3].innerHTML = '<a class="savenew" href="" value="'+ rec_id +'">Save</a>';
		jqTds[4].innerHTML = '<a class="deleterow" href="">Cancel</a>';
		//alert(rec_id);
		 
			insert_id += newdata + ",";
		
	}
	var busstation_id = $('#floor_id').val();
	$('#bayDatatable a.saveEdit')
	
	.unbind().die().live(
			'click',
			function(e) {
				e.preventDefault();
				var nRow = $(this).parents('tr')[0];
				var datas = $(this).attr('value').split(",");
				var rec_id=datas[0];
				var id=datas[1];
				var idArr = update_id.split(',');
				var busstation_id = $('#floor_id').val();
				var parent_id = $('#parent_id').val();
				var floor_name = $(
						'#floor_txt_' + idArr[rec_id]).val();
				var floor_status = $(
						'#floor_status_' + idArr[rec_id])
						.val();
				//alert(floor_name+"\t"+floor_status+"\t"+id);
		if (floor_name == undefined) {
					} else {
						 var iChars = '!@#$%^&*()+=-[]\\\';,./{}|\":<>?`';
						if(floor_name.length>0){
							for (var i = 0; i < floor_name.length; i++) {
			                    if (iChars.indexOf(floor_name.charAt(i)) != -1) {
			                    
			                        alert ("special characters for Bay Name is not allowed");
			                        $('#floor_txt_' + idArr[rec_id]).focus();
			                        return false;
			                    }
			                }	
						
						}else{
							 alert ("Please Enter Bay Name");
							 return false;
						}
						$.ajax({
							type: 'POST',
							url : "bayAjaxAction!updaterows?id="
								+ id
								+ "&floorname="
								+ floor_name
								+ "&floorstatus="
								+ floor_status
								+ "&parentID="
								+ busstation_id,
							
							success: function(data)
							{
								//alert(data);
								if(data!=""){
									if(data.indexOf("SuccessFully")!= -1)
									{
										$('#errormsgbay').hide();
										var divId = document.getElementById("successmsgbay");
									divId.style.display = 'block';
									divId.style.visibility = 'visible';
									document.getElementById('successbay').innerHTML = data;
									}
										else{
											$("#successmsgbay").hide();												
											var divId = document.getElementById("errormsgbay");
											divId.style.display = 'block';
											divId.style.visibility = 'visible';
											w=data.replace(/@/g,"<br>");
											divId.innerHTML = w;
										}
				        			var oTable = $('#bayDatatable').dataTable(
				    						{
				    							"aLengthMenu" : [ [ 5, 15, 20, -1 ],
				    									[ 5, 15, 20, "All" ] // change
				    							// per
				    							// page
				    							// values
				    							// here
				    							],
				    							// set the initial value
				    							"iDisplayLength" : 5,
				    							"sAjaxSource" : "bayAjaxAction!getBayData?busid="+busstation_id,
				    							"sPaginationType" : "bootstrap",
				    							"bDestroy" :true,
				    							
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
				    							} ]
				    						});
								}else{
									
								}
							},
							error: function(e)
							{
							   alert(e.message);
							}
						});
							
					}
			});
	
$('#bayDatatable a.savenew')
	
	.unbind().die().live(
			'click',
			function(e) {
				e.preventDefault();
				var nRow = $(this).parents('tr')[0];
				var insertid = insert_id.split(',');
				var rec_id = $(this).attr('value');
				var busstation_id = $('#parent_id').val();
				//alert($('#floor_txt_' + insertid[rec_id]).val());
				//alert($('#floor_status_' + insertid[rec_id]).val());
				var parent_id = $('#parent_id').val();
				var floor_id = $('#floor_id').val();
				var bay_id = $('#bay_id').val();
				if ($('#floor_txt_' + insertid[rec_id])
						.val() != undefined) {
					var floor_name = $('#floor_txt_' + insertid[rec_id]).val();
					var floor_status = $('#floor_status_' + insertid[rec_id]).val();
					 var iChars = '!@#$%^&*()+=-[]\\\';,./{}|\":<>?`';
						if(floor_name.length>0){
							for (var i = 0; i < floor_name.length; i++) {
			                    if (iChars.indexOf(floor_name.charAt(i)) != -1) {
			                    
			                        alert ("special characters for Bay Name is not allowed");
			                        $('#floor_txt_' + idArr[rec_id]).focus();
			                        return false;
			                    }
			                }	
						
						}else{
							 alert ("Please Enter Bay Name");
							 return false;
						}
					$
					.ajax({
						type : 'POST',
						url : "bayAjaxAction!insertrows?id="
							+ busstation_id
							+ "&parent_id="
							+ floor_id
							+ "&floorname="
							+ floor_name
							+ "&floorstatus="
							+ floor_status
							+ "&parentID="
							+ busstation_id,
						success : function(data) {
							//alert(data);
							if (data != "") {if(data.indexOf("SuccessFully")!= -1)
							{
								var divId = document.getElementById("successmsgbay");
							divId.style.display = 'block';
							divId.style.visibility = 'visible';
							document.getElementById('successbay').innerHTML = data;
							}
								else{
						var divId=document.getElementById("errormsgbay");
		        		   divId.style.display='block';
		        			divId.style.visibility='visible';
		        			document.getElementById('errorbay').innerHTML=data;
								}
								var oTable = $(
										'#bayDatatable')
										.dataTable(
												{
													"aLengthMenu" : [
															[
																	5,
																	15,
																	20,
																	-1 ],
															[
																	5,
																	15,
																	20,
																	"All" ] // change
													// per
													// page
													// values
													// here
													],
													// set the initial value
													"iDisplayLength" : 5,
													"sAjaxSource" : "bayAjaxAction!getBayData?busid="
															+ floor_id,
													"sPaginationType" : "bootstrap",
													"bDestroy" : true,

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
													} ]
												});
								jQuery(
								'#bayDatatable_wrapper .dataTables_filter input')
								.addClass(
										"form-control input-medium input-inline"); // modify
						// table
						// search
						// input
						jQuery(
								'#bayDatatable_wrapper .dataTables_length select')
								.addClass("form-control input-small"); // modify table per
						// page dropdown
						jQuery(
								'#bayDatatable_wrapper .dataTables_length select')
								.select2({
									showSearchInput : true
								// hide search box with special css class
								}); // initialize select2 dropdown

							} else {

							}
						},
						error : function(e) {
							alert(e.message);
						}
					});
					
				}
				
			});
	function saveRow(oTable, nRow) {
		var jqInputs = $('input', nRow);
		oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
		oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
		oTable.fnUpdate(jqInputs[2].value, nRow, 2, false);
		oTable.fnUpdate('<a class="edit" href="">Edit</a>', nRow, 3,
				false);
		oTable.fnUpdate('<a class="delete" href="">Delete</a>', nRow,
				4, false);
		oTable.fnDraw();
	}

	function cancelEditRow(oTable, nRow) {
		var jqInputs = $('input', nRow);
		oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
		oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
		oTable.fnUpdate(jqInputs[2].value, nRow, 2, false);
		oTable.fnUpdate('<a class="edit" href="">Edit</a>', nRow, 3,
				false);
		oTable.fnDraw();
	}
	var busstation_id = $('#floor_id').val();
	
	oTable = $('#bayDatatable').dataTable(
			{
				"aLengthMenu" : [ [ 5, 15, 20, -1 ],
						[ 5, 15, 20, "All" ] // change
				// per
				// page
				// values
				// here
				],
				// set the initial value
				"iDisplayLength" : 5,
				"sAjaxSource" : "bayAjaxAction!getBayData?busid="
						+ busstation_idd,
				"sPaginationType" : "bootstrap",
				"bDestroy" :true,
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
				} ]
			});

	jQuery('#bayDatatable_wrapper .dataTables_filter input')
			.addClass("form-control input-medium input-inline"); // modify
	// table
	// search
	// input
	jQuery('#bayDatatable_wrapper .dataTables_length select')
			.addClass("form-control input-small"); // modify table per
	// page dropdown
	jQuery('#bayDatatable_wrapper .dataTables_length select')
			.select2({
				showSearchInput : true
			// hide search box with special css class
			}); // initialize select2 dropdown

	var nEditing = null;

	$('#add_new_bay').unbind().click(function(e) {
		e.preventDefault();

		var aiNew = oTable.fnAddData([ '', '', '', '', '' ]);
		var nRow = oTable.fnGetNodes(aiNew[0]);

		savRow(oTable, nRow, new_id, new_id);
		nEditing = nRow;
		newrow = aiNew;
		new_id += 1;
	});

	$('#bayDatatable a.delete')
			.unbind()
			.die()
			.live(
					'click',
					function(e) {
						e.preventDefault();

						if (confirm("Are you sure to delete this row ?") == false) {
							return;
						}
						var delete_id = $(this).attr('value');
				
						var nRow = $(this).parents('tr')[0];
						
						oTable.fnDeleteRow(nRow);
						$
						.ajax({
							type : 'POST',
							url : "bayAjaxAction!deleterows?id=" + delete_id,
							success : function(data) {
								//alert(data);
								if (data != "") {
									$("#successmsgbay").hide();
									var divId = document.getElementById("errormsgbay");
									divId.style.display = 'block';
									divId.style.visibility = 'visible';
									 w=data.replace(/@/g,"<br>");

									  // $('#errorfloor').html(''+w+'');
									    document.getElementById('errorbay').innerHTML = w;
									//document.getElementById('errorbay').innerHTML = data;
									
									var busstation_id = $('#floor_id').val();
									    var oTable = $(
										'#bayDatatable')
										.dataTable(
												{
													"aLengthMenu" : [
															[
																	5,
																	15,
																	20,
																	-1 ],
															[
																	5,
																	15,
																	20,
																	"All" ] // change
													// per
													// page
													// values
													// here
													],
													// set
													// the
													// initial
													// value
													"iDisplayLength" : 5,
													"sAjaxSource" : "bayAjaxAction!getBayData?busid="
														+ busstation_idd,
													"sPaginationType" : "bootstrap",
													"bDestroy" : true,

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
													} ]
												});
								jQuery(
										'#floorView_wrapper .dataTables_filter input')
										.addClass(
												"form-control input-medium input-inline"); // modify
								// table
								// search
								// input
								jQuery(
										'#floorView_wrapper .dataTables_length select')
										.addClass(
												"form-control input-small"); // modify
																				// table
																				// per
								// page dropdown
								jQuery(
										'#floorView_wrapper .dataTables_length select')
										.select2(
												{
													showSearchInput : true
												// hide
												// search
												// box
												// with
												// special
												// css
												// class
												}); // initialize
													// select2
													// dropdown
								
									
								} else {

								}
							},
							error : function(e) {
								alert(e.message);
							}
						});
						/* $
						.ajax(
								{
									url : "bayAjaxAction!deleterows?id="
											+ delete_id,
									context : document.body
								}).done(function() {
							$(this).addClass("done");

						}); */
					deletedid += delete_id + ",";
						// alert(deletedid);
						restoreRow(oTable, nEditing);

					});

	$('#bayDatatable a.cancel').unbind().die().live('click', function(e) {
		e.preventDefault();
		var $this = $(this);
		var mode = $this.data('mode');
		// alert(mode);
		if (mode == "new") {
			var nRow = $(this).parents('tr')[0];
			oTable.fnDeleteRow(nRow);

		} else {
			var nRow1 = $(this).parents('tr')[0];
			restoreRow(oTable, nRow1);
			// nEditing = null;
		}
		// nEditing = null;

	});
	$('#bayDatatable a.deleterow').live('click', function(e) {

		var nRow = $(this).parents('tr')[0];
		oTable.fnDeleteRow(nRow);

		// nEditing = null;

	});

	$('#bayDatatable a.edit')
			.unbind()
			.die()
			.live(
					'click',
					function(e) {
						e.preventDefault();

						/*
						 * Get the row as a parent of the link that was
						 * clicked on
						 */
						var nRow = $(this).parents('tr')[0];
						// alert($("#editval").val());
						// alert($(this).attr( 'value' ));
						var rec_id = $(this).attr('value');
						if (nEditing !== null && nEditing != nRow) {

							/*
							 * Currently editing - but not this row -
							 * restore the old before continuing to edit
							 * mode
							 */
							// restoreRow(oTable, nEditing);
							editRow(oTable, nRow, edit_id, rec_id);
							nEditing = nRow;
						} else if (nEditing == nRow
								&& this.innerHTML == "Save") {
							/* Editing this row and want to save it */
							saveRow(oTable, nEditing);
							nEditing = null;
							alert("Updated! Do not forget to do some ajax to sync with backend :)");
						} else {
							/* No edit in progress - let's start one */
							editRow(oTable, nRow, edit_id,rec_id);
							nEditing = nRow;
						}
						edit_id+=1;
					});

	

$('#bay_save_tab').unbind().click(
	function(e) {
		e.preventDefault();
		
		document.forms[0].action="BayAction!view";
		document.forms[0].submit();
	});
}

$(document).ready(function() {	
//	alert("hel;");
	//var bsusiid=$('#parent_id').val();
	//alert("parent_id"+bsusiid);
	//getBayData(this.value);
	//window.history.pushState("", "", "FloorAjaxAction!getFloorData?busid="+busstation_id+"");
	});
</script>


</body>


</html>