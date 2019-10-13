
<%@ taglib prefix="s" uri="/struts-tags"%>
<script src="https://code.jquery.com/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="assets/vts/js/vts.js"></script>


<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />

						<div class="page-content-wrapper">
						<div class="page-content">				
					<div class="portlet-body">
						<form action="" method="post">
						<B><font color="green"><s:actionmessage /></font></B>
							<s:if test="hasActionErrors()">
								<div class="alert alert-danger">
								
									<button class="close" data-close="alert"></button>
								<span id="errorMsg" style="color:red;word-wrap: break-word;"><s:actionerror/></span>
								</div>
								</s:if>
								<b>
<%-- 								<span id="errorMsg" style="color:red;word-wrap: break-word;" ><s:actionerror/></span> --%>
							</b>
							<input type="hidden" id="" value="" name="vehicleTypeId">
							<div >
					<table class="table table-striped table-bordered table-hover"
								id="vehKMPL">
								<thead >
										<tr>
												<th align="center">Sr. NO</th>
												<th align="center">Vehicle No</th>
												<th>Sch KM</th>
												<th>Log KM</th>
												<th>VTU KM</th>
												<th>Fuel</th>
												<th>Log KMPL</th>
												<th>VTU KMPL</th>
												<th align="center">Date</th>
											
											</tr>
										</thead>
							
							</table>
							</div>
						</form>
					</div>

				</div>
			</div>
	</body>
<script>
$(document).ready(function() {
	alert("I am inside Report]");
});

</script>



