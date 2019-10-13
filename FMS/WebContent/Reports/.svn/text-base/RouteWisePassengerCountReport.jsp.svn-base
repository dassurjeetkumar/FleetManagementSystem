<style type="text/css">
.help-block,ul,li {
	list-style: none;
}
</style>

<script src="https://code.jquery.com/jquery-1.11.3.min.js" type="text/javascript"></script>
	
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
<link rel="stylesheet" href="https://code.jquery.com/ui/1.9.1/themes/black-tie/jquery-ui.css">
<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" /> --%>
	
	
<%@ taglib prefix="s" uri="/struts-tags"%>

<script >
function getRoute(val){
	var result = "";
	var availableTags=[];	
// 	$('#startdate').hide();
// 	$('#endate').hide();
	$.ajax({
	    type : 'GET',
	    data :'json',
	    url  :  "<s:url action='getRouteLists'/>",
	    data :{
	    	routename: val,
	    },
	    success: function(data){
	        data =eval(data);
	        result=data;
	        $( "#route_no" ).autocomplete({
		        	minLength: 0,
		        	source: result,
		        	focus: function( event, ui ) {
		        	$( "#route_no" ).val( ui.item.route_number  );
		        	return false;
	        	},
	        	select: function( event, ui ) {
		        	$( "#route_no" ).val( ui.item.route_number );
		        	$( "#project-id" ).val( ui.item.route_name );
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


<div class="page-content-wrapper">
	<div class="page-content">
		<div class="row">
		
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
		
		
		</div>
		
		<div class="row">
			<div class="col-md-12">
				<div class="portlet box grey-cascade">

					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-bars"></i> Route Wise Passenger Count Report
						</div>
						<!-- <div class="tools">
							<a href="" class="collapse"></a> <a href="javascript:;"
								class="reload"> </a>
						</div> -->
				

					</div>
					
					<div class="portlet-body form">

						<form action="#" class="form-horizontal form-row-seperated"
							method="post">
							<FONT color="green" style="font-weight:bold"><s:actionmessage /></FONT>
							<FONT color="red" style="font-weight:bold"><s:actionerror /></FONT>


							

							<div class="form-group">
							<label class="col-md-2 control-label">StartTime</label>
									<div class="col-md-3">
					
						<div class="input-group date form_datetime">
							<input type="text" size="16" readonly name="startdate"
								id="startdate" class="form-control" value='<s:property value="startdate"/>'> <span
								class="input-group-btn">
								<button class="btn default date-set" type="button">
									<i class="fa fa-calendar"></i>
								</button>
							</span>
						</div>
					</div>
										
										<label class="col-md-2 control-label">EndTime</label>
								<div class="col-md-3">
					<div class="input-group date form_datetime">
						<input type="text" size="16" readonly name="enddate" id="enddate" value='<s:property value="enddate"/>'
							class="form-control"> <span
							class="input-group-btn">
							<button class="btn default date-set" type="button">
								<i class="fa fa-calendar"></i>
							</button>
						</span>
					</div>
										</div>
</div>

                       <div class="form-group">
									<label class="control-label col-md-3">Route Number :</label>
									<div class="col-md-3">
										<input class="form-control" tabindex="1" id="route_no"
											placeholder="Enter Route No to Search" name="route_no" 
									
											type="text" onkeyup="getRoute(this.value)" 
											 /> <input id="project-id"
											type="hidden"><input id="project-id1" type="hidden"><input
											id="project-id2" type="hidden">
									</div>

								</div>
                            
                         
                            
                            
							<div class="form-actions fluid">
								<div class="col-md-offset-3 col-md-3">
									<button type="button" class="btn blue" onclick="getSkipStop();">Submit</button>
								

								</div>
								<div class="col-md-1"id="printbutton">
											<input type='button' class="btn blue" id='button1' onclick='printDiv()' value='Print' />													
										</div>
										<div class="col-md-1">
											<button type="button" class='btn blue' id="btnExport" filename= "report" onclick="tabletoExcel();"> EXPORT </button>
																							
										</div>
							</div>
							
							  <div id="header" class="portlet-body" style="display: none; visibility: hidden;">
										<h4 style="margin-left:350px;">Route Wise Passenger Count Report</h4>
									<br />
									<div id="headerdetails" style="margin-left:0px;">
									 <table ID="mytable" style="width:60%;border:none;">

														
														
													</table>
                            	      

													 <br/><br/> 
                                    
                                </div>
                                </div>
                                
                                
                             



                         
          

						</form>
						
					</div>
		
				</div>
				<div id="ticketconsuptionid"></div>
				
				
			</div>
			
		</div>
	</div>
</div>


<script>


function getSkipStop() {
	
	
	var startdate = document.getElementById("startdate").value;

	var enddate = document.getElementById("enddate").value;
	 var date1= moment(startdate).format('DD-MM-YYYY-HH-mm');
	 var date2= moment(enddate).format('DD-MM-YYYY-HH-mm');
	 var data1=date1.split("-");
	 var dd1=data1[0];
	 var mm1=data1[1];
	 var yy1=data1[2];
	 var hh1=data1[3];
	 var min1=data1[4];
	 var time1=Number((hh1*60))+Number(min1);
	 var d1 = new Date(yy1,mm1,dd1,hh1,min1,0,0);  //Date object creation in JS
	 
// 		 alert("time1=="+time1);
	 
	 var data2=date2.split("-");
	 var dd2=data2[0];
	 var mm2=data2[1];
	 var yy2=data2[2];
	 var hh2=data2[3];
	 var min2=data2[4];
	 var time2=Number((hh2*60))+Number(min2);
	 var d2 = new Date(yy2,mm2,dd2,hh2,min2,0,0);   //Date object creation in JS
	 
	 var diff = d2.getTime() - d1.getTime();     // in milli second
	 var hDiff = diff / 3600 / 1000;              // in hours
 
	 var timediff=Number(time2)-Number(time1);
	 
// alert("time2=="+time2);
// alert("timediff=="+timediff);
	
	if(d1>d2){
		bootbox.alert("Start Time should not be greater than to End Time");
		return false;
	}
	if(timediff>120){
		 bootbox.alert("Time Difference between date should not be greater than 2 hours");
		return false;
	}
	if($('#route_no').val()==0){
		bootbox.alert("Please select Route Number");
		return false;
	}
	
	
	
	var dd1=$("#startdate").val();
//	alert(dd1);
var dd2=$("#enddate").val();
var route=$("#route_no").val();

//  alert("route"+route);
			
// 				var divId = document.getElementById("viewRole12");
// 				divId.style.visibility = 'visible';
				$('#pageLoader').show();
				 $.ajax({
			           type: "get",
			           url: '<%=request.getContextPath()%>/AjaxRouteWisePassengerCountReport.action?startdate='+startdate+'&enddate='+enddate+'&routeno='+route,
			           success: function(result) {
			        	   $('#pageLoader').hide();
// 			        	   alert("hi"+result);
			               
			                document.getElementById('ticketconsuptionid').innerHTML=result;
			                fixHeader();
			            }
			       }); 	
		
	}
	
	function printDiv() {     
		  document.getElementById("ticketconsuptionid").style.visibility='visible';  
	     document.getElementById("header").style.display='block';
	     document.getElementById("header").style.visibility='visible'; 
	    
	     
	     var divElements = document.getElementById("header").innerHTML;
	     divElements+= document.getElementById("ticketconsuptionid").innerHTML;
	     var mywindow = window.open("<%=request.getContextPath()%>/Print.jsp");
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
// 			document.getElementById("mapshow").style.visibility=''; 
// 			$(".mapClass").show();
	     
	             
	 }
	
	

	
		
		function tabletoExcel(btnExport){     
          	var htmltable= document.getElementById('btnExport');   
            $( "#header-fixed" ).remove();
            var inputHTML = "<table border='1'>";
            inputHTML += "<tr>";
            inputHTML += "<th  align='left'colspan='9'>Bangalore Metropolitan Transport Corporation</th>";
            inputHTML += "</tr>";
            inputHTML += "<th  align='left'colspan='9'>Route Wise Passenger Count Report </th>";
            inputHTML += "</tr>";
            inputHTML += "<tr>";
            inputHTML += "<th colspan='2' align='left'>Date Range:</th>";
            inputHTML += "<th colspan='7' align='left'>" + $("#startdate").val() + " to " + $("#enddate").val() + "</th>";
            inputHTML += "</tr>";
            inputHTML += "</table>";
            var htmltable = document.getElementById("ticketconsuptionid");
            var html = inputHTML + htmltable.outerHTML;
            var downloadLink = document.createElement("a");
            downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
            downloadLink.download = "Route Wise passenger Count.xls";
            document.body.appendChild(downloadLink);
            downloadLink.click();
            document.body.removeChild(downloadLink);
        }
		
	
		
		
</script>
