<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <% 
// AccessRightsDao  accessrightdao=new AccessRightsDao();
// AccessRights accessrights=new AccessRights();
 String  orgtypeid=(String)request.getSession().getAttribute("orgtypeid");
 String  orgchartid=(String)request.getSession().getAttribute("orgchartid");
 out.println(orgtypeid+"==="+orgchartid);
// accessrights=accessrightdao.accessRightsdetails(usrsessionid, "PassIssuerTypeAction.action");
// String access=accessrights.getACCESS();
// String create=accessrights.getCREATE();
// String edit=accessrights.getEDIT();
// String delete=accessrights.getDELETE();
// String read=accessrights.getREAD();
// String error=accessrights.getERROR();
// String viewdelete=accessrights.getVIEWDELETE();
// String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
// String status=accessrightdao.UserStatus(usrsessionid);
 %> 
<form>
	<div class="page-content-wrapper">
		<div class="page-content">
<%-- 		<%if (access.equalsIgnoreCase("Y")){%> --%>
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div class="modal fade" id="portlet-config" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true"></button>
							<h4 class="modal-title"></h4>
						</div>
						<div class="modal-body"></div>
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
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						Employee Default Rank Type   <small></small>
					</h3>
					
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			
			<!-- BEGIN PAGE CONTENT-->

			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>Employee Default Rank Type
							</div>
							
							<div class="actions">
								<%if(!orgtypeid.equals("1") && !orgchartid.equals("1")){ %>
								<a href="#" class="btn green" onclick="callCreate()"> <i
								class="fa fa-plus"></i> Create
								</a>
								<%}%>
									<%if(!orgtypeid.equals("1") && !orgchartid.equals("1")){ %>
							<a href="#" class="btn blue" onclick="callEdit()"> <i
								class="fa fa-pencil"></i> Edit
							</a>

								<% }%>
									<a href="#" class="btn blue" onclick="printDiv()"> <i
								class=""></i> Print
							</a>
							<a href="#" class="btn blue" onclick="tabletoExcel()"> <i
								class=""></i> Export
							</a>					
							</div> 
							
					
						</div>

							</div>
							
						</div>

						<div class="portlet-body" id="Defaultview">
			
							<b>
						<font color="green"> <s:actionmessage/></font></b>
							<b><font color="red"> <s:actionerror/></font></b>
							<input type="hidden" id="orgchartdid" value='<%=orgchartid %>'>
							<table class="table table-striped table-bordered table-hover"
								id="EmployeeDefaultView">
								<thead>
									<tr>
										<th style="width1: 8px;"></th>
										<th>Id</th>
										<th>Token No</th>
										<th>PF No</th>
										<th>Name</th>
										<th>Rank</th>
										
					
										</tr>
								</thead>

							</table>

						</div>
					</div>
				</div>
				<!-- END EXAMPLE TABLE PORTLET-->
			</div>
		</div>

		<!-- END PAGE CONTENT-->
		 <!--  added by rama -->
	<script>
	function callEdit(){
		
		var cnt = $(":checkbox:checked").length;
		var val;
		if (cnt == 0) {
			bootbox
					.alert("Please Select Checkbox To Edit");
		} else if (cnt > 1) {
			bootbox
					.alert("Please Select One Checkbox To Edit");
		} else {
			$("input[type='checkbox']:checked").each(
					function() {

						val = this.value;
					});
			if(isEligibleForOpertion(val)){
			//document.forms[0].action = "editemployeeDutyType";
	document.forms[0].action = 'EditEmployeeDefault.action?val='+val,
			document.forms[0].submit();
			}else{
				bootbox.alert("Please Select Valid Record");
			}

					
	}
	}

	function callCreate() {
		document.forms[0].action = "EmployeeDefaultView.action";
		document.forms[0].submit();
	}
	
	$(document).ready(function() {
		getData();
		getheader();
		   window.history.pushState("","", "EmployeeDefaultView.action");
		   
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
		
	 function getData(){
		 var orgchartdid=$("#orgchartdid").val();
		// alert(orgchartdid);
	     $('#employeedutytypeView').dataTable({
				"aLengthMenu" : [ [10, 25, 50, 100,-1 ], [10, 25, 50, 100,"All" ] // change
																	// per page
																	// values
																	// here
				],
				// set the initial value
				"iDisplayLength" : 10,
				"bProcessing" : true,
				"bServerSide" : true,
				"sAjaxSource" : "employeeDefaultviewList.action?orgchartdid="+ orgchartdid,
				"sPaginationType" : "bootstrap",
			
			  "oLanguage": { "sLengthMenu": "_MENU_ records", "oPaginate": {
			  "sPrevious": "Prev", "sNext": "Next" } }, "aoColumnDefs": [ {
			  'bSortable': false, 'aTargets': [0] }, { "bSearchable": false,
			  "aTargets": [ 0,4] },{ "sClass": "url", "aTargets": [ 4 ] } ]
			 

			});
			jQuery('#EmployeeDefaultView_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
	  jQuery('#EmployeeDefaultView_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
	     }
	</script>
	 <!--  ended by rama -->
</form>
<%-- <%}else{%> --%>
 


<%-- <%@ include file="/pages/admin/error.jsp" %> --%>


<%-- <%}%> --%>



</body>
<script>

function printDiv() {     
	  document.getElementById("dutytypeview").style.visibility='visible';  
	     document.getElementById("header").style.display='block';
	     document.getElementById("header").style.visibility='visible'; 
	    
	     
	     var divElements = document.getElementById("header").innerHTML;
	     divElements+= document.getElementById("dutytypeview").innerHTML;
	     var mywindow = window.open("<%=request.getContextPath()%>/Print1.jsp");
	     mywindow.onload = function() {
	         mywindow.document.body.innerHTML=divElements;
	         mywindow.document.body.innerHTML=divElements;
	     //	mywindow.document.body.innerHTML = divElement;
	         //   document.getElementById("print").style.visibility='';
	         mywindow.print();
	         mywindow.close();
	     }
	     document.getElementById("header").style.display = 'none';
			document.getElementById("header").style.visibility = 'hidden';
            
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
             inputHTML += "<th  align='Center'colspan='8'>Employee Default Rank Type </th>";
             inputHTML += "</tr>";
           
             inputHTML += "</table>";
    		   document.getElementById("dutytypeview").style.visibility='visible';  
    	     document.getElementById("header").style.display='block';
    	     document.getElementById("header").style.visibility='visible';     
    	     var divElements = inputHTML+document.getElementById("header").innerHTML;
    	     divElements+= document.getElementById("Defaultview").innerHTML;
     
     var noOfTableExist = 0;
     try{
 		$("#Defaultview table").each(function(){
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
    downloadLink.download = "Employee Default Rank Type.xls";
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
}
	</script>
	 <script>
	function getheader() {
		
		document.getElementById("startdat").innerHTML = new Date().toJSON().slice(0,10);
		}
 </script>

		<div id="header" class="portlet-body" style="display: none; visibility: hidden;">
 <h3 style="margin-left:350px;">Employee Duty Type</h3>
<br />
<b><font size="2"><label>Date: </label></font><font size="2"><b><span id="startdat" style="text-align:left;margin-left:50px;"></span></font></b>

</div>
</html>