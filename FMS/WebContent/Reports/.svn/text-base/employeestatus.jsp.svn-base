  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
 
<script>

function getDepot(orgId){
	// $('#select2-chosen-2').html("Select");
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 var val=document.getElementById('divisionlist').value;
			 if(val!=0) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/ajaxemplgetdepot?val=' + val,
				success : function(result) {
					document.getElementById('depotlist1').innerHTML = result;
				}
			});
		}

	}
function getEmplyee(tokennumber){
	// $('#select2-chosen-2').html("Select");
	 //$('#select2-chosen-3').html("Select");
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 var val=document.getElementById('empno').value;
			 if(val!=0) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/ajaxstatusempname?val=' + val,
				success : function(result) {
					var res=document.getElementById('Emplyeelist').innerHTML = result;
					$('#Emplyeelist').val(res);
				}
			});
		}

	}
function getdesg(tokennumber){
	// $('#select2-chosen-2').html("Select");
	 //$('#select2-chosen-3').html("Select");
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 var val=document.getElementById('empno').value;
			 if(val!=0) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/ajaxempdesg?val=' + val,
				success : function(result) {
					var res=document.getElementById('Emplyeedeg').innerHTML = result;
					$('#Emplyeedeg').val(res);
				}
			});
		}

	}
function gettokenno(depo){
	// $('#select2-chosen-2').html("Select");
	// $('#select2-chosen-3').html("Select");
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 var val=document.getElementById('depotlist1').value;
			 if(val!=0) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/ajaxemptokenno?val=' + val,
				success : function(result) {
					document.getElementById('empno').innerHTML = result;
					
				}
			});
		}

	}

function check(){
	
	   var div=$("#divisionlist").val();
	 
	   var depot=$("#depotlist1").val();
	   var empno=$("#empno").val();
	   /* var monthofperform=$("#startdate").val(); */
	   //monthofperform="00-"+monthofperform; 
		var status=$("#empstat").val();
		//var remark=$("#notes").val();
 		//var actiontaken=$("#actiontaken").val();
 		//var refno=$("#refno").val();
 		//var dateaction=$("#enddate").val();
 	
if (div ==0 ){
 			 //$('#pageLoader').show();
 			alert("please select division");
        } else if(depot==0){alert("please select depot");}
        else if(empno==0){alert("please select token number");}
        else if(status==0){alert("please select the status");}
        else{
 	
 			document.getElementById("actionform").submit();
		}
		
}

</script>


<script>
 function callCancell() {

	window.location.href = 'EmployeeStatus';
	
}</script>
<div class="page-content-wrapper">
	<div class="page-content">
		<div id="pageLoader" class="modal fade in" tabindex="-1" role="dialog" aria-labelledby="myModalLabel3" aria-hidden="false">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<p>
							<img src="assets/images/loader1.gif" align="top"
								style="margin-left: 100px;" />
						</p>
						<p style='text-align: center'>Please Wait........</p>
					</div>
				</div>
			</div>
		</div>
			
			
			
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>Employee Status
							</div>
						</div>
						
						<div class="portlet-body">
						

							<!-- 						start -->

<!-- <table> -->
                   <form action="ajaxupdatestatusemp" method="post" class="form-horizontal" id="actionform">
                   <b>
								<font color="green"><h3> <s:actionmessage/></h3></font>
								<span id="msg" style="color:red;word-wrap: break-word;" ><s:actionerror/></span>
							</b>
                           <div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Division<font
											color="red">*</font></label>
										<div class="col-md-3">
											<s:select list="divisionlist" id="divisionlist" 
											name="division"  
												cssClass="select2_category form-control"  
												 onchange="getDepot(this.value)" headerKey="0" headerValue="--select--"></s:select>  
												 
										</div>
									</div>
										<div class="form-group">
									<label class="col-md-3 control-label">Depot<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select list="depotlist" id="depotlist1" class="select2_category form-control" name="depot"  onchange="gettokenno(this.value)"> 
											<option value="0">--select--</option>
 										</select> 
									</div>
 								</div> 
								

 								<div class="form-group">
									<label class="col-md-3 control-label">Token no<font
										color="red">*</font></label>
									<div class="col-md-3">
										<select list="empno" id="empno" class="select2_category form-control" name="tokennumber"
									 onchange="getEmplyee(this.value),getdesg(this.value)">
										 <option value="-1">--select--</option> 
											 
 										</select> 
									</div>
 								</div> 
								<div class="form-group">
									<label class="col-md-3 control-label">Emplyee Name<font
										color="red"></font></label>
									<div class="col-md-3">
									 	<input type="text" id="Emplyeelist" class="form-control" readonly="readonly" value=''/> 
									
									</div>
 								</div> 
 									<div class="form-group">
									<label class="col-md-3 control-label">Emplyee Designation<font
										color="red"></font></label>
									<div class="col-md-3">
									 	<input type="text" id="Emplyeedeg" class="form-control" readonly="readonly" value=''/> 
									
									</div>
 								</div> 
 							
 						
 										 <div class="form-group">
									<label class="col-md-3 control-label">Emp Status:<font
										color="red">*</font></label>
									<div class="col-md-4">
						                <select id="empstat"  class="select2_category form-control" name="status">
						                <option value="0">---select---</option>
											<option value="Retired">Retired</option>
											<option value="Dismiss">Dismiss</option>
											<option value="Death">Death</option>
											<option value="Transferred">Transferred</option>
											
											<option value="Resigned">Resigned</option>
											<option value="Promotion">Promotion</option>	
											<option value="Other">Other</option>					              
							           </select>
							          
					</div>
											</div>
											             	
									<div class="form-group">
									
									<label class="col-md-3 control-label">Remark
										</label>
										<div class="col-md-4"> 
										<textarea  class="form-control" id="notes" maxlength="100"	name=remark autofocus="autofocus">
													<s:property value="notes" /></textarea>	
										
											
								
					          </div>
				            </div>
				            	
				          
				         
 					
										<div class="col-md-offset-3 col-md-9">
											<button type="button" class="btn blue" onclick="check()">Save</button>
							<button type="button" class="btn blue"	onclick="callCancell()">Cancel</button>
										</div>
									
			  </div>
					    </form>

			</div>  
			
		
		</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	</head>
	</html>

	
	
	
