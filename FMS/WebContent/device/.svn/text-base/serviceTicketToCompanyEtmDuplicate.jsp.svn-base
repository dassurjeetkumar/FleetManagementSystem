 <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
</head>
<body>
<div class="page-content-wrapper">
	<div class="page-content">
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
					Service Ticket To Print
				</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN EXAMPLE TABLE PORTLET-->
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i>View Service Ticket To Print
						</div>
						<div class="actions">
						<div class="btn-group">
							<a href="#" class="btn blue" onclick="callPrint()"> <i
								class="fa fa-eye"></i> Print
							</a>
							
							<a href="#" class="btn green" onclick="callCancel()"> <i
								class="fa fa-eye"></i> Cancel
							</a>

						</div>
						</div>		
					</div>
					<div class="portlet-body">
						<form action="" method="post">
							<b>
								<font color="green"> <s:actionmessage/></font>
								<font color="red"> <s:actionerror/></font>
							</b>
							<table class="table table-striped table-bordered table-hover"
								id="serviceTableDuplicate">
								
								<thead>
									<tr>
										<th><input type='checkbox'   onclick="select(this.value)"/>&nbsp;&nbsp;</th>
										<th>Docket No</th>
										<th>Issuer Name</th>
										<th>Receiver Name</th>
										<th>Docket Date</th>
									</tr>
								</thead>
							</table>
							
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
function callPrint(){
	var cnt = $(":checkbox:checked").length;
	var val;
	var i=0;
	if (cnt == 0) {
		bootbox.alert("Please Select Checkbox To Print");
	} else if (cnt > 1) {
		bootbox.alert("Please Select One Checkbox To Print");
	} else {
		$("input[type='checkbox']:checked").each(
				function() {
                    
					val = this.value;
				});
		//alert("data=="+data);
		document.forms[0].action = 'serviceTicketToCompanyduplicate.action?value='+val;
		document.forms[0].submit();
	
}
}
function callCancel(){
	
	window.location.href='getEtmDeviceView.action';
}
/* $(document).ready(function() {
	
	$("#selectAll").click(function(e) {
	    //e.preventDefault();
	    if($("#selectAll").prop('checked')){
	    	$('.logsheetId').prop('checked', true);
	    }else{
	    	$('.logsheetId').prop('checked', false);
	    }
	  /*   if (ischecked == false) {
	        $("input:checkbox.checkbox1").attr("checked","checked");
	        ischecked = true;
	    } else {
	        $("input:checkbox.checkbox1").removeAttr("checked");
	        ischecked = false;
	    } */
	    /*	});
}); */
</script>
</body>