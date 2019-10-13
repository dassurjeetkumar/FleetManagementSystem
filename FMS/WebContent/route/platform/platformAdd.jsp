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

	 SelectElement('<s:property value="platform.status"/>');

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

function loadbay(val){

    $.ajax({
        type: "post",
        url: '<%=request.getContextPath()%>/PlatformAction!getBayName?orgid='+ val,
			success : function(result) {
				document.getElementById('bay_id').innerHTML = result;
			}
		});
}

function loadbayOnpgLoad(){

	var e = document.getElementById("floor_id");
	var val = e.options[e.selectedIndex].value;

	var len= document.getElementById('bay_id').options.length;

	 if(len<=1 ) {
	
    $.ajax({
        type: "post",
        url: '<%=request.getContextPath()%>/PlatformAction!getBayName?orgid='+ val,
						success : function(result) {
							document.getElementById('bay_id').innerHTML = result;
						}
					});
		}
	}

	function checkInt() {

		var val = document.getElementById('latitude').value;

		if (isNaN(val)) {
			//document.getElementById('integerVal').innerHTML='Invalid value';
			alert('Please enter valid latitude value');
			return false;
		}

		val = document.getElementById('longitude').value;

		if (isNaN(val)) {
			//document.getElementById('integerVal').innerHTML='Invalid value';
			alert('Please enter valid longitude value');
			return false;
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
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "PlatformAction!view");
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
								<i class="fa fa-gift"></i>Create Platform
							</div>
							<div class="tools"></div>
						</div>
<%if (create.equalsIgnoreCase("Y")){%>
						<div class="portlet-body form">
							<!-- BEGIN FORM-->
							<form action="AddPlatformAction" class="form-horizontal"
								method="post">
								<font color="red"><s:actionerror /></font> <br>
								<br>
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label"> Bus Station
											Name:<font color="red">*</font>
										</label>

										<div class="col-md-4">

											<s:select list="orgNameMap" id="parent_id"
												name="platform.bay.floor.orgChart.org_chart_id"
												cssClass="select2_category form-control" headerKey="0"
												headerValue="Select" onchange="loadfloor(this.value);"></s:select>

											<%--  <select class="form-control" name="fareChartMaster.route_id" 
											 id="route_id" onclick="getRouteNo()">
											
											</select> --%>
											<s:if
												test="fieldErrors.get('bay.floor.orgChart.org_chart_id').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('bay.floor.orgChart.org_chart_id').get(0)" /></span>
											</s:if>


										</div>
									</div>
								</div>

								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Floor
											Name:<font color="red">*</font>
										</label>

										<div class="col-md-4">

											<%-- <s:select list="floorNameMap" id="floor_id" name="bay.floor.floor_id" 
		cssClass="select2_category form-control" headerKey="0" headerValue="Select"></s:select> --%>

											<select class="form-control" id="floor_id"
												name="platform.bay.floor.floor_id"
												onchange="loadbay(this.value)" onclick="loadfloorOnpgLoad()">
												<option
													value="<s:property value="platform.bay.floor.floor_id"/>">
													<s:property value="platform.bay.floor.floor_name" />
												</option>
											</select>

											<s:if test="fieldErrors.get('bay.floor.floor_id').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('bay.floor.floor_id').get(0)" /></span>
											</s:if>
										</div>
									</div>
								</div>



								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Bay
											Name:<font color="red">*</font>
										</label>

										<div class="col-md-4">

											<%-- <s:select list="bayNameMap" id="bay_id" name="platform.bay.bay_id" 
		cssClass="select2_category form-control" headerKey="0" headerValue="Select"></s:select>
		 --%>
											<select class="form-control" id="bay_id"
												name="platform.bay.bay_id" onclick="loadbayOnpgLoad()"
												onchange="getPlatformData(this.value);">
												<option value="<s:property value="platform.bay.bay_id"/>">
													<s:property value="platform.bay.bay_name" />
												</option>
											</select>
											<s:if test="fieldErrors.get('bay.bay_id').size() > 0">
												<span style="color: red;"><s:property
														value="fieldErrors.get('bay.bay_id').get(0)" /></span>
											</s:if>

										</div>
									</div>
								</div>
								<div class="portlet box blue" id="platformdiv"
									style="visibility: hidden">
									<div class="portlet-title">
										<div class="caption">
											<i class="fa fa-edit"></i>PlatForm Details
										</div>

									</div>
									<div class="portlet-body">
										<div class="table-toolbar">
											<div class="btn-group">
												<button id="floorNew" class="btn green">
													Add New Platform <i class="fa fa-plus"></i>
												</button>
											</div>

										</div>
										<div  id="successmsg"
											style="display: none">
											<p id="successfloor" style="color: green"></p>
											<span> </span>
										</div>
										<div class="alert alert-danger" id="errormsg"
											style="display: none">
											<p class="intro" id="errorfloor"></p>

											<span> </span>
										</div>
										<div id="floorMoreDetails" style="visibility: hidden">
											<div id="busname"></div>
										</div>
										<table class="table table-striped table-hover table-bordered"
											id="platformDatatable">
											<thead>
												<tr>

													<th>PlatForm Id</th>
													<th>PlatForm Name</th>
													<th>Status</th>
													<th>Edit</th>
													<th>Delete</th>
												</tr>

											</thead>

										</table>
										<button id="platform_save_tab" class="btn green">
											Back</button>
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
		function getPlatformData(busstation_idd) {
			var div = document.getElementById('platformdiv');
			div.style.visibility = 'visible';
			document.getElementById('floorMoreDetails').style.visibility = 'visible';
			parentID = $('#parent_id').find(":selected").text();
			floorId = $('#floor_id').find(":selected").text();
			bayId = $('#bay_id').find(":selected").text();
			//var xx=document.getElementById('parent_id');
			document.getElementById('busname').innerHTML = "<B>" + parentID
					+ "-->" + floorId + "-->" + bayId + "</B>";
			var update_id = "";
			var new_id = 0;
			var edit_id = 0;
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
				// alert(status);
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
				jqTds[1].innerHTML = '<input type="text" class="form-control input-small" id="floor_txt_'
				+ rec_id + '" value="' + aData[1] + '">';
				jqTds[3].innerHTML = '<a class="savenew" href="" value="'+ rec_id +'">Save</a>';
				jqTds[4].innerHTML = '<a class="deleterow" href="">Cancel</a>';
				//alert(rec_id);

				insert_id += newdata + ",";

			}
			var bay_id = $('#bay_id').val();
			$('#platformDatatable a.saveEdit')

					.unbind()
					.live(
							'click',
							function(e) {
								e.preventDefault();
								var nRow = $(this).parents('tr')[0];
								var datas = $(this).attr('value').split(",");
								var rec_id = datas[0];
								var id = datas[1];
								var idArr = update_id.split(',');
								var floor_name = $(
										'#floor_txt_' + idArr[rec_id]).val();
								var floor_status = $(
										'#floor_status_' + idArr[rec_id]).val();
								
								if (floor_name == undefined) {
								} else {
									var iChars = '!@#$%^&*()+=-[]\\\';,./{}|\":<>?`';
									if(floor_name.length>0){
										for (var i = 0; i < floor_name.length; i++) {
						                    if (iChars.indexOf(floor_name.charAt(i)) != -1) {
						                    
						                        alert ("special characters for PlatForm Name is not allowed");
						                        $('#floor_txt_' + idArr[rec_id]).focus();
						                        return false;
						                    }
						                }	
									
									}else{
										 alert ("Please Enter PlatForm Name");
										 return false;
									}
									$.ajax({
										type: 'POST',
										url : "platFormAjaxAction!updaterows?id="
											+ id
											+ "&floorname="
											+ floor_name
											+ "&floorstatus="
											+ floor_status
											+ "&parentID="
											+ bay_id,
										success: function(data)
										{
											//alert(data);
											if(data!=""){
												if(data.indexOf("Successfully")!= -1)
												{
													var divId = document.getElementById("successmsg");
												divId.style.display = 'block';
												divId.style.visibility = 'visible';
												document.getElementById('successfloor').innerHTML = data;
												}
													else{
								        			$("#successmsg").hide();												
												var divId = document.getElementById("errormsg");
												divId.style.display = 'block';
												divId.style.visibility = 'visible';
												w=data.replace(/@/g,"<br>");
												divId.innerHTML = w;
													}
							        			var oTable = $('#platformDatatable').dataTable(
							    						{
							    							"aLengthMenu" : [ [ 5, 15, 20, -1 ],
							    									[ 5, 15, 20, "All" ] // change
							    							],
							    							// set the initial value
							    							"iDisplayLength" : 5,
							    							"sAjaxSource" : "platFormAjaxAction!getPlatFormData?busid="+bay_id,
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
								var busstation_id = $('#parent_id').val();
								

							});

			$('#platformDatatable a.savenew')

					.unbind()
					.live(
							'click',
							function(e) {
								e.preventDefault();
								var nRow = $(this).parents('tr')[0];
								var insertid = insert_id.split(',');
								var rec_id = $(this).attr('value');
								var busstation_id = $('#parent_id').val();
								var parent_id = $('#parent_id').val();
								var floor_id = $('#floor_id').val();
								var bay_id = $('#bay_id').val();
								if ($('#floor_txt_' + insertid[rec_id]).val() != undefined) {
									var floor_name = $(
											'#floor_txt_' + insertid[rec_id])
											.val();
									var floor_status = $(
											'#floor_status_' + insertid[rec_id])
											.val();
									// AjaxCall To Update Values..
									var iChars = '!@#$%^&*()+=-[]\\\';,./{}|\":<>?`';
									if(floor_name.length>0){
										for (var i = 0; i < floor_name.length; i++) {
						                    if (iChars.indexOf(floor_name.charAt(i)) != -1) {
						                    
						                        alert ("special characters for PlatForm Name is not allowed");
						                        $('#floor_txt_' + idArr[rec_id]).focus();
						                        return false;
						                    }
						                }	
									
									}else{
										 alert ("Please Enter PlatForm Name");
										 return false;
									}
									$
					.ajax({
						type : 'POST',
						url : "platFormAjaxAction!insertrows?id="
							+ parent_id
							+ "&floor_id="
							+ floor_id
							+ "&bay_id="
							+ bay_id
							+ "&floorname="
							+ floor_name
							+ "&floorstatus="
							+ floor_status
							+ "&parentID="
							+ bay_id,
						success : function(data) {
							//alert(data);
							if (data != "") {
								if(data.indexOf("Successfully")!= -1)
								{
									
									alert(data);
									var divId = document.getElementById("successmsg");
								
								divId.style.display = 'block';
								divId.style.visibility = 'visible';
								document.getElementById('successfloor').innerHTML = data;
								}
									else{
								var divId = document
										.getElementById("errormsg");
								divId.style.display = 'block';
								divId.style.visibility = 'visible';
								document
										.getElementById('errorfloor').innerHTML = data;
									}
								var oTable = $(
										'#platformDatatable')
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
													"sAjaxSource" : "platFormAjaxAction!getPlatFormData?busid="
															+ bay_id,
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
								'#platformDatatable_wrapper .dataTables_filter input')
								.addClass(
										"form-control input-medium input-inline"); // modify
						// table
						// search
						// input
						jQuery(
								'#platformDatatable_wrapper .dataTables_length select')
								.addClass("form-control input-small"); // modify table per
						// page dropdown
						jQuery(
								'#platformDatatable_wrapper .dataTables_length select')
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
					
									/* $
											.ajax(
													{

														url : "platFormAjaxAction!insertrows?id="
																+ parent_id
																+ "&floor_id="
																+ floor_id
																+ "&bay_id="
																+ bay_id
																+ "&floorname="
																+ floor_name
																+ "&floorstatus="
																+ floor_status,
														context : document.body
													}).done(function() {
												$(this).addClass("done");

											});
 */
									//
								}
								var oTable = $('#platformDatatable')
										.dataTable(
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
													"sAjaxSource" : "platFormAjaxAction!getPlatFormData?busid="
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
										'#platformDatatable_wrapper .dataTables_filter input')
										.addClass(
												"form-control input-medium input-inline"); // modify
								// table
								// search
								// input
								jQuery(
										'#platformDatatable_wrapper .dataTables_length select')
										.addClass("form-control input-small"); // modify table per
								// page dropdown
								jQuery(
										'#platformDatatable_wrapper .dataTables_length select')
										.select2({
											showSearchInput : true
										// hide search box with special css class
										}); // initialize select2 dropdown

							});
			
			var busstation_id = $('#parent_id').val();

			var oTable = $('#platformDatatable')
					.dataTable(
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
								"sAjaxSource" : "platFormAjaxAction!getPlatFormData?busid="
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

			jQuery('#platformDatatable_wrapper .dataTables_filter input')
					.addClass("form-control input-medium input-inline"); // modify
			// table
			// search
			// input
			jQuery('#platformDatatable_wrapper .dataTables_length select')
					.addClass("form-control input-small"); // modify table per
			// page dropdown
			jQuery('#platformDatatable_wrapper .dataTables_length select')
					.select2({
						showSearchInput : true
					// hide search box with special css class
					}); // initialize select2 dropdown

			var nEditing = null;

			$('#floorNew').unbind().click(function(e) {
				e.preventDefault();

				var aiNew = oTable.fnAddData([ '', '', '', '', '' ]);
				var nRow = oTable.fnGetNodes(aiNew[0]);

				savRow(oTable, nRow, new_id, new_id);
				nEditing = nRow;
				newrow = aiNew;
				new_id += 1;
			});

			$('#platformDatatable a.delete')
					.unbind()
					.live(
							'click',
							function(e) {
								e.preventDefault();

								if (confirm("Are you sure to delete this row ?") == false) {
									return;
								}
								var delete_id = $(this).attr('value');
								// alert(delete_id);
								var nRow = $(this).parents('tr')[0];
								oTable.fnDeleteRow(nRow);
								$
								.ajax({
									type : 'POST',
									url : "platFormAjaxAction!deleterows?id=" + delete_id,
									success : function(data) {
										//alert(data);
										if (data != "") {
											var divId = document
													.getElementById("errormsg");
											divId.style.display = 'block';
											divId.style.visibility = 'visible';
											//document.getElementById('errorfloor').innerHTML = data;
											 w=data.replace(/@/g,"<br>");

											  // $('#errorfloor').html(''+w+'');
											    document.getElementById('errorfloor').innerHTML = w;
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

			$('#platformDatatable a.cancel').unbind().live('click',
					function(e) {
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
			$('#platformDatatable a.deleterow').live('click', function(e) {

				var nRow = $(this).parents('tr')[0];
				oTable.fnDeleteRow(nRow);

				// nEditing = null;

			});

			$('#platformDatatable a.edit')
					.unbind()
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
									editRow(oTable, nRow, edit_id, rec_id);
									nEditing = nRow;
								}
								edit_id += 1;
							});

			$('#platform_save_tab').unbind().click(function(e) {
				e.preventDefault();

				document.forms[0].action = "PlatformAction!view";
				document.forms[0].submit();
			});
		}
	</script>

</body>
</html>