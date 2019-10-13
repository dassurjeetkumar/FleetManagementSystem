var TableStockEntryTicket = function() {

	return {
		init : function() {
		var update_id = "";
		var new_id = 0;
		var edit_id = 0;
		var insert_id = "";
		var insert_id_pass = "";
		var insert_id_luggage = "";
		var insert_id_tsheet="";
		var deletedid = "";
		var succy = "";
		var newrow;
		function restoreRow(oTable, nRow) {
			var aData = oTable.fnGetData(nRow);
			var jqTds = $('>td', nRow);
			for ( var i = 0, iLen = jqTds.length; i < iLen; i++) {
				oTable.fnUpdate(aData[i], nRow, i, false);
			}
			oTable.fnDraw();
		}
		function savRow(oTable, nRow, rec_id, newdata) {
			var aData = oTable.fnGetData(nRow);
			var jqTds = $('>td', nRow);
			$.ajax({
				type : "POST",
				url : "GetDenomlist.action",
				success : function(response) {
					var arr = response.split(',');
					var i = 0;
					var a = '<select class="select2_category form-control" id="denomi_no_'+rec_id+'" style="width:140px" onchange="clearAllDataOfRow('+rec_id+')"><option value="0">Select</option>';
					for (i = 0; i < arr.length - 1; i++) {
						var splitresult = arr[i].split('@');
						a += '<option value="' + splitresult[0]+ '">' + splitresult[1]+ '</option>';
					}
					a += '</select>';
					jqTds[0].innerHTML = a;
				}
			});
			jqTds[1].innerHTML = '<input type="hidden" id="ticket_type_id_'+newdata+'" value="N" style="visibility:hidden;width:0px"><input type="text" class="form-control input-small" id="denom_key_'+rec_id+'" value="'+aData[1]+'" onblur="validateTicketKey('+rec_id+')" maxlength="8" style="width:300px;">';
			jqTds[2].innerHTML = '<input type="text" width="100px" class="form-control " id="start_no_'+rec_id+'" value="'+aData[2]+'" onkeyup="isInteger(this)" onblur="validateStartTicketno('+rec_id+','+newdata+')"  maxlength="8" >';
			/*if(newdata>=1){
				var no=newdata-1;
				$("#start_no_"+no).attr('readonly', 'readonly');
				$("#end_no_"+no).attr('readonly', 'readonly');
				$("#no_of_books_"+no).attr('readonly', 'readonly');
				$("#value_"+no).attr('readonly', 'readonly');
				$("#priority_"+no).attr('readonly', 'readonly');
			}*/
			jqTds[3].innerHTML = '<input type="text"  width="100px" class="form-control " id="end_no_'+ rec_id+ '" value="'+ aData[3]+'" onblur="validateEndTicketNo('+rec_id+','+newdata+')"  maxlength="8">';
			jqTds[4].innerHTML = '<input type="text" class="form-control " id="no_of_books_'+ rec_id + '" value="' + aData[4] + '"  onblur="calculateEndNo(this,'+rec_id+','+ newdata +',1)" maxlenght="6">';/**/
			jqTds[5].innerHTML = '<input type="text" class="form-control " id="value_'+ rec_id + '" value="' + aData[5] + '" readonly="readonly">';
			jqTds[6].innerHTML = '<input type="text" class="form-control " id="priority_'+rec_id + '" priority="' + aData[6] + '">';
			jqTds[7].innerHTML = '<a class="deletetktfgg" href="">Delete</a>';
			
			$("#denom_key_" + rec_id).bind('keyup',function(e) {
				var inputVal = $(this).val();
				if (/[^A-Za-z\d]/.test(inputVal)) {
					alert("Please enter only letter and numeric characters");
					document.getElementById('no_of_books_' + rec_id).focus();
					setTimeout(function() {
						document.getElementById('denom_key_' + rec_id).focus();
					}, 200);
				return (false);
				}
				if (e.which >= 97 && e.which <= 122) {
					var newKey = e.which - 32;
					// I have tried setting those
					e.keyCode = newKey;
					e.charCode = newKey;
				}
				$("#denom_key_" + rec_id).val(($("#denom_key_" + rec_id).val()).toUpperCase());
			});
			insert_id += newdata + ",";
		}

		function savPassRow(oTable1, nRow, rec_id, newdata) {
			var aData = oTable1.fnGetData(nRow);
			var jqTds = $('>td', nRow);
			jqTds[0].innerHTML = '<select id="ticket_type_id_'+rec_id+'" class="form-control" style="width:150px" '+
						+' onblur="validatePassTypeList('+rec_id+')" onchange="clearAllPassDataRow('+rec_id+')">'
						+ '<option value="0">Select</option>'
						+ '<option value="2">Pass - Daily</option>'
						+ '<option value="3">Pass - Monthly</option>'
						+ '<option value="6">Daily Pass Id</option>'
						+ '<option value="7">Monthly Pass Id</option>'
						+ '</select>';
			var a = '<select class="form-control" id="denom_no_'+rec_id+'" style="width:100px"><option value="0">Select</option></select>';
			jqTds[1].innerHTML = a;
			
			$("#ticket_type_id_" + rec_id).change(function() {
				var tickettype = $('#ticket_type_id_' + rec_id).val();
				
				$.ajax({
					type : "POST",
					url : "GetPasslist.action?tickettype="+ tickettype,
					success : function(response) {
						var arr = response.split(',');
						var i = 0;
						var a = '<select class="form-control" id="denom_no_'+rec_id+'" style="width:100px" onblur="validatePassTypeDenomno('+rec_id+')"><option value="0">Select</option>'
						for (i = 0; i < arr.length - 1; i++) {
							var splitresult = arr[i].split('@');
							a += '<option value="'+ splitresult[0]+ '">'+ splitresult[1]+ '</option>'
						}
						a += '</select>'
						jqTds[1].innerHTML = a;
					}
				});
				if(tickettype==7 || tickettype==6){
				}else{
				$.ajax({
					type : "POST",
					url : "GetPassDayOrMonth.action?tickettype="+ tickettype,
					success : function(response) {
						var daymontharr = response.split(',');										
						var i = 0;
						var a1 = '<select class="form-control" id="pass_day_'+ rec_id+ '" style="width:150px" onblur="validatePassDays('+rec_id+')"><option value="0">Select</option>'
						for (i = 0; i < daymontharr.length - 1; i++) {
							var splitresult1 = daymontharr[i].split('@');
							a1 += '<option value="'	+ splitresult1[0]+ '">'	+ splitresult1[1]+ '</option>'
						}
						a1 += '</select>'
						jqTds[3].innerHTML = a1;
					}
				});
				
				}
			});
			
			jqTds[2].innerHTML = '<input type="text" class="form-control " id="denom_key_'+ rec_id+ '" value="'+ aData[2]+ '" onblur="validatePassKey('+ rec_id+ ')"  maxlength="8" style="width:100px">';
			jqTds[3].innerHTML = '<select id="pass_day_' + rec_id+ '" class="form-control" style="width:150px"><option value="0">Select</option></Select>';
			jqTds[4].innerHTML = '<input type="text" class="form-control " id="start_no_'+ rec_id+ '" value="'+ aData[4] +'" onkeyup="isInteger(this)" onblur="validatePassStartno('+ rec_id+ ','+ newdata + ')"  maxlength="8" style="width:100px">';
			
			/*if(newdata>=1){
				var no=newdata-1;
				$("#start_no_"+no).attr('readonly', 'readonly');
				$("#end_no_"+no).attr('readonly', 'readonly');
				$("#no_of_passes_"+no).attr('readonly', 'readonly');
				$("#no_of_books_"+no).attr('readonly', 'readonly');
				$("#total_value_"+no).attr('readonly', 'readonly');
				$("#priority_"+no).attr('readonly', 'readonly');
			}*/
				
			jqTds[5].innerHTML = '<input type="text" class="form-control " id="end_no_'+ rec_id	+ '" value="'+ aData[5]+ '" onblur="validatePassEndno('	+ rec_id+ ','+ newdata + ')"  maxlength="8" style="width:100px">';			
			jqTds[6].innerHTML = '<input type="text" class="form-control " id="no_of_passes_'+ rec_id + '" value="' + aData[6] + '" onblur="calculateEndNo(this,'+rec_id+','	+ newdata +',3)" style="width:100px" maxlength="8">';
			jqTds[7].innerHTML = '<input type="text" class="form-control " id="no_of_books_'+ rec_id + '" value="' + aData[7] + '" onblur="calculateEndNo(this,'+rec_id+ ','+ newdata +',2)" style="width:100px" maxlength="7">';
			jqTds[8].innerHTML = '<input type="text" class="form-control " id="total_value_'+ rec_id + '" value="' + aData[8] + '"style="width:200px" readonly>';
			jqTds[9].innerHTML = '<input type="text" class="form-control " id="priority_'+ rec_id + '" value="' + aData[9] + '">';
			jqTds[10].innerHTML = '<a class="deletetpassbutton" href="">Delete</a>';
			$("#denom_key_" + rec_id).bind('keyup',function(e) {
				var inputVal = $(this).val();
				if (/[^A-Za-z\d]/.test(inputVal)) {
					alert("Please enter only letter and numeric characters");
					document.getElementById('no_of_books_' + rec_id).focus();
					setTimeout(function() {
						document.getElementById('denom_key_' + rec_id).focus();
					}, 100);
					return (false);
				}
				if (e.which >= 97 && e.which <= 122) {
					var newKey = e.which - 32;
					e.keyCode = newKey;
					e.charCode = newKey;
				}
				$("#denom_key_" + rec_id).val(($("#denom_key_" + rec_id).val()).toUpperCase());
			});
				insert_id_pass += newdata + ",";

		}
		function savLuggageRow(oTable2, nRow, rec_id, newdata) {
			var aData = oTable2.fnGetData(nRow);
			var jqTds = $('>td', nRow);

			jqTds[0].innerHTML ='<input type="hidden" id="ticket_type_id_'+ newdata+ '" value="L" style="visibility:hidden;width:0px"><input type="text" class="form-control input-small" id="luggage_ticket_key_'+ rec_id + '" value="' + aData[1]
								+ '" onblur="validateLuggageKey(' + rec_id+ ')" maxlength="8" style="width:300px;">';
			jqTds[1].innerHTML = '<input type="text" class="form-control " id="start_no_'+ rec_id+'" value="'+ aData[1]+'" '
								+' onkeyup="isInteger(this)" onblur="validateStartNoLuggage('+ rec_id+ ','+ newdata + ')" maxlength="8">';
			/*if(newdata>=1){
				var no=newdata-1;
				$("#start_no_"+no).attr('readonly', 'readonly');
				$("#end_no_"+no).attr('readonly', 'readonly');
				$("#no_of_luggage_ticket_"+no).attr('readonly', 'readonly');
				$("#no_of_books_"+no).attr('readonly', 'readonly');
				$("#priority_"+no).attr('readonly', 'readonly');
			}*/
			jqTds[2].innerHTML = '<input type="text" class="form-control " id="end_no_'+ rec_id+'" value="'+aData[2]+'" onblur="validateEndNoLuggage('+ rec_id+ ','+ newdata + ')" maxlength="8">';
			jqTds[3].innerHTML = '<input type="text" class="form-control " id="no_of_luggage_ticket_'+rec_id+ '" value="'+ aData[3]+'" readonly="readonly" >';
			jqTds[4].innerHTML = '<input type="text" class="form-control " id="no_of_books_'+rec_id+'" value="'+aData[4]+'"onblur="calculateEndNo(this,'+rec_id+','+newdata+',4)">';
			jqTds[5].innerHTML = '<input type="text" class="form-control " id="priority_'+rec_id+'" value="'+aData[5]+'">';
			jqTds[6].innerHTML = '<a class="deletetluggagebutton" href="">Delete</a>';
			
			$("#luggage_ticket_key_" + rec_id).bind('keyup',function(e) {
				var inputVal = $(this).val();
				if (/[^A-Za-z\d]/.test(inputVal)) {
					alert("Please enter only letter and numeric characters");
					document.getElementById('no_of_books_' + rec_id).focus();
					setTimeout(function() {
						document.getElementById('luggage_ticket_key_'+ rec_id).focus();
					}, 100);
					return (false);
				}
				if (e.which >= 97 && e.which <= 122) {
					var newKey = e.which - 32;
					e.keyCode = newKey;
					e.charCode = newKey;
				}
				$("#luggage_ticket_key_" + rec_id).val(($("#luggage_ticket_key_" + rec_id).val()).toUpperCase());
			});
				insert_id_luggage += newdata + ",";
		}
		function savTSheetRow(oTable3, nRow, rec_id, newdata) {
			var aData = oTable3.fnGetData(nRow);
			var jqTds = $('>td', nRow);

			jqTds[0].innerHTML ='<input type="hidden" id="ticket_type_id_'+ newdata+ '" value="L" style="visibility:hidden;width:0px"><input type="text" class="form-control input-small" id="waybill_key'+ rec_id + '" value="' + aData[1]
								+ '" onblur="validateTsheetKey(' + rec_id+ ')" maxlength="8" style="width:300px;">';
			jqTds[1].innerHTML = '<input type="text" class="form-control " id="start_no_'+ rec_id+'" value="'+ aData[2]+'" '
								+' onkeyup="isInteger(this)" onblur="validateStartNoTsheet('+ rec_id+ ','+ newdata + ')" maxlength="8">';
			/*if(newdata>=1){
				var no=newdata-1;
				$("#start_no_"+no).attr('readonly', 'readonly');
				$("#end_no_"+no).attr('readonly', 'readonly');
				$("#no_of_tsheets_"+no).attr('readonly', 'readonly');
				
			}*/
			jqTds[2].innerHTML = '<input type="text" class="form-control " id="end_no_'+ rec_id+'" value="'+aData[3]+'" onblur="validateEndNoTsheet('+ rec_id+ ','+ newdata + ')" maxlength="8">';
			jqTds[3].innerHTML = '<input type="text" class="form-control " id="no_of_tsheets_'+rec_id+ '" value="'+ aData[4]+'" readonly="readonly" >';
			jqTds[4].innerHTML = '<a class="deletetTsheetbutton" href="">Delete</a>';
			
			$("#waybill_key" + rec_id).bind('keyup',function(e) {
				var inputVal = $(this).val();
				if (/[^A-Za-z\d]/.test(inputVal)) {
					alert("Please enter only letter and numeric characters");
					document.getElementById('no_of_tsheets_' + rec_id).focus();
					setTimeout(function() {
						document.getElementById('waybill_key'+ rec_id).focus();
					}, 100);
					return (false);
				}
				if (e.which >= 97 && e.which <= 122) {
					var newKey = e.which - 32;
					e.keyCode = newKey;
					e.charCode = newKey;
				}
				$("#waybill_key" + rec_id).val(($("#waybill_key" + rec_id).val()).toUpperCase());
			});
				insert_id_tsheet += newdata + ",";
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
		var busstation_id = $('#parent_id').val();

		var oTable = $('#stockentry').dataTable({
			"aLengthMenu" : [ [ 5, 15, 20, -1 ],
							[ 5, 15, 20, "All" ] 
							],
			"iDisplayLength" : 5,
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
			"aoColumnDefs" : [ {'bSortable' : false,'aTargets' : [ 0, 1, 2, 3, 4, 5, 6 ,7]},
			                   {"bSearchable" : false,"aTargets" : [ 0 ]} ],
			"bFilter" : false,
			"bSelect" : false,
			"bPaginate" : false,
			"bSelect" : false,
			"bLengthChange" : false,
			"sDom" : '<"top">rt<"bottom"flp><"clear">',
			"oLanguage" : {
				"sZeroRecords" : "",
				"sEmptyTable" : ""
			}
		}).wrap("<div style='position:relative;overflow:auto;height:300px;'/>");
		jQuery('#stockentry_wrapper .dataTables_filter input').addClass("form-control input-medium input-inline"); 
		jQuery('#stockentry_wrapper .dataTables_length select').addClass("display:none"); 
		jQuery('#stockentry_wrapper .dataTables_length select').select2({showSearchInput : false
		}); 
		var oTable1 = $('#stockPassEntry').dataTable({
			"aLengthMenu" : [ [ 5, 15, 20, -1 ],
							[ 5, 15, 20, "All" ] // change per page values here
								],
			"iDisplayLength" : 5,
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
			"aoColumnDefs" : [ {'bSortable' : false,'aTargets' : [ 0, 1, 2, 3, 4, 5, 6, 7, 8,9,10 ]},
			                   {"bSearchable" : false,"aTargets" : [ 0 ]} 
							],
			"bFilter" : false,
			"bSelect" : false,
			"bPaginate" : false,
			"bLengthChange" : false,
			"sDom" : '<"top">rt<"bottom"flp><"clear">',
			"oLanguage" : {
				"sZeroRecords" : "",
				"sEmptyTable" : ""
			}
		}).wrap("<div style='position:relative;overflow:auto;height:300px;'/>");
		jQuery('#stockPassEntry_wrapper .dataTables_filter input').addClass("form-control input-medium input-inline"); // modify
		jQuery('#stockPassEntry_wrapper .dataTables_length select').addClass("form-control input-small"); // modify table per
		jQuery('#stockPassEntry_wrapper .dataTables_length select').select2({
				showSearchInput : true
		}); 

		var oTable2 = $('#stockLuggageEntry').dataTable({
			"aLengthMenu" : [ [ 5, 15, 20, -1 ],
			                  [ 5, 15, 20, "All" ] ],
			"iDisplayLength" : 5,
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
			"aoColumnDefs" : [ {'bSortable' : false,'aTargets' : [ 0, 1, 2, 3, 4, 5 ,6]},
			                   {"bSearchable" : false,"aTargets" : [ 0 ]} 
							],
			"bFilter" : false,
			"bSelect" : false,
			"bPaginate" : false,
			"bLengthChange" : false,
			"sDom" : '<"top">rt<"bottom"flp><"clear">',
			"oLanguage" : {
				"sZeroRecords" : "",
				"sEmptyTable" : ""
			}
		}).wrap("<div style='position:relative;overflow:auto;height:300px;'/>");
		
		var oTable3 = $('#stockTsheetEntry').dataTable({
			"aLengthMenu" : [ [ 5, 15, 20, -1 ],
			                  [ 5, 15, 20, "All" ] ],
			"iDisplayLength" : 5,
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
			"aoColumnDefs" : [ {'bSortable' : false,'aTargets' : [ 0, 1, 2, 3, 4]},
			                   {"bSearchable" : false,"aTargets" : [ 0 ]} 
							],
			"bFilter" : false,
			"bSelect" : false,
			"bPaginate" : false,
			"bLengthChange" : false,
			"sDom" : '<"top">rt<"bottom"flp><"clear">',
			"oLanguage" : {
				"sZeroRecords" : "",
				"sEmptyTable" : ""
			}
		}).wrap("<div style='position:relative;overflow:auto;height:300px;'/>");


		var nEditing = null;
		$('#addStockEntry1').unbind().click(function(e) {
			e.preventDefault();
			var aiNew = oTable.fnAddData([ '', '', '', '', '', '','', '','' ]);
			var nRow = oTable.fnGetNodes(aiNew[0]);
			savRow(oTable, nRow, new_id, new_id);
			nEditing = nRow;
			newrow = aiNew;
			new_id += 1;
		});
		$('#stockentry a.deletetktfgg').unbind().live('click keypress',function(e) {
			e.preventDefault();
			if (confirm("Are you sure,you want to delete the record ?") == false) {
				return;
			}
			var nRow = $(this).parents('tr')[0];
			oTable.fnDeleteRow(nRow);
		});
		$('#stockPassEntry a.deletetpassbutton  ').unbind().live('click',function(e) {
				e.preventDefault();
				if (confirm("Are you sure,you want to delete the record ?") == false) {
					return;
				}
				var nRow1 = $(this).parents('tr')[0];
				oTable1.fnDeleteRow(nRow1);
			});
		$('#stockLuggageEntry a.deletetluggagebutton').unbind().live('click',function(e) {
			e.preventDefault();
			if (confirm("Are you sure,you want to delete the record ?") == false) {
				return;
			}
			var nRow2 = $(this).parents('tr')[0];
			oTable2.fnDeleteRow(nRow2);
		});
		$('#stockTsheetEntry a.deletetTsheetbutton').unbind().live('click',function(e) {
			e.preventDefault();
			if (confirm("Are you sure,you want to delete the record ?") == false) {
				return;
			}
			var nRow3 = $(this).parents('tr')[0];
			oTable3.fnDeleteRow(nRow3);
		});

		$('#addPassStockEntry').unbind().click(	function(e) {
			e.preventDefault();
			var aiNew = oTable1.fnAddData([ '', '', '', '', '', '','', '', '', '', '','' ]);
			var nRow = oTable1.fnGetNodes(aiNew[0]);
			savPassRow(oTable1, nRow, new_id, new_id);
			nEditing = nRow;
			newrow = aiNew;
			new_id += 1;
		});
		$('#addLuggageStockEntry').unbind().click(function(e) {
			e.preventDefault();
			var aiNew = oTable2.fnAddData([ '', '', '', '', '', '','', '', '','' ]);
			var nRow = oTable2.fnGetNodes(aiNew[0]);
			savLuggageRow(oTable2, nRow, new_id, new_id);
			nEditing = nRow;
			newrow = aiNew;
			new_id += 1;
		});
		$('#addTripSheetStockEntry').unbind().click(function(e) {
			e.preventDefault();
			var aiNew = oTable3.fnAddData([ '', '', '', '', '', '' ]);
			var nRow = oTable3.fnGetNodes(aiNew[0]);
			savTSheetRow(oTable3, nRow, new_id, new_id);
			nEditing = nRow;
			newrow = aiNew;
			new_id += 1;
		});
		$('#stockentry a.cancel').unbind().live('click', function(e) {
			e.preventDefault();
			var $this = $(this);
			var mode = $this.data('mode');
			if (mode == "new") {
				var nRow = $(this).parents('tr')[0];
				oTable.fnDeleteRow(nRow);
			} else {
				var nRow1 = $(this).parents('tr')[0];
				restoreRow(oTable, nRow1);
			}
		});

		$('#backbtn1').unbind().click(function(e) {
			e.preventDefault();
			// alert('HI');
			document.forms[0].action = "ticketinv.action";
			document.forms[0].submit();
		});
		function formvalidation() {
			var indentNumber = $("#indentNumber").val();
			var indentDate = $("#indentDate").val();
			var receiptVoucherNo = $("#receiptVoucherNo").val();
			/*if(indentNumber==""){
				alert("Please Enter indent number");
				return false;
			}
			if(indentDate==""){
				alert("Please Enter indent date");
				return false;
			}
			if(receiptVoucherNo==""){
				alert("Please Enter receipt voucher number");
				return false;
			}*/
			if ((insert_id != "") || (insert_id_pass != "")|| (insert_id_luggage != "")||(insert_id_tsheet!="")) {
				var idArr = insert_id.split(',');
				if (idArr.length >= 2) {
					var arravalue = "";
					var arr = [];
					for ( var i = 0; i < idArr.length - 1; i++) {
						var denom_no = $('#denomi_no_' + idArr[i]).val();
						if(denom_no!=undefined){
							if (denom_no == 0) {
								alert("Please select a Ticket Type denomination");
								$('#denomi_no_' + idArr[i]).focus();
									return false;
							}
							var denom_key = $('#denom_key_' + idArr[i]).val();
							if (denom_key == "") {
								alert("Ticket Type denom key is manadatory");
								$('#denom_key_' + idArr[i]).focus();
								return false;
							}
							var start_no = $('#start_no_' + idArr[i]).val();
							var firsttwostart = start_no % 100;
							var end_no = $('#end_no_' + idArr[i]).val();
							if (start_no == "") {
								alert("Ticket Type start no of  is manadatory");
								var startno = document.getElementById('start_no_' + idArr[i]);
								document.getElementById('no_of_books_' + idArr[i]).focus();
								setTimeout(function() {
									document.getElementById('start_no_' + idArr[i]).focus();
								}, 100);
								return false;
							}
							var numbersOnly = /^\d+$/;
							if (start_no.match(numbersOnly)) {
							} else {
								alert('Ticket Type Start Number should not be decimal');
								document.getElementById('no_of_books_' + idArr[i]).focus();
								setTimeout(function() {
									document.getElementById('start_no_' + idArr[i]).focus();
								}, 100);
								document.getElementById('start_no_' + idArr[i]).value = "";
								return false;
							}
							if (firsttwostart != 00) {
								// alert(result);
								// document.getElementById('priority_'
								// + idArr[i]).value=result;
								// doclert("Ticket Type  last two digits of the Start number should be 00");
								document.getElementById('no_of_books_' + idArr[i]).focus();
								setTimeout(function() {
									document.getElementById('start_no_' + idArr[i]).focus();
								}, 100);
								return false;
							}
	
							var start_no = $('#start_no_' + idArr[i]).val();
							var firsttwostart = start_no % 100;
							var end_no = $('#end_no_' + idArr[i]).val();
							if (end_no == "") {
								alert("Ticket Type end no of  is manadatory");
								setTimeout(function() {
									document.getElementById('end_no_' + idArr[i]).focus();
								}, 100);
								return false;
							}
							var numbersOnly = /^\d+$/;
							if (end_no.match(numbersOnly)) {
							} else {
								alert('Ticket Type End Number should not be decimal');
								setTimeout(function() {
									document.getElementById('end_no_' + idArr[i]).focus();
								}, 100);
								document.getElementById('end_no_' + idArr[i]).value = "";
									return false;
							}
							var endlasttwo = end_no % 100;
							if (endlasttwo != 99) {
								alert("Ticket Type last two digits of the End number should be 99");
								setTimeout(function() {
									document.getElementById('end_no_' + idArr[i]).focus();
								}, 100);
								return false;
							}
							var denomid = $('#denomi_no_' + idArr[i]+' option:selected').val();
							var denom_key = $('#denom_key_' + idArr[i]).val();
							startProcessing();
							var alertData = $.ajax({
								type : "get",
								dataType : 'json',
								global : false,
								async : false,
								url : 'checkDuplicateDenomAction?denomval='+denomid+"&denom_key="+denom_key+"&start_no=" + start_no + "&end_no="+end_no,
								success : function(count) {
									if (count != 0) {
										alert("Ticket type already exists...");
									}
									return count;
								}
							}).responseText;
							endProcessing();
							var denomval = $('#denomi_no_' + idArr[i]+' option:selected').text().toString();
							if (alertData == 0) {
								if ((start_no != "") && (end_no != "")) {
									if (Number(end_no) > Number(start_no)) {
										var diff = (end_no - start_no) + 1;
										var blockno = diff / 100;
										var partblock = diff % 100;
										var noofbooks = blockno;
										var val = diff * Number(denomval);
									} else {
										alert("Ticket Type End Number should be greater than start number");
										$('#end_no_' + idArr[i]).val("");
										document.getElementById('no_of_books_' + idArr[i]).focus();
										setTimeout(function() {
											document.getElementById('end_no_' + idArr[i]).focus();
										}, 100);
									}
									startProcessing
									$.ajax({
										type : "get",
										url : 'findPriority?denomid='+denomid,
										success : function(result) {
										}
									});
									endProcessing();
								} else {
									document.getElementById('value_' + idArr[i]).focus();
									setTimeout(function() {
										document.getElementById('start_no_' + idArr[i]).focus();
									}, 100);
								}
							}
						
							arravalue = "";
							arravalue = $('#denomi_no_'+idArr[i]).val()+""+$('#denom_key_' + idArr[i]).val()+""+$('#start_no_'+idArr[i]).val()+""+$('#end_no_'+idArr[i]).val();
							arr.push(arravalue);
						}
					}
					var flag = checkIfArrayIsUnique(arr);
					if (flag == false) {
						alert("Ticket type contains duplicate entries");
						return false;
					}
				}
				var idArrPass = insert_id_pass.split(',');
				if (idArrPass.length >= 2) {
					var arrpass = [];
					var arravalue1 = "";
					for ( var i = 0; i < idArrPass.length - 1; i++) {
						var denom_key = $('#denom_key_' + idArrPass[i]).val();
						var tickettype = $('#ticket_type_id_' + idArrPass[i]).val();
						var denom_no = $('#denom_no_' + idArrPass[i]).val();
						var passDay = $('#pass_day_' + idArrPass[i]).val();
						if(denom_no!=undefined){
							if (tickettype == 0) {
								alert("please select a Pass Type");
								document.getElementById('ticket_type_id_' + idArrPass[i]).focus();
								return false;
							}
							if (denom_no == 0) {
								alert("Please select a Pass Type denomination");
								document.getElementById('denom_no_' + idArrPass[i]).focus();
								return false;
							}
							if (denom_key == "") {
								alert("Pass Type Denom key is manadatory");
								$('#denom_key_' + idArrPass[i]).focus();
								return false;
							}
		
							var start_no = $('#start_no_' + idArrPass[i]).val();
							var end_no = $('#end_no_' + idArrPass[i]).val();
							if (start_no == "") {
								alert("Pass Type start no is manadatory");
								document.getElementById('no_of_books_' + idArrPass[i]).focus();
								setTimeout(function() {
									document.getElementById('start_no_' + idArrPass[i]).focus();
								}, 100);
							return false;
							}
							if (end_no == "") {
								alert("Pass Type end no is manadatory");
								document.getElementById('no_of_books_' + idArrPass[i]).focus();
									setTimeout(function() {
										document.getElementById('end_no_' + idArrPass[i]).focus();
									}, 100);
								return false;
							}
							var numbersOnly = /^\d+$/;
							if (end_no.match(numbersOnly)) {
							} else {
								alert('Pass Type Start Number should not be decimal');
								document.getElementById('no_of_books_' + idArrPass[i]).focus();
								setTimeout(function() {
									document.getElementById('end_no_' + idArrPass[i]).focus();
								}, 100);
								document.getElementById('end_no_'+ idArrPass[i]).value = "";
							return false;
							}
		
							arravalue1 = "";
							//alert(arravalue1);
							arravalue1 = $('#denom_no_' + idArrPass[i]).val()+ ""+$('#denom_key_' + idArrPass[i]).val()+""+$('#start_no_' + idArrPass[i]).val()+""+$('#end_no_' + idArrPass[i]).val();
							
							if(tickettype=="2"){
								arravalue1 += passDay;
								//alert("daily pass"+arravalue1);
							}
							arrpass.push(arravalue1);
						    }
					    }
						var passflag = checkIfArrayIsUnique(arrpass);
						if (passflag == false) {
							alert("Pass type contains duplicate entries");
			 				return false;
					}
				}
				var idArrLugg = insert_id_luggage.split(',');
				if (idArrLugg.length >= 2) {
					var arrlugg = [];
					var arraluggvalue1 = "";
					for ( var i = 0; i < idArrLugg.length - 1; i++) {
						var luggage_ticket_key = $('#luggage_ticket_key_' + idArrLugg[i]).val();
						var start_no = $('#start_no_' + idArrLugg[i]).val();
						var end_no = $('#end_no_' + idArrLugg[i]).val();
						if(luggage_ticket_key!=undefined){
							if (luggage_ticket_key == "") {
								alert("Luggage ticket key is manadatory");
								document.getElementById('no_of_books_' + idArrLugg[i]).focus();
								setTimeout(function() {
									document.getElementById('luggage_ticket_key_'+ idArrLugg[i]).focus();
								}, 100);
								return false;
							}
							var tickettype = $('#ticket_type_id_' + idArrLugg[i]).val();
							var start_no = $('#start_no_' + idArrLugg[i]).val();
							var end_no = $('#end_no_' + idArrLugg[i]).val();
							if (start_no == "") {
								alert("Luggage Type start no of  is manadatory");
								document.getElementById('no_of_books_' + idArrLugg[i]).focus();
								setTimeout(function() {
									document.getElementById('start_no_' + idArrLugg[i]).focus();
								}, 100);
								return false;
							}
							var numbersOnly = /^\d+$/;
							if (start_no.match(numbersOnly)) {
							} else {
								alert('Luggage Type Start Number should not be decimal');
								document.getElementById('no_of_books_' + idArrLugg[i]).focus();
								setTimeout(function() {
									document.getElementById('start_no_' + idArrLugg[i]).focus();
								}, 100);
								document.getElementById('start_no_'+ idArrLugg[i]).value = "";
								return false;
							}
		
							var endluggagestarttwo = start_no % 100;
							if (!((endluggagestarttwo == 01) || (endluggagestarttwo == 51))) {
								alert("Luggage Type of  last two digits of Starting Number should be 00 or 50");
								document.getElementById('no_of_books_' + idArrLugg[i]).focus();
								setTimeout(function() {
									document.getElementById('start_no_' + idArrLugg[i]).focus();
								}, 100);
								return false;
							}
							var end_no = $('#end_no_' + idArrLugg[i]).val();
							if (end_no == "") {
								alert("Luggage Type end no of is manadatory");
								document.getElementById('no_of_books_' + idArrLugg[i]).focus();
								setTimeout(function() {
									document.getElementById('end_no_' + idArrLugg[i]).focus();
								}, 100);
								return false;
							}
							var numbersOnly = /^\d+$/;
							if (end_no.match(numbersOnly)) {
							} else {
								alert('Luggage Type Start Number should not be decimal');
								document.getElementById('no_of_books_' + idArrLugg[i]).focus();
								setTimeout(function() {
									document.getElementById('end_no_' + idArrLugg[i]).focus();
								}, 100);
								document.getElementById('end_no_'+ idArrLugg[i]).value = "";
								return false;
							}
							var endluggagelasttwo = end_no % 100;
							if (!((endluggagelasttwo == 50) || (endluggagelasttwo == 00))) {
								alert("Luggage Type last two digits of Closing Number should be 00 or 50");
								document.getElementById('no_of_books_' + idArrLugg[i]).focus();
								setTimeout(function() {
									document.getElementById('end_no_' + idArrLugg[i]).focus();
								}, 100);
								return false;
							}
							arraluggvalue1 = "";
								arraluggvalue1 = $(	'#luggage_ticket_key_' + idArrLugg[i]).val()+ ""+ $('#start_no_' + idArrLugg[i]).val()+ "" 
									+ $('#end_no_' + idArrLugg[i]).val();
								arrlugg.push(arraluggvalue1);
							}
						}
						var luggflag = checkIfArrayIsUnique(arrlugg);
						if (luggflag == false) {
							alert("Luggage type contains duplicate entries");
							return false;
						}
					}
				var idArrTsheet = insert_id_tsheet.split(',');
				if (idArrTsheet.length >= 1) {
					var arrtsheet = [];
					var arratsheetvalue1 = "";
					for ( var i = 0; i < idArrTsheet.length - 1; i++) {
						var waybill_key = $('#waybill_key' + idArrTsheet[i]).val();
						
						if(waybill_key!=undefined){
							if (waybill_key == "") {
								alert("Trip Sheet key is manadatory");
								
								return false;
							}
							
							var start_no = $('#start_no_' + idArrTsheet[i]).val();
							
							if (start_no == "") {
								alert("Trip Sheet Type start no of  is manadatory");
								
								return false;
							}
							var numbersOnly = /^\d+$/;
							if (start_no.match(numbersOnly)) {
							} else {
								alert('Trip Sheet Start Number should not be decimal');
								
								setTimeout(function() {
									document.getElementById('start_no_' + idArrTsheet[i]).focus();
								}, 100);
								document.getElementById('start_no_'+ idArrTsheet[i]).value = "";
								return false;
							}
		
							
							var end_no = $('#end_no_' + idArrTsheet[i]).val();
							if (end_no == "") {
								alert("Trip Sheet Type end no is manadatory");
								
								setTimeout(function() {
									document.getElementById('end_no_' + idArrTsheet[i]).focus();
								}, 100);
								return false;
							}
							var numbersOnly = /^\d+$/;
							if (end_no.match(numbersOnly)) {
							} else {
								alert('Trip Sheet Type Start Number should not be decimal');
								
								setTimeout(function() {
									document.getElementById('end_no_' + idArrTsheet[i]).focus();
								}, 100);
								document.getElementById('end_no_'+ idArrTsheet[i]).value = "";
								return false;
							}
							
							arratsheetvalue1 = "";
							arratsheetvalue1 = $('#waybill_key' + idArrTsheet[i]).val()+ ""+ $('#start_no_' + idArrTsheet[i]).val()+ "" 
									+ $('#end_no_' + idArrTsheet[i]).val();
							arrtsheet.push(arratsheetvalue1);
							}
						}
						var tsheetflag = checkIfArrayIsUnique(arrtsheet);
						if (tsheetflag == false) {
							alert("Trip Sheet type contains duplicate entries");
							tsheetflag=true;
							return false;
						}
					}
				}
				return true;
			}
		function checkIfArrayIsUnique(myArray) {
			isUnique = true;
			for ( var i = 0; i < myArray.length; i++) {
				for ( var j = 0; j < myArray.length; j++) {
					if (i != j) {
						if (myArray[i] == myArray[j]) {
							isUnique = false;
						}
					}
				}
			}

			return isUnique;
		}

		function setTicketMessage(succtick1) {
		}
		/*$('#stockentry_save_tab').click(function(e) {
			aaa();
		});*/
		$('#stockentry_save_tab').click(function(e) {
			
				var succmsg = "";
				var validatestatus = formvalidation();
				var isSuccess = false;
				var succtick = "";
				var succmsg = "";
				var succmsg = "";
				var succmsg = "";
 				var succmsg = "";
				var succmsg = "";
				var succmsg = "";
				var succmsg = "";
				var count = 0;
				// alert("flag value is..."+validatestatus);
				var indentNumber = $("#indentNumber").val();
				var indentDate = $("#indentDate").val();
				var receiptVoucherNo = $("#receiptVoucherNo").val();
				var vendorName = $("#vendorName").val();
				var vendorContact = $("#vendorContact").val();
				var vendorAddress = $("#vendorAddress").val();
				if (validatestatus == true) {
					startProcessing();
					e.preventDefault();
					document.getElementById('errorluggage1').innerHTML = "";
					document.getElementById('errorluggage2').innerHTML = "";
					document.getElementById('errorluggage3').innerHTML = "";
					document.getElementById('errorluggage4').innerHTML = "";
					document.getElementById('errorluggage5').innerHTML = "";
					document.getElementById('errortsheet1').innerHTML = "";
					document.getElementById('errortsheet2').innerHTML = "";
					document.getElementById('errortsheet3').innerHTML = "";
					document.getElementById('errortsheet4').innerHTML = "";
					document.getElementById('errortsheet5').innerHTML = "";
					document.getElementById('errorpass1').innerHTML = "";
					document.getElementById('errorpass2').innerHTML = "";
					document.getElementById('errorpass3').innerHTML = "";
					document.getElementById('errorpass4').innerHTML = "";
					document.getElementById('errorpass5').innerHTML = "";
					document.getElementById('errorticket1').innerHTML = "";
					document.getElementById('errorticket2').innerHTML = "";

					document.getElementById('errorticket3').innerHTML = "";
					document.getElementById('errorticket4').innerHTML = "";
					document.getElementById('errorticket5').innerHTML = "";
					var idArrticket = insert_id.split(',');
					var succmsg = "";
					var duplicateflag = false;
					var errorflag = false;
					var deletedID = deletedid.split(',');
					for(var i = 0; i <= idArrticket.length - 1; i++) {
						var denom_no = $('#denomi_no_' + idArrticket[i]).val();
						if (denom_no != undefined){
							/*if ((denom_no != undefined) || (idArrticket.length == 1)) {
								var denom_no = "0";
								var denom_key = "0";
								var start_no = "0";
								var end_no = "0";
								var no_of_books = "0";
								var value = "0";
								var priority = "0";
							} else {*/
								var tickettype = $('#ticket_type_id_'+ idArrticket[i]).val();
								var denom_no = $('#denomi_no_'+ idArrticket[i]).val();
								var denom_key = $('#denom_key_'+ idArrticket[i]).val();
								var start_no = $('#start_no_'+ idArrticket[i]).val();
								var end_no = $('#end_no_' + idArrticket[i]).val();
								var end_no = $('#end_no_' + idArrticket[i]).val();
								var no_of_books = $('#no_of_books_'+ idArrticket[i]).val();
								var value = $('#value_' + idArrticket[i]).val();
								var priority = $('#priority_'+ idArrticket[i]).val();
								if (priority == undefined) {
									priority = "0";
								}
							/*}*/
								count++;
							$.ajax({
								type : "post",
								async : false,
								url : "saveStock!insertrows?id="+ idArrticket[i]+ "&denom_no="+ denom_no+ "&denom_key="+ denom_key+ "&start_no="+ start_no
									+ "&end_no="+ end_no+ "&no_of_books="+ no_of_books+ "&tickettype="+ tickettype+ "&priority="+ priority 
									+ "&value=" + value+"&refId="+stockEntryTempId,
								success : function(result) {
									succmsg += "@";
									var s = "";
									if (result != "") {
										succtick = result;
										s = result.split("@");
										if (s[0].indexOf("exists") != -1) {
											duplicateflag = true;
											var divId = document.getElementById("successmsgticket");
											divId.style.display = 'block';
											divId.style.visibility = 'visible';
											document.getElementById('succticket').innerHTML = s[0];
										} else if (s[0].indexOf("successfully") != -1) {
											if (s != "") {
												succmsg += s[0];
											}
										} else {
											errorflag = true;
											var divId = document.getElementById("errormsg");
											divId.style.display = 'block';
											divId.style.visibility = 'visible';
											for ( var i = 0; i < s.length; i++) {
												document.getElementById('errorticket'+ (i + 1)).innerHTML = s[i];
											}
										}
									}
								}
							});
						}
					}
					var idArrpass = insert_id_pass.split(',');
					for ( var i = 0; i <= idArrpass.length - 1; i++) {
						var denom_no = $('#denom_no_'+ idArrpass[i]).val();
						if(denom_no != undefined){
							/*if ((denom_no != undefined)|| (idArrpass.length == 1)) {
								var tickettype = "0";
								var denom_no = "0";
								var denom_key = "0";
								var pass_day = "0";
								var start_no = "0";
								var end_no = "0";
								var no_of_passess = "0";
								var no_of_books = "0";
								var total_value = "0";
								var priority = "0";
							} else {*/
								var tickettype = $('#ticket_type_id_'+ idArrpass[i]).val();
								var denom_no = $('#denom_no_'+ idArrpass[i]).val();
								var denom_key = $('#denom_key_'+ idArrpass[i]).val();
								var pass_day = $('#pass_day_'+ idArrpass[i]).val();
								var start_no = $('#start_no_'+ idArrpass[i]).val();
								var end_no = $('#end_no_'+ idArrpass[i]).val();
								var no_of_passess = $('#no_of_passes_'+ idArrpass[i]).val();
								var no_of_books = $('#no_of_books_'+ idArrpass[i]).val();
								var total_value = $('#total_value_'+ idArrpass[i]).val();
								var priority = $('#priority_'+ idArrpass[i]).val();
								if (priority == undefined) {
									priority = "0";
								}
//							}
								count++;
							$.ajax({
								type : "post",
								async : false,
								url : "saveStock!insertPassrows?id="
									+ idArrpass[i]+ "&type_of_pass="+ tickettype+ "&denom_no="+ denom_no+ "&denom_key="+ denom_key+ "&pass_day="+ pass_day
									+ "&start_no="+ start_no+ "&end_no="+ end_no+ "&no_of_passess="+ no_of_passess+ "&no_of_books="+ no_of_books+ "&tickettype="
									+ tickettype+ "&total_value="+ total_value+ "&priority="+ priority+"&refId="+stockEntryTempId,
								success : function(result) {
									var s1 = "";
									succmsg += "@";
									if (result != "") {
										s1 = result.split("@");
										if (s1[0].indexOf("exists") != -1) {
											duplicateflag = true;
											var divId = document.getElementById("successmsgpass");
											divId.style.display = 'block';
											divId.style.visibility = 'visible';
											document.getElementById('succpass').innerHTML = s1[0];
										} else if (s1[0].indexOf("successfully") != -1) {
											if (s1 != "") {
												succmsg += s1[0];
											}
										} else {
											errorflag = true;
											var divId = document.getElementById("errormsgpass");
											divId.style.display = 'block';
											divId.style.visibility = 'visible';
											for ( var i = 0; i < s1.length; i++) {
												document.getElementById('errorpass'+ (i + 1)).innerHTML = s1[i];
											}
										}
									}
								}
							});
						}
					}
					var idArrLuggage = insert_id_luggage.split(',');
					for ( var i = 0; i <= idArrLuggage.length - 1; i++) {
						var luggage_ticket_key = $('#luggage_ticket_key_'+ idArrLuggage[i]).val();
						if(luggage_ticket_key != undefined){
							/*if ((luggage_ticket_key != undefined)|| (idArrLuggage.length == 1)) {
								var denomno = "0";
								var tickettype = "0";
								var luggage_ticket_key = "0";
								var no_of_luggage_ticket = "0";
								var start_no = "0";
								var end_no = "0";
								var no_of_books = "0";
								var value = "0";
								var priority = "0";
							} else {*/
								var tickettype = $('#ticket_type_id_'+ idArrLuggage[i]).val();
								var luggage_ticket_key = $('#luggage_ticket_key_'+ idArrLuggage[i]).val();
								var no_of_luggage_ticket = $('#no_of_luggage_ticket_'+ idArrLuggage[i]).val();
								var start_no = $('#start_no_'+ idArrLuggage[i]).val();
								var end_no = $('#end_no_'+ idArrLuggage[i]).val();
								var no_of_books = $('#no_of_books_'+ idArrLuggage[i]).val();
								var priority = $('#priority_'+ idArrLuggage[i]).val();
								if (priority == undefined) {
									priority = "0";
								}
//							}
								count++;
							$.ajax({
								type : "post",
								async : false,
								url : "saveStock!insertLuggagerows?id="
									+ idArrLuggage[i]+ "&luggage_ticket_key="+ luggage_ticket_key+ "&no_of_luggage_ticket="+ no_of_luggage_ticket
									+ "&start_no="+ start_no+ "&end_no="+ end_no+ "&no_of_books="+ no_of_books+ "&tickettype="+ tickettype
									+ "&value="+ value+ "&priority="+ priority+ "&refId="+stockEntryTempId,
								success : function(result) {
									var s2 = "";
									succmsg += "@";
									if (result != "") {
										s2 = result.split("@");																									
										if (s2[0].indexOf("exist") != -1) {
											isSuccess = true;
											duplicateflag = true;
											var divId = document.getElementById("successmsgluggage");
											divId.style.display = 'block';
											divId.style.visibility = 'visible';
											document.getElementById('succluggage').innerHTML = s2[0];
										} else if (s2[0].indexOf("successfully") != -1) {
											if (s2 != "") {
												succmsg += s2[0];
											}
										} else {
											errorflag = true;
											var divId = document.getElementById("errormsgluggage");
											divId.style.display = 'block';
											divId.style.visibility = 'visible';
											for ( var i = 0; i < s2.length; i++) {
												document.getElementById('errorluggage'+ (i + 1)).innerHTML = s2[i];
											}
										}
									} 
								}
							});
						}
					}
					var idArrTsheet = insert_id_tsheet.split(',');
					for ( var i = 0; i <= idArrTsheet.length - 1; i++) {
						var waybill_key = $('#waybill_key'+ idArrTsheet[i]).val();
						if(waybill_key != undefined){
							/*if ((luggage_ticket_key != undefined)|| (idArrLuggage.length == 1)) {
								var denomno = "0";
								var tickettype = "0";
								var luggage_ticket_key = "0";
								var no_of_luggage_ticket = "0";
								var start_no = "0";
								var end_no = "0";
								var no_of_books = "0";
								var value = "0";
								var priority = "0";
							} else {*/
								var tickettype = $('#ticket_type_id_'+ idArrTsheet[i]).val();
								var waybill_key = $('#waybill_key'+ idArrTsheet[i]).val();
								var start_no = $('#start_no_'+ idArrTsheet[i]).val();
								var end_no = $('#end_no_'+ idArrTsheet[i]).val();
								var no_of_tsheets_ = $('#no_of_tsheets_'+ idArrTsheet[i]).val();
								
//							}
								count++;
							$.ajax({
								type : "post",
								async : false,
								url : "saveStock!insertTSheetrows?id="
									+ idArrTsheet[i]+ "&waybill_key="+ waybill_key+ "&no_of_tsheets_="+ no_of_tsheets_
									+ "&start_no="+ start_no+ "&end_no="+ end_no+ "&tickettype="+ tickettype
									+ "&refId="+stockEntryTempId,
								success : function(result) {
									var s2 = "";
									succmsg += "@";
									if (result != "") {
										s2 = result.split("@");																									
										if (s2[0].indexOf("exist") != -1) {
											isSuccess = true;
											duplicateflag = true;
											var divId = document.getElementById("successmsgtsheet");
											divId.style.display = 'block';
											divId.style.visibility = 'visible';
											document.getElementById('succtsheet').innerHTML = s2[0];
										} else if (s2[0].indexOf("successfully") != -1) {
											if (s2 != "") {
												succmsg += s2[0];
											}
										} else {
											errorflag = true;
											var divId = document.getElementById("errormsgtsheet");
											divId.style.display = 'block';
											divId.style.visibility = 'visible';
											for ( var i = 0; i < s2.length; i++) {
												document.getElementById('errortsheet'+ (i + 1)).innerHTML = s2[i];
											}
										}
									} 
								}
							});
						}
					}
					endProcessing();
					if(count==0){
						bootbox.alert("Please enter stock");
					}else{
						if ((idArrticket.length != 1)|| (idArrpass.length != 1)|| (idArrLuggage.length != 1)||(idArrTsheet.length!=1)) {
							if (errorflag == false) {
								if (duplicateflag == false) {
									//alert(succmsg);
									document.stockentryform.stockentrysuccmsg.value = stockEntryTempId;
									document.stockentryform.indentNumber.value = indentNumber;
									document.stockentryform.indentDate.value = indentDate;
									document.stockentryform.receiptVoucherNo.value = receiptVoucherNo;
									document.stockentryform.vendorName.value = vendorName;
									document.stockentryform.vendorContact.value = vendorContact;
									document.stockentryform.vendorAddress.value = vendorAddress;
									
									//console.log(indentDate+" "+indentDate+" "+receiptVoucherNo);
									document.forms["stockentryform"].action = 'ticketInventoryEntrySuccess.action';
									document.forms["stockentryform"].submit();
								}
							}
						}
					}
					
												 	/*}
												});
											} 
										}
									});
								}
							}
						});
					}*/
				}
				e.preventDefault();
				e.preventDefault();
			});
		}
	};
}();
