  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
 
<script>


	function getDepot(orgId){
		// $('#select2-chosen-2').html("Select");
		// $('#select2-chosen-3').html("Select");
			//alert('Here');
			 /* var selectedValue = $('#form-control').val(); */
			 var val=document.getElementById('divisionlist').value;
				 if(val!=0) {
		        $.ajax({
		            type: "post",
		            url: '<%=request.getContextPath()%>/getDepotList?val=' + val,
					success : function(result) {
						document.getElementById('depotlist1').innerHTML = result;
						
					}
				});
			}

		}

</script>
<%--  <script>
	function getheader() {
		
		document.getElementById("startdate").innerHTML = new Date().toJSON().slice(0,10);
		}
 </script> --%>
<script>

function getData(){
/* 	//$("acc66axisnotsett").empty();
	$('#clskm').attr("style", "display:''");
	var divid=$("#divisionlist").val();
    table = $('#clskmtable');
    var oTable = table.dataTable({
    	"aLengthMenu" : [ [ 5,10, 15, 20, -1 ], [ 5,10, 15, 20, "All" ] // change
		// per
		// page
		// values
		// here
		],
		// set the initial value
		"iDisplayLength" : 5,
		"sAjaxSource" : "ajaxgetclsklmsdata.action?divid="+divid,
		"sPaginationType" : "bootstrap",
		"bDestroy" : true,
		"bSort" : true,
		"bFilter" : true,
		"bSelect" : true,
		"bPaginate" : true,
		"bInfo": true,
		"oLanguage" : {
			"sLengthMenu" : "_MENU_ records",
			"oPaginate" : {
				"sPrevious" : "Prev",
				"sNext" : "Next"
			}
		},
		"aoColumnDefs" : [ {
			'bSortable' : false,
			'aTargets' : [ 0 ]
		} ]
	});

	jQuery('#clskmtable_wrapper .dataTables_filter input').addClass(
			"form-control input-xsmall input-inline"); // modify table
	// search input
	jQuery('#clskmtable_wrapper .dataTables_length select').addClass(
			"form-control input-xsmall input-inline");	 */
	var divid=$("#divisionlist").val();
	var depotid=$("#depotlist1").val();
	var statusid=$("#statusid").val();
	var strdate=$("#startdate").val();
	if(strdate=='' || strdate==null){
		alert("please select Date");
	}
	else{
		 $('#pageLoader').show();
	        $.ajax({
	        
	            type: "post",
	            url: '<%=request.getContextPath()%>/AjaxEtmWiseStatus.action?startdate='+strdate+'&divid='+divid+'&depot='+depotid+'&statusid='+statusid,
	            success: function(result) {
	            	$('#pageLoader').hide();
	                document.getElementById('ticketconsuptionid').innerHTML=result;
	                fixHeader();
	            }
	        });
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
								<i class="fa fa-globe"></i>ETM Wise Status Report
							</div>
						</div>
						
						<div class="portlet-body">
<!-- 						


							<!-- 						start -->

                     <form action="" method="post" class="form-horizontal">
                        <div class="form-body">
								    <div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Division<font
											color="red">*</font></label>
										<div class="col-md-4">
											<s:select list="divisionlist" id="divisionlist"
												name="orgchart.org_chart_id"
												cssClass="select2_category form-control" headerKey="0" headerValue="---All---"
												 onchange="getDepot(this.value)"></s:select>
										</div>
									</div>
								</div>
								
                               <div class="form-group">
									<label class="col-md-3 control-label">Depot<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select list="depotlist" id="depotlist1" class="select2_category form-control" name="depotlist1" headerKey="0" headerValue="---All---" > 
												<option value="0">--All--</option>
 										</select> 
									</div>
 								</div> 
 								
 								<%--   <div class="form-group">
									<label class="col-md-3 control-label">Status<font
										color="red">*</font></label>
									<div class="col-md-4">
										<s:select list="statuslist" id="statusid" class="select2_category form-control" name="statusname"> 
											<option value="0">--All--</option>
 										</s:select> 
									</div>
 								</div> --%> 
 								    <div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Status<font
											color="red">*</font></label>
										<div class="col-md-4">
											<%-- <s:select list="statuslist" id="statusid"
												name="statusname"
												cssClass="select2_category form-control" headerKey="0" headerValue="---All---"
												></s:select> --%>
												  <select id="statusid"  class="select2_category form-control" name="statusname">
						                <option value="0">---All---</option>
										<!-- 	<option value="In Depot">In Depot</option> -->
											<option value="In Repair At Comp">In Repair At Comp</option>
												<option value="In Repair At Depot">In Repair At Depot</option>
												<option value="Lost ETM">Lost ETM</option>
											<!-- 	<option value="Swap Etm">Swap Etm</option> -->					              
							           </select>
										</div>
									</div>
								</div>
									<div class="form-group">
							  <label class="control-label col-md-3">Date:<font
										color="red">*</font></label>
								<div class="col-md-3">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" data-date-end-date="0d"> 
								<input id="startdate" name="dateaction" class="form-control" type="text" readonly="" size="16" name="rateMaster.effectiveStartDate" placeholder="Pick The Date" "
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
						
					
                     </div>
                     </form>
				
					 <div align='center'>
					<button type="submit" class="btn blue" onClick="getData(),getheader()">Submit</button>
					<span><input type='button' class='btn blue' id='button1' onclick='printDiv()' value='Print' /></span>&nbsp;
					<button type="submit" class="btn blue" onClick="tabletoExcel()">Export</button>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
				<div id="ticketconsuptionid"></div>			
			</div>
			
			<!-- END PAGE CONTENT-->
			
			
		
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
  downloadLink.download = "Etm Wise Status.xls"; 
  document.body.appendChild(downloadLink); 
  downloadLink.click(); 
  document.body.removeChild(downloadLink);
  }	</script>