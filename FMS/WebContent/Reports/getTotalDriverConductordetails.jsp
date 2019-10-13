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
	
	var divisiontype=$("#divisiontype").val();
	var date1=$("#date1").val();
	
		$.ajax({
        type: "post",
        url: '<%=request.getContextPath()%>/getTotalDriverConductordetails1.action?divisiontype='+divisiontype+'&date1='+date1,
          success: function(result) {
       	 document.getElementById('depotss').innerHTML=result;
          }
     });
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
							<i class="fa fa-globe"></i>Conductor Less schedule Details
						</div>
						
					</div>

					<div class="portlet-body">
						<div class="table-scrollable">
<input type='hidden' id="divisiontype"  value='<s:property value="divisiontype"/>'/>
<input type='hidden' id="date1"  value='<s:property value="date1"/>'/>

<div id="depotss" ></div>
							
							
						</div>
						</div>
					</div>
					
					</div>  
					</div>
				</div>
			</div>
		</div>
	
	
	
	
	
