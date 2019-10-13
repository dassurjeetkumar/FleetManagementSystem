var TableEditableTicket = function() {

	return {

		init : function() {
			var update_id = "";
			var update_pass_id = "";
			var update_luggage_id = "";
			var edit_id = 0;
			var new_id = 1;
			var insert_id = "";
			var deletedid = "";
			function restoreRow(oTable, nRow) {
				var aData = oTable.fnGetData(nRow);
				var jqTds = $('>td', nRow);
				for ( var i = 0, iLen = jqTds.length; i < iLen; i++) {
					oTable.fnUpdate(aData[i], nRow, i, false);
				}
				oTable.fnDraw();
			}

			function editRow(oTable, nRow, rec_id, newdata) {
				
				var aData = oTable.fnGetData(nRow);
				var jqTds = $('>td', nRow);
				update_id += rec_id + ",";
				
				jqTds[0].innerHTML = '<input type="hidden" id="tickinvmstid_'+ rec_id + '" class="form-control input-small" value="'+ newdata + '" style="visibility:hidden">';
				jqTds[1].innerHTML = '<input type="text"  disabled="disabled" class="form-control input-small" value="'	+ aData[1] + '" >';
				jqTds[2].innerHTML = '<input type="text" class="form-control input-small" id="deno_key_'+ rec_id+ '" disabled="disabled" value="'+ aData[2]+ '">';
				jqTds[3].innerHTML = '<input type="text" disabled="disabled" class="form-control input-small" id="floor_txt_'+ rec_id + '" value="' + aData[3] + '">';
				jqTds[4].innerHTML = '<input type="text" disabled="disabled" class="form-control input-small" id="floor_txt_'+ rec_id + '" value="' + aData[4] + '">';
				jqTds[5].innerHTML = '<input type="text" disabled="disabled" class="form-control input-small" id="floor_txt_'+ rec_id + '" value="' + aData[5] + '">';
				jqTds[6].innerHTML = '<input type="text" disabled="disabled" class="form-control input-small" id="floor_txt_'+ rec_id + '" value="' + aData[6] + '">';
				
				var jqTds = $('>td', nRow);
				update_id += rec_id + ",";
				
				jqTds[0].innerHTML = '<input type="hidden" id="tickinvmstid_'+ rec_id + '" class="form-control input-small" value="'+ newdata + '" style="visibility:hidden">';
				jqTds[1].innerHTML = '<input type="text"  disabled="disabled" class="form-control input-small" value="'+ aData[1] + '" >';
				jqTds[2].innerHTML = '<input type="text" class="form-control input-small" id="deno_key_'+ rec_id+ '" disabled="disabled" value="'+ aData[2]	+ '">';
				jqTds[3].innerHTML = '<input type="text" disabled="disabled" class="form-control input-small" id="floor_txt_'+ rec_id + '" value="' + aData[3] + '">';
				jqTds[4].innerHTML = '<input type="text" disabled="disabled" class="form-control input-small" id="floor_txt_'+ rec_id + '" value="' + aData[4] + '">';
				jqTds[5].innerHTML = '<input type="text" disabled="disabled" class="form-control input-small" id="floor_txt_'+ rec_id + '" value="' + aData[5] + '">';
				jqTds[6].innerHTML = '<input type="text" disabled="disabled" class="form-control input-small" id="floor_txt_'+ rec_id + '" value="' + aData[6] + '">';
				jqTds[7].innerHTML = '<input type="text" class="form-control input-small" id="priority_val_'+ rec_id + '" value="' + aData[7] + '" onc="saveChanges(' + rec_id + ')" maxlength="9">';
				
				/*$("#priority_val_" + rec_id).change(function () {
					alert("To proceed please click on Save Details");
				});*/
				jqTds[8].innerHTML = '';

			}
			function editLuggageRow(oTable, nRow, rec_id, newdata) {
				var aData = oTable.fnGetData(nRow);
				var jqTds = $('>td', nRow);
				jqTds[7].innerHTML = '<input type="text" class="form-control input-small" id="priority_val_'+ rec_id + '" value="' + aData[7] + '">';
				jqTds[8].innerHTML = '';
			}
			function editLuggageRow(oTable, nRow, rec_id, newdata) {
				
				var aData = oTable.fnGetData(nRow);
				var jqTds = $('>td', nRow);
				update_luggage_id += rec_id + ",";
				jqTds[0].innerHTML = '<input type="hidden" id="luggtickinvmstid_'+ rec_id+ '" class="form-control input-small" value="'	+ newdata + '" style="visibility:hidden">';
				jqTds[1].innerHTML = '<input type="text" class="form-control input-small" id="luggage_key_'+rec_id+ '" disabled="disabled" value="'+aData[1]+ '">';
				jqTds[2].innerHTML = '<input type="text"  disabled="disabled" class="form-control input-small" value="'+ aData[2] + '" >';	
				jqTds[3].innerHTML = '<input type="text" disabled="disabled" class="form-control input-small" id="floor_txt_'+ rec_id + '" value="' + aData[3] + '">';
				jqTds[4].innerHTML = '<input type="text" disabled="disabled" class="form-control input-small" id="floor_txt_'+ rec_id + '" value="' + aData[4] + '">';
				jqTds[5].innerHTML = '<input type="text" disabled="disabled" class="form-control input-small" id="floor_txt_'+ rec_id + '" value="' + aData[5] + '">';
				jqTds[6].innerHTML = '<input type="text" disabled="disabled" class="form-control input-small" id="floor_txt_'+ rec_id + '" value="' + aData[6] + '">';
				jqTds[7].innerHTML = '<input type="text" class="form-control input-small" id="luggage_priority_val_'+ rec_id + '" value="' + aData[7] + '" maxlength="9">';
				
				/*$("#luggage_priority_val_" + rec_id).change(function () {
					alert("To proceed please click on Save Details");

				});*/
				jqTds[8].innerHTML = '';

			}
			function editPassRow(oTable, nRow, rec_id, newdata) {
				
				var aData = oTable.fnGetData(nRow);
				var jqTds = $('>td', nRow);
				update_pass_id += rec_id + ",";
				
				jqTds[0].innerHTML = '<input type="hidden"  id="passtickinvmstid_'+ rec_id+'"  class="form-control input-small" value="'+newdata+ '" style="visibility:hidden;">';
				jqTds[1].innerHTML = '<input type="text"  disabled="disabled" class="form-control input-small" value="'+ aData[1] + '" >';
				jqTds[2].innerHTML = '<input type="text" class="form-control input-small" id="pass_denom_'+ rec_id+ '" disabled="disabled" value="'+ aData[2]+ '">';
				jqTds[3].innerHTML = '<input type="text" disabled="disabled" class="form-control input-small" id="pass_key_'+ rec_id + '" value="' + aData[3] + '">';
				jqTds[4].innerHTML = '<input type="text" disabled="disabled" class="form-control input-small" id="floor_txt_'+ rec_id + '" value="' + aData[4] + '">';
				jqTds[5].innerHTML = '<input type="text" disabled="disabled" class="form-control input-small" id="floor_txt_'+ rec_id + '" value="' + aData[5] + '">';
				jqTds[6].innerHTML = '<input type="text" disabled="disabled" class="form-control input-small" id="floor_txt_'+ rec_id + '" value="' + aData[6] + '">';
				jqTds[7].innerHTML = '<input type="text" disabled="disabled" class="form-control input-small" id="floor_txt_'+ rec_id + '" value="' + aData[7] + '">';
				jqTds[8].innerHTML = '<input type="text" disabled="disabled" class="form-control input-small" id="floor_txt_'+ rec_id + '" value="' + aData[8] + '">';
				jqTds[9].innerHTML = '<input type="text" class="form-control input-small" disabled="disabled" id="pass_val_'+ rec_id + '" value="' + aData[9] + '">';
				jqTds[10].innerHTML = '<input type="text" class="form-control input-small" id="pass_priority_val_'+ rec_id + '" value="' + aData[10] + '" maxlength="9">';
				
				/*$("#pass_priority_val_" + rec_id).change(function () {
					alert("To proceed please click on Save Details");
				});*/
				jqTds[11].innerHTML = '';

			}

			function saveRow(oTable, nRow) {
				var jqInputs = $('input', nRow);
				oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
				oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
				oTable.fnUpdate(jqInputs[2].value, nRow, 2, false);
				oTable.fnUpdate('<a class="edit" href="">Edit</a>', nRow, 3,false);
				oTable.fnUpdate('<a class="delete" href="">Delete</a>', nRow,4, false);
				oTable.fnDraw();
			}

			function cancelEditRow(oTable, nRow) {
				var jqInputs = $('input', nRow);
				oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
				oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
				oTable.fnUpdate(jqInputs[2].value, nRow, 2, false);
				oTable.fnUpdate('<a class="edit" href="">Edit</a>', nRow, 3,false);
				oTable.fnDraw();
			}

			
			
			var nEditing = null;

			$('#ticketAddNew').click(function(e) {
				e.preventDefault();

				var aiNew = oTable.fnAddData([ '', '', '', '', '' ]);
				var nRow = oTable.fnGetNodes(aiNew[0]);

				editRow(oTable, nRow, new_id, new_id);
				nEditing = nRow;
				new_id += 1;
			});

			$('#backbtn1').unbind().click(function(e) {
				e.preventDefault();
				document.forms[0].action = "ticketinv.action";
				document.forms[0].submit();
			});
			$('#ticketEditTable a.edittkt').live('click', function(e) {
				e.preventDefault();
				var nRow = $(this).parents('tr')[0];
				var rec_id = $(this).attr('value');
				//alert(rec_id);
				if (nEditing !== null && nEditing != nRow) {
					editRow(oTable, nRow, edit_id, rec_id);
					nEditing = nRow;
				} else if (nEditing == nRow && this.innerHTML == "Save") {
					saveRow(oTable, nEditing);
					nEditing = null;
				} else {
					editRow(oTable, nRow, edit_id, rec_id);
					nEditing = nRow;
				}
				edit_id += 1;
			});
			$('#luggageEditTable a.editluggage').live('click', function(e) {
				e.preventDefault();
				var nRow = $(this).parents('tr')[0];
				var rec_id = $(this).attr('value');
				if (nEditing !== null && nEditing != nRow) {
					editLuggageRow(oTable2, nRow, edit_id, rec_id);
					nEditing = nRow;
				} else if (nEditing == nRow && this.innerHTML == "Save") {
					saveRow(oTable2, nEditing);
					nEditing = null;
				} else {
					editLuggageRow(oTable2, nRow, edit_id, rec_id);
					nEditing = nRow;
				}
				edit_id += 1;
			});
			$('#passEditTable a.editpass').live('click', function(e) {
				e.preventDefault();
				var nRow = $(this).parents('tr')[0];
				var rec_id = $(this).attr('value');
				if (nEditing !== null && nEditing != nRow) {
					editPassRow(oTable1, nRow, edit_id, rec_id);
					nEditing = nRow;
				} else if (nEditing == nRow && this.innerHTML == "Save") {
					saveRow(oTable1, nEditing);
					nEditing = null;
				} else {
					editPassRow(oTable1, nRow, edit_id, rec_id);
					nEditing = nRow;
				}
				edit_id += 1;
			});

			$('#save_tkt').click(function(e) {
				e.preventDefault();
				var idArr = update_id.split(',');
				var idArrPass = update_pass_id.split(',');
				var idArrLuggage = update_luggage_id.split(',');
				var deletedID = deletedid.split(',');
				var denoname = $('#deno_key_' + idArr[i]).val();
				var priority = $('#priority_val_' + idArr[0]).val();
				for ( var i = 0; i <= idArr.length - 1; i++) {
					var denoname = 0;
					var denomkey = $('#deno_key_' + idArr[i]).val();
					if ((denomkey == undefined)	|| (idArr.length == 1)) {
						var priority = 0;
						var tickinvsid = 0;
					} else {
						var priority = $('#priority_val_' + idArr[i]).val();
						var tickinvsid = $(	'#tickinvmstid_' + idArr[i]).val();
					}
					$.ajax({
						type : "post",
						async : false,
						url : "TicketsEditAjax!editTicket?tickinvsid="
							+ tickinvsid
							+ "&priority="
							+ priority,
						success : function(result) {
							var s = "";
							if (result != "") {
								s = result.split("@");
								if (result.indexOf("Successfully") != -1) {
									isSuccess = true;
									var divId = document.getElementById("successmsgtick");
									divId.style.display = 'block';
									divId.style.visibility = 'visible';
								}
							}
							for(var i = 0; i <= idArrPass.length - 1; i++) {
								var denoname = 0;
								var passkey = $('#pass_key_'+ idArrPass[i]).val();
								var denoname = 0;
								var passinvstid = 0;
								var passpriority = 0;
								if ((passkey == undefined)|| (idArrPass.length == 1)) {
									passinvstid = 0;
									passpriority = 0;
								} else {
									passpriority = $('#pass_priority_val_'+ idArrPass[i]).val();
									passinvstid = $('#passtickinvmstid_'+ idArrPass[i]).val();
								}
								$.ajax({
									type : "post",
									async : false,
									url : "TicketsEditAjax!editPass?passinvstid="
											+ passinvstid
											+ "&passpriority="
											+ passpriority,
									success : function(result) {
										var s1 = "";
										if (result != "") { //
											s1 = result.split("@");
											if (result.indexOf("Successfully") != -1) {
												var divId = document.getElementById("successmsgpass");
												divId.style.display = 'block';
												divId.style.visibility = 'visible';
											}
										}
										for(var i = 0; i <= idArrLuggage.length - 1; i++) {
											var denoname = 0;
											var denoname = 0;
											var luggagekey = $('#luggage_key_'+ idArrLuggage[i]).val();
											var denoname = 0;
											var luggagepriority = 0;
											var luggageinvstid = 0;
											if ((luggagekey == undefined)|| (idArrLuggage.length == 1)) {
												luggagepriority = 0;
												luggageinvstid = 0;
											} else {
												luggagepriority = $('#luggage_priority_val_'+idArrLuggage[i]).val();
												luggageinvstid = $('#luggtickinvmstid_'	+ idArrLuggage[i]).val();
											}
								
										$.ajax({
											type : "post",
											async : false,
											url : "TicketsEditAjax!editLuggage?luggageinvstid="
													+ luggageinvstid
													+ "&luggagepriority="
													+ luggagepriority,
											success : function(result) {
												var s2 = "";
												if (result != "") {
													s2 = result.split("@");
													if (result.indexOf("Successfully") != -1) {
														isSuccess = true;
														var divId = document.getElementById("successmsglugg");
														divId.style.display = 'block';
														divId.style.visibility = 'visible';
													}
												}
												var succmsgedit = "";
												if (s != "") {
													succmsgedit += "@"+ s[0];
												} else {
													succmsgedit += "@"+ "-";
												}
												if (s1 != "") {
													succmsgedit += "@"+ s1[0];
												} else {
													succmsgedit += "@"+ "-";
												}
												if (s2 != "") {
													succmsgedit += "@"+ s2[0];
												} else {
													succmsgedit += "@"+ "-";
												}
												document.ticketeditform.succmsgedit.value = succmsgedit;
												document.forms["ticketeditform"].action = 'ticketinv.action';
												document.forms["ticketeditform"].submit();
											}
										});
									}
								}
							});
						}
					}
				});
			}
		});
		var oTable = $('#ticketEditTable').dataTable({
			"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
							],
			
			"iDisplayLength" : 5,
			"sAjaxSource" : "TicketingAjaxAction.action",
			"sPaginationType" : "bootstrap",
			"bProcessing" : true,
			"bServerSide" : true,
			"oLanguage" : {
				"sLengthMenu" : "_MENU_ records",
				"oPaginate" : {
					"sPrevious" : "Prev",
					"sNext" : "Next"
				}
			},
			 "bFilter" : false,
			 "bSelect" : false,
	         "bPaginate":false, 		         
	         "aoColumnDefs": [
	             { 'bSortable': false, 'aTargets': [0,1,2,3,4,5,6,7,8] },
	             { "bSearchable": false, "aTargets": [ 0 ] }
	          ],
	         "oLanguage" : {
					"sZeroRecords" : "No Data",
					"sEmptyTable" : ""
				},
				
	         "bLengthChange" : false, 
			 "sDom" : '<"top">rt<"bottom"flp><"clear">'

		}).wrap("<div style='position:relative;overflow:auto;height:300px;'/>");
		
		var oTable1 = $('#passEditTable').dataTable({
			"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
							],
			
			"iDisplayLength" : 5,
			"sAjaxSource" : "PassEditAjaxAction.action",
			"sPaginationType" : "bootstrap",
			"bProcessing" : true,
			"bServerSide" : true,
			"oLanguage" : {
				"sLengthMenu" : "_MENU_ records",
				"oPaginate" : {
					"sPrevious" : "Prev",
					"sNext" : "Next"
				}
			},
			 "bFilter" : false,
			 "bSelect" : false,
	         "bPaginate":false, 		         
	         "aoColumnDefs": [
	             { 'bSortable': false, 'aTargets': [0,1,2,3,4,5,6,7,8,9,10,11] },
	             { "bSearchable": false, "aTargets": [ 0 ] }
	          ],
	         "oLanguage" : {
					"sZeroRecords" : "No Data",
					"sEmptyTable" : ""
				},
				
	         "bLengthChange" : false, 
			 "sDom" : '<"top">rt<"bottom"flp><"clear">'		
				 
		}).wrap("<div style='position:relative;overflow:auto;height:300px;'/>");
		
		var oTable2 = $('#luggageEditTable').dataTable({
			"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
					],
			"iDisplayLength" : 5,
			"sAjaxSource" : "LuggageEditAjaxAction.action",
			"sPaginationType" : "bootstrap",
			"bProcessing" : true,
			"bServerSide" : true,
			"oLanguage" : {
				"sLengthMenu" : "_MENU_ records",
				"oPaginate" : {
					"sPrevious" : "Prev",
					"sNext" : "Next"
				}
			},
			 "bFilter" : false,
			 "bSelect" : false,
	         "bPaginate":false, 		          
	         "aoColumnDefs": [
	             { 'bSortable': false, 'aTargets': [0,1,2,3,4,5,6,7,8] },
	             { "bSearchable": false, "aTargets": [ 0 ] }
	          ],
	         "oLanguage" : { 
					"sZeroRecords" : "No Data",
					"sEmptyTable" : ""
				},
				
	         "bLengthChange" : false,  
			 "sDom" : '<"top">rt<"bottom"flp><"clear">'		
				 
		}).wrap("<div style='position:relative;overflow:auto;height:300px;'/>");
	}
};
}();