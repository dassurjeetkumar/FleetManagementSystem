<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.trimax.its.dao.UserDao"%>
<script>
    window.history.forward(1);
   </script>
<!-- BEGIN FOOTER -->

<div class="page-footer">
      <!-- BEGIN HEADER INNER -->
    <div class="page-header-inner">
        <!-- BEGIN LOGO -->
<!--         <div class="page-logo"> -->
<!--             <a href="#"> -->
<!--             <img src="assets/admin/layout/img/trimaxlogo.png" style="size: 10px" alt="logo" class="logo-default"/> -->
            </a>
            <div class="menu-toggler sidebar-toggler hide">
                
            </div>
<!--         </div> -->
        </div>

   <div class="page-footer-inner">
		 2018 &copy; Oryosoft.
	</div>
	<div class="page-footer-inner">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<%UserDao udao=new UserDao(); 
	  String version=udao.getAppVersion();
	%>
	  Version No: <%=version %> 
	</div>
	
	
	
	<div class="page-footer-tools">
		<span class="go-top"><b>Top</b>
<!-- 		<i class="fa fa-angle-up"></i> -->
		</span>
	</div>
</div>
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="assets/global/plugins/respond.min.js"></script>
<script src="assets/global/plugins/excanvas.min.js"></script> 
<![endif]-->
<jsp:useBean id="layoutId" type="java.lang.String" scope="request" />
<%
	String name = layoutId;
	//System.out.println("name of page=>>>>"+name);
	if (!name.equals("OperatedScheduledKMReport")) {
		//System.out.println("hiiiiiiiiiiiiiiiiiiiiname of page=>>>>");
%>
 <!-- <script src="assets/global/plugins/jquery-1.11.0.min.js" type="text/javascript"></script> -->
<%
	}
%>
<!-- <script src="assets/global/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip
<script src="assets/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
<script src="assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="assets/global/plugins/jquery-slimscroll/jquery.slimscroll.js" type="text/javascript"></script>
<script src="assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script> -->

<!-- END CORE PLUGINS
BEGIN PAGE LEVEL PLUGINS -->
<%
 /*<script src="assets/global/plugins/jqvmap/jqvmap/jquery.vmap.js" type="text/javascript"></script>
<script src="assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.russia.js" type="text/javascript"></script>
<script src="assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.world.js" type="text/javascript"></script>
<script src="assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.europe.js" type="text/javascript"></script>
<script src="assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.germany.js" type="text/javascript"></script>
<script src="assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.usa.js" type="text/javascript"></script>
<script src="assets/global/plugins/jqvmap/jqvmap/data/jquery.vmap.sampledata.js" type="text/javascript"></script>*/
%>

<script src="assets/global/plugins/flot/jquery.flot.min.js" type="text/javascript"></script>
<script src="assets/global/plugins/flot/jquery.flot.resize.min.js" type="text/javascript"></script>
<script src="assets/global/plugins/flot/jquery.flot.categories.min.js" type="text/javascript"></script>
<script src="assets/global/plugins/jquery.pulsate.min.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="assets/global/plugins/select2/select2.css"/>
<link rel="stylesheet" href="assets/global/plugins/data-tables/DT_bootstrap.css"/>
<!-- END PAGE LEVEL STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="assets/global/css/components.css" rel="stylesheet" type="text/css"/>
<link href="assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<link id="style_color" href="assets/admin/layout/css/themes/blue.css" rel="stylesheet" type="text/css"/>
<link href="assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- IMPORTANT! fullcalendar depends on jquery-ui-1.10.3.custom.min.js for drag & drop support -->

<script src="assets/global/plugins/fullcalendar/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>
<script src="assets/global/plugins/jquery-easypiechart/jquery.easypiechart.min.js" type="text/javascript"></script>
<script src='<%=request.getContextPath()%>/js/jquery.stickyheader.min.js' type="text/javascript"></script>
<script src='<%=request.getContextPath()%>/js/jquery.stickyheader.js' type="text/javascript"></script>
<%-- <script src='<%=request.getContextPath()%>/js/jquery.freezeheader.js' type="text/javascript"></script> --%>
 <%-- <script src='<%=request.getContextPath()%>/js/jquery-1.9.1.js' type="text/javascript"></script> --%>
<script src="assets/global/plugins/jquery.sparkline.min.js" type="text/javascript"></script>
<%-- <jsp:useBean id="layoutId" type="java.lang.String" scope="request" /> --%>
<%
	 name = layoutId;
	//System.out.println("name of page=>>>>"+name);
	if (!name.equals("RouteMap") && !name.equals("OperatedScheduledKMReport")) {
		//System.out.println("hiiiiiiiiiiiiiiiiiiiiname of page=>>>>");
%>

<script type="text/javascript" src="assets/global/plugins/data-tables/jquery.dataTables.min.js"></script>
<%
	}
	if (name.equals("createtrips")) {
%>
	<script type="text/javascript" src="assets/admin/pages/scripts/jquery.autocomplete.js"></script>
	<%
		}
	%>


<%
	//Menu related 
	if (name.equalsIgnoreCase("PageRole")) {
%>
<script src="assets/admin/pages/scripts/table-editable-page.js"></script>
<%
	}
%>
<%
	if (name.equalsIgnoreCase("grouprole")) {
%>
<script src="assets/admin/pages/scripts/table-editable-role.js"></script>
<%
	}
%>
<%
	if (name.equalsIgnoreCase("UserRolePage")) {
%>
<script src="assets/admin/pages/scripts/table-editable-userrolepage.js"></script>
<%
	}
%>
<!-- CHART JS -->
<%
	if (name.equalsIgnoreCase("ShowDashBoard")) {
%>
<script src="assets/global/plugins/flot/jquery.flot.pie.min.js"></script>
<script src="assets/admin/pages/scripts/charts.js"></script>
<%
	}
%>
<%

%>
<%
	if (name.equalsIgnoreCase("showDeviceDashboard")) {
%>
<script src="assets/global/plugins/flot/jquery.flot.pie.min.js"></script>
<script src="assets/etm/deviceChart.js"></script>
<!-- <script src="assets/etm/deviceNRDChart.js"></script> -->
<%
	}
%>
<!-- For SOS By Aditi  -->

<%
	if (name.equalsIgnoreCase("showSosDashboard")) {
%>
<script src="assets/global/plugins/flot/jquery.flot.pie.min.js"></script>
<script src="assets/etm/sosChart.js"></script>
<%
	}
%>
<!-- For Device Dashboard -->
<%
	if (name.equalsIgnoreCase("showEtmDashboard")) {
%>
<script src="assets/global/plugins/flot/jquery.flot.pie.min.js"></script>
<script src="/assets/etm/etmChart.js"></script>
<%
	}
%>


<!-- Chart Js Ends -->
<%
	if (name.equalsIgnoreCase("ShowDashBoard1")) {
%>
 <script src="assets/global/plugins/flot/jquery.flot.pie.min.js"></script>
<script src="assets/ccc/js/ccc-charts.js"></script> 
<!-- <script src="assets/global/plugins/flot/jquery.flot.pie.min.js"></script>
<script src="assets/admin/pages/scripts/charts.js"></script> -->
<%
	}
%>
<!-- Start For Vehicle Master -->   
<%
	if(name.equalsIgnoreCase("vehicleMaster")||name.equalsIgnoreCase("vehicleTypeMaster")||name.equalsIgnoreCase("serviceTypeMaster")||
		name.equalsIgnoreCase("makeTypeMaster")||name.equalsIgnoreCase("modelTypeMaster")||name.equalsIgnoreCase("brandTypeMaster")||
		name.equalsIgnoreCase("dockingTypeMaster")||name.equalsIgnoreCase("componentTypeMaster")||name.equalsIgnoreCase("subComponentTypeMaster")||
		name.equalsIgnoreCase("accidentTypeMaster")||name.equalsIgnoreCase("breakDownTypeMaster")||name.equalsIgnoreCase("vehicleCategoryMaster")||
		name.equalsIgnoreCase("dockingHistoryMaster")||name.equalsIgnoreCase("vehicleMaintenanceMaster")||name.equalsIgnoreCase("breakDownHistoryMaster")||
		name.equalsIgnoreCase("vehicleDefectMaster")||name.equalsIgnoreCase("complaintMaster")||name.equalsIgnoreCase("dockingVehicleMaster")||
		name.equalsIgnoreCase("extraKM")||name.equalsIgnoreCase("CauseCanclatn")||name.equalsIgnoreCase("serviceTaxMaster")||
		name.equalsIgnoreCase("scheduleMappingMaster")||name.equalsIgnoreCase("OnlineETMAvailability")||name.equalsIgnoreCase("BrandService")||name.equalsIgnoreCase("VehicleDeviceRelation")
		||name.equalsIgnoreCase("SOSVehiclesList")||name.equalsIgnoreCase("deviceDisconnect")){%>
	   
	   <script src="assets/admin/pages/scripts/table-managed-vehicle.js"></script>
<%
	}
%>
<!-- End For Vehicle Master-->  


<!-- <script type="text/javascript" src="assets/global/plugins/data-tables/DT_bootstrap.js"></script>
END PAGE LEVEL PLUGINS
BEGIN PAGE LEVEL SCRIPTS
<script src="assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="assets/admin/pages/scripts/index.js" type="text/javascript"></script>
<script src="assets/admin/pages/scripts/viewindex.js" type="text/javascript"></script>
<script src="assets/admin/pages/scripts/tasks.js" type="text/javascript"></script>
<script type="text/javascript" src="assets/global/plugins/select2/select2.min.js"></script>
<script src="assets/admin/pages/scripts/table-managed.js"></script>
<script src="assets/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>

<script src="assets/admin/pages/scripts/ui-alert-dialog-api.js"></script> 

<script type="text/javascript" src="assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="assets/global/plugins/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
<script type="text/javascript" src="assets/global/plugins/clockface/js/clockface.js"></script>
<script type="text/javascript" src="assets/global/plugins/bootstrap-daterangepicker/moment.min.js"></script>
<script type="text/javascript" src="assets/global/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
<script type="text/javascript" src="assets/global/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
<script type="text/javascript" src="assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="assets/admin/pages/scripts/components-pickers.js"></script>
<script src="assets/admin/pages/scripts/form-samples.js"></script> 
END CORE PLUGINS
BEGIN PAGE LEVEL PLUGINS
<script type="text/javascript" src="assets/global/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="assets/global/plugins/data-tables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/global/plugins/data-tables/DT_bootstrap.js"></script>
END PAGE LEVEL PLUGINS
BEGIN PAGE LEVEL SCRIPTS
<script src="assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="assets/admin/layout/scripts/layout.js" type="text/javascript"></script>


<script src="assets/admin/pages/scripts/table-managed-admin.js"></script>
<script src="assets/admin/pages/scripts/table-managed-breaktype.js"></script>
<script src="assets/admin/pages/scripts/table-managed-device.js"></script>
<script src="assets/admin/pages/scripts/table-managed-fare.js"></script>
<script src="assets/admin/pages/scripts/table-managed-org.js"></script>
<script src="assets/admin/pages/scripts/table-managed-reports.js"></script>
<script src="assets/admin/pages/scripts/table-managed-route.js"></script>
<script src="assets/admin/pages/scripts/table-managed-transport.js"></script>


<script src="assets/admin/pages/scripts/table-managed-pass.js"></script>

Revenue
<script src="assets/admin/pages/scripts/table-revenue_sector.js"></script>
End


<script src="assets/admin/pages/scripts/table-editable-bay.js"></script>
<script src="assets/admin/pages/scripts/table-editable-floor.js"></script>
<script src="assets/admin/pages/scripts/table-editable-platform.js"></script>	

<script src="assets/admin/pages/scripts/table-managed-ticketing.js" value='1' ></script>
<script src="assets/admin/pages/scripts/table-editable-ticket.js"></script>
<script src="assets/admin/pages/scripts/table-deletetable-ticket.js"></script>
<script src="assets/admin/pages/scripts/table-stockentry-ticket.js" value='1'></script>

<script src="assets/admin/pages/scripts/table-managed-account.js"></script>

<script src="assets/global/plugins/jquery.inputmask.bundle.min.js"></script>

<script src="assets/admin/pages/scripts/table-managed-toll.js"></script>

<script src="assets/admin/pages/scripts/table-managed-holiday.js"></script>
<script src="assets/admin/pages/scripts/table-managed-cashremittancevoucher.js"></script>
<script src="assets/admin/pages/scripts/table-managed-memonotice.js"></script>
<script src="assets/admin/pages/scripts/table-managed-training.js"></script>
<script src="assets/admin/pages/scripts/table-managed-passengerCategory.js"></script>


 <script src="assets/admin/pages/scripts/table-managed-weeklychart.js"></script>
 <script src="assets/admin/pages/scripts/table-InventoryTicketPassType.js"></script>
<script src="assets/admin/pages/scripts/table-managed-pis.js"></script>
<script type="text/javascript" src="assets/vts/js/table-manage-SarthiGeoFence.js"></script> -->


 <!-- BEGIN CORE PLUGINS -->
        <script src="assets/global/plugins/jquery.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
        <!-- END CORE PLUGINS -->
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <script src="assets/global/plugins/moment.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/bootstrap-daterangepicker/daterangepicker.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/morris/morris.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/morris/raphael-min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/counterup/jquery.waypoints.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/counterup/jquery.counterup.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/amcharts/amcharts/amcharts.js" type="text/javascript"></script>
        <script src="assets/global/plugins/amcharts/amcharts/serial.js" type="text/javascript"></script>
        <script src="assets/global/plugins/amcharts/amcharts/pie.js" type="text/javascript"></script>
        <script src="assets/global/plugins/amcharts/amcharts/radar.js" type="text/javascript"></script>
        <script src="assets/global/plugins/amcharts/amcharts/themes/light.js" type="text/javascript"></script>
        <script src="assets/global/plugins/amcharts/amcharts/themes/patterns.js" type="text/javascript"></script>
        <script src="assets/global/plugins/amcharts/amcharts/themes/chalk.js" type="text/javascript"></script>
        <script src="assets/global/plugins/amcharts/ammap/ammap.js" type="text/javascript"></script>
        <script src="assets/global/plugins/amcharts/ammap/maps/js/worldLow.js" type="text/javascript"></script>
        <script src="assets/global/plugins/amcharts/amstockcharts/amstock.js" type="text/javascript"></script>
        <script src="assets/global/plugins/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/horizontal-timeline/horizontal-timeline.js" type="text/javascript"></script>
        <script src="assets/global/plugins/flot/jquery.flot.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/flot/jquery.flot.resize.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/flot/jquery.flot.categories.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/jquery-easypiechart/jquery.easypiechart.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/jquery.sparkline.min.js" type="text/javascript"></script>
        
        <script src="assets/global/plugins/highcharts/js/highcharts.js" type="text/javascript"></script>
        <script src="assets/global/plugins/highcharts/js/highcharts-3d.js" type="text/javascript"></script>
        <script src="assets/global/plugins/highcharts/js/highcharts-more.js" type="text/javascript"></script>
        <script src="assets/global/plugins/echarts/echarts.js" type="text/javascript"></script>
                <!-- D PAGE LEVEL PLUGINS -->
        <!-- BEGIN THEME GLOBAL SCRIPTS -->
        <script src="assets/global/scripts/app.min.js" type="text/javascript"></script>
        <!-- END THEME GLOBAL SCRIPTS -->
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
        <script src="assets/pages/scripts/dashboard.js" type="text/javascript"></script>
        <script src="assets/pages/scripts/charts-highcharts.js" type="text/javascript"></script>
        <script src="assets/pages/scripts/charts-echarts.js" type="text/javascript"></script>
        <!-- END PAGELEVEL SCRIPTS -->
        <!-- BEGIN THEME LAYOUT SCRIPTS -->
        <script src="assets/layouts/layout6/scripts/layout.min.js" type="text/javascript"></script>
        <script src="assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
        <script src="assets/layouts/global/scripts/quick-nav.min.js" type="text/javascript"></script>
        <!-- END THEME LAYOUT SCRIPTS -->

<!-- END PAGE LEVEL SCRIPTS -->
 <script>
jQuery(document).ready(function() {    
   Metronic.init(); // init metronic core componets
   Layout.init(); // init layout
   Index.init();   
   Index.initDashboardDaterange();
   //Index.initJQVMAP(); // init index page's custom scripts
   Index.initCalendar(); // init index page's custom scripts
   Index.initCharts(); // init index page's custom scripts
   Index.initChat();
   Index.initMiniCharts();
   Index.initIntro();
   Tasks.initDashboardWidget();
   TableManaged.init();
   
   TableManagedTransport.init();
   TableManagedRoute.init();
   TableManagedReports.init();
   TableManagedOrg.init();
   TableManagedFare.init();
   TableManagedAdmin.init();
   TableManagedDevice.init();
   TableManagedBreakType.init();
   TableManagedPass.init();
   UIAlertDialogApi.init();
   ComponentsPickers.init(); 
   FormSamples.init();
  
   TableEditableFloor.init();
   TableEditableBay.init();
   TableEditablePlatform.init();

   <%if(name.equalsIgnoreCase("PageRole")){%> 
   TableEditablepage.init();
   <%}%>
   <%if(name.equalsIgnoreCase("grouprole")){%> 
   TableEditablerole.init();
   <%}%>
   <%if(name.equalsIgnoreCase("UserRolePage")){%>
   Tableuserroleepage.init();
   <%}%>

<!-- Start For Vehicle Master -->   
<%
	if(name.equalsIgnoreCase("vehicleMaster")||name.equalsIgnoreCase("vehicleTypeMaster")||name.equalsIgnoreCase("serviceTypeMaster")||
		name.equalsIgnoreCase("makeTypeMaster")||name.equalsIgnoreCase("modelTypeMaster")||name.equalsIgnoreCase("brandTypeMaster")||
		name.equalsIgnoreCase("dockingTypeMaster")||name.equalsIgnoreCase("componentTypeMaster")||name.equalsIgnoreCase("subComponentTypeMaster")||
		name.equalsIgnoreCase("accidentTypeMaster")||name.equalsIgnoreCase("breakDownTypeMaster")||name.equalsIgnoreCase("vehicleCategoryMaster")||
		name.equalsIgnoreCase("dockingHistoryMaster")||name.equalsIgnoreCase("vehicleMaintenanceMaster")||name.equalsIgnoreCase("breakDownHistoryMaster")||
		name.equalsIgnoreCase("vehicleDefectMaster")||name.equalsIgnoreCase("complaintMaster")||name.equalsIgnoreCase("dockingVehicleMaster")||
		name.equalsIgnoreCase("extraKM")||name.equalsIgnoreCase("CauseCanclatn")||name.equalsIgnoreCase("serviceTaxMaster")||
		name.equalsIgnoreCase("scheduleMappingMaster")||name.equalsIgnoreCase("OnlineETMAvailability")||name.equalsIgnoreCase("BrandService")||name.equalsIgnoreCase("VehicleDeviceRelation")
		 ||name.equalsIgnoreCase("SOSVehiclesList")||name.equalsIgnoreCase("deviceDisconnect")){%> 
		
		TableManagedVehicle.init();

<%
	}
%>
<!-- End For Vehicle Master-->   

   
   TableManagedTicket.init();
   TableEditableTicket.init();
   TableDeleteTicket.init();
   TableStockEntryTicket.init();
   TableAccountHead.init();
   TableTollfee.init();

   /*Charrt init*/
   <%
	if (name.equalsIgnoreCase("ShowDashBoard")) {
%>
   Charts.init();
   Charts.initCharts();
   Charts.initPieCharts();
   Charts.initBarCharts();
   <%}%>
   <%
	if (name.equalsIgnoreCase("showEtmDashboard")) {
%>
etmCharts.init();
etmCharts.initCharts();
etmCharts.initPieCharts();
etmCharts.initPieChartsvolvo();
etmCharts.initPieChartsord();
etmCharts.initBarCharts();
etmCharts.initPieChartsingenico();
etmCharts.initPieChartsverifone();
etmCharts.initPieChartsetmping();


  <%}%>
  <%
	if (name.equalsIgnoreCase("showDeviceDashboard")) {
%>
deviceChart.init();
deviceChart.initCharts();
deviceChart.initPieCharts();
deviceChart.initNRDPieCharts();
deviceChart.initNRDDetailsPieCharts();
deviceChart.initBarCharts();

<%}%>
  
  <%
	if (name.equalsIgnoreCase("showSosDashboard")) {
%>
sosChart.init();
sosChart.initCharts();
sosChart.initPieCharts();
sosChart.initBarCharts();
<%}%>
  
  
   <%
	if (name.equalsIgnoreCase("viewVehicleDepotInout")) {
%>
TableManagedVts.init();
  <%}%>
   TableManagedPis.init();
   TableManagedRevenueSector.init();
   
   TableManagedInventoryTicketPassType.init();
   TableManagedHoilday.init();
   TableManagedCashremittancevoucher.init();
   TableManagedMemoNotice.init();
   TableManagedTraining.init();
   TableManagedPassengerCategory.init();
   TableManagedSarthiSectorGeofence .init();
   <%
	if (name.equalsIgnoreCase("ShowDashBoard1")) {
%>
Charts_ccc.init();
Charts_ccc.initCharts();
Charts_ccc.initPieCharts();
Charts_ccc.initBarCharts();
Charts_ccc.initPieAccidentCharts();
Charts_ccc.initPieBreakdownCharts();
Charts_ccc.initPieEmployeeCharts();
Charts_ccc.initPieVTUCharts();
Charts_ccc.initPieETMCharts();
Charts_ccc.initPieTripCountCharts();
Charts_ccc.initPieScheduleTripCountCharts();
Charts_ccc.initPieCrewAllocationCountCharts();
Charts_ccc.initPieOnlineOfflineDepotCountCharts();
  <%}%>
   
  
  TableManagedWeeklyChart.init();
  
  
  
  });
</script> 

<%
	if (name.equals("edittrip")) {
%>
	<script type="text/javascript" src="assets/admin/pages/scripts/jquery.autocomplete.js"></script>
	<%
		}
	%>
<!-- END JAVASCRIPTS -->
