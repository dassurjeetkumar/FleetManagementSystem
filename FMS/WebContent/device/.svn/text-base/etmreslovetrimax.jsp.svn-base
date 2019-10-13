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
					Etm Resloved By Trimax
				</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN EXAMPLE TABLE PORTLET-->
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i>Etm Resloved By Trimax
						</div>
						<div class="actions">
						<div class="btn-group">
							<a href="#" class="btn blue" onclick="callslove()"> <i
								class="fa fa-eye"></i> Etm Resolved
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
							<%-- <table class="table table-striped table-bordered table-hover">
								<tr>
								<td colspan="1">Issuer Name</td>
								<td><input type="text" class="form-control" name="issuer" id="issuer" value='<s:property value="issuer" /> '></td>
								<td colspan="1">Receiver Name</td>
								<td><input type="text" class="form-control" name="receiver" id="receiver" value='<s:property value="receiver" /> '></td>
								</tr>
							</table> --%>						
							<table class="table table-striped table-bordered table-hover"
								id="etmresolvetrimaxTable">
								
								<thead>
									<tr>
									<!-- 	<th><input type='checkbox' id='selectAll'  onclick="selectall()"/>&nbsp;&nbsp;</th> -->
										<th></th>
										<th>Id</th>
										<th>ETM S/N</th>
										<th>SIM No</th>
										<th>Issue Log Date</th>
										<th>ETM Problem</th>
										
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
<SCRIPT type="text/javascript">
	$("#selectAll").change(function(){  //"select all" change
		
	    var status = this.checked; // "select all" checked status
	    $(':checkbox').each(function(){ //iterate all listed checkbox items
	    	//alert("inside");
	        this.checked = status; //change ".checkbox" checked status
	    });
	});

	$('.checkbox').change(function(){ //".checkbox" change
		//alert(2);
	    //uncheck "select all", if one of the listed checkbox item is unchecked
	    if(this.checked == false){ //if this item is unchecked
	        $("#selectAll")[0].checked = false; //change "select all" checked status to false
	    }
	   
	    //check "select all" if all checkbox items are checked
	    if ($(':checkbox:checked').length == $('.checkbox').length ){
	    	//alert(3);
	        $("#selectAll")[0].checked = true; //change "select all" checked status to true
	    }
	}); 

</SCRIPT>

<script>
function callslove(){
	/* var issuer=$("#issuer").val();
	var receiver=$("#receiver").val();
	
	var issue = issuer.replace(/^\s+/g, '');
	var receive = receiver.replace(/^\s+/g, ''); */
	//alert(issue);
	var cnt = $(":checkbox:checked").length;
	var val;
	var data=new Array();
	var i=0;
	/* if(issue==null || issue==""){
		bootbox.alert("Please enter Issuer Name");
	}
	else if(receive==null || receive==""){
		bootbox.alert("Please enter Receiver Name");
	} */
	 if (cnt == 0) {
		bootbox.alert("Please Select Checkbox To print");
	} 
	/* else if (cnt > 6) {
		bootbox.alert("Please Select Six Checkbox To Service Ticket");
	} */
	else {
		$("input[type='checkbox']:checked").each(
				function() {
                    
					val = $(this).val();
//						id=$("#vechileid").val();
//						id=$("#checkbox").val();
					//alert(val);
					data[i]=val;
					i++;
//						alert(data);
				});
		//alert("data=="+data);
		document.forms[0].action = 'resolveETM.action?value='+data,
		document.forms[0].submit();
	
}
}
function callCancel(){
	
	window.location.href='getEtmDeviceView.action';
}

</script>
</body>