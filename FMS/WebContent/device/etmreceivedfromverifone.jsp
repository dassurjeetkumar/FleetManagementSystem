 <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
</head>
<body>
<div class="page-content-wrapper">
	<div class="page-content">
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
					Etm Recevied From Verifone
				</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN EXAMPLE TABLE PORTLET-->
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i>Etm Recevied From Verifone
						</div>
						<div class="actions">
						<div class="btn-group">
							<a href="#" class="btn blue" onclick="callpickup()"> <i
								class="fa fa-eye"></i> Etm Recevied
							</a>
							
							<a href="#" class="btn green" onclick="callCancel()"> <i
								class="fa fa-eye"></i> Cancel
							</a>

						</div>
						</div>		
					</div>
					<div class="portlet-body">
						<form action="" method="post">
							<b>
								<font color="green"> <s:actionmessage/></font>
								<font color="red"> <s:actionerror/></font>
							</b>
							<%-- <table class="table table-striped table-bordered table-hover">
								<tr>
								<td colspan="1">Issuer Name</td>
								<td><input type="text" class="form-control" name="issuer" id="issuer" value='<s:property value="issuer" /> '></td>
								<td colspan="1">Receiver Name</td>
								<td><input type="text" class="form-control" name="receiver" id="receiver" value='<s:property value="receiver" /> '></td>
								</tr>
							</table> --%>						
							<table class="table table-striped table-bordered table-hover"
								id="etmrecfromverifTable">
								
								<thead>
									<tr>
								<!-- 		<th><input type='checkbox' id='selectAll'  onclick="selectall()"/>&nbsp;&nbsp;</th> -->
										<th></th>
										<th>Id</th>
										<th>ETM S/N</th>
										<th>SIM No</th>
										<th>Issue Log Date</th>
										<th>ETM Problem</th>
										
									</tr>
								</thead>
							</table>
							
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
		 <div style="display: none;" id="mymodal1" class="modal fade"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
		aria-hidden="true">
		<div class="modal-dialog" id="mod21">
			<div class="modal-content" style="width: 600px; height: 600px;"
				align="justify">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 id="vehicleno123" class="modal-title"></h4>
				</div>
				<div>
					<p>
					<div class="portlet box white ">
						<div>
						<h4><span class="help-block" style="color: green; list-style: none"></span></h4>
							<input type="hidden" name="shift" id="shift" />
	
							<div>
							
								<div class="portlet solid white">
									<div class="form-group">
										<label class="col-md-3 control-label"></label>
									</div>
									<div class="form-group"><form  method="post" class="form-horizontal">
									<table id ="tableid1">		
									<div>
<!-- 									<input type="hidden" name="scheduleId" id="scheduleId" /></div>						 -->
																			
											<div class="form-group">
												<label class="col-md-3 control-label">Id</label>
												<div class="col-md-4">
													<input type="text" class="form-control" id="etmidassign"
														name="etmidassign" readonly="readonly" value=''></input>
												</div>
											</div>
										
								
											
				           <div class="form-group" id="resDescreption">
									<label class="col-md-3 control-label">Etm Status:<font
										color="red"></font></label>
										<div class="form-group">
								
										<div class="col-md-4"> 
										<select id="statusid"  class="select2_category form-control" name="statusid"">
						                <option value="0">---select---</option>
											<option value="Resolved">Etm Issues are resolved</option>
											<option value="Not Resolved">Etm Issues are not Solved</option>
																              
							           </select>
					          </div>
				            </div>
											</div></table></form>
						   <div align='center' id="submitbutten">
					<button type="button" class="btn green" onclick="savedata();">Save</button> 
<!-- 					<button type="button" id="btnExport" class="btn green" onclick="callCancell();">Cancel </button> -->
                   <%-- <span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span>&nbsp;<span>
					 <input type='button' class='btn green' id='button1' name='rawprint' onclick='rawPrint()' value='RawPrint' /></span></div> --%>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>  
						</div></div>			</div>
								</div>
							</div>
						</div>
						</p>
					</div>
				</div>
			</div>
			
</div>
<!-- <SCRIPT type="text/javascript">
	$("#selectAll").change(function(){  //"select all" change
		
	    var status = this.checked; // "select all" checked status
	    $(':checkbox').each(function(){ //iterate all listed checkbox items
	    	//alert("inside");
	        this.checked = status; //change ".checkbox" checked status
	    });
	});

	$('.checkbox').change(function(){ //".checkbox" change
		//alert(2);
	    //uncheck "select all", if one of the listed checkbox item is unchecked
	    if(this.checked == false){ //if this item is unchecked
	        $("#selectAll")[0].checked = false; //change "select all" checked status to false
	    }
	   
	    //check "select all" if all checkbox items are checked
	    if ($(':checkbox:checked').length == $('.checkbox').length ){
	    	//alert(3);
	        $("#selectAll")[0].checked = true; //change "select all" checked status to true
	    }
	}); 

</SCRIPT> -->

<script>
function callpickup(){
	/* var issuer=$("#issuer").val();
	var receiver=$("#receiver").val();
	
	var issue = issuer.replace(/^\s+/g, '');
	var receive = receiver.replace(/^\s+/g, ''); */
	//alert(issue);
	var cnt = $(":checkbox:checked").length;
	var val;
	var data=new Array();
	var i=0;
	/* if(issue==null || issue==""){
		bootbox.alert("Please enter Issuer Name");
	}
	else if(receive==null || receive==""){
		bootbox.alert("Please enter Receiver Name");
	} */
	 if (cnt == 0) {
		bootbox.alert("Please Select Checkbox To print");
	} 
	/* else if (cnt > 6) {
		bootbox.alert("Please Select Six Checkbox To Service Ticket");
	} */
	else {
		$("input[type='checkbox']:checked").each(
				function() {
                    
					val = $(this).val();
//						id=$("#vechileid").val();
//						id=$("#checkbox").val();
					//alert(val);
					data[i]=val;
					i++;
//						alert(data);
				});
		
		$('#etmidassign').val(val);
		 $("#mymodal1").modal('show');
		//alert("data=="+data);
	/* 	document.forms[0].action = 'receiveETMFromVerifone.action?value='+data,
		document.forms[0].submit(); */
	
}
}
function callCancel(){
	
	window.location.href='getEtmDeviceView.action';
}

</script>
<script>
function savedata(){
	var status=$("#statusid").val();
	if(status==0){
		alert("Please Select ETM Status");
	}
	else{
	var val;
	 $("#mymodal1").modal('hide');
	 $("input[type='checkbox']:checked").each(
				function() {

					val = this.value;
				});
	
/* 
	alert(val);
	alert(status); */
	
	   document.forms[0].action = 'receiveETMFromVerifone.action?value='+val+'&status='+status,
	document.forms[0].submit(); 
	}
}
</script>
</body>