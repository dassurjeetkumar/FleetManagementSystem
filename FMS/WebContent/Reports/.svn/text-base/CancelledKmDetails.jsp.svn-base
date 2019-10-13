  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<link rel="stylesheet" href="css/page-style.css" type="text/css"
	media="screen" />
<link rel="shortcut icon" href="favicon.ico" type="../images/x-icon" />
<link rel="stylesheet" href='./css/metallic.css' type="text/css" />
<script src="./js/jquery-1.10.2.js">
	
</script>

<script type="text/javascript">
window.onload=jsFunction;

function jsFunction(){
	var startdate1 = $('#startdate').val();	
	var enddate1 = $('#enddate').val();
	var depotid = $('#depotid').val();
//	alert("hiiii");
//alert(startdate1);
//alert(enddate1);
//alert(depotid);

		$.ajax({
        type: "post",
        url: '<%=request.getContextPath()%>/getcancelleddetailsname1.action?startdate1='+startdate1+'&enddate1='+enddate1+'&depotid='+depotid,
          success: function(result) {
       	 document.getElementById('depotss').innerHTML=result;
          }
     });
}
// function jsFunction3(depotid){
// 	var depotid = $('#depotid').val();	
// 	var startdate1 = $('#startdate').val();	
// 	var enddate1 = $('#startdate').val();	
	
<%-- 		var mywindow = window.open("<%=request.getContextPath()%>/getdetailsname.action?depotid="+depotid+"&startdate1="+startdate1+"&enddate1="+enddate1); --%>
		
// }

function printDiv() {     
    
    //  document.getElementById("print").style.visibility='hidden';     
    var divElements = document.getElementById("header").innerHTML;
    divElements += document.getElementById("printid").innerHTML;

    var mywindow = window.open("<%=request.getContextPath()%>/Print.jsp");
   
    mywindow.onload = function() {
    mywindow.document.body.innerHTML=divElements;
    mywindow.document.body.innerHTML=divElements;
    mywindow.print();
    mywindow.close();
    }
            
}

</script>
</head>
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
			
			
			<div class="row">
			<div class="col-md-12">
				<!-- BEGIN EXAMPLE TABLE PORTLET-->
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i>ScheduleWise Cancelled KM Report 
						</div>
						
					</div>

					<div class="portlet-body">
						<div class="table-scrollable">
<input type='hidden' id="startdate"  value='<s:property value="startdate"/>'/>
<input type='hidden' id="enddate"  value='<s:property value="enddate"/>'/>
<input type='hidden' id="divisionid"  value='<s:property value="divisionid"/>'/>
<input type='hidden' id="depotid"  value='<s:property value="depotid"/>'/>
<div id="depotss" ></div>
							
							
						</div>
						</div>
					</div>
					
					</div>  
					</div>
				</div>
			</div>
		</div>
	
	
	
	
	
