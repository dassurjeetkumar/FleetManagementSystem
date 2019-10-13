  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
  
 <form>
 <%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ShowBusStop.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
%>
<div class="page-content-wrapper">
<%if (access.equalsIgnoreCase("Y")){%>

		<div class="page-content">
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div class="modal fade" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">Modal title</h4>
						</div>
						<div class="modal-body">
							 Widget settings form goes here
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue">Save changes</button>
							<button type="button" class="btn default" data-dismiss="modal">Close</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			
			<!-- END STYLE CUSTOMIZER -->
			<!-- BEGIN PAGE HEADER-->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
					BUS STOPS <small></small>
					</h3>
					
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			
			<%-- //<s:hidden name="busid" id="id" ></s:hidden> --%>
			 <!-- <input type="hidden" name="busid" id="busid" value="22181"/> -->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>VIEW BUS STOPS
							</div>
							<div class="actions">
							<%if(edit.equalsIgnoreCase("Y")){ %>
							
								<a href="#" class="btn blue"  id="edit"> <!-- //onclick="callEdit()" -->
								<i class="fa fa-pencil"></i> Edit </a>
								<%}%>

<%if(delete.equalsIgnoreCase("Y")){ %>
								
								<a href="#" class="btn blue"  id="delete"> <!-- onclick="callDelete()" -->
								<i class="fa fa-pencil"></i> Delete </a>
								<%}%>
								<div class="btn-group">
								
									<button class="btn green dropdown-toggle" data-toggle="dropdown"><i class="fa fa-gears"></i> Tools <i class="fa fa-angle-down"></i>
									</button>
									<ul class="dropdown-menu pull-right ">
										<li>
											<a class="btn blue" href="javascript: void(0)" onclick="window.open('','_new').location.href='/its/generateReport?id=BUSSTOPRPT'">
											<!-- href="/its/ReportAction!generateReport?id=1"> -->
											Report </a>
										</li>	
										
																			
									</ul> 
							</div>
							</div>
						</div>
						
						<div class="portlet-body">
						<s:if test="hasActionErrors()">
   <div class="alert alert-danger">
			<button class="close" data-close="alert"></button>
			<span>
			<s:actionerror/> </span>
		</div>
      
   
</s:if>
						<div class="table-scrollable">
						
						<input type="hidden" name="requestType" id="requestType" value="text"/>
							<table class="table table-striped table-bordered table-hover" id="busstoptable">
							<thead>
							<tr>
								<th style="width1:8px;">
									
								</th>
								<th>
									 Id 
								</th>
								<th>
									 Name
								</th>
								<th>
									Kanadda Name
								</th>
								<th>
									 English Code 
								</th>
								
								<th>
									 Kanadda Code 
								</th>
								<th>
									 Locality
								</th>
								<th>
									 Landmark
								</th>
								<th>
									 Is Sheltered
								</th>
								<th>
									Alias 1
								</th>
								<th>
									 Kannada Alias 1
								</th>
								<th>
									 Alias 2
								</th>
								<th>
									 Kannada Alias 2
								</th>
								<th>
									 Alias 3
								</th>
								<th>
									 Kannada Alias 3
								</th>
								<th>
									 Alias 4
								</th>
								<th>
									 Kannada Alias 4
								</th>
								<!-- <th>
									 Alias 5
								</th>
								<th>
									 Kannada Alias 5
								</th>
								<th>
									 Alias 6
								</th>
										<th>
									 Kannada Alias 6
								</th>				 -->	
								<th>
									 Toll Zone
								</th>
								<th>
									 City Limit
								</th>
								<th>
									 Ward No
								</th>
								<th>
									 Area Population
								</th>
								
								<th>
									Towards
								</th>
								<th>
									 Stop Size
								</th>
								<th>
									 Fare Stage
								</th>
								<th>
									 Sub Stage
								</th>
								<th>
									Description
								</th>
								<th>
									Status
								</th>
								
								
							</tr>
							</thead>
							<%-- <tbody>
							<s:iterator value="list" id="list">
    <tr>
    <th style="width1:8px;">
									<input type="checkbox" class="group-checkable" data-set="#sample_2 .checkboxes" id='<s:property value="id"/>' value='<s:property value="id"/>'/>
								</th>
    <td align="center"><s:property value="id"/></td>
    <td align="center"><s:property value="busStopName"/></td>
    <td align="center"><s:property value="busStopNameKannada"/></td>
    
    <td align="center"><s:property value="bus_stop_code"/></td> 
    <td align="center"><s:property value="bus_stop_code_kannada"/></td>
    
    <td align="center"><s:property value="locality"/></td>
    <td align="center"><s:property value="landmark"/></td>
    
    <td align="center"><s:property value="sheltered"/></td>
    
    <td align="center"><s:property value="alias1"/></td> 
    <td align="center"><s:property value="alias2"/></td>
    <td align="center"><s:property value="alias3"/></td>
    <td align="center"><s:property value="alias4"/></td> 
    <td align="center"><s:property value="alias5"/></td>
    <td align="center"><s:property value="alias6"/></td>
    
    <td align="center"><s:property value="kalias1"/></td>
    <td align="center"><s:property value="k_alias_2"/></td>
    
    <td align="center"><s:property value="tollZone"/></td>
    <td align="center"><s:property value="cityLimit"/></td> 
    
    <td align="center"><s:property value="wardNumber"/></td>
    <td align="center"><s:property value="areaPopulation"/></td>
    
    <td align="center"><s:property value="stop_direction"/></td>
    
    
    <td align="center"><s:property value="stopSize"/></td>
    <td align="center"><s:property value="fareStage"/></td>
    <td align="center"><s:property value="description"/></td> 
    <td align="center"><s:property value="status"/></td>  
    
    
   
    
    
    
    
    
    
    
        
    
    </tr>
    </s:iterator>
							</tbody>  --%>
							</table>
							
							</div>
						</div>
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
			</div>
			
			<!-- END PAGE CONTENT-->
		</div>
	</div>
	
	</form>
	
<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>
	