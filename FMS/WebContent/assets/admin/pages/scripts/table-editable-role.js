
var TableEditablerole = function() {

	return {

		// main function to initiate the module
		init : function() {
			// Strat Code After This

			var new_id = 0;
			var newrow;
			var nEditing = null;
			var insert_id = "";

			var update_id = "";

			var edit_id = 0;

			var deletedid = "";

			var flag = "";

			var id = document.getElementById("groupid").value;
			var roloTable = $('#role_editable').dataTable({
				"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
				// per
				// page
				// values
				// here
				],
				// set the initial value
				"iDisplayLength" : 5,
				"sAjaxSource" : "getRoleListDetails.action?group_id=" + id,
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
			jQuery('#role_editable_wrapper .dataTables_filter input').addClass(
					"form-control input-medium input-inline"); // modify
			// table
			// search
			// input
			jQuery('#role_editable_wrapper .dataTables_length select')
					.addClass("form-control input-small"); // modify table per
			// page dropdown
			jQuery('#role_editable_wrapper .dataTables_length select').select2(
					{
						showSearchInput : true
					// hide search box with special css class
					}); // initialize select2 dropdown

			$('#cancelrolegroup').click(function(e) {

				document.forms[0].action = "viewGroup.action";
				document.forms[0].submit();
			});

			$('#roleNew').click(function(e) {

				if (flag != 'true') {
					
					e.preventDefault();
					var aiNew = roloTable.fnAddData([ '', '', '', '' ]);
					var nRow = roloTable.fnGetNodes(aiNew[0]);
					flag = "true";
					savRow(roloTable, nRow, new_id, new_id);
					nEditing = nRow;
					newrow = aiNew;
					new_id += 1;
				} else {
					// alert("Only enter one role one time");
					return false;
				}

			});

			function savRow(roloTable, nRow, rec_id, newdata) {
				var aData = roloTable.fnGetData(nRow);
				var jqTds = $('>td', nRow);
				//alert("Hello"+id);
				// jqTds[0].innerHTML = '<input type="text" class="form-control
				// input-small" >';
				$.ajax({
							type : "POST",
							async : false,
							url : "GetRolelist.action?groupid=" + id,
							success : function(response) {
								var arr = response.split(',');
								var i = 0;

								var a = '<select id="roleid'+ rec_id + '" class="select2_category form-control" style="width:250px" ><option value="0">select</option>'
								for (i = 0; i < arr.length - 1; i++) {
									var splitresult = arr[i].split('@');
									a += '<option value="' + splitresult[0] + '">' + splitresult[1] + '</option>'
								}
								+ '</select>';
								jqTds[1].innerHTML = a;
								jqTds[2].innerHTML = '<a class="save"  value="' + rec_id + '" href="" >Save</a>';

								FormSamples.init();
							}
						});
				insert_id += newdata + ",";

			}

		$('#role_editable a.save').unbind().die().live('click',function(e) {
								flag = "";
								e.preventDefault();
								var nRow = $(this).parents('tr')[0];
								var insertid = insert_id.split(',');
								var rec_id = $(this).attr('value');
								var roleid = $('#roleid' + insertid[rec_id]).val();
								// alert("roleid---"+roleid);
								if (roleid == 0) {
									alert("Please Select Role Name");
									flag='true';
									return false;
								} else {

									$.ajax({
												type : 'POST',
												async : false,
												url : "addGroupRole.action?roleid="
														+ roleid
														+ "&groupid="
														+ id,

												success : function(data) {
													// alert(data);
													if (data != "") {

														if (data.indexOf("Successfully") != -1) {
															var divId = document.getElementById("successmsg");
															divId.style.display = 'block';
															divId.style.visibility = 'visible';
															document.getElementById('successgrouprole').innerHTML = data;
														} else {
															var divId = document.getElementById("errormsg");
															divId.style.display = 'block';
															divId.style.visibility = 'visible';
															document.getElementById('errorgrouprole').innerHTML = data;
														}
														roloTable = $('#role_editable').dataTable(
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
																			"sAjaxSource" : "getRoleListDetails.action?group_id="
																					+ id,
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

														jQuery('#role_editable_wrapper .dataTables_filter input').addClass("form-control input-medium input-inline"); // modify
														// table
														// search
														// input
														jQuery('#role_editable_wrapper .dataTables_length select').addClass("form-control input-small"); // modify
																										// table
																										// per
														// page dropdown
														jQuery('#role_editable_wrapper .dataTables_length select').select2(
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

								}// else

							});

			$('#role_editable a.delete').unbind().die().live('click',function(e) {
								e.preventDefault();

								/*
								 * if (confirm("Are you sure to delete this row
								 * ?") == false) { return; } var delete_id =
								 * $(this).attr('value'); var nRow =
								 * $(this).parents('tr')[0];
								 * roloTable.fnDeleteRow(nRow); $.ajax({
								 * 
								 * url : "getDeleteRole.action?grouproleid="+
								 * delete_id, context : document.body
								 * }).done(function() {
								 * $(this).addClass("done");
								 * 
								 * }); deletedid += delete_id + ",";
								 */

								var delete_id = $(this).attr('value');
								// alert("delete_id----"+delete_id);
								bootbox.confirm("Are you Sure, you want to delete this record?",

												function(result) {
													if (result == true) {
														$.ajax({
																	type : 'POST',
																	async : false,
																	url : "getDeleteRole?grouproleid="
																			+ delete_id,
																	success : function(
																			data) {
																		// alert(data);
																		if (data != "") {
																			flag='false';
																			var divId = document.getElementById("successmsg");
																			divId.style.display = 'block';
																			divId.style.visibility = 'visible';
																			document.getElementById('successgrouprole').innerHTML = data;

																		} else {

																		}
																	},
																	error : function(
																			e) {
																		alert(e.message);
																	}
																});

														deletedid += delete_id
																+ ",";
														// restoreRow(pageoTable,
														// nEditing);

														roloTable = $('#role_editable').dataTable(
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
																			"sAjaxSource" : "getRoleListDetails.action?group_id="
																					+ id,
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
																		  'bSortable' : false,'aTargets' : [ 0 ] 
																		  } ]
																		 
																		});
														jQuery('#role_editable_wrapper .dataTables_filter input').addClass("form-control input-medium input-inline"); // modify
														// table
														// search
														// input
														jQuery('#role_editable_wrapper .dataTables_length select').addClass("form-control input-small"); // modify
																										// table
																										// per
														// page dropdown
														jQuery('#role_editable_wrapper .dataTables_length select').select2(
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
														return;
													}
												});

							});

			// });

			// End Code Before This<

		}

	};

}();
