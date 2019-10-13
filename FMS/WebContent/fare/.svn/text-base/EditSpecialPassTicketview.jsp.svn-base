<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//www.google.com/jsapi"></script>

<script type="text/javascript">

function cancelform(){
// 	document.frm.action="ShowEmployeeListDetails.action";
// 	document.frm.submit();
	window.location.href = 'specialPassTicket.action';
}

</script>



</head>
<body>

<div class="page-content-wrapper">
	<div class="page-content">
		<div class="tab-content">
		<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						SPECIAL PASS TICKET <small></small>
					</h3>
					
				
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			<div class="tab-pane active" id="tab_0">
				<div class="portlet box grey-cascade">
				<div class="row">
				
			</div>
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i>Edit Special Pass Ticket
						</div>
						
					</div>
					
					<div class="portlet-body form">
					<s:if test="hasActionErrors()">
   <div class="alert alert-danger">
			<button class="close" data-close="alert"></button>
			<span>
			<s:actionerror/> </span>
		</div>
      
   
</s:if>
						<!-- BEGIN FORM-->
						
					<form action="editspecialPassTicketAction.action" class="form-horizontal" method="POST"  name ="frm" >
						
												<div class="form-group">
													<label class="col-md-3 control-label">Service Type :</label>
													<div class="col-md-2">
													<s:select list="servicetype" id="type" name="specialPassTicketModel.servicetype.service_type_id" cssClass="form-control input-medium select2me" ></s:select>
								</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">Special Ticket Type :</label>
													<div class="col-md-2">
													<input type="text"  class="form-control" name="specialPassTicketModel.ticket_type" id="day"  
													value='<s:property value="specialPassTicketModel.ticket_type" /> '>

												</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">Amount :</label>
													<div class="col-md-2">
													<input type="text" class="form-control" name="specialPassTicketModel.Amount" id="name"  
													value='<s:property value="specialPassTicketModel.Amount" /> '>

												</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">Notes :</label>
													<div class="col-md-4">
													
													<s:textarea cssClass="form-control" name="specialPassTicketModel.Notes" ></s:textarea>
													<script>
											var orgTypeId ="<s:property value='specialPassTicketModel.Notes'/>";
											if(orgTypeId!=""){
 												document.getElementById(orgTypeId).selected= true; 
												
											}
											
										</script>
												</div>
												</div>
											<div class="form-group">
									<input type='hidden' name="specialPassTicketModel.id"  value='<s:property value="specialPassTicketModel.id"/>'/>
								</div>	
										
										<div class="form-actions fluid">
											<div class="col-md-offset-3 col-md-9">
												<button type="submit" class="btn blue" >Save</button>
												<button class="btn default" type="button" onclick="cancelform()">Cancel</button>
														</div>
										</div>
										</div>	</div>
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