<%-- 
    Document   : js.jsp
    Created on : May 20, 2014, 20:33:01 PM
    Author     : manojv
    -----------------------------------------------------------------------------
    Revision History 1
    -----------------------------------------------------------------------------
    Updated By	: 	Deepak Kor
    Updated Date:	22nd May 2014
    Description	:	Added Javascript file mapOperations.js
    -----------------------------------------------------------------------------
        Revision History 2
    -----------------------------------------------------------------------------
    Updated By	: 	Aditi Pancholiya
    Updated Date:	15 Sep 2018
    Description	:	Added Key for Google Map 
    -----------------------------------------------------------------------------
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- to get attribute name from tiles -->
<jsp:useBean id="layoutId" type="java.lang.String" scope="request" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<%
String name=layoutId;
//System.out.println("name=>>>>"+name);
if(name.equals("login")){ %>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<%}%>

<%if(name.equals("index")){%>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<%} %>

<%if(name.equals("ShowBusesOnline")){%>
<script src="https://maps.googleapis.com/maps/api/js?client=gme-trimaxitinfrastructure&sensor=false&libraries=places,drawing&signature=CpSOeA5B6SzC2rxqlzd2JwenGX0="></script>
<%-- <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?sensor=false"></script> --%>
<script type="text/javascript" src="assets/vts/js/vts.js"></script>
<%} %>

<%if(name.equals("ShowAllBusesOnline")){%>
<script src="https://maps.googleapis.com/maps/api/js?client=gme-trimaxitinfrastructure&sensor=false&libraries=places,drawing&signature=CpSOeA5B6SzC2rxqlzd2JwenGX0="></script>
<%-- <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?sensor=false"></script> --%>
<script type="text/javascript" src="assets/vts/js/vtsAll.js"></script>
<%} %>

<%if(name.equals("FormFour") || name.equals("RateMasterAdd") || name.equals("CreateFareChart")|| name.equals("showVehilce")) {%>

<script type="text/javascript" src="assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="assets/global/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js"></script>
<script type="text/javascript" src="assets/global/plugins/clockface/js/clockface.js"></script>
<script type="text/javascript" src="assets/global/plugins/bootstrap-daterangepicker/moment.min.js"></script>
<script type="text/javascript" src="assets/global/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
<script type="text/javascript" src="assets/global/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
<script type="text/javascript" src="assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>



<script src="assets/admin/pages/scripts/components-pickers.js"></script>
<script src="assets/admin/pages/scripts/ui-alert-dialog-api.js"></script>

<%if(name.equals("FormFour")){%>
<script>
        jQuery(document).ready(function() {       
           // initiate layout and plugins
           Metronic.init(); // init metronic core components
Layout.init(); // init current layout
           ComponentsPickers.init();
           UIAlertDialogApi.init();
        });   
    </script>

<%} }%>



<%if(name.equals("createschedule")){%>
<script src="assets/admin/pages/scripts/form-samples.js"></script>

<script>
        jQuery(document).ready(function() {       
         
        	FormSamples.init();
        });   
    </script>
<%} %>

<%if(name.equals("createtrips")){%>

<script src="assets/admin/pages/scripts/trips.js"
	type="text/javascript"></script>
	<script>
	jQuery(document).ready(function() {
		tripFunctions.init();
	});
	</script>
	
<%} %>
<%if(name.equals("edittrip")){%>
<script src="assets/admin/pages/scripts/tripsedit.js"
	type="text/javascript"></script>
	<script>
	jQuery(document).ready(function() {
		tripEditFunctions.init();
	});
	</script>
	
<%} %>


