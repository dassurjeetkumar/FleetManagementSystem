  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" /> --%>
 
<script>
$(document).ready(function(){
	 
// 	$("#smsfordriver0").hide();
// 	$("#okfordriver0").hide();
	

	
// 	$("#smsforconductor").hide();
// 	$("#okforconductor").hide();
// 	$("#smsfordriverconductor").hide();
// 	$("#okfordriverconductor").hide();
	
	$("#selectAll").click(function(e) {
	    //e.preventDefault();
	    if($("#selectAll").prop('checked')){
	    	$('.driverId').prop('checked', true);
// 	    	$('.conductorId').prop('checked', true);
// 	    	$('.conductordriverId').prop('checked', true);
	    }else{
	    	$('.driverId').prop('checked', false);
// 	    	$('.conductorId').prop('checked', false);
// 	    	$('.conductordriverId').prop('checked', false);
	    }
	    
	
	});

	
 });
 
 
var flag = 0;
var flag1 = 0;
var flag2 = 0;
function getSmsData() {
	
	 var divisionlist=$('#divisionlist1').val();
	 var depotid=$('#depotlist1').val();
	 var type=$('#disignationType').val();
	 var subject=$('#subject').val();
	 var desc=$('#description').val();
	
	if(flag==0){
	  
		$("#ticketconsuptionid").show();
	$("#forSmsData").show();
	$("#route_deviation_id").show();
	$('#forSmsData').dataTable({
    	"aaSorting": [
                      [0, 'asc']
                  ],
        "aLengthMenu": [
            [10, 20, 50, 100],
            [10, 20, 50, 100] // change per page values here
        ],
        // set the initial value
       // "iDisplayLength": 10, 
       "bPaginate": false,
        "bProcessing" : true,
        "bServerSide" : true,
        "bDestroy": true,
        "bFilter": false,
        "bInfo": false,
        "sAjaxSource" : "SMSForAllType.action?type="+type+"&subject="+subject+"&desc="+desc+"&depot="+depotid+"&division="+divisionlist,
        "sPaginationType": "bootstrap",
        "oLanguage": {
            "sLengthMenu": "_MENU_ records",
            "oPaginate": {
                "sPrevious": "Prev",
                "sNext": "Next"
            }
        },
       "aoColumnDefs": [
            { 'bSortable': false, 'aTargets': [0] },
            { "bSearchable": false, "aTargets": [ 0 ] }
        ]
        
    });            
    jQuery('#forSmsData_wrapper .dataTables_filter input').addClass("form-control input-medium input-inline"); // modify table search input
    jQuery('#forSmsData_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
    
    flag=0;
	}
	else if(flag==1){
		//$("#smsfordriver").hide();
// 		$("#ticketconsuptionid").hide();
// 	 $("#ticketconsuptionid").hide();
// 	    document.getElementById("okfordriver"+el+"").style.display="none";
// 	    document.getElementById("smsfordriver"+el+"").style.display="none";
	    flag=0;
	}
} 
 
 
 
 
 
 

function getDepot(orgId){
	 $('#select2-chosen-2').html("Select");
	 $('#select2-chosen-3').html("Select");
	
		 var val=document.getElementById('divisionlist1').value;
// 		 alert(val);
			 if(val!=0) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/getAllDepot?val=' + val,
				success : function(result) {
					document.getElementById('depotlist1').innerHTML = result;
					
				}
			});
		}

	}
	
function getDisignation(depotId){
	 $('#select2-chosen-2').html("Select");
	 $('#select2-chosen-3').html("Select");
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		  var division=document.getElementById('divisionlist1').value;
		 var val=document.getElementById('depotlist1').value;
		
			 if(val!=0) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/getEmployeeDesignation?val='+val+'&division='+division,
				success : function(result) {
					document.getElementById('disignationType').innerHTML = result;
					
				}
			});
		}

	}
	

function validate()
{
	 
//    alert("hii");
	 var divisionlist=$('#divisionlist1').val();
	 var depotlist=$('#depotlist1').val();
	 var type=$('#disignationType').val();
	 var subject=$('#subject').val();
	 var description=$('#description').val();

   if( divisionlist == "0" )
   {
//       alert( "Please select vehicle" );
       bootbox.alert("Please select division");
      return false;
   }
   if( depotlist == "0" )
   {
      //alert( "Please select vehicle" );
       bootbox.alert("Please select depot");
      return false;
   }
   if( type == "-1" )
   {
      //alert( "Please select vehicle" );
       bootbox.alert("Please select Designation Type");
      return false;
   }
  
   
   if( subject == "" )
   {
      //alert( "Please select vehicle" );
       bootbox.alert("Please Enter Subject");
      return false;
   }
   if( description == "" )
   {
      //alert( "Please select vehicle" );
       bootbox.alert("Please Enter Description");
      return false;
   }

 

   return( true );
   
}
	
	
	

function getScheduleData(){
	
	var valid=validate();
		var dd1=$("#startdate").val();
		var dd2=$("#enddate").val();
		var division=$("#divisionlist1").val();
		var depot=$("#depotlist1").val();
		var scheduleno=$("#scheduleid").val();
//  		alert(depot+scheduleno);
		var var1=$("#startdate").val();
		var1=var1.split("-");
		var1=var1[2]+"-"+var1[1]+"-"+var1[0];
		var var2=$("#enddate").val();
		var2=var2.split("-");
		var2=var2[2]+"-"+var2[1]+"-"+var2[0];
		//alert(var1+""+var2);
		
		 var d1 = Date.parse(var1);

// alert(valid);
		if(valid==true){
// 			alert("hello");
	var d3=Date.parse(var2);
		if (d1 <= d3){
			$('#pageLoader').show();
        $.ajax({
        
            type: "get",
            url: '<%=request.getContextPath()%>/AjaxScheduleWiseEPKMReport.action?startdate='+dd1+'&enddate='+dd2+'&scheduleno='+scheduleno+'&division='+division+'&depot='+depot,
            success: function(result) {
            	$('#pageLoader').hide();
                document.getElementById('ticketconsuptionid').innerHTML=result;
                fixHeader();
            }
        });
		}else{
 			
 			
 			alert("Till Date Should Be greater Than Start Date");
 			$('#pageLoader').hide();
 			 document.getElementById('ticketconsuptionid').innerHTML="";
 		}
		}
}
</script>

<script>
		
function printhiv() {     
    
    //  document.getElementById("print").style.visibility='hidden';     
    var divElements = document.getElementById("header").innerHTML;
    divElements += document.getElementById("ticketconsuptionid").innerHTML;
    
    var noOfTableExist = 0;
    try{
		$("#ticketconsuptionid table").each(function(){
			noOfTableExist++;
		});
		
		console.log("Total no of tables : " + noOfTableExist);
		/* If two table exist  then remove the last table on print click*/
		if(noOfTableExist >= 2){
			divElements = divElements.substring(0, divElements.indexOf("</table>") + 8) + "</div></div>";
		}
	}catch(err){
	    console.log("ExceptionCaught : " + err);
	}

    var mywindow = window.open("<%=request.getContextPath()%>/Print.jsp");
   
    mywindow.onload = function() {
    mywindow.document.body.innerHTML=divElements;
    mywindow.document.body.innerHTML=divElements;
    mywindow.print();
    mywindow.close();
    }
            
}


</script>
 
<!--  <form> -->
<!--  <INPUT type="hidden" id="singledate" name="targetamount.singledate" /> -->
<!--  </form> -->
  <form action="SendSMSForAllEmployee.action" method="POST" class="form-horizontal" >
<div class="page-content-wrapper">
		<div class="page-content" >
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
						<div class="portlet-title"  >
							<div class="caption" >
								<i class="fa fa-gift" style="font-weight: bold;font-size:15pt;">Send SMS</i>
							</div>
						</div>
						
						<div class="portlet-body">
						<b>
								<font color="green"> <s:actionmessage/></font>
								<span id="errorMsg" style="color:red;word-wrap: break-word;" ><s:actionerror/></span>
							</b>
						<div class="table-scrollable">
						
						
						
						
						
						
						
                           <div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Division<font
											color="red">*</font></label>
										<div class="col-md-4">
											<s:select list="divisionlist" id="divisionlist1" 
											name="divisionlist1"  
												cssClass="select2_category form-control"  
												 onchange="getDepot(this.value)"  headerKey="0" headerValue="---Select---" ></s:select>  
												 
										</div>
									</div>
								
                             
								<div class="form-group">
									<label class="col-md-3 control-label">Depot<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select  id="depotlist1" class="select3_category form-control" name="depotlist1" onchange="getDisignation(this.value)" 
 											> 
											<option value="0">---Select---</option>
 										</select> 
									</div>
 								</div> 
 								
 								
                          
							 
								
									<div class="form-group">
									<label class="col-md-3 control-label">Designation Type:<font
										color="red">*</font></label>
									<div class="col-md-4">
<%-- 										<s:select list="disignationTypelist" id="disignationType"  --%>
<%-- 											name="disignationType"   --%>
<%-- 												cssClass="select2_category form-control"   --%>
<%-- 												   headerKey="-1" headerValue="---Select---" ></s:select>   --%>

                                         <select  id="disignationType" class="select3_category form-control" name="disignationType" 
 											> 
											<option value="-1">---Select---</option>
 										</select> 
                                       
									</div>
 								</div> 
 								<div class="form-group">
													<label class="col-md-3 control-label">Subject:<span class="required"> * </span></label>
													<div class="col-md-4">

                                                      <input class="form-control input-lg" placeholder=""
													id="subject" name="subject" type="text"
													/>
												
												</div>
												
												
												</div>
 								
 								<div class="form-group">
													<label class="col-md-3 control-label">Description:<font
											color="red">*</font></label>
													<div class="col-md-4">
								  
										       <TEXTAREA class="form-control input-lg" placeholder="" id="description" name="description" type="text" maxlength="150"></TEXTAREA> 
												
												</div>
											  </div>
								
						  
                     
							
						 <script type="text/javascript"> 
 	                     function tabletoExcel(btnExport){
 	                    
 	                    	 var divElements = document.getElementById("header").innerHTML;
 	                      var  divElements = document.getElementById("ticketconsuptionid").innerHTML;
 	                        
 	                        var noOfTableExist = 0;
 	                        try{
 	                    		$("#ticketconsuptionid table").each(function(){
 	                    			noOfTableExist++;
 	                    		});
 	                    		
 	                    		console.log("Total no of tables : " + noOfTableExist);
 	                    		/* If two table exist  then remove the last table on print click*/
 	                    		if(noOfTableExist >= 2){
 	                    			divElements = divElements.substring(0, divElements.indexOf("</table>") + 8) + "</div></div>";
 	                    		}
 	                    	}catch(err){
 	                    	    console.log("ExceptionCaught : " + err);
 	                    	}

 	                    	 
 	                   var downloadLink = document.createElement("a"); 
 	                  downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(divElements); 
 	                  downloadLink.download = "Schedule Wise EPKM Report.xls"; 
 	                  document.body.appendChild(downloadLink); 
 	                  downloadLink.click(); 
 	                  document.body.removeChild(downloadLink);
 	                  }</script>	
					
							
					 <div class="form-group">
					 <label class="control-label col-md-3"></label>
                     <div class="col-md-3" id="">
                     </div>
                     </div>
                     </div>
<!--                      </form> -->
						
						
						
						
						
							

<%-- 							<div id="NoData" align="center"><span><lable>No Data Found</lable></span></div> --%>

                         <div align='center'>
					<button type="button" class="btn green" onClick="getSmsData()">Submit</button> 
					
					
<!-- 					<button type="button" id="btnExport" class="btn green" onclick="tabletoExcel();"> EXPORT </button> -->
<%--                    <span><input type='button' class='btn green' id='button1' onclick='printhiv()' value='Print' /></span>&nbsp;<span> --%>
<%-- 					 <input type='button' class='btn green' id='button1' name='rawprint' onclick='rawPrint()' value='RawPrint' /></span></div> --%>
					<!-- END EXAMPLE TABLE PORTLET-->
					
				</div>
                        
							</div>
							<div id="ticketconsuptionid" style="display: none">
							<div class="portlet box blue">
					
					<div class="portlet-body" id="route_deviation_id"
						style="overflow: hidden;display: none">
						<div class="portlet-body" id="scheduleprint3" style='height:310px; overflow-y:scroll; display:block;'>
							<table class="table table-bordered sticky-thead"
								id="forSmsData" style="max-height: 60px;">
								<thead>
											<tr>
												<th><input type='checkbox' id='selectAll'  onclick="select(this.value)"/>&nbsp;&nbsp;</th>
												<th>Name</th>
												<th>Designation</th>
												<th>Mobile Number</th>
												<th>Depot</th>
											
											</tr>
										</thead>
								
							</table>
							
						</div>
						 <div align='center'>
					<button type="submit" class="btn green" >Send</button> 
					
				</div>
						
					</div>
				</div>
							
							
							</div>
							
						</div>
					</div>
<!-- 					 <div id="ticketconsuptionid"></div> -->
<%-- 					<div align='center'><span><input type='button' class='btn green' id='button1' onclick='printhiv()' value='Print' /></span>&nbsp;<span><input type='button' class='btn green' id='button1' name='rawprint' onclick='rawPrint()' value='RawPrint' /></span></div> --%>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
 			</div>
		
			
			<!-- END PAGE CONTENT-->
		</div>
		
	</div>
	
 </form>	
	