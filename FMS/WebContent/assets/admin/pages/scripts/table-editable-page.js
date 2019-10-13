//alert("Hii");
var TableEditablepage = function() {

	return {

		// main function to initiate the module
		init : function() {
			// Strat Code After This
		
			var new_id =0 ;
			var newrow;
			var nEditing = null;
			var insert_id = "";
			
			var update_id = "";
		
			var edit_id=0;
		
			var deletedid = "";
			var flag="";
		
		
			
			var rolid=document.getElementById("roleid").value;
			//alert("rolid--"+document.getElementById("roleid").value);
			
			var pageoTable = $('#viewpageroledetails').dataTable(
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
						/*"bProcessing" : true,
						"bServerSide" : true,*/
						"sAjaxSource" : "PageRoleDetails.action?role_id="+rolid,
						"sPaginationType" : "bootstrap",
						"bDestroy" :true,
						"oLanguage" : {
							"sLengthMenu" : "_MENU_ records",
							"oPaginate" : {
								"sPrevious" : "Prev",
								"sNext" : "Next"
							}
						}
						/*"aoColumnDefs" : [ {
							'bSortable' : false,
							'aTargets' : [ 0 ]
						} ]*/
					});
			jQuery('#viewpageroledetails_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#viewpageroledetails_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
			
			//cancelpagerole
			 $('#cancelpagerole').click(function (e) {
				 document.forms[0].action = "ShowRoleAction.action";
					document.forms[0].submit();
			 });
			
           $('#pageNew').click(function (e) {
        	  // alert("hii");
        	  if(flag!='true')
           	{
        		   e.preventDefault();
                   var aiNew = pageoTable.fnAddData(['', '','','','','']);
                   var nRow = pageoTable.fnGetNodes(aiNew[0]);
                   savRow(pageoTable, nRow, new_id, new_id);
	   				nEditing = nRow;
	   				newrow = aiNew;
	   				new_id += 1;
	   				 flag="true";
           	}else{
           	//	alert("Only enter one role one time");
           		return false;
           	}
        	   
        	   
           });
			
            
          
            function savRow(pageoTable, nRow, rec_id, newdata) {
            	var aData = pageoTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);
                jqTds[0].innerHTML = '';
               
                $.ajax({
                    type: "POST",
                	async:false,
                    url: "GetPagelist.action?rolid="+rolid,
                    success: function(response){
                    	var arr = response.split(',');
                    	//alert("arr--"+arr);
                    	 var i=0;
                    	 var a = '<select id="pageid'+ rec_id+'"  class="select2_category form-control" style="width:250px" ><option value="0">select</option>' 
                    	for (i = 0; i < arr.length-1; i++) {
                    		var splitresult = arr[i].split('@');
                    		//alert("splitresult--"+splitresult);
                    		 a+='<option value="'+splitresult[0]+'">'+splitresult[1].trim()+'</option>'	
                    	}
                    	 a+='</select>'
                    		
                    	 jqTds[1].innerHTML=a;
                    	 jqTds[2].innerHTML = '<input type="checkbox"   id="readaccess"  />';
                    	 jqTds[3].innerHTML = '<input type="checkbox" id="writeaccess" />';
                    	 jqTds[4].innerHTML = '<a class="save"  value="'+ rec_id +'" href="" >Save</a>';
                    	 //jqTds[4].innerHTML = '<a class="save" value="'+ rec_id +'" href="">Save</a>';
                    	 //jqTds[5].innerHTML = '<a class="delete"  value="'+ rec_id +'" href="" >Delete</a>';
                    	 FormSamples.init();
                    	 
                    }
            	});
                insert_id += newdata + ",";
                
               
            }
            
	$('#viewpageroledetails a.save')
			
			.unbind().live(
					'click',
					function(e) {
						flag="";
						e.preventDefault();
						var useid=$("#userid").val();
						var path=$("#path").val();
					//	alert("val"+useid+"path"+path);
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
							 alert ("Please Select Page");
							 flag='true';
							 return false;
						}else{
					
							$.ajax({
							type: 'POST',
							async:false,
							url : "addPageRoledetails.action?roleid="
								+ rolid
								+ "&pageid="
								+ pageid
								+ "&readright="
								+ readright
								+ "&writeright="
								+ writeright
								+ "&userid="
								+ useid
								+ "&path="
								+path,
							success: function(data)
							{
								//alert(data);
								if(data!=""){
									
									
									if(data.indexOf("Successfully")!= -1)
									{
										var divId = document.getElementById("successmsg");
									divId.style.display = 'block';
									divId.style.visibility = 'visible';
									document.getElementById('successpagerole').innerHTML = data;
									}
										else{
								var divId=document.getElementById("errormsg");
				        		   divId.style.display='block';
				        			divId.style.visibility='visible';
				        			document.getElementById('errorpagerole').innerHTML=data;
										}
									pageoTable = $('#viewpageroledetails').dataTable(
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
				    							"sAjaxSource" : "PageRoleDetails.action?role_id="+rolid,
				    							"sPaginationType" : "bootstrap",
				    							"bDestroy" :true,
				    							
				    							"oLanguage" : {
				    								"sLengthMenu" : "_MENU_ records",
				    								"oPaginate" : {
				    									"sPrevious" : "Prev",
				    									"sNext" : "Next"
				    								}
				    							}
				    							/*"aoColumnDefs" : [ {
				    								'bSortable' : false,
				    								'aTargets' : [ 0 ]
				    							} ]*/
				    						});
									
									jQuery('#viewpageroledetails_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
						            jQuery('#viewpageroledetails_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
								}else{
									
								}
							},
							error: function(e)
							{
							   alert(e.message);
							}
						});

					}//else
						
					});
        	$('#viewpageroledetails a.delete')
			.unbind()
			.live(
					'click',
					function(e) {
						e.preventDefault();
							
				
						var delete_id = $(this).attr('value');
						bootbox.confirm("Are you Sure, you want to delete this record?",
							
							function(result)
								{
								if(result == true) {
								
									
									
									$
									.ajax({
										type : 'POST',
										async:false,
										url : "getDeleteRolePagedetails?pageroleid=" + delete_id,
										success : function(data) {
											//alert(data);
											if (data != "") {
												//alert("flag"+flag);
										
												flag='false';
											//	alert("flag1"+flag);
												var divId = document
														.getElementById("successmsg");
												divId.style.display = 'block';
												divId.style.visibility = 'visible';
												document.getElementById('successpagerole').innerHTML = data;
												

											} else {

											}
										},
										error : function(e) {
											alert(e.message);
										}
									});                                                                                                            
									
								deletedid += delete_id + ",";
								//restoreRow(pageoTable, nEditing);
								async:false,
								pageoTable = $('#viewpageroledetails').dataTable(
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
			    							"sAjaxSource" : "PageRoleDetails.action?role_id="+rolid,
			    							"sPaginationType" : "bootstrap",
			    							"bDestroy" :true,
			    							
			    							"oLanguage" : {
			    								"sLengthMenu" : "_MENU_ records",
			    								"oPaginate" : {
			    									"sPrevious" : "Prev",
			    									"sNext" : "Next"
			    								}
			    							}
			    							/*"aoColumnDefs" : [ {
			    								'bSortable' : false,
			    								'aTargets' : [ 0 ]
			    							} ]*/
			    						});
								jQuery('#viewpageroledetails_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
					            jQuery('#viewpageroledetails_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
								
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
