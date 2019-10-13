<%-- 
    Document   : SearchRoute.jsp
    Created on : 26th May 2014
    Author     : Manoj Vishwakarma
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
 <script src="assets/global/plugins/jquery-1.11.0.min.js"
	type="text/javascript"></script>
	<script src="assets/vts/js/vts.js" type="text/javascript"></script>
<!DOCTYPE html>

<%-- <s:if test="hasFieldErrors()"> --%>
<script>
/* jQuery(document).ready(function(){
	getServiceId();
}); */

function isInteger(n) {
	if(parseFloat(n) != parseInt(n, 10)){
	   return true;
	}else{
		return false;
	}
	} 

function getServiceId(){
	var len= document.getElementById('servicetypes').options.length;

	 if(len<=1 ) {
         $.ajax({
             type: "get",
             url: '<%=request.getContextPath()%>/SearchRouteAction!getServiceTypes',
             success: function(result) {
                 document.getElementById('servicetypes').innerHTML=result;
             }
         });
	 }
 }

function isInt(n) {
	   return typeof n === 'number' && n % 1 == 0;
	}

function checkInt(){
	
	var val=document.getElementById('version').value;
	
	if(isNaN(val) || isInteger(val) || isInt(val)){
		
	   if(val==""){
		 document.getElementById('integerVal').innerHTML='Please enter Version Number';   
	   }
	   else{
	   document.getElementById('integerVal').innerHTML='Version Number should be Integer value';
	   }
	   return false;
	}
}
$(document).ready(function() {

//	window.history.pushState("", "", "SearchRouteAction!add");
	
}); 
function getrouteno(){
	var firstrout1=document.getElementById('busstoplist');
	var firstrout=firstrout1.options[firstrout1.selectedIndex].value;
	var secontbstop1=document.getElementById('busstoplist1');
	var secontbstop=secontbstop1.options[secontbstop1.selectedIndex].value;
	//Calling DataTable
	document.getElementById("routesearch").style.display='block';
		$('#routesearch').dataTable(
		{
			"aLengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
			// per
			// page
			// values
			// here
			],
			// set the initial value
			"iDisplayLength" : 5,
			"sAjaxSource" : "getroutdetails?firstbusstop="+firstrout+"&secondbusstop="+secontbstop,
			"sPaginationType" : "bootstrap",
			"bDestroy" : true,
			"oLanguage" : {
				"sLengthMenu" : "_MENU_ records",
				"oPaginate" : {
					"sPrevious" : "Prev",
					"sNext" : "Next"
				}
			},
			"aoColumnDefs" : [ {
				'bSortable' : false,
				'aTargets' : [ 0 ]
			} ]
		});
		

	
	//alert(firstrout+"...."+secontbstop);
	<%--  $.ajax({
         type: "get",
         async:false,
         url: '<%=request.getContextPath()%>/getroutdetails?firstbusstop='+firstrout+'&secondbusstop='+secontbstop,
         success: function(result) {
             document.getElementById('routedata').innerHTML=result;
         }	
     }); --%>
	 
}
</script>
       
   
<div class="page-content-wrapper" id="mymodal1">
	<div class="page-content">
		<div class="tab-content">
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
					 SEARCH ROUTE <small></small> 
				</h3>
				<FONT color="green"><s:actionmessage /></FONT>
			
			<div class="tab-pane active" id="tab_0">
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i>Create Search Route
						</div>
					</div>
					</div>
					<div class="portlet-body form">
						<span style="color:red;"><s:actionerror/>
							<s:actionmessage/>
						</span>
						<form action="SearchRouteAction!addRate" onsubmit="return checkInt()" class="form-horizontal" method="post">
						    <s:if test="formType==2">
						    	<%int RouteId=(Integer)session.getAttribute("rateId"); %>
						   		<input name="id" type="hidden" value="<%=RouteId%>"/>
						    </s:if>
						    <s:else>
						    	<input name="id" type="hidden" value="0"/>
						    </s:else>
							<br>
							<div class="form-group">
								<label class="col-md-3 control-label">Source:<font color="red">*</font></label>
								<div class="col-md-3">
									 <s:select list="busstoplist" id="busstoplist"  
												name="source" 
 												cssClass="select2_category form-control" 
												  headerKey="0" headerValue="select"></s:select> 
					
<%-- 											<s:if test="fieldErrors.get('serviceTypeId').size() > 0"> --%>
<%-- 			     								<span style="color:red;"><s:property value="fieldErrors.get('serviceTypeId').get(0)" /></span> --%>
<%-- 											</s:if>	 --%>
											
											
								</div>	
						 	</div>
							<div class="form-group">
								<label class="col-md-3 control-label">Destination:<font color="red">*</font></label>
								<div class="col-md-3">
								<%-- 	<select class="form-control" name="SearchRoute.serviceTypeId" 
												id="servicetypes" onclick="getServiceId()">
										<option value="<s:property value='SearchRoute.serviceTypeId'/>"><s:property value='SearchRoute.versionNoServiceType'/></option>
									</select>
								 --%>			
								 <s:select list="busstoplist" id="busstoplist1"  
												name="destination" 
 												cssClass="select2_category form-control" 
												 onchange="getDepot(this.value)" headerKey="0" headerValue="select"></s:select> 
								 <s:if test="fieldErrors.get('serviceTypeId').size() > 0">
			     								<span style="color:red;"><s:property value="fieldErrors.get('serviceTypeId').get(0)" /></span>
											</s:if>									    
								</div>
								
							</div>
							<div class="form-actions fluid">
								<div class="col-md-offset-3 col-md-9">
									<button type="button" class="btn blue" onclick="getrouteno()" >Submit</button>
									<button type="button" class="btn default" onclick = "javascript:location.href='RateMasterAction';">Cancel</button>
				
			  					</div>
						  	</div>
						<div class="portlet-body" style=" width: auto">
						<table class="table table-striped table-bordered table-hover"
							id="routesearch"  >
								<thead> 
							  		<tr>
										<th>Sr. NO</th>
										<th>Route No</th>
										<th>Route Name</th>
										<th>Via Place</th>
									</tr>
								</thead>
							</table>
						</div></form>
					</div>
				 </div>
				</div>
			</div>
		</div>
	</div>
</div>	