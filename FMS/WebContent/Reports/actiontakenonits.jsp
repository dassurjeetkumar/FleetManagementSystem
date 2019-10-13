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
	            url: '<%=request.getContextPath()%>/Ajaxgetdepot?val=' + val,
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
	            url: '<%=request.getContextPath()%>/Ajaxgetemplyeename?val=' + val,
				success : function(result) {
					document.getElementById('Emplyeelist').innerHTML = result;
					
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
	            url: '<%=request.getContextPath()%>/Ajaxgettokenno?val=' + val,
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
	   var monthofperform=$("#startdate").val();
	   monthofperform="00-"+monthofperform; 
		var fault=$("#faultname").val();
		var remark=$("#notes").val();
 		var actiontaken=$("#actiontaken").val();
 		var refno=$("#refno").val();
 		var dateaction=$("#enddate").val();
 		/* var monthofperform =new monthofperform[2];
 		var dateofaction=new dateofaction[2];
 		monthofperform=monthperform.split(":");//var change
 		monthperform=monthofperform[1]+"-"+monthofperform[0]+"-00";
 		//var var2=$("#end_time").val();
 		dateofaction=dateaction.split(":");
 		dateaction=dateofaction[2]+"-"+dateofaction[1]+"-"+dateofaction[0];  */
		//alert(var1+""+var2);
		
		 //var d1 = Date.parse(var1);

	//alert(remark);	 
 	//var d3=Date.parse(var2);
if (div ==0 ){
 			 //$('#pageLoader').show();
 			alert("please select division");
        } else if(depot==0){alert("please select depot");}
        else if(empno <1){alert("please select token number");}
        else if(monthofperform ==null){alert("please select month of perform");}
        else if(fault ==-1){alert("please select fault");}
        else if(remark ==""){alert("remark can not empty");}
        else if(remark ==""){alert("remark can not empty");}
        else if(remark.length <=2){alert("remark must be more than two characters");}
        else if(actiontaken ==-1){alert("please select action taken");}
        else if(refno ==""){alert("ref no can not be empty");}
       // else if(dateaction !=null){alert("please select date of action");}
        else if(div !=0 & depot!=0 & empno >1 & monthofperform !=null & fault !=-1 & remark !="" & remark.length >=2 & actiontaken !=-1 & refno !="" & dateaction !=null){
 			       	
 			//alert("All Columns Must Be Fill");
 			//$('#pageLoader').hide();
 			document.getElementById("actionform").submit();
		}
		
}

</script>


<script>
 function callCancell() {

	window.location.href = 'actiontotakenbasedonitsreports';
	
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
								<i class="fa fa-globe"></i>Action to Taken  Based on ITS Reports
							</div>
						</div>
						
						<div class="portlet-body">
						

							<!-- 						start -->

<!-- <table> -->
                   <form action="Ajaxsaveactiontakenits" method="post" class="form-horizontal" id="actionform">
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
								
<!--                              	<div class="form-group"> -->
<!-- 										<label class="col-md-3 control-label">Depot<font -->
<!-- 											color="red">*</font></label> -->
<!-- 										<div class="col-md-3"> -->
<%-- 											<select list="depotlist" id="depotlist1" --%>
<%-- 												class="select2_category form-control" name="depot" --%>
<%-- 													 onchange="gettokenno(this.value)"> --%>
<!-- <!-- 												<option value="-1">select</option> --> 
<%-- 											</select> --%>
<!-- 										</div> -->
<!-- 									</div> -->
 							<!-- 	<option value="0">--select--</option> -->
 								<div class="form-group">
									<label class="col-md-3 control-label">Token no<font
										color="red">*</font></label>
									<div class="col-md-3">
										<select list="empno" id="empno" class="select2_category form-control" name="tokennumber"
									 onchange="getEmplyee(this.value)">
										 <option value="-1">--select--</option> 
											 
 										</select> 
									</div>
 								</div> 
								<div class="form-group">
									<label class="col-md-3 control-label">Emplyee Name<font
										color="red">*</font></label>
									<div class="col-md-3">
									 	<select id="Emplyeelist" class="select2_category form-control"> 
									
										<!--  onchange="gettokenno(this.value)"> -->
										  <option value="-1">--Check Employee Name With Excesting  Token No--</option> 
 										</select> 
									</div>
 								</div> 
 							
 										<div class="form-group">
									<label class="control-label col-md-3">Month of Performance<font
										color="red">*</font></label>
										<div class="col-md-4">
											<div class="input-group input-medium date date-picker"
												style="width: auto" data-date-format="mm-yyyy"
												data-date-viewmode="years">
												<input id="startdate" name="monthofperform" class="form-control" type="text"
													readonly="" size="16" />
												<span class="input-group-btn">
													<button class="btn default date-set" type="button">
														<i class="fa fa-calendar"></i>
													</button>
												</span>
												<script>
													var curr_date = new Date().toJSON().slice(0,10);
													curr_date = curr_date.split("-");
													curr_date = curr_date[1]+ "-"+ curr_date[0];
													var dtval = document.getElementById('startdate').value;
													if (dtval == '') {
														$('#startdate').val(curr_date);
													}
												
												</script>
											</div>
										</div>
										
								</div>
 										 <div class="form-group" id="CWReason">
									<label class="col-md-3 control-label">Fault<font
										color="red">*</font></label>
									<div class="col-md-4">
						                <select id="faultname"  class="select2_category form-control" name="fault">
						                <option value="-1">---select---</option>
											<option value="LateDeparture">Late Departure</option>
											<option value="EarlyArrival">Early Arrival</option>
											<option value="RouteDeviation">Route Deviation</option>
											<option value="SkipStop">Skip Stop</option>
											<option value="OverSpeed">Over Speed</option>
											<option value="RashDriving">Rash Driving</option>
											<option value="CancelTrip">Cancel Trip</option>
											<option value="TripNotCompleted">Trip Not Completed</option>
											<option value="LowRevenue">Low Revenue</option>
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
				            		 <div class="form-group" id="CWReason">
									<label class="col-md-3 control-label">Action Taken<font
										color="red">*</font></label>
									<div class="col-md-4">
						                <select id="actiontaken"  class="select2_category form-control" name="actiontaken">
						                <option value="-1">---select---</option>
											<option value="Fine">Fine</option>
											<option value="Memo">Memo</option>
											<option value="Warning">Warning</option>
											<option value="ChargeSheet">Charge Sheet</option>
										
											<option value="Other">Other</option>							              
							           </select>
							          
					</div>
											</div>
				            <div class="form-group">
									
									<label class="col-md-3 control-label">Refrence No<font
										color="red">*</font></label>
										<div class="col-md-4"> 
										<input type=text name="refno" class="form-control" id="refno" maxlength="100"	 autofocus="autofocus">
													<s:property value="text" /></input>	
										
											
								
					          </div>
				            </div>
				             <div class="form-group">
							  <label class="control-label col-md-3">Date Of Action Taken<font
										color="red">*</font></label>
								<div class="col-md-3">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years"  data-date-end-date="-2d"> 
								<input id="enddate" name="dateaction" class="form-control" type="text" readonly="" size="16" name="rateMaster.effectiveStartDate"
								value="<s:property value='rateMaster.effectiveStartDate' />"
								>
								<span class="input-group-btn">
								<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
								</button>
								</span>
								<script>
 										var curr_date=new Date().toJSON().slice(0,10);
                                        curr_date=curr_date.split("-");
                                         curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
                                        var dtval=document.getElementById('enddate').value;	
                                        
                                         if(dtval==''){
                                         $('#enddate').val(curr_date);
                                        }
								</script>
								</div>
								</div></div>
 					
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

	
	
	
