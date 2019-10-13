<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.trimax.its.model.User"%>

<link rel="stylesheet"
    href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />

  <!-- BEGIN HEADER -->
<div class="page-header navbar navbar-fixed-top">
    <!-- BEGIN HEADER INNER -->
    <div class="page-header-inner">
        <!-- BEGIN LOGO -->
        <div class="page-logo">
        
       <img src="assets/admin/layout/img/header-logo.png" alt="logo" class="logo-default"/>
      
            
            <div class="menu-toggler sidebar-toggler hide">
                <!-- DOC: Remove the above "hide" to enable the sidebar toggler button on header -->
            </div>
        </div>
        <!-- END LOGO -->
        
        <!-- BEGIN HORIZANTAL MENU -->
        <!-- DOC: Remove "hor-menu-light" class to have a horizontal menu with theme background instead of white background -->
        <!-- DOC: This is desktop version of the horizontal menu. The mobile version is defined(duplicated) in the responsive menu below along with sidebar menu. So the horizontal menu has 2 seperate versions -->
        <div class="hor-menu hor-menu-light hidden-sm hidden-xs" id="mnu_header">
            <ul class="nav navbar-nav">
                <!-- DOC: Remove data-hover="dropdown" and data-close-others="true" attributes below to disable the horizontal opening on mouse hover -->
                <li class="classic-menu-dropdown active">
                    <a href="DashboardAdmin.action">
                    Dashboard <span class="selected">
                    </span>
                    </a>
                </li>
               <!-- <li class="classic-menu-dropdown" id="mnu_etm">
                    <a href="etmDashboard.action">
                    ETM <span id="etm_selected">
                    </span>
                    </a>
                </li> -->
                
               <!--  <li class="classic-menu-dropdown" id="mnu_vtu">
                    <a href="dashboard.action">
                    VTU<span id="vtu_selected">
                    </span>
                    </a>
                </li> -->
               <%--  <li class="classic-menu-dropdown" id="mnu_ccc">
                    <a href="#">
                    CCC<span id="ccc_selected">
                    </span>
                    </a>
                </li>
                
                
            <li class="classic-menu-dropdown">
                   <a href="pisliveBusTracking.action"> 
                   PIS 
              </span 
                   </a>            
                 </li> 
                 
    
                <li class="classic-menu-dropdown" id="mnu_dashboard">
                    <a href="ShowDashBoardReport.action">
                    DashBoard<span id="dashboard_selected">
                    </span>
                    </a>
                </li> --%>
                
                 <!--   <li class="classic-menu-dropdown" id="mnu_sos">
                    <a href="sosVehiclesDashboard.action">
                    SOS<span id="sos_selected">
                    </span>
                    </a>
                </li> -->
                <!--  <li class="classic-menu-dropdown" id="mnu_device">
                    <a href="showDeviceDashboard.action">
                    DEVICE<span id="device_selected">
                    </span>
                    </a>
                </li>
                 <li class="classic-menu-dropdown" id="mnu_device">
                    <a href="birtdepotwiserevenue.action">
                    Birt Reports<span id="birt_selected">
                    </span>
                    </a>
                </li> -->
            </ul>
        </div>    
        <!-- END HORIZANTAL MENU -->        
        <!-- BEGIN RESPONSIVE MENU TOGGLER -->
        <div class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse">
        </div>
        <!-- END RESPONSIVE MENU TOGGLER -->
        <!-- BEGIN TOP NAVIGATION MENU -->
        <div class="top-menu">
            <ul class="nav navbar-nav pull-right">
                
                <!-- BEGIN USER LOGIN DROPDOWN -->
                <!-- BEGIN USER LOGIN DROPDOWN -->
                <%User user = (User) session.getAttribute("user");
                String username=user.getFirstname();
                Integer userid=user.getUserId();
                    System.out.println("username="+username);
                    %>
                <li class="dropdown dropdown-user">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                    <!-- <img alt="" class="img-circle" src="assets/admin/layout/img/avatar3_small.jpg"/> -->
                    <span class="username">
                    <strong><%=username %></strong>
                     </span>
                    <i class="fa fa-angle-down"></i>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="changepasswordAction.action?">
                            <i class="fa fa-user"></i>Change Password</a>
                         </li>
                        <!-- <li>
                            <a href="page_calendar.html">
                            <i class="fa fa-calendar"></i> My Calendar </a>
                        </li>
                        <li>
                            <a href="inbox.html">
                            <i class="fa fa-envelope"></i> My Inbox <span class="badge badge-danger">
                            3 </span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                            <i class="fa fa-tasks"></i> My Tasks <span class="badge badge-success">
                            7 </span>
                            </a>
                        </li>
                        <li class="divider">
                        </li>
                        <li>
                            <a href="extra_lock.html">
                            <i class="fa fa-lock"></i> Lock Screen </a>
                        </li>-->
                        <li>
                            <a href="LogoutAction.action">
                            <i class="fa fa-key"></i> Log Out </a>
                        </li>
                    </ul>
                </li>
                <!-- END USER LOGIN DROPDOWN -->
                <!-- END USER LOGIN DROPDOWN -->
            </ul>
        </div>
        <!-- END TOP NAVIGATION MENU -->
    </div>
    <!-- END HEADER INNER -->
</div>
<!-- END HEADER -->