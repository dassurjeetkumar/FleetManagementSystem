<%@page import="java.util.Collections"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%--  <%@ page import="com.trimax.its.report.dao.ReportDao,com.trimax.its.report.model.ReportDetail"%> --%>
    <%@ page import="java.util.List,com.trimax.its.model.User"%>
    <%@taglib uri="/struts-tags" prefix="s" %>
    <%-- <%@ taglib uri="/WEB-INF/tlds/birt.tld" prefix="birt"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Depot Rev</title>
<%--
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ITS Report</title>
 
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta content="" name="description"/>
<meta content="" name="author"/>
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link rel="stylesheet" type="text/css" href="assets/global/plugins/bootstrap-select/bootstrap-select.min.css"/>
<link rel="stylesheet" type="text/css" href="assets/global/plugins/select2/select2.css"/>

<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css"/>
<link href="assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<!-- <link href="assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/> -->
<link href="assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="assets/global/css/components.css" rel="stylesheet" type="text/css"/>
<link href="assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<!-- <link href="assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/> -->
<!-- <link id="style_color" href="assets/admin/layout/css/themes/default.css" rel="stylesheet" type="text/css"/> -->
<link href="assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="favicon.ico"/>
<link href="favicon.ico" rel="shortcut icon">
<script src="assets/global/plugins/jquery-1.11.0.min.js"
	type="text/javascript"></script>
 <script src="assets/admin/pages/scripts/birtreports.js" type="text/javascript"></script> --%>
<script src="https://code.jquery.com/jquery-1.11.3.min.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/externalApi/jsapi.js"></script>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#datepicker" ).datepicker();
  } );
  </script>
  <script>
	google.maps.event.addDomListener(window, 'load', initialize);
	/* $("#mnu_header li").removeClass("classic-menu-dropdown active");
	$("#mnu_vtu").addClass("classic-menu-dropdown active");
	$("#mnu_header li").addClass("classic-menu-dropdown"); */
	$("#birt_selected").addClass("selected");
	
</script>
<%-- <style type="text/css">
#Searchdiv  { float:left }
#ScrollCB  .closewindow { float:right }
#ScrollCB2  .closewindow { float:right }
#ScrollCB3  .closewindow { float:right }
#ScrollCB4  .closewindow { float:right }

.btn_blue:hover, .btn_blue:focus, .btn_blue:active, .btn_blue.active {
  color: white;
  background-color: #2e7af7;
}

.btn_blue {
    background-color: #4b8df8;
    color: white;
    background-image: none !important;
    border-width: 0;
    box-shadow: none;
    filter: none;
    font-size: 14px;
    outline: medium none !important;
    padding: 7px 14px;
    text-shadow: none;
}


.btn_default {
       
  color: #333333;
  background-color: #e5e5e5;
  border-color: "";
  background-image: none !important;
    border-width: 0;
    box-shadow: none;
    filter: none;
    font-size: 12px;
    margin-top:10px;
    margin-bottom:10px;
    outline: medium none !important;
/*     padding: 5px 10px; */
    text-shadow: none;
    height:24px;
   ;
  
   }
   
   
   
   .header {
       
  color: #333333;
  background-color:#89c4f4 ;
  border-color: "";
  background-image: none !important;
    border-width: 0;
    box-shadow: none;
    filter: none;
    font-size: 12px;
    margin-top:10px;
    margin-bottom:10px;
    outline: medium none !important;
/*     padding: 5px 10px; */
    text-shadow: none;
    height:24px;
   ;
  
   }
   #89c4f4 
.btn_default:hover, .btn_default:focus, .btn_default:active, .btn_default.active {
  color: #333333;
  background-color: #d6d6d6;
}
</style> --%>

</head>

<body>
<%-- <form name="f1" target="viewer"  id="f1" method="post"> 
<%
/*System.out.println("getLocalAddr() : "+request.getLocalAddr());
System.out.println("getRemoteAddr() : "+request.getRemoteAddr());
System.out.println("getServerName() : "+request.getServerName());
System.out.println("getRequestURL() : "+request.getRequestURL());
*/

%> --%>
<div   style="background-color: #428bca !important;height: 60px;">
	<div style="float:left;" class="page-content" >

	<!-- <input id="b1" type="button" style="margin-left:7px;"   class="btn_default" onclick="toggleF('ScrollCB')" value="Select Column"/>&nbsp;&nbsp;&nbsp;&nbsp;
	<input id="b2" type="button" class="btn_default"  onclick="toggleF('ScrollCB2')" value="Sort"/>&nbsp;&nbsp;&nbsp;&nbsp;
	<input id="b3" type="button" class="btn_default"  onclick="toggleF('ScrollCB3')" value="Search" />&nbsp;&nbsp;&nbsp;&nbsp;
	<input id="b4" type="button"   class="btn_default" value="Aggregation" />&nbsp;&nbsp;&nbsp;&nbsp; 
	 -->
	 
	<%--  <span style="font-size: 13px;color:white;margin-top:10px;
    margin-bottom:10px;">
	<a style="color:white; " href="birtschedule"> Schedule
								</a>
	</span> --%>
		<input type="button" class="btn blue"  value="Schedule" onclick="window.location.href='birtschedule'" />&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" class="btn blue"  value="Day Wise Revenue" onclick="window.location.href='birtdayrev'" />&nbsp;&nbsp;&nbsp;&nbsp;
			
			<input type="button" class="btn blue"  value="Division Revenue" onclick="window.location.href='birtdivrev'" />&nbsp;&nbsp;&nbsp;&nbsp;
	
	<input type="button" class="btn blue"  value="Depot Revenue" onclick="window.location.href='birtdepotwiserevenue'" />&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" class="btn blue"  value="Depot Wise Late Departure" onclick="window.location.href='birtlatedeparture'" />&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" class="btn blue"  value="Depot Wise Early Arrival" onclick="window.location.href='birtearlyarrival'" />&nbsp;&nbsp;&nbsp;&nbsp;
				<br>
					<input type="button" class="btn blue"  value="Target VS Revenue" onclick="window.location.href='birtrevenuetarget'" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" class="btn blue"  value="Monthly Revenue" onclick="window.location.href='birtdaywiserevmonths'" />&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" class="btn blue"  value="Comparision 2Months" onclick="window.location.href='birtcomptwomonths'" />&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" class="btn blue"  value="Yearly Rev Comparision" onclick="window.location.href='birtcompyearly'" />&nbsp;&nbsp;&nbsp;&nbsp;
	
	</div>
	
	
	
	
	<div style="float:right;margin-top:10px;
    margin-bottom:10px;">
	<%User user = (User) session.getAttribute("user");
	String username=user.getFirstname();%>
	<span style="font-size: 13px;color:white;margin-top:10px;
    margin-bottom:10px;">
	<a style="color:white; " href="homeMenu"> Home
								</a>
	</span>&nbsp;
	<span style="font-size: 13px;color:white; margin-top:10px;
    margin-bottom:10px;">
	<a   style="color:white;" href="homeMenu">Back</a>
	</span>
	&nbsp;
	<span style="font-size:13px;color:white;">
		Welcome<strong> <%=username %></strong>
	</span>
	</div>
	<br>
</div>

	<input id="sort" type="hidden" name="sort" value="" />
	<input id="filter" type="hidden" name="filter" value="" /><br>
	<input id="aggregate" type="hidden" name="agg" value="" /><br>

	
	<%-- <div id="ScrollCB"  class="page-content" style="height:200px;width:40%; overflow-y: scroll;border:1px solid gray;display:none; ">

		<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey">
						<div class="portlet-title ">
							<div class="caption ">
								<i class="fa fa-globe"></i><font color="black" style="font-size:80%;">Select The Columns To View Result:</font>
							</div>
<div class="tools">

<input type="button"  class="remove" id="close"  style="background-image: url('assets/admin/layout/img/CloseButton.png');background-color:transparent;border-style:none;background-repeat: no-repeat;width: 25px;"> 
</div>
						
	</div>
	<h4 style="color: font-size:14px;">Select The Columns To View Result:</h4><span class="closewindow .tools"><input type="button"  id="close" style="background-image: url('assets/global/img/portlet-remove-icon.png');background-color: #f3565d;margin-bottom: 2px;background-repeat: no-repeat;width: 25px; "> </span> 
	<div id="Searchdiv" >
	
	<%
		
			 	String url=(String)session.getAttribute("url");
				
				
				
				System.out.println("url="+url);
	%>
	
	
	<input id="getReport" class="btn_blue"  style="margin-left:7px; " type="button" value="Submit" onclick="report1('')"/><br>
</div>
</div>
	</div> 
	</div>
	</div> --%>
<%
		
			 	String url=(String)session.getAttribute("url");
				
				
				
				System.out.println("url="+url);
	%>

	<form action="birtdepotwiserevenue" method="post">
	
					<p>Date: <input type="text" id="datepicker" name="date" placeholder="pick here">
								
								<button type="submit" class="btn blue">Submit</button>
							</p>

				       <!--      </div> -->
	
</form>



<iframe id="viewer"  class="page-content" style="height:500px;width:100%;border:1px solid #C0C0B8;"  src="<%=url%>">

</iframe>



</html>


