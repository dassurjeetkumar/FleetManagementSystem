 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights"%>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao"%>

<!DOCTYPE html>
<html>
<head>
<script src="assets/pis/js/scrollmessage.js" type="text/javascript"></script>
<script>
	
</script>
<style>
h1.intro {
	color: red;
	font-size: 14px;
}
</style>
<script src="assets/vts/js/vehiclealert.js"></script>
<script>
	function goView() {

		/* var val = $("input[type='checkbox']").val(); */
		//alert(val);
		document.forms[0].action = 'viewAdMannagement.action';
		document.forms[0].submit();

	}
    function getAdvertisementList(value)
    {
    	
    	var customerId = document.getElementById('customerlist').value;
    	
    	if(customerId==-1)
    	{
    		  document.getElementById('advertisement_id').innerHTML="<option value=0>Select</option><option value=-1>ALL</option>";

    	}
    	else
    	{
    		 $.ajax({
    	           type: "post",
    	           async:false,
    	           url: '<%=request.getContextPath()%>/findAllAdvertisement?custId='+customerId,
    	           async:false,
    	           success: function(result) {
    	        	  
    	               document.getElementById('advertisement_id').innerHTML=result;
    	           }
    	       });
    	}
    	
    }
	function getAdvertisementName(value) {
		var customerId = document.getElementById('customerlist').value;
		
		var advertiseId=document.getElementById('advertisement_id').value;
		
		var divId = document.getElementById("advid");
		divId.style.display = 'block';

		$('#viewAd1').dataTable().fnClearTable();
		$('#viewAd1').dataTable().fnDestroy();

		$('#viewAd1').dataTable({
			"aaSorting" : [ [ 1, 'asc' ] ],
			"aLengthMenu" : [ [ 10, 25, 50, 100 ], [ 10, 25, 50, 100 ], // change per page values here
			],
			// set the initial value
			"iDisplayLength" : 10,
			"bProcessing" : true,
			"bServerSide" : true,
			"sAjaxSource" : "showAdvertisementCustList.action?custId=" + customerId+"&adId="+advertiseId,
			"sPaginationType" : "bootstrap",
			"oLanguage" : {
				"sLengthMenu" : "_MENU_ records",
				"oPaginate" : {
					"sPrevious" : "Prev",
					"sNext" : "Next"
				}
			},
			"aoColumnDefs" : [ {
				'bSortable' : false,
				'aTargets' : [ 0 ]
			}, {
				"bSearchable" : false,
				"aTargets" : [ 0 ]
			} ]

		});
		jQuery('#viewAd1_wrapper .dataTables_filter input').addClass(
				"form-control input-small input-inline"); // modify table search input
		jQuery('#viewAd1_wrapper .dataTables_length select').addClass(
				"form-control input-xsmall input-inline"); // modify table per page dropdown
		getAvertisementForCUst(customerId);

	}
</script>
<script type="text/javascript" src="//www.google.com/jsapi">
	
</script>
</head>

<body>
	<%
		AccessRightsDao accessrightdao = new AccessRightsDao();
		AccessRights accessrights = new AccessRights();
		int usrsessionid = (Integer) request.getSession().getAttribute(
				"userid");
		accessrights = accessrightdao.accessRightsdetails(usrsessionid,
				"ticketbag.action");
		String access = accessrights.getACCESS();
		String create = accessrights.getCREATE();
		String edit = accessrights.getEDIT();
		String delete = accessrights.getDELETE();
		String read = accessrights.getREAD();
		String error = accessrights.getERROR();
		String viewdelete = accessrights.getVIEWDELETE();
		String viewdeletedrecord = (String) request.getSession()
				.getAttribute("viewdeletedrecord");
	%>

	<div class="page-content-wrapper">
		<div class="page-content">
			<%
				if (access.equalsIgnoreCase("Y")) {
			%>
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<%
							if (create.equalsIgnoreCase("Y")) {
						%>
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Get All Ads
							</div>
							<div class="tools"></div>
						</div>
						<div class="portlet-body form">
							<s:if test="hasActionErrors()">
								<div class="alert alert-danger">
									<button class="close" data-close="alert"></button>
									<span> <s:actionerror />
									</span>
								</div>

							</s:if>

							<!-- BEGIN FORM-->

							<form action="saveAd.action" class="form-horizontal"
								method="post" enctype="multipart/form-data">
								<div class="form-body">
									<h1 class="intro">
										<s:property value="msg" />
									</h1>


									<div class="form-group">
										<label class="col-md-3 control-label">Customer Name<font
											color="red">*</font></label>
										<div class="col-md-4">
											<div class="input-group">
												<s:select list="customerlist" id="customerlist"
													name="advetisement.cust.id"
													cssClass="select2_category form-control" headerKey="0"
													headerValue="Select"
													onchange="getAdvertisementList(this.value)"
													style="width:200px;"></s:select>
												<%-- <select class="select2_category form-control"
													name="advetisement.cust.id" id="cust_id"
													style="width: 200px;">
													<option value="0"></option>
												</select> --%>


												<s:if test="fieldErrors.get('customerlist').size() > 0">
													<span style="color: red;"><s:property
															value="fieldErrors.get('customerlist').get(0)" /></span>
												</s:if>

												<%--  <script>
															
															 </script> --%>

											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Advertisement Name<font
											color="red">*</font></label>
										<div class="col-md-4">
											<div class="input-group">
												<select class="select2_category form-control"
													name="advetisement.advertisement_id" id="advertisement_id"
													onchange="getAdvertisementName(this.value)" style="width: 200px;" >
													
													<option value="0">Select</option> 
												</select>
											
												<%-- <s:select list="advertisementlist" id="advertiselist"
													name="advetisement.advertisement_id"
													cssClass="select2_category form-control" headerKey="0"
													headerValue="AdvertisementName"
													onchange="getAdvertisementName(this.value)"
													style="width:200px;"></s:select> --%>
												


												<s:if test="fieldErrors.get('advertiselist').size() > 0">
													<span style="color: red;"><s:property
															value="fieldErrors.get('advertiselist').get(0)" /></span>
												</s:if>

												<%--  <script>
															
															 </script> --%>

											</div>
										</div>
									</div>
									<div id="advid" style="display: none;">
										<div class="portlet-body">
											<input type="hidden" name="requestType" id="requestType"
												value="text" />
											<table class="table table-striped table-bordered table-hover"
												id="viewAd1">
												<font color="green" size="2px"><s:property
														value="msg" /></font>
												<thead>
													<tr>
														<th></th>
														<th>Sr No.</th>
														<th>Advertisement Name</th>
													    <th>Customer Name</th>
														<th>Start Date</th>
														<th>End Date</th>

														<th>Amount</th>
														<th>Duration</th>





													</tr>
												</thead>

											</table>

										</div>
										
									</div>
									<div class="form-actions fluid">
											<div class="col-md-offset-3 col-md-9" >
												 <button type="button" class="btn blue"
													onclick="getAdvertisementName()">Submit</button> 
												<button type="button" id="cancel" class="btn default"
													onclick="goView()">Cancel</button>

											</div>
										</div>
									<s:token />
							</form>
							<!-- END FORM-->
							<%
								} else {
							%>
							<%@ include file="/pages/admin/error.jsp"%>
							<%
								}
							%>

							<%
								} else {
							%>



							<%@ include file="/pages/admin/error.jsp"%>


							<%
								}
							%>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

