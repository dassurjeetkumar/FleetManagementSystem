<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">

	function getBusStation()
	{	
		
		 document.getElementById('select2-chosen-1').innerHTML='Select';
		 document.getElementById('orgNameMap').value=0;
	 //var len= document.getElementById('org_type_id').options.length;	 	 
	      <%--  $.ajax({
	           type: "get",
	           url: '<%=request.getContextPath()%>/findAllSchedule',
	           async:false,
	           
	           success: function(result) {
	        	   document.getElementById('scheduleidlist').innerHTML=result;
	        	  
	           }
	       });
	        --%>
	
	}
	
</script>

<script>
	/* jQuery(document).ready(function() {

		SelectElement('<s:property value="floor.status"/>');

	});

	function SelectElement(valueToSelect) {
		var element = document.getElementById('status');
		element.value = valueToSelect;
	} */
</script>
<title>Insert title here</title>
</head>
<body>
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "FloorAction!view");
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
								<i class="fa fa-gift"></i>Create Floor
							</div>

						</div>
<%if (create.equalsIgnoreCase("Y")){%>
						<div class="portlet-body form">
							<!-- BEGIN FORM-->
							<form action="#" class="form-horizontal" method="post">
								<font color="red"><s:actionerror /></font> <br> <br>
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Bus Station
											Name:<font color="red">*</font>
										</label>

										<div class="col-md-4">

											<s:select list="orgNameMap" id="parent_id"
												name="floor.orgChart.org_chart_id"
												cssClass="select2_category form-control" headerKey="0"
												headerValue="Select" onchange="getFloorDetails(this.value)"></s:select>

											<%--  <select class="form-control" name="fareChartMaster.route_id" 
											 id="route_id" onclick="getRouteNo()">
											
											</select> --%>
											<s:if test="fieldErrors.get('orgChart.parent_id').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('orgChart.parent_id').get(0)" /></span>
											</s:if>
										</div>
									</div>
								</div>

								<div class="portlet box blue" id="floordiv"
									style="visibility: hidden">
									<div class="portlet-title">
										<div class="caption">
											<i class="fa fa-edit"></i>Floor Details
										</div>

									</div>
									<div class="portlet-body">
										<div class="table-toolbar">
											<div class="btn-group">
												<button id="floorNew" class="btn green">
													Add New Floor <i class="fa fa-plus"></i>
												</button>
											</div>
										</div>
										
								<div class="alert alert-danger" id="successmsg"	style="display: none">
											<p id="successfloor" style="color: green"></p>
											<span> </span>
										</div>	
											
										<div class="alert alert-danger" id="errormsg" style="display: none">
											<p id="errorfloor" style="color: red"></p>
										<span >	
										</span>
										</div>
										
										<table class="table table-striped table-hover table-bordered"
											id="floorView">
											<thead>
												<tr>
													<th>Floor Id</th>
													<th>Floor Name</th>
													<th>Status</th>
													<th>Edit</th>
													<th>Delete</th>
												</tr>

											</thead>

										</table>
										<button id="backbtn" class="btn green">Back</button>
									</div>
								</div>


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
		$('#edit_').click(function() {
			var cnt = $(":checkbox:checked").length;
			var val;
			if (cnt == 0) {
				alert("Please select checkbox to edit");
			} else if (cnt > 1) {
				alert("Please select one checkbox to edit");
			} else {
				$("input[type='checkbox']:checked").each(function() {
					val = this.value;
				});

				document.forms[0].action = 'FloorAction!edit?id=' + val;
				document.forms[0].submit();
			}
		});

		$('#delete_').click(function() {
			var cnt = $(":checkbox:checked").length;
			var val;
			if (cnt == 0) {
				alert("Please select checkbox to delete");
			} else if (cnt > 1) {
				alert("Please select one checkbox to delete");
			} else {
				$("input[type='checkbox']:checked").each(function() {
					val = this.value;
					type = 'text';
				});
				if (confirm('Are you sure you want to delete this record?')) {
					document.forms[0].action = 'DeleteFloorAction?id=' + val;
					document.forms[0].submit();
				}

			}
		});
	</script>
	<script type="text/javascript">
		var oTable;
		function getFloorDetails(busstation_id) {
			$("#successmsg").hide();
			$("#errormsg").hide();
			var update_id = "";
			var new_id = 0;
			var edit_id = 0;
			var insert_id = "";
			var deletedid = "";
			var newrow;

			var div = document.getElementById('floordiv');
			div.style.visibility = 'visible';
			oTable = $('#floorView').dataTable(
					{
						"aLengthMenu" : [ [ 5, 15, 20, -1 ],
								[ 5, 15, 20, "All" ] 
						// change
						// per
						// page
						// values
						// here
						],
						// set the initial value
						"iDisplayLength" : 5,
						"sAjaxSource" : "FloorAjaxAction!getFloorData?busid="
								+ busstation_id,
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

			jQuery('#floorView_wrapper .dataTables_filter input').addClass(
					"form-control input-medium input-inline"); // modify
			// table
			// search
			// input
			jQuery('#floorView_wrapper .dataTables_length select').addClass(
					"form-control input-small"); // modify table per
			// page dropdown
			jQuery('#floorView_wrapper .dataTables_length select').select2({
				showSearchInput : true
			// hide search box with special css class
			}); // initialize select2 dropdown

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
				// alert(status);
				if (status != undefined) {
					if (status == "ACTIVE" || status == "Active") {
						jqTds[2].innerHTML = '	<select id="floor_status_'
						+ rec_id
						+ '" class="form-control"><option  id="active" value="ACTIVE">ACTIVE</option><option id="inactive" value="INACTIVE">INACTIVE</option></select>';
					} else {
						jqTds[2].innerHTML = '<select id="floor_status_'
						+ rec_id
						+ '" class="form-control"><option id="inactive" value="INACTIVE">INACTIVE</option><option  id="active" value="ACTIVE">ACTIVE</option></select>';
					}
				}
				jqTds[0].innerHTML = '<input type="test" id="new_floor_id_'
				+ newdata + '" value="' + aData[0] + '" readonly="readonly" class="form-control input-small" >';
				jqTds[1].innerHTML = '<input type="text" maxlength="50" class="form-control input-small" id="floor_txt_'
				+ rec_id + '" value="' + aData[1] + '">';
				jqTds[3].innerHTML = '<a class="saveEdit" value="' + rec_id +","+ newdata +'" href="" >Save</a>';
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
				jqTds[1].innerHTML = '<input type="text" maxlength="50" class="form-control input-small" id="floor_txt_'
				+ rec_id + '" value="' + aData[1] + '">';
				jqTds[3].innerHTML = '<a class="savenew" value="'+ rec_id +'" href="">Save</a>';
				jqTds[4].innerHTML = '<a class="deleterow" href="">Cancel</a>';
				//alert(rec_id);

				insert_id += newdata + ",";

			}
			var busstation_id = $('#parent_id').val();

			$('#floorView a.saveEdit').unbind().die().live(
							'click',
							function(e) {

								var nRow = $(this).parents('tr')[0];
								var datas = $(this).attr('value').split(",");
								var rec_id = datas[0];
								var id = datas[1];
								var idArr = update_id.split(',');
								var floor_name = $(
										'#floor_txt_' + idArr[rec_id]).val();
								var floor_status = $(
										'#floor_status_' + idArr[rec_id]).val();
								//alert(busstation_id);
								//alert(floor_name+"\t"+floor_status+"\t"+id);
								if (floor_name == undefined) {
								} else {
									/* if( /^[a-zA-Z0-9- ]*$/.test( floor_name ) ){alert("Special Characters Not Allowed For Floor Name");return false;}
									else{ */
								//Special Character Validation//
								 var iChars = '!@#$%^&*()+=-[]\\\';,./{}|\":<>?`';
								 if(floor_name.trim()=="") {
				                        alert("No Space allowed!");
				                        $('#floor_txt_' + idArr[rec_id]).focus();
				                        return false;
				                    }
								if(floor_name.length>0){
									for (var i = 0; i < floor_name.length; i++) {
					                    if (iChars.indexOf(floor_name.charAt(i)) != -1) {
					                        alert ("Special characters for Floor Name is not allowed");
					                        $('#floor_txt_' + idArr[rec_id]).focus();
					                        return false;
					                    }
					                    
					                }	
								}else{
									 alert ("Please Enter Floor Name");
									 return false;
								}
										
										
									$.ajax({
												type : 'POST',
												url : "FloorAjaxAction!updaterows?id="
														+ id
														+ "&floorname="
														+ floor_name
														+ "&floorstatus="
														+ floor_status
														+ "&parentID="
														+ busstation_id,
												success : function(data) {
													//alert(data);
													if (data != "") {
														
														//var patt = new RegExp("");
														if(data.indexOf("Successfully")!= -1){
															
														
															 $("#errormsg").hide();
														var divId = document.getElementById("successmsg");
														divId.style.display = 'block';
														divId.style.visibility = 'visible';
														document.getElementById('successfloor').innerHTML = data;
														}else{ 
															
														
															$("#successmsg").hide();												
														var divId = document.getElementById("errormsg");
														divId.style.display = 'block';
														divId.style.visibility = 'visible';
														w=data.replace(/@/g,"<br>");
														divId.innerHTML = w;
														
													}
														var oTable = $(
																'#floorView')
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
																			"sAjaxSource" : "FloorAjaxAction!getFloorData?busid="
																					+ busstation_id,
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
																		"form-control input-small"); // modify table per
														// page dropdown
														jQuery(
																'#floorView_wrapper .dataTables_length select')
																.select2(
																		{
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
								e.preventDefault();

							});

			$('#floorView a.savenew').unbind().die().live('click',
							function(e) {
								e.preventDefault();
								var nRow = $(this).parents('tr')[0];
								var insertid = insert_id.split(',');
								var rec_id = $(this).attr('value');
								var busstation_id = $('#parent_id').val();
								/* alert(insertid);
								alert("hkdgk"+busstation_id); */

								if ($('#floor_txt_' + insertid[rec_id]).val() != undefined) {
									var floor_name = $('#floor_txt_' + insertid[rec_id]).val();
									var floor_status = $('#floor_status_' + insertid[rec_id]).val();
									
			 						 var iChars = '!@#$%^&*()+=-[]\\\';,./{}|\":<>?`';
									// var iChars ='/^[a-zA-Z_\-]+$/';
							             if(floor_name.trim()=="") {
							                        alert("No Space allowed!");
							                        $('#floor_txt_' + idArr[rec_id]).focus();
							                        return false;
							                    }
								
										if(floor_name.length>0){
											for (var i = 0; i < floor_name.length; i++) {
							                    if (iChars.indexOf(floor_name.charAt(i)) != -1) {
							                    
							                        alert ("Special characters for Floor Name is not allowed");
							                        $('#floor_txt_' + idArr[rec_id]).focus();
							                        return false;
							                    }
							                    
							                   
							                    
							                }	
										
										}else{
											 alert ("Please Enter Floor Name");
											 return false;
										}
									$.ajax({
												type : 'POST',
												url : "FloorAjaxAction!insertrows?id="
														+ busstation_id
														+ "&floorname="
														+ floor_name
														+ "&floorstatus="
														+ floor_status
														+ "&parentID="
														+ busstation_id,
												success : function(data) {
													//alert(data);
													if (data != "") {
														if(data.indexOf("SuccessFully")!= -1)
														{
																$("#errormsg").hide();															
														var divId = document.getElementById("successmsg");
														divId.style.display = 'block';
														divId.style.visibility = 'visible';
														document.getElementById('successfloor').innerHTML = data;
														}else{
																$("#successmsg").hide();	
														var divId = document.getElementById("errormsg");
														divId.style.display = 'block';
														divId.style.visibility = 'visible';
														document.getElementById('errorfloor').innerHTML = data;
															}
														var oTable = $(
																'#floorView')
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
																			"sAjaxSource" : "FloorAjaxAction!getFloorData?busid="
																					+ busstation_id,
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
														.addClass("form-control input-small"); // modify table per
												// page dropdown
												jQuery(
														'#floorView_wrapper .dataTables_length select')
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
								 oTable = $('#floorView').dataTable(
												{
													"aLengthMenu" : [
															[ 5, 15, 20, -1 ],
															[ 5, 15, 20, "All" ] // change
													// per
													// page
													// values
													// here
													],
													// set the initial value
													"iDisplayLength" : 5,
													"sAjaxSource" : "FloorAjaxAction!getFloorData?busid="
															+ busstation_id,
													"sPaginationType" : "bootstrap",
													"stateSave" : true,
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

								jQuery('#floorView_wrapper .dataTables_filter input').addClass("form-control input-medium input-inline"); // modify
								// table
								// search
								// input
								jQuery('#floorView_wrapper .dataTables_length select').addClass("form-control input-small"); // modify table per
								// page dropdown
								jQuery('#floorView_wrapper .dataTables_length select').select2({
											showSearchInput : true
										// hide search box with special css class
										}); // initialize select2 dropdown

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
			var busstation_id = $('#parent_id').val();

			var oTable = $('#floorView').dataTable(
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
						"sAjaxSource" : "FloorAjaxAction!getFloorData?busid="
								+ busstation_id,
						"sPaginationType" : "bootstrap",
						"stateSave" : true,
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

			jQuery('#floorView_wrapper .dataTables_filter input').addClass("form-control input-medium input-inline"); // modify
			// table
			// search
			// input
			jQuery('#floorView_wrapper .dataTables_length select').addClass("form-control input-small"); // modify table per
			// page dropdown
			jQuery('#floorView_wrapper .dataTables_length select').select2({
				showSearchInput : true
			// hide search box with special css class
			}); // initialize select2 dropdown

			var nEditing = null;

			//$('#floorNew ').unbind().die().live().click(function(e) 
				$('#floorNew').unbind().click(function(e) {
					e.preventDefault();
				/* int i=0;
				alert(i);
				i++; */
				//e.preventDefault();
				if(busstation_id==0){
					bootbox.alert("Please select BusStation");
				}else{
				var aiNew = oTable.fnAddData([ '', '', '', '', '' ]);
				var nRow = oTable.fnGetNodes(aiNew[0]);

				savRow(oTable, nRow, new_id, new_id);
				nEditing = nRow;
				newrow = aiNew;
				new_id += 1;
				}
			});

			$('#floorView a.delete').unbind().die().live('click', function(e) {
				e.preventDefault();

				if (confirm("Are you sure to delete this row ?") == false) {
					return;
				}
				var delete_id = $(this).attr('value');
				// alert(delete_id);
				var nRow = $(this).parents('tr')[0];
				//oTable.fnDeleteRow(nRow);
				/* $.ajax({

					url : "FloorAjaxAction!deleterows?id=" + delete_id,
					context : document.body
				}).done(function() {
					$(this).addClass("done");

				}); */
				$
				.ajax({
					type : 'POST',
					url : "FloorAjaxAction!deleterows?id=" + delete_id,
					success : function(data) {
						
						if (data != "") {
							$("#successmsg").hide();				   
							var divId = document.getElementById("errormsg");
							divId.style.display = 'block';
							divId.style.visibility = 'visible';
							//alert(data);
							//var n = data.indexOf(":");
							//alert(n);
							 w=data.replace(/@/g,"<br>");
							document.getElementById('errorfloor').innerHTML = w;
							var oTable = $(
							'#floorView')
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
										"sAjaxSource" : "FloorAjaxAction!getFloorData?busid="
												+ busstation_id,
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
				deletedid += delete_id + ",";
				// alert(deletedid);
				restoreRow(oTable, nEditing);

			});

			$('#floorView a.cancel').unbind().die().live('click', function(e) {
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
			$('#floorView a.deleterow').live('click', function(e) {

				var nRow = $(this).parents('tr')[0];
				//oTable.fnDeleteRow(nRow);

				// nEditing = null;

			});

			$('#floorView a.edit').unbind().die().live(
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
									editRow(oTable, nRow, edit_id, rec_id);
									nEditing = nRow;
								}
								edit_id += 1;
							});

			//End Code Before This

			$('#backbtn').unbind().click(function(e) {
				e.preventDefault();
				//alert('HI');
				document.forms[0].action = "FloorAction!view";
				document.forms[0].submit();
			});
			$('#floor_save_tab_bus').unbind().click(function(e) {
				e.preventDefault();

				document.forms[0].action = "AllBusStations.action";
				document.forms[0].submit();
			});
			
			
		}
		$(document).ready(function() {			
			/* var bsusiid=$('#parent_id').val();
			alert("parent_id"+bsusiid); */
			getFloorDetails(this.value);
			//window.history.pushState("", "", "FloorAjaxAction!getFloorData?busid="+busstation_id+"");
			}); 
			
		
	
	</script>
</body>
</html>