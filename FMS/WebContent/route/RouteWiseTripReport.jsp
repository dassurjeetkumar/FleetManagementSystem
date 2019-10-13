  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>

<head>
<script src="assets/global/plugins/jquery-1.11.0.min.js" value='1'type="text/javascript"></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.9.1/themes/black-tie/jquery-ui.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
 </head>
<script>



function getRouteWiseData(){

	var routeId=$("#project").val();

        $.ajax({
            
            type: "post",
            url: '<%=request.getContextPath()%>/RouteWiseTriplistShow.action?routeId='+routeId,
            success: function(result) {
            	$('#pageLoader').hide();
            	 document.getElementById('ticketconsuptionid').innerHTML=result;
                fixHeader();
            }
        }); 

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

function getRoute(val){
	var result = "";
	var availableTags=[];	
	$.ajax({
	    type : 'GET',
	    data :'json',
	    url  :  "<s:url action='getRouteListForTripWise'/>",
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
		        	$( "#project-id" ).val( ui.item.value );
		        	return false;
	        	}
	        }).data( "ui-autocomplete" )._renderItem = function( ul, item ) {
	        	return $( "<li>" )
	        	.append( "<a>" + item.route_number + "</a>" )
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

</script>

<script>                 	
				               	
   	        	function tabletoExcel(btnExport){ 
   	        		
   	        	    var htmltable= document.getElementById('btnExport'); 
   	        	  var divElements = document.getElementById("header").innerHTML;
   	         var divElements = document.getElementById("ticketconsuptionid").innerHTML;
   	           
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
   	            downloadLink.download = "Route Wise Trip Report.xls";
   	            document.body.appendChild(downloadLink); 
   	            downloadLink.click(); 
   	            document.body.removeChild(downloadLink);
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
			

			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			
			
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>Route Wise Trip Report
							</div>
						</div>
						
						<div class="portlet-body">
						 <form action="" method="post" class="form-horizontal">
					<div class="form-body">
 			
								<div class="form-group">
													<label class="control-label col-md-3">Route Number :</label>
													<div class="col-md-3">
														<input class="form-control" tabindex="1" id="project" placeholder="Enter Route No to Search" 
														 name="project" type="text"	onkeyup="getRoute(this.value)"  />
														 <input id="project-id" type="hidden">
								 						<input id="project-id1" type="hidden">
								 						<input id="project-id2" type="hidden">
														</div> 
													
														</div>
								
							
						
						
								</div>
								
						
							<div align="center">
					<button type="button" class="btn green" onClick="getRouteWiseData()">Submit</button>
					<span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span>&nbsp;
					<span><button type="button" class="btn green" id="btnExport" filename= "report" onclick="tabletoExcel();"> EXPORT </button></span>&nbsp;<span> 
					<div id="ticketconsuptionid"></div>
					
					</div>
					</form>
						</div>
					</div>
					
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
				
			</div>
			
			<!-- END PAGE CONTENT-->
		</div>
	</div>
	
	
	
	
