  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<script src="https://maps.googleapis.com/maps/api/js?client=gme-trimaxitinfrastructure&sensor=false&libraries=places,drawing&signature=CpSOeA5B6SzC2rxqlzd2JwenGX0="></script>
	<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
 	<script type="text/javascript" src="assets/vts/js/vts.js"></script> 
<script src="assets/global/plugins/jquery-1.11.0.min.js" value='1' type="text/javascript"></script>

<link rel="stylesheet" href="https://code.jquery.com/ui/1.9.1/themes/black-tie/jquery-ui.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
 
<script>

function getScheduleNoBasedOnDivision(orgId){
	//alert("hii");
	 $('#select2-chosen-2').html("Select");
	 $('#select2-chosen-3').html("Select");
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
			var routeid=$("#project").val();
		// alert(routeid);
		 
			
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/getScheduleNoBasedOnDivision?val=' + routeid,
				success : function(result) {
					document.getElementById('schedulelist1').innerHTML = result;
					
				}
			});
		

	}



function getRoute2(val){
	//	alert(val);
		var result = "";
		var availableTags=[];	
		$.ajax({
		    type : 'GET',
		    data :'json',
		    url  :  "<s:url action='getRouteList'/>",
		    data :{
		    	routename: val,
		    },
		    success: function(data){
		        data =eval(data);
		        result=data;
		        $( "#project" ).autocomplete({
			        	minLength: 0,
			        	source: result,
			        	focus: function( event, ui ) {
			        	$( "#project" ).val( ui.item.route_number  );
			        	return false;
		        	},
		        	select: function( event, ui ) {
			        	$( "#project" ).val( ui.item.route_number );
			        	$( "#project-id" ).val( ui.item.route_name );
			        	return false;
		        	}
		        }).data( "ui-autocomplete" )._renderItem = function( ul, item ) {
		        	var itemData = item.route_number.trim();
		        	
		        	return $( "<li>" )
		        	.append( '<a onclick="getRoute(\'' + itemData + '\');getRoute1(\'' + itemData + '\');">' + item.route_number + '</a>' )
		        	.appendTo( ul );
		       };
		    },
		    select: function (event, ui) {

		        alert("selected!");
		    },
		    change: function (event, ui) {

		        alert("changed!");
		    },
		    error : function(xhr, errmsg) {
		    	alert("No values found..!!"+errmsg);
		    }
		});
	}


function getDepot1(){
	
	   var div=$("#divisionlist").val();
	   var routeid=$("#project").val();
	   var dd1=$("#startdate").val();
		var dd2=$("#enddate").val();
		 if(div==0){
				alert("Please select division");
				return false;
			}
	 
	   if(dd1==""){
			alert("Please select startdate");
			return false;
		}
	   if(dd2==""){
			alert("Please select enddate");
			return false;
		}
	   if(routeid==""){
			alert("Please select route No");
			return false;
		}
	  // alert(routeid);
	  // var depot=$("#depotlist1").val();
		
 		var var1=$("#startdate").val();
 		//var servicetpe=$("#servicetpe").val();
 		var schedulelist1=$("#schedulelist1").val();
		var1=var1.split("-");
		var1=var1[2]+"-"+var1[1]+"-"+var1[0];
 		var var2=$("#enddate").val();
 		var2=var2.split("-");
 		var2=var2[2]+"-"+var2[1]+"-"+var2[0];
		//alert(var1+""+var2);
		
		 var d1 = Date.parse(var1);

		 
 	var d3=Date.parse(var2);
 		if (d1 <= d3){
 			 $('#pageLoader').show();
        $.ajax({
        
            type: "post",
            url: '<%=request.getContextPath()%>/AjaxRouteWiseScheduleRevenueReport.action?startdate='+dd1+'&enddate='+dd2+'&routeid='+routeid+'&divisionlist1='+div+'&schedulelist1='+schedulelist1,
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

function rawPrint(){
	//alert("hdfdfdf");

	/* var htmlCode = "<applet archive='/doa/applet/IqPrint.jar' name='print' code='IqPrint' width=0 height=0><param name=httpUrl value='/doa/Ticketing/CashierReport.txt'><param name=printText value=''><param name=printDevice value='epson'><param name=printSubmitUrl value=''><param name=paginationRequired value='Y'><param name=directPrint value='Y'><param name=displayRequired value='N'></applet>";
	$("#resultset").html(htmlCode); */
        $.ajax({
          type: "post",
          url:"Ticketing/RawPrint.jsp",
          data:"SchedulewiseEarnings=new",
            success: function(result){
            	$("#dailyticketraw").html(result);
            	//alert(result);
              
            }
        });
}
</script>
 
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
								<i class="fa fa-globe"></i>Route wise Schedules Revenue Report
							</div>
						</div>
						
						<div class="portlet-body">
						

							<!-- 						start -->

<!-- <table> -->
                   <form action="" method="post" class="form-horizontal">
                           <div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Division<font
											color="red">*</font></label>
										<div class="col-md-4">
											<s:select list="divisionlist" id="divisionlist" 
											name="divisionlist1"  
												cssClass="select2_category form-control"  
												  headerKey="0" headerValue="--select--"></s:select>  
												 
										</div>
									</div>
								
                             
							
 								
                          
							 <div class="form-group">
							  <label class="control-label col-md-3">From Date<font
										color="red">*</font></label>
								<div class="col-md-3">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years" > <!-- data-date-start-date="+0d" -->
								<input id="startdate" class="form-control" type="text" readonly="" size="16" name="rateMaster.effectiveStartDate"
								value="<s:property value='rateMaster.effectiveStartDate' />"
								>
								<span class="input-group-btn">
								<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
								</button>
								</span>
								<script>
// 										var curr_date=new Date().toJSON().slice(0,10);
//                                         curr_date=curr_date.split("-");
//                                         curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
//                                         var dtval=document.getElementById('startdate').value;	
                                        
//                                         if(dtval==''){
//                                         $('#startdate').val(curr_date);
//                                         }
								</script>
								</div>
								</div></div>
						  
							 <div class="form-group">
							  <label class="control-label col-md-3">Till Date<font
										color="red">*</font></label>
								<div class="col-md-3">
								<div class="input-group input-medium date date-picker"
															style="width: auto" data-date-format="dd-mm-yyyy"
															data-date-viewmode="years"  data-date-end-date="-2d"> 
								<input id="enddate" class="form-control" type="text" readonly="" size="16" name="rateMaster.effectiveStartDate"
								value="<s:property value='rateMaster.effectiveStartDate' />"
								>
								<span class="input-group-btn">
								<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
								</button>
								</span>
								<script>
// 										var curr_date=new Date().toJSON().slice(0,10);
//                                         curr_date=curr_date.split("-");
//                                         curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
//                                         var dtval=document.getElementById('enddate').value;	
                                        
//                                         if(dtval==''){
//                                         $('#enddate').val(curr_date);
//                                         }
								</script>
								</div>
								</div></div>
							
								<div class="form-group">
								
									
									<label class="col-md-3 control-label">Route Number <font
										color="red">*</font></label>
													<div class="col-md-3">
														<input class="form-control" tabindex="1" id="project" placeholder="Enter Route No to Search" 
														 name="project" type="text"	onkeyup="getRoute2(this.value)"  onChange="getScheduleNoBasedOnDivision()" />
														 <input id="project-id" type="hidden">
								 						<input id="project-id1" type="hidden">
								 						<input id="project-id2" type="hidden">
														</div>
														
									<label class="col-md-3 control-label">Schedule No<font
										color="red"></font></label>
									<div class="col-md-3">
										<select list="depotlist" id="schedulelist1" class="select2_category form-control" name="schedulelist1"
 											> 
											<option value="0">All</option>
 										</select> 
									</div> 
 								</div> 
						
						<!-- end -->
							
							
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
 	                  downloadLink.download = "Route wise Schedules Revenue Report.xls"; 
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
                     </form>
                     <div align='center'>
					<button type="submit" class="btn green" onClick="getDepot1()">Submit</button> 
					<button type="button" id="btnExport" class="btn green" onclick="tabletoExcel();"> EXPORT </button>
                   <span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span>&nbsp;<span>
<%-- 					 <input type='button' class='btn green' id='button1' name='rawprint' onclick='rawPrint()' value='RawPrint' /></span></div> --%>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
					<div id="ticketconsuptionid"></div>
					
<!-- 					 <div align='center'> -->
<!-- 					<button type="submit" class="btn green" onClick="getDepot1()">Submit</button>  -->
<!-- 					<button type="button" id="btnExport" class="btn green" onclick="tabletoExcel();"> EXPORT </button> -->
<%--                    <span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span>&nbsp;<span> --%>
<%-- <%-- 					 <input type='button' class='btn green' id='button1' name='rawprint' onclick='rawPrint()' value='RawPrint' /></span></div> --%>
<!-- 					END EXAMPLE TABLE PORTLET -->
<!-- 				</div> -->
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

	
	
	
