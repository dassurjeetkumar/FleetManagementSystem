<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form>
	<div class="page-content-wrapper">
		<div class="page-content">
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						Route Map PIS  <small></small>
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
								<i class="fa fa-globe"></i>View Route Map PIS
							</div>
							
							<div class="actions">
								
								<a href="#" class="btn green" onclick="callCreate()"> <i
								class="fa fa-plus"></i> Create
								</a>
							
<!-- 							<a href="#" class="btn blue" onclick="callEdit()"> <i -->
<!-- 								class="fa fa-pencil"></i> Edit -->
<!-- 							</a> -->
							<a href="#" class="btn blue" onclick="callDelete()"> <i
								class="fa fa-pencil"></i> Delete
							</a>
					     <button type="button" id="pid" class="btn red" onclick="printDiv('data');">Print</button> 
                       <button type="button" id="eid" class="btn green" onclick="tabletoExcel('data');">Export</button> 
						</div>

							</div>
							
						</div>

						<div class="portlet-body">
			
							<b>
							<font color="green"> <s:actionmessage/></font></b>
							<font color="red"> <s:actionerror/></font></b>
							<table class="table table-striped table-bordered table-hover"
								id="viewroutmappisdetails">
								<thead>
									<tr>
										<th style="width1: 8px;"></th>
										<th>ID</th>
										<th>Route No</th>
										<th>From_To</th>
										<th>TTMC Name</th>
										<th>Bus Stop Name</th>
										<th>Floor</th>
										<th>Bay</th>
										<th>Platform</th>
										<th>Service type</th>
										
																
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
		
			document.forms[0].action = "editviewRoutePis.action?vid="+ val;
			document.forms[0].submit();
					
	}
	}
function callDelete(){
		
		var cnt = $(":checkbox:checked").length;
		var val;
		if (cnt == 0) {
			bootbox
					.alert("Please Select Checkbox To Delete");
		} else if (cnt > 1) {
			bootbox
					.alert("Please Select One Checkbox To Delete");
		} else {
			$("input[type='checkbox']:checked").each(
					function() {

						val = this.value;
					});
			
			document.forms[0].action = "deleteviewRoutePis.action?vid="+ val;
			document.forms[0].submit();
			

					
	}
	}
	function callCreate() {
		document.forms[0].action = "createviewRoutePis.action";
		document.forms[0].submit();
	}
	
		
	</script>
<script type="text/javascript">
function printDiv() {     
	  document.getElementById("viewroutmappisdetails").style.visibility='visible';  
   document.getElementById("header").style.display='block';
   document.getElementById("header").style.visibility='visible'; 
  
   
   var divElements = "<table>"+document.getElementById("header").innerHTML;
   divElements+= document.getElementById("viewroutmappisdetails").innerHTML+"</table>";
   var mywindow = window.open("<%=request.getContextPath()%>/Print1.jsp");
   mywindow.onload = function() {
       mywindow.document.body.innerHTML=divElements;
       mywindow.document.body.innerHTML=divElements;
       //console.log(divElements);
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
    	var inputHTML = "<table border='1'>";
        inputHTML += "<tr>";
        inputHTML += "<th  align='center'colspan='8'>Bangalore Metropolitan Transport Corporation</th>";
        inputHTML += "</tr>";
        inputHTML += "<th  align='Center'colspan='8'>Route Map Pis</th>";
        inputHTML += "</tr>";
       
        inputHTML += "</table>";
    		   document.getElementById("viewroutmappisdetails").style.visibility='visible';  
    	     document.getElementById("header").style.display='block';
    	     document.getElementById("header").style.visibility='visible';     
    	     var divElements = inputHTML+"<table border='1'> ";
    	     divElements+= document.getElementById("viewroutmappisdetails").innerHTML+"</table>";
     console.log(divElements);
     var noOfTableExist = 0;
     try{
 		$("#viewroutmappisdetails table").each(function(){
 			noOfTableExist++;
 		});
 		
 		console.log("Total no of tables : " + noOfTableExist);
 		/* If two table exist  then remove the last table on print click*/
 		if(noOfTableExist >= 2){
 			divElements = divElements.substring(0, divElements.indexOf("</table>") + 8);
 		}
 	}catch(err){
 	    console.log("ExceptionCaught : " + err);
 	}
 	console.log(divElements);
    var downloadLink = document.createElement("a");
    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(divElements);
    downloadLink.download = "Route Map Pis.xls";
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
}
    </script>
<div id="header" class="portlet-body" style="display: none; visibility: hidden;">
 <h3 style="margin-left:350px;">Route Map PIS</h3>
<br />

</div> 
</form>

</body>
</html>