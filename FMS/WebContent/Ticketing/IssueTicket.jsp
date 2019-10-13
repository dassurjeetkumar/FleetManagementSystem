<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.trimax.its.model.User"%>

<!DOCTYPE html>
<html>
<head>
<script>
</script>
<style>
h1.intro {
	color: red;
	font-size: 14px;
}
</style>
<script>

function goView() {

		/* var val = $("input[type='checkbox']").val(); */
		//alert(val);
		document.forms[0].action = 'ticketinv.action';
		document.forms[0].submit();


}
function 	showAllDenomTypes()
{
	//alert("hello");
	 var len= document.getElementById('denomination').options.length;
	 
	
       $.ajax({
           type: "post",
           url: '<%=request.getContextPath()%>/findAllDenomAction',
           success: function(result) {
               document.getElementById('denomination').innerHTML=result;
           }
       });
	
	 
}
function getOrgType()
{
 var len= document.getElementById('unittypeid').options.length;
 
	 if(len<=1 ) {
       $.ajax({
           type: "post",
           url: '<%=request.getContextPath()%>/findAllOrgTypeAction',
           success: function(result) {
               document.getElementById('unittypeid').innerHTML=result;
           }
       });
	 }
	
	
}
function getUnitNames()
{
	var e = document.getElementById("unittypeid");
	var strUser = e.options[e.selectedIndex].value;
	//alert(strUser);
	var len= document.getElementById('unitnameid').options.length;
	//alert(len);
	
	       $.ajax({
	           type: "post",
	           url: '<%=request.getContextPath()%>/findUnitNameAction?orgid='+strUser,
	           success: function(result) {
	               document.getElementById('unitnameid').innerHTML=result;
	           }
	       });
		
}




</script>
<script type="text/javascript" src="//www.google.com/jsapi">

</script>
</head>
<body>
	<div class="page-content-wrapper">
		<div class="page-content">
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box green">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Create Ticket Inventory
							</div>
							<div class="tools">
								
							</div>
						</div>
						<div class="portlet-body form">

							<!-- BEGIN FORM-->
							<form action="addToStock.action" class="form-horizontal"
								method="post">
								<div class="form-body">

									<%-- <div class="form-group">
										<label class="col-md-3 control-label">Denomination<font color="red">*</font></label>
										<div class="col-md-4">
											<div class="input-group">
											 <select class="form-control" name="tickinv.denoimination_type" id="denomination" onclick="showAllDenomTypes()">
												</select> 
												<span class="input-group-addon">
															    <i class="fa fa-user"></i>
															 </span>
												<input type="text" name="tickinv.denoimination_type"
													id="denomid" class="form-control"
													value="<s:property value="tickinv.denoimination_type" />">
												
											</div>
										</div>
									</div> --%>
									<div class="form-group">
										<label class="col-md-3 control-label">Denomination</label>
											<div class="col-md-4">
													<div class="input-group">
														<%-- <select class="form-control" name="denomMap" value="denomMap" id="denomination">
												          
										                 </select> --%>
														 
													<s:select list="denomMap" id="denomination" name="tickinv.denoimination_type" 
		cssClass="select2_category form-control" headerKey="0" headerValue="Select"></s:select>
															<s:if test="fieldErrors.get('denomination').size() > 0">
     								                                   <span style="color:red;"><b><s:property value="fieldErrors.get('denomination').get(0)" /></b></span>
									                        </s:if>	  
													         <span class="input-group-addon">
															    <i class="fa fa-user"></i>
															 </span>
												 		
													</div>
											</div>
										</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Denomination Key<font color="red">*</font></label>
										<div class="col-md-4">
											<div class="input-group">
												<input type="text" name="tickinv.key_number"
													id="keynumber" class="form-control"
													value="<s:property value="tickinv.key_number" />">
												<s:if test="fieldErrors.get('keynumber').size() > 0">
													<span style="color: red;"><b><s:property
															value="fieldErrors.get('keynumber').get(0)" /></b></span>
												</s:if>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Start Number<font color="red">*</font></label>
										<div class="col-md-4">
											<div class="input-group">
												<input type="text" name="tickinv.opening_number" id="startno"
													class="form-control"
													value="<s:property value="tickinv.opening_number" />">
												<s:if test="fieldErrors.get('startno').size() > 0">
													<span style="color: red;"><b><s:property
															value="fieldErrors.get('startno').get(0)" /></b></span>
												</s:if>
											</div>
										</div>
									</div>
									<script>
									if((document
											.getElementById("denomid").value==0)||(document
													.getElementById("denomid").value==-1))
												{
											document
													.getElementById("denomid").value="";
												}
									if((document
											.getElementById("startno").value==0)||(document
													.getElementById("startno").value==-1))
												{
										
											document
													.getElementById("startno").value="";
												}
															
															if((document
															.getElementById("endno").value==0)||(document
																	.getElementById("endno").value==-1))
																{
															document
																	.getElementById("endno").value="";
																}
												</script>
									<div class="form-group">
										<label class="col-md-3 control-label">End Number<font color="red">*</font></label>
										<div class="col-md-4">
											<div class="input-group">
												<input type="text" name="tickinv.closing_number" id="endno"
													class="form-control"
													value="<s:property value="tickinv.closing_number" />">
												<s:if test="fieldErrors.get('endno').size() > 0">
													<span style="color: red;"><b><s:property
															value="fieldErrors.get('endno').get(0)" /></b></span>
												</s:if>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Unit Type</label>
											<div class="col-md-4">
													<div class="input-group">
														<select class="form-control" name="tickinv.unittype" id="unittypeid" onclick="getOrgType()" onchange="getUnitNames()">
												          
										                 </select>
														 
													
															<%-- <s:if test="fieldErrors.get('parentid').size() > 0">
     								                                   <span style="color:red;"><s:property value="fieldErrors.get('parentid').get(0)" /></span>
									                        </s:if>	  --%>
													         <span class="input-group-addon">
															    <i class="fa fa-user"></i>
															 </span>
												 		
													</div>
											</div>
										</div>
										<div class="form-group">
										<label class="col-md-3 control-label">Unit Name</label>
											<div class="col-md-4">
													<div class="input-group">
														<select class="form-control" name="tickinv.unitname" id="unitnameid">
												          
										                 </select>
														 
													
															<%-- <s:if test="fieldErrors.get('parentid').size() > 0">
     								                                   <span style="color:red;"><s:property value="fieldErrors.get('parentid').get(0)" /></span>
									                        </s:if>	  --%>
													         <span class="input-group-addon">
															    <i class="fa fa-user"></i>
															 </span>
												 		
													</div>
											</div>
										</div>
									
																		<div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9">
											<button type="submit" class="btn blue">Save</button>
											<button type="button" id="cancel" class="btn default" onclick="goView()">Cancel</button>
											<h1 class="intro">
												<b><s:property value="msg" /></b>
											</h1>
										</div>
									</div>
								</div>
								<s:token />
							</form>
							<!-- END FORM-->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

