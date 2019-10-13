var Tableuserroleepage = function() {
//alert("hello");
	return {
		
		init : function() {
			var new_id =0 ;
			var newrow;
			var nEditing = null;
			var insert_id = "";
			var update_id = "";
			var edit_id=0;
			var deletedid = "";
			var flag="";
		
		  	
			
			var roleid=document.getElementById("roleid").value;
			//alert("roleid---"+roleid);
			var useriddetails=document.getElementById("useriddetails").value;
			
			var usrrolepageoTable = $('#viewuserrolepagedetails').dataTable(
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
						//"sAjaxSource" : "PageRoleDetails.action?role_id="+rolid,
						"sAjaxSource" : "UserRolePageDetails.action?role_id="+roleid+"&useriddetails="+useriddetails,
						"sPaginationType" : "bootstrap",
						"bDestroy" :true,
						"oLanguage" : {
							"sLengthMenu" : "_MENU_ records",
							"oPaginate" : {
								"sPrevious" : "Prev",
								"sNext" : "Next"
							}
						},
						/*"aoColumnDefs" : [ {
							'bSortable' : false,
							'aTargets' : [ 0 ]
						} ]*/
					});
			
			jQuery('#viewuserrolepagedetails_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#viewuserrolepagedetails_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
			
			 $('#canceluserrolepage').click(function (e) {
				 document.forms[0].action = "ShowUserList.action";
					document.forms[0].submit();
			 });
			
           $('#pageNewForUserRole').click(function (e) {
        	   //alert("test");
        	 //  alert("flag"+flag);
        	   if(flag!='true')
        		   {
                e.preventDefault();
                var aiNew = usrrolepageoTable.fnAddData(['', '','','','','']);
                var nRow = usrrolepageoTable.fnGetNodes(aiNew[0]);
                savRow(usrrolepageoTable, nRow, new_id, new_id);
				nEditing = nRow;
				newrow = aiNew;
				new_id += 1;
				flag="true";
        		   }else{
        			   return false;
        		   }
            });
			
            
          
            function savRow(usrrolepageoTable, nRow, rec_id, newdata) {
            	//alert("savRow");
            	var aData = usrrolepageoTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);
                jqTds[0].innerHTML = '';
                $.ajax({
                    type: "POST",
                	async:false,
                    url: "GetPagelistForUser.action?useriddetails="+useriddetails,
                    success: function(response){
                    	var arr = response.split(',');
                    	//alert("arr--"+arr);
                    	 var i=0;
                    	 var a = '<select id="pageid'+ rec_id+'"  class="select2_category form-control" style="width:250px" ><option value="0">select</option>' 
                    	for (i = 0; i < arr.length-1; i++) {
                    		var splitresult = arr[i].split('@');
                    		//alert("splitresult--"+splitresult);
                    		 a+='<option value="'+splitresult[0]+'">'+splitresult[1]+'</option>'	
                    	}
                    	 a+='</select>'
                    		
                    	 jqTds[1].innerHTML=a;
                    	 jqTds[2].innerHTML = '<input type="checkbox"   id="readaccess"  />';
                    	 jqTds[3].innerHTML = '<input type="checkbox" id="writeaccess" />';
                    	 jqTds[4].innerHTML = '<a class="save"  value="'+ rec_id +'" href="" >Save</a>';
                    	 //jqTds[5].innerHTML = '<a class="delete"  value="'+ rec_id +'" >Delete</a>';
                    	 FormSamples.init();
                    }
            	});
                insert_id += newdata + ",";
                
               
            }
            
	$('#viewuserrolepagedetails a.save')
			
			.unbind().live(
					'click',
					function(e) {
						flag="";
						e.preventDefault();
						var nRow = $(this).parents('tr')[0];
						var insertid = insert_id.split(',');
						var rec_id = $(this).attr('value');
						var readaccess=document.getElementById("readaccess").checked;
						var readright;
						var writeaccess;
						if(readaccess){
							
							readright=1;
						}else{
							
							readright=0;
						}
						var writeaccess=document.getElementById("writeaccess").checked;
						if(writeaccess){
							writeright=1;
						}else{
							writeright=0;
						}
						var pageid = $('#pageid' + insertid[rec_id]).val();
						if(pageid==0){
							//alert("Flag"+flag);
							 alert ("Please Select Page");
							 flag='true';
							 //alert("Flag"+flag);
							 return false;
						}else{
							//url :"addUserRolePagedetails.action?role_id="+ roleid +"&pageid="+ pageid+"&readright=" +readright+"&writeright=" + writeright+ "&useriddetails=" +useriddetails,
							$.ajax({
								type: 'POST',
								async:false,
								url : "addUserRolePagedetails.action?role_id="
									+ roleid
									+ "&pageid="
									+ pageid
									+ "&readright="
									+ readright
									+ "&writeright="
									+ writeright
									+ "&useriddetails="
									+ useriddetails,
								success: function(data)
								{
									//alert(data);
									if(data!=""){
										
										
										if(data.indexOf("Successfully")!= -1)
										{
											var divId = document.getElementById("successmsg");
										divId.style.display = 'block';
										divId.style.visibility = 'visible';
										document.getElementById('successuserrolepage').innerHTML = data;
										}
											else{
									var divId=document.getElementById("errormsg");
					        		   divId.style.display='block';
					        			divId.style.visibility='visible';
					        			document.getElementById('erroruserrolepage').innerHTML=data;
											}
										 usrrolepageoTable = $('#viewuserrolepagedetails').dataTable(
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
					    							"sAjaxSource" : "UserRolePageDetails.action?role_id="+roleid+"&useriddetails="+useriddetails,
					    							"sPaginationType" : "bootstrap",
					    							"bDestroy" :true,
					    							
					    							"oLanguage" : {
					    								"sLengthMenu" : "_MENU_ records",
					    								"oPaginate" : {
					    									"sPrevious" : "Prev",
					    									"sNext" : "Next"
					    								}
					    							},
					    							/*"aoColumnDefs" : [ {
					    								'bSortable' : false,
					    								'aTargets' : [ 0 ]
					    							} ]*/
					    						});
										
										 jQuery('#viewuserrolepagedetails_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
								         jQuery('#viewuserrolepagedetails_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
									}else{
										
									}
								},
								error: function(e)
								{
								   alert(e.message);
								}
							});

						}//else

						/*$.ajax({
							
									url :"addUserRolePagedetails.action?role_id="+ roleid +"&pageid="+ pageid+"&readright=" +readright+"&writeright=" + writeright+ "&useriddetails=" +useriddetails,
								
									context : document.body
									
									
								})
						.done(
								function() {
									$(this)
											.addClass(
													"done");
									
									usrrolepageoTable = $('#viewuserrolepagedetails').dataTable(
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
												"sAjaxSource" : "UserRolePageDetails.action?role_id="+roleid+"&useriddetails="+useriddetails,
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
									
									jQuery('#viewuserrolepagedetails.dataTables_filter input')
									.addClass("form-control input-medium input-inline"); // modify
							// table
							// search
							// input
							jQuery('#viewuserrolepagedetails.dataTables_length select')
									.addClass("form-control input-small"); // modify table per
							// page dropdown
							jQuery('#viewuserrolepagedetails.dataTables_length select')
									.select2({
										showSearchInput : true
									// hide search box with special css class
									}); // initialize select2 dropdown
							usrrolepageoTable.ajax.reload();

								});
					
					//alert("testing");
*/						
						
					});
        	$('#viewuserrolepagedetails a.delete')
			.unbind()
			.live(
					'click',
					function(e) {
						e.preventDefault();
							
						/*if (confirm("Are you sure to delete this row ?") == false) {
							return;
						}
						var delete_id = $(this).attr('value');
						//alert("delete_id---"+delete_id);
						var nRow = $(this).parents('tr')[0];
						usrrolepageoTable.fnDeleteRow(nRow);
						$.ajax({
							
							 url : "getDeleteUserRolePagedetails.action?userrolepageid="+ delete_id,
							context : document.body
								}).done(function() {
							$(this).addClass("done");

						});
					deletedid += delete_id + ",";*/
						
						
						var delete_id = $(this).attr('value');
						bootbox.confirm("Are you Sure, you want to delete this record?",
							
							function(result)
								{
								if(result == true) {
								
									
									
									$
									.ajax({
										type : 'POST',
										async:false,
										url :  "getDeleteUserRolePagedetails.action?userrolepageid="+ delete_id,
										success : function(data) {
											//alert(data);
											flag='false';
											if (data != "") {
												var divId = document
														.getElementById("successmsg");
												divId.style.display = 'block';
												divId.style.visibility = 'visible';
												document.getElementById('successuserrolepage').innerHTML = data;
												

											} else {

											}
										},
										error : function(e) {
											alert(e.message);
										}
									});                                                                                                            
									
								//deletedid += delete_id + ",";
								//restoreRow(pageoTable, nEditing);
								
								 usrrolepageoTable = $('#viewuserrolepagedetails').dataTable(
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
			    							"sAjaxSource" : "UserRolePageDetails.action?role_id="+roleid+"&useriddetails="+useriddetails,
			    							"sPaginationType" : "bootstrap",
			    							"bDestroy" :true,
			    							
			    							"oLanguage" : {
			    								"sLengthMenu" : "_MENU_ records",
			    								"oPaginate" : {
			    									"sPrevious" : "Prev",
			    									"sNext" : "Next"
			    								}
			    							},
			    							/*"aoColumnDefs" : [ {
			    								'bSortable' : false,
			    								'aTargets' : [ 0 ]
			    							} ]*/
			    						});
								 jQuery('#viewuserrolepagedetails_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
						            jQuery('#viewuserrolepagedetails_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
								
								}else{
									return;
								}
						});
						
						
						
						
						});
            
          
           			//});
			
			//End Code Before This

		}

	};

}();
