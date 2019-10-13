var TableEditableFloor = function() {

	return {

		// main function to initiate the module
		init : function() {
			// Strat Code After This
			$("#successmsg").hide();
			$("#errormsg").hide();
			var busstation_id = $('#parent_id').val();
			var oTable = $('#sample_editable_1').dataTable(
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
			jQuery('#sample_editable_1_wrapper .dataTables_filter input').addClass(
					"form-control input-medium input-inline"); // modify
			// table
			// search
			// input
			jQuery('#sample_editable_1_wrapper .dataTables_length select').addClass(
					"form-control input-small"); // modify table per
			// page dropdown
			jQuery('#sample_editable_1_wrapper.dataTables_length select').select2({
				showSearchInput : true
			// hide search box with special css class
			}); // initialize select2 dropdown

		/*	jQuery('#sample_editable_1_wrapper.dataTables_filter input')
					.addClass("form-control input-small input-inline"); // modify
			// table
			// search
			// input
			jQuery('#sample_editable_1_wrapper.dataTables_length select')
					.addClass("form-control input-xsmall input-inline"); // modify
			// table
			// per
			// page dropdown
			jQuery('#sample_editable_1_wrapper .dataTables_length select')
					.select2({
						showSearchInput : true
					// hide search box with special css
					// class
					}); // initialize select2 dropdown
*/
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
								+ '" class="form-control"><option  id="active" value="ACTIVE">ACTIVE</option><option id="inactive" value="INACTIVE">INACTIVE</option></select>';
					} else {
						jqTds[2].innerHTML = '<select id="floor_status_'
								+ rec_id
								+ '" class="form-control"><option id="inactive" value="INACTIVE">INACTIVE</option><option  id="active" value="ACTIVE">ACTIVE</option></select>';
					}
				}
				jqTds[0].innerHTML = '<input type="test" id="new_floor_id_'
						+ newdata
						+ '" value="'
						+ aData[0]
						+ '" readonly="readonly" class="form-control input-small" >';
				jqTds[1].innerHTML = '<input type="text" class="form-control input-small" id="floor_txt_'
						+ rec_id + '" value="' + aData[1] + '">';
				jqTds[3].innerHTML = '<a class="saveEdit" value="' + rec_id
						+ "," + newdata + '" href="" >Save</a>';
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
						+ newdata
						+ '" value="'
						+ aData[1]
						+ '" class="form-control input-small" >';
				jqTds[1].innerHTML = '<input type="text" class="form-control input-small" id="floor_txt_'
						+ rec_id + '" value="' + aData[1] + '">';
				jqTds[3].innerHTML = '<a class="savenew" value="' + rec_id
						+ '" href="">Save</a>';
				jqTds[4].innerHTML = '<a class="deleterow" href="">Cancel</a>';
				// alert(rec_id);

				insert_id += newdata + ",";

			}
			var busstation_id = $('#parent_id').val();
			// alert(busstation_id);
			$('#sample_editable_1 a.saveEdit')
					.unbind()
					.die()
					.live(
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
								// alert(busstation_id);
								// alert(floor_name+"\t"+floor_status+"\t"+id);
								if (floor_name == undefined) {
								} else {

									var iChars = '!@#$%^&*()+=-[]\\\';,./{}|\":<>?`';
									if(floor_name.trim()=="") {
				                        alert("No Space allowed!");
				                        $('#floor_txt_' + idArr[rec_id]).focus();
				                        return false;
				                    }
									if (floor_name.length > 0) {
										for ( var i = 0; i < floor_name.length; i++) {
											if (iChars.indexOf(floor_name
													.charAt(i)) != -1) {
												bootbox
														.alert("Special characters for Floor Name is not allowed");
												$('#floor_txt_' + idArr[rec_id])
														.focus();
												return false;
											}
											
										}

									} else {
										bootbox
												.alert("Please Enter Floor Name");
										return false;
									}
									$
											.ajax({
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
													// alert(data);
													if (data != "") {
														if (data
																.indexOf("Successfully") != -1) {
															$("#errormsg")
																	.hide();
															var divId = document
																	.getElementById("successmsg");
															divId.style.display = 'block';
															divId.style.visibility = 'visible';
															document
																	.getElementById('successfloor').innerHTML = data;
														} else {
															$("#successmsg")
																	.hide();
															var divId = document
																	.getElementById("errormsg");
															divId.style.display = 'block';
															divId.style.visibility = 'visible';
															document
																	.getElementById('errorfloor').innerHTML = data;
														}
														var oTable = $(
																'#sample_editable_1')
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

													} else {

													}
												},
												error : function(e) {
													alert(e.message);
												}
											});

								}
								e.preventDefault();

								jQuery('#sample_editable_1_wrapper .dataTables_filter input').addClass(
								"form-control input-medium input-inline"); // modify
						// table
						// search
						// input
						jQuery('#sample_editable_1_wrapper .dataTables_length select').addClass(
								"form-control input-small"); // modify table per
						// page dropdown
						jQuery('#sample_editable_1_wrapper.dataTables_length select').select2({
							showSearchInput : true
						// hide search box with special css class
						}); // initialize select2 dropdown

							});

			$('#sample_editable_1 a.savenew')

					.unbind()
					.die()
					.live(
							'click',
							function(e) {
								e.preventDefault();
								var nRow = $(this).parents('tr')[0];
								var insertid = insert_id.split(',');
								var rec_id = $(this).attr('value');
								var busstation_id = $('#parent_id').val();

								if ($('#floor_txt_' + insertid[rec_id]).val() != undefined) {
									var floor_name = $(
											'#floor_txt_' + insertid[rec_id])
											.val();
									var floor_status = $(
											'#floor_status_' + insertid[rec_id])
											.val();
									// alert(floor_name);
									var iChars = '!@#$%^&*()+=-[]\\\';,./{}|\":<>?`';
									if(floor_name.trim()=="") {
				                        alert("No Space allowed!");
				                        $('#floor_txt_' + idArr[rec_id]).focus();
				                        return false;
				                    }
									if (floor_name.length > 0) {
										for ( var i = 0; i < floor_name.length; i++) {
											if (iChars.indexOf(floor_name
													.charAt(i)) != -1) {

												alert("Special characters for Floor Name is not allowed");
												$('#floor_txt_' + idArr[rec_id])
														.focus();
												return false;
											}
											
										}

									} else {
										alert("Please Enter Floor Name");
										return false;
									}
									$
											.ajax({
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
													// alert(data);
													if (data != "") {
														if (data
																.indexOf("SuccessFully") != -1) {
															$("#errormsg")
																	.hide();
															var divId = document
																	.getElementById("successmsg");
															divId.style.display = 'block';
															divId.style.visibility = 'visible';
															document
																	.getElementById('successfloor').innerHTML = data;
														} else {
															$("#successmsg")
																	.hide();
															var divId = document
																	.getElementById("errormsg");
															divId.style.display = 'block';
															divId.style.visibility = 'visible';
															document
																	.getElementById('errorfloor').innerHTML = data;
														}
														var oTable = $(
																'#sample_editable_1')
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
														jQuery('#sample_editable_1_wrapper .dataTables_filter input').addClass(
														"form-control input-medium input-inline"); // modify
												// table
												// search
												// input
												jQuery('#sample_editable_1_wrapper .dataTables_length select').addClass(
														"form-control input-small"); // modify table per
												// page dropdown
												jQuery('#sample_editable_1_wrapper.dataTables_length select').select2({
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
								var oTable = $('#sample_editable_1')
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

								jQuery('#sample_editable_1_wrapper .dataTables_filter input').addClass(
								"form-control input-medium input-inline"); // modify
						// table
						// search
						// input
						jQuery('#sample_editable_1_wrapper .dataTables_length select').addClass(
								"form-control input-small"); // modify table per
						// page dropdown
						jQuery('#sample_editable_1_wrapper.dataTables_length select').select2({
							showSearchInput : true
						// hide search box with special css class
						}); // initialize select2 dropdown

							});
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

			$('#sample_editable_1 a.delete')
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
								// alert(delete_id);
								var nRow = $(this).parents('tr')[0];
								// oTable.fnDeleteRow(nRow);

								$
										.ajax({
											type : 'POST',
											url : "FloorAjaxAction!deleterows?id="
													+ delete_id,
											success : function(data) {
												// alert(data);
												if (data != "") {
													$("#successmsg").hide();
													var divId = document
															.getElementById("errormsg");
													divId.style.display = 'block';
													divId.style.visibility = 'visible';
													// alert(data);
													// var n =
													// data.indexOf(":");
													// alert(n);
													// document.getElementById('errorfloor').innerHTML
													// = data;
													// var w=$('#errorMsg
													// span').html();
													// alert(w);
													w = data.replace(/@/g,
															"<br>");

													// $('#errorfloor').html(''+w+'');
													document
															.getElementById('errorfloor').innerHTML = w;

													var oTable = $(
															'#sample_editable_1')
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
													jQuery('#sample_editable_1_wrapper .dataTables_filter input').addClass(
													"form-control input-medium input-inline"); // modify
											// table
											// search
											// input
											jQuery('#sample_editable_1_wrapper .dataTables_length select').addClass(
													"form-control input-small"); // modify table per
											// page dropdown
											jQuery('#sample_editable_1_wrapper.dataTables_length select').select2({
												showSearchInput : true
											// hide search box with special css class
											}); // initialize select2 dropdown

												}

												else {

												}
											},
											error : function(e) {
												alert(e.message);
											}
										});

								// deletedid += delete_id + ",";
								// alert(deletedid);
								restoreRow(oTable, nEditing);

							});

			$('#sample_editable_1 a.cancel').unbind().die().live('click',
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
			$('#sample_editable_1 a.deleterow').live('click', function(e) {

				var nRow = $(this).parents('tr')[0];
				oTable.fnDeleteRow(nRow);

				// nEditing = null;

			});

			$('#sample_editable_1 a.edit')
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
									editRow(oTable, nRow, edit_id, rec_id);
									nEditing = nRow;
								}
								edit_id += 1;
							});

			// End Code Before This

			$('#backbtn').unbind().click(function(e) {
				e.preventDefault();
				// alert('HI');
				document.forms[0].action = "FloorAction!view";
				document.forms[0].submit();
			});
			$('#floor_save_tab_bus').unbind().click(function(e) {
				e.preventDefault();

				document.forms[0].action = "AllBusStations.action";
				document.forms[0].submit();
			});

		}

	};

}();
