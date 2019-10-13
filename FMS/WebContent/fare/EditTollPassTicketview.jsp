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
	window.location.href = 'tollPassTicket.action';
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
						TOLL PASS TICKET <small></small>
					</h3>
					
				
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			<div class="tab-pane active" id="tab_0">
				<div class="portlet box grey-cascade">
				<div class="row">
				
			</div>
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i>Edit Toll Pass Ticket
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
						
					<form action="editTollPassTicketAction.action" class="form-horizontal" method="POST"  name ="frm" >
						
												<div class="form-group">
													<label class="col-md-3 control-label">Route No :</label>
													<div class="col-md-2">
													<s:select list="routetype" id="type" name="tollPassTicketModel.routeno.route_id" cssClass="form-control input-medium select2me" ></s:select>
								
												</div>
												
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">Toll Name :</label>
													<div class="col-md-2">
													<input type="text" class="form-control" name="tollPassTicketModel.toll_name" id="tollname"  
													value='<s:property value="tollPassTicketModel.toll_name" /> '>

												</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">Amount :</label>
													<div class="col-md-2">
													<input type="text" class="form-control" name="tollPassTicketModel.Amount" id="name"  
													value='<s:property value="tollPassTicketModel.Amount" /> '>

												</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">Notes :</label>
													<div class="col-md-4">
													
													<s:textarea cssClass="form-control" name="tollPassTicketModel.Notes" ></s:textarea>
													<script>
											var orgTypeId ="<s:property value='tollPassTicketModel.Notes'/>";
											if(orgTypeId!=""){
 												document.getElementById(orgTypeId).selected= true; 
												
											}
											
										</script>
												</div>
												</div>
												<div class="form-group">
									<input type='hidden' name="tollPassTicketModel.id"  value='<s:property value="tollPassTicketModel.id"/>'/>
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