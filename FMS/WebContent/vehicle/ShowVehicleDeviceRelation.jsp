
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
<script src="https://code.jquery.com/jquery-1.11.3.min.js" type="text/javascript"></script>





<script>      
 
function getSyncDetail(){
	//console.log(document.getElementById("vehicleno").value);
	
	alert("in sync");
	$('#loadingmessage').show(); 
	$.ajax({
           type: "get",
           url: '<%=request.getContextPath()%>/syncVehicleDeviceRelationToVTS',
           success: function(result) {
        	   $('#loadingmessage').hide(); 
//         	   getScheduledTripStatusDataOnSubmit();
        	   bootbox.alert('Sync is Successfull');
        	  // document.getElementById('scheduletype').innerHTML=result; 
              
               
            }
       }); 
	}
	



   function tabletoExcel(btnExport){     
  	var htmltable= document.getElementById('btnExport');   
    $( "#header-fixed" ).remove();
    var inputHTML = "<table border='1'>";
    inputHTML += "<tr>";
    inputHTML += "<th  align='left'colspan='13'>Bangalore Metropolitan Transport Corporation</th>";
    inputHTML += "</tr>";
    inputHTML += "<th  align='left'colspan='13'>Vehicle Device Relation</th>";
    inputHTML += "</tr>";
    inputHTML += "<tr>";
//     inputHTML += "<th colspan='3' align='left'>Date Range:</th>";
//     inputHTML += "<th colspan='4' align='left'>" + $("#startdate").val() + " to " + $("#endate").val() + "</th>";
    inputHTML += "</tr>";
    inputHTML += "</table>";
    var htmltable = document.getElementById("vehicleDeviceRelation");
    var html = inputHTML + htmltable.outerHTML;
    var downloadLink = document.createElement("a");
    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
    downloadLink.download = "Vehicle Device Relation.xls";
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
}</script>



<style>
.url {
    max-width: 150px;
    word-wrap: break-word;
}

</style>
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />


<div class="page-content-wrapper">
	<div class="page-content">
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
				<h3 class="page-title">
					VEHICLE DEVICE RELATION
				</h3>
			</div>
		</div>
		
<%-- <%if (access.equalsIgnoreCase("Y")){%> --%>
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN EXAMPLE TABLE PORTLET-->
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">

							<i class="fa fa-globe"></i>View Vehicle Device Relation
						</div>
						<div align="right">
<%--  <span><button type="button" class="btn green" id="btnExport" onclick="getSyncDetail();"> Sync.  </button></span>&nbsp;<span> --%>
 <span><button type="button" class="btn green" id="btnExport" onclick="tabletoExcel();"> EXPORT  </button></span>&nbsp;<span>
                      
 						</div> 
					</div>
					
					<div class="portlet-body">
						<form action="" method="post">
						<B><font color="green"><s:actionmessage /></font></B>
							<s:if test="hasActionErrors()">
								<div class="alert alert-danger">
								
									<button class="close" data-close="alert"></button>
								<span id="errorMsg" style="color:red;word-wrap: break-word;"><s:actionerror/></span>
								</div>
								</s:if>
								<b>
<%-- 								<span id="errorMsg" style="color:red;word-wrap: break-word;" ><s:actionerror/></span> --%>
							</b>
							<input type="hidden" id="" value="" name="vehicleTypeId">
							<table class="table table-striped table-bordered table-hover"
								id="vehicleDeviceRelation" name="vehicleDeviceRelation">
								<thead>
									<tr>
									<th></th>
										<th>Vehicle No</th>
										<th>Device Id </th>
										<th>Sim No.</th>
										<th>Depot Name</th>
										<th>Cause Status</th>
																	</tr>
									
								</thead>
								<tr>
<!-- 									<button type="button" class="btn blue" onClick="ExportFile()">Export To Excel</button></td> -->
									</tr>
							
							</table>
						</form>
					
 				</div></div></div> 
			</div>
		</div>
	</div>
</div>
<script>
$(document).ready(function() {
	window.history.pushState("","", "VehicleDeviceRelation.action");
	var w=$('#errorMsg span').html();
	   //alert(w);
	    w=w.replace(/@/g,"<br>");

	   $('#errorMsg').html(''+w+'');
});
function isEligibleForOpertion(id){
	 var isEligible = $('#isRocordEligible'+id).val();
	 if(isEligible == undefined || isEligible=='Y'){
		 return true;
	 }else{
		 return false;
	 }
}
</script>
<%-- <%}else{%> --%>
 


<%-- <%@ include file="/pages/admin/error.jsp" %> --%>


<%-- <%}%> --%>


