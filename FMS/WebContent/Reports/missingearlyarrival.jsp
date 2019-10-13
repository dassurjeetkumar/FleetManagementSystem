  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
 
<script>
function resetDate(){
	 $('#startdate').val(null);
}
var i=0;
function getDepot(orgId){
	 $('#select2-chosen-2').html("Select");
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 var val=document.getElementById('divisionlist').value;
			 if(val!=0) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/AjaxgetDepotfrommissingearlyarrival?val=' + val,
				success : function(result) {
					document.getElementById('depotlist').innerHTML = result;
				}
			});
		}

	}

function savereport(){
	var div=$("#divisionlist").val();
	 
	   var depot=$("#depotlist").val();

	   var date=$("#startdate").val();
	if (div ==0 ){
		alert("please select division");
   } else if(depot==-1){
	   alert("please select depot");
	   }else if(date==''){
	   alert("please pick the date");
	   }
   else{
	   document.getElementById("missingearlyarrival").submit();
   }
	}
</script>

 <script>
 function callCancell() {

	window.location.href = 'missingearlyarrival';
	
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
								<i class="fa fa-globe"></i>Missing Early Arrival
							</div>
						</div>
						
						<div class="portlet-body">
						

							<!-- 						start -->

<!-- <table> -->
                   <form action="savemissingearlyarrival" method="post" class="form-horizontal" id="missingearlyarrival">
                   <b>
								<font color="green"><h3> <s:actionmessage/></h3></font>
								<span id="msg" style="color:red;word-wrap: break-word;" ><%-- <s:actionerror/> --%>
								</span>
							</b>
                           <div class="form-body">
									
                             <div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Division<font
											color="red">*</font></label>
										<div class="col-md-3">
											<s:select list="divisionlist" id="divisionlist"
												name="division"
												cssClass="select2_category form-control" headerKey="0" headerValue="---select---"
												 onchange="getDepot(this.value)"></s:select>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">Depot<font
										color="red">*</font></label>
									<div class="col-md-3">
										<select id="depotlist" class="select2_category form-control" name="depot"
											onchange="resetDate()">
											<option value="-1">---Select---</option>
										</select>
									</div>
								</div>
                          
							 <div class="form-group">
							  <label class="control-label col-md-3"> Date:</label>
								<div class="col-md-3">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" > <!-- data-date-start-date="+0d" -->
								<input id="startdate" class="form-control" type="text" readonly="" size="16" name="startdate" placeholder="please pick the date"
								value="<s:property value='rateMaster.effectiveStartDate' />"
								>
								<span class="input-group-btn">
								<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
								</button>
								</span>
								<%-- <script>
 										var curr_date=new Date().toJSON().slice(0,10);
                                        curr_date=curr_date.split("-");
                                         curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
                                         var dtval=document.getElementById('startdate').value;	
                                        
                                        if(dtval==''){
                                         $('#startdate').val(curr_date);
                                         }
								</script> --%>
								</div>
								</div></div>
				
							
					 <div class="form-group">
					 <label class="control-label col-md-3"></label>
                     <div class="col-md-3" id="">
                     </div>
                     </div>
                     </div>
                     </form>
                     <div align='center'>
					<button type="submit" class="btn green" onClick="savereport()">Save</button> 
			
                  <button type="submit" class="btn green" onClick="callCancell()">Cancel</button> 

			</div>
		
		</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	</head>
	</html>

	
	
	
