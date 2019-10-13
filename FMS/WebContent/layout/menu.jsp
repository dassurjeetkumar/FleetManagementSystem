<%-- 
    Document   : menu.jsp
    Created on : May 18, 2014, 20:33:01 PM
    Author     : manojv
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.trimax.its.dao.MenuDao,com.trimax.its.model.User,com.trimax.its.model.Menu"%>
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-3-typeahead/4.0.2/bootstrap3-typeahead.js">

<%@ page import="java.util.List"%>

<style type="text/css">
 .title{
 	font-style: bold; 
 	font-size: 15px; 
 } 
 
/*  .path{
    background-color: #98c2e6;
}
 
 .redColor{
    background-color: #98c2e6;
}
  */
</style>

<script type="text/javascript">
/* $(document).ready(function() {
	var val="path";
	alert("val id"+val);
	if(val == "path"){
		alert("herehehe");
		 $(".js-example-basic-single").select2();
	}
 
}); */


/* function data(){
	var val="path";
// 	alert("path--"+val);
	if(val == "path"){
		 $("#path").select2();
		 
	}
} */
window.onload=data;



function getAction(val){
	var	arr=$('#path').val();
// 	alert("path"+arr+"......."+val);
	document.form.action = arr;
	 document.form.submit();
}
</script>

<!-- BEGIN SIDEBAR -->
	<div class="page-sidebar-wrapper">
		<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
		<!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
		<div class="page-sidebar navbar-collapse collapse">
			<!-- BEGIN SIDEBAR MENU -->
			<ul class="page-sidebar-menu page-header-fixed " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
				<!-- DOC: To remove the sidebar toggler from the sidebar you just need to completely remove the below "sidebar-toggler-wrapper" LI element -->
				<li class="sidebar-toggler-wrapper">
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
					<div class="sidebar-toggler">
					</div>
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
				</li>
				<!-- DOC: To remove the search box from the sidebar you just need to completely remove the below "sidebar-search-wrapper" LI element -->
				<%
				MenuDao menuDao = new MenuDao();
				User user = (User) session.getAttribute("user");
				//System.out.println("userId=" + user.getUserId());
				List list = menuDao.getMenuList(user);
				int parentId = 0;
				%>
				<li class="sidebar-search-wrapper hidden-xs">
					<!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->
					<!-- DOC: Apply "sidebar-search-bordered" class the below search form to have bordered search box -->
					<!-- DOC: Apply "sidebar-search-bordered sidebar-search-solid" class the below search form to have bordered & solid search box -->
					<form class="sidebar-search" action="" method="POST" name="form">
					<div >
							<select class="form-control"  onchange="getAction(this.value)" id="path"> 
<%-- 							<select class="js-example-basic-single" style="background:blue;"  onchange="getAction(this.value)" id="path" > --%>
							<option value="" >--Search here--</option>
			<%				for (int i = 0; i < list.size(); i++) {
				Menu menu = (Menu) list.get(i);
				parentId = menu.getPageId();
			%>
							<option value="<%=menu.getPath()%>"><%=menu.getPageName()%></option>
							<%}%>
							</select>
						</div>
						



						
						
<!-- 						<a href="javascript:;" class="remove"> -->
<!-- 						</a> -->
<!-- 						<div class="input-group"> -->
<!-- 							<input type="text" class="form-control" placeholder="Search..."> -->
<%-- 							<span class="input-group-btn"> --%>
<!-- 							DOC: value=" ", that is, value with space must be passed to the submit button -->
<!-- 							<input class="btn submit" type="button" type="button" value=" "/> -->
<%-- 							</span> --%>
<!-- 						</div> -->
					</form>
					<!-- END RESPONSIVE QUICK SEARCH FORM -->
				</li>
			
	<!-- filter menu by role -->

			
<%
				for (int i = 0; i < list.size(); i++) {
					Menu menu = (Menu) list.get(i);
					parentId = menu.getPageId();
			%> 
			<li class="nav-item start">
				<%
					if (menu.getParentId() == 0 && menu.getLevel() == 0) {
				%> <a href="<%=menu.getPath()%>" class="nav-link nav-toggle"> <i class="icon-home"></i> <span
					class="title"><%=menu.getPageName()%></span> <span class="arrow"></span>
			</a> 
				<ul class="sub-menu">
					<%
								int subChild = 0;
								for (int j = 0; j < list.size(); j++) {
									menu = (Menu) list.get(j);
									subChild = menu.getPageId();
									//System.out.println("menu.getParentId()="+ menu.getParentId() + "  j=" + j + " lvl="
									//		+ menu.getLevel());
									if (menu.getParentId() == parentId
											&& menu.getLevel() == 1) {
										//System.out.println("pg name= " + menu.getPageName());
					%>

					<li class="nav-item start"><a href="<%=menu.getPath()%>" class="nav-link "> <i class="icon-bar-chart"></i> <%=menu.getPageName()%>
					</a>
						<!-- <ul class="sub-menu"> -->
							<%  int count=0,count2=0;
								for (int k = 0; k < list.size(); k++) {
													menu = (Menu) list.get(k);
													//count2--;
													//System.out.println("menu.getParentId()="+menu.getParentId()+"  k="+k+" lvl="+menu.getLevel());
													if (menu.getParentId() == subChild
															&& menu.getLevel() == 2) {
														count++;
														//System.out.println("pg name= "+menu.getPageName());
				             if(count==1){count2=1;
// 				             System.out.println("count2>>="+count2);
							%>
							<ul class="sub-menu">
							<%}%>
							<li class="nav-item start "><a href="<%=menu.getPath()%>"> <i class="icon-bar-chart"></i> <%=menu.getPageName()%>
							</a></li>
							<%-- <%if(count2==0){ %>
							</ul> --%>
							<%//}
								}
													
							
							}
									if(count2==1){ %>
										</ul>
							          <%}%>
						 
						</li>
					<%
						}
					}
					%>

				</ul> 
		<%}%>
			</li>
		<%}%>
		</ul>
			<!-- END SIDEBAR MENU -->
		</div>
	</div>
	<%-- <script src="assets/layouts/layout6/scripts/layout.min.js" type="text/javascript"></script> --%>
	<%-- <script src="assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
     <script src="assets/layouts/global/scripts/quick-nav.min.js" type="text/javascript"></script> --%>
	<!-- END SIDEBAR -->