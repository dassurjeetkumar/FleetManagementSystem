<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>

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
function checkOption() {
	
}

checkOption();
function goView() {

		/* var val = $("input[type='checkbox']").val(); */
		//alert(val);
		document.forms[0].action = 'ticketbag.action';
		document.forms[0].submit();


}
function getOrgType()
{
 var len= document.getElementById('org_type_id').options.length;
 
	 if(len<=1 ) {
       $.ajax({
           type: "post",
           async:false,
           url: '<%=request.getContextPath()%>/findAllOrgTypeAction',
           async:false,
           success: function(result) {
        	//   alert(result);
               document.getElementById('org_type_id').innerHTML=result;
           }
       });
       var prevType="<s:property value='ticketbagmaster.orgtype.org_type_id'/>";
		//alert(prevType);
		 if(prevType!=""){
			 //document.getElementById("orgType"+prevType).selected=true;
			 document.getElementById("org_type_id").value=prevType;
			 var orgtypeid = document.getElementById("org_type_id").value;
			 var orgtype = org_type_id.options[org_type_id.selectedIndex].text;
			 document.getElementById('select2-chosen-1').innerHTML=orgtype;
		 }
		 
	 }
	
	     //var prevType1="<s:property value='ticketbagmaster.orgchart.org_chart_id'/>";
	    // alert(prevType1);
		 if(document.getElementById("orgType"+prevType).selected==true)
			{
				 $.ajax({
			           type: "post",
			           url: '<%=request.getContextPath()%>/findUnitNameAction?orgid='+prevType,
			           async:false,
			           success: function(result) {
			        	  // alert(result);
			               document.getElementById('org_chart_id').innerHTML=result;
			           }
			       });
			} 

		 var prevType1="<s:property value='ticketbagmaster.orgchart.org_chart_id'/>";
		// alert(prevType1);
		 if(prevType1!=""){
			 //document.getElementById("orgName"+prevType1).selected=true;
			 document.getElementById("org_chart_id").value=prevType1;
			 var orgchartid = document.getElementById("org_chart_id").value;
			 var orgname = org_chart_id.options[org_chart_id.selectedIndex].text;
			 document.getElementById('select2-chosen-2').innerHTML=orgname;
			 
		 }   
	}
function getUnitNames()
{
	var strUser;
	var orgtype="<s:property value='orgtTypeId'/>";
	 var orgchart="<s:property value='orgchartid'/>";
	 if(orgtype==2){
		 strUser = orgchart;
	 }else{
	var e = document.getElementById("org_type_id");
	var strUser = e.options[e.selectedIndex].value;
	 }
	//alert(strUser);
	var len= document.getElementById('org_chart_id').options.length;
	//alert(len);
	
	       $.ajax({
	           type: "post",
	           url: '<%=request.getContextPath()%>/findUnitNameAction?orgid='+strUser,
	           success: function(result) {
	               document.getElementById('org_chart_id').innerHTML=result;
	           }
	       });
	    /*    var prevType1="<s:property value='ticketbagmaster.orgchart.org_chart_id'/>";
			//alert(prevType);
			 if(prevType1!=""){
				 document.getElementById("orgName"+prevType1).selected=true;
			 }    */
}

</script>
<script type="text/javascript" src="//www.google.com/jsapi">

</script>
</head> 

<body onload="getOrgType()">
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ticketbag.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
%>

	<div class="page-content-wrapper">
		<div class="page-content">
		<%if (access.equalsIgnoreCase("Y")){%>
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
					<%if (create.equalsIgnoreCase("Y")){%>
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Create Ticket Bag
							</div>
							<div class="tools">
								




							</div>
						</div>
						<div class="portlet-body form">
                         <s:if test="hasActionErrors()">
                                       <div class="alert alert-danger">
			                                <button class="close" data-close="alert"></button>
		                                     <span>
			                                 <b><s:actionerror/></b> </span>
		                              </div>
      
                                    </s:if>
		
							<!-- BEGIN FORM--> 
							
							<form action="saveTicketBag.action" class="form-horizontal"
								method="post">
								<div class="form-body">
								<h1 class="intro">
												<b><s:property value="msg" /></b>
											</h1>
                                   <div class="form-group" id="ochart">
										<label class="col-md-3 control-label">Organisation Type<font color="red">*</font></label>
											<div class="col-md-4">
													<div class="input-group">
													
														<select class="select2_category form-control" name="ticketbagmaster.orgtype.org_type_id" id="org_type_id"  onchange="getUnitNames()" style="width:200px;">
												         <option value="0" >select</option>
										                 </select>
														 
													
															<s:if test="fieldErrors.get('orgtypeid').size() > 0">
     								                                   <span style="color:red;"><b><s:property value="fieldErrors.get('orgtypeid').get(0)" /></b></span>
									                        </s:if>	 
													        
															<%--  <script>
															
															 </script> --%>
												 		
													</div>
											</div>
										</div>
										<div class="form-group" id="otype">
										<label class="col-md-3 control-label">Organisation Name<font color="red">*</font></label>
											<div class="col-md-4">
													<div class="input-group">
														<select class="select2_category form-control" name="ticketbagmaster.orgchart.org_chart_id" id="org_chart_id" style="width:200px;">
												             <option value="0">Select</option>
										                 </select>
														 
													
															<s:if test="fieldErrors.get('orgName').size() > 0">
     								                                   <span style="color:red;"><b><s:property value="fieldErrors.get('orgName').get(0)" /></b></span>
									                        </s:if>	 
													         
												 		
													</div>
													<script>
													
													</script>
											</div>
										</div>
									<%-- <div class="form-group">
										<label class="col-md-3 control-label">Organization Name<font color="red">*</font></label>
										<div class="col-md-4">
											<div class="input-group">
												<input type="text" name="ticketbagmaster.orgname"
													id="orgName" class="form-control"
													value="<s:property value="ticketbagmaster.orgname" />" maxlength="50">
												<s:if test="fieldErrors.get('orgName').size() > 0">
													<span style="color: red;"><s:property
															value="fieldErrors.get('orgName').get(0)" /></span>
												</s:if>
											</div>
										</div>
									</div> --%>
									
									<script>
									 var orgtype="<s:property value='orgtTypeId'/>";
									 var orgchart="<s:property value='orgchartid'/>";
									
								   if(orgtype!==0 && orgtype==1)
								    {
									   $("#ochart").show();
								  	   $("#otype").show(); 
									 }
								    else if(orgtype!==0 && orgtype==2) {
								    	 $("#ochart").hide();
								    	 $("#otype").show();
								    	 getUnitNames();
								    }else if(orgtype!==0 && orgtype==3) {
								    	$("#ochart").hide();
								   	    $("#otype").hide(); 
								    }
									</script>
									<div class="form-group">
										<label class="col-md-3 control-label">Bag Code<font color="red">*</font></label>
										<div class="col-md-4">
											<div class="input-group">
												<input type="text" name="ticketbagmaster.bagcode" id="TicketBag_code"
													class="form-control"
													value="<s:property value="ticketbagmaster.bagcode" />" maxlength="11">
												<s:if test="fieldErrors.get('TicketBagCode').size() > 0">
													<span style="color: red;"><b><s:property
															value="fieldErrors.get('TicketBagCode').get(0)" /></b></span>
												</s:if>
											</div>
										</div>
									</div>
									<div class="form-group">
								<label class="col-md-3 control-label">Effective Start
									Date<sup><font color="red">*</font></sup>
								</label>
								<div class="col-md-3">
									<div class="input-group input-medium date date-picker"
										data-date-format="dd-mm-yyyy" data-date-start-date="+0d" >
										<input type="text" class="form-control" readonly 
											name="ticketbagmaster.effectivestartdate" id="startdate"> <span
											class="input-group-btn">
											<button class="btn default" type="button">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
										<script>
										var curr_date=new Date().toJSON().slice(0,10);
                                        curr_date=curr_date.split("-");
                                        curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
                                        $('#startdate').val(curr_date);
										</script>
										
									</div>

								</div>
							</div>

							<div class="form-group">
								<label class="col-md-3 control-label">Effective End Date
								</label>
								<div class="col-md-3">
									<div class="input-group input-medium date date-picker"
										data-date-format="dd-mm-yyyy" data-date-start-date="+0d" >
										<input type="text" class="form-control" readonly 
											name="ticketbagmaster.effectiveenddate" id="enddate"> <span
											class="input-group-btn">
											<s:if test="fieldErrors.get('enddate').size() > 0">
													<span style="color: red;"><b><s:property
															value="fieldErrors.get('enddate').get(0)" /></b></span>
												</s:if>
											<button class="btn default" type="button">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
										<script>
										var curr_date=new Date().toJSON().slice(0,10);
                                        curr_date=curr_date.split("-");
                                        curr_date=curr_date[2]+"-"+curr_date[1]+"-"+curr_date[0];
                                       // $('#enddate').val(curr_date);
										</script>
											
									</div>

								</div>
							</div>

									<div class="form-group">
										<label class="col-md-3 control-label">status</label>
										<div class="col-md-4">
											
												<select class="form-control" name="ticketbagmaster.status" id="status" disabled style="width:200px;">
												<option id="active" selected="selected" value="ACTIVE" >ACTIVE</option>
													
													
													

												</select>
											<%-- 	<script>
												
															var status = "<s:property value="ticketbagmaster.status"/>";
															/* if (status != undefined) {
																if (status == "ACTIVE"
																		|| status == "ACTIVE") {
																	document
																			.getElementById("active").selected = true;
																} else {
																	document
																			.getElementById("deactive").selected = true;
																}
															} */
															if((document
															.getElementById("ticket").value==0)||(document
																	.getElementById("status").value==-1))
																{
															document
																	.getElementById("status").value="";
																}
												</script>
											 --%>		
										</div>
									</div>
										<div class="form-group">
										<label class="col-md-3 control-label">Remarks</label>
										<div class="col-md-4">
											<div class="input-group">
											<textarea name="ticketbagmaster.notes"
													id="notes" class="form-control"
													value="<s:property value="ticketbagmaster.notes" />" maxlength="500"></textarea>
												<%-- <input type="text" name="ticketbagmaster.notes" id="notes"
													class="form-control"
													value="<s:property value="ticketbagmaster.notes" />" maxlength="11"> --%>
												<s:if test="fieldErrors.get('notes').size() > 0">
													<span style="color: red;"><b><s:property
															value="fieldErrors.get('notes').get(0)" /></b></span>
												</s:if>
											</div>
										</div>
									</div>
									<div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9">
											<button type="submit" class="btn blue">Save</button>
											<button type="button" id="cancel" class="btn default" onclick="goView()">Cancel</button>
											
										</div>
									</div>
								</div>
								<s:token />
							</form>
							<!-- END FORM-->
							<%}else{ %>
<%@ include file="/pages/admin/error.jsp" %>
<%} %>									
										
<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

