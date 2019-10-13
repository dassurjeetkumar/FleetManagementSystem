<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.trimax.its.model.User"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>


<!DOCTYPE html>
<html>
<head>
 <style>
.ui-autocomplete {
max-height: 100px;
overflow-y: auto;
/* prevent horizontal scrollbar */
overflow-x: hidden;
/* add padding for vertical scrollbar */
    padding-right: 5px;
}
/* IE 6 doesn't support max-height
* we use height instead, but this forces the menu to always be this tall
*/
 html .ui-autocomplete {
height: 100px;
}
</style>
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css"> 
<%-- <script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script> --%>
<link rel="stylesheet" href="/resources/demos/style.css">

<%-- <script type="text/javascript" src="//www.google.com/jsapi"></script> --%>
<script src="assets/global/plugins/jquery-1.11.0.min.js"
	type="text/javascript"></script>
<script>
function goView() {
	document.forms[0].action = 'viewVehicleSectorList.action';
	document.forms[0].submit();
}
<%-- function getRoadTypeId(){
	//alert('Here');
	 /* var selectedValue = $('#form-control').val(); */
	 
	 var len= document.getElementById('roadtypeid').options.length;

	 if(len<=1 ) {
        $.ajax({
            type: "get",
            url: '<%=request.getContextPath()%>/findRoadTypeIdAction',
            success: function(result) {
                document.getElementById('roadtypeid').innerHTML=result;
            }
        });
	 }
	// alert("end");
}
 --%>


 function getDepot(orgId){
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 var val=document.getElementById('divisionlist').value;
		 document.getElementById("select2-chosen-2").innerHTML = "Select";
		 document.getElementById('vehicleList').innerHTML = "Select";
			 if(val!=0) {
	        $.ajax({
	            type: "get",
	            url: '<%=request.getContextPath()%>/getDepot?val=' + val,
				success : function(result) {
					document.getElementById('depotlist').innerHTML = result;
				}
			});
		}

	}
 
 function  getVehicleList(orgId){
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		// var depotid=document.getElementById('depotlist').value;
		 var depotid=document.getElementById('organizationList').value;
		//  alert('Here'+depotid);
		
			
	        $.ajax({
	            type: "get",
	            url: '<%=request.getContextPath()%>/GetvehicleNameForSectors?depotid=' +depotid,
				success : function(result) {
					document.getElementById('vehicleList').innerHTML = result;
				}
			});
		

	}
 
 function saveVehicleMapping(){
	 /* var division=$("#divisionlist").val();
	 var depot=$("#depotlist").val(); */
	 var vehicle=$("#vehicleList").val();
	 var depot=document.getElementById('organizationList').value;
	 
     var  vehicleId =[];
     var sectorId=$("#sectorId").val();
     var id;
     var foo = []; 
     $('#vehicleList :selected').each(function(i, selected){ 
    	 vehicleId[i] = $(selected).val(); 
     });
    
     id=$("#vehicleList :selected").val();
   // alert(vehicle+"d"+depot);
    /*  if(division==0||division==null){
 		bootbox.alert("Please Select Division");
 	}else 
 		*/
 		if(depot==0||depot==null){
 		bootbox.alert("Please Select Organization Unit ");
 	}else  if(vehicle==0||vehicle=='undefined'||vehicle==null){
   
		bootbox.alert("Please Select Vehicle ");
	}else{
	 $.ajax({
         type: "get",
         url: '<%=request.getContextPath()%>/saveSectorVehicleRelation?sectorId='+sectorId+'&vehicleId='+vehicleId,
			success : function(result) {
				//alert(result);
				//document.getElementById("select2-chosen-2").innerHTML = "Select";
				//document.getElementById("depotlist").innerHTML = " ";
				document.getElementById('vehicleList').innerHTML ="Select";
				//$("#result").html(result);
				bootbox.alert(result);
				goView();
			}
		});
	}
 }
 


</script>


</script>
</head>
<body>
	

<div class="page-content-wrapper">

		<div class="page-content">

			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						ASSIGN VEHICLE TO SECTOR <small></small>
					</h3>
					
					<!-- END PAGE TITLE & BREADCRUMB-->
					
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Assign Vehicle To Sector :<s:property value="sectorName"/>
							</div>
							
						</div>
						
						<div class="portlet-body form">
						<s:if test="hasActionErrors()">
							<div class="alert alert-danger">
								<button class="close" data-close="alert"></button>
								<span> <s:actionerror />
								</span>
							</div>


						</s:if>
	
							<!-- BEGIN FORM-->
							<form action="saveSectorVehicleRelation.action" class="form-horizontal" method="post">
								
								<input type="hidden" name="SectorId"
													id="sectorId" class="form-control"
													value="<s:property value="sectorMapId"/>" maxlength="60">
							
									<div class="form-body">
								<div class="panel-body">
								
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Organization Unit<font
											color="red">*</font></label>
										<div class="col-md-4">
										 <s:select list="organizationUnit" id="organizationList"
												name="orgchart.org_chart_id"
												cssClass="select2_category form-control" headerKey="0"
												headerValue="Select" onchange="getVehicleList(this.value)"></s:select>
											
										</div>
									</div>
								</div>
								<%-- <div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Division<font
											color="red">*</font></label>
										<div class="col-md-4">
										 <s:select list="divisionlist" id="divisionlist"
												name="orgchart.org_chart_id"
												cssClass="select2_category form-control" headerKey="0"
												headerValue="Division" onchange="getDepot(this.value)"></s:select>
												<s:checkboxlist list="divisionlist" name="community" label="Community" />
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-3 control-label">Depot<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select id="depotlist" class="select2_category form-control" onchange="getVehicleList(this.value)" 
											>
											<option value="0">Depot</option>
										</select>
									</div>
								</div>
								</div> --%>
								
								<div class="form-group">
									<label class="col-md-3 control-label">Vehicle Registration Number:-<font
										color="red">*</font></label>
									<div class="col-md-6">
										<select id="vehicleList" class="form-control input-sm" multiple="multiple" style="width=400px" style="height=500px" size="10" >
											
											<!-- <option value="0">Vehicle</option> -->
										</select>
										 <span>To Select All Vehicles Please Press (Ctrl+Shift+End) </span>
									</div>
								</div>
                               
									
									<div class="form-actions fluid">	
									
										<div class="col-md-offset-3 col-md-9">
											<button type="button" class="btn blue" onclick="saveVehicleMapping()">Save</button>
											<button type="button" id="cancel" class="btn default" onclick="goView()">Cancel</button>
											
										</div>
									</div>
							
								<s:token />
							</form>
					
	
							
							<!-- END FORM-->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
<script>


</script>