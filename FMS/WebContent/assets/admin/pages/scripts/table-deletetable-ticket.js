var TableDeleteTicket = function() {

	return {

		// main function to initiate the module
		init : function() {
			var update_id = "";
			var new_id=1;
			var insert_id="";
			var deletedid="";
			var deletedluggageid="";
			var deletedpassid="";
			function restoreRow(oTable, nRow) {
				var aData = oTable.fnGetData(nRow);
				var jqTds = $('>td', nRow);

				for ( var i = 0, iLen = jqTds.length; i < iLen; i++) {
					//alert(aData[i]);
					oTable.fnUpdate(aData[i], nRow, i, false);
				}

				oTable.fnDraw();
			}

			function editRow(oTable, nRow, rec_id,newdata) {
				var aData = oTable.fnGetData(nRow);
				var jqTds = $('>td', nRow);

				jqTds[0].innerHTML = '<input type="text"  disabled="disabled" class="form-control input-small" value="' + aData[0] + '" >';
				jqTds[1].innerHTML = '<input type="text" class="form-control input-small" id="deno_key_'
						+ rec_id + '" value="' + aData[1] + '">';
				jqTds[2].innerHTML = '<input type="text" class="form-control input-small" id="floor_txt_'
					+ rec_id + '" value="' + aData[2] + '">';
				jqTds[3].innerHTML = '<input type="text" class="form-control input-small" id="floor_txt_'
					+ rec_id + '" value="' + aData[3] + '">';
				jqTds[4].innerHTML = '<input type="text" class="form-control input-small" id="floor_txt_'
					+ rec_id + '" value="' + aData[4] + '">';
				jqTds[5].innerHTML = '<input type="text" class="form-control input-small" id="floor_txt_'
					+ rec_id + '" value="' + aData[5] + '">';
				jqTds[6].innerHTML = '<input type="text" class="form-control input-small" id="priority_val_'
					+ rec_id + '" value="' + aData[6] + '">';
				/*jqTds[7].innerHTML = '<input type="text" class="form-control input-small" id="floor_txt_'
					+ rec_id + '" value="' + aData[7] + '">';
				jqTds[8].innerHTML = '<input type="text" disabled="disabled" class="form-control input-small" id="floor_txt_'
					+ rec_id + '" value="' + aData[8] + '">';*/
				//jqTds[7].innerHTML = '<input type="text"  class="form-control input-small" id="priority_val'
					//+ rec_id + '" value="' + aData[7] + '">';
				jqTds[7].innerHTML = '';
				
				jqTds[8].innerHTML = '<a class="cancel" href="">Cancel</a>';
				
				update_id += rec_id + ",";
			
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
			$('#backbtn1').unbind().click(function(e) {
				e.preventDefault();
				// alert('HI');
				document.forms[0].action = "ticketinv.action";
				document.forms[0].submit();
			});
			function cancelEditRow(oTable, nRow) {
				var jqInputs = $('input', nRow);
				oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
				oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
				oTable.fnUpdate(jqInputs[2].value, nRow, 2, false);
				oTable.fnUpdate('<a class="edit" href="">Edit</a>', nRow, 3,
						false);
				oTable.fnDraw();
			}

			 var oTable = $('#ticketDeleteTable').dataTable({
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 10, 20, 30, "All" ] // change
																				// per
																				// page
																				// values
																				// here
					],
					// set the initial value
					"iDisplayLength" : 5,
					"sAjaxSource" : "TicketingAjaxDeleteAction.action",
					"sPaginationType" : "bootstrap",
					 "bServerSide" : true,
					 "bProcessing" : true,
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
							"sZeroRecords" : "",  
							"sEmptyTable" : ""
						},
						
			         "bLengthChange" : false, 
					 "sDom" : '<"top">rt<"bottom"flp><"clear">'
				}).wrap("<div style='position:relative;overflow:auto;height:300px;'/>");
			
			 var oTable1 = $('#passDeleteTable').dataTable({
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 10, 20, 30, "All" ] // change
																				// per
																				// page
																				// values
																				// here
					],
					// set the initial value
					"iDisplayLength" : 5,
					"sAjaxSource" : "PassAjaxDeleteAction.action",
					"sPaginationType" : "bootstrap",
					 "bServerSide" : true,
					 "bProcessing" : true,
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
							"sZeroRecords" : "",  
							"sEmptyTable" : ""
						},
						
			         "bLengthChange" : false, 
					 "sDom" : '<"top">rt<"bottom"flp><"clear">'
				}).wrap("<div style='position:relative;overflow:auto;height:300px;'/>");
			
			 var oTable2 = $('#luggageDeleteTable').dataTable({
					"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 10, 20, 30, "All" ] // change
																				// per
																				// page
																				// values
																				// here
					],
					// set the initial value sssssssss
					"iDisplayLength" : 5,
					"sAjaxSource" : "LuggageAjaxDeleteAction.action",
					"sPaginationType" : "bootstrap",
					 "bServerSide" : true,
					 "bProcessing" : true,
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
							"sZeroRecords" : "",  
							"sEmptyTable" : ""
						},
						
			         "bLengthChange" : false, 
					 "sDom" : '<"top">rt<"bottom"flp><"clear">'
				}).wrap("<div style='position:relative;overflow:auto;height:300px;'/>");

		
			var nEditing = null;

			$('#ticketAddNew')
					.click(
							function(e) {
								e.preventDefault();
								
								var aiNew = oTable.fnAddData([ '', '', '', '','' ]);
								var nRow = oTable.fnGetNodes(aiNew[0]);
								
								editRow(oTable, nRow,new_id,new_id);
								nEditing = nRow;
								new_id+=1;
							});

			$('#ticketDeleteTable a.deletetkt').unbind()
					.live(
							'click',
							function(e) {
								e.preventDefault();
 
								if (confirm("Are you sure,you want to delete the record ?") == false) {
									return;
								}
								var delete_id = $(this).attr('value');
								$.ajax({
							           type: "post",
							           url : "checkDeleteTicketinv?tickinvid='"
									       + delete_id,
									       success: function(count) {
									    	   if(count!=0)
									    		   {
									    	  alert("Ticket Type  in use can not Delete it");	
									    	  return false;
									    		   }
									       }
									       });

								
						//alert("hell" +
								//		"o iam deletying"+delete_id);
								var nRow = $(this).parents('tr')[0];
								oTable.fnDeleteRow(nRow);
								alert("To proceed please click on Save Details");
								deletedid += delete_id+",";
								//alert(deletedid);
								restoreRow(oTable, nEditing);
								
							});
			$('#luggageDeleteTable a.deleteluggage').unbind().live(
					'click',
					function(e) {
						e.preventDefault();

						if (confirm("Are you sure,you want to delete the record ?") == false) {
							return;
						}
						
						var delete_luggage_id = $(this).attr('value');
						$.ajax({
					           type: "post",
					           url : "checkDeleteLuggageinv?luggagetickinvid='"
							       + delete_luggage_id,
							       success: function(count) {
							    	   if(count!=0)
							    		   {
							    	  alert("Luggage Type  in use can not Delete it");		
							    	  return false;
							    		   }
							       }
							       });
						/*alert(delete_luggage_id);
						alert(deletedluggageid);
*/						deletedluggageid += delete_luggage_id+",";
						//alert(deletedluggageid);
						var nRow = $(this).parents('tr')[0];
						oTable2.fnDeleteRow(nRow);
						//alert("Deleted! Do not forget to do some ajax to sync with backend :)");
						alert("To proceed please click on Save Details");
						restoreRow(oTable2, nEditing);
						
					}); 
			$('#passDeleteTable a.deletepass').unbind()
			.live(
					'click',
					function(e) {
						e.preventDefault();

						if (confirm("Are you sure,you want to delete the record ?") == false) {
							return;
						}
						var delete_pass_id = $(this).attr('value'); 
						$.ajax({
					           type: "post",
					           url : "checkDeletePassinv?passtickinvid='"
							       + delete_pass_id,
							       success: function(count) {
							    	   if(count!=0)
							    		   {
							    	  alert("Pass Type  in use can not Delete it");	
							    	  return false; 
							    	  }
							       }
							       });


						//alert(delete_pass_id);
						deletedpassid += delete_pass_id+",";
						//alert(deletedpassid);

						var nRow = $(this).parents('tr')[0];
						oTable1.fnDeleteRow(nRow);
						//alert("Deleted! Do not forget to do some ajax to sync with backend :)");
						alert("To proceed please click on Save Details");
												restoreRow(oTable1, nEditing);
						
					});

			$('#ticketDeleteTable a.cancel').live('click', function(e) {
				e.preventDefault();
				
				
	                	var nRow1 = $(this).parents('tr')[0];
	                    restoreRow(oTable, nRow1);
	                   // nEditing = null;
	                				//nEditing = null;

			}); 

			
			$('#save_deltkt')
					.click(
							function(e) {
								e.preventDefault();
								//alert(deletedid);
			 				  /*  if(deletedid!="")
								{*/
									var deletedID=deletedid.split(',');
									//alert("ticket type length.."+deletedID.length);
								//if(deletedID.length>=2)
								//{
							
								//Code For Delete Record!!
									
								for ( var i = 0; i <=deletedID.length - 1; i++) {
									var tickinvstid=0;
									//alert("inside for loop ticket type length.."+deletedID.length - 1);
									if(i==deletedID.length - 1)
									{
										tickinvstid=0;
										 
									}
									else
									{
										tickinvstid=deletedID[i];
									}
									//alert("ticket deleted id is.."+tickinvstid+"i value is"+i);
									//alert();
								$
									.ajax({
										type : "post",
										async:false,
	 									url : "TicketAjaxDelete!deleteTicket?tickinvmstid="
											+ tickinvstid
											,
										success : function(
												result) { 
											//alert(result);
											var s="";
											if (result != "") {
												//alert("inside ifg"+succy);
												 s = result
													.split("@");
												
												
												if (result
														.indexOf("Successfully") != -1) {
													isSuccess = true;

													//alert(s2[0]);
													/*var divId = document
															.getElementById("successmsgtick");
							 						divId.style.display = 'block';
													divId.style.visibility = 'visible';
													document
															.getElementById('succticket').innerHTML =result ;*/
												}
											}
											//if(deletedpassid!="")
											//{
												var deletepassID=deletedpassid.split(',');
												//alert(deletepassID.length);
											//if(deletepassID.length>=2)
											//{
												
											//Code For Delete Record!!
											for ( var i = 0; i <= deletepassID.length - 1; i++) {
												var passinvstid=0;
												//alert("inside for loop ticket type length.."+deletedID.length - 1);
												if(i==deletepassID.length - 1)
												{
													passinvstid=0;
													
												}
												else
												{
													passinvstid=deletepassID[i];
												}
												//alert("pass deleted id is.."+passinvstid+"i value is"+i);
												/*if(deletepassID.length==1)
												{
													deletepassID[i]=0;
												}*/
												
											//	alert("delted ticketID..."+deletepassID[i]);	
												
												// AjaxCall To Update Values..
				 								$
												.ajax({
													type : "post",
													async:false,
													url : "TicketAjaxDelete!deletePass?tickinvpassid="
														+ passinvstid
														,
													success : function(
															result) { 
														var s1="";
														//alert(result);
														if (result != "") {
															s1= result
															.split("@");
															//alert("inside ifg"+succy);
															
															
															
															if (result
																	.indexOf("Successfully") != -1) {
																isSuccess = true;

																//alert(s2[0]);
																/*var divId = document
																.getElementById("successmsgpass");
								 						divId.style.display = 'block';
														divId.style.visibility = 'visible';
														document
																.getElementById('succpass').innerHTML =result ;*/
															}
														}
														//if(deletedluggageid!="")
														//{
					 										var deleteluggageID=deletedluggageid.split(',');						
														//if(deleteluggageID.length>=2)
															//{
															
															//Code For Delete Record!!
															for ( var i = 0; i <= deleteluggageID.length - 1; i++) {
															//	alert("delted ticketID..."+deleteluggageID[i]);	
								
																// AjaxCall To Update Values..
																var lugginvstid=0;
																//alert("inside for loop ticket type length.."+deletedID.length - 1);
																if(i==deleteluggageID.length - 1)
																{
																	lugginvstid=0;
																	
																}
																else
																{
																	lugginvstid=deleteluggageID[i];
																}
															//	alert("luggage deleted id is.."+deleteluggageID[i]+"i value is"+i);
																
																/*if(deleteluggageID.length==1)
																{
																	deleteluggageID[i]=0;
																}*/
																$
																.ajax({
																	type : "post",
																	async:false,
																	url : "TicketAjaxDelete!deleteLuggage?tickinvluggageid="
																		+ lugginvstid
																		,
																	success : function(
																			result) { 
																	//	alert(result);
																		var s2="";
																		if (result != "") {
																			//alert("inside ifg"+succy);
																			
																			s2= result
																			.split("@");
																			//alert("luggage messase is.."+s2[0]);
																			if (result
																					.indexOf("Successfully") != -1) {
																				isSuccess = true;

																				
																				/*var divId = document
																				.getElementById("successmsglugg");
												 						divId.style.display = 'block';
																		divId.style.visibility = 'visible';
																		document
																				.getElementById('succlugg').innerHTML =result ;*/
																			}
																		}
																		var succmsgdelete="";
																		if(s!="")
																		{
																			succmsgdelete+="@"+s[0];
																		}
																		 else
																		    {
																			 succmsgdelete+="@"+"-";
																		    }
																		if(s1!="")
																		{
																			succmsgdelete+="@"+s1[0];
																			//+"@"+s1[0]+"@"+s2[0];
																		}
																		 else
																		    {
																			 succmsgdelete+="@"+"-";
	 																	    }
																	    if(s2!="")
																	    {
																	    	succmsgdelete+="@"+s2[0];	
													  				    }
 																	    else
																	    {
 																	    	succmsgdelete+="@"+"-";
													 				    }
																	//  alert(succmsgedit);
																		document.forms[0].action = 'ticketinv.action?succmsgdelete='+succmsgdelete;
																		document.forms[0].submit();
																	}
																});


																							
															}
															
															//
															
															}									
															
															//}

												//	}
												});


																				
											}
											
											//
											
											
											
											}
										//	}

										//}
									});



									//
								
								}
																//
								
								
								
					 		//	}
							//	}
							
																								
								$('#ticketDeleteTable').dataTable()
										.fnClearTable();
								$('#ticketDeleteTable').dataTable().fnDestroy();

								 var oTable = $('#ticketDeleteTable').dataTable({
										"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
																									// per
																									// page
																									// values
																									// here
										],
										// set the initial value
										"iDisplayLength" : 5,
										"sAjaxSource" : "TicketingAjaxDeleteAction.action",
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
										"aoColumnDefs" : [ {
											'bSortable' : false,
											'aTargets' : [ 0 ]
										} ]
									});

								jQuery('#ticketDeleteTable_wrapper .dataTables_filter input')
										.addClass("form-control input-medium input-inline"); // modify
																								// table
																								// search
																								// input
								jQuery('#ticketDeleteTable_wrapper .dataTables_length select')
										.addClass("form-control input-small"); // modify table per
																				// page dropdown
								jQuery('#ticketDeleteTable_wrapper .dataTables_length select')
										.select2({
											showSearchInput : true
										// hide search box with special css class
										}); // initialize select2 dropdown
								$('#passDeleteTable').dataTable()
								.fnClearTable();
						$('#passDeleteTable').dataTable().fnDestroy();

								 var oTable1 = $('#passDeleteTable').dataTable({
										"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
																									// per
																									// page
																									// values
																									// here
										],
										// set the initial value
										"iDisplayLength" : 5,
										"sAjaxSource" : "PassAjaxDeleteAction.action",
										"sPaginationType" : "bootstrap",
										 "bServerSide" : true,
										 "bProcessing" : true,
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
									jQuery('#passDeleteTable_wrapper_wrapper .dataTables_filter input')
									.addClass("form-control input-medium input-inline"); // modify
																							// table
																							// search
																							// input
							jQuery('#passDeleteTable_wrapper .dataTables_length select')
									.addClass("form-control input-small"); // modify table per
																			// page dropdown
							jQuery('#passDeleteTable_wrapper .dataTables_length select')
									.select2({
										showSearchInput : true
									// hide search box with special css class
									}); // initialize select2 dropdown
							$('#luggageDeleteTable').dataTable()
							.fnClearTable();
					$('#luggageDeleteTable').dataTable().fnDestroy();
								 var oTable2 = $('#luggageDeleteTable').dataTable({
										"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
																									// per
																									// page
																									// values
																									// here
										],
										// set the initial value
										"iDisplayLength" : 5,
										"sAjaxSource" : "LuggageAjaxDeleteAction.action",
										"sPaginationType" : "bootstrap",
										 "bServerSide" : true,
										 "bProcessing" : true,
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

								jQuery('#luggageDeleteTable_wrapper .dataTables_filter input')
										.addClass("form-control input-medium input-inline"); // modify
																								// table
																								// search
																								// input
								jQuery('#luggageDeleteTable_wrapper .dataTables_length select')
										.addClass("form-control input-small"); // modify table per
																				// page dropdown
								jQuery('#luggageDeleteTable_wrapper .dataTables_length select')
										.select2({
											showSearchInput : true
										// hide search box with special css class
										}); // initialize select2 dropdown


								/*
								 * if (nEditing) { if (confirm("Previous row not
								 * saved. Do you want to save it ?")) {
								 * saveRow(oTable, nEditing); // save } else {
								 * oTable.fnDeleteRow(nEditing); // cancel } }
								 * 
								 * var aiNew = oTable.fnAddData(['', '', '', '',
								 * '', '']); var nRow =
								 * oTable.fnGetNodes(aiNew[0]); editRow(oTable,
								 * nRow); nEditing = nRow;
								 */
							});

		}

	};

}();