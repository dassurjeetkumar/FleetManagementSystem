  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
 <script type="text/javascript" src="assets/admin/pages/scripts/jquery-1.5.js"></script>
    <script type="text/javascript" src="assets/admin/pages/scripts/map_nudi_baraha.js?v=3"></script>
    <script type="text/javascript" src="assets/admin/pages/scripts/helper.js?v=3"></script>
    <script type="text/javascript" src="assets/admin/pages/scripts/a2u.js?v=4"></script>
    <script src="assets/admin/pages/scripts/kannada.js"
	type="text/javascript"></script>

<script src="assets/admin/pages/scripts/converter.js"
	type="text/javascript"></script>
<script>

function getScheduleData(){
	
	
// 		var dd1=$("#startdate").val();
// 		var dd2=$("#enddate").val();
		var busstop=$("#busstopid").val();
		alert("Bus Stop name="+busstop);
		 document.getElementById('input-box').value=busstop;

//  			 document.getElementById('ticketconsuptionid').innerHTML="";
//  		}
	
}
function update(){
	
	var kanada=$("#last-box").val();
	alert("kanada=="+kanada);
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

function rawPrint(){
	//alert("hdfdfdf");

	/* var htmlCode = "<applet archive='/doa/applet/IqPrint.jar' name='print' code='IqPrint' width=0 height=0><param name=httpUrl value='/doa/Ticketing/CashierReport.txt'><param name=printText value=''><param name=printhevice value='epson'><param name=printSubmitUrl value=''><param name=paginationRequired value='Y'><param name=directPrint value='Y'><param name=displayRequired value='N'></applet>";
	$("#resultset").html(htmlCode); */
        $.ajax({
          type: "get",
          url:"Ticketing/RawPrint.jsp",
          data:"DetailsOfServiceTaxAmount=new",
            success: function(result){
            	$("#dailyticketraw").html(result);
            	//alert(result);
              
            }
        });
}
</script>
 
 <form>
 <INPUT type="hidden" id="singledate" name="targetamount.singledate" />
 </form>
 
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
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>SCHEDULE-WISE EPKM STATEMENT
							</div>
						</div>
						
						<div class="portlet-body">
						<div class="table-scrollable">
						
						<table>

						
						
								<tr>
									<td><label class="control-label col-md-4">Bus Stop Id :</label>
										<div class="col-md-4">
											<div class="col-md-4">
									<div class="input-group input-medium"  >
								   <select id="busstopid" name="busstop"  class="select2-container select2_category form-control" >
											<s:iterator value="busstoplist" >
												<option id="<s:property   value="busStopNamenudi" />" value='<s:property value="busStopNamenudi" />'>
													<s:property value="id" />
												</option>
											</s:iterator>
										</select>
										<script> 
 											var busid ="<s:property value='busstop'/>";
 											if(busid!=""){ 
 												document.getElementById(busid).selected= true; 
 											} 
 											
										</script> </div>
									</div>
										</div>
										</td></tr>
										
									<td><label class="control-label col-md-4"></label>
									<button  type="submit" class="btn green"
											onClick="getScheduleData()">Submit</button></td>
								<tr>
								<td><label class="control-label col-md-4">Nudi Name :</label>
										<div class="col-md-4">
											<div class="col-md-4">
									<div class="input-group input-medium"  >
								   <INPUT type="text" id="input-box" name="input-boxs">
										 </div>
									</div>
										</div>
										</td>
										<td><label class="control-label col-md-4"></label>
									<button id="convert-button" type="submit" class="btn green"
											>Convert</button></td>
								
								</tr>
								<tr>
								<td><label class="control-label col-md-4">Kannada Name :</label>
										<div class="col-md-4">
											<div class="col-md-4">
									<div class="input-group input-medium"  >
								   <INPUT type="text" id="output-box" name="output-box"
								    onclick="javascript:print_many_words_data('output-box','last-box')"
                			        onchange="javascript:print_many_words_data('output-box','last-box')"
                							        	 value="<s:property value="orgchart.org_code"/>" onblur="spclchar(this);" >
										 </div>
									</div>
										</div>
										</td>
										</tr>
										
										<tr>
								<td><label class="control-label col-md-4">Kannada Name 2 :</label>
										<div class="col-md-4">
											<div class="col-md-4">
									<div class="input-group input-medium"  >
								   <INPUT type="text" id="last-box" name="last-box" maxlength="80" 
												value='<s:property value="orgchart.org_code_kannada"/>' onclick="update()" >
										 </div>
									</div>
										</div>
										</td>
										</tr>
							</table>
						
						

							</div>
						</div>
					</div>
					 <div id="ticketconsuptionid"></div>
					<div align='center'><span><input type='button' class='btn green' id='button1' onclick='printhiv()' value='Print' /></span>&nbsp;<span><input type='button' class='btn green' id='button1' name='rawprint' onclick='rawPrint()' value='RawPrint' /></span></div>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
 			</div>
			<div id="dailyticketraw">
			</div>
			
			<!-- END PAGE CONTENT-->
		</div>
		
	</div>
	<script type="text/javascript">
      $(document).ready(function(){
    	  converter_data_init('input-box','output-box');
      });
    </script>
	
	