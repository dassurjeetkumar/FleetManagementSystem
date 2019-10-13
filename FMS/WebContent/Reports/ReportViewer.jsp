<%@page import="java.util.Collections"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.trimax.its.report.dao.ReportDao,com.trimax.its.report.model.ReportDetail"%>
    <%@ page import="java.util.List,com.trimax.its.model.User"%>
    <%@taglib uri="/struts-tags" prefix="s" %>
    <%-- <%@ taglib uri="/WEB-INF/tlds/birt.tld" prefix="birt"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

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
 <script src="assets/admin/pages/scripts/birtreports.js" type="text/javascript"></script>
<style type="text/css">
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
</style>

</head>

<body>
<form name="f1" target="viewer"  id="f1" method="post"> 
<%
/*System.out.println("getLocalAddr() : "+request.getLocalAddr());
System.out.println("getRemoteAddr() : "+request.getRemoteAddr());
System.out.println("getServerName() : "+request.getServerName());
System.out.println("getRequestURL() : "+request.getRequestURL());
*/

%>
<div   style="background-color: #428bca !important;height: 40px;">
	<div style="float:left;" class="page-content">

	<input id="b1" type="button" style="margin-left:7px;"   class="btn_default" onclick="toggleF('ScrollCB')" value="Select Column"/>&nbsp;&nbsp;&nbsp;&nbsp;
	<input id="b2" type="button" class="btn_default"  onclick="toggleF('ScrollCB2')" value="Sort"/>&nbsp;&nbsp;&nbsp;&nbsp;
	<input id="b3" type="button" class="btn_default"  onclick="toggleF('ScrollCB3')" value="Search" />&nbsp;&nbsp;&nbsp;&nbsp;
	<input id="b4" type="button"   class="btn_default" value="Aggregation" />&nbsp;&nbsp;&nbsp;&nbsp; 
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
	<a   style="color:white;" href="viewMasterReportList">Back</a>
	</span>
	&nbsp;
	<span style="font-size:13px;color:white;">
		Welcome<strong> <%=username %></strong>
	</span>
	</div>
	<br>
</div>
<%-- <jsp:include page="/footer.jsp"></jsp:include> --%>
<!-- <div class="portlet-title"> -->
<!-- <div class="caption"> -->
<!-- <i class="fa fa-gift"></i> -->
<!-- Default Form Height Sizing -->
<!-- </div> -->
<!-- <div class="tools"> -->
<!-- </div> -->
<!--                             
	<!-- Hidden fields -->
	<input id="sort" type="hidden" name="sort" value="" />
	<input id="filter" type="hidden" name="filter" value="" /><br>
	<input id="aggregate" type="hidden" name="agg" value="" /><br>
	<!-- Dynamic column addition/droping -->
	
	
	
	
	<div id="ScrollCB"  class="page-content" style="height:200px;width:40%; overflow-y: scroll;border:1px solid gray;display:none; ">

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
<%-- 	<h4 style="color: font-size:14px;">Select The Columns To View Result:</h4><span class="closewindow .tools"><input type="button"  id="close" style="background-image: url('assets/global/img/portlet-remove-icon.png');background-color: #f3565d;margin-bottom: 2px;background-repeat: no-repeat;width: 25px; "> </span>  --%>
	<div id="Searchdiv" >
	
	<%
				ReportDao Dao = new ReportDao();
				
				int tableid = (Integer)session.getAttribute("tableid");
  
				List<ReportDetail> list = Dao.getReportDetailSelectColumns(tableid);
				
				out.println("<input type=\"hidden\" name=\"id\" value=\""+tableid+"\"/>");
				
				for (int i = 0; i < list.size(); i++) {
					ReportDetail rd = (ReportDetail) list.get(i);

					if(rd.getDefaultColumn().equals("Y")){
						out.println("<input type=\"checkbox\" class=\"select\" style=\"margin-left:7px\"  name=\""+rd.getColumnName()+"\" value=\""+rd.getColumnName()+"\" checked />"+rd.getAlias()+"<br>");
					}else{
						out.println("<input type=\"checkbox\"  class=\"select\"  style=\"margin-left:7px\" name=\""+rd.getColumnName()+"\" value=\""+rd.getColumnName()+"\" />"+rd.getAlias()+"<br>");
					}
				}
				String url=(String)session.getAttribute("url");
				
				
				
				//System.out.println("url="+url);
	%>
	
	
	<input id="getReport" class="btn_blue"  style="margin-left:7px; " type="button" value="Submit" onclick="report1('')"/><br>
</div>
</div>
	</div> 
	</div>
	</div>
		
	
		<%
		 try{ 
			 String FilterAlias=(String)session.getAttribute("FilterName"); 
			 String aggAlias=(String)session.getAttribute("AggName"); 
			 String sortname=(String)session.getAttribute("SortAlias");
			String filterText=(String)session.getAttribute("FilterBy");
			String aggText=(String)session.getAttribute("AggBy");
		  	String sort=(String)session.getAttribute("SortText");
			
		 	 
	        if(filterText==null){
	        	 session.removeAttribute("FilterBy");
	        	//out.print("");
 		            }
	        if(aggText==null){
	        	 session.removeAttribute("AggBy");
	        	//out.print("");
		            }
	 
	        if(filterText!=null){
	 	    out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<H4 style='margin-left:7px;'>"+filterText+" "+FilterAlias+"</H4>");
	        session.removeAttribute("FilterName"); 
	        session.removeAttribute("FilterBy");
	        session.removeAttribute("SelectWhere"); 
	      
	        session.removeAttribute("Code"); 
	 	    }
	        if(aggText!=null){
		 	    out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<H4 style='margin-left:7px;'>"+aggText+" "+aggAlias+"</H4>");
		        session.removeAttribute("AggName"); 
		        session.removeAttribute("AggBy");
		        session.removeAttribute("SelectWhere"); 
		        session.removeAttribute("whereClauseAgg");
		        session.removeAttribute("SortCondition");
		 	    }
	        if(sort==null){
	        	session.removeAttribute("SortText");
	        
	        }
	        if(FilterAlias==null){
	        	//out.print("");
	        }
	        if(sortname==null){
	        	//out.print("");
	        }
		    if(sort!=null)
		      {   		
					 out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<H4 style='margin-left:7px;'>"+sort+" "+sortname+"</H4>");
					 session.removeAttribute("SortText");
					 session.removeAttribute("SortAlias"); 
					 
				 }
		    }catch(Exception e){
		    	System.out.print("Exception is"+e);
		    
		    }
		%>
		
	
<%-- 	 Filter By :	 <%=col%> --%>
<%-- 	 Sort By: <%=sortname %> --%>
	<!-- Sorting -->
	<div id="ScrollCB2" class="page-content" style="height:200px;width:40%; overflow-y: scroll;border:1px solid gray;display:none; ">
	
	
	
		<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i><font color="black" style="font-size:80%;">Select The Columns To Sort:</font>
							</div>
                        <div class="tools">

                         <input type="button" class="remove"  id="close1" style="background-image: url('assets/admin/layout/img/CloseButton.png');margin-bottom: 2px;background-color:transparent;background-repeat: no-repeat;width: 25px;border-style:none;">
                       </div>
						</div>
							</div> 
<!-- 		<h4 style="color: #333;font-size:14px;">Select The Columns To Sort:</h4> <input type="button" class="closewindow"  id="close1" style="background-image: url('assets/global/img/portlet-remove-icon.png');background-color: #f3565d;margin-bottom: 2px;background-repeat: no-repeat;width: 25px;"> -->
<%--         <h5 style="color=red;"><s:actionmessage/></h5> --%>
	<%
			/* 	for (int i = 0; i < list.size(); i++) {
					ReportDetail rd = (ReportDetail) list.get(i);
					System.out.println(rd.getColumnName());
					//if(rd.getDefaultColumn().equals("Y")){
					
					//out.println("<input type=\"checkbox\" name=\""+rd.getColumnName()+"\" value=\""+rd.getColumnName()+"\" checked />"+rd.getAlias()+"<br>");
					
					//}else{
						out.println("<input type=\"checkbox\" name=\""+rd.getColumnName()+"*\" value=\""+rd.getColumnName()+"\" />"+rd.getAlias()+"<br>");
						
					//}
				}		 */
	%>
	
	<table id="sortTable" border="0">
	 <!-- <tr align="center"><td>Column name</td><td>Operator</td><td>Value</td></tr> -->
	 <tr id="trSortDummy" style="display:none;">
	 <td>
	 <select style="margin-left:7px;" id="sort4" class="select2-input"  name="sortColumn">
      <option value="0" selected="selected">Select</option> 
	 <%
	 List<ReportDetail> listSort = Dao.getReportDetailSortSearchGrp(tableid,"sort='Y'");
	 
	 for(int i = 0; i < listSort.size(); i++) {
		ReportDetail rd = (ReportDetail) listSort.get(i);
		//out.println("<option value=\""+rd.getColumnName()+"\">"+rd.getAlias()+"</option>");			
		out.println("<option value=\""+rd.getColumnName()+"\">"+rd.getAlias()+"</option>");				

	   }
	 %>	
	 </select>
	 
	 
	
	 <select name="orderby" id="order">
	<option value="" selected="selected">Ascending</option>
	<option value="desc">Desceding</option>
	 </select>
	
<input type="button" value="-"   class="btn_default" onclick="deleteRowSort(this)">
	 </td>
	 </tr>
	 
	  <tr   id="trSort">
	 <td>
	<select style="margin-left:7px;" id="sort3" class="select2-choice" name="sortColumn">
	
	  <option value="0" selected="selected">Select</option>
	  
	 <% 
	 for(int i = 0; i < listSort.size(); i++) {
		ReportDetail rd = (ReportDetail) listSort.get(i);
		out.println("<option value=\""+rd.getColumnName()+"\">"+rd.getAlias()+"</option>");				
	   }
	 %>	
	 </select>
	
	 <select name="orderby" id="order">
	<option value="" selected="selected">Ascending</option>
	<option value="desc">Desceding</option>
	 </select>
	
	
	  <input type="button" name="addRow" value="+"   class="btn_default" onclick="cloneRowSort()"/>
	 </td>
	 </tr>
	
	 
	 </table>
	 
	   <input type="button" style="margin-left:7px; " onclick="report('y')"  class="btn_blue" value="Sort"/>
	  

</div>

</div>
</div>










	<!-- Filter -->
	<div id="ScrollCB3"  class="page-content" style="height:200px;width:65%;overflow-y: scroll;border:1px solid gray;display:none;">
	
	<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey">
						<div class="portlet-title " >
							<div class="caption">
								<i class="fa fa-globe"></i><font color="black" style="font-size:80%;">Select The Columns To Search:</font>
							
							</div>
                        	<div class="tools">
<!--                                 <a class="remove" href="" ></a> -->
                       <input type="button" class="remove"  id="close2" style="background-image: url('assets/admin/layout/img/CloseButton.png');background-color:transparent;margin-bottom: 2px;background-repeat: no-repeat;width: 25px;border-style:none;"> 
                       </div>
						</div>
							</div> 	
<!-- 	         <h4 style="color: #333;font-size:14px;">Select The Columns To Search:</h4>  <input type="button" class="closewindow" name="close" id="close2" style="background-image: url('assets/global/img/portlet-remove-icon.png');background-color: #f3565d;margin-bottom: 2px;background-repeat: no-repeat;width: 25px;"> -->
	       
	 
	 <table id="filterTable" border="0">
	 <!-- <tr align="center"><td>Column name</td><td>Operator</td><td>Value</td></tr> -->
	 <tr id="trDummy" style="display:none;  ">
	 
	 <td>
	 <!-- Operator -->
	  <select id="f1-1"  name="filterOperator" style="margin-left:7px;">
	 	<option value="AND" selected="selected">AND</option>
<!-- 	 	<option value="OR"> OR </option> -->
	  </select>	
	  
	  <select id="f1-2"  class="filterSelect" name="filterColumn" >
	  <option value="0">Select</option>
	 <%
	 List<ReportDetail> listFilter = Dao.getReportDetailSortSearchGrp(tableid,"sort='Y'");
	 //Collections.sort(listFilter);
	// System.out.println();
	 for(int i = 0; i < listFilter.size(); i++) {
		ReportDetail rd = (ReportDetail) listFilter.get(i);
		out.println("<option value=\""+rd.getColumnName()+"\">"+rd.getAlias()+"</option>");				
	   }
	 %>	
	 </select>
	 
	 <select  id="fexp1"  class="filexp" name="filterExpression">
	 	<option value="=" selected="selected">=</option>
	 	<option value="<"> < </option>
	 	<option value=">"> > </option>
	 	<option value="<="><=</option>
	 	<option value=">=">>=</option>
	 	<option value="!=">!=</option>
	 	<option value="LIKE">LIKE</option>
	 	<option value="LIKE %">LIKE %%</option>
	 	<option value="IN">IN</option>
	 </select>
	 
	 <input id="f1-3" type="text" name="filterValue" value="" class="filinp"   onblur="filterIN(this)">
	 
	 
	 <input type="button" value="-"  class="btn_default" onclick="deleteRowFilter(this)">
	 </td>

	 </tr>
	 <tr id="trNew" >
	 <td>
	  <!-- Operator -->
	  <select id="f1-2"  name="filterOperator" style="margin-left:7px; ">
	 	<option value="AND" selected="selected">AND</option>
	 	
	  </select>	
	 
	  <!-- Column -->
	  <select id="f1-11" class="filterSelect"  name="filterColumn">
	  <option value="0">Select</option>
	 <%for(int i = 0; i < listFilter.size(); i++) {
		ReportDetail rd = (ReportDetail) listFilter.get(i);
		out.println("<option value=\""+rd.getColumnName()+"\">"+rd.getAlias()+"</option>");	
		}
	%>
	 </select>
	 
	 <!-- Expression -->
	 <select  id="fexp1" class="filexp"  name="filterExpression">
	 	<option value="=" selected="selected">=</option>
	 	<option value="<"> < </option>
	 	<option value=">"> > </option>
	 	<option value="<="><=</option>
	 	<option value=">=">>=</option>
	 	<option value="!=">!=</option>
	 	<option value="LIKE">LIKE</option>
	 	<option value="LIKE %">LIKE %%</option>
	 	<option value="IN">IN</option>
	 </select>
	 
	 <!-- Value -->
	 <input id="f1-33" type="text" name="filterValue" value=""  class="filinp" onblur="filterIN(this)">
	 
	 <input type="button" name="addRow" value="+"   class="btn_default" onclick="cloneRow()"/>
   
	 </td>
	 </tr>
	 <tr>

	 
	 </tr>
	 </table>
	 		 <input type="button"  class="btn_blue" style="margin-left: 7px;" onclick="reportFilter()" value="Submit" />
	</div>
	
	</div>
	</div>
	
	
	
	
 	<div id="ScrollCB4"  class="page-content" style="height:200px;width:40%;overflow-y: scroll;border:1px solid gray;display:none;">	
 	
 	
 	
 	<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey">
						<div class="portlet-title "">
							<div class="caption" >
								<i class="fa fa-globe"></i><font color="black" style="font-size:80%;">Select The Columns To Do Aggregation</font>
							</div>
                        <div class="tools">

                         <input type="button" class="remove"  id="close3" style="background-image: url('assets/admin/layout/img/CloseButton.png');background-color:transparent;margin-bottom: 2px;background-repeat: no-repeat;width: 25px;border-style:none;">
                       </div>
						</div>
							</div> 
							
							  <div class="form-body">

									<div class="form-group">
										<label class=" class="form-horizontal" style="margin-left:7px;">Note:-PLease Select Only Column To Perform GroupBy.<font
											color="red"></font></label>
											</div>
											</div>
<!--  	         <h4 style="color: #333;font-size:14px; ">Select The Columns To Do Aggregation</h4>  <input type="button" class="closewindow" name="close" id="close3"style="background-image: url('assets/global/img/portlet-remove-icon.png');background-color: #f3565d;margin-bottom: 2px;background-repeat: no-repeat;width: 25px;"> -->
  	       
<!-- 	  <p style="color: #333;font-size:12px; ">Note:-PLease Select Only Column To Perform GroupBy. <p> -->
 	 <table id="aggrTable" border="0">
 	  <tr align="center"><td></td><td></td><td></td></tr>
 	 <tr id="traggDummy" style="display:none; ">
	
 	 <td>
 	 <!--  Functions -->
 	   <select style="margin-left:7px;"  id="aggdummy"  name="aggFuction"> 
  	        <option Value="0">Functions</option>
  	   	<option value="C">Count</option>
 	 	<option value="S">Sum</option>
 	 	<option value="A">Avg</option>
 	 	<option value="m">Min</option>
 	 	<option value="M">Max</option>
 	 	<option value="D">Date</option>
	 	
 	 </select> 
 	   <!--COlumns for Aggregation-  -->
 	  <select id="aggColumnId1"  name="aggColumn"  class="aggColumn"> 
 	  <option value="0">Columns</option>
 	  <% 
	 List<ReportDetail> listFilterAgg = Dao.getReportDetailSortSearchGrp(tableid,"sort='Y'");
      for(int i = 0; i < listFilter.size(); i++) {
 		ReportDetail rd = (ReportDetail) listFilter.get(i);
		out.println("<option value=\""+rd.getColumnName()+"\">"+rd.getAlias()+"</option>");				
		}
 	%> 
 	 </select> 
	 
	
	 <input type="button" value="-" onclick="deleteRowAgg(this)"  class="btn_default">

  	 </td> 
 	
 	 </tr>
 	 <tr id="trNewAgg" style="margin-left:7px; ">
 	 <td>
 	
	
 	 <select style="margin-left:7px;"  id="aggfunc"  name="aggFuction"> 
 	        <option value="0" selected="selected" >Functions</option>
 	 	<option value="C">Count</option>
 	 	<option value="S">Sum</option>
 	 	<option value="A">Avg</option>
 	 	<option value="m">Min</option>
 	 	<option value="M">Max</option>
 	 	<option value="D">Date</option>
	      </select> 
	 	   
 	  <select id="aggColumnId"  name="aggColumn" class="aggColumn"> 
 	  <option value="0" selected="selected" >Columns</option>
 	 <%for(int i = 0; i < listFilter.size(); i++) { 
 		ReportDetail rd = (ReportDetail) listFilter.get(i);
		out.println("<option value=\""+rd.getColumnName()+"\">"+rd.getAlias()+"</option>");	
 		}
 	 %>	 
 	 </select> 

	

	 
 	 <input type="button" name="addRow" value="+"   class="btn_default" onclick="cloneRowAgg()"/>

 	 </td>
 	 </tr>
	 
 	 </table>
 	 		 <input type="button" class="btn_blue"  style="margin-left:7px; " onclick="reportAggregation()" value="Submit" />
 	</div>
	</div>
	</div>
	
	
	
	
	
	
	
	
	
	
	
	
</form>

<!-- <div id="dd">
   
</div> -->


<iframe id="viewer"  class="page-content" style="height:500px;width:100%;border:1px solid #C0C0B8;" src="<%=url%>">

</iframe>




<%-- <birt:viewer id="birtViewer" reportDesign="ItsDemo.rptdesign" baseURL="http://localhost:8080/birt"
 pattern="run"  height="600" width="1000" format="html" scrolling="yes" showParameterPage="false">
    <birt:param name="DataSet" value='bus_stop'></birt:param>
    <birt:param name="DynColumns" value='bus_stop_id'></birt:param>
    <birt:param name="DynColumnsName" value='Bus Stop Id'></birt:param>
    <birt:param name="DynColumns" value='bus_stop_name'></birt:param>
    <birt:param name="DynColumnsName" value='Bus Stop Name'></birt:param>
    <birt:param name="GroupBy" value=''></birt:param>
    <birt:param name="ReportTitle" value='Bus Stop Report'></birt:param>
    <birt:param name="SortKey" value='bus_stop_id'></birt:param>
    <birt:param name="String" value=''></birt:param>
    <birt:param name="Version" value=''></birt:param>
    <birt:param name="where" value=""></birt:param>
   <birt:param name="AscDesc" value='asc'></birt:param>
   <birt:param name="Columns" value=''></birt:param>
   <birt:param name="" value=''></birt:param>
   
</birt:viewer>
 --%>



	

</html>


