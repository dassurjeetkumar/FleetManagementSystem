<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


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
		document.forms[0].action = 'vehiclealertconfig.action';
		document.forms[0].submit();


}
function getAlertDesc()
{
	
     var len= document.getElementById('vts_id').options.length;
 
	
       $.ajax({
           type: "get",
           async:false,
           url: '<%=request.getContextPath()%>/findAlertDescAction',
           async:false,
           success: function(result) {
        	//   alert(result);
               document.getElementById('vts_id').innerHTML=result;
           }
       });
      <%--  var prevType="<s:property value='ticketbagmaster.orgtype.org_type_id'/>";
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
			           type: "get",
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
 --%>	}
function setPacketCodeAndMisBytes()
{ 
	var e = document.getElementById("vts_id");
	var  alertDescId= e.options[e.selectedIndex].value;
	//alert(strUser);
	//var len= document.getElementById('org_chart_id').options.length;
	//alert(len);
	
	       $.ajax({
	           type: "get",
	           url: '<%=request.getContextPath()%>/getPacketCodeAndMisBytes?alertDescid='+alertDescId,
	           success: function(result) {
	        	 //  alert(result);
	        	  var s=result.split("@");
	        	//  alert(s[0]+"----"+s[1]);
	              document.getElementById('packet_code').value=s[0];
	              document.getElementById('mis_bytes').value=s[1];
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

<body onload="getAlertDesc()">
	<div class="page-content-wrapper">
		<div class="page-content">
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Edit Vehicle Alert Configuration
							</div>
							<div class="tools">
								




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
							
							<form action="updateAlertConfigDetails.action" class="form-horizontal"
								method="post">
								<div class="form-body">
								<h1 class="intro">
												<s:property value="msg" />
											</h1>
                                   <%--  <div class="form-group">
										<label class="col-md-3 control-label">Alert Description</label>
											<div class="col-md-4">
													<div class="input-group">
													
			 											<select class="select2_category form-control" name="vehiclealertconfig.vtsalertmaster.vts_id" id="vts_id"  onchange="setPacketCodeAndMisBytes()" style="width:200px;" disabled>
												         <option value="<s:property value="vehiclealertconfig.vtsalertmaster.vts_id" />">
												           <s:property value="vehiclealertconfig.vtsalertmaster.ALERT_DESC" />
												           </option> 
										                 </select>
														 
														 <select class="select2_category form-control" name="vehiclealertconfig.vtsalertmaster.vts_id" id="vts_id"  onchange="setPacketCodeAndMisBytes()" style="width:200px;">
												         <option value="0" >select</option>
										                 </select>
													
															<s:if test="fieldErrors.get('vts_id').size() > 0">
     								                                   <span style="color:red;"><s:property value="fieldErrors.get('vts_id').get(0)" /></span>
									                        </s:if>	 
													        
															 <script>
															
															 </script>
												 		
													</div>
											</div>
										</div> --%>
										
										<div class="form-group">
										<label class="col-md-3 control-label">Alert Description</label>
											<div class="col-md-4">
													<div class="input-group">
													
			 											<select class="select2_category form-control" name="vehiclealertconfig.vtsalertmaster.vts_id" id="vts_id"  onchange="setPacketCodeAndMisBytes()" style="width:200px;">
												         <option value="0" >select</option>
										                 </select>
														 
													
															<s:if test="fieldErrors.get('vts_id').size() > 0">
     								                                   <span style="color:red;"><s:property value="fieldErrors.get('vts_id').get(0)" /></span>
									                        </s:if>	 
													        
															<%--  <script>
															
															 </script> --%>
												 		
													</div>
											</div>
										</div>
										
		 						
									<div class="form-group">
										<label class="col-md-3 control-label">Packet Code</label>
										<div class="col-md-4">
											<div class="input-group">
												<input type="text" name="vehiclealertconfig.PACKET_CODE" id="packet_code"
													class="form-control"
													value="<s:property value="vehiclealertconfig.PACKET_CODE" />" maxlength="11" readonly>
													 <input type="hidden" name="vehiclealertconfig.VTS_ALERT_CONFIG_ID" id="alertconfigid_id" value="<s:property value="vehiclealertconfig.VTS_ALERT_CONFIG_ID" />" maxlength="200">
												<s:if test="fieldErrors.get('Packet_code').size() > 0">
													<span style="color: red;"><s:property
															value="fieldErrors.get('Packet_code').get(0)" /></span>
												</s:if>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Misc Bytes</label>
										<div class="col-md-4">
											<div class="input-group">
												<input type="text" name="vehiclealertconfig.MISC_BYTES" id="mis_bytes"
													class="form-control"
													value="<s:property value="vehiclealertconfig.MISC_BYTES" />" maxlength="11" readonly>
												<s:if test="fieldErrors.get('mis_bytes').size() > 0">
													<span style="color: red;"><s:property
															value="fieldErrors.get('mis_bytes').get(0)" /></span>
												</s:if>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Time Duration</label>
										<div class="col-md-4">
											<div class="input-group">
												<input type="text" name="vehiclealertconfig.TIME_DURATION" id="time_duration"
													class="form-control"
													value="<s:property value="vehiclealertconfig.TIME_DURATION" />" maxlength="11">
												<s:if test="fieldErrors.get('time_duration').size() > 0">
													<span style="color: red;"><s:property
															value="fieldErrors.get('time_duration').get(0)" /></span>
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

