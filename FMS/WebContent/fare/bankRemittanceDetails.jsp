<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>

<script>
function callCreate() {
	document.forms[0].action = "CreateBankRemittanceDetails.action";
	document.forms[0].submit();
}

function callEdit() {
		var val = [];
		$(':input:checked').each(function(i) {
			val = $(this).val();
		});
		
		if (check(0)) {
			document.forms[0].action = "EditBankRemittanceDetails.action?value="+ val;
			document.forms[0].submit();
		}
}

function callCancel() {
	var val = [];
	$(':input:checked').each(function(i) {
		val = $(this).val();
	});
	
	if (check(0)) {
			bootbox	.confirm("Are you sure want to delete Bank Remittance Details?",function(result) {
			if (result == true) { 
				document.forms[0].action = "DeleteBankRemittanceDetails.action?value="+ val;
			document.forms[0].submit();
			}
	});
}
}

function check() {

	var chckbxCount = $("input:checked").length;
	if (chckbxCount > 0 && chckbxCount > 1) {
		bootbox.alert("Select only one Bank Remittance Details...!")
		return false;
	} else if (chckbxCount > 0 && chckbxCount == 1) {
		return true;
	} else {
		bootbox.alert("Please select Bank Remittance Details");
		return false;
	}

}

</script>

<div class="page-content-wrapper">
	<div class="page-content">
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
					Bank Remittance Details
				</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN EXAMPLE TABLE PORTLET-->
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">

							<i class="fa fa-globe"></i>View Bank Remittance Details
						</div>
						<div class="actions">
						<div class="btn-group">
							<a href="#" class="btn green" onclick="callCreate()"> <i
								class="fa fa-plus"></i> Create
							</a>
							 &nbsp;&nbsp;<a href="#" class="btn blue" onclick="callEdit()"> <i
								class="fa fa-pencil"></i> Edit
							</a> &nbsp;&nbsp; 
							<a href="#" class="btn red" onclick="callCancel()"> <i
								class="fa fa-times"></i> Delete
							</a> &nbsp;&nbsp;
						</div>
						
							</div>		
					</div>
					<div class="portlet-body">
						
							<b>
								<font color="green"> <s:actionmessage/></font>
								<font color="red"> <s:actionerror/></font>
							</b>
							
							
							<table class="table table-striped table-bordered table-hover"
								id="BankRemittanceDetailsTable">
								<thead>
								
									<tr>
										<th></th>
										<th>Id</th>
										<th>Depot</th>
										<th>Bank Name</th>
										<th>Account Number</th>
										<th>Division</th>
										<th>Address</th>
									</tr>
								</thead>
							</table>
					
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
$(document).ready(function() {
	window.history.pushState("","", "bankRemittanceDetails.action");
});

</script>