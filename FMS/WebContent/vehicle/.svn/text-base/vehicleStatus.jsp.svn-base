<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//www.google.com/jsapi"></script>

<%-- <script src="assets/vts/js/scheduleroute.js"></script> --%>
<script src="assets/vts/js/vehiclealert.js"></script>
 <script src="assets/global/plugins/jquery-1.11.0.min.js"
	type="text/javascript"></script>
<script src="assets/vts/js/scheduledeviation.js"
	type="text/javascript"></script>
	
<%-- <script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places,drawing,geometry"></script>
 --%>
 
 <script>
 function getValue()
 {
	 var values = $('#Causereason :selected').val();
//      alert("valuesssssssssss"+values);
 }
 
 
 function formSubmit()
 {
      document.getElementById("myForm").submit();
 } 
 
function hidedata(){
	
	$(CWReason).hide();
	$(DWReason).hide();
	$(accidentReason).hide();
	$(breakdownReason).hide();
	$(NormalReason).hide();
	$("#place").hide();
// 	$(scrapReason).hide();
}
window.onload=hidedata;
</script>
<script>
var val;
function checkOption() {
	
	var val=document.getElementById("causestatus").value;
    if(document.getElementById("causestatus").value == 0 || document.getElementById("causestatus").value == -1|| document.getElementById("causestatus").value == 5) //0- Normal
    {
    	 $("#CWReason").hide();
    	 $("#DWReason").hide();
    		$("#roadWorthyReason").hide();
    	 $("#accidentReason").hide();
    	 $("#breakdownReason").hide();
    	 $("#NormalReason").hide();
    	 $("#place").hide();
    	 $("#cwPlaceDiv").hide();

//     	 $("#scrapReason").hide();
     }
    else if(document.getElementById("causestatus").value == 1) //"Central Workshop"
    {
    	 $("#CWReason").show();
    	 $("#DWReason").hide();
    		$("#roadWorthyReason").hide();
    	 $("#accidentReason").hide();
    	 $("#breakdownReason").hide();
    	 $("#NormalReason").hide();
    	 $("#place").hide();
    	 $("#cwPlaceDiv").show();
//     	 $("#scrapReason").hide();
   }
    else if(document.getElementById("causestatus").value == 2) //"Depot Workshop" 
    {
    	$("#DWReason").show();
    	$("#roadWorthyReason").hide();
    	$("#CWReason").hide();
    	 $("#accidentReason").hide();
    	 $("#breakdownReason").hide();
    	 $("#NormalReason").hide();
    	 $("#place").hide();
    	 $("#cwPlaceDiv").hide();
//     	 $("#scrapReason").hide();
    }
    else if(document.getElementById("causestatus").value ==3) // "Accident"
    {
     $("#accidentReason").show();
 	$("#roadWorthyReason").hide();
   	 $("#CWReason").hide();
   	 $("#NormalReason").hide();	 
   	 $("#breakdownReason").hide();
   	 $("#DWReason").hide();
   	$("#place").hide();
	 $("#cwPlaceDiv").hide();
//    	 $("#scrapReason").hide();
    }
    else if(document.getElementById("causestatus").value ==4){    // "Breakdown"
   	 $("#breakdownReason").show();
 	$("#roadWorthyReason").hide();
    	$("#accidentReason").hide();
      	 $("#CWReason").hide();
      	 $("#NormalReason").hide();	 
      	 $("#DWReason").hide();
      	$("#place").hide();
   	 $("#cwPlaceDiv").hide();
//       	$("#scrapReason").hide();
    }
    
    else if(document.getElementById("causestatus").value ==6){    // "Road Worthy Spare"
    	$("#place").hide(); 
    	$("#roadWorthyReason").show();
    	$("#breakdownReason").hide();
       	$("#accidentReason").hide();
         	 $("#CWReason").hide();
         	 $("#NormalReason").hide();	 
         	 $("#DWReason").hide();
        	 $("#cwPlaceDiv").hide();
//          	$("#scrapReason").hide();
       }
    else if(document.getElementById("causestatus").value ==7){    // "Authorized dealer"
     	 
    	$("#place").show();
    	$("#roadWorthyReason").hide();
    	$("#breakdownReason").hide();
       	$("#accidentReason").hide();
         	 $("#CWReason").hide();
         	 $("#NormalReason").hide();	 
         	 $("#DWReason").hide();
        	 $("#cwPlaceDiv").hide();
//          	$("#scrapReason").hide();
       }
    else if(document.getElementById("causestatus").value ==8){    // "Authorized dealer"
    	 
    	$("#place").show();
    	$("#roadWorthyReason").hide();
    	$("#breakdownReason").hide();
       	$("#accidentReason").hide();
         	 $("#CWReason").hide();
         	 $("#NormalReason").hide();	 
         	 $("#DWReason").hide();
        	 $("#cwPlaceDiv").hide();
//          	$("#scrapReason").hide();
       }
    else if(document.getElementById("causestatus").value ==9 || document.getElementById("causestatus").value ==10 ){    // "Authorized dealer"
    	 
    	$("#place").hide();
    	$("#roadWorthyReason").hide();
    	$("#breakdownReason").hide();
       	$("#accidentReason").hide();
         	 $("#CWReason").hide();
         	 $("#NormalReason").hide();	 
         	 $("#DWReason").hide();
        	 $("#cwPlaceDiv").hide();
//          	$("#scrapReason").hide();
       }
    else if(document.getElementById("causestatus").value ==11  ){    // "Authorized dealer"
   	 
    	$("#place").show();
    	$("#roadWorthyReason").hide();
    	$("#breakdownReason").hide();
       	$("#accidentReason").hide();
         	 $("#CWReason").hide();
         	 $("#NormalReason").hide();	 
         	 $("#DWReason").hide();
         	$("#cwPlaceDiv").hide();
//          	$("#scrapReason").hide();
       }
}
 
 </script>
 
<Script>
var counter=0;

 
	 function validate()
     {
		 
//         alert("hii");
		 var divisionlist=document.getElementById('divisionlist').value;
		 var depotlist=document.getElementById('depotlist1').value;
		 var vehiclelist=document.getElementById('vehiclelist').value;
		 var causestatus=document.getElementById('causestatus').value;
// 		 var reason=document.getElementById('Causereason').value;
		 var place =document.getElementById('placeId').value;
// 		 alert("reason is"+causestatus+"======"+place);
    
        
        if( divisionlist == "0" )
        {
//            alert( "Please select vehicle" );
            bootbox.alert("Please select division");
           return false;
        }
        if( depotlist == "0" )
        {
           //alert( "Please select vehicle" );
            bootbox.alert("Please select depot");
           return false;
        }
        if( vehiclelist == "0" )
        {
           //alert( "Please select vehicle" );
            bootbox.alert("Please select vehicle");
           return false;
        }
        if( causestatus == "-1" )
        {
           bootbox.alert("Please select cause");
           return false;
        }
        
        if(causestatus==1){
        	var reason=document.getElementById('CWReasonName').value;
        	var CWPlace =document.getElementById('CWPlace').value;
//         	alert(CWPlace);
        	if(reason==-1  ){
        		 bootbox.alert("Please select Central Workshop Reason");
        		 return false;
        	}
        	if(CWPlace==0){
       		 bootbox.alert("Please select Central Workshop Place");
       		 return false;
       	}	
        }
        
        if(causestatus==2){
        	var reason=document.getElementById('DWReasonname').value;
        	if(reason==-1){
        		 bootbox.alert("Please select Depot Workshop Reason");
        		 return false;
        	}
        }
        
        if(causestatus==3){
        	var reason=document.getElementById('accidentReasonlist').value;
        	if(reason==-1){
        		 bootbox.alert("Please select Accident Reason");
        		 return false;
        	}
        }
        
        if(causestatus==4){
        	var reason=document.getElementById('breakdownReasonlist').value;
        	if(reason==-1){
        		 bootbox.alert("Please select Break down Reason");
        		 return false;
        	}
        }
		   
       
        if(causestatus==6){
        	var reason=document.getElementById('roadWorthyReasonlist').value;
        	if(reason==-1){
        		 bootbox.alert("Please select Road Worthy Reason");
        		 return false;
        	}
        }
        
        
        if( causestatus==7 || causestatus==8 || causestatus==11){
        	if(place==""){
        		bootbox.alert("Please insert Place ");
        		return false;
        	}
        }
        return( true );
        
     }
// 	 function getStatusList(){
// 		 $('#select2-chosen-2').html("Select");
// 		 $('#select2-chosen-3').html("Select");
// 			//alert('Here');
// 			 /* var selectedValue = $('#form-control').val(); */
// 		        $.ajax({
// 		            type: "post",
<%-- 			        url: '<%=request.getContextPath()%>/getStatus', --%>
// 					success : function(result) {
// 						document.getElementById('causestatus').innerHTML = result;
// 					}
// 				});

// 		}



	 function getCWPlace(CwId){
// 		 $('#select2-chosen-2').html("Select");
// 		 $('#select2-chosen-3').html("Select");
			//alert('Here');
			 /* var selectedValue = $('#form-control').val(); */
			 var val=document.getElementById('causestatus').value;
				 if(val!=0) {
		        $.ajax({
		            type: "post",
		            url: '<%=request.getContextPath()%>/getCWPlaceDropdown?val=' + val,
					success : function(result) {
						document.getElementById('CWPlace').innerHTML = result;
// 						getVehicle("");
					}
				});
			}

		}
	 
	 
 function getDepot(orgId){
	 $('#select2-chosen-2').html("Select");
	 $('#select2-chosen-3').html("Select");
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 var val=document.getElementById('divisionlist').value;
			 if(val!=0) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/getDepot?val=' + val,
				success : function(result) {
					document.getElementById('depotlist1').innerHTML = result;
					getVehicle("");
				}
			});
		}

	}
 
 function getVehicle(depotID)
 {
 	$('#select2-chosen-3').html("Select");
 	var val=document.getElementById('depotlist1').value;
 	 if(val!=0) {
 		 $("#msg").html("Please wait...");
    	$.ajax({
 	   type: "POST",
        url: '<%=request.getContextPath()%>/getLiveVehicle1?val='+val,
        success: function(result) {
     	   $("#msg").html("");
     	   document.getElementById('vehiclelist').innerHTML=result;
        }
    });
 }
 }

<%-- 	function getStatus(){
	 		 $("#msg").html("Please wait...");
	    	$.ajax({
	 	   type: "POST",
	        url: '<%=request.getContextPath()%>/getStatus',
	        success: function(result) {
	     	   $("#msg").html("");
	     	   document.getElementById('vehiclelist').innerHTML=result;
	        }
	    });
	} --%>
	

 </Script>
	<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "VehicleDetails.action");
/* String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
String status=accessrightdao.UserStatus(usrsessionid); */
int rollid=accessrightdao.getroleid(usrsessionid);
System.out.println("user role id---------"+rollid);
%>
<title>Insert title here</title>
</head>
<body>
	<div class="page-content-wrapper">
		<div class="page-content">
	
			<div class="tab-content">

				<div class="row">
					<div class="col-md-12">
						<!-- BEGIN PAGE TITLE & BREADCRUMB-->
						<h3 class="page-title">
							VEHICLE STATUS <small></small>
						</h3>

						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Vehicle Status
							</div>
							<!-- <div class="tools">
								<a href="javascript:;" class="collapse"> </a> 
								 <a href="javascript:;" class="reload"> </a> -->
							</div> 
						</div>

						<div class="portlet-body form">

							<div class="col-md-12" align="left" style="font-size: 1.1em">

<%-- 								<span class="help-block" style="color: red; list-style: none"><s:actionerror /></span> --%>
								<h4><span class="help-block" style="color: green; list-style: none"><s:actionmessage /></span></h4>
							</div>
							<!-- BEGIN FORM-->
							
				 <form action="vehicleStatussave" method="post" class="form-horizontal" name="myForm" id="myForm" onsubmit="return validate();">

								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Division<font
											color="red">*</font></label>
										<div class="col-md-4">
											<s:select list="divisionlist" id="divisionlist" 
											name="org_chart_id"  
												cssClass="select2_category form-control"  
												 onchange="getDepot(this.value)" headerKey="0" headerValue="division"></s:select>  
												 
										</div>
									</div>
								</div>
								<script>
					//getDepot("");
					</script>

								<div class="form-group">
									<label class="col-md-3 control-label">Depot<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select list="depotlist" id="depotlist1" class="select2_category form-control" name="depotlist1"
 											onchange="getVehicle(this.value)"> 
											<option value="0">Depot</option>
 										</select> 
									</div>
 								</div> 
                              <div class="form-body"> 
								<div class="form-group">
									<label class="col-md-3 control-label">Vehicle No<font
										color="red">*</font></label>
									<div class="col-md-4">
						                <select list="vehiclelistname" id="vehiclelist"  class="select2_category form-control" name="vehiclelist"
 							              > 
							              <option value="0">Vehicle Number</option>
 							           </select> 
<%--                                     <s:select list="vehiclelistname" id="vehiclelist"   --%>
<%-- 												name="vehiclelist"  --%>
<%--  												cssClass="select2_category form-control"  --%>
<%-- 												 onchange="getDepot(this.value)" headerKey="0" headerValue="select"></s:select>  --%>
					
					          </div>
				            </div>
				            <%
				            	//String status = (String)request.getAttribute("myStatus");
				               // out.print("Status : " + status);
				               
				            %>
				            
				            <div class="form-group" id="status">
									<label class="col-md-3 control-label">Status<font
										color="red">*</font></label>
									<div class="col-md-4">
						                <select  id="causestatus"  class="select2_category form-control" name="causestatus" onchange="checkOption(this)">
						                <option value="-1">select</option>
							              <option value="0">Normal</option>
							              <option value="1">Central Workshop</option>
							              <option value="2">Depot WorkShop</option>
							              <option value="3">Accident</option>
							              <option value="4">Breakdown</option>
<%-- 							              <%if(rollid==5){ %> --%>
<!-- 							               <option value="5">Scrap</option> -->
<%-- 							               <%} %> --%>
							                 <option value="6">Road Worthy Spare</option>
							                   <option value="7">Vehicle Held at Authorized Dealer</option>
							                     <option value="8">Vehicle Held at Police Station</option>
							                       <option value="9">Docking</option>
							                         <option value="10">Scrap Proposal</option>
							                           <option value="11">Vehicle went on CC</option>
							           </select>
							           <script>
											var orgTypeId ="<s:property value='causestatus'/>";
											if(orgTypeId!=""){
 												document.getElementById(orgTypeId).selected= true; 
												
											}
											
											
										</script>
					</div>
											</div>
											
								<div class="form-group" id="NormalReason">
									<label class="col-md-3 control-label">Reason<font
										color="red">*</font></label>
									<div class="col-md-4">
						                <select id="Causereason" class="select2_category form-control" name="Causereason" >
						                <option value="-1">---select---</option>	
						                </select>
						                </div>
						             </div>		
											
											
											 <div class="form-group" id="CWReason">
									<label class="col-md-3 control-label">Reason<font
										color="red">*</font></label>
									<div class="col-md-4">
						                <select id="CWReasonName"  class="select2_category form-control" name="CWReasonName" onChange="getCWPlace(this.value)">
						                <option value="-1">---select---</option>
											<option value="Accident Repair">Accident Repair</option>
											<option value="FC">FC</option>
											<option value="HBR">HBR</option>							              
							           </select>
							          
					</div>
											</div>
											
											
											
								<div class="form-group" id="cwPlaceDiv" style="display: none">
									<label class="col-md-3 control-label">CW Place<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select list="CWPlace" id="CWPlace" class="select2_category form-control" name="CWPlace"> 
											<option value="0">--Select--</option>
 										</select> 
									</div>
 								</div> 
											
											
											
									 <div class="form-group"  id="DWReason">
									<label class="col-md-3 control-label">Reason<font
										color="red">*</font></label>
									<div class="col-md-4">
						                <select  id="DWReasonname" class="select2_category form-control" name="DWReasonname">
						                <option value="-1">---select---</option>
										<option value="Want Of Engine">Want Of Engine</option>
										<option value="For Engine Work">For Engine Work</option>
									<option value="Want of Spare and Assemble">Want of Spare and Assemble</option>
										
										</select>
							          
					</div>
											</div>		
											
											 <div class="form-group" id="accidentReason">
									<label class="col-md-3 control-label">Reason<font
										color="red">*</font></label>
									<div class="col-md-4">
						                <select id="accidentReasonlist" class="select2_category form-control" name="accidentReasonlist">
						                <option value="-1">---select---</option>
						                 <option value="Fatal">Fatal</option>
							              <option value="Major">Major</option>
							              <option value="Minor">Minor</option>
							              <option value="Other">Other</option>
							           </select>
							          
					</div>
											</div>		
											
											
											 <div class="form-group" id="breakdownReason">
									<label class="col-md-3 control-label">Reason<font
										color="red">*</font></label>
									<div class="col-md-4">
						                <select id= "breakdownReasonlist" class="select2_category form-control" name="breakdownReasonlist">
											<option value="-1">---select---</option>
											<option value="Cooling System">Cooling System</option>
											<option value="Electric Work">Electric Work</option>
											<option value="Engine">Engine</option>
											<option value="Transmission">Transmission</option>
											<option value="Tyres">Tyres</option>

										</select>
							          
					</div>
											</div>		
											
											
															 <div class="form-group" id="roadWorthyReason">
									<label class="col-md-3 control-label">Reason<font
										color="red">*</font></label>
									<div class="col-md-4">
						                <select id= "roadWorthyReasonlist" class="select2_category form-control" name="roadWorthyReasonlist">
											<option value="-1">---select---</option>
											<option value="Unplanned Cancel">Unplanned Cancel</option>
											<option value="Spare">Spare</option>
											<option value="Want of Driver">Want of  Driver</option>
											<option value="Want of Conductor">Want of Conductor</option>

										</select>
							          
					</div>
											</div>		
											
											
																				 <div class="form-group" id="place">
									<label class="col-md-3 control-label">Place :<font
										color="red">*</font></label>
									<div class="col-md-4">
						        <input type="text" name="placeId" id="placeId" placeholder="Enter Place" style="width: 300px;"><br>
							          
					</div>
											</div>		
											
											
										<%-- 	 <div class="form-group" id="scrapReason">
									<label class="col-md-3 control-label">Reason<font
										color="red">*</font></label>
									<div class="col-md-4">
						                <select id= "Causereason" class="select2_category form-control" name="Causereason">
											<option value="-1">---select---</option>
											</select>
											</div></div>
 --%>					                       	
									<div class="form-group">
									
									<label class="col-md-3 control-label">Remark
										</label>
										<div class="col-md-4"> 
										<textarea  class="form-control" id="notes" maxlength="100"	name="notes" autofocus="autofocus">
													<s:property value="notes" /></textarea>	
										
											
								
					          </div>
				            </div>
											
										
									</div>
									<div class="form-group">
<!-- 					          <div class="col-md-1" id=""> -->
<!-- <!-- 									<button type="button" class="btn default"style="position: static;">Submit</button> --> 
<!-- 									<button type="submit" class="btn blue">Save</button> -->
<!-- 									</div> -->
				            </div>

                              <div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn blue" onClick="getValue()">Submit</button>
<!-- 										<button type="button" class="btn default" onclick="goView()">Cancel</button> -->
									</div>
								</div>
                              

                          </form>
							</div>	
									
																		
							
							<!-- END FORM-->
					
				</div>
			</div>
		</div>
	</div>


</body>
</html>