<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

 <script src="assets/global/plugins/jquery-1.11.0.min.js"
	type="text/javascript"></script>
	<script>
	function getpassdetails(date){
		$('#studentInfo').show();
		var date1=date.split("-");
		var strdate=date1[2]+"-"+date1[1]+"-"+date1[0];
		
		 $('#studentInfo').dataTable({
				"aLengthMenu" : [ [ 10, 50, 100, -1 ], [ 10, 50, 100, "All" ] // change
				
				],
				 "sAjaxSource" : "AjaxstudentDetailview.action?startdate="+strdate,
				"sPaginationType" : "bootstrap",
				"bDestroy" : true,

				 "oLanguage": {
	                    "sLengthMenu": "_MENU_ records",
	                    "oPaginate": {
	                        "sPrevious": "Prev",
	                        "sNext": "Next"
	                    }
	                },
	               "aoColumnDefs": [
	                    { 'bSortable': false, 'aTargets': [0] },
	                    { "bSearchable": false, "aTargets": [ 0 ]} ,
	                    { "sClass": "url", "aTargets": [ 3 ] },
	                ],
			});
			jQuery(
					'#studentInfo_wrapper .dataTables_filter input')
					.addClass("form-control input-small input-inline"); // modify
			// table
			jQuery(
					'#studentInfo_wrapper .dataTables_length select')
					.addClass("form-control input-xsmall input-inline"); // modify         

	}
</script>
<script>

function studentEdit(){
	
	var cnt = $(":checkbox:checked").length;
	var val;
	var id;
	var type;
	if (cnt == 0) {
		bootbox.alert("Please Select Checkbox To Edit");
	} else if (cnt > 1) {
		bootbox.alert("Please Select One Checkbox To Edit");
	} else {
		$("input[type='checkbox']:checked").each(
				function() {

					val = $(this).val();
//							val=$("#id").val();
//						id=$("#checkbox").val();
//						alert(val);
					
				});
		document.forms[0].action = 'editstudentpassdetails.action?value='+val;
		document.forms[0].submit();
	

				
}
}


function getData(){
	
	
	var id=$("#studentid").val();
	var date=$("#startdate").val();
	
//alert(dd1);
$('#pageLoader').show();  
	 $.ajax({
		 
            type: "post",
            url: '<%=request.getContextPath()%>/AjaxStudentcashdetailsReport.action?value='+id+'&date='+date,
            success: function(result) {
            	$('#pageLoader').hide();
                document.getElementById('ticketconsuptionid').innerHTML=result;
                fixHeader();
            }
        });

}




</script>
<%-- <script type="text/javascript">
$(document).ready(function() {
getdata();
getheader();
});
</script> --%>
<script>

function printDiv() {     
	 
	    
	     
	     var divElements = document.getElementById("header").innerHTML;
	     divElements+= document.getElementById("ticketconsuptionid").innerHTML;
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
             inputHTML += "<th  align='Center'colspan='8'>Student Pass Cash Collection</th>";
             inputHTML += "</tr>";
           
             inputHTML += "</table>";
//     		   document.getElementById("ticketconsuptionid").style.visibility='visible';  
//     	     document.getElementById("header").style.display='block';
//     	     document.getElementById("header").style.visibility='visible';     
    	     var divElements = inputHTML+document.getElementById("header").innerHTML;
    	     divElements+= document.getElementById("ticketconsuptionid").innerHTML;
     
     var noOfTableExist = 0;
     try{
 		$("#ticketconsuptionid table").each(function(){
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
    downloadLink.download = "studentpasscash.xls";
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
}
	</script>
	 <script>
	function getheader() {
		
		document.getElementById("startdat").innerHTML = new Date().toJSON().slice(0,10);
		}
function studentdata(){
		
		var id=$("#studentid").val();
		
			document.forms[0].action = 'editstudentpassdetails.action?value='+id;
			document.forms[0].submit();
		

					
	
	}
 </script>

	


</head>
<body>
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
			
			<div id="pageLoader" class="modal fade in" tabindex="-1" role="dialog" aria-labelledby="myModalLabel3" aria-hidden="false">
								<div class="modal-dialog">
									<div class="modal-content">
										
										<div class="modal-body">
											<p>
												<img src="assets/images/loader1.gif" align="top" style="margin-left:100px;"/> 
											</p>
											<p style='text-align:center'>Please Wait........</p>
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
								<i class="fa fa-globe"></i>Student Pass Cash Collection
							</div>
						</div>
						
						<div class="portlet-body">
 <B><font color="green"><s:actionmessage /></font></B>
                            <s:if test="hasActionErrors()">
                                <div class="alert alert-danger">
                               
                                    <button class="close" data-close="alert"></button>
                                <span id="errorMsg" style="color:red;word-wrap: break-word;"><s:actionerror/></span>
                                </div>
                                </s:if>
                               

                     <form action="" method="post" class="form-horizontal">
                       
								    <div class="form-body"> 							
									<div class="form-group">
							  <label class="control-label col-md-3">Cashier Name:<font
										color="red">*</font></label>
								<div class="col-md-3">
								
								<input type="text" class="form-control" id="studentid"
													name="studentid" maxlength="30"></input>
								</div></div>
								
													<div class="form-group">
							  <label class="control-label col-md-3">Date :<font
										color="red">*</font></label>
								<div class="col-md-3">
								
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" ><!-- data-date-start-date="+0d"> -->
								<input id="startdate" class="form-control" type="text"  size="16" name="rateMaster.effectiveStartDate"
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
                                 var dtval=document.getElementById('startdate').value;	 
                                
                                 if(dtval==''){
                              $('#startdate').val(curr_date); 
                            
                                } 
								</script>
								</div>
								</div></div>
											
								
 					
						<!-- end -->
						 <div align='center'>
					<button type="button" class="btn blue" onClick="getData()">Submit</button>
					<button type="button" class="btn blue" onClick="printDiv()">Print</button>
					<button type="button" class="btn blue" onClick="tabletoExcel()">Export</button>
					
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
					
                     </div>
                     </form>
				
<!-- 					 <div align='center'> -->
<!-- 					<button type="button" class="btn blue" onClick="studentdata()">Submit</button> -->
					
<!-- 					END EXAMPLE TABLE PORTLET -->
<!-- 				</div> -->
				<div id="ticketconsuptionid"></div>			
			</div>
			
			<!-- END PAGE CONTENT-->
			
		
	
		
	
	
			
							</div></div></div></div></div>
			

								
	</body>
	</html>