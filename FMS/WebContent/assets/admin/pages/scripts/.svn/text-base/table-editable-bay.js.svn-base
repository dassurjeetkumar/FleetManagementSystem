var TableEditableBay = function() {

	return {

		// main function to initiate the module
		init : function() {
			// Strat Code After This


			var update_id = "";
			var new_id = 1;
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
				jqTds[0].innerHTML = '<input type="hidden" id="new_floor_id_'
						+ newdata + '" value="' + newdata
						+ '" class="form-control input-small" >';
				jqTds[1].innerHTML = '<input type="text" class="form-control input-small" id="floor_txt_'
						+ rec_id + '" value="' + aData[1] + '">';
				jqTds[3].innerHTML = '';
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
				jqTds[0].innerHTML = '<input type="hidden" id="new_floor_id_'
						+ newdata + '" value="' + newdata
						+ '" class="form-control input-small" >';
				jqTds[1].innerHTML = '<input type="text" class="form-control input-small" id="floor_txt_'
						+ rec_id + '" value="' + aData[1] + '">';
				jqTds[3].innerHTML = '';
				jqTds[4].innerHTML = '<a class="deleterow">Cancel</a>';
				if (status != '') {
					update_id += rec_id + ",";
				} else {
					insert_id += newdata + ",";
				}
			}

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
								+ busstation_id,
						"sPaginationType" : "bootstrap",
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

			$('#bayDatatable_new').data('mode', 'new').click(function(e) {
				e.preventDefault();

				var aiNew = oTable.fnAddData([ '', '', '', '', '' ]);
				var nRow = oTable.fnGetNodes(aiNew[0]);

				savRow(oTable, nRow, new_id, new_id);
				nEditing = nRow;
				newrow = aiNew;
				new_id += 1;
			});

			$('#bayDatatable a.delete')
					.data('mode', 'delete')
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
								// alert("Deleted! Do not forget to do some ajax
								// to sync with backend :)");
								deletedid += delete_id + ",";
								// alert(deletedid);
								restoreRow(oTable, nEditing);

							});

			$('#bayDatatable a.cancel').live('click', function(e) {
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
					.data('mode', 'edit')
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
									editRow(oTable, nRow, rec_id);
									nEditing = nRow;
								} else if (nEditing == nRow
										&& this.innerHTML == "Save") {
									/* Editing this row and want to save it */
									saveRow(oTable, nEditing);
									nEditing = null;
									alert("Updated! Do not forget to do some ajax to sync with backend :)");
								} else {
									/* No edit in progress - let's start one */
									editRow(oTable, nRow, rec_id);
									nEditing = nRow;
								}

							});

			$('#floor_save_tab ')
					.click(
							function(e) {
								e.preventDefault();
								/*
								 * alert(insert_id); alert(update_id);
								 * alert($('#parent_id').val());
								 */
								var idArr = update_id.split(',');
								var insertid = insert_id.split(',');
								var deletedID = deletedid.split(',');
								var busstation_id = $('#parent_id').val();
								alert($('#floor_txt_' + insertid[i])
										.val());
								/*
								 * if(1 != 2) { alert('true'); }else{
								 * alert('false'); }
								 */
								if (idArr.length >= 2) {
									for ( var i = 0; i < idArr.length - 1; i++) {
										// alert(idArr[i]);
										var floor_name = $(
												'#floor_txt_' + idArr[i]).val();
										var floor_status = $(
												'#floor_status_' + idArr[i])
												.val();
										// alert(Type($('#floor_txt_' +
										// idArr[i]).val()));
										if (floor_name == undefined) {
										} else {
											/*
											 * alert($('#floor_txt_' +
											 * idArr[i]).val());
											 * alert($('#floor_status_' +
											 * idArr[i]).val());
											 */

											// AjaxCall To Update Values..
											$
													.ajax(
															{

																url : "bayAjaxAction!updaterows?id="
																		+ idArr[i]
																		+ "&floorname="
																		+ floor_name
																		+ "&floorstatus="
																		+ floor_status,
																context : document.body
															})
													.done(
															function() {
																$(this)
																		.addClass(
																				"done");

															});

											//
										}
									}

								}

								if (insertid.length >= 2) {
									// Code for insert new Record..
									for ( var i = 0; i < insertid.length - 1; i++) {
										if ($('#floor_txt_' + insertid[i])
												.val() != undefined) {
											/*
											 * alert($('#floor_txt_' +
											 * insertid[i]).val());
											 * alert($('#floor_status_' +
											 * insertid[i]).val());
											 */
											var floor_name = $(
													'#floor_txt_' + insertid[i])
													.val();
											var floor_status = $(
													'#floor_status_'
															+ insertid[i])
													.val();
											// AjaxCall To Update Values..
											$
													.ajax(
															{

																url : "bayAjaxAction!insertrows?id="
																		+ busstation_id
																		+ "&floorname="
																		+ floor_name
																		+ "&floorstatus="
																		+ floor_status,
																context : document.body
															})
													.done(
															function() {
																$(this)
																		.addClass(
																				"done");

															});

											//
										}
									}

								}

								if (deletedID.length >= 2) {
									// Code For Delete Record!!
									for ( var i = 0; i < deletedID.length - 1; i++) {

										// AjaxCall To Update Values..
										$.ajax(
												{
															url : "bayAjaxAction!deleterows?id="
																	+ deletedID[i],
															context : document.body
														}).done(function() {
													$(this).addClass("done");

												});

										//

									}

									//

								}
								idArr.length = 0;
								update_id = "";
								new_id = 1;
								insert_id = "";
								deletedid = "";
								$('#bayDatatable').dataTable()
										.fnClearTable();
								$('#bayDatatable').dataTable().fnDestroy();
								document.forms[0].action="AllBusStations.action";
								document.forms[0].submit();
								
							});



			//End Code Before This

		}

	};

}();
