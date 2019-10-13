<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>

<body>

<div class="page-content-wrapper">
	<div class="page-content">
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
					Create Bank Remittance Details
				</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN EXAMPLE TABLE PORTLET-->
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i> Create Bank Remittance Details
						</div>
					</div>
					<div class="portlet-body form">
						<b>
							<font color="green"> <s:actionmessage/></font>
							<font color="red"> <s:actionerror/></font>
						</b>
						<form action="saveBankRemittanceDetails" class="form-horizontal " method="post">
						<input type="hidden" name="createBankRD" value="1" >		
							<div class="form-body">
							
								<div class="form-group">
									<label class="control-label col-md-3"> Depot no : <span class="required"> * </span></label>
									<div class="col-md-3">
									<s:select list="depotlist" id="deptNo" name="brmodal.depotno" cssClass="form-control input-medium select2me" ></s:select>
									<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('depot').get(0)}" />
									</span>
									</div>
									<script>
											
											var dept ="<s:property value='brmodal.depotno'/>";
											
											if(dept!=""){
													document.getElementById(dept).selected= true;
											}
										</script>
								</div>
								
								<div class="form-group">
									<label class="control-label col-md-3"> Bank Name : <span class="required"> * </span></label>
									<div class="col-md-3">
										<input type="text" class="form-control"	id="bname" name="brmodal.bankname" 
										value='<s:property value="brmodal.bankname" />' maxlength="20" >
									<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('bankname').get(0)}" />
									</span>
									</div>
								</div>
								<div class="form-group"">
									<label class="control-label col-md-3">Account Number : <span class="required"> * </span></label>
									<div class="col-md-3">
										<input type="text" class="form-control"	id="anumber" name="brmodal.accnumber" 
										value='<s:property value="brmodal.accnumber" />' maxlength="20">
									<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('accnum').get(0)}" />
									</span>
									</div>
								</div>
								<div class="form-group" id="pname">
									<label class="control-label col-md-3"> Division : <span class="required"> * </span></label>
									<div class="col-md-3">
										<input type="text" class="form-control"	id="division" name="brmodal.division" 
										value='<s:property value="brmodal.division" />' maxlength="20">
									<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('divisn').get(0)}" />
									</span>
									</div>
								</div>
								<div class="form-group" id="addr">
									<label class="control-label col-md-3"> Address : <span class="required"> * </span></label>
									<div class="col-md-3">
										<s:textarea cssClass="form-control" name="brmodal.addr" id="addr" ></s:textarea>
									<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('addre').get(0)}" />
									</span>
									</div>
								</div>
									<div class="form-group">
									<div class="col-md-offset-4 col-md-9">
										<button type="submit" class="btn blue">Save</button>
										<button type="button" class="btn default" onclick = "goView()">Cancel</button>
									
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
<script>

	function goView()
	{
		document.forms[0].action = "bankRemittanceDetails.action";
		document.forms[0].submit();
	}


</script>

