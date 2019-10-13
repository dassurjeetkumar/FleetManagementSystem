<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
String usrroleid=(String)request.getSession().getAttribute("roleid");
String orgchartid=(String)request.getSession().getAttribute("orgtypeid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ShowEmployeeListDetails.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
String status=accessrightdao.UserStatus(usrsessionid);
%>
 <script src="assets/global/plugins/jquery-1.11.0.min.js"
	type="text/javascript"></script>
<script>	 
 function callCreate(){
		document.forms[0].action = 'createetmissue.action';
		 document.forms[0].submit(); 
	}	 
	 

</script>
	<script type="text/javascript">
    function tabletoExcel(btnExport){     
    	/*  document.getElementById("mapshow").style.visibility='hidden'; 
    		$(".mapClass").hide(); */
   	     
    	     var inputHTML = "<table border='1'>";
             inputHTML += "<tr>";
             inputHTML += "<th  align='center'colspan='8'>Bangalore Metropolitan Transport Corporation</th>";
             inputHTML += "</tr>";
            inputHTML += "<tr>";
             inputHTML += "<th  align='Center'colspan='8'>Etm Device History</th>";
             inputHTML += "</tr>";
           
             inputHTML += "</table>";
    		   document.getElementById("etmdevicehistory").style.visibility='visible';  
    	     document.getElementById("header").style.display='block';
    	     document.getElementById("header").style.visibility='visible';     
    	     var divElements = inputHTML+document.getElementById("header").innerHTML;
    	     divElements+= document.getElementById("etmdevicehistory").innerHTML;
     
     var noOfTableExist = 0;
     try{
 		$("#etmdevicehistoryTable table").each(function(){
 			noOfTableExist++;
 		});
 		
 		console.log("Total no of tables : " + noOfTableExist);
 		/* If two table exist  then remove the last table on print click*/
 		/* if(noOfTableExist >= 2){
 			divElements = divElements.substring(0, divElements.indexOf("</table>") + 8) + "</div></div>";
 		} */
 	}catch(err){
 	    console.log("ExceptionCaught : " + err);
 	}
    
    var downloadLink = document.createElement("a");
    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(divElements);
    downloadLink.download = "Etm Device History.xls";
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
}
	</script>
	 <script>
	function getheader() {
		
		document.getElementById("startdate").innerHTML = new Date().toJSON().slice(0,10);
		}
 </script>
<script>
		
		
		
function callServiceTicket(){
		document.forms[0].action = 'serviceTicketToCompanyEtm.action';
		document.forms[0].submit();
	}
function callServiceTicketDuplicate(){
	document.forms[0].action = 'serviceTicketToCompanyEtmDuplicate.action';
	document.forms[0].submit();
}
function callReceiveETM(){
/* 	var cnt = $(":checkbox:checked").length;
	var val;
	var i=0;
	var data=new Array();
	if (cnt == 0) {
		bootbox
				.alert("Please Select Checkbox To Receive");
	}else {
		$("input[type='checkbox']:checked").each(
				function() {
					val = this.value;
					data[i]=val;
					i++;
				});
		document.forms[0].action = "receiveETM.action?value="+ data;
		document.forms[0].submit(); 
		}*/
	document.forms[0].action = 'getetmpickfromdepot.action';
	document.forms[0].submit();
}
function callETMResolved(){
/* 	var cnt = $(":checkbox:checked").length;
	var val;
	var i=0;
	var data=new Array();
	if (cnt == 0) {
		bootbox
				.alert("Please Select Checkbox To Resolve");
	}else {
		$("input[type='checkbox']:checked").each(
				function() {
					val = this.value;
					data[i]=val;
					i++;
				});
		document.forms[0].action = "resolveETM.action?value="+ data;
		document.forms[0].submit();
		} */
	document.forms[0].action = 'getetmnotreslove.action';
	document.forms[0].submit();
		
}
function callReceiveFromVerifone(){
/* 	var cnt = $(":checkbox:checked").length;
	var val;
	var i=0;
	var data=new Array();
	if (cnt == 0) {
		bootbox
				.alert("Please Select Checkbox To Receive");
	}else {
		$("input[type='checkbox']:checked").each(
				function() {
					val = this.value;
					data[i]=val;
					i++;
				});
		document.forms[0].action = "receiveETMFromVerifone.action?value="+ data;
		document.forms[0].submit();
		} */
	document.forms[0].action = 'receviedfromverifone.action';
	document.forms[0].submit();
}
function Resolved(){
	/* var cnt = $(":checkbox:checked").length;
	var val;
	if (cnt == 0) {
		bootbox
				.alert("Please Select Checkbox To Resolve");
	} else if (cnt > 1) {
		bootbox
				.alert("Please Select One Checkbox To Resolve");
	} else {
		$("input[type='checkbox']:checked").each(
				function() {

					val = this.value;
				});
		document.forms[0].action = "ResolveETM.action?etmserviceid="+ val;
		document.forms[0].submit();
		} */
	document.forms[0].action = "etmresolved.action";
	document.forms[0].submit();

				
}

function ReceivedByDeot(){
	
	/* var cnt = $(":checkbox:checked").length;
	var val;
	if (cnt == 0) {
		bootbox
				.alert("Please Select Checkbox To Received By Depot");
	} else if (cnt > 1) {
		bootbox
				.alert("Please Select One Checkbox To Received By Depot");
	} else {
		$("input[type='checkbox']:checked").each(
				function() {

					val = this.value;
				});
	
		document.forms[0].action = "ReceivedByDepot.action?etmserviceid="+ val;
		document.forms[0].submit();
		 */
			document.forms[0].action = "etmreceivedfromtrimax.action";
			document.forms[0].submit();

				
}

		
	</script>
	


</head>
<body>

 <form>
 
<div class="page-content-wrapper">
		<div class="page-content">
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div class="modal fade" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">Modal title</h4>
						</div>
						<div class="modal-body">

							 Widget settings form goes here
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue">Save changes</button>
							<button type="button" class="btn default" data-dismiss="modal">Close</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			
			<!-- END STYLE CUSTOMIZER -->
			<!-- BEGIN PAGE HEADER-->
<!-- 			<div class="row"> -->
<!-- 				<div class="col-md-12"> -->
<!-- 					BEGIN PAGE TITLE & BREADCRUMB -->
<!-- 					<h3 class="page-title"> -->
<!-- 					 SCHEDULE -->
<!-- 					</h3> -->
					
<!-- 					END PAGE TITLE & BREADCRUMB -->
<!-- 				</div> -->
<!-- 			</div> -->
			
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			
			<%-- //<s:hidden name="busid" id="id" ></s:hidden> --%>
			 <!-- <input type="hidden" name="busid" id="busid" value="22181"/> -->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>ETM Device Repair Status
							</div>
							<div class="actions">
							<div class="btn-group">
							<%if(usrroleid.equalsIgnoreCase("35")){ %>
								<button type="button" class="btn red" onclick="Resolved();"><i class="fa fa-eye"></i>&nbsp;ETM Resolved</button><%}%>
								<%if(orgchartid.equalsIgnoreCase("3") && !usrroleid.equalsIgnoreCase("36") ){ %>
								<a href="#" class="btn green" onclick="callCreate()"> <i
								class="fa fa-plus"></i> Create
								</a>
								<button type="button" class="btn red" onclick="ReceivedByDeot();"><i class="fa fa-eye"></i>&nbsp;ETM Received From Trimax</button>
								<%}%>
								<%if(usrroleid.equalsIgnoreCase("36")){ %>
								<button type="button" class="btn red" onclick="callReceiveETM();"><i class="fa fa-eye"></i>&nbsp;ETM Pickup From Depot</button>
								<button type="button" class="btn red" onclick="callETMResolved();"><i class="fa fa-eye"></i>&nbsp;ETM Resolved By Trimax</button>
								<button type="button" class="btn red" onclick="callServiceTicket();"><i class="fa fa-eye"></i>&nbsp;Service Ticket To Verifone</button>
								<button type="button" class="btn red" onclick="callReceiveFromVerifone();"><i class="fa fa-eye"></i>&nbsp;ETM Received From Verifone</button>
								<button type="button" class="btn red" onclick="callServiceTicketDuplicate();"><i class="fa fa-eye"></i>&nbsp;Service Ticket Duplicate</button>
								<%}%>
								<button type="button" class="btn blue" onclick="getheader(),tabletoExcel();"><i class="fa fa-eye"></i>&nbsp;Export</button>
								
<%-- 									<%if(orgchartid.equalsIgnoreCase("3")){ %> --%>
<!-- 							<a href="#" class="btn blue" onclick="callEdit()"> <i -->
<!-- 								class="fa fa-pencil"></i> Edit -->
<!-- 							</a> -->
<%-- 								<%}%>	 --%>
															
								
									</div>
							</div>
							
						</div>
						
						<div class="portlet-body" id="etmdevicehistory">
						
						<b>
							<font color="green"> <s:actionmessage/></font></b>
							<b><font color="red"> <s:actionerror/></font></b>
<!--                            <div id="vechicledefectsid"></div> -->
                           
                           <table class="table table-striped table-bordered table-hover"
								id="etmdevicehistoryTable">
								
								<thead>
									<tr>
									<!-- 	<th style="width1: 8px;"></th> -->
										<th>Id</th>
										<th>Division </th>
										<th>Depot </th>
										<th>Etm Number</th>
										<th>Etm Issue</th>
										<th>Etm Issue Date</th>
										<th>Etm Pickup From Depot</th>
										<th>Etm Resolved By Trimax</th>
										<th>Etm Pickup By Verifone</th>
										<th>Etm Resolved By verifone</th>
										<th>Etm Received From Verifone</th>
										<th>Solved Remarks</th>
											<th>Remarks</th>								     
<%-- 										  <%if(viewdeletedrecord.equalsIgnoreCase("Y")) {%>	 --%>
<!-- 													<th>Record Status</th> -->
<%-- 													<%}%> --%>
									</tr>
								</thead>

							</table>
                         
							
							</div>
						</div>
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
				
		</div>
	</div>
	
	</form>
			<div id="header" class="portlet-body" style="display: none; visibility: hidden;">
 <h3 style="margin-left:350px;">Etm Device History</h3>
<br />
<b><font size="2"><label>Date: </label></font><font size="2"><b><span id="startdate" style="text-align:left;margin-left:50px;"></span></font></b>

</div>
	
								
	</body>
	</html>