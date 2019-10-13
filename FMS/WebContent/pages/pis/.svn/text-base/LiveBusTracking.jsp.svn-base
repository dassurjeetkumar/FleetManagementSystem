<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<script src="assets/pis/js/bus_tracking.js"></script>
<script type="text/javascript"></script>
<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places,drawing,geometry"></script>

</head>

<script>
	var geocoder;
	var map;
	var places;
	var counter =0; 
	function initializeMap123() {
		// create the geocoder
		geocoder = new google.maps.Geocoder();
		var tmpLatLng = new google.maps.LatLng(12.9746, 77.5946);
		<s:iterator value="list" id="list">
		tmpLatLng = new google.maps.LatLng(<s:property value="LAT" />,
				<s:property value="LONGITUDE" />);
		// make and place map maker.
		var indMarker = new google.maps.Marker({
			map : map,
			position : tmpLatLng,
			icon : 'assets/images/Bus-Icon.png',
			title : '#' + "<s:property value="DEVICE_ID" />",
		});
		contentString = "<s:property value="DEVICE_ID" />";
		google.maps.event.addListener(indMarker, 'click', function(
				contentString) {
			return function() {
				infowindow.setContent(contentString);//set the content
				infowindow.open(map, null);
			};
		});
		markers.push(indMarker);
		</s:iterator>
	}
	function getDepot(orgId){
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
		 var val=document.getElementById('divisionlist').value;
			 if(val!=0) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/getDepot?val=' + val,
				success : function(result) {
					document.getElementById('depotlist').innerHTML = result;
				}
			});
		}

	}
	function getSchedule()
	{
		console.log('here');
		 var val=document.getElementById('depotlist').value;
		 var selectedDate=document.getElementById("selecteddate").value;
		 if(val!=0) {
			 if(counter == 0){
				 counter ++;
        $.ajax({
            type: "post",
            url: '<%=request.getContextPath()%>/getSchedule?val=' + val+'&selectedDate='+selectedDate,
			success : function(result) {
				counter =0;
				document.getElementById('schedulelist').innerHTML = result;
			}
		});
			 }
	}
	}
	function getVehicleData(scheduleNo) {
		document.getElementById("scheduleTable").style.display = '';
		var selectedDate=document.getElementById("selecteddate").value;
		initializeMap();
		$('#scheduleTable')
				.dataTable(
						{
							"aLengthMenu" : [ [ 5, 15, 20, -1 ],
									[ 5, 15, 20, "All" ] // change
							],
							// set the initial value
							"iDisplayLength" : 20,
							"sAjaxSource" : "scheduleDetails.action?scheduleNo="+ scheduleNo+"&selectedDate="+selectedDate,
							"sPaginationType" : "bootstrap",
							"bDestroy" : true,
							"oLanguage" : {
								"sLengthMenu" : "_MENU_ records",
								"oPaginate" : {
									"sPrevious" : "Prev",
									"sNext" : "Next"
								}
							},
							"bFilter" : false,
							"bSelect" : false,
							"bPaginate" : false,
							"oLanguage" : {
								"sZeroRecords" : "",
								"sEmptyTable" : ""
							},
							"aoColumnDefs" : [ {
								'bSortable' : false,
								'aTargets' : [ 0, 1, 2, 3, 4 ]
							} ],
							aaSorting:[],
							"bLengthChange" : false,
							"sDom" : '<"top">rt<"bottom"flp><"clear">'
						})
				.wrap(
						"<div style='position:auto;overflow:auto;height:300px;width:100%'/>");
		jQuery('#scheduleTable_wrapper .dataTables_filter input').addClass(
				"form-control input-medium input-inline"); // modify table
		// search input
		jQuery('#scheduleTable_wrapper .dataTables_length select').addClass(
				"form-control input-xsmall input-inline");

	}
</script>

<div class="page-content-wrapper">
	<div class="page-content">
		<div class="row">
			<div class="col-md-6">
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
				<h3 class="page-title">
					PIS LIVE BUS TRACKING
				</h3>
				<!-- END PAGE TITLE & BREADCRUMB-->
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="portlet box white">
					<div class="portlet-body form">
						<div class="form-group">
						
							<div class="col-md-2">
									<s:select list="divisionlist" id="divisionlist"
									name="orgchart.org_chart_id"
									cssClass="select2_category form-control" headerKey="0"
									headerValue="Division" onchange="getDepot(this.value)"></s:select>
							</div>
							
							<div class="col-md-2">
								<select id="depotlist" class="select2_category form-control"
									>
									<option value="0">Depot</option>
								</select>
								Refresh: <input type="checkbox" checked="checked" id="refreshID"
									onclick="getRefresh()" />
							</div>	
							<div class="col-md-2">
								<div class="input-group input-small date date-picker"
									style="width: auto" data-date-format="dd-mm-yyyy"
									data-date-viewmode="years">
									<input type="text" class="form-control" id="selecteddate" onchange="getSchedule(this.value)"
										readonly name="selecteddate" /> <span class="input-group-btn">
										<button class="btn default" type="button" >
											<i class="fa fa-calendar"></i>
											
										</button>
									</span>
								</div>
							</div>						
							<div class="col-md-2">
								<select id="schedulelist" class="select2_category form-control"
									onchange="getVehicleData(this.value)">
									<option value="0">Schedule</option>
								</select>														
								Markers: <input type="checkbox" checked="checked" id="markers"
						onclick="" />
							</div>
						</div>
						<!-- End of form Group -->
						 <div class="form-group">
							<div class="portlet-body">
								<div class="col-md-12">
									
								</div>								
							</div>
						</div> 
						<%-- <script>
							var curr_date = new Date().toJSON().slice(0, 10);
							curr_date = curr_date.split("-");
							curr_date = curr_date[0] + "-" + curr_date[1] + "-"
									+ curr_date[2];
							$('#selecteddate').val(curr_date);
						</script> --%>
						</div>
						<div class="col-md-4">
						<div class="form-group">
							<div class="portlet-body">
								<table class="table table-striped table-bordered table-hover"
									id="scheduleTable" style="display: none">
									<thead>
										<tr>
											<th>#</th>
											<th>Trip Status</th>
											<th>Start Stop</th>
											<th>End Stop</th>
											<th>Sch. Time</th>
											<th>Dept. time</th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
						<div class="form-group">
							<div class="portlet-body" id="bus_stops1" style="overflow:scroll; height:200px;">
								 <table class="table table-striped table-bordered table-hover"
									id="bus_stops" style="display: block">
									<thead>
										<tr>
											<th>Bus Stop Name</th><th>ETA</th><th>ETA(GOOGLE)</th>
										</tr>
										<tr>
											<td id="b"></td><td id="a"></td><td id="c"></td>
										</tr>
									</thead>
								</table> 
							</div>
						</div>
					</div>
					
					<div class="col-md-8">
				<div class="form-group" id="mapdiv">
					<table class="table table-striped">
						<tr>
							<td align="center"><h4 id="route_name_display"></h4></td>
						</tr>
						<tr>
							<td align="center" id="msg_schedule">&nbsp;</td>
						</tr>
						<tr>
							<td valign="top">
								<div class="portlet-body" style="height: 500px">
									<div id="route_map" class="gmaps" style="height: 100%"></div>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
				</div>
			</div>
			
		</div>
		
	</div>
</div>

<script>
	google.maps.event.addDomListener(window, 'load', initializeMap123);	
</script>