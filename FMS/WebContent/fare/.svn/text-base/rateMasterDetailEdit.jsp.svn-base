<%-- 
    Document   : rateMasterAdd.jsp
    Created on : 26th May 2014
    Author     : Manoj Vishwakarma
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>

<%-- <s:if test="hasFieldErrors()"> --%>
<script>
function myTrim(x) {
    return x.replace(/^\s+|\s+$/gm,'');
}
function calcFare(stageNo){
  var adult=myTrim(document.getElementById("adult"+stageNo).value);
  document.getElementById("adult"+stageNo).value=parseInt(adult);	  
//validate input value
	if(checkInt(adult)){
  
	 var child=Math.round(adult*0.50);
 	 var senior=Math.round(adult*0.75);
  
  	 document.getElementById("children"+stageNo).value=child;
  	 document.getElementById("seniorCitizen"+stageNo).value=senior;

	}else{
		alert('Fare amount value entered is Invalid.');
		document.getElementById("adult"+stageNo).value="";
	}
}

function calcFare2(pType,stageNo){
	  var adult=myTrim(document.getElementById(pType+stageNo).value);
	  document.getElementById(pType+stageNo).value=parseInt(adult);	  
	//validate input value
		if(checkInt(adult)){
			  

		}else{
			alert('Fare amount value entered is Invalid.');
			 
			adult=myTrim(document.getElementById("adult"+stageNo).value);
			  document.getElementById("adult"+stageNo).value=adult;	  
			  
			  var child=Math.round(adult*0.50);
			  var senior=Math.round(adult*0.75);
			  
			  document.getElementById("children"+stageNo).value=child;
			  document.getElementById("seniorCitizen"+stageNo).value=senior;
		}
	}

function isInt(n) {
	   return typeof n === 'number' && n % 1 == 0;
	}
	
function isInteger(n) {
	if(parseFloat(n) != parseInt(n, 10)){
	   return true;
	}else{
		return false;
	}
	} 
	
function checkInt(val){
	
	//var val=document.getElementById('version').value;
	
	if(isNaN(val) || isInt(val) || val<0 || isInteger(val)){
		
	
	   //document.getElementById('integerVal').innerHTML='Invalid value';
	   return false;
	}else{
		return true;
	}
		
}

function validateStages(){
	var i=0;
	alert('valid');
	var aFare=0,cFare=0,sFare=0;
	for (i = 1; i <= 70; i++) {
		alert(i);
	    aFare=document.getElementById("adult"+i).value;
	    cFare=document.getElementById("children"+i).value;
	    sFare=document.getElementById("seniorCitizen"+i).value;
	   
	   if(aFare<=document.getElementById("adult"+i).value && cFare<=document.getElementById("children"+i).value && sFare<=document.getElementById("seniorCitizen"+i).value){
		   return true;
	   }else{
		   return false;
	   }
	}
}function validateStages(){
	var i=0;
	alert('valid');
	var aFare=0,cFare=0,sFare=0;
	for (i = 1; i <= 70; i++) {
		alert(i);
	    aFare=document.getElementById("adult"+i).value;
	    cFare=document.getElementById("children"+i).value;
	    sFare=document.getElementById("seniorCitizen"+i).value;
	   
	   if(aFare<=document.getElementById("adult"+i).value && cFare<=document.getElementById("children"+i).value && sFare<=document.getElementById("seniorCitizen"+i).value){
		   return true;
	   }else{
		   return false;
	   }
	}
}function validateStages(){
	var i=0;
	alert('valid');
	var aFare=0,cFare=0,sFare=0;
	for (i = 1; i <= 70; i++) {
		alert(i);
	    aFare=document.getElementById("adult"+i).value;
	    cFare=document.getElementById("children"+i).value;
	    sFare=document.getElementById("seniorCitizen"+i).value;
	   
	   if(aFare<=document.getElementById("adult"+i).value && cFare<=document.getElementById("children"+i).value && sFare<=document.getElementById("seniorCitizen"+i).value){
		   return true;
	   }else{
		   return false;
	   }
	}
}function validateStages(){
	var i=0;
	alert('valid');
	var aFare=0,cFare=0,sFare=0;
	for (i = 1; i <= 70; i++) {
		alert(i);
	    aFare=document.getElementById("adult"+i).value;
	    cFare=document.getElementById("children"+i).value;
	    sFare=document.getElementById("seniorCitizen"+i).value;
	   
	   if(aFare<=document.getElementById("adult"+i).value && cFare<=document.getElementById("children"+i).value && sFare<=document.getElementById("seniorCitizen"+i).value){
		   return true;
	   }else{
		   return false;
	   }
	}
}

function validateStages(){
	var i=0;


	
	for (i = 1; i <= 70; i++) {

		if(i>1){
		var aFare=parseInt(document.getElementById("adult"+(i-1)).value);
	    var cFare=parseInt(document.getElementById("children"+(i-1)).value);
	    var sFare=parseInt(document.getElementById("seniorCitizen"+(i-1)).value);
		
		var a=parseInt(document.getElementById("adult"+i).value);
		var c=parseInt(document.getElementById("children"+i).value);
		var s=parseInt(document.getElementById("seniorCitizen"+i).value);
		if(a!=0 && c!=0 && s!=0){
		if(aFare<=a && cFare<=c && sFare<=s){
		}else{
			   document.getElementById('error').innerHTML='Fare for stage '+i+' should  be greater than or equals to stage '+(i-1);
			   return false;
		}
		}

		}
	}
}

</script>


<div class="page-content-wrapper">
	<div class="page-content">
		<div class="tab-content">
		<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
					 RATE MASTER <small></small> 
					
					</h3>
					<%-- <FONT color="green"><s:actionmessage /></FONT> --%>
					<!-- END PAGE TITLE & BREADCRUMB-->
		</div>
			<div class="tab-pane active" id="tab_0">
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i>Edit Rate Master Detail
						</div>

						<div class="tools">
							
						</div>
					</div>
					<div class="portlet-body form">
						<br>
						<font color="red"> <s:actionerror/> <span id="error"></span></font>
						<font color="green"> <s:actionmessage/> </font>
						<div align="center"> <bold>Version Number</bold> : <s:property value="version" /> &nbsp;&nbsp;&nbsp;&nbsp;
											 <bold>Fare Type</bold> : <s:property value="fareType" />
						</div> 
						<!-- BEGIN FORM-->
						<form action="RateMasterAction!retailDetailUpdate" onsubmit="return validateStages()" class="form-horizontal" method="post">
							<br>
							<%
								String idd = request.getParameter("id");
							%>

							<input type="hidden" name="id" value="<%=idd%>" />
							<table class="table table-striped table-bordered table-hover"
								id="sample_6" style="height:300px; overflow-y:scroll; display:block;">
								<!-- <table class="table table-striped table-bordered table-hover" id="sample_1"> -->
								<thead >
									<tr>

										<th style="width1: 8px;">Stage No</th>
										<th>Adult</th>
										<th>Children</th>
										<th>Senior Citizen</th>
										<th>Luggage</th>
										<th>Happy Hour1</th>
										<th>Happy Hour2</th>
										


									</tr>
								</thead>
								<tbody>

									<s:iterator value="rateMasterDetailList"
										id="rateMasterDetailList">
										<tr>
											<td align="center"><input type="text" size="3"
												class="form-control" readonly="readonly"
												name="stageNo<s:property value='stageNo'/>"
												value="<s:property value="stageNo"/>"></td>

											<td align="center"><input size="15" maxLength="8"
												id="adult<s:property value='stageNo'/>" type="text"
												class="form-control"
												name="adult<s:property value='stageNo'/>"
												value="<s:property value="adult"/>"
												onchange="calcFare(<s:property value='stageNo'/>)"
												tabindex="<s:property value='stageNo'/>" >
												</td>

											<td align="center"><input size="15"
												id="children<s:property value='stageNo'/>" type="text"
												class="form-control"
												name="children<s:property value='stageNo'/>"
												value="<s:property value="children"/>"
												onchange="calcFare2('children',<s:property value='stageNo'/>)"
												maxLength="8"
												>
											</td>

											<td align="center"><input size="15"
												id="seniorCitizen<s:property value='stageNo'/>" type="text"
												class="form-control"
												name="seniorCitizen<s:property value='stageNo'/>"
												value="<s:property value="seniorCitizen"/>"
												onchange="calcFare2('seniorCitizen',<s:property value='stageNo'/>)"
												maxLength="8"
												>
											</td>
											<td align="center"><input size="15"
												id="luggage<s:property value='stageNo'/>" type="text"
												class="form-control"
												name="luggage<s:property value='stageNo'/>"
												value="<s:property value="luggage"/>"
												maxLength="8"
												>
											</td>
											<td align="center"><input size="15"
												id="happyhour1<s:property value='stageNo'/>" type="text"
												class="form-control"
												name="happyhour1<s:property value='stageNo'/>"
												value="<s:property value="happyhour1"/>"
												maxLength="8"
												>
											</td>
											<td align="center"><input size="15"
												id="happyhour2<s:property value='stageNo'/>" type="text"
												class="form-control"
												name="happyhour2<s:property value='stageNo'/>"
												value="<s:property value="happyhour2"/>"
												maxLength="8"
												>
											</td>


										</tr>
									</s:iterator>
								</tbody>
							</table>
							<div class="form-actions fluid" align="center">

								<div class="col-md-offset-3 col-md-5">
									<button type="submit" class="btn green">Save</button>
									<button type="button" class="btn red" onclick = "javascript:location.href='RateMasterAction';">Cancel</button>
								</div>


							</div>
							
						</form>
						<!-- END FORM-->
					</div>
				</div>
			</div>
		</div>
	</div>
</div>