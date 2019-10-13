
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
<script src="https://code.jquery.com/jquery-1.11.3.min.js" type="text/javascript"></script>
<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places,drawing,geometry"></script>
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
								id="kmdetailtable">
								<thead >
											<tr>
												<th>Sr. NO</th>
												<th>Trip NO</th>
												<th>Duty Type</th>
												<th>From Stop</th>
												<th>To Stop</th>
												<th>Vehicle No</th>
												<th>Sch. Departure</th>
												<th>GPS Dep. Time</th>
												<th>Dep. Status</th>
												<th>Sch. Arrival Time</th>
												<th>GPS Arrival Time</th>
												<th>Arr. Status</th>
												<th>Sch. Trip Duration</th>
												
												<th>Gps. Trip Duration</th>
												<!-- <th>Sch. Distance</th> -->
												<th>Sch. Distance(In KM)</th>
												<th>GPS Distance(In KM)</th>
												<th>Trip Status</th>
												<th id='mapshow'>Show On Map</th>
												<th id='mapshow'>Playback</th>
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



