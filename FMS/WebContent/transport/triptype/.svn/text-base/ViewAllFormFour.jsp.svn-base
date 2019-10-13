  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
 
<script>
var i=0;
function getDepot(orgId){
	 $('#select2-chosen-2').html("Select");
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 var val=document.getElementById('divisionlist').value;
			 if(val!=0) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/getallformdepot?val=' + val,
				success : function(result) {
					document.getElementById('depotlist').innerHTML = result;
				}
			});
		}

	}
</script>
<script>

function getformfour(){
	var divid=$("#divisionlist").val();
	var id=$("#formfourtype").val();
	var depot=$("#depotlist").val();
	var schid=$("#scheduletypelist").val();
	if(divid==0){alert("please select the division");}
	 else if(depot==0){alert("please select depot");}
	 else if(id==0){alert("please select form four type");}
	 else if(schid==0){alert("please select schedule type");}
	else{
 			 $('#pageLoader').show();
        $.ajax({
        
            type: "post",
          
            url: '<%=request.getContextPath()%>/AjaxgetAllfv.action?id='+id+'&depot='+depot+'&schid='+schid,
            success: function(result) {
            	$('#pageLoader').hide();
                document.getElementById('ticketconsuptionid').innerHTML=result;
                fixHeader();
            }
        });
        }
		
}
function validate(){
	var id=$("#formfourtype").val();
	if(id==0){alert("Please Select The Form Four Type");}
	else{
		alert();
		$("#formfour").submit();
	}
}
</script>
<script>
		
function printDiv() {     
    
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
		/* if(noOfTableExist >= 200){
			divElements = divElements.substring(0, divElements.indexOf("</table>") + 8) + "</div></div>";
		} */
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
 	                    		/* if(noOfTableExist >= 2){
 	                    			divElements = divElements.substring(0, divElements.indexOf("</table>") + 8) + "</div></div>";
 	                    		} */
 	                    	}catch(err){
 	                    	    console.log("ExceptionCaught : " + err);
 	                    	}

 	                    	 
 	                   var downloadLink = document.createElement("a"); 
 	                  downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(divElements); 
 	                  downloadLink.download = "all form four details.xls"; 
 	                  document.body.appendChild(downloadLink); 
 	                  downloadLink.click(); 
 	                  document.body.removeChild(downloadLink);
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
								<i class="fa fa-globe"></i>VIEW ALL FORM FOUR
							</div>
						</div>
						
						<div class="portlet-body">
						

							<!-- 						start -->

<!-- <table> -->
                   <form action="" method="post" class="form-horizontal" id="formfour">
                           <div class="form-body">
					               	<div class="form-group">
										<label class="col-md-3 control-label">Division<font
											color="red">*</font></label>
										<div class="col-md-3">
											<s:select list="divisionlist" id="divisionlist" 
											name="org_chart_id"  
												cssClass="select2_category form-control"  
												 onchange="getDepot(this.value)" ></s:select>  
												 
										</div>
										<script>
										getDepot("");
										</script>
									</div>
								
                               <div class="form-group">
									<label class="col-md-3 control-label">Depot<font
										color="red">*</font></label>
									<div class="col-md-3">
										<select list="depotlist" id="depotlist" class="select2_category form-control" name="depotlist1" 
 											> 
											<option value="0">--Select--</option>
 										</select> 
									</div>
 								</div>
                          <div class="form-group">
										<label class="col-md-3 control-label">Schedule Type:<font
											color="red">*</font></label>
										<div class="col-md-4">
											<s:select list="scheduletype" id="scheduletypelist" 
											name="scheduletypelis"  
												cssClass="select2_category form-control"  
												   headerKey="0" headerValue="---select---"></s:select>  
												 
										</div>
									</div>
					<div class="form-group">
										<label class="col-md-3 control-label">Form Four Type:<font
											color="red">*</font></label>
										<div class="col-md-4">
											<s:select list="formfourtypelist" id="formfourtype" 
											name="formfourtype"  
												cssClass="select2_category form-control"  
												   headerKey="0" headerValue="---select---"></s:select>  
												 
										</div>
									</div>
									
						<!-- end -->
							
							
					
							
					 <div class="form-group">
					 <label class="control-label col-md-3"></label>
                     <div class="col-md-3" id="">
                     </div>
                     </div>
                     </div>
                    
                     <div align='center'>
					<button type="button" class="btn green" onclick="getformfour()">Submit</button> 
<button type="button" id="btnExport" class="btn green" onclick="tabletoExcel();"> EXPORT </button>
<span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span>&nbsp;<span>
				</div> </form>
					<div id="ticketconsuptionid"></div>
					

			</div>
			
			<!-- END PAGE CONTENT-->
		</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	</head>
	</html>

	
	
	
